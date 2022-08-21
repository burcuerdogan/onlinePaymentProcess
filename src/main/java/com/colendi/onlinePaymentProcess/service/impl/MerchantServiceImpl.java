package com.colendi.onlinePaymentProcess.service.impl;

import com.colendi.onlinePaymentProcess.dto.MerchantDTO;
import com.colendi.onlinePaymentProcess.entity.GPA;
import com.colendi.onlinePaymentProcess.entity.Merchant;
import com.colendi.onlinePaymentProcess.repository.MerchantRepository;
import com.colendi.onlinePaymentProcess.service.MerchantService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MerchantServiceImpl implements MerchantService {

    @Autowired
    private MerchantRepository merchantRepository;

    private final ModelMapper mapper = new ModelMapper();

    @Override
    public List<MerchantDTO> getMerchantList(List<Long> merchantIds) {
        List<Merchant> merchants = (List<Merchant>) merchantRepository.findAllById(merchantIds);
        return merchants.stream().map(value -> mapper.map(value, MerchantDTO.class)).collect(Collectors.toList());
    }

    @Override
    public MerchantDTO getById(Long merchantId) throws Exception {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if (merchant.isPresent()) {
            return mapper.map(merchant.get(), MerchantDTO.class);
        } else {
            throw new Exception("The merchant could not be found.");
        }
    }

    @Override
    @Transactional
    public MerchantDTO loadBalance(Long merchantId, Double amount) throws Exception {
        Optional<Merchant> merchant = merchantRepository.findById(merchantId);
        if(merchant.isPresent()){
            GPA merchantGPA = merchant.get().getGpa();
            merchantGPA.setBalance(merchantGPA.getBalance() + amount);

            return mapper.map(merchantRepository.save(merchant.get()), MerchantDTO.class);
        }else {
            throw new Exception("The merchant could not be found.");
        }
    }
}
