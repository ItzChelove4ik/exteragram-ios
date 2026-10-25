package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.ITabCallback;
import androidx.car.app.model.TabTemplate;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class TabCallbackDelegateImpl implements TabCallbackDelegate {
    private final ITabCallback mStubCallback;

    public static class TabCallbackStub extends ITabCallback.Stub {
        private final TabTemplate.TabCallback mCallback;

        /* renamed from: $r8$lambda$yKaF6NpaWih2D-zb-YbQfKX2X_A, reason: not valid java name */
        public static /* synthetic */ Object m646$r8$lambda$yKaF6NpaWih2DzbYbQfKX2X_A(TabCallbackStub r0, String r1) {
            r0.mCallback.onTabSelected(r1);
            return null;
        }

        public TabCallbackStub(TabTemplate.TabCallback r1) {
            this.mCallback = r1;
        }

        @Override // androidx.car.app.model.ITabCallback
        public void onTabSelected(String r2, IOnDoneCallback r3) {
            RemoteUtils.dispatchCallFromHost(r3, "onTabSelected", new TabCallbackDelegateImpl$TabCallbackStub$$ExternalSyntheticLambda0(this, r2));
        }
    }

    private TabCallbackDelegateImpl() {
        this.mStubCallback = null;
    }

    private TabCallbackDelegateImpl(TabTemplate.TabCallback r2) {
        this.mStubCallback = new TabCallbackStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static TabCallbackDelegate create(TabTemplate.TabCallback r1) {
        return new TabCallbackDelegateImpl(r1);
    }

    public void sendTabSelected(String r1, OnDoneCallback r2) {
        ITabCallback r0 = this.mStubCallback;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onTabSelected(r1, RemoteUtils.createOnDoneCallbackStub(r2));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
