package ch.maak.wz.communitylevels.communitylevels.client.level;

import ch.maak.wz.communitylevels.communitylevels.shared.level.ILevelPageService;
import ch.maak.wz.communitylevels.communitylevels.shared.level.LevelIdFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractCloseButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSaveButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.text.TEXTS;

import java.util.Arrays;
import java.util.List;


@FormData(value = LevelIdFormData.class, sdkCommand = SdkCommand.CREATE)
public class LevelIdForm extends AbstractForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("LevelId");
  }

  @Override
  public void start() {
    super.startInternal(new LoadLevelIdHandler());
  }

  public MainBox.CloseButton getCancelButton() {
    return getFieldByClass(MainBox.CloseButton.class);
  }

  public MainBox.SaveButton getSaveButton() {
    return getFieldByClass(MainBox.SaveButton.class);
  }

  public MainBox.LevelIdField getLevelIdField() {
    return getFieldByClass(MainBox.LevelIdField.class);
  }

  public class MainBox extends AbstractGroupBox {

    @Order(1000)
    public class LevelIdField extends AbstractStringField {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("LevelId");
      }

      @Override
      protected boolean getConfiguredMandatory() {
        return true;
      }
    }

    @Order(2000)
    public class SaveButton extends AbstractSaveButton {
    }

    @Order(3000)
    public class CloseButton extends AbstractCloseButton {
    }
  }

  public class LoadLevelIdHandler extends AbstractFormHandler {
    @Override
    protected void execLoad() {
    }

    @Override
    protected void execStore() {
      String levelId = getLevelIdField().getValue().replaceAll("\\D+", "");

      BEANS.get(ILevelPageService.class).loadLevels(List.of(levelId));
      doClose();
    }
  }
}
