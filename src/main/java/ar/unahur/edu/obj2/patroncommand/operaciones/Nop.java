package ar.unahur.edu.obj2.patroncommand.operaciones;

import ar.unahur.edu.obj2.patroncommand.microcontrolador.Programable;

public class Nop extends Comando{

    //NO REALIZA NINGUNA OPERACION. EL PROGRAMA CONTINUA CON LA SIGUIENTE INSTRUCCION

    @Override
    protected void doExecute(Programable micro) {
    }

}
