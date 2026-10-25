package androidx.media3.session.legacy;

import android.annotation.SuppressLint;
import android.media.AudioAttributes;
import android.os.Build;
import android.util.SparseIntArray;
import androidx.media3.common.util.Assertions;
import java.util.Objects;

/* loaded from: classes4.dex */
public class AudioAttributesCompat {
    private static final int[] SDK_USAGES = null;
    private static final SparseIntArray SUPPRESSIBLE_USAGES = null;
    private final AudioAttributesImpl impl;

    public interface AudioAttributesImpl {

        public interface Builder {
            AudioAttributesImpl build();

            Builder setContentType(int r1);

            Builder setFlags(int r1);

            Builder setUsage(int r1);
        }

        int getLegacyStreamType();
    }

    public static class AudioAttributesImplApi21 implements AudioAttributesImpl {
        public AudioAttributes audioAttributes;
        public final int legacyStreamType;

        public static class Builder implements AudioAttributesImpl.Builder {
            final AudioAttributes.Builder fwkBuilder;

            public Builder() {
                this.fwkBuilder = new AudioAttributes.Builder();
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public AudioAttributesImpl build() {
                return new AudioAttributesImplApi21(this.fwkBuilder.build());
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public /* bridge */ /* synthetic */ AudioAttributesImpl.Builder setContentType(int r1) {
                return setContentType(r1);
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public Builder setContentType(int r2) {
                this.fwkBuilder.setContentType(r2);
                return this;
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public /* bridge */ /* synthetic */ AudioAttributesImpl.Builder setFlags(int r1) {
                return setFlags(r1);
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public Builder setFlags(int r2) {
                this.fwkBuilder.setFlags(r2);
                return this;
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            @SuppressLint({"WrongConstant"})
            public /* bridge */ /* synthetic */ AudioAttributesImpl.Builder setUsage(int r1) {
                return setUsage(r1);
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            @SuppressLint({"WrongConstant"})
            public Builder setUsage(int r2) {
                if (r2 != 16) goto L5;
                r2 = 12;
            L5:
                this.fwkBuilder.setUsage(r2);
                return this;
            }
        }

        public AudioAttributesImplApi21(AudioAttributes r2) {
            this(r2, -1);
        }

        public AudioAttributesImplApi21(AudioAttributes r1, int r2) {
            this.audioAttributes = r1;
            this.legacyStreamType = r2;
        }

        public boolean equals(Object r2) {
            if ((r2 instanceof AudioAttributesImplApi21) == true) goto L7;
            return false;
        L7:
            return Objects.equals(this.audioAttributes, ((AudioAttributesImplApi21) r2).audioAttributes);
        }

        public int getFlags() {
            return ((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getFlags();
        }

        @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl
        public int getLegacyStreamType() {
            int r0 = this.legacyStreamType;
            if (r0 == (-1)) goto L6;
            return r0;
        L6:
            return AudioAttributesCompat.toVolumeStreamType(getFlags(), getUsage());
        }

        public int getUsage() {
            return ((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).getUsage();
        }

        public int hashCode() {
            return ((AudioAttributes) Assertions.checkNotNull(this.audioAttributes)).hashCode();
        }

        public String toString() {
            return "AudioAttributesCompat: audioattributes=" + this.audioAttributes;
        }
    }

    public static class AudioAttributesImplApi26 extends AudioAttributesImplApi21 {

        public static class Builder extends AudioAttributesImplApi21.Builder {
            public Builder() {
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImplApi21.Builder, androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            public AudioAttributesImpl build() {
                return new AudioAttributesImplApi26(this.fwkBuilder.build());
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImplApi21.Builder, androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            @SuppressLint({"WrongConstant"})
            public /* bridge */ /* synthetic */ AudioAttributesImpl.Builder setUsage(int r1) {
                return setUsage(r1);
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImplApi21.Builder, androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            @SuppressLint({"WrongConstant"})
            public /* bridge */ /* synthetic */ AudioAttributesImplApi21.Builder setUsage(int r1) {
                return setUsage(r1);
            }

            @Override // androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImplApi21.Builder, androidx.media3.session.legacy.AudioAttributesCompat.AudioAttributesImpl.Builder
            @SuppressLint({"WrongConstant"})
            public Builder setUsage(int r2) {
                this.fwkBuilder.setUsage(r2);
                return this;
            }
        }

        public AudioAttributesImplApi26(AudioAttributes r2) {
            super(r2, -1);
        }
    }

    public static class Builder {
        final AudioAttributesImpl.Builder builderImpl;

        public Builder() {
            if (Build.VERSION.SDK_INT < 26) goto L6;
            this.builderImpl = new AudioAttributesImplApi26.Builder();
            return;
        L6:
            this.builderImpl = new AudioAttributesImplApi21.Builder();
        }

        public AudioAttributesCompat build() {
            return new AudioAttributesCompat(this.builderImpl.build());
        }

        public Builder setContentType(int r2) {
            this.builderImpl.setContentType(r2);
            return this;
        }

        public Builder setFlags(int r2) {
            this.builderImpl.setFlags(r2);
            return this;
        }

        public Builder setUsage(int r2) {
            this.builderImpl.setUsage(r2);
            return this;
        }
    }

    static {
        SparseIntArray r0 = new SparseIntArray();
        SUPPRESSIBLE_USAGES = r0;
        r0.put(5, 1);
        r0.put(6, 2);
        r0.put(7, 2);
        r0.put(8, 1);
        r0.put(9, 1);
        r0.put(10, 1);
        SDK_USAGES = new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 16};
    }

    public AudioAttributesCompat(AudioAttributesImpl r1) {
        this.impl = r1;
    }

    public static int toVolumeStreamType(int r2, int r3) {
        if ((r2 & 1) != 1) goto L7;
        return 7;
    L7:
        if ((r2 & 4) != 4) goto L10;
        return 6;
    L10:
        switch(r3) {
            case 2: goto L23;
            case 3: goto L21;
            case 4: goto L20;
            case 5: goto L18;
            case 6: goto L16;
            case 7: goto L18;
            case 8: goto L18;
            case 9: goto L18;
            case 10: goto L18;
            case 11: goto L14;
            case 12: goto L11;
            case 13: goto L13;
            default: goto L11;
        };
    L11:
        return 3;
    L13:
        return 1;
    L14:
        return 10;
    L16:
        return 2;
    L18:
        return 5;
    L20:
        return 4;
    L21:
        return 8;
    L23:
        return 0;
    }

    public boolean equals(Object r2) {
        if ((r2 instanceof AudioAttributesCompat) == true) goto L7;
        return false;
    L7:
        return this.impl.equals(((AudioAttributesCompat) r2).impl);
    }

    public int getLegacyStreamType() {
        return this.impl.getLegacyStreamType();
    }

    public int hashCode() {
        return this.impl.hashCode();
    }

    public String toString() {
        return this.impl.toString();
    }
}
