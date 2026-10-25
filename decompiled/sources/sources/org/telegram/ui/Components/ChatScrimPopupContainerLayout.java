package org.telegram.ui.Components;

import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;

/* loaded from: classes7.dex */
public abstract class ChatScrimPopupContainerLayout extends LinearLayout {
    private float bottomViewReactionsOffset;
    private float bottomViewYOffset;
    private final List<View> bottomViews;
    private float currentPopupAlpha;
    private float expandSize;
    private float lastReactionsTransitionProgress;
    private int maxHeight;
    private float popupLayoutLeftOffset;
    private ActionBarPopupWindow.ActionBarPopupWindowLayout popupWindowLayout;
    private float progressToSwipeBack;
    private ReactionsContainerLayout reactionsLayout;

    public static /* synthetic */ void $r8$lambda$N293OZyCj3eZs4MubisX7yv4Nks(ChatScrimPopupContainerLayout r0) {
        r0.updateBottomOffset();
    }

    public static /* synthetic */ void $r8$lambda$wXR2BnLMc0Q9EWGnyhLRhGYX6U8(ChatScrimPopupContainerLayout r0, PopupSwipeBackLayout r1, float r2, float r3) {
        r0.lambda$setPopupWindowLayout$0(r1, r2, r3);
    }

    public ChatScrimPopupContainerLayout(Context r1) {
        super(r1);
        this.bottomViews = new ArrayList();
        this.lastReactionsTransitionProgress = 0.0f;
        this.currentPopupAlpha = 1.0f;
        setOrientation(1);
    }

    private /* synthetic */ void lambda$setPopupWindowLayout$0(PopupSwipeBackLayout r5, float r6, float r7) {
        ReactionsContainerLayout r52 = this.reactionsLayout;
        if (r52 != null) goto L5;
    L9:
        float r53 = 1.0f;
    L10:
        Iterator<View> r0 = this.bottomViews.iterator();
    L12:
        if (r0.hasNext() == false) goto L16;
        View r1 = r0.next();
        if (r1 == null) goto L12;
        r1.setAlpha(((1.0f - r7) * r53) * this.currentPopupAlpha);
        goto L12
    L16:
        this.progressToSwipeBack = r7;
        updatePopupTranslation();
        return;
    L5:
        if (r52.getItemsCount() <= 0) goto L9;
        if (this.reactionsLayout.getVisibility() != 0) goto L9;
        r53 = this.lastReactionsTransitionProgress;
        goto L10
    }

    private void updateBottomOffset() {
        this.bottomViewYOffset = this.popupWindowLayout.getVisibleHeight() - this.popupWindowLayout.getMeasuredHeight();
        updateBottomViewPosition();
    }

    private void updateBottomViewPosition() {
        ReactionsContainerLayout r0 = this.reactionsLayout;
        if (r0 != null) goto L5;
    L9:
        float r02 = 1.0f;
    L10:
        Iterator<View> r2 = this.bottomViews.iterator();
    L12:
        if (r2.hasNext() == false) goto L28;
        View r3 = r2.next();
        if (r3 == null) goto L12;
        if (r02 < 1.0f) goto L18;
    L20:
        this.bottomViewReactionsOffset = 0.0f;
    L21:
        if (r02 >= 1.0f) goto L23;
        float r4 = r02 * 1.0f;
    L24:
        float r6 = this.progressToSwipeBack;
        if (r6 <= 0.0f) goto L27;
        r4 = r4 * (1.0f - r6);
    L27:
        r3.setAlpha(r4 * this.currentPopupAlpha);
        r3.setTranslationY((this.bottomViewYOffset + this.expandSize) + this.bottomViewReactionsOffset);
        goto L12
    L23:
        r4 = 1.0f;
        goto L24
    L18:
        if (r3.getMeasuredHeight() <= 0) goto L20;
        this.bottomViewReactionsOffset = (-r3.getMeasuredHeight()) * (1.0f - r02);
        goto L21
    L28:
        return;
    L5:
        if (r0.getItemsCount() <= 0) goto L9;
        if (this.reactionsLayout.getVisibility() != 0) goto L9;
        r02 = this.lastReactionsTransitionProgress;
        goto L10
    }

