import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ScheduleSystem system = ScheduleSystem.loadFromFile();
        Admin admin = new Admin(system);
        Student student = new Student(system);

        int choice = 0;

        while (choice != 3) {
            System.out.println("\n===============================");
            System.out.println("  CICS Schedule System");
            System.out.println("===============================");
            System.out.println("1. Admin");
            System.out.println("2. Student");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            if (!input.hasNextInt()) {
                input.nextLine();
                System.out.println("Invalid input. Numbers only.");
                continue;
            }

            choice = input.nextInt();
            input.nextLine();

            if (choice == 1) {
                System.out.print("Enter Admin Password: ");
                String pass = input.nextLine();

                if (admin.login(pass)) {
                    admin.menu();
                }

            } else if (choice == 2) {
                student.menu();

            } else if (choice == 3) {
                system.saveToFile();
                System.out.println("Thank you for using the system!");

            } else {
                System.out.println("Invalid choice.");
            }
        }

        input.close();
    }
}
