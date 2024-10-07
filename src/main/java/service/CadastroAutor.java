package service;

import biblioteca.entities.Autor;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;


public class CadastroAutor {

    private EntityManagerFactory entityManagerFactory;

    public CadastroAutor() {
        entityManagerFactory = Persistence.createEntityManagerFactory("bibliotecaPU");
    }

    public void cadastrarAutor(Autor autor) {
        EntityManager emf = entityManagerFactory.createEntityManager();

        try {

            Autor autorExistente = emf.createQuery("SELECT a FROM Autor a WHERE a.nome = :nome", Autor.class)
                    .setParameter("nome", autor.getNome())
                    .getResultStream()
                    .findFirst()
                    .orElse(null);

            if (autorExistente != null) {
                System.out.println("-------------AUTOR JÁ CADASTRADO-------------");
                System.out.println();
                return;
            }

            emf.getTransaction().begin();
            emf.persist(autor);
            emf.getTransaction().commit();
            System.out.println();
            System.out.println("----------------- AUTOR CADASTRADO COM SUCESSO----------------- ");
            System.out.println();
        } catch (Exception e) {
            if (emf.getTransaction().isActive()) {
                emf.getTransaction().rollback();
            }
            System.out.println("ERRO AO CADASTRAR AUTOR");
        } finally {
            emf.close();
        }
    }

    public Autor buscarAutorPorNome(String nome) {
        EntityManager emf = entityManagerFactory.createEntityManager();
        try {
            return emf.createQuery("SELECT a FROM Autor a WHERE a.nome = :nome", Autor.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        } finally {
            emf.close();
        }
    }

    public void fechar() {
        entityManagerFactory.close();
    }
}

