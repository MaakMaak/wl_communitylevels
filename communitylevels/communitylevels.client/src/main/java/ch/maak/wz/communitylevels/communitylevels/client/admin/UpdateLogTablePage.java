package ch.maak.wz.communitylevels.communitylevels.client.admin;

import ch.maak.wz.communitylevels.communitylevels.shared.Icons;
import ch.maak.wz.communitylevels.communitylevels.shared.admin.IUpdateLogService;
import ch.maak.wz.communitylevels.communitylevels.shared.admin.UpdateLogTablePageData;
import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateTimeColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;


@Data(UpdateLogTablePageData.class)
public class UpdateLogTablePage extends AbstractPageWithTable<UpdateLogTablePage.Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("UpdateLog");
  }

  @Override
  public String getOverviewIconId() {
    return Icons.Gear;
  }

  @Override
  protected boolean getConfiguredLeaf() {
    return true;
  }

  @Override
  protected void execLoadData(SearchFilter filter) {
    importPageData(BEANS.get(IUpdateLogService.class).getUpdateLogTableData(filter));
  }

  public class Table extends AbstractTable {

    @Override
    protected boolean getConfiguredAutoResizeColumns() {
      return true;
    }

    public EvtEndColumn getEvtEndColumn() {
      return getColumnSet().getColumnByClass(EvtEndColumn.class);
    }

    public LevelsColumn getLevelsColumn() {
      return getColumnSet().getColumnByClass(LevelsColumn.class);
    }

    public EvtStartColumn getEvtStartColumn() {
      return getColumnSet().getColumnByClass(EvtStartColumn.class);
    }

    public CountColumn getCountColumn() {
      return getColumnSet().getColumnByClass(CountColumn.class);
    }

    @Order(1000)
    public class CountColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Count");
      }

      @Override
      public int getAutoOptimizeMaxWidth() {
        return 60;
      }
    }

    @Order(2000)
    public class EvtStartColumn extends AbstractDateTimeColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("StartDate");
      }

      @Override
      public int getAutoOptimizeMaxWidth() {
        return 75;
      }

      @Override
      protected String getConfiguredFormat() {
        return "MMM d, y hh:mm:ss";
      }
    }

    @Order(3000)
    public class EvtEndColumn extends AbstractDateTimeColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("EndDate");
      }

      @Override
      public int getAutoOptimizeMaxWidth() {
        return 60;
      }

      @Override
      protected String getConfiguredFormat() {
        return "MMM d, y hh:mm:ss";
      }
    }

    @Order(4000)
    public class LevelsColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Levels");
      }

      @Override
      protected int getConfiguredWidth() {
        return 500;
      }
    }
  }
}
