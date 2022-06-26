public class Metro{
	private int estado;
	static final private int PARADO = 0;
	static final private int EN_MARCHA = 1;
	static final private int PARANDO = 2;
	static final private int ARRANCANDO = 3;
	
	public void cambiaEstado(){
		if (estado == PARADO){
			estado = ARRANCANDO;
		}else if(estado == EN_MARCHA){
			estado = PARANDO;
		}else if( estado == ARRANCANDO){
			estado = EN_MARCHA;
		}else {
			throw new RuntimeException("Estado desconocido");
		}
	}
}

//------------------------------------------------------------------------------------
//REFACTORIZACIÓN (Paso 1)--> Crear una clase por cada posible estado, sustituir == por instanceOf y las variables estáticas por referencias a instancias de esas clases

public interface Estado{
}

public class Parado implements Estado{
}

public class Arrancando implements Estado{
}

public class Parando implements Estado{
}

public class Metro {
	private Estado estado;
	static final private Estado PARADO = new Parado();
	static final private Estado EN_MARCHA = new EnMarcha();
	static final private Estado PARANDO = new Parando();
	static final private Estado ARRANCANDO = new Arrancando();
	
	public void cambiaEstado(){
		if(estado instanceOf Parado){
			estado = ARRANCANDO;
		}else if (estado instanceOf EnMarcha){
			estado = PARANDO;
		}else if (estado instanceOf Parando){
			estado = PARADO;
		}else if (estado instanceOf Arrancando){
			estado = EN_MARCHA;
		}else {
			throw new RunTimeException ("Estado desconocido");
		}
	}
}


//------------------------------------------------------------------------------------
//REFACTORIZACIÓN (Paso 2) --> Crear el método siguiente() dentro de cada clase para que devuelva el siguiente estado, eliminar los if y los throw

public interface Estado{
	public Estado siguiente();
}

public class Parado implements Estado{
	public Estado siguiente(){
		return new Arrancando();
	}
}


public class Arrancando implements Estado{
	public Estado siguiente(){
		return new EnMarcha();
	}
}


public class Parando implements Estado{
	public Estado siguiente(){
		return new Parado();
	}
}

public class EnMarcha implements Estado{
	public Estado siguiente(){
		return new Parando();
	}
}

public class Metro{
	private Estado estado;
	
	public void cambiaEstado(){
		estado = estado.siguiente();
	}
	
	public Estado conseguirEstado(){
		return estado;
	}
	
	public void iniciarEstado(){
		estado = new Parado();
	}
}
