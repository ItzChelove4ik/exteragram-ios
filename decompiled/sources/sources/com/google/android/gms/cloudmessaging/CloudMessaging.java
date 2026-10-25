package com.google.android.gms.cloudmessaging;

import android.content.Context;

/* loaded from: classes4.dex */
public abstract class CloudMessaging {
    public static CloudMessagingClient getClient(Context r1) {
        return new com.google.android.gms.internal.cloudmessaging.zzm(r1);
    }
}
