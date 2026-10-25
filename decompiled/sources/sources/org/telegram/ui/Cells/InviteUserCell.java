package org.telegram.ui.Cells;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.CheckBox2;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ProgressButton;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes6.dex */
public class InviteUserCell extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarImageView;
    private final ProgressButton button;
    private final CheckBox2 checkBox;
    private ContactsController.Contact currentContact;
    private CharSequence currentName;
    private final SimpleTextView nameTextView;
    private final SimpleTextView statusTextView;

    /* renamed from: $r8$lambda$BON-aSHmd9Yy1iqaeqcLaWfCHok, reason: not valid java name */
    public static /* synthetic */ void m6958$r8$lambda$BONaSHmd9Yy1iqaeqcLaWfCHok(InviteUserCell r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public InviteUserCell(Context r17, boolean r18) {
        super(r17);
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r2 = new BackupImageView(r17);
        this.avatarImageView = r2;
        r2.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        int r4 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r3 = 5;
    L6:
        addView(r2, LayoutHelper.createFrame(46, 46.0f, r3 | 48, 13.0f, 6.0f, 13.0f, 6.0f));
        LinearLayout r22 = new LinearLayout(r17);
        r22.setOrientation(0);
        boolean r6 = LocaleController.isRTL;
        int r7 = 72;
        if (r6 == false) goto L9;
        int r8 = 0;
    L10:
        float r12 = r8;
        if (r6 == true) goto L14;
        r7 = 0;
    L14:
        addView(r22, LayoutHelper.createFrame(-1, -1.0f, 119, r12, 0.0f, r7, 0.0f));
        FrameLayout r62 = new FrameLayout(r17);
        r22.addView(r62, LayoutHelper.createLinear(0, 58, 1.0f));
        SimpleTextView r72 = new SimpleTextView(r17);
        this.nameTextView = r72;
        r72.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r72.setTypeface(AndroidUtilities.bold());
        r72.setTextSize(15);
        if (LocaleController.isRTL == false) goto L17;
        int r82 = 5;
    L18:
        r72.setGravity(r82 | 48);
        if (LocaleController.isRTL == false) goto L21;
        int r83 = 5;
    L22:
        r62.addView(r72, LayoutHelper.createFrame(-1, 20.0f, r83 | 48, 0.0f, 9.0f, 0.0f, 0.0f));
        SimpleTextView r73 = new SimpleTextView(r17);
        this.statusTextView = r73;
        r73.setTextSize(13);
        if (LocaleController.isRTL == false) goto L25;
        int r84 = 5;
    L26:
        r73.setGravity(r84 | 48);
        if (LocaleController.isRTL == false) goto L29;
        int r85 = 5;
    L30:
        r62.addView(r73, LayoutHelper.createFrame(-1, 20.0f, r85 | 48, 0.0f, 33.0f, 0.0f, 0.0f));
        if (r18 == false) goto L44;
        this.button = null;
        CheckBox2 r23 = new CheckBox2(r17, 21);
        this.checkBox = r23;
        r23.setColor(-1, Theme.key_windowBackgroundWhite, Theme.key_checkboxCheck);
        r23.setDrawUnchecked(false);
        r23.setDrawBackgroundAsArc(3);
        boolean r1 = LocaleController.isRTL;
        if (r1 == true) goto L36;
        r4 = 3;
    L36:
        int r74 = r4 | 48;
        float r32 = 0.0f;
        if (r1 == false) goto L39;
        float r86 = 0.0f;
    L40:
        if (r1 == false) goto L42;
        r32 = 39.0f;
    L42:
        addView(r23, LayoutHelper.createFrame(24, 24.0f, r74, r86, 32.0f, r32, 0.0f));
        return;
    L39:
        r86 = 40.0f;
        goto L40
    L44:
        this.checkBox = null;
        ProgressButton r42 = new ProgressButton(r17);
        this.button = r42;
        r42.setText(LocaleController.getString(R.string.Invite));
        r42.setTextSize(1, 14.0f);
        r42.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r42.setProgressColor(Theme.getColor(Theme.key_featuredStickers_buttonProgress));
        r42.setBackgroundRoundRect(Theme.getColor(Theme.key_telegram_color), Theme.getColor(Theme.key_featuredStickers_addButtonPressed), 16.0f);
        r42.setPadding(AndroidUtilities.dp(14.0f), 0, AndroidUtilities.dp(14.0f), 0);
        r22.addView(r42, LayoutHelper.createLinear(-2, 28, 0.0f, 16, 18, 0, 18, 0));
        r42.setOnClickListener(new InviteUserCell$$ExternalSyntheticLambda0(this));
        return;
    L29:
        r85 = 3;
        goto L30
    L25:
        r84 = 3;
        goto L26
    L21:
        r83 = 3;
        goto L22
    L17:
        r82 = 3;
        goto L18
    L9:
        r8 = 72;
        goto L10
    L5:
        r3 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        performClick();
    }

    public ContactsController.Contact getContact() {
        return this.currentContact;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public void recycle() {
        this.avatarImageView.getImageReceiver().cancelLoadImage();
    }

    public void setChecked(boolean r1, boolean r2) {
        CheckBox2 r0 = this.checkBox;
        if (r0 == null) goto L6;
        r0.setChecked(r1, r2);
        return;
    }

    public void setUser(ContactsController.Contact r1, CharSequence r2) {
        this.currentContact = r1;
        this.currentName = r2;
        update(0);
    }

    public void update(int r10) {
        ContactsController.Contact r102 = this.currentContact;
        if (r102 != null) goto L5;
        return;
    L5:
        this.avatarDrawable.setInfo(r102.contact_id, r102.first_name, r102.last_name, null, null, null, false);
        CharSequence r103 = this.currentName;
        SimpleTextView r0 = this.nameTextView;
        if (r103 == null) goto L8;
        r0.setText(r103, true);
    L9:
        SimpleTextView r104 = this.statusTextView;
        int r02 = Theme.key_windowBackgroundWhiteGrayText;
        r104.setTag(Integer.valueOf(r02));
        this.statusTextView.setTextColor(Theme.getColor(r02));
        ContactsController.Contact r105 = this.currentContact;
        int r03 = r105.imported;
        SimpleTextView r1 = this.statusTextView;
        if (r03 <= 0) goto L12;
        r1.setText(LocaleController.formatPluralString("TelegramContacts", r03, new Object[0]));
    L13:
        this.avatarImageView.setImageDrawable(this.avatarDrawable);
        return;
    L12:
        r1.setText(r105.phones.get(0));
        goto L13
    L8:
        ContactsController.Contact r106 = this.currentContact;
        r0.setText(ContactsController.formatName(r106.first_name, r106.last_name));
        goto L9
    }
}
