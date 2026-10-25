package j$.util;

import j$.time.Instant;
import java.util.Date;

/* loaded from: classes2.dex */
public final /* synthetic */ class DesugarDate {
    public static Date from(Instant r3) {
        return new Date(r3.toEpochMilli());
    L4:
        e = move-exception;
        throw new IllegalArgumentException(e);
    }
}
