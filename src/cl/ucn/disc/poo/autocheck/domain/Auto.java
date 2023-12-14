/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.domain;

/**
 * The Auto.
 *
 * @author Programacion Orientada al Objeto.
 */
public final class Auto {

    /**
     * The Patente.
     */
    private final String patente;

    /**
     * The Marca.
     */
    private final String marca;

    /**
     * The Modelo.
     */
    private final String modelo;

    /**
     * The Anio.
     */
    private final int anio;

    /**
     * The Kilometraje.
     */
    private final int kilometraje;

    /**
     * The Homologado.
     */
    private final boolean homologado;

    /**
     * The Constructor.
     */
    public Auto(String patente, String marca, String modelo, int anio, int kilometraje, boolean homologado) {
        this.patente = patente;
        this.marca = marca;
        this.modelo = modelo;
        this.anio = anio;
        this.kilometraje = kilometraje;
        this.homologado = homologado;
    }

    /**
     * @return the Patente.
     */
    public String getPatente() {
        return this.patente;
    }

    /**
     * @return the Marca.
     */
    public String getMarca() {
        return this.marca;
    }

    /**
     * @return the Modelo.
     */
    public String getModelo() {
        return this.modelo;
    }

    /**
     * @return the Anio.
     */
    public int getAnio() {
        return this.anio;
    }

    /**
     * @return the Kilometraje.
     */
    public int getKilometraje() {
        return this.kilometraje;
    }

    /**
     * @return the Homologado.
     */
    public boolean isHomologado() {
        return this.homologado;
    }
}
