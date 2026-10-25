package com.google.android.gms.internal.recaptchabase;

import android.content.Context;
import com.google.android.gms.common.Feature;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApi;
import com.google.android.gms.common.api.internal.TaskApiCall;
import com.google.android.gms.recaptchabase.ExecuteRequest;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitRequest;
import com.google.android.gms.recaptchabase.InitResult;
import com.google.android.gms.recaptchabase.RecaptchaBaseClient;
import com.google.android.gms.tasks.Task;
import kotlin.Metadata;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0007\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u00012\u00020\u0003:\u0001\u0017B%\b\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0004\u0012\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\u00020\u0006¢\u0006\u0004\b\t\u0010\nB\u0011\b\u0016\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\t\u0010\u000bJ\u001d\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u000e2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b\u0010\u0010\u0011J\u001d\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00140\u000e2\u0006\u0010\u0013\u001a\u00020\u0012H\u0016¢\u0006\u0004\b\u0015\u0010\u0016¨\u0006\u0018"}, d2 = {"Lcom/google/android/gms/recaptchabase/internal/InternalRecaptchaBaseClient;", "Lcom/google/android/gms/common/api/GoogleApi;", "Lcom/google/android/gms/common/api/Api$ApiOptions$NoOptions;", "Lcom/google/android/gms/recaptchabase/RecaptchaBaseClient;", "Landroid/content/Context;", "context", "Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;", "Lcom/google/android/gms/recaptchabase/internal/RecaptchaBaseClientImpl;", "clientBuilder", "<init>", "(Landroid/content/Context;Lcom/google/android/gms/common/api/Api$AbstractClientBuilder;)V", "(Landroid/content/Context;)V", "Lcom/google/android/gms/recaptchabase/ExecuteRequest;", "executeRequest", "Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/ExecuteResult;", "execute", "(Lcom/google/android/gms/recaptchabase/ExecuteRequest;)Lcom/google/android/gms/tasks/Task;", "Lcom/google/android/gms/recaptchabase/InitRequest;", "initRequest", "Lcom/google/android/gms/recaptchabase/InitResult;", "init", "(Lcom/google/android/gms/recaptchabase/InitRequest;)Lcom/google/android/gms/tasks/Task;", "Companion", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class zzl extends GoogleApi implements RecaptchaBaseClient {
    public static final /* synthetic */ int $r8$clinit = 0;
    private static final zzi zzb = null;
    private static final Api.ClientKey zzc = null;
    private static final Api zzd = null;

    static {
        zzi r0 = new zzi();
        zzb = r0;
        Api.ClientKey r1 = new Api.ClientKey();
        zzc = r1;
        zzd = new Api("RecaptchaBase.API", r0, r1);
    }

    public zzl(Context r4) {
        super(r4, zzd, Api.ApiOptions.NO_OPTIONS, GoogleApi.Settings.DEFAULT_SETTINGS);
    }

    @Override // com.google.android.gms.recaptchabase.RecaptchaBaseClient
    public final Task<ExecuteResult> execute(ExecuteRequest r3) {
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{com.google.android.gms.recaptchabase.zze.zza}).run(new zzg(r3)).setMethodKey(34002).build());
    }

    @Override // com.google.android.gms.recaptchabase.RecaptchaBaseClient
    public final Task<InitResult> init(InitRequest r3) {
        return doRead(TaskApiCall.builder().setFeatures(new Feature[]{com.google.android.gms.recaptchabase.zze.zzb}).run(new zzh(r3)).setMethodKey(34001).build());
    }
}
