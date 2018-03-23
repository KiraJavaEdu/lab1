package lab1;

public class StuTest {
    public static void main(String[] args) {
        Student s = new Student();
        s.setName("小林");
        s.setNO(160824210);
        System.out.println(s.getName());
        s.printReport();

        s.addCourse("IT1001", "体系结构", 40, 80);
        s.addCourse("IT1002", "操作系统", 60, 90);
        s.addCourse("IT1003", "编译原理", 60, 55);

        s.printReport();
        s.printReport("IT1002");

        s.delCourse("IT1003");
        s.printReport();
    }
}
