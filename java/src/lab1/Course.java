package lab1;

public class Course {
    private String courseNumber;
    private String courseName;
    private int courseHour;
    private double score;
    private Course next;

    public String getCourseNumber() {
        return courseNumber;
    }

    public void setCourseNumber(String courseNumber) {
        this.courseNumber = courseNumber;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public int getCourseHour() {
        return courseHour;
    }

    public void setCourseHour(int courseHour) {
        this.courseHour = courseHour;
    }

    public double getScore() {
        return score;
    }

    public void setScore(double score) {
        this.score = score;
    }

    public Course getNext() {
        return next;
    }

    public void setNext(Course next) {
        this.next = next;
    }
}

