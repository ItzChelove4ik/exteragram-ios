package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.media.session.PlaybackState;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@SuppressLint({"BanParcelableUsage"})
/* loaded from: classes4.dex */
public final class PlaybackStateCompat implements Parcelable {
    public static final Parcelable.Creator<PlaybackStateCompat> CREATOR = null;
    final long actions;
    final long activeItemId;
    final long bufferedPosition;
    List<CustomAction> customActions;
    final int errorCode;
    final CharSequence errorMessage;
    final Bundle extras;
    final long position;
    final float speed;
    final int state;
    private PlaybackState stateFwk;
    final long updateTime;

    /* renamed from: androidx.media3.session.legacy.PlaybackStateCompat$1, reason: invalid class name */
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

    public static class Api22Impl {
        public static void setExtras(PlaybackState.Builder r0, Bundle r1) {
            r0.setExtras(r1);
        }
    }

    public static final class Builder {
        private long actions;
        private long activeItemId;
        private long bufferedPosition;
        private final List<CustomAction> customActions;
        private int errorCode;
        private CharSequence errorMessage;
        private Bundle extras;
        private long position;
        private float rate;
        private int state;
        private long updateTime;

        public Builder() {
            this.customActions = new ArrayList();
            this.activeItemId = -1;
        }

        public Builder(PlaybackStateCompat r4) {
            ArrayList r0 = new ArrayList();
            this.customActions = r0;
            this.activeItemId = -1;
            this.state = r4.state;
            this.position = r4.position;
            this.rate = r4.speed;
            this.updateTime = r4.updateTime;
            this.bufferedPosition = r4.bufferedPosition;
            this.actions = r4.actions;
            this.errorCode = r4.errorCode;
            this.errorMessage = r4.errorMessage;
            List<CustomAction> r1 = r4.customActions;
            if (r1 == null) goto L5;
            r0.addAll(r1);
        L5:
            this.activeItemId = r4.activeItemId;
            this.extras = r4.extras;
        }

        public PlaybackStateCompat build() {
            return new PlaybackStateCompat(this.state, this.position, this.bufferedPosition, this.rate, this.actions, this.errorCode, this.errorMessage, this.updateTime, this.customActions, this.activeItemId, this.extras);
        }

        public Builder setActions(long r1) {
            this.actions = r1;
            return this;
        }

        public Builder setActiveQueueItemId(long r1) {
            this.activeItemId = r1;
            return this;
        }

        public Builder setBufferedPosition(long r1) {
            this.bufferedPosition = r1;
            return this;
        }

        public Builder setErrorMessage(int r1, CharSequence r2) {
            this.errorCode = r1;
            this.errorMessage = r2;
            return this;
        }

        public Builder setExtras(Bundle r1) {
            this.extras = r1;
            return this;
        }

        public Builder setState(int r1, long r2, float r4, long r5) {
            this.state = r1;
            this.position = r2;
            this.updateTime = r5;
            this.rate = r4;
            return this;
        }
    }

    public static final class CustomAction implements Parcelable {
        public static final Parcelable.Creator<CustomAction> CREATOR = null;
        private final String action;
        private PlaybackState.CustomAction customActionFwk;
        private final Bundle extras;
        private final int icon;
        private final CharSequence name;

        /* renamed from: androidx.media3.session.legacy.PlaybackStateCompat$CustomAction$1, reason: invalid class name */
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

        static {
            CREATOR = new AnonymousClass1();
        }

        public CustomAction(Parcel r2) {
            this.action = (String) Assertions.checkNotNull(r2.readString());
            this.name = (CharSequence) Assertions.checkNotNull((CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r2));
            this.icon = r2.readInt();
            this.extras = r2.readBundle(MediaSessionCompat.class.getClassLoader());
        }

        @Override // android.os.Parcelable
        public int describeContents() {
            return 0;
        }

        public Object getCustomAction() {
            PlaybackState.CustomAction r0 = this.customActionFwk;
            if (r0 == null) goto L5;
            return r0;
        L5:
            PlaybackState.CustomAction.Builder r02 = new PlaybackState.CustomAction.Builder(this.action, this.name, this.icon);
            r02.setExtras(this.extras);
            return r02.build();
        }

