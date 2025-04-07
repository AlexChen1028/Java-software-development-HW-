import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

public class LinkedListCycleDetection {

    // 定義 ListNode 類別
    static class ListNode {
        int id;
        ListNode next;

        ListNode(int id) {
            this.id = id;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int n = Integer.parseInt(scanner.nextLine());

        Map<Integer, ListNode> map = new HashMap<>();
        int[][] input = new int[n][2];

        // 讀入 (id, next) 資料
        for (int i = 0; i < n; i++) {
            String[] parts = scanner.nextLine().split(" ");
            input[i][0] = Integer.parseInt(parts[0]);
            input[i][1] = Integer.parseInt(parts[1]);
            map.put(input[i][0], new ListNode(input[i][0]));
        }

        // 建立 linked list 結構
        for (int i = 0; i < n; i++) {
            int id = input[i][0];
            int nextId = input[i][1];
            map.get(id).next = map.get(nextId);
        }

        // 頭節點
        ListNode head = map.get(input[0][0]);
        ListNode slow = head, fast = head;

        // 使用 Floyd Cycle Detection 演算法找環
        boolean hasCycle = false;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                hasCycle = true;
                break;
            }
        }

        // 若無 cycle
        if (!hasCycle) {
            System.out.println(-1);
            return;
        }

        // 找 cycle 起點
        slow = head;
        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        // 已定位 cycle 起點，開始找出 cycle 中最小 id
        ListNode cycleStart = slow;
        int minId = cycleStart.id;
        ListNode curr = cycleStart.next;
        while (curr != cycleStart) {
            minId = Math.min(minId, curr.id);
            curr = curr.next;
        }

        System.out.println(minId);
    }
}