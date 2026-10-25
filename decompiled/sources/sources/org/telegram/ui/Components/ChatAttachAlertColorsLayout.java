package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.core.util.Consumer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.WallpaperCell;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.PhotoViewer;
import org.telegram.ui.WallpapersListActivity;

/* loaded from: classes7.dex */
public class ChatAttachAlertColorsLayout extends ChatAttachAlert.AttachAlertLayout {
    Adapter adapter;
    public int currentItemTop;
    public RecyclerListView gridView;
    private int itemSize;
    private int itemsPerRow;
    GridLayoutManager layoutManager;
    Consumer<Object> wallpaperConsumer;

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListView {
        final /* synthetic */ ChatAttachAlertColorsLayout this$0;

        public AnonymousClass1(ChatAttachAlertColorsLayout r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L8;
            if (r5.getY() >= (this.this$0.parentAlert.scrollOffsetY[0] - AndroidUtilities.dp(80.0f))) goto L8;
            return false;
        L8:
            return super.onInterceptTouchEvent(r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            PhotoViewer.getInstance().checkCurrentImageVisibility();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public boolean onTouchEvent(MotionEvent r5) {
            if (r5.getAction() != 0) goto L8;
            if (r5.getY() >= (this.this$0.parentAlert.scrollOffsetY[0] - AndroidUtilities.dp(80.0f))) goto L8;
            return false;
        L8:
            return super.onTouchEvent(r5);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatAttachAlertColorsLayout this$0;

        public AnonymousClass2(ChatAttachAlertColorsLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r4, int r5) {
            if (r5 != 0) goto L15;
            int r42 = AndroidUtilities.dp(13.0f);
            ActionBarMenuItem r52 = this.this$0.parentAlert.selectedMenuItem;
            if (r52 == null) goto L6;
            int r53 = AndroidUtilities.dp(r52.getAlpha() * 26.0f);
        L7:
            int r43 = r42 + r53;
            int r54 = this.this$0.parentAlert.getBackgroundPaddingTop();
            if ((((this.this$0.parentAlert.scrollOffsetY[0] - r54) - r43) + r54) >= ActionBar.getCurrentActionBarHeight()) goto L16;
            RecyclerListView.Holder r44 = (RecyclerListView.Holder) this.this$0.gridView.findViewHolderForAdapterPosition(0);
            if (r44 != null) goto L12;
            return;
        L12:
            if (r44.itemView.getTop() <= AndroidUtilities.dp(7.0f)) goto L18;
            this.this$0.gridView.smoothScrollBy(0, r44.itemView.getTop() - AndroidUtilities.dp(7.0f));
            return;
        L18:
            return;
        L16:
            return;
        L6:
            r53 = 0;
            goto L7
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            if (this.this$0.gridView.getChildCount() > 0) goto L5;
            return;
        L5:
            ChatAttachAlertColorsLayout r0 = this.this$0;
            r0.parentAlert.updateLayout(r0, true, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$3, reason: invalid class name */
    public class AnonymousClass3 extends GridLayoutManager {
        final /* synthetic */ ChatAttachAlertColorsLayout this$0;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$3$1, reason: invalid class name */
        public class AnonymousClass1 extends LinearSmoothScroller {
            final /* synthetic */ AnonymousClass3 this$1;

            public AnonymousClass1(AnonymousClass3 r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateDyToMakeVisible(View r1, int r2) {
                return super.calculateDyToMakeVisible(r1, r2) - (this.this$1.this$0.gridView.getPaddingTop() - AndroidUtilities.dp(7.0f));
            }

            @Override // androidx.recyclerview.widget.LinearSmoothScroller
            public int calculateTimeForDeceleration(int r1) {
                return super.calculateTimeForDeceleration(r1) * 2;
            }
        }

        public AnonymousClass3(ChatAttachAlertColorsLayout r1, Context r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public void smoothScrollToPosition(RecyclerView r1, RecyclerView.State r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, r1.getContext());
            r22.setTargetPosition(r3);
            startSmoothScroll(r22);
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$4, reason: invalid class name */
    public class AnonymousClass4 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ ChatAttachAlertColorsLayout this$0;

        public AnonymousClass4(ChatAttachAlertColorsLayout r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            int r0 = ChatAttachAlertColorsLayout.m9562$$Nest$fgetitemSize(this.this$0);
            if ((r3 % ChatAttachAlertColorsLayout.m9563$$Nest$fgetitemsPerRow(this.this$0)) == (ChatAttachAlertColorsLayout.m9563$$Nest$fgetitemsPerRow(this.this$0) - 1)) goto L5;
            int r2 = AndroidUtilities.dp(5.0f);
        L7:
            return r0 + r2;
        L5:
            r2 = 0;
            goto L7
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ ChatAttachAlertColorsLayout this$0;
        private final ArrayList<Object> wallpapers;

        /* renamed from: org.telegram.ui.Components.ChatAttachAlertColorsLayout$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends WallpaperCell {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2, int r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Cells.WallpaperCell
            public void onWallpaperClick(Object r1, int r2) {
                Consumer<Object> r0 = this.this$1.this$0.wallpaperConsumer;
                if (r0 == null) goto L6;
                r0.accept(r1);
                return;
            }
        }

        /* renamed from: -$$Nest$fgetwallpapers, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m9564$$Nest$fgetwallpapers(Adapter r0) {
            return r0.wallpapers;
        }

        public Adapter(ChatAttachAlertColorsLayout r1, Context r2) {
            this.this$0 = r1;
            this.wallpapers = new ArrayList();
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.wallpapers.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 0) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            WallpaperCell r0 = (WallpaperCell) r8.itemView;
            r0.setParams(1, false, false);
            r0.setSize(ChatAttachAlertColorsLayout.m9562$$Nest$fgetitemSize(this.this$0));
            r0.setWallpaper(1, 0, this.wallpapers.get(r9), null, null, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            AnonymousClass1 r22 = new AnonymousClass1(this, this.mContext, 1);
            r22.drawStubBackground = false;
            return new RecyclerListView.Holder(r22);
        }
    }

    /* renamed from: -$$Nest$fgetitemSize, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9562$$Nest$fgetitemSize(ChatAttachAlertColorsLayout r0) {
        return r0.itemSize;
    }

    /* renamed from: -$$Nest$fgetitemsPerRow, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m9563$$Nest$fgetitemsPerRow(ChatAttachAlertColorsLayout r0) {
        return r0.itemsPerRow;
    }

    public ChatAttachAlertColorsLayout(ChatAttachAlert r2, Context r3, Theme.ResourcesProvider r4) {
        super(r2, r3, r4);
        this.itemSize = AndroidUtilities.dp(80.0f);
        this.itemsPerRow = 3;
        this.currentItemTop = 0;
        AnonymousClass1 r0 = new AnonymousClass1(this, r3, r4);
        this.gridView = r0;
        Adapter r42 = new Adapter(this, r3);
        this.adapter = r42;
        r0.setAdapter(r42);
        this.gridView.setClipToPadding(false);
        this.gridView.setItemAnimator(null);
        this.gridView.setLayoutAnimation(null);
        this.gridView.setVerticalScrollBarEnabled(false);
        this.gridView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        addView(this.gridView, LayoutHelper.createFrame(-1, -1.0f));
        this.gridView.setOnScrollListener(new AnonymousClass2(this));
        AnonymousClass3 r22 = new AnonymousClass3(this, r3, this.itemSize);
        this.layoutManager = r22;
        r22.setSpanSizeLookup(new AnonymousClass4(this));
        this.gridView.setLayoutManager(this.layoutManager);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getCurrentItemTop() {
        int r0 = this.gridView.getChildCount();
        RecyclerListView r1 = this.gridView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.currentItemTop = r02;
        r1.setTopGlowOffset(r02);
        return Integer.MAX_VALUE;
    L6:
        View r03 = r1.getChildAt(0);
        RecyclerListView.Holder r12 = (RecyclerListView.Holder) this.gridView.findContainingViewHolder(r03);
        int r04 = r03.getTop();
        int r3 = AndroidUtilities.dp(7.0f);
        if (r04 < AndroidUtilities.dp(7.0f)) goto L12;
        if (r12 == null) goto L12;
        if (r12.getAdapterPosition() != 0) goto L12;
    L13:
        this.gridView.setTopGlowOffset(r04);
        this.currentItemTop = r04;
        return r04;
    L12:
        r04 = r3;
        goto L13
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getFirstOffset() {
        return getListTopPadding() + AndroidUtilities.dp(56.0f);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int getListTopPadding() {
        return this.gridView.getPaddingTop();
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public int needsActionBar() {
        return 1;
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onPreMeasure(int r7, int r8) {
        if (AndroidUtilities.isTablet() == false) goto L5;
        this.itemsPerRow = 4;
    L9:
        ((FrameLayout.LayoutParams) getLayoutParams()).topMargin = ActionBar.getCurrentActionBarHeight();
        int r72 = ((r7 - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(10.0f)) / this.itemsPerRow;
        if (this.itemSize == r72) goto L12;
        this.itemSize = r72;
        this.adapter.notifyDataSetChanged();
    L12:
        this.layoutManager.setSpanCount(Math.max(1, (this.itemsPerRow * r72) + (AndroidUtilities.dp(5.0f) * (this.itemsPerRow - 1))));
        int r0 = (int) Math.ceil((this.adapter.getItemCount() - 1) / this.itemsPerRow);
        int r73 = ((r8 - ((r72 * r0) + ((r0 - 1) * AndroidUtilities.dp(5.0f)))) - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.dp(60.0f);
        int r02 = 0;
        Math.max(0, r73);
        if (AndroidUtilities.isTablet() == true) goto L17;
        Point r74 = AndroidUtilities.displaySize;
        if (r74.x <= r74.y) goto L17;
        int r75 = (int) (r8 / 3.5f);
    L18:
        int r76 = r75 - AndroidUtilities.dp(52.0f);
        if (r76 < 0) goto L23;
        r02 = r76;
    L23:
        if (this.gridView.getPaddingTop() == r02) goto L26;
        this.gridView.setPadding(AndroidUtilities.dp(6.0f), r02, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(48.0f));
        return;
    L26:
        return;
    L17:
        r75 = (r8 / 5) * 2;
        goto L18
    L5:
        Point r03 = AndroidUtilities.displaySize;
        if (r03.x <= r03.y) goto L8;
        this.itemsPerRow = 4;
        goto L9
    L8:
        this.itemsPerRow = 3;
        goto L9
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void onShow(ChatAttachAlert.AttachAlertLayout r2) {
        this.parentAlert.actionBar.getTitleTextView().setBuildFullLayout(true);     // Catch: Exception -> L5
    L3:
        this.parentAlert.actionBar.setTitle(LocaleController.getString(R.string.SelectColor));
        this.layoutManager.scrollToPositionWithOffset(0, 0);
    }

    @Override // org.telegram.ui.Components.ChatAttachAlert.AttachAlertLayout
    public void scrollToTop() {
        this.gridView.smoothScrollToPosition(0);
    }

    public void setDelegate(Consumer<Object> r1) {
        this.wallpaperConsumer = r1;
    }

    @Override // android.view.View
    public void setTranslationY(float r1) {
        super.setTranslationY(r1);
        this.parentAlert.getSheetContainer().invalidate();
        invalidate();
    }

    public void updateColors(boolean r2) {
        Adapter.m9564$$Nest$fgetwallpapers(this.adapter).clear();
        WallpapersListActivity.fillDefaultColors(Adapter.m9564$$Nest$fgetwallpapers(this.adapter), r2);
        this.adapter.notifyDataSetChanged();
    }
}
