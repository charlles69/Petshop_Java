package crud.Animal;

import java.util.Scanner;
import dao.DAO;
import model.Animal;
 
public class CreateAnimal {
	
	public static void executar(Scanner scanner) {
		int escolha;

        do {
            System.out.println("\n========== CADASTRO DE ANIMAIS ==========");
            System.out.println("1 - Iniciar cadastro de Animais");
            System.out.println("0 - Voltar ao menu anterior");
            System.out.print("Escolha uma opção: ");

            try {
                escolha = Integer.parseInt(scanner.nextLine());

                switch (escolha) {
                    case 1:
                        cadastrarAnimais(scanner); // Método separado para organização
                        break;
                    case 0:
                        System.out.println("↩ Retornando ao menu de clientes...");
                        break;
                    default:
                        System.out.println("❌ Opção inválida. Tente novamente.");
                }

            } catch (NumberFormatException e) {
                System.out.println("❌ Entrada inválida. Digite um número.");
                escolha = -1;
            }

        } while (escolha != 0);
    }

    // Método que realiza o cadastro de clientes no banco de dados
    private static void cadastrarAnimais(Scanner scanner) {
        DAO<Animal> dao = new DAO<>(Animal.class);
        boolean continuar = true;

        while (continuar) {
            System.out.println("\n📝 Cadastro do novo Animal");

            // Captura os dados básicos
            System.out.print("Nome: ");
            String nome = scanner.nextLine();

            System.out.print("CPF: ");
            String sexo = scanner.nextLine();

            System.out.print("RG: ");
            String raca = scanner.nextLine();

            System.out.print("Data de Nascimento (dd/mm/aaaa): ");
            String idade = scanner.nextLine();

            System.out.print("Gênero (Masculino/Feminino/Outro): ");
            String observacoes = scanner.nextLine();
            
            
            // Cria o objeto Cadastro com os novos campos
            Animal novoAnimal = new Animal(
                nome, sexo, raca, idade, observacoes
            );

            dao.incluirTransacional(novoAnimal);
            System.out.println("✅ Animal cadastrado com sucesso!");

            System.out.print("\nDeseja cadastrar outro animal? (s/n): ");
            String resposta = scanner.nextLine().toLowerCase();

            if (!resposta.equals("s")) {
                continuar = false;
                System.out.println("↩ Encerrando cadastro de clientes...");
            }
        }

        dao.fecharEm(); // Fecha o EntityManager
    }
    
	}
