package sql;
import android.content.Context;
import android.os.StrictMode;

import com.mysql.jdbc.*;

import java.sql.Date;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import model.ItemNutrient;
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
    String url="jdbc:mysql://allnutrients.c1dcqdphtova.us-west-2.rds.amazonaws.com:3306/all_nutrients";
    String userCon="comp680";
    String passwordCon="abcd1234";


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

    public void addPerDayCounter(User user){
        Connection con = getConnection();
        Statement stmt = null;
        try {
            java.util.Date date=new java.util.Date();
            stmt = (Statement) con.createStatement();
            String sqluserCal = "INSERT INTO user_perday_counter"
                    + "(id,curr_date) " + "VALUES"
                    + "("+"'"+getId(user.getEmail())+"'"+",'"+date+"'"+")";
            stmt.executeUpdate(sqluserCal);
            con.commit();
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
            stmt1.executeUpdate();
            con.commit();
            con.close();
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
            try {
                con.rollback();
            } catch (SQLException e1) {
                e1.printStackTrace();
            }
        }
    }

    public User calculateRequiredValues(User user) {
        Double BMR=0.0;
        if(user.getSex().equals("M")|| user.getSex().equals("m")){
            BMR= (10*user.getWeight()) + (6.25*0.033*user.getHeight())-(5*user.getAge()) + 5;
        }
        else if(user.getSex().equals("F") || user.getSex().equals("f")){
            BMR= (10*user.getWeight()) + (6.25*0.033*user.getHeight())-(5*user.getAge()) - 161;
        }

        max_cal=BMR*1.275;
        max_protien=user.getWeight()*1.275;

        if(user.getSex().equals("M")|| user.getSex().equals("m")){
            if(user.getAge()<50){
                max_fiber=38;
            }
            else if(user.getAge()>=50){
                max_fiber=30;
            }
        }
        else if(user.getSex().equals("F") || user.getSex().equals("f")){
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
                    +"curr_proteins="
                    + userCalCount.getTotal_protien()
                    + ", curr_fiber="
                    + userCalCount.getTotal_fiber()
                    + "WHERE id = " + userCalCount.getId();
            PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
            stmt.executeUpdate();
            //con.commit();
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
            String sql ="SELECT curr_cal, curr_proteins, curr_fiber FROM all_nutrients.user_perday_counter WHERE id=" + id;

            PreparedStatement stmt= (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs= (ResultSet) stmt.executeQuery();
            while(rs.next()) {
                userCalCount.setTotal_cal(rs.getDouble("curr_cal"));
                userCalCount.setTotal_fiber(rs.getDouble("curr_fiber"));
                userCalCount.setTotal_protien(rs.getDouble("curr_proteins"));
            }
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
                   " FROM all_nutrients.user_info WHERE id=" + id;
           PreparedStatement stmt = (PreparedStatement) con.prepareStatement(sql);
           ResultSet rs = (ResultSet) stmt.executeQuery();
           while(rs.next()) {
               user.setMax_cal(rs.getDouble("max_cal"));
               user.setMax_fiber(rs.getDouble("max_fiber"));
               user.setMax_protien(rs.getDouble("max_protein"));
               user.setName(rs.getString("name"));
               user.setEmail(rs.getString("user_name"));
               user.setAge(rs.getInt("age"));
               user.setSex(rs.getString("sex"));
               user.setHeight(rs.getDouble("height"));
               user.setWeight(rs.getDouble("weight"));
               user.setAddress(rs.getString("address"));
               user.setDob(String.valueOf(rs.getDate("dob")));
               user.setPhone(String.valueOf(rs.getInt("contact")));
               user.setId(id);
           }
       }
       catch(SQLException e){
           e.printStackTrace();
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
        String sql="SELECT distinct description from all_nutrients.fetch_nutrients";
        PreparedStatement pst= null;
        try {
            pst = (PreparedStatement) con.prepareStatement(sql);
            ResultSet rs=(ResultSet) pst.executeQuery();
            int i=1;
            items.add("");
            while(rs.next()){
                items.add(rs.getString(i));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    public ItemNutrient fetchValuesForItem(String selItem) {
        ItemNutrient itemNutrient=new ItemNutrient();
        Connection con=getConnection();
        String sql="SELECT * from all_nutrients.fetch_nutrients WHERE description=?";
        PreparedStatement getItemDetails = null;
        try {
            getItemDetails = (PreparedStatement) con.prepareStatement(sql);
            getItemDetails.setString(1,selItem);
            ResultSet rs=(ResultSet) getItemDetails.executeQuery();
            while (rs.next()){
                itemNutrient.setCalories((float) rs.getDouble("calories"));
                itemNutrient.setFiber((float) rs.getDouble("fiber"));
                itemNutrient.setProtiens((float) rs.getDouble("proteins"));
                itemNutrient.setItem(selItem);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return itemNutrient;
    }
}
