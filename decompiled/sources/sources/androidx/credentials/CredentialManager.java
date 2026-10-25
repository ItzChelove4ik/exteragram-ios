package androidx.credentials;

import android.annotation.SuppressLint;
import android.app.PendingIntent;
import android.content.Context;
import android.os.CancellationSignal;
import androidx.credentials.exceptions.CreateCredentialException;
import androidx.credentials.exceptions.GetCredentialException;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.sun.jna.Callback;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.coroutines.Continuation;
import kotlin.coroutines.intrinsics.IntrinsicsKt;
import kotlin.coroutines.jvm.internal.DebugProbesKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlinx.coroutines.CancellableContinuationImpl;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bg\u0018\u0000 \u001b2\u00020\u0001:\u0001\u001bJ \u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0096@¢\u0006\u0004\b\u0007\u0010\bJE\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\t2\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00100\u000eH&¢\u0006\u0004\b\u0013\u0010\u0014JE\u0010\u0016\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u00042\b\u0010\u000b\u001a\u0004\u0018\u00010\n2\u0006\u0010\r\u001a\u00020\f2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00150\u000eH&¢\u0006\u0004\b\u0016\u0010\u0017J\u000f\u0010\u0019\u001a\u00020\u0018H'¢\u0006\u0004\b\u0019\u0010\u001aø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001cÀ\u0006\u0001"}, d2 = {"Landroidx/credentials/CredentialManager;", _UrlKt.FRAGMENT_ENCODE_SET, "Landroid/content/Context;", "context", "Landroidx/credentials/CreateCredentialRequest;", PluginsConstants.REQUEST, "Landroidx/credentials/CreateCredentialResponse;", "createCredential", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "Landroidx/credentials/GetCredentialRequest;", "Landroid/os/CancellationSignal;", "cancellationSignal", "Ljava/util/concurrent/Executor;", "executor", "Landroidx/credentials/CredentialManagerCallback;", "Landroidx/credentials/GetCredentialResponse;", "Landroidx/credentials/exceptions/GetCredentialException;", Callback.METHOD_NAME, _UrlKt.FRAGMENT_ENCODE_SET, "getCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/GetCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroidx/credentials/exceptions/CreateCredentialException;", "createCredentialAsync", "(Landroid/content/Context;Landroidx/credentials/CreateCredentialRequest;Landroid/os/CancellationSignal;Ljava/util/concurrent/Executor;Landroidx/credentials/CredentialManagerCallback;)V", "Landroid/app/PendingIntent;", "createSettingsPendingIntent", "()Landroid/app/PendingIntent;", "Companion", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
@SuppressLint({"ObsoleteSdkInt"})
@SourceDebugExtension({"SMAP\nCredentialManager.kt\nKotlin\n*S Kotlin\n*F\n+ 1 CredentialManager.kt\nandroidx/credentials/CredentialManager\n+ 2 CancellableContinuation.kt\nkotlinx/coroutines/CancellableContinuationKt\n*L\n1#1,547:1\n351#2,11:548\n351#2,11:559\n351#2,11:570\n351#2,11:581\n351#2,11:592\n351#2,11:603\n*S KotlinDebug\n*F\n+ 1 CredentialManager.kt\nandroidx/credentials/CredentialManager\n*L\n111#1:548,11\n164#1:559,11\n210#1:570,11\n260#1:581,11\n312#1:592,11\n359#1:603,11\n*E\n"})
/* loaded from: classes3.dex */
public interface CredentialManager {
    public static final Companion Companion = null;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007¨\u0006\b"}, d2 = {"Landroidx/credentials/CredentialManager$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "create", "Landroidx/credentials/CredentialManager;", "context", "Landroid/content/Context;", "credentials"}, k = 1, mv = {2, 1, 0}, xi = 48)
    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = null;

        static {
            $$INSTANCE = new Companion();
        }

        private Companion() {
        }

        @JvmStatic
        public final CredentialManager create(Context r1) {
            return new CredentialManagerImpl(r1);
        }
    }

    static {
        Companion = Companion.$$INSTANCE;
    }

    @JvmStatic
    static CredentialManager create(Context r1) {
        return Companion.create(r1);
    }

    static /* synthetic */ Object createCredential$suspendImpl(CredentialManager r9, Context r10, CreateCredentialRequest r11, Continuation<? super CreateCredentialResponse> r12) {
        CancellableContinuationImpl r0 = new CancellableContinuationImpl(IntrinsicsKt.intercepted(r12), 1);
        r0.initCancellability();
        CancellationSignal r6 = new CancellationSignal();
        r0.invokeOnCancellation(new CredentialManager$createCredential$2$1(r6));
        CredentialManager$createCredential$2$callback$1 r8 = new CredentialManager$createCredential$2$callback$1(r0);
        r9.createCredentialAsync(r10, r11, r6, new CredentialManager$$ExternalSyntheticLambda0(), r8);
        Object r92 = r0.getResult();
        if (r92 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) goto L5;
        DebugProbesKt.probeCoroutineSuspended(r12);
    L5:
        return r92;
    }

    default Object createCredential(Context r1, CreateCredentialRequest r2, Continuation<? super CreateCredentialResponse> r3) {
        return createCredential$suspendImpl(this, r1, r2, r3);
    }

    void createCredentialAsync(Context r1, CreateCredentialRequest r2, CancellationSignal r3, Executor r4, CredentialManagerCallback<CreateCredentialResponse, CreateCredentialException> r5);

    PendingIntent createSettingsPendingIntent();

    void getCredentialAsync(Context r1, GetCredentialRequest r2, CancellationSignal r3, Executor r4, CredentialManagerCallback<GetCredentialResponse, GetCredentialException> r5);
}
