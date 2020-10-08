package br.com.transmissionadmin.Model.repository;

import br.com.transmissionadmin.Model.Portfolio;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PortfolioRepository extends JpaRepository<Portfolio, Integer> {
}
