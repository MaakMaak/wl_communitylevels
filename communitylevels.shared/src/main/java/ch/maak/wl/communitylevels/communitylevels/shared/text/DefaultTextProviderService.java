package ch.maak.wl.communitylevels.communitylevels.shared.text;

import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.AbstractDynamicNlsTextProviderService;

/**
 * @author Matthias
 */
@Order(-2000)
public class DefaultTextProviderService extends AbstractDynamicNlsTextProviderService {
	@Override
	public String getDynamicNlsBaseName() {
		return "ch.maak.wl.communitylevels.communitylevels.shared.texts.Texts";
	}
}
