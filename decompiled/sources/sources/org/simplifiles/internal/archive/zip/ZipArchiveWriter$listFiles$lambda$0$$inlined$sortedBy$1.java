package org.simplifiles.internal.archive.zip;

import j$.nio.file.Path;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 ZipArchiveWriter.kt\norg/simplifiles/internal/archive/zip/ZipArchiveWriter\n*L\n1#1,328:1\n389#2:329\n*E\n"})
/* loaded from: classes5.dex */
public final class ZipArchiveWriter$listFiles$lambda$0$$inlined$sortedBy$1<T> implements Comparator {
    final /* synthetic */ Path $normalizedRoot$inlined;

    public ZipArchiveWriter$listFiles$lambda$0$$inlined$sortedBy$1(Path r1) {
        this.$normalizedRoot$inlined = r1;
    }

    @Override // java.util.Comparator
    public final int compare(T r2, T r3) {
        Path r0 = this.$normalizedRoot$inlined;
        String r22 = r0.relativize((Path) r2).toString();
        Path r1 = this.$normalizedRoot$inlined;
        return ComparisonsKt.compareValues(r22, r1.relativize((Path) r3).toString());
    }
}
