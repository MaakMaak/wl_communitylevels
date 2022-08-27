package ch.maak.wz.communitylevels.communitylevels.shared.level;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("813b92e0-7cc8-435f-84a4-dbb490fca831-formdata")
@Generated(value = "ch.maak.wz.communitylevels.communitylevels.client.level.AbstractLevelTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public abstract class AbstractLevelTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public AbstractLevelTableRowData addRow() {
        return (AbstractLevelTableRowData) super.addRow();
    }

    @Override
    public AbstractLevelTableRowData addRow(int rowState) {
        return (AbstractLevelTableRowData) super.addRow(rowState);
    }

    @Override
    public abstract AbstractLevelTableRowData createRow();

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return AbstractLevelTableRowData.class;
    }

    @Override
    public AbstractLevelTableRowData[] getRows() {
        return (AbstractLevelTableRowData[]) super.getRows();
    }

    @Override
    public AbstractLevelTableRowData rowAt(int index) {
        return (AbstractLevelTableRowData) super.rowAt(index);
    }

    public void setRows(AbstractLevelTableRowData[] rows) {
        super.setRows(rows);
    }

    public abstract static class AbstractLevelTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String levelNr = "levelNr";
        public static final String levelName = "levelName";
        public static final String attempts = "attempts";
        public static final String wins = "wins";
        public static final String levelId = "levelId";
        public static final String creatorName = "creatorName";
        public static final String creatorId = "creatorId";
        public static final String winRate = "winRate";
        public static final String likes = "likes";
        public static final String recordHolderId = "recordHolderId";
        public static final String recordHolderName = "recordHolderName";
        public static final String recordHolderClanName = "recordHolderClanName";
        public static final String recordHolderClanId = "recordHolderClanId";
        public static final String recordTurns = "recordTurns";
        public static final String evtCreated = "evtCreated";
        public static final String evtRecord = "evtRecord";
        private BigInteger m_levelNr;
        private String m_levelName;
        private Integer m_attempts;
        private Integer m_wins;
        private String m_levelId;
        private String m_creatorName;
        private String m_creatorId;
        private BigDecimal m_winRate;
        private Integer m_likes;
        private String m_recordHolderId;
        private String m_recordHolderName;
        private String m_recordHolderClanName;
        private String m_recordHolderClanId;
        private Long m_recordTurns;
        private Date m_evtCreated;
        private Date m_evtRecord;

        public BigInteger getLevelNr() {
            return m_levelNr;
        }

        public void setLevelNr(BigInteger newLevelNr) {
            m_levelNr = newLevelNr;
        }

        public String getLevelName() {
            return m_levelName;
        }

        public void setLevelName(String newLevelName) {
            m_levelName = newLevelName;
        }

        public Integer getAttempts() {
            return m_attempts;
        }

        public void setAttempts(Integer newAttempts) {
            m_attempts = newAttempts;
        }

        public Integer getWins() {
            return m_wins;
        }

        public void setWins(Integer newWins) {
            m_wins = newWins;
        }

        public String getLevelId() {
            return m_levelId;
        }

        public void setLevelId(String newLevelId) {
            m_levelId = newLevelId;
        }

        public String getCreatorName() {
            return m_creatorName;
        }

        public void setCreatorName(String newCreatorName) {
            m_creatorName = newCreatorName;
        }

        public String getCreatorId() {
            return m_creatorId;
        }

        public void setCreatorId(String newCreatorId) {
            m_creatorId = newCreatorId;
        }

        public BigDecimal getWinRate() {
            return m_winRate;
        }

        public void setWinRate(BigDecimal newWinRate) {
            m_winRate = newWinRate;
        }

        public Integer getLikes() {
            return m_likes;
        }

        public void setLikes(Integer newLikes) {
            m_likes = newLikes;
        }

        public String getRecordHolderId() {
            return m_recordHolderId;
        }

        public void setRecordHolderId(String newRecordHolderId) {
            m_recordHolderId = newRecordHolderId;
        }

        public String getRecordHolderName() {
            return m_recordHolderName;
        }

        public void setRecordHolderName(String newRecordHolderName) {
            m_recordHolderName = newRecordHolderName;
        }

        public String getRecordHolderClanName() {
            return m_recordHolderClanName;
        }

        public void setRecordHolderClanName(String newRecordHolderClanName) {
            m_recordHolderClanName = newRecordHolderClanName;
        }

        public String getRecordHolderClanId() {
            return m_recordHolderClanId;
        }

        public void setRecordHolderClanId(String newRecordHolderClanId) {
            m_recordHolderClanId = newRecordHolderClanId;
        }

        public Long getRecordTurns() {
            return m_recordTurns;
        }

        public void setRecordTurns(Long newRecordTurns) {
            m_recordTurns = newRecordTurns;
        }

        public Date getEvtCreated() {
            return m_evtCreated;
        }

        public void setEvtCreated(Date newEvtCreated) {
            m_evtCreated = newEvtCreated;
        }

        public Date getEvtRecord() {
            return m_evtRecord;
        }

        public void setEvtRecord(Date newEvtRecord) {
            m_evtRecord = newEvtRecord;
        }
    }
}
