package ch.maak.wz.communitylevels.communitylevels.client.util;

import ch.maak.wz.communitylevels.communitylevels.client.ClientSession;
import ch.maak.wz.communitylevels.communitylevels.shared.Icons;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.action.menu.TableMenuType;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;

import java.util.Set;

public abstract class AbstractShowPlayerMenu extends AbstractMenu {

  protected abstract String getPlayerId();

  @Override
  protected String getConfiguredText() {
    return TEXTS.get("ShowPlayer");
  }

  @Override
  protected String getConfiguredIconId() {
    return Icons.Info;
  }

  @Override
  protected Set<? extends IMenuType> getConfiguredMenuTypes() {
    return CollectionUtility.hashSet(TableMenuType.SingleSelection);
  }

  @Override
  protected void execAction() {
    ClientSession.get().getDesktop().openUri("https://www.warzone.com/Profile?p=" + getPlayerId(), OpenUriAction.NEW_WINDOW);
  }
}
