package hw250408.Programming;

public class Member1 {
    private String name;
    private String id;
    private String password;
    private int age;

    // 생성자
    public Member1(String name, String id, String password, int age) {
        this.name = name;
        this.id = id;
        this.password = password;
        this.age = age;
    }

    // Getter / Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    // 회원 정보 출력
    public void printInfo() {
        System.out.println("이름: " + name);
        System.out.println("아이디: " + id);
        System.out.println("비밀번호: " + password);
        System.out.println("나이: " + age);
    }
}
