package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AnimationNotificationsLocker;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Business.QuickRepliesController;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.GraySectionCell;
import org.telegram.ui.Cells.LoadingCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Cells.SharedLinkCell;
import org.telegram.ui.Cells.SharedMediaSectionCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmbedBottomSheet;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SearchViewPager;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.PhotoViewer;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes3.dex */
public class FilteredSearchView extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target {
    private static SpannableStringBuilder[] arrowSpan;
    RecyclerView.Adapter adapter;
    private final BoolAnimator animatorFloatingDataVisible;
    BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableFactory;
    private SearchViewPager.ChatPreviewDelegate chatPreviewDelegate;
    Runnable clearCurrentResultsRunnable;
    private int columnsCount;
    private String currentDataQuery;
    boolean currentIncludeFolder;
    long currentSearchCommunityId;
    long currentSearchDialogId;
    FiltersView.MediaFilterData currentSearchFilter;
    long currentSearchMaxDate;
    long currentSearchMinDate;
    String currentSearchString;
    private Delegate delegate;
    private OnlyUserFiltersAdapter dialogsAdapter;
    StickerEmptyView emptyView;
    private boolean endReached;
    private boolean firstLoading;
    private final FloatingDateView floatingDateView;
    private final Runnable hideFloatingDateRunnable;
    boolean ignoreRequestLayout;
    private boolean isLoading;
    int lastAccount;
    String lastMessagesSearchString;
    String lastSearchFilterQueryString;
    public final LinearLayoutManager layoutManager;
    private final FlickerLoadingView loadingView;
    boolean localTipArchive;
    ArrayList<Object> localTipChats;
    ArrayList<FiltersView.DateData> localTipDates;
    private final MessageHashId messageHashIdTmp;
    public ArrayList<MessageObject> messages;
    public SparseArray<MessageObject> messagesById;
    private int nextSearchRate;
    private AnimationNotificationsLocker notificationsLocker;
    Activity parentActivity;
    BaseFragment parentFragment;
    private int photoViewerClassGuid;
    private PhotoViewer.PhotoViewerProvider provider;
    public final RecyclerListView recyclerListView;
    private int requestIndex;
    Runnable searchRunnable;
    public HashMap<String, ArrayList<MessageObject>> sectionArrays;
    public ArrayList<String> sections;
    private SharedDocumentsAdapter sharedAudioAdapter;
    private SharedDocumentsAdapter sharedDocumentsAdapter;
    private SharedLinksAdapter sharedLinksAdapter;
    private SharedPhotoVideoAdapter sharedPhotoVideoAdapter;
    private SharedDocumentsAdapter sharedVoiceAdapter;
    private int totalCount;
    private UiCallback uiCallback;
    private boolean useFromUserAsAvatar;

    /* renamed from: org.telegram.ui.FilteredSearchView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ FilteredSearchView this$0;

        public AnonymousClass1(FilteredSearchView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == false) goto L8;
            this.this$0.messages.clear();
            this.this$0.sections.clear();
            this.this$0.sectionArrays.clear();
            RecyclerView.Adapter r1 = this.this$0.adapter;
            if (r1 == null) goto L9;
            r1.notifyDataSetChanged();
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$2, reason: invalid class name */
    public class AnonymousClass2 extends PhotoViewer.EmptyPhotoViewerProvider {
        final /* synthetic */ FilteredSearchView this$0;

