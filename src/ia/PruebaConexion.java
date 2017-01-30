package ia;
//Importaciones
import java.awt.HeadlessException;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JOptionPane;

public class PruebaConexion {

    private Connection conexion = null;

    public PruebaConexion() {
    }

    public void estableceConexion() {
        if (conexion != null) {
            return;
        }
        String url = "jdbc:postgresql://localhost:5432/Iabase";
        try {
            Class.forName("org.postgresql.Driver");
            //Establecemos el Usuario y la contraseña
            conexion = DriverManager.getConnection(url, "postgres", "8326653669a");
            if (conexion != null) {
                System.out.println("Conexión a base de datos ... Ok");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } catch (NoClassDefFoundError e) {
            System.out.println("Error enn NoClassDefFoundError: " + e.getMessage());
        }
    }

    public ResultSet selectRegistro(String SCHEMA, String Table, Map values) {
        ResultSet rs = null;
        Statement s = null;
        String query = "";
        boolean res = false;
        if (values == null) {
            query = "select * from  " + SCHEMA + "." + Table;

        } else {
            Iterator it = values.entrySet().iterator();
            String set = "";

            while (it.hasNext()) {
                Map.Entry e = (Map.Entry) it.next();
                set = set + " and " + e.getKey() + "='" + e.getValue() + "'";

            }
            set = set.replaceFirst("and", " ");

            query = "select * from  " + SCHEMA + "." + Table + " where  " + set;

        }
        System.out.println(query);
        try {
            s = conexion.createStatement();
            rs = s.executeQuery(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return rs;
    }

    public boolean insertRegistro(String SCHEMA, String Table, Map values) {
        Statement s = null;

        boolean res = false;
        Iterator it = values.entrySet().iterator();
        String parameters = "";
        String values_parameters = "";

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            parameters = parameters + "," + e.getKey();
            values_parameters = values_parameters + ",'" + e.getValue() + "'";
        }
        parameters = parameters.replaceFirst(",", "");
        values_parameters = values_parameters.replaceFirst(",", "");
        String query = "INSERT INTO " + SCHEMA + "." + Table + " (" + parameters + ") " + " VALUES(" + values_parameters + ")";
        System.out.println(query);
        try {
            s = conexion.createStatement();
            res = s.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return res;
    }

    public boolean updateRegistro(String SCHEMA, String Table, int idupdate, Map values) {
        Statement s = null;

        boolean res = false;
        Iterator it = values.entrySet().iterator();
        String set = "";

        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            set = set + "," + e.getKey() + "='" + e.getValue() + "'";

        }
        set = set.replaceFirst(",", "");

        String query = "UPDATE " + SCHEMA + "." + Table + " SET " + set + "  where id=" + idupdate;
        System.out.println(query);
        try {
            s = conexion.createStatement();
            res = s.execute(query);
        } catch (SQLException e) {
            System.out.println(e);
        }
        return res;
    }

    public void cierraConexion() {
        try {
            conexion.close();
        } catch (Exception e) {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }

     public static void main(String[] args) throws SQLException {
PruebaConexion con;
            con = new PruebaConexion();
            con.estableceConexion();
            ResultSet rs=null;
            rs=con.selectRegistro("public","materias",null);
            System.out.println(rs);
            String cadena="";
     try {
     while (rs.next()) {
     //imprimimos todos los datos contenidos en la tabla
     cadena += rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + "\n";

     }

     JOptionPane.showMessageDialog(null, cadena, "trabajadores", 1);
     } catch (SQLException | HeadlessException e) {
     System.out.println(e);
     }
     con.cierraConexion();
     }
}
