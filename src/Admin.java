import java.util.Scanner;

public class Admin {
    private final String ADMIN_PASSWORD = "admin123";

    private Scanner input = new Scanner(System.in);
    private ScheduleSystem system;

    public Admin(ScheduleSystem system) {
        this.system = system;
    }

    // ---------------- INPUT VALIDATION ----------------

    private int getInt(String prompt) {
        int num;
        while (true) {
            System.out.print(prompt);
            if (input.hasNextInt()) {
                num = input.nextInt();
                input.nextLine();
                return num;
            } else {
                input.nextLine();
                System.out.println("Invalid input. Numbers only.");
            }
        }
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

    private char getAP() {
        char c;
        while (true) {
            String s = input.nextLine().trim();
            if (s.length() == 1) {
                c = Character.toUpperCase(s.charAt(0));
                if (c == 'A' || c == 'P') return c;
            }
            System.out.print("Invalid input. 'A' or 'P' only: ");
        }
    }

    // ---------------- LOGIN ----------------

    public boolean login(String pass) {
        if (pass.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful!");
            return true;
        }

        System.out.println("Incorrect password.");
        return false;
    }

    // ---------------- ADMIN MENU ----------------

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

            if (choice == 1) addSchedule();
            else if (choice == 2) viewAll();
            else if (choice == 3) deleteSchedule();
            else if (choice == 4) System.out.println("Logging out...");
            else System.out.println("Invalid option.");
        }
    }

    // ---------------- ADD SCHEDULE ----------------

    private void addSchedule() {
        char again = 'Y';

        while (again == 'Y') {
            System.out.print("Enter Official Name: ");
            String name = input.nextLine();

            System.out.print("Enter Day: ");
            String day = input.nextLine();

            int sh = getInt("Enter Start Hour (ex: 7): ");
            System.out.print("Enter Start Period (A/P): ");
            char sp = getAP();

            int eh = getInt("Enter End Hour (ex: 10): ");
            System.out.print("Enter End Period (A/P): ");
            char ep = getAP();

            String schedule = day + " - " + sh + (sp == 'A' ? "am" : "pm") +
                    " - " + eh + (ep == 'A' ? "am" : "pm");

            system.addSchedule(name, schedule);
            System.out.println("Schedule added!");

            System.out.print("Add another? (Y/N): ");
            again = getYN();
        }
    }

    // ---------------- VIEW ALL ----------------

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

    // ---------------- DELETE ALL SCHEDULES OF OFFICIAL ----------------

    private void deleteSchedule() {
        if (system.getCount() == 0) {
            System.out.println("Nothing to delete.");
            return;
        }

        viewAll();

        System.out.print("Select official number to delete all schedules: ");
        int oIndex;

        while (true) {
            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.print("Invalid input. Numbers only. Try again: ");
                continue;
            }

            oIndex = input.nextInt() - 1;
            input.nextLine();

            if (oIndex >= 0 && oIndex < system.getCount()) break;
            System.out.print("Invalid official number. Try again: ");
        }

        Official o = system.getOfficials()[oIndex];

        while (o.getScheduleCount() > 0) {
            system.deleteSchedule(oIndex, 0);
        }

        System.out.println("All schedules for " + o.getName() + " have been deleted!");
    }
}
