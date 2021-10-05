package com.dent.crawler.service.linkedin.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dent.crawler.core.linkedin.model.AuthenticationModel;
import com.dent.crawler.domain.entity.LinkedinAccountEntity;
import com.dent.crawler.domain.repository.LinkedinAccountRepository;
import com.dent.crawler.service.linkedin.ILinkedinService;
import com.dent.crawler.service.linkedin.model.AccountRequest;
import com.dent.crawler.service.linkedin.model.AccountResponse;
import com.dent.crawler.service.linkedin.model.RequestAdd;
import com.dent.crawler.service.linkedin.model.RequestDelete;
import com.dent.crawler.service.linkedin.model.RequestList;
import com.dent.crawler.service.linkedin.model.RequestUpdate;
import com.dent.crawler.service.linkedin.model.ResponseAdd;
import com.dent.crawler.service.linkedin.model.ResponseDelete;
import com.dent.crawler.service.linkedin.model.ResponseList;
import com.dent.crawler.service.linkedin.model.ResponseUpdate;

@Service
public class LinkedinService implements ILinkedinService {

    @Autowired
    LinkedinAccountRepository linkedinAccountRepository;

    @Override
    public AccountResponse getAccount(AccountRequest request) {

        List<LinkedinAccountEntity> entities = linkedinAccountRepository.findByStatus(Boolean.TRUE);
        if (entities != null && !entities.isEmpty()) {
            LinkedinAccountEntity item = entities.get(0);
            AccountResponse response = new AccountResponse(new AuthenticationModel(item.getEmail(), item.getPassword(),
                    item.getLastAccessDate(), item.getStatus()));

            item.setLastAccessDate(new Date());
            linkedinAccountRepository.save(item);

            return response;
        }

        return new AccountResponse(null);
    }

    @Override
    public ResponseAdd add(RequestAdd request) {
        ResponseAdd response = new ResponseAdd();
        linkedinAccountRepository.save(new LinkedinAccountEntity(request.getEmail(), request.getPassword()));
        return response;
    }

    @Override
    public ResponseList list(RequestList request) {
        List<AuthenticationModel> auths = new ArrayList<AuthenticationModel>();
        List<LinkedinAccountEntity> entities = linkedinAccountRepository.findAll();

        if (entities != null && entities.size() > 0) {
            for (LinkedinAccountEntity item : entities) {
                auths.add(new AuthenticationModel(item.getEmail(), item.getPassword(), item.getLastAccessDate(),
                        item.getStatus()));
            }
        }

        return new ResponseList(auths);
    }

    @Override
    public ResponseDelete delete(RequestDelete request) {
        ResponseDelete response = new ResponseDelete();
        linkedinAccountRepository.deleteById(request.getEmail());
        return response;
    }

    @Override
    public ResponseUpdate update(RequestUpdate request) {
        Optional<LinkedinAccountEntity> account = linkedinAccountRepository.findById(request.getEmail());

        if (account.isPresent()) {
            LinkedinAccountEntity linkedinAccount = account.get();
            linkedinAccount.setStatus(request.getStatus());
            linkedinAccountRepository.save(linkedinAccount);
        }

        return new ResponseUpdate(request.getEmail());
    }

}
