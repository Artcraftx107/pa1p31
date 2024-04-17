import datos.*;

import java.util.Arrays;

public class PruebaDatos {
    public static void main(String[] args){
        if(args.length<3){
            System.out.println("Error, no hay valores suficientes");
            return;
        }
        double min = 0, max = 0;
        try{
            min=Double.parseDouble(args[0]);
            max=Double.parseDouble(args[1]);
        }catch(NumberFormatException exception){
            System.out.printf("Error, al convertir un valor a número real ("+exception.getMessage()+")");
            return;
        }
        String[] data = Arrays.copyOfRange(args, 2, args.length);
        Datos d1 = new Datos(data, min, max);
        System.out.println(d1);
        d1.setRango("0;4");
        System.out.println(d1);
        try{
            d1.setRango("15 25");
        }catch(DatosException e){
            System.out.println(e.getMessage());
        }
    }
}
