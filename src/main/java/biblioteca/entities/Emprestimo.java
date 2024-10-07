package biblioteca.entities;


import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@Entity
@Table(name = "emprestimo")
public class Emprestimo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "livro_id")
    private Livro livro;

    @ManyToOne
    @JoinColumn(name = "membro_id", nullable = false)
    private Membro membro;

    private LocalDate dataEmprestimo;

    @Column(name = "dataPrevistaDevolucao")
    private LocalDate dataPrevistaDevolucao;

    @Column(name = "dataDevolucao")
    private LocalDate dataDevolucao;

    private BigDecimal multa;

    @Enumerated(EnumType.STRING)
    private StatusEmprestimo status;

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Emprestimo(Livro livro, Membro membro, LocalDate dataEmprestimo, BigDecimal multa, StatusEmprestimo status) {
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(5);
        this.multa = multa;
        this.status = StatusEmprestimo.ATIVO;
    }

    public Emprestimo(Livro livro, Membro membro, LocalDate dataEmprestimo) {
        this.livro = livro;
        this.membro = membro;
        this.dataEmprestimo = dataEmprestimo;
        this.dataPrevistaDevolucao = dataEmprestimo.plusDays(5);
        this.multa = BigDecimal.ZERO;
        this.status = StatusEmprestimo.ATIVO;
    }

    public Emprestimo() {

    }


    public Livro getLivro() {
        return livro;
    }

    public Livro setLivro() {
        return livro;
    }

    public Membro getMembro() {
        return membro;
    }

    public Livro setMembro() {
        return livro;
    }

    public LocalDate getDataEmprestimo() {
        return dataEmprestimo;
    }

    public LocalDate setDataEmprestimo(LocalDate now) {
        return dataEmprestimo;
    }

    public LocalDate getDataPrevistaDevolucao() {
        return dataPrevistaDevolucao;
    }

    public LocalDate getDataDevolucao() {
        return dataDevolucao;
    }

    public void setDataDevolucao(LocalDate dataDevolucao) {
        this.dataDevolucao = dataDevolucao;
    }

    public BigDecimal getMulta() {
        return multa;
    }

    public BigDecimal calcularMulta(){
        if(dataDevolucao != null && dataDevolucao.isAfter(dataPrevistaDevolucao)){
            long diasDeAtraso = ChronoUnit.DAYS.between(dataPrevistaDevolucao, dataDevolucao);;
            this.multa = BigDecimal.valueOf(diasDeAtraso).multiply(new BigDecimal(2.00));
        }
        return this.multa;
    }

    public StatusEmprestimo getStatus() {
        return status;
    }

    public void setStatus(StatusEmprestimo status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Livro: " + livro.getTitulo() + " | Membro: " + membro.getNome() +
                " | Status do empréstimo: " + status +
                " | Data de empréstimo: " + dataEmprestimo +
                " | Data para devolução: " + dataPrevistaDevolucao;
    }
}