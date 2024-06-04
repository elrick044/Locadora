package model;

import java.util.Date;

public class Aluguel {
    private int id;
    private Cliente cliente;
    private Midia midia;
    private Date dataAluguel;
    private Date DueDate;
    private Date dataDevolucao;
    private double preco;

    //private bool IsReturned => ReturnDate.HasValue;
}
