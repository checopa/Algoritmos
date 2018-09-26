import java.util.Scanner;

class Stack{

	Lista top;

	public Stack(){
		top = null;
	}

	public boolean estaVacio(){
		return top == null;
	}

	public void apilar(Arbol x){
		Lista newtop = new Lista(x);
		newtop.sig = top;
		top = newtop;
	}

	public Arbol desapilar(){
		if(estaVacio()){
			return top.val= null; //error
		}
		Arbol ans = top.val;
		top = top.sig;
		return ans;
	}


	public static Arbol evaluar(String expresion){

		String[] expr = expresion.split("");
		Stack stack = new Stack();
		

		for(String str : expr){
			//si el string es un numero, entonces se apila
			if(!str.equals("/") && !str.equals("*") && !str.equals("+") && !str.equals("-") ){
				Arbol arb=new Arbol(str);
				stack.apilar(arb);
			}
			//si no lo es, entonces se sacan los ultimos 2 numeros de la pila,
			//se operan segun corresponda y luego se apila el resultado
			else{
				Arbol op2 = stack.desapilar();
				Arbol op1 = stack.desapilar();
				Arbol ans = new Arbol(str,op1,op2);
				stack.apilar(ans);
			}
		}
		//el resultado de toda la operacion es el ultimo elemento que queda en la pila al 
		//terminar de revisar el string
		return stack.desapilar();
	}
	
	public static String funcionarbol(Arbol expresion){
			if(expresion.izq==null && expresion.der==null){
				return expresion.valor;
			}
			else{
				if(expresion.valor.equals("+")){
					return funcionarbol(expresion.izq)+"+"+funcionarbol(expresion.der);
				}
				if(expresion.valor.equals("-")){
					return funcionarbol(expresion.izq)+"-"+funcionarbol(expresion.der);
				}
				if(expresion.valor.equals("*")){
					if (funcionarbol(expresion.izq).equals("1") && funcionarbol(expresion.der).equals("1")) {
						return "1";
					}
					if (funcionarbol(expresion.der).equals("1")) {
						return funcionarbol(expresion.izq);
					}
					if (funcionarbol(expresion.izq).equals("1")) {
						return funcionarbol(expresion.der);
					}
					if (funcionarbol(expresion.izq).equals("0") || funcionarbol(expresion.der).equals("0")) {
						return "0";
					}
					else {
					return funcionarbol(expresion.izq)+"*"+funcionarbol(expresion.der);
				}
				}
				if(expresion.valor.equals("/")){
					if (funcionarbol(expresion.der).equals("1")) {
						return funcionarbol(expresion.izq);
					}
					else {
					return "("+funcionarbol(expresion.izq)+"/"+funcionarbol(expresion.der)+")";
				}
				}
			}
			return "2";
	}
	
