#include<iostream>
#include"Student.h"

using namespace std;


int main()
{
    Student s = Student();
    s.setName("小林");
	s.setNO(160824210);
    cout << s.getName() << endl;
    s.printReport();
    
    s.addCourse("IT1001", "计算机体系结构", 40, 80);
    s.addCourse("IT1002", "操作系统", 60, 90);
    s.addCourse("IT1003", "编译原理", 60, 55);
    
    s.printReport();
    s.printReport("IT1002");
    
//    cout << s.getAverScore() << endl;
    
    s.delCourse("IT1003");
    s.printReport();
    
//   cout << s.getAverScore() << endl;
}
