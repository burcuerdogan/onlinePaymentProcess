package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.CardTransactionDTO;
import com.colendi.onlinePaymentProcess.entity.CardTransaction;
import com.colendi.onlinePaymentProcess.repository.CardTransactionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

class CardTransactionServiceImplTest {

    @InjectMocks
    private CardTransactionServiceImpl cardTransactionService;

    @Mock
    private CardTransactionRepository cardTransactionRepository;

    @Mock
    private ModelMapper mapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void test_createCardTransaction_shouldReturnCardTransaction_whenUserActs() {
        CardTransactionDTO cardTransactionDTO = new CardTransactionDTO();
        CardTransaction cardTransaction = new CardTransaction();
        cardTransactionDTO.setCardId(1L);
        cardTransactionDTO.setUserId(1L);
        cardTransactionDTO.setMerchantId(1L);
        cardTransactionDTO.setAmount(100.0);

        cardTransaction.setCardId(cardTransactionDTO.getCardId());
        cardTransaction.setUserId(cardTransactionDTO.getUserId());
        cardTransaction.setMerchantId(cardTransactionDTO.getMerchantId());
        cardTransaction.setAmount(cardTransactionDTO.getAmount());

        when(mapper.map(cardTransactionDTO, CardTransaction.class)).thenReturn(cardTransaction);
        cardTransaction.setId(1L);
        when(cardTransactionRepository.save(cardTransaction)).thenReturn(cardTransaction);

        assertEquals(cardTransaction.getCardId(),
                cardTransactionService.createCardTransaction(cardTransactionDTO).getCardId());
    }
}