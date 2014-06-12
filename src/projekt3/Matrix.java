/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt3;

import java.util.Iterator;

/**
 *
 * @author mwos
 */
final public class Matrix implements Iterable<Double[]>  {
    private final int M;             // number of rows
    private final int N;             // number of columns
    private  double[][] data;   // M-by-N array
     private int piv[];
     private int pivsign;
    // create M-by-N matrix of 0's
    public Matrix(int M, int N) {
        this.M = M;
        this.N = N;
        data = new double[M][N];
    }
private Iterator<Double[]> _it;

    private class IRows implements Iterator/*, Iterable<Double>*/ {
        /*private Iterator<Double> _it;*/
        private int ci, cj;
        
        public IRows() {
            ci = 0;
            cj = 0;
        }
        
        @Override
        public void remove() {}
        @Override
        public boolean hasNext() {
            return ci < N;
        }
        @Override
        public double[] next() {
            return data[ci++];
        }
    }
    @Override
    public Iterator<Double[]> iterator() {
        //if(_it == null)  _it = new IRows();
        
        //return _it;
        return new IRows();
    }

    // create matrix based on 2d array
    public Matrix(double[][] data) {
        M = data.length;
        N = data[0].length;
        this.data = new double[M][N];
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                    this.data[i][j] = data[i][j];
    }

    // copy constructor
    private Matrix(Matrix A) { this(A.data); }

    // create and return a random M-by-N matrix with values between 0 and 1
    public static Matrix random(int M, int N) {
        Matrix A = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[i][j] = Math.random();
        return A;
    }

    // create and return the N-by-N identity matrix
    public static Matrix identity(int N) {
        Matrix I = new Matrix(N, N);
        for (int i = 0; i < N; i++)
            I.data[i][i] = 1;
        return I;
    }

    // swap rows i and j
    private void swap(int i, int j) {
        double[] temp = data[i];
        data[i] = data[j];
        data[j] = temp;
    }

    // create and return the transpose of the invoking matrix
    public Matrix transpose() {
        Matrix A = new Matrix(N, M);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                A.data[j][i] = this.data[i][j];
        return A;
    }

