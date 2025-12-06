import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        ScheduleSystem system = new ScheduleSystem();
        Admin admin = new Admin(system);
        Student student = new Student(system);

        int choice = 0;

        while (choice != 3) {
            System.out.println("\n=============================");
            System.out.println(" CICS Officials Schedule System");
            System.out.println("=============================");
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

            switch (choice) {
                case 1:
                    adminMenu(admin);
                    break;

                case 2:
                    student.menu();
                    break;

                case 3:
                    System.out.println("Thank you for using the system!");
                    break;

                default:
                    System.out.println("Invalid choice.");
            }
        }

        input.close();
    }

    private static void adminMenu(Admin admin) {
        Scanner sc = new Scanner(System.in);
        int ch = 0;

        while (ch != 3) {
            System.out.println("\n--- ADMIN ACCESS ---");
            System.out.println("1. Login");
            System.out.println("2. Sign Up");
            System.out.println("3. Back");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                sc.nextLine();
                System.out.println("Invalid input. Numbers only.");
                continue;
            }

            ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                if (admin.login()) {
                    admin.menu();
                }
            } else if (ch == 2) {
                admin.signUp();
            } else if (ch != 3) {
                System.out.println("Invalid choice.");
            }
        }
    }
}