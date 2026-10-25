package androidx.car.app.messaging.model;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.car.app.IOnDoneCallback;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IConversationCallback extends IInterface {
    public static final String DESCRIPTOR = null;

    public static class Default implements IConversationCallback {
        public Default() {
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // androidx.car.app.messaging.model.IConversationCallback
        public void onMarkAsRead(IOnDoneCallback r1) {
        }

        @Override // androidx.car.app.messaging.model.IConversationCallback
        public void onTextReply(IOnDoneCallback r1, String r2) {
        }
    }

    public static abstract class Stub extends Binder implements IConversationCallback {
        static final int TRANSACTION_onMarkAsRead = 2;
        static final int TRANSACTION_onTextReply = 3;

        public static class Proxy implements IConversationCallback {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            public String getInterfaceDescriptor() {
                return IConversationCallback.DESCRIPTOR;
            }

            @Override // androidx.car.app.messaging.model.IConversationCallback
            public void onMarkAsRead(IOnDoneCallback r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IConversationCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.car.app.messaging.model.IConversationCallback
            public void onTextReply(IOnDoneCallback r3, String r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IConversationCallback.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r3);     // Catch: Throwable -> L6
                r0.writeString(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IConversationCallback.DESCRIPTOR);
        }

        public static IConversationCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IConversationCallback.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IConversationCallback) == false) goto L12;
            return (IConversationCallback) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IConversationCallback.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L12;
            r6.writeString(r0);
            return true;
        L12:
            if (r4 != 2) goto L14;
            onMarkAsRead(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()));
        L19:
            return true;
        L14:
            if (r4 != 3) goto L16;
            onTextReply(IOnDoneCallback.Stub.asInterface(r5.readStrongBinder()), r5.readString());
            goto L19
        L16:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$car$app$messaging$model$IConversationCallback".replace(Typography.dollar, '.');
    }

    void onMarkAsRead(IOnDoneCallback r1);

    void onTextReply(IOnDoneCallback r1, String r2);
}
