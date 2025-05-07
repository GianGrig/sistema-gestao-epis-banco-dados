public class Emprestimo{
    private int id_emprstimo;
    private Usuario usuario;
    private EPI epi;
    private String data_retirda;
    private String data_prevista_devolucao;
    private boolean confirmacao_retirada;

    public Emprestimo(Usuario usuario, EPI epi, String data_retirda, String data_prevista_devolucao, boolean confirmacao_retirada) {
        this.usuario = usuario;
        this.epi = epi;
        this.data_retirda = data_retirda;
        this.data_prevista_devolucao = data_prevista_devolucao;
        this.confirmacao_retirada = confirmacao_retirada;
    }

    public Emprestimo(int id_emprstimo, Usuario usuario, EPI epi, String data_retirda, String data_prevista_devolucao, boolean confirmacao_retirada) {
        this.id_emprstimo = id_emprstimo;
        this.usuario = usuario;
        this.epi = epi;
        this.data_retirda = data_retirda;
        this.data_prevista_devolucao = data_prevista_devolucao;
        this.confirmacao_retirada = confirmacao_retirada;
    }

    public int getId_emprstimo() {
        return id_emprstimo;
    }

    public void setId_emprstimo(int id_emprstimo) {
        this.id_emprstimo = id_emprstimo;
    }

    public int getId_Usuario() {
        return usuario.getId_usuario();
    }

    public int getId_Epi() {
        return epi.getId();
    }

    public String getData_retirda() {
        return data_retirda;
    }

    public void setData_retirda(String data_retirda) {
        this.data_retirda = data_retirda;
    }

    public String getData_prevista_devolucao() {
        return data_prevista_devolucao;
    }

    public void setData_prevista_devolucao(String data_prevista_devolucao) {
        this.data_prevista_devolucao = data_prevista_devolucao;
    }

    public boolean isConfirmacao_retirada() {
        return confirmacao_retirada;
    }

    public void setConfirmacao_retirada(boolean confirmacao_retirada) {
        this.confirmacao_retirada = confirmacao_retirada;
    }
}
