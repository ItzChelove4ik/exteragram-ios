package org.telegram.messenger.support.customtabs;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes.dex */
public interface ICustomTabsService extends IInterface {

    public static abstract class Stub extends Binder implements ICustomTabsService {

        public static class Proxy implements ICustomTabsService {
            private IBinder mRemote;

            public Proxy(IBinder r1) {
                this.mRemote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // org.telegram.messenger.support.customtabs.ICustomTabsService
            public boolean newSession(ICustomTabsCallback r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");     // Catch: Throwable -> L6
                if (r4 == null) goto L8;
                IBinder r42 = r4.asBinder();     // Catch: Throwable -> L6
            L9:
                r0.writeStrongBinder(r42);     // Catch: Throwable -> L6
                boolean r2 = false;
                this.mRemote.transact(3, r0, r1, 0);     // Catch: Throwable -> L6
                r1.readException();     // Catch: Throwable -> L6
                if (r1.readInt() == 0) goto L12;
                r2 = true;
            L12:
                r1.recycle();
                r0.recycle();
                return r2;
            L8:
                r42 = null;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }

            @Override // org.telegram.messenger.support.customtabs.ICustomTabsService
            public boolean warmup(long r4) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.customtabs.ICustomTabsService");     // Catch: Throwable -> L8
                r0.writeLong(r4);     // Catch: Throwable -> L8
                boolean r5 = false;
                this.mRemote.transact(2, r0, r1, 0);     // Catch: Throwable -> L8
                r1.readException();     // Catch: Throwable -> L8
                if (r1.readInt() == 0) goto L6;
                r5 = true;
            L6:
                r1.recycle();
                r0.recycle();
                return r5;
            L8:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public static ICustomTabsService asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("android.support.customtabs.ICustomTabsService");
            if (r0 == null) goto L12;
            if ((r0 instanceof ICustomTabsService) == false) goto L12;
            return (ICustomTabsService) r0;
        L12:
            return new Proxy(r2);
        }
    }

    boolean newSession(ICustomTabsCallback r1);

    boolean warmup(long r1);
}
