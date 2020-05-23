package ch.maak.wl.communitylevels.communitylevels.server.level;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

import ch.maak.wl.communitylevels.communitylevels.shared.level.IPlayerPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.PlayerTablePageData;


@Bean
public class PlayerPageService implements IPlayerPageService {

	public PlayerTablePageData getTopPlayersTableData() throws ProcessingException {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CAST((@curRank := @curRank + 1) as CHAR(10)), C.* from ( "
				+ "SELECT `RECORD_HOLDER_ID`, `RECORD_HOLDER_NAME`, Count(RECORD_HOLDER_NAME), (1-AVG(WIN_RATE)/ 100)* count(LIKES) AS POINTS "
				+ "FROM `COMMUNITY_LEVELS` " + "WHERE ATTEMPTS > 50 " + "GROUP BY RECORD_HOLDER_ID "
				+ "ORDER BY POINTS DESC) C, ( SELECT @curRank := 0) R ;");

		StringBuilder binds = new StringBuilder();
		binds.append(" INTO ");
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.rank));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.playerId));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.playerName));
		binds.append(addPageBind(PlayerTablePageData.PlayerTableRowData.records));
		binds.append(addPageBindLast(PlayerTablePageData.PlayerTableRowData.points));
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
