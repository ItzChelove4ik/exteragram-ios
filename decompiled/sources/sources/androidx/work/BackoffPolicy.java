package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/work/BackoffPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "EXPONENTIAL", "LINEAR", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public enum BackoffPolicy extends Enum<BackoffPolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ BackoffPolicy[] $VALUES = null;
    public static final BackoffPolicy EXPONENTIAL = null;
    public static final BackoffPolicy LINEAR = null;

    private static final /* synthetic */ BackoffPolicy[] $values() {
        return new BackoffPolicy[]{EXPONENTIAL, LINEAR};
    }

    static {
        EXPONENTIAL = new BackoffPolicy("EXPONENTIAL", 0);
        LINEAR = new BackoffPolicy("LINEAR", 1);
        BackoffPolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    BackoffPolicy(String r1, int r2) {
    }

    public static BackoffPolicy valueOf(String r1) {
        return (BackoffPolicy) Enum.valueOf(BackoffPolicy.class, r1);
    }

    public static BackoffPolicy[] values() {
        return (BackoffPolicy[]) $VALUES.clone();
    }
}
