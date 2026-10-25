package org.telegram.ui.Components;

/* loaded from: classes7.dex */
public abstract /* synthetic */ class ChatActivityEnterView$$ExternalSyntheticBackport0 {
    public static /* synthetic */ String m(String r4) {
        int r0 = r4.length();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L8;
        int r2 = r4.codePointAt(r1);
        if (Character.isWhitespace(r2) == false) goto L8;
        r1 = r1 + Character.charCount(r2);
    L8:
        if (r0 <= r1) goto L14;
        int r22 = Character.codePointBefore(r4, r0);
        if (Character.isWhitespace(r22) == false) goto L14;
        r0 = r0 - Character.charCount(r22);
    L14:
        return r4.substring(r1, r0);
    }
}
