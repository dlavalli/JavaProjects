module softdrink.extralarge {
    // We need the service contract module.
    requires softdrink.api;

    // This tells the Service Registry to register this providers
    // implementation of SoftDrinkService; namely, ExtraLargeSoftDrinkService.
    // Note that the package softdrink.extralarge is NOT exported => the type
    // ExtraLargeSoftDrinkService is encapsulated within this module.
    provides softdrink.api.SoftDrinkService
      with softdrink.extralarge.ExtraLargeSoftDrinkService;
}
