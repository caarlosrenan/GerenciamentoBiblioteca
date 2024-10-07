package org.example;

import biblioteca.entities.Autor;
import biblioteca.entities.Membro;
import service.CadastroLivro;
import service.CadastroAutor;
import service.CadastroMembro;
import service.CadastroEmprestimo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        CadastroEmprestimo cadastroEmprestimo = new CadastroEmprestimo();
        CadastroLivro cadastroLivro = new CadastroLivro(sc, cadastroEmprestimo);
        CadastroAutor cadastroAutor = new CadastroAutor();
        CadastroMembro cadastroMembro = new CadastroMembro();

        System.out.println();
        System.out.println("-------------------Sistema de Gerenciamento de Biblioteca-------------------");

        boolean continuar = true;


        while (continuar) {
            System.out.println();
            System.out.println("Menu de Opções:");
            System.out.println("1. Cadastrar Livro");
            System.out.println("2. Cadastrar Autor");
            System.out.println("3. Cadastrar Membro");
            System.out.println("4. Realizar Empréstimo");
            System.out.println("5. Devolver Livro");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            int opcao = sc.nextInt();
            sc.nextLine();

            switch (opcao) {
                case 1:
                    System.out.println();
                    cadastroLivro.cadastrarLivroComAutor();
                    System.out.println();
                    break;

                case 2:
                    System.out.println();
                    System.out.print("Informe o nome do autor: ");
                    String nomeAutor = sc.nextLine();
                    System.out.print("Informe a nacionalidade do autor: ");
                    String nacionalidadeAutor = sc.nextLine();
                    System.out.print("Informe a data de nascimento do autor (yyyy-MM-dd): ");
                    LocalDate dataNascimentoAutor = LocalDate.parse(sc.nextLine());
                    System.out.print("Informe a biografia do autor: ");
                    String biografiaAutor = sc.nextLine();
                    System.out.println();
                    Autor autorObj = new Autor(nomeAutor, dataNascimentoAutor, nacionalidadeAutor, biografiaAutor);
                    cadastroAutor.cadastrarAutor(autorObj);
                    break;

                case 3:
                    System.out.println();
                    System.out.print("Informe o nome do membro: ");
                    String nomeMembro = sc.nextLine();
                    System.out.print("Informe a data de nascimento (YYYY-MM-DD): ");
                    LocalDate dataNascimento = LocalDate.parse(sc.nextLine());
                    System.out.print("Informe a nacionalidade: ");
                    String nacionalidade = sc.nextLine();
                    System.out.print("Informe o endereço do membro: ");
                    String endereco = sc.nextLine();
                    System.out.print("Informe o telefone do membro: ");
                    String telefone = sc.nextLine();
                    System.out.print("Informe o e-mail do membro: ");
                    String email = sc.nextLine();
                    System.out.println();
                    Membro novoMembro = new Membro(nomeMembro, dataNascimento, nacionalidade, endereco, telefone, email, LocalDate.now(), new ArrayList<>());
                    cadastroMembro.cadastrarMembro(novoMembro);
                    break;
                case 4:
                    System.out.println();
                    System.out.print("Informe o ISBN do livro: ");
                    String isbnEmprestimo = sc.nextLine();
                    System.out.print("Informe o nome do membro: ");
                    String nomeMembroEmprestimo = sc.nextLine();
                    System.out.println();
                    cadastroEmprestimo.cadastrarEmprestimo(isbnEmprestimo, nomeMembroEmprestimo);
                    break;
                case 5:
                    System.out.println();
                    System.out.print("Informe o ISBN do livro: ");
                    String isbnDevolucao = sc.nextLine();
                    System.out.print("Informe o nome do membro: ");
                    String nomeMembroDevolucao = sc.nextLine();
                    System.out.println();
                    cadastroLivro.devolverLivro(isbnDevolucao, nomeMembroDevolucao);
                    break;

                case 0:
                    continuar = false;
                    System.out.println();
                    System.out.println("Saindo do sistema...");
                    System.out.println();
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
                    break;
            }
        }


        cadastroLivro.fechar();
        cadastroAutor.fechar();
        cadastroMembro.fechar();
        cadastroEmprestimo.fechar();
        sc.close();

    }

}
