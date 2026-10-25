package androidx.emoji2.text;

import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Typeface;
import androidx.emoji2.text.flatbuffer.MetadataItem;

/* loaded from: classes.dex */
public class TypefaceEmojiRasterizer {
    private static final ThreadLocal<MetadataItem> sMetadataItem = null;
    private volatile int mCache;
    private final int mIndex;
    private final MetadataRepo mMetadataRepo;

    static {
        sMetadataItem = new ThreadLocal();
    }

    public TypefaceEmojiRasterizer(MetadataRepo r2, int r3) {
        this.mCache = 0;
        this.mMetadataRepo = r2;
        this.mIndex = r3;
    }

    private MetadataItem getMetadataItem() {
        ThreadLocal<MetadataItem> r0 = sMetadataItem;
        MetadataItem r1 = r0.get();
        if (r1 != null) goto L5;
        r1 = new MetadataItem();
        r0.set(r1);
    L5:
        this.mMetadataRepo.getMetadataList().list(r1, this.mIndex);
        return r1;
    }

    public void draw(Canvas r10, float r11, float r12, Paint r13) {
        Typeface r0 = this.mMetadataRepo.getTypeface();
        Typeface r1 = r13.getTypeface();
        r13.setTypeface(r0);
        int r4 = this.mIndex * 2;
        r10.drawText(this.mMetadataRepo.getEmojiCharArray(), r4, 2, r11, r12, r13);
        r13.setTypeface(r1);
    }

    public int getCodepointAt(int r1) {
        return getMetadataItem().codepoints(r1);
    }

    public int getCodepointsLength() {
        return getMetadataItem().codepointsLength();
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public int getHasGlyph() {
        return this.mCache & 3;
    }

    public int getHeight() {
        return getMetadataItem().height();
    }

    public int getId() {
        return getMetadataItem().id();
    }

    public short getSdkAdded() {
        return getMetadataItem().sdkAdded();
    }

    public int getWidth() {
        return getMetadataItem().width();
    }

    public boolean isDefaultEmoji() {
        return getMetadataItem().emojiStyle();
    }

    public boolean isPreferredSystemRender() {
        if ((this.mCache & 4) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void setExclusion(boolean r2) {
        int r0 = getHasGlyph();
        if (r2 == false) goto L6;
        this.mCache = r0 | 4;
        return;
    L6:
        this.mCache = r0;
    }

    @SuppressLint({"KotlinPropertyAccess"})
    public void setHasGlyph(boolean r2) {
        int r0 = this.mCache & 4;
        if (r2 == false) goto L5;
        int r22 = r0 | 2;
    L6:
        this.mCache = r22;
        return;
    L5:
        r22 = r0 | 1;
        goto L6
    }

    public String toString() {
        StringBuilder r0 = new StringBuilder();
        r0.append(super.toString());
        r0.append(", id:");
        r0.append(Integer.toHexString(getId()));
        r0.append(", codepoints:");
        int r1 = getCodepointsLength();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L6;
        r0.append(Integer.toHexString(getCodepointAt(r2)));
        r0.append(" ");
        r2 = r2 + 1;
        goto L3
    L6:
        return r0.toString();
    }
}
