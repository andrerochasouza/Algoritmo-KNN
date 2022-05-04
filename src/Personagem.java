public class Personagem {
    
    int forca, inteligencia;
    TipoPersonagemEnum tipoPersonagemEnum;

    public Personagem(int forca, int inteligencia, TipoPersonagemEnum tipoPersonagemEnum) {
        this.forca = forca;
        this.inteligencia = inteligencia;
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

    public TipoPersonagemEnum getTipoPersonagemEnum() {
        return tipoPersonagemEnum;
    }
    public void setTipoPersonagemEnum(TipoPersonagemEnum tipoPersonagemEnum) {
        this.tipoPersonagemEnum = tipoPersonagemEnum;
    }

}
