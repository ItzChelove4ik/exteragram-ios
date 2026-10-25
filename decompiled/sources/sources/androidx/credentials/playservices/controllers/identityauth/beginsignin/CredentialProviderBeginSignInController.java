package androidx.credentials.playservices.controllers.identityauth.beginsignin;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.PasswordCredential;
import androidx.credentials.PublicKeyCredential;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.identityauth.HiddenActivity;
import androidx.credentials.playservices.controllers.identityauth.createpublickeycredential.PublicKeyCredentialControllerUtility;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.BeginSignInResult;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.auth.api.identity.SignInCredential;
import com.google.android.gms.common.api.ApiException;
import com.google.android.libraries.identity.googleid.GoogleIdTokenCredential;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\b\u0005*\u00019\b\u0000\u0018\u0000 <2 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001<B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u000f\u001a\u00020\u0004H\u0002¢\u0006\u0004\b\u0011\u0010\u0012J=\u0010\u001b\u001a\u00020\u001a2\u0006\u0010\u0013\u001a\u00020\u00022\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00142\u0006\u0010\u0017\u001a\u00020\u00162\b\u0010\u0019\u001a\u0004\u0018\u00010\u0018H\u0016¢\u0006\u0004\b\u001b\u0010\u001cJ)\u0010$\u001a\u00020\u001a2\u0006\u0010\u001e\u001a\u00020\u001d2\u0006\u0010\u001f\u001a\u00020\u001d2\b\u0010!\u001a\u0004\u0018\u00010 H\u0000¢\u0006\u0004\b\"\u0010#J\u0017\u0010%\u001a\u00020\u00032\u0006\u0010\u0013\u001a\u00020\u0002H\u0017¢\u0006\u0004\b%\u0010&J\u0017\u0010'\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0004H\u0017¢\u0006\u0004\b'\u0010(R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010)R4\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00148\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0015\u0010*\u0012\u0004\b/\u00100\u001a\u0004\b+\u0010,\"\u0004\b-\u0010.R(\u0010\u0017\u001a\u00020\u00168\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0017\u00101\u0012\u0004\b6\u00100\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001e\u0010\u0019\u001a\u0004\u0018\u00010\u00188\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\u0019\u00107\u0012\u0004\b8\u00100R\u0014\u0010:\u001a\u0002098\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b:\u0010;¨\u0006="}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/beginsignin/CredentialProviderBeginSignInController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/GetCredentialRequest;", "Lcom/google/android/gms/auth/api/identity/BeginSignInRequest;", "Lcom/google/android/gms/auth/api/identity/SignInCredential;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", _UrlKt.FRAGMENT_ENCODE_SET, "e", "fromGmsException", "(Ljava/lang/Throwable;)Landroidx/credentials/exceptions/GetCredentialException;", PluginsConstants.RESPONSE, "Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", "createGoogleIdCredential", "(Lcom/google/android/gms/auth/api/identity/SignInCredential;)Lcom/google/android/libraries/identity/googleid/GoogleIdTokenCredential;", PluginsConstants.REQUEST, "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Landroidx/credentials/GetCredentialRequest;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", _UrlKt.FRAGMENT_ENCODE_SET, "uniqueRequestCode", "resultCode", "Landroid/content/Intent;", "data", "handleResponse$credentials_play_services_auth", "(IILandroid/content/Intent;)V", "handleResponse", "convertRequestToPlayServices", "(Landroidx/credentials/GetCredentialRequest;)Lcom/google/android/gms/auth/api/identity/BeginSignInRequest;", "convertResponseToCredentialManager", "(Lcom/google/android/gms/auth/api/identity/SignInCredential;)Landroidx/credentials/GetCredentialResponse;", "Landroid/content/Context;", "Landroidx/credentials/CredentialManagerCallback;", "getCallback", "()Landroidx/credentials/CredentialManagerCallback;", "setCallback", "(Landroidx/credentials/CredentialManagerCallback;)V", "getCallback$annotations", "()V", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "setExecutor", "(Ljava/util/concurrent/Executor;)V", "getExecutor$annotations", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "androidx/credentials/playservices/controllers/identityauth/beginsignin/CredentialProviderBeginSignInController$resultReceiver$1", "resultReceiver", "Landroidx/credentials/playservices/controllers/identityauth/beginsignin/CredentialProviderBeginSignInController$resultReceiver$1;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderBeginSignInController extends CredentialProviderController<GetCredentialRequest, BeginSignInRequest, SignInCredential, GetCredentialResponse, GetCredentialException> {
    public static final Companion Companion = null;
    public CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback;
    private CancellationSignal cancellationSignal;
    private final Context context;
    public Executor executor;
    private final CredentialProviderBeginSignInController$resultReceiver$1 resultReceiver;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0005\u001a\u00020\u00048\u0002X\u0082T¢\u0006\u0006\n\u0004\b\u0005\u0010\u0006¨\u0006\u0007"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/beginsignin/CredentialProviderBeginSignInController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", _UrlKt.FRAGMENT_ENCODE_SET, "TAG", "Ljava/lang/String;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$-b-lnp2JJ6BeraMH13F3mUAieEk, reason: not valid java name */
    public static /* synthetic */ void m690$r8$lambda$blnp2JJ6BeraMH13F3mUAieEk(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        handleResponse$lambda$1$0(r0, r1);
    }

    /* renamed from: $r8$lambda$1p-ZE-A12pkutKgK_hdgnOulY6Y, reason: not valid java name */
    public static Unit m691$r8$lambda$1pZEA12pkutKgK_hdgnOulY6Y(CancellationSignal r3, CredentialProviderBeginSignInController r4, BeginSignInResult r5) {
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r3) == true) goto L5;
        Intent r0 = new Intent(r4.context, HiddenActivity.class);
        r4.generateHiddenActivityIntent(r4.resultReceiver, r0, "BEGIN_SIGN_IN");
        r0.putExtra("EXTRA_FLOW_PENDING_INTENT", r5.getPendingIntent());
        r4.context.startActivity(r0);     // Catch: Exception -> L9
    L11:
        return Unit.INSTANCE;
    L9:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r3, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda14(r4));
        goto L11
    L5:
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$44MsiZlR-CUjGNnZfLkOd0Hys3k, reason: not valid java name */
    public static Unit m692$r8$lambda$44MsiZlRCUjGNnZfLkOd0Hys3k(CredentialProviderBeginSignInController r2, GetCredentialException r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda12(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$7zoW7PVK_eq8WIYlTX7GtGZEz6c(CredentialProviderBeginSignInController r0, GetCredentialResponse r1) {
        handleResponse$lambda$2$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$FSqmH0toWBBnlIPbA1fgpx8SLdQ(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        handleResponse$lambda$4$0(r0, r1);
    }

    /* renamed from: $r8$lambda$OYhwB46TeeE95vp-UZIRG4p_SnE, reason: not valid java name */
    public static /* synthetic */ Unit m693$r8$lambda$OYhwB46TeeE95vpUZIRG4p_SnE(CredentialProviderBeginSignInController r0) {
        return invokePlayServices$lambda$0$0(r0);
    }

    public static void $r8$lambda$X7C_ZlfIYxWiFWlSmjScArT1Nl0(CredentialProviderBeginSignInController r2, CancellationSignal r3, Exception r4) {
        GetCredentialException r42 = r2.fromGmsException(r4);
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r3, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda10(r2, r42));
    }

    public static /* synthetic */ void $r8$lambda$XT5K_AZvAW1ghUnM_MzUj7fBAsk(CredentialProviderBeginSignInController r0) {
        invokePlayServices$lambda$0$0$0(r0);
    }

    /* renamed from: $r8$lambda$XYSM6gKR0lvMg-lQxjkap1gMtlI, reason: not valid java name */
    public static Unit m694$r8$lambda$XYSM6gKR0lvMglQxjkap1gMtlI(CredentialProviderBeginSignInController r2, Ref.ObjectRef r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda13(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$_RaDe6ZUbrDHSJWX8gfn1FerUD0(CredentialProviderBeginSignInController r0, GetCredentialUnknownException r1) {
        handleResponse$lambda$5$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$bX94tmVfw35tJWeqEKGAQZzZkaQ(CredentialProviderBeginSignInController r0, Ref.ObjectRef r1) {
        handleResponse$lambda$3$0(r0, r1);
    }

    public static /* synthetic */ Unit $r8$lambda$eTZAmLqkj1qZUxDjGlpLTelvv2c(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        return invokePlayServices$lambda$2$0(r0, r1);
    }

    public static Unit $r8$lambda$g3Vvfv8fz2sC8IKs6dAYFNxPM9c(CredentialProviderBeginSignInController r2, GetCredentialResponse r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda9(r2, r3));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$hxvlTH6p-6Q13m2wUQfJXWRStzQ, reason: not valid java name */
    public static /* synthetic */ void m695$r8$lambda$hxvlTH6p6Q13m2wUQfJXWRStzQ(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        invokePlayServices$lambda$2$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$n7aSE3U-rjxKeAFoZqoUaZPIb7o, reason: not valid java name */
    public static Unit m696$r8$lambda$n7aSE3UrjxKeAFoZqoUaZPIb7o(CredentialProviderBeginSignInController r2, GetCredentialException r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda11(r2, r3));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$rKFuAgjwxDGa2qW9qjtGgxWEPEE(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$waf9J2Q2iu-Jv6ng6bWVjqCO3M4, reason: not valid java name */
    public static Unit m697$r8$lambda$waf9J2Q2iuJv6ng6bWVjqCO3M4(CredentialProviderBeginSignInController r2, GetCredentialUnknownException r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda15(r2, r3));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$yDFkb80k0YyfTzmOhBbyapDaNhs(CancellationSignal r1, Function0 r2) {
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r1, r2);
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderBeginSignInController(Context r2) {
        super(r2);
        this.context = r2;
        this.resultReceiver = new CredentialProviderBeginSignInController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));
    }

    public static final /* synthetic */ CancellationSignal access$getCancellationSignal$p(CredentialProviderBeginSignInController r0) {
        return r0.cancellationSignal;
    }

    public static final /* synthetic */ boolean access$maybeReportErrorFromResultReceiver(CredentialProviderBeginSignInController r0, Bundle r1, Function2 r2, Executor r3, CredentialManagerCallback r4, CancellationSignal r5) {
        return r0.maybeReportErrorFromResultReceiver(r1, r2, r3, r4, r5);
    }

    private final GoogleIdTokenCredential createGoogleIdCredential(SignInCredential r2) {
        GoogleIdTokenCredential.Builder r1 = new GoogleIdTokenCredential.Builder().setId(r2.getId()).setIdToken(r2.getGoogleIdToken());
        if (r2.getDisplayName() == null) goto L6;
        r1.setDisplayName(r2.getDisplayName());
    L6:
        if (r2.getGivenName() == null) goto L9;
        r1.setGivenName(r2.getGivenName());
    L9:
        if (r2.getFamilyName() == null) goto L12;
        r1.setFamilyName(r2.getFamilyName());
    L12:
        if (r2.getPhoneNumber() == null) goto L15;
        r1.setPhoneNumber(r2.getPhoneNumber());
    L15:
        if (r2.getProfilePictureUri() == null) goto L18;
        r1.setProfilePictureUri(r2.getProfilePictureUri());
    L18:
        return r1.build();
    }

    private final GetCredentialException fromGmsException(Throwable r4) {
        if ((r4 instanceof ApiException) == true) goto L5;
    L7:
        String r3 = "GET_NO_CREDENTIALS";
    L9:
        return CredentialProviderBaseController.Companion.getCredentialExceptionTypeToException$credentials_play_services_auth(r3, "During begin sign in, failure response from one tap: " + r4.getMessage());
    L5:
        if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) r4).getStatusCode())) == false) goto L7;
        r3 = "GET_INTERRUPTED";
        goto L9
    }

    private static final void handleResponse$lambda$1$0(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        r0.getCallback().onError(r1);
    }

    private static final void handleResponse$lambda$2$0(CredentialProviderBeginSignInController r0, GetCredentialResponse r1) {
        r0.getCallback().onResult(r1);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final void handleResponse$lambda$3$0(CredentialProviderBeginSignInController r0, Ref.ObjectRef r1) {
        r0.getCallback().onError(r1.element);
    }

    private static final void handleResponse$lambda$4$0(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        r0.getCallback().onError(r1);
    }

    private static final void handleResponse$lambda$5$0(CredentialProviderBeginSignInController r0, GetCredentialUnknownException r1) {
        r0.getCallback().onError(r1);
    }

    private static final Unit invokePlayServices$lambda$0$0(CredentialProviderBeginSignInController r2) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda16(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$0$0(CredentialProviderBeginSignInController r2) {
        r2.getCallback().onError(new GetCredentialUnknownException("Failed to launch the selector UI. Hint: ensure the `context` parameter is an Activity-based context."));
    }

    private static final Unit invokePlayServices$lambda$2$0(CredentialProviderBeginSignInController r2, GetCredentialException r3) {
        r2.getExecutor().execute(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda17(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$2$0$0(CredentialProviderBeginSignInController r0, GetCredentialException r1) {
        r0.getCallback().onError(r1);
    }

    public BeginSignInRequest convertRequestToPlayServices(GetCredentialRequest r2) {
        return BeginSignInControllerUtility.Companion.constructBeginSignInRequest$credentials_play_services_auth(r2, this.context);
    }

    public GetCredentialResponse convertResponseToCredentialManager(SignInCredential r2) {
        if (r2.getPassword() == null) goto L6;
        Credential r1 = new PasswordCredential(r2.getId(), r2.getPassword());
    L12:
        if (r1 == null) goto L16;
        return new GetCredentialResponse(r1);
    L16:
        throw new GetCredentialUnknownException("When attempting to convert get response, null credential found");
    L6:
        if (r2.getGoogleIdToken() == null) goto L9;
        r1 = createGoogleIdCredential(r2);
        goto L12
    L9:
        if (r2.getPublicKeyCredential() == null) goto L11;
        r1 = new PublicKeyCredential(PublicKeyCredentialControllerUtility.Companion.toAssertPasskeyResponse(r2));
        goto L12
    L11:
        Log.w("BeginSignIn", "Credential returned but no google Id or password or passkey found");
        r1 = null;
        goto L12
    }

    public final CredentialManagerCallback<GetCredentialResponse, GetCredentialException> getCallback() {
        CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r0 = this.callback;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    public final Executor getExecutor() {
        Executor r0 = this.executor;
        if (r0 == null) goto L5;
        return r0;
    L5:
        return null;
    }

    /* JADX WARN: Type inference failed for: r6v2, types: [T, androidx.credentials.exceptions.GetCredentialUnknownException] */
    /* JADX WARN: Type inference failed for: r6v7, types: [T, androidx.credentials.exceptions.GetCredentialInterruptedException] */
    /* JADX WARN: Type inference failed for: r6v9, types: [T, androidx.credentials.exceptions.GetCredentialCancellationException] */
    public final void handleResponse$credentials_play_services_auth(int r4, int r5, Intent r6) {
        CredentialProviderBaseController.Companion r0 = CredentialProviderBaseController.Companion;
        if (r4 == r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth()) goto L6;
        Log.w("BeginSignIn", "Returned request code " + r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth() + " which  does not match what was given " + r4);
        return;
    L6:
        CredentialProviderController.Companion r42 = CredentialProviderController.Companion;
        if (r42.maybeReportErrorResultCodeGet$credentials_play_services_auth(r5, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda3(), new CredentialProviderBeginSignInController$$ExternalSyntheticLambda4(this), this.cancellationSignal) == true) goto L31;
        r42.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda5(this, convertResponseToCredentialManager(Identity.getSignInClient(this.context).getSignInCredentialFromIntent(r6))));     // Catch: Throwable -> L11 GetCredentialException -> L13 ApiException -> L15
        return;
    L13:
        e = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda7(this, e));
        return;
    L15:
        e = move-exception;
        Ref.ObjectRef r52 = new Ref.ObjectRef();
        r52.element = new GetCredentialUnknownException(e.getMessage());
        if (e.getStatusCode() != 16) goto L24;
        r52.element = new GetCredentialCancellationException(e.getMessage());
    L26:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda6(this, r52));
        return;
    L24:
        if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(e.getStatusCode())) == false) goto L26;
        r52.element = new GetCredentialInterruptedException(e.getMessage());
    L11:
        th = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderBeginSignInController$$ExternalSyntheticLambda8(this, new GetCredentialUnknownException(th.getMessage())));
        return;
    }

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(GetCredentialRequest r1, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r2, Executor r3, CancellationSignal r4) {
        this.cancellationSignal = r4;
        setCallback(r2);
        setExecutor(r3);
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r4) == false) goto L5;
        return;
    L5:
        BeginSignInRequest r12 = convertRequestToPlayServices(r1);
        Identity.getSignInClient(this.context).beginSignIn(r12).addOnSuccessListener(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda1(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda0(r4, this))).addOnFailureListener(new CredentialProviderBeginSignInController$$ExternalSyntheticLambda2(this, r4));
    }

    @Override // androidx.credentials.playservices.controllers.CredentialProviderController
    public /* bridge */ /* synthetic */ void invokePlayServices(GetCredentialRequest r1, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r2, Executor r3, CancellationSignal r4) {
        invokePlayServices2(r1, r2, r3, r4);
    }

    public final void setCallback(CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r1) {
        this.callback = r1;
    }

    public final void setExecutor(Executor r1) {
        this.executor = r1;
    }
}
