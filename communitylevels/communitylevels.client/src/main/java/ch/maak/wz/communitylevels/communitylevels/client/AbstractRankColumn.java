package ch.maak.wz.communitylevels.communitylevels.client;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class AbstractRankColumn extends AbstractIntegerColumn {
  @Override
  protected String getConfiguredHeaderText() {
    return TEXTS.get("Rank");
  }

  @Override
  protected int getConfiguredAutoOptimizeMaxWidth() {
    return 60;
  }

  @Override
  protected int getConfiguredWidth() {
    return 60;
  }

  @Override
  protected boolean getConfiguredAutoOptimizeWidth() {
    return false;
  }
}
