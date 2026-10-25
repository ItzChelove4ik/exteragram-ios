package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnContentRefreshListener;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class OnContentRefreshDelegateImpl implements OnContentRefreshDelegate {
    private final IOnContentRefreshListener mListener;

    public static class OnContentRefreshListenerStub extends IOnContentRefreshListener.Stub {
        private final OnContentRefreshListener mOnContentRefreshListener;

        /* renamed from: $r8$lambda$HDd2U4FE66IM1uZFtMhRK-dnpR8, reason: not valid java name */
        public static /* synthetic */ Object m644$r8$lambda$HDd2U4FE66IM1uZFtMhRKdnpR8(OnContentRefreshListenerStub r0) {
            r0.getClass();
            throw null;
        }

        public OnContentRefreshListenerStub(OnContentRefreshListener r1) {
        }

        @Override // androidx.car.app.model.IOnContentRefreshListener
        public void onContentRefreshRequested(IOnDoneCallback r2) {
            RemoteUtils.dispatchCallFromHost(r2, "onClick", new OnContentRefreshDelegateImpl$OnContentRefreshListenerStub$$ExternalSyntheticLambda0(this));
        }
    }

    private OnContentRefreshDelegateImpl() {
        this.mListener = null;
    }

    private OnContentRefreshDelegateImpl(OnContentRefreshListener r2) {
        this.mListener = new OnContentRefreshListenerStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static OnContentRefreshDelegate create(OnContentRefreshListener r1) {
        return new OnContentRefreshDelegateImpl(r1);
    }

    public void sendContentRefreshRequested(OnDoneCallback r1) {
        IOnContentRefreshListener r0 = this.mListener;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onContentRefreshRequested(RemoteUtils.createOnDoneCallbackStub(r1));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
