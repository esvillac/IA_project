/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ia;

import ia.PantallaActExtracurricular.buttonListener;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.color.CMMException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;

import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.Timer;
import javax.swing.UIManager;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import jess.Fact;
import jess.JessException;
import jess.Rete;

public class PantallaAcademica extends JFrame implements ActionListener {

    private Container contenedor;/*declaramos el contenedor*/

    private JButton agregar, siguiente, atras, agregarMateriasSemestre, agregarMateriasReprobadas, agregarMateriasDeseables;
    private JLabel mensaje, agregarPromedio, materias_d, materias_r, materias_s, filtrar_materia;/*declaramos el objeto Label*/

    private JTextField campo, promedio;
    private JList listaMaterias, listaMateriasSemetresAnterior, listaMateriasReprobadas, listaMateriasDeseables;
    private DefaultListModel modelo, modeloReprobadas, modeloSemestre, modeloDeseables;/*declaramos el Modelo*/

    private JTextField nombreMatPopUp;
    private JScrollPane scrollLista, scrollLista2, scrollLista3, scrollLista4;
    private JPopupMenu jPopupMenu1;
    private int indexMenuItem = 0;
    private JMenuItem eliminarMateriaItem;
    private String materiaElimnarSelec;
    private HashMap lista_materias_old = new HashMap();
    private HashMap lista_materias_new = new HashMap();
    private HashMap lista_veces_mat_rep = new HashMap();
    private HashMap lista_materias_seleccionadas = new HashMap();
    private Map valoresMatNueva = new HashMap();//Materia recien agregada a la base
    private Map materiaNuevaActual = new HashMap();
    JRadioButton jRB_si_flujo = new JRadioButton("SI");
    JRadioButton jRB_no_flujo = new JRadioButton("NO");
    JRadioButton jRB_si_proyecto = new JRadioButton("SI");
    JRadioButton jRB_no_proyecto = new JRadioButton("NO");
    JComboBox<String> comboTipos;
    boolean bandDisplay = false;
    //boolean bandRepro = false;
    public static String formativas;
    public static String optativas;
    public static String libreOp;
    public static ArrayList<String> listadoMaterias;
    public static ArrayList<String> materiasResultFormativa;
    public static ArrayList<String> materiasResultOptativa;
    public static ArrayList<String> materiasResultLibreOpc;
    private JButton jButton2;

    public PantallaAcademica() {
        /*permite iniciar las propiedades de los componentes*/
        iniciarComponentes();
        /*Asigna un titulo a la barra de titulo*/
        setTitle("Actividad Academica");
        /*tamaño de la ventana*/
        setSize(840, 550);
        /*pone la ventana en el Centro de la pantalla*/
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    private void iniciarComponentes() {
        jButton2 = new javax.swing.JButton();
        contenedor = getContentPane();/*instanciamos el contenedor*/
        /*con esto definmos nosotros mismos los tamaños y posicion
         * de los componentes*/

        setUndecorated(true);
        contenedor.setLayout(null);
        filtrar_materia = new JLabel();
        filtrar_materia.setBounds(20, 25, 150, 20);
        filtrar_materia.setText("Filtrar materia: ");
        setIconImage(getIconImage());
        campo = new JTextField();
        campo.setBounds(20, 50, 135, 23);
        agregar = new JButton();
        agregar.setText("Nueva materia");
        agregar.setBounds(210, 50, 150, 23);
        siguiente = new JButton();
        siguiente.setText("Recomendar");
        siguiente.setFont(new java.awt.Font("Tahoma", 1, 18));
        siguiente.setBounds(460, 470, 180, 23);

        atras = new JButton();
        atras.setText("AtráS");
        atras.setFont(new java.awt.Font("Tahoma", 1, 18));
        atras.setBounds(165, 470, 150, 23);

        jButton2.setBackground(new java.awt.Color(0, 153, 0));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setText("Salir");
        jButton2.setFocusPainted(false);
        jButton2.setBounds(750, 0, 90, 33);
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {

                System.exit(0);

            }
        });

        agregar.addActionListener(this);
        siguiente.addActionListener(this);
        atras.addActionListener(this);
        //agregarPromedio = new JLabel("Ingresar promedio semestral");
        materias_d = new JLabel("Materias disponibles próximo semestre");
        materias_r = new JLabel("Materias reprobadas en semestre anterior");
        materias_s = new JLabel("Materias registradas en semestre anterior");

        materias_d.setBounds(450, 30, 280, 23);
        materias_r.setBounds(450, 220, 330, 23);
        materias_s.setBounds(20, 275, 330, 23);
        agregarMateriasSemestre = new JButton();

