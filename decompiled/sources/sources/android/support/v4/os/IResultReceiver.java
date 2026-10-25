package android.support.v4.os;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import kotlin.text.Typography;

/* loaded from: classes3.dex */
public interface IResultReceiver extends IInterface {
    public static final String DESCRIPTOR = null;

    public static abstract class Stub extends Binder implements IResultReceiver {

        public static class Proxy implements IResultReceiver {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }
        }

        public Stub() {
            attachInterface(this, IResultReceiver.DESCRIPTOR);
        }

        public static IResultReceiver asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IResultReceiver.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IResultReceiver) == false) goto L12;
            return (IResultReceiver) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IResultReceiver.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            if (r4 != 1) goto L13;
            send(r5.readInt(), (Bundle) r5.readTypedObject(Bundle.CREATOR));
            return true;
        L13:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "android$support$v4$os$IResultReceiver".replace(Typography.dollar, '.');
    }

    void send(int r1, Bundle r2);
}
