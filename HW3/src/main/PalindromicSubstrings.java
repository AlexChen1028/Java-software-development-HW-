import java.util.Scanner;

public class PalindromicSubstrings {
    
    // 檢查字串 s 的子字串 s[left...right] 是否為回文
    private static boolean isPalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // 計算所有回文子字串的數量
    public static int countSubstrings(String s) {
        int count = 0;
        int n = s.length();
        
        // 嘗試每一種可能的子字串
        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                if (isPalindrome(s, i, j)) {
                    count++;
                }
            }
        }
        return count;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();
        int result = countSubstrings(s);
        System.out.println(result);
    }
}