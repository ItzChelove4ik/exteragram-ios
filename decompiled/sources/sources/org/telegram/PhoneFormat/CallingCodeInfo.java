package org.telegram.PhoneFormat;

import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes.dex */
public class CallingCodeInfo {
    public String callingCode;
    public ArrayList<String> countries;
    public ArrayList<String> intlPrefixes;
    public ArrayList<RuleSet> ruleSets;
    public ArrayList<String> trunkPrefixes;

    public CallingCodeInfo() {
        this.countries = new ArrayList();
        this.callingCode = _UrlKt.FRAGMENT_ENCODE_SET;
        this.trunkPrefixes = new ArrayList();
        this.intlPrefixes = new ArrayList();
        this.ruleSets = new ArrayList();
    }

    public String format(String r11) {
        String r1 = null;
        if (r11.startsWith(this.callingCode) == false) goto L5;
        String r0 = this.callingCode;
        String r2 = r11.substring(r0.length());
    L9:
        ArrayList<RuleSet> r3 = this.ruleSets;
        int r4 = r3.size();
        int r6 = 0;
    L10:
        if (r6 >= r4) goto L14;
        RuleSet r7 = r3.get(r6);
        r6 = r6 + 1;
        String r72 = r7.format(r2, r0, r1, true);
        if (r72 == null) goto L10;
        return r72;
    L14:
        ArrayList<RuleSet> r10 = this.ruleSets;
        int r32 = r10.size();
        int r42 = 0;
    L15:
        if (r42 >= r32) goto L19;
        RuleSet r62 = r10.get(r42);
        r42 = r42 + 1;
        String r63 = r62.format(r2, r0, r1, false);
        if (r63 == null) goto L15;
        return r63;
    L19:
        if (r0 != null) goto L21;
    L24:
        return r11;
    L21:
        if (r2.length() == 0) goto L24;
        return String.format("%s %s", new Object[]{r0, r2});
    L5:
        String r02 = matchingTrunkCode(r11);
        if (r02 == null) goto L8;
        r2 = r11.substring(r02.length());
        r1 = r02;
        r0 = null;
        goto L9
    L8:
        r2 = r11;
        r0 = null;
        goto L9
    }

    public String matchingAccessCode(String r5) {
        ArrayList<String> r4 = this.intlPrefixes;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        String r2 = r4.get(r1);
        r1 = r1 + 1;
        String r22 = r2;
        if (r5.startsWith(r22) == false) goto L3;
        return r22;
    L7:
        return null;
    }

    public String matchingTrunkCode(String r5) {
        ArrayList<String> r4 = this.trunkPrefixes;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        String r2 = r4.get(r1);
        r1 = r1 + 1;
        String r22 = r2;
        if (r5.startsWith(r22) == false) goto L3;
        return r22;
    L7:
        return null;
    }
}
