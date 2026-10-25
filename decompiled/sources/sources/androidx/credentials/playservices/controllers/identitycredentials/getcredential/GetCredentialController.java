package androidx.credentials.playservices.controllers.identitycredentials.getcredential;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import android.util.Log;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.identityauth.HiddenActivity;
import androidx.credentials.playservices.controllers.identityauth.beginsignin.CredentialProviderBeginSignInController;
import androidx.credentials.playservices.controllers.identityauth.getsigninintent.CredentialProviderGetSignInIntentController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.identitycredentials.CredentialOption;
import com.google.android.gms.identitycredentials.IdentityCredentialManager;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import com.sun.jna.Callback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000S\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0016\n\u0002\b\u0005*\u0001.\b\u0001\u0018\u0000 12 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u00011B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\u000e\u001a\u00020\r2\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\u000e\u0010\u000fJ=\u0010\u0018\u001a\u00020\u00172\u0006\u0010\u0010\u001a\u00020\u00022\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00112\u0006\u0010\u0014\u001a\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0016¢\u0006\u0004\b\u0018\u0010\u0019J\u0017\u0010\u001a\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u001a\u0010\u001bR\u0017\u0010\b\u001a\u00020\u00078\u0006¢\u0006\f\n\u0004\b\b\u0010\u001c\u001a\u0004\b\u001d\u0010\u001eR4\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00118\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0012\u0010\u001f\u0012\u0004\b$\u0010%\u001a\u0004\b \u0010!\"\u0004\b\"\u0010#R(\u0010\u0014\u001a\u00020\u00138\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0014\u0010&\u0012\u0004\b+\u0010%\u001a\u0004\b'\u0010(\"\u0004\b)\u0010*R\u001e\u0010\u0016\u001a\u0004\u0018\u00010\u00158\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\u0016\u0010,\u0012\u0004\b-\u0010%R\u0014\u0010/\u001a\u00020.8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b/\u00100¨\u00062"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/GetCredentialRequest;", "Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroidx/credentials/CredentialOption;", "option", "Lcom/google/android/gms/identitycredentials/CredentialOption;", "convertCredentialOptionToPlayServices", "(Landroidx/credentials/CredentialOption;)Lcom/google/android/gms/identitycredentials/CredentialOption;", PluginsConstants.REQUEST, "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Landroidx/credentials/GetCredentialRequest;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", "convertRequestToPlayServices", "(Landroidx/credentials/GetCredentialRequest;)Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Landroidx/credentials/CredentialManagerCallback;", "getCallback", "()Landroidx/credentials/CredentialManagerCallback;", "setCallback", "(Landroidx/credentials/CredentialManagerCallback;)V", "getCallback$annotations", "()V", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "setExecutor", "(Ljava/util/concurrent/Executor;)V", "getExecutor$annotations", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "androidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController$resultReceiver$1", "resultReceiver", "Landroidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController$resultReceiver$1;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nGetCredentialController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 GetCredentialController.kt\nandroidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,192:1\n1563#2:193\n1634#2,3:194\n*S KotlinDebug\n*F\n+ 1 GetCredentialController.kt\nandroidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController\n*L\n161#1:193\n161#1:194,3\n*E\n"})
/* loaded from: classes3.dex */
public final class GetCredentialController extends CredentialProviderController<GetCredentialRequest, com.google.android.gms.identitycredentials.GetCredentialRequest, Object, GetCredentialResponse, GetCredentialException> {
    public static final Companion Companion = null;
    public CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback;
    private CancellationSignal cancellationSignal;
    private final Context context;
    public Executor executor;
    private final GetCredentialController$resultReceiver$1 resultReceiver;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/getcredential/GetCredentialController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    public static Unit $r8$lambda$GyDDE5ful1wYW65o8QGlZvX14Gk(CancellationSignal r3, GetCredentialController r4, Executor r5, CredentialManagerCallback r6, PendingGetCredentialHandle r7) {
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r3) == true) goto L5;
        Intent r0 = new Intent(r4.context, HiddenActivity.class);
        r4.generateHiddenActivityIntent(r4.resultReceiver, r0, "BEGIN_SIGN_IN");
        r0.putExtra("EXTRA_FLOW_PENDING_INTENT", r7.getPendingIntent());
        r4.context.startActivity(r0);     // Catch: Exception -> L9
    L11:
        return Unit.INSTANCE;
    L9:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r3, new GetCredentialController$$ExternalSyntheticLambda3(r5, r6));
        goto L11
    L5:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ void $r8$lambda$Pk2aOlFyA3EIck7KXp7BpFKY4Os(CredentialManagerCallback r0) {
        invokePlayServices$lambda$0$0$0(r0);
    }

    public static void $r8$lambda$VpJuJ2CUiiSK4UYrH3k6NDY1DmI(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static /* synthetic */ Unit $r8$lambda$egT11jQPPywk25a7Okkh__7Vf4U(Executor r0, CredentialManagerCallback r1) {
        return invokePlayServices$lambda$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$uOxAj9j1JzqXonniXxyEcP-Y2CA, reason: not valid java name */
    public static void m719$r8$lambda$uOxAj9j1JzqXonniXxyEcPY2CA(GetCredentialRequest r1, GetCredentialController r2, CredentialManagerCallback r3, Executor r4, CancellationSignal r5, Exception r6) {
        if (CredentialProviderPlayServicesImpl.Companion.isGetSignInIntentRequest$credentials_play_services_auth(r1) == false) goto L6;
        Log.w("GetCredentialController", "Pre-u credman get flow failed for get sign in intent; retrying with gis flow");
        new CredentialProviderGetSignInIntentController(r2.context).invokePlayServices2(r1, r3, r4, r5);
        return;
    L6:
        Log.w("GetCredentialController", "Pre-u credman get flow failed; retrying with gis flow");
        new CredentialProviderBeginSignInController(r2.context).invokePlayServices2(r1, r3, r4, r5);
    }

    static {
        Companion = new Companion(null);
    }

    public GetCredentialController(Context r2) {
        super(r2);
        this.context = r2;
        this.resultReceiver = new GetCredentialController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));
    }

    public static final /* synthetic */ CancellationSignal access$getCancellationSignal$p(GetCredentialController r0) {
        return r0.cancellationSignal;
    }

    public static final /* synthetic */ boolean access$maybeReportErrorFromResultReceiver(GetCredentialController r0, Bundle r1, Function2 r2, Executor r3, CredentialManagerCallback r4, CancellationSignal r5) {
        return r0.maybeReportErrorFromResultReceiver(r1, r2, r3, r4, r5);
    }

    private final CredentialOption convertCredentialOptionToPlayServices(androidx.credentials.CredentialOption r8) {
        return new CredentialOption(r8.getType(), r8.getRequestData(), r8.getCandidateQueryData(), _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    private static final Unit invokePlayServices$lambda$0$0(Executor r1, CredentialManagerCallback r2) {
        r1.execute(new GetCredentialController$$ExternalSyntheticLambda4(r2));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$0$0(CredentialManagerCallback r2) {
        r2.onError(new GetCredentialUnknownException("Failed to launch the selector UI. Hint: ensure the `context` parameter is an Activity-based context."));
    }

    public com.google.android.gms.identitycredentials.GetCredentialRequest convertRequestToPlayServices(GetCredentialRequest r5) {
        Bundle r0 = GetCredentialRequest.Companion.getRequestMetadataBundle(r5);
        List<androidx.credentials.CredentialOption> r1 = r5.getCredentialOptions();
        ArrayList r2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(r1, 10));
        Iterator<T> r12 = r1.iterator();
    L4:
        if (r12.hasNext() == false) goto L7;
        r2.add(convertCredentialOptionToPlayServices((androidx.credentials.CredentialOption) r12.next()));
        goto L4
    L7:
        return new com.google.android.gms.identitycredentials.GetCredentialRequest(r2, r0, r5.getOrigin(), new ResultReceiver(null));
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

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(GetCredentialRequest r8, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r9, Executor r10, CancellationSignal r11) {
        this.cancellationSignal = r11;
        setCallback(r9);
        setExecutor(r10);
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r11) == false) goto L5;
        return;
    L5:
        com.google.android.gms.identitycredentials.GetCredentialRequest r0 = convertRequestToPlayServices(r8);
        IdentityCredentialManager.Companion.getClient(this.context).getCredential(r0).addOnSuccessListener(new GetCredentialController$$ExternalSyntheticLambda1(new GetCredentialController$$ExternalSyntheticLambda0(r11, this, r10, r9))).addOnFailureListener(new GetCredentialController$$ExternalSyntheticLambda2(r8, this, r9, r10, r11));
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
