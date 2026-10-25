package androidx.credentials.playservices.controllers.identitycredentials.signalcredentialstate;

import android.content.Context;
import android.os.CancellationSignal;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.SignalCredentialStateRequest;
import androidx.credentials.exceptions.publickeycredential.SignalCredentialRateLimitExceededException;
import androidx.credentials.exceptions.publickeycredential.SignalCredentialStateException;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.identitycredentials.IdentityCredentialManager;
import com.google.android.gms.identitycredentials.SignalCredentialStateResponse;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import kotlin.text.MatchGroup;
import kotlin.text.MatchGroupCollection;
import kotlin.text.MatchResult;
import kotlin.text.Regex;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0000\u0018\u0000 \u00172 \u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0001:\u0001\u0017B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ6\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u00022\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u0002H\u0016J\u0010\u0010\u0015\u001a\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u0004H\u0014R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/signalcredentialstate/SignalCredentialStateController;", "Landroidx/credentials/playservices/controllers/CredentialProviderController;", "Landroidx/credentials/SignalCredentialStateRequest;", "Lcom/google/android/gms/identitycredentials/SignalCredentialStateRequest;", "Lcom/google/android/gms/identitycredentials/SignalCredentialStateResponse;", "Landroidx/credentials/SignalCredentialStateResponse;", "Landroidx/credentials/exceptions/publickeycredential/SignalCredentialStateException;", "context", "Landroid/content/Context;", "<init>", "(Landroid/content/Context;)V", "invokePlayServices", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.REQUEST, Callback.METHOD_NAME, "Landroidx/credentials/CredentialManagerCallback;", "executor", "Ljava/util/concurrent/Executor;", "cancellationSignal", "Landroid/os/CancellationSignal;", "convertRequestToPlayServices", "convertResponseToCredentialManager", PluginsConstants.RESPONSE, "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public final class SignalCredentialStateController extends CredentialProviderController<SignalCredentialStateRequest, com.google.android.gms.identitycredentials.SignalCredentialStateRequest, SignalCredentialStateResponse, androidx.credentials.SignalCredentialStateResponse, SignalCredentialStateException> {
    public static final Companion Companion = null;
    private final Context context;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\t\u001a\u00020\b2\b\u0010\n\u001a\u0004\u0018\u00010\u0005J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eH\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0086T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/credentials/playservices/controllers/identitycredentials/signalcredentialstate/SignalCredentialStateController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "SIGNAL_REQUEST_JSON_KEY", _UrlKt.FRAGMENT_ENCODE_SET, "RATE_LIMIT_EXCEPTION_MESSAGE_MATCHER", "MAX_RETRY_TIME", _UrlKt.FRAGMENT_ENCODE_SET, "parseRefillMinutesRegex", "exceptionMessage", "getInstance", "Landroidx/credentials/playservices/controllers/identitycredentials/signalcredentialstate/SignalCredentialStateController;", "context", "Landroid/content/Context;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final SignalCredentialStateController getInstance(Context r1) {
            return new SignalCredentialStateController(r1);
        }

        public final long parseRefillMinutesRegex(String r6) {
            if (r6 != null) goto L5;
            return 600000;
        L5:
            MatchResult r5 = Regex.find$default(new Regex("^SignalCredentialState has been called too frequently\\. Please retry later after (\\d+) minutes\\.$"), r6, 0, 2, null);
            if (r5 == null) goto L17;
            MatchGroupCollection r52 = r5.getGroups();
            if (r52 == null) goto L17;
            MatchGroup r53 = r52.get(1);
            if (r53 == null) goto L17;
            String r54 = r53.getValue();
            if (r54 == null) goto L17;
            if (StringsKt.toIntOrNull(r54) == null) goto L17;
            return r5.intValue();
        L17:
            return 600000;
        }
    }

    public static Unit $r8$lambda$6KEYjefjbL2D35k9qVaXsXXKNtY(Executor r0, SignalCredentialStateController r1, CredentialManagerCallback r2, SignalCredentialStateResponse r3) {
        if (r3 != null) goto L5;
        r0.execute(new SignalCredentialStateController$$ExternalSyntheticLambda4(r2));
        return Unit.INSTANCE;
    L5:
        r0.execute(new SignalCredentialStateController$$ExternalSyntheticLambda5(r2, r1.convertResponseToCredentialManager(r3)));
        return Unit.INSTANCE;
    }

    public static void $r8$lambda$8j3IRezhVACEvG39T8XlexhzcMY(Function1 r0, Object r1) {
        r0.invoke(r1);
    }

    public static /* synthetic */ void $r8$lambda$KrTtx6vXoS47G4MDU3Ul3ZfTkJ4(CredentialManagerCallback r0, androidx.credentials.SignalCredentialStateResponse r1) {
        invokePlayServices$lambda$0$1(r0, r1);
    }

    /* renamed from: $r8$lambda$a1iprMjAVocEOB93f2u-yyTumBs, reason: not valid java name */
    public static /* synthetic */ void m721$r8$lambda$a1iprMjAVocEOB93f2uyyTumBs(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        invokePlayServices$lambda$2$0(r0, r1);
    }

    public static /* synthetic */ void $r8$lambda$b3puoOwGj3hPeeVU7q4sQGzSAjA(CredentialManagerCallback r0) {
        invokePlayServices$lambda$0$0(r0);
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [T, androidx.credentials.exceptions.publickeycredential.SignalCredentialStateException] */
    /* JADX WARN: Type inference failed for: r1v8, types: [T, androidx.credentials.exceptions.publickeycredential.SignalCredentialRateLimitExceededException] */
    public static void $r8$lambda$tFkdh6pwndChndQeiErFyM9E6N8(Executor r6, CredentialManagerCallback r7, Exception r8) {
        Ref.ObjectRef r0 = new Ref.ObjectRef();
        r0.element = SignalCredentialStateException.Companion.createFrom(r8.getMessage());
        if ((r8 instanceof ApiException) == true) goto L5;
    L11:
        r6.execute(new SignalCredentialStateController$$ExternalSyntheticLambda3(r7, r0));
        return;
    L5:
        if (((ApiException) r8).getStatusCode() != 16) goto L11;
        String r1 = r8.getMessage();
        if (r1 == null) goto L11;
        if (StringsKt.contains$default(r1, "called too frequently", false, 2, null) != true) goto L11;
        r0.element = new SignalCredentialRateLimitExceededException(Companion.parseRefillMinutesRegex(r8.getMessage()), r8.getMessage());
        goto L11
    }

    static {
        Companion = new Companion(null);
    }

    public SignalCredentialStateController(Context r1) {
        super(r1);
        this.context = r1;
    }

    private static final void invokePlayServices$lambda$0$0(CredentialManagerCallback r2) {
        r2.onError(SignalCredentialStateException.Companion.createFrom("No SignalCredentialStateResponse received"));
    }

    private static final void invokePlayServices$lambda$0$1(CredentialManagerCallback r0, androidx.credentials.SignalCredentialStateResponse r1) {
        r0.onResult(r1);
    }

    private static final void invokePlayServices$lambda$2$0(CredentialManagerCallback r0, Ref.ObjectRef r1) {
        r0.onError(r1.element);
    }

    public com.google.android.gms.identitycredentials.SignalCredentialStateRequest convertRequestToPlayServices(SignalCredentialStateRequest r1) {
        throw null;
    }

    public androidx.credentials.SignalCredentialStateResponse convertResponseToCredentialManager(SignalCredentialStateResponse r1) {
        return new androidx.credentials.SignalCredentialStateResponse();
    }

    /* renamed from: invokePlayServices, reason: avoid collision after fix types in other method */
    public void invokePlayServices2(SignalCredentialStateRequest r2, CredentialManagerCallback<androidx.credentials.SignalCredentialStateResponse, SignalCredentialStateException> r3, Executor r4, CancellationSignal r5) {
        com.google.android.gms.identitycredentials.SignalCredentialStateRequest r22 = convertRequestToPlayServices(r2);
        IdentityCredentialManager.Companion.getClient(this.context).signalCredentialState(r22).addOnSuccessListener(new SignalCredentialStateController$$ExternalSyntheticLambda1(new SignalCredentialStateController$$ExternalSyntheticLambda0(r4, this, r3))).addOnFailureListener(new SignalCredentialStateController$$ExternalSyntheticLambda2(r4, r3));
    }

    @Override // androidx.credentials.playservices.controllers.CredentialProviderController
    public /* bridge */ /* synthetic */ void invokePlayServices(SignalCredentialStateRequest r1, CredentialManagerCallback<androidx.credentials.SignalCredentialStateResponse, SignalCredentialStateException> r2, Executor r3, CancellationSignal r4) {
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        invokePlayServices2(null, r2, r3, r4);
    }
}
