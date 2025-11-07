package ar.unahur.edu.obj2.patroncommand.excepciones;

public class FueraDeRangoDeMemoriaException extends RuntimeException{

    public FueraDeRangoDeMemoriaException(){
        super("La direccion de memoria ingresada esta fuera del rango");
    }
}
