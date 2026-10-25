package com.google.mlkit.common.sdkinternal;

import android.content.Context;
import android.text.TextUtils;
import com.google.android.gms.common.internal.GmsLogger;
import com.google.android.gms.common.util.PlatformVersion;
import java.util.Locale;
import okhttp3.internal.url._UrlKt;

/* loaded from: classes5.dex */
public abstract class CommonUtils {
    private static final GmsLogger zza = null;

    static {
        zza = new GmsLogger("CommonUtils", _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public static String getAppVersion(Context r2) {
        return String.valueOf(r2.getPackageManager().getPackageInfo(r2.getPackageName(), 0).versionCode);
    L4:
        e = move-exception;
        zza.e("CommonUtils", "Exception thrown when trying to get app version ".concat(e.toString()));
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static String languageTagFromLocale(Locale r3) {
        if (PlatformVersion.isAtLeastLollipop() == true) goto L5;
        StringBuilder r0 = new StringBuilder(r3.getLanguage());
        if (TextUtils.isEmpty(r3.getCountry()) == true) goto L10;
        r0.append("-");
        r0.append(r3.getCountry());
    L10:
        if (TextUtils.isEmpty(r3.getVariant()) == true) goto L13;
        r0.append("-");
        r0.append(r3.getVariant());
    L13:
        return r0.toString();
    L5:
        return r3.toLanguageTag();
    }
}
