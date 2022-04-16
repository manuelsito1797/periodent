package com.project.app.layout;

import com.project.app.user.layout.UserLayout;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public class FactoryLayout {

    public static Layout getLayout(Layout.Type type) {
        switch (type) {
            case RootLayout: return new RootLayout("view/root-layout.fxml");
            case UserLayout: return new UserLayout("user/view/UserView.fxml");
            default: return null;
        }
    }
}
