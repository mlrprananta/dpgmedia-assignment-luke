package com.gildedrose;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Stream;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

class GildedRoseTest {
  @Test
  void givenItem_WhenUpdateQuality_ThenSellInDecreases() {
    Item[] items = new Item[] {new Item("foo", 2, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(1, app.items[0].sellIn);
  }

  @Test
  void givenItem_WhenUpdateQuality_ThenQualityDecreases() {
    Item[] items = new Item[] {new Item("foo", 2, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(49, app.items[0].quality);
  }

  @Test
  void givenItemWithZeroQuality_WhenUpdateQuality_ThenQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("foo", 0, 0)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void givenItemWithNegativeQuality_WhenUpdateQuality_ThenQualityIsZero() {
    Item[] items = new Item[] {new Item("foo", 0, -1)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(0, app.items[0].quality);
  }

  @Test
  void givenItemWith100Quality_WhenUpdateQuality_ThenQualityDecreasesFrom50() {
    Item[] items = new Item[] {new Item("foo", 1, 100)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(49, app.items[0].quality);
  }

  @Test
  void givenItemWithPassedSellByDate_WhenUpdateQuality_ThenQualityDecreasesTwice() {
    Item[] items = new Item[] {new Item("foo", 0, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(48, app.items[0].quality);
  }

  @Test
  void givenAgedBrie_WhenUpdateQuality_ThenQualityIncreases() {
    Item[] items = new Item[] {new Item("Aged Brie", 1, 40)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(41, app.items[0].quality);
  }

  @Test
  void givenAgedBrieWithNegativeQuality_WhenUpdateQuality_ThenQualityIncreasesFromZero() {
    Item[] items = new Item[] {new Item("Aged Brie", 1, -10)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(1, app.items[0].quality);
  }

  @Test
  void givenAgedBrieWithMaxQuality_WhenUpdateQuality_ThenQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("Aged Brie", 1, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(50, app.items[0].quality);
  }

  @Test
  void givenAgedBrieWith100Quality_WhenUpdateQuality_ThenQualityIs50() {
    Item[] items = new Item[] {new Item("Aged Brie", 1, 100)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(50, app.items[0].quality);
  }

  @Test
  void givenAgedBrieWithPassedSellByDate_WhenUpdateQuality_ThenQualityIncreasesTwice() {
    Item[] items = new Item[] {new Item("Aged Brie", 0, 40)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(42, app.items[0].quality);
  }

  @Test
  void givenSulfares_WhenUpdateQuality_ThenQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 1, 80)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(80, app.items[0].quality);
  }

  @Test
  void givenSulfaresWithPassedSellByDate_WhenUpdateQuality_ThenQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(80, app.items[0].quality);
  }

  @Test
  void givenSulfares_WhenUpdateQuality_ThenSellInDoesNotChange() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 1, 80)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(1, app.items[0].sellIn);
  }

  private static Stream<Arguments> backstagePassesTestCases() {
    /* sellIn,  expectedQuality */
    return Stream.of(
        Arguments.of(0, 0),
        Arguments.of(1, 13),
        Arguments.of(5, 13),
        Arguments.of(6, 12),
        Arguments.of(10, 12),
        Arguments.of(11, 11));
  }

  @ParameterizedTest
  @MethodSource("backstagePassesTestCases")
  void givenBackstagePasses_WhenUpdateQuality_ThenQualityChanges(int sellIn, int expectedQuality) {
    Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, 10)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(sellIn - 1, app.items[0].sellIn);
    assertEquals(expectedQuality, app.items[0].quality);
  }

  private static Stream<Arguments> conjuredItemTestCases() {
    /* name, sellIn, quality, expectedQuality */
    return Stream.of(
        Arguments.of("Conjured foo", 0, 10, 6),
        Arguments.of("Conjured foo", 1, 10, 8),
        Arguments.of("Conjured foo", 1, -10, 0),
        Arguments.of("Conjured foo", 1, 100, 48),
        Arguments.of("Conjured Aged Brie", 0, 10, 14),
        Arguments.of("Conjured Aged Brie", 1, 10, 12),
        Arguments.of("Conjured Aged Brie", 1, -10, 2),
        Arguments.of("Conjured Aged Brie", 1, 100, 50),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 0, 10, 0),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 1, 10, 16),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 5, 10, 16),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 6, 10, 14),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 10, 10, 14),
        Arguments.of("Conjured Backstage passes to a TAFKAL80ETC concert", 11, 10, 12),
        Arguments.of("Conjured Sulfuras, Hand of Ragnaros", 0, 80, 80),
        Arguments.of("Conjured Sulfuras, Hand of Ragnaros", 1, 80, 80));
  }

  @ParameterizedTest
  @MethodSource("conjuredItemTestCases")
  void givenConjuredItem_WhenUpdateQuality_ThenQualityDegradesTwiceAsFast(
      String name, int sellIn, int quality, int expectedQuality) {
    Item[] items = new Item[] {new Item(name, sellIn, quality)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(expectedQuality, app.items[0].quality);
  }
}