    private void updatePopupTranslation() {
        float r0 = (1.0f - this.progressToSwipeBack) * this.popupLayoutLeftOffset;
        this.popupWindowLayout.setTranslationX(r0);
        ReactionsContainerLayout r2 = this.reactionsLayout;
        if (r2 != null) goto L5;
    L9:
        float r22 = 1.0f;
    L10:
        Iterator<View> r3 = this.bottomViews.iterator();
    L12:
        if (r3.hasNext() == false) goto L16;
        View r4 = r3.next();
        if (r4 == null) goto L12;
        r4.setTranslationX(r0);
        r4.setAlpha(((1.0f - this.progressToSwipeBack) * r22) * this.currentPopupAlpha);
        goto L12
    L16:
        return;
    L5:
        if (r2.getItemsCount() <= 0) goto L9;
        if (this.reactionsLayout.getVisibility() != 0) goto L9;
        r22 = this.lastReactionsTransitionProgress;
        goto L10
    }

    public void applyViewBottom(FrameLayout r2) {
        if (r2 == null) goto L5;
        this.bottomViews.add(r2);
        updateBottomOffset();
        return;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateBottomViewPosition();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r19, int r20) {
        int r1 = this.maxHeight;
        if (r1 == 0) goto L6;
        int r12 = View.MeasureSpec.makeMeasureSpec(r1, Integer.MIN_VALUE);
    L5:
        int r2 = r19;
        super.onMeasure(r2, r12);
        if (this.popupWindowLayout == null) goto L138;
        ReactionsContainerLayout r3 = this.reactionsLayout;
        int r5 = 0;
        if (r3 == null) goto L12;
        r3.getLayoutParams().width = -2;
        ((LinearLayout.LayoutParams) this.reactionsLayout.getLayoutParams()).rightMargin = 0;
    L12:
        ReactionsContainerLayout r32 = this.reactionsLayout;
        if (r32 == null) goto L15;
        int r33 = r32.getMeasuredWidth();
    L17:
        if (this.popupWindowLayout.getSwipeBack() == null) goto L22;
        if (this.popupWindowLayout.getSwipeBack().getMeasuredWidth() <= r33) goto L22;
        r33 = this.popupWindowLayout.getSwipeBack().getMeasuredWidth();
    L22:
        if (this.popupWindowLayout.getMeasuredWidth() <= r33) goto L24;
        r33 = this.popupWindowLayout.getMeasuredWidth();
    L24:
        ReactionsContainerLayout r6 = this.reactionsLayout;
        if (r6 != null) goto L27;
    L29:
        ReactionsContainerLayout r62 = this.reactionsLayout;
        if (r62 == null) goto L87;
        r62.measureHint();
        int r63 = this.reactionsLayout.getTotalWidth();
        PopupSwipeBackLayout r11 = this.popupWindowLayout.getSwipeBack();
        ActionBarPopupWindow.ActionBarPopupWindowLayout r122 = this.popupWindowLayout;
        if (r11 == null) goto L34;
        View r112 = r122.getSwipeBack().getChildAt(0);
    L35:
        int r123 = ((r112.getMeasuredWidth() + AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(16.0f)) + AndroidUtilities.dp(36.0f);
        int r13 = this.reactionsLayout.getHintTextWidth();
        if (r13 <= r123) goto L38;
        r123 = r13;
    L40:
        this.reactionsLayout.bigCircleOffset = AndroidUtilities.dp(36.0f);
        if (this.reactionsLayout.showCustomEmojiReaction() == true) goto L43;
        if (r63 <= r123) goto L59;
        int r124 = ((r123 - AndroidUtilities.dp(16.0f)) / AndroidUtilities.dp(36.0f)) + 1;
        int r14 = (AndroidUtilities.dp(36.0f) * r124) + AndroidUtilities.dp(8.0f);
        float r192 = 16.0f;
        if ((r13 + AndroidUtilities.dp(24.0f)) <= r14) goto L51;
        r14 = r13 + AndroidUtilities.dp(24.0f);
    L51:
        if (r14 > r63) goto L57;
        if (r124 == this.reactionsLayout.getItemsCount()) goto L57;
        r63 = r14;
    L57:
        if (this.reactionsLayout.getLayoutParams().width == r63) goto L63;
        this.reactionsLayout.getLayoutParams().width = r63;
    L62:
        boolean r125 = true;
    L65:
        if (this.reactionsLayout.getMeasuredWidth() != r33) goto L73;
        if (this.reactionsLayout.showCustomEmojiReaction() == false) goto L73;
        float r34 = (r33 - r112.getMeasuredWidth()) * 0.25f;
        this.popupLayoutLeftOffset = r34;
        ReactionsContainerLayout r4 = this.reactionsLayout;
        int r64 = r4.bigCircleOffset - ((int) r34);
        r4.bigCircleOffset = r64;
        if (r64 >= AndroidUtilities.dp(36.0f)) goto L88;
        this.popupLayoutLeftOffset = 0.0f;
        this.reactionsLayout.bigCircleOffset = AndroidUtilities.dp(36.0f);
    L88:
        PopupSwipeBackLayout r35 = this.popupWindowLayout.getSwipeBack();
        ActionBarPopupWindow.ActionBarPopupWindowLayout r42 = this.popupWindowLayout;
        if (r35 == null) goto L91;
        View r36 = r42.getSwipeBack().getChildAt(0);
    L92:
        int r37 = r36.getMeasuredWidth();
        int r43 = this.popupWindowLayout.getMeasuredWidth();
        if (this.popupWindowLayout.getSwipeBack() == null) goto L95;
        int r65 = this.popupWindowLayout.getSwipeBack().getMeasuredWidth() - r37;
    L96:
        if (r65 < 0) goto L99;
        r5 = r65;
    L99:
        Iterator<View> r66 = this.bottomViews.iterator();
    L101:
        if (r66.hasNext() == false) goto L127;
        View r8 = r66.next();
        if (r8 == null) goto L101;
        LinearLayout.LayoutParams r113 = (LinearLayout.LayoutParams) r8.getLayoutParams();
        ReactionsContainerLayout r132 = this.reactionsLayout;
        if (r132 == null) goto L109;
        if (r132.showCustomEmojiReaction() == false) goto L109;
    L110:
        int r133 = AndroidUtilities.dp(r192) + r37;
        if (r43 <= 0) goto L116;
        if (r133 <= r43) goto L116;
        r133 = r43;
    L116:
        if (this.popupWindowLayout.getSwipeBack() == null) goto L118;
        int r142 = AndroidUtilities.dp(36.0f) + r5;
    L120:
        if (r113.width == r133) goto L122;
    L123:
        r113.width = r133;
        r113.rightMargin = r142;
        r125 = true;
    L124:
        float r114 = this.progressToSwipeBack;
        if (r114 <= 0.0f) goto L101;
        r8.setAlpha(1.0f - r114);
        goto L101
    L122:
        if (r113.rightMargin == r142) goto L124;
    L118:
        r142 = AndroidUtilities.dp(36.0f);
    L109:
        if (r8.getTag(R.id.fit_width_tag) != null) goto L110;
        r133 = -1;
        goto L116
    L127:
        updatePopupTranslation();
        if (r125 == false) goto L139;
        super.onMeasure(r2, r12);
        return;
    L139:
        return;
    L95:
        r65 = 0;
        goto L96
    L91:
        r36 = r42.getChildAt(0);
    L73:
        if (this.popupWindowLayout.getSwipeBack() == null) goto L75;
        int r67 = this.popupWindowLayout.getSwipeBack().getMeasuredWidth() - this.popupWindowLayout.getSwipeBack().getChildAt(0).getMeasuredWidth();
    L77:
        if (this.reactionsLayout.getLayoutParams().width != (-2)) goto L79;
    L81:
        if (r67 >= 0) goto L84;
        r67 = 0;
    L84:
        if (((LinearLayout.LayoutParams) this.reactionsLayout.getLayoutParams()).rightMargin == r67) goto L86;
        ((LinearLayout.LayoutParams) this.reactionsLayout.getLayoutParams()).rightMargin = r67;
        r125 = true;
    L86:
        this.popupLayoutLeftOffset = 0.0f;
        goto L88
    L79:
        if ((this.reactionsLayout.getLayoutParams().width + r67) <= r33) goto L81;
        r67 = (r33 - this.reactionsLayout.getLayoutParams().width) + AndroidUtilities.dp(8.0f);
        goto L81
    L75:
        r67 = 0;
    L63:
        r125 = false;
        goto L65
    L59:
        r192 = 16.0f;
        if (this.reactionsLayout.getLayoutParams().width == (-2)) goto L63;
        this.reactionsLayout.getLayoutParams().width = -2;
        goto L62
    L43:
        if (this.reactionsLayout.getLayoutParams().width == r63) goto L45;
        this.reactionsLayout.getLayoutParams().width = r63;
        r125 = true;
    L46:
        this.reactionsLayout.bigCircleOffset = Math.max((r63 - r112.getMeasuredWidth()) - AndroidUtilities.dp(36.0f), AndroidUtilities.dp(36.0f));
        r192 = 16.0f;
        goto L65
    L45:
        r125 = false;
        goto L46
    L38:
        if (r123 <= r33) goto L40;
        r123 = r33;
        goto L40
    L34:
        r112 = r122.getChildAt(0);
        goto L35
    L87:
        r192 = 16.0f;
        r125 = false;
        goto L88
    L27:
        if (r6.showCustomEmojiReaction() == false) goto L29;
        r2 = View.MeasureSpec.makeMeasureSpec(r33, TLObject.FLAG_30);
        goto L29
    L15:
        r33 = 0;
        goto L17
    L138:
        return;
    L6:
        r12 = r20;
        goto L5
    }

    public void setExpandSize(float r2) {
        this.popupWindowLayout.setTranslationY(r2);
        this.expandSize = r2;
        updateBottomViewPosition();
    }

    public void setMaxHeight(int r1) {
        this.maxHeight = r1;
    }

    public void setPopupAlpha(float r2) {
        this.currentPopupAlpha = r2;
        this.popupWindowLayout.setAlpha(r2);
        Iterator<View> r1 = this.bottomViews.iterator();
    L4:
        if (r1.hasNext() == false) goto L8;
        View r0 = r1.next();
        if (r0 == null) goto L4;
        r0.setAlpha(r2);
        goto L4
    }

    public void setPopupWindowLayout(ActionBarPopupWindow.ActionBarPopupWindowLayout r2) {
        this.popupWindowLayout = r2;
        r2.setOnSizeChangedListener(new ChatScrimPopupContainerLayout$$ExternalSyntheticLambda0(this));
        if (r2.getSwipeBack() == null) goto L6;
        r2.getSwipeBack().addOnSwipeBackProgressListener(new ChatScrimPopupContainerLayout$$ExternalSyntheticLambda1(this));
        return;
    }

    public void setReactionsLayout(ReactionsContainerLayout r1) {
        this.reactionsLayout = r1;
        if (r1 == null) goto L6;
        r1.setChatScrimView(this);
        return;
    }

    public void setReactionsTransitionProgress(float r6) {
        this.lastReactionsTransitionProgress = r6;
        this.popupWindowLayout.setReactionsTransitionProgress(r6);
        ReactionsContainerLayout r0 = this.reactionsLayout;
        if (r0 != null) goto L5;
    L7:
        r6 = 1.0f;
    L8:
        Iterator<View> r02 = this.bottomViews.iterator();
    L10:
        if (r02.hasNext() == false) goto L17;
        View r1 = r02.next();
        if (r1 == null) goto L10;
        if (this.progressToSwipeBack != 0.0f) goto L16;
        r1.setAlpha(r6);
    L16:
        float r4 = (r6 * 0.5f) + 0.5f;
        r1.setPivotX(r1.getMeasuredWidth());
        r1.setPivotY(0.0f);
        r1.setScaleX(r4);
        r1.setScaleY(r4);
        goto L10
    L17:
        updateBottomViewPosition();
        return;
    L5:
        if (r0.getItemsCount() <= 0) goto L7;
        goto L7
    }
}
