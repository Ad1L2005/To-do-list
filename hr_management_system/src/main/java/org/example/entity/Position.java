package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "positions")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Position {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "positions_id_gen")
    @SequenceGenerator(name = "positions_id_gen", sequenceName = "positions_id_seq", allocationSize = 1)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @Column(name = "salary", nullable = false, precision = 10, scale = 2)
    private BigDecimal salary;
}
