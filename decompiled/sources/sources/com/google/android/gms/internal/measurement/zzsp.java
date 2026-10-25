package com.google.android.gms.internal.measurement;

import c.f$$ExternalSyntheticBUOutline1;
import com.google.common.base.Joiner;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/* loaded from: classes4.dex */
public abstract class zzsp {
    private static final Pattern zza = null;

    static {
        zza = Pattern.compile("(\\w+).*");
    }

    public static String zza(String r2) {
        Matcher r0 = zza.matcher(r2);
        if (r0.matches() == true) goto L5;
        f$$ExternalSyntheticBUOutline1.m("Invalid fragment spec: ".concat(String.valueOf(r2)));
        return null;
    L5:
        return r0.group(1);
    }

    public static String zzb(List r1) {
        if (r1.isEmpty() == false) goto L7;
        return null;
    L7:
        return "transform=".concat(String.valueOf(Joiner.on("+").join(r1)));
    }
}
