package org.telegram.ui.recyclerview;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.util.LongSparseArray;
import android.util.Property;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import androidx.core.view.ViewCompat;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.ImageReceiver;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.BotHelpCell;
import org.telegram.ui.Cells.ChatActionCell;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.Cells.UserInfoCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.ChatGreetingsView;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.ThanosEffect;
import org.telegram.ui.TextMessageEnterTransition;
import org.telegram.ui.VoiceMessageEnterTransition;

/* loaded from: classes7.dex */
public abstract class ChatListItemAnimator extends DefaultItemAnimator {
    public static final Interpolator DEFAULT_INTERPOLATOR = null;
    private final ChatActivity activity;
    long alphaEnterDelay;
    HashMap<RecyclerView.ViewHolder, Animator> animators;
    private ChatGreetingsView chatGreetingsView;
    private Utilities.Callback0Return<ThanosEffect> getThanosEffectContainer;
    private RecyclerView.ViewHolder greetingsSticker;
    HashMap<Long, Long> groupIdToEnterDelay;
    private final RecyclerListView recyclerListView;
    private final Theme.ResourcesProvider resourcesProvider;
    private boolean reversePositions;
    ArrayList<Runnable> runOnAnimationsEnd;
    private boolean shouldAnimateEnterFromBottom;
    private Utilities.Callback0Return<Boolean> supportsThanosEffectContainer;
    ArrayList<View> thanosViews;
    private final ArrayList<RecyclerView.ViewHolder> toBeSnapped;
    private ArrayList<MessageObject.GroupedMessages> willChangedGroups;
    private HashMap<Integer, MessageObject.GroupedMessages> willRemovedGroup;

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ boolean val$finalThanos;
        final /* synthetic */ ArrayList val$moves;

