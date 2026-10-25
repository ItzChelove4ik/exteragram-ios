package org.telegram.ui.Cells;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.SharedConfig;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RadioButton;

/* loaded from: classes6.dex */
public abstract class ChatListCell extends LinearLayout {
    private ListView[] listView;

    public class ListView extends FrameLayout {
        private RadioButton button;
        private boolean isThreeLines;
        private RectF rect;
        private TextPaint textPaint;
        final /* synthetic */ ChatListCell this$0;

        /* renamed from: org.telegram.ui.Cells.ChatListCell$ListView$1, reason: invalid class name */
        public class AnonymousClass1 extends RadioButton {
            final /* synthetic */ ListView this$1;
            final /* synthetic */ ChatListCell val$this$0;

            public AnonymousClass1(ListView r1, Context r2, ChatListCell r3) {
                this.this$1 = r1;
                this.val$this$0 = r3;
                super(r2);
            }

            @Override // android.view.View
            public void invalidate() {
                super.invalidate();
                this.this$1.invalidate();
            }
        }

        /* renamed from: -$$Nest$fgetbutton, reason: not valid java name */
        public static /* bridge */ /* synthetic */ RadioButton m6631$$Nest$fgetbutton(ListView r0) {
            return r0.button;
        }

        public ListView(ChatListCell r10, Context r11, boolean r12) {
            this.this$0 = r10;
            super(r11);
            this.rect = new RectF();
            boolean r1 = true;
            this.textPaint = new TextPaint(1);
            setWillNotDraw(false);
            this.isThreeLines = r12;
            if (r12 == false) goto L6;
            int r122 = R.string.ChatListExpanded;
        L7:
            setContentDescription(LocaleController.getString(r122));
            this.textPaint.setTextSize(AndroidUtilities.dp(13.0f));
            AnonymousClass1 r123 = new AnonymousClass1(this, r11, r10);
            this.button = r123;
            r123.setSize(AndroidUtilities.dp(20.0f));
            addView(this.button, LayoutHelper.createFrame(22, 22.0f, 53, 0.0f, 26.0f, 10.0f, 0.0f));
            RadioButton r102 = this.button;
            boolean r9 = this.isThreeLines;
            if (r9 == true) goto L10;
        L11:
            if (r9 == false) goto L13;
        L15:
            r1 = false;
        L16:
            r102.setChecked(r1, false);
            return;
        L13:
            if (SharedConfig.useThreeLinesLayout == true) goto L15;
        L10:
            if (SharedConfig.useThreeLinesLayout == true) goto L16;
        L6:
            r122 = R.string.ChatListDefault;
            goto L7
        }

