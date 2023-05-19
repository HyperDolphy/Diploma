package com.example.diploma;

import android.annotation.SuppressLint;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class FirewallChecker {

    public static boolean isFirewallEnabled(MainActivity mainActivity) {
        boolean isFirewallEnabled = false;

        try {
            // Получаем экземпляр класса IPTablesManager через рефлексию
            @SuppressLint("PrivateApi") Class<?> iptablesManagerClass = Class.forName("android.net.IpTablesManager");
            Object iptablesManager = iptablesManagerClass.newInstance();

            // Вызываем метод isFirewallEnabled() для проверки состояния межсетевого экрана
            Method isFirewallEnabledMethod = iptablesManagerClass.getDeclaredMethod("isFirewallEnabled");
            isFirewallEnabled = (boolean) isFirewallEnabledMethod.invoke(iptablesManager);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException | NoSuchMethodException |
                 InvocationTargetException e) {
            e.printStackTrace();
        }

        return isFirewallEnabled;
    }
}


