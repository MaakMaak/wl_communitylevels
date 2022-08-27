package ch.maak.wz.communitylevels.communitylevels.client.level;

import ch.maak.wz.communitylevels.communitylevels.shared.level.*;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;


@PageData(CreatorLevelTablePageData.class)
public class CreatorLevelTablePage extends AbstractLevelTablePage {

  public CreatorLevelTablePage() {
    super(new CreatorLevelTablePageParam());
  }

  public CreatorLevelTablePage(CreatorLevelTablePageParam param) {
    super(param);
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return AbstractIcons.PersonSolid;
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("YourLevels");
  }

  @Override
  protected boolean getConfiguredSearchRequired() {
    return false;
  }

  @Override
  protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
    return BEANS.get(ILevelPageService.class).getLevelTableData(formData, m_param, new AllLevelTablePageData());
  }

  public class Table extends AbstractLevelTablePage.Table {

    public CustomEvtCreatedColumn getCustomEvtCreatedColumn() {
      return getColumnSet().getColumnByClass(CustomEvtCreatedColumn.class);
    }

    @Replace
    public class CustomEvtCreatedColumn extends EvtCreatedColumn {

      @Override
      protected int getConfiguredSortIndex() {
        return 0;
      }

      @Override
      protected boolean getConfiguredSortAscending() {
        return false;
      }
    }
  }
}
