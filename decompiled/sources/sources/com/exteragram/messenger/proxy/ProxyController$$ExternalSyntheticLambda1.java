package com.exteragram.messenger.proxy;

import java.util.ArrayList;
import java.util.Comparator;
import org.telegram.messenger.SharedConfig;

/* loaded from: classes4.dex */
public final /* synthetic */ class ProxyController$$ExternalSyntheticLambda1 implements Comparator {
    public final /* synthetic */ ProxyController f$0;
    public final /* synthetic */ SharedConfig.ProxyInfo f$1;
    public final /* synthetic */ boolean f$2;
    public final /* synthetic */ ArrayList f$3;

    public /* synthetic */ ProxyController$$ExternalSyntheticLambda1(ProxyController r1, SharedConfig.ProxyInfo r2, boolean r3, ArrayList r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.util.Comparator
    public final int compare(Object r7, Object r8) {
        return ProxyController.m1724$r8$lambda$TdY6Cr8_tKxnkeT37UhQTXIvZA(this.f$0, this.f$1, this.f$2, this.f$3, (SharedConfig.ProxyInfo) r7, (SharedConfig.ProxyInfo) r8);
    }
}
