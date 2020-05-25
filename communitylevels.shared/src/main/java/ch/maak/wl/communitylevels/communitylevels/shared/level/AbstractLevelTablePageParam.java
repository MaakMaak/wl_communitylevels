package ch.maak.wl.communitylevels.communitylevels.shared.level;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractLevelTablePageParam implements Serializable {
	private static final long serialVersionUID = 1L;

	String m_recordHolderId;
	String m_creatorId;
	Long m_maxWinRate;
	Date m_minCreatedDate;

	public String getRecordHolderId() {
		return m_recordHolderId;
	}

	public void setRecordHolderId(String recordHolderId) {
		m_recordHolderId = recordHolderId;
	}

	public String getCreatorId() {
		return m_creatorId;
	}

	public void setCreatorId(String creatorId) {
		m_creatorId = creatorId;
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

	public boolean isSearchRequired() {
		return !(m_creatorId != null || m_recordHolderId != null);
	}
}
