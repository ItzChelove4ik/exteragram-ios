package androidx.car.app.messaging.model;

import android.os.RemoteException;
import androidx.car.app.IOnDoneCallback;
import androidx.car.app.OnDoneCallback;
import androidx.car.app.messaging.model.IConversationCallback;
import androidx.car.app.utils.RemoteUtils;
import java.util.Objects;
import okhttp3.HttpUrl$$ExternalSyntheticBUOutline0;

/* loaded from: classes3.dex */
class ConversationCallbackDelegateImpl implements ConversationCallbackDelegate {
    private final IConversationCallback mConversationCallbackBinder;

    public static class ConversationCallbackStub extends IConversationCallback.Stub {
        private final ConversationCallback mConversationCallback;

        /* renamed from: $r8$lambda$N7BVLWGtNGN4ArPkcWjJcGYJ-aI, reason: not valid java name */
        public static /* synthetic */ Object m641$r8$lambda$N7BVLWGtNGN4ArPkcWjJcGYJaI(ConversationCallbackStub r0) {
            r0.mConversationCallback.onMarkAsRead();
            return null;
        }

        public static /* synthetic */ Object $r8$lambda$Tbb_9lhfLXFSp06RpTThSQeIzOY(ConversationCallbackStub r0, String r1) {
            r0.mConversationCallback.onTextReply(r1);
            return null;
        }

        public ConversationCallbackStub(ConversationCallback r1) {
            this.mConversationCallback = r1;
        }

        @Override // androidx.car.app.messaging.model.IConversationCallback
        public void onMarkAsRead(IOnDoneCallback r2) {
            RemoteUtils.dispatchCallFromHost(r2, "onMarkAsRead", new ConversationCallbackDelegateImpl$ConversationCallbackStub$$ExternalSyntheticLambda1(this));
        }

        @Override // androidx.car.app.messaging.model.IConversationCallback
        public void onTextReply(IOnDoneCallback r2, String r3) {
            RemoteUtils.dispatchCallFromHost(r2, "onReply", new ConversationCallbackDelegateImpl$ConversationCallbackStub$$ExternalSyntheticLambda0(this, r3));
        }
    }

    private ConversationCallbackDelegateImpl() {
        this.mConversationCallbackBinder = null;
    }

    public ConversationCallbackDelegateImpl(ConversationCallback r2) {
        this.mConversationCallbackBinder = new ConversationCallbackStub(r2);
    }

    public void sendMarkAsRead(OnDoneCallback r1) {
        IConversationCallback r0 = this.mConversationCallbackBinder;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onMarkAsRead(RemoteUtils.createOnDoneCallbackStub(r1));     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }

    public void sendTextReply(String r1, OnDoneCallback r2) {
        IConversationCallback r0 = this.mConversationCallbackBinder;     // Catch: RemoteException -> L4
        Objects.requireNonNull(r0);     // Catch: RemoteException -> L4
        r0.onTextReply(RemoteUtils.createOnDoneCallbackStub(r2), r1);     // Catch: RemoteException -> L4
        return;
    L4:
        e = move-exception;
        HttpUrl$$ExternalSyntheticBUOutline0.m(e);
    }
}
