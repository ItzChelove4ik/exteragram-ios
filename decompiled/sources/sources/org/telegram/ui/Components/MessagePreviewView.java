package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.StaticLayout;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import android.text.style.URLSpan;
import android.util.LongSparseArray;
import android.util.Property;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import androidx.core.graphics.ColorUtils;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManagerFixed;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.chats.StickerTime;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagePreviewParams;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.IMessageCell;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes7.dex */
public abstract class MessagePreviewView extends FrameLayout {
    Runnable changeBoundsRunnable;
    final ChatActivity chatActivity;
    private final int currentAccount;
    TLRPC.Chat currentChat;
    TLRPC.User currentUser;
    private final ArrayList<MessageObject.GroupedMessages> drawingGroups;
    private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
    boolean isLandscapeMode;
    final MessagePreviewParams messagePreviewParams;
    ValueAnimator offsetsAnimator;
    private final ResourcesDelegate resourcesProvider;
    boolean returnSendersNames;
    TLRPC.Peer sendAsPeer;
    final boolean showOutdatedQuote;
    boolean showing;
    TabsView tabsView;
    ViewPagerFixed viewPager;

    /* renamed from: org.telegram.ui.Components.MessagePreviewView$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ MessagePreviewView this$0;

        public AnonymousClass1(MessagePreviewView r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            ValueAnimator r0 = this.this$0.offsetsAnimator;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.isRunning() == true) goto L9;
            this.this$0.offsetsAnimator.start();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.MessagePreviewView$2, reason: invalid class name */
    public class AnonymousClass2 extends ViewPagerFixed {
        final /* synthetic */ MessagePreviewView this$0;

        public AnonymousClass2(MessagePreviewView r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            super(r2, r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onScrollEnd() {
            View r1 = this.viewPages[0];
            if ((r1 instanceof Page) == false) goto L6;
            ((Page) r1).textSelectionHelper.stopScrolling();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r2) {
            MessagePreviewView r22 = this.this$0;
            r22.tabsView.setSelectedTab(r22.viewPager.getPositionAnimated());
            View r23 = this.viewPages[0];
            if ((r23 instanceof Page) == false) goto L5;
            ((Page) r23).textSelectionHelper.onParentScrolled();
        L5:
            View r1 = this.viewPages[1];
            if ((r1 instanceof Page) == false) goto L9;
            ((Page) r1).textSelectionHelper.onParentScrolled();
            return;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed, android.view.View
        public boolean onTouchEvent(MotionEvent r2) {
            if (this.this$0.isTouchedHandle() == false) goto L7;
            return false;
        L7:
            return super.onTouchEvent(r2);
        }
    }

    /* renamed from: org.telegram.ui.Components.MessagePreviewView$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed.Adapter {
        final /* synthetic */ MessagePreviewView this$0;
        final /* synthetic */ Context val$context;

        public AnonymousClass3(MessagePreviewView r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
            ((Page) r1).bind();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r3) {
            return new Page(this.this$0, this.val$context, r3);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return this.this$0.tabsView.tabs.size();
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemViewType(int r1) {
            return this.this$0.tabsView.tabs.get(r1).id;
        }
    }

    /* renamed from: org.telegram.ui.Components.MessagePreviewView$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ MessagePreviewView this$0;
        final /* synthetic */ boolean val$canShowKeyboard;

        public AnonymousClass4(MessagePreviewView r1, boolean r2) {
            this.this$0 = r1;
            this.val$canShowKeyboard = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (this.this$0.getParent() == null) goto L5;
            ((ViewGroup) this.this$0.getParent()).removeView(this.this$0);
        L5:
            this.this$0.onFullDismiss(this.val$canShowKeyboard);
        }
    }

    public class ActionBar extends FrameLayout {
        private Theme.ResourcesProvider resourcesProvider;
        private final AnimatedTextView.AnimatedTextDrawable subtitle;
        final /* synthetic */ MessagePreviewView this$0;
        private final AnimatedTextView.AnimatedTextDrawable title;

        public ActionBar(MessagePreviewView r9, Context r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r10);
            this.resourcesProvider = r11;
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.title = r0;
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            r0.setAnimationProperties(0.3f, 0, 430, r6);
            r0.setTypeface(AndroidUtilities.bold());
            r0.setTextColor(Theme.getColor(Theme.key_actionBarDefaultTitle, r11));
            r0.setTextSize(AndroidUtilities.dp(18.0f));
            r0.setEllipsizeByGradient(!LocaleController.isRTL);
            r0.setCallback(this);
            r0.setOverrideFullWidth(AndroidUtilities.displaySize.x);
            AnimatedTextView.AnimatedTextDrawable r1 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.subtitle = r1;
            r1.setAnimationProperties(0.3f, 0, 430, r6);
            r1.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubtitle, r11));
            r1.setTextSize(AndroidUtilities.dp(14.0f));
            r1.setEllipsizeByGradient(true ^ LocaleController.isRTL);
            r1.setCallback(this);
            r1.setOverrideFullWidth(AndroidUtilities.displaySize.x);
        }

        private void setBounds(Drawable r5, float r6) {
            int r62 = (int) r6;
            r5.setBounds(getPaddingLeft(), r62 - AndroidUtilities.dp(32.0f), getMeasuredWidth() - getPaddingRight(), r62 + AndroidUtilities.dp(32.0f));
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r5) {
            setBounds(this.title, AndroidUtilities.lerp(AndroidUtilities.dp(29.0f), AndroidUtilities.dp(18.83f), this.subtitle.isNotEmpty()));
            this.title.draw(r5);
            setBounds(this.subtitle, AndroidUtilities.dp(39.5f));
            this.subtitle.draw(r5);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
            setPadding(AndroidUtilities.dp(18.0f), 0, AndroidUtilities.dp(18.0f), 0);
        }

        public void setSubtitle(CharSequence r1, boolean r2) {
            AnimatedTextView.AnimatedTextDrawable r0 = this.subtitle;
            if (r2 == true) goto L5;
        L7:
            boolean r22 = false;
        L8:
            r0.setText(r1, r22);
            return;
        L5:
            if (LocaleController.isRTL == true) goto L7;
            r22 = true;
            goto L8
        }

