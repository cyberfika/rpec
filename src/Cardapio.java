import java.util.ArrayList;
import java.util.List;

class Cardapio {
    private List<ItemCardapio> itens;

    public Cardapio() {
        this.itens = new ArrayList<>();
        inicializarCardapio();
    }

    private void inicializarCardapio() {
        // Pratos principais
        itens.add(new ItemCardapio(1, "Hambúrguer Clássico", 25.90));
        itens.add(new ItemCardapio(2, "Hambúrguer com Bacon", 28.90));
        itens.add(new ItemCardapio(3, "Pizza Margherita", 35.00));
        itens.add(new ItemCardapio(4, "Pizza Pepperoni", 38.00));
        itens.add(new ItemCardapio(5, "Lasanha Bolonhesa", 32.50));
        itens.add(new ItemCardapio(6, "Salmão Grelhado", 45.00));
        itens.add(new ItemCardapio(7, "Frango à Parmegiana", 28.00));
        itens.add(new ItemCardapio(8, "Risotto de Camarão", 42.00));

        // Bebidas
        itens.add(new ItemCardapio(9, "Refrigerante Lata", 5.50));
        itens.add(new ItemCardapio(10, "Suco Natural", 8.00));
        itens.add(new ItemCardapio(11, "Água Mineral", 3.50));
        itens.add(new ItemCardapio(12, "Cerveja Long Neck", 7.50));
        itens.add(new ItemCardapio(13, "Vinho Taça", 15.00));

        // Sobremesas
        itens.add(new ItemCardapio(14, "Pudim", 12.00));
        itens.add(new ItemCardapio(15, "Torta de Chocolate", 14.50));
        itens.add(new ItemCardapio(16, "Sorvete (3 bolas)", 10.00));
    }

    public void exibirCardapio() {
        System.out.println("\n========== CARDÁPIO ==========");
        System.out.println("PRATOS PRINCIPAIS:");
        for (int i = 0; i < 8; i++) {
            System.out.println(itens.get(i));
        }
        System.out.println("\nBEBIDAS:");
        for (int i = 8; i < 13; i++) {
            System.out.println(itens.get(i));
        }
        System.out.println("\nSOBREMESAS:");
        for (int i = 13; i < itens.size(); i++) {
            System.out.println(itens.get(i));
        }
        System.out.println("\n0 - Finalizar Pedido");
        System.out.println("=============================");
    }

    public ItemCardapio buscarItem(int codigo) {
        for (ItemCardapio item : itens) {
            if (item.getCodigo() == codigo) {
                return item;
            }
        }
        return null;
    }

    public boolean itemExiste(int codigo) {
        return buscarItem(codigo) != null;
    }
}