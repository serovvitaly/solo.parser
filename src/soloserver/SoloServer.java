/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soloserver;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.mongodb.MongoClient;
import com.mongodb.MongoException;
import com.mongodb.WriteConcern;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import com.mongodb.DBCursor;
import com.mongodb.ServerAddress;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.net.UnknownHostException;


/**
 *
 * @author Vitaliy
 */
public class SoloServer {
    
    static String config_file_name = "soloserver.cfg";
    
    protected static Config conf;
    
    //static FileConfiguration conf = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        conf = loadConfig();
        
        loadHandlers();
        
        System.out.println(conf.handlers_dir);
        
        //
        
        //MongoClient mongoClient = new MongoClient("localhost");
        //DB db = mongoClient.getDB("test");
        //Set<String> colls = db.getCollectionNames();
        //DBCollection coll = db.getCollection("foobar");
        //BasicDBObject doc = new BasicDBObject("name", "Vitaly Serov").append("email", "serovvitaly@gmail.com");
        //coll.insert(doc);
        
    }
    
    
    /**
     * Загружает обработчики
     */
    protected static void loadHandlers(){
        
        System.out.println( ClassLoader.getSystemClassLoader().getClass().getName() );
        
        
        
        File hdir = new File(conf.handlers_dir);
        
        if (!hdir.isDirectory()) {
            return;
        }
        
        for (File hfile : hdir.listFiles()) {
            System.out.println( hfile.getAbsolutePath() );
            
            //cloader.
            
        }
        
    }
    
    
    /**
     * Загружает конфигурацию из файла указанного в свойстве config_file_name
     * @return Config
     */
    protected static Config loadConfig(){
        
        Config config = null;
        
        try{
            YamlReader reader;
            reader = new YamlReader(new FileReader(config_file_name));
            
            config = reader.read(Config.class);
            
        } catch (YamlException | FileNotFoundException e) {
            System.out.println(e.getMessage());
        }
        
        if (config == null) {
            config =  new Config();
        }
        
        return config;
    }
}
