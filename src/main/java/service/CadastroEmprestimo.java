package service;

import biblioteca.entities.Emprestimo;
import biblioteca.entities.Livro;
import biblioteca.entities.Membro;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class CadastroEmprestimo {

    private Set<Emprestimo> emprestimos;
    private EntityManagerFactory entityManagerFactory;

    public CadastroEmprestimo() {
        emprestimos = new HashSet<>();
        entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecaPU");
    }

    public void cadastrarEmprestimo(Emprestimo emprestimo) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();
        try {
            em.persist(emprestimo);
            em.getTransaction().commit();
            System.out.println("---------------- EMPRÉSTIMO REALIZADO ----------------");
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERRO AO CADASTRAR EMPRÉSTIMO" + e.getMessage());
        } finally {
            em.close();
        }
    }

    public Set<Emprestimo> getEmprestimos() {
        return emprestimos;
    }

    public void fechar() {
        entityManagerFactory.close();
    }

    public void cadastrarEmprestimo(String isbnEmprestimo, String nomeMembroEmprestimo) {
        EntityManager em = entityManagerFactory.createEntityManager();

        Livro livro;
        Membro membro;

        try {

            livro = em.createQuery("SELECT l FROM Livro l WHERE l.isbn = :isbn", Livro.class)
                    .setParameter("isbn", isbnEmprestimo)
                    .getSingleResult();

            membro = em.createQuery("SELECT m FROM Membro m WHERE m.nome = :nome", Membro.class)
                    .setParameter("nome", nomeMembroEmprestimo)
                    .getSingleResult();


            Emprestimo emprestimo = new Emprestimo(livro, membro, LocalDate.now());
            cadastrarEmprestimo(emprestimo);

        } catch (NoResultException e) {
            System.out.println("Livro ou Membro não encontrado.");
        } catch (Exception e) {
            System.out.println("Erro ao buscar dados: " + e.getMessage());
        } finally {
            em.close();
        }
    }
}
