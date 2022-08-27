package ch.maak.wz.communitylevels.communitylevels.client.level;

import ch.maak.wz.communitylevels.communitylevels.shared.level.LevelSearchFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.dto.FormData.SdkCommand;
import org.eclipse.scout.rt.client.ui.desktop.outline.pages.AbstractSearchForm;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractResetButton;
import org.eclipse.scout.rt.client.ui.form.fields.button.AbstractSearchButton;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.longfield.AbstractLongField;
import org.eclipse.scout.rt.client.ui.form.fields.sequencebox.AbstractSequenceBox;
import org.eclipse.scout.rt.client.ui.form.fields.stringfield.AbstractStringField;
import org.eclipse.scout.rt.client.ui.form.fields.tabbox.AbstractTabBox;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.exception.ProcessingException;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.shared.services.common.jdbc.SearchFilter;


@FormData(value = LevelSearchFormData.class, sdkCommand = SdkCommand.CREATE)
public class LevelSearchForm extends AbstractSearchForm {

  @Override
  protected String getConfiguredTitle() {
    return TEXTS.get("Level");
  }

  public LevelSearchForm() throws ProcessingException {
    super();
  }

  @Override
  protected void execResetSearchFilter(SearchFilter searchFilter) throws ProcessingException {
    super.execResetSearchFilter(searchFilter);
    LevelSearchFormData formData = new LevelSearchFormData();
    exportFormData(formData);
    searchFilter.setFormData(formData);
  }

  public MainBox.StandardBox.SearchBox.WinRateBox.MinWinRateField getMinWinRateField() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.WinRateBox.MinWinRateField.class);
  }

  public MainBox.StandardBox.SearchBox.WinRateBox.MaxWinRateField getMaxWinRateField() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.WinRateBox.MaxWinRateField.class);
  }

  public MainBox.StandardBox.SearchBox.CreatorField getCreatorField() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.CreatorField.class);
  }

  public MainBox.StandardBox getStandardBox() {
    return getFieldByClass(MainBox.StandardBox.class);
  }

  public MainBox.StandardBox.SearchBox getSearchBox() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.class);
  }

  public MainBox.StandardBox.SearchBox.RecordHolderField getRecordHolderField() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.RecordHolderField.class);
  }

  public MainBox.ResetButton getResetButton() {
    return getFieldByClass(MainBox.ResetButton.class);
  }

  public MainBox.StandardBox.SearchBox.WinRateBox getWinRateBox() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.WinRateBox.class);
  }

  public MainBox.StandardBox.SearchBox.LevelNameField getLevelNameField() {
    return getFieldByClass(MainBox.StandardBox.SearchBox.LevelNameField.class);
  }

  @Order(10.0)
  public class MainBox extends AbstractGroupBox {

    @Order(0)
    public class StandardBox extends AbstractTabBox {

      @Order(1000)
      public class SearchBox extends AbstractGroupBox {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("SearchCriteria");
        }

        @Order(1000)
        public class LevelNameField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("LevelName");
          }

          @Override
          protected int getConfiguredMaxLength() {
            return 128;
          }
        }

        @Order(1750)
        public class WinRateBox extends AbstractSequenceBox {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("WinRate") + " (%)";
          }

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Order(1500)
          public class MinWinRateField extends AbstractLongField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Min");
            }

            @Override
            protected Long getConfiguredMinValue() {
              return 0L;
            }

            @Override
            protected byte getConfiguredLabelHorizontalAlignment() {
              return LABEL_HORIZONTAL_ALIGNMENT_LEFT;
            }

            @Override
            protected byte getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

          }

          @Order(1750)
          public class MaxWinRateField extends AbstractLongField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Max");
            }

            @Override
            protected Long getConfiguredMinValue() {
              return 0L;
            }

            @Override
            protected byte getConfiguredLabelHorizontalAlignment() {
              return LABEL_HORIZONTAL_ALIGNMENT_LEFT;
            }

            @Override
            protected byte getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

          }
        }

        @Order(1500)
        public class CreatorField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Creator");
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("CanBeProfileIdOrName");
          }

          @Override
          protected int getConfiguredMaxLength() {
            return 128;
          }
        }

        @Order(1000)
        public class RecordHolderField extends AbstractStringField {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("RecordHolder");
          }

          @Override
          protected int getConfiguredMaxLength() {
            return 128;
          }

          @Override
          protected String getConfiguredTooltipText() {
            return TEXTS.get("CanBeProfileIdOrName");
          }

        }

        @Order(2000)
        public class TurnsBox extends AbstractSequenceBox {
          @Override
          protected String getConfiguredLabel() {
            return TEXTS.get("Turns");
          }

          @Override
          protected boolean getConfiguredAutoCheckFromTo() {
            return false;
          }

          @Order(1500)
          public class MinTurnsField extends AbstractLongField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Min");
            }

            @Override
            protected Long getConfiguredMinValue() {
              return 0L;
            }

            @Override
            protected byte getConfiguredLabelHorizontalAlignment() {
              return LABEL_HORIZONTAL_ALIGNMENT_LEFT;
            }

            @Override
            protected byte getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

          }

          @Order(1750)
          public class MaxTurnsField extends AbstractLongField {
            @Override
            protected String getConfiguredLabel() {
              return TEXTS.get("Max");
            }

            @Override
            protected Long getConfiguredMinValue() {
              return 0L;
            }

            @Override
            protected byte getConfiguredLabelHorizontalAlignment() {
              return LABEL_HORIZONTAL_ALIGNMENT_LEFT;
            }

            @Override
            protected byte getConfiguredLabelPosition() {
              return LABEL_POSITION_ON_FIELD;
            }

          }
        }

      }

    }

    @Order(30.0)
    public class SearchButton extends AbstractSearchButton {
    }

    @Order(2000)
    public class ResetButton extends AbstractResetButton {
    }
  }
}
