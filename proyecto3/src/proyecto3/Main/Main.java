/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proyecto3.Main;

import java.io.Serializable;
import javafx.application.Application;
import javafx.stage.Stage;
import proyecto3.Programa.Ventana;

/**
 *
 * @author mario
 */
public class Main extends Application implements Serializable
{    

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        Ventana ventana=new Ventana();
     
    }     
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)
    {
        // TODO code application logic here
        Main.launch(args);  
    }  
}
