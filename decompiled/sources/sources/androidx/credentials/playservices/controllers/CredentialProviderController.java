package androidx.credentials.playservices.controllers;

import android.content.Context;
import android.os.Bundle;
import android.os.CancellationSignal;
import androidx.credentials.CredentialManagerCallback;
import androidx.credentials.exceptions.CreateCredentialCancellationException;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.CreateCredentialUnknownException;
import androidx.credentials.exceptions.GetCredentialCancellationException;
import androidx.credentials.exceptions.GetCredentialException;
import androidx.credentials.exceptions.GetCredentialUnknownException;
import androidx.credentials.playservices.CredentialProviderPlayServicesImpl;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Ref;
import okhttp3.internal.url._UrlKt;
import okio.ByteString$$ExternalSyntheticBUOutline0;

@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\b \u0018\u0000 \u001f*\b\b\u0000\u0010\u0002*\u00020\u0001*\b\b\u0001\u0010\u0003*\u00020\u0001*\b\b\u0002\u0010\u0004*\u00020\u0001*\b\b\u0003\u0010\u0005*\u00020\u0001*\b\b\u0004\u0010\u0006*\u00020\u00012\u00020\u0007:\u0001\u001fB\u000f\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ[\u0010\u0018\u001a\u00020\u00172\u0006\u0010\r\u001a\u00020\f2\u001c\u0010\u0010\u001a\u0018\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u000f\u0012\u0004\u0012\u00028\u00040\u000e2\u0006\u0010\u0012\u001a\u00020\u00112\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\u0015H\u0004¢\u0006\u0004\b\u0018\u0010\u0019J?\u0010\u001c\u001a\u00020\u001b2\u0006\u0010\u001a\u001a\u00028\u00002\u0012\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00028\u0003\u0012\u0004\u0012\u00028\u00040\u00132\u0006\u0010\u0012\u001a\u00020\u00112\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0015H&¢\u0006\u0004\b\u001c\u0010\u001dR\u0014\u0010\t\u001a\u00020\b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\t\u0010\u001e¨\u0006 "}, d2 = {"Landroidx/credentials/playservices/controllers/CredentialProviderController;", _UrlKt.FRAGMENT_ENCODE_SET, "T1", "T2", "R2", "R1", "E1", "Landroidx/credentials/playservices/controllers/CredentialProviderBaseController;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/os/Bundle;", "resultData", "Lkotlin/Function2;", _UrlKt.FRAGMENT_ENCODE_SET, "conversionFn", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", Callback.METHOD_NAME, "Landroid/os/CancellationSignal;", "cancellationSignal", _UrlKt.FRAGMENT_ENCODE_SET, "maybeReportErrorFromResultReceiver", "(Landroid/os/Bundle;Lkotlin/jvm/functions/Function2;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;Landroid/os/CancellationSignal;)Z", PluginsConstants.REQUEST, _UrlKt.FRAGMENT_ENCODE_SET, "invokePlayServices", "(Ljava/lang/Object;Landroidx/credentials/CredentialManagerCallback;Ljava/util/concurrent/Executor;Landroid/os/CancellationSignal;)V", "Landroid/content/Context;", "Companion", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
/* loaded from: classes3.dex */
public abstract class CredentialProviderController<T1, T2, R2, R1, E1> extends CredentialProviderBaseController {
    public static final Companion Companion = null;
    private final Context context;

    @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2 \u0010\n\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000e0\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u000e0\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0005J\u0015\u0010\u0013\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0014J\r\u0010\u0015\u001a\u00020\u0005H\u0000¢\u0006\u0002\b\u0016JU\u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2 \u0010\n\u001a\u001c\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r\u0012\u0004\u0012\u00020\u000e0\u000b2\u0012\u0010\u000f\u001a\u000e\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00020\u000e0\u00102\b\u0010\u0012\u001a\u0004\u0018\u00010\fH\u0001¢\u0006\u0002\b\u0019J%\u0010\u001a\u001a\u00020\u000e2\b\u0010\u0012\u001a\u0004\u0018\u00010\f2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0001¢\u0006\u0002\b\u001cR\u000e\u0010\u0004\u001a\u00020\u0005X\u0080T¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Landroidx/credentials/playservices/controllers/CredentialProviderController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "ERROR_MESSAGE_START_ACTIVITY_FAILED", _UrlKt.FRAGMENT_ENCODE_SET, "maybeReportErrorResultCodeCreate", _UrlKt.FRAGMENT_ENCODE_SET, "resultCode", _UrlKt.FRAGMENT_ENCODE_SET, "cancelOnError", "Lkotlin/Function2;", "Landroid/os/CancellationSignal;", "Lkotlin/Function0;", _UrlKt.FRAGMENT_ENCODE_SET, "onError", "Lkotlin/Function1;", "Landroidx/credentials/exceptions/CreateCredentialException;", "cancellationSignal", "generateErrorStringUnknown", "generateErrorStringUnknown$credentials_play_services_auth", "generateErrorStringCanceled", "generateErrorStringCanceled$credentials_play_services_auth", "maybeReportErrorResultCodeGet", "Landroidx/credentials/exceptions/GetCredentialException;", "maybeReportErrorResultCodeGet$credentials_play_services_auth", "cancelOrCallbackExceptionOrResult", "onResultOrException", "cancelOrCallbackExceptionOrResult$credentials_play_services_auth", "credentials-play-services-auth"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        /* renamed from: $r8$lambda$frtSp-QNAEdzTZHBCRu3VcqA-Pg, reason: not valid java name */
        public static Unit m681$r8$lambda$frtSpQNAEdzTZHBCRu3VcqAPg(Function1 r0, Ref.ObjectRef r1) {
            r0.invoke(r1.element);
            return Unit.INSTANCE;
        }

        public static Unit $r8$lambda$gJXSR3NACyPlTRlP79kwjKj4Ji4(Function1 r0, Ref.ObjectRef r1) {
            r0.invoke(r1.element);
            return Unit.INSTANCE;
        }

        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }

        @JvmStatic
        public final void cancelOrCallbackExceptionOrResult$credentials_play_services_auth(CancellationSignal r1, Function0<Unit> r2) {
            if (CredentialProviderPlayServicesImpl.Companion.cancellationReviewer$credentials_play_services_auth(r1) == false) goto L5;
            return;
        L5:
            r2.invoke();
        }

        public final String generateErrorStringCanceled$credentials_play_services_auth() {
            return "activity is cancelled by the user.";
        }

        public final String generateErrorStringUnknown$credentials_play_services_auth(int r2) {
            return "activity with result code: " + r2 + " indicating not RESULT_OK";
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [T, androidx.credentials.exceptions.CreateCredentialUnknownException] */
        /* JADX WARN: Type inference failed for: r4v1, types: [T, androidx.credentials.exceptions.CreateCredentialCancellationException] */
        @JvmStatic
        public final boolean maybeReportErrorResultCodeCreate(int r4, Function2<? super CancellationSignal, ? super Function0<Unit>, Unit> r5, Function1<? super CreateCredentialException, Unit> r6, CancellationSignal r7) {
            if (r4 == (-1)) goto L9;
            Ref.ObjectRef r0 = new Ref.ObjectRef();
            r0.element = new CreateCredentialUnknownException(generateErrorStringUnknown$credentials_play_services_auth(r4));
            if (r4 != 0) goto L7;
            r0.element = new CreateCredentialCancellationException(generateErrorStringCanceled$credentials_play_services_auth());
        L7:
            r5.invoke(r7, new CredentialProviderController$Companion$$ExternalSyntheticLambda0(r6, r0));
            return true;
        L9:
            return false;
        }

        /* JADX WARN: Type inference failed for: r1v0, types: [T, androidx.credentials.exceptions.GetCredentialUnknownException] */
        /* JADX WARN: Type inference failed for: r4v1, types: [T, androidx.credentials.exceptions.GetCredentialCancellationException] */
        @JvmStatic
        public final boolean maybeReportErrorResultCodeGet$credentials_play_services_auth(int r4, Function2<? super CancellationSignal, ? super Function0<Unit>, Unit> r5, Function1<? super GetCredentialException, Unit> r6, CancellationSignal r7) {
            if (r4 == (-1)) goto L9;
            Ref.ObjectRef r0 = new Ref.ObjectRef();
            r0.element = new GetCredentialUnknownException(generateErrorStringUnknown$credentials_play_services_auth(r4));
            if (r4 != 0) goto L7;
            r0.element = new GetCredentialCancellationException(generateErrorStringCanceled$credentials_play_services_auth());
        L7:
            r5.invoke(r7, new CredentialProviderController$Companion$$ExternalSyntheticLambda1(r6, r0));
            return true;
        L9:
            return false;
        }
    }

    public static /* synthetic */ void $r8$lambda$6Usb3RlKxkx2BBb45dTT9Y_sTP8(CredentialManagerCallback r0, Object r1) {
        maybeReportErrorFromResultReceiver$lambda$0$0(r0, r1);
    }

    public static Unit $r8$lambda$79ayB0QihD1PtURqqgQNUCNY1mI(Executor r1, CredentialManagerCallback r2, Object r3) {
        r1.execute(new CredentialProviderController$$ExternalSyntheticLambda1(r2, r3));
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public CredentialProviderController(Context r1) {
        super(r1);
        this.context = r1;
    }

    public static /* synthetic */ void invokePlayServices$default(CredentialProviderController r0, Object r1, CredentialManagerCallback r2, Executor r3, CancellationSignal r4, int r5, Object r6) {
        if (r6 == null) goto L4;
        ByteString$$ExternalSyntheticBUOutline0.m("Super calls with default arguments not supported in this target, function: invokePlayServices");
        return;
    L4:
        if ((r5 & 8) == 0) goto L6;
        r4 = null;
    L6:
        r0.invokePlayServices(r1, r2, r3, r4);
    }

    private static final void maybeReportErrorFromResultReceiver$lambda$0$0(CredentialManagerCallback r0, Object r1) {
        r0.onError(r1);
    }

    @JvmStatic
    public static final boolean maybeReportErrorResultCodeCreate(int r1, Function2<? super CancellationSignal, ? super Function0<Unit>, Unit> r2, Function1<? super CreateCredentialException, Unit> r3, CancellationSignal r4) {
        return Companion.maybeReportErrorResultCodeCreate(r1, r2, r3, r4);
    }

    public abstract void invokePlayServices(T1 r1, CredentialManagerCallback<R1, E1> r2, Executor r3, CancellationSignal r4);

    public final boolean maybeReportErrorFromResultReceiver(Bundle r2, Function2<? super String, ? super String, ? extends E1> r3, Executor r4, CredentialManagerCallback<R1, E1> r5, CancellationSignal r6) {
        if (r2.getBoolean("FAILURE_RESPONSE") == true) goto L6;
        return false;
    L6:
        E1 r1 = r3.invoke(r2.getString("EXCEPTION_TYPE"), r2.getString("EXCEPTION_MESSAGE"));
        Companion.cancelOrCallbackExceptionOrResult$credentials_play_services_auth(r6, new CredentialProviderController$$ExternalSyntheticLambda0(r4, r5, r1));
        return true;
    }
}
