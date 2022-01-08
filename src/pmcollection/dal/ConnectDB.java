package pmcollection.dal;

import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.util.Properties;

public class ConnectDB {
    private final SQLServerDataSource ds;

    public ConnectDB() throws IOException
    {
        ds = new SQLServerDataSource();
        ds.setServerName(props.getProperty("SERVER"));
        ds.setDatabaseName(props.getProperty("DATABASE"));
        ds.setPortNumber(Integer.parseInt(props.getProperty("PORT")));
        ds.setUser(props.getProperty("USER"));
        ds.setPassword(props.getProperty("PASSWORD"));
    }

    public Connection getConnection() throws SQLServerException
    {
        return ds.getConnection();
    }
}
