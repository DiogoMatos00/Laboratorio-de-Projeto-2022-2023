

import java.sql.Connection;
import java.util.List;
import Controller.questions;
import Controller.connection;

public class topic {
    private String id;
    private String name;
    private List<questions> topics;

    public topic(String name){
        Connection conn = connection.connect();
        
    }
}