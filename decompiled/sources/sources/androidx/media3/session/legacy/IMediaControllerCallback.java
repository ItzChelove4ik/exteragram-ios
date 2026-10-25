package androidx.media3.session.legacy;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.List;

/* loaded from: classes4.dex */
public interface IMediaControllerCallback extends IInterface {

    public static abstract class Stub extends Binder implements IMediaControllerCallback {

        public static class Proxy implements IMediaControllerCallback {
            public static IMediaControllerCallback defaultImpl;
            private IBinder remote;

            public Proxy(IBinder r1) {
                this.remote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.remote;
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onPlaybackStateChanged(PlaybackStateCompat r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L6
                if (r5 == null) goto L8;
                r0.writeInt(1);     // Catch: Throwable -> L6
                r5.writeToParcel(r0, 0);     // Catch: Throwable -> L6
            L10:
                if (this.remote.transact(3, r0, null, 1) == false) goto L12;
            L16:
                r0.recycle();
                return;
            L12:
                if (Stub.getDefaultImpl() == null) goto L16;
                ((IMediaControllerCallback) Assertions.checkNotNull(Stub.getDefaultImpl())).onPlaybackStateChanged(r5);     // Catch: Throwable -> L6
                r0.recycle();
                return;
            L8:
                r0.writeInt(0);     // Catch: Throwable -> L6
            L6:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onRepeatModeChanged(int r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L10
                r0.writeInt(r5);     // Catch: Throwable -> L10
                if (this.remote.transact(9, r0, null, 1) == false) goto L6;
            L12:
                r0.recycle();
                return;
            L6:
                if (Stub.getDefaultImpl() == null) goto L12;
                ((IMediaControllerCallback) Assertions.checkNotNull(Stub.getDefaultImpl())).onRepeatModeChanged(r5);     // Catch: Throwable -> L10
                r0.recycle();
                return;
            L10:
                th = move-exception;
                r0.recycle();
                throw th;
            }

            @Override // androidx.media3.session.legacy.IMediaControllerCallback
            public void onShuffleModeChanged(int r5) {
                Parcel r0 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaControllerCallback");     // Catch: Throwable -> L10
                r0.writeInt(r5);     // Catch: Throwable -> L10
                if (this.remote.transact(12, r0, null, 1) == false) goto L6;
            L12:
                r0.recycle();
                return;
            L6:
                if (Stub.getDefaultImpl() == null) goto L12;
                ((IMediaControllerCallback) Assertions.checkNotNull(Stub.getDefaultImpl())).onShuffleModeChanged(r5);     // Catch: Throwable -> L10
                r0.recycle();
                return;
            L10:
                th = move-exception;
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaControllerCallback");
        }

        public static IMediaControllerCallback asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r0 == null) goto L12;
            if ((r0 instanceof IMediaControllerCallback) == false) goto L12;
            return (IMediaControllerCallback) r0;
        L12:
            return new Proxy(r2);
        }

        public static IMediaControllerCallback getDefaultImpl() {
            return Proxy.defaultImpl;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r5, Parcel r6, Parcel r7, int r8) {
            if (r5 == 1598968902) goto L58;
            boolean r0 = false;
            Bundle r3 = null;
            ParcelableVolumeInfo r32 = null;
            Bundle r33 = null;
            CharSequence r34 = null;
            MediaMetadataCompat r35 = null;
            PlaybackStateCompat r36 = null;
            switch(r5) {
                case 1: goto L53;
                case 2: goto L51;
                case 3: goto L46;
                case 4: goto L41;
                case 5: goto L39;
                case 6: goto L34;
                case 7: goto L29;
                case 8: goto L24;
                case 9: goto L22;
                case 10: goto L17;
                case 11: goto L12;
                case 12: goto L10;
                case 13: goto L8;
                default: goto L7;
            };
        L8:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onSessionReady();
            return true;
        L10:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onShuffleModeChanged(r6.readInt());
            return true;
        L12:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L15;
            r0 = true;
        L15:
            onCaptioningEnabledChanged(r0);
            return true;
        L17:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L20;
            r0 = true;
        L20:
            onShuffleModeChangedRemoved(r0);
            return true;
        L22:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onRepeatModeChanged(r6.readInt());
            return true;
        L24:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L27;
            r32 = ParcelableVolumeInfo.CREATOR.createFromParcel(r6);
        L27:
            onVolumeInfoChanged(r32);
            return true;
        L29:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L32;
            r33 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L32:
            onExtrasChanged(r33);
            return true;
        L34:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L37;
            r34 = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r6);
        L37:
            onQueueTitleChanged(r34);
            return true;
        L39:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onQueueChanged(r6.createTypedArrayList(MediaSessionCompat.QueueItem.CREATOR));
            return true;
        L41:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L44;
            r35 = MediaMetadataCompat.CREATOR.createFromParcel(r6);
        L44:
            onMetadataChanged(r35);
            return true;
        L46:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            if (r6.readInt() == 0) goto L49;
            r36 = PlaybackStateCompat.CREATOR.createFromParcel(r6);
        L49:
            onPlaybackStateChanged(r36);
            return true;
        L51:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            onSessionDestroyed();
            return true;
        L53:
            r6.enforceInterface("android.support.v4.media.session.IMediaControllerCallback");
            String r52 = r6.readString();
            if (r6.readInt() == 0) goto L56;
            r3 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L56:
            onEvent(r52, r3);
            return true;
        L7:
            return super.onTransact(r5, r6, r7, r8);
        L58:
            ((Parcel) Assertions.checkNotNull(r7)).writeString("android.support.v4.media.session.IMediaControllerCallback");
            return true;
        }
    }

    void onCaptioningEnabledChanged(boolean r1);

    void onEvent(String r1, Bundle r2);

    void onExtrasChanged(Bundle r1);

    void onMetadataChanged(MediaMetadataCompat r1);

    void onPlaybackStateChanged(PlaybackStateCompat r1);

    void onQueueChanged(List<MediaSessionCompat.QueueItem> r1);

    void onQueueTitleChanged(CharSequence r1);

    void onRepeatModeChanged(int r1);

    void onSessionDestroyed();

    void onSessionReady();

    void onShuffleModeChanged(int r1);

    void onShuffleModeChangedRemoved(boolean r1);

    void onVolumeInfoChanged(ParcelableVolumeInfo r1);
}
