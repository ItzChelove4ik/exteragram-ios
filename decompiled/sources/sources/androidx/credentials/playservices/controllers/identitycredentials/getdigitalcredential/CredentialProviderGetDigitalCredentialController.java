package androidx.credentials.playservices.controllers.identitycredentials.getdigitalcredential;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.CancellationSignal;
import android.os.Handler;
import android.os.Looper;
import android.os.ResultReceiver;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialInterruptedException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.playservices.controllers.identitycredentials.IdentityCredentialApiHiddenActivity;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.identitycredentials.IdentityCredentialManager;
import com.google.android.gms.identitycredentials.PendingGetCredentialHandle;
import com.sun.jna.Callback;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000M\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0014\n\u0002\b\u0006*\u0001+\b\u0001\u0018\u0000 /2 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001/B\u000f\u0012\u0006\u0010\b\u001a\u00020\u0007¢\u0006\u0004\b\t\u0010\nJ\u0017\u0010\r\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000bH\u0002¢\u0006\u0004\b\r\u0010\u000eJ=\u0010\u0017\u001a\u00020\u00162\u0006\u0010\u000f\u001a\u00020\u00022\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00102\u0006\u0010\u0013\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0014H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\u0017\u0010\u0019\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u0002H\u0016¢\u0006\u0004\b\u0019\u0010\u001aR\u0014\u0010\b\u001a\u00020\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\b\u0010\u001bR4\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u00108\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0011\u0010\u001c\u0012\u0004\b!\u0010\"\u001a\u0004\b\u001d\u0010\u001e\"\u0004\b\u001f\u0010 R(\u0010\u0013\u001a\u00020\u00128\u0006@\u0006X\u0087.¢\u0006\u0018\n\u0004\b\u0013\u0010#\u0012\u0004\b(\u0010\"\u001a\u0004\b$\u0010%\"\u0004\b&\u0010'R\u001e\u0010\u0015\u001a\u0004\u0018\u00010\u00148\u0002@\u0002X\u0083\u000e¢\u0006\f\n\u0004\b\u0015\u0010)\u0012\u0004\b*\u0010\"R\u001a\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\f\n\u0004\b,\u0010-\u0012\u0004\b.\u0010\"¨\u00060"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/getdigitalcredential/CredentialProviderGetDigitalCredentialController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/GetCredentialRequest;", "Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", _UrlKt.FRAGMENT_ENCODE_SET, "e", "fromGmsException", "(Ljava/lang/Throwable;)Landroidx/credentials/exceptions/GetCredentialException;", PluginsConstants.REQUEST, "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Ljava/util/concurrent/Executor;", "executor", "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Landroidx/credentials/GetCredentialRequest;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", "convertRequestToPlayServices", "(Landroidx/credentials/GetCredentialRequest;)Lcom/google/android/gms/identitycredentials/GetCredentialRequest;", "Landroid/content/Context;", "Landroidx/credentials/CredentialManagerCallback;", "getCallback", "()Landroidx/credentials/CredentialManagerCallback;", "setCallback", "(Landroidx/credentials/CredentialManagerCallback;)V", "getCallback$annotations", "()V", "Ljava/util/concurrent/Executor;", "getExecutor", "()Ljava/util/concurrent/Executor;", "setExecutor", "(Ljava/util/concurrent/Executor;)V", "getExecutor$annotations", "Landroid/os/CancellationSignal;", "getCancellationSignal$annotations", "androidx/credentials/playservices/controllers/identitycredentials/getdigitalcredential/CredentialProviderGetDigitalCredentialController$resultReceiver$1", "resultReceiver", "Landroidx/credentials/playservices/controllers/identitycredentials/getdigitalcredential/CredentialProviderGetDigitalCredentialController$resultReceiver$1;", "getResultReceiver$annotations", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderGetDigitalCredentialController extends CredentialProviderController<GetCredentialRequest, com.google.android.gms.identitycredentials.GetCredentialRequest, Object, GetCredentialResponse, GetCredentialException> {
    private static final Companion Companion = null;
    public CredentialManagerCallback<GetCredentialResponse, GetCredentialException> callback;
    private CancellationSignal cancellationSignal;
    private final Context context;
    public Executor executor;
    private final CredentialProviderGetDigitalCredentialController$resultReceiver$1 resultReceiver;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0006"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/getdigitalcredential/CredentialProviderGetDigitalCredentialController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$42KrmWHVmTbDLoSyLGZlQ0lLk-g, reason: not valid java name */
    public static Unit m720$r8$lambda$42KrmWHVmTbDLoSyLGZlQ0lLkg(CancellationSignal r2, CredentialProviderGetDigitalCredentialController r3, PendingGetCredentialHandle r4) {
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r2) == true) goto L5;
        Intent r22 = new Intent(r3.context, IdentityCredentialApiHiddenActivity.class);
        r22.setFlags(65536);
        r22.putExtra("RESULT_RECEIVER", r3.toIpcFriendlyResultReceiver(r3.resultReceiver));
        r22.putExtra("EXTRA_FLOW_PENDING_INTENT", r4.getPendingIntent());
        r22.putExtra("EXTRA_ERROR_NAME", "GET_UNKNOWN");
        r3.context.startActivity(r22);
        return Unit.INSTANCE;
    L5:
        return Unit.INSTANCE;
    }

    public static /* synthetic */ Unit $r8$lambda$VgFJQVU0Mq3s0QE5EFmAIP_MF3Q(Executor r0, CredentialManagerCallback r1, GetCredentialException r2) {
        return invokePlayServices$lambda$2$0(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$dEhfUFOIs3S_6RwWyT9DxgprdBU(CredentialManagerCallback r0, GetCredentialException r1) {
        invokePlayServices$lambda$2$0$0(r0, r1);
    }

    public static void $r8$lambda$g1NK9BtcsObnn4c8oTppRxi0Lyw(CredentialProviderGetDigitalCredentialController r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4, Exception r5) {
        GetCredentialException r12 = r1.fromGmsException(r5);
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r2, new CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda3(r3, r4, r12));
    }

    public static void $r8$lambda$nSVfv0HxhuRGFIDYxCIM1v0SQMk(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderGetDigitalCredentialController(Context r2) {
        super(r2);
        this.context = r2;
        this.resultReceiver = new CredentialProviderGetDigitalCredentialController$resultReceiver$1(this, new Handler(Looper.getMainLooper()));
    }

    public static final /* synthetic */ CancellationSignal access$getCancellationSignal$p(CredentialProviderGetDigitalCredentialController r0) {
        return r0.cancellationSignal;
    }

    public static final /* synthetic */ boolean access$maybeReportErrorFromResultReceiver(CredentialProviderGetDigitalCredentialController r0, Bundle r1, Function2 r2, Executor r3, CredentialManagerCallback r4, CancellationSignal r5) {
        return r0.maybeReportErrorFromResultReceiver(r1, r2, r3, r4, r5);
    }

    private final GetCredentialException fromGmsException(Throwable r3) {
        if ((r3 instanceof ApiException) == false) goto L15;
        int r2 = ((ApiException) r3).getStatusCode();
        if (r2 != 16) goto L9;
        return new GetCredentialCancellationException(r3.getMessage());
    L9:
        if (CredentialProviderBaseController.Companion.getRetryables().contains(Integer.valueOf(r2)) == false) goto L13;
        return new GetCredentialInterruptedException(r3.getMessage());
    L13:
        return new GetCredentialUnknownException("Get digital credential failed, failure: " + r3);
    L15:
        return new GetCredentialUnknownException("Get digital credential failed, failure: " + r3);
    }

    private static final Unit invokePlayServices$lambda$2$0(Executor r1, CredentialManagerCallback r2, GetCredentialException r3) {
        r1.execute(new CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda4(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$2$0$0(CredentialManagerCallback r0, GetCredentialException r1) {
        r0.onError(r1);
    }

    public com.google.android.gms.identitycredentials.GetCredentialRequest convertRequestToPlayServices(GetCredentialRequest r5) {
        ArrayList r4 = new ArrayList();
        Iterator<CredentialOption> r0 = r5.getCredentialOptions().iterator();
    L4:
        if (r0.hasNext() == false) goto L7;
        CredentialOption r1 = r0.next();
        goto L4
    L7:
        return new com.google.android.gms.identitycredentials.GetCredentialRequest(r4, GetCredentialRequest.Companion.getRequestMetadataBundle(r5), r5.getOrigin(), new ResultReceiver(null));
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
    public void invokePlayServices2(GetCredentialRequest r3, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r4, Executor r5, CancellationSignal r6) {
        this.cancellationSignal = r6;
        setCallback(r4);
        setExecutor(r5);
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r6) == false) goto L5;
        return;
    L5:
        com.google.android.gms.identitycredentials.GetCredentialRequest r32 = convertRequestToPlayServices(r3);
        IdentityCredentialManager.Companion.getClient(this.context).getCredential(r32).addOnSuccessListener(new CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda1(new CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda0(r6, this))).addOnFailureListener(new CredentialProviderGetDigitalCredentialController$$ExternalSyntheticLambda2(this, r6, r5, r4));
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
