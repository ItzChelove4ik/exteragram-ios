package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.TextureView;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import androidx.collection.LongSparseArray;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.GridLayoutManagerFixed;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.Utilities;
import org.telegram.messenger.utils.WindowVisibilityManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.AdjustPanLayoutHelper;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.ChatActivityEnterView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrimOptions;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.Text;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;
import org.telegram.ui.Components.spoilers.SpoilerEffect2;
import org.telegram.ui.EmojiAnimationsOverlay;
import org.telegram.ui.Stars.StarsIntroActivity;
import org.telegram.ui.Stories.recorder.KeyboardNotifier;
import org.telegram.ui.recyclerview.ChatListItemAnimator;

/* loaded from: classes6.dex */
public class MessageSendPreview extends Dialog implements NotificationCenter.NotificationCenterDelegate {
    private WindowVisibilityManager.Controller activityVisibilityController;
    private final RecyclerView.Adapter adapter;
    public boolean allowRelayout;
    private ChatActivityEnterView.SendButton anchorSendButton;
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private Paint buttonBgPaint;
    private Text buttonText;
    private RectF cameraRect;
    private Rect cellDelta;
    private final GridLayoutManagerFixed chatLayoutManager;
    private final RecyclerListView chatListView;
    private boolean closing;
    private final FrameLayout containerView;
    public final Context context;
    public final int currentAccount;
    private boolean customSendButtonWidth;
    private ChatMessageCell destCell;
    private float destClipBottom;
    private float destClipTop;
    private boolean dismissing;
    private Utilities.Callback2<Canvas, Utilities.Callback0Return<Boolean>> drawEditText;
    private Utilities.Callback<Canvas> drawEditTextBackground;
    private ChatMessageCell dummyMessageCell;
    private EditTextCaption editText;
    private Paint editTextBackgroundPaint;
    private AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable effectDrawable;
    private long effectId;
    private EmojiAnimationsOverlay effectOverlay;
    private ReactionsContainerLayout effectSelector;
    private FrameLayout effectSelectorContainer;
    private float effectSelectorContainerY;
    private boolean effectSelectorShown;
    private final FrameLayout effectsView;
    private boolean firstOpenFrame;
    private boolean firstOpenFrame2;
    private boolean focusable;
    private VisiblePart fromPart;
    private final LongSparseArray<MessageObject.GroupedMessages> groupedMessagesMap;
    private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
    private final BlurredBackgroundSourceBitmap iBlur3SourceBitmap;
    private Insets insets;
    private boolean keyboardVisible;
    private boolean layoutDone;
    private ChatMessageCell mainMessageCell;
    private int mainMessageCellId;
    private final ArrayList<MessageObject> messageObjects;
    private int messageObjectsWidth;
    private ValueAnimator openAnimator;
    private boolean openInProgress;
    private float openProgress;
    private boolean opening;
    private View optionsView;
    public final Theme.ResourcesProvider resourcesProvider;
    private boolean scrolledToLast;
    private ChatActivityEnterView.SendButton sendButton;
    private final int[] sendButtonInitialPosition;
    private int sendButtonWidth;
    private boolean sent;
    private boolean sentEffect;
    private SpoilerEffect2 spoilerEffect2;
    private final FrameLayout windowView;

    /* renamed from: org.telegram.ui.MessageSendPreview$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass1(MessageSendPreview r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (MessageSendPreview.m16117$$Nest$fgetactivityVisibilityController(this.this$0) == null) goto L12;
            WindowVisibilityManager.Controller r0 = MessageSendPreview.m16117$$Nest$fgetactivityVisibilityController(this.this$0);
            if (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) == 1.0f) goto L7;
        L9:
            boolean r1 = false;
        L10:
            r0.setHidden(r1);
            goto L12
        L7:
            if (MessageSendPreview.m16120$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L9;
            r1 = true;
        L12:
            if (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L14;
        L16:
            Canvas r12 = r8;
        L17:
            super.dispatchDraw(r12);
            return;
        L14:
            if (MessageSendPreview.m16120$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L16;
            MessageSendPreview.m16122$$Nest$fgetblurMatrix(this.this$0).reset();
            float r02 = getWidth() / MessageSendPreview.m16119$$Nest$fgetblurBitmap(this.this$0).getWidth();
            MessageSendPreview.m16122$$Nest$fgetblurMatrix(this.this$0).postScale(r02, r02);
            MessageSendPreview.m16121$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(MessageSendPreview.m16122$$Nest$fgetblurMatrix(this.this$0));
            MessageSendPreview.m16120$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r12 = r8;
            r12.drawRect(0.0f, 0.0f, getWidth(), getHeight(), MessageSendPreview.m16120$$Nest$fgetblurBitmapPaint(this.this$0));
            goto L17
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r3) {
            if (r3 == null) goto L10;
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getAction() != 1) goto L10;
            this.this$0.onBackPressed();
            return true;
        L10:
            return super.dispatchKeyEventPreIme(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (MessageSendPreview.m16145$$Nest$fgetlayoutDone(this.this$0) == true) goto L5;
        L8:
            MessageSendPreview.m16174$$Nest$mlayout(this.this$0);
            MessageSendPreview.m16164$$Nest$fputlayoutDone(this.this$0, true);
            return;
        L5:
            if (this.this$0.allowRelayout == true) goto L8;
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            MessageSendPreview.m16170$$Nest$mcheckBitmapMatrix(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$10, reason: invalid class name */
    public class AnonymousClass10 extends RecyclerView.Adapter {
        final /* synthetic */ MessageSendPreview this$0;
        final /* synthetic */ Context val$context;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: org.telegram.ui.MessageSendPreview$10$1, reason: invalid class name */
        public class AnonymousClass1 implements ChatMessageCell.ChatMessageCellDelegate {
            final /* synthetic */ AnonymousClass10 this$1;

            public AnonymousClass1(AnonymousClass10 r1) {
                this.this$1 = r1;
            }

            @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
            public boolean canPerformActions() {
                return false;
            }
        }

