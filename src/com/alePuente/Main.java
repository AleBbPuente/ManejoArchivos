package com.alePuente;

import java.util.HashMap;
import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

    AddressBook Contactos = new AddressBook();
    Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        AddressBook adressBook = new AddressBook();
        Main Menu = new Main();
        Menu.Menu();
    }


    Scanner scanner = new Scanner(System.in);
    int opcion = 0;
    boolean salir = false;


    public void Menu ()throws IOException{

        while (!salir) {

            System.out.println("1. Ver lista de contactos");
            System.out.println("2. Añadir nuevo contacto");
            System.out.println("3. Eliminar contacto");
            System.out.println("0. Salir");

            try {
                opcion = scanner.nextInt();

                switch (opcion) {

                    case 1:
                        enseñarContacto();
                        break;

                    case 2:
                        agregarContacto();
                        break;

                    case 3:
                        eliminarContacto();
                        break;

                    case 0:
                        salir=true;
                        System.out.println("Hasta luego!");
                        break;

                    default:
                        throw new IllegalStateException("Selecciona un número del 0 al 3 " + opcion);
                }
            } catch (InputMismatchException e) {
                System.out.println("Selcciona un número");
                scanner.next();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void enseñarContacto()throws IOException{
        Contactos.list();
    }

    private void agregarContacto() throws IOException {
        System.out.println("Nombre");
        var nombre = entrada.next();
        System.out.println("Número");
        var numero = entrada.next();
        Contactos.create(numero,nombre);
        System.out.println("Contacto Agregado");
    }

    private void eliminarContacto() throws IOException {
        System.out.println("Número");
        var eliminar = entrada.next();
        Contactos.delete(eliminar);
        System.out.println("Contacto eliminado");

    }

}
