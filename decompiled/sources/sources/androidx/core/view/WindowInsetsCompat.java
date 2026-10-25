package androidx.core.view;

import android.annotation.SuppressLint;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Build;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.WindowInsets;
import androidx.core.graphics.Insets;
import androidx.core.util.ObjectsCompat;
import androidx.core.util.Preconditions;
import c.f$$ExternalSyntheticBUOutline1;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import kotlin.CharCodeKt$$ExternalSyntheticBUOutline0;
import okio.ByteString$$ExternalSyntheticBUOutline0;

/* loaded from: classes.dex */
public class WindowInsetsCompat {
    public static final WindowInsetsCompat CONSUMED = null;
    private final Impl mImpl;

    public static final class Builder {
        private final BuilderImpl mImpl;

        public Builder() {
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 36) goto L7;
            this.mImpl = new BuilderImpl36();
            return;
        L7:
            if (r0 < 35) goto L11;
            this.mImpl = new BuilderImpl35();
            return;
        L11:
            if (r0 < 34) goto L15;
            this.mImpl = new BuilderImpl34();
            return;
        L15:
            if (r0 < 31) goto L19;
            this.mImpl = new BuilderImpl31();
            return;
        L19:
            if (r0 < 30) goto L23;
            this.mImpl = new BuilderImpl30();
            return;
        L23:
            if (r0 < 29) goto L26;
            this.mImpl = new BuilderImpl29();
            return;
        L26:
            this.mImpl = new BuilderImpl20();
        }

        public Builder(WindowInsetsCompat r3) {
            int r0 = Build.VERSION.SDK_INT;
            if (r0 < 36) goto L7;
            this.mImpl = new BuilderImpl36(r3);
            return;
        L7:
            if (r0 < 35) goto L11;
            this.mImpl = new BuilderImpl35(r3);
            return;
        L11:
            if (r0 < 34) goto L15;
            this.mImpl = new BuilderImpl34(r3);
            return;
        L15:
            if (r0 < 31) goto L19;
            this.mImpl = new BuilderImpl31(r3);
            return;
        L19:
            if (r0 < 30) goto L23;
            this.mImpl = new BuilderImpl30(r3);
            return;
        L23:
            if (r0 < 29) goto L26;
            this.mImpl = new BuilderImpl29(r3);
            return;
        L26:
            this.mImpl = new BuilderImpl20(r3);
        }

        public WindowInsetsCompat build() {
            return this.mImpl.build();
        }

        public Builder setInsets(int r2, Insets r3) {
            this.mImpl.setInsets(r2, r3);
            return this;
        }

        public Builder setInsetsIgnoringVisibility(int r2, Insets r3) {
            this.mImpl.setInsetsIgnoringVisibility(r2, r3);
            return this;
        }

        @Deprecated
        public Builder setStableInsets(Insets r2) {
            this.mImpl.setStableInsets(r2);
            return this;
        }

