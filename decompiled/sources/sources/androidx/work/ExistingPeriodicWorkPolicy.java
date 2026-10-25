package androidx.work;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Landroidx/work/ExistingPeriodicWorkPolicy;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "(Ljava/lang/String;I)V", "REPLACE", "KEEP", "UPDATE", "CANCEL_AND_REENQUEUE", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public enum ExistingPeriodicWorkPolicy extends Enum<ExistingPeriodicWorkPolicy> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ ExistingPeriodicWorkPolicy[] $VALUES = null;
    public static final ExistingPeriodicWorkPolicy CANCEL_AND_REENQUEUE = null;
    public static final ExistingPeriodicWorkPolicy KEEP = null;

    @Deprecated(message = "Deprecated in favor of the UPDATE policy. UPDATE policy has very similar behavior: next run of the worker with the same unique name, going to have new specification. However, UPDATE has better defaults: unlike REPLACE policy UPDATE won't cancel the worker if it is currently running and new worker specification will be used only on the next run. Also it preserves original enqueue time, so unlike REPLACE period isn't reset. If you want to preserve previous behavior, CANCEL_AND_REENQUEUE should be used.", replaceWith = @ReplaceWith(expression = "UPDATE", imports = {}))
    public static final ExistingPeriodicWorkPolicy REPLACE = null;
    public static final ExistingPeriodicWorkPolicy UPDATE = null;

    private static final /* synthetic */ ExistingPeriodicWorkPolicy[] $values() {
        return new ExistingPeriodicWorkPolicy[]{REPLACE, KEEP, UPDATE, CANCEL_AND_REENQUEUE};
    }

    static {
        REPLACE = new ExistingPeriodicWorkPolicy("REPLACE", 0);
        KEEP = new ExistingPeriodicWorkPolicy("KEEP", 1);
        UPDATE = new ExistingPeriodicWorkPolicy("UPDATE", 2);
        CANCEL_AND_REENQUEUE = new ExistingPeriodicWorkPolicy("CANCEL_AND_REENQUEUE", 3);
        ExistingPeriodicWorkPolicy[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    ExistingPeriodicWorkPolicy(String r1, int r2) {
    }

    public static ExistingPeriodicWorkPolicy valueOf(String r1) {
        return (ExistingPeriodicWorkPolicy) Enum.valueOf(ExistingPeriodicWorkPolicy.class, r1);
    }

    public static ExistingPeriodicWorkPolicy[] values() {
        return (ExistingPeriodicWorkPolicy[]) $VALUES.clone();
    }
}
