package crud.Animal;

import java.util.Scanner;

import crud.Animal.ReadAnimal;
import dao.DAO;
import model.Animal;

public class DeleteAnimal {
	
	public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU DE EXCLUSÃO DE ANIMAIS ==========");
            System.out.println("1 - Buscar Animais por nome");
            System.out.println("2 - Buscar Animais por ID");
            System.out.println("3 - Listar todos os Animais");
            System.out.println("4 - Excluir Animais por ID");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = Integer.parseInt(scanner.nextLine());

                switch (opcao) {
                    case 1:
                        ReadAnimal.buscarPorNome(scanner);
                        break;
                    case 2:
                        ReadAnimal.buscarPorID(scanner);
                        break;
                    case 3:
                        ReadAnimal.listarTodosComPaginacao(scanner);
                        break;
                    case 4:
                        excluirAnimal(scanner);
                        break;
                    case 0:
                        System.out.println("↩ Retornando ao menu anterior...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite apenas números.");
                opcao = -1;
            }

        } while (opcao != 0);
    }

    // Método que exclui o cliente por ID após confirmação
    private static void excluirAnimal(Scanner scanner) {
        DAO<Animal> dao = new DAO<>(Animal.class);
        Animal animais= null;
        Long id = null;

        // Loop para garantir que o ID exista ou o usuário queira sair
        while (animais == null) {
            System.out.print("Digite o ID do Animal que deseja excluir (ou 0 para voltar): ");
            try {
                id = Long.parseLong(scanner.nextLine());
                if (id == 0) {
                    System.out.println("↩ Voltando ao menu anterior...");
                    return;
                }
                animais = dao.obterPorID(id);
                if ( animais== null) {
                    System.out.println("❌ Animal com ID " + id + " não encontrado. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Digite um número válido.");
            }
        }

        System.out.println("\nAnimal encontrado:");
        System.out.println(animais);

        System.out.print("Tem certeza que deseja excluir este Animal? (S/N): ");
        String confirmar = scanner.nextLine().trim().toUpperCase();

        if (confirmar.equals("S")) {
            dao.removerPorIdTransacional(animais.getId());
            System.out.println("✅ Animal excluído com sucesso!");
        } else {
            System.out.println("Exclusão cancelada.");
        }

        dao.fecharEm();
    }

}
