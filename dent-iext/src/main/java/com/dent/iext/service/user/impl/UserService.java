package com.dent.iext.service.user.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.domain.entity.CrawlingEntity;
import com.dent.iext.domain.entity.UserEntity;
import com.dent.iext.domain.entity.UserInfoEntity;
import com.dent.iext.domain.repository.UserRepository;
import com.dent.iext.service.user.IUserService;
import com.dent.iext.service.user.model.RequestAdd;
import com.dent.iext.service.user.model.RequestDelete;
import com.dent.iext.service.user.model.RequestInfo;
import com.dent.iext.service.user.model.RequestInfoDelete;
import com.dent.iext.service.user.model.RequestList;
import com.dent.iext.service.user.model.RequestUpdate;
import com.dent.iext.service.user.model.ResponseAdd;
import com.dent.iext.service.user.model.ResponseDelete;
import com.dent.iext.service.user.model.ResponseInfo;
import com.dent.iext.service.user.model.ResponseInfoDelete;
import com.dent.iext.service.user.model.ResponseList;
import com.dent.iext.service.user.model.ResponseUpdate;
import com.dent.iext.service.user.util.Mapper;

@Service
public class UserService implements IUserService {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseAdd add(RequestAdd request) {
        UserModel userModel = request.getUserModel();

        UserEntity entity = null;
        Optional<UserEntity> findById = userRepository.findById(userModel.getUsername());
        if (findById.isPresent()) {
            entity = findById.get();
        } else {
            entity = new UserEntity(userModel.getUsername());
        }

        UserInfoEntity userInfoEntity = Mapper.map(userModel);
        entity.setUserInfoEntity(userInfoEntity);
        entity.setInfo(Mapper.map(entity.getInfo(), userInfoEntity));
        entity.setUpdateDate(new Date());

        userRepository.save(entity);
        return new ResponseAdd(userModel.getUsername());
    }

    @Override
    public ResponseList list(RequestList request) {
        List<UserModel> users = new ArrayList<UserModel>();

        if (request.getId() != null) {
            Optional<UserEntity> entity = userRepository.findById(request.getId());
            if (entity != null && entity.isPresent()) {
                users.add(Mapper.map(entity.get()));
            }
        } else {
            List<UserEntity> entities = userRepository.findAll(Sort.by(Direction.DESC, "updateDate"));
            if (entities != null && !entities.isEmpty()) {
                for (UserEntity item : entities) {
                    users.add(Mapper.map(item));
                }
            }
        }

        return new ResponseList(users);
    }

    @Override
    public ResponseDelete delete(RequestDelete request) {
        userRepository.deleteById(request.getUsername());
        return new ResponseDelete(request.getUsername());
    }

    @Override
    public ResponseUpdate update(RequestUpdate request) {
        UserModel userModel = request.getUserModel();

        Optional<UserEntity> findById = userRepository.findById(userModel.getUsername());
        if (findById.isPresent()) {
            UserEntity entity = findById.get();

            UserInfoEntity userInfoEntity = Mapper.map(userModel);
            entity.setUserInfoEntity(userInfoEntity);
            entity.setInfo(Mapper.map(entity.getInfo(), userInfoEntity));
            entity.setUpdateDate(new Date());

            userRepository.save(entity);

            return new ResponseUpdate(userModel.getUsername());
        }

        return new ResponseUpdate("");
    }

    @Override
    public ResponseInfo info(RequestInfo request) {
        Optional<UserEntity> entity = userRepository.findById(request.getProfileId());
        if (entity != null && entity.isPresent()) {
            UserEntity user = entity.get();
            List<UserInfoEntity> info = user.getInfo();
            if (info != null) {
                for (UserInfoEntity infoItem : info) {
                    CrawlingEntity crawlingEntity = infoItem.getCrawlingEntity();
                    if (crawlingEntity != null && request.getCrawlingId().equals(crawlingEntity.getId())) {
                        return new ResponseInfo(Mapper.map(new UserModel(user.getUsername(), null, null), infoItem));
                    }
                }
            }
        }

        return new ResponseInfo(null);
    }

    @Override
    public ResponseInfoDelete infoDelete(RequestInfoDelete request) {
        Optional<UserEntity> entity = userRepository.findById(request.getProfileId());
        if (entity != null && entity.isPresent()) {
            UserEntity item = null;

            UserEntity user = entity.get();
            List<UserInfoEntity> info = user.getInfo();
            if (info != null) {
                for (Iterator<UserInfoEntity> iter = info.listIterator(); iter.hasNext();) {
                    UserInfoEntity infoItem = iter.next();
                    CrawlingEntity crawlingEntity = infoItem.getCrawlingEntity();

                    if (crawlingEntity != null && request.getCrawlingId().equals(crawlingEntity.getId())) {
                        if (request.isDeleteInfo()) {
                            iter.remove();
                        } else {
                            infoItem.setCrawlingEntity(null);
                        }

                        if (user.getUserInfoEntity() != null && user.getUserInfoEntity().getCrawlingEntity() != null
                                && user.getUserInfoEntity().getCrawlingEntity().getId()
                                        .equals(request.getCrawlingId())) {
                            if (request.isDeleteInfo()) {
                                user.setUserInfoEntity(null);
                            } else {
                                user.getUserInfoEntity().setCrawlingEntity(null);
                            }
                        }

                        item = user;
                        break;
                    }
                }

                if (item != null) {
                    userRepository.save(item);
                }
            }
        }

        return new ResponseInfoDelete(request.getCrawlingId());
    }

}
