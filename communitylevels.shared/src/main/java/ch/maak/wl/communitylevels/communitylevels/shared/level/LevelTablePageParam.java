package ch.maak.wl.communitylevels.communitylevels.shared.level;

import java.io.Serializable;
import java.util.Date;

public class LevelTablePageParam implements Serializable {
	private static final long serialVersionUID = 1L;

	String m_recordHolderId;
	String m_recordHolderName;
	String m_creatorId;
	String m_creatorName;
	Long m_maxWinRate;
	Date m_minCreatedDate;

	public String getRecordHolderId() {
		return m_recordHolderId;
	}

	public void setRecordHolderId(String recordHolderId) {
		m_recordHolderId = recordHolderId;
	}

	public String getRecordHolderName() {
		return m_recordHolderName;
	}

	public void setRecordHolderName(String recordHolderName) {
		m_recordHolderName = recordHolderName;
	}

	public String getCreatorId() {
		return m_creatorId;
	}

	public void setCreatorId(String creatorId) {
		m_creatorId = creatorId;
	}

	public String getCreatorName() {
		return m_creatorName;
	}

	public void setCreatorName(String creatorName) {
		m_creatorName = creatorName;
	}

	public Long getMaxWinRate() {
		return m_maxWinRate;
	}

	public void setMaxWinRate(Long maxWinRate) {
		m_maxWinRate = maxWinRate;
	}

	public Date getMinCreatedDate() {
		return m_minCreatedDate;
	}

	public void setMinCreatedDate(Date minCreatedDate) {
		m_minCreatedDate = minCreatedDate;
	}

}
