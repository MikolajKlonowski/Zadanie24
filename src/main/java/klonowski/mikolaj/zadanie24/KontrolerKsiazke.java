package klonowski.mikolaj.zadanie24;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.TypedQuery;
import java.util.List;

@Controller
public class KontrolerKsiazke {
    @PersistenceUnit
    @Autowired
    private EntityManagerFactory factory;

    @GetMapping("/")

    public String homeSite(Model model) {
        model.addAttribute("nowaKsiazka", new Book());
        model.addAttribute("wszystkieKategorie", Kategoria.values());
        EntityManager entityManager = factory.createEntityManager();
        TypedQuery<Book> zapytanie = entityManager.createQuery("select b from Book b ", Book.class);
        List<Book> wszystkie = zapytanie.getResultList();
        model.addAttribute("listaKsiazek", wszystkie);
        return "formularzKsiazek";
    }

    @PostMapping("/dodajKsiazke")
    public String add(Book book) {
        EntityManager entityManager = factory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(book);
        entityManager.getTransaction().commit();
        return "redirect:/";
    }


}
