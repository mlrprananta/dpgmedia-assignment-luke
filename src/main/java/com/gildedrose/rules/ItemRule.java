package com.gildedrose.rules;

import com.gildedrose.Item;

public abstract class ItemRule {

  /**
   * Apply the item rule on a given item.
   *
   * @param item The item to apply it on.
   */
  public void apply(Item item) {
    updateSellIn(item);
    updateQuality(item);
  }

  /**
   * Updates the quality of an item.
   *
   * @param item The item to apply it on.
   */
  void updateQuality(Item item) {}

  /**
   * Updates the sell in of an item.
   *
   * @param item The item to apply it on.
   */
  void updateSellIn(Item item) {
    item.sellIn -= 1;
  }

  /**
   * Decreases the quality of an item.
   *
   * @param item The item to decrease the quality for.
   */
  protected void decreaseQuality(Item item) {
    correctInvalidQuality(item);
    if (item.quality > 0 && item.quality <= 50) {
      item.quality -= 1;
    }
  }

  /**
   * Increases the quality of an item.
   *
   * @param item The item to increase the quality for.
   */
  protected void increaseQuality(Item item) {
    correctInvalidQuality(item);
    if (item.quality >= 0 && item.quality < 50) {
      item.quality += 1;
    }
  }

  private void correctInvalidQuality(Item item) {
    if (item.quality < 0) {
      item.quality = 0;
    }
    if (item.quality > 50) {
      item.quality = 50;
    }
  }
}
