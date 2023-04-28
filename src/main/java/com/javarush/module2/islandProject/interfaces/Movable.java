package com.javarush.module2.islandProject.interfaces;

public interface Movable {

   int getX();
   int getY();

   int getSpeed();



   void move(int newX, int newY);

}
