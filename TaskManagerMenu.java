import java.util.Scanner;

// Abstract class for task list
abstract class AbstractTaskList {
    abstract void addTask(String task, int priority, String status);
    abstract void showTasks();
    abstract void deleteTask(String task);
    abstract void updateStatus(String task, String status);
}

// Node class for doubly linked list
class TaskNode {
    String taskName;
    int priority;
    String status; // New field
    TaskNode next;
    TaskNode prev;

    TaskNode(String name, int priority, String status) {
        this.taskName = name;
        this.priority = priority;
        this.status = status;
        this.prev = null;
        this.next = null;
    }
}

// Concrete doubly linked list implementation
class DoublyTaskList extends AbstractTaskList {
    private TaskNode head;

    @Override
    void addTask(String name, int priority, String status) {
        TaskNode newNode = new TaskNode(name, priority, status);
        if (head == null) {
            head = newNode;
        } else {
            TaskNode temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = newNode;
            newNode.prev = temp;
        }
        System.out.println("Task added: " + name + " [Priority: " + priority + ", Status: " + status + "]");
    }

    @Override
    void showTasks() {
        if (head == null) {
            System.out.println("No tasks available.");
            return;
        }
        TaskNode temp = head;
        System.out.println("\n--- Task List ---");
        while (temp != null) {
            System.out.println("[Task: " + temp.taskName + ", Priority: " + temp.priority + ", Status: " + temp.status + "]");
            temp = temp.next;
        }
    }

    @Override
    void deleteTask(String name) {
        if (head == null) {
            System.out.println("No tasks to delete.");
            return;
        }
        TaskNode temp = head;
        while (temp != null && !temp.taskName.equalsIgnoreCase(name)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Task not found: " + name);
            return;
        }
        if (temp.prev != null) {
            temp.prev.next = temp.next;
        } else {
            head = temp.next;
        }
        if (temp.next != null) {
            temp.next.prev = temp.prev;
        }
        System.out.println("Deleted task: " + name);
    }

    @Override
    void updateStatus(String name, String status) {
        if (head == null) {
            System.out.println("No tasks to update.");
            return;
        }
        TaskNode temp = head;
        while (temp != null && !temp.taskName.equalsIgnoreCase(name)) {
            temp = temp.next;
        }
        if (temp == null) {
            System.out.println("Task not found: " + name);
            return;
        }
        temp.status = status;
        System.out.println("Updated status of '" + name + "' to: " + status);
    }
}

// Main class with menu
public class TaskManagerMenu {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AbstractTaskList taskList = new DoublyTaskList();
        int choice;

        do {
            System.out.println("\n--- Menu ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Delete Task");
            System.out.println("4. Update Task Status");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");

            choice = sc.nextInt();
            sc.nextLine(); // consume leftover newline

            switch (choice) {
                case 1:
                    System.out.print("Enter task name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter task priority (integer): ");
                    int priority = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter status (Pending/Completed): ");
                    String status = sc.nextLine();
                    taskList.addTask(name, priority, status);
                    break;
                case 2:
                    taskList.showTasks();
                    break;
                case 3:
                    System.out.print("Enter task name to delete: ");
                    String delName = sc.nextLine();
                    taskList.deleteTask(delName);
                    break;
                case 4:
                    System.out.print("Enter task name to update status: ");
                    String updName = sc.nextLine();
                    System.out.print("Enter new status (Pending/Completed): ");
                    String newStatus = sc.nextLine();
                    taskList.updateStatus(updName, newStatus);
                    break;
                case 5:
                    System.out.println("Exiting program...");
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 5);

        sc.close();
    }
}

