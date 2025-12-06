import java.util.Scanner;

public class Admin extends User {

    private Scanner input = new Scanner(System.in);
    private final String PASSWORD = "admin123";

    public Admin(ScheduleSystem system) {
        super(system);
    }

    public boolean login() {
        System.out.print("Enter Admin Password: ");
        String pass = input.nextLine();

        if (pass.equals(PASSWORD)) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Incorrect password!");
        return false;
    }

    @Override
    public void menu() {
        int choice = 0;

        do {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Schedule");
            System.out.println("2. View All Schedules");
            System.out.println("3. Logout");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                System.out.println("Invalid input! Enter a number.");
                input.nextLine();
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                addSchedule();
            } else if (choice == 2) {
                system.viewAll();
            } else if (choice == 3) {
                System.out.println("Logging out...");
            } else {
                System.out.println("Invalid choice!");
            }

        } while (choice != 3);
    }

    private void addSchedule() {
        char again;

        do {
            System.out.print("Enter Official Name: ");
            String name = input.nextLine();

            System.out.print("Enter Day: ");
            String day = input.nextLine();

            System.out.print("Enter Time: ");
            String time = input.nextLine();

            String schedule = day + " - " + time;
            system.addSchedule(name, schedule);

            System.out.println("Schedule added!");

            System.out.print("Add another? (Y/N): ");
            again = input.next().toUpperCase().charAt(0);
            input.nextLine();

        } while (again == 'Y');
    }
}
