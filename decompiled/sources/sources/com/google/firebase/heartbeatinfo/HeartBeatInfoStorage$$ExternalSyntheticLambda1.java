package com.google.firebase.heartbeatinfo;

import androidx.datastore.preferences.core.MutablePreferences;
import androidx.datastore.preferences.core.Preferences;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final /* synthetic */ class HeartBeatInfoStorage$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ HeartBeatInfoStorage f$0;
    public final /* synthetic */ String f$1;
    public final /* synthetic */ String f$2;
    public final /* synthetic */ Preferences.Key f$3;

    public /* synthetic */ HeartBeatInfoStorage$$ExternalSyntheticLambda1(HeartBeatInfoStorage r1, String r2, String r3, Preferences.Key r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return HeartBeatInfoStorage.$r8$lambda$HdDmHlus2RmE_6N0PPHuiT70IIc(this.f$0, this.f$1, this.f$2, this.f$3, (MutablePreferences) r4);
    }
}
