package com.google.firebase.sessions;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016¨\u0006\b"}, d2 = {"Lcom/google/firebase/sessions/SessionLifecycleService;", "Landroid/app/Service;", "<init>", "()V", "onBind", _UrlKt.FRAGMENT_ENCODE_SET, "unused", "Landroid/content/Intent;", "com.google.firebase-firebase-sessions"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class SessionLifecycleService extends Service {
    public SessionLifecycleService() {
    }

    @Override // android.app.Service
    public /* bridge */ /* synthetic */ IBinder onBind(Intent r1) {
        return (IBinder) onBind(r1);
    }

    @Override // android.app.Service
    public Void onBind(Intent r1) {
        return null;
    }
}
