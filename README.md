# Projeto A3 – Grupo A (Turma 2G)

## Membros:
- Carlos Daniel de Souza Oliveira  
- Millena Aragão Tavares  
- Priscilla dos Santos Silva  
- Paulo  
- Rachel Moreira Damasceno Coelho  

## Descrição:
Aplicação console de conversor de temperatura desenvolvida em Java, com uso completo de Programação Orientada a Objetos (POO) e funcionalidades avançadas como `ArrayList`, tratamento de exceções e exceções personalizadas.

## Tecnologias:
- **Java**
- **POO (Programação Orientada a Objetos):**
  - Classes e Objetos  
  - Atributos e Métodos  
  - Construtores  
  - Herança e Polimorfismo  
  - Encapsulamento  
  - Interface  
  - Classe Abstrata  
  - Sobrecarga de Métodos  
- **Coleções:**
  - `ArrayList<Temperatura>` para histórico de conversões dinâmico
- **Exceções:**
  - Tratamento com `try-catch`
  - Exceção personalizada `TemperaturaInvalidaException`
- **IDE:** NetBeans

## Funcionalidades:
- Conversão entre escalas: Celsius, Fahrenheit e Kelvin
- Armazenamento dinâmico do histórico completo de conversões usando `ArrayList`
- Exibição do histórico com o formato completo:  
  `25.00°C -> 77.00°F`
- Remoção de conversões específicas do histórico por índice
- Limpeza total do histórico
- Cálculo de estatísticas com base no histórico:
  - Total de conversões realizadas  
  - Menor temperatura inserida  
  - Maior temperatura inserida  
  - Média das temperaturas  
- Validação de dados de entrada:
  - Conversão segura de strings para números  
  - Verificação de temperatura mínima com base no zero absoluto
- Lançamento de exceções personalizadas (`TemperaturaInvalidaException`) quando o valor está abaixo do permitido:
  - < -273.15°C  
  - < -459.67°F  
  - < 0K
