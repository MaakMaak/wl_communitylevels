package ch.maak.wz.communitylevels.communitylevels.server.records;

import ch.maak.wz.communitylevels.communitylevels.server.AbstractDataService;
import ch.maak.wz.communitylevels.communitylevels.shared.records.IStatisticsService;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticCodeType;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticsFormData;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticsFormData.MostRecordsOnDate.MostRecordsOnDateRowData;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticsFormData.LongestTimespan.LongestTimespanRowData;
import org.eclipse.scout.rt.platform.holders.BeanArrayHolder;
import org.eclipse.scout.rt.platform.holders.IBeanArrayHolder.State;
import org.eclipse.scout.rt.platform.holders.NVPair;
import org.eclipse.scout.rt.platform.util.StringUtility;
import org.eclipse.scout.rt.server.jdbc.SQL;

public class StatisticsService extends AbstractDataService implements IStatisticsService {

  @Override
  public StatisticsFormData prepareCreate(StatisticsFormData formData) {
    return formData;
  }

  @Override
  public StatisticsFormData create(StatisticsFormData formData) {
    return formData;
  }

  @Override
  public StatisticsFormData load(StatisticsFormData formData) {
    formData.getMostRecordsOnDate().setRows(getMostRecordsOnDateRowData());
    formData.getLongestTimespan().setRows(getLongestTimespanData());
    return formData;
  }

  @Override
  public StatisticsFormData store(StatisticsFormData formData) {
    return formData;
  }

  public MostRecordsOnDateRowData[] getMostRecordsOnDateRowData() {
    StringBuilder statement = new StringBuilder();
    statement.append(
      "SELECT CAST((@curRank := @curRank + 1) as CHAR(10))," +
        "C.* FROM( select MAX(RECORD_HOLDER_NAME), COUNT(ID) , MAX(EVT_RECORD)" +
        "from COMMUNITY_LEVELS WHERE RECORD_HOLDER_ID IS NOT NULL GROUP BY EVT_RECORD, RECORD_HOLDER_ID ORDER BY COUNT(ID) DESC , EVT_RECORD) C , (SELECT @curRank := 0) R ;");
    StringBuilder binds = new StringBuilder();
    binds.append(" INTO ");
    binds.append(addPageBind(MostRecordsOnDateRowData.rank));
    binds.append(addPageBind(MostRecordsOnDateRowData.player));
    binds.append(addPageBind(MostRecordsOnDateRowData.records));
    binds.append(addPageBindLast(MostRecordsOnDateRowData.evtRecord));
    statement.append(binds.toString());

    BeanArrayHolder<MostRecordsOnDateRowData> holder = new BeanArrayHolder<MostRecordsOnDateRowData>(MostRecordsOnDateRowData.class);
    SQL.selectIntoLimited(statement.toString(), 15, new NVPair("page", holder));

    return holder.getBeans((State[]) null);
  }

  public LongestTimespanRowData[] getLongestTimespanData() {
    StringBuilder statement = new StringBuilder();
    statement.append(
      "SELECT CAST((@curRank := @curRank + 1) as CHAR(10)), " +
        "C.* FROM( SELECT RECORD_HOLDER_NAME , ABS(DATEDIFF(EVT_CREATED, EVT_RECORD)) , NAME, ID " +
        "FROM COMMUNITY_LEVELS WHERE 1 = 1 ORDER BY ABS(DATEDIFF(EVT_CREATED, EVT_RECORD)) DESC , EVT_RECORD) C , (SELECT @curRank := 0) R ;");
    StringBuilder binds = new StringBuilder();
    binds.append(" INTO ");
    binds.append(addPageBind(LongestTimespanRowData.rank));
    binds.append(addPageBind(LongestTimespanRowData.player));
    binds.append(addPageBind(LongestTimespanRowData.days));
    binds.append(addPageBind(LongestTimespanRowData.level));
    binds.append(addPageBindLast(LongestTimespanRowData.levelId));
    statement.append(binds);

    BeanArrayHolder<LongestTimespanRowData> holder = new BeanArrayHolder<LongestTimespanRowData>(LongestTimespanRowData.class);
    SQL.selectIntoLimited(statement.toString(), 15, new NVPair("page", holder));

    return holder.getBeans((State[]) null);
  }

  @Override
  public String getStatistic(String id) {
    Object value = null;
    if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalAttemptsCode.ID)) {
      value = SQL.select("SELECT SUM(ATTEMPTS) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalCreatorsCode.ID)) {
      value = SQL.select("SELECT COUNT(DISTINCT CREATOR_ID) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalLevelsCode.ID)) {
      value = SQL.select("SELECT COUNT(ID) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalLikesCode.ID)) {
      value = SQL.select("SELECT SUM(LIKES) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalRecordHoldersCode.ID)) {
      value = SQL.select("SELECT COUNT(DISTINCT RECORD_HOLDER_ID) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalUnbeatenLevelsCode.ID)) {
      value = SQL.select("SELECT COUNT(ID) FROM COMMUNITY_LEVELS WHERE WINS = 0 AND ATTEMPTS > 50")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.TotalWinsCode.ID)) {
      value = SQL.select("SELECT SUM(WINS) FROM COMMUNITY_LEVELS")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.LongestLevelCode.ID)) {
      value = SQL.select("SELECT MAX(RECORD_TURNS) FROM COMMUNITY_LEVELS GROUP BY ID ORDER BY MAX(RECORD_TURNS) DESC, EVT_CREATED ASC")[0][0];
    } else if (StringUtility.equalsIgnoreCase(id, StatisticCodeType.LongestLevelIdCode.ID)) {
      value = SQL.select("SELECT ID, MAX(RECORD_TURNS) FROM COMMUNITY_LEVELS GROUP BY ID ORDER BY MAX(RECORD_TURNS) DESC, EVT_CREATED ASC")[0][0];
    }
    return (StringUtility.valueOf(value));
  }
}
