
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import java.io.IOException;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;


/**
 *
 * @author p.petropoulos
 */
@ApplicationPath("rest")
public class ApplicationConfig extends Application{

    public ApplicationConfig() {
        initial();
    }

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new HashSet();
        resources.add(Resources.class);
        return resources; 
    }
    
     private void initial() {
        try {
           
            String databaseUrl = "jdbc:sqlite:C:/sqlite/myDB.db";
            ConnectionSource con = new JdbcConnectionSource(databaseUrl);
            
            Dao<Outgoing, Integer> userDao = DaoManager.createDao(con, Outgoing.class);
            Dao<Income, Integer> incomeDao = DaoManager.createDao(con, Income.class);
            TableUtils.createTableIfNotExists(con, Outgoing.class);
            TableUtils.createTableIfNotExists(con, Income.class);
           
            
            con.close();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(ApplicationConfig.class.getName()).log(Level.SEVERE, null, ex);
        }

   }

}
