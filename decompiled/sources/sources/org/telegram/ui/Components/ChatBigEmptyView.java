package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes7.dex */
public class ChatBigEmptyView extends LinearLayout {
    private ArrayList<ImageView> imageViews;
    private final Theme.ResourcesProvider resourcesProvider;
    private TextView statusTextView;
    private ArrayList<TextView> textViews;

    public ChatBigEmptyView(Context r22, View r23, int r24, Theme.ResourcesProvider r25) {
        super(r22);
        this.textViews = new ArrayList();
        this.imageViews = new ArrayList();
        this.resourcesProvider = r25;
        setBackground(Theme.createServiceDrawable(AndroidUtilities.dp(18.0f), this, r23, getThemedPaint("paintChatActionBackground")));
        setPadding(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f), AndroidUtilities.dp(16.0f), AndroidUtilities.dp(12.0f));
        setOrientation(1);
        if (r24 != 0) goto L5;
        TextView r9 = new TextView(r22);
        this.statusTextView = r9;
        r9.setTextSize(1, 15.0f);
        this.statusTextView.setTextColor(getThemedColor(Theme.key_chat_serviceText));
        this.statusTextView.setGravity(1);
        this.statusTextView.setMaxWidth(AndroidUtilities.dp(210.0f));
        this.textViews.add(this.statusTextView);
        addView(this.statusTextView, LayoutHelper.createLinear(-2, -2, 49));
    L8:
        TextView r5 = new TextView(r22);
        if (r24 != 0) goto L11;
        r5.setText(LocaleController.getString(R.string.EncryptedDescriptionTitle));
        r5.setTextSize(1, 15.0f);
    L14:
        r5.setTextColor(getThemedColor(Theme.key_chat_serviceText));
        this.textViews.add(r5);
        float r3 = 260.0f;
        r5.setMaxWidth(AndroidUtilities.dp(260.0f));
        if (r24 != 2) goto L17;
        int r11 = 1;
    L21:
        int r14 = r11 | 48;
        if (r24 == 2) goto L24;
        int r18 = 0;
    L25:
        addView(r5, LayoutHelper.createLinear(-2, -2, r14, 0, 8, 0, r18));
        int r52 = 0;
    L27:
        if (r52 >= 4) goto L83;
        LinearLayout r12 = new LinearLayout(r22);
        r12.setOrientation(0);
        if (LocaleController.isRTL == false) goto L31;
        int r16 = 5;
    L32:
        addView(r12, LayoutHelper.createLinear(-2, -2, r16, 0, 8, 0, 0));
        ImageView r13 = new ImageView(r22);
        int r15 = Theme.key_chat_serviceText;
        float r232 = r3;
        r13.setColorFilter(new PorterDuffColorFilter(getThemedColor(r15), PorterDuff.Mode.MULTIPLY));
        if (r24 != 0) goto L35;
        r13.setImageResource(R.drawable.ic_lock_white);
    L38:
        this.imageViews.add(r13);
        TextView r32 = new TextView(r22);
        r32.setTextSize(1, 15.0f);
        r32.setTextColor(getThemedColor(r15));
        this.textViews.add(r32);
        if (LocaleController.isRTL == false) goto L41;
        int r6 = 5;
    L42:
        r32.setGravity(r6 | 16);
        r32.setMaxWidth(AndroidUtilities.dp(r232));
        if (r52 == 0) goto L63;
        if (r52 == 1) goto L58;
        if (r52 == 2) goto L53;
        if (r52 != 3) goto L69;
        if (r24 != 0) goto L50;
        r32.setText(LocaleController.getString(R.string.EncryptedDescription4));
        goto L69
    L50:
        if (r24 != 2) goto L52;
        r32.setText(LocaleController.getString(R.string.ChatYourSelfDescription4));
        goto L69
    L52:
        r32.setText(LocaleController.getString(R.string.GroupDescription4));
    L69:
        if (LocaleController.isRTL == false) goto L76;
        r12.addView(r32, LayoutHelper.createLinear(-2, -2));
        if (r24 != 0) goto L73;
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 8.0f, 3.0f, 0.0f, 0.0f));
    L82:
        r52 = r52 + 1;
        r3 = r232;
        goto L27
    L73:
        if (r24 != 2) goto L75;
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 8.0f, 7.0f, 0.0f, 0.0f));
        goto L82
    L75:
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 8.0f, 3.0f, 0.0f, 0.0f));
        goto L82
    L76:
        if (r24 != 0) goto L78;
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 0.0f, 4.0f, 8.0f, 0.0f));
    L81:
        r12.addView(r32, LayoutHelper.createLinear(-2, -2));
        goto L82
    L78:
        if (r24 != 2) goto L80;
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 0.0f, 8.0f, 8.0f, 0.0f));
        goto L81
    L80:
        r12.addView(r13, LayoutHelper.createLinear(-2, -2, 0.0f, 4.0f, 8.0f, 0.0f));
        goto L81
    L53:
        if (r24 != 0) goto L55;
        r32.setText(LocaleController.getString(R.string.EncryptedDescription3));
        goto L69
    L55:
        if (r24 != 2) goto L57;
        r32.setText(LocaleController.getString(R.string.ChatYourSelfDescription3));
        goto L69
    L57:
        r32.setText(LocaleController.getString(R.string.GroupDescription3));
        goto L69
    L58:
        if (r24 != 0) goto L60;
        r32.setText(LocaleController.getString(R.string.EncryptedDescription2));
        goto L69
    L60:
        if (r24 != 2) goto L62;
        r32.setText(LocaleController.getString(R.string.ChatYourSelfDescription2));
        goto L69
    L62:
        r32.setText(LocaleController.getString(R.string.GroupDescription2));
        goto L69
    L63:
        if (r24 != 0) goto L65;
        r32.setText(LocaleController.getString(R.string.EncryptedDescription1));
        goto L69
    L65:
        if (r24 != 2) goto L67;
        r32.setText(LocaleController.getString(R.string.ChatYourSelfDescription1));
        goto L69
    L67:
        r32.setText(LocaleController.getString(R.string.GroupDescription1));
        goto L69
    L41:
        r6 = 3;
        goto L42
    L35:
        if (r24 != 2) goto L37;
        r13.setImageResource(R.drawable.list_circle);
        goto L38
    L37:
        r13.setImageResource(R.drawable.groups_overview_check);
        goto L38
    L31:
        r16 = 3;
        goto L32
    L83:
        return;
    L24:
        r18 = 8;
        goto L25
    L17:
        if (LocaleController.isRTL == false) goto L19;
        r11 = 5;
        goto L21
    L19:
        r11 = 3;
        goto L21
    L11:
        if (r24 != 1) goto L13;
        r5.setText(LocaleController.getString(R.string.GroupEmptyTitle2));
        r5.setTextSize(1, 15.0f);
        goto L14
    L13:
        r5.setText(LocaleController.getString(R.string.ChatYourSelfTitle));
        r5.setTextSize(1, 16.0f);
        r5.setTypeface(AndroidUtilities.bold());
        r5.setGravity(1);
        goto L14
    L5:
        if (r24 != 1) goto L7;
        TextView r92 = new TextView(r22);
        this.statusTextView = r92;
        r92.setTextSize(1, 15.0f);
        this.statusTextView.setTextColor(getThemedColor(Theme.key_chat_serviceText));
        this.statusTextView.setGravity(1);
        this.statusTextView.setMaxWidth(AndroidUtilities.dp(210.0f));
        this.textViews.add(this.statusTextView);
        addView(this.statusTextView, LayoutHelper.createLinear(-2, -2, 49));
        goto L8
    L7:
        RLottieImageView r53 = new RLottieImageView(r22);
        r53.setAutoRepeat(true);
        r53.setAnimation(R.raw.utyan_saved_messages, 120, 120);
        r53.playAnimation();
        addView(r53, LayoutHelper.createLinear(-2, -2, 49, 0, 2, 0, 0));
        goto L8
    }

    private int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    private Paint getThemedPaint(String r1) {
        Theme.ResourcesProvider r0 = this.resourcesProvider;
        if (r0 == null) goto L5;
        Paint r02 = r0.getPaint(r1);
    L6:
        if (r02 == null) goto L9;
        return r02;
    L9:
        return Theme.getThemePaint(r1);
    L5:
        r02 = null;
        goto L6
    }

    public void setStatusText(CharSequence r1) {
        this.statusTextView.setText(r1);
    }

    public void setTextColor(int r5) {
        int r0 = 0;
        int r1 = 0;
    L4:
        if (r1 >= this.textViews.size()) goto L7;
        this.textViews.get(r1).setTextColor(r5);
        r1 = r1 + 1;
    L7:
        if (r0 >= this.imageViews.size()) goto L9;
        this.imageViews.get(r0).setColorFilter(new PorterDuffColorFilter(getThemedColor(Theme.key_chat_serviceText), PorterDuff.Mode.MULTIPLY));
        r0 = r0 + 1;
        goto L7
    }
}
