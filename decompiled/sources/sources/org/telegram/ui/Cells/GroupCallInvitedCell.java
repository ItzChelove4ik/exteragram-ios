package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.exteragram.messenger.ExteraConfig;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.SimpleTextView;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AvatarDrawable;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public abstract class GroupCallInvitedCell extends FrameLayout {
    private AvatarDrawable avatarDrawable;
    private BackupImageView avatarImageView;
    private TLRPC.User currentUser;
    private Paint dividerPaint;
    private int grayIconColor;
    private ImageView muteButton;
    private SimpleTextView nameTextView;
    private boolean needDivider;
    private SimpleTextView statusTextView;

    public GroupCallInvitedCell(Context r24) {
        super(r24);
        this.grayIconColor = Theme.key_voipgroup_mutedIcon;
        Paint r2 = new Paint();
        this.dividerPaint = r2;
        r2.setColor(Theme.getColor(Theme.key_voipgroup_actionBar));
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r22 = new BackupImageView(r24);
        this.avatarImageView = r22;
        r22.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        BackupImageView r23 = this.avatarImageView;
        boolean r3 = LocaleController.isRTL;
        int r4 = 3;
        if (r3 == false) goto L5;
        int r6 = 5;
    L6:
        int r9 = r6 | 48;
        if (r3 == false) goto L9;
        float r10 = 0.0f;
    L10:
        if (r3 == false) goto L12;
        float r12 = 11.0f;
    L13:
        addView(r23, LayoutHelper.createFrame(46, 46.0f, r9, r10, 6.0f, r12, 0.0f));
        SimpleTextView r25 = new SimpleTextView(r24);
        this.nameTextView = r25;
        r25.setTextColor(Theme.getColor(Theme.key_voipgroup_nameText));
        this.nameTextView.setTypeface(AndroidUtilities.bold());
        this.nameTextView.setTextSize(16);
        SimpleTextView r26 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L16;
        int r62 = 5;
    L17:
        r26.setGravity(r62 | 48);
        SimpleTextView r27 = this.nameTextView;
        boolean r63 = LocaleController.isRTL;
        if (r63 == false) goto L20;
        int r7 = 5;
    L21:
        int r102 = r7 | 48;
        if (r63 == false) goto L24;
        float r11 = 54.0f;
    L25:
        if (r63 == false) goto L27;
        float r13 = 67.0f;
    L28:
        addView(r27, LayoutHelper.createFrame(-1, 20.0f, r102, r11, 10.0f, r13, 0.0f));
        SimpleTextView r28 = new SimpleTextView(r24);
        this.statusTextView = r28;
        r28.setTextSize(15);
        SimpleTextView r29 = this.statusTextView;
        if (LocaleController.isRTL == false) goto L31;
        int r64 = 5;
    L32:
        r29.setGravity(r64 | 48);
        this.statusTextView.setTextColor(Theme.getColor(this.grayIconColor));
        this.statusTextView.setText(LocaleController.getString(R.string.Invited));
        SimpleTextView r210 = this.statusTextView;
        boolean r65 = LocaleController.isRTL;
        if (r65 == false) goto L35;
        int r8 = 5;
    L36:
        int r18 = r8 | 48;
        if (r65 == false) goto L39;
        float r19 = 54.0f;
    L40:
        if (r65 == false) goto L42;
        float r21 = 67.0f;
    L43:
        addView(r210, LayoutHelper.createFrame(-1, 20.0f, r18, r19, 32.0f, r21, 0.0f));
        ImageView r211 = new ImageView(r24);
        this.muteButton = r211;
        r211.setScaleType(ImageView.ScaleType.CENTER);
        this.muteButton.setImageResource(R.drawable.msg_invited);
        this.muteButton.setImportantForAccessibility(2);
        this.muteButton.setPadding(0, 0, AndroidUtilities.dp(4.0f), 0);
        this.muteButton.setColorFilter(new PorterDuffColorFilter(Theme.getColor(this.grayIconColor), PorterDuff.Mode.MULTIPLY));
        ImageView r1 = this.muteButton;
        if (LocaleController.isRTL == true) goto L47;
        r4 = 5;
    L47:
        addView(r1, LayoutHelper.createFrame(48, -1.0f, r4 | 16, 6.0f, 0.0f, 6.0f, 0.0f));
        setWillNotDraw(false);
        setFocusable(true);
        return;
    L42:
        r21 = 54.0f;
        goto L43
    L39:
        r19 = 67.0f;
        goto L40
    L35:
        r8 = 3;
        goto L36
    L31:
        r64 = 3;
        goto L32
    L27:
        r13 = 54.0f;
        goto L28
    L24:
        r11 = 67.0f;
        goto L25
    L20:
        r7 = 3;
        goto L21
    L16:
        r62 = 3;
        goto L17
    L12:
        r12 = 0.0f;
        goto L13
    L9:
        r10 = 11.0f;
        goto L10
    L5:
        r6 = 3;
        goto L6
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r9) {
        if (this.needDivider == true) goto L5;
        Canvas r2 = r9;
    L15:
        super.dispatchDraw(r2);
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(68.0f);
    L13:
        r2 = r9;
        r2.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, this.dividerPaint);
        goto L15
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(68.0f);
        goto L7
    }

    public CharSequence getName() {
        return this.nameTextView.getText();
    }

    public TLRPC.User getUser() {
        return this.currentUser;
    }

    public boolean hasAvatarSet() {
        return this.avatarImageView.getImageReceiver().hasNotThumb();
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(58.0f), TLObject.FLAG_30));
    }

    public void setData(int r2, Long r3, boolean r4, boolean r5, boolean r6) {
        TLRPC.User r32 = MessagesController.getInstance(r2).getUser(r3);
        this.currentUser = r32;
        AvatarDrawable r0 = this.avatarDrawable;
        if (r32 != null) goto L5;
        r0.setAvatarType(21);
    L6:
        this.nameTextView.setText(UserObject.getUserName(this.currentUser));
        this.avatarImageView.getImageReceiver().setCurrentAccount(r2);
        this.avatarImageView.setForUserOrChat(this.currentUser, this.avatarDrawable);
        SimpleTextView r22 = this.statusTextView;
        if (r6 == false) goto L9;
        int r33 = R.string.ShadyLeaving;
    L14:
        r22.setText(LocaleController.getString(r33));
        BackupImageView r23 = this.avatarImageView;
        float r34 = 0.5f;
        float r42 = 1.0f;
        if (r5 == true) goto L19;
        if (r6 == true) goto L19;
        float r02 = 1.0f;
    L20:
        r23.setAlpha(r02);
        SimpleTextView r24 = this.nameTextView;
        if (r5 == true) goto L25;
        if (r6 == true) goto L25;
        float r03 = 1.0f;
    L26:
        r24.setAlpha(r03);
        SimpleTextView r25 = this.statusTextView;
        if (r5 == true) goto L31;
        if (r6 == true) goto L31;
        r34 = 1.0f;
    L31:
        r25.setAlpha(r34);
        ImageView r1 = this.muteButton;
        if (r5 == true) goto L34;
        if (r6 == true) goto L34;
    L35:
        r1.setAlpha(r42);
        return;
    L34:
        r42 = 0.0f;
    L25:
        r03 = 0.5f;
    L19:
        r02 = 0.5f;
        goto L20
    L9:
        if (r5 == false) goto L11;
        r33 = R.string.ShadyJoining;
        goto L14
    L11:
        if (r4 == false) goto L13;
        r33 = R.string.ConferenceCalling;
        goto L14
    L13:
        r33 = R.string.Invited;
        goto L14
    L5:
        r0.setInfo(r32);
        goto L6
    }

    public void setDrawDivider(boolean r1) {
        this.needDivider = r1;
        invalidate();
    }

    public void setGrayIconColor(int r3, int r4) {
        this.grayIconColor = r3;
        this.muteButton.setColorFilter(new PorterDuffColorFilter(r4, PorterDuff.Mode.MULTIPLY));
        this.statusTextView.setTextColor(r4);
        Theme.setSelectorDrawableColor(this.muteButton.getDrawable(), 620756991 & r4, true);
    }
}
