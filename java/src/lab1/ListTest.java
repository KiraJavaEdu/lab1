package lab1;

public class ListTest {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("小林");
        s1.setNO(160824210);
        s1.addCourse("IT1001", "体系结构", 40, 80);
        s1.addCourse("IT1002", "操作系统", 60, 90);
        s1.addCourse("IT1003", "编译原理", 60, 55);

		Student s2 = new Student("李想", 160824209);
        s2.addCourse("IT1001", "体系结构", 40, 70);
        s2.addCourse("IT1002", "操作系统", 60, 85);
        s2.addCourse("IT1003", "编译原理", 60, 60);

		Student s3 = new Student("小宇", 160824207);
        s3.addCourse("IT1001", "体系结构", 40, 60);
        s3.addCourse("IT1002", "操作系统", 60, 70);
        s3.addCourse("IT1003", "编译原理", 60, 85);


		StuList list=new StuList();
		list.addStu(s1);
		list.addStu(s2);
		list.addStu(s3);
		list.sort();
		list.print();

		System.out.println("After delete the index 1 student:");
		list.removeStu(1);
		list.print();
		
		System.out.println("After delete the student of NO 160824210");
		list.removeStu_id(160824210);
		list.print();
    }
}
