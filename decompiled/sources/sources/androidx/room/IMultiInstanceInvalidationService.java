package androidx.room;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import androidx.room.IMultiInstanceInvalidationCallback;
import kotlin.text.Typography;

/* loaded from: classes4.dex */
public interface IMultiInstanceInvalidationService extends IInterface {
    public static final String DESCRIPTOR = null;

    public static abstract class Stub extends Binder implements IMultiInstanceInvalidationService {

        public static class Proxy implements IMultiInstanceInvalidationService {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // androidx.room.IMultiInstanceInvalidationService
            public void broadcastInvalidation(int r3, String[] r4) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken(IMultiInstanceInvalidationService.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeInt(r3);     // Catch: Throwable -> L6
                r0.writeStringArray(r4);     // Catch: Throwable -> L6
                this.mRemote.transact(3, r0, null, 1);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.room.IMultiInstanceInvalidationService
            public int registerCallback(IMultiInstanceInvalidationCallback r4, String r5) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IMultiInstanceInvalidationService.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                r0.writeString(r5);     // Catch: Throwable -> L6
                this.mRemote.transact(1, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                int r3 = r1.readInt();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return r3;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            @Override // androidx.room.IMultiInstanceInvalidationService
            public void unregisterCallback(IMultiInstanceInvalidationCallback r4, int r5) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken(IMultiInstanceInvalidationService.DESCRIPTOR);     // Catch: Throwable -> L6
                r0.writeStrongInterface(r4);     // Catch: Throwable -> L6
                r0.writeInt(r5);     // Catch: Throwable -> L6
                this.mRemote.transact(2, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                r1.recycle();
                r0.recycle();
                return;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, IMultiInstanceInvalidationService.DESCRIPTOR);
        }

        public static IMultiInstanceInvalidationService asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface(IMultiInstanceInvalidationService.DESCRIPTOR);
            if (r0 == null) goto L12;
            if ((r0 instanceof IMultiInstanceInvalidationService) == false) goto L12;
            return (IMultiInstanceInvalidationService) r0;
        L12:
            return new Proxy(r2);
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r4, Parcel r5, Parcel r6, int r7) {
            String r0 = IMultiInstanceInvalidationService.DESCRIPTOR;
            if (r4 < 1) goto L8;
            if (r4 > 16777215) goto L8;
            r5.enforceInterface(r0);
        L8:
            if (r4 != 1598968902) goto L11;
            r6.writeString(r0);
            return true;
        L11:
            if (r4 != 1) goto L13;
            int r3 = registerCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(r5.readStrongBinder()), r5.readString());
            r6.writeNoException();
            r6.writeInt(r3);
        L21:
            return true;
        L13:
            if (r4 != 2) goto L15;
            unregisterCallback(IMultiInstanceInvalidationCallback.Stub.asInterface(r5.readStrongBinder()), r5.readInt());
            r6.writeNoException();
            goto L21
        L15:
            if (r4 != 3) goto L17;
            broadcastInvalidation(r5.readInt(), r5.createStringArray());
            goto L21
        L17:
            return super.onTransact(r4, r5, r6, r7);
        }
    }

    static {
        DESCRIPTOR = "androidx$room$IMultiInstanceInvalidationService".replace(Typography.dollar, '.');
    }

    void broadcastInvalidation(int r1, String[] r2);

    int registerCallback(IMultiInstanceInvalidationCallback r1, String r2);

    void unregisterCallback(IMultiInstanceInvalidationCallback r1, int r2);
}
