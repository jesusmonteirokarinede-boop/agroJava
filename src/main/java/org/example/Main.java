import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        double[] chuvas = new double[7];
        String[] dias = {
                "Segunda-feira", "Terça-feira", "Quarta-feira",
                "Quinta-feira", "Sexta-feira", "Sábado", "Domingo"
        };
        double[][] umidade = new double[4][4];

        boolean dadosCadastrados = false;
        int opcao;

        do {
            System.out.println("AgroJava - Sistema Integrado de Agronegócio");
            System.out.println();
            System.out.print("Seja bem-vindo(a) ao AgroJava, digite qual opção deseja para prosseguir: ");
            System.out.println(" 1 - Cadastrar dados (Chuvas e Umidade)");
            System.out.println(" 2 - Exibir mapa do campo e relatório de chuvas");
            System.out.println(" 3 - Relatório de alertas de irrigação");
            System.out.println(" 4 - Sair");
            System.out.println("Escolha uma opção");

            opcao = entrada.nextInt();

            switch (opcao) {
                case 1:
                    System.out.println("===Cadastro de Pluviosidade (7 dias)===");
                    for (int i = 0; i < 7; i++) {
                        System.out.print("Volume de Chuva em " + dias[i] + " (mm): ");
                        chuvas[i] = entrada.nextDouble();
                    }
                    System.out.println();
                    System.out.println("===Cadastro de Umidade===");
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            System.out.print("Informe a umidade do talhão [" + (i + 1) + "][" + (j + 1) + "] (%): ");
                            umidade[i][j] = entrada.nextDouble();
                        }
                    }
                    dadosCadastrados = true;
                    System.out.println("Os dados foram cadastrados com sucesso!");
                    System.out.println();
                    break;

                case 2:
                    if (!dadosCadastrados) {
                        System.out.println("Cadastre os dados primeiro selecionando a opção 1.");
                        break;
                    }

                    double somaChuva = 0;
                    double maiorChuva = chuvas[0];
                    int diaMaiorChuva = 0;

                    for (int i = 0; i < 7; i++) {
                        somaChuva += chuvas[i];
                        if (chuvas[i] > maiorChuva) {
                            maiorChuva = chuvas[i];
                            diaMaiorChuva = i;
                        }
                    }
                    double mediaChuva = somaChuva / 7;

                    System.out.println("===Relatório de Pluviosidade===");
                    System.out.printf("Média semanal de precipitação: %.2f mm", mediaChuva);
                    System.out.println("Dia com maior índice de chuva: " + dias[diaMaiorChuva] + " (" + maiorChuva + " mm)");

                    System.out.println("===MAPA DE UMIDADE DO CAMPO===");
                    System.out.println("            Col 1 Col 2 Col 3 Col 4");
                    for (int i = 0; i < 4; i++) {
                        System.out.print("Talhão Linha " + (i + 1) + "");
                        for (int j = 0; j < 4; j++) {
                            System.out.printf(" %.1f", umidade[i][j]);
                        }
                        System.out.println();
                    }
                    break;

                case 3:
                    if (!dadosCadastrados) {
                        System.out.println("Cadastre os dados primeiro selecionando a opção 1.");
                        break;
                    }

                    System.out.println("Relatório de alertas de irrigação");
                    boolean alertaEncontrado = false;

                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (umidade[i][j] < 30.0) {
                                System.out.printf("ALERTA: O talhão [%d][%d] está com %.1f%% de umidade e necessita de irrigação imediata!\n",
                                        (i + 1), (j + 1), umidade[i][j]);
                                alertaEncontrado = true;
                            }
                        }
                    }

                    if (!alertaEncontrado) {
                        System.out.println("Todos os talhões estão com umidade adequada (>= 30%). Nenhuma irrigação necessária no momento.");
                    }
                    break;

                case 4:
                    System.out.println("Sistema encerrado!");
                    break;

                default:
                    System.out.println("Opção inválida! Escolha um número entre 1 e 4.");
            }
        } while (opcao != 4);

    }
}