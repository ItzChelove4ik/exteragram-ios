package org.telegram.ui.iv;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Property;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.text.LocaleUtils;
import com.google.android.gms.cast.MediaError;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MediaController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessageChatArguments;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_iv;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer$$ExternalSyntheticLambda24;
import org.telegram.ui.Cells.TextSelectionHelper;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AIEditorAlert;
import org.telegram.ui.Components.AiButtonDrawable;
import org.telegram.ui.Components.AlertsCreator;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.ChatActivityEnterViewAnimatedIconView;
import org.telegram.ui.Components.ChatAttachAlert;
import org.telegram.ui.Components.ChatAttachAlertDocumentLayout;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EmojiView;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.PremiumFeatureBottomSheet;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.ScaleStateListAnimator;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.blur3.drawable.BlurredBackgroundDrawable;
import org.telegram.ui.Components.chat.ChatInputViewsContainer;
import org.telegram.ui.GradientClip;
import org.telegram.ui.MessageSendPreview;
import org.telegram.ui.StickersActivity;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;
import org.telegram.ui.iv.RichEditorListView;

/* loaded from: classes7.dex */
public class RichEditor extends BaseFragment implements NotificationCenter.NotificationCenterDelegate {
    private ImageView addButton;
    private ImageView aiButton;
    private Button aiStyleButton;
    private ChatActivityEnterView animateEnterView;
    private int[] animateEnterViewFrom;
    private int[] animateEnterViewTo;
    private RectF animateFromRect;
    private BlurredBackgroundDrawable animateInputBackground;
    private ChatInputViewsContainer animateInputView;
    private float animateOpenProgress;
    private boolean animatingOpen;
    private ImageView backButton;
    private final ArrayList<Button> blockButtons;
    private LinearLayout blocksLayout;
    private HorizontalScrollView blocksScrollView;
    private FrameLayout bottomContainer;
    private View bottomGradient;
    private FrameLayout bottomInnerContainer;
    private int bottomInset;
    private LinearLayout bottomPanel;
    private int bottomPanelType;
    private FrameLayout bulletinContainer;
    private ChatActivity chatActivity;
    private RichCommandSuggestions commandSuggestions;
    private SizeNotifierFrameLayout container;
    private boolean convertToSimpleOnOpen;
    private ItemOptions currentMenuVisible;
    private Button dateButton;
    private MessageObject editingMessageObject;
    private ChatActivityEnterViewAnimatedIconView emojiButton;
    private int emojiPadding;
    private ValueAnimator emojiSearchAnimator;
    private boolean emojiSearchOpened;
    private float emojiSearchProgress;
    private RichEditText emojiTargetEditText;
    private int emojiTargetSelection;
    private EmojiView emojiView;
    private boolean emojiViewVisible;
    private final ArrayList<Button> formattingButtons;
    private LinearLayout formattingLayout1;
    private LinearLayout formattingLayout2;
    private LinearLayout formattingLayout3;
    private LinearLayout formattingPanel;
    private LinearLayout formattingPanelLayout;
    private int formattingScrollMaxWidth;
    private HorizontalScrollView formattingScrollView;
    private LinearLayout historyButtons;
    private int imeInset;
    private String initialHtml;
    private CharSequence initialHtmlAfter;
    private CharSequence initialHtmlBefore;
    private TL_iv.RichMessage initialRichMessage;
    private int initialSelectionEnd;
    private int initialSelectionStart;
    private CharSequence initialText;
    private Button inlineButton;
    private int keyboardHeight;
    private int keyboardHeightLand;
    private boolean keyboardVisible;
    private final Runnable limitCheckRunnable;
    private Button linkButton;
    private RichEditorListView listView;
    private int[] location;
    private Button mathButton;
    private MessageSendPreview messageSendPreview;
    private Runnable onClearedCallback;
    private Runnable onSentCallback;
    private Runnable pendingSend;
    private boolean persistedDraftOnEnd;
    private ArrayList<Button> premiumButtons;
    private Button quoteButton;
    private ImageView redoButton;
    private int reorderSavedPanelType;
    private ChatActivityEnterView.SendButton sendButton;
    private boolean sendButtonLoading;
    private boolean sent;
    private SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate sizeDelegate;
    private final Rect tempRect;
    private View topGradient;
    private FrameLayout topPanel;
    private boolean trashHovered;
    private FrameLayout trashPanel;
    private RLottieImageView trashPanelIcon;
    private ImageView undoButton;

    /* renamed from: org.telegram.ui.iv.RichEditor$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ RichEditor this$0;
        final /* synthetic */ Runnable val$callback;

