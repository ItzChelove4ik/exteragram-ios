package com.exteragram.messenger.icons;

import java.io.File;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 DecodedIconStore.kt\ncom/exteragram/messenger/icons/DecodedIconStore\n*L\n1#1,328:1\n135#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class DecodedIconStore$prune$$inlined$sortBy$1<T> implements Comparator {
    public DecodedIconStore$prune$$inlined$sortBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(Long.valueOf(((File) r1).lastModified()), Long.valueOf(((File) r2).lastModified()));
    }
}
