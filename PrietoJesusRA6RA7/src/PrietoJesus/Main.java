package PrietoJesus;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc = new Scanner(System.in);
		Random r = new Random();
		LinkedList<Persona> cola = new LinkedList<>();
		Persona plazas[] = new Persona[4];
		int opcion, altura, edad, cont = 0;
		String fecha, entrada, codigo;
		boolean estado=false;
		do {
			System.out.println("Elige una opcion\n");
			System.out.println("1.Llegada de una persona a la cola");
			System.out.println("2.Ordenar la cola");
			System.out.println("3.Montar en la atraccion");
			System.out.println("4.Poner en marcha la atraccion");
			System.out.println("5.Parar la atraccion");
			System.out.println("6.Mostrar informacion");
			System.out.println("0.Nada");
			opcion = sc.nextInt();
			switch (opcion) {
			case 1:
				sc.nextLine();
				edad = ValidarFecha(sc);
				altura = r.nextInt(140) + 70;
				entrada = TipoEntrada(r);
				if (entrada.equalsIgnoreCase("promocional")) {
					do {
						System.out.println("Introduce el codigo promocional (de 1 a 3 letras y 2 digitos)");
						codigo = sc.nextLine();
					} while (!ValidarCodigo(codigo));
					cola.addLast(new Promocional(edad, altura, entrada, codigo));
				} else
					cola.addLast(new Persona(edad, altura, entrada));
				break;
			case 2:
				break;
			case 3:
				if (cola.size() < 4) {
					System.out.println("No hay suficientes persona en la cola");
				} else {
					cont=0;
					for (int i = 0; i < plazas.length; i++) {
						if (cola.get(i).getAltura() < 100 || cola.get(i).getAltura() > 190) {
							System.out.println("Altura no válida");
							cola.remove(i);
							i = i - 1;
						if (cola.get(i).getBono()>3) {
							cola.remove(i);
						}
							if (cola.size() < 4) {
								System.out.println("No hay suficientes persona en la cola");
								break;
							}
						} else {
							plazas[i] = cola.get(i);
							cont++;
						}
					}
					if (cont == 4) {
						for (int i = 0; i < 4; i++) {
							cola.get(i).Pagar();
							cola.remove(i);
						}
					}
				}
				break;
			case 4:
				if (cont==4) {
					estado=true;
				}
				break;
			case 5:
				estado=false;
				for (int i=0;i<plazas.length;i++) {
					if (plazas[i].getEntrada()=="bono" && plazas[i].getBono()<=3)
						cola.addLast(plazas[i]);
					plazas[i]=null;
				}
				break;
			case 6:
				System.out.println("COLA");
				for (Persona i:cola) {
					System.out.println(i);
				}
				System.out.println("ATRACCION");
				for (int i=0;i<plazas.length;i++) {
					System.out.println(plazas[i]);
				}
				System.out.println("Total recaudado = "+Persona.getTotal());
				System.out.println("Estado atraccion = "+estado);
				break;
			}
		} while (opcion != 0);
	}

	public static int ValidarFecha(Scanner sc) {
		boolean seguir = false;
		String fecha = null;
		int edad = 0;
		while (!seguir) {
			try {
				System.out.println("Introduce la fecha de nacimiento (yyyy-mm-dd)");
				fecha = sc.nextLine();
				LocalDate fecha_nacimiento = LocalDate.parse(fecha);
				seguir = true;
				if (fecha_nacimiento.getYear() < 1925 || fecha_nacimiento.isAfter(LocalDate.now())) {
					System.out.println("Error, fecha de nacimiento erronea");
					seguir = false;
				} else {
					long diff = ChronoUnit.YEARS.between(fecha_nacimiento, LocalDate.now());
					edad = (int) diff;
				}
			} catch (DateTimeParseException e) {
				System.out.println("Error, fecha de nacimiento erronea");
			}
		}
		return edad;
	}

	public static String TipoEntrada(Random r) {
		int num = r.nextInt(3) + 1;
		String entrada;
		if (num == 1) {
			entrada = "normal";
		} else if (num == 2)
			entrada = "bono";
		else
			entrada = "promocional";
		return entrada;
	}

	public static boolean ValidarCodigo(String codigo) {
		if (codigo.matches("[A-Za-z]{1,3}[0-9]{2}")) {
			return true;
		}
		System.out.println("Codigo incorrecto");
		return false;
	}
}
