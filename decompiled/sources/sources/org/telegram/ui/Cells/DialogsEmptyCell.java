package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.util.Consumer;
import com.exteragram.messenger.ExteraConfig;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BlurredRecyclerView;
import org.telegram.ui.Components.Easings;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;
import org.telegram.ui.Components.TextViewSwitcher;

/* loaded from: classes6.dex */
public class DialogsEmptyCell extends LinearLayout {
    private final int currentAccount;
    private int currentType;
    private final RLottieImageView imageView;
    private Runnable onUtyanAnimationEndListener;
    private Consumer<Float> onUtyanAnimationUpdateListener;
    private int prevIcon;
    private final TextViewSwitcher subtitleView;
    private final TextView titleView;
    private boolean utyanAnimationTriggered;
    private ValueAnimator utyanAnimator;
    private float utyanCollapseProgress;

    /* renamed from: org.telegram.ui.Cells.DialogsEmptyCell$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsEmptyCell this$0;

        public AnonymousClass1(DialogsEmptyCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (DialogsEmptyCell.m6918$$Nest$fgetonUtyanAnimationEndListener(this.this$0) == null) goto L6;
            DialogsEmptyCell.m6918$$Nest$fgetonUtyanAnimationEndListener(this.this$0).run();
        L6:
            if (r2 != DialogsEmptyCell.m6919$$Nest$fgetutyanAnimator(this.this$0)) goto L9;
            DialogsEmptyCell.m6920$$Nest$fpututyanAnimator(this.this$0, null);
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.DialogsEmptyCell$2, reason: invalid class name */
    public class AnonymousClass2 extends AnimatorListenerAdapter {
        final /* synthetic */ DialogsEmptyCell this$0;

