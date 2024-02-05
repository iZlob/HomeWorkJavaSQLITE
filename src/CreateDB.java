import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateDB implements AutoCloseable {
    private Connection _connection;
    private Statement _statement;

    public CreateDB(String nameDB) throws SQLException {
        _connection = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
        _statement = _connection.createStatement();
    }

    public void Create(){
       try{
           _statement.execute("DROP TABLE IF EXISTS Info;");
           _statement.execute("CREATE TABLE IF NOT EXISTS Info (" +
                   "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "TargetDevice TEXT);");
           _statement.execute("INSERT INTO Info (TargetDevice) VALUES" +
                   "('CPU devise'), ('AI module'), ('AO module'), ('DI module'), ('DO module'), ('CPU & DI/DO/AI'), ('CPU & DI/DO'), ('CPU & DI/DO/AI/AO');");

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           _statement.execute("DROP TABLE IF EXISTS Manufacturer;");
           _statement.execute("CREATE TABLE IF NOT EXISTS Manufacturer (" +
                   "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "Country VARCHAR(15), " +
                   "NameManufacturer VARCHAR(25));");
           _statement.execute("INSERT INTO Manufacturer (Country, NameManufacturer) VALUES" +
                   "('Germany', 'Siemens')," +
                   "('France', 'Schneider Electric')," +
                   "('RUSSIA', 'OWEN');");

///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
           _statement.execute("DROP TABLE IF EXISTS Devices;");
           _statement.execute("CREATE TABLE IF NOT EXISTS Devices (" +
                   "Id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                   "NameDevice VARCHAR(20), " +
                   "Article VARCHAR(20), " +
                   "Price FLOAT, " +
                   "ManufacturerId INTEGER NOT NULL REFERENCES Manufacturer (Id), " +
                   "InfoId INTEGER NOT NULL REFERENCES Info (Id));");
           _statement.execute("INSERT INTO Devices (NameDevice, Article, Price, ManufacturerId, InfoId) VALUES" +
                   "('S7-300', 'JD756FRE-44FR', 456000.45, 1, 1), " +
                   "('S7-1200', 'JDIR54-DGH12', 234345, 1, 1), " +
                   "('6ES7131', 'FGH23-ER', 123900, 1, 4), " +
                   "('6ES7135', 'ddd56-DER23', 100322, 1, 3), " +
                   "('Modicon M258', 'TM258LF42DT4L', 137400, 2, 1), " +
                   "('Modicon M251', 'TM258LF42DR', 130560.79, 2, 1), " +
                   "('Modicon MC80', 'BMKC8020301', 89580, 2, 6), " +
                   "('ПЛК210', '789545', 64620.58, 3, 8), " +
                   "('ПЛК160 [М02]', '87685', 64920, 3, 7), " +
                   "('ПЛК154', 'ete5533', 42500.88, 3, 8);");

       }catch(Exception ex){
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void close() throws Exception {
        _connection.close();
    }
}
