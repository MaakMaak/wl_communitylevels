package ch.maak.wl.communitylevels.communitylevels.server.level;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.server.jdbc.SQL;

import ch.maak.wl.communitylevels.communitylevels.shared.level.CreatorTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ICreatorPageService;

@Bean
public class CreatorPageService implements ICreatorPageService {

	@Override
	public CreatorTablePageData getCreatorTableData() {
		StringBuilder statement = new StringBuilder();
		statement.append("SELECT CAST((@curRank := @curRank + 1) as CHAR(10)), "
				+ "C.* FROM( select CREATOR_NAME , CREATOR_ID , COUNT(CREATOR_ID) AS TOTAL_LEVELS , SUM(LIKES) AS TOTAL_LIKES , "
				+ "SUM(ATTEMPTS) AS TOTAL_ATTEMPTS , SUM(WINS) AS TOTAL_WINS , SUM(WINS) / SUM(ATTEMPTS) * 100 AS WIN_RATE , "
				+ "SUM(LIKES) / ( COUNT(LIKES) + 2) * 10 * CASE WHEN COUNT(LIKES) < 8 THEN 0.1 * COUNT(LIKES) + 0.2 ELSE 1 END + SUM(ATTEMPTS) / ( COUNT(LIKES) + 2 ) * 0.5 * CASE WHEN COUNT(LIKES) < 8 THEN 0.1 * COUNT(LIKES) + 0.2 ELSE 1 END + 350 * ( 1 - SUM(WINS) / SUM(ATTEMPTS) ) * CASE WHEN COUNT(LIKES) < 8 THEN 0.1 * COUNT(LIKES) + 0.2 ELSE 1 END AS POINTS "
				+ "from COMMUNITY_LEVELS WHERE 1 = 1 GROUP BY CREATOR_ID ORDER BY POINTS DESC , TOTAL_LIKES DESC , TOTAL_ATTEMPTS DESC ) C , (SELECT @curRank := 0) R ;");

		StringBuilder binds = new StringBuilder();

		binds.append(" INTO ");
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.rank));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.creatorName));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.creatorId));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.totalLevels));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.totalLikes));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.totalAttempts));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.totalWins));
		binds.append(addPageBind(CreatorTablePageData.CreatorTableRowData.winRate));
		binds.append(addPageBindLast(CreatorTablePageData.CreatorTableRowData.points));
		statement.append(binds.toString());

		CreatorTablePageData pageData = new CreatorTablePageData();
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
