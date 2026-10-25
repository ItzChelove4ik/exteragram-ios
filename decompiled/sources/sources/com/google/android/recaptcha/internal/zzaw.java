package com.google.android.recaptcha.internal;

import kotlin.jvm.functions.Function1;

/* loaded from: classes5.dex */
final /* synthetic */ class zzaw implements Function1 {
    static final /* synthetic */ zzaw zza = null;

    static {
        zza = new zzaw();
    }

    private /* synthetic */ zzaw() {
    }

    @Override // kotlin.jvm.functions.Function1
    public final /* synthetic */ Object invoke(Object r4) {
        zzaju r42 = (zzaju) r4;
        String r3 = zzau.zza(r42);
        String r0 = "REAL";
        switch(r42.ordinal()) {
            case 1: goto L7;
            case 2: goto L6;
            case 3: goto L5;
            case 4: goto L5;
            case 5: goto L5;
            case 6: goto L5;
            case 7: goto L5;
            case 8: goto L5;
            case 9: goto L8;
            case 10: goto L8;
            case 11: goto L5;
            case 12: goto L5;
            case 13: goto L5;
            case 14: goto L5;
            case 15: goto L8;
            case 16: goto L8;
            case 17: goto L5;
            default: goto L4;
        };
    L4:
        r0 = "COLUMN_UNSPECIFIED";
        goto L8
    L5:
        r0 = "INTEGER";
        goto L8
    L6:
        r0 = "BLOB";
        goto L8
    L7:
        r0 = "INTEGER PRIMARY KEY AUTOINCREMENT";
    L8:
        StringBuilder r1 = new StringBuilder((r3.length() + 1) + r0.length());
        r1.append(r3);
        r1.append(" ");
        r1.append(r0);
        return r1.toString();
    }
}
