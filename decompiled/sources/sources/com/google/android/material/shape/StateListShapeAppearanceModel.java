package com.google.android.material.shape;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.util.AttributeSet;
import android.util.StateSet;
import android.util.Xml;
import com.exteragram.messenger.plugins.PluginsConstants;
import com.google.android.material.R;
import com.google.android.material.shape.ShapeAppearanceModel;
import java.util.Objects;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes5.dex */
public class StateListShapeAppearanceModel implements ShapeAppearance {
    private static final int INITIAL_CAPACITY = 10;
    final StateListCornerSize bottomLeftCornerSizeOverride;
    final StateListCornerSize bottomRightCornerSizeOverride;
    final ShapeAppearanceModel defaultShape;
    final ShapeAppearanceModel[] shapeAppearanceModels;
    final int stateCount;
    final int[][] stateSpecs;
    final StateListCornerSize topLeftCornerSizeOverride;
    final StateListCornerSize topRightCornerSizeOverride;

    /* renamed from: com.google.android.material.shape.StateListShapeAppearanceModel$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static final class Builder {
        private StateListCornerSize bottomLeftCornerSizeOverride;
        private StateListCornerSize bottomRightCornerSizeOverride;
        private ShapeAppearanceModel defaultShape;
        private ShapeAppearanceModel[] shapeAppearanceModels;
        private int stateCount;
        private int[][] stateSpecs;
        private StateListCornerSize topLeftCornerSizeOverride;
        private StateListCornerSize topRightCornerSizeOverride;

        private Builder(Context r5, int r6) {
            initialize();
            XmlResourceParser r62 = r5.getResources().getXml(r6);     // Catch: Throwable -> L27
            AttributeSet r0 = Xml.asAttributeSet(r62);     // Catch: Throwable -> L15
        L5:
            int r1 = r62.next();     // Catch: Throwable -> L15
            if (r1 == 2) goto L10;
            if (r1 != 1) goto L5;
        L10:
            if (r1 != 2) goto L20;
            if (r62.getName().equals(PluginsConstants.Settings.TYPE_SELECTOR) == false) goto L17;
            StateListShapeAppearanceModel.access$000(this, r5, r62, r0, r5.getTheme());     // Catch: Throwable -> L15
        L17:
            r62.close();     // Catch: Throwable -> L27 Throwable -> L27 Throwable -> L27
            return;
        L20:
            throw new XmlPullParserException("No start tag found");     // Catch: Throwable -> L15
        L15:
            th = move-exception;
            if (r62 != null) goto L30;
        L26:
            throw th;     // Catch: Throwable -> L27 Throwable -> L27 Throwable -> L27
        L30:
            r62.close();     // Catch: Throwable -> L24
        L24:
            th = move-exception;
            th.addSuppressed(th);     // Catch: Throwable -> L27 Throwable -> L27 Throwable -> L27
        L27:
            initialize();
        }

        public /* synthetic */ Builder(Context r1, int r2, AnonymousClass1 r3) {
            this(r1, r2);
        }

        public Builder(ShapeAppearanceModel r2) {
            initialize();
            addStateShapeAppearanceModel(StateSet.WILD_CARD, r2);
        }

        public Builder(StateListShapeAppearanceModel r5) {
            int r0 = r5.stateCount;
            this.stateCount = r0;
            this.defaultShape = r5.defaultShape;
            int[][] r1 = r5.stateSpecs;
            int[][] r2 = new int[r1.length][];
            this.stateSpecs = r2;
            this.shapeAppearanceModels = new ShapeAppearanceModel[r5.shapeAppearanceModels.length];
            System.arraycopy(r1, 0, r2, 0, r0);
            System.arraycopy(r5.shapeAppearanceModels, 0, this.shapeAppearanceModels, 0, this.stateCount);
            this.topLeftCornerSizeOverride = r5.topLeftCornerSizeOverride;
            this.topRightCornerSizeOverride = r5.topRightCornerSizeOverride;
            this.bottomLeftCornerSizeOverride = r5.bottomLeftCornerSizeOverride;
            this.bottomRightCornerSizeOverride = r5.bottomRightCornerSizeOverride;
        }

        public static /* synthetic */ StateListCornerSize access$1000(Builder r0) {
            return r0.bottomRightCornerSizeOverride;
        }

