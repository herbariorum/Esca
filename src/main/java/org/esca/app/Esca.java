package org.esca.app;

import com.formdev.flatlaf.FlatLightLaf;
import org.esca.app.auth.AuthForm;

public class Esca {

    public static void main(String[] args) {
        FlatLightLaf.setup();

        AuthForm login = new AuthForm();
        login.setVisible(true);
    }
}
