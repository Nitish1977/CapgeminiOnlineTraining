package OOPs.Encapsulation;


// Question: use Abstraction: check person is eligible for vote
// or not if yes print eligible else print not eligible and person details
public class Problem {

    private String name;
    private int age;

    private String address;

    private  long contact;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public long getContact() {
        return contact;
    }

    public void setContact(long contact) {
        this.contact = contact;
    }

    public void display(){

        if(getAge()>18){
            System.out.println("You are Eligible for vote");
            System.out.println(
                    "Name: "+getName()
                            +" Age: "+getAge()
                            +" Address: "+getAddress()
                            +" Contact: "+getContact()
            );
        }
        else{

            System.out.println("Sorry, You are not eligible for Vote");
            System.out.println(
                    "Name: "+getName()
                            +" Age: "+getAge()
                            +" Address: "+getAddress()
                            +" Contact: "+getContact()
            );
    }}
}
