package ch.maak.wl.communitylevels.communitylevels.shared.level;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ILevelPageService extends IService {

	AllLevelTablePageData getLevelTableData(LevelSearchFormData formData, LevelTablePageParam param);

	void loadLevels(List<String> levelIds);

}