        public static /* synthetic */ int access$300(Builder r0) {
            return r0.stateCount;
        }

        public static /* synthetic */ ShapeAppearanceModel access$400(Builder r0) {
            return r0.defaultShape;
        }

        public static /* synthetic */ int[][] access$500(Builder r0) {
            return r0.stateSpecs;
        }

        public static /* synthetic */ ShapeAppearanceModel[] access$600(Builder r0) {
            return r0.shapeAppearanceModels;
        }

        public static /* synthetic */ StateListCornerSize access$700(Builder r0) {
            return r0.topLeftCornerSizeOverride;
        }

        public static /* synthetic */ StateListCornerSize access$800(Builder r0) {
            return r0.topRightCornerSizeOverride;
        }

        public static /* synthetic */ StateListCornerSize access$900(Builder r0) {
            return r0.bottomLeftCornerSizeOverride;
        }

        private void growArray(int r4, int r5) {
            int[][] r0 = new int[r5][];
            System.arraycopy(this.stateSpecs, 0, r0, 0, r4);
            this.stateSpecs = r0;
            ShapeAppearanceModel[] r52 = new ShapeAppearanceModel[r5];
            System.arraycopy(this.shapeAppearanceModels, 0, r52, 0, r4);
            this.shapeAppearanceModels = r52;
        }

        private void initialize() {
            this.defaultShape = new ShapeAppearanceModel();
            this.stateSpecs = new int[10][];
            this.shapeAppearanceModels = new ShapeAppearanceModel[10];
        }

        public Builder addStateShapeAppearanceModel(int[] r3, ShapeAppearanceModel r4) {
            int r0 = this.stateCount;
            if (r0 != 0) goto L5;
        L6:
            this.defaultShape = r4;
        L8:
            if (r0 < this.stateSpecs.length) goto L10;
            growArray(r0, r0 + 10);
        L10:
            int[][] r02 = this.stateSpecs;
            int r1 = this.stateCount;
            r02[r1] = r3;
            this.shapeAppearanceModels[r1] = r4;
            this.stateCount = r1 + 1;
            return this;
        L5:
            if (r3.length != 0) goto L8;
            goto L6
        }

        public StateListShapeAppearanceModel build() {
            if (this.stateCount != 0) goto L6;
            return null;
        L6:
            return new StateListShapeAppearanceModel(this, null);
        }

        public Builder setCornerSizeOverride(StateListCornerSize r2, int r3) {
            if (ShapeAppearanceModel.containsFlag(r3, 1) == false) goto L6;
            this.topLeftCornerSizeOverride = r2;
        L6:
            if (ShapeAppearanceModel.containsFlag(r3, 2) == false) goto L9;
            this.topRightCornerSizeOverride = r2;
        L9:
            if (ShapeAppearanceModel.containsFlag(r3, 4) == false) goto L12;
            this.bottomLeftCornerSizeOverride = r2;
        L12:
            if (ShapeAppearanceModel.containsFlag(r3, 8) == false) goto L14;
            this.bottomRightCornerSizeOverride = r2;
        L14:
            return this;
        }

