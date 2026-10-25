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
import android.util.Base64;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.Insets;
import androidx.core.view.OnApplyWindowInsetsListener;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.exteragram.messenger.pillstack.ui.pills.crypto.RatePill$$ExternalSyntheticLambda1;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatMessageSharedResources;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.SendMessagesHelper;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.BaseFragment;
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
import org.telegram.ui.Components.poll.PollUtils;
import org.telegram.ui.Components.poll.RecentVotersCell;

/* loaded from: classes6.dex */
public class PollItemMenu extends Dialog {
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
    private boolean pollVoted;
    private ReactionsContainerLayout reactionsView;
    public final Theme.ResourcesProvider resourcesProvider;
    private boolean setCellInvisible;
    private boolean setTaskInvisible;
    private MessagePreviewView.TabsView tabsView;
    private byte[] taskId;
    private View taskOptionsView;
    private float taskOptionsViewMaxWidth;
    private float tx;
    private float ty;
    private ViewPagerFixed viewPager;
    private FrameLayout windowView;

    /* renamed from: org.telegram.ui.PollItemMenu$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass1(PollItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup, android.view.View
        public void dispatchDraw(Canvas r8) {
            if (PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0) > 0.0f) goto L5;
        L7:
            Canvas r1 = r8;
        L9:
            if (PollItemMenu.m17543$$Nest$fgetsetCellInvisible(this.this$0) == false) goto L14;
            if (PollItemMenu.m17531$$Nest$fgetcell(this.this$0) == null) goto L14;
            PollItemMenu.m17531$$Nest$fgetcell(this.this$0).setVisibility(4);
            PollItemMenu.m17552$$Nest$fputsetCellInvisible(this.this$0, false);
        L14:
            if (PollItemMenu.m17544$$Nest$fgetsetTaskInvisible(this.this$0) == true) goto L16;
        L18:
            super.dispatchDraw(r1);
            return;
        L16:
            if (PollItemMenu.m17531$$Nest$fgetcell(this.this$0) == null) goto L18;
            PollItemMenu.m17531$$Nest$fgetcell(this.this$0).doNotDrawPollId = PollItemMenu.m17545$$Nest$fgettaskId(this.this$0);
            PollItemMenu.m17531$$Nest$fgetcell(this.this$0).invalidate();
            PollItemMenu.m17553$$Nest$fputsetTaskInvisible(this.this$0, false);
            goto L18
        L5:
            if (PollItemMenu.m17528$$Nest$fgetblurBitmapPaint(this.this$0) == null) goto L7;
            PollItemMenu.m17530$$Nest$fgetblurMatrix(this.this$0).reset();
            float r0 = getWidth() / PollItemMenu.m17527$$Nest$fgetblurBitmap(this.this$0).getWidth();
            PollItemMenu.m17530$$Nest$fgetblurMatrix(this.this$0).postScale(r0, r0);
            PollItemMenu.m17529$$Nest$fgetblurBitmapShader(this.this$0).setLocalMatrix(PollItemMenu.m17530$$Nest$fgetblurMatrix(this.this$0));
            PollItemMenu.m17528$$Nest$fgetblurBitmapPaint(this.this$0).setAlpha((int) (PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0) * 255.0f));
            r1 = r8;
            r1.drawRect(0.0f, 0.0f, getWidth(), getHeight(), PollItemMenu.m17528$$Nest$fgetblurBitmapPaint(this.this$0));
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
            PollItemMenu.m17555$$Nest$msetupTranslation(this.this$0);
        }

        @Override // android.view.View
        public void onSizeChanged(int r1, int r2, int r3, int r4) {
            super.onSizeChanged(r1, r2, r3, r4);
            PollItemMenu.m17554$$Nest$mcheckBitmapMatrix(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$10, reason: invalid class name */
    public class AnonymousClass10 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass10(PollItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$11, reason: invalid class name */
    public class AnonymousClass11 implements View.OnClickListener {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ ItemOptions val$messageOptions;

        public AnonymousClass11(PollItemMenu r1, ItemOptions r2) {
            this.this$0 = r1;
            this.val$messageOptions = r2;
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View r1) {
            Bulletin.hideVisible();
            this.val$messageOptions.closeSwipeback();
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$12, reason: invalid class name */
    public class AnonymousClass12 implements View.OnClickListener {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ MessageSeenView val$finalMessageSeenView;
        final /* synthetic */ LinearLayout val$linearLayout;
        final /* synthetic */ RecyclerListView val$listView2;
        final /* synthetic */ ItemOptions val$messageOptions;
        final /* synthetic */ ItemOptions val$swipeback;

        public AnonymousClass12(PollItemMenu r1, MessageSeenView r2, ChatActivity r3, RecyclerListView r4, LinearLayout r5, ItemOptions r6, ItemOptions r7) {
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

    /* renamed from: org.telegram.ui.PollItemMenu$13, reason: invalid class name */
    public class AnonymousClass13 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ ChatActivity val$chatActivity;
        final /* synthetic */ ReactionsContainerLayout val$finalReactionsLayout;
        final /* synthetic */ MessageObject val$message;

        public AnonymousClass13(PollItemMenu r1, ChatActivity r2, MessageObject r3, ReactionsContainerLayout r4) {
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

    /* renamed from: org.telegram.ui.PollItemMenu$14, reason: invalid class name */
    public class AnonymousClass14 extends AnimatorListenerAdapter {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ Runnable val$after;
        final /* synthetic */ boolean val$open;

        public AnonymousClass14(PollItemMenu r1, boolean r2, Runnable r3) {
            this.this$0 = r1;
            this.val$open = r2;
            this.val$after = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            PollItemMenu r22 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            PollItemMenu.m17550$$Nest$fputopenProgress(r22, r0);
            PollItemMenu.m17548$$Nest$fgetwindowView(this.this$0).invalidate();
            PollItemMenu.m17534$$Nest$fgetcontainerView(this.this$0).invalidate();
            PollItemMenu.m17556$$Nest$mupdateTranslation(this.this$0);
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

    /* renamed from: org.telegram.ui.PollItemMenu$15, reason: invalid class name */
    public class AnonymousClass15 extends AnimatorListenerAdapter {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ boolean val$open;

        public AnonymousClass15(PollItemMenu r1, boolean r2) {
            this.this$0 = r1;
            this.val$open = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            PollItemMenu r12 = this.this$0;
            if (this.val$open == false) goto L5;
            float r0 = 1.0f;
        L6:
            PollItemMenu.m17551$$Nest$fputopenProgress2(r12, r0);
            return;
        L5:
            r0 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$2, reason: invalid class name */
    public class AnonymousClass2 extends FrameLayout {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass2(PollItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.ViewGroup
        public boolean drawChild(Canvas r7, View r8, long r9) {
            if (r8 != PollItemMenu.m17538$$Nest$fgetmyCell(this.this$0)) goto L5;
        L9:
            r7.save();
            r7.clipRect(0.0f, AndroidUtilities.lerp(PollItemMenu.m17533$$Nest$fgetclipTop(this.this$0), 0.0f, PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0)), getWidth(), AndroidUtilities.lerp(PollItemMenu.m17532$$Nest$fgetclipBottom(this.this$0), getHeight(), PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0)));
            boolean r6 = super.drawChild(r7, r8, r9);
            r7.restore();
            return r6;
        L5:
            if (r8 == PollItemMenu.m17539$$Nest$fgetmyTaskCell(this.this$0)) goto L9;
            return super.drawChild(r7, r8, r9);
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$3, reason: invalid class name */
    public class AnonymousClass3 extends ViewPagerFixed {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass3(PollItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed
        public void onTabAnimationUpdate(boolean r1) {
            PollItemMenu.m17556$$Nest$mupdateTranslation(this.this$0);
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$4, reason: invalid class name */
    public class AnonymousClass4 extends ViewPagerFixed.Adapter {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ Context val$context;

        public static /* synthetic */ void $r8$lambda$bkMXBEkFenA3IxkAxn3VZ59ksnk(AnonymousClass4 r0, View r1) {
            r0.lambda$createView$0(r1);
        }

        public AnonymousClass4(PollItemMenu r1, Context r2) {
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
            r22.setOnClickListener(new PollItemMenu$4$$ExternalSyntheticLambda0(this));
            return r22;
        }

        @Override // org.telegram.ui.Components.ViewPagerFixed.Adapter
        public int getItemCount() {
            return 2;
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$5, reason: invalid class name */
    public class AnonymousClass5 extends FrameLayout {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass5(PollItemMenu r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.widget.FrameLayout, android.view.View
        public void onMeasure(int r6, int r7) {
            int r62 = View.MeasureSpec.getSize(r6);
            int r72 = View.MeasureSpec.getSize(r7);
            PollItemMenu.m17556$$Nest$mupdateTranslation(this.this$0);
            int r0 = 0;
        L4:
            if (r0 >= getChildCount()) goto L20;
            View r1 = getChildAt(r0);
            if (r1 != PollItemMenu.m17536$$Nest$fgetmessageOptionsView(this.this$0)) goto L11;
            if (PollItemMenu.m17537$$Nest$fgetmessageOptionsViewMaxWidth(this.this$0) <= 0.0f) goto L11;
            PollItemMenu.m17536$$Nest$fgetmessageOptionsView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(Math.min(r62, (int) PollItemMenu.m17537$$Nest$fgetmessageOptionsViewMaxWidth(this.this$0)), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        L19:
            r0 = r0 + 1;
        L11:
            if (r1 != PollItemMenu.m17546$$Nest$fgettaskOptionsView(this.this$0)) goto L16;
            if (PollItemMenu.m17547$$Nest$fgettaskOptionsViewMaxWidth(this.this$0) <= 0.0f) goto L16;
            PollItemMenu.m17546$$Nest$fgettaskOptionsView(this.this$0).measure(View.MeasureSpec.makeMeasureSpec(Math.min(r62, (int) PollItemMenu.m17547$$Nest$fgettaskOptionsViewMaxWidth(this.this$0)), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
        L16:
            if (r1 != PollItemMenu.m17542$$Nest$fgetreactionsView(this.this$0)) goto L18;
            r1.measure(View.MeasureSpec.makeMeasureSpec(PollItemMenu.m17542$$Nest$fgetreactionsView(this.this$0).getTotalWidth(), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
            goto L19
        L18:
            r1.measure(View.MeasureSpec.makeMeasureSpec(r62, Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(r72, Integer.MIN_VALUE));
            goto L19
        L20:
            setMeasuredDimension(r62, r72);
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$6, reason: invalid class name */
    public class AnonymousClass6 implements OnApplyWindowInsetsListener {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass6(PollItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // androidx.core.view.OnApplyWindowInsetsListener
        public WindowInsetsCompat onApplyWindowInsets(View r4, WindowInsetsCompat r5) {
            PollItemMenu.m17549$$Nest$fputinsets(this.this$0, r5.getInsetsIgnoringVisibility(WindowInsetsCompat.Type.systemBars()));
            PollItemMenu.m17534$$Nest$fgetcontainerView(this.this$0).setPadding(PollItemMenu.m17535$$Nest$fgetinsets(this.this$0).left, PollItemMenu.m17535$$Nest$fgetinsets(this.this$0).top, PollItemMenu.m17535$$Nest$fgetinsets(this.this$0).right, PollItemMenu.m17535$$Nest$fgetinsets(this.this$0).bottom);
            PollItemMenu.m17548$$Nest$fgetwindowView(this.this$0).requestLayout();
            return WindowInsetsCompat.CONSUMED;
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$7, reason: invalid class name */
    public class AnonymousClass7 extends ChatMessageCell {
        private final Path clipPath;
        private final Paint shadowPaint;
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ int val$finalHeight;
        final /* synthetic */ int val$finalWidth;
        final /* synthetic */ byte[] val$taskId;

        public AnonymousClass7(PollItemMenu r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, byte[] r7, int r8, int r9) {
            this.this$0 = r1;
            this.val$taskId = r7;
            this.val$finalWidth = r8;
            this.val$finalHeight = r9;
            super(r2, r3, r4, r5, r6);
            this.clipPath = new Path();
            this.shadowPaint = new Paint(1);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell
        public void drawOverlays(Canvas r3) {
            this.firstVisiblePollButton = 0;
            this.lastVisiblePollButton = this.pollButtons.size() - 1;
            this.resultsPollButtonOffset = (-AndroidUtilities.dp(7.0f)) * PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0);
            super.drawOverlays(r3);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onDraw(Canvas r9) {
            r9.save();
            int r0 = getPollIndex(this.val$taskId);
            float r1 = getPollButtonTop(r0);
            float r02 = getPollButtonBottom(r0);
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(getPollButtonsLeft(), r1, getPollButtonsRight(), r02);
            float r03 = r2.top;
            float r3 = AndroidUtilities.dp(3.0f);
            if (PollItemMenu.m17541$$Nest$fgetpollVoted(this.this$0) == false) goto L5;
            float r4 = -AndroidUtilities.dp(3.0f);
        L6:
            r2.top = r03 + AndroidUtilities.lerp(r3, r4, PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0));
            float r04 = r2.bottom;
            boolean r32 = PollItemMenu.m17541$$Nest$fgetpollVoted(this.this$0);
            float r12 = AndroidUtilities.dp(3.0f);
            if (r32 == true) goto L10;
            r12 = AndroidUtilities.lerp(r12, 0.0f, PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0));
        L10:
            r2.bottom = r04 + r12;
            this.clipPath.rewind();
            this.clipPath.addRoundRect(r2, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), Path.Direction.CW);
            this.shadowPaint.setColor(0);
            this.shadowPaint.setShadowLayer(AndroidUtilities.dp(2.0f), 0.0f, AndroidUtilities.dp(0.66f), Theme.multAlpha(-16777216, PollItemMenu.m17540$$Nest$fgetopenProgress(this.this$0) * 0.2f));
            r9.drawRoundRect(r2, AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), this.shadowPaint);
            r9.clipPath(this.clipPath);
            super.onDraw(r9);
            r9.restore();
            return;
        L5:
            r4 = 0.0f;
            goto L6
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void onMeasure(int r1, int r2) {
            setMeasuredDimension(this.val$finalWidth, this.val$finalHeight);
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell, android.view.View
        public void setPressed(boolean r1) {
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$8, reason: invalid class name */
    public class AnonymousClass8 implements ChatMessageCell.ChatMessageCellDelegate {
        final /* synthetic */ PollItemMenu this$0;

        public AnonymousClass8(PollItemMenu r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.Cells.ChatMessageCell.ChatMessageCellDelegate
        public boolean canPerformActions() {
            return false;
        }
    }

    /* renamed from: org.telegram.ui.PollItemMenu$9, reason: invalid class name */
    public class AnonymousClass9 extends ChatMessageCell {
        final /* synthetic */ PollItemMenu this$0;
        final /* synthetic */ int val$finalHeight;
        final /* synthetic */ int val$finalWidth;

        public AnonymousClass9(PollItemMenu r1, Context r2, int r3, boolean r4, ChatMessageSharedResources r5, Theme.ResourcesProvider r6, int r7, int r8) {
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

    /* renamed from: $r8$lambda$-vsZXodutEezmRzwHiDqNZ5F1x0, reason: not valid java name */
    public static /* synthetic */ void m17520$r8$lambda$vsZXodutEezmRzwHiDqNZ5F1x0(PollItemMenu r0) {
        r0.updateTranslation();
    }

    public static /* synthetic */ void $r8$lambda$4SMKQcarBAla8X_YJ3SJxSWQ0vA(PollItemMenu r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$19(r1);
    }

    public static /* synthetic */ void $r8$lambda$6gy1fsDPjwABLEGxod94cZrIOKc(PollItemMenu r0) {
        r0.lambda$setupMessageOptions$12();
    }

    public static /* synthetic */ void $r8$lambda$9TgrvZ_ffugi2837eBOWncIagrU(PollItemMenu r0, BaseFragment r1, Long r2) {
        r0.lambda$setCell$1(r1, r2);
    }

    /* renamed from: $r8$lambda$DrDvsw2Xb_YmIsvGvIV-F_Qci5E, reason: not valid java name */
    public static /* synthetic */ void m17521$r8$lambda$DrDvsw2Xb_YmIsvGvIVF_Qci5E(PollItemMenu r0, boolean r1, TLRPC.PollAnswer r2, BaseFragment r3, ArrayList r4) {
        r0.lambda$setCell$4(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$FVUMUtGQlJRJjZSFxjv6ypbHJZY(PollItemMenu r0, View r1, Bitmap r2, Bitmap r3) {
        r0.lambda$prepareBlur$15(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$I0DcGhhpkmreVttoo1aBp2oe64E(PollItemMenu r0, long r1, BaseFragment r3) {
        r0.lambda$setCell$9(r1, r3);
    }

    public static /* synthetic */ void $r8$lambda$OBEjcKADa29nqJ4j8go0PswNoTM(PollItemMenu r0) {
        r0.lambda$dismiss$16();
    }

    /* renamed from: $r8$lambda$PO0bPufgv17crIOY5s2tIL7-w2A, reason: not valid java name */
    public static /* synthetic */ void m17522$r8$lambda$PO0bPufgv17crIOY5s2tIL7w2A(PollItemMenu r0, ValueAnimator r1) {
        r0.lambda$animateOpenTo$18(r1);
    }

    /* renamed from: $r8$lambda$UokRB4bQwUQWlfCymwm-YKXviKU, reason: not valid java name */
    public static /* synthetic */ void m17523$r8$lambda$UokRB4bQwUQWlfCymwmYKXviKU(ItemOptions r0, ItemOptions r1, View r2) {
        r0.openSwipeback(r1);
    }

    /* renamed from: $r8$lambda$V-AImXbLE0fcN7crqbiS84QoK4I, reason: not valid java name */
    public static /* synthetic */ void m17524$r8$lambda$VAImXbLE0fcN7crqbiS84QoK4I(PollItemMenu r0, String r1) {
        r0.lambda$setCell$6(r1);
    }

    public static /* synthetic */ void $r8$lambda$gCokgN7G4KUOCadcyRAuDWYaDiU(PollItemMenu r0, boolean r1, BaseFragment r2, ArrayList r3, TLRPC.PollAnswer r4) {
        r0.lambda$setCell$3(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$gvbDxqWmpCTVahMX9G19kTMJDJg(PollItemMenu r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$jkhEB_QVWAmM5HnNTmVI4qSHvjE(PollItemMenu r0) {
        r0.lambda$setupMessageOptions$11();
    }

    /* renamed from: $r8$lambda$lJ-NTzY4hx_3BN7ii1sOn72jXJw, reason: not valid java name */
    public static /* synthetic */ void m17525$r8$lambda$lJNTzY4hx_3BN7ii1sOn72jXJw(PollItemMenu r0, Utilities.Callback r1, int r2) {
        r0.lambda$setupMessageOptions$13(r1, r2);
    }

    public static /* synthetic */ boolean $r8$lambda$lYkpuuUR2rZOqN5Ri6lEStfuL_g(PollItemMenu r0, View r1, MotionEvent r2) {
        return r0.lambda$setupMessageOptions$14(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$rD76dxdRGh4Q8euqvtlD5uqZ64w(PollItemMenu r0, boolean r1) {
        r0.lambda$dismiss$17(r1);
    }

    public static /* synthetic */ void $r8$lambda$ue2QUcOGrB29anmfFdY_Pj_icvU(PollItemMenu r0, ChatActivity r1, TLRPC.PollAnswer r2) {
        r0.lambda$setCell$5(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$wIsGRwqELHRiF60ut3z0TjNBwdE(PollItemMenu r0) {
        r0.lambda$setupMessageOptions$10();
    }

    public static /* synthetic */ void $r8$lambda$zFN7ru0xonJEB6IJu2XfJ_Zjh2g(PollItemMenu r0, TLRPC.PollAnswer r1) {
        r0.lambda$setCell$7(r1);
    }

    /* renamed from: $r8$lambda$zOXiIuQhwSfLOcwdM5fc-RExaJE, reason: not valid java name */
    public static /* synthetic */ void m17526$r8$lambda$zOXiIuQhwSfLOcwdM5fcRExaJE(PollItemMenu r0, byte[] r1) {
        r0.lambda$setCell$8(r1);
    }

    /* renamed from: -$$Nest$fgetblurBitmap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Bitmap m17527$$Nest$fgetblurBitmap(PollItemMenu r0) {
        return r0.blurBitmap;
    }

    /* renamed from: -$$Nest$fgetblurBitmapPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m17528$$Nest$fgetblurBitmapPaint(PollItemMenu r0) {
        return r0.blurBitmapPaint;
    }

    /* renamed from: -$$Nest$fgetblurBitmapShader, reason: not valid java name */
    public static /* bridge */ /* synthetic */ BitmapShader m17529$$Nest$fgetblurBitmapShader(PollItemMenu r0) {
        return r0.blurBitmapShader;
    }

    /* renamed from: -$$Nest$fgetblurMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Matrix m17530$$Nest$fgetblurMatrix(PollItemMenu r0) {
        return r0.blurMatrix;
    }

    /* renamed from: -$$Nest$fgetcell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m17531$$Nest$fgetcell(PollItemMenu r0) {
        return r0.cell;
    }

    /* renamed from: -$$Nest$fgetclipBottom, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17532$$Nest$fgetclipBottom(PollItemMenu r0) {
        return r0.clipBottom;
    }

    /* renamed from: -$$Nest$fgetclipTop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17533$$Nest$fgetclipTop(PollItemMenu r0) {
        return r0.clipTop;
    }

    /* renamed from: -$$Nest$fgetcontainerView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17534$$Nest$fgetcontainerView(PollItemMenu r0) {
        return r0.containerView;
    }

    /* renamed from: -$$Nest$fgetinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Insets m17535$$Nest$fgetinsets(PollItemMenu r0) {
        return r0.insets;
    }

    /* renamed from: -$$Nest$fgetmessageOptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m17536$$Nest$fgetmessageOptionsView(PollItemMenu r0) {
        return r0.messageOptionsView;
    }

    /* renamed from: -$$Nest$fgetmessageOptionsViewMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17537$$Nest$fgetmessageOptionsViewMaxWidth(PollItemMenu r0) {
        return r0.messageOptionsViewMaxWidth;
    }

    /* renamed from: -$$Nest$fgetmyCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m17538$$Nest$fgetmyCell(PollItemMenu r0) {
        return r0.myCell;
    }

    /* renamed from: -$$Nest$fgetmyTaskCell, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatMessageCell m17539$$Nest$fgetmyTaskCell(PollItemMenu r0) {
        return r0.myTaskCell;
    }

    /* renamed from: -$$Nest$fgetopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17540$$Nest$fgetopenProgress(PollItemMenu r0) {
        return r0.openProgress;
    }

    /* renamed from: -$$Nest$fgetpollVoted, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17541$$Nest$fgetpollVoted(PollItemMenu r0) {
        return r0.pollVoted;
    }

    /* renamed from: -$$Nest$fgetreactionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m17542$$Nest$fgetreactionsView(PollItemMenu r0) {
        return r0.reactionsView;
    }

    /* renamed from: -$$Nest$fgetsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17543$$Nest$fgetsetCellInvisible(PollItemMenu r0) {
        return r0.setCellInvisible;
    }

    /* renamed from: -$$Nest$fgetsetTaskInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m17544$$Nest$fgetsetTaskInvisible(PollItemMenu r0) {
        return r0.setTaskInvisible;
    }

    /* renamed from: -$$Nest$fgettaskId, reason: not valid java name */
    public static /* bridge */ /* synthetic */ byte[] m17545$$Nest$fgettaskId(PollItemMenu r0) {
        return r0.taskId;
    }

    /* renamed from: -$$Nest$fgettaskOptionsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ View m17546$$Nest$fgettaskOptionsView(PollItemMenu r0) {
        return r0.taskOptionsView;
    }

    /* renamed from: -$$Nest$fgettaskOptionsViewMaxWidth, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m17547$$Nest$fgettaskOptionsViewMaxWidth(PollItemMenu r0) {
        return r0.taskOptionsViewMaxWidth;
    }

    /* renamed from: -$$Nest$fgetwindowView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ FrameLayout m17548$$Nest$fgetwindowView(PollItemMenu r0) {
        return r0.windowView;
    }

    /* renamed from: -$$Nest$fputinsets, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17549$$Nest$fputinsets(PollItemMenu r0, Insets r1) {
        r0.insets = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17550$$Nest$fputopenProgress(PollItemMenu r0, float r1) {
        r0.openProgress = r1;
    }

    /* renamed from: -$$Nest$fputopenProgress2, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17551$$Nest$fputopenProgress2(PollItemMenu r0, float r1) {
        r0.openProgress2 = r1;
    }

    /* renamed from: -$$Nest$fputsetCellInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17552$$Nest$fputsetCellInvisible(PollItemMenu r0, boolean r1) {
        r0.setCellInvisible = r1;
    }

    /* renamed from: -$$Nest$fputsetTaskInvisible, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17553$$Nest$fputsetTaskInvisible(PollItemMenu r0, boolean r1) {
        r0.setTaskInvisible = r1;
    }

    /* renamed from: -$$Nest$mcheckBitmapMatrix, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17554$$Nest$mcheckBitmapMatrix(PollItemMenu r0) {
        r0.checkBitmapMatrix();
    }

    /* renamed from: -$$Nest$msetupTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17555$$Nest$msetupTranslation(PollItemMenu r0) {
        r0.setupTranslation();
    }

    /* renamed from: -$$Nest$mupdateTranslation, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m17556$$Nest$mupdateTranslation(PollItemMenu r0) {
        r0.updateTranslation();
    }

    public PollItemMenu(Context r8, Theme.ResourcesProvider r9) {
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
        r1.setOnClickListener(new PollItemMenu$$ExternalSyntheticLambda13(this));
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
        r16.addTab(0, LocaleController.getString(R.string.PollMenuTabOption));
        this.tabsView.addTab(1, LocaleController.getString(R.string.PollMenuTabPoll));
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
        this.hintTextView.setText(LocaleController.getString(R.string.PollMenuHint));
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
        r32.addUpdateListener(new PollItemMenu$$ExternalSyntheticLambda11(this));
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
        r114.addUpdateListener(new PollItemMenu$$ExternalSyntheticLambda12(this));
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

    private /* synthetic */ void lambda$animateOpenTo$18(ValueAnimator r1) {
        this.openProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.windowView.invalidate();
        this.containerView.invalidate();
        ChatMessageCell r12 = this.myTaskCell;
        if (r12 == null) goto L5;
        r12.invalidate();
    L5:
        updateTranslation();
    }

    private /* synthetic */ void lambda$animateOpenTo$19(ValueAnimator r1) {
        this.openProgress2 = ((Float) r1.getAnimatedValue()).floatValue();
    }

    private /* synthetic */ void lambda$dismiss$16() {
        super.dismiss();
    }

    private /* synthetic */ void lambda$dismiss$17(boolean r3) {
        AndroidUtilities.runOnUIThread(new PollItemMenu$$ExternalSyntheticLambda21(this));
        ChatMessageCell r32 = this.cell;
        if (r32 == null) goto L5;
        r32.setVisibility(0);
        ChatMessageCell r33 = this.cell;
        r33.doNotDrawPollId = null;
        r33.invalidate();
    L5:
        Runnable r34 = this.dismissListener;
        if (r34 == null) goto L9;
        AndroidUtilities.runOnUIThread(r34);
        this.dismissListener = null;
        return;
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        dismiss();
    }

    private /* synthetic */ void lambda$prepareBlur$15(View r3, Bitmap r4, Bitmap r5) {
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

    private /* synthetic */ void lambda$setCell$1(BaseFragment r6, Long r7) {
        Bundle r0 = new Bundle();
        if (r7.longValue() < 0) goto L5;
        r0.putLong("user_id", r7.longValue());
    L6:
        r6.presentFragment(new ProfileActivity(r0));
        dismiss(false);
        return;
    L5:
        r0.putLong("chat_id", -r7.longValue());
        goto L6
    }

    private /* synthetic */ void lambda$setCell$3(boolean r2, BaseFragment r3, ArrayList r4, TLRPC.PollAnswer r5) {
        if (r2 == true) goto L5;
        r3.getSendMessagesHelper().sendVote(this.messageObject, null, null);
    L6:
        dismiss(true);
        return;
    L5:
        r4.remove(r5);
        r3.getSendMessagesHelper().sendVote(this.messageObject, r4, null);
        goto L6
    }

    private /* synthetic */ void lambda$setCell$4(boolean r3, TLRPC.PollAnswer r4, BaseFragment r5, ArrayList r6) {
        if (r3 == true) goto L5;
        ArrayList<TLRPC.PollAnswer> r32 = new ArrayList(1);
        r32.add(r4);
        r5.getSendMessagesHelper().sendVote(this.messageObject, r32, null);
    L6:
        dismiss(true);
        return;
    L5:
        r6.add(r4);
        r5.getSendMessagesHelper().sendVote(this.messageObject, r6, null);
        goto L6
    }

    private /* synthetic */ void lambda$setCell$5(ChatActivity r2, TLRPC.PollAnswer r3) {
        MessageObject r0 = this.messageObject;
        r2.showFieldPanelForReplyQuote(r0, ChatActivity.ReplyQuote.fromPollOption(r0, r3.option));
        dismiss(false);
    }

    private /* synthetic */ void lambda$setCell$6(String r1) {
        AndroidUtilities.addToClipboard(r1);
        dismiss(true);
    }

    private /* synthetic */ void lambda$setCell$7(TLRPC.PollAnswer r2) {
        AndroidUtilities.addToClipboard(MessageObject.formatTextWithEntities(r2.text, false));
        dismiss(true);
    }

    private /* synthetic */ void lambda$setCell$8(byte[] r3) {
        SendMessagesHelper.getInstance(this.messageObject.currentAccount).deletePollOption(this.messageObject, r3);
        dismiss(true);
    }

    private /* synthetic */ void lambda$setCell$9(long r4, BaseFragment r6) {
        Bundle r0 = new Bundle();
        if (r4 <= 0) goto L5;
        r0.putLong("user_id", r4);
    L6:
        r6.presentFragment(new ProfileActivity(r0));
        dismiss(false);
        return;
    L5:
        r0.putLong("chat_id", -r4);
        goto L6
    }

    private /* synthetic */ void lambda$setupMessageOptions$10() {
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$11() {
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$12() {
        dismiss(false);
    }

    private /* synthetic */ void lambda$setupMessageOptions$13(Utilities.Callback r2, int r3) {
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

    private /* synthetic */ boolean lambda$setupMessageOptions$14(View r3, MotionEvent r4) {
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

    private void prepareBlur(View r2) {
        if (r2 == null) goto L4;
        r2.setVisibility(4);
    L4:
        ScrimOptions.makeGlobalBlurBitmaps(new PollItemMenu$$ExternalSyntheticLambda1(this, r2));
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
        int r06 = this.myTaskCell.getPollIndex(this.taskId);
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
        int r410 = this.myTaskCell.getPollIndex(this.taskId);
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
        animateOpenTo(false, new PollItemMenu$$ExternalSyntheticLambda0(this, r2));
        this.windowView.invalidate();
        return;
    L23:
        if (r6 == true) goto L27;
        ChatMessageCell r32 = this.cell;
        if (r32 == null) goto L27;
        r32.setVisibility(0);
        ChatMessageCell r33 = this.cell;
        r33.doNotDrawPollId = null;
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

    public void setCell(BaseFragment r28, ChatMessageCell r29, byte[] r30) {
        this.cell = r29;
        this.taskId = r30;
        if ((r28 instanceof ChatActivity) == false) goto L5;
        ChatActivity r12 = (ChatActivity) r28;
    L6:
        if (r29 == null) goto L8;
        MessageObject r2 = r29.getMessageObject();
    L9:
        this.messageObject = r2;
        if (r2 != null) goto L12;
    L14:
        boolean r22 = false;
    L15:
        this.isOut = r22;
        if (this.cell == null) goto L24;
        if (r12 != null) goto L19;
        float r23 = 0.0f;
    L20:
        this.clipTop = r23;
        this.clipBottom = r29.parentBoundsBottom;
        if ((r29.getParent() instanceof View) == false) goto L23;
        View r0 = (View) r29.getParent();
        this.clipTop += r0.getY();
        this.clipBottom += r0.getY();
    L23:
        int r7 = this.cell.getWidth();
        int r8 = this.cell.getHeight();
        this.heightdiff = r8 - this.cell.getHeight();
        AnonymousClass7 r02 = new AnonymousClass7(this, getContext(), UserConfig.selectedAccount, false, null, this.cell.getResourcesProvider(), r30, r7, r8);
        byte[] r9 = r30;
        this.myTaskCell = r02;
        this.cell.copyParamsTo(r02);
        this.myTaskCell.copySpoilerEffect2AttachIndexFrom(this.cell);
        this.myTaskCell.setDelegate(new AnonymousClass8(this));
        ChatMessageCell r03 = this.myTaskCell;
        MessageObject r24 = this.messageObject;
        MessageObject.GroupedMessages r19 = this.cell.getCurrentMessagesGroup();
        ChatMessageCell r3 = this.cell;
        r03.setMessageObject(r24, r19, r3.pinnedBottom, r3.pinnedTop, r3.firstInChat);
        ChatMessageCell r04 = this.myTaskCell;
        r04.drawOnlyPollId = r9;
        this.containerView.addView(r04, new FrameLayout.LayoutParams(this.cell.getWidth(), r8, 51));
        AnonymousClass9 r05 = new AnonymousClass9(this, getContext(), UserConfig.selectedAccount, false, null, this.cell.getResourcesProvider(), r7, r8);
        PollItemMenu r82 = this;
        r82.myCell = r05;
        r82.cell.copyVisiblePartTo(r05);
        r82.cell.copyParamsTo(r82.myCell);
        r82.myCell.copySpoilerEffect2AttachIndexFrom(r82.cell);
        r82.myCell.setDelegate(new AnonymousClass10(r82));
        ChatMessageCell r1 = r82.myCell;
        MessageObject r25 = r82.messageObject;
        MessageObject.GroupedMessages r192 = r82.cell.getCurrentMessagesGroup();
        ChatMessageCell r32 = r82.cell;
        r1.setMessageObject(r25, r192, r32.pinnedBottom, r32.pinnedTop, r32.firstInChat);
        r82.containerView.addView(r82.myCell, new FrameLayout.LayoutParams(r82.cell.getWidth(), r8, 51));
    L25:
        r82.viewPager.bringToFront();
        r82.menuContainer.bringToFront();
        r82.tabsView.bringToFront();
        r82.viewPager.onTabAnimationUpdate(false);
        ItemOptions r06 = ItemOptions.makeOptions(r82.containerView, r82.resourcesProvider, new View(r82.context), true);
        TLRPC.TL_messageMediaPoll r13 = (TLRPC.TL_messageMediaPoll) MessageObject.getMedia(r82.messageObject);
        r82.pollVoted = MessageObject.isVoted(r13);
        int r26 = 0;
    L27:
        if (r26 >= r13.poll.answers.size()) goto L32;
        if (Arrays.equals(r13.poll.answers.get(r26).option, r9) == true) goto L30;
        r26 = r26 + 1;
        goto L27
    L30:
        TLRPC.PollAnswer r17 = r13.poll.answers.get(r26);
    L33:
        if (r17 == null) goto L105;
        TLRPC.Poll r33 = r13.poll;
        if (r33.closed == false) goto L37;
    L39:
        boolean r18 = false;
    L40:
        boolean r34 = r33.multiple_choice;
        ArrayList r4 = new ArrayList();
        TLRPC.PollResults r5 = r13.results;
        if (r5 == null) goto L61;
        ArrayList<TLRPC.PollAnswerVoters> r52 = r5.results;
        if (r52 == null) goto L61;
        int r6 = r52.size();
        boolean r72 = false;
        int r15 = 0;
        TLRPC.PollAnswerVoters r16 = null;
    L45:
        if (r15 >= r6) goto L60;
        TLRPC.PollAnswerVoters r20 = r52.get(r15);
        r15 = r15 + 1;
        TLRPC.PollAnswerVoters r11 = r20;
        boolean r132 = Arrays.equals(r11.option, r9);
        if (r132 == false) goto L50;
        r16 = r11;
    L50:
        if (r11.chosen == false) goto L59;
        if (r132 == false) goto L53;
        r72 = true;
    L53:
        ArrayList<TLRPC.PollAnswer> r133 = r13.poll.answers;
        int r14 = r133.size();
        int r27 = 0;
    L54:
        if (r27 >= r14) goto L59;
        TLRPC.PollAnswer r232 = r133.get(r27);
        int r210 = r27 + 1;
        TLRPC.TL_messageMediaPoll r242 = r13;
        TLRPC.PollAnswer r110 = r232;
        boolean r252 = r34;
        if (Arrays.equals(r110.option, r11.option) == false) goto L58;
        r4.add(r110);
    L58:
        r27 = r210;
        r13 = r242;
        r34 = r252;
    L59:
        r13 = r13;
        r34 = r34;
        goto L45
    L60:
        TLRPC.TL_messageMediaPoll r243 = r13;
        boolean r253 = r34;
        boolean r112 = r72;
        TLRPC.PollAnswerVoters r134 = r16;
    L62:
        if (r134 != null) goto L64;
    L68:
        ArrayList r152 = r4;
        byte[] r73 = r9;
        TLRPC.TL_messageMediaPoll r10 = r243;
        ItemOptions r92 = r06;
    L69:
        if (r18 == false) goto L77;
        if (r112 == false) goto L73;
        PollItemMenu r111 = this;
        TLRPC.PollAnswer r53 = r17;
        r92.add(R.drawable.msg_unvote, LocaleController.getString(R.string.Unvote), new PollItemMenu$$ExternalSyntheticLambda4(r111, r253, r28, r152, r53));
    L72:
        BaseFragment r35 = r28;
    L78:
        if (r12 == null) goto L83;
        if (r12.canSendMessage() == false) goto L83;
        r92.add(R.drawable.menu_reply, LocaleController.getString(R.string.PollItemQuote), new PollItemMenu$$ExternalSyntheticLambda6(r111, r12, r53));
    L83:
        if (r111.messageObject.getDialogId() < 0) goto L85;
    L90:
        r92.add(R.drawable.msg_copy, LocaleController.getString(R.string.Copy), new PollItemMenu$$ExternalSyntheticLambda8(r111, r53));
        TLRPC.Peer r07 = r53.added_by;
        if (r07 == null) goto L104;
        long r113 = DialogObject.getPeerDialogId(r07);
        long r135 = UserConfig.getInstance(r111.messageObject.currentAccount).getClientUserId();
        ItemOptions r211 = r92;
        long r83 = ConnectionsManager.getInstance(r111.messageObject.currentAccount).getCurrentTime();
        long r84 = r53.date + MessagesController.getInstance(r111.messageObject.currentAccount).config.pollAnswerDeletePeriod.get(TimeUnit.SECONDS);
        if (r111.messageObject.isForwarded() == true) goto L103;
        TLRPC.Poll r08 = r10.poll;
        if (r08.closed == true) goto L103;
        if (r08.creator == false) goto L99;
    L102:
        r211.add(R.drawable.msg_delete, LocaleController.getString(R.string.Delete), true, new PollItemMenu$$ExternalSyntheticLambda9(r111, r73));
        goto L103
    L99:
        if (r113 != r135) goto L103;
        if (r83 < r84) goto L102;
    L103:
        r211.addGap();
        TLObject r09 = MessagesController.getInstance(r111.messageObject.currentAccount).getUserOrChat(r113);
        r211.addProfileCustom(r09, AndroidUtilities.replaceTags(LocaleController.formatSpannable(R.string.PollAddedByAtTime, new Object[]{DialogObject.getShortName(r09), LocaleController.formatDateTime(r53.date, true)})), new PollItemMenu$$ExternalSyntheticLambda10(r111, r113, r35));
    L106:
        r211.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r111.resourcesProvider), 0.06f));
        r211.setBlurBackground(r111.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(r111.resourcesProvider), false);
        r211.setupSelectors();
        ViewGroup r010 = r211.getLayout();
        r111.taskOptionsView = r010;
        r010.setPivotX(0.0f);
        r111.taskOptionsView.setPivotY(0.0f);
        r111.menuContainer.addView(r111.taskOptionsView, LayoutHelper.createFrame(-2, -2, 51));
        return;
    L104:
        r211 = r92;
        goto L106
    L85:
        if (r53.option == null) goto L90;
        MessagesController r011 = MessagesController.getInstance(r111.messageObject.currentAccount);
        String r212 = DialogObject.getPublicUsername(r011.getUserOrChat(r111.messageObject.getDialogId()));
        StringBuilder r42 = new StringBuilder("https://");
        r42.append(r011.linkPrefix);
        r42.append("/");
        if (TextUtils.isEmpty(r212) == false) goto L89;
        r212 = "c/" + (-r111.messageObject.getDialogId());
    L89:
        r42.append(r212);
        r42.append("/");
        r42.append(r111.messageObject.getId());
        r42.append("?option=");
        r42.append(new String(Base64.encode(r53.option, 9)));
        r92.add(R.drawable.msg_link, LocaleController.getString(R.string.CopyLink), new PollItemMenu$$ExternalSyntheticLambda7(r111, r42.toString()));
        goto L90
    L73:
        r111 = this;
        ArrayList r54 = r152;
        TLRPC.PollAnswer r36 = r17;
        boolean r213 = r253;
        if (PollUtils.getVoteRestrictedFlags(r111.messageObject) != 0) goto L76;
        int r62 = R.drawable.msg_select;
        String r114 = LocaleController.getString(R.string.PollSubmitVotesNoCaps);
        PollItemMenu$$ExternalSyntheticLambda5 r012 = new PollItemMenu$$ExternalSyntheticLambda5(r111, r213, r36, r28, r54);
        r53 = r36;
        r35 = r28;
        r92.add(r62, r114, r012);
        goto L78
    L76:
        r53 = r36;
        goto L72
    L77:
        r111 = this;
        r35 = r28;
        r53 = r17;
        goto L78
    L64:
        if (r134.voters <= 0) goto L68;
        if (MessageObject.canShowVotersList(r243) == false) goto L68;
        RecentVotersCell r115 = new RecentVotersCell(r82.context, r28.getCurrentAccount(), r82.resourcesProvider);
        ItemOptions r142 = r06.makeSwipeback();
        r142.setGapBackgroundColor(Theme.multAlpha(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem, r82.resourcesProvider), 0.06f));
        r142.setBlurBackgroundForSwipeback(r82.iBlur3Factory, BlurredBackgroundProviderImpl.scrimMenuBackground(r82.resourcesProvider), false);
        r142.add(R.drawable.ic_ab_back, LocaleController.getString(R.string.Back), new RatePill$$ExternalSyntheticLambda1(r06));
        r142.addGap();
        r152 = r4;
        byte[] r55 = r9;
        r92 = r06;
        r10 = r243;
        r73 = r55;
        r142.addView(r115.createListView(r28, r82.messageObject.getDialogId(), r82.messageObject.getId(), r55, r134.voters, new PollItemMenu$$ExternalSyntheticLambda2(r82, r28)));
        r115.setMinimumHeight(AndroidUtilities.dp(48.0f));
        r115.setText(LocaleController.formatPluralString("PollVotesCount", r134.voters, new Object[0]));
        r115.setRecentVoters(r134.recent_voters, false);
        r115.setLayoutParams(LayoutHelper.createLinear(-1, 48));
        r115.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_dialogButtonSelector), 12, 0));
        r115.setOnClickListener(new PollItemMenu$$ExternalSyntheticLambda3(r92, r142));
        r92.addView(r115);
        r92.addGap();
    L61:
        r243 = r13;
        r253 = r34;
        r112 = false;
        r134 = null;
        goto L62
    L37:
        if (r33.revoting_disabled == true) goto L39;
        r18 = true;
        goto L40
    L105:
        r211 = r06;
        r111 = r82;
        goto L106
    L32:
        r17 = null;
        goto L33
    L19:
        r23 = r12.getChatListViewPadding() - AndroidUtilities.dp(4.0f);
        goto L20
    L24:
        r82 = this;
        r9 = r30;
        goto L25
    L12:
        if (r2.isOutOwner() == false) goto L14;
        r22 = true;
        goto L15
    L8:
        r2 = null;
        goto L9
    L5:
        r12 = null;
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
        PollItemMenu r63 = this;
        r04.setOnClickListener(r05);
        r122.addView(r10, LayoutHelper.createLinear(-1, -2));
        r7.addView(r122);
        r72.addView(r33);
        r72.addGap();
    L213:
        MessageObject r34 = r8;
    L217:
        if (r13 == false) goto L219;
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 1, r34, new PollItemMenu$$ExternalSyntheticLambda16(r63), r63.resourcesProvider), LayoutHelper.createLinear(r152, 36));
        r72.addGap();
    L219:
        if (r14 == false) goto L221;
        MessageObject r82 = r34;
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 2, r34, new PollItemMenu$$ExternalSyntheticLambda17(r63), r63.resourcesProvider), LayoutHelper.createLinear(r152, 36));
        r72.addGap();
    L222:
        int r06 = r21.size();
        int r1 = 0;
    L223:
        if (r1 >= r06) goto L225;
        r72.add(r21.get(r1).intValue(), r22.get(r1), new PollItemMenu$$ExternalSyntheticLambda18(r63, r24, r23.get(r1).intValue()));
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
        ((ActionBarPopupWindow.ActionBarPopupWindowLayout) r08).setOnSizeChangedListener(new PollItemMenu$$ExternalSyntheticLambda19(r63));
        r63.messageOptionsView.setOnTouchListener(new PollItemMenu$$ExternalSyntheticLambda20(r63));
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
        r72.addView(new MessagePrivateSeenView(r63.getContext(), 0, r34, new PollItemMenu$$ExternalSyntheticLambda15(r63), r63.resourcesProvider), LayoutHelper.createLinear(-1, 36));
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
