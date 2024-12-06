import java.util.*;

public class StudentManagement {
    private List<Student> students;
    private StudentStack stack;

    public StudentManagement(int stackCapacity) {
        students = new ArrayList<>();
        stack = new StudentStack(stackCapacity);
    }

    public void addStudent(String id, String name, double marks) {
        if (id.isEmpty() || name.isEmpty() || marks < 0 || marks > 10) {
            System.out.println("Error: Invalid student data.");
            return;
        }

        for (Student s : students) {
            if (s.getId().equals(id)) {
                System.out.println("Error: Student with ID " + id + " already exists.");
                return;
            }
        }

        students.add(new Student(id, name, marks));
        System.out.println("Student added: " + id + " - " + name);
    }

    public void updateStudent(String id, String name, double marks) {
        for (int i = 0; i < students.size(); i++) {
            Student s = students.get(i);
            if (s.getId().equals(id)) {
                if (marks < 0 || marks > 10) {
                    System.out.println("Error: Invalid marks.");
                    return;
                }
                students.set(i, new Student(id, name, marks));
                System.out.println("Student updated: " + id + " - " + name);
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public void deleteStudent(String id) {
        Iterator<Student> iterator = students.iterator();
        while (iterator.hasNext()) {
            Student s = iterator.next();
            if (s.getId().equals(id)) {
                iterator.remove();
                System.out.println("Student with ID " + id + " deleted.");
                return;
            }
        }
        System.out.println("Student with ID " + id + " not found.");
    }

    public Student searchStudentById(String id) {
        for (Student s : students) {
            if (s.getId().equals(id)) {
                return s;
            }
        }
        return null;
    }

    public void printAllStudents() {
        for (Student s : students) {
            System.out.println(s);
        }
    }

    public void sortStudentsByMarks() {
        students.sort(Comparator.comparingDouble(Student::getMarks).reversed());
    }

    public void measureSortingPerformance() {
        List<Student> bubbleSortList = new ArrayList<>(students);
        List<Student> quickSortList = new ArrayList<>(students);

        long startTime, endTime;

        // Bubble Sort
        startTime = System.nanoTime();
        bubbleSort(bubbleSortList);
        endTime = System.nanoTime();
        System.out.println("Bubble Sort time: " + (endTime - startTime) + " ns");

        // QuickSort
        startTime = System.nanoTime();
        quickSort(quickSortList, 0, quickSortList.size() - 1);
        endTime = System.nanoTime();
        System.out.println("QuickSort time: " + (endTime - startTime) + " ns");
    }

    public void measureSearchingPerformance() {
        String searchId = "001";
        long startTime, endTime;

        // Linear Search
        startTime = System.nanoTime();
        linearSearch(searchId);
        endTime = System.nanoTime();
        System.out.println("Linear Search time: " + (endTime - startTime) + " ns");

        // Binary Search
        List<Student> sortedList = new ArrayList<>(students);
        bubbleSort(sortedList);
        startTime = System.nanoTime();
        binarySearch(sortedList, searchId);
        endTime = System.nanoTime();
        System.out.println("Binary Search time: " + (endTime - startTime) + " ns");
    }

    private void bubbleSort(List<Student> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (list.get(j).getMarks() < list.get(j + 1).getMarks()) {
                    Student temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }

    private void quickSort(List<Student> list, int low, int high) {
        if (low < high) {
            int pi = partition(list, low, high);
            quickSort(list, low, pi - 1);
            quickSort(list, pi + 1, high);
        }
    }

    private int partition(List<Student> list, int low, int high) {
        Student pivot = list.get(high);
        int i = (low - 1);
        for (int j = low; j < high; j++) {
            if (list.get(j).getMarks() > pivot.getMarks()) {
                i++;
                Student temp = list.get(i);
                list.set(i, list.get(j));
                list.set(j, temp);
            }
        }
        Student temp = list.get(i + 1);
        list.set(i + 1, list.get(high));
        list.set(high, temp);
        return i + 1;
    }

    private Student linearSearch(String id) {
        for (Student student : students) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    private Student binarySearch(List<Student> list, String id) {
        int low = 0, high = list.size() - 1;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int compare = list.get(mid).getId().compareTo(id);
            if (compare == 0) {
                return list.get(mid);
            }
            if (compare < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
}
