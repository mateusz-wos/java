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
public class Singleton
{
  private static Singleton singleton = new Singleton( );
   
   /* A private Constructor prevents any other 
    * class from instantiating.
    */
   private Singleton(){ }
   
   /* Static 'instance' method */
   public static Singleton getInstance( ) {
      return singleton;
   }
   /* Other methods protected by singleton-ness */
   protected static void demoMethod( ) 
   {
      System.out.println("demoMethod for singleton"); 
   }   
   int [] dane;
       int suma;  
    void inicjalizujLosowo(int rozmiar, int zakres) 
    {
       dane = new int[rozmiar];
       for(int i = 0; i < dane.length; i++)
       dane[i] = (int)((Math.random() * zakres) + 0.5); 
    }
    void wypiszdane()
    {
         System.out.println("Obecne wartosci w klasie");
       for(int i = 0; i < dane.length; i++)
       {
            System.out.println(dane[i]);
       }
        
       
    }
    void suma()
    {
      
         System.out.println("Obliczanie sumy");
       for(int i = 0; i < dane.length; i++)
       {
           suma+=dane[i];
       }
          System.out.println(suma);
       
    }
    void srednia()
    {
        suma=0;
        int a=0;
        int srednia=0;
         System.out.println("Obliczanie sredniej");
       for(int i = 0; i < dane.length; i++)
       {
           a++;
           suma+=dane[i];
           
       }
            srednia=suma/a;
          System.out.println(srednia);
       
    }
     void min()
    {
        int min=dane[0];
      
         System.out.println("Obliczanie minimum");
       for(int i = 0; i < dane.length; i++)
       {
           if (min>dane[i]) min=dane[i];
           
       }
            
          System.out.println(min);
       
    }
       void maks()
    {
        int maks=dane[0];
       
         System.out.println("Obliczanie maksimum");
       for(int i = 0; i < dane.length; i++)
       {
           if (maks<dane[i]) maks=dane[i];
           
       }
            
          System.out.println(maks);
       
    }
}
