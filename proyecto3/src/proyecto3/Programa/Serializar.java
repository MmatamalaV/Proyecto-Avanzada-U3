/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Programa;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 *
 * @author mario
 */
public class Serializar
{
    Gson gson = new Gson();
    public Serializar()
    {
    }
    
    public void guardar(Eso sistema, String nombre) 
    {
        try{
            Writer writer = new FileWriter(nombre+".json");     
            String json = gson.toJson(sistema);  
        }
        catch (Exception ex) {}     

    } 

    public Eso abrir(String nombre)
    {
      Eso obj2 = gson.fromJson(nombre+".json", Eso.class);
      return obj2;
    }
    
}
