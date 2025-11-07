package ar.unahur.edu.obj2.patroncommand;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.invoker.Programa;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;
import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;
import ar.unahur.edu.obj2.patroncommand.operaciones.Add;
import ar.unahur.edu.obj2.patroncommand.operaciones.Lod;
import ar.unahur.edu.obj2.patroncommand.operaciones.LodV;
import ar.unahur.edu.obj2.patroncommand.operaciones.Nop;
import ar.unahur.edu.obj2.patroncommand.operaciones.Str;
import ar.unahur.edu.obj2.patroncommand.operaciones.Swap;

public class ProgramaTest {

    //Creamos un programa

    private Programa p = new Programa();
    private Programable micro = new Microcontrolador();

    @BeforeEach
    void setUp(){
        p.vaciarLista();
        p.resetearMicro(micro);
    }

    @Test 
    void avanzarTresPosicionesElProgramCounter(){
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());
        p.agregarOperacion(new Nop());

        p.ejecutar(micro);

        assertEquals(3, micro.getProgramCounter());
    }

    @Test
    void sumar20Mas17YObtener37EnAcumuladorA(){
        p.agregarOperacion(new LodV(20)); //carga 20 en acumulador A mientras B es 0
        p.agregarOperacion(new Swap());//invierte los valores A= 0, B= 20
        p.agregarOperacion(new LodV(17));//carga 17 en A
        p.agregarOperacion(new Add());// suma los valores y guarda el resultado en A y color el valor 0 en B

        p.ejecutar(micro);

        assertEquals(37, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        assertEquals(4, micro.getProgramCounter());
    }

    @Test
    void sumar2Mas8Mas5EnAcumuladorA(){
        p.agregarOperacion(new LodV(2));//CARGAMOS 2 EN VALOR A
        p.agregarOperacion(new Str(0));//GUARDA EN LA POSICION 0 EL VALOR 2
        p.agregarOperacion(new LodV(8));//CARGA 8 EN A
        
        p.agregarOperacion(new Swap());//INVIERTE EL A Y EL B. 0 EN A, 8 EN B
        p.agregarOperacion(new LodV(5));//CARGA 5 EN A
        p.agregarOperacion(new Add());//SUMA LOS VALORES Y QUEDA EL RESULTADO EN A Y 0 EN B

        p.agregarOperacion(new Swap());//INVIERTE A Y B. QUEDA 0 EN A Y A+B (RESULTADO DE LA SUMA)EN B
        p.agregarOperacion(new Lod(0));//CARGA EN A EL VALOR EN LA POSICION DE MEMORIA 0, ES DECIR EL VALOR 2
        p.agregarOperacion(new Add());//SUMA 2 + LA SUMA DE 8 + 5, GUARDA EL RESULTADO EN A Y 0 EN B

        p.ejecutar(micro);

        assertEquals(15, micro.getAcumuladorA());
        assertEquals(0, micro.getAcumuladorB());
        
    }
}
