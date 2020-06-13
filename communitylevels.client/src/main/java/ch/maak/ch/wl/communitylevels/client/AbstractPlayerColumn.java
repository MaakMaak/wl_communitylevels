package ch.maak.ch.wl.communitylevels.client;

import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.platform.text.TEXTS;

public class AbstractPlayerColumn extends AbstractStringColumn {
	@Override
	protected String getConfiguredHeaderText() {
		return TEXTS.get("Player");
	}

	@Override
	protected int getConfiguredWidth() {
		return 150;
	}
}
