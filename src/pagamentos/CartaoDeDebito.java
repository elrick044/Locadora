package pagamentos;

public class CartaoDeDebito implements Pagamento{
    @Override
    public void pagar() {
        System.out.println("Pagamento realizado com cartao de débito");
    }
}

