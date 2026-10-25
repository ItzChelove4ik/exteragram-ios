package org.telegram.messenger;

/* loaded from: classes5.dex */
public abstract /* synthetic */ class MessagesController$PeerColors$$ExternalSyntheticBackport0 {
    public static /* synthetic */ int m(String r4, int r5) {
        if (r4.length() > 1) goto L5;
    L7:
        long r0 = Long.parseLong(r4, r5);
        if ((4294967295L & r0) != r0) goto L12;
        return (int) r0;
    L12:
        throw new NumberFormatException("Input " + r4 + " in base " + r5 + " is not in the range of an unsigned integer");
    L5:
        if (r4.charAt(0) != '+') goto L7;
        r4 = r4.substring(1);
        goto L7
    }
}
