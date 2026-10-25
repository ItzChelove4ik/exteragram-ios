package org.telegram.ui.Components;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.ProfileActivity;

/* loaded from: classes7.dex */
public abstract class JoinToSendSettingsView extends LinearLayout {
    private final int MAXSPEC;
    private TLRPC.Chat currentChat;
    public boolean isJoinRequest;
    public boolean isJoinToSend;
    public HeaderCell joinHeaderCell;
    public TextCheckCell joinRequestCell;
    public TextInfoPrivacyCell joinRequestInfoCell;
    public TextCheckCell joinToSendCell;
    public TextInfoPrivacyCell joinToSendInfoCell;
    private ValueAnimator toggleAnimator;
    private float toggleValue;

    /* renamed from: org.telegram.ui.Components.JoinToSendSettingsView$1, reason: invalid class name */
    public class AnonymousClass1 extends AnimatorListenerAdapter {
        final /* synthetic */ JoinToSendSettingsView this$0;

        public AnonymousClass1(JoinToSendSettingsView r1) {
            this.this$0 = r1;
        }

        @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
        public void onAnimationEnd(Animator r1) {
            JoinToSendSettingsView r0 = this.this$0;
            if (r0.isJoinToSend == true) goto L6;
            r0.joinRequestCell.setVisibility(8);
            return;
        }
    }

    public static /* synthetic */ void $r8$lambda$5LW_rf1ji0iqN5xRXtrLYoG0c_A(JoinToSendSettingsView r0, ValueAnimator r1) {
        r0.lambda$setJoinToSend$7(r1);
    }

    /* renamed from: $r8$lambda$7cBYJeaL7E_gM1cVZN2Vjwpw-F0, reason: not valid java name */
    public static /* synthetic */ void m11183$r8$lambda$7cBYJeaL7E_gM1cVZN2VjwpwF0(JoinToSendSettingsView r0, boolean r1) {
        r0.lambda$new$4(r1);
    }

