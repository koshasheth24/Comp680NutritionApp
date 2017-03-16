package sql;
import android.content.Context;
import android.os.StrictMode;

import com.mysql.jdbc.*;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Objects;

import model.User;
import model.UserCalorieCount;

/**
 * Created by Kosha and Atil on 3/13/2017.
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

    public DatabaseHelper() {

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

        try {
            Connection con = getConnection();
            Statement stmt = (Statement) con.createStatement();
            ResultSet rs;
            String sql = "INSERT INTO user_info"
                    + "(user_name, password) " + "VALUES"
                    + "("+"'"+user.getEmail()+"',"+"'"+user.getPassword()+"')";
            stmt.executeUpdate(sql);
            con.commit();
            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void deleteUser(User user) {
        try{
            Connection con = getConnection();
            String sql ="DELETE FROM user_info WHERE id=" + user.getId();
            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            stmt.executeUpdate();
            String sql1 ="DELETE FROM user_perday_counter WHERE id=" + user.getId();
            PreparedStatement stmt1=(PreparedStatement) con.prepareStatement(sql1);
            stmt.executeUpdate();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }


    public boolean checkUser(String email, String password) {

        Connection con = getConnection();
        boolean userExists = false;
        try {
            String sql = "SELECT user_name, password FROM user_info where user_name="+
                    "'"+ email+ "'" +" AND password="+"'"+password+ "'";
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs;

            rs= (ResultSet) stmt.executeQuery();
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

    public void saveToUserTable(User user) {
        Connection con = getConnection();
        try {
            String sql = "UPDATE user_info set age=?, sex=?, height=?, weight=?," +
                    " address=?, dob=?, contact=?, max_cal=?, max_protein=?, max_fiber=?,name=? "
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
            stmt.setString(11,user.getName());
            stmt.executeUpdate();
            con.commit();
            con.close();
            stmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User calculateRequiredValues(User user) {
        Double BMR=0.0;
        if(user.getSex().compareTo("M")==1 || user.getSex().compareTo("m")==1){
            BMR= (10*user.getWeight()) + (6.25*0.033*user.getHeight())-(5*user.getAge()) + 5;
        }
        else if(user.getSex().compareTo("F")==1 || user.getSex().compareTo("f")==1){
            BMR= (10*user.getWeight()) + (6.25*0.033*user.getHeight())-(5*user.getAge()) - 161;
        }

        max_cal=BMR*1.275;
        max_protien=user.getWeight()*1.275;

        if(user.getSex().compareTo("M")==1 || user.getSex().compareTo("m")==1){
            if(user.getAge()<50){
                max_fiber=38;
            }
            else if(user.getAge()>=50){
                max_fiber=30;
            }
        }
        else if(user.getSex().compareTo("F")==1 || user.getSex().compareTo("f")==1){
            if(user.getAge()<50){
                max_fiber=25;
            }
            else if(user.getAge()>=50){
                max_fiber=21;
            }
        }



        user.setMax_protien(max_protien);
        user.setMax_fiber(max_fiber);
        user.setMax_cal(max_cal);

        return user;
    }


    public void updateUserCalorieCountTable(UserCalorieCount userCalCount) {

        Connection con= getConnection();
        try{

            String sql = "UPDATE user_perday_counter SET curr_cal="
                    + userCalCount.getTotal_cal() + ","
                    + userCalCount.getTotal_protien()
                    + "," + userCalCount.getTotal_fiber()
                    + "WHERE id = " + userCalCount.getId();
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.executeUpdate();
            con.commit();
            con.close();
            stmt.close();
        }
        catch(SQLException e){
            e.printStackTrace();
        }
    }

    public UserCalorieCount fetchPreviousValue(int id) {

        UserCalorieCount userCalCount = new UserCalorieCount();
        Connection con = getConnection();
        try{
            String sql ="SELECT curr_cal, curr_proteins, curr_fiber FROM user_perday_counter WHERE id=" + id;

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs= (ResultSet) stmt.executeQuery();
            userCalCount.setTotal_cal (rs.getDouble("curr_cal"));
            userCalCount.setTotal_fiber(rs.getDouble("curr_fiber"));
            userCalCount.setTotal_protien(rs.getDouble("curr_protein"));
        }
        catch(SQLException e){
            e.printStackTrace();
        }

        return userCalCount;
    }

    public User fetchUserDetails(int id) {

        User user= new User();
       try {
           Connection con = getConnection();
           String sql = "SELECT max_cal, max_protein, max_fiber,user_name,name,age,sex,height,weight,address,dob,contact" +
                   " FROM user_info WHERE id=" + id;
           PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs = (ResultSet) stmt.executeQuery();
           user.setMax_cal(rs.getDouble("max_cal"));
           user.setMax_fiber(rs.getDouble("max_fiber"));
           user.setMax_protien(rs.getDouble("max_protein"));
           user.setName(rs.getString("user_name"));
           user.setEmail(rs.getString("name"));
           user.setAge(rs.getInt("age"));
           user.setSex(rs.getString("sex"));
           user.setHeight(rs.getDouble("height"));
           user.setWeight(rs.getDouble("weight"));
           user.setAddress(rs.getString("address"));
           user.setDob(String.valueOf(rs.getDate("dob")));
           user.setPhone(String.valueOf(rs.getInt("contact")));

       }
       catch(SQLException e){
           e.printStackTrace();;
       }

        return user;
    }

    public boolean checkUser(String email) {
        try {
            Connection con = getConnection();
            String sql = "SELECT  id FROM user_info WHERE user_name=" + email;
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs = (ResultSet) stmt.executeQuery();
            if(getSize(rs)==1){
                return true;
            }
        }
        catch(SQLException e) {
            e.printStackTrace();
        }
            return false;
    }


    public int getId(String email) {
        Connection con=getConnection();
        String getIdQuery="SELECT id FROM user_info WHERE user_name=?";
        int id=0;
        try {
            PreparedStatement getIdSQL =(PreparedStatement) con.prepareStatement(getIdQuery);
            getIdSQL.setString(1,email);
            ResultSet rs= (ResultSet) getIdSQL.executeQuery();
            while(rs.next()){
                id=rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public String getNameFromID(Integer id) {
        String name="";
        Connection con=getConnection();
        String getIdQuery="SELECT name FROM user_info WHERE id=?";
        try {
            PreparedStatement getIdSQL =(PreparedStatement) con.prepareStatement(getIdQuery);
            getIdSQL.setInt(1,id);
            ResultSet rs= (ResultSet) getIdSQL.executeQuery();
            while(rs.next()){
                name=rs.getString(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return name;
    }

    public ArrayList<String> getResults() {
        ArrayList<String> items=new ArrayList<String>();
        Connection con=getConnection();
        String sql="SELECT Shrt_Desc from fetch_nutrients WHERE Shrt_Desc like ?";
        PreparedStatement pst= null;
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=(ResultSet) pst.executeQuery();
            int i=1;
            while(rs.next()){
                items.set(i,rs.getString(i));
                i++;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;

    }
}
