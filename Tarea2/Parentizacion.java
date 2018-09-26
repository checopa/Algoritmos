import java.util.Scanner;

public class Parentizacion {
	public static void main(String[] args) {
		String dim;
		Scanner d = new Scanner(System.in);
		System.out.println("Dimensiones de las matrices?: ");
		while (d.hasNextLine()){
			dim=d.nextLine();
			String[] Dim=dim.split(" ");
			int[] listdim= new int[Dim.length];
			if ("".equals(dim)){
				System.out.print("EOF");
				break;
			
			}
			else{
			for (int i=0; i<Dim.length ; i++ ){
				listdim[i]=Integer.parseInt(Dim[i]);
			}
			int[][] c=multmatrices(listdim);
			System.out.println("La parentizacion optima es: ");
			parentizar(c,1,(listdim.length)-1);
			System.out.println("");
	}
	}
	}
		
	public static void parentizar(int[][] s, int i, int j){
		if (i<j){
			System.out.print("(");
			parentizar(s,i,s[i][j]);
			parentizar(s,(s[i][j])+1,j);
			System.out.print(")");
			}
		else {
			System.out.print(".");
		}
	}
	
	public static int[][] multmatrices( int[] listdim) {
		int n=listdim.length-1;
		int[][] m= new int [n+1][n+1];
		int[][] s= new int [n+1][n+1];
		for (int i=1; i<=n;i++){
			m[i][i]=0;
			}
		for (int l=2; l<=n; l++){
			for (int i=1;i<=(n-l+1);i++){
				int j=i+l-1;
				m[i][j]=Integer.MAX_VALUE;
				for (int k=i; k<=j-1;k++){
					int q=m[i][k] + m[k+1][j]+listdim[i-1]*listdim[k]*listdim[j];
					if (q<m[i][j]){
						m[i][j]=q;
						s[i][j]=k;
					}
				}
			}
		}
		return s;	
		}

}