        public AnonymousClass2(DialogsEmptyCell r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r2) {
            if (DialogsEmptyCell.m6918$$Nest$fgetonUtyanAnimationEndListener(this.this$0) == null) goto L6;
            DialogsEmptyCell.m6918$$Nest$fgetonUtyanAnimationEndListener(this.this$0).run();
        L6:
            if (r2 != DialogsEmptyCell.m6919$$Nest$fgetutyanAnimator(this.this$0)) goto L9;
            DialogsEmptyCell.m6920$$Nest$fpututyanAnimator(this.this$0, null);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$2rbnsx3OuGcb1NIZCZEZ9dxq0W8(DialogsEmptyCell r0, ValueAnimator r1) {
        r0.lambda$startUtyanExpandAnimation$3(r1);
    }

    /* renamed from: $r8$lambda$DyE5nhTjwATc_RUBuK-rX5spjis, reason: not valid java name */
    public static /* synthetic */ void m6916$r8$lambda$DyE5nhTjwATc_RUBuKrX5spjis(DialogsEmptyCell r0, ValueAnimator r1) {
        r0.lambda$startUtyanCollapseAnimation$4(r1);
    }

    public static /* synthetic */ void $r8$lambda$_Xr4Xh3iJRBCV8ufUe_I8KuNnGE(DialogsEmptyCell r0, View r1) {
        r0.lambda$new$1(r1);
    }

    public static /* synthetic */ View $r8$lambda$orrQ6KUp7SgjvkysqPE4x0aE658(Context r2) {
        TextView r0 = new TextView(r2);
        r0.setTextColor(Theme.getColor(Theme.key_chats_message));
        r0.setTextSize(1, 14.0f);
        r0.setGravity(17);
        r0.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        return r0;
    }

    /* renamed from: $r8$lambda$ugpKNg-6fsNS82BgCbqgLN-nBV0, reason: not valid java name */
    public static /* synthetic */ boolean m6917$r8$lambda$ugpKNg6fsNS82BgCbqgLNnBV0(View r0, MotionEvent r1) {
        return true;
    }

    /* renamed from: -$$Nest$fgetonUtyanAnimationEndListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m6918$$Nest$fgetonUtyanAnimationEndListener(DialogsEmptyCell r0) {
        return r0.onUtyanAnimationEndListener;
    }

    /* renamed from: -$$Nest$fgetutyanAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ValueAnimator m6919$$Nest$fgetutyanAnimator(DialogsEmptyCell r0) {
        return r0.utyanAnimator;
    }

    /* renamed from: -$$Nest$fpututyanAnimator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m6920$$Nest$fpututyanAnimator(DialogsEmptyCell r0, ValueAnimator r1) {
        r0.utyanAnimator = r1;
    }

    public DialogsEmptyCell(Context r12) {
        super(r12);
        this.currentType = -1;
        this.currentAccount = UserConfig.selectedAccount;
        setGravity(17);
        setOrientation(1);
        setOnTouchListener(new DialogsEmptyCell$$ExternalSyntheticLambda0());
        RLottieImageView r2 = new RLottieImageView(r12);
        this.imageView = r2;
        r2.setScaleType(ImageView.ScaleType.CENTER);
        addView(r2, LayoutHelper.createFrame(100, 100.0f, 17, 52.0f, 4.0f, 52.0f, 0.0f));
        r2.setOnClickListener(new DialogsEmptyCell$$ExternalSyntheticLambda1(this));
        TextView r22 = new TextView(r12);
        this.titleView = r22;
        r22.setTextColor(Theme.getColor(Theme.key_chats_nameMessage_threeLines));
        r22.setTextSize(1, 20.0f);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setGravity(17);
        addView(r22, LayoutHelper.createFrame(-1, -2.0f, 51, 52.0f, 10.0f, 52.0f, 0.0f));
        TextViewSwitcher r0 = new TextViewSwitcher(r12);
        this.subtitleView = r0;
        r0.setFactory(new DialogsEmptyCell$$ExternalSyntheticLambda2(r12));
        r0.setInAnimation(r12, R.anim.alpha_in);
        r0.setOutAnimation(r12, R.anim.alpha_out);
        addView(r0, LayoutHelper.createFrame(-1, -2.0f, 51, 52.0f, 7.0f, 52.0f, 0.0f));
    }

    private /* synthetic */ void lambda$new$1(View r2) {
        if (this.imageView.isPlaying() == true) goto L6;
        this.imageView.setProgress(0.0f);
        this.imageView.playAnimation();
        return;
    }

    private /* synthetic */ void lambda$startUtyanCollapseAnimation$4(ValueAnimator r1) {
        this.utyanCollapseProgress = ((Float) r1.getAnimatedValue()).floatValue();
        requestLayout();
        Consumer<Float> r12 = this.onUtyanAnimationUpdateListener;
        if (r12 == null) goto L6;
        r12.accept(Float.valueOf(this.utyanCollapseProgress));
        return;
    }

    private /* synthetic */ void lambda$startUtyanExpandAnimation$3(ValueAnimator r1) {
        this.utyanCollapseProgress = ((Float) r1.getAnimatedValue()).floatValue();
        requestLayout();
        Consumer<Float> r12 = this.onUtyanAnimationUpdateListener;
        if (r12 == null) goto L6;
        r12.accept(Float.valueOf(this.utyanCollapseProgress));
        return;
    }

    private int measureUtyanHeight(int r3) {
        if ((getParent() instanceof View) == false) goto L5;
        View r32 = (View) getParent();
        int r0 = (r32.getMeasuredHeight() - r32.getPaddingTop()) - r32.getPaddingBottom();
    L6:
        if (r0 != 0) goto L9;
        r0 = (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
    L9:
        return (int) (r0 + ((AndroidUtilities.dp(320.0f) - r0) * this.utyanCollapseProgress));
    L5:
        r0 = View.MeasureSpec.getSize(r3);
        goto L6
    }

    public boolean isUtyanAnimationTriggered() {
        return this.utyanAnimationTriggered;
    }

    @Override // android.view.View
    public void offsetTopAndBottom(int r1) {
        super.offsetTopAndBottom(r1);
        updateLayout();
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r1, int r2, int r3, int r4, int r5) {
        super.onLayout(r1, r2, r3, r4, r5);
        updateLayout();
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r6, int r7) {
        int r0 = this.currentType;
        if (r0 != 0) goto L5;
    L30:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r6), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(measureUtyanHeight(r7), TLObject.FLAG_30));
        return;
    L5:
        if (r0 == 1) goto L30;
        if (r0 == 2) goto L15;
        if (r0 == 3) goto L15;
        super.onMeasure(r6, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(166.0f), TLObject.FLAG_30));
        return;
    L15:
        if ((getParent() instanceof View) == false) goto L19;
        View r72 = (View) getParent();
        int r02 = r72.getMeasuredHeight();
        if (r72.getPaddingTop() == 0) goto L20;
        r02 = r02 - AndroidUtilities.statusBarHeight;
    L20:
        if (r02 != 0) goto L23;
        r02 = (AndroidUtilities.displaySize.y - ActionBar.getCurrentActionBarHeight()) - AndroidUtilities.statusBarHeight;
    L23:
        if ((getParent() instanceof BlurredRecyclerView) == false) goto L25;
        r02 = r02 - ((BlurredRecyclerView) getParent()).blurTopPadding;
    L25:
        ArrayList<TLRPC.RecentMeUrl> r73 = MessagesController.getInstance(this.currentAccount).hintDialogs;
        if (r73.isEmpty() == true) goto L28;
        r02 = r02 - ((((AndroidUtilities.dp(72.0f) * r73.size()) + r73.size()) - 1) + AndroidUtilities.dp(50.0f));
    L28:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r6), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(r02, TLObject.FLAG_30));
        return;
    L19:
        r02 = View.MeasureSpec.getSize(r7);
        goto L20
    }

    public void setOnUtyanAnimationEndListener(Runnable r1) {
        this.onUtyanAnimationEndListener = r1;
    }

    public void setOnUtyanAnimationUpdateListener(Consumer<Float> r1) {
        this.onUtyanAnimationUpdateListener = r1;
    }

    public void setType(int r8, boolean r9) {
        if (this.currentType != r8) goto L5;
        return;
    L5:
        this.currentType = r8;
        if (r8 == 0) goto L15;
        if (r8 == 1) goto L15;
        RLottieImageView r2 = this.imageView;
        if (r8 == 2) goto L11;
        r2.setAutoRepeat(true);
        int r82 = R.raw.filter_new;
        String r92 = LocaleController.getString(R.string.FilterAddingChatsInfo);
        this.titleView.setText(LocaleController.getString(R.string.FilterAddingChats));
    L16:
        RLottieImageView r22 = this.imageView;
        if (r82 == 0) goto L32;
        r22.setVisibility(0);
        if (this.currentType != 1) goto L30;
        if (isUtyanAnimationTriggered() == false) goto L28;
        this.utyanCollapseProgress = 1.0f;
        String r23 = LocaleController.getString(R.string.NoChatsContactsHelp);
        if (AndroidUtilities.isTablet() == true) goto L25;
    L27:
        this.subtitleView.setText(r23, true);
        requestLayout();
        goto L30
    L25:
        if (AndroidUtilities.isSmallTablet() == true) goto L27;
        r23 = r23.replace('\n', ' ');
        goto L27
    L28:
        startUtyanCollapseAnimation(true);
    L30:
        if (this.prevIcon == r82) goto L34;
        this.imageView.setAnimation(r82, 100, 100);
        this.imageView.playAnimation();
        this.prevIcon = r82;
    L34:
        if (AndroidUtilities.isTablet() == true) goto L36;
    L38:
        this.subtitleView.setText(r92, false);
        int r83 = this.currentType;
        if (r83 == 0) goto L42;
        if (r83 == 1) goto L42;
    L45:
        this.subtitleView.setVisibility(0);
        return;
    L42:
        if (ExteraConfig.getHideFloatingButton() == false) goto L45;
        this.subtitleView.setVisibility(8);
        return;
    L36:
        if (AndroidUtilities.isSmallTablet() == true) goto L38;
        r92 = r92.replace('\n', ' ');
        goto L38
    L32:
        r22.setVisibility(8);
        goto L34
    L11:
        r2.setAutoRepeat(false);
        r82 = R.raw.filter_no_chats;
        TextView r24 = this.titleView;
        if (r9 == false) goto L14;
        r24.setText(LocaleController.getString(R.string.FilterNoChatsToForward));
        r92 = LocaleController.getString(R.string.FilterNoChatsToForwardInfo);
        goto L16
    L14:
        r24.setText(LocaleController.getString(R.string.FilterNoChatsToDisplay));
        r92 = LocaleController.getString(R.string.FilterNoChatsToDisplayInfo);
    L15:
        r82 = R.raw.utyan_newborn;
        r92 = LocaleController.getString(R.string.NoChatsHelp);
        this.titleView.setText(LocaleController.getString(R.string.NoChats));
        goto L16
    }

    public void startUtyanCollapseAnimation(boolean r4) {
        ValueAnimator r0 = this.utyanAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.utyanAnimationTriggered = true;
        if (r4 == false) goto L13;
        String r42 = LocaleController.getString(R.string.NoChatsContactsHelp);
        if (AndroidUtilities.isTablet() == true) goto L10;
    L12:
        this.subtitleView.setText(r42, true);
        goto L13
    L10:
        if (AndroidUtilities.isSmallTablet() == true) goto L12;
        r42 = r42.replace('\n', ' ');
    L13:
        ValueAnimator r43 = ValueAnimator.ofFloat(new float[]{this.utyanCollapseProgress, 1.0f}).setDuration(250);
        this.utyanAnimator = r43;
        r43.setInterpolator(Easings.easeOutQuad);
        this.utyanAnimator.addUpdateListener(new DialogsEmptyCell$$ExternalSyntheticLambda3(this));
        this.utyanAnimator.addListener(new AnonymousClass2(this));
        this.utyanAnimator.start();
    }

    public void startUtyanExpandAnimation() {
        ValueAnimator r0 = this.utyanAnimator;
        if (r0 == null) goto L5;
        r0.cancel();
    L5:
        this.utyanAnimationTriggered = false;
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.utyanCollapseProgress, 0.0f}).setDuration(250);
        this.utyanAnimator = r02;
        r02.setInterpolator(Easings.easeOutQuad);
        this.utyanAnimator.addUpdateListener(new DialogsEmptyCell$$ExternalSyntheticLambda4(this));
        this.utyanAnimator.addListener(new AnonymousClass1(this));
        this.utyanAnimator.start();
    }

    public void updateLayout() {
        int r1 = 0;
        if ((getParent() instanceof View) == false) goto L11;
        int r0 = this.currentType;
        if (r0 == 2) goto L9;
        if (r0 != 3) goto L11;
    L9:
        if (((View) getParent()).getPaddingTop() == 0) goto L11;
        r1 = 0 - (getTop() / 2);
    L11:
        int r02 = this.currentType;
        if (r02 != 0) goto L14;
    L15:
        r1 = r1 - ((int) ((ActionBar.getCurrentActionBarHeight() / 2.0f) * (1.0f - this.utyanCollapseProgress)));
    L16:
        float r12 = r1;
        this.imageView.setTranslationY(r12);
        this.titleView.setTranslationY(r12);
        this.subtitleView.setTranslationY(r12);
        return;
    L14:
        if (r02 != 1) goto L16;
        goto L15
    }
}
