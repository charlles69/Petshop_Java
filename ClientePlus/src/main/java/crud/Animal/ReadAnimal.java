package crud.Animal;

import java.util.List;
 import java.util.Scanner;
 
 import dao.DAO;
 import model.Animal;

public class ReadAnimal {

	 public static void executar(Scanner scanner) {
	        int opcao;

	        do {
	            System.out.println("\n========== CONSULTA DE ANIMAIS ==========");
	            System.out.println("1 - Buscar Animal pelo nome");
	            System.out.println("2 - Buscar Animal por ID");
	            System.out.println("3 - Listar todos os Animais");
	            System.out.println("0 - Voltar ao menu de Animais ");
	            System.out.print("Escolha uma opção: ");

	            try {
	                opcao = Integer.parseInt(scanner.nextLine());

	                switch (opcao) {
	                    case 1:
	                        buscarPorNome(scanner);
	                        break;
	                    case 2:
	                        buscarPorID(scanner);
	                        break;
	                    case 3:
	                        listarTodosComPaginacao(scanner);
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

	    public static void buscarPorNome(Scanner scanner) {
	        System.out.print("Digite o nome do Animal: ");
	        String nome = scanner.nextLine();

	        DAO<Animal> dao = new DAO<>(Animal.class);
	        // Ajuste na consulta JPA para usar a entidade Cadastro e alias 'c'
	        List<Animal> clientes = dao.consultar(
	            "SELECT c FROM Animal c WHERE LOWER(c.nome) LIKE LOWER(?1)",
	            "%" + nome + "%");

	        if (clientes.isEmpty()) {
	            System.out.println("❌ Nenhum Animal encontrado com esse nome.");
	        } else {
	            System.out.println("\n📋 Animais encontrados:");
	            clientes.forEach(System.out::println);
	        }
	    }

	    public static void buscarPorID(Scanner scanner) {
	        System.out.print("Digite o ID do Animal: ");
	        String cpf = scanner.nextLine();

	        DAO<Animal> dao = new DAO<>(Animal.class);
	        List<Animal> animais = dao.consultar(
	            "SELECT c FROM Animal c WHERE c.id = ?1",
	            cpf);

	        if (animais.isEmpty()) {
	            System.out.println("❌ Nenhum Animal encontrado com esse CPF.");
	        } else {
	            System.out.println("\n📋 Animal encontrado:");
	            animais.forEach(System.out::println);
	        }
	    }

	    public static void listarTodosComPaginacao(Scanner scanner) {
	        System.out.print("Quantos Animais deseja ver por página (ex: 10, 20, 50)? ");

	        int tamanhoPagina = 0;
	        while (tamanhoPagina <= 0) {
	            try {
	                tamanhoPagina = Integer.parseInt(scanner.nextLine());
	                if (tamanhoPagina <= 0) {
	                    System.out.print("⚠ Valor deve ser maior que zero. Digite novamente: ");
	                }
	            } catch (NumberFormatException e) {
	                System.out.print("❌ Número inválido. Digite novamente: ");
	            }
	        }

	        int pagina = 0;
	        int opcaoPag;

	        DAO<Animal> dao = new DAO<>(Animal.class);

	        do {
	            List<Animal> animais = dao.obterTodos(tamanhoPagina, pagina * tamanhoPagina);

	            System.out.println("\n                                                              📄 Página " + (pagina + 1));
	            System.out.println("                          ----------------------------------------------------------------------------------------------------");

	            if (animais.isEmpty()) {
	                System.out.println("Nenhum Animal encontrado nesta página.");
	            } else {
	                // Cabeçalho da tabela
	                System.out.printf("%-8s %-35s %-17s %-33s %-17s %-39s%n", "ID", "Nome", "Sexo", "Raca", "Idade",
	                        "Observações");
	                System.out.println(
	                        "-----------------------------------------------------------------------------------------------------------------------------------------------------------------");

	                // Dados de cada cliente
	                for (Animal a : animais ) {
	                    System.out.printf("%-8d %-35s %-17s %-33s %-17s %-39s%n",
	                            a.getId(), a.getNome(), a.getSexo(), a.getRaca(), a.getIdade(), a.getObservacoes());
	                }
	            }

	            System.out.println();
	            System.out.println("-----------------------------------------------------------------------------------------------------------------------------------------------------------------");
	            System.out.println();
	            System.out.println("1 - Próxima página");
	            System.out.println("2 - Página anterior");
	            System.out.println("0 - Voltar ao menu de Animais");
	            System.out.print("Escolha uma opção: ");

	            try {
	                opcaoPag = Integer.parseInt(scanner.nextLine());

	                switch (opcaoPag) {
	                    case 1:
	                        pagina++;
	                        break;
	                    case 2:
	                        if (pagina > 0) {
	                            pagina--;
	                        } else {
	                            System.out.println("⚠ Já está na primeira página.");
	                        }
	                        break;
	                    case 0:
	                        System.out.println("↩ Saindo da listagem...");
	                        break;
	                    default:
	                        System.out.println("❌ Opção inválida!");
	                }

	            } catch (NumberFormatException e) {
	                System.out.println("❌ Entrada inválida.");
	                opcaoPag = -1;
	            }

	        } while (opcaoPag != 0);
	    }
}
