package com.exteragram.messenger.plugins.utils;

import com.exteragram.messenger.plugins.utils.PluginsWatchdog;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Ref;

/* loaded from: classes4.dex */
public final /* synthetic */ class PluginsWatchdog$$ExternalSyntheticLambda1 implements Function2 {
    public final /* synthetic */ PluginsWatchdog.ExecutionInfo f$0;
    public final /* synthetic */ PluginsWatchdog f$1;
    public final /* synthetic */ Thread f$2;
    public final /* synthetic */ String f$3;
    public final /* synthetic */ Ref.BooleanRef f$4;

    public /* synthetic */ PluginsWatchdog$$ExternalSyntheticLambda1(PluginsWatchdog.ExecutionInfo r1, PluginsWatchdog r2, Thread r3, String r4, Ref.BooleanRef r5) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
        this.f$4 = r5;
    }

    @Override // kotlin.jvm.functions.Function2
    public final Object invoke(Object r8, Object r9) {
        return PluginsWatchdog.$r8$lambda$WVisd4rB5sLtocqueDN9mCZdTTk(this.f$0, this.f$1, this.f$2, this.f$3, this.f$4, (Thread) r8, (PluginsWatchdog.ExecutionInfo) r9);
    }
}
