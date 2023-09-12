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
   *   <li>Quality is never negative and never over 50. Should items created outside of these
   *       boundaries be corrected? Assumptions:
   *       <ul>
   *         <li>Cannot decrease to negative or increase over 50 from valid quality.
   *         <li>Decreasing the quality from currently negative will set the quality to zero.
   *         <li>Increasing the quality from currently over 50 will set the quality to 50.
   *         <li>Increasing quality from negative will increase from 0.
   *         <li>Decreasing quality from over 50 will decrease from 50.
   *       </ul>
   *   <li>Do the effects of conjuration apply on top of the effects of the item (i.e. do Conjured
   *       Aged Brie items increase in quality twice)? Assumption: yes.
   *   <li>Can there be more than one legendary item? Assumption: yes.
   *   <li>Should legendary items be set to quality of 80? Assumption: yes.
   *   <li>Should invalid items still update the sell in? Assumption: yes.
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
        qualityDelta = setBackstagePassesQualityDelta(item);
      }
      correctInvalidQuality(item);
      item.quality = Math.max(0, Math.min(item.quality + qualityDelta * deltaFactor, 50));
    }
  }

  private static void correctInvalidQuality(Item item) {
    if (item.quality < 0) {
      item.quality = 0;
    }
    if (item.quality > 50) {
      item.quality = 50;
    }
  }

  private static int setBackstagePassesQualityDelta(Item item) {
    int qualityDelta;
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
    return qualityDelta;
  }
}
