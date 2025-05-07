public class Devolucao {
    private int id_devolucao;
    private Emprestimo emprestimo;
    private String data_devolucao;

    public Devolucao(Emprestimo emprestimo, String data_devolucao) {
        this.emprestimo = emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public Devolucao(int id_devolucao, Emprestimo emprestimo, String data_devolucao) {
        this.id_devolucao = id_devolucao;
        this.emprestimo = emprestimo;
        this.data_devolucao = data_devolucao;
    }

    public int getId_devolucao() {
        return id_devolucao;
    }

    public void setId_devolucao(int id_devolucao) {
        this.id_devolucao = id_devolucao;
    }

    public int getId_Emprestimo() {
        return emprestimo.getId_emprstimo();
    }

    public String getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(String data_devolucao) {
        this.data_devolucao = data_devolucao;
    }
}