	public static String derivacion(Arbol expresion, String variable){
		if (expresion.izq==null && expresion.der==null) {
			return derivar(expresion.valor,variable);
		}
		else {
			if (expresion.valor.equals("+")) {
				if (derivacion(expresion.izq,variable).equals("0")) {
					return derivacion(expresion.der,variable);
				}
				if (derivacion(expresion.der,variable).equals("0")) {
					return derivacion(expresion.izq,variable);
				}
				else {
				return "("+derivacion(expresion.izq,variable)+"+"+derivacion(expresion.der,variable)+")";
			}
			}
			if (expresion.valor.equals("-")) {
				if (derivacion(expresion.izq,variable).equals("0")) {
					return derivacion(expresion.der,variable);
				}
				if (derivacion(expresion.der,variable).equals("0")) {
					return derivacion(expresion.izq,variable);
				}
				else {
				return "("+derivacion(expresion.izq,variable)+"-"+derivacion(expresion.der,variable)+")";
			}
			}
			if (expresion.valor.equals("/")) {
				if ((derivacion(expresion.izq,variable).equals("0") || funcionarbol(expresion.der).equals("0")) && (derivacion(expresion.der,variable).equals("0") || funcionarbol(expresion.izq).equals("0"))) {
					return "0";
				}
				if (derivacion(expresion.izq,variable).equals("0")){
					if (funcionarbol(expresion.izq).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
							return derivacion(expresion.der,variable);
						}
						else {
						return "("+"-"+derivacion(expresion.der,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
					}
					}
					if (derivacion(expresion.der,variable).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
							return derivacion(expresion.izq,variable);
						}
						else {
						return "("+"-"+funcionarbol(expresion.izq)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
					}
					}
					else {	
					return "("+"-"+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
				}
				}
				if (derivacion(expresion.der,variable).equals("0")) {
					if (funcionarbol(expresion.der).equals("1")) {
							return derivacion(expresion.izq,variable);
					}
					if (derivacion(expresion.izq,variable).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
							return "1";
						}
						else {
						return "("+funcionarbol(expresion.der)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
					}
					}
					else {	
						if (funcionarbol(expresion.der).equals("1")) {
							return derivacion(expresion.izq,variable);
						}
						else {
					return "("+funcionarbol(expresion.der)+"*"+derivacion(expresion.izq,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
				}
					}
				}
					if (funcionarbol(expresion.izq).equals("0")) {
						if (funcionarbol(expresion.der).equals("1")) {
								return derivacion(expresion.izq,variable);
						}
						if (derivacion(expresion.izq,variable).equals("1")) {
							if (funcionarbol(expresion.der).equals("1")) {
								return "1";
							}
							else {
							return "("+funcionarbol(expresion.der)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
						}	
						}
						else {	
							if (funcionarbol(expresion.der).equals("1")) {
								return derivacion(expresion.izq,variable);
							}
							else {
						return "("+funcionarbol(expresion.der)+"*"+derivacion(expresion.izq,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
					}
						}
					}
				else {
					if (derivacion(expresion.izq,variable).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
						return "1"+"-"+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable);
						}
						else {
						return funcionarbol(expresion.der)+"-"+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der);
					}
					}
					if (funcionarbol(expresion.izq).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
							return derivacion(expresion.izq,variable)+"-"+"*"+derivacion(expresion.der,variable);
						}
							else {
								return derivacion(expresion.izq,variable)+"*"+funcionarbol(expresion.der)+"-"+derivacion(expresion.der,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der);
						}
					
					}
					if (derivacion(expresion.der,variable).equals("1")) {
						if (funcionarbol(expresion.der).equals("1")) {
						return derivacion(expresion.izq,variable)+"-"+funcionarbol(expresion.izq);
						}
						else {
							return derivacion(expresion.izq,variable)+"*"+funcionarbol(expresion.der)+"-"+funcionarbol(expresion.izq)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der);
					}
					}
				return "("+derivacion(expresion.izq,variable)+"*"+funcionarbol(expresion.der)+"-"+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable)+"/"+funcionarbol(expresion.der)+"*"+funcionarbol(expresion.der)+")";
			}
			}
			}
			if (expresion.valor.equals("*")) {
				if ((derivacion(expresion.izq,variable).equals("0") || funcionarbol(expresion.der).equals("0")) && (derivacion(expresion.der,variable).equals("0") || funcionarbol(expresion.izq).equals("0"))) {
					return "0";
				}
				if (derivacion(expresion.izq,variable).equals("0")){
					if (funcionarbol(expresion.izq).equals("1")) {
						return derivacion(expresion.der,variable);
					}
					if (derivacion(expresion.der,variable).equals("1")) {
						return funcionarbol(expresion.izq);
					}	
					else {	
					return "("+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable)+")";
				}
				}
				if (derivacion(expresion.der,variable).equals("0")) {
					if (funcionarbol(expresion.der).equals("1")) {
						return derivacion(expresion.izq,variable);
					}
					if (derivacion(expresion.izq,variable).equals("1")) {
						return funcionarbol(expresion.der);
					}	
					else {	
					return "("+funcionarbol(expresion.der)+"*"+derivacion(expresion.izq,variable)+")";
				}
				}
				else {
				return "("+derivacion(expresion.izq,variable)+"*"+funcionarbol(expresion.der)+"+"+funcionarbol(expresion.izq)+"*"+derivacion(expresion.der,variable)+")";
			}
			}
		return "2";
	}
	
	public static String derivar(String funcion, String variable) {
		if (funcion.equals(variable)){
			return "1";
		}
		else {
			return "0";
		}
	}

	public static void main(String[] args) {
		Stack stack = new Stack();
		Scanner sc = new Scanner(System.in);
		System.out.println("funcion en polaca inversa?: ");
		String s = sc.nextLine();
		Scanner var = new Scanner(System.in);
		System.out.println("Variable respecto a la que se deriva?: ");
		String variable = var.nextLine();
		System.out.println("La funcion es: ");
		System.out.println(funcionarbol(evaluar(s)));
		System.out.println("La derivada respecto a "+variable+" es: ");
		System.out.println(derivacion(evaluar(s),variable));

	}
}