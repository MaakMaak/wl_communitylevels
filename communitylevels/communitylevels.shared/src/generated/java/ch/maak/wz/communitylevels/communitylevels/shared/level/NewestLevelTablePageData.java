package ch.maak.wz.communitylevels.communitylevels.shared.level;

import org.eclipse.scout.rt.platform.classid.ClassId;
import org.eclipse.scout.rt.shared.data.basic.table.AbstractTableRowData;

import javax.annotation.Generated;

/**
 * <b>NOTE:</b><br>
 * This class is auto generated by the Scout SDK. No manual modifications recommended.
 */
@ClassId("0cbe475a-71c1-443b-8314-d64c8065fcaf-formdata")
@Generated(value = "ch.maak.wz.communitylevels.communitylevels.client.level.NewestLevelTablePage", comments = "This class is auto generated by the Scout SDK. No manual modifications recommended.")
public class NewestLevelTablePageData extends AbstractLevelTablePageData {
    private static final long serialVersionUID = 1L;

    @Override
    public NewestLevelTableRowData addRow() {
        return (NewestLevelTableRowData) super.addRow();
    }

    @Override
    public NewestLevelTableRowData addRow(int rowState) {
        return (NewestLevelTableRowData) super.addRow(rowState);
    }

    @Override
    public NewestLevelTableRowData createRow() {
        return new NewestLevelTableRowData();
    }

    @Override
    public Class<? extends AbstractTableRowData> getRowType() {
        return NewestLevelTableRowData.class;
    }

    @Override
    public NewestLevelTableRowData[] getRows() {
        return (NewestLevelTableRowData[]) super.getRows();
    }

    @Override
    public NewestLevelTableRowData rowAt(int index) {
        return (NewestLevelTableRowData) super.rowAt(index);
    }

    public void setRows(NewestLevelTableRowData[] rows) {
        super.setRows(rows);
    }

    public static class NewestLevelTableRowData extends AbstractLevelTableRowData {
        private static final long serialVersionUID = 1L;
    }
}
