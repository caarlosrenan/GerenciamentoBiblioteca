package service;

import biblioteca.entities.Autor;
import biblioteca.entities.Emprestimo;
import biblioteca.entities.Livro;
import biblioteca.entities.StatusEmprestimo;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class CadastroLivro {

    private EntityManagerFactory emf;
    private CadastroEmprestimo cadastroEmprestimo;
    private Scanner sc;

    public CadastroLivro(Scanner sc,  CadastroEmprestimo cadastroEmprestimo) {
        this.emf = Persistence.createEntityManagerFactory("bibliotecaPU");
        this.cadastroEmprestimo = cadastroEmprestimo;
        this.sc = sc;
    }

    public void cadastrarLivro(String isbn, String titulo, Autor autor, LocalDate dataPublicacao, String genero, int quantidadeDisponivel) {
        Livro livro = new Livro(titulo, autor, dataPublicacao, isbn, genero, quantidadeDisponivel);
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(livro);
            em.getTransaction().commit();
            System.out.println();
            System.out.println("----------- LIVRO CADASTRADO COM SUCESSO -----------");
            System.out.println();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERRO AO CADASTRAR LIVRO: " + e.getMessage());
        } finally {
            em.close();
        }
    }

    public void cadastrarLivroComAutor() {
        System.out.print("Informe o ISBN do livro: ");
        String isbn = sc.nextLine();
        System.out.print("Informe o título do livro: ");
        String titulo = sc.nextLine();
        System.out.print("Informe a data de publicação (yyyy-mm-dd): ");
        LocalDate dataPublicacao = LocalDate.parse(sc.nextLine());
        System.out.print("Informe o gênero do livro: ");
        String genero = sc.nextLine();
        System.out.print("Informe a quantidade disponível: ");
        int quantidadeDisponivel = Integer.parseInt(sc.nextLine());

        System.out.print("Informe o nome do autor: ");
        String nomeAutor = sc.nextLine();

        CadastroAutor cadastroAutor = new CadastroAutor();
        Autor autor = cadastroAutor.buscarAutorPorNome(nomeAutor);

        if (autor == null) {
            System.out.println("Autor não encontrado. Deseja cadastrar um novo autor? (s/n)");
            String resposta = sc.nextLine();
            if (resposta.equalsIgnoreCase("s")) {

                System.out.print("Informe o nome do novo autor: ");
                String novoNome = sc.nextLine();
                Autor novoAutor = new Autor(novoNome);
                cadastroAutor.cadastrarAutor(novoAutor);
                autor = novoAutor;
            } else {
                System.out.println("Cadastro de livro cancelado.");
                return;
            }
        }


        Livro livro = new Livro(titulo, autor, dataPublicacao, isbn, genero, quantidadeDisponivel);
        cadastrarLivro(isbn, titulo, autor, dataPublicacao, genero, quantidadeDisponivel);
    }

    public void devolverLivro(String isbn, String nomeMembro) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {

            Emprestimo emprestimoParaDevolucao = em.createQuery("SELECT e FROM Emprestimo e WHERE e.livro.isbn = :isbn AND e.membro.nome = :nomeMembro", Emprestimo.class)
                    .setParameter("isbn", isbn)
                    .setParameter("nomeMembro", nomeMembro)
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (emprestimoParaDevolucao != null) {

                emprestimoParaDevolucao.setDataDevolucao(LocalDate.now());

                BigDecimal multa = emprestimoParaDevolucao.calcularMulta();

                emprestimoParaDevolucao.setStatus(StatusEmprestimo.CONCLUÍDO);

                if (multa.compareTo(BigDecimal.ZERO) > 0) {
                    System.out.println("LIVRO DEVOLVIDO COM ATRASO. MULTA APLICADA: R$ " + multa);
                } else {
                    System.out.println("LIVRO DEVOLVIDO COM SUCESSO. NENHUMA MULTA APLICADA.");
                }

                em.getTransaction().commit();
            } else {
                System.out.println("EMPRÉSTIMO NÃO ENCONTRADO PARA O LIVRO E MEMBRO INFORMADOS.");
            }

        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERRO AO DEVOLVER LIVRO: " + e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Livro buscarLivroPorIsbn(String isbn) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.find(Livro.class, isbn);
        } finally {
            em.close();
        }
    }

    public List<Livro> getTodosLivros() {
        EntityManager em = emf.createEntityManager();
        List<Livro> livros;

        try {
            livros = em.createQuery("SELECT l FROM Livro l", Livro.class).getResultList();
        } finally {
            em.close();
        }

        return livros;
    }

    public void fechar() {
        emf.close();
    }

}

