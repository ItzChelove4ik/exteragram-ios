package org.telegram.messenger.support.customtabs;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;

/* loaded from: classes3.dex */
public interface ICustomTabsCallback extends IInterface {

    public static abstract class Stub extends Binder implements ICustomTabsCallback {
        public Stub() {
            attachInterface(this, "android.support.customtabs.ICustomTabsCallback");
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r5, Parcel r6, Parcel r7, int r8) {
            Bundle r1 = null;
            if (r5 != 2) goto L5;
            r6.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            int r52 = r6.readInt();
            if (r6.readInt() == 0) goto L34;
            r1 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L34:
            onNavigationEvent(r52, r1);
            r7.writeNoException();
            return true;
        L5:
            if (r5 != 3) goto L7;
            r6.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            String r53 = r6.readString();
            if (r6.readInt() == 0) goto L29;
            r1 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L29:
            extraCallback(r53, r1);
            r7.writeNoException();
            return true;
        L7:
            if (r5 != 4) goto L9;
            r6.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            if (r6.readInt() == 0) goto L24;
            r1 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L24:
            onMessageChannelReady(r1);
            r7.writeNoException();
            return true;
        L9:
            if (r5 != 5) goto L11;
            r6.enforceInterface("android.support.customtabs.ICustomTabsCallback");
            String r54 = r6.readString();
            if (r6.readInt() == 0) goto L19;
            r1 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L19:
            onPostMessage(r54, r1);
            r7.writeNoException();
            return true;
        L11:
            if (r5 != 1598968902) goto L13;
            r7.writeString("android.support.customtabs.ICustomTabsCallback");
            return true;
        L13:
            return super.onTransact(r5, r6, r7, r8);
        }
    }

    void extraCallback(String r1, Bundle r2);

    void onMessageChannelReady(Bundle r1);

    void onNavigationEvent(int r1, Bundle r2);

    void onPostMessage(String r1, Bundle r2);
}
