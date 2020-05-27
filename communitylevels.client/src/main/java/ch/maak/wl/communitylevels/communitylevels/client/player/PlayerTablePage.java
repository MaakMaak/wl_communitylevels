package ch.maak.wl.communitylevels.communitylevels.client.player;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.client.ui.tile.fields.AbstractTableFieldTile.TableField.Table;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import ch.maak.wl.communitylevels.communitylevels.client.level.AllLevelTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.util.AbstractShowPlayerMenu;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.IPlayerPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.PlayerTablePageData;

@PageData(PlayerTablePageData.class)
@ClassId("0f93b818-06d1-4cfe-af88-409c50498a2a")
public class PlayerTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("TopPlayers");
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return AbstractIcons.Star;
	}

	@Override
	protected void execLoadData(SearchFilter filter) throws ProcessingException {
		PlayerTablePageData pageData = BEANS.get(IPlayerPageService.class).getTopPlayersTableData();
		importPageData(pageData);
	}

	@Override
	protected IPage<?> execCreateChildPage(ITableRow row) {
		AllLevelTablePageParam param = new AllLevelTablePageParam();
		try {
			if (row.getCell(0) != null) {
				param.setRecordHolderId(row.getCell(0).toString());
			}
		} catch (Exception e) {
			System.out.println(e);
		}
		return new AllLevelTablePage(param);
	}

	@Order(10.0)
	public class Table extends AbstractTable {

		public RecordsColumn getRecordsColumn() {
			return getColumnSet().getColumnByClass(RecordsColumn.class);
		}

		public PointsColumn getPointsColumn() {
			return getColumnSet().getColumnByClass(PointsColumn.class);
		}

		public PlayerIdColumn getPlayerIdColumn() {
			return getColumnSet().getColumnByClass(PlayerIdColumn.class);
		}

		public PlayerNameColumn getPlayerNameColumn() {
			return getColumnSet().getColumnByClass(PlayerNameColumn.class);
		}

		public RankColumn getRankColumn() {
			return getColumnSet().getColumnByClass(RankColumn.class);
		}

		@Order(0)
		public class PlayerIdColumn extends AbstractStringColumn {

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(1000)
		public class RankColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Rank");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(2000)
		public class PlayerNameColumn extends AbstractStringColumn {
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

		@Order(3000)
		public class RecordsColumn extends AbstractLongColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Records");
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

		}

		@Order(3000)
		public class ShowPlayerMenu extends AbstractShowPlayerMenu {

			@Override
			protected String getPlayerId() {
				return getPlayerIdColumn().getSelectedValue();
			}

		}

	}
}
