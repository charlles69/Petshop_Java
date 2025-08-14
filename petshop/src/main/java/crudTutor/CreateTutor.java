package crudTutor;

import dao.DAO;
import model.Tutor;
import java.util.Scanner;

public class CreateTutor {
	private final Scanner scanner;
	private final DAO dao;
	
	public CreateTutor(Scanner scanner, DAO dao) {
		this.dao = dao;
		this.scanner = scanner;
	}
	

	public Object executar() {
		int escolha;
		
		do {
			System.out.println("\n ==== CADASTRO DE CLIENTES ====");
			System.out.println("1. Cadastrar cliente");
			System.out.println("0. voltar ao menu anterior");
		
			try {
				escolha = Integer.parseInt(scanner.nextLine());
				
				switch(escolha) {
				case 1:
					dao.incluirTransacional(scanner);
					
					break;
					
				case 0:
					System.out.println("retornando...");
					
					break;
					
					default:
						System.out.println("fechando sistema");
				}
			} catch (NumberFormatException e) {
				System.out.println("Entrada invalida.");
				escolha = -1;
			}
		}
		while(escolha != 0);
		return null;
	}
	
}
