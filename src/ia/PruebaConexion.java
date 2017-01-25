package IA;
//Importaciones
import java.sql.*;
import javax.swing.JOptionPane;
public class PruebaConexion{
    private Connection conexion = null;
    public void estableceConexion()
    {
        if (conexion != null)
            return;
        String url = "Jdbc:postgresql://localhost:5432/postgres";
        try
        {
           Class.forName("org.postgresql.Driver");
           //Establecemos el Usuario y la contraseña
           //Usuario= postgres
           //Contraseña= 50cent
           conexion = DriverManager.getConnection(url,"postgres","awesome6");
           if (conexion !=null){
               System.out.println("Conexión a base de datos ... Ok");
           }
        }catch(ClassNotFoundException e){
            System.out.println("Error en ClassNotFoundException: " + e.getMessage());
        }
        catch(SQLException e){
            System.out.println("Error en SQLException: " + e.getMessage());
        }
        catch(NoClassDefFoundError e){
            System.out.println("Error enn NoClassDefFoundError: " + e.getMessage());
        }
    }
         public ResultSet dameNombre()
    {
        ResultSet rs = null;
        Statement s = null;
        try
        {
            s = conexion.createStatement();
            //seleccionamos la tabla de la base de datos la cual lleva por nombre trabajadores
            rs = s.executeQuery("SELECT * FROM  trabajadores");
        }catch (Exception e)
        {
            System.out.println("Problema al consultar la base de datos 1 ");
        }
        return rs;
    }
    public void cierraConexion()
    {
        try
        {
            conexion.close();
        }catch(Exception e)
        {
            System.out.println("Problema para cerrar la Conexión a la base de datos ");
        }
    }

    public static void main (String [] args) throws SQLException
    {
        PruebaConexion x = new PruebaConexion() ;
        ResultSet rs = null;
        String cadena="";

        x.estableceConexion();
        rs = x.dameNombre();
        try{
        while (rs.next())
        {
            //imprimimos todos los datos contenidos en la tabla
            cadena += rs.getString (1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + " , " + rs.getString(5)+"\n";


        }

        JOptionPane.showMessageDialog(null, cadena, "trabajadores",1);
        } catch(Exception e)
        {
            System.out.println("Problema al imprimir la base de datos ");
        }
        x.cierraConexion();
    }

}