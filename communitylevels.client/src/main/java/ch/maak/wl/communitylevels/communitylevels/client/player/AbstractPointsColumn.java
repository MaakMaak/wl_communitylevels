package ch.maak.wl.communitylevels.communitylevels.client.player;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractBigDecimalColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

public abstract class AbstractPointsColumn extends AbstractBigDecimalColumn {

	protected String getConfiguredHeaderSuffix() {
		return null;
	}

	@Override
	protected String getConfiguredBackgroundEffect() {
		return BackgroundEffect.BAR_CHART;
	}

	@Override
	protected String getConfiguredHeaderText() {
		return TEXTS.get("Points") + StringUtility.box(" (", getConfiguredHeaderSuffix(), ")");
	}
}
