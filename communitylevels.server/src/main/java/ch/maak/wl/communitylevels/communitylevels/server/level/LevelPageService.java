package ch.maak.wl.communitylevels.communitylevels.server.level;

import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.and;
import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.eq;
import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.ge;
import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.le;
import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.like;
import static ch.maak.wl.communitylevels.communitylevels.server.util.QL.or;

import java.util.List;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import org.eclipse.scout.rt.platform.Bean;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.exception.VetoException;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

import ch.maak.wl.communitylevels.communitylevels.shared.level.AllLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelTablePageParam;

@Bean
public class LevelPageService implements ILevelPageService {

	@Override
	public AllLevelTablePageData getLevelTableData(LevelSearchFormData formData, LevelTablePageParam param)
			throws ProcessingException {
		StringBuilder statement = new StringBuilder();
		statement.append(
				"SELECT LEVEL_NR, NAME, ATTEMPTS, WINS, ID, CREATOR_NAME, CREATOR_ID, WIN_RATE, RECORD_HOLDER_CLAN_NAME,"
						+ " RECORD_HOLDER_CLAN_ID, RECORD_HOLDER_NAME, RECORD_TURNS, RECORD_HOLDER_ID, LIKES, EVT_CREATED, EVT_RECORD "
						+ "FROM COMMUNITY_LEVELS WHERE 1=1 ");

		if (!StringUtility.isNullOrEmpty(formData.getLevelName().getValue())) {
			statement.append(and(like("NAME", ":levelName")));
		}
		if (formData.getMinWinRate().getValue() != null) {
			statement.append(and(ge("WIN_RATE", ":minWinRate")));
		}
		if (formData.getMaxWinRate().getValue() != null) {
			statement.append(and(le("WIN_RATE", ":maxWinRate")));
		}
		if (formData.getMinTurns().getValue() != null) {
			statement.append(and(ge("RECORD_TURNS", ":minTurns")));
		}
		if (formData.getMaxTurns().getValue() != null) {
			statement.append(and(le("RECORD_TURNS", ":maxTurns")));
		}
		if (!StringUtility.isNullOrEmpty(formData.getCreator().getValue())) {
			statement.append(and(or(like("CREATOR_NAME", ":creator"), eq("CREATOR_ID", ":creator"))));

		}
		if (!StringUtility.isNullOrEmpty(formData.getRecordHolder().getValue())) {
			statement.append(
					and(or(like("RECORD_HOLDER_NAME", ":recordHolder"), eq("RECORD_HOLDER_ID", ":recordHolder"))));
		}

		if (param.getRecordHolderId() != null) {
			statement.append(and(eq("RECORD_HOLDER_ID", ":{param.recordHolderId}")));
		}
		if (param.getCreatorId() != null) {
			statement.append(and(eq("CREATOR_ID", ":{param.creatorId}")));
		}
		if (param.getMaxWinRate() != null) {
			statement.append(and(le("WIN_RATE", ":{param.maxWinRate}")));
		}
		if (param.getMinCreatedDate() != null) {
			statement.append(and(ge("EVT_CREATED", ":{param.minCreatedDate}")));
		}

		StringBuilder binds = new StringBuilder();
		binds.append(" INTO ");
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.levelNr));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.levelName));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.attempts));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.wins));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.levelId));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.creatorName));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.creatorId));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.winRate));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.recordHolderClanName));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.recordHolderClanId));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.recordHolderName));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.recordTurns));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.recordHolderId));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.likes));
		binds.append(addPageBind(AllLevelTablePageData.AllLevelTableRowData.evtCreated));
		binds.append(addPageBindLast(AllLevelTablePageData.AllLevelTableRowData.evtRecord));
		statement.append(binds.toString());

		AllLevelTablePageData pageData = new AllLevelTablePageData();
		System.out.println(SQL.createPlainText(statement.toString(), formData, new NVPair("page", pageData),
				new NVPair("param", param)));

		SQL.selectInto(statement.toString(), formData, new NVPair("page", pageData), new NVPair("param", param));

		return pageData;
	}

	private String addPageBind(String column) {
		return ":{page." + column + "}, ";
	}

	private String addPageBindLast(String column) {
		return ":{page." + column + "} ";
	}

	@Override
	public void loadLevels(List<String> levelIds) {
		for (String levelId : levelIds) {
			Client client = ClientBuilder.newClient();
			WebTarget target = client.target("https://maak.ch/wl/v2/levelstats.php?id=" + levelId);
			Response response = target.request().get();
			if (response.getStatus() != 200) {
				throw new VetoException("Failed loading level with id " + levelId);
			}
		}
	}
}