        public Builder withTransformedCornerSizes(ShapeAppearanceModel.CornerSizeUnaryOperator r4) {
            ShapeAppearanceModel[] r0 = new ShapeAppearanceModel[this.shapeAppearanceModels.length];
            int r1 = 0;
        L4:
            if (r1 >= this.stateCount) goto L6;
            r0[r1] = this.shapeAppearanceModels[r1].withTransformedCornerSizes(r4);
            r1 = r1 + 1;
            goto L4
        L6:
            this.shapeAppearanceModels = r0;
            StateListCornerSize r02 = this.topLeftCornerSizeOverride;
            if (r02 == null) goto L9;
            this.topLeftCornerSizeOverride = r02.withTransformedCornerSizes(r4);
        L9:
            StateListCornerSize r03 = this.topRightCornerSizeOverride;
            if (r03 == null) goto L12;
            this.topRightCornerSizeOverride = r03.withTransformedCornerSizes(r4);
        L12:
            StateListCornerSize r04 = this.bottomLeftCornerSizeOverride;
            if (r04 == null) goto L15;
            this.bottomLeftCornerSizeOverride = r04.withTransformedCornerSizes(r4);
        L15:
            StateListCornerSize r05 = this.bottomRightCornerSizeOverride;
            if (r05 == null) goto L18;
            this.bottomRightCornerSizeOverride = r05.withTransformedCornerSizes(r4);
        L18:
            return this;
        }
    }

    private StateListShapeAppearanceModel(Builder r2) {
        this.stateCount = Builder.access$300(r2);
        this.defaultShape = Builder.access$400(r2);
        this.stateSpecs = Builder.access$500(r2);
        this.shapeAppearanceModels = Builder.access$600(r2);
        this.topLeftCornerSizeOverride = Builder.access$700(r2);
        this.topRightCornerSizeOverride = Builder.access$800(r2);
        this.bottomLeftCornerSizeOverride = Builder.access$900(r2);
        this.bottomRightCornerSizeOverride = Builder.access$1000(r2);
    }

    public /* synthetic */ StateListShapeAppearanceModel(Builder r1, AnonymousClass1 r2) {
        this(r1);
    }

    public static /* synthetic */ void access$000(Builder r0, Context r1, XmlPullParser r2, AttributeSet r3, Resources.Theme r4) {
        loadShapeAppearanceModelsFromItems(r0, r1, r2, r3, r4);
    }

    public static StateListShapeAppearanceModel create(Context r2, TypedArray r3, int r4) {
        int r32 = r3.getResourceId(r4, 0);
        if (r32 != 0) goto L6;
        return null;
    L6:
        if (Objects.equals(r2.getResources().getResourceTypeName(r32), "xml") == true) goto L9;
        return null;
    L9:
        return new Builder(r2, r32, null).build();
    }

    private int indexOfStateSet(int[] r4) {
        int[][] r0 = this.stateSpecs;
        int r1 = 0;
    L4:
        if (r1 >= this.stateCount) goto L9;
        if (StateSet.stateSetMatches(r0[r1], r4) == true) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return r1;
    L9:
        return -1;
    }

    private static void loadShapeAppearanceModelsFromItems(Builder r11, Context r12, XmlPullParser r13, AttributeSet r14, Resources.Theme r15) {
        int r0 = r13.getDepth() + 1;
    L3:
        int r2 = r13.next();
        if (r2 == 1) goto L32;
        int r3 = r13.getDepth();
        if (r3 >= r0) goto L10;
        if (r2 != 3) goto L10;
        return;
    L10:
        if (r2 != 2) goto L3;
        if (r3 > r0) goto L3;
        if (r13.getName().equals(PluginsConstants.Settings.ITEM) == false) goto L3;
        Resources r22 = r12.getResources();
        if (r15 != null) goto L18;
        TypedArray r23 = r22.obtainAttributes(r14, R.styleable.MaterialShape);
    L19:
        ShapeAppearanceModel r4 = ShapeAppearanceModel.builder(r12, r23.getResourceId(R.styleable.MaterialShape_shapeAppearance, 0), r23.getResourceId(R.styleable.MaterialShape_shapeAppearanceOverlay, 0)).build();
        r23.recycle();
        int r24 = r14.getAttributeCount();
        int[] r5 = new int[r24];
        int r6 = 0;
        int r7 = 0;
    L20:
        if (r6 >= r24) goto L31;
        int r8 = r14.getAttributeNameResource(r6);
        if (r8 == R.attr.shapeAppearance) goto L30;
        if (r8 == R.attr.shapeAppearanceOverlay) goto L30;
        int r9 = r7 + 1;
        if (r14.getAttributeBooleanValue(r6, false) == true) goto L29;
        r8 = -r8;
    L29:
        r5[r7] = r8;
        r7 = r9;
    L30:
        r6 = r6 + 1;
        goto L20
    L31:
        r11.addStateShapeAppearanceModel(StateSet.trimStateSet(r5, r7), r4);
        goto L3
    L18:
        r23 = r15.obtainStyledAttributes(r14, R.styleable.MaterialShape, 0, 0);
        goto L19
    }

    public static int swapCornerPositionRtl(int r1) {
        int r0 = r1 & 5;
        int r12 = (r1 & 10) >> 1;
        return r12 | (r0 << 1);
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel getDefaultShape() {
        return getDefaultShape(true);
    }

    public ShapeAppearanceModel getDefaultShape(boolean r2) {
        if (r2 == false) goto L27;
        if (this.topLeftCornerSizeOverride == null) goto L6;
    L12:
        ShapeAppearanceModel.Builder r22 = this.defaultShape.toBuilder();
        StateListCornerSize r0 = this.topLeftCornerSizeOverride;
        if (r0 == null) goto L15;
        r22.setTopLeftCornerSize(r0.getDefaultCornerSize());
    L15:
        StateListCornerSize r02 = this.topRightCornerSizeOverride;
        if (r02 == null) goto L18;
        r22.setTopRightCornerSize(r02.getDefaultCornerSize());
    L18:
        StateListCornerSize r03 = this.bottomLeftCornerSizeOverride;
        if (r03 == null) goto L21;
        r22.setBottomLeftCornerSize(r03.getDefaultCornerSize());
    L21:
        StateListCornerSize r1 = this.bottomRightCornerSizeOverride;
        if (r1 == null) goto L25;
        r22.setBottomRightCornerSize(r1.getDefaultCornerSize());
    L25:
        return r22.build();
    L6:
        if (this.topRightCornerSizeOverride != null) goto L12;
        if (this.bottomLeftCornerSizeOverride != null) goto L12;
        if (this.bottomRightCornerSizeOverride != null) goto L12;
    L27:
        return this.defaultShape;
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel[] getShapeAppearanceModels() {
        return this.shapeAppearanceModels;
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel getShapeForState(int[] r3) {
        int r0 = indexOfStateSet(r3);
        if (r0 >= 0) goto L6;
        r0 = indexOfStateSet(StateSet.WILD_CARD);
    L6:
        if (this.topLeftCornerSizeOverride == null) goto L8;
    L15:
        ShapeAppearanceModel.Builder r02 = this.shapeAppearanceModels[r0].toBuilder();
        StateListCornerSize r1 = this.topLeftCornerSizeOverride;
        if (r1 == null) goto L18;
        r02.setTopLeftCornerSize(r1.getCornerSizeForState(r3));
    L18:
        StateListCornerSize r12 = this.topRightCornerSizeOverride;
        if (r12 == null) goto L21;
        r02.setTopRightCornerSize(r12.getCornerSizeForState(r3));
    L21:
        StateListCornerSize r13 = this.bottomLeftCornerSizeOverride;
        if (r13 == null) goto L24;
        r02.setBottomLeftCornerSize(r13.getCornerSizeForState(r3));
    L24:
        StateListCornerSize r2 = this.bottomRightCornerSizeOverride;
        if (r2 == null) goto L28;
        r02.setBottomRightCornerSize(r2.getCornerSizeForState(r3));
    L28:
        return r02.build();
    L8:
        if (this.topRightCornerSizeOverride != null) goto L15;
        if (this.bottomLeftCornerSizeOverride != null) goto L15;
        if (this.bottomRightCornerSizeOverride != null) goto L15;
        return this.shapeAppearanceModels[r0];
    }

    public int getStateCount() {
        return this.stateCount;
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public boolean isStateful() {
        if (this.stateCount > 1) goto L23;
        StateListCornerSize r0 = this.topLeftCornerSizeOverride;
        if (r0 != null) goto L7;
    L8:
        StateListCornerSize r02 = this.topRightCornerSizeOverride;
        if (r02 != null) goto L11;
    L12:
        StateListCornerSize r03 = this.bottomLeftCornerSizeOverride;
        if (r03 != null) goto L15;
    L16:
        StateListCornerSize r2 = this.bottomRightCornerSizeOverride;
        if (r2 != null) goto L19;
        return false;
    L19:
        if (r2.isStateful() == true) goto L23;
        return false;
    L15:
        if (r03.isStateful() == true) goto L23;
    L11:
        if (r02.isStateful() == true) goto L23;
    L7:
        if (r0.isStateful() == false) goto L8;
    L23:
        return true;
    }

    public Builder toBuilder() {
        return new Builder(this);
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel withCornerSize(float r1) {
        return getDefaultShape().withCornerSize(r1);
    }

    @Override // com.google.android.material.shape.ShapeAppearance
    public ShapeAppearanceModel withCornerSize(CornerSize r1) {
        return getDefaultShape().withCornerSize(r1);
    }

    public StateListShapeAppearanceModel withTransformedCornerSizes(ShapeAppearanceModel.CornerSizeUnaryOperator r1) {
        return toBuilder().withTransformedCornerSizes(r1).build();
    }
}
