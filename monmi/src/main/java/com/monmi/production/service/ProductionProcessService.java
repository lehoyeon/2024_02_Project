package com.monmi.production.service;

import com.monmi.production.dto.*;

import java.util.List;

public interface ProductionProcessService {
    List<MaterialHoldingsDTO> get_material_holdings_list();
    List<MonamiPenbodyDTO> get_Penbody_list();
    List<MonamiPenheadDTO> get_Penhead_list();
    List<MonamiRubberDTO> get_Rubber_list();
    List<MonamiTotalpenDTO> get_Totalpen_list();
    int get_Totalpen_Amount();
}
