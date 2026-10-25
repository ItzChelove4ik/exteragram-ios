package androidx.media3.exoplayer;

import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* loaded from: classes4.dex */
public final class LoadingInfo {
    public final long lastRebufferRealtimeMs;
    public final long playbackPositionUs;
    public final float playbackSpeed;

    /* renamed from: androidx.media3.exoplayer.LoadingInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private long lastRebufferRealtimeMs;
        private long playbackPositionUs;
        private float playbackSpeed;

        public Builder() {
            this.playbackPositionUs = -9223372036854775807L;
            this.playbackSpeed = -3.4028235E38f;
            this.lastRebufferRealtimeMs = -9223372036854775807L;
        }

        private Builder(LoadingInfo r3) {
            this.playbackPositionUs = r3.playbackPositionUs;
            this.playbackSpeed = r3.playbackSpeed;
            this.lastRebufferRealtimeMs = r3.lastRebufferRealtimeMs;
        }

        public /* synthetic */ Builder(LoadingInfo r1, AnonymousClass1 r2) {
            this(r1);
        }

        public static /* synthetic */ long access$100(Builder r2) {
            return r2.playbackPositionUs;
        }

        public static /* synthetic */ float access$200(Builder r0) {
            return r0.playbackSpeed;
        }

        public static /* synthetic */ long access$300(Builder r2) {
            return r2.lastRebufferRealtimeMs;
        }

        public LoadingInfo build() {
            return new LoadingInfo(this, null);
        }

        public Builder setLastRebufferRealtimeMs(long r3) {
            if (r3 < 0) goto L5;
        L8:
            boolean r0 = true;
        L9:
            Assertions.checkArgument(r0);
            this.lastRebufferRealtimeMs = r3;
            return this;
        L5:
            if (r3 == (-9223372036854775807L)) goto L8;
            r0 = false;
            goto L9
        }

        public Builder setPlaybackPositionUs(long r1) {
            this.playbackPositionUs = r1;
            return this;
        }

        public Builder setPlaybackSpeed(float r2) {
            if (r2 <= 0.0f) goto L5;
        L8:
            boolean r0 = true;
        L9:
            Assertions.checkArgument(r0);
            this.playbackSpeed = r2;
            return this;
        L5:
            if (r2 == (-3.4028235E38f)) goto L8;
            r0 = false;
            goto L9
        }
    }

    private LoadingInfo(Builder r3) {
        this.playbackPositionUs = Builder.access$100(r3);
        this.playbackSpeed = Builder.access$200(r3);
        this.lastRebufferRealtimeMs = Builder.access$300(r3);
    }

    public /* synthetic */ LoadingInfo(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r8) {
        if (this != r8) goto L6;
        return true;
    L6:
        if ((r8 instanceof LoadingInfo) == true) goto L8;
        return false;
    L8:
        LoadingInfo r82 = (LoadingInfo) r8;
        if (this.playbackPositionUs == r82.playbackPositionUs) goto L11;
    L15:
        return false;
    L11:
        if (this.playbackSpeed != r82.playbackSpeed) goto L15;
        if (this.lastRebufferRealtimeMs != r82.lastRebufferRealtimeMs) goto L15;
        return true;
    }

    public int hashCode() {
        return Objects.hash(new Object[]{Long.valueOf(this.playbackPositionUs), Float.valueOf(this.playbackSpeed), Long.valueOf(this.lastRebufferRealtimeMs)});
    }
}
