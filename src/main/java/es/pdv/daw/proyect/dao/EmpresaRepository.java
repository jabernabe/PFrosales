package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import es.pdv.daw.proyect.entity.Empresa;

/**
 * Interface DAO de la tabla empresa.
 * 
 * @author mint
 *
 */
public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	/**
	 * Metodo que devuelve la empresa con el identificador suministrado por
	 * parametro.
	 * 
	 * @param i
	 * @return
	 */
	Empresa findByIdEmpresa(int i);

}
