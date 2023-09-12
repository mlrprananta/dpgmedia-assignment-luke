package com.gildedrose;

class GildedRose {
  Item[] items;

  public GildedRose(Item[] items) {
    this.items = items;
  }

  /**
   * Some assumptions were made that should be clarified:
   *
   * <ul>
   *   <li>Quality is never negative and never more than 50. Should items created outside of these
   *       boundaries be corrected?
   *   <li>Do the effects of conjuration apply on top of the effects of the item (i.e. do Conjured
   *       Aged Brie items increase in quality twice)? Assumption: yes.
   *   <li>Can there be more than one legendary item? Assumption: yes.
   *   <li>Should legendary items be checked to have quality of 80?
   * </ul>
   */
  public void updateQuality() {
    for (Item item : items) {
      String name = item.name;

      int qualityDelta = -1;
      int deltaFactor = 1;

      if (name.startsWith("Conjured")) {
        deltaFactor *= 2;
        name = item.name.substring("Conjured ".length());
      }
      if (name.equals("Sulfuras, Hand of Ragnaros")) continue;

      item.sellIn -= 1;

      if (item.sellIn < 0) {
        deltaFactor *= 2;
      }
      if (name.equals("Aged Brie")) {
        qualityDelta = 1;
      }
      if (name.equals("Backstage passes to a TAFKAL80ETC concert")) {
        qualityDelta = 1;
        if (item.sellIn < 10) {
          qualityDelta += 1;
        }
        if (item.sellIn < 5) {
          qualityDelta += 1;
        }
        if (item.sellIn < 0) {
          qualityDelta = -item.quality;
        }
      }
      item.quality = Math.max(0, Math.min(item.quality + qualityDelta * deltaFactor, 50));
    }
  }
}
