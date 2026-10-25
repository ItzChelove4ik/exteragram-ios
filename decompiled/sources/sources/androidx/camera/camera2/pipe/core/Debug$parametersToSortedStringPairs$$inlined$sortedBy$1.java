package androidx.camera.camera2.pipe.core;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 Debug.kt\nandroidx/camera/camera2/pipe/core/Debug\n*L\n1#1,102:1\n133#2:103\n*E\n"})
/* loaded from: classes3.dex */
public final class Debug$parametersToSortedStringPairs$$inlined$sortedBy$1<T> implements Comparator {
    public Debug$parametersToSortedStringPairs$$inlined$sortedBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues((String) ((Pair) r1).getFirst(), (String) ((Pair) r2).getFirst());
    }
}
