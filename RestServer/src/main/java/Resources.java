
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 *
 * @author p.petropoulos
 */
@Path("/resources")
public class Resources {

    private Connection con;
    private Statement st;
    private ArrayList<Outgoing> outgoingList = new ArrayList<Outgoing>();
    //private ArrayList<RemainOutgoings> remainings = new ArrayList<RemainOutgoings>();
    private ArrayList<Income> incomeList = new ArrayList<Income>();

    @GET
    @Path("getOutgoings")
    @Produces("application/json")
    public Response getOutgoingInJson() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/myDB.db");

            st = con.createStatement();
            ResultSet s = st.executeQuery("select * from outgoings");
            while (s.next()) {

                String outDate = s.getString("outDate");
                Double amount = s.getDouble("amount");
                Outgoing o = new Outgoing(amount, outDate);
                outgoingList.add(o);

            }
            con.close();
            return Response.status(200).entity(outgoingList).build();//epistrefei 200 ws status OK
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("something went wrong").build();
    }

    @GET
    @Path("getIncome")
    @Produces("application/json")
    public Response getIncomeInJson() {
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/myDB.db");
            st = con.createStatement();
            ResultSet s = st.executeQuery("select * from incomes");
            while (s.next()) {

                String inDate = s.getString("date");
                Double amount = s.getDouble("amount");
                Income o = new Income(amount, inDate);
                incomeList.add(o);
                System.out.println(amount);
            }
            con.close();
            return Response.status(200).entity(incomeList).build();//epistrefei 200 ws status OK
        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("something went wrong").build();
    }

    @GET
    @Path("remainingsPerDay")
    @Produces("application/json")
    public Response getReqOutgoings() {
        double sumOfOutgoings = 0;
        double remains = 0;
        int days = 0;
        
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);

        calendar.add(Calendar.MONTH, 1);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        calendar.add(Calendar.DATE, -1);
        Date lastDayOfMonth = calendar.getTime();
        RemainOutgoings r=null;
        try {
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/myDB.db");

            st = con.createStatement();
            ResultSet s = st.executeQuery("select * from incomes");
            while (s.next()) {

                String inDate = s.getString("date");
                Double amount = s.getDouble("amount");
                Date d = formatter.parse(inDate);
                if (date.getMonth() == d.getMonth()) {
                    remains = amount / (lastDayOfMonth.getDate()-date.getDate());
                    r =new RemainOutgoings(lastDayOfMonth.getDate()-date.getDate(),remains);
                    break;
                }

            }

            con.close();
            return Response.status(200).entity(r).build();
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("something went wrong").build();
    }

    @POST
    @Path("insertOutgoing")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertOutgoing(Outgoing o) {
        try {

            Class.forName("org.sqlite.JDBC");
            ConnectionSource conn = new JdbcConnectionSource("jdbc:sqlite:C:/sqlite/myDB.db");
            Dao<Outgoing, Integer> userdao = DaoManager.createDao(conn, Outgoing.class);
            userdao.create(o);
            conn.close();

            return Response.status(201).entity("ok").build();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

    @POST
    @Path("insertIncome")
    @Produces("application/json")
    @Consumes("application/json")
    public Response insertIncome(Income i) {
        try {

            Class.forName("org.sqlite.JDBC");
            ConnectionSource conn = new JdbcConnectionSource("jdbc:sqlite:C:/sqlite/myDB.db");
            Dao<Income, Integer> incomeDao = DaoManager.createDao(conn, Income.class);
            incomeDao.create(i);
            conn.close();

            return Response.status(201).entity("ok").build();

        } catch (SQLException | ClassNotFoundException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

    @PUT
    @Path("updateIncome")
    @Produces("application/json")
    @Consumes("application/json")
    public Response updateIncome(Outgoing o) {
        try {
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date d1 = formatter.parse(o.getOutgoingDate());
            Class.forName("org.sqlite.JDBC");
            con = DriverManager.getConnection("jdbc:sqlite:C:/sqlite/myDB.db");

            st = con.createStatement();
            ResultSet s = st.executeQuery("select * from incomes");
            int s2 = 0;
            while (s.next()) {

                String inDate = s.getString("date");
                Date d2 = formatter.parse(inDate);
                Double amount = s.getDouble("amount");

                if (d1.getMonth() == d2.getMonth()) {
                    if (d1.equals(d2)) {
                        s2 = st.executeUpdate("UPDATE incomes SET amount= " + (amount - o.getAmount()) + " WHERE date ='" + inDate + "'");
                        break;
                    } else if (d1.after(d2)) {
                        s2 = st.executeUpdate("UPDATE incomes SET amount= " + (amount - o.getAmount()) + " WHERE date ='" + inDate + "'");
                        break;
                    }
                }
            }
            con.close();
            return Response.status(201).entity(s2).build();
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            Logger.getLogger(Resources.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Response.status(500).entity("error").build();
    }

}
