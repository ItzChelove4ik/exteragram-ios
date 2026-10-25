package com.stripe.android.util;

/* loaded from: classes5.dex */
public abstract class StripeTextUtils {
    public static String asCardBrand(String r2) {
        if (isBlank(r2) == false) goto L7;
        return null;
    L7:
        if ("American Express".equalsIgnoreCase(r2) == false) goto L10;
        return "American Express";
    L10:
        if ("MasterCard".equalsIgnoreCase(r2) == false) goto L13;
        return "MasterCard";
    L13:
        if ("Diners Club".equalsIgnoreCase(r2) == false) goto L16;
        return "Diners Club";
    L16:
        if ("Discover".equalsIgnoreCase(r2) == false) goto L19;
        return "Discover";
    L19:
        if ("JCB".equalsIgnoreCase(r2) == false) goto L22;
        return "JCB";
    L22:
        if ("Visa".equalsIgnoreCase(r2) == false) goto L24;
        return "Visa";
    L24:
        return "Unknown";
    }

    public static String asFundingType(String r2) {
        if (isBlank(r2) == false) goto L7;
        return null;
    L7:
        if ("credit".equalsIgnoreCase(r2) == false) goto L10;
        return "credit";
    L10:
        if ("debit".equalsIgnoreCase(r2) == false) goto L13;
        return "debit";
    L13:
        if ("prepaid".equalsIgnoreCase(r2) == false) goto L15;
        return "prepaid";
    L15:
        return "unknown";
    }

    public static String asTokenType(String r1) {
        if ("card".equals(r1) == false) goto L5;
        return "card";
    L5:
        return null;
    }

    public static boolean hasAnyPrefix(String r4, String... r5) {
        if (r4 != null) goto L5;
        return false;
    L5:
        int r1 = r5.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L12;
        if (r4.startsWith(r5[r2]) == true) goto L9;
        r2 = r2 + 1;
        goto L6
    L9:
        return true;
    L12:
        return false;
    }

    public static boolean isBlank(String r0) {
        if (r0 != null) goto L4;
        return true;
    L4:
        if (r0.trim().length() == 0) goto L10;
        return false;
    L10:
        return true;
    }

    public static boolean isWholePositiveNumber(String r3) {
        if (r3 != null) goto L5;
        return false;
    L5:
        int r1 = 0;
    L7:
        if (r1 >= r3.length()) goto L12;
        if (Character.isDigit(r3.charAt(r1)) == false) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        return false;
    L12:
        return true;
    }

    public static String nullIfBlank(String r1) {
        if (isBlank(r1) == false) goto L6;
        return null;
    L6:
        return r1;
    }
}