        public AnonymousClass1(ChatListItemAnimator r1, ArrayList r2, boolean r3) {
            this.this$0 = r1;
            this.val$moves = r2;
            this.val$finalThanos = r3;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList r0 = this.val$moves;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            Object r3 = r0.get(r2);
            r2 = r2 + 1;
            DefaultItemAnimator.MoveInfo r32 = (DefaultItemAnimator.MoveInfo) r3;
            this.this$0.animateMoveImpl(r32.holder, r32, this.val$finalThanos);
            goto L3
        L5:
            this.val$moves.clear();
            ChatListItemAnimator.access$000(this.this$0).remove(this.val$moves);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$10, reason: invalid class name */
    public class AnonymousClass10 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ float val$finalToX;
        final /* synthetic */ float val$finalToY;
        final /* synthetic */ ChatMessageCell val$messageCell;
        final /* synthetic */ float val$toH;
        final /* synthetic */ float val$toW;

        public AnonymousClass10(ChatListItemAnimator r1, ChatMessageCell r2, float r3, float r4, float r5, float r6) {
            this.this$0 = r1;
            this.val$messageCell = r2;
            this.val$finalToX = r3;
            this.val$finalToY = r4;
            this.val$toW = r5;
            this.val$toH = r6;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r5) {
            this.val$messageCell.getTransitionParams().resetAnimation();
            this.val$messageCell.getPhotoImage().setImageCoords(this.val$finalToX, this.val$finalToY, this.val$toW, this.val$toH);
            if (ChatListItemAnimator.m21933$$Nest$fgetchatGreetingsView(this.this$0) == null) goto L5;
            ChatListItemAnimator.m21933$$Nest$fgetchatGreetingsView(this.this$0).stickerToSendView.setAlpha(1.0f);
        L5:
            this.val$messageCell.invalidate();
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$11, reason: invalid class name */
    public class AnonymousClass11 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass11(ChatListItemAnimator r1, RecyclerView.ViewHolder r2, View r3) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$view = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            this.val$view.setAlpha(1.0f);
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            r2.removeAllListeners();
            this.val$view.setAlpha(1.0f);
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleY(1.0f);
            this.val$view.setTranslationY(0.0f);
            this.val$view.setTranslationY(0.0f);
            if (ChatListItemAnimator.access$1000(this.this$0).remove(this.val$holder) == false) goto L6;
            this.this$0.dispatchAddFinished(this.val$holder);
            ChatListItemAnimator.access$1100(this.this$0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchAddStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$12, reason: invalid class name */
    public class AnonymousClass12 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass12(ChatListItemAnimator r1, View r2, RecyclerView.ViewHolder r3) {
            this.this$0 = r1;
            this.val$view = r2;
            this.val$holder = r3;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            r2.removeAllListeners();
            this.val$view.setAlpha(1.0f);
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleY(1.0f);
            this.val$view.setTranslationX(0.0f);
            this.val$view.setTranslationY(0.0f);
            if (ChatListItemAnimator.access$1200(this.this$0).remove(this.val$holder) == false) goto L6;
            this.this$0.dispatchRemoveFinished(this.val$holder);
            ChatListItemAnimator.access$1300(this.this$0);
            return;
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ ArrayList val$changes;

        public AnonymousClass2(ChatListItemAnimator r1, ArrayList r2) {
            this.this$0 = r1;
            this.val$changes = r2;
        }

        @Override // java.lang.Runnable
        public void run() {
            ArrayList r0 = this.val$changes;
            int r1 = r0.size();
            int r2 = 0;
        L3:
            if (r2 >= r1) goto L5;
            Object r3 = r0.get(r2);
            r2 = r2 + 1;
            ChatListItemAnimator r4 = this.this$0;
            r4.animateChangeImpl((DefaultItemAnimator.ChangeInfo) r3);
            goto L3
        L5:
            this.val$changes.clear();
            ChatListItemAnimator.access$100(this.this$0).remove(this.val$changes);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$3, reason: invalid class name */
    public class AnonymousClass3 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ ViewPropertyAnimator val$animation;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass3(ChatListItemAnimator r1, RecyclerView.ViewHolder r2, View r3, ViewPropertyAnimator r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$view = r3;
            this.val$animation = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r2) {
            this.val$view.setTranslationY(0.0f);
            View r1 = this.val$view;
            if ((r1 instanceof ChatMessageCell) == false) goto L6;
            ((ChatMessageCell) r1).getTransitionParams().messageEntering = false;
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            View r22 = this.val$view;
            if ((r22 instanceof ChatMessageCell) == false) goto L5;
            ((ChatMessageCell) r22).getTransitionParams().messageEntering = false;
        L5:
            this.val$animation.setListener(null);
            if (ChatListItemAnimator.access$200(this.this$0).remove(this.val$holder) == false) goto L9;
            this.this$0.dispatchAddFinished(this.val$holder);
            ChatListItemAnimator.access$300(this.this$0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchAddStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$4, reason: invalid class name */
    public class AnonymousClass4 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ float val$animateFrom;
        final /* synthetic */ BotHelpCell val$botCell;

        public AnonymousClass4(ChatListItemAnimator r1, BotHelpCell r2, float r3) {
            this.this$0 = r1;
            this.val$botCell = r2;
            this.val$animateFrom = r3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            float r0 = ((((ChatListItemAnimator.m21934$$Nest$fgetrecyclerListView(this.this$0).getMeasuredHeight() - ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).getChatListViewPadding()) - ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).blurredViewBottomOffset) / 2.0f) - (this.val$botCell.getMeasuredHeight() / 2.0f)) + ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).getChatListViewPadding();
            if (this.val$botCell.getTop() <= r0) goto L5;
            float r02 = r0 - this.val$botCell.getTop();
        L6:
            this.val$botCell.setTranslationY((this.val$animateFrom * (1.0f - r42)) + (r02 * r42));
            return;
        L5:
            r02 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$5, reason: invalid class name */
    public class AnonymousClass5 implements ValueAnimator.AnimatorUpdateListener {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ float val$animateFrom;
        final /* synthetic */ UserInfoCell val$cell;

        public AnonymousClass5(ChatListItemAnimator r1, UserInfoCell r2, float r3) {
            this.this$0 = r1;
            this.val$cell = r2;
            this.val$animateFrom = r3;
        }

        @Override // android.animation.ValueAnimator.AnimatorUpdateListener
        public void onAnimationUpdate(ValueAnimator r4) {
            float r42 = ((Float) r4.getAnimatedValue()).floatValue();
            float r0 = ((((ChatListItemAnimator.m21934$$Nest$fgetrecyclerListView(this.this$0).getMeasuredHeight() - ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).getChatListViewPadding()) - ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).blurredViewBottomOffset) / 2.0f) - (this.val$cell.getMeasuredHeight() / 2.0f)) + ChatListItemAnimator.m21932$$Nest$fgetactivity(this.this$0).getChatListViewPadding();
            if (this.val$cell.getTop() <= r0) goto L5;
            float r02 = r0 - this.val$cell.getTop();
        L6:
            this.val$cell.setTranslationY((this.val$animateFrom * (1.0f - r42)) + (r02 * r42));
            return;
        L5:
            r02 = 0.0f;
            goto L6
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$6, reason: invalid class name */
    public class AnonymousClass6 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ MessageObject.GroupedMessages.TransitionParams val$groupTransitionParams;

        public AnonymousClass6(ChatListItemAnimator r1, MessageObject.GroupedMessages.TransitionParams r2) {
            this.this$0 = r1;
            this.val$groupTransitionParams = r2;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            MessageObject.GroupedMessages.TransitionParams r0 = this.val$groupTransitionParams;
            r0.backgroundChangeBounds = false;
            r0.drawBackgroundForDeletedItems = false;
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$7, reason: invalid class name */
    public class AnonymousClass7 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ int val$deltaY;
        final /* synthetic */ RecyclerView.ViewHolder val$holder;
        final /* synthetic */ View val$view;

        public AnonymousClass7(ChatListItemAnimator r1, RecyclerView.ViewHolder r2, int r3, View r4) {
            this.this$0 = r1;
            this.val$holder = r2;
            this.val$deltaY = r3;
            this.val$view = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationCancel(Animator r1) {
            if (this.val$deltaY == 0) goto L6;
            this.val$view.setTranslationY(0.0f);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            r2.removeAllListeners();
            ChatListItemAnimator.m21935$$Nest$mrestoreTransitionParams(this.this$0, this.val$holder.itemView);
            View r22 = this.val$holder.itemView;
            if ((r22 instanceof ChatMessageCell) == false) goto L11;
            ChatMessageCell r23 = (ChatMessageCell) r22;
            if (r23.makeVisibleAfterChange == false) goto L7;
            r23.makeVisibleAfterChange = false;
            r23.setVisibility(0);
        L7:
            MessageObject.GroupedMessages r24 = r23.getCurrentMessagesGroup();
            if (r24 == null) goto L11;
            r24.transitionParams.reset();
        L11:
            if (ChatListItemAnimator.access$400(this.this$0).remove(this.val$holder) == false) goto L14;
            this.this$0.dispatchMoveFinished(this.val$holder);
            ChatListItemAnimator.access$500(this.this$0);
            return;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r1) {
            this.this$0.dispatchMoveStarting(this.val$holder);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$8, reason: invalid class name */
    public class AnonymousClass8 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ DefaultItemAnimator.ChangeInfo val$changeInfo;
        final /* synthetic */ ViewPropertyAnimator val$oldViewAnim;
        final /* synthetic */ View val$view;

        public AnonymousClass8(ChatListItemAnimator r1, DefaultItemAnimator.ChangeInfo r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$changeInfo = r2;
            this.val$oldViewAnim = r3;
            this.val$view = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$oldViewAnim.setListener(null);
            this.val$view.setAlpha(1.0f);
            this.val$view.setScaleX(1.0f);
            this.val$view.setScaleX(1.0f);
            View r32 = this.val$view;
            if ((r32 instanceof ChatMessageCell) == false) goto L5;
            ((ChatMessageCell) r32).setAnimationOffsetX(0.0f);
        L6:
            this.val$view.setTranslationY(0.0f);
            if (ChatListItemAnimator.access$600(this.this$0).remove(this.val$changeInfo.oldHolder) == false) goto L10;
            this.this$0.dispatchChangeFinished(this.val$changeInfo.oldHolder, true);
            ChatListItemAnimator.access$700(this.this$0);
            return;
        L10:
            return;
        L5:
            r32.setTranslationX(0.0f);
            goto L6
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.dispatchChangeStarting(this.val$changeInfo.oldHolder, true);
        }
    }

    /* renamed from: org.telegram.ui.recyclerview.ChatListItemAnimator$9, reason: invalid class name */
    public class AnonymousClass9 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatListItemAnimator this$0;
        final /* synthetic */ DefaultItemAnimator.ChangeInfo val$changeInfo;
        final /* synthetic */ View val$newView;
        final /* synthetic */ ViewPropertyAnimator val$newViewAnimation;

        public AnonymousClass9(ChatListItemAnimator r1, DefaultItemAnimator.ChangeInfo r2, ViewPropertyAnimator r3, View r4) {
            this.this$0 = r1;
            this.val$changeInfo = r2;
            this.val$newViewAnimation = r3;
            this.val$newView = r4;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.val$newViewAnimation.setListener(null);
            this.val$newView.setAlpha(1.0f);
            this.val$newView.setScaleX(1.0f);
            this.val$newView.setScaleX(1.0f);
            View r32 = this.val$newView;
            if ((r32 instanceof ChatMessageCell) == false) goto L5;
            ((ChatMessageCell) r32).setAnimationOffsetX(0.0f);
        L6:
            this.val$newView.setTranslationY(0.0f);
            if (ChatListItemAnimator.access$800(this.this$0).remove(this.val$changeInfo.newHolder) == false) goto L10;
            this.this$0.dispatchChangeFinished(this.val$changeInfo.newHolder, false);
            ChatListItemAnimator.access$900(this.this$0);
            return;
        L10:
            return;
        L5:
            r32.setTranslationX(0.0f);
            goto L6
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationStart(Animator r2) {
            this.this$0.dispatchChangeStarting(this.val$changeInfo.newHolder, false);
        }
    }

    public class ItemHolderInfoExtended extends RecyclerView.ItemAnimator.ItemHolderInfo {
        float imageHeight;
        float imageWidth;
        float imageX;
        float imageY;
        final /* synthetic */ ChatListItemAnimator this$0;

        public ItemHolderInfoExtended(ChatListItemAnimator r1) {
            this.this$0 = r1;
        }
    }

    public class MoveInfoExtended extends DefaultItemAnimator.MoveInfo {
        public boolean animateBackgroundOnly;
        public boolean animateChangeGroupBackground;
        public boolean animateChangeInternal;
        boolean animateImage;
        public boolean animatePinnedBottom;
        boolean animateRemoveGroup;
        int deltaBottom;
        int deltaLeft;
        int deltaRight;
        int deltaTop;
        public int groupOffsetBottom;
        public int groupOffsetLeft;
        public int groupOffsetRight;
        public int groupOffsetTop;
        float imageHeight;
        float imageWidth;
        float imageX;
        float imageY;
        final /* synthetic */ ChatListItemAnimator this$0;

        public MoveInfoExtended(ChatListItemAnimator r1, RecyclerView.ViewHolder r2, int r3, int r4, int r5, int r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
        }
    }

    public static /* synthetic */ int $r8$lambda$0NLHKLzBmhjCgAIUOnunghuiOhE(RecyclerView.ViewHolder r0, RecyclerView.ViewHolder r1) {
        return r1.itemView.getTop() - r0.itemView.getTop();
    }

    /* renamed from: $r8$lambda$0iFW1LcuwzrDzN1iNTv8Aq-9QEw, reason: not valid java name */
    public static /* synthetic */ void m21929$r8$lambda$0iFW1LcuwzrDzN1iNTv8Aq9QEw(ChatListItemAnimator r0, ValueAnimator r1) {
        r0.lambda$runPendingAnimations$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$51q0GWi0ObdvhDKFihDT07qKlQQ(ChatActionCell.TransitionParams r0, ChatActionCell r1, ValueAnimator r2) {
        r0.animateChangeProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$8W2CifkKWRLeTD6jc3A2ApMzDzg(MoveInfoExtended r2, ChatMessageCell.TransitionParams r3, ChatMessageCell r4, ValueAnimator r5) {
        float r52 = ((Float) r5.getAnimatedValue()).floatValue();
        boolean r0 = r2.animateBackgroundOnly;
        int r1 = r2.deltaLeft;
        if (r0 == false) goto L5;
        r3.deltaLeft = (-r1) * r52;
        r3.deltaRight = (-r2.deltaRight) * r52;
        r3.deltaTop = (-r2.deltaTop) * r52;
        r3.deltaBottom = (-r2.deltaBottom) * r52;
    L6:
        r4.invalidate();
        return;
    L5:
        r3.deltaLeft = ((-r1) * r52) - r4.getAnimationOffsetX();
        r3.deltaRight = ((-r2.deltaRight) * r52) - r4.getAnimationOffsetX();
        r3.deltaTop = ((-r2.deltaTop) * r52) - r4.getTranslationY();
        r3.deltaBottom = ((-r2.deltaBottom) * r52) - r4.getTranslationY();
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$DESw_RADR9OXg_W7WyJ2EAqETMc(ChatMessageCell.TransitionParams r0, ChatMessageCell r1, ValueAnimator r2) {
        r0.animateChangeProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$DUJ7M6nOFpXXTifzbyoX8WH1zdU(ChatListItemAnimator r0, View r1, RecyclerView.ViewHolder r2) {
        r0.lambda$animateRemoveImpl$9(r1, r2);
    }

    /* renamed from: $r8$lambda$K7oJmts1jFKJu9LIRoLeF7ojs-A, reason: not valid java name */
    public static /* synthetic */ void m21930$r8$lambda$K7oJmts1jFKJu9LIRoLeF7ojsA(ChatListItemAnimator r0, ArrayList r1, ArrayList r2) {
        r0.lambda$animateRemoveGroupImpl$10(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$OQamxP6pcjwMza4x4sJNjqcje74(ChatMessageCell.TransitionParams r0, ChatMessageCell r1, ValueAnimator r2) {
        r0.changePinnedBottomProgress = ((Float) r2.getAnimatedValue()).floatValue();
        r1.invalidate();
    }

    public static /* synthetic */ void $r8$lambda$TDignImIjC3yBCWn8ikobW4ZBn8(MessageObject.GroupedMessages.TransitionParams r1, MoveInfoExtended r2, boolean r3, float r4, float r5, RecyclerListView r6, ValueAnimator r7) {
        float r72 = ((Float) r7.getAnimatedValue()).floatValue();
        r1.offsetTop = r2.groupOffsetTop * r72;
        r1.offsetBottom = r2.groupOffsetBottom * r72;
        r1.offsetLeft = r2.groupOffsetLeft * r72;
        r1.offsetRight = r2.groupOffsetRight * r72;
        if (r3 == false) goto L5;
        r1.captionEnterProgress = (r4 * r72) + (r5 * (1.0f - r72));
    L5:
        if (r6 == null) goto L8;
        r6.invalidate();
        return;
    }

    public static /* synthetic */ void $r8$lambda$jgyXUoF7EMpTRfhVSeeH1Kt7DMA(ChatMessageCell r2, float r3, float r4, float r5, float r6, float r7, float r8, float r9, float r10, ValueAnimator r11) {
        float r112 = ((Float) r11.getAnimatedValue()).floatValue();
        r2.getTransitionParams().animateChangeProgress = r112;
        if (r2.getTransitionParams().animateChangeProgress <= 1.0f) goto L5;
        r2.getTransitionParams().animateChangeProgress = 1.0f;
    L5:
        float r1 = 1.0f - r112;
        r2.getPhotoImage().setImageCoords(r3 + (r4 * r1), r5 + (r6 * r1), (r7 * r1) + (r8 * r112), (r9 * r1) + (r10 * r112));
        r2.invalidate();
    }

    /* renamed from: $r8$lambda$o-m2k4wYSnG7ii6RhLEuKuITF6s, reason: not valid java name */
    public static /* synthetic */ void m21931$r8$lambda$om2k4wYSnG7ii6RhLEuKuITF6s(MoveInfoExtended r6, ChatMessageCell.TransitionParams r7, boolean r8, float r9, float r10, ChatMessageCell r11, int[] r12, RecyclerView.ViewHolder r13, ValueAnimator r14) {
        float r142 = ((Float) r14.getAnimatedValue()).floatValue();
        float r1 = 1.0f - r142;
        float r0 = (r6.imageX * r1) + (r7.animateToImageX * r142);
        float r2 = (r6.imageY * r1) + (r7.animateToImageY * r142);
        float r3 = (r6.imageWidth * r1) + (r7.animateToImageW * r142);
        float r62 = (r6.imageHeight * r1) + (r7.animateToImageH * r142);
        if (r8 == false) goto L8;
        float r92 = (r9 * r1) + (r10 * r142);
        r7.captionEnterProgress = r92;
        if (r11.getCurrentMessagesGroup() == null) goto L8;
        r11.getCurrentMessagesGroup().transitionParams.captionEnterProgress = r92;
    L8:
        if (r7.animateRadius == false) goto L10;
        int[] r72 = r7.animateToRadius;
        r11.getPhotoImage().setRoundRadius((int) ((r12[0] * r1) + (r72[0] * r142)), (int) ((r12[1] * r1) + (r72[1] * r142)), (int) ((r12[2] * r1) + (r72[2] * r142)), (int) ((r12[3] * r1) + (r72[3] * r142)));
    L10:
        r11.setImageCoords(r0, r2, r3, r62);
        r13.itemView.invalidate();
    }

    /* renamed from: -$$Nest$fgetactivity, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m21932$$Nest$fgetactivity(ChatListItemAnimator r0) {
        return r0.activity;
    }

    /* renamed from: -$$Nest$fgetchatGreetingsView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatGreetingsView m21933$$Nest$fgetchatGreetingsView(ChatListItemAnimator r0) {
        return r0.chatGreetingsView;
    }

    /* renamed from: -$$Nest$fgetrecyclerListView, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RecyclerListView m21934$$Nest$fgetrecyclerListView(ChatListItemAnimator r0) {
        return r0.recyclerListView;
    }

    /* renamed from: -$$Nest$mrestoreTransitionParams, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21935$$Nest$mrestoreTransitionParams(ChatListItemAnimator r0, View r1) {
        r0.restoreTransitionParams(r1);
    }

    static {
        DEFAULT_INTERPOLATOR = new CubicBezierInterpolator(0.19919472913616398d, 0.010644531250000006d, 0.27920937042459737d, 0.91025390625d);
    }

    public ChatListItemAnimator(ChatActivity r2, RecyclerListView r3, Theme.ResourcesProvider r4) {
        this.willRemovedGroup = new HashMap();
        this.willChangedGroups = new ArrayList();
        this.animators = new HashMap();
        this.thanosViews = new ArrayList();
        this.runOnAnimationsEnd = new ArrayList();
        this.groupIdToEnterDelay = new HashMap();
        this.toBeSnapped = new ArrayList();
        this.resourcesProvider = r4;
        this.activity = r2;
        this.recyclerListView = r3;
        this.translationInterpolator = DEFAULT_INTERPOLATOR;
        this.alwaysCreateMoveAnimationIfPossible = true;
        setSupportsChangeAnimations(false);
    }

    public static /* synthetic */ ArrayList access$000(ChatListItemAnimator r0) {
        return r0.mMovesList;
    }

    public static /* synthetic */ ArrayList access$100(ChatListItemAnimator r0) {
        return r0.mChangesList;
    }

    public static /* synthetic */ ArrayList access$1000(ChatListItemAnimator r0) {
        return r0.mAddAnimations;
    }

    public static /* synthetic */ void access$1100(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    public static /* synthetic */ ArrayList access$1200(ChatListItemAnimator r0) {
        return r0.mRemoveAnimations;
    }

    public static /* synthetic */ void access$1300(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    public static /* synthetic */ ArrayList access$200(ChatListItemAnimator r0) {
        return r0.mAddAnimations;
    }

    public static /* synthetic */ void access$300(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    public static /* synthetic */ ArrayList access$400(ChatListItemAnimator r0) {
        return r0.mMoveAnimations;
    }

    public static /* synthetic */ void access$500(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    public static /* synthetic */ ArrayList access$600(ChatListItemAnimator r0) {
        return r0.mChangeAnimations;
    }

    public static /* synthetic */ void access$700(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    public static /* synthetic */ ArrayList access$800(ChatListItemAnimator r0) {
        return r0.mChangeAnimations;
    }

    public static /* synthetic */ void access$900(ChatListItemAnimator r0) {
        r0.dispatchFinishedWhenDone();
    }

    private void animateRemoveGroupImpl(ArrayList<RecyclerView.ViewHolder> r6) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("animate remove group impl with thanos");
    L5:
        this.mRemoveAnimations.addAll(r6);
        ThanosEffect r0 = this.getThanosEffectContainer.run();
        int r2 = 0;
    L7:
        if (r2 >= r6.size()) goto L9;
        dispatchRemoveStarting(r6.get(r2));
        r2 = r2 + 1;
        goto L7
    L9:
        ArrayList<View> r22 = new ArrayList();
        int r3 = 0;
    L11:
        if (r3 >= r6.size()) goto L13;
        r22.add(r6.get(r3).itemView);
        r3 = r3 + 1;
        goto L11
    L13:
        r0.animateGroup(r22, new ChatListItemAnimator$$ExternalSyntheticLambda10(this, r22, r6));
        this.thanosViews.add(r22.get(0));
        this.recyclerListView.stopScroll();
    }

    private void cancelAnimators() {
        ArrayList r0 = new ArrayList(this.animators.values());
        this.animators.clear();
        int r1 = r0.size();
        int r2 = 0;
    L3:
        if (r2 >= r1) goto L8;
        Object r3 = r0.get(r2);
        r2 = r2 + 1;
        Animator r32 = (Animator) r3;
        if (r32 == null) goto L3;
        r32.cancel();
        goto L3
    L8:
        if (this.thanosViews.isEmpty() == true) goto L18;
        ThanosEffect r4 = this.getThanosEffectContainer.run();
        if (r4 == null) goto L19;
        r4.kill();
        return;
    L19:
        return;
    }

    private /* synthetic */ void lambda$animateRemoveGroupImpl$10(ArrayList r4, ArrayList r5) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= r4.size()) goto L7;
        ((View) r4.get(r1)).setVisibility(0);
        r1 = r1 + 1;
        goto L4
    L7:
        if (this.mRemoveAnimations.removeAll(r5) == true) goto L9;
    L12:
        this.thanosViews.removeAll(r4);
        return;
    L9:
        if (r0 >= r5.size()) goto L11;
        dispatchRemoveFinished((RecyclerView.ViewHolder) r5.get(r0));
        r0 = r0 + 1;
        goto L9
    L11:
        dispatchFinishedWhenDone();
        goto L12
    }

    private /* synthetic */ void lambda$animateRemoveImpl$9(View r2, RecyclerView.ViewHolder r3) {
        r2.setVisibility(0);
        if (this.mRemoveAnimations.remove(r3) == false) goto L5;
        dispatchRemoveFinished(r3);
        dispatchFinishedWhenDone();
    L5:
        this.thanosViews.remove(r2);
    }

    private /* synthetic */ void lambda$runPendingAnimations$0(ValueAnimator r1) {
        ChatActivity r12 = this.activity;
        if (r12 == null) goto L6;
        r12.onListItemAnimatorTick();
        return;
    L6:
        this.recyclerListView.invalidate();
    }

    private void restoreTransitionParams(View r5) {
        r5.setAlpha(1.0f);
        r5.setScaleX(1.0f);
        r5.setScaleY(1.0f);
        r5.setTranslationY(0.0f);
        if ((r5 instanceof BotHelpCell) == false) goto L11;
        int r4 = (this.recyclerListView.getMeasuredHeight() / 2) - (r5.getMeasuredHeight() / 2);
        ((BotHelpCell) r5).setAnimating(false);
        if (r5.getTop() <= r4) goto L8;
        r5.setTranslationY(r4 - r5.getTop());
        return;
    L8:
        r5.setTranslationY(0.0f);
        return;
    L11:
        if ((r5 instanceof UserInfoCell) == false) goto L19;
        int r42 = (this.recyclerListView.getMeasuredHeight() / 2) - (r5.getMeasuredHeight() / 2);
        ((UserInfoCell) r5).setAnimating(false);
        if (r5.getTop() <= r42) goto L16;
        r5.setTranslationY(r42 - r5.getTop());
        return;
    L16:
        r5.setTranslationY(0.0f);
        return;
    L19:
        if ((r5 instanceof ChatMessageCell) == false) goto L23;
        ChatMessageCell r52 = (ChatMessageCell) r5;
        r52.getTransitionParams().resetAnimation();
        r52.setAnimationOffsetX(0.0f);
        return;
    L23:
        if ((r5 instanceof ChatActionCell) == false) goto L26;
        ((ChatActionCell) r5).getTransitionParams().resetAnimation();
        return;
    L26:
        r5.setTranslationX(0.0f);
    }

    private void runAlphaEnterTransition() {
        boolean r0 = this.mPendingRemovals.isEmpty();
        boolean r1 = this.mPendingMoves.isEmpty();
        boolean r2 = this.mPendingChanges.isEmpty();
        boolean r3 = this.mPendingAdditions.isEmpty();
        if (r0 == false) goto L8;
        if (r1 == false) goto L8;
        if (r3 == false) goto L8;
        if (r2 == false) goto L8;
        return;
    L8:
        int r6 = 0;
        if (this.getThanosEffectContainer == null) goto L15;
        Utilities.Callback0Return<Boolean> r4 = this.supportsThanosEffectContainer;
        if (r4 == null) goto L15;
        if (r4.run().booleanValue() == false) goto L15;
        boolean r42 = true;
    L17:
        if (r42 == false) goto L55;
        LongSparseArray r9 = null;
        int r10 = 0;
    L20:
        if (r10 >= this.mPendingRemovals.size()) goto L38;
        RecyclerView.ViewHolder r11 = this.mPendingRemovals.get(r10);
        if (this.toBeSnapped.contains(r11) == false) goto L37;
        View r12 = r11.itemView;
        if ((r12 instanceof ChatMessageCell) == false) goto L37;
        if (((ChatMessageCell) r12).getCurrentMessagesGroup() == null) goto L37;
        MessageObject r122 = ((ChatMessageCell) r11.itemView).getMessageObject();
        if (r122 == null) goto L37;
        if (r122.getGroupId() == 0) goto L37;
        if (r9 != null) goto L33;
        r9 = new LongSparseArray();
    L33:
        ArrayList r13 = (ArrayList) r9.get(r122.getGroupId());
        if (r13 != null) goto L36;
        long r123 = r122.getGroupId();
        ArrayList r14 = new ArrayList();
        r9.put(r123, r14);
        r13 = r14;
    L36:
        this.toBeSnapped.remove(r11);
        this.mPendingRemovals.remove(r10);
        r10 = r10 - 1;
        r13.add(r11);
    L37:
        r10 = r10 + 1;
        goto L20
    L38:
        if (r9 == null) goto L55;
        int r102 = 0;
        boolean r112 = false;
    L41:
        if (r102 >= r9.size()) goto L56;
        ArrayList<RecyclerView.ViewHolder> r124 = (ArrayList) r9.valueAt(r102);
        if (r124.size() <= 0) goto L54;
        View r132 = r124.get(0).itemView;
        if ((r132 instanceof ChatMessageCell) == false) goto L53;
        MessageObject.GroupedMessages r133 = ((ChatMessageCell) r132).getCurrentMessagesGroup();
        if (r133 == null) goto L53;
        if (r133.messages.size() <= r124.size()) goto L53;
        this.mPendingRemovals.addAll(r124);
    L53:
        animateRemoveGroupImpl(r124);
        r112 = true;
    L54:
        r102 = r102 + 1;
    L56:
        ArrayList<RecyclerView.ViewHolder> r92 = this.mPendingRemovals;
        int r103 = r92.size();
        int r125 = 0;
    L57:
        if (r125 >= r103) goto L66;
        RecyclerView.ViewHolder r134 = r92.get(r125);
        r125 = r125 + 1;
        RecyclerView.ViewHolder r135 = r134;
        if (this.toBeSnapped.remove(r135) == false) goto L62;
        if (r42 == false) goto L62;
        boolean r142 = true;
    L63:
        animateRemoveImpl(r135, r142);
        if (r142 == false) goto L57;
        r112 = true;
    L62:
        r142 = false;
        goto L63
    L66:
        this.mPendingRemovals.clear();
        if (r1 == true) goto L77;
        ArrayList<DefaultItemAnimator.MoveInfo> r15 = new ArrayList();
        r15.addAll(this.mPendingMoves);
        this.mMovesList.add(r15);
        this.mPendingMoves.clear();
        AnonymousClass1 r43 = new AnonymousClass1(this, r15, r112);
        if (this.delayAnimations == false) goto L76;
        if (r0 == true) goto L76;
        View r16 = r15.get(0).holder.itemView;
        if (r112 == false) goto L74;
        long r93 = 0;
    L75:
        ViewCompat.postOnAnimationDelayed(r16, r43, r93);
        goto L77
    L74:
        r93 = getMoveAnimationDelay();
    L76:
        r43.run();
    L77:
        if (r2 == true) goto L83;
        ArrayList<DefaultItemAnimator.ChangeInfo> r17 = new ArrayList();
        r17.addAll(this.mPendingChanges);
        this.mChangesList.add(r17);
        this.mPendingChanges.clear();
        AnonymousClass2 r22 = new AnonymousClass2(this, r17);
        if (this.delayAnimations == false) goto L82;
        if (r0 == true) goto L82;
        ViewCompat.postOnAnimationDelayed(r17.get(0).oldHolder.itemView, r22, 0);
    L82:
        r22.run();
    L83:
        if (r3 == true) goto L106;
        ArrayList r02 = new ArrayList();
        r02.addAll(this.mPendingAdditions);
        this.mPendingAdditions.clear();
        this.alphaEnterDelay = 0;
        Collections.sort(r02, new ChatListItemAnimator$$ExternalSyntheticLambda8());
        int r18 = r02.size();
    L85:
        if (r6 >= r18) goto L87;
        Object r23 = r02.get(r6);
        r6 = r6 + 1;
        animateAddImpl((RecyclerView.ViewHolder) r23);
        goto L85
    L87:
        r02.clear();
        return;
    L106:
        return;
    L55:
        r112 = false;
    L15:
        r42 = false;
        goto L17
    }

    private void runMessageEnterTransition() {
        boolean r0 = this.mPendingRemovals.isEmpty();
        boolean r1 = this.mPendingMoves.isEmpty();
        boolean r2 = this.mPendingChanges.isEmpty();
        boolean r3 = this.mPendingAdditions.isEmpty();
        if (r0 == false) goto L8;
        if (r1 == false) goto L8;
        if (r3 == false) goto L8;
        if (r2 == false) goto L8;
        return;
    L8:
        int r02 = 0;
        int r22 = 0;
        int r4 = 0;
    L10:
        if (r22 >= this.mPendingAdditions.size()) goto L20;
        View r5 = this.mPendingAdditions.get(r22).itemView;
        if ((r5 instanceof ChatMessageCell) == false) goto L18;
        ChatMessageCell r52 = (ChatMessageCell) r5;
        if (r52.getCurrentPosition() == null) goto L18;
        if ((r52.getCurrentPosition().flags & 1) != 0) goto L18;
    L19:
        r22 = r22 + 1;
    L18:
        r4 = r4 + this.mPendingAdditions.get(r22).itemView.getHeight();
        goto L19
    L20:
        ArrayList<RecyclerView.ViewHolder> r23 = this.mPendingRemovals;
        int r53 = r23.size();
        int r6 = 0;
    L21:
        if (r6 >= r53) goto L23;
        RecyclerView.ViewHolder r7 = r23.get(r6);
        r6 = r6 + 1;
        animateRemoveImpl(r7);
        goto L21
    L23:
        this.mPendingRemovals.clear();
        if (r1 == true) goto L29;
        ArrayList r12 = new ArrayList();
        r12.addAll(this.mPendingMoves);
        this.mPendingMoves.clear();
        int r24 = r12.size();
        int r54 = 0;
    L26:
        if (r54 >= r24) goto L28;
        Object r62 = r12.get(r54);
        r54 = r54 + 1;
        DefaultItemAnimator.MoveInfo r63 = (DefaultItemAnimator.MoveInfo) r62;
        animateMoveImpl(r63.holder, r63);
        goto L26
    L28:
        r12.clear();
    L29:
        if (r3 == true) goto L41;
        ArrayList r13 = new ArrayList();
        r13.addAll(this.mPendingAdditions);
        this.mPendingAdditions.clear();
        int r25 = r13.size();
    L31:
        if (r02 >= r25) goto L33;
        Object r32 = r13.get(r02);
        r02 = r02 + 1;
        animateAddImpl((RecyclerView.ViewHolder) r32, r4);
        goto L31
    L33:
        r13.clear();
        return;
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateAdd(RecyclerView.ViewHolder r4) {
        resetAnimation(r4);
        r4.itemView.setAlpha(0.0f);
        boolean r0 = this.shouldAnimateEnterFromBottom;
        View r1 = r4.itemView;
        if (r0 == true) goto L6;
        r1.setScaleX(0.9f);
        r4.itemView.setScaleY(0.9f);
    L8:
        this.mPendingAdditions.add(r4);
        return true;
    L6:
        if ((r1 instanceof ChatMessageCell) == false) goto L8;
        ((ChatMessageCell) r1).getTransitionParams().messageEntering = true;
        goto L8
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public void animateAddImpl(RecyclerView.ViewHolder r29) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("animate add impl");
    L5:
        View r8 = r29.itemView;
        this.mAddAnimations.add(r29);
        if (r29 != this.greetingsSticker) goto L8;
        r8.setAlpha(1.0f);
    L8:
        AnimatorSet r10 = new AnimatorSet();
        if ((r8 instanceof ChatMessageCell) == false) goto L14;
        ChatMessageCell r0 = (ChatMessageCell) r8;
        if (r0.getAnimationOffsetX() == 0.0f) goto L13;
        r10.playTogether(new Animator[]{ObjectAnimator.ofFloat(r0, r0.ANIMATION_OFFSET_X, new float[]{r0.getAnimationOffsetX(), 0.0f})});
    L13:
        r0.setPivotX(r0.getBackgroundDrawableLeft() + ((r0.getBackgroundDrawableRight() - r0.getBackgroundDrawableLeft()) / 2.0f));
        r8.animate().translationY(0.0f).setDuration(getAddDuration()).start();
    L15:
        long r2 = (long) ((1.0f - Math.max(0.0f, Math.min(1.0f, r8.getBottom() / this.recyclerListView.getMeasuredHeight()))) * 100.0f);
        if ((r8 instanceof ChatMessageCell) == true) goto L18;
        char r25 = 0;
    L34:
        boolean r02 = true;
    L36:
        r8.setAlpha(0.0f);
        Property r4 = View.ALPHA;
        float[] r6 = new float[2];
        r6[r25] = r8.getAlpha();
        r6[1] = 1.0f;
        Animator[] r5 = new Animator[1];
        r5[r25] = ObjectAnimator.ofFloat(r8, r4, r6);
        r10.playTogether(r5);
        if (r02 == false) goto L39;
        r8.setScaleX(0.9f);
        r8.setScaleY(0.9f);
        Property r03 = View.SCALE_Y;
        float[] r52 = new float[2];
        r52[r25] = r8.getScaleY();
        r52[1] = 1.0f;
        Animator[] r42 = new Animator[1];
        r42[r25] = ObjectAnimator.ofFloat(r8, r03, r52);
        r10.playTogether(r42);
        Property r04 = View.SCALE_X;
        float[] r53 = new float[2];
        r53[r25] = r8.getScaleX();
        r53[1] = 1.0f;
        Animator[] r43 = new Animator[1];
        r43[r25] = ObjectAnimator.ofFloat(r8, r04, r53);
        r10.playTogether(r43);
    L41:
        if (r29 != this.greetingsSticker) goto L43;
        r10.setDuration(350);
        r10.setInterpolator(new OvershootInterpolator());
    L44:
        r10.addListener(new AnonymousClass11(this, r29, r8));
        this.animators.put(r29, r10);
        r10.start();
        return;
    L43:
        r10.setStartDelay(r2);
        r10.setDuration(250);
        goto L44
    L39:
        r8.setScaleX(1.0f);
        r8.setScaleY(1.0f);
        goto L41
    L18:
        if (r29 != this.greetingsSticker) goto L23;
        ChatGreetingsView r05 = this.chatGreetingsView;
        if (r05 == null) goto L22;
        r05.stickerToSendView.setAlpha(0.0f);
    L22:
        this.recyclerListView.setClipChildren(false);
        ChatMessageCell r16 = (ChatMessageCell) r8;
        View r06 = (View) this.chatGreetingsView.getParent();
        float r44 = (this.chatGreetingsView.stickerToSendView.getX() + this.chatGreetingsView.getX()) + r06.getX();
        float r54 = (this.chatGreetingsView.stickerToSendView.getY() + this.chatGreetingsView.getY()) + r06.getY();
        float r07 = (r16.getPhotoImage().getImageX() + this.recyclerListView.getX()) + r16.getX();
        float r62 = (r16.getPhotoImage().getImageY() + this.recyclerListView.getY()) + r16.getY();
        float r15 = this.chatGreetingsView.stickerToSendView.getWidth();
        r25 = 0;
        float r12 = this.chatGreetingsView.stickerToSendView.getHeight();
        float r22 = r16.getPhotoImage().getImageWidth();
        float r24 = r16.getPhotoImage().getImageHeight();
        float r18 = r44 - r07;
        float r20 = r54 - r62;
        float r17 = r16.getPhotoImage().getImageX();
        float r19 = r16.getPhotoImage().getImageY();
        r16.getTransitionParams().imageChangeBoundsTransition = true;
        r16.getTransitionParams().animateDrawingTimeAlpha = true;
        r16.getPhotoImage().setImageCoords(r17 + r18, r17 + r20, r15, r12);
        ValueAnimator r08 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r08.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda0(r16, r17, r18, r19, r20, r15, r22, r12, r24));
        r08.addListener(new AnonymousClass10(this, r16, r17, r19, r22, r24));
        r10.play(r08);
        r2 = r2;
        r02 = false;
        goto L36
    L23:
        r25 = 0;
        MessageObject.GroupedMessages r09 = ((ChatMessageCell) r8).getCurrentMessagesGroup();
        if (r09 == null) goto L29;
        Long r23 = this.groupIdToEnterDelay.get(Long.valueOf(r09.groupId));
        if (r23 != null) goto L28;
        this.groupIdToEnterDelay.put(Long.valueOf(r09.groupId), Long.valueOf(r2));
        goto L29
    L28:
        r2 = r23.longValue();
    L30:
        if (r09 == null) goto L34;
        if (r09.transitionParams.backgroundChangeBounds == false) goto L34;
        r10.setStartDelay(140);
    L29:
        r2 = r2;
        goto L30
    L14:
        r8.animate().translationX(0.0f).translationY(0.0f).setDuration(getAddDuration()).start();
        goto L15
    }

    public void animateAddImpl(RecyclerView.ViewHolder r10, int r11) {
        View r0 = r10.itemView;
        ViewPropertyAnimator r1 = r0.animate();
        this.mAddAnimations.add(r10);
        r0.setTranslationY(r11);
        r10.itemView.setScaleX(1.0f);
        r10.itemView.setScaleY(1.0f);
        View r112 = r10.itemView;
        if ((r112 instanceof ChatMessageCell) == false) goto L6;
        ChatMessageCell r113 = (ChatMessageCell) r112;
    L5:
        ChatMessageCell r4 = r113;
        if (r4 != null) goto L9;
    L10:
        r10.itemView.setAlpha(1.0f);
    L11:
        ChatActivity r114 = this.activity;
        if (r114 == null) goto L29;
        if (r4 == null) goto L29;
        if (r114.animatingMessageObjects.contains(r4.getMessageObject()) == false) goto L29;
        this.activity.animatingMessageObjects.remove(r4.getMessageObject());
        if (this.activity.getChatActivityEnterView().canShowMessageTransition() == false) goto L29;
        if (r4.getMessageObject().isVoice() == false) goto L24;
        if (Math.abs(r0.getTranslationY()) >= (r0.getMeasuredHeight() * 3.0f)) goto L28;
        new VoiceMessageEnterTransition(r4, this.activity.getChatActivityEnterView(), this.recyclerListView, this.activity.messageEnterTransitionContainer, this.resourcesProvider).start();
    L28:
        this.activity.getChatActivityEnterView().startMessageTransition();
        goto L29
    L24:
        if (SharedConfig.getDevicePerformanceClass() == 0) goto L28;
        if (Math.abs(r0.getTranslationY()) >= this.recyclerListView.getMeasuredHeight()) goto L28;
        ChatActivity r5 = this.activity;
        new TextMessageEnterTransition(r4, r5, this.recyclerListView, r5.messageEnterTransitionContainer, this.resourcesProvider).start();
    L29:
        r1.translationY(0.0f).setDuration(getMoveDuration()).setInterpolator(this.translationInterpolator).setListener(new AnonymousClass3(this, r10, r0, r1)).start();
        return;
    L9:
        if (r4.getTransitionParams().ignoreAlpha == true) goto L11;
    L6:
        r113 = null;
        goto L5
    }

    @Override // androidx.recyclerview.widget.SimpleItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public boolean animateAppearance(RecyclerView.ViewHolder r3, RecyclerView.ItemAnimator.ItemHolderInfo r4, RecyclerView.ItemAnimator.ItemHolderInfo r5) {
        boolean r32 = super.animateAppearance(r3, r4, r5);
        if (r32 == true) goto L5;
    L22:
        return r32;
    L5:
        if (this.shouldAnimateEnterFromBottom == false) goto L22;
        int r42 = 0;
        int r52 = 0;
        boolean r0 = false;
    L8:
        if (r52 >= this.mPendingAdditions.size()) goto L13;
        if (this.mPendingAdditions.get(r52).getLayoutPosition() != 0) goto L12;
        r0 = true;
    L12:
        r52 = r52 + 1;
        goto L8
    L13:
        if (r0 == false) goto L18;
        int r53 = 0;
        int r02 = 0;
    L16:
        if (r53 >= this.mPendingAdditions.size()) goto L20;
        r02 = r02 + this.mPendingAdditions.get(r53).itemView.getHeight();
        r53 = r53 + 1;
    L20:
        if (r42 >= this.mPendingAdditions.size()) goto L22;
        this.mPendingAdditions.get(r42).itemView.setTranslationY(r02);
        r42 = r42 + 1;
        goto L20
    L18:
        r02 = 0;
        goto L20
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateChange(RecyclerView.ViewHolder r8, RecyclerView.ViewHolder r9, RecyclerView.ItemAnimator.ItemHolderInfo r10, int r11, int r12, int r13, int r14) {
        if (r8 == r9) goto L4;
        View r7 = r8.itemView;
        if ((r7 instanceof ChatMessageCell) == false) goto L8;
        float r72 = ((ChatMessageCell) r7).getAnimationOffsetX();
    L9:
        float r142 = r8.itemView.getTranslationY();
        float r1 = r8.itemView.getAlpha();
        resetAnimation(r8);
        int r2 = (int) ((r13 - r11) - r72);
        int r3 = (int) ((r14 - r12) - r142);
        View r4 = r8.itemView;
        if ((r4 instanceof ChatMessageCell) == false) goto L12;
        ((ChatMessageCell) r4).setAnimationOffsetX(r72);
    L13:
        r8.itemView.setTranslationY(r142);
        r8.itemView.setAlpha(r1);
        if (r9 == null) goto L20;
        resetAnimation(r9);
        View r73 = r9.itemView;
        if ((r73 instanceof ChatMessageCell) == false) goto L18;
        ((ChatMessageCell) r73).setAnimationOffsetX(-r2);
    L19:
        r9.itemView.setTranslationY(-r3);
        r9.itemView.setAlpha(0.0f);
        goto L20
    L18:
        r73.setTranslationX(-r2);
    L20:
        this.mPendingChanges.add(new DefaultItemAnimator.ChangeInfo(r8, r9, r11, r12, r13, r14));
        checkIsRunning();
        return true;
    L12:
        r4.setTranslationX(r72);
        goto L13
    L8:
        r72 = r7.getTranslationX();
        goto L9
    L4:
        return animateMove(r8, r10, r11, r12, r13, r14);
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public void animateChangeImpl(DefaultItemAnimator.ChangeInfo r7) {
        RecyclerView.ViewHolder r0 = r7.oldHolder;
        View r1 = null;
        if (r0 != null) goto L5;
        View r02 = null;
    L6:
        RecyclerView.ViewHolder r2 = r7.newHolder;
        if (r2 == null) goto L10;
        r1 = r2.itemView;
    L10:
        if (r02 == null) goto L12;
        ViewPropertyAnimator r3 = r02.animate().setDuration(getChangeDuration());
        this.mChangeAnimations.add(r7.oldHolder);
        r3.translationX(r7.toX - r7.fromX);
        r3.translationY(r7.toY - r7.fromY);
        r3.alpha(0.0f).setListener(new AnonymousClass8(this, r7, r3, r02)).start();
    L12:
        if (r1 == null) goto L15;
        ViewPropertyAnimator r03 = r1.animate();
        this.mChangeAnimations.add(r7.newHolder);
        r03.translationX(0.0f).translationY(0.0f).setDuration(getChangeDuration()).alpha(1.0f).setListener(new AnonymousClass9(this, r7, r03, r1)).start();
        return;
    L15:
        return;
    L5:
        r02 = r0.itemView;
        goto L6
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateMove(RecyclerView.ViewHolder r23, RecyclerView.ItemAnimator.ItemHolderInfo r24, int r25, int r26, int r27, int r28) {
        View r8 = r23.itemView;
        if ((r8 instanceof ChatMessageCell) == false) goto L10;
        ChatMessageCell r0 = (ChatMessageCell) r8;
        int r3 = r25 + ((int) r0.getAnimationOffsetX());
        if (r0.getTransitionParams().lastTopOffset == r0.getTopMediaOffset()) goto L8;
        int r4 = r26 + (r0.getTransitionParams().lastTopOffset - r0.getTopMediaOffset());
    L7:
        ChatMessageCell r9 = r0;
        ChatActionCell r10 = null;
    L13:
        int r42 = r4 + ((int) r23.itemView.getTranslationY());
        int[] r12 = new int[4];
        if (r9 == null) goto L19;
        float r02 = r9.getPhotoImage().getImageX();
        float r1 = r9.getPhotoImage().getImageY();
        float r5 = r9.getPhotoImage().getImageWidth();
        float r6 = r9.getPhotoImage().getImageHeight();
        int r15 = 0;
    L16:
        if (r15 >= 4) goto L18;
        r12[r15] = r9.getPhotoImage().getRoundRadius()[r15];
        r15 = r15 + 1;
        goto L16
    L18:
        float r152 = r02;
    L20:
        resetAnimation(r23);
        int r03 = r27 - r3;
        int r13 = r28 - r42;
        if (r13 == 0) goto L23;
        r8.setTranslationY(-r13);
    L23:
        float r17 = r5;
        float r18 = r6;
        float r11 = r1;
        MoveInfoExtended r04 = new MoveInfoExtended(this, r23, r3, r42, r27, r28);
        if (r9 == null) goto L191;
        ChatMessageCell.TransitionParams r43 = r9.getTransitionParams();
        if (r43.supportChangeAnimation() == true) goto L35;
        if (r03 != 0) goto L31;
        if (r13 != 0) goto L31;
        dispatchMoveFinished(r23);
        return false;
    L31:
        if (r03 == 0) goto L33;
        r8.setTranslationX(-r03);
    L33:
        this.mPendingMoves.add(r04);
        checkIsRunning();
        return true;
    L35:
        MessageObject.GroupedMessages r52 = r9.getCurrentMessagesGroup();
        if (r03 == 0) goto L39;
        r9.setAnimationOffsetX(-r03);
    L39:
        if ((r24 instanceof ItemHolderInfoExtended) == false) goto L114;
        ImageReceiver r62 = r9.getPhotoImage();
        ItemHolderInfoExtended r7 = (ItemHolderInfoExtended) r24;
        if (r43.wasDraw == true) goto L43;
    L47:
        boolean r102 = false;
    L48:
        r04.animateImage = r102;
        if (r102 == false) goto L90;
        this.recyclerListView.setClipChildren(false);
        this.recyclerListView.invalidate();
        r43.imageChangeBoundsTransition = true;
        if (r9.getMessageObject().isRoundVideo() == false) goto L54;
        r43.animateToImageX = r152;
        r43.animateToImageY = r11;
        r43.animateToImageW = r17;
        r43.animateToImageH = r18;
        r43.animateToRadius = r12;
    L55:
        r43.animateRadius = false;
        int r82 = 0;
    L57:
        if (r82 >= 4) goto L62;
        if (r43.imageRoundRadius[r82] != r43.animateToRadius[r82]) goto L60;
        r82 = r82 + 1;
        goto L57
    L60:
        r43.animateRadius = true;
    L62:
        float r83 = r43.animateToImageX;
        float r103 = r7.imageX;
        if (r83 == r103) goto L65;
    L73:
        r04.imageX = r103;
        r04.imageY = r7.imageY;
        r04.imageWidth = r7.imageWidth;
        r04.imageHeight = r7.imageHeight;
        if (r52 == null) goto L82;
        boolean r72 = r52.hasCaption;
        MessageObject.GroupedMessages.TransitionParams r84 = r52.transitionParams;
        boolean r104 = r84.drawCaptionLayout;
        if (r72 == r104) goto L82;
        if (r104 == false) goto L79;
        float r73 = 1.0f;
    L80:
        r84.captionEnterProgress = r73;
        goto L82
    L79:
        r73 = 0.0f;
    L82:
        if (r43.animateRadius == true) goto L84;
    L89:
        r9.setImageCoords(r04.imageX, r04.imageY, r04.imageWidth, r04.imageHeight);
        goto L90
    L84:
        if (r43.animateToRadius != r62.getRoundRadius()) goto L88;
        r43.animateToRadius = new int[4];
        int r74 = 0;
    L86:
        if (r74 >= 4) goto L88;
        r43.animateToRadius[r74] = r62.getRoundRadius()[r74];
        r74 = r74 + 1;
    L88:
        r62.setRoundRadius(r43.imageRoundRadius);
        goto L89
    L65:
        if (r43.animateToImageY != r7.imageY) goto L73;
        if (r43.animateToImageH != r7.imageHeight) goto L73;
        if (r43.animateToImageW != r7.imageWidth) goto L73;
        if (r43.animateRadius == true) goto L73;
        r43.imageChangeBoundsTransition = false;
        r04.animateImage = false;
        goto L90
    L54:
        r43.animateToImageX = r62.getImageX();
        r43.animateToImageY = r62.getImageY();
        r43.animateToImageW = r62.getImageWidth();
        r43.animateToImageH = r62.getImageHeight();
        r43.animateToRadius = r62.getRoundRadius();
    L90:
        if (r52 != null) goto L114;
        if (r43.wasDraw == false) goto L114;
        boolean r63 = r9.getMessageObject().isOutOwner();
        if (r63 == true) goto L96;
    L97:
        if (r63 == false) goto L99;
    L101:
        boolean r75 = false;
    L102:
        if (r75 == false) goto L104;
    L107:
        r04.deltaBottom = r9.getBackgroundDrawableBottom() - r43.lastDrawingBackgroundRect.bottom;
        r04.deltaTop = r9.getBackgroundDrawableTop() - r43.lastDrawingBackgroundRect.top;
        if (r9.isSideMenuEnabled == r43.lastDrawingSideMenuEnabled) goto L110;
        r04.deltaLeft = r9.getBackgroundDrawableLeft() - r43.lastDrawingBackgroundRect.left;
        r04.deltaRight = r9.getBackgroundDrawableRight() - r43.lastDrawingBackgroundRect.right;
    L113:
        r04.animateBackgroundOnly = true;
        r43.animateBackgroundBoundsInner = true;
        r43.animateBackgroundWidth = r75;
        r43.deltaLeft = -r04.deltaLeft;
        r43.deltaRight = -r04.deltaRight;
        r43.deltaTop = -r04.deltaTop;
        r43.deltaBottom = -r04.deltaBottom;
        this.recyclerListView.setClipChildren(false);
        this.recyclerListView.invalidate();
        goto L114
    L110:
        if (r63 == false) goto L112;
        r04.deltaLeft = r9.getBackgroundDrawableLeft() - r43.lastDrawingBackgroundRect.left;
        goto L113
    L112:
        r04.deltaRight = r9.getBackgroundDrawableRight() - r43.lastDrawingBackgroundRect.right;
        goto L113
    L104:
        if (r43.lastDrawingBackgroundRect.top != r9.getBackgroundDrawableTop()) goto L107;
        if (r43.lastDrawingBackgroundRect.bottom == r9.getBackgroundDrawableBottom()) goto L114;
    L99:
        if (r43.lastDrawingBackgroundRect.right == r9.getBackgroundDrawableRight()) goto L101;
    L100:
        r75 = true;
        goto L102
    L96:
        if (r43.lastDrawingBackgroundRect.left != r9.getBackgroundDrawableLeft()) goto L100;
    L43:
        if (r7.imageHeight == 0.0f) goto L47;
        if (r7.imageWidth == 0.0f) goto L47;
        r102 = true;
    L114:
        if (r52 != null) goto L116;
    L160:
        ChatMessageCell r21 = r9;
    L161:
        MessageObject.GroupedMessages r32 = this.willRemovedGroup.get(Integer.valueOf(r21.getMessageObject().getId()));
        if (r32 == null) goto L167;
        MessageObject.GroupedMessages.TransitionParams r33 = r32.transitionParams;
        this.willRemovedGroup.remove(Integer.valueOf(r21.getMessageObject().getId()));
        if (r43.wasDraw == false) goto L166;
        int r53 = r21.getLeft() + r21.getBackgroundDrawableLeft();
        int r64 = r21.getLeft() + r21.getBackgroundDrawableRight();
        int r76 = (r21.getTop() + r21.getPaddingTop()) + r21.getBackgroundDrawableTop();
        int r85 = (r21.getTop() + r21.getPaddingTop()) + r21.getBackgroundDrawableBottom();
        r04.animateRemoveGroup = true;
        r43.animateBackgroundBoundsInner = true;
        r04.deltaLeft = r53 - r33.left;
        r04.deltaRight = r64 - r33.right;
        r04.deltaTop = r76 - r33.top;
        r04.deltaBottom = r85 - r33.bottom;
        r04.animateBackgroundOnly = false;
        r43.deltaLeft = (int) ((-r5) - r21.getAnimationOffsetX());
        r43.deltaRight = (int) ((-r04.deltaRight) - r21.getAnimationOffsetX());
        r43.deltaTop = (int) ((-r04.deltaTop) - r21.getTranslationY());
        r43.deltaBottom = (int) ((-r04.deltaBottom) - r21.getTranslationY());
        boolean r105 = true;
        r43.transformGroupToSingleMessage = true;
        this.recyclerListView.setClipChildren(false);
        this.recyclerListView.invalidate();
    L169:
        if (r43.drawPinnedBottomBackground == r21.isDrawPinnedBottom()) goto L171;
        r04.animatePinnedBottom = r105;
        float r34 = 0.0f;
        r43.changePinnedBottomProgress = 0.0f;
    L172:
        boolean r54 = r43.animateChange();
        r04.animateChangeInternal = r54;
        if (r54 == false) goto L175;
        r43.animateChange = r105;
        r43.animateChangeProgress = r34;
    L175:
        if (r03 != 0) goto L223;
        if (r13 != 0) goto L223;
        if (r04.animateImage == true) goto L223;
        if (r04.animateRemoveGroup == true) goto L223;
        if (r04.animateChangeGroupBackground == true) goto L223;
        if (r04.animatePinnedBottom == true) goto L223;
        if (r04.animateBackgroundOnly == true) goto L223;
        if (r54 == true) goto L223;
        dispatchMoveFinished(r23);
        return false;
    L223:
        this.mPendingMoves.add(r04);
        checkIsRunning();
        return true;
    L171:
        r34 = 0.0f;
        goto L172
    L166:
        r105 = true;
        r33.drawBackgroundForDeletedItems = true;
        goto L169
    L167:
        r105 = true;
        goto L169
    L116:
        if (this.willChangedGroups.contains(r52) == false) goto L160;
        this.willChangedGroups.remove(r52);
        RecyclerListView r65 = (RecyclerListView) r23.itemView.getParent();
        MessageObject.GroupedMessages.TransitionParams r77 = r52.transitionParams;
        boolean r19 = true;
        int r86 = 0;
        int r106 = 0;
        int r112 = 0;
        int r122 = 0;
        int r153 = 0;
    L119:
        if (r86 >= r65.getChildCount()) goto L147;
        View r35 = r65.getChildAt(r86);
        int r172 = r86;
        if ((r35 instanceof ChatMessageCell) == false) goto L145;
        ChatMessageCell r36 = (ChatMessageCell) r35;
        if (r36.getCurrentMessagesGroup() != r52) goto L145;
        if (r36.getMessageObject().deleted == true) goto L145;
        int r87 = r36.getLeft() + r36.getBackgroundDrawableLeft();
        int r37 = r36.getLeft() + r36.getBackgroundDrawableRight();
        MessageObject.GroupedMessages r282 = r52;
        int r55 = (r36.getTop() + r36.getPaddingTop()) + r36.getBackgroundDrawableTop();
        ChatMessageCell r212 = r9;
        int r92 = (r36.getTop() + r36.getPaddingTop()) + r36.getBackgroundDrawableBottom();
        if (r122 == 0) goto L129;
        if (r87 < r122) goto L129;
    L130:
        if (r153 == 0) goto L132;
        if (r37 > r153) goto L132;
    L134:
        if (r36.getTransitionParams().wasDraw == false) goto L136;
    L137:
        if (r106 == 0) goto L139;
        if (r55 < r106) goto L139;
    L140:
        if (r112 == 0) goto L144;
        if (r92 > r112) goto L144;
    L143:
        r19 = false;
    L146:
        r86 = r172 + 1;
        r52 = r282;
        r9 = r212;
    L144:
        r112 = r92;
    L139:
        r106 = r55;
        goto L140
    L136:
        if (r77.isNewGroup == false) goto L146;
    L132:
        r153 = r37;
    L129:
        r122 = r87;
    L145:
        r282 = r52;
        r212 = r9;
        goto L146
    L147:
        r21 = r9;
        r77.isNewGroup = false;
        if (r106 != 0) goto L154;
        if (r112 != 0) goto L154;
        if (r122 != 0) goto L154;
        if (r153 != 0) goto L154;
        r04.animateChangeGroupBackground = false;
        r77.backgroundChangeBounds = false;
    L159:
        r77.drawBackgroundForDeletedItems = r19;
    L154:
        int r38 = (-r106) + r77.top;
        r04.groupOffsetTop = r38;
        int r56 = (-r112) + r77.bottom;
        r04.groupOffsetBottom = r56;
        int r88 = (-r122) + r77.left;
        r04.groupOffsetLeft = r88;
        int r93 = (-r153) + r77.right;
        r04.groupOffsetRight = r93;
        r04.animateChangeGroupBackground = true;
        r77.backgroundChangeBounds = true;
        r77.offsetTop = r38;
        r77.offsetBottom = r56;
        r77.offsetLeft = r88;
        r77.offsetRight = r93;
        if (r77.drawCaptionLayout == false) goto L157;
        float r39 = 1.0f;
    L158:
        r77.captionEnterProgress = r39;
        r65.setClipChildren(false);
        r65.invalidate();
        goto L159
    L157:
        r39 = 0.0f;
        goto L158
    L191:
        if (r10 == null) goto L212;
        ChatActionCell.TransitionParams r310 = r10.getTransitionParams();
        if (r310.supportChangeAnimation() == true) goto L203;
        if (r03 != 0) goto L197;
        if (r13 != 0) goto L197;
        dispatchMoveFinished(r23);
        return false;
    L197:
        if (r03 == 0) goto L199;
        r8.setTranslationX(-r03);
    L199:
        this.mPendingMoves.add(r04);
        checkIsRunning();
        return true;
    L203:
        if (r03 == 0) goto L205;
        r8.setTranslationX(-r03);
    L205:
        boolean r44 = r310.animateChange();
        r04.animateChangeInternal = r44;
        if (r44 == false) goto L208;
        r310.animateChange = true;
        r310.animateChangeProgress = 0.0f;
    L208:
        if (r03 != 0) goto L223;
        if (r13 != 0) goto L223;
        if (r44 == true) goto L223;
        dispatchMoveFinished(r23);
        return false;
    L212:
        View r311 = r23.itemView;
        if ((r311 instanceof BotHelpCell) == false) goto L216;
        ((BotHelpCell) r311).setAnimating(true);
        goto L223
    L216:
        if ((r311 instanceof UserInfoCell) == false) goto L218;
        ((UserInfoCell) r311).setAnimating(true);
        goto L223
    L218:
        if (r03 != 0) goto L221;
        if (r13 != 0) goto L221;
        dispatchMoveFinished(r23);
        return false;
    L221:
        if (r03 == 0) goto L223;
        r8.setTranslationX(-r03);
        goto L223
    L19:
        r1 = 0.0f;
        r5 = 0.0f;
        r6 = 0.0f;
        r152 = 0.0f;
        goto L20
    L8:
        r4 = r26;
        goto L7
    L10:
        if ((r8 instanceof ChatActionCell) == false) goto L12;
        r3 = r25 + ((int) r8.getTranslationX());
        r4 = r26;
        r10 = (ChatActionCell) r8;
        r9 = null;
        goto L13
    L12:
        r3 = r25 + ((int) r8.getTranslationX());
        r4 = r26;
        r9 = null;
        r10 = null;
        goto L13
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public void animateMoveImpl(RecyclerView.ViewHolder r2, DefaultItemAnimator.MoveInfo r3) {
        animateMoveImpl(r2, r3, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void animateMoveImpl(RecyclerView.ViewHolder r22, DefaultItemAnimator.MoveInfo r23, boolean r24) {
        int r2 = r23.fromX;
        int r25 = r23.fromY;
        int r3 = r23.toY;
        View r10 = r22.itemView;
        int r11 = r3 - r25;
        AnimatorSet r12 = new AnimatorSet();
        if (r11 == 0) goto L5;
        r12.playTogether(new Animator[]{ObjectAnimator.ofFloat(r10, View.TRANSLATION_Y, new float[]{0.0f})});
    L5:
        this.mMoveAnimations.add(r22);
        MoveInfoExtended r26 = (MoveInfoExtended) r23;
        ChatActivity r1 = this.activity;
        float r16 = 1.0f;
        if (r1 == null) goto L11;
        View r4 = r22.itemView;
        if ((r4 instanceof BotHelpCell) == false) goto L11;
        BotHelpCell r42 = (BotHelpCell) r4;
        float r13 = r42.getTranslationY();
        ValueAnimator r27 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r27.addUpdateListener(new AnonymousClass4(this, r42, r13));
        r12.playTogether(new Animator[]{r27});
    L10:
        RecyclerView.ViewHolder r28 = r22;
    L84:
        if (r24 == false) goto L86;
        r12.setInterpolator(CubicBezierInterpolator.EASE_OUT);
    L89:
        float r14 = getMoveDuration();
        if (r24 == false) goto L92;
        r16 = 1.9f;
    L92:
        r12.setDuration((long) (r14 * r16));
        r12.addListener(new AnonymousClass7(this, r28, r11, r10));
        r12.start();
        this.animators.put(r28, r12);
        return;
    L86:
        Interpolator r15 = this.translationInterpolator;
        if (r15 == null) goto L89;
        r12.setInterpolator(r15);
    L11:
        if (r1 == null) goto L15;
        View r17 = r22.itemView;
        if ((r17 instanceof UserInfoCell) == false) goto L15;
        UserInfoCell r18 = (UserInfoCell) r17;
        float r29 = r18.getTranslationY();
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r32.addUpdateListener(new AnonymousClass5(this, r18, r29));
        r12.playTogether(new Animator[]{r32});
    L15:
        View r19 = r22.itemView;
        if ((r19 instanceof ChatMessageCell) == false) goto L79;
        ChatMessageCell r7 = (ChatMessageCell) r19;
        ChatMessageCell.TransitionParams r110 = r7.getTransitionParams();
        r12.playTogether(new Animator[]{ObjectAnimator.ofFloat(r7, r7.ANIMATION_OFFSET_X, new float[]{0.0f})});
        if (r26.animateImage == false) goto L40;
        r7.setImageCoords(r26.imageX, r26.imageY, r26.imageWidth, r26.imageHeight);
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        if (r7.getCurrentMessagesGroup() != null) goto L22;
        float r5 = r110.captionEnterProgress;
    L24:
        if (r7.getCurrentMessagesGroup() != null) goto L27;
        int r6 = r7.hasCaptionLayout();
    L26:
        float r62 = r6;
        if (r5 == r62) goto L31;
        ValueAnimator r8 = r43;
        boolean r44 = true;
    L33:
        if (r110.animateRadius == false) goto L38;
        boolean r172 = false;
        int[] r132 = new int[4];
        int r152 = 0;
    L35:
        if (r152 >= 4) goto L37;
        r132[r152] = r7.getPhotoImage().getRoundRadius()[r152];
        r152 = r152 + 1;
    L37:
        ChatMessageCell.TransitionParams r33 = r110;
        int[] r153 = r132;
        ValueAnimator r133 = r8;
        int r154 = 2;
        ChatListItemAnimator$$ExternalSyntheticLambda2 r111 = new ChatListItemAnimator$$ExternalSyntheticLambda2(r26, r33, r44, r5, r62, r7, r153, r22);
        MoveInfoExtended r52 = r26;
        ChatMessageCell r112 = r7;
        r28 = r22;
        r133.addUpdateListener(r111);
        Animator[] r45 = new Animator[1];
        r45[r172 ? 1 : 0] = r133;
        r12.playTogether(r45);
    L42:
        if (r52.deltaBottom == 0) goto L44;
    L49:
        float r46 = 0.0f;
        this.recyclerListView.setClipChildren(r172);
        this.recyclerListView.invalidate();
        float[] r63 = new float[r154];
        r63 = {x02a0: FILL_ARRAY_DATA , data: [1065353216, 0} // fill-array;
        ValueAnimator r64 = ValueAnimator.ofFloat(r63);
        boolean r72 = r52.animateBackgroundOnly;
        int r82 = r52.deltaLeft;
        if (r72 == false) goto L54;
        r33.toDeltaLeft = -r82;
        r33.toDeltaRight = -r52.deltaRight;
    L55:
        r64.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda3(r52, r33, r112));
        boolean r83 = false;
        r12.playTogether(new Animator[]{r64});
    L56:
        MessageObject.GroupedMessages r65 = r112.getCurrentMessagesGroup();
        if (r65 != null) goto L60;
        r52.animateChangeGroupBackground = r83;
    L60:
        if (r52.animateChangeGroupBackground == false) goto L71;
        float[] r73 = new float[r154];
        r73 = {x02a8: FILL_ARRAY_DATA , data: [1065353216, 0} // fill-array;
        ValueAnimator r134 = ValueAnimator.ofFloat(r73);
        float r182 = r46;
        MessageObject.GroupedMessages.TransitionParams r47 = r65.transitionParams;
        RecyclerListView r9 = (RecyclerListView) r28.itemView.getParent();
        float r74 = r65.transitionParams.captionEnterProgress;
        if (r65.hasCaption == false) goto L64;
        float r84 = 1.0f;
    L66:
        if (r74 == r84) goto L69;
        boolean r66 = true;
    L70:
        ChatMessageCell.TransitionParams r20 = r33;
        r134.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda4(r47, r52, r66, r74, r84, r9));
        r134.addListener(new AnonymousClass6(this, r47));
        r12.playTogether(new Animator[]{r134});
    L73:
        if (r52.animatePinnedBottom == false) goto L75;
        float[] r34 = new float[r154];
        r34 = {x02b0: FILL_ARRAY_DATA , data: [0, 1065353216} // fill-array;
        ValueAnimator r35 = ValueAnimator.ofFloat(r34);
        ChatMessageCell.TransitionParams r67 = r20;
        r35.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda5(r67, r112));
        r12.playTogether(new Animator[]{r35});
    L77:
        if (r52.animateChangeInternal == false) goto L84;
        float[] r36 = new float[r154];
        r36 = {x02b8: FILL_ARRAY_DATA , data: [0, 1065353216} // fill-array;
        ValueAnimator r37 = ValueAnimator.ofFloat(r36);
        r67.animateChange = true;
        r37.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda6(r67, r112));
        r12.playTogether(new Animator[]{r37});
        goto L84
    L75:
        r67 = r20;
        goto L77
    L69:
        r66 = false;
        goto L70
    L64:
        r84 = r182;
        goto L66
    L71:
        r20 = r33;
        goto L73
    L54:
        r33.toDeltaLeft = (-r82) - r112.getAnimationOffsetX();
        r33.toDeltaRight = (-r52.deltaRight) - r112.getAnimationOffsetX();
        goto L55
    L44:
        if (r52.deltaRight != 0) goto L49;
        if (r52.deltaTop != 0) goto L49;
        if (r52.deltaLeft != 0) goto L49;
        r46 = 0.0f;
        r33.toDeltaLeft = 0.0f;
        r33.toDeltaRight = 0.0f;
        r83 = r172;
        goto L56
    L38:
        r172 = false;
        r132 = null;
        goto L37
    L31:
        r8 = r43;
        r44 = false;
        goto L33
    L27:
        r6 = r7.getCurrentMessagesGroup().hasCaption;
        goto L26
    L22:
        r5 = r7.getCurrentMessagesGroup().transitionParams.captionEnterProgress;
        goto L24
    L40:
        r52 = r26;
        r154 = 2;
        r28 = r22;
        r172 = false;
        r33 = r110;
        r112 = r7;
        goto L42
    L79:
        r28 = r22;
        if ((r19 instanceof ChatActionCell) == false) goto L84;
        ChatActionCell r113 = (ChatActionCell) r19;
        ChatActionCell.TransitionParams r38 = r113.getTransitionParams();
        if (r26.animateChangeInternal == false) goto L84;
        ValueAnimator r48 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r38.animateChange = true;
        r48.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda7(r38, r113));
        r12.playTogether(new Animator[]{r48});
        goto L84
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.SimpleItemAnimator
    public boolean animateRemove(RecyclerView.ViewHolder r5, RecyclerView.ItemAnimator.ItemHolderInfo r6) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("animate remove");
    L5:
        boolean r4 = super.animateRemove(r5, r6);
        if (r4 == false) goto L22;
        if (r6 == null) goto L22;
        int r0 = r6.top;
        int r1 = r5.itemView.getTop();
        int r3 = r5.itemView.getLeft() - r6.left;
        if ((r1 - r0) == 0) goto L11;
        r5.itemView.setTranslationY(-r1);
    L11:
        View r52 = r5.itemView;
        if ((r52 instanceof ChatMessageCell) == false) goto L20;
        ChatMessageCell r53 = (ChatMessageCell) r52;
        if (r3 == 0) goto L17;
        r53.setAnimationOffsetX(-r3);
    L17:
        if ((r6 instanceof ItemHolderInfoExtended) == false) goto L22;
        ItemHolderInfoExtended r62 = (ItemHolderInfoExtended) r6;
        r53.setImageCoords(r62.imageX, r62.imageY, r62.imageWidth, r62.imageHeight);
        return r4;
    L20:
        if (r3 == 0) goto L22;
        r52.setTranslationX(-r3);
    L22:
        return r4;
    }

    public void animateRemoveImpl(RecyclerView.ViewHolder r5, boolean r6) {
        if (BuildVars.LOGS_ENABLED == false) goto L8;
        if (r6 == false) goto L6;
        String r0 = " with thanos";
    L7:
        FileLog.d("animate remove impl ".concat(r0));
        goto L8
    L6:
        r0 = _UrlKt.FRAGMENT_ENCODE_SET;
    L8:
        View r02 = r5.itemView;
        this.mRemoveAnimations.add(r5);
        if (r6 == false) goto L13;
        Utilities.Callback0Return<ThanosEffect> r62 = this.getThanosEffectContainer;
        if (r62 == null) goto L13;
        ThanosEffect r63 = r62.run();
        dispatchRemoveStarting(r5);
        r63.animate(r02, new ChatListItemAnimator$$ExternalSyntheticLambda9(this, r02, r5));
        this.thanosViews.add(r02);
    L14:
        this.recyclerListView.stopScroll();
        return;
    L13:
        ObjectAnimator r64 = ObjectAnimator.ofFloat(r02, View.ALPHA, new float[]{r02.getAlpha(), 0.0f});
        dispatchRemoveStarting(r5);
        r64.setDuration(getRemoveDuration());
        r64.addListener(new AnonymousClass12(this, r02, r5));
        this.animators.put(r5, r64);
        r64.start();
        goto L14
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimation(RecyclerView.ViewHolder r3) {
        Animator r0 = this.animators.remove(r3);
        if (r0 == null) goto L6;
        r0.cancel();
    L6:
        if (this.thanosViews.contains(r3.itemView) == false) goto L10;
        ThanosEffect r02 = this.getThanosEffectContainer.run();
        if (r02 == null) goto L10;
        r02.cancel(r3.itemView);
    L10:
        super.endAnimation(r3);
        restoreTransitionParams(r3.itemView);
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void endAnimations() {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("end animations");
    L5:
        ArrayList<MessageObject.GroupedMessages> r0 = this.willChangedGroups;
        int r1 = r0.size();
        int r3 = 0;
    L6:
        if (r3 >= r1) goto L8;
        MessageObject.GroupedMessages r4 = r0.get(r3);
        r3 = r3 + 1;
        r4.transitionParams.isNewGroup = false;
        goto L6
    L8:
        this.willChangedGroups.clear();
        cancelAnimators();
        ChatGreetingsView r02 = this.chatGreetingsView;
        if (r02 == null) goto L11;
        r02.stickerToSendView.setAlpha(1.0f);
    L11:
        this.greetingsSticker = null;
        this.chatGreetingsView = null;
        int r03 = this.mPendingMoves.size() - 1;
    L12:
        if (r03 < 0) goto L14;
        DefaultItemAnimator.MoveInfo r12 = this.mPendingMoves.get(r03);
        restoreTransitionParams(r12.holder.itemView);
        dispatchMoveFinished(r12.holder);
        this.mPendingMoves.remove(r03);
        r03 = r03 - 1;
        goto L12
    L14:
        int r04 = this.mPendingRemovals.size() - 1;
    L15:
        if (r04 < 0) goto L17;
        RecyclerView.ViewHolder r13 = this.mPendingRemovals.get(r04);
        restoreTransitionParams(r13.itemView);
        dispatchRemoveFinished(r13);
        this.mPendingRemovals.remove(r04);
        r04 = r04 - 1;
        goto L15
    L17:
        int r05 = this.mPendingAdditions.size() - 1;
    L18:
        if (r05 < 0) goto L20;
        RecyclerView.ViewHolder r14 = this.mPendingAdditions.get(r05);
        restoreTransitionParams(r14.itemView);
        dispatchAddFinished(r14);
        this.mPendingAdditions.remove(r05);
        r05 = r05 - 1;
        goto L18
    L20:
        int r06 = this.mPendingChanges.size();
    L21:
        r06 = r06 - 1;
        ArrayList<DefaultItemAnimator.ChangeInfo> r15 = this.mPendingChanges;
        if (r06 < 0) goto L24;
        endChangeAnimationIfNecessary(r15.get(r06));
        goto L21
    L24:
        r15.clear();
        if (isRunning() == true) goto L27;
        return;
    L27:
        int r07 = this.mMovesList.size() - 1;
    L28:
        if (r07 < 0) goto L36;
        ArrayList<DefaultItemAnimator.MoveInfo> r16 = this.mMovesList.get(r07);
        int r2 = r16.size() - 1;
    L30:
        if (r2 < 0) goto L35;
        DefaultItemAnimator.MoveInfo r32 = r16.get(r2);
        restoreTransitionParams(r32.holder.itemView);
        dispatchMoveFinished(r32.holder);
        r16.remove(r2);
        if (r16.isEmpty() == false) goto L34;
        this.mMovesList.remove(r16);
    L34:
        r2 = r2 - 1;
        goto L30
    L35:
        r07 = r07 - 1;
        goto L28
    L36:
        int r08 = this.mAdditionsList.size() - 1;
    L37:
        if (r08 < 0) goto L45;
        ArrayList<RecyclerView.ViewHolder> r17 = this.mAdditionsList.get(r08);
        int r22 = r17.size() - 1;
    L39:
        if (r22 < 0) goto L44;
        RecyclerView.ViewHolder r33 = r17.get(r22);
        restoreTransitionParams(r33.itemView);
        dispatchAddFinished(r33);
        r17.remove(r22);
        if (r17.isEmpty() == false) goto L43;
        this.mAdditionsList.remove(r17);
    L43:
        r22 = r22 - 1;
        goto L39
    L44:
        r08 = r08 - 1;
        goto L37
    L45:
        int r09 = this.mChangesList.size() - 1;
    L46:
        if (r09 < 0) goto L54;
        ArrayList<DefaultItemAnimator.ChangeInfo> r18 = this.mChangesList.get(r09);
        int r23 = r18.size() - 1;
    L48:
        if (r23 < 0) goto L53;
        endChangeAnimationIfNecessary(r18.get(r23));
        if (r18.isEmpty() == false) goto L52;
        this.mChangesList.remove(r18);
    L52:
        r23 = r23 - 1;
        goto L48
    L53:
        r09 = r09 - 1;
        goto L46
    L54:
        cancelAll(this.mRemoveAnimations);
        cancelAll(this.mMoveAnimations);
        cancelAll(this.mAddAnimations);
        cancelAll(this.mChangeAnimations);
        dispatchAnimationsFinished();
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public boolean endChangeAnimationIfNecessary(DefaultItemAnimator.ChangeInfo r5, RecyclerView.ViewHolder r6) {
        if (BuildVars.LOGS_ENABLED == false) goto L5;
        FileLog.d("end change if necessary");
    L5:
        Animator r0 = this.animators.remove(r6);
        if (r0 == null) goto L9;
        r0.cancel();
    L9:
        if (this.thanosViews.contains(r6.itemView) == false) goto L13;
        ThanosEffect r02 = this.getThanosEffectContainer.run();
        if (r02 == null) goto L13;
        r02.cancel(r6.itemView);
    L13:
        boolean r3 = false;
        if (r5.newHolder != r6) goto L17;
        r5.newHolder = null;
    L19:
        restoreTransitionParams(r6.itemView);
        dispatchChangeFinished(r6, r3);
        return true;
    L17:
        if (r5.oldHolder != r6) goto L21;
        r5.oldHolder = null;
        r3 = true;
        goto L19
    L21:
        return false;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getChangeDuration() {
        return 250;
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public long getMoveAnimationDelay() {
        return 0;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public long getMoveDuration() {
        return 250;
    }

    public void groupWillChanged(MessageObject.GroupedMessages r7) {
        if (r7 != null) goto L4;
        return;
    L4:
        int r0 = r7.messages.size();
        MessageObject.GroupedMessages.TransitionParams r1 = r7.transitionParams;
        if (r0 != 0) goto L9;
        r1.drawBackgroundForDeletedItems = true;
        return;
    L9:
        if (r1.top == 0) goto L11;
    L26:
        this.willChangedGroups.add(r7);
        return;
    L11:
        if (r1.bottom != 0) goto L26;
        if (r1.left != 0) goto L26;
        if (r1.right != 0) goto L26;
        int r02 = this.recyclerListView.getChildCount();
        int r12 = 0;
    L17:
        if (r12 >= r02) goto L26;
        View r3 = this.recyclerListView.getChildAt(r12);
        if ((r3 instanceof ChatMessageCell) == false) goto L25;
        ChatMessageCell r32 = (ChatMessageCell) r3;
        MessageObject r4 = r32.getMessageObject();
        if (r32.getTransitionParams().wasDraw == false) goto L25;
        if (r7.messages.contains(r4) == false) goto L25;
        MessageObject.GroupedMessages.TransitionParams r03 = r7.transitionParams;
        r03.top = (r32.getTop() + r32.getPaddingTop()) + r32.getBackgroundDrawableTop();
        MessageObject.GroupedMessages.TransitionParams r04 = r7.transitionParams;
        r04.bottom = (r32.getTop() + r32.getPaddingTop()) + r32.getBackgroundDrawableBottom();
        MessageObject.GroupedMessages.TransitionParams r05 = r7.transitionParams;
        r05.left = r32.getLeft() + r32.getBackgroundDrawableLeft();
        MessageObject.GroupedMessages.TransitionParams r06 = r7.transitionParams;
        r06.right = r32.getLeft() + r32.getBackgroundDrawableRight();
        MessageObject.GroupedMessages.TransitionParams r07 = r7.transitionParams;
        r07.drawCaptionLayout = r32.hasCaptionLayout();
        MessageObject.GroupedMessages.TransitionParams r08 = r7.transitionParams;
        r08.pinnedTop = r32.isPinnedTop();
        MessageObject.GroupedMessages.TransitionParams r09 = r7.transitionParams;
        r09.pinnedBotton = r32.isPinnedBottom();
        r7.transitionParams.isNewGroup = true;
    L25:
        r12 = r12 + 1;
        goto L17
    }

    public void groupWillTransformToSingleMessage(MessageObject.GroupedMessages r3) {
        this.willRemovedGroup.put(Integer.valueOf(r3.messages.get(0).getId()), r3);
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator
    public void onAllAnimationsDone() {
        super.onAllAnimationsDone();
        this.recyclerListView.setClipChildren(true);
    L4:
        if (this.runOnAnimationsEnd.isEmpty() == true) goto L6;
        this.runOnAnimationsEnd.remove(0).run();
        goto L4
    L6:
        cancelAnimators();
    }

    public void onAnimationStart() {
    }

    public void onDestroy() {
        onAllAnimationsDone();
    }

    public void onGreetingStickerTransition(RecyclerView.ViewHolder r1, ChatGreetingsView r2) {
        this.greetingsSticker = r1;
        this.chatGreetingsView = r2;
        this.shouldAnimateEnterFromBottom = false;
    }

    public void prepareThanos(RecyclerView.ViewHolder r1) {
        if (r1 == null) goto L12;
        this.toBeSnapped.add(r1);
        View r0 = r1.itemView;
        if ((r0 instanceof ChatMessageCell) == false) goto L10;
        MessageObject r02 = ((ChatMessageCell) r0).getMessageObject();
        if (r02 == null) goto L11;
        r02.deletedByThanos = true;
        return;
    L11:
        return;
    L10:
        return;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public RecyclerView.ItemAnimator.ItemHolderInfo recordPreLayoutInformation(RecyclerView.State r1, RecyclerView.ViewHolder r2, int r3, List<Object> r4) {
        RecyclerView.ItemAnimator.ItemHolderInfo r12 = super.recordPreLayoutInformation(r1, r2, r3, r4);
        View r22 = r2.itemView;
        if ((r22 instanceof ChatMessageCell) == false) goto L6;
        ItemHolderInfoExtended r32 = new ItemHolderInfoExtended(this);
        r32.left = r12.left;
        r32.top = r12.top;
        r32.right = r12.right;
        r32.bottom = r12.bottom;
        ChatMessageCell.TransitionParams r0 = ((ChatMessageCell) r22).getTransitionParams();
        r32.imageX = r0.lastDrawingImageX;
        r32.imageY = r0.lastDrawingImageY;
        r32.imageWidth = r0.lastDrawingImageW;
        r32.imageHeight = r0.lastDrawingImageH;
        return r32;
    L6:
        return r12;
    }

    @Override // androidx.recyclerview.widget.DefaultItemAnimator, androidx.recyclerview.widget.RecyclerView.ItemAnimator
    public void runPendingAnimations() {
        boolean r0 = this.mPendingRemovals.isEmpty();
        boolean r1 = this.mPendingMoves.isEmpty();
        boolean r2 = this.mPendingChanges.isEmpty();
        boolean r3 = this.mPendingAdditions.isEmpty();
        if (r0 == false) goto L8;
        if (r1 == false) goto L8;
        if (r3 == false) goto L8;
        if (r2 == false) goto L8;
        return;
    L8:
        boolean r12 = false;
        if (this.shouldAnimateEnterFromBottom == false) goto L27;
        int r02 = 0;
        boolean r22 = false;
    L12:
        if (r02 >= this.mPendingAdditions.size()) goto L26;
        if (this.reversePositions == false) goto L23;
        if (this.recyclerListView.getAdapter() != null) goto L18;
        int r32 = 0;
    L20:
        if (this.mPendingAdditions.get(r02).getLayoutPosition() != (r32 - 1)) goto L25;
    L24:
        r22 = true;
    L25:
        r02 = r02 + 1;
        goto L12
    L18:
        r32 = this.recyclerListView.getAdapter().getItemCount();
        goto L20
    L23:
        if (this.mPendingAdditions.get(r02).getLayoutPosition() != 0) goto L25;
    L26:
        r12 = r22;
    L27:
        onAnimationStart();
        if (r12 == false) goto L30;
        runMessageEnterTransition();
    L31:
        ValueAnimator r03 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
        r03.addUpdateListener(new ChatListItemAnimator$$ExternalSyntheticLambda1(this));
        r03.setDuration(getRemoveDuration() + getMoveDuration());
        r03.start();
        return;
    L30:
        runAlphaEnterTransition();
        goto L31
    }

    public void setOnSnapMessage(Utilities.Callback0Return<Boolean> r1, Utilities.Callback0Return<ThanosEffect> r2) {
        this.supportsThanosEffectContainer = r1;
        this.getThanosEffectContainer = r2;
    }

    public void setReversePositions(boolean r1) {
        this.reversePositions = r1;
    }

    public void setShouldAnimateEnterFromBottom(boolean r1) {
        this.shouldAnimateEnterFromBottom = r1;
    }

    public boolean willAddedFromAlpha(View r3) {
        if (this.shouldAnimateEnterFromBottom == false) goto L5;
        return false;
    L5:
        RecyclerView.ViewHolder r32 = this.recyclerListView.getChildViewHolder(r3);
        if (r32 != null) goto L8;
        return false;
    L8:
        if (this.mPendingAdditions.contains(r32) == false) goto L10;
        return true;
    L10:
        if (this.mAddAnimations.contains(r32) == true) goto L16;
        return false;
    L16:
        return true;
    }

    public boolean willRemoved(View r3) {
        RecyclerView.ViewHolder r32 = this.recyclerListView.getChildViewHolder(r3);
        if (r32 != null) goto L5;
        return false;
    L5:
        if (this.mPendingRemovals.contains(r32) == false) goto L7;
        return true;
    L7:
        if (this.mRemoveAnimations.contains(r32) == true) goto L13;
        return false;
    L13:
        return true;
    }
}
