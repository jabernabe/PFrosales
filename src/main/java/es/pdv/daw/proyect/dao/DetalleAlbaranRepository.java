package es.pdv.daw.proyect.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.pdv.daw.proyect.entity.Albaran;
import es.pdv.daw.proyect.entity.DetalleAlbaran;

public interface DetalleAlbaranRepository extends JpaRepository<DetalleAlbaran, Integer> {

	List<DetalleAlbaran> findByAlbaran(Albaran albaran);

	int countByAlbaran(Albaran albaran);

	int countByIdDetalleAlbaran(int idDetalleAlbaran);

}
