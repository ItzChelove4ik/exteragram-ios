package androidx.credentials.playservices.controllers.identitycredentials;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.ResultReceiver;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u00152\u00020\u0001:\u0001\u0015B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0012\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J\u0010\u0010\r\u001a\u00020\t2\u0006\u0010\u000e\u001a\u00020\u000bH\u0014J\"\u0010\u000f\u001a\u00020\t2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u0016"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/IdentityCredentialApiHiddenActivity;", "Landroid/app/Activity;", "<init>", "()V", "resultReceiver", "Landroid/os/ResultReceiver;", "mWaitingForActivityResult", _UrlKt.FRAGMENT_ENCODE_SET, "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "savedInstanceState", "Landroid/os/Bundle;", "restoreState", "onSaveInstanceState", "outState", "onActivityResult", "requestCode", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", "data", "Landroid/content/Intent;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class IdentityCredentialApiHiddenActivity extends Activity {
    public static final Companion Companion = null;
    private boolean mWaitingForActivityResult;
    private ResultReceiver resultReceiver;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/IdentityCredentialApiHiddenActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "KEY_AWAITING_RESULT", _UrlKt.FRAGMENT_ENCODE_SET, "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    static {
        Companion = new Companion(null);
    }

    public IdentityCredentialApiHiddenActivity() {
    }

    private final void restoreState(Bundle r3) {
        if (r3 == null) goto L5;
        this.mWaitingForActivityResult = r3.getBoolean("androidx.credentials.playservices.AWAITING_RESULT", false);
        return;
    }

    @Override // android.app.Activity
    public void onActivityResult(int r3, int r4, Intent r5) {
        super.onActivityResult(r3, r4, r5);
        ResultReceiver r0 = this.resultReceiver;
        if (r0 == null) goto L5;
        CredentialProviderBaseController.Companion.reportResult$credentials_play_services_auth(r0, r3, r4, r5);
    L5:
        this.mWaitingForActivityResult = false;
        finish();
    }

    @Override // android.app.Activity
    public void onCreate(Bundle r10) {
        super.onCreate(r10);
        overridePendingTransition(0, 0);
        ResultReceiver r0 = (ResultReceiver) getIntent().getParcelableExtra("RESULT_RECEIVER");
        this.resultReceiver = r0;
        if (r0 != null) goto L5;
        finish();
    L5:
        String r02 = getIntent().getStringExtra("EXTRA_ERROR_NAME");
        if (r02 != null) goto L9;
        finish();
        return;
    L9:
        restoreState(r10);
        if (this.mWaitingForActivityResult == false) goto L12;
        return;
    L12:
        PendingIntent r102 = (PendingIntent) getIntent().getParcelableExtra("EXTRA_FLOW_PENDING_INTENT");
        if (r102 == null) goto L16;
        this.mWaitingForActivityResult = true;
        startIntentSenderForResult(r102.getIntentSender(), CredentialProviderBaseController.Companion.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth(), null, 0, 0, 0, null);
        return;
    L16:
        ResultReceiver r9 = this.resultReceiver;
        if (r9 == null) goto L19;
        CredentialProviderBaseController.Companion.reportError$credentials_play_services_auth(r9, r02, "Internal error");
    L19:
        finish();
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle r3) {
        r3.putBoolean("androidx.credentials.playservices.AWAITING_RESULT", this.mWaitingForActivityResult);
        super.onSaveInstanceState(r3);
    }
}
