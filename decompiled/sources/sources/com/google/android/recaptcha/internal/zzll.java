package com.google.android.recaptcha.internal;

import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public final class zzll {
    public /* synthetic */ zzll(DefaultConstructorMarker r1) {
    }

    public static final /* synthetic */ int zza(zzll r0, String r1) {
        return zzc("18.9.2");
    }

    public static final /* synthetic */ String zzb(zzll r0, String r1) {
        return zzd("18.9.2");
    }

    private static final int zzc(String r6) {
        return Integer.parseInt(StringsKt.substringBefore$default(StringsKt.replace$default("18.9.2", ".", _UrlKt.FRAGMENT_ENCODE_SET, false, 4, null), "-", null, 2, null));
    }

    private static final String zzd(String r2) {
        return "cesdb".concat(String.valueOf(StringsKt.substringAfter("18.9.2", "-", _UrlKt.FRAGMENT_ENCODE_SET)));
    }
}
