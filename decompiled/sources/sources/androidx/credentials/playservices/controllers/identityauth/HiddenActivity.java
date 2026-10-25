package androidx.credentials.playservices.controllers.identityauth;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentSender;
import android.os.Bundle;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0017\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0014J\u0012\u0010\f\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0002J \u0010\r\u001a\u00020\t2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000fH\u0002J\u0010\u0010\u0011\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u000bH\u0014J\u0010\u0010\u0013\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\u0018\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0016\u001a\u00020\u0017H\u0002J\u0010\u0010\u0018\u001a\u00020\t2\u0006\u0010\u0014\u001a\u00020\u000fH\u0002J\"\u0010\u0019\u001a\u00020\t2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001b2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001eH\u0014R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/HiddenActivity;", "Landroid/app/Activity;", "<init>", "()V", "resultReceiver", "Landroid/os/ResultReceiver;", "mWaitingForActivityResult", _UrlKt.FRAGMENT_ENCODE_SET, "onCreate", _UrlKt.FRAGMENT_ENCODE_SET, "savedInstanceState", "Landroid/os/Bundle;", "restoreState", "setupFailure", "errName", _UrlKt.FRAGMENT_ENCODE_SET, "errMsg", "onSaveInstanceState", "outState", "handleCredentialFlow", "type", "setupIntentSenderFailureByType", "e", "Landroid/content/IntentSender$SendIntentException;", "setupPendingIntentFailureByType", "onActivityResult", "requestCode", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", "data", "Landroid/content/Intent;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public class HiddenActivity extends Activity {
    public static final Companion Companion = null;
    private boolean mWaitingForActivityResult;
    private ResultReceiver resultReceiver;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/HiddenActivity$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "DEFAULT_VALUE", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "KEY_AWAITING_RESULT", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
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

    public HiddenActivity() {
    }

    private final void handleCredentialFlow(String r13) {
        PendingIntent r0 = (PendingIntent) getIntent().getParcelableExtra("EXTRA_FLOW_PENDING_INTENT");
        int r6 = getIntent().getIntExtra("ACTIVITY_REQUEST_CODE", 1);
        if (r0 != null) goto L18;
        setupPendingIntentFailureByType(r13);
        return;
    L18:
        this.mWaitingForActivityResult = true;     // Catch: IntentSender.SendIntentException -> L10
        HiddenActivity r4 = this;
        r4.startIntentSenderForResult(r0.getIntentSender(), r6, null, 0, 0, 0, null);     // Catch: IntentSender.SendIntentException -> L8
        return;
    L8:
        e = e;
    L12:
        r4.setupIntentSenderFailureByType(r13, e);
        return;
    L10:
        e = e;
        r4 = this;
        goto L12
    }

    private final void restoreState(Bundle r3) {
        if (r3 == null) goto L5;
        this.mWaitingForActivityResult = r3.getBoolean("androidx.credentials.playservices.AWAITING_RESULT", false);
        return;
    }

    private final void setupFailure(ResultReceiver r2, String r3, String r4) {
        CredentialProviderBaseController.Companion.reportError$credentials_play_services_auth(r2, r3, r4);
        finish();
    }

    private final void setupIntentSenderFailureByType(String r4, IntentSender.SendIntentException r5) {
        switch(r4.hashCode()) {
            case -441061071: goto L21;
            case 15545322: goto L16;
            case 1246634622: goto L11;
            case 1980564212: goto L6;
            default: goto L25;
        };
    L25:
        return;
    L6:
        if (r4.equals("SIGN_IN_INTENT") == false) goto L26;
        setupFailure(this.resultReceiver, "GET_UNKNOWN", "During get sign-in intent, one tap ui intent sender failure: " + r5.getMessage());
        return;
    L26:
        return;
    L11:
        if (r4.equals("CREATE_PASSWORD") == false) goto L27;
        setupFailure(this.resultReceiver, "CREATE_UNKNOWN", "During save password, found UI intent sender failure: " + r5.getMessage());
        return;
    L27:
        return;
    L16:
        if (r4.equals("CREATE_PUBLIC_KEY_CREDENTIAL") == false) goto L28;
        setupFailure(this.resultReceiver, "CREATE_UNKNOWN", "During public key credential, found IntentSender failure on public key creation: " + r5.getMessage());
        return;
    L28:
        return;
    L21:
        if (r4.equals("BEGIN_SIGN_IN") == true) goto L23;
        return;
    L23:
        setupFailure(this.resultReceiver, "GET_UNKNOWN", "During begin sign in, one tap ui intent sender failure: " + r5.getMessage());
    }

    private final void setupPendingIntentFailureByType(String r4) {
        switch(r4.hashCode()) {
            case -441061071: goto L21;
            case 15545322: goto L16;
            case 1246634622: goto L11;
            case 1980564212: goto L6;
            default: goto L25;
        };
    L25:
        return;
    L6:
        if (r4.equals("SIGN_IN_INTENT") == false) goto L26;
        setupFailure(this.resultReceiver, "GET_UNKNOWN", "internal error during the sign-in intent operation");
        return;
    L26:
        return;
    L11:
        if (r4.equals("CREATE_PASSWORD") == false) goto L27;
        setupFailure(this.resultReceiver, "CREATE_UNKNOWN", "internal error during password creation");
        return;
    L27:
        return;
    L16:
        if (r4.equals("CREATE_PUBLIC_KEY_CREDENTIAL") == false) goto L28;
        setupFailure(this.resultReceiver, "CREATE_UNKNOWN", "internal error during public key credential creation");
        return;
    L28:
        return;
    L21:
        if (r4.equals("BEGIN_SIGN_IN") == true) goto L23;
        return;
    L23:
        setupFailure(this.resultReceiver, "GET_UNKNOWN", "internal error during the begin sign in operation");
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
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        overridePendingTransition(0, 0);
        String r0 = getIntent().getStringExtra("TYPE");
        ResultReceiver r1 = (ResultReceiver) getIntent().getParcelableExtra("RESULT_RECEIVER");
        this.resultReceiver = r1;
        if (r1 != null) goto L5;
        finish();
    L5:
        restoreState(r4);
        if (this.mWaitingForActivityResult == false) goto L8;
        return;
    L8:
        if (r0 != null) goto L11;
        Log.w("HiddenActivity", "Activity handed an unsupported type");
        finish();
        return;
    L11:
        handleCredentialFlow(r0);
    }

    @Override // android.app.Activity
    public void onSaveInstanceState(Bundle r3) {
        r3.putBoolean("androidx.credentials.playservices.AWAITING_RESULT", this.mWaitingForActivityResult);
        super.onSaveInstanceState(r3);
    }
}
