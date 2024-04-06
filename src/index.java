
public class index {

    public String FirstName;
    public String LastName;
    public String Email;
    public String Phone;
    public String Password;


    public static void main(String[] args){
        LoginPage loginform = new LoginPage();
        index user = loginform.user;

        loginform.setVisible(true);

    }
}
