/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.ui;

import cl.ucn.disc.poo.autocheck.domain.Auto;
import cl.ucn.disc.poo.autocheck.utils.Dialog;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.time.Year;

/**
 * The Dialog para agregar o editar un Auto.
 *
 * @author Programacion Orientada al Objeto.
 */
public class AutoDialog extends JDialog {

    private JPanel pnlMain;
    private JButton buttonOK;
    private JButton buttonCancel;
    private JTextField txtPatente;
    private JTextField txtMarca;
    private JTextField txtModelo;
    private JSpinner spnAnio;
    private JSpinner spnKilometraje;
    private JCheckBox cbHomologado;

    /**
     * The AutoModel.
     */
    private final AutoModel autoModel;

    /**
     * The Model.
     */
    public AutoDialog(final AutoModel autoModel, final Auto auto) {

        // set the model
        this.autoModel = autoModel;

        // set the content
        if (auto != null) {
            this.setTitle("Editar Auto");
            this.populateAuto(auto);
        } else {
            this.setTitle("Agregar Auto");
        }

        // config
        this.setContentPane(pnlMain);
        this.setModal(true);
        this.getRootPane().setDefaultButton(buttonOK);

        // buttons
        this.buttonOK.addActionListener(e -> onOK());
        this.buttonCancel.addActionListener(e -> onCancel());

        // call onCancel() when cross is clicked
        this.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                onCancel();
            }
        });

        // call onCancel() on ESCAPE
        this.pnlMain.registerKeyboardAction(e -> onCancel(), KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

    }

    /**
     * Populate the Auto.
     */
    private void populateAuto(final Auto auto) {
        // si el auto es distinto de null lleno los campos
        if (auto != null) {
            this.txtPatente.setText(auto.getPatente());
            this.txtPatente.setEnabled(false);

            this.txtMarca.setText(auto.getMarca());
            this.txtMarca.setEnabled(false);

            this.txtModelo.setText(auto.getModelo());
            this.txtModelo.setEnabled(false);

            this.spnAnio.setValue(auto.getAnio());
            this.spnAnio.setEnabled(false);

            this.spnKilometraje.setValue(auto.getKilometraje());

            this.cbHomologado.setSelected(auto.isHomologado());
        }
    }

    /**
     * Ok button
     */
    private void onOK() {

        // validacion de patente
        if (this.txtPatente.getText().contains(" ")) {
            Dialog.showError(this, "Debe ingresar la Patente!");
            return;
        }

        // validacion de marca
        if (this.txtMarca.getText().isBlank()) {
            Dialog.showError(this, "Debe ingresar la Marca!");
            return;
        }

        // validacion de modelo
        if (this.txtModelo.getText().isBlank()) {
            Dialog.showError(this, "Debe ingresar el Modelo!");
            return;
        }

        // construyo el auto
        Auto auto = new Auto(
                this.txtPatente.getText().strip(),
                this.txtMarca.getText().strip(),
                this.txtModelo.getText().strip(),
                (int) this.spnAnio.getValue(),
                (int) this.spnKilometraje.getValue(),
                this.cbHomologado.isSelected()
        );

        // se agrega el auto al modelo
        this.autoModel.saveAuto(auto);

        // despliego un mensaje
        Dialog.showMensaje(this, "Se ha realizado el ingreso del Auto.");

        // cierro el dialog
        dispose();
    }

    /**
     * Cancel button
     */
    private void onCancel() {
        // cierro el dialog
        dispose();
    }

    /**
     * Create the specific components.
     */
    private void createUIComponents() throws ParseException {
        // formateador de la patente
        this.txtPatente = new JFormattedTextField(new MaskFormatter("UU-UU-##"));

        // formato del spinner de anio
        int anio = Year.now().getValue();
        SpinnerModel sm = new SpinnerNumberModel(anio, 1902, anio + 1, 1);
        this.spnAnio = new JSpinner(sm);
        this.spnAnio.setEditor(new JSpinner.NumberEditor(this.spnAnio, "#"));

        // formato del spinner de kilometraje
        this.spnKilometraje = new JSpinner(new SpinnerNumberModel(100, 0, 999999, 1));
    }

}
