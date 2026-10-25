package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.text.Layout;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.ContextCompat;
import java.util.Date;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.AppGlobalConfig;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_account;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.BottomSheet;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.Premium.PremiumButtonView;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.PremiumPreviewFragment;
import org.telegram.ui.Stories.recorder.ButtonWithCounterView;

/* loaded from: classes7.dex */
public class MessagePrivateSeenView extends FrameLayout {
    private final int currentAccount;
    private final long dialogId;
    private final Runnable dismiss;
    private final int edit_date;
    private final int fwd_date;
    public boolean isPremiumLocked;
    private final TextView loadingView;
    private final int messageDiff;
    private final int messageId;
    float minWidth;
    private final TextView premiumTextView;
    private final Theme.ResourcesProvider resourcesProvider;
    private final int sent_date;
    private final int type;
    private final LinearLayout valueLayout;
    private final TextView valueTextView;

    /* renamed from: org.telegram.ui.Components.MessagePrivateSeenView$1, reason: invalid class name */
    public class AnonymousClass1 extends SimpleTextView {
        private final Paint paint;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(Context r1, Theme.ResourcesProvider r2) {
            this.val$resourcesProvider = r2;
            super(r1);
            this.paint = new Paint(1);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r10) {
            this.paint.setColor(Theme.getColor(Theme.key_dialogGrayLine, this.val$resourcesProvider));
            this.paint.setStyle(Paint.Style.STROKE);
            this.paint.setStrokeWidth(1.0f);
            float r4 = getHeight() / 2.0f;
            r10.drawLine(0.0f, r4, ((getWidth() / 2.0f) - (getTextWidth() / 2.0f)) - AndroidUtilities.dp(8.0f), r4, this.paint);
            r10.drawLine(((getWidth() / 2.0f) + (getTextWidth() / 2.0f)) + AndroidUtilities.dp(8.0f), r4, getWidth(), r4, this.paint);
            super.dispatchDraw(r10);
        }
    }

    /* renamed from: $r8$lambda$1onCj4pvO6L8Hb9bbMPAmbQK-A4, reason: not valid java name */
    public static /* synthetic */ void m11294$r8$lambda$1onCj4pvO6L8Hb9bbMPAmbQKA4(ButtonWithCounterView r6, boolean r7, int r8, BottomSheet r9, Runnable r10, Context r11, Theme.ResourcesProvider r12, View r13) {
        r6.setLoading(true);
        if (r7 == false) goto L6;
        TL_account.setPrivacy r72 = new TL_account.setPrivacy();
        r72.key = new TLRPC.TL_inputPrivacyKeyStatusTimestamp();
        r72.rules.add(new TLRPC.TL_inputPrivacyValueAllowAll());
        ConnectionsManager.getInstance(r8).sendRequest(r72, new MessagePrivateSeenView$$ExternalSyntheticLambda2(r6, r9, r10));
        return;
    L6:
        TL_account.setGlobalPrivacySettings r73 = new TL_account.setGlobalPrivacySettings();
        TLRPC.GlobalPrivacySettings r132 = ContactsController.getInstance(r8).getGlobalPrivacySettings();
        r73.settings = r132;
        if (r132 != null) goto L9;
        r73.settings = new TLRPC.TL_globalPrivacySettings();
    L9:
        r73.settings.hide_read_marks = false;
        ConnectionsManager.getInstance(r8).sendRequest(r73, new MessagePrivateSeenView$$ExternalSyntheticLambda3(r11, r12, r6, r9, r10));
    }

    public static /* synthetic */ void $r8$lambda$6VkjybRO9MqmC2d3jzpplZhufnY(boolean r1, BottomSheet r2, Runnable r3, View r4) {
        BaseFragment r42 = LaunchActivity.getLastFragment();
        if (r42 != null) goto L5;
        return;
    L5:
        if (r1 == false) goto L7;
        String r12 = "lastseen";
    L8:
        r42.presentFragment(new PremiumPreviewFragment(r12));
        r2.dismiss();
        if (r3 == null) goto L13;
        r3.run();
        return;
    L13:
        return;
    L7:
        r12 = "readtime";
        goto L8
    }

