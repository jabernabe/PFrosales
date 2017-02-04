package es.pdv.daw.proyect.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Albaran;


public interface AlbaranesRepository extends JpaRepository<Albaran, Integer>{

	Albaran findByIdAlbaran(int idAlbaran);

	int countByIdAlbaran(int idAlbaran);
}
