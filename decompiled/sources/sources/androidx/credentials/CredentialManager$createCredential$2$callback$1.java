package androidx.credentials;

import androidx.credentials.exceptions.CreateCredentialException;
import kotlin.Metadata;
import kotlin.Result;
import kotlin.ResultKt;
import kotlinx.coroutines.CancellableContinuation;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialManager$createCredential$2$callback$1", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onResult", _UrlKt.FRAGMENT_ENCODE_SET, "result", "onError", "e", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialManager$createCredential$2$callback$1 implements CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> {
    final /* synthetic */ CancellableContinuation<CreateCredentialResponse> $continuation;

    /* JADX WARN: Multi-variable type inference failed */
    public CredentialManager$createCredential$2$callback$1(CancellableContinuation<? super CreateCredentialResponse> r1) {
        this.$continuation = r1;
    }

    /* renamed from: onError, reason: avoid collision after fix types in other method */
    public void onError2(CreateCredentialException r2) {
        if (this.$continuation.isActive() == false) goto L6;
        CancellableContinuation<CreateCredentialResponse> r1 = this.$continuation;
        Result.Companion r0 = Result.Companion;
        r1.resumeWith(Result.m2018constructorimpl(ResultKt.createFailure(r2)));
        return;
    }

    @Override // androidx.credentials.CredentialManagerCallback
    public /* bridge */ /* synthetic */ void onError(CreateCredentialException r1) {
        onError2(r1);
    }

    /* renamed from: onResult, reason: avoid collision after fix types in other method */
    public void onResult2(CreateCredentialResponse r2) {
        if (this.$continuation.isActive() == false) goto L6;
        this.$continuation.resumeWith(Result.m2018constructorimpl(r2));
        return;
    }

    @Override // androidx.credentials.CredentialManagerCallback
    public /* bridge */ /* synthetic */ void onResult(CreateCredentialResponse r1) {
        onResult2(r1);
    }
}
