package org.telegram.ui.Components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.LinearGradient;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Shader;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.utils.system.VibratorUtils;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.voip.VoIPService;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.voip.VoIPButtonsLayout;
import org.telegram.ui.Components.voip.VoIPToggleButton;
import org.telegram.ui.GroupCallActivity;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class GroupCallPipAlertView extends LinearLayout implements VoIPService.StateListener, NotificationCenter.NotificationCenterDelegate {
    BackupImageView avatarImageView;
    int currentAccount;
    float cx;
    float cy;
    FrameLayout groupInfoContainer;
    private boolean invalidateGradient;
    VoIPToggleButton leaveButton;
    LinearGradient linearGradient;
    VoIPToggleButton muteButton;
    float muteProgress;
    private boolean mutedByAdmin;
    float mutedByAdminProgress;
    Paint paint;
    private int position;
    RectF rectF;
    VoIPToggleButton soundButton;
    TextView subtitleView;
    TextView titleView;

    /* renamed from: org.telegram.ui.Components.GroupCallPipAlertView$1, reason: invalid class name */
    public class AnonymousClass1 extends FrameLayout {
        final /* synthetic */ GroupCallPipAlertView this$0;

        public AnonymousClass1(GroupCallPipAlertView r1, Context r2) {
            this.this$0 = r1;
            super(r2);
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r3) {
            super.onInitializeAccessibilityNodeInfo(r3);
            VoIPService r2 = VoIPService.getSharedInstance();
            if (r2 != null) goto L5;
        L8:
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.VoipGroupOpenVoiceChat)));
            return;
        L5:
            if (ChatObject.isChannelOrGiga(r2.getChat()) == false) goto L8;
            r3.addAction(new AccessibilityNodeInfo.AccessibilityAction(16, LocaleController.getString(R.string.VoipChannelOpenVoiceChat)));
        }
    }

    /* renamed from: $r8$lambda$TYRWZUik-Ziyx5niEZ0R7yKVs08, reason: not valid java name */
    public static /* synthetic */ void m10850$r8$lambda$TYRWZUikZiyx5niEZ0R7yKVs08(GroupCallPipAlertView r0, Context r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$VbPJ3k-SbJTXMek3CeG95zxHjDM, reason: not valid java name */
    public static /* synthetic */ void m10851$r8$lambda$VbPJ3kSbJTXMek3CeG95zxHjDM(GroupCallPipAlertView r0, Context r1, View r2) {
        r0.lambda$new$4(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$W0Mmh2Wm2RSUPaGN2kLCCZxC9Hk(GroupCallPipAlertView r0, View r1) {
        r0.lambda$new$2(r1);
    }

    /* renamed from: $r8$lambda$X4v34cFND7hrV-1ZGVnBox7YIkA, reason: not valid java name */
    public static /* synthetic */ void m10852$r8$lambda$X4v34cFND7hrV1ZGVnBox7YIkA(GroupCallPipAlertView r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public static /* synthetic */ void $r8$lambda$kpe7pWk0oJPP0pwIcIkJTUcoVMY(Context r0) {
        GroupCallPip.updateVisibility(r0);
    }

    public GroupCallPipAlertView(Context r20, int r21) {
        super(r20);
        this.rectF = new RectF();
        this.paint = new Paint(1);
        this.invalidateGradient = true;
        setOrientation(1);
        this.currentAccount = r21;
        this.paint.setAlpha(234);
        AnonymousClass1 r2 = new AnonymousClass1(this, r20);
        this.groupInfoContainer = r2;
        r2.setPadding(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        BackupImageView r22 = new BackupImageView(r20);
        this.avatarImageView = r22;
        r22.setRoundRadius(ExteraConfig.getAvatarCorners(44.0f));
        this.groupInfoContainer.addView(this.avatarImageView, LayoutHelper.createFrame(44, 44.0f));
        this.groupInfoContainer.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(6.0f), 0, ColorUtils.setAlphaComponent(-1, 76)));
        this.groupInfoContainer.setOnClickListener(new GroupCallPipAlertView$$ExternalSyntheticLambda0(this));
        LinearLayout r23 = new LinearLayout(r20);
        r23.setOrientation(1);
        TextView r5 = new TextView(r20);
        this.titleView = r5;
        r5.setTextColor(-1);
        this.titleView.setTextSize(15.0f);
        this.titleView.setMaxLines(2);
        this.titleView.setEllipsize(TextUtils.TruncateAt.END);
        this.titleView.setTypeface(AndroidUtilities.bold());
        r23.addView(this.titleView, LayoutHelper.createLinear(-1, -2));
        TextView r52 = new TextView(r20);
        this.subtitleView = r52;
        r52.setTextSize(12.0f);
        this.subtitleView.setTextColor(ColorUtils.setAlphaComponent(-1, 153));
        r23.addView(this.subtitleView, LayoutHelper.createLinear(-1, -2));
        this.groupInfoContainer.addView(r23, LayoutHelper.createFrame(-1, -2.0f, 16, 55.0f, 0.0f, 0.0f, 0.0f));
        addView(this.groupInfoContainer, LayoutHelper.createLinear(-1, -2, 0, 10, 10, 10, 10));
        VoIPToggleButton r24 = new VoIPToggleButton(r20, 44.0f);
        this.soundButton = r24;
        r24.setTextSize(12);
        this.soundButton.setOnClickListener(new GroupCallPipAlertView$$ExternalSyntheticLambda1(this, r20));
        this.soundButton.setCheckable(true);
        this.soundButton.setBackgroundColor(ColorUtils.setAlphaComponent(-1, 38), ColorUtils.setAlphaComponent(-1, 76));
        VoIPToggleButton r25 = new VoIPToggleButton(r20, 44.0f);
        this.muteButton = r25;
        r25.setTextSize(12);
        this.muteButton.setOnClickListener(new GroupCallPipAlertView$$ExternalSyntheticLambda2(this));
        VoIPToggleButton r26 = new VoIPToggleButton(r20, 44.0f);
        this.leaveButton = r26;
        r26.setTextSize(12);
        this.leaveButton.setData(R.drawable.calls_decline, -1, -3257782, 0.3f, false, LocaleController.getString(R.string.VoipGroupLeave), false, false);
        this.leaveButton.setOnClickListener(new GroupCallPipAlertView$$ExternalSyntheticLambda3(this, r20));
        VoIPButtonsLayout r27 = new VoIPButtonsLayout(r20);
        r27.setChildSize(68);
        r27.setUseStartPadding(false);
        r27.addView(this.soundButton, LayoutHelper.createFrame(68, 63.0f));
        r27.addView(this.muteButton, LayoutHelper.createFrame(68, 63.0f));
        r27.addView(this.leaveButton, LayoutHelper.createFrame(68, 63.0f));
        setWillNotDraw(false);
        addView(r27, LayoutHelper.createLinear(-1, -2, 0, 6, 0, 6, 0));
    }

    private /* synthetic */ void lambda$new$0(View r3) {
        if (VoIPService.getSharedInstance() == null) goto L6;
        Intent r32 = new Intent(getContext(), LaunchActivity.class).setAction("voip_chat");
        r32.putExtra("currentAccount", VoIPService.getSharedInstance().getAccount());
        getContext().startActivity(r32);
        return;
    }

    private /* synthetic */ void lambda$new$1(Context r1, View r2) {
        if (VoIPService.getSharedInstance() != null) goto L5;
        return;
    L5:
        VoIPService.getSharedInstance().toggleSpeakerphoneOrShowRouteSheet(getContext(), Settings.canDrawOverlays(r1));
    }

    private /* synthetic */ void lambda$new$2(View r3) {
        if (VoIPService.getSharedInstance() != null) goto L5;
        return;
    L5:
        if (VoIPService.getSharedInstance().mutedByAdmin() == false) goto L8;
        this.muteButton.shakeView();
        VibratorUtils.vibrate();
        return;
    L8:
        VoIPService.getSharedInstance().setMicMute(!VoIPService.getSharedInstance().isMicMute(), false, true);
    }

    private /* synthetic */ void lambda$new$4(Context r1, View r2) {
        GroupCallActivity.onLeaveClick(getContext(), new GroupCallPipAlertView$$ExternalSyntheticLambda4(r1), Settings.canDrawOverlays(r1));
    }

    private void updateButtons(boolean r14) {
        if (this.soundButton != null) goto L5;
        return;
    L5:
        if (this.muteButton == null) goto L41;
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L42;
        boolean r1 = r0.isBluetoothOn();
        if (r1 == false) goto L13;
    L15:
        boolean r2 = false;
    L16:
        this.soundButton.setChecked(r2, r14);
        if (r1 == false) goto L19;
        this.soundButton.setData(R.drawable.calls_bluetooth, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipAudioRoutingBluetooth), false, r14);
    L25:
        boolean r12 = r0.mutedByAdmin();
        VoIPToggleButton r4 = this.muteButton;
        if (r12 == false) goto L28;
        r4.setData(R.drawable.calls_unmute, -1, ColorUtils.setAlphaComponent(-1, 76), 0.1f, true, LocaleController.getString(R.string.VoipMutedByAdminShort), true, r14);
    L38:
        invalidate();
        return;
    L28:
        int r5 = R.drawable.calls_unmute;
        if (r0.isMicMute() == false) goto L31;
        float r13 = 0.3f;
    L32:
        int r7 = ColorUtils.setAlphaComponent(-1, (int) (r13 * 255.0f));
        if (r0.isMicMute() == false) goto L36;
        int r15 = R.string.VoipUnmute;
    L35:
        String r16 = LocaleController.getString(r15);
        r4.setData(r5, -1, r7, 0.1f, true, r16, r0.isMicMute(), r14);
        goto L38
    L36:
        r15 = R.string.VoipMute;
        goto L35
    L31:
        r13 = 0.15f;
        goto L32
    L19:
        if (r2 == false) goto L21;
        this.soundButton.setData(R.drawable.calls_speaker, -1, 0, 0.3f, true, LocaleController.getString(R.string.VoipSpeaker), false, r14);
        goto L25
    L21:
        boolean r17 = r0.isHeadsetPlugged();
        VoIPToggleButton r42 = this.soundButton;
        if (r17 == false) goto L24;
        r42.setData(R.drawable.calls_headphones, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipAudioRoutingHeadset), false, r14);
        goto L25
    L24:
        r42.setData(R.drawable.calls_speaker, -1, 0, 0.1f, true, LocaleController.getString(R.string.VoipSpeaker), false, r14);
        goto L25
    L13:
        if (r0.isSpeakerphoneOn() == false) goto L15;
        r2 = true;
        goto L16
    L42:
        return;
    }

    private void updateMembersCount() {
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
        return;
    L5:
        if (r0.groupCall == null) goto L25;
        int r1 = r0.getCallState();
        if (r0.isSwitchingStream() == false) goto L9;
    L18:
        TextView r3 = this.subtitleView;
        TLRPC.GroupCall r02 = r0.groupCall.call;
        if (r02.rtmp_stream == false) goto L21;
        String r12 = "ViewersWatching";
    L22:
        r3.setText(LocaleController.formatPluralString(r12, r02.participants_count, new Object[0]));
        return;
    L21:
        r12 = "Participants";
        goto L22
    L9:
        if (r1 != 1) goto L11;
    L16:
        this.subtitleView.setText(LocaleController.getString(R.string.VoipGroupConnecting));
        return;
    L11:
        if (r1 == 2) goto L16;
        if (r1 == 6) goto L16;
        if (r1 != 5) goto L18;
    }

    @Override // org.telegram.messenger.NotificationCenter.NotificationCenterDelegate
    public void didReceivedNotification(int r1, int r2, Object... r3) {
        if (r1 != NotificationCenter.groupCallUpdated) goto L10;
        updateMembersCount();
        if (VoIPService.getSharedInstance() == null) goto L11;
        boolean r12 = VoIPService.getSharedInstance().mutedByAdmin();
        if (r12 == this.mutedByAdmin) goto L12;
        this.mutedByAdmin = r12;
        invalidate();
        return;
    L12:
        return;
    L11:
        return;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 != null) goto L5;
    L56:
        NotificationCenter.getInstance(this.currentAccount).addObserver(this, NotificationCenter.groupCallUpdated);
        updateButtons(false);
        return;
    L5:
        if (r0.groupCall == null) goto L56;
        AvatarDrawable r2 = new AvatarDrawable();
        TLRPC.Chat r3 = r0.getChat();
        int[] r4 = Theme.keys_avatar_background;
        long r5 = 0;
        if (r3 == null) goto L9;
        long r7 = r3.id;
    L10:
        int r42 = Theme.getColor(r4[AvatarDrawable.getColorIndex(r7)]);
        int[] r72 = Theme.keys_avatar_background2;
        if (r3 == null) goto L13;
        r5 = r3.id;
    L13:
        r2.setColor(r42, Theme.getColor(r72[AvatarDrawable.getColorIndex(r5)]));
        r2.setInfo(this.currentAccount, r3);
        if (r3 == null) goto L17;
        this.avatarImageView.setImage(ImageLocation.getForLocal(r3.photo.photo_small), "50_50", r2, null);
    L17:
        if (r0.isConference() == false) goto L34;
        ChatObject.Call r22 = r0.groupCall;
        if (r22 == null) goto L34;
        if (r22.sortedParticipants.size() != 1) goto L23;
        String r23 = LocaleController.getString(R.string.ConferenceChat);
    L39:
        if (r23 == null) goto L41;
        r23 = r23.replace("\n", " ").replaceAll(" +", " ").trim();
    L41:
        this.titleView.setText(r23);
        updateMembersCount();
        r0.registerStateListener(this);
        if (VoIPService.getSharedInstance() == null) goto L44;
        this.mutedByAdmin = VoIPService.getSharedInstance().mutedByAdmin();
    L44:
        float r24 = 0.0f;
        if (this.mutedByAdmin == false) goto L47;
        float r02 = 1.0f;
    L48:
        this.mutedByAdminProgress = r02;
        if (VoIPService.getSharedInstance() != null) goto L51;
    L54:
        r24 = 1.0f;
    L55:
        this.muteProgress = r24;
        goto L56
    L51:
        if (VoIPService.getSharedInstance().isMicMute() == true) goto L54;
        if (this.mutedByAdmin == false) goto L55;
    L47:
        r02 = 0.0f;
        goto L48
    L23:
        StringBuilder r25 = new StringBuilder();
        int r32 = 0;
    L25:
        if (r32 >= Math.min(3, r0.groupCall.sortedParticipants.size())) goto L30;
        if (r32 <= 0) goto L28;
        r25.append(", ");
    L28:
        r25.append(DialogObject.getShortName(DialogObject.getPeerDialogId(r0.groupCall.sortedParticipants.get(r32).peer)));
        r32 = r32 + 1;
        goto L25
    L30:
        if (r0.groupCall.sortedParticipants.size() <= 3) goto L32;
        r25.append(" ");
        r25.append(LocaleController.formatPluralString("AndOther", r0.groupCall.sortedParticipants.size() - 3, new Object[0]));
    L32:
        r23 = r25.toString();
    L34:
        if (TextUtils.isEmpty(r0.groupCall.call.title) == true) goto L36;
        r23 = r0.groupCall.call.title;
        goto L39
    L36:
        if (r3 == null) goto L38;
        r23 = r3.title;
        goto L39
    L38:
        r23 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L39
    L9:
        r7 = 0;
        goto L10
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onAudioSettingsChanged() {
        updateButtons(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        VoIPService r0 = VoIPService.getSharedInstance();
        if (r0 == null) goto L5;
        r0.unregisterStateListener(this);
    L5:
        NotificationCenter.getInstance(this.currentAccount).removeObserver(this, NotificationCenter.groupCallUpdated);
    }

    @Override // android.widget.LinearLayout, android.view.View
    @SuppressLint({"DrawAllocation"})
    public void onDraw(Canvas r22) {
        if (VoIPService.getSharedInstance() != null) goto L5;
    L10:
        boolean r2 = true;
    L12:
        if (r2 == false) goto L19;
        float r8 = this.muteProgress;
        if (r8 == 1.0f) goto L19;
        float r82 = r8 + 0.10666667f;
        this.muteProgress = r82;
        if (r82 < 1.0f) goto L18;
        this.muteProgress = 1.0f;
    L18:
        this.invalidateGradient = true;
        invalidate();
    L26:
        boolean r23 = this.mutedByAdmin;
        if (r23 == false) goto L34;
        float r83 = this.mutedByAdminProgress;
        if (r83 == 1.0f) goto L34;
        float r84 = r83 + 0.10666667f;
        this.mutedByAdminProgress = r84;
        if (r84 < 1.0f) goto L33;
        this.mutedByAdminProgress = 1.0f;
    L33:
        this.invalidateGradient = true;
        invalidate();
    L42:
        if (this.invalidateGradient == false) goto L51;
        int r24 = ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_overlayAlertGradientMuted), Theme.getColor(Theme.key_voipgroup_overlayAlertGradientUnmuted), 1.0f - this.muteProgress);
        int r6 = ColorUtils.blendARGB(Theme.getColor(Theme.key_voipgroup_overlayAlertGradientMuted2), Theme.getColor(Theme.key_voipgroup_overlayAlertGradientUnmuted2), 1.0f - this.muteProgress);
        int r25 = ColorUtils.blendARGB(r24, Theme.getColor(Theme.key_voipgroup_overlayAlertMutedByAdmin), this.mutedByAdminProgress);
        int r62 = ColorUtils.blendARGB(r6, Theme.getColor(Theme.key_voipgroup_overlayAlertMutedByAdmin2), this.mutedByAdminProgress);
        this.invalidateGradient = false;
        int r3 = this.position;
        if (r3 != 0) goto L46;
        this.linearGradient = new LinearGradient(-AndroidUtilities.dp(60.0f), this.cy - getTranslationY(), getMeasuredWidth(), getMeasuredHeight() / 2.0f, new int[]{r25, r62}, null, Shader.TileMode.CLAMP);
        goto L51
    L46:
        if (r3 != 1) goto L48;
        this.linearGradient = new LinearGradient(0.0f, getMeasuredHeight() / 2.0f, getMeasuredWidth() + AndroidUtilities.dp(60.0f), this.cy - getTranslationY(), new int[]{r62, r25}, null, Shader.TileMode.CLAMP);
        goto L51
    L48:
        if (r3 != 2) goto L50;
        this.linearGradient = new LinearGradient(this.cx - getTranslationX(), -AndroidUtilities.dp(60.0f), getMeasuredWidth() / 2.0f, getMeasuredHeight(), new int[]{r25, r62}, null, Shader.TileMode.CLAMP);
        goto L51
    L50:
        this.linearGradient = new LinearGradient(getMeasuredWidth() / 2.0f, 0.0f, this.cx - getTranslationX(), getMeasuredHeight() + AndroidUtilities.dp(60.0f), new int[]{r62, r25}, null, Shader.TileMode.CLAMP);
    L51:
        this.rectF.set(0.0f, 0.0f, getMeasuredWidth(), getMeasuredHeight());
        this.paint.setShader(this.linearGradient);
        r22.drawRoundRect(this.rectF, AndroidUtilities.dp(10.0f), AndroidUtilities.dp(10.0f), this.paint);
        int r26 = this.position;
        if (r26 != 0) goto L54;
        float r27 = this.cy - getTranslationY();
        float r63 = 0.0f;
    L60:
        setPivotX(r63);
        setPivotY(r27);
        r22.save();
        int r85 = this.position;
        if (r85 != 0) goto L63;
        r22.clipRect(r63 - AndroidUtilities.dp(15.0f), r27 - AndroidUtilities.dp(15.0f), r63, AndroidUtilities.dp(15.0f) + r27);
        r22.translate(AndroidUtilities.dp(3.0f), 0.0f);
        r22.rotate(45.0f, r63, r27);
    L68:
        this.rectF.set(r63 - AndroidUtilities.dp(10.0f), r27 - AndroidUtilities.dp(10.0f), r63 + AndroidUtilities.dp(10.0f), r27 + AndroidUtilities.dp(10.0f));
        r22.drawRoundRect(this.rectF, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), this.paint);
        r22.restore();
        super.onDraw(r22);
        return;
    L63:
        if (r85 != 1) goto L65;
        r22.clipRect(r63, r27 - AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f) + r63, AndroidUtilities.dp(15.0f) + r27);
        r22.translate(-AndroidUtilities.dp(3.0f), 0.0f);
        r22.rotate(45.0f, r63, r27);
        goto L68
    L65:
        if (r85 != 2) goto L67;
        r22.clipRect(r63 - AndroidUtilities.dp(15.0f), r27 - AndroidUtilities.dp(15.0f), AndroidUtilities.dp(15.0f) + r63, r27);
        r22.rotate(45.0f, r63, r27);
        r22.translate(0.0f, AndroidUtilities.dp(3.0f));
        goto L68
    L67:
        r22.clipRect(r63 - AndroidUtilities.dp(15.0f), r27, AndroidUtilities.dp(15.0f) + r63, AndroidUtilities.dp(15.0f) + r27);
        r22.rotate(45.0f, r63, r27);
        r22.translate(0.0f, -AndroidUtilities.dp(3.0f));
        goto L68
    L54:
        if (r26 != 1) goto L56;
        r27 = this.cy - getTranslationY();
        r63 = getMeasuredWidth();
        goto L60
    L56:
        float r64 = this.cx;
        if (r26 != 2) goto L59;
        r63 = r64 - getTranslationX();
        r27 = 0.0f;
        goto L60
    L59:
        r63 = r64 - getTranslationX();
        r27 = getMeasuredHeight();
    L34:
        if (r23 == true) goto L42;
        float r28 = this.mutedByAdminProgress;
        if (r28 == 0.0f) goto L42;
        float r29 = r28 - 0.10666667f;
        this.mutedByAdminProgress = r29;
        if (r29 >= 0.0f) goto L40;
        this.mutedByAdminProgress = 0.0f;
    L40:
        this.invalidateGradient = true;
        invalidate();
    L19:
        if (r2 == true) goto L26;
        float r210 = this.muteProgress;
        if (r210 == 0.0f) goto L26;
        float r211 = r210 - 0.10666667f;
        this.muteProgress = r211;
        if (r211 >= 0.0f) goto L25;
        this.muteProgress = 0.0f;
    L25:
        this.invalidateGradient = true;
        invalidate();
        goto L26
    L5:
        if (VoIPService.getSharedInstance().isMicMute() == true) goto L10;
        if (this.mutedByAdmin == true) goto L10;
        r2 = false;
        goto L12
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(230.0f), TLObject.FLAG_30), r3);
    }

    @Override // org.telegram.messenger.voip.VoIPService.StateListener
    public void onStateChanged(int r1) {
        updateMembersCount();
    }

    public void setPosition(int r1, float r2, float r3) {
        this.position = r1;
        this.cx = r2;
        this.cy = r3;
        invalidate();
        this.invalidateGradient = true;
    }
}
