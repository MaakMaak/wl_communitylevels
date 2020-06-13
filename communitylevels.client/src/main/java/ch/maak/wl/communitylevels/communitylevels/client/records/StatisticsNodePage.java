package ch.maak.wl.communitylevels.communitylevels.client.records;

import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractPageWithNodes;
import org.eclipse.scout.rt.client.ui.form.IForm;
import org.eclipse.scout.rt.platform.text.TEXTS;

import ch.maak.wl.communitylevels.communitylevels.shared.Icons;

public class StatisticsNodePage extends AbstractPageWithNodes {

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("Overview");
	}

	@Override
	protected boolean getConfiguredLeaf() {
		return true;
	}

	@Override
	protected Class<? extends IForm> getConfiguredDetailForm() {
		return StatisticsForm.class;
	}

	@Override
	protected boolean getConfiguredTableVisible() {
		return false;
	}

	@Override
	protected String getConfiguredOverviewIconId() {
		return Icons.GraphBold;
	}
}
