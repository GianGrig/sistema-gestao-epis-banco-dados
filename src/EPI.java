public class EPI {
    private int id;
    private String nome;
    private String validade;
    private int quantidade;
    public EPI(String nome, String validade, int quantidade) {
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
    }
    public EPI(int id, String nome, String validade, int quantidade) {
        this.id = id;
        this.nome = nome;
        this.validade = validade;
        this.quantidade = quantidade;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getValidade() {
        return validade;
    }

    public void setValidade(String validade) {
        this.validade = validade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }
}
