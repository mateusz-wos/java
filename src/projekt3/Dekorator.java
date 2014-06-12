/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package projekt3;

/**
 *
 * @author mwos
 */
public class Dekorator extends Vector3D 
{
     public Dekorator(float x, float y, float z)
     {
        setTo(x, y, z);
     }
     //wypisywanie wektora
     public void wypisz_wektor()
     {
         System.out.println("WEKTOR: " + x+" "+y+" "+z); 
     }
}
