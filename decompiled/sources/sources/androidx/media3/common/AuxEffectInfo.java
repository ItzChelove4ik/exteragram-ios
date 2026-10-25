package androidx.media3.common;

/* loaded from: classes3.dex */
public final class AuxEffectInfo {
    public final int effectId;
    public final float sendLevel;

    public AuxEffectInfo(int r1, float r2) {
        this.effectId = r1;
        this.sendLevel = r2;
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L15:
        return false;
    L8:
        if (AuxEffectInfo.class != r5.getClass()) goto L15;
        AuxEffectInfo r52 = (AuxEffectInfo) r5;
        if (this.effectId != r52.effectId) goto L15;
        if (Float.compare(r52.sendLevel, this.sendLevel) != 0) goto L15;
        return true;
    }

    public int hashCode() {
        return ((527 + this.effectId) * 31) + Float.floatToIntBits(this.sendLevel);
    }
}
