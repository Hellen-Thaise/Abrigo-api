package com.abrigo.service;

import com.abrigo.model.AbrigoModel;
import com.abrigo.repository.AbrigoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    AbrigoRepository abrigoRepository;

    public List<AbrigoModel> listar(){

        return abrigoRepository.findAll();
    }

    public Optional<AbrigoModel> buscarPorId(Long id) {

        return abrigoRepository.findById(id);
    }

    public AbrigoModel cadastrar(AbrigoModel produtoModel) {

        return abrigoRepository.save(produtoModel);

    }

    public AbrigoModel alterar(Long id , AbrigoModel abrigoModel){
        AbrigoModel abrigo = buscarPorId(id).get();

        if (abrigoModel.getNome() != null){
            abrigo.setNome(abrigoModel.getNome());
        }
        if (abrigoModel.getDescricao() != null){
            abrigo.setDescricao(abrigoModel.getDescricao());
        }
        if (abrigoModel.getNumeroMorador() != null){
            abrigo.setNumeroMorador(abrigoModel.getNumeroMorador());
        }
        if (abrigoModel.getArmarioPertences() != null){
            abrigo.setArmarioPertences(abrigoModel.getArmarioPertences());
        }
        return abrigoRepository.save(abrigo);

    }
    public void deletar(Long id) {
        abrigoRepository.deleteById(id);
    }
}
