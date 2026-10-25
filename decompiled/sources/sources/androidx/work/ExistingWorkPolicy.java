package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/work/ExistingWorkPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "REPLACE", "KEEP", "APPEND", "APPEND_OR_REPLACE", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes4.dex */
public enum ExistingWorkPolicy extends Enum<ExistingWorkPolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ExistingWorkPolicy[] $VALUES = null;
    public static final ExistingWorkPolicy APPEND = null;
    public static final ExistingWorkPolicy APPEND_OR_REPLACE = null;
    public static final ExistingWorkPolicy KEEP = null;
    public static final ExistingWorkPolicy REPLACE = null;

    private static final /* synthetic */ ExistingWorkPolicy[] $values() {
        return new ExistingWorkPolicy[]{REPLACE, KEEP, APPEND, APPEND_OR_REPLACE};
    }

    static {
        REPLACE = new ExistingWorkPolicy("REPLACE", 0);
        KEEP = new ExistingWorkPolicy("KEEP", 1);
        APPEND = new ExistingWorkPolicy("APPEND", 2);
        APPEND_OR_REPLACE = new ExistingWorkPolicy("APPEND_OR_REPLACE", 3);
        ExistingWorkPolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ExistingWorkPolicy(String r1, int r2) {
    }

    public static ExistingWorkPolicy valueOf(String r1) {
        return (ExistingWorkPolicy) Enum.valueOf(ExistingWorkPolicy.class, r1);
    }

    public static ExistingWorkPolicy[] values() {
        return (ExistingWorkPolicy[]) $VALUES.clone();
    }
}
