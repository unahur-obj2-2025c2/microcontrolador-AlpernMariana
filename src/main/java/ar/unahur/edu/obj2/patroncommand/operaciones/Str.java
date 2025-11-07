package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Str extends Comando{
//Guarda el valor del acumulador A en la posicion addr de la memoria de datos
    private final Integer addr;

    public Str(Integer addr){
        this.addr = addr;
    }

    @Override
    protected void doExecute(Programable micro) {
        micro.setAddr(addr);;
    }

}
