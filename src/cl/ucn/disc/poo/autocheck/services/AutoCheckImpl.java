/*
 * Copyright (c) 2023. Programacion Orientada al Objeto, DISC, UCN, Chile.
 */

package cl.ucn.disc.poo.autocheck.services;

import cl.ucn.disc.poo.autocheck.domain.Auto;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * The AutoCheck implementation.
 *
 * @author Programacion Orientada al Objeto.
 */
public final class AutoCheckImpl implements AutoCheck {

    /**
     * Nombre del archivo.
     */
    private static final String FILENAME = "autos.json";

    /**
     * Procesador de JSON.
     */
    private static final Gson GSON = new GsonBuilder()
            .setPrettyPrinting()
            .create();

    /**
     * Listado de Autos.
     */
    private final List<Auto> autos = new ArrayList<>();

    /**
     * The Constructor.
     */
    public AutoCheckImpl() {

        // intento cargar los autos
        try {
            this.load();
        } catch (RuntimeException ex) {
            // .. si se produce un error, los guardo (es el caso que el archivo no exista)
            this.save();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<Auto> getAutos() {
        // lista no modificable de autos
        return Collections.unmodifiableList(this.autos);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int size() {
        return this.autos.size();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Auto getAuto(int posicion) {
        return this.autos.get(posicion);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void add(Auto auto) {

        for (int i = 0; i < this.autos.size(); i++) {

            // auto en la posicion i
            Auto current = this.autos.get(i);

            // ya esta!
            if (auto.getPatente()
                    .equals(current.getPatente())) {
                // lo elimino ..
                this.autos.remove(i);
                // termino el ciclo
                break;
            }

        }

        // agrego el nuevo
        this.autos.add(auto);

        // guardo en el archivo
        this.save();

    }

    /**
     * Carga los autos desde el archivo.
     */
    private void load() {

        // limpiar la lista previo a la carga
        this.autos.clear();

        // leer desde el archivo FILENAME los contactos.
        try {
            // lectura de json -> contacto[]
            Auto[] arrAutos = GSON.fromJson(new FileReader(FILENAME), Auto[].class);

            // agrego el arreglo de contactos al listado
            this.autos.addAll(Arrays.asList(arrAutos));

        } catch (FileNotFoundException e) {
            // algun problema en la lectura del archivo
            throw new RuntimeException("Error de lectura", e);
        }
    }

    /**
     * Guarda los autos en el archivo.
     */
    private void save() {

        // escribir en el archivo FILENAME los contactos.
        try (FileWriter writer = new FileWriter(FILENAME)) {
            GSON.toJson(this.autos, writer);
        } catch (IOException e) {
            // algun problema en la escritura del archivo
            throw new RuntimeException("Error de escritura", e);
        }
    }
}
