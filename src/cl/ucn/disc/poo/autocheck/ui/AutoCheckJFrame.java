/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.ui;

import cl.ucn.disc.poo.autocheck.domain.Auto;
import com.formdev.flatlaf.FlatLightLaf;

import javax.swing.*;

/**
 * The Main Frame.
 *
 * @author Programacion Orientada al Objeto.
 */
public final class AutoCheckJFrame extends JFrame {

    private JPanel pnlMain;
    private JTable tblAuto;
    private JPanel pnlBotones;
    private JPanel pnlTabla;
    private JButton btnAgregar;
    private JPanel pnlStatus;
    private JLabel lblStatus;

    /**
     * The version.
     */
    private static final String VERSION = "1.0.2";

    /**
     * The model de la tabla.
     */
    private final AutoModel autoModel = new AutoModel();

    /**
     * The Constructor.
     */
    public AutoCheckJFrame() {
        super("AutoCheck v" + VERSION);

        // panel principal
        this.getContentPane()
                .add(this.pnlMain);

        // accion al cerrar
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // tamanio
        this.setSize(800, 600);

        // centrado en la pantalla
        this.setLocationRelativeTo(null);

        // no redimensionable
        this.setResizable(false);

        // tamanio de la ventana
        // this.pack();

        // asigno el modelo a la tabla
        this.tblAuto.setModel(this.autoModel);

        // en caso de refrescar la tabla de muestra la cantidad de vehiculos
        this.autoModel.addTableModelListener(e -> {
            this.lblStatus.setText("Cantidad de Autos: " + this.autoModel.getRowCount());
        });

        // doble click en la tabla para editar un auto
        this.tblAuto.addMouseListener(new java.awt.event.MouseAdapter() {

            public void mouseClicked(java.awt.event.MouseEvent evt) {
                // si hago doble click sobre una fila y existe algun auto en esa fila
                if (evt.getClickCount() == 2 && tblAuto.getSelectedRow() != -1) {
                    // obtengo el auto de la fila
                    Auto auto = autoModel.getAuto(tblAuto.getSelectedRow());
                    // muestro el auto para su edicion
                    showDialogAuto(auto);
                }
            }
        });

        // dialog para agregar un contacto en caso de presionar el boton agregar
        this.btnAgregar.addActionListener(e -> this.showDialogAuto(null));

        // refresh de la tabla
        this.autoModel.fireTableDataChanged();
    }

    /**
     * Show a Dialog to add or edit a Auto.
     */
    private void showDialogAuto(final Auto auto) {
        AutoDialog ad = new AutoDialog(this.autoModel, auto);
        ad.pack();
        ad.setLocationRelativeTo(null);
        ad.setVisible(true);
    }

    /**
     * The Main.
     *
     * @param args to use.
     */
    public static void main(String[] args) {

        // run in the swing thread
        SwingUtilities.invokeLater(() -> {

            // look & feel: https://www.formdev.com/flatlaf/
            FlatLightLaf.setup();

            // new instance
            new AutoCheckJFrame().setVisible(true);
        });

    }

}
