import java.util.Scanner;

public class Admin extends User {

    private final String ADMIN_PASSWORD = "group9";
    private Scanner input = new Scanner(System.in);

    public Admin(ScheduleSystem system) {
        super(system);
    }

    // ---------------- LOGIN ----------------
    public boolean login(String pass) {
        if (pass.equals(ADMIN_PASSWORD)) {
            System.out.println("Login successful!");
            return true;
        }
        System.out.println("Wrong password.");
        return false;
    }

    // ---------------- POLYMORPHISM ----------------
    @Override
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
            else if (choice == 4) System.out.println("Logging out.");
            else System.out.println("Invalid option.");
        }
    }

    // ---------------- HELPERS ----------------
    private int getInt(String msg) {
        int val;
        while (true) {
            System.out.print(msg);
            if (input.hasNextInt()) {
                val = input.nextInt();
                input.nextLine();
                return val;
            }
            input.nextLine();
            System.out.println("Invalid input. Numbers only.");
        }
    }

    private char getChar(String msg, char a, char b) {
        while (true) {
            System.out.print(msg);
            String s = input.nextLine().trim().toUpperCase();
            if (s.length() == 1 && (s.charAt(0) == a || s.charAt(0) == b))
                return s.charAt(0);
            System.out.println("Invalid input. '" + a + "' or '" + b + "' only.");
        }
    }

    // ---------------- ADD SCHEDULE ----------------
    private void addSchedule() {
        char again = 'Y';

        while (again == 'Y') {
            System.out.print("Enter Official Name: ");
            String name = input.nextLine();

            System.out.print("Enter a Day: ");
            String day = input.nextLine();

            int sh = getInt("Enter Start Hour (ex: 7): ");
            char sp = getChar("Enter Start Period (A/P): ", 'A', 'P');

            int eh = getInt("Enter End Hour (ex: 9): ");
            char ep = getChar("Enter End Period (A/P): ", 'A', 'P');

            String schedule = day + " - " + sh + (sp == 'A' ? "am" : "pm") +
                    " - " + eh + (ep == 'A' ? "am" : "pm");

            system.addSchedule(name, schedule);
            System.out.println("Schedule added!");

            again = getChar("Do you want to add more? (Y/N): ", 'Y', 'N');
        }
    }

    // ---------------- VIEW ALL ----------------
    private void viewAll() {
        System.out.println("\n--- ALL SCHEDULES ---");

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

        // Step 1: Show officials
        viewAll();

        System.out.print("Select official number: ");
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

        // Step 2: Show schedules of that official
        if (o.getScheduleCount() == 0) {
            System.out.println("No schedules to delete.");
            return;
        }

        System.out.println("\nSchedules for " + o.getName() + ":");
        for (int i = 0; i < o.getScheduleCount(); i++) {
            System.out.println((i + 1) + ". " + o.getSchedules()[i]);
        }

        // Step 3: Select which schedule to delete
        System.out.print("Select schedule number to delete: ");
        int sIndex;

        while (true) {
            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.print("Invalid input. Numbers only. Try again: ");
                continue;
            }

            sIndex = input.nextInt() - 1;
            input.nextLine();

            if (sIndex >= 0 && sIndex < o.getScheduleCount()) break;
            System.out.print("Invalid schedule number. Try again: ");
        }

        // Step 4: Delete selected schedule
        system.deleteSchedule(oIndex, sIndex);
        System.out.println("Your schedule deleted successfully!");
    }
}
