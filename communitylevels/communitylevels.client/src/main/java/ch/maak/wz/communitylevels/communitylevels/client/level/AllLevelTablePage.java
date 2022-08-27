package ch.maak.wz.communitylevels.communitylevels.client.level;

import ch.maak.wz.communitylevels.communitylevels.shared.level.*;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

@PageData(AllLevelTablePageData.class)
public class AllLevelTablePage extends AbstractLevelTablePage {

  public AllLevelTablePage() {
    super(new AllLevelTablePageParam());
  }

  @Override
  public void initPage() {
    super.initPage();
    setSearchRequired(getPageParam().isSearchRequired());
  }

  public AllLevelTablePage(AllLevelTablePageParam param) {
    super(param);
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return AbstractIcons.List;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("AllLevels");
  }

  @Override
  protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
    return BEANS.get(ILevelPageService.class).getLevelTableData(formData, m_param, new AllLevelTablePageData());
  }

  public class Table extends AbstractLevelTablePage.Table {

  }
}
