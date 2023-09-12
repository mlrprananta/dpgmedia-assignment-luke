package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class BackstagePassesItemRuleTest {

  @Test
  void givenItemWithSellIn10_WhenUpdateQuality_ThenQualityIncreasesBy1() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", 10, 4);
    itemRule.updateQuality(item);
    assertEquals(5, item.quality);
  }

  @Test
  void givenItemWithSellIn9_WhenUpdateQuality_ThenQualityIncreasesBy2() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", 9, 4);
    itemRule.updateQuality(item);
    assertEquals(6, item.quality);
  }

  @Test
  void givenItemWithSellIn5_WhenUpdateQuality_ThenQualityIncreasesBy2() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", 5, 4);
    itemRule.updateQuality(item);
    assertEquals(6, item.quality);
  }

  @Test
  void givenItemWithSellIn4_WhenUpdateQuality_ThenQualityIncreasesBy3() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", 4, 4);
    itemRule.updateQuality(item);
    assertEquals(7, item.quality);
  }

  @Test
  void givenItemWithSellIn0_WhenUpdateQuality_ThenQualityIncreasesBy3() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", 0, 4);
    itemRule.updateQuality(item);
    assertEquals(7, item.quality);
  }

  @Test
  void givenItemWithNegativeSellIn_WhenUpdateQuality_ThenQualityIsZero() {
    ItemRule itemRule = new BackstagePassesItemRule();
    Item item = new Item("foo", -1, 4);
    itemRule.updateQuality(item);
    assertEquals(0, item.quality);
  }
}
