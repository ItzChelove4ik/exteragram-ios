package com.exteragram.messenger.debug;

import com.exteragram.messenger.debug.FreezeMonitor;
import java.util.Comparator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$thenByDescending$1\n+ 2 FreezeMonitor.kt\ncom/exteragram/messenger/debug/FreezeMonitor$Watchdog\n*L\n1#1,328:1\n486#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1<T> implements Comparator {
    final /* synthetic */ FreezeMonitor.Freeze $freeze$inlined;
    final /* synthetic */ Comparator $this_thenByDescending;

    public FreezeMonitor$Watchdog$record$$inlined$thenByDescending$1(Comparator r1, FreezeMonitor.Freeze r2) {
        this.$this_thenByDescending = r1;
        this.$freeze$inlined = r2;
    }

    @Override // java.util.Comparator
    public final int compare(T r3, T r4) {
        Integer r0 = 0;
        int r1 = this.$this_thenByDescending.compare(r3, r4);
        if (r1 == 0) goto L5;
        return r1;
    L5:
        Integer r42 = this.$freeze$inlined.getHotDepth().get(((Map.Entry) r4).getKey());
        if (r42 != null) goto L9;
        r42 = r0;
    L9:
        Integer r2 = this.$freeze$inlined.getHotDepth().get(((Map.Entry) r3).getKey());
        if (r2 == null) goto L13;
        r0 = r2;
    L13:
        return ComparisonsKt.compareValues(r42, r0);
    }
}
