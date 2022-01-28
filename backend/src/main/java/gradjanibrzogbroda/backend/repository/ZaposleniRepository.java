package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Zaposleni;

import java.util.List;
import java.util.Set;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {
    
    List<Zaposleni> findAll();
    
    Zaposleni findOneById(Integer id);

    void deleteById(Integer id);

    @SuppressWarnings("unchecked")
    Zaposleni save (Zaposleni zaposleni);
    
    Zaposleni findOneByUsername(String username);

    @Query(value = "SELECT z FROM Zaposleni z join z.roles r where UPPER(z.ime) LIKE UPPER(:pretragaIme) and UPPER(z.prezime) LIKE UPPER(:pretragaPrezime) and r.role in :filterTipZaposlenja")
    Page<Zaposleni> getAllPaged(Pageable pageable, @Param("pretragaIme") String pretragaIme, @Param("pretragaPrezime") String pretragaPrezime, @Param("filterTipZaposlenja") Set<String> filterTipZaposlenja);
}
