package com.dent.iext.core.iext.core;

import static com.dent.iext.core.iext.model.ProfileKey.ABOUT;
import static com.dent.iext.core.iext.model.ProfileKey.ACCOMPLISHMENT;
import static com.dent.iext.core.iext.model.ProfileKey.CERTIFICATE;
import static com.dent.iext.core.iext.model.ProfileKey.CONNECTION;
import static com.dent.iext.core.iext.model.ProfileKey.EDUCATION;
import static com.dent.iext.core.iext.model.ProfileKey.EMAIL;
import static com.dent.iext.core.iext.model.ProfileKey.EXPERIENCE;
import static com.dent.iext.core.iext.model.ProfileKey.NAME;
import static com.dent.iext.core.iext.model.ProfileKey.PHONE;
import static com.dent.iext.core.iext.model.ProfileKey.PHOTO;
import static com.dent.iext.core.iext.model.ProfileKey.PROJECT;
import static com.dent.iext.core.iext.model.ProfileKey.REFERENCE;
import static com.dent.iext.core.iext.model.ProfileKey.SKILL;
import static com.dent.iext.core.iext.model.ProfileKey.TITLE;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;
import java.util.Objects;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dent.iext.core.iext.model.ConsumerCrawlingModel;
import com.dent.iext.core.iext.model.CrawlingProcessingModel;
import com.dent.iext.core.iext.model.ProfileKey;
import com.dent.iext.core.iext.model.ProfileModel;
import com.dent.iext.core.iext.model.UserModel;

public class InformationExtractionProcessing {

    private static final Logger logger = LoggerFactory.getLogger(InformationExtractionProcessing.class);

    private String profile;
    private EnumMap<ProfileKey, List<Object>> info;
    private ConsumerCrawlingModel crawlingModel;
    private CrawlingProcessingModel crawlingData;

    public InformationExtractionProcessing init() {
        return this;
    }

    public InformationExtractionProcessingModel processing(InformationExtraction informationExtraction) {
        logger.info("--- processing start " + this.profile + " ---");

        this.info = new EnumMap<>(ProfileKey.class);
        this.crawlingModel = informationExtraction.getCrawlingModel();
        this.crawlingData = this.crawlingModel.getCrawlingData();
        this.profile = this.crawlingModel.getCrawlingModel().getProfileId();

        try {
            personalInfo();
            contactInfo();
            aboutInfo();
            socialInfo();
            experienceInfo();
            educationInfo();
            certificationInfo();
            skillsInfo();
            referenceInfo();
            accomplishmentInfo();
            projectsInfo();
            interestsInfo();
        } catch (Exception e) {
            logger.error("--- processing " + this.profile + " ---", e);
        }

        logger.info("--- processing end " + this.profile + " ---");
        return new InformationExtractionProcessingModel(new UserModel(crawlingModel.getCrawlingModel().getProfileId(),
                crawlingModel, new ProfileModel(this.info)));
    }

    private void personalInfo() {
        try {
            Document personalInfo = Jsoup.parse(this.crawlingData.getPersonalInfo().getRawData());

            if (Objects.nonNull(personalInfo)) {
                try {
                    String imgSrc = personalInfo.select("img[class*=card__photo]").get(0).attr("src");
                    if (imgSrc != null && imgSrc.length() > 0) {
                        this.info.put(PHOTO, Arrays.asList(imgSrc));    
                    }
                } catch (Exception e) {
                    logger.error("--- personal info " + this.profile + " ---", e);
                }
                
                try {
                    this.info.put(NAME, Arrays
                            .asList(personalInfo.getElementsByTag("ul").get(0).getElementsByTag("li").get(0).text()));
                } catch (Exception e) {
                    logger.error("--- personal info " + this.profile + " ---", e);
                }

                try {
                    this.info.put(TITLE, Arrays.asList(personalInfo.getElementsByTag("h2").get(0).text()));
                } catch (Exception e) {
                    logger.error("--- personal info " + this.profile + " ---", e);
                }
            }
        } catch (Exception e) {
            logger.error("--- personal info " + this.profile + " ---", e);
        }
    }

    private void aboutInfo() {
        try {
            Document aboutInfo = Jsoup.parse(crawlingData.getAboutInfo().getRawData());
            if (aboutInfo != null) {
                this.info.put(ABOUT, Arrays.asList(aboutInfo.getElementsByTag("span").get(0).text()));
            }
        } catch (Exception e) {
            logger.error("--- about info " + this.profile + " ---", e);
        }
    }

    private void socialInfo() {
        try {
            Document socialInfo = Jsoup.parse(this.crawlingData.getPersonalInfo().getRawData());
            this.info.put(CONNECTION,
                    Arrays.asList(socialInfo.getElementsByTag("ul").get(1).getElementsByTag("li").get(1).text()));
        } catch (Exception e) {
            logger.error("--- social info " + this.profile + " ---", e);
        }
    }

    private void contactInfo() {
        try {
            Document contactInfo = Jsoup.parse(this.crawlingData.getContactInfo().getRawData());

            try {
                String email = contactInfo.select("section[class*=email]").get(0).getElementsByTag("a").text();
                if (email != null && email.trim().length() > 0) {
                    this.info.put(EMAIL, Arrays.asList(email));
                }
            } catch (Exception e) {
                logger.error("--- contact info " + this.profile + " ---", e);
            }

            try {
                String phone = contactInfo.select("section[class*=phone]").get(0).getElementsByTag("li").text();
                if (phone != null && phone.trim().length() > 0) {
                    this.info.put(PHONE, Arrays.asList(phone));
                }
            } catch (Exception e) {
                logger.error("--- contact info " + this.profile + " ---", e);
            }
        } catch (Exception e) {
            logger.error("--- contact info " + this.profile + " ---", e);
        }
    }

