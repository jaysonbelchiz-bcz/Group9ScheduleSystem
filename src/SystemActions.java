public interface SystemActions {

    // Core system actions (full abstraction)
    void addSchedule(String name, String schedule);
    void deleteSchedule(int officialIndex, int scheduleIndex);
    int findOfficial(String name);
    int getCount();
    Official[] getOfficials();
}
