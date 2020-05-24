package ch.maak.wl.communitylevels.communitylevels.client.clan;

import org.eclipse.scout.rt.client.dto.Data;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import ch.maak.wl.communitylevels.communitylevels.client.clan.ClanTablePage.Table;
import ch.maak.wl.communitylevels.communitylevels.client.util.AbstractShowClanMenu;
import ch.maak.wl.communitylevels.communitylevels.shared.Icons;
import ch.maak.wl.communitylevels.communitylevels.shared.clan.ClanTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.clan.IClanPageService;

@Data(ClanTablePageData.class)
public class ClanTablePage extends AbstractPageWithTable<Table> {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("TopClans");
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return "icon/" + Icons.Trophy75 + ".png";
	}

	@Override
	protected void execLoadData(SearchFilter filter) {
		importPageData(BEANS.get(IClanPageService.class).getClanTableData(filter));
	}

	public class Table extends AbstractTable {

		public ClanIdColumn getClanIdColumn() {
			return getColumnSet().getColumnByClass(ClanIdColumn.class);
		}

		public ClanNameColumn getClanNameColumn() {
			return getColumnSet().getColumnByClass(ClanNameColumn.class);
		}

		public RecordsColumn getRecordsColumn() {
			return getColumnSet().getColumnByClass(RecordsColumn.class);
		}

		public PointsColumn getPointsColumn() {
			return getColumnSet().getColumnByClass(PointsColumn.class);
		}

		public RankColumn getRankColumn() {
			return getColumnSet().getColumnByClass(RankColumn.class);
		}

		@Order(1000)
		public class RankColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Rank");
			}

			@Override
			protected int getConfiguredWidth() {
				return 50;
			}
		}

		@Order(2000)
		public class ClanIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("MyNlsKey");
			}

			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(3000)
		public class ClanNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Name");
			}

			@Override
			protected int getConfiguredWidth() {
				return 200;
			}
		}

		@Order(4000)
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

		@Order(5000)
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
		@ClassId("2e83c39d-3aa5-4c06-9bbd-72cf2d6bfe5c")
		public class ShowClanMenu extends AbstractShowClanMenu {

			@Override
			protected String getClanId() {
				return getClanIdColumn().getSelectedValue();
			}
		}
	}
}
