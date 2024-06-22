package Data;

import java.util.ArrayList;

public class MovesHistoryData {
    public enum Player {
        o, x, Draw
    }

    public enum FieldName {
        LG, SG, PG,
        LS, SS, PS,
        LD, SD, PD
    }
    private ArrayList<Player> playerHistory;
    private ArrayList<FieldName> sectionHistory;
    private ArrayList<FieldName> fieldHistory;

    public MovesHistoryData() {
        playerHistory = new ArrayList<>();
        sectionHistory = new ArrayList<>();
        fieldHistory = new ArrayList<>();
    }

    public void addMove(Player player, FieldName section, FieldName field) {
        playerHistory.add(player);
        sectionHistory.add(section);
        fieldHistory.add(field);
    }

    // GETTERS
    public Enum<Player> getPlayer(int index) { return playerHistory.get(index); }
    public Enum<FieldName> getSection(int index) { return sectionHistory.get(index); }
    public Enum<FieldName> getField(int index) { return fieldHistory.get(index); }
    public int getSize() { return playerHistory.size(); }
}
