package com.google.android.gms.common.api.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Objects;

/* loaded from: classes4.dex */
final class zaz implements OnCompleteListener {
    final /* synthetic */ TaskCompletionSource zaa;
    final /* synthetic */ zaaa zab;

    public zaz(zaaa r1, TaskCompletionSource r2) {
        this.zaa = r2;
        Objects.requireNonNull(r1);
        this.zab = r1;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r1) {
        this.zab.zag().remove(this.zaa);
    }
}
