package com.kdbf.digitalLibrary.adapters.in.shell;

import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
public class MenuCommands {

  @ShellMethod(key = "menu", value = "Muestra el menu al usuario")
  public String showMenu() {
    String menuCatalogo = "Menu del programa de catalogo de libros. \n Opciones: \n";
    return menuCatalogo;
  }

}
