package com.colendi.onlinePaymentProcess.api;

import com.colendi.onlinePaymentProcess.dto.*;
import com.colendi.onlinePaymentProcess.request.CardRequest;
import com.colendi.onlinePaymentProcess.request.GPARequest;
import com.colendi.onlinePaymentProcess.request.LimitScopeRequest;
import com.colendi.onlinePaymentProcess.request.PaymentRequest;
import com.colendi.onlinePaymentProcess.response.CardResponse;
import com.colendi.onlinePaymentProcess.response.CardTransactionResponse;
import com.colendi.onlinePaymentProcess.response.UserResponse;
import com.colendi.onlinePaymentProcess.service.CardService;
import com.colendi.onlinePaymentProcess.service.GPAService;
import com.colendi.onlinePaymentProcess.service.MerchantService;
import com.colendi.onlinePaymentProcess.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OnlinePaymentProcessController implements OnlinePaymentProcessApi {

    @Autowired
    private CardService cardService;
    @Autowired
    private UserService userService;
    @Autowired
    private MerchantService merchantService;
    @Autowired
    private GPAService gpaService;

    private final ModelMapper mapper = new ModelMapper();

    public CardResponse getCardById(Long cardId) throws Exception {
        CardDTO cardDTO = cardService.getCardById(cardId);
        return mapper.map(cardDTO, CardResponse.class);
    }

    @Override
    public List<CardResponse> getCardsByUserId(Long userId) {
        List<CardDTO> cardDTOList = userService.getCardsByUserId(userId);
        return cardDTOList.stream().map(cardDTO -> mapper.map(cardDTO, CardResponse.class))
                .collect(Collectors.toList());
    }

    @Override
    public UserResponse createCard(Long userId, CardRequest cardRequest) throws Exception {
        CardDTO cardDTO = mapper.map(cardRequest, CardDTO.class);
        List<MerchantDTO> merchantDTOList = merchantService.getMerchantList(cardRequest.getMerchantIds());
        cardDTO.setMerchants(merchantDTOList);
        return mapper.map(userService.createCard(userId, cardDTO), UserResponse.class);
    }

    @Override
    public UserResponse reissueCard(Long userId, Long cardId) throws Exception {
        return mapper.map(userService.reissueCard(userId, cardId), UserResponse.class);
    }

    @Override
    public CardResponse cancelCard(Long cardId) throws Exception {
        return mapper.map(cardService.cancelCard(cardId), CardResponse.class);
    }

    @Override
    public CardResponse addLimitScope(LimitScopeRequest limitScopeRequest) throws Exception {
        CardDTO cardDTO = cardService.addLimitScope(mapper.map(limitScopeRequest, LimitScopeDTO.class));
        return mapper.map(cardDTO, CardResponse.class);
    }

    @Override
    public UserResponse addBalanceToUserGPA(Long userId, GPARequest gpaRequest) throws Exception {
        UserDTO userDTO = gpaService.addBalance(userId, mapper.map(gpaRequest, GPADTO.class));
        return mapper.map(userDTO, UserResponse.class);
    }

    @Override
    public CardTransactionResponse spendFromCard(Long userId, PaymentRequest paymentRequest) throws Exception {
        CardTransactionDTO cardTransactionDTO = userService
                .spendFromCard(userId, mapper.map(paymentRequest, PaymentDTO.class));
        return mapper.map(cardTransactionDTO, CardTransactionResponse.class);
    }
}
