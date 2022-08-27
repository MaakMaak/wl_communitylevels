package ch.maak.wz.communitylevels.communitylevels.client;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class AbstractLevelColumn extends AbstractStringColumn {
  @Override
  protected String getConfiguredHeaderText() {
    return TEXTS.get("Level");
  }

  @Override
  protected int getConfiguredWidth() {
    return 250;
  }
}
