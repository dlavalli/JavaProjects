module farm.stock.count {
  // - We can selectively export packages.
  // - All packages NOT listed remain strongly
  //   encapsulated.
  exports farm.stock.count;
  // exports pkg to moduleA, moduleB;
// exports farm.stock.count to farm.stock.sale;
}
