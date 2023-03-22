package ca.mcgill.ecse321.parkinglotbackend.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.parkinglotbackend.model.Garage;

import ca.mcgill.ecse321.parkinglotbackend.dao.GarageRepository;


@Service
public class GarageCreationService {

	@Autowired
	GarageRepository garageRepository;

	@Transactional
	public Garage createGarageService(int garageNumber) {
		// Create the garage by adding all the necessary components
		Garage garage = new Garage();
		garage.setGarageNumber(garageNumber);
		garageRepository.save(garage);
		return garage;
	}

	@Transactional
	public Garage getGarageService(long garageID) {
		// Fetch the garage we want through the id in the db
		Garage garage = garageRepository.findGarageByGarageID(garageID);
		return garage;
	}

	@Transactional
	public List<Garage> getAllGarageService() {
		return toList(garageRepository.findAll());
	}

	private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}