        agregarMateriasSemestre.setBounds(20, 245, 25, 25);
        agregarMateriasSemestre.addActionListener(this);
        Image img = null;
        try {
            img = ImageIO.read(getClass().getResource("add_image.png"));
        } catch (IOException ex) {
            Logger.getLogger(PantallaAcademica.class.getName()).log(Level.SEVERE, null, ex);
        }
        Image newimg = img.getScaledInstance(25, 25, java.awt.Image.SCALE_SMOOTH);

        agregarMateriasSemestre.setIcon(new ImageIcon(newimg));

        agregarMateriasDeseables = new JButton();
        agregarMateriasDeseables.setIcon(new ImageIcon(newimg));
        agregarMateriasDeseables.setBounds(360, 85, 25, 25);
        agregarMateriasDeseables.addActionListener(this);

        agregarMateriasReprobadas = new JButton();
        agregarMateriasReprobadas.setIcon(new ImageIcon(newimg));
        agregarMateriasReprobadas.setBounds(360, 245, 25, 25);
        agregarMateriasReprobadas.addActionListener(this);
        mensaje = new JLabel();
        mensaje.setBounds(355, 10, 280, 23);

        //instanciamos la lista
        listaMaterias = new JList();
        PruebaConexion con;
        con = new PruebaConexion(IA.user, IA.pass);
        con.estableceConexion();
        ResultSet rs = null;
        rs = con.selectRegistro("public", "materias", null);
        System.out.println(rs);
        lista_materias_old = new HashMap();
        listadoMaterias = new ArrayList<>();
        modelo = new DefaultListModel();
        try {
            while (rs.next()) {
                lista_materias_old.put(rs.getString(2), rs.getString(1) + "," + rs.getString(3) + "," + rs.getString(4));
                modelo.addElement(rs.getString(2));
                listadoMaterias.add(rs.getString(2));
            }
            listaMaterias.setModel(modelo);
            System.out.println(lista_materias_old.toString());

        } catch (SQLException | HeadlessException e) {
            System.out.println(e);
        }
        con.cierraConexion();
        //setmaterias(lista_materias_old);
        campo.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void removeUpdate(DocumentEvent e) {
                filter();
            }

            @Override
            public void changedUpdate(DocumentEvent e) {
            }

