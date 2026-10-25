package org.telegram.messenger;

import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.LongSparseArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.HashMap;
import okhttp3.internal.url._UrlKt;
import org.telegram.SQLite.SQLiteCursor;
import org.telegram.SQLite.SQLiteDatabase;
import org.telegram.SQLite.SQLitePreparedStatement;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.ConnectionsManager;
import org.telegram.tgnet.NativeByteBuffer;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.Vector;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.AlertDialogDecor;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedColor;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.BulletinFactory;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.EditTextCaption;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.SizeNotifierFrameLayout;
import org.telegram.ui.Components.TypefaceSpan;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes5.dex */
public class FactCheckController {
    private static volatile FactCheckController[] Instance;
    private static AlertDialog currentDialog;
    private static final Object[] lockObjects = null;
    private boolean clearedExpiredInDatabase;
    public final int currentAccount;
    private final Runnable loadMissingRunnable;
    private final ArrayList<Key> loading;
    private final LongSparseArray<TLRPC.TL_factCheck> localCache;
    private final LongSparseArray<HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>>> toload;

    /* renamed from: org.telegram.messenger.FactCheckController$1, reason: invalid class name */
    public class AnonymousClass1 extends EditTextCaption {
        AnimatedTextView.AnimatedTextDrawable limit;
        AnimatedColor limitColor;
        private int limitCount;
        final /* synthetic */ FactCheckController this$0;
        final /* synthetic */ int val$MAX_LENGTH;
        final /* synthetic */ Theme.ResourcesProvider val$resourcesProvider;

        public AnonymousClass1(FactCheckController r8, Context r9, Theme.ResourcesProvider r10, int r11, Theme.ResourcesProvider r12) {
            this.this$0 = r8;
            this.val$MAX_LENGTH = r11;
            this.val$resourcesProvider = r12;
            super(r9, r10);
            this.limitColor = new AnimatedColor(this);
            AnimatedTextView.AnimatedTextDrawable r0 = new AnimatedTextView.AnimatedTextDrawable(false, true, true);
            this.limit = r0;
            r0.setAnimationProperties(0.2f, 0, 160, CubicBezierInterpolator.EASE_OUT_QUINT);
            this.limit.setTextSize(AndroidUtilities.dp(15.33f));
            this.limit.setCallback(this);
            this.limit.setGravity(5);
        }

        @Override // android.view.View
        public void dispatchDraw(Canvas r6) {
            super.dispatchDraw(r6);
            AnimatedTextView.AnimatedTextDrawable r0 = this.limit;
            AnimatedColor r1 = this.limitColor;
            if (this.limitCount >= 0) goto L5;
            int r2 = Theme.key_text_RedRegular;
        L6:
            r0.setTextColor(r1.set(Theme.getColor(r2, this.val$resourcesProvider)));
            this.limit.setBounds(getScrollX(), 0, getScrollX() + getWidth(), getHeight());
            this.limit.draw(r6);
            return;
        L5:
            r2 = Theme.key_dialogSearchHint;
            goto L6
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor
        public void extendActionMode(ActionMode r5, Menu r6) {
            if (r6.findItem(R.id.menu_bold) == null) goto L5;
            return;
        L5:
            r6.removeItem(android.R.id.shareText);
            SpannableStringBuilder r4 = new SpannableStringBuilder(LocaleController.getString(R.string.Bold));
            r4.setSpan(new TypefaceSpan(AndroidUtilities.bold()), 0, r4.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_bold, 6, r4);
            SpannableStringBuilder r42 = new SpannableStringBuilder(LocaleController.getString(R.string.Italic));
            r42.setSpan(new TypefaceSpan(AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC)), 0, r42.length(), 33);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_italic, 7, r42);
            r6.add(R.id.menu_groupbolditalic, R.id.menu_link, 8, LocaleController.getString(R.string.CreateLink));
            r6.add(R.id.menu_groupbolditalic, R.id.menu_regular, 9, LocaleController.getString(R.string.Regular));
        }

