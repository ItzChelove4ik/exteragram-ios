package org.telegram.PhoneFormat;

/* loaded from: classes.dex */
public class PhoneRule {
    public int byte8;
    public int flag12;
    public int flag13;
    public String format;
    public boolean hasIntlPrefix;
    public boolean hasTrunkPrefix;
    public int maxLen;
    public int maxVal;
    public int minVal;
    public int otherFlag;
    public int prefixLen;

    public PhoneRule() {
    }

    public String format(String r13, String r14, String r15) {
        StringBuilder r0 = new StringBuilder(20);
        int r2 = 0;
        boolean r3 = false;
        int r4 = 0;
        boolean r5 = false;
        boolean r6 = false;
    L4:
        if (r2 >= this.format.length()) goto L43;
        char r7 = this.format.charAt(r2);
        if (r7 == '#') goto L38;
        if (r7 == '(') goto L19;
        if (r7 == 'c') goto L15;
        if (r7 != 'n') goto L22;
        if (r15 == null) goto L14;
        r0.append(r15);
    L14:
        r5 = true;
    L42:
        r2 = r2 + 1;
    L22:
        if (r7 != ' ') goto L31;
        if (r2 <= 0) goto L31;
        int r11 = r2 - 1;
        if (this.format.charAt(r11) != 'n') goto L28;
        if (r15 == null) goto L42;
    L28:
        if (this.format.charAt(r11) != 'c') goto L31;
        if (r14 == null) goto L42;
    L31:
        if (r4 < r13.length()) goto L34;
        if (r6 == false) goto L42;
        if (r7 != ')') goto L42;
    L34:
        r0.append(this.format.substring(r2, r2 + 1));
        if (r7 != ')') goto L42;
        r6 = false;
        goto L42
    L15:
        if (r14 == null) goto L17;
        r0.append(r14);
    L17:
        r3 = true;
        goto L42
    L19:
        if (r4 >= r13.length()) goto L22;
        r6 = true;
        goto L22
    L38:
        if (r4 >= r13.length()) goto L40;
        int r72 = r4 + 1;
        r0.append(r13.substring(r4, r72));
        r4 = r72;
        goto L42
    L40:
        if (r6 == false) goto L42;
        r0.append(" ");
        goto L42
    L43:
        if (r14 == null) goto L46;
        if (r3 == true) goto L46;
        r0.insert(0, r14.concat(" "));
    L50:
        return r0.toString();
    L46:
        if (r15 == null) goto L50;
        if (r5 == true) goto L50;
        r0.insert(0, r15);
        goto L50
    }
}
