package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.SpannableStringBuilder;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.URLSpanNoUnderline;

/* loaded from: classes6.dex */
public class AdminedChannelCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    CheckBox2 checkBox;
    private int currentAccount;
    private TLRPC.Chat currentChannel;
    private ImageView deleteButton;
    private boolean isLast;
    private SimpleTextView nameTextView;
    private SimpleTextView statusTextView;

    public AdminedChannelCell(Context r19, View.OnClickListener r20, boolean r21, int r22) {
        super(r19);
        this.currentAccount = UserConfig.selectedAccount;
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r3 = new BackupImageView(r19);
        this.avatarImageView = r3;
        r3.setRoundRadius(ExteraConfig.getAvatarCorners(48.0f));
        BackupImageView r32 = this.avatarImageView;
        boolean r4 = LocaleController.isRTL;
        int r5 = 5;
        if (r4 == false) goto L5;
        int r7 = 5;
    L6:
        int r10 = r7 | 48;
        if (r4 == false) goto L9;
        float r11 = 0.0f;
    L10:
        if (r4 == false) goto L12;
        float r13 = r22 + 12;
    L13:
        addView(r32, LayoutHelper.createFrame(48, 48.0f, r10, r11, 6.0f, r13, 6.0f));
        if (r21 == false) goto L27;
        CheckBox2 r33 = new CheckBox2(r19, 21);
        this.checkBox = r33;
        r33.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        this.checkBox.setDrawUnchecked(false);
        this.checkBox.setDrawBackgroundAsArc(3);
        CheckBox2 r34 = this.checkBox;
        boolean r42 = LocaleController.isRTL;
        if (r42 == false) goto L18;
        int r8 = 5;
    L19:
        int r112 = r8 | 48;
        if (r42 == false) goto L22;
        float r12 = 0.0f;
    L23:
        if (r42 == false) goto L25;
        float r14 = r22 + 42;
    L26:
        addView(r34, LayoutHelper.createFrame(24, 24.0f, r112, r12, 32.0f, r14, 0.0f));
        goto L27
    L25:
        r14 = 0.0f;
        goto L26
    L22:
        r12 = r22 + 42;
        goto L23
    L18:
        r8 = 3;
    L27:
        if (r20 != null) goto L29;
        int r35 = 24;
    L30:
        SimpleTextView r43 = new SimpleTextView(r19);
        this.nameTextView = r43;
        r43.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        this.nameTextView.setTextSize(17);
        SimpleTextView r44 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L33;
        int r82 = 5;
    L34:
        r44.setGravity(r82 | 48);
        SimpleTextView r45 = this.nameTextView;
        boolean r83 = LocaleController.isRTL;
        if (r83 == false) goto L37;
        int r9 = 5;
    L38:
        int r122 = r9 | 48;
        if (r83 == false) goto L42;
        float r92 = r35;
    L41:
        float r132 = r92;
        if (r83 == false) goto L46;
        float r84 = r22 + 73;
    L47:
        addView(r45, LayoutHelper.createFrame(-1, 20.0f, r122, r132, 9.5f, r84, 0.0f));
        SimpleTextView r46 = new SimpleTextView(r19);
        this.statusTextView = r46;
        r46.setTextSize(14);
        SimpleTextView r47 = this.statusTextView;
        int r85 = Theme.key_windowBackgroundWhiteGrayText;
        r47.setTextColor(Theme.getColor(r85));
        this.statusTextView.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        SimpleTextView r48 = this.statusTextView;
        if (LocaleController.isRTL == false) goto L50;
        int r93 = 5;
    L51:
        r48.setGravity(r93 | 48);
        SimpleTextView r49 = this.statusTextView;
        boolean r94 = LocaleController.isRTL;
        if (r94 == false) goto L54;
        int r102 = 5;
    L55:
        int r133 = r102 | 48;
        if (r94 == false) goto L59;
        float r103 = r35;
    L58:
        float r142 = r103;
        if (r94 == false) goto L62;
        r35 = r22 + 73;
    L62:
        addView(r49, LayoutHelper.createFrame(-1, 20.0f, r133, r142, 32.5f, r35, 6.0f));
        if (r20 == null) goto L76;
        ImageView r36 = new ImageView(r19);
        this.deleteButton = r36;
        r36.setScaleType(ImageView.ScaleType.CENTER);
        this.deleteButton.setImageResource(R.drawable.msg_panel_clear);
        this.deleteButton.setOnClickListener(r20);
        this.deleteButton.setBackground(Theme.createSelectorDrawable(Theme.getColor(Theme.key_listSelector)));
        this.deleteButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r85), PorterDuff.Mode.MULTIPLY));
        ImageView r1 = this.deleteButton;
        boolean r2 = LocaleController.isRTL;
        if (r2 == false) goto L67;
        r5 = 3;
    L67:
        int r104 = r5 | 48;
        if (r2 == false) goto L70;
        float r113 = 7.0f;
    L71:
        if (r2 == false) goto L73;
        float r134 = 0.0f;
    L74:
        addView(r1, LayoutHelper.createFrame(48, 48.0f, r104, r113, 6.0f, r134, 0.0f));
        return;
    L73:
        r134 = 7.0f;
        goto L74
    L70:
        r113 = 0.0f;
        goto L71
    L76:
        return;
    L59:
        r103 = r22 + 73;
        goto L58
    L54:
        r102 = 3;
        goto L55
    L50:
        r93 = 3;
        goto L51
    L46:
        r84 = r35;
        goto L47
    L42:
        r92 = r22 + 73;
        goto L41
    L37:
        r9 = 3;
        goto L38
    L33:
        r82 = 3;
        goto L34
    L29:
        r35 = 62;
        goto L30
    L12:
        r13 = 0.0f;
        goto L13
    L9:
        r11 = r22 + 12;
        goto L10
    L5:
        r7 = 3;
        goto L6
    }

    public TLRPC.Chat getCurrentChannel() {
        return this.currentChannel;
    }

    public ImageView getDeleteButton() {
        return this.deleteButton;
    }

    public SimpleTextView getNameTextView() {
        return this.nameTextView;
    }

    public SimpleTextView getStatusTextView() {
        return this.statusTextView;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        int r22 = View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30);
        if (this.isLast == false) goto L5;
        int r0 = 12;
    L6:
        super.onMeasure(r22, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(r0 + 60), TLObject.FLAG_30));
        return;
    L5:
        r0 = 0;
        goto L6
    }

    public void setChannel(TLRPC.Chat r6, boolean r7) {
        String r0 = MessagesController.getInstance(this.currentAccount).linkPrefix + "/";
        this.currentChannel = r6;
        this.avatarDrawable.setInfo(this.currentAccount, r6);
        this.nameTextView.setText(r6.title);
        SpannableStringBuilder r1 = new SpannableStringBuilder(r0 + ChatObject.getPublicUsername(r6));
        r1.setSpan(new URLSpanNoUnderline(_UrlKt.FRAGMENT_ENCODE_SET), r0.length(), r1.length(), 33);
        this.statusTextView.setText(r1);
        this.avatarImageView.setForUserOrChat(r6, this.avatarDrawable);
        this.isLast = r7;
    }

    public void setChecked(boolean r1, boolean r2) {
        this.checkBox.setChecked(r1, r2);
    }

    public void update() {
        this.avatarDrawable.setInfo(this.currentAccount, this.currentChannel);
        this.avatarImageView.invalidate();
    }
}
