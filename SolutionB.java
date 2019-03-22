public class SolutionB {
    public static String solution(int N, String S, String T) {
        boolean[][] theMap = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                theMap[i][j] = false;
            }
        }
        String[] hits = T.split(" ");
        for (String hit : hits) {
            int x = Integer.parseInt(hit.substring(0, hit.length() - 1));
            int y = hit.charAt(hit.length() - 1) - 'A';
            theMap[x - 1][y] = true;
        }
        String[] ships = S.split(",");
        int sunkCount = 0;
        int hitCount = 0;
        for (String ship : ships) {
            String[] xys = ship.split(" ");
            int topLeftX = Integer.parseInt(xys[0].substring(0, xys[0].length() - 1));
            int topLeftY = xys[0].charAt(xys[0].length() - 1) - 'A';
            int bottomRightX = Integer.parseInt(xys[1].substring(0, xys[0].length() - 1));
            int bottomRightY = xys[1].charAt(xys[0].length() - 1) - 'A';
            int count = 0;
            int size = ((bottomRightX - topLeftX) + 1) * ((bottomRightY - topLeftY) + 1);
            for (int i = topLeftX; i <= bottomRightX; i++) {
                for (int j = topLeftY; j <= bottomRightY; j++) {
                    if (theMap[i - 1][j]) {
                        count++;
                    }
                }
            }
            if (count > 0 && count < size) {
                hitCount++;
            } else if (count == size) {
                sunkCount++;
            }
        }

        return sunkCount + "," + hitCount;
    }


    public static void main(String[] args) {
        System.out.println(solution(12, "1A 2A,12A 12A", "12A"));
        System.out.println(solution(4, "1B 2C,2D 4D", "2B 2D 3D 4D 4A"));
    }
}