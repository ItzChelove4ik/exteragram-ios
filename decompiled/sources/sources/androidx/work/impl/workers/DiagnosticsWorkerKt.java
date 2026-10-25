package androidx.work.impl.workers;

import androidx.work.Logger;
import androidx.work.impl.model.SystemIdInfo;
import androidx.work.impl.model.SystemIdInfoDao;
import androidx.work.impl.model.WorkNameDao;
import androidx.work.impl.model.WorkSpec;
import androidx.work.impl.model.WorkSpecKt;
import androidx.work.impl.model.WorkTagDao;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0007\u001a5\u0010\n\u001a\u00020\t2\u0006\u0010\u0001\u001a\u00020\u00002\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000b\u001a1\u0010\u0011\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0010\u001a\u00020\tH\u0002¢\u0006\u0004\b\u0011\u0010\u0012\"\u0014\u0010\u0013\u001a\u00020\t8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Landroidx/work/impl/model/WorkNameDao;", "workNameDao", "Landroidx/work/impl/model/WorkTagDao;", "workTagDao", "Landroidx/work/impl/model/SystemIdInfoDao;", "systemIdInfoDao", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkSpec;", "workSpecs", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecRows", "(Landroidx/work/impl/model/WorkNameDao;Landroidx/work/impl/model/WorkTagDao;Landroidx/work/impl/model/SystemIdInfoDao;Ljava/util/List;)Ljava/lang/String;", "workSpec", "name", _UrlKt.FRAGMENT_ENCODE_SET, "systemId", "tags", "workSpecRow", "(Landroidx/work/impl/model/WorkSpec;Ljava/lang/String;Ljava/lang/Integer;Ljava/lang/String;)Ljava/lang/String;", "TAG", "Ljava/lang/String;", "work-runtime_release"}, k = 2, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nDiagnosticsWorker.kt\nKotlin\n*S Kotlin\n*F\n+ 1 DiagnosticsWorker.kt\nandroidx/work/impl/workers/DiagnosticsWorkerKt\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,85:1\n1869#2,2:86\n*S KotlinDebug\n*F\n+ 1 DiagnosticsWorker.kt\nandroidx/work/impl/workers/DiagnosticsWorkerKt\n*L\n74#1:86,2\n*E\n"})
/* loaded from: classes4.dex */
public abstract class DiagnosticsWorkerKt {
    private static final String TAG = null;

    static {
        TAG = Logger.tagWithPrefix("DiagnosticsWrkr");
    }

    public static final /* synthetic */ String access$getTAG$p() {
        return TAG;
    }

    public static final /* synthetic */ String access$workSpecRows(WorkNameDao r0, WorkTagDao r1, SystemIdInfoDao r2, List r3) {
        return workSpecRows(r0, r1, r2, r3);
    }

    private static final String workSpecRow(WorkSpec r3, String r4, Integer r5, String r6) {
        return "\n" + r3.id + "\t " + r3.workerClassName + "\t " + r5 + "\t " + r3.state.name() + "\t " + r4 + "\t " + r6 + '\t';
    }

    private static final String workSpecRows(WorkNameDao r18, WorkTagDao r19, SystemIdInfoDao r20, List<WorkSpec> r21) {
        StringBuilder r0 = new StringBuilder("\n Id \t Class Name\t Job Id\t State\t Unique Name\t Tags\t");
        Iterator<T> r1 = r21.iterator();
    L4:
        if (r1.hasNext() == false) goto L11;
        WorkSpec r2 = (WorkSpec) r1.next();
        SystemIdInfo r3 = r20.getSystemIdInfo(WorkSpecKt.generationalId(r2));
        if (r3 == null) goto L8;
        Integer r32 = Integer.valueOf(r3.systemId);
    L9:
        r0.append(workSpecRow(r2, CollectionsKt.joinToString$default(r18.getNamesForWorkSpecId(r2.id), ",", null, null, 0, null, null, 62, null), r32, CollectionsKt.joinToString$default(r19.getTagsForWorkSpecId(r2.id), ",", null, null, 0, null, null, 62, null)));
        goto L4
    L8:
        r32 = null;
        goto L9
    L11:
        return r0.toString();
    }
}
