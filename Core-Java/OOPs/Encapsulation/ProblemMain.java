package OOPs.Encapsulation;

public class ProblemMain {

    public static void main(String[] args) {
        Problem person = new Problem();

        person.setName("Nitish");
        person.setAge(22);
        person.setAddress("Mahua");
        person.setContact(8271459878L);

        Problem person1 = new Problem();
        person1.setName("Mani");
        person1.setAge(11);
        person1.setAddress("Patna");
        person1.setContact(8271449987L);

        person.display();

        System.out.println("================");
        person1.display();

    }

}
