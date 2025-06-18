package temperatura;

import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

public class ConversorTemperatura {
    private static ArrayList<String> historico = new ArrayList<>();
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("=== CONVERSOR DE TEMPERATURA 3.0 ===");
            System.out.println("1. Converter temperatura");
            System.out.println("2. Ver histórico");
            System.out.println("3. Remover conversão");
            System.out.println("4. Limpar histórico");
            System.out.println("5. Ver estatísticas");
            System.out.println("6. Sair");
            System.out.print("Escolha uma opção: ");

            int opcao = -1;
            try {
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Digite um número de 1 a 6.");
                continue;
            }

            if (opcao == 1) {
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", ".");
                double valorEntrada;

                try {
                    valorEntrada = Double.parseDouble(entradaStr);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Valor inválido!");
                    continue;
                }

                System.out.print("Unidade atual (C/F/K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();
                System.out.print("Converter para (C/F/K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                try {
                    Temperatura entrada;

                    if (unidadeOrigem.equals("C")) {
                        if (valorEntrada < -273.15) throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Celsius(valorEntrada);
                    } else if (unidadeOrigem.equals("F")) {
                        if (valorEntrada < -459.67) throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Fahrenheit(valorEntrada);
                    } else if (unidadeOrigem.equals("K")) {
                        if (valorEntrada < 0) throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Kelvin(valorEntrada);
                    } else {
                        System.out.println("Unidade de origem inválida.");
                        continue;
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
                        System.out.println("Unidade de destino inválida.");
                        continue;
                    }

                    System.out.println("Resultado: " +
                        df.format(convertida.getValor()) + formatarUnidade(unidadeDestino));

                    historico.add(
                        df.format(entrada.getValor()) + formatarUnidade(unidadeOrigem)
                        + " -> " +
                        df.format(convertida.getValor()) + formatarUnidade(unidadeDestino)
                    );

                } catch (TemperaturaInvalidaException e) {
                    System.out.println("Erro: " + e.getMessage());
                }

            } else if (opcao == 2) {
                System.out.println("[HISTÓRICO]");
                if (historico.isEmpty()) {
                    System.out.println("Nenhuma conversão realizada.");
                } else {
                    for (int i = 0; i < historico.size(); i++) {
                        System.out.println((i + 1) + ". " + historico.get(i));
                    }
                }

            } else if (opcao == 3) {
                System.out.print("Digite o índice da conversão para remover: ");
                try {
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    historico.remove(idx);
                    System.out.println("Conversão removida com sucesso!");
                } catch (Exception e) {
                    System.out.println("Índice inválido.");
                }

            } else if (opcao == 4) {
                historico.clear();
                System.out.println("Histórico limpo!");

            } else if (opcao == 5) {
                System.out.println("[ESTATÍSTICAS]");
                if (historico.isEmpty()) {
                    System.out.println("Nenhum dado disponível.");
                } else {
                    double soma = 0;
                    double menor = Double.MAX_VALUE;
                    double maior = Double.MIN_VALUE;
                    String unidade = "";

                    for (String linha : historico) {
                        String[] partes = linha.split(" -> ");
                        double valor = Double.parseDouble(partes[0].split("°|K")[0].replace(",", "."));
                        unidade = partes[0].contains("K") ? "K" : partes[0].contains("F") ? "°F" : "°C";

                        if (valor < menor) menor = valor;
                        if (valor > maior) maior = valor;
                        soma += valor;
                    }

                    double media = soma / historico.size();
                    System.out.println("Total de conversões: " + historico.size());
                    System.out.println("Menor valor: " + df.format(menor) + unidade);
                    System.out.println("Maior valor: " + df.format(maior) + unidade);
                    System.out.println("Média: " + df.format(media) + unidade);
                }

            } else if (opcao == 6) {
                System.out.println("Programa finalizado!");
                break;

            } else {
                System.out.println("Opção inválida!");
            }
        }

        scanner.close();
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
