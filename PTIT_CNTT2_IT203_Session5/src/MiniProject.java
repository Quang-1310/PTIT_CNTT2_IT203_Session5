import java.util.Scanner;

public class MiniProject {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice;
        do {
            System.out.println("\n------------------------Menu---------------------------");
            System.out.println("1. [FR1] Tìm cặp số có tổng bằng K (Two Sum)");
            System.out.println("2. [FR2] Dồn số 0 về cuối (Move Zeroes)");
            System.out.println("3. [FR3] Kiểm tra chuỗi đối xứng (Valid Palindrome)");
            System.out.println("4. [FR4] Đảo ngược từ trong câu (Reverse Words)");
            System.out.println("5. [FR5] Số hạnh phúc (Happy Number)");
            System.out.println("0. Thoát");
            System.out.println("-------------------------------------------------------");
            System.out.print("Nhập lựa chọn của bạn : ");

            while (!sc.hasNextInt()) { // Kiểm tra nếu người dùng nhập chữ thay vì số
                System.out.print("Vui lòng nhập số: ");
                sc.next();
            }
            choice = sc.nextInt();
            sc.nextLine(); // Chống trôi lệnh

            switch (choice) {
                case 1:
                    System.out.print("Nhập số lượng mảng: ");
                    int n1 = sc.nextInt();
                    if (n1 <= 0) {
                        System.out.println("Số lượng không hợp lệ!");
                        break;
                    }
                    int[] arr1 = new int[n1];
                    for (int i = 0; i < n1; i++) {
                        System.out.printf("Nhập phần tử %d: ", i);
                        arr1[i] = sc.nextInt();
                    }
                    System.out.print("Nhập target: ");
                    int target = sc.nextInt();
                    twoSum(arr1, target);
                    break;

                case 2:
                    System.out.print("Nhập số lượng mảng: ");
                    int n2 = sc.nextInt();
                    if (n2 <= 0) {
                        System.out.println("Số lượng không hợp lệ!");
                        break;
                    }
                    int[] arr2 = new int[n2];
                    for (int i = 0; i < n2; i++) {
                        System.out.printf("Nhập phần tử %d: ", i);
                        arr2[i] = sc.nextInt();
                    }
                    moveZero(arr2);
                    break;

                case 3:
                    System.out.print("Nhập chuỗi: ");
                    String s3 = sc.nextLine();
                    if (s3.trim().isEmpty()) {
                        System.out.println("Dữ liệu không hợp lệ!");
                    } else {
                        System.out.println(validPalindrome(s3) ? "Là chuỗi đối xứng" : "Không đối xứng");
                    }
                    break;

                case 4:
                    System.out.print("Nhập chuỗi: ");
                    String s4 = sc.nextLine();
                    System.out.println("Kết quả: '" + reverseWords(s4) + "'");
                    break;

                case 5:
                    System.out.print("Nhập số nguyên dương: ");
                    int n5 = sc.nextInt();
                    if (n5 <= 0) {
                        System.out.println("Cần nhập số nguyên dương!");
                    } else {
                        happyNumber(n5);
                    }
                    break;

                case 0:
                    System.out.println("Đã thoát!");
                    break;
                default:
                    System.out.println("Lựa chọn không phù hợp!");
            }
        } while (choice != 0);
    }

    // FR1: Tối ưu vòng lặp j = i + 1
    static void twoSum(int[] array, int target) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] + array[j] == target) {
                    System.out.printf("Tìm thấy tại chỉ số: [%d, %d]\n", i, j);
                    return;
                }
            }
        }
        System.out.println("Không tìm thấy!");
    }

    // FR2: Move Zeroes (In-place)
    static void moveZero(int[] array) {
        int pos = 0;
        for (int num : array) {
            if (num != 0) {
                array[pos++] = num;
            }
        }
        while (pos < array.length) {
            array[pos++] = 0;
        }
        System.out.print("Mảng sau khi dồn: ");
        for (int x : array) System.out.print(x + " ");
        System.out.println();
    }

    // FR3: Valid Palindrome (Sử dụng Two Pointers)
    static boolean validPalindrome(String input) {
        String clean = input.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
        int left = 0, right = clean.length() - 1;
        while (left < right) {
            if (clean.charAt(left) != clean.charAt(right)) return false;
            left++;
            right--;
        }
        return true;
    }

    // FR4: Reverse Words (Xử lý khoảng trắng thừa bằng Regex)
    static String reverseWords(String input) {
        String[] words = input.trim().split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (int i = words.length - 1; i >= 0; i--) {
            sb.append(words[i]);
            if (i > 0) sb.append(" ");
        }
        return sb.toString();
    }

    // FR5: Happy Number
    static void happyNumber(int n) {
        int original = n;
        // Sử dụng một tập hợp hoặc giới hạn vòng lặp để tránh loop vô hạn
        // Logic n != 4 là một cách tối ưu toán học cho các số không hạnh phúc
        while (n != 1 && n != 4) {
            n = sumSquare(n);
        }
        if (n == 1) System.out.println(original + " là số hạnh phúc.");
        else System.out.println(original + " không phải số hạnh phúc.");
    }

    static int sumSquare(int n) {
        int sum = 0;
        while (n > 0) {
            int digit = n % 10;
            sum += digit * digit;
            n /= 10;
        }
        return sum;
    }
}
