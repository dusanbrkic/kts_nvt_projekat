package gradjanibrzogbroda.backend.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import gradjanibrzogbroda.backend.domain.Jelo;
import gradjanibrzogbroda.backend.domain.KategorijaJela;
import gradjanibrzogbroda.backend.domain.TipJela;

@Repository
public interface JeloRepository extends JpaRepository<Jelo, Integer> {
	
	@Query("SELECT j FROM Jelo j WHERE (j.kategorijaJela = ?1 OR null = ?1) AND (j.tipJela = ?2 OR null = ?2) AND lower(j.naziv) LIKE %?3%")
	Page<Jelo> findAllByKategorijaJelaAndTipJela(Optional<KategorijaJela> kategorijaJela,Optional<TipJela> tipJela,String naziv, Pageable pageRequest);
	
	@SuppressWarnings("unchecked")
	Jelo save(Jelo jelo);
	
	Jelo findOneById(Integer id);
	
	Jelo findOneByNaziv(String naziv);
	
	List<Jelo> findAllByKategorijaJela(KategorijaJela kat);
	
	List<Jelo> findAllByTipJela(TipJela tip);
	

}
