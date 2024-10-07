package biblioteca.entities;
import javax.persistence.Entity;
import javax.persistence.Lob;
import java.time.LocalDate;

@Entity
public class Autor extends Pessoa {

    @Lob
    private String biografia;

    public Autor(String nome, LocalDate dataNascimento, String nacionalidade, String biografia) {
        super(nome, dataNascimento, nacionalidade);
        this.biografia = biografia;
    }

    public Autor() {

    }

    public Autor(String nome) {
        this.setNome(nome);
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    @Override
    public String toString() {
        return super.toString() + " | Biografia: " + biografia;
    }
}
