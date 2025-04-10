module softdrink.small {
  // We need the service contract module.
    requires softdrink.api;

    // This tells the Service Registry to register this providers
    // implementation of SoftDrinkService; namely, SmallSoftDrinkService.
    // Note that the package softdrink.small is NOT exported => the type
    // SmallSoftDrinkService is encapsulated within this module.
    provides softdrink.api.SoftDrinkService
      with softdrink.small.SmallSoftDrinkService;
}
