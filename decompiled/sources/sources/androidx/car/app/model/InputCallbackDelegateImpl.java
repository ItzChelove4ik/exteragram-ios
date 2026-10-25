package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IInputCallback;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class InputCallbackDelegateImpl implements InputCallbackDelegate {
    private final IInputCallback mCallback;

    public static class OnInputCallbackStub extends IInputCallback.Stub {
        private final InputCallback mCallback;

        public static /* synthetic */ Object $r8$lambda$N58f7Tz5aHCUixbEqJYdd2mIpbI(OnInputCallbackStub r0, String r1) {
            r0.getClass();
            throw null;
        }

        public static /* synthetic */ Object $r8$lambda$g_2c3vvJ8FsmK7acrkLVEWJpcwE(OnInputCallbackStub r0, String r1) {
            r0.getClass();
            throw null;
        }

        public OnInputCallbackStub(InputCallback r1) {
        }

        @Override // androidx.car.app.model.IInputCallback
        public void onInputSubmitted(String r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onInputSubmitted", new InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda1(this, r2));
        }

        @Override // androidx.car.app.model.IInputCallback
        public void onInputTextChanged(String r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onInputTextChanged", new InputCallbackDelegateImpl$OnInputCallbackStub$$ExternalSyntheticLambda0(this, r2));
        }
    }

    private InputCallbackDelegateImpl() {
        this.mCallback = null;
    }

    private InputCallbackDelegateImpl(InputCallback r2) {
        this.mCallback = new OnInputCallbackStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static InputCallbackDelegate create(InputCallback r1) {
        Objects.requireNonNull(r1);
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r1);
        return new InputCallbackDelegateImpl(null);
    }

    public void sendInputSubmitted(String r1, OnDoneCallback r2) {
        IInputCallback r0 = this.mCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onInputSubmitted(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void sendInputTextChanged(String r1, OnDoneCallback r2) {
        IInputCallback r0 = this.mCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onInputTextChanged(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
