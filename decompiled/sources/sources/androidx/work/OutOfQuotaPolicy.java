package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0005\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005¨\u0006\u0006"}, d2 = {"Landroidx/work/OutOfQuotaPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "RUN_AS_NON_EXPEDITED_WORK_REQUEST", "DROP_WORK_REQUEST", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public enum OutOfQuotaPolicy extends Enum<OutOfQuotaPolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ OutOfQuotaPolicy[] $VALUES = null;
    public static final OutOfQuotaPolicy DROP_WORK_REQUEST = null;
    public static final OutOfQuotaPolicy RUN_AS_NON_EXPEDITED_WORK_REQUEST = null;

    private static final /* synthetic */ OutOfQuotaPolicy[] $values() {
        return new OutOfQuotaPolicy[]{RUN_AS_NON_EXPEDITED_WORK_REQUEST, DROP_WORK_REQUEST};
    }

    static {
        RUN_AS_NON_EXPEDITED_WORK_REQUEST = new OutOfQuotaPolicy("RUN_AS_NON_EXPEDITED_WORK_REQUEST", 0);
        DROP_WORK_REQUEST = new OutOfQuotaPolicy("DROP_WORK_REQUEST", 1);
        OutOfQuotaPolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    OutOfQuotaPolicy(String r1, int r2) {
    }

    public static OutOfQuotaPolicy valueOf(String r1) {
        return (OutOfQuotaPolicy) Enum.valueOf(OutOfQuotaPolicy.class, r1);
    }

    public static OutOfQuotaPolicy[] values() {
        return (OutOfQuotaPolicy[]) $VALUES.clone();
    }
}
