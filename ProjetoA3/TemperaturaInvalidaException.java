package temperatura;

// Declara uma nova exceção personalizada chamada TemperaturaInvalidaException
public class TemperaturaInvalidaException extends Exception {

    // Construtor da exceção que recebe uma mensagem de erro como parâmetro
    public TemperaturaInvalidaException(String mensagem) {
        // Chama o construtor da classe Exception para armazenar a mensagem
        super(mensagem);
    }
}

