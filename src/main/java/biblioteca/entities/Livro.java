package biblioteca.entities;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
public class Livro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    private Autor autor;

    @Column(name = "data_publicacao")
    private LocalDate dataPublicacao;

    @Column(unique = true)
    private String isbn;

    private String genero;

    @Column(name = "quantidade_disponivel")
    private int quantidadeDisponivel;

    public Livro(String titulo, Autor autor, LocalDate dataPublicacao, String isbn, String genero, int quantidadeDisponivel) {
        this.titulo = titulo;
        this.autor = autor;
        this.dataPublicacao = dataPublicacao;
        this.isbn = isbn;
        this.genero = genero;
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    public Livro() {

    }

    public Livro(String isbn, String titulo, String autor) {

    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public LocalDate getDataPublicacao() {
        return dataPublicacao;
    }

    public void setDataPublicacao(LocalDate dataPublicacao) {
        this.dataPublicacao = dataPublicacao;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public int getQuantidadeDisponivel() {
        return quantidadeDisponivel;
    }

    public void setQuantidadeDisponivel(int quantidadeDisponivel) {
        this.quantidadeDisponivel = quantidadeDisponivel;
    }

    @Override
    public String toString() {
        return "Livro: " + titulo + " | Autor: " + autor.getNome() +
                " | Publicado em: " + dataPublicacao +
                " | ISBN: " + isbn + " | Gênero: " + genero +
                " | Quantidade disponivel: " + quantidadeDisponivel;
    }
}