        public AnonymousClass2(FilteredSearchView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public PhotoViewer.PlaceProviderObject getPlaceForPhoto(MessageObject r8, TLRPC.FileLocation r9, int r10, boolean r11, boolean r12) {
            if (r8 != null) goto L5;
            return null;
        L5:
            RecyclerListView r7 = this.this$0.recyclerListView;
            int r102 = r7.getChildCount();
            int r122 = 0;
        L6:
            if (r122 >= r102) goto L52;
            View r0 = r7.getChildAt(r122);
            int[] r1 = new int[2];
            if ((r0 instanceof SharedPhotoVideoCell) == false) goto L20;
            SharedPhotoVideoCell r2 = (SharedPhotoVideoCell) r0;
            ImageReceiver r4 = null;
            int r3 = 0;
        L11:
            if (r3 >= 6) goto L32;
            MessageObject r5 = r2.getMessageObject(r3);
            if (r5 == null) goto L32;
            if (r5.getId() != r8.getId()) goto L18;
            BackupImageView r42 = r2.getImageView(r3);
            ImageReceiver r52 = r42.getImageReceiver();
            r42.getLocationInWindow(r1);
            r4 = r52;
        L18:
            r3 = r3 + 1;
        L32:
            if (r4 != null) goto L33;
            r122 = r122 + 1;
            goto L6
        L33:
            PhotoViewer.PlaceProviderObject r92 = new PhotoViewer.PlaceProviderObject();
            r92.viewX = r1[0];
            r92.viewY = r1[1];
            r92.parentView = r7;
            r7.getLocationInWindow(r1);
            r92.animatingImageViewYOffset = -r1[1];
            r92.imageReceiver = r4;
            r92.allowTakeAnimation = false;
            r92.radius = r4.getRoundRadius(true);
            r92.thumb = r92.imageReceiver.getBitmapSafe();
            r92.parentView.getLocationInWindow(r1);
            r92.clipTopAddition = 0;
            if (PhotoViewer.isShowingImage(r8) == false) goto L50;
            View r82 = r7.getPinnedHeader();
            if (r82 == null) goto L50;
            if ((r0 instanceof SharedDocumentCell) == false) goto L40;
            int r103 = AndroidUtilities.dp(8.0f);
        L41:
            int r104 = r103 - r92.viewY;
            if (r104 <= r0.getHeight()) goto L45;
            r7.scrollBy(0, -(r104 + r82.getHeight()));
            return r92;
        L45:
            int r83 = r92.viewY - r7.getHeight();
            if ((r0 instanceof SharedDocumentCell) == false) goto L48;
            r83 = r83 - AndroidUtilities.dp(8.0f);
        L48:
            if (r83 < 0) goto L50;
            r7.scrollBy(0, r83 + r0.getHeight());
            goto L50
        L40:
            r103 = 0;
        L50:
            return r92;
        L20:
            if ((r0 instanceof SharedDocumentCell) == false) goto L25;
            SharedDocumentCell r22 = (SharedDocumentCell) r0;
            if (r22.getMessage().getId() != r8.getId()) goto L31;
            BackupImageView r23 = r22.getImageView();
            ImageReceiver r32 = r23.getImageReceiver();
            r23.getLocationInWindow(r1);
            r4 = r32;
        L31:
            r4 = null;
            goto L32
        L25:
            if ((r0 instanceof ContextLinkCell) == false) goto L31;
            ContextLinkCell r24 = (ContextLinkCell) r0;
            MessageObject r33 = (MessageObject) r24.getParentObject();
            if (r33 == null) goto L31;
            if (r33.getId() != r8.getId()) goto L31;
            r4 = r24.getPhotoImage();
            r24.getLocationInWindow(r1);
            goto L32
        L52:
            return null;
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public CharSequence getSubtitleFor(int r2) {
            return LocaleController.formatDateAudio(this.this$0.messages.get(r2).messageOwner.date, false);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public CharSequence getTitleFor(int r1) {
            return FilteredSearchView.createFromInfoString(this.this$0.messages.get(r1), 0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public int getTotalImageCount() {
            return FilteredSearchView.m14830$$Nest$fgettotalCount(this.this$0);
        }

        @Override // org.telegram.ui.PhotoViewer.EmptyPhotoViewerProvider, org.telegram.ui.PhotoViewer.PhotoViewerProvider
        public boolean loadMore() {
            if (FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) == true) goto L7;
            FilteredSearchView r1 = this.this$0;
            r1.search(r1.currentSearchDialogId, r1.currentSearchCommunityId, r1.currentSearchMinDate, r1.currentSearchMaxDate, r1.currentSearchFilter, r1.currentIncludeFolder, r1.lastMessagesSearchString, false);
            return true;
        L7:
            return true;
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerListView {
        final /* synthetic */ FilteredSearchView this$0;

        public AnonymousClass3(FilteredSearchView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            if (getAdapter() != FilteredSearchView.m14829$$Nest$fgetsharedPhotoVideoAdapter(this.this$0)) goto L11;
            int r0 = 0;
        L6:
            if (r0 >= getChildCount()) goto L11;
            if (getChildViewHolder(getChildAt(r0)).getItemViewType() != 1) goto L10;
            r5.save();
            r5.translate(getChildAt(r0).getX(), (getChildAt(r0).getY() - getChildAt(r0).getMeasuredHeight()) + AndroidUtilities.dp(2.0f));
            getChildAt(r0).draw(r5);
            r5.restore();
            invalidate();
        L10:
            r0 = r0 + 1;
        L11:
            super.dispatchDraw(r5);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r3, View r4, long r5) {
            if (getAdapter() != FilteredSearchView.m14829$$Nest$fgetsharedPhotoVideoAdapter(this.this$0)) goto L8;
            if (getChildViewHolder(r4).getItemViewType() != 1) goto L8;
            return true;
        L8:
            return super.drawChild(r3, r4, r5);
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$4, reason: invalid class name */
    public class AnonymousClass4 implements RecyclerListView.OnItemLongClickListenerExtended {
        final /* synthetic */ FilteredSearchView this$0;

        public AnonymousClass4(FilteredSearchView r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public boolean onItemClick(View r3, int r4, float r5, float r6) {
            if ((r3 instanceof SharedDocumentCell) == false) goto L6;
            FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$0, ((SharedDocumentCell) r3).getMessage(), r3, 0);
        L23:
            return true;
        L6:
            if ((r3 instanceof SharedLinkCell) == false) goto L9;
            FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$0, ((SharedLinkCell) r3).getMessage(), r3, 0);
            goto L23
        L9:
            if ((r3 instanceof SharedAudioCell) == false) goto L12;
            FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$0, ((SharedAudioCell) r3).getMessage(), r3, 0);
            goto L23
        L12:
            if ((r3 instanceof ContextLinkCell) == false) goto L15;
            FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$0, ((ContextLinkCell) r3).getMessageObject(), r3, 0);
            goto L23
        L15:
            if ((r3 instanceof DialogCell) == false) goto L23;
            if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$0).actionModeShowing() == true) goto L22;
            DialogCell r42 = (DialogCell) r3;
            if (r42.isPointInsideAvatar(r5, r6) == false) goto L22;
            FilteredSearchView.m14820$$Nest$fgetchatPreviewDelegate(this.this$0).startChatPreview(this.this$0.recyclerListView, r42);
            return true;
        L22:
            FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$0, ((DialogCell) r3).getMessage(), r3, 0);
            goto L23
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onLongClickRelease() {
            FilteredSearchView.m14820$$Nest$fgetchatPreviewDelegate(this.this$0).finish();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListenerExtended
        public void onMove(float r1, float r2) {
            FilteredSearchView.m14820$$Nest$fgetchatPreviewDelegate(this.this$0).move(r2);
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$5, reason: invalid class name */
    public class AnonymousClass5 extends FlickerLoadingView {
        final /* synthetic */ FilteredSearchView this$0;

        public AnonymousClass5(FilteredSearchView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.FlickerLoadingView
        public int getColumnsCount() {
            return FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$6, reason: invalid class name */
    public class AnonymousClass6 extends RecyclerView.OnScrollListener {
        final /* synthetic */ FilteredSearchView this$0;

        /* renamed from: $r8$lambda$Bgbi-RIBVHd3SUgcj4dGE42Axvg, reason: not valid java name */
        public static /* synthetic */ void m14839$r8$lambda$BgbiRIBVHd3SUgcj4dGE42Axvg(AnonymousClass6 r0) {
            r0.lambda$onScrolled$0();
        }

        public AnonymousClass6(FilteredSearchView r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onScrolled$0() {
            FilteredSearchView r0 = this.this$0;
            r0.search(r0.currentSearchDialogId, r0.currentSearchCommunityId, r0.currentSearchMinDate, r0.currentSearchMaxDate, r0.currentSearchFilter, r0.currentIncludeFolder, r0.lastMessagesSearchString, false);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.this$0.parentActivity.getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r5, int r6, int r7) {
            if (r5.getAdapter() == null) goto L45;
            FilteredSearchView r62 = this.this$0;
            if (r62.adapter == null) goto L51;
            int r63 = r62.layoutManager.findFirstVisibleItemPosition();
            int r0 = this.this$0.layoutManager.findLastVisibleItemPosition();
            int r1 = Math.abs(r0 - r63) + 1;
            int r2 = r5.getAdapter().getItemCount();
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == true) goto L15;
            if (r1 <= 0) goto L15;
            if (r0 < (r2 - 10)) goto L15;
            if (FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) == true) goto L15;
            AndroidUtilities.runOnUIThread(new FilteredSearchView$6$$ExternalSyntheticLambda0(this));
        L15:
            FilteredSearchView r02 = this.this$0;
            if (r02.adapter != FilteredSearchView.m14829$$Nest$fgetsharedPhotoVideoAdapter(r02)) goto L33;
            if (r7 != 0) goto L19;
        L23:
            RecyclerView.ViewHolder r52 = r5.findViewHolderForAdapterPosition(r63);
            if (r52 != null) goto L26;
            return;
        L26:
            if (r52.getItemViewType() != 0) goto L47;
            View r53 = r52.itemView;
            if ((r53 instanceof SharedPhotoVideoCell) == false) goto L48;
            MessageObject r54 = ((SharedPhotoVideoCell) r53).getMessageObject(0);
            if (r54 == null) goto L49;
            FilteredSearchView.m14824$$Nest$fgetfloatingDateView(this.this$0).setCustomDate(r54.messageOwner.date);
            return;
        L49:
            return;
        L48:
            return;
        L47:
            return;
        L19:
            if (this.this$0.messages.isEmpty() == true) goto L23;
            if (TextUtils.isEmpty(FilteredSearchView.m14822$$Nest$fgetcurrentDataQuery(this.this$0)) == false) goto L23;
            FilteredSearchView.m14838$$Nest$mshowFloatingDateView(this.this$0);
            goto L23
        L33:
            View r55 = this.this$0.recyclerListView.getPinnedHeader();
            if ((r55 instanceof GraySectionCell) == false) goto L43;
            GraySectionCell r56 = (GraySectionCell) r55;
            CharSequence r64 = r56.getText();
            if (TextUtils.isEmpty(r64) == true) goto L43;
            if (r56.getAlpha() <= 0.0f) goto L43;
            FilteredSearchView.m14824$$Nest$fgetfloatingDateView(this.this$0).setCustomText(r64.toString());
            if (r7 == 0) goto L50;
            FilteredSearchView.m14838$$Nest$mshowFloatingDateView(this.this$0);
            return;
        L50:
            return;
        L43:
            FilteredSearchView.m14833$$Nest$mhideFloatingDateView(this.this$0);
            return;
        L51:
            return;
        }
    }

    /* renamed from: org.telegram.ui.FilteredSearchView$7, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass7 implements ViewTreeObserver.OnPreDrawListener {
        final /* synthetic */ FilteredSearchView this$0;
        final /* synthetic */ View val$finalProgressView;
        final /* synthetic */ int val$finalProgressViewPosition;

        /* renamed from: org.telegram.ui.FilteredSearchView$7$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass7 this$1;

            public AnonymousClass1(AnonymousClass7 r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                FilteredSearchView.m14828$$Nest$fgetnotificationsLocker(this.this$1.this$0).unlock();
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$7$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass7 this$1;
            final /* synthetic */ RecyclerView.LayoutManager val$layoutManager;

            public AnonymousClass2(AnonymousClass7 r1, RecyclerView.LayoutManager r2) {
                this.this$1 = r1;
                this.val$layoutManager = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                this.this$1.val$finalProgressView.setAlpha(1.0f);
                this.val$layoutManager.stopIgnoringView(this.this$1.val$finalProgressView);
                AnonymousClass7 r1 = this.this$1;
                r1.this$0.recyclerListView.removeView(r1.val$finalProgressView);
            }
        }

        public AnonymousClass7(FilteredSearchView r1, View r2, int r3) {
            this.this$0 = r1;
            this.val$finalProgressView = r2;
            this.val$finalProgressViewPosition = r3;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            this.this$0.getViewTreeObserver().removeOnPreDrawListener(this);
            int r0 = this.this$0.recyclerListView.getChildCount();
            AnimatorSet r1 = new AnimatorSet();
            int r3 = 0;
        L4:
            if (r3 >= r0) goto L12;
            View r7 = this.this$0.recyclerListView.getChildAt(r3);
            if (this.val$finalProgressView != null) goto L8;
        L10:
            r7.setAlpha(0.0f);
            ObjectAnimator r4 = ObjectAnimator.ofFloat(r7, View.ALPHA, new float[]{0.0f, 1.0f});
            r4.setStartDelay((int) ((Math.min(this.this$0.recyclerListView.getMeasuredHeight(), Math.max(0, r7.getTop())) / this.this$0.recyclerListView.getMeasuredHeight()) * 100.0f));
            r4.setDuration(200);
            r1.playTogether(new Animator[]{r4});
        L11:
            r3 = r3 + 1;
            goto L4
        L8:
            if (this.this$0.recyclerListView.getChildAdapterPosition(r7) >= this.val$finalProgressViewPosition) goto L10;
        L12:
            r1.addListener(new AnonymousClass1(this));
            FilteredSearchView.m14828$$Nest$fgetnotificationsLocker(this.this$0).lock();
            r1.start();
            View r02 = this.val$finalProgressView;
            if (r02 != null) goto L15;
        L19:
            return true;
        L15:
            if (r02.getParent() != null) goto L19;
            this.this$0.recyclerListView.addView(this.val$finalProgressView);
            RecyclerView.LayoutManager r03 = this.this$0.recyclerListView.getLayoutManager();
            if (r03 == null) goto L19;
            r03.ignoreView(this.val$finalProgressView);
            View r12 = this.val$finalProgressView;
            ObjectAnimator r13 = ObjectAnimator.ofFloat(r12, View.ALPHA, new float[]{r12.getAlpha(), 0.0f});
            r13.addListener(new AnonymousClass2(this, r03));
            r13.start();
            goto L19
        }
    }

    public interface Delegate {
        void updateFiltersView(boolean r1, ArrayList<Object> r2, ArrayList<FiltersView.DateData> r3, boolean r4);
    }

    public static class FloatingDateView extends View {
        private BlurredBackgroundDrawable bgDrawable;
        private final AnimatedTextView.AnimatedTextDrawable mDrawable;
        private String text;

        public FloatingDateView(Context r3) {
            super(r3);
            AnimatedTextView.AnimatedTextDrawable r32 = new AnimatedTextView.AnimatedTextDrawable(true, false, false);
            this.mDrawable = r32;
            r32.setTextColor(-1);
            r32.setGravity(17);
            r32.setTypeface(AndroidUtilities.bold());
            r32.setTextSize(AndroidUtilities.dp(14.0f));
            r32.setCallback(this);
        }

        @Override // android.view.View
        public void onDraw(Canvas r6) {
            super.onDraw(r6);
            int r0 = (int) (this.mDrawable.getCurrentWidth() + AndroidUtilities.dpf2(30.0f));
            int r1 = (getWidth() - r0) / 2;
            int r02 = r0 + r1;
            BlurredBackgroundDrawable r2 = this.bgDrawable;
            if (r2 == null) goto L5;
            r2.setBounds(r1, 0, r02, getHeight());
            this.bgDrawable.draw(r6);
        L5:
            this.mDrawable.draw(r6);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            this.mDrawable.setBounds(0, 0, r1, r2);
        }

        public void setBlurredBackgroundDrawable(BlurredBackgroundDrawable r2) {
            this.bgDrawable = r2;
            r2.setRadius(AndroidUtilities.dp(11.5f));
            this.bgDrawable.setPadding(AndroidUtilities.dp(5.0f));
        }

        public void setCustomDate(int r3) {
            setCustomText(LocaleController.formatDateChat(r3));
        }

        public void setCustomText(String r2) {
            if (TextUtils.equals(this.text, r2) == true) goto L6;
            this.text = r2;
            this.mDrawable.setText(r2, true);
            return;
        }

        public void updateColors() {
            BlurredBackgroundDrawable r0 = this.bgDrawable;
            if (r0 == null) goto L6;
            r0.updateColors();
            return;
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (super.verifyDrawable(r2) == false) goto L5;
            return true;
        L5:
            if (r2 == this.mDrawable) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static class MessageHashId {
        public long dialogId;
        public int messageId;

        public MessageHashId(int r1, long r2) {
            this.dialogId = r2;
            this.messageId = r1;
        }

        public boolean equals(Object r7) {
            if (this != r7) goto L6;
            return true;
        L6:
            if (r7 != null) goto L8;
        L15:
            return false;
        L8:
            if (getClass() != r7.getClass()) goto L15;
            MessageHashId r72 = (MessageHashId) r7;
            if (this.dialogId != r72.dialogId) goto L15;
            if (this.messageId != r72.messageId) goto L15;
            return true;
        }

        public int hashCode() {
            return this.messageId;
        }

        public void set(int r1, long r2) {
            this.dialogId = r2;
            this.messageId = r1;
        }
    }

    public class OnlyUserFiltersAdapter extends RecyclerListView.SelectionAdapter {
        final /* synthetic */ FilteredSearchView this$0;

        /* renamed from: org.telegram.ui.FilteredSearchView$OnlyUserFiltersAdapter$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends DialogCell {
            final /* synthetic */ OnlyUserFiltersAdapter this$1;

            public AnonymousClass1(OnlyUserFiltersAdapter r1, DialogsActivity r2, Context r3, boolean r4, boolean r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // org.telegram.ui.Cells.DialogCell
            public boolean isForumCell() {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$OnlyUserFiltersAdapter$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ OnlyUserFiltersAdapter this$1;
            final /* synthetic */ boolean val$animated;
            final /* synthetic */ DialogCell val$cell;
            final /* synthetic */ MessageObject val$messageObject;

            public AnonymousClass2(OnlyUserFiltersAdapter r1, DialogCell r2, MessageObject r3, boolean r4) {
                this.this$1 = r1;
                this.val$cell = r2;
                this.val$messageObject = r3;
                this.val$animated = r4;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$cell.getViewTreeObserver().removeOnPreDrawListener(this);
                if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing() == false) goto L5;
                FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0).set(this.val$messageObject.getId(), this.val$messageObject.getDialogId());
                this.val$cell.setChecked(FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0)), this.val$animated);
                return true;
            L5:
                this.val$cell.setChecked(false, this.val$animated);
                return true;
            }
        }

        public OnlyUserFiltersAdapter(FilteredSearchView r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.this$0.messages.isEmpty() == false) goto L7;
            return 0;
        L7:
            return this.this$0.messages.size() + (!FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            if (r1 < this.this$0.messages.size()) goto L6;
            return 3;
        L6:
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return true;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
            if (r8.getItemViewType() != 0) goto L16;
            DialogCell r0 = (DialogCell) r8.itemView;
            MessageObject r3 = this.this$0.messages.get(r9);
            r0.useFromUserAsAvatar = FilteredSearchView.m14832$$Nest$fgetuseFromUserAsAvatar(this.this$0);
            r0.setDialog(r3.getDialogId(), r3, r3.messageOwner.date, false, false);
            boolean r1 = true;
            if (r9 == (getItemCount() - 1)) goto L7;
            boolean r82 = true;
        L8:
            r0.useSeparator = r82;
            if (r0.getMessage() != null) goto L11;
        L13:
            r1 = false;
        L14:
            r0.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass2(this, r0, r3, r1));
            return;
        L11:
            if (r0.getMessage().getId() != r3.getId()) goto L13;
        L7:
            r82 = false;
            goto L8
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r7, int r8) {
            if (r8 != 0) goto L4;
            AnonymousClass1 r6 = new AnonymousClass1(this, null, r7.getContext(), true, true);
        L8:
            r6.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r6);
        L4:
            if (r8 == 3) goto L6;
            GraySectionCell r62 = new GraySectionCell(r7.getContext());
            r62.setText(LocaleController.getString(R.string.SearchMessages));
            r6 = r62;
            goto L8
        L6:
            FlickerLoadingView r63 = new FlickerLoadingView(r7.getContext());
            r63.setIsSingleCell(true);
            r63.setViewType(1);
            r6 = r63;
            goto L8
        }
    }

    public class SharedDocumentsAdapter extends RecyclerListView.SectionsAdapter {
        private int currentType;
        private Context mContext;
        final /* synthetic */ FilteredSearchView this$0;

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedDocumentsAdapter$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 extends SharedAudioCell {
            final /* synthetic */ SharedDocumentsAdapter this$1;

            public AnonymousClass1(SharedDocumentsAdapter r1, Context r2, int r3, Theme.ResourcesProvider r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedAudioCell
            public boolean needPlayMessage(MessageObject r12) {
                if (r12.isVoice() == true) goto L12;
                if (r12.isRoundVideo() == true) goto L12;
                if (r12.isMusic() == false) goto L11;
                String r3 = FilteredSearchView.m14822$$Nest$fgetcurrentDataQuery(this.this$1.this$0);
                FilteredSearchView r0 = this.this$1.this$0;
                long r4 = r0.currentSearchDialogId;
                long r6 = r0.currentSearchMinDate;
                MediaController.PlaylistGlobalSearchParams r2 = new MediaController.PlaylistGlobalSearchParams(r3, r4, r6, r6, r0.currentSearchFilter);
                r2.endReached = FilteredSearchView.m14823$$Nest$fgetendReached(this.this$1.this$0);
                r2.nextSearchRate = FilteredSearchView.m14827$$Nest$fgetnextSearchRate(this.this$1.this$0);
                r2.totalCount = FilteredSearchView.m14830$$Nest$fgettotalCount(this.this$1.this$0);
                r2.folderId = this.this$1.this$0.currentIncludeFolder ? 1 : 0;
                return MediaController.getInstance().setPlaylist(this.this$1.this$0.messages, r12, 0, r2);
            L11:
                return false;
            L12:
                boolean r122 = MediaController.getInstance().playMessage(r12);
                MediaController r02 = MediaController.getInstance();
                if (r122 == false) goto L15;
                ArrayList<MessageObject> r11 = this.this$1.this$0.messages;
            L16:
                r02.setVoiceMessagesPlaylist(r11, false);
                return r122;
            L15:
                r11 = null;
                goto L16
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedDocumentsAdapter$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ SharedDocumentsAdapter this$1;
            final /* synthetic */ boolean val$animated;
            final /* synthetic */ MessageObject val$messageObject;
            final /* synthetic */ SharedDocumentCell val$sharedDocumentCell;

            public AnonymousClass2(SharedDocumentsAdapter r1, SharedDocumentCell r2, MessageObject r3, boolean r4) {
                this.this$1 = r1;
                this.val$sharedDocumentCell = r2;
                this.val$messageObject = r3;
                this.val$animated = r4;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$sharedDocumentCell.getViewTreeObserver().removeOnPreDrawListener(this);
                if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing() == false) goto L5;
                FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0).set(this.val$messageObject.getId(), this.val$messageObject.getDialogId());
                this.val$sharedDocumentCell.setChecked(FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0)), this.val$animated);
                return true;
            L5:
                this.val$sharedDocumentCell.setChecked(false, this.val$animated);
                return true;
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedDocumentsAdapter$3, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass3 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ SharedDocumentsAdapter this$1;
            final /* synthetic */ boolean val$animated;
            final /* synthetic */ MessageObject val$messageObject;
            final /* synthetic */ SharedAudioCell val$sharedAudioCell;

            public AnonymousClass3(SharedDocumentsAdapter r1, SharedAudioCell r2, MessageObject r3, boolean r4) {
                this.this$1 = r1;
                this.val$sharedAudioCell = r2;
                this.val$messageObject = r3;
                this.val$animated = r4;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$sharedAudioCell.getViewTreeObserver().removeOnPreDrawListener(this);
                if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing() == false) goto L5;
                FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0).set(this.val$messageObject.getId(), this.val$messageObject.getDialogId());
                this.val$sharedAudioCell.setChecked(FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0)), this.val$animated);
                return true;
            L5:
                this.val$sharedAudioCell.setChecked(false, this.val$animated);
                return true;
            }
        }

        public SharedDocumentsAdapter(FilteredSearchView r1, Context r2, int r3) {
            this.this$0 = r1;
            this.mContext = r2;
            this.currentType = r3;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            int r1 = 1;
            if (r3 >= this.this$0.sections.size()) goto L9;
            FilteredSearchView r2 = this.this$0;
            int r22 = r2.sectionArrays.get(r2.sections.get(r3)).size();
            if (r3 != 0) goto L8;
            r1 = 0;
        L8:
            return r22 + r1;
        L9:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r1, int r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r3, int r4) {
            if (r3 >= this.this$0.sections.size()) goto L17;
            if (r3 == 0) goto L8;
            if (r4 != 0) goto L8;
            return 0;
        L8:
            int r2 = this.currentType;
            if (r2 != 2) goto L11;
            return 3;
        L11:
            if (r2 == 4) goto L18;
            return 1;
        L18:
            return 3;
        L17:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = 0;
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            int r1 = 0;
            if (this.this$0.sections.isEmpty() == false) goto L5;
            return 0;
        L5:
            int r0 = this.this$0.sections.size();
            if (this.this$0.sections.isEmpty() == true) goto L12;
            if (FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) == true) goto L12;
            r1 = 1;
        L12:
            return r0 + r1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r3, View r4) {
            if (r4 != null) goto L4;
            r4 = new GraySectionCell(this.mContext);
            r4.setBackgroundColor(Theme.getColor(Theme.key_graySection) & (-218103809));
        L4:
            if (r3 != 0) goto L8;
            r4.setAlpha(0.0f);
            return r4;
        L8:
            if (r3 >= this.this$0.sections.size()) goto L10;
            r4.setAlpha(1.0f);
            String r32 = this.this$0.sections.get(r3);
            ((GraySectionCell) r4).setText(LocaleController.formatSectionDate(this.this$0.sectionArrays.get(r32).get(0).messageOwner.date));
        L10:
            return r4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3) {
            if (r2 == 0) goto L7;
            if (r3 != 0) goto L9;
            return false;
        L9:
            return true;
        L7:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r7, int r8, RecyclerView.ViewHolder r9) {
            if (r9.getItemViewType() == 2) goto L47;
            ArrayList<MessageObject> r0 = this.this$0.sectionArrays.get(this.this$0.sections.get(r7));
            int r1 = r9.getItemViewType();
            boolean r2 = false;
            if (r1 != 0) goto L7;
            ((GraySectionCell) r9.itemView).setText(LocaleController.formatSectionDate(r0.get(0).messageOwner.date));
            return;
        L7:
            if (r1 != 1) goto L9;
            if (r7 == 0) goto L30;
            r8 = r8 - 1;
        L30:
            SharedDocumentCell r92 = (SharedDocumentCell) r9.itemView;
            MessageObject r12 = r0.get(r8);
            if (r92.getMessage() != null) goto L33;
        L35:
            boolean r4 = false;
        L37:
            if (r8 == (r0.size() - 1)) goto L39;
        L42:
            r2 = true;
        L43:
            r92.setDocument(r12, r2);
            r92.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass2(this, r92, r12, r4));
            return;
        L39:
            if (r7 != (this.this$0.sections.size() - 1)) goto L43;
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == false) goto L43;
        L33:
            if (r92.getMessage().getId() != r12.getId()) goto L35;
            r4 = true;
            goto L37
        L9:
            if (r1 != 3) goto L48;
            if (r7 == 0) goto L13;
            r8 = r8 - 1;
        L13:
            SharedAudioCell r93 = (SharedAudioCell) r9.itemView;
            MessageObject r13 = r0.get(r8);
            if (r93.getMessage() != null) goto L16;
        L18:
            boolean r42 = false;
        L20:
            if (r8 == (r0.size() - 1)) goto L22;
        L25:
            r2 = true;
        L26:
            r93.setMessageObject(r13, r2);
            r93.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass3(this, r93, r13, r42));
            return;
        L22:
            if (r7 != (this.this$0.sections.size() - 1)) goto L26;
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == false) goto L26;
        L16:
            if (r93.getMessage().getId() != r13.getId()) goto L18;
            r42 = true;
            goto L20
        L48:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L4;
            View r32 = new GraySectionCell(this.mContext);
        L17:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L4:
            if (r4 == 1) goto L15;
            if (r4 == 2) goto L7;
            r32 = new AnonymousClass1(this, this.mContext, 1, null);
            goto L17
        L7:
            FlickerLoadingView r42 = new FlickerLoadingView(this.mContext);
            int r2 = this.currentType;
            if (r2 == 2) goto L12;
            if (r2 == 4) goto L12;
            r42.setViewType(3);
        L13:
            r42.setIsSingleCell(true);
            SharedDocumentCell r43 = r42;
        L14:
            r32 = r43;
        L12:
            r42.setViewType(4);
            goto L13
        L15:
            r43 = new SharedDocumentCell(this.mContext, 2);
            goto L14
        }
    }

    public class SharedLinksAdapter extends RecyclerListView.SectionsAdapter {
        private Context mContext;
        private final SharedLinkCell.SharedLinkCellDelegate sharedLinkCellDelegate;
        final /* synthetic */ FilteredSearchView this$0;

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedLinksAdapter$1, reason: invalid class name */
        public class AnonymousClass1 implements SharedLinkCell.SharedLinkCellDelegate {
            final /* synthetic */ SharedLinksAdapter this$1;

            public static /* synthetic */ void $r8$lambda$wRNDP7pE5mqPmr2Rh8MU6ZnTSm4(AnonymousClass1 r0, String r1, DialogInterface r2, int r3) {
                r0.lambda$onLinkPress$0(r1, r2, r3);
            }

            public AnonymousClass1(SharedLinksAdapter r1) {
                this.this$1 = r1;
            }

            private /* synthetic */ void lambda$onLinkPress$0(String r1, DialogInterface r2, int r3) {
                if (r3 != 0) goto L6;
                FilteredSearchView.m14836$$Nest$mopenUrl(this.this$1.this$0, r1);
                return;
            L6:
                if (r3 == 1) goto L8;
                return;
            L8:
                if (r1.startsWith("mailto:") == false) goto L11;
                r1 = r1.substring(7);
            L13:
                AndroidUtilities.addToClipboard(r1);
                return;
            L11:
                if (r1.startsWith("tel:") == false) goto L13;
                r1 = r1.substring(4);
                goto L13
            }

            @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
            public boolean canPerformActions() {
                return !FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing();
            }

            @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
            public void needOpenWebView(TLRPC.WebPage r1, MessageObject r2) {
                FilteredSearchView.m14837$$Nest$mopenWebView(this.this$1.this$0, r1, r2);
            }

            @Override // org.telegram.ui.Cells.SharedLinkCell.SharedLinkCellDelegate
            public void onLinkPress(String r5, boolean r6) {
                if (r6 == false) goto L5;
                BottomSheet.Builder r62 = new BottomSheet.Builder(this.this$1.this$0.parentActivity);
                r62.setTitle(r5);
                r62.setItems(new CharSequence[]{LocaleController.getString(R.string.Open), LocaleController.getString(R.string.Copy)}, new FilteredSearchView$SharedLinksAdapter$1$$ExternalSyntheticLambda0(this, r5));
                this.this$1.this$0.parentFragment.showDialog(r62.create());
                return;
            L5:
                FilteredSearchView.m14836$$Nest$mopenUrl(this.this$1.this$0, r5);
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedLinksAdapter$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 implements ViewTreeObserver.OnPreDrawListener {
            final /* synthetic */ SharedLinksAdapter this$1;
            final /* synthetic */ boolean val$animated;
            final /* synthetic */ MessageObject val$messageObject;
            final /* synthetic */ SharedLinkCell val$sharedLinkCell;

            public AnonymousClass2(SharedLinksAdapter r1, SharedLinkCell r2, MessageObject r3, boolean r4) {
                this.this$1 = r1;
                this.val$sharedLinkCell = r2;
                this.val$messageObject = r3;
                this.val$animated = r4;
            }

            @Override // android.view.ViewTreeObserver.OnPreDrawListener
            public boolean onPreDraw() {
                this.val$sharedLinkCell.getViewTreeObserver().removeOnPreDrawListener(this);
                if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing() == false) goto L5;
                FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0).set(this.val$messageObject.getId(), this.val$messageObject.getDialogId());
                this.val$sharedLinkCell.setChecked(FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$1.this$0)), this.val$animated);
                return true;
            L5:
                this.val$sharedLinkCell.setChecked(false, this.val$animated);
                return true;
            }
        }

        public SharedLinksAdapter(FilteredSearchView r1, Context r2) {
            this.this$0 = r1;
            this.sharedLinkCellDelegate = new AnonymousClass1(this);
            this.mContext = r2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getCountForSection(int r3) {
            int r1 = 1;
            if (r3 >= this.this$0.sections.size()) goto L9;
            FilteredSearchView r2 = this.this$0;
            int r22 = r2.sectionArrays.get(r2.sections.get(r3)).size();
            if (r3 != 0) goto L8;
            r1 = 0;
        L8:
            return r22 + r1;
        L9:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public Object getItem(int r1, int r2) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getItemViewType(int r1, int r2) {
            if (r1 >= this.this$0.sections.size()) goto L10;
            if (r1 == 0) goto L8;
            if (r2 != 0) goto L12;
            return 0;
        L12:
            return 1;
        L8:
            return 1;
        L10:
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public String getLetter(int r1) {
            return null;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.FastScrollAdapter
        public void getPositionForScrollProgress(RecyclerListView r1, float r2, int[] r3) {
            r3[0] = 0;
            r3[1] = 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public int getSectionCount() {
            int r1 = 0;
            if (this.this$0.messages.isEmpty() == false) goto L6;
            return 0;
        L6:
            if (this.this$0.sections.isEmpty() == true) goto L8;
        L10:
            int r0 = this.this$0.sections.size();
            if (this.this$0.sections.isEmpty() == true) goto L17;
            if (FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) == true) goto L17;
            r1 = 1;
        L17:
            return r0 + r1;
        L8:
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == false) goto L10;
            return 0;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public View getSectionHeaderView(int r3, View r4) {
            if (r4 != null) goto L4;
            r4 = new GraySectionCell(this.mContext);
            r4.setBackgroundColor(Theme.getColor(Theme.key_graySection) & (-218103809));
        L4:
            if (r3 != 0) goto L8;
            r4.setAlpha(0.0f);
            return r4;
        L8:
            if (r3 >= this.this$0.sections.size()) goto L10;
            r4.setAlpha(1.0f);
            MessageObject r2 = this.this$0.sectionArrays.get(this.this$0.sections.get(r3)).get(0);
            ((GraySectionCell) r4).setText(LocaleController.formatSectionDate(r2.messageOwner.date));
        L10:
            return r4;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1, int r2, int r3) {
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SectionsAdapter
        public void onBindViewHolder(int r7, int r8, RecyclerView.ViewHolder r9) {
            if (r9.getItemViewType() == 2) goto L28;
            ArrayList<MessageObject> r0 = this.this$0.sectionArrays.get(this.this$0.sections.get(r7));
            int r1 = r9.getItemViewType();
            boolean r2 = false;
            if (r1 != 0) goto L7;
            MessageObject r6 = r0.get(0);
            ((GraySectionCell) r9.itemView).setText(LocaleController.formatSectionDate(r6.messageOwner.date));
            return;
        L7:
            if (r1 != 1) goto L29;
            if (r7 == 0) goto L11;
            r8 = r8 - 1;
        L11:
            SharedLinkCell r92 = (SharedLinkCell) r9.itemView;
            MessageObject r12 = r0.get(r8);
            if (r92.getMessage() != null) goto L14;
        L16:
            boolean r4 = false;
        L18:
            if (r8 == (r0.size() - 1)) goto L20;
        L23:
            r2 = true;
        L24:
            r92.setLink(r12, r2);
            r92.getViewTreeObserver().addOnPreDrawListener(new AnonymousClass2(this, r92, r12, r4));
            return;
        L20:
            if (r7 != (this.this$0.sections.size() - 1)) goto L24;
            if (FilteredSearchView.m14825$$Nest$fgetisLoading(this.this$0) == false) goto L24;
        L14:
            if (r92.getMessage().getId() != r12.getId()) goto L16;
            r4 = true;
            goto L18
        L29:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L4;
            GraySectionCell r32 = new GraySectionCell(this.mContext);
        L8:
            r32.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r32);
        L4:
            if (r3 == 1) goto L6;
            FlickerLoadingView r33 = new FlickerLoadingView(this.mContext);
            r33.setViewType(5);
            r33.setIsSingleCell(true);
            r32 = r33;
            goto L8
        L6:
            SharedLinkCell r34 = new SharedLinkCell(this.mContext, 1);
            r34.setDelegate(this.sharedLinkCellDelegate);
            r32 = r34;
            goto L8
        }
    }

    public class SharedPhotoVideoAdapter extends RecyclerListView.SelectionAdapter {
        private Context mContext;
        final /* synthetic */ FilteredSearchView this$0;

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedPhotoVideoAdapter$1, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass1 implements SharedPhotoVideoCell.SharedPhotoVideoCellDelegate {
            final /* synthetic */ SharedPhotoVideoAdapter this$1;

            public AnonymousClass1(SharedPhotoVideoAdapter r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Cells.SharedPhotoVideoCell.SharedPhotoVideoCellDelegate
            public void didClickItem(SharedPhotoVideoCell r1, int r2, MessageObject r3, int r4) {
                FilteredSearchView.m14834$$Nest$monItemClick(this.this$1.this$0, r2, r1, r3, r4);
            }

            @Override // org.telegram.ui.Cells.SharedPhotoVideoCell.SharedPhotoVideoCellDelegate
            public boolean didLongClickItem(SharedPhotoVideoCell r2, int r3, MessageObject r4, int r5) {
                if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$1.this$0).actionModeShowing() == false) goto L7;
                didClickItem(r2, r3, r4, r5);
                return true;
            L7:
                return FilteredSearchView.m14835$$Nest$monItemLongClick(this.this$1.this$0, r4, r2, r5);
            }
        }

        /* renamed from: org.telegram.ui.FilteredSearchView$SharedPhotoVideoAdapter$2, reason: invalid class name */
        /* loaded from: classes6.dex */
        public class AnonymousClass2 extends FlickerLoadingView {
            final /* synthetic */ SharedPhotoVideoAdapter this$1;

            public AnonymousClass2(SharedPhotoVideoAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // org.telegram.ui.Components.FlickerLoadingView
            public int getColumnsCount() {
                return FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$1.this$0);
            }
        }

        public SharedPhotoVideoAdapter(FilteredSearchView r1, Context r2) {
            this.this$0 = r1;
            this.mContext = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            if (this.this$0.messages.isEmpty() == false) goto L7;
            return 0;
        L7:
            return ((int) Math.ceil(this.this$0.messages.size() / FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0))) + (!FilteredSearchView.m14823$$Nest$fgetendReached(this.this$0) ? 1 : 0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r3) {
            if (r3 >= ((int) Math.ceil(this.this$0.messages.size() / FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0)))) goto L6;
            return 0;
        L6:
            return 1;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r11, int r12) {
            boolean r2 = true;
            if (r11.getItemViewType() != 0) goto L22;
            FilteredSearchView r0 = this.this$0;
            ArrayList<MessageObject> r3 = r0.messages;
            SharedPhotoVideoCell r112 = (SharedPhotoVideoCell) r11.itemView;
            r112.setItemsCount(FilteredSearchView.m14821$$Nest$fgetcolumnsCount(r0));
            if (r12 != 0) goto L7;
            boolean r02 = true;
        L8:
            r112.setIsFirst(r02);
            int r03 = 0;
        L10:
            if (r03 >= FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0)) goto L19;
            int r4 = (FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0) * r12) + r03;
            if (r4 >= r3.size()) goto L17;
            MessageObject r42 = r3.get(r4);
            r112.setItem(r03, this.this$0.messages.indexOf(r42), r42);
            if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$0).actionModeShowing() == false) goto L16;
            FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$0).set(r42.getId(), r42.getDialogId());
            r112.setChecked(r03, FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$0)), true);
        L18:
            r03 = r03 + 1;
            goto L10
        L16:
            r112.setChecked(r03, false, true);
            goto L18
        L17:
            r112.setItem(r03, r4, null);
            goto L18
        L19:
            r112.requestLayout();
            return;
        L7:
            r02 = false;
            goto L8
        L22:
            if (r11.getItemViewType() != 3) goto L40;
            DialogCell r32 = (DialogCell) r11.itemView;
            if (r12 == (getItemCount() - 1)) goto L26;
            boolean r113 = true;
        L27:
            r32.useSeparator = r113;
            MessageObject r6 = this.this$0.messages.get(r12);
            if (r32.getMessage() != null) goto L30;
        L32:
            r2 = false;
        L33:
            r32.useFromUserAsAvatar = FilteredSearchView.m14832$$Nest$fgetuseFromUserAsAvatar(this.this$0);
            r32.setDialog(r6.getDialogId(), r6, r6.messageOwner.date, false, false);
            if (FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$0).actionModeShowing() == false) goto L37;
            FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$0).set(r6.getId(), r6.getDialogId());
            r32.setChecked(FilteredSearchView.m14831$$Nest$fgetuiCallback(this.this$0).isSelected(FilteredSearchView.m14826$$Nest$fgetmessageHashIdTmp(this.this$0)), r2);
            return;
        L37:
            r32.setChecked(false, r2);
            return;
        L30:
            if (r32.getMessage().getId() != r6.getId()) goto L32;
        L26:
            r113 = false;
            goto L27
        L40:
            if (r11.getItemViewType() != 1) goto L47;
            ((FlickerLoadingView) r11.itemView).skipDrawItemsCount(FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0) - ((FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0) * ((int) Math.ceil(this.this$0.messages.size() / FilteredSearchView.m14821$$Nest$fgetcolumnsCount(this.this$0)))) - this.this$0.messages.size()));
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r4v4, types: [org.telegram.ui.Components.FlickerLoadingView, org.telegram.ui.FilteredSearchView$SharedPhotoVideoAdapter$2] */
        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r3, int r4) {
            if (r4 != 0) goto L5;
            SharedPhotoVideoCell r42 = new SharedPhotoVideoCell(this.mContext, 1);
            r42.setDelegate(new AnonymousClass1(this));
            FrameLayout r43 = r42;
        L9:
            r43.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r43);
        L5:
            if (r4 == 2) goto L7;
            ?? r44 = new AnonymousClass2(this, this.mContext);
            r44.setIsSingleCell(true);
            r44.setViewType(2);
            r43 = r44;
            goto L9
        L7:
            FrameLayout r45 = new GraySectionCell(this.mContext);
            r45.setBackgroundColor(Theme.getColor(Theme.key_graySection) & (-218103809));
            r43 = r45;
            goto L9
        }
    }

    public interface UiCallback {
        boolean actionModeShowing();

        void goToMessage(MessageObject r1);

        boolean isSelected(MessageHashId r1);

        void showActionMode();

        void toggleItemSelection(MessageObject r1, View r2, int r3);
    }

    public static /* synthetic */ void $r8$lambda$S_nvVtLX13CuMmcUwsjZhzWU9qw(FilteredSearchView r0, View r1, int r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Tb_ZE63QS5pImshz8PKTKpAQiEk(FilteredSearchView r0, long r1, long r3, String r5, FiltersView.MediaFilterData r6, int r7, long r8, long r10, boolean r12, boolean r13, String r14, int r15) {
        r0.lambda$search$4(r1, r3, r5, r6, r7, r8, r10, r12, r13, r14, r15);
    }

    public static /* synthetic */ void $r8$lambda$UYc78hbV7URw5CtN_mHhsT5PrTI(FilteredSearchView r0, int r1, String r2, int r3, boolean r4, FiltersView.MediaFilterData r5, long r6, long r8, ArrayList r10, ArrayList r11, TLRPC.messages_Messages r12, TLRPC.TL_error r13) {
        r0.lambda$search$3(r1, r2, r3, r4, r5, r6, r8, r10, r11, r12, r13);
    }

    public static /* synthetic */ void $r8$lambda$a6X3WBjYTLU0oJQ_EMrBThVTieI(FilteredSearchView r0, int r1, TLRPC.TL_error r2, TLRPC.messages_Messages r3, int r4, boolean r5, String r6, ArrayList r7, FiltersView.MediaFilterData r8, long r9, long r11, ArrayList r13, ArrayList r14) {
        r0.lambda$search$2(r1, r2, r3, r4, r5, r6, r7, r8, r9, r11, r13, r14);
    }

    public static /* synthetic */ void $r8$lambda$gfwpwH75YmnIzUswuoB96J_w0kQ(FilteredSearchView r0) {
        r0.lambda$getThemeDescriptions$5();
    }

    public static /* synthetic */ void $r8$lambda$iSl7F2nFQxr8WhYuaBQ4ykTIyBg(FilteredSearchView r0) {
        r0.lambda$new$0();
    }

    /* renamed from: -$$Nest$fgetchatPreviewDelegate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchViewPager.ChatPreviewDelegate m14820$$Nest$fgetchatPreviewDelegate(FilteredSearchView r0) {
        return r0.chatPreviewDelegate;
    }

    /* renamed from: -$$Nest$fgetcolumnsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14821$$Nest$fgetcolumnsCount(FilteredSearchView r0) {
        return r0.columnsCount;
    }

    /* renamed from: -$$Nest$fgetcurrentDataQuery, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m14822$$Nest$fgetcurrentDataQuery(FilteredSearchView r0) {
        return r0.currentDataQuery;
    }

    /* renamed from: -$$Nest$fgetendReached, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14823$$Nest$fgetendReached(FilteredSearchView r0) {
        return r0.endReached;
    }

    /* renamed from: -$$Nest$fgetfloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FloatingDateView m14824$$Nest$fgetfloatingDateView(FilteredSearchView r0) {
        return r0.floatingDateView;
    }

    /* renamed from: -$$Nest$fgetisLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14825$$Nest$fgetisLoading(FilteredSearchView r0) {
        return r0.isLoading;
    }

    /* renamed from: -$$Nest$fgetmessageHashIdTmp, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageHashId m14826$$Nest$fgetmessageHashIdTmp(FilteredSearchView r0) {
        return r0.messageHashIdTmp;
    }

    /* renamed from: -$$Nest$fgetnextSearchRate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14827$$Nest$fgetnextSearchRate(FilteredSearchView r0) {
        return r0.nextSearchRate;
    }

    /* renamed from: -$$Nest$fgetnotificationsLocker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimationNotificationsLocker m14828$$Nest$fgetnotificationsLocker(FilteredSearchView r0) {
        return r0.notificationsLocker;
    }

    /* renamed from: -$$Nest$fgetsharedPhotoVideoAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedPhotoVideoAdapter m14829$$Nest$fgetsharedPhotoVideoAdapter(FilteredSearchView r0) {
        return r0.sharedPhotoVideoAdapter;
    }

    /* renamed from: -$$Nest$fgettotalCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m14830$$Nest$fgettotalCount(FilteredSearchView r0) {
        return r0.totalCount;
    }

    /* renamed from: -$$Nest$fgetuiCallback, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UiCallback m14831$$Nest$fgetuiCallback(FilteredSearchView r0) {
        return r0.uiCallback;
    }

    /* renamed from: -$$Nest$fgetuseFromUserAsAvatar, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14832$$Nest$fgetuseFromUserAsAvatar(FilteredSearchView r0) {
        return r0.useFromUserAsAvatar;
    }

    /* renamed from: -$$Nest$mhideFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14833$$Nest$mhideFloatingDateView(FilteredSearchView r0) {
        r0.hideFloatingDateView();
    }

    /* renamed from: -$$Nest$monItemClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14834$$Nest$monItemClick(FilteredSearchView r0, int r1, View r2, MessageObject r3, int r4) {
        r0.onItemClick(r1, r2, r3, r4);
    }

    /* renamed from: -$$Nest$monItemLongClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m14835$$Nest$monItemLongClick(FilteredSearchView r0, MessageObject r1, View r2, int r3) {
        return r0.onItemLongClick(r1, r2, r3);
    }

    /* renamed from: -$$Nest$mopenUrl, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14836$$Nest$mopenUrl(FilteredSearchView r0, String r1) {
        r0.openUrl(r1);
    }

    /* renamed from: -$$Nest$mopenWebView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14837$$Nest$mopenWebView(FilteredSearchView r0, TLRPC.WebPage r1, MessageObject r2) {
        r0.openWebView(r1, r2);
    }

    /* renamed from: -$$Nest$mshowFloatingDateView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m14838$$Nest$mshowFloatingDateView(FilteredSearchView r0) {
        r0.showFloatingDateView();
    }

    static {
        arrowSpan = new SpannableStringBuilder[3];
    }

    public FilteredSearchView(BaseFragment r14) {
        super(r14.getParentActivity());
        this.animatorFloatingDataVisible = new BoolAnimator(0, this, CubicBezierInterpolator.EASE_OUT_QUINT, 380);
        this.messages = new ArrayList();
        this.messagesById = new SparseArray();
        this.sections = new ArrayList();
        this.sectionArrays = new HashMap();
        this.columnsCount = 3;
        this.messageHashIdTmp = new MessageHashId(0, 0);
        this.localTipChats = new ArrayList();
        this.localTipDates = new ArrayList();
        this.clearCurrentResultsRunnable = new AnonymousClass1(this);
        this.provider = new AnonymousClass2(this);
        this.firstLoading = true;
        this.notificationsLocker = new AnimationNotificationsLocker();
        this.hideFloatingDateRunnable = new FilteredSearchView$$ExternalSyntheticLambda1(this);
        this.parentFragment = r14;
        Activity r142 = r14.getParentActivity();
        this.parentActivity = r142;
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        AnonymousClass3 r0 = new AnonymousClass3(this, r142);
        this.recyclerListView = r0;
        r0.setOnItemClickListener(new FilteredSearchView$$ExternalSyntheticLambda2(this));
        r0.setOnItemLongClickListener(new AnonymousClass4(this));
        LinearLayoutManager r1 = new LinearLayoutManager(r142);
        this.layoutManager = r1;
        r0.setLayoutManager(r1);
        AnonymousClass5 r12 = new AnonymousClass5(this, r142);
        this.loadingView = r12;
        addView(r12);
        addView(r0);
        r0.setSectionsType(2);
        r0.setSkipDrawSection(true);
        r0.setOnScrollListener(new AnonymousClass6(this));
        FloatingDateView r4 = new FloatingDateView(r142);
        this.floatingDateView = r4;
        r4.setCustomDate((int) (System.currentTimeMillis() / 1000));
        addView(r4, LayoutHelper.createFrame(-1, 33.0f, 49, 0.0f, -2.0f, 0.0f, 0.0f));
        this.dialogsAdapter = new OnlyUserFiltersAdapter(this);
        this.sharedPhotoVideoAdapter = new SharedPhotoVideoAdapter(this, getContext());
        this.sharedDocumentsAdapter = new SharedDocumentsAdapter(this, getContext(), 1);
        this.sharedLinksAdapter = new SharedLinksAdapter(this, getContext());
        this.sharedAudioAdapter = new SharedDocumentsAdapter(this, getContext(), 4);
        this.sharedVoiceAdapter = new SharedDocumentsAdapter(this, getContext(), 2);
        StickerEmptyView r2 = new StickerEmptyView(r142, r12, 1);
        this.emptyView = r2;
        addView(r2);
        r0.setEmptyView(this.emptyView);
        this.emptyView.setVisibility(8);
        checkUi_floatingDateView();
    }

    private void checkUi_floatingDateView() {
        float r0 = this.animatorFloatingDataVisible.getFloatValue();
        this.floatingDateView.setTranslationY((-AndroidUtilities.dp(24.0f)) * (1.0f - r0));
        this.floatingDateView.setAlpha(r0);
        FloatingDateView r4 = this.floatingDateView;
        if (r0 <= 0.0f) goto L5;
        int r02 = 0;
    L6:
        r4.setVisibility(r02);
        return;
    L5:
        r02 = 4;
        goto L6
    }

    public static CharSequence createFromInfoString(MessageObject r1, int r2) {
        return createFromInfoString(r1, true, r2);
    }

    public static CharSequence createFromInfoString(MessageObject r1, boolean r2, int r3) {
        return createFromInfoString(r1, r2, r3, null);
    }

    public static CharSequence createFromInfoString(MessageObject r11, boolean r12, int r13, TextPaint r14) {
        if (r11 != null) goto L5;
    L116:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        if (r11.messageOwner == null) goto L116;
        if (r11.isQuickReply() == false) goto L15;
        QuickRepliesController.QuickReply r112 = QuickRepliesController.getInstance(r11.currentAccount).findReply(r11.getQuickReplyId());
        if (r112 != null) goto L13;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L13:
        return r112.name;
    L15:
        if (r11.isSponsored() == true) goto L17;
        SpannableStringBuilder[] r1 = arrowSpan;
        if (r1[r13] != null) goto L45;
        r1[r13] = new SpannableStringBuilder(">");
        if (r13 != 0) goto L31;
        int r2 = R.drawable.attach_arrow_right;
    L35:
        Drawable r22 = ContextCompat.getDrawable(ApplicationLoader.applicationContext, r2).mutate();
        if (r13 != 0) goto L38;
        int r6 = 2;
    L39:
        ColoredImageSpan r5 = new ColoredImageSpan(r22, r6);
        if (r13 == 1) goto L42;
        if (r13 == 2) goto L42;
    L43:
        SpannableStringBuilder r15 = arrowSpan[r13];
        r15.setSpan(r5, 0, r15.length(), 0);
    L42:
        r5.setScale(0.85f);
        goto L43
    L38:
        r6 = 1;
        goto L39
    L31:
        if (r13 != 1) goto L33;
        r2 = R.drawable.msg_mini_arrow_mediathin;
        goto L35
    L33:
        if (r13 != 2) goto L44;
        r2 = R.drawable.msg_mini_arrow_mediabold;
        goto L35
    L44:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L45:
        TLRPC.Message r16 = r11.messageOwner;
        CharSequence r7 = null;
        Paint.FontMetricsInt r72 = null;
        Paint.FontMetricsInt r73 = null;
        Paint.FontMetricsInt r74 = null;
        if (r16.saved_peer_id == null) goto L56;
        if (r11.getSavedDialogId() < 0) goto L52;
        TLRPC.User r122 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r11.getSavedDialogId()));
        TLRPC.Chat r23 = null;
    L50:
        TLRPC.Chat r8 = r23;
    L82:
        if (r122 == null) goto L97;
        if (r8 == null) goto L97;
        CharSequence r17 = r8.title;
        if (ChatObject.isForum(r8) == false) goto L89;
        TLRPC.TL_forumTopic r113 = MessagesController.getInstance(UserConfig.selectedAccount).getTopicsController().findTopic(r8.id, MessageObject.getTopicId(r11.currentAccount, r11.messageOwner, true));
        if (r113 == null) goto L89;
        r17 = ForumUtilities.getTopicSpannedName(r113, null, false);
    L89:
        if (r14 != null) goto L91;
        Paint.FontMetricsInt r114 = null;
    L92:
        CharSequence r115 = Emoji.replaceEmoji(r17, r114, false);
        SpannableStringBuilder r18 = new SpannableStringBuilder();
        String r123 = UserObject.getFirstName(r122);
        if (r14 == null) goto L96;
        r72 = r14.getFontMetricsInt();
    L96:
        r18.append(Emoji.replaceEmoji(r123, r72, false)).append(8202).append(arrowSpan[r13]).append(8202).append(r115);
        r7 = r18;
    L113:
        if (r7 != null) goto L115;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L115:
        return r7;
    L91:
        r114 = r14.getFontMetricsInt();
    L97:
        if (r122 == null) goto L103;
        String r116 = UserObject.getUserName(r122);
        if (r14 == null) goto L102;
        r73 = r14.getFontMetricsInt();
    L102:
        r7 = Emoji.replaceEmoji(r116, r73, false);
        goto L113
    L103:
        if (r23 == null) goto L113;
        CharSequence r124 = r23.title;
        if (ChatObject.isForum(r23) == false) goto L109;
        TLRPC.TL_forumTopic r117 = MessagesController.getInstance(UserConfig.selectedAccount).getTopicsController().findTopic(r23.id, MessageObject.getTopicId(r11.currentAccount, r11.messageOwner, true));
        if (r117 == null) goto L109;
        r124 = ForumUtilities.getTopicSpannedName(r117, null, false);
    L109:
        if (r14 == null) goto L112;
        r74 = r14.getFontMetricsInt();
    L112:
        r7 = Emoji.replaceEmoji(r124, r74, false);
        goto L113
    L52:
        if (r11.getSavedDialogId() >= 0) goto L54;
        r23 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-r11.getSavedDialogId()));
        r122 = null;
        r8 = null;
        goto L82
    L54:
        r122 = null;
        r23 = null;
        goto L50
    L56:
        if (r16.from_id.user_id == 0) goto L58;
        TLRPC.User r19 = MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r11.messageOwner.from_id.user_id));
    L60:
        if (r11.messageOwner.from_id.chat_id == 0) goto L62;
        r23 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r11.messageOwner.peer_id.chat_id));
    L63:
        if (r23 != null) goto L69;
        if (r11.messageOwner.from_id.channel_id == 0) goto L67;
        r23 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r11.messageOwner.peer_id.channel_id));
        goto L69
    L67:
        r23 = null;
    L69:
        if (r11.messageOwner.peer_id.channel_id == 0) goto L71;
        r8 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r11.messageOwner.peer_id.channel_id));
    L72:
        if (r8 != null) goto L78;
        if (r11.messageOwner.peer_id.chat_id == 0) goto L76;
        r8 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(r11.messageOwner.peer_id.chat_id));
        goto L78
    L76:
        r8 = null;
    L78:
        if (ChatObject.isChannelAndNotMegaGroup(r8) == true) goto L81;
        if (r12 == true) goto L81;
        r122 = r19;
        r8 = null;
    L81:
        r122 = r19;
        goto L82
    L71:
        r8 = null;
        goto L72
    L62:
        r23 = null;
        goto L63
    L58:
        r19 = null;
        goto L60
    L17:
        if (r11.sponsoredCanReport == false) goto L21;
        return LocaleController.getString(R.string.SponsoredMessageAd);
    L21:
        if (r11.sponsoredRecommended == false) goto L25;
        return LocaleController.getString(R.string.SponsoredMessage2Recommended);
    L25:
        return LocaleController.getString(R.string.SponsoredMessage2);
    }

    private void hideFloatingDateView() {
        AndroidUtilities.cancelRunOnUIThread(this.hideFloatingDateRunnable);
        this.animatorFloatingDataVisible.setValue(false, true);
    }

    private /* synthetic */ void lambda$getThemeDescriptions$5() {
        FloatingDateView r0 = this.floatingDateView;
        if (r0 == null) goto L6;
        r0.updateColors();
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        hideFloatingDateView();
    }

    private /* synthetic */ void lambda$new$1(View r3, int r4) {
        if ((r3 instanceof SharedDocumentCell) == false) goto L7;
        onItemClick(r4, r3, ((SharedDocumentCell) r3).getMessage(), 0);
        return;
    L7:
        if ((r3 instanceof SharedLinkCell) == false) goto L11;
        onItemClick(r4, r3, ((SharedLinkCell) r3).getMessage(), 0);
        return;
    L11:
        if ((r3 instanceof SharedAudioCell) == false) goto L15;
        onItemClick(r4, r3, ((SharedAudioCell) r3).getMessage(), 0);
        return;
    L15:
        if ((r3 instanceof ContextLinkCell) == false) goto L19;
        onItemClick(r4, r3, ((ContextLinkCell) r3).getMessageObject(), 0);
        return;
    L19:
        if ((r3 instanceof DialogCell) == false) goto L22;
        onItemClick(r4, r3, ((DialogCell) r3).getMessage(), 0);
        return;
    }

    private /* synthetic */ void lambda$search$2(int r12, TLRPC.TL_error r13, TLRPC.messages_Messages r14, int r15, boolean r16, String r17, ArrayList r18, FiltersView.MediaFilterData r19, long r20, long r22, ArrayList r24, ArrayList r25) {
        if (r12 == this.requestIndex) goto L5;
        return;
    L5:
        int r122 = 0;
        this.isLoading = false;
        StickerEmptyView r4 = this.emptyView;
        if (r13 == null) goto L9;
        r4.title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
        this.emptyView.subtitle.setVisibility(0);
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        this.emptyView.showProgress(false, true);
        return;
    L9:
        r4.showProgress(false);
        this.nextSearchRate = r14.next_rate;
        MessagesStorage.getInstance(r15).putUsersAndChats(r14.users, r14.chats, true, true);
        MessagesController.getInstance(r15).putUsers(r14.users, false);
        MessagesController.getInstance(r15).putChats(r14.chats, false);
        if (r16 == true) goto L12;
        this.messages.clear();
        this.messagesById.clear();
        this.sections.clear();
        this.sectionArrays.clear();
    L12:
        this.totalCount = r14.count;
        this.currentDataQuery = r17;
        int r0 = r18.size();
        int r42 = 0;
    L13:
        if (r42 >= r0) goto L22;
        MessageObject r7 = (MessageObject) r18.get(r42);
        ArrayList<MessageObject> r8 = this.sectionArrays.get(r7.monthKey);
        if (r8 != null) goto L17;
        r8 = new ArrayList();
        this.sectionArrays.put(r7.monthKey, r8);
        this.sections.add(r7.monthKey);
    L17:
        r8.add(r7);
        this.messages.add(r7);
        this.messagesById.put(r7.getId(), r7);
        if (PhotoViewer.getInstance().isVisible() == false) goto L20;
        PhotoViewer.getInstance().addPhoto(r7, this.photoViewerClassGuid);
    L20:
        r42 = r42 + 1;
        goto L13
    L22:
        if (this.messages.size() <= this.totalCount) goto L25;
        this.totalCount = this.messages.size();
    L25:
        if (this.messages.size() < this.totalCount) goto L27;
        boolean r43 = true;
    L28:
        this.endReached = r43;
        if (this.messages.isEmpty() == false) goto L50;
        if (r19 != null) goto L32;
        this.emptyView.title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
        this.emptyView.subtitle.setVisibility(8);
        goto L50
    L32:
        if (TextUtils.isEmpty(this.currentDataQuery) == true) goto L34;
    L48:
        this.emptyView.title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle2));
        this.emptyView.subtitle.setVisibility(0);
        this.emptyView.subtitle.setText(LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitle2));
        goto L50
    L34:
        if (r20 != 0) goto L48;
        if (r22 != 0) goto L48;
        this.emptyView.title.setText(LocaleController.getString(R.string.SearchEmptyViewTitle));
        int r44 = r19.filterType;
        if (r44 != 1) goto L40;
        String r45 = LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleFiles);
    L47:
        this.emptyView.subtitle.setVisibility(0);
        this.emptyView.subtitle.setText(r45);
        goto L50
    L40:
        if (r44 != 0) goto L42;
        r45 = LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleMedia);
        goto L47
    L42:
        if (r44 != 2) goto L44;
        r45 = LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleLinks);
        goto L47
    L44:
        if (r44 != 3) goto L46;
        r45 = LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleMusic);
        goto L47
    L46:
        r45 = LocaleController.getString(R.string.SearchEmptyViewFilteredSubtitleVoice);
    L50:
        if (r19 == null) goto L67;
        int r2 = r19.filterType;
        if (r2 == 0) goto L64;
        if (r2 == 1) goto L62;
        if (r2 == 2) goto L61;
        if (r2 != 3) goto L57;
        this.adapter = this.sharedAudioAdapter;
    L68:
        RecyclerView.Adapter r23 = this.recyclerListView.getAdapter();
        RecyclerView.Adapter r46 = this.adapter;
        if (r23 == r46) goto L71;
        this.recyclerListView.setAdapter(r46);
    L71:
        if (r16 == true) goto L104;
        this.localTipChats.clear();
        if (r24 == null) goto L75;
        this.localTipChats.addAll(r24);
    L75:
        if (r17 != null) goto L77;
    L92:
        this.localTipDates.clear();
        this.localTipDates.addAll(r25);
        this.localTipArchive = false;
        if (r17 != null) goto L95;
    L101:
        Delegate r1 = this.delegate;
        if (r1 == null) goto L104;
        r1.updateFiltersView(TextUtils.isEmpty(this.currentDataQuery), this.localTipChats, this.localTipDates, this.localTipArchive);
        goto L104
    L95:
        if (r17.length() < 3) goto L101;
        if (LocaleController.getString(R.string.ArchiveSearchFilter).toLowerCase().startsWith(r17) == false) goto L99;
    L100:
        this.localTipArchive = true;
        goto L101
    L99:
        if ("archive".startsWith(r17) == false) goto L101;
    L77:
        if (r17.length() < 3) goto L92;
        if (LocaleController.getString(R.string.SavedMessages).toLowerCase().startsWith(r17) == false) goto L81;
    L82:
        int r26 = 0;
    L83:
        int r3 = this.localTipChats.size();
        ArrayList<Object> r47 = this.localTipChats;
        if (r26 >= r3) goto L91;
        if ((r47.get(r26) instanceof TLRPC.User) == false) goto L90;
        if (UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser().id == ((TLRPC.User) this.localTipChats.get(r26)).id) goto L92;
    L90:
        r26 = r26 + 1;
        goto L83
    L91:
        r47.add(0, UserConfig.getInstance(UserConfig.selectedAccount).getCurrentUser());
        goto L92
    L81:
        if ("saved messages".startsWith(r17) == false) goto L92;
    L104:
        this.firstLoading = false;
        View r110 = null;
        int r27 = -1;
    L105:
        if (r122 >= r0) goto L110;
        View r32 = this.recyclerListView.getChildAt(r122);
        if ((r32 instanceof FlickerLoadingView) == false) goto L109;
        r27 = this.recyclerListView.getChildAdapterPosition(r32);
        r110 = r32;
    L109:
        r122 = r122 + 1;
        goto L105
    L110:
        if (r110 == null) goto L113;
        this.recyclerListView.removeView(r110);
    L113:
        if (this.loadingView.getVisibility() != 0) goto L117;
        if (this.recyclerListView.getChildCount() != 0) goto L117;
    L119:
        getViewTreeObserver().addOnPreDrawListener(new AnonymousClass7(this, r110, r27));
    L120:
        this.adapter.notifyDataSetChanged();
        return;
    L117:
        if (this.recyclerListView.getAdapter() == this.sharedPhotoVideoAdapter) goto L120;
        if (r110 == null) goto L120;
    L57:
        if (r2 != 5) goto L68;
        this.adapter = this.sharedVoiceAdapter;
        goto L68
    L61:
        this.adapter = this.sharedLinksAdapter;
        goto L68
    L62:
        this.adapter = this.sharedDocumentsAdapter;
        goto L68
    L64:
        if (TextUtils.isEmpty(this.currentDataQuery) == false) goto L66;
        this.adapter = this.sharedPhotoVideoAdapter;
        goto L68
    L66:
        this.adapter = this.dialogsAdapter;
        goto L68
    L67:
        this.adapter = this.dialogsAdapter;
        goto L68
    L27:
        r43 = false;
        goto L28
    }

    private /* synthetic */ void lambda$search$3(int r17, String r18, int r19, boolean r20, FiltersView.MediaFilterData r21, long r22, long r24, ArrayList r26, ArrayList r27, TLRPC.messages_Messages r28, TLRPC.TL_error r29) {
        ArrayList r8 = new ArrayList();
        if (r29 != null) goto L7;
        int r0 = r28.messages.size();
        int r2 = 0;
    L5:
        if (r2 >= r0) goto L7;
        MessageObject r3 = new MessageObject(r17, r28.messages.get(r2), false, true);
        r3.setQuery(r18);
        r8.add(r3);
        r2 = r2 + 1;
    L7:
        AndroidUtilities.runOnUIThread(new FilteredSearchView$$ExternalSyntheticLambda5(this, r19, r29, r28, r17, r20, r18, r8, r21, r22, r24, r26, r27));
    }

    /* JADX WARN: Multi-variable type inference failed */
    private /* synthetic */ void lambda$search$4(long r22, long r24, String r26, FiltersView.MediaFilterData r27, int r28, long r29, long r31, boolean r33, boolean r34, String r35, int r36) {
        ArrayList<Object> r12 = null;
        if (r22 == 0) goto L25;
        if (r24 != 0) goto L25;
        TLRPC.TL_messages_search r0 = new TLRPC.TL_messages_search();
        r0.q = r26;
        r0.limit = 20;
        if (r27 != null) goto L9;
        TLRPC.MessagesFilter r2 = new TLRPC.TL_inputMessagesFilterEmpty();
    L10:
        r0.filter = r2;
        r0.peer = AccountInstance.getInstance(r28).getMessagesController().getInputPeer(r22);
        if (r29 <= 0) goto L14;
        r0.min_date = (int) (r29 / 1000);
    L14:
        if (r31 <= 0) goto L16;
        r0.max_date = (int) (r31 / 1000);
    L16:
        if (r33 == true) goto L18;
    L22:
        r0.offset_id = 0;
    L23:
        TLRPC.TL_messages_searchGlobal r13 = r0;
    L46:
        this.lastMessagesSearchString = r26;
        this.lastSearchFilterQueryString = r35;
        ArrayList<Object> r11 = r12;
        ArrayList r122 = new ArrayList();
        FiltersView.fillTipDates(this.lastMessagesSearchString, r122);
        ConnectionsManager.getInstance(r28).sendRequestTyped(r13, new FilteredSearchView$$ExternalSyntheticLambda4(this, r28, r26, r36, r33, r27, r22, r29, r11, r122));
        return;
    L18:
        if (r26.equals(this.lastMessagesSearchString) == false) goto L22;
        if (this.messages.isEmpty() == true) goto L22;
        r0.offset_id = this.messages.get(r2.size() - 1).getId();
        goto L23
    L9:
        r2 = r27.filter;
    L25:
        if (TextUtils.isEmpty(r26) == true) goto L27;
        r12 = new ArrayList();
        int r02 = 20;
        long r19 = 0;
        int r4 = 0;
        MessagesStorage.getInstance(r28).localSearch(0, r26, r12, new ArrayList(), new ArrayList(), null, r34 ? 1 : 0);
    L28:
        TLRPC.TL_messages_searchGlobal r5 = new TLRPC.TL_messages_searchGlobal();
        r5.limit = r02;
        r5.q = r26;
        if (r27 != null) goto L31;
        TLRPC.MessagesFilter r03 = new TLRPC.TL_inputMessagesFilterEmpty();
    L32:
        r5.filter = r03;
        r5.community = MessagesController.getInstance(r28).getInputChannel(r24);
        if (r29 <= r19) goto L36;
        r5.min_date = (int) (r29 / 1000);
    L36:
        if (r31 <= r19) goto L38;
        r5.max_date = (int) (r31 / 1000);
    L38:
        if (r33 == true) goto L40;
    L44:
        r5.offset_rate = r4;
        r5.offset_id = r4;
        r5.offset_peer = new TLRPC.TL_inputPeerEmpty();
    L45:
        r5.flags |= 1;
        r5.folder_id = r34 ? 1 : 0;
        r13 = r5;
        goto L46
    L40:
        if (r26.equals(this.lastMessagesSearchString) == false) goto L44;
        if (this.messages.isEmpty() == true) goto L44;
        MessageObject r04 = this.messages.get(r0.size() - 1);
        r5.offset_id = r04.getId();
        r5.offset_rate = this.nextSearchRate;
        r5.offset_peer = MessagesController.getInstance(r28).getInputPeer(MessageObject.getPeerId(r04.messageOwner.peer_id));
        goto L45
    L31:
        r03 = r27.filter;
        goto L32
    L27:
        r19 = 0;
        r02 = 20;
        r4 = 0;
        goto L28
    }

    private void onItemClick(int r15, View r16, MessageObject r17, int r18) {
        if (r17 != null) goto L6;
        return;
    L6:
        if (this.uiCallback.actionModeShowing() == false) goto L10;
        this.uiCallback.toggleItemSelection(r17, r16, r18);
        return;
    L10:
        if ((r16 instanceof DialogCell) == false) goto L13;
        this.uiCallback.goToMessage(r17);
        return;
    L13:
        int r2 = this.currentSearchFilter.filterType;
        if (r2 != 0) goto L18;
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        PhotoViewer.getInstance().openPhoto(this.messages, r15, 0, 0, 0, this.provider);
        this.photoViewerClassGuid = PhotoViewer.getInstance().getClassGuid();
        return;
    L18:
        if (r2 == 3) goto L80;
        if (r2 == 5) goto L80;
        if (r2 != 1) goto L45;
        if ((r16 instanceof SharedDocumentCell) == false) goto L84;
        SharedDocumentCell r0 = (SharedDocumentCell) r16;
        TLRPC.Document r22 = r17.getDocument();
        if (r0.isLoaded() == false) goto L39;
        if (r17.canPreviewDocument() == false) goto L36;
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        int r3 = this.messages.indexOf(r17);
        if (r3 >= 0) goto L34;
        ArrayList<MessageObject> r5 = new ArrayList();
        r5.add(r17);
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        PhotoViewer.getInstance().openPhoto(r5, 0, 0, 0, 0, this.provider);
        this.photoViewerClassGuid = PhotoViewer.getInstance().getClassGuid();
        return;
    L34:
        PhotoViewer.getInstance().setParentActivity(this.parentFragment);
        PhotoViewer.getInstance().openPhoto(this.messages, r3, 0, 0, 0, this.provider);
        this.photoViewerClassGuid = PhotoViewer.getInstance().getClassGuid();
        return;
    L36:
        AndroidUtilities.openDocument(r17, this.parentActivity, this.parentFragment);
        return;
    L39:
        if (r0.isLoading() == true) goto L42;
        MessageObject r14 = r0.getMessage();
        r14.putInDownloadsStore = true;
        AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().loadFile(r22, r14, 0, 0);
        r0.updateFileExistIcon(true);
        return;
    L42:
        AccountInstance.getInstance(UserConfig.selectedAccount).getFileLoader().cancelLoadFile(r22);
        r0.updateFileExistIcon(true);
        return;
    L84:
        return;
    L45:
        if (r2 != 2) goto L85;
        TLRPC.MessageMedia r23 = r17.messageOwner.media;     // Catch: Exception -> L76
        String r4 = null;
        if (r23 == null) goto L49;
        TLRPC.WebPage r24 = r23.webpage;     // Catch: Exception -> L76
    L50:
        if (r24 != null) goto L52;
    L71:
        if (r4 != null) goto L73;
        r4 = ((SharedLinkCell) r16).getLink(0);     // Catch: Exception -> L76
    L73:
        if (r4 == null) goto L87;
        openUrl(r4);     // Catch: Exception -> L76
        return;
    L87:
        return;
    L52:
        if ((r24 instanceof TLRPC.TL_webPageEmpty) == true) goto L71;
        if (r24.cached_page == null) goto L64;
        LaunchActivity r02 = LaunchActivity.instance;     // Catch: Exception -> L76
        if (r02 != null) goto L58;
    L62:
        this.parentFragment.createArticleViewer(false).open(r17);     // Catch: Exception -> L76
        return;
    L58:
        if (r02.getBottomSheetTabs() == null) goto L62;
        if (LaunchActivity.instance.getBottomSheetTabs().tryReopenTab(r17) == null) goto L62;
        return;
    L64:
        String r42 = r24.embed_url;     // Catch: Exception -> L76
        if (r42 != null) goto L67;
    L70:
        r4 = r24.url;     // Catch: Exception -> L76
        goto L71
    L67:
        if (r42.length() == 0) goto L70;
        openWebView(r24, r17);     // Catch: Exception -> L76
        return;
    L49:
        r24 = null;
    L76:
        e = move-exception;
        FileLog.e(e);
        return;
    L85:
        return;
    L80:
        if ((r16 instanceof SharedAudioCell) == false) goto L88;
        ((SharedAudioCell) r16).didPressedButton();
        return;
    }

    private boolean onItemLongClick(MessageObject r2, View r3, int r4) {
        if (this.uiCallback.actionModeShowing() == true) goto L6;
        this.uiCallback.showActionMode();
    L6:
        if (this.uiCallback.actionModeShowing() == false) goto L10;
        this.uiCallback.toggleItemSelection(r2, r3, r4);
        return true;
    L10:
        return true;
    }

    private void openUrl(String r2) {
        if (AndroidUtilities.shouldShowUrlInAlert(r2) == false) goto L6;
        AlertsCreator.showOpenUrlAlert(this.parentFragment, r2, true, true);
        return;
    L6:
        Browser.openUrl(this.parentActivity, r2);
    }

    private void openWebView(TLRPC.WebPage r11, MessageObject r12) {
        EmbedBottomSheet.show(this.parentFragment, r12, this.provider, r11.site_name, r11.description, r11.url, r11.embed_url, r11.embed_width, r11.embed_height, false);
    }

    private void showFloatingDateView() {
        AndroidUtilities.cancelRunOnUIThread(this.hideFloatingDateRunnable);
        AndroidUtilities.runOnUIThread(this.hideFloatingDateRunnable, 1650);
        this.animatorFloatingDataVisible.setValue(true, true);
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r2, int r3, Object... r4) {
        if (r2 != NotificationCenter.emojiLoaded) goto L10;
        int r22 = this.recyclerListView.getChildCount();
        int r42 = 0;
    L5:
        if (r42 >= r22) goto L14;
        if ((this.recyclerListView.getChildAt(r42) instanceof DialogCell) == false) goto L9;
        ((DialogCell) this.recyclerListView.getChildAt(r42)).update(0);
    L9:
        this.recyclerListView.getChildAt(r42).invalidate();
        r42 = r42 + 1;
        goto L5
    L14:
        return;
    }

    public ArrayList<ThemeDescription> getThemeDescriptions() {
        FilteredSearchView$$ExternalSyntheticLambda0 r8 = new FilteredSearchView$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r10 = new ArrayList();
        int r18 = Theme.key_windowBackgroundWhite;
        r10.add(new ThemeDescription(null, 0, null, null, null, r8, r18));
        r10.add(new ThemeDescription(this, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, r18));
        r10.add(new ThemeDescription(this, 0, null, null, null, null, Theme.key_dialogBackground));
        r10.add(new ThemeDescription(this, 0, null, null, null, null, Theme.key_windowBackgroundGray));
        int r32 = Theme.key_windowBackgroundWhiteBlackText;
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"nameTextView"}, null, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"dateTextView"}, null, null, null, Theme.key_windowBackgroundWhiteGrayText3));
        int r41 = Theme.key_sharedMedia_startStopLoadIcon;
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_PROGRESSBAR, new Class[]{SharedDocumentCell.class}, new String[]{"progressView"}, null, null, null, r41));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"statusImageView"}, null, null, null, r41));
        int r412 = Theme.key_checkbox;
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, r412));
        int r50 = Theme.key_checkboxCheck;
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedDocumentCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_IMAGECOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"thumbImageView"}, null, null, null, Theme.key_files_folderIcon));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedDocumentCell.class}, new String[]{"extTextView"}, null, null, null, Theme.key_files_iconText));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{LoadingCell.class}, new String[]{"progressBar"}, null, null, null, Theme.key_progressCircle));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, r412));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedAudioCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_titleTextPaint, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_TEXTCOLOR, new Class[]{SharedAudioCell.class}, Theme.chat_contextResult_descriptionTextPaint, null, null, Theme.key_windowBackgroundWhiteGrayText2));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOX, new Class[]{SharedLinkCell.class}, new String[]{"checkBox"}, null, null, null, r412));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{SharedLinkCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{SharedLinkCell.class}, new String[]{"titleTextPaint"}, null, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{SharedLinkCell.class}, null, null, null, Theme.key_windowBackgroundWhiteLinkText));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{SharedLinkCell.class}, Theme.linkSelectionPaint, null, null, Theme.key_windowBackgroundWhiteLinkSelection));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{SharedLinkCell.class}, new String[]{"letterDrawable"}, null, null, null, Theme.key_sharedMedia_linkPlaceholderText));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_BACKGROUNDFILTER, new Class[]{SharedLinkCell.class}, new String[]{"letterDrawable"}, null, null, null, Theme.key_sharedMedia_linkPlaceholder));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_SECTIONS, new Class[]{SharedMediaSectionCell.class}, null, null, null, r18));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_SECTIONS, new Class[]{SharedMediaSectionCell.class}, new String[]{"textView"}, null, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{SharedMediaSectionCell.class}, new String[]{"textView"}, null, null, null, r32));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, Theme.avatarDrawables, null, Theme.key_avatar_text));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countPaint, null, null, Theme.key_chats_unreadCounter));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countGrayPaint, null, null, Theme.key_chats_unreadCounterMuted));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_countTextPaint, null, null, Theme.key_chats_unreadCounterText));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_lockDrawable}, null, Theme.key_chats_secretIcon));
        Drawable[] r7 = {Theme.dialogs_scamDrawable, Theme.dialogs_fakeDrawable};
        int r26 = Theme.key_chats_draft;
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, r7, null, r26));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_pinnedDrawable, Theme.dialogs_pinnedDrawable2, Theme.dialogs_reorderDrawable}, null, Theme.key_chats_pinnedIcon));
        com.exteragram.messenger.utils.ui.TextPaint[] r72 = Theme.dialogs_namePaint;
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Paint[]{r72[0], r72[1], Theme.dialogs_searchNamePaint}, null, null, Theme.key_chats_name));
        com.exteragram.messenger.utils.ui.TextPaint[] r73 = Theme.dialogs_nameEncryptedPaint;
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Paint[]{r73[0], r73[1], Theme.dialogs_searchNameEncryptedPaint}, null, null, Theme.key_chats_secretName));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_messagePaint[1], null, null, Theme.key_chats_message_threeLines));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_messagePaint[0], null, null, Theme.key_chats_message));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_messageNamePaint, null, null, Theme.key_chats_nameMessage_threeLines));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, null, null, r26));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, Theme.dialogs_messagePrintingPaint, null, null, Theme.key_chats_actionMessage));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_timePaint, null, null, Theme.key_chats_date));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_pinnedPaint, null, null, Theme.key_chats_pinnedOverlay));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_tabletSeletedPaint, null, null, Theme.key_chats_tabletSelectedOverlay));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_checkDrawable}, null, Theme.key_chats_sentCheck));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_checkReadDrawable, Theme.dialogs_halfCheckDrawable}, null, Theme.key_chats_sentReadCheck));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_clockDrawable}, null, Theme.key_chats_sentClock));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, Theme.dialogs_errorPaint, null, null, Theme.key_chats_sentError));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_errorDrawable}, null, Theme.key_chats_sentErrorIcon));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedCheckDrawable}, null, Theme.key_chats_verifiedCheck));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class, ProfileSearchCell.class}, null, new Drawable[]{Theme.dialogs_verifiedDrawable}, null, Theme.key_chats_verifiedBackground));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_muteDrawable}, null, Theme.key_chats_muteIcon));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, new Drawable[]{Theme.dialogs_mentionDrawable}, null, Theme.key_chats_mentionIcon));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, null, null, Theme.key_chats_archivePinBackground));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, null, null, Theme.key_chats_archiveBackground));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, null, null, Theme.key_chats_onlineCircle));
        r10.add(new ThemeDescription(this.recyclerListView, 0, new Class[]{DialogCell.class}, null, null, null, r18));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOX, new Class[]{DialogCell.class}, new String[]{"checkBox"}, null, null, null, r18));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CHECKBOXCHECK, new Class[]{DialogCell.class}, new String[]{"checkBox"}, null, null, null, r50));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_SECTIONS, new Class[]{GraySectionCell.class}, new String[]{"textView"}, null, null, null, Theme.key_graySectionText));
        r10.add(new ThemeDescription(this.recyclerListView, ThemeDescription.FLAG_CELLBACKGROUNDCOLOR | ThemeDescription.FLAG_SECTIONS, new Class[]{GraySectionCell.class}, null, null, null, Theme.key_graySection));
        r10.add(new ThemeDescription(this.emptyView.title, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, r32));
        r10.add(new ThemeDescription(this.emptyView.subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        return r10;
    }

    public void messagesDeleted(long r9, ArrayList<Integer> r11) {
        int r1 = 0;
        boolean r2 = false;
    L4:
        if (r1 >= this.messages.size()) goto L24;
        MessageObject r3 = this.messages.get(r1);
        long r4 = r3.getDialogId();
        if (r4 >= 0) goto L10;
        int r42 = (int) (-r4);
        if (ChatObject.isChannel(r42, UserConfig.selectedAccount) == false) goto L10;
    L12:
        if (r42 != r9) goto L23;
        int r43 = 0;
    L15:
        if (r43 >= r11.size()) goto L23;
        if (r3.getId() != r11.get(r43).intValue()) goto L22;
        this.messages.remove(r1);
        this.messagesById.remove(r3.getId());
        ArrayList<MessageObject> r22 = this.sectionArrays.get(r3.monthKey);
        r22.remove(r3);
        if (r22.size() != 0) goto L21;
        this.sections.remove(r3.monthKey);
        this.sectionArrays.remove(r3.monthKey);
    L21:
        r1 = r1 - 1;
        this.totalCount--;
        r2 = true;
    L22:
        r43 = r43 + 1;
    L23:
        r1 = r1 + 1;
    L10:
        r42 = 0;
        goto L12
    L24:
        if (r2 == false) goto L34;
        RecyclerView.Adapter r8 = this.adapter;
        if (r8 == null) goto L35;
        r8.notifyDataSetChanged();
        return;
    L35:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        int r0 = UserConfig.selectedAccount;
        this.lastAccount = r0;
        NotificationCenter.getInstance(r0).addObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        NotificationCenter.getInstance(this.lastAccount).removeObserver(this, NotificationCenter.emojiLoaded);
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r1, float r2, float r3, FactorAnimator r4) {
        if (r1 != 0) goto L5;
        checkUi_floatingDateView();
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r5, int r6) {
        int r0 = this.columnsCount;
        if (AndroidUtilities.isTablet() == false) goto L6;
        this.columnsCount = 3;
    L10:
        if (r0 == this.columnsCount) goto L14;
        RecyclerView.Adapter r02 = this.adapter;
        if (r02 != this.sharedPhotoVideoAdapter) goto L14;
        this.ignoreRequestLayout = true;
        r02.notifyDataSetChanged();
        this.ignoreRequestLayout = false;
    L14:
        super.onMeasure(r5, r6);
        return;
    L6:
        if (getResources().getConfiguration().orientation != 2) goto L8;
        this.columnsCount = 6;
        goto L10
    L8:
        this.columnsCount = 3;
        goto L10
    }

    @Override // android.view.View, android.view.ViewParent
    public void requestLayout() {
        if (this.ignoreRequestLayout == false) goto L5;
        return;
    L5:
        super.requestLayout();
    }

    public void search(long r21, long r23, long r25, long r27, FiltersView.MediaFilterData r29, boolean r30, String r31, boolean r32) {
        if (r31 != null) goto L5;
        String r6 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        Locale r0 = Locale.ENGLISH;
        Long r13 = Long.valueOf(r21);
        Long r14 = Long.valueOf(r23);
        Long r15 = Long.valueOf(r25);
        Long r16 = Long.valueOf(r27);
        if (r29 != null) goto L9;
        int r8 = -1;
    L10:
        String r18 = r6;
        String r152 = String.format(r0, "%d%d%d%d%d%s%s", new Object[]{r13, r14, r15, r16, Integer.valueOf(r8), r18, Boolean.valueOf(r30)});
        String r02 = this.lastSearchFilterQueryString;
        if (r02 != null) goto L13;
    L15:
        boolean r03 = false;
    L16:
        if (r03 == true) goto L19;
        if (r32 == false) goto L19;
        boolean r142 = true;
    L20:
        this.currentSearchFilter = r29;
        this.currentSearchDialogId = r21;
        this.currentSearchCommunityId = r23;
        this.currentSearchMinDate = r25;
        this.currentSearchMaxDate = r27;
        this.currentSearchString = r18;
        this.currentIncludeFolder = r30;
        Runnable r132 = this.searchRunnable;
        if (r132 == null) goto L23;
        AndroidUtilities.cancelRunOnUIThread(r132);
    L23:
        AndroidUtilities.cancelRunOnUIThread(this.clearCurrentResultsRunnable);
        if (r03 == false) goto L27;
        if (r32 == false) goto L27;
        return;
    L27:
        long r17 = 0;
        if (r142 == true) goto L45;
        if (r29 == null) goto L31;
    L39:
        if (r32 == true) goto L41;
    L43:
        boolean r19 = r03;
    L54:
        this.isLoading = true;
        RecyclerView.Adapter r04 = this.adapter;
        if (r04 == null) goto L57;
        r04.notifyDataSetChanged();
    L57:
        if (r19 == true) goto L60;
        this.clearCurrentResultsRunnable.run();
        this.emptyView.showProgress(true, !r32);
    L60:
        if (TextUtils.isEmpty(r18) == false) goto L64;
        this.localTipDates.clear();
        this.localTipChats.clear();
        Delegate r05 = this.delegate;
        if (r05 == null) goto L64;
        r05.updateFiltersView(false, null, null, false);
    L64:
        int r06 = this.requestIndex + 1;
        this.requestIndex = r06;
        FilteredSearchView$$ExternalSyntheticLambda3 r07 = new FilteredSearchView$$ExternalSyntheticLambda3(this, r21, r23, r18, r29, UserConfig.selectedAccount, r25, r27, r19, r30, r152, r06);
        this.searchRunnable = r07;
        if (r19 == true) goto L67;
    L69:
        r17 = 350;
    L70:
        AndroidUtilities.runOnUIThread(r07, r17);
        if (r29 != null) goto L74;
        this.loadingView.setViewType(1);
        return;
    L74:
        int r08 = r29.filterType;
        if (r08 != 0) goto L83;
        boolean r09 = TextUtils.isEmpty(this.currentSearchString);
        FlickerLoadingView r1 = this.loadingView;
        if (r09 == true) goto L80;
        r1.setViewType(1);
        return;
    L80:
        r1.setViewType(2);
        return;
    L83:
        if (r08 != 1) goto L86;
        this.loadingView.setViewType(3);
        return;
    L86:
        if (r08 != 3) goto L88;
    L93:
        this.loadingView.setViewType(4);
        return;
    L88:
        if (r08 == 5) goto L93;
        if (r08 != 2) goto L95;
        this.loadingView.setViewType(5);
        return;
    L95:
        return;
    L67:
        if (this.messages.isEmpty() == true) goto L69;
    L41:
        if (this.messages.isEmpty() == true) goto L43;
        return;
    L31:
        if (r23 != 0) goto L39;
        if (r21 != 0) goto L39;
        if (r25 != 0) goto L39;
        if (r27 != 0) goto L39;
    L45:
        this.messages.clear();
        this.sections.clear();
        this.sectionArrays.clear();
        this.isLoading = true;
        r19 = r03;
        this.emptyView.setVisibility(0);
        RecyclerView.Adapter r010 = this.adapter;
        if (r010 == null) goto L48;
        r010.notifyDataSetChanged();
    L48:
        this.requestIndex++;
        this.firstLoading = true;
        if (this.recyclerListView.getPinnedHeader() == null) goto L51;
        this.recyclerListView.getPinnedHeader().setAlpha(0.0f);
    L51:
        this.localTipChats.clear();
        this.localTipDates.clear();
        if (r142 == true) goto L54;
        return;
    L19:
        r142 = false;
        goto L20
    L13:
        if (r02.equals(r152) == false) goto L15;
        r03 = true;
        goto L16
    L9:
        r8 = r29.filterType;
        goto L10
    L5:
        r6 = r31;
        goto L6
    }

    public void setBlurredBackgroundDrawableFactory(BlurredBackgroundDrawableViewFactory r2) {
        this.blurredBackgroundDrawableFactory = r2;
        FloatingDateView r1 = this.floatingDateView;
        r1.setBlurredBackgroundDrawable(r2.create(r1, BlurredBackgroundProviderImpl.searchFloatingDate(null)));
    }

    public void setChatPreviewDelegate(SearchViewPager.ChatPreviewDelegate r1) {
        this.chatPreviewDelegate = r1;
    }

    public void setDelegate(Delegate r3, boolean r4) {
        this.delegate = r3;
        if (r4 == false) goto L9;
        if (r3 != null) goto L6;
        return;
    L6:
        if (this.localTipChats.isEmpty() == true) goto L11;
        r3.updateFiltersView(false, this.localTipChats, this.localTipDates, this.localTipArchive);
        return;
    L11:
        return;
    }

    public void setKeyboardHeight(int r1, boolean r2) {
        this.emptyView.setKeyboardHeight(r1, r2);
    }

    public void setPagesPaddings(int r2, int r3) {
        setPagesPaddings(r2, r3, false);
    }

    public void setPagesPaddings(int r8, int r9, boolean r10) {
        setClipToPadding(false);
        this.ignoreRequestLayout = r10;
        setPadding(0, r8, 0, r9);
        this.recyclerListView.setPadding(0, r8, 0, r9, r10);
        ViewGroup.MarginLayoutParams r82 = (ViewGroup.MarginLayoutParams) this.recyclerListView.getLayoutParams();
        r82.topMargin = -r8;
        r82.bottomMargin = -r9;
        this.ignoreRequestLayout = false;
    }

    public void setUiCallback(UiCallback r1) {
        this.uiCallback = r1;
    }

    public void setUseFromUserAsAvatar(boolean r1) {
        this.useFromUserAsAvatar = r1;
    }

    public void update() {
        RecyclerView.Adapter r0 = this.adapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }
}
