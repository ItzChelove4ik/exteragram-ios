package androidx.credentials.playservices;

import android.content.Context;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.credentials.ClearCredentialStateRequest;
import androidx.credentials.CreateCredentialRequest;
import androidx.credentials.CreateCredentialResponse;
import androidx.credentials.CreatePublicKeyCredentialRequest;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CredentialOption;
import androidx.credentials.CredentialProvider;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.PrepareGetCredentialResponse$PendingGetCredentialHandle;
import androidx.credentials.SignalCredentialStateRequest;
import androidx.credentials.SignalCredentialStateResponse;
import androidx.credentials.exceptions.ClearCredentialException;
import androidx.credentials.exceptions.ClearCredentialProviderConfigurationException;
import androidx.credentials.exceptions.ClearCredentialUnknownException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialProviderConfigurationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialProviderConfigurationException;
import androidx.credentials.exceptions.publickeycredential.SignalCredentialStateException;
import androidx.credentials.exceptions.publickeycredential.SignalCredentialStateProviderConfigurationException;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.blockstore.getrestorecredential.CredentialProviderGetRestoreCredentialController;
import androidx.credentials.playservices.controllers.identityauth.beginsignin.CredentialProviderBeginSignInController;
import androidx.credentials.playservices.controllers.identityauth.createpublickeycredential.CredentialProviderCreatePublicKeyCredentialController;
import androidx.credentials.playservices.controllers.identityauth.getsigninintent.CredentialProviderGetSignInIntentController;
import androidx.credentials.playservices.controllers.identitycredentials.createpublickeycredential.CreatePublicKeyCredentialController;
import androidx.credentials.playservices.controllers.identitycredentials.getcredential.GetCredentialController;
import androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential.CredentialProviderGetDigitalCredentialController;
import androidx.credentials.playservices.controllers.identitycredentials.signalcredentialstate.SignalCredentialStateController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.api.identity.Identity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.identitycredentials.ClearCredentialStateResponse;
import com.sun.jna.Callback;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u0000 ,2\u00020\u0001:\u0001,B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J>\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u00190\u0017H\u0017J>\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u001b2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020\u001c\u0012\u0004\u0012\u00020\u001d0\u0017H\u0017J\b\u0010\u001e\u001a\u00020\u001fH\u0016J\u000e\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020!J\u0018\u0010\"\u001a\u00020!2\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010 \u001a\u00020!H\u0002J8\u0010#\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020$2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\u0004\u0012\u00020&0\u0017H\u0016J,\u0010'\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020(2\u0006\u0010\u0014\u001a\u00020\u00152\u0012\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00020)\u0012\u0004\u0012\u00020*0\u0017H\u0016J8\u0010+\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020$2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0014\u0010\u0016\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010%\u0012\u0004\u0012\u00020&0\u0017H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\u0006\u001a\u00020\u00078\u0006@\u0006X\u0087\u000e¢\u0006\u0014\n\u0000\u0012\u0004\b\b\u0010\t\u001a\u0004\b\n\u0010\u000b\"\u0004\b\f\u0010\r¨\u0006-"}, d2 = {"Landroidx/credentials/playservices/CredentialProviderPlayServicesImpl;", "Landroidx/credentials/CredentialProvider;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "googleApiAvailability", "Lcom/google/android/gms/common/GoogleApiAvailability;", "getGoogleApiAvailability$annotations", "()V", "getGoogleApiAvailability", "()Lcom/google/android/gms/common/GoogleApiAvailability;", "setGoogleApiAvailability", "(Lcom/google/android/gms/common/GoogleApiAvailability;)V", "onGetCredential", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, "Landroidx/credentials/GetCredentialRequest;", "cancellationSignal", "Landroid/os/CancellationSignal;", "executor", "Ljava/util/concurrent/Executor;", Callback.METHOD_NAME, "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "onCreateCredential", "Landroidx/credentials/CreateCredentialRequest;", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "isAvailableOnDevice", _UrlKt.FRAGMENT_ENCODE_SET, "minApkVersion", _UrlKt.FRAGMENT_ENCODE_SET, "isGooglePlayServicesAvailable", "onClearCredential", "Landroidx/credentials/ClearCredentialStateRequest;", "Ljava/lang/Void;", "Landroidx/credentials/exceptions/ClearCredentialException;", "onSignalCredentialState", "Landroidx/credentials/SignalCredentialStateRequest;", "Landroidx/credentials/SignalCredentialStateResponse;", "Landroidx/credentials/exceptions/publickeycredential/SignalCredentialStateException;", "runFallbackClearCredFlow", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderPlayServicesImpl implements CredentialProvider {
    public static final Companion Companion = null;
    public static final int MIN_GMS_APK_VERSION = 230815045;
    public static final int MIN_GMS_APK_VERSION_DIGITAL_CRED = 243100000;
    public static final int MIN_GMS_APK_VERSION_RESTORE_CRED = 242200000;
    public static final int MIN_GMS_APK_VERSION_SIGNAL_API = 254625000;
    public static final int PRE_U_MIN_GMS_APK_VERSION = 252400000;
    private static final String TAG = "PlayServicesImpl";
    private final Context context;
    private GoogleApiAvailability googleApiAvailability;

    @Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J%\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\r0\u0011H\u0000¢\u0006\u0002\b\u0012J\u0017\u0010\u0013\u001a\u00020\u00142\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0000¢\u0006\u0002\b\u0015J\u0015\u0010\u0016\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u0019J\u0015\u0010\u001a\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u001bJ\u0015\u0010\u001c\u001a\u00020\u00142\u0006\u0010\u0017\u001a\u00020\u0018H\u0000¢\u0006\u0002\b\u001dR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\b\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000R\u0010\u0010\u000b\u001a\u00020\u00078\u0006X\u0087T¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Landroidx/credentials/playservices/CredentialProviderPlayServicesImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "MIN_GMS_APK_VERSION", _UrlKt.FRAGMENT_ENCODE_SET, "PRE_U_MIN_GMS_APK_VERSION", "MIN_GMS_APK_VERSION_RESTORE_CRED", "MIN_GMS_APK_VERSION_DIGITAL_CRED", "MIN_GMS_APK_VERSION_SIGNAL_API", "cancellationReviewerWithCallback", _UrlKt.FRAGMENT_ENCODE_SET, "cancellationSignal", "Landroid/os/CancellationSignal;", Callback.METHOD_NAME, "Lkotlin/Function0;", "cancellationReviewerWithCallback$credentials_play_services_auth", "cancellationReviewer", _UrlKt.FRAGMENT_ENCODE_SET, "cancellationReviewer$credentials_play_services_auth", "isGetSignInIntentRequest", PluginsConstants.REQUEST, "Landroidx/credentials/GetCredentialRequest;", "isGetSignInIntentRequest$credentials_play_services_auth", "isGetRestoreCredentialRequest", "isGetRestoreCredentialRequest$credentials_play_services_auth", "isDigitalCredentialRequest", "isDigitalCredentialRequest$credentials_play_services_auth", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        public final boolean cancellationReviewer$credentials_play_services_auth(CancellationSignal r1) {
            if (r1 != null) goto L5;
            Log.i(CredentialProviderPlayServicesImpl.TAG, "No cancellationSignal found");
            return false;
        L5:
            if (r1.isCanceled() == false) goto L11;
            Log.i(CredentialProviderPlayServicesImpl.TAG, "the flow has been canceled");
            return true;
        L11:
            return false;
        }

        public final void cancellationReviewerWithCallback$credentials_play_services_auth(CancellationSignal r1, Function0<Unit> r2) {
            if (cancellationReviewer$credentials_play_services_auth(r1) == true) goto L6;
            r2.invoke();
            return;
        }

        public final boolean isDigitalCredentialRequest$credentials_play_services_auth(GetCredentialRequest r1) {
            Iterator<CredentialOption> r0 = r1.getCredentialOptions().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            CredentialOption r12 = r0.next();
            goto L4
        L6:
            return false;
        }

        public final boolean isGetRestoreCredentialRequest$credentials_play_services_auth(GetCredentialRequest r1) {
            Iterator<CredentialOption> r0 = r1.getCredentialOptions().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            CredentialOption r12 = r0.next();
            goto L4
        L6:
            return false;
        }

        public final boolean isGetSignInIntentRequest$credentials_play_services_auth(GetCredentialRequest r1) {
            Iterator<CredentialOption> r0 = r1.getCredentialOptions().iterator();
        L4:
            if (r0.hasNext() == false) goto L6;
            CredentialOption r12 = r0.next();
            goto L4
        L6:
            return false;
        }
    }

    /* renamed from: $r8$lambda$1UTL-i4hwhJk_BYM4Zcx0ZRJ19w, reason: not valid java name */
    public static void m674$r8$lambda$1UTLi4hwhJk_BYM4Zcx0ZRJ19w(CredentialProviderPlayServicesImpl r1, ClearCredentialStateRequest r2, CancellationSignal r3, Executor r4, CredentialManagerCallback r5, Exception r6) {
        Log.e(TAG, "GMS Clear credential flow failed, calling fallback");
        r1.runFallbackClearCredFlow(r2, r3, r4, r5);
    }

    /* renamed from: $r8$lambda$27hAKj8hhFiHQnNA1lTKsiG-Oxw, reason: not valid java name */
    public static /* synthetic */ void m675$r8$lambda$27hAKj8hhFiHQnNA1lTKsiGOxw(CredentialManagerCallback r0) {
        runFallbackClearCredFlow$lambda$0$0$0(r0);
    }

    public static /* synthetic */ void $r8$lambda$AS0yfz0pVQO1Ewzm5zGeHqWtm6I(CredentialManagerCallback r0, Exception r1) {
        runFallbackClearCredFlow$lambda$2$0$0$0(r0, r1);
    }

    public static Unit $r8$lambda$CkXA6uyZF5r3Uy4uE_kF2MrG3TY(CancellationSignal r1, Executor r2, CredentialManagerCallback r3, Void r4) {
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth(r1, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda15(r2, r3));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$EfEsA0oxTYc7AqOZZSNy2cLCz-o, reason: not valid java name */
    public static /* synthetic */ void m676$r8$lambda$EfEsA0oxTYc7AqOZZSNy2cLCzo(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        onClearCredential$lambda$3$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$I96JcpYfaG8OJdM-2J7UmFIJHiE, reason: not valid java name */
    public static /* synthetic */ Unit m677$r8$lambda$I96JcpYfaG8OJdM2J7UmFIJHiE(Exception r0, Executor r1, CredentialManagerCallback r2) {
        return runFallbackClearCredFlow$lambda$2$0$0(r0, r1, r2);
    }

    public static void $r8$lambda$KPnyPsbzUo0kEQwputkdgA68I1Y(CredentialProviderPlayServicesImpl r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4, Exception r5) {
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth(r2, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda8(r5, r3, r4));
    }

    public static Unit $r8$lambda$NFvwqGaQl8vK3ul8X_0RI454oIM(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda2(r2));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$NQbSk4pvJcM237tlhW3qXRMIF0Y(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static void $r8$lambda$Qhj5bSmYMsKY2IK3G30xvMhtcXQ(CredentialManagerCallback r2) {
        r2.onError(new SignalCredentialStateProviderConfigurationException("this device requires a Google Play Services update for the given feature to be supported"));
    }

    public static /* synthetic */ Unit $r8$lambda$VsxIaY9CMEklHrOXk5cdkiRsqcE(Executor r0, CredentialManagerCallback r1) {
        return runFallbackClearCredFlow$lambda$0$0(r0, r1);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.credentials.exceptions.ClearCredentialUnknownException] */
    /* JADX WARN: Type inference failed for: r6v4, types: [T, androidx.credentials.exceptions.ClearCredentialUnknownException] */
    public static void $r8$lambda$Z8tlc7Lp2cNhbHTy0dCxp0FF7rQ(CancellationSignal r3, Executor r4, CredentialManagerCallback r5, Exception r6) {
        Log.w(TAG, "Clearing restore credential failed", r6);
        Ref.ObjectRef r0 = new Ref.ObjectRef();
        r0.element = new ClearCredentialUnknownException("Clear restore credential failed for unknown reason.");
        if ((r6 instanceof ApiException) == true) goto L5;
    L7:
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth(r3, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda4(r4, r5, r0));
        return;
    L5:
        if (((ApiException) r6).getStatusCode() != 40201) goto L7;
        r0.element = new ClearCredentialUnknownException("The restore credential internal service had a failure.");
        goto L7
    }

    public static /* synthetic */ void $r8$lambda$ZmeORH0b9a1FJ17DY04w0WU6DDE(CredentialManagerCallback r0) {
        onClearCredential$lambda$1$0$0(r0);
    }

    public static /* synthetic */ Unit $r8$lambda$_y5WH3MEXM44F4UiflADvlnUoCA(Executor r0, CredentialManagerCallback r1, Ref.ObjectRef r2) {
        return onClearCredential$lambda$3$0(r0, r1, r2);
    }

    public static /* synthetic */ Unit $r8$lambda$deaEx7cFJz8WB4dww9fPUSax2YE(Executor r0, CredentialManagerCallback r1) {
        return onClearCredential$lambda$4$0(r0, r1);
    }

    /* renamed from: $r8$lambda$e26-TJ45BetGQtJZIcAQ5s9rm3c, reason: not valid java name */
    public static /* synthetic */ void m678$r8$lambda$e26TJ45BetGQtJZIcAQ5s9rm3c(CredentialManagerCallback r0) {
        onGetCredential$lambda$1$0(r0);
    }

    public static /* synthetic */ void $r8$lambda$eKxW_gs1lUPICybr1syVFaQsVp0(CredentialManagerCallback r0) {
        onClearCredential$lambda$4$0$0(r0);
    }

    public static /* synthetic */ void $r8$lambda$hCjUIdJpkFZZ_R4jPEVDfW9xTXk(CredentialManagerCallback r0) {
        onClearCredential$lambda$0$0(r0);
    }

    public static /* synthetic */ Unit $r8$lambda$mCecmz7sSDa7vQZdPoDQHAX0H3U(Executor r0, CredentialManagerCallback r1) {
        return onClearCredential$lambda$1$0(r0, r1);
    }

    public static Unit $r8$lambda$nLqf08e3fIgSrrhjRatjutfw5fE(CancellationSignal r1, Executor r2, CredentialManagerCallback r3, ClearCredentialStateResponse r4) {
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth(r1, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda13(r2, r3));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$o_KQtBV8bzJ_YUpu54q6ta2QxDQ(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda5(r2));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$pdpGIYvPEfq-hpYnJSMZXGd3BSQ, reason: not valid java name */
    public static Unit m679$r8$lambda$pdpGIYvPEfqhpYnJSMZXGd3BSQ(CancellationSignal r1, Executor r2, CredentialManagerCallback r3, Boolean r4) {
        Companion.cancellationReviewerWithCallback$credentials_play_services_auth(r1, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda16(r2, r3));
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$v2_cK85gsZZQw32xnN1qU13GbKQ(CredentialManagerCallback r0) {
        onGetCredential$lambda$0$0(r0);
    }

    public static /* synthetic */ void $r8$lambda$v5kLi_w59Ybz8Cu6DmJc3hm3YW4(CredentialManagerCallback r0) {
        onCreateCredential$lambda$0$0(r0);
    }

    public static void $r8$lambda$wBiSTxUbOhG0ep8ucfM6ivfiSz8(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static void $r8$lambda$wNyRQU4FRR3qZkFIXqBcIQRWXcc(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static Unit $r8$lambda$xgrl6ZZEG6yNGAU_HQNbfaxXN8I(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda9(r2));
        return Unit.INSTANCE;
    }

    /* renamed from: $r8$lambda$xrkLPQ171M9o-LLsi3Kz8A_1kSE, reason: not valid java name */
    public static Unit m680$r8$lambda$xrkLPQ171M9oLLsi3Kz8A_1kSE(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda10(r2));
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderPlayServicesImpl(Context r1) {
        this.context = r1;
        this.googleApiAvailability = GoogleApiAvailability.getInstance();
    }

    public static /* synthetic */ void getGoogleApiAvailability$annotations() {
    }

    private final int isGooglePlayServicesAvailable(Context r1, int r2) {
        return this.googleApiAvailability.isGooglePlayServicesAvailable(r1, r2);
    }

    private static final void onClearCredential$lambda$0$0(CredentialManagerCallback r2) {
        r2.onError(new ClearCredentialProviderConfigurationException("clearCredentialStateAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
    }

    private static final Unit onClearCredential$lambda$1$0(Executor r2, CredentialManagerCallback r3) {
        Log.i(TAG, "Cleared restore credential successfully!");
        r2.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda6(r3));
        return Unit.INSTANCE;
    }

    private static final void onClearCredential$lambda$1$0$0(CredentialManagerCallback r1) {
        r1.onResult(null);
    }

    private static final Unit onClearCredential$lambda$3$0(Executor r1, CredentialManagerCallback r2, Ref.ObjectRef r3) {
        r1.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda3(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void onClearCredential$lambda$3$0$0(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        r0.onError(r1.element);
    }

    private static final Unit onClearCredential$lambda$4$0(Executor r2, CredentialManagerCallback r3) {
        Log.i(TAG, "During clear credential, signed out successfully!");
        r2.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda12(r3));
        return Unit.INSTANCE;
    }

    private static final void onClearCredential$lambda$4$0$0(CredentialManagerCallback r1) {
        r1.onResult(null);
    }

    private static final void onCreateCredential$lambda$0$0(CredentialManagerCallback r2) {
        r2.onError(new CreateCredentialProviderConfigurationException("createCredentialAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
    }

    private static final void onGetCredential$lambda$0$0(CredentialManagerCallback r2) {
        r2.onError(new GetCredentialProviderConfigurationException("this device requires a Google Play Services update for the given feature to be supported"));
    }

    private static final void onGetCredential$lambda$1$0(CredentialManagerCallback r2) {
        r2.onError(new GetCredentialProviderConfigurationException("getCredentialAsync no provider dependencies found - please ensure the desired provider dependencies are added"));
    }

    private final void runFallbackClearCredFlow(ClearCredentialStateRequest r3, CancellationSignal r4, Executor r5, CredentialManagerCallback<Void, ClearCredentialException> r6) {
        Identity.getSignInClient(this.context).signOut().addOnSuccessListener(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda18(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda17(r4, r5, r6))).addOnFailureListener(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda19(this, r4, r5, r6));
    }

    private static final Unit runFallbackClearCredFlow$lambda$0$0(Executor r2, CredentialManagerCallback r3) {
        Log.i(TAG, "During clear credential, signed out successfully!");
        r2.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda7(r3));
        return Unit.INSTANCE;
    }

    private static final void runFallbackClearCredFlow$lambda$0$0$0(CredentialManagerCallback r1) {
        r1.onResult(null);
    }

    private static final Unit runFallbackClearCredFlow$lambda$2$0$0(Exception r2, Executor r3, CredentialManagerCallback r4) {
        Log.w(TAG, "During clear credential sign out failed with " + r2);
        r3.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda11(r4, r2));
        return Unit.INSTANCE;
    }

    private static final void runFallbackClearCredFlow$lambda$2$0$0$0(CredentialManagerCallback r1, Exception r2) {
        r1.onError(new ClearCredentialUnknownException(r2.getMessage()));
    }

    public final GoogleApiAvailability getGoogleApiAvailability() {
        return this.googleApiAvailability;
    }

    @Override // androidx.credentials.CredentialProvider
    public boolean isAvailableOnDevice() {
        return isAvailableOnDevice(MIN_GMS_APK_VERSION);
    }

    public final boolean isAvailableOnDevice(int r3) {
        int r2 = isGooglePlayServicesAvailable(this.context, r3);
        if (r2 != 0) goto L5;
        boolean r32 = true;
    L6:
        if (r32 == true) goto L8;
        Log.w(TAG, "Connection with Google Play Services was not successful. Connection result is: " + new ConnectionResult(r2));
    L8:
        return r32;
    L5:
        r32 = false;
        goto L6
    }

    public void onClearCredential(ClearCredentialStateRequest r1, CancellationSignal r2, Executor r3, CredentialManagerCallback<Void, ClearCredentialException> r4) {
        if (Companion.cancellationReviewer$credentials_play_services_auth(r2) == false) goto L6;
        return;
    L6:
        throw null;
    }

    @Override // androidx.credentials.CredentialProvider
    public void onCreateCredential(Context r2, CreateCredentialRequest r3, CancellationSignal r4, Executor r5, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r6) {
        if (Companion.cancellationReviewer$credentials_play_services_auth(r4) == false) goto L6;
        return;
    L6:
        if ((r3 instanceof CreatePublicKeyCredentialRequest) == true) goto L8;
        ByteString$$ExternalSyntheticBUOutline0.m("Create Credential request is unsupported, not password or publickeycredential");
        return;
    L8:
        if (isAvailableOnDevice(PRE_U_MIN_GMS_APK_VERSION) == true) goto L14;
        CreatePublicKeyCredentialRequest r1 = (CreatePublicKeyCredentialRequest) r3;
        if (r1.isConditional() == true) goto L14;
        CredentialProviderCreatePublicKeyCredentialController.Companion.getInstance(r2).invokePlayServices2(r1, r6, r5, r4);
        return;
    L14:
        CreatePublicKeyCredentialController.Companion.getInstance(r2).invokePlayServices2((CreatePublicKeyCredentialRequest) r3, r6, r5, r4);
    }

    @Override // androidx.credentials.CredentialProvider
    public void onGetCredential(Context r3, GetCredentialRequest r4, CancellationSignal r5, Executor r6, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r7) {
        Companion r0 = Companion;
        if (r0.cancellationReviewer$credentials_play_services_auth(r5) == false) goto L6;
        return;
    L6:
        if (r0.isDigitalCredentialRequest$credentials_play_services_auth(r4) == false) goto L14;
        if (isAvailableOnDevice(MIN_GMS_APK_VERSION_DIGITAL_CRED) == true) goto L11;
        r0.cancellationReviewerWithCallback$credentials_play_services_auth(r5, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda0(r6, r7));
        return;
    L11:
        new CredentialProviderGetDigitalCredentialController(r3).invokePlayServices2(r4, r7, r6, r5);
        return;
    L14:
        if (r0.isGetRestoreCredentialRequest$credentials_play_services_auth(r4) == false) goto L22;
        if (isAvailableOnDevice(MIN_GMS_APK_VERSION_RESTORE_CRED) == true) goto L19;
        r0.cancellationReviewerWithCallback$credentials_play_services_auth(r5, new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda1(r6, r7));
        return;
    L19:
        new CredentialProviderGetRestoreCredentialController(r3).invokePlayServices2(r4, r7, r6, r5);
        return;
    L22:
        if (isAvailableOnDevice(PRE_U_MIN_GMS_APK_VERSION) == false) goto L26;
        new GetCredentialController(r3).invokePlayServices2(r4, r7, r6, r5);
        return;
    L26:
        if (r0.isGetSignInIntentRequest$credentials_play_services_auth(r4) == false) goto L29;
        new CredentialProviderGetSignInIntentController(r3).invokePlayServices2(r4, r7, r6, r5);
        return;
    L29:
        new CredentialProviderBeginSignInController(r3).invokePlayServices2(r4, r7, r6, r5);
    }

    @Override // androidx.credentials.CredentialProvider
    public /* bridge */ /* synthetic */ void onGetCredential(Context r1, PrepareGetCredentialResponse$PendingGetCredentialHandle r2, CancellationSignal r3, Executor r4, CredentialManagerCallback r5) {
        super.onGetCredential(r1, r2, r3, r4, r5);
    }

    @Override // androidx.credentials.CredentialProvider
    public /* bridge */ /* synthetic */ void onPrepareCredential(GetCredentialRequest r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4) {
        super.onPrepareCredential(r1, r2, r3, r4);
    }

    public void onSignalCredentialState(SignalCredentialStateRequest r9, Executor r10, CredentialManagerCallback<SignalCredentialStateResponse, SignalCredentialStateException> r11) {
        if (isAvailableOnDevice(MIN_GMS_APK_VERSION_SIGNAL_API) == true) goto L6;
        r10.execute(new CredentialProviderPlayServicesImpl$$ExternalSyntheticLambda14(r11));
        return;
    L6:
        CredentialProviderController.invokePlayServices$default(SignalCredentialStateController.Companion.getInstance(this.context), r9, r11, r10, null, 8, null);
    }

    public final void setGoogleApiAvailability(GoogleApiAvailability r1) {
        this.googleApiAvailability = r1;
    }
}
