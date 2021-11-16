package gradjanibrzogbroda.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;

@Repository
public interface JeloRepository extends JpaRepository<Jelo, Integer> {
	
	List<Jelo> findAll();
	
	@SuppressWarnings("unchecked")
	Jelo save(Jelo jelo);
	
	Jelo findOneById(Integer id);
	
	Jelo findOneByNaziv(String naziv);
	
	List<Jelo> findAllByKategorijaJela(KategorijaJela kat);
	
	List<Jelo> findAllByTipJela(TipJela tip);

}
