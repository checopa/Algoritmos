class Arbol{
	int valor;
	float frec;
	Arbol izq;
	Arbol der;

	public Arbol(int valor,float frec){
		this.valor = valor;
		this.frec=frec;
		izq = null;
		der = null;
	}

	public Arbol(int valor,float frec, Arbol izq, Arbol der){
		this.valor = valor;
		this.frec = frec;
		this.izq = izq;
		this.der = der;
	}
}