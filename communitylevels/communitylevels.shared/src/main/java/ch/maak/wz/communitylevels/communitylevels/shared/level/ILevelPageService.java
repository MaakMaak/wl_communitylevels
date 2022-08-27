package ch.maak.wz.communitylevels.communitylevels.shared.level;

import org.eclipse.scout.rt.platform.service.IService;
import org.eclipse.scout.rt.shared.TunnelToServer;

import java.util.List;

@TunnelToServer
public interface ILevelPageService extends IService {

  AbstractLevelTablePageData getLevelTableData(LevelSearchFormData formData, AbstractLevelTablePageParam param, AbstractLevelTablePageData pageData);

  AbstractLevelTablePageData getCampaignLevelTableData(LevelSearchFormData formData, AbstractLevelTablePageParam param, AbstractLevelTablePageData pageData);

  void loadLevels(List<String> levelIds);

}
