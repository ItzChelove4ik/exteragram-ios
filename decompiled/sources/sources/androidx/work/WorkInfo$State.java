package androidx.work;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0010\u0010\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004R\u0011\u0010\u0006\u001a\u00020\u00058F¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\r¨\u0006\u000e"}, d2 = {"androidx/work/WorkInfo$State", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/WorkInfo$State;", "<init>", "(Ljava/lang/String;I)V", _UrlKt.FRAGMENT_ENCODE_SET, "isFinished", "()Z", "ENQUEUED", "RUNNING", "SUCCEEDED", "FAILED", "BLOCKED", "CANCELLED", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public enum WorkInfo$State extends Enum<WorkInfo$State> {
    private static final /* synthetic */ EnumEntries $ENTRIES = null;
    private static final /* synthetic */ WorkInfo$State[] $VALUES = null;
    public static final WorkInfo$State BLOCKED = null;
    public static final WorkInfo$State CANCELLED = null;
    public static final WorkInfo$State ENQUEUED = null;
    public static final WorkInfo$State FAILED = null;
    public static final WorkInfo$State RUNNING = null;
    public static final WorkInfo$State SUCCEEDED = null;

    private static final /* synthetic */ WorkInfo$State[] $values() {
        return new WorkInfo$State[]{ENQUEUED, RUNNING, SUCCEEDED, FAILED, BLOCKED, CANCELLED};
    }

    static {
        ENQUEUED = new WorkInfo$State("ENQUEUED", 0);
        RUNNING = new WorkInfo$State("RUNNING", 1);
        SUCCEEDED = new WorkInfo$State("SUCCEEDED", 2);
        FAILED = new WorkInfo$State("FAILED", 3);
        BLOCKED = new WorkInfo$State("BLOCKED", 4);
        CANCELLED = new WorkInfo$State("CANCELLED", 5);
        WorkInfo$State[] r0 = $values();
        $VALUES = r0;
        $ENTRIES = EnumEntriesKt.enumEntries(r0);
    }

    WorkInfo$State(String r1, int r2) {
    }

    public static WorkInfo$State valueOf(String r1) {
        return (WorkInfo$State) Enum.valueOf(WorkInfo$State.class, r1);
    }

    public static WorkInfo$State[] values() {
        return (WorkInfo$State[]) $VALUES.clone();
    }

    public final boolean isFinished() {
        if (this != SUCCEEDED) goto L5;
        return true;
    L5:
        if (this != FAILED) goto L7;
        return true;
    L7:
        if (this == CANCELLED) goto L14;
        return false;
    L14:
        return true;
    }
}
