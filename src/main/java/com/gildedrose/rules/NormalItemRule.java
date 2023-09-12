package com.gildedrose.rules;

import com.gildedrose.Item;

public class NormalItemRule extends ItemRule {
  @Override
  void updateQuality(Item item) {
    decreaseQuality(item);
    if (item.sellIn < 0) {
      decreaseQuality(item);
    }
  }
}
