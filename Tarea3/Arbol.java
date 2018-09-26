class Arbol{
	String valor;
	Arbol izq;
	Arbol der;

	public Arbol(String valor){
		this.valor = valor;
		izq = null;
		der = null;
	}

	public Arbol(String valor, Arbol izq, Arbol der){
		this.valor = valor;
		this.izq = izq;
		this.der = der;
	}
}