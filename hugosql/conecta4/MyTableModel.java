package hugosql.conecta4;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Oracle, Inc.
 */
public class MyTableModel extends AbstractTableModel {
    private String[] columnNames = new String[0];
    private Object[][] data = new Object[0][0];

    public MyTableModel(Object[][] data,String[] columnNames) {
        super();
        this.data=data;
        this.columnNames=columnNames;
    }
    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    @Override
    public Object getValueAt(int row, int col) {
        return data[row][col];
    }

    /*
     * JTable uses this method to determine the default renderer/
     * editor for each cell.  If we didn't implement this method,
     * then the last column would contain text ("true"/"false"),
     * rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }
    @Override
    public void setValueAt(Object value, int row, int col) {
        data[row][col] = value;
        fireTableCellUpdated(row, col);
    }
    /*
     * Don't need to implement this method unless your table's
     * editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {
        //Note that the data/cell address is constant,
        //no matter where the cell appears onscreen.
        return col > 0;
    }


}
 
