/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package soloserver.belt;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.Serializable;

/**
 *
 * @author vitaly
 */
public class MainObject implements Serializable{
    
    @Override
    public String toString(){
        Gson gson = new GsonBuilder().create();
        
        return gson.toJson(this);
    }
    
}
