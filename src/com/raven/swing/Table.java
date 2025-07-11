package com.raven.swing;

import com.raven.model.StatusType;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

public class Table extends JTable {

    public Table() {
        setShowHorizontalLines(true);
        setGridColor(new Color(230, 230, 230));
        setRowHeight(40);
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader header = new TableHeader(o + "");
                if (i1 == 4) {
                    header.setHorizontalAlignment(JLabel.CENTER);
                }
                return header;
            }
        });
        setDefaultRenderer(Object.class, new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean selected, boolean hasFocus, int row, int column) {
                String colname = jtable.getColumnName(column);

                if (colname.equalsIgnoreCase("123123123")) {
                    if (o instanceof StatusType) {
                        return new CellStatus((StatusType) o);
                    } else if (o instanceof Integer) {
                        int intValue = (Integer) o;
                        return new CellStatus(StatusType.fromIndex(intValue));
                    } else {
                        // Nếu không xác định được kiểu thì render giống các ô bình thường
                        Component com = super.getTableCellRendererComponent(jtable, o, selected, hasFocus, row, column);
                        com.setBackground(Color.WHITE);
                        setBorder(noFocusBorder);
                        com.setForeground(selected ? new Color(15, 89, 140) : new Color(102, 102, 102));
                        return com;
                    }
                } else {
                    // Cột bình thường
                    Component com = super.getTableCellRendererComponent(jtable, o, selected, hasFocus, row, column);
                    com.setBackground(Color.WHITE);
                    setBorder(noFocusBorder);
                    com.setForeground(selected ? new Color(15, 89, 140) : new Color(102, 102, 102));
                    return com;
                }
            }
        });
    }

    public void addRow(Object[] row) {
        DefaultTableModel model = (DefaultTableModel) getModel();
        model.addRow(row);
    }
}
