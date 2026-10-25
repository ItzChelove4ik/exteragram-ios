package org.telegram.ui.Components.Reactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.LayoutTransition;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.text.Editable;
import android.text.Layout;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.core.math.MathUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.MessagesStorage;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.browser.Browser;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stars;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.INavigationLayout;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.SlideIntChooseView;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SectionsScrollView;
import org.telegram.ui.SelectAnimatedEmojiDialog;

/* loaded from: classes7.dex */
public class ChatCustomReactionsEditActivity extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private UpdateReactionsButton actionButton;
    private FrameLayout actionButtonContainer;
    private ImageView actionButtonContainerGradient;
    private final List<TLRPC.TL_availableReaction> allAvailableReactions;
    private BackSpaceButtonView backSpaceButtonView;
    private TL_stories.TL_premium_boostsStatus boostsStatus;
    private FrameLayout bottomDialogLayout;
    private final long chatId;
    private final Runnable checkAfterFastDeleteRunnable;
    private LinearLayout contentLayout;
    private TLRPC.Chat currentChat;
    private int currentReactionsCount;
    private CustomReactionEditText editText;
    private boolean emojiKeyboardVisible;
    private TextCheckCell enableReactionsCell;
    private final TLRPC.ChatFull info;
    private boolean initialPaid;
    private final HashMap<Long, AnimatedEmojiSpan> initialSelectedEmojis;
    private boolean isPaused;
    private final int maxReactionsCount;
    private boolean paid;
    private TextCheckCell paidCheckCell;
    private int reactionsCount;
    private SectionsScrollView scrollView;
    private SelectAnimatedEmojiDialog selectAnimatedEmojiDialog;
    private int selectedCustomReactions;
    private final List<Long> selectedEmojisIds;
    private final HashMap<Long, AnimatedEmojiSpan> selectedEmojisMap;
    private int selectedType;
    private SlideIntChooseView slideView;
    private LinearLayout switchLayout;

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass1(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 == (-1)) goto L5;
            return;
        L5:
            if (ChatCustomReactionsEditActivity.m12327$$Nest$mcheckChangesBeforeExit(this.this$0, true) == true) goto L9;
            this.this$0.finishFragment();
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass10(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
            ChatCustomReactionsEditActivity.m12317$$Nest$fgetbottomDialogLayout(this.this$0).setVisibility(4);
            if (ChatCustomReactionsEditActivity.m12330$$Nest$misClearFocusNotWorking(this.this$0) == false) goto L6;
            ChatCustomReactionsEditActivity.m12325$$Nest$fgetswitchLayout(this.this$0).setFocusableInTouchMode(false);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatedEmojiSpan {
        private final Bitmap bitmap;
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass11(ChatCustomReactionsEditActivity r2, long r3, Paint.FontMetricsInt r5) {
            this.this$0 = r2;
            super(r3, r5);
            Bitmap r4 = Bitmap.createBitmap(AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f), Bitmap.Config.ARGB_8888);
            this.bitmap = r4;
            Drawable r1 = r2.getContext().getResources().getDrawable(R.drawable.star_small_inner).mutate();
            r1.setBounds(0, 0, AndroidUtilities.dp(24.0f), AndroidUtilities.dp(24.0f));
            r1.draw(new Canvas(r4));
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan, android.text.style.ReplacementSpan
        public void draw(Canvas r1, CharSequence r2, int r3, int r4, float r5, int r6, int r7, int r8, Paint r9) {
            super.draw(r1, r2, r3, r4, r5, r6, r7, r8, r9);
            r1.save();
            r1.translate(r5, ((r6 + r8) / 2.0f) - AndroidUtilities.dp(12.0f));
            float r22 = this.extraScale;
            r1.scale(r22, r22, r5 + AndroidUtilities.dp(12.0f), AndroidUtilities.dp(12.0f));
            r1.drawBitmap(this.bitmap, 0.0f, 0.0f, null);
            r1.restore();
        }

        @Override // org.telegram.ui.Components.AnimatedEmojiSpan, android.text.style.ReplacementSpan
        public int getSize(Paint r1, CharSequence r2, int r3, int r4, Paint.FontMetricsInt r5) {
            return super.getSize(r1, r2, r3, r4, r5) + AndroidUtilities.dp(5.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final AdjustPanLayoutHelper adjustPanLayoutHelper;
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$2$1, reason: invalid class name */
        public class AnonymousClass1 extends AdjustPanLayoutHelper {
            final /* synthetic */ AnonymousClass2 this$1;

            public static /* synthetic */ void $r8$lambda$mMtcSmCHg2V1_DctFRqw5QISUwE(AnonymousClass1 r0, boolean r1) {
                r0.lambda$onTransitionStart$0(r1);
            }

            public AnonymousClass1(AnonymousClass2 r1, View r2) {
                this.this$1 = r1;
                super(r2);
            }

            private /* synthetic */ void lambda$onTransitionStart$0(boolean r1) {
                if (r1 == false) goto L5;
                ChatCustomReactionsEditActivity.m12316$$Nest$fgetactionButtonContainer(this.this$1.this$0).setVisibility(4);
                return;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public boolean applyTranslation() {
                return false;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public boolean heightAnimationEnabled() {
                INavigationLayout r0 = this.this$1.this$0.getParentLayout();
                if (ChatCustomReactionsEditActivity.access$000(this.this$1.this$0) == false) goto L5;
                return false;
            L5:
                if (AndroidUtilities.isTablet() == false) goto L7;
                return false;
            L7:
                if (ChatCustomReactionsEditActivity.access$100(this.this$1.this$0) == false) goto L9;
                return false;
            L9:
                if (AndroidUtilities.isInMultiwindow == true) goto L18;
                if (r0 == null) goto L19;
                return true;
            L19:
                return false;
            L18:
                return false;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onPanTranslationUpdate(float r1, float r2, boolean r3) {
                if (this.this$1.this$0.getParentLayout() == null) goto L6;
                this.this$1.this$0.getParentLayout().isPreviewOpenAnimationInProgress();
                return;
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionEnd() {
            }

            @Override // org.telegram.ui.ActionBar.AdjustPanLayoutHelper
            public void onTransitionStart(boolean r2, int r3) {
                ChatCustomReactionsEditActivity.m12316$$Nest$fgetactionButtonContainer(this.this$1.this$0).setVisibility(0);
                ViewPropertyAnimator r32 = ChatCustomReactionsEditActivity.m12316$$Nest$fgetactionButtonContainer(this.this$1.this$0).animate();
                if (r2 == true) goto L5;
                float r0 = 1.0f;
            L6:
                r32.alpha(r0).withEndAction(new ChatCustomReactionsEditActivity$2$1$$ExternalSyntheticLambda0(this, r2)).start();
                return;
            L5:
                r0 = 0.0f;
                goto L6
            }
        }

        public AnonymousClass2(ChatCustomReactionsEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.adjustPanLayoutHelper = new AnonymousClass1(this, this);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onAttachedToWindow() {
            super.onAttachedToWindow();
            this.adjustPanLayoutHelper.onAttach();
        }

        @Override // android.view.ViewGroup, android.view.View
        public void onDetachedFromWindow() {
            super.onDetachedFromWindow();
            this.adjustPanLayoutHelper.onDetach();
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$3, reason: invalid class name */
    public class AnonymousClass3 extends CustomReactionEditText {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass3(ChatCustomReactionsEditActivity r1, Context r2, Theme.ResourcesProvider r3, int r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.EditTextCaption
        public void onLineCountChanged(int r1, int r2) {
            if (r2 <= r1) goto L5;
            ChatCustomReactionsEditActivity.m12321$$Nest$fgetscrollView(this.this$0).smoothScrollBy(0, AndroidUtilities.dp(30.0f));
            return;
        }

        @Override // org.telegram.ui.Components.EditTextCaption, org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.EditText, android.widget.TextView
        public boolean onTextContextMenuItem(int r2) {
            if (r2 == R.id.menu_delete) goto L17;
            if (r2 == 16908320) goto L17;
            if (r2 != 16908322) goto L10;
            return false;
        L10:
            if (r2 != 16908321) goto L13;
            return false;
        L13:
            return super.onTextContextMenuItem(r2);
        L17:
            return ChatCustomReactionsEditActivity.m12329$$Nest$mdeleteSelectedEmojis(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$4, reason: invalid class name */
    public class AnonymousClass4 extends FrameLayout {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass4(ChatCustomReactionsEditActivity r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (ChatCustomReactionsEditActivity.m12319$$Nest$fgetemojiKeyboardVisible(this.this$0) == false) goto L7;
            if (r1 == false) goto L8;
            ChatCustomReactionsEditActivity.m12316$$Nest$fgetactionButtonContainer(this.this$0).setTranslationY(-ChatCustomReactionsEditActivity.m12317$$Nest$fgetbottomDialogLayout(this.this$0).getMeasuredHeight());
            ChatCustomReactionsEditActivity r0 = this.this$0;
            ChatCustomReactionsEditActivity.m12331$$Nest$mupdateScrollViewMarginBottom(r0, ChatCustomReactionsEditActivity.m12317$$Nest$fgetbottomDialogLayout(r0).getMeasuredHeight());
            return;
        L8:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$5, reason: invalid class name */
    public class AnonymousClass5 extends SelectAnimatedEmojiDialog {
        private boolean firstLayout;
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        /* renamed from: $r8$lambda$-IE-NGH9rZBcG-Zcu1aC7X_TvLk, reason: not valid java name */
        public static /* synthetic */ void m12332$r8$lambda$IENGH9rZBcGZcu1aC7X_TvLk(AnonymousClass5 r0, AnimatedEmojiSpan r1) {
            r0.lambda$onEmojiSelected$0(r1);
        }

        public AnonymousClass5(ChatCustomReactionsEditActivity r1, BaseFragment r2, Context r3, boolean r4, Integer r5, int r6, boolean r7, Theme.ResourcesProvider r8, int r9, int r10) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10);
            this.firstLayout = true;
            setDrawBackground(false);
        }

        private /* synthetic */ void lambda$onEmojiSelected$0(AnimatedEmojiSpan r6) {
            SpannableStringBuilder r0 = new SpannableStringBuilder(ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).getText());
            int r3 = 0;
            AnimatedEmojiSpan[] r1 = (AnimatedEmojiSpan[]) r0.getSpans(0, r0.length(), AnimatedEmojiSpan.class);
            int r2 = r1.length;
        L3:
            if (r3 >= r2) goto L12;
            AnimatedEmojiSpan r4 = r1[r3];
            if (r4 == r6) goto L6;
            r3 = r3 + 1;
            goto L3
        L6:
            int r62 = ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).getEditTextSelectionEnd();
            int r12 = r0.getSpanEnd(r4);
            int r02 = r0.getSpanStart(r4);
            ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).getText().delete(r02, r12);
            int r03 = r12 - r02;
            CustomReactionEditText r5 = ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0);
            if (r12 > r62) goto L9;
            r62 = r62 - r03;
        L9:
            r5.setSelection(r62);
            return;
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog
        public void onEmojiSelected(View r4, Long r5, TLRPC.Document r6, TL_stars.TL_starGiftUnique r7, Integer r8) {
            boolean r42 = ChatCustomReactionsEditActivity.m12324$$Nest$fgetselectedEmojisMap(this.this$0).containsKey(r5);
            ChatCustomReactionsEditActivity r72 = this.this$0;
            if (r42 == false) goto L6;
            ChatCustomReactionsEditActivity.m12323$$Nest$fgetselectedEmojisIds(r72).remove(r5);
            AnimatedEmojiSpan r43 = (AnimatedEmojiSpan) ChatCustomReactionsEditActivity.m12324$$Nest$fgetselectedEmojisMap(this.this$0).remove(r5);
            r43.setRemoved(new ChatCustomReactionsEditActivity$5$$ExternalSyntheticLambda0(this, r43));
            ChatCustomReactionsEditActivity.m12326$$Nest$manimateChangesInNextRows(this.this$0, r43);
            ChatCustomReactionsEditActivity.m12322$$Nest$fgetselectAnimatedEmojiDialog(this.this$0).setMultiSelected(r5, true);
            ChatCustomReactionsEditActivity.m12328$$Nest$mcheckMaxCustomReactions(this.this$0, false);
            return;
        L6:
            int r44 = ChatCustomReactionsEditActivity.m12324$$Nest$fgetselectedEmojisMap(r72).size() - (ChatCustomReactionsEditActivity.m12324$$Nest$fgetselectedEmojisMap(this.this$0).containsKey(-1L) ? 1 : 0);
            int r73 = ChatCustomReactionsEditActivity.m12320$$Nest$fgetmaxReactionsCount(this.this$0);
            ChatCustomReactionsEditActivity r1 = this.this$0;
            if (r44 < r73) goto L15;
            BulletinFactory.of(r1).createErrorBulletin(LocaleController.formatPluralString("ReactionMaxCountError", ChatCustomReactionsEditActivity.m12320$$Nest$fgetmaxReactionsCount(this.this$0), new Object[0])).show();
            return;
        L15:
            int r45 = ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(r1).getEditTextSelectionEnd();     // Catch: Exception -> L12
            SpannableString r74 = new SpannableString("b");     // Catch: Exception -> L12
            AnimatedEmojiSpan r62 = ReactionsUtils.createAnimatedEmojiSpan(r6, r5, ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).getFontMetricsInt());     // Catch: Exception -> L12
            r62.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();     // Catch: Exception -> L12
            r62.setAdded();     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12323$$Nest$fgetselectedEmojisIds(this.this$0).add(MathUtils.clamp(r45, 0, ChatCustomReactionsEditActivity.m12323$$Nest$fgetselectedEmojisIds(this.this$0).size()), r5);     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12324$$Nest$fgetselectedEmojisMap(this.this$0).put(r5, r62);     // Catch: Exception -> L12
            r74.setSpan(r62, 0, r74.length(), 33);     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).getText().insert(r45, r74);     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).setSelection(r45 + r74.length());     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12322$$Nest$fgetselectAnimatedEmojiDialog(this.this$0).setMultiSelected(r5, true);     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12328$$Nest$mcheckMaxCustomReactions(this.this$0, true);     // Catch: Exception -> L12
            ChatCustomReactionsEditActivity.m12326$$Nest$manimateChangesInNextRows(this.this$0, r62);     // Catch: Exception -> L12
            return;
        L12:
            e = move-exception;
            FileLog.e(e);
        }

        @Override // org.telegram.ui.SelectAnimatedEmojiDialog, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.firstLayout == false) goto L6;
            this.firstLayout = false;
            ChatCustomReactionsEditActivity.m12322$$Nest$fgetselectAnimatedEmojiDialog(this.this$0).onShow(null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass6(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).setFocusableInTouchMode(true);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass7(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            ChatCustomReactionsEditActivity.m12316$$Nest$fgetactionButtonContainer(this.this$0).setVisibility(4);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass8(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            ChatCustomReactionsEditActivity.m12318$$Nest$fgeteditText(this.this$0).setFocusableInTouchMode(false);
            ChatCustomReactionsEditActivity.m12325$$Nest$fgetswitchLayout(this.this$0).setVisibility(4);
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatCustomReactionsEditActivity$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatCustomReactionsEditActivity this$0;

        public AnonymousClass9(ChatCustomReactionsEditActivity r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
        }
    }

    public static /* synthetic */ void $r8$lambda$0MYD7HkNmp0LxLXpzRE6BGP0sgE(ChatCustomReactionsEditActivity r0, Boolean r1) {
        r0.lambda$initSelectAnimatedEmojiDialog$11(r1);
    }

    /* renamed from: $r8$lambda$1YP_Iu-OWIUWYMB49qb7O-M4Vl4, reason: not valid java name */
    public static /* synthetic */ void m12311$r8$lambda$1YP_IuOWIUWYMB49qb7OM4Vl4(ChatCustomReactionsEditActivity r0) {
        r0.lambda$onResume$13();
    }

    /* renamed from: $r8$lambda$5Hqwp_yk7p_iH-SmTZO81Oh_Xpo, reason: not valid java name */
    public static /* synthetic */ void m12312$r8$lambda$5Hqwp_yk7p_iHSmTZO81Oh_Xpo(ChatCustomReactionsEditActivity r0, AnimatedEmojiSpan r1) {
        r0.lambda$toggleStarsEnabled$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$78_YXBPKlzr1MFRiokbGyVuDXTM(ChatCustomReactionsEditActivity r0, AnimatedEmojiSpan r1, int r2) {
        r0.lambda$initSelectAnimatedEmojiDialog$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$DMiMKd2qOLIZkRWap8gJX5eKFDY(ChatCustomReactionsEditActivity r0) {
        r0.showKeyboard();
    }

    /* renamed from: $r8$lambda$FUJ-7x_Wtt39p1JE7QE6wBjZu60, reason: not valid java name */
    public static /* synthetic */ void m12313$r8$lambda$FUJ7x_Wtt39p1JE7QE6wBjZu60(ChatCustomReactionsEditActivity r0, TLRPC.TL_error r1) {
        r0.lambda$createView$7(r1);
    }

    public static /* synthetic */ void $r8$lambda$HXeb9TjvPlQ1Ky5SCKNd0ptIXaE() {
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.startAllHeavyOperations, new Object[]{512});
    }

    public static /* synthetic */ void $r8$lambda$HzDaTqsDWy2R8Th_juYZhJlyCS4(ChatCustomReactionsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkChangesBeforeExit$15(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Kfqn4UNQOkLDGnv2UCnbDvtNJBk(ChatCustomReactionsEditActivity r0, ValueAnimator r1) {
        r0.lambda$showKeyboard$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$LdHm8GQqLT5_1p9Es9OSUpoQvTI(ChatCustomReactionsEditActivity r0) {
        r0.lambda$new$0();
    }

    public static /* synthetic */ void $r8$lambda$PkAIRgP6l6g_7ves6HlTnVT7naY(ChatCustomReactionsEditActivity r0) {
        r0.lambda$createView$6();
    }

    public static /* synthetic */ void $r8$lambda$a0zaSRgJWR6V3zGsfVnkepRwVwQ(ChatCustomReactionsEditActivity r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$c_jXeEoHYBIz5TOhFwx35uPmy5U(ChatCustomReactionsEditActivity r0, TL_stories.TL_premium_boostsStatus r1) {
        r0.lambda$onFragmentCreate$1(r1);
    }

    /* renamed from: $r8$lambda$fdE6XdD-93Xcrv2OFMGD1vc0Nxk, reason: not valid java name */
    public static /* synthetic */ void m12314$r8$lambda$fdE6XdD93Xcrv2OFMGD1vc0Nxk(ChatCustomReactionsEditActivity r0) {
        r0.lambda$createView$3();
    }

    public static /* synthetic */ void $r8$lambda$g0tdanQjcENkrNyzFz8iNwFRISg(ChatCustomReactionsEditActivity r0, AlertDialog r1, int r2) {
        r0.lambda$checkChangesBeforeExit$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$gCOIXAqf4f35kgfIMVQTjPLOMVI(ChatCustomReactionsEditActivity r0, ValueAnimator r1) {
        r0.lambda$closeKeyboard$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$gb9IRekFsCQHOH8794zM0V4HULg(ChatCustomReactionsEditActivity r0, TLRPC.TL_error r1) {
        r0.lambda$createView$8(r1);
    }

    public static /* synthetic */ void $r8$lambda$oVyv19ncIKkGIQduBEMjPfX5XoY(ChatCustomReactionsEditActivity r0, Integer r1) {
        r0.lambda$createView$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$sZJ1tvbSgp7m_iXj0lBhldyMbLU(ChatCustomReactionsEditActivity r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    /* renamed from: $r8$lambda$ve-UnHeXHS0dzLoM5Jl15w5hSqU, reason: not valid java name */
    public static /* synthetic */ void m12315$r8$lambda$veUnHeXHS0dzLoM5Jl15w5hSqU(ChatCustomReactionsEditActivity r0, View r1) {
        r0.lambda$createView$9(r1);
    }

    /* renamed from: -$$Nest$fgetactionButtonContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12316$$Nest$fgetactionButtonContainer(ChatCustomReactionsEditActivity r0) {
        return r0.actionButtonContainer;
    }

    /* renamed from: -$$Nest$fgetbottomDialogLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m12317$$Nest$fgetbottomDialogLayout(ChatCustomReactionsEditActivity r0) {
        return r0.bottomDialogLayout;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ CustomReactionEditText m12318$$Nest$fgeteditText(ChatCustomReactionsEditActivity r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgetemojiKeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12319$$Nest$fgetemojiKeyboardVisible(ChatCustomReactionsEditActivity r0) {
        return r0.emojiKeyboardVisible;
    }

    /* renamed from: -$$Nest$fgetmaxReactionsCount, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m12320$$Nest$fgetmaxReactionsCount(ChatCustomReactionsEditActivity r0) {
        return r0.maxReactionsCount;
    }

    /* renamed from: -$$Nest$fgetscrollView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SectionsScrollView m12321$$Nest$fgetscrollView(ChatCustomReactionsEditActivity r0) {
        return r0.scrollView;
    }

    /* renamed from: -$$Nest$fgetselectAnimatedEmojiDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SelectAnimatedEmojiDialog m12322$$Nest$fgetselectAnimatedEmojiDialog(ChatCustomReactionsEditActivity r0) {
        return r0.selectAnimatedEmojiDialog;
    }

    /* renamed from: -$$Nest$fgetselectedEmojisIds, reason: not valid java name */
    public static /* bridge */ /* synthetic */ List m12323$$Nest$fgetselectedEmojisIds(ChatCustomReactionsEditActivity r0) {
        return r0.selectedEmojisIds;
    }

    /* renamed from: -$$Nest$fgetselectedEmojisMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m12324$$Nest$fgetselectedEmojisMap(ChatCustomReactionsEditActivity r0) {
        return r0.selectedEmojisMap;
    }

    /* renamed from: -$$Nest$fgetswitchLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m12325$$Nest$fgetswitchLayout(ChatCustomReactionsEditActivity r0) {
        return r0.switchLayout;
    }

    /* renamed from: -$$Nest$manimateChangesInNextRows, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12326$$Nest$manimateChangesInNextRows(ChatCustomReactionsEditActivity r0, AnimatedEmojiSpan r1) {
        r0.animateChangesInNextRows(r1);
    }

    /* renamed from: -$$Nest$mcheckChangesBeforeExit, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12327$$Nest$mcheckChangesBeforeExit(ChatCustomReactionsEditActivity r0, boolean r1) {
        return r0.checkChangesBeforeExit(r1);
    }

    /* renamed from: -$$Nest$mcheckMaxCustomReactions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12328$$Nest$mcheckMaxCustomReactions(ChatCustomReactionsEditActivity r0, boolean r1) {
        r0.checkMaxCustomReactions(r1);
    }

    /* renamed from: -$$Nest$mdeleteSelectedEmojis, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12329$$Nest$mdeleteSelectedEmojis(ChatCustomReactionsEditActivity r0) {
        return r0.deleteSelectedEmojis();
    }

    /* renamed from: -$$Nest$misClearFocusNotWorking, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m12330$$Nest$misClearFocusNotWorking(ChatCustomReactionsEditActivity r0) {
        return r0.isClearFocusNotWorking();
    }

    /* renamed from: -$$Nest$mupdateScrollViewMarginBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12331$$Nest$mupdateScrollViewMarginBottom(ChatCustomReactionsEditActivity r0, int r1) {
        r0.updateScrollViewMarginBottom(r1);
    }

    public ChatCustomReactionsEditActivity(long r2, TLRPC.ChatFull r4) {
        this.selectedEmojisMap = new LinkedHashMap();
        this.selectedEmojisIds = new ArrayList();
        this.initialSelectedEmojis = new LinkedHashMap();
        this.allAvailableReactions = new ArrayList();
        this.maxReactionsCount = getMessagesController().boostsChannelLevelMax;
        this.emojiKeyboardVisible = false;
        this.selectedType = -1;
        this.checkAfterFastDeleteRunnable = new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda9(this);
        this.chatId = r2;
        this.info = r4;
    }

    public static /* synthetic */ boolean access$000(ChatCustomReactionsEditActivity r0) {
        return r0.inPreviewMode;
    }

    public static /* synthetic */ boolean access$100(ChatCustomReactionsEditActivity r0) {
        return r0.inBubbleMode;
    }

    private void animateChangesInNextRows(AnimatedEmojiSpan r3) {
        Editable r0 = this.editText.getText();
        Layout r2 = this.editText.getLayout();
        int r32 = r2.getLineForOffset(r0.getSpanStart(r3)) + 1;
        if (r32 >= r2.getLineCount()) goto L7;
        AnimatedEmojiSpan[] r22 = (AnimatedEmojiSpan[]) r0.getSpans(r2.getLineStart(r32), r0.length(), AnimatedEmojiSpan.class);
        int r33 = r22.length;
        int r02 = 0;
    L5:
        if (r02 >= r33) goto L9;
        r22[r02].setAnimateChanges();
        r02 = r02 + 1;
        goto L5
    L9:
        return;
    }

    private boolean checkChangesBeforeExit(boolean r5) {
        boolean r1 = true;
        boolean r0 = !this.selectedEmojisMap.keySet().equals(this.initialSelectedEmojis.keySet());
        TL_stories.TL_premium_boostsStatus r2 = this.boostsStatus;
        if (r2 == null) goto L8;
        if (r2.level >= this.selectedCustomReactions) goto L8;
        r0 = false;
    L8:
        if (this.initialPaid != this.paid) goto L11;
        r1 = r0;
    L11:
        if (r5 == false) goto L14;
        if (r1 == false) goto L14;
        AlertDialog.Builder r52 = new AlertDialog.Builder(getContext(), getResourceProvider());
        r52.setTitle(LocaleController.getString("UnsavedChanges", R.string.UnsavedChanges));
        r52.setMessage(LocaleController.getString("ReactionApplyChangesDialog", R.string.ReactionApplyChangesDialog));
        r52.setPositiveButton(LocaleController.getString("ApplyTheme", R.string.ApplyTheme), new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda10(this));
        r52.setNegativeButton(LocaleController.getString(R.string.Discard), new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda11(this));
        r52.show();
    L14:
        return r1;
    }

    private void checkMaxCustomReactions(boolean r3) {
        if (this.boostsStatus != null) goto L6;
        return;
    L6:
        if (this.selectedType != 0) goto L8;
        this.selectedType = 1;
    L8:
        int r0 = grabReactions(true).size();
        this.selectedCustomReactions = r0;
        if (this.boostsStatus.level >= r0) goto L14;
        if (r3 == false) goto L12;
        SpannableStringBuilder r32 = AndroidUtilities.replaceTags(LocaleController.formatPluralString("ReactionReachLvlForReactionShort", r0, new Object[]{Integer.valueOf(r0)}));
        BulletinFactory.of(this).createSimpleBulletin(R.raw.chats_infotip, r32).show();
    L12:
        this.actionButton.setLvlRequiredState(this.selectedCustomReactions);
        return;
    L14:
        this.actionButton.removeLvlRequiredState();
    }

    private boolean closeKeyboard() {
        if (this.emojiKeyboardVisible == false) goto L10;
        this.emojiKeyboardVisible = false;
        if (isClearFocusNotWorking() == false) goto L7;
        this.switchLayout.setFocusableInTouchMode(true);
        this.switchLayout.requestFocus();
    L8:
        updateScrollViewMarginBottom(0);
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        this.bottomDialogLayout.animate().setListener(null).cancel();
        this.bottomDialogLayout.animate().translationY(this.bottomDialogLayout.getMeasuredHeight()).setDuration(350).withLayer().setInterpolator(CubicBezierInterpolator.DEFAULT).setUpdateListener(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda8(this)).setListener(new AnonymousClass10(this)).start();
        return true;
    L7:
        this.editText.clearFocus();
        goto L8
    L10:
        return false;
    }

    private boolean deleteSelectedEmojis() {
        int r0 = this.editText.getEditTextSelectionEnd();
        int r1 = this.editText.getEditTextSelectionStart();
        SpannableStringBuilder r2 = new SpannableStringBuilder(this.editText.getText());
        if (this.editText.hasSelection() == false) goto L9;
        AnimatedEmojiSpan[] r02 = (AnimatedEmojiSpan[]) r2.getSpans(r1, r0, AnimatedEmojiSpan.class);
        int r12 = r02.length;
        int r22 = 0;
    L5:
        if (r22 >= r12) goto L7;
        AnimatedEmojiSpan r3 = r02[r22];
        this.selectedEmojisMap.remove(Long.valueOf(r3.documentId));
        this.selectedEmojisIds.remove(Long.valueOf(r3.documentId));
        this.selectAnimatedEmojiDialog.unselect(Long.valueOf(r3.documentId));
        r22 = r22 + 1;
        goto L5
    L7:
        this.editText.dispatchKeyEvent(new KeyEvent(0, 67));
        checkMaxCustomReactions(false);
        return true;
    L9:
        return false;
    }

    private List<TLRPC.Reaction> grabReactions(boolean r11) {
        ArrayList r0 = new ArrayList();
        ArrayList r1 = new ArrayList();
        Iterator<Long> r2 = this.selectedEmojisIds.iterator();
    L4:
        if (r2.hasNext() == false) goto L15;
        Long r3 = r2.next();
        if (r3.longValue() == (-1)) goto L4;
        Iterator<TLRPC.TL_availableReaction> r4 = this.allAvailableReactions.iterator();
    L10:
        if (r4.hasNext() == false) goto L14;
        TLRPC.TL_availableReaction r5 = r4.next();
        if (r3.longValue() != r5.activate_animation.id) goto L10;
        TLRPC.TL_reactionEmoji r32 = new TLRPC.TL_reactionEmoji();
        r32.emoticon = r5.reaction;
        r0.add(r32);
        goto L4
    L14:
        TLRPC.TL_reactionCustomEmoji r42 = new TLRPC.TL_reactionCustomEmoji();
        r42.document_id = r3.longValue();
        r0.add(r42);
        r1.add(r42);
        goto L4
    L15:
        if (r11 == false) goto L17;
        return r1;
    L17:
        return r0;
    }

    private void initSelectAnimatedEmojiDialog() {
        if (this.selectAnimatedEmojiDialog != null) goto L9;
        int r12 = Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, getResourceProvider());
        AnonymousClass5 r2 = new AnonymousClass5(this, this, getContext(), false, null, 6, false, getResourceProvider(), 16, r12);
        this.selectAnimatedEmojiDialog = r2;
        r2.setAnimationsEnabled(false);
        this.selectAnimatedEmojiDialog.setClipChildren(false);
        this.selectAnimatedEmojiDialog.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        this.bottomDialogLayout.addView(this.selectAnimatedEmojiDialog, LayoutHelper.createFrame(-1, -2, 80));
        BackSpaceButtonView r0 = new BackSpaceButtonView(getContext(), getResourceProvider());
        this.backSpaceButtonView = r0;
        r0.setOnBackspace(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda15(this));
        this.bottomDialogLayout.addView(this.backSpaceButtonView, LayoutHelper.createFrame(-1, -2.0f, 85, 0.0f, 0.0f, 8.0f, 8.0f));
        Iterator<Long> r02 = this.selectedEmojisIds.iterator();
    L7:
        if (r02.hasNext() == false) goto L11;
        Long r1 = r02.next();
        this.selectAnimatedEmojiDialog.setMultiSelected(r1, false);
        goto L7
    L11:
        return;
    }

    private boolean isClearFocusNotWorking() {
        if (Build.MODEL.toLowerCase().startsWith("zte") == true) goto L5;
        return false;
    L5:
        if (Build.VERSION.SDK_INT > 28) goto L10;
        return true;
    L10:
        return false;
    }

    private /* synthetic */ void lambda$checkChangesBeforeExit$14(AlertDialog r1, int r2) {
        this.actionButton.performClick();
    }

    private /* synthetic */ void lambda$checkChangesBeforeExit$15(AlertDialog r1, int r2) {
        finishFragment();
    }

    private /* synthetic */ void lambda$closeKeyboard$17(ValueAnimator r3) {
        this.actionButtonContainer.setTranslationY((-(1.0f - ((Float) r3.getAnimatedValue()).floatValue())) * this.bottomDialogLayout.getMeasuredHeight());
    }

    private /* synthetic */ void lambda$createView$2(View r3) {
        if (this.enableReactionsCell.isChecked() == false) goto L10;
        TextCheckCell r32 = this.paidCheckCell;
        if (r32 == null) goto L10;
        if (r32.isChecked() == false) goto L10;
        toggleStarsEnabled();
    L10:
        if (this.enableReactionsCell.isChecked() == false) goto L12;
        int r33 = 2;
    L14:
        if (this.enableReactionsCell.isChecked() == false) goto L16;
        boolean r1 = false;
    L17:
        setCheckedEnableReactionCell(r33, r1, true);
        return;
    L16:
        r1 = this.paid;
        goto L17
    L12:
        r33 = 1;
        goto L14
    }

    private /* synthetic */ void lambda$createView$3() {
        Browser.openUrl(getContext(), "https://t.me/stickers");
    }

    private /* synthetic */ void lambda$createView$4(Integer r1) {
        this.reactionsCount = r1.intValue();
    }

    private /* synthetic */ void lambda$createView$5(View r1) {
        toggleStarsEnabled();
    }

    private /* synthetic */ void lambda$createView$6() {
        Browser.openUrl(getContext(), LocaleController.getString(R.string.ChannelEnablePaidReactionsInfoLink));
    }

    private /* synthetic */ void lambda$createView$7(TLRPC.TL_error r3) {
        if (this.boostsStatus != null) goto L5;
    L8:
        String r32 = r3.text;
        if (r32.equals("REACTIONS_TOO_MANY") == false) goto L11;
        r32 = LocaleController.formatPluralString("ReactionMaxCountError", this.maxReactionsCount, new Object[0]);
    L11:
        BulletinFactory.of(this).createErrorBulletin(r32).show();
        return;
    L5:
        if (r3.text.equals("BOOSTS_REQUIRED") == false) goto L8;
        ReactionsUtils.showLimitReachedDialogForReactions(-this.chatId, this.selectedCustomReactions, this.boostsStatus);
    }

    private /* synthetic */ void lambda$createView$8(TLRPC.TL_error r3) {
        if (isFinishing() == false) goto L5;
        return;
    L5:
        this.actionButton.setLoading(false);
        if (r3.text.equals("CHAT_NOT_MODIFIED") == false) goto L9;
        finishFragment();
        return;
    L9:
        ChatCustomReactionsEditActivity$$ExternalSyntheticLambda20 r0 = new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda20(this, r3);
        if (this.boostsStatus != null) goto L12;
        long r2 = 200;
    L13:
        AndroidUtilities.runOnUIThread(r0, r2);
        return;
    L12:
        r2 = 0;
        goto L13
    }

    private /* synthetic */ void lambda$createView$9(View r10) {
        if (this.actionButton.isLoading() == false) goto L5;
        return;
    L5:
        TL_stories.TL_premium_boostsStatus r102 = this.boostsStatus;
        if (r102 == null) goto L11;
        int r0 = r102.level;
        int r1 = this.selectedCustomReactions;
        if (r0 >= r1) goto L11;
        ReactionsUtils.showLimitReachedDialogForReactions(-this.chatId, r1, r102);
        return;
    L11:
        TextCheckCell r103 = this.paidCheckCell;
        if (r103 != null) goto L14;
    L17:
        Boolean r104 = null;
    L16:
        Boolean r6 = r104;
        this.actionButton.setLoading(true);
        MessagesController r02 = getMessagesController();
        long r12 = this.chatId;
        int r3 = this.selectedType;
        List<TLRPC.Reaction> r4 = grabReactions(false);
        int r5 = this.reactionsCount;
        this.currentReactionsCount = r5;
        r02.setCustomChatReactions(r12, r3, r4, r5, r6, new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda17(this), new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda18(this));
        return;
    L14:
        if (this.info.paid_media_allowed == false) goto L17;
        r104 = Boolean.valueOf(r103.isChecked());
        goto L16
    }

    private /* synthetic */ void lambda$initSelectAnimatedEmojiDialog$10(AnimatedEmojiSpan r4, int r5) {
        Editable r0 = this.editText.getText();
        int r1 = r0.getSpanStart(r4);
        int r42 = r0.getSpanEnd(r4);
        int r02 = r42 - r1;
        if (r1 == (-1)) goto L8;
        if (r42 == (-1)) goto L9;
        this.editText.getText().delete(r1, r42);
        CustomReactionEditText r3 = this.editText;
        r3.setSelection(Math.min(r5 - r02, r3.getText().length()));
        return;
    L9:
        return;
    }

    private /* synthetic */ void lambda$initSelectAnimatedEmojiDialog$11(Boolean r10) {
        if (deleteSelectedEmojis() == true) goto L21;
        int r0 = this.editText.getEditTextSelectionEnd();
        SpannableStringBuilder r1 = new SpannableStringBuilder(this.editText.getText());
        AnimatedEmojiSpan[] r2 = (AnimatedEmojiSpan[]) r1.getSpans(0, r1.length(), AnimatedEmojiSpan.class);
        int r3 = r2.length;
        int r5 = 0;
    L6:
        if (r5 >= r3) goto L24;
        AnimatedEmojiSpan r6 = r2[r5];
        if (r1.getSpanEnd(r6) == r0) goto L9;
        r5 = r5 + 1;
        goto L6
    L9:
        this.selectedEmojisMap.remove(Long.valueOf(r6.documentId));
        this.selectedEmojisIds.remove(Long.valueOf(r6.documentId));
        this.selectAnimatedEmojiDialog.unselect(Long.valueOf(r6.documentId));
        if (r6.documentId != (-1)) goto L15;
        TextCheckCell r12 = this.paidCheckCell;
        if (r12 == null) goto L15;
        r12.setChecked(false);
        this.editText.setMaxLength(this.maxReactionsCount);
    L15:
        if (r10.booleanValue() == false) goto L18;
        this.editText.dispatchKeyEvent(new KeyEvent(0, 67));
        AndroidUtilities.cancelRunOnUIThread(this.checkAfterFastDeleteRunnable);
        AndroidUtilities.runOnUIThread(this.checkAfterFastDeleteRunnable, 350);
        return;
    L18:
        r6.setRemoved(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda19(this, r6, r0));
        animateChangesInNextRows(r6);
        checkMaxCustomReactions(false);
        return;
    L24:
        return;
    }

    private /* synthetic */ void lambda$new$0() {
        checkMaxCustomReactions(false);
    }

    private /* synthetic */ void lambda$onFragmentCreate$1(TL_stories.TL_premium_boostsStatus r2) {
        this.boostsStatus = r2;
        if (this.selectedEmojisMap.keySet().equals(this.initialSelectedEmojis.keySet()) == true) goto L6;
        checkMaxCustomReactions(false);
        return;
    }

    private /* synthetic */ void lambda$onResume$13() {
        this.editText.requestFocus();
    }

    private /* synthetic */ void lambda$showKeyboard$16(ValueAnimator r2) {
        this.actionButtonContainer.setTranslationY((-((Float) r2.getAnimatedValue()).floatValue()) * this.bottomDialogLayout.getMeasuredHeight());
    }

    private /* synthetic */ void lambda$toggleStarsEnabled$18(AnimatedEmojiSpan r6) {
        SpannableStringBuilder r0 = new SpannableStringBuilder(this.editText.getText());
        int r3 = 0;
        AnimatedEmojiSpan[] r1 = (AnimatedEmojiSpan[]) r0.getSpans(0, r0.length(), AnimatedEmojiSpan.class);
        int r2 = r1.length;
    L3:
        if (r3 >= r2) goto L12;
        AnimatedEmojiSpan r4 = r1[r3];
        if (r4 == r6) goto L6;
        r3 = r3 + 1;
        goto L3
    L6:
        int r62 = this.editText.getEditTextSelectionEnd();
        int r12 = r0.getSpanEnd(r4);
        int r02 = r0.getSpanStart(r4);
        this.editText.getText().delete(r02, r12);
        int r03 = r12 - r02;
        CustomReactionEditText r5 = this.editText;
        if (r12 > r62) goto L9;
        r62 = r62 - r03;
    L9:
        r5.setSelection(r62);
        return;
    }

    private void setCheckedEnableReactionCell(int r9, boolean r10, boolean r11) {
        if (this.selectedType == r9) goto L5;
    L7:
        this.paid = r10;
        if (r9 == 1) goto L13;
        if (r9 == 0) goto L13;
        if (r10 == true) goto L13;
        boolean r2 = false;
    L14:
        this.enableReactionsCell.setChecked(r2);
        if (r2 == false) goto L17;
        int r3 = Theme.key_windowBackgroundChecked;
    L18:
        int r32 = Theme.getColor(r3);
        if (r11 == false) goto L24;
        TextCheckCell r4 = this.enableReactionsCell;
        if (r2 == false) goto L23;
        r4.setBackgroundColorAnimated(true, r32);
    L25:
        this.selectedType = r9;
        if (r9 == 1) goto L35;
        if (r9 == 0) goto L35;
        if (r10 == true) goto L35;
        if (r11 == false) goto L33;
        closeKeyboard();
        this.actionButtonContainer.animate().setListener(null).cancel();
        this.switchLayout.animate().setListener(null).cancel();
        ViewPropertyAnimator r92 = this.actionButtonContainer.animate().alpha(0.0f).setDuration(350);
        CubicBezierInterpolator r112 = CubicBezierInterpolator.DEFAULT;
        r92.setInterpolator(r112).setListener(new AnonymousClass7(this)).start();
        this.switchLayout.animate().alpha(0.0f).setDuration(350).setInterpolator(r112).setListener(new AnonymousClass8(this)).start();
        return;
    L33:
        this.switchLayout.setVisibility(4);
        this.actionButtonContainer.setVisibility(4);
        return;
    L35:
        this.switchLayout.setVisibility(0);
        this.actionButtonContainer.setVisibility(0);
        if (r11 == false) goto L49;
        this.actionButtonContainer.animate().setListener(null).cancel();
        this.switchLayout.animate().setListener(null).cancel();
        ViewPropertyAnimator r93 = this.switchLayout.animate().alpha(1.0f).setDuration(350);
        CubicBezierInterpolator r113 = CubicBezierInterpolator.DEFAULT;
        r93.setInterpolator(r113).setListener(new AnonymousClass6(this)).start();
        this.actionButtonContainer.animate().alpha(1.0f).setDuration(350).setInterpolator(r113).start();
        if (this.selectedEmojisMap.isEmpty() == false) goto L50;
        this.selectAnimatedEmojiDialog.clearSelectedDocuments();
        this.editText.setText(_UrlKt.FRAGMENT_ENCODE_SET);
        SpannableStringBuilder r5 = new SpannableStringBuilder();
        Iterator<TLRPC.TL_availableReaction> r94 = this.allAvailableReactions.iterator();
        int r102 = 0;
    L41:
        if (r94.hasNext() == false) goto L44;
        ReactionsUtils.addReactionToEditText(r94.next(), this.selectedEmojisMap, this.selectedEmojisIds, r5, this.selectAnimatedEmojiDialog, this.editText.getFontMetricsInt());
        r102 = r102 + 1;
        if (r102 < this.maxReactionsCount) goto L41;
    L44:
        this.editText.append(r5);
        this.editText.addReactionsSpan();
        this.selectAnimatedEmojiDialog.notifyDataSetChanged();
        checkMaxCustomReactions(false);
        return;
    L50:
        return;
    L49:
        return;
    L23:
        r4.setBackgroundColorAnimatedReverse(r32);
        goto L25
    L24:
        this.enableReactionsCell.setBackgroundColor(r32);
        goto L25
    L17:
        r3 = Theme.key_windowBackgroundUnchecked;
    L13:
        r2 = true;
        goto L14
    L5:
        if (this.paid != r10) goto L7;
    }

    private void showKeyboard() {
        if (this.emojiKeyboardVisible == true) goto L6;
        this.emojiKeyboardVisible = true;
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        updateScrollViewMarginBottom(this.bottomDialogLayout.getMeasuredHeight());
        this.bottomDialogLayout.setVisibility(0);
        this.bottomDialogLayout.setTranslationY(r0.getMeasuredHeight());
        this.bottomDialogLayout.animate().setListener(null).cancel();
        this.bottomDialogLayout.animate().translationY(0.0f).withLayer().setDuration(350).setInterpolator(CubicBezierInterpolator.DEFAULT).setUpdateListener(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda14(this)).setListener(new AnonymousClass9(this)).start();
        return;
    }

    private void updateScrollViewMarginBottom(int r2) {
        ViewGroup.MarginLayoutParams r0 = (ViewGroup.MarginLayoutParams) this.scrollView.getLayoutParams();
        r0.bottomMargin = r2;
        this.scrollView.setLayoutParams(r0);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean canBeginSlide() {
        if (checkChangesBeforeExit(true) == false) goto L7;
        return false;
    L7:
        return super.canBeginSlide();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    @SuppressLint({"ClickableViewAccessibility"})
    public View createView(Context r24) {
        this.actionBar.setTitle(LocaleController.getString(R.string.Reactions));
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        boolean r3 = true;
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        this.contentLayout = new SectionsScrollView.SectionsLinearLayout(r24);
        SectionsScrollView r2 = new SectionsScrollView(r24, this.contentLayout, this.resourceProvider);
        this.scrollView = r2;
        r2.setFillViewport(true);
        this.actionBar.setAdaptiveBackground(this.scrollView);
        AnonymousClass2 r22 = new AnonymousClass2(this, r24);
        this.contentLayout.setOrientation(1);
        this.scrollView.addView(this.contentLayout);
        TextCheckCell r4 = new TextCheckCell(r24);
        this.enableReactionsCell = r4;
        r4.setHeight(56);
        TextCheckCell r42 = this.enableReactionsCell;
        if (r42.isChecked() == false) goto L5;
        int r5 = Theme.key_windowBackgroundChecked;
    L6:
        r42.setBackgroundColor(Theme.getColor(r5));
        this.enableReactionsCell.setTypeface(AndroidUtilities.bold());
        this.enableReactionsCell.setColors(Theme.key_windowBackgroundCheckText, Theme.key_switchTrackBlue, Theme.key_switchTrackBlueChecked, Theme.key_switchTrackBlueThumb, Theme.key_switchTrackBlueThumbChecked);
        this.enableReactionsCell.setOnClickListener(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda1(this));
        this.contentLayout.addView(this.enableReactionsCell, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r43 = new TextInfoPrivacyCell(r24, 12, this.resourceProvider);
        int r52 = Theme.key_windowBackgroundWhiteGrayText4;
        r43.setTextColor(Theme.getColor(r52));
        r43.setTopPadding(12);
        r43.setBottomPadding(16);
        r43.setText(LocaleController.getString(R.string.ReactionAddEmojiFromAnyPack));
        this.contentLayout.addView(r43, LayoutHelper.createLinear(-1, -2));
        HeaderCell r44 = new HeaderCell(r24);
        r44.setText(LocaleController.getString(R.string.AvailableReactions));
        r44.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        r44.setTextSize(15.0f);
        r44.setTopMargin(14);
        LinearLayout r10 = new LinearLayout(r24);
        this.switchLayout = r10;
        r10.setOrientation(1);
        this.contentLayout.addView(this.switchLayout, LayoutHelper.createFrame(-1, -2.0f));
        this.switchLayout.addView(r44, LayoutHelper.createLinear(-1, -2));
        AnonymousClass3 r45 = new AnonymousClass3(this, r24, getResourceProvider(), this.maxReactionsCount);
        this.editText = r45;
        r45.setOnFocused(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda2(this));
        this.switchLayout.addView(this.editText, LayoutHelper.createLinear(-1, -2));
        LayoutTransition r46 = new LayoutTransition();
        r46.setDuration(200);
        r46.enableTransitionType(4);
        this.switchLayout.setLayoutTransition(r46);
        TextInfoPrivacyCell r47 = new TextInfoPrivacyCell(r24, 12, this.resourceProvider);
        r47.setTextColor(Theme.getColor(r52));
        r47.setText(AndroidUtilities.replaceSingleTag(LocaleController.getString(R.string.ReactionCreateOwnPack), Theme.key_chat_messageLinkIn, 0, new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda3(this), getResourceProvider()));
        this.switchLayout.addView(r47, LayoutHelper.createLinear(-1, -2));
        HeaderCell r48 = new HeaderCell(r24, this.resourceProvider);
        r48.setText(LocaleController.getString(R.string.MaximumReactionsHeader));
        this.switchLayout.addView(r48, LayoutHelper.createLinear(-1, -2));
        this.slideView = new SlideIntChooseView(r24, this.resourceProvider);
        TLRPC.ChatFull r49 = this.info;
        if ((r49 instanceof TLRPC.TL_chatFull) == false) goto L12;
        if ((r49.flags & 1048576) != 0) goto L13;
    L14:
        int r410 = getMessagesController().reactionsUniqMax;
        this.reactionsCount = r410;
        this.currentReactionsCount = r410;
    L15:
        this.slideView.set(this.reactionsCount, SlideIntChooseView.Options.make(0, "MaximumReactionsValue", 1, getMessagesController().reactionsUniqMax), new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda4(this));
        this.switchLayout.addView(this.slideView, LayoutHelper.createLinear(-1, -2));
        TextInfoPrivacyCell r411 = new TextInfoPrivacyCell(r24, 12, this.resourceProvider);
        r411.setTopPadding(12);
        r411.setBottomPadding(16);
        r411.setText(LocaleController.getString(R.string.MaximumReactionsInfo));
        this.switchLayout.addView(r411, LayoutHelper.createLinear(-1, -2));
        if (this.info.paid_media_allowed == false) goto L18;
        TextCheckCell r412 = new TextCheckCell(r24);
        this.paidCheckCell = r412;
        r412.setTextAndCheck(LocaleController.getString(R.string.ChannelEnablePaidReactions), false, false);
        this.switchLayout.addView(this.paidCheckCell, LayoutHelper.createLinear(-1, -2));
        this.paidCheckCell.setOnClickListener(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda5(this));
        TextInfoPrivacyCell r413 = new TextInfoPrivacyCell(r24, 12, this.resourceProvider);
        r413.setTextColor(Theme.getColor(r52));
        r413.setTopPadding(12);
        r413.setBottomPadding(70);
        r413.setText(AndroidUtilities.withLearnMore(LocaleController.getString(R.string.ChannelEnablePaidReactionsInfo), new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda6(this)));
        this.switchLayout.addView(r413, LayoutHelper.createLinear(-1, -2));
    L19:
        this.actionButtonContainer = new FrameLayout(r24);
        ImageView r414 = new ImageView(r24);
        this.actionButtonContainerGradient = r414;
        r414.setImageResource(R.drawable.gradient_bottom);
        this.actionButtonContainerGradient.setScaleType(ImageView.ScaleType.FIT_XY);
        ImageView r415 = this.actionButtonContainerGradient;
        int r8 = Theme.key_windowBackgroundGray;
        r415.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r8, this.resourceProvider), PorterDuff.Mode.SRC_ATOP));
        this.actionButtonContainer.addView(this.actionButtonContainerGradient, LayoutHelper.createFrame(-1, -1, 119));
        UpdateReactionsButton r416 = new UpdateReactionsButton(r24, getResourceProvider());
        this.actionButton = r416;
        r416.setRound();
        this.actionButton.setDefaultState();
        this.actionButton.setOnClickListener(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda7(this));
        r22.addView(this.scrollView);
        r22.addView(this.actionButtonContainer, LayoutHelper.createFrame(-1, 74.0f, 80, 0.0f, 0.0f, 0.0f, 0.0f));
        this.actionButtonContainer.addView(this.actionButton, LayoutHelper.createFrame(-1, 48.0f, 80, 13.0f, 13.0f, 13.0f, 13.0f));
        r22.setBackgroundColor(Theme.getColor(r8));
        AnonymousClass4 r417 = new AnonymousClass4(this, r24);
        this.bottomDialogLayout = r417;
        r417.setVisibility(4);
        r22.addView(this.bottomDialogLayout, LayoutHelper.createFrame(-1, -2, 80));
        TLRPC.ChatFull r1 = this.info;
        TLRPC.ChatReactions r418 = r1.available_reactions;
        if ((r418 instanceof TLRPC.TL_chatReactionsAll) == false) goto L28;
        SpannableStringBuilder r102 = new SpannableStringBuilder();
        Iterator<TLRPC.TL_availableReaction> r12 = this.allAvailableReactions.iterator();
        int r419 = 0;
    L23:
        if (r12.hasNext() == false) goto L26;
        ReactionsUtils.addReactionToEditText(r12.next(), this.selectedEmojisMap, this.selectedEmojisIds, r102, this.selectAnimatedEmojiDialog, this.editText.getFontMetricsInt());
        r419 = r419 + 1;
        if (r419 < this.maxReactionsCount) goto L23;
    L26:
        this.editText.append(r102);
        setCheckedEnableReactionCell(0, this.paid, false);
    L60:
        TextCheckCell r13 = this.enableReactionsCell;
        String r420 = LocaleController.getString(R.string.EnableReactions);
        if (this.selectedType == 2) goto L63;
    L66:
        r13.setTextAndCheck(r420, r3, false);
        this.editText.addReactionsSpan();
        TLRPC.ChatFull r14 = this.info;
        if (r14.paid_media_allowed == true) goto L69;
    L71:
        this.initialSelectedEmojis.putAll(this.selectedEmojisMap);
        this.initialPaid = this.paid;
        this.fragmentView = r22;
        return r22;
    L69:
        if (r14.paid_reactions_available == false) goto L71;
        toggleStarsEnabled();
        goto L71
    L63:
        if (this.paid == true) goto L66;
        r3 = false;
        goto L66
    L28:
        if ((r418 instanceof TLRPC.TL_chatReactionsSome) == false) goto L45;
        SpannableStringBuilder r103 = new SpannableStringBuilder();
        ArrayList<TLRPC.Reaction> r15 = ((TLRPC.TL_chatReactionsSome) r418).reactions;
        int r421 = r15.size();
        int r53 = 0;
        int r7 = 0;
    L30:
        if (r7 >= r421) goto L43;
        int r132 = r7 + 1;
        TLRPC.Reaction r82 = r15.get(r7);
        if ((r82 instanceof TLRPC.TL_reactionEmoji) == false) goto L39;
        TLRPC.TL_availableReaction r72 = getMediaDataController().getReactionsMap().get(((TLRPC.TL_reactionEmoji) r82).emoticon);
        if (r72 == null) goto L35;
        ReactionsUtils.addReactionToEditText(r72, this.selectedEmojisMap, this.selectedEmojisIds, r103, this.selectAnimatedEmojiDialog, this.editText.getFontMetricsInt());
    L37:
        r53 = r53 + 1;
    L42:
        if (r53 >= this.maxReactionsCount) goto L43;
    L35:
        r7 = r132;
        goto L30
    L39:
        if ((r82 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L42;
        ReactionsUtils.addReactionToEditText((TLRPC.TL_reactionCustomEmoji) r82, this.selectedEmojisMap, this.selectedEmojisIds, r103, this.selectAnimatedEmojiDialog, this.editText.getFontMetricsInt());
    L43:
        this.editText.append(r103);
        setCheckedEnableReactionCell(1, this.paid, false);
        goto L60
    L45:
        if ((r418 instanceof TLRPC.TL_chatReactionsNone) == false) goto L53;
        if (r1 == null) goto L53;
        if (r1.paid_media_allowed == false) goto L53;
        if (r1.paid_reactions_available == false) goto L53;
        setCheckedEnableReactionCell(2, this.paid, false);
    L53:
        if ((r418 instanceof TLRPC.TL_chatReactionsNone) == false) goto L60;
        SpannableStringBuilder r104 = new SpannableStringBuilder();
        Iterator<TLRPC.TL_availableReaction> r16 = this.allAvailableReactions.iterator();
        int r422 = 0;
    L56:
        if (r16.hasNext() == false) goto L59;
        ReactionsUtils.addReactionToEditText(r16.next(), this.selectedEmojisMap, this.selectedEmojisIds, r104, this.selectAnimatedEmojiDialog, this.editText.getFontMetricsInt());
        r422 = r422 + 1;
        if (r422 < this.maxReactionsCount) goto L56;
    L59:
        this.editText.append(r104);
        setCheckedEnableReactionCell(2, this.paid, false);
        goto L60
    L18:
        r411.setBottomPadding(70);
    L13:
        int r423 = r49.reactions_limit;
        this.reactionsCount = r423;
        this.currentReactionsCount = r423;
        goto L15
    L12:
        if ((r49.flags2 & 8192) == 0) goto L14;
    L5:
        r5 = Theme.key_windowBackgroundUnchecked;
        goto L6
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r3, int r4, Object... r5) {
        if (r3 == NotificationCenter.dialogDeleted) goto L5;
        return;
    L5:
        if (((Long) r5[0]).longValue() != (-this.chatId)) goto L15;
        INavigationLayout r32 = this.parentLayout;
        if (r32 != null) goto L9;
    L12:
        removeSelfFromStack();
        return;
    L9:
        if (r32.getLastFragment() != this) goto L12;
        finishFragment();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.emojiKeyboardVisible == false) goto L8;
        if (r3 == false) goto L6;
        closeKeyboard();
    L6:
        return false;
    L8:
        if (checkChangesBeforeExit(r3) == false) goto L11;
        return false;
    L11:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        TLRPC.Chat r0 = getMessagesController().getChat(Long.valueOf(this.chatId));
        this.currentChat = r0;
        if (r0 != null) goto L9;
        TLRPC.Chat r02 = MessagesStorage.getInstance(this.currentAccount).getChatSync(this.chatId);
        this.currentChat = r02;
        if (r02 == null) goto L7;
        getMessagesController().putChat(this.currentChat, true);
        goto L9
    L7:
        return false;
    L9:
        if (this.info != null) goto L11;
        return false;
    L11:
        getMessagesController().getBoostsController().getBoostsStats(-this.chatId, new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda13(this));
        getNotificationCenter().addObserver(this, NotificationCenter.reactionsDidLoad);
        this.allAvailableReactions.addAll(getMediaDataController().getEnabledReactionsList());
        NotificationCenter.getGlobalInstance().postNotificationName(NotificationCenter.stopAllHeavyOperations, new Object[]{512});
        getNotificationCenter().addObserver(this, NotificationCenter.dialogDeleted);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        super.onFragmentDestroy();
        AndroidUtilities.cancelRunOnUIThread(this.checkAfterFastDeleteRunnable);
        if (this.selectedType == 2) goto L5;
    L7:
        getNotificationCenter().removeObserver(this, NotificationCenter.dialogDeleted);
        getNotificationCenter().removeObserver(this, NotificationCenter.reactionsDidLoad);
        return;
    L5:
        if (this.reactionsCount == this.currentReactionsCount) goto L7;
        getMessagesController().setCustomChatReactions(this.chatId, this.selectedType, grabReactions(false), this.reactionsCount, null, null, null);
        goto L7
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onPause() {
        this.isPaused = true;
        this.editText.setFocusable(false);
        super.onPause();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onResume() {
        super.onResume();
        if (this.isPaused == false) goto L8;
        this.isPaused = false;
        this.editText.setFocusable(true);
        this.editText.setFocusableInTouchMode(true);
        if (this.emojiKeyboardVisible == false) goto L9;
        this.editText.removeReactionsSpan(false);
        AndroidUtilities.runOnUIThread(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda12(this), 250);
        return;
    L9:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationEnd(boolean r3, boolean r4) {
        super.onTransitionAnimationEnd(r3, r4);
        if (r3 == true) goto L5;
    L7:
        if (r3 == false) goto L11;
        if (r4 == true) goto L12;
        initSelectAnimatedEmojiDialog();
        AndroidUtilities.runOnUIThread(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda0(), 200);
        return;
    L12:
        return;
    L11:
        return;
    L5:
        if (this.selectedType == 2) goto L7;
        this.editText.setFocusableInTouchMode(true);
        goto L7
    }

    public void toggleStarsEnabled() {
        boolean r3 = this.paidCheckCell.isChecked();
        TextCheckCell r4 = this.paidCheckCell;
        if (r3 == false) goto L8;
        r4.setChecked(false);
        this.selectedEmojisIds.remove(-1L);
        AnimatedEmojiSpan r0 = this.selectedEmojisMap.remove(-1L);
        if (r0 == null) goto L7;
        r0.setRemoved(new ChatCustomReactionsEditActivity$$ExternalSyntheticLambda16(this, r0));
    L7:
        animateChangesInNextRows(r0);
        this.selectAnimatedEmojiDialog.setMultiSelected(-1L, true);
        checkMaxCustomReactions(false);
        this.editText.setMaxLength(this.maxReactionsCount);
        setCheckedEnableReactionCell(this.selectedType, this.paid, true);
    L14:
        this.editText.updateAnimatedEmoji(true);
        return;
    L8:
        r4.setChecked(true);
        this.editText.setMaxLength(this.maxReactionsCount + 1);     // Catch: Exception -> L11
        SpannableString r32 = new SpannableString("b");     // Catch: Exception -> L11
        AnonymousClass11 r42 = new AnonymousClass11(this, -1, null);     // Catch: Exception -> L11
        r42.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();     // Catch: Exception -> L11
        r42.setAdded();     // Catch: Exception -> L11
        this.selectedEmojisIds.add(0, -1L);     // Catch: Exception -> L11
        this.selectedEmojisMap.put(-1L, r42);     // Catch: Exception -> L11
        r32.setSpan(r42, 0, r32.length(), 33);     // Catch: Exception -> L11
        this.editText.getText().insert(0, r32);     // Catch: Exception -> L11
        this.selectAnimatedEmojiDialog.setMultiSelected(-1L, true);     // Catch: Exception -> L11
        checkMaxCustomReactions(true);     // Catch: Exception -> L11
        animateChangesInNextRows(r42);     // Catch: Exception -> L11
    L13:
        setCheckedEnableReactionCell(this.selectedType, true, true);
    L11:
        e = move-exception;
        FileLog.e(e);
        goto L13
    }
}
