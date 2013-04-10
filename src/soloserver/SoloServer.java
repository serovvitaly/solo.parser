/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soloserver;

import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.net.UnknownHostException;
import java.util.Set;


/**
 *
 * @author Vitaliy
 */
public class SoloServer {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException{
        // TODO code application logic here
        
        MongoClient mongoClient = new MongoClient("localhost");
        
        DB db = mongoClient.getDB("test");
        
        Set<String> colls = db.getCollectionNames();
        
        DBCollection coll = db.getCollection("foobar");
        
        BasicDBObject doc = new BasicDBObject("name", "Vitaly Serov").append("email", "serovvitaly@gmail.com");
        
        coll.insert(doc);
        
    }
}
