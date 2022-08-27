package ch.maak.wz.communitylevels.communitylevels.client;

import ch.maak.wz.communitylevels.communitylevels.shared.records.IStatisticsService;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticCodeType;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.platform.cache.CacheBuilder;
import org.eclipse.scout.rt.platform.cache.CacheRegistryService;
import org.eclipse.scout.rt.platform.cache.ICache;
import org.eclipse.scout.rt.platform.cache.ICacheValueResolver;

import java.util.concurrent.TimeUnit;

public class ClientPlatformListener implements IPlatformListener {

  @Override
  public void stateChanged(PlatformEvent event) {
    if (event.getState() == State.PlatformStarted) {
      ICache<String, String> statisticsCache = new CacheBuilder<String, String>()
        .withCacheId(StatisticCodeType.ID)
        .withTimeToLive(5L, TimeUnit.MINUTES, true)
        .withValueResolver(createCacheValueResolver())
        .build();
      BEANS.get(CacheRegistryService.class).registerIfAbsent(statisticsCache);
    }
  }

  protected ICacheValueResolver<String, String> createCacheValueResolver() {
    return key -> BEANS.get(IStatisticsService.class).getStatistic(key);
  }
}
