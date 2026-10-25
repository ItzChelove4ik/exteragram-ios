package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnClickListener;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class OnClickDelegateImpl implements OnClickDelegate {
    private final boolean mIsParkedOnly;
    private final IOnClickListener mListener;

    public static class OnClickListenerStub extends IOnClickListener.Stub {
        private final OnClickListener mOnClickListener;

        /* renamed from: $r8$lambda$XNCP4ktZ0-uqZhJZBLJ1aOuuP5k, reason: not valid java name */
        public static /* synthetic */ Object m643$r8$lambda$XNCP4ktZ0uqZhJZBLJ1aOuuP5k(OnClickListenerStub r0) {
            r0.mOnClickListener.onClick();
            return null;
        }

        public OnClickListenerStub(OnClickListener r1) {
            this.mOnClickListener = r1;
        }

        @Override // androidx.car.app.model.IOnClickListener
        public void onClick(IOnDoneCallback r2) {
            RemoteUtils.dispatchCallFromHost(r2, "onClick", new OnClickDelegateImpl$OnClickListenerStub$$ExternalSyntheticLambda0(this));
        }
    }

    private OnClickDelegateImpl() {
        this.mListener = null;
        this.mIsParkedOnly = false;
    }

    private OnClickDelegateImpl(OnClickListener r2, boolean r3) {
        this.mListener = new OnClickListenerStub(r2);
        this.mIsParkedOnly = r3;
    }

    @SuppressLint({"ExecutorRegistration"})
    public static OnClickDelegate create(OnClickListener r2) {
        return new OnClickDelegateImpl(r2, r2 instanceof ParkedOnlyOnClickListener);
    }

    @Override // androidx.car.app.model.OnClickDelegate
    public boolean isParkedOnly() {
        return this.mIsParkedOnly;
    }

    public void sendClick(OnDoneCallback r1) {
        IOnClickListener r0 = this.mListener;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onClick(RemoteUtils.createOnDoneCallbackStub(r1));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
