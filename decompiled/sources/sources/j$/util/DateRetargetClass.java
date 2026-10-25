package j$.util;

import j$.time.Instant;
import java.util.Date;

/* compiled from: r8-map-id-d770cecce19e13e7204002895f1f3846e7cae954646f7ef2982ee0cbc1d920c0 */
/* loaded from: classes2.dex */
public final /* synthetic */ class DateRetargetClass {
    public static Instant toInstant(Date r2) {
        return Instant.ofEpochMilli(r2.getTime());
    }
}
