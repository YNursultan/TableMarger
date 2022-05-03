public class Table {

    private String Monday[] = new String[1000];
    private String Tuesday[] = new String[1000];
    private String Wednesday[] = new String[1000];
    private String Thursday[] = new String[1000];
    private String Friday[] = new String[1000];
    private String Saturday[] = new String[1000];
    private String Sunday[] = new String[1000];
    private String Time[] = new String[100];

    public String[] getTime() {
        return Time;
    }

    public void setTime(String[] time) {
        Time = time;
    }

    private String teacher;

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

    public String[] getMonday() {
        return Monday;
    }

    public void setMonday(String[] monday) {
        Monday = monday;
    }

    public String[] getTuesday() {
        return Tuesday;
    }

    public void setTuesday(String[] tuesday) {
        Tuesday = tuesday;
    }

    public String[] getWednesday() {
        return Wednesday;
    }

    public void setWednesday(String[] wednesday) {
        Wednesday = wednesday;
    }

    public String[] getThursday() {
        return Thursday;
    }

    public void setThursday(String[] thursday) {
        Thursday = thursday;
    }

    public String[] getFriday() {
        return Friday;
    }

    public void setFriday(String[] friday) {
        Friday = friday;
    }

    public String[] getSaturday() {
        return Saturday;
    }

    public void setSaturday(String[] saturday) {
        Saturday = saturday;
    }

    public String[] getSunday() {
        return Sunday;
    }

    public void setSunday(String[] sunday) {
        Sunday = sunday;
    }
}
