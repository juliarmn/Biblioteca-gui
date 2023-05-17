module julia.biblioteca {
    requires javafx.controls;
    requires javafx.fxml;


    opens julia.biblioteca to javafx.fxml;
    exports julia.biblioteca;
    exports julia.biblioteca.gui.controllers;
    opens julia.biblioteca.gui.controllers to javafx.fxml;
}