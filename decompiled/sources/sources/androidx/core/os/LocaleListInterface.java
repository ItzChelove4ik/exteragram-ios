package androidx.core.os;

import java.util.Locale;

/* loaded from: classes3.dex */
interface LocaleListInterface {
    Locale get(int r1);

    Object getLocaleList();

    boolean isEmpty();

    int size();

    String toLanguageTags();
}
