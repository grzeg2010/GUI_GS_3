package Data;

import javax.swing.table.AbstractTableModel;

public class MovesTableModel extends AbstractTableModel {
    private String[] columns = {
            "l.p.", "Gracz", "Sekcja", "Pole"
    };

    MovesHistoryData movesHistoryData;

    public MovesTableModel() {
        movesHistoryData = new MovesHistoryData();
    }

    @Override
    public int getRowCount() {
        return movesHistoryData.getSize();
    }

    @Override
    public int getColumnCount() {
        return 4;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0 -> {
                return rowIndex;
            }
            case 1 -> {
                return movesHistoryData.getPlayer(rowIndex);
            }
            case 2 -> {
                return movesHistoryData.getSection(rowIndex);
            }
            case 3 -> {
                return movesHistoryData.getField(rowIndex);
            }
            default -> {
                return null;
            }
        }
    }

    @Override
    public String getColumnName(int col) {
        return columns[col];
    }

    public void newMove(MovesHistoryData.Player player, int section, int field) {
        MovesHistoryData.FieldName sectionEnum, fieldEnum;

        switch (section) {
            case 0 -> { sectionEnum=Data.MovesHistoryData.FieldName.LG; }
            case 1 -> { sectionEnum=Data.MovesHistoryData.FieldName.SG; }
            case 2 -> { sectionEnum=Data.MovesHistoryData.FieldName.PG; }
            case 3 -> { sectionEnum=Data.MovesHistoryData.FieldName.LS; }
            case 4 -> { sectionEnum=Data.MovesHistoryData.FieldName.SS; }
            case 5 -> { sectionEnum=Data.MovesHistoryData.FieldName.PS; }
            case 6 -> { sectionEnum=Data.MovesHistoryData.FieldName.LD; }
            case 7 -> { sectionEnum=Data.MovesHistoryData.FieldName.SD; }
            case 8 -> { sectionEnum=Data.MovesHistoryData.FieldName.PD; }
            default -> { sectionEnum=null; }
        }

        switch (field) {
            case 0 -> { fieldEnum=Data.MovesHistoryData.FieldName.LG; }
            case 1 -> { fieldEnum=Data.MovesHistoryData.FieldName.SG; }
            case 2 -> { fieldEnum=Data.MovesHistoryData.FieldName.PG; }
            case 3 -> { fieldEnum=Data.MovesHistoryData.FieldName.LS; }
            case 4 -> { fieldEnum=Data.MovesHistoryData.FieldName.SS; }
            case 5 -> { fieldEnum=Data.MovesHistoryData.FieldName.PS; }
            case 6 -> { fieldEnum=Data.MovesHistoryData.FieldName.LD; }
            case 7 -> { fieldEnum=Data.MovesHistoryData.FieldName.SD; }
            case 8 -> { fieldEnum=Data.MovesHistoryData.FieldName.PD; }
            default -> { fieldEnum=null; }
        }

        movesHistoryData.addMove(player, sectionEnum, fieldEnum);
        fireTableDataChanged();
    }
}

