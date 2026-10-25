package com.exteragram.messenger.api.db;

import androidx.sqlite.SQLiteConnection;
import com.exteragram.messenger.api.dto.AddedRegDateDTO;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class AddedRegDateDao_Impl$$ExternalSyntheticLambda1 implements Function1 {
    public final /* synthetic */ AddedRegDateDao_Impl f$0;
    public final /* synthetic */ AddedRegDateDTO f$1;

    public /* synthetic */ AddedRegDateDao_Impl$$ExternalSyntheticLambda1(AddedRegDateDao_Impl r1, AddedRegDateDTO r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return AddedRegDateDao_Impl.$r8$lambda$Io97oZNfeFEOzi2ZwPsP1ngH8Do(this.f$0, this.f$1, (SQLiteConnection) r2);
    }
}
