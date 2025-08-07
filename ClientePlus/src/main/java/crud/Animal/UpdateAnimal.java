package crud.Animal;

import java.util.Scanner;

import dao.DAO;
import model.Animal;

public class UpdateAnimal {

	public static void executar(Scanner scanner) {
        int opcao;

        do {
            System.out.println("\n========== MENU DE ATUALIZAÇÃO DE ANIMAIS ==========");
            System.out.println("1 - Buscar Animal por nome");
            System.out.println("2 - Buscar Animal por ID");
            System.out.println("3 - Listar todos os animais");
            System.out.println("4 - Atualizar Animal por ID");
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
                        atualizarAnimais(scanner);
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

    private static void atualizarAnimais(Scanner scanner) {
        DAO<Animal> dao = new DAO<>(Animal.class);
        Animal animais = null;
        Long id = null;

        // Loop para garantir que o ID exista ou o usuário queira sair
        while (animais == null) {
            System.out.print("Digite o ID do Animal que deseja atualizar (ou 0 para voltar): ");
            try {
                id = Long.parseLong(scanner.nextLine());
                if (id == 0) {
                    System.out.println("↩ Voltando ao menu anterior...");
                    return;
                }
                animais = dao.obterPorID(id);
                if (animais == null) {
                    System.out.println("❌ Animal com ID " + id + " não encontrado. Tente novamente.");
                }
            } catch (NumberFormatException e) {
                System.out.println("❌ ID inválido. Digite um número válido.");
            }
        }

        System.out.println("\nAnimal encontrado:");
        System.out.println(animais);

        System.out.println("\nQuais campos deseja atualizar?");
        System.out.println("Digite os números separados por vírgula, ou '0' para atualizar todos:");
        System.out.println("1 - Nome");
        System.out.println("2 - idade");
        System.out.println("3 - Observações");
        System.out.print("Opções: ");
        String opcao = scanner.nextLine();

        boolean atualizarTodos = opcao.trim().equals("0");

        if (atualizarTodos || opcao.contains("1")) {
            System.out.print("Novo nome: ");
            String novoNome = scanner.nextLine();
            animais.setNome(novoNome);
        }

        if (atualizarTodos || opcao.contains("2")) {
            System.out.print("Nova idade: ");
            String novaidade = scanner.nextLine();
            animais.setIdade(novaidade);
        }

        if (atualizarTodos || opcao.contains("3")) {
            System.out.print("Novas observações: ");
            String novaOb = scanner.nextLine();
            animais.setObservacoes(novaOb);
        }

        dao.atualizarTransacional(animais);
        System.out.println("\n✅ Animal atualizado com sucesso!");
        System.out.println("Novo estado do Animal:");
        System.out.println(animais);

        dao.fecharEm();
    }
}
