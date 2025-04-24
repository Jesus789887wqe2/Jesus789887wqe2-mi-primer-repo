package PrietoJesus;

public class Persona {
	private int edad, altura, bono=1;
	protected String entrada;
	protected double pago;
	private static double total;
	
	public Persona (int edad,int altura, String entrada) {
		this.edad=edad;
		this.altura=altura;
		this.entrada=entrada;
	}

	public int getAltura() {
		return altura;
	}
	public void Pagar() {
		if (entrada=="normal") {
			if (edad<18)
				pago=3;
			else
				pago=4;
		}
		if (entrada=="bono" && bono==1) {
			if (edad<18)
				pago=4;
			else
				pago=10;
			bono++;
		}
		total+=pago;
	}

	public int getBono() {
		return bono;
	}

	public String getEntrada() {
		return entrada;
	}

	@Override
	public String toString() {
		return "Persona [edad=" + edad + ", altura=" + altura + ", entrada=" + entrada + "]";
	}

	public static double getTotal() {
		return total;
	}
	
}