        public AnonymousClass10(MessageSendPreview r1, Context r2, Theme.ResourcesProvider r3) {
            this.this$0 = r1;
            this.val$context = r2;
            this.val$resourcesProvider = r3;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).size();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r10, int r11) {
            boolean r2 = true;
            MessageObject r4 = (MessageObject) MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).get((getItemCount() - 1) - r11);
            ChatMessageCell r3 = (ChatMessageCell) r10.itemView;
            MessageObject.GroupedMessages r5 = MessageSendPreview.m16173$$Nest$mgetValidGroupedMessage(this.this$0, r4);
            if (r5 != null) goto L6;
            r2 = false;
        L6:
            r3.setInvalidatesParent(r2);
            r3.setMessageObject(r4, r5, false, false, false);
            if (r11 == MessageSendPreview.m16171$$Nest$mgetMainMessageCellPosition(this.this$0)) goto L9;
            return;
        L9:
            if (r4.needDrawForwarded() == true) goto L13;
            MessageSendPreview.m16165$$Nest$fputmainMessageCell(this.this$0, r3);
            ChatMessageCell r102 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0);
            Point r112 = AndroidUtilities.displaySize;
            r102.setParentViewSize(r112.x, r112.y);
            MessageSendPreview.m16166$$Nest$fputmainMessageCellId(this.this$0, r4.getId());
            return;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r8, int r9) {
            MessageSendPreview r1 = this.this$0;
            MessageCell r0 = new MessageCell(r1, this.val$context, r1.currentAccount, true, null, this.val$resourcesProvider);
            r0.setDelegate(new AnonymousClass1(this));
            return new RecyclerListView.Holder(r0);
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$11, reason: invalid class name */
    public class AnonymousClass11 extends FrameLayout {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass11(MessageSendPreview r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r3) {
            super.dispatchDraw(r3);
            MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).draw(r3);
            float r32 = MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).getProgress();
            if (r32 == (-2.0f)) goto L12;
            ChatActivityEnterView.SendButton r0 = MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0);
            if (r32 >= 0.0f) goto L7;
        L9:
            boolean r33 = false;
        L10:
            r0.setLoading(r33, -3.0f);
            goto L12
        L7:
            if (r32 >= 1.0f) goto L9;
            r33 = true;
        L12:
            if (MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).isIdle() == true) goto L15;
            invalidate();
            return;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$12, reason: invalid class name */
    public class AnonymousClass12 extends EmojiAnimationsOverlay {
        int[] messagePos;
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass12(MessageSendPreview r1, FrameLayout r2, int r3) {
            this.this$0 = r1;
            super(r2, r3);
            this.messagePos = new int[2];
        }

        @Override // org.telegram.ui.EmojiAnimationsOverlay
        public void layoutObject(EmojiAnimationsOverlay.DrawingObject r8) {
            if (r8 != null) goto L5;
            return;
        L5:
            if (MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0) == null) goto L9;
            r8.viewFound = true;
            float r0 = (EmojiAnimationsOverlay.getFilterWidth() * AndroidUtilities.density) / 1.3f;
            float r3 = r0 / 3.0f;
            r8.lastW = r3;
            r8.lastH = r3;
            r8.lastX = Utilities.clamp(MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).right - (0.75f * r0), AndroidUtilities.displaySize.x - r0, 0.0f);
            r8.lastY = MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).bottom - (r0 / 2.0f);
            return;
        L9:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0) != null) goto L11;
            return;
        L11:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).isAttachedToWindow() == true) goto L13;
            return;
        L13:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject() != null) goto L15;
            return;
        L15:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject().getId() != MessageSendPreview.m16147$$Nest$fgetmainMessageCellId(this.this$0)) goto L21;
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getLocationOnScreen(this.messagePos);
            r8.viewFound = true;
            float r02 = (EmojiAnimationsOverlay.getFilterWidth() * AndroidUtilities.density) / 1.3f;
            float r32 = r02 / 3.0f;
            r8.lastW = r32;
            r8.lastH = r32;
            float r1 = r02 / 2.0f;
            r8.lastX = Utilities.clamp((this.messagePos[0] + (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTimeX() * MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getScaleX())) - r1, AndroidUtilities.displaySize.x - r02, 0.0f);
            r8.lastY = (this.messagePos[1] + (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTimeY() * MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getScaleY())) - r1;
            return;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$13, reason: invalid class name */
    public class AnonymousClass13 extends ChatActivityEnterView.SendButton {
        final /* synthetic */ MessageSendPreview this$0;
        final /* synthetic */ boolean val$fillWhenClose;
        final /* synthetic */ ChatActivityEnterView.SendButton val$sendButton;

        public AnonymousClass13(MessageSendPreview r1, Context r2, int r3, Theme.ResourcesProvider r4, ChatActivityEnterView.SendButton r5, boolean r6) {
            this.this$0 = r1;
            this.val$sendButton = r5;
            this.val$fillWhenClose = r6;
            super(r2, r3, r4);
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public int getFillColor() {
            return this.val$sendButton.getFillColor();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInScheduleMode() {
            return this.val$sendButton.isInScheduleMode();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isInactive() {
            return this.val$sendButton.isInactive();
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean isOpen() {
            if (this.val$fillWhenClose == true) goto L5;
            return true;
        L5:
            if (MessageSendPreview.m16131$$Nest$fgetdismissing(this.this$0) == true) goto L8;
            return true;
        L8:
            if (super.isOpen() == true) goto L15;
            return false;
        L15:
            return true;
        }

        @Override // org.telegram.ui.Components.ChatActivityEnterView.SendButton
        public boolean shouldDrawBackground() {
            return this.val$sendButton.shouldDrawBackground();
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$14, reason: invalid class name */
    public class AnonymousClass14 extends ReactionsContainerLayout {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass14(MessageSendPreview r1, int r2, BaseFragment r3, Context r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r1, int r2) {
            super.onMeasure(r1, r2);
            setPivotX(getMeasuredWidth());
            setPivotY(getMeasuredHeight());
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$15, reason: invalid class name */
    public class AnonymousClass15 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ MessageSendPreview this$0;
        final /* synthetic */ BaseFragment val$fragment;

        public static /* synthetic */ void $r8$lambda$tyWKze7VD88usEKRXkm81_hG0mQ(BaseFragment r3) {
            BaseFragment.BottomSheetParams r0 = new BaseFragment.BottomSheetParams();
            r0.transitionFromLeft = true;
            r0.allowNestedScroll = false;
            r3.showAsSheet(new PremiumPreviewFragment("effect"), r0);
        }

        public AnonymousClass15(MessageSendPreview r1, BaseFragment r2) {
            this.this$0 = r1;
            this.val$fragment = r2;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r17, ReactionsLayoutInBubble.VisibleReaction r18, boolean r19, boolean r20) {
            ReactionsLayoutInBubble.VisibleReaction r1 = r18;
            if (r1 != null) goto L5;
            return;
        L5:
            if (MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0) == null) goto L100;
            boolean r3 = true;
            if (UserConfig.getInstance(this.this$0.currentAccount).isPremium() == false) goto L10;
        L12:
            boolean r2 = false;
        L13:
            ChatMessageCell r5 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0);
            MessageSendPreview r6 = this.this$0;
            if (r5 == null) goto L52;
            MessageObject r11 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(r6).getMessageObject();
            if (r11 == null) goto L101;
            TLRPC.Message r52 = r11.messageOwner;
            long r12 = r52.effect;
            long r14 = r1.effectId;
            if (r14 != r12) goto L21;
            r52.flags2 &= -5;
            r52.effect = 0;
            boolean r53 = true;
        L22:
            if (r2 == true) goto L39;
            ChatMessageCell r10 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0);
            MessageObject.GroupedMessages r62 = MessageSendPreview.m16173$$Nest$mgetValidGroupedMessage(this.this$0, r11);
            if (MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).size() > 1) goto L27;
            r3 = false;
        L27:
            long r192 = 0;
            long r7 = r12;
            r10.setMessageObject(r11, r62, r3, false, false);
            ReactionsContainerLayout r32 = MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0);
            if (r53 == false) goto L30;
            ReactionsLayoutInBubble.VisibleReaction r63 = null;
        L31:
            r32.setSelectedReactionAnimated(r63);
            if (MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow() != null) goto L34;
        L40:
            MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).clear();
            if (r53 == true) goto L43;
            MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).showAnimationForCell(MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0), 0, false, false);
        L43:
            if (r2 == false) goto L48;
            TLRPC.Message r13 = r11.messageOwner;
            r13.effect = r7;
            if (r7 != r192) goto L48;
            r13.flags2 &= -5;
        L48:
            if (MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0) == null) goto L50;
            MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).setEffect(r11.messageOwner.effect);
        L50:
            this.this$0.onEffectChange(r11.messageOwner.effect);
        L93:
            if (r2 == true) goto L95;
        L97:
            MessageSendPreview.m16140$$Nest$fgeteffectsView(this.this$0).invalidate();
            return;
        L95:
            if (this.val$fragment == null) goto L97;
            BulletinFactory.of(MessageSendPreview.m16127$$Nest$fgetcontainerView(this.this$0), this.this$0.resourcesProvider).createSimpleBulletin(R.raw.star_premium_2, AndroidUtilities.premiumText(LocaleController.getString(R.string.AnimatedEffectPremium), new MessageSendPreview$15$$ExternalSyntheticLambda0(this.val$fragment))).show();
            goto L97
        L34:
            if (MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().getSelectAnimatedEmojiDialog() == null) goto L40;
            SelectAnimatedEmojiDialog r33 = MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().getSelectAnimatedEmojiDialog();
            if (r53 == false) goto L38;
            r1 = null;
        L38:
            r33.setSelectedReaction(r1);
            MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().containerView.invalidate();
            goto L40
        L30:
            r63 = r1;
            goto L31
        L39:
            r192 = 0;
            r7 = r12;
            goto L40
        L21:
            r52.flags2 |= 4;
            r52.effect = r14;
            r53 = false;
            goto L22
        L101:
            return;
        L52:
            if (MessageSendPreview.m16123$$Nest$fgetcameraRect(r6) == null) goto L93;
            long r54 = r1.effectId;
            long r72 = MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0);
            MessageSendPreview r64 = this.this$0;
            if (r54 != r72) goto L56;
            MessageSendPreview.m16160$$Nest$fputeffectId(r64, 0);
            boolean r55 = true;
        L58:
            if (MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0) == null) goto L60;
            MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).setEffect(MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0));
        L60:
            MessageSendPreview r65 = this.this$0;
            r65.onEffectChange(MessageSendPreview.m16137$$Nest$fgeteffectId(r65));
            if (r2 == false) goto L63;
        L87:
            MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).clear();
            if (r55 == true) goto L93;
            TLRPC.TL_message r15 = new TLRPC.TL_message();
            r15.effect = MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0);
            if (MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0) == 0) goto L92;
            r15.flags2 |= 4;
        L92:
            MessageSendPreview.m16138$$Nest$fgeteffectOverlay(this.this$0).createDrawingObject(null, 0, null, new MessageObject(this.this$0.currentAccount, r15, false, false), 0, false, false, 0.0f, 0.0f, true);
            goto L93
        L63:
            if (MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0) != 0) goto L65;
            TLRPC.TL_availableEffect r66 = null;
        L67:
            if (MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0) != null) goto L69;
        L73:
            ReactionsLayoutInBubble.VisibleReaction r73 = null;
        L75:
            ReactionsContainerLayout r34 = MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0);
            if (r55 == false) goto L78;
            ReactionsLayoutInBubble.VisibleReaction r67 = r73;
        L79:
            r34.setSelectedReactionAnimated(r67);
            if (MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow() == null) goto L87;
            if (MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().getSelectAnimatedEmojiDialog() == null) goto L87;
            SelectAnimatedEmojiDialog r35 = MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().getSelectAnimatedEmojiDialog();
            if (r55 == false) goto L86;
            r1 = r73;
        L86:
            r35.setSelectedReaction(r1);
            MessageSendPreview.m16139$$Nest$fgeteffectSelector(this.this$0).getReactionsWindow().containerView.invalidate();
            goto L87
        L78:
            r67 = r1;
            goto L79
        L69:
            if (MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0) == 0) goto L74;
            if (r66 == null) goto L74;
            MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).set(Emoji.getEmojiDrawable(r66.emoticon), true);
        L74:
            r73 = null;
            MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).set(null, true);
            goto L75
        L65:
            r66 = MessagesController.getInstance(this.this$0.currentAccount).getEffect(MessageSendPreview.m16137$$Nest$fgeteffectId(this.this$0));
            goto L67
        L56:
            MessageSendPreview.m16160$$Nest$fputeffectId(r64, r1.effectId);
            r55 = false;
            goto L58
        L10:
            if (r1.premium == false) goto L12;
            r2 = true;
            goto L13
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$16, reason: invalid class name */
    public class AnonymousClass16 extends AnimatorListenerAdapter {
        final /* synthetic */ MessageSendPreview this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$animateOptions;
        final /* synthetic */ boolean val$open;

        public AnonymousClass16(MessageSendPreview r1, boolean r2, boolean r3, Runnable r4) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$animateOptions = r3;
            this.val$after = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            MessageSendPreview r52 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            MessageSendPreview.m16168$$Nest$fputopenProgress(r52, r0);
            MessageSendPreview.m16161$$Nest$fputfirstOpenFrame(this.this$0, false);
            MessageSendPreview.m16162$$Nest$fputfirstOpenFrame2(this.this$0, false);
            MessageSendPreview.m16140$$Nest$fgeteffectsView(this.this$0).setAlpha(MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            if (this.val$open == false) goto L10;
            MessageSendPreview.m16167$$Nest$fputopenInProgress(this.this$0, false);
            MessageSendPreview.m16169$$Nest$fputopening(this.this$0, false);
            MessageSendPreview.m16158$$Nest$fputclosing(this.this$0, false);
        L10:
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0) == null) goto L13;
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).setAlpha(1.0f);
        L13:
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0) == null) goto L16;
            MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).setVisibility(0);
        L16:
            if (MessageSendPreview.m16118$$Nest$fgetanchorSendButton(this.this$0) == null) goto L21;
            if (MessageSendPreview.m16156$$Nest$fgetsent(this.this$0) == true) goto L21;
            MessageSendPreview.m16118$$Nest$fgetanchorSendButton(this.this$0).setAlpha(1.0f);
        L21:
            if (this.val$open == true) goto L26;
            if (MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0) == null) goto L26;
            MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).setAlpha(0.0f);
        L26:
            if (this.val$animateOptions == false) goto L28;
        L30:
            MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).invalidate();
            MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).setAlpha(MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            MessageSendPreview.m16157$$Nest$fgetwindowView(this.this$0).invalidate();
            MessageSendPreview.m16127$$Nest$fgetcontainerView(this.this$0).invalidate();
            if (this.val$after != null) goto L33;
            return;
        L33:
            if (this.val$open == true) goto L41;
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0) == null) goto L41;
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).isAttachedToWindow() == false) goto L41;
            MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).post(this.val$after);
            return;
        L41:
            if (this.val$open == false) goto L43;
        L48:
            AndroidUtilities.runOnUIThread(this.val$after);
            return;
        L43:
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0) == null) goto L48;
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).isAttachedToWindow() == false) goto L48;
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).post(this.val$after);
            return;
        L28:
            if (MessageSendPreview.m16152$$Nest$fgetoptionsView(this.this$0) == null) goto L30;
            MessageSendPreview.m16152$$Nest$fgetoptionsView(this.this$0).setAlpha(MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            goto L30
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$2, reason: invalid class name */
    public class AnonymousClass2 extends SizeNotifierFrameLayout {
        private Paint backgroundPaint;
        int chatListViewTy;
        private GradientClip clip;
        final int[] destCellPos;
        private AnimatedFloat destCellY;
        final int[] pos;
        final int[] pos2;
        final /* synthetic */ MessageSendPreview this$0;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        /* renamed from: $r8$lambda$n1QHAz45tG0ls8OKv-lgSkQAz74, reason: not valid java name */
        public static /* synthetic */ Boolean m16176$r8$lambda$n1QHAz45tG0ls8OKvlgSkQAz74(AnonymousClass2 r0, Canvas r1, float r2) {
            return r0.lambda$dispatchDraw$0(r1, r2);
        }

        public AnonymousClass2(MessageSendPreview r7, Context r8, Theme.ResourcesProvider r9) {
            this.this$0 = r7;
            this.val$resourcesProvider = r9;
            super(r8);
            this.pos = new int[2];
            this.pos2 = new int[2];
            this.chatListViewTy = 0;
            this.destCellPos = new int[2];
            this.clip = new GradientClip();
            this.destCellY = new AnimatedFloat(0, 100, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.backgroundPaint = new Paint(1);
        }

        private /* synthetic */ Boolean lambda$dispatchDraw$0(Canvas r4, float r5) {
            r4.save();
            r4.translate(MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getX(), MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getY() - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getScrollY());
            float r52 = r5 / MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getTextSize();
            r4.scale(r52, r52, MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft(), MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingTop());
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).draw(r4);
            r4.restore();
            return Boolean.TRUE;
        }

        @Override // org.telegram.ui.Components.SizeNotifierFrameLayout, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r28) {
            if (MessageSendPreview.m16150$$Nest$fgetopenInProgress(this.this$0) == true) goto L5;
        L92:
            Canvas r1 = r28;
            float r17 = 255.0f;
        L94:
            if (MessageSendPreview.m16150$$Nest$fgetopenInProgress(this.this$0) == true) goto L96;
        L113:
            super.dispatchDraw(r28);
            if (MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0) != null) goto L116;
            return;
        L116:
            if (MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0) != null) goto L118;
            MessageSendPreview.m16159$$Nest$fputeffectDrawable(this.this$0, new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(this, AndroidUtilities.dp(24.0f), 23));
        L118:
            Rect r2 = AndroidUtilities.rectTmp2;
            r2.set((int) ((MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).right - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(24.0f)), (int) ((MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).bottom - AndroidUtilities.dp(12.0f)) - AndroidUtilities.dp(24.0f)), (int) (MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).right - AndroidUtilities.dp(12.0f)), (int) (MessageSendPreview.m16123$$Nest$fgetcameraRect(this.this$0).bottom - AndroidUtilities.dp(12.0f)));
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(r2);
            r3.inset(-AndroidUtilities.dp(12.0f), -AndroidUtilities.dp(6.0f));
            float r4 = r3.height() / 2.0f;
            this.backgroundPaint.setColor(503316480);
            this.backgroundPaint.setAlpha((int) ((MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).isNotEmpty() * 30.0f) * MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0)));
            r1.drawRoundRect(r3, r4, r4, this.backgroundPaint);
            MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).setBounds(r2);
            MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).setAlpha((int) (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) * r17));
            MessageSendPreview.m16136$$Nest$fgeteffectDrawable(this.this$0).draw(r1);
            return;
        L96:
            if (MessageSendPreview.m16142$$Nest$fgetfirstOpenFrame2(this.this$0) == true) goto L98;
            char r5 = 0;
        L102:
            r1.save();
            r1.translate(AndroidUtilities.lerp((MessageSendPreview.m16155$$Nest$fgetsendButtonInitialPosition(this.this$0)[r5] - (MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getWidth() - MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).width(MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getHeight()))) + AndroidUtilities.dp(6.0f), MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getX(), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0)), AndroidUtilities.lerp(MessageSendPreview.m16155$$Nest$fgetsendButtonInitialPosition(this.this$0)[1], MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getY(), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0)));
            if (MessageSendPreview.m16126$$Nest$fgetclosing(this.this$0) == true) goto L105;
        L107:
            MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).draw(r1);
            if (MessageSendPreview.m16126$$Nest$fgetclosing(this.this$0) == true) goto L110;
        L112:
            r1.restore();
            goto L113
        L110:
            if (MessageSendPreview.m16156$$Nest$fgetsent(this.this$0) == false) goto L112;
            r1.restore();
            goto L112
        L105:
            if (MessageSendPreview.m16156$$Nest$fgetsent(this.this$0) == false) goto L107;
            r1.saveLayerAlpha(0.0f, 0.0f, MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getWidth(), MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0).getHeight(), (int) (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) * r17), 31);
            goto L107
        L98:
            if (MessageSendPreview.m16118$$Nest$fgetanchorSendButton(this.this$0) == null) goto L100;
            MessageSendPreview.m16118$$Nest$fgetanchorSendButton(this.this$0).setAlpha(0.0f);
        L100:
            r5 = 0;
            MessageSendPreview.m16162$$Nest$fputfirstOpenFrame2(this.this$0, false);
            goto L102
        L5:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0) == null) goto L92;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getCurrentPosition() != null) goto L92;
            if (MessageSendPreview.m16141$$Nest$fgetfirstOpenFrame(this.this$0) == false) goto L15;
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0) == null) goto L13;
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).setAlpha(0.0f);
        L13:
            MessageSendPreview.m16161$$Nest$fputfirstOpenFrame(this.this$0, false);
        L15:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject() != null) goto L17;
        L19:
            boolean r12 = false;
        L20:
            ChatMessageCell r22 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0);
            if (r12 == false) goto L24;
            float r23 = r22.getPhotoImage().getImageX();
        L23:
            float r122 = r23;
            MessageSendPreview r24 = this.this$0;
            if (r12 == false) goto L29;
            float r13 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(r24).getPhotoImage().getImageY();
        L28:
            float r132 = r13;
            float r14 = (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getX() + MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getX()) + r122;
            float r25 = (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getY() + MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getY()) + r132;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject() == null) goto L33;
            TextPaint r32 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject().getTextPaint();
        L34:
            float r142 = r32.getTextSize();
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0) == null) goto L38;
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getLocationOnScreen(this.pos);
            float r33 = this.pos[0] + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft();
            float r42 = (this.pos[1] + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingTop()) - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getScrollY();
            float r52 = MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getTextSize();
            int r6 = this.pos[1];
            float r7 = r6;
            float r62 = r6 + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getMeasuredHeight();
            r14 = AndroidUtilities.lerp(r33, r14, MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            r25 = AndroidUtilities.lerp(r42, r25, MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            float r34 = AndroidUtilities.lerp(r52, r142, MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
        L37:
            float r15 = r14;
            float r16 = MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0) == null) goto L42;
            r7 = MessageSendPreview.m16130$$Nest$fgetdestClipTop(this.this$0);
        L42:
            r17 = 255.0f;
            float r18 = AndroidUtilities.lerp(r7, MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getY() + (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight() * (1.0f - MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getScaleY())), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            if (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).canScrollVertically(-1) == false) goto L45;
            float r43 = 1.0f;
        L46:
            float r44 = AndroidUtilities.lerp(0.0f, r43, MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0) == null) goto L49;
            r62 = MessageSendPreview.m16129$$Nest$fgetdestClipBottom(this.this$0);
        L49:
            float r53 = AndroidUtilities.lerp(r62, MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getY() + MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight(), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            if (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).canScrollVertically(1) == false) goto L52;
            float r63 = 1.0f;
        L53:
            float r64 = AndroidUtilities.lerp(0.0f, r63, MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            float r72 = r34;
            float r222 = r25;
            r28.saveLayerAlpha(0.0f, r18 + 1.0f, getWidth(), r53 - 1.0f, 255, 31);
            if (MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0) == null) goto L66;
            r1 = r28;
            r1.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) ((1.0f - r16) * 255.0f), 31);
            r1.translate(r15, r222);
            r1.translate((-MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getX()) - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft(), ((-MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getY()) - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingTop()) + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getScrollY());
            float r26 = MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getAlpha();
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).setAlpha(1.0f);
            if (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) < 0.001f) goto L58;
            float r10 = r26;
        L63:
            if (MessageSendPreview.m16132$$Nest$fgetdrawEditText(this.this$0) == null) goto L65;
            MessageSendPreview.m16132$$Nest$fgetdrawEditText(this.this$0).run(r1, new MessageSendPreview$2$$ExternalSyntheticLambda0(this, r1, r72));
        L65:
            MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).setAlpha(r10);
            r1.restore();
        L67:
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().ignoreAlpha = true;
            if (MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0) != null) goto L73;
            r1.saveLayerAlpha(0.0f, 0.0f, getWidth(), getHeight(), (int) (r16 * 255.0f), 31);
            r1.translate(r15, r222);
            r1.translate(-r122, -r132);
            float r27 = AndroidUtilities.lerp(1.0f, MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getScaleX(), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            r1.scale(r27, r27, (-MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getX()) + MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getWidth(), (-MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getY()) + MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight());
            float r35 = r72 / r142;
            r1.scale(r35, r35, r122, r132);
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawBackgroundInParent() == false) goto L72;
            r1.save();
            r1.translate(0.0f, MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getPaddingTop());
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawBackgroundInternal(r1, true);
            r1.restore();
        L72:
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).draw(r1);
            r1.restore();
        L91:
            r1.save();
            RectF r29 = AndroidUtilities.rectTmp;
            r29.set(0.0f, r18, getWidth(), AndroidUtilities.dp(14.0f) + r18);
            this.clip.draw(r1, r29, true, r44);
            r29.set(0.0f, r53 - AndroidUtilities.dp(14.0f), getWidth(), r53);
            this.clip.draw(r1, r29, false, r64);
            r1.restore();
            r1.restore();
            goto L94
        L73:
            MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getLocationInWindow(this.pos2);
            if ((MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getParent() instanceof View) == false) goto L76;
            int r210 = (int) ((View) MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getParent()).getTranslationY();
        L77:
            int r36 = this.chatListViewTy;
            if (r36 <= r210) goto L82;
            char r19 = 1;
            if ((this.destCellPos[1] - this.pos2[1]) > r36) goto L84;
        L83:
            int[] r37 = this.destCellPos;
            int[] r45 = this.pos2;
            r37[0] = r45[0];
            r37[r19] = r45[r19];
        L84:
            this.chatListViewTy = r210;
            float r211 = AndroidUtilities.lerp(MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getX() + MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getX(), this.destCellPos[0], 1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            float r38 = AndroidUtilities.lerp(MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getY() + MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getY(), this.destCellPos[1], 1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            r1.save();
            r1.translate(r211, r38);
            float r212 = AndroidUtilities.lerp(1.0f, MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getScaleX(), MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            r1.scale(r212, r212, (-MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getX()) + MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getWidth(), (-MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getY()) + MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight());
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().animateChangeProgress = 1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().deltaLeft = MessageSendPreview.m16124$$Nest$fgetcellDelta(this.this$0).left * MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().deltaTop = MessageSendPreview.m16124$$Nest$fgetcellDelta(this.this$0).top * MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().deltaRight = MessageSendPreview.m16124$$Nest$fgetcellDelta(this.this$0).right * MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().deltaBottom = MessageSendPreview.m16124$$Nest$fgetcellDelta(this.this$0).bottom * MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).setTimeAlpha(1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0));
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawBackgroundInParent() == false) goto L87;
            r1.saveLayerAlpha(0.0f, 0.0f, MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getWidth(), MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getHeight(), (int) (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) * 255.0f), 31);
            r1.translate(0.0f, MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getPaddingTop());
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawBackgroundInternal(r1, true);
            r1.restore();
            r1.saveLayerAlpha(0.0f, 0.0f, MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getWidth(), MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getHeight(), (int) ((1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0)) * 255.0f), 31);
            r1.translate(0.0f, MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).getPaddingTop());
            MessageSendPreview.m16128$$Nest$fgetdestCell(this.this$0).drawBackgroundInternal(r1, true);
            r1.restore();
        L87:
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).draw(r1);
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getTransitionParams().animateBackgroundBoundsInner == false) goto L90;
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawNamesLayout(r1, 1.0f);
            MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).drawTime(r1, 1.0f - MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0), true);
        L90:
            r1.restore();
            goto L91
        L82:
            r19 = 1;
            goto L83
        L76:
            r210 = 0;
            goto L77
        L58:
            if (MessageSendPreview.m16133$$Nest$fgetdrawEditTextBackground(this.this$0) == null) goto L60;
            r1.save();
            r1.translate(0.0f, MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getY());
            r10 = r26;
            r1.saveLayerAlpha(MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getX() + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft(), 0.0f, ((MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getX() + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft()) + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getWidth()) - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingRight(), MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getHeight(), (int) ((1.0f - (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) / 0.1f)) * 255.0f), 31);
            MessageSendPreview.m16133$$Nest$fgetdrawEditTextBackground(this.this$0).run(r1);
            r1.restore();
            r1.restore();
            goto L63
        L60:
            r10 = r26;
            MessageSendPreview.m16135$$Nest$fgeteditTextBackgroundPaint(this.this$0).setColor(Theme.getColor(Theme.key_chat_messagePanelBackground, this.val$resourcesProvider));
            MessageSendPreview.m16135$$Nest$fgeteditTextBackgroundPaint(this.this$0).setAlpha((int) (MessageSendPreview.m16135$$Nest$fgeteditTextBackgroundPaint(this.this$0).getAlpha() * (1.0f - (MessageSendPreview.m16151$$Nest$fgetopenProgress(this.this$0) / 0.1f))));
            r1.drawRect(MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft(), MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getY(), ((MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getX() + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingLeft()) + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getWidth()) - MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getPaddingRight(), MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getY() + MessageSendPreview.m16134$$Nest$fgeteditText(this.this$0).getHeight(), MessageSendPreview.m16135$$Nest$fgeteditTextBackgroundPaint(this.this$0));
            goto L63
        L66:
            r1 = r28;
            goto L67
        L52:
            r63 = 0.0f;
            goto L53
        L45:
            r43 = 0.0f;
            goto L46
        L38:
            r62 = getHeight();
            r7 = 0.0f;
            r34 = r142;
            goto L37
        L33:
            r32 = Theme.chat_msgTextPaint;
            goto L34
        L29:
            r13 = MessageSendPreview.m16146$$Nest$fgetmainMessageCell(r24).getTextY();
            goto L28
        L24:
            r23 = r22.getTextX();
            goto L23
        L17:
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getMessageObject().type != 15) goto L19;
            r12 = true;
            goto L20
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r2, View r3, long r4) {
            if (MessageSendPreview.m16150$$Nest$fgetopenInProgress(this.this$0) == false) goto L15;
            if (r3 != MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0)) goto L7;
            return false;
        L7:
            if (r3 != MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0)) goto L15;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0) == null) goto L15;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getCurrentPosition() != null) goto L15;
            return false;
        L15:
            return super.drawChild(r2, r3, r4);
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$3, reason: invalid class name */
    public class AnonymousClass3 implements OnApplyWindowInsetsListener {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass3(MessageSendPreview r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            MessageSendPreview.m16163$$Nest$fputinsets(this.this$0, AndroidUtilities.getDefaultWindowInsets(r5, false));
            MessageSendPreview.m16127$$Nest$fgetcontainerView(this.this$0).setPadding(MessageSendPreview.m16144$$Nest$fgetinsets(this.this$0).left, MessageSendPreview.m16144$$Nest$fgetinsets(this.this$0).top, MessageSendPreview.m16144$$Nest$fgetinsets(this.this$0).right, MessageSendPreview.m16144$$Nest$fgetinsets(this.this$0).bottom);
            MessageSendPreview.m16157$$Nest$fgetwindowView(this.this$0).requestLayout();
            return WindowInsetsCompat.CONSUMED;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$4, reason: invalid class name */
    public class AnonymousClass4 extends RecyclerListView {
        private final AnimatedFloat bottom;
        private final GradientClip clip;
        private final ArrayList<MessageObject.GroupedMessages> drawingGroups;
        final /* synthetic */ MessageSendPreview this$0;
        private final AnimatedFloat top;

        public AnonymousClass4(MessageSendPreview r9, Context r10, Theme.ResourcesProvider r11) {
            this.this$0 = r9;
            super(r10, r11);
            this.drawingGroups = new ArrayList(10);
            CubicBezierInterpolator r6 = CubicBezierInterpolator.EASE_OUT_QUINT;
            this.top = new AnimatedFloat(this, 0, 360, r6);
            this.bottom = new AnimatedFloat(this, 0, 360, r6);
            this.clip = new GradientClip();
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
            if (r42 >= 3) goto L117;
            this.drawingGroups.clear();
            if (r42 == 2) goto L18;
        L20:
            int r63 = r3 ? 1 : 0;
        L21:
            boolean r7 = true;
            if (r63 >= r1) goto L82;
            View r8 = MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getChildAt(r63);
            if ((r8 instanceof ChatMessageCell) == false) goto L81;
            ChatMessageCell r9 = (ChatMessageCell) r8;
            if (r8.getY() > MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight()) goto L81;
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
            if (this.drawingGroups.contains(r82) == true) goto L56;
            MessageObject.GroupedMessages.TransitionParams r72 = r82.transitionParams;
            r72.left = r3 ? 1 : 0;
            r72.top = r3 ? 1 : 0;
            r72.right = r3 ? 1 : 0;
            r72.bottom = r3 ? 1 : 0;
            r72.pinnedBotton = r3;
            r72.pinnedTop = r3;
            r72.cell = r9;
            this.drawingGroups.add(r82);
        L56:
            r82.transitionParams.pinnedTop = r9.isPinnedTop();
            r82.transitionParams.pinnedBotton = r9.isPinnedBottom();
            int r73 = (int) (r9.getX() + r9.getBackgroundDrawableLeft());
            int r10 = (int) (r9.getX() + r9.getBackgroundDrawableRight());
            int r11 = (int) ((r9.getY() + r9.getPaddingTop()) + r9.getBackgroundDrawableTop());
            int r12 = (int) ((r9.getY() + r9.getPaddingTop()) + r9.getBackgroundDrawableBottom());
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
            if (r52 >= this.drawingGroups.size()) goto L116;
            MessageObject.GroupedMessages r64 = this.drawingGroups.get(r52);
            if (r64 == null) goto L115;
            float r84 = r64.transitionParams.cell.getNonAnimationTranslationX(r7);
            MessageObject.GroupedMessages.TransitionParams r95 = r64.transitionParams;
            float r102 = (r95.left + r84) + r95.offsetLeft;
            float r112 = r95.top + r95.offsetTop;
            float r122 = (r95.right + r84) + r95.offsetRight;
            float r85 = r95.bottom + r95.offsetBottom;
            if (r112 >= (-AndroidUtilities.dp(20.0f))) goto L92;
            r112 = -AndroidUtilities.dp(20.0f);
        L92:
            if (r85 <= (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(20.0f))) goto L95;
            r85 = MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(20.0f);
        L95:
            if (r64.transitionParams.cell.getScaleX() == 1.0f) goto L97;
        L100:
            boolean r96 = r7;
        L102:
            if (r96 == false) goto L104;
            r28.save();
            float r16 = 2.0f;
            Canvas r13 = r28;
            r13.scale(r64.transitionParams.cell.getScaleX(), r64.transitionParams.cell.getScaleY(), r102 + ((r122 - r102) / 2.0f), r112 + ((r85 - r112) / 2.0f));
        L105:
            MessageObject.GroupedMessages.TransitionParams r32 = r64.transitionParams;
            r32.cell.drawBackground(r13, (int) r102, (int) r112, (int) r122, (int) r85, r32.pinnedTop, r32.pinnedBotton, false, 0);
            MessageObject.GroupedMessages.TransitionParams r2 = r64.transitionParams;
            r2.cell = null;
            r2.drawCaptionLayout = r64.hasCaption;
            if (r96 == false) goto L115;
            r28.restore();
            int r22 = 0;
        L108:
            if (r22 >= r1) goto L115;
            View r75 = MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getChildAt(r22);
            if ((r75 instanceof ChatMessageCell) == false) goto L114;
            ChatMessageCell r97 = (ChatMessageCell) r75;
            if (r97.getCurrentMessagesGroup() != r64) goto L114;
            int r132 = r97.getLeft();
            int r98 = r97.getTop();
            r75.setPivotX((r102 - r132) + ((r122 - r102) / r16));
            r75.setPivotY((r112 - r98) + ((r85 - r112) / r16));
        L114:
            r22 = r22 + 1;
            goto L108
        L104:
            r16 = 2.0f;
            r13 = r28;
            goto L105
        L97:
            if (r64.transitionParams.cell.getScaleY() != 1.0f) goto L100;
            r96 = r3;
        L115:
            r52 = r52 + 1;
            r3 = false;
            r7 = true;
        L116:
            r42 = r42 + 1;
            r3 = false;
            goto L14
        L18:
            if (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).isFastScrollAnimationRunning() == true) goto L20;
        }

        private void drawChatForegroundElements(Canvas r21) {
            int r1 = getChildCount();
            MessageObject.GroupedMessages r5 = null;
            int r4 = 0;
        L3:
            if (r4 >= r1) goto L14;
            View r6 = getChildAt(r4);
            if ((r6 instanceof ChatMessageCell) == false) goto L13;
            ChatMessageCell r62 = (ChatMessageCell) r6;
            MessageObject.GroupedMessages r7 = r62.getCurrentMessagesGroup();
            if (r7 == null) goto L10;
            if (r7 == r5) goto L13;
        L10:
            if (r7 != null) goto L12;
            this.this$0.drawStarsPrice(r21, r62.getBoundsLeft(), r62.getY(), r62.getBoundsRight(), r62.getY() + r62.getHeight());
        L12:
            r5 = r7;
        L13:
            r4 = r4 + 1;
            goto L3
        L14:
            int r42 = 0;
        L16:
            if (r42 >= 3) goto L95;
            this.drawingGroups.clear();
            if (r42 == 2) goto L20;
        L22:
            int r63 = 0;
        L24:
            if (r63 >= r1) goto L84;
            View r8 = MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getChildAt(r63);
            if ((r8 instanceof ChatMessageCell) == false) goto L83;
            ChatMessageCell r9 = (ChatMessageCell) r8;
            if (r8.getY() > MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getHeight()) goto L83;
            if ((r8.getY() + r8.getHeight()) < 0.0f) goto L83;
            MessageObject.GroupedMessages r82 = r9.getCurrentMessagesGroup();
            if (r82 == null) goto L83;
            if (r42 == 0) goto L36;
        L37:
            if (r42 == 1) goto L39;
        L41:
            if (r42 == 0) goto L43;
        L44:
            if (r42 == 1) goto L46;
        L48:
            if (r42 == 2) goto L50;
        L51:
            if (r42 == 2) goto L56;
            if (r9.willRemovedAfterAnimation() == true) goto L83;
        L56:
            if (this.drawingGroups.contains(r82) == true) goto L58;
            MessageObject.GroupedMessages.TransitionParams r72 = r82.transitionParams;
            r72.left = 0;
            r72.top = 0;
            r72.right = 0;
            r72.bottom = 0;
            r72.pinnedBotton = false;
            r72.pinnedTop = false;
            r72.cell = r9;
            this.drawingGroups.add(r82);
        L58:
            r82.transitionParams.pinnedTop = r9.isPinnedTop();
            r82.transitionParams.pinnedBotton = r9.isPinnedBottom();
            int r73 = (int) (r9.getX() + r9.getBackgroundDrawableLeft());
            int r10 = (int) (r9.getX() + r9.getBackgroundDrawableRight());
            int r11 = (int) ((r9.getY() + r9.getPaddingTop()) + r9.getBackgroundDrawableTop());
            int r12 = (int) ((r9.getY() + r9.getPaddingTop()) + r9.getBackgroundDrawableBottom());
            if ((r9.getCurrentPosition().flags & 4) != 0) goto L62;
            r11 = r11 - AndroidUtilities.dp(10.0f);
        L62:
            if ((r9.getCurrentPosition().flags & 8) != 0) goto L65;
            r12 = r12 + AndroidUtilities.dp(10.0f);
        L65:
            if (r9.willRemovedAfterAnimation() == false) goto L67;
            r82.transitionParams.cell = r9;
        L67:
            MessageObject.GroupedMessages.TransitionParams r83 = r82.transitionParams;
            int r92 = r83.top;
            if (r92 == 0) goto L70;
            if (r11 < r92) goto L70;
        L71:
            int r93 = r83.bottom;
            if (r93 == 0) goto L74;
            if (r12 > r93) goto L74;
        L75:
            int r94 = r83.left;
            if (r94 == 0) goto L78;
            if (r73 < r94) goto L78;
        L79:
            int r74 = r83.right;
            if (r74 == 0) goto L82;
            if (r10 <= r74) goto L83;
        L82:
            r83.right = r10;
        L78:
            r83.left = r73;
        L74:
            r83.bottom = r12;
        L70:
            r83.top = r11;
            goto L71
        L50:
            if (r9.willRemovedAfterAnimation() == false) goto L83;
        L46:
            if (r9.getMessageObject().deleted == true) goto L48;
        L43:
            if (r9.getMessageObject().deleted == true) goto L83;
        L39:
            if (r82.transitionParams.drawBackgroundForDeletedItems == true) goto L41;
        L36:
            if (r82.messages.size() != 1) goto L37;
        L83:
            r63 = r63 + 1;
            goto L24
        L84:
            int r52 = 0;
        L86:
            if (r52 >= this.drawingGroups.size()) goto L94;
            MessageObject.GroupedMessages r64 = this.drawingGroups.get(r52);
            float r84 = r64.transitionParams.cell.getNonAnimationTranslationX(true);
            MessageObject.GroupedMessages.TransitionParams r95 = r64.transitionParams;
            float r16 = (r95.left + r84) + r95.offsetLeft;
            float r102 = r95.top + r95.offsetTop;
            float r18 = (r95.right + r84) + r95.offsetRight;
            float r85 = r95.bottom + r95.offsetBottom;
            if (r102 >= (-AndroidUtilities.dp(20.0f))) goto L90;
            r102 = -AndroidUtilities.dp(20.0f);
        L90:
            float r17 = r102;
            if (r85 <= (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(20.0f))) goto L93;
            r85 = MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).getMeasuredHeight() + AndroidUtilities.dp(20.0f);
        L93:
            this.this$0.drawStarsPrice(r21, r16, r17, r18, r85);
            r64.transitionParams.cell = null;
            r52 = r52 + 1;
        L94:
            r42 = r42 + 1;
            goto L16
        L20:
            if (MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).isFastScrollAnimationRunning() == true) goto L22;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r10) {
            MessageSendPreview.m16175$$Nest$mupdateMessagesVisiblePart(this.this$0);
            r10.saveLayerAlpha(0.0f, getScrollY() + 1, getWidth(), (getScrollY() + getHeight()) - 1, 255, 31);
            r10.save();
            drawChatBackgroundElements(r10);
            super.dispatchDraw(r10);
            drawChatForegroundElements(r10);
            r10.save();
            float r102 = this.top.set(canScrollVertically(-1));
            float r0 = this.bottom.set(canScrollVertically(1));
            RectF r3 = AndroidUtilities.rectTmp;
            r3.set(0.0f, getScrollY(), getWidth(), getScrollY() + AndroidUtilities.dp(14.0f));
            this.clip.draw(r10, r3, true, r102);
            r3.set(0.0f, (getScrollY() + getHeight()) - AndroidUtilities.dp(14.0f), getWidth(), getScrollY() + getHeight());
            this.clip.draw(r10, r3, false, r0);
            r10.restore();
            r10.restore();
            r10.restore();
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup
        public boolean drawChild(Canvas r8, View r9, long r10) {
            if (MessageSendPreview.m16150$$Nest$fgetopenInProgress(this.this$0) == false) goto L14;
            if (r9 != MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0)) goto L11;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0) == null) goto L11;
            if (MessageSendPreview.m16146$$Nest$fgetmainMessageCell(this.this$0).getCurrentPosition() != null) goto L11;
        L12:
            return false;
        L11:
            if (r9 == MessageSendPreview.m16154$$Nest$fgetsendButton(this.this$0)) goto L12;
        L14:
            if ((r9 instanceof ChatMessageCell) == false) goto L52;
            ChatMessageCell r0 = (ChatMessageCell) r9;
            r0.setInvalidatesParent(true);
            r0.drawCheckBox(r8);
            r8.save();
            r8.translate(r0.getX(), r0.getY());
            r8.scale(r0.getScaleX(), r0.getScaleY(), r0.getPivotX(), r0.getPivotY());
            if (r0.drawBackgroundInParent() == true) goto L18;
        L20:
            r8.restore();
            boolean r7 = super.drawChild(r8, r9, r10);
            r8.save();
            r8.translate(r0.getX(), r0.getY() + r0.getPaddingTop());
            r8.scale(r0.getScaleX(), r0.getScaleY(), r0.getPivotX(), r0.getPivotY());
            if (r0.getCurrentPosition() == null) goto L32;
            if ((r0.getCurrentPosition().flags & r0.captionFlag()) == 0) goto L27;
            if ((r0.getCurrentPosition().flags & 1) == 0) goto L27;
        L30:
            r0.drawCaptionLayout(r8, false, r0.getAlpha());
        L27:
            if (r0.getCurrentMessagesGroup() == null) goto L32;
            if (r0.getCurrentMessagesGroup().isDocuments == true) goto L30;
        L32:
            if (r0.getCurrentPosition() == null) goto L43;
            if ((r0.getCurrentPosition().flags & 8) == 0) goto L38;
            if ((r0.getCurrentPosition().flags & 1) == 0) goto L38;
        L41:
            r0.drawReactionsLayout(r8, r0.getAlpha(), null);
            r0.drawCommentLayout(r8, r0.getAlpha());
        L38:
            if (r0.getCurrentMessagesGroup() == null) goto L43;
            if (r0.getCurrentMessagesGroup().isDocuments == true) goto L41;
        L43:
            if (r0.getCurrentPosition() == null) goto L46;
            r0.drawNamesLayout(r8, r0.getAlpha());
        L46:
            if (r0.getCurrentPosition() != null) goto L48;
        L49:
            r0.drawTime(r8, r0.getAlpha(), true);
        L50:
            r0.drawOutboundsContent(r8);
            r0.getTransitionParams().recordDrawingStatePreview();
            r8.restore();
            r0.setInvalidatesParent(false);
            return r7;
        L48:
            if (r0.getCurrentPosition().last == false) goto L50;
        L18:
            if (r0.getCurrentPosition() != null) goto L20;
            r8.save();
            r8.translate(0.0f, r0.getPaddingTop());
            r0.drawBackgroundInternal(r8, true);
            r8.restore();
            goto L20
        L52:
            return true;
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r5, int r6, int r7, int r8, int r9) {
            int r0 = 0;
        L4:
            if (r0 >= getChildCount()) goto L11;
            View r1 = getChildAt(r0);
            if (r1.getTop() == 0) goto L10;
            if ((r1 instanceof MessageCell) == false) goto L10;
            MessageCell r2 = (MessageCell) r1;
            r2.top = r1.getTop();
            r2.bottom = r1.getBottom();
            MessageCell.m16178$$Nest$fputpastId(r2, r2.getMessageObject().getId());
        L10:
            r0 = r0 + 1;
            goto L4
        L11:
            super.onLayout(r5, r6, r7, r8, r9);
        }

        @Override // org.telegram.ui.Components.RecyclerListView, androidx.recyclerview.widget.RecyclerView, android.view.View
        public void onMeasure(int r5, int r6) {
            if (MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).isEmpty() == false) goto L5;
            float r62 = -6.0f;
        L6:
            int r63 = AndroidUtilities.dp(r62);
            if (MessageSendPreview.m16152$$Nest$fgetoptionsView(this.this$0) != null) goto L9;
            int r0 = 0;
        L10:
            super.onMeasure(r5, View.MeasureSpec.makeMeasureSpec(Math.max(0, ((AndroidUtilities.displaySize.y - (r63 + r0)) - AndroidUtilities.dp(8.0f)) - MessageSendPreview.m16144$$Nest$fgetinsets(this.this$0).top), Integer.MIN_VALUE));
            int r52 = Math.max(MessageSendPreview.m16172$$Nest$mgetSendButtonWidth(this.this$0) + AndroidUtilities.dp(12.0f), -((MessageSendPreview.m16155$$Nest$fgetsendButtonInitialPosition(this.this$0)[0] + AndroidUtilities.dp(7.0f)) - getMeasuredWidth()));
            int r02 = MessageSendPreview.m16149$$Nest$fgetmessageObjectsWidth(this.this$0);
            int r2 = getMeasuredWidth() - r52;
            if (MessageSendPreview.m16143$$Nest$fgetgroupedMessagesMap(this.this$0).isEmpty() == false) goto L13;
            int r3 = 0;
        L14:
            int r03 = Math.max(0, r02 - (r2 - AndroidUtilities.dp(r3 + 8)));
            float r1 = Math.max(1, getMeasuredWidth() - r52) / Math.max(1, ((getMeasuredWidth() - r52) - AndroidUtilities.dp(8.0f)) + r03);
            setPivotX(getMeasuredWidth());
            setPivotY(getMeasuredHeight());
            setScaleX(r1);
            setScaleY(r1);
            return;
        L13:
            r3 = 40;
            goto L14
        L9:
            r0 = MessageSendPreview.m16152$$Nest$fgetoptionsView(this.this$0).getMeasuredHeight();
            goto L10
        L5:
            r62 = 48.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$5, reason: invalid class name */
    public class AnonymousClass5 extends RecyclerView.OnScrollListener {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass5(MessageSendPreview r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            MessageSendPreview.m16125$$Nest$fgetchatListView(this.this$0).invalidate();
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$6, reason: invalid class name */
    public class AnonymousClass6 extends ChatListItemAnimator {
        Runnable finishRunnable;
        final /* synthetic */ MessageSendPreview this$0;

        public static /* synthetic */ void $r8$lambda$4Rou5S6EB8CSJmubfzCBJfZvH8g(AnonymousClass6 r0) {
            r0.lambda$endAnimations$1();
        }

        /* renamed from: $r8$lambda$MFebQz5mFjy-5ZRe083jzne5Ttc, reason: not valid java name */
        public static /* synthetic */ void m16177$r8$lambda$MFebQz5mFjy5ZRe083jzne5Ttc(AnonymousClass6 r0) {
            r0.lambda$onAllAnimationsDone$0();
        }

        public AnonymousClass6(MessageSendPreview r1, ChatActivity r2, RecyclerListView r3, Theme.ResourcesProvider r4) {
            this.this$0 = r1;
            super(r2, r3, r4);
        }

        private /* synthetic */ void lambda$endAnimations$1() {
            this.finishRunnable = null;
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.d("chatItemAnimator enable notifications");
            return;
        }

        private /* synthetic */ void lambda$onAllAnimationsDone$0() {
            this.finishRunnable = null;
            if (BuildVars.LOGS_ENABLED == false) goto L6;
            FileLog.d("chatItemAnimator enable notifications");
            return;
        }

        @Override // androidx.recyclerview.widget.DefaultItemAnimator
        public void checkIsRunning() {
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
        public void endAnimations() {
            super.endAnimations();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
        L5:
            MessageSendPreview$6$$ExternalSyntheticLambda1 r02 = new MessageSendPreview$6$$ExternalSyntheticLambda1(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator, androidx.recyclerview.widget.DefaultItemAnimator
        public void onAllAnimationsDone() {
            super.onAllAnimationsDone();
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L5;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
        L5:
            MessageSendPreview$6$$ExternalSyntheticLambda0 r02 = new MessageSendPreview$6$$ExternalSyntheticLambda0(this);
            this.finishRunnable = r02;
            AndroidUtilities.runOnUIThread(r02);
        }

        @Override // org.telegram.ui.recyclerview.ChatListItemAnimator
        public void onAnimationStart() {
            Runnable r0 = this.finishRunnable;
            if (r0 == null) goto L6;
            AndroidUtilities.cancelRunOnUIThread(r0);
            this.finishRunnable = null;
        L6:
            if (BuildVars.LOGS_ENABLED == false) goto L9;
            FileLog.d("chatItemAnimator disable notifications");
            return;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$7, reason: invalid class name */
    public class AnonymousClass7 extends GridLayoutManagerFixed {
        boolean computingScroll;
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass7(MessageSendPreview r1, Context r2, int r3, int r4, boolean r5) {
            this.this$0 = r1;
            super(r2, r3, r4, r5);
        }

        @Override // androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int computeVerticalScrollExtent(RecyclerView.State r2) {
            this.computingScroll = true;
            int r22 = super.computeVerticalScrollExtent(r2);
            this.computingScroll = false;
            return r22;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int computeVerticalScrollOffset(RecyclerView.State r2) {
            this.computingScroll = true;
            int r22 = super.computeVerticalScrollOffset(r2);
            this.computingScroll = false;
            return r22;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public int computeVerticalScrollRange(RecyclerView.State r2) {
            this.computingScroll = true;
            int r22 = super.computeVerticalScrollRange(r2);
            this.computingScroll = false;
            return r22;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
        public boolean hasSiblingChild(int r8) {
            MessageObject r82 = (MessageObject) MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).get((getItemCount() - 1) - r8);
            MessageObject.GroupedMessages r7 = MessageSendPreview.m16173$$Nest$mgetValidGroupedMessage(this.this$0, r82);
            if (r7 == null) goto L21;
            MessageObject.GroupedMessagePosition r83 = r7.getPosition(r82);
            if (r83.minX == r83.maxX) goto L21;
            byte r1 = r83.minY;
            if (r1 != r83.maxY) goto L21;
            if (r1 == 0) goto L21;
            int r12 = r7.posArray.size();
            int r3 = 0;
        L11:
            if (r3 >= r12) goto L21;
            MessageObject.GroupedMessagePosition r4 = r7.posArray.get(r3);
            if (r4 == r83) goto L20;
            byte r5 = r4.minY;
            byte r6 = r83.minY;
            if (r5 > r6) goto L20;
            if (r4.maxY < r6) goto L20;
            return true;
        L20:
            r3 = r3 + 1;
        L21:
            return false;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManagerFixed
        public boolean shouldLayoutChildFromOpositeSide(View r1) {
            if ((r1 instanceof ChatMessageCell) == true) goto L5;
            return false;
        L5:
            return !((ChatMessageCell) r1).getMessageObject().isOutOwner();
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager, androidx.recyclerview.widget.LinearLayoutManager, androidx.recyclerview.widget.RecyclerView.LayoutManager
        public boolean supportsPredictiveItemAnimations() {
            return true;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$8, reason: invalid class name */
    public class AnonymousClass8 extends GridLayoutManager.SpanSizeLookup {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass8(MessageSendPreview r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
        public int getSpanSize(int r3) {
            MessageObject r32 = (MessageObject) MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).get((MessageSendPreview.m16148$$Nest$fgetmessageObjects(this.this$0).size() - 1) - r3);
            MessageObject.GroupedMessages r2 = MessageSendPreview.m16173$$Nest$mgetValidGroupedMessage(this.this$0, r32);
            if (r2 != null) goto L5;
            return 1000;
        L5:
            return r2.getPosition(r32).spanSize;
        }
    }

    /* renamed from: org.telegram.ui.MessageSendPreview$9, reason: invalid class name */
    public class AnonymousClass9 extends RecyclerView.ItemDecoration {
        final /* synthetic */ MessageSendPreview this$0;

        public AnonymousClass9(MessageSendPreview r1) {
            this.this$0 = r1;
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

    public class MessageCell extends ChatMessageCell {
        public int bottom;
        private int pastId;
        final /* synthetic */ MessageSendPreview this$0;
        public int top;

        /* renamed from: -$$Nest$fputpastId, reason: not valid java name */
        public static /* bridge */ /* synthetic */ void m16178$$Nest$fputpastId(MessageCell r0, int r1) {
            r0.pastId = r1;
        }

        public MessageCell(MessageSendPreview r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
            this.top = Integer.MAX_VALUE;
            this.bottom = Integer.MAX_VALUE;
            this.pastId = -1;
        }

        @Override // android.view.View
        public boolean isPressed() {
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public SpoilerEffect2 makeSpoilerEffect() {
            return SpoilerEffect2.getInstance(1, this, MessageSendPreview.m16157$$Nest$fgetwindowView(this.this$0));
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            if (this.transitionParams.animateBackgroundBoundsInner == false) goto L24;
            if (r3 != 0) goto L6;
            return;
        L6:
            if (this.top == Integer.MAX_VALUE) goto L26;
            if (r5 != 0) goto L9;
            return;
        L9:
            if (this.bottom == Integer.MAX_VALUE) goto L28;
            int r12 = this.pastId;
            int r42 = 0;
            if (getMessageObject() != null) goto L13;
            int r22 = 0;
        L14:
            if (r12 == r22) goto L16;
            return;
        L16:
            if (MessageSendPreview.m16153$$Nest$fgetscrolledToLast(this.this$0) == true) goto L18;
            setTranslationY(-(r3 - this.top));
            animate().translationY(0.0f).setDuration(320).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        L18:
            this.top = getTop();
            this.bottom = getBottom();
            if (getMessageObject() == null) goto L22;
            r42 = getMessageObject().getId();
        L22:
            this.pastId = r42;
            return;
        L13:
            r22 = getMessageObject().getId();
            goto L14
        L28:
            return;
        L26:
            return;
        }
    }

    public static class VisiblePart {
        private int blurredViewBottomOffset;
        private int blurredViewTopOffset;
        private int childPosition;
        public int parentHeight;
        public int parentWidth;
        private int visibleHeight;
        private int visibleParent;
        private float visibleParentOffset;
        private float visibleTop;

        private VisiblePart() {
        }

        public static VisiblePart of(ChatMessageCell r2) {
            VisiblePart r0 = new VisiblePart();
            r0.childPosition = r2.childPosition;
            r0.visibleHeight = r2.visibleHeight;
            r0.visibleParent = r2.visibleParent;
            r0.parentWidth = r2.parentWidth;
            r0.parentHeight = r2.parentHeight;
            r0.visibleTop = r2.visibleTop;
            r0.visibleParentOffset = r2.visibleParentOffset;
            r0.blurredViewTopOffset = r2.blurredViewTopOffset;
            r0.blurredViewBottomOffset = r2.blurredViewBottomOffset;
            return r0;
        }
    }

    /* renamed from: $r8$lambda$2zKuHnwQrmzYFf8NvlVmiXL63-s, reason: not valid java name */
    public static /* synthetic */ void m16111$r8$lambda$2zKuHnwQrmzYFf8NvlVmiXL63s(MessageSendPreview r0, View r1) {
        r0.lambda$new$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$3aL4l7BAUOawmthPnK2jsOr4gYI(MessageSendPreview r0) {
        r0.lambda$dismiss$10();
    }

    /* renamed from: $r8$lambda$3kGH038Na7LgB0KRe-RCoauT1F4, reason: not valid java name */
    public static /* synthetic */ void m16112$r8$lambda$3kGH038Na7LgB0KReRCoauT1F4(MessageSendPreview r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$8yiLUvfNRtzDxyyLCg2kpEUYOGQ(MessageSendPreview r0, View r1, int r2) {
        r0.lambda$new$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$BxJRtCAvWVFqCy6RKJmo2Zvtd8U(MessageSendPreview r0) {
        r0.lambda$dismiss$9();
    }

    public static /* synthetic */ void $r8$lambda$RO8h9ztU0U4eP2IB7_oE743U2pw(MessageSendPreview r0, Integer r1) {
        r0.lambda$allowEffectSelector$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$SyUMQBIzkbo2B6Qc785g0XK4GW4(MessageSendPreview r0) {
        r0.lambda$dismissInto$8();
    }

    /* renamed from: $r8$lambda$h6ObxL9wGOxl-2Nb28H1UcaMqbk, reason: not valid java name */
    public static /* synthetic */ void m16113$r8$lambda$h6ObxL9wGOxl2Nb28H1UcaMqbk(MessageSendPreview r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$swCivqbWyeRRW4YvK0d3H3-D_ts, reason: not valid java name */
    public static /* synthetic */ void m16114$r8$lambda$swCivqbWyeRRW4YvK0d3H3D_ts(MessageSendPreview r0, View r1, View r2) {
        r0.lambda$new$3(r1, r2);
    }

    /* renamed from: $r8$lambda$t_bXxBpcXf-jhrQ_2oH-ZxT5fLA, reason: not valid java name */
    public static /* synthetic */ void m16115$r8$lambda$t_bXxBpcXfjhrQ_2oHZxT5fLA(MessageSendPreview r0, boolean r1, ValueAnimator r2) {
        r0.lambda$animateOpenTo$11(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$tnFZ578TD_ho7jZLDglm93iATys(MessageSendPreview r0, float r1, View r2, Bitmap r3, Bitmap r4) {
        r0.lambda$prepareBlur$12(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$u4GEI3Da1UIJHIjnlgfa1jUzcbo(MessageSendPreview r0) {
        r0.lambda$dismissInto$7();
    }

    /* renamed from: $r8$lambda$yqCSjrv6Q-bMxMy9p1wmdllkGOI, reason: not valid java name */
    public static /* synthetic */ void m16116$r8$lambda$yqCSjrv6QbMxMy9p1wmdllkGOI(MessageSendPreview r0, View r1) {
        r0.lambda$new$1(r1);
    }

    /* renamed from: -$$Nest$fgetactivityVisibilityController, reason: not valid java name */
    public static /* bridge */ /* synthetic */ WindowVisibilityManager.Controller m16117$$Nest$fgetactivityVisibilityController(MessageSendPreview r0) {
        return r0.activityVisibilityController;
    }

    /* renamed from: -$$Nest$fgetanchorSendButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView.SendButton m16118$$Nest$fgetanchorSendButton(MessageSendPreview r0) {
        return r0.anchorSendButton;
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m16119$$Nest$fgetblurBitmap(MessageSendPreview r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m16120$$Nest$fgetblurBitmapPaint(MessageSendPreview r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m16121$$Nest$fgetblurBitmapShader(MessageSendPreview r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m16122$$Nest$fgetblurMatrix(MessageSendPreview r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcameraRect, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m16123$$Nest$fgetcameraRect(MessageSendPreview r0) {
        return r0.cameraRect;
    }

    /* renamed from: -$$Nest$fgetcellDelta, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Rect m16124$$Nest$fgetcellDelta(MessageSendPreview r0) {
        return r0.cellDelta;
    }

    /* renamed from: -$$Nest$fgetchatListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m16125$$Nest$fgetchatListView(MessageSendPreview r0) {
        return r0.chatListView;
    }

    /* renamed from: -$$Nest$fgetclosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16126$$Nest$fgetclosing(MessageSendPreview r0) {
        return r0.closing;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m16127$$Nest$fgetcontainerView(MessageSendPreview r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetdestCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m16128$$Nest$fgetdestCell(MessageSendPreview r0) {
        return r0.destCell;
    }

    /* renamed from: -$$Nest$fgetdestClipBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16129$$Nest$fgetdestClipBottom(MessageSendPreview r0) {
        return r0.destClipBottom;
    }

    /* renamed from: -$$Nest$fgetdestClipTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16130$$Nest$fgetdestClipTop(MessageSendPreview r0) {
        return r0.destClipTop;
    }

    /* renamed from: -$$Nest$fgetdismissing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16131$$Nest$fgetdismissing(MessageSendPreview r0) {
        return r0.dismissing;
    }

    /* renamed from: -$$Nest$fgetdrawEditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback2 m16132$$Nest$fgetdrawEditText(MessageSendPreview r0) {
        return r0.drawEditText;
    }

    /* renamed from: -$$Nest$fgetdrawEditTextBackground, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Utilities.Callback m16133$$Nest$fgetdrawEditTextBackground(MessageSendPreview r0) {
        return r0.drawEditTextBackground;
    }

    /* renamed from: -$$Nest$fgeteditText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EditTextCaption m16134$$Nest$fgeteditText(MessageSendPreview r0) {
        return r0.editText;
    }

    /* renamed from: -$$Nest$fgeteditTextBackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m16135$$Nest$fgeteditTextBackgroundPaint(MessageSendPreview r0) {
        return r0.editTextBackgroundPaint;
    }

    /* renamed from: -$$Nest$fgeteffectDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable m16136$$Nest$fgeteffectDrawable(MessageSendPreview r0) {
        return r0.effectDrawable;
    }

    /* renamed from: -$$Nest$fgeteffectId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ long m16137$$Nest$fgeteffectId(MessageSendPreview r2) {
        return r2.effectId;
    }

    /* renamed from: -$$Nest$fgeteffectOverlay, reason: not valid java name */
    public static /* bridge */ /* synthetic */ EmojiAnimationsOverlay m16138$$Nest$fgeteffectOverlay(MessageSendPreview r0) {
        return r0.effectOverlay;
    }

    /* renamed from: -$$Nest$fgeteffectSelector, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m16139$$Nest$fgeteffectSelector(MessageSendPreview r0) {
        return r0.effectSelector;
    }

    /* renamed from: -$$Nest$fgeteffectsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m16140$$Nest$fgeteffectsView(MessageSendPreview r0) {
        return r0.effectsView;
    }

    /* renamed from: -$$Nest$fgetfirstOpenFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16141$$Nest$fgetfirstOpenFrame(MessageSendPreview r0) {
        return r0.firstOpenFrame;
    }

    /* renamed from: -$$Nest$fgetfirstOpenFrame2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16142$$Nest$fgetfirstOpenFrame2(MessageSendPreview r0) {
        return r0.firstOpenFrame2;
    }

    /* renamed from: -$$Nest$fgetgroupedMessagesMap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ LongSparseArray m16143$$Nest$fgetgroupedMessagesMap(MessageSendPreview r0) {
        return r0.groupedMessagesMap;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m16144$$Nest$fgetinsets(MessageSendPreview r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetlayoutDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16145$$Nest$fgetlayoutDone(MessageSendPreview r0) {
        return r0.layoutDone;
    }

    /* renamed from: -$$Nest$fgetmainMessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m16146$$Nest$fgetmainMessageCell(MessageSendPreview r0) {
        return r0.mainMessageCell;
    }

    /* renamed from: -$$Nest$fgetmainMessageCellId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16147$$Nest$fgetmainMessageCellId(MessageSendPreview r0) {
        return r0.mainMessageCellId;
    }

    /* renamed from: -$$Nest$fgetmessageObjects, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ArrayList m16148$$Nest$fgetmessageObjects(MessageSendPreview r0) {
        return r0.messageObjects;
    }

    /* renamed from: -$$Nest$fgetmessageObjectsWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16149$$Nest$fgetmessageObjectsWidth(MessageSendPreview r0) {
        return r0.messageObjectsWidth;
    }

    /* renamed from: -$$Nest$fgetopenInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16150$$Nest$fgetopenInProgress(MessageSendPreview r0) {
        return r0.openInProgress;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m16151$$Nest$fgetopenProgress(MessageSendPreview r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetoptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m16152$$Nest$fgetoptionsView(MessageSendPreview r0) {
        return r0.optionsView;
    }

    /* renamed from: -$$Nest$fgetscrolledToLast, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16153$$Nest$fgetscrolledToLast(MessageSendPreview r0) {
        return r0.scrolledToLast;
    }

    /* renamed from: -$$Nest$fgetsendButton, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivityEnterView.SendButton m16154$$Nest$fgetsendButton(MessageSendPreview r0) {
        return r0.sendButton;
    }

    /* renamed from: -$$Nest$fgetsendButtonInitialPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m16155$$Nest$fgetsendButtonInitialPosition(MessageSendPreview r0) {
        return r0.sendButtonInitialPosition;
    }

    /* renamed from: -$$Nest$fgetsent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m16156$$Nest$fgetsent(MessageSendPreview r0) {
        return r0.sent;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m16157$$Nest$fgetwindowView(MessageSendPreview r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputclosing, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16158$$Nest$fputclosing(MessageSendPreview r0, boolean r1) {
        r0.closing = r1;
    }

    /* renamed from: -$$Nest$fputeffectDrawable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16159$$Nest$fputeffectDrawable(MessageSendPreview r0, AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r1) {
        r0.effectDrawable = r1;
    }

    /* renamed from: -$$Nest$fputeffectId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16160$$Nest$fputeffectId(MessageSendPreview r0, long r1) {
        r0.effectId = r1;
    }

    /* renamed from: -$$Nest$fputfirstOpenFrame, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16161$$Nest$fputfirstOpenFrame(MessageSendPreview r0, boolean r1) {
        r0.firstOpenFrame = r1;
    }

    /* renamed from: -$$Nest$fputfirstOpenFrame2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16162$$Nest$fputfirstOpenFrame2(MessageSendPreview r0, boolean r1) {
        r0.firstOpenFrame2 = r1;
    }

    /* renamed from: -$$Nest$fputinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16163$$Nest$fputinsets(MessageSendPreview r0, Insets r1) {
        r0.insets = r1;
    }

    /* renamed from: -$$Nest$fputlayoutDone, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16164$$Nest$fputlayoutDone(MessageSendPreview r0, boolean r1) {
        r0.layoutDone = r1;
    }

    /* renamed from: -$$Nest$fputmainMessageCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16165$$Nest$fputmainMessageCell(MessageSendPreview r0, ChatMessageCell r1) {
        r0.mainMessageCell = r1;
    }

    /* renamed from: -$$Nest$fputmainMessageCellId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16166$$Nest$fputmainMessageCellId(MessageSendPreview r0, int r1) {
        r0.mainMessageCellId = r1;
    }

    /* renamed from: -$$Nest$fputopenInProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16167$$Nest$fputopenInProgress(MessageSendPreview r0, boolean r1) {
        r0.openInProgress = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16168$$Nest$fputopenProgress(MessageSendPreview r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$fputopening, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16169$$Nest$fputopening(MessageSendPreview r0, boolean r1) {
        r0.opening = r1;
    }

    /* renamed from: -$$Nest$mcheckBitmapMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16170$$Nest$mcheckBitmapMatrix(MessageSendPreview r0) {
        r0.checkBitmapMatrix();
    }

    /* renamed from: -$$Nest$mgetMainMessageCellPosition, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16171$$Nest$mgetMainMessageCellPosition(MessageSendPreview r0) {
        return r0.getMainMessageCellPosition();
    }

    /* renamed from: -$$Nest$mgetSendButtonWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m16172$$Nest$mgetSendButtonWidth(MessageSendPreview r0) {
        return r0.getSendButtonWidth();
    }

    /* renamed from: -$$Nest$mgetValidGroupedMessage, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject.GroupedMessages m16173$$Nest$mgetValidGroupedMessage(MessageSendPreview r0, MessageObject r1) {
        return r0.getValidGroupedMessage(r1);
    }

    /* renamed from: -$$Nest$mlayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16174$$Nest$mlayout(MessageSendPreview r0) {
        r0.layout();
    }

    /* renamed from: -$$Nest$mupdateMessagesVisiblePart, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m16175$$Nest$mupdateMessagesVisiblePart(MessageSendPreview r0) {
        r0.updateMessagesVisiblePart();
    }

    public MessageSendPreview(Context r15, Theme.ResourcesProvider r16) {
        super(r15, R.style.TransparentDialog);
        int r7 = UserConfig.selectedAccount;
        this.currentAccount = r7;
        this.insets = Insets.NONE;
        this.messageObjects = new ArrayList();
        this.groupedMessagesMap = new LongSparseArray();
        this.editTextBackgroundPaint = new Paint(1);
        this.sendButtonInitialPosition = new int[2];
        this.dismissing = false;
        this.cellDelta = new Rect();
        this.context = r15;
        this.resourcesProvider = r16;
        AndroidUtilities.enableEdgeToEdge(getWindow());
        this.activityVisibilityController = LaunchActivity.obtainActivityVisibilityController();
        AnonymousClass1 r10 = new AnonymousClass1(this, r15);
        this.windowView = r10;
        this.spoilerEffect2 = SpoilerEffect2.getInstance(1, r10, r10);
        r10.setOnClickListener(new MessageSendPreview$$ExternalSyntheticLambda5(this));
        r10.getViewTreeObserver().addOnGlobalFocusChangeListener(new MessageSendPreview$$ExternalSyntheticLambda6(this));
        BlurredBackgroundSourceBitmap r0 = new BlurredBackgroundSourceBitmap();
        this.iBlur3SourceBitmap = r0;
        BlurredBackgroundDrawableViewFactory r3 = new BlurredBackgroundDrawableViewFactory(r0);
        this.iBlur3Factory = r3;
        r3.setSourceRootView(new ViewPositionWatcher(r10), r10);
        AnonymousClass2 r11 = new AnonymousClass2(this, r15, r16);
        this.containerView = r11;
        r11.setClipToPadding(false);
        r10.addView(r11, LayoutHelper.createFrame(-1, -1, 119));
        ViewCompat.setOnApplyWindowInsetsListener(r10, new AnonymousClass3(this));
        AnonymousClass4 r13 = new AnonymousClass4(this, r15, r16);
        this.chatListView = r13;
        r13.setOnClickListener(new MessageSendPreview$$ExternalSyntheticLambda7(this));
        r13.setOnItemClickListener(new MessageSendPreview$$ExternalSyntheticLambda8(this));
        r13.setOnScrollListener(new AnonymousClass5(this));
        r13.setItemAnimator(new AnonymousClass6(this, null, r13, r16));
        AnonymousClass7 r02 = new AnonymousClass7(this, r15, 1000, 1, true);
        this.chatLayoutManager = r02;
        r02.setSpanSizeLookup(new AnonymousClass8(this));
        r13.setLayoutManager(r02);
        r13.addItemDecoration(new AnonymousClass9(this));
        AnonymousClass10 r03 = new AnonymousClass10(this, r15, r16);
        this.adapter = r03;
        r13.setAdapter(r03);
        r13.setVerticalScrollBarEnabled(false);
        r13.setOverScrollMode(2);
        r11.addView(r13, LayoutHelper.createFrame(-1, -2.0f));
        AnonymousClass11 r04 = new AnonymousClass11(this, r15);
        this.effectsView = r04;
        r10.addView(r04, LayoutHelper.createFrame(-1, -1.0f));
        this.effectOverlay = new AnonymousClass12(this, r04, r7);
    }

    private void afterDismiss() {
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.availableEffectsUpdate);
        WindowVisibilityManager.Controller r0 = this.activityVisibilityController;
        if (r0 == null) goto L6;
        r0.destroy();
        this.activityVisibilityController = null;
        return;
    }

    private void animateOpenTo(boolean r7, Runnable r8) {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (r7 == false) goto L12;
        View r2 = this.optionsView;
        if (r2 == null) goto L12;
        if ((r2 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L12;
        boolean r22 = true;
    L13:
        if (r22 == false) goto L15;
        ActionBarPopupWindow.startAnimation((ActionBarPopupWindow.ActionBarPopupWindowLayout) this.optionsView);
    L15:
        if (r7 == true) goto L17;
        hideEffectSelector();
    L17:
        this.openInProgress = true;
        this.opening = r7;
        this.closing = !r7;
        this.chatListView.invalidate();
        this.firstOpenFrame = true;
        this.firstOpenFrame2 = true;
        float r3 = this.openProgress;
        if (r7 == false) goto L20;
        float r4 = 1.0f;
    L21:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{r3, r4});
        this.openAnimator = r02;
        r02.addUpdateListener(new MessageSendPreview$$ExternalSyntheticLambda0(this, r22));
        this.openAnimator.addListener(new AnonymousClass16(this, r7, r22, r8));
        this.openAnimator.setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT);
        this.openAnimator.setDuration(350);
        this.openAnimator.start();
        return;
    L20:
        r4 = 0.0f;
    L12:
        r22 = false;
        goto L13
    }

    private void checkBitmapMatrix() {
        Blur3Utils.checkBitmapSourceMatrixScale(this.iBlur3SourceBitmap, this.windowView);
        View r2 = this.optionsView;
        if (r2 == null) goto L6;
        r2.invalidate();
        return;
    }

    private int getMainMessageCellPosition() {
        if (this.groupedMessagesMap.isEmpty() == false) goto L5;
        return 0;
    L5:
        if (this.messageObjects.size() >= 10) goto L8;
        return 0;
    L8:
        return this.messageObjects.size() % 10;
    }

    private int getSendButtonWidth() {
        if (this.customSendButtonWidth == false) goto L7;
        return this.sendButtonWidth;
    L7:
        return this.anchorSendButton.width();
    }

    private MessageObject.GroupedMessages getValidGroupedMessage(MessageObject r5) {
        if (r5.getGroupId() == 0) goto L12;
        MessageObject.GroupedMessages r4 = this.groupedMessagesMap.get(r5.getGroupId());
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

    private int getWidthForMessage(MessageObject r9) {
        if (getContext() != null) goto L6;
        return 0;
    L6:
        if (this.dummyMessageCell != null) goto L8;
        this.dummyMessageCell = new ChatMessageCell(getContext(), this.currentAccount, true, null, this.resourcesProvider);
    L8:
        ChatMessageCell r0 = this.dummyMessageCell;
        r0.isChat = false;
        r0.isSavedChat = false;
        r0.isSavedPreviewChat = false;
        r0.isBot = false;
        r0.isMegagroup = false;
        return r0.computeWidth(r9, this.groupedMessagesMap.get(r9.getGroupId()));
    }

    private /* synthetic */ void lambda$allowEffectSelector$6(Integer r3) {
        if ((r3.intValue() - this.insets.bottom) <= AndroidUtilities.dp(20.0f)) goto L5;
        boolean r0 = true;
    L6:
        this.keyboardVisible = r0;
        float r1 = this.effectSelectorContainerY;
        if (r0 == false) goto L9;
        r1 = Math.min(r1, (this.windowView.getHeight() - r3.intValue()) - this.effectSelectorContainer.getMeasuredHeight());
    L9:
        this.effectSelectorContainer.animate().translationY(r1 - this.effectSelectorContainer.getTop()).setDuration(250).setInterpolator(AdjustPanLayoutHelper.keyboardInterpolator).start();
        return;
    L5:
        r0 = false;
        goto L6
    }

    private /* synthetic */ void lambda$animateOpenTo$11(boolean r2, ValueAnimator r3) {
        float r32 = ((Float) r3.getAnimatedValue()).floatValue();
        this.openProgress = r32;
        this.effectsView.setAlpha(r32);
        this.chatListView.setAlpha(this.openProgress);
        if (r2 == true) goto L7;
        View r22 = this.optionsView;
        if (r22 == null) goto L7;
        r22.setAlpha(this.openProgress);
    L7:
        this.windowView.invalidate();
        this.containerView.invalidate();
    }

    private /* synthetic */ void lambda$dismiss$10() {
        SpoilerEffect2.pause(0, false);
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 == null) goto L5;
        r0.detach(this.windowView);
    L5:
        AndroidUtilities.runOnUIThread(new MessageSendPreview$$ExternalSyntheticLambda9(this));
    }

    private /* synthetic */ void lambda$dismiss$9() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismissInto$7() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismissInto$8() {
        SpoilerEffect2.pause(0, false);
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 == null) goto L5;
        r0.detach(this.windowView);
    L5:
        AndroidUtilities.runOnUIThread(new MessageSendPreview$$ExternalSyntheticLambda10(this));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$new$1(View r6) {
        AndroidUtilities.showKeyboard(r6);
        ChatActivityEnterView.SendButton r62 = this.anchorSendButton;
        if (r62 == null) goto L6;
        r62.getLocationOnScreen(this.sendButtonInitialPosition);
        int[] r63 = this.sendButtonInitialPosition;
        int r1 = r63[0];
        int r2 = this.anchorSendButton.getWidth();
        ChatActivityEnterView.SendButton r3 = this.anchorSendButton;
        r63[0] = r1 + ((r2 - r3.width(r3.getHeight())) - AndroidUtilities.dp(6.0f));
        this.sendButton.setScaleX(this.anchorSendButton.getScaleX());
        this.sendButton.setScaleY(this.anchorSendButton.getScaleY());
        return;
    }

    private /* synthetic */ void lambda$new$2(View r2) {
        makeFocusable();
        AndroidUtilities.runOnUIThread(new MessageSendPreview$$ExternalSyntheticLambda12(this, r2), 100);
    }

    private /* synthetic */ void lambda$new$3(View r3, View r4) {
        if (this.focusable == false) goto L5;
        return;
    L5:
        if ((r4 instanceof EditText) == false) goto L9;
        AndroidUtilities.hideKeyboard(this.editText);
        AndroidUtilities.runOnUIThread(new MessageSendPreview$$ExternalSyntheticLambda11(this, r4), 200);
        return;
    }

    private /* synthetic */ void lambda$new$4(View r1) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$new$5(View r1, int r2) {
        onBackPressed();
    }

    private /* synthetic */ void lambda$prepareBlur$12(float r2, View r3, Bitmap r4, Bitmap r5) {
        ChatActivityEnterView.SendButton r0 = this.anchorSendButton;
        if (r0 == null) goto L5;
        r0.setAlpha(r2);
    L5:
        if (r3 == null) goto L7;
        r3.setVisibility(0);
    L7:
        this.blurBitmap = r4;
        Paint r22 = new Paint(1);
        this.blurBitmapPaint = r22;
        Bitmap r42 = this.blurBitmap;
        Shader.TileMode r02 = Shader.TileMode.CLAMP;
        BitmapShader r32 = new BitmapShader(r42, r02, r02);
        this.blurBitmapShader = r32;
        r22.setShader(r32);
        this.blurMatrix = new Matrix();
        this.iBlur3SourceBitmap.setBitmap(r5);
        checkBitmapMatrix();
    }

    private void layout() {
        if (this.windowView.getWidth() <= 0) goto L55;
        int[] r0 = new int[2];
        this.anchorSendButton.getLocationOnScreen(r0);
        r0[0] = r0[0] + ((this.anchorSendButton.getWidth() - this.anchorSendButton.width()) - AndroidUtilities.dp(6.0f));
        this.sendButton.setScaleX(this.anchorSendButton.getScaleX());
        this.sendButton.setScaleY(this.anchorSendButton.getScaleY());
        int[] r2 = this.sendButtonInitialPosition;
        r2[0] = r0[0];
        r2[1] = r0[1];
        int r22 = this.chatListView.getMeasuredHeight() - this.sendButton.getHeight();
        if (this.effectSelector == null) goto L8;
        int r5 = AndroidUtilities.dp(320.0f);
    L9:
        int r23 = r22 + r5;
        int r52 = this.insets.top + AndroidUtilities.dp(8.0f);
        if (this.messageObjects.isEmpty() == false) goto L12;
        float r7 = -6.0f;
    L13:
        int r72 = AndroidUtilities.dp(r7);
        View r8 = this.optionsView;
        if (r8 != null) goto L16;
        int r82 = 0;
    L17:
        int r73 = r72 + r82;
        int r83 = (this.containerView.getMeasuredHeight() - AndroidUtilities.dp(8.0f)) - this.insets.bottom;
        if ((r0[1] + r73) <= r83) goto L21;
        r0[1] = r83 - r73;
    L21:
        if ((r0[1] - r23) >= r52) goto L24;
        r0[1] = r52 + r23;
    L24:
        if (((r0[1] + this.anchorSendButton.getHeight()) + r73) <= r83) goto L26;
        r0[1] = (r83 - r73) - this.anchorSendButton.getHeight();
    L26:
        this.sendButton.setX((r0[0] - (r2.getWidth() - this.sendButton.width())) + AndroidUtilities.dp(6.0f));
        this.sendButton.setY(r0[1]);
        if (this.customSendButtonWidth == false) goto L29;
        r0[0] = r0[0] - (this.sendButtonWidth - this.anchorSendButton.width());
    L29:
        this.chatListView.setX((r0[0] + AndroidUtilities.dp(7.0f)) - this.chatListView.getMeasuredWidth());
        boolean r24 = this.layoutDone;
        RecyclerListView r53 = this.chatListView;
        if (r24 == false) goto L32;
        r53.animate().translationY(((r0[1] + this.sendButton.getHeight()) - this.chatListView.getMeasuredHeight()) - this.chatListView.getTop()).setInterpolator(ChatListItemAnimator.DEFAULT_INTERPOLATOR).setDuration(250).start();
    L33:
        View r25 = this.optionsView;
        if (r25 == null) goto L40;
        r25.setX((r0[0] + AndroidUtilities.dp(7.0f)) - this.optionsView.getMeasuredWidth());
        View r26 = this.optionsView;
        int r54 = r0[1];
        if (this.messageObjects.isEmpty() == false) goto L38;
        int r6 = -AndroidUtilities.dp(6.0f);
    L39:
        r26.setY(r54 + r6);
        goto L40
    L38:
        r6 = this.sendButton.getHeight();
    L40:
        FrameLayout r27 = this.effectSelectorContainer;
        if (r27 == null) goto L52;
        r27.setX(Math.max(0, ((r0[0] + this.sendButton.width()) - this.effectSelectorContainer.getMeasuredWidth()) - AndroidUtilities.dp(6.0f)));
        RectF r1 = this.cameraRect;
        if (r1 == null) goto L48;
        FrameLayout r02 = this.effectSelectorContainer;
        float r12 = Math.max(this.insets.top, r1.top - r02.getMeasuredWidth());
        this.effectSelectorContainerY = r12;
        r02.setY(r12);
        ReactionsContainerLayout r03 = this.effectSelector;
        if (r03 == null) goto L53;
        r03.setY(Math.max(this.insets.top, (this.cameraRect.top - AndroidUtilities.dp(24.0f)) - this.effectSelector.getMeasuredHeight()));
        return;
    L53:
        return;
    L48:
        float r04 = (r0[1] + this.sendButton.getHeight()) - this.chatListView.getMeasuredHeight();
        FrameLayout r13 = this.effectSelectorContainer;
        float r3 = Math.max(this.insets.top, r04 - r13.getMeasuredHeight()) + AndroidUtilities.dp(24.0f);
        this.effectSelectorContainerY = r3;
        r13.setY(r3);
        ReactionsContainerLayout r14 = this.effectSelector;
        if (r14 == null) goto L54;
        r14.setY(Math.max(0.0f, (r04 - r14.getMeasuredHeight()) - this.effectSelectorContainerY));
        return;
    L54:
        return;
    L52:
        return;
    L32:
        r53.setY((r0[1] + this.sendButton.getHeight()) - this.chatListView.getMeasuredHeight());
        goto L33
    L16:
        r82 = r8.getMeasuredHeight();
        goto L17
    L12:
        r7 = 48.0f;
        goto L13
    L8:
        r5 = 0;
        goto L9
    }

    private boolean performEffectClickAt(float r5, float r6) {
        ReactionsContainerLayout r0 = this.effectSelector;
        if (r0 != null) goto L5;
    L28:
        return false;
    L5:
        if (this.effectSelectorShown == false) goto L28;
        if (r0.getAlpha() < 0.5f) goto L28;
        RecyclerListView r4 = this.effectSelector.recyclerListView;
        r4.getLocationOnScreen(new int[2]);
        float r52 = r5 - r0[0];
        float r62 = r6 - r0[1];
        if (r52 < 0.0f) goto L28;
        if (r52 > r4.getWidth()) goto L28;
        if (r62 < 0.0f) goto L28;
        if (r62 > r4.getHeight()) goto L28;
        View r53 = r4.findChildViewUnder(r52, r62);
        if (r53 == null) goto L28;
        if (r53.getVisibility() != 0) goto L28;
        int r63 = r4.getChildAdapterPosition(r53);
        if (r63 != (-1)) goto L26;
        return false;
    L26:
        r4.clickItem(r53, r63);
        return true;
    }

    private void prepareBlur(View r4) {
        if (r4 == null) goto L4;
        r4.setVisibility(4);
    L4:
        float r0 = this.anchorSendButton.getAlpha();
        ChatActivityEnterView.SendButton r1 = this.anchorSendButton;
        if (r1 == null) goto L7;
        r1.setAlpha(0.0f);
    L7:
        ScrimOptions.makeGlobalBlurBitmaps(new MessageSendPreview$$ExternalSyntheticLambda1(this, r0, r4));
    }

    private void updateMessagesVisiblePart() {
        int r4 = this.containerView.getMeasuredHeight();
        int r0 = this.chatListView.getChildCount();
        int r13 = 0;
    L3:
        if (r13 >= r0) goto L15;
        View r1 = this.chatListView.getChildAt(r13);
        if ((r1 instanceof ChatMessageCell) == false) goto L14;
        float r5 = ViewPositionWatcher.computeYCoordinateInParent(r1, this.containerView);
        ChatMessageCell r12 = (ChatMessageCell) r1;
        int r3 = (int) r5;
        r1.getMeasuredHeight();
        if (r3 < 0) goto L9;
        int r32 = 0;
    L10:
        int r2 = r1.getMeasuredHeight();
        if (r2 <= r4) goto L13;
        r2 = r32 + r4;
    L13:
        r12.setVisiblePart(r32, r2 - r32, r4, r5, r5, this.containerView.getMeasuredWidth(), this.containerView.getMeasuredHeight(), 0, 0, 0);
        goto L14
    L9:
        r32 = -r3;
    L14:
        r13 = r13 + 1;
        goto L3
    }

    public void allowEffectSelector(BaseFragment r13) {
        if (this.effectSelector != null) goto L15;
        if (r13 == null) goto L16;
        MessagesController.getInstance(this.currentAccount).getAvailableEffects();
        FrameLayout r0 = new FrameLayout(this.context);
        this.effectSelectorContainer = r0;
        r0.setClipChildren(false);
        this.effectSelectorContainer.setClipToPadding(false);
        this.effectSelectorContainer.setPadding(0, 0, 0, AndroidUtilities.dp(24.0f));
        AnonymousClass14 r3 = new AnonymousClass14(this, 5, null, getContext(), this.currentAccount, this.resourcesProvider);
        this.effectSelector = r3;
        r3.setClipChildren(false);
        this.effectSelector.setClipToPadding(false);
        this.effectSelector.setPadding(AndroidUtilities.dp(4.0f), AndroidUtilities.dp(22.0f), AndroidUtilities.dp(4.0f), AndroidUtilities.dp(22.0f));
        this.effectSelector.setDelegate(new AnonymousClass15(this, r13));
        this.effectSelector.setTop(false);
        this.effectSelector.setClipChildren(false);
        this.effectSelector.setClipToPadding(false);
        this.effectSelector.setVisibility(0);
        this.effectSelector.setHint(LocaleController.getString(R.string.AddEffectMessageHint));
        this.effectSelector.setBubbleOffset(AndroidUtilities.dp(-25.0f));
        this.effectSelector.setMiniBubblesOffset(AndroidUtilities.dp(2.0f));
        this.containerView.addView(this.effectSelectorContainer, LayoutHelper.createFrame(-2, 300.0f, 51, 0.0f, 0.0f, 0.0f, 0.0f));
        this.effectSelectorContainer.addView(this.effectSelector, LayoutHelper.createFrame(-1, 116.0f, 83, 0.0f, 0.0f, 0.0f, 0.0f));
        this.effectSelector.setScaleY(0.4f);
        this.effectSelector.setScaleX(0.4f);
        this.effectSelector.setAlpha(0.0f);
        if (MessagesController.getInstance(this.currentAccount).hasAvailableEffects() == false) goto L9;
        showEffectSelector();
    L10:
        ReactionsContainerLayout r12 = this.effectSelector;
        if (r12 == null) goto L13;
        r12.setPaused(true, true);
    L13:
        new KeyboardNotifier(this.windowView, new MessageSendPreview$$ExternalSyntheticLambda2(this));
        return;
    L9:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.availableEffectsUpdate);
        goto L10
    L16:
        return;
    }

    public void changeMessage(MessageObject r4) {
        MessageObject.GroupedMessages r0 = getValidGroupedMessage(r4);
        if (r0 == null) goto L8;
        r0.calculate();
        ArrayList<MessageObject> r42 = r0.messages;
        int r02 = r42.size();
        int r1 = 0;
    L5:
        if (r1 >= r02) goto L7;
        MessageObject r2 = r42.get(r1);
        r1 = r1 + 1;
        changeMessageInternal(r2);
        goto L5
    L7:
        return;
    L8:
        changeMessageInternal(r4);
    }

    public void changeMessageInternal(MessageObject r10) {
        if (this.chatListView != null) goto L5;
        return;
    L5:
        int r0 = 0;
        int r1 = 0;
    L7:
        if (r1 >= this.chatListView.getChildCount()) goto L14;
        View r2 = this.chatListView.getChildAt(r1);
        if ((r2 instanceof ChatMessageCell) == false) goto L13;
        ChatMessageCell r22 = (ChatMessageCell) r2;
        if (r22.getMessageObject() != r10) goto L13;
    L12:
        ChatMessageCell r3 = r22;
        int r12 = -1;
    L17:
        if (r0 >= this.messageObjects.size()) goto L22;
        if (this.messageObjects.get(r0) != r10) goto L21;
        r12 = (this.messageObjects.size() - 1) - r0;
    L21:
        r0 = r0 + 1;
        goto L17
    L22:
        if (r3 != null) goto L25;
        this.chatListView.getAdapter().notifyItemChanged(r12);
        return;
    L25:
        r10.forceUpdate = true;
        r3.setMessageObject(r10, r3.getCurrentMessagesGroup(), r3.isPinnedBottom(), r3.isPinnedTop(), r3.isFirstInChat());
        this.chatListView.getAdapter().notifyItemChanged(r12);
        return;
    L13:
        r1 = r1 + 1;
        goto L7
    L14:
        r22 = null;
        goto L12
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 == NotificationCenter.availableEffectsUpdate) goto L5;
        return;
    L5:
        if (MessagesController.getInstance(this.currentAccount).hasAvailableEffects() == false) goto L9;
        showEffectSelector();
        return;
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        this.dismissing = true;
        ChatActivityEnterView.SendButton r0 = this.sendButton;
        if (r0 == null) goto L8;
        r0.invalidate();
    L8:
        ChatActivityEnterView.SendButton r02 = this.anchorSendButton;
        if (r02 == null) goto L11;
        r02.invalidate();
    L11:
        animateOpenTo(false, new MessageSendPreview$$ExternalSyntheticLambda4(this));
        this.windowView.invalidate();
        afterDismiss();
    }

    public void dismiss(boolean r1) {
        this.sent = r1;
        dismiss();
    }

    public void dismissInstant() {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        this.dismissing = true;
        SpoilerEffect2.pause(0, false);
        SpoilerEffect2 r0 = this.spoilerEffect2;
        if (r0 == null) goto L8;
        r0.detach(this.windowView);
    L8:
        super.dismiss();
        afterDismiss();
    }

    public void dismissInto(ChatMessageCell r10, float r11, float r12) {
        if (this.dismissing == false) goto L5;
        return;
    L5:
        this.sent = true;
        this.dismissing = true;
        ChatActivityEnterView.SendButton r1 = this.sendButton;
        if (r1 == null) goto L8;
        r1.invalidate();
    L8:
        ChatActivityEnterView.SendButton r13 = this.anchorSendButton;
        if (r13 == null) goto L12;
        r13.invalidate();
    L12:
        if (this.mainMessageCell == null) goto L29;
        if (r10 == null) goto L29;
        this.destCell = r10;
        r10.setVisibility(4);
        this.destClipTop = r11;
        this.destClipBottom = r12;
        ChatMessageCell r3 = this.mainMessageCell;
        ChatMessageCell r112 = this.destCell;
        r3.isChat = r112.isChat;
        r3.isThreadChat = r112.isThreadChat;
        r3.isSavedChat = r112.isSavedChat;
        r3.isBot = r112.isBot;
        r3.isForum = r112.isForum;
        r3.isForumGeneral = r112.isForumGeneral;
        r3.setMessageObject(r10.getMessageObject(), null, r10.isPinnedBottom(), r10.isPinnedTop(), r10.isFirstInChat());
        ChatMessageCell.TransitionParams r113 = this.mainMessageCell.getTransitionParams();
        r113.animateChange = this.mainMessageCell.getTransitionParams().animateChange();
        r113.animateChangeProgress = 0.0f;
        if (this.mainMessageCell.getTransitionParams().lastDrawingBackgroundRect.left == this.mainMessageCell.getBackgroundDrawableLeft()) goto L17;
        boolean r122 = true;
    L18:
        if (r122 == false) goto L20;
    L23:
        this.cellDelta.bottom = -(this.mainMessageCell.getBackgroundDrawableBottom() - r113.lastDrawingBackgroundRect.bottom);
        this.cellDelta.top = -(this.mainMessageCell.getBackgroundDrawableTop() - r113.lastDrawingBackgroundRect.top);
        boolean r102 = r10.getMessageObject().isOutOwner();
        Rect r14 = this.cellDelta;
        if (r102 == false) goto L26;
        r14.left = -(this.mainMessageCell.getBackgroundDrawableLeft() - r113.lastDrawingBackgroundRect.left);
        this.cellDelta.right = 0;
    L27:
        r113.animateBackgroundBoundsInner = true;
        r113.animateBackgroundWidth = r122;
    L28:
        this.fromPart = VisiblePart.of(this.mainMessageCell);
        goto L29
    L26:
        r14.left = 0;
        r14.right = this.mainMessageCell.getBackgroundDrawableRight() - r113.lastDrawingBackgroundRect.right;
        goto L27
    L20:
        if (r113.lastDrawingBackgroundRect.top != this.mainMessageCell.getBackgroundDrawableTop()) goto L23;
        if (r113.lastDrawingBackgroundRect.bottom == this.mainMessageCell.getBackgroundDrawableBottom()) goto L28;
    L17:
        r122 = false;
    L29:
        animateOpenTo(false, new MessageSendPreview$$ExternalSyntheticLambda3(this));
        this.windowView.invalidate();
        afterDismiss();
    }

    public void dispatchCapturedTouchEvent(MotionEvent r3, ItemOptions r4) {
        if (r3.getAction() == 1) goto L5;
    L9:
        if (r4 == null) goto L13;
        r4.dispatchCapturedTouchEvent(r3);
        return;
    L13:
        return;
    L5:
        if (performEffectClickAt(r3.getRawX(), r3.getRawY()) == false) goto L9;
        if (r4 == null) goto L12;
        MotionEvent r2 = MotionEvent.obtain(r3);
        r2.setAction(3);
        r4.dispatchCapturedTouchEvent(r2);
        r2.recycle();
        return;
    }

    public void drawStarsPrice(Canvas r7, float r8, float r9, float r10, float r11) {
        if (this.buttonText != null) goto L5;
        return;
    L5:
        if (this.buttonBgPaint == null) goto L10;
        float r82 = (r8 + r10) / 2.0f;
        float r3 = (r9 + r11) / 2.0f;
        float r92 = AndroidUtilities.dp(28.0f) + this.buttonText.getCurrentWidth();
        float r112 = AndroidUtilities.dp(32.0f);
        RectF r0 = AndroidUtilities.rectTmp;
        float r93 = r92 / 2.0f;
        float r1 = r82 - r93;
        float r113 = r112 / 2.0f;
        r0.set(r1, r3 - r113, r82 + r93, r3 + r113);
        r7.save();
        r7.drawRoundRect(r0, r113, r113, this.buttonBgPaint);
        this.buttonText.draw(r7, r1 + AndroidUtilities.dp(14.0f), r3, -1, 1.0f);
        r7.restore();
        return;
    }

    public long getSelectedEffect() {
        if (this.sentEffect == false) goto L5;
    L21:
        return 0;
    L5:
        if (this.effectSelector == null) goto L21;
        if (this.cameraRect == null) goto L11;
        this.sentEffect = true;
        return this.effectId;
    L11:
        ChatMessageCell r0 = this.mainMessageCell;
        if (r0 == null) goto L21;
        MessageObject r02 = r0.getMessageObject();
        if (r02 != null) goto L16;
        return 0;
    L16:
        TLRPC.Message r03 = r02.messageOwner;
        if ((r03.flags2 & 4) != 0) goto L19;
        return 0;
    L19:
        this.sentEffect = true;
        return r03.effect;
    }

    public void hideEffectSelector() {
        ReactionsContainerLayout r0 = this.effectSelector;
        if (r0 != null) goto L6;
        return;
    L6:
        if (this.effectSelectorShown == true) goto L8;
        return;
    L8:
        r0.dismissWindow();
        if (this.effectSelector.getReactionsWindow() != null) goto L11;
    L13:
        this.effectSelector.animate().alpha(0.01f).translationY(-AndroidUtilities.dp(12.0f)).scaleX(0.6f).scaleY(0.6f).setDuration(180).start();
        return;
    L11:
        if (this.effectSelector.getReactionsWindow().containerView == null) goto L13;
        this.effectSelector.getReactionsWindow().containerView.animate().alpha(0.0f).setDuration(180).start();
        goto L13
    }

    @Override // android.app.Dialog
    public boolean isShowing() {
        return !this.dismissing;
    }

    public void makeFocusable() {
        if (this.focusable == false) goto L10;
        return;
    L10:
        Window r0 = getWindow();     // Catch: Exception -> L7
        WindowManager.LayoutParams r1 = r0.getAttributes();     // Catch: Exception -> L7
        r1.flags &= -131073;
        r0.setAttributes(r1);     // Catch: Exception -> L7
        this.focusable = true;     // Catch: Exception -> L7
        return;
    L7:
        e = move-exception;
        FileLog.e(e);
    }

    @Override // android.app.Dialog
    public void onBackPressed() {
        if (this.keyboardVisible == false) goto L6;
        AndroidUtilities.hideKeyboard(getCurrentFocus());
        this.keyboardVisible = false;
        return;
    L6:
        ReactionsContainerLayout r0 = this.effectSelector;
        if (r0 != null) goto L9;
    L14:
        this.sentEffect = true;
        super.onBackPressed();
        return;
    L9:
        if (r0.getReactionsWindow() == null) goto L14;
        if (this.effectSelector.getReactionsWindow().transition == true) goto L16;
        this.effectSelector.getReactionsWindow().dismiss();
        return;
    }

    @Override // android.app.Dialog
    public void onCreate(Bundle r4) {
        super.onCreate(r4);
        Window r42 = getWindow();
        r42.setWindowAnimations(R.style.DialogNoAnimation);
        setContentView(this.windowView, new ViewGroup.LayoutParams(-1, -1));
        WindowManager.LayoutParams r0 = r42.getAttributes();
        r0.width = -1;
        r0.height = -1;
        r0.gravity = 119;
        r0.dimAmount = 0.0f;
        int r1 = r0.flags & (-3);
        r0.softInputMode = 16;
        r0.flags = r1 | (-1945959040);
        r42.setAttributes(r0);
        this.windowView.setSystemUiVisibility(256);
        AndroidUtilities.setLightNavigationBar(this.windowView, !Theme.isCurrentThemeDark());
    }

    public void onEffectChange(long r1) {
    }

    public void scrollTo(boolean r4) {
        RecyclerListView r0 = this.chatListView;
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.getAdapter() != null) goto L7;
        return;
    L7:
        if (this.chatLayoutManager == null) goto L20;
        int r02 = this.chatListView.getAdapter().getItemCount();
        if (r4 == true) goto L12;
        int r03 = r02 - 1;
    L16:
        this.chatLayoutManager.scrollToPositionWithOffset(r03, AndroidUtilities.dp(12.0f), r4);
        this.scrolledToLast = r4;
        return;
    L12:
        if (r02 <= 10) goto L14;
        r03 = r02 % 10;
        goto L16
    L14:
        r03 = 0;
        goto L16
    }

    public void setCameraTexture(TextureView r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        this.cameraRect = new RectF();
        int[] r0 = new int[2];
        r7.getLocationOnScreen(r0);
        this.cameraRect.set(r0[0], r0[1], r1 + r7.getWidth(), r0[1] + r7.getHeight());
    }

    public void setEditText(EditTextCaption r1, Utilities.Callback2<Canvas, Utilities.Callback0Return<Boolean>> r2, Utilities.Callback<Canvas> r3) {
        this.editText = r1;
        this.drawEditText = r2;
        this.drawEditTextBackground = r3;
    }

    public void setEffectId(long r3) {
        this.effectId = r3;
        int r0 = getMainMessageCellPosition();
        if (r0 >= 0) goto L5;
    L7:
        MessageObject r02 = null;
    L8:
        if (r02 == null) goto L11;
        TLRPC.Message r03 = r02.messageOwner;
        r03.flags2 |= 4;
        r03.effect = r3;
    L11:
        if (this.effectSelector == null) goto L16;
        TLRPC.TL_availableEffect r32 = MessagesController.getInstance(this.currentAccount).getEffect(r3);
        if (r32 == null) goto L17;
        this.effectSelector.setSelectedReactionAnimated(ReactionsLayoutInBubble.VisibleReaction.fromTL(r32));
        return;
    L17:
        return;
    L16:
        return;
    L5:
        if (r0 >= this.messageObjects.size()) goto L7;
        r02 = this.messageObjects.get(r0);
        goto L8
    }

    public void setItemOptions(ItemOptions r4) {
        r4.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.06f));
        r4.setBlurBackground(this.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider), false);
        ViewGroup r42 = r4.getLayout();
        this.optionsView = r42;
        this.containerView.addView(r42, LayoutHelper.createFrame(-2, -2.0f));
    }

    public void setMessageObjects(ArrayList<MessageObject> r8) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r8.size()) goto L24;
        MessageObject r2 = r8.get(r1);
        if (r2.hasValidGroupId() == false) goto L21;
        MessageObject.GroupedMessages r3 = this.groupedMessagesMap.get(r2.getGroupIdForUse());
        if (r3 != null) goto L11;
        r3 = new MessageObject.GroupedMessages();
        r3.reversed = false;
        long r4 = r2.getGroupId();
        r3.groupId = r4;
        this.groupedMessagesMap.put(r4, r3);
    L11:
        if (r3.getPosition(r2) != null) goto L23;
        int r42 = 0;
    L13:
        int r5 = r3.messages.size();
        ArrayList<MessageObject> r6 = r3.messages;
        if (r42 >= r5) goto L19;
        if (r6.get(r42).getId() == r2.getId()) goto L23;
        r42 = r42 + 1;
        goto L13
    L19:
        r6.add(r2);
    L23:
        r1 = r1 + 1;
        goto L4
    L21:
        if (r2.getGroupIdForUse() == 0) goto L23;
        r2.messageOwner.grouped_id = 0;
        r2.localSentGroupId = 0;
        goto L23
    L24:
        int r12 = 0;
    L26:
        if (r12 >= this.groupedMessagesMap.size()) goto L28;
        this.groupedMessagesMap.valueAt(r12).calculate();
        r12 = r12 + 1;
        goto L26
    L28:
        this.messageObjects.addAll(r8);
        int r82 = 0;
    L30:
        if (r82 >= this.messageObjects.size()) goto L32;
        this.messageObjectsWidth = Math.max(this.messageObjectsWidth, getWidthForMessage(this.messageObjects.get(r82)));
        r82 = r82 + 1;
        goto L30
    L32:
        this.chatListView.getAdapter().notifyDataSetChanged();
        int r83 = this.chatListView.getAdapter().getItemCount();
        GridLayoutManagerFixed r7 = this.chatLayoutManager;
        if (r83 <= 10) goto L35;
        r0 = r83 % 10;
    L35:
        r7.scrollToPositionWithOffset(r0, AndroidUtilities.dp(12.0f), true);
    }

    public ChatActivityEnterView.SendButton setSendButton(ChatActivityEnterView.SendButton r9, boolean r10, View.OnClickListener r11) {
        this.anchorSendButton = r9;
        r9.getLocationOnScreen(this.sendButtonInitialPosition);
        AnonymousClass13 r1 = new AnonymousClass13(this, getContext(), r9.resId, this.resourcesProvider, r9, r10);
        this.sendButton = r1;
        r1.setScaleX(this.anchorSendButton.getScaleX());
        this.sendButton.setScaleY(this.anchorSendButton.getScaleY());
        this.anchorSendButton.copyTo(this.sendButton);
        this.sendButton.open.set(r9.open.get(), true);
        this.sendButton.setOnClickListener(r11);
        this.containerView.addView(this.sendButton, new ViewGroup.LayoutParams(r9.getWidth(), r9.getHeight()));
        this.sendButtonWidth = this.anchorSendButton.width(r9.getHeight());
        int[] r8 = this.sendButtonInitialPosition;
        r8[0] = r8[0] + ((this.anchorSendButton.getWidth() - this.anchorSendButton.width(r9.getHeight())) - AndroidUtilities.dp(6.0f));
        return this.sendButton;
    }

    public void setSendButtonWidth(int r2) {
        this.customSendButtonWidth = true;
        this.sendButtonWidth = r2;
    }

    public void setStars(long r5) {
        if (r5 > 0) goto L5;
        Text r52 = null;
    L6:
        this.buttonText = r52;
        if (this.buttonBgPaint != null) goto L9;
        Paint r53 = new Paint(1);
        this.buttonBgPaint = r53;
        r53.setColor(TLObject.FLAG_30);
    L9:
        this.chatListView.invalidate();
        int r1 = 0;
    L11:
        if (r1 >= this.messageObjects.size()) goto L23;
        MessageObject r2 = this.messageObjects.get(r1);
        if (r2 == null) goto L22;
        TLRPC.Message r22 = r2.messageOwner;
        if (r22 == null) goto L22;
        TLRPC.MessageMedia r23 = r22.media;
        if (r23 == null) goto L22;
        if (r5 <= 0) goto L20;
        boolean r3 = true;
    L21:
        r23.spoiler = r3;
        goto L22
    L20:
        r3 = false;
    L22:
        r1 = r1 + 1;
        goto L11
    L23:
        this.adapter.notifyDataSetChanged();
        return;
    L5:
        r52 = new Text(StarsIntroActivity.replaceStarsWithPlain(LocaleController.formatPluralStringComma("UnlockPaidContent", (int) r5), 0.7f), 14.0f, AndroidUtilities.bold());
        goto L6
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
        return;
    L5:
        SpoilerEffect2.pause(0, true);
        super.show();
        prepareBlur(null);
        FrameLayout r2 = this.effectsView;
        if (r2 == null) goto L8;
        r2.bringToFront();
    L8:
        animateOpenTo(true, null);
    }

    public void showEffectSelector() {
        if (this.effectSelectorShown == false) goto L5;
        return;
    L5:
        this.layoutDone = false;
        this.effectSelectorShown = true;
        this.effectSelector.setMessage(null, null, true);
        this.effectSelector.animate().scaleY(1.0f).scaleX(1.0f).alpha(1.0f).setDuration(420).setInterpolator(CubicBezierInterpolator.EASE_OUT_QUINT).start();
        this.effectSelector.startEnterAnimation(false);
    }

    public void updateColors() {
    }
}
