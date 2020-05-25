package ch.maak.wl.communitylevels.communitylevels.shared.level;

import java.util.List;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

@TunnelToServer
public interface ILevelPageService extends IService {

	AbstractLevelTablePageData getLevelTableData(LevelSearchFormData formData, AbstractLevelTablePageParam param, AbstractLevelTablePageData pageData);

	void loadLevels(List<String> levelIds);

}
