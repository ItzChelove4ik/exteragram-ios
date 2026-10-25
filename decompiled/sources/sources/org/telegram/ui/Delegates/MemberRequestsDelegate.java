package org.telegram.ui.Delegates;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.LongSparseArray;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AndroidUtilities$$ExternalSyntheticApiModelOutline1;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DispatchQueue;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MemberRequestsController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarPreviewPagerIndicator;
import org.telegram.ui.Cells.MemberRequestCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.ProfileGalleryView;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public abstract class MemberRequestsDelegate implements MemberRequestCell.OnClickListener {
    private final Adapter adapter;
    private final ArrayList<TLRPC.TL_chatInviteImporter> allImporters;
    private final long chatId;
    private final MemberRequestsController controller;
    private final int currentAccount;
    private final List<TLRPC.TL_chatInviteImporter> currentImporters;
    private StickerEmptyView emptyView;
    private final BaseFragment fragment;
    private boolean hasMore;
    private TLRPC.TL_chatInviteImporter importer;
    public final boolean isChannel;
    private boolean isDataLoaded;
    private boolean isFirstLoading;
    private boolean isLoading;
    public boolean isNeedRestoreList;
    private boolean isSearchExpanded;
    private boolean isShowLastItemDivider;
    private final FrameLayout layoutContainer;
    private final RecyclerView.OnScrollListener listScrollListener;
    private final Runnable loadMembersRunnable;
    private FlickerLoadingView loadingView;
    private PreviewDialog previewDialog;
    private String query;
    private RecyclerListView recyclerView;
    private FrameLayout rootLayout;
    private StickerEmptyView searchEmptyView;
    private int searchRequestId;
    private Runnable searchRunnable;
    private final boolean showSearchMenu;
    private final LongSparseArray<TLRPC.User> users;

    /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MemberRequestsDelegate this$0;
        final /* synthetic */ RecyclerView.OnScrollListener val$currentScrollListener;

        public AnonymousClass1(MemberRequestsDelegate r1, RecyclerView.OnScrollListener r2) {
            this.this$0 = r1;
            this.val$currentScrollListener = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            super.onScrollStateChanged(r2, r3);
            this.val$currentScrollListener.onScrollStateChanged(r2, r3);
            MemberRequestsDelegate.m14289$$Nest$fgetlistScrollListener(this.this$0).onScrollStateChanged(r2, r3);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r2, int r3, int r4) {
            super.onScrolled(r2, r3, r4);
            this.val$currentScrollListener.onScrolled(r2, r3, r4);
            MemberRequestsDelegate.m14289$$Nest$fgetlistScrollListener(this.this$0).onScrolled(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MemberRequestsDelegate this$0;

        public AnonymousClass2(MemberRequestsDelegate r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            LinearLayoutManager r12 = (LinearLayoutManager) r1.getLayoutManager();
            if (MemberRequestsDelegate.m14286$$Nest$fgethasMore(this.this$0) == true) goto L5;
            return;
        L5:
            if (MemberRequestsDelegate.m14287$$Nest$fgetisLoading(this.this$0) == true) goto L12;
            if (r12 == null) goto L13;
            int r13 = r12.findLastVisibleItemPosition();
            if ((MemberRequestsDelegate.m14282$$Nest$fgetadapter(this.this$0).getItemCount() - r13) >= 10) goto L14;
            AndroidUtilities.cancelRunOnUIThread(MemberRequestsDelegate.m14290$$Nest$fgetloadMembersRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(MemberRequestsDelegate.m14290$$Nest$fgetloadMembersRunnable(this.this$0));
            return;
        L14:
            return;
        L13:
            return;
        }
    }

    public class Adapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ MemberRequestsDelegate this$0;

        /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$Adapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass1(Adapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(52.0f), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$Adapter$2, reason: invalid class name */
        public class AnonymousClass2 extends FlickerLoadingView {
            final /* synthetic */ Adapter this$1;

            public AnonymousClass2(Adapter r1, Context r2, Theme.ResourcesProvider r3) {
                this.this$1 = r1;
                super(r2, r3);
            }

            @Override // org.telegram.ui.Components.FlickerLoadingView, android.view.View
            public void onMeasure(int r1, int r2) {
                setMeasuredDimension(View.MeasureSpec.getSize(r1), AndroidUtilities.dp(104.0f));
            }
        }

        /* renamed from: -$$Nest$mextraFirstHolders, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14293$$Nest$mextraFirstHolders(Adapter r0) {
            return r0.extraFirstHolders();
        }

        private Adapter(MemberRequestsDelegate r1) {
            this.this$0 = r1;
        }

        public /* synthetic */ Adapter(MemberRequestsDelegate r1, MemberRequestsDelegateIA r2) {
            this(r1);
        }

        private int extraFirstHolders() {
            return !MemberRequestsDelegate.m14288$$Nest$fgetisShowLastItemDivider(this.this$0) ? 1 : 0;
        }

        private int extraLastHolders() {
            if (MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).isEmpty() == false) goto L5;
            return 0;
        L5:
            if (MemberRequestsDelegate.m14286$$Nest$fgethasMore(this.this$0) == false) goto L10;
            return 1;
        L10:
            return 0;
        }

        public void appendItems(List<TLRPC.TL_chatInviteImporter> r8) {
            int r1 = 0;
        L4:
            if (r1 >= r8.size()) goto L13;
            long r2 = r8.get(r1).user_id;
            int r4 = 0;
        L7:
            if (r4 >= MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size()) goto L12;
            if (((TLRPC.TL_chatInviteImporter) MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).get(r4)).user_id == r2) goto L10;
            r4 = r4 + 1;
            goto L7
        L10:
            r8.remove(r1);
            r1 = r1 - 1;
        L12:
            r1 = r1 + 1;
            goto L4
        L13:
            MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).addAll(r8);
            notifyItemRangeInserted(((!MemberRequestsDelegate.m14288$$Nest$fgetisShowLastItemDivider(this.this$0) ? 1 : 0) + MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size()) - r8.size(), r8.size());
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return (extraFirstHolders() + MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size()) + extraLastHolders();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != 0) goto L8;
            if (MemberRequestsDelegate.m14288$$Nest$fgetisShowLastItemDivider(this.this$0) == true) goto L8;
            return 2;
        L8:
            if (r2 == (getItemCount() - 1)) goto L10;
            return 0;
        L10:
            if (extraLastHolders() <= 0) goto L15;
            return 4;
        L15:
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
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            if (r5.getItemViewType() != 0) goto L13;
            MemberRequestCell r52 = (MemberRequestCell) r5.itemView;
            int r62 = r6 - extraFirstHolders();
            LongSparseArray<TLRPC.User> r0 = MemberRequestsDelegate.m14291$$Nest$fgetusers(this.this$0);
            TLRPC.TL_chatInviteImporter r1 = (TLRPC.TL_chatInviteImporter) MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).get(r62);
            boolean r3 = true;
            if (r62 == (MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size() - 1)) goto L7;
        L10:
            r52.setData(r0, r1, r3);
            return;
        L7:
            if (MemberRequestsDelegate.m14286$$Nest$fgethasMore(this.this$0) == true) goto L10;
            r3 = false;
            goto L10
        L13:
            if (r5.getItemViewType() != 2) goto L16;
            r5.itemView.requestLayout();
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public /* bridge */ /* synthetic */ RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r1, int r2) {
            return onCreateViewHolder(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerListView.Holder onCreateViewHolder(ViewGroup r5, int r6) {
            if (r6 != 1) goto L5;
            View r62 = new View(r5.getContext());
        L19:
            return new RecyclerListView.Holder(r62);
        L5:
            if (r6 != 2) goto L7;
            AnonymousClass1 r63 = new AnonymousClass1(this, r5.getContext());
            r63.setTag(-33024);
            r62 = r63;
            goto L19
        L7:
            if (r6 != 3) goto L9;
            r62 = new View(r5.getContext());
            goto L19
        L9:
            if (r6 == 4) goto L11;
            Context r52 = r5.getContext();
            MemberRequestsDelegate r4 = this.this$0;
            r62 = new MemberRequestCell(r52, r4, r4.isChannel);
            goto L19
        L11:
            AnonymousClass2 r64 = new AnonymousClass2(this, MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).getParentActivity(), MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).getResourceProvider());
            if (MemberRequestsDelegate.m14288$$Nest$fgetisShowLastItemDivider(this.this$0) == false) goto L14;
            r64.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).getResourceProvider()));
        L14:
            r64.setColors(Theme.key_windowBackgroundWhite, Theme.key_windowBackgroundGray, -1);
            r64.setViewType(15);
            r64.setMemberRequestButton(this.this$0.isChannel);
            r64.setIsSingleCell(true);
            r64.setItemsCount(1);
            r64.setTag(-33024);
            r62 = r64;
            goto L19
        }

        public void removeItem(TLRPC.TL_chatInviteImporter r6) {
            int r0 = 0;
        L4:
            if (r0 >= MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size()) goto L9;
            if (((TLRPC.TL_chatInviteImporter) MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).get(r0)).user_id == r6.user_id) goto L10;
            r0 = r0 + 1;
        L10:
            if (r0 < 0) goto L17;
            MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).remove(r0);
            notifyItemRemoved(r0 + extraFirstHolders());
            if (MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).isEmpty() == false) goto L18;
            notifyItemRemoved(1);
            return;
        L18:
            return;
        L17:
            return;
        L9:
            r0 = -1;
            goto L10
        }

        @SuppressLint({"NotifyDataSetChanged"})
        public void setItems(List<TLRPC.TL_chatInviteImporter> r8) {
            boolean r0 = MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).isEmpty();
            int r1 = 0;
        L4:
            if (r1 >= r8.size()) goto L13;
            long r2 = r8.get(r1).user_id;
            int r4 = r1 + 1;
        L7:
            if (r4 >= r8.size()) goto L12;
            if (r8.get(r4).user_id == r2) goto L10;
            r4 = r4 + 1;
            goto L7
        L10:
            r8.remove(r1);
            r1 = r1 - 1;
        L12:
            r1 = r1 + 1;
            goto L4
        L13:
            MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).clear();
            MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).addAll(r8);
            if (r0 == false) goto L17;
            notifyItemRangeInserted(!MemberRequestsDelegate.m14288$$Nest$fgetisShowLastItemDivider(this.this$0) ? 1 : 0, MemberRequestsDelegate.m14284$$Nest$fgetcurrentImporters(this.this$0).size());
            return;
        L17:
            notifyDataSetChanged();
        }
    }

    public class PreviewDialog extends Dialog {
        private float animationProgress;
        private ValueAnimator animator;
        private BitmapDrawable backgroundDrawable;
        private final TextView bioText;
        private final ViewGroup contentView;
        private BackupImageView imageView;
        private TLRPC.TL_chatInviteImporter importer;
        private final TextView nameText;
        private final AvatarPreviewPagerIndicator pagerIndicator;
        private final Drawable pagerShadowDrawable;
        private final ActionBarPopupWindow.ActionBarPopupWindowLayout popupLayout;
        private final int shadowPaddingLeft;
        private final int shadowPaddingTop;
        final /* synthetic */ MemberRequestsDelegate this$0;
        private final ProfileGalleryView viewPager;

        /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$PreviewDialog$1, reason: invalid class name */
        public class AnonymousClass1 extends AvatarPreviewPagerIndicator {
            final /* synthetic */ PreviewDialog this$1;
            final /* synthetic */ MemberRequestsDelegate val$this$0;

            public AnonymousClass1(PreviewDialog r1, Context r2, MemberRequestsDelegate r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // org.telegram.ui.AvatarPreviewPagerIndicator, android.view.View
            public void onDraw(Canvas r3) {
                if (this.profileGalleryView.getRealCount() <= 1) goto L6;
                super.onDraw(r3);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$PreviewDialog$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ PreviewDialog this$1;
            final /* synthetic */ float val$fromScale;
            final /* synthetic */ boolean val$show;

            public AnonymousClass2(PreviewDialog r1, boolean r2, float r3) {
                this.this$1 = r1;
                this.val$show = r2;
                this.val$fromScale = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                super.onAnimationEnd(r1);
                if (this.val$show == true) goto L6;
                PreviewDialog.access$001(this.this$1);
                return;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator r2) {
                super.onAnimationStart(r2);
                PreviewDialog.m14297$$Nest$fgetcontentView(this.this$1).setVisibility(0);
                if (this.val$show == false) goto L6;
                PreviewDialog.m14297$$Nest$fgetcontentView(this.this$1).setScaleX(this.val$fromScale);
                PreviewDialog.m14297$$Nest$fgetcontentView(this.this$1).setScaleY(this.val$fromScale);
                return;
            }
        }

        /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$PreviewDialog$3, reason: invalid class name */
        public class AnonymousClass3 extends ViewGroup {
            private final Path clipPath;
            private boolean firstSizeChange;
            private final GestureDetector gestureDetector;
            private final RectF rectF;
            final /* synthetic */ PreviewDialog this$1;

            /* renamed from: org.telegram.ui.Delegates.MemberRequestsDelegate$PreviewDialog$3$1, reason: invalid class name */
            public class AnonymousClass1 extends GestureDetector.SimpleOnGestureListener {
                final /* synthetic */ AnonymousClass3 this$2;

                public AnonymousClass1(AnonymousClass3 r1) {
                    this.this$2 = r1;
                }

                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public boolean onDown(MotionEvent r1) {
                    return true;
                }

                @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnGestureListener
                public boolean onSingleTapUp(MotionEvent r4) {
                    if (PreviewDialog.m14300$$Nest$fgetpagerShadowDrawable(this.this$2.this$1).getBounds().contains((int) r4.getX(), (int) r4.getY()) == true) goto L15;
                    if (PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$2.this$1).getLeft() < r4.getX()) goto L7;
                L13:
                    this.this$2.this$1.dismiss();
                    goto L15
                L7:
                    if (r4.getX() >= PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$2.this$1).getRight()) goto L13;
                    if (PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$2.this$1).getTop() >= r4.getY()) goto L13;
                    if (r4.getY() >= PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$2.this$1).getBottom()) goto L13;
                L15:
                    return super.onSingleTapUp(r4);
                }
            }

            public AnonymousClass3(PreviewDialog r2, Context r3) {
                this.this$1 = r2;
                super(r3);
                this.gestureDetector = new GestureDetector(getContext(), new AnonymousClass1(this));
                this.clipPath = new Path();
                this.rectF = new RectF();
                this.firstSizeChange = true;
            }

            @Override // android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r2) {
                r2.save();
                r2.clipPath(this.clipPath);
                super.dispatchDraw(r2);
                r2.restore();
            }

            @Override // android.view.View
            public void onDraw(Canvas r2) {
                PreviewDialog.m14300$$Nest$fgetpagerShadowDrawable(this.this$1).draw(r2);
                super.onDraw(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
                int r62 = (getHeight() - PreviewDialog.m14305$$Nest$mgetContentHeight(this.this$1)) / 2;
                int r82 = (getWidth() - PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getMeasuredWidth()) / 2;
                PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).layout(r82, r62, PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getMeasuredWidth() + r82, PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getMeasuredHeight() + r62);
                PreviewDialog.m14299$$Nest$fgetpagerIndicator(this.this$1).layout(PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getLeft(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop() + PreviewDialog.m14299$$Nest$fgetpagerIndicator(this.this$1).getMeasuredHeight());
                int r63 = r62 + (PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getMeasuredHeight() + AndroidUtilities.dp(12.0f));
                PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).layout(PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getLeft() + AndroidUtilities.dp(16.0f), r63, PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight() - AndroidUtilities.dp(16.0f), PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).getMeasuredHeight() + r63);
                int r64 = r63 + PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).getMeasuredHeight();
                int r1 = 8;
                if (PreviewDialog.m14296$$Nest$fgetbioText(this.this$1).getVisibility() == 8) goto L5;
                int r65 = r64 + AndroidUtilities.dp(4.0f);
                PreviewDialog.m14296$$Nest$fgetbioText(this.this$1).layout(PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).getLeft(), r65, PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).getRight(), PreviewDialog.m14296$$Nest$fgetbioText(this.this$1).getMeasuredHeight() + r65);
                r64 = r65 + PreviewDialog.m14296$$Nest$fgetbioText(this.this$1).getMeasuredHeight();
            L5:
                int r66 = r64 + AndroidUtilities.dp(12.0f);
                PreviewDialog.m14300$$Nest$fgetpagerShadowDrawable(this.this$1).setBounds(PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getLeft() - PreviewDialog.m14302$$Nest$fgetshadowPaddingLeft(this.this$1), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop() - PreviewDialog.m14303$$Nest$fgetshadowPaddingTop(this.this$1), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight() + PreviewDialog.m14302$$Nest$fgetshadowPaddingLeft(this.this$1), PreviewDialog.m14303$$Nest$fgetshadowPaddingTop(this.this$1) + r66);
                PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1).layout((PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight() - PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1).getMeasuredWidth()) + PreviewDialog.m14302$$Nest$fgetshadowPaddingLeft(this.this$1), r66, PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight() + PreviewDialog.m14302$$Nest$fgetshadowPaddingLeft(this.this$1), PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1).getMeasuredHeight() + r66);
                ActionBarPopupWindow.ActionBarPopupWindowLayout r67 = PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1);
                if (PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1).getBottom() >= r10) goto L8;
                r1 = 0;
            L8:
                r67.setVisibility(r1);
                int r68 = AndroidUtilities.dp(6.0f);
                this.rectF.set(PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getLeft(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getRight(), PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop() + (r68 * 2));
                this.clipPath.reset();
                Path r83 = this.clipPath;
                RectF r0 = this.rectF;
                float r12 = r68;
                Path.Direction r2 = Path.Direction.CW;
                r83.addRoundRect(r0, r12, r12, r2);
                this.rectF.set(r7, PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getTop() + r68, r9, r10);
                this.clipPath.addRect(this.rectF, r2);
            }

            @Override // android.view.View
            public void onMeasure(int r6, int r7) {
                setWillNotDraw(false);
                super.onMeasure(r6, r7);
                int r62 = Math.min(Math.min(getMeasuredWidth(), getMeasuredHeight()), (int) (getMeasuredHeight() * 0.66d)) - (AndroidUtilities.dp(12.0f) * 2);
                int r1 = View.MeasureSpec.makeMeasureSpec(r62, Integer.MIN_VALUE);
                PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).measure(r1, r1);
                PreviewDialog.m14299$$Nest$fgetpagerIndicator(this.this$1).measure(r1, r1);
                int r63 = View.MeasureSpec.makeMeasureSpec(r62 - (AndroidUtilities.dp(16.0f) * 2), TLObject.FLAG_30);
                PreviewDialog.m14298$$Nest$fgetnameText(this.this$1).measure(r63, View.MeasureSpec.makeMeasureSpec(0, 0));
                PreviewDialog.m14296$$Nest$fgetbioText(this.this$1).measure(r63, View.MeasureSpec.makeMeasureSpec(0, 0));
                PreviewDialog.m14301$$Nest$fgetpopupLayout(this.this$1).measure(View.MeasureSpec.makeMeasureSpec(PreviewDialog.m14304$$Nest$fgetviewPager(this.this$1).getMeasuredWidth() + (PreviewDialog.m14302$$Nest$fgetshadowPaddingLeft(this.this$1) * 2), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(0, 0));
            }

            @Override // android.view.View
            public void onSizeChanged(int r3, int r4, int r5, int r6) {
                super.onSizeChanged(r3, r4, r5, r6);
                Point r0 = AndroidUtilities.displaySize;
                if (r0.x <= r0.y) goto L5;
                PreviewDialog.access$101(this.this$1);
            L5:
                if (r3 == r5) goto L12;
                if (r4 != r6) goto L8;
                return;
            L8:
                if (this.firstSizeChange == true) goto L10;
                PreviewDialog.m14306$$Nest$mupdateBackgroundBitmap(this.this$1);
            L10:
                this.firstSizeChange = false;
                return;
            }

            @Override // android.view.View
            @SuppressLint({"ClickableViewAccessibility"})
            public boolean onTouchEvent(MotionEvent r1) {
                return this.gestureDetector.onTouchEvent(r1);
            }

            @Override // android.view.View
            public boolean verifyDrawable(Drawable r2) {
                if (r2 != PreviewDialog.m14300$$Nest$fgetpagerShadowDrawable(this.this$1)) goto L5;
                return true;
            L5:
                if (super.verifyDrawable(r2) == true) goto L11;
                return false;
            L11:
                return true;
            }
        }

        /* renamed from: $r8$lambda$9NGPfg4-LuoiINgz4xhqOyAMn58, reason: not valid java name */
        public static /* synthetic */ void m14294$r8$lambda$9NGPfg4LuoiINgz4xhqOyAMn58(PreviewDialog r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$Re7CAM3Ha0Jpd9EmojEVBk7Y8dc(PreviewDialog r0, float r1, float r2, float r3, float r4, int r5, ValueAnimator r6) {
            r0.lambda$runAnimation$4(r1, r2, r3, r4, r5, r6);
        }

        public static /* synthetic */ void $r8$lambda$UCHrQ9qPhoSuTh8dengviu6wgxM(PreviewDialog r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: $r8$lambda$g_XLf2a7U-i5ljoRBhnb9e5eDFs, reason: not valid java name */
        public static /* synthetic */ void m14295$r8$lambda$g_XLf2a7Ui5ljoRBhnb9e5eDFs(PreviewDialog r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$q6bDGU9O0ERpnE2SFnAvMKx8P7U(PreviewDialog r0) {
            r0.lambda$show$3();
        }

        /* renamed from: -$$Nest$fgetbioText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m14296$$Nest$fgetbioText(PreviewDialog r0) {
            return r0.bioText;
        }

        /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ViewGroup m14297$$Nest$fgetcontentView(PreviewDialog r0) {
            return r0.contentView;
        }

        /* renamed from: -$$Nest$fgetnameText, reason: not valid java name */
        public static /* bridge */ /* synthetic */ TextView m14298$$Nest$fgetnameText(PreviewDialog r0) {
            return r0.nameText;
        }

        /* renamed from: -$$Nest$fgetpagerIndicator, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AvatarPreviewPagerIndicator m14299$$Nest$fgetpagerIndicator(PreviewDialog r0) {
            return r0.pagerIndicator;
        }

        /* renamed from: -$$Nest$fgetpagerShadowDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ Drawable m14300$$Nest$fgetpagerShadowDrawable(PreviewDialog r0) {
            return r0.pagerShadowDrawable;
        }

        /* renamed from: -$$Nest$fgetpopupLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ActionBarPopupWindow.ActionBarPopupWindowLayout m14301$$Nest$fgetpopupLayout(PreviewDialog r0) {
            return r0.popupLayout;
        }

        /* renamed from: -$$Nest$fgetshadowPaddingLeft, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14302$$Nest$fgetshadowPaddingLeft(PreviewDialog r0) {
            return r0.shadowPaddingLeft;
        }

        /* renamed from: -$$Nest$fgetshadowPaddingTop, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14303$$Nest$fgetshadowPaddingTop(PreviewDialog r0) {
            return r0.shadowPaddingTop;
        }

        /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ProfileGalleryView m14304$$Nest$fgetviewPager(PreviewDialog r0) {
            return r0.viewPager;
        }

        /* renamed from: -$$Nest$mgetContentHeight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m14305$$Nest$mgetContentHeight(PreviewDialog r0) {
            return r0.getContentHeight();
        }

        /* renamed from: -$$Nest$mupdateBackgroundBitmap, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m14306$$Nest$mupdateBackgroundBitmap(PreviewDialog r0) {
            r0.updateBackgroundBitmap();
        }

        public PreviewDialog(MemberRequestsDelegate r9, Context r10, RecyclerListView r11, Theme.ResourcesProvider r12, boolean r13) {
            this.this$0 = r9;
            super(r10, R.style.TransparentDialog2);
            Drawable r0 = getContext().getResources().getDrawable(R.drawable.popup_fixed_alert2).mutate();
            this.pagerShadowDrawable = r0;
            TextView r1 = new TextView(getContext());
            this.nameText = r1;
            TextView r2 = new TextView(getContext());
            this.bioText = r2;
            AnonymousClass3 r3 = new AnonymousClass3(this, getContext());
            this.contentView = r3;
            setCancelable(true);
            r3.setVisibility(4);
            int r5 = Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, MemberRequestsDelegate.m14285$$Nest$fgetfragment(r9).getResourceProvider());
            r0.setColorFilter(new PorterDuffColorFilter(r5, PorterDuff.Mode.MULTIPLY));
            r0.setCallback(r3);
            Rect r6 = new Rect();
            r0.getPadding(r6);
            this.shadowPaddingTop = r6.top;
            this.shadowPaddingLeft = r6.left;
            ActionBarPopupWindow.ActionBarPopupWindowLayout r02 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(r10, r12);
            this.popupLayout = r02;
            r02.setBackgroundColor(r5);
            r3.addView(r02);
            AnonymousClass1 r52 = new AnonymousClass1(this, getContext(), r9);
            this.pagerIndicator = r52;
            ProfileGalleryView r62 = new ProfileGalleryView(r10, MemberRequestsDelegate.m14285$$Nest$fgetfragment(r9).getActionBar(), r11, r52);
            this.viewPager = r62;
            r62.setCreateThumbFromParent(true);
            r3.addView(r62);
            r52.setProfileGalleryView(r62);
            r3.addView(r52);
            r1.setMaxLines(1);
            r1.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, MemberRequestsDelegate.m14285$$Nest$fgetfragment(r9).getResourceProvider()));
            r1.setTextSize(16.0f);
            r1.setTypeface(AndroidUtilities.bold());
            r3.addView(r1);
            r2.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, MemberRequestsDelegate.m14285$$Nest$fgetfragment(r9).getResourceProvider()));
            r2.setTextSize(14.0f);
            r3.addView(r2);
            ActionBarMenuSubItem r92 = new ActionBarMenuSubItem(r10, true, false);
            int r14 = Theme.key_actionBarDefaultSubmenuItem;
            int r22 = Theme.getColor(r14, r12);
            int r32 = Theme.key_actionBarDefaultSubmenuItemIcon;
            r92.setColors(r22, Theme.getColor(r32, r12));
            int r23 = Theme.key_dialogButtonSelector;
            r92.setSelectorColor(Theme.getColor(r23, r12));
            if (r13 == false) goto L6;
            int r132 = R.string.AddToChannel;
        L5:
            String r133 = LocaleController.getString(r132);
            r92.setTextAndIcon(r133, R.drawable.msg_requests);
            r92.setOnClickListener(new MemberRequestsDelegate$PreviewDialog$$ExternalSyntheticLambda2(this));
            r02.addView(r92);
            ActionBarMenuSubItem r93 = new ActionBarMenuSubItem(r10, false, false);
            r93.setColors(Theme.getColor(r14, r12), Theme.getColor(r32, r12));
            r93.setSelectorColor(Theme.getColor(r23, r12));
            r93.setTextAndIcon(LocaleController.getString(R.string.SendMessage), R.drawable.msg_msgbubble3);
            r93.setOnClickListener(new MemberRequestsDelegate$PreviewDialog$$ExternalSyntheticLambda3(this));
            r02.addView(r93);
            ActionBarMenuSubItem r94 = new ActionBarMenuSubItem(r10, false, true);
            r94.setColors(Theme.getColor(Theme.key_text_RedBold, r12), Theme.getColor(Theme.key_text_RedRegular, r12));
            r94.setSelectorColor(Theme.getColor(r23, r12));
            r94.setTextAndIcon(LocaleController.getString(R.string.DismissRequest), R.drawable.msg_remove);
            r94.setOnClickListener(new MemberRequestsDelegate$PreviewDialog$$ExternalSyntheticLambda4(this));
            r02.addView(r94);
            return;
        L6:
            r132 = R.string.AddToGroup;
            goto L5
        }

        public static /* synthetic */ void access$001(PreviewDialog r0) {
            super.dismiss();
        }

        public static /* synthetic */ void access$101(PreviewDialog r0) {
            super.dismiss();
        }

        private Bitmap getBlurredBitmap() {
            int r0 = (int) (this.contentView.getMeasuredWidth() / 6.0f);
            int r1 = (int) (this.contentView.getMeasuredHeight() / 6.0f);
            Bitmap r2 = Bitmap.createBitmap(r0, r1, Bitmap.Config.ARGB_8888);
            Canvas r3 = new Canvas(r2);
            r3.scale(0.16666667f, 0.16666667f);
            r3.save();
            ((LaunchActivity) MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).getParentActivity()).getActionBarLayout().getView().draw(r3);
            r3.drawColor(ColorUtils.setAlphaComponent(-16777216, 76));
            Dialog r6 = MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).getVisibleDialog();
            if (r6 == null) goto L5;
            r6.getWindow().getDecorView().draw(r3);
        L5:
            Utilities.stackBlurBitmap(r2, Math.max(7, Math.max(r0, r1) / 180));
            return r2;
        }

        private int getContentHeight() {
            int r0 = this.viewPager.getMeasuredHeight() + (AndroidUtilities.dp(12.0f) + this.nameText.getMeasuredHeight());
            if (this.bioText.getVisibility() == 8) goto L6;
            r0 = r0 + (AndroidUtilities.dp(4.0f) + this.bioText.getMeasuredHeight());
        L6:
            return r0 + (AndroidUtilities.dp(12.0f) + this.popupLayout.getMeasuredHeight());
        }

        private int getContentWidth() {
            return this.viewPager.getMeasuredWidth();
        }

        private /* synthetic */ void lambda$new$0(View r2) {
            TLRPC.TL_chatInviteImporter r22 = this.importer;
            if (r22 == null) goto L5;
            this.this$0.onAddClicked(r22);
        L5:
            MemberRequestsDelegate.m14292$$Nest$mhidePreview(this.this$0);
        }

        private /* synthetic */ void lambda$new$1(View r4) {
            if (this.importer == null) goto L6;
            this.this$0.isNeedRestoreList = true;
            super.dismiss();
            MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).dismissCurrentDialog();
            Bundle r42 = new Bundle();
            r42.putLong("user_id", this.importer.user_id);
            ChatActivity r0 = new ChatActivity(r42);
            MemberRequestsDelegate.m14285$$Nest$fgetfragment(this.this$0).presentFragment(r0);
            return;
        }

        private /* synthetic */ void lambda$new$2(View r2) {
            TLRPC.TL_chatInviteImporter r22 = this.importer;
            if (r22 == null) goto L5;
            this.this$0.onDismissClicked(r22);
        L5:
            MemberRequestsDelegate.m14292$$Nest$mhidePreview(this.this$0);
        }

        private /* synthetic */ void lambda$runAnimation$4(float r3, float r4, float r5, float r6, int r7, ValueAnimator r8) {
            float r82 = ((Float) r8.getAnimatedValue()).floatValue();
            this.animationProgress = r82;
            float r32 = r3 + ((1.0f - r3) * r82);
            this.contentView.setScaleX(r32);
            this.contentView.setScaleY(r32);
            this.contentView.setTranslationX(r4 * (1.0f - this.animationProgress));
            this.contentView.setTranslationY(r5 * (1.0f - this.animationProgress));
            int r33 = (int) (r6 * (1.0f - this.animationProgress));
            this.viewPager.setRoundRadius(r33, r33);
            float r34 = MathUtils.clamp((this.animationProgress * 2.0f) - 1.0f, 0.0f, 1.0f);
            this.pagerShadowDrawable.setAlpha((int) (r34 * 255.0f));
            this.nameText.setAlpha(r34);
            this.bioText.setAlpha(r34);
            this.popupLayout.setTranslationY(r7 * (1.0f - this.animationProgress));
            this.popupLayout.setAlpha(r34);
            BitmapDrawable r42 = this.backgroundDrawable;
            if (r42 == null) goto L5;
            r42.setAlpha((int) (this.animationProgress * 255.0f));
        L5:
            this.pagerIndicator.setAlpha(r34);
        }

        private /* synthetic */ void lambda$show$3() {
            updateBackgroundBitmap();
            runAnimation(true);
        }

        private void runAnimation(boolean r13) {
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L5;
            r0.cancel();
        L5:
            int[] r1 = new int[2];
            this.imageView.getLocationOnScreen(r1);
            float r3 = 1.0f;
            float r7 = (this.imageView.getWidth() * 1.0f) / getContentWidth();
            float r10 = (this.imageView.getWidth() / 2.0f) / r7;
            float r9 = 1.0f - r7;
            float r8 = r1[0] - (this.viewPager.getLeft() + ((int) ((getContentWidth() * r9) / 2.0f)));
            float r92 = r1[1] - (this.viewPager.getTop() + ((int) ((getContentHeight() * r9) / 2.0f)));
            int r11 = (-this.popupLayout.getTop()) / 2;
            if (r13 == false) goto L8;
            float r4 = 0.0f;
        L9:
            if (r13 == true) goto L12;
            r3 = 0.0f;
        L12:
            ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r4, r3});
            this.animator = r02;
            r02.addUpdateListener(new MemberRequestsDelegate$PreviewDialog$$ExternalSyntheticLambda0(this, r7, r8, r92, r10, r11));
            this.animator.addListener(new AnonymousClass2(this, r13, r7));
            this.animator.setDuration(220);
            this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.animator.start();
            return;
        L8:
            r4 = 1.0f;
            goto L9
        }

        private void updateBackgroundBitmap() {
            BitmapDrawable r0 = this.backgroundDrawable;
            if (r0 == null) goto L5;
            int r02 = r0.getAlpha();
        L6:
            BitmapDrawable r1 = new BitmapDrawable(getContext().getResources(), getBlurredBitmap());
            this.backgroundDrawable = r1;
            r1.setAlpha(r02);
            getWindow().setBackgroundDrawable(this.backgroundDrawable);
            return;
        L5:
            r02 = 255;
            goto L6
        }

        @Override // android.app.Dialog, android.content.DialogInterface
        public void dismiss() {
            runAnimation(false);
        }

        @Override // android.app.Dialog
        public void onCreate(Bundle r3) {
            super.onCreate(r3);
            getWindow().setWindowAnimations(R.style.DialogNoAnimation);
            setContentView(this.contentView, new ViewGroup.LayoutParams(-1, -1));
            WindowManager.LayoutParams r32 = getWindow().getAttributes();
            r32.width = -1;
            r32.height = -1;
            r32.dimAmount = 0.0f;
            int r0 = r32.flags & (-3);
            r32.gravity = 51;
            r32.flags = r0 | (-2147417856);
            if (Build.VERSION.SDK_INT < 28) goto L5;
            AndroidUtilities$$ExternalSyntheticApiModelOutline1.m(r32, 1);
        L5:
            getWindow().setAttributes(r32);
        }

        public void setImporter(TLRPC.TL_chatInviteImporter r9, BackupImageView r10) {
            this.importer = r9;
            this.imageView = r10;
            TLRPC.User r0 = MessagesController.getInstance(MemberRequestsDelegate.m14283$$Nest$fgetcurrentAccount(this.this$0)).getUser(Long.valueOf(r9.user_id));
            int r2 = 0;
            ImageLocation r1 = ImageLocation.getForUserOrChat(MemberRequestsDelegate.m14283$$Nest$fgetcurrentAccount(this.this$0), r0, 0);
            ImageLocation r3 = ImageLocation.getForUserOrChat(MemberRequestsDelegate.m14283$$Nest$fgetcurrentAccount(this.this$0), r0, 1);
            if (MessagesController.getInstance(MemberRequestsDelegate.m14283$$Nest$fgetcurrentAccount(this.this$0)).getUserFull(r9.user_id) != null) goto L5;
            MessagesController.getInstance(MemberRequestsDelegate.m14283$$Nest$fgetcurrentAccount(this.this$0)).loadUserInfo(r0, false, 0);
        L5:
            this.viewPager.setParentAvatarImage(r10);
            this.viewPager.setData(r9.user_id, true);
            this.viewPager.initIfEmpty(null, r1, r3, true);
            TLRPC.User r102 = (TLRPC.User) MemberRequestsDelegate.m14291$$Nest$fgetusers(this.this$0).get(r9.user_id);
            this.nameText.setText(UserObject.getUserName(r102));
            this.bioText.setText(r9.about);
            TextView r103 = this.bioText;
            if (TextUtils.isEmpty(r9.about) == false) goto L8;
            r2 = 8;
        L8:
            r103.setVisibility(r2);
            this.contentView.requestLayout();
        }

        @Override // android.app.Dialog
        public void show() {
            super.show();
            AndroidUtilities.runOnUIThread(new MemberRequestsDelegate$PreviewDialog$$ExternalSyntheticLambda1(this), 80);
        }
    }

    /* renamed from: $r8$lambda$2n2gxPHM92617CJ0tNI1VG-3qaE, reason: not valid java name */
    public static /* synthetic */ void m14278$r8$lambda$2n2gxPHM92617CJ0tNI1VG3qaE(MemberRequestsDelegate r0, TLRPC.TL_error r1, TLObject r2, TLRPC.TL_chatInviteImporter r3, boolean r4, TLRPC.User r5, TLRPC.TL_messages_hideChatJoinRequest r6) {
        r0.lambda$hideChatJoinRequest$6(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$IzZlgwyo0MRKrX3sUOIj3tBJLtY(MemberRequestsDelegate r0) {
        r0.lambda$new$8();
    }

    public static /* synthetic */ void $r8$lambda$O3o5_ZtgV9aaSeucdYx3bj6hsgQ(MemberRequestsDelegate r0, MemberRequestCell r1) {
        r0.lambda$onItemClick$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$XyPKEEDoC2QymE_V1FxRdrGLwEs(MemberRequestsDelegate r0) {
        r0.lambda$loadMembers$2();
    }

    /* renamed from: $r8$lambda$YQMbo-ypHp8FnQrEfyCf5lJtOVw, reason: not valid java name */
    public static /* synthetic */ void m14279$r8$lambda$YQMboypHp8FnQrEfyCf5lJtOVw(MemberRequestsDelegate r0, DialogInterface r1) {
        r0.lambda$onItemClick$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jLtR16Tn8TR5SfjwaRx600hpJfQ(MemberRequestsDelegate r0, boolean r1) {
        r0.lambda$loadMembers$5(r1);
    }

    /* renamed from: $r8$lambda$mM1Xi-svfpzBZKpnOJFlBuyd67o, reason: not valid java name */
    public static /* synthetic */ void m14280$r8$lambda$mM1XisvfpzBZKpnOJFlBuyd67o(MemberRequestsDelegate r0, TLRPC.TL_chatInviteImporter r1, boolean r2, TLRPC.User r3, TLRPC.TL_messages_hideChatJoinRequest r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$hideChatJoinRequest$7(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: $r8$lambda$rPmRSvZhQFl9S9R68a-2YHIFxzU, reason: not valid java name */
    public static /* synthetic */ void m14281$r8$lambda$rPmRSvZhQFl9S9R68a2YHIFxzU(MemberRequestsDelegate r0, boolean r1, Runnable r2, String r3, TLRPC.TL_error r4, TLObject r5, boolean r6) {
        r0.lambda$loadMembers$3(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$x300BY5jOAleWfV_yPyG9IVmGog(MemberRequestsDelegate r0, boolean r1, Runnable r2, String r3, boolean r4, TLObject r5, TLRPC.TL_error r6) {
        r0.lambda$loadMembers$4(r1, r2, r3, r4, r5, r6);
    }

    /* renamed from: -$$Nest$fgetadapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Adapter m14282$$Nest$fgetadapter(MemberRequestsDelegate r0) {
        return r0.adapter;
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14283$$Nest$fgetcurrentAccount(MemberRequestsDelegate r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetcurrentImporters, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m14284$$Nest$fgetcurrentImporters(MemberRequestsDelegate r0) {
        return r0.currentImporters;
    }

    /* renamed from: -$$Nest$fgetfragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m14285$$Nest$fgetfragment(MemberRequestsDelegate r0) {
        return r0.fragment;
    }

    /* renamed from: -$$Nest$fgethasMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14286$$Nest$fgethasMore(MemberRequestsDelegate r0) {
        return r0.hasMore;
    }

    /* renamed from: -$$Nest$fgetisLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14287$$Nest$fgetisLoading(MemberRequestsDelegate r0) {
        return r0.isLoading;
    }

    /* renamed from: -$$Nest$fgetisShowLastItemDivider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14288$$Nest$fgetisShowLastItemDivider(MemberRequestsDelegate r0) {
        return r0.isShowLastItemDivider;
    }

    /* renamed from: -$$Nest$fgetlistScrollListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerView.OnScrollListener m14289$$Nest$fgetlistScrollListener(MemberRequestsDelegate r0) {
        return r0.listScrollListener;
    }

    /* renamed from: -$$Nest$fgetloadMembersRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m14290$$Nest$fgetloadMembersRunnable(MemberRequestsDelegate r0) {
        return r0.loadMembersRunnable;
    }

    /* renamed from: -$$Nest$fgetusers, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m14291$$Nest$fgetusers(MemberRequestsDelegate r0) {
        return r0.users;
    }

    /* renamed from: -$$Nest$mhidePreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14292$$Nest$mhidePreview(MemberRequestsDelegate r0) {
        r0.hidePreview();
    }

    public MemberRequestsDelegate(BaseFragment r3, FrameLayout r4, long r5, boolean r7) {
        this.currentImporters = new ArrayList();
        this.users = new LongSparseArray();
        this.allImporters = new ArrayList();
        this.adapter = new Adapter(this, null);
        this.hasMore = true;
        this.isFirstLoading = true;
        this.isShowLastItemDivider = true;
        this.loadMembersRunnable = new MemberRequestsDelegate$$ExternalSyntheticLambda1(this);
        this.listScrollListener = new AnonymousClass2(this);
        this.fragment = r3;
        this.layoutContainer = r4;
        this.chatId = r5;
        int r32 = r3.getCurrentAccount();
        this.currentAccount = r32;
        this.isChannel = ChatObject.isChannelAndNotMegaGroup(r5, r32);
        this.showSearchMenu = r7;
        this.controller = MemberRequestsController.getInstance(r32);
    }

    private void hideChatJoinRequest(TLRPC.TL_chatInviteImporter r8, boolean r9) {
        TLRPC.User r5 = this.users.get(r8.user_id);
        if (r5 != null) goto L5;
        return;
    L5:
        TLRPC.TL_messages_hideChatJoinRequest r6 = new TLRPC.TL_messages_hideChatJoinRequest();
        r6.approved = r9;
        r6.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(-this.chatId);
        r6.user_id = MessagesController.getInstance(this.currentAccount).getInputUser(r5);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r6, new MemberRequestsDelegate$$ExternalSyntheticLambda9(this, r8, r9, r5, r6));
    }

    private void hidePreview() {
        this.previewDialog.dismiss();
        this.importer = null;
    }

    private /* synthetic */ void lambda$hideChatJoinRequest$6(TLRPC.TL_error r6, TLObject r7, TLRPC.TL_chatInviteImporter r8, boolean r9, TLRPC.User r10, TLRPC.TL_messages_hideChatJoinRequest r11) {
        BaseFragment r0 = this.fragment;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getParentActivity() == null) goto L45;
        int r02 = 0;
        if (r6 != null) goto L38;
        TLRPC.TL_updates r72 = (TLRPC.TL_updates) r7;
        if (r72.chats.isEmpty() == true) goto L12;
        MessagesController.getInstance(this.currentAccount).loadFullChat(r72.chats.get(0).id, 0, true);
    L12:
        int r62 = 0;
    L14:
        if (r62 >= this.allImporters.size()) goto L19;
        if (this.allImporters.get(r62).user_id == r8.user_id) goto L17;
        r62 = r62 + 1;
        goto L14
    L17:
        this.allImporters.remove(r62);
    L19:
        this.adapter.removeItem(r8);
        onImportersChanged(this.query, false, true);
        if (r9 == false) goto L29;
        Bulletin.MultiLineLayout r63 = new Bulletin.MultiLineLayout(this.fragment.getParentActivity(), this.fragment.getResourceProvider());
        r63.imageView.setRoundRadius(ExteraConfig.getAvatarCorners(32.0f));
        r63.imageView.setForUserOrChat(r10, new AvatarDrawable(r10));
        String r73 = UserObject.getFirstName(r10);
        if (this.isChannel == false) goto L24;
        String r82 = LocaleController.formatString("HasBeenAddedToChannel", R.string.HasBeenAddedToChannel, new Object[]{r73});
    L25:
        SpannableStringBuilder r92 = new SpannableStringBuilder(r82);
        int r83 = r82.indexOf(r73);
        r92.setSpan(new TypefaceSpan(AndroidUtilities.bold()), r83, r73.length() + r83, 18);
        r63.textView.setText(r92);
        if (this.allImporters.isEmpty() == false) goto L28;
        Bulletin.make(this.fragment, r63, 2750).show();
        goto L29
    L28:
        Bulletin.make(this.layoutContainer, r63, 2750).show();
        goto L29
    L24:
        r82 = LocaleController.formatString("HasBeenAddedToGroup", R.string.HasBeenAddedToGroup, new Object[]{r73});
    L29:
        ActionBarMenu r64 = this.fragment.getActionBar().createMenu();
        if (TextUtils.isEmpty(this.query) == true) goto L32;
        return;
    L32:
        if (this.showSearchMenu == false) goto L44;
        ActionBarMenuItem r65 = r64.getItem(0);
        if (this.allImporters.isEmpty() == false) goto L36;
        r02 = 8;
    L36:
        r65.setVisibility(r02);
        return;
    L44:
        return;
    L38:
        AlertsCreator.processError(this.currentAccount, r6, this.fragment, r11, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$hideChatJoinRequest$7(TLRPC.TL_chatInviteImporter r10, boolean r11, TLRPC.User r12, TLRPC.TL_messages_hideChatJoinRequest r13, TLObject r14, TLRPC.TL_error r15) {
        if (r15 != null) goto L4;
        MessagesController r1 = MessagesController.getInstance(this.currentAccount);
        r1.processUpdates((TLRPC.TL_updates) r14, false);
    L4:
        AndroidUtilities.runOnUIThread(new MemberRequestsDelegate$$ExternalSyntheticLambda10(this, r15, r14, r10, r11, r12, r13));
    }

    private /* synthetic */ void lambda$loadMembers$2() {
        setViewVisible(this.loadingView, true, true);
    }

    private /* synthetic */ void lambda$loadMembers$3(boolean r3, Runnable r4, String r5, TLRPC.TL_error r6, TLObject r7, boolean r8) {
        this.isLoading = false;
        this.isDataLoaded = true;
        if (r3 == false) goto L5;
        AndroidUtilities.cancelRunOnUIThread(r4);
    L5:
        setViewVisible(this.loadingView, false, false);
        if (TextUtils.equals(r5, this.query) == false) goto L12;
        if (r6 != null) goto L11;
        this.isDataLoaded = true;
        onImportersLoaded((TLRPC.TL_messages_chatInviteImporters) r7, r5, r8, false);
        return;
    L11:
        return;
    }

    private /* synthetic */ void lambda$loadMembers$4(boolean r9, Runnable r10, String r11, boolean r12, TLObject r13, TLRPC.TL_error r14) {
        AndroidUtilities.runOnUIThread(new MemberRequestsDelegate$$ExternalSyntheticLambda8(this, r9, r10, r11, r14, r13, r12));
    }

    private /* synthetic */ void lambda$loadMembers$5(boolean r13) {
        boolean r3 = TextUtils.isEmpty(this.query);
        String r5 = this.query;
        this.isLoading = true;
        this.isFirstLoading = false;
        MemberRequestsDelegate$$ExternalSyntheticLambda3 r2 = null;
        if (r3 == true) goto L5;
    L7:
        TLRPC.TL_chatInviteImporter r8 = null;
    L8:
        if (r8 != null) goto L10;
        boolean r6 = true;
    L11:
        if (r3 == false) goto L15;
        if (r6 == false) goto L15;
        if (r13 == false) goto L15;
        r2 = new MemberRequestsDelegate$$ExternalSyntheticLambda3(this);
    L15:
        MemberRequestsDelegate$$ExternalSyntheticLambda3 r4 = r2;
        if (r3 == false) goto L18;
        AndroidUtilities.runOnUIThread(r4, 300);
    L18:
        this.searchRequestId = this.controller.getImporters(this.chatId, r5, r8, this.users, new MemberRequestsDelegate$$ExternalSyntheticLambda4(this, r3, r4, r5, r6));
        return;
    L10:
        r6 = false;
        goto L11
    L5:
        if (this.currentImporters.isEmpty() == true) goto L7;
        List<TLRPC.TL_chatInviteImporter> r42 = this.currentImporters;
        r8 = r42.get(r42.size() - 1);
        goto L8
    }

    private /* synthetic */ void lambda$new$8() {
        loadMembers();
    }

    private /* synthetic */ void lambda$onItemClick$0(DialogInterface r1) {
        this.previewDialog = null;
    }

    private /* synthetic */ void lambda$onItemClick$1(MemberRequestCell r8) {
        TLRPC.TL_chatInviteImporter r0 = r8.getImporter();
        this.importer = r0;
        TLRPC.User r02 = this.users.get(r0.user_id);
        if (r02 == null) goto L20;
        this.fragment.getMessagesController().putUser(r02, false);
        Point r1 = AndroidUtilities.displaySize;
        if (r1.x <= r1.y) goto L8;
        boolean r12 = true;
    L10:
        if (r02.photo == null) goto L17;
        if (r12 == true) goto L17;
        if (this.previewDialog != null) goto L19;
        PreviewDialog r13 = new PreviewDialog(this, this.fragment.getParentActivity(), (RecyclerListView) r8.getParent(), this.fragment.getResourceProvider(), this.isChannel);
        this.previewDialog = r13;
        r13.setImporter(this.importer, r8.getAvatarImageView());
        this.previewDialog.setOnDismissListener(new MemberRequestsDelegate$$ExternalSyntheticLambda7(this));
        this.previewDialog.show();
        return;
    L19:
        return;
    L17:
        this.isNeedRestoreList = true;
        this.fragment.dismissCurrentDialog();
        Bundle r82 = new Bundle();
        ProfileActivity r14 = new ProfileActivity(r82);
        r82.putLong("user_id", r02.id);
        r82.putBoolean("removeFragmentOnChatOpen", false);
        this.fragment.presentFragment(r14);
        return;
    L8:
        r12 = false;
        goto L10
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void onImportersLoaded(TLRPC.TL_messages_chatInviteImporters r9, String r10, boolean r11, boolean r12) {
        boolean r1 = false;
        if (this.currentImporters.isEmpty() == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        int r3 = 0;
    L10:
        if (r3 >= r9.users.size()) goto L12;
        TLRPC.User r4 = r9.users.get(r3);
        this.users.put(r4.id, r4);
        r3 = r3 + 1;
        goto L10
    L12:
        if (r11 == false) goto L15;
        this.adapter.setItems(r9.importers);
    L26:
        if (TextUtils.isEmpty(r10) == false) goto L36;
        if (r11 == false) goto L29;
        this.allImporters.clear();
    L29:
        this.allImporters.addAll(r9.importers);
        if (this.showSearchMenu == false) goto L36;
        ActionBarMenuItem r112 = this.fragment.getActionBar().createMenu().getItem(0);
        if (this.allImporters.isEmpty() == false) goto L34;
        int r32 = 8;
    L35:
        r112.setVisibility(r32);
        goto L36
    L34:
        r32 = 0;
    L36:
        onImportersChanged(r10, r12, false);
        if (this.currentImporters.size() >= r9.count) goto L39;
        boolean r92 = true;
    L40:
        this.hasMore = r92;
        if (this.currentImporters.isEmpty() == false) goto L43;
    L45:
        if (r0 == r1) goto L53;
        boolean r93 = this.hasMore;
        Adapter r8 = this.adapter;
        if (r93 == false) goto L50;
        r8.notifyItemInserted(r8.getItemCount() - 1);
        return;
    L50:
        r8.notifyItemRemoved(r8.getItemCount());
        return;
    L53:
        return;
    L43:
        if (this.hasMore == false) goto L45;
        r1 = true;
        goto L45
    L39:
        r92 = false;
        goto L40
    L15:
        if (r9.importers.size() > 0) goto L17;
    L19:
        Object[] r33 = false;
    L20:
        if (r33 == false) goto L22;
        this.adapter.notifyItemRemoved((!this.isShowLastItemDivider ? 1 : 0) + this.currentImporters.size());
    L22:
        this.adapter.appendItems(r9.importers);
        if (r33 == false) goto L26;
        this.adapter.notifyItemInserted((!this.isShowLastItemDivider ? 1 : 0) + this.currentImporters.size());
        goto L26
    L17:
        if ((this.currentImporters.size() + r9.importers.size()) >= r9.count) goto L19;
        r33 = true;
        goto L20
    L5:
        if (this.hasMore == false) goto L7;
        r0 = true;
        goto L8
    }

    private void setViewVisible(View r4, boolean r5, boolean r6) {
        if (r4 == null) goto L26;
        int r0 = 0;
        if (r4.getVisibility() != 0) goto L7;
        boolean r3 = true;
    L9:
        if (r5 == false) goto L11;
        float r2 = 1.0f;
    L12:
        if (r5 == r3) goto L14;
    L16:
        if (r6 == false) goto L21;
        if (r5 == false) goto L19;
        r4.setAlpha(0.0f);
    L19:
        r4.setVisibility(0);
        r4.animate().alpha(r2).setDuration(150).start();
        return;
    L21:
        if (r5 == true) goto L24;
        r0 = 4;
    L24:
        r4.setVisibility(r0);
        return;
    L14:
        if (r2 != r4.getAlpha()) goto L16;
        return;
    L11:
        r2 = 0.0f;
        goto L12
    L7:
        r3 = false;
        goto L9
    }

    public Adapter getAdapter() {
        return this.adapter;
    }

    public StickerEmptyView getEmptyView() {
        if (this.emptyView != null) goto L16;
        StickerEmptyView r0 = new StickerEmptyView(this.fragment.getParentActivity(), null, 16, this.fragment.getResourceProvider());
        this.emptyView = r0;
        SpoilersTextView r02 = r0.title;
        if (this.isChannel == false) goto L8;
        int r1 = R.string.NoSubscribeRequests;
    L9:
        r02.setText(LocaleController.getString(r1));
        LinkSpanDrawable.LinksTextView r03 = this.emptyView.subtitle;
        if (this.isChannel == false) goto L13;
        int r12 = R.string.NoSubscribeRequestsDescription;
    L14:
        r03.setText(LocaleController.getString(r12));
        this.emptyView.setAnimateLayoutChange(true);
        this.emptyView.setVisibility(8);
        goto L16
    L13:
        r12 = R.string.NoMemberRequestsDescription;
        goto L14
    L8:
        r1 = R.string.NoMemberRequests;
    L16:
        return this.emptyView;
    }

    public FlickerLoadingView getLoadingView() {
        if (this.loadingView != null) goto L9;
        FlickerLoadingView r0 = new FlickerLoadingView(this.fragment.getParentActivity(), this.fragment.getResourceProvider());
        this.loadingView = r0;
        r0.setAlpha(0.0f);
        if (this.isShowLastItemDivider == false) goto L7;
        this.loadingView.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.fragment.getResourceProvider()));
    L7:
        this.loadingView.setColors(Theme.key_windowBackgroundWhite, Theme.key_windowBackgroundGray, -1);
        this.loadingView.setViewType(15);
        this.loadingView.setMemberRequestButton(this.isChannel);
    L9:
        return this.loadingView;
    }

    public RecyclerListView getRecyclerView() {
        return this.recyclerView;
    }

    public FrameLayout getRootLayout() {
        if (this.rootLayout != null) goto L6;
        FrameLayout r0 = new FrameLayout(this.fragment.getParentActivity());
        this.rootLayout = r0;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.fragment.getResourceProvider()));
        FlickerLoadingView r02 = getLoadingView();
        this.loadingView = r02;
        this.rootLayout.addView(r02, -1, -1);
        StickerEmptyView r03 = getSearchEmptyView();
        this.searchEmptyView = r03;
        this.rootLayout.addView(r03, -1, -1);
        StickerEmptyView r04 = getEmptyView();
        this.emptyView = r04;
        this.rootLayout.addView(r04, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayoutManager r05 = new LinearLayoutManager(this.fragment.getParentActivity());
        RecyclerListView r1 = new RecyclerListView(this.fragment.getParentActivity());
        this.recyclerView = r1;
        r1.setAdapter(this.adapter);
        this.recyclerView.setSections();
        this.recyclerView.setLayoutManager(r05);
        this.recyclerView.setOnItemClickListener(new MemberRequestsDelegate$$ExternalSyntheticLambda2(this));
        this.recyclerView.setOnScrollListener(this.listScrollListener);
        this.recyclerView.setSelectorDrawableColor(Theme.getColor(Theme.key_listSelector, this.fragment.getResourceProvider()));
        this.rootLayout.addView(this.recyclerView, -1, -1);
        DefaultItemAnimator r06 = new DefaultItemAnimator();
        r06.setDurations(350);
        r06.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        r06.setDelayAnimations(false);
        r06.setSupportsChangeAnimations(false);
        this.recyclerView.setItemAnimator(r06);
    L6:
        return this.rootLayout;
    }

    public StickerEmptyView getSearchEmptyView() {
        if (this.searchEmptyView != null) goto L9;
        StickerEmptyView r0 = new StickerEmptyView(this.fragment.getParentActivity(), null, 1, this.fragment.getResourceProvider());
        this.searchEmptyView = r0;
        if (this.isShowLastItemDivider == false) goto L7;
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite, this.fragment.getResourceProvider()));
    L7:
        this.searchEmptyView.title.setText(LocaleController.getString(R.string.NoResult));
        this.searchEmptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        this.searchEmptyView.setAnimateLayoutChange(true);
        this.searchEmptyView.setVisibility(8);
    L9:
        return this.searchEmptyView;
    }

    public boolean hasAllImporters() {
        return !this.allImporters.isEmpty();
    }

    public void loadMembers() {
        boolean r1 = true;
        if (this.isFirstLoading == false) goto L7;
        TLRPC.TL_messages_chatInviteImporters r0 = this.controller.getCachedImporters(this.chatId);
        if (r0 == null) goto L7;
        this.isDataLoaded = true;
        onImportersLoaded(r0, null, true, true);
        r1 = false;
    L7:
        AndroidUtilities.runOnUIThread(new MemberRequestsDelegate$$ExternalSyntheticLambda0(this, r1));
    }

    @Override // org.telegram.ui.Cells.MemberRequestCell.OnClickListener
    public void onAddClicked(TLRPC.TL_chatInviteImporter r2) {
        hideChatJoinRequest(r2, true);
    }

    public boolean onBackPressed(boolean r1) {
        PreviewDialog r0 = this.previewDialog;
        if (r0 == null) goto L8;
        if (r1 == false) goto L10;
        r0.dismiss();
        return false;
    L10:
        return false;
    L8:
        return true;
    }

    @Override // org.telegram.ui.Cells.MemberRequestCell.OnClickListener
    public void onDismissClicked(TLRPC.TL_chatInviteImporter r2) {
        hideChatJoinRequest(r2, false);
    }

    public void onImportersChanged(String r4, boolean r5, boolean r6) {
        if (TextUtils.isEmpty(r4) == false) goto L20;
        if (this.allImporters.isEmpty() == false) goto L9;
        if (r5 == true) goto L9;
        boolean r42 = false;
    L10:
        StickerEmptyView r52 = this.emptyView;
        if (r52 == null) goto L16;
        if (r42 == false) goto L14;
        int r2 = 4;
    L15:
        r52.setVisibility(r2);
        goto L16
    L14:
        r2 = 0;
    L16:
        StickerEmptyView r53 = this.searchEmptyView;
        if (r53 == null) goto L34;
        r53.setVisibility(4);
    L34:
        setViewVisible(this.recyclerView, r42, true);
        if (this.allImporters.isEmpty() == false) goto L48;
        StickerEmptyView r43 = this.emptyView;
        if (r43 == null) goto L39;
        r43.setVisibility(0);
    L39:
        StickerEmptyView r44 = this.searchEmptyView;
        if (r44 == null) goto L42;
        r44.setVisibility(4);
    L42:
        setViewVisible(this.loadingView, false, false);
        if (this.isSearchExpanded == true) goto L45;
        return;
    L45:
        if (this.showSearchMenu == false) goto L50;
        this.fragment.getActionBar().createMenu().closeSearchField(true);
        return;
    L50:
        return;
    L48:
        return;
    L9:
        r42 = true;
        goto L10
    L20:
        if (this.currentImporters.isEmpty() == false) goto L24;
        if (r5 == true) goto L24;
        r42 = false;
    L25:
        StickerEmptyView r54 = this.emptyView;
        if (r54 == null) goto L28;
        r54.setVisibility(4);
    L28:
        StickerEmptyView r55 = this.searchEmptyView;
        if (r55 == null) goto L34;
        if (r42 == false) goto L32;
        int r22 = 4;
    L33:
        r55.setVisibility(r22);
        goto L34
    L32:
        r22 = 0;
    L24:
        r42 = true;
        goto L25
    }

    public void onItemClick(View r1, int r2) {
        if ((r1 instanceof MemberRequestCell) == true) goto L5;
        return;
    L5:
        if (this.isSearchExpanded == false) goto L7;
        AndroidUtilities.hideKeyboard(this.fragment.getParentActivity().getCurrentFocus());
    L7:
        MemberRequestsDelegate$$ExternalSyntheticLambda5 r22 = new MemberRequestsDelegate$$ExternalSyntheticLambda5(this, (MemberRequestCell) r1);
        if (this.isSearchExpanded == false) goto L10;
        long r0 = 100;
    L11:
        AndroidUtilities.runOnUIThread(r22, r0);
        return;
    L10:
        r0 = 0;
        goto L11
    }

    public void setAdapterItemsEnabled(boolean r3) {
        if (this.recyclerView == null) goto L10;
        int r0 = Adapter.m14293$$Nest$mextraFirstHolders(this.adapter);
        if (r0 >= 0) goto L7;
        return;
    L7:
        if (r0 >= this.recyclerView.getChildCount()) goto L12;
        this.recyclerView.getChildAt(r0).setEnabled(r3);
        return;
    L12:
        return;
    }

    public void setQuery(String r7) {
        if (this.searchRunnable == null) goto L5;
        Utilities.searchQueue.cancelRunnable(this.searchRunnable);
        this.searchRunnable = null;
    L5:
        int r1 = 0;
        if (this.searchRequestId == 0) goto L8;
        ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.searchRequestId, false);
        this.searchRequestId = 0;
    L8:
        this.query = r7;
        if (this.isDataLoaded == true) goto L11;
    L14:
        boolean r0 = TextUtils.isEmpty(r7);
        Adapter r2 = this.adapter;
        if (r0 == false) goto L26;
        r2.setItems(this.allImporters);
        setViewVisible(this.recyclerView, true, true);
        setViewVisible(this.loadingView, false, false);
        StickerEmptyView r02 = this.searchEmptyView;
        if (r02 == null) goto L19;
        r02.setVisibility(4);
    L19:
        if (r7 == null) goto L21;
    L27:
        if (r7 == null) goto L35;
        StickerEmptyView r72 = this.emptyView;
        if (r72 == null) goto L31;
        r72.setVisibility(4);
    L31:
        StickerEmptyView r6 = this.searchEmptyView;
        if (r6 == null) goto L36;
        r6.setVisibility(4);
        return;
    L36:
        return;
    L35:
        return;
    L21:
        if (this.showSearchMenu == false) goto L27;
        ActionBarMenuItem r03 = this.fragment.getActionBar().createMenu().getItem(0);
        if (this.allImporters.isEmpty() == false) goto L25;
        r1 = 8;
    L25:
        r03.setVisibility(r1);
        goto L27
    L26:
        r2.setItems(Collections.EMPTY_LIST);
        setViewVisible(this.recyclerView, false, false);
        setViewVisible(this.loadingView, true, true);
        DispatchQueue r04 = Utilities.searchQueue;
        MemberRequestsDelegate$$ExternalSyntheticLambda6 r12 = new MemberRequestsDelegate$$ExternalSyntheticLambda6(this);
        this.searchRunnable = r12;
        r04.postRunnable(r12, 300);
        goto L27
    L11:
        if (this.allImporters.isEmpty() == false) goto L14;
        setViewVisible(this.loadingView, false, false);
    }

    public void setRecyclerView(RecyclerListView r3) {
        this.recyclerView = r3;
        r3.setOnItemClickListener(new MemberRequestsDelegate$$ExternalSyntheticLambda2(this));
        RecyclerView.OnScrollListener r0 = r3.getOnScrollListener();
        if (r0 != null) goto L6;
        r3.setOnScrollListener(this.listScrollListener);
        return;
    L6:
        r3.setOnScrollListener(new AnonymousClass1(this, r0));
    }

    public void setSearchExpanded(boolean r1) {
        this.isSearchExpanded = r1;
    }

    public void setShowLastItemDivider(boolean r1) {
        this.isShowLastItemDivider = r1;
    }
}
