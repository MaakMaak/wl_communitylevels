package ch.maak.wl.communitylevels.communitylevels.client.level;

import java.util.Set;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.basic.cell.Cell;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.ITableRow;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.desktop.notification.DesktopNotification;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithTable;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.client.ui.tile.fields.AbstractTableFieldTile.TableField.Table;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.status.IStatus;
import org.eclipse.scout.rt.platform.status.Status;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

import ch.maak.wl.communitylevels.communitylevels.client.ClientSession;
import ch.maak.wl.communitylevels.communitylevels.client.util.AbstractShowPlayerMenu;
import ch.maak.wl.communitylevels.communitylevels.shared.Icons;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

@PageData(AbstractLevelTablePageData.class)
@ClassId("813b92e0-7cc8-435f-84a4-dbb490fca831")
public abstract class AbstractLevelTablePage extends AbstractPageWithTable<Table> {
	protected AbstractLevelTablePageParam m_param;

	public AbstractLevelTablePage(AbstractLevelTablePageParam param) {
		super();
		m_param = param;
	}

	@Override
	protected void initConfig() {
		super.initConfig();
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("AllLevels");
	}

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected void execLoadData(SearchFilter filter) throws ProcessingException {
		LevelSearchFormData formData = (LevelSearchFormData) filter.getFormData();
		if (formData == null) {
			formData = new LevelSearchFormData();
		}

		importPageData(getTablePageData(formData));
	}

