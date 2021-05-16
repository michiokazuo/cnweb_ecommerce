package com.http.convert;

import java.util.List;

public interface Convert<Model, DTO> {

    DTO toDTO(Model model) throws Exception;

    List<DTO> toDtoList(List<Model> models) throws Exception;

    Model toModel(DTO dto) throws Exception;
}
