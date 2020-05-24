package ch.maak.wl.communitylevels.communitylevels.client.work;

import java.util.List;

import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import ch.maak.wl.communitylevels.communitylevels.client.clan.ClanTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.level.AllLevelTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.level.MostDifficultLevelTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.level.NewestLevelTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.player.CreatorTablePage;
import ch.maak.wl.communitylevels.communitylevels.client.player.PlayerTablePage;
import ch.maak.wl.communitylevels.communitylevels.shared.Icons;

@Order(1000)
public class DefaultOutline extends AbstractOutline {

	@Override
	protected void execCreateChildPages(List<IPage<?>> pageList) {
		super.execCreateChildPages(pageList);
		pageList.add(new NewestLevelTablePage());
		pageList.add(new MostDifficultLevelTablePage());
		pageList.add(new AllLevelTablePage());
		pageList.add(new PlayerTablePage());
		pageList.add(new ClanTablePage());
		pageList.add(new CreatorTablePage());
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("CommunityLevels");
	}

	@Override
	protected String getConfiguredIconId() {
		return Icons.Star;
	}
}
