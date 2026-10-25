package org.telegram.ui.Components.Premium.boosts.cells.selector;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.view.ViewPropertyAnimator;
import android.widget.ImageView;
import com.google.android.material.timepicker.TimeModel;
import java.util.Date;
import kotlin.time.DurationKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.Premium.boosts.cells.BaseCell;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.StatusBadgeComponent;
import org.telegram.ui.Components.UItem;
import org.telegram.ui.Components.UniversalAdapter;
import org.telegram.ui.Components.UniversalRecyclerView;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class SelectorUserCell extends BaseCell {
    private final ImageView audioView;
    private TL_stories.TL_myBoost boost;
    private TLRPC.Chat chat;
    private final CheckBox2 checkBox;
    private boolean hasAudioView;
    private boolean hasVideoView;
    private final boolean[] isOnline;
    private final ImageView optionsView;
    private boolean showCallButtons;
    StatusBadgeComponent statusBadgeComponent;
    private TLRPC.User user;
    private final ImageView videoView;

    public static class Factory extends UItem.UItemFactory<SelectorUserCell> {
        static {
            UItem.UItemFactory.setup(new Factory());
        }

        public Factory() {
        }

        public static UItem make(TLRPC.User r1) {
            UItem r0 = UItem.ofFactory(Factory.class);
            r0.object = r1;
            return r0;
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public void bindView(View r1, UItem r2, boolean r3, UniversalAdapter r4, UniversalRecyclerView r5) {
            SelectorUserCell r12 = (SelectorUserCell) r1;
            r12.setUser((TLRPC.User) r2.object);
            r12.setChecked(r2.checked, false);
            r12.setDivider(r3);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public /* bridge */ /* synthetic */ View createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return createView(r1, r2, r3, r4, r5);
        }

        @Override // org.telegram.ui.Components.UItem.UItemFactory
        public SelectorUserCell createView(Context r1, RecyclerListView r2, int r3, int r4, Theme.ResourcesProvider r5) {
            return new SelectorUserCell(r1, true, false, r5, false);
        }
    }

    public static /* synthetic */ void $r8$lambda$6JVy2EEviu6DoWvSbePookdVXnk(SelectorUserCell r0) {
        r0.lambda$setCallButtonsVisible$1();
    }

    /* renamed from: $r8$lambda$8aALk_YeQEmgal1IUAkjb4t1A-c, reason: not valid java name */
    public static /* synthetic */ void m12194$r8$lambda$8aALk_YeQEmgal1IUAkjb4t1Ac(SelectorUserCell r0) {
        r0.lambda$setCallButtonsVisible$0();
    }

    public SelectorUserCell(Context r7, boolean r8, Theme.ResourcesProvider r9, boolean r10) {
        this(r7, r8, false, r9, r10);
    }

    public SelectorUserCell(Context r20, boolean r21, boolean r22, Theme.ResourcesProvider r23, boolean r24) {
        super(r20, r23);
        this.isOnline = new boolean[1];
        this.showCallButtons = true;
        this.statusBadgeComponent = new StatusBadgeComponent(this);
        this.titleTextView.setTypeface(AndroidUtilities.bold());
        this.radioButton.setVisibility(8);
        int r7 = 5;
        if (r22 == false) goto L15;
        CheckBox2 r3 = new CheckBox2(r20, 21, r23);
        this.checkBox = r3;
        r3.setColor(Theme.key_dialogRoundCheckBox, Theme.key_dialogBackground, Theme.key_checkboxCheck);
        r3.setDrawUnchecked(false);
        r3.setDrawBackgroundAsArc(3);
        boolean r4 = LocaleController.isRTL;
        if (r4 == false) goto L7;
        int r6 = 5;
    L8:
        int r11 = r6 | 48;
        float r62 = 0.0f;
        if (r4 == false) goto L11;
        float r12 = 0.0f;
    L12:
        if (r4 == false) goto L14;
        r62 = 39.0f;
    L14:
        addView(r3, LayoutHelper.createFrame(24, 24.0f, r11, r12, 33.0f, r62, 0.0f));
        updateLayouts();
    L26:
        ImageView r32 = new ImageView(r20);
        this.optionsView = r32;
        ImageView.ScaleType r42 = ImageView.ScaleType.CENTER;
        r32.setScaleType(r42);
        r32.setImageResource(R.drawable.ic_ab_other);
        int r9 = Theme.getColor(Theme.key_chat_inMenu, r23);
        PorterDuff.Mode r10 = PorterDuff.Mode.SRC_IN;
        r32.setColorFilter(new PorterDuffColorFilter(r9, r10));
        if (LocaleController.isRTL == false) goto L29;
        int r63 = 3;
    L30:
        addView(r32, LayoutHelper.createFrame(32, 32.0f, r63 | 16, 12.0f, 0.0f, 12.0f, 0.0f));
        ImageView r33 = new ImageView(r20);
        this.audioView = r33;
        r33.setScaleType(r42);
        r33.setImageResource(R.drawable.menu_phone);
        int r92 = Theme.key_featuredStickers_addButton;
        r33.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r92, r23), r10));
        boolean r64 = LocaleController.isRTL;
        if (r64 == false) goto L33;
        int r112 = 3;
    L34:
        int r14 = r112 | 16;
        if (r64 == false) goto L37;
        float r15 = 52.0f;
    L38:
        if (r64 == false) goto L40;
        float r17 = 12.0f;
    L41:
        addView(r33, LayoutHelper.createFrame(32, 32.0f, r14, r15, 0.0f, r17, 0.0f));
        r33.setVisibility(8);
        ImageView r34 = new ImageView(r20);
        this.videoView = r34;
        r34.setScaleType(r42);
        r34.setImageResource(R.drawable.menu_videocall);
        r34.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r92, r23), r10));
        if (LocaleController.isRTL == false) goto L44;
        r7 = 3;
    L44:
        addView(r34, LayoutHelper.createFrame(32, 32.0f, r7 | 16, 12.0f, 0.0f, 12.0f, 0.0f));
        r34.setVisibility(8);
        return;
    L40:
        r17 = 52.0f;
        goto L41
    L37:
        r15 = 12.0f;
        goto L38
    L33:
        r112 = 5;
        goto L34
    L29:
        r63 = 5;
        goto L30
    L11:
        r12 = 40.0f;
        goto L12
    L7:
        r6 = 3;
        goto L8
    L15:
        if (r21 == false) goto L25;
        CheckBox2 r93 = new CheckBox2(r20, 21, r23);
        this.checkBox = r93;
        if (r24 == false) goto L19;
        r93.setColor(Theme.key_checkbox, Theme.key_checkboxDisabled, Theme.key_dialogRoundCheckBoxCheck);
    L20:
        r93.setDrawUnchecked(true);
        r93.setDrawBackgroundAsArc(10);
        addView(r93);
        r93.setChecked(false, false);
        if (LocaleController.isRTL == false) goto L23;
        int r35 = 5;
    L24:
        r93.setLayoutParams(LayoutHelper.createFrame(24, 24.0f, r35 | 16, 13.0f, 0.0f, 14.0f, 0.0f));
        updateLayouts();
        goto L26
    L23:
        r35 = 3;
        goto L24
    L19:
        r93.setColor(Theme.key_dialogRoundCheckBox, Theme.key_checkboxDisabled, Theme.key_dialogRoundCheckBoxCheck);
        goto L20
    L25:
        this.checkBox = null;
        goto L26
    }

    public static String buildCountDownTime(long r8) {
        long r2 = r8 / DurationKt.MILLIS_IN_HOUR;
        long r82 = r8 % DurationKt.MILLIS_IN_HOUR;
        long r4 = r82 / 60000;
        long r83 = (r82 % 60000) / 1000;
        StringBuilder r0 = new StringBuilder();
        if (r2 <= 0) goto L5;
        r0.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(r2)}));
        r0.append(":");
    L5:
        r0.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(r4)}));
        r0.append(":");
        r0.append(String.format(TimeModel.ZERO_LEADING_NUMBER_FORMAT, new Object[]{Long.valueOf(r83)}));
        return r0.toString();
    }

    private /* synthetic */ void lambda$setCallButtonsVisible$0() {
        this.audioView.setVisibility(8);
    }

    private /* synthetic */ void lambda$setCallButtonsVisible$1() {
        this.videoView.setVisibility(8);
    }

    public TL_stories.TL_myBoost getBoost() {
        return this.boost;
    }

    public TLRPC.Chat getChat() {
        return this.chat;
    }

    public TLRPC.User getUser() {
        return this.user;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public boolean needCheck() {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L5;
        return false;
    L5:
        if (r0.getDrawUnchecked() == false) goto L10;
        return true;
    L10:
        return false;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        this.statusBadgeComponent.onAttachedToWindow();
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        this.statusBadgeComponent.onDetachedFromWindow();
    }

    public void setBoost(TL_stories.TL_myBoost r8) {
        this.optionsView.setVisibility(8);
        this.boost = r8;
        TLRPC.Chat r0 = MessagesController.getInstance(UserConfig.selectedAccount).getChat(Long.valueOf(-DialogObject.getPeerDialogId(r8.peer)));
        this.chat = r0;
        this.avatarDrawable.setInfo(r0);
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        this.imageView.setForUserOrChat(this.chat, this.avatarDrawable);
        this.titleTextView.setText(this.chat.title);
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        setSubtitle(LocaleController.formatString(R.string.BoostExpireOn, new Object[]{LocaleController.getInstance().getFormatterBoostExpired().format(new Date(r8.expires * 1000))}));
        int r82 = r8.cooldown_until_date;
        if (r82 <= 0) goto L6;
        setSubtitle(LocaleController.formatString(R.string.BoostingAvailableIn, new Object[]{buildCountDownTime((r82 * 1000) - System.currentTimeMillis())}));
        this.titleTextView.setAlpha(0.65f);
        this.subtitleTextView.setAlpha(0.65f);
        setCheckboxAlpha(0.3f, false);
        return;
    L6:
        this.titleTextView.setAlpha(1.0f);
        this.subtitleTextView.setAlpha(1.0f);
        setCheckboxAlpha(1.0f, false);
    }

    public void setCallButtons(View.OnClickListener r6, View.OnClickListener r7) {
        boolean r0 = true;
        int r1 = 0;
        if (r6 == null) goto L5;
        boolean r2 = true;
    L6:
        this.hasAudioView = r2;
        ImageView r3 = this.audioView;
        if (r2 == true) goto L9;
    L11:
        int r22 = 8;
    L12:
        r3.setVisibility(r22);
        this.audioView.setOnClickListener(r6);
        if (r7 != null) goto L16;
        r0 = false;
    L16:
        this.hasVideoView = r0;
        ImageView r62 = this.videoView;
        if (r0 == true) goto L19;
    L21:
        r1 = 8;
    L22:
        r62.setVisibility(r1);
        this.videoView.setOnClickListener(r7);
        return;
    L19:
        if (this.showCallButtons == false) goto L21;
    L9:
        if (this.showCallButtons == false) goto L11;
        r22 = 0;
        goto L12
    L5:
        r2 = false;
        goto L6
    }

    public void setCallButtonsVisible(boolean r6, boolean r7) {
        if (this.showCallButtons != r6) goto L5;
        return;
    L5:
        this.showCallButtons = r6;
        ImageView r0 = this.audioView;
        float r1 = 0.0f;
        int r3 = 0;
        if (r7 == false) goto L32;
        r0.setVisibility(0);
        ViewPropertyAnimator r72 = this.audioView.animate();
        if (r6 == true) goto L10;
    L12:
        float r02 = 0.0f;
    L13:
        ViewPropertyAnimator r73 = r72.alpha(r02);
        SelectorUserCell$$ExternalSyntheticLambda1 r03 = null;
        if (r6 == true) goto L16;
    L19:
        SelectorUserCell$$ExternalSyntheticLambda0 r4 = new SelectorUserCell$$ExternalSyntheticLambda0(this);
    L20:
        r73.withEndAction(r4).start();
        this.videoView.setVisibility(0);
        ViewPropertyAnimator r74 = this.videoView.animate();
        if (r6 == true) goto L23;
    L25:
        ViewPropertyAnimator r75 = r74.alpha(r1);
        if (r6 == true) goto L28;
    L29:
        r03 = new SelectorUserCell$$ExternalSyntheticLambda1(this);
    L30:
        r75.withEndAction(r03).start();
        return;
    L28:
        if (this.hasVideoView == true) goto L30;
    L23:
        if (this.hasVideoView == false) goto L25;
        r1 = 1.0f;
        goto L25
    L16:
        if (this.hasAudioView == false) goto L19;
        r4 = null;
        goto L20
    L10:
        if (this.hasAudioView == false) goto L12;
        r02 = 1.0f;
        goto L13
    L32:
        r0.animate().cancel();
        ImageView r76 = this.audioView;
        if (r6 == true) goto L35;
    L37:
        float r04 = 0.0f;
    L38:
        r76.setAlpha(r04);
        ImageView r77 = this.audioView;
        if (r6 == true) goto L41;
    L43:
        int r42 = 8;
    L44:
        r77.setVisibility(r42);
        this.videoView.animate().cancel();
        ImageView r78 = this.videoView;
        if (r6 == true) goto L47;
    L49:
        r78.setAlpha(r1);
        ImageView r79 = this.videoView;
        if (r6 == true) goto L52;
    L54:
        r3 = 8;
    L55:
        r79.setVisibility(r3);
        return;
    L52:
        if (this.hasVideoView == false) goto L54;
    L47:
        if (this.hasVideoView == false) goto L49;
        r1 = 1.0f;
        goto L49
    L41:
        if (this.hasAudioView == false) goto L43;
        r42 = 0;
        goto L44
    L35:
        if (this.hasAudioView == false) goto L37;
        r04 = 1.0f;
        goto L38
    }

    public void setChat(TLRPC.Chat r4, int r5) {
        this.optionsView.setVisibility(8);
        this.chat = r4;
        this.user = null;
        this.avatarDrawable.setInfo(r4);
        BackupImageView r1 = this.imageView;
        if (ChatObject.isForum(r4) == false) goto L5;
        float r2 = 12.0f;
    L6:
        r1.setRoundRadius(AndroidUtilities.dp(r2));
        this.imageView.setForUserOrChat(r4, this.avatarDrawable);
        this.titleTextView.setText(r4.title);
        this.titleTextView.setRightDrawable(null);
        if (r5 > 0) goto L9;
        r5 = r4.participants_count;
    L9:
        boolean r42 = ChatObject.isChannelAndNotMegaGroup(r4);
        if (r5 < 1) goto L15;
        if (r42 == false) goto L13;
        String r43 = "Subscribers";
    L14:
        String r44 = LocaleController.formatPluralString(r43, r5, new Object[0]);
    L19:
        setSubtitle(r44);
        this.subtitleTextView.setTextColor(Theme.getColor(Theme.key_dialogTextGray3, this.resourcesProvider));
        if (r5 <= 200) goto L22;
        float r45 = 0.3f;
    L23:
        setCheckboxAlpha(r45, false);
        return;
    L22:
        r45 = 1.0f;
        goto L23
    L13:
        r43 = "Members";
        goto L14
    L15:
        if (r42 == false) goto L17;
        int r46 = R.string.DiscussChannel;
    L18:
        r44 = LocaleController.getString(r46);
        goto L19
    L17:
        r46 = R.string.AccDescrGroup;
        goto L18
    L5:
        r2 = 20.0f;
        goto L6
    }

    public void setCheckboxAlpha(float r5, boolean r6) {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L13;
        if (r6 == true) goto L7;
        r0.animate().cancel();
        this.checkBox.setAlpha(r5);
        return;
    L7:
        if (Math.abs(r0.getAlpha() - r5) <= 0.1d) goto L12;
        this.checkBox.animate().cancel();
        this.checkBox.animate().alpha(r5).start();
        return;
    L12:
        return;
    }

    @Override // org.telegram.ui.Components.Premium.boosts.cells.BaseCell
    public void setChecked(boolean r2, boolean r3) {
        CheckBox2 r0 = this.checkBox;
        if (r0 != null) goto L6;
        return;
    L6:
        if (r0.getVisibility() != 0) goto L9;
        this.checkBox.setChecked(r2, r3);
        return;
    }

    public void setCustomUser(Drawable r4, CharSequence r5, CharSequence r6) {
        this.optionsView.setVisibility(8);
        this.user = null;
        this.chat = null;
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        this.imageView.setImageDrawable(r4);
        this.titleTextView.setText(r5);
        this.isOnline[0] = false;
        setSubtitle(r6);
        SimpleTextView r42 = this.subtitleTextView;
        if (this.isOnline[0] == false) goto L5;
        int r52 = Theme.key_dialogTextBlue2;
    L6:
        r42.setTextColor(Theme.getColor(r52, this.resourcesProvider));
        CheckBox2 r43 = this.checkBox;
        if (r43 == null) goto L9;
        r43.setAlpha(1.0f);
    L9:
        this.titleTextView.setRightDrawable(null);
        return;
    L5:
        r52 = Theme.key_dialogTextGray3;
        goto L6
    }

    public void setOptions(View.OnClickListener r3) {
        ImageView r0 = this.optionsView;
        if (r3 == null) goto L6;
        r0.setVisibility(0);
        this.optionsView.setOnClickListener(r3);
        return;
    L6:
        r0.setVisibility(8);
    }

    public void setUser(TLRPC.User r5) {
        this.optionsView.setVisibility(8);
        this.user = r5;
        this.chat = null;
        this.avatarDrawable.setInfo(r5);
        this.imageView.setRoundRadius(AndroidUtilities.dp(20.0f));
        this.imageView.setForUserOrChat(r5, this.avatarDrawable);
        this.titleTextView.setText(UserObject.getUserName(r5));
        this.isOnline[0] = false;
        if (UserObject.isBot(r5) == false) goto L8;
        int r0 = r5.bot_active_users;
        if (r0 <= 0) goto L7;
        setSubtitle(LocaleController.formatPluralStringComma("BotUsers", r0, ','));
    L9:
        SimpleTextView r02 = this.subtitleTextView;
        if (this.isOnline[0] == false) goto L12;
        int r2 = Theme.key_dialogTextBlue2;
    L13:
        r02.setTextColor(Theme.getColor(r2, this.resourcesProvider));
        CheckBox2 r03 = this.checkBox;
        if (r03 == null) goto L16;
        r03.setAlpha(1.0f);
    L16:
        this.titleTextView.setRightDrawable(this.statusBadgeComponent.updateDrawable(r5, Theme.getColor(Theme.key_chats_verifiedBackground), false));
        return;
    L12:
        r2 = Theme.key_dialogTextGray3;
        goto L13
    L7:
        setSubtitle(LocaleController.getString(R.string.Bot));
        goto L9
    L8:
        setSubtitle(LocaleController.formatUserStatus(UserConfig.selectedAccount, r5, this.isOnline));
        goto L9
    }

    public void updateTimer() {
        int r0 = this.boost.cooldown_until_date;
        if (r0 <= 0) goto L6;
        setSubtitle(LocaleController.formatString(R.string.BoostingAvailableIn, new Object[]{buildCountDownTime((r0 * 1000) - System.currentTimeMillis())}));
        this.titleTextView.setAlpha(0.65f);
        this.subtitleTextView.setAlpha(0.65f);
        setCheckboxAlpha(0.3f, false);
        return;
    L6:
        setSubtitle(LocaleController.formatString(R.string.BoostExpireOn, new Object[]{LocaleController.getInstance().getFormatterBoostExpired().format(new Date(this.boost.expires * 1000))}));
        float r02 = this.titleTextView.getAlpha();
        SimpleTextView r3 = this.titleTextView;
        if (r02 >= 1.0f) goto L10;
        r3.animate().alpha(1.0f).start();
        this.subtitleTextView.animate().alpha(1.0f).start();
        setCheckboxAlpha(1.0f, true);
        return;
    L10:
        r3.setAlpha(1.0f);
        this.subtitleTextView.setAlpha(1.0f);
        setCheckboxAlpha(1.0f, false);
    }
}
