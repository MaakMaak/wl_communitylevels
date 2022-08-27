package ch.maak.wz.communitylevels.communitylevels.shared.clan;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

@TunnelToServer
public interface IClanPageService extends IService {

  ClanTablePageData getClanTableData(SearchFilter filter);
}
