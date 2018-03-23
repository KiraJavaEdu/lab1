# Lab1-从CPP到Java，重构之旅第一弹

{% mdinhtml begin='<div class=panel><div class=lab>重构之旅 第一章</div><div class=word-in-panel>', end='</div></div>' %}
* Lab系列是重构之旅，我们将从一个简单的需求开始，伴随课程的不断深入，对这一程序逐步重构优化。
* lab1 的相关代码已经放到了github上，请执行`git clone XXXX`获取。
{% endmdinhtml %}

#重构之旅，由此展开
各位都是学生，我们就从学生开始这场重构之旅！为了对比CPP与Java面向对象的差异，首先请各位同学请使用CPP语言编写一个Student类，要求声明和实现分离，具体需求如下：
{% mdinhtml begin='<div class=panel><div class=lab>需求分析</div><div class=word-in-panel>', end='</div></div>' %}
1. 至少包含以下字段：姓名、学号、成绩单（包括课程名称、学时、分数，要求使用链表实现，由于每个学生选修的课程数量不一致，成绩单的项数不固定）、学分积（要求根据成绩单中各课程的学时数来计算分数的加权平均）
2. 方法：构造函数以及析构函数不能省略，可以根据要求自行添加所需要的方法，至少有一组函数重载。
3. 具备良好的封装，不允许直接操作对象的属性，必须通过相应的方法对对象的属性进行添加、删除、修改、查询。
{% endmdinhtml %}

为了更快进入正题，我们给出了CPP的头文件`lab1/cpp/src/Student.h`，lab的第一步请给出该头文件的具体实现，要求通过代码目录中所提供的测试用例。

{% mdinhtml begin='<div class=panel><div class=lab>提供的测试用例</div><div class=word-in-panel>', end='</div></div>' %}
* **注意**：后续的实现部分，一方面，lab的各个环节并不一定都会给出测试用例，需要同学们自行编写足够的测试用例保证程序的正确性；另一方面，即便提供了测试用例，其覆盖面也不一定能够针对所有边界条件，意味着即便通过了我们提供的测试用例，也无法保证你的程序实现是完全正确的。

* 我们的检测程序的测试用例是脚本自动生成的，不会使用我们提供的测试用例检测程序的正确性。因此，鼓励同学们尽可能跳出所提供的测试用例之外，进行足够的测试，请牢记**调试公理**！
{% endmdinhtml %}

```
class Student {
private:
	typedef struct _course_ {  
		string _courseNumber;
		string _courseName;	
		int _courseHour;
		double _score;  
		struct _course_ *next;  
	}course, *courseList;   
private:
	string name;
	int    NO;
	double averScore;
	courseList report;

private:
	void calcAverScore();
	
public:
	Student();
	Student(string name, int NO, double averScoer);
	~Student();
	
	// omit some getter & setter methods
	
	bool addCourse(string NO, string name, int hour, double score);
	bool delCourse(string NO);
	bool updateCourse(string NO, double score);
	void printReport();
	void printReport(string NO);
};

```
其中，学生的成绩单使用链表组织，链表中的每个节点代表该学生所修的一门课程，包括课程编码、课程名称、课时、得分信息。
这个头文件所定义的Student类对外提供了如下的方法：
1. 对于私有属性name以及NO，提供了共有的getter&setter方法。
2. 对于学分积，只能通过getter方法查询，并不能人为更改。
3. 对于学生的成绩单，提供了增删改查的基本操作，其中：
	* 对于“增”操作，如果该学生的成绩表中以及有相同课程编号的课程，则添加失败，返回false，否则添加到链表的尾部，返回true。
	* 对于“删”操作，如果该学生的成绩表中没有对应课程编号的课程，则删除失败，返回false，否则执行删除操作，返回true。
	* 对于“改”操作，如果该学生的成绩表中没有对应课程编号的课程，则修改失败，返回false，否则更新对应课程分数，返回true。
	* 对于“查”操作，通过函数重载，提供了2种查询方式：对于无参的`printReport()`方法，输出该学生的完整成绩表，包括姓名、学号、学分积以及所有课程信息；对于有参的`printReport(string)`方法，只需要输出该学生的姓名、学号以及对应课程的信息即可。
4. 请思考为什么要将方法`calcAverScore()`设置为`private`，并正确使用之。

# 穿越时空，从CPP到Java
在完成了上面的牛刀小试之后，相信实现一个对应Java版本的Student类对于各位同学来说，也不在话下。为了保证能够与上文的CPP版本的学生类相对应，此处我们同样给出了一个Java版的Student类模板。其中，由于Java不存在结构体，因此对于Course结构体，我们单独定义了一个课程类`lab1\java\src\lab1\Course.java`。lab所需要完成的第二部分，就是需要给出Java版本的具体实现，即完成`lab1\java\src\lab1\Student.java`文件中`todo`部分的代码体，要求通过工程目录中`lab1\java\src\lab1\StuList.java`的测试函数。Student类的方法细节与CPP版本的要求完全一致，请各位同学在实现代码的同时，仔细观察并学习示例代码的框架结构。

{% mdinhtml begin='<div class=panel><div class=lab>系统设计的黄金法则 -- KISS法则</div><div class=word-in-panel>', end='</div></div>' %}
这里的`KISS`是`Keep It Simple, Stupid`的缩写, 它的中文翻译是: 不要在一开始追求绝对的完美.

你已经学习过程序设计基础, 这意味着你已经学会写程序了, 但这并不意味着你可以顺利完成今后的项目。
因为在现实世界中, 我们需要的是可以运行的system, 而不是求阶乘的小程序.
随着Lab的进行，代码量会越来越多，虽然Lab重构之旅的最后一章的代码量虽然仍旧不多。
但是麻雀是虽小，五脏俱全，工程的维护会变得越来越困难, 一个很弱智的bug可能需要调好几天.
在这种情况下, 程序能跑起来才是王道, 跑不起来什么都是浮云, 追求面面俱到只会增加代码维护的难度.

