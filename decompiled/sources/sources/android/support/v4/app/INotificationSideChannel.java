package android.support.v4.app;

import android.app.Notification;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface INotificationSideChannel extends IInterface {
    public static final String DESCRIPTOR = null;

    public static abstract class Stub extends Binder implements INotificationSideChannel {

        public static class Proxy implements INotificationSideChannel {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.support.v4.app.INotificationSideChannel
            public void notify(String r3, int r4, String r5, Notification r6) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(INotificationSideChannel.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeString(r3);     // Catch: Throwable -> L6
                r0.writeInt(r4);     // Catch: Throwable -> L6
                r0.writeString(r5);     // Catch: Throwable -> L6
                r0.writeTypedObject(r6, 0);     // Catch: Throwable -> L6
                this.mRemote.transact(1, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public static INotificationSideChannel asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(INotificationSideChannel.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof INotificationSideChannel) == false) goto L12;
            return (INotificationSideChannel) r0;
        L12:
            return new Proxy(r2);
        }
    }

    static {
        DESCRIPTOR = "android$support$v4$app$INotificationSideChannel".replace(Typography.dollar, '.');
    }

    void notify(String r1, int r2, String r3, Notification r4);
}
