package androidx.car.app.model;

import android.annotation.SuppressLint;
import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.model.IOnItemVisibilityChangedListener;
import androidx.car.app.model.ItemList;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
public class OnItemVisibilityChangedDelegateImpl implements OnItemVisibilityChangedDelegate {
    private final IOnItemVisibilityChangedListener mStub;

    public static class OnItemVisibilityChangedListenerStub extends IOnItemVisibilityChangedListener.Stub {
        private final ItemList.OnItemVisibilityChangedListener mListener;

        /* renamed from: $r8$lambda$DQ2dP-NndKRSDvlhWIUdtzTOBak, reason: not valid java name */
        public static /* synthetic */ Object m645$r8$lambda$DQ2dPNndKRSDvlhWIUdtzTOBak(OnItemVisibilityChangedListenerStub r0, int r1, int r2) {
            r0.getClass();
            throw null;
        }

        public OnItemVisibilityChangedListenerStub(ItemList.OnItemVisibilityChangedListener r1) {
        }

        @Override // androidx.car.app.model.IOnItemVisibilityChangedListener
        public void onItemVisibilityChanged(int r2, int r3, IOnDoneCallback r4) {
            RemoteUtils.dispatchCallFromHost(r4, "onItemVisibilityChanged", new OnItemVisibilityChangedDelegateImpl$OnItemVisibilityChangedListenerStub$$ExternalSyntheticLambda0(this, r2, r3));
        }
    }

    private OnItemVisibilityChangedDelegateImpl() {
        this.mStub = null;
    }

    private OnItemVisibilityChangedDelegateImpl(ItemList.OnItemVisibilityChangedListener r2) {
        this.mStub = new OnItemVisibilityChangedListenerStub(r2);
    }

    @SuppressLint({"ExecutorRegistration"})
    public static OnItemVisibilityChangedDelegate create(ItemList.OnItemVisibilityChangedListener r1) {
        return new OnItemVisibilityChangedDelegateImpl(r1);
    }

    public void sendItemVisibilityChanged(int r1, int r2, OnDoneCallback r3) {
        IOnItemVisibilityChangedListener r0 = this.mStub;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onItemVisibilityChanged(r1, r2, RemoteUtils.createOnDoneCallbackStub(r3));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
