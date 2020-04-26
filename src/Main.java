import java.util.Scanner;

class Student{
    Student(){

    }
    Student (String stuNo, String stuName, String gender, byte age, int score){
        this.stuNo   = stuNo;

        this.stuName = stuName;

        this.gender  = gender;

        this.age     =  age;

        this.score   =  score;

    }

    String stuNo;

    String stuName;

    String birDate;

    String gender;

    byte   age;

    int    score;
}

class StudentManager{

    Student stuArray[] = new Student[10];
    int dataindex = 0;

    void insertStu(Student stu){
        if(dataindex == stuArray.length){
            Student newArray[] = new Student[stuArray.length + (stuArray.length >> 1)];
            for(int i = 0; i<dataindex; i++){
                newArray[i] = stuArray[i];
            }
            stuArray = newArray;///赋值时不用加[]
        }
        stuArray[dataindex] = stu;
        dataindex++;

    }

    Student checkStuNo(String stuNo){
        for(int i = 0; i < dataindex; i++){
            if(stuArray[i].stuNo.equals(stuNo)) ///.equals
            {
                return stuArray[i];
            }
        }
        return null;
    }

    void delateStu(String stuNo){///错误：找不到符号 有可能是大小写的问题，只要双击不论大小写只要拼写相同就变绿
        boolean flag = false;
        for(int i = 0; i < dataindex; i++){
            if (stuArray[i].stuNo.equals(stuNo)){
                stuArray[i] = stuArray[i + 1];
                flag = true;
            }
        }
        if(flag){
            dataindex--;
        }

    }

    void updateStu(Student stu){
        for(int i = 0; i < dataindex; i++){
            if(stuArray[i].stuNo.equals(stu.stuNo)){
                stuArray[i] = stu;
            }
        }
    }

    void printStu(Student stu){
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.print("学生编号 " + stu.stuNo + "  ");
        System.out.print("学生姓名 " + stu.stuName + "  ");
        System.out.print("学生性别 " + stu.gender + "  ");
        System.out.print("学生年龄 " + stu.age + "  ");
        System.out.println("学生成绩 " + stu.score + "  ");
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++");
    }

    void printAllstuInf(){
        for(int i  = 0; i < dataindex; i++){
            printStu(stuArray[i]);
        }

    }

    void App()
    { Scanner scan = new Scanner(System.in);

        Main main = new Main();

        System.out.println("请选择操作：\n\n");
        System.out.println("***************************");
        System.out.println("1：插入");
        System.out.println("2：删除");
        System.out.println("3：修改");
        System.out.println("4：查找");
        System.out.println("5：输出");
        System.out.println("0：退出");
        System.out.println("***************************");
        StuSys s1 = new StuSys();

        while(true){

            switch(scan.nextInt()){
                case 0:
                    System.exit(0);

                case 1:
                    s1.insertStu(scan);
                    break;

                case 2:
                    s1.delateStu(scan);
                    break;

                case 3:
                    s1.updateStu(scan);
                    break;

                case 4:
                    s1.showStuInfo(scan);
                    break;

                case 5:
                    s1.showAllStuInfo();
                    break;

                default:

                    System.out.println("输入数据不合规");
            }
        }}
}


class StuSys{
    StudentManager stud = new StudentManager();

    void insertStu(Scanner scanner){
        System.out.println("请输入学生的学号 姓名 性别 年龄 成绩（输入出用空格分开）：");
        String stuNo    = scanner.next();
        String stuName  = scanner.next();
        String gender   = scanner.next();
        byte   age		= scanner.nextByte();
        int    score	= scanner.nextInt();
        int    i 		= 0;

        if(stud.checkStuNo(stuNo) == null){
            Student stu = new Student(stuNo, stuName, gender, age, score);
            stud.insertStu(stu);
            System.out.println("学生信息添加成功");
        }
        else{
            System.out.println("您输入的学生编号已存在,请选择下一步操作");
            System.out.println("1、继续添加  2、退出系统");
            i = scanner.nextInt();
            if(i == 1){
                insertStu(scanner); ///递归
            }
            else if(i == 2){
                System.exit(0);
            }

            else{
                System.out.println("您输入的数据不合规");
            }
        }
    }

    void delateStu(Scanner scan){
        int i = 0;///可以重复使用i吗
        System.out.println("请输入要删除学生的编号：");
        String stuNo = scan.next();
        if(stud.checkStuNo(stuNo) != null){
            stud.delateStu(stuNo);
            System.out.println("学成信息删除成功");
        }
        else {
            System.out.println("您输入的学号不存在，请选择下一步操作");
            System.out.println("1、继续删除 2、退出系统");
            if(i == 1){
                delateStu(scan);
            }
            else if(i == 2) {
                System.exit(0);
            }
            else{
                System.out.println("您输入的数据不合规");
            }
        }
    }

    void updateStu(Scanner scanner){
        int i = 0;
        System.out.println("请输入要修改的学生的编号：");
        String stuNo = scanner.next();
        Student stu = new Student();
        ///非要等与null吗？？调试后的总结：1不能直接= new student 因为构造方法里没有student（） （无参数的构造方法）
        ///解决方法：加一个无参的构造方法
        if(stud.checkStuNo(stuNo) != null){
            System.out.println("请输入学生的姓名 性别 年龄 成绩 （输入时用空格分开）");
            stu.stuNo     = stuNo;
            stu.stuName  = scanner.next();
            stu.gender	 = scanner.next();
            stu.age		 = scanner.nextByte();
            stu.score	 = scanner.nextInt();
            stud.updateStu(stu);
            System.out.println("学生信息修改成功");
        }
        else{
            System.out.println("您输入的学生编号不存在，请选择下一步操作");
            System.out.println("1、继续修改  2、退出系统");
            i = scanner.nextInt();
            if(i == 1){
                insertStu(scanner);
            }
            else if(i == 2){
                System.exit(0);
            }

            else{

                System.out.println("您输入的数据不合规");
            }

        }
    }

    void showStuInfo(Scanner scanner){
        int i = 0;
        Student stu = new Student();
        System.out.println("请输入查看学生的编号");
        String stuNo = scanner.next();
        if((stu = stud.checkStuNo(stuNo)) != null){
            stud.printStu(stu);
        }
        else{
            System.out.println("您输入的学号不存在，请选择下一步操作");
            System.out.println("1、继续查看 2、退出系统");
            i = scanner.nextInt();
            if(i == 1){
                showStuInfo(scanner);
            }

            else if(i == 2){
                System.exit(0);
            }

            else{
                System.out.println("您输入的数据不合规");
            }
        }
    }
    void showAllStuInfo(){
        stud.printAllstuInf();
    }
}

public class Main{
    public static void main(String[] strs){

       new StudentManager().App();
    }


}
