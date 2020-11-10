package ch.maak.wl.communitylevels.communitylevels.client.player;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractLongColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

public abstract class AbstractRecordsColumn extends AbstractLongColumn {

	protected String getConfiguredHeaderSuffix() {
		return null;
	}

	@Override
	protected String getConfiguredHeaderText() {
		return TEXTS.get("Records") + StringUtility.box(" (", getConfiguredHeaderSuffix(), ")");
	}

	@Override
	protected int getConfiguredWidth() {
		return 150;
	}
}
