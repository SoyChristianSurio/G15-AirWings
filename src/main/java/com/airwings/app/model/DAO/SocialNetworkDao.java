package com.airwings.app.model.DAO;

import org.springframework.data.jpa.repository.JpaRepository;

import com.airwings.app.model.entity.SocialNetwork;

public interface SocialNetworkDao extends JpaRepository<SocialNetwork, Long> {

}
