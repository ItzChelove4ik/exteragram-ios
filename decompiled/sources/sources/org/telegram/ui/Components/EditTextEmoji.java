package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.SpannableString;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.utils.text.LocaleUtils;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.XiaomiUtilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.SizeNotifierFrameLayout;

/* loaded from: classes7.dex */
public class EditTextEmoji extends FrameLayout implements NotificationCenter.NotificationCenterDelegate, SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate {
    AdjustPanLayoutHelper adjustPanLayoutHelper;
    private boolean allowAnimatedEmoji;
    private boolean allowEmojisForNonPremium;
    private int currentStyle;
    private boolean destroyed;
    private EditTextCaption editText;
    private OutlineTextContainerView editTextFieldContainer;
    private ImageView emojiButton;
    public boolean emojiExpanded;
    private ReplaceableIconDrawable emojiIconDrawable;
    private int emojiPadding;
    private EmojiView emojiView;
    private float emojiViewAlpha;
    private int emojiViewCacheType;
    private boolean emojiViewVisible;
    private ItemOptions formatOptions;
    public boolean glassDesignForEmojiView;
    public boolean includeNavigationBar;
    private int innerTextChange;
    private boolean isAnimatePopupClosing;
    private boolean isPaused;
    private int keyboardHeight;
    private int keyboardHeightLand;
    private boolean keyboardVisible;
    private boolean lastEmojiExpanded;
    private int lastSizeChangeValue1;
    private boolean lastSizeChangeValue2;
    private Runnable openKeyboardRunnable;
    private BaseFragment parentFragment;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean showKeyboardOnResume;
    private boolean showLocalPremiumEmojiHint;
    private boolean shownFormatButton;
    private SizeNotifierFrameLayout sizeNotifierLayout;
    private boolean waitingForKeyboardOpen;

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ EditTextEmoji this$0;

