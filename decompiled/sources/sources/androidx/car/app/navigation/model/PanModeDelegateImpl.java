package androidx.car.app.navigation.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.navigation.model.IPanModeListener;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class PanModeDelegateImpl implements PanModeDelegate {
    private final IPanModeListener mStub;

    public static class PanModeListenerStub extends IPanModeListener.Stub {
        private final PanModeListener mListener;

        public static /* synthetic */ Object $r8$lambda$Cc5fbnjHwTbzO2_Zd3zE93Unv3c(PanModeListenerStub r0, boolean r1) {
            r0.getClass();
            throw null;
        }

        public PanModeListenerStub(PanModeListener r1) {
        }

        @Override // androidx.car.app.navigation.model.IPanModeListener
        public void onPanModeChanged(boolean r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onPanModeChanged", new PanModeDelegateImpl$PanModeListenerStub$$ExternalSyntheticLambda0(this, r2));
        }
    }

    private PanModeDelegateImpl() {
        this.mStub = null;
    }

    private PanModeDelegateImpl(PanModeListener r2) {
        this.mStub = new PanModeListenerStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static PanModeDelegate create(PanModeListener r1) {
        return new PanModeDelegateImpl(r1);
    }

    public void sendPanModeChanged(boolean r1, OnDoneCallback r2) {
        IPanModeListener r0 = this.mStub;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onPanModeChanged(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
