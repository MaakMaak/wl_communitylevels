package ch.maak.wz.communitylevels.communitylevels.client.records;

import ch.maak.wz.communitylevels.communitylevels.client.AbstractLevelColumn;
import ch.maak.wz.communitylevels.communitylevels.client.AbstractPlayerColumn;
import ch.maak.wz.communitylevels.communitylevels.client.AbstractRankColumn;
import ch.maak.wz.communitylevels.communitylevels.client.ClientSession;
import ch.maak.wz.communitylevels.communitylevels.client.level.AbstractShowLevelMenu;
import ch.maak.wz.communitylevels.communitylevels.shared.records.IStatisticsService;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticCodeType;
import ch.maak.wz.communitylevels.communitylevels.shared.records.StatisticsFormData;
import org.eclipse.scout.rt.client.dto.FormData;
import org.eclipse.scout.rt.client.ui.MouseButton;
import org.eclipse.scout.rt.client.ui.basic.table.AbstractTable;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractDateColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractIntegerColumn;
import org.eclipse.scout.rt.client.ui.basic.table.columns.AbstractStringColumn;
import org.eclipse.scout.rt.client.ui.desktop.OpenUriAction;
import org.eclipse.scout.rt.client.ui.form.AbstractForm;
import org.eclipse.scout.rt.client.ui.form.AbstractFormHandler;
import org.eclipse.scout.rt.client.ui.form.fields.groupbox.AbstractGroupBox;
import org.eclipse.scout.rt.client.ui.form.fields.tablefield.AbstractTableField;
import org.eclipse.scout.rt.client.ui.form.fields.tilefield.AbstractTileField;
import org.eclipse.scout.rt.client.ui.tile.AbstractTileGrid;
import org.eclipse.scout.rt.client.ui.tile.fields.AbstractHtmlFieldTile;
import org.eclipse.scout.rt.platform.BEANS;
import org.eclipse.scout.rt.platform.Order;
import org.eclipse.scout.rt.platform.cache.CacheRegistryService;
import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.platform.text.TEXTS;
import org.eclipse.scout.rt.platform.util.StringUtility;

@FormData(value = StatisticsFormData.class, sdkCommand = FormData.SdkCommand.CREATE)
public class StatisticsForm extends AbstractForm {

  @Override
  protected void execFormActivated() {
    super.execFormActivated();
    new ReadHandler().execLoad();
  }

  @Override
  protected int getConfiguredDisplayHint() {
    return DISPLAY_HINT_VIEW;
  }

  public void startModify() {
    startInternalExclusive(new ReadHandler());
  }

  public MainBox.RecordsBox.MostRecordsOnDateField getLongestRecordField() {
    return getFieldByClass(MainBox.RecordsBox.MostRecordsOnDateField.class);
  }

  public MainBox.RecordsBox.LongestTimespanField getLongestTimespanField() {
    return getFieldByClass(MainBox.RecordsBox.LongestTimespanField.class);
  }

  public MainBox.StatisticsBox.StatisticsTileField getStatisticsTileField() {
    return getFieldByClass(MainBox.StatisticsBox.StatisticsTileField.class);
  }

  public MainBox getMainBox() {
    return getFieldByClass(MainBox.class);
  }

  @Order(1000)
  public class MainBox extends AbstractGroupBox {

    @Order(0)
    public class StatisticsBox extends AbstractGroupBox {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Statistics");
      }

      public class StatisticsTileField extends AbstractTileField<StatisticsTileField.StatisticsTileGrid> {
        @Override
        protected boolean getConfiguredLabelVisible() {
          return false;
        }

        @Override
        protected double getConfiguredGridWeightY() {
          return 1;
        }

        public class StatisticsTileGrid extends AbstractTileGrid<AbstractHtmlFieldTile> {

          @Order(100)
          @ClassId("8059459f-ac17-494b-8d6d-83e02a867e5e")
          public class TotalLevelsField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalLevels");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalLevelsCode.ID;
            }
          }

