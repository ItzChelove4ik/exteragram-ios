package com.exteragram.messenger.icons.ui;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.telegram.ui.Components.UItem;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 IconPacksEditorActivity.kt\ncom/exteragram/messenger/icons/ui/IconPacksEditorActivity\n*L\n1#1,328:1\n208#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class IconPacksEditorActivity$loadIconsAsync$lambda$6$$inlined$sortBy$1<T> implements Comparator {
    public IconPacksEditorActivity$loadIconsAsync$lambda$6$$inlined$sortBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(((UItem) r1).text.toString(), ((UItem) r2).text.toString());
    }
}
