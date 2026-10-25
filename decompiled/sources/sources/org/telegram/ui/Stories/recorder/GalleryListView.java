package org.telegram.ui.Stories.recorder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.text.StaticLayout;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.LruCache;
import android.util.Pair;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.rop.code.RegisterSpec;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.FileLoader;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextBoldCursor;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.recyclerview.LinearSmoothScrollerCustom;

/* loaded from: classes7.dex */
public abstract class GalleryListView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private static final MediaController.AlbumEntry draftsAlbum = null;
    private final float ASPECT_RATIO;
    public final ActionBar actionBar;
    public boolean actionBarShown;
    private final AnimatedFloat actionBarT;
    public final Adapter adapter;
    private final Paint backgroundPaint;
    private final ButtonWithCounterView button1View;
    private final ButtonWithCounterView button2View;
    private final LinearLayout buttonsLayout;
    private boolean buttonsLayoutVisible;
    public final boolean collaging;
    private boolean containsDraftFolder;
    private boolean containsDrafts;
    private final int currentAccount;
    private final ArrayList<StoryEntry> drafts;
    private final TextView dropDown;
    private ArrayList<MediaController.AlbumEntry> dropDownAlbums;
    private final ActionBarMenuItem dropDownContainer;
    private final Drawable dropDownDrawable;
    public boolean firstLayout;
    private HeaderView headerView;
    public boolean ignoreScroll;
    private final KeyboardNotifier keyboardNotifier;
    public final GridLayoutManager layoutManager;
    public final RecyclerListView listView;
    private int maxCount;
    private boolean multipleOnClick;
    private Runnable onBackClickListener;
    private Utilities.Callback2<Object, Bitmap> onSelectListener;
    private Utilities.Callback3<Boolean, ArrayList<MediaController.PhotoEntry>, ArrayList<Bitmap>> onSelectMultipleListener;
    public final boolean onlyCollaging;
    public final boolean onlyPhotos;
    public ArrayList<MediaController.PhotoEntry> photos;
    private final Theme.ResourcesProvider resourcesProvider;
    private final SearchAdapter searchAdapterImages;
    private final FrameLayout searchContainer;
    private final StickerEmptyView searchEmptyView;
    private final ActionBarMenuItem searchItem;
    private final GridLayoutManager searchLayoutManager;
    private final RecyclerListView searchListView;
    private final ImageView selectButton;
    public MediaController.AlbumEntry selectedAlbum;
    public final ArrayList<MediaController.PhotoEntry> selectedPhotos;
    private int shiftDp;

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass1(GalleryListView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            if (this.this$0.ignoreScroll == false) goto L7;
            return false;
        L7:
            return super.dispatchTouchEvent(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r2) {
            if (this.this$0.ignoreScroll == false) goto L7;
            return false;
        L7:
            return super.onInterceptTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.ItemDecoration {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass10(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r0 = AndroidUtilities.dp(4.0f);
            r1.top = 0;
            r1.bottom = r0;
            r1.right = r0;
            r1.left = r0;
            if ((r3.getChildAdapterPosition(r2) % 3) == 2) goto L6;
            r1.right = 0;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$11, reason: invalid class name */
    public class AnonymousClass11 extends FlickerLoadingView {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass11(GalleryListView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getColumnsCount() {
            return 3;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$12, reason: invalid class name */
    public class AnonymousClass12 extends ActionBarMenuItem.ActionBarMenuItemSearchListener {
        private AnimatorSet animatorSet;
        final /* synthetic */ GalleryListView this$0;

        /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$12$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass12 this$1;
            final /* synthetic */ View val$searchField;

            public AnonymousClass1(AnonymousClass12 r1, View r2) {
                this.this$1 = r1;
                this.val$searchField = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                View r22 = this.val$searchField;
                if (r22 == null) goto L5;
                r22.setVisibility(4);
            L5:
                GalleryListView.m20020$$Nest$fgetsearchContainer(this.this$1.this$0).setVisibility(8);
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$12$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass12 this$1;

            public AnonymousClass2(AnonymousClass12 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$1.this$0).setVisibility(8);
                this.this$1.this$0.listView.setVisibility(8);
            }
        }

        public static /* synthetic */ void $r8$lambda$CANoCI2AEgPZYL0mpJjkIl9C67Y(AnonymousClass12 r0, ValueAnimator r1) {
            r0.lambda$onSearchCollapse$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$RYnneEcqZDksqvzScU3E00a5qzY(AnonymousClass12 r0, ValueAnimator r1) {
            r0.lambda$onSearchExpand$1(r1);
        }

        public AnonymousClass12(GalleryListView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onSearchCollapse$0(ValueAnimator r1) {
            this.this$0.invalidate();
        }

        private /* synthetic */ void lambda$onSearchExpand$1(ValueAnimator r1) {
            this.this$0.invalidate();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchCollapse() {
            AnimatorSet r0 = this.animatorSet;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            ArrayList r02 = new ArrayList();
            GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0).setVisibility(0);
            ActionBarMenuItem r1 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r3 = View.SCALE_X;
            r02.add(ObjectAnimator.ofFloat(r1, r3, new float[]{1.0f}));
            ActionBarMenuItem r12 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r5 = View.SCALE_Y;
            r02.add(ObjectAnimator.ofFloat(r12, r5, new float[]{1.0f}));
            ActionBarMenuItem r13 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r7 = View.ALPHA;
            r02.add(ObjectAnimator.ofFloat(r13, r7, new float[]{1.0f}));
            EditTextBoldCursor r14 = GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0).getSearchField();
            if (r14 == null) goto L8;
            r02.add(ObjectAnimator.ofFloat(r14, r3, new float[]{0.8f}));
            r02.add(ObjectAnimator.ofFloat(r14, r5, new float[]{0.8f}));
            r02.add(ObjectAnimator.ofFloat(r14, r7, new float[]{0.0f}));
        L8:
            this.this$0.listView.setVisibility(0);
            r02.add(ObjectAnimator.ofFloat(this.this$0.listView, r7, new float[]{1.0f}));
            this.this$0.listView.setFastScrollVisible(true);
            r02.add(ObjectAnimator.ofFloat(GalleryListView.m20020$$Nest$fgetsearchContainer(this.this$0), r7, new float[]{0.0f}));
            ValueAnimator r2 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r2.addUpdateListener(new GalleryListView$12$$ExternalSyntheticLambda1(this));
            r02.add(r2);
            AnimatorSet r22 = new AnimatorSet();
            this.animatorSet = r22;
            r22.setDuration(320);
            this.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.animatorSet.playTogether(r02);
            this.animatorSet.addListener(new AnonymousClass1(this, r14));
            this.animatorSet.start();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onSearchExpand() {
            AnimatorSet r0 = this.animatorSet;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            ArrayList r02 = new ArrayList();
            ActionBarMenuItem r1 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r2 = View.SCALE_X;
            r02.add(ObjectAnimator.ofFloat(r1, r2, new float[]{0.8f}));
            ActionBarMenuItem r12 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r4 = View.SCALE_Y;
            r02.add(ObjectAnimator.ofFloat(r12, r4, new float[]{0.8f}));
            ActionBarMenuItem r13 = GalleryListView.m20015$$Nest$fgetdropDownContainer(this.this$0);
            Property r6 = View.ALPHA;
            r02.add(ObjectAnimator.ofFloat(r13, r6, new float[]{0.0f}));
            EditTextBoldCursor r14 = GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0).getSearchField();
            if (r14 == null) goto L8;
            r14.setVisibility(0);
            r14.setHandlesColor(-1);
            r02.add(ObjectAnimator.ofFloat(r14, r2, new float[]{1.0f}));
            r02.add(ObjectAnimator.ofFloat(r14, r4, new float[]{1.0f}));
            r02.add(ObjectAnimator.ofFloat(r14, r6, new float[]{1.0f}));
        L8:
            GalleryListView.m20020$$Nest$fgetsearchContainer(this.this$0).setVisibility(0);
            r02.add(ObjectAnimator.ofFloat(this.this$0.listView, r6, new float[]{0.0f}));
            this.this$0.listView.setFastScrollVisible(false);
            r02.add(ObjectAnimator.ofFloat(GalleryListView.m20020$$Nest$fgetsearchContainer(this.this$0), r6, new float[]{1.0f}));
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(this.this$0).setVisibility(0);
            ValueAnimator r15 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r15.addUpdateListener(new GalleryListView$12$$ExternalSyntheticLambda0(this));
            r02.add(r15);
            AnimatorSet r16 = new AnimatorSet();
            this.animatorSet = r16;
            r16.setDuration(320);
            this.animatorSet.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.animatorSet.playTogether(r02);
            this.animatorSet.addListener(new AnonymousClass2(this));
            this.animatorSet.start();
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem.ActionBarMenuItemSearchListener
        public void onTextChanged(EditText r1) {
            String r12 = r1.getText().toString();
            GalleryListView.m20019$$Nest$fgetsearchAdapterImages(this.this$0).load(r12);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$13, reason: invalid class name */
    public class AnonymousClass13 extends AnimatorListenerAdapter {
        final /* synthetic */ GalleryListView this$0;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass13(GalleryListView r1, boolean r2) {
            this.this$0 = r1;
            this.val$visible = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            if (this.val$visible == true) goto L6;
            GalleryListView.m20008$$Nest$fgetbuttonsLayout(this.this$0).setVisibility(8);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$2, reason: invalid class name */
    public class AnonymousClass2 extends GridLayoutManager {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass2(GalleryListView r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void onLayoutChildren(RecyclerView.Recycler r1, RecyclerView.State r2) {
            super.onLayoutChildren(r1, r2);
            GalleryListView r0 = this.this$0;
            if (r0.firstLayout == false) goto L6;
            r0.firstLayout = false;
            r0.firstLayout();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$3, reason: invalid class name */
    public class AnonymousClass3 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass3(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r2) {
            if (r2 != 0) goto L4;
            return 3;
        L4:
            if (r2 != 1) goto L6;
            return 3;
        L6:
            if (r2 == (this.this$0.adapter.getItemCount() - 1)) goto L12;
            return 1;
        L12:
            return 3;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.ItemDecoration {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass4(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
        public void getItemOffsets(Rect r1, View r2, RecyclerView r3, RecyclerView.State r4) {
            int r0 = AndroidUtilities.dp(5.0f);
            r1.right = r0;
            r1.bottom = r0;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass5(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.onScroll();
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$6, reason: invalid class name */
    public class AnonymousClass6 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass6(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r3) {
            if (r3 != (-1)) goto L9;
            if (GalleryListView.m20017$$Nest$fgetonBackClickListener(this.this$0) == null) goto L12;
            GalleryListView.m20017$$Nest$fgetonBackClickListener(this.this$0).run();
            return;
        L12:
            return;
        L9:
            if (r3 < 10) goto L13;
            GalleryListView r2 = this.this$0;
            GalleryListView.m20027$$Nest$mselectAlbum(r2, (MediaController.AlbumEntry) GalleryListView.m20014$$Nest$fgetdropDownAlbums(r2).get(r3 - 10), false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$7, reason: invalid class name */
    public class AnonymousClass7 extends ActionBarMenuItem {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass7(GalleryListView r1, Context r2, ActionBarMenu r3, int r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.ActionBar.ActionBarMenuItem, android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r1) {
            super.onInitializeAccessibilityNodeInfo(r1);
            r1.setText(GalleryListView.m20013$$Nest$fgetdropDown(this.this$0).getText());
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$8, reason: invalid class name */
    public class AnonymousClass8 extends SearchAdapter {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass8(GalleryListView r2) {
            this.this$0 = r2;
            super(r2, null);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            super.notifyDataSetChanged();
            boolean r0 = TextUtils.isEmpty(this.query);
            GalleryListView r1 = this.this$0;
            if (r0 == false) goto L6;
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(r1).setStickerType(11);
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(this.this$0).title.setText(LocaleController.getString(R.string.SearchImagesType));
            return;
        L6:
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(r1).setStickerType(1);
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(this.this$0).title.setText(LocaleController.formatString(R.string.NoResultFoundFor, new Object[]{this.query}));
        }

        @Override // org.telegram.ui.Stories.recorder.GalleryListView.SearchAdapter
        public void onLoadingUpdate(boolean r2) {
            if (GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0) == null) goto L5;
            GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0).setShowSearchProgress(r2);
        L5:
            GalleryListView.m20021$$Nest$fgetsearchEmptyView(this.this$0).showProgress(r2, true);
        }
    }

    /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ GalleryListView this$0;

        public AnonymousClass9(GalleryListView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (GalleryListView.m20023$$Nest$fgetsearchListView(this.this$0).scrollingByUser == true) goto L5;
            return;
        L5:
            if (GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0) != null) goto L7;
            return;
        L7:
            if (GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0).getSearchField() == null) goto L12;
            AndroidUtilities.hideKeyboard(GalleryListView.m20022$$Nest$fgetsearchItem(this.this$0).getSearchContainer());
            return;
        }
    }

    public class Adapter extends RecyclerListView.FastScrollAdapter {
        final /* synthetic */ GalleryListView this$0;

        /* renamed from: $r8$lambda$044BHjiuwE-1tJEwR43FtobvhUI, reason: not valid java name */
        public static /* synthetic */ void m20029$r8$lambda$044BHjiuwE1tJEwR43FtobvhUI(Adapter r0, Cell r1) {
            r0.lambda$onBindViewHolder$1(r1);
        }

        /* renamed from: $r8$lambda$J4tnm-c6qeuODI61kpqbqOsOGMU, reason: not valid java name */
        public static /* synthetic */ void m20030$r8$lambda$J4tnmc6qeuODI61kpqbqOsOGMU(Adapter r0, MediaController.PhotoEntry r1, Cell r2, View r3) {
            r0.lambda$onBindViewHolder$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$aI2en2AJi9ziXfkaE5wlRaftluY(Adapter r0, Cell r1) {
            r0.lambda$onBindViewHolder$0(r1);
        }

        private Adapter(GalleryListView r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(GalleryListView r1, GalleryListViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onBindViewHolder$0(Cell r3) {
            int r0 = this.this$0.listView.getChildAdapterPosition(r3);
            if (r0 == (-1)) goto L6;
            this.this$0.listView.clickItem(r3, r0);
            return;
        }

        private /* synthetic */ void lambda$onBindViewHolder$1(Cell r3) {
            int r0 = this.this$0.listView.getChildAdapterPosition(r3);
            if (r0 == (-1)) goto L6;
            this.this$0.listView.longClickItem(r3, r0);
            return;
        }

        private /* synthetic */ void lambda$onBindViewHolder$2(MediaController.PhotoEntry r3, Cell r4, View r5) {
            boolean r52 = this.this$0.selectedPhotos.contains(r3);
            GalleryListView r0 = this.this$0;
            if (r52 == false) goto L5;
            r0.selectedPhotos.remove(r3);
        L10:
            AndroidUtilities.updateVisibleRows(this.this$0.listView);
            this.this$0.updateSelectButtonVisible();
            return;
        L5:
            int r53 = r0.selectedPhotos.size() + 1;
            int r02 = GalleryListView.m20016$$Nest$fgetmaxCount(this.this$0);
            GalleryListView r1 = this.this$0;
            if (r53 <= r02) goto L9;
            int r2 = -GalleryListView.m20024$$Nest$fgetshiftDp(r1);
            GalleryListView.m20026$$Nest$fputshiftDp(r1, r2);
            AndroidUtilities.shakeViewSpring(r4, r2);
            BotWebViewVibrationEffect.APP_ERROR.vibrate();
            return;
        L9:
            r1.selectedPhotos.add(r3);
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return getTotalItemsCount() + 3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != 0) goto L4;
            return 0;
        L4:
            if (r2 == (getItemCount() - 1)) goto L12;
            if (r2 != 1) goto L8;
            return 1;
        L8:
            return 2;
        L12:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r7) {
            int r0 = r7 - 2;
            if (GalleryListView.m20009$$Nest$fgetcontainsDraftFolder(this.this$0) == false) goto L8;
            if (r0 != 0) goto L6;
            return null;
        L6:
            r0 = r7 - 3;
        L15:
            ArrayList<MediaController.PhotoEntry> r72 = this.this$0.photos;
            if (r72 == null) goto L27;
            if (r0 < 0) goto L27;
            if (r0 >= r72.size()) goto L27;
            MediaController.PhotoEntry r6 = this.this$0.photos.get(r0);
            if (r6 == null) goto L27;
            long r62 = r6.dateTaken;
            if (Build.VERSION.SDK_INT > 28) goto L26;
            r62 = r62 / 1000;
        L26:
            return LocaleController.formatYearMont(r62, true);
        L27:
            return null;
        L8:
            if (GalleryListView.m20010$$Nest$fgetcontainsDrafts(this.this$0) == false) goto L15;
            if (r0 >= 0) goto L11;
        L14:
            r0 = r0 - GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size();
            goto L15
        L11:
            if (r0 >= GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size()) goto L14;
            return LocaleController.formatYearMont(((StoryEntry) GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).get(r0)).draftDate / 1000, true);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r6, float r7, int[] r8) {
            int r0 = getTotalItemsCount();
            int r1 = (int) (((int) (((r6.getWidth() - r6.getPaddingLeft()) - r6.getPaddingRight()) / this.this$0.layoutManager.getSpanCount())) * GalleryListView.m20007$$Nest$fgetASPECT_RATIO(this.this$0));
            int r02 = (int) Math.ceil(r0 / this.this$0.layoutManager.getSpanCount());
            float r72 = (AndroidUtilities.lerp(0, Math.max(0, r2 - ((AndroidUtilities.displaySize.y - r6.getPaddingTop()) - r6.getPaddingBottom())), r7) / (r02 * r1)) * r02;
            int r03 = Math.round(r72);
            r8[0] = Math.max(0, this.this$0.layoutManager.getSpanCount() * r03) + 2;
            r8[1] = r6.getPaddingTop() + ((int) ((r72 - r03) * r1));
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public float getScrollProgress(RecyclerListView r5) {
            int r0 = getTotalItemsCount();
            int r02 = ((int) Math.ceil(r0 / this.this$0.layoutManager.getSpanCount())) * ((int) (((int) (((r5.getWidth() - r5.getPaddingLeft()) - r5.getPaddingRight()) / this.this$0.layoutManager.getSpanCount())) * GalleryListView.m20007$$Nest$fgetASPECT_RATIO(this.this$0)));
            int r1 = AndroidUtilities.displaySize.y - r5.getPaddingTop();
            return (Math.max(0, r5.computeVerticalScrollOffset() - this.this$0.getPadding()) - r5.getPaddingTop()) / (r02 - r1);
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public int getTotalItemsCount() {
            ArrayList<MediaController.PhotoEntry> r0 = this.this$0.photos;
            if (r0 != null) goto L5;
            int r02 = 0;
        L7:
            if (GalleryListView.m20009$$Nest$fgetcontainsDraftFolder(this.this$0) == false) goto L11;
            return r02 + 1;
        L11:
            if (GalleryListView.m20010$$Nest$fgetcontainsDrafts(this.this$0) == true) goto L13;
            return r02;
        L13:
            return r02 + GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size();
        L5:
            r02 = r0.size();
            goto L7
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r6, int r7) {
            int r0 = r6.getItemViewType();
            int r1 = -1;
            if (r0 != 0) goto L10;
            EmptyView r62 = (EmptyView) r6.itemView;
            if (r7 != 0) goto L7;
            r1 = this.this$0.getPadding();
        L7:
            r62.setHeight(r1);
            return;
        L10:
            if (r0 != 2) goto L47;
            Cell r63 = (Cell) r6.itemView;
            boolean r02 = true;
            if (r7 != 2) goto L14;
            boolean r2 = true;
        L16:
            if (r7 != 4) goto L18;
            boolean r4 = true;
        L19:
            r63.setRounding(r2, r4);
            r63.accessibilityClick = new GalleryListView$Adapter$$ExternalSyntheticLambda0(this, r63);
            r63.accessibilityLongClick = new GalleryListView$Adapter$$ExternalSyntheticLambda1(this, r63);
            int r22 = r7 - 2;
            if (GalleryListView.m20009$$Nest$fgetcontainsDraftFolder(this.this$0) == false) goto L26;
            if (r22 != 0) goto L24;
            r63.setCheckbox(false, -1, false);
            r63.set((StoryEntry) GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).get(0), GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size());
            return;
        L24:
            r22 = r7 - 3;
        L33:
            ArrayList<MediaController.PhotoEntry> r72 = this.this$0.photos;
            if (r72 == null) goto L48;
            if (r22 >= 0) goto L37;
            return;
        L37:
            if (r22 >= r72.size()) goto L51;
            MediaController.PhotoEntry r73 = this.this$0.photos.get(r22);
            boolean r12 = this.this$0.isMultiple();
            int r23 = this.this$0.selectedPhotos.indexOf(r73);
            if (Cell.m20034$$Nest$fgetcurrentObject(r63) == r73) goto L43;
            r02 = false;
        L43:
            r63.setCheckbox(r12, r23, r02);
            r63.set(r73);
            if (this.this$0.collaging == false) goto L50;
            r63.checkBoxContainer.setOnClickListener(new GalleryListView$Adapter$$ExternalSyntheticLambda2(this, r73, r63));
            return;
        L50:
            return;
        L51:
            return;
        L48:
            return;
        L26:
            if (GalleryListView.m20010$$Nest$fgetcontainsDrafts(this.this$0) == false) goto L33;
            if (r22 >= 0) goto L29;
        L32:
            r22 = r22 - GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size();
            goto L33
        L29:
            if (r22 >= GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size()) goto L32;
            r63.setCheckbox(false, -1, false);
            r63.set((StoryEntry) GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).get(r22), 0);
            return;
        L18:
            r4 = false;
            goto L19
        L14:
            r2 = false;
            goto L16
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            GalleryListView r2 = this.this$0;
            View r32 = new EmptyView(r2, r2.getContext());
        L9:
            return new RecyclerListView.Holder(r32);
        L5:
            if (r4 != 1) goto L7;
            GalleryListView r33 = this.this$0;
            GalleryListView r0 = this.this$0;
            HeaderView r42 = new HeaderView(r0, r0.getContext(), this.this$0.onlyPhotos);
            GalleryListView.m20025$$Nest$fputheaderView(r33, r42);
            r32 = r42;
            goto L9
        L7:
            r32 = new Cell(this.this$0.getContext(), GalleryListView.m20018$$Nest$fgetresourcesProvider(this.this$0), GalleryListView.m20007$$Nest$fgetASPECT_RATIO(this.this$0), this.this$0.collaging);
            goto L9
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onViewAttachedToWindow(RecyclerView.ViewHolder r4) {
            super.onViewAttachedToWindow(r4);
            if (r4.getItemViewType() != 2) goto L10;
            Cell r42 = (Cell) r4.itemView;
            if ((Cell.m20034$$Nest$fgetcurrentObject(r42) instanceof MediaController.PhotoEntry) == false) goto L8;
            MediaController.PhotoEntry r0 = (MediaController.PhotoEntry) Cell.m20034$$Nest$fgetcurrentObject(r42);
            r42.setCheckbox(this.this$0.isMultiple(), this.this$0.selectedPhotos.indexOf(r0), false);
            return;
        L8:
            r42.setCheckbox(false, -1, false);
            return;
        }
    }

    public static class Cell extends FrameLayout {
        private static ArrayList<DispatchQueue> allQueues;
        private static int allQueuesIndex;
        private static final LruCache<String, Bitmap> bitmapsCache = null;
        private static final HashMap<String, Integer> bitmapsUseCounts = null;
        public Runnable accessibilityClick;
        public Runnable accessibilityLongClick;
        private CharSequence accessibilityText;
        private final boolean alwaysShowCheckbox;
        private float aspectRatio;
        private final Paint bgPaint;
        private Bitmap bitmap;
        private final Matrix bitmapMatrix;
        private final Paint bitmapPaint;
        public CheckBox2 checkBox;
        public FrameLayout checkBoxContainer;
        private final Path clipPath;
        private String currentKey;
        private Object currentObject;
        private StaticLayout draftLayout;
        private float draftLayoutLeft;
        private float draftLayoutWidth;
        private final TextPaint draftTextPaint;
        private boolean drawDurationPlay;
        private final Paint durationBackgroundPaint;
        private StaticLayout durationLayout;
        private float durationLayoutLeft;
        private float durationLayoutWidth;
        private final Drawable durationPlayDrawable;
        private final TextPaint durationTextPaint;
        private LinearGradient gradient;
        private final Matrix gradientMatrix;
        private final Paint gradientPaint;
        private Runnable loadingBitmap;
        private DispatchQueue myQueue;
        private final Paint paintUnderCheck;
        private final float[] radii;
        private boolean topLeft;
        private boolean topRight;
        private final Runnable unload;

        /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$Cell$1, reason: invalid class name */
        public class AnonymousClass1 extends CheckBox2 {
            final /* synthetic */ Cell this$0;

            public AnonymousClass1(Cell r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$0 = r1;
                super(r2, r3, r4);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$0.invalidate();
            }
        }

        /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$Cell$2, reason: invalid class name */
        public class AnonymousClass2 extends LruCache<String, Bitmap> {
            public AnonymousClass2(int r1) {
                super(r1);
            }

            @Override // android.util.LruCache
            public /* bridge */ /* synthetic */ void entryRemoved(boolean r1, String r2, Bitmap r3, Bitmap r4) {
                entryRemoved2(r1, r2, r3, r4);
            }

            /* renamed from: entryRemoved, reason: avoid collision after fix types in other method */
            public void entryRemoved2(boolean r1, String r2, Bitmap r3, Bitmap r4) {
                if (r3.isRecycled() == false) goto L5;
                return;
            L5:
                if (Cell.m20035$$Nest$sfgetbitmapsUseCounts().containsKey(r2) == true) goto L10;
                r3.recycle();
                return;
            }
        }

        /* renamed from: $r8$lambda$4FYrQgSL2GJ6KRMKvi8xfI-pWoM, reason: not valid java name */
        public static /* synthetic */ void m20031$r8$lambda$4FYrQgSL2GJ6KRMKvi8xfIpWoM(Cell r0, boolean r1) {
            r0.lambda$setCheckbox$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$9VTbWBopQEE8qLi4aPgghpvZDwA(Cell r0, Object r1, String r2) {
            r0.lambda$loadBitmap$3(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$SjHhbtBIqegS7xBRFvLWfZTieqY(Cell r0, String r1, Pair r2) {
            r0.lambda$loadBitmap$2(r1, r2);
        }

        /* renamed from: $r8$lambda$yGZsJxPpPExlAuEfSHXAM-jYf9w, reason: not valid java name */
        public static /* synthetic */ void m20032$r8$lambda$yGZsJxPpPExlAuEfSHXAMjYf9w(Cell r0) {
            r0.lambda$new$0();
        }

        /* renamed from: -$$Nest$fgetbitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Bitmap m20033$$Nest$fgetbitmap(Cell r0) {
            return r0.bitmap;
        }

        /* renamed from: -$$Nest$fgetcurrentObject, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Object m20034$$Nest$fgetcurrentObject(Cell r0) {
            return r0.currentObject;
        }

        /* renamed from: -$$Nest$sfgetbitmapsUseCounts, reason: not valid java name */
        public static /* bridge */ /* synthetic */ HashMap m20035$$Nest$sfgetbitmapsUseCounts() {
            return bitmapsUseCounts;
        }

        static {
            allQueues = new ArrayList();
            bitmapsUseCounts = new HashMap();
            bitmapsCache = new AnonymousClass2(45);
        }

        public Cell(Context r8, Theme.ResourcesProvider r9, float r10, boolean r11) {
            super(r8);
            this.bitmapPaint = new Paint(3);
            Paint r0 = new Paint(1);
            this.bgPaint = r0;
            this.gradientPaint = new Paint(1);
            this.bitmapMatrix = new Matrix();
            this.gradientMatrix = new Matrix();
            Paint r2 = new Paint(1);
            this.durationBackgroundPaint = r2;
            TextPaint r3 = new TextPaint(1);
            this.durationTextPaint = r3;
            TextPaint r4 = new TextPaint(1);
            this.draftTextPaint = r4;
            this.unload = new GalleryListView$Cell$$ExternalSyntheticLambda0(this);
            this.clipPath = new Path();
            this.radii = new float[8];
            this.paintUnderCheck = new Paint(1);
            this.aspectRatio = r10;
            this.alwaysShowCheckbox = r11;
            r0.setColor(285212671);
            r2.setColor(1275068416);
            r3.setTypeface(AndroidUtilities.bold());
            r3.setTextSize(AndroidUtilities.dpf2(12.66f));
            r3.setColor(-1);
            r4.setTextSize(AndroidUtilities.dp(11.33f));
            r4.setColor(-1);
            this.durationPlayDrawable = r8.getResources().getDrawable(R.drawable.play_mini_video).mutate();
            AnonymousClass1 r102 = new AnonymousClass1(this, r8, 24, r9);
            this.checkBox = r102;
            if (r11 == true) goto L5;
            r102.setDrawBackgroundAsArc(6);
        L6:
            this.checkBox.setColor(Theme.key_chat_attachCheckBoxBackground, Theme.key_chat_attachPhotoBackground, Theme.key_chat_attachCheckBoxCheck);
            this.checkBox.getCheckBoxBase().setStrokeBackgroundColor(Theme.key_windowBackgroundWhiteBlackText);
            FrameLayout r92 = new FrameLayout(r8);
            this.checkBoxContainer = r92;
            r92.addView(this.checkBox, LayoutHelper.createFrame(26, 26, 17));
            addView(this.checkBoxContainer, LayoutHelper.createFrame(36, 36.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
            this.checkBoxContainer.setVisibility(0);
            this.checkBoxContainer.setImportantForAccessibility(2);
            this.checkBox.setImportantForAccessibility(2);
            setWillNotDraw(false);
            return;
        L5:
            r102.setDrawBackgroundAsArc(7);
            goto L6
        }

        private void afterLoad(String r9, Bitmap r10, int[] r11) {
            if (r10 != null) goto L4;
            return;
        L4:
            putBitmap(r9, r10);
            if (TextUtils.equals(r9, this.currentKey) == true) goto L8;
            releaseBitmap(r9);
            return;
        L8:
            this.bitmap = r10;
            Paint r92 = this.gradientPaint;
            if (r11 != null) goto L11;
            r92.setShader(null);
            this.gradient = null;
        L12:
            updateMatrix();
            invalidate();
            return;
        L11:
            LinearGradient r0 = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, r11, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradient = r0;
            r92.setShader(r0);
            goto L12
        }

        public static void cleanupQueues() {
            releaseAllBitmaps();
            int r0 = 0;
        L4:
            if (r0 >= allQueues.size()) goto L6;
            allQueues.get(r0).cleanupQueue();
            allQueues.get(r0).recycle();
            r0 = r0 + 1;
            goto L4
        L6:
            allQueues.clear();
        }

        private static Bitmap getBitmap(String r4) {
            if (r4 != null) goto L5;
            return null;
        L5:
            Bitmap r0 = bitmapsCache.get(r4);
            if (r0 == null) goto L12;
            HashMap<String, Integer> r1 = bitmapsUseCounts;
            Integer r2 = r1.get(r4);
            int r3 = 1;
            if (r2 == null) goto L11;
            r3 = 1 + r2.intValue();
        L11:
            r1.put(r4, Integer.valueOf(r3));
        L12:
            return r0;
        }

        private DispatchQueue getQueue() {
            DispatchQueue r0 = this.myQueue;
            if (r0 == null) goto L6;
            return r0;
        L6:
            if (allQueues.size() >= 4) goto L8;
            ArrayList<DispatchQueue> r02 = allQueues;
            DispatchQueue r1 = new DispatchQueue("gallery_load_" + allQueues.size());
            this.myQueue = r1;
            r02.add(r1);
        L13:
            return this.myQueue;
        L8:
            int r03 = allQueuesIndex + 1;
            allQueuesIndex = r03;
            if (r03 < allQueues.size()) goto L11;
            allQueuesIndex = 0;
        L11:
            this.myQueue = allQueues.get(allQueuesIndex);
            goto L13
        }

        private Pair<Bitmap, int[]> getThumbnail(Object r8) {
            Bitmap r0 = null;
            int[] r02 = null;
            r02 = null;
            r02 = null;
            r02 = null;
            if (r8 != null) goto L5;
            return null;
        L5:
            int r1 = (int) Math.min(AndroidUtilities.displaySize.x / 3.0f, AndroidUtilities.dp(330.0f));
            int r2 = (int) (r1 * this.aspectRatio);
            if ((r8 instanceof MediaController.PhotoEntry) == false) goto L26;
            MediaController.PhotoEntry r82 = (MediaController.PhotoEntry) r8;
            BitmapFactory.Options r3 = new BitmapFactory.Options();
            r3.inJustDecodeBounds = true;
            readBitmap(r82, r3);
            StoryEntry.setupScale(r3, r1, r2);
            r3.inPreferredConfig = Bitmap.Config.ARGB_8888;
            r3.inDither = true;
            r3.inJustDecodeBounds = false;
            Bitmap r12 = readBitmap(r82, r3);
            if (r12 != null) goto L10;
        L24:
            int[] r7 = r02;
            r0 = r12;
        L32:
            return new Pair(r0, r7);
        L10:
            if ((r12.getHeight() / r12.getWidth()) >= this.aspectRatio) goto L24;
            if (r82.gradientTopColor == 0) goto L14;
        L19:
            int r72 = r82.gradientTopColor;
            if (r72 == 0) goto L24;
            int r83 = r82.gradientBottomColor;
            if (r83 == 0) goto L24;
            r02 = new int[]{r72, r83};
            goto L24
        L14:
            if (r82.gradientBottomColor != 0) goto L19;
            if (r12 == null) goto L19;
            if (r12.isRecycled() == true) goto L19;
            r02 = DominantColors.getColorsSync(true, r12, true);
            r82.gradientTopColor = r02[0];
            r82.gradientBottomColor = r02[1];
            goto L24
        L26:
            if ((r8 instanceof StoryEntry) == false) goto L30;
            File r73 = ((StoryEntry) r8).draftThumbFile;
            if (r73 == null) goto L30;
            BitmapFactory.Options r84 = new BitmapFactory.Options();
            r84.inJustDecodeBounds = true;
            BitmapFactory.decodeFile(r73.getPath(), r84);
            StoryEntry.setupScale(r84, r1, r2);
            r84.inPreferredConfig = Bitmap.Config.ARGB_8888;
            r84.inDither = true;
            r84.inJustDecodeBounds = false;
            r0 = BitmapFactory.decodeFile(r73.getPath(), r84);
            r7 = null;
        L30:
            r7 = null;
            goto L32
        }

        private String key(MediaController.PhotoEntry r2) {
            if (r2 != null) goto L5;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            String r0 = r2.thumbPath;
            if (r0 == null) goto L9;
            return r0;
        L9:
            if (r2.isVideo == false) goto L15;
            if (r2.isLivePhoto() == true) goto L15;
            return _UrlKt.FRAGMENT_ENCODE_SET + r2.imageId;
        L15:
            return r2.path;
        }

        private /* synthetic */ void lambda$loadBitmap$2(String r2, Pair r3) {
            afterLoad(r2, (Bitmap) r3.first, (int[]) r3.second);
        }

        private /* synthetic */ void lambda$loadBitmap$3(Object r2, String r3) {
            AndroidUtilities.runOnUIThread(new GalleryListView$Cell$$ExternalSyntheticLambda3(this, r3, getThumbnail(r2)));
        }

        private /* synthetic */ void lambda$new$0() {
            loadBitmap(null);
        }

        private /* synthetic */ void lambda$setCheckbox$1(boolean r1) {
            if (r1 == true) goto L5;
            this.checkBoxContainer.setVisibility(8);
            return;
        }

        private void loadBitmap(Object r13) {
            if (r13 != null) goto L6;
            releaseBitmap(this.currentKey);
            this.currentKey = null;
            this.bitmap = null;
            invalidate();
            return;
        L6:
            boolean r1 = r13 instanceof MediaController.PhotoEntry;
            if (r1 == false) goto L10;
            String r2 = key((MediaController.PhotoEntry) r13);
        L14:
            if (TextUtils.equals(r2, this.currentKey) == false) goto L16;
            return;
        L16:
            String r3 = this.currentKey;
            if (r3 == null) goto L19;
            this.bitmap = null;
            releaseBitmap(r3);
            invalidate();
        L19:
            this.currentKey = r2;
            this.gradientPaint.setShader(null);
            this.gradient = null;
            if (r1 == false) goto L26;
            MediaController.PhotoEntry r12 = (MediaController.PhotoEntry) r13;
            if (r12.gradientTopColor == 0) goto L26;
            if (r12.gradientBottomColor == 0) goto L26;
            Paint r32 = this.gradientPaint;
            LinearGradient r4 = new LinearGradient(0.0f, 0.0f, 0.0f, 1.0f, new int[]{r12.gradientTopColor, r12.gradientBottomColor}, new float[]{0.0f, 1.0f}, Shader.TileMode.CLAMP);
            this.gradient = r4;
            r32.setShader(r4);
            updateMatrix();
        L26:
            Bitmap r14 = getBitmap(r2);
            this.bitmap = r14;
            if (r14 == null) goto L31;
            invalidate();
            return;
        L31:
            if (this.loadingBitmap == null) goto L33;
            getQueue().cancelRunnable(this.loadingBitmap);
            this.loadingBitmap = null;
        L33:
            DispatchQueue r0 = getQueue();
            GalleryListView$Cell$$ExternalSyntheticLambda1 r15 = new GalleryListView$Cell$$ExternalSyntheticLambda1(this, r13, r2);
            this.loadingBitmap = r15;
            r0.postRunnable(r15);
            return;
        L10:
            if ((r13 instanceof StoryEntry) == false) goto L12;
            r2 = "d" + ((StoryEntry) r13).draftId;
            goto L14
        L12:
            r2 = null;
            goto L14
        }

        private static void putBitmap(String r2, Bitmap r3) {
            if (r2 == null) goto L11;
            if (r3 == null) goto L12;
            bitmapsCache.put(r2, r3);
            HashMap<String, Integer> r32 = bitmapsUseCounts;
            Integer r0 = r32.get(r2);
            if (r0 == null) goto L9;
            r32.put(r2, Integer.valueOf(r0.intValue() + 1));
            return;
        L9:
            r32.put(r2, 1);
            return;
        L12:
            return;
        }

        private Bitmap readBitmap(MediaController.PhotoEntry r5, BitmapFactory.Options r6) {
            if (r5 != null) goto L5;
            return null;
        L5:
            String r0 = r5.thumbPath;
            if (r0 == null) goto L10;
            return BitmapFactory.decodeFile(r0, r6);
        L10:
            if (r5.isVideo == false) goto L16;
            if (r5.isLivePhoto() == true) goto L16;
            return MediaStore.Video.Thumbnails.getThumbnail(getContext().getContentResolver(), r5.imageId, 1, r6);
        L16:
            return MediaStore.Images.Thumbnails.getThumbnail(getContext().getContentResolver(), r5.imageId, 1, r6);
        }

        private static void releaseAllBitmaps() {
            bitmapsUseCounts.clear();
            bitmapsCache.evictAll();
        }

        private static void releaseBitmap(String r3) {
            if (r3 == null) goto L13;
            HashMap<String, Integer> r0 = bitmapsUseCounts;
            Integer r1 = r0.get(r3);
            if (r1 == null) goto L12;
            int r12 = r1.intValue() - 1;
            Integer r2 = Integer.valueOf(r12);
            if (r12 > 0) goto L10;
            r0.remove(r3);
            return;
        L10:
            r0.put(r3, r2);
            return;
        L12:
            return;
        }

        private void setDraft(boolean r9) {
            if (r9 == false) goto L17;
            String r1 = LocaleController.getString("StoryDraft");
            TextPaint r2 = this.draftTextPaint;
            if (getMeasuredWidth() <= 0) goto L7;
            int r92 = getMeasuredWidth();
        L8:
            StaticLayout r0 = new StaticLayout(r1, r2, r92, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.draftLayout = r0;
            int r93 = r0.getLineCount();
            float r02 = 0.0f;
            if (r93 <= 0) goto L11;
            float r94 = this.draftLayout.getLineWidth(0);
        L12:
            this.draftLayoutWidth = r94;
            if (this.draftLayout.getLineCount() <= 0) goto L15;
            r02 = this.draftLayout.getLineLeft(0);
        L15:
            this.draftLayoutLeft = r02;
            return;
        L11:
            r94 = 0.0f;
            goto L12
        L7:
            r92 = AndroidUtilities.displaySize.x;
            goto L8
        L17:
            this.draftLayout = null;
        }

        private void setDuration(String r10) {
            if (TextUtils.isEmpty(r10) == true) goto L17;
            TextPaint r3 = this.durationTextPaint;
            if (getMeasuredWidth() <= 0) goto L8;
            int r0 = getMeasuredWidth();
        L9:
            StaticLayout r1 = new StaticLayout(r10, r3, r0, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
            this.durationLayout = r1;
            float r02 = 0.0f;
            if (r1.getLineCount() <= 0) goto L12;
            float r102 = this.durationLayout.getLineWidth(0);
        L13:
            this.durationLayoutWidth = r102;
            if (this.durationLayout.getLineCount() <= 0) goto L16;
            r02 = this.durationLayout.getLineLeft(0);
        L16:
            this.durationLayoutLeft = r02;
        L18:
            this.drawDurationPlay = true;
            return;
        L12:
            r102 = 0.0f;
            goto L13
        L8:
            r0 = AndroidUtilities.displaySize.x;
            goto L9
        L17:
            this.durationLayout = null;
            goto L18
        }

        private void updateMatrix() {
            if (getMeasuredWidth() <= 0) goto L14;
            if (getMeasuredHeight() <= 0) goto L14;
            if (this.bitmap == null) goto L14;
            if ((r0.getHeight() / this.bitmap.getWidth()) <= (this.aspectRatio - 0.1f)) goto L11;
            float r0 = Math.max(getMeasuredWidth() / this.bitmap.getWidth(), getMeasuredHeight() / this.bitmap.getHeight());
        L12:
            this.bitmapMatrix.reset();
            this.bitmapMatrix.postScale(r0, r0);
            this.bitmapMatrix.postTranslate((getMeasuredWidth() - (this.bitmap.getWidth() * r0)) / 2.0f, (getMeasuredHeight() - (r0 * this.bitmap.getHeight())) / 2.0f);
            goto L14
        L11:
            r0 = getMeasuredWidth() / this.bitmap.getWidth();
        L14:
            if (getMeasuredHeight() <= 0) goto L19;
            this.gradientMatrix.reset();
            this.gradientMatrix.postScale(1.0f, getMeasuredHeight());
            LinearGradient r02 = this.gradient;
            if (r02 == null) goto L20;
            r02.setLocalMatrix(this.gradientMatrix);
            return;
        L20:
            return;
        }

        @Override // android.view.View
        public void draw(Canvas r15) {
            boolean r7 = true;
            boolean r2 = false;
            if (this.topLeft == false) goto L5;
        L6:
            r15.save();
            this.clipPath.rewind();
            RectF r1 = AndroidUtilities.rectTmp;
            r1.set(0.0f, 0.0f, getWidth(), getHeight());
            float[] r3 = this.radii;
            if (this.topLeft == false) goto L9;
            float r4 = AndroidUtilities.dp(6.0f);
        L10:
            r3[1] = r4;
            r3[0] = r4;
            float[] r22 = this.radii;
            if (this.topRight == false) goto L13;
            float r32 = AndroidUtilities.dp(6.0f);
        L14:
            r22[3] = r32;
            r22[2] = r32;
            this.clipPath.addRoundRect(r1, this.radii, Path.Direction.CW);
            r15.clipPath(this.clipPath);
            r2 = true;
        L15:
            float r12 = this.checkBox.getProgress() * AndroidUtilities.dp(12.66f);
            if (r12 <= 0.0f) goto L20;
            if (r2 == true) goto L19;
            r15.save();
        L19:
            float r11 = (getWidth() - (r12 * 2.0f)) / getWidth();
            this.paintUnderCheck.setColor(218103807);
            r15.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.paintUnderCheck);
            r15.scale(r11, r11, getWidth() / 2.0f, getHeight() / 2.0f);
            this.clipPath.rewind();
            RectF r13 = AndroidUtilities.rectTmp;
            r13.set(0.0f, 0.0f, getWidth(), getHeight());
            float r23 = AndroidUtilities.dp(12.0f) * this.checkBox.getProgress();
            this.clipPath.addRoundRect(r13, r23, r23, Path.Direction.CW);
            r15.clipPath(this.clipPath);
        L21:
            r15.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.bgPaint);
            if (this.gradient == null) goto L25;
            r15.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.gradientPaint);
        L25:
            Bitmap r14 = this.bitmap;
            if (r14 == null) goto L31;
            if (r14.isRecycled() == true) goto L31;
            r15.drawBitmap(this.bitmap, this.bitmapMatrix, this.bitmapPaint);
        L31:
            if (this.draftLayout == null) goto L34;
            RectF r16 = AndroidUtilities.rectTmp;
            r16.set(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), (AndroidUtilities.dp(10.0f) + this.draftLayoutWidth) + AndroidUtilities.dp(6.0f), (AndroidUtilities.dp(5.0f) + this.draftLayout.getHeight()) + AndroidUtilities.dp(2.0f));
            r15.drawRoundRect(r16, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.durationBackgroundPaint);
            r15.save();
            r15.translate((r16.left + AndroidUtilities.dp(6.0f)) - this.draftLayoutLeft, r16.top + AndroidUtilities.dp(1.33f));
            this.draftLayout.draw(r15);
            r15.restore();
        L34:
            if (this.durationLayout == null) goto L47;
            RectF r17 = AndroidUtilities.rectTmp;
            float r5 = AndroidUtilities.dp(4.0f);
            float r9 = ((getHeight() - AndroidUtilities.dp(4.0f)) - this.durationLayout.getHeight()) - AndroidUtilities.dp(2.0f);
            int r10 = AndroidUtilities.dp(4.0f);
            if (this.drawDurationPlay == false) goto L38;
            int r112 = AndroidUtilities.dp(16.0f);
        L39:
            r17.set(r5, r9, ((r10 + r112) + this.durationLayoutWidth) + AndroidUtilities.dp(5.0f), getHeight() - AndroidUtilities.dp(4.0f));
            r15.drawRoundRect(r17, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.durationBackgroundPaint);
            if (this.drawDurationPlay == false) goto L42;
            this.durationPlayDrawable.setBounds((int) (r17.left + AndroidUtilities.dp(6.0f)), (int) (r17.centerY() - (AndroidUtilities.dp(8.0f) / 2)), (int) (r17.left + AndroidUtilities.dp(13.0f)), (int) (r17.centerY() + (AndroidUtilities.dp(8.0f) / 2)));
            this.durationPlayDrawable.draw(r15);
        L42:
            r15.save();
            float r33 = r17.left;
            if (this.drawDurationPlay == false) goto L45;
            int r24 = AndroidUtilities.dp(16.0f);
        L46:
            r15.translate((r33 + r24) - this.durationLayoutLeft, r17.top + AndroidUtilities.dp(1.0f));
            this.durationLayout.draw(r15);
            r15.restore();
            goto L47
        L45:
            r24 = AndroidUtilities.dp(5.0f);
            goto L46
        L38:
            r112 = AndroidUtilities.dp(4.0f);
        L47:
            if (r7 == false) goto L49;
            r15.restore();
        L49:
            super.draw(r15);
            return;
        L20:
            r7 = r2;
            goto L21
        L13:
            r32 = 0.0f;
            goto L14
        L9:
            r4 = 0.0f;
            goto L10
        L5:
            if (this.topRight == false) goto L15;
            goto L6
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            AndroidUtilities.cancelRunOnUIThread(this.unload);
            Object r0 = this.currentObject;
            if (r0 == null) goto L6;
            loadBitmap(r0);
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            AndroidUtilities.runOnUIThread(this.unload, 250);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            FrameLayout r0 = this.checkBoxContainer;
            if (r0 != null) goto L5;
        L13:
            r3.setClassName("android.widget.ImageView");
        L14:
            r3.setClickable(true);
            r3.setEnabled(true);
            r3.addAction(16);
            if (this.accessibilityLongClick == null) goto L17;
            r3.setLongClickable(true);
            r3.addAction(32);
        L17:
            CharSequence r2 = this.accessibilityText;
            if (r2 == null) goto L21;
            r3.setContentDescription(r2);
            return;
        L21:
            return;
        L5:
            if (r0.getVisibility() != 0) goto L13;
            r3.setClassName("android.widget.CheckBox");
            r3.setCheckable(true);
            CheckBox2 r02 = this.checkBox;
            if (r02 != null) goto L9;
        L11:
            boolean r03 = false;
        L12:
            r3.setChecked(r03);
            goto L14
        L9:
            if (r02.isChecked() == false) goto L11;
            r03 = true;
            goto L12
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r3, int r4) {
            int r32 = View.MeasureSpec.getSize(r3);
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec((int) (r32 * this.aspectRatio), TLObject.FLAG_30));
            updateMatrix();
        }

        @Override // android.view.View
        public boolean performAccessibilityAction(int r3, Bundle r4) {
            if (r3 != 16) goto L9;
            Runnable r0 = this.accessibilityClick;
            if (r0 == null) goto L9;
            r0.run();
            return true;
        L9:
            if (r3 != 32) goto L15;
            Runnable r02 = this.accessibilityLongClick;
            if (r02 == null) goto L15;
            r02.run();
            return true;
        L15:
            return super.performAccessibilityAction(r3, r4);
        }

        public void set(MediaController.PhotoEntry r3) {
            this.currentObject = r3;
            if (r3 != null) goto L5;
        L9:
            String r1 = null;
        L10:
            setDuration(r1);
            setDraft(false);
            if (r3 != null) goto L14;
            this.accessibilityText = null;
        L17:
            loadBitmap(r3);
            invalidate();
            return;
        L14:
            if (r3.isVideo == false) goto L16;
            this.accessibilityText = LocaleController.getString(R.string.AttachVideo) + ", " + LocaleController.formatDuration(r3.duration);
            goto L17
        L16:
            this.accessibilityText = LocaleController.getString(R.string.AttachPhoto);
            goto L17
        L5:
            if (r3.isVideo == false) goto L9;
            if (r3.isLivePhoto() == true) goto L9;
            r1 = AndroidUtilities.formatShortDuration(r3.duration);
            goto L10
        }

        public void set(StoryEntry r5, int r6) {
            this.currentObject = r5;
            boolean r0 = false;
            if (r6 <= 0) goto L5;
            setDraft(false);
            setDuration(LocaleController.formatPluralString("StoryDrafts", r6, new Object[0]));
            this.drawDurationPlay = false;
            this.accessibilityText = LocaleController.formatPluralString("StoryDrafts", r6, new Object[0]);
        L21:
            loadBitmap(r5);
            return;
        L5:
            if (r5 != null) goto L7;
        L9:
            setDraft(r0);
            if (r5 != null) goto L12;
        L14:
            String r1 = null;
        L15:
            setDuration(r1);
            if (r5 != null) goto L18;
        L20:
            this.accessibilityText = LocaleController.getString(R.string.StoryDraft);
            goto L21
        L18:
            if (r5.isVideo == false) goto L20;
            this.accessibilityText = LocaleController.getString(R.string.StoryDraft) + ", " + LocaleController.formatDuration((int) Math.max(0.0f, (r5.duration * (r5.right - r5.left)) / 1000.0f));
            goto L21
        L12:
            if (r5.isVideo == false) goto L14;
            r1 = AndroidUtilities.formatShortDuration((int) Math.max(0.0f, (r5.duration * (r5.right - r5.left)) / 1000.0f));
            goto L15
        L7:
            if (r5.isDraft == false) goto L9;
            r0 = true;
            goto L9
        }

        public void setCheckbox(boolean r7, int r8, boolean r9) {
            if (this.alwaysShowCheckbox == false) goto L5;
            r7 = true;
        L5:
            FrameLayout r0 = this.checkBoxContainer;
            if (r9 == true) goto L11;
            if (r7 == false) goto L9;
            int r72 = 0;
        L10:
            r0.setVisibility(r72);
        L24:
            CheckBox2 r73 = this.checkBox;
            if (r8 < 0) goto L28;
            r73.setChecked(true, r9);
            this.checkBox.setNum(r8);
            return;
        L28:
            r73.setChecked(false, r9);
            return;
        L9:
            r72 = 8;
            goto L10
        L11:
            r0.setVisibility(0);
            ViewPropertyAnimator r02 = this.checkBox.animate();
            float r3 = 1.0f;
            if (r7 == false) goto L14;
            float r4 = 1.0f;
        L15:
            ViewPropertyAnimator r03 = r02.alpha(r4);
            if (r7 == false) goto L18;
            float r5 = 1.0f;
        L19:
            ViewPropertyAnimator r04 = r03.scaleX(r5);
            if (r7 == true) goto L23;
            r3 = 0.7f;
        L23:
            r04.scaleY(r3).withEndAction(new GalleryListView$Cell$$ExternalSyntheticLambda2(this, r7)).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
            goto L24
        L18:
            r5 = 0.7f;
            goto L19
        L14:
            r4 = 0.0f;
            goto L15
        }

        public void setRounding(boolean r1, boolean r2) {
            this.topLeft = r1;
            this.topRight = r2;
        }
    }

    public class EmptyView extends View {
        int height;
        final /* synthetic */ GalleryListView this$0;

        public EmptyView(GalleryListView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onMeasure(int r5, int r6) {
            int r52 = View.MeasureSpec.getSize(r5);
            int r62 = this.height;
            if (r62 != (-1)) goto L17;
            MediaController.AlbumEntry r63 = this.this$0.selectedAlbum;
            MediaController.AlbumEntry r0 = GalleryListView.m20028$$Nest$sfgetdraftsAlbum();
            GalleryListView r1 = this.this$0;
            if (r63 != r0) goto L7;
            int r64 = GalleryListView.m20012$$Nest$fgetdrafts(r1).size();
        L15:
            setMeasuredDimension(r52, Math.max(0, (AndroidUtilities.displaySize.y - AndroidUtilities.dp(62.0f)) - (((int) (((int) (r52 / this.this$0.layoutManager.getSpanCount())) * GalleryListView.m20007$$Nest$fgetASPECT_RATIO(this.this$0))) * ((int) Math.ceil(r64 / this.this$0.layoutManager.getSpanCount())))));
            return;
        L7:
            ArrayList<MediaController.PhotoEntry> r65 = r1.photos;
            if (r65 == null) goto L14;
            int r66 = r65.size() + (GalleryListView.m20009$$Nest$fgetcontainsDraftFolder(this.this$0) ? 1 : 0);
            if (GalleryListView.m20010$$Nest$fgetcontainsDrafts(this.this$0) == false) goto L12;
            int r02 = GalleryListView.m20012$$Nest$fgetdrafts(this.this$0).size();
        L13:
            r64 = r66 + r02;
            goto L15
        L12:
            r02 = 0;
            goto L13
        L14:
            r64 = 0;
            goto L15
        L17:
            setMeasuredDimension(r52, r62);
        }

        public void setHeight(int r1) {
            this.height = r1;
        }
    }

    public class HeaderView extends FrameLayout {
        public TextView textView;
        final /* synthetic */ GalleryListView this$0;

        public HeaderView(GalleryListView r8, Context r9, boolean r10) {
            this.this$0 = r8;
            super(r9);
            if (r10 == false) goto L5;
            float r1 = 14.0f;
        L6:
            setPadding(AndroidUtilities.dp(r1), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(10.0f));
            TextView r12 = new TextView(r9);
            this.textView = r12;
            r12.setTextSize(1, 16.0f);
            this.textView.setTextColor(-1);
            this.textView.setTypeface(AndroidUtilities.bold());
            this.textView.setText(r8.getTitle());
            TextView r82 = this.textView;
            if (r10 == false) goto L10;
            float r92 = 32.0f;
        L11:
            addView(r82, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, r92, 0.0f));
            return;
        L10:
            r92 = 0.0f;
            goto L11
        L5:
            r1 = 16.0f;
            goto L6
        }
    }

    public class SearchAdapter extends RecyclerListView.SelectionAdapter {
        private TLRPC.User bot;
        private int currentReqId;
        private String lastOffset;
        private boolean loading;
        private Drawable loadingDrawable;
        public String query;
        public ArrayList<TLObject> results;
        private final Runnable searchRunnable;
        final /* synthetic */ GalleryListView this$0;
        private boolean triedResolvingBot;
        public int type;

        /* renamed from: org.telegram.ui.Stories.recorder.GalleryListView$SearchAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends BackupImageView {
            final /* synthetic */ SearchAdapter this$1;

            public AnonymousClass1(SearchAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r1, int r2) {
                int r12 = View.MeasureSpec.getSize(r1);
                setMeasuredDimension(r12, r12);
            }
        }

        /* renamed from: $r8$lambda$EufSeWmg-BVARMgzvAsWXWTSNyY, reason: not valid java name */
        public static /* synthetic */ void m20036$r8$lambda$EufSeWmgBVARMgzvAsWXWTSNyY(SearchAdapter r0, TLObject r1, MessagesController r2) {
            r0.lambda$loadInternal$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$OGlOrtvdeixzl21V50Ma8yxG8zE(SearchAdapter r0) {
            r0.loadInternal();
        }

        public static /* synthetic */ void $r8$lambda$dwhNH3s6SX433JpKfVv78bpovKA(SearchAdapter r0, TLObject r1, boolean r2) {
            r0.lambda$loadInternal$2(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$wA3YHlUxKvKsP_Y1rqPwSJOXVjA(SearchAdapter r0, boolean r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadInternal$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$yN2H3qfaF3cL8XkRos-IsILXPcs, reason: not valid java name */
        public static /* synthetic */ void m20037$r8$lambda$yN2H3qfaF3cL8XkRosIsILXPcs(SearchAdapter r0, MessagesController r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadInternal$1(r1, r2, r3);
        }

        private SearchAdapter(GalleryListView r2) {
            this.this$0 = r2;
            this.results = new ArrayList();
            this.currentReqId = -1;
            this.loadingDrawable = new ColorDrawable(285212671);
            this.searchRunnable = new GalleryListView$SearchAdapter$$ExternalSyntheticLambda0(this);
        }

        public /* synthetic */ SearchAdapter(GalleryListView r1, GalleryListViewIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$loadInternal$0(TLObject r4, MessagesController r5) {
            this.triedResolvingBot = true;
            this.loading = false;
            if ((r4 instanceof TLRPC.TL_contacts_resolvedPeer) == false) goto L6;
            TLRPC.TL_contacts_resolvedPeer r42 = (TLRPC.TL_contacts_resolvedPeer) r4;
            r5.putUsers(r42.users, false);
            r5.putChats(r42.chats, false);
            MessagesStorage.getInstance(GalleryListView.m20011$$Nest$fgetcurrentAccount(this.this$0)).putUsersAndChats(r42.users, r42.chats, true, true);
            loadInternal();
            return;
        }

        private /* synthetic */ void lambda$loadInternal$1(MessagesController r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new GalleryListView$SearchAdapter$$ExternalSyntheticLambda4(this, r2, r1));
        }

        private /* synthetic */ void lambda$loadInternal$2(TLObject r4, boolean r5) {
            if ((r4 instanceof TLRPC.messages_BotResults) == false) goto L27;
            TLRPC.messages_BotResults r42 = (TLRPC.messages_BotResults) r4;
            this.lastOffset = r42.next_offset;
            if (r5 == false) goto L7;
            this.results.clear();
        L7:
            int r0 = 0;
        L9:
            if (r0 >= r42.results.size()) goto L20;
            TLRPC.BotInlineResult r1 = r42.results.get(r0);
            TLRPC.Document r2 = r1.document;
            if (r2 == null) goto L13;
            this.results.add(r2);
        L19:
            r0 = r0 + 1;
            goto L9
        L13:
            TLRPC.Photo r22 = r1.photo;
            if (r22 == null) goto L17;
            this.results.add(r22);
            goto L19
        L17:
            if (r1.content == null) goto L19;
            this.results.add(r1);
            goto L19
        L20:
            this.loading = false;
            onLoadingUpdate(false);
            notifyDataSetChanged();
            return;
        }

        private /* synthetic */ void lambda$loadInternal$3(boolean r1, TLObject r2, TLRPC.TL_error r3) {
            AndroidUtilities.runOnUIThread(new GalleryListView$SearchAdapter$$ExternalSyntheticLambda3(this, r2, r1));
        }

        private void loadInternal() {
            if (this.loading == true) goto L31;
            this.loading = true;
            onLoadingUpdate(true);
            MessagesController r1 = MessagesController.getInstance(GalleryListView.m20011$$Nest$fgetcurrentAccount(this.this$0));
            if (this.type != 1) goto L8;
            String r0 = r1.gifSearchBot;
        L10:
            if (this.bot != null) goto L14;
            TLObject r2 = r1.getUserOrChat(r0);
            if ((r2 instanceof TLRPC.User) == false) goto L14;
            this.bot = (TLRPC.User) r2;
        L14:
            TLRPC.User r22 = this.bot;
            if (r22 == null) goto L17;
        L20:
            if (r22 != null) goto L22;
            return;
        L22:
            TLRPC.TL_messages_getInlineBotResults r02 = new TLRPC.TL_messages_getInlineBotResults();
            r02.bot = r1.getInputUser(this.bot);
            String r12 = this.query;
            String r23 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r12 != null) goto L25;
            r12 = _UrlKt.FRAGMENT_ENCODE_SET;
        L25:
            r02.query = r12;
            r02.peer = new TLRPC.TL_inputPeerEmpty();
            String r13 = this.lastOffset;
            if (r13 == null) goto L29;
            r23 = r13;
        L29:
            r02.offset = r23;
            boolean r14 = TextUtils.isEmpty(r23);
            this.currentReqId = ConnectionsManager.getInstance(GalleryListView.m20011$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r02, new GalleryListView$SearchAdapter$$ExternalSyntheticLambda2(this, r14));
            return;
        L17:
            if (this.triedResolvingBot == true) goto L20;
            TLRPC.TL_contacts_resolveUsername r24 = new TLRPC.TL_contacts_resolveUsername();
            r24.username = r0;
            this.currentReqId = ConnectionsManager.getInstance(GalleryListView.m20011$$Nest$fgetcurrentAccount(this.this$0)).sendRequest(r24, new GalleryListView$SearchAdapter$$ExternalSyntheticLambda1(this, r1));
            return;
        L8:
            r0 = r1.imageSearchBot;
            goto L10
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.results.size();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        public void load(String r6) {
            if (TextUtils.equals(this.query, r6) == false) goto L5;
        L8:
            this.query = r6;
            AndroidUtilities.cancelRunOnUIThread(this.searchRunnable);
            if (TextUtils.isEmpty(r6) == false) goto L12;
            this.results.clear();
            onLoadingUpdate(false);
            notifyDataSetChanged();
            return;
        L12:
            onLoadingUpdate(true);
            AndroidUtilities.runOnUIThread(this.searchRunnable, 1500);
            return;
        L5:
            if (this.currentReqId == (-1)) goto L7;
            ConnectionsManager.getInstance(GalleryListView.m20011$$Nest$fgetcurrentAccount(this.this$0)).cancelRequest(this.currentReqId, true);
            this.currentReqId = -1;
        L7:
            this.loading = false;
            this.lastOffset = null;
            goto L8
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            BackupImageView r52 = (BackupImageView) r5.itemView;
            TLObject r62 = this.results.get(r6);
            if ((r62 instanceof TLRPC.Document) == false) goto L7;
            r52.setImage(ImageLocation.getForDocument((TLRPC.Document) r62), "200_200", this.loadingDrawable, null);
            return;
        L7:
            if ((r62 instanceof TLRPC.Photo) == false) goto L11;
            TLRPC.Photo r63 = (TLRPC.Photo) r62;
            r52.setImage(ImageLocation.getForPhoto(FileLoader.getClosestPhotoSizeWithSize(r63.sizes, 320), r63), "200_200", this.loadingDrawable, null);
            return;
        L11:
            if ((r62 instanceof TLRPC.BotInlineResult) == false) goto L18;
            TLRPC.BotInlineResult r64 = (TLRPC.BotInlineResult) r62;
            TLRPC.WebDocument r0 = r64.thumb;
            if (r0 == null) goto L16;
            r52.setImage(ImageLocation.getForPath(r0.url), "200_200", this.loadingDrawable, r64);
            return;
        L16:
            r52.clearImage();
            return;
        L18:
            r52.clearImage();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            return new RecyclerListView.Holder(new AnonymousClass1(this, this.this$0.getContext()));
        }

        public abstract void onLoadingUpdate(boolean r1);
    }

    /* renamed from: $r8$lambda$-dnBWtsp_CxyReYe2UyoZtU0t9A, reason: not valid java name */
    public static /* synthetic */ void m20001$r8$lambda$dnBWtsp_CxyReYe2UyoZtU0t9A(GalleryListView r0, View r1) {
        r0.lambda$new$3(r1);
    }

    /* renamed from: $r8$lambda$2D3TF-0v4cC5od0ibWiu93FJAwY, reason: not valid java name */
    public static /* synthetic */ void m20002$r8$lambda$2D3TF0v4cC5od0ibWiu93FJAwY(GalleryListView r0, View r1) {
        r0.lambda$new$6(r1);
    }

    /* renamed from: $r8$lambda$VhAxl82Z-yter1ysClwp4XDtIOk, reason: not valid java name */
    public static /* synthetic */ boolean m20003$r8$lambda$VhAxl82Zyter1ysClwp4XDtIOk(GalleryListView r0, View r1, int r2) {
        return r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$Y-IzJghPru2PrsNB7WdeqSg5ePo, reason: not valid java name */
    public static /* synthetic */ void m20004$r8$lambda$YIzJghPru2PrsNB7WdeqSg5ePo(GalleryListView r0, View r1, int r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Z5LfqtDHbK9yIUUZsK3iBeCrj6o(GalleryListView r0, MediaController.AlbumEntry r1, View r2) {
        r0.lambda$updateAlbumsDropDown$10(r1, r2);
    }

    /* renamed from: $r8$lambda$hAV_pIipD4P-a7H1B-5IHRHUEIM, reason: not valid java name */
    public static /* synthetic */ Integer m20005$r8$lambda$hAV_pIipD4Pa7H1B5IHRHUEIM(Integer r0) {
        return 0;
    }

    public static /* synthetic */ int $r8$lambda$loNHKpXmiTQFIt6B9vuy9b9fjLQ(ArrayList r2, MediaController.AlbumEntry r3, MediaController.AlbumEntry r4) {
        int r0 = r3.bucketId;
        if (r0 == 0) goto L5;
    L7:
        if (r0 != 0) goto L9;
    L11:
        int r32 = r2.indexOf(r3);
        int r22 = r2.indexOf(r4);
        if (r32 <= r22) goto L15;
        return 1;
    L15:
        if (r32 >= r22) goto L18;
        return -1;
    L18:
        return 0;
    L9:
        if (r4.bucketId != 0) goto L11;
        return 1;
    L5:
        if (r4.bucketId == 0) goto L7;
        return -1;
    }

    public static /* synthetic */ void $r8$lambda$qwEs0WkW6Jc89nYShr6UHBCr87c(GalleryListView r0, Integer r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$uLAsecyXFA9lyftb5vhN6LSElps(GalleryListView r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$xEj88bSN5jREzDz5BAFvfb2-dbk, reason: not valid java name */
    public static /* synthetic */ void m20006$r8$lambda$xEj88bSN5jREzDz5BAFvfb2dbk(GalleryListView r0, View r1) {
        r0.lambda$new$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$yNUVv3ysojog5lVoOmCEfD37_sY(GalleryListView r0, View r1) {
        r0.lambda$new$7(r1);
    }

    /* renamed from: -$$Nest$fgetASPECT_RATIO, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20007$$Nest$fgetASPECT_RATIO(GalleryListView r0) {
        return r0.ASPECT_RATIO;
    }

    /* renamed from: -$$Nest$fgetbuttonsLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m20008$$Nest$fgetbuttonsLayout(GalleryListView r0) {
        return r0.buttonsLayout;
    }

    /* renamed from: -$$Nest$fgetcontainsDraftFolder, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20009$$Nest$fgetcontainsDraftFolder(GalleryListView r0) {
        return r0.containsDraftFolder;
    }

    /* renamed from: -$$Nest$fgetcontainsDrafts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20010$$Nest$fgetcontainsDrafts(GalleryListView r0) {
        return r0.containsDrafts;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20011$$Nest$fgetcurrentAccount(GalleryListView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdrafts, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20012$$Nest$fgetdrafts(GalleryListView r0) {
        return r0.drafts;
    }

    /* renamed from: -$$Nest$fgetdropDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m20013$$Nest$fgetdropDown(GalleryListView r0) {
        return r0.dropDown;
    }

    /* renamed from: -$$Nest$fgetdropDownAlbums, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m20014$$Nest$fgetdropDownAlbums(GalleryListView r0) {
        return r0.dropDownAlbums;
    }

    /* renamed from: -$$Nest$fgetdropDownContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20015$$Nest$fgetdropDownContainer(GalleryListView r0) {
        return r0.dropDownContainer;
    }

    /* renamed from: -$$Nest$fgetmaxCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20016$$Nest$fgetmaxCount(GalleryListView r0) {
        return r0.maxCount;
    }

    /* renamed from: -$$Nest$fgetonBackClickListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m20017$$Nest$fgetonBackClickListener(GalleryListView r0) {
        return r0.onBackClickListener;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m20018$$Nest$fgetresourcesProvider(GalleryListView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetsearchAdapterImages, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchAdapter m20019$$Nest$fgetsearchAdapterImages(GalleryListView r0) {
        return r0.searchAdapterImages;
    }

    /* renamed from: -$$Nest$fgetsearchContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20020$$Nest$fgetsearchContainer(GalleryListView r0) {
        return r0.searchContainer;
    }

    /* renamed from: -$$Nest$fgetsearchEmptyView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StickerEmptyView m20021$$Nest$fgetsearchEmptyView(GalleryListView r0) {
        return r0.searchEmptyView;
    }

    /* renamed from: -$$Nest$fgetsearchItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m20022$$Nest$fgetsearchItem(GalleryListView r0) {
        return r0.searchItem;
    }

    /* renamed from: -$$Nest$fgetsearchListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m20023$$Nest$fgetsearchListView(GalleryListView r0) {
        return r0.searchListView;
    }

    /* renamed from: -$$Nest$fgetshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20024$$Nest$fgetshiftDp(GalleryListView r0) {
        return r0.shiftDp;
    }

    /* renamed from: -$$Nest$fputheaderView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20025$$Nest$fputheaderView(GalleryListView r0, HeaderView r1) {
        r0.headerView = r1;
    }

    /* renamed from: -$$Nest$fputshiftDp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20026$$Nest$fputshiftDp(GalleryListView r0, int r1) {
        r0.shiftDp = r1;
    }

    /* renamed from: -$$Nest$mselectAlbum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20027$$Nest$mselectAlbum(GalleryListView r0, MediaController.AlbumEntry r1, boolean r2) {
        r0.selectAlbum(r1, r2);
    }

    /* renamed from: -$$Nest$sfgetdraftsAlbum, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MediaController.AlbumEntry m20028$$Nest$sfgetdraftsAlbum() {
        return draftsAlbum;
    }

    static {
        draftsAlbum = new MediaController.AlbumEntry(-1, null, null);
    }

    public GalleryListView(int r29, Context r30, Theme.ResourcesProvider r31, MediaController.AlbumEntry r32, boolean r33, float r34, boolean r35, boolean r36) {
        super(r30);
        Paint r13 = new Paint(1);
        this.backgroundPaint = r13;
        this.shiftDp = -2;
        this.actionBarT = new AnimatedFloat(this, 0, 350, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.firstLayout = true;
        ArrayList<StoryEntry> r0 = new ArrayList();
        this.drafts = r0;
        this.selectedPhotos = new ArrayList();
        this.ASPECT_RATIO = r34;
        this.currentAccount = r29;
        this.resourcesProvider = r31;
        this.onlyPhotos = r33;
        this.collaging = r35;
        this.onlyCollaging = r36;
        r13.setColor(-14737633);
        r13.setShadowLayer(AndroidUtilities.dp(2.33f), 0.0f, AndroidUtilities.dp(-0.4f), 134217728);
        AnonymousClass1 r4 = new AnonymousClass1(this, r30, r31);
        this.listView = r4;
        r4.setItemSelectorColorProvider(new GalleryListView$$ExternalSyntheticLambda0());
        Adapter r5 = new Adapter(this, null);
        this.adapter = r5;
        r4.setAdapter(r5);
        AnonymousClass2 r52 = new AnonymousClass2(this, r30, 3);
        this.layoutManager = r52;
        r4.setLayoutManager(r52);
        r4.setFastScrollEnabled(1);
        r4.setFastScrollVisible(true);
        r4.getFastScroll().setAlpha(0.0f);
        r52.setSpanSizeLookup(new AnonymousClass3(this));
        r4.addItemDecoration(new AnonymousClass4(this));
        r4.setClipToPadding(false);
        addView(r4, LayoutHelper.createFrame(-1, -1, 119));
        r4.setOnItemClickListener(new GalleryListView$$ExternalSyntheticLambda1(this));
        r4.setOnItemLongClickListener(new GalleryListView$$ExternalSyntheticLambda2(this));
        r4.setOnScrollListener(new AnonymousClass5(this));
        ActionBar r14 = new ActionBar(r30, r31);
        this.actionBar = r14;
        r14.setBackgroundColor(-14737633);
        r14.setTitleColor(-1);
        r14.setAlpha(0.0f);
        r14.setVisibility(8);
        r14.setBackButtonImage(R.drawable.ic_ab_back);
        r14.setItemsBackgroundColor(436207615, false);
        r14.setItemsColor(-1, false);
        r14.setItemsColor(-1, true);
        addView(r14, LayoutHelper.createFrame(-1, -2, 55));
        r14.setActionBarMenuOnItemClick(new AnonymousClass6(this));
        ActionBarMenu r3 = r14.createMenu();
        AnonymousClass7 r02 = new AnonymousClass7(this, r30, r3, 0, 0, r31);
        this.dropDownContainer = r02;
        r02.setSubMenuOpenSide(1);
        if (AndroidUtilities.isTablet() == false) goto L5;
        float r21 = 64.0f;
    L6:
        r14.addView(r02, 0, LayoutHelper.createFrame(-2, -1.0f, 51, r21, 0.0f, 40.0f, 0.0f));
        r02.setOnClickListener(new GalleryListView$$ExternalSyntheticLambda3(this));
        TextView r42 = new TextView(r30);
        this.dropDown = r42;
        r42.setImportantForAccessibility(2);
        r42.setGravity(3);
        r42.setSingleLine(true);
        r42.setLines(1);
        r42.setMaxLines(1);
        r42.setEllipsize(TextUtils.TruncateAt.END);
        r42.setTextColor(-1);
        r42.setTypeface(AndroidUtilities.bold());
        Drawable r53 = r30.getResources().getDrawable(R.drawable.ic_arrow_drop_down).mutate();
        this.dropDownDrawable = r53;
        r53.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.MULTIPLY));
        r42.setCompoundDrawablePadding(AndroidUtilities.dp(4.0f));
        r42.setPadding(0, AndroidUtilities.statusBarHeight, AndroidUtilities.dp(10.0f), 0);
        r02.addView(r42, LayoutHelper.createFrame(-2, -2.0f, 16, 16.0f, 0.0f, 0.0f, 0.0f));
        FrameLayout r03 = new FrameLayout(r30);
        this.searchContainer = r03;
        r03.setVisibility(8);
        r03.setAlpha(0.0f);
        addView(r03, LayoutHelper.createFrame(-1, -1, 119));
        RecyclerListView r43 = new RecyclerListView(r30, r31);
        this.searchListView = r43;
        GridLayoutManager r54 = new GridLayoutManager(r30, 3);
        this.searchLayoutManager = r54;
        r43.setLayoutManager(r54);
        AnonymousClass8 r55 = new AnonymousClass8(this);
        this.searchAdapterImages = r55;
        r43.setAdapter(r55);
        r43.setOnScrollListener(new AnonymousClass9(this));
        r43.setClipToPadding(true);
        r43.addItemDecoration(new AnonymousClass10(this));
        r03.addView(r43, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass11 r142 = new AnonymousClass11(this, r30, r31);
        float r20 = 10.0f;
        r142.setViewType(2);
        r142.setAlpha(0.0f);
        r142.setVisibility(8);
        r03.addView(r142, LayoutHelper.createFrame(-1, -1, 119));
        StickerEmptyView r56 = new StickerEmptyView(r30, r142, 11, r31);
        this.searchEmptyView = r56;
        r56.title.setTextSize(1, 16.0f);
        r56.title.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, r31));
        r56.title.setTypeface(null);
        r56.title.setText(LocaleController.getString(R.string.SearchImagesType));
        this.keyboardNotifier = new KeyboardNotifier(this, new GalleryListView$$ExternalSyntheticLambda4(this));
        r03.addView(r56, LayoutHelper.createFrame(-1, -1, 119));
        r43.setEmptyView(r56);
        ActionBarMenuItem r04 = r3.addItem(0, R.drawable.outline_header_search).setIsSearchField(true).setActionBarMenuItemSearchListener(new AnonymousClass12(this));
        this.searchItem = r04;
        r04.setVisibility(8);
        r04.setSearchFieldHint(LocaleController.getString(R.string.SearchImagesTitle));
        r43.setOnItemClickListener(new GalleryListView$$ExternalSyntheticLambda5(this));
        r0.clear();
        if (r33 == true) goto L15;
        ArrayList<StoryEntry> r05 = MessagesController.getInstance(r29).getStoriesController().getDraftsController().drafts;
        int r37 = r05.size();
        int r57 = 0;
    L9:
        if (r57 >= r37) goto L15;
        StoryEntry r44 = r05.get(r57);
        r57 = r57 + 1;
        StoryEntry r45 = r44;
        if (r45.isEdit == true) goto L9;
        if (r45.isError == true) goto L9;
        this.drafts.add(r45);
    L15:
        if (r35 == false) goto L23;
        this.selectButton = null;
        LinearLayout r06 = new LinearLayout(r30);
        this.buttonsLayout = r06;
        r06.setOrientation(1);
        r06.setBackgroundColor(Theme.getColor(Theme.key_dialogBackground, r31));
        int r38 = AndroidUtilities.dp(10.0f);
        int r46 = AndroidUtilities.dp(10.0f);
        int r58 = AndroidUtilities.dp(10.0f);
        if (AndroidUtilities.navigationBarHeight <= 0) goto L19;
        r20 = 0.0f;
    L19:
        r06.setPadding(r38, r46, r58, AndroidUtilities.dp(r20) + AndroidUtilities.navigationBarHeight);
        addView(r06, LayoutHelper.createFrame(-1, -2.0f, 87, 0.0f, 0.0f, 0.0f, 0.0f));
        r06.setAlpha(0.0f);
        r06.setTranslationY(AndroidUtilities.dp(32.0f));
        r06.setVisibility(8);
        ButtonWithCounterView r39 = new ButtonWithCounterView(r30, true, r31).setRound();
        this.button1View = r39;
        r39.setText(LocaleController.formatPluralStringComma("StoriesCreate", 1), false);
        if (r36 == true) goto L22;
        r06.addView(r39, LayoutHelper.createLinear(-1, 48, 0.0f, 0.0f, 0.0f, 8.0f));
        r39.setOnClickListener(new GalleryListView$$ExternalSyntheticLambda6(this));
    L22:
        ButtonWithCounterView r2 = new ButtonWithCounterView(r30, r36, r31).setRound();
        this.button2View = r2;
        SpannableStringBuilder r310 = new SpannableStringBuilder(RegisterSpec.PREFIX);
        ColoredImageSpan r47 = new ColoredImageSpan(R.drawable.mini_collage);
        r47.translate(-AndroidUtilities.dp(1.33f), AndroidUtilities.dp(0.66f));
        r310.setSpan(r47, 0, 1, 33);
        r310.append(" ").append(LocaleController.getString(R.string.StoriesCollage));
        r2.setText(r310, false);
        r06.addView(r2, LayoutHelper.createLinear(-1, 48, 0.0f, 0.0f, 0.0f, 0.0f));
        r2.setOnClickListener(new GalleryListView$$ExternalSyntheticLambda7(this));
    L24:
        updateAlbumsDropDown();
        if (r32 != null) goto L27;
    L31:
        ArrayList<MediaController.AlbumEntry> r07 = this.dropDownAlbums;
        if (r07 != null) goto L34;
    L37:
        this.selectedAlbum = MediaController.allMediaAlbumEntry;
    L38:
        this.photos = getPhotoEntries(this.selectedAlbum);
        updateContainsDrafts();
        MediaController.AlbumEntry r08 = this.selectedAlbum;
        if (r08 != MediaController.allMediaAlbumEntry) goto L42;
        this.dropDown.setText(LocaleController.getString(R.string.ChatGallery));
        return;
    L42:
        MediaController.AlbumEntry r22 = draftsAlbum;
        TextView r1 = this.dropDown;
        if (r08 != r22) goto L46;
        r1.setText(LocaleController.getString(R.string.StoryDraftsAlbum));
        return;
    L46:
        r1.setText(r08.bucketName);
        return;
    L34:
        if (r07.isEmpty() == true) goto L37;
        this.selectedAlbum = this.dropDownAlbums.get(0);
        goto L38
    L27:
        if (r32 == draftsAlbum) goto L29;
    L30:
        this.selectedAlbum = r32;
        goto L38
    L29:
        if (this.drafts.size() <= 0) goto L31;
    L23:
        this.buttonsLayout = null;
        this.button1View = null;
        this.button2View = null;
        ImageView r09 = new ImageView(r30);
        this.selectButton = r09;
        r09.setScaleType(ImageView.ScaleType.CENTER);
        r09.setImageResource(R.drawable.floating_check);
        r09.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(56.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r31)));
        ScaleStateListAnimator.apply(r09, 0.1f, 1.5f);
        addView(r09, LayoutHelper.createFrame(-2, -2.0f, 85, 0.0f, 0.0f, 14.0f, 14.0f));
        r09.setOnClickListener(new GalleryListView$$ExternalSyntheticLambda8(this));
        r09.setAlpha(0.0f);
        r09.setScaleX(0.7f);
        r09.setScaleY(0.7f);
        goto L24
    L5:
        r21 = 56.0f;
        goto L6
    }

    private Cell findCell(MediaController.PhotoEntry r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.listView.getChildCount()) goto L11;
        View r1 = this.listView.getChildAt(r0);
        if ((r1 instanceof Cell) == false) goto L10;
        Cell r12 = (Cell) r1;
        if (Cell.m20034$$Nest$fgetcurrentObject(r12) != r4) goto L10;
        return r12;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return null;
    }

    private ArrayList<MediaController.PhotoEntry> getPhotoEntries(MediaController.AlbumEntry r5) {
        if (r5 == null) goto L4;
        ArrayList<MediaController.PhotoEntry> r0 = new ArrayList();
        int r1 = 0;
    L7:
        if (r1 >= r5.photos.size()) goto L14;
        MediaController.PhotoEntry r2 = r5.photos.get(r1);
        if (this.onlyPhotos == true) goto L11;
    L12:
        r0.add(r2);
    L13:
        r1 = r1 + 1;
        goto L7
    L11:
        if (r2.isVideo == true) goto L13;
    L14:
        return r0;
    L4:
        return new ArrayList();
    }

    private /* synthetic */ void lambda$new$1(View r5, int r6) {
        if (r6 >= 2) goto L5;
        return;
    L5:
        if (this.onSelectListener != null) goto L7;
        return;
    L7:
        if ((r5 instanceof Cell) == false) goto L52;
        Cell r52 = (Cell) r5;
        int r0 = r6 - 2;
        Bitmap r2 = null;
        if (this.containsDraftFolder == false) goto L16;
        if (r0 != 0) goto L14;
        selectAlbum(draftsAlbum, true);
        return;
    L14:
        r0 = r6 - 3;
    L26:
        if (r0 >= 0) goto L28;
        return;
    L28:
        if (r0 >= this.photos.size()) goto L51;
        MediaController.PhotoEntry r62 = this.photos.get(r0);
        if (isMultiple() == false) goto L41;
        boolean r02 = this.selectedPhotos.contains(r62);
        ArrayList<MediaController.PhotoEntry> r1 = this.selectedPhotos;
        if (r02 == false) goto L35;
        r1.remove(r62);
    L39:
        AndroidUtilities.updateVisibleRows(this.listView);
        updateSelectButtonVisible();
        return;
    L35:
        if ((r1.size() + 1) <= this.maxCount) goto L38;
        int r63 = -this.shiftDp;
        this.shiftDp = r63;
        AndroidUtilities.shakeViewSpring(r52, r63);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return;
    L38:
        this.selectedPhotos.add(r62);
        goto L39
    L41:
        Utilities.Callback2<Object, Bitmap> r03 = this.onSelectListener;
        if (r62.isVideo == true) goto L44;
    L46:
        r03.run(r62, r2);
        return;
    L44:
        if (r62.isLivePhoto() == true) goto L46;
        r2 = prepareBlurredThumb(r52);
        goto L46
    L51:
        return;
    L16:
        if (this.containsDrafts == false) goto L26;
        if (r0 >= 0) goto L19;
    L25:
        r0 = r0 - this.drafts.size();
        goto L26
    L19:
        if (r0 >= this.drafts.size()) goto L25;
        StoryEntry r64 = this.drafts.get(r0);
        Utilities.Callback2<Object, Bitmap> r04 = this.onSelectListener;
        if (r64.isVideo == false) goto L23;
        r2 = prepareBlurredThumb(r52);
    L23:
        r04.run(r64, r2);
        return;
    }

    private /* synthetic */ boolean lambda$new$2(View r4, int r5) {
        if (r5 >= 2) goto L5;
    L38:
        return false;
    L5:
        if (this.onSelectListener == null) goto L38;
        if ((r4 instanceof Cell) == false) goto L38;
        int r0 = r5 - 2;
        if (this.containsDraftFolder == false) goto L15;
        if (r0 != 0) goto L13;
        return false;
    L13:
        r0 = r5 - 3;
    L21:
        if (r0 < 0) goto L38;
        if (r0 >= this.photos.size()) goto L38;
        MediaController.PhotoEntry r52 = this.photos.get(r0);
        if (this.selectedPhotos.isEmpty() == false) goto L38;
        if (this.multipleOnClick == true) goto L38;
        boolean r02 = this.selectedPhotos.contains(r52);
        ArrayList<MediaController.PhotoEntry> r1 = this.selectedPhotos;
        if (r02 == false) goto L32;
        r1.remove(r52);
    L36:
        AndroidUtilities.updateVisibleRows(this.listView);
        updateSelectButtonVisible();
        return true;
    L32:
        if ((r1.size() + 1) <= this.maxCount) goto L35;
        int r53 = -this.shiftDp;
        this.shiftDp = r53;
        AndroidUtilities.shakeViewSpring(r4, r53);
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        return true;
    L35:
        this.selectedPhotos.add(r52);
        goto L36
    L15:
        if (this.containsDrafts == false) goto L21;
        if (r0 >= 0) goto L18;
    L20:
        r0 = r0 - this.drafts.size();
        goto L21
    L18:
        if (r0 >= this.drafts.size()) goto L20;
        return false;
    }

    private /* synthetic */ void lambda$new$3(View r1) {
        this.dropDownContainer.toggleSubMenu();
    }

    private /* synthetic */ void lambda$new$4(Integer r3) {
        this.searchEmptyView.animate().translationY(((-r3.intValue()) / 2.0f) + AndroidUtilities.dp(80.0f)).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
    }

    private /* synthetic */ void lambda$new$5(View r1, int r2) {
        ActionBarMenuItem r12 = this.searchItem;
        if (r12 == null) goto L5;
        AndroidUtilities.hideKeyboard(r12.getSearchContainer());
    L5:
        if (r2 >= 0) goto L7;
        return;
    L7:
        if (r2 >= this.searchAdapterImages.results.size()) goto L15;
        Utilities.Callback2<Object, Bitmap> r13 = this.onSelectListener;
        if (r13 == null) goto L14;
        r13.run(this.searchAdapterImages.results.get(r2), null);
        return;
    L14:
        return;
    }

    private /* synthetic */ void lambda$new$6(View r2) {
        if (this.buttonsLayout.getAlpha() >= 0.25f) goto L5;
        return;
    L5:
        selectMultiple(false);
    }

    private /* synthetic */ void lambda$new$7(View r2) {
        if (this.buttonsLayout.getAlpha() >= 0.25f) goto L5;
        return;
    L5:
        selectMultiple(true);
    }

    private /* synthetic */ void lambda$new$8(View r1) {
        selectMultiple(false);
    }

    private /* synthetic */ void lambda$updateAlbumsDropDown$10(MediaController.AlbumEntry r1, View r2) {
        selectAlbum(r1, false);
        this.dropDownContainer.closeSubMenu();
    }

    private Bitmap prepareBlurredThumb(Cell r2) {
        if (r2 != null) goto L5;
        return null;
    L5:
        Bitmap r22 = Cell.m20033$$Nest$fgetbitmap(r2);
        if (r22 != null) goto L8;
        return null;
    L8:
        if (r22.isRecycled() == false) goto L10;
        return null;
    L10:
        return Utilities.stackBlurBitmapWithScaleFactor(r22, 6.0f);
    }

    private void selectAlbum(MediaController.AlbumEntry r4, boolean r5) {
        this.selectedAlbum = r4;
        this.photos = getPhotoEntries(r4);
        this.selectedPhotos.clear();
        updateContainsDrafts();
        MediaController.AlbumEntry r42 = this.selectedAlbum;
        if (r42 != MediaController.allMediaAlbumEntry) goto L5;
        this.dropDown.setText(LocaleController.getString(R.string.ChatGallery));
    L9:
        this.adapter.notifyDataSetChanged();
        if (r5 == false) goto L13;
        LinearSmoothScrollerCustom r52 = new LinearSmoothScrollerCustom(getContext(), 2);
        r52.setTargetPosition(1);
        r52.setOffset((-ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(16.0f));
        this.layoutManager.startSmoothScroll(r52);
        return;
    L13:
        this.layoutManager.scrollToPositionWithOffset(1, (-ActionBar.getCurrentActionBarHeight()) + AndroidUtilities.dp(16.0f));
        return;
    L5:
        MediaController.AlbumEntry r0 = draftsAlbum;
        TextView r1 = this.dropDown;
        if (r42 != r0) goto L8;
        r1.setText(LocaleController.getString(R.string.StoryDraftsAlbum));
        goto L9
    L8:
        r1.setText(r42.bucketName);
        goto L9
    }

    private void selectMultiple(boolean r8) {
        if (this.onSelectMultipleListener != null) goto L5;
        return;
    L5:
        if (this.selectedPhotos.isEmpty() == true) goto L26;
        int r2 = 0;
        if (this.selectedPhotos.size() != 1) goto L11;
        this.onSelectListener.run(this.selectedPhotos.get(0), null);
        return;
    L11:
        ArrayList<Bitmap> r0 = new ArrayList();
        ArrayList<MediaController.PhotoEntry> r3 = this.selectedPhotos;
        int r4 = r3.size();
    L12:
        if (r2 >= r4) goto L20;
        MediaController.PhotoEntry r5 = r3.get(r2);
        r2 = r2 + 1;
        MediaController.PhotoEntry r52 = r5;
        if (r52.isVideo == true) goto L16;
    L18:
        Bitmap r53 = null;
    L19:
        r0.add(r53);
        goto L12
    L16:
        if (r52.isLivePhoto() == true) goto L18;
        r53 = prepareBlurredThumb(findCell(r52));
        goto L19
    L20:
        this.onSelectMultipleListener.run(Boolean.valueOf(r8), new ArrayList(this.selectedPhotos), r0);
        this.selectedPhotos.clear();
        AndroidUtilities.updateVisibleRows(this.listView);
        updateSelectButtonVisible();
        return;
    }

    private void updateAlbumsDropDown() {
        this.dropDownContainer.removeAllSubItems();
        ArrayList<MediaController.AlbumEntry> r0 = MediaController.allMediaAlbums;
        ArrayList<MediaController.AlbumEntry> r1 = new ArrayList(r0);
        this.dropDownAlbums = r1;
        Collections.sort(r1, new GalleryListView$$ExternalSyntheticLambda9(r0));
        if (this.drafts.isEmpty() == true) goto L5;
        ArrayList<MediaController.AlbumEntry> r02 = this.dropDownAlbums;
        r02.add(!r02.isEmpty() ? 1 : 0, draftsAlbum);
    L5:
        boolean r03 = this.dropDownAlbums.isEmpty();
        TextView r12 = this.dropDown;
        if (r03 == false) goto L9;
        r12.setCompoundDrawablesWithIntrinsicBounds(null, null, null, null);
        return;
    L9:
        r12.setCompoundDrawablesWithIntrinsicBounds(null, null, this.dropDownDrawable, null);
        int r04 = this.dropDownAlbums.size();
        int r13 = 0;
    L10:
        if (r13 >= r04) goto L20;
        MediaController.AlbumEntry r2 = this.dropDownAlbums.get(r13);
        if (r2 != draftsAlbum) goto L14;
        AlbumButton r4 = new AlbumButton(getContext(), r2.coverPhoto, LocaleController.getString("StoryDraftsAlbum"), this.drafts.size(), this.resourcesProvider);
    L18:
        this.dropDownContainer.getPopupLayout().addView(r4);
        r4.setOnClickListener(new GalleryListView$$ExternalSyntheticLambda10(this, r2));
    L19:
        r13 = r13 + 1;
        goto L10
    L14:
        ArrayList<MediaController.PhotoEntry> r3 = getPhotoEntries(r2);
        if (r3.isEmpty() == true) goto L19;
        r4 = new AlbumButton(getContext(), r2.coverPhoto, r2.bucketName, r3.size(), this.resourcesProvider);
        goto L18
    }

    private void updateContainsDrafts() {
        ArrayList<MediaController.AlbumEntry> r0 = this.dropDownAlbums;
        boolean r1 = true;
        if (r0 != null) goto L5;
    L11:
        boolean r02 = false;
    L12:
        this.containsDraftFolder = r02;
        if (r02 == false) goto L15;
    L23:
        r1 = false;
    L24:
        this.containsDrafts = r1;
        return;
    L15:
        if (this.selectedAlbum == draftsAlbum) goto L24;
        ArrayList<MediaController.AlbumEntry> r03 = this.dropDownAlbums;
        if (r03 == null) goto L23;
        if (r03.isEmpty() == true) goto L23;
        if (this.dropDownAlbums.get(0) != this.selectedAlbum) goto L23;
    L5:
        if (r0.isEmpty() == true) goto L11;
        if (this.dropDownAlbums.get(0) != this.selectedAlbum) goto L11;
        if (this.drafts.size() <= 2) goto L11;
        r02 = true;
        goto L12
    }

    public void allowSearch(boolean r1) {
        ActionBarMenuItem r0 = this.searchItem;
        if (r1 == false) goto L5;
        int r12 = 0;
    L6:
        r0.setVisibility(r12);
        return;
    L5:
        r12 = 8;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.albumsDidLoad) goto L26;
        updateAlbumsDropDown();
        int r42 = 0;
        if (this.selectedAlbum != null) goto L14;
        ArrayList<MediaController.AlbumEntry> r32 = this.dropDownAlbums;
        if (r32 != null) goto L9;
    L12:
        this.selectedAlbum = MediaController.allMediaAlbumEntry;
    L21:
        this.photos = getPhotoEntries(this.selectedAlbum);
        this.selectedPhotos.clear();
        updateContainsDrafts();
        Adapter r2 = this.adapter;
        if (r2 == null) goto L33;
        r2.notifyDataSetChanged();
        return;
    L33:
        return;
    L9:
        if (r32.isEmpty() == true) goto L12;
        this.selectedAlbum = this.dropDownAlbums.get(0);
    L14:
        if (r42 >= MediaController.allMediaAlbums.size()) goto L21;
        MediaController.AlbumEntry r33 = MediaController.allMediaAlbums.get(r42);
        int r52 = r33.bucketId;
        MediaController.AlbumEntry r0 = this.selectedAlbum;
        if (r52 != r0.bucketId) goto L20;
        if (r33.videoOnly != r0.videoOnly) goto L20;
        this.selectedAlbum = r33;
    L20:
        r42 = r42 + 1;
        goto L14
    L26:
        if (r3 != NotificationCenter.storiesDraftsUpdated) goto L34;
        updateDrafts();
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        float r0 = top();
        int r1 = (AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(32.0f);
        int r2 = 0;
        if (r0 > Math.max(0, r1)) goto L5;
        boolean r12 = true;
    L6:
        float r3 = this.actionBarT.set(r12);
        float r02 = AndroidUtilities.lerp(r0, 0.0f, r3);
        if (r12 == this.actionBarShown) goto L13;
        this.actionBarShown = r12;
        onFullScreen(r12);
        ViewPropertyAnimator r13 = this.listView.getFastScroll().animate();
        if (this.actionBarShown == false) goto L11;
        float r5 = 1.0f;
    L12:
        r13.alpha(r5).start();
        goto L13
    L11:
        r5 = 0.0f;
    L13:
        ActionBar r14 = this.actionBar;
        if (r14 == null) goto L21;
        r14.setAlpha(r3);
        if (r3 > 0.0f) goto L19;
        r2 = 8;
    L19:
        if (this.actionBar.getVisibility() == r2) goto L21;
        this.actionBar.setVisibility(r2);
    L21:
        HeaderView r15 = this.headerView;
        if (r15 == null) goto L24;
        r15.setAlpha(1.0f - r3);
    L24:
        RectF r16 = AndroidUtilities.rectTmp;
        r16.set(0.0f, r02, getWidth(), getHeight() + AndroidUtilities.dp(14.0f));
        r8.drawRoundRect(r16, AndroidUtilities.dp(14.0f), AndroidUtilities.dp(14.0f), this.backgroundPaint);
        r8.save();
        r8.clipRect(0.0f, r02, getWidth(), getHeight());
        super.dispatchDraw(r8);
        r8.restore();
        return;
    L5:
        r12 = false;
        goto L6
    }

    public void firstLayout() {
    }

    public int getPadding() {
        return (int) (AndroidUtilities.displaySize.y * 0.35f);
    }

    public MediaController.AlbumEntry getSelectedAlbum() {
        return this.selectedAlbum;
    }

    public String getTitle() {
        if (this.onlyPhotos == false) goto L5;
        int r0 = R.string.AddImage;
    L7:
        return LocaleController.getString(r0);
    L5:
        r0 = R.string.ChoosePhotoOrVideo;
        goto L7
    }

    public boolean isMultiple() {
        if (this.selectedPhotos.isEmpty() == true) goto L5;
        return true;
    L5:
        if (this.multipleOnClick == true) goto L11;
        return false;
    L11:
        return true;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesDraftsUpdated);
        super.onAttachedToWindow();
    }

    public boolean onBackPressed() {
        ActionBarMenuItem r0 = this.searchItem;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.isSearchFieldVisible() == false) goto L14;
        EditTextBoldCursor r02 = this.searchItem.getSearchField();
        if (this.keyboardNotifier.keyboardVisible() == false) goto L10;
        AndroidUtilities.hideKeyboard(r02);
        return true;
    L10:
        this.actionBar.onSearchFieldVisibilityChanged(this.searchItem.toggleSearch(true));
        return true;
    L14:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.albumsDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesDraftsUpdated);
        Cell.cleanupQueues();
    }

    public void onFullScreen(boolean r1) {
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r9, int r10) {
        this.listView.setPinnedSectionOffsetY(AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight());
        RecyclerListView r0 = this.listView;
        int r1 = AndroidUtilities.dp(6.0f);
        int r2 = AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight();
        int r3 = AndroidUtilities.dp(1.0f);
        if (this.buttonsLayout != null) goto L6;
        int r4 = 0;
    L10:
        r0.setPadding(r1, r2, r3, r4 + AndroidUtilities.navigationBarHeight);
        ImageView r02 = this.selectButton;
        if (r02 == null) goto L13;
        r02.setTranslationY(-AndroidUtilities.navigationBarHeight);
    L13:
        LinearLayout r03 = this.buttonsLayout;
        if (r03 == null) goto L20;
        int r22 = AndroidUtilities.dp(10.0f);
        int r32 = AndroidUtilities.dp(10.0f);
        int r42 = AndroidUtilities.dp(10.0f);
        if (AndroidUtilities.navigationBarHeight <= 0) goto L18;
        float r6 = 0.0f;
    L19:
        r03.setPadding(r22, r32, r42, AndroidUtilities.dp(r6) + AndroidUtilities.navigationBarHeight);
        goto L20
    L18:
        r6 = 10.0f;
    L20:
        FrameLayout.LayoutParams r04 = (FrameLayout.LayoutParams) this.searchContainer.getLayoutParams();
        r04.leftMargin = 0;
        r04.topMargin = AndroidUtilities.statusBarHeight + ActionBar.getCurrentActionBarHeight();
        r04.rightMargin = 0;
        r04.bottomMargin = AndroidUtilities.navigationBarHeight;
        this.dropDown.setPadding(0, AndroidUtilities.statusBarHeight, AndroidUtilities.dp(10.0f), 0);
        TextView r05 = this.dropDown;
        if (AndroidUtilities.isTablet() == true) goto L25;
        Point r12 = AndroidUtilities.displaySize;
        if (r12.x <= r12.y) goto L25;
        float r13 = 18.0f;
    L26:
        r05.setTextSize(r13);
        super.onMeasure(r9, r10);
        return;
    L25:
        r13 = 20.0f;
        goto L26
    L6:
        if (AndroidUtilities.navigationBarHeight <= 0) goto L8;
        int r43 = 0;
    L9:
        r4 = AndroidUtilities.dp(r43 + 114);
        goto L10
    L8:
        r43 = 10;
        goto L9
    }

    public void onScroll() {
    }

    public void setMaxCount(int r1) {
        this.maxCount = r1;
    }

    public void setMultipleOnClick(boolean r2) {
        if (this.multipleOnClick == r2) goto L6;
        this.multipleOnClick = r2;
        AndroidUtilities.updateVisibleRows(this.listView);
        return;
    }

    public void setOnBackClickListener(Runnable r1) {
        this.onBackClickListener = r1;
    }

    public void setOnSelectListener(Utilities.Callback2<Object, Bitmap> r1) {
        this.onSelectListener = r1;
    }

    public void setOnSelectMultipleListener(Utilities.Callback3<Boolean, ArrayList<MediaController.PhotoEntry>, ArrayList<Bitmap>> r1) {
        this.onSelectMultipleListener = r1;
    }

    public int top() {
        RecyclerListView r0 = this.listView;
        if (r0 != null) goto L5;
    L17:
        int r02 = getPadding();
    L18:
        RecyclerListView r5 = this.listView;
        if (r5 != null) goto L22;
        return r02;
    L22:
        return AndroidUtilities.lerp(0, r02, r5.getAlpha());
    L5:
        if (r0.getChildCount() <= 0) goto L17;
        int r2 = Integer.MAX_VALUE;
        if (this.listView == null) goto L16;
        int r03 = 0;
    L11:
        if (r03 >= this.listView.getChildCount()) goto L16;
        View r3 = this.listView.getChildAt(r03);
        if (this.listView.getChildAdapterPosition(r3) <= 0) goto L15;
        r2 = Math.min(r2, (int) r3.getY());
    L15:
        r03 = r03 + 1;
    L16:
        r02 = Math.max(0, Math.min(r2, getHeight()));
        goto L18
    }

    public void updateDrafts() {
        this.drafts.clear();
        if (this.onlyPhotos == true) goto L11;
        ArrayList<StoryEntry> r0 = MessagesController.getInstance(this.currentAccount).getStoriesController().getDraftsController().drafts;
        int r1 = r0.size();
        int r2 = 0;
    L5:
        if (r2 >= r1) goto L11;
        StoryEntry r3 = r0.get(r2);
        r2 = r2 + 1;
        StoryEntry r32 = r3;
        if (r32.isEdit == true) goto L5;
        if (r32.isError == true) goto L5;
        this.drafts.add(r32);
    L11:
        updateAlbumsDropDown();
        updateContainsDrafts();
        Adapter r5 = this.adapter;
        if (r5 == null) goto L22;
        r5.notifyDataSetChanged();
        return;
    }

    public void updateSelectButtonVisible() {
        boolean r0 = this.selectedPhotos.isEmpty();
        boolean r1 = !r0;
        ImageView r2 = this.selectButton;
        float r5 = 0.0f;
        float r6 = 1.0f;
        if (r2 == null) goto L22;
        ViewPropertyAnimator r22 = r2.animate();
        if (r0 == true) goto L7;
        float r7 = 1.0f;
    L8:
        ViewPropertyAnimator r23 = r22.alpha(r7);
        float r72 = 0.7f;
        if (r0 == true) goto L11;
        float r8 = 1.0f;
    L12:
        ViewPropertyAnimator r24 = r23.scaleX(r8);
        if (r0 == true) goto L15;
        r72 = 1.0f;
    L15:
        ViewPropertyAnimator r25 = r24.scaleY(r72);
        if (r0 == true) goto L19;
        int r73 = -AndroidUtilities.navigationBarHeight;
    L20:
        r25.translationY(r73).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).start();
        goto L22
    L19:
        r73 = AndroidUtilities.dp(8.0f);
        goto L20
    L11:
        r8 = 0.7f;
        goto L12
    L7:
        r7 = 0.0f;
    L22:
        if (this.buttonsLayout == null) goto L41;
        ButtonWithCounterView r26 = this.button1View;
        if (r26 == null) goto L26;
        r26.setText(LocaleController.formatPluralStringComma("StoriesCreate", Math.max(1, this.selectedPhotos.size())), true);
    L26:
        LinearLayout r27 = this.buttonsLayout;
        float r74 = 10.0f;
        int r82 = AndroidUtilities.dp(10.0f);
        int r9 = AndroidUtilities.dp(10.0f);
        int r10 = AndroidUtilities.dp(10.0f);
        if (AndroidUtilities.navigationBarHeight <= 0) goto L29;
        r74 = 0.0f;
    L29:
        r27.setPadding(r82, r9, r10, AndroidUtilities.dp(r74) + AndroidUtilities.navigationBarHeight);
        if (this.buttonsLayoutVisible == r1) goto L42;
        this.buttonsLayoutVisible = r1;
        this.buttonsLayout.setVisibility(0);
        ViewPropertyAnimator r28 = this.buttonsLayout.animate();
        if (r0 == false) goto L35;
        r6 = 0.0f;
    L35:
        ViewPropertyAnimator r29 = r28.alpha(r6);
        if (r0 == false) goto L39;
        r5 = AndroidUtilities.dp(32.0f);
    L39:
        r29.translationY(r5).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).setDuration(320).setListener(new AnonymousClass13(this, r1)).start();
        return;
    L42:
        return;
    }
}
