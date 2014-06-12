/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package projekt3;

/**
 *
 * @author mwos
 */
public class Vector3D  {

    public float x;
    public float y;
    public float z;


    /**
        Creates a new Vector3D at (0,0,0).
    */
    public Vector3D() {
        this(0,0,0);
    }


    /**
        Creates a new Vector3D with the same values as the
        specified Vector3D.
    */
    public Vector3D(Vector3D v) {
        this(v.x, v.y, v.z);
    }


    /**
        Creates a new Vector3D with the specified (x, y, z) values.
    */
    public Vector3D(float x, float y, float z) {
        setTo(x, y, z);
    }

  
    public void setTo(Vector3D v) {
        setTo(v.x, v.y, v.z);
    }


   
    public void setTo(float x, float y, float z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }


    
    public void add(float x, float y, float z) {
        this.x+=x;
        this.y+=y;
        this.z+=z;
    }


    public void subtract(float x, float y, float z) {
        add(-x, -y, -z);
    }



    public void add(Vector3D v) {
        add(v.x, v.y, v.z);
    }


    public void subtract(Vector3D v) {
        add(-v.x, -v.y, -v.z);
    }


  
    public void multiply(float s) {
       x*=s;
       y*=s;
       z*=s;
    }


    public void divide(float s) {
       x/=s;
       y/=s;
       z/=s;
    }


    /**
        Returns the length of this vector as a float.
    */
    public float length() {
        return (float)Math.sqrt(x*x + y*y + z*z);
    }


    public String toString() {
        return "(" + x + ", " + y + ", " + z + ")";
    }


    public void setToCrossProduct(Vector3D u, Vector3D v) {
      
        float x = u.y * v.z - u.z * v.y;
        float y = u.z * v.x - u.x * v.z;
        float z = u.x * v.y - u.y * v.x;
        this.x = x;
        this.y = y;
        this.z = z;
    }


}
