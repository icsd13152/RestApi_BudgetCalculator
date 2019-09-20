
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 *
 * @author p.petropoulos
 */
@DatabaseTable(tableName = "outgoings")
public class Outgoing {

    @DatabaseField
    private Double amount;
    @DatabaseField
    private String outDate;

    public Outgoing() {
    }

    public Outgoing(Double amount, String outgoingDate) {
        this.amount = amount;
        this.outDate = outgoingDate;

    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public void setOutgoingDate(String outgoingDate) {
        this.outDate = outgoingDate;
    }

    public Double getAmount() {
        return amount;
    }

    public String getOutgoingDate() {
        return outDate;
    }

    @Override
    public String toString() {
        return "{\"amount\":\"" + amount + "\",\"outgoingDate\":\"" + outDate + "\"}";
    }
    
    

}
