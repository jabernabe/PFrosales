package es.pdv.daw.proyect.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import es.pdv.daw.proyect.entity.Empresa;


public interface EmpresaRepository extends JpaRepository<Empresa, Integer> {

	Empresa findByIdEmpresa(int i);

	

}
