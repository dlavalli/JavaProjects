package softdrink.customer;

import java.util.ServiceLoader;

import softdrink.api.SoftDrinkService;

public class Main {
    public static void main(String[] args){
      ServiceLoader<SoftDrinkService> softDrinkServices = ServiceLoader.load(SoftDrinkService.class);

      // Lazy - using the Stream API
      softDrinkServices.stream()                // OUT: Stream<ServiceLoader.Provider<SoftDrinkService>>
          .filter(provider -> provider.type().toString().contains("Small")) // healthiest
          .map(ServiceLoader.Provider::get)     // instantiates the service    OUT: SoftDrinkService
          .forEach(softDrinkService -> System.out.println(softDrinkService.getSize()));
    }
}
/*
        //Eager - use iteration
        for(SoftDrinkService softDrinkService: softDrinkServices){
          System.out.println(softDrinkService.getSize());
*/
