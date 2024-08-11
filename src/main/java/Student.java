import javax.xml.transform.Source;
import java.sql.*;
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
        String name;
        String gender;
        float score1,score2,score3,total,average;
        char grade;
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
                    int n;
                    System.out.println("------- Insert Data -------");
                    System.out.print("Enter Number of Student: ");
                    n = scan.nextInt();
                    for (int i = 0;i < n;i++){
                        System.out.println("---- Student #" + (i+1)+ "------");
                        System.out.print("Enter Name: ");
                        scan.nextLine();
                        name = scan.nextLine();
                        System.out.print("Enter Gender: ");
                        gender = scan.nextLine();
                        System.out.print("Enter Score1: ");
                        score1 = scan.nextFloat();
                        System.out.print("Enter Score2: ");
                        score2 = scan.nextFloat();
                        System.out.print("Enter Score3: ");
                        score3 = scan.nextFloat();
                        total = score1+score2+score3;
                        average = total / 3;
                        grade = (average>=90)?'A' :
                                (average>=80)?'B':
                                        (average>=70)?'C':
                                                (average>=60)?'D':
                                                        (average>=50)?'E':'F';
                        String sql = "INSERT INTO `students`(`name`, `gender`, `score1`, `score2`, `score3`, `total`, `average`, `grade`) values(?,?,?,?,?,?,?,?)";
                        try {
                            Connection con = connection();
                            PreparedStatement ps = con.prepareStatement(sql);
                            ps.setString(1, name);
                            ps.setString(2,gender);
                            ps.setFloat(3, score1);
                            ps.setFloat(4, score2);
                            ps.setFloat(5, score3);
                            ps.setFloat(6, total);
                            ps.setFloat(7, average);
                            ps.setString(8,grade+"");
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
                        String sql = "SELECT * FROM students";//sql
                        Statement st = con.createStatement();// create statement to execute sql
                        ResultSet rs = st.executeQuery(sql);// execute sql
                        while(rs.next()){// get all data from result set
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
                case 3->{
                    System.out.println("------ SEARCH by ID -------");
                    System.out.print("Enter id to search: ");
                    int id = scan.nextInt();
                    String sql = "SELECT * FROM students WHERE id = " + id;
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while(rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
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
                    scan.nextLine();
                    name = scan.nextLine();
                    System.out.print("Enter Gender: ");
                    gender = scan.nextLine();
                    System.out.print("Enter Score1: ");
                    score1 = scan.nextFloat();
                    System.out.print("Enter Score2: ");
                    score2 = scan.nextFloat();
                    System.out.print("Enter Score3: ");
                    score3 = scan.nextFloat();
                    total = score1+score2+score3;
                    average = total / 3;
                    grade = (average>=90)?'A' :
                            (average>=80)?'B':
                                    (average>=70)?'C':
                                            (average>=60)?'D':
                                                    (average>=50)?'E':'F';
                    String sql = "UPDATE `students` SET name=?,gender=?,score1=?,score2=?,score3=?,total=?,average=?,grade=? WHERE id=?";
                    try {
                        Connection con = connection();
                        PreparedStatement ps = con.prepareStatement(sql);
                        ps.setString(1, name);
                        ps.setString(2,gender);
                        ps.setFloat(3, score1);
                        ps.setFloat(4, score2);
                        ps.setFloat(5, score3);
                        ps.setFloat(6, total);
                        ps.setFloat(7, average);
                        ps.setString(8,grade+"");
                        ps.setInt(9,id);
                        ps.executeUpdate();
                        System.out.println("Update Successfully");
                    }catch (Exception e){
                        e.printStackTrace();
                        System.out.println("Update not Successfully");
                    }
                }
                case 6->{
                    System.out.println("------------ Delete By ID ------------");
                    System.out.print("Enter ID to Delete: ");
                    int id = scan.nextInt();
                    String sql = "DELETE FROM students WHERE id = " +id;
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
                    String sql = "SELECT * FROM students ORDER BY id DESC";
                    try {
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                case 8->{
                    System.out.println("----------- Sort By Name A-Z ------------");
                    String sql = "SELECT * FROM students ORDER BY name ASC";
                    try{
                        Connection con = connection();
                        Statement st = con.createStatement();
                        ResultSet rs = st.executeQuery(sql);
                        while (rs.next()){
                            System.out.println("ID = "+rs.getInt("id"));
                            System.out.println("Name = "+rs.getString("name"));
                            System.out.println("Gender = "+rs.getString("gender"));
                            System.out.println("Score1 = "+rs.getFloat("score1"));
                            System.out.println("Score2 = "+rs.getFloat("score2"));
                            System.out.println("Score3 = "+rs.getFloat("score3"));
                            System.out.println("Total = "+rs.getFloat("total"));
                            System.out.println("Average = "+rs.getFloat("average"));
                            System.out.println("Grade = "+rs.getString("grade"));
                        }
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }while (true);
    }
}
