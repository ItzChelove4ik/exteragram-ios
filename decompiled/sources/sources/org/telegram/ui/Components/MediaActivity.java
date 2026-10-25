package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Property;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BotWebViewVibrationEffect;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.ActionBarMenu;
import org.telegram.ui.ActionBar.ActionBarMenuItem;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BackDrawable;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ActionBar.ThemeDescription;
import org.telegram.ui.Components.BottomPagerTabs;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugController;
import org.telegram.ui.Components.FloatingDebug.FloatingDebugProvider;
import org.telegram.ui.Components.Paint.ShapeDetector;
import org.telegram.ui.Components.SharedMediaLayout;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.ProfileActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class MediaActivity extends BaseFragment implements SharedMediaLayout.SharedMediaPreloaderDelegate, FloatingDebugProvider, NotificationCenter.NotificationCenterDelegate {
    private SparseArray<MessageObject> actionModeMessageObjects;
    private Runnable applyBulletin;
    ProfileActivity.AvatarImageView avatarImageView;
    private BackDrawable backDrawable;
    private ButtonWithCounterView button;
    private FrameLayout buttonContainer;
    private ActionBarMenuSubItem calendarItem;
    private TLRPC.ChatFull currentChatInfo;
    private TLRPC.UserFull currentUserInfo;
    private ActionBarMenuItem deleteItem;
    private long dialogId;
    private boolean filterPhotos;
    private boolean filterVideos;
    private final boolean[] firstSubtitleCheck;
    private String hashtag;
    private int initialTab;
    private int lastTab;
    private SimpleTextView[] nameTextView;
    private ActionBarMenuItem optionsItem;
    private AnimatedTextView selectedTextView;
    SharedMediaLayout sharedMediaLayout;
    private SharedMediaLayout.SharedMediaPreloader sharedMediaPreloader;
    private int shiftDp;
    private ActionBarMenuSubItem showPhotosItem;
    private ActionBarMenuSubItem showVideosItem;
    private int storiesCount;
    private final ValueAnimator[] subtitleAnimator;
    private final boolean[] subtitleShown;
    private final float[] subtitleT;
    private AnimatedTextView[] subtitleTextView;
    private StoriesTabsView tabsView;
    private FrameLayout[] titles;
    private FrameLayout titlesContainer;
    private long topicId;
    private int type;
    private String username;
    private ActionBarMenuSubItem zoomInItem;
    private ActionBarMenuSubItem zoomOutItem;

    /* renamed from: org.telegram.ui.Components.MediaActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ MediaActivity this$0;

        /* renamed from: $r8$lambda$O-QwGuzwO7A-KH93F1hskwXQAFg, reason: not valid java name */
        public static /* synthetic */ void m11234$r8$lambda$OQwGuzwO7AKH93F1hskwXQAFg(AnonymousClass1 r0, ArrayList r1, AlertDialog r2, int r3) {
            r0.lambda$onItemClick$0(r1, r2, r3);
        }

        public static /* synthetic */ void $r8$lambda$jQ1a7WZCdcinAz31UDKj0dRGXag(AlertDialog r0, int r1) {
            r0.dismiss();
        }

        public AnonymousClass1(MediaActivity r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onItemClick$0(ArrayList r3, AlertDialog r4, int r5) {
            this.this$0.getMessagesController().getStoriesController().deleteStories(MediaActivity.m11218$$Nest$fgetdialogId(this.this$0), r3);
            this.this$0.sharedMediaLayout.closeActionMode(false);
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r6) {
            if (r6 != (-1)) goto L10;
            if (this.this$0.sharedMediaLayout.closeActionMode(true) == true) goto L43;
            this.this$0.finishFragment();
            return;
        L43:
            return;
        L10:
            if (r6 != 2) goto L30;
            if (MediaActivity.m11213$$Nest$fgetactionModeMessageObjects(this.this$0) == null) goto L40;
            ArrayList r62 = new ArrayList();
            int r0 = 0;
        L15:
            if (r0 >= MediaActivity.m11213$$Nest$fgetactionModeMessageObjects(this.this$0).size()) goto L21;
            TL_stories.StoryItem r3 = ((MessageObject) MediaActivity.m11213$$Nest$fgetactionModeMessageObjects(this.this$0).valueAt(r0)).storyItem;
            if (r3 == null) goto L19;
            r62.add(r3);
        L19:
            r0 = r0 + 1;
            goto L15
        L21:
            if (r62.isEmpty() == true) goto L41;
            AlertDialog.Builder r02 = new AlertDialog.Builder(this.this$0.getContext(), this.this$0.getResourceProvider());
            if (r62.size() <= 1) goto L26;
            int r1 = R.string.DeleteStoriesTitle;
        L27:
            r02.setTitle(LocaleController.getString(r1));
            r02.setMessage(LocaleController.formatPluralString("DeleteStoriesSubtitle", r62.size(), new Object[0]));
            r02.setPositiveButton(LocaleController.getString(R.string.Delete), new MediaActivity$1$$ExternalSyntheticLambda0(this, r62));
            r02.setNegativeButton(LocaleController.getString(R.string.Cancel), new MediaActivity$1$$ExternalSyntheticLambda1());
            AlertDialog r5 = r02.create();
            r5.show();
            r5.redPositive();
            return;
        L26:
            r1 = R.string.DeleteStoryTitle;
            goto L27
        L41:
            return;
        L40:
            return;
        L30:
            if (r6 != 10) goto L34;
            SharedMediaLayout r52 = this.this$0.sharedMediaLayout;
            r52.showMediaCalendar(r52.getClosestTab(), false);
            return;
        L34:
            if (r6 != 11) goto L42;
            this.this$0.sharedMediaLayout.closeActionMode(true);
            this.this$0.sharedMediaLayout.getSearchItem().openSearch(false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        final /* synthetic */ MediaActivity this$0;
        final /* synthetic */ FrameLayout val$avatarContainer;

        public AnonymousClass2(MediaActivity r1, Context r2, FrameLayout r3) {
            this.this$0 = r1;
            this.val$avatarContainer = r3;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r2) {
            SharedMediaLayout r0 = this.this$0.sharedMediaLayout;
            if (r0 != null) goto L5;
        L8:
            SharedMediaLayout r02 = this.this$0.sharedMediaLayout;
            if (r02 == null) goto L15;
            if (r02.checkPinchToZoom(r2) == false) goto L15;
            return true;
        L15:
            return super.dispatchTouchEvent(r2);
        L5:
            if (r0.isInFastScroll() == false) goto L8;
            return this.this$0.sharedMediaLayout.dispatchFastScrollEvent(r2);
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
        public void drawList(Canvas r1, boolean r2, ArrayList<SizeNotifierFrameLayout.IViewWithInvalidateCallback> r3) {
            this.this$0.sharedMediaLayout.drawListForBlur(r1, r3);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            FrameLayout.LayoutParams r0 = (FrameLayout.LayoutParams) this.this$0.sharedMediaLayout.getLayoutParams();
            int r1 = ActionBar.getCurrentActionBarHeight();
            int r3 = 0;
            if (MediaActivity.access$000(this.this$0).getOccupyStatusBar() == false) goto L5;
            int r2 = AndroidUtilities.statusBarHeight;
        L6:
            r0.topMargin = r1 + r2;
            FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) this.val$avatarContainer.getLayoutParams();
            if (MediaActivity.access$100(this.this$0).getOccupyStatusBar() == false) goto L9;
            int r12 = AndroidUtilities.statusBarHeight;
        L10:
            r02.topMargin = r12;
            r02.height = ActionBar.getCurrentActionBarHeight();
        L11:
            MediaActivity r03 = this.this$0;
            if (r3 >= 2) goto L26;
            if (MediaActivity.m11221$$Nest$fgetnameTextView(r03)[r3] == null) goto L23;
            int r04 = ((ActionBar.getCurrentActionBarHeight() / 2) - AndroidUtilities.dp(22.0f)) / 2;
            if (AndroidUtilities.isTablet() == false) goto L18;
        L20:
            float r22 = 5.0f;
        L21:
            ((FrameLayout.LayoutParams) MediaActivity.m11221$$Nest$fgetnameTextView(this.this$0)[r3].getLayoutParams()).topMargin = r04 + AndroidUtilities.dp(r22);
            goto L23
        L18:
            if (getResources().getConfiguration().orientation != 2) goto L20;
            r22 = 4.0f;
        L23:
            if (MediaActivity.m11225$$Nest$fgetsubtitleTextView(this.this$0)[r3] == null) goto L25;
            ((FrameLayout.LayoutParams) MediaActivity.m11225$$Nest$fgetsubtitleTextView(this.this$0)[r3].getLayoutParams()).topMargin = ((ActionBar.getCurrentActionBarHeight() / 2) + (((ActionBar.getCurrentActionBarHeight() / 2) - AndroidUtilities.dp(19.0f)) / 2)) - AndroidUtilities.dp(7.0f);
        L25:
            r3 = r3 + 1;
            goto L11
        L26:
            ((FrameLayout.LayoutParams) r03.avatarImageView.getLayoutParams()).topMargin = (ActionBar.getCurrentActionBarHeight() - AndroidUtilities.dp(42.0f)) / 2;
            super.onMeasure(r6, r7);
            return;
        L9:
            r12 = 0;
            goto L10
        L5:
            r2 = 0;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends ProfileActivity.AvatarImageView {
        final /* synthetic */ MediaActivity this$0;

        public AnonymousClass3(MediaActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            if (getImageReceiver().hasNotThumb() == false) goto L6;
            r3.setText(LocaleController.getString(R.string.AccDescrProfilePicture));
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.Open)));
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(32, LocaleController.getString(R.string.AccDescrOpenInPhotoViewer)));
            return;
        L6:
            r3.setVisibleToUser(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$4, reason: invalid class name */
    public class AnonymousClass4 implements Bulletin.Delegate {
        final /* synthetic */ MediaActivity this$0;

        public AnonymousClass4(MediaActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.Bulletin.Delegate
        public int getBottomOffset(int r1) {
            return AndroidUtilities.dp(64.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$5, reason: invalid class name */
    public class AnonymousClass5 implements SharedMediaLayout.Delegate {
        final /* synthetic */ MediaActivity this$0;

        public AnonymousClass5(MediaActivity r1) {
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
            MediaActivity.m11233$$Nest$mupdateMediaCount(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends SharedMediaLayout {
        private AnimatorSet actionModeAnimation;
        final /* synthetic */ MediaActivity this$0;
        final /* synthetic */ FrameLayout val$avatarContainer;
        final /* synthetic */ SizeNotifierFrameLayout val$fragmentView;

        /* renamed from: org.telegram.ui.Components.MediaActivity$6$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ AnonymousClass6 this$1;
            final /* synthetic */ boolean val$empty;
            final /* synthetic */ boolean val$show;

            public AnonymousClass1(AnonymousClass6 r1, boolean r2, boolean r3) {
                this.this$1 = r1;
                this.val$show = r2;
                this.val$empty = r3;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationCancel(Animator r1) {
                AnonymousClass6.m11236$$Nest$fputactionModeAnimation(this.this$1, null);
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r4) {
                if (AnonymousClass6.m11235$$Nest$fgetactionModeAnimation(this.this$1) == null) goto L26;
                AnonymousClass6.m11236$$Nest$fputactionModeAnimation(this.this$1, null);
                boolean r42 = this.val$show;
                AnonymousClass6 r0 = this.this$1;
                if (r42 == true) goto L19;
                MediaActivity.m11223$$Nest$fgetselectedTextView(r0.this$0).setVisibility(4);
                if (MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$1.this$0) == null) goto L11;
                MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$1.this$0).setVisibility(4);
            L11:
                if (MediaActivity.m11217$$Nest$fgetdeleteItem(this.this$1.this$0) == null) goto L14;
                MediaActivity.m11217$$Nest$fgetdeleteItem(this.this$1.this$0).setVisibility(8);
            L14:
                if (this.val$empty == true) goto L16;
                return;
            L16:
                if (MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$1.this$0) == null) goto L24;
                MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$1.this$0).setVisibility(8);
                return;
            L24:
                return;
            L19:
                MediaActivity.m11228$$Nest$fgettitlesContainer(r0.this$0).setVisibility(4);
                if (MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$1.this$0) == null) goto L25;
                MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$1.this$0).setVisibility(8);
                return;
            L25:
                return;
            }
        }

        /* renamed from: -$$Nest$fgetactionModeAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ AnimatorSet m11235$$Nest$fgetactionModeAnimation(AnonymousClass6 r0) {
            return r0.actionModeAnimation;
        }

        /* renamed from: -$$Nest$fputactionModeAnimation, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11236$$Nest$fputactionModeAnimation(AnonymousClass6 r0, AnimatorSet r1) {
            r0.actionModeAnimation = r1;
        }

        public AnonymousClass6(MediaActivity r16, Context r17, long r18, SharedMediaLayout.SharedMediaPreloader r20, int r21, ArrayList r22, TLRPC.ChatFull r23, TLRPC.UserFull r24, int r25, int r26, BaseFragment r27, SharedMediaLayout.Delegate r28, int r29, Theme.ResourcesProvider r30, FrameLayout r31, SizeNotifierFrameLayout r32) {
            this.this$0 = r16;
            this.val$avatarContainer = r31;
            this.val$fragmentView = r32;
            super(r17, r18, r20, r21, r22, r23, r24, r25, r26, r27, r28, r29, r30);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean canShowSearchItem() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L5;
            return false;
        L5:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 2) goto L9;
            return true;
        L9:
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean customTabs() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L5;
        L11:
            return true;
        L5:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 2) goto L11;
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 3) goto L11;
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void drawBackgroundWithBlur(Canvas r7, float r8, Rect r9, Paint r10) {
            this.val$fragmentView.drawBlurRect(r7, getY() + r8, r9, r10, true);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public int getInitialTab() {
            return MediaActivity.m11220$$Nest$fgetinitialTab(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public String getStoriesHashtag() {
            return MediaActivity.m11219$$Nest$fgethashtag(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public String getStoriesHashtagUsername() {
            return MediaActivity.m11231$$Nest$fgetusername(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean includeSavedDialogs() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 0) goto L5;
            return false;
        L5:
            if (MediaActivity.m11218$$Nest$fgetdialogId(this.this$0) == this.this$0.getUserConfig().getClientUserId()) goto L7;
            return false;
        L7:
            if (MediaActivity.m11229$$Nest$fgettopicId(this.this$0) != 0) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean includeStories() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L5;
        L9:
            return true;
        L5:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 2) goto L9;
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void invalidateBlur() {
            this.val$fragmentView.invalidateBlur();
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean isArchivedOnlyStoriesView() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 2) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public boolean isStoriesView() {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L5;
        L9:
            return true;
        L5:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 2) goto L9;
            return false;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onActionModeSelectedUpdate(SparseArray<MessageObject> r6) {
            int r0 = r6.size();
            MediaActivity.m11232$$Nest$fputactionModeMessageObjects(this.this$0, r6);
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L5;
        L6:
            MediaActivity.m11223$$Nest$fgetselectedTextView(this.this$0).cancelAnimation();
            MediaActivity.m11223$$Nest$fgetselectedTextView(this.this$0).setText(LocaleController.formatPluralString("StoriesSelected", r0, new Object[0]), !LocaleController.isRTL);
            if (MediaActivity.m11215$$Nest$fgetbutton(this.this$0) == null) goto L17;
            ButtonWithCounterView r62 = MediaActivity.m11215$$Nest$fgetbutton(this.this$0);
            if (r0 <= 0) goto L11;
            boolean r2 = true;
        L12:
            r62.setEnabled(r2);
            MediaActivity.m11215$$Nest$fgetbutton(this.this$0).setCount(r0, true);
            if (this.this$0.sharedMediaLayout.getClosestTab() != 8) goto L18;
            MediaActivity.m11215$$Nest$fgetbutton(this.this$0).setText(LocaleController.formatPluralString("ArchiveStories", r0, new Object[0]), true);
            return;
        L18:
            return;
        L11:
            r2 = false;
            goto L12
        L17:
            return;
        L5:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 2) goto L6;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onSearchStateChanged(boolean r3) {
            AndroidUtilities.removeAdjustResize(this.this$0.getParentActivity(), MediaActivity.access$200(this.this$0));
            AndroidUtilities.updateViewVisibilityAnimated(this.val$avatarContainer, !r3, 0.95f, true);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onSelectedTabChanged() {
            super.onSelectedTabChanged();
            MediaActivity.m11233$$Nest$mupdateMediaCount(this.this$0);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onTabProgress(float r5) {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) == 1) goto L5;
            return;
        L5:
            float r52 = r5 - 8.0f;
            if (MediaActivity.m11226$$Nest$fgettabsView(this.this$0) == null) goto L8;
            MediaActivity.m11226$$Nest$fgettabsView(this.this$0).setProgress(r52);
        L8:
            float r3 = 1.0f - r52;
            MediaActivity.m11227$$Nest$fgettitles(this.this$0)[0].setAlpha(r3);
            MediaActivity.m11227$$Nest$fgettitles(this.this$0)[0].setTranslationX(AndroidUtilities.dp(-12.0f) * r52);
            MediaActivity.m11227$$Nest$fgettitles(this.this$0)[1].setAlpha(r52);
            MediaActivity.m11227$$Nest$fgettitles(this.this$0)[1].setTranslationX(AndroidUtilities.dp(12.0f) * r3);
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void onTabScroll(boolean r2) {
            if (MediaActivity.m11226$$Nest$fgettabsView(this.this$0) == null) goto L6;
            MediaActivity.m11226$$Nest$fgettabsView(this.this$0).setScrolling(r2);
            return;
        }

        @Override // org.telegram.ui.Components.SharedMediaLayout
        public void showActionMode(boolean r10) {
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 0) goto L7;
            super.showActionMode(r10);
            return;
        L7:
            if (this.isActionModeShowed != r10) goto L9;
            return;
        L9:
            this.isActionModeShowed = r10;
            AnimatorSet r0 = this.actionModeAnimation;
            if (r0 == null) goto L13;
            r0.cancel();
        L13:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 1) goto L15;
        L16:
            disableScroll(r10);
        L17:
            MediaActivity r02 = this.this$0;
            if (r10 == false) goto L22;
            MediaActivity.m11223$$Nest$fgetselectedTextView(r02).setVisibility(0);
            if (MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0) == null) goto L23;
            MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0).setVisibility(0);
        L23:
            BackDrawable r03 = MediaActivity.m11214$$Nest$fgetbackDrawable(this.this$0);
            float r3 = 0.0f;
            float r4 = 1.0f;
            if (r10 == false) goto L26;
            float r5 = 1.0f;
        L27:
            r03.setRotation(r5, true);
            this.actionModeAnimation = new AnimatorSet();
            ArrayList r04 = new ArrayList();
            AnimatedTextView r52 = MediaActivity.m11223$$Nest$fgetselectedTextView(this.this$0);
            if (r10 == false) goto L30;
            float r6 = 1.0f;
        L31:
            float[] r7 = {r6};
            Property r62 = View.ALPHA;
            r04.add(ObjectAnimator.ofFloat(r52, r62, r7));
            FrameLayout r53 = MediaActivity.m11228$$Nest$fgettitlesContainer(this.this$0);
            if (r10 == false) goto L34;
            float r72 = 0.0f;
        L35:
            r04.add(ObjectAnimator.ofFloat(r53, r62, new float[]{r72}));
            if (MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0) == null) goto L47;
            FrameLayout r54 = MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0);
            if (r10 == false) goto L40;
            float r73 = 1.0f;
        L41:
            r04.add(ObjectAnimator.ofFloat(r54, r62, new float[]{r73}));
            FrameLayout r55 = MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0);
            if (r10 == false) goto L44;
            float r74 = 0.0f;
        L45:
            r04.add(ObjectAnimator.ofFloat(r55, View.TRANSLATION_Y, new float[]{r74}));
            goto L47
        L44:
            r74 = MediaActivity.m11216$$Nest$fgetbuttonContainer(this.this$0).getMeasuredHeight();
            goto L45
        L40:
            r73 = 0.0f;
        L47:
            if (MediaActivity.m11217$$Nest$fgetdeleteItem(this.this$0) == null) goto L54;
            MediaActivity.m11217$$Nest$fgetdeleteItem(this.this$0).setVisibility(0);
            ActionBarMenuItem r56 = MediaActivity.m11217$$Nest$fgetdeleteItem(this.this$0);
            if (r10 == false) goto L51;
            float r75 = 1.0f;
        L52:
            r04.add(ObjectAnimator.ofFloat(r56, r62, new float[]{r75}));
            goto L54
        L51:
            r75 = 0.0f;
        L54:
            if (getStoriesCount(getClosestTab()) != 0) goto L56;
            boolean r57 = true;
        L58:
            if (MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$0) == null) goto L66;
            MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$0).setVisibility(0);
            ActionBarMenuItem r76 = MediaActivity.m11222$$Nest$fgetoptionsItem(this.this$0);
            if (r10 == true) goto L64;
            if (r57 == true) goto L64;
            r3 = 1.0f;
        L64:
            r04.add(ObjectAnimator.ofFloat(r76, r62, new float[]{r3}));
        L66:
            if (MediaActivity.m11226$$Nest$fgettabsView(this.this$0) == null) goto L71;
            StoriesTabsView r32 = MediaActivity.m11226$$Nest$fgettabsView(this.this$0);
            if (r10 == false) goto L70;
            r4 = 0.4f;
        L70:
            r04.add(ObjectAnimator.ofFloat(r32, r62, new float[]{r4}));
        L71:
            this.actionModeAnimation.playTogether(r04);
            this.actionModeAnimation.setDuration(300);
            this.actionModeAnimation.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.actionModeAnimation.addListener(new AnonymousClass1(this, r10, r57));
            this.actionModeAnimation.start();
            return;
        L56:
            r57 = false;
            goto L58
        L34:
            r72 = 1.0f;
            goto L35
        L30:
            r6 = 0.0f;
            goto L31
        L26:
            r5 = 0.0f;
            goto L27
        L22:
            MediaActivity.m11228$$Nest$fgettitlesContainer(r02).setVisibility(0);
            goto L23
        L15:
            if (MediaActivity.m11230$$Nest$fgettype(this.this$0) != 2) goto L17;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.Components.MediaActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ MediaActivity this$0;
        final /* synthetic */ int val$i;
        final /* synthetic */ boolean val$show;

        public AnonymousClass7(MediaActivity r1, int r2, boolean r3) {
            this.this$0 = r1;
            this.val$i = r2;
            this.val$show = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            float[] r52 = MediaActivity.m11224$$Nest$fgetsubtitleT(this.this$0);
            int r0 = this.val$i;
            float r2 = 0.0f;
            if (this.val$show == false) goto L5;
            float r1 = 1.0f;
        L6:
            r52[r0] = r1;
            SimpleTextView r53 = MediaActivity.m11221$$Nest$fgetnameTextView(this.this$0)[this.val$i];
            float r12 = 1.111f;
            if (this.val$show == false) goto L9;
            float r02 = 1.0f;
        L10:
            r53.setScaleX(r02);
            SimpleTextView r54 = MediaActivity.m11221$$Nest$fgetnameTextView(this.this$0)[this.val$i];
            if (this.val$show == false) goto L13;
            r12 = 1.0f;
        L13:
            r54.setScaleY(r12);
            SimpleTextView r55 = MediaActivity.m11221$$Nest$fgetnameTextView(this.this$0)[this.val$i];
            if (this.val$show == false) goto L16;
            float r03 = 0.0f;
        L17:
            r55.setTranslationY(r03);
            AnimatedTextView r56 = MediaActivity.m11225$$Nest$fgetsubtitleTextView(this.this$0)[this.val$i];
            if (this.val$show == false) goto L20;
            r2 = 1.0f;
        L20:
            r56.setAlpha(r2);
            if (this.val$show == true) goto L24;
            MediaActivity.m11225$$Nest$fgetsubtitleTextView(this.this$0)[this.val$i].setVisibility(8);
            return;
        L24:
            return;
        L16:
            r03 = AndroidUtilities.dp(8.0f);
            goto L17
        L9:
            r02 = 1.111f;
            goto L10
        L5:
            r1 = 0.0f;
            goto L6
        }
    }

    public class StoriesTabsView extends BottomPagerTabs {
        final /* synthetic */ MediaActivity this$0;

        public StoriesTabsView(MediaActivity r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.BottomPagerTabs
        public BottomPagerTabs.Tab[] createTabs() {
            return new BottomPagerTabs.Tab[]{new BottomPagerTabs.Tab(this, 0, R.raw.msg_stories_saved, 20, 40, LocaleController.getString(R.string.ProfileMyStoriesTab)), new BottomPagerTabs.Tab(this, 1, R.raw.msg_stories_archive, 0, 0, LocaleController.getString(R.string.ProfileStoriesArchiveTab))};
        }
    }

    /* renamed from: $r8$lambda$4l_xz5AL-fwe_NUngDmaVhl82kQ, reason: not valid java name */
    public static /* synthetic */ void m11208$r8$lambda$4l_xz5ALfwe_NUngDmaVhl82kQ(MediaActivity r0) {
        r0.lambda$onGetDebugItems$13();
    }

    public static /* synthetic */ void $r8$lambda$67vsUI76zgNeYuB4RHtREAk59bs(MediaActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$8ZRQSRvkhLrayUg3fXLppEI4-jo, reason: not valid java name */
    public static /* synthetic */ void m11209$r8$lambda$8ZRQSRvkhLrayUg3fXLppEI4jo(MediaActivity r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$BUohxziEJyKdfL0OHc-8NmbYeow, reason: not valid java name */
    public static /* synthetic */ void m11210$r8$lambda$BUohxziEJyKdfL0OHc8NmbYeow(MediaActivity r0, boolean[] r1, ArrayList r2, boolean[] r3) {
        r0.lambda$createView$8(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$QpVqzrdLOSJ5GGQCFY5iftTNMUA(MediaActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    /* renamed from: $r8$lambda$VrKAUqPrZH1wm-n3QlwKL9cRiDE, reason: not valid java name */
    public static /* synthetic */ void m11211$r8$lambda$VrKAUqPrZH1wmn3QlwKL9cRiDE(MediaActivity r0, boolean r1) {
        r0.lambda$updateMediaCount$11(r1);
    }

    public static /* synthetic */ void $r8$lambda$_y951ZqTyR0aMa4wDboFqgmISWw(MediaActivity r0) {
        r0.updateColors();
    }

    public static /* synthetic */ void $r8$lambda$bBDJ35z4wScUwyCMIynX2hirTbo(MediaActivity r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$g6C_qBBtPF15VbM3KvLEz_7EwcE(MediaActivity r0, Integer r1) {
        r0.lambda$createView$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$gJZDJXoLdiKsXl6GV5sJC_W14Os(MediaActivity r0, boolean[] r1) {
        r0.lambda$createView$9(r1);
    }

    public static /* synthetic */ void $r8$lambda$gqogU6cwFbSh2vWx2dkzQ8KMlIM(MediaActivity r0, View r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$kabw8wEYR7gUCxVSMs5YazTmbQI(ActionBarMenu r0, View r1) {
        r0.onItemClick(2);
    }

    /* renamed from: $r8$lambda$l63v-_q7kiWSlB55k9yVwZFIUdA, reason: not valid java name */
    public static /* synthetic */ void m11212$r8$lambda$l63v_q7kiWSlB55k9yVwZFIUdA(MediaActivity r0, ArrayList r1, boolean r2) {
        r0.lambda$createView$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$sjmXC2rl8H1GA3f0IesTHonz4VU(MediaActivity r0, int r1, ValueAnimator r2) {
        r0.lambda$showSubtitle$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$zny6PXedFfnqdW0QJ5HuBpGF88s(MediaActivity r0, View r1) {
        r0.lambda$createView$10(r1);
    }

    /* renamed from: -$$Nest$fgetactionModeMessageObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SparseArray m11213$$Nest$fgetactionModeMessageObjects(MediaActivity r0) {
        return r0.actionModeMessageObjects;
    }

    /* renamed from: -$$Nest$fgetbackDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BackDrawable m11214$$Nest$fgetbackDrawable(MediaActivity r0) {
        return r0.backDrawable;
    }

    /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ButtonWithCounterView m11215$$Nest$fgetbutton(MediaActivity r0) {
        return r0.button;
    }

    /* renamed from: -$$Nest$fgetbuttonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11216$$Nest$fgetbuttonContainer(MediaActivity r0) {
        return r0.buttonContainer;
    }

    /* renamed from: -$$Nest$fgetdeleteItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m11217$$Nest$fgetdeleteItem(MediaActivity r0) {
        return r0.deleteItem;
    }

    /* renamed from: -$$Nest$fgetdialogId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11218$$Nest$fgetdialogId(MediaActivity r2) {
        return r2.dialogId;
    }

    /* renamed from: -$$Nest$fgethashtag, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11219$$Nest$fgethashtag(MediaActivity r0) {
        return r0.hashtag;
    }

    /* renamed from: -$$Nest$fgetinitialTab, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11220$$Nest$fgetinitialTab(MediaActivity r0) {
        return r0.initialTab;
    }

    /* renamed from: -$$Nest$fgetnameTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SimpleTextView[] m11221$$Nest$fgetnameTextView(MediaActivity r0) {
        return r0.nameTextView;
    }

    /* renamed from: -$$Nest$fgetoptionsItem, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionBarMenuItem m11222$$Nest$fgetoptionsItem(MediaActivity r0) {
        return r0.optionsItem;
    }

    /* renamed from: -$$Nest$fgetselectedTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView m11223$$Nest$fgetselectedTextView(MediaActivity r0) {
        return r0.selectedTextView;
    }

    /* renamed from: -$$Nest$fgetsubtitleT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m11224$$Nest$fgetsubtitleT(MediaActivity r0) {
        return r0.subtitleT;
    }

    /* renamed from: -$$Nest$fgetsubtitleTextView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedTextView[] m11225$$Nest$fgetsubtitleTextView(MediaActivity r0) {
        return r0.subtitleTextView;
    }

    /* renamed from: -$$Nest$fgettabsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ StoriesTabsView m11226$$Nest$fgettabsView(MediaActivity r0) {
        return r0.tabsView;
    }

    /* renamed from: -$$Nest$fgettitles, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout[] m11227$$Nest$fgettitles(MediaActivity r0) {
        return r0.titles;
    }

    /* renamed from: -$$Nest$fgettitlesContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m11228$$Nest$fgettitlesContainer(MediaActivity r0) {
        return r0.titlesContainer;
    }

    /* renamed from: -$$Nest$fgettopicId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m11229$$Nest$fgettopicId(MediaActivity r2) {
        return r2.topicId;
    }

    /* renamed from: -$$Nest$fgettype, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11230$$Nest$fgettype(MediaActivity r0) {
        return r0.type;
    }

    /* renamed from: -$$Nest$fgetusername, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m11231$$Nest$fgetusername(MediaActivity r0) {
        return r0.username;
    }

    /* renamed from: -$$Nest$fputactionModeMessageObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11232$$Nest$fputactionModeMessageObjects(MediaActivity r0, SparseArray r1) {
        r0.actionModeMessageObjects = r1;
    }

    /* renamed from: -$$Nest$mupdateMediaCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m11233$$Nest$mupdateMediaCount(MediaActivity r0) {
        r0.updateMediaCount();
    }

    public MediaActivity(Bundle r2, SharedMediaLayout.SharedMediaPreloader r3) {
        super(r2);
        this.titles = new FrameLayout[2];
        this.nameTextView = new SimpleTextView[2];
        this.subtitleTextView = new AnimatedTextView[2];
        this.filterPhotos = true;
        this.filterVideos = true;
        this.shiftDp = -12;
        this.subtitleShown = new boolean[2];
        this.subtitleT = new float[2];
        this.firstSubtitleCheck = new boolean[]{true, true};
        this.subtitleAnimator = new ValueAnimator[2];
        this.sharedMediaPreloader = r3;
    }

    public static /* synthetic */ ActionBar access$000(MediaActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ ActionBar access$100(MediaActivity r0) {
        return r0.actionBar;
    }

    public static /* synthetic */ int access$200(MediaActivity r0) {
        return r0.classGuid;
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        this.optionsItem.toggleSubMenu();
    }

    private /* synthetic */ void lambda$createView$10(View r13) {
        Runnable r132 = this.applyBulletin;
        if (r132 == null) goto L5;
        r132.run();
        this.applyBulletin = null;
    L5:
        Bulletin.hideVisible();
        if (this.sharedMediaLayout.getClosestTab() != 9) goto L8;
        boolean r133 = true;
    L9:
        ArrayList r0 = new ArrayList();
        if (this.actionModeMessageObjects == null) goto L18;
        int r3 = 0;
        int r4 = 0;
    L13:
        if (r3 >= this.actionModeMessageObjects.size()) goto L19;
        TL_stories.StoryItem r5 = this.actionModeMessageObjects.valueAt(r3).storyItem;
        if (r5 == null) goto L17;
        r0.add(r5);
        r4 = r4 + 1;
    L17:
        r3 = r3 + 1;
    L19:
        this.sharedMediaLayout.closeActionMode(false);
        if (r133 == false) goto L23;
        this.sharedMediaLayout.scrollToPage(8);
    L23:
        if (r0.isEmpty() == false) goto L25;
        return;
    L25:
        boolean[] r32 = new boolean[r0.size()];
        int r52 = 0;
    L27:
        if (r52 >= r0.size()) goto L29;
        TL_stories.StoryItem r6 = (TL_stories.StoryItem) r0.get(r52);
        r32[r52] = r6.pinned;
        r6.pinned = r133;
        r52 = r52 + 1;
        goto L27
    L29:
        getMessagesController().getStoriesController().updateStoriesInLists(this.dialogId, r0);
        boolean[] r1 = {false};
        this.applyBulletin = new MediaActivity$$ExternalSyntheticLambda10(this, r0, r133);
        MediaActivity$$ExternalSyntheticLambda11 r11 = new MediaActivity$$ExternalSyntheticLambda11(this, r1, r0, r32);
        if (r133 == false) goto L32;
        Bulletin r134 = BulletinFactory.of(this).createSimpleBulletin(R.raw.contact_check, LocaleController.formatPluralString("StorySavedTitle", r4, new Object[0]), LocaleController.getString("StorySavedSubtitle"), LocaleController.getString("Undo"), r11).show();
    L33:
        r134.setOnHideListener(new MediaActivity$$ExternalSyntheticLambda12(this, r1));
        return;
    L32:
        r134 = BulletinFactory.of(this).createSimpleBulletin(R.raw.chats_archived, LocaleController.formatPluralString("StoryArchived", r4, new Object[0]), LocaleController.getString("Undo"), 5000, r11).show();
        goto L33
    L18:
        r4 = 0;
        goto L19
    L8:
        r133 = false;
        goto L9
    }

    private /* synthetic */ void lambda$createView$2(View r5) {
        Boolean r52 = this.sharedMediaLayout.zoomIn();
        if (r52 != null) goto L5;
        return;
    L5:
        boolean r53 = r52.booleanValue();
        this.zoomOutItem.setEnabled(true);
        ViewPropertyAnimator r0 = this.zoomOutItem.animate();
        float r2 = 0.5f;
        if (this.zoomOutItem.isEnabled() == false) goto L8;
        float r1 = 1.0f;
    L9:
        r0.alpha(r1).start();
        this.zoomInItem.setEnabled(r53);
        ViewPropertyAnimator r54 = this.zoomInItem.animate();
        if (this.zoomInItem.isEnabled() == false) goto L12;
        r2 = 1.0f;
    L12:
        r54.alpha(r2).start();
        return;
    L8:
        r1 = 0.5f;
        goto L9
    }

    private /* synthetic */ void lambda$createView$3(View r4) {
        Boolean r42 = this.sharedMediaLayout.zoomOut();
        if (r42 != null) goto L5;
        return;
    L5:
        this.zoomOutItem.setEnabled(r42.booleanValue());
        ViewPropertyAnimator r43 = this.zoomOutItem.animate();
        float r1 = 0.5f;
        if (this.zoomOutItem.isEnabled() == false) goto L8;
        float r0 = 1.0f;
    L9:
        r43.alpha(r0).start();
        this.zoomInItem.setEnabled(true);
        ViewPropertyAnimator r44 = this.zoomInItem.animate();
        if (this.zoomInItem.isEnabled() == false) goto L12;
        r1 = 1.0f;
    L12:
        r44.alpha(r1).start();
        return;
    L8:
        r0 = 0.5f;
        goto L9
    }

    private /* synthetic */ void lambda$createView$4(View r2) {
        boolean r22 = this.filterPhotos;
        if (r22 == true) goto L5;
    L8:
        ActionBarMenuSubItem r0 = this.showPhotosItem;
        boolean r23 = !r22;
        this.filterPhotos = r23;
        r0.setChecked(r23);
        this.sharedMediaLayout.setStoriesFilter(this.filterPhotos, this.filterVideos);
        return;
    L5:
        if (this.filterVideos == true) goto L8;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        ActionBarMenuSubItem r24 = this.showPhotosItem;
        int r02 = -this.shiftDp;
        this.shiftDp = r02;
        AndroidUtilities.shakeViewSpring(r24, r02);
    }

    private /* synthetic */ void lambda$createView$5(View r2) {
        boolean r22 = this.filterVideos;
        if (r22 == true) goto L5;
    L8:
        ActionBarMenuSubItem r0 = this.showVideosItem;
        boolean r23 = !r22;
        this.filterVideos = r23;
        r0.setChecked(r23);
        this.sharedMediaLayout.setStoriesFilter(this.filterPhotos, this.filterVideos);
        return;
    L5:
        if (this.filterPhotos == true) goto L8;
        BotWebViewVibrationEffect.APP_ERROR.vibrate();
        ActionBarMenuSubItem r24 = this.showVideosItem;
        int r02 = -this.shiftDp;
        this.shiftDp = r02;
        AndroidUtilities.shakeViewSpring(r24, r02);
    }

    private /* synthetic */ void lambda$createView$6(Integer r1) {
        this.sharedMediaLayout.scrollToPage(r1.intValue() + 8);
    }

    private /* synthetic */ void lambda$createView$7(ArrayList r8, boolean r9) {
        getMessagesController().getStoriesController().updateStoriesPinned(this.dialogId, r8, r9, null);
    }

    private /* synthetic */ void lambda$createView$8(boolean[] r3, ArrayList r4, boolean[] r5) {
        int r1 = 0;
        r3[0] = true;
        AndroidUtilities.cancelRunOnUIThread(this.applyBulletin);
    L4:
        if (r1 >= r4.size()) goto L6;
        TL_stories.StoryItem r32 = (TL_stories.StoryItem) r4.get(r1);
        r32.pinned = r5[r1];
        r1 = r1 + 1;
        goto L4
    L6:
        getMessagesController().getStoriesController().updateStoriesInLists(this.dialogId, r4);
    }

    private /* synthetic */ void lambda$createView$9(boolean[] r2) {
        if (r2[0] == true) goto L7;
        Runnable r22 = this.applyBulletin;
        if (r22 == null) goto L7;
        r22.run();
    L7:
        this.applyBulletin = null;
    }

    private /* synthetic */ void lambda$onGetDebugItems$13() {
        ShapeDetector.setLearning(getContext(), !ShapeDetector.isLearning(getContext()));
    }

    private /* synthetic */ void lambda$showSubtitle$12(int r4, ValueAnimator r5) {
        this.subtitleT[r4] = ((Float) r5.getAnimatedValue()).floatValue();
        this.nameTextView[r4].setScaleX(AndroidUtilities.lerp(1.111f, 1.0f, this.subtitleT[r4]));
        this.nameTextView[r4].setScaleY(AndroidUtilities.lerp(1.111f, 1.0f, this.subtitleT[r4]));
        this.nameTextView[r4].setTranslationY(AndroidUtilities.lerp(AndroidUtilities.dp(8.0f), 0, this.subtitleT[r4]));
        this.subtitleTextView[r4].setAlpha(this.subtitleT[r4]);
    }

    private /* synthetic */ void lambda$updateMediaCount$11(boolean r1) {
        if (r1 == false) goto L5;
        this.optionsItem.setVisibility(8);
        return;
    }

    private void showSubtitle(int r7, boolean r8, boolean r9) {
        int r0 = this.type;
        if (r0 != 3) goto L6;
        return;
    L6:
        if (r7 != 1) goto L9;
        if (r0 != 2) goto L9;
        return;
    L9:
        boolean[] r02 = this.subtitleShown;
        if (r02[r7] == r8) goto L12;
    L14:
        boolean[] r3 = this.firstSubtitleCheck;
        int r5 = 0;
        if (r3[r7] == true) goto L18;
        if (r9 == false) goto L18;
        boolean r92 = true;
    L19:
        r3[r7] = false;
        r02[r7] = r8;
        ValueAnimator r03 = this.subtitleAnimator[r7];
        if (r03 == null) goto L22;
        r03.cancel();
        this.subtitleAnimator[r7] = null;
    L22:
        float r04 = 0.0f;
        if (r92 == false) goto L29;
        this.subtitleTextView[r7].setVisibility(0);
        ValueAnimator[] r93 = this.subtitleAnimator;
        float r4 = this.subtitleT[r7];
        if (r8 == false) goto L27;
        r04 = 1.0f;
    L27:
        r93[r7] = ValueAnimator.ofFloat(new float[]{r4, r04});
        this.subtitleAnimator[r7].addUpdateListener(new MediaActivity$$ExternalSyntheticLambda13(this, r7));
        this.subtitleAnimator[r7].addListener(new AnonymousClass7(this, r7, r8));
        this.subtitleAnimator[r7].setDuration(320);
        this.subtitleAnimator[r7].setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.subtitleAnimator[r7].start();
        return;
    L29:
        float[] r94 = this.subtitleT;
        if (r8 == false) goto L32;
        float r1 = 1.0f;
    L33:
        r94[r7] = r1;
        SimpleTextView r95 = this.nameTextView[r7];
        float r12 = 1.111f;
        if (r8 == false) goto L36;
        float r2 = 1.0f;
    L37:
        r95.setScaleX(r2);
        SimpleTextView r96 = this.nameTextView[r7];
        if (r8 == false) goto L40;
        r12 = 1.0f;
    L40:
        r96.setScaleY(r12);
        SimpleTextView r97 = this.nameTextView[r7];
        if (r8 == false) goto L43;
        float r13 = 0.0f;
    L44:
        r97.setTranslationY(r13);
        AnimatedTextView r98 = this.subtitleTextView[r7];
        if (r8 == false) goto L47;
        r04 = 1.0f;
    L47:
        r98.setAlpha(r04);
        AnimatedTextView r6 = this.subtitleTextView[r7];
        if (r8 == true) goto L51;
        r5 = 8;
    L51:
        r6.setVisibility(r5);
        return;
    L43:
        r13 = AndroidUtilities.dp(8.0f);
        goto L44
    L36:
        r2 = 1.111f;
        goto L37
    L32:
        r1 = 0.0f;
    L18:
        r92 = false;
        goto L19
    L12:
        if (this.firstSubtitleCheck[r7] == true) goto L14;
    }

    private void updateColors() {
        if (this.sharedMediaLayout.getSearchOptionsItem() == null) goto L5;
        this.sharedMediaLayout.getSearchOptionsItem().setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_windowBackgroundWhiteBlackText), PorterDuff.Mode.SRC_IN));
    L5:
        this.actionBar.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        ActionBar r0 = this.actionBar;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r0.setItemsColor(Theme.getColor(r1), false);
        this.actionBar.setItemsColor(Theme.getColor(r1), true);
        this.actionBar.setItemsBackgroundColor(Theme.getColor(Theme.key_actionBarActionModeDefaultSelector), false);
        this.actionBar.setTitleColor(Theme.getColor(r1));
        SimpleTextView r02 = this.nameTextView[0];
        if (r02 == null) goto L8;
        r02.setTextColor(Theme.getColor(r1));
    L8:
        SimpleTextView r5 = this.nameTextView[1];
        if (r5 == null) goto L12;
        r5.setTextColor(Theme.getColor(r1));
        return;
    }

    private void updateMediaCount() {
        SharedMediaLayout r0 = this.sharedMediaLayout;
        if (r0 == null) goto L135;
        boolean r2 = false;
        if (this.subtitleTextView[0] == null) goto L139;
        int r02 = r0.getClosestTab();
        if (this.type != 3) goto L11;
        if (r02 == 8) goto L11;
        return;
    L11:
        int[] r1 = this.sharedMediaPreloader.getLastMediaCount();
        boolean r5 = LocaleController.isRTL;
        boolean r6 = !r5;
        if (this.type == 1) goto L14;
    L13:
        int r7 = 0;
    L18:
        if (r02 == 8) goto L68;
        if (r02 == 9) goto L68;
        if (r02 != 11) goto L25;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("SavedDialogsTabCount", getMessagesController().getSavedMessagesController().getAllCount(), new Object[0]), r6);
        return;
    L25:
        if (r02 >= 0) goto L27;
        return;
    L27:
        if (r02 >= r1.length) goto L32;
        if (r1[r02] >= 0) goto L32;
        return;
    L32:
        if (r02 != 0) goto L43;
        showSubtitle(r7, true, true);
        if (this.sharedMediaLayout.getPhotosVideosTypeFilter() != 1) goto L37;
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("Photos", r1[6], new Object[0]), r6);
        return;
    L37:
        int r03 = this.sharedMediaLayout.getPhotosVideosTypeFilter();
        AnimatedTextView[] r13 = this.subtitleTextView;
        if (r03 != 2) goto L41;
        r13[r7].setText(LocaleController.formatPluralString("Videos", r1[7], new Object[0]), r6);
        return;
    L41:
        r13[r7].setText(LocaleController.formatPluralString("Media", r1[0], new Object[0]), r6);
        return;
    L43:
        if (r02 != 1) goto L46;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("Files", r1[1], new Object[0]), r6);
        return;
    L46:
        if (r02 != 2) goto L49;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("Voice", r1[2], new Object[0]), r6);
        return;
    L49:
        if (r02 != 3) goto L53;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("Links", r1[3], new Object[0]), r6);
        return;
    L53:
        if (r02 != 4) goto L57;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("MusicFiles", r1[4], new Object[0]), r6);
        return;
    L57:
        if (r02 != 5) goto L61;
        showSubtitle(r7, true, true);
        this.subtitleTextView[r7].setText(LocaleController.formatPluralString("GIFs", r1[5], new Object[0]), r6);
        return;
    L61:
        if (r02 != 10) goto L137;
        showSubtitle(r7, true, true);
        MessagesController.ChannelRecommendations r04 = MessagesController.getInstance(this.currentAccount).getChannelRecommendations(this.dialogId);
        AnimatedTextView r132 = this.subtitleTextView[r7];
        if (r04 != null) goto L65;
        int r12 = 0;
    L66:
        r132.setText(LocaleController.formatPluralString("Channels", r12, new Object[0]), r6);
        return;
    L65:
        r12 = r04.more + r04.chats.size();
        goto L66
    L137:
        return;
    L68:
        ActionBarMenuSubItem r14 = this.zoomOutItem;
        float r72 = 0.5f;
        if (r14 == null) goto L75;
        r14.setEnabled(this.sharedMediaLayout.canZoomOut());
        ActionBarMenuSubItem r15 = this.zoomOutItem;
        if (r15.isEnabled() == false) goto L73;
        float r11 = 1.0f;
    L74:
        r15.setAlpha(r11);
        goto L75
    L73:
        r11 = 0.5f;
    L75:
        ActionBarMenuSubItem r16 = this.zoomInItem;
        if (r16 == null) goto L82;
        r16.setEnabled(this.sharedMediaLayout.canZoomIn());
        ActionBarMenuSubItem r17 = this.zoomInItem;
        if (r17.isEnabled() == false) goto L80;
        float r112 = 1.0f;
    L81:
        r17.setAlpha(r112);
        goto L82
    L80:
        r112 = 0.5f;
    L82:
        int r18 = this.sharedMediaLayout.getStoriesCount(8);
        if (r18 > 0) goto L85;
        showSubtitle(0, false, true);
    L92:
        if (this.type != 1) goto L98;
        int r19 = this.sharedMediaLayout.getStoriesCount(9);
        if (r19 <= 0) goto L96;
        showSubtitle(1, true, true);
        this.subtitleTextView[1].setText(LocaleController.formatPluralString("ProfileStoriesArchiveCount", r19, new Object[0]), r6);
        goto L98
    L96:
        showSubtitle(1, false, true);
    L98:
        if (this.optionsItem == null) goto L110;
        SharedMediaLayout r110 = this.sharedMediaLayout;
        if (r110.getStoriesCount(r110.getClosestTab()) > 0) goto L102;
        boolean r111 = true;
    L103:
        if (r111 == true) goto L105;
        this.optionsItem.setVisibility(0);
    L105:
        ViewPropertyAnimator r3 = this.optionsItem.animate();
        if (r111 == false) goto L108;
        float r62 = 0.0f;
    L109:
        r3.alpha(r62).withEndAction(new MediaActivity$$ExternalSyntheticLambda1(this, r111)).setDuration(220).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        goto L110
    L108:
        r62 = 1.0f;
        goto L109
    L102:
        r111 = false;
    L110:
        ButtonWithCounterView r113 = this.button;
        if (r113 == null) goto L126;
        if (r5 == false) goto L114;
    L116:
        boolean r32 = false;
    L117:
        if (r02 != 8) goto L123;
        SparseArray<MessageObject> r4 = this.actionModeMessageObjects;
        if (r4 != null) goto L121;
        int r42 = 0;
    L122:
        r113.setText(LocaleController.formatPluralString("ArchiveStories", r42, new Object[0]), r32);
    L124:
        this.lastTab = r02;
        goto L126
    L121:
        r42 = r4.size();
        goto L122
    L123:
        r113.setText(LocaleController.getString(R.string.SaveToProfile), r32);
        goto L124
    L114:
        if (this.lastTab != r02) goto L116;
        r32 = true;
    L126:
        if (this.calendarItem != null) goto L128;
        return;
    L128:
        if (this.sharedMediaLayout.getStoriesCount(r02) <= 0) goto L130;
        r2 = true;
    L130:
        this.calendarItem.setEnabled(r2);
        ActionBarMenuSubItem r133 = this.calendarItem;
        if (r2 == false) goto L133;
        r72 = 1.0f;
    L133:
        r133.setAlpha(r72);
        return;
    L85:
        if (this.type == 3) goto L87;
        showSubtitle(0, true, true);
        this.subtitleTextView[0].setText(LocaleController.formatPluralString("ProfileMyStoriesCount", r18, new Object[0]), r6);
        goto L92
    L87:
        if (TextUtils.isEmpty(this.subtitleTextView[0].getText()) == false) goto L92;
        showSubtitle(0, true, true);
        this.subtitleTextView[0].setText(LocaleController.formatPluralStringSpaced("FoundStories", r18), r6);
        goto L92
    L14:
        if (r02 == 8) goto L13;
        r7 = 1;
        goto L18
    L139:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        if (this.sharedMediaLayout.isSwipeBackEnabled() == true) goto L7;
        return false;
    L7:
        return super.canBeginSlide();
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r34) {
        ActionBar r0 = this.actionBar;
        BackDrawable r3 = new BackDrawable(false);
        this.backDrawable = r3;
        r0.setBackButtonDrawable(r3);
        this.backDrawable.setAnimationTime(240.0f);
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r02 = new FrameLayout(r34);
        AnonymousClass2 r32 = new AnonymousClass2(this, r34, r02);
        r32.needBlur = true;
        this.fragmentView = r32;
        r32.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundGray));
        ActionBarMenu r6 = this.actionBar.createMenu();
        int r7 = this.type;
        if (r7 == 1) goto L5;
        if (r7 == 2) goto L5;
    L7:
        if (this.type != 0) goto L9;
        boolean r62 = true;
    L10:
        FrameLayout r72 = new FrameLayout(r34);
        this.titlesContainer = r72;
        r02.addView(r72, LayoutHelper.createFrame(-1, -1, 119));
        int r73 = 0;
    L12:
        if (this.type != 1) goto L14;
        int r13 = 2;
    L16:
        if (r73 >= r13) goto L29;
        this.titles[r73] = new FrameLayout(r34);
        this.titlesContainer.addView(this.titles[r73], LayoutHelper.createFrame(-1, -1, 119));
        this.nameTextView[r73] = new SimpleTextView(r34);
        this.nameTextView[r73].setPivotX(0.0f);
        this.nameTextView[r73].setPivotY(AndroidUtilities.dp(9.0f));
        this.nameTextView[r73].setTextSize(18);
        this.nameTextView[r73].setGravity(3);
        this.nameTextView[r73].setTypeface(AndroidUtilities.bold());
        this.nameTextView[r73].setLeftDrawableTopPadding(-AndroidUtilities.dp(1.3f));
        this.nameTextView[r73].setScrollNonFitText(true);
        this.nameTextView[r73].setImportantForAccessibility(2);
        FrameLayout r8 = this.titles[r73];
        SimpleTextView r132 = this.nameTextView[r73];
        if (r62 == false) goto L20;
        float r21 = 118.0f;
    L21:
        r8.addView(r132, LayoutHelper.createFrame(-2, -2.0f, 51, r21, 0.0f, 56.0f, 0.0f));
        this.subtitleTextView[r73] = new AnimatedTextView(r34, true, true, true);
        this.subtitleTextView[r73].setAnimationProperties(0.4f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.subtitleTextView[r73].setTextSize(AndroidUtilities.dp(14.0f));
        this.subtitleTextView[r73].setTextColor(Theme.getColor(Theme.key_player_actionBarSubtitle));
        FrameLayout r82 = this.titles[r73];
        AnimatedTextView r9 = this.subtitleTextView[r73];
        if (r62 == false) goto L24;
        float r212 = 118.0f;
    L25:
        r82.addView(r9, LayoutHelper.createFrame(-2, -2.0f, 51, r212, 0.0f, 56.0f, 0.0f));
        if (r73 == 0) goto L28;
        this.titles[r73].setAlpha(0.0f);
    L28:
        r73 = r73 + 1;
        goto L12
    L24:
        r212 = 72.0f;
        goto L25
    L20:
        r21 = 72.0f;
        goto L21
    L29:
        AnonymousClass3 r74 = new AnonymousClass3(this, r34);
        this.avatarImageView = r74;
        r74.getImageReceiver().setAllowDecodeSingleFrame(true);
        ProfileActivity.AvatarImageView r75 = this.avatarImageView;
        if (getDialogId() == getUserConfig().getClientUserId()) goto L32;
    L36:
        boolean r83 = false;
    L37:
        r75.setRoundRadius(ExteraConfig.getAvatarCorners(42.0f, false, r83));
        this.avatarImageView.setPivotX(0.0f);
        this.avatarImageView.setPivotY(0.0f);
        AvatarDrawable r76 = new AvatarDrawable();
        r76.setProfile(true);
        ProfileActivity.AvatarImageView r84 = this.avatarImageView;
        if (r62 == false) goto L40;
        int r92 = 0;
    L41:
        r84.setVisibility(r92);
        this.avatarImageView.setImageDrawable(r76);
        r02.addView(this.avatarImageView, LayoutHelper.createFrame(42, 42.0f, 51, 64.0f, 0.0f, 0.0f, 0.0f));
        AnimatedTextView r85 = new AnimatedTextView(r34, true, true, true);
        this.selectedTextView = r85;
        r85.setAnimationProperties(0.4f, 0, 320, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.selectedTextView.setTextSize(AndroidUtilities.dp(20.0f));
        this.selectedTextView.setGravity(3);
        AnimatedTextView r86 = this.selectedTextView;
        int r93 = Theme.key_windowBackgroundWhiteBlackText;
        r86.setTextColor(getThemedColor(r93));
        this.selectedTextView.setTypeface(AndroidUtilities.bold());
        AnimatedTextView r87 = this.selectedTextView;
        if (r62 == false) goto L44;
        int r63 = 48;
    L45:
        r02.addView(r87, LayoutHelper.createFrame(-2, -1.0f, 23, r63 + 72, -2.0f, 72.0f, 0.0f));
        if (this.type != 1) goto L49;
        StoriesTabsView r64 = new StoriesTabsView(this, r34, getResourceProvider());
        this.tabsView = r64;
        r64.setOnTabClick(new MediaActivity$$ExternalSyntheticLambda8(this));
        FrameLayout r65 = new FrameLayout(r34);
        this.buttonContainer = r65;
        r65.setPadding(AndroidUtilities.dp(10.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(8.0f));
        this.buttonContainer.setBackgroundColor(getThemedColor(Theme.key_windowBackgroundWhite));
        ButtonWithCounterView r66 = new ButtonWithCounterView(r34, getResourceProvider());
        this.button = r66;
        r66.setText(LocaleController.getString(R.string.SaveToProfile), false);
        this.button.setShowZero(true);
        this.button.setCount(0, false);
        this.button.setEnabled(false);
        this.button.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda9(this));
        this.buttonContainer.addView(this.button);
        this.buttonContainer.setAlpha(0.0f);
        this.buttonContainer.setTranslationY(AndroidUtilities.dp(100.0f));
        Bulletin.addDelegate(this, new AnonymousClass4(this));
    L49:
        if (this.type == 0) goto L51;
    L59:
        AnonymousClass6 r03 = new AnonymousClass6(this, r34, this.dialogId, this.sharedMediaPreloader, 0, null, this.currentChatInfo, this.currentUserInfo, this.initialTab, 0, this, new AnonymousClass5(this), 0, getResourceProvider(), r02, r32);
        this.sharedMediaLayout = r03;
        r03.scrollSlidingTextTabStrip.setOpen(true);
        if (this.sharedMediaLayout.getSearchOptionsItem() == null) goto L62;
        this.sharedMediaLayout.getSearchOptionsItem().setColorFilter(new PorterDuffColorFilter(getThemedColor(r93), PorterDuff.Mode.SRC_IN));
    L62:
        this.sharedMediaLayout.setPinnedToTop(true);
        this.sharedMediaLayout.getSearchItem().setTranslationY(0.0f);
        this.sharedMediaLayout.photoVideoOptionsItem.setTranslationY(0.0f);
        if (this.sharedMediaLayout.getSearchOptionsItem() == null) goto L65;
        this.sharedMediaLayout.getSearchOptionsItem().setTranslationY(0.0f);
    L65:
        int r33 = this.type;
        if (r33 == 1) goto L70;
        if (r33 == 2) goto L70;
        r32.addView(this.sharedMediaLayout);
    L71:
        r32.addView(this.actionBar);
        r32.addView(r02);
        r32.blurBehindViews.add(this.sharedMediaLayout);
        if (this.type != 1) goto L74;
        showSubtitle(0, false, false);
        showSubtitle(1, false, false);
    L74:
        StoriesTabsView r35 = this.tabsView;
        if (r35 == null) goto L77;
        int r77 = -1;
        r32.addView(r35, LayoutHelper.createFrame(-1, -2, 87));
    L78:
        FrameLayout r36 = this.buttonContainer;
        if (r36 == null) goto L81;
        r32.addView(r36, LayoutHelper.createFrame(r77, 64, 87));
    L81:
        long r37 = this.dialogId;
        if (this.topicId != 0) goto L84;
    L86:
        int r5 = this.type;
        TLObject r88 = null;
        if (r5 != 3) goto L92;
        this.nameTextView[0].setText(this.hashtag);
        int r38 = this.storiesCount;
        if (r38 == r77) goto L91;
        this.subtitleTextView[0].setText(LocaleController.formatPluralStringSpaced("FoundStories", r38));
    L91:
        AvatarDrawable r52 = r76;
    L123:
        this.avatarImageView.setImage(ImageLocation.getForUserOrChat(this.currentAccount, r88, 1), "50_50", r52, r88);
        SimpleTextView r39 = this.nameTextView[0];
        if (r39 == null) goto L129;
        if (TextUtils.isEmpty(r39.getText()) == false) goto L129;
        this.nameTextView[0].setText(LocaleController.getString(R.string.SharedContentTitle));
    L129:
        if (this.sharedMediaLayout.isSearchItemVisible() == true) goto L131;
    L133:
        ActionBarMenuItem r310 = this.sharedMediaLayout.searchItemIcon;
        if (r310 == null) goto L139;
        if (this.initialTab == 11) goto L139;
        r310.setVisibility(8);
    L139:
        if (this.sharedMediaLayout.getSearchOptionsItem() == null) goto L144;
        if (this.type == 1) goto L144;
        this.sharedMediaLayout.animateSearchToOptions(!r3.isSearchItemVisible(), false);
        this.sharedMediaLayout.getSearchOptionsItem().setVisibility(0);
    L144:
        if (this.sharedMediaLayout.isCalendarItemVisible() == true) goto L146;
    L148:
        this.sharedMediaLayout.photoVideoOptionsItem.setVisibility(4);
    L149:
        this.actionBar.setDrawBlurBackground(r32);
        AndroidUtilities.updateViewVisibilityAnimated(r02, true, 1.0f, false);
        updateMediaCount();
        updateColors();
        if (this.type == 1) goto L152;
    L154:
        return r32;
    L152:
        if (this.initialTab != 9) goto L154;
        this.sharedMediaLayout.onTabProgress(9.0f);
        goto L154
    L146:
        if (this.type == 1) goto L148;
        this.sharedMediaLayout.photoVideoOptionsItem.setVisibility(0);
        goto L149
    L131:
        if (this.type == 1) goto L133;
        this.sharedMediaLayout.getSearchItem().setVisibility(0);
        goto L133
    L92:
        if (r5 != 2) goto L94;
        this.nameTextView[0].setText(LocaleController.getString(R.string.ProfileStoriesArchive));
        goto L91
    L94:
        if (r5 != 1) goto L97;
        this.nameTextView[0].setText(LocaleController.getString(R.string.ProfileMyStories));
        this.nameTextView[1].setText(LocaleController.getString(R.string.ProfileStoriesArchive));
        goto L91
    L97:
        if (r37 != UserObject.ANONYMOUS) goto L99;
        this.nameTextView[0].setText(LocaleController.getString(R.string.AnonymousForward));
        AvatarDrawable r53 = r76;
        r53.setAvatarType(21);
        r53.setScaleSize(0.75f);
        r52 = r53;
        goto L123
    L99:
        AvatarDrawable r54 = r76;
        if (this.topicId == 0) goto L105;
        if (r37 != getUserConfig().getClientUserId()) goto L105;
        this.nameTextView[0].setText(LocaleController.getString(R.string.MyNotes));
        r54.setAvatarType(22);
        r54.setScaleSize(0.75f);
        r52 = r54;
    L105:
        if (DialogObject.isEncryptedDialog(r37) == false) goto L111;
        TLRPC.EncryptedChat r311 = getMessagesController().getEncryptedChat(Integer.valueOf(DialogObject.getEncryptedChatId(r37)));
        r52 = r54;
        if (r311 == null) goto L123;
        TLRPC.User r312 = getMessagesController().getUser(Long.valueOf(r311.user_id));
        r52 = r54;
        if (r312 == null) goto L123;
        this.nameTextView[0].setText(ContactsController.formatName(r312.first_name, r312.last_name));
        r54.setInfo(this.currentAccount, r312);
        TLRPC.User r313 = r312;
    L122:
        r88 = r313;
        r52 = r54;
        goto L123
    L111:
        boolean r67 = DialogObject.isUserDialog(r37);
        int r78 = this.currentAccount;
        if (r67 == false) goto L119;
        TLRPC.User r314 = MessagesController.getInstance(r78).getUser(Long.valueOf(r37));
        r52 = r54;
        if (r314 == null) goto L123;
        boolean r4 = r314.self;
        SimpleTextView[] r68 = this.nameTextView;
        if (r4 == false) goto L118;
        r68[0].setText(LocaleController.getString(R.string.SavedMessages));
        r54.setAvatarType(1);
        r54.setScaleSize(0.8f);
        r52 = r54;
        goto L123
    L118:
        r68[0].setText(ContactsController.formatName(r314.first_name, r314.last_name));
        r54.setInfo(this.currentAccount, r314);
        r313 = r314;
        goto L122
    L119:
        TLRPC.Chat r315 = MessagesController.getInstance(r78).getChat(Long.valueOf(-r37));
        r52 = r54;
        if (r315 == 0) goto L123;
        this.nameTextView[0].setText(r315.title);
        r54.setInfo(this.currentAccount, r315);
        r313 = r315;
        goto L122
    L84:
        if (r37 != getUserConfig().getClientUserId()) goto L86;
        r37 = this.topicId;
        goto L86
    L77:
        r77 = -1;
    L70:
        r32.addView(this.sharedMediaLayout, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 64.0f));
        goto L71
    L51:
        if (this.dialogId != getUserConfig().getClientUserId()) goto L59;
        if (this.topicId != 0) goto L59;
        if (getMessagesController().getSavedMessagesController().unsupported == true) goto L59;
        if (getMessagesController().getSavedMessagesController().hasDialogs() == false) goto L59;
        this.initialTab = 11;
        goto L59
    L44:
        r63 = 0;
        goto L45
    L40:
        r92 = 8;
        goto L41
    L32:
        if (this.topicId != 0) goto L36;
        if (getMessagesController().savedViewAsChats == false) goto L36;
        r83 = true;
        goto L37
    L14:
        r13 = 1;
        goto L16
    L9:
        r62 = false;
    L5:
        FrameLayout r79 = new FrameLayout(r34);
        this.actionBar.addView(r79, LayoutHelper.createFrame(56, 56, 85));
        int r133 = Theme.key_actionBarActionModeDefaultSelector;
        int r14 = getThemedColor(r133);
        int r15 = Theme.key_windowBackgroundWhiteBlackText;
        ActionBarMenuItem r12 = new ActionBarMenuItem(r34, r6, r14, getThemedColor(r15));
        this.deleteItem = r12;
        r12.setIcon(R.drawable.msg_delete);
        this.deleteItem.setVisibility(8);
        this.deleteItem.setAlpha(0.0f);
        this.deleteItem.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda2(r6));
        r79.addView(this.deleteItem);
        ActionBarMenuItem r10 = new ActionBarMenuItem(r34, r6, getThemedColor(r133), getThemedColor(r15));
        this.optionsItem = r10;
        r10.setIcon(R.drawable.ic_ab_other);
        this.optionsItem.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda3(this));
        this.optionsItem.setVisibility(8);
        this.optionsItem.setAlpha(0.0f);
        r79.addView(this.optionsItem);
        ActionBarMenuSubItem r69 = this.optionsItem.addSubItem(8, R.drawable.msg_zoomin, LocaleController.getString(R.string.MediaZoomIn));
        this.zoomInItem = r69;
        r69.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda4(this));
        ActionBarMenuSubItem r610 = this.optionsItem.addSubItem(9, R.drawable.msg_zoomout, LocaleController.getString(R.string.MediaZoomOut));
        this.zoomOutItem = r610;
        r610.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda5(this));
        ActionBarMenuSubItem r611 = this.optionsItem.addSubItem(10, R.drawable.msg_calendar2, LocaleController.getString(R.string.Calendar));
        this.calendarItem = r611;
        r611.setEnabled(false);
        this.calendarItem.setAlpha(0.5f);
        this.optionsItem.addColoredGap();
        ActionBarMenuSubItem r612 = this.optionsItem.addSubItem(6, 0, LocaleController.getString(R.string.MediaShowPhotos), true);
        this.showPhotosItem = r612;
        r612.setChecked(this.filterPhotos);
        this.showPhotosItem.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda6(this));
        ActionBarMenuSubItem r613 = this.optionsItem.addSubItem(7, 0, LocaleController.getString(R.string.MediaShowVideos), true);
        this.showVideosItem = r613;
        r613.setChecked(this.filterVideos);
        this.showVideosItem.setOnClickListener(new MediaActivity$$ExternalSyntheticLambda7(this));
        goto L7
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.userInfoDidLoad) goto L5;
        int r2 = NotificationCenter.didReceiveNewMessages;
        return;
    L5:
        if (((Long) r5[0]).longValue() != this.dialogId) goto L12;
        TLRPC.UserFull r32 = (TLRPC.UserFull) r5[1];
        this.currentUserInfo = r32;
        SharedMediaLayout r22 = this.sharedMediaLayout;
        if (r22 == null) goto L13;
        r22.setUserInfo(r32);
        return;
    L13:
        return;
    }

    public long getDialogId() {
        return this.dialogId;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public int getNavigationBarColor() {
        int r0 = getThemedColor(Theme.key_windowBackgroundWhite);
        if (getLastStoryViewer() != null) goto L5;
    L8:
        return r0;
    L5:
        if (getLastStoryViewer().attachedToParent() == false) goto L8;
        return getLastStoryViewer().getNavigationBarColor(r0);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public ArrayList<ThemeDescription> getThemeDescriptions() {
        MediaActivity$$ExternalSyntheticLambda0 r6 = new MediaActivity$$ExternalSyntheticLambda0(this);
        ArrayList<ThemeDescription> r8 = new ArrayList();
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhite));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_actionBarActionModeDefaultSelector));
        r8.add(new ThemeDescription(null, 0, null, null, null, r6, Theme.key_windowBackgroundWhiteBlackText));
        r8.addAll(this.sharedMediaLayout.getThemeDescriptions());
        return r8;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isLightStatusBar() {
        if (getLastStoryViewer() != null) goto L5;
    L7:
        int r0 = Theme.getColor(Theme.key_windowBackgroundWhite);
        if (this.actionBar.isActionModeShowed() == false) goto L11;
        r0 = Theme.getColor(Theme.key_actionBarActionModeDefault);
    L11:
        if (ColorUtils.calculateLuminance(r0) <= 0.699999988079071d) goto L14;
        return true;
    L14:
        return false;
    L5:
        if (getLastStoryViewer().isShown() == false) goto L7;
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r1) {
        if (this.sharedMediaLayout.isSwipeBackEnabled() == true) goto L7;
        return false;
    L7:
        return this.sharedMediaLayout.isCurrentTabFirst();
    }

    @Override // org.telegram.ui.Components.SharedMediaLayout.SharedMediaPreloaderDelegate
    public void mediaCountUpdated() {
        SharedMediaLayout r0 = this.sharedMediaLayout;
        if (r0 == null) goto L7;
        SharedMediaLayout.SharedMediaPreloader r1 = this.sharedMediaPreloader;
        if (r1 == null) goto L7;
        r0.setNewMediaCounts(r1.getLastMediaCount());
    L7:
        updateMediaCount();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (hasShownSheet() == false) goto L8;
        if (r3 == false) goto L6;
        closeSheet();
    L6:
        return false;
    L8:
        if (this.sharedMediaLayout.isActionModeShown() == false) goto L13;
        if (r3 == false) goto L11;
        this.sharedMediaLayout.closeActionMode(false);
    L11:
        return false;
    L13:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        this.type = getArguments().getInt("type", 0);
        this.dialogId = getArguments().getLong("dialog_id");
        this.topicId = getArguments().getLong("topic_id", 0);
        this.hashtag = getArguments().getString("hashtag", _UrlKt.FRAGMENT_ENCODE_SET);
        this.username = getArguments().getString("username", _UrlKt.FRAGMENT_ENCODE_SET);
        this.storiesCount = getArguments().getInt("storiesCount", -1);
        int r0 = this.type;
        if (r0 != 2) goto L6;
        int r02 = 9;
    L9:
        this.initialTab = getArguments().getInt("start_from", r02);
        getNotificationCenter().addObserver(this, NotificationCenter.userInfoDidLoad);
        getNotificationCenter().addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().addObserver(this, NotificationCenter.storiesEnabledUpdate);
        if (DialogObject.isUserDialog(this.dialogId) == false) goto L17;
        if (this.topicId != 0) goto L17;
        TLRPC.User r03 = getMessagesController().getUser(Long.valueOf(this.dialogId));
        if (UserObject.isUserSelf(r03) == false) goto L17;
        getMessagesController().loadUserInfo(r03, false, this.classGuid);
        this.currentUserInfo = getMessagesController().getUserFull(this.dialogId);
    L17:
        if (this.sharedMediaPreloader != null) goto L19;
        this.sharedMediaPreloader = new SharedMediaLayout.SharedMediaPreloader(this);
    L19:
        this.sharedMediaPreloader.addDelegate(this);
        return super.onFragmentCreate();
    L6:
        if (r0 != 1) goto L8;
        r02 = 8;
        goto L9
    L8:
        r02 = 0;
        goto L9
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        getNotificationCenter().removeObserver(this, NotificationCenter.userInfoDidLoad);
        getNotificationCenter().removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        getNotificationCenter().removeObserver(this, NotificationCenter.storiesEnabledUpdate);
        Runnable r0 = this.applyBulletin;
        if (r0 == null) goto L5;
        this.applyBulletin = null;
        AndroidUtilities.runOnUIThread(r0);
    L5:
        Bulletin.removeDelegate(this);
        SharedMediaLayout r02 = this.sharedMediaLayout;
        if (r02 == null) goto L8;
        r02.onDestroy();
    L8:
        SharedMediaLayout.SharedMediaPreloader r03 = this.sharedMediaPreloader;
        if (r03 == null) goto L12;
        r03.onDestroy(this);
        this.sharedMediaPreloader.removeDelegate(this);
        return;
    }

    @Override // org.telegram.ui.Components.FloatingDebug.FloatingDebugProvider
    public List<FloatingDebugController.DebugItem> onGetDebugItems() {
        if (ShapeDetector.isLearning(getContext()) == false) goto L5;
        String r1 = "Disable";
    L7:
        return Arrays.asList(new FloatingDebugController.DebugItem[]{new FloatingDebugController.DebugItem(r1.concat(" shape detector learning debug"), new MediaActivity$$ExternalSyntheticLambda14(this))});
    L5:
        r1 = "Enable";
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        SharedMediaLayout r0 = this.sharedMediaLayout;
        if (r0 == null) goto L6;
        r0.setPagesPaddingBottom(r4);
        return;
    }

    public void setChatInfo(TLRPC.ChatFull r1) {
        this.currentChatInfo = r1;
    }
}
