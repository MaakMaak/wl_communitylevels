package ch.maak.wz.communitylevels.communitylevels.client.level;

import ch.maak.wz.communitylevels.communitylevels.shared.level.*;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.date.DateUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;

import java.util.Date;


@PageData(NewestLevelTablePageData.class)
@ClassId("0cbe475a-71c1-443b-8314-d64c8065fcaf")
public class NewestLevelTablePage extends AbstractLevelTablePage {

  public NewestLevelTablePage() {
    super(new NewestLevelTablePageParam());
  }

  public NewestLevelTablePage(NewestLevelTablePageParam param) {
    super(param);
  }

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("NewestLevels");
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return AbstractIcons.Calendar;
  }

  @Override
  protected void execInitPage() {
    m_param.setMinCreatedDate(DateUtility.addDays(new Date(), -15));
  }

  @Override
  protected boolean getConfiguredSearchRequired() {
    return false;
  }

  @Override
  protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
    return BEANS.get(ILevelPageService.class).getLevelTableData(formData, m_param, new NewestLevelTablePageData());
  }

  public class Table extends AbstractLevelTablePage.Table {

    @Replace
    public class LevelNrColumn extends AbstractLevelTablePage.Table.LevelNrColumn {
      @Override
      protected int getConfiguredSortIndex() {
        return 1;
      }

      @Override
      protected boolean getConfiguredSortAscending() {
        return false;
      }
    }

    @Replace
    public class EvtCreatedColumn extends AbstractLevelTablePage.Table.EvtCreatedColumn {
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