    // return C = A + B
    public Matrix plus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] + B.data[i][j];
        return C;
    }


    // return C = A - B
    public Matrix minus(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(M, N);
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                C.data[i][j] = A.data[i][j] - B.data[i][j];
        return C;
    }

    // does A = B exactly?
    public boolean eq(Matrix B) {
        Matrix A = this;
        if (B.M != A.M || B.N != A.N) throw new RuntimeException("Illegal matrix dimensions.");
        for (int i = 0; i < M; i++)
            for (int j = 0; j < N; j++)
                if (A.data[i][j] != B.data[i][j]) return false;
        return true;
    }

    // return C = A * B
    public Matrix times(Matrix B) {
        Matrix A = this;
        if (A.N != B.M) throw new RuntimeException("Illegal matrix dimensions.");
        Matrix C = new Matrix(A.M, B.N);
        for (int i = 0; i < C.M; i++)
            for (int j = 0; j < C.N; j++)
                for (int k = 0; k < A.N; k++)
                    C.data[i][j] += (A.data[i][k] * B.data[k][j]);
        return C;
    }


    // return x = A^-1 b, assuming A is square and has full rank
    public Matrix solve(Matrix rhs) {
        if (M != N || rhs.M != N || rhs.N != 1)
            throw new RuntimeException("Illegal matrix dimensions.");

        // create copies of the data
        Matrix A = new Matrix(this);
        Matrix b = new Matrix(rhs);

        // Gaussian elimination with partial pivoting
        for (int i = 0; i < N; i++) {

            // find pivot row and swap
            int max = i;
            for (int j = i + 1; j < N; j++)
                if (Math.abs(A.data[j][i]) > Math.abs(A.data[max][i]))
                    max = j;
            A.swap(i, max);
            b.swap(i, max);

            // singular
            if (A.data[i][i] == 0.0) throw new RuntimeException("Matrix is singular.");

            // pivot within b
            for (int j = i + 1; j < N; j++)
                b.data[j][0] -= b.data[i][0] * A.data[j][i] / A.data[i][i];

            // pivot within A
            for (int j = i + 1; j < N; j++) {
                double m = A.data[j][i] / A.data[i][i];
                for (int k = i+1; k < N; k++) {
                    A.data[j][k] -= A.data[i][k] * m;
                }
                A.data[j][i] = 0.0;
            }
        }

        // back substitution
        Matrix x = new Matrix(N, 1);
        for (int j = N - 1; j >= 0; j--) {
            double t = 0.0;
            for (int k = j + 1; k < N; k++)
                t += A.data[j][k] * x.data[k][0];
            x.data[j][0] = (b.data[j][0] - t) / A.data[j][j];
        }
        return x;
   
    }

    // print matrix to standard output
    public void show() {
        for (int i = 0; i < M; i++) {
            for (int j = 0; j < N; j++) 
                System.out.printf("%9.4f ", data[i][j]);
            System.out.println();
        }
    }
     public Matrix LUDecomposition() {
        // Use a "left-looking", dot-product, Crout/Doolittle algorithm.
        double[][] LU = data;

        piv = new int[M];
        for (int i = 0; i < M; i++) {
           piv[i] = i;
        }
        pivsign = 1;
        double[] LUrowi;
        final double[] LUcolj = new double[M];

        // Outer loop.
        for (int j = 0; j < N; j++) {

           // Make a copy of the j-th column to localize references.
           for (int i = 0; i < M; i++) {
              LUcolj[i] = LU[i][j];
           }

           // Apply previous transformations.
           for (int i = 0; i < M; i++) {
              LUrowi = LU[i];

              // Most of the time is spent in the following dot product.
              final int kmax = Math.min(i, j);
              double s = 0.0;
              for (int k = 0; k < kmax; k++) {
                 s += LUrowi[k] * LUcolj[k];
              }

              LUrowi[j] = LUcolj[i] -= s;
           }

           // Find pivot and exchange if necessary.
           int p = j;
           for (int i = j + 1; i < M; i++) {
              if (Math.abs(LUcolj[i]) > Math.abs(LUcolj[p])) {
                 p = i;
              }
           }
           if (p != j) {
              for (int k = 0; k < N; k++) {
                 final double t = LU[p][k];
                 LU[p][k] = LU[j][k];
                 LU[j][k] = t;
              }
              final int k = piv[p];
              piv[p] = piv[j];
              piv[j] = k;
              pivsign = -pivsign;
           }

           // Compute multipliers.
           if (j < M & LU[j][j] != 0.0) {
              for (int i = j + 1; i < M; i++) {
                 LU[i][j] /= LU[j][j];
              }
           }
        }
        
        return this;
    }
         public Matrix macierzL() {
        double[][] L = new double[N][M];
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            if (i > j) {
              L[i][j] = data[i][j];
            } else if (i == j) {
              L[i][j] = 1.0;
            } else {
              L[i][j] = 0.0;
            }
          }
        }
        
        return new Matrix(L);
    }
    public Matrix macierzU() {
        double[][] U = new double[N][M];
        for (int i = 0; i < N; i++) {
          for (int j = 0; j < M; j++) {
            if (i <= j) {
              U[i][j] = data[i][j];
            } else {
              U[i][j] = 0.0;
            }
          }
        }
        
        return new Matrix(U);
    }
     public double elem(int i, int j) {
        return data[i][j];
    }
public double det() {
        /* zrob dekompozycje LU */
        if(N == M) {
            Matrix tmpLU = new Matrix(data).LUDecomposition();
            Matrix tmpU = tmpLU.macierzU();
            double det = (double) tmpLU.pivsign;

            for(int i = 0; i < N; i++) 
            {
                det *= tmpU.elem(i, i);
            }

            return det;
            }
        else return 0;
    }

   

}