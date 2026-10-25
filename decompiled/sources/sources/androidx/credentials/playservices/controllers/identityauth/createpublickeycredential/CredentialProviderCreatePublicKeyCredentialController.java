package androidx.credentials.playservices.controllers.identityauth.createpublickeycredential;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CreatePublicKeyCredentialResponse;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.domerrors.EncodingError;
import androidx.credentials.exceptions.domerrors.UnknownError;
import androidx.credentials.exceptions.publickeycredential.CreatePublicKeyCredentialDomException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.identityauth.HiddenActivity;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.fido.Fido;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredential;
import com.google.android.gms.fido.fido2.api.common.PublicKeyCredentialCreationOptions;
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
import org.json.JSONException;

@Metadata(d1 = {"\u0000k\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0011\n\u0002\b\u0005*\u00013\b\u0000\u0018\u0000 62 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u00016B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ\u0017\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0010\u001a\u00020\u000fH\u0002¢\u0006\u0004\b\u0012\u0010\u0013J=\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u0014\u001a\u00020\u00022\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00152\u0006\u0010\u0018\u001a\u00020\u00172\b\u0010\u001a\u001a\u0004\u0018\u00010\u0019H\u0016¢\u0006\u0004\b\u001c\u0010\u001dJ)\u0010%\u001a\u00020\u001b2\u0006\u0010\u001f\u001a\u00020\u001e2\u0006\u0010 \u001a\u00020\u001e2\b\u0010\"\u001a\u0004\u0018\u00010!H\u0000¢\u0006\u0004\b#\u0010$J\u0017\u0010&\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u0002H\u0017¢\u0006\u0004\b&\u0010'J\u0017\u0010)\u001a\u00020\u00052\u0006\u0010(\u001a\u00020\u0004H\u0017¢\u0006\u0004\b)\u0010*R\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010+R(\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00158\u0002@\u0002X\u0083.¢\u0006\f\n\u0004\b\u0016\u0010,\u0012\u0004\b-\u0010.R\u001c\u0010\u0018\u001a\u00020\u00178\u0002@\u0002X\u0083.¢\u0006\f\n\u0004\b\u0018\u0010/\u0012\u0004\b0\u0010.R\u001e\u0010\u001a\u001a\u0004\u0018\u00010\u00198\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\u001a\u00101\u0012\u0004\b2\u0010.R\u0014\u00104\u001a\u0002038\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b4\u00105¨\u00067"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/CreatePublicKeyCredentialRequest;", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", "Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredential;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", _UrlKt.FRAGMENT_ENCODE_SET, "e", "fromIntentRequestException", "(Ljava/lang/Throwable;)Landroidx/credentials/exceptions/CreateCredentialException;", "Lorg/json/JSONException;", "exception", "Landroidx/credentials/exceptions/publickeycredential/CreatePublicKeyCredentialDomException;", "JSONExceptionToPKCError", "(Lorg/json/JSONException;)Landroidx/credentials/exceptions/publickeycredential/CreatePublicKeyCredentialDomException;", PluginsConstants.REQUEST, "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Landroidx/credentials/CreatePublicKeyCredentialRequest;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", _UrlKt.FRAGMENT_ENCODE_SET, "uniqueRequestCode", "resultCode", "Landroid/content/Intent;", "data", "handleResponse$credentials_play_services_auth", "(IILandroid/content/Intent;)V", "handleResponse", "convertRequestToPlayServices", "(Landroidx/credentials/CreatePublicKeyCredentialRequest;)Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredentialCreationOptions;", PluginsConstants.RESPONSE, "convertResponseToCredentialManager", "(Lcom/google/android/gms/fido/fido2/api/common/PublicKeyCredential;)Landroidx/credentials/CreateCredentialResponse;", "Landroid/content/Context;", "Landroidx/credentials/CredentialManagerCallback;", "getCallback$annotations", "()V", "Ljava/util/concurrent/Executor;", "getExecutor$annotations", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "androidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1", "resultReceiver", "Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderCreatePublicKeyCredentialController extends CredentialProviderController<CreatePublicKeyCredentialRequest, PublicKeyCredentialCreationOptions, PublicKeyCredential, CreateCredentialResponse, CreateCredentialException> {
    public static final Companion Companion = null;
    private CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> callback;
    private CancellationSignal cancellationSignal;
    private final Context context;
    private Executor executor;
    private final CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1 resultReceiver;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "getInstance", "Landroidx/credentials/playservices/controllers/identityauth/createpublickeycredential/CredentialProviderCreatePublicKeyCredentialController;", "context", "Landroid/content/Context;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final CredentialProviderCreatePublicKeyCredentialController getInstance(Context r1) {
            return new CredentialProviderCreatePublicKeyCredentialController(r1);
        }
    }

    public static Unit $r8$lambda$1xal5EcOrqxKwSMl2tXgYESvv4g(CredentialProviderCreatePublicKeyCredentialController r2, CreateCredentialException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda17(r2, r3));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$99zoIljkziZQzqdKcgsI2u3UXZA(CancellationSignal r1, Function0 r2) {
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r1, r2);
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$9s975gPjMQzn4uXzSUk4O9I2hKI(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    /* renamed from: $r8$lambda$AeGo3nOtF54WcYicQlEAv-rOAcg, reason: not valid java name */
    public static /* synthetic */ void m698$r8$lambda$AeGo3nOtF54WcYicQlEAvrOAcg(CredentialProviderCreatePublicKeyCredentialController r0, Throwable r1) {
        handleResponse$lambda$6$0(r0, r1);
    }

    public static Unit $r8$lambda$EHEMpmxLLDuRrBEOfqAj5NAw998(CredentialProviderCreatePublicKeyCredentialController r2, Throwable r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda16(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Unit $r8$lambda$GvK00uElfYLpBvi_bqSrzcaE3Io(CredentialProviderCreatePublicKeyCredentialController r0) {
        return invokePlayServices$lambda$2$0(r0);
    }

    public static Unit $r8$lambda$J4DokRjT_UNYCgGxPTnL9VjN24U(CredentialProviderCreatePublicKeyCredentialController r2, Throwable r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda14(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$JQpD973YytEMzThng4Yv_N47YvY(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialResponse r1) {
        handleResponse$lambda$4$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$V34xah0kXeEspVi57vwVreq9Wjw(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        handleResponse$lambda$3$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$VUqNJAznfCJ24r3aYgFf05ZDxj0(CredentialProviderCreatePublicKeyCredentialController r0, Throwable r1) {
        invokePlayServices$lambda$1$0(r0, r1);
    }

    /* renamed from: $r8$lambda$bCd5Jy2AYJhGXNLDNqzJGx-_OPo, reason: not valid java name */
    public static /* synthetic */ void m699$r8$lambda$bCd5Jy2AYJhGXNLDNqzJGx_OPo(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        handleResponse$lambda$1$0(r0, r1);
    }

    public static void $r8$lambda$h1ZWD_XzS4_A2cK0LC8hIyymuW0(CredentialProviderCreatePublicKeyCredentialController r3) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r32 = r3.callback;
        if (r32 != null) goto L5;
        r32 = null;
    L5:
        r32.onError(new CreatePublicKeyCredentialDomException(new UnknownError(), "Upon handling create public key credential response, fido module giving null bytes indicating internal error"));
    }

    /* renamed from: $r8$lambda$hFe-9y1VyKDgbIJEMfGGnLnEIuE, reason: not valid java name */
    public static Unit m700$r8$lambda$hFe9y1VyKDgbIJEMfGGnLnEIuE(CredentialProviderCreatePublicKeyCredentialController r2, CreateCredentialException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda20(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$hifV1KO6O5d6x10tHqihqNqiriQ(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        invokePlayServices$lambda$4$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$iKFfRkDUQzn61dV-PjEc3lKL3iA, reason: not valid java name */
    public static /* synthetic */ Unit m701$r8$lambda$iKFfRkDUQzn61dVPjEc3lKL3iA(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        return invokePlayServices$lambda$4$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$j844zYhrR_UMnzixbciHAsqwfRM(CredentialProviderCreatePublicKeyCredentialController r0, JSONException r1) {
        invokePlayServices$lambda$0$0(r0, r1);
    }

    public static Unit $r8$lambda$p2ejyZfFzISnqWGB5xuk0Ww1tK8(CredentialProviderCreatePublicKeyCredentialController r2, JSONException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda13(r2, r3));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$pPG6tl2TOCJ1aQHZ-L9-lkML7nc, reason: not valid java name */
    public static /* synthetic */ void m702$r8$lambda$pPG6tl2TOCJ1aQHZL9lkML7nc(CredentialProviderCreatePublicKeyCredentialController r0) {
        invokePlayServices$lambda$2$0$0(r0);
    }

    public static Unit $r8$lambda$tMlp5CR9RweECHA2WWRJ6onKrVY(CredentialProviderCreatePublicKeyCredentialController r2, CreateCredentialResponse r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda15(r2, r3));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$todgVpRpq5O2Vis5Z7Mgc5ediXo(CredentialProviderCreatePublicKeyCredentialController r2, CancellationSignal r3, Exception r4) {
        CreateCredentialException r42 = r2.fromIntentRequestException(r4);
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r3, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda19(r2, r42));
    }

    public static /* synthetic */ void $r8$lambda$xG7aC36yEeU3rzUN41d2He3Ikvk(CredentialProviderCreatePublicKeyCredentialController r0, JSONException r1) {
        handleResponse$lambda$5$0(r0, r1);
    }

    public static Unit $r8$lambda$xh5VtBVOIlmQ20dRuDYGIUKJzjc(CancellationSignal r3, CredentialProviderCreatePublicKeyCredentialController r4, PendingIntent r5) {
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r3) == true) goto L5;
        Intent r0 = new Intent(r4.context, HiddenActivity.class);
        r4.generateHiddenActivityIntent(r4.resultReceiver, r0, "CREATE_PUBLIC_KEY_CREDENTIAL");
        r0.putExtra("EXTRA_FLOW_PENDING_INTENT", r5);
        r4.context.startActivity(r0);     // Catch: Exception -> L9
    L11:
        return Unit.INSTANCE;
    L9:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r3, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda18(r4));
        goto L11
    L5:
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$zIoiRx81t3DKhQPivKDCKHlmFUk(CredentialProviderCreatePublicKeyCredentialController r2, JSONException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda12(r2, r3));
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderCreatePublicKeyCredentialController(Context r2) {
        super(r2);
        this.context = r2;
        this.resultReceiver = new CredentialProviderCreatePublicKeyCredentialController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));
    }

    private final CreatePublicKeyCredentialDomException JSONExceptionToPKCError(JSONException r2) {
        String r1 = r2.getMessage();
        if (r1 == null) goto L9;
        if (r1.length() <= 0) goto L9;
        return new CreatePublicKeyCredentialDomException(new EncodingError(), r1);
    L9:
        return new CreatePublicKeyCredentialDomException(new EncodingError(), "Unknown error");
    }

    public static final /* synthetic */ CredentialManagerCallback access$getCallback$p(CredentialProviderCreatePublicKeyCredentialController r0) {
        return r0.callback;
    }

    public static final /* synthetic */ CancellationSignal access$getCancellationSignal$p(CredentialProviderCreatePublicKeyCredentialController r0) {
        return r0.cancellationSignal;
    }

    public static final /* synthetic */ Executor access$getExecutor$p(CredentialProviderCreatePublicKeyCredentialController r0) {
        return r0.executor;
    }

    public static final /* synthetic */ boolean access$maybeReportErrorFromResultReceiver(CredentialProviderCreatePublicKeyCredentialController r0, Bundle r1, Function2 r2, Executor r3, CredentialManagerCallback r4, CancellationSignal r5) {
        return r0.maybeReportErrorFromResultReceiver(r1, r2, r3, r4, r5);
    }

    private final CreateCredentialException fromIntentRequestException(Throwable r4) {
        if ((r4 instanceof ApiException) == true) goto L5;
    L7:
        String r3 = "CREATE_UNKNOWN";
    L9:
        return CredentialProviderBaseController.Companion.createCredentialExceptionTypeToException$credentials_play_services_auth(r3, "During create public key credential, fido registration failure: " + r4.getMessage());
    L5:
        if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(((ApiException) r4).getStatusCode())) == false) goto L7;
        r3 = "CREATE_INTERRUPTED";
        goto L9
    }

    private static final void handleResponse$lambda$1$0(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onError(r1);
    }

    private static final void handleResponse$lambda$3$0(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onError(r1);
    }

    private static final void handleResponse$lambda$4$0(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialResponse r1) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onResult(r1);
    }

    private static final void handleResponse$lambda$5$0(CredentialProviderCreatePublicKeyCredentialController r2, JSONException r3) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r22 = r2.callback;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.onError(new CreatePublicKeyCredentialDomException(new EncodingError(), r3.getMessage()));
    }

    private static final void handleResponse$lambda$6$0(CredentialProviderCreatePublicKeyCredentialController r2, Throwable r3) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r22 = r2.callback;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.onError(new CreatePublicKeyCredentialDomException(new UnknownError(), r3.getMessage()));
    }

    private static final void invokePlayServices$lambda$0$0(CredentialProviderCreatePublicKeyCredentialController r1, JSONException r2) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r0 = r1.callback;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.onError(r1.JSONExceptionToPKCError(r2));
    }

    private static final void invokePlayServices$lambda$1$0(CredentialProviderCreatePublicKeyCredentialController r1, Throwable r2) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r12 = r1.callback;
        if (r12 != null) goto L5;
        r12 = null;
    L5:
        r12.onError(new CreateCredentialUnknownException(r2.getMessage()));
    }

    private static final Unit invokePlayServices$lambda$2$0(CredentialProviderCreatePublicKeyCredentialController r2) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda22(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$2$0$0(CredentialProviderCreatePublicKeyCredentialController r2) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r22 = r2.callback;
        if (r22 != null) goto L5;
        r22 = null;
    L5:
        r22.onError(new CreateCredentialUnknownException("Failed to launch the selector UI. Hint: ensure the `context` parameter is an Activity-based context."));
    }

    private static final Unit invokePlayServices$lambda$4$0(CredentialProviderCreatePublicKeyCredentialController r2, CreateCredentialException r3) {
        Executor r0 = r2.executor;
        if (r0 != null) goto L5;
        r0 = null;
    L5:
        r0.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda21(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$4$0$0(CredentialProviderCreatePublicKeyCredentialController r0, CreateCredentialException r1) {
        CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r02 = r0.callback;
        if (r02 != null) goto L5;
        r02 = null;
    L5:
        r02.onError(r1);
    }

    public PublicKeyCredentialCreationOptions convertRequestToPlayServices(CreatePublicKeyCredentialRequest r2) {
        return PublicKeyCredentialControllerUtility.Companion.convert(r2, this.context);
    }

    public CreateCredentialResponse convertResponseToCredentialManager(PublicKeyCredential r3) {
        return new CreatePublicKeyCredentialResponse(r3.toJson());
    L4:
        th = move-exception;
        throw new CreateCredentialUnknownException("The PublicKeyCredential response json had an unexpected exception when parsing: " + th.getMessage());
    }

    public final void handleResponse$credentials_play_services_auth(int r3, int r4, Intent r5) {
        CredentialProviderBaseController.Companion r0 = CredentialProviderBaseController.Companion;
        if (r3 == r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth()) goto L7;
        Log.w("CreatePublicKey", "Returned request code " + r0.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth() + " does not match what was given " + r3);
        return;
    L7:
        if (CredentialProviderController.maybeReportErrorResultCodeCreate(r4, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda0(), new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda1(this), this.cancellationSignal) == true) goto L39;
        Executor r32 = null;
        if (r5 == null) goto L12;
        byte[] r42 = r5.getByteArrayExtra("FIDO2_CREDENTIAL_EXTRA");
    L13:
        if (r42 == null) goto L15;
        PublicKeyCredential r33 = PublicKeyCredential.deserializeFromBytes(r42);
        CreateCredentialException r43 = PublicKeyCredentialControllerUtility.Companion.publicKeyCredentialResponseContainsError(r33);
        if (r43 == null) goto L36;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda3(this, r43));
        return;
    L36:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda4(this, convertResponseToCredentialManager(r33)));     // Catch: Throwable -> L29 JSONException -> L31
        return;
    L31:
        e = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda5(this, e));
        return;
    L29:
        th = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(this.cancellationSignal, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda6(this, th));
        return;
    L15:
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(this.cancellationSignal) == true) goto L40;
        Executor r44 = this.executor;
        if (r44 == null) goto L21;
        r32 = r44;
    L21:
        r32.execute(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda2(this));
        return;
    L40:
        return;
    L12:
        r42 = null;
        goto L13
    }

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(CreatePublicKeyCredentialRequest r1, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r2, Executor r3, CancellationSignal r4) {
        this.cancellationSignal = r4;
        this.callback = r2;
        this.executor = r3;
        PublicKeyCredentialCreationOptions r12 = convertRequestToPlayServices(r1);     // Catch: Throwable -> L9 JSONException -> L12
    L5:
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r4) == false) goto L7;
        return;
    L7:
        Fido.getFido2ApiClient(this.context).getRegisterPendingIntent(r12).addOnSuccessListener(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda10(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda9(r4, this))).addOnFailureListener(new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda11(this, r4));
        return;
    L12:
        e = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda7(this, e));
        return;
    L9:
        th = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r4, new CredentialProviderCreatePublicKeyCredentialController$$ExternalSyntheticLambda8(this, th));
    }

    @Override // androidx.credentials.playservices.controllers.CredentialProviderController
    public /* bridge */ /* synthetic */ void invokePlayServices(CreatePublicKeyCredentialRequest r1, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r2, Executor r3, CancellationSignal r4) {
        invokePlayServices2(r1, r2, r3, r4);
    }
}
