package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.HashtagSearchController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Adapters.MessagesSearchAdapter;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.ChatActivityContainer;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Stories.StoriesController;

/* loaded from: classes7.dex */
public class HashtagActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ChatActivityContainer chatContainer;
    private FrameLayout contentView;
    private ValueAnimator contentViewAnimator;
    private float contentViewValue;
    private final String hashtag;
    private final String query;
    private SharedMediaLayout sharedMediaLayout;
    private FrameLayout sharedMediaLayoutContainer;
    private final StoriesController.SearchStoriesList storiesList;
    private FrameLayout storiesTotal;
    private TextView storiesTotalTextView;
    private MessagesSearchAdapter.StoriesView storiesView;
    private boolean storiesVisible;
    private ValueAnimator transitAnimator;
    private float transitValue;
    private final String username;

    /* renamed from: org.telegram.ui.Components.HashtagActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass1(HashtagActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass2(HashtagActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void setTranslationY(float r2) {
            super.setTranslationY(r2);
            setPadding(0, 0, (int) r2, 0);
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ChatActivityContainer {
        boolean activityCreated;
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass3(HashtagActivity r1, Context r2, INavigationLayout r3, Bundle r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
            this.activityCreated = false;
        }

        @Override // org.telegram.ui.ChatActivityContainer
        public void initChatActivity() {
            if (this.activityCreated == true) goto L6;
            this.activityCreated = true;
            super.initChatActivity();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements SharedMediaLayout.Delegate {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass4(HashtagActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean canSearchMembers() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public TLRPC.Chat getCurrentChat() {
            return null;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public RecyclerListView getListView() {
            return null;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean isFragmentOpened() {
            return true;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public boolean onMemberClick(TLRPC.ChatParticipant r1, boolean r2, boolean r3, View r4) {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public void scrollToSharedMedia() {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout.Delegate
        public void updateSelectedMediaTabText() {
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SharedMediaLayout {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass5(HashtagActivity r16, Context r17, long r18, SharedMediaLayout.SharedMediaPreloader r20, int r21, ArrayList r22, TLRPC.ChatFull r23, TLRPC.UserFull r24, int r25, int r26, BaseFragment r27, SharedMediaLayout.Delegate r28, int r29, Theme.ResourcesProvider r30) {
            this.this$0 = r16;
            super(r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean addActionButtons() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean canShowSearchItem() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean customTabs() {
            return true;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void drawBackgroundWithBlur(Canvas r1, float r2, Rect r3, Paint r4) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public int getInitialTab() {
            return 8;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public String getStoriesHashtag() {
            return HashtagActivity.m10896$$Nest$fgethashtag(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public String getStoriesHashtagUsername() {
            return HashtagActivity.m10900$$Nest$fgetusername(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean includeSavedDialogs() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean includeStories() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void invalidateBlur() {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean isArchivedOnlyStoriesView() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean isSearchingStories() {
            return true;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean isStoriesView() {
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onActionModeSelectedUpdate(SparseArray<MessageObject> r1) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onSearchStateChanged(boolean r1) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onTabProgress(float r1) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onTabScroll(boolean r1) {
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void showActionMode(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$6, reason: invalid class name */
    public class AnonymousClass6 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass6(HashtagActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            HashtagActivity.m10901$$Nest$fputcontentViewValue(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            HashtagActivity.m10894$$Nest$fgetcontentView(this.this$0).setTranslationY(HashtagActivity.m10895$$Nest$fgetcontentViewValue(this.this$0) * AndroidUtilities.dp(48.0f));
            HashtagActivity.m10894$$Nest$fgetcontentView(this.this$0).setPadding(0, 0, 0, (int) (HashtagActivity.m10895$$Nest$fgetcontentViewValue(this.this$0) * AndroidUtilities.dp(48.0f)));
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ HashtagActivity this$0;
        final /* synthetic */ boolean val$visible;

        public AnonymousClass7(HashtagActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$visible = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            HashtagActivity r42 = this.this$0;
            if (this.val$visible == false) goto L5;
            float r0 = 1.0f;
        L6:
            HashtagActivity.m10901$$Nest$fputcontentViewValue(r42, r0);
            HashtagActivity.m10894$$Nest$fgetcontentView(this.this$0).setTranslationY(HashtagActivity.m10895$$Nest$fgetcontentViewValue(this.this$0) * AndroidUtilities.dp(48.0f));
            HashtagActivity.m10894$$Nest$fgetcontentView(this.this$0).setPadding(0, 0, 0, (int) (HashtagActivity.m10895$$Nest$fgetcontentViewValue(this.this$0) * AndroidUtilities.dp(48.0f)));
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$8, reason: invalid class name */
    public class AnonymousClass8 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ HashtagActivity this$0;

        public AnonymousClass8(HashtagActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            HashtagActivity.m10902$$Nest$fputtransitValue(this.this$0, ((Float) r4.getAnimatedValue()).floatValue());
            HashtagActivity.m10897$$Nest$fgetsharedMediaLayout(this.this$0).setScaleX(AndroidUtilities.lerp(0.95f, 1.0f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            HashtagActivity.m10897$$Nest$fgetsharedMediaLayout(this.this$0).setScaleY(AndroidUtilities.lerp(0.95f, 1.0f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0) != null) goto L5;
        L9:
            HashtagActivity.m10898$$Nest$fgetsharedMediaLayoutContainer(this.this$0).setAlpha(HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0));
            return;
        L5:
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity == null) goto L9;
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView == null) goto L9;
            HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView.setScaleX(AndroidUtilities.lerp(1.0f, 0.95f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView.setScaleY(AndroidUtilities.lerp(1.0f, 0.95f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            goto L9
        }
    }

    /* renamed from: org.telegram.ui.Components.HashtagActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ HashtagActivity this$0;
        final /* synthetic */ boolean val$stories;

        public AnonymousClass9(HashtagActivity r1, boolean r2) {
            this.this$0 = r1;
            this.val$stories = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r4) {
            HashtagActivity r42 = this.this$0;
            if (this.val$stories == false) goto L5;
            float r0 = 1.0f;
        L6:
            HashtagActivity.m10902$$Nest$fputtransitValue(r42, r0);
            HashtagActivity.m10897$$Nest$fgetsharedMediaLayout(this.this$0).setScaleX(AndroidUtilities.lerp(0.95f, 1.0f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            HashtagActivity.m10897$$Nest$fgetsharedMediaLayout(this.this$0).setScaleY(AndroidUtilities.lerp(0.95f, 1.0f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0) != null) goto L9;
        L13:
            HashtagActivity.m10898$$Nest$fgetsharedMediaLayoutContainer(this.this$0).setAlpha(HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0));
            if (this.val$stories == true) goto L17;
            HashtagActivity.m10898$$Nest$fgetsharedMediaLayoutContainer(this.this$0).setVisibility(8);
            return;
        L17:
            return;
        L9:
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity == null) goto L13;
            if (HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView == null) goto L13;
            HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView.setScaleX(AndroidUtilities.lerp(1.0f, 0.95f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            HashtagActivity.m10893$$Nest$fgetchatContainer(this.this$0).chatActivity.messagesSearchListView.setScaleY(AndroidUtilities.lerp(1.0f, 0.95f, HashtagActivity.m10899$$Nest$fgettransitValue(this.this$0)));
            goto L13
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    public static /* synthetic */ void $r8$lambda$_DqlSOMfspI523cHekhp2JnI_Q8(HashtagActivity r0, boolean r1) {
        r0.lambda$updateStoriesVisible$1(r1);
    }

    /* renamed from: $r8$lambda$k7Zucm-6NY_SbbU72WzPfA69xKA, reason: not valid java name */
    public static /* synthetic */ void m10892$r8$lambda$k7Zucm6NY_SbbU72WzPfA69xKA(HashtagActivity r0, View r1) {
        r0.lambda$createView$0(r1);
    }

    /* renamed from: -$$Nest$fgetchatContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityContainer m10893$$Nest$fgetchatContainer(HashtagActivity r0) {
        return r0.chatContainer;
    }

    /* renamed from: -$$Nest$fgetcontentView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10894$$Nest$fgetcontentView(HashtagActivity r0) {
        return r0.contentView;
    }

    /* renamed from: -$$Nest$fgetcontentViewValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10895$$Nest$fgetcontentViewValue(HashtagActivity r0) {
        return r0.contentViewValue;
    }

    /* renamed from: -$$Nest$fgethashtag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10896$$Nest$fgethashtag(HashtagActivity r0) {
        return r0.hashtag;
    }

    /* renamed from: -$$Nest$fgetsharedMediaLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SharedMediaLayout m10897$$Nest$fgetsharedMediaLayout(HashtagActivity r0) {
        return r0.sharedMediaLayout;
    }

    /* renamed from: -$$Nest$fgetsharedMediaLayoutContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m10898$$Nest$fgetsharedMediaLayoutContainer(HashtagActivity r0) {
        return r0.sharedMediaLayoutContainer;
    }

    /* renamed from: -$$Nest$fgettransitValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m10899$$Nest$fgettransitValue(HashtagActivity r0) {
        return r0.transitValue;
    }

    /* renamed from: -$$Nest$fgetusername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m10900$$Nest$fgetusername(HashtagActivity r0) {
        return r0.username;
    }

    /* renamed from: -$$Nest$fputcontentViewValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10901$$Nest$fputcontentViewValue(HashtagActivity r0, float r1) {
        r0.contentViewValue = r1;
    }

    /* renamed from: -$$Nest$fputtransitValue, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10902$$Nest$fputtransitValue(HashtagActivity r0, float r1) {
        r0.transitValue = r1;
    }

    public HashtagActivity(String r2) {
        this(r2, null);
    }

    public HashtagActivity(String r4, Theme.ResourcesProvider r5) {
        setResourceProvider(r5);
        String r52 = _UrlKt.FRAGMENT_ENCODE_SET;
        if (r4 != null) goto L5;
        r4 = _UrlKt.FRAGMENT_ENCODE_SET;
    L5:
        String r42 = r4.trim();
        if (r42.startsWith("#") == false) goto L8;
    L10:
        int r1 = r42.indexOf("@");
        if (r1 <= 0) goto L13;
        this.hashtag = r42.substring(0, r1);
        this.username = r42.substring(r1 + 1);
    L14:
        StringBuilder r43 = new StringBuilder();
        r43.append(this.hashtag);
        if (TextUtils.isEmpty(this.username) == true) goto L17;
        r52 = "@" + this.username;
    L17:
        r43.append(r52);
        this.query = r43.toString();
        this.storiesList = new StoriesController.SearchStoriesList(this.currentAccount, this.username, this.hashtag);
        return;
    L13:
        this.hashtag = r42;
        this.username = null;
        goto L14
    L8:
        if (r42.startsWith("$") == true) goto L10;
        r42 = "#".concat(r42);
        goto L10
    }

    private /* synthetic */ void lambda$createView$0(View r2) {
        transit(!this.storiesVisible, true);
        this.storiesView.transition(this.storiesVisible);
    }

    private /* synthetic */ void lambda$updateStoriesVisible$1(boolean r1) {
        if (r1 == true) goto L5;
        this.storiesView.setVisibility(8);
        return;
    }

    private void transit(boolean r6, boolean r7) {
        ValueAnimator r0 = this.transitAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        int r02 = 0;
        float r1 = 0.0f;
        if (r7 == true) goto L35;
        this.storiesVisible = r6;
        if (r6 == false) goto L10;
        float r72 = 1.0f;
    L11:
        this.transitValue = r72;
        SharedMediaLayout r73 = this.sharedMediaLayout;
        if (r6 == false) goto L14;
        float r4 = 1.0f;
    L15:
        r73.setScaleX(r4);
        SharedMediaLayout r74 = this.sharedMediaLayout;
        if (r6 == false) goto L18;
        float r42 = 1.0f;
    L19:
        r74.setScaleY(r42);
        FrameLayout r75 = this.sharedMediaLayoutContainer;
        if (r6 == false) goto L22;
        r1 = 1.0f;
    L22:
        r75.setAlpha(r1);
        FrameLayout r76 = this.sharedMediaLayoutContainer;
        if (r6 == true) goto L26;
        r02 = 8;
    L26:
        r76.setVisibility(r02);
        ChatActivityContainer r62 = this.chatContainer;
        if (r62 == null) goto L36;
        ChatActivity r63 = r62.chatActivity;
        if (r63 == null) goto L42;
        RecyclerListView r64 = r63.messagesSearchListView;
        if (r64 == null) goto L43;
        r64.setScaleX(AndroidUtilities.lerp(1.0f, 0.95f, this.transitValue));
        this.chatContainer.chatActivity.messagesSearchListView.setScaleY(AndroidUtilities.lerp(1.0f, 0.95f, this.transitValue));
        return;
    L43:
        return;
    L42:
        return;
    L36:
        return;
    L18:
        r42 = 0.95f;
        goto L19
    L14:
        r4 = 0.95f;
        goto L15
    L10:
        r72 = 0.0f;
        goto L11
    L35:
        if (this.storiesVisible == r6) goto L44;
        this.storiesVisible = r6;
        this.sharedMediaLayoutContainer.setVisibility(0);
        float r77 = this.transitValue;
        if (r6 == false) goto L40;
        r1 = 1.0f;
    L40:
        ValueAnimator r78 = ValueAnimator.ofFloat(new float[]{r77, r1});
        this.transitAnimator = r78;
        r78.addUpdateListener(new AnonymousClass8(this));
        this.transitAnimator.addListener(new AnonymousClass9(this, r6));
        this.transitAnimator.setDuration(320);
        this.transitAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.transitAnimator.start();
        return;
    }

    private void updateStoriesVisible(boolean r7, boolean r8) {
        this.storiesView.animate().cancel();
        ValueAnimator r0 = this.contentViewAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        MessagesSearchAdapter.StoriesView r02 = this.storiesView;
        float r2 = 0.0f;
        if (r8 == true) goto L23;
        if (r7 == false) goto L9;
        int r82 = 0;
    L10:
        r02.setVisibility(r82);
        MessagesSearchAdapter.StoriesView r83 = this.storiesView;
        if (r7 == false) goto L13;
        float r03 = 0.0f;
    L14:
        r83.setTranslationY(r03);
        FrameLayout r84 = this.contentView;
        if (r7 == false) goto L17;
        r2 = AndroidUtilities.dp(48.0f);
    L17:
        r84.setTranslationY(r2);
        FrameLayout r6 = this.contentView;
        if (r7 == false) goto L20;
        int r72 = AndroidUtilities.dp(48.0f);
    L21:
        r6.setPadding(0, 0, 0, r72);
        return;
    L20:
        r72 = 0;
        goto L21
    L13:
        r03 = -AndroidUtilities.dp(48.0f);
        goto L14
    L9:
        r82 = 8;
        goto L10
    L23:
        r02.setVisibility(0);
        ViewPropertyAnimator r85 = this.storiesView.animate();
        if (r7 == false) goto L26;
        float r04 = 0.0f;
    L27:
        ViewPropertyAnimator r86 = r85.translationY(r04).withEndAction(new HashtagActivity$$ExternalSyntheticLambda0(this, r7)).setDuration(320);
        CubicBezierInterpolator r4 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r86.setInterpolator(r4).start();
        float r87 = this.contentViewValue;
        if (r7 == false) goto L30;
        r2 = 1.0f;
    L30:
        ValueAnimator r88 = ValueAnimator.ofFloat(new float[]{r87, r2});
        this.contentViewAnimator = r88;
        r88.addUpdateListener(new AnonymousClass6(this));
        this.contentViewAnimator.addListener(new AnonymousClass7(this, r7));
        this.contentViewAnimator.setDuration(320);
        this.contentViewAnimator.setInterpolator(r4);
        this.contentViewAnimator.start();
        return;
    L26:
        r04 = -AndroidUtilities.dp(48.0f);
        goto L27
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r28) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(this.query);
        ActionBar r0 = this.actionBar;
        int r4 = Theme.key_windowBackgroundWhite;
        r0.setBackgroundColor(getThemedColor(r4));
        ActionBar r02 = this.actionBar;
        int r5 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setItemsColor(getThemedColor(r5), false);
        this.actionBar.setItemsBackgroundColor(getThemedColor(Theme.key_actionBarWhiteSelector), false);
        this.actionBar.setTitleColor(getThemedColor(r5));
        this.actionBar.setCastShadows(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r03 = new FrameLayout(r28);
        this.fragmentView = r03;
        r03.setBackgroundColor(getThemedColor(r4));
        AnonymousClass2 r6 = new AnonymousClass2(this, r28);
        this.contentView = r6;
        r03.addView(r6, LayoutHelper.createFrame(-1, -1, 119));
        HashtagSearchController.getInstance(this.currentAccount).clearSearchResults(3);
        Bundle r62 = new Bundle();
        r62.putInt("chatMode", 7);
        r62.putInt("searchType", 3);
        r62.putString("searchHashtag", this.query);
        AnonymousClass3 r11 = new AnonymousClass3(this, r28, getParentLayout(), r62);
        this.chatContainer = r11;
        this.contentView.addView(r11, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass5 r04 = new AnonymousClass5(this, r28, 0, new SharedMediaLayout.SharedMediaPreloader(null), 0, null, null, null, 8, 0, this, new AnonymousClass4(this), 0, this.resourceProvider);
        this.sharedMediaLayout = r04;
        if (r04.getSearchOptionsItem() == null) goto L5;
        this.sharedMediaLayout.getSearchOptionsItem().setColorFilter(new PorterDuffColorFilter(Theme.getColor(r5, this.resourceProvider), PorterDuff.Mode.SRC_IN));
    L5:
        this.sharedMediaLayout.setPinnedToTop(true);
        this.sharedMediaLayout.photoVideoOptionsItem.setTranslationY(0.0f);
        if (this.sharedMediaLayout.getSearchOptionsItem() == null) goto L8;
        this.sharedMediaLayout.getSearchOptionsItem().setTranslationY(0.0f);
    L8:
        this.sharedMediaLayout.setBackgroundColor(getThemedColor(r4));
        this.sharedMediaLayout.updateStoriesList(this.storiesList);
        FrameLayout r05 = new FrameLayout(r28);
        this.sharedMediaLayoutContainer = r05;
        r05.setBackgroundColor(getThemedColor(r4));
        this.sharedMediaLayoutContainer.addView(this.sharedMediaLayout, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 49.0f));
        FrameLayout r06 = new FrameLayout(r28);
        this.storiesTotal = r06;
        r06.setBackgroundColor(getThemedColor(r4));
        TextView r07 = new TextView(r28);
        this.storiesTotalTextView = r07;
        r07.setTypeface(AndroidUtilities.bold());
        this.storiesTotalTextView.setTextSize(1, 15.0f);
        this.storiesTotalTextView.setTextColor(getThemedColor(Theme.key_chat_searchPanelText));
        this.storiesTotalTextView.setText(LocaleController.formatPluralString("FoundStories", this.storiesList.getCount(), new Object[0]));
        this.storiesTotal.addView(this.storiesTotalTextView, LayoutHelper.createFrame(-1, -2.0f, 19, 18.0f, 0.0f, 18.0f, 0.0f));
        View r08 = new View(r28);
        r08.setBackgroundColor(Theme.getColor(Theme.key_divider, this.resourceProvider));
        this.storiesTotal.addView(r08, LayoutHelper.createFrame(-1.0f, 1.0f / AndroidUtilities.density, 55));
        this.sharedMediaLayoutContainer.addView(this.storiesTotal, LayoutHelper.createFrame(-1, 49, 87));
        this.contentView.addView(this.sharedMediaLayoutContainer, LayoutHelper.createFrame(-1, -1, 119));
        MessagesSearchAdapter.StoriesView r09 = new MessagesSearchAdapter.StoriesView(r28, this.resourceProvider);
        this.storiesView = r09;
        r09.setBackground(Theme.createSelectorWithBackgroundDrawable(getThemedColor(r4), getThemedColor(Theme.key_listSelector)));
        this.storiesView.setOnClickListener(new HashtagActivity$$ExternalSyntheticLambda1(this));
        updateStoriesVisible(this.storiesView.set(this.storiesList), false);
        this.storiesView.setMessages(HashtagSearchController.getInstance(this.currentAccount).getCount(3), this.hashtag, this.username);
        r03.addView(this.storiesView, LayoutHelper.createFrame(-1, 48, 55));
        transit(false, false);
        return this.fragmentView;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 != NotificationCenter.storiesListUpdated) goto L14;
        Object r32 = r5[0];
        StoriesController.SearchStoriesList r42 = this.storiesList;
        if (r32 != r42) goto L27;
        MessagesSearchAdapter.StoriesView r33 = this.storiesView;
        if (r33 == null) goto L9;
        updateStoriesVisible(r33.set(r42), true);
    L9:
        TextView r34 = this.storiesTotalTextView;
        if (r34 == null) goto L28;
        r34.setText(LocaleController.formatPluralString("FoundStories", this.storiesList.getCount(), new Object[0]));
        return;
    L28:
        return;
    L27:
        return;
    L14:
        if (r3 != NotificationCenter.hashtagSearchUpdated) goto L29;
        ChatActivityContainer r35 = this.chatContainer;
        if (r35 != null) goto L18;
        return;
    L18:
        if (r35.chatActivity != null) goto L21;
        return;
    L21:
        if (((Integer) r5[0]).intValue() != this.chatContainer.chatActivity.getClassGuid()) goto L33;
        int r36 = ((Integer) r5[1]).intValue();
        MessagesSearchAdapter.StoriesView r43 = this.storiesView;
        if (r43 == null) goto L31;
        r43.setMessages(r36, this.hashtag, this.username);
        return;
    L31:
        return;
    L33:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (ColorUtils.calculateLuminance(Theme.getColor(Theme.key_windowBackgroundWhite, null, true)) <= 0.699999988079071d) goto L5;
        return true;
    L5:
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        getMessagesController().getStoriesController().attachedSearchLists.add(this.storiesList);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.hashtagSearchUpdated);
        this.storiesList.load(true, 18);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        getMessagesController().getStoriesController().attachedSearchLists.remove(this.storiesList);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.storiesListUpdated);
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.hashtagSearchUpdated);
        super.onFragmentDestroy();
    }
}
