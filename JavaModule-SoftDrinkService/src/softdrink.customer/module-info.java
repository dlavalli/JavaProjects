module softdrink.customer {
    // We need the service contract module.
    requires softdrink.api;

    // This tells the Service Registry that this consumer
    // is interested in implementations of SoftDrinkService.
    uses softdrink.api.SoftDrinkService;
}
