package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

@PageData(AllLevelTablePageData.class)
public class AllLevelTablePage extends AbstractLevelTablePage {

	public AllLevelTablePage() {
		super(new AllLevelTablePageParam());
	}

	@Override
	protected void initConfig() {
		// TODO Auto-generated method stub
		super.initConfig();
	}

	@Override
	public void initPage() {
		super.initPage();
		setSearchRequired(getPageParam().isSearchRequired());
	}

	public AllLevelTablePage(AllLevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("AllLevels");
	}

	@Override
	protected AbstractLevelTablePageData getTablePageData(LevelSearchFormData formData) {
		return BEANS.get(ILevelPageService.class).getLevelTableData(formData, m_param, new AllLevelTablePageData());
	}

	public class Table extends AbstractLevelTablePage.Table {

	}
}
