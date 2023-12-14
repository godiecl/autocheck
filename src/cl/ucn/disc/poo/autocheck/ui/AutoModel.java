/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.ui;

import cl.ucn.disc.poo.autocheck.domain.Auto;
import cl.ucn.disc.poo.autocheck.services.AutoCheck;
import cl.ucn.disc.poo.autocheck.services.AutoCheckImpl;

import javax.swing.table.AbstractTableModel;

/**
 * The Model of Auto.
 *
 * @author Programacion Orientada al Objeto.
 */
public final class AutoModel extends AbstractTableModel {

    /**
     * Controlador via singleton
     */
    private static final AutoCheck AUTOCHECK = new AutoCheckImpl();

    /**
     * The Columnas.
     */
    private static final String[] COLUMNAS = {
            "Patente", // 0
            "Marca", // 1
            "Modelo", // 2
            "Anio", // 3
            "Kilometraje", // 4
            "Homologado" // 5
    };

    /**
     * The Constructor.
     */
    public AutoModel() {
        // nothing here
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getRowCount() {
        return AUTOCHECK.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getColumnCount() {
        return COLUMNAS.length;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getColumnName(int column) {
        return COLUMNAS[column];
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        // retorna el tipo de dato de cada columna para su correcto despliegue
        return switch (columnIndex) {
            case 0, 1, 2 -> String.class;
            case 3, 4 -> Integer.class;
            case 5 -> Boolean.class;
            default -> Object.class;
        };
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {

        // auto en la fila de la tabla indicada
        Auto auto = this.getAuto(rowIndex);

        // segun la columna retornar el valor
        switch (columnIndex) {
            case 0:
                return auto.getPatente();
            case 1:
                return auto.getMarca();
            case 2:
                return auto.getModelo();
            case 3:
                return auto.getAnio();
            case 4:
                return auto.getKilometraje();
            case 5:
                return auto.isHomologado();
        }
        throw new IndexOutOfBoundsException("Columna invalida.");
    }

    /**
     * Return the Auto in the row.
     */
    public Auto getAuto(int rowIndex) {
        return AUTOCHECK.getAuto(rowIndex);
    }

    /**
     * Save the Auto in the model.
     */
    public void saveAuto(final Auto auto) {
        AUTOCHECK.add(auto);

        // refresco la tabla
        this.fireTableDataChanged();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return false;
    }
}
