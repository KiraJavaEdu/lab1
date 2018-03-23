#include<string>

using std::string;

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
	
	void setName(string);
	void setNO(int);
	string getName();
	int getNO();
	double getAverScore();
	
	bool addCourse(string NO, string name, int hour, double score);
	bool delCourse(string NO);
	bool updateCourse(string NO, double score);
	void printReport();
	void printReport(string NO);
};

