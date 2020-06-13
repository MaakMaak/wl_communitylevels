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

import ch.maak.wl.communitylevels.communitylevels.server.AbstractDataService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageData;
import ch.maak.wl.communitylevels.communitylevels.shared.level.AbstractLevelTablePageParam;
import ch.maak.wl.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

@Bean
public class LevelPageService extends AbstractDataService implements ILevelPageService {

	@Override
	public AbstractLevelTablePageData getLevelTableData(LevelSearchFormData formData, AbstractLevelTablePageParam param, AbstractLevelTablePageData pageData)
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
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelNr));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.attempts));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.wins));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.creatorName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.creatorId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.winRate));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderClanName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderClanId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordTurns));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.likes));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.evtCreated));
		binds.append(addPageBindLast(AbstractLevelTablePageData.AbstractLevelTableRowData.evtRecord));
		statement.append(binds.toString());

		SQL.selectInto(statement.toString(), formData, new NVPair("page", pageData), new NVPair("param", param));

		return pageData;
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

	@Override
	public AbstractLevelTablePageData getCampaignLevelTableData(LevelSearchFormData formData, AbstractLevelTablePageParam param, AbstractLevelTablePageData pageData) {
		StringBuilder statement = new StringBuilder();
		statement.append(
				"SELECT LEVEL_NR, NAME, ATTEMPTS, WINS, ID, WIN_RATE, RECORD_HOLDER_CLAN_NAME,"
						+ " RECORD_HOLDER_CLAN_ID, RECORD_HOLDER_NAME, RECORD_TURNS, RECORD_HOLDER_ID, LIKES, EVT_RECORD "
						+ "FROM SINGLE_PLAYER_LEVELS WHERE 1=1 ");
		StringBuilder binds = new StringBuilder();
		binds.append(" INTO ");
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelNr));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.attempts));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.wins));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.levelId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.winRate));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderClanName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderClanId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderName));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordTurns));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.recordHolderId));
		binds.append(addPageBind(AbstractLevelTablePageData.AbstractLevelTableRowData.likes));
		binds.append(addPageBindLast(AbstractLevelTablePageData.AbstractLevelTableRowData.evtRecord));
		statement.append(binds.toString());

		SQL.selectInto(statement.toString(), formData, new NVPair("page", pageData), new NVPair("param", param));

		return pageData;
	}
}
