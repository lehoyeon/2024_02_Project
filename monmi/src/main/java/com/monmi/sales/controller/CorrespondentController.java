package com.monmi.sales.controller;

import com.monmi.dto.PageRequestDTO;
import com.monmi.dto.PageResponseDTO;
import com.monmi.sales.dto.CorrespondentMaterialDTO;
import com.monmi.sales.dto.CorrespondentMaterialListDTO;
import com.monmi.sales.dto.CorrespondentProductDTO;
import com.monmi.sales.service.CorrespondentMaterialService;
import com.monmi.sales.service.CorrespondentProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@Log4j2
@RequiredArgsConstructor
@RequestMapping("/sales")
public class CorrespondentController {
    private final CorrespondentMaterialService correspondentMaterialService;
    private final CorrespondentProductService correspondentProductService;

    // material
    @GetMapping("/correspondent_material_management")
    public void material_process(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO pageResponseDTO = correspondentMaterialService.correspondent_material_list(pageRequestDTO);
        CorrespondentMaterialListDTO correspondentMaterialListDTO = correspondentMaterialService.correspondent_material_material_list();
        log.info("correspondent_material_management..............");

        model.addAttribute("responseDTO", pageResponseDTO);
        model.addAttribute("correspondentMaterialListDTO", correspondentMaterialListDTO);
    }

    @PostMapping("/correspondent_material_management_register")
    public String material_registerPost(
            @Valid CorrespondentMaterialDTO correspondentMaterialDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/sales/correspondent_material_management";
        }
        correspondentMaterialService.correspondent_material_register(correspondentMaterialDTO);

        return "redirect:/sales/correspondent_material_management";
    }

    @PostMapping("/correspondent_material_management_delete")
    public String material_delete(
            @RequestParam String companyName,
            RedirectAttributes redirectAttributes
    ) {
        correspondentMaterialService.correspondent_material_delete(companyName);

        return "redirect:/sales/correspondent_material_management";
    }

    @PostMapping("/correspondent_material_management_modify")
    public String material_modifyPost(
            @Valid CorrespondentMaterialDTO correspondentMaterialDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/sales/correspondent_material_management";
        }
        correspondentMaterialService.correspondent_material_modify(correspondentMaterialDTO);

        return "redirect:/sales/correspondent_material_management";
    }

    // product
    @GetMapping("/correspondent_product_management")
    public void product_process(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO pageResponseDTO = correspondentProductService.correspondent_product_list(pageRequestDTO);
        log.info("correspondent_material_management..............");

        model.addAttribute("responseDTO", pageResponseDTO);
    }

    @PostMapping("/correspondent_product_management_register")
    public String product_registerPost(
            @Valid CorrespondentProductDTO correspondentProductDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/sales/correspondent_product_management";
        }
        correspondentProductService.correspondent_product_register(correspondentProductDTO);

        return "redirect:/sales/correspondent_product_management";
    }

    @PostMapping("/correspondent_product_management_delete")
    public String product_delete(
            @RequestParam String companyName,
            RedirectAttributes redirectAttributes
    ) {
        correspondentProductService.correspondent_product_delete(companyName);

        return "redirect:/sales/correspondent_product_management";
    }

    @PostMapping("/correspondent_product_management_modify")
    public String product_modifyPost(
            @Valid CorrespondentProductDTO correspondentProductDTO,
            BindingResult bindingResult,
            RedirectAttributes redirectAttributes
    ) {

        if(bindingResult.hasErrors()) {
            log.info("has errors.......");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());

            return "redirect:/sales/correspondent_product_management";
        }
        correspondentProductService.correspondent_product_modify(correspondentProductDTO);

        return "redirect:/sales/correspondent_product_management";
    }
}
