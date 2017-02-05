package ia;
//Importaciones
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

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
            conexion = DriverManager.getConnection(url, "postgres", "esvb");
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
            System.out.println("ooooooooooooo");
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
        public Object[][] Select_Materia()
    {
     int registros = 0;      
      String consulta = "Select id,nombre,dificultad,tipo FROM public.materias ";
      String consulta2 = "Select count(*) as total from public.materias ";
      //obtenemos la cantidad de registros existentes en la tabla
      try{
         PreparedStatement pstm = conexion.prepareStatement( consulta2 );
         ResultSet res = pstm.executeQuery();
         res.next();
         registros = res.getInt("total");
         res.close();
      }catch(SQLException e){
         System.out.println(e);
      }
    //se crea una matriz con tantas filas y columnas que necesite
    Object[][] data = new String[registros][4];
    //realizamos la consulta sql y llenamos los datos en la matriz "Object"
      try{
         PreparedStatement pstm = conexion.prepareStatement(consulta);
         ResultSet res = pstm.executeQuery();
         int i = 0;
         while(res.next()){            
            data[i][0] = res.getString( "id" );
            data[i][1] = res.getString( "nombre" );
            data[i][2] = res.getString( "dificultad" );
            data[i][3] = res.getString( "tipo" );
            i++;
         }
         res.close();
          }catch(SQLException e){
               System.out.println(e);
        }
    return data;
    }

/* Ejecuta la actualizacion de la tabla persona dado los valores de actualizacion
 * y el ID del registro a afectar
 */
    public boolean update(String valores, String id)
    {
        boolean res = false;        
        String q = " UPDATE public.materias SET " + valores + " WHERE id= " + id;
        try {
            PreparedStatement pstm = conexion.prepareStatement(q);
            pstm.execute();
            pstm.close();
            res=true;
         }catch(SQLException e){            
            System.out.println(e);
        }
        return res;
    }


    /* public static void main(String[] args) throws SQLException {
     PruebaConexion x = new PruebaConexion();
     ResultSet rs = null;
     String cadena = "";

     x.estableceConexion();
     Map valores = new HashMap();
     valores.put("nombre", "jhb");
     valores.put("dificultad", "9");
     valores.put("tipo", "lij");
     boolean u = x.insertRegistro("public", "materias", valores);
     u = x.updateRegistro("public", "materias", 1, valores);
     try {
     while (u) {
     //imprimimos todos los datos contenidos en la tabla
     cadena += rs.getString(1) + ", " + rs.getString(2) + ", " + rs.getString(3) + ", " + rs.getString(4) + " , " + rs.getString(5) + "\n";

     }

     JOptionPane.showMessageDialog(null, cadena, "trabajadores", 1);
     } catch (SQLException | HeadlessException e) {
     System.out.println(e);
     }
     x.cierraConexion();
     }*/
}
