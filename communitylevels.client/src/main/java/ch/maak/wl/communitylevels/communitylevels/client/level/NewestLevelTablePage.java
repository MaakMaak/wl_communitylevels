package ch.maak.wl.communitylevels.communitylevels.client.level;

import java.util.Date;

import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.date.DateUtility;

import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelTablePageParam;

public class NewestLevelTablePage extends AllLevelTablePage {

	public NewestLevelTablePage() {
		super();
	}

	public NewestLevelTablePage(LevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("NewestLevels");
	}

	@Override
	protected void execInitPage() {
		m_param.setMinCreatedDate(DateUtility.addDays(new Date(), -15));
	}

	@Override
	protected boolean getConfiguredSearchRequired() {
		return false;
	}

	public class Table extends AllLevelTablePage.Table {

		@Replace
		public class EvtCreatedColumn extends AllLevelTablePage.Table.EvtCreatedColumn {
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
		public class LevelIdColumn extends AllLevelTablePage.Table.LevelIdColumn {
			@Override
			protected int getConfiguredSortIndex() {
				return 1;
			}

			@Override
			protected boolean getConfiguredSortAscending() {
				return false;
			}

		}
	}

}
