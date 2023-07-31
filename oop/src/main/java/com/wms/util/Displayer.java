package com.oop.util;

import java.lang.reflect.Method;

public class Displayer {
    
    public Displayer(){}

    //Aus dem Objekt (Lagerstandort oder Hangar oder Produkt oder ...) 
    //werden die Attribute zu den enthaltenden Get-Methoden genommen, um das Objekt vollständig darzustellen
    //Funktioniert mit dem Prinzip der Reflexion

    public void displayObject(Object ob, String[] at){

        //Für jedes Attribut wird eine Get-Methode ausgeführt
        for(int i=0;i<at.length;i++){

            String tempMethodName = "get"+ at[i];
            try {
                // Hole die Methode basierend auf dem Namen
                Method method = ob.getClass().getMethod(tempMethodName);

                // Rufe die Methode auf der Objektinstanz auf
                Object value = method.invoke(ob);

                System.out.println("----- " +at[i]+ " : " + value);

            } catch (Exception e) {
                e.printStackTrace();
            }
        }        
        System.out.println("");
        
   
    }

}
