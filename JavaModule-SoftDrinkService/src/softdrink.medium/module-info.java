module softdrink.medium {
  // We need the service contract module.
    requires softdrink.api;

    // This tells the Service Registry to register this providers
    // implementation of SoftDrinkService; namely, MediumSoftDrinkService.
    // Note that the package softdrink.medium is NOT exported => the type
    // MediumSoftDrinkService is encapsulated within this module.
    provides softdrink.api.SoftDrinkService
      with softdrink.medium.MediumSoftDrinkService;
}
