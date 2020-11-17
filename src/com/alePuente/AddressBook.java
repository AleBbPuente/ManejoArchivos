package com.alePuente;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class AddressBook {
    private HashMap<String,String> contactos = new HashMap<>();

    public void load() throws IOException {
        String separador = FileSystems.getDefault().getSeparator();
        String fileName = String.format("src%scom%salePuente%scontactos.csv", separador, separador, separador);

        Path path = Paths.get(fileName);

        ArrayList<String> lines = new ArrayList<>();

        if(!Files.exists(path)){
            File file = new File(String.valueOf(path));
            file.createNewFile();
        }

        lines = (ArrayList<String>) Files.readAllLines(path);
        for (var contact : lines){
            var infoContact = contact.split(",");
            contactos.put(infoContact[0].trim(),infoContact[1].trim());
        }
    }

    public void save() throws IOException {
        String separador = FileSystems.getDefault().getSeparator();
        String fileName = String.format("src%scom%salePuente%scontactos.csv",separador, separador, separador);
        Path path = Paths.get(fileName);

        ArrayList<String> saveContacts = new ArrayList<>();
        for (var contact : contactos.entrySet()){
            saveContacts.add(contact.getKey()+", "+ contact.getValue());
        }

        Files.write(path,saveContacts);
    }

    public void list() throws IOException {
        for(var contact : contactos.entrySet()){
            System.out.println(String.format("Nombre: %s, Número: %s",
                    contact.getKey(),contact.getValue()));
        }
    }

    public void create(String nombre, String telefono) throws IOException {
        contactos.put(telefono,nombre);
        save();
        load();
    }

    public void delete(String telefono) throws IOException {
        var p = contactos.remove(telefono);
        save();
        load();
    }
}