            private void filter() {
                String filter = campo.getText();
                filterModel(modelo, filter);
            }
        });
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
                mensaje.setText("Se eliminó materia");
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
        //modelo = new DefaultListModel();
        modeloSemestre = new DefaultListModel();
        modeloDeseables = new DefaultListModel();
        modeloReprobadas = new DefaultListModel();
        //instanciamos el Scroll que tendra la lista
        scrollLista = new JScrollPane();
        scrollLista2 = new JScrollPane();
        scrollLista3 = new JScrollPane();
        scrollLista4 = new JScrollPane();
        scrollLista.setBounds(20, 85, 340, 160);
        scrollLista.setViewportView(listaMaterias);
        scrollLista2.setBounds(20, 300, 340, 150);
        scrollLista2.setViewportView(listaMateriasSemetresAnterior);
        scrollLista3.setBounds(450, 55, 340, 150);
        scrollLista3.setViewportView(listaMateriasDeseables);
        scrollLista4.setBounds(450, 247, 340, 150);
        scrollLista4.setViewportView(listaMateriasReprobadas);
        Color colorVerde = new Color(180, 233, 163);
        /*Agregamos los componentes al Contenedor*/
        contenedor.add(filtrar_materia);
        contenedor.add(campo);
        contenedor.add(agregar);
        contenedor.add(jButton2);
        //contenedor.add(promedio);
        //contenedor.add(agregarPromedio);
        contenedor.add(agregarMateriasSemestre);
        contenedor.add(agregarMateriasDeseables);
        contenedor.add(agregarMateriasReprobadas);
        contenedor.add(siguiente);
        contenedor.add(atras);
        contenedor.add(mensaje);
        contenedor.add(materias_d);
        contenedor.add(materias_r);
        contenedor.add(materias_s);
        contenedor.add(scrollLista);
        contenedor.add(scrollLista2);
        contenedor.add(scrollLista3);
        contenedor.add(scrollLista4);
        contenedor.setBackground(colorVerde);
        Timer t = new Timer(5000, new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mensaje.setText("");
            }
        });
        t.start();

    }

    public Image getIconImage() {
        Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("images/IA_LOGO.png"));
        return retValue;
    }

    @Override
    public void actionPerformed(ActionEvent evento) {

        if (evento.getSource() == agregar) {

            if (!(lista_materias_new.containsKey(campo.getText())) && !(lista_materias_old.containsKey(campo.getText()))) {
                display();
            }
        }

        if (evento.getSource() == agregarMateriasSemestre) {
            System.out.println(valoresMatNueva);
            if (valoresMatNueva != null && !(valoresMatNueva.containsKey(listaMaterias.getSelectedValue().toString()))) {
                displayFormMateriaDificultad(listaMaterias, listaMateriasSemetresAnterior, modeloSemestre);
            }
        }

        if (evento.getSource() == agregarMateriasReprobadas) {
            verificaMateriaExisteRepro(listaMaterias, listaMateriasReprobadas, modeloReprobadas, modeloDeseables);

        }

        if (evento.getSource() == agregarMateriasDeseables) {
            verificaMateriaExiste(listaMaterias, listaMateriasDeseables, modeloDeseables);

        }

        if (evento.getSource() == atras) {
            PantallaActExtracurricular ventanaActExtracurricular = new PantallaActExtracurricular();
            ventanaActExtracurricular.setVisible(true);
            this.setVisible(false);
        }

        if (evento.getSource() == siguiente) {
            PruebaConexion x = new PruebaConexion(IA.user, IA.pass);
            ResultSet rs = null;
            int tamListRepro = 0;
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

            for (int i = 0; i < listaMateriasSemetresAnterior.getModel().getSize(); i++) {
                String item = (String) listaMateriasSemetresAnterior.getModel().getElementAt(i);
                System.out.println("Item = " + item);
                if (lista_materias_new.containsKey(item)) {
                    lista_materias_escogidas_new.put(item, lista_materias_new.get(item));

                }
            }
            //Calculo el,peso mpara la nueva materia , la agrego a la base y se agrega en la lista de materias escogidas
            try {
                lista_materias_escogidas = peso_materia_nueva(lista_materias_escogidas_new, lista_materias_escogidas);
                actualiza_pesos_materiassemestres_anteriores();
            } catch (JessException | SQLException ex) {
                Logger.getLogger(PantallaAcademica.class.getName()).log(Level.SEVERE, null, ex);
            }
            HashMap lista_materias_reproBase = new HashMap();
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
                        lista_materias_reproBase.put(item, Integer.parseInt(rss.getString(2)) + 1);
                    }
                    if (ban) {
                        Map valoretem = new HashMap();
                        valoretem.put("numero", 1);
                        valoretem.put("nombre", item);
                        x.insertRegistro("public", "materias_reprobadas", valoretem);
                        lista_materias_reproBase.put(item, 1);

                    }
                } catch (SQLException e) {
                    System.out.println(e);
                }
                tamListRepro = lista_materias_reproBase.size();
            }
            try {
                jess.batch("template/templates.clp");
                jess.batch("rules/reglas_peso_materias_reprobadas.clp");

                String assertsRepro = "(Numero_Repro (NombreEst Jordy German)(Numero " + tamListRepro + "))"; //Numero de materias que el estudiante ha reprobado
                System.out.println(assertsRepro);
                jess.assertString(assertsRepro);
                Iterator it_mat_repro = lista_veces_mat_rep.entrySet().iterator();
                while (it_mat_repro.hasNext()) {//Asserts de las materias reprobadas más las veces que ha sido tomada/reprobada
                    Map.Entry e = (Map.Entry) it_mat_repro.next();
                    String veces = e.getValue().toString();
                    String materia = e.getKey().toString();
                    String assert_mat_veces = "(Veces_Materia_Reprobada (NombreEst Jordy German)(MatRepro " + materia + ")" + "(Veces " + veces + "))";
                    System.out.println(assert_mat_veces);
                    jess.assertString(assert_mat_veces);
                }

                jess.run();
                HashMap pesoF_reprobada = new HashMap();
                int maxValue = 0;
                Iterator it = jess.listFacts();
                int i = 0;
                while (it.hasNext()) {
                    Fact dd = (Fact) it.next();
                    String nombre = dd.getName();
                    if (nombre.contains("Peso_Repro")) {//Peso_repro determinado segun la cantidad de materias que ha reprobado considerando aquellas por 2da vez
                        i++;
                        System.out.println("Peso_Repro");
                        System.out.println(dd.toString());
                        System.out.println(dd.getSlotValue("Peso"));
                        pesoF_reprobada.put(i, dd.getSlotValue("Peso"));
                    }

                }
                Iterator it_pesoF_repro = pesoF_reprobada.entrySet().iterator();
                while (it_pesoF_repro.hasNext()) {
                    Map.Entry e = (Map.Entry) it_pesoF_repro.next();
                    int valor = Integer.parseInt(e.getValue().toString());
                    if (valor > maxValue) {
                        maxValue = valor;

                    }
                }
                System.out.println("Max Peso Materias Repro: " + maxValue);//PESO FINAL de peso materias repro
                System.out.println("Max Peso Atenuantes: " + PantallaActExtracurricular.peso_atenuantes);//PESO FINAL de peso materias repro
                String respuesta = respuesta_difusa(maxValue, tamListRepro);
                jess.clear();
                jess.batch("template/templates.clp");
                jess.batch("rules/reglas_ajuste_numero_materias.clp");
                jess.batch("rules/reglas_peso_final.clp");
                jess.batch("rules/reglas_respuesta.clp");
                Iterator iteold = lista_materias_escogidas.entrySet().iterator();
                jess.reset();
                System.out.println("respuesta difusa:" + respuesta);
                jess.assertString(respuesta);
                int numero_formativas = 0;
                int numero_optativas = 0;
                int numero_libre_opcion = 0;
                while (iteold.hasNext()) {
                    Map.Entry e = (Map.Entry) iteold.next();
                    String val = (String) e.getValue();
                    String values[] = val.split(",");
                    String asserts = "(Materias_Posibles (NombreMat " + e.getKey() + ")" + "(Peso " + values[1] + ")" + "(Tipo " + values[2] + "))";
                    System.out.println(asserts);
                    jess.assertString(asserts);
                    String var = values[2];
                    switch (var) {
                        case "F":
                            numero_formativas = numero_formativas + 1;
                            break;
                        case "OP":
                            numero_optativas = numero_optativas + 1;
                            break;
                        case "LB":
                            numero_libre_opcion = numero_libre_opcion + 1;
                            break;
                        default:
                            break;

                    }
                }
                String asserts2 = "(Materias_Posibles_Cojer (Nombre " + PantallaPrincipal.name + ")" + "(Formativas " + numero_formativas + ")" + "(Optativas " + numero_optativas + ")" + "(Libre_Opcion " + numero_libre_opcion + "))";
                jess.assertString(asserts2);
                System.out.println(asserts2);
                Iterator iterep = lista_materias_reproBase.entrySet().iterator();
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
                    String resultado = dd.toString();
                    System.out.println(resultado);
                    String values[];

                    if (nombre.contains("Materias_Resultado")) {
                        values = resultado.split("\\(");
                        System.out.println("Resultado-> " + values[2] + " " + values[3] + " " + values[4]);
                        if (values[2].contains("Formativas")) {
                            materiasResultFormativa = new ArrayList<>();
                            obtieneMateriasPorTipo2(values[2], "Formativas", materiasResultFormativa);
//                            formativas = obtieneMateriasPorTipo(values[2], "Formativas");
//                            formativas = formativas.replaceFirst(", ", "");
//                            materias = formativas.split(",");
//                            for(int j=0; j<materias.length; j++){
//                                materia = materias[j];
//                                materiasResultFormativa.add(materia);
//                            }

                        }
                        if (values[3].contains("Optativas")) {
                            materiasResultOptativa = new ArrayList<>();
                            //optativas = obtieneMateriasPorTipo(values[3], "Optativas");
                            obtieneMateriasPorTipo2(values[3], "Optativas", materiasResultOptativa);
                        }
                        if (values[4].contains("LibreOpcion")) {
                            //libreOp = obtieneMateriasPorTipo(values[4], "LibreOpcion");
                            materiasResultLibreOpc = new ArrayList<>();
                            obtieneMateriasPorTipo2(values[4], "LibreOpcion", materiasResultLibreOpc);
                        }
                    }

                }

            } catch (JessException ex) {
                Logger.getLogger(PantallaAcademica.class.getName()).log(Level.SEVERE, null, ex);
            }
            ResultadoMaterias ventanaResultados = new ResultadoMaterias();
            ventanaResultados.setVisible(true);
            this.setVisible(false);

        }
        //ventanaActExtra.setVisible(true);

    }

    private void agregarNombrePouup(String nombreMatPopUp) {
        String nombre = nombreMatPopUp;
        modelo.addElement(nombre);
        listaMaterias.setModel(modelo);
        campo.setText(nombreMatPopUp);
    }

    private void display() {
        String[] tipos = {"F", "OP", "LB"};
        comboTipos = new JComboBox<>(tipos);
        JTextField nombreMatPopUp = new JTextField(campo.getText(), 20);
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel panelFlujo = new JPanel();
        JPanel panelProyecto = new JPanel();
        JPanel JPCombo = new JPanel();
        JPanel JPNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int min = 1;
        int max = 5;
        int step = 1;
        int initValue = 1;
        SpinnerModel modelSpin = new SpinnerNumberModel(initValue, min, max, step);
        JSpinner spinnerCreditos = new JSpinner(modelSpin);

        JPNombre.add(new JLabel("NOMBRE:"));
        JPNombre.add(Box.createHorizontalStrut(27));
        JPNombre.add(nombreMatPopUp);
        panel.add(JPNombre);

        JPanel JPCredito = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPCredito.add(new JLabel("CREDITOS:"));
        JPCredito.add(Box.createHorizontalStrut(20));
        JPCredito.add(spinnerCreditos);
        panel.add(JPCredito);

        JPanel JPFlujo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPFlujo.add(new JLabel("FLUJO:"));
        JPFlujo.add(Box.createHorizontalStrut(30));
        panelFlujo.add(jRB_si_flujo);
        panelFlujo.add(jRB_no_flujo);
        JPFlujo.add(panelFlujo);
        panel.add(JPFlujo);

        JPanel JPProyecto = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPProyecto.add(new JLabel("PROYECTO:"));
        JPProyecto.add(Box.createHorizontalStrut(6));
        panelProyecto.add(jRB_si_proyecto);
        panelProyecto.add(jRB_no_proyecto);
        JPProyecto.add(panelProyecto);
        panel.add(JPProyecto);

        JPanel JPTipo = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPTipo.add(new JLabel("TIPO:"));
        JPTipo.add(Box.createHorizontalStrut(40));
        JPCombo.setSize(60, 10);
        JPCombo.add(comboTipos);
        JPTipo.add(JPCombo);
        panel.add(JPTipo);
        int result = JOptionPane.showConfirmDialog(null, panel, "INGRESAR NUEVA MATERIA",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            if ((jRB_si_proyecto.isSelected() || jRB_no_proyecto.isSelected()) && (jRB_si_flujo.isSelected() || jRB_no_flujo.isSelected())
                    && !(nombreMatPopUp.getText().isEmpty())) {

                if (jRB_si_flujo.isSelected() && jRB_si_proyecto.isSelected()) {
                    lista_materias_new.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());///Corregir "4,Si,Si,F"
                    listadoMaterias.add(nombreMatPopUp.getText());
                    materiaNuevaActual.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());
                    System.out.println("Si,Si");
                }

                if (jRB_no_flujo.isSelected() && jRB_no_proyecto.isSelected()) {
                    lista_materias_new.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",No,No," + comboTipos.getSelectedItem().toString());
                    listadoMaterias.add(nombreMatPopUp.getText());
                    materiaNuevaActual.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());
                    materiaNuevaActual.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());
                    System.out.println("No,No");
                }

                if (jRB_si_flujo.isSelected() && jRB_no_proyecto.isSelected()) {
                    lista_materias_new.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,No," + comboTipos.getSelectedItem().toString());
                    listadoMaterias.add(nombreMatPopUp.getText());
                    materiaNuevaActual.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());
                    System.out.println("Si,No");
                }

                if (jRB_no_flujo.isSelected() && jRB_si_proyecto.isSelected()) {
                    lista_materias_new.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",No,Si," + comboTipos.getSelectedItem().toString());
                    listadoMaterias.add(nombreMatPopUp.getText());
                    materiaNuevaActual.put(nombreMatPopUp.getText(), spinnerCreditos.getValue().toString() + ",Si,Si," + comboTipos.getSelectedItem().toString());
                    System.out.println("No,Si");
                }

                agregarNombrePouup(nombreMatPopUp.getText());//
                mensaje.setText("Materia nueva");
            } else {
                System.out.println("Llene todos los campos");
                JOptionPane.showMessageDialog(null, "Llene todos los campos!!", "Mensaje de Advertencia", JOptionPane.WARNING_MESSAGE); //Tipo de mensaje
                display();
            }
            System.out.println(nombreMatPopUp.getText()
                    + " " + spinnerCreditos.getValue().toString());
        } else {
            System.out.println("Cancelled");
        }
    }

    private ArrayList<String> obtieneMateriasPorTipo2(String value, String tipo, ArrayList<String> materiasResult) {
        String materias[];
        String materia;
        String MateriasTipo = value.split(tipo)[1];
        //System.out.println(MateriasTipo);
        MateriasTipo = MateriasTipo.replace(")", "");
        MateriasTipo = MateriasTipo.replaceFirst(", ", "");
        System.out.println(MateriasTipo);
        materias = MateriasTipo.split(",");

        for (int j = 0; j < materias.length; j++) {
            materia = materias[j];
            materiasResult.add(materia);
        }
        return materiasResult;

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

    private void verificaMateriaExiste(JList listaMaterias, JList listaMateriasCategoria, DefaultListModel modelo) {
        if (listaMaterias.getSelectedIndex() != -1) {
            if ((lista_materias_seleccionadas != null) && (!lista_materias_seleccionadas.containsKey(listaMaterias.getSelectedValue()))) {

                displayFormMateriaDificultad2(listaMaterias, listaMateriasCategoria, modelo);

            }
        }

    }

    private void verificaMateriaExisteRepro(JList listaMaterias, JList listaMateriasCategoria, DefaultListModel modelo, DefaultListModel modelodeseable) {
        if (lista_veces_mat_rep != null && !lista_veces_mat_rep.containsKey(listaMaterias.getSelectedValue())) {
            displayMateriaReprobada(listaMaterias.getSelectedValue(), listaMateriasCategoria, modelo, modelodeseable);
        }
    }

    private void displayFormMateriaDificultad(JList listaMaterias, JList listaMateriasCategoria, DefaultListModel modelo) {
        if (listaMaterias.getSelectedIndex() != -1) {
            String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
            JPanel panel = new JPanel(new GridLayout(0, 1));
            int total = 0, n_encuestados = 0, dificultad = 0;
            int min = 1;
            int max = 5;
            int step = 1;
            int initValue = 1;
            SpinnerModel modelSpin = new SpinnerNumberModel(initValue, min, max, step);
            JSpinner spinnerValor = new JSpinner(modelSpin);
            JPanel JPNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPanel JPDificultad = new JPanel(new FlowLayout(FlowLayout.LEFT));
            JPNombre.add(new JLabel("NOMBRE:"));
            JPNombre.add(Box.createHorizontalStrut(21));
            JPNombre.add(new JLabel(materiaSeleccionada));
            panel.add(JPNombre);
            JPDificultad.add(new JLabel("Dificultad:"));
            JPDificultad.add(Box.createHorizontalStrut(20));
            JPDificultad.add(spinnerValor);
            panel.add(JPDificultad);
            int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar dificultad materia", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
            if (result == JOptionPane.OK_OPTION) {
                dificultad = Integer.parseInt(spinnerValor.getValue().toString());
                modelo.addElement(materiaSeleccionada);
                listaMateriasCategoria.setModel(modelo);
                valoresMatNueva.put(materiaSeleccionada, dificultad);
            } else {
                System.out.println("Cancelled");
            }

        }
    }

    private void displayMateriaReprobada(Object item, JList listaMateriasCategoria, DefaultListModel modelo, DefaultListModel modelodeseable) {
        JLabel nombreMat = new JLabel(item.toString());
        JPanel panel = new JPanel(new GridLayout(0, 1));
        JPanel JPNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        int min = 1;
        int max = 2;
        int step = 1;
        int initValue = 1;
        SpinnerModel modelSpin = new SpinnerNumberModel(initValue, min, max, step);
        JSpinner spinnerVecesTomadas = new JSpinner(modelSpin);

        JPNombre.add(new JLabel("NOMBRE:"));
        JPNombre.add(Box.createHorizontalStrut(21));
        JPNombre.add(nombreMat);
        panel.add(JPNombre);

        JPanel JPVecesTomadas = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JPVecesTomadas.add(new JLabel("# REPRO:"));
        JPVecesTomadas.add(Box.createHorizontalStrut(20));
        JPVecesTomadas.add(spinnerVecesTomadas);
        panel.add(JPVecesTomadas);

        //UIManager.put("OptionPane.minimumSize", new Dimension(370, 400));
        int result = JOptionPane.showConfirmDialog(null, panel, "Ingresar # veces reprobada",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            System.out.println(nombreMat.getText()
                    + " Veces tomada: " + spinnerVecesTomadas.getValue().toString());
            if (!modelo.contains(listaMaterias.getSelectedValue().toString())) {
                modelo.addElement(listaMaterias.getSelectedValue().toString());
                
                listaMateriasCategoria.setModel(modelo);
                lista_veces_mat_rep.put(nombreMat.getText(), spinnerVecesTomadas.getValue().toString());
                System.out.println(lista_materias_seleccionadas);
                System.out.println(listaMaterias.getSelectedValue());
                
                if (!(lista_materias_seleccionadas.containsKey(listaMaterias.getSelectedValue().toString()))) {
                    modelodeseable.addElement(listaMaterias.getSelectedValue().toString());
                    listaMateriasDeseables.setModel(modelodeseable);
                    lista_materias_seleccionadas.put(nombreMat.getText(), spinnerVecesTomadas.getValue().toString());
                }
            }

        } else {
            System.out.println("Cancelled");
        }
    }

    private void eliminarMateriaItemActionPerformed(java.awt.event.ActionEvent evt) {
        // TODO add your handling code here:
        if (materiaElimnarSelec.equals("listaMaterias")) {
            String materia = listaMaterias.getModel().getElementAt(indexMenuItem).toString();
            ((DefaultListModel) listaMaterias.getModel()).removeElementAt(indexMenuItem);
            listadoMaterias.remove(indexMenuItem);
            lista_materias_new.remove(materia);
            lista_materias_old.remove(materia);
        }
        if (materiaElimnarSelec.equals("listaMateriasDeseables")) {
            String materia = listaMateriasDeseables.getModel().getElementAt(indexMenuItem).toString();
            ((DefaultListModel) listaMateriasDeseables.getModel()).removeElementAt(indexMenuItem);

            lista_materias_seleccionadas.remove(materia);
        }
        if (materiaElimnarSelec.equals("listaMateriasReprobadas")) {
            String materia = listaMateriasReprobadas.getModel().getElementAt(indexMenuItem).toString();
            ((DefaultListModel) listaMateriasReprobadas.getModel()).removeElementAt(indexMenuItem);
            lista_veces_mat_rep.remove(materia);
            //String materia = listaMateriasDeseables.getModel().getElementAt(indexMenuItem).toString();
            ((DefaultListModel) listaMateriasDeseables.getModel()).removeElement(materia);

            lista_materias_seleccionadas.remove(materia);
        }
        if (materiaElimnarSelec.equals("listaMateriasSemetresAnterior")) {
            String materia = listaMateriasSemetresAnterior.getModel().getElementAt(indexMenuItem).toString();
            ((DefaultListModel) listaMateriasSemetresAnterior.getModel()).removeElementAt(indexMenuItem);
            lista_materias_seleccionadas.remove(materia);
        }
    }

    public void filterModel(DefaultListModel model, String filter) {

        DefaultListModel modeloF = new DefaultListModel();
        for (String s : listadoMaterias) {
            if (!s.toLowerCase().startsWith(filter.toLowerCase())) {
                //System.out.println("NO filtro");

            } else {
                //if (!model.contains(s)) {
                modeloF.addElement(s);
                //}
            }
        }
        listaMaterias.setModel(modeloF);
    }

    private void displayFormMateriaDificultad2(JList listaMaterias, JList listaMateriasCategoria, DefaultListModel modelo) {
        if (listaMaterias.getSelectedIndex() != -1) {
            String materiaSeleccionada = listaMaterias.getSelectedValue().toString();
            modelo.addElement(materiaSeleccionada);
            listaMateriasCategoria.setModel(modelo);
            lista_materias_seleccionadas.put(materiaSeleccionada, 0);
        }
    }

    public HashMap peso_materia_nueva(HashMap lista_materias_escogidas_new, HashMap lista_materias_escogidas) throws JessException {
        PruebaConexion x = new PruebaConexion(IA.user, IA.pass);
        x.estableceConexion();
        Rete jess = new Rete();
        jess.batch("template/templates.clp");
        jess.batch("rules/reglas_peso_materia_nueva.clp");
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
        int i = 0;
        while (it.hasNext()) {
            Fact dd = (Fact) it.next();
            String nombre = dd.getName();

            if (nombre.contains("Materia_Peso")) {
                System.out.println(dd.toString());
                Map valores = new HashMap();
                String nombreM = dd.getSlotValue("NombreMat").toString();
                System.out.println(valoresMatNueva);
                int total = Integer.parseInt(valoresMatNueva.get(nombreM).toString());
                valores.put("nombre", dd.getSlotValue("NombreMat"));
                valores.put("dificultad", dd.getSlotValue("Peso"));
                valores.put("tipo", dd.getSlotValue("Tipo"));
                valores.put("total", total);
                valores.put("n_encuestados", 1);
                boolean u = false;
                u = x.insertRegistro("public", "materias", valores);
                lista_materias_escogidas.put(dd.getSlotValue("NombreMat"), "1," + dd.getSlotValue("Peso") + ',' + dd.getSlotValue("Tipo"));

            }

        }
        jess.clear();
        return lista_materias_escogidas;
    }

    public String respuesta_difusa(float maxValue, int tamListRepro) throws JessException {
        Rete jess = new Rete();
        jess.batch("template/templates.clp");
        jess.batch("rules/reglasDifusas.clp");
        jess.reset();
        VariablePromedio vp = new VariablePromedio();
        VariableReprobada vr = new VariableReprobada();
        VariableAtenuante va = new VariableAtenuante();
        HashMap maprepro = vr.EvaluarRango(maxValue);
        HashMap mappro = vp.EvaluarRango((float) PantallaPrincipal.promedio);
        HashMap mapate = va.EvaluarRango(PantallaActExtracurricular.peso_atenuantes);
        Iterator it_vp = mappro.entrySet().iterator();
        while (it_vp.hasNext()) {
            Map.Entry e = (Map.Entry) it_vp.next();
            double valor = Double.parseDouble(e.getValue().toString());
            System.out.println(valor);
            if (valor > 0) {
                String asserts = "(Peso_Promedio (Peso " + e.getKey() + ")" + "(CF " + valor + "))";
                System.out.println(asserts);
                jess.assertString(asserts);

            }
        }
        Iterator it_vr = maprepro.entrySet().iterator();
        while (it_vr.hasNext()) {
            Map.Entry e = (Map.Entry) it_vr.next();
            double valor = Double.parseDouble(e.getValue().toString());
            System.out.println(valor);
            if (valor > 0) {
                String asserts = "(Peso_Reprobado (Peso " + e.getKey() + ")" + "(CF " + valor + "))";
                System.out.println(asserts);
                jess.assertString(asserts);
            }
        }
        Iterator it_va = mapate.entrySet().iterator();
        while (it_va.hasNext()) {
            Map.Entry e = (Map.Entry) it_va.next();
            double valor = Double.parseDouble(e.getValue().toString());
            System.out.println(valor);
            if (valor > 0) {
                String asserts = "(Peso_Atenuantes (Peso " + e.getKey() + ")" + "(CF " + valor + "))";
                System.out.println(asserts);
                jess.assertString(asserts);
            }
        }
        HashMap respuestaDifusa = new HashMap();
        jess.run();
        Iterator it = jess.listFacts();
        while (it.hasNext()) {
            Fact dd = (Fact) it.next();
            String nombre = dd.getName();
            String resultad = dd.toString();
            if (nombre.contains("RespuestaDifusa")) {
                String resultado = dd.toString();
                System.out.println(resultado);
                respuestaDifusa.put(dd.getSlotValue("Categoria").toString(), dd.getSlotValue("CF").toString());
            }
        }
        jess.clear();
        int numF = 0;
        int numOp = 0;
        int numLB = 0;
        if (PantallaPrincipal.nivel >= 5) {
            System.out.println("estoy en el nivel :" + PantallaPrincipal.nivel);
            VariableRespuesta_Formativa_noNovato rf = new VariableRespuesta_Formativa_noNovato(tamListRepro);
            numF = (int) CentroMasa(rf.rangos_funcion, respuestaDifusa);
            VariableRespuesta_Optativa rp = new VariableRespuesta_Optativa();
            numOp = (int) CentroMasa(rp.rangos_funcion, respuestaDifusa);
            VariableRespuesta_LibreOpcion_noNovato rl = new VariableRespuesta_LibreOpcion_noNovato();
            numLB = (int) CentroMasa(rl.rangos_funcion, respuestaDifusa);

        } else {
            VariableRespuesta_Formativa_Novato rf = new VariableRespuesta_Formativa_Novato(tamListRepro);
            numF = (int) CentroMasa(rf.rangos_funcion, respuestaDifusa);
            numOp = 0;
            VariableRespuesta_LibreOpcion_Novato rl = new VariableRespuesta_LibreOpcion_Novato();
            numLB = (int) CentroMasa(rl.rangos_funcion, respuestaDifusa);
        }

        String assertsMaterias = "(Respuesta_Numero_Materias (Nombre_Estudiante " + PantallaPrincipal.name + ")(Formativas " + numF + ")(Optativas " + numOp + ")(Libre_Opcion " + numLB + "))";
        return assertsMaterias;
    }

    public float CentroMasa(HashMap variable, HashMap respuesta) {
        System.out.println(variable);
        System.out.println(respuesta);
        Iterator it = variable.entrySet().iterator();
        float numerador = 0;
        float denominador = 0;
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry) it.next();
            String key = (String) e.getKey();
            Iterator it2 = respuesta.entrySet().iterator();
            while (it2.hasNext()) {
                Map.Entry o = (Map.Entry) it2.next();
                String n = (String) o.getKey();
                if (n.equals(key)) {
                    System.out.println(e.getKey());
                    MembresiaTriangular m = (MembresiaTriangular) e.getValue();
                    numerador = numerador + (m.extremo_derecho * (Float.parseFloat(o.getValue().toString())));
                    denominador = denominador + (Float.parseFloat(o.getValue().toString()));
                    System.out.println(m.extremo_derecho);
                    System.out.println(o.getValue());
                    System.out.println(numerador);
                    System.out.println(denominador);
                }
            }

        }
        float resp = (float) Math.ceil(numerador / denominador);
        System.out.println(resp);
        return resp;
    }

    public void actualiza_pesos_materiassemestres_anteriores() throws SQLException {
        Iterator it_mat_sem_amterior = lista_materias_seleccionadas.entrySet().iterator();
        while (it_mat_sem_amterior.hasNext()) {//Asserts de las materias reprobadas más las veces que ha sido tomada/reprobada
            Map.Entry e = (Map.Entry) it_mat_sem_amterior.next();
            float pesos_ponderados_del_estudiante = Float.parseFloat(e.getValue().toString());
            String materia = e.getKey().toString();
            PruebaConexion con = new PruebaConexion(IA.user, IA.pass);
            con.estableceConexion();
            Map valores = new HashMap();
            valores.put("nombre", materia);
            ResultSet rss = null;
            rss = con.selectRegistro("public", "materias", valores);
            while (rss.next()) {
                float total = Float.parseFloat(rss.getString("total"));
                int n_encuestados = Integer.parseInt(rss.getString("n_encuestados"));
                n_encuestados++;
                float newPromedio = (float) (total + pesos_ponderados_del_estudiante) / (float) (n_encuestados);
                String promedio = String.format(Locale.ROOT, "%.2f", newPromedio);
                Map valoretem = new HashMap();
                valoretem.put("dificultad", promedio);
                valoretem.put("total", total + pesos_ponderados_del_estudiante);
                valoretem.put("n_encuestados", n_encuestados);
                con.updateRegistro("public", "materias", Integer.parseInt(rss.getString("id")), valoretem);
            }
        }
    }
}
