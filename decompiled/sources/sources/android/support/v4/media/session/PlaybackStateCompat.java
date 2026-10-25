package android.support.v4.media.session;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.SystemClock;
import android.text.TextUtils;
import c.f$$ExternalSyntheticBUOutline1;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes3.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = null;
    final long mActions;
    final long mActiveItemId;
    final long mBufferedPosition;
    List<CustomAction> mCustomActions;
    final int mErrorCode;
    final CharSequence mErrorMessage;
    final Bundle mExtras;
    final long mPosition;
    final float mSpeed;
    final int mState;
    private PlaybackState mStateFwk;
    final long mUpdateTime;

    /* renamed from: android.support.v4.media.session.PlaybackStateCompat$1, reason: invalid class name */
    public class AnonymousClass1 implements Parcelable.Creator<PlaybackStateCompat> {
        public AnonymousClass1() {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackStateCompat createFromParcel(Parcel r1) {
            return new PlaybackStateCompat(r1);
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ PlaybackStateCompat createFromParcel(Parcel r1) {
            return createFromParcel(r1);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // android.os.Parcelable.Creator
        public PlaybackStateCompat[] newArray(int r1) {
            return new PlaybackStateCompat[r1];
        }

        @Override // android.os.Parcelable.Creator
        public /* bridge */ /* synthetic */ PlaybackStateCompat[] newArray(int r1) {
            return newArray(r1);
        }
    }

    public static class Api21Impl {
        public static void addCustomAction(PlaybackState.Builder r0, PlaybackState.CustomAction r1) {
            r0.addCustomAction(r1);
        }

        public static PlaybackState.CustomAction build(PlaybackState.CustomAction.Builder r0) {
            return r0.build();
        }

        public static PlaybackState build(PlaybackState.Builder r0) {
            return r0.build();
        }

        public static PlaybackState.Builder createBuilder() {
            return new PlaybackState.Builder();
        }

        public static PlaybackState.CustomAction.Builder createCustomActionBuilder(String r1, CharSequence r2, int r3) {
            return new PlaybackState.CustomAction.Builder(r1, r2, r3);
        }

        public static String getAction(PlaybackState.CustomAction r0) {
            return r0.getAction();
        }

        public static long getActions(PlaybackState r2) {
            return r2.getActions();
        }

        public static long getActiveQueueItemId(PlaybackState r2) {
            return r2.getActiveQueueItemId();
        }

        public static long getBufferedPosition(PlaybackState r2) {
            return r2.getBufferedPosition();
        }

        public static List<PlaybackState.CustomAction> getCustomActions(PlaybackState r0) {
            return r0.getCustomActions();
        }

        public static CharSequence getErrorMessage(PlaybackState r0) {
            return r0.getErrorMessage();
        }

        public static Bundle getExtras(PlaybackState.CustomAction r0) {
            return r0.getExtras();
        }

        public static int getIcon(PlaybackState.CustomAction r0) {
            return r0.getIcon();
        }

        public static long getLastPositionUpdateTime(PlaybackState r2) {
            return r2.getLastPositionUpdateTime();
        }

        public static CharSequence getName(PlaybackState.CustomAction r0) {
            return r0.getName();
        }

        public static float getPlaybackSpeed(PlaybackState r0) {
            return r0.getPlaybackSpeed();
        }

        public static long getPosition(PlaybackState r2) {
            return r2.getPosition();
        }

        public static int getState(PlaybackState r0) {
            return r0.getState();
        }

        public static void setActions(PlaybackState.Builder r0, long r1) {
            r0.setActions(r1);
        }

        public static void setActiveQueueItemId(PlaybackState.Builder r0, long r1) {
            r0.setActiveQueueItemId(r1);
        }

        public static void setBufferedPosition(PlaybackState.Builder r0, long r1) {
            r0.setBufferedPosition(r1);
        }

        public static void setErrorMessage(PlaybackState.Builder r0, CharSequence r1) {
            r0.setErrorMessage(r1);
        }

        public static void setExtras(PlaybackState.CustomAction.Builder r0, Bundle r1) {
            r0.setExtras(r1);
        }

        public static void setState(PlaybackState.Builder r0, int r1, long r2, float r4, long r5) {
            r0.setState(r1, r2, r4, r5);
        }
    }

    public static class Api22Impl {
        public static Bundle getExtras(PlaybackState r0) {
            return r0.getExtras();
        }

        public static void setExtras(PlaybackState.Builder r0, Bundle r1) {
            r0.setExtras(r1);
        }
    }

    public static final class Builder {
        private long mActions;
        private long mActiveItemId;
        private long mBufferedPosition;
        private final List<CustomAction> mCustomActions;
        private int mErrorCode;
        private CharSequence mErrorMessage;
        private Bundle mExtras;
        private long mPosition;
        private float mRate;
        private int mState;
        private long mUpdateTime;

        public Builder() {
            this.mCustomActions = new ArrayList();
            this.mActiveItemId = -1;
        }

        public Builder(PlaybackStateCompat r4) {
            ArrayList r0 = new ArrayList();
            this.mCustomActions = r0;
            this.mActiveItemId = -1;
            this.mState = r4.mState;
            this.mPosition = r4.mPosition;
            this.mRate = r4.mSpeed;
            this.mUpdateTime = r4.mUpdateTime;
            this.mBufferedPosition = r4.mBufferedPosition;
            this.mActions = r4.mActions;
            this.mErrorCode = r4.mErrorCode;
            this.mErrorMessage = r4.mErrorMessage;
            List<CustomAction> r1 = r4.mCustomActions;
            if (r1 == null) goto L5;
            r0.addAll(r1);
        L5:
            this.mActiveItemId = r4.mActiveItemId;
            this.mExtras = r4.mExtras;
        }

        public Builder addCustomAction(CustomAction r2) {
            if (r2 == null) goto L5;
            this.mCustomActions.add(r2);
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("You may not add a null CustomAction to PlaybackStateCompat");
            return null;
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.mState, this.mPosition, this.mBufferedPosition, this.mRate, this.mActions, this.mErrorCode, this.mErrorMessage, this.mUpdateTime, this.mCustomActions, this.mActiveItemId, this.mExtras);
        }

        public Builder setActions(long r1) {
            this.mActions = r1;
            return this;
        }

        public Builder setState(int r8, long r9, float r11) {
            return setState(r8, r9, r11, SystemClock.elapsedRealtime());
        }

        public Builder setState(int r1, long r2, float r4, long r5) {
            this.mState = r1;
            this.mPosition = r2;
            this.mUpdateTime = r5;
            this.mRate = r4;
            return this;
        }
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = null;
        private final String mAction;
        private PlaybackState.CustomAction mCustomActionFwk;
        private final Bundle mExtras;
        private final int mIcon;
        private final CharSequence mName;

        /* renamed from: android.support.v4.media.session.PlaybackStateCompat$CustomAction$1, reason: invalid class name */
        public class AnonymousClass1 implements Parcelable.Creator<CustomAction> {
            public AnonymousClass1() {
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CustomAction createFromParcel(Parcel r1) {
                return new CustomAction(r1);
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ CustomAction createFromParcel(Parcel r1) {
                return createFromParcel(r1);
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // android.os.Parcelable.Creator
            public CustomAction[] newArray(int r1) {
                return new CustomAction[r1];
            }

            @Override // android.os.Parcelable.Creator
            public /* bridge */ /* synthetic */ CustomAction[] newArray(int r1) {
                return newArray(r1);
            }
        }

        public static final class Builder {
            private final String mAction;
            private Bundle mExtras;
            private final int mIcon;
            private final CharSequence mName;

            public Builder(String r2, CharSequence r3, int r4) {
                if (TextUtils.isEmpty(r2) == false) goto L5;
                f$$ExternalSyntheticBUOutline1.m("You must specify an action to build a CustomAction");
                throw null;
            L5:
                if (TextUtils.isEmpty(r3) == true) goto L11;
                if (r4 == 0) goto L9;
                this.mAction = r2;
                this.mName = r3;
                this.mIcon = r4;
                return;
            L9:
                f$$ExternalSyntheticBUOutline1.m("You must specify an icon resource id to build a CustomAction");
                throw null;
            L11:
                f$$ExternalSyntheticBUOutline1.m("You must specify a name to build a CustomAction");
                throw null;
            }

            public CustomAction build() {
                return new CustomAction(this.mAction, this.mName, this.mIcon, this.mExtras);
            }
        }

        static {
            CREATOR = new AnonymousClass1();
        }

        public CustomAction(Parcel r2) {
            this.mAction = r2.readString();
            this.mName = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r2);
            this.mIcon = r2.readInt();
            this.mExtras = r2.readBundle(MediaSessionCompat.class.getClassLoader());
        }

        public CustomAction(String r1, CharSequence r2, int r3, Bundle r4) {
            this.mAction = r1;
            this.mName = r2;
            this.mIcon = r3;
            this.mExtras = r4;
        }

        public static CustomAction fromCustomAction(Object r5) {
            if (r5 == null) goto L5;
            PlaybackState.CustomAction r52 = (PlaybackState.CustomAction) r5;
            Bundle r0 = Api21Impl.getExtras(r52);
            MediaSessionCompat.ensureClassLoader(r0);
            CustomAction r1 = new CustomAction(Api21Impl.getAction(r52), Api21Impl.getName(r52), Api21Impl.getIcon(r52), r0);
            r1.mCustomActionFwk = r52;
            return r1;
        L5:
            return null;
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Object getCustomAction() {
            PlaybackState.CustomAction r0 = this.mCustomActionFwk;
            if (r0 != null) goto L6;
            PlaybackState.CustomAction.Builder r02 = Api21Impl.createCustomActionBuilder(this.mAction, this.mName, this.mIcon);
            Api21Impl.setExtras(r02, this.mExtras);
            return Api21Impl.build(r02);
        L6:
            return r0;
        }

        public String toString() {
            return "Action:mName='" + this.mName + ", mIcon=" + this.mIcon + ", mExtras=" + this.mExtras;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            r2.writeString(this.mAction);
            TextUtils.writeToParcel(this.mName, r2, r3);
            r2.writeInt(this.mIcon);
            r2.writeBundle(this.mExtras);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public PlaybackStateCompat(int r1, long r2, long r4, float r6, long r7, int r9, CharSequence r10, long r11, List<CustomAction> r13, long r14, Bundle r16) {
        this.mState = r1;
        this.mPosition = r2;
        this.mBufferedPosition = r4;
        this.mSpeed = r6;
        this.mActions = r7;
        this.mErrorCode = r9;
        this.mErrorMessage = r10;
        this.mUpdateTime = r11;
        this.mCustomActions = new ArrayList(r13);
        this.mActiveItemId = r14;
        this.mExtras = r16;
    }

    public PlaybackStateCompat(Parcel r3) {
        this.mState = r3.readInt();
        this.mPosition = r3.readLong();
        this.mSpeed = r3.readFloat();
        this.mUpdateTime = r3.readLong();
        this.mBufferedPosition = r3.readLong();
        this.mActions = r3.readLong();
        this.mErrorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r3);
        this.mCustomActions = r3.createTypedArrayList(CustomAction.CREATOR);
        this.mActiveItemId = r3.readLong();
        this.mExtras = r3.readBundle(MediaSessionCompat.class.getClassLoader());
        this.mErrorCode = r3.readInt();
    }

    public static PlaybackStateCompat fromPlaybackState(Object r21) {
        ArrayList r0 = null;
        if (r21 == null) goto L12;
        PlaybackState r1 = (PlaybackState) r21;
        List<PlaybackState.CustomAction> r2 = Api21Impl.getCustomActions(r1);
        if (r2 == null) goto L10;
        r0 = new ArrayList(r2.size());
        Iterator<PlaybackState.CustomAction> r22 = r2.iterator();
    L8:
        if (r22.hasNext() == false) goto L10;
        r0.add(CustomAction.fromCustomAction(r22.next()));
    L10:
        Bundle r20 = Api22Impl.getExtras(r1);
        MediaSessionCompat.ensureClassLoader(r20);
        int r5 = Api21Impl.getState(r1);
        long r6 = Api21Impl.getPosition(r1);
        long r8 = Api21Impl.getBufferedPosition(r1);
        float r10 = Api21Impl.getPlaybackSpeed(r1);
        long r11 = Api21Impl.getActions(r1);
        CharSequence r14 = Api21Impl.getErrorMessage(r1);
        long r15 = Api21Impl.getLastPositionUpdateTime(r1);
        PlaybackStateCompat r4 = new PlaybackStateCompat(r5, r6, r8, r10, r11, 0, r14, r15, r0, Api21Impl.getActiveQueueItemId(r1), r20);
        r4.mStateFwk = r1;
        return r4;
    L12:
        return null;
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getActions() {
        return this.mActions;
    }

    public long getLastPositionUpdateTime() {
        return this.mUpdateTime;
    }

    public float getPlaybackSpeed() {
        return this.mSpeed;
    }

    public Object getPlaybackState() {
        if (this.mStateFwk != null) goto L10;
        PlaybackState.Builder r1 = Api21Impl.createBuilder();
        Api21Impl.setState(r1, this.mState, this.mPosition, this.mSpeed, this.mUpdateTime);
        Api21Impl.setBufferedPosition(r1, this.mBufferedPosition);
        Api21Impl.setActions(r1, this.mActions);
        Api21Impl.setErrorMessage(r1, this.mErrorMessage);
        Iterator<CustomAction> r0 = this.mCustomActions.iterator();
    L6:
        if (r0.hasNext() == false) goto L8;
        Api21Impl.addCustomAction(r1, (PlaybackState.CustomAction) r0.next().getCustomAction());
        goto L6
    L8:
        Api21Impl.setActiveQueueItemId(r1, this.mActiveItemId);
        Api22Impl.setExtras(r1, this.mExtras);
        this.mStateFwk = Api21Impl.build(r1);
    L10:
        return this.mStateFwk;
    }

    public long getPosition() {
        return this.mPosition;
    }

    public int getState() {
        return this.mState;
    }

    public String toString() {
        return "PlaybackState {state=" + this.mState + ", position=" + this.mPosition + ", buffered position=" + this.mBufferedPosition + ", speed=" + this.mSpeed + ", updated=" + this.mUpdateTime + ", actions=" + this.mActions + ", error code=" + this.mErrorCode + ", error message=" + this.mErrorMessage + ", custom actions=" + this.mCustomActions + ", active item id=" + this.mActiveItemId + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        r3.writeInt(this.mState);
        r3.writeLong(this.mPosition);
        r3.writeFloat(this.mSpeed);
        r3.writeLong(this.mUpdateTime);
        r3.writeLong(this.mBufferedPosition);
        r3.writeLong(this.mActions);
        TextUtils.writeToParcel(this.mErrorMessage, r3, r4);
        r3.writeTypedList(this.mCustomActions);
        r3.writeLong(this.mActiveItemId);
        r3.writeBundle(this.mExtras);
        r3.writeInt(this.mErrorCode);
    }
}
