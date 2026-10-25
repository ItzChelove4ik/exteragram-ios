package androidx.work.impl;

import androidx.work.impl.model.WorkGenerationalId;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\u00072\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0012\u0010\n\u001a\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u00020\u0006H\u0016J\u0016\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u000b2\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u0006H\u0016R\u001a\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Landroidx/work/impl/StartStopTokensImpl;", "Landroidx/work/impl/StartStopTokens;", "<init>", "()V", "runs", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/work/impl/model/WorkGenerationalId;", "Landroidx/work/impl/StartStopToken;", "tokenFor", "id", "remove", _UrlKt.FRAGMENT_ENCODE_SET, "workSpecId", _UrlKt.FRAGMENT_ENCODE_SET, "contains", _UrlKt.FRAGMENT_ENCODE_SET, "work-runtime_release"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nStartStopToken.kt\nKotlin\n*S Kotlin\n*F\n+ 1 StartStopToken.kt\nandroidx/work/impl/StartStopTokensImpl\n+ 2 Maps.kt\nkotlin/collections/MapsKt__MapsKt\n+ 3 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,104:1\n384#2,7:105\n490#2,7:112\n1869#3,2:119\n*S KotlinDebug\n*F\n+ 1 StartStopToken.kt\nandroidx/work/impl/StartStopTokensImpl\n*L\n65#1:105,7\n73#1:112,7\n74#1:119,2\n*E\n"})
/* loaded from: classes.dex */
final class StartStopTokensImpl implements StartStopTokens {
    private final Map<WorkGenerationalId, StartStopToken> runs;

    public StartStopTokensImpl() {
        this.runs = new LinkedHashMap();
    }

    @Override // androidx.work.impl.StartStopTokens
    public boolean contains(WorkGenerationalId r1) {
        return this.runs.containsKey(r1);
    }

    @Override // androidx.work.impl.StartStopTokens
    public StartStopToken remove(WorkGenerationalId r1) {
        return this.runs.remove(r1);
    }

    @Override // androidx.work.impl.StartStopTokens
    public List<StartStopToken> remove(String r5) {
        Map<WorkGenerationalId, StartStopToken> r0 = this.runs;
        LinkedHashMap r1 = new LinkedHashMap();
        Iterator<Map.Entry<WorkGenerationalId, StartStopToken>> r02 = r0.entrySet().iterator();
    L4:
        if (r02.hasNext() == false) goto L8;
        Map.Entry<WorkGenerationalId, StartStopToken> r2 = r02.next();
        if (Intrinsics.areEqual(r2.getKey().getWorkSpecId(), r5) == false) goto L4;
        r1.put(r2.getKey(), r2.getValue());
        goto L4
    L8:
        Iterator r52 = r1.keySet().iterator();
    L10:
        if (r52.hasNext() == false) goto L13;
        WorkGenerationalId r03 = (WorkGenerationalId) r52.next();
        this.runs.remove(r03);
        goto L10
    L13:
        return CollectionsKt.toList(r1.values());
    }

    @Override // androidx.work.impl.StartStopTokens
    public StartStopToken tokenFor(WorkGenerationalId r2) {
        Map<WorkGenerationalId, StartStopToken> r1 = this.runs;
        StartStopToken r0 = r1.get(r2);
        if (r0 != null) goto L6;
        r0 = new StartStopToken(r2);
        r1.put(r2, r0);
    L6:
        return r0;
    }
}
