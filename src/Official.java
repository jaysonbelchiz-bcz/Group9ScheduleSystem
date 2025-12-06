public class Official {
    private String name;
    private String[] schedules;
    private int count;

    public Official(String name) {
        this.name = name;
        this.schedules = new String[50];
        this.count = 0;
    }

    public String getName() {
        return name;
    }

    public void addSchedule(String schedule) {
        if (count < schedules.length) {
            schedules[count] = schedule;
            count++;
        }
    }

    public String[] getSchedules() {
        return schedules;
    }

    public int getScheduleCount() {
        return count;
    }
}
