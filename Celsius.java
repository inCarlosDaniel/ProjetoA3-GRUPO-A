package temperatura;

public class Celsius extends Temperatura implements Conversao {

    public Celsius(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado (String)
    public Celsius(String valorStr) {
        super(valorStr);
    }

    @Override
    public double converterParaCelsius() {
        return getValor();
    }

    @Override
    public double converterParaFahrenheit() {
        return (getValor() * 9 / 5) + 32;
    }

    @Override
    public double converterParaKelvin() {
        return getValor() + 273.15;
    }

    @Override
    public double converter() {
        return converterParaCelsius();
    }
}