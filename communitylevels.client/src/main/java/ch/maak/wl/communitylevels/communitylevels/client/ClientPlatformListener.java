package ch.maak.wl.communitylevels.communitylevels.client;

import java.util.concurrent.TimeUnit;

import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.IPlatform.State;
import org.eclipse.scout.rt.platform.IPlatformListener;
import org.eclipse.scout.rt.platform.PlatformEvent;
import org.eclipse.scout.rt.shared.cache.CacheBuilder;
import org.eclipse.scout.rt.shared.cache.CacheRegistryService;
import org.eclipse.scout.rt.shared.cache.ICache;
import org.eclipse.scout.rt.shared.cache.ICacheValueResolver;

import ch.maak.wl.communitylevels.communitylevels.shared.records.IStatisticsService;
import ch.maak.wl.communitylevels.communitylevels.shared.records.StatisticCodeType;

public class ClientPlatformListener implements IPlatformListener {

	@Override
	public void stateChanged(PlatformEvent event) {
		if (event.getState() == State.PlatformStarted) {
			ICache<String, String> statisticsCache = new CacheBuilder<String, String>()
					.withCacheId(StatisticCodeType.ID)
					.withTimeToLive(5L, TimeUnit.MINUTES, true)
					.withValueResolver(createCacheValueResolver())
					.build();
			BEANS.get(CacheRegistryService.class).register(statisticsCache);
		}

	}

	protected ICacheValueResolver<String, String> createCacheValueResolver() {
		return new ICacheValueResolver<String, String>() {

			@Override
			public String resolve(String key) {
				return BEANS.get(IStatisticsService.class).getStatistic(key);
			}
		};
	}

}
