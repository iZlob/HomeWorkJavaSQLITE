import java.sql.SQLException;
import java.nio.file.Files;

import java.io.File;

public class Main {
    public static void main(String[] args) throws SQLException {
        String FileName = "DevicesDB.sqlite";
        if (!(new File(FileName).exists())) {
            var db = new CreateDB(FileName);
            db.Create();
        }

        String query = "SELECT Manufacturer.NameManufacturer AS Manufacturer, Manufacturer.Country, Devices.NameDevice AS Device, Devices.Article, Devices.Price, Info.TargetDevice AS Target " +
                       "FROM Devices " +
                       "INNER JOIN Manufacturer ON Manufacturer.Id=Devices.ManufacturerId " +
                       "INNER JOIN Info ON Info.Id=Devices.InfoId;";

        var result = new GetData(FileName);
        result.PrintDataTable(query);
    }
}