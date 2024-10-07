package biblioteca.entities;

import com.sun.istack.NotNull;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Membro extends Pessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String endereco;
    private String telefone;
    private LocalDate dataAssociacao;

    @Column(nullable = false, unique = true)
    @NotNull
    private String email;

    @OneToMany(mappedBy = "membro", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Emprestimo> emprestimos;

    public Membro(String nome, LocalDate dataNascimento, String nacionalidade, String endereco, String telefone, String email, LocalDate dataAssociacao, List<Emprestimo> emprestimos) {
        super(nome, dataNascimento, nacionalidade);
        this.endereco = endereco;
        this.telefone = telefone;
        this.email = email;
        this.dataAssociacao = LocalDate.now();
        this.emprestimos = new ArrayList<>();

    }

    public Membro() {
        this.emprestimos = new ArrayList<>();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {

        this.email = email;
    }

    public LocalDate getDataAssociacao() {

        return dataAssociacao;
    }

    public void setDataAssociacao(LocalDate dataAssociacao) {

        this.dataAssociacao = dataAssociacao;
    }

    public List<Emprestimo> getEmprestimos() {

        return emprestimos;
    }

    public void setEmprestimos(List<Emprestimo> emprestimos) {

        this.emprestimos = emprestimos;
    }

    @Override
    public String toString() {
        return super.toString() + " | Endereço: " + endereco + " | Telefone: " + telefone +
                " | E-mail: " + email + " | Data de Associação: " + dataAssociacao +
                " | Quantidade de Empréstimos: " + emprestimos.size();
    }
}