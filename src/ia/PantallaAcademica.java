/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import java.awt.Color;
import java.awt.Container;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

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


public class PantallaAcademica extends JFrame implements ActionListener {

    private Container contenedor;/*declaramos el contenedor*/
    private JButton agregar,siguiente,agregarMateriasSemestre, agregarMateriasReprobadas, agregarMateriasDeseables;
    private JLabel mensaje, agregarPromedio;/*declaramos el objeto Label*/
    private JTextField campo, promedio;
    private JList listaMaterias, listaMateriasSemetresAnterior, listaMateriasReprobadas, listaMateriasDeseables;
    private DefaultListModel modelo,modeloReprobadas,modeloSemestre,modeloDeseables;/*declaramos el Modelo*/
    private JScrollPane scrollLista, scrollLista2, scrollLista3, scrollLista4;
    private JPopupMenu jPopupMenu1;
    private int indexMenuItem = 0;
    private JMenuItem eliminarMateriaItem;
    private String materiaElimnarSelec;

    public PantallaAcademica() {
        /*permite iniciar las propiedades de los componentes*/
        iniciarComponentes();
        /*Asigna un titulo a la barra de titulo*/
        setTitle("Actividad Academica");
        /*tamaño de la ventana*/
        setSize(800, 500);
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
        agregar.setText("Agregar");
        agregar.setBounds(160, 80, 100, 23);
        siguiente= new JButton();
        siguiente.setText("Siguiente");
        siguiente.setBounds(580, 440, 150, 23);
        agregar.addActionListener(this);
        siguiente.addActionListener(this);
        agregarPromedio = new JLabel("Ingresar promedio semestral");
        agregarPromedio.setBounds(150, 20, 280, 23);
        promedio = new JTextField();
        promedio.setBounds(380, 20, 135, 23);

        agregarMateriasSemestre = new JButton();
        agregarMateriasSemestre.setText("+");
        agregarMateriasSemestre.setBounds(20, 270, 100, 23);
        agregarMateriasSemestre.addActionListener(this);
        

        agregarMateriasDeseables = new JButton();
        agregarMateriasDeseables.setText("+");
        agregarMateriasDeseables.setBounds(260, 120, 120, 23);
        agregarMateriasDeseables.addActionListener(this);

        agregarMateriasReprobadas = new JButton();
        agregarMateriasReprobadas.setText("+");
        agregarMateriasReprobadas.setBounds(260, 245, 120, 23);
        agregarMateriasReprobadas.addActionListener(this);
        mensaje = new JLabel();
        mensaje.setBounds(20, 250, 280, 23);

        //instanciamos la lista
        listaMaterias = new JList();
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
        scrollLista.setBounds(20, 120, 240, 150);
        scrollLista.setViewportView(listaMaterias);
        scrollLista2.setBounds(20, 340, 240, 150);
        scrollLista2.setViewportView(listaMateriasSemetresAnterior);
        scrollLista3.setBounds(420, 60, 240, 150);
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
        contenedor.add(scrollLista);
        contenedor.add(scrollLista2);
        contenedor.add(scrollLista3);
        contenedor.add(scrollLista4);
        contenedor.setBackground(colorVerde);

    }

    @Override
    public void actionPerformed(ActionEvent evento) {
        if (evento.getSource() == agregar) {
            agregarNombre();
            mensaje.setText("Se agregó un nuevo elemento");
        }
        if (evento.getSource() == agregarMateriasSemestre) {
            if(listaMaterias.getSelectedIndex() !=-1){
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if(listaMateriasSemetresAnterior.getModel().getSize()!=0){
                    for (int i = 0; i < listaMateriasSemetresAnterior.getModel().getSize(); i++) {
                        Object item = listaMateriasSemetresAnterior.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if(item.equals(materiaSeleccionada)){
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if(existe==false){
                        modeloSemestre.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasSemetresAnterior.setModel(modeloSemestre);

                    }

                }else{
                    modeloSemestre.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasSemetresAnterior.setModel(modeloSemestre);
                }
            }
     
            

        }        
        if (evento.getSource() == agregarMateriasReprobadas) {
            if(listaMaterias.getSelectedIndex() !=-1){
                //ListModel materiasReprobadas = listaMateriasReprobadas.getModel();
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if(listaMateriasReprobadas.getModel().getSize()!=0){
                    for (int i = 0; i < listaMateriasReprobadas.getModel().getSize(); i++) {
                        Object item = listaMateriasReprobadas.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if(item.equals(materiaSeleccionada)){
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if(existe==false){
                        modeloReprobadas.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasReprobadas.setModel(modeloReprobadas);

                    }

                }else{
                    modeloReprobadas.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasReprobadas.setModel(modeloReprobadas);
                }
            }
            

        }
        if (evento.getSource() == agregarMateriasDeseables) {
            if(listaMaterias.getSelectedIndex() !=-1){
                boolean existe = false;
                String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
                if(listaMateriasDeseables.getModel().getSize()!=0){
                    for (int i = 0; i < listaMateriasDeseables.getModel().getSize(); i++) {
                        Object item = listaMateriasDeseables.getModel().getElementAt(i);
                        System.out.println("Item = " + item);
                        if(item.equals(materiaSeleccionada)){
                            System.out.println("Ya existe");
                            existe = true;
                            break;
                        }
                    }
                    if(existe==false){
                        modeloDeseables.addElement(listaMaterias.getSelectedValue().toString());
                        listaMateriasDeseables.setModel(modeloDeseables);

                    }

                }else{
                    modeloDeseables.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasDeseables.setModel(modeloDeseables);
                }

            }
            
        }
        if (evento.getSource() == siguiente) {
            this.setVisible(false);
            PantallaActExtracurricular ventanaActExtra = new PantallaActExtracurricular();
            ventanaActExtra.setVisible(true);
        }
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
        if(materiaElimnarSelec.equals("listaMaterias"))
            ((DefaultListModel) listaMaterias.getModel()).removeElementAt(indexMenuItem);
        if(materiaElimnarSelec.equals("listaMateriasDeseables"))
            ((DefaultListModel) listaMateriasDeseables.getModel()).removeElementAt(indexMenuItem);
        if(materiaElimnarSelec.equals("listaMateriasReprobadas"))
            ((DefaultListModel) listaMateriasReprobadas.getModel()).removeElementAt(indexMenuItem);
        if(materiaElimnarSelec.equals("listaMateriasSemetresAnterior"))
            ((DefaultListModel) listaMateriasSemetresAnterior.getModel()).removeElementAt(indexMenuItem);
    }  

}
