package com.exteragram.messenger.debug;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor$Sampler\n*L\n1#1,328:1\n663#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class LoadMonitor$Sampler$buildReport$$inlined$sortedByDescending$1<T> implements Comparator {
    public LoadMonitor$Sampler$buildReport$$inlined$sortedByDescending$1() {
    }

    @Override // java.util.Comparator
    public final int compare(T r3, T r4) {
        return ComparisonsKt.compareValues(Long.valueOf(ArraysKt.sum((long[]) ((Map.Entry) r4).getValue())), Long.valueOf(ArraysKt.sum((long[]) ((Map.Entry) r3).getValue())));
    }
}
