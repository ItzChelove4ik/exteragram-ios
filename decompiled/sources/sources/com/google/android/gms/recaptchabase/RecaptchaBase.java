package com.google.android.gms.recaptchabase;

import android.content.Context;
import com.google.android.gms.internal.recaptchabase.zzl;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0017\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0005\u001a\u00020\u0004H\u0007¢\u0006\u0004\b\u0007\u0010\b¨\u0006\t"}, d2 = {"Lcom/google/android/gms/recaptchabase/RecaptchaBase;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/content/Context;", "context", "Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "getClient", "(Landroid/content/Context;)Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class RecaptchaBase {
    public static final RecaptchaBase INSTANCE = null;

    static {
        INSTANCE = new RecaptchaBase();
    }

    private RecaptchaBase() {
    }

    @JvmStatic
    public static final RecaptchaBaseClient getClient(Context r1) {
        return new zzl(r1);
    }
}