    /* renamed from: $r8$lambda$CJV-NxNrUonNXvAhBzq3HXi4lVY, reason: not valid java name */
    public static /* synthetic */ void m11184$r8$lambda$CJVNxNrUonNXvAhBzq3HXi4lVY(JoinToSendSettingsView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    public static /* synthetic */ void $r8$lambda$JXHII4rWZD7GoodKuMeYmJdCNGw(JoinToSendSettingsView r0, boolean r1, boolean r2) {
        r0.lambda$new$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$K7zS6IWafYo4tK0ZJFKivwV3BSU(TLRPC.ChatFull r4, BaseFragment r5) {
        Bundle r0 = new Bundle();
        r0.putLong("user_id", r4.guard_bot_id);
        r5.presentFragment(new ProfileActivity(r0));
    }

    /* renamed from: $r8$lambda$NnoOhFcnwrF6CFh-1we7oVBE76Y, reason: not valid java name */
    public static /* synthetic */ void m11185$r8$lambda$NnoOhFcnwrF6CFh1we7oVBE76Y(JoinToSendSettingsView r0, View r1) {
        r0.lambda$new$5(r1);
    }

    public static /* synthetic */ void $r8$lambda$WLxaj9ttjeWilFCcJwCxcwfwg_I(JoinToSendSettingsView r0, boolean r1, boolean r2) {
        r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$w7QmoqNSRnu7_56HW3yK6dKqlwY(JoinToSendSettingsView r0, boolean r1) {
        r0.lambda$new$3(r1);
    }

    public JoinToSendSettingsView(Context r6, TLRPC.Chat r7) {
        super(r6);
        this.MAXSPEC = View.MeasureSpec.makeMeasureSpec(999999, Integer.MIN_VALUE);
        this.currentChat = r7;
        this.isJoinToSend = r7.join_to_send;
        this.isJoinRequest = r7.join_request;
        boolean r0 = true;
        setOrientation(1);
        HeaderCell r1 = new HeaderCell(r6, 20);
        this.joinHeaderCell = r1;
        r1.setText(LocaleController.getString(R.string.ChannelSettingsJoinTitle));
        this.joinHeaderCell.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        addView(this.joinHeaderCell);
        TextCheckCell r12 = new TextCheckCell(r6, 20);
        this.joinToSendCell = r12;
        String r3 = LocaleController.getString(R.string.ChannelSettingsJoinToSend);
        boolean r4 = this.isJoinToSend;
        r12.setTextAndCheck(r3, r4, r4);
        TextCheckCell r13 = this.joinToSendCell;
        int r42 = 0;
        if (r7.creator == true) goto L10;
        TLRPC.TL_chatAdminRights r32 = r7.admin_rights;
        if (r32 != null) goto L7;
    L9:
        boolean r33 = false;
    L11:
        r13.setEnabled(r33);
        this.joinToSendCell.setOnClickListener(new JoinToSendSettingsView$$ExternalSyntheticLambda1(this));
        addView(this.joinToSendCell);
        TextCheckCell r14 = new TextCheckCell(r6, 20);
        this.joinRequestCell = r14;
        r14.setTextAndCheck(LocaleController.getString(R.string.ChannelSettingsJoinRequest), this.isJoinRequest, false);
        float r2 = 0.0f;
        this.joinRequestCell.setPivotY(0.0f);
        TextCheckCell r15 = this.joinRequestCell;
        if (r7.creator == true) goto L19;
        TLRPC.TL_chatAdminRights r72 = r7.admin_rights;
        if (r72 != null) goto L16;
    L18:
        r0 = false;
        goto L19
    L16:
        if (r72.ban_users == false) goto L18;
    L19:
        r15.setEnabled(r0);
        this.joinRequestCell.setOnClickListener(new JoinToSendSettingsView$$ExternalSyntheticLambda2(this));
        addView(this.joinRequestCell);
        TextInfoPrivacyCell r73 = new TextInfoPrivacyCell(r6, 12);
        this.joinToSendInfoCell = r73;
        r73.setText(LocaleController.getString(R.string.ChannelSettingsJoinToSendInfo));
        addView(this.joinToSendInfoCell);
        TextInfoPrivacyCell r74 = new TextInfoPrivacyCell(r6, 12);
        this.joinRequestInfoCell = r74;
        r74.setText(LocaleController.getString(R.string.ChannelSettingsJoinRequestInfo));
        addView(this.joinRequestInfoCell);
        boolean r62 = this.isJoinToSend;
        if (r62 == false) goto L22;
        r2 = 1.0f;
    L22:
        this.toggleValue = r2;
        TextCheckCell r75 = this.joinRequestCell;
        if (r62 == true) goto L26;
        r42 = 8;
    L26:
        r75.setVisibility(r42);
        updateToggleValue(this.toggleValue);
        return;
    L7:
        if (r32.ban_users == false) goto L9;
    L10:
        r33 = true;
        goto L11
    }

    private int calcHeight() {
        if (this.joinToSendCell.getVisibility() != 0) goto L5;
        float r0 = (this.joinHeaderCell.getMeasuredHeight() + this.joinToSendCell.getMeasuredHeight()) + (this.joinRequestCell.getMeasuredHeight() * this.toggleValue);
    L7:
        return (int) (r0 + AndroidUtilities.lerp(this.joinToSendInfoCell.getMeasuredHeight(), this.joinRequestInfoCell.getMeasuredHeight(), this.toggleValue));
    L5:
        r0 = this.joinRequestCell.getMeasuredHeight();
        goto L7
    }

    private /* synthetic */ void lambda$new$0(boolean r1, boolean r2) {
        setJoinRequest(r1);
        setJoinToSend(r2);
    }

    private /* synthetic */ void lambda$new$1(boolean r2, boolean r3) {
        AndroidUtilities.runOnUIThread(new JoinToSendSettingsView$$ExternalSyntheticLambda6(this, r2, r3));
    }

    private /* synthetic */ void lambda$new$2(View r4) {
        boolean r42 = this.isJoinToSend;
        boolean r0 = !r42;
        if (onJoinToSendToggle(r0, new JoinToSendSettingsView$$ExternalSyntheticLambda5(this, this.isJoinRequest, r42)) == false) goto L6;
        setJoinRequest(false);
        setJoinToSend(r0);
        return;
    }

    private /* synthetic */ void lambda$new$3(boolean r1) {
        setJoinRequest(r1);
    }

    private /* synthetic */ void lambda$new$4(boolean r2) {
        AndroidUtilities.runOnUIThread(new JoinToSendSettingsView$$ExternalSyntheticLambda7(this, r2));
    }

    private /* synthetic */ void lambda$new$5(View r3) {
        boolean r32 = this.isJoinRequest;
        boolean r0 = !r32;
        if (onJoinRequestToggle(r0, new JoinToSendSettingsView$$ExternalSyntheticLambda4(this, r32)) == false) goto L6;
        setJoinRequest(r0);
        return;
    }

    private /* synthetic */ void lambda$setJoinToSend$7(ValueAnimator r1) {
        float r12 = ((Float) r1.getAnimatedValue()).floatValue();
        this.toggleValue = r12;
        updateToggleValue(r12);
    }

    private void updateToggleValue(float r6) {
        this.toggleValue = r6;
        this.joinRequestCell.setAlpha(r6);
        float r2 = 1.0f - r6;
        this.joinRequestCell.setTranslationY((-AndroidUtilities.dp(16.0f)) * r2);
        this.joinRequestCell.setScaleY(1.0f - (0.1f * r2));
        if (this.joinRequestCell.getMeasuredHeight() > 0) goto L5;
        int r0 = AndroidUtilities.dp(50.0f);
    L6:
        this.joinToSendInfoCell.setAlpha(r2);
        float r02 = (-r0) * r2;
        this.joinToSendInfoCell.setTranslationY(((-AndroidUtilities.dp(4.0f)) * r6) + r02);
        this.joinRequestInfoCell.setAlpha(r6);
        this.joinRequestInfoCell.setTranslationY(r02 + (AndroidUtilities.dp(4.0f) * r2));
        requestLayout();
        return;
    L5:
        r0 = this.joinRequestCell.getMeasuredHeight();
        goto L6
    }

    public float getBottomInfoMargin() {
        return (this.joinToSendInfoCell.getAlpha() * this.joinToSendInfoCell.getHeight()) + (this.joinRequestInfoCell.getAlpha() * this.joinRequestInfoCell.getHeight());
    }

    public abstract boolean onJoinRequestToggle(boolean r1, Runnable r2);

    public boolean onJoinToSendToggle(boolean r1, Runnable r2) {
        return true;
    }

    @Override // android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r3, int r4, int r5, int r6, int r7) {
        if (this.joinToSendCell.getVisibility() != 0) goto L5;
        HeaderCell r32 = this.joinHeaderCell;
        int r72 = r6 - r4;
        int r0 = r32.getMeasuredHeight();
        r32.layout(0, 0, r72, r0);
        TextCheckCell r33 = this.joinToSendCell;
        int r1 = r33.getMeasuredHeight() + r0;
        r33.layout(0, r0, r72, r1);
    L6:
        TextCheckCell r34 = this.joinRequestCell;
        int r62 = r6 - r4;
        int r42 = r34.getMeasuredHeight() + r1;
        r34.layout(0, r1, r62, r42);
        TextInfoPrivacyCell r35 = this.joinToSendInfoCell;
        r35.layout(0, r42, r62, r35.getMeasuredHeight() + r42);
        TextInfoPrivacyCell r2 = this.joinRequestInfoCell;
        r2.layout(0, r42, r62, r2.getMeasuredHeight() + r42);
        return;
    L5:
        r1 = 0;
        goto L6
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        this.joinHeaderCell.measure(r2, this.MAXSPEC);
        this.joinToSendCell.measure(r2, this.MAXSPEC);
        this.joinRequestCell.measure(r2, this.MAXSPEC);
        this.joinToSendInfoCell.measure(r2, this.MAXSPEC);
        this.joinRequestInfoCell.measure(r2, this.MAXSPEC);
        super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(calcHeight(), TLObject.FLAG_30));
    }

    public void setChat(TLRPC.Chat r5) {
        this.currentChat = r5;
        TextCheckCell r0 = this.joinToSendCell;
        boolean r2 = true;
        if (r5.creator == true) goto L10;
        TLRPC.TL_chatAdminRights r52 = r5.admin_rights;
        if (r52 != null) goto L7;
    L9:
        boolean r53 = false;
    L11:
        r0.setEnabled(r53);
        TextCheckCell r54 = this.joinRequestCell;
        TLRPC.Chat r4 = this.currentChat;
        if (r4.creator == true) goto L19;
        TLRPC.TL_chatAdminRights r42 = r4.admin_rights;
        if (r42 != null) goto L16;
    L18:
        r2 = false;
        goto L19
    L16:
        if (r42.ban_users == false) goto L18;
    L19:
        r54.setEnabled(r2);
        return;
    L7:
        if (r52.ban_users == false) goto L9;
    L10:
        r53 = true;
        goto L11
    }

    public void setFullInfo(BaseFragment r7, TLRPC.ChatFull r8) {
        boolean r0 = ChatObject.isChannelAndNotMegaGroup(this.currentChat);
        boolean r1 = ChatObject.isPublic(this.currentChat);
        if (r8 != null) goto L5;
    L14:
        TextInfoPrivacyCell r6 = this.joinRequestInfoCell;
        if (r0 == false) goto L17;
        int r72 = R.string.ChannelSettingsJoinRequestInfo2;
    L20:
        r6.setText(LocaleController.getString(r72));
        return;
    L17:
        if (r1 == false) goto L19;
        r72 = R.string.GroupPublicSettingsJoinRequestInfo2;
        goto L20
    L19:
        r72 = R.string.GroupPrivateSettingsJoinRequestInfo2;
        goto L20
    L5:
        if (r8.guard_bot_id == 0) goto L14;
        String r2 = "@" + DialogObject.getPublicUsername(MessagesController.getInstance(UserConfig.selectedAccount).getUser(Long.valueOf(r8.guard_bot_id)));
        TextInfoPrivacyCell r62 = this.joinRequestInfoCell;
        if (r0 == false) goto L9;
        int r02 = R.string.ChannelSettingsJoinRequestInfoManagedBy;
    L12:
        r62.setText(AndroidUtilities.replaceSingleLink(LocaleController.formatString(r02, new Object[]{r2}), Theme.getColor(Theme.key_telegram_color_text), new JoinToSendSettingsView$$ExternalSyntheticLambda0(r8, r7)));
        return;
    L9:
        if (r1 == false) goto L11;
        r02 = R.string.GroupPublicSettingsJoinRequestInfoManagedBy;
        goto L12
    L11:
        r02 = R.string.GroupPrivateSettingsJoinRequestInfoManagedBy;
        goto L12
    }

    public void setJoinRequest(boolean r1) {
        this.isJoinRequest = r1;
        this.joinRequestCell.setChecked(r1);
    }

    public void setJoinToSend(boolean r4) {
        this.isJoinToSend = r4;
        this.joinToSendCell.setChecked(r4);
        this.joinToSendCell.setDivider(this.isJoinToSend);
        this.joinRequestCell.setChecked(this.isJoinRequest);
        ValueAnimator r42 = this.toggleAnimator;
        if (r42 == null) goto L5;
        r42.cancel();
    L5:
        float r43 = this.toggleValue;
        if (this.isJoinToSend == false) goto L8;
        float r0 = 1.0f;
    L9:
        ValueAnimator r44 = ValueAnimator.ofFloat(new float[]{r43, r0});
        this.toggleAnimator = r44;
        r44.setDuration(200);
        this.toggleAnimator.setInterpolator(CubicBezierInterpolator.DEFAULT);
        this.toggleAnimator.addUpdateListener(new JoinToSendSettingsView$$ExternalSyntheticLambda3(this));
        this.toggleAnimator.addListener(new AnonymousClass1(this));
        this.joinRequestCell.setVisibility(0);
        this.toggleAnimator.start();
        return;
    L8:
        r0 = 0.0f;
        goto L9
    }

    public void showJoinToSend(boolean r5) {
        HeaderCell r0 = this.joinHeaderCell;
        int r1 = 8;
        if (r5 == false) goto L5;
        int r3 = 0;
    L6:
        r0.setVisibility(r3);
        TextCheckCell r02 = this.joinToSendCell;
        if (r5 == false) goto L9;
        r1 = 0;
    L9:
        r02.setVisibility(r1);
        if (r5 == true) goto L12;
        this.isJoinToSend = true;
        this.joinRequestCell.setVisibility(0);
        updateToggleValue(1.0f);
    L12:
        requestLayout();
        return;
    L5:
        r3 = 8;
        goto L6
    }
}
