package com.dent.iext.service.user.util;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;

import com.dent.iext.core.iext.model.ConsumerCrawlingModel;
import com.dent.iext.core.iext.model.CrawlingModelDTO;
import com.dent.iext.core.iext.model.CrawlingProcessingModel;
import com.dent.iext.core.iext.model.ProfileModel;
import com.dent.iext.core.iext.model.UserModel;
import com.dent.iext.domain.entity.CrawlingEntity;
import com.dent.iext.domain.entity.InfoEntity;
import com.dent.iext.domain.entity.UserEntity;
import com.dent.iext.domain.entity.UserInfoEntity;

public class Mapper {

    public static UserInfoEntity map(ProfileModel profileModel, ConsumerCrawlingModel crawlingModel) {
        return new UserInfoEntity(new ObjectId().toString(), map(profileModel), map(crawlingModel));
    }

    public static CrawlingEntity map(ConsumerCrawlingModel crawlingModel) {
        if (crawlingModel == null) {
            return null;
        }

        return new CrawlingEntity(crawlingModel.getId(), crawlingModel.getJobId(), crawlingModel.getCrawlingDate(),
                crawlingModel.getCrawlingModel(), crawlingModel.getCrawlingData());
    }

    public static InfoEntity map(ProfileModel profileModel) {
        if (profileModel == null) {
            return null;
        }

        return new InfoEntity(new ObjectId().toString(), profileModel.getInfo(), new Date());
    }

    public static List<UserInfoEntity> map(List<UserInfoEntity> info, UserInfoEntity userInfoEntity) {
        List<UserInfoEntity> response = new ArrayList<UserInfoEntity>();

        if (userInfoEntity != null) {
            response.add(userInfoEntity);
        }

        if (info != null) {
            response.addAll(info);
        }

        return response;
    }

    public static UserModel map(UserEntity item) {
        if (item == null) {
            return null;
        }

        UserModel response = new UserModel(item.getUsername(), null, null);
        response = map(response, item.getUserInfoEntity());

        return response;
    }

    public static UserModel map(UserModel userModel, UserInfoEntity userInfoEntity) {
        if (userInfoEntity == null) {
            return userModel;
        }

        userModel.setCrawlingModel(map(userInfoEntity.getCrawlingEntity()));
        userModel.setProfileModel(map(userInfoEntity.getInfoEntity()));

        return userModel;
    }

    public static ProfileModel map(InfoEntity infoEntity) {
        if (infoEntity == null) {
            return null;
        }

        return new ProfileModel(infoEntity.getId(), infoEntity.getInfo(), infoEntity.getInfoDate());
    }

    public static ConsumerCrawlingModel map(CrawlingEntity crawlingEntity) {
        if (crawlingEntity == null) {
            return null;
        }

        return new ConsumerCrawlingModel(crawlingEntity.getId(), crawlingEntity.getJobId(),
                (CrawlingProcessingModel) crawlingEntity.getCrawlingData(), crawlingEntity.getCrawlingDate(),
                (CrawlingModelDTO) crawlingEntity.getCrawlingModel());
    }

    public static UserInfoEntity map(UserModel userModel) {
        if (userModel == null) {
            return null;
        }

        return map(userModel.getProfileModel(), userModel.getCrawlingModel());
    }

}
