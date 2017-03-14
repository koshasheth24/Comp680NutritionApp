package sql;
import android.content.Context;
import android.os.StrictMode;

import com.mysql.jdbc.*;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;

import model.User;
import model.UserCalorieCount;

/**
 * Created by Kosha on 3/8/2017.
 */

public class DatabaseHelper{


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "nutrients";
    private static final String TABLE_USER = "user_info";
    private static final String USER_PERDAY_COUNTER="user_perday_counter";
    private static final String FETCH_NUTRIENTS="fetch_nutrients";
    String url="jdbc:mysql://76.174.25.49:3306/nutrients";
    String userCon="kosha";
    String passwordCon="kosha";


    private double max_cal,max_protien,max_fiber;
    private UserCalorieCount userCalCount;


    public DatabaseHelper(Context context) {
        //super(context,DATABASE_NAME, null, DATABASE_VERSION);
    }

    public Connection getConnection(){
        Connection con=null;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();

        StrictMode.setThreadPolicy(policy);
        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            con = (Connection) DriverManager.getConnection(url, userCon, passwordCon);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;

    }


    public void addUser(User user) {
        Connection con = getConnection();
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs;
            String sql = "INSERT INTO user_info"
                    + "(user_name, password) " + "VALUES"
                    + "("+"'"+user.getEmail()+"',"+"'"+user.getPassword()+"')";
            stmt.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void updateUser(User user) {
        //ToDo :update user query
    }


    public void deleteUser(User user) {
        //ToDO: Delete User query
    }


    public boolean checkUser(String email, String password) {

        Connection con = getConnection();
        boolean userExists = false;
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs;
            String sql = "SELECT id, user_name, password FROM user_info where user_name="+
                    "'"+ email+ "'" +" AND password="+"'"+password+ "'";
            rs= (ResultSet) stmt.executeQuery(sql);
            if(getSize(rs)== 1){
                userExists =  true;
            }else{
                userExists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    private int getSize(ResultSet rs) {
        int n=0;
        try {
            while(rs.next()){
                n+=1;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return n;
    }


    public boolean checkUserProfileExists(String username) {
        Connection con = getConnection();
        boolean userExists = false;
        try {
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs;
            String sql = "SELECT age, weight FROM user_info where user_name="+
                    "'"+ username+ "'" ;
            rs= (ResultSet) stmt.executeQuery(sql);
            if(getSize(rs)== 1){
                userExists =  true;
            }else{
                userExists = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userExists;
    }

    public void saveToUserTable(User user) {
        Connection con = getConnection();
        try {
            String sql = "UPDATE user_info set age=?, sex=?, height=?, weight=?," +
                    " address=?, dob=?, contact=?, max_cal=?, max_protein=?, max_fiber=? "
                    +" WHERE user_name='"+user.getEmail()+"'";

            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.setInt(1,user.getAge());
            stmt.setString(2,user.getSex());
            stmt.setDouble(3,user.getHeight());
            stmt.setDouble(4,user.getWeight());
            stmt.setString(5,user.getAddress());

            stmt.setDate(6, Date.valueOf(user.getDob()));
            stmt.setInt(7, Integer.parseInt(user.getPhone()));
            stmt.setDouble(8,user.getMax_cal());
            stmt.setDouble(9,user.getMax_protien());
            stmt.setDouble(10,user.getMax_fiber());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User calculateRequiredValues(String textAge, String textHeight, String textWeight,User user) {
        // ToDo : logic to calculate required cal,fiber,protiens
        max_cal=45;
        max_fiber=45;
        max_protien=34;
        user.setMax_protien(max_protien);
        user.setMax_fiber(max_fiber);
        user.setMax_cal(max_cal);
        //send in arraylist
        return user;
    }


    public void updateUserCalorieCountTable(UserCalorieCount userCalCount) {
        //ToDo : updatequery for usercaloriecounttable
    }

    public UserCalorieCount fetchPreviousValue(int id) {
        //ToDo : Select query for userCalCount

        return userCalCount;
    }

    public User fetchUserDetails(int id, String email) {
        //ToDo : select Query to fetch user details
        return null;
    }

    public boolean checkUser(String trim) {
        return false;
    }





}
