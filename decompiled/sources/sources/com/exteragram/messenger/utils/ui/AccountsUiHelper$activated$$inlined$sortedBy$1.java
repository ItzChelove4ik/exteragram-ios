package com.exteragram.messenger.utils.ui;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.internal.SourceDebugExtension;
import org.telegram.messenger.UserConfig;

@Metadata(k = 3, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nComparisons.kt\nKotlin\n*S Kotlin\n*F\n+ 1 Comparisons.kt\nkotlin/comparisons/ComparisonsKt__ComparisonsKt$compareBy$2\n+ 2 AccountsUiHelper.kt\ncom/exteragram/messenger/utils/ui/AccountsUiHelper\n*L\n1#1,328:1\n61#2:329\n*E\n"})
/* loaded from: classes4.dex */
public final class AccountsUiHelper$activated$$inlined$sortedBy$1<T> implements Comparator {
    public AccountsUiHelper$activated$$inlined$sortedBy$1() {
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // java.util.Comparator
    public final int compare(T r1, T r2) {
        return ComparisonsKt.compareValues(Integer.valueOf(UserConfig.getInstance(((Number) r1).intValue()).loginTime), Integer.valueOf(UserConfig.getInstance(((Number) r2).intValue()).loginTime));
    }
}
