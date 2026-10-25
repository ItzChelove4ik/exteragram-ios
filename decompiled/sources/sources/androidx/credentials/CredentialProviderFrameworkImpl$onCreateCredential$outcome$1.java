package androidx.credentials;

import android.os.OutcomeReceiver;
import android.util.Log;
import androidx.credentials.exceptions.CreateCredentialException;
import com.exteragram.messenger.plugins.PluginsConstants;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004*\u0001\u0000\b\n\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0016J\u0010\u0010\u0007\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\t"}, d2 = {"androidx/credentials/CredentialProviderFrameworkImpl$onCreateCredential$outcome$1", "Landroid/os/OutcomeReceiver;", "Landroid/credentials/CreateCredentialResponse;", "Landroid/credentials/CreateCredentialException;", "onResult", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.RESPONSE, "onError", "error", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderFrameworkImpl$onCreateCredential$outcome$1 implements OutcomeReceiver {
    final /* synthetic */ CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> $callback;
    final /* synthetic */ CreateCredentialRequest $request;
    final /* synthetic */ CredentialProviderFrameworkImpl this$0;

    public CredentialProviderFrameworkImpl$onCreateCredential$outcome$1(CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r1, CreateCredentialRequest r2, CredentialProviderFrameworkImpl r3) {
        this.$callback = r1;
        this.$request = r2;
        this.this$0 = r3;
    }

    public void onError(android.credentials.CreateCredentialException r3) {
        Log.i("CredManProvService", "CreateCredentialResponse error returned from framework");
        this.$callback.onError(this.this$0.convertToJetpackCreateException$credentials(r3));
    }

    public /* bridge */ /* synthetic */ void onError(Throwable r1) {
        onError(CredentialProviderFrameworkImpl$onCreateCredential$outcome$1$$ExternalSyntheticApiModelOutline0.m(r1));
    }

    public void onResult(android.credentials.CreateCredentialResponse r3) {
        Log.i("CredManProvService", "Create Result returned from framework: ");
        this.$callback.onResult(CreateCredentialResponse.Companion.createFrom(this.$request.getType(), r3.getData()));
    }

    public /* bridge */ /* synthetic */ void onResult(Object r1) {
        onResult(CredentialProviderFrameworkImpl$onCreateCredential$outcome$1$$ExternalSyntheticApiModelOutline1.m(r1));
    }
}
