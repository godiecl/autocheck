/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Clase que permite mostrar mensajes de error.
 *
 * @author Programacion Orientada al Objeto.
 */
public final class Dialog {

    /**
     * Muestra un mensaje de error.
     *
     * @param component que mostrara el error.
     * @param message   a desplegar.
     */
    public static void showError(final Component component, final String message) {
        JOptionPane.showMessageDialog(component, message, "ERROR", JOptionPane.ERROR_MESSAGE);
    }

    /**
     * Muestra un mensaje de informacion.
     *
     * @param component que mostrara el error.
     * @param message   a desplegar.
     */
    public static void showMensaje(final Component component, final String message) {
        JOptionPane.showMessageDialog(component, message, "INFORMACION", JOptionPane.INFORMATION_MESSAGE);
    }

}
