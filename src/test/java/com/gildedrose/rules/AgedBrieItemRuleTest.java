package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class AgedBrieItemRuleTest {
  @Test
  void givenItem_WhenUpdateQuality_ThenQualityIncreases() {
    ItemRule itemRule = new AgedBrieItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.updateQuality(item);
    assertEquals(3, item.quality);
  }

  @Test
  void givenItemWithNegativeSellIn_WhenUpdateQuality_ThenQualityIncreasesTwice() {
    ItemRule itemRule = new AgedBrieItemRule();
    Item item = new Item("foo", -1, 4);
    itemRule.updateQuality(item);
    assertEquals(6, item.quality);
  }
}
