package org.telegram.ui.community;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.AvatarCornerType;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import me.vkryl.android.animator.BoolAnimator;
import me.vkryl.android.animator.FactorAnimator;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.FBool;
import org.telegram.messenger.utils.GradientProtectionDrawable;
import org.telegram.messenger.utils.TextWatcherImpl;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.FiltersView;
import org.telegram.ui.Cells.DialogCell;
import org.telegram.ui.Cells.TextCheckCell2;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ColoredImageSpan;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.Forum.ForumUtilities;
import org.telegram.ui.Components.FragmentSearchField;
import org.telegram.ui.Components.IconBackgroundColors;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.JoinGroupAlert;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.SearchViewPager;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.chat.layouts.ChatActivityFadeView;
import org.telegram.ui.FilteredSearchView;
import org.telegram.ui.Stories.StoriesListPlaceProvider;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.TopicsFragment;
import org.telegram.ui.community.CommunityUtils;
import org.telegram.ui.community.cells.CommunityPendingRequestCell;
import org.telegram.ui.community.cells.CommunityRequestsCell;
import org.telegram.ui.community.sheet.CommunityAddOptionsSheet;
import org.telegram.ui.community.sheet.CommunityInviteOnlySheet;

/* loaded from: classes7.dex */
public class CommunitySheet extends BottomSheet implements NotificationCenter.NotificationCenterDelegate, FactorAnimator.Target, DialogCell.DialogCellDelegate {
    private ButtonWithCounterView addChatToCommunityButton;
    private final BoolAnimator animatorSearchChatsVisible;
    private final BoolAnimator animatorSearchMessagesVisible;
    private final Paint backgroundPaint;
    private final ChatsToAddListPage chatsPage;
    private final FadeView chatsPageFadeView;
    private final FragmentSearchField chatsSearchView;
    private final Utilities.Callback<TLRPC.Chat> chatsToAddCallback;
    private ArrayList<TLRPC.Chat> chatsToAddToCommunity;
    private ButtonWithCounterView closeChatToCommunityButton;
    private boolean collapsedInDialogs;
    private final long communityId;
    private TLRPC.ChatFull communityInfo;
    private final CommunityPage communityPage;
    private final FadeView communityPageFadeView;
    private TLRPC.Chat currentCommunity;
    private final View fakeAnchorView;
    private final FilteredSearchView filteredSearchView;
    private final UniversalRecyclerView foundChatsView;
    private final GradientProtectionDrawable gradientProtectionDrawableBottom;
    private final GradientProtectionDrawable gradientProtectionDrawableTop;
    private String lastSearchChatsString;
    private String lastSearchString;
    private final FragmentSearchField messagesSearchView;
    private final boolean onlyChatsMode;
    private final BaseFragment parentFragment;
    private CommunityUtils.PendingRequests pendingRequestsList;
    private final PendingRequestsPage requestsPage;
    private Insets systemAndImeInsets;
    private Insets systemInsets;
    private ViewPagerFixed viewPager;

