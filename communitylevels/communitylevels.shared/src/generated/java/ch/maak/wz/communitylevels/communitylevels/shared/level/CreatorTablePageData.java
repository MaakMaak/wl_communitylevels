package ch.maak.wz.communitylevels.communitylevels.shared.level;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;
import org.eclipse.scout.rt.shared.data.page.AbstractTablePageData;

import javax.annotation.Generated;
import java.math.BigDecimal;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("d762e81b-5f0c-4d75-b9eb-a95d43dfec53-formdata")
@Generated(value = "ch.maak.wz.communitylevels.communitylevels.client.player.CreatorTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class CreatorTablePageData extends AbstractTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public CreatorTableRowData addRow() {
        return (CreatorTableRowData) super.addRow();
    }

    @Override
    public CreatorTableRowData addRow(int rowState) {
        return (CreatorTableRowData) super.addRow(rowState);
    }

    @Override
    public CreatorTableRowData createRow() {
        return new CreatorTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return CreatorTableRowData.class;
    }

    @Override
    public CreatorTableRowData[] getRows() {
        return (CreatorTableRowData[]) super.getRows();
    }

    @Override
    public CreatorTableRowData rowAt(int index) {
        return (CreatorTableRowData) super.rowAt(index);
    }

    public void setRows(CreatorTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class CreatorTableRowData extends AbstractTableRowData {
        private static final long serialVersionUID = 1L;
        public static final String rank = "rank";
        public static final String creatorId = "creatorId";
        public static final String creatorName = "creatorName";
        public static final String totalAttempts = "totalAttempts";
        public static final String totalLikes = "totalLikes";
        public static final String totalWins = "totalWins";
        public static final String winRate = "winRate";
        public static final String totalLevels = "totalLevels";
        public static final String points = "points";
        public static final String pointsLikes = "pointsLikes";
        public static final String pointsAttempts = "pointsAttempts";
        public static final String pointsDifficulty = "pointsDifficulty";
        private Integer m_rank;
        private String m_creatorId;
        private String m_creatorName;
        private Integer m_totalAttempts;
        private Integer m_totalLikes;
        private Integer m_totalWins;
        private BigDecimal m_winRate;
        private Integer m_totalLevels;
        private BigDecimal m_points;
        private BigDecimal m_pointsLikes;
        private BigDecimal m_pointsAttempts;
        private BigDecimal m_pointsDifficulty;

        public Integer getRank() {
            return m_rank;
        }

        public void setRank(Integer newRank) {
            m_rank = newRank;
        }

        public String getCreatorId() {
            return m_creatorId;
        }

        public void setCreatorId(String newCreatorId) {
            m_creatorId = newCreatorId;
        }

        public String getCreatorName() {
            return m_creatorName;
        }

        public void setCreatorName(String newCreatorName) {
            m_creatorName = newCreatorName;
        }

        public Integer getTotalAttempts() {
            return m_totalAttempts;
        }

        public void setTotalAttempts(Integer newTotalAttempts) {
            m_totalAttempts = newTotalAttempts;
        }

        public Integer getTotalLikes() {
            return m_totalLikes;
        }

        public void setTotalLikes(Integer newTotalLikes) {
            m_totalLikes = newTotalLikes;
        }

        public Integer getTotalWins() {
            return m_totalWins;
        }

        public void setTotalWins(Integer newTotalWins) {
            m_totalWins = newTotalWins;
        }

        public BigDecimal getWinRate() {
            return m_winRate;
        }

        public void setWinRate(BigDecimal newWinRate) {
            m_winRate = newWinRate;
        }

        public Integer getTotalLevels() {
            return m_totalLevels;
        }

        public void setTotalLevels(Integer newTotalLevels) {
            m_totalLevels = newTotalLevels;
        }

        public BigDecimal getPoints() {
            return m_points;
        }

        public void setPoints(BigDecimal newPoints) {
            m_points = newPoints;
        }

        public BigDecimal getPointsLikes() {
            return m_pointsLikes;
        }

        public void setPointsLikes(BigDecimal newPointsLikes) {
            m_pointsLikes = newPointsLikes;
        }

        public BigDecimal getPointsAttempts() {
            return m_pointsAttempts;
        }

        public void setPointsAttempts(BigDecimal newPointsAttempts) {
            m_pointsAttempts = newPointsAttempts;
        }

        public BigDecimal getPointsDifficulty() {
            return m_pointsDifficulty;
        }

        public void setPointsDifficulty(BigDecimal newPointsDifficulty) {
            m_pointsDifficulty = newPointsDifficulty;
        }
    }
}
