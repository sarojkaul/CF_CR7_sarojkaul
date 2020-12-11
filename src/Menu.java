
//I would like to get feedback due to someQuestions
import java.*;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static Scanner scanner = new Scanner(System.in);
    static final String JDBC_Driver = "com.mysql.cj.jdbc.Driver";
    static final String url = "jdbc:mysql://localhost:3306/cr7_sarojkaul";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";

    Connection connection = null;
    static ArrayList<Classes> classes;

    public void display_menu() {
        System.out.println("1) Display all students");
        System.out.println("2) Display all teachers");
        System.out.println("3) Display all classes.");
        System.out.println("4) Display classes of teacher");
        System.out.println("5) Create report");
        System.out.println("6) Exit");
    }

    public void all_students() {
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** All Students ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT last_name,first_name FROM students";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                String last_name = set.getString("last_name");
                String first_name = set.getString("first_name");

                System.out.println("Surname: " + last_name + "  " + " Name: " + first_name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void all_teachers() {
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** All Teachers ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT last_name,first_name FROM teachers";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                String last_name = set.getString("last_name");
                String first_name = set.getString("first_name");

                System.out.println("Surname: " + last_name + "  " + " Name: " + first_name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void all_classes() {
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** All Classes ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();
            String sql;
            // Query for display all students
            sql = "SELECT id,name FROM classes";
            ResultSet set = statement.executeQuery(sql);
            while (set.next()) {
                int id = set.getInt("id");
                String first_name = set.getString("name");

                System.out.println("class_ID: " + id + "  " + " Name: " + first_name);

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Teacher> class_of_teacher() throws SQLException {
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection
            System.out.println("****** Display class_of_teacher  ********");
            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            String sql;
            // Query for display all students

            Scanner scanner = new Scanner(System.in);
            System.out.println("Enter teacher_Id : ");
            int teacher_Id = scanner.nextInt();
            sql = "SELECT teachers.first_name,teachers.last_name,classes.name,classes.id FROM teachers INNER JOIN classes ON teachers.id = classes.fk_teacher_id WHERE teachers.id = ? ";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, teacher_Id);
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Teacher> list = new ArrayList<>();
            List<Classes> list1 = new ArrayList<>();
            while (resultSet.next()) {

                String first_name = resultSet.getString("first_name");
                String last_name = resultSet.getString("last_name");
                String class_name = resultSet.getString("name");
                int id = resultSet.getInt("id");
                list.add(new Teacher(first_name, last_name));
                list1.add(new Classes(class_name));
                System.out.println("Teacher " + first_name + " " + last_name + " teaches ");

                //donot know why show only 1 class why not all classes belongs to teacher??

                System.out.println(class_name);

                preparedStatement.executeQuery();
                preparedStatement.close();

                preparedStatement.close();
                return list;
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        return null;
    }
    public void execute_menu(){
        int n = 0;
        try {
            do {
                Scanner user_input = new Scanner(System.in);
                System.out.println("Enter Your choice: ");
                int x = user_input.nextInt();
                if (x >= 0 && x <= 6) {
                    switch (x) {
                        case 1: {
                            Menu menu = new Menu();
                            menu.all_students();
                            break;
                        }
                        case 2: {

                            Menu menu = new Menu();
                            menu.all_teachers();
                            break;
                        }
                        case 3: {
                            Menu menu = new Menu();
                            menu.all_classes();
                            break;
                        }
                        case 4: {
                            Menu menu = new Menu();
                            menu.class_of_teacher();
                            break;
                        }
                        case 5: {
                            Menu menu = new Menu();
                            menu.getReport("C:\\Users\\kaul\\IdeaProjects\\CR7_Sarojkaul\\Report_for_allClasses.txt");
                        }
                        case 6: {
                            System.out.println("Exit");
                            n =-1;
                        }

                    }
                } else {
                    System.out.println("Enter Valid number");
                }
            } while (n == 0);
            System.out.println("End");
        } catch (NumberFormatException | SQLException e) {
            System.out.println("Enter numeric value");
        }
    }
    public boolean getReport(String nameofFile) {
        int count = 0;
        try {
            Class.forName(JDBC_Driver);
            //Step:3 OPen a connection

            connection = DriverManager.getConnection(url, USERNAME, PASSWORD);
            String sql="SELECT * FROM enroll";

            Statement statement = connection.createStatement();

            ResultSet set = statement.executeQuery(sql);
            FileWriter file = new FileWriter(nameofFile);
            while(set.next()){
                count++;
                int id = set.getInt("enroll_id");
                int student_Id = set.getInt("fk_student_id");
                int class_Id = set.getInt("fk_class_Id");
                 file.write("Enroll_Id " +id +" Student_ID " +student_Id +" class_ID " +class_Id);
                file.close();
                System.out.println("Sucessfully Wrote to the File");
                return true;
            }

            connection.close();
        } catch (ClassNotFoundException | SQLException |IOException ex) {
            System.err.println("An error occurred during writing into the file.");
            ex.printStackTrace();
        }
        return false;
    }



}










