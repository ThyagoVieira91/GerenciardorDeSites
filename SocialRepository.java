package br.com.transmissionadmin.Model.repository;

import br.com.transmissionadmin.Model.Social;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface SocialRepository extends JpaRepository<Social, Integer> {

}
