import java.util.Arrays;
import java.util.Scanner;

public class CarFleet {
    public static int carFleet(int target, int[] position, int[] speed) {
        int n = position.length;
        if (n == 0) return 0;

        // 創建一個陣列存放車輛的位置和到達時間
        double[][] cars = new double[n][2];
        for (int i = 0; i < n; i++) {
            cars[i][0] = position[i];
            cars[i][1] = (double) (target - position[i]) / speed[i];
        }

        // 依據位置從大到小排序（確保最接近終點的車輛先被處理）
        Arrays.sort(cars, (a, b) -> Double.compare(b[0], a[0]));

        int fleets = 0;
        double timeToReachTarget = 0;

        // 計算車隊數量
        for (int i = 0; i < n; i++) {
            if (cars[i][1] > timeToReachTarget) {
                fleets++;
                timeToReachTarget = cars[i][1];
            }
        }

        return fleets;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int caseNumber = 1;

        while (scanner.hasNextInt()) {
            int target = scanner.nextInt();
            scanner.nextLine();  // 讀取換行符

            String[] positionStr = scanner.nextLine().split(" ");
            String[] speedStr = scanner.nextLine().split(" ");

            int n = positionStr.length;
            int[] position = new int[n];
            int[] speed = new int[n];

            for (int i = 0; i < n; i++) {
                position[i] = Integer.parseInt(positionStr[i]);
                speed[i] = Integer.parseInt(speedStr[i]);
            }

            int result = carFleet(target, position, speed);
            System.out.println("Case " + caseNumber + ": " + result + ".");
            caseNumber++;

            if (!scanner.hasNextInt()) {
                break;
            }
        }

        scanner.close();
    }
}
