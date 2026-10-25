package androidx.media3.common.text;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.text.Layout;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.SpannedString;
import android.text.TextUtils;
import androidx.media3.common.util.Assertions;
import androidx.media3.common.util.Util;
import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.checkerframework.dataflow.qual.Pure;

/* loaded from: classes3.dex */
public final class Cue {

    @Deprecated
    public static final Cue EMPTY = null;
    private static final String FIELD_BITMAP_BYTES = null;
    private static final String FIELD_BITMAP_HEIGHT = null;
    private static final String FIELD_BITMAP_PARCELABLE = null;
    private static final String FIELD_CUSTOM_SPANS = null;
    private static final String FIELD_LINE = null;
    private static final String FIELD_LINE_ANCHOR = null;
    private static final String FIELD_LINE_TYPE = null;
    private static final String FIELD_MULTI_ROW_ALIGNMENT = null;
    private static final String FIELD_POSITION = null;
    private static final String FIELD_POSITION_ANCHOR = null;
    private static final String FIELD_SHEAR_DEGREES = null;
    private static final String FIELD_SIZE = null;
    private static final String FIELD_TEXT = null;
    private static final String FIELD_TEXT_ALIGNMENT = null;
    private static final String FIELD_TEXT_SIZE = null;
    private static final String FIELD_TEXT_SIZE_TYPE = null;
    private static final String FIELD_VERTICAL_TYPE = null;
    private static final String FIELD_WINDOW_COLOR = null;
    private static final String FIELD_WINDOW_COLOR_SET = null;
    private static final String FIELD_Z_INDEX = null;
    public final Bitmap bitmap;
    public final float bitmapHeight;
    public final float line;
    public final int lineAnchor;
    public final int lineType;
    public final Layout.Alignment multiRowAlignment;
    public final float position;
    public final int positionAnchor;
    public final float shearDegrees;
    public final float size;
    public final CharSequence text;
    public final Layout.Alignment textAlignment;
    public final float textSize;
    public final int textSizeType;
    public final int verticalType;
    public final int windowColor;
    public final boolean windowColorSet;
    public final int zIndex;

    /* renamed from: androidx.media3.common.text.Cue$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private Bitmap bitmap;
        private float bitmapHeight;
        private float line;
        private int lineAnchor;
        private int lineType;
        private Layout.Alignment multiRowAlignment;
        private float position;
        private int positionAnchor;
        private float shearDegrees;
        private float size;
        private CharSequence text;
        private Layout.Alignment textAlignment;
        private float textSize;
        private int textSizeType;
        private int verticalType;
        private int windowColor;
        private boolean windowColorSet;
        private int zIndex;

        public Builder() {
            this.text = null;
            this.bitmap = null;
            this.textAlignment = null;
            this.multiRowAlignment = null;
            this.line = -3.4028235E38f;
            this.lineType = Integer.MIN_VALUE;
            this.lineAnchor = Integer.MIN_VALUE;
            this.position = -3.4028235E38f;
            this.positionAnchor = Integer.MIN_VALUE;
            this.textSizeType = Integer.MIN_VALUE;
            this.textSize = -3.4028235E38f;
            this.size = -3.4028235E38f;
            this.bitmapHeight = -3.4028235E38f;
            this.windowColorSet = false;
            this.windowColor = -16777216;
            this.verticalType = Integer.MIN_VALUE;
        }

        private Builder(Cue r2) {
            this.text = r2.text;
            this.bitmap = r2.bitmap;
            this.textAlignment = r2.textAlignment;
            this.multiRowAlignment = r2.multiRowAlignment;
            this.line = r2.line;
            this.lineType = r2.lineType;
            this.lineAnchor = r2.lineAnchor;
            this.position = r2.position;
            this.positionAnchor = r2.positionAnchor;
            this.textSizeType = r2.textSizeType;
            this.textSize = r2.textSize;
            this.size = r2.size;
            this.bitmapHeight = r2.bitmapHeight;
            this.windowColorSet = r2.windowColorSet;
            this.windowColor = r2.windowColor;
            this.verticalType = r2.verticalType;
            this.shearDegrees = r2.shearDegrees;
            this.zIndex = r2.zIndex;
        }

        public /* synthetic */ Builder(Cue r1, AnonymousClass1 r2) {
            this(r1);
        }

