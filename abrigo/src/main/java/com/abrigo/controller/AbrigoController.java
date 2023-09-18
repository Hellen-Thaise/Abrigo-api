package com.abrigo.controller;

import com.abrigo.dto.MoradorDto;
import com.abrigo.model.AbrigoModel;
import com.abrigo.service.AbrigoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/abrigo")

public class AbrigoController {

    @Autowired
    AbrigoService abrigoService;


    @Operation(summary = "Buscar todos os moradores cadastrados", description = "Busca todos os moradores")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Busca realizada com sucesso!"),
            @ApiResponse(responseCode = "405", description = "Not found - Nenhum morador cadastrado!")
    })

    @PreAuthorize("hasAnyRole('ROLE_USER', 'ROLE_ADMIN')")
    @GetMapping
    public List<MoradorDto> listar(){

        //o DTO pega a lista de produtos e seta as informações validas para ele(DTO)

        List<AbrigoModel> abrigo = abrigoService.listar();
        List<MoradorDto> moradorDto = new ArrayList<>();

        for (AbrigoModel abrigoDto : abrigo){
            MoradorDto dto = new MoradorDto();


            dto.setNome(abrigoDto.getNome());
            dto.setDescricao(abrigoDto.getDescricao());
            moradorDto.add(dto);

        }
        return moradorDto;
    }

    @Operation(summary = "Cadastrar um novo morador", description = "Cadastra um novo produto")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Morador cadastrado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not found - Morador não cadastrado")
    })
    @PostMapping
    public ResponseEntity<AbrigoModel> cadastrar(@RequestBody AbrigoModel produtoModel){
        AbrigoModel novoMorador = abrigoService.cadastrar(produtoModel);
        return new ResponseEntity<>(novoMorador, HttpStatus.CREATED);
    }

    @Operation(summary = "Alterar um cadastro de morador pelo ID", description = "Altera dados de um morador ao buscar o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Alteração realizada com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not found - Morador não encontrado")
    })
    @PutMapping(path = "/{id}")
    public AbrigoModel alterar(@PathVariable Long id,
                                @RequestBody AbrigoModel abrigoModel){

        Optional<AbrigoModel> abrigo = abrigoService.buscarPorId(id);
        if (abrigo.isPresent()){

            return abrigoService.alterar(id, abrigoModel);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "morador não encontrado!");
        }
    }

    @Operation(summary = "Deleta um cadastro de morador pelo ID", description = "Deleta um cadastro de morador conforme o ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Deletado com sucesso!"),
            @ApiResponse(responseCode = "404", description = "Not found - id não encontrado")
    })
    @DeleteMapping(path = "/{id}")
    public void deletar(@PathVariable Long id){

        Optional<AbrigoModel> morador = abrigoService.buscarPorId(id);
        if (morador.isPresent()){

            abrigoService.deletar(id);

        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "id não encontrado!");
        }
    }

}