          @Order(200)
          @ClassId("37c725d5-c2d6-4704-a2b9-5e1832008f88")
          public class TotalAttemptsField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalAttempts");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalAttemptsCode.ID;
            }
          }

          @Order(300)
          @ClassId("a33c3365-8fa3-45d6-b938-010e4c94ece4")
          public class TotalWinsField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalWins");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalWinsCode.ID;
            }
          }

          @Order(400)
          @ClassId("19ded994-9f89-4a03-9d51-5929dd47ed9a")
          public class TotalLikesField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalLikes");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalLikesCode.ID;
            }
          }

          @Order(500)
          @ClassId("cc59d8e9-f8e1-4e25-b570-0ee0a5428335")
          public class TotalCreatorsField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalCreators");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalCreatorsCode.ID;
            }
          }

          @Order(600)
          @ClassId("9c25e1cc-2f9e-42c1-8406-0e8ecef1056c")
          public class TotalRecordHoldersField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("TotalRecordHolders");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalRecordHoldersCode.ID;
            }
          }

          @Order(700)
          @ClassId("d62072b6-4598-451c-9c90-7d1cefbd2964")
          public class UnbeatenLevelsField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("UnbeatenLevels");
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.TotalUnbeatenLevelsCode.ID;
            }
          }

          @Order(800)
          @ClassId("0efbd749-b94a-49ac-a1a9-81e4ff07abb1")
          public class LongestLevelField extends AbstractRecordFieldTile {

            @Override
            protected String getTitle() {
              return TEXTS.get("LongestLevel");
            }

            @Override
            protected String getValue() {
              return StringUtility.concatenateTokens(super.getValue(), " ", TEXTS.get("Turns"));
            }

            @Override
            protected String getStatisticId() {
              return StatisticCodeType.LongestLevelCode.ID;
            }

          }

          @Override
          protected void execTileClick(AbstractHtmlFieldTile tile, MouseButton mouseButton) {
            super.execTileClick(tile, mouseButton);
            if (tile instanceof LongestLevelField) {
              ClientSession.get().getDesktop().openUri(
                "https://www.warzone.com/SinglePlayer/Level?ID=" + BEANS.get(CacheRegistryService.class).get(StatisticCodeType.ID).get(StatisticCodeType.LongestLevelIdCode.ID),
                OpenUriAction.NEW_WINDOW);
            }
          }
        }
      }
    }

    @Order(500)
    public class RecordsBox extends AbstractGroupBox {
      @Override
      protected String getConfiguredLabel() {
        return TEXTS.get("Records");
      }

      @Override
      protected double getConfiguredGridWeightY() {
        return 0;
      }

      @Order(1000)
      public class MostRecordsOnDateField extends AbstractTableField<MostRecordsOnDateField.Table> {
        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("MostRecordsInADay");
        }

        @Override
        protected int getConfiguredHeightInPixel() {
          return 250;
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected double getConfiguredGridWeightX() {
          return 1;
        }

        public class Table extends AbstractTable {

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          public PlayerColumn getPlayerColumn() {
            return getColumnSet().getColumnByClass(PlayerColumn.class);
          }

          public RankColumn getRankColumn() {
            return getColumnSet().getColumnByClass(RankColumn.class);
          }

          @Order(1000)
          public class RankColumn extends AbstractRankColumn {

          }

          @Order(2000)
          public class PlayerColumn extends AbstractPlayerColumn {
          }

          @Order(3000)
          public class RecordsColumn extends AbstractStringColumn {
            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Records");
            }

            @Override
            protected int getConfiguredWidth() {
              return 60;
            }
          }

          @Order(4000)
          public class EvtRecordColumn extends AbstractDateColumn {

            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Date");
            }

            @Override
            protected int getConfiguredWidth() {
              return 150;
            }
          }
        }
      }

      @Order(2000)
      public class LongestTimespanField extends AbstractTableField<LongestTimespanField.Table> {

        @Override
        protected String getConfiguredLabel() {
          return TEXTS.get("LongestTimeSpanBetweenCreationAndRecordDate");
        }

        @Override
        protected byte getConfiguredLabelPosition() {
          return LABEL_POSITION_TOP;
        }

        @Override
        protected boolean getConfiguredEnabled() {
          return false;
        }

        @Override
        protected double getConfiguredGridWeightX() {
          return 1;
        }

        public class Table extends AbstractTable {

          @Override
          protected boolean getConfiguredAutoResizeColumns() {
            return true;
          }

          public DaysColumn getDaysColumn() {
            return getColumnSet().getColumnByClass(DaysColumn.class);
          }

          public LevelIdColumn getLevelIdColumn() {
            return getColumnSet().getColumnByClass(LevelIdColumn.class);
          }

          public PlayerColumn getPlayerColumn() {
            return getColumnSet().getColumnByClass(PlayerColumn.class);
          }

          public RankColumn getRankColumn() {
            return getColumnSet().getColumnByClass(RankColumn.class);
          }

          @Order(1000)
          @ClassId("08b4c0ac-29f7-4f54-93a4-fc50c6fbada3")
          public class RankColumn extends AbstractRankColumn {
          }

          @Order(1500)
          @ClassId("301d29ad-85f7-4b43-b383-5f1070380c44")
          public class PlayerColumn extends AbstractPlayerColumn {
          }

          @Order(2000)
          @ClassId("280d430b-98c3-4571-9aeb-a0abc51765fd")
          public class DaysColumn extends AbstractIntegerColumn {
            @Override
            protected String getConfiguredHeaderText() {
              return TEXTS.get("Days");
            }

            @Override
            protected int getConfiguredWidth() {
              return 50;
            }
          }

          @Order(4000)
          @ClassId("6359f81d-6ec3-4e9e-894b-688193346ed6")
          public class LevelColumn extends AbstractLevelColumn {

          }

          @Order(5000)
          @ClassId("f3363c92-8c20-4aad-a444-3f3b520f5727")
          public class LevelIdColumn extends AbstractStringColumn {

            @Override
            protected boolean getConfiguredDisplayable() {
              return false;
            }
          }

          @Order(1000)
          @ClassId("1f229605-d298-46b8-b17a-e047dce9fe07")
          public class ShowLevelMenu extends AbstractShowLevelMenu {

            @Override
            protected String getLevelId() {
              return getLevelIdColumn().getSelectedValue();
            }

            @Override
            protected boolean getConfiguredInheritAccessibility() {
              return false;
            }
          }
        }
      }
    }
  }

  public class ReadHandler extends AbstractFormHandler {

    @Override
    protected void execLoad() {
      IStatisticsService service = BEANS.get(IStatisticsService.class);
      StatisticsFormData formData = new StatisticsFormData();
      exportFormData(formData);
      formData = service.load(formData);
      importFormData(formData);
    }
  }
}
