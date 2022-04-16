module com.project.app {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires de.saxsys.mvvmfx;

    requires io.activej.inject;
    requires io.activej.types;

    requires java.sql;

    requires domain;
    requires data;
    requires adapter;

    opens com.project.app to javafx.fxml;
    opens com.project.app.controller to javafx.fxml;
    opens com.project.app.di.activej to io.activej.inject;
    opens com.project.app.user.viewmodel to de.saxsys.mvvmfx,javafx.fxml;
    exports com.project.app;
    exports com.project.app.controller;
    opens com.project.app.user.view to de.saxsys.mvvmfx, javafx.fxml;
}