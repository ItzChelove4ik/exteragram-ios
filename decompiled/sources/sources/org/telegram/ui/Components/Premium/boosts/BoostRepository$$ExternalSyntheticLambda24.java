package org.telegram.ui.Components.Premium.boosts;

import java.util.Comparator;
import org.telegram.tgnet.TLRPC;

/* loaded from: classes7.dex */
public final /* synthetic */ class BoostRepository$$ExternalSyntheticLambda24 implements Comparator {
    public final /* synthetic */ Comparator f$0;

    public /* synthetic */ BoostRepository$$ExternalSyntheticLambda24(Comparator r1) {
        this.f$0 = r1;
    }

    @Override // java.util.Comparator
    public final int compare(Object r1, Object r2) {
        return BoostRepository.m12092$r8$lambda$_LNcCYSxMJCkO7LeIC1_aTSjrU(this.f$0, (TLRPC.TL_help_country) r1, (TLRPC.TL_help_country) r2);
    }
}
