package androidx.media3.session.legacy;

import android.app.PendingIntent;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.text.TextUtils;
import android.view.KeyEvent;
import androidx.media3.common.util.Assertions;
import androidx.media3.session.legacy.IMediaControllerCallback;
import androidx.media3.session.legacy.MediaSessionCompat;
import java.util.List;

/* loaded from: classes4.dex */
public interface IMediaSession extends IInterface {

    public static abstract class Stub extends Binder implements IMediaSession {

        public static class Proxy implements IMediaSession {
            public static IMediaSession defaultImpl;
            private IBinder remote;

            public Proxy(IBinder r1) {
                this.remote = r1;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.remote;
            }

            @Override // androidx.media3.session.legacy.IMediaSession
            public void registerCallbackListener(IMediaControllerCallback r5) {
                Parcel r0 = Parcel.obtain();
                Parcel r1 = Parcel.obtain();
                r0.writeInterfaceToken("android.support.v4.media.session.IMediaSession");     // Catch: Throwable -> L6
                if (r5 == null) goto L8;
                IBinder r2 = r5.asBinder();     // Catch: Throwable -> L6
            L9:
                r0.writeStrongBinder(r2);     // Catch: Throwable -> L6
                if (this.remote.transact(3, r0, r1, 0) == false) goto L12;
            L16:
                r1.readException();     // Catch: Throwable -> L6
            L14:
                r1.recycle();
                r0.recycle();
                return;
            L12:
                if (Stub.getDefaultImpl() == null) goto L16;
                ((IMediaSession) Assertions.checkNotNull(Stub.getDefaultImpl())).registerCallbackListener(r5);     // Catch: Throwable -> L6
                goto L14
            L8:
                r2 = null;
            L6:
                th = move-exception;
                r1.recycle();
                r0.recycle();
                throw th;
            }
        }

        public Stub() {
            attachInterface(this, "android.support.v4.media.session.IMediaSession");
        }

        public static IMediaSession asInterface(IBinder r2) {
            if (r2 != null) goto L5;
            return null;
        L5:
            IInterface r0 = r2.queryLocalInterface("android.support.v4.media.session.IMediaSession");
            if (r0 == null) goto L12;
            if ((r0 instanceof IMediaSession) == false) goto L12;
            return (IMediaSession) r0;
        L12:
            return new Proxy(r2);
        }

