package com.google.android.gms.common.api.internal;

import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zay implements PendingResult.StatusListener {
    final /* synthetic */ BasePendingResult zaa;
    final /* synthetic */ zaaa zab;

    public zay(zaaa r1, BasePendingResult r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // com.google.android.gms.common.api.PendingResult.StatusListener
    public final void onComplete(Status r1) {
        this.zab.zaf().remove(this.zaa);
    }
}
