package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.util.LongSparseArray;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
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
public class MemberRequestCell extends FrameLayout {
    private final AvatarDrawable avatarDrawable;
    private final BackupImageView avatarImageView;
    private TLRPC.TL_chatInviteImporter importer;
    private boolean isNeedDivider;
    private final SimpleTextView nameTextView;
    private final SimpleTextView statusTextView;

    public interface OnClickListener {
        void onAddClicked(TLRPC.TL_chatInviteImporter r1);

        void onDismissClicked(TLRPC.TL_chatInviteImporter r1);
    }

    public static /* synthetic */ void $r8$lambda$q0hO7KHutFiqCbZxwaC96hMl93E(MemberRequestCell r0, OnClickListener r1, View r2) {
        r0.lambda$new$1(r1, r2);
    }

    /* renamed from: $r8$lambda$y7bnj_qv9xZdwBGok2czy6N-_Wk, reason: not valid java name */
    public static /* synthetic */ void m6964$r8$lambda$y7bnj_qv9xZdwBGok2czy6N_Wk(MemberRequestCell r0, OnClickListener r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public MemberRequestCell(Context r19, OnClickListener r20, boolean r21) {
        super(r19);
        this.avatarDrawable = new AvatarDrawable();
        BackupImageView r2 = new BackupImageView(getContext());
        this.avatarImageView = r2;
        SimpleTextView r3 = new SimpleTextView(getContext());
        this.nameTextView = r3;
        SimpleTextView r4 = new SimpleTextView(getContext());
        this.statusTextView = r4;
        r2.setRoundRadius(ExteraConfig.getAvatarCorners(46.0f));
        int r6 = 3;
        if (LocaleController.isRTL == false) goto L5;
        int r10 = 5;
    L6:
        addView(r2, LayoutHelper.createFrame(46, 46.0f, r10, 12.0f, 8.0f, 12.0f, 0.0f));
        if (LocaleController.isRTL == false) goto L9;
        int r22 = 5;
    L10:
        r3.setGravity(r22);
        r3.setMaxLines(1);
        r3.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
        r3.setTextSize(17);
        r3.setTypeface(AndroidUtilities.bold());
        boolean r5 = LocaleController.isRTL;
        if (r5 == false) goto L13;
        float r13 = 12.0f;
    L14:
        if (r5 == false) goto L16;
        float r15 = 74.0f;
    L17:
        addView(r3, LayoutHelper.createFrame(-1, -2.0f, 48, r13, 12.0f, r15, 0.0f));
        if (LocaleController.isRTL == false) goto L20;
        int r32 = 5;
    L21:
        r4.setGravity(r32);
        r4.setMaxLines(1);
        r4.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText));
        r4.setTextSize(14);
        boolean r33 = LocaleController.isRTL;
        if (r33 == false) goto L24;
        float r132 = 12.0f;
    L25:
        if (r33 == false) goto L27;
        float r152 = 74.0f;
    L28:
        addView(r4, LayoutHelper.createFrame(-1, -2.0f, 48, r132, 36.0f, r152, 0.0f));
        int r34 = AndroidUtilities.dp(17.0f);
        TextView r42 = new TextView(getContext());
        int r9 = 0;
        r42.setBackground(Theme.AdaptiveRipple.filledRectByKey(Theme.key_featuredStickers_addButton, new float[]{16.0f}));
        if (LocaleController.isRTL == false) goto L31;
        int r52 = 5;
    L32:
        r42.setGravity(r52 | 16);
        r42.setMaxLines(1);
        r42.setPadding(r34, 0, r34, 0);
        if (r21 == false) goto L36;
        int r53 = R.string.AddToChannel;
    L37:
        r42.setText(LocaleController.getString(r53));
        r42.setTextColor(Theme.getColor(Theme.key_featuredStickers_buttonText));
        r42.setTextSize(14.0f);
        r42.setTypeface(AndroidUtilities.bold());
        r42.setOnClickListener(new MemberRequestCell$$ExternalSyntheticLambda0(this, r20));
        boolean r8 = LocaleController.isRTL;
        if (r8 == false) goto L40;
        int r133 = 5;
    L42:
        if (r8 == false) goto L44;
        float r14 = 0.0f;
    L45:
        if (r8 == false) goto L47;
        float r16 = 73.0f;
    L48:
        addView(r42, LayoutHelper.createFrame(-2, 32.0f, r133, r14, 62.0f, r16, 0.0f));
        float r43 = r42.getPaint().measureText(r42.getText().toString()) + (r34 * 2);
        TextView r82 = new TextView(getContext());
        r82.setBackground(Theme.createSimpleSelectorRoundRectDrawable(AndroidUtilities.dp(16.0f), 0, Theme.getColor(Theme.key_listSelector), -16777216));
        if (LocaleController.isRTL == false) goto L51;
        int r102 = 5;
    L52:
        r82.setGravity(r102 | 16);
        r82.setMaxLines(1);
        r82.setPadding(r34, 0, r34, 0);
        r82.setText(LocaleController.getString(R.string.Dismiss));
        r82.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlueText));
        r82.setTextSize(14.0f);
        r82.setTypeface(AndroidUtilities.bold());
        r82.setOnClickListener(new MemberRequestCell$$ExternalSyntheticLambda1(this, r20));
        int r23 = AndroidUtilities.dp(32.0f);
        if (LocaleController.isRTL == false) goto L55;
        r6 = 5;
    L55:
        FrameLayout.LayoutParams r1 = new FrameLayout.LayoutParams(-2, r23, r6);
        r1.topMargin = AndroidUtilities.dp(62.0f);
        if (LocaleController.isRTL == false) goto L58;
        int r24 = 0;
    L59:
        r1.leftMargin = r24;
        if (LocaleController.isRTL == false) goto L62;
        r9 = (int) (r43 + AndroidUtilities.dp(79.0f));
    L62:
        r1.rightMargin = r9;
        addView(r82, r1);
        return;
    L58:
        r24 = (int) (AndroidUtilities.dp(79.0f) + r43);
        goto L59
    L51:
        r102 = 3;
        goto L52
    L47:
        r16 = 0.0f;
        goto L48
    L44:
        r14 = 73.0f;
        goto L45
    L40:
        r133 = 3;
        goto L42
    L36:
        r53 = R.string.AddToGroup;
        goto L37
    L31:
        r52 = 3;
        goto L32
    L27:
        r152 = 12.0f;
        goto L28
    L24:
        r132 = 74.0f;
        goto L25
    L20:
        r32 = 3;
        goto L21
    L16:
        r15 = 12.0f;
        goto L17
    L13:
        r13 = 74.0f;
        goto L14
    L9:
        r22 = 3;
        goto L10
    L5:
        r10 = 3;
        goto L6
    }

    private /* synthetic */ void lambda$new$0(OnClickListener r1, View r2) {
        if (r1 == null) goto L7;
        TLRPC.TL_chatInviteImporter r0 = this.importer;
        if (r0 == null) goto L8;
        r1.onAddClicked(r0);
        return;
    L8:
        return;
    }

    private /* synthetic */ void lambda$new$1(OnClickListener r1, View r2) {
        if (r1 == null) goto L7;
        TLRPC.TL_chatInviteImporter r0 = this.importer;
        if (r0 == null) goto L8;
        r1.onDismissClicked(r0);
        return;
    L8:
        return;
    }

    public BackupImageView getAvatarImageView() {
        return this.avatarImageView;
    }

    public TLRPC.TL_chatInviteImporter getImporter() {
        return this.importer;
    }

    public String getStatus() {
        return this.statusTextView.getText().toString();
    }

    @Override // android.view.View
    public void onDraw(Canvas r9) {
        super.onDraw(r9);
        if (this.isNeedDivider == true) goto L5;
        return;
    L5:
        if (LocaleController.isRTL == false) goto L8;
        float r0 = 0.0f;
    L7:
        float r3 = r0;
        float r4 = getMeasuredHeight() - 1;
        int r02 = getMeasuredWidth();
        if (LocaleController.isRTL == false) goto L12;
        int r1 = AndroidUtilities.dp(72.0f);
    L13:
        r9.drawLine(r3, r4, r02 - r1, getMeasuredHeight() - 1, Theme.dividerPaint);
        return;
    L12:
        r1 = 0;
        goto L13
    L8:
        r0 = AndroidUtilities.dp(72.0f);
        goto L7
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(107.0f), TLObject.FLAG_30));
    }

    public void setData(LongSparseArray<TLRPC.User> r5, TLRPC.TL_chatInviteImporter r6, boolean r7) {
        this.importer = r6;
        this.isNeedDivider = r7;
        setWillNotDraw(!r7);
        TLRPC.User r72 = r5.get(r6.user_id);
        this.avatarDrawable.setInfo(r72);
        this.avatarImageView.setForUserOrChat(r72, this.avatarDrawable);
        this.nameTextView.setText(UserObject.getUserName(r72));
        String r73 = LocaleController.formatDateAudio(r6.date, false);
        if (r6.via_chatlist == false) goto L6;
        this.statusTextView.setText(LocaleController.getString(R.string.JoinedViaFolder));
        return;
    L6:
        long r0 = r6.approved_by;
        if (r0 != 0) goto L10;
        this.statusTextView.setText(LocaleController.formatString("RequestedToJoinAt", R.string.RequestedToJoinAt, new Object[]{r73}));
        return;
    L10:
        TLRPC.User r52 = r5.get(r0);
        SimpleTextView r4 = this.statusTextView;
        if (r52 == null) goto L14;
        r4.setText(LocaleController.formatString("AddedBy", R.string.AddedBy, new Object[]{UserObject.getFirstName(r52), r73}));
        return;
    L14:
        r4.setText(_UrlKt.FRAGMENT_ENCODE_SET);
    }
}
