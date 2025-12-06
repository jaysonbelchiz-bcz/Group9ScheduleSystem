public class ScheduleSystem {
    private Official[] officials = new Official[50];
    private int count = 0;

    public Official[] getOfficials() {
        return officials;
    }

    public int getCount() {
        return count;
    }

    // Find official by name (case-insensitive)
    public int findOfficial(String name) {
        for (int i = 0; i < count; i++) {
            if (officials[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }

    // Add schedule (group same names)
    public void addSchedule(String name, String schedule) {
        int index = findOfficial(name);

        if (index == -1) {
            officials[count] = new Official(name);
            officials[count].addSchedule(schedule);
            count++;
        } else {
            officials[index].addSchedule(schedule);
        }
    }

    // Remove a single schedule
    public void deleteSchedule(int officialIndex, int scheduleIndex) {
        Official o = officials[officialIndex];
        o.removeSchedule(scheduleIndex);

        if (o.getScheduleCount() == 0) {
            for (int i = officialIndex; i < count - 1; i++) {
                officials[i] = officials[i + 1];
            }
            officials[count - 1] = null;
            count--;
        }
    }
}
