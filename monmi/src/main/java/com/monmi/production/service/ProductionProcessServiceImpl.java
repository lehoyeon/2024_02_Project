package com.monmi.production.service;

import com.monmi.domain.Material;
import com.monmi.domain.Part;
import com.monmi.production.domain.MaterialHoldings;
import com.monmi.production.domain.ProductComb;
import com.monmi.production.dto.*;
import com.monmi.production.mapper.ProductionProcessMapper;
import com.monmi.production.repository.*;
import com.monmi.repository.MaterialRepository;
import com.monmi.repository.PartRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class ProductionProcessServiceImpl implements ProductionProcessService{
    private final ModelMapper modelMapper;
    private final MaterialRepository materialRepository;
    private final PartRepository partRepository;
    private final MaterialHoldingsRepository materialHoldingsRepository;
    private final MonamiPenbodyRepository monamiPenbodyRepository;
    private final MonamiPenheadRepository monamiPenheadRepository;
    private final MonamiRubberRepository monamiRubberRepository;
    private final MonamiTotalPenRepository monamiTotalpenRepository;
    private final MonamiAmountRepository monamiAmountRepository;
    private final ProductionProcessMapper productionProcessMapper;
    private final ProductCombRepository productCombRepository;

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

    @Override
    public void update_Recent_data() {
        List<Part> parts = partRepository.findAll();

        LocalDate date_now = LocalDate.now();
        LocalDateTime dateTime_now = LocalDateTime.now();

        // ---------- monami part 수정 ----------
        for (Part partObj : parts) {
            String part = partObj.getPartName();

            LocalDateTime recent_part_date = productionProcessMapper.getRecentPartDate(part);
            if (recent_part_date != null && recent_part_date.toLocalDate().isBefore(date_now)) {
                int daysBetween = Period.between(recent_part_date.toLocalDate(), date_now).getDays();
                for (int i = 1; i < daysBetween; i++) {
                    int daily_production = productionProcessMapper.getDailyProduction(part, recent_part_date.toLocalDate());

                    Random random = new Random();
                    if (random.nextInt(100) < 5) {  // 5% 확률
                        int change = random.nextInt(10) * 10;  // 0~9 사이의 값을 곱해서 0~90 사이의 값을 생성
                        if (random.nextBoolean()) {
                            daily_production += change;  // 10의 배수를 더함
                        } else {
                            daily_production -= change;  // 10의 배수를 뺌
                        }
                    }

                    LocalDateTime partDate = recent_part_date.plusDays(i);
                    int partDailyProduction = daily_production;
                    int partDailyAmount = daily_production;
                    if (random.nextBoolean()) {
                        partDailyAmount += (((int) (partDailyProduction * 0.2)) + 1);  // 10의 배수를 더함
                    } else {
                        partDailyAmount -= (((int) (partDailyProduction * 0.2)) + 1);  // 10의 배수를 뺌
                    }
                    int partDefective = (((int) (partDailyAmount * 0.05)) + 1);
                    productionProcessMapper.monamiPartInsert(part, partDate, partDefective, partDailyAmount, partDailyProduction);
                }

                int daily_production = productionProcessMapper.getDailyProduction(part, recent_part_date.toLocalDate());

                Random random = new Random();
                if (random.nextInt(100) < 5) {  // 5% 확률
                    int change = random.nextInt(10) * 10;  // 0~9 사이의 값을 곱해서 0~90 사이의 값을 생성
                    if (random.nextBoolean()) {
                        daily_production += change;  // 10의 배수를 더함
                    } else {
                        daily_production -= change;  // 10의 배수를 뺌
                    }
                }

                int partDailyProduction = daily_production;
                int partDailyAmount = ((int) ((partDailyProduction / 1440.0) * (dateTime_now.getMinute() + (dateTime_now.getHour() * 60))));
                int amountError = ((int) (partDailyAmount * 0.2)) + 1;
                if (random.nextBoolean()) {
                    partDailyProduction += amountError;  // 10의 배수를 더함
                } else {
                    partDailyProduction -= amountError;  // 10의 배수를 뺌
                }
                int partDefective = (((int) (partDailyAmount * 0.05)) + 1);
                productionProcessMapper.monamiPartInsert(part, dateTime_now, partDefective, partDailyAmount, partDailyProduction);

                int amount_now = productionProcessMapper.getMonamiAmountItemAmount(part) + partDailyAmount;
                productionProcessMapper.monamiAmountItemModify(part, amount_now);
            } else if (recent_part_date != null && recent_part_date.toLocalDate().isEqual(date_now)) {
                Random random = new Random();
                int minutesBetween = (int) Duration.between(dateTime_now, recent_part_date).toMinutes();

                int partDailyProduction = productionProcessMapper.getDailyProduction(part, recent_part_date.toLocalDate());
                int partDailyAmount_plus = ((int) ((partDailyProduction / 1440) * minutesBetween));
                int amountError = ((int) (partDailyAmount_plus * 0.2)) + 1;
                if (random.nextBoolean()) {
                    partDailyAmount_plus += amountError;  // 10의 배수를 더함
                } else {
                    partDailyAmount_plus -= amountError;  // 10의 배수를 뺌
                }
                int partDailyAmount = productionProcessMapper.getDailyAmount(part, recent_part_date.toLocalDate()) + partDailyAmount_plus;
                int partDefective = (((int) (partDailyAmount_plus * 0.05)) + 1);
                productionProcessMapper.monamiPartModify(part, dateTime_now, partDefective, partDailyAmount, partDailyProduction);

                int amount_now = productionProcessMapper.getMonamiAmountItemAmount(part) + partDailyAmount_plus;
                productionProcessMapper.monamiAmountItemModify(part, amount_now);

            } else if (recent_part_date == null) {
                Random random = new Random();


                int partDailyProduction = 750;
                int partDailyAmount = ((int) ((partDailyProduction / 1440) * dateTime_now.getMinute()));
                int amountError = ((int) (partDailyAmount * 0.2)) + 1;
                if (random.nextBoolean()) {
                    partDailyProduction += amountError;  // 10의 배수를 더함
                } else {
                    partDailyProduction -= amountError;  // 10의 배수를 뺌
                }
                int partDefective = (((int) (partDailyAmount * 0.05)) + 1);
                productionProcessMapper.monamiPartInsert(part, dateTime_now, partDefective, partDailyAmount, partDailyProduction);
            }
        }

        // ---------- monami totalpen 수정 ----------
        LocalDateTime recent_total_date = productionProcessMapper.getRecentTotalpenDate();

        if (recent_total_date != null && recent_total_date.toLocalDate().isBefore(date_now)) {
            int daysBetween = Period.between(recent_total_date.toLocalDate(), date_now).getDays();
            for (int i = 1; i < daysBetween; i++) {
                int daily_production = productionProcessMapper.getDailyProduction("totalpen", recent_total_date.toLocalDate());

                Random random = new Random();
                if (random.nextInt(100) < 5) {  // 5% 확률
                    int change = random.nextInt(10) * 10;
                    if (random.nextBoolean()) {
                        daily_production += change;  // 10의 배수를 더함
                    } else {
                        daily_production -= change;  // 10의 배수를 뺌
                    }
                }

                LocalDateTime totalpenDate = recent_total_date.plusDays(i);
                int totalpenDailyProduction = daily_production;
                int totalpenDailyAmount = daily_production;
                if (random.nextBoolean()) {
                    totalpenDailyAmount += (((int) (totalpenDailyProduction * 0.2)) + 1);  // 10의 배수를 더함
                } else {
                    totalpenDailyAmount -= (((int) (totalpenDailyProduction * 0.2)) + 1);  // 10의 배수를 뺌
                }
                int totalpenDefective = (((int) (totalpenDailyAmount * 0.05)) + 1);
                productionProcessMapper.monamiTotalpenInsert(totalpenDate, totalpenDefective, totalpenDailyAmount, totalpenDailyProduction);
            }

            int daily_production = productionProcessMapper.getDailyProduction("totalpen", recent_total_date.toLocalDate());
            System.out.println(daily_production);
            Random random = new Random();
            if (random.nextInt(100) < 5) {  // 5% 확률
                int change = random.nextInt(10) * 10;  // 0~9 사이의 값을 곱해서 0~90 사이의 값을 생성
                if (random.nextBoolean()) {
                    daily_production += change;  // 10의 배수를 더함
                } else {
                    daily_production -= change;  // 10의 배수를 뺌
                }
            }

            int totalpenDailyProduction = daily_production;
            int totalpenDailyAmount = ((int) ((totalpenDailyProduction / 1440) * dateTime_now.getMinute()));
            int amountError = ((int) (totalpenDailyAmount * 0.2)) + 1;
            if (random.nextBoolean()) {
                totalpenDailyProduction += amountError;  // 10의 배수를 더함
            } else {
                totalpenDailyProduction -= amountError;  // 10의 배수를 뺌
            }
            int totalpenDefective = (((int) (totalpenDailyAmount * 0.05)) + 1);
            productionProcessMapper.monamiTotalpenInsert(dateTime_now, totalpenDefective, totalpenDailyAmount, totalpenDailyProduction);

            int amount_now = productionProcessMapper.getMonamiAmountItemAmount("totalpen") + totalpenDailyAmount;
            productionProcessMapper.monamiAmountItemModify("totalpen", amount_now);
        } else if (recent_total_date != null && recent_total_date.toLocalDate().isEqual(date_now)) {
            Random random = new Random();
            int minutesBetween = (int) Duration.between(dateTime_now, recent_total_date).toMinutes();

            int totalpenDailyProduction = productionProcessMapper.getDailyProduction("totalpen", recent_total_date.toLocalDate());
            int totalpenDailyAmount_plus = ((int) ((totalpenDailyProduction / 1440) * minutesBetween));
            int amountError = ((int) (totalpenDailyAmount_plus * 0.2)) + 1;
            if (random.nextBoolean()) {
                totalpenDailyAmount_plus += amountError;  // 10의 배수를 더함
            } else {
                totalpenDailyAmount_plus -= amountError;  // 10의 배수를 뺌
            }
            int totalpenDefective = (((int) (totalpenDailyAmount_plus * 0.05)) + 1);
            int totalpenDailyAmount = productionProcessMapper.getTotalpenDailyAmount(recent_total_date.toLocalDate()) + totalpenDailyAmount_plus;
            productionProcessMapper.monamiTotalpenModify(dateTime_now, totalpenDefective, totalpenDailyAmount, totalpenDailyProduction);

            int amount_now = productionProcessMapper.getMonamiAmountItemAmount("pen") + totalpenDailyAmount_plus;
            productionProcessMapper.monamiAmountItemModify("pen", amount_now);
        } else if (recent_total_date == null) {
            Random random = new Random();


            int totalpenDailyProduction = 750;
            int totalpenDailyAmount = ((int) ((totalpenDailyProduction / 1440) * (dateTime_now.getMinute() + (dateTime_now.getHour() * 60))));
            int amountError = ((int) (totalpenDailyAmount * 0.2)) + 1;
            if (random.nextBoolean()) {
                totalpenDailyProduction += amountError;  // 10의 배수를 더함
            } else {
                totalpenDailyProduction -= amountError;  // 10의 배수를 뺌
            }
            int totalDefective = (((int) (totalpenDailyAmount * 0.05)) + 1);
            productionProcessMapper.monamiTotalpenInsert(dateTime_now, totalDefective, totalpenDailyAmount, totalpenDailyProduction);
        }

        // ---------- material holdings 수정 ----------
        List<Material> materials = materialRepository.findAll();
        List<ProductComb> productCombs = productCombRepository.findAll();

        for (Material material : materials) {
            LocalDateTime recent_date = productionProcessMapper.getMaterialHoldingsHoldingsDate(material.getMaterialId());
            int recent_quantity = productionProcessMapper.getMaterialHoldingsQuantity(material.getMaterialId());

            if (recent_date != null) {
                LocalDate recentDataDate = recent_date.toLocalDate();

                if (recentDataDate.isBefore(dateTime_now.toLocalDate())) {
                    int daysBetween = (int)(ChronoUnit.DAYS.between(recentDataDate, dateTime_now.toLocalDate()));

                    for (int i = 1; i < daysBetween; i++) {
                        int sum = 0;
                        for (ProductComb productComb : productCombs) {
                            int daily_production = productionProcessMapper.getDailyProduction(productComb.getProduct(), recent_date.plusDays(i).toLocalDate());
                            if (productComb.getMaterialId() == material.getMaterialId()) {
                                sum += productComb.getAmount() * daily_production;
                            }
                        }

                        int quantity = recent_quantity - sum;
                        LocalDateTime holdingsDate = recent_date.plusDays(i);

                        productionProcessMapper.materialHoldingsInsert(MaterialHoldings.builder()
                                .materialId(material.getMaterialId())
                                .quantity(quantity)
                                .holdingsDate(holdingsDate)
                                .build());
                    }
                } else if (recentDataDate.isEqual(dateTime_now.toLocalDate())) {
                    int sum = 0;
                    for (ProductComb productComb : productCombs) {
                        int daily_production = productionProcessMapper.getDailyProduction(productComb.getProduct(), recent_date.plusDays(1).toLocalDate());
                        if (productComb.getMaterialId() == material.getMaterialId()) {
                            sum += productComb.getAmount() * daily_production;
                        }
                    }

                    int quantity = recent_quantity - sum;
                    LocalDateTime holdingsDate = recent_date.plusDays(1);

                    productionProcessMapper.materialHoldingsRecentModifybyId(MaterialHoldings.builder()
                            .materialId(material.getMaterialId())
                            .quantity(quantity)
                            .holdingsDate(holdingsDate)
                            .build());
                }
            } else if (recent_total_date == null) {
                int quantity = 32000000;
                LocalDateTime holdingsDate = dateTime_now;

                productionProcessMapper.materialHoldingsRecentModifybyId(MaterialHoldings.builder()
                        .materialId(material.getMaterialId())
                        .quantity(quantity)
                        .holdingsDate(holdingsDate)
                        .build());
            }
        }
    }
}
