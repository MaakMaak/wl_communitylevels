package ch.maak.wz.communitylevels.communitylevels.client.records;

import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticCodeType;
import org.eclipse.scout.rt.client.ui.tile.fields.AbstractHtmlFieldTile;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.cache.CacheRegistryService;
import org.eclipse.scout.rt.platform.util.NumberUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;


public abstract class AbstractRecordFieldTile extends AbstractHtmlFieldTile {

  @Override
  protected void execInitTile() {
    setDisplayStyle(DISPLAY_STYLE_DEFAULT);
    getTileWidget().setValue(buildHtml());
  }

  protected abstract String getTitle();

  protected abstract String getStatisticId();

  protected String getValue() {
    return NumberUtility.format(NumberUtility.parseInt(StringUtility.valueOf(BEANS.get(CacheRegistryService.class).get(StatisticCodeType.ID).get(getStatisticId()))));
  }

  protected String buildHtml() {
    return "<div style='display:flex;flex-direction:column; height:100%'>"
      + "<span style='color: #4bbbff;font-size: x-large;flex-grow:1'>" + getValue() + "</span>"
      + "<span style='padding: 20px 0;font-size:medium'>" + getTitle() + "</span></div>";
  }
}
