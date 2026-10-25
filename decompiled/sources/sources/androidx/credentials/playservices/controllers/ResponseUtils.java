package androidx.credentials.playservices.controllers;

import android.content.Intent;
import android.os.CancellationSignal;
import android.util.Log;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.GetCredentialResponse;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.controllers.CredentialProviderBaseController;
import androidx.credentials.playservices.controllers.CredentialProviderController;
import androidx.credentials.provider.PendingIntentHandler;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\b\u0001\u0018\u0000 \u00022\u00020\u0001:\u0001\u0002¨\u0006\u0003"}, d2 = {"Landroidx/credentials/playservices/controllers/ResponseUtils;", _UrlKt.FRAGMENT_ENCODE_SET, "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class ResponseUtils {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JH\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u000e2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00120\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014H\u0007R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000¨\u0006\u0015"}, d2 = {"Landroidx/credentials/playservices/controllers/ResponseUtils$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "TAG", _UrlKt.FRAGMENT_ENCODE_SET, "handleGetCredentialResponse", _UrlKt.FRAGMENT_ENCODE_SET, "uniqueRequestCode", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", "data", "Landroid/content/Intent;", "executor", "Ljava/util/concurrent/Executor;", Callback.METHOD_NAME, "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", "cancellationSignal", "Landroid/os/CancellationSignal;", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        public static Unit $r8$lambda$4xDG5tuFzF9vPvJ3Fz0XD1ZPNoc(CancellationSignal r1, Function0 r2) {
            CredentialProviderController.Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r1, r2);
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void $r8$lambda$A06gwChzcFuhXsKmyMy0CkBHQxI(CredentialManagerCallback r0) {
            handleGetCredentialResponse$lambda$2$0(r0);
        }

        /* renamed from: $r8$lambda$JE-GM6T_V8V7e_XFAqAqKCqwbUo, reason: not valid java name */
        public static /* synthetic */ void m682$r8$lambda$JEGM6T_V8V7e_XFAqAqKCqwbUo(CredentialManagerCallback r0, GetCredentialResponse r1) {
            handleGetCredentialResponse$lambda$3$0(r0, r1);
        }

        public static Unit $r8$lambda$Rj_QcAAEo31zdXJsqOqntcts71A(Executor r1, CredentialManagerCallback r2) {
            r1.execute(new ResponseUtils$Companion$$ExternalSyntheticLambda7(r2));
            return Unit.INSTANCE;
        }

        /* renamed from: $r8$lambda$Y1y08ow1t71qyW_X-seA_tVzVBA, reason: not valid java name */
        public static Unit m683$r8$lambda$Y1y08ow1t71qyW_XseA_tVzVBA(Executor r1, CredentialManagerCallback r2, GetCredentialException r3) {
            r1.execute(new ResponseUtils$Companion$$ExternalSyntheticLambda6(r2, r3));
            return Unit.INSTANCE;
        }

        /* renamed from: $r8$lambda$dCdZlrjuJxGw6qcci-__6ElYZ7U, reason: not valid java name */
        public static /* synthetic */ void m684$r8$lambda$dCdZlrjuJxGw6qcci__6ElYZ7U(CredentialManagerCallback r0, GetCredentialException r1) {
            handleGetCredentialResponse$lambda$4$0(r0, r1);
        }

        public static Unit $r8$lambda$oiqC25vkrJEjP1b7huHgFi11C9A(Executor r1, CredentialManagerCallback r2, GetCredentialResponse r3) {
            r1.execute(new ResponseUtils$Companion$$ExternalSyntheticLambda5(r2, r3));
            return Unit.INSTANCE;
        }

        public static /* synthetic */ void $r8$lambda$u7ojyQKDmKKfz1_tCnTZEKMpmR8(CredentialManagerCallback r0, GetCredentialException r1) {
            handleGetCredentialResponse$lambda$1$0(r0, r1);
        }

        /* renamed from: $r8$lambda$udknnXJUkCi6PtqNHxZRjz-MAXc, reason: not valid java name */
        public static Unit m685$r8$lambda$udknnXJUkCi6PtqNHxZRjzMAXc(Executor r1, CredentialManagerCallback r2, GetCredentialException r3) {
            r1.execute(new ResponseUtils$Companion$$ExternalSyntheticLambda8(r2, r3));
            return Unit.INSTANCE;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        private static final void handleGetCredentialResponse$lambda$1$0(CredentialManagerCallback r0, GetCredentialException r1) {
            r0.onError(r1);
        }

        private static final void handleGetCredentialResponse$lambda$2$0(CredentialManagerCallback r2) {
            r2.onError(new GetCredentialUnknownException("No provider data returned."));
        }

        private static final void handleGetCredentialResponse$lambda$3$0(CredentialManagerCallback r0, GetCredentialResponse r1) {
            r0.onResult(r1);
        }

        private static final void handleGetCredentialResponse$lambda$4$0(CredentialManagerCallback r1, GetCredentialException r2) {
            if (r2 != null) goto L4;
            r2 = new GetCredentialUnknownException("No provider data returned");
        L4:
            r1.onError(r2);
        }

        @JvmStatic
        public final void handleGetCredentialResponse(int r2, int r3, Intent r4, Executor r5, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r6, CancellationSignal r7) {
            CredentialProviderBaseController.Companion r1 = CredentialProviderBaseController.Companion;
            if (r2 == r1.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth()) goto L6;
            Log.w("GetCredentialController", "Returned request code " + r1.getCONTROLLER_REQUEST_CODE$credentials_play_services_auth() + " which  does not match what was given " + r2);
            return;
        L6:
            CredentialProviderController.Companion r12 = CredentialProviderController.Companion;
            if (r12.maybeReportErrorResultCodeGet$credentials_play_services_auth(r3, new ResponseUtils$Companion$$ExternalSyntheticLambda0(), new ResponseUtils$Companion$$ExternalSyntheticLambda1(r5, r6), r7) == false) goto L9;
            return;
        L9:
            if (r4 != null) goto L12;
            r12.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r7, new ResponseUtils$Companion$$ExternalSyntheticLambda2(r5, r6));
            return;
        L12:
            PendingIntentHandler.Companion r22 = PendingIntentHandler.Companion;
            GetCredentialResponse r32 = r22.retrieveGetCredentialResponse(r4);
            if (r32 == null) goto L16;
            r12.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r7, new ResponseUtils$Companion$$ExternalSyntheticLambda3(r5, r6, r32));
            return;
        L16:
            r12.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r7, new ResponseUtils$Companion$$ExternalSyntheticLambda4(r5, r6, r22.retrieveGetCredentialException(r4)));
        }
    }

    static {
        Companion = new Companion(null);
    }
}