	protected abstract AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData);

	protected AbstractLevelTablePageParam getPageParam() {
		return m_param;
	}

	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return LevelSearchForm.class;
	}

	@Order(10.0)
	public class Table extends AbstractTable {

		public AttemptsColumn getAttemptsColumn() {
			return getColumnSet().getColumnByClass(AttemptsColumn.class);
		}

		public LevelIdColumn getLevelIdColumn() {
			return getColumnSet().getColumnByClass(LevelIdColumn.class);
		}

		public CreatorNameColumn getCreatorNameColumn() {
			return getColumnSet().getColumnByClass(CreatorNameColumn.class);
		}

		public CreatorIdColumn getCreatedCreatorIdColumn() {
			return getColumnSet().getColumnByClass(CreatorIdColumn.class);
		}

		public WinRateColumn getWinPercentageColumn() {
			return getColumnSet().getColumnByClass(WinRateColumn.class);
		}

		public LikesColumn getLikesColumn() {
			return getColumnSet().getColumnByClass(LikesColumn.class);
		}

		public RecordHolderNameColumn getRecordHolderNameColumn() {
			return getColumnSet().getColumnByClass(RecordHolderNameColumn.class);
		}

		public RecordTurnsColumn getRecordTurnsColumn() {
			return getColumnSet().getColumnByClass(RecordTurnsColumn.class);
		}

		public EvtCreatedColumn getEvtCreatedColumn() {
			return getColumnSet().getColumnByClass(EvtCreatedColumn.class);
		}

		public EvtRecordColumn getEvtRecordColumn() {
			return getColumnSet().getColumnByClass(EvtRecordColumn.class);
		}

		public LevelNrColumn getLevelNrColumn() {
			return getColumnSet().getColumnByClass(LevelNrColumn.class);
		}

		public RecordHolderClanIdColumn getRecordHolderClanIdColumn() {
			return getColumnSet().getColumnByClass(RecordHolderClanIdColumn.class);
		}

		public RecordHolderClanNameColumn getRecordHolderClanNameColumn() {
			return getColumnSet().getColumnByClass(RecordHolderClanNameColumn.class);
		}

		public RecordHolderIdColumn getRecordHolderIdColumn() {
			return getColumnSet().getColumnByClass(RecordHolderIdColumn.class);
		}

		public WinsColumn getWinsColumn() {
			return getColumnSet().getColumnByClass(WinsColumn.class);
		}

		public LevelNameColumn getLevelNameColumn() {
			return getColumnSet().getColumnByClass(LevelNameColumn.class);
		}

		@Order(0)
		public class LevelNrColumn extends AbstractBigIntegerColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Order(1000)
		public class LevelNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("LevelName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 250;
			}

			@Override
			protected boolean getConfiguredSummary() {
				return true;
			}
		}

		@Order(2000)
		public class AttemptsColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Attempts");
			}

			@Override
			protected int getConfiguredWidth() {
				return 75;
			}

			@Override
			protected String getConfiguredBackgroundEffect() {
				return BackgroundEffect.BAR_CHART;
			}
		}

		@Order(3000)
		public class WinsColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Wins");
			}

			@Override
			protected int getConfiguredWidth() {
				return 75;
			}
		}

		@Order(4000)
		public class LevelIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("LevelId");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}
		}

		@Order(5000)
		public class CreatorNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Creator");
			}

			@Override
			protected int getConfiguredWidth() {
				return 140;
			}
		}

		@Order(6000)
		public class CreatorIdColumn extends AbstractStringColumn {

			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("CreatorId");
			}

			@Override
			protected int getConfiguredWidth() {
				return 200;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(7000)
		public class WinRateColumn extends AbstractBigDecimalColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("WinRate");
			}

			@Override
			protected int getConfiguredWidth() {
				return 70;
			}

			@Override
			protected String getConfiguredBackgroundEffect() {
				return BackgroundEffect.COLOR_GRADIENT_1;
			}

			@Override
			protected boolean getConfiguredPercent() {
				return true;
			}

		}

		@Order(8000)
		public class LikesColumn extends AbstractIntegerColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Likes");
			}

			@Override
			protected int getConfiguredWidth() {
				return 60;
			}
		}

		@Order(9000)
		public class RecordHolderIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RecordHolder");
			}

			@Override
			protected int getConfiguredWidth() {
				return 200;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(10000)
		public class RecordHolderNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RecordHolder");
			}

			@Override
			protected int getConfiguredWidth() {
				return 175;
			}
		}

		@Order(10500)
		public class RecordHolderClanNameColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RecordHolderClanName");
			}

			@Override
			protected int getConfiguredWidth() {
				return 175;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(10750)
		public class RecordHolderClanIdColumn extends AbstractStringColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RecordHolderClanId");
			}

			@Override
			protected int getConfiguredWidth() {
				return 175;
			}

			@Override
			protected void execDecorateCell(Cell cell, ITableRow row) {
				super.execDecorateCell(cell, row);
				if (cell.getValue() != null && cell.getValue().toString().equals("0")) {
					cell.setText("");
				}
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(11000)
		public class RecordTurnsColumn extends AbstractLongColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("Turns");
			}

			@Override
			protected int getConfiguredWidth() {
				return 100;
			}

			@Override
			protected void execDecorateCell(Cell cell, ITableRow row) {
				super.execDecorateCell(cell, row);
				if (cell.getValue() != null) {
					cell.setText(cell.getValue() + " turns");
				}
			}
		}

		@Order(12000)
		public class EvtCreatedColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("CreatedOn");
			}

			@Override
			protected int getConfiguredWidth() {
				return 115;
			}
		}

		@Order(13000)
		public class EvtRecordColumn extends AbstractDateColumn {
			@Override
			protected String getConfiguredHeaderText() {
				return TEXTS.get("RecordOn");
			}

			@Override
			protected int getConfiguredWidth() {
				return 115;
			}
		}

		@Order(0)
		public class AddLevelMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("AddLevel");
			}

			@Override
			protected String getConfiguredIconId() {
				return Icons.Plus;
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection, TableMenuType.MultiSelection,
						TableMenuType.EmptySpace);
			}

			@Override
			protected void execAction() {
				LevelIdForm form = new LevelIdForm();
				form.start();
				form.waitFor();
				if (form.isFormStored()) {
					reloadPage();
				}
			}
		}

		@Order(500)
		public class PlayLevelMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("PlayLevel");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}

			@Override
			protected String getConfiguredIconId() {
				return Icons.CaretRight;
			}

			@Override
			protected void execAction() {
				ClientSession.get().getDesktop().openUri(
						"https://www.warzone.com/SinglePlayer?Level=" + getLevelIdColumn().getSelectedValue(),
						OpenUriAction.NEW_WINDOW);

			}
		}

		@Order(1000)
		@ClassId("43a1bf37-68aa-4cef-b129-1460f60713fc")
		public class ShowLevelMenu extends AbstractShowLevelMenu {

			@Override
			protected String getLevelId() {
				return getLevelIdColumn().getSelectedValue();
			}
		}

		@Order(2000)
		@ClassId("d9469f3e-16c3-44ac-9d71-ff0d7d16396e")
		public class ShowRecordHolderMenu extends AbstractShowPlayerMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("ShowRecordHolder");
			}

			@Override
			protected String getPlayerId() {
				return getRecordHolderIdColumn().getSelectedValue();
			}
		}

		@Order(3000)
		@ClassId("4e6df8ef-a673-4852-804d-d08a4fea9ffd")
		public class ShowCreatorMenu extends AbstractShowPlayerMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("ShowCreator");
			}

			@Override
			protected String getPlayerId() {
				return getCreatedCreatorIdColumn().getSelectedValue();
			}
		}

		@Order(3500)
		@ClassId("dd13e4c6-bc2d-4f4b-99b2-416593148bf6")
		public class UpdateLevelMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("UpdateLevel");
			}

			@Override
			protected String getConfiguredIconId() {
				return Icons.Spinner;
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet(TableMenuType.SingleSelection);
			}

			@Override
			protected void execAction() {
				BEANS.get(ILevelPageService.class).loadLevels(getLevelIdColumn().getSelectedValues());
				reloadPage();
				DesktopNotification notification = new DesktopNotification(
						new Status(TEXTS.get("LevelUpdated"), IStatus.OK), 5000L, true);
				ClientSession.get().getDesktop().addNotification(notification);
			}
		}

	}

}