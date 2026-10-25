package com.exteragram.messenger.debug;

import java.io.File;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 LoadMonitor.kt\ncom/exteragram/messenger/debug/LoadMonitor\n*L\n1#1,328:1\n418#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class LoadMonitor$writeReport$$inlined$sortedByDescending$1<T> implements Comparator {
    public LoadMonitor$writeReport$$inlined$sortedByDescending$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r3, T r4) {
        return ComparisonsKt.compareValues(Long.valueOf(((File) r4).lastModified()), Long.valueOf(((File) r3).lastModified()));
    }
}
