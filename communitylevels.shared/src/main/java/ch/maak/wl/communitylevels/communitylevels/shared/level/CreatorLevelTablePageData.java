package ch.maak.wl.communitylevels.communitylevels.shared.level;

import javax.annotation.Generated;

import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications
 * recommended.
 */
@Generated(value = "ch.maak.wl.communitylevels.communitylevels.client.level.CreatorLevelTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class CreatorLevelTablePageData extends AbstractLevelTablePageData {

	private static final long serialVersionUID = 1L;

	@Override
	public CreatorLevelTableRowData addRow() {
		return (CreatorLevelTableRowData) super.addRow();
	}

	@Override
	public CreatorLevelTableRowData addRow(int rowState) {
		return (CreatorLevelTableRowData) super.addRow(rowState);
	}

	@Override
	public CreatorLevelTableRowData createRow() {
		return new CreatorLevelTableRowData();
	}

	@Override
	public Class<? extends AbstractTableRowData> getRowType() {
		return CreatorLevelTableRowData.class;
	}

	@Override
	public CreatorLevelTableRowData[] getRows() {
		return (CreatorLevelTableRowData[]) super.getRows();
	}

	@Override
	public CreatorLevelTableRowData rowAt(int index) {
		return (CreatorLevelTableRowData) super.rowAt(index);
	}

	public void setRows(CreatorLevelTableRowData[] rows) {
		super.setRows(rows);
	}

	public static class CreatorLevelTableRowData extends AbstractLevelTableRowData {

		private static final long serialVersionUID = 1L;
	}
}
