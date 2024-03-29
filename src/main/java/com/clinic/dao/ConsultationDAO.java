package com.clinic.dao;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.clinic.entity.Consultation;

/**
 * Interface de l'accès aux données (DAO) pour l'entité Consultation.
 */
public interface ConsultationDAO extends JpaRepository<Consultation, Long> {

    /**
     * Calcule le revenu total de toutes les consultations.
     * @return Le revenu total.
     */
    @Query("SELECT COALESCE(SUM(c.prix), 0) FROM Consultation c")
    BigDecimal calculateTotalIncome();

    /**
     * Calcule le revenu mensuel des consultations pour une année et un mois spécifiés.
     * @param year  L'année.
     * @param month Le mois.
     * @return Le revenu mensuel.
     */
    @Query("SELECT COALESCE(SUM(c.prix), 0) FROM Consultation c WHERE FUNCTION('YEAR', c.dateCreation) = :year AND FUNCTION('MONTH', c.dateCreation) = :month")
    BigDecimal calculateMonthlyIncome(@Param("year") int year, @Param("month") int month);

    /**
     * Calcule le revenu mensuel de toutes les consultations, groupé par année et mois.
     * @return La liste des revenus mensuels.
     */
    @Query(value = "SELECT MONTH(c.date_creation) as `month`, YEAR(c.date_creation) as `year`, COALESCE(SUM(c.prix), 0) as total FROM consultation c GROUP BY `year`, `month`", nativeQuery = true)
    List<Object[]> calculateMonthlyIncome();
}
