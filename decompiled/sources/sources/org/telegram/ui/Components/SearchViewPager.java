package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.AnimatedVectorDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.OvershootInterpolator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.MenuDrawable;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Adapters.DialogsSearchAdapter;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Cells.ContextLinkCell;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.HashtagSearchCell;
import org.telegram.ui.Cells.ProfileSearchCell;
import org.telegram.ui.Cells.SharedAudioCell;
import org.telegram.ui.Cells.SharedDocumentCell;
import org.telegram.ui.Cells.SharedLinkCell;
import org.telegram.ui.Cells.SharedPhotoVideoCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.capture.IBlur3Capture;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.DialogsActivity;
import org.telegram.ui.FilteredSearchView;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.ReportBottomSheet;
import org.telegram.ui.SearchAdsInfoBottomSheet;
import org.telegram.ui.TopicsFragment;

/* loaded from: classes3.dex */
public abstract class SearchViewPager extends ViewPagerFixed implements FilteredSearchView.UiCallback, NotificationCenter.NotificationCenterDelegate, IBlur3Capture {
    private ActionBarMenu actionMode;
    private ImageView actionModeCloseView;
    int animateFromCount;
    private boolean attached;
    BlurredBackgroundDrawableViewFactory blurredBackgroundDrawableFactory;
    public StickerEmptyView botsEmptyView;
    private DefaultItemAnimator botsItemAnimator;
    public DialogsBotsAdapter botsSearchAdapter;
    public FrameLayout botsSearchContainer;
    private LinearLayoutManager botsSearchLayoutManager;
    public final RecyclerListView botsSearchListView;
    public StickerEmptyView channelsEmptyView;
    private DefaultItemAnimator channelsItemAnimator;
    public DialogsChannelsAdapter channelsSearchAdapter;
    public FrameLayout channelsSearchContainer;
    private LinearLayoutManager channelsSearchLayoutManager;
    public final RecyclerListView channelsSearchListView;
    private boolean channelsUpdatePending;
    ChatPreviewDelegate chatPreviewDelegate;
    private final long communityId;
    int currentAccount;
    private ArrayList<FiltersView.MediaFilterData> currentSearchFilters;
    private ActionBarMenuItem deleteItem;
    public DialogsSearchAdapter dialogsSearchAdapter;
    private SearchDownloadsContainer downloadsContainer;
    public StickerEmptyView emptyView;
    public boolean expandedPublicPosts;
    private FilteredSearchView.Delegate filteredSearchViewDelegate;
    private final int folderId;
    private ActionBarMenuItem forwardItem;
    SizeNotifierFrameLayout fragmentView;
    private ActionBarMenuItem gotoItem;
    public StickerEmptyView hashtagEmptyView;
    private DefaultItemAnimator hashtagItemAnimator;
    public HashtagsSearchAdapter hashtagSearchAdapter;
    public FrameLayout hashtagSearchContainer;
    private LinearLayoutManager hashtagSearchLayoutManager;
    public final RecyclerListView hashtagSearchListView;
    private boolean isActionModeShowed;
    private DefaultItemAnimator itemAnimator;
    private RecyclerItemsEnterAnimator itemsEnterAnimator;
    private int keyboardSize;
    private boolean lastSearchScrolledToTop;
    String lastSearchString;
    private FilteredSearchView noMediaFiltersSearchView;
    private NotificationCenter.ObserversGroup observersGroup;
    private int pagesPaddingBottom;
    private int pagesPaddingTop;
    DialogsActivity parent;
    public boolean postsAreNew;
    public final PostsSearchContainer postsSearchContainer;
    public FrameLayout searchContainer;
    private LinearLayoutManager searchLayoutManager;
    public final RecyclerListView searchListView;
    private HashMap<FilteredSearchView.MessageHashId, MessageObject> selectedFiles;
    private NumberTextView selectedMessagesCountTextView;
    private boolean showOnlyDialogsAdapter;
    private ActionBarMenuItem speedItem;
    protected final ViewPagerAdapter viewPagerAdapter;

    /* renamed from: org.telegram.ui.Components.SearchViewPager$1, reason: invalid class name */
    public class AnonymousClass1 extends DialogsSearchAdapter {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ DialogsActivity val$fragment;

