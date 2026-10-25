package com.stripe.android.model;

import com.stripe.android.util.DateUtils;
import com.stripe.android.util.StripeTextUtils;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public class Card {
    public static final String[] PREFIXES_AMERICAN_EXPRESS = null;
    public static final String[] PREFIXES_DINERS_CLUB = null;
    public static final String[] PREFIXES_DISCOVER = null;
    public static final String[] PREFIXES_JCB = null;
    public static final String[] PREFIXES_MASTERCARD = null;
    public static final String[] PREFIXES_VISA = null;
    private String addressCity;
    private String addressCountry;
    private String addressLine1;
    private String addressLine2;
    private String addressState;
    private String addressZip;
    private String brand;
    private String country;
    private String currency;
    private String cvc;
    private Integer expMonth;
    private Integer expYear;
    private String fingerprint;
    private String funding;
    private String last4;
    private String name;
    private String number;

    static {
        PREFIXES_AMERICAN_EXPRESS = new String[]{"34", "37"};
        PREFIXES_DISCOVER = new String[]{"60", "62", "64", "65"};
        PREFIXES_JCB = new String[]{"35"};
        PREFIXES_DINERS_CLUB = new String[]{"300", "301", "302", "303", "304", "305", "309", "36", "38", "39"};
        PREFIXES_VISA = new String[]{"4"};
        PREFIXES_MASTERCARD = new String[]{"2221", "2222", "2223", "2224", "2225", "2226", "2227", "2228", "2229", "223", "224", "225", "226", "227", "228", "229", "23", "24", "25", "26", "270", "271", "2720", "50", "51", "52", "53", "54", "55"};
    }

    public Card(String r19, Integer r20, Integer r21, String r22, String r23, String r24, String r25, String r26, String r27, String r28, String r29, String r30) {
        this(r19, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, null, null, null, null, null, r30);
    }

    public Card(String r1, Integer r2, Integer r3, String r4, String r5, String r6, String r7, String r8, String r9, String r10, String r11, String r12, String r13, String r14, String r15, String r16, String r17) {
        this.number = StripeTextUtils.nullIfBlank(normalizeCardNumber(r1));
        this.expMonth = r2;
        this.expYear = r3;
        this.cvc = StripeTextUtils.nullIfBlank(r4);
        this.name = StripeTextUtils.nullIfBlank(r5);
        this.addressLine1 = StripeTextUtils.nullIfBlank(r6);
        this.addressLine2 = StripeTextUtils.nullIfBlank(r7);
        this.addressCity = StripeTextUtils.nullIfBlank(r8);
        this.addressState = StripeTextUtils.nullIfBlank(r9);
        this.addressZip = StripeTextUtils.nullIfBlank(r10);
        this.addressCountry = StripeTextUtils.nullIfBlank(r11);
        if (StripeTextUtils.asCardBrand(r12) != null) goto L5;
        r12 = getBrand();
    L5:
        this.brand = r12;
        if (StripeTextUtils.nullIfBlank(r13) != null) goto L8;
        r13 = getLast4();
    L8:
        this.last4 = r13;
        this.fingerprint = StripeTextUtils.nullIfBlank(r14);
        this.funding = StripeTextUtils.asFundingType(r15);
        this.country = StripeTextUtils.nullIfBlank(r16);
        this.currency = StripeTextUtils.nullIfBlank(r17);
    }

    private boolean isValidLuhnNumber(String r8) {
        int r7 = r8.length() - 1;
        boolean r3 = true;
        int r2 = 0;
    L3:
        if (r7 < 0) goto L15;
        char r4 = r8.charAt(r7);
        if (Character.isDigit(r4) == false) goto L6;
        int r42 = Integer.parseInt(_UrlKt.FRAGMENT_ENCODE_SET + r4);
        boolean r5 = r3 ^ true;
        if (r3 == true) goto L11;
        r42 = r42 * 2;
    L11:
        if (r42 <= 9) goto L13;
        r42 = r42 - 9;
    L13:
        r2 = r2 + r42;
        r7 = r7 - 1;
        r3 = r5;
        goto L3
    L6:
        return false;
    L15:
        if ((r2 % 10) != 0) goto L17;
        return true;
    L17:
        return false;
    }

    private String normalizeCardNumber(String r2) {
        if (r2 != null) goto L6;
        return null;
    L6:
        return r2.trim().replaceAll("\\s+|-", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public String getAddressCity() {
        return this.addressCity;
    }

    public String getAddressCountry() {
        return this.addressCountry;
    }

    public String getAddressLine1() {
        return this.addressLine1;
    }

    public String getAddressLine2() {
        return this.addressLine2;
    }

    public String getAddressState() {
        return this.addressState;
    }

    public String getAddressZip() {
        return this.addressZip;
    }

    public String getBrand() {
        if (StripeTextUtils.isBlank(this.brand) == false) goto L27;
        if (StripeTextUtils.isBlank(this.number) == true) goto L27;
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_AMERICAN_EXPRESS) == false) goto L10;
        String r0 = "American Express";
    L25:
        this.brand = r0;
        goto L27
    L10:
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_DISCOVER) == false) goto L13;
        r0 = "Discover";
        goto L25
    L13:
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_JCB) == false) goto L16;
        r0 = "JCB";
        goto L25
    L16:
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_DINERS_CLUB) == false) goto L19;
        r0 = "Diners Club";
        goto L25
    L19:
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_VISA) == false) goto L22;
        r0 = "Visa";
        goto L25
    L22:
        if (StripeTextUtils.hasAnyPrefix(this.number, PREFIXES_MASTERCARD) == false) goto L24;
        r0 = "MasterCard";
        goto L25
    L24:
        r0 = "Unknown";
    L27:
        return this.brand;
    }

    public String getCVC() {
        return this.cvc;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Integer getExpMonth() {
        return this.expMonth;
    }

    public Integer getExpYear() {
        return this.expYear;
    }

    public String getLast4() {
        if (StripeTextUtils.isBlank(this.last4) == false) goto L5;
        String r0 = this.number;
        if (r0 != null) goto L9;
        return null;
    L9:
        if (r0.length() <= 4) goto L14;
        String r02 = this.number;
        String r03 = r02.substring(r02.length() - 4, this.number.length());
        this.last4 = r03;
        return r03;
    L14:
        return null;
    L5:
        return this.last4;
    }

    public String getName() {
        return this.name;
    }

    public String getNumber() {
        return this.number;
    }

    public boolean validateCVC() {
        if (StripeTextUtils.isBlank(this.cvc) == false) goto L5;
        return false;
    L5:
        String r0 = this.cvc.trim();
        String r6 = getBrand();
        if (r6 != null) goto L12;
        if (r0.length() < 3) goto L12;
        if (r0.length() > 4) goto L12;
    L17:
        boolean r62 = true;
    L20:
        if (StripeTextUtils.isWholePositiveNumber(r0) == false) goto L23;
        if (r62 == false) goto L23;
        return true;
    L23:
        return false;
    L12:
        if ("American Express".equals(r6) == false) goto L16;
        if (r0.length() == 4) goto L17;
    L16:
        if (r0.length() == 3) goto L17;
        r62 = false;
        goto L20
    }

    public boolean validateExpMonth() {
        Integer r0 = this.expMonth;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.intValue() >= 1) goto L7;
        return false;
    L7:
        if (this.expMonth.intValue() > 12) goto L12;
        return true;
    L12:
        return false;
    }

    public boolean validateExpYear() {
        Integer r0 = this.expYear;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (DateUtils.hasYearPassed(r0.intValue()) == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean validateExpiryDate() {
        if (validateExpMonth() == true) goto L6;
        return false;
    L6:
        if (validateExpYear() == true) goto L9;
        return false;
    L9:
        return !DateUtils.hasMonthPassed(this.expYear.intValue(), this.expMonth.intValue());
    }

    public boolean validateNumber() {
        if (StripeTextUtils.isBlank(this.number) == false) goto L5;
        return false;
    L5:
        String r0 = this.number.trim().replaceAll("\\s+|-", _UrlKt.FRAGMENT_ENCODE_SET);
        if (StripeTextUtils.isBlank(r0) == false) goto L8;
    L27:
        return false;
    L8:
        if (StripeTextUtils.isWholePositiveNumber(r0) == false) goto L27;
        if (isValidLuhnNumber(r0) == false) goto L27;
        String r4 = getBrand();
        if ("American Express".equals(r4) == false) goto L19;
        if (r0.length() != 15) goto L17;
        return true;
    L17:
        return false;
    L19:
        if ("Diners Club".equals(r4) == false) goto L25;
        if (r0.length() != 14) goto L23;
        return true;
    L23:
        return false;
    L25:
        if (r0.length() != 16) goto L27;
        return true;
    }
}
