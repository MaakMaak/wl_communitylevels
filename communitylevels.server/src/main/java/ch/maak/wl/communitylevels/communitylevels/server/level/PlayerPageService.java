package ch.maak.wl.communitylevels.communitylevels.server.level;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

import ch.maak.wl.communitylevels.communitylevels.shared.level.IPlayerPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.PlayerTablePageData;

@Bean
public class PlayerPageService implements IPlayerPageService {

	@Override
	public PlayerTablePageData getTopPlayersTableData() throws ProcessingException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CAST((@curRank := @curRank + 1) as CHAR(10)), "
				+ "C.* from( SELECT cl.RECORD_HOLDER_ID, cl.RECORD_HOLDER_NAME, "
				+ "Count(cl.RECORD_HOLDER_NAME) AS RECORDS, (1-AVG(cl.WIN_RATE)/ 100)* Count(cl.LIKES) AS POINTS, "
				+ "Count(cl_100d.RECORD_HOLDER_NAME) AS RECORDS_100d, "
				+ "COALESCE((1-AVG(cl_100d.WIN_RATE)/ 100)* Count(cl_100d.LIKES),0) AS POINTS_100d "
				+ "FROM COMMUNITY_LEVELS cl "
				+ "LEFT JOIN COMMUNITY_LEVELS cl_100d on cl.ID = cl_100d.ID "
				+ "AND cl_100d.EVT_RECORD > DATE_SUB(NOW(), INTERVAL 100 DAY)  and cl_100d.ATTEMPTS > 50 "
				+ "WHERE cl.ATTEMPTS > 50 GROUP BY cl.RECORD_HOLDER_ID "
				+ "ORDER BY POINTS DESC) C, ( SELECT @curRank := 0) R ;");

		StringBuilder binds = new StringBuilder();
		binds.append(" INTO ");
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.rank));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.playerId));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.playerName));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.records));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.points));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.records100d));
		binds.append(addPageBindLast(PlayerTablePageData.PlayerTableRowData.points100d));
		statement.append(binds.toString());

		PlayerTablePageData pageData = new PlayerTablePageData();
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
