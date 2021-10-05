package com.dent.crawler.core.crawling.core;

import static com.dent.crawler.core.crawling.util.NavigateUtil.scroll;
import static com.dent.crawler.core.crawling.util.NavigateUtil.zoomIn;
import static com.dent.crawler.core.crawling.util.NavigateUtil.zoomOut;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dent.crawler.common.exception.CrawlingFilterException;
import com.dent.crawler.core.crawling.model.CrawlingModel;
import com.dent.crawler.core.crawling.model.FilterType;
import com.dent.crawler.core.crawling.model.Mode;
import com.dent.crawler.core.linkedin.core.WebUnit;
import com.dent.crawler.infrastructure.common.util.JsonUtil;
import com.dent.crawler.infrastructure.common.util.UrlUtil;

public class CrawlingProcessing {

    private final Logger logger = LoggerFactory.getLogger(CrawlingProcessing.class);

    private WebUnit webUnit;
    private HashMap<String, CrawlingProcessingModel> processingResponse;

    public CrawlingProcessing init() {
        return this;
    }

    public HashMap<String, CrawlingProcessingModel> processing(Crawling crawling) {
        this.processingResponse = new HashMap<String, CrawlingProcessingModel>();
        this.webUnit = crawling.getWebUnit();

        try {
            CrawlingModel model = crawling.getModel();
            if (this.webUnit == null) {
                logger.error("--- web unit null : {}---", model.getProfileId());
                return this.processingResponse;
            }

            switch (model.getMode()) {
                case CORPORATE: {
                    corporate(model);
                    break;
                }
                case PROFILE: {
                    profileProcessing(model);
                    break;
                }
                case RELATED_PROFILE: {
                    relatedProfile(model);
                    break;
                }
                default: {
                    break;
                }
            }
        } catch (Throwable e) {
            logger.error("--- processing ---", e);
        }

        destroy();
        return this.processingResponse;
    }

    private void destroy() {
        try {
            this.webUnit.quit();
        } catch (Throwable e) {
            logger.error("--- destroy ---");
        }
    }

    private void profileProcessing(CrawlingModel model) {
        String profileId = model.getProfileId();
        this.webUnit.navigate().to(UrlUtil.contact(profileId));

        try {
            // TODO:GA: failover

            contactInfo(profileId, model.getFilterType(), model.getFilter());
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            personalInfo(profileId, model.getFilterType(), model.getFilter());
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            socialInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            aboutInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            experienceInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            educationInfo(profileId, model.getFilterType(), model.getFilter());
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            certificationInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            skillsInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            referenceInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            accomplishmentInfo(profileId);
            zoomIn(this.webUnit);
            scroll(this.webUnit);
            zoomOut(this.webUnit);

            projectsInfo(profileId);

            interestsInfo(profileId);

            CrawlingProcessingModel profile = this.processingResponse.get(profileId);
            if (profile != null) {
                if (!checkData(profile)) {
                    this.processingResponse.remove(profileId);
                }
            }
        } catch (CrawlingFilterException e) {
            this.processingResponse.remove(profileId);
            logger.info("--- ignore profile {}-{}-{} ---", e.getProfileId(), e.getFilterType(), e.getFilter());
        }
    }

