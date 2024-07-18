package lects.lect10;

import java.io.*;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DBApp {
    private static final String DBMS = "sqlite";
    private static final String SQL_CREATE_TABLES = "create_tables.sql";
    private static final String SQL_POPULATE_TABLES = "populate_tables.sql";
    private static final String SQL_QUERY_TABLES = "queries.sql";
    protected Connection conn;

    // application main method
    public static void main(String[] args) {
        DBApp dba = new DBApp();

        // general tasks: preparation
        try {
            dba.connect("coffee.sqlite3");
            dba.createTables();
            dba.populateTables();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }

        // application specific tasks
        try {
            dba.queryTables();
        } catch (Exception e) {
            e.printStackTrace();
        }

        // general tasks: close
        try {
            dba.close();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    /**
     * @effects Returns a new <code>Connection</code> object to database
     * <code>dbName</code> on disk. Database <code>dbName</code> is
     * created if not yet exists.
     * @modifies <code>this</code>
     */
    public void connect(String dbName) throws SQLException {
        conn = DriverManager.getConnection("jdbc:" + DBMS + ":" + dbName);
        System.out.println("Connected to database " + dbName);
    }

    /**
     * @effects Executes each statement in <code>this.SQL_CREATE_TABLES</code> to
     * create tables in the database connected to by
     * <code>this.conn</code>, throwing <code>SQLException</code> if an
     * error occurred.
     */
    public void createTables() throws SQLException {
        executeStatementsFromFile(SQL_CREATE_TABLES, null);
    }

    /**
     * @effects Executes each statement in <code>this.SQL_POPULATE_TABLES</code>
     * to insert data into each table in the database connected to by
     * <code>this.conn</code>, throwing <code>SQLException</code> if an
     * error occurred.
     */
    public void populateTables() throws SQLException {
        executeStatementsFromFile(SQL_POPULATE_TABLES, null);
    }

    /**
     * @effects Executes each statement in <code>this.SQL_QUERY_TABLES</code> to
     * query data from each table in the database connected to by
     * <code>this.conn</code>, throwing <code>SQLException</code> if an
     * error occurred.
     */
    public void queryTables() throws SQLException {
        Map<String, String> resultMap = new HashMap<String, String>();
        executeStatementsFromFile(SQL_QUERY_TABLES, resultMap);
        for (Entry<String, String> e : resultMap.entrySet()) {
            System.out.println("Query: \n" + e.getKey());
            System.out.println("Result:");
            System.out.println(e.getValue());
        }
    }

    /**
     * @effects perform SQL statement <tt>SELECT * from tableName</tt> and
     * return a <tt>ResultSet</tt> object if succeeds (never <tt>null</tt>), or
     * throw <tt>SQLException</tt> if an error occurred.
     */
    public ResultSet queryTable(String tableName) throws SQLException {
        String sql = "Select * from " + tableName;
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        return rs;
    }

    /**
     * @effects drop table <tt>tableName</tt> from the database, or
     * throw <tt>SQLException</tt> if fail
     */
    public void dropTable(String tableName) throws SQLException {
        String sql = "drop table " + tableName;
        Statement s = conn.createStatement();
        s.executeUpdate(sql);
    }

    /**
     * @effects Executes each statement in <code>fname</code> to over the database
     * connected to by <code>this.conn</code>, throwing
     * <code>SQLException</code> if an error occured.
     * @modifies if <code>resultMap != null</code> and there are result sets then
     * adds
     *
     * <pre><sql,ResultSet></pre>
     * <p>
     * entries to <code>resultMap</code>
     */
    private void executeStatementsFromFile(String fname,
                                           Map<String, String> resultMap) throws SQLException {
        System.out.println("------ Executing " + fname + " ------");

        URL fileIn = DBApp.class.getResource(fname);
        if (fileIn != null) {
            String sql = null;

            Statement s = null;
            try {

                BufferedReader in = new BufferedReader(new FileReader(new File(
                        fileIn.getPath())));
                s = conn.createStatement();
                StringBuffer sb = new StringBuffer();
                ResultSet rs = null;
                while ((sql = in.readLine()) != null) {
                    sql = sql.trim();
                    sb.append(sql);

                    if (sql.endsWith(";")) {
                        sql = sb.toString();
                        System.out.println("-> Statement: \n" + sql);
                        try {
                            s.execute(sql.substring(0, sql.length() - 1));
                            // if result map is specified...
                            if (resultMap != null) {
                                rs = s.getResultSet();
                                if (rs != null) {
                                    resultMap.put(sql, resultSetToString(rs));
                                }
                            }
                        } catch (SQLException e) {
                            System.out.println(e);
                        }
                        sb = new StringBuffer();
                    } else {
                        sb.append("\n");
                    }
                }
            } catch (FileNotFoundException e) {
                // should not happen
            } catch (IOException e) {
                // should not happen
                System.err.println("Faild to read file " + fname + ": " + e);
            } finally {
                if (s != null)
                    s.close();
            }

        } else {
            System.err.println("File " + fname + " does not exist");
        }
    }

    /**
     * @effects Executes <code>sql</code> over the database connected to by
     * <code>this.conn</code>. If successful then returns a string
     * representation of <code>ResultSet</code> object, otherwise throws
     * <code>SQLException</code>.
     */
    protected String executeQuery(String sql) throws SQLException {
        Statement s = conn.createStatement();
        ResultSet rs = s.executeQuery(sql);
        String rss = resultSetToString(rs);
        s.close();
        return rss;
    }

    /**
     * @effects Return a string containing a sequence of rows of <tt>rs</tt>, one per line,
     * or return <tt>empty</tt> if nio rows are found.
     */
    protected String resultSetToString(ResultSet rs) throws SQLException {
        ResultSetMetaData meta = rs.getMetaData();
        int cols = meta.getColumnCount();

        Object v;
        StringBuffer sb = new StringBuffer();
        int size = 0;
        int colType;
        String vstr;
        while (rs.next()) {
            size++;
            for (int i = 1; i <= cols; i++) {
                v = rs.getObject(i);
                colType = meta.getColumnType(i);
                vstr = toString(v, colType);
                sb.append(vstr);
                if (i < cols)
                    sb.append(",");
            }
            sb.append("\n");
        }

        if (size == 0) {
            return "Empty";
        } else {
            return sb.toString();
        }
    }

    /**
     * @effects convert <tt>v</tt> to a <tt>String</tt> based on the column type <tt>colType</tt>
     */
    protected String toString(Object v, int colType) {
        if (v == null)
            return "null";
        return v.toString();
    }

    /**
     * @effects Closes <code>this.conn</code>
     */
    public void close() {
        try {
            this.conn.close();
        } catch (SQLException e) {
            // ignore
        }
    }
}
