package androidx.media3.common;

import android.os.Bundle;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.util.Objects;

/* loaded from: classes3.dex */
public final class DeviceInfo {
    private static final String FIELD_MAX_VOLUME = null;
    private static final String FIELD_MIN_VOLUME = null;
    private static final String FIELD_PLAYBACK_TYPE = null;
    private static final String FIELD_ROUTING_CONTROLLER_ID = null;
    public static final DeviceInfo UNKNOWN = null;
    public final int maxVolume;
    public final int minVolume;
    public final int playbackType;
    public final String routingControllerId;

    /* renamed from: androidx.media3.common.DeviceInfo$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private int maxVolume;
        private int minVolume;
        private final int playbackType;
        private String routingControllerId;

        public Builder(int r1) {
            this.playbackType = r1;
        }

        public static /* synthetic */ int access$100(Builder r0) {
            return r0.playbackType;
        }

        public static /* synthetic */ int access$200(Builder r0) {
            return r0.minVolume;
        }

        public static /* synthetic */ int access$300(Builder r0) {
            return r0.maxVolume;
        }

        public static /* synthetic */ String access$400(Builder r0) {
            return r0.routingControllerId;
        }

        public DeviceInfo build() {
            if (this.minVolume > this.maxVolume) goto L5;
            boolean r0 = true;
        L6:
            Assertions.checkArgument(r0);
            return new DeviceInfo(this, null);
        L5:
            r0 = false;
            goto L6
        }

        public Builder setMaxVolume(int r1) {
            this.maxVolume = r1;
            return this;
        }

        public Builder setMinVolume(int r1) {
            this.minVolume = r1;
            return this;
        }
    }

    static {
        UNKNOWN = new Builder(0).build();
        FIELD_PLAYBACK_TYPE = Util.intToStringMaxRadix(0);
        FIELD_MIN_VOLUME = Util.intToStringMaxRadix(1);
        FIELD_MAX_VOLUME = Util.intToStringMaxRadix(2);
        FIELD_ROUTING_CONTROLLER_ID = Util.intToStringMaxRadix(3);
    }

    private DeviceInfo(Builder r2) {
        this.playbackType = Builder.access$100(r2);
        this.minVolume = Builder.access$200(r2);
        this.maxVolume = Builder.access$300(r2);
        this.routingControllerId = Builder.access$400(r2);
    }

    public /* synthetic */ DeviceInfo(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if ((r5 instanceof DeviceInfo) == true) goto L8;
        return false;
    L8:
        DeviceInfo r52 = (DeviceInfo) r5;
        if (this.playbackType == r52.playbackType) goto L11;
    L17:
        return false;
    L11:
        if (this.minVolume != r52.minVolume) goto L17;
        if (this.maxVolume != r52.maxVolume) goto L17;
        if (Objects.equals(this.routingControllerId, r52.routingControllerId) == false) goto L17;
        return true;
    }

    public int hashCode() {
        int r0 = (((((527 + this.playbackType) * 31) + this.minVolume) * 31) + this.maxVolume) * 31;
        String r2 = this.routingControllerId;
        if (r2 != null) goto L5;
        int r22 = 0;
    L7:
        return r0 + r22;
    L5:
        r22 = r2.hashCode();
        goto L7
    }

    public Bundle toBundle() {
        Bundle r0 = new Bundle();
        int r1 = this.playbackType;
        if (r1 == 0) goto L5;
        r0.putInt(FIELD_PLAYBACK_TYPE, r1);
    L5:
        int r12 = this.minVolume;
        if (r12 == 0) goto L8;
        r0.putInt(FIELD_MIN_VOLUME, r12);
    L8:
        int r13 = this.maxVolume;
        if (r13 == 0) goto L11;
        r0.putInt(FIELD_MAX_VOLUME, r13);
    L11:
        String r3 = this.routingControllerId;
        if (r3 == null) goto L14;
        r0.putString(FIELD_ROUTING_CONTROLLER_ID, r3);
    L14:
        return r0;
    }
}
