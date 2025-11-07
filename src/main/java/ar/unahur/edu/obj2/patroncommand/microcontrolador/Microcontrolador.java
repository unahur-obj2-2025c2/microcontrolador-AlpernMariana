package ar.unahur.edu.obj2.patroncommand.microcontrolador;

import java.util.Arrays;
import java.util.List;

import ar.unahur.edu.obj2.patroncommand.excepciones.FueraDeRangoDeMemoriaException;
import ar.unahur.edu.obj2.patroncommand.operaciones.Operable;

public class Microcontrolador implements Programable {

    private Integer acumuladorA = 0;
    private Integer acumuladorB = 0;
    private Integer programCounter = 0;
    private List<Integer> memoria = Arrays.asList(new Integer[1024]); // creo un valor fijo de 1024 valores

    public Microcontrolador(){
        this.reset();
    }
    

    @Override
    public void run(List<Operable> operaciones) {
        // Ejecutar las operaciones que recibe. recorrer lista y ejecutarlas
        // forEach uno de los pocos metodos que no necesita entrar a stream para
        // recorrer
        operaciones.forEach(o -> o.execute(this));
    }

    @Override
    public void incProgramCounter() {
        // aumentar en uno
        programCounter += 1;
    }

    @Override
    public Integer getProgramCounter() {
        return programCounter;
    }

    @Override
    public void setAcumuladorA(Integer value) {
        acumuladorA = value;
    }

    @Override
    public Integer getAcumuladorA() {
        return acumuladorA;
    }

    @Override
    public void setAcumuladorB(Integer value) {
        acumuladorB = value;
    }

    @Override
    public Integer getAcumuladorB() {
        return acumuladorB;
    }

    @Override
    public void setAddr(Integer addr) {
        // para guardar un valor en la memoria, tenemos que buscar la direccion en
        // memoria
        // lo que se guarda es lo que esta en el acumuladorA
        // con set guardamos en la posicion que queremos, va en parametro: (posicion,
        // loQueQueremosGuardar)
        estaDentroDelRangoDeMemoria(addr);
        memoria.set(addr, acumuladorA);
    }

    @Override
    public Integer getAddr(Integer addr) {
        estaDentroDelRangoDeMemoria(addr); //no hace falta el IF. no hay ambiguedades...
        return memoria.get(addr); // lee lo que hay en la memoria en esa ubicacion
    }

    // metodo auxiliar:

    private void estaDentroDelRangoDeMemoria(Integer direccionEnMemoria) {
        if (direccionEnMemoria < 0 || direccionEnMemoria >= memoria.size()) {
            throw new FueraDeRangoDeMemoriaException();
        }
    }

    @Override
    public void reset() {
        acumuladorA = 0;
        acumuladorB = 0;
        programCounter = 0;
        memoria = Arrays.asList(new Integer[1024]);
    }


    @Override
    public Programable copiar() {
        Microcontrolador nuevo = new Microcontrolador();
        nuevo.acumuladorA = this.acumuladorA;
        nuevo.acumuladorB = this.acumuladorB;
        nuevo.programCounter = this.programCounter;
        return nuevo;
    }


    @Override
    public void copiarDesde(Programable microDeRespaldo) {
        this.acumuladorA = microDeRespaldo.getAcumuladorA();
        this.acumuladorB = microDeRespaldo.getAcumuladorB();
        this.programCounter = microDeRespaldo.getProgramCounter();
    }

}
