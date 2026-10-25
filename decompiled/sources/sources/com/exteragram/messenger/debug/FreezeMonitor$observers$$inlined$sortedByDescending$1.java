package com.exteragram.messenger.debug;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n*L\n1#1,328:1\n281#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class FreezeMonitor$observers$$inlined$sortedByDescending$1<T> implements Comparator {
    public FreezeMonitor$observers$$inlined$sortedByDescending$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues((Integer) ((Pair) r2).getFirst(), (Integer) ((Pair) r1).getFirst());
    }
}
