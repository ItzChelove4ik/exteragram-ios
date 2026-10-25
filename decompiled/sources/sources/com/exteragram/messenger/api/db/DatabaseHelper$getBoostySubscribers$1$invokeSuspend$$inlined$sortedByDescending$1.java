package com.exteragram.messenger.api.db;

import com.exteragram.messenger.api.dto.BoostySubscriberDTO;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareByDescending$1\n+ 2 DatabaseHelper.kt\ncom/exteragram/messenger/api/db/DatabaseHelper$getBoostySubscribers$1\n*L\n1#1,328:1\n128#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class DatabaseHelper$getBoostySubscribers$1$invokeSuspend$$inlined$sortedByDescending$1<T> implements Comparator {
    public DatabaseHelper$getBoostySubscribers$1$invokeSuspend$$inlined$sortedByDescending$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(((BoostySubscriberDTO) r2).getTotalAmountRub(), ((BoostySubscriberDTO) r1).getTotalAmountRub());
    }
}
