package br.com.senac.revisao2.controller;

import br.com.senac.revisao2.dtos.PessoaFisicaRequestDto;
import br.com.senac.revisao2.entidades.PessoaFisica;
import br.com.senac.revisao2.repositorio.PessoaFisicaRepositorio;
import br.com.senac.revisao2.service.PessoaFisicaService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pessoaFisica")
public class PessoaFisicaController{
    private PessoaFisicaService pessoaFisicaService;

    public PessoaFisicaController(PessoaFisicaService pessoaFisicaService) {
        this.pessoaFisicaService = pessoaFisicaService;
    }

    @GetMapping("/listar")
    public List<PessoaFisica> listar(){

        return pessoaFisicaService.listar();
    }

        @PostMapping("/criar")
        public ResponseEntity<PessoaFisica> criar(@RequestBody PessoaFisicaRequestDto pessoaFisica){
            try{
                return ResponseEntity.ok(pessoaFisicaService.criar(pessoaFisica));
            }catch(Exception e){
                return ResponseEntity.badRequest().body(null);
            }
        }

        @PutMapping("/atualizar/{id}")
        public ResponseEntity<PessoaFisica> atualizar(@RequestBody PessoaFisicaRequestDto pessoaFisica,
                                                 @PathVariable Long id){
            try {
                return ResponseEntity.ok(pessoaFisicaService.atualizar(id, pessoaFisica));
            }catch(RuntimeException e){
                return ResponseEntity.badRequest().body(null);
            }catch(Exception e){
                return ResponseEntity.internalServerError().body(null);
            }
        }
        @DeleteMapping("/deletar/{id}")
        public ResponseEntity<PessoaFisica> deletar(@PathVariable Long id){
            try {
                pessoaFisicaService.deletar(id);
                return ResponseEntity.ok(null);
            }catch(RuntimeException e){
                return ResponseEntity.badRequest().body(null);
            }catch(Exception e){
                return ResponseEntity.internalServerError().body(null);
            }
        }
    }
