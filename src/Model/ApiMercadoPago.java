package Model;

public class ApiMercadoPago {
    private boolean pago=true;
    private static ApiMercadoPago instancia;

    private ApiMercadoPago(){}
    public static ApiMercadoPago getInstancia() {
        if(instancia==null){
            instancia=new ApiMercadoPago();
        }
        return instancia;
    }
}
