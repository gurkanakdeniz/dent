package com.dent.crawler.service.proxy.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dent.crawler.core.proxy.model.ProxyAccountModel;
import com.dent.crawler.domain.entity.ProxyAccountEntity;
import com.dent.crawler.domain.repository.ProxyAccountRepository;
import com.dent.crawler.service.proxy.IProxyService;
import com.dent.crawler.service.proxy.model.RequestAccount;
import com.dent.crawler.service.proxy.model.RequestAdd;
import com.dent.crawler.service.proxy.model.RequestDelete;
import com.dent.crawler.service.proxy.model.RequestList;
import com.dent.crawler.service.proxy.model.RequestUpdate;
import com.dent.crawler.service.proxy.model.ResponseAccount;
import com.dent.crawler.service.proxy.model.ResponseAdd;
import com.dent.crawler.service.proxy.model.ResponseDelete;
import com.dent.crawler.service.proxy.model.ResponseList;
import com.dent.crawler.service.proxy.model.ResponseUpdate;

@Service
public class ProxyService implements IProxyService {

    @Autowired
    ProxyAccountRepository proxyAccountRepository;

    @Override
    public ResponseAccount getAccount(RequestAccount request) {

        List<ProxyAccountEntity> entities = proxyAccountRepository.findByStatus(Boolean.TRUE);

        if (entities != null && !entities.isEmpty()) {
            ProxyAccountEntity item = entities.get(0);

            ResponseAccount response = new ResponseAccount(
                    new ProxyAccountModel(item.getId(), item.getUsername(), item.getPassword(), item.getPort(),
                            item.getType(), item.getHostname(), item.getLastAccessDate(), item.getStatus()));

            item.setLastAccessDate(new Date());
            proxyAccountRepository.save(item);

            return response;
        }

        return new ResponseAccount(null);
    }

    @Override
    public ResponseAdd add(RequestAdd request) {
        ProxyAccountEntity entity = new ProxyAccountEntity(request.getUsername(), request.getPassword(),
                request.getPort(), request.getType(), request.getHostname());
        proxyAccountRepository.save(entity);

        return new ResponseAdd(entity.getId());
    }

    @Override
    public ResponseList list(RequestList request) {
        List<ProxyAccountModel> auths = new ArrayList<ProxyAccountModel>();

        List<ProxyAccountEntity> entities = proxyAccountRepository.findAll();

        if (entities != null && entities.size() > 0) {
            for (ProxyAccountEntity item : entities) {
                auths.add(new ProxyAccountModel(item.getId(), item.getUsername(), item.getPassword(), item.getPort(),
                        item.getType(), item.getHostname(), item.getLastAccessDate(), item.getStatus()));
            }
        }

        return new ResponseList(auths);
    }

    @Override
    public ResponseDelete delete(RequestDelete request) {
        ResponseDelete response = new ResponseDelete();
        proxyAccountRepository.deleteById(request.getId());
        return response;
    }

    @Override
    public ResponseUpdate update(RequestUpdate request) {
        Optional<ProxyAccountEntity> account = proxyAccountRepository.findById(request.getId());

        if (account.isPresent()) {
            ProxyAccountEntity linkedinAccount = account.get();
            linkedinAccount.setStatus(request.getStatus());
            proxyAccountRepository.save(linkedinAccount);
        }

        return new ResponseUpdate(request.getId());
    }

}
