import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class GetData {
    private Connection _connection;
    private Statement _statement;

    public GetData(String nameDB) throws SQLException {
        _connection = DriverManager.getConnection("jdbc:sqlite:" + nameDB);
        _statement = _connection.createStatement();
    }

    public void PrintDataTable(String query) throws SQLException {
        var result = _statement.executeQuery(query);
        var metaData = result.getMetaData();

        System.out.format("%-25s", metaData.getColumnName(1));
        System.out.format("%-15s", metaData.getColumnName(2));
        System.out.format("%-20s", metaData.getColumnName(3));
        System.out.format("%-20s", metaData.getColumnName(4));
        System.out.format("%-15s", metaData.getColumnName(5));
        System.out.format("%-20s", metaData.getColumnName(6));
        System.out.println();
        System.out.println("==========================================================================================================================");

        while (result.next()){
            System.out.format("%-25s", result.getString(1));
            System.out.format("%-15s", result.getString(2));
            System.out.format("%-20s", result.getString(3));
            System.out.format("%-20s", result.getString(4));
            System.out.format("%-15s", GetBeautifulPrice(result.getString(5)));
            System.out.format("%-20s", result.getString(6));
            System.out.println();
        }
    }

    private String GetBeautifulPrice(String price){
        int lenStr = price.length() - 1;
        StringBuilder newPrice = new StringBuilder(price);
        while(lenStr > 0) {
            if (price.charAt(lenStr) == '.') {
                while (lenStr > 0) {
                    lenStr -= 3;
                    if (lenStr > 0)
                        newPrice.insert(lenStr, '`');
                    else break;
                }
            } else lenStr--;
        }

        return newPrice.toString();
    }
}
