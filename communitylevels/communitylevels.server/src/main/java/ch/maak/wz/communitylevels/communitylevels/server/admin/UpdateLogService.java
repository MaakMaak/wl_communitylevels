package ch.maak.wz.communitylevels.communitylevels.server.admin;

import ch.maak.wz.communitylevels.communitylevels.server.AbstractDataService;
import ch.maak.wz.communitylevels.communitylevels.shared.admin.IUpdateLogService;
import ch.maak.wz.communitylevels.communitylevels.shared.admin.UpdateLogTablePageData;
import ch.maak.wz.communitylevels.communitylevels.shared.admin.UpdateLogTablePageData.UpdateLogTableRowData;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;

public class UpdateLogService extends AbstractDataService implements IUpdateLogService {

  @Override
  public UpdateLogTablePageData getUpdateLogTableData(SearchFilter filter) {
    StringBuilder statement = new StringBuilder();
    statement.append("select COUNT, EVT_START, EVT_END, LEVELS from COMMUNITY_LEVELS_LOG ORDER BY EVT_END DESC LIMIT 1000;");
    StringBuilder binds = new StringBuilder();
    binds.append(" INTO ");
    binds.append(addPageBind(UpdateLogTableRowData.count));
    binds.append(addPageBind(UpdateLogTableRowData.evtStart));
    binds.append(addPageBind(UpdateLogTableRowData.evtEnd));
    binds.append(addPageBindLast(UpdateLogTableRowData.levels));
    statement.append(binds.toString());

    UpdateLogTablePageData pageData = new UpdateLogTablePageData();
    SQL.selectInto(statement.toString(), new NVPair("page", pageData));

    return pageData;
  }
}
