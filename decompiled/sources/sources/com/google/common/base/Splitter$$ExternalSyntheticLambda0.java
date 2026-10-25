package com.google.common.base;

import com.google.common.base.Splitter;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final /* synthetic */ class Splitter$$ExternalSyntheticLambda0 implements Splitter.Strategy {
    public final /* synthetic */ String f$0;

    public /* synthetic */ Splitter$$ExternalSyntheticLambda0(String r1) {
        this.f$0 = r1;
    }

    @Override // com.google.common.base.Splitter.Strategy
    public final Iterator iterator(Splitter r1, CharSequence r2) {
        return Splitter.$r8$lambda$e00L1vhkadM2rBAapSaIfxSYJ7k(this.f$0, r1, r2);
    }
}
