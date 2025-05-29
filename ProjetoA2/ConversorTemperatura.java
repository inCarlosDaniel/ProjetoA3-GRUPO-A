package temperatura;

import java.util.Scanner;           // Para ler entrada do usuário do console
import java.text.DecimalFormat;     // Para formatar números com duas casas decimais

public class ConversorTemperatura {
    // Armazena as últimas 5 conversões realizadas (buffer circular)
    private static Temperatura[] historico = new Temperatura[5];
    // Contador total de conversões feitas até agora
    private static int contador = 0;
    // Formata valores numéricos com até duas casas decimais
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in); // Cria Scanner para ler do teclado

        // Loop infinito até o usuário escolher “Sair”
        while (true) {
            // Exibe menu de opções
            System.out.println("======= CONVERSOR DE TEMPERATURA =======");
            System.out.println("1. Converter temperatura");
            System.out.println("2. Ver histórico de conversões");
            System.out.println("3. Ver estatísticas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt(); // Lê opção escolhida
            scanner.nextLine();            // Limpa o buffer (enter)

            if (opcao == 1) {
                // ------ Fluxo de conversão ------
                // Lê valor e normaliza vírgula para ponto
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", ".");
                double valorEntrada = Double.parseDouble(entradaStr);

                // Lê unidade de origem e converte para maiúscula
                System.out.print("Unidade de origem (C, F, K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();

                // Lê unidade de destino e converte para maiúscula
                System.out.print("Unidade de destino (C, F, K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                // Cria objeto Temperatura correspondente à unidade de origem
                Temperatura entrada;
                if (unidadeOrigem.equals("C")) {
                    entrada = new Celsius(valorEntrada);
                } else if (unidadeOrigem.equals("F")) {
                    entrada = new Fahrenheit(valorEntrada);
                } else if (unidadeOrigem.equals("K")) {
                    entrada = new Kelvin(valorEntrada);
                } else {
                    // Se digitar unidade inválida, usa Celsius como padrão
                    System.out.println("Unidade inválida! Usando Celsius como padrão.");
                    entrada = new Celsius(valorEntrada);
                }

                // Faz o cast para a interface de conversão
                Conversao conversao = (Conversao) entrada;
                Temperatura convertida;

                // Converte para a unidade de destino escolhida
                if (unidadeDestino.equals("C")) {
                    convertida = new Celsius(conversao.converterParaCelsius());
                } else if (unidadeDestino.equals("F")) {
                    convertida = new Fahrenheit(conversao.converterParaFahrenheit());
                } else if (unidadeDestino.equals("K")) {
                    convertida = new Kelvin(conversao.converterParaKelvin());
                } else {
                    // Unidade destino inválida: converte para Celsius e avisa
                    System.out.println("Unidade inválida! Convertendo para Celsius.");
                    convertida = new Celsius(conversao.converterParaCelsius());
                }

                // Exibe resultado formatado
                System.out.println("Resultado da conversão:");
                System.out.println(
                    df.format(entrada.getValor()) + " " + formatarUnidade(unidadeOrigem)
                    + " = " +
                    df.format(convertida.getValor()) + " " + formatarUnidade(unidadeDestino)
                );

                // Armazena no histórico em posição circular
                historico[contador % historico.length] = convertida;
                contador++;

            } else if (opcao == 2) {
                // Exibe o histórico de conversões
                verHistorico();

            } else if (opcao == 3) {
                // Exibe estatísticas (menor/maior/média)
                verEstatisticas();

            } else if (opcao == 4) {
                // Encerra o programa
                System.out.println("Programa encerrado.");
                break;

            } else {
                // Opção inválida no menu
                System.out.println("Opção inválida!");
            }
        }

        scanner.close(); // Fecha Scanner antes de sair
    }

    /**
     * Imprime as últimas conversões armazenadas no histórico.
     */
    private static void verHistorico() {
        System.out.println("----- HISTÓRICO DE CONVERSÕES -----");
        if (contador == 0) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }

        // Total de registros a mostrar (até 5 ou até o número de conversões)
        int total = Math.min(contador, historico.length);

        // Percorre o histórico e exibe valor e unidade
        for (int i = 0; i < total; i++) {
            Temperatura t = historico[i];
            String unidade = t instanceof Celsius    ? "°C"
                          : t instanceof Fahrenheit ? "°F"
                          :                           "K";
            System.out.println((i + 1) + ". " + df.format(t.getValor()) + " " + unidade);
        }
    }

    /**
     * Calcula e exibe estatísticas (menor, maior e média) das temperaturas no histórico.
     */
    private static void verEstatisticas() {
        System.out.println("----- ESTATÍSTICAS -----");
        if (contador == 0) {
            System.out.println("Nenhum dado disponível para estatísticas.");
            return;
        }

        int total = Math.min(contador, historico.length);

        // Inicializa menor, maior e soma com o primeiro valor
        double menor = historico[0].getValor();
        double maior = menor;
        double soma  = menor;

        // Percorre o restante para atualizar estatísticas
        for (int i = 1; i < total; i++) {
            if (historico[i] == null) continue;
            double valor = historico[i].getValor();
            if (valor < menor) menor = valor;
            if (valor > maior) maior = valor;
            soma += valor;
        }

        double media = soma / total;

        // Determina a unidade de exibição a partir da primeira entrada
        String unidade = historico[0] instanceof Celsius    ? "°C"
                       : historico[0] instanceof Fahrenheit ? "°F"
                       :                                      "K";

        // Mostra resultados formatados
        System.out.println("Menor temperatura: " + df.format(menor) + " " + unidade);
        System.out.println("Maior temperatura: " + df.format(maior) + " " + unidade);
        System.out.println("Média das temperaturas: " + df.format(media) + " " + unidade);
    }

    /**
     * Converte a letra da unidade para o símbolo correspondente.
     * @param unidade "C", "F" ou "K"
     * @return "°C", "°F" ou "K"
     */
    private static String formatarUnidade(String unidade) {
        switch (unidade) {
            case "C": return "°C";
            case "F": return "°F";
            case "K": return "K";
            default:  return unidade; // Caso inesperado, retorna a própria string
        }
    }
}
