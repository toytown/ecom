package com.ecom.domain;

public enum Language {
    EN("en"), DE("de");
    
    private final String languagekey;
    public static final short LANGUAGE_CODE_GERMAN     = 121;
    
    Language(String langkey) {
        this.languagekey = langkey;
    }
    
    public String getLanguage() {
        return this.languagekey;
    }
    
    public static Language fromLanguageKey(String language) {
        for (Language value : Language.values()) {
            if (value.languagekey.equalsIgnoreCase(language)) {
                return value;
            }
        }
        throw new IllegalArgumentException(String.format("Given Item category %s doesn't exist in enumeration ItemCategoryValue", language));
    }    
}
