package com.google.android.recaptcha;

import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\bf\u0018\u00002\u00020\u0001J\u001d\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00042\u0006\u0010\u0003\u001a\u00020\u0002H&¢\u0006\u0004\b\u0006\u0010\u0007ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\bÀ\u0006\u0001"}, d2 = {"Lcom/google/android/recaptcha/RecaptchaTasksClient;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/android/recaptcha/RecaptchaAction;", "recaptchaAction", "Lcom/google/android/gms/tasks/Task;", _UrlKt.FRAGMENT_ENCODE_SET, "executeTask", "(Lcom/google/android/recaptcha/RecaptchaAction;)Lcom/google/android/gms/tasks/Task;", "java.com.google.android.libraries.abuse.recaptcha.enterprise.public_public"}, k = 1, mv = {2, 3, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface RecaptchaTasksClient {
    Task<String> executeTask(RecaptchaAction r1);
}
