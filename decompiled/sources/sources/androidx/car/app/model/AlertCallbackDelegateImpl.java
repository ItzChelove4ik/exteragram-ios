package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IAlertCallback;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class AlertCallbackDelegateImpl implements AlertCallbackDelegate {
    private final IAlertCallback mCallback;

    public static class AlertCallbackStub extends IAlertCallback.Stub {
        private final AlertCallback mCallback;

        /* renamed from: $r8$lambda$KnQ-FnNRyUyR5iddrT6-wu_j5-U, reason: not valid java name */
        public static /* synthetic */ Object m642$r8$lambda$KnQFnNRyUyR5iddrT6wu_j5U(AlertCallbackStub r0, int r1) {
            r0.getClass();
            throw null;
        }

        public static /* synthetic */ Object $r8$lambda$TaRFdnKZJVRXV72MsEkaaZoD3dg(AlertCallbackStub r0) {
            r0.getClass();
            throw null;
        }

        public AlertCallbackStub(AlertCallback r1) {
        }

        @Override // androidx.car.app.model.IAlertCallback
        public void onAlertCancelled(int r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onCancel", new AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda1(this, r2));
        }

        @Override // androidx.car.app.model.IAlertCallback
        public void onAlertDismissed(IOnDoneCallback r2) {
            RemoteUtils.dispatchCallFromHost(r2, "onDismiss", new AlertCallbackDelegateImpl$AlertCallbackStub$$ExternalSyntheticLambda0(this));
        }
    }

    private AlertCallbackDelegateImpl() {
        this.mCallback = null;
    }

    private AlertCallbackDelegateImpl(AlertCallback r2) {
        this.mCallback = new AlertCallbackStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static AlertCallbackDelegate create(AlertCallback r1) {
        return new AlertCallbackDelegateImpl(r1);
    }

    public void sendCancel(int r1, OnDoneCallback r2) {
        IAlertCallback r0 = this.mCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onAlertCancelled(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void sendDismiss(OnDoneCallback r1) {
        IAlertCallback r0 = this.mCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onAlertDismissed(RemoteUtils.createOnDoneCallbackStub(r1));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
