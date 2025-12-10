public class Official {
    private String name;
    private String[] schedules = new String[50];
    private int scheduleCount = 0;

    public Official(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String[] getSchedules() {
        return schedules;
    }

    public int getScheduleCount() {
        return scheduleCount;
    }

    public void addSchedule(String schedule) {
        if (scheduleCount < schedules.length) {
            schedules[scheduleCount] = schedule;
            scheduleCount++;
        }
    }

    public void removeSchedule(int index) {
        if (index >= 0 && index < scheduleCount) {
            for (int i = index; i < scheduleCount - 1; i++) {
                schedules[i] = schedules[i + 1];
            }
            schedules[scheduleCount - 1] = null;
            scheduleCount--;
        }
    }
}
