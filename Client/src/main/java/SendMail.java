
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


/**
 *
 * @author p.petropoulos
 */
public class SendMail {
    private String to;

    public SendMail(String to) {
        this.to = to;
        sendRequest();
    }
    
    
    
    public void sendRequest(){
        
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://localhost:8080/RestServer/rest/resources/getToken");
        
        String input = "{\"mail\":\"" + to + "\"}";

        Response res = target.request(MediaType.APPLICATION_JSON).get();
        if (res.getStatus() != 201) {
            System.out.println("Something went wrong");
            System.out.println(res.getStatus());

        } else {
            String result = res.readEntity(String.class);
            System.out.println(result);

        }
        
        
    }
}
