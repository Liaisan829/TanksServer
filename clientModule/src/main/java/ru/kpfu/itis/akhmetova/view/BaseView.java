package ru.kpfu.itis.akhmetova.view;

import javafx.scene.Parent;
import ru.kpfu.itis.akhmetova.MainApplication;

import java.io.IOException;

public abstract class BaseView {

    private static MainApplication application;

    public abstract Parent getView() throws IOException;

    public static void setApplication(MainApplication application) {
        BaseView.application = application;
    }

    public static MainApplication getApplication() throws Exception {
        if (application!= null) {
            return application;
        }
        throw new Exception("No Application in BaseView");
    }
}