package service;

import biblioteca.entities.Membro;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CadastroMembro {

    private Set<Membro> membros;
    private EntityManagerFactory emf;

    public CadastroMembro() {
        membros = new HashSet<>();
        emf = Persistence.createEntityManagerFactory("bibliotecaPU");
    }

    public void cadastrarMembro(Membro membro) {

        for (Membro m : membros) {
            if (m.getEmail().equalsIgnoreCase(membro.getEmail())) {
                System.out.println("O MEMBRO JÁ POSSUI CADASTRO");
                return;
            }
        }

        membros.add(membro);

        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();

        try {
            em.persist(membro);
            em.getTransaction().commit();
            System.out.println();
            System.out.println("--------------- MEMBRO CADASTRADO ---------------");
            System.out.println();
        } catch (Exception e) {
            em.getTransaction().rollback();
            System.out.println("ERRO AO CADASTRAR MEMBRO"+e.getMessage());
            e.printStackTrace();
        } finally {
            em.close();
        }
    }

    public Membro buscarMembroPorNome(String nome) {
        EntityManager em = emf.createEntityManager();

        try {
            return em.createQuery("SELECT m FROM Membro m WHERE m.nome = :nome", Membro.class)
                    .setParameter("nome", nome)
                    .getSingleResult();
        } catch (Exception e) {
            System.out.println("Membro não encontrado: " + e.getMessage());
            return null;
        } finally {
            em.close();
        }
    }

    public List<Membro> getTodosMembros() {
        EntityManager em = emf.createEntityManager();
        List<Membro> membros;

        try {
            membros = em.createQuery("SELECT m FROM Membro m", Membro.class).getResultList();
        } finally {
            em.close();
        }

        return membros;
    }

    public Set<Membro> getMembros() {
        return membros;
    }

    public void fechar() {
        emf.close();
    }
}