    private void corporate(CrawlingModel model) {
        this.webUnit.navigate().to(UrlUtil.people(model.getProfileId()));
        scroll(this.webUnit, Integer.MAX_VALUE);

        try {
            List<WebElement> findElementsByXPath = this.webUnit.findElementsByXPath(
                    "//div//ul[contains(@class, '__profile-list')]//li//section//div[contains(@class, 'profile-info')]//a[contains(@href, '/in/')]");

            if (findElementsByXPath != null && !findElementsByXPath.isEmpty()) {
                HashSet<String> profiles = new HashSet<String>();

                String profile = "";
                for (WebElement item : findElementsByXPath) {
                    profile = "";
                    try {
                        profile = item.getAttribute("href");
                        profiles.add(
                                UrlUtil.decode(item.getAttribute("href").split("/in/")[1].replaceAll("\\/+$", "")));
                    } catch (Exception e) {
                        logger.error("--- corporate find people " + profile + " ---", e);
                    }
                }

                logger.info("--- corporate find people ---" + JsonUtil.toJsonPretty(profiles));

                for (String profileId : profiles) {
                    try {
                        CrawlingModel cloneProfileModel = model.clone();
                        cloneProfileModel.setMode(Mode.PROFILE);
                        cloneProfileModel.setProfileId(profileId);

                        this.profileProcessing(cloneProfileModel);
                    } catch (Exception e) {
                        logger.error("--- corporate profile " + profileId + "---", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("--- corporate " + model.getProfileId() + " ---", e);
        }
    }

    private void relatedProfile(CrawlingModel model) {
        try {
            CrawlingModel cloneModel = model.clone();
            cloneModel.setMode(Mode.PROFILE);

            this.profileProcessing(cloneModel);

            seeMore("//div//section[contains(@class, 'browsemap-section')]//button[contains(@class, 'section__show')]",
                    "//div//section[contains(@class, 'browsemap-section')]//button[contains(@class, 'section__show')]//li-icon[contains(@type, 'down')]");

            List<WebElement> findElementsByXPath = this.webUnit.findElementsByXPath(
                    "//div//section[contains(@class, 'browsemap-section')]//li//a[contains(@href, '/in/')]");

            if (findElementsByXPath != null && !findElementsByXPath.isEmpty()) {
                HashSet<String> profiles = new HashSet<String>();

                String profile = "";
                for (WebElement item : findElementsByXPath) {
                    profile = "";
                    try {
                        profile = item.getAttribute("href");
                        profiles.add(
                                UrlUtil.decode(item.getAttribute("href").split("/in/")[1].replaceAll("\\/+$", "")));
                    } catch (Exception e) {
                        logger.error("--- related find people " + profile + " ---", e);
                    }
                }

                logger.info("--- related find people ---" + JsonUtil.toJsonPretty(profiles));

                for (String profileId : profiles) {
                    try {
                        CrawlingModel cloneProfileModel = model.clone();
                        cloneProfileModel.setMode(Mode.PROFILE);
                        cloneProfileModel.setProfileId(profileId);

                        this.profileProcessing(cloneProfileModel);
                    } catch (Exception e) {
                        logger.error("--- related profile " + profileId + "---", e);
                    }
                }
            }
        } catch (Exception e) {
            logger.error("--- related profile " + model.getProfileId() + "---", e);
        }
    }

    private void personalInfo(String profileId, FilterType filterType, String filter) throws CrawlingFilterException {
        String info = "";

        try {
            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("(//div[@class='display-flex']/following-sibling::div)[1]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- personal info ---", e);
        }
        
        try {
            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//div[contains(@class,'photo')]//div[contains(@class, 'presence-entity')]");

            info += findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- personal info ---", e);
        }

        if (filterType != null) {
            if (StringUtils.equalsAny(filter, FilterType.COUNTRY.getCode(), FilterType.TITLE.getCode(),
                    FilterType.WORKPLACE.getCode())) {
                filter(filterType, filter, profileId, info);
            }
        }

        getProfile(profileId).getPersonalInfo().setInfo(info, new Date());
    }

    private void aboutInfo(String profileId) {
        String info = "";

        try {
            WebElement element = webUnit.findElementByXPath(
                    "(//section[contains(@class, 'pv-about-section')]//a[contains(@class, '__more')])[1]");
            webUnit.executeScript("arguments[0].click();", element);

            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("(//section[contains(@class, 'pv-about-section')]//p)[1]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- personal info ---", e);
        }

        getProfile(profileId).getAboutInfo().setInfo(info, new Date());
    }

    private void socialInfo(String profileId) {
        String info = "";

        try {
            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("(//div[@class='display-flex']/following-sibling::div)[1]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- personal info ---", e);
        }

        getProfile(profileId).getSocialInfo().setInfo(info, new Date());
    }

    private void contactInfo(String profileId, FilterType filterType, String filter) throws CrawlingFilterException {
        String info = "";

        try {
            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//div[contains(@class, 'artdeco-modal__content')]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- contact info ---", e);
        }

        try {
            WebElement findElementByXPath = this.webUnit.findElementByXPath(
                    "//div[contains(@role, 'dialog')]//button[contains(@class, 'dismiss artdeco-button')]");

            webUnit.executeScript("arguments[0].click();", findElementByXPath);
        } catch (Exception e) {
            logger.error("--- contact info ---", e);
        }

        getProfile(profileId).getContactInfo().setInfo(info, new Date());
    }

    private void experienceInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@id, 'experience-section')]//button[contains(@class,'__see-more-inline')]");

            WebElement findElementByXPath = this.webUnit.findElement(By.id("experience-section"));

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- experience info ---", e);
        }

        getProfile(profileId).getExperienceInfo().setInfo(info, new Date());
    }

