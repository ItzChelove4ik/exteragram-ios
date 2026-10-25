package androidx.credentials.playservices.controllers.identitycredentials.createpublickeycredential;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CreatePublicKeyCredentialResponse;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialInterruptedException;
import androidx.credentials.exceptions.CreateCredentialNoCreateOptionException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.CreateCredentialUnsupportedException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.identityauth.HiddenActivity;
import androidx.credentials.playservices.controllers.identityauth.createpublickeycredential.CredentialProviderCreatePublicKeyCredentialController;
import androidx.credentials.provider.PendingIntentHandler;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.UnsupportedApiCallException;
import com.google.android.gms.identitycredentials.CreateCredentialHandle;
import com.google.android.gms.identitycredentials.CreateCredentialRequest;
import com.google.android.gms.identitycredentials.CreateCredentialResponse;
import com.google.android.gms.identitycredentials.IdentityCredentialManager;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000]\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0010\n\u0002\b\u0005*\u0001.\b\u0001\u0018\u0000 12 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u00011B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ=\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u000b\u001a\u00020\u00022\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\f2\u0006\u0010\u000f\u001a\u00020\u000e2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0010H\u0016¢\u0006\u0004\b\u0013\u0010\u0014J)\u0010\u001c\u001a\u00020\u00122\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0000¢\u0006\u0004\b\u001a\u0010\u001bJ\u0015\u0010\u001f\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u001d¢\u0006\u0004\b\u001f\u0010 J\u0017\u0010!\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u0002H\u0016¢\u0006\u0004\b!\u0010\"J\u0017\u0010$\u001a\u00020\u00052\u0006\u0010#\u001a\u00020\u0004H\u0014¢\u0006\u0004\b$\u0010%R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010&R(\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\f8\u0002@\u0002X\u0083.¢\u0006\f\n\u0004\b\r\u0010'\u0012\u0004\b(\u0010)R\u001c\u0010\u000f\u001a\u00020\u000e8\u0002@\u0002X\u0083.¢\u0006\f\n\u0004\b\u000f\u0010*\u0012\u0004\b+\u0010)R\u001e\u0010\u0011\u001a\u0004\u0018\u00010\u00108\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\u0011\u0010,\u0012\u0004\b-\u0010)R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00062"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/createpublickeycredential/CreatePublicKeyCredentialController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/CreatePublicKeyCredentialRequest;", "Lcom/google/android/gms/identitycredentials/CreateCredentialRequest;", "Lcom/google/android/gms/identitycredentials/CreateCredentialResponse;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", PluginsConstants.REQUEST, "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Landroidx/credentials/CreatePublicKeyCredentialRequest;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", _UrlKt.FRAGMENT_ENCODE_SET, "uniqueRequestCode", "resultCode", "Landroid/content/Intent;", "data", "handleResponse$credentials_play_services_auth", "(IILandroid/content/Intent;)V", "handleResponse", _UrlKt.FRAGMENT_ENCODE_SET, "e", "fromGmsException", "(Ljava/lang/Throwable;)Landroidx/credentials/exceptions/CreateCredentialException;", "convertRequestToPlayServices", "(Landroidx/credentials/CreatePublicKeyCredentialRequest;)Lcom/google/android/gms/identitycredentials/CreateCredentialRequest;", PluginsConstants.RESPONSE, "convertResponseToCredentialManager", "(Lcom/google/android/gms/identitycredentials/CreateCredentialResponse;)Landroidx/credentials/CreateCredentialResponse;", "Landroid/content/Context;", "Landroidx/credentials/CredentialManagerCallback;", "getCallback$annotations", "()V", "Ljava/util/concurrent/Executor;", "getExecutor$annotations", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "androidx/credentials/playservices/controllers/identitycredentials/createpublickeycredential/CreatePublicKeyCredentialController$resultReceiver$1", "resultReceiver", "Landroidx/credentials/playservices/controllers/identitycredentials/createpublickeycredential/CreatePublicKeyCredentialController$resultReceiver$1;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CreatePublicKeyCredentialController extends CredentialProviderController<CreatePublicKeyCredentialRequest, CreateCredentialRequest, CreateCredentialResponse, androidx.credentials.CreateCredentialResponse, CreateCredentialException> {
    public static final Companion Companion = null;
    private CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> callback;
    private CancellationSignal cancellationSignal;
    private final Context context;
    private Executor executor;
    private final CreatePublicKeyCredentialController$resultReceiver$1 resultReceiver;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/createpublickeycredential/CreatePublicKeyCredentialController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "getInstance", "Landroidx/credentials/playservices/controllers/identitycredentials/createpublickeycredential/CreatePublicKeyCredentialController;", "context", "Landroid/content/Context;", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CreatePublicKeyCredentialController getInstance(Context r1) {
            return new CreatePublicKeyCredentialController(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$6BTjUpmKVr3y_fTTYfRsim6m_5w(CreatePublicKeyCredentialController r0) {
        invokePlayServices$lambda$0$1$0(r0);
    }

    public static Unit $r8$lambda$7Pbaj8QY0qWYXijdVr1avx6m7cE(CreatePublicKeyCredentialController r2, androidx.credentials.CreateCredentialResponse r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda12(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$87J9bQWIR7JRQRUDAAURv25N6wY(CreatePublicKeyCredentialController r0, androidx.credentials.CreateCredentialResponse r1) {
        handleResponse$lambda$3$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$BRPev__kVq7w295qbsSiomk3uFU(CredentialManagerCallback r0, CreateCredentialException r1) {
        invokePlayServices$lambda$2$0$0(r0, r1);
    }

    public static /* synthetic */ Unit $r8$lambda$DBCHD8NI2Uy6ofqKR01l45B4FoY(Executor r0, CredentialManagerCallback r1, androidx.credentials.CreateCredentialResponse r2) {
        return invokePlayServices$lambda$0$2(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$EktFgG8fKJBAiksRwFhMIhMprWQ(CredentialManagerCallback r0) {
        invokePlayServices$lambda$0$3$0(r0);
    }

    /* renamed from: $r8$lambda$H8m58jFF48xOVIW7wbb-qAohYTk, reason: not valid java name */
    public static /* synthetic */ void m711$r8$lambda$H8m58jFF48xOVIW7wbbqAohYTk(CredentialManagerCallback r0) {
        invokePlayServices$lambda$0$0$0(r0);
    }

    public static /* synthetic */ Unit $r8$lambda$M0vMk2hb_HCwjo7bcMkaBfxRMpo(CreatePublicKeyCredentialController r0, Exception r1, Executor r2, CredentialManagerCallback r3) {
        return invokePlayServices$lambda$2$0(r0, r1, r2, r3);
    }

    public static void $r8$lambda$R0XYJePXIB2wqkqFXoKnFQ23syc(CreatePublicKeyCredentialRequest r2, CreatePublicKeyCredentialController r3, CredentialManagerCallback r4, Executor r5, CancellationSignal r6, Exception r7) {
        if (r2.isConditional() == true) goto L6;
        Log.w("CreatePublicKey", "Pre-u credman PK create flow failed " + r7 + "; retrying with gis flow");
        CredentialProviderCreatePublicKeyCredentialController.Companion.getInstance(r3.context).invokePlayServices2(r2, r4, r5, r6);
        return;
    L6:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r6, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda15(r3, r7, r5, r4));
    }

    public static /* synthetic */ void $r8$lambda$RI_yp9D8O45MTQNTh9uwcvGFNWk(CreatePublicKeyCredentialController r0) {
        handleResponse$lambda$2$0(r0);
    }

    /* renamed from: $r8$lambda$TIoKY2xikd5hwSIbqw-Kayv1nqw, reason: not valid java name */
    public static Unit m712$r8$lambda$TIoKY2xikd5hwSIbqwKayv1nqw(CreatePublicKeyCredentialController r2, CreateCredentialException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda16(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Unit $r8$lambda$Wk2wV1qTJBl56nji4Ebk_U1kGKY(CreatePublicKeyCredentialController r0) {
        return invokePlayServices$lambda$0$1(r0);
    }

    /* renamed from: $r8$lambda$a_6C8FvoS0-mJrJ_QxqiYGSfhnQ, reason: not valid java name */
    public static Unit m713$r8$lambda$a_6C8FvoS0mJrJ_QxqiYGSfhnQ(CreatePublicKeyCredentialController r2) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda14(r2));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$bBGaLg-ROEB-WUhoK5OSTerPB8U, reason: not valid java name */
    public static /* synthetic */ void m714$r8$lambda$bBGaLgROEBWUhoK5OSTerPB8U(CreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        handleResponse$lambda$1$0(r0, r1);
    }

    public static Unit $r8$lambda$cXqt7RJ41JfjJIeUD7hpYzA5MEM(CreatePublicKeyCredentialController r2, CreateCredentialException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda13(r2, r3));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$eKqAcBOVoI3C5613LPG-ls3vZIc, reason: not valid java name */
    public static /* synthetic */ void m715$r8$lambda$eKqAcBOVoI3C5613LPGls3vZIc(CreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        handleResponse$lambda$4$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$lNb_ZF5uCy7UxIj9iUzcU3gyEYM(CredentialManagerCallback r0, androidx.credentials.CreateCredentialResponse r1) {
        invokePlayServices$lambda$0$2$0(r0, r1);
    }

    public static Unit $r8$lambda$qGhktyn4yrwbaYVeRIYX3YUyRnU(CancellationSignal r4, CreatePublicKeyCredentialController r5, Executor r6, CredentialManagerCallback r7, CreateCredentialHandle r8) {
        PendingIntent r0 = r8.getPendingIntent();
        CreateCredentialResponse r82 = r8.getCreateCredentialResponse();
        if (r0 != null) goto L7;
        if (r82 != null) goto L7;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda8(r6, r7));
        return Unit.INSTANCE;
    L7:
        if (r0 == null) goto L12;
        Intent r1 = new Intent(r5.context, HiddenActivity.class);
        r5.generateHiddenActivityIntent(r5.resultReceiver, r1, "CREATE_PUBLIC_KEY_CREDENTIAL");
        r1.putExtra("EXTRA_FLOW_PENDING_INTENT", r0);
        r5.context.startActivity(r1);     // Catch: Exception -> L11
    L11:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda9(r5));
    L12:
        if (r82 == null) goto L16;
        androidx.credentials.CreateCredentialResponse r52 = r5.convertResponseToCredentialManager(r82);
        if ((r52 instanceof CreatePublicKeyCredentialResponse) == false) goto L16;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda10(r6, r7, r52));
        return Unit.INSTANCE;
    L16:
        if (r0 != null) goto L19;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda11(r6, r7));
    L19:
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$s6bjXkRcKG2n8yTP9Si_H23lVVA(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$tYbf8Lmz5Gwdn2OkpxSCygD-a34, reason: not valid java name */
    public static Unit m716$r8$lambda$tYbf8Lmz5Gwdn2OkpxSCygDa34(CancellationSignal r1, Function0 r2) {
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r1, r2);
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$yvnKpwglMNiBQxT4pAHzT8tD-Fs, reason: not valid java name */
    public static /* synthetic */ Unit m717$r8$lambda$yvnKpwglMNiBQxT4pAHzT8tDFs(Executor r0, CredentialManagerCallback r1) {
        return invokePlayServices$lambda$0$3(r0, r1);
    }

    /* renamed from: $r8$lambda$zkGnpDDNsVFwHCJOiX-5XJVtI70, reason: not valid java name */
    public static /* synthetic */ Unit m718$r8$lambda$zkGnpDDNsVFwHCJOiX5XJVtI70(Executor r0, CredentialManagerCallback r1) {
        return invokePlayServices$lambda$0$0(r0, r1);
    }

    static {
        Companion = new Companion(null);
    }

    public CreatePublicKeyCredentialController(Context r2) {
        super(r2);
        this.context = r2;
        this.resultReceiver = new CreatePublicKeyCredentialController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));
    }

    public static final /* synthetic */ CredentialManagerCallback access$getCallback$p(CreatePublicKeyCredentialController r0) {
        return r0.callback;
    }

    public static final /* synthetic */ CancellationSignal access$getCancellationSignal$p(CreatePublicKeyCredentialController r0) {
        return r0.cancellationSignal;
    }

    public static final /* synthetic */ Executor access$getExecutor$p(CreatePublicKeyCredentialController r0) {
        return r0.executor;
    }

    public static final /* synthetic */ boolean access$maybeReportErrorFromResultReceiver(CreatePublicKeyCredentialController r0, Bundle r1, Function2 r2, Executor r3, CredentialManagerCallback r4, CancellationSignal r5) {
        return r0.maybeReportErrorFromResultReceiver(r1, r2, r3, r4, r5);
    }

    private static final void handleResponse$lambda$1$0(CreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onError(r1);
    }

    private static final void handleResponse$lambda$2$0(CreatePublicKeyCredentialController r2) {
        CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r22 = r2.callback;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.onError(new CreateCredentialUnknownException("No provider data returned."));
    }

    private static final void handleResponse$lambda$3$0(CreatePublicKeyCredentialController r0, androidx.credentials.CreateCredentialResponse r1) {
        CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onResult(r1);
    }

    private static final void handleResponse$lambda$4$0(CreatePublicKeyCredentialController r1, CreateCredentialException r2) {
        CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r12 = r1.callback;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        if (r2 != null) goto L7;
        r2 = new CreateCredentialUnknownException("No provider data returned");
    L7:
        r12.onError(r2);
    }

    private static final Unit invokePlayServices$lambda$0$0(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda17(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$0$0(CredentialManagerCallback r3) {
        r3.onError(new CreateCredentialUnknownException(null, 1, null));
    }

    private static final Unit invokePlayServices$lambda$0$1(CreatePublicKeyCredentialController r2) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda20(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$1$0(CreatePublicKeyCredentialController r2) {
        CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r22 = r2.callback;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.onError(new CreateCredentialUnknownException("Failed to launch the selector UI. Hint: ensure the `context` parameter is an Activity-based context."));
    }

    private static final Unit invokePlayServices$lambda$0$2(Executor r1, CredentialManagerCallback r2, androidx.credentials.CreateCredentialResponse r3) {
        r1.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda19(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$2$0(CredentialManagerCallback r0, androidx.credentials.CreateCredentialResponse r1) {
        r0.onResult(r1);
    }

    private static final Unit invokePlayServices$lambda$0$3(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda21(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$3$0(CredentialManagerCallback r3) {
        r3.onError(new CreateCredentialUnknownException(null, 1, null));
    }

    private static final Unit invokePlayServices$lambda$2$0(CreatePublicKeyCredentialController r0, Exception r1, Executor r2, CredentialManagerCallback r3) {
        r2.execute(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda18(r3, r0.fromGmsException(r1)));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$2$0$0(CredentialManagerCallback r0, CreateCredentialException r1) {
        r0.onError(r1);
    }

    public CreateCredentialRequest convertRequestToPlayServices(CreatePublicKeyCredentialRequest r8) {
        return new CreateCredentialRequest(r8.getType(), r8.getCredentialData(), r8.getCandidateQueryData(), r8.getOrigin(), r8.getRequestJson(), null);
    }

    public androidx.credentials.CreateCredentialResponse convertResponseToCredentialManager(CreateCredentialResponse r2) {
        return androidx.credentials.CreateCredentialResponse.Companion.createFrom(r2.getType(), r2.getData());
    }

    public final CreateCredentialException fromGmsException(Throwable r3) {
        if ((r3 instanceof ApiException) == false) goto L23;
        int r2 = ((ApiException) r3).getStatusCode();
        if (r2 != 16) goto L9;
        return new CreateCredentialCancellationException(r3.getMessage());
    L9:
        if (r2 != 17) goto L13;
        return new CreateCredentialUnsupportedException("API is not supported: " + r3.getMessage());
    L13:
        if (r2 != 8) goto L17;
        return new CreateCredentialNoCreateOptionException(r3.getMessage());
    L17:
        if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(r2)) == false) goto L21;
        return new CreateCredentialInterruptedException(r3.getMessage());
    L21:
        return new CreateCredentialUnknownException("Conditional create failed, failure: " + r3.getMessage());
    L23:
        if ((r3 instanceof UnsupportedApiCallException) == false) goto L27;
        return new CreateCredentialUnsupportedException("API is unsupported");
    L27:
        return new CreateCredentialUnknownException("Conditional create failed, failure: " + r3);
    }

    public final void handleResponse$credentials_play_services_auth(int r3, int r4, Intent r5) {
        CredentialProviderBaseController.Companion r0 = CredentialProviderBaseController.Companion;
        if (r3 == r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth()) goto L7;
        Log.w("CreatePublicKey", "Returned request code " + r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth() + " does not match what was given " + r3);
        return;
    L7:
        if (CredentialProviderController.maybeReportErrorResultCodeCreate(r4, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda3(), new CreatePublicKeyCredentialController$$ExternalSyntheticLambda4(this), this.cancellationSignal) == false) goto L9;
        return;
    L9:
        if (r5 != null) goto L12;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda5(this));
        return;
    L12:
        PendingIntentHandler.Companion r32 = PendingIntentHandler.Companion;
        androidx.credentials.CreateCredentialResponse r42 = r32.retrieveCreateCredentialResponse("androidx.credentials.TYPE_PUBLIC_KEY_CREDENTIAL", r5);
        if (r42 == null) goto L16;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda6(this, r42));
        return;
    L16:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CreatePublicKeyCredentialController$$ExternalSyntheticLambda7(this, r32.retrieveCreateCredentialException(r5)));
    }

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(CreatePublicKeyCredentialRequest r8, CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r9, Executor r10, CancellationSignal r11) {
        this.cancellationSignal = r11;
        this.callback = r9;
        this.executor = r10;
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r11) == false) goto L5;
        return;
    L5:
        CreateCredentialRequest r0 = convertRequestToPlayServices(r8);
        IdentityCredentialManager.Companion.getClient(this.context).createCredential(r0).addOnSuccessListener(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda1(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda0(r11, this, r10, r9))).addOnFailureListener(new CreatePublicKeyCredentialController$$ExternalSyntheticLambda2(r8, this, r9, r10, r11));
    }

    @Override // androidx.credentials.playservices.controllers.CredentialProviderController
    public /* bridge */ /* synthetic */ void invokePlayServices(CreatePublicKeyCredentialRequest r1, CredentialManagerCallback<androidx.credentials.CreateCredentialResponse, CreateCredentialException> r2, Executor r3, CancellationSignal r4) {
        invokePlayServices2(r1, r2, r3, r4);
    }
}
