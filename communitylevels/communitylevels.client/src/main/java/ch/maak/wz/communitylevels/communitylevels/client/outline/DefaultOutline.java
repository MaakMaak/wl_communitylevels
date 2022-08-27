package ch.maak.wz.communitylevels.communitylevels.client.outline;

import ch.maak.wz.communitylevels.communitylevels.client.admin.UpdateLogTablePage;
import ch.maak.wz.communitylevels.communitylevels.client.clan.ClanTablePage;
import ch.maak.wz.communitylevels.communitylevels.client.level.*;
import ch.maak.wz.communitylevels.communitylevels.client.player.CreatorTablePage;
import ch.maak.wz.communitylevels.communitylevels.client.player.PlayerTablePage;
import ch.maak.wz.communitylevels.communitylevels.client.records.StatisticsNodePage;
import ch.maak.wz.communitylevels.communitylevels.client.util.SessionUtility;
import ch.maak.wz.communitylevels.communitylevels.shared.Icons;
import ch.maak.wz.communitylevels.communitylevels.shared.clotauth.WarzoneUserPrincipal;
import ch.maak.wz.communitylevels.communitylevels.shared.level.CreatorLevelTablePageParam;
import ch.maak.wz.communitylevels.communitylevels.shared.level.RecordHolderLevelTablePageParam;
import org.eclipse.scout.rt.client.ui.desktop.outline.AbstractOutline;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.IPage;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;


@Order(1000)
public class DefaultOutline extends AbstractOutline {
  public static Logger LOG = LoggerFactory.getLogger(DefaultOutline.class);

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("ApplicationTitle");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Star;
  }

  @Override
  protected void execCreateChildPages(List<IPage<?>> pageList) {
    super.execCreateChildPages(pageList);
    pageList.add(new StatisticsNodePage());
    pageList.add(new NewestLevelTablePage());
    pageList.add(new MostDifficultLevelTablePage());
    pageList.add(new AllLevelTablePage());
    pageList.add(new ClanTablePage());
    pageList.add(new PlayerTablePage());
    pageList.add(new CreatorTablePage());
    pageList.add(new CampaignLevelTablePage());

    Optional<WarzoneUserPrincipal> principal = SessionUtility.getWarzoneUserPrincipal();
    if (principal.isPresent()) {
      LOG.info("User {}", principal.get().getName());
      LOG.info("Id {}", principal.get().getUserId());

      CreatorLevelTablePageParam creatorpageParam = (CreatorLevelTablePageParam) BEANS.get(CreatorLevelTablePageParam.class).withCreatorId(principal.get().getUserId());
      pageList.add(new CreatorLevelTablePage(creatorpageParam));

      RecordHolderLevelTablePageParam recordHolderPageParam = (RecordHolderLevelTablePageParam) BEANS.get(RecordHolderLevelTablePageParam.class).withRecordHolderId(principal.get().getUserId());
      pageList.add(new RecordHolderLevelTablePage(recordHolderPageParam));

      if (StringUtility.equalsIgnoreCase("2211733141", principal.get().getUserId())) {
        pageList.add(new UpdateLogTablePage());
      }
    }
  }
}
