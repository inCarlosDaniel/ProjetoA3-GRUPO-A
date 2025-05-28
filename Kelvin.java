package temperatura;

public class Kelvin extends Temperatura implements Conversao {

    public Kelvin(double valor) {
        super(valor);
    }

    // Construtor sobrecarregado (String)
    public Kelvin(String valorStr) {
        super(valorStr);
    }

    @Override
    public double converterParaCelsius() {
        return getValor() - 273.15;
    }

    @Override
    public double converterParaFahrenheit() {
        return (getValor() - 273.15) * 9 / 5 + 32;
    }

    @Override
    public double converterParaKelvin() {
        return getValor();
    }

    @Override
    public double converter() {
        return converterParaCelsius();
    }
}