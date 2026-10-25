package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

/* loaded from: classes3.dex */
public final class LocaleListCompat {
    private static final LocaleListCompat sEmptyLocaleList = null;
    private final LocaleListInterface mImpl;

    public static class Api24Impl {
        public static LocaleList createLocaleList(Locale... r1) {
            return new LocaleList(r1);
        }
    }

    static {
        sEmptyLocaleList = create(new Locale[0]);
    }

    private LocaleListCompat(LocaleListInterface r1) {
        this.mImpl = r1;
    }

    public static LocaleListCompat create(Locale... r0) {
        return wrap(Api24Impl.createLocaleList(r0));
    }

    public static LocaleListCompat forLanguageTags(String r4) {
        if (r4 == null) goto L12;
        if (r4.isEmpty() == true) goto L12;
        String[] r42 = r4.split(",", -1);
        int r0 = r42.length;
        Locale[] r1 = new Locale[r0];
        int r2 = 0;
    L7:
        if (r2 >= r0) goto L10;
        r1[r2] = Locale.forLanguageTag(r42[r2]);
        r2 = r2 + 1;
        goto L7
    L10:
        return create(r1);
    L12:
        return getEmptyLocaleList();
    }

    public static LocaleListCompat getEmptyLocaleList() {
        return sEmptyLocaleList;
    }

    public static LocaleListCompat wrap(LocaleList r2) {
        return new LocaleListCompat(new LocaleListPlatformWrapper(r2));
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof LocaleListCompat) == true) goto L5;
        return false;
    L5:
        if (this.mImpl.equals(((LocaleListCompat) r2).mImpl) == false) goto L10;
        return true;
    L10:
        return false;
    }

    public Locale get(int r1) {
        return this.mImpl.get(r1);
    }

    public int hashCode() {
        return this.mImpl.hashCode();
    }

    public boolean isEmpty() {
        return this.mImpl.isEmpty();
    }

    public int size() {
        return this.mImpl.size();
    }

    public String toLanguageTags() {
        return this.mImpl.toLanguageTags();
    }

    public String toString() {
        return this.mImpl.toString();
    }
}
