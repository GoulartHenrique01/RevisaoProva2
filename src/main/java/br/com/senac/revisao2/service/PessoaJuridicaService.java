package br.com.senac.revisao2.service;

import br.com.senac.revisao2.dtos.PessoaJuridicaRequestDto;
import br.com.senac.revisao2.entidades.PessoaJuridica;
import br.com.senac.revisao2.repositorio.PessoaJuridicaRepositorio;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PessoaJuridicaService {
    private PessoaJuridicaRepositorio pessoaJuridicaRepositorio;

    public PessoaJuridicaService(PessoaJuridicaRepositorio pessoaJuridicaRepositorio) {
        this.pessoaJuridicaRepositorio = pessoaJuridicaRepositorio;
    }

    public List<PessoaJuridica> listar(){
        return pessoaJuridicaRepositorio.findAll();
    }

    public PessoaJuridica atualizar(Long id,
                                    PessoaJuridicaRequestDto pessoaJuridica){
        validarCnpj(pessoaJuridica.getCnpj());

        if (pessoaJuridicaRepositorio.existsById(id)){
            PessoaJuridica pessoaPersist =
                    this.pessoaRequestDtoParaPessoaJuridica(pessoaJuridica);

            PessoaJuridica pessoaBanco = pessoaJuridicaRepositorio.findById(id)
                    .orElseThrow(() -> new RuntimeException("Pessoa não encontrada"));


            pessoaPersist.setId(id);
            pessoaPersist.setEmail(pessoaBanco.getEmail());

            return pessoaJuridicaRepositorio.save(pessoaPersist);
        }
        throw new RuntimeException("Pessoa não encontrada");
    }

    public PessoaJuridica criar(PessoaJuridicaRequestDto pessoaJuridica){
        validarCnpj(pessoaJuridica.getCnpj());

        PessoaJuridica pessoaPerist =
                this.pessoaRequestDtoParaPessoaJuridica(pessoaJuridica);

        return pessoaJuridicaRepositorio.save(pessoaPerist);
    }

    public void deletar(Long id){

        if(pessoaJuridicaRepositorio.existsById(id)){
            pessoaJuridicaRepositorio.deleteById(id);
            return;
        }

        throw new RuntimeException("Pessoa não encontrada");
    }


    private void validarCnpj(String cnpj) {

        if(cnpj == null){
            throw new RuntimeException("CNPJ é obrigatório");
        }

        if(cnpj.length() != 14){
            throw new RuntimeException("CNPJ deve possuir 14 caracteres");
        }
    }

    private PessoaJuridica pessoaRequestDtoParaPessoaJuridica(
            PessoaJuridicaRequestDto entrada){
        PessoaJuridica saida = new PessoaJuridica();
        saida.setRazaoSocial(entrada.getRazaoSocial());
        saida.setDataFundacao(entrada.getDataFundacao());
        saida.setInscricaoEstadual(entrada.getInscricaoEstadual());
        saida.setCnpj(entrada.getCnpj());
        saida.setEmail(entrada.getEmail());

        return saida;
    }
}
