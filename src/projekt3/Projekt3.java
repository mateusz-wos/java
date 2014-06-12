/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt3;

/**
 *
 * @author mwos
 */
public class Projekt3 {
  public static double determinant(double A[][],int N)
{

double m[][];
double det=0;
double res;


if(N == 1)
res = A[0][0];

else if (N == 2)
{
res = A[0][0]*A[1][1] - A[1][0]*A[0][1];
}

else
{
res=0;
for(int j1=0;j1<N;j1++)
{
m = new double[N-1][];
 for(int k=0;k<(N-1);k++)
   m[k] = new double[N-1];
for(int i=1;i<N;i++)
{
int j2=0;
for(int j=0;j<N;j++)
{
  if(j == j1)
   continue;
  m[i-1][j2] = A[i][j];
  j2++;
}
}
 res += Math.pow(-1.0,1.0+j1+1.0)* A[0][j1] * determinant(m,N-1);


}
}


return res;

}

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        double[][] d = { { 1, 1 }, { 4, 15 } };
            double[][] d2 = { { 3, 2 }, { 4, 15 } };
        double test;
       
        Matrix D = new Matrix(d);
        Matrix Q = new Matrix(d2);
        Matrix A = Matrix.random(5, 5);
        Matrix b = Matrix.random(5, 1);
        System.out.println("Macierz 1" );
        D.show();
         System.out.println("Macierz 2" );
        Q.show();
        Q.minus(Q);
        test=D.det();
          System.out.println("Wyznacznik" + test);
      
        

        
        
        
        
        float dlugosc;
        Vector3D vector2=new Vector3D(1,1,1); 
        Vector3D vector=new Vector3D(5,10,15); 
        System.out.println("Wypisanie wektora 1 i 2"); 
        System.out.println(vector); 
        System.out.println(vector2); 
        dlugosc=vector.length();
        System.out.println("Dlugosc wektora"); 
        System.out.println(dlugosc); 
        System.out.println("Dodawanie wektora");
        vector.add(vector2);
        System.out.println(vector); 
        
        
        
        
        
        /*
        Dane s = new Dane();
        s.inicjalizujLosowo(5, 15);
        s.wypiszdane();
        s.suma();
        s.srednia();
        s.min();
        s.maks();
        */
        
        ////////SINGLETOOON///////////////////
         Singleton tmp = Singleton.getInstance( );
         tmp.inicjalizujLosowo(5, 15);
         tmp.wypiszdane();
         tmp.suma();
         tmp.srednia();
         tmp.min();
         tmp.maks();
        ///DEKTORATOR///////////
         Dekorator vec=new Dekorator(5,1,1);
         vec.wypisz_wektor();
    }
}
