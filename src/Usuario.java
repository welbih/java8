class Usuario {
    private String nome;
    private int pontos;
    private boolean moderador;

    public Usuario() { }

    public Usuario(String nome, int pontos) {
        this.nome = nome;
        this.pontos = pontos;
        this.moderador = false;
    }

    public Usuario(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }
    public int getPontos() {
        return pontos;
    }
    public boolean isModerador() {
        return moderador;
    }

    public void tornaModerador() {
        this.moderador = true;
    }

    @Override
    public String toString() {
        return "Nome: " + this.nome +" "+ this.pontos + " pontos";
    }
}
