package org.telegram.ui.Stories;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.NotificationsController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.FixedHeightEmptyCell;
import org.telegram.ui.Cells.ReactedUserHolderView;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.CustomPopupMenu;
import org.telegram.ui.Components.EmojiPacksAlert;
import org.telegram.ui.Components.FillLastLinearLayoutManager;
import org.telegram.ui.Components.FlickerLoadingView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.MessageContainsEmojiButton;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.RecyclerAnimationScrollHelper;
import org.telegram.ui.Components.RecyclerItemsEnterAnimator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ReplaceableIconDrawable;
import org.telegram.ui.Components.SearchField;
import org.telegram.ui.Components.StickerEmptyView;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.RecyclerListViewScroller;
import org.telegram.ui.Stories.SelfStoryViewsView;
import org.telegram.ui.Stories.StoriesController;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.recorder.StoryEntry;
import org.telegram.ui.Stories.recorder.StoryPrivacyBottomSheet;

/* loaded from: classes7.dex */
public abstract class SelfStoryViewsPage extends FrameLayout implements NotificationCenter.NotificationCenterDelegate {
    private int TOP_PADDING;
    private boolean checkAutoscroll;
    int currentAccount;
    ViewsModel currentModel;
    private StoriesController.StoryRepostsList currentRepostsList;
    ViewsModel defaultModel;
    private long dialogId;
    HeaderView headerView;
    private boolean isAttachedToWindow;
    boolean isSearchDebounce;
    public FillLastLinearLayoutManager layoutManager;
    ListAdapter listAdapter;
    private int measuerdHeight;
    Utilities.Callback<SelfStoryViewsPage> onSharedStateChanged;
    private CustomPopupMenu popupMenu;
    RecyclerItemsEnterAnimator recyclerItemsEnterAnimator;
    RecyclerListView recyclerListView;
    private int repostsListConsumedCount;
    Theme.ResourcesProvider resourcesProvider;
    RecyclerAnimationScrollHelper scrollHelper;
    private final RecyclerListViewScroller scroller;
    SearchField searchField;
    Drawable shadowDrawable;
    private final View shadowView;
    private final View shadowView2;
    final FiltersState sharedFilterState;
    private boolean showContactsFilter;
    private boolean showReactionsSort;
    private boolean showSearch;
    private boolean showServerErrorText;
    final FiltersState state;
    SelfStoryViewsView.StoryItemInternal storyItem;
    StoryViewer storyViewer;
    private final TextView titleView;
    private final FrameLayout topViewsContainer;

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerListViewInner {
        final /* synthetic */ SelfStoryViewsPage this$0;

        public AnonymousClass1(SelfStoryViewsPage r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r1, r2, r3);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r3, int r4) {
            SelfStoryViewsPage.m19662$$Nest$fputmeasuerdHeight(this.this$0, View.MeasureSpec.getSize(r4));
            super.onMeasure(r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$2, reason: invalid class name */
    public class AnonymousClass2 implements RecyclerAnimationScrollHelper.ScrollListener {
        final /* synthetic */ SelfStoryViewsPage this$0;

        public AnonymousClass2(SelfStoryViewsPage r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.RecyclerAnimationScrollHelper.ScrollListener
        public void onScroll() {
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ SelfStoryViewsPage this$0;

        public AnonymousClass3(SelfStoryViewsPage r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrollStateChanged(RecyclerView r2, int r3) {
            super.onScrollStateChanged(r2, r3);
            if (r3 != 0) goto L5;
            SelfStoryViewsPage.m19661$$Nest$fputcheckAutoscroll(this.this$0, true);
            this.this$0.invalidate();
        L5:
            if (r3 != 1) goto L8;
            SelfStoryViewsPage.m19661$$Nest$fputcheckAutoscroll(this.this$0, false);
            SelfStoryViewsPage.m19658$$Nest$fgetscroller(this.this$0).cancel();
            AndroidUtilities.hideKeyboard(this.this$0);
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            SelfStoryViewsPage.m19664$$Nest$mcheckLoadMore(this.this$0);
            this.this$0.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$4, reason: invalid class name */
    public class AnonymousClass4 implements RecyclerListView.OnItemLongClickListener {
        final /* synthetic */ SelfStoryViewsPage this$0;
        final /* synthetic */ StoryViewer val$storyViewer;

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$4$1, reason: invalid class name */
        public class AnonymousClass1 extends BaseFragment {
            final /* synthetic */ AnonymousClass4 this$1;

            public AnonymousClass1(AnonymousClass4 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Context getContext() {
                return this.this$1.this$0.getContext();
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public int getCurrentAccount() {
                return this.currentAccount;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Theme.ResourcesProvider getResourceProvider() {
                return this.this$1.this$0.resourcesProvider;
            }
        }

        public static /* synthetic */ void $r8$lambda$1KN0hYXengMVemo07StZZMXm3gg(AnonymousClass4 r0, MessagesController r1, TLRPC.User r2, ReactedUserHolderView r3, TL_stories.StoryView r4) {
            r0.lambda$onItemClick$2(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$6cRBX1vNBff6NygIF_H0MebLVbc(AnonymousClass4 r0, MessagesController r1, TLRPC.User r2, ReactedUserHolderView r3, TL_stories.StoryView r4) {
            r0.lambda$onItemClick$3(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$A0v459o1TLR1YtC4ulHSziXU5B4(AnonymousClass4 r0, MessagesController r1, TLRPC.User r2, String r3, ReactedUserHolderView r4, TL_stories.StoryView r5) {
            r0.lambda$onItemClick$0(r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$GhJ4X-DceIZPwagje96zzfd60m8, reason: not valid java name */
        public static /* synthetic */ void m19669$r8$lambda$GhJ4XDceIZPwagje96zzfd60m8(AnonymousClass4 r0, TLRPC.User r1, String r2, ReactedUserHolderView r3, TL_stories.StoryView r4) {
            r0.lambda$onItemClick$4(r1, r2, r3, r4);
        }

        public static /* synthetic */ void $r8$lambda$SYNn7a_m0D5ZJjALqQQatMUqx2I(AnonymousClass4 r0, ArrayList r1, ItemOptions r2, View r3) {
            r0.lambda$onItemClick$5(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$dT0rGYOjTZm3f9Q4VW6lefiz1Kk(AnonymousClass4 r0, MessagesController r1, TLRPC.User r2, String r3, ReactedUserHolderView r4, TL_stories.StoryView r5) {
            r0.lambda$onItemClick$1(r1, r2, r3, r4, r5);
        }

        public AnonymousClass4(SelfStoryViewsPage r1, StoryViewer r2) {
            this.this$0 = r1;
            this.val$storyViewer = r2;
        }

        private /* synthetic */ void lambda$onItemClick$0(MessagesController r3, TLRPC.User r4, String r5, ReactedUserHolderView r6, TL_stories.StoryView r7) {
            r3.getStoriesController().updateBlockUser(r4.id, true);
            SelfStoryViewsPage r32 = this.this$0;
            BulletinFactory.of(r32, r32.resourcesProvider).createSimpleBulletin(R.raw.ic_ban, LocaleController.formatString(R.string.StoryHidFromToast, new Object[]{r5})).show();
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r7) == false) goto L5;
            float r2 = 1.0f;
        L6:
            r6.animateAlpha(r2, true);
            return;
        L5:
            r2 = 0.5f;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$1(MessagesController r3, TLRPC.User r4, String r5, ReactedUserHolderView r6, TL_stories.StoryView r7) {
            r3.getStoriesController().updateBlockUser(r4.id, false);
            SelfStoryViewsPage r32 = this.this$0;
            BulletinFactory.of(r32, r32.resourcesProvider).createSimpleBulletin(R.raw.contact_check, LocaleController.formatString(R.string.StoryShownBackToToast, new Object[]{r5})).show();
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r7) == false) goto L5;
            float r2 = 1.0f;
        L6:
            r6.animateAlpha(r2, true);
            return;
        L5:
            r2 = 0.5f;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$2(MessagesController r3, TLRPC.User r4, ReactedUserHolderView r5, TL_stories.StoryView r6) {
            r3.blockPeer(r4.id);
            SelfStoryViewsPage r32 = this.this$0;
            BulletinFactory.of(r32, r32.resourcesProvider).createBanBulletin(true).show();
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r6) == false) goto L5;
            float r2 = 1.0f;
        L6:
            r5.animateAlpha(r2, true);
            return;
        L5:
            r2 = 0.5f;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$3(MessagesController r5, TLRPC.User r6, ReactedUserHolderView r7, TL_stories.StoryView r8) {
            r5.getStoriesController().updateBlockUser(r6.id, false);
            r5.unblockPeer(r6.id);
            SelfStoryViewsPage r52 = this.this$0;
            BulletinFactory.of(r52, r52.resourcesProvider).createBanBulletin(false).show();
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r8) == false) goto L5;
            float r4 = 1.0f;
        L6:
            r7.animateAlpha(r4, true);
            return;
        L5:
            r4 = 0.5f;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$4(TLRPC.User r3, String r4, ReactedUserHolderView r5, TL_stories.StoryView r6) {
            ArrayList<TLRPC.User> r0 = new ArrayList();
            r0.add(r3);
            ContactsController.getInstance(this.this$0.currentAccount).deleteContact(r0, false);
            SelfStoryViewsPage r32 = this.this$0;
            BulletinFactory.of(r32, r32.resourcesProvider).createSimpleBulletin(R.raw.ic_ban, LocaleController.formatString(R.string.DeletedFromYourContacts, new Object[]{r4})).show();
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r6) == false) goto L5;
            float r2 = 1.0f;
        L6:
            r5.animateAlpha(r2, true);
            return;
        L5:
            r2 = 0.5f;
            goto L6
        }

        private /* synthetic */ void lambda$onItemClick$5(ArrayList r3, ItemOptions r4, View r5) {
            new EmojiPacksAlert(new AnonymousClass1(this), this.this$0.getContext(), this.this$0.resourcesProvider, r3).show();
            r4.dismiss();
        }

        @Override // org.telegram.ui.Components.RecyclerListView.OnItemLongClickListener
        public boolean onItemClick(View r22, int r23) {
            if ((r22 instanceof ReactedUserHolderView) == true) goto L5;
            return false;
        L5:
            ReactedUserHolderView r4 = (ReactedUserHolderView) r22;
            StoryViewer r2 = this.val$storyViewer;
            if (r2 != null) goto L8;
        L86:
            return false;
        L8:
            if (r2.containerView == null) goto L86;
            TL_stories.StoryView r5 = this.this$0.listAdapter.items.get(r23).view;
            if (r5 != null) goto L13;
            return false;
        L13:
            MessagesController r24 = MessagesController.getInstance(this.this$0.currentAccount);
            TLRPC.User r3 = r24.getUser(Long.valueOf(r5.user_id));
            if (r3 != null) goto L17;
            return false;
        L17:
            if (r24.blockePeers.indexOfKey(r3.id) < 0) goto L19;
            boolean r9 = true;
        L21:
            if (r3.contact == false) goto L23;
        L26:
            boolean r10 = true;
        L27:
            boolean r6 = SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r5);
            boolean r11 = r24.getStoriesController().isBlocked(r5);
            boolean r12 = UserObject.isUserSelf(r3);
            if (TextUtils.isEmpty(r3.first_name) == true) goto L30;
            String r13 = r3.first_name;
        L34:
            int r14 = r13.indexOf(" ");
            if (r14 <= 2) goto L37;
            r13 = r13.substring(0, r14);
        L37:
            if (r12 == false) goto L39;
            return false;
        L39:
            ItemOptions r8 = ItemOptions.makeOptions(this.val$storyViewer.containerView, this.this$0.resourcesProvider, r22).setGravity(3).ignoreX().setScrimViewBackground(new ColorDrawable(Theme.getColor(Theme.key_dialogBackground, this.this$0.resourcesProvider))).setDimAlpha(133);
            if (r6 == false) goto L45;
            if (r11 == true) goto L45;
            if (r9 == true) goto L45;
            if (r12 == true) goto L45;
            boolean r142 = true;
        L46:
            String r42 = r13;
            ItemOptions r82 = r8.addIf(r142, R.drawable.msg_stories_myhide, LocaleController.formatString(R.string.StoryHideFrom, new Object[]{r13}), new SelfStoryViewsPage$4$$ExternalSyntheticLambda0(this, r24, r3, r42, r4, r5)).makeMultiline(false).cutTextInFancyHalf();
            if (r11 == false) goto L51;
            if (r9 == true) goto L51;
            if (r12 == true) goto L51;
            boolean r112 = true;
        L52:
            ItemOptions r132 = r82.addIf(r112, R.drawable.msg_menu_stories, LocaleController.formatString(R.string.StoryShowBackTo, new Object[]{r42}), new SelfStoryViewsPage$4$$ExternalSyntheticLambda1(this, r24, r3, r42, r4, r5)).makeMultiline(false).cutTextInFancyHalf();
            if (r10 == true) goto L57;
            if (r9 == true) goto L57;
            if (r12 == true) goto L57;
            boolean r143 = true;
        L58:
            ItemOptions r83 = r132.addIf(r143, R.drawable.msg_user_remove, LocaleController.getString(R.string.BlockUser), true, new SelfStoryViewsPage$4$$ExternalSyntheticLambda2(this, r24, r3, r4, r5));
            if (r10 == true) goto L63;
            if (r9 == false) goto L63;
            if (r12 == true) goto L63;
            boolean r92 = true;
        L64:
            ItemOptions r144 = r83.addIf(r92, R.drawable.msg_block, LocaleController.getString(R.string.Unblock), new SelfStoryViewsPage$4$$ExternalSyntheticLambda3(this, r24, r3, r4, r5));
            if (r10 == false) goto L68;
            if (r12 == true) goto L68;
            boolean r15 = true;
        L69:
            ItemOptions r0 = r144.addIf(r15, R.drawable.msg_user_remove, LocaleController.getString(R.string.StoryDeleteContact), true, new SelfStoryViewsPage$4$$ExternalSyntheticLambda4(this, r3, r42, r4, r5));
            TLRPC.Reaction r25 = r5.reaction;
            if ((r25 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L74;
            TLRPC.InputStickerSet r26 = AnimatedEmojiDrawable.getDocumentFetcher(this.this$0.currentAccount).findStickerSet(((TLRPC.TL_reactionCustomEmoji) r25).document_id);
            if (r26 == null) goto L74;
            r0.addGap();
            ArrayList r122 = new ArrayList();
            r122.add(r26);
            SelfStoryViewsPage r27 = this.this$0;
            MessageContainsEmojiButton r84 = new MessageContainsEmojiButton(r27.currentAccount, r27.getContext(), this.this$0.resourcesProvider, r122, 3);
            r84.setOnClickListener(new SelfStoryViewsPage$4$$ExternalSyntheticLambda5(this, r122, r0));
            r0.addView(r84);
            boolean r28 = true;
        L76:
            if (r0.getItemsCount() > 0) goto L79;
            if (r28 == true) goto L79;
            return false;
        L79:
            r0.show();
            this.this$0.performHapticFeedback(0, 1);     // Catch: Exception -> L87
            return true;
        L93:
            return true;
        L84:
            return true;
        L74:
            r28 = false;
        L68:
            r15 = false;
        L63:
            r92 = false;
        L57:
            r143 = false;
        L51:
            r112 = false;
        L45:
            r142 = false;
            goto L46
        L30:
            if (TextUtils.isEmpty(r3.last_name) == false) goto L32;
            r13 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L34
        L32:
            r13 = r3.last_name;
            goto L34
        L23:
            if (ContactsController.getInstance(this.this$0.currentAccount).contactsDict.get(Long.valueOf(r3.id)) != null) goto L26;
            r10 = false;
            goto L27
        L19:
            r9 = false;
            goto L21
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$5, reason: invalid class name */
    public class AnonymousClass5 extends SearchField {
        Runnable runnable;
        final /* synthetic */ SelfStoryViewsPage this$0;

        public static /* synthetic */ void $r8$lambda$Qx1IKxwscWJF0Gerj4RrLjUkhxU(AnonymousClass5 r0, String r1) {
            r0.lambda$onTextChange$0(r1);
        }

        public AnonymousClass5(SelfStoryViewsPage r1, Context r2, boolean r3, float r4, Theme.ResourcesProvider r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        private /* synthetic */ void lambda$onTextChange$0(String r3) {
            this.runnable = null;
            SelfStoryViewsPage r0 = this.this$0;
            r0.isSearchDebounce = false;
            FiltersState r02 = r0.state;
            r02.searchQuery = r3.toLowerCase();
            SelfStoryViewsPage.m19666$$Nest$mreload(this.this$0);
        }

        @Override // org.telegram.ui.Components.SearchField
        public void onTextChange(String r4) {
            Runnable r0 = this.runnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            this.runnable = new SelfStoryViewsPage$5$$ExternalSyntheticLambda0(this, r4);
            boolean r42 = TextUtils.isEmpty(r4);
            Runnable r02 = this.runnable;
            if (r42 == true) goto L8;
            AndroidUtilities.runOnUIThread(r02, 300);
        L10:
            if (this.runnable == null) goto L15;
            SelfStoryViewsPage r43 = this.this$0;
            if (r43.isSearchDebounce == true) goto L16;
            r43.isSearchDebounce = true;
            r43.listAdapter.updateRows();
            SelfStoryViewsPage r3 = this.this$0;
            r3.layoutManager.scrollToPositionWithOffset(0, -r3.recyclerListView.getPaddingTop());
            return;
        L16:
            return;
        L15:
            return;
        L8:
            r02.run();
            goto L10
        }
    }

    /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$6, reason: invalid class name */
    public class AnonymousClass6 implements Bulletin.Delegate {
        final /* synthetic */ SelfStoryViewsPage this$0;

        public AnonymousClass6(SelfStoryViewsPage r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return this.this$0.recyclerListView.getPaddingBottom();
        }
    }

    public static class FiltersState {
        boolean contactsOnly;
        String searchQuery;
        boolean sortByReactions;

        public FiltersState() {
            this.sortByReactions = true;
        }

        public boolean equals(Object r6) {
            if (this != r6) goto L6;
            return true;
        L6:
            if (r6 != null) goto L8;
        L24:
            return false;
        L8:
            if (getClass() != r6.getClass()) goto L24;
            FiltersState r62 = (FiltersState) r6;
            if (TextUtils.isEmpty(this.searchQuery) == false) goto L15;
            if (TextUtils.isEmpty(r62.searchQuery) == false) goto L15;
        L16:
            boolean r2 = true;
        L19:
            if (this.sortByReactions != r62.sortByReactions) goto L24;
            if (this.contactsOnly != r62.contactsOnly) goto L24;
            if (r2 == false) goto L24;
            return true;
        L15:
            if (Objects.equals(this.searchQuery, r62.searchQuery) == true) goto L16;
            r2 = false;
            goto L19
        }

        public int hashCode() {
            return Objects.hash(new Object[]{Boolean.valueOf(this.sortByReactions), Boolean.valueOf(this.contactsOnly), this.searchQuery});
        }

        public void set(FiltersState r2) {
            this.sortByReactions = r2.sortByReactions;
            this.contactsOnly = r2.contactsOnly;
            this.searchQuery = r2.searchQuery;
        }
    }

    public class HeaderView extends FrameLayout {
        TextView allViewersView;
        float animateFromAlpha1;
        float animateFromAlpha2;
        RectF animateFromRect;
        float animationProgress;
        ValueAnimator animator;
        private final LinearLayout buttonContainer;
        TextView contactsViewersView;
        boolean lastSortType;
        RectF rectF;
        ReplaceableIconDrawable replacableDrawable;
        int selected;
        Paint selectedPaint;
        final /* synthetic */ SelfStoryViewsPage this$0;

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$HeaderView$1, reason: invalid class name */
        public class AnonymousClass1 extends CustomPopupMenu {
            final /* synthetic */ HeaderView this$1;

            public static /* synthetic */ void $r8$lambda$Gms4xyjEYHhoLwYwDXprEuUbels(AnonymousClass1 r0, View r1) {
                r0.lambda$onCreate$0(r1);
            }

            /* renamed from: $r8$lambda$MufSAxTf77ZI-y1QGz9eaEBfRLM, reason: not valid java name */
            public static /* synthetic */ void m19672$r8$lambda$MufSAxTf77ZIy1QGz9eaEBfRLM(AnonymousClass1 r0, View r1) {
                r0.lambda$onCreate$1(r1);
            }

            public AnonymousClass1(HeaderView r1, Context r2, Theme.ResourcesProvider r3, boolean r4) {
                this.this$1 = r1;
                super(r2, r3, r4);
            }

            private /* synthetic */ void lambda$onCreate$0(View r4) {
                SelfStoryViewsPage r42 = this.this$1.this$0;
                FiltersState r0 = r42.state;
                if (r0.sortByReactions == true) goto L10;
                FiltersState r1 = r42.sharedFilterState;
                if (r1 == null) goto L7;
                r0.sortByReactions = true;
                r1.sortByReactions = true;
            L8:
                SelfStoryViewsPage.m19668$$Nest$mupdateViewState(r42, true);
                SelfStoryViewsPage.m19666$$Nest$mreload(this.this$1.this$0);
                SelfStoryViewsPage r43 = this.this$1.this$0;
                r43.onSharedStateChanged.run(r43);
                goto L10
            L7:
                r0.sortByReactions = true;
            L10:
                if (SelfStoryViewsPage.m19657$$Nest$fgetpopupMenu(this.this$1.this$0) == null) goto L13;
                SelfStoryViewsPage.m19657$$Nest$fgetpopupMenu(this.this$1.this$0).dismiss();
                return;
            }

            private /* synthetic */ void lambda$onCreate$1(View r4) {
                SelfStoryViewsPage r42 = this.this$1.this$0;
                FiltersState r0 = r42.state;
                if (r0.sortByReactions == false) goto L10;
                FiltersState r1 = r42.sharedFilterState;
                if (r1 == null) goto L7;
                r0.sortByReactions = false;
                r1.sortByReactions = false;
            L8:
                SelfStoryViewsPage.m19668$$Nest$mupdateViewState(r42, true);
                SelfStoryViewsPage.m19666$$Nest$mreload(this.this$1.this$0);
                SelfStoryViewsPage r43 = this.this$1.this$0;
                r43.onSharedStateChanged.run(r43);
                goto L10
            L7:
                r0.sortByReactions = false;
            L10:
                if (SelfStoryViewsPage.m19657$$Nest$fgetpopupMenu(this.this$1.this$0) == null) goto L13;
                SelfStoryViewsPage.m19657$$Nest$fgetpopupMenu(this.this$1.this$0).dismiss();
                return;
            }

            @Override // org.telegram.ui.Components.CustomPopupMenu
            public void onCreate(ActionBarPopupWindow.ActionBarPopupWindowLayout r9) {
                r9.setBackgroundColor(ColorUtils.blendARGB(-16777216, -1, 0.18f));
                SelfStoryViewsPage r0 = this.this$1.this$0;
                ViewsModel r1 = r0.currentModel;
                if (r1 != null) goto L5;
            L7:
                boolean r12 = false;
            L8:
                if (r12 == false) goto L11;
                int r02 = R.drawable.menu_views_reposts;
            L14:
                if (r12 == false) goto L16;
                int r5 = R.string.SortByReposts;
            L17:
                ActionBarMenuSubItem r03 = ActionBarMenuItem.addItem(r9, r02, LocaleController.getString(r5), false, this.this$1.this$0.resourcesProvider);
                if (this.this$1.this$0.state.sortByReactions == true) goto L20;
                r03.setAlpha(0.5f);
            L20:
                r03.setOnClickListener(new SelfStoryViewsPage$HeaderView$1$$ExternalSyntheticLambda0(this));
                if (this.this$1.this$0.state.sortByReactions == true) goto L23;
                int r04 = R.drawable.menu_views_recent2;
            L24:
                ActionBarMenuSubItem r05 = ActionBarMenuItem.addItem(r9, r04, LocaleController.getString(R.string.SortByTime), false, this.this$1.this$0.resourcesProvider);
                if (this.this$1.this$0.state.sortByReactions == false) goto L27;
                r05.setAlpha(0.5f);
            L27:
                r05.setOnClickListener(new SelfStoryViewsPage$HeaderView$1$$ExternalSyntheticLambda1(this));
                ActionBarPopupWindow.GapView r06 = new ActionBarPopupWindow.GapView(this.this$1.getContext(), this.this$1.this$0.resourcesProvider, Theme.key_actionBarDefaultSubmenuSeparator);
                r06.setTag(R.id.fit_width_tag, 1);
                r9.addView(r06, LayoutHelper.createLinear(-1, 8));
                if (r12 == false) goto L30;
                int r07 = R.string.StoryReactionsSortDescription;
            L31:
                ActionBarMenuItem.addText(r9, LocaleController.getString(r07), this.this$1.this$0.resourcesProvider);
                return;
            L30:
                r07 = R.string.StoryViewsSortDescription;
                goto L31
            L23:
                r04 = R.drawable.menu_views_recent;
                goto L24
            L16:
                r5 = R.string.SortByReactions;
                goto L17
            L11:
                if (r0.state.sortByReactions == false) goto L13;
                r02 = R.drawable.menu_views_reactions2;
                goto L14
            L13:
                r02 = R.drawable.menu_views_reactions;
                goto L14
            L5:
                if (r1.isChannel == false) goto L7;
                r12 = true;
                goto L8
            }

            @Override // org.telegram.ui.Components.CustomPopupMenu
            public void onDismissed() {
            }
        }

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$HeaderView$2, reason: invalid class name */
        public class AnonymousClass2 extends AnimatorListenerAdapter {
            final /* synthetic */ HeaderView this$1;

            public AnonymousClass2(HeaderView r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                HeaderView r0 = this.this$1;
                r0.animator = null;
                r0.animationProgress = 1.0f;
                r0.invalidate();
            }
        }

        /* renamed from: $r8$lambda$T5Pgpy-RH3ZAhvV-K4-a3l9TOVM, reason: not valid java name */
        public static /* synthetic */ void m19670$r8$lambda$T5PgpyRH3ZAhvVK4a3l9TOVM(HeaderView r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$sdlMsJHWe7NJwFDy93vskyKtv68(HeaderView r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$t6w8RqEPjdIODVhUdpgznfenzOU(HeaderView r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$yHH_3OmQtsde6lMxdFqM3fGtWiM(HeaderView r0, ValueAnimator r1) {
            r0.lambda$setState$3(r1);
        }

        /* renamed from: -$$Nest$fgetbuttonContainer, reason: not valid java name */
        public static /* bridge */ /* synthetic */ LinearLayout m19671$$Nest$fgetbuttonContainer(HeaderView r0) {
            return r0.buttonContainer;
        }

        public HeaderView(SelfStoryViewsPage r18, Context r19) {
            this.this$0 = r18;
            super(r19);
            this.selectedPaint = new Paint(1);
            this.animateFromRect = new RectF();
            this.rectF = new RectF();
            this.animationProgress = 1.0f;
            Paint r5 = this.selectedPaint;
            int r6 = Theme.key_listSelector;
            r5.setColor(Theme.getColor(r6, r18.resourcesProvider));
            LinearLayout r52 = new LinearLayout(r19);
            r52.setOrientation(0);
            TextView r8 = new TextView(r19);
            this.allViewersView = r8;
            r8.setText(LocaleController.getString(R.string.AllViewers));
            TextView r82 = this.allViewersView;
            int r9 = Theme.key_dialogTextBlack;
            r82.setTextColor(Theme.getColor(r9, r18.resourcesProvider));
            this.allViewersView.setTextSize(1, 14.0f);
            this.allViewersView.setTypeface(AndroidUtilities.bold());
            this.allViewersView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
            TextView r3 = new TextView(r19);
            this.contactsViewersView = r3;
            r3.setText(LocaleController.getString(R.string.Contacts));
            this.contactsViewersView.setTextColor(Theme.getColor(r9, r18.resourcesProvider));
            this.contactsViewersView.setTextSize(1, 14.0f);
            this.contactsViewersView.setTypeface(AndroidUtilities.bold());
            this.contactsViewersView.setPadding(AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(4.0f));
            r52.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
            r52.addView(this.allViewersView, LayoutHelper.createLinear(-2, -2, 0, 13, 0, 0, 0));
            r52.addView(this.contactsViewersView, LayoutHelper.createLinear(-2, -2, 0, 0, 0, 0, 0));
            LinearLayout r32 = new LinearLayout(getContext());
            this.buttonContainer = r32;
            r32.setPadding(AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f), 0);
            r32.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(26.0f), Theme.getColor(r6, r18.resourcesProvider)));
            r32.setOrientation(0);
            ReplaceableIconDrawable r1 = new ReplaceableIconDrawable(getContext());
            this.replacableDrawable = r1;
            r1.exactlyBounds = true;
            this.lastSortType = true;
            r1.setIcon(R.drawable.menu_views_reactions3, false);
            ImageView r12 = new ImageView(getContext());
            r12.setScaleType(ImageView.ScaleType.FIT_XY);
            r12.setImageDrawable(this.replacableDrawable);
            r12.setPadding(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f));
            r32.addView(r12, LayoutHelper.createLinear(26, 26));
            ImageView r13 = new ImageView(getContext());
            r13.setImageResource(R.drawable.arrow_more);
            r32.addView(r13, LayoutHelper.createLinear(16, 26));
            addView(r52, LayoutHelper.createFrame(-2, -2.0f));
            addView(r32, LayoutHelper.createFrame(-2, -2.0f, 5, 13.0f, 6.0f, 13.0f, 6.0f));
            this.allViewersView.setOnClickListener(new SelfStoryViewsPage$HeaderView$$ExternalSyntheticLambda0(this));
            this.contactsViewersView.setOnClickListener(new SelfStoryViewsPage$HeaderView$$ExternalSyntheticLambda1(this));
            r32.setOnClickListener(new SelfStoryViewsPage$HeaderView$$ExternalSyntheticLambda2(this));
        }

        private /* synthetic */ void lambda$new$0(View r3) {
            SelfStoryViewsPage r32 = this.this$0;
            FiltersState r0 = r32.state;
            if (r0.contactsOnly == true) goto L5;
            return;
        L5:
            r0.contactsOnly = false;
            SelfStoryViewsPage.m19668$$Nest$mupdateViewState(r32, true);
            SelfStoryViewsPage.m19666$$Nest$mreload(this.this$0);
        }

        private /* synthetic */ void lambda$new$1(View r3) {
            SelfStoryViewsPage r32 = this.this$0;
            FiltersState r0 = r32.state;
            if (r0.contactsOnly == false) goto L5;
            return;
        L5:
            r0.contactsOnly = true;
            SelfStoryViewsPage.m19668$$Nest$mupdateViewState(r32, true);
            SelfStoryViewsPage.m19666$$Nest$mreload(this.this$0);
        }

        private /* synthetic */ void lambda$new$2(View r5) {
            SelfStoryViewsPage.m19663$$Nest$fputpopupMenu(this.this$0, new AnonymousClass1(this, getContext(), this.this$0.resourcesProvider, false));
            CustomPopupMenu r52 = SelfStoryViewsPage.m19657$$Nest$fgetpopupMenu(this.this$0);
            LinearLayout r4 = this.buttonContainer;
            r52.show(r4, 0, (-r4.getMeasuredHeight()) - AndroidUtilities.dp(8.0f));
        }

        private /* synthetic */ void lambda$setState$3(ValueAnimator r1) {
            this.animationProgress = ((Float) this.animator.getAnimatedValue()).floatValue();
            invalidate();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r6) {
            if (SelfStoryViewsPage.m19659$$Nest$fgetshowContactsFilter(this.this$0) == false) goto L12;
            float r1 = 0.5f;
            if (this.selected != 0) goto L7;
            this.allViewersView.getHitRect(AndroidUtilities.rectTmp2);
            float r0 = 0.5f;
            r1 = 1.0f;
        L8:
            this.rectF.set(AndroidUtilities.rectTmp2);
            float r3 = this.animationProgress;
            if (r3 == 1.0f) goto L11;
            r1 = AndroidUtilities.lerp(this.animateFromAlpha1, r1, r3);
            r0 = AndroidUtilities.lerp(this.animateFromAlpha2, r0, this.animationProgress);
            RectF r2 = this.animateFromRect;
            RectF r32 = this.rectF;
            AndroidUtilities.lerp(r2, r32, this.animationProgress, r32);
        L11:
            this.allViewersView.setAlpha(r1);
            this.contactsViewersView.setAlpha(r0);
            float r02 = this.rectF.height() / 2.0f;
            r6.drawRoundRect(this.rectF, r02, r02, this.selectedPaint);
            goto L12
        L7:
            this.contactsViewersView.getHitRect(AndroidUtilities.rectTmp2);
            r0 = 1.0f;
        L12:
            super.dispatchDraw(r6);
        }

        public void setState(boolean r3, boolean r4) {
            if (r3 != this.selected) goto L6;
            if (r4 == false) goto L6;
            return;
        L6:
            ValueAnimator r0 = this.animator;
            if (r0 == null) goto L9;
            r0.removeAllListeners();
            this.animator.cancel();
        L9:
            this.selected = r3 ? 1 : 0;
            if (r4 == true) goto L13;
            this.animationProgress = 1.0f;
            invalidate();
            return;
        L13:
            this.animateFromRect.set(this.rectF);
            this.animateFromAlpha1 = this.allViewersView.getAlpha();
            this.animateFromAlpha2 = this.contactsViewersView.getAlpha();
            this.animationProgress = 0.0f;
            invalidate();
            ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.animator = r32;
            r32.addUpdateListener(new SelfStoryViewsPage$HeaderView$$ExternalSyntheticLambda3(this));
            this.animator.addListener(new AnonymousClass2(this));
            this.animator.setDuration(250);
            this.animator.setInterpolator(CubicBezierInterpolator.DEFAULT);
            this.animator.start();
        }
    }

    public static class Item {
        final TL_stories.StoryReaction reaction;
        final TL_stories.StoryView view;
        final int viewType;

        private Item(int r1) {
            this.viewType = r1;
            this.view = null;
            this.reaction = null;
        }

        private Item(int r1, TL_stories.StoryReaction r2) {
            this.viewType = r1;
            this.view = null;
            this.reaction = r2;
        }

        public /* synthetic */ Item(int r1, TL_stories.StoryReaction r2, SelfStoryViewsPageIA r3) {
            this(r1, r2);
        }

        private Item(int r1, TL_stories.StoryView r2) {
            this.viewType = r1;
            this.view = r2;
            this.reaction = null;
        }

        public /* synthetic */ Item(int r1, TL_stories.StoryView r2, SelfStoryViewsPageIA r3) {
            this(r1, r2);
        }

        public /* synthetic */ Item(int r1, SelfStoryViewsPageIA r2) {
            this(r1);
        }
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        ArrayList<Item> items;
        final /* synthetic */ SelfStoryViewsPage this$0;

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$ListAdapter$1, reason: invalid class name */
        public class AnonymousClass1 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass1(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(SelfStoryViewsPage.m19655$$Nest$fgetTOP_PADDING(this.this$1.this$0)), TLObject.FLAG_30));
            }
        }

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$ListAdapter$2, reason: invalid class name */
        public class AnonymousClass2 extends ReactedUserHolderView {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass2(ListAdapter r1, int r2, int r3, Context r4, Theme.ResourcesProvider r5, boolean r6, boolean r7) {
                this.this$1 = r1;
                super(r2, r3, r4, r5, r6, r7);
            }

            @Override // org.telegram.ui.Cells.ReactedUserHolderView
            public void openStory(long r2, Runnable r4) {
                BaseFragment r0 = LaunchActivity.getLastFragment();
                if (r0 != null) goto L5;
                return;
            L5:
                StoryViewer r02 = r0.createOverlayStoryViewer();
                r02.doOnAnimationReady(r4);
                r02.open(getContext(), r2, StoriesListPlaceProvider.of(this.this$1.this$0.recyclerListView));
            }
        }

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$ListAdapter$3, reason: invalid class name */
        public class AnonymousClass3 extends View {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass3(ListAdapter r1, Context r2) {
                this.this$1 = r1;
                super(r2);
            }

            @Override // android.view.View
            public void onMeasure(int r2, int r3) {
                int r32 = this.this$1.this$0.layoutManager.getLastItemHeight();
                if (r32 >= this.this$1.this$0.recyclerListView.getPaddingTop()) goto L5;
            L7:
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(r32, TLObject.FLAG_30));
                return;
            L5:
                if (SelfStoryViewsPage.m19660$$Nest$fgetshowSearch(this.this$1.this$0) == true) goto L7;
                r32 = 0;
                goto L7
            }
        }

        /* renamed from: org.telegram.ui.Stories.SelfStoryViewsPage$ListAdapter$4, reason: invalid class name */
        public class AnonymousClass4 extends StickerEmptyView {
            final /* synthetic */ ListAdapter this$1;

            public AnonymousClass4(ListAdapter r1, Context r2, View r3, int r4, Theme.ResourcesProvider r5) {
                this.this$1 = r1;
                super(r2, r3, r4, r5);
            }

            @Override // android.widget.FrameLayout, android.view.View
            public void onMeasure(int r2, int r3) {
                super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec((SelfStoryViewsPage.m19656$$Nest$fgetmeasuerdHeight(this.this$1.this$0) - this.this$1.this$0.recyclerListView.getPaddingTop()) - AndroidUtilities.dp(SelfStoryViewsPage.m19655$$Nest$fgetTOP_PADDING(this.this$1.this$0)), TLObject.FLAG_30));
            }
        }

        public static /* synthetic */ void $r8$lambda$APz6RNQps02R8NWi6nw8hB2MZxQ(ListAdapter r0) {
            r0.lambda$onCreateViewHolder$0();
        }

        /* renamed from: $r8$lambda$K70ocoQAJSPOIDsMi-Av3dVwrmE, reason: not valid java name */
        public static /* synthetic */ void m19673$r8$lambda$K70ocoQAJSPOIDsMiAv3dVwrmE(SelfStoryViewsPage r0) {
            SelfStoryViewsPage.m19667$$Nest$mshowPremiumAlert(r0);
        }

        private ListAdapter(SelfStoryViewsPage r1) {
            this.this$0 = r1;
            this.items = new ArrayList();
        }

        public /* synthetic */ ListAdapter(SelfStoryViewsPage r1, SelfStoryViewsPageIA r2) {
            this(r1);
        }

        private /* synthetic */ void lambda$onCreateViewHolder$0() {
            SelfStoryViewsPage.m19667$$Nest$mshowPremiumAlert(this.this$0);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return this.items.size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r1) {
            return this.items.get(r1).viewType;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r1) {
            if (r1.getItemViewType() != 1) goto L5;
            return true;
        L5:
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r21, int r22) {
            if (r21.getItemViewType() != 1) goto L124;
            if (r22 >= 0) goto L6;
            return;
        L6:
            if (r22 >= this.items.size()) goto L127;
            Item r2 = this.items.get(r22);
            ReactedUserHolderView r5 = (ReactedUserHolderView) r21.itemView;
            TL_stories.StoryView r4 = r2.view;
            if (r4 != null) goto L11;
            TL_stories.StoryReaction r42 = r2.reaction;
            if (r42 == null) goto L27;
            TLRPC.Peer r7 = r42.peer_id;
            if ((r42 instanceof TL_stories.TL_storyReactionPublicForward) == false) goto L26;
            TLRPC.Message r43 = r42.message;
            if (r43 == null) goto L26;
            TLRPC.Peer r44 = r43.peer_id;
        L28:
            long r72 = DialogObject.getPeerDialogId(r44);
            SelfStoryViewsPage r11 = this.this$0;
            if (r72 < 0) goto L31;
            TLRPC.User r45 = MessagesController.getInstance(r11.currentAccount).getUser(Long.valueOf(r72));
            long r112 = r72;
            TLRPC.Chat r73 = null;
        L32:
            boolean r15 = this.this$0.defaultModel.animateDateForUsers.remove(Long.valueOf(r112));
            TL_stories.StoryView r8 = r2.view;
            if (r8 == null) goto L80;
            TLRPC.Reaction r74 = r8.reaction;
            if (r74 == null) goto L43;
            ReactionsLayoutInBubble.VisibleReaction r75 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r74);
            if (r75 == null) goto L43;
            String r76 = r75.emojicon;
            if (r76 == null) goto L43;
            if (r76.equals("❤") == false) goto L43;
            long r18 = 0;
            boolean r9 = true;
        L44:
            TL_stories.StoryView r77 = r2.view;
            if ((r77 instanceof TL_stories.TL_storyViewPublicRepost) == false) goto L47;
            int r3 = 11;
            TLRPC.User r6 = r45;
            int r46 = 12;
            r5.setUserReaction(r6, null, null, r9, 0, r77.story, false, true, r15);
        L63:
            if (r22 >= (this.items.size() - 1)) goto L66;
            int r1 = this.items.get(r22 + 1).viewType;
        L67:
            if (r1 == 1) goto L72;
            if (r1 == r3) goto L72;
            if (r1 == r46) goto L72;
            boolean r32 = false;
        L73:
            r5.drawDivider = r32;
            if (SelfStoryViewsPage.m19665$$Nest$misStoryShownToUser(this.this$0, r2.view) == false) goto L77;
            float r113 = 1.0f;
        L78:
            r5.animateAlpha(r113, false);
            return;
        L77:
            r113 = 0.5f;
        L72:
            r32 = true;
            goto L73
        L66:
            r1 = -1;
            goto L67
        L47:
            TLRPC.Reaction r82 = null;
            r3 = 11;
            TLRPC.User r62 = r45;
            r46 = 12;
            if ((r77 instanceof TL_stories.TL_storyViewPublicForward) == false) goto L58;
            TLRPC.Message r78 = r77.message;
            if (r78 == null) goto L52;
            long r10 = r78.date;
        L53:
            SelfStoryViewsView.StoryItemInternal r79 = this.this$0.storyItem;
            if (r79 != null) goto L56;
            TL_stories.StoryItem r12 = null;
        L57:
            r5.setUserReaction(r62, null, null, r9, r10, r12, true, true, r15);
            goto L63
        L56:
            r12 = r79.storyItem;
            goto L57
        L52:
            r10 = r18;
            goto L53
        L58:
            if (r9 == true) goto L61;
            r82 = r77.reaction;
        L61:
            r5.setUserReaction(r62, null, r82, r9, r77.date, null, false, true, r15);
        L43:
            r18 = 0;
            r9 = false;
            goto L44
        L80:
            TLRPC.Reaction r83 = null;
            TLRPC.User r63 = r45;
            TL_stories.StoryReaction r23 = r2.reaction;
            if (r23 != null) goto L83;
            return;
        L83:
            if ((r23 instanceof TL_stories.TL_storyReaction) == false) goto L99;
            TL_stories.TL_storyReaction r24 = (TL_stories.TL_storyReaction) r23;
            TLRPC.Reaction r92 = r24.reaction;
            if (r92 == null) goto L93;
            ReactionsLayoutInBubble.VisibleReaction r93 = ReactionsLayoutInBubble.VisibleReaction.fromTL(r92);
            if (r93 == null) goto L93;
            String r94 = r93.emojicon;
            if (r94 == null) goto L93;
            if (r94.equals("❤") == false) goto L93;
            boolean r95 = true;
        L94:
            if (r95 == true) goto L97;
            r83 = r24.reaction;
        L97:
            r5.setUserReaction(r63, r73, r83, r95, r24.date, null, false, true, r15);
        L113:
            if (r22 >= (this.items.size() - 1)) goto L115;
            int r0 = this.items.get(r22 + 1).viewType;
        L116:
            if (r0 == 1) goto L121;
            if (r0 == 11) goto L121;
            if (r0 == 12) goto L121;
            boolean r33 = false;
        L122:
            r5.drawDivider = r33;
            r5.animateAlpha(1.0f, false);
            return;
        L121:
            r33 = true;
            goto L122
        L115:
            r0 = -1;
        L93:
            r95 = false;
            goto L94
        L99:
            if ((r23 instanceof TL_stories.TL_storyReactionPublicRepost) == false) goto L102;
            r5.setUserReaction(r63, r73, null, false, 0, ((TL_stories.TL_storyReactionPublicRepost) r23).story, false, true, r15);
            goto L113
        L102:
            if ((r23 instanceof TL_stories.TL_storyReactionPublicForward) == false) goto L113;
            TLRPC.Message r25 = r23.message;
            if (r25 == null) goto L106;
            long r102 = r25.date;
        L107:
            SelfStoryViewsView.StoryItemInternal r26 = this.this$0.storyItem;
            if (r26 != null) goto L110;
            TL_stories.StoryItem r122 = null;
        L111:
            r5.setUserReaction(r63, r73, null, false, r102, r122, true, true, r15);
            goto L113
        L110:
            r122 = r26.storyItem;
            goto L111
        L106:
            r102 = 0;
            goto L107
        L31:
            TLRPC.Chat r47 = MessagesController.getInstance(r11.currentAccount).getChat(Long.valueOf(-r72));
            r112 = r72;
            r73 = r47;
            r45 = null;
        L26:
            r44 = r7;
            goto L28
        L27:
            r44 = null;
            goto L28
        L11:
            if ((r4 instanceof TL_stories.TL_storyViewPublicRepost) == false) goto L14;
            r44 = r4.peer_id;
            goto L28
        L14:
            if ((r4 instanceof TL_stories.TL_storyViewPublicForward) == false) goto L18;
            TLRPC.Message r48 = r4.message;
            if (r48 == null) goto L18;
            r44 = r48.peer_id;
        L18:
            r44 = new TLRPC.TL_peerUser();
            r44.user_id = r2.view.user_id;
            goto L28
        L127:
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r12, int r13) {
            int r2 = 1;
            switch(r13) {
                case 0: goto L44;
                case 1: goto L43;
                case 2: goto L4;
                case 3: goto L42;
                case 4: goto L41;
                case 5: goto L13;
                case 6: goto L10;
                case 7: goto L13;
                case 8: goto L13;
                case 9: goto L4;
                case 10: goto L13;
                case 11: goto L5;
                case 12: goto L5;
                default: goto L4;
            };
        L4:
            View r0 = new AnonymousClass3(this, this.this$0.getContext());
        L46:
            return new RecyclerListView.Holder(r0);
        L5:
            LinkSpanDrawable.LinksTextView r02 = new LinkSpanDrawable.LinksTextView(this.this$0.getContext());
            r02.setTextSize(1, 13.0f);
            r02.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText, this.this$0.resourcesProvider));
            r02.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText, this.this$0.resourcesProvider));
            int r3 = AndroidUtilities.dp(16.0f);
            int r4 = AndroidUtilities.dp(21.0f);
            r02.setPadding(r4, r3, r4, r3);
            r02.setMaxLines(Integer.MAX_VALUE);
            r02.setGravity(17);
            r02.setDisablePaddingsOffsetY(true);
            if (r13 != 11) goto L8;
            r02.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.StoryViewsPremiumHint), new SelfStoryViewsPage$ListAdapter$$ExternalSyntheticLambda0(this)));
        L9:
            r02.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            r0 = r02;
            goto L46
        L8:
            r02.setText(LocaleController.getString(R.string.ServerErrorViewersFull));
            goto L9
        L10:
            FlickerLoadingView r32 = new FlickerLoadingView(this.this$0.getContext(), this.this$0.resourcesProvider);
            r32.setIsSingleCell(true);
            r32.setIgnoreHeightCheck(true);
            r32.setItemsCount(20);
            r32.setViewType(28);
            r32.showDate(false);
        L11:
            r0 = r32;
            goto L46
        L41:
            r32 = new FlickerLoadingView(this.this$0.getContext(), this.this$0.resourcesProvider);
            r32.setIsSingleCell(true);
            r32.setViewType(28);
            r32.showDate(false);
            goto L11
        L42:
            r0 = new FixedHeightEmptyCell(this.this$0.getContext(), 70);
            goto L46
        L43:
            int r22 = ReactedUserHolderView.STYLE_STORY;
            SelfStoryViewsPage r33 = this.this$0;
            r0 = new AnonymousClass2(this, r22, r33.currentAccount, r33.getContext(), this.this$0.resourcesProvider, false, true);
            goto L46
        L44:
            r0 = new AnonymousClass1(this, this.this$0.getContext());
            goto L46
        L13:
            if (this.this$0.defaultModel.isExpiredViews == false) goto L16;
            r2 = 12;
        L15:
            int r42 = r2;
        L23:
            AnonymousClass4 r03 = new AnonymousClass4(this, this.this$0.getContext(), null, r42, this.this$0.resourcesProvider);
            if (r13 != 7) goto L26;
            r03.title.setVisibility(8);
            r03.setSubtitle(LocaleController.getString(R.string.NoResult));
        L40:
            r03.showProgress(false, false);
            r0 = r03;
            goto L46
        L26:
            if (r13 != 8) goto L28;
            r03.title.setVisibility(8);
            r03.setSubtitle(LocaleController.getString(R.string.NoContactsViewed));
            goto L40
        L28:
            if (r13 != 10) goto L30;
            r03.title.setVisibility(0);
            r03.title.setText(LocaleController.getString(R.string.ServerErrorViewersTitle));
            r03.setSubtitle(LocaleController.getString(R.string.ServerErrorViewers));
            goto L40
        L30:
            boolean r23 = this.this$0.defaultModel.isExpiredViews;
            SpoilersTextView r34 = r03.title;
            if (r23 == false) goto L36;
            r34.setVisibility(8);
            SpannableStringBuilder r24 = new SpannableStringBuilder();
            r24.append(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ExpiredViewsStub)));
            if (MessagesController.getInstance(this.this$0.currentAccount).premiumFeaturesBlocked() == true) goto L35;
            r24.append("\n\n");
            r24.append(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ExpiredViewsStubPremiumDescription), new SelfStoryViewsPage$ListAdapter$$ExternalSyntheticLambda1(this.this$0)));
            r03.createButtonLayout(LocaleController.getString(R.string.LearnMore), new SelfStoryViewsPage$ListAdapter$$ExternalSyntheticLambda1(this.this$0));
        L35:
            r03.subtitle.setText(r24);
            goto L40
        L36:
            r34.setVisibility(0);
            boolean r1 = this.this$0.defaultModel.isChannel;
            SpoilersTextView r25 = r03.title;
            if (r1 == false) goto L39;
            r25.setText(LocaleController.getString(R.string.NoReactions));
            r03.setSubtitle(LocaleController.getString(R.string.NoReactionsStub));
            goto L40
        L39:
            r25.setText(LocaleController.getString(R.string.NoViews));
            r03.setSubtitle(LocaleController.getString(R.string.NoViewsStub));
            goto L40
        L16:
            if (r13 == 10) goto L15;
            if (r13 == 7) goto L15;
            if (r13 == 8) goto L15;
            if (r13 == 5) goto L15;
            r42 = 0;
            goto L23
        }

        public void updateRows() {
            this.items.clear();
            SelfStoryViewsPage r0 = this.this$0;
            ViewsModel r1 = r0.currentModel;
            boolean r02 = r0.isSearchDebounce;
            ArrayList<Item> r2 = this.items;
            int r4 = 0;
            if (r02 == false) goto L5;
            r2.add(new Item(0, null));
            this.items.add(new Item(6, null));
        L60:
            this.items.add(new Item(9, null));
            notifyDataSetChanged();
            return;
        L5:
            r2.add(new Item(0, null));
            if (r1 != null) goto L8;
        L30:
            if (r1 != null) goto L32;
        L39:
            if (r1 != null) goto L41;
        L48:
            if (r1 != null) goto L50;
        L52:
            if (r1 == null) goto L60;
            if (r1.getCount() >= r1.totalCount) goto L60;
            if (TextUtils.isEmpty(r1.state.searchQuery) == false) goto L60;
            if (r1.state.contactsOnly == true) goto L60;
            this.items.add(new Item(12, null));
            goto L60
        L50:
            if (r1.showReactionOnly == false) goto L52;
            this.items.add(new Item(11, null));
            goto L60
        L41:
            if (r1.loading == false) goto L43;
        L44:
            int r03 = r1.getCount();
            ArrayList<Item> r12 = this.items;
            if (r03 > 0) goto L47;
            r12.add(new Item(6, null));
            goto L60
        L47:
            r12.add(new Item(4, null));
            goto L60
        L43:
            if (r1.hasNext == false) goto L48;
        L32:
            if (r1.isChannel == false) goto L37;
        L34:
            if (r4 >= r1.reactions.size()) goto L39;
            this.items.add(new Item(1, r1.reactions.get(r4), null));
            r4 = r4 + 1;
        L37:
            if (r4 >= r1.views.size()) goto L39;
            this.items.add(new Item(1, r1.views.get(r4), null));
            r4 = r4 + 1;
            goto L37
        L8:
            if (r1.getCount() > 0) goto L30;
            if (r1.isExpiredViews == true) goto L16;
            if (r1.loading == true) goto L30;
            if (r1.hasNext == true) goto L30;
        L16:
            if (TextUtils.isEmpty(r1.state.searchQuery) == true) goto L19;
            this.items.add(new Item(7, null));
            goto L60
        L19:
            if (r1.isExpiredViews == false) goto L21;
            this.items.add(new Item(5, null));
            goto L60
        L21:
            int r04 = r1.totalCount;
            if (r04 > 0) goto L24;
        L26:
            ArrayList<Item> r13 = this.items;
            if (r04 <= 0) goto L29;
            r13.add(new Item(10, null));
            goto L60
        L29:
            r13.add(new Item(5, null));
            goto L60
        L24:
            if (r1.state.contactsOnly == false) goto L26;
            this.items.add(new Item(8, null));
            goto L60
        }
    }

    public class RecyclerListViewInner extends RecyclerListView implements StoriesListPlaceProvider.ClippedView {
        final /* synthetic */ SelfStoryViewsPage this$0;

        public RecyclerListViewInner(SelfStoryViewsPage r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Stories.StoriesListPlaceProvider.ClippedView
        public void updateClip(int[] r3) {
            r3[0] = AndroidUtilities.dp(SelfStoryViewsPage.m19655$$Nest$fgetTOP_PADDING(this.this$0));
            r3[1] = getMeasuredHeight();
        }
    }

    public static class ViewsModel {
        HashSet<Long> animateDateForUsers;
        int currentAccount;
        private long dialogId;
        boolean hasNext;
        boolean initial;
        public final boolean isChannel;
        boolean isExpiredViews;
        ArrayList<SelfStoryViewsPage> listeners;
        boolean loading;
        String offset;
        ArrayList<TL_stories.StoryView> originalViews;
        ArrayList<TL_stories.StoryReaction> reactions;
        int reqId;
        boolean showReactionOnly;
        FiltersState state;
        TL_stories.StoryItem storyItem;
        public int totalCount;
        boolean useLocalFilters;
        ArrayList<TL_stories.StoryView> views;

        /* renamed from: $r8$lambda$keJViVULdsq7T-mm19unD9O4elA, reason: not valid java name */
        public static /* synthetic */ int m19674$r8$lambda$keJViVULdsq7Tmm19unD9O4elA(TL_stories.StoryView r0) {
            return -r0.date;
        }

        public static /* synthetic */ void $r8$lambda$qHSazAMsWEta2v_5p35B_LurRDI(ViewsModel r0, int[] r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadNext$3(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$wZAXknthT39xWJdMvWVnI6nFL-g, reason: not valid java name */
        public static /* synthetic */ void m19675$r8$lambda$wZAXknthT39xWJdMvWVnI6nFLg(ViewsModel r0, int[] r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadNext$1(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$xfYhUIeGnyqVprOz2zIUt2M4WZc(ViewsModel r0, int[] r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadNext$2(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$zbZLiEWElvZwLURxxQ7JmcnJtyU(ViewsModel r0, int[] r1, TLObject r2, TLRPC.TL_error r3) {
            r0.lambda$loadNext$0(r1, r2, r3);
        }

        public ViewsModel(int r4, long r5, TL_stories.StoryItem r7, boolean r8) {
            this.views = new ArrayList();
            this.originalViews = new ArrayList();
            this.reactions = new ArrayList();
            this.hasNext = true;
            this.reqId = -1;
            this.animateDateForUsers = new HashSet();
            this.listeners = new ArrayList();
            this.state = new FiltersState();
            this.currentAccount = r4;
            this.storyItem = r7;
            if (r5 >= 0) goto L5;
            boolean r1 = true;
        L6:
            this.isChannel = r1;
            this.dialogId = r5;
            TL_stories.StoryViews r52 = r7.views;
            if (r52 != null) goto L9;
            int r53 = 0;
        L10:
            this.totalCount = r53;
            if (r53 >= 200) goto L14;
            this.useLocalFilters = true;
        L14:
            if (StoriesUtilities.hasExpiredViews(r7) == true) goto L16;
        L18:
            boolean r54 = false;
        L19:
            this.isExpiredViews = r54;
            if (r54 == false) goto L27;
            TL_stories.StoryViews r55 = r7.views;
            if (r55 == null) goto L27;
            if (r55.reactions_count <= 0) goto L27;
            this.isExpiredViews = false;
            this.showReactionOnly = true;
        L27:
            if (this.isExpiredViews == true) goto L39;
            this.initial = true;
            if (r7.views == null) goto L43;
            if (r8 == false) goto L44;
            int r56 = 0;
        L33:
            if (r56 >= r7.views.recent_viewers.size()) goto L45;
            Long r6 = r7.views.recent_viewers.get(r56);
            long r0 = r6.longValue();
            if (MessagesController.getInstance(r4).getUser(r6) == null) goto L38;
            TL_stories.TL_storyView r62 = new TL_stories.TL_storyView();
            r62.user_id = r0;
            r62.date = 0;
            this.views.add(r62);
        L38:
            r56 = r56 + 1;
            goto L33
        L45:
            return;
        L44:
            return;
        L43:
            return;
        L39:
            return;
        L16:
            if (UserConfig.getInstance(r4).isPremium() == true) goto L18;
            r54 = true;
            goto L19
        L9:
            r53 = r52.views_count;
            goto L10
        L5:
            r1 = false;
            goto L6
        }

        private void applyLocalFilter() {
            if (this.isChannel == true) goto L56;
            this.views.clear();
            FiltersState r0 = this.state;
            if (r0.contactsOnly == true) goto L12;
            if (TextUtils.isEmpty(r0.searchQuery) == false) goto L12;
            this.views.addAll(this.originalViews);
        L49:
            if (this.state.sortByReactions == true) goto L55;
            Collections.sort(this.views, Comparator.comparingInt(new SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda2()));
            return;
        L55:
            return;
        L12:
            if (TextUtils.isEmpty(this.state.searchQuery) == true) goto L14;
            String r02 = this.state.searchQuery.trim().toLowerCase();
            String r1 = LocaleController.getInstance().getTranslitString(r02);
            String r2 = " " + r02;
            String r3 = " " + r1;
        L15:
            int r5 = 0;
        L17:
            if (r5 >= this.originalViews.size()) goto L49;
            TLRPC.User r6 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(this.originalViews.get(r5).user_id));
            if (this.state.contactsOnly == false) goto L24;
            if (r6 != null) goto L22;
        L23:
            boolean r7 = false;
        L25:
            if (r7 == false) goto L45;
            if (r02 == null) goto L45;
            String r8 = ContactsController.formatName(r6.first_name, r6.last_name).toLowerCase();
            String r62 = UserObject.getPublicUsername(r6);
            String r9 = AndroidUtilities.translitSafe(r8);
            if (r8 != null) goto L30;
        L33:
            if (r9 != null) goto L35;
        L38:
            if (r62 != null) goto L40;
        L44:
            r7 = false;
            goto L45
        L40:
            if (r62.startsWith(r1) == true) goto L45;
            if (r62.contains(r3) == false) goto L44;
        L35:
            if (r9.startsWith(r1) == true) goto L45;
            if (r9.contains(r3) == true) goto L45;
        L30:
            if (r8.startsWith(r02) == true) goto L45;
            if (r8.contains(r2) == false) goto L33;
        L45:
            if (r7 == false) goto L47;
            this.views.add(this.originalViews.get(r5));
        L47:
            r5 = r5 + 1;
            goto L17
        L22:
            if (r6.contact == false) goto L23;
        L24:
            r7 = true;
            goto L25
        L14:
            r02 = null;
            r1 = null;
            r2 = null;
            r3 = null;
            goto L15
        }

        private /* synthetic */ void lambda$loadNext$0(int[] r7, TLObject r8, TLRPC.TL_error r9) {
            int r0 = 0;
            if (r7[0] == this.reqId) goto L6;
            FileLog.d("SelfStoryViewsPage reactions " + this.storyItem.id + " localId != reqId");
            return;
        L6:
            this.loading = false;
            this.reqId = -1;
            if (r8 == null) goto L32;
            TL_stories.TL_storyReactionsList r82 = (TL_stories.TL_storyReactionsList) r8;
            MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
            boolean r3 = true;
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r82.users, r82.chats, true, false);
            if (this.initial == false) goto L15;
            this.initial = false;
            int r72 = 0;
        L12:
            if (r72 >= this.reactions.size()) goto L14;
            this.animateDateForUsers.add(Long.valueOf(DialogObject.getPeerDialogId(this.reactions.get(r72).peer_id)));
            r72 = r72 + 1;
            goto L12
        L14:
            this.reactions.clear();
            this.originalViews.clear();
        L15:
            this.reactions.addAll(r82.reactions);
            if (r82.reactions.isEmpty() == true) goto L18;
            this.hasNext = true;
        L19:
            String r73 = r82.next_offset;
            this.offset = r73;
            if (TextUtils.isEmpty(r73) == false) goto L22;
            this.hasNext = false;
        L22:
            TL_stories.StoryItem r74 = this.storyItem;
            if (r74.views != null) goto L25;
            r74.views = new TL_stories.TL_storyViews();
        L25:
            int r75 = this.totalCount;
            int r83 = r82.count;
            if (r75 != r83) goto L29;
            r3 = false;
        L29:
            this.totalCount = r83;
            if (r3 == false) goto L37;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        L37:
            FileLog.d("SelfStoryViewsPage reactions " + this.storyItem.id + " response  totalItems " + this.reactions.size() + " has next " + this.hasNext);
        L39:
            if (r0 >= this.listeners.size()) goto L42;
            this.listeners.get(r0).onDataRecieved(this);
            r0 = r0 + 1;
            goto L39
        L42:
            if (this.reactions.size() < 20) goto L44;
            return;
        L44:
            if (this.hasNext == false) goto L50;
            loadNext();
            return;
        L50:
            return;
        L18:
            this.hasNext = false;
            goto L19
        L32:
            if (r9 != null) goto L34;
        L36:
            this.hasNext = false;
            goto L37
        L34:
            if ("MSG_ID_INVALID".equals(r9.text) == false) goto L36;
            this.totalCount = 0;
            goto L36
        }

        private /* synthetic */ void lambda$loadNext$1(int[] r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda4(this, r2, r3, r4));
        }

        private /* synthetic */ void lambda$loadNext$2(int[] r7, TLObject r8, TLRPC.TL_error r9) {
            int r0 = 0;
            if (r7[0] == this.reqId) goto L6;
            FileLog.d("SelfStoryViewsPage " + this.storyItem.id + " localId != reqId");
            return;
        L6:
            this.loading = false;
            this.reqId = -1;
            if (r8 == null) goto L43;
            TL_stories.StoryViewsList r82 = (TL_stories.StoryViewsList) r8;
            MessagesController.getInstance(this.currentAccount).getStoriesController().applyStoryViewsBlocked(r82);
            MessagesController.getInstance(this.currentAccount).putUsers(r82.users, false);
            MessagesController.getInstance(this.currentAccount).putChats(r82.chats, false);
            boolean r3 = true;
            MessagesStorage.getInstance(this.currentAccount).putUsersAndChats(r82.users, r82.chats, true, false);
            if (this.initial == false) goto L16;
            this.initial = false;
            int r72 = 0;
        L12:
            if (r72 >= this.views.size()) goto L14;
            this.animateDateForUsers.add(Long.valueOf(this.views.get(r72).user_id));
            r72 = r72 + 1;
            goto L12
        L14:
            this.views.clear();
            this.originalViews.clear();
        L16:
            if (this.useLocalFilters == false) goto L18;
            this.originalViews.addAll(r82.views);
            applyLocalFilter();
        L20:
            if (r82.views.isEmpty() == true) goto L22;
            this.hasNext = true;
        L23:
            String r73 = r82.next_offset;
            this.offset = r73;
            if (TextUtils.isEmpty(r73) == false) goto L26;
            this.hasNext = false;
        L26:
            TL_stories.StoryItem r74 = this.storyItem;
            if (r74.views != null) goto L29;
            r74.views = new TL_stories.TL_storyViews();
        L29:
            int r75 = r82.count;
            TL_stories.StoryViews r92 = this.storyItem.views;
            if (r75 <= r92.views_count) goto L36;
            r92.recent_viewers.clear();
            int r76 = 0;
        L32:
            int r93 = Math.min(3, r82.users.size());
            TL_stories.StoryItem r1 = this.storyItem;
            if (r76 >= r93) goto L35;
            r1.views.recent_viewers.add(Long.valueOf(r82.users.get(r76).id));
            r76 = r76 + 1;
            goto L32
        L35:
            r1.views.views_count = r82.count;
            boolean r77 = true;
        L37:
            TL_stories.StoryViews r94 = this.storyItem.views;
            int r12 = r94.reactions_count;
            int r83 = r82.reactions_count;
            if (r12 == r83) goto L40;
            r94.reactions_count = r83;
        L41:
            if (r3 == false) goto L48;
            NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.storiesUpdated, new Object[0]);
        L48:
            FileLog.d("SelfStoryViewsPage " + this.storyItem.id + " response  totalItems " + this.views.size() + " has next " + this.hasNext);
        L50:
            if (r0 >= this.listeners.size()) goto L53;
            this.listeners.get(r0).onDataRecieved(this);
            r0 = r0 + 1;
            goto L50
        L53:
            if (this.views.size() < 20) goto L55;
            return;
        L55:
            if (this.hasNext == false) goto L62;
            loadNext();
            return;
        L62:
            return;
        L40:
            r3 = r77;
            goto L41
        L36:
            r77 = false;
            goto L37
        L22:
            this.hasNext = false;
            goto L23
        L18:
            this.views.addAll(r82.views);
            goto L20
        L43:
            if (r9 != null) goto L45;
        L47:
            this.hasNext = false;
            goto L48
        L45:
            if ("MSG_ID_INVALID".equals(r9.text) == false) goto L47;
            this.totalCount = 0;
            goto L47
        }

        private /* synthetic */ void lambda$loadNext$3(int[] r2, TLObject r3, TLRPC.TL_error r4) {
            AndroidUtilities.runOnUIThread(new SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda3(this, r2, r3, r4));
        }

        public void addListener(SelfStoryViewsPage r2) {
            if (this.listeners.contains(r2) == true) goto L6;
            this.listeners.add(r2);
            return;
        }

        public int getCount() {
            if (this.isChannel == false) goto L7;
            ArrayList r1 = this.reactions;
        L6:
            return r1.size();
        L7:
            r1 = this.views;
            goto L6
        }

        public void loadNext() {
            if (this.loading == false) goto L5;
            return;
        L5:
            if (this.hasNext == true) goto L7;
            return;
        L7:
            if (this.isExpiredViews == true) goto L41;
            int r2 = 100;
            if (this.isChannel == false) goto L22;
            TL_stories.TL_getStoryReactionsList r0 = new TL_stories.TL_getStoryReactionsList();
            r0.forwards_first = this.state.sortByReactions;
            r0.id = this.storyItem.id;
            r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            if (this.initial == false) goto L14;
        L15:
            r2 = 20;
        L16:
            r0.limit = r2;
            String r22 = this.offset;
            r0.offset = r22;
            if (r22 != null) goto L19;
            r0.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        L20:
            this.loading = true;
            FileLog.d("SelfStoryViewsPage reactions load next " + this.storyItem.id + " " + this.initial + " offset=" + r0.offset);
            int r02 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda0(this, r2));
            this.reqId = r02;
            int[] r23 = {r02};
            return;
        L19:
            r0.flags |= 2;
            goto L20
        L14:
            if (this.reactions.size() >= 20) goto L16;
        L22:
            TL_stories.TL_stories_getStoryViewsList r03 = new TL_stories.TL_stories_getStoryViewsList();
            r03.id = this.storyItem.id;
            r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
            if (this.useLocalFilters == false) goto L25;
            r03.q = _UrlKt.FRAGMENT_ENCODE_SET;
            r03.just_contacts = false;
            r03.reactions_first = true;
        L30:
            if (this.initial == false) goto L32;
        L33:
            r2 = 20;
        L34:
            r03.limit = r2;
            String r24 = this.offset;
            r03.offset = r24;
            if (r24 != null) goto L37;
            r03.offset = _UrlKt.FRAGMENT_ENCODE_SET;
        L37:
            this.loading = true;
            FileLog.d("SelfStoryViewsPage load next " + this.storyItem.id + " " + this.initial + " offset=" + r03.offset + " q" + r03.q + " " + r03.just_contacts + " " + r03.reactions_first);
            int r04 = ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new SelfStoryViewsPage$ViewsModel$$ExternalSyntheticLambda1(this, r2));
            this.reqId = r04;
            int[] r25 = {r04};
            return;
        L32:
            if (this.views.size() >= 20) goto L34;
        L25:
            String r8 = this.state.searchQuery;
            r03.q = r8;
            if (TextUtils.isEmpty(r8) == true) goto L28;
            r03.flags |= 2;
        L28:
            FiltersState r82 = this.state;
            r03.just_contacts = r82.contactsOnly;
            r03.reactions_first = r82.sortByReactions;
            goto L30
        }

        public void release() {
            if (this.reqId < 0) goto L5;
            ConnectionsManager.getInstance(this.currentAccount).cancelRequest(this.reqId, false);
        L5:
            this.reqId = -1;
        }

        public void reloadIfNeed(FiltersState r2, boolean r3, boolean r4) {
            FiltersState r0 = new FiltersState();
            r0.set(r2);
            int r22 = 0;
            if (r3 == true) goto L6;
            r0.contactsOnly = false;
        L6:
            if (r4 == true) goto L9;
            r0.sortByReactions = true;
        L9:
            if (this.state.equals(r0) == true) goto L19;
            this.state.set(r0);
            if (this.isChannel == false) goto L14;
        L20:
            release();
            this.views.clear();
            this.reactions.clear();
            this.initial = true;
            this.loading = false;
            this.hasNext = true;
            this.offset = _UrlKt.FRAGMENT_ENCODE_SET;
            loadNext();
            return;
        L14:
            if (this.useLocalFilters == false) goto L20;
            applyLocalFilter();
        L17:
            if (r22 >= this.listeners.size()) goto L23;
            this.listeners.get(r22).onDataRecieved(this);
            r22 = r22 + 1;
            goto L17
        L23:
            return;
        }

        public void removeListener(SelfStoryViewsPage r1) {
            this.listeners.remove(r1);
        }
    }

    public static /* synthetic */ void $r8$lambda$YX9xvAB73Ku_gfWkByCTnkGtSvw(SelfStoryViewsPage r0, StoryViewer r1, View r2, int r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$_f7TK0cg3XqQRZ05pbRimcJD3nk(ViewsModel r0, boolean r1) {
        if (r0 == null) goto L5;
        r0.loadNext();
        return;
    }

    /* renamed from: -$$Nest$fgetTOP_PADDING, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19655$$Nest$fgetTOP_PADDING(SelfStoryViewsPage r0) {
        return r0.TOP_PADDING;
    }

    /* renamed from: -$$Nest$fgetmeasuerdHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m19656$$Nest$fgetmeasuerdHeight(SelfStoryViewsPage r0) {
        return r0.measuerdHeight;
    }

    /* renamed from: -$$Nest$fgetpopupMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CustomPopupMenu m19657$$Nest$fgetpopupMenu(SelfStoryViewsPage r0) {
        return r0.popupMenu;
    }

    /* renamed from: -$$Nest$fgetscroller, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListViewScroller m19658$$Nest$fgetscroller(SelfStoryViewsPage r0) {
        return r0.scroller;
    }

    /* renamed from: -$$Nest$fgetshowContactsFilter, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19659$$Nest$fgetshowContactsFilter(SelfStoryViewsPage r0) {
        return r0.showContactsFilter;
    }

    /* renamed from: -$$Nest$fgetshowSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19660$$Nest$fgetshowSearch(SelfStoryViewsPage r0) {
        return r0.showSearch;
    }

    /* renamed from: -$$Nest$fputcheckAutoscroll, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19661$$Nest$fputcheckAutoscroll(SelfStoryViewsPage r0, boolean r1) {
        r0.checkAutoscroll = r1;
    }

    /* renamed from: -$$Nest$fputmeasuerdHeight, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19662$$Nest$fputmeasuerdHeight(SelfStoryViewsPage r0, int r1) {
        r0.measuerdHeight = r1;
    }

    /* renamed from: -$$Nest$fputpopupMenu, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19663$$Nest$fputpopupMenu(SelfStoryViewsPage r0, CustomPopupMenu r1) {
        r0.popupMenu = r1;
    }

    /* renamed from: -$$Nest$mcheckLoadMore, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19664$$Nest$mcheckLoadMore(SelfStoryViewsPage r0) {
        r0.checkLoadMore();
    }

    /* renamed from: -$$Nest$misStoryShownToUser, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m19665$$Nest$misStoryShownToUser(SelfStoryViewsPage r0, TL_stories.StoryView r1) {
        return r0.isStoryShownToUser(r1);
    }

    /* renamed from: -$$Nest$mreload, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19666$$Nest$mreload(SelfStoryViewsPage r0) {
        r0.reload();
    }

    /* renamed from: -$$Nest$mshowPremiumAlert, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19667$$Nest$mshowPremiumAlert(SelfStoryViewsPage r0) {
        r0.showPremiumAlert();
    }

    /* renamed from: -$$Nest$mupdateViewState, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m19668$$Nest$mupdateViewState(SelfStoryViewsPage r0, boolean r1) {
        r0.updateViewState(r1);
    }

    public SelfStoryViewsPage(StoryViewer r11, Context r12, FiltersState r13, Utilities.Callback<SelfStoryViewsPage> r14) {
        super(r12);
        this.TOP_PADDING = 96;
        this.state = new FiltersState();
        this.sharedFilterState = r13;
        this.onSharedStateChanged = r14;
        this.resourcesProvider = r11.resourcesProvider;
        this.storyViewer = r11;
        this.currentAccount = r11.currentAccount;
        TextView r132 = new TextView(r12);
        this.titleView = r132;
        r132.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r132.setTextSize(1, 20.0f);
        r132.setTypeface(AndroidUtilities.bold());
        r132.setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(6.0f), AndroidUtilities.dp(21.0f), AndroidUtilities.dp(8.0f));
        this.headerView = new HeaderView(this, getContext());
        AnonymousClass1 r142 = new AnonymousClass1(this, r12, this.resourcesProvider);
        this.recyclerListView = r142;
        r142.setClipToPadding(false);
        this.recyclerItemsEnterAnimator = new RecyclerItemsEnterAnimator(this.recyclerListView, true);
        RecyclerListView r143 = this.recyclerListView;
        FillLastLinearLayoutManager r2 = new FillLastLinearLayoutManager(r12, 0, r143);
        this.layoutManager = r2;
        r143.setLayoutManager(r2);
        this.recyclerListView.setNestedScrollingEnabled(true);
        RecyclerListView r122 = this.recyclerListView;
        ListAdapter r144 = new ListAdapter(this, null);
        this.listAdapter = r144;
        r122.setAdapter(r144);
        RecyclerAnimationScrollHelper r123 = new RecyclerAnimationScrollHelper(this.recyclerListView, this.layoutManager);
        this.scrollHelper = r123;
        r123.setScrollListener(new AnonymousClass2(this));
        addView(this.recyclerListView);
        this.scroller = new RecyclerListViewScroller(this.recyclerListView);
        this.recyclerListView.setOnScrollListener(new AnonymousClass3(this));
        this.recyclerListView.setOnItemClickListener(new SelfStoryViewsPage$$ExternalSyntheticLambda0(this, r11));
        this.recyclerListView.setOnItemLongClickListener(new AnonymousClass4(this, r11));
        this.listAdapter.updateRows();
        FrameLayout r112 = new FrameLayout(getContext());
        this.topViewsContainer = r112;
        View r124 = new View(getContext());
        this.shadowView = r124;
        GradientDrawable.Orientation r0 = GradientDrawable.Orientation.TOP_BOTTOM;
        int r22 = Theme.key_dialogBackground;
        r124.setBackground(new GradientDrawable(r0, new int[]{Theme.getColor(r22, this.resourcesProvider), 0}));
        r112.addView(r124, LayoutHelper.createFrame(-1, 8.0f, 0, 0.0f, this.TOP_PADDING - 8, 0.0f, 0.0f));
        View r125 = new View(getContext());
        this.shadowView2 = r125;
        r125.setBackgroundColor(Theme.getColor(r22, this.resourcesProvider));
        r112.addView(r125, LayoutHelper.createFrame(-1, 10.0f, 0, 0.0f, this.TOP_PADDING - 17, 0.0f, 0.0f));
        r112.addView(this.headerView);
        r112.addView(r132);
        AnonymousClass5 r02 = new AnonymousClass5(this, getContext(), true, 13.0f, this.resourcesProvider);
        this.searchField = r02;
        r02.setHint(LocaleController.getString(R.string.Search));
        r112.addView(this.searchField, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, 36.0f, 0.0f, 0.0f));
        addView(r112);
    }

    private void appendNewRepostsToList(ViewsModel r7) {
        if (this.currentRepostsList == null) goto L31;
        if (r7 != null) goto L6;
        return;
    L6:
        if (r7 != this.currentModel) goto L33;
        ArrayList<TL_stories.StoryReaction> r0 = r7.reactions;
        if (r0 != null) goto L11;
        return;
    L11:
        if (this.repostsListConsumedCount >= r0.size()) goto L36;
        ArrayList<TL_stories.StoryItem> r02 = new ArrayList();
        int r1 = this.repostsListConsumedCount;
    L14:
        int r2 = r7.reactions.size();
        ArrayList<TL_stories.StoryReaction> r3 = r7.reactions;
        if (r1 >= r2) goto L23;
        TL_stories.StoryReaction r22 = r3.get(r1);
        if ((r22 instanceof TL_stories.TL_storyReactionPublicRepost) == false) goto L22;
        TL_stories.TL_storyReactionPublicRepost r23 = (TL_stories.TL_storyReactionPublicRepost) r22;
        TL_stories.StoryItem r32 = r23.story;
        if (r32 == null) goto L22;
        r32.dialogId = DialogObject.getPeerDialogId(r23.peer_id);
        r02.add(r32);
    L22:
        r1 = r1 + 1;
        goto L14
    L23:
        this.repostsListConsumedCount = r3.size();
        if (r02.isEmpty() == true) goto L34;
        this.currentRepostsList.append(r02);
        return;
    L34:
        return;
    L36:
        return;
    L33:
        return;
    }

    private void checkLoadMore() {
        if (this.currentModel != null) goto L5;
        return;
    L5:
        if (this.layoutManager.findLastVisibleItemPosition() <= (this.listAdapter.getItemCount() - 10)) goto L9;
        this.currentModel.loadNext();
        return;
    }

    private boolean isStoryShownToUser(TL_stories.StoryView r6) {
        if (r6 != null) goto L6;
        return true;
    L6:
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().isBlocked(r6) == false) goto L9;
        return false;
    L9:
        if (MessagesController.getInstance(this.currentAccount).blockePeers.indexOfKey(r6.user_id) < 0) goto L11;
        return false;
    L11:
        TLRPC.User r62 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r6.user_id));
        SelfStoryViewsView.StoryItemInternal r1 = this.storyItem;
        if (r1 == null) goto L28;
        TL_stories.StoryItem r2 = r1.storyItem;
        if (r2 != null) goto L16;
        StoriesController.UploadingStory r5 = r1.uploadingStory;
        if (r5 == null) goto L28;
        StoryEntry r52 = r5.entry;
        if (r52 == null) goto L28;
        StoryPrivacyBottomSheet.StoryPrivacy r53 = r52.privacy;
        if (r53 == null) goto L28;
        return r53.containsUser(r62);
    L16:
        if (r2.parsedPrivacy != null) goto L19;
        r2.parsedPrivacy = new StoryPrivacyBottomSheet.StoryPrivacy(this.currentAccount, r2.privacy);
    L19:
        return this.storyItem.storyItem.parsedPrivacy.containsUser(r62);
    L28:
        return true;
    }

    private /* synthetic */ void lambda$new$1(StoryViewer r9, View r10, int r11) {
        if (r11 >= 0) goto L4;
        return;
    L4:
        if (r11 >= this.listAdapter.items.size()) goto L65;
        Item r102 = this.listAdapter.items.get(r11);
        TL_stories.StoryView r112 = r102.view;
        if ((r112 instanceof TL_stories.TL_storyView) == false) goto L11;
        r9.presentFragment(ProfileActivity.of(r112.user_id));
        return;
    L11:
        if ((r112 instanceof TL_stories.TL_storyViewPublicRepost) == false) goto L14;
        r9.fragment.createOverlayStoryViewer().open(getContext(), ((TL_stories.TL_storyViewPublicRepost) r102.view).story, StoriesListPlaceProvider.of(this.recyclerListView));
        return;
    L14:
        TL_stories.StoryReaction r0 = r102.reaction;
        if ((r0 instanceof TL_stories.TL_storyReaction) == false) goto L19;
        r9.presentFragment(ProfileActivity.of(DialogObject.getPeerDialogId(r0.peer_id)));
        return;
    L19:
        if ((r0 instanceof TL_stories.TL_storyReactionPublicRepost) == false) goto L46;
        ArrayList r113 = new ArrayList();
        ViewsModel r02 = this.currentModel;
        int r1 = 0;
        int r2 = -1;
        if (r02 == null) goto L38;
        ArrayList<TL_stories.StoryReaction> r03 = r02.reactions;
        if (r03 == null) goto L38;
        int r04 = r03.size();
    L26:
        if (r1 >= this.currentModel.reactions.size()) goto L37;
        TL_stories.StoryReaction r3 = this.currentModel.reactions.get(r1);
        if ((r3 instanceof TL_stories.TL_storyReactionPublicRepost) == false) goto L36;
        TL_stories.TL_storyReactionPublicRepost r4 = (TL_stories.TL_storyReactionPublicRepost) r3;
        TL_stories.StoryItem r5 = r4.story;
        if (r5 == null) goto L36;
        r5.dialogId = DialogObject.getPeerDialogId(r4.peer_id);
        if (r3 != r102.reaction) goto L35;
        r2 = r113.size();
    L35:
        r113.add(r5);
    L36:
        r1 = r1 + 1;
        goto L26
    L37:
        r1 = r04;
    L38:
        if (r2 >= 0) goto L40;
    L43:
        this.currentRepostsList = null;
        r9.fragment.createOverlayStoryViewer().open(getContext(), ((TL_stories.TL_storyReactionPublicRepost) r102.reaction).story, StoriesListPlaceProvider.of(this.recyclerListView));
        return;
    L40:
        if (r113.size() <= 1) goto L43;
        this.currentRepostsList = new StoriesController.StoryRepostsList(this.currentAccount, r113);
        this.repostsListConsumedCount = r1;
        ViewsModel r103 = this.currentModel;
        r9.fragment.createOverlayStoryViewer().open(getContext(), r2, this.currentRepostsList, StoriesListPlaceProvider.of(this.recyclerListView).with(new SelfStoryViewsPage$$ExternalSyntheticLambda1(r103)));
        return;
    L46:
        if ((r0 instanceof TL_stories.TL_storyReactionPublicForward) == true) goto L50;
        if ((r112 instanceof TL_stories.TL_storyViewPublicForward) == true) goto L50;
        return;
    L50:
        if ((r0 instanceof TL_stories.TL_storyReactionPublicForward) == false) goto L52;
        TLRPC.Message r8 = r0.message;
    L53:
        Bundle r104 = new Bundle();
        long r05 = DialogObject.getPeerDialogId(r8.peer_id);
        if (r05 < 0) goto L56;
        r104.putLong("user_id", r05);
    L57:
        r104.putInt("message_id", r8.id);
        r9.presentFragment(new ChatActivity(r104));
        return;
    L56:
        r104.putLong("chat_id", -r05);
        goto L57
    L52:
        r8 = r112.message;
        goto L53
    }

    public static void preload(int r10, long r11, TL_stories.StoryItem r13) {
        if (r13 == null) goto L24;
        SparseArray<ViewsModel> r0 = MessagesController.getInstance(r10).storiesController.selfViewsModel.get(r13.dialogId);
        if (r0 != null) goto L7;
        ViewsModel r1 = null;
    L8:
        TL_stories.StoryViews r2 = r13.views;
        if (r2 != null) goto L11;
        int r22 = 0;
    L12:
        if (r1 != null) goto L14;
    L17:
        if (r1 == null) goto L19;
        r1.release();
    L19:
        ViewsModel r4 = new ViewsModel(r10, r11, r13, true);
        r4.loadNext();
        if (r0 != null) goto L22;
        LongSparseArray<SparseArray<ViewsModel>> r102 = MessagesController.getInstance(r10).storiesController.selfViewsModel;
        long r112 = r13.dialogId;
        r0 = new SparseArray();
        r102.put(r112, r0);
    L22:
        r0.put(r13.id, r4);
        return;
    L14:
        if (r1.totalCount != r22) goto L17;
        return;
    L11:
        r22 = r2.views_count;
        goto L12
    L7:
        r1 = r0.get(r13.id);
        goto L8
    }

    private void reload() {
        ViewsModel r0 = this.currentModel;
        if (r0 == null) goto L5;
        r0.removeListener(this);
    L5:
        ViewsModel r02 = this.defaultModel;
        this.currentModel = r02;
        if (r02 != null) goto L8;
        return;
    L8:
        r02.addListener(this);
        this.currentModel.reloadIfNeed(this.state, this.showContactsFilter, this.showReactionsSort);
        this.listAdapter.updateRows();
        this.layoutManager.scrollToPositionWithOffset(0, (int) (getTopOffset() - this.recyclerListView.getPaddingTop()));
    }

    private void showPremiumAlert() {
        new PremiumFeatureBottomSheet(this.storyViewer.fragment, 14, false).show();
    }

    private void updateViewState(boolean r3) {
        this.headerView.setState(this.state.contactsOnly, r3);
        HeaderView r0 = this.headerView;
        boolean r1 = this.state.sortByReactions;
        r0.lastSortType = r1;
        ReplaceableIconDrawable r02 = r0.replacableDrawable;
        if (r1 == false) goto L10;
        ViewsModel r2 = this.currentModel;
        if (r2 != null) goto L7;
    L9:
        int r22 = R.drawable.menu_views_reactions3;
    L11:
        r02.setIcon(r22, r3);
        return;
    L7:
        if (r2.isChannel == false) goto L9;
        r22 = R.drawable.menu_views_reposts3;
        goto L11
    L10:
        r22 = R.drawable.menu_views_recent3;
        goto L11
    }

    private void updateViewsVisibility() {
        this.showSearch = false;
        this.showContactsFilter = false;
        this.showReactionsSort = false;
        TL_stories.StoryItem r7 = this.storyItem.storyItem;
        int r2 = 46;
        if (r7 == null) goto L144;
        TL_stories.StoryViews r3 = r7.views;
        int r12 = 10;
        if (r3 == null) goto L29;
        int r5 = r3.views_count;
        if (r5 < 15) goto L9;
        boolean r6 = true;
    L10:
        this.showSearch = r6;
        int r32 = r3.reactions_count;
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L13;
        int r62 = 5;
    L14:
        if (r32 < r62) goto L16;
        boolean r33 = true;
    L17:
        this.showReactionsSort = r33;
        long r16 = 0;
        if (r7.dialogId < 0) goto L27;
        if (r5 < 20) goto L27;
        if (r7.contacts == true) goto L27;
        if (r7.close_friends == true) goto L27;
        if (r7.selected_contacts == true) goto L27;
        boolean r34 = true;
    L28:
        this.showContactsFilter = r34;
    L30:
        SparseArray<ViewsModel> r10 = MessagesController.getInstance(this.currentAccount).storiesController.selfViewsModel.get(r7.dialogId);
        if (r10 == null) goto L33;
        ViewsModel r35 = r10.get(r7.id);
    L34:
        this.defaultModel = r35;
        TL_stories.StoryViews r52 = r7.views;
        if (r52 != null) goto L37;
        int r53 = 0;
    L38:
        if (r35 != null) goto L40;
    L45:
        if (r35 == null) goto L47;
        r35.release();
    L47:
        boolean r11 = true;
        ViewsModel r36 = new ViewsModel(this.currentAccount, this.dialogId, r7, true);
        this.defaultModel = r36;
        r36.reloadIfNeed(this.state, this.showContactsFilter, this.showReactionsSort);
        this.defaultModel.loadNext();
        if (r10 == null) goto L50;
        r10.put(r7.id, this.defaultModel);
    L51:
        ViewsModel r37 = this.currentModel;
        if (r37 == null) goto L54;
        r37.removeListener(this);
    L54:
        ViewsModel r38 = this.defaultModel;
        this.currentModel = r38;
        if (r38 != null) goto L57;
    L59:
        ViewsModel r39 = this.currentModel;
        if (r39 != null) goto L62;
    L65:
        ViewsModel r310 = this.currentModel;
        if (r310.loading == false) goto L68;
    L80:
        TL_stories.StoryViews r311 = r7.views;
        if (r311 != null) goto L83;
    L139:
        this.showSearch = false;
        this.showReactionsSort = false;
        this.showContactsFilter = false;
        TextView r312 = this.titleView;
        if (this.currentModel.isChannel == false) goto L142;
        int r4 = R.string.Reactions;
    L143:
        r312.setText(LocaleController.getString(r4));
        this.searchField.setVisibility(8);
        this.headerView.setVisibility(8);
        this.TOP_PADDING = 46;
    L145:
        LinearLayout r22 = HeaderView.m19671$$Nest$fgetbuttonContainer(this.headerView);
        if (this.showReactionsSort == false) goto L148;
        int r313 = 0;
    L149:
        r22.setVisibility(r313);
        TextView r23 = this.headerView.allViewersView;
        if (this.showContactsFilter == false) goto L152;
        int r314 = 0;
    L153:
        r23.setVisibility(r314);
        TextView r24 = this.headerView.contactsViewersView;
        if (this.showContactsFilter == false) goto L156;
        int r315 = 0;
    L157:
        r24.setVisibility(r315);
        boolean r25 = this.showContactsFilter;
        TextView r316 = this.titleView;
        if (r25 == true) goto L160;
        r316.setVisibility(0);
    L161:
        ((ViewGroup.MarginLayoutParams) this.shadowView.getLayoutParams()).topMargin = AndroidUtilities.dp(this.TOP_PADDING - 8);
        ((ViewGroup.MarginLayoutParams) this.shadowView2.getLayoutParams()).topMargin = AndroidUtilities.dp(this.TOP_PADDING - 17);
        return;
    L160:
        r316.setVisibility(8);
        goto L161
    L156:
        r315 = 8;
        goto L157
    L152:
        r314 = 8;
        goto L153
    L148:
        r313 = 8;
        goto L149
    L142:
        r4 = R.string.Viewers;
        goto L143
    L83:
        if (r311.views_count == 0) goto L139;
        this.headerView.setVisibility(0);
        ViewsModel r317 = this.currentModel;
        if (r317.showReactionOnly == false) goto L93;
        TextView r42 = this.titleView;
        if (r317.isChannel == false) goto L90;
        int r318 = R.string.Reactions;
    L91:
        r42.setText(LocaleController.getString(r318));
        this.showSearch = false;
        this.showReactionsSort = false;
        this.showContactsFilter = false;
    L131:
        SearchField r319 = this.searchField;
        if (this.showSearch == false) goto L134;
        int r43 = 0;
    L135:
        r319.setVisibility(r43);
        if (this.showSearch == false) goto L138;
        r2 = 96;
    L138:
        this.TOP_PADDING = r2;
        goto L145
    L134:
        r43 = 8;
        goto L135
    L90:
        r318 = R.string.Viewers;
        goto L91
    L93:
        if (r317.getCount() >= 20) goto L102;
        if (this.currentModel.getCount() >= r7.views.views_count) goto L102;
        ViewsModel r320 = this.currentModel;
        if (r320.loading == true) goto L102;
        if (r320.hasNext == true) goto L102;
        this.showSearch = false;
        this.showReactionsSort = false;
        this.showContactsFilter = false;
        this.showServerErrorText = r11;
    L126:
        TextView r321 = this.titleView;
        if (this.currentModel.isChannel == false) goto L129;
        int r44 = R.string.Reactions;
    L130:
        r321.setText(LocaleController.getString(r44));
        goto L131
    L129:
        r44 = R.string.Viewers;
    L102:
        if (this.currentModel.isChannel == false) goto L104;
    L106:
        boolean r45 = false;
    L107:
        this.showSearch = r45;
        TL_stories.StoryViews r322 = r7.views;
        int r46 = r322.reactions_count;
        if (BuildVars.DEBUG_VERSION == false) goto L110;
        r12 = 5;
    L110:
        if (r46 < r12) goto L112;
        boolean r47 = r11;
    L113:
        this.showReactionsSort = r47;
        if (r7.dialogId >= r16) goto L116;
    L124:
        boolean r48 = false;
    L125:
        this.showContactsFilter = r48;
        goto L126
    L116:
        if (r322.views_count < 20) goto L124;
        if (r7.contacts == true) goto L124;
        if (r7.close_friends == true) goto L124;
        if (r7.selected_contacts == true) goto L124;
        r48 = r11;
        goto L125
    L112:
        r47 = false;
        goto L113
    L104:
        if (r7.views.views_count < 15) goto L106;
        r45 = r11;
        goto L107
    L68:
        if (r310.hasNext == true) goto L80;
        if (r310.views.isEmpty() == false) goto L80;
        if (this.currentModel.reactions.isEmpty() == false) goto L80;
        if (TextUtils.isEmpty(this.currentModel.state.searchQuery) == false) goto L80;
    L75:
        this.showSearch = false;
        this.showReactionsSort = false;
        this.showContactsFilter = false;
        TextView r323 = this.titleView;
        if (this.currentModel.isChannel == false) goto L78;
        int r49 = R.string.Reactions;
    L79:
        r323.setText(LocaleController.getString(r49));
        this.searchField.setVisibility(8);
        this.headerView.setVisibility(8);
        this.TOP_PADDING = 46;
        goto L145
    L78:
        r49 = R.string.Viewers;
        goto L79
    L62:
        if (r39.isExpiredViews == false) goto L65;
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L75;
    L57:
        if (this.isAttachedToWindow == false) goto L59;
        r38.addListener(this);
        goto L59
    L50:
        SparseArray<ViewsModel> r324 = new SparseArray();
        r324.put(r7.id, this.defaultModel);
        MessagesController.getInstance(this.currentAccount).storiesController.selfViewsModel.put(r7.dialogId, r324);
        goto L51
    L40:
        if (r35.isChannel == false) goto L42;
    L44:
        r35.reloadIfNeed(this.state, this.showContactsFilter, this.showReactionsSort);
        r11 = true;
        goto L51
    L42:
        if (r35.totalCount == r53) goto L44;
    L37:
        r53 = r52.views_count;
        goto L38
    L33:
        r35 = null;
    L27:
        r34 = false;
        goto L28
    L16:
        r33 = false;
        goto L17
    L13:
        r62 = 10;
        goto L14
    L9:
        r6 = false;
        goto L10
    L29:
        r16 = 0;
        goto L30
    L144:
        this.TOP_PADDING = 46;
        this.titleView.setText(LocaleController.getString(R.string.UploadingStory));
        this.searchField.setVisibility(8);
        this.headerView.setVisibility(8);
        goto L145
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        int r52 = 0;
        if (r3 != NotificationCenter.storiesUpdated) goto L18;
        if (this.storyItem.uploadingStory == null) goto L34;
        TL_stories.PeerStories r32 = MessagesController.getInstance(this.currentAccount).storiesController.getStories(UserConfig.getInstance(this.currentAccount).clientUserId);
        if (r32 != null) goto L9;
        return;
    L9:
        if (r52 >= r32.stories.size()) goto L46;
        TL_stories.StoryItem r42 = r32.stories.get(r52);
        String r0 = r42.attachPath;
        if (r0 == null) goto L16;
        if (r0.equals(this.storyItem.uploadingStory.path) == false) goto L16;
        SelfStoryViewsView.StoryItemInternal r33 = this.storyItem;
        r33.uploadingStory = null;
        r33.storyItem = r42;
        setStoryItem(this.dialogId, r33);
        return;
    L16:
        r52 = r52 + 1;
        goto L9
    L46:
        return;
    L34:
        return;
    L18:
        if (r3 == NotificationCenter.storiesBlocklistUpdate) goto L20;
        return;
    L20:
        if (r52 >= this.recyclerListView.getChildCount()) goto L47;
        View r34 = this.recyclerListView.getChildAt(r52);
        if ((r34 instanceof ReactedUserHolderView) == false) goto L33;
        int r43 = this.recyclerListView.getChildAdapterPosition(r34);
        if (r43 < 0) goto L33;
        if (r43 >= this.listAdapter.items.size()) goto L33;
        ReactedUserHolderView r35 = (ReactedUserHolderView) r34;
        if (isStoryShownToUser(this.listAdapter.items.get(r43).view) == false) goto L31;
        float r44 = 1.0f;
    L32:
        r35.animateAlpha(r44, true);
        goto L33
    L31:
        r44 = 0.5f;
    L33:
        r52 = r52 + 1;
        goto L20
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r8) {
        View r1 = null;
        int r4 = -1;
        int r3 = 0;
    L4:
        if (r3 >= this.recyclerListView.getChildCount()) goto L11;
        View r5 = this.recyclerListView.getChildAt(r3);
        int r6 = this.recyclerListView.getChildLayoutPosition(r5);
        if (r6 < r4) goto L8;
        if (r4 == (-1)) goto L8;
    L9:
        r3 = r3 + 1;
    L8:
        r1 = r5;
        r4 = r6;
        goto L9
    L11:
        if (r4 != 0) goto L13;
        int r12 = (int) Math.max(0.0f, r1.getY());
    L16:
        float r42 = r12;
        if (this.topViewsContainer.getTranslationY() == r42) goto L19;
        this.topViewsContainer.setTranslationY(r42);
        onTopOffsetChanged(r12);
    L19:
        this.shadowDrawable.setBounds(-AndroidUtilities.dp(6.0f), r12, getMeasuredWidth() + AndroidUtilities.dp(6.0f), getMeasuredHeight());
        this.shadowDrawable.draw(r8);
        if (this.checkAutoscroll == false) goto L29;
        this.checkAutoscroll = false;
        if (this.topViewsContainer.getTranslationY() == 0.0f) goto L29;
        if (this.topViewsContainer.getTranslationY() == this.recyclerListView.getPaddingTop()) goto L29;
        float r0 = this.topViewsContainer.getTranslationY();
        float r13 = this.recyclerListView.getPaddingTop() / 2.0f;
        RecyclerListViewScroller r14 = this.scroller;
        if (r0 <= r13) goto L28;
        r14.smoothScrollBy((int) (-(this.recyclerListView.getPaddingTop() - this.topViewsContainer.getTranslationY())));
        goto L29
    L28:
        r14.smoothScrollBy((int) this.topViewsContainer.getTranslationY());
    L29:
        super.dispatchDraw(r8);
        return;
    L13:
        if (r4 <= 0) goto L15;
        r12 = 0;
        goto L16
    L15:
        r12 = this.recyclerListView.getPaddingTop();
        goto L16
    }

    @Override // android.view.ViewGroup
    public boolean drawChild(Canvas r5, View r6, long r7) {
        if (r6 != this.recyclerListView) goto L7;
        r5.save();
        r5.clipRect(0, AndroidUtilities.dp(this.TOP_PADDING), getMeasuredWidth(), getMeasuredHeight());
        super.drawChild(r5, r6, r7);
        r5.restore();
        return true;
    L7:
        return super.drawChild(r5, r6, r7);
    }

    public float getTopOffset() {
        return this.topViewsContainer.getTranslationY();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.isAttachedToWindow = true;
        ViewsModel r0 = this.currentModel;
        if (r0 == null) goto L5;
        r0.addListener(this);
        this.currentModel.animateDateForUsers.clear();
    L5:
        this.listAdapter.updateRows();
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesBlocklistUpdate);
        Bulletin.addDelegate(this, new AnonymousClass6(this));
    }

    public boolean onBackPressed() {
        CustomPopupMenu r0 = this.popupMenu;
        if (r0 == null) goto L9;
        if (r0.isShowing() == false) goto L9;
        this.popupMenu.dismiss();
        return true;
    L9:
        if (Math.abs(this.topViewsContainer.getTranslationY() - this.recyclerListView.getPaddingTop()) <= AndroidUtilities.dp(2.0f)) goto L12;
        this.recyclerListView.dispatchTouchEvent(AndroidUtilities.emptyMotionEvent());
        this.recyclerListView.smoothScrollToPosition(0);
        return true;
    L12:
        return false;
    }

    public void onDataRecieved(ViewsModel r3) {
        int r0 = this.listAdapter.getItemCount();
        if (TextUtils.isEmpty(this.state.searchQuery) == true) goto L5;
    L7:
        this.listAdapter.updateRows();
        this.recyclerItemsEnterAnimator.showItemsAnimated(r0 - 1);
        checkLoadMore();
        appendNewRepostsToList(r3);
        return;
    L5:
        if (this.state.contactsOnly == true) goto L7;
        updateViewsVisibility();
        goto L7
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.isAttachedToWindow = false;
        ViewsModel r0 = this.currentModel;
        if (r0 == null) goto L5;
        r0.removeListener(this);
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesBlocklistUpdate);
        Bulletin.removeDelegate(this);
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent r3) {
        if (r3.getY() >= this.topViewsContainer.getTranslationY()) goto L7;
        return false;
    L7:
        return super.onInterceptTouchEvent(r3);
    }

    public void onKeyboardShown() {
        this.recyclerListView.dispatchTouchEvent(AndroidUtilities.emptyMotionEvent());
        if (this.topViewsContainer.getTranslationY() == 0.0f) goto L6;
        this.scroller.smoothScrollBy((int) this.topViewsContainer.getTranslationY(), 250, AdjustPanLayoutHelper.keyboardInterpolator);
        return;
    }

    public void onTopOffsetChanged(int r1) {
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent r3) {
        if (r3.getY() >= this.topViewsContainer.getTranslationY()) goto L7;
        return false;
    L7:
        return super.onTouchEvent(r3);
    }

    public boolean scrollToRepostCell(long r6, int r8) {
        ListAdapter r0 = this.listAdapter;
        if (r0 != null) goto L5;
    L34:
        return false;
    L5:
        if (r0.items == null) goto L34;
        if (this.layoutManager == null) goto L34;
        int r02 = 0;
    L11:
        if (r02 >= this.listAdapter.items.size()) goto L25;
        Item r2 = this.listAdapter.items.get(r02);
        if (r2 == null) goto L24;
        TL_stories.StoryReaction r22 = r2.reaction;
        if ((r22 instanceof TL_stories.TL_storyReactionPublicRepost) == false) goto L24;
        TL_stories.TL_storyReactionPublicRepost r23 = (TL_stories.TL_storyReactionPublicRepost) r22;
        if (r23.story == null) goto L24;
        if (DialogObject.getPeerDialogId(r23.peer_id) != r6) goto L24;
        if (r23.story.id != r8) goto L24;
    L26:
        if (r02 >= 0) goto L28;
        return false;
    L28:
        int r62 = this.layoutManager.findFirstVisibleItemPosition();
        int r7 = this.layoutManager.findLastVisibleItemPosition();
        if (r02 < r62) goto L32;
        if (r02 > r7) goto L32;
        return false;
    L32:
        this.layoutManager.scrollToPositionWithOffset(r02, AndroidUtilities.dp(60.0f));
        return true;
    L24:
        r02 = r02 + 1;
        goto L11
    L25:
        r02 = -1;
        goto L26
    }

    public void setListBottomPadding(float r3) {
        if (r3 == this.recyclerListView.getPaddingBottom()) goto L6;
        this.recyclerListView.setPadding(0, (int) r3, 0, 0);
        this.recyclerListView.requestLayout();
        return;
    }

    public void setShadowDrawable(Drawable r1) {
        this.shadowDrawable = r1;
    }

    public void setStoryItem(long r2, SelfStoryViewsView.StoryItemInternal r4) {
        this.dialogId = r2;
        this.storyItem = r4;
        updateViewsVisibility();
        updateViewState(false);
        if (r4 != null) goto L5;
        return;
    L5:
        if (r4.storyItem == null) goto L9;
        NotificationsController.getInstance(this.currentAccount).processSeenStoryReactions(r2, r4.storyItem.id);
        return;
    }

    public void updateSharedState() {
    }
}
