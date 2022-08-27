package ch.maak.wz.communitylevels.communitylevels.server.clan;

import ch.maak.wz.communitylevels.communitylevels.shared.clan.ClanTablePageData;
import ch.maak.wz.communitylevels.communitylevels.shared.clan.IClanPageService;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;


public class ClanPageService implements IClanPageService {

  @Override
  public ClanTablePageData getClanTableData(SearchFilter filter) {
    StringBuilder statement = new StringBuilder();
    statement.append("SELECT CAST((@curRank := @curRank + 1) as CHAR(10)), C.* from( "
      + "SELECT `RECORD_HOLDER_CLAN_ID`, `RECORD_HOLDER_CLAN_NAME`, Count(RECORD_HOLDER_CLAN_NAME), "
      + "(1-AVG(WIN_RATE)/ 100)* count(LIKES) AS POINTS FROM `COMMUNITY_LEVELS` "
      + "WHERE RECORD_HOLDER_CLAN_ID IS NOT NULL AND ATTEMPTS > 50 GROUP BY RECORD_HOLDER_CLAN_ID ORDER BY POINTS DESC) C, "
      + "( SELECT @curRank := 0) R ;");

    StringBuilder binds = new StringBuilder();
    binds.append(" INTO ");
    binds.append(addPageBind(ClanTablePageData.ClanTableRowData.rank));
    binds.append(addPageBind(ClanTablePageData.ClanTableRowData.clanId));
    binds.append(addPageBind(ClanTablePageData.ClanTableRowData.clanName));
    binds.append(addPageBind(ClanTablePageData.ClanTableRowData.records));
    binds.append(addPageBindLast(ClanTablePageData.ClanTableRowData.points));
    statement.append(binds.toString());

    ClanTablePageData pageData = new ClanTablePageData();
    System.out.println(SQL.createPlainText(statement.toString(), new NVPair("page", pageData)));
    SQL.selectInto(statement.toString(), new NVPair("page", pageData));

    return pageData;
  }

  private String addPageBind(String column) {
    return ":{page." + column + "}, ";
  }

  private String addPageBindLast(String column) {
    return ":{page." + column + "} ";
  }
}
