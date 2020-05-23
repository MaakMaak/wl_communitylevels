package ch.maak.wl.communitylevels.communitylevels.client.level;

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

import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.ResetButton;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.CreatorField;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.LevelNameField;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.RecordHolderField;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.WinRateBox;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.WinRateBox.MaxWinRateField;
import ch.maak.wl.communitylevels.communitylevels.client.level.LevelSearchForm.MainBox.StandardBox.SearchBox.WinRateBox.MinWinRateField;
import ch.maak.wl.communitylevels.communitylevels.shared.level.LevelSearchFormData;

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

	public MinWinRateField getMinWinRateField() {
		return getFieldByClass(MinWinRateField.class);
	}

	public MaxWinRateField getMaxWinRateField() {
		return getFieldByClass(MaxWinRateField.class);
	}

	public CreatorField getCreatorField() {
		return getFieldByClass(CreatorField.class);
	}

	public StandardBox getStandardBox() {
		return getFieldByClass(StandardBox.class);
	}

	public SearchBox getSearchBox() {
		return getFieldByClass(SearchBox.class);
	}

	public RecordHolderField getRecordHolderField() {
		return getFieldByClass(RecordHolderField.class);
	}

	public ResetButton getResetButton() {
		return getFieldByClass(ResetButton.class);
	}

	public WinRateBox getWinRateBox() {
		return getFieldByClass(WinRateBox.class);
	}

	public LevelNameField getLevelNameField() {
		return getFieldByClass(LevelNameField.class);
	}

	@Order(10.0)
	public class MainBox extends AbstractGroupBox {

		@Order(0)
		public class StandardBox extends AbstractTabBox {

			@Order(1000)
			public class SearchBox extends AbstractGroupBox {

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