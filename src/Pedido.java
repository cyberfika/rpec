import java.util.ArrayList;
import java.util.List;

// ==================== CLASSE PEDIDO ====================
class Pedido {
    private String nomeCliente;
    private List<ItemPedido> itens;
    private double taxaServico;

    public Pedido(String nomeCliente) {
        this.nomeCliente = nomeCliente;
        this.itens = new ArrayList<>();
        this.taxaServico = 0.10; // 10% por padrão
    }

    public void adicionarItem(ItemCardapio item) {
        // Verifica se o item já existe no pedido
        for (ItemPedido itemPedido : itens) {
            if (itemPedido.getItem().getCodigo() == item.getCodigo()) {
                itemPedido.adicionarQuantidade(1);
                return;
            }
        }
        // Se não existe, adiciona novo item
        itens.add(new ItemPedido(item, 1));
    }

    public double calcularSubtotal() {
        double subtotal = 0;
        for (ItemPedido item : itens) {
            subtotal += item.getSubtotal();
        }
        return subtotal;
    }

    public double calcularTaxaServico() {
        return calcularSubtotal() * taxaServico;
    }

    public double calcularTotal() {
        return calcularSubtotal() + calcularTaxaServico();
    }

    public void exibirNotaFiscal() {
        System.out.println("\n========== NOTA FISCAL ==========");
        System.out.println("Cliente: " + nomeCliente);
        System.out.println("\nITENS DO PEDIDO:");

        for (ItemPedido item : itens) {
            System.out.println(item);
        }

        System.out.printf("\nSubtotal: R$ %.2f%n", calcularSubtotal());
        System.out.printf("Taxa de Serviço (%.0f%%): R$ %.2f%n",
                taxaServico * 100, calcularTaxaServico());
        System.out.printf("TOTAL: R$ %.2f%n", calcularTotal());
        System.out.println("=================================");
    }

    public double calcularTroco(double valorPago) {
        return valorPago - calcularTotal();
    }

    public boolean temItens() {
        return !itens.isEmpty();
    }

    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setTaxaServico(double taxa) {
        this.taxaServico = taxa / 100.0; // Converte percentual para decimal
    }
}