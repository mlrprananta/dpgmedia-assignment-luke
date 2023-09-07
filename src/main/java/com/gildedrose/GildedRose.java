package com.gildedrose;

/**
 * Some constraints:
 *
 * <ul>
 *   <li>Item class and items property can not be altered
 * </ul>
 */
class GildedRose {
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  public void updateQuality() {
    for (Item item : items) {
      String name = item.name;
      int degradation = -1;
      int degradationFactor = 1;
      if (item.sellIn <= 0) {
        degradationFactor *= 2;
      }
      if (name.startsWith("Conjured")) {
        degradationFactor *= 2;
        name = item.name.substring("Conjured ".length());
      }
      if (name.equals("Sulfuras, Hand of Ragnaros")) continue;
      if (name.equals("Aged Brie")) {
        degradation = 1;
      }
      if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        degradation = 1;
        if (item.sellIn < 11) {
          degradation += 1;
        }
        if (item.sellIn < 6) {
          degradation += 1;
        }
        if (item.sellIn <= 0) {
          degradation = -item.quality;
        }
      }
      item.quality = Math.max(0, Math.min(item.quality + degradation * degradationFactor, 50));
      item.sellIn -= 1;
    }
  }
}
