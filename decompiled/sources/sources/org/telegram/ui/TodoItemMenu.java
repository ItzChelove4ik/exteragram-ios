package org.telegram.ui;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Shader;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BaseCell;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.MessagePreviewView;
import org.telegram.ui.Components.MessagePrivateSeenView;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ScrimOptions;
import org.telegram.ui.Components.ViewPagerFixed;
import org.telegram.ui.Components.blur3.BlurredBackgroundDrawableViewFactory;
import org.telegram.ui.Components.blur3.drawable.color.impl.BlurredBackgroundProviderImpl;
import org.telegram.ui.Components.blur3.source.BlurredBackgroundSourceBitmap;
import org.telegram.ui.Components.blur3.utils.Blur3Utils;
import org.telegram.ui.Components.chat.ViewPositionWatcher;

/* loaded from: classes6.dex */
public class TodoItemMenu extends Dialog {
    private Bitmap blurBitmap;
    private Paint blurBitmapPaint;
    private BitmapShader blurBitmapShader;
    private Matrix blurMatrix;
    private ChatMessageCell cell;
    private float clipBottom;
    private float clipTop;
    private FrameLayout containerView;
    public final Context context;
    private Runnable dismissListener;
    private boolean dismissing;
    private boolean dismissingWithAlpha;
    private float dtx1;
    private float dtx2;
    private float dty1;
    private float dty2;
    private boolean hasDestTranslation;
    private boolean hasTranslation;
    private float heightdiff;
    private TextView hintTextView;
    private final BlurredBackgroundDrawableViewFactory iBlur3Factory;
    private final BlurredBackgroundSourceBitmap iBlur3SourceBitmap;
    private Insets insets;
    private boolean isOut;
    private FrameLayout menuContainer;
    private MessageObject messageObject;
    private View messageOptionsView;
    private float messageOptionsViewMaxWidth;
    private ChatMessageCell myCell;
    private ChatMessageCell myTaskCell;
    private boolean open;
    private ValueAnimator open2Animator;
    private ValueAnimator openAnimator;
    private float openProgress;
    private float openProgress2;
    private ReactionsContainerLayout reactionsView;
    public final Theme.ResourcesProvider resourcesProvider;
    private boolean setCellInvisible;
    private boolean setTaskInvisible;
    private MessagePreviewView.TabsView tabsView;
    private int taskId;
    private View taskOptionsView;
    private float taskOptionsViewMaxWidth;
    private float tx;
    private float ty;
    private ViewPagerFixed viewPager;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.TodoItemMenu$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass1(TodoItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (TodoItemMenu.m20880$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L5;
        L7:
            Canvas r1 = r8;
        L9:
            if (TodoItemMenu.m20882$$Nest$fgetsetCellInvisible(this.this$0) == false) goto L14;
            if (TodoItemMenu.m20871$$Nest$fgetcell(this.this$0) == null) goto L14;
            TodoItemMenu.m20871$$Nest$fgetcell(this.this$0).setVisibility(4);
            TodoItemMenu.m20891$$Nest$fputsetCellInvisible(this.this$0, false);
        L14:
            if (TodoItemMenu.m20883$$Nest$fgetsetTaskInvisible(this.this$0) == true) goto L16;
        L18:
            super.dispatchDraw(r1);
            return;
        L16:
            if (TodoItemMenu.m20871$$Nest$fgetcell(this.this$0) == null) goto L18;
            TodoItemMenu.m20871$$Nest$fgetcell(this.this$0).doNotDrawTaskId = TodoItemMenu.m20884$$Nest$fgettaskId(this.this$0);
            TodoItemMenu.m20871$$Nest$fgetcell(this.this$0).invalidate();
            TodoItemMenu.m20892$$Nest$fputsetTaskInvisible(this.this$0, false);
            goto L18
        L5:
            if (TodoItemMenu.m20868$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
            TodoItemMenu.m20870$$Nest$fgetblurMatrix(this.this$0).reset();
            float r0 = getWidth() / TodoItemMenu.m20867$$Nest$fgetblurBitmap(this.this$0).getWidth();
            TodoItemMenu.m20870$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
            TodoItemMenu.m20869$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(TodoItemMenu.m20870$$Nest$fgetblurMatrix(this.this$0));
            TodoItemMenu.m20868$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (TodoItemMenu.m20880$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r1 = r8;
            r1.drawRect(0.0f, 0.0f, getWidth(), getHeight(), TodoItemMenu.m20868$$Nest$fgetblurBitmapPaint(this.this$0));
            goto L9
        }

        @Override // android.view.ViewGroup, android.view.View
        public boolean dispatchKeyEventPreIme(KeyEvent r3) {
            if (r3 == null) goto L10;
            if (r3.getKeyCode() != 4) goto L10;
            if (r3.getAction() != 1) goto L10;
            this.this$0.dismiss();
            return true;
        L10:
            return super.dispatchKeyEventPreIme(r3);
        }

        @Override // android.widget.FrameLayout, android.view.ViewGroup, android.view.View
        public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
            super.onLayout(r1, r2, r3, r4, r5);
            TodoItemMenu.m20894$$Nest$msetupTranslation(this.this$0);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            TodoItemMenu.m20893$$Nest$mcheckBitmapMatrix(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$10, reason: invalid class name */
    public class AnonymousClass10 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass10(TodoItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnClickListener {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ ItemOptions val$messageOptions;

        public AnonymousClass11(TodoItemMenu r1, ItemOptions r2) {
            this.this$0 = r1;
            this.val$messageOptions = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            Bulletin.hideVisible();
            this.val$messageOptions.closeSwipeback();
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$12, reason: invalid class name */
    public class AnonymousClass12 implements View.OnClickListener {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ MessageSeenView val$finalMessageSeenView;
        final /* synthetic */ LinearLayout val$linearLayout;
        final /* synthetic */ RecyclerListView val$listView2;
        final /* synthetic */ ItemOptions val$messageOptions;
        final /* synthetic */ ItemOptions val$swipeback;

        public AnonymousClass12(TodoItemMenu r1, MessageSeenView r2, ChatActivity r3, RecyclerListView r4, LinearLayout r5, ItemOptions r6, ItemOptions r7) {
            this.this$0 = r1;
            this.val$finalMessageSeenView = r2;
            this.val$chatActivity = r3;
            this.val$listView2 = r4;
            this.val$linearLayout = r5;
            this.val$messageOptions = r6;
            this.val$swipeback = r7;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r5) {
            if (this.val$finalMessageSeenView.users.isEmpty() == false) goto L6;
            return;
        L6:
            if (this.val$finalMessageSeenView.users.size() != 1) goto L23;
            if (this.val$finalMessageSeenView.dates.size() > 0) goto L10;
        L11:
            TLObject r52 = this.val$finalMessageSeenView.users.get(0);
            if (r52 != null) goto L14;
            return;
        L14:
            Bundle r0 = new Bundle();
            if ((r52 instanceof TLRPC.User) == false) goto L18;
            r0.putLong("user_id", ((TLRPC.User) r52).id);
        L20:
            ProfileActivity r53 = new ProfileActivity(r0);
            this.val$chatActivity.presentFragment(r53);
            this.this$0.dismiss(false);
            return;
        L18:
            if ((r52 instanceof TLRPC.Chat) == false) goto L20;
            r0.putLong("chat_id", ((TLRPC.Chat) r52).id);
            goto L20
        L10:
            if (this.val$finalMessageSeenView.dates.get(0).intValue() <= 0) goto L11;
        L23:
            if (SharedConfig.messageSeenHintCount > 0) goto L25;
        L27:
            this.val$listView2.requestLayout();
            this.val$linearLayout.requestLayout();
            this.val$listView2.getAdapter().notifyDataSetChanged();
            this.val$messageOptions.openSwipeback(this.val$swipeback);
            return;
        L25:
            if (this.val$chatActivity.contentView.getKeyboardHeight() >= AndroidUtilities.dp(20.0f)) goto L27;
            ChatActivity r54 = this.val$chatActivity;
            r54.messageSeenPrivacyBulletin = BulletinFactory.of(Bulletin.BulletinWindow.make(this.this$0.getContext()), this.this$0.resourcesProvider).createErrorBulletin(AndroidUtilities.replaceTags(LocaleController.getString(R.string.MessageSeenTooltipMessage)));
            this.val$chatActivity.messageSeenPrivacyBulletin.setDuration(4000);
            this.val$chatActivity.messageSeenPrivacyBulletin.show();
            SharedConfig.updateMessageSeenHintCount(SharedConfig.messageSeenHintCount - 1);
            goto L27
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$13, reason: invalid class name */
    public class AnonymousClass13 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ ReactionsContainerLayout val$finalReactionsLayout;
        final /* synthetic */ MessageObject val$message;

        public AnonymousClass13(TodoItemMenu r1, ChatActivity r2, MessageObject r3, ReactionsContainerLayout r4) {
            this.this$0 = r1;
            this.val$chatActivity = r2;
            this.val$message = r3;
            this.val$finalReactionsLayout = r4;
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r14, ReactionsLayoutInBubble.VisibleReaction r15, boolean r16, boolean r17) {
            BaseCell r2 = this.val$chatActivity.findMessageCell(this.val$message.getId(), true);
            float r5 = 0.0f;
            if ((r2 instanceof ChatMessageCell) == false) goto L10;
            ChatMessageCell r1 = (ChatMessageCell) r2;
            ReactionsLayoutInBubble.ReactionButton r6 = r1.reactionsLayoutInBubble.getReactionButton(r15);
            if (r6 == null) goto L7;
            r5 = (r1.x + r6.x) + (r6.width / 2.0f);
            float r12 = r1.reactionsLayoutInBubble.y + r6.y;
            int r62 = r6.height;
        L14:
            float r13 = r12 + (r62 / 2.0f);
        L8:
            float r7 = r13;
            float r63 = r5;
        L16:
            if (r15 != null) goto L18;
        L20:
            boolean r10 = r16;
        L21:
            this.val$chatActivity.selectReaction(r2, this.val$message, this.val$finalReactionsLayout, r14, r63, r7, r15, false, r10, r17, false);
            this.this$0.dismiss(false);
            return;
        L18:
            if (r15.isStar == false) goto L20;
            r10 = true;
            goto L21
        L7:
            r13 = 0.0f;
            goto L8
        L10:
            if ((r2 instanceof ChatActionCell) == false) goto L15;
            ChatActionCell r18 = (ChatActionCell) r2;
            ReactionsLayoutInBubble.ReactionButton r64 = r18.reactionsLayoutInBubble.getReactionButton(r15);
            if (r64 == null) goto L15;
            r5 = (r1.x + r64.x) + (r64.width / 2.0f);
            r12 = r18.reactionsLayoutInBubble.y + r64.y;
            r62 = r64.height;
        L15:
            r63 = 0.0f;
            r7 = 0.0f;
            goto L16
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$open;

        public AnonymousClass14(TodoItemMenu r1, boolean r2, Runnable r3) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$after = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            TodoItemMenu r22 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            TodoItemMenu.m20889$$Nest$fputopenProgress(r22, r0);
            TodoItemMenu.m20887$$Nest$fgetwindowView(this.this$0).invalidate();
            TodoItemMenu.m20874$$Nest$fgetcontainerView(this.this$0).invalidate();
            TodoItemMenu.m20895$$Nest$mupdateTranslation(this.this$0);
            Runnable r1 = this.val$after;
            if (r1 == null) goto L10;
            r1.run();
            return;
        L10:
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ boolean val$open;

        public AnonymousClass15(TodoItemMenu r1, boolean r2) {
            this.this$0 = r1;
            this.val$open = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            TodoItemMenu r12 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            TodoItemMenu.m20890$$Nest$fputopenProgress2(r12, r0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass2(TodoItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != TodoItemMenu.m20878$$Nest$fgetmyCell(this.this$0)) goto L5;
        L9:
            r7.save();
            r7.clipRect(0.0f, AndroidUtilities.lerp(TodoItemMenu.m20873$$Nest$fgetclipTop(this.this$0), 0.0f, TodoItemMenu.m20880$$Nest$fgetopenProgress(this.this$0)), getWidth(), AndroidUtilities.lerp(TodoItemMenu.m20872$$Nest$fgetclipBottom(this.this$0), getHeight(), TodoItemMenu.m20880$$Nest$fgetopenProgress(this.this$0)));
            boolean r6 = super.drawChild(r7, r8, r9);
            r7.restore();
            return r6;
        L5:
            if (r8 == TodoItemMenu.m20879$$Nest$fgetmyTaskCell(this.this$0)) goto L9;
            return super.drawChild(r7, r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass3(TodoItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            TodoItemMenu.m20895$$Nest$mupdateTranslation(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewPagerFixed.Adapter {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ Context val$context;

        public static /* synthetic */ void $r8$lambda$nFih3TrkxDvN4TJFSmY9r75S2ME(AnonymousClass4 r0, View r1) {
            r0.lambda$createView$0(r1);
        }

        public AnonymousClass4(TodoItemMenu r1, Context r2) {
            this.this$0 = r1;
            this.val$context = r2;
        }

        private /* synthetic */ void lambda$createView$0(View r1) {
            this.this$0.dismiss(true);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public void bindView(View r1, int r2, int r3) {
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public View createView(int r2) {
            FrameLayout r22 = new FrameLayout(this.val$context);
            r22.setOnClickListener(new TodoItemMenu$4$$ExternalSyntheticLambda0(this));
            return r22;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 2;
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass5(TodoItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r62 = View.MeasureSpec.getSize(r6);
            int r72 = View.MeasureSpec.getSize(r7);
            TodoItemMenu.m20895$$Nest$mupdateTranslation(this.this$0);
            int r0 = 0;
        L4:
            if (r0 >= getChildCount()) goto L20;
            View r1 = getChildAt(r0);
            if (r1 != TodoItemMenu.m20876$$Nest$fgetmessageOptionsView(this.this$0)) goto L11;
            if (TodoItemMenu.m20877$$Nest$fgetmessageOptionsViewMaxWidth(this.this$0) <= 0.0f) goto L11;
            TodoItemMenu.m20876$$Nest$fgetmessageOptionsView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(Math.min(r62, (int) TodoItemMenu.m20877$$Nest$fgetmessageOptionsViewMaxWidth(this.this$0)), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        L19:
            r0 = r0 + 1;
        L11:
            if (r1 != TodoItemMenu.m20885$$Nest$fgettaskOptionsView(this.this$0)) goto L16;
            if (TodoItemMenu.m20886$$Nest$fgettaskOptionsViewMaxWidth(this.this$0) <= 0.0f) goto L16;
            TodoItemMenu.m20885$$Nest$fgettaskOptionsView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(Math.min(r62, (int) TodoItemMenu.m20886$$Nest$fgettaskOptionsViewMaxWidth(this.this$0)), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        L16:
            if (r1 != TodoItemMenu.m20881$$Nest$fgetreactionsView(this.this$0)) goto L18;
            r1.measure(View.MeasureSpec.makeMeasureSpec(TodoItemMenu.m20881$$Nest$fgetreactionsView(this.this$0).getTotalWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
            goto L19
        L18:
            r1.measure(View.MeasureSpec.makeMeasureSpec(r62, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
            goto L19
        L20:
            setMeasuredDimension(r62, r72);
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$6, reason: invalid class name */
    public class AnonymousClass6 implements OnApplyWindowInsetsListener {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass6(TodoItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            TodoItemMenu.m20888$$Nest$fputinsets(this.this$0, r5.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()));
            TodoItemMenu.m20874$$Nest$fgetcontainerView(this.this$0).setPadding(TodoItemMenu.m20875$$Nest$fgetinsets(this.this$0).left, TodoItemMenu.m20875$$Nest$fgetinsets(this.this$0).top, TodoItemMenu.m20875$$Nest$fgetinsets(this.this$0).right, TodoItemMenu.m20875$$Nest$fgetinsets(this.this$0).bottom);
            TodoItemMenu.m20887$$Nest$fgetwindowView(this.this$0).requestLayout();
            return WindowInsetsCompat.CONSUMED;
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$7, reason: invalid class name */
    public class AnonymousClass7 extends ChatMessageCell {
        private final Path clipPath;
        private final Paint shadowPaint;
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ int val$finalHeight;
        final /* synthetic */ int val$finalWidth;
        final /* synthetic */ int val$taskId;

        public AnonymousClass7(TodoItemMenu r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, int r7, int r8, int r9) {
            this.this$0 = r1;
            this.val$taskId = r7;
            this.val$finalWidth = r8;
            this.val$finalHeight = r9;
            super(r2, r3, r4, r5, r6);
            this.clipPath = new Path();
            this.shadowPaint = new Paint(1);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawOverlays(Canvas r2) {
            this.firstVisiblePollButton = 0;
            this.lastVisiblePollButton = this.pollButtons.size() - 1;
            super.drawOverlays(r2);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onDraw(Canvas r8) {
            r8.save();
            int r0 = getTodoIndex(this.val$taskId);
            float r1 = getPollButtonTop(r0);
            float r02 = getPollButtonBottom(r0);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(getPollButtonsLeft(), r1, getPollButtonsRight(), r02);
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r2, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
            this.shadowPaint.setColor(0);
            this.shadowPaint.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(-16777216, TodoItemMenu.m20880$$Nest$fgetopenProgress(this.this$0) * 0.2f));
            r8.drawRoundRect(r2, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.shadowPaint);
            r8.clipPath(this.clipPath);
            super.onDraw(r8);
            r8.restore();
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(this.val$finalWidth, this.val$finalHeight);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void setPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$8, reason: invalid class name */
    public class AnonymousClass8 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ TodoItemMenu this$0;

        public AnonymousClass8(TodoItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean didPressToDoButton(ChatMessageCell r1, TLRPC.TodoItem r2, boolean r3) {
            if (TodoItemMenu.m20871$$Nest$fgetcell(this.this$0).getDelegate() != null) goto L5;
            return false;
        L5:
            return TodoItemMenu.m20871$$Nest$fgetcell(this.this$0).getDelegate().didPressToDoButton(TodoItemMenu.m20871$$Nest$fgetcell(this.this$0), r2, r3);
        }
    }

    /* renamed from: org.telegram.ui.TodoItemMenu$9, reason: invalid class name */
    public class AnonymousClass9 extends ChatMessageCell {
        final /* synthetic */ TodoItemMenu this$0;
        final /* synthetic */ int val$finalHeight;
        final /* synthetic */ int val$finalWidth;

        public AnonymousClass9(TodoItemMenu r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, int r7, int r8) {
            this.this$0 = r1;
            this.val$finalWidth = r7;
            this.val$finalHeight = r8;
            super(r2, r3, r4, r5, r6);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawOverlays(Canvas r2) {
            this.firstVisiblePollButton = 0;
            this.lastVisiblePollButton = this.pollButtons.size() - 1;
            super.drawOverlays(r2);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(this.val$finalWidth, this.val$finalHeight);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void setPressed(boolean r1) {
        }
    }

    public static /* synthetic */ void $r8$lambda$25pfmdxeaxxu4Q_DfY2bM89Iw8c(TodoItemMenu r0, ChatActivity r1, int r2) {
        r0.lambda$setCell$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$8QUwrVxVsPuQ7viGPbwiwjmKPzk(TodoItemMenu r0) {
        r0.lambda$setupMessageOptions$11();
    }

    public static /* synthetic */ void $r8$lambda$AQVp7IuGsyhwNpfYtJyVojAG2Sg(TodoItemMenu r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$B1WyI4bwG6q2Ac2IZwHOsMrJdIM(TodoItemMenu r0, ChatActivity r1, int r2) {
        r0.lambda$setCell$7(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$MOzok5t424ApGzGeCLYmuiys7bA(TodoItemMenu r0, Utilities.Callback r1, int r2) {
        r0.lambda$setupMessageOptions$12(r1, r2);
    }

    /* renamed from: $r8$lambda$SJ4JZmuaA-OwEtC6CCid41LzRaA, reason: not valid java name */
    public static /* synthetic */ void m20862$r8$lambda$SJ4JZmuaAOwEtC6CCid41LzRaA(TodoItemMenu r0, View r1, Bitmap r2, Bitmap r3) {
        r0.lambda$prepareBlur$14(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$SNgLq7KFVVnufvvvnXEKtAN4rCM(TodoItemMenu r0) {
        r0.lambda$setupMessageOptions$9();
    }

    public static /* synthetic */ void $r8$lambda$akfuIXa_jWj6yATp1TuG9FeydL4(TodoItemMenu r0, ChatActivity r1, TLRPC.TodoItem r2) {
        r0.lambda$setCell$3(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$cNfkwBWw1TTktGlSIfcpZSgIfnE(TodoItemMenu r0, TLRPC.TL_messageMediaToDo r1, int r2, ChatActivity r3) {
        r0.lambda$setCell$8(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$gPnQTLX6z-XFOu4lnAb4cwX7NC4, reason: not valid java name */
    public static /* synthetic */ void m20863$r8$lambda$gPnQTLX6zXFOu4lnAb4cwX7NC4(TodoItemMenu r0) {
        r0.lambda$dismiss$15();
    }

    public static /* synthetic */ void $r8$lambda$gphvZuYzaby3l64C9SptheH9oNo(TodoItemMenu r0, ChatActivity r1, TLRPC.MessageMedia r2, ArrayList r3, boolean r4, int r5) {
        r0.lambda$setCell$6(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$kkl6QobsSFwobP16jaAdfsiFRNk(TodoItemMenu r0, boolean r1) {
        r0.lambda$dismiss$16(r1);
    }

    public static /* synthetic */ void $r8$lambda$mLUfFrS0uYztpBMWH8GBFNh9tHw(TodoItemMenu r0) {
        r0.lambda$setupMessageOptions$10();
    }

    public static /* synthetic */ void $r8$lambda$nqTZoCQuCS0rLt3e5kmSkv9tinc(TodoItemMenu r0, String r1) {
        r0.lambda$setCell$4(r1);
    }

    /* renamed from: $r8$lambda$pn2UaiJOh8JcOiZ9l-X52Wi58ns, reason: not valid java name */
    public static /* synthetic */ void m20864$r8$lambda$pn2UaiJOh8JcOiZ9lX52Wi58ns(TodoItemMenu r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$18(r1);
    }

    /* renamed from: $r8$lambda$u7vN4ZNtVATxvg9Rm-pU797tTbg, reason: not valid java name */
    public static /* synthetic */ void m20865$r8$lambda$u7vN4ZNtVATxvg9RmpU797tTbg(TodoItemMenu r0, ChatActivity r1, int r2) {
        r0.lambda$setCell$2(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$v3k0JcxZRvf4uJQiGeNF2zV8eYE(TodoItemMenu r0, View r1, MotionEvent r2) {
        return r0.lambda$setupMessageOptions$13(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$y04jnfvv8_zJo0iYUXN1GdLAAmo(TodoItemMenu r0) {
        r0.updateTranslation();
    }

    public static /* synthetic */ void $r8$lambda$yQFOEjTtn93yg6YlazhhVzzjRwM(TodoItemMenu r0, View r1) {
        r0.lambda$new$0(r1);
    }

    /* renamed from: $r8$lambda$z8n2cKZJXfxHU_MWOBQaSb-HIr8, reason: not valid java name */
    public static /* synthetic */ void m20866$r8$lambda$z8n2cKZJXfxHU_MWOBQaSbHIr8(TodoItemMenu r0, TLRPC.TodoItem r1) {
        r0.lambda$setCell$5(r1);
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m20867$$Nest$fgetblurBitmap(TodoItemMenu r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m20868$$Nest$fgetblurBitmapPaint(TodoItemMenu r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m20869$$Nest$fgetblurBitmapShader(TodoItemMenu r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m20870$$Nest$fgetblurMatrix(TodoItemMenu r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m20871$$Nest$fgetcell(TodoItemMenu r0) {
        return r0.cell;
    }

    /* renamed from: -$$Nest$fgetclipBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20872$$Nest$fgetclipBottom(TodoItemMenu r0) {
        return r0.clipBottom;
    }

    /* renamed from: -$$Nest$fgetclipTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20873$$Nest$fgetclipTop(TodoItemMenu r0) {
        return r0.clipTop;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20874$$Nest$fgetcontainerView(TodoItemMenu r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m20875$$Nest$fgetinsets(TodoItemMenu r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetmessageOptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20876$$Nest$fgetmessageOptionsView(TodoItemMenu r0) {
        return r0.messageOptionsView;
    }

    /* renamed from: -$$Nest$fgetmessageOptionsViewMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20877$$Nest$fgetmessageOptionsViewMaxWidth(TodoItemMenu r0) {
        return r0.messageOptionsViewMaxWidth;
    }

    /* renamed from: -$$Nest$fgetmyCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m20878$$Nest$fgetmyCell(TodoItemMenu r0) {
        return r0.myCell;
    }

    /* renamed from: -$$Nest$fgetmyTaskCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m20879$$Nest$fgetmyTaskCell(TodoItemMenu r0) {
        return r0.myTaskCell;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20880$$Nest$fgetopenProgress(TodoItemMenu r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetreactionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m20881$$Nest$fgetreactionsView(TodoItemMenu r0) {
        return r0.reactionsView;
    }

    /* renamed from: -$$Nest$fgetsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20882$$Nest$fgetsetCellInvisible(TodoItemMenu r0) {
        return r0.setCellInvisible;
    }

    /* renamed from: -$$Nest$fgetsetTaskInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m20883$$Nest$fgetsetTaskInvisible(TodoItemMenu r0) {
        return r0.setTaskInvisible;
    }

    /* renamed from: -$$Nest$fgettaskId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m20884$$Nest$fgettaskId(TodoItemMenu r0) {
        return r0.taskId;
    }

    /* renamed from: -$$Nest$fgettaskOptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m20885$$Nest$fgettaskOptionsView(TodoItemMenu r0) {
        return r0.taskOptionsView;
    }

    /* renamed from: -$$Nest$fgettaskOptionsViewMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m20886$$Nest$fgettaskOptionsViewMaxWidth(TodoItemMenu r0) {
        return r0.taskOptionsViewMaxWidth;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m20887$$Nest$fgetwindowView(TodoItemMenu r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20888$$Nest$fputinsets(TodoItemMenu r0, Insets r1) {
        r0.insets = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20889$$Nest$fputopenProgress(TodoItemMenu r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20890$$Nest$fputopenProgress2(TodoItemMenu r0, float r1) {
        r0.openProgress2 = r1;
    }

    /* renamed from: -$$Nest$fputsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20891$$Nest$fputsetCellInvisible(TodoItemMenu r0, boolean r1) {
        r0.setCellInvisible = r1;
    }

    /* renamed from: -$$Nest$fputsetTaskInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20892$$Nest$fputsetTaskInvisible(TodoItemMenu r0, boolean r1) {
        r0.setTaskInvisible = r1;
    }

    /* renamed from: -$$Nest$mcheckBitmapMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20893$$Nest$mcheckBitmapMatrix(TodoItemMenu r0) {
        r0.checkBitmapMatrix();
    }

    /* renamed from: -$$Nest$msetupTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20894$$Nest$msetupTranslation(TodoItemMenu r0) {
        r0.setupTranslation();
    }

    /* renamed from: -$$Nest$mupdateTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m20895$$Nest$mupdateTranslation(TodoItemMenu r0) {
        r0.updateTranslation();
    }

    public TodoItemMenu(Context r8, Theme.ResourcesProvider r9) {
        super(r8, R.style.TransparentDialog);
        this.insets = Insets.NONE;
        this.clipTop = 0.0f;
        this.clipBottom = 0.0f;
        this.taskOptionsViewMaxWidth = -1.0f;
        this.messageOptionsViewMaxWidth = -1.0f;
        this.dismissing = false;
        this.context = r8;
        this.resourcesProvider = r9;
        AnonymousClass1 r1 = new AnonymousClass1(this, r8);
        this.windowView = r1;
        r1.setOnClickListener(new TodoItemMenu$$ExternalSyntheticLambda17(this));
        BlurredBackgroundSourceBitmap r12 = new BlurredBackgroundSourceBitmap();
        this.iBlur3SourceBitmap = r12;
        BlurredBackgroundDrawableViewFactory r2 = new BlurredBackgroundDrawableViewFactory(r12);
        this.iBlur3Factory = r2;
        r2.setSourceRootView(new ViewPositionWatcher(this.windowView), this.windowView);
        AnonymousClass2 r13 = new AnonymousClass2(this, r8);
        this.containerView = r13;
        r13.setClipToPadding(false);
        this.windowView.addView(this.containerView, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass3 r14 = new AnonymousClass3(this, r8);
        this.viewPager = r14;
        r14.setAdapter(new AnonymousClass4(this, r8));
        this.containerView.addView(this.viewPager, LayoutHelper.createFrame(-1, -1, 119));
        AnonymousClass5 r15 = new AnonymousClass5(this, r8);
        this.menuContainer = r15;
        this.containerView.addView(r15, LayoutHelper.createFrame(-1, -1, 119));
        MessagePreviewView.TabsView r16 = new MessagePreviewView.TabsView(r8, r9);
        this.tabsView = r16;
        r16.addTab(0, LocaleController.getString(R.string.TodoMenuTabTask));
        this.tabsView.addTab(1, LocaleController.getString(R.string.TodoMenuTabList));
        this.containerView.addView(this.tabsView, LayoutHelper.createFrame(-1, 66, 80));
        MessagePreviewView.TabsView r0 = this.tabsView;
        ViewPagerFixed r17 = this.viewPager;
        Objects.requireNonNull(r17);
        r0.setOnTabClick(new PollItemMenu$$ExternalSyntheticLambda14(r17));
        MessagePreviewView.TabsView r02 = this.tabsView;
        r02.setBackground(r2.create(r02).setColorProvider(BlurredBackgroundProviderImpl.scrimMenuBackground(r9)).setHasPadding(true).setPadding(AndroidUtilities.dp(8.0f)).setRadius(AndroidUtilities.dp(16.0f)));
        TextView r92 = new TextView(r8);
        this.hintTextView = r92;
        r92.setTextSize(1, 13.0f);
        this.hintTextView.setTextColor(this.tabsView.getColor());
        this.hintTextView.setText(LocaleController.getString(R.string.TodoMenuHint));
        this.hintTextView.setGravity(17);
        this.containerView.addView(this.hintTextView, LayoutHelper.createFrame(-1, -2.0f, 80, 0.0f, 0.0f, 0.0f, 66.0f));
        ViewCompat.setOnApplyWindowInsetsListener(this.windowView, new AnonymousClass6(this));
    }

    private void animateOpenTo(boolean r10, Runnable r11) {
        ValueAnimator r0 = this.openAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        ValueAnimator r02 = this.open2Animator;
        if (r02 == null) goto L8;
        r02.cancel();
    L8:
        setupTranslation();
        float r03 = this.openProgress;
        float r1 = 0.0f;
        if (r10 == false) goto L11;
        float r3 = 1.0f;
    L12:
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{r03, r3});
        this.openAnimator = r32;
        r32.addUpdateListener(new TodoItemMenu$$ExternalSyntheticLambda15(this));
        this.openAnimator.addListener(new AnonymousClass14(this, r10, r11));
        if (r10 == true) goto L15;
        long r7 = 330;
    L16:
        ValueAnimator r112 = this.openAnimator;
        CubicBezierInterpolator r33 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r112.setInterpolator(r33);
        this.openAnimator.setDuration(r7);
        this.openAnimator.start();
        float r113 = this.openProgress2;
        if (r10 == false) goto L19;
        r1 = 1.0f;
    L19:
        ValueAnimator r114 = ValueAnimator.ofFloat(new float[]{r113, r1});
        this.open2Animator = r114;
        r114.addUpdateListener(new TodoItemMenu$$ExternalSyntheticLambda16(this));
        this.open2Animator.addListener(new AnonymousClass15(this, r10));
        this.open2Animator.setDuration((long) (r7 * 1.5f));
        this.open2Animator.setInterpolator(r33);
        this.open2Animator.start();
        return;
    L15:
        r7 = 520;
        goto L16
    L11:
        r3 = 0.0f;
        goto L12
    }

    private void checkBitmapMatrix() {
        Blur3Utils.checkBitmapSourceMatrixScale(this.iBlur3SourceBitmap, this.windowView);
        this.iBlur3Factory.invalidateAllLinkedViews();
    }

    private /* synthetic */ void lambda$animateOpenTo$17(ValueAnimator r1) {
        this.openProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.windowView.invalidate();
        this.containerView.invalidate();
        updateTranslation();
    }

    private /* synthetic */ void lambda$animateOpenTo$18(ValueAnimator r1) {
        this.openProgress2 = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$dismiss$15() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismiss$16(boolean r3) {
        AndroidUtilities.runOnUIThread(new TodoItemMenu$$ExternalSyntheticLambda19(this));
        ChatMessageCell r0 = this.cell;
        if (r0 == null) goto L8;
        r0.setVisibility(0);
        if (r3 == true) goto L7;
        ChatMessageCell r32 = this.cell;
        r32.syncTodoCheck(r32.getTodoIndex(this.taskId), this.myTaskCell);
    L7:
        ChatMessageCell r33 = this.cell;
        r33.doNotDrawTaskId = -1;
        r33.invalidate();
    L8:
        Runnable r34 = this.dismissListener;
        if (r34 == null) goto L12;
        AndroidUtilities.runOnUIThread(r34);
        this.dismissListener = null;
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$prepareBlur$14(View r3, Bitmap r4, Bitmap r5) {
        if (r3 == null) goto L4;
        r3.setVisibility(0);
    L4:
        this.blurBitmap = r4;
        Paint r32 = new Paint(1);
        this.blurBitmapPaint = r32;
        Bitmap r0 = this.blurBitmap;
        Shader.TileMode r1 = Shader.TileMode.CLAMP;
        BitmapShader r42 = new BitmapShader(r0, r1, r1);
        this.blurBitmapShader = r42;
        r32.setShader(r42);
        ColorMatrix r33 = new ColorMatrix();
        if (Theme.isCurrentThemeDark() == false) goto L7;
        float r43 = 0.05f;
    L8:
        AndroidUtilities.adjustSaturationColorMatrix(r33, r43);
        if (Theme.isCurrentThemeDark() == false) goto L11;
        float r44 = -0.02f;
    L12:
        AndroidUtilities.adjustBrightnessColorMatrix(r33, r44);
        this.blurBitmapPaint.setColorFilter(new ColorMatrixColorFilter(r33));
        this.blurMatrix = new Matrix();
        this.iBlur3SourceBitmap.setBitmap(r5);
        checkBitmapMatrix();
        return;
    L11:
        r44 = -0.04f;
        goto L12
    L7:
        r43 = 0.25f;
        goto L8
    }

    private /* synthetic */ void lambda$setCell$1(ChatActivity r3, int r4) {
        if (r3.isInScheduleMode() == false) goto L5;
        Toast.makeText(getContext(), LocaleController.getString(R.string.MessageScheduledTodo), 1).show();
    L6:
        dismiss(true);
        return;
    L5:
        ChatMessageCell r32 = this.myTaskCell;
        r32.toggleTodoCheck(r32.getTodoIndex(r4), false);
        goto L6
    }

    private /* synthetic */ void lambda$setCell$2(ChatActivity r3, int r4) {
        if (r3.isInScheduleMode() == false) goto L5;
        Toast.makeText(getContext(), LocaleController.getString(R.string.MessageScheduledTodo), 1).show();
    L6:
        dismiss(true);
        return;
    L5:
        ChatMessageCell r32 = this.myTaskCell;
        r32.toggleTodoCheck(r32.getTodoIndex(r4), false);
        goto L6
    }

    private /* synthetic */ void lambda$setCell$3(ChatActivity r2, TLRPC.TodoItem r3) {
        MessageObject r0 = this.messageObject;
        r2.showFieldPanelForReplyQuote(r0, ChatActivity.ReplyQuote.from(r0, r3.id));
        dismiss(false);
    }

    private /* synthetic */ void lambda$setCell$4(String r1) {
        AndroidUtilities.addToClipboard(r1);
        dismiss(true);
    }

    private /* synthetic */ void lambda$setCell$5(TLRPC.TodoItem r2) {
        AndroidUtilities.addToClipboard(MessageObject.formatTextWithEntities(r2.title, false));
        dismiss(true);
    }

    private /* synthetic */ void lambda$setCell$6(ChatActivity r13, TLRPC.MessageMedia r14, ArrayList r15, boolean r16, int r17) {
        if ((r14 instanceof TLRPC.TL_messageMediaToDo) == false) goto L7;
        TLRPC.MessageMedia r0 = this.messageObject.messageOwner.media;
        if ((r0 instanceof TLRPC.TL_messageMediaToDo) == false) goto L7;
        ArrayList<TLRPC.TodoCompletion> r02 = ((TLRPC.TL_messageMediaToDo) r0).completions;
        ((TLRPC.TL_messageMediaToDo) r14).completions = r02;
    L7:
        this.messageObject.messageOwner.media = r14;
        r13.getSendMessagesHelper().editMessage(this.messageObject, null, null, null, null, null, null, false, false, null);
    }

    private /* synthetic */ void lambda$setCell$7(ChatActivity r4, int r5) {
        PollCreateActivity r0 = new PollCreateActivity(r4, true, Boolean.FALSE);
        r0.setEditing(MessageObject.getMedia(this.messageObject), false, r5);
        r0.setDelegate(new TodoItemMenu$$ExternalSyntheticLambda18(this, r4));
        r4.presentFragment(r0);
        dismiss(false);
    }

    private /* synthetic */ void lambda$setCell$8(TLRPC.TL_messageMediaToDo r13, int r14, ChatActivity r15) {
        int r1 = 0;
    L4:
        if (r1 >= r13.todo.list.size()) goto L9;
        if (r13.todo.list.get(r1).id != r14) goto L8;
        r13.todo.list.remove(r1);
        r1 = r1 - 1;
    L8:
        r1 = r1 + 1;
        goto L4
    L9:
        int r12 = 0;
    L11:
        if (r12 >= r13.completions.size()) goto L19;
        if (r13.completions.get(r12).id != r14) goto L18;
        r13.completions.remove(r12);
        if (r13.completions.isEmpty() == false) goto L17;
        r13.flags &= -2;
    L17:
        r12 = r12 - 1;
    L18:
        r12 = r12 + 1;
        goto L11
    L19:
        this.messageObject.messageOwner.media = r13;
        r15.getSendMessagesHelper().editMessage(this.messageObject, null, null, null, null, null, null, false, false, null);
        r15.updateVisibleRows();
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$10() {
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$11() {
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$12(Utilities.Callback r2, int r3) {
        r2.run(Integer.valueOf(r3));
        boolean r22 = true;
        if (r3 != 1) goto L5;
    L8:
        dismiss(r22);
        return;
    L5:
        if (r3 == 13) goto L8;
        r22 = false;
        goto L8
    }

    private /* synthetic */ boolean lambda$setupMessageOptions$13(View r3, MotionEvent r4) {
        if (this.messageOptionsView != null) goto L5;
        return false;
    L5:
        if (r4.getAction() != 0) goto L12;
        Drawable r32 = ((ActionBarPopupWindow.ActionBarPopupWindowLayout) this.messageOptionsView).getBackgroundDrawable();
        RectF r0 = AndroidUtilities.rectTmp;
        r0.set(r32.getBounds());
        r0.offset(this.messageOptionsView.getX(), this.messageOptionsView.getY());
        if (r0.contains(r4.getX(), r4.getY()) == true) goto L13;
        dismiss(true);
        return true;
    L13:
        return false;
    L12:
        return false;
    }

    private /* synthetic */ void lambda$setupMessageOptions$9() {
        dismiss(false);
    }

    private void prepareBlur(View r2) {
        if (r2 == null) goto L4;
        r2.setVisibility(4);
    L4:
        ScrimOptions.makeGlobalBlurBitmaps(new TodoItemMenu$$ExternalSyntheticLambda7(this, r2));
    }

    private void setupTranslation() {
        if (this.hasTranslation == false) goto L5;
        return;
    L5:
        if (this.windowView.getWidth() <= 0) goto L29;
        ChatMessageCell r0 = this.cell;
        if (r0 == null) goto L25;
        int[] r3 = new int[2];
        r0.getLocationOnScreen(r3);
        int r02 = r3[0];
        Insets r4 = this.insets;
        this.tx = r02 - r4.left;
        float r03 = r3[1] - r4.top;
        this.ty = r03;
        if (this.hasDestTranslation == true) goto L24;
        this.hasDestTranslation = true;
        this.dtx1 = 0.0f;
        this.dty1 = r03;
        if (this.messageOptionsView == null) goto L16;
        float r04 = (r03 + this.cell.getHeight()) + this.messageOptionsView.getHeight();
        int r32 = this.windowView.getHeight();
        Insets r42 = this.insets;
        if (r04 <= (((r32 - r42.top) - r42.bottom) - AndroidUtilities.dp(66.0f))) goto L16;
        int r05 = this.windowView.getHeight();
        Insets r33 = this.insets;
        this.dty1 = ((((r05 - r33.top) - r33.bottom) - AndroidUtilities.dp(66.0f)) - this.cell.getHeight()) - this.messageOptionsView.getHeight();
    L16:
        int r06 = this.myTaskCell.getTodoIndex(this.taskId);
        this.myTaskCell.getPollButtonTop(r06);
        float r07 = this.myTaskCell.getPollButtonBottom(r06);
        this.dtx2 = 0.0f;
        float r1 = this.ty;
        this.dty2 = r1;
        float r34 = (int) r07;
        float r12 = r1 + r34;
        int r43 = this.windowView.getHeight();
        Insets r5 = this.insets;
        if (r12 <= ((((r43 - r5.top) - r5.bottom) - AndroidUtilities.dp(78.0f)) - this.hintTextView.getHeight())) goto L20;
        int r13 = this.windowView.getHeight();
        Insets r44 = this.insets;
        this.dty2 = ((((r13 - r44.top) - r44.bottom) - AndroidUtilities.dp(78.0f)) - this.hintTextView.getHeight()) - r0;
    L20:
        if (this.taskOptionsView == null) goto L24;
        float r45 = (this.dty2 + r34) + r1.getHeight();
        int r14 = this.windowView.getHeight();
        Insets r35 = this.insets;
        if (r45 <= ((((r14 - r35.top) - r35.bottom) - AndroidUtilities.dp(78.0f)) - this.hintTextView.getHeight())) goto L24;
        int r15 = this.windowView.getHeight();
        Insets r36 = this.insets;
        this.dty2 = (((((r15 - r36.top) - r36.bottom) - AndroidUtilities.dp(78.0f)) - this.hintTextView.getHeight()) - r0) - this.taskOptionsView.getHeight();
    L24:
        updateTranslation();
    L26:
        this.hasTranslation = true;
        return;
    L25:
        this.ty = 0.0f;
        this.tx = 0.0f;
        goto L26
    }

    private void updateTranslation() {
        float r0 = this.viewPager.getPositionAnimated();
        int r2 = 0;
        float r1 = AndroidUtilities.lerp(0, -this.viewPager.getWidth(), r0);
        float r3 = AndroidUtilities.lerp(this.viewPager.getWidth(), 0, r0);
        if (this.hasTranslation == false) goto L11;
        View r4 = this.messageOptionsView;
        if ((r4 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L11;
        ActionBarPopupWindow.ActionBarPopupWindowLayout r6 = (ActionBarPopupWindow.ActionBarPopupWindowLayout) r4;
        this.dtx1 = 0.0f;
        float r7 = this.ty;
        this.dty1 = r7;
        if (r4 == null) goto L11;
        float r72 = (r7 + this.cell.getHeight()) + r6.getVisibleHeight();
        int r42 = this.windowView.getHeight();
        Insets r8 = this.insets;
        if (r72 <= (((r42 - r8.top) - r8.bottom) - AndroidUtilities.dp(66.0f))) goto L11;
        int r43 = this.windowView.getHeight();
        Insets r73 = this.insets;
        this.dty1 = ((((r43 - r73.top) - r73.bottom) - AndroidUtilities.dp(66.0f)) - this.cell.getHeight()) - r6.getVisibleHeight();
    L11:
        ChatMessageCell r44 = this.myCell;
        float r62 = this.tx;
        float r74 = this.dtx1;
        if (this.dismissingWithAlpha == false) goto L14;
        float r82 = 1.0f;
    L15:
        r44.setTranslationX(AndroidUtilities.lerp(r62, r74, r82) + r3);
        ChatMessageCell r45 = this.myCell;
        float r63 = this.ty;
        float r75 = this.dty1;
        if (this.dismissingWithAlpha == false) goto L18;
        float r83 = 1.0f;
    L19:
        r45.setTranslationY(AndroidUtilities.lerp(r63, r75, r83));
        View r46 = this.messageOptionsView;
        if (r46 == null) goto L30;
        boolean r10 = this.isOut;
        float r11 = this.dtx1;
        if (r10 == false) goto L24;
        r46.setTranslationX(((((r11 + r3) + this.myCell.getLeft()) + this.myCell.getPollButtonsLeft()) - AndroidUtilities.dp(8.0f)) - this.messageOptionsView.getLeft());
    L29:
        this.messageOptionsViewMaxWidth = this.menuContainer.getMeasuredWidth() - (this.messageOptionsView.getX() - r3);
        this.messageOptionsView.setTranslationY(((this.myCell.getY() + this.myCell.getHeight()) - this.messageOptionsView.getTop()) - this.menuContainer.getTop());
        this.messageOptionsView.setAlpha(this.openProgress);
        float r47 = AndroidUtilities.lerp(0.75f, 1.0f, this.openProgress);
        this.messageOptionsView.setScaleX(r47);
        this.messageOptionsView.setScaleY(r47);
        goto L30
    L24:
        float r112 = r11 + r3;
        if (this.myCell.needDrawAvatarOutside() == false) goto L27;
        int r102 = AndroidUtilities.dp(48.0f);
    L28:
        r46.setTranslationX(((r112 + r102) + this.myCell.getLeft()) - this.messageOptionsView.getLeft());
        goto L29
    L27:
        r102 = 0;
    L30:
        ChatMessageCell r48 = this.myTaskCell;
        float r103 = this.tx;
        float r113 = this.dtx2;
        if (this.dismissingWithAlpha == false) goto L33;
        float r12 = 1.0f;
    L34:
        r48.setTranslationX(AndroidUtilities.lerp(r103, r113, r12) + r1);
        ChatMessageCell r49 = this.myTaskCell;
        float r104 = this.ty;
        float r114 = this.dty2;
        if (this.dismissingWithAlpha == false) goto L37;
        float r122 = 1.0f;
    L38:
        r49.setTranslationY(AndroidUtilities.lerp(r104, r114, r122));
        if (this.taskOptionsView == null) goto L49;
        int r410 = this.myTaskCell.getTodoIndex(this.taskId);
        this.myTaskCell.getPollButtonTop(r410);
        float r411 = this.myTaskCell.getPollButtonBottom(r410);
        boolean r105 = this.isOut;
        View r115 = this.taskOptionsView;
        if (r105 == false) goto L43;
        r115.setTranslationX(((((this.dtx2 + r1) + this.myTaskCell.getLeft()) + this.myTaskCell.getPollButtonsLeft()) - AndroidUtilities.dp(8.0f)) - this.taskOptionsView.getLeft());
    L47:
        this.taskOptionsViewMaxWidth = this.menuContainer.getMeasuredWidth() - (this.taskOptionsView.getX() - r3);
        this.taskOptionsView.setTranslationY(((this.myTaskCell.getY() + ((int) r411)) - this.taskOptionsView.getTop()) - this.menuContainer.getTop());
        this.taskOptionsView.setAlpha(this.openProgress);
        float r22 = AndroidUtilities.lerp(0.75f, 1.0f, this.openProgress);
        this.taskOptionsView.setScaleX(r22);
        this.taskOptionsView.setScaleY(r22);
        goto L49
    L43:
        float r84 = this.dtx2 + r1;
        if (this.myTaskCell.needDrawAvatarOutside() == false) goto L46;
        r2 = AndroidUtilities.dp(48.0f);
    L46:
        r115.setTranslationX(((r84 + r2) + this.myTaskCell.getLeft()) - this.taskOptionsView.getLeft());
    L49:
        if (this.dismissingWithAlpha == false) goto L52;
        this.myCell.setAlpha(this.openProgress);
        this.myTaskCell.setAlpha(this.openProgress);
    L52:
        if (this.reactionsView == null) goto L56;
        float r32 = r3 + Math.max(0.0f, ((this.myCell.getBoundsRight() + this.myCell.getBoundsLeft()) / 2.0f) - (this.reactionsView.getWidth() * 0.8f));
        this.reactionsView.setTranslationX(r32);
        this.reactionsView.setTranslationY(Math.max(0.0f, ((this.myCell.getY() - this.reactionsView.getHeight()) + AndroidUtilities.dp(22.0f)) - this.menuContainer.getTop()));
        this.reactionsView.setAlpha(this.openProgress);
        View r23 = this.reactionsView.getWindowView();
        if (r23 == null) goto L56;
        r23.setTranslationX(r32);
        r23.setAlpha(this.openProgress);
    L56:
        this.hintTextView.setTranslationX(r1);
        this.hintTextView.setAlpha(this.openProgress);
        this.tabsView.setSelectedTab(r0);
        this.tabsView.setAlpha(this.openProgress);
        return;
    L37:
        r122 = this.openProgress;
        goto L38
    L33:
        r12 = this.openProgress;
        goto L34
    L18:
        r83 = this.openProgress;
        goto L19
    L14:
        r82 = this.openProgress;
        goto L15
    }

    @Override // android.app.Dialog, android.content.DialogInterface
    public void dismiss() {
        dismiss(true);
    }

    public void dismiss(boolean r6) {
        if (r6 == false) goto L12;
        ReactionsContainerLayout r0 = this.reactionsView;
        if (r0 == null) goto L12;
        if (r0.getReactionsWindow() == null) goto L12;
        if (this.reactionsView.getReactionsWindow().isShowing() == false) goto L12;
        this.reactionsView.dismissWindow();
        return;
    L12:
        if (this.dismissing == false) goto L14;
        return;
    L14:
        this.dismissing = true;
        this.hasTranslation = false;
        this.viewPager.cancelTouches();
        if (this.viewPager.getCurrentPosition() != 1) goto L17;
        boolean r2 = true;
    L18:
        if (r6 == false) goto L23;
        if (r2 == false) goto L23;
        ChatMessageCell r3 = this.cell;
        if (r3 == null) goto L27;
        r3.setVisibility(4);
        this.cell.invalidate();
    L27:
        this.dismissingWithAlpha = !r6;
        setupTranslation();
        this.open = false;
        animateOpenTo(false, new TodoItemMenu$$ExternalSyntheticLambda6(this, r2));
        this.windowView.invalidate();
        return;
    L23:
        if (r6 == true) goto L27;
        ChatMessageCell r32 = this.cell;
        if (r32 == null) goto L27;
        r32.setVisibility(0);
        ChatMessageCell r33 = this.cell;
        r33.doNotDrawTaskId = -1;
        r33.invalidate();
        goto L27
    L17:
        r2 = false;
        goto L18
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
        r0.softInputMode = 48;
        r0.flags = r1 | (-1945959040);
        AndroidUtilities.applyEdgeToEdgeLayoutParams(r0);
        r42.setAttributes(r0);
        this.windowView.setSystemUiVisibility(1284);
        AndroidUtilities.setLightNavigationBar(this.windowView, !Theme.isCurrentThemeDark());
    }

    public void setCell(ChatActivity r23, ChatMessageCell r24, int r25) {
        this.cell = r24;
        this.taskId = r25;
        TLRPC.TodoCompletion r11 = null;
        if (r24 == null) goto L5;
        MessageObject r2 = r24.getMessageObject();
    L6:
        this.messageObject = r2;
        if (r2 != null) goto L9;
    L11:
        boolean r22 = false;
    L12:
        this.isOut = r22;
        if (this.cell == null) goto L18;
        this.clipTop = r23.getChatListViewPadding() - AndroidUtilities.dp(4.0f);
        this.clipBottom = r24.parentBoundsBottom;
        if ((r24.getParent() instanceof View) == false) goto L17;
        View r0 = (View) r24.getParent();
        this.clipTop += r0.getY();
        this.clipBottom += r0.getY();
    L17:
        int r8 = this.cell.getWidth();
        int r9 = this.cell.getHeight();
        this.heightdiff = r9 - this.cell.getHeight();
        AnonymousClass7 r02 = new AnonymousClass7(this, getContext(), UserConfig.selectedAccount, false, null, this.cell.getResourcesProvider(), r25, r8, r9);
        int r92 = r25;
        this.myTaskCell = r02;
        this.cell.copyParamsTo(r02);
        this.myTaskCell.copySpoilerEffect2AttachIndexFrom(this.cell);
        this.myTaskCell.setDelegate(new AnonymousClass8(this));
        ChatMessageCell r15 = this.myTaskCell;
        MessageObject r03 = this.messageObject;
        MessageObject.GroupedMessages r17 = this.cell.getCurrentMessagesGroup();
        ChatMessageCell r26 = this.cell;
        r15.setMessageObject(r03, r17, r26.pinnedBottom, r26.pinnedTop, r26.firstInChat);
        this.containerView.addView(this.myTaskCell, new FrameLayout.LayoutParams(this.cell.getWidth(), r9, 51));
        AnonymousClass9 r04 = new AnonymousClass9(this, getContext(), UserConfig.selectedAccount, false, null, this.cell.getResourcesProvider(), r8, r9);
        this.myCell = r04;
        this.cell.copyVisiblePartTo(r04);
        this.cell.copyParamsTo(this.myCell);
        this.myCell.copySpoilerEffect2AttachIndexFrom(this.cell);
        this.myCell.setDelegate(new AnonymousClass10(this));
        ChatMessageCell r152 = this.myCell;
        MessageObject r05 = this.messageObject;
        MessageObject.GroupedMessages r172 = this.cell.getCurrentMessagesGroup();
        ChatMessageCell r27 = this.cell;
        r152.setMessageObject(r05, r172, r27.pinnedBottom, r27.pinnedTop, r27.firstInChat);
        this.containerView.addView(this.myCell, new FrameLayout.LayoutParams(this.cell.getWidth(), r9, 51));
    L19:
        this.viewPager.bringToFront();
        this.menuContainer.bringToFront();
        this.tabsView.bringToFront();
        this.viewPager.onTabAnimationUpdate(false);
        ItemOptions r06 = ItemOptions.makeOptions(this.containerView, this.resourcesProvider, null);
        TLRPC.TL_messageMediaToDo r28 = (TLRPC.TL_messageMediaToDo) MessageObject.getMedia(this.messageObject);
        int r3 = 0;
    L21:
        if (r3 >= r28.todo.list.size()) goto L26;
        if (r28.todo.list.get(r3).id == r92) goto L24;
        r3 = r3 + 1;
        goto L21
    L24:
        TLRPC.TodoItem r4 = r28.todo.list.get(r3);
    L27:
        int r5 = 0;
    L29:
        if (r5 >= r28.completions.size()) goto L35;
        if (r28.completions.get(r5).id == r92) goto L32;
        r5 = r5 + 1;
        goto L29
    L32:
        r11 = r28.completions.get(r5);
    L35:
        if (this.messageObject.canCompleteTodo() == false) goto L39;
        if (r11 == null) goto L38;
        r06.addText(LocaleController.formatTodoCompletedDate(r11.date), 14);
        r06.addGap();
        r06.add(R.drawable.msg_cancel, LocaleController.getString(R.string.TodoUncheck), new TodoItemMenu$$ExternalSyntheticLambda8(this, r23, r92));
        goto L39
    L38:
        r06.add(R.drawable.msg_select, LocaleController.getString(R.string.TodoCheck), new TodoItemMenu$$ExternalSyntheticLambda9(this, r23, r92));
    L39:
        if (r4 == null) goto L50;
        if (r23 == null) goto L43;
        r06.add(R.drawable.menu_reply, LocaleController.getString(R.string.TodoItemQuote), new TodoItemMenu$$ExternalSyntheticLambda10(this, r23, r4));
    L43:
        if (this.messageObject.getDialogId() >= 0) goto L48;
        MessagesController r52 = MessagesController.getInstance(this.messageObject.currentAccount);
        String r6 = DialogObject.getPublicUsername(r52.getUserOrChat(this.messageObject.getDialogId()));
        StringBuilder r7 = new StringBuilder("https://");
        r7.append(r52.linkPrefix);
        r7.append("/");
        if (TextUtils.isEmpty(r6) == false) goto L47;
        r6 = "c/" + (-this.messageObject.getDialogId());
    L47:
        r7.append(r6);
        r7.append("/");
        r7.append(this.messageObject.getId());
        r7.append("?task=");
        r7.append(r4.id);
        r06.add(R.drawable.msg_link, LocaleController.getString(R.string.CopyLink), new TodoItemMenu$$ExternalSyntheticLambda11(this, r7.toString()));
    L48:
        r06.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new TodoItemMenu$$ExternalSyntheticLambda12(this, r4));
    L50:
        if (this.messageObject.canEditMessage(r23.currentChat) == false) goto L54;
        r06.add(R.drawable.msg_edit, LocaleController.getString(R.string.TodoEditItem), new TodoItemMenu$$ExternalSyntheticLambda13(this, r23, r3));
        if (r28.todo.list.size() <= 1) goto L54;
        r06.add(R.drawable.msg_delete, LocaleController.getString(R.string.TodoDeleteItem), new TodoItemMenu$$ExternalSyntheticLambda14(this, r28, r92, r23));
    L54:
        r06.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, this.resourcesProvider), 0.06f));
        r06.setBlurBackground(this.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(this.resourcesProvider), false);
        r06.setupSelectors();
        ViewGroup r07 = r06.getLayout();
        this.taskOptionsView = r07;
        r07.setPivotX(0.0f);
        this.taskOptionsView.setPivotY(0.0f);
        this.menuContainer.addView(this.taskOptionsView, LayoutHelper.createFrame(-2, -2, 51));
        return;
    L26:
        r3 = -1;
        r4 = null;
        goto L27
    L18:
        r92 = r25;
        goto L19
    L9:
        if (r2.isOutOwner() == false) goto L11;
        r22 = true;
        goto L12
    L5:
        r2 = null;
        goto L6
    }

    public void setOnDismissListener(Runnable r1) {
        this.dismissListener = r1;
    }

    public void setupMessageOptions(ChatActivity r20, ArrayList<Integer> r21, ArrayList<CharSequence> r22, ArrayList<Integer> r23, Utilities.Callback<Integer> r24) {
        MessageObject r8 = this.messageObject;
        List<TLRPC.TL_availableReaction> r0 = r20.getMediaDataController().getEnabledReactionsList();
        if (r20.isSecretChat() == false) goto L5;
    L23:
        boolean r3 = false;
    L25:
        if (r8.isForwardedChannelPost() == false) goto L47;
        TLRPC.ChatFull r4 = r20.getMessagesController().getChatFull(-r8.getFromChatId());
        if (r4 != null) goto L30;
    L28:
        boolean r02 = true;
    L45:
        boolean r11 = r02;
        if (r3 == false) goto L75;
    L111:
        boolean r03 = false;
    L113:
        if (r20.currentChat != null) goto L115;
    L121:
        if (r3 == false) goto L123;
    L163:
        boolean r42 = false;
    L164:
        TLRPC.User r5 = r20.currentUser;
        if (r5 == null) goto L171;
        if (UserObject.isReplyUser(r5) == false) goto L169;
    L177:
        boolean r13 = false;
    L178:
        TLRPC.User r52 = r20.currentUser;
        if (r52 == null) goto L185;
        if (UserObject.isReplyUser(r52) == false) goto L183;
    L195:
        boolean r14 = false;
    L196:
        FrameLayout r53 = this.containerView;
        Theme.ResourcesProvider r6 = r20.getResourceProvider();
        if (r3 == true) goto L201;
        if (r03 == true) goto L201;
        boolean r32 = false;
    L202:
        ItemOptions r62 = ItemOptions.makeOptions(r53, r6, null, r32);
        if (r03 == false) goto L214;
        MessageSeenView r04 = new MessageSeenView(getContext(), r20.getCurrentAccount(), r8, r20.currentChat);
        FrameLayout r33 = new FrameLayout(getContext());
        r33.addView(r04, LayoutHelper.createFrame(-1, 36.0f));
        ItemOptions r7 = r62.makeSwipeback();
        ActionBarMenuSubItem r43 = new ActionBarMenuSubItem(getContext(), true, false, this.resourcesProvider);
        r43.setItemHeight(44);
        r43.setTextAndIcon(LocaleController.getString(R.string.Back), R.drawable.msg_arrow_back);
        AnimatedEmojiSpan.TextViewEmojis r12 = r43.getTextView();
        if (LocaleController.isRTL == false) goto L207;
        int r15 = 0;
    L209:
        if (LocaleController.isRTL == false) goto L211;
        int r54 = AndroidUtilities.dp(40.0f);
    L212:
        r12.setPadding(r15, 0, r54, 0);
        FrameLayout r55 = new FrameLayout(getContext());
        LinearLayout r122 = new LinearLayout(getContext());
        r122.setBackgroundColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuBackground, this.resourcesProvider));
        r122.setOrientation(1);
        RecyclerListView r10 = r04.createListView();
        r55.addView(r43);
        r122.addView(r55);
        int r152 = -1;
        r122.addView(new ActionBarPopupWindow.GapView(getContext(), this.resourcesProvider), LayoutHelper.createLinear(-1, 8));
        r55.setOnClickListener(new AnonymousClass11(this, r62));
        int r102 = -2;
        AnonymousClass12 r05 = new AnonymousClass12(this, r04, r20, r10, r122, r62, r7);
        ItemOptions r72 = r62;
        TodoItemMenu r63 = this;
        r04.setOnClickListener(r05);
        r122.addView(r10, LayoutHelper.createLinear(-1, -2));
        r7.addView(r122);
        r72.addView(r33);
        r72.addGap();
    L213:
        MessageObject r34 = r8;
    L217:
        if (r13 == false) goto L219;
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 1, r34, new TodoItemMenu$$ExternalSyntheticLambda1(r63), r63.resourcesProvider), LayoutHelper.createLinear(r152, 36));
        r72.addGap();
    L219:
        if (r14 == false) goto L221;
        MessageObject r82 = r34;
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 2, r34, new TodoItemMenu$$ExternalSyntheticLambda2(r63), r63.resourcesProvider), LayoutHelper.createLinear(r152, 36));
        r72.addGap();
    L222:
        int r06 = r21.size();
        int r1 = 0;
    L223:
        if (r1 >= r06) goto L225;
        r72.add(r21.get(r1).intValue(), r22.get(r1), new TodoItemMenu$$ExternalSyntheticLambda3(r63, r24, r23.get(r1).intValue()));
        r1 = r1 + 1;
        goto L223
    L225:
        r72.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r63.resourcesProvider), 0.06f));
        r72.setBlurBackground(r63.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(r63.resourcesProvider), false);
        r72.setupSelectors();
        ViewGroup r07 = r72.getLayout();
        r63.messageOptionsView = r07;
        r07.setPivotX(0.0f);
        r63.messageOptionsView.setPivotY(0.0f);
        r63.menuContainer.addView(r63.messageOptionsView, LayoutHelper.createFrame(r102, r102, 51));
        View r08 = r63.messageOptionsView;
        if ((r08 instanceof ActionBarPopupWindow.ActionBarPopupWindowLayout) == false) goto L228;
        ((ActionBarPopupWindow.ActionBarPopupWindowLayout) r08).setOnSizeChangedListener(new TodoItemMenu$$ExternalSyntheticLambda4(r63));
        r63.messageOptionsView.setOnTouchListener(new TodoItemMenu$$ExternalSyntheticLambda5(r63));
    L228:
        if (r11 == true) goto L230;
    L246:
        r63.updateTranslation();
        return;
    L230:
        if (r20.getUserConfig().getClientUserId() != r20.getDialogId()) goto L232;
        boolean r09 = true;
    L234:
        if (r09 == false) goto L236;
        int r123 = 3;
    L237:
        ReactionsContainerLayout r16 = new ReactionsContainerLayout(r123, r20, r63.getContext(), r20.getCurrentAccount(), r63.resourcesProvider);
        r16.forceAttachToParent = true;
        int r35 = AndroidUtilities.dp(4.0f);
        int r56 = 24;
        if (LocaleController.isRTL == false) goto L240;
        int r44 = 0;
    L241:
        int r36 = r35 + r44;
        int r45 = AndroidUtilities.dp(4.0f);
        int r17 = AndroidUtilities.dp(4.0f);
        if (LocaleController.isRTL == true) goto L245;
        r56 = 0;
    L245:
        r16.setPadding(r36, r45, r17 + r56, AndroidUtilities.dp(22.0f));
        r16.setDelegate(new AnonymousClass13(r63, r20, r82, r16));
        FrameLayout r18 = r63.menuContainer;
        r63.reactionsView = r16;
        r18.addView(r16, LayoutHelper.createFrame(r102, (int) (((r16.getTopOffset() / AndroidUtilities.density) + 52.0f) + 22.0f), 51));
        r16.setMessage(r82, r20.chatInfo, true);
        r63.reactionsView.setTransitionProgress(1.0f);
        goto L246
    L240:
        r44 = 24;
        goto L241
    L236:
        r123 = 0;
        goto L237
    L232:
        r09 = false;
        goto L234
    L221:
        r82 = r34;
        goto L222
    L211:
        r54 = 0;
        goto L212
    L207:
        r15 = AndroidUtilities.dp(40.0f);
        goto L209
    L214:
        r152 = -1;
        r72 = r62;
        r102 = -2;
        r63 = this;
        if (r42 == false) goto L213;
        r34 = r8;
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 0, r34, new TodoItemMenu$$ExternalSyntheticLambda0(r63), r63.resourcesProvider), LayoutHelper.createLinear(-1, 36));
        r72.addGap();
    L201:
        r32 = true;
        goto L202
    L183:
        if (UserObject.isAnonymous(r20.currentUser) == true) goto L195;
    L185:
        if (r20.isInScheduleMode() == true) goto L195;
        if (r8.needDrawForwarded() == false) goto L195;
        TLRPC.Message r57 = r8.messageOwner;
        TLRPC.MessageFwdHeader r64 = r57.fwd_from;
        if (r64 == null) goto L195;
        if (r64.date <= 0) goto L195;
        if ((r57.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == true) goto L195;
        r14 = true;
        goto L196
    L169:
        if (UserObject.isAnonymous(r20.currentUser) == true) goto L177;
    L171:
        if (r20.isInScheduleMode() == true) goto L177;
        if (r8.isEdited() == false) goto L177;
        if ((r8.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == true) goto L177;
        r13 = true;
        goto L178
    L123:
        if (r20.currentChat != null) goto L163;
        if (r20.currentEncryptedChat != null) goto L163;
        TLRPC.User r46 = r20.currentUser;
        if (r46 == null) goto L163;
        if (UserObject.isUserSelf(r46) == true) goto L163;
        if (UserObject.isReplyUser(r20.currentUser) == true) goto L163;
        if (UserObject.isAnonymous(r20.currentUser) == true) goto L163;
        TLRPC.User r47 = r20.currentUser;
        if (r47.bot == true) goto L163;
        if (UserObject.isService(r47.id) == true) goto L163;
        TLRPC.UserFull r48 = r20.userInfo;
        if (r48 == null) goto L143;
        if (r48.read_dates_private == true) goto L163;
    L143:
        if (r20.isInScheduleMode() == true) goto L163;
        if (r8.isOutOwner() == false) goto L163;
        if (r8.isSent() == false) goto L163;
        if (r8.isEditing() == true) goto L163;
        if (r8.isSending() == true) goto L163;
        if (r8.isSendError() == true) goto L163;
        if (r8.isContentUnread() == true) goto L163;
        if (r8.isUnread() == true) goto L163;
        if ((r20.getConnectionsManager().getCurrentTime() - r8.messageOwner.date) >= r20.getMessagesController().pmReadDateExpirePeriod) goto L163;
        if ((r8.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == true) goto L163;
        r42 = true;
        goto L164
    L115:
        if (r8.isOut() == true) goto L121;
        if (ChatObject.isMonoForum(r20.currentChat) == false) goto L121;
        if (ChatObject.canManageMonoForum(r20.getCurrentAccount(), r20.currentChat) == false) goto L121;
        int r49 = ((-r20.currentChat.linked_monoforum_id) > r8.getFromChatId() ? 1 : ((-r20.currentChat.linked_monoforum_id) == r8.getFromChatId() ? 0 : -1));
        goto L121
    L75:
        if (r20.isInScheduleMode() == true) goto L111;
        if (r20.currentChat == null) goto L111;
        if (r8.isOutOwner() == false) goto L111;
        if (r8.isSent() == false) goto L111;
        if (r8.isEditing() == true) goto L111;
        if (r8.isSending() == true) goto L111;
        if (r8.isSendError() == true) goto L111;
        if (r8.isContentUnread() == true) goto L111;
        if (r8.isUnread() == true) goto L111;
        if ((ConnectionsManager.getInstance(r20.getCurrentAccount()).getCurrentTime() - r8.messageOwner.date) >= r20.getMessagesController().chatReadMarkExpirePeriod) goto L111;
        if (ChatObject.isMegagroup(r20.currentChat) == false) goto L97;
    L98:
        TLRPC.ChatFull r010 = r20.chatInfo;
        if (r010 == null) goto L111;
        if (r010.participants_count > r20.getMessagesController().chatReadMarkSizeThreshold) goto L111;
        if ((r8.messageOwner.action instanceof TLRPC.TL_messageActionChatJoinedByRequest) == true) goto L111;
        if (r20.getChatMode() == 3) goto L111;
        if (r8.canSetReaction() == false) goto L111;
        if (ChatObject.isMonoForum(r20.currentChat) == true) goto L111;
        r03 = true;
        goto L113
    L97:
        if (ChatObject.isChannel(r20.currentChat) == true) goto L111;
    L30:
        if (r20.isSecretChat() == false) goto L32;
    L44:
        r02 = false;
        goto L45
    L32:
        if (r20.getChatMode() == 5) goto L44;
        if (r20.isInScheduleMode() == true) goto L44;
        if (r8.isReactionsAvailable() == false) goto L44;
        if ((r4.available_reactions instanceof TLRPC.TL_chatReactionsNone) == false) goto L42;
        if (r4.paid_reactions_available == false) goto L44;
    L42:
        if (r0.isEmpty() == true) goto L44;
    L47:
        if (r8.isSecretMedia() == true) goto L44;
        if (r20.getChatMode() == 5) goto L44;
        if (r20.isSecretChat() == true) goto L44;
        if (r20.isInScheduleMode() == true) goto L44;
        if (r8.isReactionsAvailable() == false) goto L44;
        TLRPC.ChatFull r410 = r20.chatInfo;
        if (r410 != null) goto L59;
    L62:
        if (r410 != null) goto L66;
        if (ChatObject.isChannel(r20.currentChat) == true) goto L66;
    L70:
        if (r0.isEmpty() == true) goto L44;
    L66:
        if (r20.currentUser != null) goto L70;
        if (ChatObject.isMonoForum(r20.currentChat) == false) goto L44;
    L59:
        if ((r410.available_reactions instanceof TLRPC.TL_chatReactionsNone) == false) goto L70;
        if (r410.paid_reactions_available == true) goto L70;
    L5:
        if (r20.isInScheduleMode() == true) goto L23;
        if (r20.currentUser != null) goto L23;
        if (r8.hasReactions() == false) goto L23;
        if (ChatObject.isChannel(r20.currentChat) == false) goto L15;
        if (r20.currentChat.megagroup == false) goto L23;
    L15:
        if (ChatObject.isMonoForum(r20.currentChat) == true) goto L23;
        if (r0.isEmpty() == true) goto L23;
        if (r8.messageOwner.reactions.can_see_list == false) goto L23;
        if (r8.isSecretMedia() == true) goto L23;
        r3 = true;
        goto L25
    }

    @Override // android.app.Dialog
    public void show() {
        if (AndroidUtilities.isSafeToShow(getContext()) == true) goto L5;
        return;
    L5:
        super.show();
        prepareBlur(null);
        this.setTaskInvisible = true;
        this.open = true;
        animateOpenTo(true, null);
    }
}
