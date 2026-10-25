package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Point;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import java.util.Locale;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ImageLocation;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.WebFile;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes6.dex */
public class PaymentInfoCell extends FrameLayout {
    private final TextView detailExTextView;
    private final TextView detailTextView;
    private final BackupImageView imageView;
    private final TextView nameTextView;

    public PaymentInfoCell(Context r26) {
        super(r26);
        BackupImageView r2 = new BackupImageView(r26);
        this.imageView = r2;
        r2.getImageReceiver().setRoundRadius(AndroidUtilities.dp(8.0f));
        int r4 = 5;
        if (LocaleController.isRTL == false) goto L5;
        int r8 = 5;
    L6:
        addView(r2, LayoutHelper.createFrame(100, 100.0f, r8, 10.0f, 10.0f, 10.0f, 0.0f));
        TextView r22 = new TextView(r26);
        this.nameTextView = r22;
        int r3 = Theme.key_windowBackgroundWhiteBlackText;
        r22.setTextColor(Theme.getColor(r3));
        r22.setTextSize(1, 16.0f);
        r22.setLines(1);
        r22.setTypeface(AndroidUtilities.bold());
        r22.setMaxLines(1);
        r22.setSingleLine(true);
        TextUtils.TruncateAt r6 = TextUtils.TruncateAt.END;
        r22.setEllipsize(r6);
        if (LocaleController.isRTL == false) goto L9;
        int r82 = 5;
    L10:
        r22.setGravity(r82 | 48);
        boolean r83 = LocaleController.isRTL;
        if (r83 == false) goto L13;
        int r9 = 5;
    L14:
        int r12 = r9 | 48;
        if (r83 == false) goto L17;
        float r13 = 10.0f;
    L18:
        if (r83 == false) goto L20;
        float r15 = 123.0f;
    L21:
        addView(r22, LayoutHelper.createFrame(-1, -2.0f, r12, r13, 9.0f, r15, 0.0f));
        TextView r23 = new TextView(r26);
        this.detailTextView = r23;
        r23.setTextColor(Theme.getColor(r3));
        r23.setTextSize(1, 14.0f);
        r23.setMaxLines(3);
        r23.setEllipsize(r6);
        if (LocaleController.isRTL == false) goto L24;
        int r84 = 5;
    L25:
        r23.setGravity(r84 | 48);
        boolean r85 = LocaleController.isRTL;
        if (r85 == false) goto L28;
        int r10 = 5;
    L29:
        int r20 = r10 | 48;
        if (r85 == false) goto L32;
        float r21 = 10.0f;
    L33:
        if (r85 == false) goto L35;
        float r232 = 123.0f;
    L36:
        addView(r23, LayoutHelper.createFrame(-1, -2.0f, r20, r21, 33.0f, r232, 0.0f));
        TextView r24 = new TextView(r26);
        this.detailExTextView = r24;
        r24.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteGrayText2));
        r24.setTextSize(1, 14.0f);
        r24.setLines(1);
        r24.setMaxLines(1);
        r24.setSingleLine(true);
        r24.setEllipsize(r6);
        if (LocaleController.isRTL == false) goto L39;
        int r1 = 5;
    L40:
        r24.setGravity(r1 | 48);
        boolean r14 = LocaleController.isRTL;
        if (r14 == true) goto L44;
        r4 = 3;
    L44:
        int r122 = r4 | 48;
        if (r14 == false) goto L47;
        float r132 = 10.0f;
    L48:
        if (r14 == false) goto L50;
        float r152 = 123.0f;
    L51:
        addView(r24, LayoutHelper.createFrame(-1, -2.0f, r122, r132, 90.0f, r152, 9.0f));
        return;
    L50:
        r152 = 10.0f;
        goto L51
    L47:
        r132 = 123.0f;
        goto L48
    L39:
        r1 = 3;
        goto L40
    L35:
        r232 = 10.0f;
        goto L36
    L32:
        r21 = 123.0f;
        goto L33
    L28:
        r10 = 3;
        goto L29
    L24:
        r84 = 3;
        goto L25
    L20:
        r15 = 10.0f;
        goto L21
    L17:
        r13 = 123.0f;
        goto L18
    L13:
        r9 = 3;
        goto L14
    L9:
        r82 = 3;
        goto L10
    L5:
        r8 = 3;
        goto L6
    }

    @Override // android.widget.FrameLayout, android.view.View
    public void onMeasure(int r10, int r11) {
        if (this.imageView.getVisibility() == 8) goto L5;
        int r112 = View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(120.0f), TLObject.FLAG_30);
        PaymentInfoCell r3 = this;
        int r5 = r10;
    L6:
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r5), TLObject.FLAG_30), r112);
        return;
    L5:
        int r0 = View.MeasureSpec.makeMeasureSpec(0, 0);
        r3 = this;
        r5 = r10;
        r3.measureChildWithMargins(this.detailTextView, r5, 0, r11, 0);
        FrameLayout.LayoutParams r9 = (FrameLayout.LayoutParams) r3.detailExTextView.getLayoutParams();
        r9.topMargin = (AndroidUtilities.dp(33.0f) + r3.detailTextView.getMeasuredHeight()) + AndroidUtilities.dp(3.0f);
        r112 = r0;
        goto L6
    }

    public void setInfo(String r25, String r26, TLRPC.WebDocument r27, String r28, Object r29) {
        this.nameTextView.setText(r25);
        this.detailTextView.setText(r26);
        this.detailExTextView.setText(r28);
        if (AndroidUtilities.isTablet() == false) goto L6;
        int r2 = AndroidUtilities.getMinTabletSide();
    L5:
        int r22 = (int) (r2 * 0.7f);
        float r23 = 640.0f / (r22 - AndroidUtilities.dp(2.0f));
        int r3 = (int) (640.0f / r23);
        int r24 = (int) (360.0f / r23);
        int r4 = 3;
        if (r27 != null) goto L10;
    L45:
        TextView r1 = this.nameTextView;
        if (LocaleController.isRTL == false) goto L48;
        int r210 = 5;
    L49:
        r1.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r210 | 48, 17.0f, 9.0f, 17.0f, 0.0f));
        TextView r12 = this.detailTextView;
        if (LocaleController.isRTL == false) goto L52;
        int r211 = 5;
    L53:
        r12.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r211 | 48, 17.0f, 33.0f, 17.0f, 0.0f));
        TextView r13 = this.detailExTextView;
        if (LocaleController.isRTL == false) goto L56;
        r4 = 5;
    L56:
        r13.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r4 | 48, 17.0f, 90.0f, 17.0f, 9.0f));
        this.imageView.setVisibility(8);
        return;
    L52:
        r211 = 3;
        goto L53
    L48:
        r210 = 3;
        goto L49
    L10:
        if (r27.mime_type.startsWith("image/") == false) goto L45;
        TextView r6 = this.nameTextView;
        boolean r7 = LocaleController.isRTL;
        if (r7 == false) goto L14;
        int r8 = 5;
    L15:
        int r11 = r8 | 48;
        if (r7 == false) goto L18;
        float r122 = 10.0f;
    L19:
        if (r7 == false) goto L21;
        float r14 = 123.0f;
    L22:
        r6.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r11, r122, 9.0f, r14, 0.0f));
        TextView r62 = this.detailTextView;
        boolean r72 = LocaleController.isRTL;
        if (r72 == false) goto L25;
        int r9 = 5;
    L26:
        int r19 = r9 | 48;
        if (r72 == false) goto L29;
        float r20 = 10.0f;
    L30:
        if (r72 == false) goto L32;
        float r222 = 123.0f;
    L33:
        r62.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r19, r20, 33.0f, r222, 0.0f));
        TextView r63 = this.detailExTextView;
        boolean r73 = LocaleController.isRTL;
        if (r73 == false) goto L36;
        r4 = 5;
    L36:
        int r112 = r4 | 48;
        if (r73 == false) goto L39;
        float r123 = 10.0f;
    L40:
        if (r73 == false) goto L42;
        float r142 = 123.0f;
    L43:
        r63.setLayoutParams(LayoutHelper.createFrame(-1, -2.0f, r112, r123, 90.0f, r142, 0.0f));
        this.imageView.setVisibility(0);
        this.imageView.getImageReceiver().setImage(ImageLocation.getForWebFile(WebFile.createWithWebDocument(r27)), String.format(Locale.US, "%d_%d", new Object[]{Integer.valueOf(r3), Integer.valueOf(r24)}), null, null, -1, null, r29, 1);
        return;
    L42:
        r142 = 10.0f;
        goto L43
    L39:
        r123 = 123.0f;
        goto L40
    L32:
        r222 = 10.0f;
        goto L33
    L29:
        r20 = 123.0f;
        goto L30
    L25:
        r9 = 3;
        goto L26
    L21:
        r14 = 10.0f;
        goto L22
    L18:
        r122 = 123.0f;
        goto L19
    L14:
        r8 = 3;
        goto L15
    L6:
        Point r212 = AndroidUtilities.displaySize;
        r2 = Math.min(r212.x, r212.y);
        goto L5
    }

    public void setInvoice(TLRPC.TL_messageMediaInvoice r7, String r8) {
        setInfo(r7.title, r7.description, r7.webPhoto, r8, r7);
    }

    public void setReceipt(TLRPC.PaymentReceipt r7, String r8) {
        setInfo(r7.title, r7.description, r7.photo, r8, r7);
    }
}
