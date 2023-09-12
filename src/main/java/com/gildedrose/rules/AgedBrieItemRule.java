package com.gildedrose.rules;

import com.gildedrose.Item;

public class AgedBrieItemRule extends ItemRule {
  public static final String ITEM_NAME = "Aged Brie";

  @Override
  void updateQuality(Item item) {
    increaseQuality(item);
    if (item.sellIn < 0) {
      increaseQuality(item);
    }
  }
}
