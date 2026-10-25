package androidx.room.util;

import androidx.room.util.TableInfo;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 TableInfo.kt\nandroidx/room/util/TableInfoKt\n*L\n1#1,102:1\n194#2:103\n*E\n"})
/* loaded from: classes4.dex */
public final class TableInfoKt$toStringCommon$$inlined$sortedBy$2<T> implements Comparator {
    public TableInfoKt$toStringCommon$$inlined$sortedBy$2() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(((TableInfo.Index) r1).name, ((TableInfo.Index) r2).name);
    }
}
