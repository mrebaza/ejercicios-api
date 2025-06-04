package com.rebaza.api;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.rebaza.api.ejercicios.EjerciciosBiConsumer;
import com.rebaza.api.ejercicios.EjerciciosBiFunction;
import com.rebaza.api.ejercicios.EjerciciosBiPredicate;
import com.rebaza.api.ejercicios.EjerciciosBinaryOperator;
import com.rebaza.api.ejercicios.EjerciciosCollect;
import com.rebaza.api.ejercicios.EjerciciosConsumer;
import com.rebaza.api.ejercicios.EjerciciosCount;
import com.rebaza.api.ejercicios.EjerciciosDistinct;
import com.rebaza.api.ejercicios.EjerciciosFindFirstAny;
import com.rebaza.api.ejercicios.EjerciciosFlatMap;
import com.rebaza.api.ejercicios.EjerciciosFunction;
import com.rebaza.api.ejercicios.EjerciciosMatch;
import com.rebaza.api.ejercicios.EjerciciosPeek;
import com.rebaza.api.ejercicios.EjerciciosPredicate;
import com.rebaza.api.ejercicios.EjerciciosSorted;
import com.rebaza.api.ejercicios.EjerciciosSupplier;
import com.rebaza.api.ejercicios.EjerciciosUnaryOperator;
import com.rebaza.api.models.Persona;

