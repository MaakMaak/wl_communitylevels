package ch.maak.wl.communitylevels.communitylevels.shared.admin;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IUpdateLogService extends IService {

	UpdateLogTablePageData getUpdateLogTableData(SearchFilter filter);
}
