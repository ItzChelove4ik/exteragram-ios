package androidx.credentials;

import android.annotation.SuppressLint;
import android.content.Context;
import android.credentials.CreateCredentialRequest;
import android.credentials.GetCredentialRequest;
import android.os.Build;
import android.os.CancellationSignal;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialUnsupportedException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnsupportedException;
import androidx.credentials.internal.ConversionUtilsKt;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.Iterator;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.SourceDebugExtension;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u0094\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u0000 =2\u00020\u0001:\u0001=B\u000f\u0012\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\n\u001a\u00020\t2\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001f\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0003\u001a\u00020\u0002H\u0002¢\u0006\u0004\b\u000f\u0010\u0010J\u001f\u0010\u0013\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0011H\u0003¢\u0006\u0004\b\u0013\u0010\u0014J\u0017\u0010\u0017\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u0015H\u0002¢\u0006\u0004\b\u0017\u0010\u0018J\u001f\u0010\u001a\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u00152\u0006\u0010\u0012\u001a\u00020\u0019H\u0003¢\u0006\u0004\b\u001a\u0010\u001bJE\u0010$\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u00152\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020!\u0012\u0004\u0012\u00020\"0 H\u0016¢\u0006\u0004\b$\u0010%JE\u0010(\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\f2\b\u0010\u001d\u001a\u0004\u0018\u00010\u001c2\u0006\u0010\u001f\u001a\u00020\u001e2\u0012\u0010#\u001a\u000e\u0012\u0004\u0012\u00020&\u0012\u0004\u0012\u00020'0 H\u0016¢\u0006\u0004\b(\u0010)J\u0017\u0010.\u001a\u00020\"2\u0006\u0010+\u001a\u00020*H\u0000¢\u0006\u0004\b,\u0010-J\u0017\u00102\u001a\u00020'2\u0006\u0010+\u001a\u00020/H\u0000¢\u0006\u0004\b0\u00101J\u0017\u00107\u001a\u00020!2\u0006\u00104\u001a\u000203H\u0000¢\u0006\u0004\b5\u00106J\u000f\u00108\u001a\u00020\tH\u0016¢\u0006\u0004\b8\u00109R\u0016\u0010;\u001a\u0004\u0018\u00010:8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b;\u0010<¨\u0006>"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl;", "Landroidx/credentials/CredentialProvider;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "handleNullCredMan", _UrlKt.FRAGMENT_ENCODE_SET, "isCredmanDisabled", "(Lkotlin/jvm/functions/Function0;)Z", "Landroidx/credentials/CreateCredentialRequest;", PluginsConstants.REQUEST, "Landroid/credentials/CreateCredentialRequest;", "convertCreateRequestToFrameworkClass", "(Landroidx/credentials/CreateCredentialRequest;Landroid/content/Context;)Landroid/credentials/CreateCredentialRequest;", "Landroid/credentials/CreateCredentialRequest$Builder;", "builder", "setOriginForCreateRequest", "(Landroidx/credentials/CreateCredentialRequest;Landroid/credentials/CreateCredentialRequest$Builder;)V", "Landroidx/credentials/GetCredentialRequest;", "Landroid/credentials/GetCredentialRequest;", "convertGetRequestToFrameworkClass", "(Landroidx/credentials/GetCredentialRequest;)Landroid/credentials/GetCredentialRequest;", "Landroid/credentials/GetCredentialRequest$Builder;", "setOriginForGetRequest", "(Landroidx/credentials/GetCredentialRequest;Landroid/credentials/GetCredentialRequest$Builder;)V", "Landroid/os/CancellationSignal;", "cancellationSignal", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", Callback.METHOD_NAME, "onGetCredential", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/CreateCredentialResponse;", "Landroidx/credentials/exceptions/CreateCredentialException;", "onCreateCredential", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/credentials/GetCredentialException;", "error", "convertToJetpackGetException$credentials", "(Landroid/credentials/GetCredentialException;)Landroidx/credentials/exceptions/GetCredentialException;", "convertToJetpackGetException", "Landroid/credentials/CreateCredentialException;", "convertToJetpackCreateException$credentials", "(Landroid/credentials/CreateCredentialException;)Landroidx/credentials/exceptions/CreateCredentialException;", "convertToJetpackCreateException", "Landroid/credentials/GetCredentialResponse;", PluginsConstants.RESPONSE, "convertGetResponseToJetpackClass$credentials", "(Landroid/credentials/GetCredentialResponse;)Landroidx/credentials/GetCredentialResponse;", "convertGetResponseToJetpackClass", "isAvailableOnDevice", "()Z", "Landroid/credentials/CredentialManager;", "credentialManager", "Landroid/credentials/CredentialManager;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nCredentialProviderFrameworkImpl.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CredentialProviderFrameworkImpl.kt\nandroidx/credentials/CredentialProviderFrameworkImpl\n+ 2 _Collections.kt\nkotlin/collections/CollectionsKt___CollectionsKt\n*L\n1#1,378:1\n1869#2,2:379\n*S KotlinDebug\n*F\n+ 1 CredentialProviderFrameworkImpl.kt\nandroidx/credentials/CredentialProviderFrameworkImpl\n*L\n261#1:379,2\n*E\n"})
/* loaded from: classes3.dex */
public final class CredentialProviderFrameworkImpl implements CredentialProvider {
    private static final Companion Companion = null;
    private final android.credentials.CredentialManager credentialManager;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Landroidx/credentials/CredentialProviderFrameworkImpl$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "GET_DOM_EXCEPTION_PREFIX", "CREATE_DOM_EXCEPTION_PREFIX", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    /* renamed from: $r8$lambda$BGTCgAZAUrx_nDBb8V1l-E-T4sA, reason: not valid java name */
    public static Unit m673$r8$lambda$BGTCgAZAUrx_nDBb8V1lET4sA(CredentialManagerCallback r2) {
        r2.onError(new GetCredentialUnsupportedException("Your device doesn't support credential manager"));
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$llBVW9jm112fI6SuOs48aXaWuhk(CredentialManagerCallback r2) {
        r2.onError(new CreateCredentialUnsupportedException("Your device doesn't support credential manager"));
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderFrameworkImpl(Context r2) {
        this.credentialManager = CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline6.m(r2.getSystemService("credential"));
    }

    private final android.credentials.CreateCredentialRequest convertCreateRequestToFrameworkClass(CreateCredentialRequest r3, Context r4) {
        CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline5.m();
        CreateCredentialRequest.Builder r42 = CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline4.m(r3.getType(), ConversionUtilsKt.getFinalCreateCredentialData(r3, r4), r3.getCandidateQueryData()).setIsSystemProviderRequired(r3.isSystemProviderRequired()).setAlwaysSendAppInfoToProvider(true);
        setOriginForCreateRequest(r3, r42);
        return r42.build();
    }

    private final android.credentials.GetCredentialRequest convertGetRequestToFrameworkClass(GetCredentialRequest r7) {
        CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline2.m();
        GetCredentialRequest.Builder r0 = CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline0.m(GetCredentialRequest.Companion.getRequestMetadataBundle(r7));
        Iterator<T> r1 = r7.getCredentialOptions().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        CredentialOption r2 = (CredentialOption) r1.next();
        CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline3.m();
        r0.addCredentialOption(CredentialProviderFrameworkImpl$$ExternalSyntheticApiModelOutline1.m(r2.getType(), r2.getRequestData(), r2.getCandidateQueryData()).setIsSystemProviderRequired(r2.isSystemProviderRequired()).setAllowedProviders(r2.getAllowedProviders()).build());
        goto L4
    L6:
        setOriginForGetRequest(r7, r0);
        return r0.build();
    }

    private final boolean isCredmanDisabled(Function0<Unit> r1) {
        if (this.credentialManager != null) goto L6;
        r1.invoke();
        return true;
    L6:
        return false;
    }

    @SuppressLint({"MissingPermission"})
    private final void setOriginForCreateRequest(CreateCredentialRequest r1, CreateCredentialRequest.Builder r2) {
        if (r1.getOrigin() == null) goto L6;
        r2.setOrigin(r1.getOrigin());
        return;
    }

    @SuppressLint({"MissingPermission"})
    private final void setOriginForGetRequest(GetCredentialRequest r1, GetCredentialRequest.Builder r2) {
        if (r1.getOrigin() == null) goto L6;
        r2.setOrigin(r1.getOrigin());
        return;
    }

    public final GetCredentialResponse convertGetResponseToJetpackClass$credentials(android.credentials.GetCredentialResponse r3) {
        android.credentials.Credential r2 = r3.getCredential();
        return new GetCredentialResponse(Credential.Companion.createFrom(r2.getType(), r2.getData()));
    }

    public final CreateCredentialException convertToJetpackCreateException$credentials(android.credentials.CreateCredentialException r1) {
        return ConversionUtilsKt.toJetpackCreateException(r1.getType(), r1.getMessage());
    }

    public final GetCredentialException convertToJetpackGetException$credentials(android.credentials.GetCredentialException r1) {
        return ConversionUtilsKt.toJetpackGetException(r1.getType(), r1.getMessage());
    }

    @Override // androidx.credentials.CredentialProvider
    public boolean isAvailableOnDevice() {
        if (Build.VERSION.SDK_INT >= 34) goto L5;
        return false;
    L5:
        if (this.credentialManager == null) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // androidx.credentials.CredentialProvider
    public void onCreateCredential(Context r2, CreateCredentialRequest r3, CancellationSignal r4, Executor r5, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r6) {
        if (isCredmanDisabled(new CredentialProviderFrameworkImpl$$ExternalSyntheticLambda8(r6)) == false) goto L5;
        return;
    L5:
        CredentialProviderFrameworkImpl$onCreateCredential$outcome$1 r62 = new CredentialProviderFrameworkImpl$onCreateCredential$outcome$1(r6, r3, this);
        this.credentialManager.createCredential(r2, convertCreateRequestToFrameworkClass(r3, r2), r4, r5, r62);
    }

    @Override // androidx.credentials.CredentialProvider
    public void onGetCredential(Context r2, GetCredentialRequest r3, CancellationSignal r4, Executor r5, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r6) {
        if (isCredmanDisabled(new CredentialProviderFrameworkImpl$$ExternalSyntheticLambda7(r6)) == false) goto L5;
        return;
    L5:
        CredentialProviderFrameworkImpl$onGetCredential$outcome$2 r62 = new CredentialProviderFrameworkImpl$onGetCredential$outcome$2(r6, this);
        this.credentialManager.getCredential(r2, convertGetRequestToFrameworkClass(r3), r4, r5, r62);
    }
}
