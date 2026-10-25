package androidx.media3.extractor.text.ttml;

/* loaded from: classes4.dex */
final class TtmlRegion {
    public final float height;
    public final String id;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final float position;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final float width;

    public TtmlRegion(String r12) {
        this(r12, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, Integer.MIN_VALUE, -3.4028235E38f, -3.4028235E38f, Integer.MIN_VALUE, -3.4028235E38f, Integer.MIN_VALUE);
    }

    public TtmlRegion(String r1, float r2, float r3, int r4, int r5, float r6, float r7, int r8, float r9, int r10) {
        this.id = r1;
        this.position = r2;
        this.line = r3;
        this.lineType = r4;
        this.lineAnchor = r5;
        this.width = r6;
        this.height = r7;
        this.textSizeType = r8;
        this.textSize = r9;
        this.verticalType = r10;
    }
}
