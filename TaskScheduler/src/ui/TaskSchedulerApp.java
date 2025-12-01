package ui;

import model.Task;
import service.TaskSchedulerService;

import java.util.List;
import java.util.Scanner;


public class TaskSchedulerApp {

    public static void main(String[] args) {

        TaskSchedulerService scheduler = new TaskSchedulerService();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n--- Task Scheduler ---");
            System.out.println("1. Add Task");
            System.out.println("2. Execute Next Task");
            System.out.println("3. Execute All Tasks");
            System.out.println("4. View Pending Tasks");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");

            String rawChoice = scanner.nextLine().trim();
            int choice;
            try {
                choice = Integer.parseInt(rawChoice);
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1 -> {

                    try {
                        System.out.print("Enter task ID (integer): ");
                        int id = Integer.parseInt(scanner.nextLine().trim());

                        System.out.print("Enter task name/description: ");
                        String name = scanner.nextLine();

                        System.out.print("Enter priority (1 = highest, larger number = lower priority): ");
                        int priority = Integer.parseInt(scanner.nextLine().trim());

                        Task created = scheduler.addTask(id, name, priority);
                        System.out.println("Task added: " + created);
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid number input. Task not added.");
                    } catch (IllegalArgumentException ex) {
                        System.out.println("Error: " + ex.getMessage());
                    }
                }
                case 2 -> {


                    Task executed = scheduler.executeNextTask();
                    if (executed == null) {
                        System.out.println("No tasks to execute.");
                    }
                }
                case 3 -> {
                    if (!scheduler.hasPendingTasks()) {
                        System.out.println("No tasks to execute.");
                    } else {
                        System.out.println("Executing all tasks in priority order...");
                        scheduler.executeAll();
                    }
                }
                case 4 -> {

                    List<Task> pending = scheduler.getPendingTasks();
                    if (pending.isEmpty()) {
                        System.out.println("No pending tasks.");
                    } else {
                        System.out.println("Pending tasks (in execution order):");
                        for (Task t : pending) {
                            System.out.println("  " + t);
                        }
                    }
                }
                case 5 -> {
                    System.out.println("Exiting Task Scheduler...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
