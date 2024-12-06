import java.util.Random;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random();

        // Nhập số lượng sinh viên
        System.out.print("Enter the number of students to generate: ");
        int numberOfStudents = scanner.nextInt();

        // Tạo đối tượng StudentManagement với Stack kích thước tối đa bằng số sinh viên
        StudentManagement sm = new StudentManagement(numberOfStudents);

        // Sinh dữ liệu ngẫu nhiên cho danh sách sinh viên
        for (int i = 1; i <= numberOfStudents; i++) {
            String id = String.format("%03d", i); // Sinh ID: 001, 002, ...
            String name = "Student" + i; // Tên ngẫu nhiên: Student1, Student2, ...
            double marks = 1 + random.nextDouble() * 9; // Điểm từ 1.0 đến 10.0
            sm.addStudent(id, name, marks);
        }

        // In danh sách sinh viên ban đầu
        System.out.println("\nInitial list of students:");
        sm.printAllStudents();

        // So sánh thời gian thực hiện của các thuật toán sắp xếp
        System.out.println("\nSorting Algorithms Execution Time:");
        sm.measureSortingPerformance();

        // So sánh thời gian thực hiện của các thuật toán tìm kiếm
        System.out.println("\nSearching Algorithms Execution Time:");
        sm.measureSearchingPerformance();

        // Thực hiện các thao tác xóa và cập nhật sinh viên
        System.out.println("\nUpdate or Delete Operations:");

        // Cập nhật sinh viên
        System.out.print("Enter student ID to update: ");
        String updateId = scanner.next();
        System.out.print("Enter new name: ");
        String newName = scanner.next();
        System.out.print("Enter new marks (0.0 to 10.0): ");
        double newMarks = scanner.nextDouble();
        sm.updateStudent(updateId, newName, newMarks);

        // Xóa sinh viên
        System.out.print("Enter student ID to delete: ");
        String deleteId = scanner.next();
        sm.deleteStudent(deleteId);

        // In lại danh sách sinh viên sau khi cập nhật và xóa
        System.out.println("\nList of students after update and delete operations:");
        sm.printAllStudents();

        // Nhập ID sinh viên cần tìm
        System.out.print("\nEnter a student ID to search: ");
        String searchId = scanner.next();
        Student foundStudent = sm.searchStudentById(searchId);
        if (foundStudent != null) {
            System.out.println("\nFound student: " + foundStudent);
        } else {
            System.out.println("\nStudent with ID " + searchId + " not found.");
        }

        scanner.close();
    }
}
