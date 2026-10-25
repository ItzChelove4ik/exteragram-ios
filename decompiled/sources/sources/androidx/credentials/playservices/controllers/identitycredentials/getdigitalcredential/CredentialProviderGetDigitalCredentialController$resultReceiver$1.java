package androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;
import androidx.core.os.BundleCompat;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.ResponseUtils;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016¨\u0006\b"}, d2 = {"androidx/credentials/playservices/controllers/identitycredentials/getdigitalcredential/CredentialProviderGetDigitalCredentialController$resultReceiver$1", "Landroid/os/ResultReceiver;", "onReceiveResult", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", _UrlKt.FRAGMENT_ENCODE_SET, "resultData", "Landroid/os/Bundle;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderGetDigitalCredentialController$resultReceiver$1 extends ResultReceiver {
    final /* synthetic */ CredentialProviderGetDigitalCredentialController this$0;

    public CredentialProviderGetDigitalCredentialController$resultReceiver$1(CredentialProviderGetDigitalCredentialController r1, Handler r2) {
        this.this$0 = r1;
        super(r2);
    }

    @Override // android.os.ResultReceiver
    public void onReceiveResult(int r8, Bundle r9) {
        if (CredentialProviderGetDigitalCredentialController.access$maybeReportErrorFromResultReceiver(this.this$0, r9, new CredentialProviderGetDigitalCredentialController$resultReceiver$1$onReceiveResult$1(CredentialProviderBaseController.Companion), this.this$0.getExecutor(), this.this$0.getCallback(), CredentialProviderGetDigitalCredentialController.access$getCancellationSignal$p(this.this$0)) == false) goto L5;
        return;
    L5:
        ResponseUtils.Companion.handleGetCredentialResponse(r9.getInt("ACTIVITY_REQUEST_CODE"), r8, (Intent) BundleCompat.getParcelable(r9, "RESULT_DATA", Intent.class), this.this$0.getExecutor(), this.this$0.getCallback(), CredentialProviderGetDigitalCredentialController.access$getCancellationSignal$p(this.this$0));
    }
}
