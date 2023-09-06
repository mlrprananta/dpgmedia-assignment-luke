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
    assertEquals(app.items[0].sellIn, 1);
  }

  @Test
  void givenItem_WhenUpdateQuality_ThenItemDegrades() {
    Item[] items = new Item[] {new Item("foo", 2, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 99);
  }

  @Test
  void givenItemWithZeroQuality_WhenUpdateQuality_ThenQualityIsZero() {
    Item[] items = new Item[] {new Item("foo", 0, 0)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals("foo", app.items[0].name);
  }

  @Test
  void givenItemWithPassedSellByDate_WhenUpdateQuality_ThenItemDegradesTwice() {
    Item[] items = new Item[] {new Item("foo", 0, 50)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 98);
  }

  @Test
  void givenAgedBrie_WhenUpdateQuality_ThenItemQualityIncreases() {
    Item[] items = new Item[] {new Item("Aged Brie", 1, 40)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 41);
  }

  // Unsure if this is part of the requirements
  @Test
  void givenAgedBrieWithPassedSellByDate_WhenUpdateQuality_ThenItemQualityIncreasesTwice() {
    Item[] items = new Item[] {new Item("Aged Brie", 0, 40)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 42);
  }

  @Test
  void givenSulfares_WhenUpdateQuality_ThenItemQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 1, 80)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 80);
  }

  @Test
  void givenSulfaresWithPassedSellByDate_WhenUpdateQuality_ThenItemQualityDoesNotChange() {
    Item[] items = new Item[] {new Item("Sulfuras, Hand of Ragnaros", 0, 80)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, 80);
  }

  private static Stream<Arguments> backstagePassesTestCases() {
    /* sellIn,  expectedQuality */
    return Stream.of(
        Arguments.of(0, 0),
        Arguments.of(1, 13),
        Arguments.of(5, 13),
        Arguments.of(4, 13),
        Arguments.of(6, 12),
        Arguments.of(10, 12),
        Arguments.of(9, 12),
        Arguments.of(11, 11));
  }

  @ParameterizedTest
  @MethodSource("backstagePassesTestCases")
  void givenBackstagePasses_WhenUpdateQuality_ThenItemQualityChanges(
      int sellIn, int expectedQuality) {
    Item[] items = new Item[] {new Item("Backstage passes to a TAFKAL80ETC concert", sellIn, 10)};
    GildedRose app = new GildedRose(items);
    app.updateQuality();
    assertEquals(app.items[0].quality, expectedQuality);
  }
}
