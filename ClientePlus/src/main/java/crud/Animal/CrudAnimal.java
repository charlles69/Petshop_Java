package crud.Animal;

import java.util.Scanner;

public class CrudAnimal {
	 public static void executar(Scanner scanner) {
	        int opcao;

	        do {
	            System.out.println("\n========== MENU - ANIMAIS ==========");
	            System.out.println("1 - Cadastrar novos animais");
	            System.out.println("2 - Listar todos os animais");
	            System.out.println("3 - Atualizar dados de um animal");
	            System.out.println("4 - Remover animais");
	            System.out.println("0 - Voltar ao menu principal");
	            System.out.print("Escolha uma opção: ");

	            try {
	                opcao = Integer.parseInt(scanner.nextLine());

	                switch (opcao) {
	                    case 1:
	                        CreateAnimal.executar(scanner);
	                        break;
	                    case 2:
	                        ReadAnimal.executar(scanner);
	                        break;
	                    case 3:
	                        UpdateAnimal.executar(scanner);
	                        break;
	                    case 4:
	                        DeleteAnimal.executar(scanner);
	                        break;
	                    case 0:
	                        System.out.println("↩ Retornando ao menu principal...");
	                        break;
	                    default:
	                        System.out.println("❌ Opção inválida.");
	                }

	            } catch (NumberFormatException e) {
	                System.out.println("❌ Entrada inválida.");
	                opcao = -1;
	            }

	        } while (opcao != 0);
	    }

}
