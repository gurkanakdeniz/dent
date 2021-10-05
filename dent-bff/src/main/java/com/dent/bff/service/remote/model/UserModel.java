package com.dent.bff.service.remote.model;

public class UserModel extends BaseModel {

    private static final long serialVersionUID = -2218889861083109125L;
    
    private String username;
    private ProfileModel profileModel;
    private ConsumerCrawlingModel crawlingModel;

    public UserModel(String username, ConsumerCrawlingModel crawlingModel, ProfileModel profileModel) {
        super();
        this.username = username;
        this.crawlingModel = crawlingModel;
        this.profileModel = profileModel;
    }

    public String getUsername() {
        return username;
    }

    public ConsumerCrawlingModel getCrawlingModel() {
        return crawlingModel;
    }

    public ProfileModel getProfileModel() {
        return profileModel;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setProfileModel(ProfileModel profileModel) {
        this.profileModel = profileModel;
    }

    public void setCrawlingModel(ConsumerCrawlingModel crawlingModel) {
        this.crawlingModel = crawlingModel;
    }

}
