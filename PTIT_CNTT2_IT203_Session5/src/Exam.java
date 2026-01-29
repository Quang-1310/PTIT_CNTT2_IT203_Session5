import java.util.Scanner;
import java.util.regex.Pattern;

public class Exam {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int chosse;
        String[] arr = new String[100];
        int count = 0;
        do{
            System.out.println("""
                    1. Hiển thị
                    2. Thêm mới (Có Regex): * Yêu cầu nhập MSSV mới
                    3. Cập nhật: Nhập vị trí (index) cần sửa, kiểm tra tính hợp lệ của index và cho phép nhập MSSV mới (cũng phải thỏa mãn Regex)
                    4. Xóa: Nhập một MSSV cụ thể. Nếu tìm thấy, thực hiện xóa và dồn mảng để đảm bảo tính liên tục
                    5. Tìm kiếm (Regex): Nhập một chuỗi ký tự bất kỳ từ bàn phím
                    6. Thoát
                    """);
            System.out.print("Nhập lựa chọn của bạn: ");
            chosse = sc.nextInt();
            sc.nextLine();
            switch (chosse){
                case 1:
                    if(count == 0){
                        System.out.println("Danh sách trống");
                    }
                    else {
                        for(int i = 0; i < count; i++){
                            System.out.printf("%s\n", arr[i]);
                        }
                    }
                    break;
                case 2:
                    String regex = "^B[0-9]{7}$";
                    String temp;
                    while (true) {
                        System.out.print("Nhập MSSV mới: ");
                        temp = sc.nextLine();
                        if (temp.matches(regex)){
                            break;
                        }
                        System.out.println("Sai định dạng (B + 7 số). Thử lại");
                    }
                    arr[count] = temp;
                    count++;
                    System.out.println("Thêm thành công");
                    break;
                case 3:
                    int index;
                    System.out.print("Nhập vào vị trí cần sửa: ");
                    index = sc.nextInt();
                    sc.nextLine();
                    if(index < 0 || index > count){
                        System.out.print("Vị trí index không hợp lệ\n");
                    }
                    else {
                        String newMSSV;
                        while (true) {
                            System.out.print("Nhập MSSV mới: ");
                            newMSSV = sc.nextLine();
                            if (newMSSV.matches("^B[0-9]{7}$")) break;
                            System.out.println("Định dạng MSSV không đúng!");
                        }
                        arr[index - 1] = newMSSV;
                        System.out.println("Cập nhật thành công");
                    }
                    break;
                case 4:
                    String mssvDelete;
                    int indexDelete = -1;
                    System.out.print("Nhập vào MSSV cần xoá: ");
                    mssvDelete = sc.nextLine();
                    for(int i = 0; i < count; i++){
                        if(arr[i].equals(mssvDelete)){
                            indexDelete = i;
                            break;
                        }
                    }
                    if(indexDelete == -1){
                        System.out.print("Không tìm thấy MSSV cần tìm\n");
                    }
                    else{
                        for(int i = indexDelete; i < count - 1; i++){
                            arr[i] = arr[i + 1];
                        }
                        count--;
                        System.out.print("Xoá thành công\n");
                    }
                    break;
                case 5:
                    String str;
                    System.out.print("Nhập 1 chuỗi cần tìm kiếm: ");
                    str = sc.nextLine().toLowerCase();
                    boolean isCheck = false;
                    for(int i = 0; i < count; i++){
                        if(arr[i].toLowerCase().contains(str)){
                            isCheck = true;
                            System.out.printf("%s\n", arr[i]);
                        }
                    }
                    if(isCheck == false){
                        System.out.print("Không tìm thấy mã sinh viên\n");
                    }

                    break;
                case 6:
                    break;
                default:
                    System.out.print("Lựa chọn không hợp lệ");
            }
        }while(chosse != 6);
    }
}
