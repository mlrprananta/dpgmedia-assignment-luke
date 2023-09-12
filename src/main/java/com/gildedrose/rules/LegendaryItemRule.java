package com.gildedrose.rules;

import com.gildedrose.Item;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LegendaryItemRule extends ItemRule {
  public static final Set<String> ITEM_NAMES =
      Stream.of("Sulfuras, Hand of Ragnaros").collect(Collectors.toSet());

  @Override
  void updateQuality(Item item) {
    item.quality = 80;
  }

  @Override
  void updateSellIn(Item item) {}
}
