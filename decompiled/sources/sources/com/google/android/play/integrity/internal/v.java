package com.google.android.play.integrity.internal;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;

/* loaded from: classes5.dex */
public final /* synthetic */ class v implements OnCompleteListener {

    /* renamed from: a, reason: collision with root package name */
    public final /* synthetic */ ae f132a;

    /* renamed from: b, reason: collision with root package name */
    public final /* synthetic */ TaskCompletionSource f133b;

    public /* synthetic */ v(ae r1, TaskCompletionSource r2) {
        this.f132a = r1;
        this.f133b = r2;
    }

    @Override // com.google.android.gms.tasks.OnCompleteListener
    public final void onComplete(Task r2) {
        this.f132a.u(this.f133b, r2);
    }
}
