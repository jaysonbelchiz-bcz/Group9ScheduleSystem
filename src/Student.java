import java.util.Scanner;

public class Student {
    private Scanner input = new Scanner(System.in);
    private ScheduleSystem system;

    public Student(ScheduleSystem system) {
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

    public void menu() {
        int choice = 0;

        while (choice != 3) {
            System.out.println("\n--- STUDENT MENU ---");
            System.out.println("1. View All Schedules");
            System.out.println("2. Search Official");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid input. Numbers only.");
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            switch (choice) {
                case 1: viewAll(); break;
                case 2: search(); break;
                case 3: System.out.println("Returning..."); break;
                default: System.out.println("Invalid option.");
            }
        }
    }

    private void viewAll() {
        if (system.getCount() == 0) {
            System.out.println("No schedules available.");
            return;
        }

        System.out.println("\n--- ALL SCHEDULES ---");
        for (int i = 0; i < system.getCount(); i++) {
            Official o = system.getOfficials()[i];
            System.out.println(o.getName() + ":");
            for (int j = 0; j < o.getScheduleCount(); j++) {
                System.out.println("   - " + o.getSchedules()[j]);
            }
        }

        System.out.print("Search for an official? (Y/N): ");
        char ans = getYN();
        if (ans == 'Y') search();
    }

    private void search() {
        System.out.print("Enter Official Name: ");
        String name = input.nextLine();

        int index = system.findOfficial(name);

        if (index == -1) {
            System.out.println("Official not found.");
            return;
        }

        Official o = system.getOfficials()[index];

        System.out.println("\nSchedules for " + o.getName() + ":");
        for (int j = 0; j < o.getScheduleCount(); j++) {
            System.out.println("   - " + o.getSchedules()[j]);
        }
    }
}
