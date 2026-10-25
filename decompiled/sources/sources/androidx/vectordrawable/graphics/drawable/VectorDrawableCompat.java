package androidx.vectordrawable.graphics.drawable;

import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.VectorDrawable;
import android.util.AttributeSet;
import androidx.collection.ArrayMap;
import androidx.core.content.res.ComplexColorCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.content.res.TypedArrayUtils;
import androidx.core.graphics.PathParser;
import androidx.core.graphics.drawable.DrawableCompat;
import java.util.ArrayDeque;
import java.util.ArrayList;
import okio.Segment$$ExternalSyntheticBUOutline1;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

/* loaded from: classes4.dex */
public class VectorDrawableCompat extends VectorDrawableCommon {
    static final PorterDuff.Mode DEFAULT_TINT_MODE = null;
    private boolean mAllowCaching;
    private Drawable.ConstantState mCachedConstantStateDelegate;
    private ColorFilter mColorFilter;
    private boolean mMutated;
    private PorterDuffColorFilter mTintFilter;
    private final Rect mTmpBounds;
    private final float[] mTmpFloats;
    private final Matrix mTmpMatrix;
    private VectorDrawableCompatState mVectorState;

    /* renamed from: androidx.vectordrawable.graphics.drawable.VectorDrawableCompat$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
    }

    public static class VClipPath extends VPath {
        public VClipPath() {
        }

        public VClipPath(VClipPath r1) {
            super(r1);
        }

        private void updateStateFromTypedArray(TypedArray r4, XmlPullParser r5) {
            String r1 = r4.getString(0);
            if (r1 == null) goto L5;
            this.mPathName = r1;
        L5:
            String r12 = r4.getString(1);
            if (r12 == null) goto L8;
            this.mNodes = PathParser.createNodesFromPathData(r12);
        L8:
            this.mFillRule = TypedArrayUtils.getNamedInt(r4, r5, "fillType", 2, 0);
        }

        public void inflate(Resources r2, AttributeSet r3, Resources.Theme r4, XmlPullParser r5) {
            if (TypedArrayUtils.hasAttribute(r5, "pathData") == true) goto L5;
            return;
        L5:
            TypedArray r22 = TypedArrayUtils.obtainAttributes(r2, r4, r3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_CLIP_PATH);
            updateStateFromTypedArray(r22, r5);
            r22.recycle();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VPath
        public boolean isClipPath() {
            return true;
        }
    }

    public static class VFullPath extends VPath {
        float mFillAlpha;
        ComplexColorCompat mFillColor;
        float mStrokeAlpha;
        ComplexColorCompat mStrokeColor;
        Paint.Cap mStrokeLineCap;
        Paint.Join mStrokeLineJoin;
        float mStrokeMiterlimit;
        float mStrokeWidth;
        private int[] mThemeAttrs;
        float mTrimPathEnd;
        float mTrimPathOffset;
        float mTrimPathStart;

        public VFullPath() {
            this.mStrokeWidth = 0.0f;
            this.mStrokeAlpha = 1.0f;
            this.mFillAlpha = 1.0f;
            this.mTrimPathStart = 0.0f;
            this.mTrimPathEnd = 1.0f;
            this.mTrimPathOffset = 0.0f;
            this.mStrokeLineCap = Paint.Cap.BUTT;
            this.mStrokeLineJoin = Paint.Join.MITER;
            this.mStrokeMiterlimit = 4.0f;
        }

        public VFullPath(VFullPath r3) {
            super(r3);
            this.mStrokeWidth = 0.0f;
            this.mStrokeAlpha = 1.0f;
            this.mFillAlpha = 1.0f;
            this.mTrimPathStart = 0.0f;
            this.mTrimPathEnd = 1.0f;
            this.mTrimPathOffset = 0.0f;
            this.mStrokeLineCap = Paint.Cap.BUTT;
            this.mStrokeLineJoin = Paint.Join.MITER;
            this.mStrokeMiterlimit = 4.0f;
            this.mThemeAttrs = r3.mThemeAttrs;
            this.mStrokeColor = r3.mStrokeColor;
            this.mStrokeWidth = r3.mStrokeWidth;
            this.mStrokeAlpha = r3.mStrokeAlpha;
            this.mFillColor = r3.mFillColor;
            this.mFillRule = r3.mFillRule;
            this.mFillAlpha = r3.mFillAlpha;
            this.mTrimPathStart = r3.mTrimPathStart;
            this.mTrimPathEnd = r3.mTrimPathEnd;
            this.mTrimPathOffset = r3.mTrimPathOffset;
            this.mStrokeLineCap = r3.mStrokeLineCap;
            this.mStrokeLineJoin = r3.mStrokeLineJoin;
            this.mStrokeMiterlimit = r3.mStrokeMiterlimit;
        }

        private Paint.Cap getStrokeLineCap(int r1, Paint.Cap r2) {
            if (r1 == 0) goto L13;
            if (r1 == 1) goto L11;
            if (r1 == 2) goto L9;
            return r2;
        L9:
            return Paint.Cap.SQUARE;
        L11:
            return Paint.Cap.ROUND;
        L13:
            return Paint.Cap.BUTT;
        }

        private Paint.Join getStrokeLineJoin(int r1, Paint.Join r2) {
            if (r1 == 0) goto L13;
            if (r1 == 1) goto L11;
            if (r1 == 2) goto L9;
            return r2;
        L9:
            return Paint.Join.BEVEL;
        L11:
            return Paint.Join.ROUND;
        L13:
            return Paint.Join.MITER;
        }

        private void updateStateFromTypedArray(TypedArray r8, XmlPullParser r9, Resources.Theme r10) {
            this.mThemeAttrs = null;
            if (TypedArrayUtils.hasAttribute(r9, "pathData") == true) goto L5;
            return;
        L5:
            String r0 = r8.getString(0);
            if (r0 == null) goto L8;
            this.mPathName = r0;
        L8:
            String r02 = r8.getString(2);
            if (r02 == null) goto L11;
            this.mNodes = PathParser.createNodesFromPathData(r02);
        L11:
            this.mFillColor = TypedArrayUtils.getNamedComplexColor(r8, r9, r10, "fillColor", 1, 0);
            this.mFillAlpha = TypedArrayUtils.getNamedFloat(r8, r9, "fillAlpha", 12, this.mFillAlpha);
            this.mStrokeLineCap = getStrokeLineCap(TypedArrayUtils.getNamedInt(r8, r9, "strokeLineCap", 8, -1), this.mStrokeLineCap);
            this.mStrokeLineJoin = getStrokeLineJoin(TypedArrayUtils.getNamedInt(r8, r9, "strokeLineJoin", 9, -1), this.mStrokeLineJoin);
            this.mStrokeMiterlimit = TypedArrayUtils.getNamedFloat(r8, r9, "strokeMiterLimit", 10, this.mStrokeMiterlimit);
            this.mStrokeColor = TypedArrayUtils.getNamedComplexColor(r8, r9, r10, "strokeColor", 3, 0);
            this.mStrokeAlpha = TypedArrayUtils.getNamedFloat(r8, r9, "strokeAlpha", 11, this.mStrokeAlpha);
            this.mStrokeWidth = TypedArrayUtils.getNamedFloat(r8, r9, "strokeWidth", 4, this.mStrokeWidth);
            this.mTrimPathEnd = TypedArrayUtils.getNamedFloat(r8, r9, "trimPathEnd", 6, this.mTrimPathEnd);
            this.mTrimPathOffset = TypedArrayUtils.getNamedFloat(r8, r9, "trimPathOffset", 7, this.mTrimPathOffset);
            this.mTrimPathStart = TypedArrayUtils.getNamedFloat(r8, r9, "trimPathStart", 5, this.mTrimPathStart);
            this.mFillRule = TypedArrayUtils.getNamedInt(r8, r9, "fillType", 13, this.mFillRule);
        }

        public float getFillAlpha() {
            return this.mFillAlpha;
        }

        public int getFillColor() {
            return this.mFillColor.getColor();
        }

        public float getStrokeAlpha() {
            return this.mStrokeAlpha;
        }

        public int getStrokeColor() {
            return this.mStrokeColor.getColor();
        }

        public float getStrokeWidth() {
            return this.mStrokeWidth;
        }

        public float getTrimPathEnd() {
            return this.mTrimPathEnd;
        }

        public float getTrimPathOffset() {
            return this.mTrimPathOffset;
        }

        public float getTrimPathStart() {
            return this.mTrimPathStart;
        }

        public void inflate(Resources r2, AttributeSet r3, Resources.Theme r4, XmlPullParser r5) {
            TypedArray r22 = TypedArrayUtils.obtainAttributes(r2, r4, r3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_PATH);
            updateStateFromTypedArray(r22, r5, r4);
            r22.recycle();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            if (this.mFillColor.isStateful() == false) goto L5;
            return true;
        L5:
            if (this.mStrokeColor.isStateful() == true) goto L11;
            return false;
        L11:
            return true;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] r2) {
            boolean r0 = this.mFillColor.onStateChanged(r2);
            return this.mStrokeColor.onStateChanged(r2) | r0;
        }

        public void setFillAlpha(float r1) {
            this.mFillAlpha = r1;
        }

        public void setFillColor(int r1) {
            this.mFillColor.setColor(r1);
        }

        public void setStrokeAlpha(float r1) {
            this.mStrokeAlpha = r1;
        }

        public void setStrokeColor(int r1) {
            this.mStrokeColor.setColor(r1);
        }

        public void setStrokeWidth(float r1) {
            this.mStrokeWidth = r1;
        }

        public void setTrimPathEnd(float r1) {
            this.mTrimPathEnd = r1;
        }

        public void setTrimPathOffset(float r1) {
            this.mTrimPathOffset = r1;
        }

        public void setTrimPathStart(float r1) {
            this.mTrimPathStart = r1;
        }
    }

    public static class VGroup extends VObject {
        int mChangingConfigurations;
        final ArrayList<VObject> mChildren;
        private String mGroupName;
        final Matrix mLocalMatrix;
        private float mPivotX;
        private float mPivotY;
        float mRotate;
        private float mScaleX;
        private float mScaleY;
        final Matrix mStackedMatrix;
        private int[] mThemeAttrs;
        private float mTranslateX;
        private float mTranslateY;

        public VGroup() {
            super(null);
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            this.mLocalMatrix = new Matrix();
            this.mGroupName = null;
        }

        public VGroup(VGroup r6, ArrayMap<String, Object> r7) {
            super(null);
            this.mStackedMatrix = new Matrix();
            this.mChildren = new ArrayList();
            this.mRotate = 0.0f;
            this.mPivotX = 0.0f;
            this.mPivotY = 0.0f;
            this.mScaleX = 1.0f;
            this.mScaleY = 1.0f;
            this.mTranslateX = 0.0f;
            this.mTranslateY = 0.0f;
            Matrix r1 = new Matrix();
            this.mLocalMatrix = r1;
            this.mGroupName = null;
            this.mRotate = r6.mRotate;
            this.mPivotX = r6.mPivotX;
            this.mPivotY = r6.mPivotY;
            this.mScaleX = r6.mScaleX;
            this.mScaleY = r6.mScaleY;
            this.mTranslateX = r6.mTranslateX;
            this.mTranslateY = r6.mTranslateY;
            this.mThemeAttrs = r6.mThemeAttrs;
            String r2 = r6.mGroupName;
            this.mGroupName = r2;
            this.mChangingConfigurations = r6.mChangingConfigurations;
            if (r2 == null) goto L5;
            r7.put(r2, this);
        L5:
            r1.set(r6.mLocalMatrix);
            ArrayList<VObject> r62 = r6.mChildren;
            int r12 = 0;
        L7:
            if (r12 >= r62.size()) goto L23;
            VObject r22 = r62.get(r12);
            if ((r22 instanceof VGroup) == false) goto L12;
            this.mChildren.add(new VGroup((VGroup) r22, r7));
        L20:
            r12 = r12 + 1;
            goto L7
        L12:
            if ((r22 instanceof VFullPath) == false) goto L15;
            VPath r3 = new VFullPath((VFullPath) r22);
        L17:
            this.mChildren.add(r3);
            String r23 = r3.mPathName;
            if (r23 == null) goto L20;
            r7.put(r23, r3);
            goto L20
        L15:
            if ((r22 instanceof VClipPath) == false) goto L21;
            r3 = new VClipPath((VClipPath) r22);
            goto L17
        L21:
            Segment$$ExternalSyntheticBUOutline1.m("Unknown object in the tree!");
            throw null;
        }

        private void updateLocalMatrix() {
            this.mLocalMatrix.reset();
            this.mLocalMatrix.postTranslate(-this.mPivotX, -this.mPivotY);
            this.mLocalMatrix.postScale(this.mScaleX, this.mScaleY);
            this.mLocalMatrix.postRotate(this.mRotate, 0.0f, 0.0f);
            this.mLocalMatrix.postTranslate(this.mTranslateX + this.mPivotX, this.mTranslateY + this.mPivotY);
        }

        private void updateStateFromTypedArray(TypedArray r4, XmlPullParser r5) {
            this.mThemeAttrs = null;
            this.mRotate = TypedArrayUtils.getNamedFloat(r4, r5, "rotation", 5, this.mRotate);
            this.mPivotX = r4.getFloat(1, this.mPivotX);
            this.mPivotY = r4.getFloat(2, this.mPivotY);
            this.mScaleX = TypedArrayUtils.getNamedFloat(r4, r5, "scaleX", 3, this.mScaleX);
            this.mScaleY = TypedArrayUtils.getNamedFloat(r4, r5, "scaleY", 4, this.mScaleY);
            this.mTranslateX = TypedArrayUtils.getNamedFloat(r4, r5, "translateX", 6, this.mTranslateX);
            this.mTranslateY = TypedArrayUtils.getNamedFloat(r4, r5, "translateY", 7, this.mTranslateY);
            String r42 = r4.getString(0);
            if (r42 == null) goto L5;
            this.mGroupName = r42;
        L5:
            updateLocalMatrix();
        }

        public String getGroupName() {
            return this.mGroupName;
        }

        public Matrix getLocalMatrix() {
            return this.mLocalMatrix;
        }

        public float getPivotX() {
            return this.mPivotX;
        }

        public float getPivotY() {
            return this.mPivotY;
        }

        public float getRotation() {
            return this.mRotate;
        }

        public float getScaleX() {
            return this.mScaleX;
        }

        public float getScaleY() {
            return this.mScaleY;
        }

        public float getTranslateX() {
            return this.mTranslateX;
        }

        public float getTranslateY() {
            return this.mTranslateY;
        }

        public void inflate(Resources r2, AttributeSet r3, Resources.Theme r4, XmlPullParser r5) {
            TypedArray r22 = TypedArrayUtils.obtainAttributes(r2, r4, r3, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_GROUP);
            updateStateFromTypedArray(r22, r5);
            r22.recycle();
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean isStateful() {
            int r1 = 0;
        L4:
            if (r1 >= this.mChildren.size()) goto L10;
            if (this.mChildren.get(r1).isStateful() == true) goto L7;
            r1 = r1 + 1;
            goto L4
        L7:
            return true;
        L10:
            return false;
        }

        @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCompat.VObject
        public boolean onStateChanged(int[] r4) {
            int r0 = 0;
            boolean r1 = false;
        L4:
            if (r0 >= this.mChildren.size()) goto L6;
            r1 = r1 | this.mChildren.get(r0).onStateChanged(r4);
            r0 = r0 + 1;
            goto L4
        L6:
            return r1;
        }

        public void setPivotX(float r2) {
            if (r2 == this.mPivotX) goto L6;
            this.mPivotX = r2;
            updateLocalMatrix();
            return;
        }

        public void setPivotY(float r2) {
            if (r2 == this.mPivotY) goto L6;
            this.mPivotY = r2;
            updateLocalMatrix();
            return;
        }

        public void setRotation(float r2) {
            if (r2 == this.mRotate) goto L6;
            this.mRotate = r2;
            updateLocalMatrix();
            return;
        }

        public void setScaleX(float r2) {
            if (r2 == this.mScaleX) goto L6;
            this.mScaleX = r2;
            updateLocalMatrix();
            return;
        }

        public void setScaleY(float r2) {
            if (r2 == this.mScaleY) goto L6;
            this.mScaleY = r2;
            updateLocalMatrix();
            return;
        }

        public void setTranslateX(float r2) {
            if (r2 == this.mTranslateX) goto L6;
            this.mTranslateX = r2;
            updateLocalMatrix();
            return;
        }

        public void setTranslateY(float r2) {
            if (r2 == this.mTranslateY) goto L6;
            this.mTranslateY = r2;
            updateLocalMatrix();
            return;
        }
    }

    public static abstract class VObject {
        private VObject() {
        }

        public /* synthetic */ VObject(AnonymousClass1 r1) {
            this();
        }

