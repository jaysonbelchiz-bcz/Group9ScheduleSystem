import com.google.gson.*;
import java.io.*;

public class ScheduleSystem implements SystemActions {

    private Official[] officials = new Official[20];
    private int count = 0;

    // Default constructor (needed for Gson)
    public ScheduleSystem() {}

    // Getters
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

    // Add schedule
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

    // Delete schedule
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

    // ---------------- GSON SAVE ----------------
    public void saveToFile() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        try (FileWriter writer = new FileWriter("schedules.json")) {
            gson.toJson(this, writer);
        } catch (IOException e) {
            System.out.println("Error saving JSON file.");
        }
    }

    // ---------------- GSON LOAD ----------------
    public static ScheduleSystem loadFromFile() {
        Gson gson = new Gson();

        try (FileReader reader = new FileReader("schedules.json")) {
            return gson.fromJson(reader, ScheduleSystem.class);
        } catch (Exception e) {
            return new ScheduleSystem();
        }
    }
}
