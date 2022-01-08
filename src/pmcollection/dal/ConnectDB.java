package pmcollection.dal;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;

public class ConnectDB {
    private final SQLServerDataSource ds;

    public ConnectDB() throws IOException
    {
        ds = new SQLServerDataSource();
        ds.setServerName("10.176.111.31");
        ds.setDatabaseName("PMCollection_TeamRMA");
        ds.setPortNumber(1433);
        ds.setUser("CSe21B_1");
        ds.setPassword("CSe21B_1");
    }

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}
