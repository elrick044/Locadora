package pagamentos;

public class Dinheiro implements Pagamento{
    @Override
    public void pagar() {
        System.out.println("Pagamento realizado com dinheiro");
    }

    @Override
    public String obterMetodo() {
        return "DINHEIRO";
    }
}
