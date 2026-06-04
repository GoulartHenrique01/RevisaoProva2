package br.com.senac.revisao2.repositorio;

import br.com.senac.revisao2.entidades.PessoaJuridica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaJuridicaRepositorio extends JpaRepository<PessoaJuridica, Long> {
}
