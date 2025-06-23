// Pacote principal do projeto
package temperatura;

// Importa bibliotecas necessárias para entrada de dados, listas e formatação numérica
import java.util.Scanner;
import java.util.ArrayList;
import java.text.DecimalFormat;

// Classe principal do sistema de conversão de temperatura
public class ConversorTemperatura {

    // Lista que armazena o histórico das conversões realizadas
    private static ArrayList<String> historico = new ArrayList<>();

    // Formata os valores numéricos com no máximo duas casas decimais
    private static DecimalFormat df = new DecimalFormat("#.##");

    public static void main(String[] args) {
        // Cria objeto para leitura de dados pelo teclado
        Scanner scanner = new Scanner(System.in);

        // Laço principal que exibe o menu até o usuário escolher sair
        while (true) {
            // Menu principal
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
                // Tenta ler e converter a opção escolhida
                opcao = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Caso não seja um número válido
                System.out.println("Entrada inválida. Digite um número de 1 a 6.");
                continue;
            }

            if (opcao == 1) {
                // Início do processo de conversão
                System.out.print("Digite a temperatura: ");
                String entradaStr = scanner.nextLine().replace(",", ".");
                double valorEntrada;

                try {
                    valorEntrada = Double.parseDouble(entradaStr);
                } catch (NumberFormatException e) {
                    System.out.println("Erro: Valor inválido!");
                    continue;
                }

                // Solicita as unidades de origem e destino
                System.out.print("Unidade atual (C/F/K): ");
                String unidadeOrigem = scanner.nextLine().toUpperCase();
                System.out.print("Converter para (C/F/K): ");
                String unidadeDestino = scanner.nextLine().toUpperCase();

                try {
                    // Declara objeto que representará a temperatura de entrada
                    Temperatura entrada;

                    // Instancia o tipo de temperatura correto com validação
                    if (unidadeOrigem.equals("C")) {
                        if (valorEntrada < -273.15)
                            throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Celsius(valorEntrada);
                    } else if (unidadeOrigem.equals("F")) {
                        if (valorEntrada < -459.67)
                            throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Fahrenheit(valorEntrada);
                    } else if (unidadeOrigem.equals("K")) {
                        if (valorEntrada < 0)
                            throw new TemperaturaInvalidaException("Valor abaixo do zero absoluto.");
                        entrada = new Kelvin(valorEntrada);
                    } else {
                        System.out.println("Unidade de origem inválida.");
                        continue;
                    }

                    // Realiza a conversão para a unidade de destino
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

                    // Mostra o resultado da conversão
                    System.out.println("Resultado: " +
                        df.format(convertida.getValor()) + formatarUnidade(unidadeDestino));

                    // Adiciona ao histórico
                    historico.add(
                        df.format(entrada.getValor()) + formatarUnidade(unidadeOrigem)
                        + " -> " +
                        df.format(convertida.getValor()) + formatarUnidade(unidadeDestino)
                    );

                } catch (TemperaturaInvalidaException e) {
                    System.out.println("Erro: " + e.getMessage());
                }

            } else if (opcao == 2) {
                // Exibe o histórico de conversões
                System.out.println("[HISTÓRICO]");
                if (historico.isEmpty()) {
                    System.out.println("Nenhuma conversão realizada.");
                } else {
                    for (int i = 0; i < historico.size(); i++) {
                        System.out.println((i + 1) + ". " + historico.get(i));
                    }
                }

            } else if (opcao == 3) {
                // Remove uma conversão específica pelo índice informado
                System.out.print("Digite o índice da conversão para remover: ");
                try {
                    int idx = Integer.parseInt(scanner.nextLine()) - 1;
                    historico.remove(idx);
                    System.out.println("Conversão removida com sucesso!");
                } catch (Exception e) {
                    System.out.println("Índice inválido.");
                }

            } else if (opcao == 4) {
                // Limpa todo o histórico
                historico.clear();
                System.out.println("Histórico limpo!");

            } else if (opcao == 5) {
                // Exibe estatísticas das conversões
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
                        double valor = Double.parseDouble(partes[0].split("\u00b0|K")[0].replace(",", "."));
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
                // Encerra o programa
                System.out.println("Programa finalizado!");
                break;

            } else {
                // Opção inválida no menu
                System.out.println("Opção inválida!");
            }
        }

        // Fecha o scanner para evitar vazamento de recurso
        scanner.close();
    }

    // Método auxiliar que retorna a unidade formatada
    private static String formatarUnidade(String unidade) {
        switch (unidade) {
            case "C": return "°C";
            case "F": return "°F";
            case "K": return "K";
            default: return unidade;
        }
    }
}

