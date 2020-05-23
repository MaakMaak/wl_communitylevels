package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.text.TEXTS;

import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelTablePageParam;

public class MostDifficultLevelTablePage extends AllLevelTablePage {

	public MostDifficultLevelTablePage() {
		super();
	}

	public MostDifficultLevelTablePage(LevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("MostDifficultLevels");
	}

	@Override
	protected void execInitPage() {
		m_param.setMaxWinRate(20L);
	}

	@Override
	protected boolean getConfiguredSearchRequired() {
		return false;
	}

	public class Table extends AllLevelTablePage.Table {

		@Replace
		public class WinRateColumn extends AllLevelTablePage.Table.WinRateColumn {
			@Override
			protected int getConfiguredSortIndex() {
				return 0;
			}

		}
	}

}
