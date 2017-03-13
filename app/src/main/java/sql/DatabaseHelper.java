package sql;
import android.content.Context;
import android.os.AsyncTask;
import android.os.StrictMode;

import com.mysql.jdbc.*;

import java.sql.DriverManager;
import java.sql.SQLException;

import model.User;
import model.UserCalorieCount;

/**
 * Created by Kosha on 3/8/2017.
 */

public class DatabaseHelper extends AsyncTask {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "nutrients";
    private static final String TABLE_USER = "user_info";
    private static final String USER_PERDAY_COUNTER="user_perday_counter";
    private static final String FETCH_NUTRIENTS="fetch_nutrients";
    String url="jdbc:mysql://76.174.25.49:3306/nutrients";
    String userCon="kosha";
    String passwordCon="kosha";


    private float max_cal,max_protien,max_fiber;
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
    //ToDo: insert query user table

    }


    public void updateUser(User user) {
        //ToDo :update user query
    }


    public void deleteUser(User user) {
        //ToDO: Delete User query
    }


    public boolean checkUser(String email, String password) {

        //ToDo: check login details query
        return true;
    }


    public boolean checkUserExists(String username) {
        //ToDo : DB call to check if user exists
        return true;
    }

    public void saveToUserTable(User user) {
        //ToDo : Insert profile details to user table
    }

    public void calculateRequiredValues(String textAge, String textHeight, String textWeight) {
        // ToDo : logic to calculate required cal,fiber,protiens
        max_cal=0;
        max_fiber=0;
        max_protien=0;
        //send in arraylist
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
        return true;
    }


    @Override
    protected Object doInBackground(Object[] params) {
        getConnection();
        return null;
    }
}
