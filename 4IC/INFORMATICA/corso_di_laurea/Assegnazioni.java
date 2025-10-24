import java.util.ArrayList;

public class Assegnazioni {
    private ArrayList<Tesi> tesi;
    private ArrayList<Studente> laureandi;

    public Assegnazioni() {
        tesi = new ArrayList<>();
        laureandi = new ArrayList<>();
    }

    public void aggiungiTesi(String titoloTesi) {
        boolean isIn = tesiAlreadyExists(titoloTesi) != null;
        if (isIn) {
            System.out.println("Tesi già esistente");
            return;
        }

        Tesi newTesi = new Tesi(titoloTesi);
        tesi.add(newTesi);
    }

    public void aggiungiStudente(String nomeStudente, String titoloTesi) {
        boolean doesStudentExist = studentAlreadyExists(nomeStudente) != null;
        if (doesStudentExist) {
            System.out.println("Studente esiste già");
            return;
        }
        Studente newStudente = new Studente(nomeStudente, null);
        Tesi tesiFound = tesiAlreadyExists(titoloTesi);

        newStudente.setTesi(tesiFound != null ? tesiFound : new Tesi(titoloTesi));
        laureandi.add(newStudente);
    }

    public void laureato(String nomeStudente) {
        Studente studente = studentAlreadyExists(nomeStudente);
        Tesi t = tesiAlreadyExists(studente.getTesi().getTitle());
        if (studente == null || t == null) {
            System.out.println("Errore - Studente o Tesi non presente nel sistema");
            return;
        }
        laureandi.remove(studente);
        tesi.remove(t);
    }

    public void liberaTesi(String titoloTesi) {
        for (Studente studente : laureandi) {
            Tesi currentTesi = studente.getTesi();
            if(currentTesi == null) continue;
            if(currentTesi.getTitle().equals(titoloTesi)){
                laureandi.remove(studente);
                break;
            }
        }
        System.out.println("Nessuna tesi trovata");
    }

    public int disponibili() {
        int tot =0;
        for (Tesi t : tesi) {
            boolean found = false;
            for (Studente studente : laureandi) {
                if(studente.getTesi().equals(t)) {
                    found =true;
                    break;
                }
            }
            if (!found) tot++;
        }
        return tot;
    }
    // additional methods
    public Tesi tesiAlreadyExists(String nomeTesi) {
        Tesi tesiFound = null;
        for (Tesi t : tesi) {
            if (nomeTesi.equals(t.getTitle())) {
                tesiFound = t;
            }
        }
        return tesiFound;
    }

    public Studente studentAlreadyExists(String nomeStudente) {
        Studente studenteFound = null;
        for (Studente t : laureandi) {
            if (nomeStudente.equals(t.getNome())) {
                studenteFound = t;
            }
        }
        return studenteFound;
    }
}