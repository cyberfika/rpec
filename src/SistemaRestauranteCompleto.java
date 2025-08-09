import java.util.Scanner;

// ==================== CLASSE PRINCIPAL ====================
public class SistemaRestauranteCompleto {
    private Scanner scanner;
    private Cardapio cardapio;

    public SistemaRestauranteCompleto() {
        this.scanner = new Scanner(System.in);
        this.cardapio = new Cardapio();
    }

    public void iniciar() {
        System.out.println("=== BEM-VINDO AO SISTEMA DO RESTAURANTE ===");

        int opcao;
        do {
            exibirMenuPrincipal();
            opcao = lerOpcao();

            switch (opcao) {
                case 1:
                    fazerPedido();
                    break;
                case 2:
                    System.out.println("Obrigado por usar nosso sistema! Até logo!");
                    break;
                default:
                    System.out.println("Opção inválida! Tente novamente.");
            }
        } while (opcao != 2);

        scanner.close();
    }

    private void exibirMenuPrincipal() {
        System.out.println("\n========== MENU PRINCIPAL ==========");
        System.out.println("1 - Fazer Pedido");
        System.out.println("2 - Sair");
        System.out.println("====================================");
        System.out.print("Escolha uma opção: ");
    }

    private int lerOpcao() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1; // Opção inválida
        }
    }

    private void fazerPedido() {
        System.out.print("\nDigite o nome do cliente: ");
        String nomeCliente = scanner.nextLine().trim();

        if (nomeCliente.isEmpty()) {
            System.out.println("Nome do cliente não pode estar vazio!");
            return;
        }

        Pedido pedido = new Pedido(nomeCliente);

        System.out.printf("\nOlá %s! Vamos fazer seu pedido.%n", nomeCliente);

        int codigoItem;
        do {
            cardapio.exibirCardapio();
            System.out.print("Digite o código do item (0 para finalizar): ");

            try {
                codigoItem = Integer.parseInt(scanner.nextLine());

                if (codigoItem == 0) {
                    break;
                } else if (cardapio.itemExiste(codigoItem)) {
                    ItemCardapio item = cardapio.buscarItem(codigoItem);
                    pedido.adicionarItem(item);
                    System.out.printf("✓ %s adicionado ao pedido!%n", item.getNome());
                } else {
                    System.out.println("Código inválido! Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite apenas números.");
                codigoItem = -1; // Continua o loop
            }

        } while (codigoItem != 0);

        if (!pedido.temItens()) {
            System.out.println("Nenhum item foi adicionado ao pedido!");
            return;
        }

        finalizarPedido(pedido);
    }

    private void finalizarPedido(Pedido pedido) {
        // Pergunta sobre taxa de serviço personalizada
        System.out.print("\nDeseja alterar a taxa de serviço? (padrão 10%) [s/N]: ");
        String resposta = scanner.nextLine().trim().toLowerCase();

        if (resposta.equals("s") || resposta.equals("sim")) {
            System.out.print("Digite a nova taxa de serviço (%): ");
            try {
                double novaTaxa = Double.parseDouble(scanner.nextLine());
                if (novaTaxa >= 0 && novaTaxa <= 30) {
                    pedido.setTaxaServico(novaTaxa);
                } else {
                    System.out.println("Taxa inválida! Mantendo taxa padrão de 10%.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Valor inválido! Mantendo taxa padrão de 10%.");
            }
        }

        // Exibe a nota fiscal
        pedido.exibirNotaFiscal();

        // Processo de pagamento
        processarPagamento(pedido);
    }

    private void processarPagamento(Pedido pedido) {
        double total = pedido.calcularTotal();
        double valorPago;

        do {
            System.out.printf("\nValor a pagar: R$ %.2f%n", total);
            System.out.print("Quanto foi recebido em dinheiro? R$ ");

            try {
                valorPago = Double.parseDouble(scanner.nextLine());

                if (valorPago < total) {
                    System.out.printf("Valor insuficiente! Faltam R$ %.2f%n",
                            total - valorPago);
                } else {
                    double troco = pedido.calcularTroco(valorPago);
                    System.out.printf("\n✓ Pagamento recebido: R$ %.2f%n", valorPago);

                    if (troco > 0) {
                        System.out.printf("✓ Troco: R$ %.2f%n", troco);
                    } else {
                        System.out.println("✓ Pagamento exato!");
                    }

                    System.out.printf("\n Pedido finalizado com sucesso!%n");
                    System.out.printf("Obrigado, %s! Seu pedido está sendo preparado.%n",
                            pedido.getNomeCliente());
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Por favor, digite um valor válido.");
                valorPago = 0; // Força a repetição do loop
            }

        } while (valorPago < total);
    }

    public static void main(String[] args) {
        SistemaRestauranteCompleto sistema = new SistemaRestauranteCompleto();
        sistema.iniciar();
    }
}