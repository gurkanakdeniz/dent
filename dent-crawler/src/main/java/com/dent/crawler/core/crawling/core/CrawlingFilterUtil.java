package com.dent.crawler.core.crawling.core;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.StringUtils;

public class CrawlingFilterUtil {
    public static final Map<String, String> COUNTRIES = Stream.of(new String[][] { { "namibya", "namibia" },
            { "paraguay", "paraguay" }, { "gana", "ghana" }, { "komorlar", "comoros" }, { "arnavutluk", "albania" },
            { "lübnan", "lebanon" }, { "montserrat", "montserrat" }, { "hırvatistan", "croatia" }, { "mali", "mali" },
            { "guadeloupe", "guadeloupe" }, { "panama", "panama" }, { "cook adaları", "cook islands" },
            { "gine", "guinea" }, { "cezayir", "algeria" }, { "laos", "laos" }, { "israil", "israel" },
            { "yeni zelanda", "new zealand" }, { "kırgızistan", "kyrgyzstan" }, { "yeşil burun adaları", "cape verde" },
            { "katar", "qatar" }, { "christmas adası", "christmas island" }, { "yeni kaledonya", "new caledonia" },
            { "vatikan", "vatican city" }, { "belize", "belize" }, { "hindistan", "india" }, { "zambiya", "zambia" },
            { "isviçre", "switzerland" }, { "jamaika", "jamaica" }, { "küba", "cuba" }, { "lüksemburg", "luxembourg" },
            { "kuveyt", "kuwait" }, { "svaziland", "swaziland" }, { "avusturya", "austria" }, { "sırbistan", "serbia" },
            { "türkiye", "turkey" }, { "yemen", "yemen" }, { "ermenistan", "armenia" },
            { "turks ve caicos adaları", "turks and caicos islands" }, { "aruba", "aruba" }, { "libya", "libya" },
            { "svalbard and jan mayen", "svalbard and jan mayen" }, { "saint martin", "saint martin" },
            { "madagaskar", "madagascar" }, { "çin halk cumhuriyeti", "china" },
            { "amerikan samoası", "american samoa" }, { "andorra", "andorra" },
            { "saint vincent ve grenadinler", "saint vincent and the grenadines" },
            { "cayman adaları", "cayman islands" }, { "porto riko", "puerto rico" }, { "kanada", "canada" },
            { "lihtenştayn", "liechtenstein" }, { "iran", "iran" }, { "kosta rika", "costa rica" },
            { "tuvalu", "tuvalu" }, { "demokratik kongo cumhuriyeti", "democratic republic of the congo" },
            { "kenya", "kenya" }, { "gine-bissau", "guinea-bissau" }, { "brunei", "brunei" }, { "malavi", "malawi" },
            { "saint lucia", "saint lucia" }, { "guernsey", "guernsey" }, { "mayotte", "mayotte" },
            { "wallis ve futuna adaları", "wallis and futuna" }, { "san marino", "san marino" },
            { "bonaire", "bonaire" }, { "myanmar", "myanmar" }, { "surinam", "suriname" },
            { "avustralya", "australia" }, { "özbekistan", "uzbekistan" }, { "karadağ", "montenegro" },
            { "gabon", "gabon" }, { "seyşeller", "seychelles" }, { "trinidad ve tobago", "trinidad and tobago" },
            { "almanya", "germany" }, { "tayland", "thailand" }, { "jersey", "jersey" }, { "kazakistan", "kazakhstan" },
            { "zimbabve", "zimbabwe" }, { "nijer", "niger" }, { "birleşik krallık", "united kingdom" },
            { "türkmenistan", "turkmenistan" }, { "kamboçya", "cambodia" }, { "batı sahra", "western sahara" },
            { "papua yeni gine", "papua new guinea" }, { "filipinler", "philippines" }, { "moldova", "moldova" },
            { "slovenya", "slovenia" }, { "norveç", "norway" }, { "monako", "monaco" }, { "filistin", "palestine" },
            { "vanuatu", "vanuatu" }, { "litvanya", "lithuania" }, { "fransız guyanası", "french guiana" },
            { "honduras", "honduras" }, { "güney kore", "south korea" }, { "nikaragua", "nicaragua" },
            { "kuzey kıbrıs türk cumhuriyeti", "northern cyprus" }, { "nauru", "nauru" }, { "kolombiya", "colombia" },
            { "haiti", "haiti" }, { "burundi", "burundi" }, { "ruanda", "rwanda" },
            { "fransız güney ve antarktika toprakları", "french southern territories" }, { "finlandiya", "finland" },
            { "ekvator ginesi", "equatorial guinea" }, { "japonya", "japan" },
            { "birleşik arap emirlikleri", "united arab emirates" }, { "bangladeş", "bangladesh" },
            { "mısır", "egypt" }, { "martinique", "martinique" }, { "sint maarten", "sint maarten" },
            { "eritre", "eritrea" }, { "fildişi sahili", "ivory coast" }, { "beyaz rusya", "belarus" },
            { "bhutan", "bhutan" }, { "togo", "togo" }, { "ekvador", "ecuador" }, { "italya", "italy" },
            { "letonya", "latvia" }, { "grönland", "greenland" }, { "güney sudan", "south sudan" },
            { "tayvan", "taiwan" }, { "romanya", "romania" }, { "ispanya", "spain" }, { "fas", "morocco" },
            { "güney afrika cumhuriyeti", "south africa" }, { "benin", "benin" }, { "tacikistan", "tajikistan" },
            { "angola", "angola" }, { "isveç", "sweden" }, { "kongo cumhuriyeti", "republic of the congo" },
            { "faroe adaları", "faroe islands" }, { "sudan", "sudan" }, { "makedonya", "macedonia" },
            { "grenada", "grenada" }, { "falkland adaları", "falkland islands" },
            { "britanya hint okyanusu toprakları", "british indian ocean territory" }, { "ırak", "iraq" },
            { "kamerun", "cameroon" }, { "botsvana", "botswana" },
            { "u.s. minor outlying ıslands", "u.s. minor outlying islands" }, { "guatemala", "guatemala" },
            { "guyana", "guyana" }, { "ukrayna", "ukraine" },
            { "heard adası ve mcdonald adaları", "heard island and mcdonald islands" }, { "nepal", "nepal" },
            { "bahreyn", "bahrain" }, { "kuzey kore", "north korea" }, { "norfolk adası", "norfolk island" },
            { "mozambik", "mozambique" }, { "şili", "chile" }, { "çad", "chad" },
            { "são tomé ve príncipe", "são tomé and príncipe" }, { "curaçao", "curacao" }, { "anguilla", "anguilla" },
            { "cocos adaları", "cocos islands" }, { "belçika", "belgium" }, { "portekiz", "portugal" },
            { "tanzanya", "tanzania" }, { "åland", "åland" }, { "bermuda", "bermuda" },
            { "solomon adaları", "solomon islands" }, { "peru", "peru" }, { "liberya", "liberia" },
            { "hollanda", "netherlands" }, { "umman", "oman" }, { "tokelau", "tokelau" }, { "fiji", "fiji" },
            { "hong kong", "hong kong" }, { "yunanistan", "greece" },
            { "amerika birleşik devletleri", "united states" }, { "macaristan", "hungary" }, { "gambiya", "gambia" },
            { "suriye", "syria" }, { "kiribati", "kiribati" },
            { "orta afrika cumhuriyeti", "central african republic" }, { "vietnam", "vietnam" },
            { "malezya", "malaysia" }, { "polonya", "poland" }, { "marshall adaları", "marshall islands" },
            { "antigua ve barbuda", "antigua and barbuda" }, { "afganistan", "afghanistan" }, { "uruguay", "uruguay" },
            { "sri lanka", "sri lanka" }, { "etiyopya", "ethiopia" }, { "gürcistan", "georgia" },
            { "kuzey mariana adaları", "northern mariana islands" }, { "moğolistan", "mongolia" }, { "samoa", "samoa" },
            { "estonya", "estonia" }, { "venezuela", "venezuela" },
            { "saint pierre ve miquelon", "saint pierre and miquelon" }, { "burkina faso", "burkina faso" },
            { "bulgaristan", "bulgaria" }, { "izlanda", "iceland" }, { "palau", "palau" },
            { "saint barthélemy", "saint barthélemy" }, { "somali", "somalia" },
            { "fransız polinezyası", "french polynesia" }, { "çek cumhuriyeti", "czech republic" },
            { "bosna-hersek", "bosnia and herzegovina" }, { "man adası", "isle of man" },
            { "dominik cumhuriyeti", "dominican republic" },
            { "güney georgia ve güney sandwich adaları", "south georgia and the south sandwich islands" },
            { "maldivler", "maldives" }, { "niue", "niue" }, { "el salvador", "el salvador" }, { "guam", "guam" },
            { "tunus", "tunisia" }, { "endonezya", "indonesia" }, { "lesotho", "lesotho" }, { "tonga", "tonga" },
            { "bouvet adası", "bouvet island" }, { "réunion", "réunion" }, { "arjantin", "argentina" },
            { "britanya virjin adaları", "british virgin islands" }, { "mauritius", "mauritius" },
            { "senegal", "senegal" }, { "nijerya", "nigeria" }, { "suudi arabistan", "saudi arabia" },
            { "fransa", "france" }, { "cebelitarık", "gibraltar" }, { "rusya", "russia" }, { "bahamalar", "bahamas" },
            { "cibuti", "djibouti" }, { "brezilya", "brazil" }, { "singapur", "singapore" }, { "kosova", "kosovo" },
            { "dominika", "dominica" }, { "sierra leone", "sierra leone" }, { "mikronezya", "micronesia" },
            { "makao", "macau" }, { "malta", "malta" }, { "ürdün", "jordan" }, { "pakistan", "pakistan" },
            { "azerbaycan", "azerbaijan" }, { "doğu timor", "east timor" }, { "irlanda", "ireland" },
            { "danimarka", "denmark" }, { "abd virjin adaları", "u.s. virgin islands" }, { "slovakya", "slovakia" },
            { "moritanya", "mauritania" }, { "meksika", "mexico" }, { "barbados", "barbados" }, { "uganda", "uganda" },
            { "saint kitts ve nevis", "saint kitts and nevis" }, { "bolivya", "bolivia" },
            { "saint helena", "saint helena" }, { "pitcairn adaları", "pitcairn islands" } })
            .collect(Collectors.toMap(data -> data[0], data -> data[1]));

    public static boolean country(String value, String data) {
        if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(data)) {
            if (StringUtils.containsIgnoreCase(data, value)) {
                return true;
            }
            
            String country = COUNTRIES.get(value.toLowerCase());
            if (StringUtils.isNotBlank(country)) {
                if (StringUtils.containsIgnoreCase(data, country)) {
                    return true;
                }
            }
        }
        
        return false;
    }
    
    public static boolean contains(String value, String data) {
        if (StringUtils.isNotBlank(value) && StringUtils.isNotBlank(data)) {
            if (StringUtils.containsIgnoreCase(data, value)) {
                return true;
            }
        }
        
        return false;
    }

}
