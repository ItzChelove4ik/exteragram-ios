package androidx.media;

import java.util.Arrays;

/* loaded from: classes3.dex */
public class AudioAttributesImplBase implements AudioAttributesImpl {
    public int mContentType;
    public int mFlags;
    public int mLegacyStream;
    public int mUsage;

    public AudioAttributesImplBase() {
        this.mUsage = 0;
        this.mContentType = 0;
        this.mFlags = 0;
        this.mLegacyStream = -1;
    }

    public boolean equals(Object r4) {
        if ((r4 instanceof AudioAttributesImplBase) == true) goto L5;
        return false;
    L5:
        AudioAttributesImplBase r42 = (AudioAttributesImplBase) r4;
        if (this.mContentType == r42.getContentType()) goto L8;
    L15:
        return false;
    L8:
        if (this.mFlags != r42.getFlags()) goto L15;
        if (this.mUsage != r42.getUsage()) goto L15;
        if (this.mLegacyStream != r42.mLegacyStream) goto L15;
        return true;
    }

    public int getContentType() {
        return this.mContentType;
    }

    public int getFlags() {
        int r0 = this.mFlags;
        int r2 = getLegacyStreamType();
        if (r2 != 6) goto L6;
        r0 = r0 | 4;
    L9:
        return r0 & 273;
    L6:
        if (r2 != 7) goto L9;
        r0 = r0 | 1;
        goto L9
    }

    public int getLegacyStreamType() {
        int r0 = this.mLegacyStream;
        if (r0 == (-1)) goto L6;
        return r0;
    L6:
        return AudioAttributesCompat.toVolumeStreamType(false, this.mFlags, this.mUsage);
    }

    public int getUsage() {
        return this.mUsage;
    }

    public int hashCode() {
        return Arrays.hashCode(new Object[]{Integer.valueOf(this.mContentType), Integer.valueOf(this.mFlags), Integer.valueOf(this.mUsage), Integer.valueOf(this.mLegacyStream)});
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder("AudioAttributesCompat:");
        if (this.mLegacyStream == (-1)) goto L5;
        r0.append(" stream=");
        r0.append(this.mLegacyStream);
        r0.append(" derived");
    L5:
        r0.append(" usage=");
        r0.append(AudioAttributesCompat.usageToString(this.mUsage));
        r0.append(" content=");
        r0.append(this.mContentType);
        r0.append(" flags=0x");
        r0.append(Integer.toHexString(this.mFlags).toUpperCase());
        return r0.toString();
    }
}
