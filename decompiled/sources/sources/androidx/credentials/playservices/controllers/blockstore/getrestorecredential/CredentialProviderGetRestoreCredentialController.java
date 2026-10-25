package androidx.credentials.playservices.controllers.blockstore.getrestorecredential;

import android.content.Context;
import android.os.CancellationSignal;
import androidx.credentials.Credential;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.CredentialOption;
import androidx.credentials.GetCredentialRequest;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.exceptions.NoCredentialException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialRequest;
import com.google.android.gms.auth.blockstore.restorecredential.GetRestoreCredentialResponse;
import com.google.android.gms.auth.blockstore.restorecredential.RestoreCredential;
import com.google.android.gms.common.api.ApiException;
import com.sun.jna.Callback;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0000\u0018\u00002 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0004H\u0016R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Landroidx/credentials/playservices/controllers/blockstore/getrestorecredential/CredentialProviderGetRestoreCredentialController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/GetCredentialRequest;", "Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialRequest;", "Lcom/google/android/gms/auth/blockstore/restorecredential/GetRestoreCredentialResponse;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "invokePlayServices", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, Callback.METHOD_NAME, "Landroidx/credentials/CredentialManagerCallback;", "executor", "Ljava/util/concurrent/Executor;", "cancellationSignal", "Landroid/os/CancellationSignal;", "convertRequestToPlayServices", "convertResponseToCredentialManager", PluginsConstants.RESPONSE, "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class CredentialProviderGetRestoreCredentialController extends CredentialProviderController<GetCredentialRequest, GetRestoreCredentialRequest, GetRestoreCredentialResponse, GetCredentialResponse, GetCredentialException> {
    private final Context context;

    public static /* synthetic */ Unit $r8$lambda$82BNbyG1HnSoswvjbSpH7RSPSSc(Executor r0, CredentialManagerCallback r1, Exception r2) {
        return invokePlayServices$lambda$0$1(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$KOrOLiyOLszXzFVEX7PEtuFvb7E(CredentialManagerCallback r0, GetCredentialResponse r1) {
        invokePlayServices$lambda$0$0$0(r0, r1);
    }

    /* renamed from: $r8$lambda$LBZnVkbW-B8CRzdw0L5Xdm213kE, reason: not valid java name */
    public static void m686$r8$lambda$LBZnVkbWB8CRzdw0L5Xdm213kE(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static /* synthetic */ Unit $r8$lambda$Qr2yod4HQKAhMYR2yqtwdQMONlk(Executor r0, CredentialManagerCallback r1, Ref.ObjectRef r2) {
        return invokePlayServices$lambda$2$0(r0, r1, r2);
    }

    public static Unit $r8$lambda$RzDSQNuMWPQN0LmT82FX7UtPaM8(CredentialProviderGetRestoreCredentialController r1, CancellationSignal r2, Executor r3, CredentialManagerCallback r4, GetRestoreCredentialResponse r5) {
        GetCredentialResponse r12 = r1.convertResponseToCredentialManager(r5);     // Catch: Exception -> L4
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r2, new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda3(r3, r4, r12));     // Catch: Exception -> L4
    L7:
        return Unit.INSTANCE;
    L4:
        e = move-exception;
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r2, new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda4(r3, r4, e));
        goto L7
    }

    /* renamed from: $r8$lambda$YCaZPEfABwcobKMf-mdYs5IJGpc, reason: not valid java name */
    public static /* synthetic */ Unit m687$r8$lambda$YCaZPEfABwcobKMfmdYs5IJGpc(Executor r0, CredentialManagerCallback r1, GetCredentialResponse r2) {
        return invokePlayServices$lambda$0$0(r0, r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$hHHRU_r8yQ6pC85WTLEcbfy_LJY(CredentialManagerCallback r0, Exception r1) {
        invokePlayServices$lambda$0$1$0(r0, r1);
    }

    /* JADX WARN: Type inference failed for: r1v0, types: [T, androidx.credentials.exceptions.GetCredentialUnknownException] */
    /* JADX WARN: Type inference failed for: r1v5, types: [T, androidx.credentials.exceptions.GetCredentialUnknownException] */
    /* JADX WARN: Type inference failed for: r2v3, types: [T, androidx.credentials.exceptions.GetCredentialUnknownException] */
    /* renamed from: $r8$lambda$tO6y8ElnBXVfZNoZjI-XJpiQLZI, reason: not valid java name */
    public static void m688$r8$lambda$tO6y8ElnBXVfZNoZjIXJpiQLZI(CancellationSignal r5, Executor r6, CredentialManagerCallback r7, Exception r8) {
        Ref.ObjectRef r0 = new Ref.ObjectRef();
        r0.element = new GetCredentialUnknownException("Get restore credential failed for unknown reason, failure: " + r8.getMessage());
        if ((r8 instanceof ApiException) == false) goto L8;
        ApiException r1 = (ApiException) r8;
        if (r1.getStatusCode() != 40201) goto L7;
        r0.element = new GetCredentialUnknownException("The restore credential internal service had a failure, failure: " + r8.getMessage());
        goto L8
    L7:
        r0.element = new GetCredentialUnknownException("The restore credential service failed with unsupported status code, failure: " + r8.getMessage() + ", status code: " + r1.getStatusCode());
    L8:
        CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r5, new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda5(r6, r7, r0));
    }

    /* renamed from: $r8$lambda$vw_ntyNwI101GixVLVll1-GMkj4, reason: not valid java name */
    public static /* synthetic */ void m689$r8$lambda$vw_ntyNwI101GixVLVll1GMkj4(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        invokePlayServices$lambda$2$0$0(r0, r1);
    }

    public CredentialProviderGetRestoreCredentialController(Context r1) {
        super(r1);
        this.context = r1;
    }

    private static final Unit invokePlayServices$lambda$0$0(Executor r1, CredentialManagerCallback r2, GetCredentialResponse r3) {
        r1.execute(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda6(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$0$0(CredentialManagerCallback r0, GetCredentialResponse r1) {
        r0.onResult(r1);
    }

    private static final Unit invokePlayServices$lambda$0$1(Executor r1, CredentialManagerCallback r2, Exception r3) {
        r1.execute(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda8(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$0$1$0(CredentialManagerCallback r1, Exception r2) {
        if ((r2 instanceof NoCredentialException) == false) goto L5;
        GetCredentialException r22 = (GetCredentialException) r2;
    L6:
        r1.onError(r22);
        return;
    L5:
        r22 = new GetCredentialUnknownException(r2.getMessage());
        goto L6
    }

    private static final Unit invokePlayServices$lambda$2$0(Executor r1, CredentialManagerCallback r2, Ref.ObjectRef r3) {
        r1.execute(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda7(r2, r3));
        return Unit.INSTANCE;
    }

    private static final void invokePlayServices$lambda$2$0$0(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        r0.onError(r1.element);
    }

    public GetRestoreCredentialRequest convertRequestToPlayServices(GetCredentialRequest r1) {
        Iterator<CredentialOption> r0 = r1.getCredentialOptions().iterator();
    L4:
        if (r0.hasNext() == false) goto L7;
        CredentialOption r12 = r0.next();
        goto L4
    L7:
        throw null;
    }

    public GetCredentialResponse convertResponseToCredentialManager(GetRestoreCredentialResponse r3) {
        return new GetCredentialResponse(Credential.Companion.createFrom("androidx.credentials.TYPE_RESTORE_CREDENTIAL", r3.getResponseBundle()));
    }

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(GetCredentialRequest r2, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r3, Executor r4, CancellationSignal r5) {
        if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r5) == false) goto L5;
        return;
    L5:
        GetRestoreCredentialRequest r22 = convertRequestToPlayServices(r2);
        RestoreCredential.getRestoreCredentialClient(this.context).getRestoreCredential(r22).addOnSuccessListener(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda1(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda0(this, r5, r4, r3))).addOnFailureListener(new CredentialProviderGetRestoreCredentialController$$ExternalSyntheticLambda2(r5, r4, r3));
    }

    @Override // androidx.credentials.playservices.controllers.CredentialProviderController
    public /* bridge */ /* synthetic */ void invokePlayServices(GetCredentialRequest r1, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r2, Executor r3, CancellationSignal r4) {
        invokePlayServices2(r1, r2, r3, r4);
    }
}
