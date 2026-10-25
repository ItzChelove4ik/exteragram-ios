package androidx.credentials.playservices.controllers.identityauth.createpublickeycredential;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"androidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1", "Landroid/os/ResultReceiver;", "onReceiveResult", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", _UrlKt.FRAGMENT_ENCODE_SET, "resultData", "Landroid/os/Bundle;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1 extends ResultReceiver {
    final /* synthetic */ CredentialProviderCreatePublicKeyCredentialController this$0;

    public CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1(CredentialProviderCreatePublicKeyCredentialController r1, Handler r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // android.os.ResultReceiver
    public void onReceiveResult(int r7, Bundle r8) {
        CredentialProviderCreatePublicKeyCredentialController r0 = this.this$0;
        CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1$onReceiveResult$1 r2 = new CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1$onReceiveResult$1(CredentialProviderBaseController.Companion);
        Executor r1 = CredentialProviderCreatePublicKeyCredentialController.access$getExecutor$p(this.this$0);
        if (r1 != null) goto L5;
        r1 = null;
    L5:
        CredentialManagerCallback r4 = CredentialProviderCreatePublicKeyCredentialController.access$getCallback$p(this.this$0);
        if (r4 != null) goto L9;
        r4 = null;
    L9:
        if (CredentialProviderCreatePublicKeyCredentialController.access$maybeReportErrorFromResultReceiver(r0, r8, r2, r1, r4, CredentialProviderCreatePublicKeyCredentialController.access$getCancellationSignal$p(this.this$0)) == false) goto L11;
        return;
    L11:
        this.this$0.handleResponse$credentials_play_services_auth(r8.getInt("ACTIVITY_REQUEST_CODE"), r7, (Intent) r8.getParcelable("RESULT_DATA"));
    }
}
