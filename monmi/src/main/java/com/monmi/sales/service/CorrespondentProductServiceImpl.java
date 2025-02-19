package com.monmi.sales.service;

import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.sales.domain.CorrespondentProduct;
import com.monmi.sales.dto.CorrespondentProductDTO;
import com.monmi.sales.mapper.CorrespondentProductMapper;
import com.monmi.sales.repository.CorrespondentProductRepository;
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
public class CorrespondentProductServiceImpl implements CorrespondentProductService{
    private final ModelMapper modelMapper;

    private final CorrespondentProductRepository correspondentProductRepository;
    private final CorrespondentProductMapper correspondentProductMapper;

    @Override
    public PageResponseDTO correspondent_product_list(PageRequestDTO pageRequestDTO) {
        String[] types = pageRequestDTO.getTypes();
        String keyword = pageRequestDTO.getKeyword();
        Pageable pageable = pageRequestDTO.getPageable("companyName");

        Page<CorrespondentProduct> result = correspondentProductRepository.correspondent_product_search_all(types, keyword, pageable);

        List<CorrespondentProductDTO> dtoList = result.getContent().stream()
                .map(correspondentProduct -> modelMapper.map(correspondentProduct, CorrespondentProductDTO.class)).collect(Collectors.toList());

        return PageResponseDTO.<CorrespondentProductDTO>builder()
                .dtoList(dtoList)
                .build();
    }

    @Override
    public void correspondent_product_register(CorrespondentProductDTO correspondentProductDTO) {
        CorrespondentProduct correspondentProduct = modelMapper.map(correspondentProductDTO, CorrespondentProduct.class);

        correspondentProductMapper.correspondent_product_insert(correspondentProduct);
    }

    @Override
    public void correspondent_product_delete(String company_name) {
        correspondentProductMapper.correspondent_product_delete(company_name);
    }

    @Override
    public void correspondent_product_modify(CorrespondentProductDTO correspondentProductDTO) {
        CorrespondentProduct correspondentProduct = modelMapper.map(correspondentProductDTO, CorrespondentProduct.class);

        correspondentProductMapper.correspondent_product_modify(correspondentProduct);
    }
}
