package androidx.room.util;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 SchemaInfoUtil.kt\nandroidx/room/util/SchemaInfoUtilKt\n*L\n1#1,102:1\n257#2:103\n*E\n"})
/* loaded from: classes4.dex */
public final class SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$2<T> implements Comparator {
    public SchemaInfoUtilKt$readIndex$lambda$13$$inlined$sortedBy$2() {
    }

    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues((Integer) ((Map.Entry) r1).getKey(), (Integer) ((Map.Entry) r2).getKey());
    }
}
