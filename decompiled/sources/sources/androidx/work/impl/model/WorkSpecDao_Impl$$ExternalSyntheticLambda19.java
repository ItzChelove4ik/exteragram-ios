package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import androidx.work.Data;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkSpecDao_Impl$$ExternalSyntheticLambda19 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ Data f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ WorkSpecDao_Impl$$ExternalSyntheticLambda19(String r1, Data r2, String r3) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r3) {
        return WorkSpecDao_Impl.m995$r8$lambda$uBycsnqVEM52eglkebt_v2RNeQ(this.f$0, this.f$1, this.f$2, (SQLiteConnection) r3);
    }
}
