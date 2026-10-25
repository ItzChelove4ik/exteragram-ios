package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class WorkSpecDao_Impl$$ExternalSyntheticLambda26 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ WorkSpecDao_Impl$$ExternalSyntheticLambda26(String r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return Integer.valueOf(WorkSpecDao_Impl.$r8$lambda$9icPC8zW71nSfq9GS1Jqyvbbd9I(this.f$0, this.f$1, (SQLiteConnection) r2));
    }
}