    private void experienceInfo() {
        try {
            Document experienceInfo = Jsoup.parse(crawlingData.getExperienceInfo().getRawData());
            Elements experiences = experienceInfo.getElementsByTag("section");

            if (experiences != null && !experiences.isEmpty()) {
                List<Object> experienceList = new ArrayList<Object>();

                for (Element element : experiences) {
                    experienceList.add(element.text());
                }

                this.info.put(EXPERIENCE, experienceList);
            }
        } catch (Exception e) {
            logger.error("--- experience info " + this.profile + " ---", e);
        }
    }

    private void educationInfo() {
        try {
            Document educationInfo = Jsoup.parse(crawlingData.getEducationInfo().getRawData());
            Elements educations = educationInfo.getElementsByTag("li");

            if (educations != null && !educations.isEmpty()) {
                List<Object> educationList = new ArrayList<Object>();

                for (Element element : educations) {
                    educationList.add(element.text());
                }

                this.info.put(EDUCATION, educationList);
            }
        } catch (Exception e) {
            logger.error("--- education info " + this.profile + " ---", e);
        }
    }

    private void certificationInfo() {
        try {
            Document certificationInfo = Jsoup.parse(crawlingData.getCertificationInfo().getRawData());
            Elements items = certificationInfo.getElementsByTag("li");

            if (items != null && !items.isEmpty()) {
                List<Object> certificationList = new ArrayList<Object>();

                for (Element item : items) {
                    String text = "";

                    try {
                        text = item.getElementsByTag("h3").get(0).text() + " : ";
                    } catch (Exception e) {
                        // ignore
                    }

                    try {
                        Elements texts = item.getElementsByTag("span");
                        text += String.join("-", texts.eachText());
                    } catch (Exception e) {
                        // ignore
                    }

                    certificationList.add(text);
                }

                this.info.put(CERTIFICATE, certificationList);
            }

        } catch (Exception e) {
            logger.error("--- certification info " + this.profile + " ---", e);
        }
    }

    private void skillsInfo() {
        try {
            Document skillsInfo = Jsoup.parse(crawlingData.getSkillsInfo().getRawData());

            Elements items = skillsInfo.getElementsByTag("li");
            if (items != null && !items.isEmpty()) {
                List<Object> skillList = new ArrayList<Object>();

                for (Element item : items) {
                    try {
                        Elements itemSkills = item.select("span[class*=skill-category-entity]")
                                .select("span[class*=text]");
                        if (itemSkills != null) {
                            for (Element skill : itemSkills) {
                                skillList.add(skill.text());
                            }
                        }
                    } catch (Exception e) {
                        logger.error("--- skills info " + this.profile + " ---", e);
                    }
                }

                this.info.put(SKILL, skillList);
            }
        } catch (Exception e) {
            logger.error("--- skills info " + this.profile + " ---", e);
        }
    }

    private void referenceInfo() {
        try {
            Document referenceInfo = Jsoup.parse(crawlingData.getReferenceInfo().getRawData());
            Elements items = referenceInfo.getElementsByTag("li");

            if (items != null && !items.isEmpty()) {
                List<Object> referenceList = new ArrayList<Object>();

                for (Element item : items) {
                    String text = "";

                    try {
                        text = item.getElementsByTag("h3").get(0).text() + " - ";
                    } catch (Exception e) {
                        // ignore
                    }

                    try {
                        text += item.getElementsByTag("p").get(0).text();
                    } catch (Exception e) {
                        // ignore
                    }

                    referenceList.add(text);
                }

                this.info.put(REFERENCE, referenceList);
            }

        } catch (Exception e) {
            logger.error("--- reference info " + this.profile + " ---", e);
        }
    }

    private void accomplishmentInfo() {
        try {
            Document accomplishmentInfo = Jsoup.parse(crawlingData.getAccomplishmentInfo().getRawData());
            Elements items = accomplishmentInfo.getElementsByTag("li");

            if (items != null && !items.isEmpty()) {
                List<Object> accomplishmentList = new ArrayList<Object>();

                for (Element item : items) {
                    String text = item.text();

                    accomplishmentList.add(text);
                }

                this.info.put(ACCOMPLISHMENT, accomplishmentList);
            }
        } catch (Exception e) {
            logger.error("--- accomplishment info " + this.profile + " ---", e);
        }
    }

    private void projectsInfo() {
        try {
            Document projectsInfo = Jsoup.parse(crawlingData.getProjectsInfo().getRawData());

            Elements projects = projectsInfo.getElementsByTag("ul").get(0).getElementsByTag("li");
            if (projects != null && !projects.isEmpty()) {
                List<Object> projectList = new ArrayList<Object>();

                for (Element element : projects) {
                    projectList.add(element.getElementsByTag("span").get(1).ownText() + " : " + element.ownText());
                }

                this.info.put(PROJECT, projectList);
            }

        } catch (Exception e) {
            logger.error("--- projects info " + this.profile + " ---", e);
        }
    }

    private void interestsInfo() {
        try {
            Document interestsInfo = Jsoup.parse(crawlingData.getInterestsInfo().getRawData());

            Elements interests = interestsInfo.getElementsByTag("ul").get(0).getElementsByTag("li");
            if (interests != null && !interests.isEmpty()) {
                List<Object> interestsList = new ArrayList<Object>();

                for (Element element : interests) {
                    interestsList.add(element.getElementsByTag("h3").get(0).text());
                }

                this.info.put(ProfileKey.INTEREST, interestsList);
            }
        } catch (Exception e) {
            logger.error("--- interests info " + this.profile + " ---", e);
        }
    }

}
