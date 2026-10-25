package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnCheckedChangeListener;
import androidx.car.app.model.Toggle;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class OnCheckedChangeDelegateImpl implements OnCheckedChangeDelegate {
    private final IOnCheckedChangeListener mStub;

    public static class OnCheckedChangeListenerStub extends IOnCheckedChangeListener.Stub {
        private final Toggle.OnCheckedChangeListener mListener;

        public static /* synthetic */ Object $r8$lambda$48webNydW9KdPgFQqULUrGYAipw(OnCheckedChangeListenerStub r0, boolean r1) {
            r0.getClass();
            throw null;
        }

        public OnCheckedChangeListenerStub(Toggle.OnCheckedChangeListener r1) {
        }

        @Override // androidx.car.app.model.IOnCheckedChangeListener
        public void onCheckedChange(boolean r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onCheckedChange", new OnCheckedChangeDelegateImpl$OnCheckedChangeListenerStub$$ExternalSyntheticLambda0(this, r2));
        }
    }

    private OnCheckedChangeDelegateImpl() {
        this.mStub = null;
    }

    private OnCheckedChangeDelegateImpl(Toggle.OnCheckedChangeListener r2) {
        this.mStub = new OnCheckedChangeListenerStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static OnCheckedChangeDelegate create(Toggle.OnCheckedChangeListener r1) {
        return new OnCheckedChangeDelegateImpl(r1);
    }

    public void sendCheckedChange(boolean r1, OnDoneCallback r2) {
        IOnCheckedChangeListener r0 = this.mStub;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onCheckedChange(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
