package com.association.afp.account.service;

import com.association.afp.account.domain.Afp;
import com.association.afp.account.mapper.AfpMapper;
import com.association.afp.account.model.AfpModel;
import com.association.afp.account.model.ResultModel;
import com.association.afp.account.repository.AfpRepository;
import com.association.afp.account.service.interfaces.IAfpService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AfpService extends BaseService implements IAfpService {

    private final AfpRepository afpRepository;
    private final AfpMapper afpMapper;

    @Override
    public ResultModel<List<AfpModel>> findAll() throws Exception {
        List<Afp> response = afpRepository.findAll();
        List<AfpModel> afpModels = afpMapper.EntitiesToModels(response);
        return Ok(afpModels);
    }

    @Override
    public ResultModel<AfpModel> findById(Integer id) throws Exception {
        Optional<Afp> afp = afpRepository.findById(id);
        if (afp.isPresent()) {
            AfpModel afpModel = afpMapper.EntityToModel(afp.get());
            return Ok (afpModel);
        } else {
            return  Error("Afp no encontrada!");
        }    }

    @Override
    public ResultModel<Integer> create(AfpModel entityModel) throws Exception {
        Afp afp = afpRepository.save(afpMapper.ModelToEntity(entityModel));
        Integer afpId = afp.getId();
        if (afpId > 0)
             return Ok(afpId , "Afp Registrada Correctamente!");
        else
            return Error ("Afp no pudo ser creada!");
    }

    @Override
    public ResultModel<String> update(Integer id, AfpModel entityModel) throws Exception {
        Optional<Afp> afp = afpRepository.findById(id);
        if (afp.isPresent()) {
            Afp afpUpdated  = afp.get();
            afpMapper.update(afpUpdated, entityModel);
            afpRepository.save(afpUpdated);
            return  Ok ("", "afp actualizado correctamente!");
        } else {
            return Error("No se encontraron datos");
        }
    }

    @Override
    public ResultModel<String> deleteById(Integer id) throws Exception {
      afpRepository.deleteById(id);
        return Ok("", "Afp eliminado correctamente!");
    }
}
