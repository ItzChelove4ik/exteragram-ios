package com.google.android.gms.auth.api.phone;

import android.content.Context;
import com.google.android.gms.internal.p001authapiphone.zzab;

/* loaded from: classes4.dex */
public abstract class SmsRetriever {
    public static SmsRetrieverClient getClient(Context r1) {
        return new zzab(r1);
    }
}
