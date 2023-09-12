package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class LegendaryItemRuleTest {
  @Test
  void givenItem_WhenUpdateQuality_ThenQualityIs80() {
    ItemRule itemRule = new LegendaryItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.apply(item);
    assertEquals(80, item.quality);
  }

  @Test
  void givenItem_WhenUpdateSellIn_ThenSellInDoesNotChange() {
    ItemRule itemRule = new LegendaryItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.apply(item);
    assertEquals(1, item.sellIn);
  }
}