        public static IMediaSession getDefaultImpl() {
            return Proxy.defaultImpl;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        @Override // android.os.Binder
        public boolean onTransact(int r5, Parcel r6, Parcel r7, int r8) {
            if (r5 == 1598968902) goto L195;
            boolean r0 = false;
            MediaSessionCompat.ResultReceiverWrapper r3 = null;
            Bundle r32 = null;
            MediaDescriptionCompat r33 = null;
            MediaDescriptionCompat r34 = null;
            MediaDescriptionCompat r35 = null;
            Bundle r36 = null;
            Bundle r37 = null;
            Bundle r38 = null;
            Bundle r39 = null;
            RatingCompat r310 = null;
            Bundle r311 = null;
            Bundle r312 = null;
            Bundle r313 = null;
            KeyEvent r314 = null;
            switch(r5) {
                case 1: goto L186;
                case 2: goto L181;
                case 3: goto L179;
                case 4: goto L177;
                case 5: goto L175;
                case 6: goto L173;
                case 7: goto L171;
                case 8: goto L166;
                case 9: goto L164;
                case 10: goto L159;
                case 11: goto L157;
                case 12: goto L155;
                case 13: goto L153;
                case 14: goto L148;
                case 15: goto L143;
                case 16: goto L134;
                case 17: goto L132;
                case 18: goto L130;
                case 19: goto L128;
                case 20: goto L126;
                case 21: goto L124;
                case 22: goto L122;
                case 23: goto L120;
                case 24: goto L118;
                case 25: goto L113;
                case 26: goto L108;
                case 27: goto L103;
                case 28: goto L98;
                case 29: goto L96;
                case 30: goto L91;
                case 31: goto L86;
                case 32: goto L84;
                case 33: goto L82;
                case 34: goto L77;
                case 35: goto L72;
                case 36: goto L63;
                case 37: goto L61;
                case 38: goto L59;
                case 39: goto L57;
                case 40: goto L52;
                case 41: goto L47;
                case 42: goto L42;
                case 43: goto L37;
                case 44: goto L35;
                case 45: goto L33;
                case 46: goto L28;
                case 47: goto L26;
                case 48: goto L24;
                case 49: goto L22;
                case 50: goto L17;
                case 51: goto L8;
                default: goto L7;
            };
        L8:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L11;
            RatingCompat r52 = RatingCompat.CREATOR.createFromParcel(r6);
        L13:
            if (r6.readInt() == 0) goto L15;
            r32 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L15:
            rateWithExtras(r52, r32);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L11:
            r52 = null;
            goto L13
        L17:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle r4 = getSessionInfo();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r4 == null) goto L20;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r4.writeToParcel(r7, 1);
        L21:
            return true;
        L20:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L21
        L22:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            setPlaybackSpeed(r6.readFloat());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L24:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            setShuffleMode(r6.readInt());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L26:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            int r42 = getShuffleMode();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r42);
            return true;
        L28:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L31;
            r0 = true;
        L31:
            setCaptioningEnabled(r0);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L33:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            boolean r43 = isCaptioningEnabled();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r43 ? 1 : 0);
            return true;
        L35:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            removeQueueItemAt(r6.readInt());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L37:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L40;
            r33 = MediaDescriptionCompat.CREATOR.createFromParcel(r6);
        L40:
            removeQueueItem(r33);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L42:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L45;
            r34 = MediaDescriptionCompat.CREATOR.createFromParcel(r6);
        L45:
            addQueueItemAt(r34, r6.readInt());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L47:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L50;
            r35 = MediaDescriptionCompat.CREATOR.createFromParcel(r6);
        L50:
            addQueueItem(r35);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L52:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L55;
            r0 = true;
        L55:
            setShuffleModeEnabledRemoved(r0);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L57:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            setRepeatMode(r6.readInt());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L59:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            boolean r44 = isShuffleModeEnabledRemoved();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r44 ? 1 : 0);
            return true;
        L61:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            int r45 = getRepeatMode();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r45);
            return true;
        L63:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L66;
            Uri r53 = (Uri) Uri.CREATOR.createFromParcel(r6);
        L68:
            if (r6.readInt() == 0) goto L70;
            r36 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L70:
            prepareFromUri(r53, r36);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L66:
            r53 = null;
            goto L68
        L72:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r54 = r6.readString();
            if (r6.readInt() == 0) goto L75;
            r37 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L75:
            prepareFromSearch(r54, r37);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L77:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r55 = r6.readString();
            if (r6.readInt() == 0) goto L80;
            r38 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L80:
            prepareFromMediaId(r55, r38);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L82:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            prepare();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L84:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            int r46 = getRatingType();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r46);
            return true;
        L86:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            Bundle r47 = getExtras();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r47 == null) goto L89;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r47.writeToParcel(r7, 1);
        L90:
            return true;
        L89:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L90
        L91:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            CharSequence r48 = getQueueTitle();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r48 == null) goto L94;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            TextUtils.writeToParcel(r48, r7, 1);
        L95:
            return true;
        L94:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L95
        L96:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            List<MediaSessionCompat.QueueItem> r49 = getQueue();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeTypedList(r49);
            return true;
        L98:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            PlaybackStateCompat r410 = getPlaybackState();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r410 == null) goto L101;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r410.writeToParcel(r7, 1);
        L102:
            return true;
        L101:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L102
        L103:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            MediaMetadataCompat r411 = getMetadata();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r411 == null) goto L106;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r411.writeToParcel(r7, 1);
        L107:
            return true;
        L106:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L107
        L108:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r56 = r6.readString();
            if (r6.readInt() == 0) goto L111;
            r39 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L111:
            sendCustomAction(r56, r39);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L113:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L116;
            r310 = RatingCompat.CREATOR.createFromParcel(r6);
        L116:
            rate(r310);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L118:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            seekTo(r6.readLong());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L120:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            rewind();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L122:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            fastForward();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L124:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            previous();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L126:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            next();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L128:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            stop();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L130:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            pause();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L132:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            skipToQueueItem(r6.readLong());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L134:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L137;
            Uri r57 = (Uri) Uri.CREATOR.createFromParcel(r6);
        L139:
            if (r6.readInt() == 0) goto L141;
            r311 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L141:
            playFromUri(r57, r311);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L137:
            r57 = null;
            goto L139
        L143:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r58 = r6.readString();
            if (r6.readInt() == 0) goto L146;
            r312 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L146:
            playFromSearch(r58, r312);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L148:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r59 = r6.readString();
            if (r6.readInt() == 0) goto L151;
            r313 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L151:
            playFromMediaId(r59, r313);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L153:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            play();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L155:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            setVolumeTo(r6.readInt(), r6.readInt(), r6.readString());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L157:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            adjustVolume(r6.readInt(), r6.readInt(), r6.readString());
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L159:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            ParcelableVolumeInfo r412 = getVolumeAttributes();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r412 == null) goto L162;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r412.writeToParcel(r7, 1);
        L163:
            return true;
        L162:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L163
        L164:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            long r413 = getFlags();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeLong(r413);
            return true;
        L166:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            PendingIntent r414 = getLaunchPendingIntent();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            if (r414 == null) goto L169;
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(1);
            r414.writeToParcel(r7, 1);
        L170:
            return true;
        L169:
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(0);
            goto L170
        L171:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r415 = getTag();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeString(r415);
            return true;
        L173:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r416 = getPackageName();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeString(r416);
            return true;
        L175:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            boolean r417 = isTransportControlEnabled();
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r417 ? 1 : 0);
            return true;
        L177:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            unregisterCallbackListener(IMediaControllerCallback.Stub.asInterface(r6.readStrongBinder()));
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L179:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            registerCallbackListener(IMediaControllerCallback.Stub.asInterface(r6.readStrongBinder()));
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L181:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            if (r6.readInt() == 0) goto L184;
            r314 = (KeyEvent) KeyEvent.CREATOR.createFromParcel(r6);
        L184:
            boolean r418 = sendMediaButton(r314);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            ((Parcel) Assertions.checkNotNull(r7)).writeInt(r418 ? 1 : 0);
            return true;
        L186:
            r6.enforceInterface("android.support.v4.media.session.IMediaSession");
            String r510 = r6.readString();
            if (r6.readInt() == 0) goto L189;
            Bundle r82 = (Bundle) Bundle.CREATOR.createFromParcel(r6);
        L191:
            if (r6.readInt() == 0) goto L193;
            r3 = MediaSessionCompat.ResultReceiverWrapper.CREATOR.createFromParcel(r6);
        L193:
            sendCommand(r510, r82, r3);
            ((Parcel) Assertions.checkNotNull(r7)).writeNoException();
            return true;
        L189:
            r82 = null;
            goto L191
        L7:
            return super.onTransact(r5, r6, r7, r8);
        L195:
            ((Parcel) Assertions.checkNotNull(r7)).writeString("android.support.v4.media.session.IMediaSession");
            return true;
        }
    }

    void addQueueItem(MediaDescriptionCompat r1);

    void addQueueItemAt(MediaDescriptionCompat r1, int r2);

    void adjustVolume(int r1, int r2, String r3);

    void fastForward();

    Bundle getExtras();

    long getFlags();

    PendingIntent getLaunchPendingIntent();

    MediaMetadataCompat getMetadata();

    String getPackageName();

    PlaybackStateCompat getPlaybackState();

    List<MediaSessionCompat.QueueItem> getQueue();

    CharSequence getQueueTitle();

    int getRatingType();

    int getRepeatMode();

    Bundle getSessionInfo();

    int getShuffleMode();

    String getTag();

    ParcelableVolumeInfo getVolumeAttributes();

    boolean isCaptioningEnabled();

    boolean isShuffleModeEnabledRemoved();

    boolean isTransportControlEnabled();

    void next();

    void pause();

    void play();

    void playFromMediaId(String r1, Bundle r2);

    void playFromSearch(String r1, Bundle r2);

    void playFromUri(Uri r1, Bundle r2);

    void prepare();

    void prepareFromMediaId(String r1, Bundle r2);

    void prepareFromSearch(String r1, Bundle r2);

    void prepareFromUri(Uri r1, Bundle r2);

    void previous();

    void rate(RatingCompat r1);

    void rateWithExtras(RatingCompat r1, Bundle r2);

    void registerCallbackListener(IMediaControllerCallback r1);

    void removeQueueItem(MediaDescriptionCompat r1);

    void removeQueueItemAt(int r1);

    void rewind();

    void seekTo(long r1);

    void sendCommand(String r1, Bundle r2, MediaSessionCompat.ResultReceiverWrapper r3);

    void sendCustomAction(String r1, Bundle r2);

    boolean sendMediaButton(KeyEvent r1);

    void setCaptioningEnabled(boolean r1);

    void setPlaybackSpeed(float r1);

    void setRepeatMode(int r1);

    void setShuffleMode(int r1);

    void setShuffleModeEnabledRemoved(boolean r1);

    void setVolumeTo(int r1, int r2, String r3);

    void skipToQueueItem(long r1);

    void stop();

    void unregisterCallbackListener(IMediaControllerCallback r1);
}