    /* renamed from: org.telegram.ui.community.CommunitySheet$1, reason: invalid class name */
    public class AnonymousClass1 implements TextWatcherImpl {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass1(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            CommunitySheet.m21550$$Nest$monMessagesSearchTextChanged(this.this$0, r1.toString());
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$2, reason: invalid class name */
    public class AnonymousClass2 implements TextWatcherImpl {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass2(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r1) {
            CommunitySheet.m21545$$Nest$monChatsSearchTextChanged(this.this$0, r1.toString());
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$3, reason: invalid class name */
    public class AnonymousClass3 extends RecyclerView.OnScrollListener {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass3(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            super.onScrolled(r1, r2, r3);
            if (CommunitySheet.m21529$$Nest$fgetfoundChatsView(this.this$0).scrollingByUser == false) goto L6;
            AndroidUtilities.hideKeyboard(CommunitySheet.m21522$$Nest$fgetchatsSearchView(this.this$0).editText);
            return;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$4, reason: invalid class name */
    public class AnonymousClass4 implements SearchViewPager.ChatPreviewDelegate {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass4(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
        public void finish() {
        }

        @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
        public void move(float r1) {
        }

        @Override // org.telegram.ui.Components.SearchViewPager.ChatPreviewDelegate
        public void startChatPreview(RecyclerListView r1, DialogCell r2) {
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$5, reason: invalid class name */
    public class AnonymousClass5 implements FilteredSearchView.UiCallback {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass5(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public boolean actionModeShowing() {
            return false;
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void goToMessage(MessageObject r3) {
            CommunitySheet.m21534$$Nest$fgetparentFragment(this.this$0).presentFragment(SearchViewPager.createFragmentFromMessage(CommunitySheet.access$000(this.this$0), r3));
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public boolean isSelected(FilteredSearchView.MessageHashId r1) {
            return false;
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void showActionMode() {
        }

        @Override // org.telegram.ui.FilteredSearchView.UiCallback
        public void toggleItemSelection(MessageObject r1, View r2, int r3) {
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$6, reason: invalid class name */
    public class AnonymousClass6 implements CommunityUtils.PendingRequests.Delegate {
        final /* synthetic */ CommunitySheet this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public AnonymousClass6(CommunitySheet r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void close() {
            CommunitySheet.m21538$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void onClickGroupOwner(long r2) {
            this.val$fragment.presentFragment(ChatActivity.of(r2));
            this.this$0.dismiss();
        }

        @Override // org.telegram.ui.community.CommunityUtils.PendingRequests.Delegate
        public void updateAdapter() {
            CommunitySheet.m21536$$Nest$fgetrequestsPage(this.this$0).listView.adapter.update(true);
            CommunitySheet.m21525$$Nest$fgetcommunityPage(this.this$0).listView.adapter.update(true);
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$7, reason: invalid class name */
    public class AnonymousClass7 extends ViewPagerFixed.Adapter {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass7(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ((Page) r1).bind(r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            CommunitySheet r1 = this.this$0;
            if (r2 == 2) goto L5;
            if (r2 != 0) goto L10;
            return CommunitySheet.m21525$$Nest$fgetcommunityPage(r1);
        L10:
            return CommunitySheet.m21536$$Nest$fgetrequestsPage(r1);
        L5:
            return CommunitySheet.m21520$$Nest$fgetchatsPage(r1);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            if (CommunitySheet.m21533$$Nest$fgetonlyChatsMode(this.this$0) == false) goto L6;
            return 1;
        L6:
            return 3;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r2) {
            if (CommunitySheet.m21533$$Nest$fgetonlyChatsMode(this.this$0) == false) goto L5;
            return 2;
        L5:
            if (r2 != 2) goto L7;
            return 2;
        L7:
            if (r2 != 0) goto L10;
            return 0;
        L10:
            return 1;
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$8, reason: invalid class name */
    public class AnonymousClass8 implements Bulletin.Delegate {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass8(CommunitySheet r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(60.0f);
        }
    }

    /* renamed from: org.telegram.ui.community.CommunitySheet$9, reason: invalid class name */
    public class AnonymousClass9 extends ViewPagerFixed {
        final /* synthetic */ CommunitySheet this$0;

        public AnonymousClass9(CommunitySheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScrollBackward(MotionEvent r1) {
            if (getCurrentPosition() == 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public boolean canScrollForward(MotionEvent r1) {
            return false;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onScrollEnd() {
            super.onScrollEnd();
            if (getCurrentPosition() != 1) goto L6;
            CommunitySheet.m21525$$Nest$fgetcommunityPage(this.this$0).listView.adapter.update(false);
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            CommunitySheet.access$100(this.this$0).invalidate();
        }
    }

    public class ChatsToAddListPage extends Page {
        final /* synthetic */ CommunitySheet this$0;

        /* renamed from: org.telegram.ui.community.CommunitySheet$ChatsToAddListPage$1, reason: invalid class name */
        public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
            final /* synthetic */ ChatsToAddListPage this$1;
            final /* synthetic */ CommunitySheet val$this$0;

            public AnonymousClass1(ChatsToAddListPage r1, CommunitySheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
            public void onItemClick(int r3) {
                if (r3 != (-1)) goto L11;
                boolean r32 = CommunitySheet.m21533$$Nest$fgetonlyChatsMode(this.this$1.this$0);
                ChatsToAddListPage r0 = this.this$1;
                if (r32 == false) goto L8;
                r0.this$0.dismiss();
                return;
            L8:
                CommunitySheet.m21525$$Nest$fgetcommunityPage(r0.this$0).listView.adapter.update(false);
                CommunitySheet.m21538$$Nest$fgetviewPager(this.this$1.this$0).scrollToPosition(0);
                return;
            L11:
                if (r3 != 3) goto L14;
                CommunitySheet.m21517$$Nest$fgetanimatorSearchChatsVisible(this.this$1.this$0).setValue(true, true);
                this.this$1.this$0.setAllowNestedScroll(false);
                CommunitySheet.m21545$$Nest$monChatsSearchTextChanged(this.this$1.this$0, null);
                CommunitySheet.m21522$$Nest$fgetchatsSearchView(this.this$1.this$0).editText.getText().clear();
                CommunitySheet.m21522$$Nest$fgetchatsSearchView(this.this$1.this$0).editText.requestFocus();
                AndroidUtilities.showKeyboard(CommunitySheet.m21522$$Nest$fgetchatsSearchView(this.this$1.this$0).editText);
                return;
            }
        }

        /* renamed from: $r8$lambda$-kFC9EvNJ-EqvBriV6YPq3KpkEI, reason: not valid java name */
        public static /* synthetic */ void m21552$r8$lambda$kFC9EvNJEqvBriV6YPq3KpkEI(ChatsToAddListPage r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public static /* synthetic */ void $r8$lambda$Pl64CftOiNXF5aYWKIIZx2b1E5k(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
            CommunitySheet.m21541$$Nest$mfillItemsChatsToAdd(r0, r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eA24aFbXYmETRwrLiJAL5VtnnH4(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
            CommunitySheet.m21546$$Nest$monClickChatToAdd(r0, r1, r2, r3, r4, r5);
        }

        public ChatsToAddListPage(CommunitySheet r10, Context r11) {
            this.this$0 = r10;
            super(r10, r11);
            AndroidUtilities.removeFromParent(this.fadeView);
            UniversalRecyclerView r1 = new UniversalRecyclerView(r11, CommunitySheet.access$900(r10), 0, new CommunitySheet$ChatsToAddListPage$$ExternalSyntheticLambda0(r10), new CommunitySheet$ChatsToAddListPage$$ExternalSyntheticLambda1(r10), null, CommunitySheet.access$1000(r10));
            this.listView = r1;
            r1.setSections();
            this.listView.adapter.setApplyBackground(false);
            this.listView.setClipToPadding(false);
            this.listView.setPadding(0, 0, 0, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(60.0f));
            this.contentView.addView(CommunitySheet.m21529$$Nest$fgetfoundChatsView(r10), LayoutHelper.createFrameMatchParent());
            this.contentView.addView(this.listView, 0, LayoutHelper.createFrame(-1, -1.0f));
            this.contentView.addView(CommunitySheet.m21521$$Nest$fgetchatsPageFadeView(r10), LayoutHelper.createFrameMatchParent());
            ActionBar r112 = new ActionBar(r11, CommunitySheet.access$1100(r10));
            this.actionBar = r112;
            r112.setOccupyStatusBar(false);
            this.actionBar.setTitleColor(CommunitySheet.access$1200(r10, Theme.key_windowBackgroundWhiteBlackText));
            this.actionBar.setItemsBackgroundColor(CommunitySheet.access$1300(r10, Theme.key_actionBarActionModeDefaultSelector), false);
            ActionBar r113 = this.actionBar;
            if (CommunitySheet.m21533$$Nest$fgetonlyChatsMode(r10) == false) goto L5;
            int r12 = R.drawable.ic_ab_close;
        L6:
            r113.setBackButtonImage(r12);
            this.actionBar.setItemsColor(CommunitySheet.access$1400(r10, Theme.key_actionBarActionModeDefaultIcon), false);
            this.actionBar.setTitle(LocaleController.getString(R.string.CommunityAddAChatToCommunity));
            this.actionBar.getTitleTextView().setTranslationX(-AndroidUtilities.dp(18.0f));
            this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, r10));
            this.contentView.addView(this.actionBar, LayoutHelper.createFrame(-1, 56, 48));
            this.contentView.addView(CommunitySheet.m21522$$Nest$fgetchatsSearchView(r10), LayoutHelper.createFrame(-1, 40.0f, 48, 11.0f, 0.0f, 11.0f, 0.0f));
            ActionBarMenu r114 = this.actionBar.createMenu();
            r114.setGlassMode(true);
            r114.setTranslationX(-AndroidUtilities.dp(7.0f));
            r114.addItem(3, R.drawable.outline_header_search);
            CommunitySheet.m21540$$Nest$fputcloseChatToCommunityButton(r10, new ButtonWithCounterView(getContext(), CommunitySheet.access$1500(r10)));
            CommunitySheet.m21523$$Nest$fgetcloseChatToCommunityButton(r10).setRound();
            CommunitySheet.m21523$$Nest$fgetcloseChatToCommunityButton(r10).setText(LocaleController.getString(R.string.OK));
            CommunitySheet.m21523$$Nest$fgetcloseChatToCommunityButton(r10).setOnClickListener(new CommunitySheet$ChatsToAddListPage$$ExternalSyntheticLambda2(this));
            if (CommunitySheet.m21533$$Nest$fgetonlyChatsMode(r10) == false) goto L9;
            CommunitySheet.m21523$$Nest$fgetcloseChatToCommunityButton(r10).setVisibility(8);
        L9:
            this.contentView.addView(CommunitySheet.m21523$$Nest$fgetcloseChatToCommunityButton(r10), LayoutHelper.createFrameMarginPx(-1, 48.0f, 80, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(12.0f)));
            afterInit();
            return;
        L5:
            r12 = R.drawable.ic_ab_back;
            goto L6
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            CommunitySheet.m21538$$Nest$fgetviewPager(this.this$0).scrollToPosition(0);
        }

        @Override // org.telegram.ui.community.CommunitySheet.Page
        public float top() {
            return super.top() * FBool.not(CommunitySheet.m21517$$Nest$fgetanimatorSearchChatsVisible(this.this$0).getFloatValue());
        }

        @Override // org.telegram.ui.community.CommunitySheet.Page
        public void updateTops() {
            super.updateTops();
            CommunitySheet.m21522$$Nest$fgetchatsSearchView(this.this$0).setTranslationY(Math.max(AndroidUtilities.statusBarHeight + AndroidUtilities.dp(8.0f), top() + AndroidUtilities.dp(4.0f)));
        }
    }

    public class CommunityPage extends Page {
        private AvatarDrawable avatarDrawable;
        private BackupImageView avatarImage;
        final /* synthetic */ CommunitySheet this$0;

        /* renamed from: org.telegram.ui.community.CommunitySheet$CommunityPage$1, reason: invalid class name */
        public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
            final /* synthetic */ CommunityPage this$1;
            final /* synthetic */ CommunitySheet val$this$0;

            public AnonymousClass1(CommunityPage r1, CommunitySheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
            public void onItemClick(int r4) {
                if (r4 != 2) goto L7;
                Bundle r42 = new Bundle();
                r42.putLong("community_id", CommunitySheet.m21524$$Nest$fgetcommunityId(this.this$1.this$0));
                CommunitySheet.m21534$$Nest$fgetparentFragment(this.this$1.this$0).presentFragment(new CommunityEditActivity(r42));
                this.this$1.this$0.dismiss();
                return;
            L7:
                if (r4 != 3) goto L10;
                CommunitySheet.m21518$$Nest$fgetanimatorSearchMessagesVisible(this.this$1.this$0).setValue(true, true);
                this.this$1.this$0.setAllowNestedScroll(false);
                CommunitySheet.m21551$$Nest$monMessagesSearchTextChanged(this.this$1.this$0, null, true);
                CommunitySheet.m21532$$Nest$fgetmessagesSearchView(this.this$1.this$0).editText.getText().clear();
                CommunitySheet.m21532$$Nest$fgetmessagesSearchView(this.this$1.this$0).editText.requestFocus();
                AndroidUtilities.showKeyboard(CommunitySheet.m21532$$Nest$fgetmessagesSearchView(this.this$1.this$0).editText);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$HtTqLEpc_2hFQrNqHcBtHU1o7bE(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
            CommunitySheet.m21542$$Nest$mfillItemsCommunity(r0, r1, r2);
        }

        public static /* synthetic */ boolean $r8$lambda$JqXBKHbkhmhtYfe4bbT1B9ymcu8(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
            return CommunitySheet.m21549$$Nest$monLongClickCommunity(r0, r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$lSeAdl0fjg-Yv24p-osz_pL5L50, reason: not valid java name */
        public static /* synthetic */ void m21553$r8$lambda$lSeAdl0fjgYv24posz_pL5L50(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
            CommunitySheet.m21547$$Nest$monClickCommunity(r0, r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$r5hK7sG1Nss6UaKuq-8Jr1WjkVY, reason: not valid java name */
        public static /* synthetic */ void m21554$r8$lambda$r5hK7sG1Nss6UaKuq8Jr1WjkVY(CommunityPage r0, View r1) {
            r0.lambda$new$0(r1);
        }

        /* renamed from: -$$Nest$fgetavatarDrawable, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AvatarDrawable m21555$$Nest$fgetavatarDrawable(CommunityPage r0) {
            return r0.avatarDrawable;
        }

        /* renamed from: -$$Nest$fgetavatarImage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ BackupImageView m21556$$Nest$fgetavatarImage(CommunityPage r0) {
            return r0.avatarImage;
        }

        public CommunityPage(CommunitySheet r10, Context r11) {
            this.this$0 = r10;
            super(r10, r11);
            UniversalRecyclerView r0 = new UniversalRecyclerView(r11, CommunitySheet.access$200(r10), 0, new CommunitySheet$CommunityPage$$ExternalSyntheticLambda0(r10), new CommunitySheet$CommunityPage$$ExternalSyntheticLambda1(r10), new CommunitySheet$CommunityPage$$ExternalSyntheticLambda2(r10), CommunitySheet.access$300(r10));
            this.listView = r0;
            r0.setSections();
            this.listView.adapter.setApplyBackground(false);
            this.listView.setClipToPadding(false);
            this.listView.setPadding(0, 0, 0, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(60.0f));
            AndroidUtilities.removeFromParent(this.fadeView);
            this.contentView.addView(CommunitySheet.m21528$$Nest$fgetfilteredSearchView(r10), LayoutHelper.createFrameMatchParent());
            this.contentView.addView(this.listView, LayoutHelper.createFrameMatchParent());
            this.contentView.addView(CommunitySheet.m21526$$Nest$fgetcommunityPageFadeView(r10), LayoutHelper.createFrameMatchParent());
            ActionBar r112 = new ActionBar(r11, CommunitySheet.access$400(r10));
            this.actionBar = r112;
            r112.setOccupyStatusBar(false);
            this.actionBar.setTitleColor(CommunitySheet.access$500(r10, Theme.key_windowBackgroundWhiteBlackText));
            this.actionBar.setItemsBackgroundColor(CommunitySheet.access$600(r10, Theme.key_actionBarActionModeDefaultSelector), false);
            this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
            this.actionBar.setItemsColor(CommunitySheet.access$700(r10, Theme.key_actionBarActionModeDefaultIcon), false);
            this.actionBar.setTitle(DialogObject.getName(CommunitySheet.m21527$$Nest$fgetcurrentCommunity(r10)));
            this.actionBar.getTitleTextView().setTranslationX(-AndroidUtilities.dp(18.0f));
            this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this, r10));
            this.avatarDrawable = new AvatarDrawable(CommunitySheet.m21527$$Nest$fgetcurrentCommunity(r10));
            BackupImageView r113 = new BackupImageView(getContext());
            this.avatarImage = r113;
            r113.setRoundRadius(ExteraConfig.getAvatarCorners(27.33f, false, AvatarCornerType.COMMUNITY));
            this.avatarImage.setForUserOrChat(CommunitySheet.m21527$$Nest$fgetcurrentCommunity(r10), this.avatarDrawable);
            this.actionBar.addView(this.avatarImage, LayoutHelper.createFrame(27.33f, 27.33f, 83, 14.33f, 0.0f, 0.0f, 14.33f));
            this.contentView.addView(this.actionBar, LayoutHelper.createFrame(-1, 56, 48));
            this.contentView.addView(CommunitySheet.m21532$$Nest$fgetmessagesSearchView(r10), LayoutHelper.createFrame(-1, 40.0f, 48, 11.0f, 0.0f, 11.0f, 0.0f));
            ActionBarMenu r114 = this.actionBar.createMenu();
            r114.setGlassMode(true);
            r114.setTranslationX(-AndroidUtilities.dp(7.0f));
            r114.addItem(3, R.drawable.outline_header_search);
            if (ChatObject.hasAdminRights(CommunitySheet.m21527$$Nest$fgetcurrentCommunity(r10)) == false) goto L5;
            r114.addItem(2, R.drawable.msg_download_settings);
        L5:
            ButtonWithCounterView r115 = new ButtonWithCounterView(getContext(), CommunitySheet.access$800(r10));
            r115.setRound();
            if (ChatObject.canAddChatToCommunity(CommunitySheet.m21527$$Nest$fgetcurrentCommunity(r10)) == false) goto L8;
            ColoredImageSpan r2 = new ColoredImageSpan(R.drawable.filled_add_album);
            SpannableStringBuilder r3 = new SpannableStringBuilder("+ ");
            r3.append(LocaleController.getString(R.string.CommunityAddAChatToCommunity));
            r3.setSpan(r2, 0, 1, 33);
            r115.setText(r3);
        L9:
            CommunitySheet.m21539$$Nest$fputaddChatToCommunityButton(r10, r115);
            CommunitySheet.m21516$$Nest$fgetaddChatToCommunityButton(r10).setOnClickListener(new CommunitySheet$CommunityPage$$ExternalSyntheticLambda3(this));
            this.contentView.addView(r115, LayoutHelper.createFrameMarginPx(-1, 48.0f, 80, AndroidUtilities.dp(12.0f), 0, AndroidUtilities.dp(12.0f), AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(12.0f)));
            afterInit();
            return;
        L8:
            r115.setText(LocaleController.getString(R.string.OK));
            goto L9
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            CommunitySheet.m21544$$Nest$monAddChatToCommunityButtonClick(this.this$0);
        }

        @Override // org.telegram.ui.community.CommunitySheet.Page
        public float top() {
            return super.top() * FBool.not(CommunitySheet.m21518$$Nest$fgetanimatorSearchMessagesVisible(this.this$0).getFloatValue());
        }

        @Override // org.telegram.ui.community.CommunitySheet.Page
        public void updateTops() {
            super.updateTops();
            CommunitySheet.m21532$$Nest$fgetmessagesSearchView(this.this$0).setTranslationY(Math.max(AndroidUtilities.statusBarHeight + AndroidUtilities.dp(8.0f), top() + AndroidUtilities.dp(4.0f)));
        }
    }

    public class ContainerView extends FrameLayout {
        private final AnimatedFloat isActionBar;
        private final Path path;
        final /* synthetic */ CommunitySheet this$0;
        private float top;

        public ContainerView(CommunitySheet r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.isActionBar = new AnimatedFloat(this, 250, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.path = new Path();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r9) {
            View[] r0 = CommunitySheet.m21538$$Nest$fgetviewPager(this.this$0).getViewPages();
            float r1 = 0.0f;
            this.top = 0.0f;
            int r3 = 0;
        L4:
            if (r3 >= r0.length) goto L12;
            View r4 = r0[r3];
            if (r4 == null) goto L11;
            Page r42 = (Page) r4;
            this.top += r42.top() * Utilities.clamp(1.0f - Math.abs(r42.getTranslationX() / r42.getMeasuredWidth()), 1.0f, 0.0f);
            if (r42.getVisibility() != 0) goto L11;
            r42.updateTops();
        L11:
            r3 = r3 + 1;
            goto L4
        L12:
            AnimatedFloat r02 = this.isActionBar;
            if (this.top > AndroidUtilities.statusBarHeight) goto L15;
            r1 = 1.0f;
        L15:
            float r03 = r02.set(r1);
            this.top = (Math.max(AndroidUtilities.statusBarHeight, this.top) - (AndroidUtilities.statusBarHeight * r03)) - (AndroidUtilities.dp(10.0f) * Math.max(CommunitySheet.m21518$$Nest$fgetanimatorSearchMessagesVisible(this.this$0).getFloatValue(), CommunitySheet.m21517$$Nest$fgetanimatorSearchChatsVisible(this.this$0).getFloatValue()));
            RectF r12 = AndroidUtilities.rectTmp;
            r12.set(CommunitySheet.access$3100(this.this$0), this.top, getWidth() - CommunitySheet.access$3200(this.this$0), getHeight() + AndroidUtilities.dp(8.0f));
            float r04 = AndroidUtilities.lerp(AndroidUtilities.dp(14.0f), 0, r03);
            r9.drawRoundRect(r12, r04, r04, CommunitySheet.m21519$$Nest$fgetbackgroundPaint(this.this$0));
            r9.save();
            this.path.rewind();
            this.path.addRoundRect(r12, r04, r04, Path.Direction.CW);
            r9.clipPath(this.path);
            super.dispatchDraw(r9);
            r9.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r3) {
            if (r3.getAction() != 0) goto L9;
            if (r3.getY() >= this.top) goto L9;
            this.this$0.dismiss();
            return true;
        L9:
            return super.dispatchTouchEvent(r3);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r3), TLObject.FLAG_30));
        }
    }

    public class FadeView extends View {
        final /* synthetic */ CommunitySheet this$0;

        public FadeView(CommunitySheet r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onDraw(Canvas r9) {
            super.onDraw(r9);
            float r0 = Math.max(CommunitySheet.m21518$$Nest$fgetanimatorSearchMessagesVisible(this.this$0).getFloatValue(), CommunitySheet.m21517$$Nest$fgetanimatorSearchChatsVisible(this.this$0).getFloatValue());
            CommunitySheet.m21531$$Nest$fgetgradientProtectionDrawableTop(this.this$0).setInsets(0, CommunitySheet.m21537$$Nest$fgetsystemInsets(this.this$0).top + AndroidUtilities.dp(42.0f), 0, 0);
            CommunitySheet.m21531$$Nest$fgetgradientProtectionDrawableTop(this.this$0).setBounds(0, 0, getWidth(), CommunitySheet.m21537$$Nest$fgetsystemInsets(this.this$0).top + AndroidUtilities.dp(56.0f));
            GradientProtectionDrawable r1 = CommunitySheet.m21531$$Nest$fgetgradientProtectionDrawableTop(this.this$0);
            CommunitySheet r2 = this.this$0;
            int r4 = Theme.key_windowBackgroundGray;
            r1.setColor(Theme.multAlpha(CommunitySheet.access$3300(r2, r4), AndroidUtilities.lerp(1.0f, 0.8f, r0)));
            CommunitySheet.m21531$$Nest$fgetgradientProtectionDrawableTop(this.this$0).draw(r9);
            if (CommunitySheet.m21533$$Nest$fgetonlyChatsMode(this.this$0) == false) goto L5;
            r0 = 1.0f;
        L5:
            int r12 = AndroidUtilities.lerp(CommunitySheet.m21537$$Nest$fgetsystemInsets(this.this$0).bottom + AndroidUtilities.dp(48.0f), 0, r0);
            int r22 = CommunitySheet.m21537$$Nest$fgetsystemInsets(this.this$0).bottom + AndroidUtilities.lerp(AndroidUtilities.dp(72.0f), 0, r0);
            float r02 = AndroidUtilities.lerp(0.8f, AndroidUtilities.getNavigationBarThirdButtonsFactor(CommunitySheet.m21537$$Nest$fgetsystemInsets(this.this$0).bottom), r0);
            CommunitySheet.m21530$$Nest$fgetgradientProtectionDrawableBottom(this.this$0).setInsets(0, 0, 0, r12);
            CommunitySheet.m21530$$Nest$fgetgradientProtectionDrawableBottom(this.this$0).setBounds(0, getHeight() - r22, getWidth(), getHeight());
            CommunitySheet.m21530$$Nest$fgetgradientProtectionDrawableBottom(this.this$0).setColor(Theme.multAlpha(CommunitySheet.access$3400(this.this$0, r4), r02));
            CommunitySheet.m21530$$Nest$fgetgradientProtectionDrawableBottom(this.this$0).draw(r9);
        }
    }

    public abstract class Page extends FrameLayout {
        protected ActionBar actionBar;
        protected final FrameLayout contentView;
        protected ChatActivityFadeView fadeView;
        protected UniversalRecyclerView listView;
        public int pageType;
        private boolean scrolling;
        final /* synthetic */ CommunitySheet this$0;
        public boolean wasAtBottom;
        public boolean wasAtTop;

        /* renamed from: org.telegram.ui.community.CommunitySheet$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$1;

            public AnonymousClass1(Page r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(RecyclerView r2, int r3) {
                if (r3 != 0) goto L4;
                Page r22 = this.this$1;
                r22.wasAtTop = r22.atTop();
                Page r23 = this.this$1;
                r23.wasAtBottom = r23.atBottom();
            L4:
                Page r1 = this.this$1;
                if (r3 == 0) goto L7;
                boolean r24 = true;
            L8:
                Page.m21557$$Nest$fputscrolling(r1, r24);
                return;
            L7:
                r24 = false;
                goto L8
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                CommunitySheet.access$2600(this.this$1.this$0).invalidate();
            }
        }

        /* renamed from: org.telegram.ui.community.CommunitySheet$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends DefaultItemAnimator {
            final /* synthetic */ Page this$1;

            public AnonymousClass2(Page r1) {
                this.this$1 = r1;
            }

            @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
            public boolean canReuseUpdatedViewHolder(RecyclerView.ViewHolder r1) {
                return true;
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onAddAnimationUpdate(RecyclerView.ViewHolder r1) {
                CommunitySheet.access$2900(this.this$1.this$0).invalidate();
                this.this$1.contentView.invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onChangeAnimationUpdate(RecyclerView.ViewHolder r1) {
                CommunitySheet.access$2800(this.this$1.this$0).invalidate();
                this.this$1.contentView.invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onMoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                CommunitySheet.access$2700(this.this$1.this$0).invalidate();
                this.this$1.contentView.invalidate();
                this.this$1.listView.invalidate();
            }

            @Override // androidx.recyclerview.widget.DefaultItemAnimator
            public void onRemoveAnimationUpdate(RecyclerView.ViewHolder r1) {
                CommunitySheet.access$3000(this.this$1.this$0).invalidate();
                this.this$1.contentView.invalidate();
            }
        }

        /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m21557$$Nest$fputscrolling(Page r0, boolean r1) {
            r0.scrolling = r1;
        }

        public Page(CommunitySheet r4, Context r5) {
            this.this$0 = r4;
            super(r5);
            FrameLayout r42 = new FrameLayout(r5);
            this.contentView = r42;
            r42.setPadding(0, 0, 0, 0);
            r42.setClipToPadding(true);
            addView(r42, LayoutHelper.createFrame(-1, -1, 119));
            ChatActivityFadeView r0 = new ChatActivityFadeView(getContext());
            this.fadeView = r0;
            r0.setupColorKey(Theme.key_windowBackgroundGray);
            this.fadeView.setFadeZoneBottom(AndroidUtilities.dp(72.0f) + AndroidUtilities.navigationBarHeight);
            this.fadeView.setFadeHeightBottom(AndroidUtilities.dp(24.0f));
            this.fadeView.setFadeZoneTop(AndroidUtilities.dp(64.0f) + AndroidUtilities.statusBarHeight);
            this.fadeView.setFadeHeightTop(AndroidUtilities.dp(20.0f), false);
            r42.addView(this.fadeView, LayoutHelper.createFrameMatchParent());
        }

        public void afterInit() {
            this.listView.addOnScrollListener(new AnonymousClass1(this));
            AnonymousClass2 r0 = new AnonymousClass2(this);
            r0.setDurations(350);
            r0.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            r0.setDelayAnimations(false);
            r0.setSupportsChangeAnimations(false);
            this.listView.setItemAnimator(r0);
        }

        public boolean atBottom() {
            return !this.listView.canScrollVertically(1);
        }

        public boolean atTop() {
            return !this.listView.canScrollVertically(-1);
        }

        public void bind(int r1) {
            this.pageType = r1;
        }

        public float top() {
            float r0 = AndroidUtilities.displaySize.y;
            int r1 = 0;
        L4:
            if (r1 >= this.listView.getChildCount()) goto L14;
            View r2 = this.listView.getChildAt(r1);
            RecyclerView.ViewHolder r3 = this.listView.getChildViewHolder(r2);
            if (r3 == null) goto L13;
            UItem r32 = this.listView.adapter.getItem(r3.getAdapterPosition());
            if (r32 == null) goto L13;
            if (r32.id == 99) goto L13;
            r0 = Math.min(this.contentView.getPaddingTop() + r2.getY(), r0);
        L13:
            r1 = r1 + 1;
            goto L4
        L14:
            return r0;
        }

        public void updateTops() {
            float r0 = top();
            ActionBar r2 = this.actionBar;
            if (r2 == null) goto L6;
            r2.setTranslationY(Math.max(AndroidUtilities.statusBarHeight, r0));
            return;
        }
    }

    public class PendingRequestsPage extends Page {
        final /* synthetic */ CommunitySheet this$0;

        /* renamed from: org.telegram.ui.community.CommunitySheet$PendingRequestsPage$1, reason: invalid class name */
        public class AnonymousClass1 extends RecyclerView.OnScrollListener {
            final /* synthetic */ PendingRequestsPage this$1;
            final /* synthetic */ CommunitySheet val$this$0;

            public AnonymousClass1(PendingRequestsPage r1, CommunitySheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r1, int r2, int r3) {
                super.onScrolled(r1, r2, r3);
                CommunitySheet.m21535$$Nest$fgetpendingRequestsList(this.this$1.this$0).checkLoadNext(this.this$1.listView);
            }
        }

        /* renamed from: org.telegram.ui.community.CommunitySheet$PendingRequestsPage$2, reason: invalid class name */
        public class AnonymousClass2 extends ActionBar.ActionBarMenuOnItemClick {
            final /* synthetic */ PendingRequestsPage this$1;
            final /* synthetic */ CommunitySheet val$this$0;

            public AnonymousClass2(PendingRequestsPage r1, CommunitySheet r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
            public void onItemClick(int r2) {
                if (r2 != (-1)) goto L6;
                CommunitySheet.m21525$$Nest$fgetcommunityPage(this.this$1.this$0).listView.adapter.update(false);
                CommunitySheet.m21538$$Nest$fgetviewPager(this.this$1.this$0).scrollToPosition(0);
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$8kaiP3XuVPKwrlYXpPIWVeAzdzI(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
            CommunitySheet.m21543$$Nest$mfillItemsRequests(r0, r1, r2);
        }

        /* renamed from: $r8$lambda$PyPqdTOJ8LWZdd03tCNk2lUp3-k, reason: not valid java name */
        public static /* synthetic */ void m21558$r8$lambda$PyPqdTOJ8LWZdd03tCNk2lUp3k(PendingRequestsPage r0, View r1) {
            r0.lambda$new$1(r1);
        }

        public static /* synthetic */ void $r8$lambda$iXT7FnRlXAWZZqRY3DunTyhkVo0(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
            CommunitySheet.m21548$$Nest$monClickRequest(r0, r1, r2, r3, r4, r5);
        }

        /* renamed from: $r8$lambda$lr7rcCDxXS9s-UPUfhcrCgLCbFs, reason: not valid java name */
        public static /* synthetic */ void m21559$r8$lambda$lr7rcCDxXS9sUPUfhcrCgLCbFs(PendingRequestsPage r0, View r1) {
            r0.lambda$new$0(r1);
        }

        public PendingRequestsPage(CommunitySheet r12, Context r13) {
            this.this$0 = r12;
            super(r12, r13);
            UniversalRecyclerView r0 = new UniversalRecyclerView(r13, CommunitySheet.access$1600(r12), 0, new CommunitySheet$PendingRequestsPage$$ExternalSyntheticLambda0(r12), new CommunitySheet$PendingRequestsPage$$ExternalSyntheticLambda1(r12), null, CommunitySheet.access$1700(r12));
            this.listView = r0;
            r0.setSections();
            this.listView.adapter.setApplyBackground(false);
            this.listView.setClipToPadding(false);
            this.listView.setPadding(0, 0, 0, AndroidUtilities.navigationBarHeight + AndroidUtilities.dp(60.0f));
            this.listView.addOnScrollListener(new AnonymousClass1(this, r12));
            this.contentView.addView(this.listView, 0, LayoutHelper.createFrame(-1, -1.0f));
            ActionBar r132 = new ActionBar(r13, CommunitySheet.access$1800(r12));
            this.actionBar = r132;
            r132.setOccupyStatusBar(false);
            ActionBar r133 = this.actionBar;
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r133.setTitleColor(CommunitySheet.access$1900(r12, r2));
            this.actionBar.setItemsBackgroundColor(CommunitySheet.access$2000(r12, Theme.key_actionBarActionModeDefaultSelector), false);
            this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
            this.actionBar.setItemsColor(CommunitySheet.access$2100(r12, Theme.key_actionBarActionModeDefaultIcon), false);
            this.actionBar.setTitle(LocaleController.getString(R.string.CommunityPendingRequestsTitle));
            this.actionBar.getTitleTextView().setTranslationX(-AndroidUtilities.dp(18.0f));
            this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass2(this, r12));
            this.contentView.addView(this.actionBar, LayoutHelper.createFrame(-1, 56, 48));
            LinearLayout r134 = new LinearLayout(r13);
            r134.setOrientation(0);
            r134.setPadding(AndroidUtilities.dp(7.0f), 0, AndroidUtilities.dp(7.0f), AndroidUtilities.dp(12.0f));
            ButtonWithCounterView r02 = new ButtonWithCounterView(r13, CommunitySheet.access$2200(r12));
            r02.setNeutral();
            r02.setColor(ColorUtils.blendARGB(CommunitySheet.access$2300(r12, Theme.key_windowBackgroundWhite), CommunitySheet.access$2400(r12, r2), 0.125f));
            r02.setText(LocaleController.getString(R.string.CommunityPendingRequestDeclineAll));
            r02.setRound();
            r02.setOnClickListener(new CommunitySheet$PendingRequestsPage$$ExternalSyntheticLambda2(this));
            r134.addView(r02, LayoutHelper.createLinear(0, 48, 1.0f, 0, 4, 0, 4, 0));
            ButtonWithCounterView r03 = new ButtonWithCounterView(r13, CommunitySheet.access$2500(r12));
            r03.setText(LocaleController.getString(R.string.CommunityPendingRequestAddAll));
            r03.setRound();
            r03.setOnClickListener(new CommunitySheet$PendingRequestsPage$$ExternalSyntheticLambda3(this));
            r134.addView(r03, LayoutHelper.createLinear(0, 48, 1.0f, 0, 4, 0, 4, 0));
            this.contentView.addView(r134, LayoutHelper.createFrameMarginPx(-1, -2.0f, 80, 0, 0, 0, AndroidUtilities.navigationBarHeight));
            afterInit();
        }

        private /* synthetic */ void lambda$new$0(View r1) {
            CommunitySheet.m21535$$Nest$fgetpendingRequestsList(this.this$0).onResolveAllJoinRequests(false);
        }

        private /* synthetic */ void lambda$new$1(View r1) {
            CommunitySheet.m21535$$Nest$fgetpendingRequestsList(this.this$0).onResolveAllJoinRequests(true);
        }
    }

    /* renamed from: $r8$lambda$1BE1E6Gr-mkm7WkASgwp0Ub-2-I, reason: not valid java name */
    public static /* synthetic */ void m21513$r8$lambda$1BE1E6Grmkm7WkASgwp0Ub2I(CommunitySheet r0) {
        r0.lambda$fillItemsRequests$2();
    }

    public static /* synthetic */ void $r8$lambda$5MM3jGsY5xzWLlK6rMMcsPvgQv0(CommunitySheet r0, AlertDialog r1, long r2, boolean r4, long r5) {
        r0.lambda$linkToCommunity$9(r1, r2, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$9hA7ehfEJuzzluX7WZC_9sVFzis(CommunitySheet r0, boolean r1, boolean r2, long r3) {
        r0.lambda$onLongClickCommunity$6(r1, r2, r3);
    }

    public static /* synthetic */ WindowInsetsCompat $r8$lambda$KvgWOBrhraqVkAPkSoIciIyxeJs(CommunitySheet r0, View r1, WindowInsetsCompat r2) {
        return r0.onApplyWindowInsets(r1, r2);
    }

    /* renamed from: $r8$lambda$M2M4-qp3gfnmSxTCxaLzJRsbFAI, reason: not valid java name */
    public static /* synthetic */ void m21514$r8$lambda$M2M4qp3gfnmSxTCxaLzJRsbFAI(CommunitySheet r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$PCTHkJ5lSNQ3AluSqkLxqTSWIY8(CommunitySheet r0, boolean r1, TLRPC.Bool r2, TLRPC.TL_error r3) {
        r0.lambda$linkToCommunity$10(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$W1JJv34Q6bpNJ_KpQjTWHoX5Tkw(CommunitySheet r0, TLRPC.Bool r1, TLRPC.TL_error r2) {
        r0.lambda$onLongClickCommunity$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cIulDA9IwFzXHoNGTRTDr514hgA(CommunitySheet r0, CommunityPendingRequestCell.Data r1) {
        r0.lambda$checkPendingRequestClick$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$eA24aFbXYmETRwrLiJAL5VtnnH4(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClickChatToAdd(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$kM4IHVPm6Rpyop9OOQg82hHah5Q(CommunitySheet r0, ArrayList r1, TLRPC.TL_error r2) {
        r0.lambda$loadChatsToAddToCommunity$8(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$liIDgNeEjnI2tRt63A86wsk0lRY(CommunitySheet r0, long r1) {
        r0.lambda$onLongClickCommunity$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$ltfpixSnqC2NWTW7aBN5AAPo8X8(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItemsChatsToAddSearch(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$qcsvO1_pmS7PmRV5EqLpRYgS03k(CommunitySheet r0) {
        r0.lambda$new$0();
    }

    /* renamed from: $r8$lambda$rHEP-bV-mHxfXdmRunkq3kW0McQ, reason: not valid java name */
    public static /* synthetic */ void m21515$r8$lambda$rHEPbVmHxfXdmRunkq3kW0McQ(CommunitySheet r0, TLRPC.Chat r1, Boolean r2) {
        r0.lambda$onClickChatToAdd$3(r1, r2);
    }

    /* renamed from: -$$Nest$fgetaddChatToCommunityButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m21516$$Nest$fgetaddChatToCommunityButton(CommunitySheet r0) {
        return r0.addChatToCommunityButton;
    }

    /* renamed from: -$$Nest$fgetanimatorSearchChatsVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m21517$$Nest$fgetanimatorSearchChatsVisible(CommunitySheet r0) {
        return r0.animatorSearchChatsVisible;
    }

    /* renamed from: -$$Nest$fgetanimatorSearchMessagesVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BoolAnimator m21518$$Nest$fgetanimatorSearchMessagesVisible(CommunitySheet r0) {
        return r0.animatorSearchMessagesVisible;
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m21519$$Nest$fgetbackgroundPaint(CommunitySheet r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetchatsPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatsToAddListPage m21520$$Nest$fgetchatsPage(CommunitySheet r0) {
        return r0.chatsPage;
    }

    /* renamed from: -$$Nest$fgetchatsPageFadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FadeView m21521$$Nest$fgetchatsPageFadeView(CommunitySheet r0) {
        return r0.chatsPageFadeView;
    }

    /* renamed from: -$$Nest$fgetchatsSearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSearchField m21522$$Nest$fgetchatsSearchView(CommunitySheet r0) {
        return r0.chatsSearchView;
    }

    /* renamed from: -$$Nest$fgetcloseChatToCommunityButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m21523$$Nest$fgetcloseChatToCommunityButton(CommunitySheet r0) {
        return r0.closeChatToCommunityButton;
    }

    /* renamed from: -$$Nest$fgetcommunityId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m21524$$Nest$fgetcommunityId(CommunitySheet r2) {
        return r2.communityId;
    }

    /* renamed from: -$$Nest$fgetcommunityPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CommunityPage m21525$$Nest$fgetcommunityPage(CommunitySheet r0) {
        return r0.communityPage;
    }

    /* renamed from: -$$Nest$fgetcommunityPageFadeView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FadeView m21526$$Nest$fgetcommunityPageFadeView(CommunitySheet r0) {
        return r0.communityPageFadeView;
    }

    /* renamed from: -$$Nest$fgetcurrentCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ TLRPC.Chat m21527$$Nest$fgetcurrentCommunity(CommunitySheet r0) {
        return r0.currentCommunity;
    }

    /* renamed from: -$$Nest$fgetfilteredSearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FilteredSearchView m21528$$Nest$fgetfilteredSearchView(CommunitySheet r0) {
        return r0.filteredSearchView;
    }

    /* renamed from: -$$Nest$fgetfoundChatsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ UniversalRecyclerView m21529$$Nest$fgetfoundChatsView(CommunitySheet r0) {
        return r0.foundChatsView;
    }

    /* renamed from: -$$Nest$fgetgradientProtectionDrawableBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientProtectionDrawable m21530$$Nest$fgetgradientProtectionDrawableBottom(CommunitySheet r0) {
        return r0.gradientProtectionDrawableBottom;
    }

    /* renamed from: -$$Nest$fgetgradientProtectionDrawableTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ GradientProtectionDrawable m21531$$Nest$fgetgradientProtectionDrawableTop(CommunitySheet r0) {
        return r0.gradientProtectionDrawableTop;
    }

    /* renamed from: -$$Nest$fgetmessagesSearchView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FragmentSearchField m21532$$Nest$fgetmessagesSearchView(CommunitySheet r0) {
        return r0.messagesSearchView;
    }

    /* renamed from: -$$Nest$fgetonlyChatsMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21533$$Nest$fgetonlyChatsMode(CommunitySheet r0) {
        return r0.onlyChatsMode;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m21534$$Nest$fgetparentFragment(CommunitySheet r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetpendingRequestsList, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CommunityUtils.PendingRequests m21535$$Nest$fgetpendingRequestsList(CommunitySheet r0) {
        return r0.pendingRequestsList;
    }

    /* renamed from: -$$Nest$fgetrequestsPage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ PendingRequestsPage m21536$$Nest$fgetrequestsPage(CommunitySheet r0) {
        return r0.requestsPage;
    }

    /* renamed from: -$$Nest$fgetsystemInsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m21537$$Nest$fgetsystemInsets(CommunitySheet r0) {
        return r0.systemInsets;
    }

    /* renamed from: -$$Nest$fgetviewPager, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ViewPagerFixed m21538$$Nest$fgetviewPager(CommunitySheet r0) {
        return r0.viewPager;
    }

    /* renamed from: -$$Nest$fputaddChatToCommunityButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21539$$Nest$fputaddChatToCommunityButton(CommunitySheet r0, ButtonWithCounterView r1) {
        r0.addChatToCommunityButton = r1;
    }

    /* renamed from: -$$Nest$fputcloseChatToCommunityButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21540$$Nest$fputcloseChatToCommunityButton(CommunitySheet r0, ButtonWithCounterView r1) {
        r0.closeChatToCommunityButton = r1;
    }

    /* renamed from: -$$Nest$mfillItemsChatsToAdd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21541$$Nest$mfillItemsChatsToAdd(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItemsChatsToAdd(r1, r2);
    }

    /* renamed from: -$$Nest$mfillItemsCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21542$$Nest$mfillItemsCommunity(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItemsCommunity(r1, r2);
    }

    /* renamed from: -$$Nest$mfillItemsRequests, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21543$$Nest$mfillItemsRequests(CommunitySheet r0, ArrayList r1, UniversalAdapter r2) {
        r0.fillItemsRequests(r1, r2);
    }

    /* renamed from: -$$Nest$monAddChatToCommunityButtonClick, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21544$$Nest$monAddChatToCommunityButtonClick(CommunitySheet r0) {
        r0.onAddChatToCommunityButtonClick();
    }

    /* renamed from: -$$Nest$monChatsSearchTextChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21545$$Nest$monChatsSearchTextChanged(CommunitySheet r0, String r1) {
        r0.onChatsSearchTextChanged(r1);
    }

    /* renamed from: -$$Nest$monClickChatToAdd, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21546$$Nest$monClickChatToAdd(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClickChatToAdd(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$monClickCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21547$$Nest$monClickCommunity(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClickCommunity(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$monClickRequest, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21548$$Nest$monClickRequest(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
        r0.onClickRequest(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$monLongClickCommunity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21549$$Nest$monLongClickCommunity(CommunitySheet r0, UItem r1, View r2, int r3, float r4, float r5) {
        return r0.onLongClickCommunity(r1, r2, r3, r4, r5);
    }

    /* renamed from: -$$Nest$monMessagesSearchTextChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21550$$Nest$monMessagesSearchTextChanged(CommunitySheet r0, String r1) {
        r0.onMessagesSearchTextChanged(r1);
    }

    /* renamed from: -$$Nest$monMessagesSearchTextChanged, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21551$$Nest$monMessagesSearchTextChanged(CommunitySheet r0, String r1, boolean r2) {
        r0.onMessagesSearchTextChanged(r1, r2);
    }

    public CommunitySheet(BaseFragment r7, long r8) {
        this(r7, r8, null, null);
    }

    public CommunitySheet(BaseFragment r24, long r25, ArrayList<TLRPC.Chat> r27, Utilities.Callback<TLRPC.Chat> r28) {
        super(r24.getContext(), true, true, r24.getResourceProvider());
        CubicBezierInterpolator r3 = CubicBezierInterpolator.EASE_OUT_QUINT;
        this.animatorSearchMessagesVisible = new BoolAnimator(1, this, r3, 350);
        this.animatorSearchChatsVisible = new BoolAnimator(2, this, r3, 350);
        this.gradientProtectionDrawableTop = new GradientProtectionDrawable(2);
        this.gradientProtectionDrawableBottom = new GradientProtectionDrawable(8);
        this.backgroundPaint = new Paint(1);
        Insets r0 = Insets.NONE;
        this.systemAndImeInsets = r0;
        this.systemInsets = r0;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        this.parentFragment = r24;
        if (r27 == null) goto L5;
        boolean r32 = true;
    L6:
        this.onlyChatsMode = r32;
        this.chatsToAddToCommunity = r27;
        this.chatsToAddCallback = r28;
        Context r33 = r24.getContext();
        init(r33);
        this.communityPageFadeView = new FadeView(this, r33);
        this.chatsPageFadeView = new FadeView(this, r33);
        FragmentSearchField r4 = new FragmentSearchField(r33, this.resourcesProvider);
        this.messagesSearchView = r4;
        r4.setCloseButtonVisible(true);
        r4.setWhiteBackground();
        r4.editText.setHint(LocaleController.getString(R.string.Search));
        r4.editText.addTextChangedListener(new AnonymousClass1(this));
        r4.setVisibility(8);
        FragmentSearchField r5 = new FragmentSearchField(r33, this.resourcesProvider);
        this.chatsSearchView = r5;
        r5.setCloseButtonVisible(true);
        r5.setWhiteBackground();
        r5.editText.setHint(LocaleController.getString(R.string.Search));
        r5.editText.addTextChangedListener(new AnonymousClass2(this));
        r5.setVisibility(8);
        UniversalRecyclerView r15 = new UniversalRecyclerView(r33, this.currentAccount, 0, new CommunitySheet$$ExternalSyntheticLambda0(this), new CommunitySheet$$ExternalSyntheticLambda1(this), null, this.resourcesProvider);
        this.foundChatsView = r15;
        r15.addOnScrollListener(new AnonymousClass3(this));
        r15.setClipToPadding(false);
        r15.setVisibility(8);
        r15.setSections();
        r15.adapter.setApplyBackground(false);
        r15.setPadding(0, AndroidUtilities.statusBarHeight + AndroidUtilities.dp(52.0f), 0, AndroidUtilities.navigationBarHeight);
        FilteredSearchView r7 = new FilteredSearchView(r24);
        this.filteredSearchView = r7;
        r7.setVisibility(8);
        r7.setBackground(null);
        r7.setChatPreviewDelegate(new AnonymousClass4(this));
        r7.setUiCallback(new AnonymousClass5(this));
        r7.recyclerListView.setClipToPadding(false);
        this.fakeAnchorView = new View(getContext());
        Context r8 = getContext();
        Theme.ResourcesProvider r9 = this.resourcesProvider;
        CommunityUtils.PendingRequests r72 = new CommunityUtils.PendingRequests(r8, r9, BulletinFactory.of((FrameLayout) this.containerView, r9), this.currentAccount, r25);
        this.pendingRequestsList = r72;
        r72.setDelegate(new AnonymousClass6(this, r24));
        this.communityId = r25;
        this.currentCommunity = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r25));
        this.communityInfo = MessagesController.getInstance(this.currentAccount).getChatFull(r25);
        TLRPC.Chat r6 = this.currentCommunity;
        if (r6 != null) goto L9;
    L11:
        boolean r73 = false;
    L12:
        this.collapsedInDialogs = r73;
        FiltersView.MediaFilterData r74 = new FiltersView.MediaFilterData(R.drawable.search_users_filled, DialogObject.getShortName(r6), null, 4);
        r74.setUser(this.currentCommunity);
        r74.removable = false;
        r4.addSearchFilter(r74);
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray, this.resourcesProvider));
        this.requestsPage = new PendingRequestsPage(this, r33);
        this.communityPage = new CommunityPage(this, r33);
        this.chatsPage = new ChatsToAddListPage(this, r33);
        this.viewPager.setAdapter(new AnonymousClass7(this));
        r4.setCloseButtonOnClickListener(new CommunitySheet$$ExternalSyntheticLambda2(this));
        r5.setCloseButtonOnClickListener(new CommunitySheet$$ExternalSyntheticLambda3(this));
        this.pendingRequestsList.loadNext();
        MessagesController.getInstance(this.currentAccount).loadFullChat(r25, 0, true);
        Bulletin.addDelegate((FrameLayout) this.containerView, new AnonymousClass8(this));
        ViewCompat.setOnApplyWindowInsetsListener(this.containerView, new CommunitySheet$$ExternalSyntheticLambda4(this));
        return;
    L9:
        if (r6.collapsed_in_dialogs == false) goto L11;
        r73 = true;
        goto L12
    L5:
        r32 = false;
        goto L6
    }

    public static /* synthetic */ int access$000(CommunitySheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ ViewGroup access$100(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1000(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1100(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1200(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1300(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$1400(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1500(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1600(CommunitySheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1700(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$1800(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$1900(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$200(CommunitySheet r0) {
        return r0.currentAccount;
    }

    public static /* synthetic */ int access$2000(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2100(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2200(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$2300(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$2400(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$2500(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$2600(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2700(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2800(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ ViewGroup access$2900(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ Theme.ResourcesProvider access$300(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ ViewGroup access$3000(CommunitySheet r0) {
        return r0.containerView;
    }

    public static /* synthetic */ int access$3100(CommunitySheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3200(CommunitySheet r0) {
        return r0.backgroundPaddingLeft;
    }

    public static /* synthetic */ int access$3300(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$3400(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$400(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$500(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$600(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ int access$700(CommunitySheet r0, int r1) {
        return r0.getThemedColor(r1);
    }

    public static /* synthetic */ Theme.ResourcesProvider access$800(CommunitySheet r0) {
        return r0.resourcesProvider;
    }

    public static /* synthetic */ int access$900(CommunitySheet r0) {
        return r0.currentAccount;
    }

    private boolean checkPendingRequestClick(UItem r6) {
        Object r62 = r6.object;
        if ((r62 instanceof CommunityPendingRequestCell.Data) == false) goto L16;
        CommunityPendingRequestCell.Data r63 = (CommunityPendingRequestCell.Data) r62;
        TLRPC.Chat r0 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(-r63.dialogToAdd));
        TLRPC.User r1 = MessagesController.getInstance(this.currentAccount).getUser(Long.valueOf(r63.dialogToAdd));
        if (r1 == null) goto L8;
        this.parentFragment.presentFragment(ChatActivity.of(r1.id));
        return true;
    L8:
        if (ChatObject.isPublic(r0) == false) goto L10;
    L13:
        this.parentFragment.presentFragment(ChatActivity.of(-r0.id));
        return true;
    L10:
        if (ChatObject.isInChat(r0) == true) goto L13;
        new CommunityInviteOnlySheet(getContext(), r0, r63.requestFromUser, new CommunitySheet$$ExternalSyntheticLambda10(this, r63)).show();
        return true;
    L16:
        return false;
    }

    private void fillItemsChatsToAdd(ArrayList<UItem> r2, UniversalAdapter r3) {
        fillItemsChatsToAddImpl(r2, r3, false);
    }

    private void fillItemsChatsToAddImpl(ArrayList<UItem> r5, UniversalAdapter r6, boolean r7) {
        int r62 = 0;
        if (r7 == true) goto L6;
        r5.add(UItem.asSpace(99, (int) (AndroidUtilities.displaySize.y * 0.35f)));
        r5.add(UItem.asSpace(0, AndroidUtilities.dp(56.0f)));
    L6:
        if (this.chatsToAddToCommunity == null) goto L24;
        if (r7 == false) goto L11;
        String r0 = this.lastSearchChatsString;
        if (r0 == null) goto L11;
        String r02 = r0.toLowerCase();
    L12:
        ArrayList<TLRPC.Chat> r4 = this.chatsToAddToCommunity;
        int r1 = r4.size();
    L13:
        if (r62 >= r1) goto L36;
        TLRPC.Chat r2 = r4.get(r62);
        r62 = r62 + 1;
        TLRPC.Chat r22 = r2;
        if (r7 == false) goto L23;
        if (TextUtils.isEmpty(r02) == true) goto L23;
        String r3 = r22.title;
        if (r3 == null) goto L13;
        if (r3.toLowerCase().contains(r02) == false) goto L13;
        r5.add(UItem.asProfileCell(r22));
    L23:
        r5.add(UItem.asProfileCell(r22));
        goto L13
    L36:
        return;
    L11:
        r02 = null;
        goto L12
    }

    private void fillItemsChatsToAddSearch(ArrayList<UItem> r2, UniversalAdapter r3) {
        fillItemsChatsToAddImpl(r2, r3, true);
    }

    private void fillItemsCommunity(ArrayList<UItem> r11, UniversalAdapter r12) {
        r11.add(UItem.asSpace(99, Math.min(AndroidUtilities.statusBarHeight + AndroidUtilities.dp(176.0f), (int) (AndroidUtilities.displaySize.y * 0.25f))));
        r11.add(UItem.asSpace(0, AndroidUtilities.dp(56.0f)));
        r11.add(UItem.asSwitchNoIcon(101, LocaleController.getString(R.string.CommunityShowAsOneChat)).setChecked(this.collapsedInDialogs));
        r11.add(UItem.asShadow(2, LocaleController.getString(R.string.CommunityShowAsOneChatInfo)));
        if (this.pendingRequestsList.isSingle() == false) goto L6;
        r11.add(UItem.asHeader(3, LocaleController.getString(R.string.CommunityPendingRequest)));
        this.pendingRequestsList.fillItems(r11);
        r11.add(UItem.asSpace(5, AndroidUtilities.dp(14.33f)));
    L17:
        CommunityUtils.fillLinkedPeers(this.currentAccount, r11, this, this.communityId, true);
        return;
    L6:
        if (this.pendingRequestsList.getTotalCount() <= 0) goto L17;
        int r122 = this.pendingRequestsList.getTotalCount();
        int r3 = this.pendingRequestsList.getUnreadCount();
        IconBackgroundColors r5 = IconBackgroundColors.BLUE_ALT;
        int r6 = R.drawable.filled_requests_24;
        if (r122 != r3) goto L11;
        String r123 = LocaleController.getString(R.string.CommunityPendingRequests);
    L10:
        String r7 = r123;
        if (r3 <= 0) goto L15;
        String r124 = Integer.toString(r3);
    L16:
        r11.add(CommunityRequestsCell.Factory.of(100, r5, r6, r7, r124, true));
        r11.add(UItem.asSpace(5, AndroidUtilities.dp(14.33f)));
        goto L17
    L15:
        r124 = null;
        goto L16
    L11:
        r123 = LocaleController.formatPluralString("CommunityPendingRequestsRow", r122, new Object[0]);
        goto L10
    }

    private void fillItemsRequests(ArrayList<UItem> r4, UniversalAdapter r5) {
        r4.add(UItem.asSpace(99, (int) (AndroidUtilities.displaySize.y * 0.35f)));
        r4.add(UItem.asSpace(0, AndroidUtilities.dp(48.0f)));
        if (ChatObject.canBlockUsers(this.currentCommunity) == false) goto L5;
        r4.add(UItem.asShadow(1, AndroidUtilities.replaceArrows(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.CommunityPendingRequestsInfo), new CommunitySheet$$ExternalSyntheticLambda8(this)), true)));
    L6:
        r4.add(UItem.asCustom(2, this.fakeAnchorView));
        r4.add(UItem.asHeader(3, LocaleController.formatPluralString("CommunityPendingRequestsSuggestedHeader", this.pendingRequestsList.getTotalCount(), new Object[0])));
        this.pendingRequestsList.fillItems(r4);
        return;
    L5:
        r4.add(UItem.asShadow(1, LocaleController.getString(R.string.CommunityPendingRequestsInfoNoChange)));
        goto L6
    }

    private void init(Context r4) {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.updateInterfaces);
        Paint r0 = this.backgroundPaint;
        int r1 = Theme.key_windowBackgroundGray;
        r0.setColor(Theme.getColor(r1, this.resourcesProvider));
        fixNavigationBar(Theme.getColor(r1, this.resourcesProvider));
        this.containerView = new ContainerView(this, r4);
        AnonymousClass9 r02 = new AnonymousClass9(this, r4);
        this.viewPager = r02;
        int r42 = this.backgroundPaddingLeft;
        r02.setPadding(r42, 0, r42, 0);
        this.containerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1, 119));
    }

    private /* synthetic */ void lambda$checkPendingRequestClick$7(CommunityPendingRequestCell.Data r3) {
        this.parentFragment.presentFragment(ChatActivity.of(r3.requestFromUser.id));
    }

    private /* synthetic */ void lambda$fillItemsRequests$2() {
        Bundle r0 = new Bundle();
        r0.putLong("community_id", this.communityId);
        this.parentFragment.presentFragment(new CommunityEditActivity(r0));
        dismiss();
    }

    private /* synthetic */ void lambda$linkToCommunity$10(boolean r2, TLRPC.Bool r3, TLRPC.TL_error r4) {
        if (r4 != null) goto L4;
        onLinkSuccess(1, r2);
        return;
    L4:
        if (TextUtils.equals("COMMUNITY_REQUEST_CREATED", r4.text) == false) goto L7;
        onLinkSuccess(2, r2);
        return;
    L7:
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r4);
    }

    private /* synthetic */ void lambda$linkToCommunity$9(AlertDialog r3, long r4, boolean r6, long r7) {
        r3.dismiss();
        if (r7 != 0) goto L5;
        return;
    L5:
        linkToCommunity(MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(r7)), r4, r6);
    }

    private /* synthetic */ void lambda$loadChatsToAddToCommunity$8(ArrayList r3, TLRPC.TL_error r4) {
        this.addChatToCommunityButton.setLoading(false);
        if (r4 == null) goto L6;
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r4);
        return;
    L6:
        if (r3 == null) goto L13;
        this.chatsToAddToCommunity = r3;
        if (r3.isEmpty() == false) goto L11;
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).createSimpleBulletin(R.raw.info, LocaleController.getString(R.string.CommunityNoChatsToAdd)).show();
        return;
    L11:
        this.chatsPage.listView.adapter.update(false);
        this.viewPager.scrollToPosition(2);
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        this.communityPage.listView.layoutManager.scrollToPositionWithOffset(1, this.systemInsets.top);
        this.animatorSearchMessagesVisible.setValue(false, true);
        setAllowNestedScroll(true);
        AndroidUtilities.hideKeyboard(this.messagesSearchView.editText);
        this.messagesSearchView.editText.clearFocus();
    }

    private /* synthetic */ void lambda$new$1() {
        this.chatsPage.listView.layoutManager.scrollToPositionWithOffset(1, this.systemInsets.top);
        this.animatorSearchChatsVisible.setValue(false, true);
        setAllowNestedScroll(true);
        AndroidUtilities.hideKeyboard(this.chatsSearchView.editText);
        this.chatsSearchView.editText.clearFocus();
    }

    private /* synthetic */ void lambda$onClickChatToAdd$3(TLRPC.Chat r3, Boolean r4) {
        linkToCommunity(r3, this.communityId, r4.booleanValue());
    }

    private /* synthetic */ void lambda$onLongClickCommunity$4(TLRPC.Bool r1, TLRPC.TL_error r2) {
        if (r2 == null) goto L5;
        BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider).showForError(r2);
        return;
    }

    private /* synthetic */ void lambda$onLongClickCommunity$5(long r8) {
        MessagesController.getInstance(this.currentAccount).unlinkCommunity(r8, this.communityId, new CommunitySheet$$ExternalSyntheticLambda13(this));
    }

    private /* synthetic */ void lambda$onLongClickCommunity$6(boolean r8, boolean r9, long r10) {
        Context r0 = getContext();
        Theme.ResourcesProvider r1 = this.resourcesProvider;
        String r2 = LocaleController.getString(R.string.CommunityMenuRemoveFromCommunity);
        if (r8 == false) goto L5;
        int r82 = R.string.CommunityMenuRemoveBotFromCommunityConfirm;
    L8:
        AlertsCreator.showSimpleConfirmAlert(r0, r1, r2, LocaleController.getString(r82), LocaleController.getString(R.string.Remove), true, new CommunitySheet$$ExternalSyntheticLambda12(this, r10));
        return;
    L5:
        if (r9 == false) goto L7;
        r82 = R.string.CommunityMenuRemoveChannelFromCommunityConfirm;
        goto L8
    L7:
        r82 = R.string.CommunityMenuRemoveGroupFromCommunityConfirm;
        goto L8
    }

    private void linkToCommunity(TLRPC.Chat r14, long r15, boolean r17) {
        long r5 = -r14.id;
        boolean r2 = ChatObject.isChannelAndNotMegaGroup(r14);
        if (ChatObject.isChannel(r14) == true) goto L6;
        AlertDialog r22 = new AlertDialog(getContext(), 3);
        r22.showDelayed(250);
        MessagesController.getInstance(this.currentAccount).convertToMegaGroup(getContext(), -r5, null, new CommunitySheet$$ExternalSyntheticLambda6(this, r22, r15, r17));
        return;
    L6:
        MessagesController.getInstance(this.currentAccount).linkCommunity(r5, r15, r17, new CommunitySheet$$ExternalSyntheticLambda7(this, r2));
    }

    private void loadChatsToAddToCommunity() {
        if (this.addChatToCommunityButton.isLoading() == false) goto L5;
        return;
    L5:
        this.addChatToCommunityButton.setLoading(true);
        MessagesController.getInstance(this.currentAccount).fetchChatsToAddToCommunity(new CommunitySheet$$ExternalSyntheticLambda9(this));
    }

    private void onAddChatToCommunityButtonClick() {
        if (ChatObject.canAddChatToCommunity(this.currentCommunity) == true) goto L6;
        dismiss();
        return;
    L6:
        loadChatsToAddToCommunity();
    }

    private WindowInsetsCompat onApplyWindowInsets(View r2, WindowInsetsCompat r3) {
        this.systemAndImeInsets = r3.getInsets(WindowInsetsCompat.Type.systemBars() | WindowInsetsCompat.Type.ime());
        this.systemInsets = r3.getInsets(WindowInsetsCompat.Type.systemBars());
        this.filteredSearchView.setPagesPaddings(this.systemAndImeInsets.top + AndroidUtilities.dp(56.0f), this.systemAndImeInsets.bottom);
        this.communityPageFadeView.invalidate();
        return WindowInsetsCompat.CONSUMED;
    }

    private void onChatsSearchTextChanged(String r1) {
        this.lastSearchChatsString = r1;
        this.foundChatsView.adapter.update(true);
    }

    private void onClickChatToAdd(UItem r7, View r8, int r9, float r10, float r11) {
        Object r72 = r7.object;
        if ((r72 instanceof TLRPC.Chat) == false) goto L10;
        TLRPC.Chat r73 = (TLRPC.Chat) r72;
        if (this.onlyChatsMode == false) goto L8;
        this.chatsToAddCallback.run(r73);
        dismiss();
        return;
    L8:
        new CommunityAddOptionsSheet(getContext(), this.currentCommunity, -r73.id, new CommunitySheet$$ExternalSyntheticLambda5(this, r73)).show();
        return;
    }

    private void onClickCommunity(UItem r7, View r8, int r9, float r10, float r11) {
        if (checkPendingRequestClick(r7) == true) goto L67;
        int r92 = r7.id;
        boolean r112 = false;
        if (r92 != 101) goto L14;
        this.collapsedInDialogs = !this.collapsedInDialogs;
        MessagesController.getInstance(this.currentAccount).toggleCommunityCollapsedInDialogs(this.communityId, this.collapsedInDialogs);
        if ((r8 instanceof TextCheckCell2) == false) goto L11;
        ((TextCheckCell2) r8).getCheckBox().setChecked(this.collapsedInDialogs, true);
        return;
    L11:
        this.communityPage.listView.adapter.update(false);
        return;
    L14:
        if (r92 != 100) goto L17;
        this.viewPager.scrollToPosition(1);
        this.pendingRequestsList.markAsViewed();
        return;
    L17:
        Object r72 = r7.object;
        if ((r72 instanceof TLRPC.Chat) == false) goto L22;
        TLRPC.Chat r73 = (TLRPC.Chat) r72;
        r112 = ChatObject.isChannelAndNotMegaGroup(r73);
        long r82 = -r73.id;
    L20:
        TLRPC.Chat r2 = r73;
        CommunityChatType r74 = CommunityUtils.getCommunityChatType(this.currentAccount, r82);
        if (r74 != CommunityChatType.YouAreIn) goto L27;
    L41:
        BaseFragment r75 = this.parentFragment;
        if ((r75 instanceof ChatActivity) == false) goto L52;
        TLRPC.Chat r76 = ((ChatActivity) r75).getCurrentChat();
        TLRPC.User r102 = ((ChatActivity) this.parentFragment).getCurrentUser();
        if (r76 != null) goto L46;
    L47:
        if (r102 == null) goto L52;
        if (r102.id != r82) goto L52;
    L50:
        dismiss();
        return;
    L46:
        if (r76.id == (-r82)) goto L50;
    L52:
        Bundle r77 = new Bundle();
        if (r82 <= 0) goto L55;
        r77.putLong("user_id", r82);
    L57:
        if (ChatObject.isForum(r2) == true) goto L59;
        this.parentFragment.presentFragment(new ChatActivity(r77));
    L63:
        dismiss();
        return;
    L59:
        if (ChatObject.areTabsEnabled(r2) == false) goto L61;
        ChatActivity r103 = new ChatActivity(r77);
        ForumUtilities.applyTopic(r103, MessagesStorage.TopicKey.of(r82, MessagesController.getInstance(this.currentAccount).getForumLastTopicId(r2.id)));
        this.parentFragment.presentFragment(r103);
        goto L63
    L61:
        this.parentFragment.presentFragment(new TopicsFragment(r77));
        goto L63
    L55:
        r77.putLong("chat_id", -r82);
        goto L57
    L27:
        if (r74 == CommunityChatType.YouCanView) goto L41;
        if (r74 != CommunityChatType.YouCanSendJoinRequest) goto L34;
        new JoinGroupAlert(getContext(), r2, null, this.parentFragment, this.resourcesProvider).setBulletinFactory(BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider)).show();
        return;
    L34:
        if (r74 != CommunityChatType.HiddenUnavailable) goto L66;
        BulletinFactory r6 = BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider);
        int r78 = R.raw.e_hand_2;
        if (r112 == false) goto L38;
        int r83 = R.string.CommunityHiddenChannelUnavailable;
    L39:
        r6.createSimpleBulletin(r78, LocaleController.getString(r83)).show();
        return;
    L38:
        r83 = R.string.CommunityHiddenGroupUnavailable;
        goto L39
    L66:
        return;
    L22:
        if ((r72 instanceof TLRPC.User) == false) goto L65;
        r82 = ((TLRPC.User) r72).id;
        r73 = null;
        goto L20
    L65:
        return;
    }

    private void onClickRequest(UItem r1, View r2, int r3, float r4, float r5) {
        checkPendingRequestClick(r1);
    }

    private void onLinkSuccess(int r3, boolean r4) {
        CommunityUtils.showCommunityLinkSuccessToast(BulletinFactory.of((FrameLayout) this.containerView, this.resourcesProvider), r3, r4);
        this.viewPager.scrollToPosition(0);
    }

    private boolean onLongClickCommunity(UItem r7, View r8, int r9, float r10, float r11) {
        Object r72 = r7.object;
        if ((r72 instanceof TLRPC.Chat) == false) goto L7;
        TLRPC.Chat r73 = (TLRPC.Chat) r72;
        long r0 = -r73.id;
        boolean r92 = ChatObject.isChannelAndNotMegaGroup(r73);
        boolean r74 = ChatObject.canRemoveChatFromCommunity(r73, this.currentCommunity);
        boolean r3 = r92;
        boolean r2 = false;
    L5:
        long r4 = r0;
        if (r74 == true) goto L11;
        return false;
    L11:
        ItemOptions r75 = ItemOptions.makeOptions(this.container, r8);
        r75.add(R.drawable.msg_cancel, LocaleController.getString(R.string.CommunityMenuRemoveFromCommunity), true, new CommunitySheet$$ExternalSyntheticLambda11(this, r2, r3, r4));
        r75.setScrimViewBackground(this.communityPage.listView.getClipBackground(r8, true));
        r75.show();
        return true;
    L7:
        if ((r72 instanceof TLRPC.User) == false) goto L13;
        TLRPC.User r76 = (TLRPC.User) r72;
        r0 = r76.id;
        boolean r93 = UserObject.isBot(r76);
        r74 = ChatObject.canRemoveBotFromCommunity(r76, this.currentCommunity);
        r2 = r93;
        r3 = false;
        goto L5
    L13:
        return false;
    }

    private void onMessagesSearchTextChanged(String r2) {
        onMessagesSearchTextChanged(r2, false);
    }

    private void onMessagesSearchTextChanged(String r14, boolean r15) {
        if (TextUtils.isEmpty(this.lastSearchString) == false) goto L5;
        r15 = true;
    L5:
        this.lastSearchString = r14;
        FilteredSearchView r0 = this.filteredSearchView;
        long r3 = this.communityId;
        r0.search(0, r3, 0, 0, null, false, r14, r15);
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public boolean canClickButtonInside() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canDismissWithSwipe() {
        if (this.animatorSearchMessagesVisible.getValue() == false) goto L5;
        return false;
    L5:
        if (this.animatorSearchChatsVisible.getValue() == true) goto L15;
        View r1 = this.viewPager.getCurrentView();
        if ((r1 instanceof Page) == true) goto L10;
        return true;
    L10:
        return ((Page) r1).wasAtTop;
    L15:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public boolean canSwipeToBack(MotionEvent r1) {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.chatInfoDidLoad) goto L9;
        TLRPC.ChatFull r32 = (TLRPC.ChatFull) r5[0];
        if (r32.id != this.communityId) goto L20;
        this.communityInfo = r32;
        this.communityPage.listView.adapter.update(true);
        return;
    L20:
        return;
    L9:
        if (r3 != NotificationCenter.updateInterfaces) goto L21;
        Integer r33 = (Integer) r5[0];
        if ((r33.intValue() & MessagesController.UPDATE_MASK_CHAT) == 0) goto L13;
    L18:
        TLRPC.Chat r34 = MessagesController.getInstance(this.currentAccount).getChat(Long.valueOf(this.communityId));
        this.currentCommunity = r34;
        this.communityPage.actionBar.setTitle(DialogObject.getName(r34));
        CommunityPage.m21556$$Nest$fgetavatarImage(this.communityPage).setForUserOrChat(this.currentCommunity, CommunityPage.m21555$$Nest$fgetavatarDrawable(this.communityPage));
        return;
    L13:
        if ((r33.intValue() & MessagesController.UPDATE_MASK_AVATAR) != 0) goto L18;
        if ((r33.intValue() & MessagesController.UPDATE_MASK_CHAT_AVATAR) != 0) goto L18;
        if ((r33.intValue() & MessagesController.UPDATE_MASK_CHAT_NAME) != 0) goto L18;
        return;
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet
    public void dismissInternal() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.chatInfoDidLoad);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.updateInterfaces);
        super.dismissInternal();
    }

    @Override // org.telegram.ui.ActionBar.BottomSheet, android.app.Dialog
    public void onBackPressed() {
        if (this.viewPager.getCurrentPosition() > 0) goto L5;
        super.onBackPressed();
        return;
    L5:
        if (this.viewPager.getCurrentPosition() == 2) goto L7;
    L10:
        this.viewPager.scrollToPosition(0);
        return;
    L7:
        if (this.animatorSearchChatsVisible.getValue() == false) goto L10;
        this.chatsPage.listView.layoutManager.scrollToPositionWithOffset(1, this.systemInsets.top);
        this.animatorSearchChatsVisible.setValue(false, true);
        setAllowNestedScroll(true);
        AndroidUtilities.hideKeyboard(this.chatsSearchView.editText);
        this.chatsSearchView.editText.clearFocus();
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonClicked(DialogCell r7) {
        if (r7.getMessage() == null) goto L8;
        TLRPC.TL_forumTopic r0 = MessagesController.getInstance(this.currentAccount).getTopicsController().findTopic(-r7.getDialogId(), MessageObject.getTopicId(this.currentAccount, r7.getMessage().messageOwner, true));
        if (r0 == null) goto L9;
        ForumUtilities.openTopic(this.parentFragment, -r7.getDialogId(), r0, 0);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void onButtonLongPress(DialogCell r1) {
    }

    @Override // me.vkryl.android.animator.FactorAnimator.Target
    public void onFactorChanged(int r10, float r11, float r12, FactorAnimator r13) {
        int r3 = 8;
        if (r10 != 1) goto L26;
        float r122 = FBool.not(r11);
        float r5 = AndroidUtilities.lerp(0.9f, 1.0f, r122);
        this.communityPage.actionBar.setAlpha(r122);
        this.communityPage.actionBar.setScaleX(r5);
        this.communityPage.actionBar.setScaleY(r5);
        ActionBar r52 = this.communityPage.actionBar;
        if (r122 <= 0.0f) goto L7;
        int r7 = 0;
    L8:
        r52.setVisibility(r7);
        float r53 = AndroidUtilities.lerp(0.9f, 1.0f, r11);
        this.messagesSearchView.setAlpha(r11);
        this.messagesSearchView.setScaleX(r53);
        this.messagesSearchView.setScaleY(r53);
        FragmentSearchField r54 = this.messagesSearchView;
        if (r11 <= 0.0f) goto L11;
        int r8 = 0;
    L12:
        r54.setVisibility(r8);
        this.communityPage.listView.setAlpha(r122);
        UniversalRecyclerView r55 = this.communityPage.listView;
        if (r122 <= 0.0f) goto L15;
        int r82 = 0;
    L16:
        r55.setVisibility(r82);
        this.addChatToCommunityButton.setAlpha(r122);
        this.addChatToCommunityButton.setScaleX(AndroidUtilities.lerp(0.95f, 1.0f, r122));
        this.addChatToCommunityButton.setScaleY(AndroidUtilities.lerp(0.95f, 1.0f, r122));
        ButtonWithCounterView r123 = this.addChatToCommunityButton;
        if (r122 <= 0.0f) goto L19;
        int r56 = 0;
    L20:
        r123.setVisibility(r56);
        this.filteredSearchView.setAlpha(r11);
        FilteredSearchView r124 = this.filteredSearchView;
        if (r11 <= 0.0f) goto L23;
        int r57 = 0;
    L24:
        r124.setVisibility(r57);
        this.containerView.invalidate();
        this.communityPageFadeView.invalidate();
        goto L26
    L23:
        r57 = 8;
        goto L24
    L19:
        r56 = 8;
        goto L20
    L15:
        r82 = 8;
        goto L16
    L11:
        r8 = 8;
        goto L12
    L7:
        r7 = 8;
    L26:
        if (r10 != 2) goto L51;
        float r102 = FBool.not(r11);
        float r125 = AndroidUtilities.lerp(0.9f, 1.0f, r102);
        this.chatsPage.actionBar.setAlpha(r102);
        this.chatsPage.actionBar.setScaleX(r125);
        this.chatsPage.actionBar.setScaleY(r125);
        ActionBar r126 = this.chatsPage.actionBar;
        if (r102 <= 0.0f) goto L30;
        int r6 = 0;
    L31:
        r126.setVisibility(r6);
        float r127 = AndroidUtilities.lerp(0.9f, 1.0f, r11);
        this.chatsSearchView.setAlpha(r11);
        this.chatsSearchView.setScaleX(r127);
        this.chatsSearchView.setScaleY(r127);
        FragmentSearchField r128 = this.chatsSearchView;
        if (r11 <= 0.0f) goto L34;
        int r1 = 0;
    L35:
        r128.setVisibility(r1);
        this.chatsPage.listView.setAlpha(r102);
        UniversalRecyclerView r129 = this.chatsPage.listView;
        if (r102 <= 0.0f) goto L38;
        int r14 = 0;
    L39:
        r129.setVisibility(r14);
        if (this.onlyChatsMode == true) goto L46;
        this.closeChatToCommunityButton.setAlpha(r102);
        this.closeChatToCommunityButton.setScaleX(AndroidUtilities.lerp(0.95f, 1.0f, r102));
        this.closeChatToCommunityButton.setScaleY(AndroidUtilities.lerp(0.95f, 1.0f, r102));
        ButtonWithCounterView r103 = this.closeChatToCommunityButton;
        if (r102 <= 0.0f) goto L44;
        int r1210 = 0;
    L45:
        r103.setVisibility(r1210);
        goto L46
    L44:
        r1210 = 8;
    L46:
        this.foundChatsView.setAlpha(r11);
        UniversalRecyclerView r104 = this.foundChatsView;
        if (r11 <= 0.0f) goto L49;
        r3 = 0;
    L49:
        r104.setVisibility(r3);
        this.containerView.invalidate();
        this.chatsPageFadeView.invalidate();
        return;
    L38:
        r14 = 8;
        goto L39
    L34:
        r1 = 8;
        goto L35
    L30:
        r6 = 8;
        goto L31
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openHiddenStories() {
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void openStory(DialogCell r4, Runnable r5) {
        if (MessagesController.getInstance(this.currentAccount).getStoriesController().hasStories(r4.getDialogId()) == false) goto L6;
        this.parentFragment.getOrCreateStoryViewer().doOnAnimationReady(r5);
        this.parentFragment.getOrCreateStoryViewer().open(this.parentFragment.getContext(), r4.getDialogId(), StoriesListPlaceProvider.of((RecyclerListView) r4.getParent()));
        return;
    }

    @Override // org.telegram.ui.Cells.DialogCell.DialogCellDelegate
    public void showChatPreview(DialogCell r1) {
    }
}
