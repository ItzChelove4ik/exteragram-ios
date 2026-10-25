package org.telegram.utils.glass;

import android.graphics.RectF;
import android.os.Build;
import android.support.v4.media.session.MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0;
import android.view.View;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Iterator;
import org.telegram.messenger.postdrawcompat.OnPostDrawListener;
import org.telegram.messenger.postdrawcompat.ViewOnPostDrawListener;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawableRenderNode;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.utils.glass.positions.GlassPositionsArray;
import org.telegram.utils.glass.positions.GlassPositionsMerger;

/* loaded from: classes6.dex */
public class GlassEngine {
    private final ArrayList<Object> additionalInvalidationCondition;
    private final GlassPositionsArray allVisibleDrawablesWithDisplayListPositions;
    private final GlassPositionsArray allVisibleDrawablesWithDisplayListPositionsMerged;
    private GlassInvalidationListener glassInvalidationListener;
    private long lastOtherGenerationId;
    private int lastRootHeight;
    private int lastRootWidth;
    private long lastScrollEdgesGenerationId;
    private long lastScrollGenerationId;
    private final OnPostDrawListener onPostDrawListener;
    private long otherGenerationId;
    private final GlassPositionsArray output;
    private GlassPositionsMerger positionsMerger;
    private View rootView;
    private long scrollEdgesGenerationId;
    private long scrollGenerationId;
    private final ArrayList<ThemedSourceColorState> themedSourceColorStates;
    private final RectF tmpPositionRect;
    private final ArrayList<ViewPositionState> viewPositionStates;

    /* renamed from: org.telegram.utils.glass.GlassEngine$1, reason: invalid class name */
    public class AnonymousClass1 extends ViewPager.SimpleOnPageChangeListener {
        final /* synthetic */ GlassEngine this$0;

        public AnonymousClass1(GlassEngine r1) {
            this.this$0 = r1;
        }

        @Override // androidx.viewpager.widget.ViewPager.OnPageChangeListener
        public void onPageScrolled(int r3, float r4, int r5) {
            GlassEngine r2 = this.this$0;
            GlassEngine.m22218$$Nest$fputscrollGenerationId(r2, GlassEngine.m22217$$Nest$fgetscrollGenerationId(r2) + 1);
        }
    }

    /* renamed from: org.telegram.utils.glass.GlassEngine$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GlassEngine this$0;

        public AnonymousClass2(GlassEngine r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r3, int r4) {
            GlassEngine r2 = this.this$0;
            GlassEngine.m22218$$Nest$fputscrollGenerationId(r2, GlassEngine.m22217$$Nest$fgetscrollGenerationId(r2) + 1);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r3, int r4, int r5) {
            GlassEngine r2 = this.this$0;
            GlassEngine.m22218$$Nest$fputscrollGenerationId(r2, GlassEngine.m22217$$Nest$fgetscrollGenerationId(r2) + 1);
        }
    }

    public interface GlassInvalidationListener {
        void onGlassInvalidated(int r1);
    }

    public interface ThemedColorDelegate {
    }

    public static class ThemedSourceColorState {
        /* renamed from: -$$Nest$fgetcolorDelegate, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ThemedColorDelegate m22219$$Nest$fgetcolorDelegate(ThemedSourceColorState r0) {
            throw null;
        }
    }

    public static class ViewPositionState {
        private final BlurredBackgroundDrawable drawable;
        private final RectF drawableBoundsRelativeRoot;
        private final RectF drawableBoundsRelativeRootWithOutset;
        private final RectF drawableBoundsRelativeView;
        private boolean invalidated;
        private final View view;
        private final RectF viewPositionRelativeRoot;
        private boolean visible;

        /* renamed from: -$$Nest$fgetdrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m22220$$Nest$fgetdrawable(ViewPositionState r0) {
            return r0.drawable;
        }

        /* renamed from: -$$Nest$fgetdrawableBoundsRelativeRoot, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m22221$$Nest$fgetdrawableBoundsRelativeRoot(ViewPositionState r0) {
            return r0.drawableBoundsRelativeRoot;
        }

        /* renamed from: -$$Nest$fgetdrawableBoundsRelativeRootWithOutset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m22222$$Nest$fgetdrawableBoundsRelativeRootWithOutset(ViewPositionState r0) {
            return r0.drawableBoundsRelativeRootWithOutset;
        }

        /* renamed from: -$$Nest$fgetdrawableBoundsRelativeView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m22223$$Nest$fgetdrawableBoundsRelativeView(ViewPositionState r0) {
            return r0.drawableBoundsRelativeView;
        }

        /* renamed from: -$$Nest$fgetinvalidated, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22224$$Nest$fgetinvalidated(ViewPositionState r0) {
            return r0.invalidated;
        }

        /* renamed from: -$$Nest$fgetview, reason: not valid java name */
        public static /* bridge */ /* synthetic */ View m22225$$Nest$fgetview(ViewPositionState r0) {
            return r0.view;
        }

