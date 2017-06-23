package OldBD;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

import org.firebirdsql.gds.impl.GDSType;
import org.firebirdsql.management.FBManager;

public class GPSManager {

    Connection connection;
    Statement statement;
    ResultSet resultSet;
   
    public void CreateDatabase (String Name)
    {
        FBManager manager = new FBManager(GDSType.getType("TYPE4"));
        
        try
        {
            manager.start();
            manager.createDatabase("c:\\Kabart\\" + Name + ".fdb", "sysdba", "masterkey");
            manager.stop();
        }
        catch (Exception e)
        {
            System.out.println("ERRO: " + e);
        }
        
    }
   
    public GPSManager()
    {
       
    }
   
    //Esta função é o construtor de DBManager: se conecta a <database>
    public GPSManager (String database) throws SQLException
    {
        connection = DriverManager.getConnection("jdbc:firebirdsql:localhost:c:\\Kabart\\" + database, "sysdba", "masterkey");
        //connection = DriverManager.getConnection("jdbc:firebirdsql:" + database, "sysdba", "master");
        statement = connection.createStatement();
        System.out.println("Connected");
    }
   
    //Esta função utiliza a conexão para criar a Tabela Clientes.
    //Campos da Tabela Clientes:
    //ClienteID (PK)    Nome     Email    Data
    void CreateObjectsTable () throws SQLException
    {
        statement.executeUpdate("CREATE TABLE Objetos (                 " +
                                   "ObjetoID INT NOT NULL PRIMARY KEY,  " +
                                   "Nome VARCHAR(255),                  " +
                                   "Coordenadas VARCHAR(255),           " +
                                   "Atributo1 VARCHAR(255),             " +
                                   "Atributo2 VARCHAR(255),             " +
                                   "Data TIMESTAMP )    " );              
        System.out.println("TABELA ADICIONADA");
    }
    
    void CreateSQLTable () throws SQLException {
    	int sizeSQL = 255;
    	statement.executeUpdate("CREATE TABLE SqlData (                 " +
                "ObjetoID INT NOT NULL PRIMARY KEY,  " +
                "IDsql VARCHAR(" + sizeSQL + "),                  " +
                "SQLcd BLOB SUB_TYPE TEXT )");              
    	System.out.println("TABELA ADICIONADA");
    }
   
    //Esta função acrescenta o Generator "Gerador"
    void AddGenerator () throws SQLException
    {
        statement.executeUpdate("CREATE GENERATOR Gerador");
        System.out.println("GERADOR ADICIONADO");
    }
   
    //Esta função acrescenta a SP para acrescentar a data de Criação/Modificação à Tabela Clientes
    void AddTrigger_Atualizar_Data() throws SQLException
    {
        statement.executeUpdate("CREATE TRIGGER ATUALIZAR_DATA FOR Objetos BEFORE INSERT OR UPDATE " +
                                "AS                                                                 " +
                                "BEGIN                                                              " +
                                "     NEW.Data = CURRENT_TIMESTAMP;                                    " +
                                "END                                                                ");
        statement.executeUpdate("CREATE TRIGGER ATUALIZAR_DATA FOR SqlData BEFORE INSERT OR UPDATE " +
        						"AS                                                                 " +
        						"BEGIN                                                              " +
        						"     NEW.Data = CURRENT_TIMESTAMP;                                    " +
                				"END                                                                ");
        System.out.println("TRIGGER ADICIONADA");
    }
   
    public void InsertObjetoFromUserInput (Scanner scanner) throws SQLException
    {
        String Nome = "";
        String Coordenadas = "";
       
        System.out.println("Digite o Nome do novo Objeto:");
        while (Nome.isEmpty())
            Nome = scanner.nextLine();
        System.out.println("Digite as Coordenadas do novo Objeto:");
        while (Coordenadas.isEmpty())
            Coordenadas = scanner.nextLine();

        statement.executeUpdate("INSERT INTO Objetos (ObjetoID, Nome, Coordenadas) VALUES (GEN_ID(Gerador,1) ,  '" + Nome + "', '" + Coordenadas + "')");
        
    }
    
    public void InsertSQL (String id, String SQLcode) throws SQLException {
    	System.out.println("Inserindo SQL Code");
    	
    	statement.executeUpdate("INSERT INTO SqlData (ObjetoID, IDsql, SQLcd) VALUES (GEN_ID(Gerador,1) ,  '" + id + "', '" + SQLcode + "')");
    }
    
    public void EditSQL (String ID, String newSQL) throws SQLException {
    	statement.executeUpdate("DELETE FROM SqlData WHERE IDsql = '" + ID + "'");
    	InsertSQL(ID, newSQL);
    }
    //PQP CUIDADO COM ISSO, NAO APAGUE A TABELA SEM QUERER PF
    public void deleteEVERYTHINGSqlData() throws SQLException{
    	statement.executeUpdate("DELETE FROM SqlData");
    }
    public void deleteEVERYTHINGSObjetos() throws SQLException{
    	statement.executeUpdate("DELETE FROM Objetos");
    }
    public void DeleteObjectFromUserInput (Scanner scanner) throws SQLException
    {
    	String Nome = "";
    	
    	System.out.println("Digite o Nome do Objeto a ser deletado:");
        while (Nome.isEmpty())
            Nome = scanner.nextLine();
    	
        statement.executeUpdate("DELETE FROM Objetos WHERE Nome = '" + Nome + "'");
    }
   
    //Esta função mostra os registros da Tabela Clientes na tela.
    void Show_RegisteredObjects () throws SQLException
    {
        resultSet = this.getRegisteredObjects();
        System.out.println("Printando conteúdo!");
        
        while (resultSet.next())
        {
            System.out.println( "ID: "         + resultSet.getString("ObjetoID") + " " +
                                "Nome: "     + resultSet.getString("Nome")      + " " +
                                "Coordenadas: "    + resultSet.getString("Coordenadas")     + " " +
                                "Data: "    + resultSet.getTimestamp("Data").toString() );
        }
        System.out.println("Termino de Print");
    }
    public ResultSet getRegisteredObjects() throws SQLException{
    	return statement.executeQuery("SELECT * FROM Objetos");
    }
    public void Show_RegisteredSQL () throws SQLException
    {
        resultSet = this.getSQLresultset();
        System.out.println("Printando conteúdo!");
        while (resultSet.next())
        {
            System.out.println( "ID do BD: "         + resultSet.getString("ObjetoID") + " " +
                                "IDsql: "     + resultSet.getString("IDsql")      + " " +
                                "SQLCode: "    + resultSet.getString("SQLcd") );
        }
        System.out.println("Termino de Print");
    }
    public ResultSet getSQLresultset() throws SQLException{
    	return statement.executeQuery("SELECT * FROM SqlData");
    }
    public String GetSQLCodeFromID (String id) throws SQLException {
    	resultSet = statement.executeQuery("SELECT * FROM SqlData WHERE IDsql = " + id);
    	return resultSet.getString("SQLcd");
    }
   
    public void Disconnect () throws SQLException
    {
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println("Disconnected");
    }
       
}

