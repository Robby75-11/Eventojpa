

import dao.*;
import entities.*;
import enumeration.Sesso;
import enumeration.StatoPartecipazione;
import enumeration.TipoEvento;
import java.time.LocalDate;
import java.util.List;

public class Main { // Ho rinominato la classe in Main come richiesto
    public static void main(String[] args) {
        PersonaDao personaDAO = new PersonaDao();
        LocationDao locationDAO = new LocationDao();
        EventoDao eventoDAO = new EventoDao();
        PartecipazioneDao partecipazioneDAO = new PartecipazioneDao();

        // Crea e salva una Persona
        Persona persona1 = new Persona("Mario", "Rossi", "mario.rossi@example.com", LocalDate.of(1980, 5, 15), Sesso.M);
        personaDAO.save(persona1);
        System.out.println("Persona salvata: " + persona1);

        // Crea e salva una Location
        Location location1 = new Location("Teatro Grande", "Roma");
        locationDAO.save(location1);
        System.out.println("Location salvata: " + location1);

        // Crea e salva un Evento
        Evento evento1 = new Evento("Concerto Rock", LocalDate.of(2025, 6, 10), "Concerto della band XYZ", TipoEvento.PUBBLICO, 1000);
        evento1.setLocation(location1);
        eventoDAO.save(evento1);
        System.out.println("Evento salvato: " + evento1);

        // Crea e salva una Partecipazione
        Partecipazione partecipazione1 = new Partecipazione(StatoPartecipazione.CONFERMATA);
        partecipazione1.setPersona(persona1);
        partecipazione1.setEvento(evento1);
        partecipazioneDAO.save(partecipazione1);
        System.out.println("Partecipazione salvata: " + partecipazione1);

        // Leggi una Persona
        Persona personaRecuperata = personaDAO.findById(persona1.getId());
        System.out.println("Persona recuperata: " + personaRecuperata);

        // Leggi una Location
        Location locationRecuperata = locationDAO.findById(location1.getId());
        System.out.println("Location recuperata: " + locationRecuperata);

        // Leggi un Evento
        Evento eventoRecuperato = eventoDAO.findById(evento1.getId());
        System.out.println("Evento recuperato: " + eventoRecuperato);

        // Leggi una Partecipazione
        Partecipazione partecipazioneRecuperata = partecipazioneDAO.findById(partecipazione1.getId());
        System.out.println("Partecipazione recuperata: " + partecipazioneRecuperata);

        // Aggiorna una Persona
        if (personaRecuperata != null) {
            personaRecuperata.setEmail("mario.rossi.aggiornato@example.com");
            personaDAO.update(personaRecuperata);
            System.out.println("Persona aggiornata: " + personaRecuperata);
        }

        // Leggi tutte le Persone
        List<Persona> tutteLePersone = personaDAO.findAll();
        System.out.println("Tutte le persone: " + tutteLePersone);

        // Elimina una Partecipazione
        if (partecipazioneRecuperata != null) {
            partecipazioneDAO.delete(partecipazioneRecuperata.getId());
            System.out.println("Partecipazione eliminata con ID: " + partecipazioneRecuperata.getId());
        }

        // Chiudi l'EntityManagerFactory alla fine dell'applicazione
        EventoDao.closeEntityManagerFactory();
        LocationDao.closeEntityManagerFactory();
        PartecipazioneDao.closeEntityManagerFactory();
        PersonaDao.closeEntityManagerFactory();
    }
}