    public static /* synthetic */ void $r8$lambda$7SzQMeODf1mM0uEfnn6a1cRtd24(ButtonWithCounterView r0, BottomSheet r1, Runnable r2, TLObject r3, TLRPC.TL_error r4) {
        AndroidUtilities.runOnUIThread(new MessagePrivateSeenView$$ExternalSyntheticLambda6(r4, r0, r1, r2));
    }

    public static /* synthetic */ void $r8$lambda$F4wqKx8CaXEC400kn9LeyNuRTsU(MessagePrivateSeenView r0, TLObject r1, TLRPC.TL_error r2) {
        r0.lambda$request$2(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$PqUJuMHIZzyOtdJxkvzvEg5JlJ4(MessagePrivateSeenView r0) {
        r0.request();
    }

    /* renamed from: $r8$lambda$SSHINyneUjnW8xw5zjHuA-ISMrU, reason: not valid java name */
    public static /* synthetic */ void m11295$r8$lambda$SSHINyneUjnW8xw5zjHuAISMrU(MessagePrivateSeenView r0, View r1) {
        r0.lambda$request$0(r1);
    }

    /* renamed from: $r8$lambda$guwLb45BisSIq2eY_xOdk0X5n-Y, reason: not valid java name */
    public static /* synthetic */ void m11296$r8$lambda$guwLb45BisSIq2eY_xOdk0X5nY(TLRPC.TL_error r0, ButtonWithCounterView r1, BottomSheet r2, Runnable r3) {
        if (r0 == null) goto L5;
        BulletinFactory.global().showForError(r0);
        return;
    L5:
        r1.setLoading(false);
        r2.dismiss();
        BulletinFactory.global().createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString(R.string.PremiumLastSeenSet)).show();
        if (r3 == null) goto L9;
        r3.run();
        return;
    }

    /* renamed from: $r8$lambda$jHYwNYYn0fwy3WW39eS9NTIab-g, reason: not valid java name */
    public static /* synthetic */ void m11297$r8$lambda$jHYwNYYn0fwy3WW39eS9NTIabg(Context r0, Theme.ResourcesProvider r1, ButtonWithCounterView r2, BottomSheet r3, Runnable r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new MessagePrivateSeenView$$ExternalSyntheticLambda4(r6, r0, r1, r2, r3, r4));
    }

    /* renamed from: $r8$lambda$mFKiuqa7A-9qEAjUuAbSEUUzIag, reason: not valid java name */
    public static /* synthetic */ void m11298$r8$lambda$mFKiuqa7A9qEAjUuAbSEUUzIag(TLRPC.TL_error r0, Context r1, Theme.ResourcesProvider r2, ButtonWithCounterView r3, BottomSheet r4, Runnable r5) {
        if (r0 == null) goto L5;
        BulletinFactory.of(Bulletin.BulletinWindow.make(r1), r2).showForError(r0);
        return;
    L5:
        r3.setLoading(false);
        r4.dismiss();
        BulletinFactory.of(Bulletin.BulletinWindow.make(r1), r2).createSimpleBulletin(R.raw.chats_infotip, LocaleController.getString(R.string.PremiumReadSet)).show();
        if (r5 == null) goto L9;
        r5.run();
        return;
    }

    public static /* synthetic */ void $r8$lambda$sZabJSPRIkJtSwn2KBKRtuKdOic(MessagePrivateSeenView r0, TLRPC.TL_error r1, TLObject r2) {
        r0.lambda$request$1(r1, r2);
    }

