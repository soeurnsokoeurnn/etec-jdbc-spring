import java.sql.*;
import java.util.Scanner;

public class Teacher {
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
        Teacher.connection();
        String name, gender, phoneNumber;
        Scanner scan = new Scanner(System.in);
        int option;
        do{
            System.out.println("------------ Menu ------------");
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
                    int n;
                    System.out.println("------- Insert Data -------");
                    System.out.print("Enter Number of Teacher: ");
                    n = scan.nextInt();
                    for (int i = 0;i < n;i++){
                        System.out.println("---- Teacher #" + (i+1)+ "------");
                        System.out.print("Enter Name: ");
                        scan.nextLine();
                        name = scan.nextLine();
                        System.out.print("Enter Gender: ");
                        gender = scan.nextLine();
                        System.out.print("Enter Phone Number: ");
                        phoneNumber = scan.nextLine();
                        String sql = "INSERT INTO `teachers`(`name`, `gender`, `phone`) values(?,?,?)";
                        try {
                            Connection con = connection();
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, name);
                            ps.setString(2,gender);
                            ps.setString(3,phoneNumber+" ");
                            ps.executeUpdate();
                        }
                        catch (Exception e){
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Insert Successfully");
                }
                case 2->{
                    System.out.println("------ SELECT -------");
                    try{
                        Connection con = connection();
                        String sql = "SELECT * FROM teachers";
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("PhoneNumber = "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 3->{
                    System.out.println("------ SEARCH by ID -------");
                    System.out.print("Enter id to search: ");
                    int id = scan.nextInt();
                    String sql = "SELECT * FROM teachers WHERE id = " + id;
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("PhoneNumber = "+rs.getString("phone"));
                        }
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 5->{
                    System.out.println("---------- Update By ID -----------");
                    System.out.print("-------- Enter ID ---------");
                    int id = scan.nextInt();
                    System.out.print("Enter Name: ");
                    name = scan.nextLine();
                    System.out.print("Enter Gender: ");
                    gender = scan.nextLine();
                    System.out.print("Enter Phone Number: ");
                    phoneNumber = scan.nextLine();
                    String sql = "UPDATE `teachers` SET name=?,gender=?,phone=? WHERE id=?";
                    try {
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2,gender);
                        ps.setString(3,phoneNumber+" ");
                        ps.setInt(4,id);
                        ps.executeUpdate();
                        System.out.println("Update Successfully");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Update not Successfully");
                    }
                }
                case 6->{
                    System.out.println("------------ Delete By ID ------------");
                    System.out.print("Enter ID to Delete: ");
                    int id = scan.nextInt();
                    String sql = "DELETE FROM teachers WHERE id = " +id;
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        st.execute(sql);
                        System.out.println("Delete Successfully");
                    }
                    catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 7->{
                    System.out.println("------------- Sort By ID -------------");
                    String sql = "SELECT * FROM teachers ORDER BY id DESC";
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("PhoneNumber = "+rs.getString("phone"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 8->{
                    System.out.println("----------- Sort By Name A-Z ------------");
                    String sql = "SELECT * FROM teachers ORDER BY name ASC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("PhoneNumber = "+rs.getString("phone"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }while (true);
    }
}
