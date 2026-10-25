package com.google.android.gms.internal.recaptchabase;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.TaskUtil;
import com.google.android.gms.recaptchabase.ExecuteResult;
import com.google.android.gms.recaptchabase.InitResult;
import com.google.android.gms.tasks.TaskCompletionSource;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000#\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007H\u0016J\u001c\u0010\b\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\b\u0010\u0006\u001a\u0004\u0018\u00010\tH\u0016¨\u0006\n"}, d2 = {"com/google/android/gms/recaptchabase/internal/InternalRecaptchaBaseClient$init$1$callback$1", "Lcom/google/android/gms/recaptchabase/internal/IRecaptchaBaseCallbacks$Stub;", "onInitResponse", _UrlKt.FRAGMENT_ENCODE_SET, "status", "Lcom/google/android/gms/common/api/Status;", "result", "Lcom/google/android/gms/recaptchabase/InitResult;", "onExecuteResponse", "Lcom/google/android/gms/recaptchabase/ExecuteResult;", "java.com.google.android.gmscore.integ.client.recaptchabase_recaptchabase_kt"}, k = 1, mv = {2, 0, 0}, xi = 48)
/* loaded from: classes5.dex */
public final class zzk extends zzd {
    final /* synthetic */ TaskCompletionSource zza;

    public zzk(TaskCompletionSource r1) {
        this.zza = r1;
    }

    @Override // com.google.android.gms.internal.recaptchabase.zze
    public final void zzb(Status r1, ExecuteResult r2) {
    }

    @Override // com.google.android.gms.internal.recaptchabase.zze
    public final void zzc(Status r1, InitResult r2) {
        TaskUtil.setResultOrApiException(r1, r2, this.zza);
    }
}
