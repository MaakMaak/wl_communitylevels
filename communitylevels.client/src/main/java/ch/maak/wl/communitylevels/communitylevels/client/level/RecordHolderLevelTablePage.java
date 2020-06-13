package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.RecordHolderLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.RecordHolderLevelTablePageParam;

@PageData(RecordHolderLevelTablePageData.class)
public class RecordHolderLevelTablePage extends AbstractLevelTablePage {

	public RecordHolderLevelTablePage() {
		super(new RecordHolderLevelTablePageParam());
	}

	public RecordHolderLevelTablePage(RecordHolderLevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("YourRecords");
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return AbstractIcons.PersonSolid;
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

		@Replace
		public class EvtRecordColumn extends AbstractLevelTablePage.Table.EvtRecordColumn {
			@Override
			protected boolean getConfiguredSortAscending() {
				return false;
			}

			@Override
			protected int getConfiguredSortIndex() {
				return 1;
			}
		}
	}
}
