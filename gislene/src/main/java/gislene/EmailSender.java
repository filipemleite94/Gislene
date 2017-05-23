package gislene;
import java.sql.SQLException;
import java.util.Scanner;

public class EmailSender {

    //Esta função cria um arquivo <Name>.fdb
 
   
    public static void main (String[] args)
    {
       
        GPSManager DBM = new GPSManager();
        DBM.CreateDatabase("objetos");   
       
        Scanner scanner = new Scanner(System.in);
       
        try
        {
            
        	DBM = new GPSManager("objetos.fdb");
       
            DBM.CreateObjectsTable();
            DBM.AddGenerator();
            DBM.AddTrigger_Atualizar_Data();
       
            DBM.InsertObjetoFromUserInput(scanner);
       
            DBM.Show_RegisteredObjects();
            
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
        
       
    }
   
   
}