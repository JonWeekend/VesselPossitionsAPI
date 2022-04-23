package com.trackwell.test.vessel_positions_api;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface VesselPositionRepository extends JpaRepository<VesselPosition, Long>{
	
	List<VesselPosition> findByVesselName(String vesselName);
}
