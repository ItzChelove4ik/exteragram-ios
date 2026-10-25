package androidx.core.os;

import android.content.res.Configuration;
import android.os.LocaleList;

/* loaded from: classes3.dex */
public abstract class ConfigurationCompat {

    public static class Api24Impl {
        public static LocaleList getLocales(Configuration r0) {
            return r0.getLocales();
        }
    }

    public static LocaleListCompat getLocales(Configuration r0) {
        return LocaleListCompat.wrap(Api24Impl.getLocales(r0));
    }
}
