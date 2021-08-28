package ui.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum MenuItems {
    DASHBOARD("Dashboard"),
    STATISTICS("Statistics"),
    OFFERS("Offers"),

    //Advertisers
    ADVERTISERS("Advertisers"),
    ADVERTISERS_SUB_ITEM("Advertisers"),
    SKAD_NETWORRS("SKAdNetworks"),

    //SmartLinks
    SMART_LINKS("SmartLinks"),

    //Automation
    AUTOMATION("Automation"),
    CR_AUTOMATION("CR Automation"),
    CLICK_AUTOMATION("Click Automation"),

    //Affiliates
    AFFILIATES("Affiliates"),
    AFFILIATES_MANAGEMENT("Affiliates Management"),
    PIXELS_MANAGEMENT("Pixels Management"),
    TESTING_LINKS("Testing Links"),
    TOP("Top"),
    AFFILIATES_AUTH_LOG("Affiliates Auth Log"),

    //Tickets
    TICKETS("Tickets"),
    TICKETS_LIST("Tickets List"),
    TEMPLATES("Templates"),

    //BILLING
    BILLING("Billing"),

    //USERS
    USERS("Users"),
    USERS_MANAGEMENT("Users Management"),
    USERS_ACTIVITY_LOG("Users Activity Log"),
    PERMISSIONS_PRESETS("Permissions Presets");

    private final String item;
}