        public boolean isStateful() {
            return false;
        }

        public boolean onStateChanged(int[] r1) {
            return false;
        }
    }

    public static abstract class VPath extends VObject {
        int mChangingConfigurations;
        int mFillRule;
        protected PathParser.PathDataNode[] mNodes;
        String mPathName;

        public VPath() {
            super(null);
            this.mNodes = null;
            this.mFillRule = 0;
        }

        public VPath(VPath r2) {
            super(null);
            this.mNodes = null;
            this.mFillRule = 0;
            this.mPathName = r2.mPathName;
            this.mChangingConfigurations = r2.mChangingConfigurations;
            this.mNodes = PathParser.deepCopyNodes(r2.mNodes);
        }

        public PathParser.PathDataNode[] getPathData() {
            return this.mNodes;
        }

        public String getPathName() {
            return this.mPathName;
        }

        public boolean isClipPath() {
            return false;
        }

        public void setPathData(PathParser.PathDataNode[] r2) {
            if (PathParser.canMorph(this.mNodes, r2) == true) goto L6;
            this.mNodes = PathParser.deepCopyNodes(r2);
            return;
        L6:
            PathParser.updateNodes(this.mNodes, r2);
        }

        public void toPath(Path r1) {
            r1.reset();
            PathParser.PathDataNode[] r0 = this.mNodes;
            if (r0 == null) goto L6;
            PathParser.PathDataNode.nodesToPath(r0, r1);
            return;
        }
    }

    public static class VPathRenderer {
        private static final Matrix IDENTITY_MATRIX = null;
        float mBaseHeight;
        float mBaseWidth;
        private int mChangingConfigurations;
        Paint mFillPaint;
        private final Matrix mFinalPathMatrix;
        Boolean mIsStateful;
        private final Path mPath;
        private PathMeasure mPathMeasure;
        private final Path mRenderPath;
        int mRootAlpha;
        final VGroup mRootGroup;
        String mRootName;
        Paint mStrokePaint;
        final ArrayMap<String, Object> mVGTargetsMap;
        float mViewportHeight;
        float mViewportWidth;

        static {
            IDENTITY_MATRIX = new Matrix();
        }

        public VPathRenderer() {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mIsStateful = null;
            this.mVGTargetsMap = new ArrayMap();
            this.mRootGroup = new VGroup();
            this.mPath = new Path();
            this.mRenderPath = new Path();
        }

        public VPathRenderer(VPathRenderer r4) {
            this.mFinalPathMatrix = new Matrix();
            this.mBaseWidth = 0.0f;
            this.mBaseHeight = 0.0f;
            this.mViewportWidth = 0.0f;
            this.mViewportHeight = 0.0f;
            this.mRootAlpha = 255;
            this.mRootName = null;
            this.mIsStateful = null;
            ArrayMap<String, Object> r0 = new ArrayMap();
            this.mVGTargetsMap = r0;
            this.mRootGroup = new VGroup(r4.mRootGroup, r0);
            this.mPath = new Path(r4.mPath);
            this.mRenderPath = new Path(r4.mRenderPath);
            this.mBaseWidth = r4.mBaseWidth;
            this.mBaseHeight = r4.mBaseHeight;
            this.mViewportWidth = r4.mViewportWidth;
            this.mViewportHeight = r4.mViewportHeight;
            this.mChangingConfigurations = r4.mChangingConfigurations;
            this.mRootAlpha = r4.mRootAlpha;
            this.mRootName = r4.mRootName;
            String r1 = r4.mRootName;
            if (r1 == null) goto L5;
            r0.put(r1, this);
        L5:
            this.mIsStateful = r4.mIsStateful;
        }

        private static float cross(float r0, float r1, float r2, float r3) {
            return (r0 * r3) - (r1 * r2);
        }

        private void drawGroupTree(VGroup r16, Matrix r17, Canvas r18, int r19, int r20, ColorFilter r21) {
            VGroup r1 = r16;
            r1.mStackedMatrix.set(r17);
            r1.mStackedMatrix.preConcat(r1.mLocalMatrix);
            r18.save();
            int r7 = 0;
        L4:
            if (r7 >= r1.mChildren.size()) goto L12;
            VObject r0 = r1.mChildren.get(r7);
            if ((r0 instanceof VGroup) == false) goto L9;
            drawGroupTree((VGroup) r0, r1.mStackedMatrix, r18, r19, r20, r21);
        L11:
            r7 = r7 + 1;
            r1 = r16;
            goto L4
        L9:
            if ((r0 instanceof VPath) == false) goto L11;
            drawPath(r1, (VPath) r0, r18, r19, r20, r21);
            goto L11
        L12:
            r18.restore();
        }

        private void drawPath(VGroup r8, VPath r9, Canvas r10, int r11, int r12, ColorFilter r13) {
            float r112 = r11 / this.mViewportWidth;
            float r122 = r12 / this.mViewportHeight;
            float r0 = Math.min(r112, r122);
            Matrix r82 = r8.mStackedMatrix;
            this.mFinalPathMatrix.set(r82);
            this.mFinalPathMatrix.postScale(r112, r122);
            float r83 = getMatrixScale(r82);
            if (r83 == 0.0f) goto L57;
            r9.toPath(this.mPath);
            Path r123 = this.mPath;
            this.mRenderPath.reset();
            if (r9.isClipPath() == false) goto L13;
            Path r84 = this.mRenderPath;
            if (r9.mFillRule != 0) goto L10;
            Path.FillType r92 = Path.FillType.WINDING;
        L11:
            r84.setFillType(r92);
            this.mRenderPath.addPath(r123, this.mFinalPathMatrix);
            r10.clipPath(this.mRenderPath);
            return;
        L10:
            r92 = Path.FillType.EVEN_ODD;
            goto L11
        L13:
            VFullPath r93 = (VFullPath) r9;
            float r1 = r93.mTrimPathStart;
            if (r1 == 0.0f) goto L16;
        L17:
            float r2 = r93.mTrimPathOffset;
            float r14 = (r1 + r2) % 1.0f;
            float r5 = (r93.mTrimPathEnd + r2) % 1.0f;
            if (this.mPathMeasure != null) goto L20;
            this.mPathMeasure = new PathMeasure();
        L20:
            this.mPathMeasure.setPath(this.mPath, false);
            float r22 = this.mPathMeasure.getLength();
            float r15 = r14 * r22;
            float r52 = r5 * r22;
            r123.reset();
            PathMeasure r6 = this.mPathMeasure;
            if (r15 <= r52) goto L23;
            r6.getSegment(r15, r22, r123, true);
            this.mPathMeasure.getSegment(0.0f, r52, r123, true);
        L24:
            r123.rLineTo(0.0f, 0.0f);
        L25:
            this.mRenderPath.addPath(r123, this.mFinalPathMatrix);
            if (r93.mFillColor.willDraw() == false) goto L40;
            ComplexColorCompat r113 = r93.mFillColor;
            if (this.mFillPaint != null) goto L30;
            Paint r3 = new Paint(1);
            this.mFillPaint = r3;
            r3.setStyle(Paint.Style.FILL);
        L30:
            Paint r32 = this.mFillPaint;
            if (r113.isGradient() == false) goto L33;
            Shader r114 = r113.getShader();
            r114.setLocalMatrix(this.mFinalPathMatrix);
            r32.setShader(r114);
            r32.setAlpha(Math.round(r93.mFillAlpha * 255.0f));
        L34:
            r32.setColorFilter(r13);
            Path r115 = this.mRenderPath;
            if (r93.mFillRule != 0) goto L37;
            Path.FillType r53 = Path.FillType.WINDING;
        L38:
            r115.setFillType(r53);
            r10.drawPath(this.mRenderPath, r32);
            goto L40
        L37:
            r53 = Path.FillType.EVEN_ODD;
            goto L38
        L33:
            r32.setShader(null);
            r32.setAlpha(255);
            r32.setColor(VectorDrawableCompat.applyAlpha(r113.getColor(), r93.mFillAlpha));
        L40:
            if (r93.mStrokeColor.willDraw() == false) goto L56;
            ComplexColorCompat r116 = r93.mStrokeColor;
            if (this.mStrokePaint != null) goto L44;
            Paint r33 = new Paint(1);
            this.mStrokePaint = r33;
            r33.setStyle(Paint.Style.STROKE);
        L44:
            Paint r34 = this.mStrokePaint;
            Paint.Join r4 = r93.mStrokeLineJoin;
            if (r4 == null) goto L47;
            r34.setStrokeJoin(r4);
        L47:
            Paint.Cap r42 = r93.mStrokeLineCap;
            if (r42 == null) goto L50;
            r34.setStrokeCap(r42);
        L50:
            r34.setStrokeMiter(r93.mStrokeMiterlimit);
            if (r116.isGradient() == false) goto L53;
            Shader r117 = r116.getShader();
            r117.setLocalMatrix(this.mFinalPathMatrix);
            r34.setShader(r117);
            r34.setAlpha(Math.round(r93.mStrokeAlpha * 255.0f));
        L54:
            r34.setColorFilter(r13);
            r34.setStrokeWidth(r93.mStrokeWidth * (r0 * r83));
            r10.drawPath(this.mRenderPath, r34);
            return;
        L53:
            r34.setShader(null);
            r34.setAlpha(255);
            r34.setColor(VectorDrawableCompat.applyAlpha(r116.getColor(), r93.mStrokeAlpha));
            goto L54
        L56:
            return;
        L23:
            r6.getSegment(r15, r52, r123, true);
            goto L24
        L16:
            if (r93.mTrimPathEnd == 1.0f) goto L25;
        }

        private float getMatrixScale(Matrix r9) {
            float[] r8 = {0.0f, 1.0f, 1.0f, 0.0f};
            r9.mapVectors(r8);
            float r0 = (float) Math.hypot(r8[0], r8[1]);
            float r3 = (float) Math.hypot(r8[2], r8[3]);
            float r82 = cross(r8[0], r8[1], r8[2], r8[3]);
            float r92 = Math.max(r0, r3);
            if (r92 > 0.0f) goto L5;
            return 0.0f;
        L5:
            return Math.abs(r82) / r92;
        }

        public void draw(Canvas r8, int r9, int r10, ColorFilter r11) {
            drawGroupTree(this.mRootGroup, IDENTITY_MATRIX, r8, r9, r10, r11);
        }

        public float getAlpha() {
            return getRootAlpha() / 255.0f;
        }

        public int getRootAlpha() {
            return this.mRootAlpha;
        }

        public boolean isStateful() {
            if (this.mIsStateful != null) goto L6;
            this.mIsStateful = Boolean.valueOf(this.mRootGroup.isStateful());
        L6:
            return this.mIsStateful.booleanValue();
        }

        public boolean onStateChanged(int[] r1) {
            return this.mRootGroup.onStateChanged(r1);
        }

        public void setAlpha(float r2) {
            setRootAlpha((int) (r2 * 255.0f));
        }

        public void setRootAlpha(int r1) {
            this.mRootAlpha = r1;
        }
    }

    public static class VectorDrawableCompatState extends Drawable.ConstantState {
        boolean mAutoMirrored;
        boolean mCacheDirty;
        boolean mCachedAutoMirrored;
        Bitmap mCachedBitmap;
        int mCachedRootAlpha;
        ColorStateList mCachedTint;
        PorterDuff.Mode mCachedTintMode;
        int mChangingConfigurations;
        Paint mTempPaint;
        ColorStateList mTint;
        PorterDuff.Mode mTintMode;
        VPathRenderer mVPathRenderer;

        public VectorDrawableCompatState() {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            this.mVPathRenderer = new VPathRenderer();
        }

        public VectorDrawableCompatState(VectorDrawableCompatState r4) {
            this.mTint = null;
            this.mTintMode = VectorDrawableCompat.DEFAULT_TINT_MODE;
            if (r4 == null) goto L12;
            this.mChangingConfigurations = r4.mChangingConfigurations;
            VPathRenderer r0 = new VPathRenderer(r4.mVPathRenderer);
            this.mVPathRenderer = r0;
            if (r4.mVPathRenderer.mFillPaint == null) goto L8;
            r0.mFillPaint = new Paint(r4.mVPathRenderer.mFillPaint);
        L8:
            if (r4.mVPathRenderer.mStrokePaint == null) goto L10;
            VPathRenderer r02 = this.mVPathRenderer;
            r02.mStrokePaint = new Paint(r4.mVPathRenderer.mStrokePaint);
        L10:
            this.mTint = r4.mTint;
            this.mTintMode = r4.mTintMode;
            this.mAutoMirrored = r4.mAutoMirrored;
            return;
        }

        public boolean canReuseBitmap(int r2, int r3) {
            if (r2 == this.mCachedBitmap.getWidth()) goto L5;
            return false;
        L5:
            if (r3 != this.mCachedBitmap.getHeight()) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean canReuseCache() {
            if (this.mCacheDirty == false) goto L5;
            return false;
        L5:
            if (this.mCachedTint == this.mTint) goto L7;
            return false;
        L7:
            if (this.mCachedTintMode == this.mTintMode) goto L9;
            return false;
        L9:
            if (this.mCachedAutoMirrored == this.mAutoMirrored) goto L11;
            return false;
        L11:
            if (this.mCachedRootAlpha != this.mVPathRenderer.getRootAlpha()) goto L19;
            return true;
        L19:
            return false;
        }

        public void createCachedBitmapIfNeeded(int r2, int r3) {
            if (this.mCachedBitmap != null) goto L5;
        L8:
            this.mCachedBitmap = Bitmap.createBitmap(r2, r3, Bitmap.Config.ARGB_8888);
            this.mCacheDirty = true;
            return;
        L5:
            if (canReuseBitmap(r2, r3) == false) goto L8;
        }

        public void drawCachedBitmapWithRootAlpha(Canvas r2, ColorFilter r3, Rect r4) {
            Paint r32 = getPaint(r3);
            r2.drawBitmap(this.mCachedBitmap, null, r4, r32);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mChangingConfigurations;
        }

        public Paint getPaint(ColorFilter r3) {
            if (hasTranslucentRoot() == true) goto L8;
            if (r3 != null) goto L8;
            return null;
        L8:
            if (this.mTempPaint != null) goto L10;
            Paint r0 = new Paint();
            this.mTempPaint = r0;
            r0.setFilterBitmap(true);
        L10:
            this.mTempPaint.setAlpha(this.mVPathRenderer.getRootAlpha());
            this.mTempPaint.setColorFilter(r3);
            return this.mTempPaint;
        }

        public boolean hasTranslucentRoot() {
            if (this.mVPathRenderer.getRootAlpha() >= 255) goto L6;
            return true;
        L6:
            return false;
        }

        public boolean isStateful() {
            return this.mVPathRenderer.isStateful();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            return new VectorDrawableCompat(this);
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r1) {
            return new VectorDrawableCompat(this);
        }

        public boolean onStateChanged(int[] r2) {
            boolean r22 = this.mVPathRenderer.onStateChanged(r2);
            this.mCacheDirty |= r22;
            return r22;
        }

        public void updateCacheStates() {
            this.mCachedTint = this.mTint;
            this.mCachedTintMode = this.mTintMode;
            this.mCachedRootAlpha = this.mVPathRenderer.getRootAlpha();
            this.mCachedAutoMirrored = this.mAutoMirrored;
            this.mCacheDirty = false;
        }

        public void updateCachedBitmap(int r3, int r4) {
            this.mCachedBitmap.eraseColor(0);
            Canvas r0 = new Canvas(this.mCachedBitmap);
            this.mVPathRenderer.draw(r0, r3, r4, null);
        }
    }

    public static class VectorDrawableDelegateState extends Drawable.ConstantState {
        private final Drawable.ConstantState mDelegateState;

        public VectorDrawableDelegateState(Drawable.ConstantState r1) {
            this.mDelegateState = r1;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public boolean canApplyTheme() {
            return this.mDelegateState.canApplyTheme();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public int getChangingConfigurations() {
            return this.mDelegateState.getChangingConfigurations();
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable() {
            VectorDrawableCompat r0 = new VectorDrawableCompat();
            r0.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable();
            return r0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r2) {
            VectorDrawableCompat r0 = new VectorDrawableCompat();
            r0.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(r2);
            return r0;
        }

        @Override // android.graphics.drawable.Drawable.ConstantState
        public Drawable newDrawable(Resources r2, Resources.Theme r3) {
            VectorDrawableCompat r0 = new VectorDrawableCompat();
            r0.mDelegateDrawable = (VectorDrawable) this.mDelegateState.newDrawable(r2, r3);
            return r0;
        }
    }

    static {
        DEFAULT_TINT_MODE = PorterDuff.Mode.SRC_IN;
    }

    public VectorDrawableCompat() {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = new VectorDrawableCompatState();
    }

    public VectorDrawableCompat(VectorDrawableCompatState r3) {
        this.mAllowCaching = true;
        this.mTmpFloats = new float[9];
        this.mTmpMatrix = new Matrix();
        this.mTmpBounds = new Rect();
        this.mVectorState = r3;
        this.mTintFilter = updateTintFilter(this.mTintFilter, r3.mTint, r3.mTintMode);
    }

    public static int applyAlpha(int r2, float r3) {
        return (r2 & 16777215) | (((int) (Color.alpha(r2) * r3)) << 24);
    }

    public static VectorDrawableCompat create(Resources r1, int r2, Resources.Theme r3) {
        VectorDrawableCompat r0 = new VectorDrawableCompat();
        r0.mDelegateDrawable = ResourcesCompat.getDrawable(r1, r2, r3);
        r0.mCachedConstantStateDelegate = new VectorDrawableDelegateState(r0.mDelegateDrawable.getConstantState());
        return r0;
    }

    private void inflateInternal(Resources r10, XmlPullParser r11, AttributeSet r12, Resources.Theme r13) {
        VectorDrawableCompatState r9 = this.mVectorState;
        VPathRenderer r0 = r9.mVPathRenderer;
        ArrayDeque r1 = new ArrayDeque();
        r1.push(r0.mRootGroup);
        int r2 = r11.getEventType();
        int r3 = r11.getDepth() + 1;
        boolean r5 = true;
    L3:
        if (r2 == 1) goto L32;
        if (r11.getDepth() >= r3) goto L8;
        if (r2 == 3) goto L32;
    L8:
        if (r2 != 2) goto L27;
        String r22 = r11.getName();
        VGroup r6 = (VGroup) r1.peek();
        if ("path".equals(r22) == false) goto L16;
        VFullPath r23 = new VFullPath();
        r23.inflate(r10, r12, r13, r11);
        r6.mChildren.add(r23);
        if (r23.getPathName() == null) goto L14;
        r0.mVGTargetsMap.put(r23.getPathName(), r23);
    L14:
        r9.mChangingConfigurations = r23.mChangingConfigurations | r9.mChangingConfigurations;
        r5 = false;
    L31:
        r2 = r11.next();
        goto L3
    L16:
        if ("clip-path".equals(r22) == false) goto L22;
        VClipPath r24 = new VClipPath();
        r24.inflate(r10, r12, r13, r11);
        r6.mChildren.add(r24);
        if (r24.getPathName() == null) goto L20;
        r0.mVGTargetsMap.put(r24.getPathName(), r24);
    L20:
        r9.mChangingConfigurations = r24.mChangingConfigurations | r9.mChangingConfigurations;
        goto L31
    L22:
        if ("group".equals(r22) == false) goto L31;
        VGroup r25 = new VGroup();
        r25.inflate(r10, r12, r13, r11);
        r6.mChildren.add(r25);
        r1.push(r25);
        if (r25.getGroupName() == null) goto L26;
        r0.mVGTargetsMap.put(r25.getGroupName(), r25);
    L26:
        r9.mChangingConfigurations = r25.mChangingConfigurations | r9.mChangingConfigurations;
        goto L31
    L27:
        if (r2 != 3) goto L31;
        if ("group".equals(r11.getName()) == false) goto L31;
        r1.pop();
    L32:
        if (r5 == true) goto L35;
        return;
    L35:
        throw new XmlPullParserException("no path defined");
    }

    private boolean needMirroring() {
        if (isAutoMirrored() == true) goto L5;
        return false;
    L5:
        if (DrawableCompat.getLayoutDirection(this) != 1) goto L9;
        return true;
    L9:
        return false;
    }

    private static PorterDuff.Mode parseTintModeCompat(int r1, PorterDuff.Mode r2) {
        if (r1 == 3) goto L21;
        if (r1 == 5) goto L19;
        if (r1 == 9) goto L17;
        switch(r1) {
            case 14: goto L15;
            case 15: goto L13;
            case 16: goto L11;
            default: goto L9;
        };
    L9:
        return r2;
    L11:
        return PorterDuff.Mode.ADD;
    L13:
        return PorterDuff.Mode.SCREEN;
    L15:
        return PorterDuff.Mode.MULTIPLY;
    L17:
        return PorterDuff.Mode.SRC_ATOP;
    L19:
        return PorterDuff.Mode.SRC_IN;
    L21:
        return PorterDuff.Mode.SRC_OVER;
    }

    private void updateStateFromTypedArray(TypedArray r5, XmlPullParser r6, Resources.Theme r7) {
        VectorDrawableCompatState r4 = this.mVectorState;
        VPathRenderer r0 = r4.mVPathRenderer;
        r4.mTintMode = parseTintModeCompat(TypedArrayUtils.getNamedInt(r5, r6, "tintMode", 6, -1), PorterDuff.Mode.SRC_IN);
        ColorStateList r72 = TypedArrayUtils.getNamedColorStateList(r5, r6, r7, "tint", 1);
        if (r72 == null) goto L5;
        r4.mTint = r72;
    L5:
        r4.mAutoMirrored = TypedArrayUtils.getNamedBoolean(r5, r6, "autoMirrored", 5, r4.mAutoMirrored);
        r0.mViewportWidth = TypedArrayUtils.getNamedFloat(r5, r6, "viewportWidth", 7, r0.mViewportWidth);
        float r42 = TypedArrayUtils.getNamedFloat(r5, r6, "viewportHeight", 8, r0.mViewportHeight);
        r0.mViewportHeight = r42;
        if (r0.mViewportWidth <= 0.0f) goto L24;
        if (r42 <= 0.0f) goto L22;
        r0.mBaseWidth = r5.getDimension(3, r0.mBaseWidth);
        float r43 = r5.getDimension(2, r0.mBaseHeight);
        r0.mBaseHeight = r43;
        if (r0.mBaseWidth <= 0.0f) goto L20;
        if (r43 <= 0.0f) goto L18;
        r0.setAlpha(TypedArrayUtils.getNamedFloat(r5, r6, "alpha", 4, r0.getAlpha()));
        String r44 = r5.getString(0);
        if (r44 == null) goto L25;
        r0.mRootName = r44;
        r0.mVGTargetsMap.put(r44, r0);
        return;
    L25:
        return;
    L18:
        throw new XmlPullParserException(r5.getPositionDescription() + "<vector> tag requires height > 0");
    L20:
        throw new XmlPullParserException(r5.getPositionDescription() + "<vector> tag requires width > 0");
    L22:
        throw new XmlPullParserException(r5.getPositionDescription() + "<vector> tag requires viewportHeight > 0");
    L24:
        throw new XmlPullParserException(r5.getPositionDescription() + "<vector> tag requires viewportWidth > 0");
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void applyTheme(Resources.Theme r1) {
        super.applyTheme(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public boolean canApplyTheme() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        DrawableCompat.canApplyTheme(r0);
        return false;
    L7:
        return false;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void clearColorFilter() {
        super.clearColorFilter();
    }

    @Override // android.graphics.drawable.Drawable
    public void draw(Canvas r10) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.draw(r10);
        return;
    L6:
        copyBounds(this.mTmpBounds);
        if (this.mTmpBounds.width() > 0) goto L9;
        return;
    L9:
        if (this.mTmpBounds.height() <= 0) goto L36;
        ColorFilter r02 = this.mColorFilter;
        if (r02 != null) goto L14;
        r02 = this.mTintFilter;
    L14:
        r10.getMatrix(this.mTmpMatrix);
        this.mTmpMatrix.getValues(this.mTmpFloats);
        float r1 = Math.abs(this.mTmpFloats[0]);
        float r3 = Math.abs(this.mTmpFloats[4]);
        float r4 = Math.abs(this.mTmpFloats[1]);
        float r5 = Math.abs(this.mTmpFloats[3]);
        if (r4 == 0.0f) goto L17;
    L18:
        r1 = 1.0f;
        r3 = 1.0f;
    L19:
        int r12 = Math.min(2048, (int) (this.mTmpBounds.width() * r1));
        int r32 = Math.min(2048, (int) (this.mTmpBounds.height() * r3));
        if (r12 <= 0) goto L35;
        if (r32 <= 0) goto L37;
        int r42 = r10.save();
        Rect r52 = this.mTmpBounds;
        r10.translate(r52.left, r52.top);
        if (needMirroring() == false) goto L26;
        r10.translate(this.mTmpBounds.width(), 0.0f);
        r10.scale(-1.0f, 1.0f);
    L26:
        this.mTmpBounds.offsetTo(0, 0);
        this.mVectorState.createCachedBitmapIfNeeded(r12, r32);
        boolean r2 = this.mAllowCaching;
        VectorDrawableCompatState r53 = this.mVectorState;
        if (r2 == true) goto L30;
        r53.updateCachedBitmap(r12, r32);
    L32:
        this.mVectorState.drawCachedBitmapWithRootAlpha(r10, r02, this.mTmpBounds);
        r10.restoreToCount(r42);
        return;
    L30:
        if (r53.canReuseCache() == true) goto L32;
        this.mVectorState.updateCachedBitmap(r12, r32);
        this.mVectorState.updateCacheStates();
        goto L32
    L37:
        return;
    L35:
        return;
    L17:
        if (r5 == 0.0f) goto L19;
    }

    @Override // android.graphics.drawable.Drawable
    public int getAlpha() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.getAlpha(r0);
    L7:
        return this.mVectorState.mVPathRenderer.getRootAlpha();
    }

    @Override // android.graphics.drawable.Drawable
    public int getChangingConfigurations() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        int r02 = super.getChangingConfigurations();
        return this.mVectorState.getChangingConfigurations() | r02;
    L5:
        return r0.getChangingConfigurations();
    }

    @Override // android.graphics.drawable.Drawable
    public ColorFilter getColorFilter() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.getColorFilter(r0);
    L7:
        return this.mColorFilter;
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable.ConstantState getConstantState() {
        if (this.mDelegateDrawable != null) goto L5;
        VectorDrawableCompatState r0 = this.mVectorState;
        r0.mChangingConfigurations = getChangingConfigurations();
        return this.mVectorState;
    L5:
        return new VectorDrawableDelegateState(this.mDelegateDrawable.getConstantState());
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Drawable getCurrent() {
        return super.getCurrent();
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicHeight() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicHeight();
    L7:
        return (int) this.mVectorState.mVPathRenderer.mBaseHeight;
    }

    @Override // android.graphics.drawable.Drawable
    public int getIntrinsicWidth() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.getIntrinsicWidth();
    L7:
        return (int) this.mVectorState.mVPathRenderer.mBaseWidth;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumHeight() {
        return super.getMinimumHeight();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int getMinimumWidth() {
        return super.getMinimumWidth();
    }

    @Override // android.graphics.drawable.Drawable
    public int getOpacity() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        return -3;
    L5:
        return r0.getOpacity();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean getPadding(Rect r1) {
        return super.getPadding(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ int[] getState() {
        return super.getState();
    }

    public Object getTargetByName(String r1) {
        return this.mVectorState.mVPathRenderer.mVGTargetsMap.get(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ Region getTransparentRegion() {
        return super.getTransparentRegion();
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r2, XmlPullParser r3, AttributeSet r4) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.inflate(r2, r3, r4);
        return;
    L6:
        inflate(r2, r3, r4, null);
    }

    @Override // android.graphics.drawable.Drawable
    public void inflate(Resources r3, XmlPullParser r4, AttributeSet r5, Resources.Theme r6) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.inflate(r0, r3, r4, r5, r6);
        return;
    L6:
        VectorDrawableCompatState r02 = this.mVectorState;
        r02.mVPathRenderer = new VPathRenderer();
        TypedArray r1 = TypedArrayUtils.obtainAttributes(r3, r6, r5, AndroidResources.STYLEABLE_VECTOR_DRAWABLE_TYPE_ARRAY);
        updateStateFromTypedArray(r1, r4, r6);
        r1.recycle();
        r02.mChangingConfigurations = getChangingConfigurations();
        r02.mCacheDirty = true;
        inflateInternal(r3, r4, r5, r6);
        this.mTintFilter = updateTintFilter(this.mTintFilter, r02.mTint, r02.mTintMode);
    }

    @Override // android.graphics.drawable.Drawable
    public void invalidateSelf() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.invalidateSelf();
        return;
    L6:
        super.invalidateSelf();
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isAutoMirrored() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return DrawableCompat.isAutoMirrored(r0);
    L7:
        return this.mVectorState.mAutoMirrored;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean isStateful() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.isStateful();
    L7:
        if (super.isStateful() == true) goto L19;
        VectorDrawableCompatState r02 = this.mVectorState;
        if (r02 != null) goto L11;
        return false;
    L11:
        if (r02.isStateful() == true) goto L23;
        ColorStateList r1 = this.mVectorState.mTint;
        if (r1 != null) goto L15;
        return false;
    L15:
        if (r1.isStateful() == true) goto L24;
        return false;
    L24:
        return true;
    L23:
        return true;
    L19:
        return true;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void jumpToCurrentState() {
        super.jumpToCurrentState();
    }

    @Override // android.graphics.drawable.Drawable
    public Drawable mutate() {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        r0.mutate();
        return this;
    L7:
        if (this.mMutated == false) goto L9;
    L11:
        return this;
    L9:
        if (super.mutate() != this) goto L11;
        this.mVectorState = new VectorDrawableCompatState(this.mVectorState);
        this.mMutated = true;
        goto L11
    }

    @Override // android.graphics.drawable.Drawable
    public void onBoundsChange(Rect r1) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setBounds(r1);
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean onStateChange(int[] r6) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 != null) goto L5;
        VectorDrawableCompatState r02 = this.mVectorState;
        ColorStateList r1 = r02.mTint;
        if (r1 == null) goto L11;
        PorterDuff.Mode r3 = r02.mTintMode;
        if (r3 == null) goto L11;
        this.mTintFilter = updateTintFilter(this.mTintFilter, r1, r3);
        invalidateSelf();
        boolean r12 = true;
    L13:
        if (r02.isStateful() == true) goto L15;
    L18:
        return r12;
    L15:
        if (r02.onStateChanged(r6) == false) goto L18;
        invalidateSelf();
        return true;
    L11:
        r12 = false;
        goto L13
    L5:
        return r0.setState(r6);
    }

    @Override // android.graphics.drawable.Drawable
    public void scheduleSelf(Runnable r2, long r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.scheduleSelf(r2, r3);
        return;
    L6:
        super.scheduleSelf(r2, r3);
    }

    public void setAllowCaching(boolean r1) {
        this.mAllowCaching = r1;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAlpha(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        r0.setAlpha(r2);
        return;
    L7:
        if (this.mVectorState.mVPathRenderer.getRootAlpha() == r2) goto L10;
        this.mVectorState.mVPathRenderer.setRootAlpha(r2);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setAutoMirrored(boolean r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setAutoMirrored(r0, r2);
        return;
    L6:
        this.mVectorState.mAutoMirrored = r2;
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setChangingConfigurations(int r1) {
        super.setChangingConfigurations(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setColorFilter(int r1, PorterDuff.Mode r2) {
        super.setColorFilter(r1, r2);
    }

    @Override // android.graphics.drawable.Drawable
    public void setColorFilter(ColorFilter r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.setColorFilter(r2);
        return;
    L6:
        this.mColorFilter = r2;
        invalidateSelf();
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setFilterBitmap(boolean r1) {
        super.setFilterBitmap(r1);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspot(float r1, float r2) {
        super.setHotspot(r1, r2);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ void setHotspotBounds(int r1, int r2, int r3, int r4) {
        super.setHotspotBounds(r1, r2, r3, r4);
    }

    @Override // androidx.vectordrawable.graphics.drawable.VectorDrawableCommon, android.graphics.drawable.Drawable
    public /* bridge */ /* synthetic */ boolean setState(int[] r1) {
        return super.setState(r1);
    }

    @Override // android.graphics.drawable.Drawable
    public void setTint(int r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTint(r0, r2);
        return;
    L6:
        setTintList(ColorStateList.valueOf(r2));
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintList(ColorStateList r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTintList(r0, r3);
        return;
    L6:
        VectorDrawableCompatState r02 = this.mVectorState;
        if (r02.mTint == r3) goto L10;
        r02.mTint = r3;
        this.mTintFilter = updateTintFilter(this.mTintFilter, r3, r02.mTintMode);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public void setTintMode(PorterDuff.Mode r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        DrawableCompat.setTintMode(r0, r3);
        return;
    L6:
        VectorDrawableCompatState r02 = this.mVectorState;
        if (r02.mTintMode == r3) goto L10;
        r02.mTintMode = r3;
        this.mTintFilter = updateTintFilter(this.mTintFilter, r02.mTint, r3);
        invalidateSelf();
        return;
    }

    @Override // android.graphics.drawable.Drawable
    public boolean setVisible(boolean r2, boolean r3) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L7;
        return r0.setVisible(r2, r3);
    L7:
        return super.setVisible(r2, r3);
    }

    @Override // android.graphics.drawable.Drawable
    public void unscheduleSelf(Runnable r2) {
        Drawable r0 = this.mDelegateDrawable;
        if (r0 == null) goto L6;
        r0.unscheduleSelf(r2);
        return;
    L6:
        super.unscheduleSelf(r2);
    }

    public PorterDuffColorFilter updateTintFilter(PorterDuffColorFilter r1, ColorStateList r2, PorterDuff.Mode r3) {
        if (r2 == null) goto L7;
        if (r3 != null) goto L6;
        return null;
    L6:
        return new PorterDuffColorFilter(r2.getColorForState(getState(), 0), r3);
    L7:
        return null;
    }
}
