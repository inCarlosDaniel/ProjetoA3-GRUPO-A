package temperatura;

public abstract class Temperatura {
    private double valor;

    // Construtor padrão 
    public Temperatura(double valor) {
        this.valor = valor;
    }

    // Construtor sobrecarregado 
    public Temperatura(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    // Getter
    public double getValor() {
        return valor;
    }

    // Setter 
    public void setValor(double valor) {
        this.valor = valor;
    }

    // Setter sobrecarregado 
    public void setValor(String valorStr) {
        this.valor = Double.parseDouble(valorStr.replace(",", "."));
    }

    // Método abstrato 
    public abstract double converter();
}