        @Override // android.view.View
        public void onDraw(Canvas r18) {
            int r2 = Theme.getColor(Theme.key_switchTrack);
            int r3 = Color.red(r2);
            int r4 = Color.green(r2);
            int r22 = Color.blue(r2);
            this.button.setColor(Theme.getColor(Theme.key_radioBackground), Theme.getColor(Theme.key_radioBackgroundChecked));
            this.rect.set(AndroidUtilities.dp(1.0f), AndroidUtilities.dp(1.0f), getMeasuredWidth() - AndroidUtilities.dp(1.0f), AndroidUtilities.dp(73.0f));
            Theme.chat_instantViewRectPaint.setColor(Color.argb((int) (this.button.getProgress() * 43.0f), r3, r4, r22));
            r18.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.chat_instantViewRectPaint);
            this.rect.set(0.0f, 0.0f, getMeasuredWidth(), AndroidUtilities.dp(74.0f));
            Theme.dialogs_onlineCirclePaint.setColor(Color.argb((int) ((1.0f - this.button.getProgress()) * 31.0f), r3, r4, r22));
            r18.drawRoundRect(this.rect, AndroidUtilities.dp(6.0f), AndroidUtilities.dp(6.0f), Theme.dialogs_onlineCirclePaint);
            if (this.isThreeLines == false) goto L6;
            int r5 = R.string.ChatListExpanded;
        L5:
            String r52 = LocaleController.getString(r5);
            int r6 = (int) Math.ceil(this.textPaint.measureText(r52));
            this.textPaint.setColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText));
            int r7 = getMeasuredWidth() - r6;
            int r62 = 2;
            r18.drawText(r52, r7 / 2, AndroidUtilities.dp(96.0f), this.textPaint);
            int r72 = 0;
        L8:
            if (r72 >= r62) goto L38;
            if (r72 != 0) goto L11;
            float r8 = 21.0f;
        L12:
            int r82 = AndroidUtilities.dp(r8);
            Paint r9 = Theme.dialogs_onlineCirclePaint;
            if (r72 != 0) goto L15;
            int r12 = 204;
        L16:
            r9.setColor(Color.argb(r12, r3, r4, r22));
            r18.drawCircle(AndroidUtilities.dp(22.0f), r82, AndroidUtilities.dp(11.0f), Theme.dialogs_onlineCirclePaint);
            int r92 = 0;
        L18:
            if (this.isThreeLines == false) goto L20;
            int r122 = 3;
        L21:
            if (r92 >= r122) goto L37;
            Paint r123 = Theme.dialogs_onlineCirclePaint;
            if (r92 != 0) goto L25;
            int r13 = 204;
        L26:
            r123.setColor(Color.argb(r13, r3, r4, r22));
            boolean r124 = this.isThreeLines;
            RectF r132 = this.rect;
            float r14 = 48.0f;
            if (r124 == false) goto L32;
            float r125 = AndroidUtilities.dp(41.0f);
            float r63 = r82 - AndroidUtilities.dp(8.3f - (r92 * 7));
            int r16 = getMeasuredWidth();
            if (r92 != 0) goto L31;
            r14 = 72.0f;
        L31:
            r132.set(r125, r63, r16 - AndroidUtilities.dp(r14), r82 - AndroidUtilities.dp(5.3f - r5));
            r18.drawRoundRect(this.rect, AndroidUtilities.dpf2(1.5f), AndroidUtilities.dpf2(1.5f), Theme.dialogs_onlineCirclePaint);
        L36:
            r92 = r92 + 1;
            r62 = 2;
            goto L18
        L32:
            float r53 = AndroidUtilities.dp(41.0f);
            int r64 = r92 * 10;
            float r126 = r82 - AndroidUtilities.dp(7 - r64);
            int r162 = getMeasuredWidth();
            if (r92 != 0) goto L35;
            r14 = 72.0f;
        L35:
            r132.set(r53, r126, r162 - AndroidUtilities.dp(r14), r82 - AndroidUtilities.dp(3 - r64));
            r18.drawRoundRect(this.rect, AndroidUtilities.dp(2.0f), AndroidUtilities.dp(2.0f), Theme.dialogs_onlineCirclePaint);
            goto L36
        L25:
            r13 = 90;
            goto L26
        L37:
            r72 = r72 + 1;
            r62 = 2;
            goto L8
        L20:
            r122 = r62;
            goto L21
        L15:
            r12 = 90;
            goto L16
        L11:
            r8 = 53.0f;
            goto L12
        L38:
            return;
        L6:
            r5 = R.string.ChatListDefault;
            goto L5
        }

        @Override // android.view.View
        public void onInitializeAccessibilityNodeInfo(AccessibilityNodeInfo r2) {
            super.onInitializeAccessibilityNodeInfo(r2);
            r2.setClassName(RadioButton.class.getName());
            r2.setChecked(this.button.isChecked());
            r2.setCheckable(true);
            if (this.isThreeLines == false) goto L6;
            int r1 = R.string.ChatListExpanded;
        L7:
            r2.setContentDescription(LocaleController.getString(r1));
            return;
        L6:
            r1 = R.string.ChatListDefault;
            goto L7
        }
    }

    public static /* synthetic */ void $r8$lambda$gQkjn2AH58n8yNohChzsXxjSLNs(ChatListCell r0, boolean r1, View r2) {
        r0.lambda$new$0(r1, r2);
    }

    public ChatListCell(Context r13) {
        super(r13);
        this.listView = new ListView[2];
        setOrientation(0);
        setPadding(AndroidUtilities.dp(21.0f), AndroidUtilities.dp(10.0f), AndroidUtilities.dp(21.0f), 0);
        int r1 = 0;
    L3:
        ListView[] r2 = this.listView;
        if (r1 >= r2.length) goto L14;
        if (r1 != 1) goto L8;
        boolean r4 = true;
    L9:
        r2[r1] = new ListView(this, r13, r4);
        ListView r22 = this.listView[r1];
        if (r1 != 1) goto L12;
        int r8 = 10;
    L13:
        addView(r22, LayoutHelper.createLinear(-1, -1, 0.5f, r8, 0, 0, 0));
        this.listView[r1].setOnClickListener(new ChatListCell$$ExternalSyntheticLambda0(this, r4));
        r1 = r1 + 1;
        goto L3
    L12:
        r8 = 0;
        goto L13
    L8:
        r4 = false;
        goto L9
    }

    private /* synthetic */ void lambda$new$0(boolean r6, View r7) {
        int r1 = 0;
    L4:
        if (r1 >= 2) goto L10;
        RadioButton r2 = ListView.m6631$$Nest$fgetbutton(this.listView[r1]);
        if (this.listView[r1] != r7) goto L8;
        boolean r3 = true;
    L9:
        r2.setChecked(r3, true);
        r1 = r1 + 1;
        goto L4
    L8:
        r3 = false;
        goto L9
    L10:
        didSelectChatType(r6);
    }

    public abstract void didSelectChatType(boolean r1);

    @Override // android.view.View
    public void invalidate() {
        super.invalidate();
        int r0 = 0;
    L3:
        ListView[] r1 = this.listView;
        if (r0 >= r1.length) goto L6;
        r1[r0].invalidate();
        r0 = r0 + 1;
        goto L3
    }

    @Override // android.widget.LinearLayout, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(View.MeasureSpec.makeMeasureSpec(View.MeasureSpec.getSize(r2), TLObject.FLAG_30), View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(123.0f), TLObject.FLAG_30));
    }
}
