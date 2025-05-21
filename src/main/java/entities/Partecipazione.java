package entities;

import jakarta.persistence.*;
import enumeration.StatoPartecipazione;

@Entity
@Table(name = "partecipazioni")

public class Partecipazione {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false) // Indica che la relazione non può essere nulla
    @JoinColumn(name = "persona_id", nullable = false)
    private Persona persona;

    @ManyToOne(optional = false) // Indica che la relazione non può essere nulla
    @JoinColumn(name = "evento_id", nullable = false)
    private Evento evento;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private StatoPartecipazione stato;

    public Partecipazione(StatoPartecipazione stato) {
        this.stato = stato;
    }

    public Partecipazione() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public StatoPartecipazione getStato() {
        return stato;
    }

    public void setStato(StatoPartecipazione stato) {
        this.stato = stato;
    }

    @Override
    public String toString() {
        return "Partecipazione{" +
                "id=" + id +
                ", persona=" + (persona != null ? persona.getId() : null) + // Evita NullPointerException nel toString
                ", evento=" + (evento != null ? evento.getId() : null) +   // Evita NullPointerException nel toString
                ", stato=" + stato +
                '}';
    }
}
