package OOPs.Encapsulation;

public class Main {
    public static void main(String[] args) {
        Employee emp = new Employee();
        emp.setId(1);
        emp.setName("Hari");
        emp.setDepartment("CSE");
        emp.setSalary(50000.00);


        System.out.println(
                "UserID: "+emp.getId()
                +" Name: "+emp.getName()
                + " Department: "+emp.getDepartment()
                +" Salary: "+ emp.getSalary()
        );

    }


}
