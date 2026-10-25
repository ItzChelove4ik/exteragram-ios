package androidx.work.impl.model;

import androidx.sqlite.SQLiteConnection;
import kotlin.jvm.functions.Function1;

/* loaded from: classes4.dex */
public final /* synthetic */ class DependencyDao_Impl$$ExternalSyntheticLambda3 implements Function1 {
    public final /* synthetic */ String f$0;
    public final /* synthetic */ String f$1;

    public /* synthetic */ DependencyDao_Impl$$ExternalSyntheticLambda3(String r1, String r2) {
        this.f$0 = r1;
        this.f$1 = r2;
    }

    @Override // kotlin.jvm.functions.Function1
    public final Object invoke(Object r2) {
        return Boolean.valueOf(DependencyDao_Impl.$r8$lambda$fiiqOIOlPIoK2xVIJEfoCfVnRyI(this.f$0, this.f$1, (SQLiteConnection) r2));
    }
}
