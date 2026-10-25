package androidx.core.os;

import android.os.LocaleList;
import java.util.Locale;

/* loaded from: classes3.dex */
final class LocaleListPlatformWrapper implements LocaleListInterface {
    private final LocaleList mLocaleList;

    public LocaleListPlatformWrapper(Object r1) {
        this.mLocaleList = (LocaleList) r1;
    }

    public boolean equals(Object r1) {
        return this.mLocaleList.equals(((LocaleListInterface) r1).getLocaleList());
    }

    @Override // androidx.core.os.LocaleListInterface
    public Locale get(int r1) {
        return this.mLocaleList.get(r1);
    }

    @Override // androidx.core.os.LocaleListInterface
    public Object getLocaleList() {
        return this.mLocaleList;
    }

    public int hashCode() {
        return this.mLocaleList.hashCode();
    }

    @Override // androidx.core.os.LocaleListInterface
    public boolean isEmpty() {
        return this.mLocaleList.isEmpty();
    }

    @Override // androidx.core.os.LocaleListInterface
    public int size() {
        return this.mLocaleList.size();
    }

    @Override // androidx.core.os.LocaleListInterface
    public String toLanguageTags() {
        return this.mLocaleList.toLanguageTags();
    }

    public String toString() {
        return this.mLocaleList.toString();
    }
}
