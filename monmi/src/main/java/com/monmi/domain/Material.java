package com.monmi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Builder
@Table(name="monami_material")
public class Material {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="material_id")
    private int materialId;
    @Column(name="material_name", nullable = false)
    private String materialName;

    @Builder
    public Material(int materialId, String materialName) {
        this.materialId = materialId;
        this.materialName = materialName;
    }
}
