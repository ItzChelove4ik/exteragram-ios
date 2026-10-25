package androidx.media3.common.util;

import org.xmlpull.v1.XmlPullParser;

/* loaded from: classes3.dex */
public abstract class XmlPullParserUtil {
    public static String getAttributeValue(XmlPullParser r3, String r4) {
        int r0 = r3.getAttributeCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (r3.getAttributeName(r1).equals(r4) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.getAttributeValue(r1);
    L9:
        return null;
    }

    public static String getAttributeValueIgnorePrefix(XmlPullParser r3, String r4) {
        int r0 = r3.getAttributeCount();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        if (stripPrefix(r3.getAttributeName(r1)).equals(r4) == true) goto L7;
        r1 = r1 + 1;
        goto L3
    L7:
        return r3.getAttributeValue(r1);
    L9:
        return null;
    }

    public static boolean isEndTag(XmlPullParser r1) {
        if (r1.getEventType() != 3) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isEndTag(XmlPullParser r1, String r2) {
        if (isEndTag(r1) == true) goto L5;
        return false;
    L5:
        if (r1.getName().equals(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isStartTag(XmlPullParser r1) {
        if (r1.getEventType() != 2) goto L6;
        return true;
    L6:
        return false;
    }

    public static boolean isStartTag(XmlPullParser r1, String r2) {
        if (isStartTag(r1) == true) goto L5;
        return false;
    L5:
        if (r1.getName().equals(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public static boolean isStartTagIgnorePrefix(XmlPullParser r1, String r2) {
        if (isStartTag(r1) == true) goto L5;
        return false;
    L5:
        if (stripPrefix(r1.getName()).equals(r2) == false) goto L10;
        return true;
    L10:
        return false;
    }

    private static String stripPrefix(String r2) {
        int r0 = r2.indexOf(58);
        if (r0 != (-1)) goto L6;
        return r2;
    L6:
        return r2.substring(r0 + 1);
    }
}
