package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.ISearchForm;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.CampaignLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.CampaignLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

@PageData(CampaignLevelTablePageData.class)
public class CampaignLevelTablePage extends AbstractLevelTablePage {

	public CampaignLevelTablePage() {
		super(new CampaignLevelTablePageParam());
	}

	@Override
	protected boolean getConfiguredSearchRequired() {
		return false;
	}

	@Override
	protected Class<? extends ISearchForm> getConfiguredSearchForm() {
		return null;
	}

	public CampaignLevelTablePage(CampaignLevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return AbstractIcons.World;
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Campaign");
	}

	@Override
	protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
		return BEANS.get(ILevelPageService.class).getCampaignLevelTableData(formData, m_param, new CampaignLevelTablePageData());
	}

	@Replace
	public class Table extends AbstractLevelTablePage.Table {

		public CustomCreatorIdColumn getCustomCreatorIdColumn() {
			return getColumnSet().getColumnByClass(CustomCreatorIdColumn.class);
		}

		public CustomEvtCreatedColumn getCustomEvtCreatedColumn() {
			return getColumnSet().getColumnByClass(CustomEvtCreatedColumn.class);
		}

		public CustomCreatorNameColumn getCustomCreatorNameColumn() {
			return getColumnSet().getColumnByClass(CustomCreatorNameColumn.class);
		}

		@Replace
		public class LevelNrColumn extends AbstractLevelTablePage.Table.LevelNrColumn {
			@Override
			protected int getConfiguredSortIndex() {
				return 0;
			}

			@Override
			protected boolean getConfiguredSortAscending() {
				return false;
			}
		}

		@Replace
		public class CustomCreatorNameColumn extends AbstractLevelTablePage.Table.CreatorNameColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Replace
		public class CustomEvtCreatedColumn extends AbstractLevelTablePage.Table.EvtCreatedColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}

		@Replace
		public class CustomCreatorIdColumn extends AbstractLevelTablePage.Table.CreatorIdColumn {
			@Override
			protected boolean getConfiguredDisplayable() {
				return false;
			}
		}
	}
}
