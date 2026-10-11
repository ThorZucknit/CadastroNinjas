package dev.java10x.CadastroDeNinjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissoesService {

    private MissoesRepository missoesRepository;

    public MissoesService (MissoesRepository missoesRepository){
        this.missoesRepository = missoesRepository;
    }

    public List<MissoesModel> listarMissoes(){

        return missoesRepository.findAll();
    }

    public MissoesModel criarMissoes(MissoesModel missoesModel){

        return missoesRepository.save(missoesModel);
    }

    public MissoesModel listarMissoesPorId(Long id){

        Optional<MissoesModel> missoesModel = missoesRepository.findById(id);
        return missoesModel.orElse(null);
    }

    public MissoesModel atualizarMissoesPorID(Long id, MissoesModel missoes){
        if(missoesRepository.existsById(id)){
            missoes.setId(id);
            return missoesRepository.save(missoes);
        }
        return null;
    }

    public void deletarMissaoPorID(Long id){

        missoesRepository.deleteById(id);
    }
}
