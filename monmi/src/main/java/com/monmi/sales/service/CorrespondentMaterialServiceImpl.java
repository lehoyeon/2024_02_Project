package com.monmi.sales.service;

import com.monmi.dto.MaterialDTO;
import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.repository.MaterialRepository;
import com.monmi.sales.domain.CorrespondentMaterial;
import com.monmi.sales.dto.CorrespondentMaterialDTO;
import com.monmi.sales.dto.CorrespondentMaterialListDTO;
import com.monmi.sales.mapper.CorrespondentMaterialMapper;
import com.monmi.sales.repository.CorrespondentMaterialRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class CorrespondentMaterialServiceImpl implements CorrespondentMaterialService{
    private final ModelMapper modelMapper;

    private final CorrespondentMaterialRepository correspondentMaterialRepository;
    private final MaterialRepository materialRepository;
    private final CorrespondentMaterialMapper correspondentMaterialMapper;

    @Override
    public PageResponseDTO correspondent_material_list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("companyName");

        Page<CorrespondentMaterial> result = correspondentMaterialRepository.correspondent_material_search_all(types, keyword, pageable);

        List<CorrespondentMaterialDTO> dtoList = result.getContent().stream()
                .map(correspondentMaterial -> modelMapper.map(correspondentMaterial, CorrespondentMaterialDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<CorrespondentMaterialDTO>builder()
                .dtoList(dtoList)
                .build();
    }

    @Override
    public CorrespondentMaterialListDTO correspondent_material_material_list() {
        List<MaterialDTO> dtoList = materialRepository.findAll().stream()
                .map(material -> modelMapper.map(material, MaterialDTO.class)).collect(Collectors.toList());

        return CorrespondentMaterialListDTO.builder()
                .dtoList(dtoList)
                .build();
    }

    @Override
    public void correspondent_material_register(CorrespondentMaterialDTO correspondentMaterialDTO) {
        CorrespondentMaterial correspondentMaterial = modelMapper.map(correspondentMaterialDTO, CorrespondentMaterial.class);

        correspondentMaterialMapper.correspondent_material_insert(correspondentMaterial);
    }

    @Override
    public void correspondent_material_delete(String company_name) {
        correspondentMaterialMapper.correspondent_material_delete(company_name);
    }

    @Override
    public void correspondent_material_modify(CorrespondentMaterialDTO correspondentMaterialDTO) {
        CorrespondentMaterial correspondentMaterial = modelMapper.map(correspondentMaterialDTO, CorrespondentMaterial.class);

        correspondentMaterialMapper.correspondent_material_modify(correspondentMaterial);
    }
}
