package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.LocationController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.SharingLiveLocationCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.ContentPreviewViewer;

/* loaded from: classes7.dex */
public class SharingLocationsAlert extends BottomSheet implements NotificationCenter.NotificationCenterDelegate {
    private ListAdapter adapter;
    private SharingLocationsAlertDelegate delegate;
    private boolean ignoreLayout;
    private RecyclerListView listView;
    private int scrollOffsetY;
    private Drawable shadowDrawable;
    private TextView textView;

    /* renamed from: org.telegram.ui.Components.SharingLocationsAlert$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ SharingLocationsAlert this$0;

        public AnonymousClass1(SharingLocationsAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            SharingLocationsAlert.m13000$$Nest$fgetshadowDrawable(this.this$0).setBounds(0, SharingLocationsAlert.m12999$$Nest$fgetscrollOffsetY(this.this$0) - SharingLocationsAlert.access$000(this.this$0), getMeasuredWidth(), getMeasuredHeight());
            SharingLocationsAlert.m13000$$Nest$fgetshadowDrawable(this.this$0).draw(r6);
        }

        @Override // android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L11;
            if (SharingLocationsAlert.m12999$$Nest$fgetscrollOffsetY(this.this$0) == 0) goto L11;
            if (r3.getY() >= SharingLocationsAlert.m12999$$Nest$fgetscrollOffsetY(this.this$0)) goto L11;
            this.this$0.dismiss();
            return true;
        L11:
            return super.onInterceptTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            SharingLocationsAlert.m13005$$Nest$mupdateLayout(this.this$0);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r72 = View.MeasureSpec.getSize(r7) - AndroidUtilities.statusBarHeight;
            getMeasuredWidth();
            int r1 = ((AndroidUtilities.dp(56.0f) + AndroidUtilities.dp(56.0f)) + 1) + (LocationController.getLocationsCount() * AndroidUtilities.dp(54.0f));
            int r2 = r72 / 5;
            if (r1 >= (r2 * 3)) goto L5;
            int r22 = AndroidUtilities.dp(8.0f);
        L9:
            if (SharingLocationsAlert.m12998$$Nest$fgetlistView(this.this$0).getPaddingTop() == r22) goto L11;
            SharingLocationsAlert.m13002$$Nest$fputignoreLayout(this.this$0, true);
            SharingLocationsAlert.m12998$$Nest$fgetlistView(this.this$0).setPadding(0, r22, 0, AndroidUtilities.dp(8.0f));
            SharingLocationsAlert.m13002$$Nest$fputignoreLayout(this.this$0, false);
        L11:
            super.onMeasure(r6, View.MeasureSpec.makeMeasureSpec(Math.min(r1, r72), TLObject.FLAG_30));
            return;
        L5:
            r22 = r2 * 2;
            if (r1 >= r72) goto L9;
            r22 = r22 - (r72 - r1);
            goto L9
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isDismissed() == false) goto L5;
            return false;
        L5:
            if (super.onTouchEvent(r2) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (SharingLocationsAlert.m12997$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharingLocationsAlert$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ SharingLocationsAlert this$0;

        public AnonymousClass2(SharingLocationsAlert r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean onInterceptTouchEvent(MotionEvent r7) {
            boolean r72 = ContentPreviewViewer.getInstance().onInterceptTouchEvent(r7, SharingLocationsAlert.m12998$$Nest$fgetlistView(this.this$0), 0, null, this.resourcesProvider);
            if (super.onInterceptTouchEvent(r7) == true) goto L8;
            if (r72 == true) goto L10;
            return false;
        L10:
            return true;
        L8:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View, android.view.ViewParent
        public void requestLayout() {
            if (SharingLocationsAlert.m12997$$Nest$fgetignoreLayout(this.this$0) == false) goto L5;
            return;
        L5:
            super.requestLayout();
        }
    }

    /* renamed from: org.telegram.ui.Components.SharingLocationsAlert$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SharingLocationsAlert this$0;

        public AnonymousClass3(SharingLocationsAlert r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            SharingLocationsAlert.m13005$$Nest$mupdateLayout(this.this$0);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private Context context;
        final /* synthetic */ SharingLocationsAlert this$0;