        public void setTitle(CharSequence r1, boolean r2) {
            AnimatedTextView.AnimatedTextDrawable r0 = this.title;
            if (r2 == true) goto L5;
        L7:
            boolean r22 = false;
        L8:
            r0.setText(r1, r22);
            return;
        L5:
            if (LocaleController.isRTL == true) goto L7;
            r22 = true;
            goto L8
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (this.title != r2) goto L5;
            return true;
        L5:
            if (this.subtitle != r2) goto L7;
            return true;
        L7:
            if (super.verifyDrawable(r2) == true) goto L14;
            return false;
        L14:
            return true;
        }
    }

    public class Page extends FrameLayout {
        ActionBar actionBar;
        Adapter adapter;
        private int buttonsHeight;
        ToggleButton changePositionBtn;
        ToggleButton changeSizeBtn;
        FrameLayout changeSizeBtnContainer;
        GridLayoutManagerFixed chatLayoutManager;
        RecyclerListView chatListView;
        SizeNotifierFrameLayout chatPreviewContainer;
        int chatTopOffset;
        ActionBarMenuSubItem clearQuoteButton;
        public int currentTab;
        int currentTopOffset;
        float currentYOffset;
        ActionBarMenuSubItem deleteReplyButton;
        private boolean firstAttach;
        private boolean firstLayout;
        ChatListItemAnimator itemAnimator;
        int lastSize;
        ActionBarPopupWindow.ActionBarPopupWindowLayout menu;
        int menuBack;
        MessagePreviewParams.Messages messages;
        ActionBarMenuSubItem quoteAnotherChatButton;
        ActionBarMenuSubItem quoteButton;
        private AnimatorSet quoteSwitcher;
        Rect rect;
        ActionBarMenuSubItem replyAnotherChatButton;
        int scrollToQuoteEndY;
        int scrollToQuoteStartY;
        ChatMessageSharedResources sharedResources;
        boolean shouldScrollToQuote;
        TextSelectionHelper.ChatListTextSelectionHelper textSelectionHelper;
        View textSelectionOverlay;
        final /* synthetic */ MessagePreviewView this$0;
        boolean toQuote;
        boolean updateAfterAnimations;
        private boolean updateScroll;
        ToggleButton videoChangeSizeBtn;
        float yOffset;

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ Page this$1;
            final /* synthetic */ boolean val$quote;

            public AnonymousClass1(Page r1, boolean r2) {
                this.this$1 = r1;
                this.val$quote = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Page.m11282$$Nest$fputquoteSwitcher(this.this$1, null);
                Page.m11287$$Nest$mswitchToQuote(this.this$1, this.val$quote, false);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$10, reason: invalid class name */
        public class AnonymousClass10 extends GridLayoutManagerFixed {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public static /* synthetic */ void $r8$lambda$qMJcYjRkkEtXwr8xwb5fsjS4Mxk(AnonymousClass10 r0) {
                r0.lambda$onLayoutChildren$0();
            }

            public AnonymousClass10(Page r1, Context r2, int r3, int r4, boolean r5, MessagePreviewView r6) {
                this.this$1 = r1;
                this.val$this$0 = r6;
                super(r2, r3, r4, r5);
            }

            private /* synthetic */ void lambda$onLayoutChildren$0() {
                this.this$1.adapter.notifyDataSetChanged();
            }

            @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
            public boolean hasSiblingChild(int r7) {
                MessageObject r72 = this.this$1.messages.previewMessages.get(r7);
                MessageObject.GroupedMessages r6 = Page.m11284$$Nest$mgetValidGroupedMessage(this.this$1, r72);
                if (r6 == null) goto L22;
                MessageObject.GroupedMessagePosition r73 = r6.getPosition(r72);
                if (r73.minX == r73.maxX) goto L22;
                byte r1 = r73.minY;
                if (r1 != r73.maxY) goto L22;
                if (r1 == 0) goto L22;
                int r12 = r6.posArray.size();
                int r2 = 0;
            L11:
                if (r2 >= r12) goto L22;
                MessageObject.GroupedMessagePosition r3 = r6.posArray.get(r2);
                if (r3 == r73) goto L21;
                byte r4 = r3.minY;
                byte r5 = r73.minY;
                if (r4 > r5) goto L21;
                if (r3.maxY < r5) goto L21;
                return true;
            L21:
                r2 = r2 + 1;
            L22:
                return false;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
            public void onLayoutChildren(RecyclerView.Recycler r2, RecyclerView.State r3) {
                if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L11;
                super.onLayoutChildren(r2, r3);
                return;
            L11:
                super.onLayoutChildren(r2, r3);     // Catch: Exception -> L8
                return;
            L8:
                e = move-exception;
                FileLog.e(e);
                AndroidUtilities.runOnUIThread(new MessagePreviewView$Page$10$$ExternalSyntheticLambda0(this));
            }

            @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
            public boolean shouldLayoutChildFromOpositeSide(View r1) {
                return false;
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$11, reason: invalid class name */
        public class AnonymousClass11 extends GridLayoutManager.SpanSizeLookup {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass11(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(int r2) {
                if (r2 >= 0) goto L4;
                return 1000;
            L4:
                if (r2 >= this.this$1.messages.previewMessages.size()) goto L11;
                MessageObject r22 = this.this$1.messages.previewMessages.get(r2);
                MessageObject.GroupedMessages r1 = Page.m11284$$Nest$mgetValidGroupedMessage(this.this$1, r22);
                if (r1 != null) goto L8;
                return 1000;
            L8:
                return r1.getPosition(r22).spanSize;
            L11:
                return 1000;
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$12, reason: invalid class name */
        public class AnonymousClass12 extends RecyclerView.ItemDecoration {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass12(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.ItemDecoration
            public void getItemOffsets(Rect r8, View r9, RecyclerView r10, RecyclerView.State r11) {
                int r7 = 0;
                r8.bottom = 0;
                if ((r9 instanceof ChatMessageCell) == false) goto L37;
                ChatMessageCell r92 = (ChatMessageCell) r9;
                MessageObject.GroupedMessages r102 = r92.getCurrentMessagesGroup();
                if (r102 == null) goto L38;
                MessageObject.GroupedMessagePosition r112 = r92.getCurrentPosition();
                if (r112 != null) goto L9;
                return;
            L9:
                if (r112.siblingHeights == null) goto L40;
                Point r0 = AndroidUtilities.displaySize;
                float r02 = Math.max(r0.x, r0.y) * 0.5f;
                int r93 = r92.getExtraInsetHeight();
                int r1 = 0;
            L12:
                if (r1 >= r112.siblingHeights.length) goto L14;
                r93 = r93 + ((int) Math.ceil(r2[r1] * r02));
                r1 = r1 + 1;
                goto L12
            L14:
                int r94 = r93 + ((r112.maxY - r112.minY) * Math.round(AndroidUtilities.density * 7.0f));
                int r12 = r102.posArray.size();
            L15:
                if (r7 >= r12) goto L29;
                MessageObject.GroupedMessagePosition r2 = r102.posArray.get(r7);
                byte r3 = r2.minY;
                byte r4 = r112.minY;
                if (r3 != r4) goto L28;
                if (r2.minX == r112.minX) goto L21;
            L26:
                if (r3 != r4) goto L28;
                r94 = r94 - (((int) Math.ceil(r02 * r2.ph)) - AndroidUtilities.dp(4.0f));
                goto L29
            L21:
                if (r2.maxX != r112.maxX) goto L26;
                if (r3 != r4) goto L26;
                if (r2.maxY != r112.maxY) goto L26;
            L28:
                r7 = r7 + 1;
            L29:
                r8.bottom = -r94;
                return;
            L40:
                return;
            L38:
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$13, reason: invalid class name */
        public class AnonymousClass13 extends ActionBarMenuSubItem {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass13(Page r1, Context r2, boolean r3, boolean r4, boolean r5, Theme.ResourcesProvider r6, MessagePreviewView r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r2, r3, r4, r5, r6);
            }

            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r3) {
                if (getVisibility() == 0) goto L5;
                return false;
            L5:
                if (getAlpha() >= 0.5f) goto L8;
                return false;
            L8:
                return super.onTouchEvent(r3);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem
            public void updateBackground() {
                setBackground(null);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$14, reason: invalid class name */
        public class AnonymousClass14 extends ActionBarMenuSubItem {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass14(Page r1, Context r2, boolean r3, boolean r4, boolean r5, Theme.ResourcesProvider r6, MessagePreviewView r7) {
                this.this$1 = r1;
                this.val$this$0 = r7;
                super(r2, r3, r4, r5, r6);
            }

            @Override // android.view.View
            public boolean onTouchEvent(MotionEvent r3) {
                if (getVisibility() == 0) goto L5;
                return false;
            L5:
                if (getAlpha() >= 0.5f) goto L8;
                return false;
            L8:
                return super.onTouchEvent(r3);
            }

            @Override // org.telegram.ui.ActionBar.ActionBarMenuSubItem
            public void updateBackground() {
                setBackground(null);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$15, reason: invalid class name */
        public class AnonymousClass15 extends AnimatorListenerAdapter {
            final /* synthetic */ Page this$1;

            public AnonymousClass15(Page r1) {
                this.this$1 = r1;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r2) {
                Page r1 = this.this$1;
                r1.this$0.offsetsAnimator = null;
                Page.m11285$$Nest$msetOffset(r1, r1.yOffset, r1.chatTopOffset);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$2, reason: invalid class name */
        public class AnonymousClass2 extends SizeNotifierFrameLayout {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass2(Page r1, Context r2, MessagePreviewView r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.ViewGroup, android.view.View
            public boolean dispatchTouchEvent(MotionEvent r3) {
                if (r3.getY() >= this.this$1.currentTopOffset) goto L7;
                return false;
            L7:
                return super.dispatchTouchEvent(r3);
            }

            @Override // org.telegram.ui.Components.SizeNotifierFrameLayout
            public Drawable getNewDrawable() {
                Drawable r0 = MessagePreviewView.m11267$$Nest$fgetresourcesProvider(this.this$1.this$0).getWallpaperDrawable();
                if (r0 == null) goto L6;
                return r0;
            L6:
                return super.getNewDrawable();
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$3, reason: invalid class name */
        public class AnonymousClass3 extends ViewOutlineProvider {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass3(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // android.view.ViewOutlineProvider
            public void getOutline(View r7, Outline r8) {
                r8.setRoundRect(0, this.this$1.currentTopOffset + 1, r7.getMeasuredWidth(), r7.getMeasuredHeight(), AndroidUtilities.dp(8.0f));
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$4, reason: invalid class name */
        public class AnonymousClass4 extends TextSelectionHelper.ChatListTextSelectionHelper {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass4(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
                this.resourcesProvider = MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r1.this$0);
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper
            public boolean canCopy() {
                if (this.this$1.isReplyToRichMessage() == false) goto L5;
                return false;
            L5:
                MessagePreviewParams r2 = this.this$1.this$0.messagePreviewParams;
                if (r2 != null) goto L8;
                return true;
            L8:
                if (r2.noforwards == false) goto L13;
                return false;
            L13:
                return true;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper
            public boolean canShowQuote() {
                Page r1 = this.this$1;
                if (r1.currentTab == 0) goto L5;
                return false;
            L5:
                if (r1.this$0.messagePreviewParams.isSecret == false) goto L7;
                return false;
            L7:
                if (r1.isReplyToRichMessage() == true) goto L13;
                return true;
            L13:
                return false;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper
            public Theme.ResourcesProvider getResourcesProvider() {
                return this.resourcesProvider;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.ChatListTextSelectionHelper, org.telegram.ui.Cells.TextSelectionHelper
            public void invalidate() {
                super.invalidate();
                RecyclerListView r0 = this.this$1.chatListView;
                if (r0 == null) goto L6;
                r0.invalidate();
                return;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper
            public boolean isSelected(MessageObject r2) {
                Page r22 = this.this$1;
                if (r22.currentTab == 0) goto L5;
                return false;
            L5:
                if (r22.this$0.messagePreviewParams.isSecret == false) goto L7;
                return false;
            L7:
                if (isInSelectionMode() == false) goto L13;
                return true;
            L13:
                return false;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper
            public void onQuoteClick(MessageObject r4, int r5, int r6, CharSequence r7) {
                Page r72 = this.this$1;
                TextSelectionHelper.ChatListTextSelectionHelper r0 = r72.textSelectionHelper;
                int r1 = r0.selectionEnd - r0.selectionStart;
                int r73 = MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(r72.this$0)).quoteLengthMax;
                Page r02 = this.this$1;
                if (r1 <= r73) goto L6;
                Page.m11286$$Nest$mshowQuoteLengthError(r02);
                return;
            L6:
                MessagePreviewParams r74 = r02.this$0.messagePreviewParams;
                TextSelectionHelper.ChatListTextSelectionHelper r12 = r02.textSelectionHelper;
                r74.quoteStart = r12.selectionStart;
                r74.quoteEnd = r12.selectionEnd;
                MessageObject r42 = r02.getReplyMessage(r4);
                if (r42 == null) goto L15;
                ChatActivity.ReplyQuote r75 = this.this$1.this$0.messagePreviewParams.quote;
                if (r75 == null) goto L14;
                MessageObject r76 = r75.message;
                if (r76 == null) goto L14;
                if (r76.getId() == r42.getId()) goto L15;
            L14:
                MessagePreviewParams r77 = this.this$1.this$0.messagePreviewParams;
                r77.quote = ChatActivity.ReplyQuote.from(r42, r5, r6);
            L15:
                this.this$1.this$0.onQuoteSelectedPart();
                this.this$1.this$0.dismiss(true);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$5, reason: invalid class name */
        public class AnonymousClass5 extends TextSelectionHelper.Callback {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass5(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Cells.TextSelectionHelper.Callback
            public void onStateChanged(boolean r5) {
                Page r0 = this.this$1;
                if (r0.this$0.showing == true) goto L6;
                return;
            L6:
                if (r5 == false) goto L8;
            L11:
                if (r5 == false) goto L24;
                Page r52 = this.this$1;
                TextSelectionHelper.ChatListTextSelectionHelper r02 = r52.textSelectionHelper;
                int r2 = r02.selectionEnd - r02.selectionStart;
                int r53 = MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(r52.this$0)).quoteLengthMax;
                Page r03 = this.this$1;
                if (r2 <= r53) goto L17;
                Page.m11286$$Nest$mshowQuoteLengthError(r03);
                return;
            L17:
                if (r03.textSelectionHelper.getSelectedCell() == null) goto L19;
                MessageObject r54 = this.this$1.textSelectionHelper.getSelectedCell().getMessageObject();
            L20:
                MessageObject r55 = this.this$1.getReplyMessage(r54);
                Page r04 = this.this$1;
                MessagePreviewParams r22 = r04.this$0.messagePreviewParams;
                if (r22.quote != null) goto L25;
                TextSelectionHelper.ChatListTextSelectionHelper r05 = r04.textSelectionHelper;
                int r3 = r05.selectionStart;
                r22.quoteStart = r3;
                int r06 = r05.selectionEnd;
                r22.quoteEnd = r06;
                r22.quote = ChatActivity.ReplyQuote.from(r55, r3, r06);
                Page.m11287$$Nest$mswitchToQuote(this.this$1, true, true);
                this.this$1.menu.getSwipeBack().openForeground(this.this$1.menuBack);
                return;
            L25:
                return;
            L19:
                r54 = null;
                goto L20
            L24:
                return;
            L8:
                if (r0.menu.getSwipeBack().isForegroundOpen() == false) goto L11;
                this.this$1.menu.getSwipeBack().closeForeground(true);
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$6, reason: invalid class name */
        public class AnonymousClass6 extends RecyclerListView {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            /* renamed from: $r8$lambda$b2z-qhGDI4lWxkrQESKSJaQEL38, reason: not valid java name */
            public static /* synthetic */ void m11292$r8$lambda$b2zqhGDI4lWxkrQESKSJaQEL38(AnonymousClass6 r0, int r1, int r2) {
                r0.lambda$onLayout$0(r1, r2);
            }

            public AnonymousClass6(Page r1, Context r2, Theme.ResourcesProvider r3, MessagePreviewView r4) {
                this.this$1 = r1;
                this.val$this$0 = r4;
                super(r2, r3);
            }

            private void drawChatBackgroundElements(Canvas r28) {
                int r1 = getChildCount();
                boolean r3 = false;
                int r4 = 0;
                MessageObject.GroupedMessages r5 = null;
            L3:
                if (r4 >= r1) goto L12;
                View r6 = getChildAt(r4);
                if ((r6 instanceof ChatMessageCell) == false) goto L11;
                MessageObject.GroupedMessages r62 = ((ChatMessageCell) r6).getCurrentMessagesGroup();
                if (r62 == null) goto L10;
                if (r62 == r5) goto L11;
            L10:
                r5 = r62;
            L11:
                r4 = r4 + 1;
                goto L3
            L12:
                int r42 = 0;
            L14:
                if (r42 >= 3) goto L120;
                MessagePreviewView.m11265$$Nest$fgetdrawingGroups(this.this$1.this$0).clear();
                if (r42 == 2) goto L18;
            L20:
                int r63 = r3 ? 1 : 0;
            L21:
                boolean r7 = true;
                if (r63 >= r1) goto L82;
                View r8 = this.this$1.chatListView.getChildAt(r63);
                if ((r8 instanceof ChatMessageCell) == false) goto L81;
                ChatMessageCell r9 = (ChatMessageCell) r8;
                if (r8.getY() > this.this$1.chatListView.getHeight()) goto L81;
                if ((r8.getY() + r8.getHeight()) < 0.0f) goto L81;
                MessageObject.GroupedMessages r82 = r9.getCurrentMessagesGroup();
                if (r82 == null) goto L81;
                if (r42 == 0) goto L34;
            L35:
                if (r42 == 1) goto L37;
            L39:
                if (r42 == 0) goto L41;
            L42:
                if (r42 == 1) goto L44;
            L46:
                if (r42 == 2) goto L48;
            L49:
                if (r42 == 2) goto L54;
                if (r9.willRemovedAfterAnimation() == true) goto L81;
            L54:
                if (MessagePreviewView.m11265$$Nest$fgetdrawingGroups(this.this$1.this$0).contains(r82) == true) goto L56;
                MessageObject.GroupedMessages.TransitionParams r72 = r82.transitionParams;
                r72.left = r3 ? 1 : 0;
                r72.top = r3 ? 1 : 0;
                r72.right = r3 ? 1 : 0;
                r72.bottom = r3 ? 1 : 0;
                r72.pinnedBotton = r3;
                r72.pinnedTop = r3;
                r72.cell = r9;
                MessagePreviewView.m11265$$Nest$fgetdrawingGroups(this.this$1.this$0).add(r82);
            L56:
                r82.transitionParams.pinnedTop = r9.isPinnedTop();
                r82.transitionParams.pinnedBotton = r9.isPinnedBottom();
                int r73 = r9.getLeft() + r9.getBackgroundDrawableLeft();
                int r10 = r9.getLeft() + r9.getBackgroundDrawableRight();
                int r11 = (r9.getTop() + r9.getPaddingTop()) + r9.getBackgroundDrawableTop();
                int r12 = (r9.getTop() + r9.getPaddingTop()) + r9.getBackgroundDrawableBottom();
                if ((r9.getCurrentPosition().flags & 4) != 0) goto L60;
                r11 = r11 - AndroidUtilities.dp(10.0f);
            L60:
                if ((r9.getCurrentPosition().flags & 8) != 0) goto L63;
                r12 = r12 + AndroidUtilities.dp(10.0f);
            L63:
                if (r9.willRemovedAfterAnimation() == false) goto L65;
                r82.transitionParams.cell = r9;
            L65:
                MessageObject.GroupedMessages.TransitionParams r83 = r82.transitionParams;
                int r92 = r83.top;
                if (r92 == 0) goto L68;
                if (r11 < r92) goto L68;
            L69:
                int r93 = r83.bottom;
                if (r93 == 0) goto L72;
                if (r12 > r93) goto L72;
            L73:
                int r94 = r83.left;
                if (r94 == 0) goto L76;
                if (r73 < r94) goto L76;
            L77:
                int r74 = r83.right;
                if (r74 == 0) goto L80;
                if (r10 <= r74) goto L81;
            L80:
                r83.right = r10;
            L76:
                r83.left = r73;
            L72:
                r83.bottom = r12;
            L68:
                r83.top = r11;
                goto L69
            L48:
                if (r9.willRemovedAfterAnimation() == false) goto L81;
            L44:
                if (r9.getMessageObject().deleted == true) goto L46;
            L41:
                if (r9.getMessageObject().deleted == true) goto L81;
            L37:
                if (r82.transitionParams.drawBackgroundForDeletedItems == true) goto L39;
            L34:
                if (r82.messages.size() != 1) goto L35;
            L81:
                r63 = r63 + 1;
                goto L21
            L82:
                int r52 = r3 ? 1 : 0;
            L84:
                if (r52 >= MessagePreviewView.m11265$$Nest$fgetdrawingGroups(this.this$1.this$0).size()) goto L119;
                MessageObject.GroupedMessages r64 = (MessageObject.GroupedMessages) MessagePreviewView.m11265$$Nest$fgetdrawingGroups(this.this$1.this$0).get(r52);
                if (r64 == null) goto L118;
                float r84 = r64.transitionParams.cell.getNonAnimationTranslationX(r7);
                MessageObject.GroupedMessages.TransitionParams r95 = r64.transitionParams;
                float r102 = (r95.left + r84) + r95.offsetLeft;
                float r112 = r95.top + r95.offsetTop;
                float r122 = (r95.right + r84) + r95.offsetRight;
                float r85 = r95.bottom + r95.offsetBottom;
                if (r95.backgroundChangeBounds == true) goto L92;
                r112 = r112 + r95.cell.getTranslationY();
                r85 = r85 + r64.transitionParams.cell.getTranslationY();
            L92:
                if (r112 >= (-AndroidUtilities.dp(20.0f))) goto L95;
                r112 = -AndroidUtilities.dp(20.0f);
            L95:
                if (r85 <= (this.this$1.chatListView.getMeasuredHeight() + AndroidUtilities.dp(20.0f))) goto L98;
                r85 = this.this$1.chatListView.getMeasuredHeight() + AndroidUtilities.dp(20.0f);
            L98:
                if (r64.transitionParams.cell.getScaleX() == 1.0f) goto L100;
            L103:
                boolean r96 = r7;
            L105:
                if (r96 == false) goto L107;
                r28.save();
                float r16 = 2.0f;
                Canvas r13 = r28;
                r13.scale(r64.transitionParams.cell.getScaleX(), r64.transitionParams.cell.getScaleY(), r102 + ((r122 - r102) / 2.0f), r112 + ((r85 - r112) / 2.0f));
            L108:
                MessageObject.GroupedMessages.TransitionParams r32 = r64.transitionParams;
                r32.cell.drawBackground(r13, (int) r102, (int) r112, (int) r122, (int) r85, r32.pinnedTop, r32.pinnedBotton, false, 0);
                MessageObject.GroupedMessages.TransitionParams r2 = r64.transitionParams;
                r2.cell = null;
                r2.drawCaptionLayout = r64.hasCaption;
                if (r96 == false) goto L118;
                r28.restore();
                int r22 = 0;
            L111:
                if (r22 >= r1) goto L118;
                View r75 = this.this$1.chatListView.getChildAt(r22);
                if ((r75 instanceof ChatMessageCell) == false) goto L117;
                ChatMessageCell r97 = (ChatMessageCell) r75;
                if (r97.getCurrentMessagesGroup() != r64) goto L117;
                int r132 = r97.getLeft();
                int r98 = r97.getTop();
                r75.setPivotX((r102 - r132) + ((r122 - r102) / r16));
                r75.setPivotY((r112 - r98) + ((r85 - r112) / r16));
            L117:
                r22 = r22 + 1;
                goto L111
            L107:
                r16 = 2.0f;
                r13 = r28;
                goto L108
            L100:
                if (r64.transitionParams.cell.getScaleY() != 1.0f) goto L103;
                r96 = r3;
            L118:
                r52 = r52 + 1;
                r3 = false;
                r7 = true;
            L119:
                r42 = r42 + 1;
                r3 = false;
                goto L14
            L18:
                if (this.this$1.chatListView.isFastScrollAnimationRunning() == true) goto L20;
            }

            private /* synthetic */ void lambda$onLayout$0(int r5, int r6) {
                View r0 = this.this$1.getReplyMessageCell();
                if (r0 == null) goto L13;
                int r1 = r0.getTop() + r5;
                int r52 = r0.getTop() + r6;
                int r62 = r52 - r1;
                int r02 = this.this$1.chatListView.getPaddingTop();
                int r2 = this.this$1.chatListView.getHeight() - this.this$1.chatListView.getPaddingBottom();
                if (r62 <= (r2 - r02)) goto L8;
            L7:
                int r12 = r1 - r02;
                if (r12 >= 0) goto L12;
                this.this$1.chatListView.scrollBy(0, r12);
                return;
            L12:
                return;
            L8:
                r1 = (r1 + r52) / 2;
                r02 = (r02 + r2) / 2;
                goto L7
            }

            @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
            public void dispatchDraw(Canvas r5) {
                int r0 = 0;
            L4:
                if (r0 >= getChildCount()) goto L9;
                View r1 = getChildAt(r0);
                if ((r1 instanceof ChatMessageCell) == false) goto L8;
                ((ChatMessageCell) r1).setParentViewSize(this.this$1.chatPreviewContainer.getMeasuredWidth(), this.this$1.chatPreviewContainer.getBackgroundSizeY());
            L8:
                r0 = r0 + 1;
                goto L4
            L9:
                drawChatBackgroundElements(r5);
                super.dispatchDraw(r5);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
            public boolean drawChild(Canvas r3, View r4, long r5) {
                if ((r4 instanceof ChatMessageCell) == false) goto L34;
                ChatMessageCell r0 = (ChatMessageCell) r4;
                boolean r2 = super.drawChild(r3, r4, r5);
                r0.drawCheckBox(r3);
                r3.save();
                r3.translate(r0.getX(), r0.getY());
                r3.save();
                r3.scale(r0.getScaleX(), r0.getScaleY(), r0.getPivotX(), r0.getPivotY());
                r0.drawContent(r3, true);
                r0.layoutTextXY(true);
                r0.drawMessageText(r3);
                if (r0.getCurrentMessagesGroup() != null) goto L7;
            L18:
                r0.drawCaptionLayout(r3, false, r0.getAlpha());
                r0.drawReactionsLayout(r3, r0.getAlpha(), null);
                r0.drawCommentLayout(r3, r0.getAlpha());
            L20:
                if (r0.getCurrentMessagesGroup() == null) goto L22;
            L23:
                r0.drawNamesLayout(r3, r0.getAlpha());
            L25:
                if (r0.getCurrentPosition() == null) goto L29;
                if (r0.getCurrentPosition().last == false) goto L29;
            L30:
                r0.drawTime(r3, r0.getAlpha(), true);
            L31:
                r0.drawOverlays(r3);
                r3.restore();
                r0.getTransitionParams().recordDrawingStatePreview();
                r3.restore();
                return r2;
            L29:
                if (r0.getTransitionParams().animateBackgroundBoundsInner == false) goto L31;
            L22:
                if (r0.getTransitionParams().animateBackgroundBoundsInner == false) goto L25;
            L7:
                if (r0.getCurrentPosition() == null) goto L17;
                if ((r0.getCurrentPosition().flags & r0.captionFlag()) == 0) goto L13;
                if ((r0.getCurrentPosition().flags & 1) != 0) goto L18;
            L13:
                if (r0.getCurrentMessagesGroup() == null) goto L17;
                if (r0.getCurrentMessagesGroup().isDocuments == true) goto L18;
            L17:
                if (r0.getTransitionParams().animateBackgroundBoundsInner == false) goto L20;
            L34:
                return super.drawChild(r3, r4, r5);
            }

            @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
            public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
                if (Page.m11279$$Nest$fgetfirstLayout(this.this$1) == true) goto L5;
            L8:
                super.onLayout(r3, r4, r5, r6, r7);
                Page.m11290$$Nest$mupdatePositions(this.this$1);
                Page.m11283$$Nest$mcheckScroll(this.this$1);
                Page r32 = this.this$1;
                if (r32.shouldScrollToQuote == true) goto L11;
                return;
            L11:
                if (r32.currentTab != 0) goto L15;
                int r42 = r32.scrollToQuoteStartY;
                int r52 = r32.scrollToQuoteEndY;
                r32.shouldScrollToQuote = false;
                post(new MessagePreviewView$Page$6$$ExternalSyntheticLambda0(this, r42, r52));
                return;
            L15:
                return;
            L5:
                if (this.this$1.currentTab == 0) goto L7;
                scrollToPosition(0);
            L7:
                Page.m11281$$Nest$fputfirstLayout(this.this$1, false);
                goto L8
            }

            @Override // androidx.recyclerview.widget.RecyclerView
            public void onScrollStateChanged(int r2) {
                if (r2 != 0) goto L4;
                this.this$1.textSelectionHelper.stopScrolling();
            L4:
                super.onScrollStateChanged(r2);
            }

            @Override // androidx.recyclerview.widget.RecyclerView
            public void onScrolled(int r1, int r2) {
                super.onScrolled(r1, r2);
                this.this$1.textSelectionHelper.onParentScrolled();
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$7, reason: invalid class name */
        public class AnonymousClass7 extends ChatListItemAnimator {
            Runnable finishRunnable;
            int scrollAnimationIndex;
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public static /* synthetic */ void $r8$lambda$Fwwot_Y24Rl1GhF0e3XJDth1Vcg(AnonymousClass7 r0) {
                r0.lambda$onAllAnimationsDone$0();
            }

            /* renamed from: $r8$lambda$lKZeyz5hggLzGPB0h4-7Q07ihto, reason: not valid java name */
            public static /* synthetic */ void m11293$r8$lambda$lKZeyz5hggLzGPB0h47Q07ihto(AnonymousClass7 r0) {
                r0.lambda$onAllAnimationsDone$1();
            }

            public static /* synthetic */ void $r8$lambda$oxeFIPPChnFR32_uozpvqltsFao(AnonymousClass7 r0) {
                r0.lambda$endAnimations$2();
            }

            public AnonymousClass7(Page r1, ChatActivity r2, RecyclerListView r3, Theme.ResourcesProvider r4, MessagePreviewView r5) {
                this.this$1 = r1;
                this.val$this$0 = r5;
                super(r2, r3, r4);
                this.scrollAnimationIndex = -1;
            }

            private /* synthetic */ void lambda$endAnimations$2() {
                if (this.scrollAnimationIndex == (-1)) goto L6;
                NotificationCenter.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$1.this$0)).onAnimationFinish(this.scrollAnimationIndex);
                this.scrollAnimationIndex = -1;
                return;
            }

            private /* synthetic */ void lambda$onAllAnimationsDone$0() {
                if (this.scrollAnimationIndex == (-1)) goto L6;
                NotificationCenter.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$1.this$0)).onAnimationFinish(this.scrollAnimationIndex);
                this.scrollAnimationIndex = -1;
                return;
            }

            private /* synthetic */ void lambda$onAllAnimationsDone$1() {
                Page.m11289$$Nest$mupdateMessages(this.this$1);
            }

            @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
            public void endAnimations() {
                super.endAnimations();
                Runnable r0 = this.finishRunnable;
                if (r0 == null) goto L5;
                AndroidUtilities.cancelRunOnUIThread(r0);
            L5:
                MessagePreviewView$Page$7$$ExternalSyntheticLambda2 r02 = new MessagePreviewView$Page$7$$ExternalSyntheticLambda2(this);
                this.finishRunnable = r02;
                AndroidUtilities.runOnUIThread(r02);
            }

            @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator
            public void onAllAnimationsDone() {
                super.onAllAnimationsDone();
                Runnable r0 = this.finishRunnable;
                if (r0 == null) goto L5;
                AndroidUtilities.cancelRunOnUIThread(r0);
            L5:
                MessagePreviewView$Page$7$$ExternalSyntheticLambda0 r02 = new MessagePreviewView$Page$7$$ExternalSyntheticLambda0(this);
                this.finishRunnable = r02;
                AndroidUtilities.runOnUIThread(r02);
                Page r03 = this.this$1;
                if (r03.updateAfterAnimations == false) goto L9;
                r03.updateAfterAnimations = false;
                AndroidUtilities.runOnUIThread(new MessagePreviewView$Page$7$$ExternalSyntheticLambda1(this));
                return;
            }

            @Override // org.telegram.ui.recyclerview.ChatListItemAnimator
            public void onAnimationStart() {
                super.onAnimationStart();
                AndroidUtilities.cancelRunOnUIThread(this.this$1.this$0.changeBoundsRunnable);
                this.this$1.this$0.changeBoundsRunnable.run();
                if (this.scrollAnimationIndex != (-1)) goto L5;
                this.scrollAnimationIndex = NotificationCenter.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$1.this$0)).setAnimationInProgress(this.scrollAnimationIndex, null, false);
            L5:
                Runnable r0 = this.finishRunnable;
                if (r0 == null) goto L9;
                AndroidUtilities.cancelRunOnUIThread(r0);
                this.finishRunnable = null;
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$8, reason: invalid class name */
        public class AnonymousClass8 extends RecyclerView.OnScrollListener {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass8(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(RecyclerView r2, int r3, int r4) {
                super.onScrolled(r2, r3, r4);
                int r22 = 0;
            L3:
                int r32 = this.this$1.chatListView.getChildCount();
                Page r42 = this.this$1;
                if (r22 >= r32) goto L9;
                View r33 = r42.chatListView.getChildAt(r22);
                if ((r33 instanceof ChatMessageCell) == false) goto L8;
                ((ChatMessageCell) r33).setParentViewSize(this.this$1.chatPreviewContainer.getMeasuredWidth(), this.this$1.chatPreviewContainer.getBackgroundSizeY());
            L8:
                r22 = r22 + 1;
                goto L3
            L9:
                TextSelectionHelper.ChatListTextSelectionHelper r1 = r42.textSelectionHelper;
                if (r1 == null) goto L16;
                r1.invalidate();
                return;
            }
        }

        /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$9, reason: invalid class name */
        public class AnonymousClass9 implements RecyclerListView.OnItemClickListener {
            final /* synthetic */ Page this$1;
            final /* synthetic */ MessagePreviewView val$this$0;

            public AnonymousClass9(Page r1, MessagePreviewView r2) {
                this.this$1 = r1;
                this.val$this$0 = r2;
            }

            @Override // org.telegram.ui.Components.RecyclerListView.OnItemClickListener
            public void onItemClick(View r5, int r6) {
                Page r0 = this.this$1;
                if (r0.currentTab == 1) goto L5;
                return;
            L5:
                if (r0.messages.previewMessages.size() <= 1) goto L17;
                MessageObject r62 = this.this$1.messages.previewMessages.get(r6);
                boolean r02 = this.this$1.messages.isSelected(r62);
                boolean r1 = !r02;
                if (this.this$1.messages.getSelectedCount() != 1) goto L11;
                if (r02 == false) goto L11;
                return;
            L11:
                this.this$1.messages.setSelected(r62, r1);
                if ((r5 instanceof ChatMessageCell) == false) goto L14;
                ((ChatMessageCell) r5).setChecked(r1, r1, true);
            L14:
                Page.m11291$$Nest$mupdateSubtitle(this.this$1, true);
                return;
            }
        }

        public class Adapter extends RecyclerView.Adapter {
            final /* synthetic */ Page this$1;

            /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$Adapter$1, reason: invalid class name */
            public class AnonymousClass1 extends ChatMessageCell {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass1(Adapter r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6) {
                    this.this$2 = r1;
                    super(r2, r3, r4, r5, r6);
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell, org.telegram.ui.Cells.BaseCell, android.view.View
                public void invalidate() {
                    super.invalidate();
                    this.this$2.this$1.chatListView.invalidate();
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
                public void invalidate(int r1, int r2, int r3, int r4) {
                    super.invalidate(r1, r2, r3, r4);
                    this.this$2.this$1.chatListView.invalidate();
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.ViewGroup, android.view.View
                public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
                    super.onLayout(r1, r2, r3, r4, r5);
                    Page.m11288$$Nest$mupdateLinkHighlight(this.this$2.this$1, this);
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell
                public void setMessageObject(MessageObject r3, MessageObject.GroupedMessages r4, boolean r5, boolean r6, boolean r7, boolean r8) {
                    boolean r0 = StickerTime.shouldPreserveOnPreview(getMessageObject(), r3);
                    float r1 = getTimeAlpha();
                    super.setMessageObject(r3, r4, r5, r6, r7, r8);
                    if (r0 == false) goto L8;
                    getTransitionParams().resetAnimation();
                    if (r1 != 0.0f) goto L7;
                    r1 = 1.0f;
                L7:
                    setTimeAlpha(r1);
                L8:
                    Page.m11288$$Nest$mupdateLinkHighlight(this.this$2.this$1, this);
                }
            }

            /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$Adapter$2, reason: invalid class name */
            public class AnonymousClass2 implements ChatMessageCell.ChatMessageCellDelegate {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass2(Adapter r1) {
                    this.this$2 = r1;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public boolean canPerformActions() {
                    Page r2 = this.this$2.this$1;
                    if (r2.currentTab != 2) goto L10;
                    MessagePreviewParams r22 = r2.this$0.messagePreviewParams;
                    if (r22.singleLink == false) goto L7;
                    return false;
                L7:
                    if (r22.isSecret == true) goto L13;
                    return true;
                L13:
                    return false;
                L10:
                    return false;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public void didPressUrl(ChatMessageCell r3, CharacterStyle r4, boolean r5) {
                    Page r52 = this.this$2.this$1;
                    if (r52.currentTab == 2) goto L5;
                    return;
                L5:
                    if (r52.this$0.messagePreviewParams.currentLink != r4) goto L8;
                    return;
                L8:
                    if (r3.getMessageObject() != null) goto L11;
                    return;
                L11:
                    if ((r4 instanceof URLSpan) == false) goto L22;
                    String r53 = ((URLSpan) r4).getURL();
                    MessagePreviewView r0 = this.this$2.this$1.this$0;
                    MessagePreviewParams r1 = r0.messagePreviewParams;
                    r1.currentLink = r4;
                    r1.webpage = null;
                    ChatActivity r42 = r0.chatActivity;
                    if (r42 == null) goto L17;
                    if (r53 == null) goto L17;
                    r42.searchLinks(r53, true);
                L17:
                    Page.m11288$$Nest$mupdateLinkHighlight(this.this$2.this$1, r3);
                    return;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public CharacterStyle getProgressLoadingLink(ChatMessageCell r2) {
                    Page r1 = this.this$2.this$1;
                    if (r1.currentTab != 2) goto L9;
                    MessagePreviewParams r12 = r1.this$0.messagePreviewParams;
                    if (r12.singleLink == false) goto L8;
                    return null;
                L8:
                    return r12.currentLink;
                L9:
                    return null;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public TextSelectionHelper.ChatListTextSelectionHelper getTextSelectionHelper() {
                    return this.this$2.this$1.textSelectionHelper;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public boolean hasSelectedMessages() {
                    return true;
                }

                @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
                public boolean isProgressLoading(ChatMessageCell r3, int r4) {
                    Page r2 = this.this$2.this$1;
                    if (r2.currentTab == 2) goto L5;
                L16:
                    return false;
                L5:
                    if (r4 != 1) goto L16;
                    MessagePreviewParams r22 = r2.this$0.messagePreviewParams;
                    if (r22.singleLink == true) goto L16;
                    TLRPC.WebPage r23 = r22.webpage;
                    if (r23 != null) goto L12;
                L15:
                    return true;
                L12:
                    if ((r23 instanceof TLRPC.TL_webPagePending) == true) goto L15;
                    return false;
                }
            }

            /* renamed from: org.telegram.ui.Components.MessagePreviewView$Page$Adapter$3, reason: invalid class name */
            public class AnonymousClass3 implements ChatMessageCell.ChatMessageCellDelegate {
                final /* synthetic */ Adapter this$2;

                public AnonymousClass3(Adapter r1) {
                    this.this$2 = r1;
                }
            }

            private Adapter(Page r1) {
                this.this$1 = r1;
            }

            public /* synthetic */ Adapter(Page r1, MessagePreviewViewIA r2) {
                this(r1);
            }

            private int offset(ChatMessageCell r10, int r11, boolean r12) {
                if (r10 != null) goto L5;
                return 0;
            L5:
                MessageObject r0 = r10.getMessageObject();
                if (r0 != null) goto L9;
                return 0;
            L9:
                if (r0.getGroupId() == 0) goto L12;
                return 0;
            L12:
                if (TextUtils.isEmpty(r0.caption) == true) goto L16;
                MessageObject.TextLayoutBlocks r1 = r10.captionLayout;
                if (r1 == null) goto L16;
                int r3 = (int) r10.captionY;
                CharSequence r02 = r0.caption;
                ArrayList<MessageObject.TextLayoutBlock> r13 = r1.textLayoutBlocks;
            L20:
                if (r13 == null) goto L39;
                if (r02 == null) goto L39;
                int r03 = 0;
            L25:
                if (r03 >= r13.size()) goto L39;
                MessageObject.TextLayoutBlock r4 = r13.get(r03);
                StaticLayout r5 = r4.textLayout;
                String r6 = r5.getText().toString();
                int r7 = r4.charactersOffset;
                if (r11 > r7) goto L29;
                r03 = r03 + 1;
                goto L25
            L29:
                if ((r11 - r7) <= (r6.length() - 1)) goto L31;
                float r9 = r3 + ((int) ((r4.textYOffset(r13, r10.transitionParams) + r4.padTop) + r4.height));
            L37:
                return (int) r9;
            L31:
                int r92 = r5.getLineForOffset(r11 - r4.charactersOffset);
                if (r12 == false) goto L34;
                int r93 = r5.getLineBottom(r92);
            L35:
                r9 = r93 + ((r3 + r4.textYOffset(r13, r10.transitionParams)) + r4.padTop);
                goto L37
            L34:
                r93 = r5.getLineTop(r92);
            L39:
                return 0;
            L16:
                r10.layoutTextXY(true);
                r3 = r10.textY;
                CharSequence r14 = r0.messageText;
                ArrayList<MessageObject.TextLayoutBlock> r04 = r0.textLayoutBlocks;
                if (r10.linkPreviewAbove == false) goto L19;
                r3 = r3 + (r10.linkPreviewHeight + AndroidUtilities.dp(10.0f));
            L19:
                r13 = r04;
                r02 = r14;
                goto L20
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemCount() {
                MessagePreviewParams.Messages r0 = this.this$1.messages;
                if (r0 != null) goto L7;
                return 0;
            L7:
                return r0.previewMessages.size();
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public int getItemViewType(int r1) {
                return 0;
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onBindViewHolder(RecyclerView.ViewHolder r8, int r9) {
                if (this.this$1.messages != null) goto L6;
                return;
            L6:
                if (r8.getItemViewType() != 0) goto L28;
                ChatMessageCell r0 = (ChatMessageCell) r8.itemView;
                r0.setInvalidateSpoilersParent(this.this$1.messages.hasSpoilers);
                r0.setParentViewSize(this.this$1.chatListView.getMeasuredWidth(), this.this$1.chatListView.getMeasuredHeight());
                boolean r6 = false;
                if (r0.getMessageObject() == null) goto L10;
                int r82 = r0.getMessageObject().getId();
            L11:
                Page r1 = this.this$1;
                if (r1.currentTab != 2) goto L14;
                r1.this$0.messagePreviewParams.checkCurrentLink(r1.messages.previewMessages.get(r9));
            L14:
                MessageObject r12 = this.this$1.messages.previewMessages.get(r9);
                MessagePreviewParams.Messages r2 = this.this$1.messages;
                r0.setMessageObject(r12, r2.groupedMessagesMap.get(r2.previewMessages.get(r9).getGroupId()), true, true, false);
                if (this.this$1.currentTab != 1) goto L18;
                r0.setDelegate(new AnonymousClass3(this));
            L18:
                if (this.this$1.messages.previewMessages.size() > 1) goto L20;
                return;
            L20:
                if (this.this$1.currentTab != 1) goto L22;
                boolean r13 = true;
            L23:
                r0.setCheckBoxVisible(r13, false);
                if (r82 != this.this$1.messages.previewMessages.get(r9).getId()) goto L26;
                r6 = true;
            L26:
                MessagePreviewParams.Messages r7 = this.this$1.messages;
                boolean r72 = r7.isSelected(r7.previewMessages.get(r9));
                r0.setChecked(r72, r72, r6);
                return;
            L22:
                r13 = false;
                goto L23
            L10:
                r82 = 0;
                goto L11
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
                Context r2 = r8.getContext();
                int r3 = MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$1.this$0);
                Page r82 = this.this$1;
                AnonymousClass1 r0 = new AnonymousClass1(this, r2, r3, false, r82.sharedResources, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r82.this$0));
                r0.setClipChildren(false);
                r0.setClipToPadding(false);
                r0.setDelegate(new AnonymousClass2(this));
                return new RecyclerListView.Holder(r0);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.Adapter
            public void onViewAttachedToWindow(RecyclerView.ViewHolder r6) {
                Page r0 = this.this$1;
                if (r0.messages == null) goto L33;
                int r1 = r0.currentTab;
                if (r1 == 1) goto L40;
                View r62 = r6.itemView;
                if ((r62 instanceof ChatMessageCell) == false) goto L34;
                ChatMessageCell r63 = (ChatMessageCell) r62;
                if (r1 != 0) goto L31;
                MessageObject.GroupedMessages r02 = Page.m11284$$Nest$mgetValidGroupedMessage(r0, r63.getMessageObject());
                if (r02 != null) goto L14;
                boolean r12 = true;
            L15:
                r63.setDrawSelectionBackground(r12);
                if (r02 != null) goto L18;
                boolean r03 = true;
            L19:
                r63.setChecked(true, r03, false);
                Page r04 = this.this$1;
                MessagePreviewParams r13 = r04.this$0.messagePreviewParams;
                if (r13.isSecret == false) goto L22;
                return;
            L22:
                if (r13.quote != null) goto L24;
                return;
            L24:
                if (r04.isReplyMessageCell(r63) == true) goto L26;
                return;
            L26:
                if (this.this$1.textSelectionHelper.isInSelectionMode() == true) goto L38;
                Page r05 = this.this$1;
                TextSelectionHelper.ChatListTextSelectionHelper r14 = r05.textSelectionHelper;
                MessagePreviewParams r06 = r05.this$0.messagePreviewParams;
                r14.select(r63, r06.quoteStart, r06.quoteEnd);
                if (Page.m11278$$Nest$fgetfirstAttach(this.this$1) == false) goto L39;
                Page r07 = this.this$1;
                r07.scrollToQuoteStartY = offset(r63, r07.this$0.messagePreviewParams.quoteStart, false);
                Page r08 = this.this$1;
                r08.scrollToQuoteEndY = offset(r63, r08.this$0.messagePreviewParams.quoteEnd, true);
                Page r5 = this.this$1;
                r5.shouldScrollToQuote = true;
                Page.m11280$$Nest$fputfirstAttach(r5, false);
                return;
            L39:
                return;
            L38:
                return;
            L18:
                r03 = false;
                goto L19
            L14:
                r12 = false;
                goto L15
            L31:
                r63.setDrawSelectionBackground(false);
                return;
            L34:
                return;
            L40:
                return;
            }
        }

        public static /* synthetic */ void $r8$lambda$5BiSygdRnIBrK6CYoxw9VrDYzas(Page r0, Context r1) {
            r0.lambda$new$15(r1);
        }

        public static /* synthetic */ void $r8$lambda$5IbvCWETYCRqDV0lxoHbsq1mpmI(Page r0, View r1) {
            r0.lambda$new$20(r1);
        }

        /* renamed from: $r8$lambda$5XL6oa3j_10-cQUxrwJrjBnOBlU, reason: not valid java name */
        public static /* synthetic */ void m11269$r8$lambda$5XL6oa3j_10cQUxrwJrjBnOBlU(Page r0, View r1) {
            r0.lambda$new$11(r1);
        }

        /* renamed from: $r8$lambda$8zRfXYdFmQUWfHd8YeH2KQl-1WA, reason: not valid java name */
        public static /* synthetic */ void m11270$r8$lambda$8zRfXYdFmQUWfHd8YeH2KQl1WA(Page r0, View r1) {
            r0.lambda$new$13(r1);
        }

        /* renamed from: $r8$lambda$Dfo3ePPTpsMfw-XmSyV39WuHJW4, reason: not valid java name */
        public static /* synthetic */ void m11271$r8$lambda$Dfo3ePPTpsMfwXmSyV39WuHJW4(Page r0, View r1) {
            r0.lambda$new$6(r1);
        }

        public static /* synthetic */ void $r8$lambda$FH5FLmMybP6raRI_ky1TNZH2Njs(Page r0, View r1) {
            r0.lambda$new$3(r1);
        }

        /* renamed from: $r8$lambda$FfH1fdqLKn-cRegA96DUQ8QmXRk, reason: not valid java name */
        public static /* synthetic */ void m11272$r8$lambda$FfH1fdqLKncRegA96DUQ8QmXRk(Page r0, View r1) {
            r0.lambda$new$10(r1);
        }

        public static /* synthetic */ void $r8$lambda$GKvvOIjh2fU5CDNfsbnY4qg0lss(Page r0, View r1) {
            r0.lambda$onAttachedToWindow$23(r1);
        }

        public static /* synthetic */ void $r8$lambda$HsJyMRgBpZZoLc6q7UocJNNVeNw(Page r0, View r1) {
            r0.lambda$new$7(r1);
        }

        public static /* synthetic */ void $r8$lambda$N21477nH6lP011esu_pomKKJFio(Page r0, View r1) {
            r0.lambda$new$21(r1);
        }

        public static /* synthetic */ void $r8$lambda$NF33m209Z2l9W5b0oXwEw22vMio(Page r0, int r1, float r2, ValueAnimator r3) {
            r0.lambda$updatePositions$24(r1, r2, r3);
        }

        /* renamed from: $r8$lambda$P9oEW-hW-ImJeaju6K7OChfPYM4, reason: not valid java name */
        public static /* synthetic */ void m11273$r8$lambda$P9oEWhWImJeaju6K7OChfPYM4(Page r0, boolean r1, Context r2, ToggleButton r3, ToggleButton r4, View r5) {
            r0.lambda$new$16(r1, r2, r3, r4, r5);
        }

        public static /* synthetic */ void $r8$lambda$QMIl1XabvuCwxTjAO8ZLYm4kEBU(Page r0, View r1) {
            r0.lambda$new$8(r1);
        }

        public static /* synthetic */ void $r8$lambda$RRL1djlyeVXM15AaNFFBA1uTM8Y(Page r0, View r1) {
            r0.lambda$new$12(r1);
        }

        public static /* synthetic */ void $r8$lambda$VAJS2wPDSG3tptxaLKZbfrvCchQ(Page r0, Context r1) {
            r0.lambda$new$14(r1);
        }

        public static /* synthetic */ void $r8$lambda$ZDVrabV2xRoWkxDWcGzm7lzgA4c(Page r0, View r1) {
            r0.lambda$new$18(r1);
        }

        /* renamed from: $r8$lambda$hL8To22AlpgutFQZyBa-eb39I3s, reason: not valid java name */
        public static /* synthetic */ void m11274$r8$lambda$hL8To22AlpgutFQZyBaeb39I3s(Page r0, ToggleButton r1, ToggleButton r2, View r3) {
            r0.lambda$new$17(r1, r2, r3);
        }

        public static /* synthetic */ boolean $r8$lambda$iBqXjPxEwhYMYDaBPH6jnrQRyDY(Page r0, View r1, MotionEvent r2) {
            return r0.lambda$new$0(r1, r2);
        }

        /* renamed from: $r8$lambda$q64O-yqcDOD49irK6lnCchRR-XM, reason: not valid java name */
        public static /* synthetic */ void m11275$r8$lambda$q64OyqcDOD49irK6lnCchRRXM(Page r0) {
            r0.lambda$new$1();
        }

        /* renamed from: $r8$lambda$rxjAr5QB8I-DEy3iNuHiaSSF2GE, reason: not valid java name */
        public static /* synthetic */ void m11276$r8$lambda$rxjAr5QB8IDEy3iNuHiaSSF2GE(Page r0, View r1) {
            r0.lambda$new$19(r1);
        }

        public static /* synthetic */ void $r8$lambda$uOzZiIfJg360gHdtv8QMmuansEk(Page r0, View r1) {
            r0.lambda$new$4(r1);
        }

        /* renamed from: $r8$lambda$v_VhxOpkmiwU3-yXILhW92a1VJ8, reason: not valid java name */
        public static /* synthetic */ void m11277$r8$lambda$v_VhxOpkmiwU3yXILhW92a1VJ8(Page r0) {
            r0.lambda$checkScroll$22();
        }

        public static /* synthetic */ void $r8$lambda$wKUndCKnPNFriKb61yKYI5QxKUA(Page r0, View r1) {
            r0.lambda$new$2(r1);
        }

        public static /* synthetic */ void $r8$lambda$ylsICbi4slORlmXk3rx2lUBPeNs(Page r0, View r1) {
            r0.lambda$new$5(r1);
        }

        public static /* synthetic */ void $r8$lambda$zUH0Yxba5dhz_5hx2U16DJ0Ucio(Page r0, View r1) {
            r0.lambda$new$9(r1);
        }

        /* renamed from: -$$Nest$fgetfirstAttach, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11278$$Nest$fgetfirstAttach(Page r0) {
            return r0.firstAttach;
        }

        /* renamed from: -$$Nest$fgetfirstLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ boolean m11279$$Nest$fgetfirstLayout(Page r0) {
            return r0.firstLayout;
        }

        /* renamed from: -$$Nest$fputfirstAttach, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11280$$Nest$fputfirstAttach(Page r0, boolean r1) {
            r0.firstAttach = r1;
        }

        /* renamed from: -$$Nest$fputfirstLayout, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11281$$Nest$fputfirstLayout(Page r0, boolean r1) {
            r0.firstLayout = r1;
        }

        /* renamed from: -$$Nest$fputquoteSwitcher, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11282$$Nest$fputquoteSwitcher(Page r0, AnimatorSet r1) {
            r0.quoteSwitcher = r1;
        }

        /* renamed from: -$$Nest$mcheckScroll, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11283$$Nest$mcheckScroll(Page r0) {
            r0.checkScroll();
        }

        /* renamed from: -$$Nest$mgetValidGroupedMessage, reason: not valid java name */
        public static /* bridge */ /* synthetic */ MessageObject.GroupedMessages m11284$$Nest$mgetValidGroupedMessage(Page r0, MessageObject r1) {
            return r0.getValidGroupedMessage(r1);
        }

        /* renamed from: -$$Nest$msetOffset, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11285$$Nest$msetOffset(Page r0, float r1, int r2) {
            r0.setOffset(r1, r2);
        }

        /* renamed from: -$$Nest$mshowQuoteLengthError, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11286$$Nest$mshowQuoteLengthError(Page r0) {
            r0.showQuoteLengthError();
        }

        /* renamed from: -$$Nest$mswitchToQuote, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11287$$Nest$mswitchToQuote(Page r0, boolean r1, boolean r2) {
            r0.switchToQuote(r1, r2);
        }

        /* renamed from: -$$Nest$mupdateLinkHighlight, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11288$$Nest$mupdateLinkHighlight(Page r0, ChatMessageCell r1) {
            r0.updateLinkHighlight(r1);
        }

        /* renamed from: -$$Nest$mupdateMessages, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11289$$Nest$mupdateMessages(Page r0) {
            r0.updateMessages();
        }

        /* renamed from: -$$Nest$mupdatePositions, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11290$$Nest$mupdatePositions(Page r0) {
            r0.updatePositions();
        }

        /* renamed from: -$$Nest$mupdateSubtitle, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m11291$$Nest$mupdateSubtitle(Page r0, boolean r1) {
            r0.updateSubtitle(r1);
        }

        public Page(MessagePreviewView r24, Context r25, int r26) {
            this.this$0 = r24;
            super(r25);
            this.firstLayout = true;
            this.scrollToQuoteStartY = -1;
            this.scrollToQuoteEndY = -1;
            this.shouldScrollToQuote = false;
            this.rect = new Rect();
            this.updateScroll = false;
            this.firstAttach = true;
            this.sharedResources = new ChatMessageSharedResources(r25);
            this.currentTab = r26;
            setOnTouchListener(new MessagePreviewView$Page$$ExternalSyntheticLambda0(this));
            AnonymousClass2 r0 = new AnonymousClass2(this, r25, r24);
            this.chatPreviewContainer = r0;
            r0.setBackgroundImage(MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24).getWallpaperDrawable(), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24).isWallpaperMotion());
            this.chatPreviewContainer.setOccupyStatusBar(false);
            this.chatPreviewContainer.setOutlineProvider(new AnonymousClass3(this, r24));
            this.chatPreviewContainer.setClipToOutline(true);
            this.chatPreviewContainer.setElevation(AndroidUtilities.dp(4.0f));
            ActionBar r02 = new ActionBar(r24, r25, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24));
            this.actionBar = r02;
            r02.setBackgroundColor(MessagePreviewView.m11268$$Nest$mgetThemedColor(r24, Theme.key_actionBarDefault));
            AnonymousClass4 r03 = new AnonymousClass4(this, r24);
            this.textSelectionHelper = r03;
            r03.setCallback(new AnonymousClass5(this, r24));
            AnonymousClass6 r14 = new AnonymousClass6(this, r25, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24), r24);
            this.chatListView = r14;
            AnonymousClass7 r04 = new AnonymousClass7(this, null, this.chatListView, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24), r24);
            this.itemAnimator = r04;
            r14.setItemAnimator(r04);
            this.chatListView.setOnScrollListener(new AnonymousClass8(this, r24));
            this.chatListView.setOnItemClickListener(new AnonymousClass9(this, r24));
            RecyclerListView r05 = this.chatListView;
            Adapter r2 = new Adapter(this, null);
            this.adapter = r2;
            r05.setAdapter(r2);
            this.chatListView.setPadding(0, AndroidUtilities.dp(4.0f), 0, AndroidUtilities.dp(4.0f));
            AnonymousClass10 r06 = new AnonymousClass10(this, r25, 1000, 1, true, r24);
            Context r1 = r25;
            this.chatLayoutManager = r06;
            r06.setSpanSizeLookup(new AnonymousClass11(this, r24));
            this.chatListView.setClipToPadding(false);
            this.chatListView.setLayoutManager(this.chatLayoutManager);
            this.chatListView.addItemDecoration(new AnonymousClass12(this, r24));
            this.chatPreviewContainer.addView(this.chatListView);
            addView(this.chatPreviewContainer, LayoutHelper.createFrame(-1, 400.0f, 0, 8.0f, 0.0f, 8.0f, 0.0f));
            this.chatPreviewContainer.addView(this.actionBar, LayoutHelper.createFrame(-1, -2.0f));
            ActionBarPopupWindow.ActionBarPopupWindowLayout r07 = new ActionBarPopupWindow.ActionBarPopupWindowLayout(getContext(), R.drawable.popup_fixed_alert2, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24), 1);
            this.menu = r07;
            r07.getSwipeBack().setOnForegroundOpenFinished(new MessagePreviewView$Page$$ExternalSyntheticLambda11(this));
            this.menu.setBackground(MessagePreviewView.m11266$$Nest$fgetiBlur3Factory(r24).create(this.menu).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24))).setPadding(AndroidUtilities.dp(8.0f)).setHasPadding(true).setRadius(AndroidUtilities.dp(12.0f)));
            addView(this.menu, LayoutHelper.createFrame(-2, -2.0f));
            if (r26 != 0) goto L41;
            MessagePreviewParams r5 = r24.messagePreviewParams;
            MessagePreviewParams.Messages r08 = r5.replyMessage;
            if (r08 == null) goto L41;
            if (r08.hasText == true) goto L9;
        L19:
            Page r7 = this;
            MessagePreviewView r6 = r24;
            float r15 = 0.06f;
            int r8 = 48;
            float r21 = 8.0f;
            int r13 = 8;
        L20:
            MessagePreviewParams r09 = r6.messagePreviewParams;
            if (r09.monoforum == false) goto L23;
        L27:
            MessagePreviewParams r010 = r6.messagePreviewParams;
            if (r010.noforwards == true) goto L33;
            if (r010.hasSecretMessages == true) goto L33;
            ActionBarPopupWindow.GapView r011 = new ActionBarPopupWindow.GapView(r1, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r011.setColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6)), r15));
            r011.setTag(R.id.fit_width_tag, 1);
            r7.menu.addView(r011, LayoutHelper.createLinear(-1, r13));
        L33:
            if (r6.messagePreviewParams.quote == null) goto L35;
            boolean r012 = true;
        L36:
            r7.switchToQuote(r012, false);
            ActionBarMenuSubItem r013 = new ActionBarMenuSubItem(r1, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r013.setTextAndIcon(LocaleController.getString(R.string.ApplyChanges), R.drawable.msg_select);
            r013.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda17(r7));
            r7.menu.addView(r013, LayoutHelper.createLinear(-1, r8));
            ActionBarMenuSubItem r014 = new ActionBarMenuSubItem(r25, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r014.setTextAndIcon(LocaleController.getString(R.string.ViewInChat), R.drawable.msg_view_file);
            r014.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda18(r7));
            r7.menu.addView(r014, LayoutHelper.createLinear(-1, r8));
            ActionBarMenuSubItem r015 = new ActionBarMenuSubItem(r25, true, false, true, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r7.deleteReplyButton = r015;
            if (r6.showOutdatedQuote == false) goto L39;
            int r12 = R.string.DoNotQuote;
        L40:
            r015.setTextAndIcon(LocaleController.getString(r12), R.drawable.msg_delete);
            ActionBarMenuSubItem r016 = r7.deleteReplyButton;
            int r16 = MessagePreviewView.m11268$$Nest$mgetThemedColor(r6, Theme.key_text_RedBold);
            int r22 = Theme.key_text_RedRegular;
            r016.setColors(r16, MessagePreviewView.m11268$$Nest$mgetThemedColor(r6, r22));
            r7.deleteReplyButton.setSelectorColor(Theme.multAlpha(Theme.getColor(r22), 0.12f));
            r7.deleteReplyButton.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda19(r7));
            r7.menu.addView(r7.deleteReplyButton, LayoutHelper.createLinear(-1, r8));
            Context r17 = r25;
            MessagePreviewView r132 = r6;
        L96:
            int r017 = r7.currentTab;
            if (r017 != 1) goto L99;
            r7.messages = r132.messagePreviewParams.forwardMessages;
        L104:
            TextSelectionHelper<Cell>.TextSelectionOverlay r018 = r7.textSelectionHelper.getOverlayView(r17);
            r7.textSelectionOverlay = r018;
            r018.setElevation(AndroidUtilities.dp(r21));
            r7.textSelectionOverlay.setOutlineProvider(null);
            View r019 = r7.textSelectionOverlay;
            if (r019 != null) goto L107;
        L110:
            r7.textSelectionHelper.setParentView(r7.chatListView);
            return;
        L107:
            if ((r019.getParent() instanceof ViewGroup) == false) goto L109;
            ((ViewGroup) r7.textSelectionOverlay.getParent()).removeView(r7.textSelectionOverlay);
        L109:
            r7.addView(r7.textSelectionOverlay, LayoutHelper.createFrame(-1, -1.0f, 51, 0.0f, org.telegram.ui.ActionBar.ActionBar.getCurrentActionBarHeight() / AndroidUtilities.density, 0.0f, 0.0f));
            goto L110
        L99:
            if (r017 != 0) goto L102;
            r7.messages = r132.messagePreviewParams.replyMessage;
            goto L104
        L102:
            if (r017 != 2) goto L104;
            r7.messages = r132.messagePreviewParams.linkMessage;
            goto L104
        L39:
            r12 = R.string.DoNotReply;
            goto L40
        L35:
            r012 = false;
            goto L36
        L23:
            if (r09.noforwards == true) goto L27;
            if (r09.hasSecretMessages == true) goto L27;
            FrameLayout r142 = new FrameLayout(r1);
            ActionBarMenuSubItem r020 = new ActionBarMenuSubItem(r1, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r7.replyAnotherChatButton = r020;
            r020.setTextAndIcon(LocaleController.getString(R.string.ReplyToAnotherChat), R.drawable.msg_forward_replace);
            r7.replyAnotherChatButton.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda15(r7));
            r1 = r25;
            ActionBarMenuSubItem r021 = new ActionBarMenuSubItem(r1, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r6));
            r7.quoteAnotherChatButton = r021;
            r021.setTextAndIcon(LocaleController.getString(R.string.QuoteToAnotherChat), R.drawable.msg_forward_replace);
            r7.quoteAnotherChatButton.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda16(r7));
            r142.addView(r7.quoteAnotherChatButton, LayoutHelper.createFrame(-1, 48.0f));
            r142.addView(r7.replyAnotherChatButton, LayoutHelper.createFrame(-1, 48.0f));
            r7.menu.addView(r142, LayoutHelper.createLinear(-1, r8));
            goto L27
        L9:
            if (r5.isSecret == true) goto L19;
            LinearLayout r022 = new LinearLayout(r1);
            r022.setOrientation(1);
            if (r24.showOutdatedQuote == true) goto L13;
            r21 = 8.0f;
            r8 = 48;
            ViewGroup r143 = r022;
            r15 = 0.06f;
            r13 = 8;
            ActionBarMenuSubItem r023 = new ActionBarMenuSubItem(r1, false, true, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24));
            r023.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
            r023.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda12(this));
            r143.addView(r023, LayoutHelper.createLinear(-1, 48));
            ActionBarPopupWindow.GapView r024 = new ActionBarPopupWindow.GapView(r1, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24));
            r024.setColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24)), 0.06f));
            r024.setTag(R.id.fit_width_tag, 1);
            r143.addView(r024, LayoutHelper.createLinear(-1, 8));
            ActionBarMenuSubItem r025 = new ActionBarMenuSubItem(r1, false, false, true, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24));
            r025.setTextAndIcon(LocaleController.getString(R.string.QuoteSelectedPart), R.drawable.menu_quote_specific);
            r025.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda13(this));
            r143.addView(r025, LayoutHelper.createLinear(-1, 48));
        L14:
            this.menuBack = this.menu.addViewToSwipeBack(r143);
            this.menu.getSwipeBack().setStickToRight(true);
            FrameLayout r144 = new FrameLayout(r1);
            AnonymousClass13 r026 = new AnonymousClass13(this, r1, true, true, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24), r24);
            this.quoteButton = r026;
            if (r24.showOutdatedQuote == false) goto L17;
            int r23 = R.string.QuoteSelectedPart;
        L18:
            r026.setTextAndIcon(LocaleController.getString(r23), R.drawable.menu_select_quote);
            AnonymousClass14 r027 = new AnonymousClass14(this, r25, true, true, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24), r24);
            r6 = r24;
            r7 = this;
            r1 = r25;
            r7.clearQuoteButton = r027;
            r027.setTextAndIcon(LocaleController.getString(R.string.ClearQuote), R.drawable.menu_quote_delete);
            r144.setBackground(Theme.createRadSelectorDrawable(MessagePreviewView.m11268$$Nest$mgetThemedColor(r6, Theme.key_dialogButtonSelector), 6, 0));
            r144.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda14(r7));
            r144.addView(r7.quoteButton, LayoutHelper.createFrame(-1, 48.0f));
            r144.addView(r7.clearQuoteButton, LayoutHelper.createFrame(-1, 48.0f));
            r7.menu.addView(r144, LayoutHelper.createLinear(-1, r8));
            goto L20
        L17:
            r23 = R.string.SelectSpecificQuote;
            goto L18
        L13:
            r143 = r022;
            r15 = 0.06f;
            r8 = 48;
            r21 = 8.0f;
            r13 = 8;
        L41:
            r7 = this;
            r21 = 8.0f;
            if (r26 == 1) goto L44;
        L73:
            r132 = r24;
            if (r26 == 2) goto L76;
        L72:
            r17 = r25;
            goto L96
        L76:
            if (r132.messagePreviewParams.linkMessage == null) goto L72;
            ToggleButton r028 = new ToggleButton(r25, R.raw.position_below, LocaleController.getString(R.string.LinkAbove), R.raw.position_above, LocaleController.getString(R.string.LinkBelow), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r7.changePositionBtn = r028;
            r028.setState(!r132.messagePreviewParams.webpageTop, false);
            r7.menu.addView(r7.changePositionBtn, LayoutHelper.createLinear(-1, 48));
            FrameLayout r029 = new FrameLayout(r25);
            r7.changeSizeBtnContainer = r029;
            r029.setBackground(Theme.createRadSelectorDrawable(MessagePreviewView.m11268$$Nest$mgetThemedColor(r132, Theme.key_dialogButtonSelector), 0, 0));
            ToggleButton r030 = new ToggleButton(r25, R.raw.media_shrink, LocaleController.getString(R.string.LinkMediaLarger), R.raw.media_enlarge, LocaleController.getString(R.string.LinkMediaSmaller), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r7.changeSizeBtn = r030;
            r030.setBackground(null);
            ToggleButton r031 = r7.changeSizeBtn;
            int r145 = 4;
            if (r132.messagePreviewParams.isVideo == false) goto L80;
            int r18 = 4;
        L81:
            r031.setVisibility(r18);
            r7.changeSizeBtnContainer.addView(r7.changeSizeBtn, LayoutHelper.createLinear(-1, 48));
            ToggleButton r032 = new ToggleButton(r25, R.raw.media_shrink, LocaleController.getString(R.string.LinkVideoLarger), R.raw.media_enlarge, LocaleController.getString(R.string.LinkVideoSmaller), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r7.videoChangeSizeBtn = r032;
            r032.setBackground(null);
            ToggleButton r033 = r7.videoChangeSizeBtn;
            if (r132.messagePreviewParams.isVideo == false) goto L85;
            r145 = 0;
        L85:
            r033.setVisibility(r145);
            FrameLayout r034 = r7.changeSizeBtnContainer;
            if (r132.messagePreviewParams.hasMedia == false) goto L88;
            float r27 = 1.0f;
        L89:
            r034.setAlpha(r27);
            r7.changeSizeBtnContainer.addView(r7.videoChangeSizeBtn, LayoutHelper.createLinear(-1, 48));
            r7.menu.addView(r7.changeSizeBtnContainer, LayoutHelper.createLinear(-1, 48));
            FrameLayout r035 = r7.changeSizeBtnContainer;
            MessagePreviewParams r28 = r132.messagePreviewParams;
            if (r28.singleLink == true) goto L92;
        L94:
            int r3 = 0;
        L95:
            r035.setVisibility(r3);
            r7.changeSizeBtn.setState(r132.messagePreviewParams.webpageSmall, false);
            r7.videoChangeSizeBtn.setState(r132.messagePreviewParams.webpageSmall, false);
            ActionBarPopupWindow.GapView r036 = new ActionBarPopupWindow.GapView(r25, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r036.setColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132)), 0.06f));
            r036.setTag(R.id.fit_width_tag, 1);
            r7.menu.addView(r036, LayoutHelper.createLinear(-1, 8));
            ActionBarMenuSubItem r037 = new ActionBarMenuSubItem(r25, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r037.setTextAndIcon(LocaleController.getString(R.string.ApplyChanges), R.drawable.msg_select);
            r037.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda7(r7));
            r7.menu.addView(r037, LayoutHelper.createLinear(-1, 48));
            r17 = r25;
            ActionBarMenuSubItem r038 = new ActionBarMenuSubItem(r17, true, false, true, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r038.setTextAndIcon(LocaleController.getString(R.string.DoNotLinkPreview), R.drawable.msg_delete);
            int r29 = MessagePreviewView.m11268$$Nest$mgetThemedColor(r132, Theme.key_text_RedBold);
            int r32 = Theme.key_text_RedRegular;
            r038.setColors(r29, MessagePreviewView.m11268$$Nest$mgetThemedColor(r132, r32));
            r038.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda8(r7));
            r038.setSelectorColor(Theme.multAlpha(Theme.getColor(r32), 0.12f));
            r7.menu.addView(r038, LayoutHelper.createLinear(-1, 48));
            r7.changeSizeBtnContainer.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda9(r7));
            r7.changePositionBtn.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda10(r7));
            goto L96
        L92:
            if (r28.hasMedia == true) goto L94;
            r3 = 8;
            goto L95
        L88:
            r27 = 0.5f;
            goto L89
        L80:
            r18 = 0;
            goto L81
        L44:
            if (r24.messagePreviewParams.forwardMessages == null) goto L73;
            if (UserConfig.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(r24)).isPremium() == true) goto L54;
            int r039 = 0;
        L49:
            if (r039 >= r24.messagePreviewParams.forwardMessages.messages.size()) goto L54;
            if (r24.messagePreviewParams.forwardMessages.messages.get(r039).type == 36) goto L52;
            r039 = r039 + 1;
            goto L49
        L52:
            boolean r146 = false;
        L55:
            int r210 = R.raw.name_hide;
            if (r24.messagePreviewParams.multipleUsers == false) goto L59;
            int r19 = R.string.ShowSenderNames;
        L58:
            String r110 = LocaleController.getString(r19);
            int r4 = R.raw.name_show;
            if (r24.messagePreviewParams.multipleUsers == false) goto L64;
            int r111 = R.string.HideSenderNames;
        L65:
            r132 = r24;
            ToggleButton r040 = new ToggleButton(r25, r210, r110, r4, LocaleController.getString(r111), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r24));
            r7.menu.addView(r040, LayoutHelper.createLinear(-1, 48));
            if (r132.messagePreviewParams.hasCaption == false) goto L68;
            ToggleButton r222 = r040;
            Context r112 = r25;
            ToggleButton r041 = new ToggleButton(r112, R.raw.caption_hide, LocaleController.getString(R.string.ShowCaption), R.raw.caption_show, LocaleController.getString(R.string.HideCaption), MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r041.setState(r132.messagePreviewParams.hideCaption, false);
            r7.menu.addView(r041, LayoutHelper.createLinear(-1, 48));
            ToggleButton r62 = r041;
        L69:
            ActionBarMenuSubItem r042 = new ActionBarMenuSubItem(r112, true, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r042.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda1(r7));
            r042.setTextAndIcon(LocaleController.getString(R.string.ChangeRecipient), R.drawable.msg_forward_replace);
            r7.menu.addView(r042, LayoutHelper.createLinear(-1, 48));
            ActionBarPopupWindow.GapView r043 = new ActionBarPopupWindow.GapView(r112, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r043.setColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132)), 0.06f));
            r043.setTag(R.id.fit_width_tag, 1);
            r7.menu.addView(r043, LayoutHelper.createLinear(-1, 8));
            ActionBarMenuSubItem r044 = new ActionBarMenuSubItem(r112, true, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r044.setTextAndIcon(LocaleController.getString(R.string.ApplyChanges), R.drawable.msg_select);
            r044.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda2(r7));
            r7.menu.addView(r044, LayoutHelper.createLinear(-1, 48));
            ActionBarMenuSubItem r045 = new ActionBarMenuSubItem(r112, false, false, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r045.setTextAndIcon(LocaleController.getString(R.string.ForwardSendMessages), R.drawable.msg_send);
            r7.menu.addView(r045, LayoutHelper.createLinear(-1, 48));
            r045.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda3(r7));
            ActionBarMenuSubItem r046 = new ActionBarMenuSubItem(r112, true, false, true, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r132));
            r046.setTextAndIcon(LocaleController.getString(R.string.DoNotForward), R.drawable.msg_delete);
            int r113 = MessagePreviewView.m11268$$Nest$mgetThemedColor(r132, Theme.key_text_RedBold);
            int r211 = Theme.key_text_RedRegular;
            r046.setColors(r113, MessagePreviewView.m11268$$Nest$mgetThemedColor(r132, r211));
            r046.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda4(r7));
            r046.setSelectorColor(Theme.multAlpha(Theme.getColor(r211), 0.12f));
            r7.menu.addView(r046, LayoutHelper.createLinear(-1, 48));
            ToggleButton r52 = r222;
            r52.setState(r132.messagePreviewParams.hideForwardSendersName, false);
            ToggleButton r42 = r62;
            r52.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda5(r7, r146, r25, r42, r52));
            if (r42 == null) goto L72;
            r42.setOnClickListener(new MessagePreviewView$Page$$ExternalSyntheticLambda6(r7, r42, r52));
            goto L72
        L68:
            r112 = r25;
            r222 = r040;
            r62 = null;
            goto L69
        L64:
            r111 = R.string.HideSendersName;
            goto L65
        L59:
            r19 = R.string.ShowSendersName;
        L54:
            r146 = true;
            goto L55
        }

        private void checkScroll() {
            if (this.updateScroll == true) goto L6;
            return;
        L6:
            if (this.chatListView.computeVerticalScrollRange() <= this.chatListView.computeVerticalScrollExtent()) goto L8;
            postDelayed(new MessagePreviewView$Page$$ExternalSyntheticLambda24(this), 0);
        L8:
            this.updateScroll = false;
        }

        private MessageObject.GroupedMessages getValidGroupedMessage(MessageObject r5) {
            if (r5.getGroupId() == 0) goto L12;
            MessageObject.GroupedMessages r4 = this.messages.groupedMessagesMap.get(r5.getGroupId());
            if (r4 != null) goto L7;
        L11:
            return r4;
        L7:
            if (r4.messages.size() > 1) goto L9;
        L10:
            return null;
        L9:
            if (r4.getPosition(r5) != null) goto L11;
        L12:
            return null;
        }

        private /* synthetic */ void lambda$checkScroll$22() {
            boolean r0 = this.this$0.messagePreviewParams.webpageTop;
            RecyclerListView r1 = this.chatListView;
            if (r0 == false) goto L6;
            r1.smoothScrollBy(0, -r1.computeVerticalScrollOffset(), 250, ChatListItemAnimator.DEFAULT_INTERPOLATOR);
            return;
        L6:
            r1.smoothScrollBy(0, r1.computeVerticalScrollRange() - (this.chatListView.computeVerticalScrollOffset() + this.chatListView.computeVerticalScrollExtent()), 250, ChatListItemAnimator.DEFAULT_INTERPOLATOR);
        }

        private /* synthetic */ boolean lambda$new$0(View r1, MotionEvent r2) {
            if (r2.getAction() != 1) goto L5;
            this.this$0.dismiss(true);
        L5:
            return true;
        }

        private /* synthetic */ void lambda$new$1() {
            switchToQuote(true, false);
        }

        private /* synthetic */ void lambda$new$10(View r1) {
            this.this$0.selectAnotherChat(true);
        }

        private /* synthetic */ void lambda$new$11(View r1) {
            this.this$0.dismiss(true);
        }

        private /* synthetic */ void lambda$new$12(View r1) {
            this.this$0.didSendPressed();
        }

        private /* synthetic */ void lambda$new$13(View r1) {
            this.this$0.removeForward();
        }

        private /* synthetic */ void lambda$new$14(Context r4) {
            if (AndroidUtilities.isContextSafe(r4) == true) goto L5;
            return;
        L5:
            new PremiumFeatureBottomSheet(r4, 43, true, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(this.this$0)).show();
        }

        private /* synthetic */ void lambda$new$15(Context r3) {
            this.this$0.dismiss(false);
            AndroidUtilities.runOnUIThread(new MessagePreviewView$Page$$ExternalSyntheticLambda23(this, r3));
        }

        private /* synthetic */ void lambda$new$16(boolean r2, Context r3, ToggleButton r4, ToggleButton r5, View r6) {
            MessagePreviewView r62 = this.this$0;
            if (r2 == true) goto L6;
            BulletinFactory.of(r62, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r62)).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.replaceSingleTag("Subscribe to **Telegram Premium** to forward formatted messages without the sender’s name.", new MessagePreviewView$Page$$ExternalSyntheticLambda22(this, r3))).show();
            return;
        L6:
            MessagePreviewParams r22 = r62.messagePreviewParams;
            boolean r32 = r22.hideForwardSendersName;
            r22.hideForwardSendersName = !r32;
            r62.returnSendersNames = false;
            if (r32 == false) goto L11;
            r22.hideCaption = false;
            if (r4 == null) goto L11;
            r4.setState(false, true);
        L11:
            r5.setState(this.this$0.messagePreviewParams.hideForwardSendersName, true);
            updateMessages();
            updateSubtitle(true);
        }

        private /* synthetic */ void lambda$new$17(ToggleButton r6, ToggleButton r7, View r8) {
            MessagePreviewView r82 = this.this$0;
            MessagePreviewParams r0 = r82.messagePreviewParams;
            boolean r1 = r0.hideCaption;
            boolean r2 = !r1;
            r0.hideCaption = r2;
            if (r1 == true) goto L8;
            if (r0.hideForwardSendersName == true) goto L11;
            r0.hideForwardSendersName = true;
            r82.returnSendersNames = true;
        L11:
            r6.setState(r2, true);
            r7.setState(this.this$0.messagePreviewParams.hideForwardSendersName, true);
            updateMessages();
            updateSubtitle(true);
            return;
        L8:
            if (r82.returnSendersNames == false) goto L10;
            r0.hideForwardSendersName = false;
        L10:
            r82.returnSendersNames = false;
            goto L11
        }

        private /* synthetic */ void lambda$new$18(View r1) {
            this.this$0.dismiss(true);
        }

        private /* synthetic */ void lambda$new$19(View r1) {
            this.this$0.removeLink();
        }

        private /* synthetic */ void lambda$new$2(View r2) {
            this.this$0.messagePreviewParams.quote = null;
            this.textSelectionHelper.clear();
            switchToQuote(false, false);
            this.menu.getSwipeBack().closeForeground();
        }

        private /* synthetic */ void lambda$new$20(View r4) {
            MessagePreviewParams r42 = this.this$0.messagePreviewParams;
            if (r42.hasMedia == true) goto L5;
            return;
        L5:
            boolean r0 = !r42.webpageSmall;
            r42.webpageSmall = r0;
            this.changeSizeBtn.setState(r0, true);
            this.videoChangeSizeBtn.setState(this.this$0.messagePreviewParams.webpageSmall, true);
            if (this.messages.messages.size() <= 0) goto L13;
            TLRPC.Message r43 = this.messages.messages.get(0).messageOwner;
            if (r43 == null) goto L13;
            TLRPC.MessageMedia r44 = r43.media;
            if (r44 == null) goto L13;
            boolean r2 = this.this$0.messagePreviewParams.webpageSmall;
            r44.force_small_media = r2;
            r44.force_large_media = !r2;
        L13:
            if (this.messages.previewMessages.size() <= 0) goto L19;
            TLRPC.Message r45 = this.messages.previewMessages.get(0).messageOwner;
            if (r45 == null) goto L19;
            TLRPC.MessageMedia r46 = r45.media;
            if (r46 == null) goto L19;
            boolean r02 = this.this$0.messagePreviewParams.webpageSmall;
            r46.force_small_media = r02;
            r46.force_large_media = !r02;
        L19:
            updateMessages();
            this.updateScroll = true;
        }

        private /* synthetic */ void lambda$new$21(View r4) {
            MessagePreviewParams r42 = this.this$0.messagePreviewParams;
            boolean r0 = r42.webpageTop;
            r42.webpageTop = !r0;
            this.changePositionBtn.setState(r0, true);
            if (this.messages.messages.size() <= 0) goto L8;
            TLRPC.Message r43 = this.messages.messages.get(0).messageOwner;
            if (r43 == null) goto L8;
            r43.invert_media = this.this$0.messagePreviewParams.webpageTop;
        L8:
            if (this.messages.previewMessages.size() <= 0) goto L12;
            TLRPC.Message r44 = this.messages.previewMessages.get(0).messageOwner;
            if (r44 == null) goto L12;
            r44.invert_media = this.this$0.messagePreviewParams.webpageTop;
        L12:
            updateMessages();
            this.updateScroll = true;
        }

        private /* synthetic */ void lambda$new$3(View r4) {
            if (getReplyMessage() == null) goto L14;
            TextSelectionHelper.ChatListTextSelectionHelper r42 = this.textSelectionHelper;
            if ((r42.selectionEnd - r42.selectionStart) <= MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$0)).quoteLengthMax) goto L9;
            showQuoteLengthError();
            return;
        L9:
            if (this.textSelectionHelper.getSelectedCell() == null) goto L11;
            MessageObject r43 = this.textSelectionHelper.getSelectedCell().getMessageObject();
        L12:
            MessageObject r44 = getReplyMessage(r43);
            MessagePreviewParams r0 = this.this$0.messagePreviewParams;
            TextSelectionHelper.ChatListTextSelectionHelper r1 = this.textSelectionHelper;
            int r2 = r1.selectionStart;
            r0.quoteStart = r2;
            int r12 = r1.selectionEnd;
            r0.quoteEnd = r12;
            r0.quote = ChatActivity.ReplyQuote.from(r44, r2, r12);
            this.this$0.onQuoteSelectedPart();
            this.this$0.dismiss(true);
            return;
        L11:
            r43 = null;
            goto L12
        }

        private /* synthetic */ void lambda$new$4(View r6) {
            MessagePreviewView r62 = this.this$0;
            MessagePreviewParams r0 = r62.messagePreviewParams;
            MessageObject r3 = null;
            if (r0.quote != null) goto L5;
        L8:
            TextSelectionHelper.ChatListTextSelectionHelper r02 = this.textSelectionHelper;
            if ((r02.selectionEnd - r02.selectionStart) <= MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(r62)).quoteLengthMax) goto L12;
            showQuoteLengthError();
            return;
        L12:
            MessageObject r63 = getReplyMessage();
            if (r63 == null) goto L29;
            boolean r03 = this.textSelectionHelper.isInSelectionMode();
            MessagePreviewView r1 = this.this$0;
            if (r03 == false) goto L21;
            MessagePreviewParams r64 = r1.messagePreviewParams;
            TextSelectionHelper.ChatListTextSelectionHelper r04 = this.textSelectionHelper;
            r64.quoteStart = r04.selectionStart;
            r64.quoteEnd = r04.selectionEnd;
            if (r04.getSelectedCell() == null) goto L19;
            r3 = this.textSelectionHelper.getSelectedCell().getMessageObject();
        L19:
            MessageObject r65 = getReplyMessage(r3);
            MessagePreviewParams r05 = this.this$0.messagePreviewParams;
            r05.quote = ChatActivity.ReplyQuote.from(r65, r05.quoteStart, r05.quoteEnd);
            this.this$0.onQuoteSelectedPart();
            this.this$0.dismiss(true);
            return;
        L21:
            MessagePreviewParams r06 = r1.messagePreviewParams;
            r06.quoteStart = 0;
            r06.quoteEnd = Math.min(MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(r1)).quoteLengthMax, r63.messageOwner.message.length());
            MessagePreviewParams r07 = this.this$0.messagePreviewParams;
            r07.quote = ChatActivity.ReplyQuote.from(r63, r07.quoteStart, r07.quoteEnd);
            View r66 = getReplyMessageCell();
            if ((r66 instanceof ChatMessageCell) == false) goto L25;
            MessagePreviewParams r12 = this.this$0.messagePreviewParams;
            this.textSelectionHelper.select((ChatMessageCell) r66, r12.quoteStart, r12.quoteEnd);
        L25:
            if (this.this$0.showOutdatedQuote == true) goto L27;
            this.menu.getSwipeBack().openForeground(this.menuBack);
        L27:
            switchToQuote(true, true);
            return;
        L29:
            return;
        L5:
            if (r62.showOutdatedQuote == true) goto L8;
            r0.quote = null;
            this.textSelectionHelper.clear();
            switchToQuote(false, true);
            updateSubtitle(true);
        }

        private /* synthetic */ void lambda$new$5(View r1) {
            this.this$0.selectAnotherChat(false);
        }

        private /* synthetic */ void lambda$new$6(View r1) {
            this.this$0.selectAnotherChat(false);
        }

        private /* synthetic */ void lambda$new$7(View r1) {
            this.this$0.dismiss(true);
        }

        private /* synthetic */ void lambda$new$8(View r1) {
            this.this$0.viewInChat();
        }

        private /* synthetic */ void lambda$new$9(View r1) {
            MessagePreviewView r0 = this.this$0;
            if (r0.showOutdatedQuote == false) goto L6;
            r0.removeQuote();
            return;
        L6:
            r0.removeReply();
        }

        private /* synthetic */ void lambda$onAttachedToWindow$23(View r2) {
            this.adapter.onViewAttachedToWindow(this.chatListView.getChildViewHolder(r2));
        }

        private /* synthetic */ void lambda$updatePositions$24(int r3, float r4, ValueAnimator r5) {
            float r52 = ((Float) r5.getAnimatedValue()).floatValue();
            float r0 = 1.0f - r52;
            int r32 = (int) ((r3 * r0) + (this.chatTopOffset * r52));
            this.currentTopOffset = r32;
            float r42 = (r4 * r0) + (this.yOffset * r52);
            this.currentYOffset = r42;
            setOffset(r42, r32);
        }

        private void setOffset(float r3, int r4) {
            boolean r0 = this.this$0.isLandscapeMode;
            ActionBar r1 = this.actionBar;
            if (r0 == false) goto L5;
            r1.setTranslationY(0.0f);
            this.chatPreviewContainer.invalidateOutline();
            this.chatPreviewContainer.setTranslationY(0.0f);
            this.menu.setTranslationY(0.0f);
        L6:
            this.textSelectionOverlay.setTranslationX(this.chatPreviewContainer.getX());
            this.textSelectionOverlay.setTranslationY(this.chatPreviewContainer.getY());
            return;
        L5:
            r1.setTranslationY(r4);
            this.chatPreviewContainer.invalidateOutline();
            this.chatPreviewContainer.setTranslationY(r3);
            this.menu.setTranslationY((r3 + this.chatPreviewContainer.getMeasuredHeight()) - AndroidUtilities.dp(2.0f));
            goto L6
        }

        private void showQuoteLengthError() {
            MessagePreviewView r3 = this.this$0;
            BulletinFactory.of(r3, MessagePreviewView.m11267$$Nest$fgetresourcesProvider(r3)).createSimpleBulletin(R.raw.error, LocaleController.getString(R.string.QuoteMaxError), LocaleController.getString(R.string.QuoteMaxErrorMessage)).show();
        }

        private void switchToQuote(boolean r9, boolean r10) {
            int r1 = 0;
            if (this.this$0.showOutdatedQuote == false) goto L5;
            r9 = false;
        L5:
            if (r10 == true) goto L7;
        L9:
            this.toQuote = r9;
            AnimatorSet r0 = this.quoteSwitcher;
            if (r0 == null) goto L12;
            r0.cancel();
            this.quoteSwitcher = null;
        L12:
            float r02 = 0.0f;
            if (r10 == false) goto L43;
            this.quoteSwitcher = new AnimatorSet();
            ArrayList r102 = new ArrayList();
            ActionBarMenuSubItem r3 = this.quoteButton;
            Property r5 = View.ALPHA;
            if (r3 == null) goto L21;
            r3.setVisibility(0);
            ActionBarMenuSubItem r32 = this.quoteButton;
            if (r9 == true) goto L19;
            float r6 = 1.0f;
        L20:
            r102.add(ObjectAnimator.ofFloat(r32, r5, new float[]{r6}));
            goto L21
        L19:
            r6 = 0.0f;
        L21:
            ActionBarMenuSubItem r33 = this.clearQuoteButton;
            if (r33 == null) goto L28;
            r33.setVisibility(0);
            ActionBarMenuSubItem r34 = this.clearQuoteButton;
            if (r9 == false) goto L26;
            float r62 = 1.0f;
        L27:
            r102.add(ObjectAnimator.ofFloat(r34, r5, new float[]{r62}));
            goto L28
        L26:
            r62 = 0.0f;
        L28:
            ActionBarMenuSubItem r35 = this.replyAnotherChatButton;
            if (r35 == null) goto L35;
            r35.setVisibility(0);
            ActionBarMenuSubItem r36 = this.replyAnotherChatButton;
            if (r9 == true) goto L33;
            float r63 = 1.0f;
        L34:
            r102.add(ObjectAnimator.ofFloat(r36, r5, new float[]{r63}));
            goto L35
        L33:
            r63 = 0.0f;
        L35:
            ActionBarMenuSubItem r37 = this.quoteAnotherChatButton;
            if (r37 == null) goto L41;
            r37.setVisibility(0);
            ActionBarMenuSubItem r38 = this.quoteAnotherChatButton;
            if (r9 == false) goto L40;
            r02 = 1.0f;
        L40:
            r102.add(ObjectAnimator.ofFloat(r38, r5, new float[]{r02}));
        L41:
            this.quoteSwitcher.playTogether(r102);
            this.quoteSwitcher.setDuration(360);
            this.quoteSwitcher.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
            this.quoteSwitcher.addListener(new AnonymousClass1(this, r9));
            this.quoteSwitcher.start();
            return;
        L43:
            ActionBarMenuSubItem r103 = this.quoteButton;
            if (r103 == null) goto L53;
            if (r9 == true) goto L47;
            float r4 = 1.0f;
        L48:
            r103.setAlpha(r4);
            ActionBarMenuSubItem r104 = this.quoteButton;
            if (r9 == true) goto L51;
            int r42 = 0;
        L52:
            r104.setVisibility(r42);
            goto L53
        L51:
            r42 = 4;
            goto L52
        L47:
            r4 = 0.0f;
        L53:
            ActionBarMenuSubItem r105 = this.clearQuoteButton;
            if (r105 == null) goto L63;
            if (r9 == false) goto L57;
            float r43 = 1.0f;
        L58:
            r105.setAlpha(r43);
            ActionBarMenuSubItem r106 = this.clearQuoteButton;
            if (r9 == false) goto L61;
            int r44 = 0;
        L62:
            r106.setVisibility(r44);
            goto L63
        L61:
            r44 = 4;
            goto L62
        L57:
            r43 = 0.0f;
        L63:
            ActionBarMenuSubItem r107 = this.replyAnotherChatButton;
            if (r107 == null) goto L73;
            if (r9 == true) goto L67;
            float r45 = 1.0f;
        L68:
            r107.setAlpha(r45);
            ActionBarMenuSubItem r108 = this.replyAnotherChatButton;
            if (r9 == true) goto L71;
            int r46 = 0;
        L72:
            r108.setVisibility(r46);
            goto L73
        L71:
            r46 = 4;
            goto L72
        L67:
            r45 = 0.0f;
        L73:
            ActionBarMenuSubItem r109 = this.quoteAnotherChatButton;
            if (r109 == null) goto L83;
            if (r9 == false) goto L77;
            r02 = 1.0f;
        L77:
            r109.setAlpha(r02);
            ActionBarMenuSubItem r8 = this.quoteAnotherChatButton;
            if (r9 == true) goto L81;
            r1 = 4;
        L81:
            r8.setVisibility(r1);
            return;
        L83:
            return;
        L7:
            if (this.toQuote != r9) goto L9;
        }

        private void updateLinkHighlight(ChatMessageCell r3) {
            if (this.currentTab != 2) goto L14;
            MessagePreviewParams r2 = this.this$0.messagePreviewParams;
            if (r2.singleLink == true) goto L14;
            CharacterStyle r0 = r2.currentLink;
            if (r0 == null) goto L14;
            TLRPC.WebPage r22 = r2.webpage;
            if (r22 == null) goto L14;
            if ((r22 instanceof TLRPC.TL_webPagePending) == true) goto L14;
            r3.setHighlightedSpan(r0);
            return;
        L14:
            r3.setHighlightedSpan(null);
        }

        private void updateMessages() {
            if (this.itemAnimator.isRunning() == false) goto L6;
            this.updateAfterAnimations = true;
            return;
        L6:
            int r2 = 0;
        L8:
            if (r2 >= this.messages.previewMessages.size()) goto L36;
            MessageObject r3 = this.messages.previewMessages.get(r2);
            r3.forceUpdate = true;
            MessagePreviewView r4 = this.this$0;
            r3.sendAsPeer = r4.sendAsPeer;
            MessagePreviewParams r42 = r4.messagePreviewParams;
            boolean r5 = r42.hideForwardSendersName;
            TLRPC.Message r6 = r3.messageOwner;
            if (r5 == true) goto L12;
            r6.flags |= 4;
            r3.hideSendersName = false;
        L14:
            if (this.currentTab != 2) goto L25;
            TLRPC.WebPage r43 = r42.webpage;
            if (r43 == null) goto L22;
            TLRPC.Message r52 = r3.messageOwner;
            TLRPC.MessageMedia r62 = r52.media;
            if (r62 != null) goto L20;
        L21:
            r52.flags |= 512;
            r52.media = new TLRPC.TL_messageMediaWebPage();
            TLRPC.MessageMedia r44 = r3.messageOwner.media;
            MessagePreviewParams r53 = this.this$0.messagePreviewParams;
            r44.webpage = r53.webpage;
            boolean r54 = r53.webpageSmall;
            r44.force_large_media = !r54;
            r44.force_small_media = r54;
            r44.manual = true;
            r3.linkDescription = null;
            r3.generateLinkDescription();
            r3.photoThumbs = null;
            r3.photoThumbs2 = null;
            r3.photoThumbsObject = null;
            r3.photoThumbsObject2 = null;
            r3.generateThumbs(true);
            r3.checkMediaExistance();
            goto L25
        L20:
            if (r62.webpage != r43) goto L21;
        L22:
            if (r43 != null) goto L25;
            TLRPC.Message r45 = r3.messageOwner;
            r45.flags &= -513;
            r45.media = null;
        L25:
            if (this.this$0.messagePreviewParams.hideCaption == false) goto L27;
            r3.caption = null;
        L29:
            if (r3.isPoll() == false) goto L35;
            MessagePreviewParams.PreviewMediaPoll r32 = (MessagePreviewParams.PreviewMediaPoll) r3.messageOwner.media;
            TLRPC.PollResults r46 = r32.results;
            if (this.this$0.messagePreviewParams.hideCaption == false) goto L33;
            int r33 = 0;
        L34:
            r46.total_voters = r33;
            goto L35
        L33:
            r33 = r32.totalVotersCached;
        L35:
            r2 = r2 + 1;
            goto L8
        L27:
            r3.generateCaption();
            goto L29
        L12:
            r6.flags &= -5;
            r3.hideSendersName = true;
            goto L14
        L36:
            int r22 = 0;
        L38:
            if (r22 >= this.messages.pollChosenAnswers.size()) goto L40;
            this.messages.pollChosenAnswers.get(r22).chosen = !this.this$0.messagePreviewParams.hideForwardSendersName;
            r22 = r22 + 1;
            goto L38
        L40:
            int r1 = 0;
        L42:
            if (r1 >= this.messages.groupedMessagesMap.size()) goto L44;
            this.itemAnimator.groupWillChanged(this.messages.groupedMessagesMap.valueAt(r1));
            r1 = r1 + 1;
            goto L42
        L44:
            this.adapter.notifyItemRangeChanged(0, this.messages.previewMessages.size());
        }

        private void updatePositions() {
            int r0 = this.chatTopOffset;
            float r1 = this.yOffset;
            int r4 = 0;
            if (this.this$0.isLandscapeMode == true) goto L26;
            int r2 = this.chatListView.getMeasuredHeight();
            int r5 = 0;
            int r6 = 0;
        L6:
            if (r5 >= this.chatListView.getChildCount()) goto L11;
            View r7 = this.chatListView.getChildAt(r5);
            if (this.chatListView.getChildAdapterPosition(r7) == (-1)) goto L10;
            r2 = Math.min(r2, r7.getTop());
            r6 = r6 + 1;
        L10:
            r5 = r5 + 1;
            goto L6
        L11:
            MessagePreviewParams.Messages r52 = this.messages;
            if (r52 == null) goto L21;
            if (r6 == 0) goto L21;
            if (r6 > r52.previewMessages.size()) goto L21;
            int r22 = Math.max(0, r2 - AndroidUtilities.dp(4.0f));
            this.chatTopOffset = r22;
            int r23 = r22 + (this.chatListView.getMeasuredHeight() - this.chatTopOffset);
            int r53 = AndroidUtilities.displaySize.y;
            if (Build.VERSION.SDK_INT < 35) goto L20;
            r4 = AndroidUtilities.navigationBarHeight;
        L20:
            this.chatTopOffset = Math.min(r23 - ((int) ((((r53 - r4) * 0.8f) - this.buttonsHeight) - AndroidUtilities.dp(8.0f))), this.chatTopOffset);
        L22:
            float r24 = (this.buttonsHeight - AndroidUtilities.dp(8.0f)) + (this.chatPreviewContainer.getMeasuredHeight() - this.chatTopOffset);
            float r54 = (AndroidUtilities.dp(8.0f) + (((getMeasuredHeight() - AndroidUtilities.dp(16.0f)) - r24) / 2.0f)) - this.chatTopOffset;
            this.yOffset = r54;
            if (r54 <= AndroidUtilities.dp(8.0f)) goto L25;
            this.yOffset = AndroidUtilities.dp(8.0f);
        L25:
            this.menu.setTranslationX(getMeasuredWidth() - this.menu.getMeasuredWidth());
        L27:
            boolean r25 = this.firstLayout;
            if (r25 == false) goto L30;
        L38:
            if (r25 == false) goto L44;
            float r02 = this.yOffset;
            this.currentYOffset = r02;
            int r12 = this.chatTopOffset;
            this.currentTopOffset = r12;
            setOffset(r02, r12);
            return;
        L44:
            return;
        L30:
            if (this.chatTopOffset == r0) goto L32;
        L33:
            ValueAnimator r26 = this.this$0.offsetsAnimator;
            if (r26 == null) goto L36;
            r26.cancel();
        L36:
            this.this$0.offsetsAnimator = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            this.this$0.offsetsAnimator.addUpdateListener(new MessagePreviewView$Page$$ExternalSyntheticLambda20(this, r0, r1));
            this.this$0.offsetsAnimator.setDuration(250);
            this.this$0.offsetsAnimator.setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR);
            this.this$0.offsetsAnimator.addListener(new AnonymousClass15(this));
            AndroidUtilities.runOnUIThread(this.this$0.changeBoundsRunnable, 50);
            this.currentTopOffset = r0;
            this.currentYOffset = r1;
            setOffset(r1, r0);
            return;
        L32:
            if (this.yOffset == r1) goto L38;
        L21:
            this.chatTopOffset = 0;
            goto L22
        L26:
            this.yOffset = 0.0f;
            this.chatTopOffset = 0;
            this.menu.setTranslationX(this.chatListView.getMeasuredWidth() + AndroidUtilities.dp(8.0f));
            goto L27
        }

        private void updateSubtitle(boolean r5) {
            int r0 = this.currentTab;
            if (r0 != 1) goto L52;
            ActionBar r02 = this.actionBar;
            MessagePreviewParams.Messages r1 = this.this$0.messagePreviewParams.forwardMessages;
            if (r1 != null) goto L7;
            int r12 = 0;
        L8:
            r02.setTitle(LocaleController.formatPluralString("PreviewForwardMessagesCount", r12, new Object[0]), r5);
            MessagePreviewView r03 = this.this$0;
            MessagePreviewParams r13 = r03.messagePreviewParams;
            if (r13.hasSenders == true) goto L31;
            if (r13.willSeeSenders == false) goto L21;
            TLRPC.User r14 = r03.currentUser;
            if (r14 == null) goto L16;
            String r04 = LocaleController.formatString("ForwardPreviewSendersNameVisible", R.string.ForwardPreviewSendersNameVisible, new Object[]{ContactsController.formatName(r14.first_name, r14.last_name)});
        L50:
            this.actionBar.setSubtitle(r04, r5);
            return;
        L16:
            if (ChatObject.isChannel(r03.currentChat) == true) goto L18;
        L20:
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameVisibleGroup);
            goto L50
        L18:
            if (this.this$0.currentChat.megagroup == true) goto L20;
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameVisibleChannel);
            goto L50
        L21:
            TLRPC.User r15 = r03.currentUser;
            if (r15 == null) goto L25;
            r04 = LocaleController.formatString("ForwardPreviewSendersNameVisible", R.string.ForwardPreviewSendersNameVisible, new Object[]{ContactsController.formatName(r15.first_name, r15.last_name)});
            goto L50
        L25:
            if (ChatObject.isChannel(r03.currentChat) == true) goto L27;
        L29:
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameHiddenGroup);
            goto L50
        L27:
            if (this.this$0.currentChat.megagroup == true) goto L29;
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameHiddenChannel);
            goto L50
        L31:
            if (r13.hideForwardSendersName == true) goto L41;
            TLRPC.User r16 = r03.currentUser;
            if (r16 == null) goto L36;
            r04 = LocaleController.formatString("ForwardPreviewSendersNameVisible", R.string.ForwardPreviewSendersNameVisible, new Object[]{ContactsController.formatName(r16.first_name, r16.last_name)});
            goto L50
        L36:
            if (ChatObject.isChannel(r03.currentChat) == true) goto L38;
        L40:
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameVisibleGroup);
            goto L50
        L38:
            if (this.this$0.currentChat.megagroup == true) goto L40;
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameVisibleChannel);
            goto L50
        L41:
            TLRPC.User r17 = r03.currentUser;
            if (r17 == null) goto L45;
            r04 = LocaleController.formatString("ForwardPreviewSendersNameHidden", R.string.ForwardPreviewSendersNameHidden, new Object[]{ContactsController.formatName(r17.first_name, r17.last_name)});
            goto L50
        L45:
            if (ChatObject.isChannel(r03.currentChat) == true) goto L47;
        L49:
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameHiddenGroup);
            goto L50
        L47:
            if (this.this$0.currentChat.megagroup == true) goto L49;
            r04 = LocaleController.getString(R.string.ForwardPreviewSendersNameHiddenChannel);
            goto L50
        L7:
            r12 = r1.getSelectedCount();
            goto L8
        L52:
            if (r0 != 0) goto L66;
            MessagePreviewParams r05 = this.this$0.messagePreviewParams;
            if (r05.quote != null) goto L56;
        L59:
            this.actionBar.setTitle(LocaleController.getString(R.string.MessageOptionsReplyTitle), r5);
            ActionBar r06 = this.actionBar;
            if (this.this$0.messagePreviewParams.replyMessage.hasText == false) goto L62;
            String r4 = LocaleController.getString(R.string.MessageOptionsReplySubtitle);
        L63:
            r06.setSubtitle(r4, r5);
            return;
        L62:
            r4 = _UrlKt.FRAGMENT_ENCODE_SET;
            goto L63
        L56:
            if (r05.replyMessage.hasText == false) goto L59;
            this.actionBar.setTitle(LocaleController.getString(R.string.PreviewQuoteUpdate), r5);
            this.actionBar.setSubtitle(LocaleController.getString(R.string.PreviewQuoteUpdateSubtitle), r5);
            return;
        L66:
            if (r0 != 2) goto L69;
            this.actionBar.setTitle(LocaleController.getString(R.string.MessageOptionsLinkTitle), r5);
            this.actionBar.setSubtitle(LocaleController.getString(R.string.MessageOptionsLinkSubtitle), r5);
            return;
        }

        public void bind() {
            updateMessages();
            updateSubtitle(false);
        }

        public MessageObject getReplyMessage() {
            return getReplyMessage(null);
        }

        public MessageObject getReplyMessage(MessageObject r3) {
            MessagePreviewParams.Messages r0 = this.this$0.messagePreviewParams.replyMessage;
            if (r0 == null) goto L22;
            LongSparseArray<MessageObject.GroupedMessages> r02 = r0.groupedMessagesMap;
            if (r02 == null) goto L21;
            if (r02.size() <= 0) goto L21;
            MessageObject.GroupedMessages r03 = this.this$0.messagePreviewParams.replyMessage.groupedMessagesMap.valueAt(0);
            if (r03 == null) goto L21;
            if (r03.isDocuments == false) goto L19;
            if (r3 == null) goto L14;
            return r3;
        L14:
            ChatActivity.ReplyQuote r2 = this.this$0.messagePreviewParams.quote;
            if (r2 == null) goto L19;
            return r2.message;
        L19:
            return r03.captionMessage;
        L21:
            return this.this$0.messagePreviewParams.replyMessage.messages.get(0);
        L22:
            return null;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public View getReplyMessageCell() {
            MessageObject r0 = getReplyMessage();
            if (r0 != null) goto L5;
            return null;
        L5:
            int r2 = 0;
        L7:
            if (r2 >= this.chatListView.getChildCount()) goto L18;
            View r3 = this.chatListView.getChildAt(r2);
            IMessageCell r4 = (IMessageCell) r3;
            if (r4.getMessageObject() == null) goto L16;
            if (r4.getMessageObject() == r0) goto L17;
            if (r4.getMessageObject().getId() != r0.getId()) goto L16;
        L17:
            return r3;
        L16:
            r2 = r2 + 1;
            goto L7
        L18:
            return null;
        }

        public boolean isReplyMessageCell(ChatMessageCell r3) {
            if (r3 != null) goto L5;
        L18:
            return false;
        L5:
            if (r3.getMessageObject() == null) goto L18;
            MessageObject r2 = getReplyMessage();
            if (r2 != null) goto L11;
            return false;
        L11:
            if (r3.getMessageObject() != r2) goto L13;
            return true;
        L13:
            if (r3.getMessageObject().getId() == r2.getId()) goto L19;
            return false;
        L19:
            return true;
        }

        public boolean isReplyToRichMessage() {
            if (this.currentTab == 0) goto L5;
            return false;
        L5:
            MessageObject r2 = getReplyMessage();
            if (r2 == null) goto L13;
            TLRPC.Message r22 = r2.messageOwner;
            if (r22 == null) goto L13;
            if (r22.rich_message == null) goto L13;
            return true;
        L13:
            return false;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            if (this.currentTab != 0) goto L6;
            AndroidUtilities.forEachViews(this.chatListView, new MessagePreviewView$Page$$ExternalSyntheticLambda21(this));
            return;
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            updateSelection();
            this.firstAttach = true;
            this.firstLayout = true;
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            updatePositions();
            this.firstLayout = false;
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            MessagePreviewView r0 = this.this$0;
            int r4 = 0;
            if (View.MeasureSpec.getSize(r9) <= View.MeasureSpec.getSize(r10)) goto L5;
            boolean r1 = true;
        L6:
            r0.isLandscapeMode = r1;
            this.buttonsHeight = 0;
            this.menu.measure(r9, View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r10), 0));
            int r02 = this.buttonsHeight;
            int r12 = this.menu.getMeasuredHeight();
            Rect r2 = this.rect;
            this.buttonsHeight = Math.max(r02, (r12 + r2.top) + r2.bottom);
            ((ViewGroup.MarginLayoutParams) this.chatListView.getLayoutParams()).topMargin = org.telegram.ui.ActionBar.ActionBar.getCurrentActionBarHeight();
            boolean r03 = this.this$0.isLandscapeMode;
            SizeNotifierFrameLayout r13 = this.chatPreviewContainer;
            if (r03 == false) goto L9;
            r13.getLayoutParams().height = -1;
            ((ViewGroup.MarginLayoutParams) this.chatPreviewContainer.getLayoutParams()).topMargin = AndroidUtilities.dp(8.0f);
            ((ViewGroup.MarginLayoutParams) this.chatPreviewContainer.getLayoutParams()).bottomMargin = AndroidUtilities.dp(8.0f);
            this.chatPreviewContainer.getLayoutParams().width = (int) Math.min(View.MeasureSpec.getSize(r9), Math.max(AndroidUtilities.dp(340.0f), View.MeasureSpec.getSize(r9) * 0.6f));
            this.menu.getLayoutParams().height = -1;
        L13:
            int r04 = (View.MeasureSpec.getSize(r9) + View.MeasureSpec.getSize(r10)) << 16;
            if (this.lastSize != r04) goto L16;
        L26:
            this.lastSize = r04;
            super.onMeasure(r9, r10);
            return;
        L16:
            if (r4 >= this.messages.previewMessages.size()) goto L25;
            MessageObject r14 = this.messages.previewMessages.get(r4);
            if (this.this$0.isLandscapeMode == false) goto L20;
            int r22 = this.chatPreviewContainer.getLayoutParams().width;
        L21:
            r14.parentWidth = r22;
            r14.resetLayout();
            r14.forceUpdate = true;
            Adapter r15 = this.adapter;
            if (r15 == null) goto L24;
            r15.notifyDataSetChanged();
        L24:
            r4 = r4 + 1;
            goto L16
        L20:
            r22 = View.MeasureSpec.getSize(r9) - AndroidUtilities.dp(16.0f);
            goto L21
        L25:
            this.firstLayout = true;
            goto L26
        L9:
            ((ViewGroup.MarginLayoutParams) r13.getLayoutParams()).topMargin = 0;
            ((ViewGroup.MarginLayoutParams) this.chatPreviewContainer.getLayoutParams()).bottomMargin = 0;
            this.chatPreviewContainer.getLayoutParams().height = (View.MeasureSpec.getSize(r10) - AndroidUtilities.dp(6.0f)) - this.buttonsHeight;
            if (this.chatPreviewContainer.getLayoutParams().height >= (View.MeasureSpec.getSize(r10) * 0.5f)) goto L12;
            this.chatPreviewContainer.getLayoutParams().height = (int) (View.MeasureSpec.getSize(r10) * 0.5f);
        L12:
            this.chatPreviewContainer.getLayoutParams().width = -1;
            this.menu.getLayoutParams().height = View.MeasureSpec.getSize(r10) - this.chatPreviewContainer.getLayoutParams().height;
            goto L13
        L5:
            r1 = false;
            goto L6
        }

        public void updateSelection() {
            if (this.currentTab != 0) goto L24;
            TextSelectionHelper.ChatListTextSelectionHelper r0 = this.textSelectionHelper;
            if ((r0.selectionEnd - r0.selectionStart) <= MessagesController.getInstance(MessagePreviewView.m11264$$Nest$fgetcurrentAccount(this.this$0)).quoteLengthMax) goto L8;
            return;
        L8:
            if (this.textSelectionHelper.getSelectedCell() == null) goto L10;
            MessageObject r02 = this.textSelectionHelper.getSelectedCell().getMessageObject();
        L11:
            MessageObject r03 = getReplyMessage(r02);
            if (this.this$0.messagePreviewParams.quote != null) goto L14;
        L22:
            this.textSelectionHelper.clear();
            return;
        L14:
            if (this.textSelectionHelper.isInSelectionMode() == false) goto L22;
            MessagePreviewParams r1 = this.this$0.messagePreviewParams;
            TextSelectionHelper.ChatListTextSelectionHelper r2 = this.textSelectionHelper;
            r1.quoteStart = r2.selectionStart;
            r1.quoteEnd = r2.selectionEnd;
            if (r03 == null) goto L22;
            MessageObject r12 = r1.quote.message;
            if (r12 != null) goto L20;
        L21:
            MessagePreviewParams r13 = this.this$0.messagePreviewParams;
            r13.quote = ChatActivity.ReplyQuote.from(r03, r13.quoteStart, r13.quoteEnd);
            this.this$0.onQuoteSelectedPart();
            goto L22
        L20:
            if (r12.getId() == r03.getId()) goto L22;
        L10:
            r02 = null;
            goto L11
        }
    }

    public static class RLottieToggleDrawable extends Drawable {
        private RLottieDrawable currentState;
        private boolean detached;
        private boolean isState1;
        private RLottieDrawable state1;
        private RLottieDrawable state2;

        public RLottieToggleDrawable(View r5, int r6, int r7) {
            RLottieDrawable r0 = new RLottieDrawable(r6, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            this.state1 = r0;
            r0.setMasterParent(r5);
            this.state1.setAllowDecodeSingleFrame(true);
            this.state1.setPlayInDirectionOfCustomEndFrame(true);
            this.state1.setAutoRepeat(0);
            RLottieDrawable r62 = new RLottieDrawable(r7, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            this.state2 = r62;
            r62.setMasterParent(r5);
            this.state2.setAllowDecodeSingleFrame(true);
            this.state2.setPlayInDirectionOfCustomEndFrame(true);
            this.state2.setAutoRepeat(0);
            this.currentState = this.state1;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r7) {
            if (this.detached == false) goto L5;
            return;
        L5:
            Rect r0 = AndroidUtilities.rectTmp2;
            r0.set(getBounds().centerX() - AndroidUtilities.dp(12.0f), getBounds().centerY() - AndroidUtilities.dp(12.0f), getBounds().centerX() + AndroidUtilities.dp(12.0f), getBounds().centerY() + AndroidUtilities.dp(12.0f));
            if (this.currentState.isLastFrame() == false) goto L16;
            RLottieDrawable r1 = this.currentState;
            boolean r2 = this.isState1;
            if (r2 == false) goto L10;
            RLottieDrawable r3 = this.state1;
        L11:
            if (r1 == r3) goto L16;
            if (r2 == false) goto L14;
            RLottieDrawable r12 = this.state1;
        L15:
            this.currentState = r12;
            r12.setCurrentFrame(r12.getFramesCount() - 1);
            goto L16
        L14:
            r12 = this.state2;
            goto L15
        L10:
            r3 = this.state2;
        L16:
            this.currentState.setBounds(r0);
            this.currentState.draw(r7);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return AndroidUtilities.dp(24.0f);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r2) {
            this.state1.setAlpha(r2);
            this.state2.setAlpha(r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r2) {
            this.state1.setColorFilter(r2);
            this.state2.setColorFilter(r2);
        }

        public void setState(boolean r1, boolean r2) {
            this.isState1 = r1;
            if (r2 == false) goto L9;
            if (r1 == false) goto L6;
            RLottieDrawable r12 = this.state1;
        L7:
            this.currentState = r12;
            this.state1.setCurrentFrame(0);
            this.state2.setCurrentFrame(0);
            this.currentState.start();
            return;
        L6:
            r12 = this.state2;
            goto L7
        L9:
            if (r1 == false) goto L11;
            RLottieDrawable r13 = this.state1;
        L12:
            this.currentState = r13;
            r13.setCurrentFrame(r13.getFramesCount() - 1);
            return;
        L11:
            r13 = this.state2;
            goto L12
        }
    }

    public interface ResourcesDelegate extends Theme.ResourcesProvider {
        Drawable getWallpaperDrawable();

        boolean isWallpaperMotion();
    }

    public static class TabsView extends View {
        private Drawable bgDrawable;
        private final Paint bgPaint;
        private int color;
        private float marginBetween;
        private Utilities.Callback<Integer> onTabClick;
        private final Theme.ResourcesProvider resourcesProvider;
        private RectF selectRect;
        private int selectedColor;
        private float selectedTab;
        private float tabInnerPadding;
        public final ArrayList<Tab> tabs;

        public static class Tab {
            final RectF bounds;
            final RectF clickBounds;
            final int id;
            final Text text;

            public Tab(int r3, String r4) {
                this.bounds = new RectF();
                this.clickBounds = new RectF();
                this.id = r3;
                this.text = new Text(r4, 14.0f, AndroidUtilities.bold());
            }
        }

        public TabsView(Context r4, Theme.ResourcesProvider r5) {
            super(r4);
            this.tabs = new ArrayList();
            Paint r42 = new Paint(1);
            this.bgPaint = r42;
            this.tabInnerPadding = AndroidUtilities.dp(14.0f);
            this.marginBetween = AndroidUtilities.dp(0.0f);
            this.selectRect = new RectF();
            this.resourcesProvider = r5;
            if (Theme.isCurrentThemeDark() == false) goto L6;
            this.color = -1862270977;
            this.selectedColor = -1325400065;
            r42.setColor(285212671);
            return;
        L6:
            int r1 = Theme.getColor(Theme.key_chat_wallpaper, r5);
            if ((r5 instanceof ChatActivity.ThemeDelegate) == false) goto L13;
            ChatActivity.ThemeDelegate r52 = (ChatActivity.ThemeDelegate) r5;
            if ((r52.getWallpaperDrawable() instanceof MotionBackgroundDrawable) == false) goto L13;
            int[] r53 = ((MotionBackgroundDrawable) r52.getWallpaperDrawable()).getColors();
            if (r53 == null) goto L13;
            r1 = AndroidUtilities.getAverageColor(AndroidUtilities.getAverageColor(r53[0], r53[1]), AndroidUtilities.getAverageColor(r53[2], r53[3]));
        L13:
            this.color = Theme.adaptHue(-1606201797, r1);
            this.selectedColor = Theme.adaptHue(-448573893, r1);
            r42.setColor(Theme.adaptHue(814980216, r1));
        }

        private int getHitTab(float r3, float r4) {
            int r0 = 0;
        L4:
            if (r0 >= this.tabs.size()) goto L10;
            if (this.tabs.get(r0).clickBounds.contains(r3, r4) == true) goto L8;
            r0 = r0 + 1;
            goto L4
        L8:
            return this.tabs.get(r0).id;
        L10:
            return -1;
        }

        public void addTab(int r2, String r3) {
            this.tabs.add(new Tab(r2, r3));
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r13) {
            boolean r1 = true;
            if (this.tabs.size() <= 1) goto L33;
            float r0 = this.selectedTab;
            double r2 = r0;
            int r4 = (int) Math.floor(r2);
            int r5 = 0;
            if (r4 >= 0) goto L8;
        L10:
            boolean r6 = false;
        L11:
            int r22 = (int) Math.ceil(r2);
            if (r22 >= 0) goto L14;
        L16:
            r1 = false;
        L17:
            if (r6 == false) goto L20;
            if (r1 == false) goto L20;
            AndroidUtilities.lerp(this.tabs.get(r4).bounds, this.tabs.get(r22).bounds, r0 - r4, this.selectRect);
        L24:
            Drawable r23 = this.bgDrawable;
            if (r23 == null) goto L27;
            r23.draw(r13);
        L27:
            if (r6 == true) goto L29;
            if (r1 == true) goto L29;
        L31:
            if (r5 >= this.tabs.size()) goto L36;
            Tab r12 = this.tabs.get(r5);
            r12.text.draw(r13, r12.bounds.left + this.tabInnerPadding, getMeasuredHeight() / 2.0f, ColorUtils.blendARGB(this.color, this.selectedColor, 1.0f - Math.abs(r0 - r5)), 1.0f);
            r5 = r5 + 1;
            goto L31
        L36:
            return;
        L29:
            r13.drawRoundRect(this.selectRect, AndroidUtilities.dp(13.0f), AndroidUtilities.dp(13.0f), this.bgPaint);
        L20:
            if (r6 == false) goto L22;
            this.selectRect.set(this.tabs.get(r4).bounds);
            goto L24
        L22:
            if (r1 == false) goto L24;
            this.selectRect.set(this.tabs.get(r22).bounds);
            goto L24
        L14:
            if (r22 >= this.tabs.size()) goto L16;
        L8:
            if (r4 >= this.tabs.size()) goto L10;
            r6 = true;
            goto L11
        }

        @Override // android.view.View
        public boolean dispatchTouchEvent(MotionEvent r6) {
            if (this.tabs.size() > 1) goto L5;
            return false;
        L5:
            int r0 = getHitTab(r6.getX(), r6.getY());
            if (r6.getAction() != 0) goto L11;
            if (r0 == (-1)) goto L9;
            return true;
        L9:
            return false;
        L11:
            if (r6.getAction() != 1) goto L16;
            if (r0 == (-1)) goto L16;
            Utilities.Callback<Integer> r5 = this.onTabClick;
            if (r5 == null) goto L16;
            r5.run(Integer.valueOf(r0));
        L16:
            return false;
        }

        public int getColor() {
            return this.color;
        }

        @Override // android.view.View
        public void onMeasure(int r12, int r13) {
            super.onMeasure(r12, r13);
            this.tabInnerPadding = AndroidUtilities.dp(14.0f);
            float r122 = 0.0f;
            this.marginBetween = AndroidUtilities.dp(0.0f);
            int r132 = 0;
            int r0 = 0;
        L4:
            if (r0 >= this.tabs.size()) goto L8;
            if (r0 <= 0) goto L7;
            r122 = r122 + this.marginBetween;
        L7:
            r122 = r122 + ((this.tabInnerPadding + this.tabs.get(r0).text.getWidth()) + this.tabInnerPadding);
            r0 = r0 + 1;
            goto L4
        L8:
            int r02 = getMeasuredWidth();
            int r1 = getMeasuredHeight();
            float r3 = (r1 - AndroidUtilities.dp(26.0f)) / 2.0f;
            float r2 = (AndroidUtilities.dp(26.0f) + r1) / 2.0f;
            float r03 = r02;
            float r5 = (r03 - r122) / 2.0f;
            float r6 = r5;
        L10:
            if (r132 >= this.tabs.size()) goto L12;
            float r7 = (this.tabInnerPadding + this.tabs.get(r132).text.getWidth()) + this.tabInnerPadding;
            this.tabs.get(r132).bounds.set(r6, r3, r6 + r7, r2);
            this.tabs.get(r132).clickBounds.set(this.tabs.get(r132).bounds);
            this.tabs.get(r132).clickBounds.inset((-this.marginBetween) / 2.0f, -r3);
            r6 = r6 + (r7 + this.marginBetween);
            r132 = r132 + 1;
            goto L10
        L12:
            Drawable r133 = this.bgDrawable;
            if (r133 == null) goto L20;
            Rect r22 = AndroidUtilities.rectTmp2;
            r133.getPadding(r22);
            int r14 = r1 / 2;
            this.bgDrawable.setBounds((((int) r5) - AndroidUtilities.dp(3.0f)) - r22.left, (r14 - AndroidUtilities.dp(16.0f)) - r22.top, (((int) ((r03 + r122) / 2.0f)) + AndroidUtilities.dp(3.0f)) + r22.right, (r14 + AndroidUtilities.dp(16.0f)) + r22.bottom);
            return;
        }

        @Override // android.view.View
        public void setBackground(Drawable r1) {
            this.bgDrawable = r1;
        }

        public void setOnTabClick(Utilities.Callback<Integer> r1) {
            this.onTabClick = r1;
        }

        public void setSelectedTab(float r1) {
            this.selectedTab = r1;
            invalidate();
        }
    }

    public static class ToggleButton extends View {
        private boolean first;
        RLottieToggleDrawable iconDrawable;
        private boolean isState1;
        final int minWidth;
        final String text1;
        final String text2;
        AnimatedTextView.AnimatedTextDrawable textDrawable;

        public ToggleButton(Context r9, int r10, String r11, int r12, String r13, Theme.ResourcesProvider r14) {
            super(r9);
            this.first = true;
            this.text1 = r11;
            this.text2 = r13;
            setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector, r14), 2));
            AnimatedTextView.AnimatedTextDrawable r1 = new AnimatedTextView.AnimatedTextDrawable(true, true, true);
            this.textDrawable = r1;
            r1.setAnimationProperties(0.35f, 0, 300, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.textDrawable.setTextSize(AndroidUtilities.dp(16.0f));
            this.textDrawable.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r14));
            this.textDrawable.setCallback(this);
            this.textDrawable.setEllipsizeByGradient(true ^ LocaleController.isRTL);
            if (LocaleController.isRTL == false) goto L5;
            this.textDrawable.setGravity(5);
        L5:
            int r92 = (int) (AndroidUtilities.dp(77.0f) + Math.max(this.textDrawable.getPaint().measureText(r11), this.textDrawable.getPaint().measureText(r13)));
            this.minWidth = r92;
            this.textDrawable.setOverrideFullWidth(r92);
            RLottieToggleDrawable r93 = new RLottieToggleDrawable(this, r10, r12);
            this.iconDrawable = r93;
            r93.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r14), PorterDuff.Mode.SRC_IN));
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r9) {
            boolean r0 = LocaleController.isRTL;
            RLottieToggleDrawable r1 = this.iconDrawable;
            if (r0 == false) goto L5;
            r1.setBounds(getMeasuredWidth() - AndroidUtilities.dp(41.0f), (getMeasuredHeight() - AndroidUtilities.dp(24.0f)) / 2, getMeasuredWidth() - AndroidUtilities.dp(17.0f), (getMeasuredHeight() + AndroidUtilities.dp(24.0f)) / 2);
            this.textDrawable.setBounds(0, 0, getMeasuredWidth() - AndroidUtilities.dp(59.0f), getMeasuredHeight());
        L6:
            this.textDrawable.draw(r9);
            this.iconDrawable.draw(r9);
            return;
        L5:
            r1.setBounds(AndroidUtilities.dp(17.0f), (getMeasuredHeight() - AndroidUtilities.dp(24.0f)) / 2, AndroidUtilities.dp(41.0f), (getMeasuredHeight() + AndroidUtilities.dp(24.0f)) / 2);
            this.textDrawable.setBounds(AndroidUtilities.dp(59.0f), 0, getMeasuredWidth(), getMeasuredHeight());
            goto L6
        }

        public boolean getState() {
            return this.isState1;
        }

        @Override // android.view.View
        public void onMeasure(int r3, int r4) {
            int r42 = View.MeasureSpec.getMode(r3);
            if (r42 != 1073741824) goto L5;
            int r32 = Math.max(View.MeasureSpec.getSize(r3), this.minWidth);
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r32, r42), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
            return;
        L5:
            r32 = Math.min(View.MeasureSpec.getSize(r3), this.minWidth);
            goto L6
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r3) {
            if (getVisibility() == 0) goto L5;
            return false;
        L5:
            if (getAlpha() >= 0.5f) goto L8;
            return false;
        L8:
            return super.onTouchEvent(r3);
        }

        public ToggleButton setColors(int r1, int r2) {
            setTextColor(r1);
            setIconColor(r2);
            return this;
        }

        public void setIconColor(int r4) {
            this.iconDrawable.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.SRC_IN));
            invalidate();
        }

        public void setSelectorColor(int r2) {
            setBackground(Theme.createSelectorDrawable(r2, 2));
        }

        public void setState(boolean r5, boolean r6) {
            if (this.first == false) goto L5;
        L7:
            this.isState1 = r5;
            AnimatedTextView.AnimatedTextDrawable r0 = this.textDrawable;
            if (r5 == false) goto L10;
            String r1 = this.text1;
        L12:
            if (r6 == true) goto L14;
        L16:
            boolean r3 = false;
        L17:
            r0.setText(r1, r3);
            this.iconDrawable.setState(r5, r6);
            this.first = false;
            setContentDescription(this.textDrawable.getText());
            return;
        L14:
            if (LocaleController.isRTL == true) goto L16;
            r3 = true;
            goto L17
        L10:
            r1 = this.text2;
            goto L12
        L5:
            if (r5 != this.isState1) goto L7;
        }

        public void setTextColor(int r2) {
            this.textDrawable.setTextColor(r2);
            invalidate();
        }

        @Override // android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.textDrawable) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: $r8$lambda$2rP3t7OvT2H6YzUFGirl-jGBmwE, reason: not valid java name */
    public static /* synthetic */ boolean m11263$r8$lambda$2rP3t7OvT2H6YzUFGirljGBmwE(MessagePreviewView r0, boolean r1, View r2, MotionEvent r3) {
        return r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$dCy3eu08PC23zgX8NfAqiWywVuM(MessagePreviewView r0, Integer r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentAccount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11264$$Nest$fgetcurrentAccount(MessagePreviewView r0) {
        return r0.currentAccount;
    }

    /* renamed from: -$$Nest$fgetdrawingGroups, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m11265$$Nest$fgetdrawingGroups(MessagePreviewView r0) {
        return r0.drawingGroups;
    }

    /* renamed from: -$$Nest$fgetiBlur3Factory, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawableViewFactory m11266$$Nest$fgetiBlur3Factory(MessagePreviewView r0) {
        return r0.iBlur3Factory;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ResourcesDelegate m11267$$Nest$fgetresourcesProvider(MessagePreviewView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$mgetThemedColor, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m11268$$Nest$mgetThemedColor(MessagePreviewView r0, int r1) {
        return r0.getThemedColor(r1);
    }

    @SuppressLint({"ClickableViewAccessibility"})
    public MessagePreviewView(Context r10, ChatActivity r11, BlurredBackgroundDrawableViewFactory r12, MessagePreviewParams r13, TLRPC.User r14, TLRPC.Chat r15, int r16, ResourcesDelegate r17, int r18, boolean r19) {
        super(r10);
        this.changeBoundsRunnable = new AnonymousClass1(this);
        this.drawingGroups = new ArrayList(10);
        this.showOutdatedQuote = r19;
        this.chatActivity = r11;
        this.currentAccount = r16;
        this.iBlur3Factory = r12;
        this.currentUser = r14;
        this.currentChat = r15;
        this.messagePreviewParams = r13;
        this.resourcesProvider = r17;
        this.viewPager = new AnonymousClass2(this, r10, r17);
        TabsView r112 = new TabsView(r10, r17);
        this.tabsView = r112;
        r112.setBackground(r12.create(r112).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(r17)).setHasPadding(true).setPadding(AndroidUtilities.dp(8.0f)).setRadius(AndroidUtilities.dp(16.0f)));
        int r122 = 0;
        int r0 = 0;
    L4:
        if (r122 >= 3) goto L25;
        if (r122 == 0) goto L7;
    L10:
        if (r122 != 1) goto L16;
        if (r13.forwardMessages == null) goto L16;
        if (r19 == true) goto L16;
        this.tabsView.addTab(1, LocaleController.getString(R.string.MessageOptionsForward));
    L21:
        if (r122 != r18) goto L24;
        r0 = this.tabsView.tabs.size() - 1;
    L24:
        r122 = r122 + 1;
    L16:
        if (r122 != 2) goto L24;
        if (r13.linkMessage == null) goto L24;
        if (r19 == true) goto L24;
        this.tabsView.addTab(2, LocaleController.getString(R.string.MessageOptionsLink));
        goto L21
    L7:
        if (r13.replyMessage == null) goto L10;
        this.tabsView.addTab(0, LocaleController.getString(R.string.MessageOptionsReply));
        goto L21
    L25:
        this.viewPager.setAdapter(new AnonymousClass3(this, r10));
        this.viewPager.setPosition(r0);
        this.tabsView.setSelectedTab(r0);
        addView(this.tabsView, LayoutHelper.createFrame(-1, 66, 87));
        addView(this.viewPager, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 66.0f));
        this.tabsView.setOnTabClick(new MessagePreviewView$$ExternalSyntheticLambda0(this));
        setOnTouchListener(new MessagePreviewView$$ExternalSyntheticLambda1(this, r19));
        this.showing = true;
        setAlpha(0.0f);
        setScaleX(0.95f);
        setScaleY(0.95f);
        animate().alpha(1.0f).scaleX(1.0f).setDuration(250).setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR).scaleY(1.0f);
        updateColors();
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$new$0(Integer r5) {
        if (this.tabsView.tabs.get(this.viewPager.getCurrentPosition()).id == r5.intValue()) goto L19;
        int r0 = 0;
        int r1 = 0;
    L7:
        if (r1 >= this.tabsView.tabs.size()) goto L13;
        if (this.tabsView.tabs.get(r1).id == r5.intValue()) goto L10;
        r1 = r1 + 1;
        goto L7
    L10:
        r0 = r1;
    L13:
        if (this.viewPager.getCurrentPosition() != r0) goto L15;
        return;
    L15:
        this.viewPager.scrollToPosition(r0);
        return;
    }

    private /* synthetic */ boolean lambda$new$1(boolean r1, View r2, MotionEvent r3) {
        if (r3.getAction() != 1) goto L6;
        if (r1 == true) goto L6;
        dismiss(true);
    L6:
        return true;
    }

    private void updateColors() {
    }

    public void didSendPressed() {
    }

    public void dismiss(boolean r5) {
        if (this.showing == false) goto L19;
        int r0 = 0;
        this.showing = false;
        animate().alpha(0.0f).scaleX(0.95f).scaleY(0.95f).setDuration(250).setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR).setListener(new AnonymousClass4(this, r5));
    L5:
        View[] r1 = this.viewPager.viewPages;
        if (r0 >= r1.length) goto L13;
        View r12 = r1[r0];
        if ((r12 instanceof Page) == false) goto L12;
        Page r13 = (Page) r12;
        if (r13.currentTab != 0) goto L12;
        r13.updateSelection();
    L12:
        r0 = r0 + 1;
    L13:
        onDismiss(r5);
        return;
    }

    public boolean isShowing() {
        return this.showing;
    }

    public boolean isTouchedHandle() {
        int r1 = 0;
    L3:
        View[] r2 = this.viewPager.viewPages;
        if (r1 >= r2.length) goto L12;
        View r22 = r2[r1];
        if (r22 == null) goto L11;
        if (((Page) r22).currentTab != 0) goto L11;
        return ((Page) r22).textSelectionHelper.isTouched();
    L11:
        r1 = r1 + 1;
        goto L3
    L12:
        return false;
    }

    public abstract void onDismiss(boolean r1);

    public abstract void onFullDismiss(boolean r1);

    public abstract void onQuoteSelectedPart();

    public void removeForward() {
    }

    public abstract void removeLink();

    public abstract void removeQuote();

    public abstract void removeReply();

    public abstract void selectAnotherChat(boolean r1);

    public void setSendAsPeer(TLRPC.Peer r4) {
        this.sendAsPeer = r4;
        int r42 = 0;
    L3:
        View[] r0 = this.viewPager.viewPages;
        if (r42 >= r0.length) goto L11;
        View r02 = r0[r42];
        if (r02 == null) goto L10;
        if (((Page) r02).currentTab != 1) goto L10;
        Page.m11289$$Nest$mupdateMessages((Page) r02);
    L10:
        r42 = r42 + 1;
        goto L3
    }

    public void updateAll() {
        int r1 = 0;
    L3:
        View[] r2 = this.viewPager.viewPages;
        if (r1 >= r2.length) goto L39;
        View r22 = r2[r1];
        if ((r22 instanceof Page) == false) goto L38;
        Page r23 = (Page) r22;
        int r3 = r23.currentTab;
        if (r3 != 1) goto L10;
        r23.messages = this.messagePreviewParams.forwardMessages;
    L15:
        Page.m11289$$Nest$mupdateMessages(r23);
        if (r23.currentTab != 0) goto L31;
        MessageObject r5 = null;
        if (this.showOutdatedQuote == true) goto L20;
    L29:
        this.messagePreviewParams.quote = null;
        r23.textSelectionHelper.clear();
        Page.m11287$$Nest$mswitchToQuote(r23, false, true);
    L30:
        Page.m11291$$Nest$mupdateSubtitle(r23, true);
        goto L31
    L20:
        if (this.messagePreviewParams.isSecret == true) goto L29;
        if (r23.textSelectionHelper.getSelectedCell() == null) goto L24;
        r5 = r23.textSelectionHelper.getSelectedCell().getMessageObject();
    L24:
        MessageObject r32 = r23.getReplyMessage(r5);
        if (r32 == null) goto L30;
        MessagePreviewParams r52 = this.messagePreviewParams;
        r52.quoteStart = 0;
        r52.quoteEnd = Math.min(MessagesController.getInstance(this.currentAccount).quoteLengthMax, r32.messageOwner.message.length());
        MessagePreviewParams r53 = this.messagePreviewParams;
        r53.quote = ChatActivity.ReplyQuote.from(r32, r53.quoteStart, r53.quoteEnd);
        View r33 = r23.getReplyMessageCell();
        if ((r33 instanceof ChatMessageCell) == false) goto L30;
        MessagePreviewParams r6 = this.messagePreviewParams;
        r23.textSelectionHelper.select((ChatMessageCell) r33, r6.quoteStart, r6.quoteEnd);
    L31:
        ToggleButton r24 = r23.changeSizeBtn;
        if (r24 == null) goto L38;
        ViewPropertyAnimator r25 = r24.animate();
        if (this.messagePreviewParams.hasMedia == false) goto L36;
        float r34 = 1.0f;
    L37:
        r25.alpha(r34).start();
        goto L38
    L36:
        r34 = 0.5f;
        goto L37
    L10:
        if (r3 != 0) goto L13;
        r23.messages = this.messagePreviewParams.replyMessage;
        goto L15
    L13:
        if (r3 != 2) goto L15;
        r23.messages = this.messagePreviewParams.linkMessage;
    L38:
        r1 = r1 + 1;
        goto L3
    }

    public void updateLink() {
        int r1 = 0;
    L3:
        View[] r2 = this.viewPager.viewPages;
        if (r1 >= r2.length) goto L29;
        View r22 = r2[r1];
        if (r22 == null) goto L28;
        if (((Page) r22).currentTab != 2) goto L28;
        Page r23 = (Page) r22;
        FrameLayout r3 = r23.changeSizeBtnContainer;
        MessagePreviewParams r4 = this.messagePreviewParams;
        if (r4.singleLink == true) goto L12;
    L14:
        int r42 = 0;
    L15:
        r3.setVisibility(r42);
        ToggleButton r32 = r23.changeSizeBtn;
        int r5 = 4;
        if (this.messagePreviewParams.isVideo == false) goto L18;
        int r43 = 4;
    L19:
        r32.setVisibility(r43);
        ToggleButton r33 = r23.videoChangeSizeBtn;
        if (this.messagePreviewParams.isVideo == false) goto L23;
        r5 = 0;
    L23:
        r33.setVisibility(r5);
        ViewPropertyAnimator r34 = r23.changeSizeBtnContainer.animate();
        if (this.messagePreviewParams.hasMedia == false) goto L26;
        float r44 = 1.0f;
    L27:
        r34.alpha(r44).start();
        r23.changeSizeBtn.setState(this.messagePreviewParams.webpageSmall, true);
        r23.videoChangeSizeBtn.setState(this.messagePreviewParams.webpageSmall, true);
        r23.changePositionBtn.setState(!this.messagePreviewParams.webpageTop, true);
        Page.m11289$$Nest$mupdateMessages(r23);
        goto L28
    L26:
        r44 = 0.5f;
        goto L27
    L18:
        r43 = 0;
        goto L19
    L12:
        if (r4.hasMedia == true) goto L14;
        r42 = 8;
    L28:
        r1 = r1 + 1;
        goto L3
    }

    public abstract void viewInChat();
}
