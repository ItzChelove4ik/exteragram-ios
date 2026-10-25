package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes.dex */
public final /* synthetic */ class WorkSpecDao_Impl$$ExternalSyntheticLambda12 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ long f$1;
    public final /* synthetic */ String f$2;

    public /* synthetic */ WorkSpecDao_Impl$$ExternalSyntheticLambda12(String r1, long r2, String r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r4;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r4) {
        return Integer.valueOf(WorkSpecDao_Impl.$r8$lambda$c3IlkoMXTk79d9ht9bnL8LIKQs0(this.f$0, this.f$1, this.f$2, (SQLiteConnection) r4));
    }
}
