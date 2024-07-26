package teste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Calendar;


public class conexaoDB {

    Calendar c = Calendar.getInstance();


    public Connection coneDB(String DBname, String user, String pass) {
        
        Connection conn = null;
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(security.getUrl() + DBname, user, pass);
            if (conn != null) {
                System.out.println("Conexão deu certo");
            } else {
                System.out.println("Deu errado");
            }


        } catch (Exception e) {
            System.out.println(e);
        }
        return conn;
    }

    public void create_table(Connection conn, String table_name) {
        Statement statement;
        try {
            String query = "create table " + table_name + "(empid SERIAL, name varchar (200), email text, senha text,saldo float, sobrenome varchar(200), cpf float,  primary key(empid));";
            statement = conn.createStatement();
            statement.executeUpdate(query);
            System.out.println("table created");
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    public void inserir_linha(Connection conn, String name, String email,String senha, String sobrenome, int cpf, String table_name) {
        Statement statement;
        try{
                String query2 = String.format("insert into %s(name,email, senha, sobrenome, cpf, data_cadastro) values('%s', '%s', '%s','%s' , '%d', '%s');", table_name, name, email, senha,sobrenome, cpf, c.getTime());
                statement = conn.createStatement();
                statement.executeUpdate(query2);
                System.out.println("Inseriu");

        }catch (Exception e){
            System.out.println(e);

        }

    }
    public int naoIgual (Connection conn, String table_name, String email, String cpf){
        Statement statement;
        ResultSet rs;

        try{
            String query = String.format("select * from %s", table_name);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                if(email.equals(rs.getString("email")) || cpf.equals(rs.getString("cpf"))){
                    return 1;
                }
                else{
                }
            }
        }catch(Exception e){
            System.out.println(e);

        }
        return 0;
    }
    public String name(Connection conn, String table_name, String email){
        Statement statement;
        ResultSet rs;
        String retornaNome = new String();
        try{
            String query = String.format("select * from %s where email = '%s'", table_name, email);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){

                retornaNome = rs.getString("name");

            }
        }catch(Exception e){
            System.out.println(e);

        }
        return retornaNome;
    }
    public String busca(Connection conn, String table_name, String email){
        Statement statement;
        ResultSet rs;
        String retornaBusca = null;
        try{
            String query = String.format("select senha from %s where email = '%s'", table_name, email);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                retornaBusca = rs.getString("senha");
            }
        }catch(Exception e){
            System.out.println(e);

        }
        return retornaBusca;
    }
    public double saldo(Connection conn, String table_name, String email){
        Statement statement;
        ResultSet rs;
        String retornaBusca;
        double retorno = 0;
        try{
            String query = String.format("select saldo from %s where email = '%s'", table_name, email);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                retornaBusca = rs.getString("saldo");
                retorno = Double.parseDouble(retornaBusca);

            }
        }catch(Exception e){
            System.out.println(e);

        }

        return retorno;
    }
    public void setSaldo(Connection conn, String table_name, int empid, String saldo){
        try {
            Statement statement;
            String query = String.format("update %s set saldo = '%s' where empid = '%d'", table_name, saldo, empid );
            statement = conn.createStatement();
            statement.executeUpdate(query);
            String query2 = String.format("update %s set data_transacao = '%s' where empid = '%d'", table_name,c.getTime(), empid );
            statement.executeUpdate(query2);

        }catch (Exception e){
            System.out.println(e);
        }
    }
    public int empid(Connection conn, String table_name, String email){
        Statement statement;
        ResultSet rs = null;
        String retornaBusca = new String();
        int retorno = 0;
        try{
            String query = String.format("select empid from %s where email = '%s'", table_name, email);
            statement = conn.createStatement();
            rs = statement.executeQuery(query);
            if(rs.next()){
                retornaBusca = rs.getString("empid");
                retorno = Integer.parseInt(retornaBusca);

            }
        }catch(Exception e){
            System.out.println(e);

        }

        return retorno;
    }
}