package br.com.senac.revisao2.controller;

import br.com.senac.revisao2.dtos.PessoaFisicaRequestDto;
import br.com.senac.revisao2.dtos.PessoaJuridicaRequestDto;
import br.com.senac.revisao2.entidades.PessoaFisica;
import br.com.senac.revisao2.entidades.PessoaJuridica;
import br.com.senac.revisao2.service.PessoaJuridicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin
@RequestMapping("/pessoaJuridica")
public class PessoaJuridicaController {
    private PessoaJuridicaService pessoaJuridicaService;

    public PessoaJuridicaController(PessoaJuridicaService pessoaJuridicaService) {
        this.pessoaJuridicaService = pessoaJuridicaService;
    }

    @GetMapping("/listar")
    public List<PessoaJuridica> listar(){

        return pessoaJuridicaService.listar();
    }

    @PostMapping("/criar")
    public ResponseEntity<PessoaJuridica> criar(@RequestBody PessoaJuridicaRequestDto pessoaJuridica){
        try{
            return ResponseEntity.ok(pessoaJuridicaService.criar(pessoaJuridica));
        }catch(Exception e){
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/atualizar/{id}")
    public ResponseEntity<PessoaJuridica> atualizar(@RequestBody PessoaJuridicaRequestDto pessoaJuridica,
                                                  @PathVariable Long id){
        try {
            return ResponseEntity.ok(pessoaJuridicaService.atualizar(id, pessoaJuridica));
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<PessoaJuridica> deletar(@PathVariable Long id){
        try {
            pessoaJuridicaService.deletar(id);
            return ResponseEntity.ok(null);
        }catch(RuntimeException e){
            return ResponseEntity.badRequest().body(null);
        }catch(Exception e){
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
