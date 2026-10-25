package okio.internal;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 ZipFiles.kt\nokio/internal/ZipFilesKt\n*L\n1#1,328:1\n155#2:329\n*E\n"})
/* loaded from: classes5.dex */
public final class ZipFilesKt$buildIndex$$inlined$sortedBy$1<T> implements Comparator {
    public ZipFilesKt$buildIndex$$inlined$sortedBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(((ZipEntry) r1).getCanonicalPath(), ((ZipEntry) r2).getCanonicalPath());
    }
}
