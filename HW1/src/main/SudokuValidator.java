import java.util.HashSet;
import java.util.Scanner;

public class SudokuValidator {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        while (scanner.hasNext()) {
            int[][] grid = new int[9][9];

            // 讀取 9 行輸入，存入 grid
            for (int i = 0; i < 9; i++) {
                String line = scanner.next();
                for (int j = 0; j < 9; j++) {
                    grid[i][j] = line.charAt(j) - '0';
                }
            }

            // 驗證數獨是否合法
            boolean isValid = isValidSudoku(grid);
            System.out.printf("Case %d: %s.%n", caseNumber++, isValid ? "True" : "False");

            // 檢查是否還有下一個測試案例
            if (scanner.hasNext()) {
                scanner.nextLine(); // 讀取空行
            }
        }

        scanner.close();
    }

    private static boolean isValidSudoku(int[][] grid) {
        // 驗證每一列
        for (int i = 0; i < 9; i++) {
            if (!isValidSet(grid[i])) {
                return false;
            }
        }

        // 驗證每一行
        for (int j = 0; j < 9; j++) {
            int[] column = new int[9];
            for (int i = 0; i < 9; i++) {
                column[i] = grid[i][j];
            }
            if (!isValidSet(column)) {
                return false;
            }
        }

        // 驗證每個 3x3 子區塊
        for (int blockRow = 0; blockRow < 3; blockRow++) {
            for (int blockCol = 0; blockCol < 3; blockCol++) {
                int[] subGrid = new int[9];
                int index = 0;
                for (int i = blockRow * 3; i < blockRow * 3 + 3; i++) {
                    for (int j = blockCol * 3; j < blockCol * 3 + 3; j++) {
                        subGrid[index++] = grid[i][j];
                    }
                }
                if (!isValidSet(subGrid)) {
                    return false;
                }
            }
        }

        return true;
    }

    private static boolean isValidSet(int[] numbers) {
        HashSet<Integer> set = new HashSet<>();
        for (int num : numbers) {
            if (num < 1 || num > 9 || !set.add(num)) {
                return false;
            }
        }
        return true;
    }
}