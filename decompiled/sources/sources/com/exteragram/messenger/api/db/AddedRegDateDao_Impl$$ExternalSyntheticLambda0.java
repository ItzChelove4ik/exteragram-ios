package com.exteragram.messenger.api.db;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class AddedRegDateDao_Impl$$ExternalSyntheticLambda0 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ long f$1;

    public /* synthetic */ AddedRegDateDao_Impl$$ExternalSyntheticLambda0(String r1, long r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return Boolean.valueOf(AddedRegDateDao_Impl.$r8$lambda$WU9sfMQkLn_FRxJgVv9GMgGynPQ(this.f$0, this.f$1, (SQLiteConnection) r4));
    }
}
