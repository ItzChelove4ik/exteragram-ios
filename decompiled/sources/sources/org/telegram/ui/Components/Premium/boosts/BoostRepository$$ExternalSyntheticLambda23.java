package org.telegram.ui.Components.Premium.boosts;

import java.text.Collator;
import java.util.Comparator;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda23 implements Comparator {
    public final /* synthetic */ Collator f$0;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda23(Collator r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return this.f$0.compare((String) r1, (String) r2);
    }
}