        public static /* synthetic */ void $r8$lambda$7eruK5M0XKGAnE7FmYElmQ8M5iM(AnonymousClass1 r0, DialogsActivity r1, Context r2, ItemOptions r3) {
            r0.lambda$openSponsoredOptions$3(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$970x6bjlkoPj0NkiO2Wfkf1SJb0(AnonymousClass1 r0, DialogsActivity r1, TLRPC.TL_sponsoredPeer r2, ItemOptions r3) {
            r0.lambda$openSponsoredOptions$5(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$F-qtStNG4NaqM0qiSC5YSeMx-xQ, reason: not valid java name */
        public static /* synthetic */ void m12604$r8$lambda$FqtStNG4NaqM0qiSC5YSeMxxQ(AnonymousClass1 r0, DialogsActivity r1) {
            r0.lambda$openSponsoredOptions$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$_oaULIgpVgZrQGnDuHp97jjBh4E(ItemOptions r0, ItemOptions r1) {
            r0.openSwipeback(r1);
        }

        public static /* synthetic */ void $r8$lambda$aha4vRySm2BRhxpvNhIbmgqCEEI(AnonymousClass1 r0, TLRPC.TL_sponsoredPeer r1) {
            r0.lambda$openSponsoredOptions$4(r1);
        }

        /* renamed from: $r8$lambda$et2SdWptKt73-L8vg58EOKkCblI, reason: not valid java name */
        public static /* synthetic */ void m12605$r8$lambda$et2SdWptKt73L8vg58EOKkCblI(ItemOptions r0) {
            r0.closeSwipeback();
        }

        /* renamed from: $r8$lambda$xY5V61DrBR07u4lVn_-RTqbAY64, reason: not valid java name */
        public static /* synthetic */ void m12606$r8$lambda$xY5V61DrBR07u4lVn_RTqbAY64(AnonymousClass1 r0, DialogsActivity r1, ItemOptions r2) {
            r0.lambda$openSponsoredOptions$6(r1, r2);
        }

        public AnonymousClass1(SearchViewPager r1, Context r2, DialogsActivity r3, int r4, int r5, DefaultItemAnimator r6, boolean r7, Theme.ResourcesProvider r8, DialogsActivity r9, Context r10) {
            this.this$0 = r1;
            this.val$fragment = r9;
            this.val$context = r10;
            super(r2, r3, r4, r5, r6, r7, r8);
        }

        private /* synthetic */ void lambda$openSponsoredOptions$2(DialogsActivity r1) {
            removeAllAds();
            BulletinFactory.of(r1).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        }

        private /* synthetic */ void lambda$openSponsoredOptions$3(DialogsActivity r4, Context r5, ItemOptions r6) {
            r4.showDialog(new SearchAdsInfoBottomSheet(r5, r4.getResourceProvider(), new SearchViewPager$1$$ExternalSyntheticLambda6(this, r4)));
            r6.dismiss();
        }

        private /* synthetic */ void lambda$openSponsoredOptions$4(TLRPC.TL_sponsoredPeer r1) {
            removeAd(r1);
        }

        private /* synthetic */ void lambda$openSponsoredOptions$5(DialogsActivity r4, TLRPC.TL_sponsoredPeer r5, ItemOptions r6) {
            ReportBottomSheet.openSponsoredPeer(r4, r5.random_id, r4.getResourceProvider(), new SearchViewPager$1$$ExternalSyntheticLambda5(this, r5));
            r6.dismiss();
        }

        private /* synthetic */ void lambda$openSponsoredOptions$6(DialogsActivity r3, ItemOptions r4) {
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L5;
            r3.getMessagesController().disableAds(true);
            removeAllAds();
            BulletinFactory.of(r3).createAdReportedBulletin(LocaleController.getString(R.string.AdHidden)).show();
        L6:
            r4.dismiss();
            return;
        L5:
            new PremiumFeatureBottomSheet(r3, 3, true).show();
            goto L6
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void notifyDataSetChanged() {
            int r0 = getCurrentItemCount();
            super.notifyDataSetChanged();
            if (SearchViewPager.m12595$$Nest$fgetlastSearchScrolledToTop(this.this$0) == true) goto L8;
            RecyclerListView r1 = this.this$0.searchListView;
            if (r1 == null) goto L8;
            r1.scrollToPosition(0);
            SearchViewPager.m12602$$Nest$fputlastSearchScrolledToTop(this.this$0, true);
        L8:
            if (getItemCount() != 0) goto L14;
            if (r0 != 0) goto L11;
            return;
        L11:
            if (isSearching() == true) goto L16;
            this.this$0.emptyView.showProgress(false, false);
            return;
        L16:
            return;
        }

        @Override // org.telegram.ui.Adapters.DialogsSearchAdapter
        public void openBotApp(TLRPC.User r3) {
            if (r3 != null) goto L5;
            return;
        L5:
            if (this.this$0.parent == null) goto L7;
            this.this$0.parent.closeSearching();
        L7:
            MessagesController.getInstance(this.this$0.currentAccount).openApp(r3, 0);
            putRecentSearch(r3.id, r3);
        }

        @Override // org.telegram.ui.Adapters.DialogsSearchAdapter
        public void openPublicPosts() {
            SearchViewPager r0 = this.this$0;
            HashtagsSearchAdapter r1 = r0.hashtagSearchAdapter;
            DialogsSearchAdapter r02 = r0.dialogsSearchAdapter;
            r1.setInitialData(r02.publicPostsHashtag, r02.publicPosts, r02.publicPostsLastRate, r02.publicPostsTotalCount);
            SearchViewPager r03 = this.this$0;
            r03.expandedPublicPosts = true;
            SearchViewPager.m12593$$Nest$fgethashtagSearchLayoutManager(r03).scrollToPositionWithOffset(0, 0);
            this.this$0.updateTabs();
            ViewPagerFixed.TabsView r04 = this.this$0.tabsView;
            if (r04 != null) goto L5;
        L7:
            SearchViewPager r5 = this.this$0;
            r5.hashtagSearchAdapter.search(r5.lastSearchString);
            return;
        L5:
            if (r04.getCurrentTabId() == 1) goto L7;
            this.this$0.tabsView.scrollToTab(1, 1);
            goto L7
        }

        @Override // org.telegram.ui.Adapters.DialogsSearchAdapter
        public void openSponsoredOptions(ProfileSearchCell r6, TLRPC.TL_sponsoredPeer r7) {
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            ItemOptions r62 = ItemOptions.makeOptions(this.val$fragment, r6, true);
            if (TextUtils.isEmpty(r7.sponsor_info) == true) goto L5;
        L6:
            ItemOptions r0 = r62.makeSwipeback().add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new SearchViewPager$1$$ExternalSyntheticLambda0(r62)).addGap();
            if (TextUtils.isEmpty(r7.sponsor_info) == true) goto L10;
            r0.addText(r7.sponsor_info, 13);
        L10:
            if (TextUtils.isEmpty(r7.additional_info) == false) goto L12;
        L15:
            r62.add(R.drawable.msg_channel, LocaleController.getString(R.string.SponsoredMessageSponsorReportable), new SearchViewPager$1$$ExternalSyntheticLambda1(r62, r0));
        L16:
            ItemOptions r5 = r62.add(R.drawable.msg_info, LocaleController.getString(R.string.AboutRevenueSharingAds), new SearchViewPager$1$$ExternalSyntheticLambda2(this, this.val$fragment, this.val$context, r62)).add(R.drawable.msg_block2, LocaleController.getString(R.string.ReportAd), new SearchViewPager$1$$ExternalSyntheticLambda3(this, this.val$fragment, r7, r62)).addGap().add(R.drawable.msg_cancel, LocaleController.getString(R.string.RemoveAds), new SearchViewPager$1$$ExternalSyntheticLambda4(this, this.val$fragment, r62));
            if (LocaleController.isRTL == false) goto L19;
            int r63 = 3;
        L20:
            r5.setGravity(r63).setOnTopOfScrim().setDrawScrim(false).show();
            return;
        L19:
            r63 = 5;
            goto L20
        L12:
            if (TextUtils.isEmpty(r7.sponsor_info) == true) goto L14;
            r0.addGap();
        L14:
            r0.addText(r7.additional_info, 13);
            goto L15
        L5:
            if (TextUtils.isEmpty(r7.additional_info) == true) goto L16;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$10, reason: invalid class name */
    public class AnonymousClass10 extends DefaultItemAnimator {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass10(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$11, reason: invalid class name */
    public class AnonymousClass11 extends StickerEmptyView {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass11(SearchViewPager r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView, android.view.View
        public void setVisibility(int r2) {
            if (SearchViewPager.m12596$$Nest$fgetnoMediaFiltersSearchView(this.this$0).getTag() == null) goto L6;
            super.setVisibility(8);
            return;
        L6:
            super.setVisibility(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$12, reason: invalid class name */
    public class AnonymousClass12 extends DialogsBotsAdapter {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass12(SearchViewPager r1, RecyclerListView r2, Context r3, int r4, int r5, boolean r6, Theme.ResourcesProvider r7, DialogsActivity r8) {
            this.this$0 = r1;
            this.val$fragment = r8;
            super(r2, r3, r4, r5, r6, r7);
        }

        @Override // org.telegram.ui.Components.UniversalAdapter
        public void update(boolean r3) {
            super.update(r3);
            StickerEmptyView r0 = this.this$0.botsEmptyView;
            if (this.loadingMessages == false) goto L5;
        L12:
            boolean r1 = true;
        L13:
            r0.showProgress(r1, r3);
            this.this$0.botsEmptyView.title.setText(LocaleController.getString(R.string.NoResult));
            this.this$0.botsEmptyView.subtitle.setVisibility(8);
            return;
        L5:
            if (this.loadingBots == true) goto L12;
            ArrayList<MessageObject> r12 = this.searchMessages;
            if (r12 == null) goto L12;
            if (r12.isEmpty() == false) goto L12;
            r1 = false;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$13, reason: invalid class name */
    public class AnonymousClass13 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass13(SearchViewPager r1, DialogsActivity r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.botsSearchAdapter.checkBottom();
            this.this$0.onPageScrolled(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$14, reason: invalid class name */
    public class AnonymousClass14 extends DefaultItemAnimator {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass14(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$15, reason: invalid class name */
    public class AnonymousClass15 extends StickerEmptyView {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass15(SearchViewPager r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView, android.view.View
        public void setVisibility(int r2) {
            if (SearchViewPager.m12596$$Nest$fgetnoMediaFiltersSearchView(this.this$0).getTag() == null) goto L6;
            super.setVisibility(8);
            return;
        L6:
            super.setVisibility(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$16, reason: invalid class name */
    public class AnonymousClass16 extends HashtagsSearchAdapter {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass16(SearchViewPager r1, RecyclerListView r2, Context r3, int r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.HashtagsSearchAdapter
        public void scrollToTop(boolean r2) {
            if (r2 == true) goto L4;
        L6:
            SearchViewPager.m12593$$Nest$fgethashtagSearchLayoutManager(this.this$0).scrollToPositionWithOffset(0, 0);
            return;
        L4:
            if (this.this$0.hashtagSearchListView.canScrollVertically(-1) == false) goto L6;
        }

        @Override // org.telegram.ui.Components.UniversalAdapter
        public void update(boolean r3) {
            super.update(r3);
            this.this$0.hashtagEmptyView.showProgress(false, r3);
            this.this$0.hashtagEmptyView.title.setText(LocaleController.getString(R.string.NoResult));
            this.this$0.hashtagEmptyView.subtitle.setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$17, reason: invalid class name */
    public class AnonymousClass17 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass17(SearchViewPager r1, DialogsActivity r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.hashtagSearchAdapter.checkBottom();
            this.this$0.onPageScrolled(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$18, reason: invalid class name */
    public class AnonymousClass18 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass18(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            this.this$0.onPageScrolled(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$19, reason: invalid class name */
    /* loaded from: classes7.dex */
    public class AnonymousClass19 extends AnimatorListenerAdapter {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass19(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            SearchViewPager.m12596$$Nest$fgetnoMediaFiltersSearchView(this.this$0).setVisibility(8);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$2, reason: invalid class name */
    public class AnonymousClass2 extends RecyclerListView {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass2(SearchViewPager r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            SearchViewPager r0 = this.this$0;
            if (r0.dialogsSearchAdapter != null) goto L5;
        L17:
            super.dispatchDraw(r5);
            SearchViewPager r02 = this.this$0;
            if (r02.dialogsSearchAdapter != null) goto L20;
        L26:
            DialogsSearchAdapter r03 = this.this$0.dialogsSearchAdapter;
            if (r03 != null) goto L29;
            return;
        L29:
            if (r03.showMoreHeader == null) goto L35;
            r5.save();
            r5.translate(this.this$0.dialogsSearchAdapter.showMoreHeader.getLeft(), this.this$0.dialogsSearchAdapter.showMoreHeader.getTop() + this.this$0.dialogsSearchAdapter.showMoreHeader.getTranslationY());
            this.this$0.dialogsSearchAdapter.showMoreHeader.draw(r5);
            r5.restore();
            return;
        L35:
            return;
        L20:
            if (SearchViewPager.m12594$$Nest$fgetitemAnimator(r02) == null) goto L26;
            if (SearchViewPager.m12599$$Nest$fgetsearchLayoutManager(this.this$0) == null) goto L26;
            if (this.this$0.dialogsSearchAdapter.showMoreAnimation == false) goto L26;
            r5.restore();
            goto L26
        L5:
            if (SearchViewPager.m12594$$Nest$fgetitemAnimator(r0) == null) goto L17;
            if (SearchViewPager.m12599$$Nest$fgetsearchLayoutManager(this.this$0) == null) goto L17;
            if (this.this$0.dialogsSearchAdapter.showMoreAnimation == false) goto L17;
            r5.save();
            invalidate();
            int r04 = this.this$0.dialogsSearchAdapter.getItemCount() - 1;
            int r1 = 0;
        L12:
            if (r1 >= getChildCount()) goto L17;
            View r2 = getChildAt(r1);
            if (getChildAdapterPosition(r2) == r04) goto L15;
            r1 = r1 + 1;
            goto L12
        L15:
            r5.clipRect(0.0f, 0.0f, getWidth(), r2.getBottom() + r2.getTranslationY());
            goto L17
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass3(SearchViewPager r1, DialogsActivity r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r7, int r8, int r9) {
            int r0 = SearchViewPager.m12599$$Nest$fgetsearchLayoutManager(this.this$0).findFirstVisibleItemPosition();
            int r1 = SearchViewPager.m12599$$Nest$fgetsearchLayoutManager(this.this$0).findLastVisibleItemPosition();
            int r2 = Math.abs(SearchViewPager.m12599$$Nest$fgetsearchLayoutManager(this.this$0).findLastVisibleItemPosition() - r0) + 1;
            int r72 = r7.getAdapter().getItemCount();
            if (r2 > 0) goto L5;
        L17:
            this.this$0.onPageScrolled(r8, r9);
            return;
        L5:
            if (this.this$0.dialogsSearchAdapter.isMessagesSearchEndReached() == true) goto L17;
            if (r1 == (r72 - 1)) goto L16;
            DialogsSearchAdapter.DialogsSearchAdapterDelegate r73 = this.this$0.dialogsSearchAdapter.delegate;
            if (r73 == null) goto L17;
            if (r73.getSearchForumDialogId() == 0) goto L17;
            int r74 = this.this$0.dialogsSearchAdapter.localMessagesLoadingRow;
            if (r74 < 0) goto L17;
            if (r0 > r74) goto L17;
            if (r1 < r74) goto L17;
        L16:
            this.this$0.dialogsSearchAdapter.loadMoreSearchMessages();
            goto L17
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass4(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            this.this$0.onPageScrolled(r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$5, reason: invalid class name */
    public class AnonymousClass5 extends StickerEmptyView {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass5(SearchViewPager r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView, android.view.View
        public void setVisibility(int r2) {
            if (SearchViewPager.m12596$$Nest$fgetnoMediaFiltersSearchView(this.this$0).getTag() == null) goto L6;
            super.setVisibility(8);
            return;
        L6:
            super.setVisibility(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$6, reason: invalid class name */
    public class AnonymousClass6 extends DefaultItemAnimator {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass6(SearchViewPager r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
            super.onMoveAnimationUpdate(r1);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$7, reason: invalid class name */
    public class AnonymousClass7 extends StickerEmptyView {
        final /* synthetic */ SearchViewPager this$0;

        public AnonymousClass7(SearchViewPager r1, Context r2, View r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.StickerEmptyView, android.view.View
        public void setVisibility(int r2) {
            if (SearchViewPager.m12596$$Nest$fgetnoMediaFiltersSearchView(this.this$0).getTag() == null) goto L6;
            super.setVisibility(8);
            return;
        L6:
            super.setVisibility(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$8, reason: invalid class name */
    public class AnonymousClass8 extends DialogsChannelsAdapter {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass8(SearchViewPager r1, RecyclerListView r2, Context r3, int r4, int r5, Theme.ResourcesProvider r6, DialogsActivity r7) {
            this.this$0 = r1;
            this.val$fragment = r7;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Components.DialogsChannelsAdapter
        public void hideKeyboard() {
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
        }

        @Override // org.telegram.ui.Components.UniversalAdapter
        public void update(boolean r4) {
            super.update(r4);
            StickerEmptyView r0 = this.this$0.channelsEmptyView;
            if (this.loadingMessages == false) goto L5;
        L24:
            boolean r1 = true;
        L25:
            r0.showProgress(r1, r4);
            boolean r42 = TextUtils.isEmpty(this.query);
            SearchViewPager r02 = this.this$0;
            if (r42 == false) goto L29;
            r02.channelsEmptyView.title.setText(LocaleController.getString(R.string.NoChannelsTitle));
            this.this$0.channelsEmptyView.subtitle.setVisibility(0);
            this.this$0.channelsEmptyView.subtitle.setText(LocaleController.getString(R.string.NoChannelsMessage));
            return;
        L29:
            r02.channelsEmptyView.title.setText(LocaleController.getString(R.string.NoResult));
            this.this$0.channelsEmptyView.subtitle.setVisibility(8);
            return;
        L5:
            if (this.loadingChannels == true) goto L24;
            ArrayList<MessageObject> r12 = this.messages;
            if (r12 == null) goto L24;
            if (r12.isEmpty() == false) goto L24;
            ArrayList<TLRPC.Chat> r13 = this.searchMyChannels;
            if (r13 == null) goto L24;
            if (r13.isEmpty() == false) goto L24;
            ArrayList<TLRPC.Chat> r14 = this.searchChannels;
            if (r14 == null) goto L24;
            if (r14.isEmpty() == false) goto L24;
            ArrayList<TLRPC.Chat> r15 = this.searchRecommendedChannels;
            if (r15 == null) goto L24;
            if (r15.isEmpty() == false) goto L24;
            r1 = false;
            goto L25
        }
    }

    /* renamed from: org.telegram.ui.Components.SearchViewPager$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SearchViewPager this$0;
        final /* synthetic */ DialogsActivity val$fragment;

        public AnonymousClass9(SearchViewPager r1, DialogsActivity r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r1, int r2) {
            if (r2 != 1) goto L6;
            AndroidUtilities.hideKeyboard(this.val$fragment.getParentActivity().getCurrentFocus());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.channelsSearchAdapter.checkBottom();
            this.this$0.onPageScrolled(r2, r3);
        }
    }

    public interface ChatPreviewDelegate {
        void finish();

        void move(float r1);

        void startChatPreview(RecyclerListView r1, DialogCell r2);
    }

    public class ViewPagerAdapter extends ViewPagerFixed.Adapter {
        ArrayList<Item> items;
        final /* synthetic */ SearchViewPager this$0;

        /* renamed from: org.telegram.ui.Components.SearchViewPager$ViewPagerAdapter$1, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass1 extends RecyclerView.OnScrollListener {
            final /* synthetic */ ViewPagerAdapter this$1;

            public AnonymousClass1(ViewPagerAdapter r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                super.onScrolled(r1, r2, r3);
                this.this$1.this$0.onPageScrolled(r2, r3);
            }
        }

        /* renamed from: org.telegram.ui.Components.SearchViewPager$ViewPagerAdapter$2, reason: invalid class name */
        /* loaded from: classes7.dex */
        public class AnonymousClass2 extends RecyclerView.OnScrollListener {
            final /* synthetic */ ViewPagerAdapter this$1;

            public AnonymousClass2(ViewPagerAdapter r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                super.onScrolled(r1, r2, r3);
                this.this$1.this$0.onPageScrolled(r2, r3);
            }
        }

        public class Item {
            int filterIndex;
            final /* synthetic */ ViewPagerAdapter this$1;
            private final int type;

            /* renamed from: -$$Nest$fgettype, reason: not valid java name */
            public static /* bridge */ /* synthetic */ int m12607$$Nest$fgettype(Item r0) {
                return r0.type;
            }

            private Item(ViewPagerAdapter r1, int r2) {
                this.this$1 = r1;
                this.type = r2;
            }

            public /* synthetic */ Item(ViewPagerAdapter r1, int r2, SearchViewPagerIA r3) {
                this(r1, r2);
            }
        }

        public ViewPagerAdapter(SearchViewPager r1) {
            this.this$0 = r1;
            this.items = new ArrayList();
            updateItems();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r2, int r3, int r4) {
            SearchViewPager r1 = this.this$0;
            SearchViewPager.m12603$$Nest$msearch(r1, r2, r3, r1.lastSearchString, true);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r5) {
            if (r5 != 1) goto L7;
            return this.this$0.searchContainer;
        L7:
            if (r5 != 3) goto L11;
            return this.this$0.channelsSearchContainer;
        L11:
            if (r5 != 4) goto L15;
            return this.this$0.botsSearchContainer;
        L15:
            if (r5 != 5) goto L19;
            return this.this$0.hashtagSearchContainer;
        L19:
            if (r5 != 2) goto L23;
            SearchViewPager r52 = this.this$0;
            SearchViewPager r2 = this.this$0;
            SearchViewPager.m12601$$Nest$fputdownloadsContainer(r52, new SearchDownloadsContainer(r2.parent, r2.currentAccount));
            SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0).setPagesPaddings(SearchViewPager.m12598$$Nest$fgetpagesPaddingTop(this.this$0), SearchViewPager.m12597$$Nest$fgetpagesPaddingBottom(this.this$0));
            SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0).recyclerListView.setClipToPadding(false);
            SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0).recyclerListView.addOnScrollListener(new AnonymousClass1(this));
            SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0).recyclerListView.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this.this$0));
            SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0).setUiCallback(this.this$0);
            return SearchViewPager.m12592$$Nest$fgetdownloadsContainer(this.this$0);
        L23:
            if (r5 == 6) goto L25;
            FilteredSearchView r53 = new FilteredSearchView(this.this$0.parent);
            r53.setChatPreviewDelegate(this.this$0.chatPreviewDelegate);
            r53.setUiCallback(this.this$0);
            r53.setPagesPaddings(SearchViewPager.m12598$$Nest$fgetpagesPaddingTop(this.this$0), SearchViewPager.m12597$$Nest$fgetpagesPaddingBottom(this.this$0));
            BlurredBackgroundDrawableViewFactory r0 = this.this$0.blurredBackgroundDrawableFactory;
            if (r0 == null) goto L29;
            r53.setBlurredBackgroundDrawableFactory(r0);
        L29:
            r53.recyclerListView.setClipToPadding(false);
            r53.recyclerListView.addOnScrollListener(new AnonymousClass2(this));
            r53.recyclerListView.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this.this$0));
            return r53;
        L25:
            return this.this$0.postsSearchContainer;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return this.items.size();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public CharSequence getItemTitle(int r3) {
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 0) goto L7;
            return LocaleController.getString(R.string.SearchAllChatsShort);
        L7:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 1) goto L11;
            return LocaleController.getString(R.string.ChannelsTab);
        L11:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 4) goto L15;
            return LocaleController.getString(R.string.AppsTab);
        L15:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 6) goto L23;
            if (this.this$0.postsAreNew == false) goto L21;
            return PremiumPreviewFragment.applyNewSpan(LocaleController.getString(R.string.SearchPosts));
        L21:
            return LocaleController.getString(R.string.SearchPosts);
        L23:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 2) goto L27;
            return LocaleController.getString(R.string.DownloadsTabs);
        L27:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 5) goto L31;
            return LocaleController.getString(R.string.PublicPostsTabs);
        L31:
            return FiltersView.filters[this.items.get(r3).filterIndex].getTitle();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r3) {
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 0) goto L6;
            return 1;
        L6:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 1) goto L10;
            return 3;
        L10:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 4) goto L13;
            return 4;
        L13:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 2) goto L16;
            return 2;
        L16:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 5) goto L19;
            return 5;
        L19:
            if (Item.m12607$$Nest$fgettype(this.items.get(r3)) != 6) goto L22;
            return 6;
        L22:
            return r3 + 10;
        }

        public void updateItems() {
            this.items.clear();
            this.items.add(new Item(this, 0, null));
            if (SearchViewPager.m12591$$Nest$fgetcommunityId(this.this$0) == 0) goto L6;
            return;
        L6:
            if (this.this$0.expandedPublicPosts == false) goto L8;
            this.items.add(new Item(this, 5, null));
        L8:
            this.items.add(new Item(this, 1, null));
            this.items.add(new Item(this, 4, null));
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L12;
            this.items.add(new Item(this, 6, null));
        L12:
            if (SearchViewPager.m12600$$Nest$fgetshowOnlyDialogsAdapter(this.this$0) == true) goto L18;
            Item r0 = new Item(this, 3, null);
            r0.filterIndex = 0;
            this.items.add(r0);
            if (this.this$0.includeDownloads() == false) goto L16;
            this.items.add(new Item(this, 2, null));
        L16:
            Item r02 = new Item(this, 3, null);
            r02.filterIndex = 1;
            this.items.add(r02);
            Item r03 = new Item(this, 3, null);
            r03.filterIndex = 2;
            this.items.add(r03);
            Item r04 = new Item(this, 3, null);
            r04.filterIndex = 3;
            this.items.add(r04);
            Item r05 = new Item(this, 3, null);
            r05.filterIndex = 4;
            this.items.add(r05);
            return;
        }
    }

    /* renamed from: $r8$lambda$-Z4XbrrTtC-lfximlzvzCaAR4oM, reason: not valid java name */
    public static /* synthetic */ void m12587$r8$lambda$Z4XbrrTtClfximlzvzCaAR4oM(SearchViewPager r0, ArrayList r1, AlertDialog r2, int r3) {
        r0.lambda$onActionBarItemClick$4(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$2_xlUEgcXpwvW-WiQ_uZAz9ZsdA, reason: not valid java name */
    public static /* synthetic */ void m12588$r8$lambda$2_xlUEgcXpwvWWiQ_uZAz9ZsdA(SearchViewPager r0, View r1) {
        r0.lambda$showActionMode$0(r1);
    }

    /* renamed from: $r8$lambda$DsTLGFPW5XcGywD5uya-g-X7wWg, reason: not valid java name */
    public static /* synthetic */ boolean m12589$r8$lambda$DsTLGFPW5XcGywD5uyagX7wWg(SearchViewPager r0, View r1) {
        return r0.lambda$showActionMode$2(r1);
    }

    /* renamed from: $r8$lambda$GLNsbdbEK-BQwTLWZFGCtMYDQYI, reason: not valid java name */
    public static /* synthetic */ void m12590$r8$lambda$GLNsbdbEKBQwTLWZFGCtMYDQYI(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ boolean $r8$lambda$HXzSiwlNEzD9EbeK7omJ49KuHak(SearchViewPager r0, boolean r1, DialogsActivity r2, ArrayList r3, CharSequence r4, boolean r5, boolean r6, int r7, int r8, TopicsFragment r9) {
        return r0.lambda$onActionBarItemClick$5(r1, r2, r3, r4, r5, r6, r7, r8, r9);
    }

    public static /* synthetic */ boolean $r8$lambda$IWDQng5sl7zUoo6zKCuKyGfo6VY(View r0, MotionEvent r1) {
        return true;
    }

    public static /* synthetic */ void $r8$lambda$wQ24JlVkAXQjq3yjeqn9D_Cuvg8(SearchViewPager r0) {
        r0.lambda$getThemeDescriptions$6();
    }

    /* renamed from: -$$Nest$fgetcommunityId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m12591$$Nest$fgetcommunityId(SearchViewPager r2) {
        return r2.communityId;
    }

    /* renamed from: -$$Nest$fgetdownloadsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SearchDownloadsContainer m12592$$Nest$fgetdownloadsContainer(SearchViewPager r0) {
        return r0.downloadsContainer;
    }

    /* renamed from: -$$Nest$fgethashtagSearchLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m12593$$Nest$fgethashtagSearchLayoutManager(SearchViewPager r0) {
        return r0.hashtagSearchLayoutManager;
    }

    /* renamed from: -$$Nest$fgetitemAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ DefaultItemAnimator m12594$$Nest$fgetitemAnimator(SearchViewPager r0) {
        return r0.itemAnimator;
    }

    /* renamed from: -$$Nest$fgetlastSearchScrolledToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12595$$Nest$fgetlastSearchScrolledToTop(SearchViewPager r0) {
        return r0.lastSearchScrolledToTop;
    }

    /* renamed from: -$$Nest$fgetnoMediaFiltersSearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilteredSearchView m12596$$Nest$fgetnoMediaFiltersSearchView(SearchViewPager r0) {
        return r0.noMediaFiltersSearchView;
    }

    /* renamed from: -$$Nest$fgetpagesPaddingBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12597$$Nest$fgetpagesPaddingBottom(SearchViewPager r0) {
        return r0.pagesPaddingBottom;
    }

    /* renamed from: -$$Nest$fgetpagesPaddingTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12598$$Nest$fgetpagesPaddingTop(SearchViewPager r0) {
        return r0.pagesPaddingTop;
    }

    /* renamed from: -$$Nest$fgetsearchLayoutManager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayoutManager m12599$$Nest$fgetsearchLayoutManager(SearchViewPager r0) {
        return r0.searchLayoutManager;
    }

    /* renamed from: -$$Nest$fgetshowOnlyDialogsAdapter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12600$$Nest$fgetshowOnlyDialogsAdapter(SearchViewPager r0) {
        return r0.showOnlyDialogsAdapter;
    }

    /* renamed from: -$$Nest$fputdownloadsContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12601$$Nest$fputdownloadsContainer(SearchViewPager r0, SearchDownloadsContainer r1) {
        r0.downloadsContainer = r1;
    }

    /* renamed from: -$$Nest$fputlastSearchScrolledToTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12602$$Nest$fputlastSearchScrolledToTop(SearchViewPager r0, boolean r1) {
        r0.lastSearchScrolledToTop = r1;
    }

    /* renamed from: -$$Nest$msearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12603$$Nest$msearch(SearchViewPager r0, View r1, int r2, String r3, boolean r4) {
        r0.search(r1, r2, r3, r4);
    }

    public SearchViewPager(Context r19, DialogsActivity r20, int r21, int r22, int r23, long r24, ChatPreviewDelegate r26) {
        super(r19);
        this.expandedPublicPosts = false;
        this.selectedFiles = new HashMap();
        this.currentSearchFilters = new ArrayList();
        this.currentAccount = UserConfig.selectedAccount;
        this.animateFromCount = 0;
        this.folderId = r23;
        this.communityId = r24;
        this.parent = r20;
        this.chatPreviewDelegate = r26;
        DefaultItemAnimator r0 = new DefaultItemAnimator();
        this.itemAnimator = r0;
        r0.setAddDuration(150);
        this.itemAnimator.setMoveDuration(350);
        this.itemAnimator.setChangeDuration(0);
        this.itemAnimator.setRemoveDuration(0);
        this.itemAnimator.setMoveInterpolator(new OvershootInterpolator(1.1f));
        this.itemAnimator.setTranslationInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.dialogsSearchAdapter = new AnonymousClass1(this, r19, r20, r21, r22, this.itemAnimator, r20.getAllowGlobalSearch(), null, r20, r19);
        if (r22 != 15) goto L9;
        ArrayList<TLRPC.Dialog> r02 = r20.getDialogsArray(this.currentAccount, r22, r23, true);
        ArrayList<Long> r2 = new ArrayList();
        int r4 = 0;
    L6:
        if (r4 >= r02.size()) goto L8;
        r2.add(Long.valueOf(r02.get(r4).id));
        r4 = r4 + 1;
        goto L6
    L8:
        this.dialogsSearchAdapter.setFilterDialogIds(r2);
    L9:
        this.fragmentView = (SizeNotifierFrameLayout) r20.getFragmentView();
        AnonymousClass2 r10 = new AnonymousClass2(this, r19);
        this.searchListView = r10;
        r10.setItemAnimator(this.itemAnimator);
        r10.setPivotY(0.0f);
        r10.setClipToPadding(false);
        r10.setAdapter(this.dialogsSearchAdapter);
        r10.setVerticalScrollBarEnabled(true);
        r10.setInstantClick(true);
        if (LocaleController.isRTL == false) goto L12;
        int r03 = 1;
    L13:
        r10.setVerticalScrollbarPosition(r03);
        LinearLayoutManager r04 = new LinearLayoutManager(r19, 1, false);
        this.searchLayoutManager = r04;
        r10.setLayoutManager(r04);
        r10.setAnimateEmptyView(true, 0);
        r10.setOnScrollListener(new AnonymousClass3(this, r20));
        r10.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        FilteredSearchView r05 = new FilteredSearchView(this.parent);
        this.noMediaFiltersSearchView = r05;
        r05.recyclerListView.setClipToPadding(false);
        this.noMediaFiltersSearchView.recyclerListView.addOnScrollListener(new AnonymousClass4(this));
        this.noMediaFiltersSearchView.recyclerListView.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        this.noMediaFiltersSearchView.setUiCallback(this);
        this.noMediaFiltersSearchView.setVisibility(8);
        this.noMediaFiltersSearchView.setChatPreviewDelegate(r26);
        FlickerLoadingView r06 = new FlickerLoadingView(r19);
        r06.setViewType(1);
        AnonymousClass5 r42 = new AnonymousClass5(this, r19, r06, 1);
        this.emptyView = r42;
        r42.title.setText(LocaleController.getString(R.string.NoResult));
        this.emptyView.subtitle.setVisibility(8);
        this.emptyView.setVisibility(8);
        this.emptyView.addView(r06, 0);
        this.emptyView.showProgress(true, false);
        FrameLayout r07 = new FrameLayout(r19);
        this.searchContainer = r07;
        r07.addView(this.emptyView);
        this.searchContainer.addView(r10);
        this.searchContainer.addView(this.noMediaFiltersSearchView);
        r10.setEmptyView(this.emptyView);
        this.channelsSearchContainer = new FrameLayout(r19);
        AnonymousClass6 r08 = new AnonymousClass6(this);
        this.channelsItemAnimator = r08;
        r08.setSupportsChangeAnimations(false);
        this.channelsItemAnimator.setDelayAnimations(false);
        DefaultItemAnimator r09 = this.channelsItemAnimator;
        CubicBezierInterpolator r12 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r09.setInterpolator(r12);
        this.channelsItemAnimator.setDurations(350);
        RecyclerListView r010 = new RecyclerListView(r19);
        this.channelsSearchListView = r010;
        r010.setItemAnimator(this.channelsItemAnimator);
        r010.setPivotY(0.0f);
        r010.setVerticalScrollBarEnabled(true);
        r010.setInstantClick(true);
        if (LocaleController.isRTL == false) goto L16;
        int r43 = 1;
    L17:
        r010.setVerticalScrollbarPosition(r43);
        LinearLayoutManager r44 = new LinearLayoutManager(r19, 1, false);
        this.channelsSearchLayoutManager = r44;
        r010.setLayoutManager(r44);
        r010.setAnimateEmptyView(true, 0);
        r010.setClipToPadding(false);
        FlickerLoadingView r45 = new FlickerLoadingView(r19);
        r45.setViewType(1);
        AnonymousClass7 r5 = new AnonymousClass7(this, r19, r45, 1);
        this.channelsEmptyView = r5;
        r5.title.setText(LocaleController.getString(R.string.NoResult));
        this.channelsEmptyView.subtitle.setVisibility(8);
        this.channelsEmptyView.setVisibility(8);
        this.channelsEmptyView.addView(r45, 0);
        this.channelsEmptyView.showProgress(true, false);
        this.channelsSearchContainer.addView(this.channelsEmptyView);
        this.channelsSearchContainer.addView(r010);
        r010.setEmptyView(this.channelsEmptyView);
        AnonymousClass8 r011 = new AnonymousClass8(this, r010, r19, this.currentAccount, r23, null, r20);
        this.channelsSearchAdapter = r011;
        r010.setAdapter(r011);
        r010.setOnScrollListener(new AnonymousClass9(this, r20));
        r010.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        this.botsSearchContainer = new FrameLayout(r19);
        AnonymousClass10 r012 = new AnonymousClass10(this);
        this.botsItemAnimator = r012;
        r012.setSupportsChangeAnimations(false);
        this.botsItemAnimator.setDelayAnimations(false);
        this.botsItemAnimator.setInterpolator(r12);
        this.botsItemAnimator.setDurations(350);
        RecyclerListView r25 = new RecyclerListView(r19);
        this.botsSearchListView = r25;
        r25.setItemAnimator(this.botsItemAnimator);
        r25.setPivotY(0.0f);
        r25.setClipToPadding(false);
        r25.setVerticalScrollBarEnabled(true);
        r25.setInstantClick(true);
        if (LocaleController.isRTL == false) goto L20;
        int r013 = 1;
    L21:
        r25.setVerticalScrollbarPosition(r013);
        LinearLayoutManager r014 = new LinearLayoutManager(r19, 1, false);
        this.botsSearchLayoutManager = r014;
        r25.setLayoutManager(r014);
        r25.setAnimateEmptyView(true, 0);
        FlickerLoadingView r015 = new FlickerLoadingView(r19);
        r015.setViewType(1);
        AnonymousClass11 r46 = new AnonymousClass11(this, r19, r015, 1);
        this.botsEmptyView = r46;
        r46.title.setText(LocaleController.getString(R.string.NoResult));
        this.botsEmptyView.subtitle.setVisibility(8);
        this.botsEmptyView.setVisibility(8);
        this.botsEmptyView.addView(r015, 0);
        this.botsEmptyView.showProgress(true, false);
        this.botsSearchContainer.addView(this.botsEmptyView);
        this.botsSearchContainer.addView(r25);
        r25.setEmptyView(this.botsEmptyView);
        AnonymousClass12 r016 = new AnonymousClass12(this, r25, r19, this.currentAccount, r23, false, null, r20);
        this.botsSearchAdapter = r016;
        r25.setAdapter(r016);
        r25.setOnScrollListener(new AnonymousClass13(this, r20));
        r25.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        this.hashtagSearchContainer = new FrameLayout(r19);
        AnonymousClass14 r017 = new AnonymousClass14(this);
        this.hashtagItemAnimator = r017;
        r017.setSupportsChangeAnimations(false);
        this.hashtagItemAnimator.setDelayAnimations(false);
        this.hashtagItemAnimator.setInterpolator(r12);
        this.hashtagItemAnimator.setDurations(350);
        RecyclerListView r27 = new RecyclerListView(r19);
        this.hashtagSearchListView = r27;
        r27.setItemAnimator(this.hashtagItemAnimator);
        r27.setPivotY(0.0f);
        r27.setVerticalScrollBarEnabled(true);
        r27.setInstantClick(true);
        if (LocaleController.isRTL == false) goto L24;
        int r018 = 1;
    L25:
        r27.setVerticalScrollbarPosition(r018);
        LinearLayoutManager r019 = new LinearLayoutManager(r19, 1, false);
        this.hashtagSearchLayoutManager = r019;
        r27.setLayoutManager(r019);
        r27.setAnimateEmptyView(true, 0);
        r27.setClipToPadding(false);
        FlickerLoadingView r020 = new FlickerLoadingView(r19);
        r020.setViewType(1);
        AnonymousClass15 r47 = new AnonymousClass15(this, r19, r020, 1);
        this.hashtagEmptyView = r47;
        r47.title.setText(LocaleController.getString(R.string.NoResult));
        this.hashtagEmptyView.subtitle.setVisibility(8);
        this.hashtagEmptyView.setVisibility(8);
        this.hashtagEmptyView.addView(r020, 0);
        this.hashtagEmptyView.showProgress(true, false);
        this.hashtagSearchContainer.addView(this.hashtagEmptyView);
        this.hashtagSearchContainer.addView(r27);
        r27.setEmptyView(this.hashtagEmptyView);
        AnonymousClass16 r021 = new AnonymousClass16(this, r27, r19, this.currentAccount, r23, null);
        this.hashtagSearchAdapter = r021;
        r27.setAdapter(r021);
        r27.setOnScrollListener(new AnonymousClass17(this, r20));
        r27.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        this.itemsEnterAnimator = new RecyclerItemsEnterAnimator(r10, true);
        this.postsAreNew = false;
        PostsSearchContainer r022 = new PostsSearchContainer(r19, r20);
        this.postsSearchContainer = r022;
        r022.listView.setClipToPadding(false);
        r022.listView.addOnScrollListener(new AnonymousClass18(this));
        r022.listView.addEdgeEffectListener(new SearchViewPager$$ExternalSyntheticLambda0(this));
        ViewPagerAdapter r023 = new ViewPagerAdapter(this);
        this.viewPagerAdapter = r023;
        setAdapter(r023);
        return;
    L24:
        r018 = 2;
        goto L25
    L20:
        r013 = 2;
        goto L21
    L16:
        r43 = 2;
        goto L17
    L12:
        r03 = 2;
        goto L13
    }

    public static BaseFragment createFragmentFromMessage(int r5, MessageObject r6) {
        Bundle r0 = new Bundle();
        long r1 = r6.getDialogId();
        if (DialogObject.isEncryptedDialog(r1) == false) goto L6;
        r0.putInt("enc_id", DialogObject.getEncryptedChatId(r1));
    L14:
        r0.putInt("message_id", r6.getId());
        return new ChatActivity(r0);
    L6:
        if (DialogObject.isUserDialog(r1) == false) goto L8;
        r0.putLong("user_id", r1);
        goto L14
    L8:
        TLRPC.Chat r52 = AccountInstance.getInstance(r5).getMessagesController().getChat(Long.valueOf(-r1));
        if (r52 != null) goto L11;
    L13:
        r0.putLong("chat_id", -r1);
        goto L14
    L11:
        if (r52.migrated_to == null) goto L13;
        r0.putLong("migrated_to", r1);
        r1 = -r52.migrated_to.channel_id;
        goto L13
    }

    private RecyclerListView getRecyclerViewFromPage(View r3) {
        if (r3 != null) goto L6;
        return null;
    L6:
        if (r3 != this.searchContainer) goto L10;
        return this.searchListView;
    L10:
        if (r3 != this.channelsSearchContainer) goto L14;
        return this.channelsSearchListView;
    L14:
        if (r3 != this.botsSearchContainer) goto L18;
        return this.botsSearchListView;
    L18:
        if (r3 == this.hashtagSearchContainer) goto L20;
        SearchDownloadsContainer r1 = this.downloadsContainer;
        if (r3 == r1) goto L24;
        PostsSearchContainer r2 = this.postsSearchContainer;
        if (r3 != r2) goto L30;
        return r2.listView;
    L30:
        if ((r3 instanceof FilteredSearchView) == true) goto L32;
        return null;
    L32:
        return ((FilteredSearchView) r3).recyclerListView;
    L24:
        return r1.recyclerListView;
    L20:
        return this.hashtagSearchListView;
    }

    private boolean isSpeedItemVisible() {
        return false;
    }

    private /* synthetic */ void lambda$getThemeDescriptions$6() {
        NumberTextView r1 = this.selectedMessagesCountTextView;
        if (r1 == null) goto L6;
        r1.setTextColor(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon));
        return;
    }

    private /* synthetic */ void lambda$onActionBarItemClick$4(ArrayList r1, AlertDialog r2, int r3) {
        r2.dismiss();
        this.parent.getDownloadController().deleteRecentFiles(r1);
        hideActionMode();
    }

    private /* synthetic */ boolean lambda$onActionBarItemClick$5(boolean r31, DialogsActivity r32, ArrayList r33, CharSequence r34, boolean r35, boolean r36, int r37, int r38, TopicsFragment r39) {
        boolean r5 = r31;
        ArrayList<MessageObject> r2 = new ArrayList();
        Iterator<FilteredSearchView.MessageHashId> r1 = this.selectedFiles.keySet().iterator();
    L4:
        if (r1.hasNext() == false) goto L6;
        r2.add(this.selectedFiles.get(r1.next()));
        goto L4
    L6:
        this.selectedFiles.clear();
        showActionMode(false);
        if (r33.size() <= 1) goto L9;
    L23:
        int r14 = 0;
    L25:
        if (r14 >= r33.size()) goto L31;
        long r3 = ((MessagesStorage.TopicKey) r33.get(r14)).dialogId;
        if (r34 == null) goto L29;
        long r16 = r3;
        AccountInstance.getInstance(this.currentAccount).getSendMessagesHelper().sendMessage(SendMessagesHelper.SendMessageParams.of(r34.toString(), r16, null, null, null, true, null, null, null, true, 0, 0, null, false));
    L30:
        AccountInstance.getInstance(this.currentAccount).getSendMessagesHelper().sendMessage(r2, r16, r5, false, true, 0, 0);
        r14 = r14 + 1;
        r5 = r31;
        goto L25
    L29:
        r16 = r3;
        goto L30
    L31:
        r32.finishFragment();
    L32:
        return true;
    L9:
        if (((MessagesStorage.TopicKey) r33.get(0)).dialogId == AccountInstance.getInstance(this.currentAccount).getUserConfig().getClientUserId()) goto L23;
        if (r34 != null) goto L23;
        long r310 = ((MessagesStorage.TopicKey) r33.get(0)).dialogId;
        Bundle r12 = new Bundle();
        r12.putBoolean("scrollToTopOnResume", true);
        if (DialogObject.isEncryptedDialog(r310) == false) goto L16;
        r12.putInt("enc_id", DialogObject.getEncryptedChatId(r310));
    L22:
        r12.putBoolean("forward_noquote", r5);
        ChatActivity r0 = new ChatActivity(r12);
        r32.presentFragment(r0, true);
        r0.setForwardParams(r5);
        r0.showFieldPanelForForward(true, r2);
        goto L32
    L16:
        if (DialogObject.isUserDialog(r310) == false) goto L18;
        r12.putLong("user_id", r310);
    L20:
        if (AccountInstance.getInstance(this.currentAccount).getMessagesController().checkCanOpenChat(r12, r32) == true) goto L22;
        return true;
    L18:
        r12.putLong("chat_id", -r310);
        goto L20
    }

    private /* synthetic */ void lambda$showActionMode$0(View r1) {
        hideActionMode();
    }

    private /* synthetic */ boolean lambda$showActionMode$2(View r1) {
        onActionBarItemClick(Opcodes.SUB_DOUBLE_2ADDR);
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private void search(View r24, int r25, String r26, boolean r27) {
        boolean r3 = TextUtils.isEmpty(r26);
        StickerEmptyView r4 = this.emptyView;
        if (r3 == false) goto L5;
        r4.subtitle.setVisibility(8);
    L6:
        DialogsSearchAdapter.DialogsSearchAdapterDelegate r32 = this.dialogsSearchAdapter.delegate;
        if (r32 == null) goto L9;
        long r6 = r32.getSearchForumDialogId();
    L10:
        if (r25 != 0) goto L12;
        long r8 = 0;
    L13:
        long r12 = r6;
        int r33 = 0;
        int r10 = 0;
        long r62 = 0;
        long r16 = 0;
    L15:
        if (r33 >= this.currentSearchFilters.size()) goto L36;
        FiltersView.MediaFilterData r14 = this.currentSearchFilters.get(r33);
        int r15 = r14.filterType;
        if (r15 != 4) goto L29;
        TLObject r42 = r14.chat;
        if ((r42 instanceof TLRPC.User) == false) goto L23;
        long r43 = ((TLRPC.User) r42).id;
    L21:
        r8 = r43;
    L34:
        r33 = r33 + 1;
        goto L15
    L23:
        if ((r42 instanceof TLRPC.Chat) == false) goto L34;
        if (ChatObject.isCommunity((TLRPC.Chat) r42) == true) goto L34;
        r43 = -((TLRPC.Chat) r14.chat).id;
        goto L21
    L29:
        if (r15 != 6) goto L32;
        FiltersView.DateData r44 = r14.dateData;
        r62 = r44.minDate;
        r16 = r44.maxDate;
        goto L34
    L32:
        if (r15 != 7) goto L34;
        r10 = 1;
        goto L34
    L36:
        if (this.hashtagSearchAdapter.getHashtag(r26) != null) goto L39;
        collapsePublicPosts();
    L39:
        if (r24 != this.channelsSearchContainer) goto L43;
        MessagesController.getInstance(this.currentAccount).getChannelRecommendations(0);
        this.channelsSearchAdapter.search(r26);
        this.channelsEmptyView.setKeyboardHeight(this.keyboardSize, false);
        return;
    L43:
        if (r24 != this.botsSearchContainer) goto L48;
        this.botsSearchAdapter.search(r26);
        this.botsEmptyView.setKeyboardHeight(this.keyboardSize, false);
        if (TextUtils.isEmpty(r26) == false) goto L120;
        this.botsSearchAdapter.checkBottom();
        return;
    L120:
        return;
    L48:
        PostsSearchContainer r34 = this.postsSearchContainer;
        if (r24 != r34) goto L53;
        r34.search(r26);
        return;
    L53:
        if (r24 != this.hashtagSearchContainer) goto L63;
        if (this.hashtagSearchAdapter.getHashtag(r26) == null) goto L122;
        if (r27 == false) goto L59;
        boolean r35 = false;
        this.hashtagSearchLayoutManager.scrollToPositionWithOffset(0, 0);
    L60:
        this.hashtagSearchAdapter.search(r26);
        this.hashtagEmptyView.setKeyboardHeight(this.keyboardSize, r35);
        return;
    L59:
        r35 = false;
        goto L60
    L122:
        return;
    L63:
        if (r24 == this.searchContainer) goto L65;
        long r5 = r62;
        boolean r11 = r10;
        if ((r24 instanceof FilteredSearchView) == false) goto L110;
        FilteredSearchView r1 = (FilteredSearchView) r24;
        if (r12 == 0) goto L106;
        boolean r45 = true;
    L107:
        r1.setUseFromUserAsAvatar(r45);
        r1.setKeyboardHeight(this.keyboardSize, false);
        long r21 = r8;
        long r7 = r16;
        r1.search(r21, this.communityId, r5, r7, FiltersView.filters[this.viewPagerAdapter.items.get(r25).filterIndex], r11, r26, r27);
        return;
    L106:
        r45 = false;
        goto L107
    L110:
        if ((r24 instanceof SearchDownloadsContainer) == false) goto L121;
        SearchDownloadsContainer r13 = (SearchDownloadsContainer) r24;
        r13.setKeyboardHeight(this.keyboardSize, false);
        r13.search(r26);
        return;
    L121:
        return;
    L65:
        if (r8 != 0) goto L75;
        if (this.communityId != 0) goto L75;
        if (r62 != 0) goto L75;
        if (r16 != 0) goto L75;
    L77:
        this.lastSearchScrolledToTop = false;
        this.dialogsSearchAdapter.searchDialogs(r26, r10, true);
        this.dialogsSearchAdapter.setFiltersDelegate(this.filteredSearchViewDelegate, false);
        this.noMediaFiltersSearchView.animate().setListener(null).cancel();
        this.noMediaFiltersSearchView.setDelegate(null, false);
        if (r27 == false) goto L81;
        this.emptyView.showProgress(!this.dialogsSearchAdapter.isSearching(), false);
        this.emptyView.showProgress(this.dialogsSearchAdapter.isSearching(), false);
    L83:
        FilteredSearchView r17 = this.noMediaFiltersSearchView;
        if (r27 == false) goto L87;
        r17.setVisibility(8);
    L89:
        this.noMediaFiltersSearchView.setTag(null);
    L99:
        this.emptyView.setKeyboardHeight(this.keyboardSize, false);
        this.noMediaFiltersSearchView.setKeyboardHeight(this.keyboardSize, false);
        return;
    L87:
        if (r17.getVisibility() == 8) goto L89;
        this.noMediaFiltersSearchView.animate().alpha(0.0f).setListener(new AnonymousClass19(this)).setDuration(150).start();
        goto L89
    L81:
        if (this.dialogsSearchAdapter.hasRecentSearch() == true) goto L83;
        this.emptyView.showProgress(this.dialogsSearchAdapter.isSearching(), true);
    L75:
        if (r12 != 0) goto L77;
        boolean r18 = true;
        this.noMediaFiltersSearchView.setTag(1);
        this.noMediaFiltersSearchView.setDelegate(this.filteredSearchViewDelegate, false);
        this.noMediaFiltersSearchView.animate().setListener(null).cancel();
        FilteredSearchView r19 = this.noMediaFiltersSearchView;
        if (r27 == false) goto L94;
        r19.setVisibility(0);
        this.noMediaFiltersSearchView.setAlpha(1.0f);
        boolean r132 = r27;
    L98:
        this.noMediaFiltersSearchView.search(r8, this.communityId, r62, r16, null, r10, r26, r132);
        this.emptyView.setVisibility(8);
        goto L99
    L94:
        if (r19.getVisibility() == 0) goto L96;
        this.noMediaFiltersSearchView.setVisibility(0);
        this.noMediaFiltersSearchView.setAlpha(0.0f);
    L97:
        this.noMediaFiltersSearchView.animate().alpha(1.0f).setDuration(150).start();
        r132 = r18;
        goto L98
    L96:
        r18 = r27;
        goto L97
    L12:
        r8 = r6;
        goto L13
    L9:
        r6 = 0;
        goto L10
    L5:
        r4.subtitle.setVisibility(0);
        this.emptyView.subtitle.setText(LocaleController.formatString(R.string.NoResultFoundFor2, new Object[]{r26}));
        goto L6
    }

    private static void setPagesPaddings(ViewGroup r7, RecyclerListView r8, int r9, int r10, boolean r11) {
        r7.setClipToPadding(false);
        r7.setPadding(0, r9, 0, r10);
        r8.setPadding(0, r9, 0, r10, r11);
        ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) r8.getLayoutParams();
        r72.topMargin = -r9;
        r72.bottomMargin = -r10;
    }

    private void showActionMode(boolean r15) {
        if (this.isActionModeShowed == r15) goto L70;
        if (r15 == true) goto L7;
    L9:
        int r0 = 72;
        if (r15 == true) goto L12;
    L21:
        int r4 = 0;
        if (this.selectedMessagesCountTextView == null) goto L39;
        DialogsSearchAdapter r3 = this.dialogsSearchAdapter;
        if (r3 == null) goto L30;
        DialogsSearchAdapter.DialogsSearchAdapterDelegate r32 = r3.delegate;
        if (r32 == null) goto L30;
        if (r32.getSearchForumDialogId() == 0) goto L30;
        boolean r33 = true;
    L31:
        ViewGroup.MarginLayoutParams r5 = (ViewGroup.MarginLayoutParams) this.selectedMessagesCountTextView.getLayoutParams();
        if (this.parent.hasMainTabs == false) goto L34;
        r0 = 18;
    L34:
        if (r33 == false) goto L36;
        int r1 = 56;
    L37:
        r5.leftMargin = AndroidUtilities.dp(r0 + r1);
        NumberTextView r02 = this.selectedMessagesCountTextView;
        r02.setLayoutParams(r02.getLayoutParams());
        goto L39
    L36:
        r1 = 0;
    L30:
        r33 = false;
    L39:
        if (this.parent.getActionBar().getBackButton() != null) goto L41;
    L43:
        this.isActionModeShowed = r15;
        DialogsActivity r03 = this.parent;
        if (r15 == false) goto L51;
        AndroidUtilities.hideKeyboard(r03.getParentActivity().getCurrentFocus());
        this.parent.getActionBar().showActionMode();
        this.selectedMessagesCountTextView.setNumber(this.selectedFiles.size(), false);
        ActionBarMenuItem r152 = this.speedItem;
        if (isSpeedItemVisible() == false) goto L48;
        int r04 = 0;
    L49:
        r152.setVisibility(r04);
        this.gotoItem.setVisibility(0);
        this.forwardItem.setVisibility(0);
        this.deleteItem.setVisibility(0);
        return;
    L48:
        r04 = 8;
        goto L49
    L51:
        r03.getActionBar().hideActionMode();
        this.selectedFiles.clear();
        int r153 = 0;
    L53:
        if (r153 >= getChildCount()) goto L61;
        if ((getChildAt(r153) instanceof FilteredSearchView) == false) goto L58;
        ((FilteredSearchView) getChildAt(r153)).update();
    L58:
        if ((getChildAt(r153) instanceof SearchDownloadsContainer) == false) goto L60;
        ((SearchDownloadsContainer) getChildAt(r153)).update(true);
    L60:
        r153 = r153 + 1;
        goto L53
    L61:
        FilteredSearchView r154 = this.noMediaFiltersSearchView;
        if (r154 == null) goto L64;
        r154.update();
    L64:
        int r155 = this.viewsByType.size();
    L65:
        if (r4 >= r155) goto L78;
        View r05 = this.viewsByType.valueAt(r4);
        if ((r05 instanceof FilteredSearchView) == false) goto L69;
        ((FilteredSearchView) r05).update();
    L69:
        r4 = r4 + 1;
        goto L65
    L78:
        return;
    L41:
        if ((this.parent.getActionBar().getBackButton().getDrawable() instanceof MenuDrawable) == false) goto L43;
        BackDrawable r06 = new BackDrawable(false);
        this.parent.getActionBar().setBackButtonDrawable(r06);
        r06.setColorFilter(null);
        goto L43
    L12:
        if (this.parent.getActionBar().actionModeIsExist("search_view_pager") == true) goto L21;
        this.actionMode = this.parent.getActionBar().createActionMode(true, "search_view_pager");
        if (this.parent.hasMainTabs == false) goto L16;
        ImageView r34 = new ImageView(getContext());
        this.actionModeCloseView = r34;
        r34.setScaleType(ImageView.ScaleType.CENTER);
        this.actionModeCloseView.setImageDrawable(new BackDrawable(true));
        this.actionModeCloseView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.MULTIPLY));
        this.actionModeCloseView.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector)));
        this.actionModeCloseView.setOnClickListener(new SearchViewPager$$ExternalSyntheticLambda5(this));
        this.actionMode.addView(this.actionModeCloseView, LayoutHelper.createLinear(54, 54, 0.0f, 16));
    L16:
        NumberTextView r35 = new NumberTextView(this.actionMode.getContext());
        this.selectedMessagesCountTextView = r35;
        r35.setTextSize(18);
        this.selectedMessagesCountTextView.setTypeface(AndroidUtilities.bold());
        NumberTextView r36 = this.selectedMessagesCountTextView;
        int r42 = Theme.key_actionBarActionModeDefaultIcon;
        r36.setTextColor(Theme.getColor(r42));
        ActionBarMenu r37 = this.actionMode;
        NumberTextView r52 = this.selectedMessagesCountTextView;
        if (this.parent.hasMainTabs == false) goto L19;
        int r10 = 18;
    L20:
        r37.addView(r52, LayoutHelper.createLinear(0, -1, 1.0f, r10, 0, 0, 0));
        this.selectedMessagesCountTextView.setOnTouchListener(new SearchViewPager$$ExternalSyntheticLambda6());
        ActionBarMenuItem r38 = this.actionMode.addItemWithWidth(203, R.drawable.avd_speed, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.AccDescrPremiumSpeed));
        this.speedItem = r38;
        r38.getIconView().setColorFilter(new PorterDuffColorFilter(Theme.getColor(r42), PorterDuff.Mode.SRC_IN));
        this.gotoItem = this.actionMode.addItemWithWidth(200, R.drawable.msg_message, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.AccDescrGoToMessage));
        ActionBarMenuItem r39 = this.actionMode.addItemWithWidth(201, R.drawable.msg_forward, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Forward));
        this.forwardItem = r39;
        r39.setOnLongClickListener(new SearchViewPager$$ExternalSyntheticLambda7(this));
        this.deleteItem = this.actionMode.addItemWithWidth(202, R.drawable.msg_delete, AndroidUtilities.dp(54.0f), LocaleController.getString(R.string.Delete));
        goto L21
    L19:
        r10 = 72;
        goto L20
    L7:
        if (this.parent.getActionBar().isActionModeShowed() == false) goto L9;
        return;
    }

    @Override // org.telegram.ui.FilteredSearchView.UiCallback
    public boolean actionModeShowing() {
        return this.isActionModeShowed;
    }

    public boolean addSearchFilter(FiltersView.MediaFilterData r4) {
        if (this.currentSearchFilters.isEmpty() == true) goto L11;
        int r1 = 0;
    L6:
        if (r1 >= this.currentSearchFilters.size()) goto L11;
        if (r4.isSameType(this.currentSearchFilters.get(r1)) == true) goto L9;
        r1 = r1 + 1;
        goto L6
    L9:
        return false;
    L11:
        this.currentSearchFilters.add(r4);
        return true;
    }

    public void cancelEnterAnimation() {
        this.itemsEnterAnimator.cancel();
        this.searchListView.invalidate();
        this.animateFromCount = 0;
    }

    @Override // org.telegram.ui.Components.blur3.capture.IBlur3Capture
    public void capture(Canvas r6, RectF r7) {
        View[] r0 = getViewPages();
        if (r0 == null) goto L16;
        int r1 = r0.length;
        int r2 = 0;
    L6:
        if (r2 >= r1) goto L21;
        View r3 = r0[r2];
        RecyclerListView r4 = getRecyclerViewFromPage(r3);
        if (r4 == null) goto L11;
        Blur3Utils.captureRelativeParent(r4, r6, r7, r4, this);
    L11:
        if (r3 != this.searchContainer) goto L15;
        if (this.noMediaFiltersSearchView.getVisibility() != 0) goto L15;
        RecyclerListView r32 = this.noMediaFiltersSearchView.recyclerListView;
        Blur3Utils.captureRelativeParent(r32, r6, r7, r32, this);
    L15:
        r2 = r2 + 1;
        goto L6
    L21:
        return;
    }

    public void clear() {
        this.currentSearchFilters.clear();
        collapsePublicPosts();
    }

    public void collapsePublicPosts() {
        if (this.expandedPublicPosts == false) goto L15;
        this.expandedPublicPosts = false;
        updateTabs();
        ViewPagerFixed.TabsView r1 = this.tabsView;
        if (r1 != null) goto L8;
    L10:
        DialogsSearchAdapter r0 = this.dialogsSearchAdapter;
        if (r0 == null) goto L14;
        r0.searchDialogs(this.lastSearchString, includeFolder() ? 1 : 0, true);
        return;
    L14:
        return;
    L8:
        if (r1.getCurrentTabId() == 0) goto L10;
        this.tabsView.scrollToTab(0, 0);
        goto L10
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r5, int r6, Object... r7) {
        boolean r0 = false;
        if (r5 != NotificationCenter.channelRecommendationsLoaded) goto L10;
        StickerEmptyView r52 = this.channelsEmptyView;
        if (MessagesController.getInstance(this.currentAccount).getChannelRecommendations(0) == null) goto L7;
        r0 = true;
    L7:
        r52.showProgress(r0, true);
        this.channelsSearchAdapter.updateMyChannels();
        this.channelsSearchAdapter.update(true);
        return;
    L10:
        if (r5 == NotificationCenter.dialogDeleted) goto L25;
        if (r5 == NotificationCenter.dialogsNeedReload) goto L25;
        if (r5 != NotificationCenter.reloadWebappsHints) goto L19;
        this.botsSearchAdapter.update(true);
        return;
    L19:
        if (r5 != NotificationCenter.storiesListUpdated) goto L30;
        Object r53 = r7[0];
        HashtagsSearchAdapter r4 = this.hashtagSearchAdapter;
        if (r53 != r4.list) goto L31;
        r4.update(true);
        return;
    L31:
        return;
    L30:
        return;
    L25:
        if (getVisibility() == 0) goto L28;
        this.channelsUpdatePending = true;
        return;
    L28:
        this.channelsSearchAdapter.updateMyChannels();
        this.channelsSearchAdapter.update(true);
    }

    public ActionBarMenu getActionMode() {
        return this.actionMode;
    }

    public ArrayList<FiltersView.MediaFilterData> getCurrentSearchFilters() {
        return this.currentSearchFilters;
    }

    public SearchDownloadsContainer getDownloadsContainer() {
        return this.downloadsContainer;
    }

    public int getFolderId() {
        return this.folderId;
    }

    @Override // org.telegram.ui.Components.ViewPagerFixed
    public long getManualScrollDuration() {
        return 320;
    }

    public int getPositionForType(int r4) {
        int r0 = 0;
    L4:
        if (r0 >= this.viewPagerAdapter.items.size()) goto L11;
        if (ViewPagerAdapter.Item.m12607$$Nest$fgettype(this.viewPagerAdapter.items.get(r0)) != 3) goto L10;
        if (this.viewPagerAdapter.items.get(r0).filterIndex != r4) goto L10;
        return r0;
    L10:
        r0 = r0 + 1;
        goto L4
    L11:
        return -1;
    }

    public ActionBarMenuItem getSpeedItem() {
        return this.speedItem;
    }

    public ViewPagerFixed.TabsView getTabsView() {
        return this.tabsView;
    }

    public void getThemeDescriptions(ArrayList<ThemeDescription> r12) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.searchListView.getChildCount()) goto L13;
        View r4 = this.searchListView.getChildAt(r1);
        if ((r4 instanceof ProfileSearchCell) == false) goto L8;
    L11:
        r12.add(new ThemeDescription(r4, ThemeDescription.FLAG_BACKGROUND, null, null, null, null, Theme.key_windowBackgroundWhite));
    L12:
        r1 = r1 + 1;
        goto L4
    L8:
        if ((r4 instanceof DialogCell) == true) goto L11;
        if ((r4 instanceof HashtagSearchCell) == false) goto L12;
    L13:
        int r13 = 0;
    L15:
        if (r13 >= getChildCount()) goto L20;
        if ((getChildAt(r13) instanceof FilteredSearchView) == false) goto L19;
        r12.addAll(((FilteredSearchView) getChildAt(r13)).getThemeDescriptions());
    L19:
        r13 = r13 + 1;
        goto L15
    L20:
        int r14 = this.viewsByType.size();
    L21:
        if (r0 >= r14) goto L26;
        View r2 = this.viewsByType.valueAt(r0);
        if ((r2 instanceof FilteredSearchView) == false) goto L25;
        r12.addAll(((FilteredSearchView) r2).getThemeDescriptions());
    L25:
        r0 = r0 + 1;
        goto L21
    L26:
        FilteredSearchView r02 = this.noMediaFiltersSearchView;
        if (r02 == null) goto L29;
        r12.addAll(r02.getThemeDescriptions());
    L29:
        r12.add(new ThemeDescription(this.emptyView.title, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteBlackText));
        r12.add(new ThemeDescription(this.emptyView.subtitle, ThemeDescription.FLAG_TEXTCOLOR, null, null, null, null, Theme.key_windowBackgroundWhiteGrayText));
        r12.addAll(SimpleThemeDescription.createThemeDescriptions(new SearchViewPager$$ExternalSyntheticLambda4(this), new int[]{Theme.key_actionBarActionModeDefaultIcon}));
    }

    @Override // org.telegram.ui.FilteredSearchView.UiCallback
    public void goToMessage(MessageObject r3) {
        this.parent.presentFragment(createFragmentFromMessage(this.currentAccount, r3));
        showActionMode(false);
    }

    public void hideActionMode() {
        showActionMode(false);
    }

    public abstract boolean includeDownloads();

    public boolean includeFolder() {
        int r1 = 0;
    L4:
        if (r1 >= this.currentSearchFilters.size()) goto L10;
        if (this.currentSearchFilters.get(r1).filterType == 7) goto L7;
        r1 = r1 + 1;
        goto L4
    L7:
        return true;
    L10:
        return false;
    }

    @Override // org.telegram.ui.Components.ViewPagerFixed
    public void invalidateBlur() {
        this.fragmentView.invalidateBlur();
    }

    public boolean isDownloadsTab(int r2) {
        ViewPagerAdapter r0 = this.viewPagerAdapter;
        if (r0 == null) goto L11;
        if (r2 >= 0) goto L6;
        return false;
    L6:
        if (r2 < r0.getItemCount()) goto L8;
        return false;
    L8:
        if (this.viewPagerAdapter.getItemViewType(r2) != 2) goto L15;
        return true;
    L15:
        return false;
    L11:
        return false;
    }

    @Override // org.telegram.ui.FilteredSearchView.UiCallback
    public boolean isSelected(FilteredSearchView.MessageHashId r1) {
        return this.selectedFiles.containsKey(r1);
    }

    public void messagesDeleted(long r11, ArrayList<Integer> r13) {
        int r0 = this.viewsByType.size();
        int r1 = 0;
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        View r3 = this.viewsByType.valueAt(r2);
        if ((r3 instanceof FilteredSearchView) == false) goto L7;
        ((FilteredSearchView) r3).messagesDeleted(r11, r13);
    L7:
        r2 = r2 + 1;
        goto L3
    L8:
        int r02 = 0;
    L10:
        if (r02 >= getChildCount()) goto L15;
        if ((getChildAt(r02) instanceof FilteredSearchView) == false) goto L14;
        ((FilteredSearchView) getChildAt(r02)).messagesDeleted(r11, r13);
    L14:
        r02 = r02 + 1;
        goto L10
    L15:
        this.noMediaFiltersSearchView.messagesDeleted(r11, r13);
        if (this.selectedFiles.isEmpty() == true) goto L63;
        ArrayList r03 = new ArrayList(this.selectedFiles.keySet());
        ArrayList r22 = null;
        int r32 = 0;
    L19:
        if (r32 >= r03.size()) goto L38;
        FilteredSearchView.MessageHashId r4 = (FilteredSearchView.MessageHashId) r03.get(r32);
        MessageObject r5 = this.selectedFiles.get(r4);
        if (r5 == null) goto L37;
        long r6 = r5.getDialogId();
        if (r6 >= 0) goto L27;
        int r62 = (int) (-r6);
        if (ChatObject.isChannel(r62, this.currentAccount) == false) goto L27;
    L29:
        if (r62 != r11) goto L37;
        int r63 = 0;
    L32:
        if (r63 >= r13.size()) goto L37;
        if (r5.getId() != r13.get(r63).intValue()) goto L36;
        r22 = new ArrayList();
        r22.add(r4);
    L36:
        r63 = r63 + 1;
    L27:
        r62 = 0;
    L37:
        r32 = r32 + 1;
        goto L19
    L38:
        if (r22 == null) goto L64;
        int r112 = r22.size();
        int r12 = 0;
    L40:
        if (r12 >= r112) goto L42;
        this.selectedFiles.remove(r22.get(r12));
        r12 = r12 + 1;
        goto L40
    L42:
        this.selectedMessagesCountTextView.setNumber(this.selectedFiles.size(), true);
        ActionBarMenuItem r113 = this.gotoItem;
        if (r113 != null) goto L45;
        return;
    L45:
        if (this.selectedFiles.size() == 1) goto L48;
        r1 = 8;
    L48:
        r113.setVisibility(r1);
        return;
    L64:
        return;
    }

    public void onActionBarItemClick(int r6) {
        boolean r1 = false;
        if (r6 != 202) goto L14;
        DialogsActivity r62 = this.parent;
        if (r62 != null) goto L7;
        return;
    L7:
        if (r62.getParentActivity() == null) goto L38;
        ArrayList r63 = new ArrayList(this.selectedFiles.values());
        AlertDialog.Builder r0 = new AlertDialog.Builder(this.parent.getParentActivity());
        r0.setTitle(LocaleController.formatPluralString("RemoveDocumentsTitle", this.selectedFiles.size(), new Object[0]));
        SpannableStringBuilder r2 = new SpannableStringBuilder();
        r2.append(AndroidUtilities.replaceTags(LocaleController.formatPluralString("RemoveDocumentsMessage", this.selectedFiles.size(), new Object[0]))).append("\n\n").append(LocaleController.getString(R.string.RemoveDocumentsAlertMessage));
        r0.setMessage(r2);
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), new SearchViewPager$$ExternalSyntheticLambda1());
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new SearchViewPager$$ExternalSyntheticLambda2(this, r63));
        TextView r5 = (TextView) r0.show().getButton(-1);
        if (r5 == null) goto L36;
        r5.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L36:
        return;
    L38:
        return;
    L14:
        if (r6 != 203) goto L21;
        if (isSpeedItemVisible() == false) goto L39;
        this.parent.showDialog(new PremiumFeatureBottomSheet(this.parent, 2, true));
        return;
    L39:
        return;
    L21:
        if (r6 != 200) goto L28;
        if (this.selectedFiles.size() != 1) goto L40;
        goToMessage(this.selectedFiles.values().iterator().next());
        return;
    L40:
        return;
    L28:
        if (r6 == 201) goto L32;
        if (r6 == 204) goto L32;
        return;
    L32:
        if (r6 != 204) goto L34;
        r1 = true;
    L34:
        Bundle r64 = new Bundle();
        r64.putBoolean("onlySelect", true);
        r64.putInt("dialogsType", 3);
        r64.putBoolean("forward_noquote", r1);
        DialogsActivity r02 = new DialogsActivity(r64);
        r02.setDelegate(new SearchViewPager$$ExternalSyntheticLambda3(this, r1));
        this.parent.presentFragment(r02);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L5;
        r0.removeAllObservers();
        this.observersGroup = null;
    L5:
        this.observersGroup = NotificationCenter.getInstance(this.currentAccount).createObserversGroup(this).add(NotificationCenter.channelRecommendationsLoaded).add(NotificationCenter.dialogDeleted).add(NotificationCenter.dialogsNeedReload).add(NotificationCenter.reloadWebappsHints).add(NotificationCenter.storiesListUpdated);
        this.attached = true;
        DialogsChannelsAdapter r02 = this.channelsSearchAdapter;
        if (r02 == null) goto L8;
        r02.update(false);
    L8:
        DialogsBotsAdapter r2 = this.botsSearchAdapter;
        if (r2 == null) goto L12;
        r2.update(false);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.attached = false;
        NotificationCenter.ObserversGroup r0 = this.observersGroup;
        if (r0 == null) goto L6;
        r0.removeAllObservers();
        this.observersGroup = null;
        return;
    }

    @Override // org.telegram.ui.Components.ViewPagerFixed
    public void onItemSelected(View r4, View r5, int r6, int r7) {
        boolean r0 = true;
        if (r6 != 0) goto L9;
        int r42 = this.noMediaFiltersSearchView.getVisibility();
        FilteredSearchView r62 = this.noMediaFiltersSearchView;
        if (r42 != 0) goto L7;
        r62.setDelegate(this.filteredSearchViewDelegate, false);
        this.dialogsSearchAdapter.setFiltersDelegate(null, false);
    L17:
        if ((r5 instanceof FilteredSearchView) == false) goto L20;
        ((FilteredSearchView) r5).setDelegate(null, false);
        return;
    L20:
        this.dialogsSearchAdapter.setFiltersDelegate(null, false);
        this.noMediaFiltersSearchView.setDelegate(null, false);
        return;
    L7:
        r62.setDelegate(null, false);
        this.dialogsSearchAdapter.setFiltersDelegate(this.filteredSearchViewDelegate, true);
        goto L17
    L9:
        if ((r4 instanceof FilteredSearchView) == false) goto L17;
        if (r7 == 0) goto L12;
    L14:
        r0 = false;
    L15:
        ((FilteredSearchView) r4).setDelegate(this.filteredSearchViewDelegate, r0);
        goto L17
    L12:
        if (this.noMediaFiltersSearchView.getVisibility() == 0) goto L14;
        goto L14
    }

    public void onPageScrolled(int r1, int r2) {
    }

    public void onResume() {
        DialogsSearchAdapter r0 = this.dialogsSearchAdapter;
        if (r0 == null) goto L6;
        r0.notifyDataSetChanged();
        return;
    }

    public void onShown() {
        DialogsSearchAdapter r0 = this.dialogsSearchAdapter;
        if (r0 == null) goto L6;
        r0.resetFilter();
        return;
    }

    public void onTextChanged(String r5) {
        View r0 = getCurrentView();
        boolean r2 = true;
        boolean r1 = !this.attached;
        if (TextUtils.isEmpty(this.lastSearchString) == true) goto L6;
        r2 = r1;
    L6:
        this.lastSearchString = r5;
        search(r0, getCurrentPosition(), r5, r2);
    }

    public void removeSearchFilter(FiltersView.MediaFilterData r1) {
        this.currentSearchFilters.remove(r1);
    }

    public void reset() {
        setPosition(0);
        if (this.dialogsSearchAdapter.getItemCount() <= 0) goto L5;
        this.searchLayoutManager.scrollToPositionWithOffset(0, 0);
    L5:
        LinearLayoutManager r1 = this.channelsSearchLayoutManager;
        if (r1 == null) goto L8;
        r1.scrollToPositionWithOffset(0, 0);
    L8:
        LinearLayoutManager r12 = this.botsSearchLayoutManager;
        if (r12 == null) goto L11;
        r12.scrollToPositionWithOffset(0, 0);
    L11:
        LinearLayoutManager r13 = this.hashtagSearchLayoutManager;
        if (r13 == null) goto L14;
        r13.scrollToPositionWithOffset(0, 0);
    L14:
        this.viewsByType.clear();
    }

    public void runResultsEnterAnimation() {
        RecyclerItemsEnterAnimator r0 = this.itemsEnterAnimator;
        int r1 = this.animateFromCount;
        if (r1 <= 0) goto L5;
        int r12 = r1 + 1;
    L6:
        r0.showItemsAnimated(r12);
        this.animateFromCount = this.dialogsSearchAdapter.getItemCount();
        return;
    L5:
        r12 = 0;
        goto L6
    }

    public void setBlurredBackgroundDrawableFactory(BlurredBackgroundDrawableViewFactory r1) {
        this.blurredBackgroundDrawableFactory = r1;
    }

    public void setFilteredSearchViewDelegate(FilteredSearchView.Delegate r1) {
        this.filteredSearchViewDelegate = r1;
    }

    public void setKeyboardHeight(int r5) {
        this.keyboardSize = r5;
        int r1 = 0;
        if (getVisibility() == 0) goto L5;
    L7:
        boolean r0 = false;
    L9:
        if (r1 >= getChildCount()) goto L23;
        if ((getChildAt(r1) instanceof FilteredSearchView) == false) goto L14;
        ((FilteredSearchView) getChildAt(r1)).setKeyboardHeight(r5, r0);
    L22:
        r1 = r1 + 1;
        goto L9
    L14:
        if (getChildAt(r1) != this.searchContainer) goto L17;
        this.emptyView.setKeyboardHeight(r5, r0);
        this.noMediaFiltersSearchView.setKeyboardHeight(r5, r0);
        goto L22
    L17:
        if ((getChildAt(r1) instanceof SearchDownloadsContainer) == false) goto L20;
        ((SearchDownloadsContainer) getChildAt(r1)).setKeyboardHeight(r5, r0);
        goto L22
    L20:
        if (getChildAt(r1) != this.channelsSearchContainer) goto L22;
        this.channelsEmptyView.setKeyboardHeight(r5, r0);
        goto L22
    L23:
        return;
    L5:
        if (getAlpha() <= 0.0f) goto L7;
        r0 = true;
        goto L9
    }

    public void setPagesPadding(int r7, int r8, boolean r9) {
        this.pagesPaddingTop = r7;
        this.pagesPaddingBottom = r8;
        this.searchListView.setPadding(0, r7, 0, r8, r9);
        this.noMediaFiltersSearchView.setPagesPaddings(this.pagesPaddingTop, this.pagesPaddingBottom, r9);
        ViewGroup.MarginLayoutParams r72 = (ViewGroup.MarginLayoutParams) this.emptyView.getLayoutParams();
        int r82 = r72.topMargin;
        int r92 = this.pagesPaddingTop;
        if (r82 == r92) goto L5;
    L6:
        r72.topMargin = r92;
        r72.bottomMargin = this.pagesPaddingBottom;
        this.emptyView.requestLayout();
    L7:
        setPagesPaddings(this.channelsSearchContainer, this.channelsSearchListView, this.pagesPaddingTop, this.pagesPaddingBottom, r9);
        setPagesPaddings(this.botsSearchContainer, this.botsSearchListView, this.pagesPaddingTop, this.pagesPaddingBottom, r9);
        setPagesPaddings(this.hashtagSearchContainer, this.hashtagSearchListView, this.pagesPaddingTop, this.pagesPaddingBottom, r9);
        this.postsSearchContainer.setPagesPaddings(this.pagesPaddingTop, this.pagesPaddingBottom, r9);
        SearchDownloadsContainer r73 = this.downloadsContainer;
        if (r73 == null) goto L10;
        r73.setPagesPaddings(this.pagesPaddingTop, this.pagesPaddingBottom, r9);
    L10:
        int r74 = this.viewsByType.size();
        int r83 = 0;
        int r93 = 0;
    L11:
        if (r93 >= r74) goto L17;
        View r0 = this.viewsByType.valueAt(r93);
        if ((r0 instanceof FilteredSearchView) == false) goto L15;
        ((FilteredSearchView) r0).setPagesPaddings(this.pagesPaddingTop, this.pagesPaddingBottom, r9);
    L15:
        r93 = r93 + 1;
    L17:
        if (r83 >= getChildCount()) goto L22;
        if ((getChildAt(r83) instanceof FilteredSearchView) == false) goto L21;
        ((FilteredSearchView) getChildAt(r83)).setPagesPaddings(this.pagesPaddingTop, this.pagesPaddingBottom, r9);
    L21:
        r83 = r83 + 1;
        goto L17
    L22:
        return;
    L5:
        if (r72.bottomMargin == this.pagesPaddingBottom) goto L7;
        goto L6
    }

    @Override // org.telegram.ui.Components.ViewPagerFixed
    public void setPosition(int r3) {
        if (r3 >= 0) goto L4;
        return;
    L4:
        super.setPosition(r3);
        this.viewsByType.clear();
        ViewPagerFixed.TabsView r0 = this.tabsView;
        if (r0 == null) goto L7;
        r0.selectTabWithId(r3, 1.0f);
    L7:
        invalidate();
    }

    @Override // android.view.View
    public void setVisibility(int r2) {
        super.setVisibility(r2);
        if (r2 == 0) goto L5;
        return;
    L5:
        if (this.channelsUpdatePending == false) goto L11;
        DialogsChannelsAdapter r22 = this.channelsSearchAdapter;
        if (r22 == null) goto L12;
        this.channelsUpdatePending = false;
        r22.updateMyChannels();
        this.channelsSearchAdapter.update(false);
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.FilteredSearchView.UiCallback
    public void showActionMode() {
        showActionMode(true);
    }

    public void showDownloads() {
        int r0 = 0;
    L4:
        if (r0 >= this.viewPagerAdapter.items.size()) goto L10;
        if (ViewPagerAdapter.Item.m12607$$Nest$fgettype(this.viewPagerAdapter.items.get(r0)) == 2) goto L7;
        r0 = r0 + 1;
        goto L4
    L7:
        setPosition(r0);
        return;
    }

    public void showOnlyDialogsAdapter(boolean r1) {
        this.showOnlyDialogsAdapter = r1;
    }

    @Override // org.telegram.ui.FilteredSearchView.UiCallback
    public void toggleItemSelection(MessageObject r9, View r10, int r11) {
        FilteredSearchView.MessageHashId r0 = new FilteredSearchView.MessageHashId(r9.getId(), r9.getDialogId());
        boolean r1 = this.selectedFiles.containsKey(r0);
        HashMap<FilteredSearchView.MessageHashId, MessageObject> r2 = this.selectedFiles;
        if (r1 == false) goto L6;
        r2.remove(r0);
    L9:
        int r12 = 0;
        if (this.selectedFiles.size() != 0) goto L12;
        showActionMode(false);
    L46:
        if ((r10 instanceof SharedDocumentCell) == false) goto L50;
        ((SharedDocumentCell) r10).setChecked(this.selectedFiles.containsKey(r0), true);
        return;
    L50:
        if ((r10 instanceof SharedPhotoVideoCell) == false) goto L54;
        ((SharedPhotoVideoCell) r10).setChecked(r11, this.selectedFiles.containsKey(r0), true);
        return;
    L54:
        if ((r10 instanceof SharedLinkCell) == false) goto L58;
        ((SharedLinkCell) r10).setChecked(this.selectedFiles.containsKey(r0), true);
        return;
    L58:
        if ((r10 instanceof SharedAudioCell) == false) goto L62;
        ((SharedAudioCell) r10).setChecked(this.selectedFiles.containsKey(r0), true);
        return;
    L62:
        if ((r10 instanceof ContextLinkCell) == false) goto L66;
        ((ContextLinkCell) r10).setChecked(this.selectedFiles.containsKey(r0), true);
        return;
    L66:
        if ((r10 instanceof DialogCell) == false) goto L72;
        ((DialogCell) r10).setChecked(this.selectedFiles.containsKey(r0), true);
        return;
    L72:
        return;
    L12:
        this.selectedMessagesCountTextView.setNumber(this.selectedFiles.size(), true);
        ActionBarMenuItem r92 = this.gotoItem;
        if (r92 == null) goto L20;
        if (this.selectedFiles.size() != 1) goto L17;
        int r4 = 0;
    L18:
        r92.setVisibility(r4);
        goto L20
    L17:
        r4 = 8;
    L20:
        if (this.speedItem == null) goto L32;
        boolean r93 = isSpeedItemVisible();
        if (r93 == false) goto L24;
        int r42 = 0;
    L26:
        if (this.speedItem.getVisibility() == r42) goto L32;
        this.speedItem.setVisibility(r42);
        AnimatedVectorDrawable r43 = (AnimatedVectorDrawable) this.speedItem.getIconView().getDrawable();
        r43.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarActionModeDefaultIcon), PorterDuff.Mode.SRC_IN));
        if (r93 == false) goto L30;
        r43.start();
        goto L32
    L30:
        r43.reset();
        goto L32
    L24:
        r42 = 8;
    L32:
        if (this.deleteItem == null) goto L46;
        Iterator<FilteredSearchView.MessageHashId> r94 = this.selectedFiles.keySet().iterator();
    L35:
        if (r94.hasNext() == false) goto L39;
        if (this.selectedFiles.get(r94.next()).isDownloadingFile == true) goto L35;
        boolean r95 = false;
    L40:
        ActionBarMenuItem r44 = this.deleteItem;
        if (r95 == true) goto L44;
        r12 = 8;
    L44:
        r44.setVisibility(r12);
        goto L46
    L39:
        r95 = true;
        goto L40
    L6:
        if (r2.size() >= 100) goto L73;
        this.selectedFiles.put(r0, r9);
        goto L9
    }

    public void updateColors() {
        int r1 = 0;
    L4:
        if (r1 >= getChildCount()) goto L14;
        if ((getChildAt(r1) instanceof FilteredSearchView) == false) goto L13;
        RecyclerListView r2 = ((FilteredSearchView) getChildAt(r1)).recyclerListView;
        int r3 = r2.getChildCount();
        int r4 = 0;
    L8:
        if (r4 >= r3) goto L13;
        View r5 = r2.getChildAt(r4);
        if ((r5 instanceof DialogCell) == false) goto L12;
        ((DialogCell) r5).update(0);
    L12:
        r4 = r4 + 1;
    L13:
        r1 = r1 + 1;
        goto L4
    L14:
        int r12 = this.viewsByType.size();
        int r22 = 0;
    L15:
        if (r22 >= r12) goto L25;
        View r32 = this.viewsByType.valueAt(r22);
        if ((r32 instanceof FilteredSearchView) == false) goto L24;
        RecyclerListView r33 = ((FilteredSearchView) r32).recyclerListView;
        int r42 = r33.getChildCount();
        int r52 = 0;
    L19:
        if (r52 >= r42) goto L24;
        View r6 = r33.getChildAt(r52);
        if ((r6 instanceof DialogCell) == false) goto L23;
        ((DialogCell) r6).update(0);
    L23:
        r52 = r52 + 1;
    L24:
        r22 = r22 + 1;
        goto L15
    L25:
        FilteredSearchView r13 = this.noMediaFiltersSearchView;
        if (r13 == null) goto L33;
        RecyclerListView r14 = r13.recyclerListView;
        int r23 = r14.getChildCount();
        int r34 = 0;
    L28:
        if (r34 >= r23) goto L33;
        View r43 = r14.getChildAt(r34);
        if ((r43 instanceof DialogCell) == false) goto L32;
        ((DialogCell) r43).update(0);
    L32:
        r34 = r34 + 1;
    L33:
        PostsSearchContainer r8 = this.postsSearchContainer;
        if (r8 == null) goto L50;
        r8.updateColors();
        return;
    }

    public void updateTabs() {
        updateTabs(false);
    }

    public void updateTabs(boolean r2) {
        this.viewPagerAdapter.updateItems();
        fillTabs(r2);
        if (this.currentPosition < this.viewPagerAdapter.getItemCount()) goto L5;
        setPosition(Math.max(0, this.viewPagerAdapter.getItemCount() - 1));
    L5:
        ViewPagerFixed.TabsView r1 = this.tabsView;
        if (r1 == null) goto L9;
        r1.finishAddingTabs();
        return;
    }
}
