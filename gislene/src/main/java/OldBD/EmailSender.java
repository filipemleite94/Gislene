package OldBD;
import java.sql.SQLException;
import java.util.Scanner;

public class EmailSender {

    //Esta função cria um arquivo <Name>.fdb
 
   
    public static void main (String[] args)
    {
       
        GPSManager DBM  = new GPSManager();
        DBM.CreateDatabase("objetos3");   
       
        Scanner scanner = new Scanner(System.in);
       
        DBM = new GPSManager();
        DBM.CreateDatabase("objetos");
        
        try
        {
            
        	DBM = new GPSManager("objetos.fdb");
        	
            /*DBM.CreateObjectsTable();
            DBM.AddGenerator();
            DBM.AddTrigger_Atualizar_Data();*/
       
            DBM.InsertObjetoFromUserInput(scanner);
       
            DBM.Show_RegisteredObjects();
            
            DBM.DeleteObjectFromUserInput(scanner);
            
            DBM.Show_RegisteredObjects();
        	DBM = new GPSManager("objetos3.fdb");
            
            DBM.CreateSQLTable();
            DBM.AddGenerator();
            DBM.AddTrigger_Atualizar_Data();
       
            DBM.InsertSQL("Mouris, The Myth", "Minha sql importante");
       
            DBM.Show_RegisteredSQL();
            
            DBM.EditSQL("Mouris, The Myth", "Chupa JUnit");
            
            DBM.Show_RegisteredSQL();
        }
        catch (SQLException e)
        {
            System.out.println(e);
        }
    }
   
   
}