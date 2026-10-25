package androidx.camera.camera2.impl;

import android.util.Size;
import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;

@Metadata(k = 3, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 MeteringRepeating.kt\nandroidx/camera/camera2/impl/MeteringRepeatingKt\n*L\n1#1,102:1\n229#2:103\n*E\n"})
/* loaded from: classes3.dex */
public final class MeteringRepeatingKt$getProperPreviewSize$$inlined$sortBy$1<T> implements Comparator {
    public MeteringRepeatingKt$getProperPreviewSize$$inlined$sortBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r3, T r4) {
        Size r32 = (Size) r3;
        Size r42 = (Size) r4;
        return ComparisonsKt.compareValues(Long.valueOf(r32.getWidth() * r32.getHeight()), Long.valueOf(r42.getWidth() * r42.getHeight()));
    }
}
