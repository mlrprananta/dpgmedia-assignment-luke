package com.gildedrose.rules;

import static org.junit.jupiter.api.Assertions.*;

import com.gildedrose.Item;
import org.junit.jupiter.api.Test;

class ConjuredItemRuleTest {
  @Test
  void givenNormalItemRule_WhenUpdateQuality_ThenQualityUpdatesTwice() {
    ItemRule itemRule = new NormalItemRule();
    ConjuredItemRule conjuredItemRule = new ConjuredItemRule(itemRule);
    Item item = new Item("foo", 2, 4);
    conjuredItemRule.updateQuality(item);
    assertEquals(2, item.quality);
  }

  @Test
  void givenNormalItemRule_WhenUpdateSellIn_ThenSellInDecreases() {
    ItemRule itemRule = new NormalItemRule();
    ConjuredItemRule conjuredItemRule = new ConjuredItemRule(itemRule);
    Item item = new Item("foo", 2, 4);
    conjuredItemRule.updateSellIn(item);
    assertEquals(1, item.sellIn);
  }
}