唯一可以把你从bug的混沌中拯救出来的就是KISS法则,
它的宗旨是**从易到难, 逐步推进**, 一次只做一件事, 少做无关的事.
如果你不知道这是什么意思, 我们以上文提到的`str`成员缓冲区溢出问题来作为例子.
KISS法则告诉你, 你应该使用`assert(0)`, 就算不"得体"地处理上述问题, 仍然不会影响表达式求值的核心功能的正确性.
如果你还记得调试公理, 你会发现两者之间是有联系的: 调试公理第二点告诉你, 未测试代码永远是错的.
与其一下子写那么多"错误"的代码, 倒不如使用`assert(0)`来有效帮助你减少这些"错误".

如果把KISS法则放在软件工程领域来解释, 它强调的就是多做[单元测试](http://en.wikipedia.org/wiki/Unit_testing):
写一个函数, 对它进行测试, 正确之后再写下一个函数, 再对它进行测试...
一种好的测试方式是使用assertion进行验证, 学会使用assertion, 对程序的测试和调试都百利而无一害.

KISS法则不但广泛用在计算机领域, 就连其它很多领域也视其为黄金法则,
[这里](http://blog.sciencenet.cn/blog-414166-562616.html)有一篇文章举出了很多的例子, 我们强烈建议你阅读它, 体会KISS法则的重要性.
{% endmdinhtml %}


{% mdinhtml begin='<div class=panel><div class=lab>文档注释</div><div class=word-in-panel>', end='</div></div>' %}
在第二章中，介绍过java的文档注释工具，并要求同学们进行自学。在今后的lab中，凡是java程序都要求书写文档注释，并且使用javadoc工具，将对应文档抽取打包。
基本要求：所有对外暴露的方法，都需要有对应的文档注释！
{% endmdinhtml %}

```
public class Student {
    private String name;
    private int NO;
    private double averScore;
    private Course report;

    public Student() {
		// todo, use just one line code
    }

    public Student(String name, int NO) {
        this.name = name;
        this.NO = NO;
        this.averScore = 0.0;
        this.report = new Course();
        this.report.setNext(null);
    }

    private void calcAverScore() {
		// todo
    }

	//omit some getter & setter methods
    public double getAverScore() {
		// todo
    }

    public boolean addCourse(String NO, String name, int hour, double score) {
		// todo
    }

    public boolean delCourse(String NO) {
		// todo
    }

    public boolean updateCourse(String NO, double score) {
		// todo
    }

    public void printReport() {
		// todo
    }

    public void printReport(String NO) {
		// todo
    }
}
```
在充分了解了KISS原则，现在就让我们来践行它。实际上，在Java的生态中，有非常成熟的单元测试工具[JUnit](http://junit.org/junit5/docs/current/user-guide/)。我们会在后续的lab重构之旅中接触这一工具，现在我们需要手动的进行单元测试，简单来说对于每一个`todo`的方法，你每实现一个方法，就需要执行相应方法功能的测试。同时为了跟踪你的开发过程，**要求每一次测试通过一个方法之后，都需要git commit一次，提交信息为“complete method XXX”**，XXX代表对应的方法名，例如`delCourse(String)`。请严格按照要求逐个方法进行单元测试，并提交相应的git记录，如果git log中缺少相应的提交信息，将严重影响你这一次的lab分数。

对于使用IDE进行开发的同学，请思考对于上述的程序（包括，StuTest.java, Student.java, Course.java 3个文件），如何使用javac进行编译，并使用java命令运行相应的可执行文件，**考试要求哦！**。

#学生从来都不止一个！
有人的地方就有江湖，有学生的地方就有排名！相信实现一个简单的学生类一定难不倒大家，接下来，留给大家的就是实现一个学生列表！
既然学生的成绩表信息已经使用链表组织了，那么这一次学生列表，就使用传统的**顺序表**也就是数组来实现！

同样的，我们给出了如下的代码框架，lab1你所需要实现的第3部分就是实现`lab1\java\src\lab1\StuList.java`，并且通过`lab1\java\src\lab1\ListTest.java`的测试用例。在实现过程中仍旧需要遵循KISS原则，按照如上要求提交git日志，并且书写文档注释（部分文档注释代码中已经给出）。请留心框架代码中用`private`修饰的辅助方法，并在实现过程中合理使用它们。回忆你所学过的知识！**为什么`DEFAULT_CAPACITY`使用 static final进行修饰？**，要求将你思考的结果写在文档注释中。

```
public class StuList {
	private Student[] stus;
	private int length;   // the actual number of student it contains
	/**
	 * Default initial capacity
	 * please write your answer here
	 * why use static final ?
	 */
	private static final int DEFAULT_CAPACITY=15;
	
	/**
	 * Init the StuList using the default capacity
	 */
	public StuList() {
		// todo
	}
	
	/**
	 * Init the StuList using the given cap
	 * @param 
	 */
	public StuList(int cap) {
		// todo
	}
	
	private boolean isOverflow() {  
		// todo
	}
	
	public boolean isEmpty() {  
		// todo
	}
	
	// omit other methods
```

如果你成功实现了`StuList`类，那么重构之旅第一弹也就告一段落了，很快lab2将会对上述的程序进行大刀阔斧的更改。
当然，相信聪明的你已经发现了，上述程序有哪些地方设计得不够好，可以利用目前学习过的知识，进行重构。
可以将你发现的问题明确的在实验报告中指出，并且提出解决方案（不要求实现）。敲黑板！**这可是加分项哦！**

