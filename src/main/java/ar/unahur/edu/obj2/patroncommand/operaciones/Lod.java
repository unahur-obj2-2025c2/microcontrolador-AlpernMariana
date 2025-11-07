package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Lod extends Comando{

    // carga el acumulador A con el contenido de la memoria de datos en la posicion addr
    private final Integer addr; // direccion en memoria

    //creamos constructor:
    public Lod(Integer addr){
        this.addr = addr;
    }

    @Override
    protected void doExecute(Programable micro) {
        micro.setAcumuladorA(micro.getAddr(addr));
    }

}