        public AnonymousClass1(EditTextEmoji r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (EditTextEmoji.m10156$$Nest$fgetdestroyed(this.this$0) == false) goto L5;
            return;
        L5:
            if (EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0) != null) goto L7;
            return;
        L7:
            if (EditTextEmoji.m10167$$Nest$fgetwaitingForKeyboardOpen(this.this$0) == true) goto L9;
            return;
        L9:
            if (EditTextEmoji.m10161$$Nest$fgetkeyboardVisible(this.this$0) == false) goto L11;
            return;
        L11:
            if (AndroidUtilities.usingHardwareInput == false) goto L13;
            return;
        L13:
            if (AndroidUtilities.isInMultiwindow == false) goto L15;
            return;
        L15:
            if (AndroidUtilities.isTablet() == false) goto L24;
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).requestFocus();
            AndroidUtilities.showKeyboard(EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0));
            AndroidUtilities.cancelRunOnUIThread(EditTextEmoji.m10162$$Nest$fgetopenKeyboardRunnable(this.this$0));
            AndroidUtilities.runOnUIThread(EditTextEmoji.m10162$$Nest$fgetopenKeyboardRunnable(this.this$0), 100);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$2, reason: invalid class name */
    public class AnonymousClass2 extends EditTextCaption {
        private Drawable lastIcon;
        final /* synthetic */ EditTextEmoji this$0;
        final /* synthetic */ int val$style;

        public AnonymousClass2(EditTextEmoji r1, Context r2, Theme.ResourcesProvider r3, int r4) {
            this.this$0 = r1;
            this.val$style = r4;
            super(r2, r3);
            this.lastIcon = null;
        }

        @Override // org.telegram.ui.Components.EditTextEffects
        public int emojiCacheType() {
            return this.this$0.emojiCacheType();
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public void extendActionMode(ActionMode r4, Menu r5) {
            boolean r0 = this.this$0.allowEntities();
            EditTextEmoji r1 = this.this$0;
            if (r0 == true) goto L5;
            r1.extendActionMode(r4, r5);
        L10:
            super.extendActionMode(r4, r5);
            return;
        L5:
            if (EditTextEmoji.m10155$$Nest$fgetcurrentStyle(r1) != 3) goto L7;
            boolean r02 = true;
        L8:
            ChatActivity.fillActionModeMenu(r5, null, r02, true);
            goto L10
        L7:
            r02 = false;
            goto L8
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public int getActionModeStyle() {
            int r0 = this.val$style;
            if (r0 != 2) goto L5;
        L9:
            return 2;
        L5:
            if (r0 == 3) goto L9;
            return super.getActionModeStyle();
        }

        @Override // org.telegram.ui.Components.EditTextCaption
        public void onLineCountChanged(int r1, int r2) {
            this.this$0.onLineCountChanged(r1, r2);
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onSelectionChanged(int r3, int r4) {
            super.onSelectionChanged(r3, r4);
            if (EditTextEmoji.m10159$$Nest$fgetemojiIconDrawable(this.this$0) == null) goto L20;
            boolean r0 = false;
            if (r4 == r3) goto L7;
            boolean r32 = true;
        L9:
            if (this.this$0.allowEntities() == false) goto L13;
            if (r32 == false) goto L13;
            XiaomiUtilities.isMIUI();
            r0 = true;
        L13:
            if (EditTextEmoji.m10165$$Nest$fgetshownFormatButton(this.this$0) == r0) goto L21;
            EditTextEmoji.m10171$$Nest$fputshownFormatButton(this.this$0, r0);
            EditTextEmoji r33 = this.this$0;
            if (r0 == false) goto L18;
            this.lastIcon = EditTextEmoji.m10159$$Nest$fgetemojiIconDrawable(r33).getIcon();
            EditTextEmoji.m10159$$Nest$fgetemojiIconDrawable(this.this$0).setIcon(R.drawable.msg_edit, true);
            return;
        L18:
            EditTextEmoji.m10159$$Nest$fgetemojiIconDrawable(r33).setIcon(this.lastIcon, true);
            this.lastIcon = null;
            return;
        L21:
            return;
        L7:
            r32 = false;
            goto L9
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView, android.view.View
        public boolean onTouchEvent(MotionEvent r4) {
            if (this.this$0.isPopupShowing() == false) goto L18;
            if (r4.getAction() != 0) goto L18;
            this.this$0.onWaitingForKeyboard();
            EditTextEmoji r0 = this.this$0;
            if (r0.emojiExpanded == true) goto L9;
        L11:
            EditTextEmoji r02 = this.this$0;
            if (AndroidUtilities.usingHardwareInput == false) goto L14;
            int r2 = 0;
        L15:
            r02.showPopup(r2);
        L16:
            this.this$0.openKeyboardInternal();
            goto L18
        L14:
            r2 = 2;
            goto L15
        L9:
            if (EditTextEmoji.m10160$$Nest$fgetemojiView(r0) == null) goto L11;
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).closeSearch(false);
            EditTextEmoji r03 = this.this$0;
            r03.emojiExpanded = false;
            r03.hidePopup(true);
            AndroidUtilities.showKeyboard(this);
        L18:
            if (r4.getAction() != 0) goto L29;
            boolean r04 = isFocused();
            requestFocus();
            if (AndroidUtilities.showKeyboard(this) == true) goto L22;
            clearFocus();
            requestFocus();
        L22:
            if (r04 == true) goto L29;
            setSelection(getText().length());
        L29:
            return super.onTouchEvent(r4);
        L26:
            e = move-exception;
            FileLog.e(e);
            return false;
        }

        @Override // android.view.View
        public void scrollTo(int r2, int r3) {
            if (this.this$0.onScrollYChange(r3) == false) goto L6;
            super.scrollTo(r2, r3);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$3, reason: invalid class name */
    public class AnonymousClass3 extends ImageView {
        final /* synthetic */ EditTextEmoji this$0;

        public AnonymousClass3(EditTextEmoji r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r4) {
            EditTextEmoji r0 = this.this$0;
            if (r0.customEmojiButtonDraw(r4, EditTextEmoji.m10158$$Nest$fgetemojiButton(r0), EditTextEmoji.m10159$$Nest$fgetemojiIconDrawable(this.this$0)) == true) goto L6;
            super.dispatchDraw(r4);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ EditTextEmoji this$0;

        public AnonymousClass4(EditTextEmoji r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            EditTextEmoji.m10170$$Nest$fputisAnimatePopupClosing(this.this$0, false);
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).setTranslationY(0.0f);
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).setAlpha(0.0f);
            this.this$0.bottomPanelTranslationY(0.0f);
            EditTextEmoji.m10168$$Nest$fputemojiViewAlpha(this.this$0, 0.0f);
            this.this$0.hideEmojiView();
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$5, reason: invalid class name */
    public class AnonymousClass5 extends AnimatorListenerAdapter {
        final /* synthetic */ EditTextEmoji this$0;

        public AnonymousClass5(EditTextEmoji r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).setTranslationY(0.0f);
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).setAlpha(1.0f);
            EditTextEmoji.m10168$$Nest$fputemojiViewAlpha(this.this$0, 1.0f);
            this.this$0.bottomPanelTranslationY(0.0f);
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$6, reason: invalid class name */
    public class AnonymousClass6 extends EmojiView {
        private boolean changedExpanded;
        private boolean lastExpanded;
        private int lastHeight;
        final /* synthetic */ EditTextEmoji this$0;

        public AnonymousClass6(EditTextEmoji r1, BaseFragment r2, boolean r3, boolean r4, boolean r5, Context r6, boolean r7, TLRPC.ChatFull r8, ViewGroup r9, boolean r10, Theme.ResourcesProvider r11, boolean r12, boolean r13, boolean r14) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14);
        }

        @Override // org.telegram.ui.Components.EmojiView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            if (EditTextEmoji.m10155$$Nest$fgetcurrentStyle(this.this$0) != 2) goto L5;
        L6:
            this.this$0.drawEmojiBackground(r3, this);
        L7:
            super.dispatchDraw(r3);
            return;
        L5:
            if (EditTextEmoji.m10155$$Nest$fgetcurrentStyle(this.this$0) != 3) goto L7;
            goto L6
        }

        @Override // org.telegram.ui.Components.EmojiView, android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.this$0.allowSearch() == false) goto L18;
            int r52 = r5 - r3;
            if (this.lastExpanded == true) goto L10;
            if (this.this$0.emojiExpanded == false) goto L10;
            this.changedExpanded = true;
        L10:
            if (this.changedExpanded == false) goto L16;
            int r12 = this.lastHeight;
            if (r12 <= 0) goto L16;
            if (r52 <= 0) goto L16;
            if (r52 == r12) goto L16;
            setTranslationY(r52 - r12);
            animate().translationY(0.0f).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).setDuration(250).start();
            this.changedExpanded = false;
        L16:
            this.lastExpanded = this.this$0.emojiExpanded;
            this.lastHeight = r52;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.EditTextEmoji$7, reason: invalid class name */
    public class AnonymousClass7 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ EditTextEmoji this$0;

        /* renamed from: org.telegram.ui.Components.EditTextEmoji$7$1, reason: invalid class name */
        public class AnonymousClass1 extends BaseFragment {
            final /* synthetic */ AnonymousClass7 this$1;

            /* renamed from: org.telegram.ui.Components.EditTextEmoji$7$1$1, reason: invalid class name and collision with other inner class name */
            public class DialogC00501 extends Dialog {
                final /* synthetic */ AnonymousClass1 this$2;

                public DialogC00501(AnonymousClass1 r1, Context r2) {
                    this.this$2 = r1;
                    super(r2);
                }

                @Override // android.app.Dialog, android.content.DialogInterface
                public void dismiss() {
                    this.this$2.this$1.this$0.hidePopup(false);
                    this.this$2.this$1.this$0.closeParent();
                }
            }

            public AnonymousClass1(AnonymousClass7 r1) {
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
            public Activity getParentActivity() {
                Context r1 = getContext();
            L4:
                if ((r1 instanceof ContextWrapper) == false) goto L10;
                if ((r1 instanceof Activity) == true) goto L8;
                r1 = ((ContextWrapper) r1).getBaseContext();
                goto L4
            L8:
                return (Activity) r1;
            L10:
                return null;
            }

            @Override // org.telegram.ui.ActionBar.BaseFragment
            public Dialog getVisibleDialog() {
                return new DialogC00501(this, this.this$1.this$0.getContext());
            }
        }

        /* renamed from: $r8$lambda$-fzUcZkzyHad2-pFi4xSyVONH1Y, reason: not valid java name */
        public static /* synthetic */ void m10172$r8$lambda$fzUcZkzyHad2pFi4xSyVONH1Y(AnonymousClass7 r0, AlertDialog r1, int r2) {
            r0.lambda$onClearEmojiRecent$0(r1, r2);
        }

        public AnonymousClass7(EditTextEmoji r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onClearEmojiRecent$0(AlertDialog r1, int r2) {
            EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).clearRecentEmoji();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isSearchOpened() {
            return this.this$0.emojiExpanded;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onAnimatedEmojiUnlockClick() {
            BaseFragment r0 = EditTextEmoji.m10163$$Nest$fgetparentFragment(this.this$0);
            if (r0 != null) goto L6;
            new PremiumFeatureBottomSheet(new AnonymousClass1(this), 11, false).show();
            return;
        L6:
            r0.showDialog(new PremiumFeatureBottomSheet(r0, 11, false));
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            if (EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).length() != 0) goto L5;
            return false;
        L5:
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onClearEmojiRecent() {
            AlertDialog.Builder r0 = new AlertDialog.Builder(this.this$0.getContext(), EditTextEmoji.m10164$$Nest$fgetresourcesProvider(this.this$0));
            r0.setTitle(LocaleController.getString(R.string.ClearRecentEmojiTitle));
            r0.setMessage(LocaleController.getString(R.string.ClearRecentEmojiText));
            r0.setPositiveButton(LocaleController.getString(R.string.ClearButton), new EditTextEmoji$7$$ExternalSyntheticLambda0(this));
            r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
            if (EditTextEmoji.m10163$$Nest$fgetparentFragment(this.this$0) == null) goto L6;
            EditTextEmoji.m10163$$Nest$fgetparentFragment(this.this$0).showDialog(r0.create());
            return;
        L6:
            r0.show();
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r4, TLRPC.Document r6, String r7, boolean r8) {
            int r82 = EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getSelectionEnd();
            if (r82 >= 0) goto L20;
            r82 = 0;
        L20:
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 2);     // Catch: Throwable -> L8 Exception -> L10
            SpannableString r1 = new SpannableString(r7);     // Catch: Throwable -> L8 Exception -> L10
            if (r6 == null) goto L12;
            AnimatedEmojiSpan r42 = new AnimatedEmojiSpan(r6, EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getPaint().getFontMetricsInt());     // Catch: Throwable -> L8 Exception -> L10
        L13:
            r42.cacheType = EditTextEmoji.m10160$$Nest$fgetemojiView(this.this$0).emojiCacheType;     // Catch: Throwable -> L8 Exception -> L10
            r1.setSpan(r42, 0, r1.length(), 33);     // Catch: Throwable -> L8 Exception -> L10
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).setText(EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getText().insert(r82, r1));     // Catch: Throwable -> L8 Exception -> L10
            int r83 = r82 + r1.length();     // Catch: Throwable -> L8 Exception -> L10
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).setSelection(r83, r83);     // Catch: Throwable -> L8 Exception -> L10
        L14:
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 0);
            return;
        L12:
            r42 = new AnimatedEmojiSpan(r4, EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getPaint().getFontMetricsInt());     // Catch: Throwable -> L8 Exception -> L10
        L10:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L8
        L8:
            th = move-exception;
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 0);
            throw th;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r5) {
            int r0 = EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getSelectionEnd();
            if (r0 >= 0) goto L15;
            r0 = 0;
        L15:
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 2);     // Catch: Throwable -> L8 Exception -> L10
            CharSequence r52 = Emoji.replaceEmoji(r5, EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getPaint().getFontMetricsInt(), false);     // Catch: Throwable -> L8 Exception -> L10
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).setText(EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).getText().insert(r0, r52));     // Catch: Throwable -> L8 Exception -> L10
            int r02 = r0 + r52.length();     // Catch: Throwable -> L8 Exception -> L10
            EditTextEmoji.m10157$$Nest$fgeteditText(this.this$0).setSelection(r02, r02);     // Catch: Throwable -> L8 Exception -> L10
        L6:
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 0);
            return;
        L8:
            th = move-exception;
            EditTextEmoji.m10169$$Nest$fputinnerTextChange(this.this$0, 0);
            throw th;
        L10:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L8
            goto L6
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onSearchOpenClose(int r2) {
            if (this.this$0.allowSearch() == false) goto L14;
            EditTextEmoji r0 = this.this$0;
            if (r2 == 0) goto L8;
            boolean r22 = true;
        L9:
            r0.emojiExpanded = r22;
            r0.updatedEmojiExpanded();
            if (EditTextEmoji.m10166$$Nest$fgetsizeNotifierLayout(this.this$0) == null) goto L13;
            EditTextEmoji.m10166$$Nest$fgetsizeNotifierLayout(this.this$0).notifyHeightChanged();
            return;
        L13:
            return;
        L8:
            r22 = false;
            goto L9
        }
    }

    public interface EditTextEmojiDelegate {
    }

    public static /* synthetic */ void $r8$lambda$3L95WzKAqBu2xaJzAbUI5txOkow(EditTextEmoji r0, ValueAnimator r1) {
        r0.lambda$showPopup$3(r1);
    }

    /* renamed from: $r8$lambda$5JFszUoisMOOIE6T2d-0zBM8XD0, reason: not valid java name */
    public static /* synthetic */ void m10154$r8$lambda$5JFszUoisMOOIE6T2d0zBM8XD0(EditTextEmoji r0, SizeNotifierFrameLayout r1, Theme.ResourcesProvider r2, View r3) {
        r0.lambda$new$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$k2NZFGwvDFkIDSW9Asi20taojUk(EditTextEmoji r0, int r1, ValueAnimator r2) {
        r0.lambda$hidePopup$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$nbwCEvJKWYiizPfkreX_hnn9muA(EditTextEmoji r0, View r1, boolean r2) {
        r0.lambda$new$0(r1, r2);
    }

    /* renamed from: -$$Nest$fgetcurrentStyle, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m10155$$Nest$fgetcurrentStyle(EditTextEmoji r0) {
        return r0.currentStyle;
    }

    /* renamed from: -$$Nest$fgetdestroyed, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10156$$Nest$fgetdestroyed(EditTextEmoji r0) {
        return r0.destroyed;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCaption m10157$$Nest$fgeteditText(EditTextEmoji r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgetemojiButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ImageView m10158$$Nest$fgetemojiButton(EditTextEmoji r0) {
        return r0.emojiButton;
    }

    /* renamed from: -$$Nest$fgetemojiIconDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReplaceableIconDrawable m10159$$Nest$fgetemojiIconDrawable(EditTextEmoji r0) {
        return r0.emojiIconDrawable;
    }

    /* renamed from: -$$Nest$fgetemojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiView m10160$$Nest$fgetemojiView(EditTextEmoji r0) {
        return r0.emojiView;
    }

    /* renamed from: -$$Nest$fgetkeyboardVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10161$$Nest$fgetkeyboardVisible(EditTextEmoji r0) {
        return r0.keyboardVisible;
    }

    /* renamed from: -$$Nest$fgetopenKeyboardRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m10162$$Nest$fgetopenKeyboardRunnable(EditTextEmoji r0) {
        return r0.openKeyboardRunnable;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BaseFragment m10163$$Nest$fgetparentFragment(EditTextEmoji r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m10164$$Nest$fgetresourcesProvider(EditTextEmoji r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetshownFormatButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10165$$Nest$fgetshownFormatButton(EditTextEmoji r0) {
        return r0.shownFormatButton;
    }

    /* renamed from: -$$Nest$fgetsizeNotifierLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ SizeNotifierFrameLayout m10166$$Nest$fgetsizeNotifierLayout(EditTextEmoji r0) {
        return r0.sizeNotifierLayout;
    }

    /* renamed from: -$$Nest$fgetwaitingForKeyboardOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m10167$$Nest$fgetwaitingForKeyboardOpen(EditTextEmoji r0) {
        return r0.waitingForKeyboardOpen;
    }

    /* renamed from: -$$Nest$fputemojiViewAlpha, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10168$$Nest$fputemojiViewAlpha(EditTextEmoji r0, float r1) {
        r0.emojiViewAlpha = r1;
    }

    /* renamed from: -$$Nest$fputinnerTextChange, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10169$$Nest$fputinnerTextChange(EditTextEmoji r0, int r1) {
        r0.innerTextChange = r1;
    }

    /* renamed from: -$$Nest$fputisAnimatePopupClosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10170$$Nest$fputisAnimatePopupClosing(EditTextEmoji r0, boolean r1) {
        r0.isAnimatePopupClosing = r1;
    }

    /* renamed from: -$$Nest$fputshownFormatButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m10171$$Nest$fputshownFormatButton(EditTextEmoji r0, boolean r1) {
        r0.shownFormatButton = r1;
    }

    public EditTextEmoji(Context r8, SizeNotifierFrameLayout r9, BaseFragment r10, int r11, boolean r12) {
        this(r8, r9, r10, r11, r12, null);
    }

    public EditTextEmoji(Context r24, SizeNotifierFrameLayout r25, BaseFragment r26, int r27, boolean r28, Theme.ResourcesProvider r29) {
        super(r24);
        this.isPaused = true;
        this.openKeyboardRunnable = new AnonymousClass1(this);
        this.emojiViewCacheType = 2;
        this.allowAnimatedEmoji = r28;
        this.resourcesProvider = r29;
        this.currentStyle = r27;
        this.parentFragment = r26;
        this.sizeNotifierLayout = r25;
        r25.addDelegate(this);
        int r8 = AndroidUtilities.dp(16.0f);
        if (r27 != 0) goto L5;
        OutlineTextContainerView r9 = new OutlineTextContainerView(r24);
        this.editTextFieldContainer = r9;
        addView(r9, LayoutHelper.createLinear(-1, -2, 1, 0, 0, 0, 0));
    L5:
        AnonymousClass2 r92 = new AnonymousClass2(this, r24, r29, r27);
        this.editText = r92;
        r92.setImeOptions(268435456);
        EditTextCaption r93 = this.editText;
        r93.setInputType(r93.getInputType() | 16384);
        EditTextCaption r94 = this.editText;
        r94.setFocusable(r94.isEnabled());
        this.editText.setCursorSize(AndroidUtilities.dp(20.0f));
        this.editText.setTextSize(1, 18.0f);
        this.editText.setCursorWidth(1.5f);
        this.editText.setMaxLines(4);
        this.editText.setBackground(null);
        EditTextCaption r95 = this.editText;
        int r13 = Theme.key_windowBackgroundWhiteBlackText;
        r95.setCursorColor(getThemedColor(r13));
        if (r27 != 0) goto L13;
        EditTextCaption r5 = this.editText;
        if (LocaleController.isRTL == false) goto L10;
        int r7 = 5;
    L11:
        r5.setGravity(r7 | 16);
        this.editText.setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        this.editText.setTextColor(getThemedColor(r13));
        this.editText.setHandlesColor(getThemedColor(Theme.key_chat_TextSelectionCursor));
        this.editText.setPadding(0, r8, 0, r8);
        this.editText.setOnFocusChangeListener(new EditTextEmoji$$ExternalSyntheticLambda0(this));
        this.editTextFieldContainer.addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 19, 16.0f, 0.0f, 53.0f, 0.0f));
        this.editTextFieldContainer.attachEditText(this.editText);
    L21:
        AnonymousClass3 r52 = new AnonymousClass3(this, r24);
        this.emojiButton = r52;
        r52.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
        ImageView r53 = this.emojiButton;
        ReplaceableIconDrawable r72 = new ReplaceableIconDrawable(r24);
        this.emojiIconDrawable = r72;
        r53.setImageDrawable(r72);
        if (r27 != 0) goto L28;
        this.emojiIconDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        this.emojiIconDrawable.setIcon(R.drawable.smiles_tab_smiles, false);
        OutlineTextContainerView r1 = this.editTextFieldContainer;
        ImageView r3 = this.emojiButton;
        if (LocaleController.isRTL == false) goto L26;
        int r14 = 3;
    L27:
        r1.addView(r3, LayoutHelper.createFrame(48, 48.0f, r14 | 16, 0.0f, 0.0f, 5.0f, 0.0f));
    L38:
        this.emojiButton.setBackground(Theme.createSelectorDrawable(getThemedColor(Theme.key_listSelector)));
        this.emojiButton.setOnClickListener(new EditTextEmoji$$ExternalSyntheticLambda1(this, r25, r29));
        this.emojiButton.setContentDescription(LocaleController.getString(R.string.Emoji));
        return;
    L26:
        r14 = 5;
        goto L27
    L28:
        if (r27 == 2) goto L37;
        if (r27 == 3) goto L37;
        if (r27 != 4) goto L33;
        this.emojiIconDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        this.emojiIconDrawable.setIcon(R.drawable.input_smile, false);
        addView(this.emojiButton, LayoutHelper.createFrame(48, 48.0f, 53, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L38
    L33:
        ReplaceableIconDrawable r12 = this.emojiIconDrawable;
        if (r27 != 5) goto L36;
        r12.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_graySectionText), PorterDuff.Mode.MULTIPLY));
        this.emojiIconDrawable.setIcon(R.drawable.input_smile, false);
        addView(this.emojiButton, LayoutHelper.createFrame(48, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L38
    L36:
        r12.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        this.emojiIconDrawable.setIcon(R.drawable.input_smile, false);
        addView(this.emojiButton, LayoutHelper.createFrame(48, 48.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
    L37:
        this.emojiIconDrawable.setColorFilter(new PorterDuffColorFilter(-1929379841, PorterDuff.Mode.MULTIPLY));
        this.emojiIconDrawable.setIcon(R.drawable.input_smile, false);
        addView(this.emojiButton, LayoutHelper.createFrame(40, 40.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        goto L38
    L10:
        r7 = 3;
        goto L11
    L13:
        if (r27 == 2) goto L20;
        if (r27 == 3) goto L20;
        EditTextCaption r73 = this.editText;
        if (r27 != 4) goto L19;
        r73.setTextSize(1, 18.0f);
        this.editText.setMaxLines(4);
        this.editText.setGravity(19);
        this.editText.setHintTextColor(getThemedColor(Theme.key_dialogTextHint));
        this.editText.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.editText.setBackground(null);
        this.editText.setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(12.0f));
        addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 19, 14.0f, 0.0f, 48.0f, 0.0f));
        goto L21
    L19:
        r73.setGravity(19);
        this.editText.setHintTextColor(getThemedColor(Theme.key_dialogTextHint));
        this.editText.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        this.editText.setPadding(0, AndroidUtilities.dp(11.0f), 0, AndroidUtilities.dp(12.0f));
        addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 19, 48.0f, 0.0f, 0.0f, 0.0f));
    L20:
        this.editText.setTextSize(1, 16.0f);
        this.editText.setMaxLines(8);
        this.editText.setGravity(19);
        this.editText.setAllowTextEntitiesIntersection(true);
        this.editText.setHintTextColor(-1929379841);
        this.editText.setTextColor(-1);
        this.editText.setCursorColor(-1);
        this.editText.setClipToPadding(false);
        this.editText.setPadding(0, AndroidUtilities.dp(9.0f), 0, AndroidUtilities.dp(9.0f));
        this.editText.setHandlesColor(-1);
        this.editText.setHighlightColor(822083583);
        this.editText.setLinkTextColor(-12147733);
        EditTextCaption r74 = this.editText;
        r74.quoteColor = -1;
        r74.setTextIsSelectable(true);
        setClipChildren(false);
        setClipToPadding(false);
        addView(this.editText, LayoutHelper.createFrame(-1, -1.0f, 19, 40.0f, 0.0f, 24.0f, 0.0f));
        goto L21
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private /* synthetic */ void lambda$hidePopup$2(int r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r52);
        float r0 = r4;
        float r2 = 1.0f - (r52 / r0);
        this.emojiViewAlpha = r2;
        if (r4 <= 0) goto L9;
        int r42 = this.currentStyle;
        if (r42 != 2) goto L7;
    L8:
        this.emojiView.setAlpha(r2);
        goto L9
    L7:
        if (r42 == 3) goto L8;
    L9:
        bottomPanelTranslationY(r52 - r0);
    }

    private /* synthetic */ void lambda$new$0(View r1, boolean r2) {
        OutlineTextContainerView r0 = this.editTextFieldContainer;
        if (r2 == false) goto L5;
        float r12 = 1.0f;
    L6:
        r0.animateSelection(r12);
        return;
    L5:
        r12 = 0.0f;
        goto L6
    }

    private /* synthetic */ void lambda$new$1(SizeNotifierFrameLayout r9, Theme.ResourcesProvider r10, View r11) {
        if (this.emojiButton.isEnabled() == true) goto L5;
        return;
    L5:
        if (this.emojiButton.getAlpha() < 0.5f) goto L35;
        AdjustPanLayoutHelper r112 = this.adjustPanLayoutHelper;
        if (r112 != null) goto L9;
    L11:
        boolean r0 = true;
        if (this.shownFormatButton == false) goto L20;
        ItemOptions r113 = this.formatOptions;
        if (r113 != null) goto L17;
        this.editText.hideActionMode();
        ItemOptions r92 = ItemOptions.makeOptions(r9, r10, this.emojiButton, false, false, true);
        r92.setMaxHeight(AndroidUtilities.dp(280.0f));
        EditTextCaption r102 = this.editText;
        Objects.requireNonNull(r102);
        r102.extendActionMode(null, new MenuToItemOptions(r92, new EditTextEmoji$$ExternalSyntheticLambda4(r102), this.editText.getOnPremiumMenuLockClickListener()));
        r92.forceTop(true);
        r92.show();
        return;
    L17:
        r113.dismiss();
        this.formatOptions = null;
        return;
    L20:
        if (isPopupShowing() == true) goto L30;
        showPopup(1);
        boolean r93 = this.editText.isFocused();
        EmojiView r114 = this.emojiView;
        if (this.editText.length() > 0) goto L25;
        r0 = false;
    L25:
        r114.onOpen(r0, false);
        this.editText.requestFocus();
        if (r93 == true) goto L36;
        EditTextCaption r8 = this.editText;
        r8.setSelection(r8.length());
        return;
    L36:
        return;
    L30:
        if (this.emojiExpanded == false) goto L32;
        hidePopup(true);
        this.emojiExpanded = false;
        onEmojiKeyboardUpdate();
    L32:
        openKeyboardInternal();
        return;
    L9:
        if (r112.animationInProgress() == false) goto L11;
        return;
    }

    private /* synthetic */ void lambda$showPopup$3(ValueAnimator r4) {
        float r42 = ((Float) r4.getAnimatedValue()).floatValue();
        this.emojiView.setTranslationY(r42);
        int r0 = this.emojiPadding;
        float r2 = 1.0f - (r42 / r0);
        this.emojiViewAlpha = r2;
        if (r0 <= 0) goto L9;
        int r02 = this.currentStyle;
        if (r02 != 2) goto L7;
    L8:
        this.emojiView.setAlpha(r2);
        goto L9
    L7:
        if (r02 == 3) goto L8;
    L9:
        bottomPanelTranslationY(r42);
    }

    private void onWindowSizeChanged() {
        this.sizeNotifierLayout.getHeight();
    }

    public void allowEmojisForNonPremium(boolean r1) {
        allowEmojisForNonPremium(r1, r1);
    }

    public void allowEmojisForNonPremium(boolean r1, boolean r2) {
        this.allowEmojisForNonPremium = r1;
        this.showLocalPremiumEmojiHint = r2;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L6;
        r0.allowEmojisForNonPremium(r1, r2);
        return;
    }

    public boolean allowEntities() {
        int r1 = this.currentStyle;
        if (r1 != 2) goto L5;
        return true;
    L5:
        if (r1 != 3) goto L7;
        return true;
    L7:
        if (r1 == 5) goto L14;
        return false;
    L14:
        return true;
    }

    public boolean allowSearch() {
        return false;
    }

    public boolean allowSearchAnimation() {
        return false;
    }

    public void bottomPanelTranslationY(float r1) {
    }

    public void closeKeyboard() {
        AndroidUtilities.hideKeyboard(this.editText);
    }

    public void closeParent() {
    }

    public void collapseEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L6;
        r0.hideSearchKeyboard();
        this.emojiView.closeSearch(false);
        return;
    }

    public void createEmojiView() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L8;
        if (r0.currentAccount == UserConfig.selectedAccount) goto L8;
        this.sizeNotifierLayout.removeView(r0);
        this.emojiView = null;
    L8:
        if (this.emojiView == null) goto L10;
        return;
    L10:
        BaseFragment r2 = this.parentFragment;
        if (this.allowAnimatedEmoji == true) goto L13;
    L17:
        boolean r3 = false;
    L18:
        Context r6 = getContext();
        boolean r7 = allowSearch();
        int r5 = this.currentStyle;
        if (r5 != 2) goto L21;
    L25:
        boolean r10 = false;
    L26:
        AnonymousClass6 r02 = new AnonymousClass6(this, r2, r3, false, false, r6, r7, null, null, r10, this.resourcesProvider, false, this.glassDesignForEmojiView, this.allowEmojisForNonPremium);
        this.emojiView = r02;
        r02.emojiCacheType = this.emojiViewCacheType;
        r02.allowEmojisForNonPremium(this.allowEmojisForNonPremium, this.showLocalPremiumEmojiHint);
        this.emojiView.setVisibility(8);
        this.emojiViewAlpha = 0.0f;
        if (AndroidUtilities.isTablet() == false) goto L29;
        this.emojiView.setForseMultiwindowLayout(true);
    L29:
        this.emojiView.setDelegate(new AnonymousClass7(this));
        this.sizeNotifierLayout.addView(this.emojiView);
        return;
    L21:
        if (r5 == 3) goto L25;
        if (r5 == 5) goto L25;
        r10 = true;
        goto L26
    L13:
        if (UserConfig.getInstance(UserConfig.selectedAccount).isPremium() == false) goto L15;
    L16:
        r3 = true;
        goto L18
    L15:
        if (LocaleUtils.canUseLocalPremiumEmojis() == false) goto L17;
        goto L16
    }

    public boolean customEmojiButtonDraw(Canvas r1, View r2, Drawable r3) {
        return false;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.emojiLoaded) goto L11;
        EmojiView r12 = this.emojiView;
        if (r12 == null) goto L7;
        r12.invalidateViews();
    L7:
        EditTextCaption r13 = this.editText;
        if (r13 == null) goto L12;
        int r14 = r13.getCurrentTextColor();
        this.editText.setTextColor(-1);
        this.editText.setTextColor(r14);
        return;
    L12:
        return;
    }

    public void drawEmojiBackground(Canvas r1, View r2) {
    }

    public int emojiCacheType() {
        return AnimatedEmojiDrawable.getCacheTypeForEnterView();
    }

    public void extendActionMode(ActionMode r1, Menu r2) {
    }

    public EditTextCaption getEditText() {
        return this.editText;
    }

    public View getEmojiButton() {
        return this.emojiButton;
    }

    public int getEmojiPadding() {
        return this.emojiPadding;
    }

    public float getEmojiPaddingShown() {
        return this.emojiViewAlpha;
    }

    public EmojiView getEmojiView() {
        return this.emojiView;
    }

    public int getKeyboardHeight() {
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L5;
        int r02 = this.keyboardHeightLand;
    L7:
        if (this.includeNavigationBar == false) goto L9;
        int r1 = AndroidUtilities.navigationBarHeight;
    L10:
        int r03 = r02 + r1;
        if (this.emojiExpanded == true) goto L13;
        return r03;
    L13:
        return Math.min(r03 + AndroidUtilities.dp(200.0f), AndroidUtilities.displaySize.y);
    L9:
        r1 = 0;
        goto L10
    L5:
        r02 = this.keyboardHeight;
        goto L7
    }

    public Editable getText() {
        return this.editText.getText();
    }

    public void hideEmojiView() {
        if (this.emojiViewVisible == true) goto L9;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        if (r0.getVisibility() == 8) goto L9;
        this.emojiView.setVisibility(8);
        this.emojiViewAlpha = 0.0f;
    L9:
        this.emojiPadding = 0;
        boolean r1 = this.emojiExpanded;
        this.emojiExpanded = false;
        if (r1 == false) goto L16;
        EmojiView r12 = this.emojiView;
        if (r12 == null) goto L14;
        r12.closeSearch(false);
    L14:
        updatedEmojiExpanded();
        return;
    }

    public void hidePopup(boolean r5) {
        if (isPopupShowing() == false) goto L5;
        showPopup(0);
    L5:
        if (r5 == false) goto L17;
        EmojiView r52 = this.emojiView;
        if (r52 != null) goto L9;
    L16:
        hideEmojiView();
        goto L17
    L9:
        if (r52.getVisibility() != 0) goto L16;
        if (this.waitingForKeyboardOpen == true) goto L16;
        int r53 = this.emojiView.getMeasuredHeight();
        if ((this.emojiView.getParent() instanceof ViewGroup) == false) goto L15;
        r53 = r53 + (((ViewGroup) this.emojiView.getParent()).getHeight() - this.emojiView.getBottom());
    L15:
        this.emojiViewAlpha = 1.0f;
        ValueAnimator r0 = ValueAnimator.ofFloat(new float[]{0.0f, r53});
        r0.addUpdateListener(new EditTextEmoji$$ExternalSyntheticLambda2(this, r53));
        this.isAnimatePopupClosing = true;
        r0.addListener(new AnonymousClass4(this));
        r0.setDuration(250);
        r0.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r0.start();
    L17:
        boolean r54 = this.emojiExpanded;
        this.emojiExpanded = false;
        if (r54 == false) goto L24;
        EmojiView r55 = this.emojiView;
        if (r55 == null) goto L22;
        r55.closeSearch(false);
    L22:
        updatedEmojiExpanded();
        return;
    }

    public boolean isAnimatePopupClosing() {
        return this.isAnimatePopupClosing;
    }

    public boolean isKeyboardVisible() {
        return this.keyboardVisible;
    }

    public boolean isPopupShowing() {
        return this.emojiViewVisible;
    }

    public boolean isPopupView(View r1) {
        if (r1 != this.emojiView) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean isPopupVisible() {
        EmojiView r0 = this.emojiView;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getVisibility() != 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isWaitingForKeyboardOpen() {
        return this.waitingForKeyboardOpen;
    }

    public int length() {
        return this.editText.length();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        NotificationCenter.getGlobalInstance().addObserver(this, NotificationCenter.emojiLoaded);
    }

    public void onDestroy() {
        this.destroyed = true;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L5;
        r0.onDestroy();
    L5:
        SizeNotifierFrameLayout r02 = this.sizeNotifierLayout;
        if (r02 == null) goto L9;
        r02.removeDelegate(this);
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        onDestroy();
        super.onDetachedFromWindow();
        NotificationCenter.getGlobalInstance().removeObserver(this, NotificationCenter.emojiLoaded);
    }

    public void onEmojiKeyboardUpdate() {
    }

    public void onLineCountChanged(int r1, int r2) {
    }

    public void onPause() {
        this.isPaused = true;
        closeKeyboard();
    }

    public void onResume() {
        this.isPaused = false;
        if (this.showKeyboardOnResume == false) goto L14;
        this.showKeyboardOnResume = false;
        this.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.editText);
        if (AndroidUtilities.usingHardwareInput == false) goto L7;
        return;
    L7:
        if (this.keyboardVisible == false) goto L9;
        return;
    L9:
        if (AndroidUtilities.isInMultiwindow == false) goto L11;
        return;
    L11:
        if (AndroidUtilities.isTablet() == true) goto L18;
        this.waitingForKeyboardOpen = true;
        onWaitingForKeyboard();
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        AndroidUtilities.runOnUIThread(this.openKeyboardRunnable, 100);
        return;
    L18:
        return;
    }

    public boolean onScrollYChange(int r1) {
        return true;
    }

    @Override // org.telegram.ui.Components.SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate
    public void onSizeChanged(int r6, boolean r7) {
        if (r6 > AndroidUtilities.dp(50.0f)) goto L5;
    L17:
        boolean r1 = false;
        if (isPopupShowing() == false) goto L38;
        if (r7 == false) goto L21;
        int r0 = this.keyboardHeightLand;
    L23:
        if (this.includeNavigationBar == false) goto L25;
        int r2 = AndroidUtilities.navigationBarHeight;
    L26:
        int r02 = r0 + r2;
        if (this.emojiExpanded == false) goto L29;
        r02 = Math.min(r02 + AndroidUtilities.dp(200.0f), AndroidUtilities.displaySize.y);
    L29:
        FrameLayout.LayoutParams r22 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        int r3 = r22.width;
        int r4 = AndroidUtilities.displaySize.x;
        if (r3 == r4) goto L32;
    L33:
        r22.width = r4;
        r22.height = r02;
        this.emojiView.setLayoutParams(r22);
        SizeNotifierFrameLayout r03 = this.sizeNotifierLayout;
        if (r03 == null) goto L38;
        this.emojiPadding = r22.height;
        r03.requestLayout();
        onWindowSizeChanged();
        if (this.lastEmojiExpanded == this.emojiExpanded) goto L38;
        onEmojiKeyboardUpdate();
        goto L38
    L32:
        if (r22.height == r02) goto L38;
    L25:
        r2 = 0;
        goto L26
    L21:
        r0 = this.keyboardHeight;
    L38:
        this.lastEmojiExpanded = this.emojiExpanded;
        boolean r23 = true;
        if (this.lastSizeChangeValue1 == r6) goto L41;
    L51:
        this.lastSizeChangeValue1 = r6;
        this.lastSizeChangeValue2 = r7;
        boolean r72 = this.keyboardVisible;
        if (this.editText.isFocused() == false) goto L55;
        if (r6 <= 0) goto L55;
    L56:
        this.keyboardVisible = r23;
        if (r23 == false) goto L62;
        if (isPopupShowing() == false) goto L62;
        showPopup(0);
    L62:
        if (this.emojiPadding == 0) goto L70;
        boolean r62 = this.keyboardVisible;
        if (r62 == true) goto L70;
        if (r62 == r72) goto L70;
        if (isPopupShowing() == true) goto L70;
        this.emojiPadding = 0;
        this.sizeNotifierLayout.requestLayout();
    L70:
        if (this.keyboardVisible == true) goto L72;
    L74:
        onWindowSizeChanged();
        return;
    L72:
        if (this.waitingForKeyboardOpen == false) goto L74;
        this.waitingForKeyboardOpen = false;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
    L55:
        r23 = false;
        goto L56
    L41:
        if (this.lastSizeChangeValue2 != r7) goto L51;
        if (allowSearch() == true) goto L45;
    L49:
        onWindowSizeChanged();
        return;
    L45:
        if (this.editText.isFocused() == false) goto L48;
        if (r6 <= 0) goto L48;
        r1 = true;
    L48:
        this.keyboardVisible = r1;
        goto L49
    L5:
        if (this.keyboardVisible == true) goto L11;
        int r04 = this.currentStyle;
        if (r04 == 2) goto L11;
        if (r04 != 3) goto L17;
    L11:
        if (AndroidUtilities.isInMultiwindow == true) goto L17;
        if (AndroidUtilities.isTablet() == true) goto L17;
        if (r7 == false) goto L16;
        this.keyboardHeightLand = r6;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).apply();
        goto L17
    L16:
        this.keyboardHeight = r6;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).apply();
        goto L17
    }

    public void onWaitingForKeyboard() {
    }

    public void openKeyboard() {
        this.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.editText);
    }

    public void openKeyboardInternal() {
        onWaitingForKeyboard();
        if (AndroidUtilities.usingHardwareInput == false) goto L5;
    L8:
        int r0 = 0;
    L9:
        showPopup(r0);
        this.editText.requestFocus();
        AndroidUtilities.showKeyboard(this.editText);
        if (this.isPaused == false) goto L14;
        this.showKeyboardOnResume = true;
        return;
    L14:
        if (AndroidUtilities.usingHardwareInput == false) goto L16;
        return;
    L16:
        if (this.keyboardVisible == false) goto L18;
        return;
    L18:
        if (AndroidUtilities.isInMultiwindow == false) goto L20;
        return;
    L20:
        if (AndroidUtilities.isTablet() == true) goto L26;
        this.waitingForKeyboardOpen = true;
        AndroidUtilities.cancelRunOnUIThread(this.openKeyboardRunnable);
        AndroidUtilities.runOnUIThread(this.openKeyboardRunnable, 100);
        return;
    L26:
        return;
    L5:
        if (this.isPaused == true) goto L8;
        r0 = 2;
        goto L9
    }

    public void setAdjustPanLayoutHelper(AdjustPanLayoutHelper r1) {
        this.adjustPanLayoutHelper = r1;
    }

    public void setDelegate(EditTextEmojiDelegate r1) {
    }

    public void setEmojiViewCacheType(int r1) {
        this.emojiViewCacheType = r1;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L6;
        r0.emojiCacheType = r1;
        return;
    }

    @Override // android.view.View
    public void setEnabled(boolean r2) {
        this.editText.setEnabled(r2);
        if (r2 == true) goto L6;
        this.emojiButton.setAlpha(0.5f);
        this.emojiButton.setBackground(null);
        this.emojiButton.setOnClickListener(null);
        return;
    }

    public void setFilters(InputFilter[] r1) {
        this.editText.setFilters(r1);
    }

    @Override // android.view.View
    public void setFocusable(boolean r1) {
        this.editText.setFocusable(r1);
    }

    public void setHint(CharSequence r2) {
        if (this.currentStyle != 0) goto L6;
        this.editTextFieldContainer.setText((String) r2);
        return;
    L6:
        this.editText.setHint(r2);
    }

    public void setMaxLines(int r1) {
        this.editText.setMaxLines(r1);
    }

    public void setSelection(int r1) {
        this.editText.setSelection(r1);
    }

    public void setSelection(int r1, int r2) {
        this.editText.setSelection(r1, r2);
    }

    public void setSizeNotifierLayout(SizeNotifierFrameLayout r2) {
        SizeNotifierFrameLayout r0 = this.sizeNotifierLayout;
        if (r0 == null) goto L5;
        r0.removeDelegate(this);
    L5:
        this.sizeNotifierLayout = r2;
        r2.addDelegate(this);
    }

    public void setSuggestionsEnabled(boolean r2) {
        int r0 = this.editText.getInputType();
        if (r2 == true) goto L5;
        int r22 = 524288 | r0;
    L7:
        if (this.editText.getInputType() == r22) goto L10;
        this.editText.setInputType(r22);
        return;
    L10:
        return;
    L5:
        r22 = (-524289) & r0;
        goto L7
    }

    public void setText(CharSequence r1) {
        this.editText.setText(r1);
    }

    public void showPopup(int r11) {
        if (r11 != 1) goto L51;
        EmojiView r112 = this.emojiView;
        if (r112 != null) goto L7;
    L9:
        boolean r113 = false;
    L10:
        createEmojiView();
        this.emojiView.setVisibility(0);
        this.emojiViewVisible = true;
        this.emojiViewAlpha = 1.0f;
        EmojiView r4 = this.emojiView;
        if (this.keyboardHeight > 0) goto L17;
        if (AndroidUtilities.isTablet() == false) goto L15;
        this.keyboardHeight = AndroidUtilities.dp(150.0f);
        goto L17
    L15:
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
    L17:
        if (this.keyboardHeightLand <= 0) goto L19;
    L22:
        Point r5 = AndroidUtilities.displaySize;
        if (r5.x <= r5.y) goto L25;
        int r52 = this.keyboardHeightLand;
    L27:
        if (this.includeNavigationBar == false) goto L29;
        int r6 = AndroidUtilities.navigationBarHeight;
    L30:
        int r53 = r52 + r6;
        if (this.emojiExpanded == false) goto L33;
        r53 = Math.min(r53 + AndroidUtilities.dp(200.0f), AndroidUtilities.displaySize.y);
    L33:
        FrameLayout.LayoutParams r62 = (FrameLayout.LayoutParams) r4.getLayoutParams();
        r62.height = r53;
        r4.setLayoutParams(r62);
        if (AndroidUtilities.isInMultiwindow == false) goto L36;
    L38:
        SizeNotifierFrameLayout r42 = this.sizeNotifierLayout;
        if (r42 == null) goto L41;
        this.emojiPadding = r53;
        r42.requestLayout();
        this.emojiIconDrawable.setIcon(R.drawable.input_keyboard, true);
        onWindowSizeChanged();
    L41:
        onEmojiKeyboardUpdate();
        if (this.keyboardVisible == true) goto L48;
        if (r113 == true) goto L48;
        if (allowSearchAnimation() == false) goto L48;
        ValueAnimator r114 = ValueAnimator.ofFloat(new float[]{this.emojiPadding, 0.0f});
        r114.addUpdateListener(new EditTextEmoji$$ExternalSyntheticLambda3(this));
        r114.addListener(new AnonymousClass5(this));
        r114.setDuration(250);
        r114.setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator);
        r114.start();
        return;
    L48:
        this.emojiView.setAlpha(1.0f);
        this.emojiViewAlpha = 1.0f;
        bottomPanelTranslationY(0.0f);
        return;
    L36:
        if (AndroidUtilities.isTablet() == true) goto L38;
        AndroidUtilities.hideKeyboard(this.editText);
        goto L38
    L29:
        r6 = 0;
        goto L30
    L25:
        r52 = this.keyboardHeight;
        goto L27
    L19:
        if (AndroidUtilities.isTablet() == false) goto L21;
        this.keyboardHeightLand = AndroidUtilities.dp(150.0f);
        goto L22
    L21:
        this.keyboardHeightLand = MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3", AndroidUtilities.dp(200.0f));
        goto L22
    L7:
        if (r112.getVisibility() != 0) goto L9;
        r113 = true;
        goto L10
    L51:
        if (this.emojiButton == null) goto L57;
        int r3 = this.currentStyle;
        ReplaceableIconDrawable r43 = this.emojiIconDrawable;
        if (r3 != 0) goto L55;
        r43.setIcon(R.drawable.smiles_tab_smiles, true);
        goto L57
    L55:
        r43.setIcon(R.drawable.input_smile, true);
    L57:
        if (this.emojiView == null) goto L63;
        this.emojiViewVisible = false;
        onEmojiKeyboardUpdate();
        if (AndroidUtilities.usingHardwareInput == false) goto L61;
    L62:
        this.emojiView.setVisibility(8);
        this.emojiViewAlpha = 0.0f;
        goto L63
    L61:
        if (AndroidUtilities.isInMultiwindow == true) goto L62;
    L63:
        SizeNotifierFrameLayout r2 = this.sizeNotifierLayout;
        if (r2 == null) goto L69;
        if (r11 != 0) goto L67;
        this.emojiPadding = 0;
        this.emojiViewAlpha = 0.0f;
    L67:
        r2.requestLayout();
        onWindowSizeChanged();
        return;
    }

    public void updateColors() {
        int r0 = this.currentStyle;
        if (r0 != 0) goto L6;
        this.editText.setHintTextColor(getThemedColor(Theme.key_windowBackgroundWhiteHintText));
        EditTextCaption r02 = this.editText;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r02.setCursorColor(getThemedColor(r1));
        this.editText.setTextColor(getThemedColor(r1));
    L12:
        this.emojiIconDrawable.setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_messagePanelIcons), PorterDuff.Mode.MULTIPLY));
        EmojiView r4 = this.emojiView;
        if (r4 == null) goto L16;
        r4.updateColors();
        return;
    L16:
        return;
    L6:
        if (r0 != 2) goto L8;
    L11:
        this.editText.setHintTextColor(-1929379841);
        this.editText.setTextColor(-1);
        this.editText.setCursorColor(-1);
        this.editText.setHandlesColor(-1);
        this.editText.setHighlightColor(822083583);
        this.editText.quoteColor = -1;
        goto L12
    L8:
        if (r0 == 3) goto L11;
        this.editText.setHintTextColor(getThemedColor(Theme.key_dialogTextHint));
        this.editText.setTextColor(getThemedColor(Theme.key_dialogTextBlack));
        goto L12
    }

    public void updatedEmojiExpanded() {
    }
}
