package ch.maak.wl.communitylevels.communitylevels.shared.records;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IStatisticsService extends IService {

	StatisticsFormData prepareCreate(StatisticsFormData formData);

	StatisticsFormData create(StatisticsFormData formData);

	StatisticsFormData load(StatisticsFormData formData);

	StatisticsFormData store(StatisticsFormData formData);

	String getStatistic(String id);
}
