package PrietoJesus;

public class Promocional extends Persona{
	private String codigo;
	public Promocional(int edad, int altura, String entrada, String codigo) {
		super(edad, altura, entrada);
		this.codigo=codigo;
	}
	@Override
	public void Pagar() {
		if (entrada=="promocional") {
			//No se ponerlo, pongo 5 euros
			pago=5;
		}
	}
}
