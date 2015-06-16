package com.dexter.Utils;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.lang.reflect.Type;

import android.content.SharedPreferences;

import com.google.gson.Gson;

public class GsonWrapper {

    public static Object retrieveObjectFromPrefs(Class<?> objClass, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = prefs.getString(objClass.getSimpleName(), "");
        return gson.fromJson(json, objClass);
    }

    public static void saveObjectToPrefs(Object obj, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        prefs.edit().putString(obj.getClass().getSimpleName(), json).commit();
    }

    public static void removeObjectFromPrefs(Object obj, SharedPreferences prefs){
        prefs.edit().remove(obj.getClass().getSimpleName()).commit();
    }

    public static Object retrieveArrayObjectFromPrefs(Class<?> objClass, Type objType, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = prefs.getString(objClass.getSimpleName(), "");
        return gson.fromJson(json, objType);
    }

    public static void saveArrayObjectToPrefs(Class<?> objClass, Object obj, SharedPreferences prefs){
        Gson gson = new Gson();
        String json = gson.toJson(obj);
        prefs.edit().putString(objClass.getSimpleName(), json).commit();
    }

    public static Object retrieveObjectFromInputStream(Class<?> objClass, InputStream input){
        Gson gson = new Gson();
        Reader reader = new InputStreamReader(input);

        return gson.fromJson(reader, objClass);
    }

}

