package org.simplifiles.internal.io;

import j$.nio.file.Path;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 3, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 FileTreeCopier.kt\norg/simplifiles/internal/io/FileTreeCopier\n*L\n1#1,328:1\n46#2:329\n*E\n"})
/* loaded from: classes5.dex */
public final class FileTreeCopier$copyDirectory$lambda$0$$inlined$sortedBy$1<T> implements Comparator {
    public FileTreeCopier$copyDirectory$lambda$0$$inlined$sortedBy$1() {
    }

    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(Integer.valueOf(((Path) r1).getNameCount()), Integer.valueOf(((Path) r2).getNameCount()));
    }
}
