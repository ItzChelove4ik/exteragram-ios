package androidx.work.impl.utils;

import androidx.work.impl.WorkDatabase;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\t\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u0010\b\u001a\u00020\u0006¢\u0006\u0004\b\t\u0010\nR\u0014\u0010\u0003\u001a\u00020\u00028\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0003\u0010\u000b¨\u0006\f"}, d2 = {"Landroidx/work/impl/utils/IdGenerator;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/WorkDatabase;", "workDatabase", "<init>", "(Landroidx/work/impl/WorkDatabase;)V", _UrlKt.FRAGMENT_ENCODE_SET, "minInclusive", "maxInclusive", "nextJobSchedulerIdWithRange", "(II)I", "Landroidx/work/impl/WorkDatabase;", "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes.dex */
public final class IdGenerator {
    private final WorkDatabase workDatabase;

    public static Integer $r8$lambda$JQr7FK7MZ2bSJyfHKiwoJ9vBWDc(IdGenerator r2, int r3, int r4) {
        int r0 = IdGeneratorKt.access$nextId(r2.workDatabase, "next_job_scheduler_id");
        if (r3 > r0) goto L6;
        if (r0 > r4) goto L6;
        r3 = r0;
    L8:
        return Integer.valueOf(r3);
    L6:
        IdGeneratorKt.access$updatePreference(r2.workDatabase, "next_job_scheduler_id", r3 + 1);
        goto L8
    }

    public IdGenerator(WorkDatabase r1) {
        this.workDatabase = r1;
    }

    public final int nextJobSchedulerIdWithRange(int r3, int r4) {
        return ((Number) this.workDatabase.runInTransaction(new IdGenerator$$ExternalSyntheticLambda0(this, r3, r4))).intValue();
    }
}
