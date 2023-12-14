/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.services;

import cl.ucn.disc.poo.autocheck.domain.Auto;

import java.util.List;

/**
 * The AutoCheck implementation.
 *
 * @author Programacion Orientada al Objeto.
 */
public interface AutoCheck {

    /**
     * @return the List of Autos.
     */
    List<Auto> getAutos();

    /**
     * @return the size of the Autos.
     */
    int size();

    /**
     * @return el auto en la posicion indicada.
     */
    Auto getAuto(int posicion);

    /**
     * Agrega un Auto al sistema.
     */
    void add(Auto auto);

}
