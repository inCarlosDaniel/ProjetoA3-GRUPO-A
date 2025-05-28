package temperatura;

import java.util.Scanner;
import java.text.DecimalFormat;

public class ConversorTemperatura {
    private static Temperatura[] historico = new Temperatura[5];
    private static int contador = 0;
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("======= CONVERSOR DE TEMPERATURA =======");
            System.out.println("1. Converter temperatura");
            System.out.println("2. Ver histórico de conversões");
            System.out.println("3. Ver estatísticas");
            System.out.println("4. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = scanner.nextInt();
            scanner.nextLine(); 

            if (opcao == 1) {
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", ".");
                double valorEntrada = Double.parseDouble(entradaStr);

                System.out.print("Unidade de origem (C, F, K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();

                System.out.print("Unidade de destino (C, F, K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                Temperatura entrada;

                if (unidadeOrigem.equals("C")) {
                    entrada = new Celsius(valorEntrada);
                } else if (unidadeOrigem.equals("F")) {
                    entrada = new Fahrenheit(valorEntrada);
                } else if (unidadeOrigem.equals("K")) {
                    entrada = new Kelvin(valorEntrada);
                } else {
                    System.out.println("Unidade inválida! Usando Celsius como padrão.");
                    entrada = new Celsius(valorEntrada);
                }

                Conversao conversao = (Conversao) entrada;
                Temperatura convertida;

                if (unidadeDestino.equals("C")) {
                    convertida = new Celsius(conversao.converterParaCelsius());
                } else if (unidadeDestino.equals("F")) {
                    convertida = new Fahrenheit(conversao.converterParaFahrenheit());
                } else if (unidadeDestino.equals("K")) {
                    convertida = new Kelvin(conversao.converterParaKelvin());
                } else {
                    System.out.println("Unidade inválida! Convertendo para Celsius.");
                    convertida = new Celsius(conversao.converterParaCelsius());
                }

                System.out.println("Resultado da conversão:");
                System.out.println(df.format(entrada.getValor()) + " " + formatarUnidade(unidadeOrigem) +
                        " = " + df.format(convertida.getValor()) + " " + formatarUnidade(unidadeDestino));

                // Histórico 
                historico[contador % historico.length] = convertida;
                contador++;

            } else if (opcao == 2) {
                verHistorico();
            } else if (opcao == 3) {
                verEstatisticas();
            } else if (opcao == 4) {
                System.out.println("Programa encerrado.");
                break;
            } else {
                System.out.println("Opção inválida!");
            }
        }
        scanner.close();
    }

    private static void verHistorico() {
        System.out.println("----- HISTÓRICO DE CONVERSÕES -----");
        if (contador == 0) {
            System.out.println("Nenhuma conversão realizada ainda.");
            return;
        }

        int total = Math.min(contador, historico.length);

        for (int i = 0; i < total; i++) {
            Temperatura t = historico[i];
            String unidade = t instanceof Celsius ? "°C" :
                             t instanceof Fahrenheit ? "°F" :
                             "K";
            System.out.println((i + 1) + ". " + df.format(t.getValor()) + " " + unidade);
        }
    }

    private static void verEstatisticas() {
    System.out.println("----- ESTATÍSTICAS -----");
    if (contador == 0) {
        System.out.println("Nenhum dado disponível para estatísticas.");
        return;
    }

    int total = Math.min(contador, historico.length);

    // Mostra os valores originais do histórico
    double menor = historico[0].getValor();
    double maior = menor;
    double soma = menor;

    for (int i = 1; i < total; i++) {
        if (historico[i] == null) continue;
        double valor = historico[i].getValor();
        if (valor < menor) menor = valor;
        if (valor > maior) maior = valor;
        soma += valor;
    }

    double media = soma / total;

    // Mostra as unidades originais
    String unidade = historico[0] instanceof Celsius ? "°C" :
                    historico[0] instanceof Fahrenheit ? "°F" : "K";

    System.out.println("Menor temperatura: " + df.format(menor) + " " + unidade);
    System.out.println("Maior temperatura: " + df.format(maior) + " " + unidade);
    System.out.println("Média das temperaturas: " + df.format(media) + " " + unidade);
}
    private static String formatarUnidade(String unidade) {
        switch (unidade) {
            case "C": return "°C";
            case "F": return "°F";
            case "K": return "K";
            default: return unidade;
        }
    }
}