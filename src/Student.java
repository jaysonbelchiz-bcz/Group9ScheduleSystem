import java.util.Scanner;

public class Student extends User {

    private Scanner input = new Scanner(System.in);

    public Student(ScheduleSystem system) {
        super(system);
    }

    @Override
    public void menu() {
        int choice = 0;

        do {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View All Schedules");
            System.out.println("2. Search Official");
            System.out.println("3. Exit to Main");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                system.viewAll();
            } else if (choice == 2) {
                System.out.print("Enter Official Name: ");
                String name = input.nextLine();
                system.search(name);
            } else if (choice == 3) {
                System.out.println("Returning to Main Menu...");
            } else {
                System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }
}
