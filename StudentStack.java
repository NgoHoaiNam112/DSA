import java.util.Stack;

public class StudentStack {
    private Stack<Student> stack;
    private int capacity;

    public StudentStack(int capacity) {
        this.capacity = capacity;
        stack = new Stack<>();
    }

    public boolean isFull() {
        return stack.size() >= capacity;
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

    public void push(Student student) {
        if (!isFull()) {
            stack.push(student);
            System.out.println("Student pushed to stack: " + student);
        } else {
            System.out.println("Error: Stack is full.");
        }
    }

    public Student pop() {
        if (!isEmpty()) {
            return stack.pop();
        } else {
            System.out.println("Error: Stack is empty.");
            return null;
        }
    }

    public Student peek() {
        if (!isEmpty()) {
            return stack.peek();
        } else {
            System.out.println("Error: Stack is empty.");
            return null;
        }
    }
}
