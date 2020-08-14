package giangnth.db;


import java.io.Serializable;
import java.sql.Connection;
import org.apache.log4j.Logger;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Dell
 */

public class MyConnection implements Serializable{
    static Logger log = Logger.getLogger(MyConnection.class.getName());
    public static Connection getConnection() throws Exception{
        Context context = new InitialContext();
        Context tomContext = (Context) context.lookup("java:comp/env");
        DataSource ds = (DataSource) tomContext.lookup("Se140109");
        Connection cnn = ds.getConnection();
        return cnn;
    }
    
    public static void main(String[] args) {
        log.debug("This is debug");
    }
}
