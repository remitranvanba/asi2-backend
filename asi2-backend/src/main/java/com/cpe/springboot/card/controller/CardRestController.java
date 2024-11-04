package com.cpe.springboot.card.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.cpe.springboot.card.service.CardModelService;
import com.shared.CardGenerationPrompt;
import com.shared.CardGenerationResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import com.cpe.springboot.card.model.CardDTO;
import com.cpe.springboot.card.model.CardModel;
import com.cpe.springboot.common.tools.DTOMapper;

//ONLY FOR TEST NEED ALSO TO ALLOW CROOS ORIGIN ON WEB BROWSER SIDE
@CrossOrigin
@RestController

public class CardRestController {

	private static final Logger log = LoggerFactory.getLogger(CardRestController.class);
	private final CardModelService cardModelService;
	
	public CardRestController(CardModelService cardModelService) {
		this.cardModelService=cardModelService;
	}
	
	@RequestMapping(method=RequestMethod.GET, value="/cards")
	private List<CardDTO> getAllCards() {
		List<CardDTO> cLightList=new ArrayList<>();
		for(CardModel c:cardModelService.getAllCardModel()){
			cLightList.add(new CardDTO(c));
		}
		return cLightList;

	}
	
	@RequestMapping(method=RequestMethod.GET, value="/card/{id}")
	private CardDTO getCard(@PathVariable String id) {
		Optional<CardModel> rcard;
		rcard= cardModelService.getCard(Integer.valueOf(id));
		if(rcard.isPresent()) {
			return DTOMapper.fromCardModelToCardDTO(rcard.get());
		}
		throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Card id:"+id+", not found",null);

	}
	
	@RequestMapping(method=RequestMethod.POST,value="/card")
	public ResponseEntity<CardGenerationResponse> addCard(@RequestBody CardGenerationPrompt card) {

		// api calls to generationcard =>
		String uri = "http://localhost:8087/cardgeneration/generate-card";
		RestTemplate restTemplate = new RestTemplate();
		CardGenerationPrompt cardGenerationPrompt = new CardGenerationPrompt(card.getUserId(), card.getPrompt());

		CardGenerationResponse result = restTemplate.postForObject(uri, cardGenerationPrompt, CardGenerationResponse.class);
		// call went well
		log.info(result != null ? result.transactionId : null);
		return ResponseEntity.ok(result);
	}
	
	@RequestMapping(method=RequestMethod.PUT,value="/card/{id}")
	public CardDTO updateCard(@RequestBody CardDTO card,@PathVariable String id) {
		card.setId(Integer.valueOf(id));
		 return cardModelService.updateCard(DTOMapper.fromCardDtoToCardModel(card));
	}
	
	@RequestMapping(method=RequestMethod.DELETE,value="/card/{id}")
	public void deleteUser(@PathVariable String id) {
		cardModelService.deleteCardModel(Integer.valueOf(id));
	}

	
}
