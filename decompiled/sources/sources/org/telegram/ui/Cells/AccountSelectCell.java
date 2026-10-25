package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ContactsController;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class AccountSelectCell extends FrameLayout {
    private int accountNumber;
    private AvatarDrawable avatarDrawable;
    private ImageView checkImageView;
    private BackupImageView imageView;
    private TextView infoTextView;
    private SimpleTextView textView;

    public AccountSelectCell(Context r10, boolean r11) {
        super(r10);
        setMinimumWidth(AndroidUtilities.dp(196.0f));
        AvatarDrawable r0 = new AvatarDrawable();
        this.avatarDrawable = r0;
        r0.setTextSize(AndroidUtilities.dp(12.0f));
        BackupImageView r02 = new BackupImageView(r10);
        this.imageView = r02;
        r02.setRoundRadius(ExteraConfig.getAvatarCorners(36.0f));
        addView(this.imageView, LayoutHelper.createFrame(36, 36.0f, 51, 10.0f, 10.0f, 0.0f, 0.0f));
        SimpleTextView r03 = new SimpleTextView(r10);
        this.textView = r03;
        r03.setTextSize(15);
        this.textView.setTypeface(AndroidUtilities.bold());
        this.textView.setEllipsizeByGradient(true);
        this.textView.setMaxLines(1);
        this.textView.setGravity(19);
        SimpleTextView r04 = this.textView;
        if (r11 == false) goto L6;
        addView(r04, LayoutHelper.createFrame(-2, -2.0f, 51, 61.0f, 7.0f, 8.0f, 0.0f));
        this.textView.setTextColor(Theme.getColor(Theme.key_voipgroup_nameText));
        this.textView.setText(LocaleController.getString(R.string.VoipGroupDisplayAs));
        TextView r112 = new TextView(r10);
        this.infoTextView = r112;
        r112.setTextColor(Theme.getColor(Theme.key_voipgroup_lastSeenText));
        this.infoTextView.setTextSize(1, 15.0f);
        this.infoTextView.setLines(1);
        this.infoTextView.setMaxLines(1);
        this.infoTextView.setSingleLine(true);
        this.infoTextView.setMaxWidth(AndroidUtilities.dp(320.0f));
        this.infoTextView.setGravity(51);
        this.infoTextView.setEllipsize(TextUtils.TruncateAt.END);
        addView(this.infoTextView, LayoutHelper.createFrame(-2, -2.0f, 51, 61.0f, 27.0f, 8.0f, 0.0f));
        return;
    L6:
        addView(r04, LayoutHelper.createFrame(-1, -1.0f, 51, 61.0f, 0.0f, 52.0f, 0.0f));
        this.textView.setTextColor(Theme.getColor(Theme.key_actionBarDefaultSubmenuItem));
        ImageView r113 = new ImageView(r10);
        this.checkImageView = r113;
        r113.setImageResource(R.drawable.account_check);
        this.checkImageView.setScaleType(ImageView.ScaleType.CENTER);
        this.checkImageView.setColorFilter(new PorterDuffColorFilter(Theme.getColor(Theme.key_chats_menuItemCheck), PorterDuff.Mode.MULTIPLY));
        addView(this.checkImageView, LayoutHelper.createFrame(40, -1.0f, 53, 0.0f, 0.0f, 6.0f, 0.0f));
    }

    private int width() {
        float r0 = AndroidUtilities.dp(196.0f);
        if (this.checkImageView == null) goto L5;
        int r1 = 50;
    L6:
        float r12 = AndroidUtilities.dp(r1 + 69);
        float r2 = this.textView.getTextPaint().measureText(this.textView.getText().toString());
        TextView r3 = this.infoTextView;
        if (r3 == null) goto L9;
        float r4 = r3.getPaint().measureText(this.infoTextView.getText().toString());
    L11:
        return (int) Math.max(r0, r12 + Math.max(r2, r4));
    L9:
        r4 = 0.0f;
        goto L11
    L5:
        r1 = 0;
        goto L6
    }

    public int getAccountNumber() {
        return this.accountNumber;
    }

    @Override // android.view.ViewGroup, android.view.View
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (this.infoTextView != null) goto L6;
        this.textView.setTextColor(Theme.getColor(Theme.key_chats_menuItemText));
        return;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r4, int r5) {
        if (this.checkImageView == null) goto L5;
    L15:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r4), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        return;
    L5:
        if (this.infoTextView == null) goto L10;
        if (getLayoutParams().width != (-2)) goto L15;
    L10:
        if (View.MeasureSpec.getMode(r4) != Integer.MIN_VALUE) goto L13;
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(width(), Integer.MIN_VALUE), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
        return;
    L13:
        super.onMeasure(r4, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(56.0f), TLObject.FLAG_30));
    }

    public void setAccount(int r5, boolean r6) {
        this.accountNumber = r5;
        TLRPC.User r0 = UserConfig.getInstance(r5).getCurrentUser();
        this.avatarDrawable.setInfo(r5, r0);
        this.textView.setText(ContactsController.formatName(r0.first_name, r0.last_name));
        this.imageView.getImageReceiver().setCurrentAccount(r5);
        this.imageView.setForUserOrChat(r0, this.avatarDrawable);
        ImageView r4 = this.checkImageView;
        if (r6 == true) goto L5;
    L7:
        int r52 = 4;
    L8:
        r4.setVisibility(r52);
        return;
    L5:
        if (r5 != UserConfig.selectedAccount) goto L7;
        r52 = 0;
        goto L8
    }

    public void setObject(TLObject r4) {
        if ((r4 instanceof TLRPC.User) == false) goto L6;
        TLRPC.User r42 = (TLRPC.User) r4;
        this.avatarDrawable.setInfo(r42);
        this.infoTextView.setText(ContactsController.formatName(r42.first_name, r42.last_name));
        this.imageView.setForUserOrChat(r42, this.avatarDrawable);
        return;
    L6:
        TLRPC.Chat r43 = (TLRPC.Chat) r4;
        this.avatarDrawable.setInfo(r43);
        TextView r0 = this.infoTextView;
        if (r43 != null) goto L9;
        String r1 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r0.setText(r1);
        this.imageView.setForUserOrChat(r43, this.avatarDrawable);
        return;
    L9:
        r1 = r43.title;
        goto L10
    }
}
