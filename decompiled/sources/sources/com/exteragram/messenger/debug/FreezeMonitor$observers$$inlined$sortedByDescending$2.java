package com.exteragram.messenger.debug;

import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor\n*L\n1#1,328:1\n284#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class FreezeMonitor$observers$$inlined$sortedByDescending$2<T> implements Comparator {
    public FreezeMonitor$observers$$inlined$sortedByDescending$2() {
    }

    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues((Integer) ((Map.Entry) r2).getValue(), (Integer) ((Map.Entry) r1).getValue());
    }
}
