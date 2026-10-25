package com.google.android.gms.recaptchabase;

import com.google.android.gms.tasks.Task;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bg\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001J\u001d\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u0006\u0010\u0004\u001a\u00020\u0003H'¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000b0\u00052\u0006\u0010\n\u001a\u00020\tH'¢\u0006\u0004\b\f\u0010\rø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u000eÀ\u0006\u0001"}, d2 = {"Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", _UrlKt.FRAGMENT_ENCODE_SET, "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "Lcom/google/android/gms/recaptchabase/ExecuteRequest;", "executeRequest", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/ExecuteResult;", "execute", "(Lcom/google/android/gms/recaptchabase/ExecuteRequest;)Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/InitRequest;", "initRequest", "Lcom/google/android/gms/recaptchabase/InitResult;", "init", "(Lcom/google/android/gms/recaptchabase/InitRequest;)Lcom/google/android/gms/tasks/Task;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public interface RecaptchaBaseClient {
    Task<ExecuteResult> execute(ExecuteRequest r1);

    Task<InitResult> init(InitRequest r1);
}
