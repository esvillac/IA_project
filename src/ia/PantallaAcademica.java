/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Container;
import java.awt.HeadlessException;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import jess.Fact;
import jess.JessException;
import jess.Rete;

public class PantallaAcademica extends JFrame implements ActionListener {

    private Container contenedor;/*declaramos el contenedor*/

    private JButton agregar, siguiente, agregarMateriasSemestre, agregarMateriasReprobadas, agregarMateriasDeseables;
    private JLabel mensaje, agregarPromedio, materias_d, materias_r, materias_s;/*declaramos el objeto Label*/

    private JTextField campo, promedio;
    private JList listaMaterias, listaMateriasSemetresAnterior, listaMateriasReprobadas, listaMateriasDeseables;
    private DefaultListModel modelo, modeloReprobadas, modeloSemestre, modeloDeseables;/*declaramos el Modelo*/

    private JScrollPane scrollLista, scrollLista2, scrollLista3, scrollLista4;
    private JPopupMenu jPopupMenu1;
    private int indexMenuItem = 0;
    private JMenuItem eliminarMateriaItem;
    private String materiaElimnarSelec;
    private HashMap lista_materias_old = new HashMap();
    private HashMap lista_materias_new = new HashMap();

    public PantallaAcademica() {
        /*permite iniciar las propiedades de los componentes*/
        iniciarComponentes();
        /*Asigna un titulo a la barra de titulo*/
        setTitle("Actividad Academica");
        /*tamaño de la ventana*/
        setSize(800, 550);
        /*pone la ventana en el Centro de la pantalla*/
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void iniciarComponentes() {
        contenedor = getContentPane();/*instanciamos el contenedor*/
        /*con esto definmos nosotros mismos los tamaños y posicion
         * de los componentes*/

        contenedor.setLayout(null);
        campo = new JTextField();
        campo.setBounds(20, 80, 135, 23);
        agregar = new JButton();
        agregar.setText("Agregar materia");
        agregar.setBounds(160, 80, 150, 23);
        siguiente = new JButton();
        siguiente.setText("Recomendacion");
        siguiente.setBounds(580, 440, 150, 23);
        agregar.addActionListener(this);
        siguiente.addActionListener(this);
        agregarPromedio = new JLabel("Ingresar promedio semestral");
        materias_d = new JLabel("Materias que podria coger");
        materias_r = new JLabel("Materias reprobadas en semestre anterior");
        materias_s = new JLabel("Materias registradas en semestre anterior");

        materias_d.setBounds(420, 50, 280, 23);
        materias_r.setBounds(420, 225, 280, 23);
        materias_s.setBounds(20, 300, 280, 23);
        agregarPromedio.setBounds(150, 20, 280, 23);
        promedio = new JTextField();
        promedio.setBounds(380, 20, 135, 23);

        agregarMateriasSemestre = new JButton();
        agregarMateriasSemestre.setText("+");
        agregarMateriasSemestre.setBounds(20, 270, 50, 23);
        agregarMateriasSemestre.addActionListener(this);

        agregarMateriasDeseables = new JButton();
        agregarMateriasDeseables.setText("+");
        agregarMateriasDeseables.setBounds(310, 120, 50, 23);
        agregarMateriasDeseables.addActionListener(this);

        agregarMateriasReprobadas = new JButton();
        agregarMateriasReprobadas.setText("+");
        agregarMateriasReprobadas.setBounds(310, 245, 50, 23);
        agregarMateriasReprobadas.addActionListener(this);
        mensaje = new JLabel();
        mensaje.setBounds(20, 250, 280, 23);

        //instanciamos la lista
        listaMaterias = new JList();
        PruebaConexion con;
        con = new PruebaConexion();
        con.estableceConexion();
        ResultSet rs = null;
        rs = con.selectRegistro("public", "materias", null);
        System.out.println(rs);
        lista_materias_old = new HashMap();
        try {
            while (rs.next()) {
                lista_materias_old.put(rs.getString(2), rs.getString(1) + ", " + rs.getString(4) + ", " + rs.getString(3));
                
            }
            System.out.println(lista_materias_old.toString());

        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        con.cierraConexion();
        setmaterias(lista_materias_old);
        listaMaterias.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                ArrayList<String> mensajes = null;
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 1) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        materiaElimnarSelec = "listaMaterias";
                        int index = list.locationToIndex(evt.getPoint());
                        String nombre = listaMaterias.getModel().getElementAt(index).toString();
                        System.out.println(nombre);
                        jPopupMenu1.show(list, evt.getX(), evt.getY());
                        indexMenuItem = index;

                    }
                }
            }
        });
        jPopupMenu1 = new javax.swing.JPopupMenu();
        jPopupMenu1.setBackground(new java.awt.Color(0, 51, 51));
        eliminarMateriaItem = new javax.swing.JMenuItem();
        ///eliminarMateriaItem.setBackground(new java.awt.Color(0, 51, 51));
        eliminarMateriaItem.setFont(new java.awt.Font("Segoe UI", 1, 15)); // NOI18N
        eliminarMateriaItem.setForeground(java.awt.Color.black);
        eliminarMateriaItem.setText("Eliminar materia");
        eliminarMateriaItem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMateriaItemActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminarMateriaItem);

        listaMateriasSemetresAnterior = new JList();
        listaMateriasSemetresAnterior.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                ArrayList<String> mensajes = null;
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 1) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        materiaElimnarSelec = "listaMateriasSemetresAnterior";
                        int index = list.locationToIndex(evt.getPoint());
                        String nombre = listaMateriasSemetresAnterior.getModel().getElementAt(index).toString();
                        System.out.println(nombre);
                        jPopupMenu1.show(list, evt.getX(), evt.getY());
                        indexMenuItem = index;

                    }
                }
            }
        });

        listaMateriasDeseables = new JList();
        listaMateriasDeseables.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 1) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        materiaElimnarSelec = "listaMateriasDeseables";
                        int index = list.locationToIndex(evt.getPoint());
                        String nombre = listaMateriasDeseables.getModel().getElementAt(index).toString();
                        System.out.println(nombre);
                        jPopupMenu1.show(list, evt.getX(), evt.getY());
                        indexMenuItem = index;

                    }
                }
            }
        });

        listaMateriasReprobadas = new JList();
        listaMateriasReprobadas.addMouseListener(new MouseAdapter() {

            public void mouseClicked(MouseEvent evt) {
                JList list = (JList) evt.getSource();
                if (evt.getClickCount() == 1) {
                    if (evt.getButton() == MouseEvent.BUTTON3) {
                        materiaElimnarSelec = "listaMateriasReprobadas";
                        int index = list.locationToIndex(evt.getPoint());
                        String nombre = listaMateriasReprobadas.getModel().getElementAt(index).toString();
                        System.out.println(nombre);
                        jPopupMenu1.show(list, evt.getX(), evt.getY());
                        indexMenuItem = index;

                    }
                }
            }
        });

        //instanciamos el modelo
        modelo = new DefaultListModel();
        modeloSemestre = new DefaultListModel();
        modeloDeseables = new DefaultListModel();
        modeloReprobadas = new DefaultListModel();
        //instanciamos el Scroll que tendra la lista
        scrollLista = new JScrollPane();
        scrollLista2 = new JScrollPane();
        scrollLista3 = new JScrollPane();
        scrollLista4 = new JScrollPane();
        scrollLista.setBounds(20, 120, 290, 150);
        scrollLista.setViewportView(listaMaterias);
        scrollLista2.setBounds(20, 325, 240, 150);
        scrollLista2.setViewportView(listaMateriasSemetresAnterior);
        scrollLista3.setBounds(420, 75, 240, 150);
        scrollLista3.setViewportView(listaMateriasDeseables);
        scrollLista4.setBounds(420, 250, 240, 150);
        scrollLista4.setViewportView(listaMateriasReprobadas);
        Color colorVerde = new Color(180, 233, 163);
        /*Agregamos los componentes al Contenedor*/
        contenedor.add(campo);
        contenedor.add(agregar);
        contenedor.add(promedio);
        contenedor.add(agregarPromedio);
        contenedor.add(agregarMateriasSemestre);
        contenedor.add(agregarMateriasDeseables);
        contenedor.add(agregarMateriasReprobadas);
        contenedor.add(siguiente);
        contenedor.add(mensaje);
        contenedor.add(materias_d);
        contenedor.add(materias_r);
        contenedor.add(materias_s);
        contenedor.add(scrollLista);
        contenedor.add(scrollLista2);
        contenedor.add(scrollLista3);
        contenedor.add(scrollLista4);
        contenedor.setBackground(colorVerde);

    }

    public void setmaterias(HashMap lista_materias) {
        TextAutoCompleter t = new TextAutoCompleter(campo);
        Iterator it = lista_materias.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            t.addItem((String) e.getKey());
        }

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == agregar) {
            if (lista_materias_old.containsKey(campo.getText())) {
                agregarNombre();
                mensaje.setText("Se agregó un nuevo elemento");
            } else {

                lista_materias_new.put(campo.getText(), "4,Si,Si,F");
                agregarNombre();
                mensaje.setText("Materia nueva mas datos");
            }
        }

        if (evento.getSource() == agregarMateriasSemestre) {
            if (listaMaterias.getSelectedIndex() != -1) {
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if (listaMateriasSemetresAnterior.getModel().getSize() != 0) {
                    for (int i = 0; i < listaMateriasSemetresAnterior.getModel().getSize(); i++) {
                        Object item = listaMateriasSemetresAnterior.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if (item.equals(materiaSeleccionada)) {
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if (existe == false) {
                        modeloSemestre.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasSemetresAnterior.setModel(modeloSemestre);

                    }

                } else {
                    modeloSemestre.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasSemetresAnterior.setModel(modeloSemestre);
                }
            }

        }
        if (evento.getSource() == agregarMateriasReprobadas) {
            if (listaMaterias.getSelectedIndex() != -1) {
                //ListModel materiasReprobadas = listaMateriasReprobadas.getModel();
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if (listaMateriasReprobadas.getModel().getSize() != 0) {
                    for (int i = 0; i < listaMateriasReprobadas.getModel().getSize(); i++) {
                        Object item = listaMateriasReprobadas.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if (item.equals(materiaSeleccionada)) {
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if (existe == false) {
                        modeloReprobadas.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasReprobadas.setModel(modeloReprobadas);

                    }

                } else {
                    modeloReprobadas.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasReprobadas.setModel(modeloReprobadas);
                }
            }

        }
        if (evento.getSource() == agregarMateriasDeseables) {

            if (listaMaterias.getSelectedIndex() != -1) {
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if (listaMateriasDeseables.getModel().getSize() != 0) {
                    for (int i = 0; i < listaMateriasDeseables.getModel().getSize(); i++) {
                        Object item = listaMateriasDeseables.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if (item.equals(materiaSeleccionada)) {
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if (existe == false) {
                        modeloDeseables.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasDeseables.setModel(modeloDeseables);

                    }

                } else {
                    modeloDeseables.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasDeseables.setModel(modeloDeseables);
                }

            }

        }
        if (evento.getSource() == siguiente) {
            PruebaConexion x = new PruebaConexion();
            ResultSet rs = null;
            String cadena = "";
            x.estableceConexion();

            Rete jess = new Rete();
            this.setVisible(false);
            HashMap lista_materias_escogidas = new HashMap();
            HashMap lista_materias_escogidas_new = new HashMap();
            for (int i = 0; i < listaMateriasDeseables.getModel().getSize(); i++) {
                String item = (String) listaMateriasDeseables.getModel().getElementAt(i);
                System.out.println("Item = " + item);
                if (lista_materias_old.containsKey(item)) {
                    lista_materias_escogidas.put(item, lista_materias_old.get(item));
                } else if (lista_materias_new.containsKey(item)) {
                    lista_materias_escogidas_new.put(item, lista_materias_new.get(item));

                } else {
                    System.out.println("error en parseo de valores");
                }
            }
            HashMap lista_materias_repro = new HashMap();
            for (int i = 0; i < listaMateriasReprobadas.getModel().getSize(); i++) {
                String item = (String) listaMateriasReprobadas.getModel().getElementAt(i);
                Map valores = new HashMap();
                valores.put("nombre", item);
                ResultSet rss = null;
                rss = x.selectRegistro("public", "materias_reprobadas", valores);
                try {
                    boolean ban = true;
                    while (rss.next()) {
                        ban = false;
                        Map valoretem = new HashMap();
                        valoretem.put("numero", Integer.parseInt(rss.getString(2)) + 1);
                        int id = Integer.parseInt(rss.getString(1));
                        x.updateRegistro("public", "materias_reprobadas", id, valoretem);
                        lista_materias_repro.put(item, Integer.parseInt(rss.getString(2)) + 1);
                    }
                    if (ban) {
                        Map valoretem = new HashMap();
                        valoretem.put("numero", 1);
                        valoretem.put("nombre", item);
                        x.insertRegistro("public", "materias_reprobadas", valoretem);
                        lista_materias_repro.put(item, 1);

                    }
                } catch (SQLException e) {
                    System.out.println(e);
                    System.out.println("kkk.k");
                }
            }
            try {
                jess.batch("..//template//templates.clp");
                jess.batch("..//rules//reglas_peso_materia_nueva.clp");
                Iterator ite = lista_materias_escogidas_new.entrySet().iterator();
                jess.reset();
                while (ite.hasNext()) {
                    Map.Entry e = (Map.Entry) ite.next();
                    String val = (String) e.getValue();
                    String values[] = val.split(",");
                    String asserts = "(Materia (NombreMat " + e.getKey() + ")" + "(Creditos " + values[0] + ")" + "(Flujo " + values[1] + ")" + "(Proyecto " + values[2] + ")" + "(Tipo " + values[3] + "))";
                    System.out.println(asserts);
                    jess.assertString(asserts);

                }
                jess.run();
                Iterator it = jess.listFacts();
                while (it.hasNext()) {
                    Fact dd = (Fact) it.next();
                    String nombre = dd.getName();

                    if (nombre.contains("Materia_Peso")) {
                        System.out.println(dd.toString());
                        Map valores = new HashMap();
                        valores.put("nombre", dd.getSlotValue("NombreMat"));
                        valores.put("dificultad", dd.getSlotValue("Peso"));
                        valores.put("tipo", dd.getSlotValue("Tipo"));
                        boolean u = false;
                        u = x.insertRegistro("public", "materias", valores);
                        lista_materias_escogidas.put(dd.getSlotValue("NombreMat"), "1," + dd.getSlotValue("Peso") + ',' + dd.getSlotValue("Tipo"));

                    }
                }
                jess.clear(); 
                jess.batch("..//template//templates.clp");
                jess.batch("..//rules//reglas_peso_final.clp");
                Iterator iteold = lista_materias_escogidas.entrySet().iterator();
                jess.reset();
                while (iteold.hasNext()) {
                    Map.Entry e = (Map.Entry) iteold.next();
                    String val = (String) e.getValue();
                    String values[] = val.split(",");
                    String asserts = "(Materias_Posibles (NombreMat " + e.getKey() + ")" + "(Peso " + values[1] + ")" + "(Tipo " + values[2] + "))";
                    System.out.println(asserts);
                    jess.assertString(asserts);

                }
                Iterator iterep = lista_materias_repro.entrySet().iterator();
                while (iterep.hasNext()) {
                    Map.Entry e = (Map.Entry) iterep.next();
                    String val = e.getValue().toString();
                    String values[] = val.split(",");
                    String asserts = "(Materia_Repro (NombreMat " + e.getKey() + ")" + "(Numero " + values[0] + "))";
                    System.out.println(asserts);
                    jess.assertString(asserts);
                }
                int numeroest = 0;
                ResultSet rss = x.selectRegistro("public", "numero_estudiantes", null);
                try {
                    boolean ban = true;
                    while (rss.next()) {
                        ban = false;
                        Map valoretem = new HashMap();
                        valoretem.put("numero", Integer.parseInt(rss.getString(2)) + 1);
                        int id = Integer.parseInt(rss.getString(1));
                        x.updateRegistro("public", "numero_estudiantes", id, valoretem);
                        numeroest = Integer.parseInt(rss.getString(2)) + 1;
                    }
                    if (ban) {
                        Map valoretem = new HashMap();
                        valoretem.put("numero", 1);
                        x.insertRegistro("public", "numero_estudiantes", valoretem);
                        numeroest = 1;

                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                String asserts = "(Numero_Estudiantes (Numero " + numeroest + "))";
                System.out.println(asserts);
                jess.assertString(asserts);
                jess.run();
                it = jess.listFacts();
                while (it.hasNext()) {
                    Fact dd = (Fact) it.next();
                    String nombre = dd.getName();
                    System.out.println(dd.toString());

                }

            } catch (JessException ex) {
                Logger.getLogger(PantallaAcademica.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        //ventanaActExtra.setVisible(true);

    }

    private void agregarNombre() {
        String nombre = campo.getText();
        modelo.addElement(nombre);
        listaMaterias.setModel(modelo);
        campo.setText("");
    }

    private void eliminarNombre(int indice) {
        if (indice >= 0) {
            modelo.removeElementAt(indice);
            mensaje.setText("Se eliminó un elemento en la posición " + indice);
        } else {
            JOptionPane.showMessageDialog(null, "Debe seleccionar un indice", "Error", JOptionPane.ERROR_MESSAGE);

            mensaje.setText("NO se seleccionó ningún elemento");
        }

    }

    private void borrarLista() {
        modelo.clear();
    }

    private void eliminarMateriaItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (materiaElimnarSelec.equals("listaMaterias")) {
            ((DefaultListModel) listaMaterias.getModel()).removeElementAt(indexMenuItem);
        }
        if (materiaElimnarSelec.equals("listaMateriasDeseables")) {
            ((DefaultListModel) listaMateriasDeseables.getModel()).removeElementAt(indexMenuItem);
        }
        if (materiaElimnarSelec.equals("listaMateriasReprobadas")) {
            ((DefaultListModel) listaMateriasReprobadas.getModel()).removeElementAt(indexMenuItem);
        }
        if (materiaElimnarSelec.equals("listaMateriasSemetresAnterior")) {
            ((DefaultListModel) listaMateriasSemetresAnterior.getModel()).removeElementAt(indexMenuItem);
        }
    }

}
