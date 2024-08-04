import javax.xml.transform.Source;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Scanner;

public class Student {
    public static Connection connection(){
        String url = "jdbc:mysql://localhost:3306/db_java_etec";
        String username = "root";
        String password = "";

        try {
            System.out.println("Successfully");
            return DriverManager.getConnection(url, username, password);
        }
        catch (Exception e){
            System.out.println("Fail");
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        Student.connection();
        Scanner scan = new Scanner(System.in);
        int option;
        do {
            System.out.println("1. Insert Data");
            System.out.println("2. Select Data");
            System.out.println("3. Search Data By ID");
            System.out.println("4. Search Data By Name");
            System.out.println("5. Update Data By ID");
            System.out.println("6. Delete Data By ID");
            System.out.println("7. Sort Data By ID");
            System.out.println("8. Sort Data By Name(A->Z)");
            System.out.print("Enter One Option: ");
            option = scan.nextInt();
            switch (option){
                case 1->{

                }
            }
        }while (true);
    }
}
