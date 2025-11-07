package ar.unahur.edu.obj2.patroncommand.excepciones;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Microcontrolador;

public class FueraDeRangoDeMemoriaExceptionTest {
    
    @Test
    public void lanzaExcepcionAlLeerDireccionFueraDeRango() {
        Microcontrolador micro = new Microcontrolador();

        assertThrows(FueraDeRangoDeMemoriaException.class, () -> {
            micro.getAddr(2000); // fuera del rango permitido (0–1023)
        });
    }
    

}
