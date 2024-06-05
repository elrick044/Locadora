package pagamentos;

public class Pix implements Pagamento{
    @Override
    public void pagar() {
        System.out.println("Pagamento realizado com Pix");
    }

    @Override
    public String obterMetodo() {
        return "PIX";
    }
}
