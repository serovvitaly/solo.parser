/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soloserver;

import com.esotericsoftware.yamlbeans.YamlException;
import com.esotericsoftware.yamlbeans.YamlReader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
import java.io.ObjectOutputStream;

import java.net.UnknownHostException;
import java.util.Set;
import soloserver.belt.BeltObject;

/**
 *
 * @author Vitaliy
 */
public class SoloServer {
    
    static String config_file_name = "soloserver.cfg";
    
    protected static Config conf;
    
    protected static DBCollection coll_bobjects = null;
    
    //static FileConfiguration conf = null;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws UnknownHostException {
        // TODO code application logic here
        
        conf = loadConfig();
        
        MongoClient mongoClient = new MongoClient( conf.mongodb_host );
        DB db = mongoClient.getDB( conf.mongodb_base );
        coll_bobjects = db.getCollection( conf.name_coll_bobjects );
        
        //initHandlersStore();
        
        //System.out.println(conf.handlers_dir);
        
        BeltObject bobj = new BeltObject();
        
        bobj.name = "Иван";
        bobj.age = 33;
        bobj.sex = "male";
        
        //

    }
    
    
    /**
     * Фиксирует BeltObject в базе данных
     * @param bobject
     * @param passed_handler 
     */
    protected static void commitBeltObject(BeltObject bobject, String passed_handler){
        
        BasicDBObject doc = new BasicDBObject();
        doc.append("created", new java.util.Date().toString());
        doc.append("passed_handler", passed_handler);
        doc.append("BeltObject", bobject.toString());
        coll_bobjects.insert(doc);
        
    }
    
    
    protected static BeltObject captureObject(){
        return null;
        //
    }
    
    
    /**
     * Вызывает обработчик и передает ему объект BeltObject
     * @param handler_name - имя класса обработчика
     * @param bobject - BeltObject
     */
    protected static void callHandler(String handler_name, BeltObject bobject){
        //
    }
    
    
    /**
     * Инициализирует хранилище обработчиков
     */
    protected static void initHandlersStore(){       
        
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
