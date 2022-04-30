package com.lavalliere.daniel.microsoft.msgraph;

import com.azure.identity.DeviceCodeCredential;
import com.azure.identity.DeviceCodeCredentialBuilder;

import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.logger.DefaultLogger;
import com.microsoft.graph.logger.LoggerLevel;
import com.microsoft.graph.models.*;
import com.microsoft.graph.options.HeaderOption;
import com.microsoft.graph.options.Option;
import com.microsoft.graph.options.QueryOption;
import com.microsoft.graph.requests.EventCollectionPage;
import com.microsoft.graph.requests.EventCollectionRequestBuilder;
import com.microsoft.graph.requests.GraphServiceClient;
import lombok.extern.slf4j.Slf4j;
import okhttp3.Request;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Slf4j
public class Graph {
    private static GraphServiceClient<Request> graphClient = null;
    private static TokenCredentialAuthProvider authProvider = null;

    public static void initializeGraphAuth(
            String applicationId,
            List<String> scopes) {

        // Create the auth provider for authentication with MS Graph
        final DeviceCodeCredential credential = new DeviceCodeCredentialBuilder()
                .clientId(applicationId)
                .challengeConsumer(challenge -> System.out.println(challenge.getMessage()))
                .build();
        authProvider = new TokenCredentialAuthProvider(scopes, credential);

        // Create default logger to only log errors
        DefaultLogger logger = new DefaultLogger();
        logger.setLoggingLevel(LoggerLevel.ERROR);

        // Build a Graph client to provide access to MS Graph functionality
        graphClient = GraphServiceClient.builder()
                .authenticationProvider(authProvider)
                .logger(logger)
                .buildClient();
    }

    public static String getUserAccessToken()
    {
        try {
            URL meUrl = new URL("https://graph.microsoft.com/v1.0/me");
            return authProvider.getAuthorizationTokenAsync(meUrl).get();
        } catch(Exception ex) {
            return null;
        }
    }
    public static User getUser() {
        if (graphClient == null) {
            throw new NullPointerException(
                    "Graph client has not been initialized. Call initializeGraphAuth before calling this method"
            );
        }

        // GET /me to get authenticated user
        User me = graphClient
                .me()
                .buildRequest()
                .select("displayName,mailboxSettings")
                .get();

        return me;
    }

    /*
    - The URL that will be called is:   /me/calendarview.
    - QueryOption objects are used to add the startDateTime and endDateTime
      parameters, setting the start and end of the calendar view.
    - A QueryOption object is used to add the $orderby parameter,
      sorting the results by start time.
    - A HeaderOption object is used to add the Prefer: outlook.timezone header,
      causing the start and end times to be adjusted to the user's time zone.
    - The select function limits the fields returned for each event
      to just those the app will actually use.
    - The top function limits the number of events in the response
      to a maximum of 25.
    - The getNextPage function is used to request additional pages of results
      if there are more than 25 events in the current week.
     */
    public static List<Event> getCalendarView(
            ZonedDateTime viewStart,
            ZonedDateTime viewEnd,
            String timeZone) {
        if (graphClient == null) {
            throw new NullPointerException(
                    "Graph client has not been initialized. Call initializeGraphAuth before calling this method");
        }

        List<Option> options = new LinkedList<Option>();
        options.add(new QueryOption("startDateTime", viewStart.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));
        options.add(new QueryOption("endDateTime", viewEnd.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME)));

        // Sort results by start time
        options.add(new QueryOption("$orderby", "start/dateTime"));

        // Start and end times adjusted to user's time zone
        options.add(new HeaderOption("Prefer", "outlook.timezone=\"Canada/Eastern\""));

        // GET /me/events
        EventCollectionPage eventPage = graphClient
                .me()
                .calendarView()
                .buildRequest(options)
                .select("subject,organizer,start,end")
                .top(25)
                .get();

        List<Event> allEvents = new LinkedList<Event>();

        // Create a separate list of options for the paging requests
        // paging request should not include the query parameters from the initial
        // request, but should include the headers.
        List<Option> pagingOptions = new LinkedList<Option>();
        pagingOptions.add(new HeaderOption("Prefer", "outlook.timezone=\"Canada/Eastern\""));

        while (eventPage != null) {
            allEvents.addAll(eventPage.getCurrentPage());

            EventCollectionRequestBuilder nextPage =
                    eventPage.getNextPage();

            if (nextPage == null) {
                break;
            } else {
                eventPage = nextPage
                        .buildRequest(pagingOptions)
                        .get();
            }
        }

        return allEvents;
    }

    public static void createEvent(
            String timeZone,
            String subject,
            LocalDateTime start,
            LocalDateTime end,
            Set<String> attendees,
            String body)
    {
        if (graphClient == null) {
            throw new NullPointerException(
                    "Graph client has not been initialized. Call initializeGraphAuth before calling this method");
        }

        Event newEvent = new Event();
        newEvent.subject = subject;
        newEvent.start = new DateTimeTimeZone();
        newEvent.start.dateTime = start.toString();
        newEvent.start.timeZone = timeZone;
        newEvent.end = new DateTimeTimeZone();
        newEvent.end.dateTime = end.toString();
        newEvent.end.timeZone = timeZone;

        if (attendees != null && !attendees.isEmpty()) {
            newEvent.attendees = new LinkedList<Attendee>();

            attendees.forEach((email) -> {
                Attendee attendee = new Attendee();
                // Set each attendee as required
                attendee.type = AttendeeType.REQUIRED;
                attendee.emailAddress = new EmailAddress();
                attendee.emailAddress.address = email;
                newEvent.attendees.add(attendee);
            });
        }

        if (body != null) {
            newEvent.body = new ItemBody();
            newEvent.body.content = body;
            // Treat body as plain text
            newEvent.body.contentType = BodyType.TEXT;
        }

        // POST /me/events
        graphClient
            .me()
            .events()
            .buildRequest()
            .post(newEvent);
    }
}
