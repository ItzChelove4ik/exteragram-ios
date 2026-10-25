package org.telegram.PhoneFormat;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes.dex */
public class RuleSet {
    public static Pattern pattern;
    public boolean hasRuleWithIntlPrefix;
    public boolean hasRuleWithTrunkPrefix;
    public int matchLen;
    public ArrayList<PhoneRule> rules;

    static {
        pattern = Pattern.compile("[0-9]+");
    }

    public RuleSet() {
        this.rules = new ArrayList();
    }

    public String format(String r10, String r11, String r12, boolean r13) {
        int r0 = r10.length();
        int r1 = this.matchLen;
        if (r0 < r1) goto L68;
        int r02 = 0;
        Matcher r14 = pattern.matcher(r10.substring(0, r1));
        if (r14.find() == false) goto L7;
        int r15 = Integer.parseInt(r14.group(0));
    L8:
        ArrayList<PhoneRule> r3 = this.rules;
        int r4 = r3.size();
        int r5 = 0;
    L9:
        if (r5 >= r4) goto L39;
        PhoneRule r6 = r3.get(r5);
        r5 = r5 + 1;
        PhoneRule r62 = r6;
        if (r15 < r62.minVal) goto L9;
        if (r15 > r62.maxVal) goto L9;
        if (r10.length() > r62.maxLen) goto L9;
        if (r13 == true) goto L17;
        if (r12 != null) goto L31;
        if (r11 != null) goto L31;
    L38:
        return r62.format(r10, r11, r12);
    L31:
        if (r12 != null) goto L33;
    L34:
        if (r11 == null) goto L9;
        if ((r62.flag12 & 2) == 0) goto L9;
    L33:
        if ((r62.flag12 & 1) != 0) goto L38;
    L17:
        int r7 = r62.flag12;
        if ((r7 & 3) != 0) goto L21;
        if (r12 != null) goto L21;
        if (r11 != null) goto L21;
    L28:
        return r62.format(r10, r11, r12);
    L21:
        if (r12 != null) goto L23;
    L24:
        if (r11 == null) goto L9;
        if ((r7 & 2) == 0) goto L9;
    L23:
        if ((r7 & 1) != 0) goto L28;
    L39:
        if (r13 == true) goto L68;
        if (r11 == null) goto L54;
        ArrayList<PhoneRule> r9 = this.rules;
        int r132 = r9.size();
    L42:
        if (r02 >= r132) goto L68;
        PhoneRule r32 = r9.get(r02);
        r02 = r02 + 1;
        PhoneRule r33 = r32;
        if (r15 < r33.minVal) goto L42;
        if (r15 > r33.maxVal) goto L42;
        if (r10.length() > r33.maxLen) goto L42;
        if (r12 == null) goto L53;
        if ((r33.flag12 & 1) == 0) goto L42;
    L53:
        return r33.format(r10, r11, r12);
    L54:
        if (r12 == null) goto L68;
        ArrayList<PhoneRule> r92 = this.rules;
        int r133 = r92.size();
    L56:
        if (r02 >= r133) goto L68;
        PhoneRule r34 = r92.get(r02);
        r02 = r02 + 1;
        PhoneRule r35 = r34;
        if (r15 < r35.minVal) goto L56;
        if (r15 > r35.maxVal) goto L56;
        if (r10.length() > r35.maxLen) goto L56;
        if (r11 == null) goto L67;
        if ((r35.flag12 & 2) == 0) goto L56;
    L67:
        return r35.format(r10, r11, r12);
    L7:
        r15 = 0;
    L68:
        return null;
    }
}
