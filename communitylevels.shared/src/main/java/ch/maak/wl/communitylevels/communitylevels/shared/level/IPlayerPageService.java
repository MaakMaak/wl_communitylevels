package ch.maak.wl.communitylevels.communitylevels.shared.level;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface IPlayerPageService extends IService {

	public PlayerTablePageData getTopPlayersTableData();
}
