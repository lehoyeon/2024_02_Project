package com.monmi.production.service;

import com.monmi.production.dto.*;
import com.monmi.production.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionProcessServiceImpl implements ProductionProcessService{
    private final ModelMapper modelMapper;
    private final MaterialHoldingsRepository materialHoldingsRepository;
    private final MonamiPenbodyRepository monamiPenbodyRepository;
    private final MonamiPenheadRepository monamiPenheadRepository;
    private final MonamiRubberRepository monamiRubberRepository;
    private final MonamiTotalPenRepository monamiTotalpenRepository;
    private final MonamiAmountRepository monamiAmountRepository;

    @Override
    public List<MaterialHoldingsDTO> get_material_holdings_list() {
        List<MaterialHoldingsDTO> materialholdingsDTOList = materialHoldingsRepository.findAll().stream()
                .map(materialHoldings -> modelMapper.map(materialHoldings, MaterialHoldingsDTO.class)).collect(Collectors.toList());

        return materialholdingsDTOList;
    }
    @Override
    public List<MonamiPenbodyDTO> get_Penbody_list() {
        List<MonamiPenbodyDTO> monamiPenbodyDTOList = monamiPenbodyRepository.findAll().stream()
                .map(MonamiPenbody -> modelMapper.map(MonamiPenbody, MonamiPenbodyDTO.class)).collect(Collectors.toList());

        return monamiPenbodyDTOList;
    }
    @Override
    public List<MonamiPenheadDTO> get_Penhead_list() {
        List<MonamiPenheadDTO> monamiPenheadDTOList = monamiPenheadRepository.findAll().stream()
                .map(MonamiPenhead -> modelMapper.map(MonamiPenhead, MonamiPenheadDTO.class)).collect(Collectors.toList());

        return monamiPenheadDTOList;
    }
    @Override
    public List<MonamiRubberDTO> get_Rubber_list() {
        List<MonamiRubberDTO> monamiRubberDTOList = monamiRubberRepository.findAll().stream()
                .map(MonamiRubber -> modelMapper.map(MonamiRubber, MonamiRubberDTO.class)).collect(Collectors.toList());

        return monamiRubberDTOList;
    }

    @Override
    public List<MonamiTotalpenDTO> get_Totalpen_list() {
        List<MonamiTotalpenDTO> monamiTotalpenDTOList = monamiTotalpenRepository.findAll().stream()
                .map(MonamiTotalpen -> modelMapper.map(MonamiTotalpen, MonamiTotalpenDTO.class)).collect(Collectors.toList());

        return monamiTotalpenDTOList;
    }

    @Override
    public int get_Totalpen_Amount() {
        return monamiAmountRepository.findAmountByProductName("pen");
    }
}
