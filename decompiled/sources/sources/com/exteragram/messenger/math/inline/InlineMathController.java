package com.exteragram.messenger.math.inline;

import android.graphics.Canvas;
import android.graphics.Region;
import android.text.Editable;
import android.text.Layout;
import android.text.Selection;
import android.text.Spannable;
import android.text.Spanned;
import android.text.style.MetricAffectingSpan;
import android.view.KeyEvent;
import android.view.inputmethod.BaseInputConnection;
import android.view.inputmethod.InputConnection;
import android.view.inputmethod.InputConnectionWrapper;
import android.widget.TextView;
import com.exteragram.messenger.ExteraConfig;
import com.exteragram.messenger.math.MathExpression;
import com.exteragram.messenger.math.MathOptions;
import com.exteragram.messenger.math.MathSuggestion;
import com.exteragram.messenger.plugins.PluginsConstants;
import java.text.DecimalFormatSymbols;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.SourceDebugExtension;
import kotlin.text.StringsKt;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.QuoteSpan;
import org.telegram.ui.Components.TextStyleSpan;

@Metadata(d1 = {"\u0000\u008a\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\r\n\u0002\b\u0006\u0018\u0000 M2\u00020\u0001:\u0002LMB\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\"\u001a\u00020\u0013J\u0006\u0010#\u001a\u00020\u0011J\u0006\u0010$\u001a\u00020%J\u0006\u0010&\u001a\u00020%J\u0016\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u0013J.\u0010,\u001a\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010+\u001a\u00020\u00132\u0006\u0010.\u001a\u00020%2\u0006\u0010/\u001a\u00020%J\u0006\u00100\u001a\u00020(J\u0006\u00101\u001a\u00020(J\u0006\u00102\u001a\u00020(J\u000e\u00103\u001a\u00020(2\u0006\u00104\u001a\u00020\u0011J\u0006\u00105\u001a\u00020(J\u0006\u00106\u001a\u00020\u0011J\b\u00107\u001a\u00020(H\u0002J\b\u00108\u001a\u00020\u0011H\u0002J\b\u00109\u001a\u00020(H\u0002J\b\u0010\u0018\u001a\u00020\u0019H\u0002J\b\u0010:\u001a\u00020(H\u0002J\u000e\u0010;\u001a\u00020\u00112\u0006\u0010<\u001a\u00020=J\u000e\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?J\b\u0010A\u001a\u00020\u0011H\u0002J\b\u0010B\u001a\u00020\u0011H\u0002J\u0016\u0010C\u001a\u00020\u00112\f\u0010D\u001a\b\u0012\u0004\u0012\u00020(0EH\u0002J \u0010F\u001a\u00020\u00112\u0006\u0010G\u001a\u00020H2\u0006\u0010I\u001a\u00020\u00132\u0006\u0010J\u001a\u00020\u0013H\u0002J\b\u0010K\u001a\u00020(H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0014\u001a\u0004\u0018\u00010\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0018\u001a\u00020\u0019X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001a\u001a\u0004\u0018\u00010\u001bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006N"}, d2 = {"Lcom/exteragram/messenger/math/inline/InlineMathController;", _UrlKt.FRAGMENT_ENCODE_SET, PluginsConstants.Settings.VIEW, "Landroid/widget/TextView;", "delegate", "Lcom/exteragram/messenger/math/inline/InlineMathController$Delegate;", "<init>", "(Landroid/widget/TextView;Lcom/exteragram/messenger/math/inline/InlineMathController$Delegate;)V", "ghost", "Lcom/exteragram/messenger/math/inline/GhostTextLayout;", "appear", "Lorg/telegram/ui/Components/AnimatedFloat;", "reveal", "Lcom/exteragram/messenger/math/inline/MathRevealAnimation;", "suggestion", "Lcom/exteragram/messenger/math/MathSuggestion;", "dirty", _UrlKt.FRAGMENT_ENCODE_SET, "layoutWidth", _UrlKt.FRAGMENT_ENCODE_SET, "announcedValue", _UrlKt.FRAGMENT_ENCODE_SET, "announce", "Ljava/lang/Runnable;", "options", "Lcom/exteragram/messenger/math/MathOptions;", "optionsLocale", "Ljava/util/Locale;", "undoStart", "undoEnd", "suppressedAt", "insertingSelf", "caretMovedByTouch", "swallowKeyCode", "getExtraBottom", "hasCursorShift", "getCursorShiftX", _UrlKt.FRAGMENT_ENCODE_SET, "getCursorShiftY", "clipReplacedParagraph", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "top", "draw", "left", "clipTop", "clipBottom", "invalidateState", "onTextChanged", "onTouchDown", "onFocusChanged", "focused", "cancel", "updateOnMeasure", "schedule", "canTrigger", "updateAnnouncement", PluginsConstants.UPDATE, "onKeyEvent", "event", "Landroid/view/KeyEvent;", "wrap", "Landroid/view/inputmethod/InputConnection;", "connection", "commit", "undo", "edit", "block", "Lkotlin/Function0;", "hasUnsupportedSpans", "text", _UrlKt.FRAGMENT_ENCODE_SET, "start", "end", "clearUndo", "Delegate", "Companion", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SourceDebugExtension({"SMAP\nInlineMathController.kt\nKotlin\n*S Kotlin\n*F\n+ 1 InlineMathController.kt\ncom/exteragram/messenger/math/inline/InlineMathController\n+ 2 _Arrays.kt\nkotlin/collections/ArraysKt___ArraysKt\n*L\n1#1,430:1\n13213#2,2:431\n*S KotlinDebug\n*F\n+ 1 InlineMathController.kt\ncom/exteragram/messenger/math/inline/InlineMathController\n*L\n422#1:431,2\n*E\n"})
/* loaded from: classes4.dex */
public final class InlineMathController {
    public static final Companion Companion = null;
    private final Runnable announce;
    private String announcedValue;
    private final AnimatedFloat appear;
    private boolean caretMovedByTouch;
    private final Delegate delegate;
    private boolean dirty;
    private final GhostTextLayout ghost;
    private boolean insertingSelf;
    private int layoutWidth;
    private MathOptions options;
    private Locale optionsLocale;
    private final MathRevealAnimation reveal;
    private MathSuggestion suggestion;
    private int suppressedAt;
    private int swallowKeyCode;
    private int undoEnd;
    private int undoStart;
    private final TextView view;

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\t\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082T¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lcom/exteragram/messenger/math/inline/InlineMathController$Companion;", _UrlKt.FRAGMENT_ENCODE_SET, "<init>", "()V", "GHOST_ALPHA", _UrlKt.FRAGMENT_ENCODE_SET, "ANNOUNCE_DELAY", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        private Companion() {
        }

        public /* synthetic */ Companion(DefaultConstructorMarker r1) {
            this();
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\bf\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H&J\b\u0010\u0006\u001a\u00020\u0007H&¨\u0006\bÀ\u0006\u0003"}, d2 = {"Lcom/exteragram/messenger/math/inline/InlineMathController$Delegate;", _UrlKt.FRAGMENT_ENCODE_SET, "runProgrammatic", _UrlKt.FRAGMENT_ENCODE_SET, "action", "Ljava/lang/Runnable;", "accentColor", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public interface Delegate {
        int accentColor();

        void runProgrammatic(Runnable r1);
    }

    @Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u001a\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016J\u0018\u0010\u000b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u0007H\u0016¨\u0006\f"}, d2 = {"com/exteragram/messenger/math/inline/InlineMathController$wrap$1", "Landroid/view/inputmethod/InputConnectionWrapper;", "commitText", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "newCursorPosition", _UrlKt.FRAGMENT_ENCODE_SET, "deleteSurroundingText", "beforeLength", "afterLength", "deleteSurroundingTextInCodePoints", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
    /* renamed from: com.exteragram.messenger.math.inline.InlineMathController$wrap$1, reason: invalid class name */
    public static final class AnonymousClass1 extends InputConnectionWrapper {
        final /* synthetic */ InlineMathController this$0;

        public AnonymousClass1(InputConnection r1, InlineMathController r2) {
            this.this$0 = r2;
            super(r1, true);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean commitText(CharSequence r4, int r5) {
            if (r4 == null) goto L13;
            if (r4.length() != 1) goto L13;
            if (r4.charAt(0) != ' ') goto L13;
            if (InlineMathController.access$getSuggestion$p(this.this$0) == null) goto L13;
            finishComposingText();
            if (InlineMathController.access$commit(this.this$0) == false) goto L13;
            return true;
        L13:
            return super.commitText(r4, r5);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingText(int r3, int r4) {
            if (r3 != 1) goto L9;
            if (r4 != 0) goto L9;
            if (InlineMathController.access$undo(this.this$0) == false) goto L9;
            return true;
        L9:
            return super.deleteSurroundingText(r3, r4);
        }

        @Override // android.view.inputmethod.InputConnectionWrapper, android.view.inputmethod.InputConnection
        public boolean deleteSurroundingTextInCodePoints(int r3, int r4) {
            if (r3 != 1) goto L9;
            if (r4 != 0) goto L9;
            if (InlineMathController.access$undo(this.this$0) == false) goto L9;
            return true;
        L9:
            return super.deleteSurroundingTextInCodePoints(r3, r4);
        }
    }

    /* renamed from: $r8$lambda$NZnfwmgSa-qL3T-CjeWYkZ9BajE, reason: not valid java name */
    public static void m1502$r8$lambda$NZnfwmgSaqL3TCjeWYkZ9BajE(InlineMathController r1) {
        r1.dirty = true;
    }

    /* renamed from: $r8$lambda$bC5G05WbzVXNEF-_Hy-5TiXUXrI, reason: not valid java name */
    public static void m1503$r8$lambda$bC5G05WbzVXNEF_Hy5TiXUXrI(InlineMathController r1) {
        MathSuggestion r0 = r1.suggestion;
        if (r0 == null) goto L5;
        String r02 = r0.getValue();
    L6:
        if (r02 == null) goto L9;
        r1.announcedValue = r02;
        AndroidUtilities.makeAccessibilityAnnouncement(LocaleController.formatString(R.string.InlineMathResultAnnouncement, new Object[]{r02}));
        return;
    L9:
        return;
    L5:
        r02 = null;
        goto L6
    }

    public static void $r8$lambda$guhZXD64x1PCf_PzIoYeK7FQ_rY(InlineMathController r1, Function0 r2, Ref.BooleanRef r3) {
        r1.insertingSelf = true;
        r2.invoke();     // Catch: Throwable -> L6 Exception -> L8
        r1.insertingSelf = false;
        return;
    L8:
        e = move-exception;
        FileLog.e(e);     // Catch: Throwable -> L6
        r3.element = false;     // Catch: Throwable -> L6
        r1.insertingSelf = false;
        return;
    L6:
        th = move-exception;
        r1.insertingSelf = false;
        throw th;
    }

    /* renamed from: $r8$lambda$laHnhw0LcXrrnF6-Rszt1kM4BwM, reason: not valid java name */
    public static Unit m1504$r8$lambda$laHnhw0LcXrrnF6Rszt1kM4BwM(Editable r1, InlineMathController r2) {
        r1.delete(r2.undoStart, r2.undoEnd);
        return Unit.INSTANCE;
    }

    public static Unit $r8$lambda$qWOovbGAMnapn2V8igAcpMi9X1g(Editable r1, MathSuggestion r2) {
        r1.insert(r2.getInsertAt(), r2.getInsertText());
        return Unit.INSTANCE;
    }

    static {
        Companion = new Companion(null);
    }

    public InlineMathController(TextView r8, Delegate r9) {
        this.view = r8;
        this.delegate = r9;
        this.ghost = new GhostTextLayout();
        this.appear = new AnimatedFloat(r8, 0, 120, CubicBezierInterpolator.EASE_OUT_QUINT);
        this.reveal = new MathRevealAnimation(r8, new InlineMathController$$ExternalSyntheticLambda0(this));
        this.dirty = true;
        this.layoutWidth = -1;
        this.announce = new InlineMathController$$ExternalSyntheticLambda1(this);
        this.options = new MathOptions('.');
        this.undoStart = -1;
        this.undoEnd = -1;
        this.suppressedAt = -1;
    }

    public static final /* synthetic */ boolean access$commit(InlineMathController r0) {
        return r0.commit();
    }

    public static final /* synthetic */ MathSuggestion access$getSuggestion$p(InlineMathController r0) {
        return r0.suggestion;
    }

    public static final /* synthetic */ boolean access$undo(InlineMathController r0) {
        return r0.undo();
    }

    private final boolean canTrigger() {
        if (ExteraConfig.getInlineMathResult() == true) goto L5;
        return false;
    L5:
        CharSequence r0 = this.view.getText();
        if (r0 != null) goto L8;
        return false;
    L8:
        int r4 = this.view.getSelectionStart();
        if (1 <= r4) goto L11;
    L15:
        return false;
    L11:
        if (r4 > r0.length()) goto L15;
        if (r0.charAt(r4 - 1) != '=') goto L15;
        return true;
    }

    private final void clearUndo() {
        this.undoStart = -1;
        this.undoEnd = -1;
    }

    private final boolean commit() {
        MathSuggestion r0 = this.suggestion;
        if (r0 != null) goto L5;
        return false;
    L5:
        CharSequence r2 = this.view.getText();
        if ((r2 instanceof Editable) == false) goto L8;
        Editable r6 = (Editable) r2;
    L9:
        if (r6 != null) goto L12;
        return false;
    L12:
        if (r0.getInsertAt() == this.view.getSelectionStart()) goto L14;
        return false;
    L14:
        if (this.view.getSelectionStart() == this.view.getSelectionEnd()) goto L17;
        return false;
    L17:
        if (r0.getInsertAt() <= r6.length()) goto L19;
        return false;
    L19:
        int r22 = r0.getInsertText().length() - r0.getValue().length();
        int r8 = r0.getValue().length();
        float[] r9 = new float[r8];
        float[] r10 = new float[r8];
        this.ghost.readInsertedPositions(r22, r8, r9, r10);
        BaseInputConnection.removeComposingSpans(r6);
        if (edit(new InlineMathController$$ExternalSyntheticLambda3(r6, r0)) == true) goto L22;
        return false;
    L22:
        Selection.setSelection(r6, r0.getInsertAt() + r0.getInsertText().length());
        this.suggestion = null;
        this.ghost.clear();
        this.dirty = true;
        this.reveal.begin(r6, r0.getInsertAt() + r22, r8, r9, r10);
        this.undoStart = r0.getInsertAt();
        this.undoEnd = r0.getInsertAt() + r0.getInsertText().length();
        this.appear.set(0.0f, true);
        this.view.requestLayout();
        this.view.invalidate();
        return true;
    L8:
        r6 = null;
        goto L9
    }

    private final boolean edit(Function0<Unit> r3) {
        Ref.BooleanRef r0 = new Ref.BooleanRef();
        r0.element = true;
        InlineMathController$$ExternalSyntheticLambda4 r1 = new InlineMathController$$ExternalSyntheticLambda4(this, r3, r0);
        Delegate r2 = this.delegate;
        if (r2 == null) goto L5;
        r2.runProgrammatic(r1);
    L7:
        return r0.element;
    L5:
        r1.run();
        goto L7
    }

    private final boolean hasUnsupportedSpans(CharSequence r3, int r4, int r5) {
        if ((r3 instanceof Spanned) == true) goto L5;
        return false;
    L5:
        Spanned r32 = (Spanned) r3;
        if (r32.getSpans(r4, r5, AnimatedEmojiSpan.class).length != 0) goto L8;
        boolean r2 = true;
    L9:
        if (r2 == true) goto L12;
        return true;
    L12:
        if (r32.getSpans(r4, r5, QuoteSpan.class).length != 0) goto L14;
        boolean r22 = true;
    L15:
        if (r22 == true) goto L17;
        return true;
    L17:
        Object[] r23 = r32.getSpans(r4, r5, TextStyleSpan.class);
        int r33 = r23.length;
        int r42 = 0;
    L18:
        if (r42 >= r33) goto L23;
        if (((TextStyleSpan) r23[r42]).isSpoiler() == true) goto L21;
        r42 = r42 + 1;
        goto L18
    L21:
        return true;
    L23:
        return false;
    L14:
        r22 = false;
        goto L15
    L8:
        r2 = false;
        goto L9
    }

    private final MathOptions options() {
        Locale r0 = LocaleController.getInstance().getCurrentLocale();
        if (r0 != null) goto L6;
        r0 = Locale.US;
    L6:
        if (Intrinsics.areEqual(r0, this.optionsLocale) == true) goto L9;
        this.optionsLocale = r0;
        this.options = new MathOptions(DecimalFormatSymbols.getInstance(r0).getDecimalSeparator());
    L9:
        return this.options;
    }

    private final void schedule() {
        this.dirty = true;
        this.view.invalidate();
        if (this.ghost.getExtraHeight() == 0) goto L5;
    L10:
        this.view.requestLayout();
        return;
    L5:
        if (this.suggestion != null) goto L10;
        if (canTrigger() == true) goto L10;
    }

    private final boolean undo() {
        if (this.undoStart >= 0) goto L5;
        return false;
    L5:
        CharSequence r0 = this.view.getText();
        if ((r0 instanceof Editable) == false) goto L8;
        Editable r02 = (Editable) r0;
    L9:
        if (r02 != null) goto L12;
        return false;
    L12:
        if (this.undoEnd <= r02.length()) goto L14;
    L23:
        clearUndo();
        return false;
    L14:
        if (this.view.getSelectionStart() != this.undoEnd) goto L23;
        if (this.view.getSelectionEnd() != this.undoEnd) goto L23;
        this.reveal.cancel();
        int r2 = this.undoStart;
        if (edit(new InlineMathController$$ExternalSyntheticLambda2(r02, this)) == true) goto L21;
        return false;
    L21:
        Selection.setSelection(r02, r2);
        clearUndo();
        this.suppressedAt = r2;
        this.dirty = true;
        this.view.requestLayout();
        this.view.invalidate();
        return true;
    L8:
        r02 = null;
        goto L9
    }

    private final void update() {
        if (this.dirty == false) goto L86;
        boolean r0 = false;
        this.dirty = false;
        this.suggestion = null;
        this.ghost.clear();
        if (ExteraConfig.getInlineMathResult() == true) goto L8;
        return;
    L8:
        if (this.reveal.isRunning() == false) goto L10;
        return;
    L10:
        if (this.suppressedAt < 0) goto L13;
        return;
    L13:
        if (this.view.isFocused() == true) goto L15;
        return;
    L15:
        if (this.view.isEnabled() == false) goto L88;
        Layout r4 = this.view.getLayout();
        if (r4 == null) goto L89;
        CharSequence r5 = this.view.getText();
        if (r5 == null) goto L90;
        int r1 = this.view.getSelectionStart();
        if (r1 > 0) goto L26;
        return;
    L26:
        if (r1 == this.view.getSelectionEnd()) goto L28;
        return;
    L28:
        if (r1 > r5.length()) goto L91;
        MathSuggestion r11 = MathExpression.INSTANCE.suggestionAt(r5, r1, options());
        if (r11 != null) goto L34;
        return;
    L34:
        if ((r5 instanceof Spannable) == false) goto L39;
        if (BaseInputConnection.getComposingSpanStart((Spannable) r5) == (-1)) goto L39;
        return;
    L39:
        if ((r5 instanceof Spanned) == false) goto L47;
        if (((Spanned) r5).getSpans(r1 - 1, r1, MetricAffectingSpan.class).length != 0) goto L43;
        boolean r2 = true;
    L44:
        if (r2 == true) goto L47;
        return;
    L43:
        r2 = false;
    L47:
        if (r4.getParagraphDirection(r4.getLineForOffset(r1)) != 1) goto L95;
        int r22 = StringsKt.lastIndexOf$default(r5, '\n', r1 - 1, false, 4, null) + 1;
        int r12 = StringsKt.indexOf$default(r5, '\n', r1, false, 4, null);
        if (r12 >= 0) goto L52;
        r12 = r5.length();
    L52:
        int r6 = r12;
        if (r6 < r5.length()) goto L56;
        r0 = true;
    L56:
        if (hasUnsupportedSpans(r5, r22, r6) == false) goto L64;
        if (r0 == true) goto L59;
        return;
    L59:
        if (this.ghost.buildDetached(this.view, r4, r6, r11.getInsertText()) == false) goto L96;
        this.suggestion = r11;
        return;
    L96:
        return;
    L64:
        if (this.ghost.build(this.view, r4, r22, r6, r1, r11.getInsertText()) == true) goto L67;
        return;
    L67:
        if (this.ghost.getExtraHeight() <= 0) goto L70;
        if (r0 == true) goto L70;
    L73:
        if (r0 == true) goto L75;
    L76:
        this.ghost.clear();
        return;
    L75:
        if (this.ghost.buildDetached(this.view, r4, r6, r11.getInsertText()) == false) goto L76;
    L78:
        this.suggestion = r11;
        return;
    L70:
        if (this.ghost.hasMovedText() == false) goto L78;
        if (this.caretMovedByTouch == false) goto L78;
    L95:
        return;
    L91:
        return;
    L90:
        return;
    L89:
        return;
    L88:
        return;
    }

    private final void updateAnnouncement() {
        MathSuggestion r0 = this.suggestion;
        if (r0 == null) goto L5;
        String r02 = r0.getValue();
    L6:
        if (r02 != null) goto L10;
        this.announcedValue = null;
        AndroidUtilities.cancelRunOnUIThread(this.announce);
        return;
    L10:
        if (Intrinsics.areEqual(r02, this.announcedValue) == true) goto L13;
        AndroidUtilities.cancelRunOnUIThread(this.announce);
        AndroidUtilities.runOnUIThread(this.announce, 600);
        return;
    L13:
        return;
    L5:
        r02 = null;
        goto L6
    }

    public final void cancel() {
        AndroidUtilities.cancelRunOnUIThread(this.announce);
        this.announcedValue = null;
        this.reveal.cancel();
        clearUndo();
        this.suppressedAt = -1;
        this.caretMovedByTouch = false;
        this.suggestion = null;
        this.ghost.clear();
        schedule();
    }

    public final void clipReplacedParagraph(Canvas r10, int r11) {
        if (this.ghost.isEmpty() == false) goto L5;
        return;
    L5:
        if (this.ghost.getDetached() == true) goto L13;
        Layout r0 = this.view.getLayout();
        if (r0 == null) goto L14;
        int r1 = r0.getLineForOffset(this.ghost.getParagraphStart());
        int r2 = r0.getLineForOffset(this.ghost.getParagraphEnd());
        float r112 = r11;
        r10.clipRect(0.0f, r112 + r0.getLineTop(r1), this.view.getWidth(), r112 + r0.getLineBottom(r2), Region.Op.DIFFERENCE);
        return;
    L14:
        return;
    }

    public final void draw(Canvas r4, int r5, int r6, float r7, float r8) {
        boolean r0 = this.reveal.isRunning();
        if (r0 == false) goto L5;
    L8:
        r4.save();
        r4.clipRect(0.0f, r7, this.view.getWidth(), r8);
        r4.translate(r5, r6);
        if (r0 == false) goto L15;
        MathRevealAnimation r52 = this.reveal;
        Delegate r62 = this.delegate;
        if (r62 == null) goto L13;
        int r3 = r62.accentColor();
    L14:
        r52.draw(r4, r3);
    L16:
        r4.restore();
        return;
    L13:
        r3 = this.view.getCurrentTextColor();
        goto L14
    L15:
        this.ghost.setAlpha(this.appear.set(1.0f) * 0.4f);
        this.ghost.draw(r4);
        goto L16
    L5:
        if (this.ghost.isEmpty() == false) goto L8;
        this.appear.set(0.0f, true);
    }

    public final float getCursorShiftX() {
        if (hasCursorShift() == true) goto L5;
        return 0.0f;
    L5:
        return this.ghost.getCursorShiftX();
    }

    public final float getCursorShiftY() {
        if (hasCursorShift() == true) goto L5;
        return 0.0f;
    L5:
        return this.ghost.getCursorShiftY();
    }

    public final int getExtraBottom() {
        return this.ghost.getExtraHeight();
    }

    public final boolean hasCursorShift() {
        return this.ghost.hasMovedText();
    }

    public final void invalidateState() {
        if (this.insertingSelf == true) goto L17;
        int r0 = this.view.getSelectionStart();
        if (r0 == this.suppressedAt) goto L8;
        this.suppressedAt = -1;
    L8:
        if (r0 == this.undoEnd) goto L10;
    L11:
        clearUndo();
    L13:
        if (this.reveal.isRunning() == false) goto L17;
        if (this.reveal.isCaretOutside(r0) == false) goto L17;
        this.reveal.cancel();
        goto L17
    L10:
        if (this.view.getSelectionEnd() == this.undoEnd) goto L13;
    L17:
        schedule();
    }

    public final void onFocusChanged(boolean r1) {
        if (r1 == true) goto L4;
        this.reveal.cancel();
        clearUndo();
    L4:
        schedule();
    }

    public final boolean onKeyEvent(KeyEvent r4) {
        boolean r1 = false;
        if (r4.getAction() != 1) goto L12;
        if (this.swallowKeyCode != 0) goto L7;
    L10:
        return false;
    L7:
        if (r4.getKeyCode() != this.swallowKeyCode) goto L10;
        this.swallowKeyCode = 0;
        return true;
    L12:
        if (r4.getAction() == 0) goto L14;
    L36:
        return r1;
    L14:
        if (r4.getRepeatCount() != 0) goto L36;
        if (r4.isCtrlPressed() == true) goto L36;
        if (r4.isAltPressed() == true) goto L36;
        if (r4.isShiftPressed() == true) goto L36;
        int r0 = r4.getKeyCode();
        if (r0 != 22) goto L26;
    L33:
        r1 = commit();
    L34:
        if (r1 == false) goto L36;
        this.swallowKeyCode = r4.getKeyCode();
        goto L36
    L26:
        if (r0 != 67) goto L28;
        r1 = undo();
        goto L34
    L28:
        if (r0 == 61) goto L33;
        if (r0 == 62) goto L33;
        goto L33
    }

    public final void onTextChanged() {
        if (this.insertingSelf == true) goto L5;
        this.reveal.cancel();
        clearUndo();
        this.suppressedAt = -1;
        this.caretMovedByTouch = false;
    L5:
        schedule();
    }

    public final void onTouchDown() {
        if (this.caretMovedByTouch == true) goto L6;
        this.caretMovedByTouch = true;
        schedule();
        return;
    }

    public final boolean updateOnMeasure() {
        int r0 = this.ghost.getExtraHeight();
        Layout r1 = this.view.getLayout();
        if (r1 == null) goto L5;
        int r12 = r1.getWidth();
    L7:
        if (r12 == this.layoutWidth) goto L9;
        this.layoutWidth = r12;
        this.dirty = true;
    L9:
        update();
        if (AndroidUtilities.isAccessibilityScreenReaderEnabled() == false) goto L13;
        updateAnnouncement();
    L13:
        if (this.ghost.getExtraHeight() == r0) goto L15;
        return true;
    L15:
        return false;
    L5:
        r12 = 0;
        goto L7
    }

    public final InputConnection wrap(InputConnection r2) {
        return new AnonymousClass1(r2, this);
    }
}
