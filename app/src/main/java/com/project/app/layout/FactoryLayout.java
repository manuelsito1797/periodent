package com.project.app.layout;

/**
 * @author dhelarius 13/4/2022
 * periodent
 */
public class FactoryLayout {

    public static Layout getLayout(Layout.Type type, String resource) {
        switch (type) {
            case RootLayout: return new RootLayout(resource);
            default: return null;
        }
    }
}
