package gradjanibrzogbroda.backend.repository;

import gradjanibrzogbroda.backend.domain.Zaposleni;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ZaposleniRepository extends JpaRepository<Zaposleni, Integer> {
    
    List<Zaposleni> findAll();
    
    Zaposleni findOneById(Integer id);

    void deleteById(Integer id);

    @SuppressWarnings("unchecked")
    Zaposleni save (Zaposleni zaposleni);

    Zaposleni findOneByIdentificationNumber(String identificationNumber);

    void deleteByIdentificationNumber(String idNum);
}
