package j$.time.format;

import java.util.Comparator;
import java.util.Map;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final class u implements Comparator {
    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return ((String) ((Map.Entry) r2).getKey()).length() - ((String) ((Map.Entry) r1).getKey()).length();
    }
}
