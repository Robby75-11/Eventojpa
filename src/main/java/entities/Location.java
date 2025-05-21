package entities;

import jakarta.persistence.*;

@Entity
@Table(name = "locations") // Pluralizza il nome della tabella per convenzione
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(nullable = false, length = 100)
    private String citta;

    // Potrebbe esserci una relazione inversa OneToOne con Evento
    // @OneToOne(mappedBy = "location")
    // private Evento evento;

    public Location() {
    }

    public Location(String nome, String citta) {
        this.nome = nome;
        this.citta = citta;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCitta() {
        return citta;
    }

    public void setCitta(String citta) {
        this.citta = citta;
    }

    // Getter e Setter per la relazione inversa con Evento (se necessario)
    // public Evento getEvento() {
    //     return evento;
    // }
    //
    // public void setEvento(Evento evento) {
    //     this.evento = evento;
    // }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", citta='" + citta + '\'' +
                '}';
    }

}
