package com.dent.bff.service.remote.model;

import java.util.Date;

public class CrawlingProcessingModel extends BaseModel {

    private PersonalInfo personalInfo;
    private AboutInfo aboutInfo;
    private SocialInfo socialInfo;
    private ContactInfo contactInfo;
    private ExperienceInfo experienceInfo;
    private EducationInfo educationInfo;
    private CertificationInfo certificationInfo;
    private SkillsInfo skillsInfo;
    private ReferenceInfo referenceInfo;
    private AccomplishmentInfo accomplishmentInfo;
    private ProjectsInfo projectsInfo;
    private InterestsInfo interestsInfo;

    public CrawlingProcessingModel() {
        super();
        init();
    }

    private void init() {
        this.personalInfo = new PersonalInfo();
        this.aboutInfo = new AboutInfo();
        this.socialInfo = new SocialInfo();
        this.contactInfo = new ContactInfo();
        this.experienceInfo = new ExperienceInfo();
        this.educationInfo = new EducationInfo();
        this.certificationInfo = new CertificationInfo();
        this.skillsInfo = new SkillsInfo();
        this.referenceInfo = new ReferenceInfo();
        this.accomplishmentInfo = new AccomplishmentInfo();
        this.projectsInfo = new ProjectsInfo();
        this.interestsInfo = new InterestsInfo();
    }

    public PersonalInfo getPersonalInfo() {
        return personalInfo;
    }

    public void setPersonalInfo(PersonalInfo personalInfo) {
        this.personalInfo = personalInfo;
    }

    public AboutInfo getAboutInfo() {
        return aboutInfo;
    }

    public void setAboutInfo(AboutInfo aboutInfo) {
        this.aboutInfo = aboutInfo;
    }

    public SocialInfo getSocialInfo() {
        return socialInfo;
    }

    public void setSocialInfo(SocialInfo socialInfo) {
        this.socialInfo = socialInfo;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(ContactInfo contactInfo) {
        this.contactInfo = contactInfo;
    }

    public ExperienceInfo getExperienceInfo() {
        return experienceInfo;
    }

    public void setExperienceInfo(ExperienceInfo experienceInfo) {
        this.experienceInfo = experienceInfo;
    }

    public EducationInfo getEducationInfo() {
        return educationInfo;
    }

    public void setEducationInfo(EducationInfo educationInfo) {
        this.educationInfo = educationInfo;
    }

    public CertificationInfo getCertificationInfo() {
        return certificationInfo;
    }

    public void setCertificationInfo(CertificationInfo certificationInfo) {
        this.certificationInfo = certificationInfo;
    }

    public SkillsInfo getSkillsInfo() {
        return skillsInfo;
    }

    public void setSkillsInfo(SkillsInfo skillsInfo) {
        this.skillsInfo = skillsInfo;
    }

    public ReferenceInfo getReferenceInfo() {
        return referenceInfo;
    }

    public void setReferenceInfo(ReferenceInfo referenceInfo) {
        this.referenceInfo = referenceInfo;
    }

    public AccomplishmentInfo getAccomplishmentInfo() {
        return accomplishmentInfo;
    }

    public void setAccomplishmentInfo(AccomplishmentInfo accomplishmentInfo) {
        this.accomplishmentInfo = accomplishmentInfo;
    }

    public ProjectsInfo getProjectsInfo() {
        return projectsInfo;
    }

    public void setProjectsInfo(ProjectsInfo projectsInfo) {
        this.projectsInfo = projectsInfo;
    }

    public InterestsInfo getInterestsInfo() {
        return interestsInfo;
    }

    public void setInterestsInfo(InterestsInfo interestsInfo) {
        this.interestsInfo = interestsInfo;
    }

    public class PersonalInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class AboutInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class SocialInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class ContactInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class ExperienceInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class EducationInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class CertificationInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class SkillsInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class ReferenceInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class AccomplishmentInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class ProjectsInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class InterestsInfo extends BaseInfo {
        private static final long serialVersionUID = 1L;
    }

    public class BaseInfo extends BaseModel {
        private static final long serialVersionUID = 1L;

        protected String rawData;
        protected Date rawDate;

        public BaseInfo() {
            super();
        }

        public BaseInfo(String rawData) {
            super();
            this.rawData = rawData;
        }

        public BaseInfo(String rawData, Date rawDate) {
            super();
            this.rawData = rawData;
            this.rawDate = rawDate;
        }

        public String getRawData() {
            return rawData;
        }

        public void setRawData(String rawData) {
            this.rawData = rawData;
        }

        public Date getRawDate() {
            return rawDate;
        }

        public void setRawDate(Date rawDate) {
            this.rawDate = rawDate;
        }

        public void setInfo(String rawData, Date rawDate) {
            this.rawData = rawData;
            this.rawDate = rawDate;
        }
    }
}