    public MessagePrivateSeenView(Context r18, int r19, MessageObject r20, Runnable r21, Theme.ResourcesProvider r22) {
        super(r18);
        this.isPremiumLocked = false;
        this.minWidth = -1.0f;
        this.type = r19;
        int r6 = r20.currentAccount;
        this.currentAccount = r6;
        this.resourcesProvider = r22;
        this.dismiss = r21;
        this.messageDiff = ConnectionsManager.getInstance(r6).getCurrentTime() - r20.messageOwner.date;
        this.dialogId = r20.getDialogId();
        this.messageId = r20.getId();
        TLRPC.Message r7 = r20.messageOwner;
        if (r7 != null) goto L5;
        int r8 = 0;
    L6:
        this.sent_date = r8;
        if (r7 != null) goto L9;
        int r82 = 0;
    L10:
        this.edit_date = r82;
        if (r7 == null) goto L16;
        TLRPC.MessageFwdHeader r72 = r7.fwd_from;
        if (r72 == null) goto L16;
        int r73 = r72.date;
    L17:
        this.fwd_date = r73;
        ImageView r74 = new ImageView(r18);
        addView(r74, LayoutHelper.createFrame(24, 24.0f, 19, 11.0f, 0.0f, 0.0f, 0.0f));
        if (r19 != 1) goto L24;
        if (AppGlobalConfig.getInstance(r6).messagePrimaryEditedDate.get() == false) goto L22;
        int r2 = R.drawable.outline_message_time_24;
    L30:
        Drawable r23 = ContextCompat.getDrawable(r18, r2).mutate();
        r23.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_actionBarDefaultSubmenuItemIcon, r22), PorterDuff.Mode.MULTIPLY));
        r74.setImageDrawable(r23);
        TextView r24 = new TextView(r18);
        this.loadingView = r24;
        SpannableStringBuilder r3 = new SpannableStringBuilder("loading text ");
        r3.setSpan(new LoadingSpan(r24, AndroidUtilities.dp(96.0f), AndroidUtilities.dp(2.0f), r22), 0, r3.length() - 1, 17);
        int r62 = Theme.key_dialogTextBlack;
        r24.setTextColor(Theme.multAlpha(Theme.getColor(r62, r22), 0.7f));
        r24.setText(r3);
        r24.setTextSize(1, 13.0f);
        addView(r24, LayoutHelper.createFrame(96, -2.0f, 19, 40.0f, -1.0f, 8.0f, 0.0f));
        LinearLayout r25 = new LinearLayout(r18);
        this.valueLayout = r25;
        r25.setOrientation(0);
        r25.setAlpha(0.0f);
        addView(r25, LayoutHelper.createFrame(-1, -2.0f, 19, 38.0f, 0.0f, 8.0f, 0.0f));
        TextView r32 = new TextView(r18);
        this.valueTextView = r32;
        r32.setTextColor(Theme.getColor(r62, r22));
        r32.setTextSize(1, 14.0f);
        r25.addView(r32, LayoutHelper.createLinear(-2, -2, 19, 0, -1, 0, 0));
        TextView r33 = new TextView(r18);
        this.premiumTextView = r33;
        r33.setBackground(Theme.createRoundRectDrawable(AndroidUtilities.dp(20.0f), Theme.multAlpha(Theme.getColor(Theme.key_divider, r22), 0.75f)));
        r33.setTextColor(Theme.getColor(r62, r22));
        r33.setTextSize(1, 11.0f);
        r33.setPadding(AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.0f), AndroidUtilities.dp(5.33f), AndroidUtilities.dp(2.33f));
        r25.addView(r33, LayoutHelper.createLinear(-2, -2, 19, 4, 0, 0, 0));
        request();
        return;
    L22:
        r2 = R.drawable.menu_edited_stamp;
        goto L30
    L24:
        if (r19 != 2) goto L27;
        r2 = R.drawable.menu_forward_stamp;
        goto L30
    L27:
        if (r20.isVoice() == false) goto L29;
        r2 = R.drawable.msg_played;
        goto L30
    L29:
        r2 = R.drawable.msg_seen;
    L16:
        r73 = 0;
        goto L17
    L9:
        r82 = r7.edit_date;
        goto L10
    L5:
        r8 = r7.date;
        goto L6
    }

    private /* synthetic */ void lambda$request$0(View r9) {
        showSheet(getContext(), this.currentAccount, this.dialogId, false, this.dismiss, new MessagePrivateSeenView$$ExternalSyntheticLambda9(this), this.resourcesProvider);
    }

    private /* synthetic */ void lambda$request$1(TLRPC.TL_error r4, TLObject r5) {
        if (r4 == null) goto L12;
        if ("USER_PRIVACY_RESTRICTED".equals(r4.text) == false) goto L8;
        this.valueTextView.setText(LocaleController.getString(R.string.PmReadUnknown));
        this.premiumTextView.setVisibility(8);
    L14:
        ViewPropertyAnimator r42 = this.valueLayout.animate().alpha(1.0f);
        CubicBezierInterpolator r52 = CubicBezierInterpolator.EASE_OUT_QUINT;
        r42.setInterpolator(r52).setDuration(320).start();
        this.loadingView.animate().alpha(0.0f).setInterpolator(r52).setDuration(320).start();
        if (this.isPremiumLocked == false) goto L18;
        setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 6, 0));
        setOnClickListener(new MessagePrivateSeenView$$ExternalSyntheticLambda8(this));
        return;
    L18:
        setBackground(null);
        setOnClickListener(null);
        return;
    L8:
        if ("YOUR_PRIVACY_RESTRICTED".equals(r4.text) == false) goto L10;
        this.isPremiumLocked = true;
        this.valueTextView.setText(LocaleController.getString(R.string.PmRead));
        this.premiumTextView.setText(LocaleController.getString(R.string.PmReadShowWhen));
        goto L14
    L10:
        this.valueTextView.setText(LocaleController.getString("UnknownError"));
        this.premiumTextView.setVisibility(8);
        BulletinFactory.of(Bulletin.BulletinWindow.make(getContext()), this.resourcesProvider).showForError(r4);
        goto L14
    L12:
        if ((r5 instanceof TLRPC.TL_outboxReadDate) == false) goto L14;
        this.valueTextView.setText(LocaleController.formatPmSeenDate(((TLRPC.TL_outboxReadDate) r5).date));
        this.premiumTextView.setVisibility(8);
        goto L14
    }

    private /* synthetic */ void lambda$request$2(TLObject r2, TLRPC.TL_error r3) {
        AndroidUtilities.runOnUIThread(new MessagePrivateSeenView$$ExternalSyntheticLambda7(this, r3, r2));
    }

    private void request() {
        int r0 = this.type;
        if (r0 != 1) goto L11;
        this.valueLayout.setAlpha(1.0f);
        this.loadingView.setAlpha(0.0f);
        this.premiumTextView.setVisibility(8);
        TextView r02 = this.valueTextView;
        if (AppGlobalConfig.getInstance(this.currentAccount).messagePrimaryEditedDate.get() == false) goto L7;
        String r5 = LocaleController.formatPmSentDate(this.sent_date);
    L8:
        r02.setText(r5);
        return;
    L7:
        r5 = LocaleController.formatPmEditedDate(this.edit_date);
        goto L8
    L11:
        if (r0 != 2) goto L14;
        this.valueLayout.setAlpha(1.0f);
        this.loadingView.setAlpha(0.0f);
        this.premiumTextView.setVisibility(8);
        this.valueTextView.setText(LocaleController.formatPmFwdDate(this.fwd_date));
        return;
    L14:
        setOnClickListener(null);
        this.valueLayout.setAlpha(0.0f);
        this.loadingView.setAlpha(1.0f);
        this.premiumTextView.setVisibility(0);
        TLRPC.TL_messages_getOutboxReadDate r03 = new TLRPC.TL_messages_getOutboxReadDate();
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(this.dialogId);
        r03.msg_id = this.messageId;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r03, new MessagePrivateSeenView$$ExternalSyntheticLambda5(this));
    }

    public static void showSheet(Context r24, int r25, long r26, boolean r28, Runnable r29, Runnable r30, Theme.ResourcesProvider r31) {
        BottomSheet r4 = new BottomSheet(r24, false, r31);
        r4.fixNavigationBar(Theme.getColor(Theme.key_dialogBackground, r31));
        boolean r9 = MessagesController.getInstance(r25).premiumFeaturesBlocked();
        LinearLayout r10 = new LinearLayout(r24);
        r10.setOrientation(1);
        r10.setPadding(AndroidUtilities.dp(16.0f), 0, AndroidUtilities.dp(16.0f), 0);
        RLottieImageView r0 = new RLottieImageView(r24);
        r0.setScaleType(ImageView.ScaleType.CENTER);
        if (r28 == false) goto L5;
        int r1 = R.raw.large_lastseen;
    L6:
        r0.setAnimation(r1, 70, 70);
        r0.playAnimation();
        r0.setColorFilter(new PorterDuffColorFilter(-1, PorterDuff.Mode.SRC_IN));
        r0.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(80.0f), Theme.getColor(Theme.key_featuredStickers_addButton, r31)));
        r10.addView(r0, LayoutHelper.createLinear(80, 80, 1, 0, 16, 0, 16));
        TextView r02 = new TextView(r24);
        r02.setTypeface(AndroidUtilities.bold());
        r02.setGravity(17);
        int r13 = Theme.key_dialogTextBlack;
        r02.setTextColor(Theme.getColor(r13, r31));
        r02.setTextSize(1, 20.0f);
        if (r28 == false) goto L9;
        int r12 = R.string.PremiumLastSeenHeader1;
    L10:
        r02.setText(LocaleController.getString(r12));
        r10.addView(r02, LayoutHelper.createLinear(-1, -2, 1, 12, 0, 12, 0));
        TextView r03 = new TextView(r24);
        r03.setGravity(17);
        r03.setTextColor(Theme.getColor(r13, r31));
        r03.setTextSize(1, 14.0f);
        if (r26 <= 0) goto L14;
        String r14 = UserObject.getFirstName(MessagesController.getInstance(r25).getUser(Long.valueOf(r26)));
    L13:
        String r16 = r14;
        if (r28 == false) goto L19;
        if (r9 == false) goto L18;
        int r15 = R.string.PremiumLastSeenText1Locked;
    L22:
        r03.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r15, new Object[]{r16})));
        r10.addView(r03, LayoutHelper.createLinear(-1, -2, 1, 32, 9, 32, 19));
        ButtonWithCounterView r17 = new ButtonWithCounterView(r24, r31).setRound();
        if (r28 == false) goto L25;
        int r04 = R.string.PremiumLastSeenButton1;
    L26:
        r17.setText(LocaleController.getString(r04), false);
        r10.addView(r17, LayoutHelper.createLinear(-1, 48, 1));
        r17.setOnClickListener(new MessagePrivateSeenView$$ExternalSyntheticLambda0(r17, r28, r25, r4, r30, r24, r31));
        if (r9 == true) goto L41;
        AnonymousClass1 r05 = new AnonymousClass1(r24, r31);
        r05.setGravity(17);
        r05.setAlignment(Layout.Alignment.ALIGN_CENTER);
        r05.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2, r31));
        r05.setText(" " + LocaleController.getString(R.string.PremiumOr) + " ");
        r05.setTextSize(14);
        r10.addView(r05, LayoutHelper.createLinear(270, -2, 1, 12, 17, 12, 17));
        TextView r06 = new TextView(r24);
        r06.setTypeface(AndroidUtilities.bold());
        r06.setGravity(17);
        r06.setTextColor(Theme.getColor(r13, r31));
        r06.setTextSize(1, 20.0f);
        if (r28 == false) goto L31;
        int r18 = R.string.PremiumLastSeenHeader2;
    L32:
        r06.setText(LocaleController.getString(r18));
        r10.addView(r06, LayoutHelper.createLinear(-1, -2, 1, 12, 0, 12, 0));
        TextView r07 = new TextView(r24);
        r07.setGravity(17);
        r07.setTextColor(Theme.getColor(r13, r31));
        r07.setTextSize(1, 14.0f);
        if (r28 == false) goto L35;
        int r19 = R.string.PremiumLastSeenText2;
    L36:
        r07.setText(AndroidUtilities.replaceTags(LocaleController.formatString(r19, new Object[]{r16})));
        r10.addView(r07, LayoutHelper.createLinear(-1, -2, 1, 32, 9, 32, 19));
        PremiumButtonView r08 = new PremiumButtonView(r24, true, r31);
        r08.setOnClickListener(new MessagePrivateSeenView$$ExternalSyntheticLambda1(r28, r4, r29));
        if (r28 == false) goto L39;
        int r110 = R.string.PremiumLastSeenButton2;
    L40:
        r08.setOverlayText(LocaleController.getString(r110), false, false);
        r10.addView(r08, LayoutHelper.createLinear(-1, 48, 1, 0, 0, 0, 4));
        goto L41
    L39:
        r110 = R.string.PremiumReadButton2;
        goto L40
    L35:
        r19 = R.string.PremiumReadText2;
        goto L36
    L31:
        r18 = R.string.PremiumReadHeader2;
    L41:
        r4.setCustomView(r10);
        r4.show();
        return;
    L25:
        r04 = R.string.PremiumReadButton1;
        goto L26
    L18:
        r15 = R.string.PremiumLastSeenText1;
        goto L22
    L19:
        if (r9 == false) goto L21;
        r15 = R.string.PremiumReadText1Locked;
        goto L22
    L21:
        r15 = R.string.PremiumReadText1;
        goto L22
    L14:
        r14 = _UrlKt.FRAGMENT_ENCODE_SET;
        goto L13
    L9:
        r12 = R.string.PremiumReadHeader1;
        goto L10
    L5:
        r1 = R.raw.large_readtime;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (Bulletin.getVisibleBulletin() == null) goto L12;
        Bulletin r1 = Bulletin.getVisibleBulletin();
        if (r1.getLayout() != null) goto L7;
        return;
    L7:
        if (r1.getLayout().getParent() != null) goto L9;
        return;
    L9:
        if ((r1.getLayout().getParent().getParent() instanceof Bulletin.BulletinWindow.BulletinWindowLayout) == false) goto L15;
        r1.hide();
        return;
    L15:
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r13, int r14) {
        View r0 = (View) getParent();
        int r1 = View.MeasureSpec.getSize(r13);
        int r132 = View.MeasureSpec.getMode(r13);
        if (this.minWidth >= 0.0f) goto L13;
        this.minWidth = 0.0f;
        if (this.type != 0) goto L12;
        long r4 = System.currentTimeMillis();
        float r2 = Math.max(this.minWidth, AndroidUtilities.dp(144.0f));
        this.minWidth = r2;
        float r22 = Math.max(r2, AndroidUtilities.dp(48.0f) + this.valueTextView.getPaint().measureText(LocaleController.getString(R.string.PmReadUnknown)));
        this.minWidth = r22;
        float r23 = Math.max(r22, AndroidUtilities.dp(64.0f) + this.valueTextView.getPaint().measureText(LocaleController.getString(R.string.PmRead) + this.premiumTextView.getPaint().measureText(LocaleController.getString(R.string.PmReadShowWhen))));
        this.minWidth = r23;
        float r24 = Math.max(r23, ((float) AndroidUtilities.dp(48.0f)) + this.valueTextView.getPaint().measureText(LocaleController.formatString(R.string.PmReadTodayAt, new Object[]{LocaleController.getInstance().getFormatterDay().format(new Date(r4))})));
        this.minWidth = r24;
        if (this.messageDiff <= 86400) goto L10;
        this.minWidth = Math.max(r24, AndroidUtilities.dp(48.0f) + this.valueTextView.getPaint().measureText(LocaleController.formatString(R.string.PmReadYesterdayAt, new Object[]{LocaleController.getInstance().getFormatterDay().format(new Date(r4))})));
    L10:
        if (this.messageDiff <= 172800) goto L13;
        float r25 = Math.max(this.minWidth, AndroidUtilities.dp(48.0f) + this.valueTextView.getPaint().measureText(LocaleController.formatString(R.string.PmReadDateTimeAt, new Object[]{LocaleController.getInstance().getFormatterDayMonth().format(new Date(r4)), LocaleController.getInstance().getFormatterDay().format(new Date(r4))})));
        this.minWidth = r25;
        this.minWidth = Math.max(r25, AndroidUtilities.dp(48.0f) + this.valueTextView.getPaint().measureText(LocaleController.formatString(R.string.PmReadDateTimeAt, new Object[]{LocaleController.getInstance().getFormatterYear().format(new Date(r4)), LocaleController.getInstance().getFormatterDay().format(new Date(r4))})));
        goto L13
    L12:
        this.minWidth = AndroidUtilities.dp(48.0f) + this.valueTextView.getPaint().measureText(this.valueTextView.getText().toString());
    L13:
        int r26 = TLObject.FLAG_30;
        if (r0 != null) goto L16;
    L18:
        float r02 = r1;
        float r3 = this.minWidth;
        if (r02 >= r3) goto L21;
    L24:
        r1 = (int) r3;
    L25:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(r1, r26), r14);
        return;
    L21:
        if (r132 == Integer.MIN_VALUE) goto L24;
        r26 = r132;
        goto L25
    L16:
        if (r0.getWidth() <= 0) goto L18;
        r1 = r0.getWidth();
        r132 = 1073741824;
        goto L18
    }
}
