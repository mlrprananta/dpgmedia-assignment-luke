package com.gildedrose.rules;

import com.gildedrose.Item;

public class ConjuredItemRule extends ItemRule {
  public static final String ITEM_NAME_PREFIX = "Conjured ";
  private final ItemRule originalItemRule;

  /**
   * The conjured item rule applies the original item rule twice.
   *
   * @param originalItemRule The original item rule to apply twice.
   */
  public ConjuredItemRule(ItemRule originalItemRule) {
    this.originalItemRule = originalItemRule;
  }

  @Override
  void updateQuality(Item item) {
    originalItemRule.updateQuality(item);
    originalItemRule.updateQuality(item);
  }

  @Override
  void updateSellIn(Item item) {
    originalItemRule.updateSellIn(item);
  }
}
