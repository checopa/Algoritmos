import java.util.Scanner ;
import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.lang.Math;
public class PilaArena{
	public static void main(String[] args) {
    	
    	Scanner n = new Scanner(System.in);
    	int numero=0;
    	System.out.print("Numero de granos de arena? "); //Se pide al usuario el numero de granos de arena
    	numero=n.nextInt();
		int lg=(int) Math.round(Math.sqrt(numero)); //Aproximacion para el largo de la matriz 
		int largo=lg+1;                             //Se aumenta en uno para evitar problemas de dimension con alguno numeros de n
		int centro=(largo)/2;                       //Una buena aproximacion para el centro de la matriz
		int [][] matriz = new int [largo][largo] ;  //Creacion de la matriz de largo y ancho previamente calculado
		matriz[centro][centro]=numero;              //En el centro de la matriz se coloca la cantidad de granos de arena preguntada anteriormente
		int k=0;                                    //Contador para el numero de casillas donde no ocurre un derrumbe
		long contador=0;                             //Contador para el numero de derrumbes que ocurren
		while (k<(largo)*(largo)) {							//Realiza el proceso hasta que todas las celdas tengan menos de 4 granos
		k=0;
		for (int i=0;i<largo;i++){					//Permite recorrer cada fila de la matriz
			for (int j=0;j<largo;j++){				//Permite recorrer cada columna de la matriz
				if (matriz[i][j]<4) {				//Si una casilla tiene menos de 4 granos aumenta el contador de celdas sin derrumbe
					k++;
				}
				while (matriz[i][j]>=4){              //Mientras en una casilla quedan mas de 4 granitos sigue derrumbando
					matriz[i][j+1]=(matriz[i][j+1])+1;
					matriz[i][j-1]=(matriz[i][j-1])+1;
					matriz[i+1][j]=(matriz[i+1][j])+1;
					matriz[i-1][j]=(matriz[i-1][j])+1;
					matriz[i][j]=(matriz[i][j])-4;
					contador++;						  //Aumenta contador de las iteraciones
				}
				}
		}
		}
    	Ventana V = new Ventana(500,"Pila Arena") ;   //Se crea una ventana
    	V.mostrarMatriz(matriz);                      //Se muestra la matriz final en la ventana
    	System.out.print("El numero de derrumbes: "+contador); //Se imprime el numero de derrumbes realizados
	    }
}