package com.google.common.base;

import com.google.common.base.Splitter;
import java.util.Iterator;

/* loaded from: classes5.dex */
public final /* synthetic */ class Splitter$$ExternalSyntheticLambda1 implements Splitter.Strategy {
    public final /* synthetic */ CharMatcher f$0;

    public /* synthetic */ Splitter$$ExternalSyntheticLambda1(CharMatcher r1) {
        this.f$0 = r1;
    }

    @Override // com.google.common.base.Splitter.Strategy
    public final Iterator iterator(Splitter r1, CharSequence r2) {
        return Splitter.m1914$r8$lambda$CK_F_WMWUv6g4fX5TCs4Q17sbA(this.f$0, r1, r2);
    }
}
