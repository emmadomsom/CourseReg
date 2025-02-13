
package CourseReg;
import javax.swing.table.AbstractTableModel;


public class CourseTableModel extends AbstractTableModel{
    private final String[] columnNames = {"Course Code", "Course Title", "Unit Load", "Select"};
    private final Object[][] data = {
        {"CS101", "Introduction to Computer Science", 3, Boolean.FALSE},
        {"CS102", "Data Structures", 4, Boolean.FALSE},
        {"CS103", "Algorithms", 3, Boolean.FALSE},
        {"CS104", "Operating Systems", 3, Boolean.FALSE}
    };

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if (columnIndex == 3) { // Column with checkboxes
            return Boolean.class;
        }
        return Object.class;
    }
}

