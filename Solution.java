// you can also use imports, for example:
// import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

import com.sun.org.apache.regexp.internal.CharacterArrayCharacterIterator;

import java.util.*;

class Solution {
    public static void main(String[] args) {
        System.out.println(solution(1, "1A 2A,12A 12A", "12A"));
        System.out.println(solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }

    static public String solution(int N, String S, String Tx) {
        // write your code in Java SE 8
        int injuryCounter = 0;
        int sunkCounter = 0;
        List<String> bombList = Arrays.asList(Tx.split(" "));
        List<String> strList = Arrays.asList(S.split(","));
        for (String st : strList) {
            String[] shipLoc = st.split(" ");
            Ship ship = new Ship(findShipCells(shipLoc));
            for (String bomb : bombList) {
                if (ship.getCells().contains(bomb)) {
                    List<String> newCells = ship.getCells();
                    newCells.remove(bomb);
                    ship.setCells(newCells);
                }
            }
            if (ship.isInjury()) injuryCounter++;
            if (ship.isSunk()) sunkCounter++;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(sunkCounter);
        sb.append(",");
        sb.append(injuryCounter);
        return sb.toString();
    }

    static private List<String> findShipCells(String[] shipLoc) {
        List<String> result = new ArrayList<>();
        int topLeftRow = Integer.parseInt(shipLoc[0].substring(0, shipLoc[0].length() - 1));
        int topLeftCol = shipLoc[0].charAt(shipLoc[0].length() - 1);
        int bottomRightRow = Integer.parseInt(shipLoc[1].substring(0, shipLoc[0].length() - 1));
        int bottomRightCol = shipLoc[1].charAt(shipLoc[0].length() - 1);

        for (int i = topLeftRow; i <= bottomRightRow; i++) {
            for (int j = topLeftCol; j <= bottomRightCol; j++) {
                StringBuilder sb = new StringBuilder();
                sb.append(i);
                sb.append((char) j);
                result.add(sb.toString());
            }
        }
        return result;
    }
}

class Ship {

    private int originCellsSize;
    private List<String> cells;

    public Ship(List<String> cells) {
        this.originCellsSize = cells.size(); //maybe have problem
        this.cells = cells;
    }

    public List<String> getCells() {
        return cells;
    }

    public void setCells(List<String> cells) {
        this.cells = cells;
    }

    public boolean isInjury() {
        if (originCellsSize != cells.size() && cells.size() != 0) return true;
        else return false;
    }

    public boolean isSunk() {
        if (cells.size() == 0) {
            return true;
        } else {
            return false;
        }
    }
}