package org.telegram.ui.Components;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.TextPaint;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TableRow;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Date;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.DialogObject;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.AvatarSpan;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.ButtonSpan;
import org.telegram.ui.Components.LinkSpanDrawable;
import org.telegram.ui.Components.spoilers.SpoilersTextView;
import org.telegram.ui.Stars.StarsIntroActivity;

/* loaded from: classes7.dex */
public class TableView extends android.widget.TableLayout {
    private final Paint backgroundPaint;
    private final Paint borderPaint;
    private final float hw;
    private final Path path;
    private final float[] radii;
    private final Theme.ResourcesProvider resourcesProvider;
    private final float w;

    /* renamed from: org.telegram.ui.Components.TableView$1, reason: invalid class name */
    public class AnonymousClass1 extends ClickableSpan {
        final /* synthetic */ TableView this$0;
        final /* synthetic */ Runnable val$onCopy;
        final /* synthetic */ CharSequence val$text;

        public AnonymousClass1(TableView r1, CharSequence r2, Runnable r3) {
            this.this$0 = r1;
            this.val$text = r2;
            this.val$onCopy = r3;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            AndroidUtilities.addToClipboard(this.val$text);
            this.val$onCopy.run();
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setColor(r1.linkColor);
        }
    }

    /* renamed from: org.telegram.ui.Components.TableView$2, reason: invalid class name */
    public class AnonymousClass2 extends ClickableSpan {
        final /* synthetic */ TableView this$0;
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass2(TableView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.TableView$3, reason: invalid class name */
    public class AnonymousClass3 implements View.OnAttachStateChangeListener {
        final /* synthetic */ TableView this$0;
        final /* synthetic */ AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable val$emojiDrawable;

        public AnonymousClass3(TableView r1, AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r2) {
            this.this$0 = r1;
            this.val$emojiDrawable = r2;
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewAttachedToWindow(View r1) {
            this.val$emojiDrawable.attach();
        }

        @Override // android.view.View.OnAttachStateChangeListener
        public void onViewDetachedFromWindow(View r1) {
            this.val$emojiDrawable.detach();
        }
    }

    /* renamed from: org.telegram.ui.Components.TableView$4, reason: invalid class name */
    public class AnonymousClass4 extends ClickableSpan {
        final /* synthetic */ TableView this$0;
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass4(TableView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    /* renamed from: org.telegram.ui.Components.TableView$5, reason: invalid class name */
    public class AnonymousClass5 extends ClickableSpan {
        final /* synthetic */ TableView this$0;
        final /* synthetic */ Runnable val$onClick;

        public AnonymousClass5(TableView r1, Runnable r2) {
            this.this$0 = r1;
            this.val$onClick = r2;
        }

        @Override // android.text.style.ClickableSpan
        public void onClick(View r1) {
            Runnable r0 = this.val$onClick;
            if (r0 == null) goto L6;
            r0.run();
            return;
        }

        @Override // android.text.style.ClickableSpan, android.text.style.CharacterStyle
        public void updateDrawState(TextPaint r1) {
            r1.setUnderlineText(false);
        }
    }

    public static class SelectableTextEmojis extends AnimatedEmojiSpan.TextViewEmojis {
        private ArrayList<TLRPC.MessageEntity> entities;
        private CharSequence originalText;

        public SelectableTextEmojis(Context r1) {
            super(r1);
        }

        @Override // android.widget.TextView
        public boolean onTextContextMenuItem(int r2) {
            if (r2 != 16908321) goto L11;
            if (this.originalText == null) goto L11;
            if (this.entities == null) goto L11;
            TLRPC.TL_textWithEntities r22 = new TLRPC.TL_textWithEntities();
            r22.text = this.originalText.toString();
            r22.entities = this.entities;
            AndroidUtilities.addToClipboard(MessageObject.formatTextWithEntities(r22, false));
            return true;
        L11:
            return super.onTextContextMenuItem(r2);
        }

        public void setTextWithEntities(CharSequence r1, ArrayList<TLRPC.MessageEntity> r2) {
            this.originalText = r1;
            this.entities = r2;
        }
    }

    public static class TableRowContent extends FrameLayout {
        private boolean first;
        private boolean last;
        private boolean left;
        private final Theme.ResourcesProvider resourcesProvider;
        private boolean right;
        private final TableView table;

        public TableRowContent(TableView r2, View r3) {
            this(r2, r3, false);
        }

        public TableRowContent(TableView r3, View r4, boolean r5) {
            super(r3.getContext());
            this.left = false;
            this.right = true;
            this.table = r3;
            this.resourcesProvider = TableView.m13200$$Nest$fgetresourcesProvider(r3);
            setWillNotDraw(false);
            if (r5 == true) goto L5;
            setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        L5:
            addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r8) {
            if (this.first == false) goto L5;
        L6:
            Canvas r1 = r8;
            float r82 = AndroidUtilities.dp(10.0f);
            float[] r0 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r2 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.first == true) goto L11;
        L13:
            float r3 = 0.0f;
        L14:
            r2[1] = r3;
            r0[0] = r3;
            float[] r02 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r22 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.first == true) goto L17;
        L19:
            float r32 = 0.0f;
        L20:
            r22[3] = r32;
            r02[2] = r32;
            float[] r03 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r23 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.last == true) goto L23;
        L25:
            float r33 = 0.0f;
        L26:
            r23[5] = r33;
            r03[4] = r33;
            float[] r04 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r24 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.last == true) goto L29;
        L31:
            r82 = 0.0f;
        L32:
            r24[7] = r82;
            r04[6] = r82;
            TableView.m13198$$Nest$fgetpath(this.table).rewind();
            RectF r83 = AndroidUtilities.rectTmp;
            float r05 = TableView.m13197$$Nest$fgethw(this.table);
            float r25 = TableView.m13197$$Nest$fgethw(this.table);
            float r34 = getWidth() - TableView.m13197$$Nest$fgethw(this.table);
            float r4 = getHeight();
            float r5 = TableView.m13197$$Nest$fgethw(this.table);
            if (this.last == false) goto L35;
            float r6 = -1.0f;
        L36:
            r83.set(r05, r25, r34, r4 + (r5 * AndroidUtilities.dp(r6)));
            if (this.right == true) goto L39;
            r83.right += TableView.m13201$$Nest$fgetw(this.table);
        L39:
            TableView.m13198$$Nest$fgetpath(this.table).addRoundRect(r83, TableView.m13199$$Nest$fgetradii(this.table), Path.Direction.CW);
            r1.drawPath(TableView.m13198$$Nest$fgetpath(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
        L40:
            super.onDraw(r1);
            return;
        L35:
            r6 = 1.0f;
            goto L36
        L29:
            if (this.left == false) goto L31;
        L23:
            if (this.right == false) goto L25;
            r33 = r82;
            goto L26
        L17:
            if (this.right == false) goto L19;
            r32 = r82;
            goto L20
        L11:
            if (this.left == false) goto L13;
            r3 = r82;
            goto L14
        L5:
            if (this.last == true) goto L6;
            r1 = r8;
            r1.drawRect(TableView.m13197$$Nest$fgethw(this.table), TableView.m13197$$Nest$fgethw(this.table), getWidth() - TableView.m13197$$Nest$fgethw(this.table), getHeight() + TableView.m13197$$Nest$fgethw(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
            goto L40
        }

        public void setFirstLast(boolean r2, boolean r3) {
            if (this.first == r2) goto L5;
        L8:
            this.first = r2;
            this.last = r3;
            invalidate();
            return;
        L5:
            if (this.last != r3) goto L8;
        }

        public void setLeftRight(boolean r2, boolean r3) {
            if (this.left == r2) goto L5;
        L8:
            this.left = r2;
            this.right = r3;
            invalidate();
            return;
        L5:
            if (this.right != r3) goto L8;
        }
    }

    public static class TableRowFullContent extends FrameLayout {
        private boolean filled;
        private boolean first;
        private boolean last;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TableView table;

        public TableRowFullContent(TableView r2, View r3) {
            this(r2, r3, false);
        }

        public TableRowFullContent(TableView r3, View r4, boolean r5) {
            super(r3.getContext());
            this.table = r3;
            this.resourcesProvider = TableView.m13200$$Nest$fgetresourcesProvider(r3);
            setWillNotDraw(false);
            if (r5 == true) goto L5;
            setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        L5:
            addView(r4, LayoutHelper.createFrame(-1, -1.0f));
        }

        @Override // android.view.View
        public void onDraw(Canvas r14) {
            if (this.first == false) goto L5;
        L6:
            Canvas r1 = r14;
            float r142 = AndroidUtilities.dp(10.0f);
            float[] r0 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r2 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.first == false) goto L15;
            float r3 = r142;
        L16:
            r2[1] = r3;
            r0[0] = r3;
            float[] r02 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r22 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.first == false) goto L19;
            float r32 = r142;
        L20:
            r22[3] = r32;
            r02[2] = r32;
            float[] r03 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r23 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.last == false) goto L23;
            float r33 = r142;
        L24:
            r23[5] = r33;
            r03[4] = r33;
            float[] r04 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r24 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.last == true) goto L28;
            r142 = 0.0f;
        L28:
            r24[7] = r142;
            r04[6] = r142;
            TableView.m13198$$Nest$fgetpath(this.table).rewind();
            RectF r143 = AndroidUtilities.rectTmp;
            float r05 = TableView.m13197$$Nest$fgethw(this.table);
            float r25 = TableView.m13197$$Nest$fgethw(this.table);
            float r34 = getWidth() - TableView.m13197$$Nest$fgethw(this.table);
            float r4 = getHeight();
            float r5 = TableView.m13197$$Nest$fgethw(this.table);
            if (this.last == false) goto L31;
            float r6 = -1.0f;
        L32:
            r143.set(r05, r25, r34, r4 + (r5 * AndroidUtilities.dp(r6)));
            TableView.m13198$$Nest$fgetpath(this.table).addRoundRect(r143, TableView.m13199$$Nest$fgetradii(this.table), Path.Direction.CW);
            if (this.filled == false) goto L35;
            r1.drawPath(TableView.m13198$$Nest$fgetpath(this.table), TableView.m13195$$Nest$fgetbackgroundPaint(this.table));
        L35:
            r1.drawPath(TableView.m13198$$Nest$fgetpath(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
        L36:
            super.onDraw(r1);
            return;
        L31:
            r6 = 1.0f;
            goto L32
        L23:
            r33 = 0.0f;
            goto L24
        L19:
            r32 = 0.0f;
            goto L20
        L15:
            r3 = 0.0f;
            goto L16
        L5:
            if (this.last == true) goto L6;
            if (this.filled == false) goto L10;
            r1 = r14;
            r1.drawRect(TableView.m13197$$Nest$fgethw(this.table), TableView.m13197$$Nest$fgethw(this.table), getWidth() + TableView.m13197$$Nest$fgethw(this.table), getHeight() + TableView.m13197$$Nest$fgethw(this.table), TableView.m13195$$Nest$fgetbackgroundPaint(this.table));
        L11:
            r1.drawRect(TableView.m13197$$Nest$fgethw(this.table), TableView.m13197$$Nest$fgethw(this.table), getWidth() - TableView.m13197$$Nest$fgethw(this.table), getHeight() + TableView.m13197$$Nest$fgethw(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
            goto L36
        L10:
            r1 = r14;
            goto L11
        }

        public void setFilled(boolean r1) {
            this.filled = r1;
        }

        public void setFirstLast(boolean r2, boolean r3) {
            if (this.first == r2) goto L5;
        L8:
            this.first = r2;
            this.last = r3;
            invalidate();
            return;
        L5:
            if (this.last != r3) goto L8;
        }
    }

    public static class TableRowTitle extends TextView {
        private boolean first;
        private boolean last;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TableView table;

        public TableRowTitle(TableView r5, CharSequence r6) {
            super(r5.getContext());
            this.table = r5;
            Theme.ResourcesProvider r52 = TableView.m13200$$Nest$fgetresourcesProvider(r5);
            this.resourcesProvider = r52;
            setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
            setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, r52));
            setTypeface(AndroidUtilities.bold());
            setTextSize(1, 14.0f);
            setText(r6);
        }

        @Override // android.widget.TextView, android.view.View
        public void onDraw(Canvas r14) {
            if (this.first == false) goto L5;
        L6:
            Canvas r1 = r14;
            float r142 = AndroidUtilities.dp(10.0f);
            float[] r0 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r2 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.first == false) goto L11;
            float r3 = r142;
        L12:
            r2[1] = r3;
            r0[0] = r3;
            float[] r02 = TableView.m13199$$Nest$fgetradii(this.table);
            TableView.m13199$$Nest$fgetradii(this.table)[3] = 0.0f;
            r02[2] = 0.0f;
            float[] r03 = TableView.m13199$$Nest$fgetradii(this.table);
            TableView.m13199$$Nest$fgetradii(this.table)[5] = 0.0f;
            r03[4] = 0.0f;
            float[] r04 = TableView.m13199$$Nest$fgetradii(this.table);
            float[] r22 = TableView.m13199$$Nest$fgetradii(this.table);
            if (this.last == true) goto L16;
            r142 = 0.0f;
        L16:
            r22[7] = r142;
            r04[6] = r142;
            TableView.m13198$$Nest$fgetpath(this.table).rewind();
            RectF r143 = AndroidUtilities.rectTmp;
            float r05 = TableView.m13197$$Nest$fgethw(this.table);
            float r23 = TableView.m13197$$Nest$fgethw(this.table);
            float r32 = getWidth() + TableView.m13197$$Nest$fgethw(this.table);
            float r4 = getHeight();
            float r5 = TableView.m13197$$Nest$fgethw(this.table);
            if (this.last == false) goto L19;
            float r6 = -1.0f;
        L20:
            r143.set(r05, r23, r32, r4 + (r5 * AndroidUtilities.dp(r6)));
            TableView.m13198$$Nest$fgetpath(this.table).addRoundRect(r143, TableView.m13199$$Nest$fgetradii(this.table), Path.Direction.CW);
            r1.drawPath(TableView.m13198$$Nest$fgetpath(this.table), TableView.m13195$$Nest$fgetbackgroundPaint(this.table));
            r1.drawPath(TableView.m13198$$Nest$fgetpath(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
        L21:
            super.onDraw(r1);
            return;
        L19:
            r6 = 1.0f;
            goto L20
        L11:
            r3 = 0.0f;
            goto L12
        L5:
            if (this.last == true) goto L6;
            r1 = r14;
            r1.drawRect(TableView.m13197$$Nest$fgethw(this.table), TableView.m13197$$Nest$fgethw(this.table), getWidth() + TableView.m13197$$Nest$fgethw(this.table), getHeight() + TableView.m13197$$Nest$fgethw(this.table), TableView.m13195$$Nest$fgetbackgroundPaint(this.table));
            r1.drawRect(TableView.m13197$$Nest$fgethw(this.table), TableView.m13197$$Nest$fgethw(this.table), getWidth() + TableView.m13197$$Nest$fgethw(this.table), getHeight() + TableView.m13197$$Nest$fgethw(this.table), TableView.m13196$$Nest$fgetborderPaint(this.table));
            goto L21
        }

        public void setFirstLast(boolean r2, boolean r3) {
            if (this.first == r2) goto L5;
        L8:
            this.first = r2;
            this.last = r3;
            invalidate();
            return;
        L5:
            if (this.last != r3) goto L8;
        }
    }

    /* renamed from: $r8$lambda$94D2tet7nd1WDB-XBAcVpSQDBqE, reason: not valid java name */
    public static /* synthetic */ void m13194$r8$lambda$94D2tet7nd1WDBXBAcVpSQDBqE(CharSequence r0, Runnable r1, View r2) {
        AndroidUtilities.addToClipboard(r0);
        r1.run();
    }

    public static /* synthetic */ void $r8$lambda$rEbVSy3GeF1keOVG62K5kjVlt_A(long r7, int r9, AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r10, LinkSpanDrawable.LinksSimpleTextView r11, Drawable r12, int r13, Object[] r14) {
        if (r7 != UserObject.ANONYMOUS) goto L5;
        return;
    L5:
        if (UserObject.isService(r7) == false) goto L8;
        return;
    L8:
        if (r7 <= 0) goto L17;
        TLRPC.User r72 = MessagesController.getInstance(r9).getUser(Long.valueOf(r7));
        if (r72 == null) goto L12;
        TLRPC.EmojiStatus r8 = r72.emoji_status;
    L13:
        if (r72 != null) goto L15;
    L21:
        boolean r73 = false;
    L22:
        long r5 = DialogObject.getEmojiStatusDocumentId(r8);
        if (r5 == 0) goto L25;
        r10.set(r5, true);
        r10.setParticles(DialogObject.isEmojiStatusCollectible(r8), true);
        r11.setRightDrawable(r10);
    L28:
        r10.setColor(Integer.valueOf(r13));
        return;
    L25:
        if (r73 == false) goto L27;
        r10.set(r12, true);
        r10.setParticles(false, true);
        r11.setRightDrawable(r10);
        goto L28
    L27:
        r11.setRightDrawable(null);
        goto L28
    L15:
        if (r72.premium == false) goto L21;
        r73 = true;
        goto L22
    L12:
        r8 = null;
        goto L13
    L17:
        TLRPC.Chat r74 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r7));
        if (r74 == null) goto L20;
        r8 = r74.emoji_status;
        goto L21
    L20:
        r8 = null;
        goto L21
    }

    /* renamed from: -$$Nest$fgetbackgroundPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13195$$Nest$fgetbackgroundPaint(TableView r0) {
        return r0.backgroundPaint;
    }

    /* renamed from: -$$Nest$fgetborderPaint, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Paint m13196$$Nest$fgetborderPaint(TableView r0) {
        return r0.borderPaint;
    }

    /* renamed from: -$$Nest$fgethw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13197$$Nest$fgethw(TableView r0) {
        return r0.hw;
    }

    /* renamed from: -$$Nest$fgetpath, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Path m13198$$Nest$fgetpath(TableView r0) {
        return r0.path;
    }

    /* renamed from: -$$Nest$fgetradii, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float[] m13199$$Nest$fgetradii(TableView r0) {
        return r0.radii;
    }

    /* renamed from: -$$Nest$fgetresourcesProvider, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Theme.ResourcesProvider m13200$$Nest$fgetresourcesProvider(TableView r0) {
        return r0.resourcesProvider;
    }

    /* renamed from: -$$Nest$fgetw, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m13201$$Nest$fgetw(TableView r0) {
        return r0.w;
    }

    public TableView(Context r3, Theme.ResourcesProvider r4) {
        super(r3);
        this.path = new Path();
        this.radii = new float[8];
        this.backgroundPaint = new Paint(1);
        this.borderPaint = new Paint(1);
        float r32 = Math.max(1, AndroidUtilities.dp(0.66f));
        this.w = r32;
        this.hw = r32 / 2.0f;
        this.resourcesProvider = r4;
        setClipToPadding(false);
        setColumnStretchable(1, true);
    }

    public TableRowFullContent addFullRow(CharSequence r6) {
        SpoilersTextView r0 = new SpoilersTextView(getContext());
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r0.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        r0.setText(Emoji.replaceEmoji(r6, r0.getPaint().getFontMetricsInt(), false));
        NotificationCenter.listenEmojiLoading(r0);
        r0.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        TableRow r62 = new TableRow(getContext());
        TableRow.LayoutParams r1 = new TableRow.LayoutParams(-2, -1);
        r1.span = 2;
        TableRowFullContent r3 = new TableRowFullContent(this, r0, true);
        r62.addView(r3, r1);
        addView(r62);
        return r3;
    }

    public void addFullRow(CharSequence r10, ArrayList<TLRPC.MessageEntity> r11) {
        SelectableTextEmojis r0 = new SelectableTextEmojis(getContext());
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        r0.setTextIsSelectable(true);
        r0.setTextWithEntities(r10, r11);
        SpannableStringBuilder r3 = new SpannableStringBuilder(r10);
        MessageObject.addEntitiesToText(r3, r11, false, false, false, false);
        r0.setText(MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r3, r0.getPaint().getFontMetricsInt(), false), r11, r0.getPaint().getFontMetricsInt()));
        NotificationCenter.listenEmojiLoading(r0);
        TableRow r102 = new TableRow(getContext());
        TableRow.LayoutParams r112 = new TableRow.LayoutParams(-2, -1);
        r112.span = 2;
        r102.addView(new TableRowFullContent(this, r0), r112);
        addView(r102);
    }

    public TableRow addRow(CharSequence r2, CharSequence r3) {
        return addRow(r2, r3, null);
    }

    public TableRow addRow(CharSequence r7, CharSequence r8, CharSequence r9, Runnable r10) {
        return addRow(r7, r8, r9, r10, null);
    }

    public TableRow addRow(CharSequence r5, CharSequence r6, CharSequence r7, Runnable r8, Integer r9) {
        ButtonSpan.TextViewButtons r0 = new ButtonSpan.TextViewButtons(getContext());
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        SpannableStringBuilder r1 = new SpannableStringBuilder(Emoji.replaceEmoji(r6, r0.getPaint().getFontMetricsInt(), false));
        if (r7 == null) goto L5;
        r1.append(" ").append(ButtonSpan.make(r7, r8, this.resourcesProvider, r9));
    L5:
        r0.setText(r1);
        NotificationCenter.listenEmojiLoading(r0);
        TableRow r62 = new TableRow(getContext());
        TableRow.LayoutParams r72 = new TableRow.LayoutParams(-2, -1);
        r62.addView(new TableRowTitle(this, r5), r72);
        TableRow.LayoutParams r52 = new TableRow.LayoutParams(0, -1, 1.0f);
        r62.addView(new TableRowContent(this, r0), r52);
        addView(r62);
        return r62;
    }

    public TableRow addRow(CharSequence r2, CharSequence r3, ButtonSpan.TextViewButtons[] r4) {
        return addRow(r2, r3, null, r4);
    }

    public TableRow addRow(CharSequence r6, CharSequence r7, TableRowTitle[] r8, ButtonSpan.TextViewButtons[] r9) {
        ButtonSpan.TextViewButtons r0 = new ButtonSpan.TextViewButtons(getContext());
        r0.setTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteBlackText, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        r0.setText(Emoji.replaceEmoji(r7, r0.getPaint().getFontMetricsInt(), false));
        NotificationCenter.listenEmojiLoading(r0);
        TableRow r72 = new TableRow(getContext());
        TableRow.LayoutParams r1 = new TableRow.LayoutParams(-2, -1);
        TableRowTitle r3 = new TableRowTitle(this, r6);
        if (r8 == null) goto L5;
        r8[0] = r3;
    L5:
        r72.addView(r3, r1);
        TableRow.LayoutParams r62 = new TableRow.LayoutParams(0, -1, 1.0f);
        r72.addView(new TableRowContent(this, r0), r62);
        addView(r72);
        if (r9 == null) goto L8;
        r9[0] = r0;
    L8:
        return r72;
    }

    public TableRow addRowDateTime(CharSequence r8, int r9) {
        long r3 = r9 * 1000;
        return addRow(r8, LocaleController.formatString(R.string.formatDateAtTime, new Object[]{LocaleController.getInstance().getFormatterGiveawayCard().format(new Date(r3)), LocaleController.getInstance().getFormatterDay().format(new Date(r3))}));
    }

    public TableRow addRowLink(CharSequence r6, CharSequence r7, Runnable r8) {
        LinkSpanDrawable.LinksTextView r0 = new LinkSpanDrawable.LinksTextView(getContext(), this.resourcesProvider);
        r0.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r0.setEllipsize(TextUtils.TruncateAt.END);
        int r1 = Theme.key_chat_messageLinkIn;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        r0.setLinkTextColor(Theme.getColor(r1, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        r0.setSingleLine(true);
        r0.setDisablePaddingsOffsetY(true);
        SpannableStringBuilder r12 = new SpannableStringBuilder(r7);
        r12.setSpan(new AnonymousClass5(this, r8), 0, r12.length(), 33);
        r0.setText(r12);
        return addRowUnpadded(r6, r0);
    }

    public TableRow addRowMonospaced(CharSequence r10, CharSequence r11, int r12, Runnable r13) {
        FrameLayout r0 = new FrameLayout(getContext());
        r0.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(10.66f), AndroidUtilities.dp(9.33f));
        TextView r1 = new TextView(getContext());
        r1.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        r1.setTextSize(1, r12);
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r1.setMaxLines(4);
        r1.setSingleLine(false);
        r1.setText(r11);
        r0.addView(r1, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 34.0f, 0.0f));
        if (r13 == null) goto L6;
        ImageView r122 = new ImageView(getContext());
        r122.setImageResource(R.drawable.msg_copy);
        r122.setScaleType(ImageView.ScaleType.CENTER);
        int r2 = Theme.key_windowBackgroundWhiteBlueIcon;
        r122.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
        r122.setOnClickListener(new TableView$$ExternalSyntheticLambda0(r11, r13));
        ScaleStateListAnimator.apply(r122);
        r122.setBackground(Theme.createSelectorDrawable(Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.1f), 7));
        r0.addView(r122, LayoutHelper.createFrame(30, 30, 21));
    L6:
        return addRowUnpadded(r10, r0);
    }

    public TableRow addRowUnpadded(CharSequence r5, View r6) {
        TableRow r0 = new TableRow(getContext());
        TableRow.LayoutParams r1 = new TableRow.LayoutParams(-2, -1);
        r0.addView(new TableRowTitle(this, r5), r1);
        TableRow.LayoutParams r52 = new TableRow.LayoutParams(0, -1, 1.0f);
        r0.addView(new TableRowContent(this, r6, true), r52);
        addView(r0);
        return r0;
    }

    public TableRow addRowUser(CharSequence r9, int r10, long r11, Runnable r13) {
        return addRowUser(r9, r10, r11, r13, null, null);
    }

    public TableRow addRowUser(CharSequence r8, int r9, long r10, Runnable r12, CharSequence r13, Runnable r14) {
        ButtonSpan.TextViewButtons r0 = new ButtonSpan.TextViewButtons(getContext(), this.resourcesProvider);
        r0.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        r0.setEllipsize(TextUtils.TruncateAt.END);
        int r1 = Theme.key_chat_messageLinkIn;
        r0.setTextColor(Theme.getColor(r1, this.resourcesProvider));
        r0.setLinkTextColor(Theme.getColor(r1, this.resourcesProvider));
        r0.setTextSize(1, 14.0f);
        r0.setSingleLine(true);
        r0.setDisablePaddingsOffsetY(true);
        AvatarSpan r15 = new AvatarSpan(r0, r9, 24.0f);
        if (r10 != UserObject.ANONYMOUS) goto L6;
        String r92 = LocaleController.getString(R.string.StarsTransactionHidden);
        CombinedDrawable r102 = StarsIntroActivity.StarsTransactionView.getPlatformDrawable("anonymous");
        r102.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r15.setImageDrawable(r102);
        boolean r103 = false;
        boolean r11 = false;
    L24:
        SpannableStringBuilder r3 = new SpannableStringBuilder("x  " + r92);
        r3.setSpan(r15, 0, 1, 33);
        if (r11 == false) goto L27;
        r3.setSpan(new AnonymousClass4(this, r12), 3, r3.length(), 33);
    L27:
        if (r13 == null) goto L29;
        r0.addButton(new ButtonSpan(r13, r14, this.resourcesProvider));
    L29:
        r0.setText(r3);
        if (r103 == false) goto L32;
        return null;
    L32:
        return addRowUnpadded(r8, r0);
    L6:
        if (UserObject.isService(r10) == false) goto L9;
        r92 = LocaleController.getString(R.string.StarsTransactionUnknown);
        CombinedDrawable r104 = StarsIntroActivity.StarsTransactionView.getPlatformDrawable("fragment");
        r104.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r15.setImageDrawable(r104);
        r11 = true;
        r103 = false;
        goto L24
    L9:
        if (r10 < 0) goto L16;
        TLRPC.User r93 = MessagesController.getInstance(r9).getUser(Long.valueOf(r10));
        if (r93 != null) goto L13;
        r103 = true;
    L14:
        String r112 = UserObject.getUserName(r93);
        r15.setUser(r93);
    L15:
        r92 = r112;
        r11 = true;
        goto L24
    L13:
        r103 = false;
        goto L14
    L16:
        TLRPC.Chat r94 = MessagesController.getInstance(r9).getChat(Long.valueOf(-r10));
        if (r94 != null) goto L19;
        r103 = true;
    L20:
        if (r94 != null) goto L22;
        r112 = _UrlKt.FRAGMENT_ENCODE_SET;
    L23:
        r15.setChat(r94);
        goto L15
    L22:
        r112 = r94.title;
        goto L23
    L19:
        r103 = false;
        goto L20
    }

    public TableRow addRowUserWithEmojiStatus(CharSequence r11, int r12, long r13, Runnable r15) {
        LinkSpanDrawable.LinksSimpleTextView r5 = new LinkSpanDrawable.LinksSimpleTextView(getContext(), this.resourcesProvider);
        r5.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f));
        int r0 = Theme.key_featuredStickers_addButton;
        r5.setTextColor(Theme.getColor(r0, this.resourcesProvider));
        r5.setLinkTextColor(Theme.getColor(r0, this.resourcesProvider));
        r5.setTextSize(14);
        AvatarSpan r1 = new AvatarSpan(r5, r12, 24.0f);
        if (r13 != UserObject.ANONYMOUS) goto L6;
        String r2 = LocaleController.getString(R.string.StarsTransactionHidden);
        CombinedDrawable r7 = StarsIntroActivity.StarsTransactionView.getPlatformDrawable("anonymous");
        r7.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r1.setImageDrawable(r7);
        boolean r4 = false;
    L18:
        SpannableStringBuilder r8 = new SpannableStringBuilder("x  " + r2);
        r8.setSpan(r1, 0, 1, 33);
        if (r4 == false) goto L21;
        r5.setClickable(true);
        r8.setSpan(new AnonymousClass2(this, r15), 3, r8.length(), 33);
    L21:
        int r72 = Theme.getColor(r0, this.resourcesProvider);
        AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable r42 = new AnimatedEmojiDrawable.SwapAnimatedEmojiDrawable(r5, AndroidUtilities.dp(20.0f));
        r42.setColor(Integer.valueOf(r72));
        r42.offset(AndroidUtilities.dp(12.0f), 0);
        r5.addOnAttachStateChangeListener(new AnonymousClass3(this, r42));
        Drawable r6 = getContext().getResources().getDrawable(R.drawable.msg_premium_liststar).mutate();
        r6.setColorFilter(new PorterDuffColorFilter(r72, PorterDuff.Mode.SRC_IN));
        Utilities.Callback<Object[]> r02 = new TableView$$ExternalSyntheticLambda1(r13, r12, r42, r5, r6, r72);
        r02.run(null);
        r5.setRightDrawable(r42);
        NotificationCenter.getInstance(r12).listen(r5, NotificationCenter.updateInterfaces, r02);
        NotificationCenter.getInstance(r12).listen(r5, NotificationCenter.userEmojiStatusUpdated, r02);
        r5.setText(r8);
        return addRowUnpadded(r11, r5);
    L6:
        if (UserObject.isService(r13) == false) goto L10;
        r2 = LocaleController.getString(R.string.StarsTransactionUnknown);
        CombinedDrawable r73 = StarsIntroActivity.StarsTransactionView.getPlatformDrawable("fragment");
        r73.setIconSize(AndroidUtilities.dp(16.0f), AndroidUtilities.dp(16.0f));
        r1.setImageDrawable(r73);
    L8:
        r4 = true;
        goto L18
    L10:
        if (r13 < 0) goto L13;
        TLRPC.User r22 = MessagesController.getInstance(r12).getUser(Long.valueOf(r13));
        String r43 = UserObject.getUserName(r22);
        r1.setUser(r22);
    L12:
        r2 = r43;
        goto L8
    L13:
        TLRPC.Chat r23 = MessagesController.getInstance(r12).getChat(Long.valueOf(-r13));
        if (r23 != null) goto L16;
        r43 = _UrlKt.FRAGMENT_ENCODE_SET;
    L17:
        r1.setChat(r23);
        goto L12
    L16:
        r43 = r23.title;
        goto L17
    }

    public TableRow addWalletAddressRow(CharSequence r12, CharSequence r13, Runnable r14) {
        FrameLayout r0 = new FrameLayout(getContext());
        LinkSpanDrawable.LinksTextView r1 = new LinkSpanDrawable.LinksTextView(getContext());
        r1.setTypeface(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MONO));
        r1.setTextSize(1, 13.0f);
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, this.resourcesProvider));
        r1.setLinkTextColor(Theme.getColor(Theme.key_chat_messageLinkIn, this.resourcesProvider));
        r1.setMaxLines(1);
        r1.setSingleLine();
        r1.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        SpannableStringBuilder r2 = new SpannableStringBuilder(r13);
        if (r14 == null) goto L5;
        r2.setSpan(new AnonymousClass1(this, r13, r14), 0, r2.length(), 33);
    L5:
        r1.setText(r2);
        r1.setDisablePaddingsOffsetY(true);
        r1.setPadding(AndroidUtilities.dp(12.66f), AndroidUtilities.dp(9.33f), AndroidUtilities.dp(10.66f), AndroidUtilities.dp(9.33f));
        r0.addView(r1, LayoutHelper.createFrame(-1, -1.0f, 119, 0.0f, 0.0f, 0.0f, 0.0f));
        return addRowUnpadded(r12, r0);
    }

    public void clear() {
        removeAllViews();
    }

    @Override // android.widget.TableLayout, android.widget.LinearLayout, android.view.ViewGroup, android.view.View
    public void onLayout(boolean r6, int r7, int r8, int r9, int r10) {
        super.onLayout(r6, r7, r8, r9, r10);
        this.borderPaint.setStyle(Paint.Style.STROKE);
        this.borderPaint.setStrokeWidth(this.w);
        this.borderPaint.setColor(Theme.getColor(Theme.key_table_border, this.resourcesProvider));
        this.backgroundPaint.setStyle(Paint.Style.FILL);
        this.backgroundPaint.setColor(Theme.getColor(Theme.key_table_background, this.resourcesProvider));
        int r62 = getChildCount();
        int r82 = 0;
    L3:
        if (r82 >= r62) goto L52;
        if ((getChildAt(r82) instanceof TableRow) == false) goto L51;
        TableRow r92 = (TableRow) getChildAt(r82);
        int r102 = r92.getChildCount();
        int r0 = 0;
    L8:
        if (r0 >= r102) goto L51;
        View r1 = r92.getChildAt(r0);
        boolean r3 = true;
        if ((r1 instanceof TableRowTitle) == false) goto L21;
        TableRowTitle r12 = (TableRowTitle) r1;
        if (r82 != 0) goto L14;
        boolean r2 = true;
    L16:
        if (r82 == (r62 - 1)) goto L19;
        r3 = false;
    L19:
        r12.setFirstLast(r2, r3);
    L50:
        r0 = r0 + 1;
        goto L8
    L14:
        r2 = false;
        goto L16
    L21:
        if ((r1 instanceof TableRowContent) == false) goto L40;
        TableRowContent r13 = (TableRowContent) r1;
        if (r82 != 0) goto L25;
        boolean r22 = true;
    L27:
        if (r82 != (r62 - 1)) goto L29;
        boolean r4 = true;
    L30:
        r13.setFirstLast(r22, r4);
        if (r0 != 0) goto L33;
        boolean r23 = true;
    L35:
        if (r0 == (r102 - 1)) goto L38;
        r3 = false;
    L38:
        r13.setLeftRight(r23, r3);
        goto L50
    L33:
        r23 = false;
        goto L35
    L29:
        r4 = false;
        goto L30
    L25:
        r22 = false;
        goto L27
    L40:
        if ((r1 instanceof TableRowFullContent) == false) goto L50;
        TableRowFullContent r14 = (TableRowFullContent) r1;
        if (r82 != 0) goto L44;
        boolean r24 = true;
    L46:
        if (r82 == (r62 - 1)) goto L49;
        r3 = false;
    L49:
        r14.setFirstLast(r24, r3);
        goto L50
    L44:
        r24 = false;
    L51:
        r82 = r82 + 1;
        goto L3
    }
}
