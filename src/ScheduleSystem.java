public class ScheduleSystem {

    private Official[] officials;
    private int count;

    public ScheduleSystem() {
        officials = new Official[50];
        count = 0;
    }

    public void addSchedule(String name, String schedule) {
        // Check if official already exists
        for (int i = 0; i < count; i++) {
            if (officials[i].getName().equalsIgnoreCase(name)) {
                officials[i].addSchedule(schedule);
                return;
            }
        }

        // If not found, make a new Official
        officials[count] = new Official(name);
        officials[count].addSchedule(schedule);
        count++;
    }

    public void viewAll() {
        if (count == 0) {
            System.out.println("No schedules available.");
            return;
        }

        System.out.println("\n--- ALL OFFICIALS' SCHEDULES ---");
        for (int i = 0; i < count; i++) {
            System.out.println("\n" + (i + 1) + ". " + officials[i].getName());
            String[] list = officials[i].getSchedules();
            int sCount = officials[i].getScheduleCount();

            for (int j = 0; j < sCount; j++) {
                System.out.println("   - " + list[j]);
            }
        }
    }

    public void search(String name) {
        for (int i = 0; i < count; i++) {
            if (officials[i].getName().equalsIgnoreCase(name)) {
                System.out.println("\nSchedules for " + name + ":");
                String[] list = officials[i].getSchedules();
                int sCount = officials[i].getScheduleCount();

                for (int j = 0; j < sCount; j++) {
                    System.out.println("   - " + list[j]);
                }
                return;
            }
        }

        System.out.println("Official not found.");
    }
}
