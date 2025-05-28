package temperatura;

public class Fahrenheit extends Temperatura implements Conversao {

    public Fahrenheit(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado (String)
    public Fahrenheit(String valorStr) {
        super(valorStr);
    }

    @Override
    public double converterParaCelsius() {
        return (getValor() - 32) * 5 / 9;
    }

    @Override
    public double converterParaFahrenheit() {
        return getValor();
    }

    @Override
    public double converterParaKelvin() {
        return (getValor() - 32) * 5 / 9 + 273.15;
    }

    @Override
    public double converter() {
        return converterParaCelsius();
    }
}