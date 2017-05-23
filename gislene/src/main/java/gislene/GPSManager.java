package gislene;

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
   
    void CreateDatabase (String Name)
    {
        FBManager manager = new FBManager(  GDSType.getType("TYPE4"));
        
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
   
    GPSManager()
    {
       
    }
   
    //Esta função é o construtor de DBManager: se conecta a <database>
    GPSManager (String database) throws SQLException
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
        System.out.println("TRIGGER ADICIONADA");
    }
   
    void InsertObjetoFromUserInput (Scanner scanner) throws SQLException
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
   
    //Esta função mostra os registros da Tabela Clientes na tela.
    void Show_RegisteredObjects () throws SQLException
    {
        resultSet = statement.executeQuery("SELECT * FROM Objetos");
        while (resultSet.next())
        {
            System.out.println( "ID: "         + resultSet.getString("ObjetoID") + " " +
                                "Nome: "     + resultSet.getString("Nome")      + " " +
                                "Coordenadas: "    + resultSet.getString("Coordenadas")     + " " +
                                "Data: "    + resultSet.getTimestamp("Data").toString() );
        }
    }
   
    void Disconnect () throws SQLException
    {
        resultSet.close();
        statement.close();
        connection.close();
        System.out.println("Disconnected");
    }
       
}