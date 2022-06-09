package com.toyvalley.services;

import com.toyvalley.models.data.toy.ToyResponse;
import com.toyvalley.models.data.toy.ToyResponseExchange;
import com.toyvalley.models.data.toyExchange.ExchangeRequestsResponse;
import com.toyvalley.models.data.toyExchange.ToyExchangeRequest;
import com.toyvalley.models.data.toyExchange.ToyExchangeResponse;
import com.toyvalley.models.entities.ExchangeRequest;
import com.toyvalley.models.entities.Toy;
import com.toyvalley.repositories.ToyExchangeRepository;
import com.toyvalley.repositories.ToyRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ToyExchangeService {

  private final ToyExchangeRepository toyExchangeRepository;
  private final ToyRepository toyRepository;

  public ToyExchangeService(ToyExchangeRepository toyExchangeRepository, ToyRepository toyRepository) {
    this.toyExchangeRepository = toyExchangeRepository;
    this.toyRepository = toyRepository;
  }

  public ToyExchangeResponse createToyExchange(ToyExchangeRequest toyExchangeRequest) {
    Optional<Toy> toyOffered = toyRepository.findById(toyExchangeRequest.getToyOffered());
    Optional<Toy> toyRequested = toyRepository.findById(toyExchangeRequest.getToyRequested());

    if (toyOffered.isPresent() && toyRequested.isPresent()) {
      ExchangeRequest requestEntity = new ExchangeRequest(toyRequested.get(), toyOffered.get(), toyExchangeRequest.getMessage());
      ExchangeRequest responseEntity = toyExchangeRepository.save(requestEntity);

      return new ToyExchangeResponse(responseEntity.getToy_offered().getId(), responseEntity.getToy_requested().getId());
    }

    throw new RuntimeException("Exchange was not created.");
  }

    public List<ExchangeRequestsResponse> getUserExchangeRequests(long userId) {
      ArrayList<ExchangeRequestsResponse> responseList = new ArrayList<>();
      List<ExchangeRequest> requestsList = toyExchangeRepository.getUserExchangeRequests(userId);
      for (ExchangeRequest exchangeRequest : requestsList) {
        Toy toyOffered = toyRepository.findAllById(exchangeRequest.getToy_offered().getId());
        Toy toyRequested = toyRepository.findAllById(exchangeRequest.getToy_requested().getId());

        ExchangeRequestsResponse exchangeRequestsResponse = new ExchangeRequestsResponse(
          new ToyResponseExchange(exchangeRequest.getToy_offered().getId(), toyOffered.getName()),
          new ToyResponseExchange(exchangeRequest.getToy_requested().getId(), toyRequested.getName()),
          exchangeRequest.isActive(),
          exchangeRequest.getMessage());

        responseList.add(exchangeRequestsResponse);
      }
      return responseList;
    }
}

