package org.telegram.ui.Components.Reactions;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.view.View;
import android.widget.FrameLayout;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.RecyclerView;
import com.exteragram.messenger.utils.chats.GlassMenuHelper;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.ChatMessageCell;
import org.telegram.ui.ChatActivity;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.FragmentContextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.Components.ReactionsContainerLayout;
import org.telegram.ui.Components.RecyclerListView;

/* loaded from: classes7.dex */
public class ChatSelectionReactionMenuOverlay extends FrameLayout {
    private float currentOffsetY;
    private MessageObject currentPrimaryObject;
    private boolean hiddenByScroll;
    private boolean isVisible;
    private long lastUpdate;
    private int mPadding;
    private int mSidePadding;
    private boolean messageSet;
    private ChatActivity parentFragment;
    private int[] pos;
    private ReactionsContainerLayout reactionsContainerLayout;
    private List<MessageObject> selectedMessages;
    private float toOffsetY;
    private float translationOffsetY;

    /* renamed from: org.telegram.ui.Components.Reactions.ChatSelectionReactionMenuOverlay$1, reason: invalid class name */
    public class AnonymousClass1 extends RecyclerView.OnScrollListener {
        final /* synthetic */ ChatSelectionReactionMenuOverlay this$0;

        public AnonymousClass1(ChatSelectionReactionMenuOverlay r1) {
            this.this$0 = r1;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
        public void onScrolled(RecyclerView r1, int r2, int r3) {
            this.this$0.invalidatePosition();
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatSelectionReactionMenuOverlay$2, reason: invalid class name */
    public class AnonymousClass2 extends ReactionsContainerLayout {
        float enabledAlpha;
        long lastUpdate;
        final /* synthetic */ ChatSelectionReactionMenuOverlay this$0;

        public AnonymousClass2(ChatSelectionReactionMenuOverlay r1, int r2, BaseFragment r3, Context r4, int r5, Theme.ResourcesProvider r6) {
            this.this$0 = r1;
            super(r2, r3, r4, r5, r6);
            this.enabledAlpha = 1.0f;
            setWillNotDraw(false);
        }

        @Override // android.view.View
        public void draw(Canvas r7) {
            long r0 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
            this.lastUpdate = System.currentTimeMillis();
            RectF r2 = AndroidUtilities.rectTmp;
            r2.set(0.0f, 0.0f, getWidth(), getHeight());
            r7.saveLayerAlpha(r2, (int) (this.enabledAlpha * 255.0f), 31);
            super.draw(r7);
            r7.restore();
            if (isEnabled() == true) goto L11;
            float r72 = this.enabledAlpha;
            if (r72 == 0.0f) goto L11;
            this.enabledAlpha = Math.max(0.0f, r72 - (r0 / 150.0f));
            invalidate();
            if (this.enabledAlpha != 0.0f) goto L16;
            setVisibility(8);
            return;
        L16:
            return;
        L11:
            if (isEnabled() == false) goto L17;
            float r73 = this.enabledAlpha;
            if (r73 == 1.0f) goto L18;
            this.enabledAlpha = Math.min(1.0f, r73 + (r0 / 150.0f));
            invalidate();
            return;
        L18:
            return;
        }

        @Override // android.view.View
        public void setVisibility(int r2) {
            super.setVisibility(r2);
            if (r2 == 8) goto L5;
            return;
        L5:
            if (this.enabledAlpha == 0.0f) goto L9;
            this.enabledAlpha = 0.0f;
            return;
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatSelectionReactionMenuOverlay$3, reason: invalid class name */
    public class AnonymousClass3 implements ReactionsContainerLayout.ReactionsContainerDelegate {
        final /* synthetic */ ChatSelectionReactionMenuOverlay this$0;

        public static /* synthetic */ void $r8$lambda$9okCVx4XQRkNbF5UxMR1d7kdfws(AnonymousClass3 r0) {
            r0.lambda$onReactionClicked$0();
        }

        public AnonymousClass3(ChatSelectionReactionMenuOverlay r1) {
            this.this$0 = r1;
        }

        private /* synthetic */ void lambda$onReactionClicked$0() {
            if (ChatSelectionReactionMenuOverlay.m12335$$Nest$fgetreactionsContainerLayout(this.this$0) == null) goto L5;
            ChatSelectionReactionMenuOverlay.m12335$$Nest$fgetreactionsContainerLayout(this.this$0).dismissParent(true);
        L5:
            hideMenu();
        }

        public void hideMenu() {
            ChatSelectionReactionMenuOverlay.m12334$$Nest$fgetparentFragment(this.this$0).clearSelectionMode(true);
        }

        @Override // org.telegram.ui.Components.ReactionsContainerLayout.ReactionsContainerDelegate
        public void onReactionClicked(View r14, ReactionsLayoutInBubble.VisibleReaction r15, boolean r16, boolean r17) {
            ChatSelectionReactionMenuOverlay.m12334$$Nest$fgetparentFragment(this.this$0).selectReaction(null, ChatSelectionReactionMenuOverlay.m12333$$Nest$fgetcurrentPrimaryObject(this.this$0), ChatSelectionReactionMenuOverlay.m12335$$Nest$fgetreactionsContainerLayout(this.this$0), r14, 0.0f, 0.0f, r15, false, r16, r17, false);
            AndroidUtilities.runOnUIThread(new ChatSelectionReactionMenuOverlay$3$$ExternalSyntheticLambda0(this));
        }
    }

    /* renamed from: org.telegram.ui.Components.Reactions.ChatSelectionReactionMenuOverlay$4, reason: invalid class name */
    public class AnonymousClass4 extends AnimatorListenerAdapter {
        final /* synthetic */ ChatSelectionReactionMenuOverlay this$0;

        public AnonymousClass4(ChatSelectionReactionMenuOverlay r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r3) {
            this.this$0.setVisibility(8);
            if (ChatSelectionReactionMenuOverlay.m12335$$Nest$fgetreactionsContainerLayout(this.this$0) == null) goto L5;
            ChatSelectionReactionMenuOverlay r32 = this.this$0;
            r32.removeView(ChatSelectionReactionMenuOverlay.m12335$$Nest$fgetreactionsContainerLayout(r32));
            ChatSelectionReactionMenuOverlay.m12337$$Nest$fputreactionsContainerLayout(this.this$0, null);
        L5:
            ChatSelectionReactionMenuOverlay.m12336$$Nest$fputcurrentPrimaryObject(this.this$0, null);
        }
    }

    public static /* synthetic */ void $r8$lambda$PcyeAYsXxAGEWHtZ5Ofi7XI6n64(ChatSelectionReactionMenuOverlay r0) {
        r0.lambda$animateVisible$0();
    }

    public static /* synthetic */ void $r8$lambda$oUM9jiYLeGVlVJLYlyKgbjWVZlU(ChatSelectionReactionMenuOverlay r0, ValueAnimator r1) {
        r0.lambda$animateVisible$1(r1);
    }

    /* renamed from: -$$Nest$fgetcurrentPrimaryObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ MessageObject m12333$$Nest$fgetcurrentPrimaryObject(ChatSelectionReactionMenuOverlay r0) {
        return r0.currentPrimaryObject;
    }

    /* renamed from: -$$Nest$fgetparentFragment, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ChatActivity m12334$$Nest$fgetparentFragment(ChatSelectionReactionMenuOverlay r0) {
        return r0.parentFragment;
    }

    /* renamed from: -$$Nest$fgetreactionsContainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ReactionsContainerLayout m12335$$Nest$fgetreactionsContainerLayout(ChatSelectionReactionMenuOverlay r0) {
        return r0.reactionsContainerLayout;
    }

    /* renamed from: -$$Nest$fputcurrentPrimaryObject, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12336$$Nest$fputcurrentPrimaryObject(ChatSelectionReactionMenuOverlay r0, MessageObject r1) {
        r0.currentPrimaryObject = r1;
    }

    /* renamed from: -$$Nest$fputreactionsContainerLayout, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m12337$$Nest$fputreactionsContainerLayout(ChatSelectionReactionMenuOverlay r0, ReactionsContainerLayout r1) {
        r0.reactionsContainerLayout = r1;
    }

    public ChatSelectionReactionMenuOverlay(ChatActivity r1, Context r2) {
        super(r2);
        this.selectedMessages = Collections.EMPTY_LIST;
        this.mPadding = 22;
        this.mSidePadding = 24;
        this.pos = new int[2];
        setVisibility(8);
        this.parentFragment = r1;
        setClipToPadding(false);
        setClipChildren(false);
        r1.getChatListView().addOnScrollListener(new AnonymousClass1(this));
    }

    private void animateVisible(boolean r3) {
        if (r3 == false) goto L6;
        setVisibility(0);
        post(new ChatSelectionReactionMenuOverlay$$ExternalSyntheticLambda1(this));
        return;
    L6:
        this.messageSet = false;
        ValueAnimator r32 = ValueAnimator.ofFloat(new float[]{1.0f, 0.0f}).setDuration(150);
        r32.addUpdateListener(new ChatSelectionReactionMenuOverlay$$ExternalSyntheticLambda2(this));
        r32.addListener(new AnonymousClass4(this));
        r32.start();
    }

    private void checkCreateReactionsLayout() {
        if (this.reactionsContainerLayout == null) goto L5;
        return;
    L5:
        if (this.parentFragment.getUserConfig().getClientUserId() != this.parentFragment.getDialogId()) goto L7;
        boolean r0 = true;
    L9:
        if (r0 == false) goto L11;
        int r4 = 3;
    L12:
        AnonymousClass2 r2 = new AnonymousClass2(this, r4, this.parentFragment, getContext(), this.parentFragment.getCurrentAccount(), this.parentFragment.getResourceProvider());
        this.reactionsContainerLayout = r2;
        int r02 = AndroidUtilities.dp(4.0f);
        if (LocaleController.isRTL == false) goto L15;
        int r42 = 0;
    L16:
        int r03 = r02 + r42;
        int r43 = AndroidUtilities.dp(4.0f);
        int r9 = AndroidUtilities.dp(4.0f);
        if (LocaleController.isRTL == false) goto L19;
        int r5 = this.mSidePadding;
    L20:
        r2.setPadding(r03, r43, r9 + r5, AndroidUtilities.dp(this.mPadding));
        this.reactionsContainerLayout.setDelegate(new AnonymousClass3(this));
        this.reactionsContainerLayout.setClipChildren(false);
        this.reactionsContainerLayout.setClipToPadding(false);
        addView(this.reactionsContainerLayout, LayoutHelper.createFrame(-2, this.mPadding + 70, 5));
        if (GlassMenuHelper.isEnabled(this.parentFragment.getCurrentAccount(), this.parentFragment.getResourceProvider()) == false) goto L25;
        GlassMenuHelper.applyToReactions(this.parentFragment.getGlassBackgroundDrawableFactory(), this.parentFragment.getResourceProvider(), this.reactionsContainerLayout);
        return;
    L25:
        return;
    L19:
        r5 = 0;
        goto L20
    L15:
        r42 = this.mSidePadding;
        goto L16
    L11:
        r4 = 0;
        goto L12
    L7:
        r0 = false;
        goto L9
    }

    private MessageObject findPrimaryObject() {
        if (this.isVisible == true) goto L5;
        return null;
    L5:
        if (this.selectedMessages.isEmpty() == true) goto L36;
        int r1 = 0;
        MessageObject r0 = this.selectedMessages.get(0);
        if (r0.getGroupId() == 0) goto L23;
        MessageObject.GroupedMessages r6 = this.parentFragment.getGroup(r0.getGroupId());
        if (r6 == null) goto L23;
        ArrayList<MessageObject> r62 = r6.messages;
        if (r62 == null) goto L23;
        int r2 = r62.size();
    L13:
        if (r1 >= r2) goto L23;
        MessageObject r3 = r62.get(r1);
        r1 = r1 + 1;
        MessageObject r32 = r3;
        TLRPC.Message r4 = r32.messageOwner;
        if (r4 == null) goto L13;
        TLRPC.TL_messageReactions r42 = r4.reactions;
        if (r42 == null) goto L13;
        ArrayList<TLRPC.ReactionCount> r43 = r42.results;
        if (r43 == null) goto L13;
        if (r43.isEmpty() == true) goto L13;
        return r32;
    L23:
        return r0;
    L36:
        return null;
    }

    private boolean isMessageTypeAllowed(MessageObject r2) {
        if (r2 == null) goto L9;
        TLRPC.Message r0 = r2.messageOwner;
        if (r0 == null) goto L9;
        if (r0.rich_message == null) goto L9;
        return true;
    L9:
        if (r2 != null) goto L11;
        return false;
    L11:
        if (r2.needDrawBluredPreview() == false) goto L13;
        return false;
    L13:
        if (MessageObject.isPhoto(r2.messageOwner) == false) goto L19;
        if (MessageObject.getMedia(r2.messageOwner) == null) goto L19;
        if (MessageObject.getMedia(r2.messageOwner).webpage != null) goto L19;
    L24:
        return true;
    L19:
        if (r2.getDocument() != null) goto L21;
        return false;
    L21:
        if (MessageObject.isVideoDocument(r2.getDocument()) == true) goto L24;
        if (MessageObject.isGifDocument(r2.getDocument()) == true) goto L24;
        return false;
    }

    private /* synthetic */ void lambda$animateVisible$0() {
        this.currentPrimaryObject = findPrimaryObject();
        checkCreateReactionsLayout();
        invalidatePosition(false);
        if (this.reactionsContainerLayout.isEnabled() == false) goto L6;
        this.messageSet = true;
        this.reactionsContainerLayout.setMessage(this.currentPrimaryObject, this.parentFragment.getCurrentChatInfo(), true);
        this.reactionsContainerLayout.startEnterAnimation(false);
        return;
    L6:
        this.messageSet = false;
        this.reactionsContainerLayout.setTransitionProgress(1.0f);
    }

    private /* synthetic */ void lambda$animateVisible$1(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 == null) goto L6;
        r0.setAlpha(r12);
        return;
    }

    public void invalidatePosition() {
        invalidatePosition(true);
    }

    public void invalidatePosition(boolean r12) {
        if (this.isVisible == true) goto L5;
        return;
    L5:
        if (this.currentPrimaryObject != null) goto L7;
        return;
    L7:
        if (this.reactionsContainerLayout == null) goto L118;
        long r0 = Math.min(16, System.currentTimeMillis() - this.lastUpdate);
        this.lastUpdate = System.currentTimeMillis();
        float r2 = this.currentOffsetY;
        float r3 = this.toOffsetY;
        if (r2 == r3) goto L18;
        float r02 = r0 / 220.0f;
        if (r3 <= r2) goto L15;
        this.currentOffsetY = Math.min(r2 + r02, r3);
    L17:
        AndroidUtilities.runOnUIThread(new ChatSelectionReactionMenuOverlay$$ExternalSyntheticLambda0(this));
        goto L18
    L15:
        if (r3 >= r2) goto L17;
        this.currentOffsetY = Math.max(r2 - r02, r3);
    L18:
        RecyclerListView r03 = this.parentFragment.getChatListView();
        r03.getLocationInWindow(this.pos);
        int[] r1 = this.pos;
        boolean r22 = true;
        float r32 = r1[1];
        getLocationInWindow(r1);
        float r33 = (r32 - this.pos[1]) - this.parentFragment.getPullingDownOffset();
        boolean r13 = false;
        int r4 = 0;
    L20:
        if (r4 >= r03.getChildCount()) goto L103;
        View r5 = r03.getChildAt(r4);
        if ((r5 instanceof ChatMessageCell) == false) goto L102;
        ChatMessageCell r52 = (ChatMessageCell) r5;
        MessageObject r6 = r52.getMessageObject();
        if (r6.getId() != this.currentPrimaryObject.getId()) goto L102;
        boolean r42 = r6.isOutOwner();
        ReactionsContainerLayout r62 = this.reactionsContainerLayout;
        if (r62 == null) goto L41;
        r62.setMirrorX(r42);
        ReactionsContainerLayout r63 = this.reactionsContainerLayout;
        int r8 = AndroidUtilities.dp(4.0f);
        if (LocaleController.isRTL == true) goto L32;
        if (r42 == true) goto L32;
        int r9 = this.mSidePadding;
    L33:
        int r82 = r8 + r9;
        int r92 = AndroidUtilities.dp(this.mPadding);
        int r7 = AndroidUtilities.dp(4.0f);
        if (LocaleController.isRTL == true) goto L38;
        if (r42 == true) goto L38;
        int r10 = 0;
    L39:
        r63.setPadding(r82, r92, r7 + r10, AndroidUtilities.dp(this.mPadding));
    L38:
        r10 = this.mSidePadding;
    L32:
        r9 = 0;
    L41:
        if (getHeight() == 0) goto L43;
        int r04 = getHeight();
    L45:
        if (r52.getCurrentMessagesGroup() == null) goto L47;
        MessageObject.GroupedMessages.TransitionParams r64 = r52.getCurrentMessagesGroup().transitionParams;
        int r72 = r64.bottom - r64.top;
    L48:
        float r65 = (r52.getY() + r33) - AndroidUtilities.dp(74.0f);
        float r34 = AndroidUtilities.dp(14.0f);
        float r05 = r04 - AndroidUtilities.dp(218.0f);
        FragmentContextView r83 = this.parentFragment.getFragmentContextView();
        if (r83 != null) goto L51;
    L53:
        float r84 = r72;
        if (r65 <= (r34 - (r84 / 2.0f))) goto L59;
        if (r65 >= r05) goto L59;
        this.toOffsetY = 0.0f;
        boolean r73 = false;
        boolean r85 = true;
    L66:
        if (r12 == true) goto L68;
        this.currentOffsetY = this.toOffsetY;
    L68:
        float r66 = r65 + (CubicBezierInterpolator.DEFAULT.getInterpolation(this.currentOffsetY) * this.translationOffsetY);
        ReactionsContainerLayout r122 = this.reactionsContainerLayout;
        if (r122 != null) goto L72;
        return;
    L72:
        if (r73 == r122.isFlippedVertically()) goto L75;
        this.reactionsContainerLayout.setFlippedVertically(r73);
        AndroidUtilities.runOnUIThread(new ChatSelectionReactionMenuOverlay$$ExternalSyntheticLambda0(this));
    L75:
        if (r85 == this.reactionsContainerLayout.isEnabled()) goto L81;
        this.reactionsContainerLayout.setEnabled(r85);
        this.reactionsContainerLayout.invalidate();
        if (r85 == false) goto L81;
        this.reactionsContainerLayout.setVisibility(0);
        if (this.messageSet == true) goto L81;
        this.messageSet = true;
        this.reactionsContainerLayout.setMessage(this.currentPrimaryObject, this.parentFragment.getCurrentChatInfo(), true);
    L81:
        this.reactionsContainerLayout.setTranslationY(MathUtils.clamp(r66, r34, r05));
        this.reactionsContainerLayout.setTranslationX(r52.getNonAnimationTranslationX(true));
        FrameLayout.LayoutParams r123 = (FrameLayout.LayoutParams) this.reactionsContainerLayout.getLayoutParams();
        int r06 = Math.max(0, r52.getBackgroundDrawableLeft() - AndroidUtilities.dp(32.0f));
        int r35 = Math.max((int) r52.getNonAnimationTranslationX(true), (r52.getWidth() - r52.getBackgroundDrawableRight()) - AndroidUtilities.dp(32.0f));
        int r53 = AndroidUtilities.dp(40.0f) * 8;
        if (((getWidth() - r35) - r06) >= r53) goto L86;
        if (r42 == false) goto L85;
        r06 = Math.min(r06, getWidth() - r53);
        r35 = 0;
        goto L86
    L85:
        r35 = Math.min(r35, getWidth() - r53);
        r06 = 0;
    L86:
        if (r42 == false) goto L88;
        int r43 = 5;
    L90:
        if (r43 == r123.gravity) goto L93;
        r123.gravity = r43;
        r13 = true;
    L93:
        if (r06 == r123.leftMargin) goto L96;
        r123.leftMargin = r06;
        r13 = true;
    L96:
        if (r35 == r123.rightMargin) goto L98;
        r123.rightMargin = r35;
    L99:
        if (r22 == false) goto L115;
        this.reactionsContainerLayout.requestLayout();
        return;
    L115:
        return;
    L98:
        r22 = r13;
        goto L99
    L88:
        r43 = 3;
    L59:
        if (r65 >= ((r34 - r84) - AndroidUtilities.dp(92.0f))) goto L61;
    L65:
        r73 = false;
    L64:
        r85 = r73;
        goto L66
    L61:
        if (r65 > r05) goto L65;
        this.translationOffsetY = r72 + AndroidUtilities.dp(56.0f);
        this.toOffsetY = 1.0f;
        r73 = true;
        goto L64
    L51:
        if (r83.getVisibility() != 0) goto L53;
        r34 = r34 + r83.getHeight();
        goto L53
    L47:
        r72 = r52.getHeight();
        goto L48
    L43:
        r04 = r03.getHeight();
    L102:
        r4 = r4 + 1;
        goto L20
    L103:
        ReactionsContainerLayout r124 = this.reactionsContainerLayout;
        if (r124 != null) goto L106;
        return;
    L106:
        if (r124.isEnabled() == false) goto L117;
        this.reactionsContainerLayout.setEnabled(false);
        return;
    L117:
        return;
    }

    public boolean isVisible() {
        if (this.isVisible == true) goto L5;
        return false;
    L5:
        if (this.hiddenByScroll == true) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean onBackPressed(boolean r2) {
        ReactionsContainerLayout r0 = this.reactionsContainerLayout;
        if (r0 != null) goto L5;
        return true;
    L5:
        if (r0.getReactionsWindow() == null) goto L13;
        if (r2 == false) goto L12;
        this.reactionsContainerLayout.dismissWindow();
        return false;
    L12:
        return false;
    L13:
        return true;
    }

    public void setHiddenByScroll(boolean r1) {
        this.hiddenByScroll = r1;
        if (r1 == false) goto L6;
        animateVisible(false);
        return;
    }

    public void setSelectedMessages(List<MessageObject> r10) {
        this.selectedMessages = r10;
        boolean r2 = true;
        if (this.parentFragment.getChatMode() != 1) goto L5;
    L24:
        r2 = false;
    L33:
        if (r2 == this.isVisible) goto L36;
        this.isVisible = r2;
        this.hiddenByScroll = false;
        animateVisible(r2);
        return;
    L36:
        if (r2 == false) goto L48;
        this.currentPrimaryObject = findPrimaryObject();
        return;
    L48:
        return;
    L5:
        if (this.parentFragment.getChatMode() == 5) goto L24;
        if (this.parentFragment.getChatMode() == 6) goto L24;
        if (this.parentFragment.isReport() == true) goto L24;
        if (this.parentFragment.isSecretChat() == true) goto L24;
        if (this.parentFragment.getCurrentChatInfo() == null) goto L18;
        if ((this.parentFragment.getCurrentChatInfo().available_reactions instanceof TLRPC.TL_chatReactionsNone) == true) goto L24;
    L18:
        if (r10.isEmpty() == true) goto L24;
        Iterator<MessageObject> r102 = r10.iterator();
        boolean r0 = false;
        long r5 = 0;
    L21:
        if (r102.hasNext() == false) goto L33;
        MessageObject r7 = r102.next();
        if (isMessageTypeAllowed(r7) == false) goto L24;
        if (r0 == false) goto L26;
        if (r5 != r7.getGroupId()) goto L24;
        if (r5 != 0) goto L21;
    L26:
        r5 = r7.getGroupId();
        r0 = true;
        goto L21
    }
}
