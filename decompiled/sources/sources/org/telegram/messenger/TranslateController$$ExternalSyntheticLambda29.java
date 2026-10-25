package org.telegram.messenger;

import android.icu.text.Collator;
import java.util.Comparator;
import org.telegram.messenger.TranslateController;

/* loaded from: classes5.dex */
public final /* synthetic */ class TranslateController$$ExternalSyntheticLambda29 implements Comparator {
    public final /* synthetic */ Collator f$0;

    public /* synthetic */ TranslateController$$ExternalSyntheticLambda29(Collator r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return TranslateController.$r8$lambda$ogpEaxb9JmIC6s_561tRHjzJzY8(this.f$0, (TranslateController.Language) r1, (TranslateController.Language) r2);
    }
}
