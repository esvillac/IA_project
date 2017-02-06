/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

/**
 *
 * @author edgar-pc
 */
public class IA {

    /**
     * @param args the command line arguments
     */
    static String user="postgres";
    static String pass="root";
    public static void main(String[] args) {
        /*Declaramos el objeto*/
        if(args!=null){
        user=args[0];
        pass=args[1];
        }
		PantallaPrincipal miVentanaPrincipal;
		/*Instanciamos el objeto*/
		miVentanaPrincipal= new PantallaPrincipal();
		/*Hacemos que se cargue la ventana*/
		miVentanaPrincipal.setVisible(true);
    }
    
}
