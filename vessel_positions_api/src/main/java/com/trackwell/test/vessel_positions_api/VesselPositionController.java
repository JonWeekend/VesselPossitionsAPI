package com.trackwell.test.vessel_positions_api;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
public class VesselPositionController {

	private final VesselPositionRepository repository;

	VesselPositionController(VesselPositionRepository repository) {

		this.repository = repository;
	}

	@GetMapping("/vessel-positions")
	List<VesselPosition> all() {

		return repository.findAll();
	}

	@GetMapping("/vessel-positions/{vesselName}")
	List<VesselPosition> allByName(@PathVariable String vesselName) {

		return repository.findByVesselName(vesselName);
	}

	@PostMapping("/vessel-positions")
	@JsonView(Views.VesselPositionResponseView.class)
	VesselPosition newVesselPosition(@RequestBody VesselPosition newVesselPosition) {

		newVesselPosition.getPosition().setReceivedDate();
		return repository.save(newVesselPosition);
	}
}