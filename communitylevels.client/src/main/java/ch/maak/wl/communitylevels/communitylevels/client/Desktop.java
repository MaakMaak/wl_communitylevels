package ch.maak.wl.communitylevels.communitylevels.client;

import java.beans.PropertyChangeEvent;
import java.security.AccessController;
import java.security.Principal;
import java.util.List;
import java.util.Set;

import javax.security.auth.Subject;

import org.eclipse.scout.rt.client.session.ClientSessionProvider;
import org.eclipse.scout.rt.client.ui.action.keystroke.IKeyStroke;
import org.eclipse.scout.rt.client.ui.action.menu.AbstractMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenu;
import org.eclipse.scout.rt.client.ui.action.menu.IMenuType;
import org.eclipse.scout.rt.client.ui.desktop.AbstractDesktop;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.desktop.outline.IOutline;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.CollectionUtility;
import org.eclipse.scout.rt.platform.util.StringUtility;

import ch.maak.wl.communitylevels.communitylevels.client.Desktop.UserProfileMenu.ThemeMenu.DarkThemeMenu;
import ch.maak.wl.communitylevels.communitylevels.client.Desktop.UserProfileMenu.ThemeMenu.DefaultThemeMenu;
import ch.maak.wl.communitylevels.communitylevels.client.outline.DefaultOutline;
import ch.maak.wl.communitylevels.communitylevels.client.util.SessionUtility;
import ch.maak.wl.communitylevels.communitylevels.shared.Icons;

public class Desktop extends AbstractDesktop {

	public Desktop() {
		addPropertyChangeListener(PROP_THEME, this::onThemeChanged);
	}

	@Override
	protected String getConfiguredTitle() {
		return TEXTS.get("ApplicationTitle");
	}

	@Override
	protected String getConfiguredLogoId() {
		return Icons.AppLogo;
	}

	@Override
	protected List<Class<? extends IOutline>> getConfiguredOutlines() {
		return CollectionUtility.<Class<? extends IOutline>>arrayList(DefaultOutline.class);
	}

	@Override
	protected void execDefaultView() {
		selectFirstVisibleOutline();
	}

	protected void selectFirstVisibleOutline() {
		for (IOutline outline : getAvailableOutlines()) {
			if (outline.isEnabled() && outline.isVisible()) {
				setOutline(outline.getClass());
				return;
			}
		}
	}

	protected void onThemeChanged(PropertyChangeEvent evt) {
		IMenu darkMenu = getMenuByClass(DarkThemeMenu.class);
		IMenu defaultMenu = getMenuByClass(DefaultThemeMenu.class);
		String newThemeName = (String) evt.getNewValue();
		if (DarkThemeMenu.DARK_THEME.equalsIgnoreCase(newThemeName)) {
			darkMenu.setIconId(Icons.CheckedBold);
			defaultMenu.setIconId(null);
		} else {
			darkMenu.setIconId(null);
			defaultMenu.setIconId(Icons.CheckedBold);
		}
	}

	@Order(1000)
	public class UserProfileMenu extends AbstractMenu {

		@Override
		protected String getConfiguredKeyStroke() {
			return IKeyStroke.F10;
		}

		@Override
		protected String getConfiguredIconId() {
			return Icons.PersonSolid;
		}

		@Override
		protected String getConfiguredText() {
			Subject subject = Subject.getSubject(AccessController.getContext());
			Principal firstPrincipal = CollectionUtility.firstElement(subject.getPrincipals());
			return StringUtility.uppercaseFirst(firstPrincipal.getName());
		}

		@Order(1000)
		public class AboutMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("About");
			}

			@Override
			protected void execAction() {
				ScoutInfoForm form = new ScoutInfoForm();
				form.startModify();
			}

			@Override
			protected boolean getConfiguredVisible() {
				return false;
			}
		}

		@Order(2000)
		public class ThemeMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Theme");
			}

			@Order(1000)
			public class DefaultThemeMenu extends AbstractMenu {

				private static final String DEFAULT_THEME = "Default";

				@Override
				protected String getConfiguredText() {
					return DEFAULT_THEME;
				}

				@Override
				protected void execAction() {
					setTheme(DEFAULT_THEME.toLowerCase());
				}
			}

			@Order(2000)
			public class DarkThemeMenu extends AbstractMenu {

				private static final String DARK_THEME = "Dark";

				@Override
				protected String getConfiguredText() {
					return DARK_THEME;
				}

				@Override
				protected void execAction() {
					setTheme(DARK_THEME.toLowerCase());
				}
			}
		}

		@Order(2500)
		public class LinkWarzoneAccountMenu extends AbstractMenu {
			@Override
			protected String getConfiguredText() {
				return TEXTS.get("LinkWarzoneAccount");
			}

			@Override
			protected Set<? extends IMenuType> getConfiguredMenuTypes() {
				return CollectionUtility.hashSet();
			}

			@Override
			protected boolean getConfiguredVisible() {
				return !SessionUtility.getWarzoneUserPrincipal().isPresent();
			}

			@Override
			protected void execAction() {
				ClientSession.get().getDesktop().openUri("https://www.Warzone.com/CLOT/Auth?p=2211733141", OpenUriAction.SAME_WINDOW);
			}
		}

		@Order(3000)
		public class LogoutMenu extends AbstractMenu {

			@Override
			protected String getConfiguredText() {
				return TEXTS.get("Logout");
			}

			@Override
			protected void execAction() {
				ClientSessionProvider.currentSession().stop();
			}
		}
	}
}
