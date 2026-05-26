package br.com.senac.revisao2.repositorio;

import br.com.senac.revisao2.entidades.PessoaFisica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PessoaFisicaRepositorio extends JpaRepository<PessoaFisica, Long> {
}
