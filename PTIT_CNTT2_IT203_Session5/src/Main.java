import java.util.Scanner;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;

        do {
            System.out.println("\n========= MENU THUẬT TOÁN =========");
            System.out.println("1. [FR1] Tìm cặp số có tổng bằng K (Two Sum)");
            System.out.println("2. [FR2] Dồn số 0 về cuối (Move Zeroes)");
            System.out.println("3. [FR3] Kiểm tra chuỗi đối xứng (Valid Palindrome)");
            System.out.println("4. [FR4] Đảo ngược từ trong câu (Reverse Words)");
            System.out.println("5. [FR5] Số hạnh phúc (Happy Number)");
            System.out.println("0. Thoát chương trình");
            System.out.print("Lựa chọn của bạn: ");

            while (!sc.hasNextInt()) {
                System.out.println("Lỗi: Vui lòng nhập một số nguyên!");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // Đọc bỏ dòng trống

            switch (choice) {
                case 1:
                    handleTwoSum(sc);
                    break;
                case 2:
                    handleMoveZeroes(sc);
                    break;
                case 3:
                    handlePalindrome(sc);
                    break;
                case 4:
                    handleReverseWords(sc);
                    break;
                case 5:
                    handleHappyNumber(sc);
                    break;
                case 0:
                    System.out.println("Tạm biệt!");
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại.");
            }
        } while (choice != 0);
    }

    // --- [FR1] Two Sum ---
    public static void handleTwoSum(Scanner sc) {
        System.out.print("Nhập số lượng phần tử mảng: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            System.out.printf("arr[%d] = ", i);
            arr[i] = sc.nextInt();
        }
        System.out.print("Nhập giá trị target K: ");
        int target = sc.nextInt();

        boolean found = false;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (arr[i] + arr[j] == target) {
                    System.out.println("Tìm thấy cặp chỉ số: " + i + " và " + j);
                    found = true;
                    return; // Dừng khi tìm thấy cặp đầu tiên
                }
            }
        }
        if (!found) System.out.println("Không tìm thấy cặp số nào.");
    }

    // --- [FR2] Move Zeroes ---
    public static void handleMoveZeroes(Scanner sc) {
        System.out.print("Nhập số lượng phần tử: ");
        int n = sc.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) arr[i] = sc.nextInt();

        int lastNonZeroFoundAt = 0;
        for (int i = 0; i < n; i++) {
            if (arr[i] != 0) {
                int temp = arr[lastNonZeroFoundAt];
                arr[lastNonZeroFoundAt] = arr[i];
                arr[i] = temp;
                lastNonZeroFoundAt++;
            }
        }
        System.out.println("Mảng sau khi biến đổi: " + Arrays.toString(arr));
    }

    // --- [FR3] Valid Palindrome ---
    public static void handlePalindrome(Scanner sc) {
        System.out.print("Nhập chuỗi: ");
        String s = sc.nextLine();
        // Regex: loại bỏ ký tự không phải chữ và số
        String clean = s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();

        String reversed = new StringBuilder(clean).reverse().toString();
        boolean isPalindrome = clean.equals(reversed);

        System.out.println("Kết quả: " + (isPalindrome ? "True (Là chuỗi đối xứng)" : "False (Không đối xứng)"));
    }

    // --- [FR4] Reverse Words ---
    public static void handleReverseWords(Scanner sc) {
        System.out.print("Nhập câu: ");
        String s = sc.nextLine().trim();
        // Tách từ bằng regex xử lý nhiều khoảng trắng
        String[] words = s.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i != 0) sb.append(" ");
        }
        System.out.println("Kết quả: \"" + sb.toString() + "\"");
    }

    // --- [FR5] Happy Number ---
    public static void handleHappyNumber(Scanner sc) {
        System.out.print("Nhập n: ");
        int n = sc.nextInt();
        int slow = n, fast = n;

        // Sử dụng thuật toán Floyd's Cycle-Finding (Rùa và Thỏ) để phát hiện vòng lặp
        do {
            slow = getNext(slow);
            fast = getNext(getNext(fast));
        } while (slow != fast);

        boolean isHappy = (slow == 1);
        System.out.println("Kết quả: " + (isHappy ? "Là số hạnh phúc" : "Không phải số hạnh phúc"));
    }

    private static int getNext(int n) {
        int totalSum = 0;
        while (n > 0) {
            int d = n % 10;
            totalSum += d * d;
            n /= 10;
        }
        return totalSum;
    }
}