import java.util.Scanner;

public class Admin {
    private String username;
    private String password;
    private boolean isRegistered = false;

    private Scanner input = new Scanner(System.in);
    private ScheduleSystem system;

    public Admin(ScheduleSystem system) {
        this.system = system;
    }

    private char getYN() {
        char c;
        while (true) {
            String s = input.nextLine().trim();
            if (s.length() == 1) {
                c = Character.toUpperCase(s.charAt(0));
                if (c == 'Y' || c == 'N') return c;
            }
            System.out.print("Invalid input. Enter Y or N: ");
        }
    }

    // SIGN UP
    public void signUp() {
        System.out.print("Create Username: ");
        username = input.nextLine();
        System.out.print("Create Password: ");
        password = input.nextLine();
        isRegistered = true;
        System.out.println("Account created successfully!");
    }

    // LOGIN
    public boolean login() {
        if (!isRegistered) {
            System.out.println("No admin account yet. Please sign up first.");
            return false;
        }

        System.out.print("Username: ");
        String u = input.nextLine();
        System.out.print("Password: ");
        String p = input.nextLine();

        if (u.equals(username) && p.equals(password)) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Incorrect username or password.");
        return false;
    }

    // ADMIN MENU
    public void menu() {
        int choice = 0;

        while (choice != 4) {
            System.out.println("\n--- ADMIN MENU ---");
            System.out.println("1. Add Schedule");
            System.out.println("2. View All Schedules");
            System.out.println("3. Delete Schedule");
            System.out.println("4. Logout");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid input. Numbers only.");
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: addSchedule(); break;
                case 2: viewAll(); break;
                case 3: deleteSchedule(); break;
                case 4: System.out.println("Logging out..."); break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    // ADD SCHEDULE
    private void addSchedule() {
        char again = 'Y';

        while (again == 'Y') {
            System.out.print("Enter Official Name: ");
            String name = input.nextLine();

            System.out.print("Enter Day: ");
            String day = input.nextLine();

            System.out.print("Enter Start Hour (ex: 7): ");
            int sh = input.nextInt();
            input.nextLine();

            System.out.print("Enter Start Period (A/P): ");
            char sp = getYN();

            System.out.print("Enter End Hour (ex: 10): ");
            int eh = input.nextInt();
            input.nextLine();

            System.out.print("Enter End Period (A/P): ");
            char ep = getYN();

            String schedule = day + " - " + sh + (sp=='A'?"am":"pm") + " - " + eh + (ep=='A'?"am":"pm");

            system.addSchedule(name, schedule);
            System.out.println("Schedule added!");

            System.out.print("Add another? (Y/N): ");
            again = getYN();
        }
    }

    // VIEW ALL
    private void viewAll() {
        System.out.println("\n--- ALL OFFICIALS ---");

        if (system.getCount() == 0) {
            System.out.println("No schedules found.");
            return;
        }

        for (int i = 0; i < system.getCount(); i++) {
            Official o = system.getOfficials()[i];
            System.out.println((i + 1) + ". " + o.getName() + ":");
            for (int j = 0; j < o.getScheduleCount(); j++) {
                System.out.println("   - " + o.getSchedules()[j]);
            }
        }
    }

    // DELETE SCHEDULE
    private void deleteSchedule() {
        if (system.getCount() == 0) {
            System.out.println("Nothing to delete.");
            return;
        }

        viewAll();

        System.out.print("Select official number: ");
        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Invalid input.");
            return;
        }
        int oIndex = input.nextInt() - 1;
        input.nextLine();

        if (oIndex < 0 || oIndex >= system.getCount()) {
            System.out.println("Invalid official.");
            return;
        }

        Official o = system.getOfficials()[oIndex];

        for (int j = 0; j < o.getScheduleCount(); j++) {
            System.out.println((j + 1) + ". " + o.getSchedules()[j]);
        }

        System.out.print("Select schedule number to delete: ");
        if (!input.hasNextInt()) {
            input.nextLine();
            System.out.println("Invalid input.");
            return;
        }
        int sIndex = input.nextInt() - 1;
        input.nextLine();

        system.deleteSchedule(oIndex, sIndex);
        System.out.println("Schedule deleted!");
    }
}