    private void educationInfo(String profileId, FilterType filterType, String filter) throws CrawlingFilterException {
        String info = "";

        try {
            WebElement findElementByXPath = this.webUnit.findElement(By.id("education-section"));

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- experience info ---", e);
        }

        if (filterType != null) {
            if (StringUtils.equalsAny(filter, FilterType.EDUCATION_LEVEL.getCode(),
                    FilterType.EDUCATION_SECTION.getCode(), FilterType.SCHOOL.getCode())) {
                filter(filterType, filter, profileId, info);
            }
        }

        getProfile(profileId).getEducationInfo().setInfo(info, new Date());
    }

    private void certificationInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@id, 'certifications-section')]//button[contains(@class,'__see-more-inline')]");

            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//section[contains(@id, 'certifications-section')]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- certificationInfo info ---", e);
        }

        getProfile(profileId).getCertificationInfo().setInfo(info, new Date());
    }

    private void skillsInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@class, 'skill-categories-section')]//button[contains(@class,'artdeco-button')]");

            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//section[contains(@class, 'skill-categories-section')]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- skills info ---", e);
        }

        getProfile(profileId).getSkillsInfo().setInfo(info, new Date());
    }

    private void referenceInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@class, 'ecommendations-section')]//button[contains(@class,'__see-more-inline')]");

            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//section[contains(@class, 'ecommendations-section')]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- reference info ---", e);
        }

        getProfile(profileId).getReferenceInfo().setInfo(info, new Date());
    }

    private void accomplishmentInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@class, 'accomplishments-section')]//button[contains(@class,'__see-more-inline')]");

            WebElement findElementByXPath = this.webUnit
                    .findElementByXPath("//section[contains(@class, 'accomplishments-section')]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- accomplishment info ---", e);
        }

        getProfile(profileId).getAccomplishmentInfo().setInfo(info, new Date());
    }

    private void projectsInfo(String profileId) {
        String info = "";

        try {
            seeMore("//section[contains(@class, 'accomplishments-section')]//button[contains(@aria-controls, 'projects')]");
            seeMore("//section[contains(@class, 'accomplishments-section')]//div[contains(@id, 'projects-expandable-content')]//button[contains(@aria-expanded, 'false')]");

            WebElement findElementByXPath = this.webUnit.findElementByXPath(
                    "(//section[contains(@class, 'pv-accomplishments-block projects pv-accomplishments-block')])[1]");

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- projects info ---", e);
        }

        getProfile(profileId).getProjectsInfo().setInfo(info, new Date());
    }

    private void interestsInfo(String profileId) {
        String info = "";

        try {
            this.webUnit.navigate().to(UrlUtil.interests(profileId));
            String elementPath = "//div[contains(@class, 'artdeco-modal')]//div[contains(@class, 'pv-interests-list')]";

            seeMoreScroll(elementPath);

            WebElement findElementByXPath = this.webUnit.findElementByXPath(elementPath);

            info = findElementByXPath.getAttribute("innerHTML");
        } catch (Exception e) {
            logger.error("--- interests info ---", e);
        }

        getProfile(profileId).getInterestsInfo().setInfo(info, new Date());
    }

    private CrawlingProcessingModel getProfile(String profileId) {
        CrawlingProcessingModel profile = this.processingResponse.get(profileId);
        if (profile == null) {
            profile = new CrawlingProcessingModel();
        }

        this.processingResponse.put(profileId, profile);
        return this.processingResponse.get(profileId);
    }

    private void seeMoreScroll(String xpath) {
        boolean seeMore = true;
        int count = 9;
        do {
            String info = "";
            count--;
            try {
                WebElement findElementByXPath = this.webUnit.findElementByXPath(xpath);
                info = findElementByXPath.getAttribute("innerHTML");
                this.webUnit.executeScript("arguments[0].scrollTop = arguments[0].scrollHeight", findElementByXPath);

                try {
                    Thread.sleep(1000);
                } catch (Exception e) {
                    // ignore
                }

                webUnit.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
                findElementByXPath = this.webUnit.findElementByXPath(xpath);

                seeMore = !info.equals(findElementByXPath.getAttribute("innerHTML"));
            } catch (Exception e) {
                logger.error("--- see more scroll ---", e);
                seeMore = false;
            }
        } while (seeMore && count > 0);
    }

    private void seeMore(String xpath) {
        boolean seeMore = true;
        int count = 5;
        do {
            count--;
            try {
                WebElement element = webUnit.findElementByXPath(xpath);
                this.webUnit.executeScript("arguments[0].click();", element);
            } catch (Exception e) {
                logger.error("--- see more ---", e);
                seeMore = false;
            }
        } while (seeMore && count > 0);
    }

    private void seeMore(String xpath, String continueXpath) {
        boolean seeMore = true;
        int count = 5;
        do {
            count--;
            try {
                WebElement element = webUnit.findElementByXPath(xpath);
                this.webUnit.executeScript("arguments[0].click();", element);
            } catch (Exception e) {
                logger.error("--- see more ---", e);
                seeMore = false;
            }

            try {
                WebElement element = webUnit.findElementByXPath(continueXpath);
                seeMore = element != null;
            } catch (Exception e) {
                logger.error("--- see more ---", e);
                seeMore = false;
            }

        } while (seeMore && count > 0);
    }

    private void filter(FilterType filterType, String filter, String profileId, String data)
            throws CrawlingFilterException {

        if (filterType == null || StringUtils.isBlank(filter) || StringUtils.isBlank(profileId)) {
            return;
        }

        if (StringUtils.isBlank(data)) {
            logger.info("--- data empty ---");
            throw new CrawlingFilterException(profileId, filterType.getCode(), filter);
        }

        boolean filterProfile = false;
        switch (filterType) {
            case COUNTRY: {
                filterProfile = !CrawlingFilterUtil.country(filter, data);
                break;
            }
            case EDUCATION_LEVEL:
            case EDUCATION_SECTION:
            case SCHOOL:
            case TITLE:
            case WORKPLACE: {
                filterProfile = !CrawlingFilterUtil.contains(filter, data);
                break;
            }
            default: {
                break;
            }
        }

        if (filterProfile) {
            throw new CrawlingFilterException(profileId, filterType.getCode(), filter);
        }
    }
    
    public boolean checkData(CrawlingProcessingModel profile) {
        return !(StringUtils.isBlank(profile.getAboutInfo().getRawData())
                && StringUtils.isBlank(profile.getPersonalInfo().getRawData())
                && StringUtils.isBlank(profile.getSocialInfo().getRawData())
                && StringUtils.isBlank(profile.getContactInfo().getRawData())
                && StringUtils.isBlank(profile.getExperienceInfo().getRawData())
                && StringUtils.isBlank(profile.getEducationInfo().getRawData())
                && StringUtils.isBlank(profile.getCertificationInfo().getRawData())
                && StringUtils.isBlank(profile.getSkillsInfo().getRawData())
                && StringUtils.isBlank(profile.getReferenceInfo().getRawData())
                && StringUtils.isBlank(profile.getAccomplishmentInfo().getRawData())
                && StringUtils.isBlank(profile.getProjectsInfo().getRawData())
                && StringUtils.isBlank(profile.getInterestsInfo().getRawData()));
    }

}
