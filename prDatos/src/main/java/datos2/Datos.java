package datos2;


import java.util.ArrayList;
import java.util.List;


public class Datos {
    private List<Double> datos;
    private List<String> errores;
    private double min;
    private double max;
    public Datos(String[] args, double minim, double maxi){
        this.min=minim;
        this.max=maxi;
        this.datos=new ArrayList<>();
        this.errores=new ArrayList<>();
        for(int i = 0; i<args.length; i++){
            try{
                double aux = Double.parseDouble(args[i]);
                    datos.add(aux);
            }catch (NumberFormatException e){
                errores.add(args[i]);
            }
        }
    }
    public double calcMedia() throws DatosException {
        double aux = 0;
        int elms_good = 0;
        for(int i = 0; i<datos.size(); i++){
            if(datos.get(i)>=min && datos.get(i)<=max){
                aux += datos.get(i);
                elms_good++;
            }
        }
        if(aux==0){
            throw new DatosException("No hay datos en el rango especificado");
        }
        return aux/elms_good;
    }
    public double calcDesvTipica() throws DatosException{
        double media = calcMedia();
        double aux = 0;
        int elms_good = 0;
        for(int i = 0; i<datos.size(); i++){
            if(datos.get(i)>=min && datos.get(i)<=max){
                aux+=Math.pow((datos.get(i)-media), 2);
                elms_good++;
            }
        }
        return Math.sqrt(aux/elms_good);
    }
    public void setRango(String s)throws DatosException{
        int indexOfChar=s.indexOf(';');
        if(indexOfChar<0){
            throw new DatosException("Error en los datos al establecer el rango");
        }
        String num1 = s.substring(0, indexOfChar);
        String num2 = s.substring(indexOfChar+1);
        try{
            double aux = Double.parseDouble(num1);
            double aux2 = Double.parseDouble(num2);
            this.min=aux;
            this.max=aux2;
        }catch(NumberFormatException e){
            throw new DatosException("Error en los datos al establecer el rango");
        }
    }
    public List<Double> getDatos(){
        return datos;
    }
    public List<String> getErrores() {
        return errores;
    }

    @Override
    public String toString() {
        String aux;
        try{
            aux = "Min: "+this.min+", Max: "+this.max+", "+this.datos+", "+this.errores+", Media: "+this.calcMedia()+", DesvTipica: "+this.calcDesvTipica();
        }catch(DatosException e){
            aux ="Min: "+this.min+", Max: "+this.max+", "+this.datos+", "+this.errores+", Media: ERROR, DesvTipica: ERROR";
        }
        return aux;
    }
}