        /* renamed from: -$$Nest$fgetviewPositionRelativeRoot, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RectF m22226$$Nest$fgetviewPositionRelativeRoot(ViewPositionState r0) {
            return r0.viewPositionRelativeRoot;
        }

        /* renamed from: -$$Nest$fgetvisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m22227$$Nest$fgetvisible(ViewPositionState r0) {
            return r0.visible;
        }

        /* renamed from: -$$Nest$fputinvalidated, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22228$$Nest$fputinvalidated(ViewPositionState r0, boolean r1) {
            r0.invalidated = r1;
        }

        /* renamed from: -$$Nest$fputvisible, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m22229$$Nest$fputvisible(ViewPositionState r0, boolean r1) {
            r0.visible = r1;
        }

        private ViewPositionState(View r2, BlurredBackgroundDrawable r3) {
            this.viewPositionRelativeRoot = new RectF();
            this.drawableBoundsRelativeView = new RectF();
            this.drawableBoundsRelativeRoot = new RectF();
            this.drawableBoundsRelativeRootWithOutset = new RectF();
            this.view = r2;
            this.drawable = r3;
        }

        public /* synthetic */ ViewPositionState(View r1, BlurredBackgroundDrawable r2, GlassEngineIA r3) {
            this(r1, r2);
        }
    }

    /* renamed from: $r8$lambda$0m73wZXsogvcbP9WC5xSwi_Dm-M, reason: not valid java name */
    public static /* synthetic */ void m22215$r8$lambda$0m73wZXsogvcbP9WC5xSwi_DmM(GlassEngine r0, int r1, boolean r2) {
        r0.lambda$addScrolledView$0(r1, r2);
    }

    /* renamed from: $r8$lambda$7hSYliEEzzJXg6qDwZ-uD6LziUo, reason: not valid java name */
    public static /* synthetic */ void m22216$r8$lambda$7hSYliEEzzJXg6qDwZuD6LziUo(GlassEngine r0) {
        r0.onPostDraw();
    }

    /* renamed from: -$$Nest$fgetscrollGenerationId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m22217$$Nest$fgetscrollGenerationId(GlassEngine r2) {
        return r2.scrollGenerationId;
    }

    /* renamed from: -$$Nest$fputscrollGenerationId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m22218$$Nest$fputscrollGenerationId(GlassEngine r0, long r1) {
        r0.scrollGenerationId = r1;
    }

    public GlassEngine() {
        this.onPostDrawListener = new GlassEngine$$ExternalSyntheticLambda0(this);
        this.additionalInvalidationCondition = new ArrayList();
        this.viewPositionStates = new ArrayList();
        this.positionsMerger = GlassPositionsMerger.DEFAULT;
        this.tmpPositionRect = new RectF();
        this.allVisibleDrawablesWithDisplayListPositions = new GlassPositionsArray();
        this.allVisibleDrawablesWithDisplayListPositionsMerged = new GlassPositionsArray();
        this.output = new GlassPositionsArray();
        this.themedSourceColorStates = new ArrayList();
    }

    private boolean checkColorSources() {
        if (this.themedSourceColorStates.size() > 0) goto L5;
        return false;
    L5:
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(this.themedSourceColorStates.get(0));
        ThemedSourceColorState.m22219$$Nest$fgetcolorDelegate(null);
        throw null;
    }

    private boolean checkDrawablePositionsIfNeeded() {
        int r0 = this.rootView.getWidth();
        int r1 = this.rootView.getHeight();
        int r4 = 0;
        if (this.lastRootWidth == r0) goto L5;
    L8:
        this.lastRootWidth = r0;
        this.lastRootHeight = r1;
        boolean r2 = true;
    L9:
        ArrayList<ViewPositionState> r5 = this.viewPositionStates;
        int r6 = r5.size();
        int r7 = 0;
    L10:
        if (r7 >= r6) goto L54;
        ViewPositionState r8 = r5.get(r7);
        r7 = r7 + 1;
        ViewPositionState r82 = r8;
        if (ViewPositionWatcher.computeRectInParent(ViewPositionState.m22225$$Nest$fgetview(r82), this.rootView, this.tmpPositionRect) == false) goto L10;
        if (ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).equals(this.tmpPositionRect) == true) goto L17;
        ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).set(this.tmpPositionRect);
        ViewPositionState.m22228$$Nest$fputinvalidated(r82, true);
        ViewPositionState.m22220$$Nest$fgetdrawable(r82).setSourceOffset(ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).left, ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).top);
        r2 = true;
    L17:
        this.tmpPositionRect.set(ViewPositionState.m22220$$Nest$fgetdrawable(r82).getBounds());
        if (ViewPositionState.m22223$$Nest$fgetdrawableBoundsRelativeView(r82).equals(this.tmpPositionRect) == true) goto L20;
        ViewPositionState.m22223$$Nest$fgetdrawableBoundsRelativeView(r82).set(this.tmpPositionRect);
        ViewPositionState.m22228$$Nest$fputinvalidated(r82, true);
        r2 = true;
    L20:
        this.tmpPositionRect.offset(ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).left, ViewPositionState.m22226$$Nest$fgetviewPositionRelativeRoot(r82).top);
        if (ViewPositionState.m22221$$Nest$fgetdrawableBoundsRelativeRoot(r82).equals(this.tmpPositionRect) == true) goto L23;
        ViewPositionState.m22221$$Nest$fgetdrawableBoundsRelativeRoot(r82).set(this.tmpPositionRect);
        ViewPositionState.m22228$$Nest$fputinvalidated(r82, true);
        r2 = true;
    L23:
        this.tmpPositionRect.set(ViewPositionState.m22221$$Nest$fgetdrawableBoundsRelativeRoot(r82));
        this.tmpPositionRect.inset(-ViewPositionState.m22220$$Nest$fgetdrawable(r82).getOutsetX(), -ViewPositionState.m22220$$Nest$fgetdrawable(r82).getOutsetY());
        if (ViewPositionState.m22222$$Nest$fgetdrawableBoundsRelativeRootWithOutset(r82).equals(this.tmpPositionRect) == true) goto L26;
        ViewPositionState.m22222$$Nest$fgetdrawableBoundsRelativeRootWithOutset(r82).set(this.tmpPositionRect);
        ViewPositionState.m22228$$Nest$fputinvalidated(r82, true);
        r2 = true;
    L26:
        View r9 = ViewPositionState.m22225$$Nest$fgetview(r82);
        if (ViewPositionState.m22221$$Nest$fgetdrawableBoundsRelativeRoot(r82).isEmpty() == false) goto L29;
    L43:
        boolean r92 = false;
    L44:
        if (r92 == true) goto L46;
    L48:
        boolean r10 = false;
    L50:
        if (ViewPositionState.m22227$$Nest$fgetvisible(r82) != r92) goto L53;
        if (r92 == false) goto L10;
        if (r10 == true) goto L10;
    L53:
        ViewPositionState.m22229$$Nest$fputvisible(r82, r92);
        ViewPositionState.m22228$$Nest$fputinvalidated(r82, true);
        r2 = true;
        goto L10
    L46:
        if (ViewPositionState.m22220$$Nest$fgetdrawable(r82).hasDisplayList() == false) goto L48;
        r10 = true;
        goto L50
    L29:
        if (r9.isAttachedToWindow() == false) goto L43;
        if (ViewPositionState.m22221$$Nest$fgetdrawableBoundsRelativeRoot(r82).intersects(0.0f, 0.0f, r0, r1) == false) goto L43;
        if (ViewPositionState.m22220$$Nest$fgetdrawable(r82).getAlpha() <= 0) goto L43;
        if (r9.getVisibility() != 0) goto L43;
        if (r9.getAlpha() <= 0.0f) goto L43;
        if (r9.getScaleX() == 0.0f) goto L43;
        if (r9.getScaleY() == 0.0f) goto L43;
        r92 = true;
        goto L44
    L54:
        if (r2 == false) goto L61;
        this.allVisibleDrawablesWithDisplayListPositions.clear();
        ArrayList<ViewPositionState> r02 = this.viewPositionStates;
        int r12 = r02.size();
    L56:
        if (r4 >= r12) goto L60;
        ViewPositionState r3 = r02.get(r4);
        r4 = r4 + 1;
        ViewPositionState r32 = r3;
        if (ViewPositionState.m22227$$Nest$fgetvisible(r32) == false) goto L56;
        this.allVisibleDrawablesWithDisplayListPositions.add(ViewPositionState.m22222$$Nest$fgetdrawableBoundsRelativeRootWithOutset(r32));
        goto L56
    L60:
        this.positionsMerger.merge(this.allVisibleDrawablesWithDisplayListPositions, this.allVisibleDrawablesWithDisplayListPositionsMerged);
    L61:
        return r2;
    L5:
        if (this.lastRootHeight != r1) goto L8;
        r2 = false;
        goto L9
    }

    private /* synthetic */ void lambda$addScrolledView$0(int r3, boolean r4) {
        this.scrollEdgesGenerationId++;
    }

    private void onPostDraw() {
        boolean r0 = checkDrawablePositionsIfNeeded();
        boolean r1 = checkColorSources();
        Iterator<Object> r2 = this.additionalInvalidationCondition.iterator();
        if (r2.hasNext() == false) goto L5;
        MediaSessionCompat$$ExternalSyntheticThrowCCEIfNotNull0.m(r2.next());
        throw null;
    L5:
        if (r0 == false) goto L7;
        int r02 = 4;
    L8:
        if (r1 == false) goto L10;
        r02 = r02 | 16;
    L10:
        long r3 = this.lastOtherGenerationId;
        long r5 = this.otherGenerationId;
        if (r3 == r5) goto L13;
        this.lastOtherGenerationId = r5;
        r02 = r02 | 8;
    L13:
        long r32 = this.lastScrollEdgesGenerationId;
        long r52 = this.scrollEdgesGenerationId;
        if (r32 == r52) goto L16;
        this.lastScrollEdgesGenerationId = r52;
        r02 = r02 | 2;
    L16:
        long r33 = this.lastScrollGenerationId;
        long r53 = this.scrollGenerationId;
        if (r33 == r53) goto L19;
        this.lastScrollGenerationId = r53;
        r02 = r02 | 1;
    L19:
        if (r02 == 0) goto L35;
        GlassInvalidationListener r12 = this.glassInvalidationListener;
        if (r12 == null) goto L23;
        r12.onGlassInvalidated(r02);
    L23:
        ArrayList<ViewPositionState> r7 = this.viewPositionStates;
        int r03 = r7.size();
        int r13 = 0;
    L24:
        if (r13 >= r03) goto L49;
        ViewPositionState r34 = r7.get(r13);
        r13 = r13 + 1;
        ViewPositionState r35 = r34;
        if (ViewPositionState.m22227$$Nest$fgetvisible(r35) == false) goto L24;
        if (ViewPositionState.m22224$$Nest$fgetinvalidated(r35) == false) goto L24;
        ViewPositionState.m22228$$Nest$fputinvalidated(r35, false);
        if (Build.VERSION.SDK_INT < 29) goto L34;
        if ((ViewPositionState.m22220$$Nest$fgetdrawable(r35) instanceof BlurredBackgroundDrawableRenderNode) == false) goto L34;
        ViewPositionState.m22220$$Nest$fgetdrawable(r35).updateDisplayList();
    L34:
        ViewPositionState.m22220$$Nest$fgetdrawable(r35).invalidateSelf();
        goto L24
    L49:
        return;
    L35:
        return;
    L7:
        r02 = 0;
        goto L8
    }

    public void addScrolledView(ViewPager r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.addOnPageChangeListener(new AnonymousClass1(this));
    }

    public void addScrolledView(RecyclerListView r2) {
        if (r2 != null) goto L4;
        return;
    L4:
        r2.addEdgeEffectListener(new GlassEngine$$ExternalSyntheticLambda1(this));
        r2.addOnScrollListener(new AnonymousClass2(this));
    }

    public GlassPositionsArray getAllVisibleDrawablesWithDisplayListPositionsMerged() {
        this.output.set(this.allVisibleDrawablesWithDisplayListPositionsMerged);
        return this.output;
    }

    public void invalidate() {
        this.otherGenerationId++;
    }

    public void registerDrawable(View r3, BlurredBackgroundDrawable r4) {
        this.viewPositionStates.add(new ViewPositionState(r3, r4, null));
    }

    public void setGlassInvalidationListener(GlassInvalidationListener r1) {
        this.glassInvalidationListener = r1;
    }

    public void setPositionsMerger(GlassPositionsMerger r1) {
        this.positionsMerger = r1;
    }

    public void setRoot(View r3) {
        View r0 = this.rootView;
        if (r0 == r3) goto L10;
        if (r0 == null) goto L6;
        ViewOnPostDrawListener.removeListener(r0, this.onPostDrawListener);
    L6:
        if (r3 == null) goto L8;
        ViewOnPostDrawListener.addListener(r3, this.onPostDrawListener);
    L8:
        this.rootView = r3;
        return;
    }
}
