package com.project.domain.user.preferences;

import com.project.domain.user.model.UserResponseModel;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.function.Predicate;
import java.util.prefs.Preferences;

/**
 * @author dhelarius 12/4/2022
 * periodent
 */
public class UserPreferences {

    public interface Restringer {
        void onRestrict(Predicate<String> predicate);
    }

    private final Preferences prefs = Preferences.userNodeForPackage(this.getClass());

    private static UserPreferences instance;

    private UserPreferences() {}

    public static UserPreferences getInstance() {
        if(instance == null) {
            return new UserPreferences();
        }
        return instance;
    }

    public void setUserPreferences(UserResponseModel response) {
        prefs.putInt("id", response.getId());
        prefs.put("name", response.getName());
        prefs.put("lastname", response.getLastname());
        prefs.put("dni", response.getDni());
        prefs.put("phone", response.getPhone());
        prefs.put("email", response.getEmail());
        prefs.put("username", response.getUsername());
        prefs.put("createdBy", response.getCreatedBy());
        prefs.put("creationDate", response.getCreationDate().toString());
        prefs.putBoolean("status", response.isStatus());

        for(var permission : response.getPermissions()) {
            prefs.putBoolean(permission.getKey(), permission.isAssigned());
        }
    }

    public UserResponseModel getUserFromPreference() {
        var prefs = Preferences.userNodeForPackage(this.getClass());
        Timestamp timestamp = null;
        try {
            Date date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS").parse(prefs.get("creationDate", ""));
            timestamp = new Timestamp(date.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return new UserResponseModel(prefs.getInt("id", 0), prefs.get("name", ""),
                prefs.get("lastname", ""), prefs.get("dni", ""), prefs.get("phone", ""),
                prefs.get("email", ""), prefs.get("username", ""),
                prefs.get("createdBy", ""), timestamp, prefs.getBoolean("status", false));
    }

    public boolean assignPermission(String key) {
        return prefs.getBoolean(key, false);
    }

    public void restrictFromPermission(Restringer restringer) {
        restringer.onRestrict(this::assignPermission);
    }

    public Preferences getPreferences() {
        return prefs;
    }
}
