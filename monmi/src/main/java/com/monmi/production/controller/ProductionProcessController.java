package com.monmi.production.controller;

import com.monmi.production.dto.MaterialHoldingsDTO;
import com.monmi.production.service.ProductionProcessService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/production")
public class ProductionProcessController {
    private final ProductionProcessService productionProcessService;

    @GetMapping("/production_process")
    public void production_process(Model model) {
        productionProcessService.update_Recent_data();

        model.addAttribute("MaterialHoldingsList", productionProcessService.get_material_holdings_list());
        model.addAttribute("monamiPenbodyList", productionProcessService.get_Penbody_list());
        model.addAttribute("monamiPenheadList", productionProcessService.get_Penhead_list());
        model.addAttribute("monamiRubberList", productionProcessService.get_Rubber_list());
        model.addAttribute("monamiTotalpenList", productionProcessService.get_Totalpen_list());
        model.addAttribute("monamiTotalpenAmount", productionProcessService.get_Totalpen_Amount());
    }
}
