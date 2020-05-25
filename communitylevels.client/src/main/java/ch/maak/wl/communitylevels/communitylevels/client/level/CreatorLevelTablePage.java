package ch.maak.wl.communitylevels.communitylevels.client.level;

import org.eclipse.scout.rt.client.dto.PageData;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.text.TEXTS;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.CreatorLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.CreatorLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

@PageData(CreatorLevelTablePageData.class)
public class CreatorLevelTablePage extends AbstractLevelTablePage {

	public CreatorLevelTablePage() {
		super(new CreatorLevelTablePageParam());
	}

	public CreatorLevelTablePage(CreatorLevelTablePageParam param) {
		super(param);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("YourLevels");
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

	}
}