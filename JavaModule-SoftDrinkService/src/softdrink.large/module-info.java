module softdrink.large {
  // We need the service contract module.
    requires softdrink.api;

    // This tells the Service Registry to register this providers
    // implementation of SoftDrinkService; namely, LargeSoftDrinkService.
    // Note that the package softdrink.large is NOT exported => the type
    // LargeSoftDrinkService is encapsulated within this module.
    provides softdrink.api.SoftDrinkService
      with softdrink.large.LargeSoftDrinkService;
}