        @Deprecated
        public Builder setSystemWindowInsets(Insets r2) {
            this.mImpl.setSystemWindowInsets(r2);
            return this;
        }
    }

    public static class BuilderImpl {
        DisplayShapeCompat mDisplayShapeCompat;
        private final WindowInsetsCompat mInsets;
        Insets[] mInsetsTypeMask;
        Rect[][] mTypeBoundingRectsMap;
        Rect[][] mTypeMaxBoundingRectsMap;

        public BuilderImpl() {
            this(new WindowInsetsCompat(null));
        }

        public BuilderImpl(WindowInsetsCompat r3) {
            this.mTypeBoundingRectsMap = new Rect[10][];
            this.mTypeMaxBoundingRectsMap = new Rect[10][];
            this.mInsets = r3;
            initTypeBoundingRects(r3);
        }

        public final void applyInsetTypes() {
            Insets[] r0 = this.mInsetsTypeMask;
            if (r0 == null) goto L19;
            Insets r02 = r0[Type.indexOf(1)];
            Insets r2 = this.mInsetsTypeMask[Type.indexOf(2)];
            if (r2 != null) goto L7;
            r2 = this.mInsets.getInsets(2);
        L7:
            if (r02 != null) goto L9;
            r02 = this.mInsets.getInsets(1);
        L9:
            setSystemWindowInsets(Insets.max(r02, r2));
            Insets r03 = this.mInsetsTypeMask[Type.indexOf(16)];
            if (r03 == null) goto L12;
            setSystemGestureInsets(r03);
        L12:
            Insets r04 = this.mInsetsTypeMask[Type.indexOf(32)];
            if (r04 == null) goto L15;
            setMandatorySystemGestureInsets(r04);
        L15:
            Insets r05 = this.mInsetsTypeMask[Type.indexOf(64)];
            if (r05 == null) goto L20;
            setTappableElementInsets(r05);
            return;
        L20:
            return;
        }

        public abstract WindowInsetsCompat build();

        public void initTypeBoundingRects(WindowInsetsCompat r6) {
            int r0 = 1;
        L4:
            if (r0 > 512) goto L9;
            List<Rect> r1 = r6.getBoundingRects(r0);
            int r2 = Type.indexOf(r0);
            this.mTypeBoundingRectsMap[r2] = (Rect[]) r1.toArray(new Rect[r1.size()]);
            if (r0 == 8) goto L8;
            List<Rect> r12 = r6.getBoundingRectsIgnoringVisibility(r0);
            this.mTypeMaxBoundingRectsMap[r2] = (Rect[]) r12.toArray(new Rect[r12.size()]);
        L8:
            r0 = r0 << 1;
            goto L4
        }

        public void setInsets(int r4, Insets r5) {
            if (this.mInsetsTypeMask != null) goto L5;
            this.mInsetsTypeMask = new Insets[10];
        L5:
            int r0 = 1;
        L7:
            if (r0 > 512) goto L13;
            if ((r4 & r0) == 0) goto L12;
            this.mInsetsTypeMask[Type.indexOf(r0)] = r5;
        L12:
            r0 = r0 << 1;
            goto L7
        }

        public void setInsetsIgnoringVisibility(int r1, Insets r2) {
            if (r1 == 8) goto L5;
            return;
        L5:
            f$$ExternalSyntheticBUOutline1.m("Ignoring visibility inset not available for IME");
        }

        public void setMandatorySystemGestureInsets(Insets r1) {
        }

        public abstract void setStableInsets(Insets r1);

        public void setSystemGestureInsets(Insets r1) {
        }

        public abstract void setSystemWindowInsets(Insets r1);

        public void setTappableElementInsets(Insets r1) {
        }
    }

    /* loaded from: classes3.dex */
    public static class BuilderImpl20 extends BuilderImpl {
        private static Constructor<WindowInsets> sConstructor = null;
        private static boolean sConstructorFetched = false;
        private static Field sConsumedField = null;
        private static boolean sConsumedFieldFetched = false;
        private WindowInsets mPlatformInsets;
        private Insets mStableInsets;

        static {
        }

        public BuilderImpl20() {
            this.mPlatformInsets = createWindowInsetsInstance();
        }

        public BuilderImpl20(WindowInsetsCompat r1) {
            super(r1);
            this.mPlatformInsets = r1.toWindowInsets();
        }

        private static WindowInsets createWindowInsetsInstance() {
            if (sConsumedFieldFetched == false) goto L33;
        L9:
            Field r0 = sConsumedField;
            if (r0 == null) goto L18;
            WindowInsets r02 = (WindowInsets) r0.get(null);     // Catch: ReflectiveOperationException -> L15
            if (r02 == null) goto L18;
            return new WindowInsets(r02);
        L15:
            e = move-exception;
            Log.i("WindowInsetsCompat", "Could not get value from WindowInsets.CONSUMED field", e);
        L18:
            if (sConstructorFetched == false) goto L31;
        L24:
            Constructor<WindowInsets> r03 = sConstructor;
            if (r03 != null) goto L35;
        L30:
            return null;
        L35:
            return r03.newInstance(new Object[]{new Rect()});
        L28:
            e = move-exception;
            Log.i("WindowInsetsCompat", "Could not invoke WindowInsets(Rect) constructor", e);
            goto L30
        L31:
            sConstructor = WindowInsets.class.getConstructor(new Class[]{Rect.class});     // Catch: ReflectiveOperationException -> L21
        L23:
            sConstructorFetched = true;
        L21:
            e = move-exception;
            Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets(Rect) constructor", e);
            goto L23
        L33:
            sConsumedField = WindowInsets.class.getDeclaredField("CONSUMED");     // Catch: ReflectiveOperationException -> L6
        L8:
            sConsumedFieldFetched = true;
        L6:
            e = move-exception;
            Log.i("WindowInsetsCompat", "Could not retrieve WindowInsets.CONSUMED field", e);
            goto L8
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat r0 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets);
            r0.setOverriddenInsets(this.mInsetsTypeMask);
            r0.setStableInsets(this.mStableInsets);
            r0.setDisplayShape(this.mDisplayShapeCompat);
            r0.setTypeBoundingRectsMap(this.mTypeBoundingRectsMap);
            r0.setTypeMaxBoundingRectsMap(this.mTypeMaxBoundingRectsMap);
            return r0;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setStableInsets(Insets r1) {
            this.mStableInsets = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemWindowInsets(Insets r5) {
            WindowInsets r0 = this.mPlatformInsets;
            if (r0 == null) goto L6;
            this.mPlatformInsets = r0.replaceSystemWindowInsets(r5.left, r5.top, r5.right, r5.bottom);
            return;
        }
    }

    public static class BuilderImpl29 extends BuilderImpl {
        final WindowInsets.Builder mPlatBuilder;

        public BuilderImpl29() {
            this.mPlatBuilder = WindowInsetsCompat$BuilderImpl29$$ExternalSyntheticApiModelOutline1.m();
        }

        public BuilderImpl29(WindowInsetsCompat r1) {
            super(r1);
            WindowInsets r12 = r1.toWindowInsets();
            if (r12 == null) goto L5;
            WindowInsets.Builder r13 = WindowInsetsCompat$BuilderImpl29$$ExternalSyntheticApiModelOutline0.m(r12);
        L6:
            this.mPlatBuilder = r13;
            return;
        L5:
            r13 = WindowInsetsCompat$BuilderImpl29$$ExternalSyntheticApiModelOutline1.m();
            goto L6
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public WindowInsetsCompat build() {
            applyInsetTypes();
            WindowInsetsCompat r0 = WindowInsetsCompat.toWindowInsetsCompat(this.mPlatBuilder.build());
            r0.setOverriddenInsets(this.mInsetsTypeMask);
            r0.setDisplayShape(this.mDisplayShapeCompat);
            r0.setTypeBoundingRectsMap(this.mTypeBoundingRectsMap);
            r0.setTypeMaxBoundingRectsMap(this.mTypeMaxBoundingRectsMap);
            return r0;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setMandatorySystemGestureInsets(Insets r1) {
            this.mPlatBuilder.setMandatorySystemGestureInsets(r1.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setStableInsets(Insets r1) {
            this.mPlatBuilder.setStableInsets(r1.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemGestureInsets(Insets r1) {
            this.mPlatBuilder.setSystemGestureInsets(r1.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setSystemWindowInsets(Insets r1) {
            this.mPlatBuilder.setSystemWindowInsets(r1.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setTappableElementInsets(Insets r1) {
            this.mPlatBuilder.setTappableElementInsets(r1.toPlatformInsets());
        }
    }

    public static class BuilderImpl30 extends BuilderImpl29 {
        public BuilderImpl30() {
        }

        public BuilderImpl30(WindowInsetsCompat r1) {
            super(r1);
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsets(int r1, Insets r2) {
            this.mPlatBuilder.setInsets(TypeImpl30.toPlatformType(r1), r2.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsetsIgnoringVisibility(int r1, Insets r2) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl30.toPlatformType(r1), r2.toPlatformInsets());
        }
    }

    public static class BuilderImpl31 extends BuilderImpl30 {
        public BuilderImpl31() {
        }

        public BuilderImpl31(WindowInsetsCompat r1) {
            super(r1);
        }
    }

    public static class BuilderImpl34 extends BuilderImpl31 {
        public BuilderImpl34() {
        }

        public BuilderImpl34(WindowInsetsCompat r1) {
            super(r1);
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl30, androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsets(int r1, Insets r2) {
            this.mPlatBuilder.setInsets(TypeImpl34.toPlatformType(r1), r2.toPlatformInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl30, androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsetsIgnoringVisibility(int r1, Insets r2) {
            this.mPlatBuilder.setInsetsIgnoringVisibility(TypeImpl34.toPlatformType(r1), r2.toPlatformInsets());
        }
    }

    public static class BuilderImpl35 extends BuilderImpl34 {
        private boolean mSetInsetsCalled;
        private boolean mSetInsetsIgnoringVisibilityCalled;

        public BuilderImpl35() {
            this.mSetInsetsCalled = false;
            this.mSetInsetsIgnoringVisibilityCalled = false;
        }

        public BuilderImpl35(WindowInsetsCompat r2) {
            super(r2);
            this.mSetInsetsCalled = false;
            this.mSetInsetsIgnoringVisibilityCalled = false;
            if (r2.isConsumed() == true) goto L6;
            this.mSetInsetsCalled = true;
            this.mSetInsetsIgnoringVisibilityCalled = true;
            return;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void initTypeBoundingRects(WindowInsetsCompat r1) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl34, androidx.core.view.WindowInsetsCompat.BuilderImpl30, androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsets(int r1, Insets r2) {
            super.setInsets(r1, r2);
            this.mSetInsetsCalled = true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.BuilderImpl34, androidx.core.view.WindowInsetsCompat.BuilderImpl30, androidx.core.view.WindowInsetsCompat.BuilderImpl
        public void setInsetsIgnoringVisibility(int r1, Insets r2) {
            super.setInsetsIgnoringVisibility(r1, r2);
            this.mSetInsetsIgnoringVisibilityCalled = true;
        }
    }

    public static class BuilderImpl36 extends BuilderImpl35 {
        public BuilderImpl36() {
        }

        public BuilderImpl36(WindowInsetsCompat r1) {
            super(r1);
        }
    }

    public static class Impl {
        static final WindowInsetsCompat CONSUMED = null;
        final WindowInsetsCompat mHost;

        static {
            CONSUMED = new Builder().build().consumeDisplayCutout().consumeStableInsets().consumeSystemWindowInsets();
        }

        public Impl(WindowInsetsCompat r1) {
            this.mHost = r1;
        }

        public WindowInsetsCompat consumeDisplayCutout() {
            return this.mHost;
        }

        public WindowInsetsCompat consumeStableInsets() {
            return this.mHost;
        }

        public WindowInsetsCompat consumeSystemWindowInsets() {
            return this.mHost;
        }

        public void copyRootViewBounds(View r1) {
        }

        public void copyWindowDataInto(WindowInsetsCompat r1) {
        }

        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Impl) == true) goto L8;
            return false;
        L8:
            Impl r52 = (Impl) r5;
            if (isRound() == r52.isRound()) goto L11;
        L19:
            return false;
        L11:
            if (isConsumed() != r52.isConsumed()) goto L19;
            if (ObjectsCompat.equals(getSystemWindowInsets(), r52.getSystemWindowInsets()) == false) goto L19;
            if (ObjectsCompat.equals(getStableInsets(), r52.getStableInsets()) == false) goto L19;
            if (ObjectsCompat.equals(getDisplayCutout(), r52.getDisplayCutout()) == false) goto L19;
            return true;
        }

        public List<Rect> getBoundingRects(int r1) {
            return Collections.EMPTY_LIST;
        }

        public List<Rect> getBoundingRectsIgnoringVisibility(int r1) {
            return Collections.EMPTY_LIST;
        }

        public DisplayCutoutCompat getDisplayCutout() {
            return null;
        }

        public Insets getInsets(int r1) {
            return Insets.NONE;
        }

        public Insets getInsetsIgnoringVisibility(int r1) {
            if ((r1 & 8) == 0) goto L5;
            f$$ExternalSyntheticBUOutline1.m("Unable to query the maximum insets for IME");
            return null;
        L5:
            return Insets.NONE;
        }

        public Insets getMandatorySystemGestureInsets() {
            return getSystemWindowInsets();
        }

        public Insets getStableInsets() {
            return Insets.NONE;
        }

        public Insets getSystemGestureInsets() {
            return getSystemWindowInsets();
        }

        public Insets getSystemWindowInsets() {
            return Insets.NONE;
        }

        public Insets getTappableElementInsets() {
            return getSystemWindowInsets();
        }

        public int hashCode() {
            return ObjectsCompat.hash(new Object[]{Boolean.valueOf(isRound()), Boolean.valueOf(isConsumed()), getSystemWindowInsets(), getStableInsets(), getDisplayCutout()});
        }

        public void initDisplayShape(View r1) {
        }

        public void initTypeBoundingRectsMaps() {
        }

        public WindowInsetsCompat inset(int r1, int r2, int r3, int r4) {
            return CONSUMED;
        }

        public boolean isConsumed() {
            return false;
        }

        public boolean isRound() {
            return false;
        }

        public boolean isVisible(int r1) {
            return true;
        }

        public void setDisplayShape(DisplayShapeCompat r1) {
        }

        public void setOverriddenInsets(Insets[] r1) {
        }

        public void setRootViewData(Insets r1) {
        }

        public void setRootWindowInsets(WindowInsetsCompat r1) {
        }

        public void setStableInsets(Insets r1) {
        }

        public void setSystemUiVisibility(int r1) {
        }

        public void setTypeBoundingRectsMap(Rect[][] r1) {
        }

        public void setTypeMaxBoundingRectsMap(Rect[][] r1) {
        }
    }

    public static class Impl20 extends Impl {
        private static Class<?> sAttachInfoClass = null;
        private static Field sAttachInfoField = null;
        private static Method sGetViewRootImplMethod = null;
        private static Field sVisibleInsetsField = null;
        private static boolean sVisibleRectReflectionFetched = false;
        DisplayShapeCompat mDisplayShapeCompat;
        private Insets[] mOverriddenInsets;
        final WindowInsets mPlatformInsets;
        int mRootViewHeight;
        Insets mRootViewVisibleInsets;
        int mRootViewWidth;
        private WindowInsetsCompat mRootWindowInsets;
        int mSystemUiVisibility;
        private Insets mSystemWindowInsets;
        private Rect[][] mTypeBoundingRectsMap;
        private Rect[][] mTypeMaxBoundingRectsMap;

        static {
        }

        public Impl20(WindowInsetsCompat r2, WindowInsets r3) {
            super(r2);
            this.mSystemWindowInsets = null;
            this.mTypeBoundingRectsMap = new Rect[10][];
            this.mTypeMaxBoundingRectsMap = new Rect[10][];
            this.mPlatformInsets = r3;
        }

        public Impl20(WindowInsetsCompat r2, Impl20 r3) {
            this(r2, new WindowInsets(r3.mPlatformInsets));
        }

        private DisplayShapeCompat createDisplayShape(View r12) {
            if (r12 != null) goto L5;
            return null;
        L5:
            Display r122 = r12.getDisplay();
            if (r122 != null) goto L8;
            return null;
        L8:
            Point r0 = new Point();
            r122.getRealSize(r0);
            if (this.mHost.isRound() == true) goto L11;
            int r11 = 0;
            RoundedCornerCompat r1 = DisplayCompat.getRoundedCorner(r122, 0);
            RoundedCornerCompat r2 = DisplayCompat.getRoundedCorner(r122, 1);
            RoundedCornerCompat r3 = DisplayCompat.getRoundedCorner(r122, 2);
            RoundedCornerCompat r123 = DisplayCompat.getRoundedCorner(r122, 3);
            int r4 = r0.x;
            int r5 = r0.y;
            if (r1 == null) goto L15;
            int r7 = r1.getRadius();
        L16:
            if (r2 == null) goto L18;
            int r8 = r2.getRadius();
        L19:
            if (r3 == null) goto L21;
            int r9 = r3.getRadius();
        L22:
            if (r123 == null) goto L25;
            r11 = r123.getRadius();
        L25:
            return DisplayShapeCompat.create(r4, r5, false, r7, r8, r9, r11);
        L21:
            r9 = 0;
            goto L22
        L18:
            r8 = 0;
            goto L19
        L15:
            r7 = 0;
            goto L16
        L11:
            return DisplayShapeCompat.create(r0.x, r0.y, true, 0, 0, 0, 0);
        }

        private static List<Rect> getBoundingRects(Rect[][] r6, int r7) {
            Rect[] r0 = null;
            int r1 = 1;
        L4:
            if (r1 > 512) goto L15;
            if ((r7 & r1) == 0) goto L14;
            Rect[] r2 = r6[Type.indexOf(r1)];
            if (r2 == null) goto L14;
            if (r0 != null) goto L13;
            r0 = r2;
            goto L14
        L13:
            Rect[] r3 = new Rect[r0.length + r2.length];
            System.arraycopy(r0, 0, r3, 0, r0.length);
            System.arraycopy(r2, 0, r3, r0.length, r2.length);
            r0 = r3;
        L14:
            r1 = r1 << 1;
            goto L4
        L15:
            if (r0 != null) goto L19;
            return Collections.EMPTY_LIST;
        L19:
            return Arrays.asList(r0);
        }

        private Rect[] getBoundingRectsFromInsets(Insets r7) {
            ArrayList r0 = new ArrayList();
            if (r7.left == 0) goto L6;
            r0.add(new Rect(0, 0, r7.left, this.mRootViewHeight));
        L6:
            if (r7.top == 0) goto L9;
            r0.add(new Rect(0, 0, this.mRootViewWidth, r7.top));
        L9:
            if (r7.right == 0) goto L12;
            int r3 = this.mRootViewWidth;
            r0.add(new Rect(r3 - r7.right, 0, r3, this.mRootViewHeight));
        L12:
            if (r7.bottom == 0) goto L15;
            int r32 = this.mRootViewHeight;
            r0.add(new Rect(0, r32 - r7.bottom, this.mRootViewWidth, r32));
        L15:
            return (Rect[]) r0.toArray(new Rect[r0.size()]);
        }

        @SuppressLint({"WrongConstant"})
        private Insets getInsets(int r4, boolean r5) {
            Insets r0 = Insets.NONE;
            int r1 = 1;
        L4:
            if (r1 > 512) goto L10;
            if ((r4 & r1) == 0) goto L9;
            r0 = Insets.max(r0, getInsetsForType(r1, r5));
        L9:
            r1 = r1 << 1;
            goto L4
        L10:
            return r0;
        }

        private Insets getRootStableInsets() {
            WindowInsetsCompat r0 = this.mRootWindowInsets;
            if (r0 == null) goto L7;
            return r0.getStableInsets();
        L7:
            return Insets.NONE;
        }

        private Insets getVisibleInsets(View r4) {
            if (Build.VERSION.SDK_INT < 30) goto L5;
            ByteString$$ExternalSyntheticBUOutline0.m("getVisibleInsets() should not be called on API >= 30. Use WindowInsets.isVisible() instead.");
            return null;
        L5:
            if (sVisibleRectReflectionFetched == true) goto L7;
            loadReflectionField();
        L7:
            Method r0 = sGetViewRootImplMethod;
            if (r0 != null) goto L10;
        L26:
            return null;
        L10:
            if (sAttachInfoClass == null) goto L26;
            if (sVisibleInsetsField == null) goto L26;
            Object r42 = r0.invoke(r4, null);     // Catch: ReflectiveOperationException -> L18
            if (r42 != null) goto L20;
            Log.w("WindowInsetsCompat", "Failed to get visible insets. getViewRootImpl() returned null from the provided view. This means that the view is either not attached or the method has been overridden", new NullPointerException());     // Catch: ReflectiveOperationException -> L18
            return null;
        L20:
            Rect r43 = (Rect) sVisibleInsetsField.get(sAttachInfoField.get(r42));     // Catch: ReflectiveOperationException -> L18
            if (r43 != null) goto L23;
            return null;
        L23:
            return Insets.of(r43);
        L18:
            e = move-exception;
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            goto L26
        }

        @SuppressLint({"PrivateApi"})
        private static void loadReflectionField() {
            sGetViewRootImplMethod = View.class.getDeclaredMethod("getViewRootImpl", null);     // Catch: ReflectiveOperationException -> L5
            Class<?> r1 = Class.forName("android.view.View$AttachInfo");     // Catch: ReflectiveOperationException -> L5
            sAttachInfoClass = r1;     // Catch: ReflectiveOperationException -> L5
            sVisibleInsetsField = r1.getDeclaredField("mVisibleInsets");     // Catch: ReflectiveOperationException -> L5
            sAttachInfoField = Class.forName("android.view.ViewRootImpl").getDeclaredField("mAttachInfo");     // Catch: ReflectiveOperationException -> L5
            sVisibleInsetsField.setAccessible(true);     // Catch: ReflectiveOperationException -> L5
            sAttachInfoField.setAccessible(true);     // Catch: ReflectiveOperationException -> L5
        L7:
            sVisibleRectReflectionFetched = true;
            return;
        L5:
            e = move-exception;
            Log.e("WindowInsetsCompat", "Failed to get visible insets. (Reflection error). " + e.getMessage(), e);
            goto L7
        }

        public static boolean systemBarVisibilityEquals(int r0, int r1) {
            if ((r0 & 6) != (r1 & 6)) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void copyRootViewBounds(View r2) {
            this.mRootViewWidth = r2.getWidth();
            this.mRootViewHeight = r2.getHeight();
            Insets r22 = getVisibleInsets(r2);
            if (r22 != null) goto L5;
            r22 = Insets.NONE;
        L5:
            setRootViewData(r22);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void copyWindowDataInto(WindowInsetsCompat r2) {
            r2.setRootWindowInsets(this.mRootWindowInsets);
            r2.setRootViewData(this.mRootViewVisibleInsets);
            r2.setSystemUiVisibility(this.mSystemUiVisibility);
            r2.setDisplayShape(this.mDisplayShapeCompat);
            r2.setTypeBoundingRectsMap(this.mTypeBoundingRectsMap);
            r2.setTypeMaxBoundingRectsMap(this.mTypeMaxBoundingRectsMap);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object r4) {
            if (super.equals(r4) == true) goto L5;
            return false;
        L5:
            Impl20 r42 = (Impl20) r4;
            if (Objects.equals(this.mRootViewVisibleInsets, r42.mRootViewVisibleInsets) == true) goto L8;
        L11:
            return false;
        L8:
            if (systemBarVisibilityEquals(this.mSystemUiVisibility, r42.mSystemUiVisibility) == false) goto L11;
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public List<Rect> getBoundingRects(int r1) {
            return getBoundingRects(this.mTypeBoundingRectsMap, r1);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public List<Rect> getBoundingRectsIgnoringVisibility(int r1) {
            return getBoundingRects(this.mTypeMaxBoundingRectsMap, r1);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsets(int r2) {
            return getInsets(r2, false);
        }

        public Insets getInsetsForType(int r4, boolean r5) {
            if (r4 == 1) goto L67;
            Insets r0 = null;
            if (r4 != 2) goto L7;
            if (r5 == false) goto L56;
            Insets r42 = getRootStableInsets();
            Insets r3 = getStableInsets();
            return Insets.of(Math.max(r42.left, r3.left), 0, Math.max(r42.right, r3.right), Math.max(r42.bottom, r3.bottom));
        L56:
            if ((this.mSystemUiVisibility & 2) != 0) goto L58;
            Insets r43 = getSystemWindowInsets();
            WindowInsetsCompat r32 = this.mRootWindowInsets;
            if (r32 == null) goto L62;
            r0 = r32.getStableInsets();
        L62:
            int r33 = r43.bottom;
            if (r0 == null) goto L66;
            r33 = Math.min(r33, r0.bottom);
        L66:
            return Insets.of(r43.left, 0, r43.right, r33);
        L58:
            return Insets.NONE;
        L7:
            if (r4 != 8) goto L9;
            Insets[] r44 = this.mOverriddenInsets;
            if (r44 == null) goto L36;
            r0 = r44[Type.indexOf(8)];
        L36:
            if (r0 == null) goto L38;
            return r0;
        L38:
            Insets r45 = getSystemWindowInsets();
            Insets r52 = getRootStableInsets();
            int r46 = r45.bottom;
            if (r46 > r52.bottom) goto L41;
            Insets r47 = this.mRootViewVisibleInsets;
            if (r47 == null) goto L51;
            if (r47.equals(Insets.NONE) == true) goto L51;
            int r34 = this.mRootViewVisibleInsets.bottom;
            if (r34 <= r52.bottom) goto L51;
            return Insets.of(0, 0, 0, r34);
        L51:
            return Insets.NONE;
        L41:
            return Insets.of(0, 0, 0, r46);
        L9:
            if (r4 == 16) goto L32;
            if (r4 == 32) goto L30;
            if (r4 == 64) goto L28;
            if (r4 != 128) goto L17;
            WindowInsetsCompat r48 = this.mRootWindowInsets;
            if (r48 == null) goto L21;
            DisplayCutoutCompat r35 = r48.getDisplayCutout();
        L22:
            if (r35 == null) goto L26;
            return Insets.of(r35.getSafeInsetLeft(), r35.getSafeInsetTop(), r35.getSafeInsetRight(), r35.getSafeInsetBottom());
        L26:
            return Insets.NONE;
        L21:
            r35 = getDisplayCutout();
            goto L22
        L17:
            return Insets.NONE;
        L28:
            return getTappableElementInsets();
        L30:
            return getMandatorySystemGestureInsets();
        L32:
            return getSystemGestureInsets();
        L67:
            if (r5 == false) goto L71;
            return Insets.of(0, Math.max(getRootStableInsets().top, getSystemWindowInsets().top), 0, 0);
        L71:
            if ((this.mSystemUiVisibility & 4) == 0) goto L75;
            return Insets.NONE;
        L75:
            return Insets.of(0, getSystemWindowInsets().top, 0, 0);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsetsIgnoringVisibility(int r2) {
            return getInsets(r2, true);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public final Insets getSystemWindowInsets() {
            if (this.mSystemWindowInsets != null) goto L6;
            this.mSystemWindowInsets = Insets.of(this.mPlatformInsets.getSystemWindowInsetLeft(), this.mPlatformInsets.getSystemWindowInsetTop(), this.mPlatformInsets.getSystemWindowInsetRight(), this.mPlatformInsets.getSystemWindowInsetBottom());
        L6:
            return this.mSystemWindowInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void initDisplayShape(View r1) {
            this.mDisplayShapeCompat = createDisplayShape(r1);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @SuppressLint({"WrongConstant"})
        public void initTypeBoundingRectsMaps() {
            int r0 = 1;
        L4:
            if (r0 > 512) goto L9;
            int r1 = Type.indexOf(r0);
            this.mTypeBoundingRectsMap[r1] = getBoundingRectsFromInsets(getInsets(r0));
            if (r0 == 8) goto L8;
            this.mTypeMaxBoundingRectsMap[r1] = getBoundingRectsFromInsets(getInsetsIgnoringVisibility(r0));
        L8:
            r0 = r0 << 1;
            goto L4
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public WindowInsetsCompat inset(int r3, int r4, int r5, int r6) {
            Builder r0 = new Builder(WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets));
            r0.setSystemWindowInsets(WindowInsetsCompat.insetInsets(getSystemWindowInsets(), r3, r4, r5, r6));
            r0.setStableInsets(WindowInsetsCompat.insetInsets(getStableInsets(), r3, r4, r5, r6));
            return r0.build();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean isRound() {
            return this.mPlatformInsets.isRound();
        }

        public boolean isTypeVisible(int r4) {
            if (r4 == 1) goto L15;
            if (r4 == 2) goto L15;
            if (r4 != 4) goto L9;
            return false;
        L9:
            if (r4 == 8) goto L15;
            if (r4 == 128) goto L15;
            return true;
        L15:
            return !getInsetsForType(r4, false).equals(Insets.NONE);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        @SuppressLint({"WrongConstant"})
        public boolean isVisible(int r4) {
            int r1 = 1;
        L4:
            if (r1 > 512) goto L13;
            if ((r4 & r1) == 0) goto L12;
            if (isTypeVisible(r1) == true) goto L12;
            return false;
        L12:
            r1 = r1 << 1;
            goto L4
        L13:
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setDisplayShape(DisplayShapeCompat r1) {
            this.mDisplayShapeCompat = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setOverriddenInsets(Insets[] r1) {
            this.mOverriddenInsets = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setRootViewData(Insets r1) {
            this.mRootViewVisibleInsets = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setRootWindowInsets(WindowInsetsCompat r1) {
            this.mRootWindowInsets = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setSystemUiVisibility(int r1) {
            this.mSystemUiVisibility = r1;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setTypeBoundingRectsMap(Rect[][] r1) {
            Objects.requireNonNull(r1);
            this.mTypeBoundingRectsMap = (Rect[][]) r1.clone();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setTypeMaxBoundingRectsMap(Rect[][] r1) {
            Objects.requireNonNull(r1);
            this.mTypeMaxBoundingRectsMap = (Rect[][]) r1.clone();
        }
    }

    public static class Impl21 extends Impl20 {
        private Insets mStableInsets;

        public Impl21(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
            this.mStableInsets = null;
        }

        public Impl21(WindowInsetsCompat r1, Impl21 r2) {
            super(r1, r2);
            this.mStableInsets = null;
            this.mStableInsets = r2.mStableInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public WindowInsetsCompat consumeStableInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeStableInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public WindowInsetsCompat consumeSystemWindowInsets() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeSystemWindowInsets());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public final Insets getStableInsets() {
            if (this.mStableInsets != null) goto L6;
            this.mStableInsets = Insets.of(this.mPlatformInsets.getStableInsetLeft(), this.mPlatformInsets.getStableInsetTop(), this.mPlatformInsets.getStableInsetRight(), this.mPlatformInsets.getStableInsetBottom());
        L6:
            return this.mStableInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public boolean isConsumed() {
            return this.mPlatformInsets.isConsumed();
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public void setStableInsets(Insets r1) {
            this.mStableInsets = r1;
        }
    }

    public static class Impl28 extends Impl21 {
        public Impl28(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
        }

        public Impl28(WindowInsetsCompat r1, Impl28 r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public WindowInsetsCompat consumeDisplayCutout() {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.consumeDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean equals(Object r5) {
            if (this != r5) goto L6;
            return true;
        L6:
            if ((r5 instanceof Impl28) == true) goto L8;
            return false;
        L8:
            Impl28 r52 = (Impl28) r5;
            if (Objects.equals(this.mPlatformInsets, r52.mPlatformInsets) == true) goto L11;
        L15:
            return false;
        L11:
            if (Objects.equals(this.mRootViewVisibleInsets, r52.mRootViewVisibleInsets) == false) goto L15;
            if (Impl20.systemBarVisibilityEquals(this.mSystemUiVisibility, r52.mSystemUiVisibility) == false) goto L15;
            return true;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public DisplayCutoutCompat getDisplayCutout() {
            return DisplayCutoutCompat.wrap(this.mPlatformInsets.getDisplayCutout());
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public int hashCode() {
            return this.mPlatformInsets.hashCode();
        }
    }

    public static class Impl29 extends Impl28 {
        private Insets mMandatorySystemGestureInsets;
        private Insets mSystemGestureInsets;
        private Insets mTappableElementInsets;

        public Impl29(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }

        public Impl29(WindowInsetsCompat r1, Impl29 r2) {
            super(r1, r2);
            this.mSystemGestureInsets = null;
            this.mMandatorySystemGestureInsets = null;
            this.mTappableElementInsets = null;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public Insets getMandatorySystemGestureInsets() {
            if (this.mMandatorySystemGestureInsets != null) goto L6;
            this.mMandatorySystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getMandatorySystemGestureInsets());
        L6:
            return this.mMandatorySystemGestureInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public Insets getSystemGestureInsets() {
            if (this.mSystemGestureInsets != null) goto L6;
            this.mSystemGestureInsets = Insets.toCompatInsets(this.mPlatformInsets.getSystemGestureInsets());
        L6:
            return this.mSystemGestureInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl
        public Insets getTappableElementInsets() {
            if (this.mTappableElementInsets != null) goto L6;
            this.mTappableElementInsets = Insets.toCompatInsets(this.mPlatformInsets.getTappableElementInsets());
        L6:
            return this.mTappableElementInsets;
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public WindowInsetsCompat inset(int r1, int r2, int r3, int r4) {
            return WindowInsetsCompat.toWindowInsetsCompat(this.mPlatformInsets.inset(r1, r2, r3, r4));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl21, androidx.core.view.WindowInsetsCompat.Impl
        public void setStableInsets(Insets r1) {
        }
    }

    public static class Impl30 extends Impl29 {
        static final WindowInsetsCompat CONSUMED = null;

        static {
            CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m());
        }

        public Impl30(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
        }

        public Impl30(WindowInsetsCompat r1, Impl30 r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public final void copyRootViewBounds(View r1) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsets(int r1) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl30.toPlatformType(r1)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsetsIgnoringVisibility(int r1) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl30.toPlatformType(r1)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean isVisible(int r1) {
            return this.mPlatformInsets.isVisible(TypeImpl30.toPlatformType(r1));
        }
    }

    public static class Impl31 extends Impl30 {
        public Impl31(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
        }

        public Impl31(WindowInsetsCompat r1, Impl31 r2) {
            super(r1, r2);
        }
    }

    public static class Impl34 extends Impl31 {
        static final WindowInsetsCompat CONSUMED = null;

        static {
            CONSUMED = WindowInsetsCompat.toWindowInsetsCompat(WindowInsetsCompat$Impl30$$ExternalSyntheticApiModelOutline0.m());
        }

        public Impl34(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
        }

        public Impl34(WindowInsetsCompat r1, Impl34 r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl30, androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsets(int r1) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsets(TypeImpl34.toPlatformType(r1)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl30, androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public Insets getInsetsIgnoringVisibility(int r1) {
            return Insets.toCompatInsets(this.mPlatformInsets.getInsetsIgnoringVisibility(TypeImpl34.toPlatformType(r1)));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public void initDisplayShape(View r1) {
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl30, androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public boolean isVisible(int r1) {
            return this.mPlatformInsets.isVisible(TypeImpl34.toPlatformType(r1));
        }
    }

    public static class Impl35 extends Impl34 {
        public Impl35(WindowInsetsCompat r1, WindowInsets r2) {
            super(r1, r2);
        }

        public Impl35(WindowInsetsCompat r1, Impl35 r2) {
            super(r1, r2);
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public List<Rect> getBoundingRects(int r1) {
            return this.mPlatformInsets.getBoundingRects(TypeImpl34.toPlatformType(r1));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public List<Rect> getBoundingRectsIgnoringVisibility(int r1) {
            return this.mPlatformInsets.getBoundingRectsIgnoringVisibility(TypeImpl34.toPlatformType(r1));
        }

        @Override // androidx.core.view.WindowInsetsCompat.Impl20, androidx.core.view.WindowInsetsCompat.Impl
        public void initTypeBoundingRectsMaps() {
        }
    }

    public static final class Type {
        public static int displayCutout() {
            return 128;
        }

        public static int ime() {
            return 8;
        }

        public static int indexOf(int r3) {
            if (r3 != 1) goto L5;
            return 0;
        L5:
            if (r3 != 2) goto L7;
            return 1;
        L7:
            if (r3 != 4) goto L9;
            return 2;
        L9:
            if (r3 != 8) goto L11;
            return 3;
        L11:
            if (r3 != 16) goto L13;
            return 4;
        L13:
            if (r3 != 32) goto L15;
            return 5;
        L15:
            if (r3 != 64) goto L17;
            return 6;
        L17:
            if (r3 != 128) goto L19;
            return 7;
        L19:
            if (r3 != 256) goto L21;
            return 8;
        L21:
            if (r3 != 512) goto L24;
            return 9;
        L24:
            CharCodeKt$$ExternalSyntheticBUOutline0.m("type needs to be >= FIRST and <= LAST, type=", r3);
            return 0;
        }

        public static int mandatorySystemGestures() {
            return 32;
        }

        public static int navigationBars() {
            return 2;
        }

        public static int statusBars() {
            return 1;
        }

        public static int systemBars() {
            return 519;
        }

        public static int systemGestures() {
            return 16;
        }

        public static int tappableElement() {
            return 64;
        }
    }

    /* loaded from: classes3.dex */
    public static final class TypeImpl30 {
        public static int toPlatformType(int r4) {
            int r0 = 0;
            int r2 = 1;
        L4:
            if (r2 > 512) goto L33;
            if ((r4 & r2) == 0) goto L32;
            if (r2 != 1) goto L9;
            int r3 = WindowInsets.Type.statusBars();
        L24:
            r0 = r0 | r3;
            goto L32
        L9:
            if (r2 != 2) goto L11;
            r3 = WindowInsets.Type.navigationBars();
            goto L24
        L11:
            if (r2 != 4) goto L13;
            r3 = WindowInsets.Type.captionBar();
            goto L24
        L13:
            if (r2 != 8) goto L15;
            r3 = WindowInsets.Type.ime();
            goto L24
        L15:
            if (r2 != 16) goto L17;
            r3 = WindowInsets.Type.systemGestures();
            goto L24
        L17:
            if (r2 != 32) goto L19;
            r3 = WindowInsets.Type.mandatorySystemGestures();
            goto L24
        L19:
            if (r2 != 64) goto L21;
            r3 = WindowInsets.Type.tappableElement();
            goto L24
        L21:
            if (r2 != 128) goto L32;
            r3 = WindowInsets.Type.displayCutout();
        L32:
            r2 = r2 << 1;
            goto L4
        L33:
            return r0;
        }
    }

    public static final class TypeImpl34 {
        public static int toPlatformType(int r5) {
            int r0 = 0;
            int r2 = 1;
        L4:
            if (r2 > 512) goto L35;
            if ((r5 & r2) == 0) goto L34;
            if (r2 != 1) goto L9;
            int r3 = WindowInsets.Type.statusBars();
        L25:
            r0 = r0 | r3;
            goto L34
        L9:
            if (r2 != 2) goto L11;
            r3 = WindowInsets.Type.navigationBars();
            goto L25
        L11:
            if (r2 != 4) goto L13;
            r3 = WindowInsets.Type.captionBar();
            goto L25
        L13:
            if (r2 != 8) goto L15;
            r3 = WindowInsets.Type.ime();
            goto L25
        L15:
            if (r2 != 16) goto L17;
            r3 = WindowInsets.Type.systemGestures();
            goto L25
        L17:
            if (r2 != 32) goto L19;
            r3 = WindowInsets.Type.mandatorySystemGestures();
            goto L25
        L19:
            if (r2 != 64) goto L21;
            r3 = WindowInsets.Type.tappableElement();
            goto L25
        L21:
            if (r2 == 128) goto L26;
            if (r2 != 512) goto L34;
            r3 = WindowInsets.Type.systemOverlays();
            goto L25
        L26:
            r3 = WindowInsets.Type.displayCutout();
        L34:
            r2 = r2 << 1;
            goto L4
        L35:
            return r0;
        }
    }

    static {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 34) goto L7;
        CONSUMED = Impl34.CONSUMED;
        return;
    L7:
        if (r0 < 30) goto L10;
        CONSUMED = Impl30.CONSUMED;
        return;
    L10:
        CONSUMED = Impl.CONSUMED;
    }

    private WindowInsetsCompat(WindowInsets r3) {
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 35) goto L7;
        this.mImpl = new Impl35(this, r3);
        return;
    L7:
        if (r0 < 34) goto L11;
        this.mImpl = new Impl34(this, r3);
        return;
    L11:
        if (r0 < 31) goto L15;
        this.mImpl = new Impl31(this, r3);
        return;
    L15:
        if (r0 < 30) goto L19;
        this.mImpl = new Impl30(this, r3);
        return;
    L19:
        if (r0 < 29) goto L23;
        this.mImpl = new Impl29(this, r3);
        return;
    L23:
        if (r0 < 28) goto L26;
        this.mImpl = new Impl28(this, r3);
        return;
    L26:
        this.mImpl = new Impl21(this, r3);
    }

    public WindowInsetsCompat(WindowInsetsCompat r3) {
        if (r3 == null) goto L43;
        Impl r32 = r3.mImpl;
        int r0 = Build.VERSION.SDK_INT;
        if (r0 < 35) goto L10;
        if ((r32 instanceof Impl35) == false) goto L10;
        this.mImpl = new Impl35(this, (Impl35) r32);
    L41:
        r32.copyWindowDataInto(this);
        return;
    L10:
        if (r0 < 34) goto L15;
        if ((r32 instanceof Impl34) == false) goto L15;
        this.mImpl = new Impl34(this, (Impl34) r32);
    L15:
        if (r0 < 31) goto L20;
        if ((r32 instanceof Impl31) == false) goto L20;
        this.mImpl = new Impl31(this, (Impl31) r32);
    L20:
        if (r0 < 30) goto L25;
        if ((r32 instanceof Impl30) == false) goto L25;
        this.mImpl = new Impl30(this, (Impl30) r32);
    L25:
        if (r0 < 29) goto L30;
        if ((r32 instanceof Impl29) == false) goto L30;
        this.mImpl = new Impl29(this, (Impl29) r32);
    L30:
        if (r0 < 28) goto L35;
        if ((r32 instanceof Impl28) == false) goto L35;
        this.mImpl = new Impl28(this, (Impl28) r32);
    L35:
        if ((r32 instanceof Impl21) == false) goto L38;
        this.mImpl = new Impl21(this, (Impl21) r32);
        goto L41
    L38:
        if ((r32 instanceof Impl20) == false) goto L40;
        this.mImpl = new Impl20(this, (Impl20) r32);
        goto L41
    L40:
        this.mImpl = new Impl(this);
        goto L41
    L43:
        this.mImpl = new Impl(this);
    }

    public static Insets insetInsets(Insets r5, int r6, int r7, int r8, int r9) {
        int r0 = Math.max(0, r5.left - r6);
        int r2 = Math.max(0, r5.top - r7);
        int r3 = Math.max(0, r5.right - r8);
        int r1 = Math.max(0, r5.bottom - r9);
        if (r0 != r6) goto L9;
        if (r2 != r7) goto L9;
        if (r3 != r8) goto L9;
        if (r1 != r9) goto L9;
        return r5;
    L9:
        return Insets.of(r0, r2, r3, r1);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets r1) {
        return toWindowInsetsCompat(r1, null);
    }

    public static WindowInsetsCompat toWindowInsetsCompat(WindowInsets r1, View r2) {
        WindowInsetsCompat r0 = new WindowInsetsCompat((WindowInsets) Preconditions.checkNotNull(r1));
        if (r2 != null) goto L5;
    L7:
        return r0;
    L5:
        if (r2.isAttachedToWindow() == false) goto L7;
        r0.setRootWindowInsets(ViewCompat.getRootWindowInsets(r2));
        r0.init(r2.getRootView());
        r0.setSystemUiVisibility(r2.getWindowSystemUiVisibility());
        goto L7
    }

    @Deprecated
    public WindowInsetsCompat consumeDisplayCutout() {
        return this.mImpl.consumeDisplayCutout();
    }

    @Deprecated
    public WindowInsetsCompat consumeStableInsets() {
        return this.mImpl.consumeStableInsets();
    }

    @Deprecated
    public WindowInsetsCompat consumeSystemWindowInsets() {
        return this.mImpl.consumeSystemWindowInsets();
    }

    public boolean equals(Object r2) {
        if (this != r2) goto L6;
        return true;
    L6:
        if ((r2 instanceof WindowInsetsCompat) == true) goto L10;
        return false;
    L10:
        return ObjectsCompat.equals(this.mImpl, ((WindowInsetsCompat) r2).mImpl);
    }

    public List<Rect> getBoundingRects(int r1) {
        return this.mImpl.getBoundingRects(r1);
    }

    public List<Rect> getBoundingRectsIgnoringVisibility(int r1) {
        return this.mImpl.getBoundingRectsIgnoringVisibility(r1);
    }

    public DisplayCutoutCompat getDisplayCutout() {
        return this.mImpl.getDisplayCutout();
    }

    public Insets getInsets(int r1) {
        return this.mImpl.getInsets(r1);
    }

    public Insets getInsetsIgnoringVisibility(int r1) {
        return this.mImpl.getInsetsIgnoringVisibility(r1);
    }

    @Deprecated
    public int getStableInsetBottom() {
        return this.mImpl.getStableInsets().bottom;
    }

    @Deprecated
    public int getStableInsetLeft() {
        return this.mImpl.getStableInsets().left;
    }

    @Deprecated
    public int getStableInsetRight() {
        return this.mImpl.getStableInsets().right;
    }

    @Deprecated
    public int getStableInsetTop() {
        return this.mImpl.getStableInsets().top;
    }

    @Deprecated
    public Insets getStableInsets() {
        return this.mImpl.getStableInsets();
    }

    @Deprecated
    public int getSystemWindowInsetBottom() {
        return this.mImpl.getSystemWindowInsets().bottom;
    }

    @Deprecated
    public int getSystemWindowInsetLeft() {
        return this.mImpl.getSystemWindowInsets().left;
    }

    @Deprecated
    public int getSystemWindowInsetRight() {
        return this.mImpl.getSystemWindowInsets().right;
    }

    @Deprecated
    public int getSystemWindowInsetTop() {
        return this.mImpl.getSystemWindowInsets().top;
    }

    @Deprecated
    public boolean hasSystemWindowInsets() {
        return !this.mImpl.getSystemWindowInsets().equals(Insets.NONE);
    }

    public int hashCode() {
        Impl r0 = this.mImpl;
        if (r0 != null) goto L7;
        return 0;
    L7:
        return r0.hashCode();
    }

    public void init(View r2) {
        this.mImpl.copyRootViewBounds(r2);
        this.mImpl.initDisplayShape(r2);
        this.mImpl.initTypeBoundingRectsMaps();
    }

    public WindowInsetsCompat inset(int r1, int r2, int r3, int r4) {
        return this.mImpl.inset(r1, r2, r3, r4);
    }

    public boolean isConsumed() {
        return this.mImpl.isConsumed();
    }

    public boolean isRound() {
        return this.mImpl.isRound();
    }

    public boolean isVisible(int r1) {
        return this.mImpl.isVisible(r1);
    }

    @Deprecated
    public WindowInsetsCompat replaceSystemWindowInsets(int r2, int r3, int r4, int r5) {
        return new Builder(this).setSystemWindowInsets(Insets.of(r2, r3, r4, r5)).build();
    }

    public void setDisplayShape(DisplayShapeCompat r1) {
        this.mImpl.setDisplayShape(r1);
    }

    public void setOverriddenInsets(Insets[] r1) {
        this.mImpl.setOverriddenInsets(r1);
    }

    public void setRootViewData(Insets r1) {
        this.mImpl.setRootViewData(r1);
    }

    public void setRootWindowInsets(WindowInsetsCompat r1) {
        this.mImpl.setRootWindowInsets(r1);
    }

    public void setStableInsets(Insets r1) {
        this.mImpl.setStableInsets(r1);
    }

    public void setSystemUiVisibility(int r1) {
        this.mImpl.setSystemUiVisibility(r1);
    }

    public void setTypeBoundingRectsMap(Rect[][] r1) {
        this.mImpl.setTypeBoundingRectsMap(r1);
    }

    public void setTypeMaxBoundingRectsMap(Rect[][] r1) {
        this.mImpl.setTypeMaxBoundingRectsMap(r1);
    }

    public WindowInsets toWindowInsets() {
        Impl r1 = this.mImpl;
        if ((r1 instanceof Impl20) == true) goto L5;
        return null;
    L5:
        return ((Impl20) r1).mPlatformInsets;
    }
}
