package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnSelectedListener;
import androidx.car.app.model.ItemList;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class OnSelectedDelegateImpl implements OnSelectedDelegate {
    private final IOnSelectedListener mStub;

    public static class OnSelectedListenerStub extends IOnSelectedListener.Stub {
        private final ItemList.OnSelectedListener mListener;

        public static /* synthetic */ Object $r8$lambda$RU_djcpI4ucKClcsLCOI3qeLGLM(OnSelectedListenerStub r0, int r1) {
            r0.getClass();
            throw null;
        }

        public OnSelectedListenerStub(ItemList.OnSelectedListener r1) {
        }

        @Override // androidx.car.app.model.IOnSelectedListener
        public void onSelected(int r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onSelectedListener", new OnSelectedDelegateImpl$OnSelectedListenerStub$$ExternalSyntheticLambda0(this, r2));
        }
    }

    private OnSelectedDelegateImpl() {
        this.mStub = null;
    }

    private OnSelectedDelegateImpl(ItemList.OnSelectedListener r2) {
        this.mStub = new OnSelectedListenerStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static OnSelectedDelegate create(ItemList.OnSelectedListener r1) {
        return new OnSelectedDelegateImpl(r1);
    }

    public void sendSelected(int r1, OnDoneCallback r2) {
        IOnSelectedListener r0 = this.mStub;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onSelected(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
