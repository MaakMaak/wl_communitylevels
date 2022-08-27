package ch.maak.wz.communitylevels.communitylevels.client;

import ch.maak.wz.communitylevels.communitylevels.shared.Icons;
import org.eclipse.scout.rt.client.ui.form.ScoutInfoForm;
import org.eclipse.scout.rt.platform.html.HTML;
import org.eclipse.scout.rt.platform.html.IHtmlElement;
import org.eclipse.scout.rt.platform.html.IHtmlTable;
import org.eclipse.scout.rt.platform.html.IHtmlTableRow;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;


public class CustomScoutInfoForm extends ScoutInfoForm {

  @Override
  protected IHtmlElement createLogoHtml() {
    return HTML.div(HTML.imgByIconId(Icons.AppLogo)
        .cssClass("scout-info-form-logo"))
      .cssClass("scout-info-form-logo-container");
  }

  @Override
  protected IHtmlTable createHtmlTable(Map<String, ?> properties) {
    List<IHtmlTableRow> rows = new ArrayList<>();
    for (Entry<String, ?> p : properties.entrySet()) {
      rows.add(createHtmlRow(p.getKey(), p.getValue()));
    }
    rows.add(HTML.tr(
      HTML.td(StringUtility.box("", TEXTS.get("CreatedBy"), ":")).cssClass("scout-info-form-table-cell-description"),
      HTML.td(HTML.link("https://www.warzone.com/Profile?p=2211733141", "Muli").addAttribute("target", "_blank")).cssClass("scout-info-form-table-cell-value")));
    return HTML.table(rows).cssClass("scout-info-form-table");
  }

  @Override
  protected IHtmlElement createTitleHtml() {
    setTitle(getProductName());
    return null;
  }
}
