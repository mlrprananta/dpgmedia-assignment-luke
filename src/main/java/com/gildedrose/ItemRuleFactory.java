package com.gildedrose;

import com.gildedrose.rules.*;

public class ItemRuleFactory {

  static ItemRule createItemRule(String itemName) {
    if (itemName.startsWith(ConjuredItemRule.ITEM_NAME_PREFIX)) {
      return new ConjuredItemRule(createItemRule(itemName.substring("Conjured ".length())));
    }
    if (LegendaryItemRule.ITEM_NAMES.contains(itemName)) {
      return new LegendaryItemRule();
    }
    if (itemName.equals(AgedBrieItemRule.ITEM_NAME)) {
      return new AgedBrieItemRule();
    }
    if (itemName.startsWith(BackstagePassesItemRule.ITEM_NAME_PREFIX)) {
      return new BackstagePassesItemRule();
    }
    return new NormalItemRule();
  }
}
