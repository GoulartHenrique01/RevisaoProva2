package br.com.senac.revisao2.service;

import br.com.senac.revisao2.dtos.PessoaFisicaRequestDto;
import br.com.senac.revisao2.entidades.PessoaFisica;
import br.com.senac.revisao2.repositorio.PessoaFisicaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaFisicaService {

    private PessoaFisicaRepositorio pessoaFisicaRepositorio;

    public PessoaFisicaService(PessoaFisicaRepositorio pessoaFisicaRepositorio) {
        this.pessoaFisicaRepositorio = pessoaFisicaRepositorio;
    }

    public List<PessoaFisica> listar(){
        return pessoaFisicaRepositorio.findAll();
    }

    public PessoaFisica atualizar(Long id,
                                  PessoaFisicaRequestDto pessoaFisica){

        validarCpf(pessoaFisica.getCpf());

        if(pessoaFisicaRepositorio.existsById(id)){
            PessoaFisica pessoaPersist =
                    this.pessoaRequestDtoParaPessoaFisica(pessoaFisica);

            pessoaPersist.setId(id);

            return pessoaFisicaRepositorio.save(pessoaPersist);
        }

        throw new RuntimeException("Pessoa não encontrada");
    }

    public PessoaFisica criar(PessoaFisicaRequestDto pessoaFisica){
        validarCpf(pessoaFisica.getCpf());

        PessoaFisica pessoaPersist =
                this.pessoaRequestDtoParaPessoaFisica(pessoaFisica);

        return pessoaFisicaRepositorio.save(pessoaPersist);
    }

    public void deletar(Long id){

        if(pessoaFisicaRepositorio.existsById(id)){
            pessoaFisicaRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Pessoa não encontrada");
    }

    private void validarCpf(String cpf){

        cpf = cpf.replaceAll("\\D", "");

        if(cpf.length() != 11){
            throw new RuntimeException(
                    "Documento informado não é um cpf"
            );
        }
    }

    private PessoaFisica pessoaRequestDtoParaPessoaFisica(
            PessoaFisicaRequestDto entrada){
        PessoaFisica saida = new PessoaFisica();
        saida.setNome(entrada.getNome());
        saida.setCpf(entrada.getCpf());
        saida.setDataNascimento(entrada.getDataNascimento());
        saida.setEmail(entrada.getEmail());

        return saida;
    }
}