package com.gildedrose.rules;

import com.gildedrose.Item;

public class BackstagePassesItemRule extends ItemRule {
  public static final String ITEM_NAME_PREFIX = "Backstage passes ";

  @Override
  void updateQuality(Item item) {
    increaseQuality(item);
    if (item.sellIn < 10) {
      increaseQuality(item);
    }
    if (item.sellIn < 5) {
      increaseQuality(item);
    }
    if (item.sellIn < 0) {
      item.quality = 0;
    }
  }
}
