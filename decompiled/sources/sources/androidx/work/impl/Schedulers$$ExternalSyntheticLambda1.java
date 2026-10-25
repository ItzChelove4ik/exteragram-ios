package androidx.work.impl;

import androidx.work.Configuration;
import androidx.work.impl.model.WorkGenerationalId;
import java.util.List;

/* loaded from: classes4.dex */
public final /* synthetic */ class Schedulers$$ExternalSyntheticLambda1 implements Runnable {
    public final /* synthetic */ List f$0;
    public final /* synthetic */ WorkGenerationalId f$1;
    public final /* synthetic */ Configuration f$2;
    public final /* synthetic */ WorkDatabase f$3;

    public /* synthetic */ Schedulers$$ExternalSyntheticLambda1(List r1, WorkGenerationalId r2, Configuration r3, WorkDatabase r4) {
        this.f$0 = r1;
        this.f$1 = r2;
        this.f$2 = r3;
        this.f$3 = r4;
    }

    @Override // java.lang.Runnable
    public final void run() {
        Schedulers.$r8$lambda$UldOQUXkk9IlUfICp9vyZBPK1cE(this.f$0, this.f$1, this.f$2, this.f$3);
    }
}
