package com.exteragram.messenger.api.db;

import androidx.sqlite.SQLiteConnection;
import java.util.List;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class ProfileDao_Impl$$ExternalSyntheticLambda2 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ List f$1;

    public /* synthetic */ ProfileDao_Impl$$ExternalSyntheticLambda2(String r1, List r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return Integer.valueOf(ProfileDao_Impl.$r8$lambda$j6f9cnJG2ThNHCJCgms70gjEkcM(this.f$0, this.f$1, (SQLiteConnection) r2));
    }
}
