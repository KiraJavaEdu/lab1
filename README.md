# Lab1-从CPP到Java，重构之旅第一弹
在开展实验之前，请各位同学仔细阅读[实验前阅读](https://kirajava.gitbook.io/introduction/)

Lab系列是重构之旅，我们将从一个简单的需求开始，伴随课程的不断深入，对这一程序逐步重构优化。
Lab1的相关代码已经放在了该git版本库中，需要同学们进一步完善这些程序。

提交要求：
1. Deadline: 
2. 提交内容包括：所有要求完成的源程序，实验报告以及要求导出的文档注释目录(java目录下的src和doc目录下内容此处省略)。
```
    kirajava-lab1
    ├── README.md
    ├── .gitignore
    ├── cpp
    │   └── src
    │       ├── Student.h   
    │       ├── Student.cpp
    │       └── main.cpp       
    └── java
        ├── src
        └── doc
```
3. 要求所有源文件均使用utf-8编码。

## 重构之旅，由此展开
各位都是学生，我们就从学生开始这场重构之旅！为了对比CPP与Java面向对象的差异，首先请各位同学请使用CPP语言编写一个Student类，要求声明和实现分离，具体需求如下：
1. 至少包含以下字段：姓名、学号、成绩单（包括课程名称、学时、分数，要求使用链表实现，由于每个学生选修的课程数量不一致，成绩单的项数不固定）、学分积（要求根据成绩单中各课程的学时数来计算分数的加权平均）
2. 方法：构造函数以及析构函数不能省略，可以根据要求自行添加所需要的方法，至少有一组函数重载。
3. 具备良好的封装，不允许直接操作对象的属性，必须通过相应的方法对对象的属性进行添加、删除、修改、查询。

为了更快进入正题，我们给出了CPP的头文件`cpp/src/Student.h`，lab的第一步请给出该头文件的具体实现，要求通过代码目录中所提供的测试用例`cpp/src/main.cpp`。

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

### 实现要求
学生的成绩单要求使用链表组织，链表中的每个节点代表该学生所修的一门课程，包括课程编码、课程名称、课时、得分信息。
这个头文件所定义的Student类对外提供了如下的方法：
1. 对于私有属性name以及NO，提供了共有的getter&setter方法。
2. 对于学分积，只能通过getter方法查询，并不能人为更改。
3. 对于学生的成绩单，提供了增删改查的基本操作，其中：
    * 对于“增”操作，如果该学生的成绩表中以及有相同课程编号的课程，则添加失败，返回false，否则添加到链表的尾部，返回true。
    * 对于“删”操作，如果该学生的成绩表中没有对应课程编号的课程，则删除失败，返回false，否则执行删除操作，返回true。
    * 对于“改”操作，如果该学生的成绩表中没有对应课程编号的课程，则修改失败，返回false，否则更新对应课程分数，返回true。
    * 对于“查”操作，通过函数重载，提供了2种查询方式：对于无参的`printReport()`方法，输出该学生的完整成绩表，包括姓名、学号、学分积以及所有课程信息；对于有参的`printReport(string)`方法，只需要输出该学生的姓名、学号以及对应课程的信息即可。
4. 请思考为什么要将方法`calcAverScore()`设置为`private`，并正确使用之。

### 关于提供的测试用例补充说明：
* **注意**：后续的实现部分，一方面，lab的各个环节并不一定都会给出测试用例，需要同学们自行编写足够的测试用例保证程序的正确性；另一方面，即便提供了测试用例，其覆盖面也不一定能够针对所有边界条件，意味着即便通过了我们提供的测试用例，也无法保证你的程序实现是完全正确的。

* 我们的检测程序的测试用例是脚本自动生成的，不会使用我们提供的测试用例检测程序的正确性。因此，鼓励同学们尽可能跳出所提供的测试用例之外，进行足够的测试，请牢记**调试公理——机器永远是对的，未测试的代码永远是错的**！

## 穿越时空，从CPP到Java
在完成了上面的牛刀小试之后，相信实现一个对应Java版本的Student类对于各位同学来说，也不在话下。为了保证能够与上文的CPP版本的学生类相对应，此处我们同样给出了一个Java版的Student类模板。其中，由于Java不存在结构体，因此对于Course结构体，我们单独定义了一个课程类`java\src\lab1\Course.java`。lab所需要完成的第二部分，就是需要给出Java版本的具体实现，即完成`java\src\lab1\Student.java`文件中`todo`部分的代码体，要求通过工程目录中`java\src\lab1\StuList.java`的测试函数。Student类的方法细节与CPP版本的要求完全一致，请各位同学在实现代码的同时，**仔细观察并学习示例代码的框架结构**。

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
        // todo
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

### 系统设计的黄金法则 -- KISS法则
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

在充分了解了KISS原则，现在就让我们来践行它。实际上，在Java的生态中，有非常成熟的单元测试工具[JUnit](http://junit.org/junit5/docs/current/user-guide/)。我们会在后续的lab重构之旅中接触这一工具，现在我们需要手动的进行单元测试，简单来说对于每一个`todo`的方法，你每实现一个方法，就需要执行相应方法功能的测试。同时为了跟踪你的开发过程，**要求每一次测试通过一个方法之后，都需要git commit一次，提交信息为“complete method XXX”**，XXX代表对应的方法名，例如`delCourse(String)`。请严格按照要求逐个方法进行单元测试，并提交相应的git记录，如果git log中缺少相应的提交信息，将严重影响你这一次的lab分数。

### 一次完整的“手工单元测试”示例（git极简教程）
同学们在github下载下来的工程目录，已经是一个git版本库，同学们已经不需要再执行`git init`创建git版本库。
我们通过实现`Student`类的一个含参数的构造器，来进行演示：
```
    public Student(String name, int NO) {
        this.name = name;
        this.NO = NO;
        this.averScore = 0.0;
        this.report = new Course();
        this.report.setNext(null);
    }
```
假设，各位同学测试通过了这个构造器，即通过该构造器成功创建了一个Student对象，并且通过提供的getter&setter方法，输出验证了构造器的正确性。一个可用的测试用例如下：
```
    public static void main(String[] args) {
        Student stu = new Student("李想", 130824210);
        System.out.println(stu.getName().equals("李想") ? "right" : "wrong");  // 注意：使用equals比较String类型的字面值
        System.out.println(stu.getNO() == 130824210 ? "right" : "wrong");
    }
```
现在，我们的`Student`构造器通过了上述测试用例，接下来，在项目的工程目录的根目录下运行如下命令：
```
2016_lab1 $ git add java/src/lab1/Student.java
```
该命令将修改后的`Student.java`添加到了版本库中，我们可以运行`git status`命令来观察，版本库目前的状态。
```
2016_lab1 $ git status
On branch master
Your branch is up to date with 'origin/master'.

Changes to be committed:
  (use "git reset HEAD <file>..." to unstage)

    modified:   java/src/lab1/Student.java
```
我们可以看到，目前版本库中目前记录了修改后的`Student.java`文件。接下来，需要我们通过`git commit`命令，将这次修改进行提交，**每一次提交必须填写提交信息**。
```
2016_lab1 $ git commit -m 'complete method Student(String, int)'
```
我们可以通过`git log`命令来查看，所有的提交信息：
```
2016_lab1 $ git log
commit 87aad9321cc58541f6272c1c968aa2fd12c45107 (HEAD -> master)
Author: kira-macbook pro <mybaby101@126.com>
Date:   Fri Mar 23 19:07:51 2018 +0800

    complete method Student(String, int)

commit 82a6f57614ee08b4bb37daf561cbcad978ea47da (origin/master)
Author: kira-macbook pro <mybaby101@126.com>
Date:   Fri Mar 23 18:08:30 2018 +0800

    modify to github markdown grammer

commit 9c7e2a8b573bf5f0240b899ad11c96dbf253e6c6
Author: kira-macbook pro <mybaby101@126.com>
Date:   Fri Mar 23 17:58:24 2018 +0800

    remove .DS_Store file

commit 7f3eea902469f6409ab0f989e8be152612ea5023
Author: kira-macbook pro <mybaby101@126.com>
Date:   Fri Mar 23 17:53:01 2018 +0800

    first commit
```
git日志为我们记录开发过程中每一次的提交状态，可以通过`git reset --hard [commit]`命令回滚到某一次的提交，还可以通过`git diff`比较两次提交，或两个分支的不同。
**再次强调，请严格按照要求逐个方法进行单元测试，并提交相应的git记录，如果git log中缺少相应的提交信息，将严重影响你这一次的lab分数。**

### 文档注释
在第二章中，介绍过java的文档注释工具，并要求同学们进行自学。在今后的lab中，凡是java程序都要求书写文档注释，并且使用javadoc工具，将对应文档抽取打包。
基本要求：所有对外暴露的方法，都需要有对应的文档注释！

### IDE帮我们隐藏了很多细节
相信经历过第二章作业，大部分同学都已经对于`javac`和`java`命令的使用比较熟悉，但是对于含有多个文件的代码，该以怎样的顺序编译，该执行哪个文件呢？
对于使用IDE进行开发的同学，IDE帮我们隐藏了很多细节，你所需要的只是点击一下run。但是，请思考对于上述的程序（包括，StuTest.java, Student.java, Course.java 3个文件），如何使用javac进行编译，并使用java命令运行相应的可执行文件，**考试要求哦！**。
在理解如何使用`javac`和`java`命令编译执行多文件程序后，今后的作业和实验，我们将推荐各位同学使用IDE开发，以提高开发效率。

## 学生从来都不止一个！
有人的地方就有江湖，有学生的地方就有排名！相信实现一个简单的学生类一定难不倒大家，接下来，留给大家的就是实现一个学生列表！
既然学生的成绩表信息已经使用链表组织了，那么这一次学生列表，就使用传统的**顺序表**也就是数组来实现！

同样的，我们给出了如下的代码框架，lab1你所需要实现的第3部分就是实现`java\src\lab1\StuList.java`，并且通过`java\src\lab1\ListTest.java`的测试用例。在实现过程中仍旧需要遵循KISS原则，按照如上要求提交git日志，并且书写文档注释（部分文档注释代码中已经给出）。
请留心框架代码中用`private`修饰的辅助方法，并在实现过程中合理使用它们。回忆你所学过的知识！**为什么`DEFAULT_CAPACITY`使用 static final进行修饰？**，要求将你思考的结果写在文档注释中。

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

# 实验报告
**看到这里，各位同学终于完成了lab1的全部内容，接下来请各位同学在本篇文档的后半部分完成实验报告，我们不需要八股文式的实验报告，在填写完基本信息后，你所要做的只是记录下你在实验过程中遇到的问题以及你的实验感想。另外，如果你在文本编辑器中打开本篇文档，你可能已经发现了本文的文字内容与网页显示的并不完全一致，包含了诸如`#, *`的符号。这些是[Markdown基本语法](https://guides.github.com/features/mastering-markdown/)标记，Markdown是一个非常非常简单的标记语言，将文档编写者从排版中解放出来，而专心于文字。各位需要使用Markdown的基本语法来书写实验报告。**

## 基本信息
学号：
班级：
姓名

## 遇到的问题
TODO

## 实验感想
TODO

## 发现的问题（可选）
TODO
