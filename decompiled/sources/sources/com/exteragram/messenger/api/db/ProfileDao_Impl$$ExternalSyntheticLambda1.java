package com.exteragram.messenger.api.db;

import androidx.sqlite.SQLiteConnection;
import com.exteragram.messenger.api.dto.BadgeDTO;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class ProfileDao_Impl$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ ProfileDao_Impl f$1;
    public final /* synthetic */ BadgeDTO f$2;
    public final /* synthetic */ long f$3;

    public /* synthetic */ ProfileDao_Impl$$ExternalSyntheticLambda1(String r1, ProfileDao_Impl r2, BadgeDTO r3, long r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r7) {
        return Integer.valueOf(ProfileDao_Impl.$r8$lambda$2rPikLOPJnXPuTJs1oMdKpUjG0o(this.f$0, this.f$1, this.f$2, this.f$3, (SQLiteConnection) r7));
    }
}
