package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class NormalItemRuleTest {
  @Test
  void givenItem_WhenUpdateQuality_ThenQualityDecreases() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", 1, 2);
    itemRule.updateQuality(item);
    assertEquals(1, item.quality);
  }

  @Test
  void givenItemWithNegativeSellIn_WhenUpdateQuality_ThenQualityDecreasesTwice() {
    ItemRule itemRule = new NormalItemRule();
    Item item = new Item("foo", -1, 4);
    itemRule.updateQuality(item);
    assertEquals(2, item.quality);
  }
}
