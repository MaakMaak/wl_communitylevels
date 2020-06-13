package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Replace;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.AbstractIcons;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.MostDifficultLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.MostDifficultLevelTablePageParam;

@ClassId("81ce2baa-b4fc-4de9-8095-2c1fd33b9eb9")
@PageData(MostDifficultLevelTablePageData.class)
public class MostDifficultLevelTablePage extends AbstractLevelTablePage {

	public MostDifficultLevelTablePage() {
		super(new MostDifficultLevelTablePageParam());
	}

	public MostDifficultLevelTablePage(MostDifficultLevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("MostDifficultLevels");
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return AbstractIcons.LightbulbOn;
	}

	@Override
	protected void execInitPage() {
		m_param.setMaxWinRate(10L);
	}

	@Override
	protected boolean getConfiguredSearchRequired() {
		return false;
	}

	@Override
	protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
		return BEANS.get(ILevelPageService.class).getLevelTableData(formData, m_param, new MostDifficultLevelTablePageData());
	}

	public class Table extends AbstractLevelTablePage.Table {

		@Replace
		public class WinRateColumn extends AbstractLevelTablePage.Table.WinRateColumn {
			@Override
			protected int getConfiguredSortIndex() {
				return 0;
			}

		}
	}

}
