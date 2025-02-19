package com.monmi.domain;

import lombok.*;

import javax.persistence.*;

@Entity
@ToString
@Getter
@NoArgsConstructor
@Builder
@Table(name="monami_part")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="part_id")
    private Long partId;
    @Column(name="part_name", nullable = false)
    private String partName;

    @Builder
    public Part(Long partId, String partName) {
        this.partId = partId;
        this.partName = partName;
    }
}
