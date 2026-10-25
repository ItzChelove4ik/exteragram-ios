package com.google.android.recaptcha;

import android.app.Application;
import com.google.android.gms.cast.framework.media.NotificationOptions;
import com.google.android.gms.tasks.Task;
import com.google.android.recaptcha.internal.zzjm;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.jvm.JvmStatic;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\u0006H\u0007¢\u0006\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/google/android/recaptcha/Recaptcha;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "Landroid/app/Application;", "application", _UrlKt.FRAGMENT_ENCODE_SET, "siteKey", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/recaptcha/RecaptchaTasksClient;", "getTasksClient", "(Landroid/app/Application;Ljava/lang/String;)Lcom/google/android/gms/tasks/Task;", "java.com.google.android.libraries.abuse.recaptcha.enterprise_enterprise"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class Recaptcha {
    public static final Recaptcha INSTANCE = null;

    static {
        INSTANCE = new Recaptcha();
    }

    private Recaptcha() {
    }

    @Deprecated(message = "Use fetchTaskClient(Application, siteKey) instead.", replaceWith = @ReplaceWith(expression = "fetchTaskClient", imports = {}))
    @JvmStatic
    public static final Task<RecaptchaTasksClient> getTasksClient(Application r2, String r3) {
        return zzjm.zze(r2, r3, NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS);
    }
}