        @Override // org.telegram.ui.Components.EditTextBoldCursor, org.telegram.ui.Components.EditTextEffects, com.exteragram.messenger.components.ReceiveContentEditText, android.widget.TextView
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
            super.onTextChanged(r1, r2, r3, r4);
            if (this.limit == null) goto L10;
            this.limitCount = this.val$MAX_LENGTH - r1.length();
            this.limit.cancelAnimation();
            AnimatedTextView.AnimatedTextDrawable r12 = this.limit;
            int r22 = this.limitCount;
            String r42 = _UrlKt.FRAGMENT_ENCODE_SET;
            if (r22 > 4) goto L8;
            r42 = _UrlKt.FRAGMENT_ENCODE_SET + this.limitCount;
        L8:
            r12.setText(r42);
            return;
        }

        @Override // android.widget.TextView, android.view.View
        public boolean verifyDrawable(Drawable r2) {
            if (r2 != this.limit) goto L5;
            return true;
        L5:
            if (super.verifyDrawable(r2) == true) goto L11;
            return false;
        L11:
            return true;
        }
    }

    /* renamed from: org.telegram.messenger.FactCheckController$2, reason: invalid class name */
    public class AnonymousClass2 implements TextView.OnEditorActionListener {
        final /* synthetic */ FactCheckController this$0;
        final /* synthetic */ int val$MAX_LENGTH;
        final /* synthetic */ boolean val$creating;
        final /* synthetic */ View val$currentFocus;
        final /* synthetic */ AlertDialog[] val$dialog;
        final /* synthetic */ EditTextCaption val$editText;
        final /* synthetic */ MessageObject val$messageObject;

        public AnonymousClass2(FactCheckController r1, EditTextCaption r2, int r3, MessageObject r4, boolean r5, AlertDialog[] r6, View r7) {
            this.this$0 = r1;
            this.val$editText = r2;
            this.val$MAX_LENGTH = r3;
            this.val$messageObject = r4;
            this.val$creating = r5;
            this.val$dialog = r6;
            this.val$currentFocus = r7;
        }

        @Override // android.widget.TextView.OnEditorActionListener
        public boolean onEditorAction(TextView r4, int r5, KeyEvent r6) {
            if (r5 == 6) goto L5;
            return false;
        L5:
            if (this.val$editText.getText().toString().length() <= this.val$MAX_LENGTH) goto L8;
            AndroidUtilities.shakeView(this.val$editText);
            return true;
        L8:
            TLRPC.TL_textWithEntities r42 = new TLRPC.TL_textWithEntities();
            CharSequence[] r1 = {this.val$editText.getText()};
            r42.entities = MediaDataController.getInstance(this.this$0.currentAccount).getEntities(r1, true);
            CharSequence r52 = r1[0];
            if (r52 != null) goto L11;
            String r53 = _UrlKt.FRAGMENT_ENCODE_SET;
        L12:
            r42.text = r53;
            this.this$0.applyFactCheck(this.val$messageObject, r42, this.val$creating);
            AlertDialog r43 = this.val$dialog[0];
            if (r43 == null) goto L16;
            r43.dismiss();
        L16:
            if (this.val$dialog[0] != FactCheckController.m4001$$Nest$sfgetcurrentDialog()) goto L18;
            FactCheckController.m4002$$Nest$sfputcurrentDialog(null);
        L18:
            View r3 = this.val$currentFocus;
            if (r3 == null) goto L21;
            r3.requestFocus();
        L21:
            return true;
        L11:
            r53 = r52.toString();
            goto L12
        }
    }

    /* renamed from: org.telegram.messenger.FactCheckController$3, reason: invalid class name */
    public class AnonymousClass3 implements TextWatcher {
        boolean ignoreTextChange;
        final /* synthetic */ FactCheckController this$0;
        final /* synthetic */ int val$MAX_LENGTH;
        final /* synthetic */ EditTextCaption val$editText;
        final /* synthetic */ TLRPC.TL_factCheck val$factCheck;
        final /* synthetic */ TextView[] val$positiveButton;

        public AnonymousClass3(FactCheckController r1, int r2, EditTextCaption r3, TextView[] r4, TLRPC.TL_factCheck r5) {
            this.this$0 = r1;
            this.val$MAX_LENGTH = r2;
            this.val$editText = r3;
            this.val$positiveButton = r4;
            this.val$factCheck = r5;
        }

        @Override // android.text.TextWatcher
        public void afterTextChanged(Editable r6) {
            if (this.ignoreTextChange == true) goto L32;
            int r0 = r6.length();
            int r1 = this.val$MAX_LENGTH;
            boolean r2 = true;
            if (r0 <= r1) goto L11;
            this.ignoreTextChange = true;
            r6.delete(r1, r6.length());
            AndroidUtilities.shakeView(this.val$editText);
            this.val$editText.performHapticFeedback(3, 2);     // Catch: Exception -> L28
        L9:
            this.ignoreTextChange = false;
        L11:
            if (this.val$positiveButton[0] != null) goto L13;
            return;
        L13:
            if (r6.length() <= 0) goto L15;
        L18:
            TextView r62 = this.val$positiveButton[0];
            if (r2 == false) goto L21;
            int r02 = R.string.Done;
        L22:
            r62.setText(LocaleController.getString(r02));
            TextView r5 = this.val$positiveButton[0];
            if (r2 == false) goto L25;
            int r63 = Theme.key_dialogButton;
        L26:
            r5.setTextColor(Theme.getColor(r63));
            return;
        L25:
            r63 = Theme.key_text_RedBold;
            goto L26
        L21:
            r02 = R.string.Remove;
            goto L22
        L15:
            if (this.val$factCheck == null) goto L18;
            r2 = false;
            goto L18
        }

        @Override // android.text.TextWatcher
        public void beforeTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }

        @Override // android.text.TextWatcher
        public void onTextChanged(CharSequence r1, int r2, int r3, int r4) {
        }
    }

    public static class Key {
        public final long dialogId;
        public final long hash;
        public final int messageId;

        private Key(long r1, int r3, long r4) {
            this.dialogId = r1;
            this.messageId = r3;
            this.hash = r4;
        }

        public static Key of(MessageObject r8) {
            if (r8 != null) goto L5;
            return null;
        L5:
            TLRPC.Message r1 = r8.messageOwner;
            if (r1 != null) goto L9;
            return null;
        L9:
            if (r1.factcheck != null) goto L12;
            return null;
        L12:
            return new Key(r8.getDialogId(), r8.getId(), r8.messageOwner.factcheck.hash);
        }

        public int hashCode() {
            return Long.hashCode(this.hash);
        }
    }

    public static /* synthetic */ void $r8$lambda$0YOzaS10zcx32w1ALmgDs5omjs0(FactCheckController r0) {
        r0.loadMissing();
    }

    public static /* synthetic */ void $r8$lambda$17a66GbrcQ0ifpTLmeZl4mfZmyc(EditTextCaption r0, DialogInterface r1) {
        AndroidUtilities.hideKeyboard(r0);
    }

    /* renamed from: $r8$lambda$EvefuM2DWexX6YT77dm7f5qJG-Y, reason: not valid java name */
    public static /* synthetic */ void m3996$r8$lambda$EvefuM2DWexX6YT77dm7f5qJGY(FactCheckController r0, TLRPC.TL_getFactCheck r1, ArrayList r2, HashMap r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$loadMissing$2(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$JpH7yHT6pnclw1XNRCmFTCCRSQU(MessagesStorage r4) {
        r4.getDatabase().executeFast("DELETE FROM fact_checks WHERE expires > " + System.currentTimeMillis()).stepThis().dispose();     // Catch: Exception -> L5
        return;
    L5:
        e = move-exception;
        FileLog.e(e);
    }

    public static /* synthetic */ void $r8$lambda$XcfdiiqH2OjcRzQHr76vjXznhvk(FactCheckController r0, EditTextCaption r1, int r2, MessageObject r3, boolean r4, AlertDialog r5, int r6) {
        r0.lambda$openFactCheckEditor$8(r1, r2, r3, r4, r5, r6);
    }

    public static /* synthetic */ void $r8$lambda$YGSlWWCz3vO1AbKtahcZijYmhZ0(FactCheckController r0, TLObject r1, TLRPC.TL_textWithEntities r2, boolean r3, AlertDialog r4) {
        r0.lambda$applyFactCheck$15(r1, r2, r3, r4);
    }

    /* renamed from: $r8$lambda$Zj-8KJppbhb3_WpMVDIia5RYMME, reason: not valid java name */
    public static /* synthetic */ void m3997$r8$lambda$Zj8KJppbhb3_WpMVDIia5RYMME(FactCheckController r0, TLRPC.TL_textWithEntities r1, boolean r2, AlertDialog r3, TLObject r4, TLRPC.TL_error r5) {
        r0.lambda$applyFactCheck$16(r1, r2, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$ZxqDc3jp6zDTZquLci3o58khSpg(Utilities.Callback r0, ArrayList r1) {
        r0.run(r1);
    }

    /* renamed from: $r8$lambda$brix5NFGn4Ir-cJ43bQ7UwGcMyc, reason: not valid java name */
    public static /* synthetic */ void m3998$r8$lambda$brix5NFGn4IrcJ43bQ7UwGcMyc(FactCheckController r0, long r1, ArrayList r3, HashMap r4, ArrayList r5) {
        r0.lambda$loadMissing$3(r1, r3, r4, r5);
    }

    public static /* synthetic */ void $r8$lambda$cSn0YY6kSzQ16psVIG73jVTJqRo(EditTextCaption r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$covAdcOkR6_V4SG3w1tVgkD6jFY(View r0, DialogInterface r1) {
        currentDialog = null;
        r0.requestFocus();
    }

    public static /* synthetic */ void $r8$lambda$cvq9xLIa9zkgDqBmcyWoOiLrXKM(AlertDialog r0, int r1) {
        r0.dismiss();
    }

    public static /* synthetic */ void $r8$lambda$eRU3nxQjWPT8Oj30fqwMG3VVK3A(MessagesStorage r10, ArrayList r11, Utilities.Callback r12) {
        ArrayList r0 = new ArrayList();
        SQLiteCursor r1 = null;
        NativeByteBuffer r13 = null;
        r1 = null;
        SQLiteDatabase r102 = r10.getDatabase();     // Catch: Throwable -> L6 Exception -> L8
        ArrayList r2 = new ArrayList();     // Catch: Throwable -> L6 Exception -> L8
        int r3 = r11.size();     // Catch: Throwable -> L6 Exception -> L8
        int r4 = 0;
        int r5 = 0;
    L4:
        if (r5 >= r3) goto L10;
        Object r6 = r11.get(r5);     // Catch: Throwable -> L6 Exception -> L8
        r5 = r5 + 1;     // Catch: Throwable -> L6 Exception -> L8
        r2.add(Long.valueOf(((Key) r6).hash));     // Catch: Throwable -> L6 Exception -> L8
        r0.add(null);     // Catch: Throwable -> L6 Exception -> L8
        goto L4
    L10:
        SQLiteCursor r103 = r102.queryFinalized("SELECT data FROM fact_checks WHERE hash IN (" + TextUtils.join(", ", r2) + ")", new Object[0]);     // Catch: Throwable -> L6 Exception -> L8
    L57:
    L20:
        e = move-exception;
        r1 = r103;
        e = e;
    L42:
        FileLog.e(e);     // Catch: Throwable -> L6
        if (r1 == null) goto L45;
        r1.dispose();
    L45:
        AndroidUtilities.runOnUIThread(new FactCheckController$$ExternalSyntheticLambda9(r12, r0));
        return;
    L18:
        th = move-exception;
        r1 = r103;
        th = th;
    L47:
        if (r1 == null) goto L49;
        r1.dispose();
    L49:
        throw th;
    L12:
        if (r103.next() == false) goto L40;
        NativeByteBuffer r22 = r103.byteBufferValue(0);     // Catch: Throwable -> L36
        TLRPC.TL_factCheck r32 = TLRPC.TL_factCheck.TLdeserialize(r22, r22.readInt32(false), false);     // Catch: Throwable -> L29
        if (r32 != null) goto L22;
        r22.reuse();     // Catch: Throwable -> L18 Exception -> L20
        goto L57
    L22:
        int r14 = -1;
    L24:
        if (r4 >= r11.size()) goto L31;
        if (r32.hash != ((Key) r11.get(r4)).hash) goto L28;
        r14 = r4;
    L28:
        r4 = r4 + 1;     // Catch: Throwable -> L29
        goto L24
    L31:
        if (r14 >= 0) goto L33;
    L35:
        r22.reuse();     // Catch: Throwable -> L18 Exception -> L20
        goto L40
    L33:
        if (r14 >= r0.size()) goto L35;
        r0.set(r14, r32);     // Catch: Throwable -> L29
    L29:
        th = th;
        r13 = r22;
    L37:
        if (r13 == null) goto L39;
        r13.reuse();     // Catch: Throwable -> L18 Exception -> L20
    L39:
        throw th;     // Catch: Throwable -> L18 Exception -> L20
    L36:
        th = th;
    L40:
        r103.dispose();     // Catch: Throwable -> L18 Exception -> L20
    L8:
        e = e;
    L6:
        th = th;
        goto L47
    }

    /* renamed from: $r8$lambda$fIkaxRl8CN3xq7q4-SzIvYtzEA0, reason: not valid java name */
    public static /* synthetic */ void m3999$r8$lambda$fIkaxRl8CN3xq7q4SzIvYtzEA0(FactCheckController r0, Key r1, MessageObject r2, TLRPC.TL_factCheck r3) {
        r0.lambda$getFactCheck$0(r1, r2, r3);
    }

    /* renamed from: $r8$lambda$hwZvWtn8X40ulbUsYqEavkir-k8, reason: not valid java name */
    public static /* synthetic */ void m4000$r8$lambda$hwZvWtn8X40ulbUsYqEavkirk8(MessagesStorage r6, TLRPC.TL_factCheck r7) {
        SQLitePreparedStatement r0 = null;
        SQLitePreparedStatement r62 = r6.getDatabase().executeFast("REPLACE INTO fact_checks VALUES(?, ?, ?)");     // Catch: Throwable -> L16 Exception -> L18
        r62.requery();     // Catch: Throwable -> L12 Exception -> L14
        r62.bindLong(1, r7.hash);     // Catch: Throwable -> L12 Exception -> L14
        NativeByteBuffer r1 = new NativeByteBuffer(r7.getObjectSize());     // Catch: Throwable -> L12 Exception -> L14
    L36:
        r7.serializeToStream(r1);     // Catch: Throwable -> L8 Exception -> L10
        r62.bindByteBuffer(2, r1);     // Catch: Throwable -> L8 Exception -> L10
        r62.bindLong(3, System.currentTimeMillis() + 889032704);     // Catch: Throwable -> L8 Exception -> L10
        r62.step();     // Catch: Throwable -> L8 Exception -> L10
        r62.dispose();     // Catch: Throwable -> L8 Exception -> L10
        r1.reuse();
        return;
    L10:
        e = e;
    L11:
        r0 = r62;
    L32:
        FileLog.e(e);     // Catch: Throwable -> L26
        if (r0 == null) goto L23;
        r0.dispose();
    L23:
        if (r1 == null) goto L40;
        r1.reuse();
        return;
    L40:
        return;
    L26:
        th = th;
    L27:
        if (r0 == null) goto L29;
        r0.dispose();
    L29:
        if (r1 == null) goto L31;
        r1.reuse();
    L31:
        throw th;
    L8:
        th = th;
    L9:
        r0 = r62;
    L14:
        e = e;
        r1 = null;
    L12:
        th = th;
        r1 = null;
        goto L9
    L18:
        e = e;
        r1 = null;
    L16:
        th = th;
        r1 = null;
        goto L27
    }

    public static /* synthetic */ void $r8$lambda$rCZDn9DTeD00226gcwmQQ1cnVzY(EditTextCaption r0, DialogInterface r1) {
        r0.requestFocus();
        AndroidUtilities.showKeyboard(r0);
    }

    public static /* synthetic */ void $r8$lambda$sZwNbzSKTw4HXRYJF_5OjVglHcQ(FactCheckController r0, TLObject r1, TLRPC.TL_getFactCheck r2, ArrayList r3, HashMap r4) {
        r0.lambda$loadMissing$1(r1, r2, r3, r4);
    }

    public static /* synthetic */ void $r8$lambda$xdLJfpQp5zaZtfDClgjrFyPrON0(FactCheckController r0, TLObject r1) {
        r0.lambda$applyFactCheck$14(r1);
    }

    /* renamed from: -$$Nest$sfgetcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ AlertDialog m4001$$Nest$sfgetcurrentDialog() {
        return currentDialog;
    }

    /* renamed from: -$$Nest$sfputcurrentDialog, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m4002$$Nest$sfputcurrentDialog(AlertDialog r0) {
        currentDialog = r0;
    }

    static {
        Instance = new FactCheckController[16];
        lockObjects = new Object[16];
        int r1 = 0;
    L3:
        if (r1 >= 16) goto L5;
        lockObjects[r1] = new Object();
        r1 = r1 + 1;
        goto L3
    }

    private FactCheckController(int r2) {
        this.localCache = new LongSparseArray();
        this.toload = new LongSparseArray();
        this.loading = new ArrayList();
        this.loadMissingRunnable = new FactCheckController$$ExternalSyntheticLambda17(this);
        this.currentAccount = r2;
    }

    private void clearExpiredInDatabase() {
        if (this.clearedExpiredInDatabase == false) goto L5;
        return;
    L5:
        this.clearedExpiredInDatabase = true;
        MessagesStorage r2 = MessagesStorage.getInstance(this.currentAccount);
        r2.getStorageQueue().postRunnable(new FactCheckController$$ExternalSyntheticLambda10(r2));
    }

    private void getFromDatabase(ArrayList<Key> r3, Utilities.Callback<ArrayList<TLRPC.TL_factCheck>> r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        if (r3 != null) goto L6;
    L10:
        r4.run(new ArrayList());
        return;
    L6:
        if (r3.isEmpty() == true) goto L10;
        MessagesStorage r2 = MessagesStorage.getInstance(this.currentAccount);
        r2.getStorageQueue().postRunnable(new FactCheckController$$ExternalSyntheticLambda14(r2, r3, r4));
    }

    public static FactCheckController getInstance(int r3) {
        FactCheckController r0 = Instance[r3];
        if (r0 != null) goto L15;
        Object r1 = lockObjects[r3];
        monitor-enter(r1);
        FactCheckController r02 = Instance[r3];     // Catch: Throwable -> L9
        if (r02 != null) goto L11;
        FactCheckController[] r03 = Instance;     // Catch: Throwable -> L9
        FactCheckController r2 = new FactCheckController(r3);     // Catch: Throwable -> L9
        r03[r3] = r2;     // Catch: Throwable -> L9
        r02 = r2;
    L11:
        monitor-exit(r1);     // Catch: Throwable -> L9
        return r02;
    L9:
        th = move-exception;
        throw th;
    L15:
        return r0;
    }

    private /* synthetic */ void lambda$applyFactCheck$14(TLObject r2) {
        MessagesController.getInstance(this.currentAccount).processUpdates((TLRPC.Updates) r2, false);
    }

    private /* synthetic */ void lambda$applyFactCheck$15(TLObject r3, TLRPC.TL_textWithEntities r4, boolean r5, AlertDialog r6) {
        if ((r3 instanceof TLRPC.Updates) == false) goto L22;
        Utilities.stageQueue.postRunnable(new FactCheckController$$ExternalSyntheticLambda13(this, r3));
        BaseFragment r2 = LaunchActivity.getSafeLastFragment();
        if (r2 == null) goto L22;
        if (r4 != null) goto L8;
    L11:
        boolean r32 = true;
    L12:
        if (r32 == true) goto L14;
        if (r5 == true) goto L22;
    L14:
        BulletinFactory r22 = BulletinFactory.of(r2);
        if (r32 == false) goto L17;
        int r42 = R.raw.ic_delete;
    L18:
        if (r32 == false) goto L20;
        int r33 = R.string.FactCheckDeleted;
    L21:
        r22.createSimpleBulletin(r42, LocaleController.getString(r33)).show();
        goto L22
    L20:
        r33 = R.string.FactCheckEdited;
        goto L21
    L17:
        r42 = R.raw.contact_check;
        goto L18
    L8:
        if (TextUtils.isEmpty(r4.text) == true) goto L11;
        r32 = false;
    L22:
        r6.dismiss();
    }

    private /* synthetic */ void lambda$applyFactCheck$16(TLRPC.TL_textWithEntities r2, boolean r3, AlertDialog r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new FactCheckController$$ExternalSyntheticLambda12(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$getFactCheck$0(Key r3, MessageObject r4, TLRPC.TL_factCheck r5) {
        this.localCache.put(r3.hash, r5);
        r4.messageOwner.factcheck = r5;
    }

    private /* synthetic */ void lambda$loadMissing$1(TLObject r8, TLRPC.TL_getFactCheck r9, ArrayList r10, HashMap r11) {
        ArrayList r0 = new ArrayList();
        if ((r8 instanceof Vector) == false) goto L11;
        ArrayList<T> r82 = ((Vector) r8).objects;
        int r1 = 0;
    L6:
        if (r1 >= r82.size()) goto L11;
        if ((r82.get(r1) instanceof TLRPC.TL_factCheck) == false) goto L10;
        r0.add((TLRPC.TL_factCheck) r82.get(r1));
    L10:
        r1 = r1 + 1;
    L11:
        HashMap r83 = new HashMap();
        int r12 = 0;
    L13:
        if (r12 >= Math.min(r9.msg_id.size(), r0.size())) goto L15;
        Integer r3 = r9.msg_id.get(r12);
        r3.intValue();
        r83.put(r3, (TLRPC.TL_factCheck) r0.get(r12));
        r12 = r12 + 1;
        goto L13
    L15:
        int r02 = 0;
        int r13 = 0;
    L17:
        if (r02 >= r9.msg_id.size()) goto L25;
        Key r32 = (Key) r10.get(r02);
        Integer r4 = r9.msg_id.get(r02);
        r4.intValue();
        TLRPC.TL_factCheck r42 = (TLRPC.TL_factCheck) r83.get(r4);
        Utilities.Callback r5 = (Utilities.Callback) r11.get(r32);
        if (r42 == null) goto L24;
        if (r42.need_check == true) goto L24;
        if (r5 == null) goto L24;
        r5.run(r42);
        r13 = r13 + 1;
        this.loading.remove(r32);
    L24:
        r02 = r02 + 1;
        goto L17
    L25:
        if (r13 <= 0) goto L37;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.factCheckLoaded, new Object[0]);
        return;
    }

    private /* synthetic */ void lambda$loadMissing$2(TLRPC.TL_getFactCheck r2, ArrayList r3, HashMap r4, TLObject r5, TLRPC.TL_error r6) {
        AndroidUtilities.runOnUIThread(new FactCheckController$$ExternalSyntheticLambda1(this, r5, r2, r3, r4));
    }

    private /* synthetic */ void lambda$loadMissing$3(long r7, ArrayList r9, HashMap r10, ArrayList r11) {
        TLRPC.TL_getFactCheck r0 = new TLRPC.TL_getFactCheck();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r7);
        ArrayList r72 = new ArrayList();
        int r1 = 0;
        int r2 = 0;
    L4:
        if (r1 >= r11.size()) goto L12;
        Key r3 = (Key) r9.get(r1);
        TLRPC.TL_factCheck r4 = (TLRPC.TL_factCheck) r11.get(r1);
        if (r4 != null) goto L8;
        r72.add(r3);
        r0.msg_id.add(Integer.valueOf(r3.messageId));
    L11:
        r1 = r1 + 1;
        goto L4
    L8:
        this.loading.remove(r3);
        Utilities.Callback r32 = (Utilities.Callback) r10.get(r3);
        if (r32 == null) goto L11;
        r32.run(r4);
        r2 = r2 + 1;
        goto L11
    L12:
        if (r2 <= 0) goto L15;
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.factCheckLoaded, new Object[0]);
    L15:
        if (r0.msg_id.isEmpty() == true) goto L22;
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r0, new FactCheckController$$ExternalSyntheticLambda16(this, r0, r72, r10));
        return;
    }

    private /* synthetic */ void lambda$openFactCheckEditor$8(EditTextCaption r3, int r4, MessageObject r5, boolean r6, AlertDialog r7, int r8) {
        if (r3.getText().toString().length() <= r4) goto L6;
        AndroidUtilities.shakeView(r3);
        return;
    L6:
        TLRPC.TL_textWithEntities r42 = new TLRPC.TL_textWithEntities();
        CharSequence[] r0 = {r3.getText()};
        r42.entities = MediaDataController.getInstance(this.currentAccount).getEntities(r0, true);
        CharSequence r32 = r0[0];
        if (r32 != null) goto L9;
        String r33 = _UrlKt.FRAGMENT_ENCODE_SET;
    L10:
        r42.text = r33;
        applyFactCheck(r5, r42, r6);
        r7.dismiss();
        return;
    L9:
        r33 = r32.toString();
        goto L10
    }

    private void loadMissing() {
    L2:
        int r0 = this.toload.size();
        LongSparseArray<HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>>> r1 = this.toload;
        if (r0 <= 0) goto L5;
        long r4 = r1.keyAt(0);
        HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>> r7 = this.toload.valueAt(0);
        this.toload.removeAt(0);
        ArrayList<Key> r6 = new ArrayList(r7.keySet());
        this.loading.addAll(r6);
        getFromDatabase(r6, new FactCheckController$$ExternalSyntheticLambda11(this, r4, r6, r7));
        goto L2
    L5:
        r1.clear();
    }

    private void saveToDatabase(TLRPC.TL_factCheck r4) {
        if (r4 != null) goto L4;
        return;
    L4:
        MessagesStorage r0 = MessagesStorage.getInstance(this.currentAccount);
        r0.getStorageQueue().postRunnable(new FactCheckController$$ExternalSyntheticLambda8(r0, r4));
        clearExpiredInDatabase();
    }

    private void scheduleLoadMissing() {
        AndroidUtilities.cancelRunOnUIThread(this.loadMissingRunnable);
        AndroidUtilities.runOnUIThread(this.loadMissingRunnable, 80);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public void applyFactCheck(MessageObject r5, TLRPC.TL_textWithEntities r6, boolean r7) {
        if (r6 != null) goto L4;
    L7:
        if (r7 == false) goto L9;
        return;
    L9:
        TLRPC.TL_deleteFactCheck r0 = new TLRPC.TL_deleteFactCheck();
        r0.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5.getDialogId());
        r0.msg_id = r5.getId();
        TLRPC.TL_deleteFactCheck r02 = r0;
    L10:
        Context r52 = LaunchActivity.instance;
        if (r52 != null) goto L14;
        r52 = ApplicationLoader.applicationContext;
    L14:
        AlertDialog r1 = new AlertDialog(r52, 3);
        r1.showDelayed(320);
        ConnectionsManager.getInstance(this.currentAccount).sendRequest(r02, new FactCheckController$$ExternalSyntheticLambda0(this, r6, r7, r1));
        return;
    L4:
        if (TextUtils.isEmpty(r6.text) == true) goto L7;
        TLRPC.TL_editFactCheck r03 = new TLRPC.TL_editFactCheck();
        r03.peer = MessagesController.getInstance(this.currentAccount).getInputPeer(r5.getDialogId());
        r03.msg_id = r5.getId();
        r03.text = r6;
        r02 = r03;
        goto L10
    }

    public TLRPC.TL_factCheck getFactCheck(MessageObject r6) {
        if (r6 == null) goto L39;
        TLRPC.Message r1 = r6.messageOwner;
        if (r1 == null) goto L39;
        TLRPC.TL_factCheck r12 = r1.factcheck;
        if (r12 != null) goto L11;
        return null;
    L11:
        if (r12.need_check == false) goto L13;
        Key r13 = Key.of(r6);
        if (r13 != null) goto L21;
        return null;
    L21:
        if (r13.messageId >= 0) goto L23;
        return null;
    L23:
        TLRPC.TL_factCheck r0 = this.localCache.get(r13.hash);
        if (r0 == null) goto L28;
        r6.messageOwner.factcheck = r0;
        return r0;
    L28:
        if (this.loading.contains(r13) == true) goto L30;
        HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>> r02 = this.toload.get(r13.dialogId);
        if (r02 != null) goto L35;
        LongSparseArray<HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>>> r03 = this.toload;
        long r2 = r13.dialogId;
        HashMap<Key, Utilities.Callback<TLRPC.TL_factCheck>> r4 = new HashMap();
        r03.put(r2, r4);
        r02 = r4;
    L35:
        if (r02.containsKey(r13) == true) goto L38;
        r02.put(r13, new FactCheckController$$ExternalSyntheticLambda15(this, r13, r6));
        scheduleLoadMissing();
    L38:
        return r6.messageOwner.factcheck;
    L30:
        return r6.messageOwner.factcheck;
    L13:
        if (this.localCache.get(r12.hash) != null) goto L16;
        LongSparseArray<TLRPC.TL_factCheck> r04 = this.localCache;
        TLRPC.TL_factCheck r14 = r6.messageOwner.factcheck;
        r04.put(r14.hash, r14);
        saveToDatabase(r6.messageOwner.factcheck);
    L16:
        return r6.messageOwner.factcheck;
    L39:
        return null;
    }

    public void openFactCheckEditor(Context r24, Theme.ResourcesProvider r25, MessageObject r26, boolean r27) {
        BaseFragment r0 = LaunchActivity.getLastFragment();
        Activity r4 = AndroidUtilities.findActivity(r24);
        if (r4 == null) goto L5;
        View r7 = r4.getCurrentFocus();
    L7:
        if (r0 != null) goto L9;
    L14:
        boolean r11 = false;
    L15:
        AlertDialog[] r12 = new AlertDialog[1];
        if (r11 == false) goto L19;
        AlertDialog.Builder r02 = new AlertDialogDecor.Builder(r24, r25);
    L18:
        AlertDialog.Builder r13 = r02;
        TextView[] r14 = new TextView[1];
        if (r26 == null) goto L28;
        TLRPC.Message r03 = r26.messageOwner;
        if (r03 == null) goto L28;
        if (r03.factcheck == null) goto L28;
        boolean r15 = false;
    L29:
        r13.setTitle(LocaleController.getString(R.string.FactCheckDialog));
        int r42 = MessagesController.getInstance(this.currentAccount).factcheckLengthLimit;
        AnonymousClass1 r04 = new AnonymousClass1(this, r24, r25, r42, r25);
        r04.lineYFix = true;
        boolean r5 = r15;
        r04.setOnEditorActionListener(new AnonymousClass2(this, r04, r42, r26, r5, r12, r7));
        MediaDataController.getInstance(this.currentAccount).fetchNewEmojiKeywords(AndroidUtilities.getCurrentKeyboardLanguage(), true);
        r04.setTextSize(1, 18.0f);
        r04.setTextColor(Theme.getColor(Theme.key_dialogTextBlack, r25));
        r04.setHintColor(Theme.getColor(Theme.key_groupcreate_hintText, r25));
        r04.setHintText(LocaleController.getString(R.string.FactCheckPlaceholder));
        r04.setFocusable(true);
        r04.setInputType(147457);
        r04.setLineColors(Theme.getColor(Theme.key_windowBackgroundWhiteInputField, r25), Theme.getColor(Theme.key_windowBackgroundWhiteInputFieldActivated, r25), Theme.getColor(Theme.key_text_RedRegular, r25));
        r04.setImeOptions(6);
        r04.setBackgroundDrawable(null);
        r04.setPadding(0, AndroidUtilities.dp(6.0f), 0, AndroidUtilities.dp(6.0f));
        TLRPC.TL_factCheck r52 = r26.getFactCheck();
        if (r52 == null) goto L34;
        TLRPC.TL_textWithEntities r05 = r52.text;
        if (r05 == null) goto L34;
        SpannableStringBuilder r17 = SpannableStringBuilder.valueOf(r05.text);
        MessageObject.addEntitiesToText(r17, r52.text.entities, false, true, false, false);
        r04.setText(r17);
    L34:
        r04.addTextChangedListener(new AnonymousClass3(this, r42, r04, r14, r52));
        LinearLayout r06 = new LinearLayout(r24);
        r06.setOrientation(1);
        r06.addView(r04, LayoutHelper.createLinear(-1, -2, 24.0f, 0.0f, 24.0f, 10.0f));
        r13.makeCustomMaxHeight();
        r13.setView(r06);
        r13.setWidth(AndroidUtilities.dp(292.0f));
        r13.setPositiveButton(LocaleController.getString(R.string.Done), new FactCheckController$$ExternalSyntheticLambda2(this, r04, r42, r26, r5));
        r13.setNegativeButton(LocaleController.getString("Cancel", R.string.Cancel), new FactCheckController$$ExternalSyntheticLambda3());
        if (r11 == false) goto L37;
        AlertDialog r07 = r13.create();
        currentDialog = r07;
        r12[0] = r07;
        r07.setOnDismissListener(new FactCheckController$$ExternalSyntheticLambda4(r7));
        currentDialog.setOnShowListener(new FactCheckController$$ExternalSyntheticLambda5(r04));
        currentDialog.showDelayed(250);
    L38:
        r12[0].setDismissDialogByButtons(false);
        View r08 = r12[0].getButton(-1);
        if ((r08 instanceof TextView) == false) goto L41;
        r14[0] = (TextView) r08;
    L41:
        r04.setSelection(r04.getText().length());
        return;
    L37:
        AlertDialog r09 = r13.create();
        r12[0] = r09;
        r09.setOnDismissListener(new FactCheckController$$ExternalSyntheticLambda6(r04));
        r12[0].setOnShowListener(new FactCheckController$$ExternalSyntheticLambda7(r04));
        r12[0].show();
    L28:
        r15 = true;
        goto L29
    L19:
        r02 = new AlertDialog.Builder(r24, r25);
        goto L18
    L9:
        if ((r0.getFragmentView() instanceof SizeNotifierFrameLayout) == false) goto L14;
        if (((SizeNotifierFrameLayout) r0.getFragmentView()).measureKeyboardHeight() <= AndroidUtilities.dp(20.0f)) goto L14;
        if (r27 == true) goto L14;
        r11 = true;
        goto L15
    L5:
        r7 = null;
        goto L7
    }
}