        public Cue build() {
            return new Cue(this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, this.line, this.lineType, this.lineAnchor, this.position, this.positionAnchor, this.textSizeType, this.textSize, this.size, this.bitmapHeight, this.windowColorSet, this.windowColor, this.verticalType, this.shearDegrees, this.zIndex, null);
        }

        public Builder clearWindowColor() {
            this.windowColorSet = false;
            return this;
        }

        @Pure
        public int getLineAnchor() {
            return this.lineAnchor;
        }

        @Pure
        public int getPositionAnchor() {
            return this.positionAnchor;
        }

        @Pure
        public CharSequence getText() {
            return this.text;
        }

        public Builder setBitmap(Bitmap r1) {
            this.bitmap = r1;
            this.text = null;
            return this;
        }

        public Builder setBitmapHeight(float r1) {
            this.bitmapHeight = r1;
            return this;
        }

        public Builder setLine(float r1, int r2) {
            this.line = r1;
            this.lineType = r2;
            return this;
        }

        public Builder setLineAnchor(int r1) {
            this.lineAnchor = r1;
            return this;
        }

        public Builder setMultiRowAlignment(Layout.Alignment r1) {
            this.multiRowAlignment = r1;
            return this;
        }

        public Builder setPosition(float r1) {
            this.position = r1;
            return this;
        }

        public Builder setPositionAnchor(int r1) {
            this.positionAnchor = r1;
            return this;
        }

        public Builder setShearDegrees(float r1) {
            this.shearDegrees = r1;
            return this;
        }

        public Builder setSize(float r1) {
            this.size = r1;
            return this;
        }

        public Builder setText(CharSequence r1) {
            this.text = r1;
            this.bitmap = null;
            return this;
        }

        public Builder setTextAlignment(Layout.Alignment r1) {
            this.textAlignment = r1;
            return this;
        }

        public Builder setTextSize(float r1, int r2) {
            this.textSize = r1;
            this.textSizeType = r2;
            return this;
        }

        public Builder setVerticalType(int r1) {
            this.verticalType = r1;
            return this;
        }

        public Builder setWindowColor(int r1) {
            this.windowColor = r1;
            this.windowColorSet = true;
            return this;
        }

