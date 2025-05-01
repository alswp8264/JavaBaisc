package progam06;

public class Student extends Work implements Human{
    int age;
    Student (int age){
        this.age = age;
    }
    public void print(){
        System.out.println(age + "세의 학생입니다.");
    }

    @Override
    public void eat(){
        System.out.println("도시락을 먹습니다.");
    }
}