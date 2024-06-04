package pagamentos;

public class CartaoDeCredito implements Pagamento{

    @Override
    public void pagar() {
        System.out.println("Pagamento realizado com cartao de crédito");
    }
}