        public AnonymousClass1(RichEditor r1, Runnable r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            RichEditor.m21716$$Nest$fputanimatingOpen(this.this$0, false);
            RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).setAlpha(1.0f);
            RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.setVisibility(0);
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).setRadius(AndroidUtilities.dp(24.0f));
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).setAlpha(255);
            RichEditor.m21690$$Nest$fgetanimateInputView(this.this$0).drawInputBackground = true;
            RichEditor.m21690$$Nest$fgetanimateInputView(this.this$0).invalidate();
            this.val$callback.run();
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$10, reason: invalid class name */
    public class AnonymousClass10 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ RichEditor this$0;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;

        public AnonymousClass10(RichEditor r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$chatAttachAlert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
            if (r1 != 7) goto L5;
        L6:
            HashMap<Object, Object> r12 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotos();
            ArrayList<Object> r22 = this.val$chatAttachAlert.getPhotoLayout().getSelectedPhotosOrder();
            BlockRow r42 = RichEditor.m21710$$Nest$fgetlistView(this.this$0).pendingMediaRow;
            RichEditor.m21710$$Nest$fgetlistView(this.this$0).pendingMediaRow = null;
            int r52 = 0;
        L8:
            if (r52 >= r22.size()) goto L16;
            Object r62 = r12.get(r22.get(r52));
            if ((r62 instanceof MediaController.PhotoEntry) == true) goto L11;
            r52 = r52 + 1;
            goto L8
        L11:
            RichEditor r13 = this.this$0;
            if (r42 == null) goto L14;
            RichEditor.m21710$$Nest$fgetlistView(r13).addMediaToRow(r42, (MediaController.PhotoEntry) r62);
            goto L16
        L14:
            RichEditor.m21710$$Nest$fgetlistView(r13).attachMedia((MediaController.PhotoEntry) r62);
        L16:
            RichEditor.m21710$$Nest$fgetlistView(this.this$0).pendingMediaRow = null;
            this.val$chatAttachAlert.dismiss(true);
            return;
        L5:
            if (r1 != 8) goto L16;
            goto L6
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didSelectBot(TLRPC.User r1) {
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void doOnIdle(Runnable r1) {
            NotificationCenter.getInstance(this.this$0.getCurrentAccount()).doOnIdle(r1);
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public boolean needEnterComment() {
            return false;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void onCameraOpened() {
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$11, reason: invalid class name */
    public class AnonymousClass11 implements ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate {
        final /* synthetic */ RichEditor this$0;
        final /* synthetic */ ChatAttachAlert val$chatAttachAlert;

        public AnonymousClass11(RichEditor r1, ChatAttachAlert r2) {
            this.this$0 = r1;
            this.val$chatAttachAlert = r2;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void didSelectFiles(ArrayList<String> r1, String r2, ArrayList<TLRPC.MessageEntity> r3, ArrayList<MessageObject> r4, boolean r5, int r6, int r7, long r8, boolean r10, long r11) {
            if (r1 != null) goto L5;
        L7:
            if (r4 != null) goto L9;
        L11:
            this.val$chatAttachAlert.dismiss(true);
            return;
        L9:
            if (r4.isEmpty() == true) goto L11;
            RichEditor.m21710$$Nest$fgetlistView(this.this$0).attachDocument(r4.get(0));
            goto L11
        L5:
            if (r1.isEmpty() == true) goto L7;
            RichEditor.m21710$$Nest$fgetlistView(this.this$0).attachDocument(r1.get(0));
            goto L11
        }

        @Override // org.telegram.ui.Components.ChatAttachAlertDocumentLayout.DocumentSelectActivityDelegate
        public void startDocumentSelectActivity() {
            Intent r0 = new Intent("android.intent.action.GET_CONTENT");     // Catch: Exception -> L4
            r0.setType("*/*");     // Catch: Exception -> L4
            this.this$0.startActivityForResult(r0, 21);     // Catch: Exception -> L4
            return;
        L4:
            e = move-exception;
            FileLog.e(e);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$12, reason: invalid class name */
    public class AnonymousClass12 implements ChatAttachAlert.ChatAttachViewDelegate {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass12(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.ChatAttachAlert.ChatAttachViewDelegate
        public void didPressedButton(int r1, boolean r2, boolean r3, int r4, int r5, long r6, boolean r8, boolean r9, long r10) {
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$13, reason: invalid class name */
    public class AnonymousClass13 implements AlertsCreator.ScheduleDatePickerDelegate {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass13(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
        public void didSelectDate(boolean r1, int r2, int r3) {
            RichEditor.m21735$$Nest$msendMessage(this.this$0, r1, r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$14, reason: invalid class name */
    public class AnonymousClass14 implements AlertsCreator.ScheduleDatePickerDelegate {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass14(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.AlertsCreator.ScheduleDatePickerDelegate
        public void didSelectDate(boolean r2, int r3, int r4) {
            RichEditor.m21735$$Nest$msendMessage(this.this$0, r2, r3, r4);
            if (RichEditor.m21711$$Nest$fgetmessageSendPreview(this.this$0) == null) goto L6;
            RichEditor.m21711$$Nest$fgetmessageSendPreview(this.this$0).dismissInstant();
            RichEditor.m21723$$Nest$fputmessageSendPreview(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$15, reason: invalid class name */
    public class AnonymousClass15 implements EmojiView.EmojiViewDelegate {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass15(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isSearchOpened() {
            return RichEditor.m21701$$Nest$fgetemojiSearchOpened(this.this$0);
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean isUserSelf() {
            if (RichEditor.m21698$$Nest$fgetchatActivity(this.this$0) != null) goto L5;
            return false;
        L5:
            if (UserObject.isUserSelf(RichEditor.m21698$$Nest$fgetchatActivity(this.this$0).getCurrentUser()) == false) goto L10;
            return true;
        L10:
            return false;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public boolean onBackspace() {
            RichEditText r3 = RichEditor.m21731$$Nest$mresolveEmojiTarget(this.this$0);
            if (r3 != null) goto L5;
        L9:
            return false;
        L5:
            if (r3.length() == 0) goto L9;
            r3.dispatchKeyEvent(new KeyEvent(0, 67));
            return true;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onCustomEmojiSelected(long r3, TLRPC.Document r5, String r6, boolean r7) {
            RichEditText r72 = RichEditor.m21731$$Nest$mresolveEmojiTarget(this.this$0);
            if (r72 == null) goto L21;
            int r0 = RichEditor.m21732$$Nest$mresolveEmojiTargetOffset(this.this$0, r72);
            if (r6 != null) goto L9;
            r6 = "😀";
        L9:
            SpannableString r1 = new SpannableString(r6);     // Catch: Exception -> L17
            if (r5 == null) goto L12;
            AnimatedEmojiSpan r32 = new AnimatedEmojiSpan(r5, r72.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L13:
            r32.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();     // Catch: Exception -> L17
            r1.setSpan(r32, 0, r1.length(), 33);     // Catch: Exception -> L17
            r72.setText(r72.getText().insert(r0, r1));     // Catch: Exception -> L17
            int r02 = r0 + r1.length();     // Catch: Exception -> L17
            r72.setSelection(r02, r02);     // Catch: Exception -> L17
            if (r72 != RichEditor.m21702$$Nest$fgetemojiTargetEditText(this.this$0)) goto L20;
            RichEditor.m21721$$Nest$fputemojiTargetSelection(this.this$0, r02);     // Catch: Exception -> L17
            return;
        L20:
            return;
        L12:
            r32 = new AnimatedEmojiSpan(r3, r72.getPaint().getFontMetricsInt());     // Catch: Exception -> L17
        L22:
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSelected(String r6) {
            RichEditText r0 = RichEditor.m21731$$Nest$mresolveEmojiTarget(this.this$0);
            if (r0 == null) goto L14;
            int r1 = RichEditor.m21732$$Nest$mresolveEmojiTargetOffset(this.this$0, r0);
            CharSequence r62 = Emoji.replaceEmoji(r6, r0.getPaint().getFontMetricsInt(), false, null);     // Catch: Exception -> L10
            r0.setText(r0.getText().insert(r1, r62));     // Catch: Exception -> L10
            int r12 = r1 + r62.length();     // Catch: Exception -> L10
            r0.setSelection(r12, r12);     // Catch: Exception -> L10
            if (r0 != RichEditor.m21702$$Nest$fgetemojiTargetEditText(this.this$0)) goto L13;
            RichEditor.m21721$$Nest$fputemojiTargetSelection(this.this$0, r12);     // Catch: Exception -> L10
            return;
        L13:
            return;
        L15:
            return;
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onEmojiSettingsClick(ArrayList<TLRPC.TL_messages_stickerSet> r3) {
            this.this$0.presentFragment(new StickersActivity(5, r3));
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onSearchOpenClose(int r4) {
            boolean r0 = false;
            if (r4 == 0) goto L7;
            RichEditText r1 = RichEditor.m21710$$Nest$fgetlistView(this.this$0).getFocusedEditTextOrNull();
            if (r1 == null) goto L7;
            RichEditor.m21720$$Nest$fputemojiTargetEditText(this.this$0, r1);
            RichEditor.m21721$$Nest$fputemojiTargetSelection(this.this$0, Math.max(0, r1.getSelectionEnd()));
        L7:
            RichEditor r12 = this.this$0;
            if (r4 == 0) goto L10;
            r0 = true;
        L10:
            RichEditor.m21719$$Nest$fputemojiSearchOpened(r12, r0);
            RichEditor r3 = this.this$0;
            RichEditor.m21725$$Nest$manimateEmojiSearch(r3, RichEditor.m21701$$Nest$fgetemojiSearchOpened(r3));
        }

        @Override // org.telegram.ui.Components.EmojiView.EmojiViewDelegate
        public void onStickersSettingsClick() {
            this.this$0.presentFragment(new StickersActivity(0, null));
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private final Paint bgPaint;
        private final Path clipPath;
        private final RectF rect;
        final /* synthetic */ RichEditor this$0;
        private boolean touchStartedInBottomPanel;

        public AnonymousClass2(RichEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.bgPaint = new Paint(1);
            this.clipPath = new Path();
            this.rect = new RectF();
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            this.bgPaint.setColor(Theme.multAlpha(this.this$0.getThemedColor(Theme.key_windowBackgroundWhite), RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)));
            if (RichEditor.m21692$$Nest$fgetanimatingOpen(this.this$0) == true) goto L5;
        L11:
            r10.drawRect(0.0f, 0.0f, getWidth(), getHeight(), this.bgPaint);
            super.dispatchDraw(r10);
            return;
        L5:
            if (RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0) == null) goto L11;
            this.rect.set(0.0f, 0.0f, getWidth(), getHeight());
            this.rect.inset(-AndroidUtilities.dp(7.0f), -AndroidUtilities.dp(7.0f));
            float r1 = AndroidUtilities.lerp(AndroidUtilities.dpf2(24.0f), 0.0f, RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0));
            AndroidUtilities.lerp(RichEditor.m21688$$Nest$fgetanimateFromRect(this.this$0), this.rect, RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0), this.rect);
            RichEditor.m21715$$Nest$fgettempRect(this.this$0).set(RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).getBounds());
            BlurredBackgroundDrawable r3 = RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0);
            RectF r4 = this.rect;
            r3.setBounds((int) r4.left, (int) r4.top, (int) r4.right, (int) r4.bottom);
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).setRadius(r1);
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).setAlpha((int) ((1.0f - RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)) * 255.0f));
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).draw(r10);
            RichEditor.m21689$$Nest$fgetanimateInputBackground(this.this$0).setBounds(RichEditor.m21715$$Nest$fgettempRect(this.this$0));
            this.rect.inset(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
            r10.drawRoundRect(this.rect, r1, r1, this.bgPaint);
            if (RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0) == null) goto L9;
            r10.save();
            r10.translate(AndroidUtilities.lerp(RichEditor.m21686$$Nest$fgetanimateEnterViewFrom(this.this$0)[0], RichEditor.m21687$$Nest$fgetanimateEnterViewTo(this.this$0)[0], RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)), AndroidUtilities.lerp(RichEditor.m21686$$Nest$fgetanimateEnterViewFrom(this.this$0)[1], RichEditor.m21687$$Nest$fgetanimateEnterViewTo(this.this$0)[1], RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)));
            r10.saveLayerAlpha(0.0f, 0.0f, RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).getWidth(), RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).getHeight(), (int) ((1.0f - RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)) * 255.0f), 31);
            RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).draw(r10);
            r10.restore();
            r10.restore();
            r10.save();
            r10.translate(AndroidUtilities.lerp(this.rect.right, (((RichEditor.m21693$$Nest$fgetbottomContainer(this.this$0).getX() + RichEditor.m21694$$Nest$fgetbottomInnerContainer(this.this$0).getX()) + RichEditor.m21696$$Nest$fgetbottomPanel(this.this$0).getX()) + RichEditor.m21713$$Nest$fgetsendButton(this.this$0).getX()) + RichEditor.m21713$$Nest$fgetsendButton(this.this$0).getWidth(), RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)) - RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.getWidth(), AndroidUtilities.lerp(this.rect.bottom, (((RichEditor.m21693$$Nest$fgetbottomContainer(this.this$0).getY() + RichEditor.m21694$$Nest$fgetbottomInnerContainer(this.this$0).getY()) + RichEditor.m21696$$Nest$fgetbottomPanel(this.this$0).getY()) + RichEditor.m21713$$Nest$fgetsendButton(this.this$0).getY()) + RichEditor.m21713$$Nest$fgetsendButton(this.this$0).getHeight(), RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)) - RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.getHeight());
            r10.saveLayerAlpha(-AndroidUtilities.dp(6.0f), -AndroidUtilities.dp(6.0f), RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.getWidth(), RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.getHeight(), (int) ((1.0f - RichEditor.m21691$$Nest$fgetanimateOpenProgress(this.this$0)) * 255.0f), 31);
            RichEditor.m21685$$Nest$fgetanimateEnterView(this.this$0).sendButtonContainer.draw(r10);
            r10.restore();
            r10.restore();
        L9:
            r10.save();
            super.dispatchDraw(r10);
            r10.restore();
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEvent(KeyEvent r4) {
            if (r4.getAction() != 0) goto L11;
            if (r4.getKeyCode() != 47) goto L11;
            if (r4.isCtrlPressed() == false) goto L11;
            RichEditor.m21733$$Nest$msaveDraftWithBulletin(this.this$0);
            return true;
        L11:
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).handleKeyEvent(r4) == false) goto L14;
            return true;
        L14:
            return super.dispatchKeyEvent(r4);
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchTouchEvent(MotionEvent r7) {
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).textSelectionHelper.isInSelectionMode() == false) goto L8;
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).textSelectionOverlay.onTouchEvent(r7) == false) goto L8;
            return true;
        L8:
            if (RichEditor.m21701$$Nest$fgetemojiSearchOpened(this.this$0) == true) goto L10;
        L12:
            int r0 = (getHeight() - AndroidUtilities.dp(60.0f)) - Math.max(Math.max(RichEditor.m21700$$Nest$fgetemojiPadding(this.this$0), RichEditor.m21695$$Nest$fgetbottomInset(this.this$0)), RichEditor.m21709$$Nest$fgetimeInset(this.this$0));
        L14:
            if (r7.getAction() != 0) goto L21;
            if (RichEditor.m21704$$Nest$fgetemojiViewVisible(this.this$0) == false) goto L21;
            if (r7.getY() >= r0) goto L21;
            RichEditor.m21726$$Nest$mhideEmojiPopup(this.this$0, true);
        L21:
            if (r7.getAction() != 0) goto L27;
            if (r7.getY() <= (getPaddingTop() + AndroidUtilities.dp(60.0f))) goto L30;
            if (r7.getY() < r0) goto L27;
        L30:
            if (r7.getAction() != 0) goto L39;
            int r02 = (getHeight() - AndroidUtilities.dp(60.0f)) - Math.max(Math.max(RichEditor.m21700$$Nest$fgetemojiPadding(this.this$0), RichEditor.m21695$$Nest$fgetbottomInset(this.this$0)), RichEditor.m21709$$Nest$fgetimeInset(this.this$0));
            if (RichEditor.m21696$$Nest$fgetbottomPanel(this.this$0).getVisibility() == 0) goto L34;
        L36:
            boolean r03 = false;
        L37:
            this.touchStartedInBottomPanel = r03;
            goto L39
        L34:
            if (r7.getY() < r02) goto L36;
            r03 = true;
        L39:
            if (this.touchStartedInBottomPanel == true) goto L44;
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).handleSelectionTouch(r7) == false) goto L44;
            return true;
        L44:
            return super.dispatchTouchEvent(r7);
        L27:
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).textSelectionOverlay.checkOnTap(r7) == false) goto L30;
            r7.setAction(3);
            goto L30
        L10:
            if (RichEditor.m21703$$Nest$fgetemojiView(this.this$0) == null) goto L12;
            r0 = (int) RichEditor.m21703$$Nest$fgetemojiView(this.this$0).getY();
            goto L14
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$3, reason: invalid class name */
    public class AnonymousClass3 implements RichEditorListView.Delegate {
        final /* synthetic */ RichEditor this$0;

        /* renamed from: $r8$lambda$xgAhPBGsM0CIBp21_duO-AwAgy0, reason: not valid java name */
        public static /* synthetic */ ItemOptions m21743$r8$lambda$xgAhPBGsM0CIBp21_duOAwAgy0(AnonymousClass3 r0, View r1) {
            return r0.lambda$onSlashSuggest$0(r1);
        }

        public AnonymousClass3(RichEditor r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ ItemOptions lambda$onSlashSuggest$0(View r1) {
            return ItemOptions.makeOptions(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void makeEditTextFocusable(RichEditText r1, boolean r2) {
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public ItemOptions makeMenu(View r1) {
            return ItemOptions.makeOptions(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onBlockButtonEditRequested(RichEditorListView.BlockButtonEdit r3, View r4) {
            ItemOptions r42 = ItemOptions.makeOptions(this.this$0, r4).dontFocus();
            RichEditor r0 = this.this$0;
            RichEditor.m21718$$Nest$fputcurrentMenuVisible(r0, RichInlineButtonEditor.showBlock(r42, r0, r0.getContext(), this.this$0.getResourceProvider(), r3));
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onContentChanged() {
            RichEditor.m21741$$Nest$mupdateSendButtonLoading(this.this$0);
            RichEditor.m21742$$Nest$mupdateSendButtonLock(this.this$0);
            RichEditor.m21734$$Nest$mscheduleLimitCheck(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onHistoryChanged() {
            RichEditor.m21740$$Nest$mupdateHistoryButtons(this.this$0);
            RichEditor.m21742$$Nest$mupdateSendButtonLock(this.this$0);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onInlineButtonEditRequested(RichEditorListView.InlineButtonEdit r3, View r4) {
            ItemOptions r42 = ItemOptions.makeOptions(this.this$0, r4).dontFocus();
            RichEditor r0 = this.this$0;
            RichEditor.m21718$$Nest$fputcurrentMenuVisible(r0, RichInlineButtonEditor.show(r42, r0, r0.getContext(), this.this$0.getResourceProvider(), r3));
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onListLayoutUpdated() {
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onListScrolled(int r1) {
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onOpenAttachRequest(int r1, int r2) {
            RichEditor.m21729$$Nest$mopenAttach(this.this$0, r1, r2);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onOpenLocationRequest(BlockRow r1) {
            RichEditor.m21730$$Nest$mopenLocationPicker(this.this$0, r1);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onReorderEnd() {
            int r1 = 0;
            RichEditor.m21736$$Nest$msetTrashHovered(this.this$0, false, true);
            RichEditor r0 = this.this$0;
            if (RichEditor.m21712$$Nest$fgetreorderSavedPanelType(r0) == 2) goto L6;
            r1 = RichEditor.m21712$$Nest$fgetreorderSavedPanelType(this.this$0);
        L6:
            RichEditor.m21738$$Nest$mupdateBottomPanel(r0, r1, true);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public boolean onReorderMove(float r1, float r2) {
            boolean r12 = RichEditor.m21728$$Nest$misOverTrash(this.this$0, r2);
            RichEditor.m21736$$Nest$msetTrashHovered(this.this$0, r12, true);
            return r12;
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onReorderStart() {
            RichEditor r0 = this.this$0;
            RichEditor.m21724$$Nest$fputreorderSavedPanelType(r0, RichEditor.m21697$$Nest$fgetbottomPanelType(r0));
            RichEditor.m21736$$Nest$msetTrashHovered(this.this$0, false, false);
            RichEditor.m21738$$Nest$mupdateBottomPanel(this.this$0, 2, true);
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onSelectionChanged() {
            RichEditor r0 = this.this$0;
            if (RichEditor.m21710$$Nest$fgetlistView(r0).isInSelectionMode() == true) goto L5;
        L7:
            int r1 = 0;
        L8:
            RichEditor.m21738$$Nest$mupdateBottomPanel(r0, r1, true);
            RichEditor.m21739$$Nest$mupdateFormattingButtons(this.this$0);
            RichEditor.m21737$$Nest$mupdateBlockButtons(this.this$0);
            return;
        L5:
            if (RichEditor.m21710$$Nest$fgetlistView(this.this$0).selectionHasInlineFormattable() == false) goto L7;
            r1 = 1;
            goto L8
        }

        @Override // org.telegram.ui.iv.RichEditorListView.Delegate
        public void onSlashSuggest(RichTextCell r5, String r6) {
            if (RichEditor.m21699$$Nest$fgetcommandSuggestions(this.this$0) != null) goto L5;
            RichEditor.m21717$$Nest$fputcommandSuggestions(this.this$0, new RichCommandSuggestions(new RichEditor$3$$ExternalSyntheticLambda0(this), this.this$0.getResourceProvider()));
        L5:
            RichEditor.m21699$$Nest$fgetcommandSuggestions(this.this$0).update(r5, r6);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$4, reason: invalid class name */
    public class AnonymousClass4 extends HorizontalScrollView {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass4(RichEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r0 = View.MeasureSpec.getMode(r4);
            int r1 = View.MeasureSpec.getSize(r4);
            if (r0 != 1073741824) goto L6;
            super.onMeasure(r4, r5);
            return;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(r1, 0), r5);
            int r42 = getMeasuredWidth();
            if (r0 != Integer.MIN_VALUE) goto L9;
            r42 = Math.min(r42, r1);
        L9:
            setMeasuredDimension(r42, getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$5, reason: invalid class name */
    public class AnonymousClass5 extends ViewOutlineProvider {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass5(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(22.0f));
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$6, reason: invalid class name */
    public class AnonymousClass6 extends LinearLayout {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass6(RichEditor r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r9, int r10) {
            int r0 = View.MeasureSpec.getSize(r9);
            int r1 = getPaddingLeft() + getPaddingRight();
            if (RichEditor.m21705$$Nest$fgetformattingLayout1(this.this$0) == null) goto L8;
            if (RichEditor.m21705$$Nest$fgetformattingLayout1(this.this$0).getVisibility() != 0) goto L8;
            RichEditor.m21705$$Nest$fgetformattingLayout1(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r2 = (ViewGroup.MarginLayoutParams) RichEditor.m21705$$Nest$fgetformattingLayout1(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditor.m21705$$Nest$fgetformattingLayout1(this.this$0).getMeasuredWidth() + r2.leftMargin) + r2.rightMargin);
        L8:
            if (RichEditor.m21706$$Nest$fgetformattingLayout2(this.this$0) == null) goto L11;
            RichEditor.m21706$$Nest$fgetformattingLayout2(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r22 = (ViewGroup.MarginLayoutParams) RichEditor.m21706$$Nest$fgetformattingLayout2(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditor.m21706$$Nest$fgetformattingLayout2(this.this$0).getMeasuredWidth() + r22.leftMargin) + r22.rightMargin);
        L11:
            if (RichEditor.m21707$$Nest$fgetformattingLayout3(this.this$0) == null) goto L13;
            RichEditor.m21707$$Nest$fgetformattingLayout3(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(r0, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(44.0f), TLObject.FLAG_30));
            ViewGroup.MarginLayoutParams r23 = (ViewGroup.MarginLayoutParams) RichEditor.m21707$$Nest$fgetformattingLayout3(this.this$0).getLayoutParams();
            r1 = r1 + ((RichEditor.m21707$$Nest$fgetformattingLayout3(this.this$0).getMeasuredWidth() + r23.leftMargin) + r23.rightMargin);
        L13:
            RichEditor.m21722$$Nest$fputformattingScrollMaxWidth(this.this$0, Math.max(0, r0 - r1));
            super.onMeasure(r9, r10);
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$7, reason: invalid class name */
    public class AnonymousClass7 extends HorizontalScrollView {
        private final GradientClip clip;
        private final AnimatedFloat leftGradientAlpha;
        private final AnimatedFloat rightGradientAlpha;
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass7(RichEditor r3, Context r4) {
            this.this$0 = r3;
            super(r4);
            this.clip = new GradientClip();
            CubicBezierInterpolator r42 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.leftGradientAlpha = new AnimatedFloat(this, 300, r42);
            this.rightGradientAlpha = new AnimatedFloat(this, 300, r42);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r13) {
            float r0 = this.leftGradientAlpha.set(canScrollHorizontally(-1));
            float r1 = this.rightGradientAlpha.set(canScrollHorizontally(1));
            if (r0 <= 0.0f) goto L5;
        L8:
            Canvas r5 = r13;
            r5.saveLayerAlpha(getScrollX(), 0.0f, getScrollX() + getWidth(), getHeight(), 255, 31);
        L9:
            super.dispatchDraw(r5);
            if (r0 <= 0.0f) goto L12;
        L15:
            r5.save();
            if (r0 <= 0.0f) goto L19;
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(getScrollX(), 0.0f, getScrollX() + AndroidUtilities.dp(48.0f), getHeight());
            this.clip.draw(r5, r3, 0, r0);
        L19:
            if (r1 <= 0.0f) goto L21;
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set((getScrollX() + getWidth()) - AndroidUtilities.dp(48.0f), 0.0f, getScrollX() + getWidth(), getHeight());
            this.clip.draw(r5, r02, 2, r1);
        L21:
            r5.restore();
            return;
        L12:
            if (r1 > 0.0f) goto L15;
            return;
        L5:
            if (r1 > 0.0f) goto L8;
            r5 = r13;
            goto L9
        }

        @Override // android.widget.HorizontalScrollView, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r4, int r5) {
            int r0 = View.MeasureSpec.getMode(r4);
            if (r0 != 1073741824) goto L6;
            super.onMeasure(r4, r5);
            return;
        L6:
            super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), 0), r5);
            int r52 = getMeasuredWidth();
            int r1 = RichEditor.m21708$$Nest$fgetformattingScrollMaxWidth(this.this$0);
            if (r0 != Integer.MIN_VALUE) goto L9;
            r1 = Math.min(r1, View.MeasureSpec.getSize(r4));
        L9:
            setMeasuredDimension(Math.min(r52, r1), getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$8, reason: invalid class name */
    public class AnonymousClass8 extends ViewOutlineProvider {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass8(RichEditor r1) {
            this.this$0 = r1;
        }

        @Override // android.view.ViewOutlineProvider
        public void getOutline(View r7, Outline r8) {
            r8.setRoundRect(0, 0, r7.getWidth(), r7.getHeight(), AndroidUtilities.dp(22.0f));
        }
    }

    /* renamed from: org.telegram.ui.iv.RichEditor$9, reason: invalid class name */
    public class AnonymousClass9 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ RichEditor this$0;

        public AnonymousClass9(RichEditor r1, Context r2, int r3, Theme.ResourcesProvider r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInScheduleMode() {
            return RichEditor.m21727$$Nest$misInScheduleMode(this.this$0);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            if (RichEditor.m21714$$Nest$fgetsendButtonLoading(this.this$0) == false) goto L5;
            return true;
        L5:
            if (super.isOpen() == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    public static class Button extends ImageView implements Theme.Colorable {
        private boolean accent;
        private int backgroundColorKey;
        private int currentIcon;
        private boolean enabled;
        private boolean premium;
        private boolean premiumLocked;
        private Theme.ResourcesProvider resourcesProvider;
        private int roundRadius;
        private boolean selected;
        private int startIcon;

        public Button(Context r1, int r2, Theme.ResourcesProvider r3) {
            super(r1);
            this.roundRadius = 20;
            this.backgroundColorKey = Theme.key_windowBackgroundWhite;
            this.enabled = true;
            this.accent = true;
            this.currentIcon = r2;
            this.startIcon = r2;
            this.resourcesProvider = r3;
            if (r2 == 0) goto L5;
            setImageResource(r2);
        L5:
            setScaleType(ImageView.ScaleType.CENTER);
            ScaleStateListAnimator.apply(this);
            updateColors();
        }

        private RequiresPremiumDrawable wrapPremium(int r3) {
            return new RequiresPremiumDrawable(getContext(), r3).setCutoutColorKey(this.backgroundColorKey).setPremium(this.premiumLocked);
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        public void resetIcon() {
            updateIcon(this.startIcon);
        }

        public Button setAccent(boolean r2) {
            if (this.accent != r2) goto L5;
            return this;
        L5:
            this.accent = r2;
            updateColors();
            return this;
        }

        public Button setBackgroundColorKey(int r2) {
            if (this.backgroundColorKey != r2) goto L5;
            return this;
        L5:
            this.backgroundColorKey = r2;
            updateColors();
            return this;
        }

        @Override // android.view.View
        public void setEnabled(boolean r3) {
            if (this.enabled != r3) goto L5;
            return;
        L5:
            setClickable(r3);
            ViewPropertyAnimator r0 = animate();
            this.enabled = r3;
            if (r3 == false) goto L8;
            float r2 = 1.0f;
        L9:
            r0.alpha(r2).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
            return;
        L8:
            r2 = 0.5f;
            goto L9
        }

        public Button setPremium() {
            this.premium = true;
            setImageDrawable(wrapPremium(this.currentIcon));
            return this;
        }

        public void setPremiumLocked(boolean r2) {
            this.premiumLocked = r2;
            if ((getDrawable() instanceof RequiresPremiumDrawable) == false) goto L6;
            ((RequiresPremiumDrawable) getDrawable()).setPremium(r2);
            return;
        }

        public Button setRoundRadius(int r1) {
            this.roundRadius = r1;
            updateColors();
            return this;
        }

        @Override // android.widget.ImageView, android.view.View
        public void setSelected(boolean r2) {
            if (this.selected != r2) goto L5;
            return;
        L5:
            this.selected = r2;
            updateColors();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            if (this.selected == true) goto L5;
            setBackground(Theme.createRadSelectorDrawable(Theme.getColor(this.backgroundColorKey, this.resourcesProvider), Theme.getColor(Theme.key_listSelector, this.resourcesProvider), AndroidUtilities.dp(this.roundRadius), AndroidUtilities.dp(this.roundRadius)));
            setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            return;
        L5:
            if (this.accent == false) goto L7;
            int r0 = Theme.key_featuredStickers_addButton;
        L8:
            int r02 = Theme.getColor(r0);
            setBackground(Theme.createRadSelectorDrawable(Theme.blendOver(Theme.getColor(this.backgroundColorKey, this.resourcesProvider), Theme.multAlpha(r02, 0.1f)), Theme.multAlpha(r02, 0.1f), AndroidUtilities.dp(this.roundRadius), AndroidUtilities.dp(this.roundRadius)));
            setColorFilter(new PorterDuffColorFilter(r02, PorterDuff.Mode.SRC_IN));
            return;
        L7:
            r0 = Theme.key_windowBackgroundWhiteBlackText;
            goto L8
        }

        public void updateIcon(int r2) {
            if (this.currentIcon != r2) goto L5;
            return;
        L5:
            this.currentIcon = r2;
            if (this.premium == false) goto L9;
            AndroidUtilities.updateImageViewImageAnimated(this, wrapPremium(r2));
            return;
        L9:
            AndroidUtilities.updateImageViewImageAnimated(this, r2);
        }
    }

    public static class DraggingDrawable extends Drawable {
        private int alpha;
        private final AnimatedFloat animatedDragging;
        private boolean dragging;
        private final Paint paint;

        public DraggingDrawable(int r10) {
            this.paint = new Paint(1);
            this.animatedDragging = new AnimatedFloat(new RichEditor$DraggingDrawable$$ExternalSyntheticLambda0(this), 0, 420, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.alpha = 255;
            setColor(r10);
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r14) {
            float r0 = this.animatedDragging.set(this.dragging);
            if (r0 > 0.0f) goto L5;
            return;
        L5:
            this.paint.setAlpha((int) (this.alpha * r0));
            this.paint.setShadowLayer(AndroidUtilities.dp(12.0f) * r0, 0.0f, AndroidUtilities.dp(3.0f), Theme.multAlpha(805306368, r0));
            Rect r2 = getBounds();
            float r4 = AndroidUtilities.dp(8.0f) * r0;
            float r1 = AndroidUtilities.dp(0.0f) * r0;
            float r10 = AndroidUtilities.dp(12.0f) * r0;
            r14.drawRoundRect(r2.left + r4, r2.top + r1, r2.right - r4, (r2.bottom - r1) + (AndroidUtilities.dp(6.0f) * r0), r10, r10, this.paint);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -2;
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.alpha = r1;
        }

        public void setColor(int r1) {
            this.paint.setColor(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.paint.setColorFilter(r1);
        }

        public void setDragging(boolean r2) {
            if (this.dragging != r2) goto L5;
            return;
        L5:
            this.dragging = r2;
            invalidateSelf();
        }
    }

    public static class RequiresPremiumDrawable extends Drawable {
        private final Context context;
        public final Drawable icon;
        private Drawable premiumIcon;
        private Drawable premiumIconCutout;
        private int premiumIconCutoutColor;
        private int premiumIconCutoutColorKey;
        public boolean showPremiumIcon;

        public RequiresPremiumDrawable(Context r2, int r3) {
            this(r2, r2.getResources().getDrawable(r3).mutate());
        }

        public RequiresPremiumDrawable(Context r2, Drawable r3) {
            this.premiumIconCutoutColorKey = Theme.key_windowBackgroundWhite;
            this.showPremiumIcon = true;
            this.context = r2;
            this.icon = r3;
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r9) {
            Rect r0 = getBounds();
            int r1 = r0.centerX();
            int r02 = r0.centerY();
            Drawable r2 = this.icon;
            r2.setBounds(r1 - (r2.getIntrinsicWidth() / 2), r02 - (this.icon.getIntrinsicHeight() / 2), (this.icon.getIntrinsicWidth() / 2) + r1, (this.icon.getIntrinsicHeight() / 2) + r02);
            this.icon.draw(r9);
            if (this.showPremiumIcon == false) goto L15;
            int r12 = r1 + AndroidUtilities.dp(9.0f);
            int r03 = r02 + AndroidUtilities.dp(9.0f);
            int r3 = Theme.getColor(this.premiumIconCutoutColorKey);
            if (this.premiumIconCutout != null) goto L8;
            Drawable r4 = this.context.getResources().getDrawable(R.drawable.star_premium_cutout).mutate();
            this.premiumIconCutout = r4;
            this.premiumIconCutoutColor = r3;
            r4.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        L8:
            if (r3 == this.premiumIconCutoutColor) goto L11;
            Drawable r42 = this.premiumIconCutout;
            this.premiumIconCutoutColor = r3;
            r42.setColorFilter(new PorterDuffColorFilter(r3, PorterDuff.Mode.SRC_IN));
        L11:
            if (this.premiumIcon != null) goto L13;
            this.premiumIcon = this.context.getResources().getDrawable(R.drawable.star_premium).mutate();
        L13:
            this.premiumIconCutout.setBounds(r12 - AndroidUtilities.dp(9.0f), r03 - AndroidUtilities.dp(9.0f), AndroidUtilities.dp(9.0f) + r12, AndroidUtilities.dp(9.0f) + r03);
            this.premiumIconCutout.draw(r9);
            this.premiumIcon.setBounds(r12 - AndroidUtilities.dp(9.0f), r03 - AndroidUtilities.dp(9.0f), r12 + AndroidUtilities.dp(9.0f), r03 + AndroidUtilities.dp(9.0f));
            this.premiumIcon.draw(r9);
            return;
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicHeight() {
            return Math.max(AndroidUtilities.dp(38.0f), this.icon.getIntrinsicHeight());
        }

        @Override // android.graphics.drawable.Drawable
        public int getIntrinsicWidth() {
            return Math.max(AndroidUtilities.dp(38.0f), this.icon.getIntrinsicWidth());
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return this.icon.getOpacity();
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.icon.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.icon.setColorFilter(r1);
        }

        public RequiresPremiumDrawable setCutoutColorKey(int r1) {
            this.premiumIconCutoutColorKey = r1;
            return this;
        }

        public RequiresPremiumDrawable setPremium(boolean r2) {
            if (this.showPremiumIcon != r2) goto L5;
            return this;
        L5:
            this.showPremiumIcon = r2;
            invalidateSelf();
            return this;
        }
    }

    public static class ShadowWrapperDrawable extends Drawable implements Drawable.Callback {
        private final Drawable inner;
        private final Outline outline;
        private boolean pathDirty;
        private final RectF rectF;
        private final Paint shadowPaint;
        private final Path shadowPath;

        public ShadowWrapperDrawable(Drawable r5) {
            Paint r0 = new Paint(1);
            this.shadowPaint = r0;
            this.shadowPath = new Path();
            this.rectF = new RectF();
            this.outline = new Outline();
            this.pathDirty = true;
            this.inner = r5;
            r5.setCallback(this);
            r0.setColor(0);
            if (Theme.isCurrentThemeDark() == false) goto L6;
            r0.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(4.0f), Theme.multAlpha(-16777216, 0.3f));
            return;
        L6:
            r0.setShadowLayer(AndroidUtilities.dp(12.0f), 0.0f, AndroidUtilities.dp(4.0f), Theme.multAlpha(-16777216, 0.1f));
        }

        private void rebuildPath() {
            this.shadowPath.reset();
            this.rectF.set(getBounds());
            this.inner.getOutline(this.outline);     // Catch: Throwable -> L5
            float r0 = this.outline.getRadius();     // Catch: Throwable -> L5
        L6:
            Path r2 = this.shadowPath;
            if (r0 <= 0.0f) goto L9;
            r2.addRoundRect(this.rectF, r0, r0, Path.Direction.CW);
        L10:
            this.pathDirty = false;
            return;
        L9:
            r2.addRect(this.rectF, Path.Direction.CW);
        L5:
            r0 = -1.0f;
            goto L6
        }

        @Override // android.graphics.drawable.Drawable
        public void draw(Canvas r3) {
            if (this.pathDirty == false) goto L5;
            rebuildPath();
        L5:
            r3.drawPath(this.shadowPath, this.shadowPaint);
            this.inner.draw(r3);
        }

        @Override // android.graphics.drawable.Drawable
        public int getOpacity() {
            return -3;
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void invalidateDrawable(Drawable r1) {
            invalidateSelf();
        }

        @Override // android.graphics.drawable.Drawable
        public boolean isStateful() {
            return this.inner.isStateful();
        }

        @Override // android.graphics.drawable.Drawable
        public void jumpToCurrentState() {
            this.inner.jumpToCurrentState();
        }

        @Override // android.graphics.drawable.Drawable
        public void onBoundsChange(Rect r2) {
            this.inner.setBounds(r2);
            this.pathDirty = true;
        }

        @Override // android.graphics.drawable.Drawable
        public boolean onStateChange(int[] r1) {
            return this.inner.setState(r1);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void scheduleDrawable(Drawable r1, Runnable r2, long r3) {
            scheduleSelf(r2, r3);
        }

        @Override // android.graphics.drawable.Drawable
        public void setAlpha(int r1) {
            this.inner.setAlpha(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setColorFilter(ColorFilter r1) {
            this.inner.setColorFilter(r1);
        }

        @Override // android.graphics.drawable.Drawable
        public void setHotspot(float r1, float r2) {
            this.inner.setHotspot(r1, r2);
        }

        @Override // android.graphics.drawable.Drawable
        public void setHotspotBounds(int r1, int r2, int r3, int r4) {
            this.inner.setHotspotBounds(r1, r2, r3, r4);
        }

        @Override // android.graphics.drawable.Drawable.Callback
        public void unscheduleDrawable(Drawable r1, Runnable r2) {
            unscheduleSelf(r2);
        }
    }

    /* renamed from: $r8$lambda$-KP3CdRtnSJ5qv6iGza2Utzg2w4, reason: not valid java name */
    public static /* synthetic */ void m21656$r8$lambda$KP3CdRtnSJ5qv6iGza2Utzg2w4(RichEditor r0, View r1) {
        r0.lambda$createView$37(r1);
    }

    /* renamed from: $r8$lambda$-l3bpecYm1v9axzuUl4wf1Nkswo, reason: not valid java name */
    public static /* synthetic */ void m21657$r8$lambda$l3bpecYm1v9axzuUl4wf1Nkswo(BottomSheet r0, Runnable r1, View r2) {
        r0.dismiss();
        r1.run();
    }

    /* renamed from: $r8$lambda$-lu2RUJ0sOB3gd1vpm7BDl_qb3k, reason: not valid java name */
    public static /* synthetic */ void m21658$r8$lambda$lu2RUJ0sOB3gd1vpm7BDl_qb3k(RichEditor r0) {
        r0.lambda$updateBottomPanel$41();
    }

    /* renamed from: $r8$lambda$-naNA2Bg3fY-QN2tyXsmL6JYx8E, reason: not valid java name */
    public static /* synthetic */ void m21659$r8$lambda$naNA2Bg3fYQN2tyXsmL6JYx8E(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$9(r1, r2);
    }

    /* renamed from: $r8$lambda$06C1vzY8b0sMeZteiiw9f-VEIww, reason: not valid java name */
    public static /* synthetic */ void m21660$r8$lambda$06C1vzY8b0sMeZteiiw9fVEIww(RichEditor r0) {
        r0.lambda$showConversionSheet$46();
    }

    public static /* synthetic */ void $r8$lambda$06rAyR4xZws4NY26mun1CehjsGU(RichEditor r0, View r1) {
        r0.lambda$createView$1(r1);
    }

    public static /* synthetic */ void $r8$lambda$1C6pgQFx2ydDydBlx3lvJGILMqo(RichEditor r0, View r1) {
        r0.lambda$createView$6(r1);
    }

    /* renamed from: $r8$lambda$2jc-SSqpqzjUvbuz262vo0jHsgU, reason: not valid java name */
    public static /* synthetic */ void m21661$r8$lambda$2jcSSqpqzjUvbuz262vo0jHsgU(RichEditor r0, BlockRow r1) {
        r0.lambda$openLocationPicker$47(r1);
    }

    public static /* synthetic */ void $r8$lambda$5PGctodF0Lu2ylouunngFcQsTzY(RichEditor r0, DialogInterface r1) {
        r0.lambda$onSendLongClick$50(r1);
    }

    /* renamed from: $r8$lambda$73sq-PegBVD0yynoWSBjDwyMfNk, reason: not valid java name */
    public static /* synthetic */ void m21662$r8$lambda$73sqPegBVD0yynoWSBjDwyMfNk(ItemOptions r0) {
        r0.closeSwipeback();
    }

    public static /* synthetic */ void $r8$lambda$9WAk_eVgveKjrTUah0UrGvfoqpI(RichEditor r0, ItemOptions r1) {
        r0.lambda$createView$25(r1);
    }

    public static /* synthetic */ void $r8$lambda$CEY5pttHH01aCILs6fMs8Rg3bpI(RichEditor r0, BlockRow r1, ChatAttachAlert r2, TLRPC.MessageMedia r3, int r4, boolean r5, int r6, long r7) {
        r0.lambda$openLocationPicker$48(r1, r2, r3, r4, r5, r6, r7);
    }

    /* renamed from: $r8$lambda$D56vN1et848W9qM8OuQSNgZ9o-0, reason: not valid java name */
    public static /* synthetic */ void m21663$r8$lambda$D56vN1et848W9qM8OuQSNgZ9o0(RichEditor r0, View r1) {
        r0.lambda$createView$34(r1);
    }

    public static /* synthetic */ void $r8$lambda$DwmF7eyj68kOKcOY4OIA2SQFnV8(RichEditor r0, TL_iv.pageBlockMath r1, String r2) {
        r0.lambda$createView$29(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Exyqc1IezdF4Q4wfyuHBmxtAd5w(RichEditor r0, ValueAnimator r1) {
        r0.lambda$onCustomTransitionAnimation$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$ISkMgEL7xQM4POq_elRNAAbiM40(RichEditor r0) {
        r0.lambda$updateBottomPanel$42();
    }

    public static /* synthetic */ void $r8$lambda$IhjLjYK9yUT0C2aq7yNTxvroBDs(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$IojBzriy1AWgs4hEMAU41MfBTG8(RichEditor r0, View r1) {
        r0.lambda$createView$38(r1);
    }

    /* renamed from: $r8$lambda$JR9rvaYYSAXnN-JJIjHgLQBIpXI, reason: not valid java name */
    public static /* synthetic */ void m21664$r8$lambda$JR9rvaYYSAXnNJJIjHgLQBIpXI(RichEditor r0, ChatAttachAlert r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        r0.lambda$openAttach$44(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$JnMTdtAZb9DmX1GaTQ78hblmT7k(RichEditor r0, ItemOptions r1) {
        r0.lambda$createView$26(r1);
    }

    /* renamed from: $r8$lambda$K1E6i8Qx-QiLwDwwf4JjrCnPNTs, reason: not valid java name */
    public static /* synthetic */ void m21665$r8$lambda$K1E6i8QxQiLwDwwf4JjrCnPNTs(ItemOptions r0, ItemOptions r1) {
        r0.openSwipeback(r1);
    }

    public static /* synthetic */ void $r8$lambda$KCKi1kbJIF5WMg22Xsj7svDR5_k(RichEditor r0, int r1, boolean r2) {
        r0.onKeyboardSizeChanged(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MG8n1tO8Xr5DMXFPt20zxHR_cyk(BottomSheet r0, Runnable r1, View r2) {
        r0.dismiss();
        r1.run();
    }

    public static /* synthetic */ void $r8$lambda$MjN7CxapRdSEhKXkSyyPwfFGB20(RichEditor r0, View r1) {
        r0.lambda$createView$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$N5EA4qwQlvzDTGNAhP3pCzexnnY(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$NFNyp4WBCwDjW4xbmiDxuVB3bdY(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$16(r1);
    }

    /* renamed from: $r8$lambda$NUk4M-4RWnzbn2h4bJvYk6ofZGE, reason: not valid java name */
    public static /* synthetic */ void m21666$r8$lambda$NUk4M4RWnzbn2h4bJvYk6ofZGE(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$15(r1);
    }

    /* renamed from: $r8$lambda$OxAJYcCPLqQRo85-eibpR5SyPhs, reason: not valid java name */
    public static /* synthetic */ void m21667$r8$lambda$OxAJYcCPLqQRo85eibpR5SyPhs(RichEditor r0, View r1) {
        r0.lambda$createView$35(r1);
    }

    public static /* synthetic */ void $r8$lambda$PoVlAOFQKNRiQhnh586SHAXza5E(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$24(r1);
    }

    /* renamed from: $r8$lambda$QI4-FEaZnKCzQeU2HAYHAnVUBiA, reason: not valid java name */
    public static /* synthetic */ void m21668$r8$lambda$QI4FEaZnKCzQeU2HAYHAnVUBiA(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$22(r1);
    }

    public static /* synthetic */ boolean $r8$lambda$RpQiKE9uDBf_WZ7zA8uR0Tlr73Y(RichEditor r0, View r1) {
        return r0.onSendLongClick(r1);
    }

    /* renamed from: $r8$lambda$SA8p5rm50hCEkOmyyv9A_kM6-2I, reason: not valid java name */
    public static /* synthetic */ void m21669$r8$lambda$SA8p5rm50hCEkOmyyv9A_kM62I(RichEditor r0, ChatAttachAlert r1, ArrayList r2, CharSequence r3, boolean r4, int r5, int r6, long r7, boolean r9, long r10) {
        r0.lambda$openAttach$45(r1, r2, r3, r4, r5, r6, r7, r9, r10);
    }

    public static /* synthetic */ void $r8$lambda$SoNyT0p0d4HfB9xRVoA22khxLiU(RichEditor r0) {
        r0.updateSendButtonEnabled();
    }

    public static /* synthetic */ void $r8$lambda$St5hEYJwkbAIrfBoAX86iQgC8Sc(RichEditor r0, View r1) {
        r0.lambda$createView$28(r1);
    }

    public static /* synthetic */ void $r8$lambda$SteGRBZYAB66yWtAir6I16BorO8(RichEditor r0) {
        r0.lambda$updateBottomPanel$40();
    }

    /* renamed from: $r8$lambda$VhD1b9TqkqU-f2d9yuI0KoiajeM, reason: not valid java name */
    public static /* synthetic */ void m21670$r8$lambda$VhD1b9TqkqUf2d9yuI0KoiajeM(RichEditor r0, View r1) {
        r0.lambda$createView$31(r1);
    }

    public static /* synthetic */ void $r8$lambda$W8bKhDgoqspwtotr6WGTIYUKu84(RichEditor r0, View r1) {
        r0.lambda$createView$3(r1);
    }

    /* renamed from: $r8$lambda$Y9Cku7-llI6aylAfo9YwRfVe2Gs, reason: not valid java name */
    public static /* synthetic */ void m21671$r8$lambda$Y9Cku7llI6aylAfo9YwRfVe2Gs(RichEditor r0) {
        r0.lambda$onSendLongClick$53();
    }

    /* renamed from: $r8$lambda$bepP7F4zRVH-kn3_oVlxVe3_Wj0, reason: not valid java name */
    public static /* synthetic */ void m21672$r8$lambda$bepP7F4zRVHkn3_oVlxVe3_Wj0(RichEditor r0, int r1, View r2) {
        r0.lambda$addFormattingButton$43(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$bjZGEzZvMdf11sI6EmxVkx2nNiA(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$23(r1);
    }

    /* renamed from: $r8$lambda$cL-ktpTg2Kfwpnh7aaqf5zgV0q0, reason: not valid java name */
    public static /* synthetic */ void m21673$r8$lambda$cLktpTg2Kfwpnh7aaqf5zgV0q0(RichEditor r0, View r1) {
        r0.lambda$createView$33(r1);
    }

    /* renamed from: $r8$lambda$e4PZoAdQUX-06ZyXQ2MffZWHevQ, reason: not valid java name */
    public static /* synthetic */ void m21674$r8$lambda$e4PZoAdQUX06ZyXQ2MffZWHevQ(RichEditor r0) {
        r0.lambda$onSendLongClick$54();
    }

    /* renamed from: $r8$lambda$eYebX-z6UHd8hPXIlvIY5eo04cw, reason: not valid java name */
    public static /* synthetic */ void m21675$r8$lambda$eYebXz6UHd8hPXIlvIY5eo04cw(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$18(r1);
    }

    public static /* synthetic */ void $r8$lambda$fbZADl7THXRkXMWmDMhYi5MQ1A8(RichEditor r0, TL_iv.RichMessage r1) {
        r0.lambda$createView$4(r1);
    }

    /* renamed from: $r8$lambda$h1NRXvhTAd-h66Fe77j1i9CsnR0, reason: not valid java name */
    public static /* synthetic */ void m21676$r8$lambda$h1NRXvhTAdh66Fe77j1i9CsnR0(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$husKe2tSPVQiUdohEtLDxL_prRY(RichEditor r0, View r1) {
        r0.lambda$createView$36(r1);
    }

    /* renamed from: $r8$lambda$hxo5bCtcGrsv-l3MzgjUNj1zhZU, reason: not valid java name */
    public static /* synthetic */ void m21677$r8$lambda$hxo5bCtcGrsvl3MzgjUNj1zhZU(RichEditor r0, long r1) {
        r0.lambda$onSendLongClick$52(r1);
    }

    public static /* synthetic */ void $r8$lambda$jP9M06hVaGPTWfBdO30q1gj2I70(RichEditor r0, View r1) {
        r0.lambda$createView$30(r1);
    }

    /* renamed from: $r8$lambda$jQgJ_T7oy3ZgXN0ESNMlEApui-0, reason: not valid java name */
    public static /* synthetic */ void m21678$r8$lambda$jQgJ_T7oy3ZgXN0ESNMlEApui0(RichEditor r0, MessageObject r1, ArrayList r2, ArrayList r3, ArrayList r4, ArrayList r5, long r6, MessageObject r8, MessageObject r9, boolean r10, int r11, int r12, SendMessageChatArguments r13, long r14) {
        r0.lambda$sendMessage$49(r1, r2, r3, r4, r5, r6, r8, r9, r10, r11, r12, r13, r14);
    }

    /* renamed from: $r8$lambda$ku9feFFYZdqHzMZZDrAeB-x9_kM, reason: not valid java name */
    public static /* synthetic */ void m21679$r8$lambda$ku9feFFYZdqHzMZZDrAeBx9_kM(RichEditor r0, View r1) {
        r0.lambda$createView$32(r1);
    }

    public static /* synthetic */ void $r8$lambda$nOB851NnfAOGCrFe13L5PKyLpIk(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$21(r1);
    }

    /* renamed from: $r8$lambda$nsnP1MD2pnmPTm8ia8_-xJ5xZfA, reason: not valid java name */
    public static /* synthetic */ void m21680$r8$lambda$nsnP1MD2pnmPTm8ia8_xJ5xZfA(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$8(r1, r2);
    }

    /* renamed from: $r8$lambda$pmnYLWkJDN5b5ZS1sAIz--Ljw3I, reason: not valid java name */
    public static /* synthetic */ void m21681$r8$lambda$pmnYLWkJDN5b5ZS1sAIzLjw3I(RichEditor r0, Context r1, View r2) {
        r0.lambda$createView$20(r1, r2);
    }

    /* renamed from: $r8$lambda$rjgpMYP70c-n7pT6kxW49HwGkm0, reason: not valid java name */
    public static /* synthetic */ void m21682$r8$lambda$rjgpMYP70cn7pT6kxW49HwGkm0(RichEditor r0, BlockRow r1) {
        r0.lambda$createView$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$vjlErgSgo9tWOdJnDxiUO2HuRJ0(RichEditor r0, View r1) {
        r0.lambda$onSendLongClick$51(r1);
    }

    public static /* synthetic */ void $r8$lambda$wO7jajn02qVvLmKoSlhlF1hT11E(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$10(r1, r2);
    }

    /* renamed from: $r8$lambda$wVpvh3RVPA5-b_EyiOixO5jhWUc, reason: not valid java name */
    public static /* synthetic */ void m21683$r8$lambda$wVpvh3RVPA5b_EyiOixO5jhWUc(RichEditor r0, View r1) {
        r0.lambda$createView$27(r1);
    }

    /* renamed from: $r8$lambda$x7c4Hnr2iiFi5MSSWKeOg16V5-g, reason: not valid java name */
    public static /* synthetic */ void m21684$r8$lambda$x7c4Hnr2iiFi5MSSWKeOg16V5g(RichEditor r0, BlockRow r1, ItemOptions r2) {
        r0.lambda$createView$12(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yM7IP27RF0SdDKF53qQ53tkq8JY(RichEditor r0, View r1, View r2) {
        r0.lambda$createView$39(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$yfxOHimkDzyp7isl8l1GdpUL97Y(RichEditor r0, View r1) {
        r0.lambda$createView$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$zt3CErhq0Vh7cE6YEbzvfU8LD7g(RichEditor r0, ValueAnimator r1) {
        r0.lambda$animateEmojiSearch$55(r1);
    }

    /* renamed from: -$$Nest$fgetanimateEnterView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView m21685$$Nest$fgetanimateEnterView(RichEditor r0) {
        return r0.animateEnterView;
    }

    /* renamed from: -$$Nest$fgetanimateEnterViewFrom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m21686$$Nest$fgetanimateEnterViewFrom(RichEditor r0) {
        return r0.animateEnterViewFrom;
    }

    /* renamed from: -$$Nest$fgetanimateEnterViewTo, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m21687$$Nest$fgetanimateEnterViewTo(RichEditor r0) {
        return r0.animateEnterViewTo;
    }

    /* renamed from: -$$Nest$fgetanimateFromRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m21688$$Nest$fgetanimateFromRect(RichEditor r0) {
        return r0.animateFromRect;
    }

    /* renamed from: -$$Nest$fgetanimateInputBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BlurredBackgroundDrawable m21689$$Nest$fgetanimateInputBackground(RichEditor r0) {
        return r0.animateInputBackground;
    }

    /* renamed from: -$$Nest$fgetanimateInputView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatInputViewsContainer m21690$$Nest$fgetanimateInputView(RichEditor r0) {
        return r0.animateInputView;
    }

    /* renamed from: -$$Nest$fgetanimateOpenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m21691$$Nest$fgetanimateOpenProgress(RichEditor r0) {
        return r0.animateOpenProgress;
    }

    /* renamed from: -$$Nest$fgetanimatingOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21692$$Nest$fgetanimatingOpen(RichEditor r0) {
        return r0.animatingOpen;
    }

    /* renamed from: -$$Nest$fgetbottomContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m21693$$Nest$fgetbottomContainer(RichEditor r0) {
        return r0.bottomContainer;
    }

    /* renamed from: -$$Nest$fgetbottomInnerContainer, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m21694$$Nest$fgetbottomInnerContainer(RichEditor r0) {
        return r0.bottomInnerContainer;
    }

    /* renamed from: -$$Nest$fgetbottomInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21695$$Nest$fgetbottomInset(RichEditor r0) {
        return r0.bottomInset;
    }

    /* renamed from: -$$Nest$fgetbottomPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21696$$Nest$fgetbottomPanel(RichEditor r0) {
        return r0.bottomPanel;
    }

    /* renamed from: -$$Nest$fgetbottomPanelType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21697$$Nest$fgetbottomPanelType(RichEditor r0) {
        return r0.bottomPanelType;
    }

    /* renamed from: -$$Nest$fgetchatActivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m21698$$Nest$fgetchatActivity(RichEditor r0) {
        return r0.chatActivity;
    }

    /* renamed from: -$$Nest$fgetcommandSuggestions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichCommandSuggestions m21699$$Nest$fgetcommandSuggestions(RichEditor r0) {
        return r0.commandSuggestions;
    }

    /* renamed from: -$$Nest$fgetemojiPadding, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21700$$Nest$fgetemojiPadding(RichEditor r0) {
        return r0.emojiPadding;
    }

    /* renamed from: -$$Nest$fgetemojiSearchOpened, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21701$$Nest$fgetemojiSearchOpened(RichEditor r0) {
        return r0.emojiSearchOpened;
    }

    /* renamed from: -$$Nest$fgetemojiTargetEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditText m21702$$Nest$fgetemojiTargetEditText(RichEditor r0) {
        return r0.emojiTargetEditText;
    }

    /* renamed from: -$$Nest$fgetemojiView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiView m21703$$Nest$fgetemojiView(RichEditor r0) {
        return r0.emojiView;
    }

    /* renamed from: -$$Nest$fgetemojiViewVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21704$$Nest$fgetemojiViewVisible(RichEditor r0) {
        return r0.emojiViewVisible;
    }

    /* renamed from: -$$Nest$fgetformattingLayout1, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21705$$Nest$fgetformattingLayout1(RichEditor r0) {
        return r0.formattingLayout1;
    }

    /* renamed from: -$$Nest$fgetformattingLayout2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21706$$Nest$fgetformattingLayout2(RichEditor r0) {
        return r0.formattingLayout2;
    }

    /* renamed from: -$$Nest$fgetformattingLayout3, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LinearLayout m21707$$Nest$fgetformattingLayout3(RichEditor r0) {
        return r0.formattingLayout3;
    }

    /* renamed from: -$$Nest$fgetformattingScrollMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21708$$Nest$fgetformattingScrollMaxWidth(RichEditor r0) {
        return r0.formattingScrollMaxWidth;
    }

    /* renamed from: -$$Nest$fgetimeInset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21709$$Nest$fgetimeInset(RichEditor r0) {
        return r0.imeInset;
    }

    /* renamed from: -$$Nest$fgetlistView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditorListView m21710$$Nest$fgetlistView(RichEditor r0) {
        return r0.listView;
    }

    /* renamed from: -$$Nest$fgetmessageSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageSendPreview m21711$$Nest$fgetmessageSendPreview(RichEditor r0) {
        return r0.messageSendPreview;
    }

    /* renamed from: -$$Nest$fgetreorderSavedPanelType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21712$$Nest$fgetreorderSavedPanelType(RichEditor r0) {
        return r0.reorderSavedPanelType;
    }

    /* renamed from: -$$Nest$fgetsendButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView.SendButton m21713$$Nest$fgetsendButton(RichEditor r0) {
        return r0.sendButton;
    }

    /* renamed from: -$$Nest$fgetsendButtonLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21714$$Nest$fgetsendButtonLoading(RichEditor r0) {
        return r0.sendButtonLoading;
    }

    /* renamed from: -$$Nest$fgettempRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m21715$$Nest$fgettempRect(RichEditor r0) {
        return r0.tempRect;
    }

    /* renamed from: -$$Nest$fputanimatingOpen, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21716$$Nest$fputanimatingOpen(RichEditor r0, boolean r1) {
        r0.animatingOpen = r1;
    }

    /* renamed from: -$$Nest$fputcommandSuggestions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21717$$Nest$fputcommandSuggestions(RichEditor r0, RichCommandSuggestions r1) {
        r0.commandSuggestions = r1;
    }

    /* renamed from: -$$Nest$fputcurrentMenuVisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21718$$Nest$fputcurrentMenuVisible(RichEditor r0, ItemOptions r1) {
        r0.currentMenuVisible = r1;
    }

    /* renamed from: -$$Nest$fputemojiSearchOpened, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21719$$Nest$fputemojiSearchOpened(RichEditor r0, boolean r1) {
        r0.emojiSearchOpened = r1;
    }

    /* renamed from: -$$Nest$fputemojiTargetEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21720$$Nest$fputemojiTargetEditText(RichEditor r0, RichEditText r1) {
        r0.emojiTargetEditText = r1;
    }

    /* renamed from: -$$Nest$fputemojiTargetSelection, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21721$$Nest$fputemojiTargetSelection(RichEditor r0, int r1) {
        r0.emojiTargetSelection = r1;
    }

    /* renamed from: -$$Nest$fputformattingScrollMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21722$$Nest$fputformattingScrollMaxWidth(RichEditor r0, int r1) {
        r0.formattingScrollMaxWidth = r1;
    }

    /* renamed from: -$$Nest$fputmessageSendPreview, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21723$$Nest$fputmessageSendPreview(RichEditor r0, MessageSendPreview r1) {
        r0.messageSendPreview = r1;
    }

    /* renamed from: -$$Nest$fputreorderSavedPanelType, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21724$$Nest$fputreorderSavedPanelType(RichEditor r0, int r1) {
        r0.reorderSavedPanelType = r1;
    }

    /* renamed from: -$$Nest$manimateEmojiSearch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21725$$Nest$manimateEmojiSearch(RichEditor r0, boolean r1) {
        r0.animateEmojiSearch(r1);
    }

    /* renamed from: -$$Nest$mhideEmojiPopup, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21726$$Nest$mhideEmojiPopup(RichEditor r0, boolean r1) {
        r0.hideEmojiPopup(r1);
    }

    /* renamed from: -$$Nest$misInScheduleMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21727$$Nest$misInScheduleMode(RichEditor r0) {
        return r0.isInScheduleMode();
    }

    /* renamed from: -$$Nest$misOverTrash, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m21728$$Nest$misOverTrash(RichEditor r0, float r1) {
        return r0.isOverTrash(r1);
    }

    /* renamed from: -$$Nest$mopenAttach, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21729$$Nest$mopenAttach(RichEditor r0, int r1, int r2) {
        r0.openAttach(r1, r2);
    }

    /* renamed from: -$$Nest$mopenLocationPicker, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21730$$Nest$mopenLocationPicker(RichEditor r0, BlockRow r1) {
        r0.openLocationPicker(r1);
    }

    /* renamed from: -$$Nest$mresolveEmojiTarget, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RichEditText m21731$$Nest$mresolveEmojiTarget(RichEditor r0) {
        return r0.resolveEmojiTarget();
    }

    /* renamed from: -$$Nest$mresolveEmojiTargetOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m21732$$Nest$mresolveEmojiTargetOffset(RichEditor r0, RichEditText r1) {
        return r0.resolveEmojiTargetOffset(r1);
    }

    /* renamed from: -$$Nest$msaveDraftWithBulletin, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21733$$Nest$msaveDraftWithBulletin(RichEditor r0) {
        r0.saveDraftWithBulletin();
    }

    /* renamed from: -$$Nest$mscheduleLimitCheck, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21734$$Nest$mscheduleLimitCheck(RichEditor r0) {
        r0.scheduleLimitCheck();
    }

    /* renamed from: -$$Nest$msendMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21735$$Nest$msendMessage(RichEditor r0, boolean r1, int r2, int r3) {
        r0.sendMessage(r1, r2, r3);
    }

    /* renamed from: -$$Nest$msetTrashHovered, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21736$$Nest$msetTrashHovered(RichEditor r0, boolean r1, boolean r2) {
        r0.setTrashHovered(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateBlockButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21737$$Nest$mupdateBlockButtons(RichEditor r0) {
        r0.updateBlockButtons();
    }

    /* renamed from: -$$Nest$mupdateBottomPanel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21738$$Nest$mupdateBottomPanel(RichEditor r0, int r1, boolean r2) {
        r0.updateBottomPanel(r1, r2);
    }

    /* renamed from: -$$Nest$mupdateFormattingButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21739$$Nest$mupdateFormattingButtons(RichEditor r0) {
        r0.updateFormattingButtons();
    }

    /* renamed from: -$$Nest$mupdateHistoryButtons, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21740$$Nest$mupdateHistoryButtons(RichEditor r0) {
        r0.updateHistoryButtons();
    }

    /* renamed from: -$$Nest$mupdateSendButtonLoading, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21741$$Nest$mupdateSendButtonLoading(RichEditor r0) {
        r0.updateSendButtonLoading();
    }

    /* renamed from: -$$Nest$mupdateSendButtonLock, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21742$$Nest$mupdateSendButtonLock(RichEditor r0) {
        r0.updateSendButtonLock();
    }

    public RichEditor(CharSequence r3) {
        this.initialSelectionStart = -1;
        this.initialSelectionEnd = -1;
        this.tempRect = new Rect();
        this.location = new int[2];
        this.animateOpenProgress = 1.0f;
        this.premiumButtons = new ArrayList();
        this.blockButtons = new ArrayList();
        this.formattingScrollMaxWidth = Integer.MAX_VALUE;
        this.formattingButtons = new ArrayList();
        this.reorderSavedPanelType = 0;
        this.bottomPanelType = -1;
        this.limitCheckRunnable = new RichEditor$$ExternalSyntheticLambda0(this);
        this.initialText = r3;
    }

    public RichEditor(String r3, boolean r4) {
        this.initialSelectionStart = -1;
        this.initialSelectionEnd = -1;
        this.tempRect = new Rect();
        this.location = new int[2];
        this.animateOpenProgress = 1.0f;
        this.premiumButtons = new ArrayList();
        this.blockButtons = new ArrayList();
        this.formattingScrollMaxWidth = Integer.MAX_VALUE;
        this.formattingButtons = new ArrayList();
        this.reorderSavedPanelType = 0;
        this.bottomPanelType = -1;
        this.limitCheckRunnable = new RichEditor$$ExternalSyntheticLambda0(this);
        if (r4 == true) goto L6;
        r3 = null;
    L6:
        this.initialHtml = r3;
    }

    public RichEditor(TL_iv.RichMessage r3) {
        this.initialSelectionStart = -1;
        this.initialSelectionEnd = -1;
        this.tempRect = new Rect();
        this.location = new int[2];
        this.animateOpenProgress = 1.0f;
        this.premiumButtons = new ArrayList();
        this.blockButtons = new ArrayList();
        this.formattingScrollMaxWidth = Integer.MAX_VALUE;
        this.formattingButtons = new ArrayList();
        this.reorderSavedPanelType = 0;
        this.bottomPanelType = -1;
        this.limitCheckRunnable = new RichEditor$$ExternalSyntheticLambda0(this);
        this.initialRichMessage = r3;
    }

    private Button addBlockButton(int r2, int r3) {
        return addBlockButton(r2, r3, false);
    }

    private Button addBlockButton(int r9, int r10, boolean r11) {
        Button r0 = new Button(this.blocksLayout.getContext(), r9, getResourceProvider());
        if (r11 == false) goto L5;
        r0.setPremium();
        this.premiumButtons.add(r0);
    L5:
        r0.setTag(Integer.valueOf(r10));
        r0.setContentDescription(blockButtonContentDescription(r10));
        this.blockButtons.add(r0);
        LinearLayout r8 = this.blocksLayout;
        if (r8.getChildCount() != 0) goto L9;
        int r92 = 0;
    L10:
        r8.addView(r0, LayoutHelper.createLinear(38, 38, 16, r92, 0, 0, 0));
        return r0;
    L9:
        r92 = 2;
        goto L10
    }

    private void addFormattingButton(Context r2, int r3, int r4) {
        addFormattingButton(r2, r3, r4, false);
    }

    private void addFormattingButton(Context r9, int r10, int r11, boolean r12) {
        Button r0 = new Button(r9, r10, getResourceProvider());
        if (r12 == false) goto L5;
        r0.setPremium();
        this.premiumButtons.add(r0);
    L5:
        r0.setTag(Integer.valueOf(r11));
        r0.setContentDescription(formattingButtonContentDescription(r11));
        r0.setOnClickListener(new RichEditor$$ExternalSyntheticLambda28(this, r11));
        this.formattingButtons.add(r0);
        LinearLayout r8 = this.formattingPanelLayout;
        if (r8.getChildCount() <= 0) goto L9;
        int r92 = 2;
    L10:
        r8.addView(r0, LayoutHelper.createLinear(38, 38, 16, r92, 0, 0, 0));
        return;
    L9:
        r92 = 0;
        goto L10
    }

    private void animateEmojiSearch(boolean r4) {
        ValueAnimator r0 = this.emojiSearchAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
        this.emojiSearchAnimator = null;
    L5:
        if (r4 == false) goto L7;
        float r42 = 1.0f;
    L8:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{this.emojiSearchProgress, r42});
        this.emojiSearchAnimator = r43;
        r43.addUpdateListener(new RichEditor$$ExternalSyntheticLambda1(this));
        this.emojiSearchAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.emojiSearchAnimator.setDuration(250);
        this.emojiSearchAnimator.start();
        return;
    L7:
        r42 = 0.0f;
        goto L8
    }

    private void applyEmojiPadding() {
        checkUI_listViewPadding();
    }

    private void applyEmojiSearchOffset() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L13;
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        if (r02 == null) goto L14;
        int r1 = Math.round(getEmojiPanelHeight() + ((getExpandedEmojiHeight() - r1) * this.emojiSearchProgress));
        if (r02.height == r1) goto L12;
        r02.height = r1;
        this.emojiView.setLayoutParams(r02);
        return;
    L12:
        return;
    L14:
        return;
    }

    public static String blockButtonContentDescription(int r1) {
        if (r1 == 1) goto L23;
        if (r1 == 2) goto L21;
        if (r1 == 4) goto L19;
        if (r1 == 7) goto L17;
        if (r1 == 9) goto L15;
        return null;
    L15:
        return LocaleController.getString(R.string.AccDescrIVDetails);
    L17:
        return LocaleController.getString(R.string.AccDescrIVFormula);
    L19:
        return LocaleController.getString(R.string.AccDescrIVTable);
    L21:
        return LocaleController.getString(R.string.AccDescrIVListStyle);
    L23:
        return LocaleController.getString(R.string.AccDescrIVTextStyle);
    }

    private void checkUI_listViewPadding() {
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L9;
        FrameLayout.LayoutParams r02 = (FrameLayout.LayoutParams) r0.getLayoutParams();
        if (r02 == null) goto L9;
        int r1 = r02.bottomMargin;
        int r2 = this.bottomInset;
        if (r1 == r2) goto L9;
        r02.bottomMargin = r2;
        this.emojiView.setLayoutParams(r02);
    L9:
        applyEmojiSearchOffset();
        int r03 = Math.max(Math.max(this.emojiPadding, this.bottomInset), this.imeInset);
        this.listView.setPadding(0, AndroidUtilities.dp(60.0f), 0, AndroidUtilities.dp(110.0f) + r03);
        this.listView.setInsets(this.bottomInset, this.imeInset, this.emojiPadding);
        this.bottomContainer.setTranslationY(-r03);
        this.bottomGradient.setTranslationY(r0 + this.bottomInset);
    }

    private void closeEmojiSearch() {
        if (this.emojiSearchOpened == true) goto L5;
        return;
    L5:
        this.emojiSearchOpened = false;
        EmojiView r1 = this.emojiView;
        if (r1 == null) goto L8;
        r1.closeSearch(false);
        this.emojiView.hideSearchKeyboard();
    L8:
        animateEmojiSearch(false);
    }

    private void createEmojiView() {
        if (this.emojiView == null) goto L5;
        return;
    L5:
        ChatActivity r0 = this.chatActivity;
        if (r0 == null) goto L9;
        TLRPC.ChatFull r02 = r0.getCurrentChatInfo();
    L8:
        TLRPC.ChatFull r8 = r02;
        boolean r14 = LocaleUtils.canUseLocalPremiumEmojis(this.currentAccount);
        EmojiView r1 = new EmojiView(this, true, false, false, getContext(), true, r8, this.container, true, getResourceProvider(), false, false, r14);
        this.emojiView = r1;
        r1.allowEmojisForNonPremium(r14, false);
        this.emojiView.setVisibility(8);
        EmojiView r03 = this.emojiView;
        r03.fixBottomTabContainerTranslation = false;
        r03.setDelegate(new AnonymousClass15(this));
        int r15 = this.container.indexOfChild(this.bottomPanel);
        if (r15 >= 0) goto L13;
        r15 = this.container.getChildCount();
    L13:
        FrameLayout.LayoutParams r04 = LayoutHelper.createFrame(-1, getEmojiPanelHeight(), 87);
        r04.bottomMargin = this.bottomInset;
        this.container.addView(this.emojiView, r15, r04);
        return;
    L9:
        r02 = null;
        goto L8
    }

    public static String formattingButtonContentDescription(int r1) {
        if (r1 != 1) goto L7;
        return LocaleController.getString(R.string.Bold);
    L7:
        if (r1 != 2) goto L11;
        return LocaleController.getString(R.string.Italic);
    L11:
        if (r1 != 16) goto L15;
        return LocaleController.getString(R.string.Underline);
    L15:
        if (r1 != 8) goto L19;
        return LocaleController.getString(R.string.Strike);
    L19:
        if (r1 != 256) goto L23;
        return LocaleController.getString(R.string.Spoiler);
    L23:
        if (r1 != 4) goto L27;
        return LocaleController.getString(R.string.Mono);
    L27:
        if (r1 != 65536) goto L31;
        return LocaleController.getString(R.string.Highlight);
    L31:
        if (r1 != 16384) goto L35;
        return LocaleController.getString(R.string.Subscript);
    L35:
        if (r1 == 32768) goto L37;
        return null;
    L37:
        return LocaleController.getString(R.string.Superscript);
    }

    private int getEmojiPanelHeight() {
        Point r0 = AndroidUtilities.displaySize;
        if (r0.x <= r0.y) goto L5;
        int r2 = this.keyboardHeightLand;
    L6:
        if (r2 <= 0) goto L8;
        return r2;
    L8:
        return AndroidUtilities.dp(200.0f);
    L5:
        r2 = this.keyboardHeight;
        goto L6
    }

    private int getExpandedEmojiHeight() {
        SizeNotifierFrameLayout r0 = this.container;
        if (r0 == null) goto L5;
        int r02 = ((r0.getMeasuredHeight() - this.container.getPaddingTop()) - AndroidUtilities.dp(240.0f)) - this.bottomInset;
        return Math.max(getEmojiPanelHeight(), r02);
    L5:
        return getEmojiPanelHeight();
    }

    private void hideEmojiPopup(boolean r4) {
        if (this.emojiSearchOpened == false) goto L7;
        this.emojiSearchOpened = false;
        EmojiView r0 = this.emojiView;
        if (r0 == null) goto L7;
        r0.closeSearch(false);
        this.emojiView.hideSearchKeyboard();
    L7:
        ValueAnimator r02 = this.emojiSearchAnimator;
        if (r02 == null) goto L10;
        r02.cancel();
        this.emojiSearchAnimator = null;
    L10:
        this.emojiSearchProgress = 0.0f;
        this.emojiTargetEditText = null;
        EmojiView r2 = this.emojiView;
        if (r2 == null) goto L14;
        r2.setTranslationY(0.0f);
        this.emojiView.setVisibility(8);
    L14:
        if (this.emojiViewVisible == false) goto L16;
    L17:
        this.emojiViewVisible = false;
        this.emojiPadding = 0;
        applyEmojiPadding();
    L18:
        ChatActivityEnterViewAnimatedIconView r3 = this.emojiButton;
        if (r3 == null) goto L22;
        r3.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, r4);
        return;
    L22:
        return;
    L16:
        if (this.emojiPadding == 0) goto L18;
        goto L17
    }

    private boolean isInScheduleMode() {
        if (this.editingMessageObject != null) goto L10;
        ChatActivity r1 = this.chatActivity;
        if (r1 != null) goto L7;
        return false;
    L7:
        if (r1.isInScheduleMode() == false) goto L13;
        return true;
    L13:
        return false;
    L10:
        return false;
    }

    private boolean isOverTrash(float r3) {
        FrameLayout r2 = this.trashPanel;
        if (r2 != null) goto L5;
        return false;
    L5:
        r2.getLocationOnScreen(new int[2]);
        if (r3 < r1[1]) goto L8;
        return true;
    L8:
        return false;
    }

    private boolean isSendLocked() {
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L5;
        return false;
    L5:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == false) goto L7;
        return false;
    L7:
        if (this.listView.isLossy() == false) goto L13;
        return true;
    L13:
        return false;
    }

    private /* synthetic */ void lambda$addFormattingButton$43(int r1, View r2) {
        this.listView.onFormattingClicked(r1);
    }

    private /* synthetic */ void lambda$animateEmojiSearch$55(ValueAnimator r1) {
        this.emojiSearchProgress = ((Float) r1.getAnimatedValue()).floatValue();
        applyEmojiSearchOffset();
    }

    private /* synthetic */ void lambda$createView$1(View r1) {
        if (this.listView.deselectIfAny() == true) goto L6;
        finishFragment();
        return;
    }

    private /* synthetic */ void lambda$createView$10(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading3());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$createView$11(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading4());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$createView$12(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading5());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$createView$13(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading6());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$createView$15(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockParagraph());
    }

    private /* synthetic */ void lambda$createView$16(BlockRow r8) {
        this.listView.turnInto(r8, RichEditorListView.newBlockquote(), 0, 0, false, false);
    }

    private /* synthetic */ void lambda$createView$17(BlockRow r8) {
        this.listView.turnInto(r8, RichEditorListView.newPullquote(), 0, 0, false, false);
    }

    private /* synthetic */ void lambda$createView$18(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockPreformatted());
    }

    private /* synthetic */ void lambda$createView$19(BlockRow r2) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockFooter());
    }

    private /* synthetic */ void lambda$createView$2(View r1) {
        this.listView.undo();
    }

    private /* synthetic */ void lambda$createView$20(Context r12, View r13) {
        ItemOptions r0 = this.currentMenuVisible;
        if (r0 == null) goto L5;
        r0.dismiss();
        this.currentMenuVisible = null;
    L5:
        boolean r2 = true;
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L8;
    L10:
        boolean r02 = false;
    L11:
        BlockRow r3 = this.listView.findFocusedRow();
        ItemOptions r132 = ItemOptions.makeOptions(this, r13, true).dontFocus();
        ItemOptions r4 = r132.makeSwipeback();
        r4.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RichEditor$$ExternalSyntheticLambda29(r132));
        r4.addGap();
        if (r3 != null) goto L14;
    L16:
        boolean r5 = false;
    L17:
        r4.addChecked(r5, R.drawable.iv_h1, LocaleController.getString(R.string.ArticleHeading1), new RichEditor$$ExternalSyntheticLambda33(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize + 2);
        if (r3 != null) goto L20;
    L22:
        boolean r52 = false;
    L23:
        r4.addChecked(r52, R.drawable.iv_h2, LocaleController.getString(R.string.ArticleHeading2), new RichEditor$$ExternalSyntheticLambda34(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize + 1);
        if (r3 != null) goto L26;
    L28:
        boolean r53 = false;
    L29:
        r4.addChecked(r53, R.drawable.iv_h3, LocaleController.getString(R.string.ArticleHeading3), new RichEditor$$ExternalSyntheticLambda35(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize);
        if (r3 != null) goto L32;
    L34:
        boolean r54 = false;
    L35:
        r4.addChecked(r54, R.drawable.iv_h4, LocaleController.getString(R.string.ArticleHeading4), new RichEditor$$ExternalSyntheticLambda36(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize - 1);
        if (r3 != null) goto L38;
    L40:
        boolean r55 = false;
    L41:
        r4.addChecked(r55, R.drawable.iv_h5, LocaleController.getString(R.string.ArticleHeading5), new RichEditor$$ExternalSyntheticLambda37(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize - 2);
        if (r3 != null) goto L44;
    L46:
        boolean r56 = false;
    L47:
        r4.addChecked(r56, R.drawable.iv_h6, LocaleController.getString(R.string.ArticleHeading6), new RichEditor$$ExternalSyntheticLambda38(this, r3, r132));
        r4.getLast().textView.setTypeface(AndroidUtilities.bold());
        r4.getLast().textView.setTextSize(1, SharedConfig.fontSize - 3);
        if (r3 != null) goto L50;
    L52:
        boolean r57 = false;
    L53:
        RequiresPremiumDrawable r6 = new RequiresPremiumDrawable(r12, R.drawable.iv_h).setPremium(r02);
        int r7 = Theme.key_actionBarDefaultSubmenuBackground;
        r132.addChecked(r57, r6.setCutoutColorKey(r7), LocaleController.getString(R.string.ArticleHeading), new RichEditor$$ExternalSyntheticLambda39(r132, r4));
        r132.getLast().textView.setTypeface(AndroidUtilities.bold());
        ActionBarMenuSubItem r42 = r132.getLast();
        float r62 = 9.0f;
        if (LocaleController.isRTL == false) goto L56;
        float r58 = 18.0f;
    L57:
        int r59 = AndroidUtilities.dp(r58);
        if (LocaleController.isRTL == false) goto L60;
        float r9 = 9.0f;
    L61:
        r42.setPadding(r59, 0, AndroidUtilities.dp(r9), 0);
        if (r3 != null) goto L64;
    L66:
        boolean r43 = false;
    L67:
        r132.addChecked(r43, R.drawable.iv_text2, LocaleController.getString(R.string.ArticleText), new RichEditor$$ExternalSyntheticLambda40(this, r3));
        if (r3 != null) goto L70;
    L72:
        boolean r44 = false;
    L73:
        r132.addChecked(r44, R.drawable.iv_quote, LocaleController.getString(R.string.ArticleQuote), new RichEditor$$ExternalSyntheticLambda41(this, r3));
        if (r3 != null) goto L76;
    L78:
        boolean r45 = false;
    L79:
        r132.addChecked(r45, new RequiresPremiumDrawable(r12, R.drawable.iv_pullquote).setPremium(r02).setCutoutColorKey(r7), LocaleController.getString(R.string.ArticlePullquote), new RichEditor$$ExternalSyntheticLambda30(this, r3));
        ActionBarMenuSubItem r46 = r132.getLast();
        if (LocaleController.isRTL == false) goto L82;
        float r510 = 18.0f;
    L83:
        int r511 = AndroidUtilities.dp(r510);
        if (LocaleController.isRTL == false) goto L86;
        float r92 = 9.0f;
    L87:
        r46.setPadding(r511, 0, AndroidUtilities.dp(r92), 0);
        if (r3 != null) goto L90;
    L92:
        boolean r47 = false;
    L93:
        r132.addChecked(r47, R.drawable.iv_code, LocaleController.getString(R.string.ArticleCode), new RichEditor$$ExternalSyntheticLambda31(this, r3));
        if (r3 != null) goto L96;
    L98:
        r2 = false;
    L99:
        r132.addChecked(r2, new RequiresPremiumDrawable(r12, R.drawable.iv_footer).setPremium(r02).setCutoutColorKey(r7), LocaleController.getString(R.string.ArticleFooter), new RichEditor$$ExternalSyntheticLambda32(this, r3));
        ActionBarMenuSubItem r122 = r132.getLast();
        if (LocaleController.isRTL == false) goto L102;
        float r03 = 18.0f;
    L103:
        int r04 = AndroidUtilities.dp(r03);
        if (LocaleController.isRTL == true) goto L107;
        r62 = 18.0f;
    L107:
        r122.setPadding(r04, 0, AndroidUtilities.dp(r62), 0);
        this.currentMenuVisible = r132.show();
        return;
    L102:
        r03 = 9.0f;
        goto L103
    L96:
        if ((r3.block instanceof TL_iv.pageBlockFooter) == false) goto L98;
    L90:
        if ((r3.block instanceof TL_iv.pageBlockPreformatted) == false) goto L92;
        r47 = true;
        goto L93
    L86:
        r92 = 18.0f;
        goto L87
    L82:
        r510 = 9.0f;
        goto L83
    L76:
        if ((r3.block instanceof TL_iv.pageBlockPullquote) == false) goto L78;
        r45 = true;
        goto L79
    L70:
        if ((r3.block instanceof TL_iv.pageBlockBlockquote) == false) goto L72;
        r44 = true;
        goto L73
    L64:
        if ((r3.block instanceof TL_iv.pageBlockParagraph) == false) goto L66;
        r43 = true;
        goto L67
    L60:
        r9 = 18.0f;
        goto L61
    L56:
        r58 = 9.0f;
        goto L57
    L50:
        if (RichEditorListView.isHeading(r3.block) == false) goto L52;
        r57 = true;
        goto L53
    L44:
        if ((r3.block instanceof TL_iv.pageBlockHeading6) == false) goto L46;
        r56 = true;
        goto L47
    L38:
        if ((r3.block instanceof TL_iv.pageBlockHeading5) == false) goto L40;
        r55 = true;
        goto L41
    L32:
        if ((r3.block instanceof TL_iv.pageBlockHeading4) == false) goto L34;
        r54 = true;
        goto L35
    L26:
        if ((r3.block instanceof TL_iv.pageBlockHeading3) == false) goto L28;
        r53 = true;
        goto L29
    L20:
        if ((r3.block instanceof TL_iv.pageBlockHeading2) == false) goto L22;
        r52 = true;
        goto L23
    L14:
        if ((r3.block instanceof TL_iv.pageBlockHeading1) == false) goto L16;
        r5 = true;
        goto L17
    L8:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L10;
        r02 = true;
        goto L11
    }

    private /* synthetic */ void lambda$createView$21(BlockRow r2) {
        this.listView.turnIntoList(r2, 0);
    }

    private /* synthetic */ void lambda$createView$22(BlockRow r2) {
        this.listView.turnIntoList(r2, 1);
    }

    private /* synthetic */ void lambda$createView$23(BlockRow r2) {
        this.listView.turnIntoList(r2, 2);
    }

    private /* synthetic */ void lambda$createView$24(BlockRow r2) {
        this.listView.turnIntoList(r2, 3);
    }

    private /* synthetic */ void lambda$createView$25(ItemOptions r2) {
        this.listView.indentSelection(false);
        r2.dismiss();
    }

    private /* synthetic */ void lambda$createView$26(ItemOptions r2) {
        this.listView.indentSelection(true);
        r2.dismiss();
    }

    private /* synthetic */ void lambda$createView$27(View r9) {
        ItemOptions r0 = this.currentMenuVisible;
        if (r0 == null) goto L5;
        r0.dismiss();
        this.currentMenuVisible = null;
    L5:
        ItemOptions r92 = ItemOptions.makeOptions(this, r9).dontFocus();
        BlockRow r02 = this.listView.findFocusedRow();
        boolean r1 = false;
        if (r02 != null) goto L8;
    L11:
        boolean r3 = true;
    L12:
        ItemOptions r32 = r92.addChecked(r3, R.drawable.field_carret_empty, LocaleController.getString(R.string.ArticleNone), new RichEditor$$ExternalSyntheticLambda51(this, r02));
        if (r02 != null) goto L15;
    L21:
        boolean r4 = false;
    L22:
        ItemOptions r33 = r32.addChecked(r4, R.drawable.iv_list, LocaleController.getString(R.string.ArticleListBulletedList), new RichEditor$$ExternalSyntheticLambda52(this, r02));
        if (r02 != null) goto L25;
    L31:
        boolean r42 = false;
    L32:
        ItemOptions r34 = r33.addChecked(r42, R.drawable.iv_ordered_list, LocaleController.getString(R.string.ArticleListNumberedList), new RichEditor$$ExternalSyntheticLambda53(this, r02));
        if (r02 != null) goto L35;
    L41:
        boolean r43 = false;
    L42:
        ItemOptions r35 = r34.addChecked(r43, R.drawable.iv_todo, LocaleController.getString(R.string.ArticleListChecklist), new RichEditor$$ExternalSyntheticLambda54(this, r02));
        if (r02 != null) goto L45;
    L47:
        int r03 = R.drawable.iv_details;
        String r44 = LocaleController.getString(R.string.ArticleToggleBlock);
        RichEditorListView r5 = this.listView;
        Objects.requireNonNull(r5);
        r35.addChecked(r1, r03, r44, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda30(r5));
        boolean r04 = this.listView.canIndentSelection();
        boolean r12 = this.listView.canOutdentSelection();
        if (r04 == true) goto L50;
        if (r12 == true) goto L50;
    L55:
        this.currentMenuVisible = r92.forceTop(true).show();
        return;
    L50:
        r92.addGap();
        if (r04 == false) goto L53;
        r92.add(R.drawable.iv_list_tab, LocaleController.getString(R.string.ArticleIndent), new RichEditor$$ExternalSyntheticLambda55(this, r92));
    L53:
        if (r12 == false) goto L55;
        r92.add(R.drawable.iv_list_untab, LocaleController.getString(R.string.ArticleOutdent), new RichEditor$$ExternalSyntheticLambda56(this, r92));
        goto L55
    L45:
        if ((r02.block instanceof TL_iv.pageBlockDetails) == false) goto L47;
        r1 = true;
        goto L47
    L35:
        if (r02.isInList() == false) goto L41;
        if (r02.isChecklist() == false) goto L41;
        if (r02.isOrdered() == true) goto L41;
        r43 = true;
        goto L42
    L25:
        if (r02.isInList() == false) goto L31;
        if (r02.isChecklist() == true) goto L31;
        if (r02.isOrdered() == false) goto L31;
        r42 = true;
        goto L32
    L15:
        if (r02.isInList() == false) goto L21;
        if (r02.isChecklist() == true) goto L21;
        if (r02.isOrdered() == true) goto L21;
        r4 = true;
        goto L22
    L8:
        if (r02.isInList() == false) goto L11;
        r3 = false;
        goto L12
    }

    private /* synthetic */ void lambda$createView$28(View r3) {
        ItemOptions r32 = this.currentMenuVisible;
        if (r32 == null) goto L5;
        r32.dismiss();
        this.currentMenuVisible = null;
    L5:
        RichEditorListView r33 = this.listView;
        RichTableCell r0 = r33.activeCellSelectionTable;
        if (r0 != null) goto L14;
        RichTableCell r34 = r33.findFocusedTableCell();
        if (r34 == null) goto L14;
        if (r34.getModel() == null) goto L14;
        TL_iv.pageTableCell r1 = this.listView.focusedCellOf(r34);
        if (r1 == null) goto L14;
        this.listView.enterCellSelectionMode(r34, r1);
        r0 = r34;
    L14:
        if (r0 != null) goto L16;
    L21:
        this.listView.addBlock(RichTextCell.newEmptyTable(2, 2));
        return;
    L16:
        if (r0.getModel() == null) goto L21;
        if (r0.hasCellSelection() == false) goto L21;
        this.listView.showTableCellMenu(r0);
    }

    private /* synthetic */ void lambda$createView$29(TL_iv.pageBlockMath r1, String r2) {
        if (r1 == null) goto L5;
        r1.source = r2;
        this.listView.adapter.update(false);
        return;
    L5:
        TL_iv.pageBlockMath r12 = new TL_iv.pageBlockMath();
        r12.source = r2;
        this.listView.addBlock(r12);
    }

    private /* synthetic */ void lambda$createView$3(View r1) {
        this.listView.redo();
    }

    private /* synthetic */ void lambda$createView$30(View r4) {
        ItemOptions r42 = this.currentMenuVisible;
        TL_iv.pageBlockMath r0 = null;
        if (r42 == null) goto L5;
        r42.dismiss();
        this.currentMenuVisible = null;
    L5:
        BlockRow r43 = this.listView.findFocusedRow();
        if (r43 == null) goto L10;
        TL_iv.PageBlock r44 = r43.block;
        if ((r44 instanceof TL_iv.pageBlockMath) == false) goto L10;
        r0 = (TL_iv.pageBlockMath) r44;
    L10:
        Context r45 = getContext();
        if (r0 != null) goto L13;
    L16:
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        ChatAttachAlertRichLayout.showEditLatexSheet(r45, r1, new RichEditor$$ExternalSyntheticLambda49(this, r0), getResourceProvider());
        return;
    L13:
        if (TextUtils.isEmpty(r0.source) == true) goto L16;
        r1 = r0.source;
        goto L17
    }

    private /* synthetic */ void lambda$createView$31(View r2) {
        this.listView.pendingMediaRow = null;
        openAttach();
    }

    private /* synthetic */ void lambda$createView$32(View r1) {
        this.listView.toggleQuoteOnSelection();
        updateFormattingButtons();
    }

    private /* synthetic */ void lambda$createView$33(View r1) {
        this.listView.onInlineButtonClicked(r1);
    }

    private /* synthetic */ void lambda$createView$34(View r1) {
        this.listView.onLinkClicked();
    }

    private /* synthetic */ void lambda$createView$35(View r1) {
        this.listView.onDateClicked();
    }

    private /* synthetic */ void lambda$createView$36(View r1) {
        this.listView.onMathClicked();
    }

    private /* synthetic */ void lambda$createView$37(View r1) {
        onAiStyleSelection();
    }

    private /* synthetic */ void lambda$createView$38(View r1) {
        sendMessage();
    }

    private /* synthetic */ void lambda$createView$39(View r1, View r2) {
        updateBlockButtons();
    }

    private /* synthetic */ void lambda$createView$4(TL_iv.RichMessage r1) {
        this.listView.addRichMessage(r1);
    }

    private /* synthetic */ void lambda$createView$5(View r5) {
        if (this.listView.isInSelectionMode() == false) goto L6;
        onAiStyleSelection();
        return;
    L6:
        new RichAIComposeSheet(getContext(), this.currentAccount, getResourceProvider(), new RichEditor$$ExternalSyntheticLambda50(this)).show();
    }

    private /* synthetic */ void lambda$createView$6(View r1) {
        toggleEmojiPopup();
    }

    private /* synthetic */ void lambda$createView$8(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading1());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$createView$9(BlockRow r2, ItemOptions r3) {
        this.listView.turnIntoKeepList(r2, new TL_iv.pageBlockHeading2());
        r3.dismiss();
    }

    private /* synthetic */ void lambda$onCustomTransitionAnimation$0(ValueAnimator r5) {
        this.animateOpenProgress = ((Float) r5.getAnimatedValue()).floatValue();
        updateAnimatingLocations();
        this.listView.setTranslationX(AndroidUtilities.lerp(this.animateEnterViewFrom[0] - this.animateEnterViewTo[0], 0, this.animateOpenProgress));
        this.listView.setTranslationY(AndroidUtilities.lerp(this.animateEnterViewFrom[1] - this.animateEnterViewTo[1], 0, this.animateOpenProgress));
        this.container.invalidate();
    }

    private /* synthetic */ void lambda$onSendLongClick$50(DialogInterface r1) {
        this.messageSendPreview = null;
    }

    private /* synthetic */ void lambda$onSendLongClick$51(View r2) {
        sendMessage();
        MessageSendPreview r22 = this.messageSendPreview;
        if (r22 == null) goto L6;
        r22.dismiss(true);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$onSendLongClick$52(long r3) {
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), r3, new AnonymousClass14(this), getResourceProvider());
    }

    private /* synthetic */ void lambda$onSendLongClick$53() {
        sendMessage(true, 2147483646, 0);
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L6;
        r0.dismiss(false);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$onSendLongClick$54() {
        sendMessage(false, 0, 0);
        MessageSendPreview r0 = this.messageSendPreview;
        if (r0 == null) goto L6;
        r0.dismiss(true);
        this.messageSendPreview = null;
        return;
    }

    private /* synthetic */ void lambda$openAttach$44(ChatAttachAlert r1, TLRPC.MessageMedia r2, int r3, boolean r4, int r5, long r6) {
        if (r2 != null) goto L5;
    L9:
        r1.dismiss(true);
        return;
    L5:
        if (r2.geo == null) goto L9;
        TL_iv.pageBlockMap r42 = new TL_iv.pageBlockMap();
        r42.geo = r2.geo;
        r42.zoom = 15;
        r42.w = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        r42.h = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
        this.listView.addBlock(r42);
        r1.dismiss(true);
    }

    private /* synthetic */ void lambda$openAttach$45(ChatAttachAlert r1, ArrayList r2, CharSequence r3, boolean r4, int r5, int r6, long r7, boolean r9, long r10) {
        if (r2 != null) goto L4;
    L6:
        r1.dismiss(true);
        return;
    L4:
        if (r2.isEmpty() == true) goto L6;
        this.listView.attachAudio((MessageObject) r2.get(0));
        goto L6
    }

    private /* synthetic */ void lambda$openLocationPicker$47(BlockRow r3) {
        View r0 = this.listView.findViewByItemObject(r3);
        boolean r1 = r0 instanceof RichMapCell;
        RichEditorListView r2 = this.listView;
        if (r1 == false) goto L6;
        ((RichMapCell) r0).bind(r3, r2.getMapDelegate());
        return;
    L6:
        r2.adapter.update(false);
    }

    private /* synthetic */ void lambda$openLocationPicker$48(BlockRow r1, ChatAttachAlert r2, TLRPC.MessageMedia r3, int r4, boolean r5, int r6, long r7) {
        if (r3 != null) goto L4;
        return;
    L4:
        if (r3.geo == null) goto L20;
        RichEditorHistory r42 = this.listView.history;
        if (r42 == null) goto L9;
        r42.flush();
    L9:
        TL_iv.pageBlockMap r43 = (TL_iv.pageBlockMap) r1.block;
        r43.geo = r3.geo;
        r43.zoom = 15;
        if (r43.w > 0) goto L12;
    L13:
        r43.w = MediaError.DetailedErrorCode.TEXT_UNKNOWN;
        r43.h = MediaError.DetailedErrorCode.MANIFEST_UNKNOWN;
    L14:
        RichEditorHistory r32 = this.listView.history;
        if (r32 == null) goto L17;
        r32.record();
    L17:
        r2.dismiss(true);
        this.listView.post(new RichEditor$$ExternalSyntheticLambda60(this, r1));
        return;
    L12:
        if (r43.h > 0) goto L14;
    }

    private /* synthetic */ void lambda$sendMessage$49(MessageObject r24, ArrayList r25, ArrayList r26, ArrayList r27, ArrayList r28, long r29, MessageObject r31, MessageObject r32, boolean r33, int r34, int r35, SendMessageChatArguments r36, long r37) {
        int r1 = this.currentAccount;
        if (r24 == null) goto L6;
        SendMessagesHelper.prepareEditingArticle(AccountInstance.getInstance(r1), r24, r25, r26, r27, r28, false, this.chatActivity);
        return;
    L6:
        SendMessagesHelper.prepareSendingArticle(AccountInstance.getInstance(r1), r25, r26, r27, r28, false, r29, r31, r32, r33, r34, r35, r36, 0, r37, 0);
    }

    private /* synthetic */ void lambda$showConversionSheet$46() {
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L6;
        showDialog(new PremiumFeatureBottomSheet(this, 43, true));
        return;
    }

    private /* synthetic */ void lambda$updateBottomPanel$40() {
        if (this.bottomPanelType == 0) goto L6;
        this.bottomPanel.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateBottomPanel$41() {
        if (this.bottomPanelType == 1) goto L6;
        this.formattingPanel.setVisibility(8);
        return;
    }

    private /* synthetic */ void lambda$updateBottomPanel$42() {
        if (this.bottomPanelType == 2) goto L6;
        this.trashPanel.setVisibility(8);
        return;
    }

    private void onAiStyleSelection() {
        RichEditorListView.SelectionEdit r0 = this.listView.beginSelectionEdit();
        if (r0 == null) goto L13;
        TL_iv.RichMessage r1 = r0.extractRichMessage();
        if (r1 != null) goto L8;
        return;
    L8:
        if (r1.blocks.isEmpty() == true) goto L14;
        new AIEditorAlert(getContext(), getResourceProvider()).setText(r1).setOnUseRich(new ChatAttachAlertRichLayout$2$$ExternalSyntheticLambda0(r0)).show();
        return;
    L14:
        return;
    }

    private void onKeyboardSizeChanged(int r1, boolean r2) {
    }

    private boolean onSendLongClick(View r12) {
        ChatActivity r0 = this.chatActivity;
        if (r0 != null) goto L5;
    L55:
        return false;
    L5:
        if (this.editingMessageObject != null) goto L55;
        if (r0.isInScheduleMode() == false) goto L11;
        return false;
    L11:
        if (this.listView.hasAnyText() == true) goto L14;
        return false;
    L14:
        if (this.listView.hasPendingUploads() == false) goto L17;
        return false;
    L17:
        if (this.listView.isWithinLimits() == true) goto L20;
        updateSendButtonEnabled();
        return false;
    L20:
        ArrayList<TL_iv.PageBlock> r02 = this.listView.flattenRowsToBlocks();
        if (r02.isEmpty() == false) goto L23;
        return false;
    L23:
        MessageSendPreview r2 = this.messageSendPreview;
        if (r2 == null) goto L26;
        r2.dismiss(false);
        this.messageSendPreview = null;
    L26:
        MessageSendPreview r22 = new MessageSendPreview(getContext(), getResourceProvider());
        this.messageSendPreview = r22;
        r22.setOnDismissListener(new RichEditor$$ExternalSyntheticLambda44(this));
        long r4 = this.chatActivity.getDialogId();
        MessageObject r23 = this.chatActivity.getReplyMessage();
        TLRPC.TL_message r6 = new TLRPC.TL_message();
        r6.id = 0;
        r6.out = true;
        r6.peer_id = MessagesController.getInstance(this.currentAccount).getPeer(r4);
        r6.from_id = MessagesController.getInstance(this.currentAccount).getPeer(UserConfig.getInstance(this.currentAccount).getClientUserId());
        r6.flags2 |= 8192;
        TL_iv.RichMessage r8 = new TL_iv.RichMessage();
        r6.rich_message = r8;
        r8.blocks = r02;
        r8.photos = this.listView.collectPhotos();
        r6.rich_message.documents = this.listView.collectDocuments();
        if (r23 != null) goto L29;
    L31:
        MessageObject r03 = new MessageObject(this.currentAccount, r6, false, false);
        if (r23 != null) goto L34;
    L36:
        r03.sendPreview = true;
        r03.isOutOwnerCached = Boolean.TRUE;
        r03.generateLayout(null);
        r03.notime = true;
        ArrayList<MessageObject> r1 = new ArrayList();
        r1.add(r03);
        this.messageSendPreview.setMessageObjects(r1);
        this.sendButton.setScaleX(1.0f);
        this.sendButton.setScaleY(1.0f);
        ChatActivityEnterView.SendButton r04 = this.messageSendPreview.setSendButton(this.sendButton, true, new RichEditor$$ExternalSyntheticLambda45(this));
        if (r04 == null) goto L39;
        r04.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(Theme.key_featuredStickers_addButton))));
        this.messageSendPreview.setSendButtonWidth(AndroidUtilities.dp(44.0f));
    L39:
        ItemOptions r05 = ItemOptions.makeOptions(this, this.sendButton);
        boolean r13 = UserObject.isUserSelf(this.chatActivity.getCurrentUser());
        if (this.chatActivity.canScheduleMessage() == false) goto L50;
        int r24 = R.drawable.msg_calendar2;
        if (r13 == false) goto L44;
        int r3 = R.string.SetReminder;
    L45:
        r05.add(r24, LocaleController.getString(r3), new RichEditor$$ExternalSyntheticLambda46(this, r4));
        if (r13 == true) goto L50;
        if (r4 <= 0) goto L50;
        r05.add(R.drawable.msg_online, LocaleController.getString(R.string.SendWhenOnline), new RichEditor$$ExternalSyntheticLambda47(this));
        goto L50
    L44:
        r3 = R.string.ScheduleMessage;
    L50:
        if (r13 == true) goto L52;
        r05.add(R.drawable.input_notify_off, LocaleController.getString(R.string.SendWithoutSound), new RichEditor$$ExternalSyntheticLambda48(this));
    L52:
        r05.setupSelectors();
        this.messageSendPreview.setItemOptions(r05);
        this.messageSendPreview.show();
        r12.performHapticFeedback(3, 2);     // Catch: Exception -> L56
    L54:
        return true;
    L34:
        if (r23.isTopicMainMessage == true) goto L36;
        r03.replyMessageObject = r23;
        goto L36
    L29:
        if (r23.isTopicMainMessage == true) goto L31;
        TLRPC.TL_messageReplyHeader r06 = new TLRPC.TL_messageReplyHeader();
        r06.flags |= 16;
        r06.reply_to_msg_id = r23.getId();
        r6.reply_to = r06;
        goto L31
    }

    private void openAttach() {
        openAttach(90, 0);
    }

    private void openAttach(int r10, int r11) {
        RichEditorListView r0 = this.listView;
        r0.pendingInsertRow = r0.findFocusedRow();
        ChatAttachAlert r2 = new ChatAttachAlert(getContext(), this, false, false, true, getResourceProvider());
        r2.setDelegate(new AnonymousClass10(this, r2));
        r2.getPhotoLayout().setIncludeVideosInGallery(true);
        r2.getPhotoLayout().loadGalleryPhotos();
        r2.setMaxSelectedPhotos(1, true);
        r2.enablePollAttachMode(r10);
        r2.setLocationActivityDelegate(new RichEditor$$ExternalSyntheticLambda57(this, r2));
        r2.setAudioSelectDelegate(new RichEditor$$ExternalSyntheticLambda58(this, r2));
        r2.setDocumentsDelegate(new AnonymousClass11(this, r2));
        r2.init();
        if (r11 == 0) goto L5;
        r2.openAttachLayoutForType(r11);
    L5:
        r2.setFocusable(true);
        r2.show();
    }

    public static BottomSheet openConversionSheet(Context r17, Runnable r18, Runnable r19, Theme.ResourcesProvider r20) {
        BottomSheet.Builder r2 = new BottomSheet.Builder(r17, false, r20);
        LinearLayout r4 = new LinearLayout(r17);
        r4.setOrientation(1);
        r2.setCustomView(r4);
        ImageView r6 = new ImageView(r17);
        r6.setImageResource(R.drawable.large_article);
        r6.setScaleType(ImageView.ScaleType.CENTER);
        r6.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r20)));
        r4.addView(r6, LayoutHelper.createLinear(80, 80, 1, 0, 18, 0, 0));
        TextView r62 = new TextView(r17);
        r62.setText(LocaleController.getString(R.string.ArticleConversionTitle));
        r62.setTextSize(1, 20.0f);
        r62.setTypeface(AndroidUtilities.bold());
        r62.setGravity(17);
        int r8 = Theme.key_windowBackgroundWhiteBlackText;
        r62.setTextColor(Theme.getColor(r8, r20));
        r4.addView(r62, LayoutHelper.createLinear(-1, -2, 49, 25, 16, 25, 0));
        TextView r63 = new TextView(r17);
        r63.setText(AndroidUtilities.replaceTags(LocaleController.getString(R.string.ArticleConversionText)));
        r63.setTextSize(1, 14.0f);
        r63.setGravity(17);
        r63.setTextColor(Theme.getColor(r8, r20));
        r4.addView(r63, LayoutHelper.createLinear(-1, -2, 49, 25, 11, 25, 0));
        ButtonWithCounterView r5 = new ButtonWithCounterView(r17, r20).setRound();
        r5.setText(LocaleController.getString(R.string.ArticleConversionSubscribe));
        r4.addView(r5, LayoutHelper.createLinear(-1, 48, 49, 14, 31, 14, 0));
        ButtonWithCounterView r0 = new ButtonWithCounterView(r17, false, r20).setRound();
        r0.setText(LocaleController.getString(R.string.ArticleConversionConvert));
        r4.addView(r0, LayoutHelper.createLinear(-1, 48, 49, 14, 2, 14, 6));
        BottomSheet r1 = r2.show();
        r5.setOnClickListener(new RichEditor$$ExternalSyntheticLambda22(r1, r19));
        r0.setOnClickListener(new RichEditor$$ExternalSyntheticLambda23(r1, r18));
        return r1;
    }

    private void openKeyboardFromPopup() {
        hideEmojiPopup(true);
        RichEditText r1 = this.listView.findFocusedEditText();
        if (r1 == null) goto L6;
        r1.requestEditFocus();
        AndroidUtilities.showKeyboard(r1);
        return;
    }

    private void openLocationPicker(BlockRow r9) {
        if (r9 != null) goto L4;
        return;
    L4:
        if ((r9.block instanceof TL_iv.pageBlockMap) == true) goto L7;
        return;
    L7:
        if (AndroidUtilities.isMapsInstalled(this) == false) goto L13;
        ChatAttachAlert r1 = new ChatAttachAlert(getContext(), this, false, false, false, getResourceProvider());
        r1.setDelegate(new AnonymousClass12(this));
        r1.setLocationPicker();
        r1.setLocationActivityDelegate(new RichEditor$$ExternalSyntheticLambda59(this, r9, r1));
        r1.init();
        r1.show();
        return;
    }

    private boolean persistDraft() {
        if (this.chatActivity != null) goto L6;
        return false;
    L6:
        if (this.editingMessageObject == null) goto L9;
        return false;
    L9:
        if (this.listView.canUndo() == true) goto L12;
        return false;
    L12:
        if (this.sent == false) goto L15;
        TL_iv.RichMessage r1 = null;
    L14:
        TL_iv.RichMessage r16 = r1;
        if (r16 != null) goto L20;
        Runnable r12 = this.onClearedCallback;
        if (r12 == null) goto L20;
        r12.run();
    L20:
        ChatActivityEnterView r13 = this.chatActivity.getChatActivityEnterView();
        if (r16 != null) goto L23;
    L29:
        getMediaDataController().saveDraft(this.chatActivity.getDialogId(), this.chatActivity.getDraftThreadId(), _UrlKt.FRAGMENT_ENCODE_SET, null, null, null, null, 0, false, false, r16);
        if (r13 == null) goto L32;
        r13.setRichDraftPreview(r16);
    L32:
        return true;
    L23:
        if (this.sent == true) goto L29;
        if (this.listView.isSimpleConvertible() == false) goto L29;
        if (r13 == null) goto L29;
        r13.applyConvertedSimpleDraft(this.listView.toSimpleMessage());
        return true;
    L15:
        r1 = this.listView.buildDraftRichMessage();
        goto L14
    }

    private RichEditText resolveEmojiTarget() {
        RichEditText r0 = this.listView.getFocusedEditTextOrNull();
        if (r0 == null) goto L6;
        this.emojiTargetEditText = r0;
        this.emojiTargetSelection = Math.max(0, r0.getSelectionEnd());
        return r0;
    L6:
        RichEditText r02 = this.emojiTargetEditText;
        if (r02 == null) goto L10;
        return r02;
    L10:
        return this.listView.findFocusedEditText();
    }

    private int resolveEmojiTargetOffset(RichEditText r2) {
        if (r2 != this.emojiTargetEditText) goto L9;
        if (this.listView.getFocusedEditTextOrNull() == r2) goto L9;
        return Math.min(this.emojiTargetSelection, r2.length());
    L9:
        return Math.max(0, r2.getSelectionEnd());
    }

    private void saveDraftWithBulletin() {
        if (persistDraft() == false) goto L6;
        BulletinFactory.of(this.bulletinContainer, getResourceProvider()).createSimpleBulletin(R.raw.contact_check, LocaleController.getString(R.string.RichEditorDraftSaved)).show();
        return;
    }

    private void scheduleLimitCheck() {
        AndroidUtilities.cancelRunOnUIThread(this.limitCheckRunnable);
        AndroidUtilities.runOnUIThread(this.limitCheckRunnable, 1000);
    }

    private void sendMessage() {
        if (isSendLocked() == false) goto L7;
        showConversionSheet();
        return;
    L7:
        if (isInScheduleMode() == false) goto L10;
        AlertsCreator.createScheduleDatePickerDialog(getParentActivity(), this.chatActivity.getDialogId(), new AnonymousClass13(this), getResourceProvider());
        return;
    L10:
        sendMessage(true, 0, 0);
    }

    private void sendMessage(boolean r18, int r19, int r20) {
        if (isSendLocked() == false) goto L7;
        showConversionSheet();
        return;
    L7:
        if (this.chatActivity != null) goto L10;
        return;
    L10:
        if (this.listView.hasAnyText() == true) goto L13;
        return;
    L13:
        if (this.listView.hasPendingUploads() == false) goto L16;
        return;
    L16:
        if (this.listView.isWithinLimits() == true) goto L20;
        updateSendButtonEnabled();
        return;
    L20:
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == true) goto L29;
        ChatActivityEnterView r0 = this.chatActivity.getChatActivityEnterView();
        if (r0 == null) goto L44;
        this.sent = true;
        Runnable r2 = this.onSentCallback;
        if (r2 == null) goto L27;
        r2.run();
    L27:
        r0.sendConvertedRichAsSimple(this.listView.toSimpleMessage(), r18, r19, r20);
        finishFragment();
        return;
    L44:
        return;
    L29:
        this.sent = true;
        ArrayList<TL_iv.PageBlock> r3 = this.listView.flattenRowsToBlocks();
        if (r3.isEmpty() == false) goto L32;
        return;
    L32:
        ArrayList<TLRPC.Photo> r4 = this.listView.collectPhotos();
        ArrayList<TLRPC.Document> r5 = this.listView.collectDocuments();
        ArrayList<TLRPC.InputUser> r6 = RichMessageButtonUsers.collect(this.currentAccount, r3);
        long r7 = this.chatActivity.getDialogId();
        MessageObject r9 = this.chatActivity.getReplyMessage();
        MessageObject r10 = this.chatActivity.getThreadMessage();
        long r15 = this.chatActivity.getSendMonoForumPeerId();
        SendMessageChatArguments r14 = this.chatActivity.getMessageChatSendParams();
        MessageObject r22 = this.editingMessageObject;
        RichEditor$$ExternalSyntheticLambda43 r02 = new RichEditor$$ExternalSyntheticLambda43(this, r22, r3, r4, r5, r6, r7, r9, r10, r18, r19, r20, r14, r15);
        Runnable r32 = this.onSentCallback;
        if (r32 == null) goto L35;
        r32.run();
    L35:
        if (r19 == 0) goto L39;
        if (r22 != null) goto L39;
        this.pendingSend = r02;
        finishFragment();
        return;
    L39:
        r02.run();
        finishFragment();
    }

    private void setBoldEnabled(boolean r6) {
        ArrayList<Button> r5 = this.formattingButtons;
        int r0 = r5.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L7;
        Button r2 = r5.get(r1);
        r1 = r1 + 1;
        Button r22 = r2;
        if (((Integer) r22.getTag()).intValue() != 1) goto L3;
        r22.setEnabled(r6);
        goto L3
    }

    private void setInlineButtonsEnabled(boolean r2, boolean r3) {
        Button r0 = this.linkButton;
        if (r0 == null) goto L5;
        r0.setEnabled(r2);
    L5:
        Button r02 = this.inlineButton;
        if (r02 == null) goto L8;
        r02.setEnabled(r3);
    L8:
        Button r32 = this.dateButton;
        if (r32 == null) goto L11;
        r32.setEnabled(r2);
    L11:
        Button r1 = this.mathButton;
        if (r1 == null) goto L15;
        r1.setEnabled(r2);
        return;
    }

    private void setTrashHovered(boolean r4, boolean r5) {
        if (this.trashHovered != r4) goto L6;
        if (r5 == false) goto L6;
        return;
    L6:
        this.trashHovered = r4;
        if (r4 == false) goto L9;
        float r0 = 1.15f;
    L10:
        RLottieImageView r1 = this.trashPanelIcon;
        if (r5 == false) goto L13;
        r1.animate().scaleX(r0).scaleY(r0).setDuration(180).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
    L14:
        RLottieImageView r52 = this.trashPanelIcon;
        if (r4 == false) goto L17;
        int r12 = Theme.key_text_RedBold;
    L18:
        r52.setColorFilter(new PorterDuffColorFilter(getThemedColor(r12), PorterDuff.Mode.SRC_IN));
        RLottieDrawable r3 = this.trashPanelIcon.getAnimatedDrawable();
        if (r3 != null) goto L21;
        return;
    L21:
        if (r4 == true) goto L23;
        r3.setCustomEndFrame(0);
    L27:
        r3.start();
        return;
    L23:
        if (r3.getCurrentFrame() <= 34) goto L25;
        r3.setCurrentFrame(0, false);
    L25:
        r3.setCustomEndFrame(33);
        goto L27
    L17:
        r12 = Theme.key_windowBackgroundWhiteBlackText;
        goto L18
    L13:
        r1.animate().cancel();
        this.trashPanelIcon.setScaleX(r0);
        this.trashPanelIcon.setScaleY(r0);
        goto L14
    L9:
        r0 = 1.0f;
        goto L10
    }

    private void showConversionSheet() {
        Context r0 = getContext();
        RichEditorListView r1 = this.listView;
        Objects.requireNonNull(r1);
        openConversionSheet(r0, new ChatAttachAlertRichLayout$$ExternalSyntheticLambda6(r1), new RichEditor$$ExternalSyntheticLambda42(this), getResourceProvider());
    }

    private void showEmojiPopup() {
        createEmojiView();
        int r0 = getEmojiPanelHeight();
        FrameLayout.LayoutParams r1 = (FrameLayout.LayoutParams) this.emojiView.getLayoutParams();
        if (r1 != null) goto L5;
        r1 = LayoutHelper.createFrame(-1, r0, 87);
    L6:
        r1.bottomMargin = this.bottomInset;
        this.emojiView.setLayoutParams(r1);
        this.emojiView.setVisibility(0);
        this.emojiViewVisible = true;
        this.emojiPadding = r0 + this.bottomInset;
        RichEditText r02 = this.listView.findFocusedEditText();
        if (r02 == null) goto L9;
        AndroidUtilities.hideKeyboard(r02);
    L9:
        applyEmojiPadding();
        this.emojiButton.setState(ChatActivityEnterViewAnimatedIconView.State.KEYBOARD, true);
        return;
    L5:
        r1.height = r0;
        goto L6
    }

    private void toggleEmojiPopup() {
        if (this.emojiViewVisible == false) goto L6;
        openKeyboardFromPopup();
        return;
    L6:
        showEmojiPopup();
    }

    private void updateAnimatingLocations() {
        this.animateInputView.getLocationInWindow(this.location);
        if (this.animateFromRect != null) goto L5;
        this.animateFromRect = new RectF();
    L5:
        RectF r0 = new RectF(this.animateInputBackground.getBounds());
        this.animateFromRect = r0;
        int[] r1 = this.location;
        r0.offset(r1[0], r1[1]);
        if (this.animateEnterViewFrom != null) goto L8;
        this.animateEnterViewFrom = new int[2];
    L8:
        this.animateEnterView.getLocationInWindow(this.animateEnterViewFrom);
        if (this.animateEnterViewTo != null) goto L11;
        this.animateEnterViewTo = new int[2];
    L11:
        this.animateEnterViewTo[0] = this.listView.getPaddingLeft();
        this.animateEnterViewTo[1] = this.listView.getPaddingTop();
        this.animateEnterViewTo[0] = (int) (r0[0] - (this.animateEnterView.messageEditText.getX() - AndroidUtilities.dp(16.0f)));
    }

    private void updateBlockButtons() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        if (r0 != null) goto L5;
    L10:
        BlockRow r02 = this.listView.findFocusedRow();
    L12:
        if (this.listView.findFocusedTableCell() == null) goto L14;
    L13:
        int r1 = 4;
    L78:
        ArrayList<Button> r12 = this.blockButtons;
        int r7 = r12.size();
        int r8 = 0;
    L79:
        if (r8 >= r7) goto L136;
        Button r9 = r12.get(r8);
        r8 = r8 + 1;
        Button r92 = r9;
        int r10 = ((Integer) r92.getTag()).intValue();
        if (r1 != r10) goto L83;
        boolean r11 = true;
    L84:
        r92.setSelected(r11);
        if (r1 == r10) goto L86;
        if (r1 == 4) goto L134;
        boolean r102 = true;
    L135:
        r92.setEnabled(r102);
        r92.resetIcon();
        goto L79
    L134:
        r102 = false;
        goto L135
    L86:
        r92.setEnabled(true);
        if (r02 == null) goto L88;
        if (r1 == 1) goto L90;
        if (r1 == 2) goto L123;
        if (r1 == 8) goto L130;
        r92.resetIcon();
        goto L79
    L130:
        r92.updateIcon(R.drawable.iv_details);
        goto L79
    L123:
        if (r02.isChecklist() == true) goto L124;
        if (r02.isOrdered() == true) goto L127;
        r92.resetIcon();
        goto L79
    L127:
        r92.updateIcon(R.drawable.iv_ordered_list);
        goto L79
    L124:
        r92.updateIcon(R.drawable.iv_todo);
        goto L79
    L90:
        TL_iv.PageBlock r103 = r02.block;
        if ((r103 instanceof TL_iv.pageBlockHeading1) == true) goto L92;
        if ((r103 instanceof TL_iv.pageBlockHeading2) == true) goto L95;
        if ((r103 instanceof TL_iv.pageBlockHeading3) == true) goto L98;
        if ((r103 instanceof TL_iv.pageBlockHeading4) == true) goto L101;
        if ((r103 instanceof TL_iv.pageBlockHeading5) == true) goto L104;
        if ((r103 instanceof TL_iv.pageBlockHeading6) == true) goto L107;
        if ((r103 instanceof TL_iv.pageBlockPreformatted) == true) goto L110;
        if ((r103 instanceof TL_iv.pageBlockBlockquote) == true) goto L113;
        if ((r103 instanceof TL_iv.pageBlockPullquote) == true) goto L116;
        if ((r103 instanceof TL_iv.pageBlockFooter) == true) goto L119;
        r92.resetIcon();
        goto L79
    L119:
        r92.updateIcon(R.drawable.iv_footer);
        goto L79
    L116:
        r92.updateIcon(R.drawable.iv_pullquote);
        goto L79
    L113:
        r92.updateIcon(R.drawable.iv_quote);
        goto L79
    L110:
        r92.updateIcon(R.drawable.iv_code);
        goto L79
    L107:
        r92.updateIcon(R.drawable.iv_h6);
        goto L79
    L104:
        r92.updateIcon(R.drawable.iv_h5);
        goto L79
    L101:
        r92.updateIcon(R.drawable.iv_h4);
        goto L79
    L98:
        r92.updateIcon(R.drawable.iv_h3);
        goto L79
    L95:
        r92.updateIcon(R.drawable.iv_h2);
        goto L79
    L92:
        r92.updateIcon(R.drawable.iv_h1);
        goto L79
    L88:
        r92.resetIcon();
        goto L79
    L83:
        r11 = false;
        goto L84
    L136:
        return;
    L14:
        if (r02 != null) goto L17;
    L15:
        r1 = 0;
        goto L78
    L17:
        if (r02.isChecklist() == false) goto L19;
    L77:
        r1 = 2;
        goto L78
    L19:
        if (r02.isInList() == true) goto L77;
        if (r02.isOrdered() == true) goto L77;
        TL_iv.PageBlock r13 = r02.block;
        if ((r13 instanceof TL_iv.pageBlockDetails) == true) goto L77;
        if ((r13 instanceof TL_iv.pageBlockTable) == true) goto L13;
        if ((r13 instanceof TL_iv.pageBlockDivider) == false) goto L32;
        r1 = 8;
        goto L78
    L32:
        if ((r13 instanceof TL_iv.pageBlockHeading1) == false) goto L34;
    L76:
        r1 = 1;
        goto L78
    L34:
        if ((r13 instanceof TL_iv.pageBlockHeading2) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockHeading3) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockHeading4) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockHeading5) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockHeading6) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockParagraph) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockPreformatted) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockBlockquote) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockPullquote) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockFooter) == true) goto L76;
        if ((r13 instanceof TL_iv.pageBlockPhoto) == false) goto L57;
    L75:
        r1 = 3;
        goto L78
    L57:
        if ((r13 instanceof TL_iv.pageBlockVideo) == true) goto L75;
        if ((r13 instanceof TL_iv.pageBlockCollage) == true) goto L75;
        if ((r13 instanceof TL_iv.pageBlockSlideshow) == true) goto L75;
        if ((r13 instanceof TL_iv.pageBlockAudio) == false) goto L66;
    L74:
        r1 = 5;
        goto L78
    L66:
        if ((r13 instanceof TL_iv.pageBlockDocument) == true) goto L74;
        if ((r13 instanceof TL_iv.pageBlockMap) == false) goto L72;
        r1 = 6;
        goto L78
    L72:
        if ((r13 instanceof TL_iv.pageBlockMath) == false) goto L15;
        r1 = 7;
        goto L78
    L5:
        if (r0.isInSelectionMode() == false) goto L10;
        int r14 = r0.getStartCell();
        if (r14 != r0.getEndCell()) goto L9;
        r02 = this.listView.rowForCell(r14);
        goto L12
    L9:
        r02 = null;
        goto L12
    }

    private void updateBottomPanel(int r11, boolean r12) {
        if (this.bottomPanelType != r11) goto L5;
        return;
    L5:
        this.bottomPanelType = r11;
        LinearLayout r0 = this.bottomPanel;
        int r2 = 0;
        float r5 = 0.0f;
        float r6 = 0.8f;
        if (r12 == false) goto L52;
        r0.setVisibility(0);
        ViewPropertyAnimator r112 = this.bottomPanel.animate();
        if (this.bottomPanelType != 0) goto L10;
        float r122 = 1.0f;
    L11:
        ViewPropertyAnimator r113 = r112.alpha(r122);
        if (this.bottomPanelType != 0) goto L14;
        float r123 = 1.0f;
    L15:
        ViewPropertyAnimator r114 = r113.scaleX(r123);
        if (this.bottomPanelType != 0) goto L18;
        float r124 = 1.0f;
    L19:
        ViewPropertyAnimator r115 = r114.scaleY(r124);
        if (this.bottomPanelType != 0) goto L22;
        float r125 = 0.0f;
    L23:
        ViewPropertyAnimator r116 = r115.translationY(r125).setDuration(420);
        CubicBezierInterpolator r126 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r116.setInterpolator(r126).withEndAction(new RichEditor$$ExternalSyntheticLambda25(this)).start();
        this.formattingPanel.setVisibility(0);
        ViewPropertyAnimator r117 = this.formattingPanel.animate();
        if (this.bottomPanelType != 1) goto L26;
        float r02 = 1.0f;
    L27:
        ViewPropertyAnimator r118 = r117.alpha(r02);
        if (this.bottomPanelType != 1) goto L30;
        float r03 = 1.0f;
    L31:
        ViewPropertyAnimator r119 = r118.scaleX(r03);
        if (this.bottomPanelType != 1) goto L34;
        float r04 = 1.0f;
    L35:
        ViewPropertyAnimator r1110 = r119.scaleY(r04);
        if (this.bottomPanelType != 1) goto L38;
        float r05 = 0.0f;
    L39:
        r1110.translationY(r05).setDuration(420).setInterpolator(r126).withEndAction(new RichEditor$$ExternalSyntheticLambda26(this)).start();
        this.trashPanel.setVisibility(0);
        ViewPropertyAnimator r1111 = this.trashPanel.animate();
        if (this.bottomPanelType != 2) goto L42;
        r5 = 1.0f;
    L42:
        ViewPropertyAnimator r1112 = r1111.alpha(r5);
        if (this.bottomPanelType != 2) goto L45;
        float r06 = 1.0f;
    L46:
        ViewPropertyAnimator r1113 = r1112.scaleX(r06);
        if (this.bottomPanelType != 2) goto L49;
        r6 = 1.0f;
    L49:
        r1113.scaleY(r6).setDuration(420).setInterpolator(r126).withEndAction(new RichEditor$$ExternalSyntheticLambda27(this)).start();
        return;
    L45:
        r06 = 0.8f;
        goto L46
    L38:
        r05 = AndroidUtilities.dp(30.0f);
        goto L39
    L34:
        r04 = 0.8f;
        goto L35
    L30:
        r03 = 0.8f;
        goto L31
    L26:
        r02 = 0.0f;
        goto L27
    L22:
        r125 = AndroidUtilities.dp(30.0f);
        goto L23
    L18:
        r124 = 0.8f;
        goto L19
    L14:
        r123 = 0.8f;
        goto L15
    L10:
        r122 = 0.0f;
        goto L11
    L52:
        if (r11 != 0) goto L54;
        int r8 = 0;
    L55:
        r0.setVisibility(r8);
        LinearLayout r07 = this.bottomPanel;
        if (r11 != 0) goto L58;
        float r82 = 1.0f;
    L59:
        r07.setAlpha(r82);
        LinearLayout r08 = this.bottomPanel;
        if (r11 != 0) goto L62;
        float r83 = 1.0f;
    L63:
        r08.setScaleX(r83);
        LinearLayout r09 = this.bottomPanel;
        if (r11 != 0) goto L66;
        float r84 = 1.0f;
    L67:
        r09.setScaleY(r84);
        LinearLayout r010 = this.bottomPanel;
        if (r11 != 0) goto L70;
        float r85 = 0.0f;
    L71:
        r010.setTranslationY(r85);
        LinearLayout r011 = this.formattingPanel;
        if (r11 != 1) goto L74;
        int r86 = 0;
    L75:
        r011.setVisibility(r86);
        LinearLayout r012 = this.formattingPanel;
        if (r11 != 1) goto L78;
        float r87 = 1.0f;
    L79:
        r012.setAlpha(r87);
        LinearLayout r013 = this.formattingPanel;
        if (r11 != 1) goto L82;
        float r88 = 1.0f;
    L83:
        r013.setScaleX(r88);
        LinearLayout r014 = this.formattingPanel;
        if (r11 != 1) goto L86;
        float r89 = 1.0f;
    L87:
        r014.setScaleY(r89);
        LinearLayout r015 = this.formattingPanel;
        if (r11 != 1) goto L90;
        float r1 = 0.0f;
    L91:
        r015.setTranslationY(r1);
        FrameLayout r016 = this.trashPanel;
        if (r11 == 2) goto L95;
        r2 = 8;
    L95:
        r016.setVisibility(r2);
        FrameLayout r127 = this.trashPanel;
        if (r11 != 2) goto L98;
        r5 = 1.0f;
    L98:
        r127.setAlpha(r5);
        FrameLayout r128 = this.trashPanel;
        if (r11 != 2) goto L101;
        float r017 = 1.0f;
    L102:
        r128.setScaleX(r017);
        FrameLayout r10 = this.trashPanel;
        if (r11 != 2) goto L105;
        r6 = 1.0f;
    L105:
        r10.setScaleY(r6);
        return;
    L101:
        r017 = 0.8f;
        goto L102
    L90:
        r1 = AndroidUtilities.dp(30.0f);
        goto L91
    L86:
        r89 = 0.8f;
        goto L87
    L82:
        r88 = 0.8f;
        goto L83
    L78:
        r87 = 0.0f;
        goto L79
    L74:
        r86 = 8;
        goto L75
    L70:
        r85 = AndroidUtilities.dp(30.0f);
        goto L71
    L66:
        r84 = 0.8f;
        goto L67
    L62:
        r83 = 0.8f;
        goto L63
    L58:
        r82 = 0.0f;
        goto L59
    L54:
        r8 = 8;
        goto L55
    }

    private void updateFormattingButtons() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        if (this.formattingButtons.isEmpty() == true) goto L60;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.isInSelectionMode() == false) goto L62;
        Button r1 = this.quoteButton;
        if (r1 == null) goto L12;
        r1.setSelected(this.listView.isSelectionQuoted());
    L12:
        if (this.listView.isTableSelection() == false) goto L16;
        updateFormattingButtonsTable();
        return;
    L16:
        if (this.listView.isCaptionSelection() == false) goto L19;
        updateFormattingButtonsCaption();
        return;
    L19:
        int r4 = r0.getStartCell();
        int r6 = r0.getEndCell();
        int r5 = r0.getStartOffset();
        int r7 = r0.getEndOffset();
        boolean r02 = false;
        if (r4 < 0) goto L26;
        if (r6 < 0) goto L26;
        if (r6 < r4) goto L26;
        if (r6 >= this.listView.itemRows.size()) goto L26;
        boolean r8 = true;
    L27:
        ArrayList<Button> r9 = this.formattingButtons;
        int r10 = r9.size();
        int r2 = 0;
    L28:
        if (r2 >= r10) goto L36;
        int r11 = r2 + 1;
        Button r12 = r9.get(r2);
        int r3 = ((Integer) r12.getTag()).intValue();
        if (r8 == true) goto L32;
    L34:
        boolean r22 = false;
    L35:
        r12.setSelected(r22);
        r2 = r11;
        goto L28
    L32:
        if (this.listView.isStyleFullyApplied(r3, r4, r5, r6, r7) == false) goto L34;
        r22 = true;
        goto L35
    L36:
        setBoldEnabled(!this.listView.isSelectionAllHeadings());
        Button r23 = this.linkButton;
        if (r23 == null) goto L44;
        if (r8 == true) goto L40;
    L42:
        boolean r32 = false;
    L43:
        r23.setSelected(r32);
        goto L44
    L40:
        if (this.listView.isLinkApplied(r4, r5, r6, r7) == false) goto L42;
        r32 = true;
    L44:
        Button r24 = this.dateButton;
        if (r24 == null) goto L52;
        if (r8 == true) goto L48;
    L50:
        boolean r33 = false;
    L51:
        r24.setSelected(r33);
        goto L52
    L48:
        if (this.listView.isDateApplied(r4, r5, r6, r7) == false) goto L50;
        r33 = true;
    L52:
        if (r8 == false) goto L55;
        if (r4 != r6) goto L55;
        r02 = true;
    L55:
        setInlineButtonsEnabled(r02, this.listView.canCreateInlineButtonOnSelection());
        return;
    L26:
        r8 = false;
        goto L27
    L62:
        return;
    }

    private void updateFormattingButtonsCaption() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        RichEditText r1 = this.listView.captionEditText(r0.getStartCell());
        int r2 = r0.getStartOffset();
        int r02 = r0.getEndOffset();
        boolean r3 = false;
        if (r1 != null) goto L5;
        int r4 = 0;
    L6:
        if (r1 != null) goto L8;
        int r03 = 0;
    L9:
        ArrayList<Button> r22 = this.formattingButtons;
        int r5 = r22.size();
        int r6 = 0;
    L10:
        boolean r7 = true;
        if (r6 >= r5) goto L20;
        Button r8 = r22.get(r6);
        r6 = r6 + 1;
        Button r82 = r8;
        int r9 = ((Integer) r82.getTag()).intValue();
        if (r1 == null) goto L18;
        if (r4 >= r03) goto L18;
        if ((r9 & r1.getCurrentStyle(r4, r03)) == 0) goto L18;
    L19:
        r82.setSelected(r7);
    L18:
        r7 = false;
        goto L19
    L20:
        Button r23 = this.linkButton;
        if (r23 == null) goto L29;
        if (r1 == null) goto L27;
        if (r4 >= r03) goto L27;
        if (RichTextStyle.hasLink(r1.getText(), r4, r03) == false) goto L27;
        boolean r52 = true;
    L28:
        r23.setSelected(r52);
    L27:
        r52 = false;
    L29:
        Button r24 = this.dateButton;
        if (r24 == null) goto L37;
        if (r1 == null) goto L36;
        if (r4 >= r03) goto L36;
        if (RichTextStyle.hasDate(r1.getText(), r4, r03) == false) goto L36;
        r3 = true;
    L36:
        r24.setSelected(r3);
    L37:
        setBoldEnabled(true);
        setInlineButtonsEnabled(true, this.listView.canCreateInlineButtonOnSelection());
        return;
    L8:
        r03 = Math.max(0, Math.min(Math.max(r2, r02), r1.length()));
        goto L9
    L5:
        r4 = Math.max(0, Math.min(Math.min(r2, r02), r1.length()));
        goto L6
    }

    private void updateFormattingButtonsTable() {
        TextSelectionHelper.ArticleTextSelectionHelper r0 = this.listView.getTextSelectionHelper();
        int r3 = r0.getStartCell();
        int r4 = r0.getStartChildPosition();
        int r6 = r0.getEndChildPosition();
        int r5 = r0.getStartOffset();
        int r7 = r0.getEndOffset();
        ArrayList<Button> r02 = this.formattingButtons;
        int r8 = r02.size();
        boolean r9 = false;
        int r1 = 0;
    L3:
        if (r1 >= r8) goto L6;
        Button r11 = r02.get(r1);
        r11.setSelected(this.listView.isStyleFullyAppliedTable(((Integer) r11.getTag()).intValue(), r3, r4, r5, r6, r7));
        r1 = r1 + 1;
        goto L3
    L6:
        if (r4 != r6) goto L8;
        boolean r12 = true;
    L9:
        if (r12 == false) goto L11;
        RichEditText r2 = this.listView.tableEditText(r3, r4);
    L12:
        int r32 = Math.max(0, Math.min(r5, r7));
        if (r2 != null) goto L15;
        int r42 = 0;
    L16:
        Button r52 = this.linkButton;
        if (r52 == null) goto L25;
        if (r2 == null) goto L23;
        if (r32 >= r42) goto L23;
        if (RichTextStyle.hasLink(r2.getText(), r32, r42) == false) goto L23;
        boolean r62 = true;
    L24:
        r52.setSelected(r62);
    L23:
        r62 = false;
    L25:
        Button r53 = this.dateButton;
        if (r53 == null) goto L33;
        if (r2 == null) goto L32;
        if (r32 >= r42) goto L32;
        if (RichTextStyle.hasDate(r2.getText(), r32, r42) == false) goto L32;
        r9 = true;
    L32:
        r53.setSelected(r9);
    L33:
        setBoldEnabled(true);
        setInlineButtonsEnabled(r12, this.listView.canCreateInlineButtonOnSelection());
        return;
    L15:
        r42 = Math.max(0, Math.min(Math.max(r5, r7), r2.length()));
        goto L16
    L11:
        r2 = null;
        goto L12
    L8:
        r12 = false;
        goto L9
    }

    private void updateHistoryButtons() {
        boolean r0 = this.listView.canUndo();
        boolean r1 = this.listView.canRedo();
        ImageView r2 = this.undoButton;
        float r3 = 0.35f;
        if (r2 == null) goto L9;
        r2.setEnabled(r0);
        ImageView r22 = this.undoButton;
        if (r0 == false) goto L7;
        float r02 = 1.0f;
    L8:
        r22.setAlpha(r02);
        goto L9
    L7:
        r02 = 0.35f;
    L9:
        ImageView r03 = this.redoButton;
        if (r03 == null) goto L16;
        r03.setEnabled(r1);
        ImageView r5 = this.redoButton;
        if (r1 == false) goto L14;
        r3 = 1.0f;
    L14:
        r5.setAlpha(r3);
        return;
    }

    private void updatePremiumButtons() {
        int r1 = 0;
        if (MessagesController.getInstance(this.currentAccount).richEditorAllowed() == false) goto L5;
    L7:
        boolean r0 = false;
    L8:
        ArrayList<Button> r4 = this.premiumButtons;
        int r2 = r4.size();
    L9:
        if (r1 >= r2) goto L11;
        Button r3 = r4.get(r1);
        r1 = r1 + 1;
        r3.setPremiumLocked(r0);
        goto L9
    L11:
        return;
    L5:
        if (UserConfig.getInstance(this.currentAccount).isPremium() == true) goto L7;
        r0 = true;
        goto L8
    }

    private void updateSendButtonEnabled() {
        if (this.sendButton != null) goto L5;
        return;
    L5:
        boolean r0 = this.listView.isWithinLimits();
        this.sendButton.setEnabled(r0);
        ViewPropertyAnimator r2 = this.sendButton.animate();
        if (r0 == false) goto L8;
        float r02 = 1.0f;
    L9:
        r2.alpha(r02).setDuration(150).start();
        return;
    L8:
        r02 = 0.5f;
        goto L9
    }

    private void updateSendButtonLoading() {
        if (this.sendButton != null) goto L5;
        return;
    L5:
        boolean r0 = this.listView.hasPendingUploads();
        this.sendButtonLoading = r0;
        this.sendButton.setLoading(r0, -3.0f);
        this.sendButton.invalidate();
    }

    private void updateSendButtonLock() {
        ChatActivityEnterView.SendButton r0 = this.sendButton;
        if (r0 != null) goto L5;
        return;
    L5:
        r0.setLocked(isSendLocked());
    }

    public static Drawable withShadow(Drawable r1) {
        return new ShadowWrapperDrawable(r1);
    }

    public RichEditor animateFrom(ChatActivity r2) {
        this.animateInputView = r2.chatInputViewsContainer;
        this.animateEnterView = r2.getChatActivityEnterView();
        return this;
    }

    public RichEditor convertToSimpleOnOpen() {
        this.convertToSimpleOnOpen = true;
        return this;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r38) {
        this.actionBar.setCastShadows(false);
        this.actionBar.setAddToContainer(false);
        this.premiumButtons.clear();
        this.container = new AnonymousClass2(this, r38);
        setHasOwnBackground(true);
        this.container.setFocusable(true);
        this.container.setFocusableInTouchMode(true);
        if (Build.VERSION.SDK_INT < 26) goto L5;
        this.container.setDefaultFocusHighlightEnabled(false);
    L5:
        this.keyboardHeight = MessagesController.getGlobalEmojiSettings().getInt("kbd_height", AndroidUtilities.dp(200.0f));
        this.keyboardHeightLand = MessagesController.getGlobalEmojiSettings().getInt("kbd_height_land3", AndroidUtilities.dp(200.0f));
        RichEditor$$ExternalSyntheticLambda2 r0 = new RichEditor$$ExternalSyntheticLambda2(this);
        this.sizeDelegate = r0;
        this.container.addDelegate(r0);
        RichEditorListView r02 = new RichEditorListView(r38, this.currentAccount, getResourceProvider(), new AnonymousClass3(this));
        this.listView = r02;
        r02.setFileRefParentObject(this.editingMessageObject);
        this.container.addView(this.listView, LayoutHelper.createFrame(-1, -1, 119));
        this.container.addView(this.listView.getOverlayView(), LayoutHelper.createFrame(-1, -1.0f));
        TL_iv.RichMessage r03 = this.initialRichMessage;
        if (r03 == null) goto L8;
        this.listView.loadRichMessage(r03);
    L14:
        this.listView.resetHistoryBaseline();
        View r04 = new View(r38);
        this.topGradient = r04;
        GradientDrawable.Orientation r9 = GradientDrawable.Orientation.TOP_BOTTOM;
        int r10 = Theme.key_windowBackgroundWhite;
        r04.setBackground(new GradientDrawable(r9, new int[]{getThemedColor(r10), Theme.multAlpha(getThemedColor(r10), 0.0f)}));
        this.container.addView(this.topGradient, LayoutHelper.createFrame(-1, 68, 55));
        View r05 = new View(r38);
        this.bottomGradient = r05;
        r05.setBackground(new GradientDrawable(r9, new int[]{Theme.multAlpha(getThemedColor(r10), 0.0f), getThemedColor(r10)}));
        this.container.addView(this.bottomGradient, LayoutHelper.createFrame(-1, 68, 87));
        FrameLayout r06 = new FrameLayout(r38);
        this.topPanel = r06;
        r06.setClipChildren(false);
        this.topPanel.setClipToPadding(false);
        this.container.addView(this.topPanel, LayoutHelper.createFrame(-1, 68, 55));
        ImageView r07 = new ImageView(r38);
        this.backButton = r07;
        r07.setImageResource(R.drawable.ic_ab_back);
        ImageView r08 = this.backButton;
        ImageView.ScaleType r3 = ImageView.ScaleType.CENTER;
        r08.setScaleType(r3);
        ImageView r09 = this.backButton;
        int r11 = getThemedColor(r10);
        int r12 = getThemedColor(r10);
        int r13 = Theme.key_listSelector;
        r09.setBackground(withShadow(Theme.createRadSelectorDrawable(r11, Theme.blendOver(r12, getThemedColor(r13)), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f))));
        ImageView r010 = this.backButton;
        int r122 = Theme.key_windowBackgroundWhiteBlackText;
        int r14 = getThemedColor(r122);
        PorterDuff.Mode r15 = PorterDuff.Mode.SRC_IN;
        r010.setColorFilter(new PorterDuffColorFilter(r14, r15));
        ScaleStateListAnimator.apply(this.backButton);
        this.backButton.setContentDescription(LocaleController.getString(R.string.AccDescrGoBack));
        this.backButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda13(this));
        this.topPanel.addView(this.backButton, LayoutHelper.createFrame(44, 44.0f, 51, 8.0f, 8.0f, 8.0f, 8.0f));
        LinearLayout r011 = new LinearLayout(r38);
        this.historyButtons = r011;
        r011.setOrientation(0);
        this.historyButtons.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        this.topPanel.addView(this.historyButtons, LayoutHelper.createFrame(82, 44.0f, 53, 8.0f, 8.0f, 8.0f, 8.0f));
        ImageView r012 = new ImageView(r38);
        this.undoButton = r012;
        r012.setImageResource(R.drawable.iv_undo);
        this.undoButton.setScaleType(r3);
        this.undoButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r13)));
        this.undoButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        ScaleStateListAnimator.apply(this.undoButton);
        this.undoButton.setContentDescription(LocaleController.getString(R.string.Undo));
        this.undoButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda14(this));
        this.historyButtons.addView(this.undoButton, LayoutHelper.createLinear(41, 41, 16));
        ImageView r013 = new ImageView(r38);
        this.redoButton = r013;
        r013.setImageResource(R.drawable.iv_redo);
        this.redoButton.setScaleType(r3);
        this.redoButton.setBackground(Theme.createSelectorDrawable(getThemedColor(r13)));
        this.redoButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        ScaleStateListAnimator.apply(this.redoButton);
        this.redoButton.setContentDescription(LocaleController.getString(R.string.Redo));
        this.redoButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda15(this));
        this.historyButtons.addView(this.redoButton, LayoutHelper.createLinear(41, 41, 16));
        FrameLayout r014 = new FrameLayout(r38);
        this.bottomContainer = r014;
        r014.setClipChildren(false);
        this.bottomContainer.setClipToPadding(false);
        this.container.addView(this.bottomContainer, LayoutHelper.createFrame(-1, 160, 87));
        FrameLayout r015 = new FrameLayout(r38);
        this.bottomInnerContainer = r015;
        r015.setClipChildren(false);
        this.bottomInnerContainer.setClipToPadding(false);
        this.bottomContainer.addView(this.bottomInnerContainer, LayoutHelper.createFrame(-1, 160, 87));
        LinearLayout r016 = new LinearLayout(r38);
        this.bottomPanel = r016;
        r016.setClipToPadding(false);
        this.bottomPanel.setClipChildren(false);
        this.bottomPanel.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.bottomInnerContainer.addView(this.bottomPanel, LayoutHelper.createFrame(-1, 60, 87));
        FrameLayout r017 = new FrameLayout(r38);
        this.bulletinContainer = r017;
        this.bottomInnerContainer.addView(r017, LayoutHelper.createFrame(-1, 100.0f, 87, 0.0f, 0.0f, 0.0f, 60.0f));
        ImageView r018 = new ImageView(r38);
        this.aiButton = r018;
        r018.setImageDrawable(new AiButtonDrawable(r38));
        this.aiButton.setScaleType(r3);
        this.aiButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        this.aiButton.setBackground(withShadow(Theme.createRadSelectorDrawable(getThemedColor(r10), Theme.blendOver(getThemedColor(r10), getThemedColor(r13)), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(22.0f))));
        ImageView r019 = this.aiButton;
        if (ExteraConfig.getTelegramAiEditor() == false) goto L17;
        int r5 = 0;
    L18:
        r019.setVisibility(r5);
        this.bottomPanel.addView(this.aiButton, LayoutHelper.createLinear(44, 44, 0.0f, 19, 0, 0, 8, 0));
        ScaleStateListAnimator.apply(this.aiButton);
        this.aiButton.setContentDescription(LocaleController.getString(R.string.AIEditor));
        this.aiButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda16(this));
        FrameLayout r020 = new FrameLayout(r38);
        r020.setClipToPadding(false);
        r020.setClipChildren(false);
        FrameLayout r52 = new FrameLayout(r38);
        r52.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        r020.addView(r52, LayoutHelper.createFrame(-2, 44, 81));
        AnonymousClass4 r8 = new AnonymousClass4(this, r38);
        this.blocksScrollView = r8;
        r8.setClipToOutline(true);
        this.blocksScrollView.setOutlineProvider(new AnonymousClass5(this));
        LinearLayout r82 = new LinearLayout(r38);
        this.blocksLayout = r82;
        r82.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.blocksLayout.setOrientation(0);
        this.blocksScrollView.addView(this.blocksLayout);
        r52.addView(this.blocksScrollView, LayoutHelper.createFrame(-1, -1.0f));
        ChatActivityEnterViewAnimatedIconView r53 = new ChatActivityEnterViewAnimatedIconView(r38, 24);
        this.emojiButton = r53;
        r53.setPadding(AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f), AndroidUtilities.dp(7.0f));
        this.emojiButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        this.emojiButton.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r10), getThemedColor(r13), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f)));
        this.emojiButton.setState(ChatActivityEnterViewAnimatedIconView.State.SMILE, false);
        this.blocksLayout.addView(this.emojiButton, LayoutHelper.createLinear(38, 38, 16));
        ScaleStateListAnimator.apply(this.emojiButton);
        this.emojiButton.setContentDescription(LocaleController.getString(R.string.AccDescrEmojiButton));
        this.emojiButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda17(this));
        addBlockButton(R.drawable.iv_text, 1).setOnClickListener(new RichEditor$$ExternalSyntheticLambda18(this, r38));
        addBlockButton(R.drawable.iv_lists, 2, true).setOnClickListener(new RichEditor$$ExternalSyntheticLambda19(this));
        addBlockButton(R.drawable.iv_table, 4, true).setOnClickListener(new RichEditor$$ExternalSyntheticLambda20(this));
        addBlockButton(R.drawable.iv_math, 7, true).setOnClickListener(new RichEditor$$ExternalSyntheticLambda21(this));
        ImageView r4 = new ImageView(r38);
        this.addButton = r4;
        r4.setImageResource(R.drawable.outline_poll_attach_24);
        this.addButton.setScaleType(r3);
        this.addButton.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        this.addButton.setBackground(Theme.createRadSelectorDrawable(getThemedColor(r10), getThemedColor(r13), AndroidUtilities.dp(20.0f), AndroidUtilities.dp(20.0f)));
        this.blocksLayout.addView(this.addButton, LayoutHelper.createLinear(38, 38, 16, 2, 0, 0, 0));
        ScaleStateListAnimator.apply(this.addButton);
        this.addButton.setContentDescription(LocaleController.getString(R.string.AccDescrAttachButton));
        this.addButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda3(this));
        this.bottomPanel.addView(r020, LayoutHelper.createLinear(0, 44, 1.0f));
        AnonymousClass6 r021 = new AnonymousClass6(this, r38);
        this.formattingPanel = r021;
        r021.setOrientation(0);
        this.formattingPanel.setClipToPadding(false);
        this.formattingPanel.setClipChildren(false);
        this.formattingPanel.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.bottomContainer.addView(this.formattingPanel, LayoutHelper.createFrame(-2, 60, 81));
        FrameLayout r022 = new FrameLayout(r38);
        this.trashPanel = r022;
        r022.setClipChildren(false);
        this.trashPanel.setClipToPadding(false);
        this.trashPanel.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        this.bottomContainer.addView(this.trashPanel, LayoutHelper.createFrame(80, 60, 81));
        RLottieImageView r023 = new RLottieImageView(r38);
        this.trashPanelIcon = r023;
        r023.setAnimation(R.raw.group_pip_delete_icon, AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        RLottieDrawable r024 = this.trashPanelIcon.getAnimatedDrawable();
        if (r024 == null) goto L21;
        r024.setPlayInDirectionOfCustomEndFrame(true);
        r024.setAutoRepeat(0);
        r024.setCustomEndFrame(0);
    L21:
        this.trashPanelIcon.setScaleType(r3);
        this.trashPanelIcon.setColorFilter(new PorterDuffColorFilter(getThemedColor(r122), r15));
        this.trashPanelIcon.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        this.trashPanel.addView(this.trashPanelIcon, LayoutHelper.createFrame(-1, -1, 119));
        FrameLayout r025 = new FrameLayout(r38);
        r025.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        this.formattingPanel.addView(r025, LayoutHelper.createFrame(-2, 44.0f));
        AnonymousClass7 r32 = new AnonymousClass7(this, r38);
        this.formattingScrollView = r32;
        r32.setHorizontalScrollBarEnabled(false);
        this.formattingScrollView.setClipToOutline(true);
        this.formattingScrollView.setOutlineProvider(new AnonymousClass8(this));
        r025.addView(this.formattingScrollView, LayoutHelper.createFrame(-1, -1.0f));
        LinearLayout r026 = new LinearLayout(r38);
        this.formattingPanelLayout = r026;
        r026.setOrientation(0);
        this.formattingPanelLayout.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingScrollView.addView(this.formattingPanelLayout, new FrameLayout.LayoutParams(-2, -1));
        addFormattingButton(r38, R.drawable.formatting_bold, 1);
        addFormattingButton(r38, R.drawable.formatting_italic, 2);
        addFormattingButton(r38, R.drawable.formatting_underline, 16);
        addFormattingButton(r38, R.drawable.formatting_strikethrough, 8);
        addFormattingButton(r38, R.drawable.formatting_spoiler, 256);
        addFormattingButton(r38, R.drawable.iv_code, 4);
        addFormattingButton(r38, R.drawable.formatting_marked, 65536, true);
        addFormattingButton(r38, R.drawable.iv_sub, 16384, true);
        addFormattingButton(r38, R.drawable.iv_super, 32768, true);
        Button r027 = new Button(r38, R.drawable.iv_quote, getResourceProvider());
        this.quoteButton = r027;
        r027.setContentDescription(LocaleController.getString(R.string.Quote));
        this.quoteButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda4(this));
        LinearLayout r028 = this.formattingPanelLayout;
        Button r42 = this.quoteButton;
        if (r028.getChildCount() <= 0) goto L24;
        int r322 = 2;
    L25:
        r028.addView(r42, LayoutHelper.createLinear(38, 38, 16, r322, 0, 0, 0));
        Button r029 = new Button(r38, R.drawable.iv_button, getResourceProvider());
        this.inlineButton = r029;
        r029.setContentDescription(LocaleController.getString(R.string.RichEditorButton));
        this.inlineButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda5(this));
        LinearLayout r030 = this.formattingPanelLayout;
        Button r43 = this.inlineButton;
        if (r030.getChildCount() <= 0) goto L28;
        int r323 = 2;
    L29:
        r030.addView(r43, LayoutHelper.createLinear(38, 38, 16, r323, 0, 0, 0));
        LinearLayout r031 = new LinearLayout(r38);
        this.formattingLayout2 = r031;
        r031.setOrientation(0);
        this.formattingLayout2.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout2.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        this.formattingPanel.addView(this.formattingLayout2, LayoutHelper.createFrame(-2, 44.0f, 80, 8.0f, 0.0f, 0.0f, 0.0f));
        Button r032 = new Button(r38, R.drawable.media_link_24, getResourceProvider());
        this.linkButton = r032;
        r032.setContentDescription(LocaleController.getString(R.string.CreateLink));
        this.linkButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda6(this));
        this.formattingLayout2.addView(this.linkButton, LayoutHelper.createLinear(38, 38, 16));
        Button r033 = new Button(r38, R.drawable.msg_calendar2, getResourceProvider());
        this.dateButton = r033;
        r033.setContentDescription(LocaleController.getString(R.string.AccDescrIVInsertDate));
        this.dateButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda7(this));
        this.formattingLayout2.addView(this.dateButton, LayoutHelper.createLinear(38, 38, 16));
        LinearLayout r034 = new LinearLayout(r38);
        this.formattingLayout3 = r034;
        r034.setOrientation(0);
        this.formattingLayout3.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout3.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        this.formattingPanel.addView(this.formattingLayout3, LayoutHelper.createFrame(-2, 44.0f, 80, 8.0f, 0.0f, 0.0f, 0.0f));
        Button r035 = new Button(r38, R.drawable.iv_math, getResourceProvider());
        this.mathButton = r035;
        r035.setPremium();
        this.premiumButtons.add(this.mathButton);
        this.mathButton.setContentDescription(LocaleController.getString(R.string.AccDescrIVFormula));
        this.mathButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda8(this));
        this.formattingLayout3.addView(this.mathButton, LayoutHelper.createLinear(38, 38, 16));
        LinearLayout r036 = new LinearLayout(r38);
        this.formattingLayout1 = r036;
        r036.setOrientation(0);
        this.formattingLayout1.setPadding(AndroidUtilities.dp(2.0f), 0, AndroidUtilities.dp(2.0f), 0);
        this.formattingLayout1.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(r10))));
        LinearLayout r037 = this.formattingLayout1;
        if (ExteraConfig.getTelegramAiEditor() == false) goto L32;
        int r92 = 0;
    L33:
        r037.setVisibility(r92);
        this.formattingPanel.addView(this.formattingLayout1, 0, LayoutHelper.createFrame(-2, 44.0f, 80, 0.0f, 0.0f, 8.0f, 0.0f));
        Button r038 = new Button(r38, 0, getResourceProvider());
        this.aiStyleButton = r038;
        r038.setImageDrawable(new AiButtonDrawable(r38));
        this.aiStyleButton.setContentDescription(LocaleController.getString(R.string.AIEditor));
        this.aiStyleButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda9(this));
        this.formattingLayout1.addView(this.aiStyleButton, LayoutHelper.createLinear(38, 38, 16));
        if (this.editingMessageObject == null) goto L38;
        int r039 = R.drawable.input_done;
    L41:
        AnonymousClass9 r040 = new AnonymousClass9(this, r38, r039, getResourceProvider(), true);
        this.sendButton = r040;
        r040.centeredBackground = true;
        r040.setBackground(withShadow(Theme.createRoundRectDrawable(AndroidUtilities.dp(22.0f), getThemedColor(Theme.key_chat_messagePanelSend))));
        ScaleStateListAnimator.apply(this.sendButton);
        this.bottomPanel.addView(this.sendButton, LayoutHelper.createLinear(44, 44, 0.0f, 5, 8, 0, 0, 0));
        this.sendButton.setContentDescription(LocaleController.getString(R.string.Send));
        this.sendButton.setOnClickListener(new RichEditor$$ExternalSyntheticLambda10(this));
        this.sendButton.setOnLongClickListener(new RichEditor$$ExternalSyntheticLambda11(this));
        updateSendButtonLock();
        this.container.setPadding(0, AndroidUtilities.statusBarHeight, 0, 0);
        checkUI_listViewPadding();
        updateBottomPanel(0, false);
        updateHistoryButtons();
        this.container.getViewTreeObserver().addOnGlobalFocusChangeListener(new RichEditor$$ExternalSyntheticLambda12(this));
        updatePremiumButtons();
        if (this.convertToSimpleOnOpen == false) goto L44;
        this.listView.convertToSimple();
        this.convertToSimpleOnOpen = false;
    L44:
        SizeNotifierFrameLayout r041 = this.container;
        this.fragmentView = r041;
        return r041;
    L38:
        if (isInScheduleMode() == false) goto L40;
        r039 = R.drawable.input_schedule;
        goto L41
    L40:
        r039 = R.drawable.send_extera_24;
        goto L41
    L32:
        r92 = 8;
        goto L33
    L28:
        r323 = 0;
        goto L29
    L24:
        r322 = 0;
        goto L25
    L17:
        r5 = 8;
        goto L18
    L8:
        String r042 = this.initialHtml;
        if (r042 == null) goto L11;
        this.listView.loadHtml(this.initialHtmlBefore, r042, this.initialHtmlAfter);
        goto L14
    L11:
        CharSequence r043 = this.initialText;
        if (r043 == null) goto L14;
        this.listView.setInitialText(r043);
        goto L14
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.currentUserPremiumStatusChanged) goto L6;
        updateSendButtonLock();
        updatePremiumButtons();
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean hideKeyboardOnShow() {
        return false;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSupportEdgeToEdge() {
        return true;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean isSwipeBackEnabled(MotionEvent r2) {
        RichEditorListView r0 = this.listView;
        if (r0 == null) goto L9;
        if (r0.textSelectionHelper.isInSelectionMode() == false) goto L9;
        return false;
    L9:
        return super.isSwipeBackEnabled(r2);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onActivityResultFragment(int r3, int r4, Intent r5) {
        if (r4 == (-1)) goto L5;
    L11:
        if (r4 == (-1)) goto L13;
    L22:
        super.onActivityResultFragment(r3, r4, r5);
        return;
    L13:
        if (r3 != 1) goto L15;
    L16:
        if (r5 != null) goto L18;
        return;
    L18:
        if (r5.getData() == null) goto L27;
        this.listView.attachExternalMedia(r5.getData());
        return;
    L27:
        return;
    L15:
        if (r3 != 14) goto L22;
    L5:
        if (r3 != 21) goto L11;
        if (r5 != null) goto L8;
        return;
    L8:
        if (r5.getData() == null) goto L25;
        this.listView.attachDocument(r5.getData());
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onBackPressed(boolean r3) {
        if (this.emojiSearchOpened == false) goto L7;
        closeEmojiSearch();
        return false;
    L7:
        if (this.emojiViewVisible == false) goto L11;
        hideEmojiPopup(true);
        return false;
    L11:
        if (this.listView.deselectIfAny() == false) goto L14;
        return false;
    L14:
        return super.onBackPressed(r3);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public AnimatorSet onCustomTransitionAnimation(boolean r22, Runnable r23) {
        if (r22 == true) goto L8;
        if (this.persistedDraftOnEnd == true) goto L8;
        persistDraft();
        this.persistedDraftOnEnd = true;
    L8:
        if (AndroidUtilities.isTablet() == true) goto L28;
        if (this.animateInputView == null) goto L28;
        if (this.animateEnterView == null) goto L28;
        AnimatorSet r2 = new AnimatorSet();
        ChatInputViewsContainer r3 = this.animateInputView;
        this.animateInputBackground = r3.blurredBackgroundDrawable;
        r3.drawInputBackground = false;
        r3.invalidate();
        this.animateEnterView.setAlpha(0.0f);
        this.animateEnterView.sendButtonContainer.setVisibility(4);
        updateAnimatingLocations();
        float r32 = 1.0f;
        if (r22 == false) goto L16;
        float r7 = 0.0f;
    L17:
        this.animateOpenProgress = r7;
        if (r22 == true) goto L21;
        r32 = 0.0f;
    L21:
        ValueAnimator r33 = ValueAnimator.ofFloat(new float[]{r7, r32});
        this.animatingOpen = true;
        this.container.invalidate();
        r33.addUpdateListener(new RichEditor$$ExternalSyntheticLambda24(this));
        r33.addListener(new AnonymousClass1(this, r23));
        FrameLayout r72 = this.topPanel;
        if (r22 == true) goto L24;
        Property r15 = View.ALPHA;
        ObjectAnimator r4 = ObjectAnimator.ofFloat(r72, r15, new float[]{0.0f});
        FrameLayout r73 = this.topPanel;
        Property r5 = View.TRANSLATION_Y;
        r2.playTogether(new Animator[]{r33, r4, ObjectAnimator.ofFloat(r73, r5, new float[]{-AndroidUtilities.dp(16.0f)}), ObjectAnimator.ofFloat(this.bottomInnerContainer, r15, new float[]{0.0f}), ObjectAnimator.ofFloat(this.bottomInnerContainer, r5, new float[]{AndroidUtilities.dp(16.0f), 0.0f}), ObjectAnimator.ofFloat(this.listView, r15, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.topGradient, r15, new float[]{1.0f, 0.0f}), ObjectAnimator.ofFloat(this.bottomGradient, r15, new float[]{1.0f, 0.0f})});
    L25:
        r2.setDuration(420);
        r2.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.container.post(new ArticleViewer$$ExternalSyntheticLambda24(r2));
        return r2;
    L24:
        Property r1 = View.ALPHA;
        ObjectAnimator r42 = ObjectAnimator.ofFloat(r72, r1, new float[]{0.0f, 1.0f});
        FrameLayout r52 = this.topPanel;
        Property r6 = View.TRANSLATION_Y;
        r2.playTogether(new Animator[]{r33, r42, ObjectAnimator.ofFloat(r52, r6, new float[]{-AndroidUtilities.dp(16.0f), 0.0f}), ObjectAnimator.ofFloat(this.bottomInnerContainer, r1, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.bottomInnerContainer, r6, new float[]{AndroidUtilities.dp(16.0f), 0.0f}), ObjectAnimator.ofFloat(this.listView, r1, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.topGradient, r1, new float[]{0.0f, 1.0f}), ObjectAnimator.ofFloat(this.bottomGradient, r1, new float[]{0.0f, 1.0f})});
        goto L25
    L16:
        r7 = 1.0f;
    L28:
        return super.onCustomTransitionAnimation(r22, r23);
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public boolean onFragmentCreate() {
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        return super.onFragmentCreate();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onFragmentDestroy() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.currentUserPremiumStatusChanged);
        if (this.persistedDraftOnEnd == true) goto L5;
        persistDraft();
        this.persistedDraftOnEnd = true;
    L5:
        Runnable r0 = this.pendingSend;
        if (r0 == null) goto L8;
        this.pendingSend = null;
        AndroidUtilities.runOnUIThread(r0);
    L8:
        MessageSendPreview r02 = this.messageSendPreview;
        if (r02 == null) goto L11;
        r02.dismissInstant();
        this.messageSendPreview = null;
    L11:
        RichEditorListView r03 = this.listView;
        if (r03 == null) goto L14;
        r03.destroy();
    L14:
        RichCommandSuggestions r04 = this.commandSuggestions;
        if (r04 == null) goto L17;
        r04.hide();
    L17:
        super.onFragmentDestroy();
        EmojiView r05 = this.emojiView;
        if (r05 == null) goto L20;
        r05.onDestroy();
    L20:
        SizeNotifierFrameLayout r06 = this.container;
        if (r06 == null) goto L26;
        SizeNotifierFrameLayout.SizeNotifierFrameLayoutDelegate r2 = this.sizeDelegate;
        if (r2 == null) goto L27;
        r06.removeDelegate(r2);
        return;
    L27:
        return;
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onInsets(int r1, int r2, int r3, int r4) {
        this.bottomInset = r4;
        FrameLayout.LayoutParams r12 = (FrameLayout.LayoutParams) this.bottomGradient.getLayoutParams();
        r12.height = AndroidUtilities.dp(68.0f) + this.bottomInset;
        this.bottomGradient.setLayoutParams(r12);
        checkUI_listViewPadding();
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public WindowInsetsCompat onInsetsInternal(View r5, WindowInsetsCompat r6) {
        Insets r52 = r6.getInsets(WindowInsetsCompat.Type.navigationBars() | WindowInsetsCompat.Type.statusBars());
        int r62 = r6.getInsets(WindowInsetsCompat.Type.ime()).bottom;
        this.imeInset = r62;
        int r63 = r62 - r52.bottom;
        boolean r0 = this.keyboardVisible;
        if (r63 <= AndroidUtilities.dp(20.0f)) goto L5;
        boolean r1 = true;
    L6:
        this.keyboardVisible = r1;
        if (r1 == false) goto L19;
        if (r63 <= AndroidUtilities.dp(50.0f)) goto L19;
        if (AndroidUtilities.isInMultiwindow == true) goto L19;
        if (AndroidUtilities.isTablet() == true) goto L19;
        Point r12 = AndroidUtilities.displaySize;
        if (r12.x <= r12.y) goto L17;
        this.keyboardHeightLand = r63;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height_land3", this.keyboardHeightLand).commit();
        goto L19
    L17:
        this.keyboardHeight = r63;
        MessagesController.getGlobalEmojiSettings().edit().putInt("kbd_height", this.keyboardHeight).commit();
    L19:
        if (this.keyboardVisible == false) goto L27;
        if (r0 == true) goto L27;
        if (this.emojiViewVisible == false) goto L27;
        if (this.emojiSearchOpened == true) goto L27;
        hideEmojiPopup(false);
    L27:
        if (this.keyboardVisible == true) goto L32;
        if (r0 == false) goto L32;
        ItemOptions r64 = this.currentMenuVisible;
        if (r64 == null) goto L32;
        r64.dismiss();
        this.currentMenuVisible = null;
    L32:
        onInsets(r52.left, r52.top, r52.right, r52.bottom);
        return WindowInsetsCompat.CONSUMED;
    L5:
        r1 = false;
        goto L6
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public void onTransitionAnimationStart(boolean r3, boolean r4) {
        super.onTransitionAnimationStart(r3, r4);
        if (r3 == false) goto L11;
        if (r4 == true) goto L11;
        int r0 = this.initialSelectionStart;
        if (r0 < 0) goto L11;
        RichEditorListView r1 = this.listView;
        if (r1 == null) goto L11;
        int r32 = this.initialSelectionEnd;
        this.initialSelectionEnd = -1;
        this.initialSelectionStart = -1;
        r1.applyInitialSelection(r0, r32);
        return;
    L11:
        if (r3 == false) goto L21;
        if (r4 == false) goto L14;
        return;
    L14:
        if (this.initialRichMessage == null) goto L16;
    L17:
        RichEditorListView r2 = this.listView;
        if (r2 == null) goto L24;
        r2.focusForDraft();
        return;
    L24:
        return;
    L16:
        if (this.initialHtml != null) goto L17;
        return;
    }

    public RichEditor setChatActivity(ChatActivity r1) {
        this.chatActivity = r1;
        return this;
    }

    public RichEditor setEditing(MessageObject r1) {
        this.editingMessageObject = r1;
        return this;
    }

    public RichEditor setHtmlSurrounding(CharSequence r1, CharSequence r2) {
        this.initialHtmlBefore = r1;
        this.initialHtmlAfter = r2;
        return this;
    }

    public RichEditor setInitialSelection(int r1, int r2) {
        this.initialSelectionStart = r1;
        this.initialSelectionEnd = r2;
        return this;
    }

    public RichEditor setOnCleared(Runnable r1) {
        this.onClearedCallback = r1;
        return this;
    }

    public RichEditor setOnSent(Runnable r1) {
        this.onSentCallback = r1;
        return this;
    }
}
