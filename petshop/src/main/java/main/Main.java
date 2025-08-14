package main;

import dao.DAO;
import java.util.Scanner;
import crudTutor.CreateTutor;

public class Main {
	private final DAO dao;
	private final Scanner scanner;
	
	public Main(Scanner scanner, DAO dao) {
		this.dao = dao;
		this.scanner = scanner;
	}

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		CreateTutor crudTutor = new CreateTutor(scanner, dao);
		
		crudTutor.executar();

	}
}