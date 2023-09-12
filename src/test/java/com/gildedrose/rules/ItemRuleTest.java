package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class ItemRuleTest {

  @Test
  void givenItem_WhenUpdateSellIn_ThenSellInDecreases() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.updateSellIn(item);
    assertEquals(0, item.sellIn);
  }

  @Test
  void givenItem_WhenDecreaseQuality_ThenQualityDecreases() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.decreaseQuality(item);
    assertEquals(1, item.quality);
  }

  @Test
  void givenItemWithNegativeQuality_WhenDecreaseQuality_ThenQualityIsZero() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, -2);
    itemRule.decreaseQuality(item);
    assertEquals(0, item.quality);
  }

  @Test
  void givenItemWith100Quality_WhenDecreaseQuality_ThenQualityDecreasesFrom50() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 100);
    itemRule.decreaseQuality(item);
    assertEquals(49, item.quality);
  }

  @Test
  void givenItem_WhenIncreaseQuality_ThenQualityIncreases() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.increaseQuality(item);
    assertEquals(3, item.quality);
  }

  @Test
  void givenItemWithNegativeQuality_WhenIncreaseQuality_ThenQualityIncreasesFromZero() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, -2);
    itemRule.increaseQuality(item);
    assertEquals(1, item.quality);
  }

  @Test
  void givenItemWith100Quality_WhenIncreaseQuality_ThenQualityIs50() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 100);
    itemRule.increaseQuality(item);
    assertEquals(50, item.quality);
  }
}
