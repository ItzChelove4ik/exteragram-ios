package androidx.biometric;

import android.os.CancellationSignal;
import android.util.Log;

/* loaded from: classes3.dex */
class CancellationSignalProvider {
    private CancellationSignal mBiometricCancellationSignal;
    private androidx.core.os.CancellationSignal mFingerprintCancellationSignal;
    private final Injector mInjector;

    /* renamed from: androidx.biometric.CancellationSignalProvider$1, reason: invalid class name */
    public class AnonymousClass1 implements Injector {
        final /* synthetic */ CancellationSignalProvider this$0;

        public AnonymousClass1(CancellationSignalProvider r1) {
            this.this$0 = r1;
        }

        @Override // androidx.biometric.CancellationSignalProvider.Injector
        public CancellationSignal getBiometricCancellationSignal() {
            return Api16Impl.create();
        }

        @Override // androidx.biometric.CancellationSignalProvider.Injector
        public androidx.core.os.CancellationSignal getFingerprintCancellationSignal() {
            return new androidx.core.os.CancellationSignal();
        }
    }

    public static class Api16Impl {
        public static void cancel(CancellationSignal r0) {
            r0.cancel();
        }

        public static CancellationSignal create() {
            return new CancellationSignal();
        }
    }

    public interface Injector {
        CancellationSignal getBiometricCancellationSignal();

        androidx.core.os.CancellationSignal getFingerprintCancellationSignal();
    }

    public CancellationSignalProvider() {
        this.mInjector = new AnonymousClass1(this);
    }

    public void cancel() {
        CancellationSignal r0 = this.mBiometricCancellationSignal;
        if (r0 != null) goto L19;
    L9:
        androidx.core.os.CancellationSignal r02 = this.mFingerprintCancellationSignal;
        if (r02 == null) goto L21;
        r02.cancel();     // Catch: NullPointerException -> L13
    L15:
        this.mFingerprintCancellationSignal = null;
        return;
    L13:
        e = move-exception;
        Log.e("CancelSignalProvider", "Got NPE while canceling fingerprint authentication.", e);
        goto L15
    L21:
        return;
    L19:
        Api16Impl.cancel(r0);     // Catch: NullPointerException -> L6
    L8:
        this.mBiometricCancellationSignal = null;
    L6:
        e = move-exception;
        Log.e("CancelSignalProvider", "Got NPE while canceling biometric authentication.", e);
        goto L8
    }

    public CancellationSignal getBiometricCancellationSignal() {
        if (this.mBiometricCancellationSignal != null) goto L6;
        this.mBiometricCancellationSignal = this.mInjector.getBiometricCancellationSignal();
    L6:
        return this.mBiometricCancellationSignal;
    }

    public androidx.core.os.CancellationSignal getFingerprintCancellationSignal() {
        if (this.mFingerprintCancellationSignal != null) goto L6;
        this.mFingerprintCancellationSignal = this.mInjector.getFingerprintCancellationSignal();
    L6:
        return this.mFingerprintCancellationSignal;
    }
}
