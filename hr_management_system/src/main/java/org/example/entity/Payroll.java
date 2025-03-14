package org.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "payroll")
public class Payroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_id", nullable = false, unique = true)
    private Employee employee;

    private BigDecimal baseSalary;
    private BigDecimal bonuses = BigDecimal.ZERO;
    private BigDecimal deductions = BigDecimal.ZERO;

    @Transient
    public BigDecimal getTotalSalary() {
        return baseSalary.add(bonuses).subtract(deductions);
    }

    public void setSalary(BigDecimal bigDecimal) {
    }

    public void setPaymentDate(LocalDate now) {
    }
}
