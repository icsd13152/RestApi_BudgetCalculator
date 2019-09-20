
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;



/**
 *
 * @author p.petropoulos
 */
@DatabaseTable(tableName = "incomes")
public class Income {
    
    @DatabaseField
    private Double amount;
    @DatabaseField
    private String date;

    public Income() {
    }

    public Income(Double amount, String date) {
        this.amount = amount;
        this.date = date;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "{\"amount\":\"" + amount + "\",\"date\":\"" + date + "\"}";
    }
    
    
}
