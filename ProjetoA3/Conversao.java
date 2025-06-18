package temperatura;

// Interface que define métodos para conversão entre escalas de temperatura
public interface Conversao {

    // Método para converter o valor para a escala Celsius
    double converterParaCelsius();

    // Método para converter o valor para a escala Fahrenheit
    double converterParaFahrenheit();

    // Método para converter o valor para a escala Kelvin
    double converterParaKelvin();
}
