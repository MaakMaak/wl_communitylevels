package ch.maak.wz.communitylevels.communitylevels.client.player;

import ch.maak.wz.communitylevels.communitylevels.client.level.AllLevelTablePage;
import ch.maak.wz.communitylevels.communitylevels.client.util.AbstractShowPlayerMenu;
import ch.maak.wz.communitylevels.communitylevels.shared.level.AllLevelTablePageParam;
import ch.maak.wz.communitylevels.communitylevels.shared.level.CreatorTablePageData;
import ch.maak.wz.communitylevels.communitylevels.shared.level.ICreatorPageService;
import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.tile.fields.AbstractTableFieldTile.TableField.Table;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import java.util.Set;


@PageData(CreatorTablePageData.class)
@ClassId("d762e81b-5f0c-4d75-b9eb-a95d43dfec53")
public class CreatorTablePage extends AbstractPageWithTable<Table> {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("TopCreators");
  }

  @Override
  protected String getConfiguredOverviewIconId() {
    return AbstractIcons.StarMarked;
  }

  @Override
  protected void execLoadData(SearchFilter filter) throws ProcessingException {
    CreatorTablePageData pageData = BEANS.get(ICreatorPageService.class).getCreatorTableData();
    importPageData(pageData);
  }

  @Override
  protected IPage<?> execCreateChildPage(ITableRow row) {
    AllLevelTablePageParam param = new AllLevelTablePageParam();
    try {
      if (row.getCell(1) != null) {
        param.setCreatorId(row.getCell(1).toString());
      }
    } catch (Exception e) {
      System.out.println(e);
    }
    return new AllLevelTablePage(param);
  }

  @Order(10.0)
  public class Table extends AbstractTable {

    public CreatorIdColumn getCreatorIdColumn() {
      return getColumnSet().getColumnByClass(CreatorIdColumn.class);
    }

    public CreatorNameColumn getCreatorNameColumn() {
      return getColumnSet().getColumnByClass(CreatorNameColumn.class);
    }

    public TotalAttemptsColumn getTotalAttemptsColumn() {
      return getColumnSet().getColumnByClass(TotalAttemptsColumn.class);
    }

    public TotalLikesColumn getTotalLikesColumn() {
      return getColumnSet().getColumnByClass(TotalLikesColumn.class);
    }

    public TotalWinsColumn getTotalWinsColumn() {
      return getColumnSet().getColumnByClass(TotalWinsColumn.class);
    }

    public WinRateColumn getWinRateColumn() {
      return getColumnSet().getColumnByClass(WinRateColumn.class);
    }

    public TotalLevelsColumn getTotalLevelsColumn() {
      return getColumnSet().getColumnByClass(TotalLevelsColumn.class);
    }

    public PointsDifficultyColumn getPointsDifficultyColumn() {
      return getColumnSet().getColumnByClass(PointsDifficultyColumn.class);
    }

    public PointsAttemptsColumn getPointsAttemptsColumn() {
      return getColumnSet().getColumnByClass(PointsAttemptsColumn.class);
    }

    public PointsColumn getPointsColumn() {
      return getColumnSet().getColumnByClass(PointsColumn.class);
    }

    public RankColumn getRankColumn() {
      return getColumnSet().getColumnByClass(RankColumn.class);
    }

    @Order(1000)
    public class RankColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Rank");
      }

      @Override
      protected int getConfiguredWidth() {
        return 50;
      }

      @Override
      protected Set<String> getConfiguredAllowedAggregationFunctions() {
        return CollectionUtility.emptyHashSet();
      }
    }

    @Order(2000)
    public class CreatorIdColumn extends AbstractStringColumn {

      @Override
      protected boolean getConfiguredDisplayable() {
        return false;
      }
    }

    @Order(3000)
    public class CreatorNameColumn extends AbstractStringColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Name");
      }

      @Override
      protected int getConfiguredWidth() {
        return 200;
      }

      @Override
      protected boolean getConfiguredSummary() {
        return true;
      }
    }

    @Order(3500)
    public class TotalAttemptsColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TotalAttempts");
      }

      @Override
      protected int getConfiguredWidth() {
        return 125;
      }
    }

    @Order(3750)
    public class TotalLikesColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TotalLikes");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(3875)
    public class TotalWinsColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TotalWins");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(3937)
    public class WinRateColumn extends AbstractBigDecimalColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("WinRate");
      }

      @Override
      protected boolean getConfiguredPercent() {
        return true;
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(3968)
    public class TotalLevelsColumn extends AbstractIntegerColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("TotalLevels");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }
    }

    @Order(4000)
    public class PointsColumn extends AbstractBigDecimalColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("Points");
      }

      @Override
      protected int getConfiguredWidth() {
        return 100;
      }

      @Override
      protected boolean getConfiguredSortAscending() {
        return false;
      }

      @Override
      protected int getConfiguredSortIndex() {
        return 0;
      }

      @Override
      protected String getConfiguredBackgroundEffect() {
        return BackgroundEffect.BAR_CHART;
      }

      @Override
      protected String getConfiguredHeaderTooltipText() {
        return TEXTS.get("PointsCalculation");
      }
    }

    @Order(5000)
    public class PointsLikesColumn extends AbstractBigDecimalColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PointsLikes");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(6000)
    public class PointsAttemptsColumn extends AbstractBigDecimalColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PointsAttempts");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(7000)
    public class PointsDifficultyColumn extends AbstractBigDecimalColumn {
      @Override
      protected String getConfiguredHeaderText() {
        return TEXTS.get("PointsDifficulty");
      }

      @Override
      protected int getConfiguredWidth() {
        return 150;
      }

      @Override
      protected boolean getConfiguredVisible() {
        return false;
      }
    }

    @Order(3000)
    public class ShowCreatorMenu extends AbstractShowPlayerMenu {

      @Override
      protected String getConfiguredText() {
        return TEXTS.get("ShowCreator");
      }

      @Override
      protected String getPlayerId() {
        return getCreatorIdColumn().getSelectedValue();
      }

    }
  }
}
