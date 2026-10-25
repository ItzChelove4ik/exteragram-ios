package androidx.credentials.playservices.controllers;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.os.ResultReceiver;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.NoCredentialException;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0010\u0018\u0000 \u00112\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J!\u0010\u0006\u001a\u0004\u0018\u00010\u0007\"\n\b\u0000\u0010\b*\u0004\u0018\u00010\u00072\u0006\u0010\t\u001a\u0002H\b¢\u0006\u0002\u0010\nJ\u001e\u0010\u000b\u001a\u00020\f2\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Landroidx/credentials/playservices/controllers/CredentialProviderBaseController;", _UrlKt.FRAGMENT_ENCODE_SET, "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "toIpcFriendlyResultReceiver", "Landroid/os/ResultReceiver;", "T", "resultReceiver", "(Landroid/os/ResultReceiver;)Landroid/os/ResultReceiver;", "generateHiddenActivityIntent", _UrlKt.FRAGMENT_ENCODE_SET, "hiddenIntent", "Landroid/content/Intent;", "typeTag", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CredentialProviderBaseController {
    private static final int CONTROLLER_REQUEST_CODE = 0;
    public static final Companion Companion = null;
    private static final Set<Integer> retryables = null;
    private final Context context;

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b!\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J#\u0010\n\u001a\u00020\u00072\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\b\u0010\tJ#\u0010\u0011\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u0004H\u0000¢\u0006\u0004\b\u000f\u0010\u0010J-\u0010\u0019\u001a\u00020\u000e*\u00020\u000b2\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0000¢\u0006\u0004\b\u0017\u0010\u0018J#\u0010\u001d\u001a\u00020\u001a2\b\u0010\u0005\u001a\u0004\u0018\u00010\u00042\b\u0010\u0006\u001a\u0004\u0018\u00010\u0004H\u0000¢\u0006\u0004\b\u001b\u0010\u001cR\u001d\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00120\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"R \u0010#\u001a\u00020\u00128\u0000X\u0081D¢\u0006\u0012\n\u0004\b#\u0010$\u0012\u0004\b'\u0010\u0003\u001a\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b(\u0010)R\u0014\u0010*\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b*\u0010)R\u0014\u0010+\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b+\u0010)R\u0014\u0010,\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b,\u0010)R\u0014\u0010-\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b-\u0010)R\u0014\u0010.\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b.\u0010)R\u0014\u0010/\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b/\u0010)R\u0014\u00100\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b0\u0010)R\u0014\u00101\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b1\u0010)R\u0014\u00102\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b2\u0010)R\u0014\u00103\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b3\u0010)R\u0014\u00104\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b4\u0010)R\u0014\u00105\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b5\u0010)R\u0014\u00106\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b6\u0010)R\u0014\u00107\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b7\u0010)R\u0014\u00108\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b8\u0010)R\u0014\u00109\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b9\u0010)R\u0014\u0010:\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b:\u0010)R\u0014\u0010;\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b;\u0010)R\u0014\u0010<\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b<\u0010)R\u0014\u0010=\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b=\u0010)R\u0014\u0010>\u001a\u00020\u00048\u0006X\u0086T¢\u0006\u0006\n\u0004\b>\u0010)¨\u0006?"}, d2 = {"Landroidx/credentials/playservices/controllers/CredentialProviderBaseController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "typeName", "msg", "Landroidx/credentials/exceptions/GetCredentialException;", "getCredentialExceptionTypeToException$credentials_play_services_auth", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/credentials/exceptions/GetCredentialException;", "getCredentialExceptionTypeToException", "Landroid/os/ResultReceiver;", "errName", "errMsg", _UrlKt.FRAGMENT_ENCODE_SET, "reportError$credentials_play_services_auth", "(Landroid/os/ResultReceiver;Ljava/lang/String;Ljava/lang/String;)V", "reportError", _UrlKt.FRAGMENT_ENCODE_SET, "requestCode", "resultCode", "Landroid/content/Intent;", "data", "reportResult$credentials_play_services_auth", "(Landroid/os/ResultReceiver;IILandroid/content/Intent;)V", "reportResult", "Landroidx/credentials/exceptions/CreateCredentialException;", "createCredentialExceptionTypeToException$credentials_play_services_auth", "(Ljava/lang/String;Ljava/lang/String;)Landroidx/credentials/exceptions/CreateCredentialException;", "createCredentialExceptionTypeToException", _UrlKt.FRAGMENT_ENCODE_SET, "retryables", "Ljava/util/Set;", "getRetryables", "()Ljava/util/Set;", "CONTROLLER_REQUEST_CODE", "I", "getCONTROLLER_REQUEST_CODE$credentials_play_services_auth", "()I", "getCONTROLLER_REQUEST_CODE$credentials_play_services_auth$annotations", "GET_CANCELED", "Ljava/lang/String;", "GET_INTERRUPTED", "GET_NO_CREDENTIALS", "GET_UNKNOWN", "CREATE_CANCELED", "CREATE_INTERRUPTED", "CREATE_UNKNOWN", "TYPE_TAG", "BEGIN_SIGN_IN_TAG", "SIGN_IN_INTENT_TAG", "CREATE_PASSWORD_TAG", "CREATE_PUBLIC_KEY_CREDENTIAL_TAG", "REQUEST_TAG", "RESULT_DATA_TAG", "EXTRA_FLOW_PENDING_INTENT", "EXTRA_DIGITAL_CREDENTIAL_INTENT", "EXTRA_ERROR_NAME", "FAILURE_RESPONSE_TAG", "EXCEPTION_TYPE_TAG", "EXCEPTION_MESSAGE_TAG", "ACTIVITY_REQUEST_CODE_TAG", "RESULT_RECEIVER_TAG", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final CreateCredentialException createCredentialExceptionTypeToException$credentials_play_services_auth(String r1, String r2) {
            if (Intrinsics.areEqual(r1, "CREATE_CANCELED") == false) goto L7;
            return new CreateCredentialCancellationException(r2);
        L7:
            if (Intrinsics.areEqual(r1, "CREATE_INTERRUPTED") == false) goto L11;
            return new CreateCredentialInterruptedException(r2);
        L11:
            return new CreateCredentialUnknownException(r2);
        }

        public final int getCONTROLLER_REQUEST_CODE$credentials_play_services_auth() {
            return CredentialProviderBaseController.access$getCONTROLLER_REQUEST_CODE$cp();
        }

        public final GetCredentialException getCredentialExceptionTypeToException$credentials_play_services_auth(String r2, String r3) {
            if (r2 == null) goto L25;
            int r1 = r2.hashCode();
            if (r1 == (-1567968963)) goto L21;
            if (r1 == (-154594663)) goto L16;
            if (r1 != 1996705159) goto L25;
            if (r2.equals("GET_NO_CREDENTIALS") == false) goto L25;
            return new NoCredentialException(r3);
        L16:
            if (r2.equals("GET_INTERRUPTED") == false) goto L25;
            return new GetCredentialInterruptedException(r3);
        L21:
            if (r2.equals("GET_CANCELED_TAG") == false) goto L25;
            return new GetCredentialCancellationException(r3);
        L25:
            return new GetCredentialUnknownException(r3);
        }

        public final Set<Integer> getRetryables() {
            return CredentialProviderBaseController.access$getRetryables$cp();
        }

        public final void reportError$credentials_play_services_auth(ResultReceiver r3, String r4, String r5) {
            Bundle r2 = new Bundle();
            r2.putBoolean("FAILURE_RESPONSE", true);
            r2.putString("EXCEPTION_TYPE", r4);
            r2.putString("EXCEPTION_MESSAGE", r5);
            r3.send(Integer.MAX_VALUE, r2);
        }

        public final void reportResult$credentials_play_services_auth(ResultReceiver r3, int r4, int r5, Intent r6) {
            Bundle r2 = new Bundle();
            r2.putBoolean("FAILURE_RESPONSE", false);
            r2.putInt("ACTIVITY_REQUEST_CODE", r4);
            r2.putParcelable("RESULT_DATA", r6);
            r3.send(r5, r2);
        }
    }

    static {
        Companion = new Companion(null);
        retryables = SetsKt.setOf(new Integer[]{7, 20});
        CONTROLLER_REQUEST_CODE = 1;
    }

    public CredentialProviderBaseController(Context r1) {
        this.context = r1;
    }

    public static final /* synthetic */ int access$getCONTROLLER_REQUEST_CODE$cp() {
        return CONTROLLER_REQUEST_CODE;
    }

    public static final /* synthetic */ Set access$getRetryables$cp() {
        return retryables;
    }

    public final void generateHiddenActivityIntent(ResultReceiver r2, Intent r3, String r4) {
        r3.putExtra("TYPE", r4);
        r3.putExtra("ACTIVITY_REQUEST_CODE", CONTROLLER_REQUEST_CODE);
        r3.putExtra("RESULT_RECEIVER", toIpcFriendlyResultReceiver(r2));
        r3.setFlags(65536);
    }

    public final <T extends ResultReceiver> ResultReceiver toIpcFriendlyResultReceiver(T r2) {
        Parcel r1 = Parcel.obtain();
        r2.writeToParcel(r1, 0);
        r1.setDataPosition(0);
        ResultReceiver r22 = (ResultReceiver) ResultReceiver.CREATOR.createFromParcel(r1);
        r1.recycle();
        return r22;
    }
}
