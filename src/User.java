public abstract class User {
    protected ScheduleSystem system;

    public User(ScheduleSystem system) {
        this.system = system;
    }

    public abstract void menu();
}