        public Builder setZIndex(int r1) {
            this.zIndex = r1;
            return this;
        }
    }

    static {
        EMPTY = new Builder().setText(_UrlKt.FRAGMENT_ENCODE_SET).build();
        FIELD_TEXT = Util.intToStringMaxRadix(0);
        FIELD_CUSTOM_SPANS = Util.intToStringMaxRadix(17);
        FIELD_TEXT_ALIGNMENT = Util.intToStringMaxRadix(1);
        FIELD_MULTI_ROW_ALIGNMENT = Util.intToStringMaxRadix(2);
        FIELD_BITMAP_PARCELABLE = Util.intToStringMaxRadix(3);
        FIELD_BITMAP_BYTES = Util.intToStringMaxRadix(18);
        FIELD_LINE = Util.intToStringMaxRadix(4);
        FIELD_LINE_TYPE = Util.intToStringMaxRadix(5);
        FIELD_LINE_ANCHOR = Util.intToStringMaxRadix(6);
        FIELD_POSITION = Util.intToStringMaxRadix(7);
        FIELD_POSITION_ANCHOR = Util.intToStringMaxRadix(8);
        FIELD_TEXT_SIZE_TYPE = Util.intToStringMaxRadix(9);
        FIELD_TEXT_SIZE = Util.intToStringMaxRadix(10);
        FIELD_SIZE = Util.intToStringMaxRadix(11);
        FIELD_BITMAP_HEIGHT = Util.intToStringMaxRadix(12);
        FIELD_WINDOW_COLOR = Util.intToStringMaxRadix(13);
        FIELD_WINDOW_COLOR_SET = Util.intToStringMaxRadix(14);
        FIELD_VERTICAL_TYPE = Util.intToStringMaxRadix(15);
        FIELD_SHEAR_DEGREES = Util.intToStringMaxRadix(16);
        FIELD_Z_INDEX = Util.intToStringMaxRadix(19);
    }

    private Cue(CharSequence r2, Layout.Alignment r3, Layout.Alignment r4, Bitmap r5, float r6, int r7, int r8, float r9, int r10, int r11, float r12, float r13, float r14, boolean r15, int r16, int r17, float r18, int r19) {
        if (r2 != null) goto L5;
        Assertions.checkNotNull(r5);
    L10:
        if ((r2 instanceof Spanned) == false) goto L12;
        this.text = SpannedString.valueOf(r2);
    L15:
        this.textAlignment = r3;
        this.multiRowAlignment = r4;
        this.bitmap = r5;
        this.line = r6;
        this.lineType = r7;
        this.lineAnchor = r8;
        this.position = r9;
        this.positionAnchor = r10;
        this.size = r13;
        this.bitmapHeight = r14;
        this.windowColorSet = r15;
        this.windowColor = r16;
        this.textSizeType = r11;
        this.textSize = r12;
        this.verticalType = r17;
        this.shearDegrees = r18;
        this.zIndex = r19;
        return;
    L12:
        if (r2 == null) goto L14;
        this.text = r2.toString();
        goto L15
    L14:
        this.text = null;
        goto L15
    L5:
        if (r5 != null) goto L7;
        boolean r0 = true;
    L8:
        Assertions.checkArgument(r0);
        goto L10
    L7:
        r0 = false;
        goto L8
    }

    public /* synthetic */ Cue(CharSequence r1, Layout.Alignment r2, Layout.Alignment r3, Bitmap r4, float r5, int r6, int r7, float r8, int r9, int r10, float r11, float r12, float r13, boolean r14, int r15, int r16, float r17, int r18, AnonymousClass1 r19) {
        this(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18);
    }

    public static Cue fromBundle(Bundle r7) {
        Builder r0 = new Builder();
        CharSequence r1 = r7.getCharSequence(FIELD_TEXT);
        if (r1 == null) goto L10;
        r0.setText(r1);
        ArrayList r3 = r7.getParcelableArrayList(FIELD_CUSTOM_SPANS);
        if (r3 == null) goto L10;
        SpannableString r12 = SpannableString.valueOf(r1);
        int r4 = r3.size();
        int r5 = 0;
    L7:
        if (r5 >= r4) goto L9;
        Object r6 = r3.get(r5);
        r5 = r5 + 1;
        CustomSpanBundler.unbundleAndApplyCustomSpan((Bundle) r6, r12);
        goto L7
    L9:
        r0.setText(r12);
    L10:
        Layout.Alignment r13 = (Layout.Alignment) r7.getSerializable(FIELD_TEXT_ALIGNMENT);
        if (r13 == null) goto L13;
        r0.setTextAlignment(r13);
    L13:
        Layout.Alignment r14 = (Layout.Alignment) r7.getSerializable(FIELD_MULTI_ROW_ALIGNMENT);
        if (r14 == null) goto L16;
        r0.setMultiRowAlignment(r14);
    L16:
        Bitmap r15 = (Bitmap) r7.getParcelable(FIELD_BITMAP_PARCELABLE);
        if (r15 == null) goto L19;
        r0.setBitmap(r15);
    L22:
        String r16 = FIELD_LINE;
        if (r7.containsKey(r16) == false) goto L27;
        String r32 = FIELD_LINE_TYPE;
        if (r7.containsKey(r32) == false) goto L27;
        r0.setLine(r7.getFloat(r16), r7.getInt(r32));
    L27:
        String r17 = FIELD_LINE_ANCHOR;
        if (r7.containsKey(r17) == false) goto L30;
        r0.setLineAnchor(r7.getInt(r17));
    L30:
        String r18 = FIELD_POSITION;
        if (r7.containsKey(r18) == false) goto L33;
        r0.setPosition(r7.getFloat(r18));
    L33:
        String r19 = FIELD_POSITION_ANCHOR;
        if (r7.containsKey(r19) == false) goto L36;
        r0.setPositionAnchor(r7.getInt(r19));
    L36:
        String r110 = FIELD_TEXT_SIZE;
        if (r7.containsKey(r110) == false) goto L41;
        String r33 = FIELD_TEXT_SIZE_TYPE;
        if (r7.containsKey(r33) == false) goto L41;
        r0.setTextSize(r7.getFloat(r110), r7.getInt(r33));
    L41:
        String r111 = FIELD_SIZE;
        if (r7.containsKey(r111) == false) goto L44;
        r0.setSize(r7.getFloat(r111));
    L44:
        String r112 = FIELD_BITMAP_HEIGHT;
        if (r7.containsKey(r112) == false) goto L47;
        r0.setBitmapHeight(r7.getFloat(r112));
    L47:
        String r113 = FIELD_WINDOW_COLOR;
        if (r7.containsKey(r113) == false) goto L51;
        r0.setWindowColor(r7.getInt(r113));
    L51:
        if (r7.getBoolean(FIELD_WINDOW_COLOR_SET, false) == true) goto L53;
        r0.clearWindowColor();
    L53:
        String r114 = FIELD_VERTICAL_TYPE;
        if (r7.containsKey(r114) == false) goto L56;
        r0.setVerticalType(r7.getInt(r114));
    L56:
        String r115 = FIELD_SHEAR_DEGREES;
        if (r7.containsKey(r115) == false) goto L59;
        r0.setShearDegrees(r7.getFloat(r115));
    L59:
        String r116 = FIELD_Z_INDEX;
        if (r7.containsKey(r116) == false) goto L63;
        r0.setZIndex(r7.getInt(r116));
    L63:
        return r0.build();
    L19:
        byte[] r117 = r7.getByteArray(FIELD_BITMAP_BYTES);
        if (r117 == null) goto L22;
        r0.setBitmap(BitmapFactory.decodeByteArray(r117, 0, r117.length));
        goto L22
    }

    private Bundle toBundleWithoutBitmap() {
        Bundle r0 = new Bundle();
        CharSequence r1 = this.text;
        if (r1 == null) goto L9;
        r0.putCharSequence(FIELD_TEXT, r1);
        CharSequence r12 = this.text;
        if ((r12 instanceof Spanned) == false) goto L9;
        ArrayList<Bundle> r13 = CustomSpanBundler.bundleCustomSpans((Spanned) r12);
        if (r13.isEmpty() == true) goto L9;
        r0.putParcelableArrayList(FIELD_CUSTOM_SPANS, r13);
    L9:
        r0.putSerializable(FIELD_TEXT_ALIGNMENT, this.textAlignment);
        r0.putSerializable(FIELD_MULTI_ROW_ALIGNMENT, this.multiRowAlignment);
        r0.putFloat(FIELD_LINE, this.line);
        r0.putInt(FIELD_LINE_TYPE, this.lineType);
        r0.putInt(FIELD_LINE_ANCHOR, this.lineAnchor);
        r0.putFloat(FIELD_POSITION, this.position);
        r0.putInt(FIELD_POSITION_ANCHOR, this.positionAnchor);
        r0.putInt(FIELD_TEXT_SIZE_TYPE, this.textSizeType);
        r0.putFloat(FIELD_TEXT_SIZE, this.textSize);
        r0.putFloat(FIELD_SIZE, this.size);
        r0.putFloat(FIELD_BITMAP_HEIGHT, this.bitmapHeight);
        r0.putBoolean(FIELD_WINDOW_COLOR_SET, this.windowColorSet);
        r0.putInt(FIELD_WINDOW_COLOR, this.windowColor);
        r0.putInt(FIELD_VERTICAL_TYPE, this.verticalType);
        r0.putFloat(FIELD_SHEAR_DEGREES, this.shearDegrees);
        r0.putInt(FIELD_Z_INDEX, this.zIndex);
        return r0;
    }

    public Builder buildUpon() {
        return new Builder(this, null);
    }

    public boolean equals(Object r5) {
        if (this != r5) goto L6;
        return true;
    L6:
        if (r5 != null) goto L8;
    L52:
        return false;
    L8:
        if (Cue.class != r5.getClass()) goto L52;
        Cue r52 = (Cue) r5;
        if (TextUtils.equals(this.text, r52.text) == false) goto L52;
        if (this.textAlignment != r52.textAlignment) goto L52;
        if (this.multiRowAlignment != r52.multiRowAlignment) goto L52;
        Bitmap r2 = this.bitmap;
        Bitmap r3 = r52.bitmap;
        if (r2 != null) goto L20;
        if (r3 != null) goto L52;
    L24:
        if (this.line != r52.line) goto L52;
        if (this.lineType != r52.lineType) goto L52;
        if (this.lineAnchor != r52.lineAnchor) goto L52;
        if (this.position != r52.position) goto L52;
        if (this.positionAnchor != r52.positionAnchor) goto L52;
        if (this.size != r52.size) goto L52;
        if (this.bitmapHeight != r52.bitmapHeight) goto L52;
        if (this.windowColorSet != r52.windowColorSet) goto L52;
        if (this.windowColor != r52.windowColor) goto L52;
        if (this.textSizeType != r52.textSizeType) goto L52;
        if (this.textSize != r52.textSize) goto L52;
        if (this.verticalType != r52.verticalType) goto L52;
        if (this.shearDegrees != r52.shearDegrees) goto L52;
        if (this.zIndex != r52.zIndex) goto L52;
        return true;
    L20:
        if (r3 == null) goto L52;
        if (r2.sameAs(r3) == false) goto L52;
        goto L52
    }

    public int hashCode() {
        return Objects.hash(new Object[]{this.text, this.textAlignment, this.multiRowAlignment, this.bitmap, Float.valueOf(this.line), Integer.valueOf(this.lineType), Integer.valueOf(this.lineAnchor), Float.valueOf(this.position), Integer.valueOf(this.positionAnchor), Float.valueOf(this.size), Float.valueOf(this.bitmapHeight), Boolean.valueOf(this.windowColorSet), Integer.valueOf(this.windowColor), Integer.valueOf(this.textSizeType), Float.valueOf(this.textSize), Integer.valueOf(this.verticalType), Float.valueOf(this.shearDegrees), Integer.valueOf(this.zIndex)});
    }

    public Bundle toBinderBasedBundle() {
        Bundle r0 = toBundleWithoutBitmap();
        Bitmap r2 = this.bitmap;
        if (r2 == null) goto L5;
        r0.putParcelable(FIELD_BITMAP_PARCELABLE, r2);
    L5:
        return r0;
    }

    public Bundle toSerializableBundle() {
        Bundle r0 = toBundleWithoutBitmap();
        if (this.bitmap == null) goto L5;
        ByteArrayOutputStream r1 = new ByteArrayOutputStream();
        Assertions.checkState(this.bitmap.compress(Bitmap.CompressFormat.PNG, 0, r1));
        r0.putByteArray(FIELD_BITMAP_BYTES, r1.toByteArray());
    L5:
        return r0;
    }
}