        /* renamed from: org.telegram.ui.Components.SharingLocationsAlert$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends FrameLayout {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onDraw(Canvas r9) {
                r9.drawLine(0.0f, AndroidUtilities.dp(40.0f), getMeasuredWidth(), AndroidUtilities.dp(40.0f), Theme.dividerPaint);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f) + 1, TLObject.FLAG_30));
            }
        }

        public ListAdapter(SharingLocationsAlert r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return LocationController.getLocationsCount() + 1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 != 0) goto L5;
            return 1;
        L5:
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
        public void onBindViewHolder(RecyclerView.ViewHolder r3, int r4) {
            int r0 = r3.getItemViewType();
            if (r0 == 0) goto L10;
            if (r0 == 1) goto L7;
            return;
        L7:
            if (SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0) == null) goto L12;
            SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0).setText(LocaleController.formatString("SharingLiveLocationTitle", R.string.SharingLiveLocationTitle, new Object[]{LocaleController.formatPluralString("Chats", LocationController.getLocationsCount(), new Object[0])}));
            return;
        L12:
            return;
        L10:
            ((SharingLiveLocationCell) r3.itemView).setDialog(SharingLocationsAlert.m13004$$Nest$mgetLocation(this.this$0, r4 - 1));
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r4, int r5) {
            if (r5 == 0) goto L5;
            FrameLayout r52 = new AnonymousClass1(this, this.context);
            r52.setWillNotDraw(false);
            SharingLocationsAlert.m13003$$Nest$fputtextView(this.this$0, new TextView(this.context));
            SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0).setTextColor(SharingLocationsAlert.access$200(this.this$0, Theme.key_dialogIcon));
            SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0).setTextSize(1, 14.0f);
            SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0).setGravity(17);
            SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0).setPadding(0, 0, 0, AndroidUtilities.dp(8.0f));
            r52.addView(SharingLocationsAlert.m13001$$Nest$fgettextView(this.this$0), LayoutHelper.createFrame(-1, 40.0f));
        L7:
            return new RecyclerListView.Holder(r52);
        L5:
            r52 = new SharingLiveLocationCell(this.context, false, 54, SharingLocationsAlert.access$100(this.this$0));
            goto L7
        }
    }

    public interface SharingLocationsAlertDelegate {
        void didSelectLocation(LocationController.SharingLocationInfo r1);
    }

    public static /* synthetic */ void $r8$lambda$A6jb1kqVJNcLxlnyiopTnANYi9w(SharingLocationsAlert r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$igvUHYoUvBsrX0GCIhLN4AtdMbg(SharingLocationsAlert r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$o_6B_7Z6wmXPGVrqMBA3Y7cGSNs(SharingLocationsAlert r0, View r1, int r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12997$$Nest$fgetignoreLayout(SharingLocationsAlert r0) {
        return r0.ignoreLayout;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m12998$$Nest$fgetlistView(SharingLocationsAlert r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetscrollOffsetY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12999$$Nest$fgetscrollOffsetY(SharingLocationsAlert r0) {
        return r0.scrollOffsetY;
    }

    /* renamed from: -$$Nest$fgetshadowDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Drawable m13000$$Nest$fgetshadowDrawable(SharingLocationsAlert r0) {
        return r0.shadowDrawable;
    }

    /* renamed from: -$$Nest$fgettextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TextView m13001$$Nest$fgettextView(SharingLocationsAlert r0) {
        return r0.textView;
    }

    /* renamed from: -$$Nest$fputignoreLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13002$$Nest$fputignoreLayout(SharingLocationsAlert r0, boolean r1) {
        r0.ignoreLayout = r1;
    }

    /* renamed from: -$$Nest$fputtextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13003$$Nest$fputtextView(SharingLocationsAlert r0, TextView r1) {
        r0.textView = r1;
    }

    /* renamed from: -$$Nest$mgetLocation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LocationController.SharingLocationInfo m13004$$Nest$mgetLocation(SharingLocationsAlert r0, int r1) {
        return r0.getLocation(r1);
    }

    /* renamed from: -$$Nest$mupdateLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m13005$$Nest$mupdateLayout(SharingLocationsAlert r0) {
        r0.updateLayout();
    }

    public SharingLocationsAlert(Context r10, SharingLocationsAlertDelegate r11, Theme.ResourcesProvider r12) {
        super(r10, false, r12);
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.liveLocationsChanged);
        this.delegate = r11;
        fixNavigationBar();
        Drawable r112 = r10.getResources().getDrawable(R.drawable.sheet_shadow_round).mutate();
        this.shadowDrawable = r112;
        int r1 = Theme.key_dialogBackground;
        r112.setColorFilter(new PorterDuffColorFilter(getThemedColor(r1), PorterDuff.Mode.MULTIPLY));
        AnonymousClass1 r113 = new AnonymousClass1(this, r10);
        this.containerView = r113;
        r113.setWillNotDraw(false);
        ViewGroup r114 = this.containerView;
        int r122 = this.backgroundPaddingLeft;
        r114.setPadding(r122, 0, r122, 0);
        AnonymousClass2 r115 = new AnonymousClass2(this, r10);
        this.listView = r115;
        r115.setLayoutManager(new LinearLayoutManager(getContext(), 1, false));
        RecyclerListView r116 = this.listView;
        ListAdapter r123 = new ListAdapter(this, r10);
        this.adapter = r123;
        r116.setAdapter(r123);
        this.listView.setVerticalScrollBarEnabled(false);
        this.listView.setClipToPadding(false);
        this.listView.setEnabled(true);
        this.listView.setGlowColor(getThemedColor(Theme.key_dialogScrollGlow));
        this.listView.setOnScrollListener(new AnonymousClass3(this));
        this.listView.setOnItemClickListener(new SharingLocationsAlert$$ExternalSyntheticLambda0(this));
        this.containerView.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 0.0f, 0.0f, 48.0f));
        View r117 = new View(r10);
        r117.setBackgroundResource(R.drawable.header_shadow_reverse);
        this.containerView.addView(r117, LayoutHelper.createFrame(-1, 3.0f, 83, 0.0f, 0.0f, 0.0f, 48.0f));
        PickerBottomLayout r118 = new PickerBottomLayout(r10, false);
        r118.setBackgroundColor(getThemedColor(r1));
        this.containerView.addView(r118, LayoutHelper.createFrame(-1, 48, 83));
        r118.cancelButton.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r118.cancelButton.setTextColor(getThemedColor(Theme.key_text_RedBold));
        r118.cancelButton.setText(LocaleController.getString(R.string.StopAllLocationSharings));
        r118.cancelButton.setOnClickListener(new SharingLocationsAlert$$ExternalSyntheticLambda1(this));
        r118.doneButtonTextView.setTextColor(getThemedColor(Theme.key_dialogTextBlue2));
        r118.doneButtonTextView.setText(LocaleController.getString(R.string.Close).toUpperCase());
        r118.doneButton.setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        r118.doneButton.setOnClickListener(new SharingLocationsAlert$$ExternalSyntheticLambda2(this));
        r118.doneButtonBadgeTextView.setVisibility(8);
        this.adapter.notifyDataSetChanged();
    }

    public static /* synthetic */ int access$000(SharingLocationsAlert r0) {
        return r0.backgroundPaddingTop;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$100(SharingLocationsAlert r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$200(SharingLocationsAlert r0, int r1) {
        return r0.getThemedColor(r1);
    }

    private LocationController.SharingLocationInfo getLocation(int r3) {
        int r2 = 0;
    L4:
        if (r2 >= 16) goto L10;
        ArrayList<LocationController.SharingLocationInfo> r0 = LocationController.getInstance(r2).sharingLocationsUI;
        if (r3 < r0.size()) goto L9;
        r3 = r3 - r0.size();
        r2 = r2 + 1;
        goto L4
    L9:
        return r0.get(r3);
    L10:
        return null;
    }

    private /* synthetic */ void lambda$new$0(View r1, int r2) {
        int r22 = r2 - 1;
        if (r22 >= 0) goto L5;
        return;
    L5:
        if (r22 >= LocationController.getLocationsCount()) goto L10;
        this.delegate.didSelectLocation(getLocation(r22));
        dismiss();
        return;
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        int r22 = 0;
    L4:
        if (r22 >= 16) goto L6;
        LocationController.getInstance(r22).removeAllLocationSharings();
        r22 = r22 + 1;
        goto L4
    L6:
        dismiss();
    }

    private /* synthetic */ void lambda$new$2(View r1) {
        dismiss();
    }

    @SuppressLint({"NewApi"})
    private void updateLayout() {
        int r0 = this.listView.getChildCount();
        RecyclerListView r1 = this.listView;
        if (r0 > 0) goto L6;
        int r02 = r1.getPaddingTop();
        this.scrollOffsetY = r02;
        r1.setTopGlowOffset(r02);
        this.containerView.invalidate();
        return;
    L6:
        int r03 = 0;
        View r12 = r1.getChildAt(0);
        RecyclerListView.Holder r2 = (RecyclerListView.Holder) this.listView.findContainingViewHolder(r12);
        int r13 = r12.getTop() - AndroidUtilities.dp(8.0f);
        if (r13 <= 0) goto L13;
        if (r2 == null) goto L13;
        if (r2.getAdapterPosition() != 0) goto L13;
        r03 = r13;
    L13:
        if (this.scrollOffsetY == r03) goto L16;
        RecyclerListView r14 = this.listView;
        this.scrollOffsetY = r03;
        r14.setTopGlowOffset(r03);
        this.containerView.invalidate();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.liveLocationsChanged) goto L5;
        return;
    L5:
        if (LocationController.getLocationsCount() != 0) goto L8;
        dismiss();
        return;
    L8:
        this.adapter.notifyDataSetChanged();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog, android.content.DialogInterface, org.telegram.ui.ActionBar.BaseFragment.AttachedSheet
    public void dismiss() {
        super.dismiss();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.liveLocationsChanged);
    }
}
