package com.wms.util;

import java.util.ArrayList;

public class Compare {
    
    // Funktion zum Vergleich von Objekten in einer ArrayList
    public static boolean compareObjects(ArrayList<Object> objects) {
        if (objects == null || objects.size() <= 1) {
            // Wenn die Liste null ist oder nur ein Element enthält,
            // betrachten wir sie als "gleich"
            return true;
        }

        Object firstObject = objects.get(0);
        for (int i = 1; i < objects.size(); i++) {
            if (!objectsEqual(firstObject, objects.get(i))) {
                return false;
            }
        }

        return true;
    }

    // Hilfsfunktion zur Überprüfung der Gleichheit zweier Objekte
    private static boolean objectsEqual(Object obj1, Object obj2) {
        if (obj1 == null) {
            return obj2 == null;
        }
        return obj1.equals(obj2);
    }
}
