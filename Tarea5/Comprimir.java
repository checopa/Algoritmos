import java.util.HashMap;
import java.util.Map;
import java.io.*;
import java.util.Scanner;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.ArrayList;

public class Comprimir {
	public static void codigoarbol(Arbol expresion,String codigo,String x,HashMap <Integer,String> map2) {
    	codigo=codigo+x;
        if (expresion.izq==null && expresion.der==null) {
        	map2.put(expresion.valor,codigo);
        }
        else {
        	codigoarbol(expresion.der,codigo,"1",map2);
        	codigoarbol(expresion.izq,codigo,"0",map2);
        }
    }
    public static Arbol generaarbol(ArrayList<Arbol> listar) {
        boolean seguir=true;
        while(seguir) {
            int k=0;
            int j=0;
            if (listar.get(0).frec<listar.get(1).frec) {
            	k=0;
            	j=1;
            }
            else {
            	j=0;
            	k=1;
            }
        	for (int i=2 ; i<listar.size() ; i++) {
        		if (listar.get(i).frec<=listar.get(j).frec && listar.get(i).frec>=listar.get(k).frec) {
        			j=i;
        		}
        		if (listar.get(i).frec<=listar.get(k).frec && listar.get(i).frec<=listar.get(j).frec) {
        			j=k;
        			k=i;
        		}
        	}
        	Arbol arb1=new Arbol(0,listar.get(j).frec+listar.get(k).frec,listar.get(k),listar.get(j));
        	if (k<j) {
        		listar.remove(j);
        		listar.remove(k);
        	}
        	else {
        		listar.remove(k);
        		listar.remove(j);
        	}
        	listar.add(arb1);
        	if (listar.size()==1) {
        		seguir=false;
        	}
        }
        return listar.get(0);
    }
    public static void main(String[] args)throws Exception {
    	float cant= 0;
    	Scanner sc = new Scanner(System.in);
		System.out.println("Texto Entrada: ");
		String s = sc.nextLine();
    	String nombreFichero = s;
		System.out.println("Texto Salida: ");
		String d = sc.nextLine();
    	String nombreSalida = d;
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		FileReader fr = new FileReader(nombreFichero);
		FileWriter fw = new FileWriter(nombreSalida,true);
		BufferedWriter bWrite = new BufferedWriter(fw);
		int cantcarac= 0;
        int caract = fr.read();
        while(caract != -1) {
        	if (map.containsKey(caract)){
        		map.put(caract,map.get(caract)+1);
        	}
        	else{
        		map.put(caract,1);
        		cantcarac=cantcarac+1;
        	}
        	cant=cant+1;
            caract = fr.read();
        }
        Iterator it1 = map.entrySet().iterator();
    	ArrayList<Arbol> listar= new ArrayList<Arbol>(cantcarac);
        while (it1.hasNext()) {
            Map.Entry e = (Map.Entry)it1.next();
            Integer cant1= (Integer)e.getValue();
            Float frec =(cant1/cant)*100;
            String car = String.valueOf(e.getKey()) ;
        	int car1=Integer.parseInt(car);
        	Arbol arb=new Arbol(car1,frec);
        	listar.add(arb);
        }
        HashMap<Integer, String> map2 = new HashMap<Integer,String>();
        Arbol arb1=generaarbol(listar);
        codigoarbol(arb1,"","",map2);
        Iterator it = map.entrySet().iterator();
        bWrite.append("Caracter "+"\r\t"+"Codigo ASCII"+"\r\t"+"Frecuencia de Aparicion"+"\r\t"+" "+"Porcentaje de Frecuencia"+"\r\t"+"Codificacion"+"\r\n");
        int larcom=0;
        int larnor=0;
        while (it.hasNext()) {
            Map.Entry e = (Map.Entry)it.next();
            Integer cant1= (Integer)e.getValue();
            String Frec = Float.toString((cant1/cant)*100);
        	String car = String.valueOf(e.getKey()) ;
        	int car1=Integer.parseInt(car);
        	char carac= (char)car1;
        	String carc=Character.toString(carac);
        	if (car1==10) {
        		carc="\\n";
        	}
        	if (car1==13) {
        		carc="\\r";
        	}
        	if (car1==32) {
        		carc="espacio";
        	}
        	int lar=String.valueOf(map2.get(car1)).length();
            bWrite.append(carc +"\r\t"+"\r\t"+ e.getKey() +"\r\t"+"\r\t"+ e.getValue()+"\r\t"+"\r\t"+"\r\t"+" "+Frec+"\r\t"+"\r\t"+"\r\t"+map2.get(car1)+"\r\n");
            larcom=larcom+lar*cant1;
            larnor=larnor+cant1*8;
        }
        bWrite.close();
        System.out.println("Largo comprimido= "+Integer.toString(larcom));
        System.out.println("Largo original= "+Integer.toString(larnor));
    }
}