        public String toString() {
            return "Action:mName='" + this.name + ", mIcon=" + this.icon + ", mExtras=" + this.extras;
        }

        @Override // android.os.Parcelable
        public void writeToParcel(Parcel r2, int r3) {
            r2.writeString(this.action);
            TextUtils.writeToParcel(this.name, r2, r3);
            r2.writeInt(this.icon);
            r2.writeBundle(this.extras);
        }
    }

    static {
        CREATOR = new AnonymousClass1();
    }

    public PlaybackStateCompat(int r1, long r2, long r4, float r6, long r7, int r9, CharSequence r10, long r11, List<CustomAction> r13, long r14, Bundle r16) {
        this.state = r1;
        this.position = r2;
        this.bufferedPosition = r4;
        this.speed = r6;
        this.actions = r7;
        this.errorCode = r9;
        this.errorMessage = r10;
        this.updateTime = r11;
        if (r13 != null) goto L5;
        List<CustomAction> r12 = ImmutableList.of();
    L6:
        this.customActions = r12;
        this.activeItemId = r14;
        this.extras = r16;
        return;
    L5:
        r12 = new ArrayList(r13);
        goto L6
    }

    public PlaybackStateCompat(Parcel r3) {
        this.state = r3.readInt();
        this.position = r3.readLong();
        this.speed = r3.readFloat();
        this.updateTime = r3.readLong();
        this.bufferedPosition = r3.readLong();
        this.actions = r3.readLong();
        this.errorMessage = (CharSequence) TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(r3);
        List<CustomAction> r0 = r3.createTypedArrayList(CustomAction.CREATOR);
        if (r0 != null) goto L5;
        r0 = ImmutableList.of();
    L5:
        this.customActions = r0;
        this.activeItemId = r3.readLong();
        this.extras = r3.readBundle(MediaSessionCompat.class.getClassLoader());
        this.errorCode = r3.readInt();
    }

    @Override // android.os.Parcelable
    public int describeContents() {
        return 0;
    }

    public long getActions() {
        return this.actions;
    }

    public long getLastPositionUpdateTime() {
        return this.updateTime;
    }

    public float getPlaybackSpeed() {
        return this.speed;
    }

    public PlaybackState getPlaybackState() {
        if (this.stateFwk != null) goto L12;
        PlaybackState.Builder r1 = new PlaybackState.Builder();
        r1.setState(this.state, this.position, this.speed, this.updateTime);
        r1.setBufferedPosition(this.bufferedPosition);
        r1.setActions(this.actions);
        r1.setErrorMessage(this.errorMessage);
        Iterator<CustomAction> r0 = this.customActions.iterator();
    L6:
        if (r0.hasNext() == false) goto L10;
        PlaybackState.CustomAction r2 = (PlaybackState.CustomAction) r0.next().getCustomAction();
        if (r2 == null) goto L6;
        r1.addCustomAction(r2);
        goto L6
    L10:
        r1.setActiveQueueItemId(this.activeItemId);
        Api22Impl.setExtras(r1, this.extras);
        this.stateFwk = r1.build();
    L12:
        return this.stateFwk;
    }

    public long getPosition() {
        return this.position;
    }

    public int getState() {
        return this.state;
    }

    public String toString() {
        return "PlaybackState {state=" + this.state + ", position=" + this.position + ", buffered position=" + this.bufferedPosition + ", speed=" + this.speed + ", updated=" + this.updateTime + ", actions=" + this.actions + ", error code=" + this.errorCode + ", error message=" + this.errorMessage + ", custom actions=" + this.customActions + ", active item id=" + this.activeItemId + "}";
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r3, int r4) {
        r3.writeInt(this.state);
        r3.writeLong(this.position);
        r3.writeFloat(this.speed);
        r3.writeLong(this.updateTime);
        r3.writeLong(this.bufferedPosition);
        r3.writeLong(this.actions);
        TextUtils.writeToParcel(this.errorMessage, r3, r4);
        r3.writeTypedList(this.customActions);
        r3.writeLong(this.activeItemId);
        r3.writeBundle(this.extras);
        r3.writeInt(this.errorCode);
    }
}
