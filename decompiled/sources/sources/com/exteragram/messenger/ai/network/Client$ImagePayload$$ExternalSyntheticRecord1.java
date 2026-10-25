package com.exteragram.messenger.ai.network;

/* loaded from: classes4.dex */
public abstract /* synthetic */ class Client$ImagePayload$$ExternalSyntheticRecord1 {
    public static /* synthetic */ String m(Object[] r2, Class r3, String r4) {
        int r1 = 0;
        if (r4.length() != 0) goto L5;
        String[] r42 = new String[0];
    L6:
        StringBuilder r0 = new StringBuilder();
        r0.append(r3.getSimpleName());
        r0.append("[");
    L8:
        if (r1 >= r42.length) goto L13;
        r0.append(r42[r1]);
        r0.append("=");
        r0.append(r2[r1]);
        if (r1 == (r42.length - 1)) goto L12;
        r0.append(", ");
    L12:
        r1 = r1 + 1;
        goto L8
    L13:
        r0.append("]");
        return r0.toString();
    L5:
        r42 = r4.split(";");
        goto L6
    }
}
