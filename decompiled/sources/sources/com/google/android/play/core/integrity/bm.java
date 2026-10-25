package com.google.android.play.core.integrity;

import com.google.android.gms.tasks.TaskCompletionSource;
import com.google.android.play.integrity.internal.af;

/* loaded from: classes5.dex */
abstract class bm extends com.google.android.play.integrity.internal.t {
    final /* synthetic */ bn f;

    public bm(bn r1, TaskCompletionSource r2) {
        this.f = r1;
        super(r2);
    }

    @Override // com.google.android.play.integrity.internal.t
    public final void a(Exception r3) {
        if ((r3 instanceof af) == true) goto L7;
        super.a(r3);
        return;
    L7:
        if (bn.l(this.f) == false) goto L10;
        super.a(new StandardIntegrityException(-2, r3));
        return;
    L10:
        super.a(new StandardIntegrityException(-9, r3));
    }
}
