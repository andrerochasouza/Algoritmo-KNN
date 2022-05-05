public class Personagem {

    int forca, inteligencia, velocidade, poder, tenacidade;
    TipoPersonagemEnum tipoPersonagemEnum;

    public Personagem(int forca, int inteligencia, int velocidade, int tenacidade, int poder,
            TipoPersonagemEnum tipoPersonagemEnum) {
        this.forca = forca;
        this.inteligencia = inteligencia;
        this.velocidade = velocidade;
        this.tenacidade = tenacidade;
        this.poder = poder;
        this.tipoPersonagemEnum = tipoPersonagemEnum;
    }

    public int getForca() {
        return forca;
    }

    public int getInteligencia() {
        return inteligencia;
    }

    public void setForca(int forca) {
        this.forca = forca;
    }

    public void setInteligencia(int inteligencia) {
        this.inteligencia = inteligencia;
    }

    public int getPoder() {
        return poder;
    }

    public void setPoder(int poder) {
        this.poder = poder;
    }

    public int getTenacidade() {
        return tenacidade;
    }

    public void setTenacidade(int tenacidade) {
        this.tenacidade = tenacidade;
    }

    public int getVelocidade() {
        return velocidade;
    }

    public void setVelocidade(int velocidade) {
        this.velocidade = velocidade;
    }

    public TipoPersonagemEnum getTipoPersonagemEnum() {
        return tipoPersonagemEnum;
    }

    public void setTipoPersonagemEnum(TipoPersonagemEnum tipoPersonagemEnum) {
        this.tipoPersonagemEnum = tipoPersonagemEnum;
    }

}