@SpringBootApplication
public class EjerciciosApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(EjerciciosApiApplication.class, args);
		
		// ************************************************************************ //
		EjerciciosPredicate.ejercicio1();
		EjerciciosPredicate.ejercico2();
		
		EjerciciosFunction.ejercicio1();
		EjerciciosFunction.ejercicio1();
		
		EjerciciosConsumer.ejercicio1();
		EjerciciosConsumer.ejercicio2();
		
		EjerciciosSupplier.ejercicio1();
		EjerciciosSupplier.ejercicio2();
		
		EjerciciosUnaryOperator.ejercicio1();
		EjerciciosUnaryOperator.ejercicio2();
		
		EjerciciosBinaryOperator.ejercicio1();
		EjerciciosBinaryOperator.ejercicio2();
		
		EjerciciosBiFunction.ejercicio1();
		EjerciciosBiFunction.ejercicio2();
		
		EjerciciosBiPredicate.ejercicio1();
		EjerciciosBiPredicate.ejercicio2();
		
		EjerciciosBiConsumer.ejercicio1();
		EjerciciosBiConsumer.ejercicio2();
		
		EjerciciosFlatMap.ejercicio1();
		EjerciciosFlatMap.ejercicio2();

		EjerciciosDistinct.ejercicio1();
		EjerciciosDistinct.ejercicio2();
				
		EjerciciosSorted.ejercicio1();
		EjerciciosSorted.ejercicio2();
		
		EjerciciosPeek.ejercicio1();
		EjerciciosPeek.ejercicio2();
		
		
		EjerciciosCollect.ejercicio1();
		EjerciciosCollect.ejercicio2();
		
		EjerciciosCount.ejercicio1();
		EjerciciosCount.ejercicio2();
		
		EjerciciosMatch.ejercicioAnyMatch1();
		EjerciciosMatch.ejercicioAnyMatch2();
		
		EjerciciosMatch.ejercicioAllMatch1();
		EjerciciosMatch.ejercicioAllMatch2();
		
		EjerciciosMatch.ejercicioNoneMatch1();
		EjerciciosMatch.ejercicioNoneMatch2();

		EjerciciosFindFirstAny.ejercicioFindFirst1();
		EjerciciosFindFirstAny.ejercicioFindFirst2();
		
		EjerciciosFindFirstAny.ejercicioFindAny1();
		EjerciciosFindFirstAny.ejercicioFindAny2();
		
		// ************************************************************************ //
		
		
		// Converti una lista de nombres en mayusculas		
		List<String> nombres = List.of("miguel", "ana", "juan", "aron", "raul", "angel","Miguel", "Juan");
		
		// Uso de MAP
		// Convertir los nombres en mayusculas
		List<String> nombresMayusculas = nombres.stream()
				.map(n -> n.toUpperCase())
				.collect(Collectors.toList());
		
		// System.out.println(nombresMayusculas);
		
		List<Persona> personas = List.of(
				new Persona("Miguel", "Rebaza", 34),
				new Persona("Yoselin", "Rebaza", 34),
				new Persona("Lucia", "Apaza", 34),
				new Persona("Harold", "Apaza", 34),
				new Persona("Angel", "Gutierrez", 23),
				new Persona("Maria", "Alcantara", 53),
				new Persona("Daniel", "Tamashiro", 45),
				new Persona("Raul", "Serrano", 12),
				new Persona("Juan", "Petreli", 14),
				new Persona("Gregorio", "Torres", 18),
				new Persona("Aron","Carrasco",22),
				new Persona("Mario","Carrasco",22),
				new Persona("Frank","Carrasco",21)
				);
		
		// Obter las edades en una lista
		List<Integer> edades = personas.stream()
				.map(p -> p.getEdad())
				.collect(Collectors.toList());
		
		// System.out.println(edades);
		
		// Uso de FILTER
		// Filtrar las personas mayores a 18 años.
		List<Persona> adultos = personas.stream()
				.filter(p -> p.getEdad() > 18)
				.collect(Collectors.toList());
		
		// System.out.println(adultos);
		
		// Filtrar las persoans que sus nombres comienzen 
		// por la letra a.
		List<Persona> nombresA = personas.stream()
				.filter(p -> p.getNombres().toLowerCase().startsWith("a"))
				.collect(Collectors.toList());
		
		// System.out.println(nombresA);
		
		// Uso de FLATMAP
		List<List<String>> frases = List.of(
				List.of("Hola", "mundo", "con", "java"),
				List.of("Java","API","Stream","programacion","funcinal")
				);
		
		// Crear una lista de todas las las palabras en una sola lista.
		List<String> todasPalabras = frases.stream()
				.flatMap(f -> f.stream())
				.collect(Collectors.toList());
		
		// System.out.println(todasPalabras);
		
		//Unir las palabras dentro de una sola lista de string.
		List<String> lineas = List.of("Hola mundo desde java", "API Stream para desarrollar aplicaciones con programación funcional");
		
		List<String> palabras = lineas.stream()
				.flatMap(linea -> Arrays.stream(linea.split(" ")))
				.collect(Collectors.toList());
		
		// System.out.println(palabras);
	
	
		// Uso de RECUDE (sumar)
		// Sumas todas las edades de una lista de personas.
		int sumaEdades = personas.stream()
				.map(p -> p.getEdad())
				.reduce(0, Integer::sum);
		
		// System.out.println(sumaEdades);
				
		// unir todos los nombres en una sola variable String.
		String todos = nombres.stream()
				.reduce("",  (a,b) -> a + " " + b)
				.trim();
		
		// System.out.println(todos);
		
		// uso de COLLECT
		// Crear un Set de nombres unicos 
		Set<String> nombresUnicos = nombres.stream()
				.collect(Collectors.toSet());
		
		// System.out.println(nombresUnicos);
		
		// Agrupar las personas por apellidos.
		Map<String, List<Persona>> agrupado = personas.stream()
				.collect(Collectors.groupingBy(p -> p.getApellidos()));
		
		// System.out.println(agrupado);
		
		// uso de SORTED
		// Ordenar las personas por edad.
		List<Persona> ordenadoEdad = personas.stream()
				.sorted(Comparator.comparing(p -> p.getEdad()))
				.collect(Collectors.toList());
		
		// System.out.println(ordenadoEdad);
		
		// Ordenar las personas por nombre
		List<Persona> ordenadoNombre = personas.stream()
				.sorted(Comparator.comparing(Persona::getNombres).reversed())
				.collect(Collectors.toList());
		
		// System.out.println(ordenadoNombre);
		
		// Uso de DISTINCT
		// Eliminar a las personas con edad iguales.
		List<Integer> sinRepetidos = personas.stream()
				.map(p -> p.getEdad())
				.distinct()
				.collect(Collectors.toList());
		
		// System.out.println(sinRepetidos);
		
		// eliminar las palabras iguales en una lista.
		List<String> sinDuplicados = palabras.stream()
				.distinct()
				.collect(Collectors.toList());
		
		// System.out.println(sinDuplicados);
		
		// Uso de PEEK
		// Mostrar resultado en el proceso de ejecucion del
		// flujo.
		List<String> resultado = nombres.stream()
				//.peek(n -> System.out.println("Antes del map: " + n ))
				.map(String::toUpperCase)
				//.peek(n -> System.out.println("Despues del map: " + n))
				.collect(Collectors.toList());
		
		// Mostrar personas por grupo de edad.
		Map<Integer, List<Persona>> porEdad = personas.stream()
				.collect(Collectors.groupingBy(Persona::getEdad));
		
		// System.out.println(porEdad);
		
		// Contar las personas por edad.
		Map<String, Long> conteo = personas.stream()
				.collect(Collectors.groupingBy(Persona::getApellidos
												,Collectors.counting()));
		
		// System.out.print(conteo);
		
		// Separar menores de mayores de edad.
		Map<Boolean, List<Persona>> particion = personas.stream()
				.collect(Collectors.partitioningBy(p -> p.getEdad() > 18));
		
		// System.out.println(particion);
		
		// Separar nombres largo y cortos
		Map<Boolean, List<Persona>> largos = personas.stream()
				.collect(Collectors.partitioningBy(p -> p.getNombres().length() > 4));
		
		// System.out.println(largos);
	}
	

}
