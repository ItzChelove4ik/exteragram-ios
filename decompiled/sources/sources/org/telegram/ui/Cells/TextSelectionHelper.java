package org.telegram.ui.Cells;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.CornerPathEffect;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.Layout;
import android.text.NoCopySpan;
import android.text.SpanWatcher;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.StaticLayout;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.animation.Interpolator;
import android.view.animation.OvershootInterpolator;
import android.widget.Magnifier;
import androidx.core.widget.NestedScrollView;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.system.VibratorUtils;
import com.google.zxing.common.detector.MathUtils;
import java.util.ArrayList;
import java.util.Arrays;
import kotlin.jvm.internal.LongCompanionObject;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LanguageDetector;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.R;
import org.telegram.messenger.RichMessageLayout;
import org.telegram.messenger.SharedConfig;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.ActionBarPopupWindow;
import org.telegram.ui.ActionBar.FloatingActionMode;
import org.telegram.ui.ActionBar.FloatingToolbar;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.ArticleViewer;
import org.telegram.ui.Cells.TextSelectionHelper.SelectableView;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.CornerPath;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.ui.Components.TranslateAlert2;

/* loaded from: classes6.dex */
public abstract class TextSelectionHelper<Cell extends SelectableView> {
    private ActionMode actionMode;
    protected boolean actionsIsShowing;
    boolean allowDiscard;
    public boolean allowScrollPrentRelative;
    protected Callback callback;
    protected int capturedX;
    protected int capturedY;
    protected float cornerRadius;
    private RectF endArea;
    protected float enterProgress;
    private ValueAnimator handleViewAnimator;
    protected float handleViewProgress;
    private final Runnable hideActionsRunnable;
    private Interpolator interpolator;
    private boolean invalidateParent;
    private boolean isOneTouch;
    int keyboardSize;
    private int lastX;
    private int lastY;
    protected final LayoutBlock layoutBlock;
    private int longpressDelay;
    private Magnifier magnifier;
    private float magnifierDx;
    private float magnifierDy;
    private float magnifierX;
    private float magnifierXanimated;
    private float magnifierY;
    private float magnifierYanimated;
    protected Cell maybeSelectedView;
    protected int maybeTextX;
    protected int maybeTextY;
    protected boolean movingDirectionSettling;
    protected boolean movingHandle;
    protected boolean movingHandleStart;
    float movingOffsetX;
    float movingOffsetY;
    protected boolean multiselect;
    private OnAiGenerateListener onAiGenerateListener;
    private OnTranslateListener onTranslateListener;
    private boolean parentIsScrolling;
    protected NestedScrollView parentNestedScrollView;
    protected RecyclerListView parentRecyclerView;
    protected ViewGroup parentView;
    protected PathWithSavedBottom path;
    private ActionBarPopupWindow popupWindow;
    protected Theme.ResourcesProvider resourcesProvider;
    private boolean scrollDown;
    private Runnable scrollRunnable;
    private boolean scrolling;
    protected Integer selectedCellEditDate;
    protected int selectedCellId;
    protected Cell selectedView;
    public int selectionEnd;
    protected Paint selectionHandlePaint;
    protected Path selectionHandlePath;
    protected Paint selectionPaint;
    protected CornerPath selectionPath;
    protected PathCopyTo selectionPathMirror;
    public int selectionStart;
    protected boolean showActionsAsPopupAlways;
    protected final Runnable showActionsRunnable;
    private boolean snap;
    private RectF startArea;
    final Runnable startSelectionRunnable;
    private final ScalablePath tempPath2;
    protected final Rect textArea;
    private final ActionMode.Callback textSelectActionCallback;
    protected TextSelectionHelper<Cell>.TextSelectionOverlay textSelectionOverlay;
    protected int textX;
    protected int textY;
    protected int[] tmpCoord;
    private int topOffset;
    private int touchSlop;
    private boolean tryCapture;
    public boolean useMovingOffset;

    /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$1, reason: invalid class name */
    public class AnonymousClass1 implements Runnable {
        final /* synthetic */ TextSelectionHelper this$0;

        public AnonymousClass1(TextSelectionHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextSelectionHelper.m7082$$Nest$fgetscrolling(this.this$0) == false) goto L45;
            TextSelectionHelper r0 = this.this$0;
            if (r0.parentRecyclerView != null) goto L9;
            if (r0.parentNestedScrollView != null) goto L9;
            return;
        L9:
            if (r0.multiselect == false) goto L14;
            if (r0.selectedView != null) goto L14;
            int r02 = AndroidUtilities.dp(8.0f);
        L16:
            TextSelectionHelper r1 = this.this$0;
            if (r1.multiselect == false) goto L19;
        L29:
            TextSelectionHelper r12 = this.this$0;
            RecyclerListView r2 = r12.parentRecyclerView;
            if (r2 != null) goto L32;
        L36:
            NestedScrollView r13 = this.this$0.parentNestedScrollView;
            if (r13 == null) goto L43;
            int r22 = r13.getScrollY();
            if (TextSelectionHelper.m7080$$Nest$fgetscrollDown(this.this$0) == true) goto L42;
            r02 = -r02;
        L42:
            r13.setScrollY(r22 + r02);
        L43:
            AndroidUtilities.runOnUIThread(this);
            return;
        L32:
            if (TextSelectionHelper.m7080$$Nest$fgetscrollDown(r12) == false) goto L34;
            int r14 = r02;
        L35:
            r2.scrollBy(0, r14);
            goto L36
        L34:
            r14 = -r02;
            goto L35
        L19:
            if (r1.allowScrollPrentRelative == true) goto L29;
            boolean r15 = TextSelectionHelper.m7080$$Nest$fgetscrollDown(r1);
            TextSelectionHelper r23 = this.this$0;
            if (r15 == false) goto L27;
            if ((r23.selectedView.getBottom() - r02) >= (this.this$0.parentView.getMeasuredHeight() - this.this$0.getParentBottomPadding())) goto L29;
            int r03 = this.this$0.selectedView.getBottom() - this.this$0.parentView.getMeasuredHeight();
            int r16 = this.this$0.getParentBottomPadding();
        L25:
            r02 = r03 + r16;
            goto L29
        L27:
            if ((r23.selectedView.getTop() + r02) <= this.this$0.getParentTopPadding()) goto L29;
            r03 = -this.this$0.selectedView.getTop();
            r16 = this.this$0.getParentTopPadding();
        L14:
            if (r0.selectedView == null) goto L47;
            r02 = r0.getLineHeight() >> 1;
            goto L16
        L47:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$2, reason: invalid class name */
    public class AnonymousClass2 implements Runnable {
        final /* synthetic */ TextSelectionHelper this$0;

        public AnonymousClass2(TextSelectionHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            TextSelectionHelper r0 = this.this$0;
            Cell r6 = r0.maybeSelectedView;
            if (r6 != null) goto L5;
            return;
        L5:
            if (r0.textSelectionOverlay == null) goto L96;
            Cell r8 = r0.selectedView;
            CharSequence r02 = r0.getText(r6, true);
            RecyclerListView r1 = this.this$0.parentRecyclerView;
            if (r1 == null) goto L10;
            r1.cancelClickRunnables(false);
        L10:
            TextSelectionHelper r12 = this.this$0;
            int r2 = r12.capturedX;
            int r3 = r12.capturedY;
            if (r12.textArea.isEmpty() == true) goto L24;
            Rect r13 = this.this$0.textArea;
            int r4 = r13.right;
            if (r2 <= r4) goto L15;
            r2 = r4 - 1;
        L15:
            int r42 = r13.left;
            if (r2 >= r42) goto L18;
            r2 = r42 + 1;
        L18:
            int r43 = r13.top;
            if (r3 >= r43) goto L21;
            r3 = r43 + 1;
        L21:
            int r14 = r13.bottom;
            if (r3 <= r14) goto L24;
            r3 = r14 - 1;
        L24:
            TextSelectionHelper r15 = this.this$0;
            int r16 = r15.getCharOffsetFromCord(r2, r3, r15.maybeTextX, r15.maybeTextY, r6, true);
            if (r16 < r02.length()) goto L35;
            TextSelectionHelper r32 = this.this$0;
            r32.fillLayoutForOffset(r16, r32.layoutBlock, true);
            TextSelectionHelper r33 = this.this$0;
            Layout r44 = r33.layoutBlock.layout;
            if (r44 != null) goto L30;
            r33.selectionEnd = -1;
            r33.selectionStart = -1;
            return;
        L30:
            int r34 = r44.getLineCount() - 1;
            TextSelectionHelper r45 = this.this$0;
            float r22 = r2 - r45.maybeTextX;
            if (r22 >= (r45.layoutBlock.layout.getLineRight(r34) + AndroidUtilities.dp(4.0f))) goto L35;
            if (r22 <= this.this$0.layoutBlock.layout.getLineLeft(r34)) goto L35;
            r16 = r02.length() - 1;
        L35:
            if (r16 >= 0) goto L37;
        L77:
            TextSelectionHelper.m7093$$Nest$fputtryCapture(this.this$0, false);
            this.this$0.allowDiscard = false;
            return;
        L37:
            if (r16 >= r02.length()) goto L77;
            if (r02.charAt(r16) == '\n') goto L77;
            TextSelectionHelper r23 = this.this$0;
            int r35 = r23.maybeTextX;
            int r46 = r23.maybeTextY;
            r23.clear();
            this.this$0.textSelectionOverlay.setVisibility(0);
            this.this$0.onTextSelected(r6, r8);
            TextSelectionHelper r24 = this.this$0;
            r24.selectionStart = r16;
            r24.selectionEnd = r16;
            if ((r02 instanceof Spanned) == false) goto L56;
            Spanned r25 = (Spanned) r02;
            Emoji.EmojiSpan[] r5 = (Emoji.EmojiSpan[]) r25.getSpans(0, r02.length(), Emoji.EmojiSpan.class);
            int r7 = r5.length;
            int r11 = 0;
        L43:
            if (r11 >= r7) goto L49;
            Emoji.EmojiSpan r122 = r5[r11];
            int r132 = r25.getSpanStart(r122);
            int r123 = r25.getSpanEnd(r122);
            if (r16 < r132) goto L48;
            if (r16 > r123) goto L48;
            TextSelectionHelper r17 = this.this$0;
            r17.selectionStart = r132;
            r17.selectionEnd = r123;
        L48:
            r11 = r11 + 1;
            goto L43
        L49:
            AnimatedEmojiSpan[] r52 = (AnimatedEmojiSpan[]) r25.getSpans(0, r02.length(), AnimatedEmojiSpan.class);
            int r72 = r52.length;
            int r112 = 0;
        L50:
            if (r112 >= r72) goto L56;
            AnimatedEmojiSpan r124 = r52[r112];
            int r133 = r25.getSpanStart(r124);
            int r125 = r25.getSpanEnd(r124);
            if (r16 < r133) goto L55;
            if (r16 > r125) goto L55;
            TextSelectionHelper r18 = this.this$0;
            r18.selectionStart = r133;
            r18.selectionEnd = r125;
        L55:
            r112 = r112 + 1;
        L56:
            TextSelectionHelper r19 = this.this$0;
            if (r19.selectionStart != r19.selectionEnd) goto L68;
        L58:
            int r110 = this.this$0.selectionStart;
            if (r110 <= 0) goto L64;
            if (TextSelectionHelper.isInterruptedCharacter(r02.charAt(r110 - 1)) == false) goto L64;
            this.this$0.selectionStart--;
        L64:
            if (this.this$0.selectionEnd >= r02.length()) goto L68;
            if (TextSelectionHelper.isInterruptedCharacter(r02.charAt(this.this$0.selectionEnd)) == false) goto L68;
            this.this$0.selectionEnd++;
        L68:
            TextSelectionHelper r03 = this.this$0;
            r03.textX = r35;
            r03.textY = r46;
            r03.selectedView = r6;
            r03.textSelectionOverlay.performHapticFeedback(VibratorUtils.getType(0), 1);     // Catch: Exception -> L79
        L70:
            AndroidUtilities.cancelRunOnUIThread(this.this$0.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.this$0.showActionsRunnable);
            this.this$0.showHandleViews();
            this.this$0.invalidate();
            if (r8 == null) goto L73;
            r8.invalidate();
        L73:
            Callback r04 = this.this$0.callback;
            if (r04 == null) goto L76;
            r04.onStateChanged(true);
        L76:
            TextSelectionHelper r05 = this.this$0;
            r05.movingHandle = true;
            r05.movingDirectionSettling = true;
            TextSelectionHelper.m7087$$Nest$fputisOneTouch(r05, true);
            TextSelectionHelper r06 = this.this$0;
            r06.movingOffsetY = 0.0f;
            r06.movingOffsetX = 0.0f;
            r06.onOffsetChanged();
            goto L77
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$3, reason: invalid class name */
    public class AnonymousClass3 implements Runnable {
        final /* synthetic */ TextSelectionHelper this$0;

        public AnonymousClass3(TextSelectionHelper r1) {
            this.this$0 = r1;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (TextSelectionHelper.m7066$$Nest$fgetactionMode(this.this$0) == null) goto L8;
            TextSelectionHelper r0 = this.this$0;
            if (r0.actionsIsShowing == true) goto L9;
            TextSelectionHelper.m7066$$Nest$fgetactionMode(r0).hide(LongCompanionObject.MAX_VALUE);
            AndroidUtilities.runOnUIThread(TextSelectionHelper.m7068$$Nest$fgethideActionsRunnable(this.this$0), 1000);
            return;
        L9:
            return;
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$4, reason: invalid class name */
    public class AnonymousClass4 implements ActionMode.Callback {
        final /* synthetic */ TextSelectionHelper this$0;
        private String translateFromLanguage;

        public static /* synthetic */ void $r8$lambda$0lIpiHkWzXUmxqzGn5Rt0oicD3U(AnonymousClass4 r0) {
            r0.lambda$onActionItemClicked$2();
        }

        /* renamed from: $r8$lambda$GjEspndNTycl-MfPQY_AlsLmX0k, reason: not valid java name */
        public static /* synthetic */ void m7100$r8$lambda$GjEspndNTyclMfPQY_AlsLmX0k(AnonymousClass4 r0, Menu r1, String r2) {
            r0.lambda$onPrepareActionMode$0(r1, r2);
        }

        public static /* synthetic */ void $r8$lambda$eEPR_GqdxxqRHZuG_JPz_Tff7C4(AnonymousClass4 r0, Menu r1, Exception r2) {
            r0.lambda$onPrepareActionMode$1(r1, r2);
        }

        public AnonymousClass4(TextSelectionHelper r1) {
            this.this$0 = r1;
            this.translateFromLanguage = null;
        }

        private /* synthetic */ void lambda$onActionItemClicked$2() {
            TextSelectionHelper.m7098$$Nest$mshowActions(this.this$0);
        }

        private /* synthetic */ void lambda$onPrepareActionMode$0(Menu r1, String r2) {
            this.translateFromLanguage = r2;
            updateTranslateButton(r1);
        }

        private /* synthetic */ void lambda$onPrepareActionMode$1(Menu r2, Exception r3) {
            FileLog.e("mlkit: failed to detect language in selection");
            FileLog.e(r3);
            this.translateFromLanguage = null;
            updateTranslateButton(r2);
        }

        private void updateTranslateButton(Menu r5) {
            LocaleController.getInstance().getCurrentLocale().getLanguage();
            MenuItem r0 = r5.findItem(3);
            if (r0 != null) goto L5;
            return;
        L5:
            boolean r2 = false;
            if (TextSelectionHelper.m7078$$Nest$fgetonTranslateListener(this.this$0) == null) goto L14;
            String r1 = this.translateFromLanguage;
            if (r1 == null) goto L12;
            if (TranslatorUtils.isRestrictedLanguage(r1) == true) goto L12;
        L13:
            boolean r12 = true;
        L15:
            r0.setVisible(r12);
            MenuItem r52 = r5.getItem(4);
            if (TextSelectionHelper.m7077$$Nest$fgetonAiGenerateListener(this.this$0) == null) goto L18;
            r2 = true;
        L18:
            r52.setVisible(r2);
            return;
        L12:
            if (LanguageDetector.hasSupport() == false) goto L13;
        L14:
            r12 = false;
            goto L15
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r5, MenuItem r6) {
            if (this.this$0.isInSelectionMode() == true) goto L5;
            return true;
        L5:
            int r52 = r6.getItemId();
            if (r52 != 16908321) goto L10;
            TextSelectionHelper.m7094$$Nest$mcopyText(this.this$0);
            return true;
        L10:
            if (r52 != 16908319) goto L20;
            if (this.this$0.onSelectAllOverride() == false) goto L14;
            return true;
        L14:
            TextSelectionHelper r53 = this.this$0;
            CharSequence r54 = r53.getText(r53.selectedView, false);
            if (r54 != null) goto L17;
            return true;
        L17:
            TextSelectionHelper r62 = this.this$0;
            r62.selectionStart = 0;
            r62.selectionEnd = r54.length();
            this.this$0.hideActions();
            this.this$0.invalidate();
            AndroidUtilities.cancelRunOnUIThread(this.this$0.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.this$0.showActionsRunnable);
            return true;
        L20:
            if (r52 != 3) goto L27;
            if (TextSelectionHelper.m7078$$Nest$fgetonTranslateListener(this.this$0) == null) goto L24;
            String r55 = TranslateAlert2.getToLanguage();
            TextSelectionHelper.m7078$$Nest$fgetonTranslateListener(this.this$0).run(this.this$0.getSelectedText(), this.translateFromLanguage, r55, new TextSelectionHelper$4$$ExternalSyntheticLambda0(this));
        L24:
            this.this$0.hideActions();
            return true;
        L27:
            if (r52 != R.id.menu_quote) goto L31;
            TextSelectionHelper.m7097$$Nest$mquoteText(this.this$0);
            this.this$0.hideActions();
            return true;
        L31:
            if (r52 != 4) goto L38;
            if (TextSelectionHelper.m7077$$Nest$fgetonAiGenerateListener(this.this$0) == null) goto L35;
            TextSelectionHelper.m7077$$Nest$fgetonAiGenerateListener(this.this$0).run(this.this$0.getSelectedText());
        L35:
            this.this$0.hideActions();
            return true;
        L38:
            if (r52 != 16908320) goto L41;
            this.this$0.onCutAction();
            this.this$0.hideActions();
            return true;
        L41:
            TextSelectionHelper r63 = this.this$0;
            if (r52 != 16908322) goto L45;
            r63.onPasteAction();
            this.this$0.hideActions();
            return true;
        L45:
            r63.clear();
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r5, Menu r6) {
            r6.add(0, android.R.id.copy, 0, android.R.string.copy);
            r6.add(0, R.id.menu_quote, 1, LocaleController.getString(R.string.Quote));
            r6.add(0, 3, 2, LocaleController.getString(R.string.TranslateMessage));
            r6.add(0, android.R.id.cut, 3, android.R.string.cut);
            r6.add(0, android.R.id.paste, 4, android.R.string.paste);
            r6.add(0, android.R.id.selectAll, 5, android.R.string.selectAll);
            r6.add(0, 4, 4, LocaleController.getString(R.string.Generate));
            return true;
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r6, Menu r7) {
            MenuItem r62 = r7.findItem(R.id.menu_quote);
            if (r62 == null) goto L5;
            r62.setVisible(this.this$0.canShowQuote());
        L5:
            MenuItem r63 = r7.findItem(android.R.id.copy);
            if (r63 == null) goto L8;
            r63.setVisible(this.this$0.canCopy());
        L8:
            MenuItem r64 = r7.findItem(android.R.id.selectAll);
            boolean r0 = false;
            if (r64 == null) goto L27;
            TextSelectionHelper r2 = this.this$0;
            Cell r3 = r2.selectedView;
            if (r3 == null) goto L27;
            CharSequence r22 = r2.getText(r3, false);
            if (this.this$0.canCopy() == true) goto L16;
            r64.setVisible(false);
            goto L27
        L16:
            if (this.this$0.forceShowSelectAll() == false) goto L18;
            r64.setVisible(true);
            goto L27
        L18:
            TextSelectionHelper r32 = this.this$0;
            if (r32.multiselect == false) goto L21;
        L26:
            r64.setVisible(false);
            goto L27
        L21:
            if (r32.selectionStart <= 0) goto L23;
        L25:
            r64.setVisible(true);
            goto L27
        L23:
            if (r32.selectionEnd < (r22.length() - 1)) goto L25;
        L27:
            MenuItem r65 = r7.findItem(android.R.id.cut);
            if (r65 == null) goto L30;
            r65.setVisible(this.this$0.canCut());
        L30:
            MenuItem r66 = r7.findItem(android.R.id.paste);
            if (r66 == null) goto L39;
            if (this.this$0.canPaste() == true) goto L35;
        L37:
            r66.setVisible(r0);
            goto L39
        L35:
            if (this.this$0.clipboardHasContent() == false) goto L37;
            r0 = true;
        L39:
            if (TextSelectionHelper.m7078$$Nest$fgetonTranslateListener(this.this$0) != null) goto L41;
        L45:
            this.translateFromLanguage = null;
            updateTranslateButton(r7);
        L46:
            return true;
        L41:
            if (LanguageDetector.hasSupport() == false) goto L45;
            if (this.this$0.getSelectedText() == null) goto L45;
            LanguageDetector.detectLanguage(this.this$0.getSelectedText().toString(), new TextSelectionHelper$4$$ExternalSyntheticLambda1(this, r7), new TextSelectionHelper$4$$ExternalSyntheticLambda2(this, r7));
            goto L46
        }
    }

    /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$5, reason: invalid class name */
    public class AnonymousClass5 extends ActionMode.Callback2 {
        final /* synthetic */ TextSelectionHelper this$0;
        final /* synthetic */ ActionMode.Callback val$callback;

        public AnonymousClass5(TextSelectionHelper r1, ActionMode.Callback r2) {
            this.this$0 = r1;
            this.val$callback = r2;
        }

        @Override // android.view.ActionMode.Callback
        public boolean onActionItemClicked(ActionMode r1, MenuItem r2) {
            return this.val$callback.onActionItemClicked(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public boolean onCreateActionMode(ActionMode r1, Menu r2) {
            return this.val$callback.onCreateActionMode(r1, r2);
        }

        @Override // android.view.ActionMode.Callback
        public void onDestroyActionMode(ActionMode r1) {
            this.val$callback.onDestroyActionMode(r1);
        }

        @Override // android.view.ActionMode.Callback2
        public void onGetContentRect(ActionMode r7, View r8, Rect r9) {
            if (this.this$0.isInSelectionMode() == true) goto L5;
            return;
        L5:
            this.this$0.pickStartView();
            int[] r72 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0);
            TextSelectionHelper r82 = this.this$0;
            int r2 = 1;
            if (r82.selectedView == null) goto L11;
            int r83 = -r82.getLineHeight();
            TextSelectionHelper r0 = this.this$0;
            int[] r02 = r0.offsetToCord(r0.selectionStart);
            int r3 = r02[0];
            TextSelectionHelper r4 = this.this$0;
            int r32 = r3 + r4.textX;
            int r03 = (((r02[1] + r4.textY) + r72[1]) + (r83 / 2)) - AndroidUtilities.dp(4.0f);
            if (r03 < 1) goto L12;
            r2 = r03;
        L12:
            int r73 = this.this$0.parentView.getWidth();
            this.this$0.pickEndView();
            TextSelectionHelper r84 = this.this$0;
            if (r84.selectedView == null) goto L15;
            r73 = r84.offsetToCord(r84.selectionEnd)[0] + this.this$0.textX;
        L15:
            r9.set(Math.min(r32, r73), r2, Math.max(r32, r73), r2 + 1);
            return;
        L11:
            r32 = 0;
            goto L12
        }

        @Override // android.view.ActionMode.Callback
        public boolean onPrepareActionMode(ActionMode r1, Menu r2) {
            return this.val$callback.onPrepareActionMode(r1, r2);
        }
    }

    public interface ArticleSelectableView extends SelectableView {
        void fillTextLayoutBlocks(ArrayList<TextLayoutBlock> r1);
    }

    public static class ArticleTextSelectionHelper extends TextSelectionHelper<ArticleSelectableView> {
        public int anchorChildPosition;
        public int anchorOffset;
        public int anchorViewPosition;
        public ArrayList<TextLayoutBlock> arrayList;
        SparseIntArray childCountByPosition;
        int endViewChildPosition;
        int endViewOffset;
        int endViewPosition;
        public LinearLayoutManager layoutManager;
        int maybeTextIndex;
        SparseArray<CharSequence> prefixTextByPosition;
        boolean startPeek;
        int startViewChildPosition;
        int startViewOffset;
        int startViewPosition;
        SparseArray<CharSequence> textByPosition;

        public static /* synthetic */ int $r8$lambda$uquVZVcR300YvrtjLb0hkpKCzWo(SpannableStringBuilder r0, ReplaceCopyTextSpannable r1, ReplaceCopyTextSpannable r2) {
            return r0.getSpanStart(r2) - r0.getSpanStart(r1);
        }

        public ArticleTextSelectionHelper() {
            this.startViewPosition = -1;
            this.startViewChildPosition = -1;
            this.endViewPosition = -1;
            this.endViewChildPosition = -1;
            this.maybeTextIndex = -1;
            this.textByPosition = new SparseArray();
            this.prefixTextByPosition = new SparseArray();
            this.childCountByPosition = new SparseIntArray();
            this.arrayList = new ArrayList();
            this.anchorViewPosition = -1;
            this.anchorOffset = -1;
            this.anchorChildPosition = 0;
            this.multiselect = true;
            this.showActionsAsPopupAlways = true;
        }

        private void cacheLayoutBlock(int r2, int r3, TextLayoutBlock r4) {
            int r22 = r2 + (r3 << 16);
            this.textByPosition.put(r22, detachedText(r4.getText()));
            CharSequence r32 = r4.getPrefix();
            SparseArray<CharSequence> r1 = this.prefixTextByPosition;
            if (r32 != null) goto L6;
            r1.remove(r22);
            return;
        L6:
            r1.put(r22, detachedText(r32));
        }

        private static CharSequence detachedText(CharSequence r8) {
            if (r8 != null) goto L6;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            if ((r8 instanceof Spanned) == false) goto L8;
            Spanned r0 = (Spanned) r8;
            SpannableStringBuilder r1 = new SpannableStringBuilder(r8.toString());
            int r3 = 0;
            Object[] r82 = r0.getSpans(0, r0.length(), Object.class);
            int r2 = r82.length;
        L10:
            if (r3 >= r2) goto L26;
            Object r4 = r82[r3];
            if ((r4 instanceof TextWatcher) == true) goto L25;
            if ((r4 instanceof SpanWatcher) == true) goto L25;
            if ((r4 instanceof NoCopySpan) == true) goto L25;
            int r5 = r0.getSpanStart(r4);
            int r6 = r0.getSpanEnd(r4);
            if (r5 < 0) goto L25;
            if (r6 < r5) goto L25;
            if (r5 > r1.length()) goto L25;
            r1.setSpan(r4, r5, Math.min(r6, r1.length()), r0.getSpanFlags(r4));
        L25:
            r3 = r3 + 1;
            goto L10
        L26:
            return r1;
        L8:
            return r8.toString();
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int findClosestLayoutIndex(int r8, int r9, ArticleSelectableView r10) {
            int r1 = 0;
            if ((r10 instanceof ViewGroup) == false) goto L16;
            ViewGroup r0 = (ViewGroup) r10;
            int r2 = 0;
        L6:
            if (r2 >= r0.getChildCount()) goto L16;
            View r3 = r0.getChildAt(r2);
            if ((r3 instanceof ArticleSelectableView) == false) goto L15;
            float r4 = r9;
            if (r4 <= r3.getY()) goto L15;
            if (r4 >= (r3.getY() + r3.getHeight())) goto L15;
            return findClosestLayoutIndex((int) (r8 - r3.getX()), (int) (r4 - r3.getY()), (ArticleSelectableView) r3);
        L15:
            r2 = r2 + 1;
        L16:
            this.arrayList.clear();
            r10.fillTextLayoutBlocks(this.arrayList);
            if (this.arrayList.isEmpty() == false) goto L19;
            return -1;
        L19:
            int r102 = this.arrayList.size() - 1;
            int r22 = Integer.MAX_VALUE;
            int r42 = -1;
            int r32 = Integer.MAX_VALUE;
        L20:
            if (r102 < 0) goto L29;
            TextLayoutBlock r5 = this.arrayList.get(r102);
            int r6 = r5.getY();
            int r52 = r5.getLayout().getHeight() + r6;
            if (r9 < r6) goto L25;
            if (r9 >= r52) goto L25;
        L30:
            if (r102 >= 0) goto L32;
            return -1;
        L32:
            int r92 = this.arrayList.get(r102).getRow();
            if (r92 > 0) goto L35;
        L48:
            return r102;
        L35:
            if (r1 >= AndroidUtilities.dp(24.0f)) goto L48;
            int r02 = this.arrayList.size() - 1;
        L37:
            if (r02 < 0) goto L48;
            TextLayoutBlock r12 = this.arrayList.get(r02);
            if (r12.getRow() != r92) goto L47;
            int r33 = r12.getX();
            int r43 = r12.getX() + r12.getLayout().getWidth();
            if (r8 < r33) goto L44;
            if (r8 > r43) goto L44;
            return r02;
        L44:
            int r13 = Math.min(Math.abs(r8 - r33), Math.abs(r8 - r43));
            if (r13 >= r22) goto L47;
            r102 = r02;
            r22 = r13;
        L47:
            r02 = r02 - 1;
        L25:
            int r53 = Math.min(Math.abs(r9 - r6), Math.abs(r9 - r52));
            if (r53 >= r32) goto L28;
            r42 = r102;
            r32 = r53;
        L28:
            r102 = r102 - 1;
            goto L20
        L29:
            r1 = r32;
            r102 = r42;
            goto L30
        }

        /* JADX WARN: Multi-variable type inference failed */
        private int getAdapterPosition(ArticleSelectableView r4) {
            View r42 = (View) r4;
            ViewParent r0 = r42.getParent();
        L3:
            ViewGroup r1 = this.parentView;
            if (r0 == r1) goto L10;
            if (r0 == null) goto L10;
            if ((r0 instanceof View) == false) goto L9;
            r42 = r0;
            r0 = r42.getParent();
            goto L3
        L9:
            r0 = null;
        L10:
            if (r0 == null) goto L17;
            RecyclerListView r3 = this.parentRecyclerView;
            if (r3 == null) goto L16;
            return r3.getChildAdapterPosition(r42);
        L16:
            return r1.indexOfChild(r42);
        L17:
            return -1;
        }

        private static int lexCompare(int r0, int r1, int r2, int r3, int r4, int r5) {
            if (r0 != r3) goto L4;
            if (r1 == r4) goto L9;
            return Integer.compare(r1, r4);
        L9:
            return Integer.compare(r2, r5);
        L4:
            return Integer.compare(r0, r3);
        }

        private void populateTextCacheForView(ArticleSelectableView r3, int r4) {
            this.arrayList.clear();
            r3.fillTextLayoutBlocks(this.arrayList);
            int r32 = this.arrayList.size();
            this.childCountByPosition.put(r4, r32);
            int r0 = 0;
        L3:
            if (r0 >= r32) goto L5;
            cacheLayoutBlock(r4, r0, this.arrayList.get(r0));
            r0 = r0 + 1;
            goto L3
        }

        private boolean selectAllBlocksRangeInternal(int r3, int r4, int r5, ArticleSelectableView r6) {
            this.selectedView = r6;
            this.selectionStart = 0;
            this.selectionEnd = r5;
            this.startViewPosition = r3;
            this.endViewPosition = r4;
            this.endViewChildPosition = 0;
            this.startViewChildPosition = 0;
            this.startViewOffset = 0;
            this.endViewOffset = r5;
            SparseIntArray r52 = this.childCountByPosition;
            r52.put(r3, Math.max(1, r52.get(r3)));
            SparseIntArray r53 = this.childCountByPosition;
            r53.put(r4, Math.max(1, r53.get(r4)));
            this.anchorViewPosition = r3;
            this.anchorOffset = 0;
            TextSelectionHelper<Cell>.TextSelectionOverlay r32 = this.textSelectionOverlay;
            if (r32 == null) goto L5;
            r32.setVisibility(0);
        L5:
            showHandleViews();
            invalidate();
            hideActions();
            AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.showActionsRunnable);
            Callback r2 = this.callback;
            if (r2 == null) goto L8;
            r2.onStateChanged(true);
        L8:
            return true;
        }

        public void cacheChildText(int r3, int r4, CharSequence r5) {
            this.textByPosition.put((r4 << 16) + r3, detachedText(r5));
            SparseIntArray r2 = this.childCountByPosition;
            r2.put(r3, Math.max(r4 + 1, r2.get(r3)));
        }

        public void cacheText(int r2, CharSequence r3, CharSequence r4) {
            this.textByPosition.put(r2, detachedText(r3));
            SparseArray<CharSequence> r32 = this.prefixTextByPosition;
            if (r4 != null) goto L5;
            r32.remove(r2);
        L6:
            SparseIntArray r1 = this.childCountByPosition;
            r1.put(r2, Math.max(1, r1.get(r2)));
            return;
        L5:
            r32.put(r2, detachedText(r4));
            goto L6
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean canSelect(int r3) {
            if (this.startViewPosition == this.endViewPosition) goto L5;
            return true;
        L5:
            if (this.startViewChildPosition == this.endViewChildPosition) goto L7;
            return true;
        L7:
            return super.canSelect(r3);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean canShowActions() {
            LinearLayoutManager r0 = this.layoutManager;
            if (r0 != null) goto L5;
            return true;
        L5:
            int r02 = r0.findFirstVisibleItemPosition();
            int r2 = this.layoutManager.findLastVisibleItemPosition();
            int r3 = this.startViewPosition;
            if (r02 >= r3) goto L8;
        L9:
            if (r2 >= r3) goto L11;
        L13:
            if (r3 >= r02) goto L15;
            return false;
        L15:
            if (this.endViewPosition > r2) goto L19;
            return true;
        L19:
            return false;
        L11:
            if (r2 > this.endViewPosition) goto L13;
        L12:
            return true;
        L8:
            if (r02 <= this.endViewPosition) goto L12;
            goto L9
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void clear(boolean r2) {
            super.clear(r2);
            this.startViewPosition = -1;
            this.endViewPosition = -1;
            this.startViewChildPosition = -1;
            this.endViewChildPosition = -1;
            this.textByPosition.clear();
            this.childCountByPosition.clear();
            this.anchorViewPosition = -1;
            this.anchorOffset = -1;
        }

        public void draw(Canvas r11, ArticleSelectableView r12, int r13) {
            Paint r3 = this.selectionPaint;
            int r4 = Theme.key_chat_inTextSelectionHighlight;
            r3.setColor(getThemedColor(r4));
            this.selectionHandlePaint.setColor(getThemedColor(r4));
            int r32 = getAdapterPosition(r12);
            if (r32 < 0) goto L69;
            this.arrayList.clear();
            r12.fillTextLayoutBlocks(this.arrayList);
            if (r13 >= 0) goto L8;
            return;
        L8:
            if (r13 >= this.arrayList.size()) goto L61;
            TextLayoutBlock r1 = this.arrayList.get(r13);
            if (r1 != null) goto L12;
            return;
        L12:
            if (r1.getLayout() != null) goto L14;
            return;
        L14:
            if (r1.getLayout().getText() == null) goto L64;
            int r42 = this.endViewOffset;
            int r5 = r1.getLayout().getText().length();
            if (r42 <= r5) goto L18;
            r42 = r5;
        L18:
            int r6 = this.startViewPosition;
            if (r32 == r6) goto L21;
        L38:
            int r52 = r42;
            if (r32 == r6) goto L41;
        L44:
            int r7 = this.endViewPosition;
            if (r32 == r7) goto L47;
        L50:
            if (r32 <= r6) goto L52;
            if (r32 >= r7) goto L52;
        L58:
            drawSelection(r11, r1.getLayout(), 0, r5, false, false, 0.0f);
            return;
        L52:
            if (r32 == r6) goto L54;
        L55:
            if (r32 == r7) goto L57;
            return;
        L57:
            if (r13 < this.endViewChildPosition) goto L58;
            return;
        L54:
            if (r13 > this.startViewChildPosition) goto L58;
        L47:
            if (this.endViewChildPosition != r13) goto L50;
            drawSelection(r11, r1.getLayout(), 0, r52, false, true, 0.0f);
            return;
        L41:
            if (this.startViewChildPosition != r13) goto L44;
            drawSelection(r11, r1.getLayout(), this.startViewOffset, r5, true, false, 0.0f);
            return;
        L21:
            if (r32 != this.endViewPosition) goto L38;
            int r33 = this.startViewChildPosition;
            int r62 = this.endViewChildPosition;
            if (r33 != r62) goto L27;
            if (r33 != r13) goto L27;
            drawSelection(r11, r1.getLayout(), this.startViewOffset, r42, true, true, 0.0f);
            return;
        L27:
            if (r13 != r33) goto L30;
            drawSelection(r11, r1.getLayout(), this.startViewOffset, r5, true, false, 0.0f);
            return;
        L30:
            int r53 = r42;
            if (r13 != r62) goto L34;
            drawSelection(r11, r1.getLayout(), 0, r53, false, true, 0.0f);
            return;
        L34:
            if (r13 <= r33) goto L65;
            if (r13 >= r62) goto L66;
            drawSelection(r11, r1.getLayout(), 0, r5, false, false, 0.0f);
            return;
        L66:
            return;
        L65:
            return;
        L64:
            return;
        L61:
            return;
        }

        public boolean expandSelectionToWholeCurrentBlock() {
            if (isInSelectionMode() == true) goto L5;
        L28:
            return false;
        L5:
            if (this.selectedView == null) goto L28;
            if (this.startViewPosition != this.endViewPosition) goto L28;
            int r0 = this.startViewChildPosition;
            if (r0 != this.endViewChildPosition) goto L28;
            if (r0 >= 0) goto L14;
            r0 = 0;
        L14:
            this.arrayList.clear();
            ((ArticleSelectableView) this.selectedView).fillTextLayoutBlocks(this.arrayList);
            if (this.arrayList.isEmpty() == true) goto L28;
            if (r0 >= this.arrayList.size()) goto L28;
            int r2 = this.arrayList.get(r0).getLayout().getText().length();
            if (r2 <= 0) goto L28;
            if (this.startViewOffset > 0) goto L27;
            if (this.endViewOffset >= r2) goto L28;
        L27:
            return selectRangeOf((ArticleSelectableView) this.selectedView, r0, 0, r2);
        }

        public boolean extendSelectionTo(ArticleSelectableView r2, int r3) {
            return extendSelectionTo(r2, 0, r3);
        }

        public boolean extendSelectionTo(ArticleSelectableView r8, int r9, int r10) {
            int r0 = getAdapterPosition(r8);
            if (r0 >= 0) goto L7;
            return false;
        L7:
            if (this.anchorViewPosition >= 0) goto L9;
            this.anchorViewPosition = this.startViewPosition;
            this.anchorChildPosition = this.startViewChildPosition;
            this.anchorOffset = this.startViewOffset;
        L9:
            populateTextCacheForView(r8, r0);
            int r2 = r10;
            int r82 = lexCompare(r0, r9, r2, this.anchorViewPosition, this.anchorChildPosition, this.anchorOffset);
            int r92 = this.anchorViewPosition;
            if (r82 >= 0) goto L12;
            int r83 = this.anchorChildPosition;
            int r1 = r92;
            int r93 = r9;
            r2 = this.anchorOffset;
            int r102 = r2;
        L14:
            if (r0 != r1) goto L19;
            if (r93 != r83) goto L19;
            if (r102 != r2) goto L19;
            clear();
            return true;
        L19:
            this.startViewPosition = r0;
            this.startViewChildPosition = r93;
            this.startViewOffset = r102;
            this.endViewPosition = r1;
            this.endViewChildPosition = r83;
            this.endViewOffset = r2;
            pickEndView();
            invalidate();
            TextSelectionHelper<Cell>.TextSelectionOverlay r84 = this.textSelectionOverlay;
            if (r84 == null) goto L22;
            r84.invalidate();
        L22:
            AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.showActionsRunnable);
            return true;
        L12:
            int r85 = this.anchorChildPosition;
            r102 = this.anchorOffset;
            r93 = r85;
            r83 = r9;
            r1 = r0;
            r0 = r92;
            goto L14
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void fillLayoutForOffset(int r3, LayoutBlock r4, boolean r5) {
            this.arrayList.clear();
            r4.selectionBounds = null;
            if (r5 == false) goto L6;
            Cell r0 = this.maybeSelectedView;
        L5:
            ArticleSelectableView r02 = (ArticleSelectableView) r0;
            if (r02 != null) goto L10;
            r4.layout = null;
            return;
        L10:
            r02.fillTextLayoutBlocks(this.arrayList);
            if (r5 == false) goto L14;
            int r52 = this.maybeTextIndex;
        L17:
            if (r52 >= 0) goto L19;
        L23:
            r4.layout = null;
            return;
        L19:
            if (r52 >= this.arrayList.size()) goto L23;
            r4.layout = this.arrayList.get(r52).getLayout();
            r4.selectionBounds = this.arrayList.get(r52).getSelectionBounds();
            r4.yOffset = 0.0f;
            r4.xOffset = 0.0f;
            return;
        L14:
            if (this.startPeek == false) goto L16;
            r52 = this.startViewChildPosition;
            goto L17
        L16:
            r52 = this.endViewChildPosition;
            goto L17
        L6:
            r0 = this.selectedView;
            goto L5
        }

        public int getAnchorCell() {
            return this.anchorViewPosition;
        }

        public int getAnchorChildPosition() {
            return this.anchorChildPosition;
        }

        public int getAnchorOffset() {
            return this.anchorOffset;
        }

        public int getCharOffsetFromCord(int r2, int r3, int r4, int r5, ArticleSelectableView r6, boolean r7) {
            if (r6 != null) goto L5;
            return -1;
        L5:
            int r22 = r2 - r4;
            int r32 = r3 - r5;
            this.arrayList.clear();
            r6.fillTextLayoutBlocks(this.arrayList);
            if (r7 == false) goto L9;
            int r42 = this.maybeTextIndex;
        L12:
            if (r42 >= 0) goto L14;
        L40:
            return -1;
        L14:
            if (r42 >= this.arrayList.size()) goto L40;
            Layout r1 = this.arrayList.get(r42).getLayout();
            if (r22 >= 0) goto L19;
            r22 = 1;
        L19:
            if (r32 >= 0) goto L22;
            r32 = 1;
        L22:
            if (r22 <= r1.getWidth()) goto L25;
            r22 = r1.getWidth();
        L25:
            if (r32 <= r1.getLineBottom(r1.getLineCount() - 1)) goto L27;
            r32 = r1.getLineBottom(r1.getLineCount() - 1) - 1;
        L27:
            int r43 = 0;
        L29:
            if (r43 >= r1.getLineCount()) goto L36;
            if (r32 < r1.getLineTop(r43)) goto L35;
            if (r32 > r1.getLineBottom(r43)) goto L35;
        L37:
            if (r43 < 0) goto L40;
            return r1.getOffsetForHorizontal(r43, r22);
        L35:
            r43 = r43 + 1;
            goto L29
        L36:
            r43 = -1;
            goto L37
        L9:
            if (this.startPeek == false) goto L11;
            r42 = this.startViewChildPosition;
            goto L12
        L11:
            r42 = this.endViewChildPosition;
            goto L12
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ int getCharOffsetFromCord(int r1, int r2, int r3, int r4, SelectableView r5, boolean r6) {
            return getCharOffsetFromCord(r1, r2, r3, r4, (ArticleSelectableView) r5, r6);
        }

        public int getEndCell() {
            return this.endViewPosition;
        }

        public int getEndChildPosition() {
            return this.endViewChildPosition;
        }

        public int getEndOffset() {
            return this.endViewOffset;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getLineHeight() {
            int r1 = 0;
            if (this.selectedView != null) goto L5;
            return 0;
        L5:
            this.arrayList.clear();
            ((ArticleSelectableView) this.selectedView).fillTextLayoutBlocks(this.arrayList);
            if (this.startPeek == false) goto L8;
            int r0 = this.startViewChildPosition;
        L9:
            if (r0 >= 0) goto L11;
        L21:
            return 0;
        L11:
            if (r0 >= this.arrayList.size()) goto L21;
            Layout r4 = this.arrayList.get(r0).getLayout();
            int r02 = Integer.MAX_VALUE;
        L15:
            if (r1 >= r4.getLineCount()) goto L20;
            int r2 = r4.getLineBottom(r1) - r4.getLineTop(r1);
            if (r2 >= r02) goto L19;
            r02 = r2;
        L19:
            r1 = r1 + 1;
            goto L15
        L20:
            return r02;
        L8:
            r0 = this.endViewChildPosition;
            goto L9
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public CharSequence getSelectedText() {
            SpannableStringBuilder r0 = new SpannableStringBuilder();
            int r1 = this.startViewPosition;
        L3:
            int r2 = this.endViewPosition;
            if (r1 > r2) goto L92;
            int r4 = this.startViewPosition;
            if (r1 != r4) goto L51;
            if (r4 != r2) goto L9;
            int r22 = this.endViewChildPosition;
        L10:
            int r42 = this.startViewChildPosition;
        L11:
            if (r42 > r22) goto L90;
            int r8 = (r42 << 16) + r1;
            CharSequence r7 = this.textByPosition.get(r8);
            if (r7 == null) goto L50;
            int r9 = this.startViewPosition;
            int r10 = this.endViewPosition;
            if (r9 == r10) goto L18;
        L31:
            if (r9 != r10) goto L42;
            if (r42 != this.endViewChildPosition) goto L42;
            CharSequence r82 = this.prefixTextByPosition.get(r8);
            if (r82 == null) goto L37;
            r0.append(r82).append(' ');
        L37:
            int r83 = this.endViewOffset;
            if (r83 <= r7.length()) goto L40;
            r83 = r7.length();
        L40:
            r0.append(r7.subSequence(0, r83));
            r0.append('\n');
        L42:
            if (r42 != this.startViewChildPosition) goto L46;
            int r84 = this.startViewOffset;
            if (r84 >= r7.length()) goto L50;
            r0.append(r7.subSequence(r84, r7.length()));
            r0.append('\n');
            goto L50
        L46:
            CharSequence r85 = this.prefixTextByPosition.get(r8);
            if (r85 == null) goto L49;
            r0.append(r85).append(' ');
        L49:
            r0.append(r7);
            r0.append('\n');
            goto L50
        L18:
            if (r42 != this.endViewChildPosition) goto L31;
            if (r42 != this.startViewChildPosition) goto L31;
            int r86 = this.endViewOffset;
            int r92 = this.startViewOffset;
            if (r86 < r92) goto L26;
            r92 = r86;
            r86 = r92;
        L26:
            if (r86 >= r7.length()) goto L50;
            if (r92 <= r7.length()) goto L30;
            r92 = r7.length();
        L30:
            r0.append(r7.subSequence(r86, r92));
            r0.append('\n');
        L50:
            r42 = r42 + 1;
        L90:
            r1 = r1 + 1;
            goto L3
        L9:
            r22 = this.childCountByPosition.get(r1) - 1;
            goto L10
        L51:
            if (r1 != r2) goto L84;
            int r23 = 0;
        L54:
            if (r23 > this.endViewChildPosition) goto L90;
            int r72 = (r23 << 16) + r1;
            CharSequence r43 = this.textByPosition.get(r72);
            if (r43 == null) goto L83;
            if (this.startViewPosition == this.endViewPosition) goto L61;
        L70:
            int r87 = this.endViewChildPosition;
            SparseArray<CharSequence> r93 = this.prefixTextByPosition;
            if (r23 != r87) goto L79;
            CharSequence r73 = r93.get(r72);
            if (r73 == null) goto L75;
            r0.append(r73).append(' ');
        L75:
            int r74 = this.endViewOffset;
            if (r74 <= r43.length()) goto L78;
            r74 = r43.length();
        L78:
            r0.append(r43.subSequence(0, r74));
            r0.append('\n');
            goto L83
        L79:
            CharSequence r75 = r93.get(r72);
            if (r75 == null) goto L82;
            r0.append(r75).append(' ');
        L82:
            r0.append(r43);
            r0.append('\n');
            goto L83
        L61:
            if (r23 != this.endViewChildPosition) goto L70;
            if (r23 != this.startViewChildPosition) goto L70;
            int r76 = this.endViewOffset;
            int r88 = this.startViewOffset;
            if (r88 >= r43.length()) goto L83;
            if (r76 <= r43.length()) goto L69;
            r76 = r43.length();
        L69:
            r0.append(r43.subSequence(r88, r76));
            r0.append('\n');
        L83:
            r23 = r23 + 1;
            goto L54
        L84:
            int r24 = this.childCountByPosition.get(r1);
            int r3 = this.startViewChildPosition;
        L85:
            if (r3 >= r24) goto L90;
            int r77 = (r3 << 16) + r1;
            CharSequence r44 = this.prefixTextByPosition.get(r77);
            if (r44 == null) goto L89;
            r0.append(r44).append(' ');
        L89:
            r0.append(this.textByPosition.get(r77));
            r0.append('\n');
            r3 = r3 + 1;
            goto L85
        L92:
            if (r0.length() <= 0) goto L112;
            ReplaceCopyTextSpannable[] r13 = (ReplaceCopyTextSpannable[]) r0.getSpans(0, r0.length() - 1, ReplaceCopyTextSpannable.class);
            if (r13 != null) goto L96;
        L107:
            IgnoreCopySpannable[] r132 = (IgnoreCopySpannable[]) r0.getSpans(0, r0.length() - 1, IgnoreCopySpannable.class);
            int r12 = r132.length;
            int r25 = 0;
        L108:
            if (r25 >= r12) goto L111;
            IgnoreCopySpannable r45 = r132[r25];
            r0.delete(r0.getSpanStart(r45), r0.getSpanEnd(r45));
            r25 = r25 + 1;
            goto L108
        L111:
            return r0.subSequence(0, r0.length() - 1);
        L96:
            if (r13.length <= 0) goto L107;
            Arrays.sort(r13, new TextSelectionHelper$ArticleTextSelectionHelper$$ExternalSyntheticLambda0(r0));
            int r14 = r13.length;
            int r26 = 0;
        L98:
            if (r26 >= r14) goto L107;
            ReplaceCopyTextSpannable r46 = r13[r26];
            int r5 = r0.getSpanStart(r46);
            int r6 = r0.getSpanEnd(r46);
            if (r5 < 0) goto L106;
            if (r6 <= r5) goto L106;
            CharSequence r47 = r46.replacement;
            if (r47 != null) goto L105;
            r47 = _UrlKt.FRAGMENT_ENCODE_SET;
        L105:
            r0.replace(r5, r6, r47);
        L106:
            r26 = r26 + 1;
            goto L98
        L112:
            return null;
        }

        public CharSequence getSelectedTextPublic() {
            return getSelectedText();
        }

        public int getStartCell() {
            return this.startViewPosition;
        }

        public int getStartChildPosition() {
            return this.startViewChildPosition;
        }

        public int getStartOffset() {
            return this.startViewOffset;
        }

        public CharSequence getText(ArticleSelectableView r2, boolean r3) {
            this.arrayList.clear();
            r2.fillTextLayoutBlocks(this.arrayList);
            if (r3 == false) goto L6;
            int r22 = this.maybeTextIndex;
        L10:
            if (this.arrayList.isEmpty() == true) goto L17;
            if (r22 >= 0) goto L13;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L13:
            if (r22 < this.arrayList.size()) goto L16;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L16:
            return this.arrayList.get(r22).getLayout().getText();
        L17:
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L6:
            if (this.startPeek == false) goto L8;
            r22 = this.startViewChildPosition;
            goto L10
        L8:
            r22 = this.endViewChildPosition;
            goto L10
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ CharSequence getText(SelectableView r1, boolean r2) {
            return getText((ArticleSelectableView) r1, r2);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void invalidate() {
            super.invalidate();
            if (this.parentView == null) goto L8;
            int r0 = 0;
        L6:
            if (r0 >= this.parentView.getChildCount()) goto L10;
            this.parentView.getChildAt(r0).invalidate();
            r0 = r0 + 1;
            goto L6
        L10:
            return;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public boolean isSelectable(View r3) {
            if ((r3 instanceof ArticleSelectableView) == false) goto L9;
            this.arrayList.clear();
            ((ArticleSelectableView) r3).fillTextLayoutBlocks(this.arrayList);
            if ((r3 instanceof ArticleViewer.BlockTableCell) == false) goto L8;
            return true;
        L8:
            return !this.arrayList.isEmpty();
        L9:
            return false;
        }

        public void jumpToLine(int r8, int r9, boolean r10, float r11, float r12, ArticleSelectableView r13) {
            if (r10 == true) goto L4;
        L13:
            super.jumpToLine(r8, r9, r10, r11, r12, r13);
            return;
        L4:
            if (r13 != this.selectedView) goto L13;
            if (r12 != r11) goto L13;
            if (this.movingHandleStart == false) goto L11;
            this.selectionStart = r8;
            return;
        L11:
            this.selectionEnd = r8;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ void jumpToLine(int r1, int r2, boolean r3, float r4, float r5, SelectableView r6) {
            jumpToLine(r1, r2, r3, r4, r5, (ArticleSelectableView) r6);
        }

        public void onNewViewSelected(ArticleSelectableView r6, ArticleSelectableView r7, int r8) {
            int r0 = getAdapterPosition(r7);
            if (r6 == null) goto L5;
            int r62 = getAdapterPosition(r6);
        L6:
            invalidate();
            int r3 = 0;
            if (this.movingDirectionSettling == false) goto L19;
            int r1 = this.startViewPosition;
            if (r1 != this.endViewPosition) goto L19;
            if (r0 == r1) goto L12;
            if (r0 >= r1) goto L17;
            this.startViewPosition = r0;
            this.startViewChildPosition = r8;
            pickStartView();
            this.movingHandleStart = true;
            int r63 = this.selectionEnd;
            this.startViewOffset = r63;
            this.selectionStart = r63 - 1;
        L44:
            this.arrayList.clear();
            r7.fillTextLayoutBlocks(this.arrayList);
            int r64 = this.arrayList.size();
            this.childCountByPosition.put(r0, r64);
        L45:
            if (r3 >= r64) goto L47;
            cacheLayoutBlock(r0, r3, this.arrayList.get(r3));
            r3 = r3 + 1;
            goto L45
        L47:
            return;
        L17:
            this.endViewPosition = r0;
            this.endViewChildPosition = r8;
            pickEndView();
            this.movingHandleStart = false;
            this.endViewOffset = 0;
            goto L44
        L12:
            if (r8 >= this.startViewChildPosition) goto L14;
            this.startViewChildPosition = r8;
            pickStartView();
            this.movingHandleStart = true;
            int r65 = this.selectionEnd;
            this.startViewOffset = r65;
            this.selectionStart = r65 - 1;
            goto L44
        L14:
            this.endViewChildPosition = r8;
            pickEndView();
            this.movingHandleStart = false;
            this.endViewOffset = 0;
        L19:
            if (this.movingHandleStart == false) goto L32;
            if (r0 != r62) goto L29;
            int r66 = this.endViewChildPosition;
            if (r8 > r66) goto L24;
        L27:
            this.startViewPosition = r0;
            this.startViewChildPosition = r8;
            pickStartView();
            this.startViewOffset = this.selectionEnd;
            goto L44
        L24:
            if (r0 < this.endViewPosition) goto L27;
            this.endViewPosition = r0;
            this.startViewChildPosition = r66;
            this.endViewChildPosition = r8;
            this.startViewOffset = this.endViewOffset;
            pickEndView();
            this.endViewOffset = 0;
            this.movingHandleStart = false;
            goto L44
        L29:
            if (r0 > this.endViewPosition) goto L31;
            this.startViewPosition = r0;
            this.startViewChildPosition = r8;
            pickStartView();
            this.startViewOffset = this.selectionEnd;
            goto L44
        L31:
            this.endViewPosition = r0;
            this.startViewChildPosition = this.endViewChildPosition;
            this.endViewChildPosition = r8;
            this.startViewOffset = this.endViewOffset;
            pickEndView();
            this.endViewOffset = 0;
            this.movingHandleStart = false;
            goto L44
        L32:
            if (r0 != r62) goto L41;
            int r67 = this.startViewChildPosition;
            if (r8 < r67) goto L36;
        L39:
            this.endViewPosition = r0;
            this.endViewChildPosition = r8;
            pickEndView();
            this.endViewOffset = 0;
            goto L44
        L36:
            if (r0 > this.startViewPosition) goto L39;
            this.startViewPosition = r0;
            this.endViewChildPosition = r67;
            this.startViewChildPosition = r8;
            this.endViewOffset = this.startViewOffset;
            pickStartView();
            this.movingHandleStart = true;
            this.startViewOffset = this.selectionEnd;
            goto L44
        L41:
            if (r0 < this.startViewPosition) goto L43;
            this.endViewPosition = r0;
            this.endViewChildPosition = r8;
            pickEndView();
            this.endViewOffset = 0;
            goto L44
        L43:
            this.startViewPosition = r0;
            this.endViewChildPosition = this.startViewChildPosition;
            this.startViewChildPosition = r8;
            this.endViewOffset = this.startViewOffset;
            pickStartView();
            this.movingHandleStart = true;
            this.startViewOffset = this.selectionEnd;
            goto L44
        L5:
            r62 = -1;
            goto L6
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void onOffsetChanged() {
            int r0 = getAdapterPosition((ArticleSelectableView) this.selectedView);
            if (this.startPeek == false) goto L5;
            int r1 = this.startViewChildPosition;
        L7:
            if (r0 != this.startViewPosition) goto L12;
            if (r1 != this.startViewChildPosition) goto L12;
            this.startViewOffset = this.selectionStart;
        L12:
            if (r0 == this.endViewPosition) goto L14;
            return;
        L14:
            if (r1 != this.endViewChildPosition) goto L18;
            this.endViewOffset = this.selectionEnd;
            return;
        L18:
            return;
        L5:
            r1 = this.endViewChildPosition;
            goto L7
        }

        public void onTextSelected(ArticleSelectableView r3, ArticleSelectableView r4) {
            int r42 = getAdapterPosition(r3);
            if (r42 < 0) goto L8;
            this.endViewPosition = r42;
            this.startViewPosition = r42;
            int r0 = this.maybeTextIndex;
            this.endViewChildPosition = r0;
            this.startViewChildPosition = r0;
            this.arrayList.clear();
            r3.fillTextLayoutBlocks(this.arrayList);
            int r32 = this.arrayList.size();
            this.childCountByPosition.put(r42, r32);
            int r02 = 0;
        L6:
            if (r02 >= r32) goto L10;
            cacheLayoutBlock(r42, r02, this.arrayList.get(r02));
            r02 = r02 + 1;
            goto L6
        L10:
            return;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ void onTextSelected(SelectableView r1, SelectableView r2) {
            onTextSelected((ArticleSelectableView) r1, (ArticleSelectableView) r2);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void pickEndView() {
            if (isInSelectionMode() == false) goto L36;
            this.startPeek = false;
            int r1 = this.endViewPosition;
            if (r1 < 0) goto L33;
            LinearLayoutManager r2 = this.layoutManager;
            if (r2 == null) goto L11;
            ArticleSelectableView r12 = (ArticleSelectableView) r2.findViewByPosition(r1);
        L14:
            if (r12 != null) goto L17;
            this.selectedView = null;
            return;
        L17:
            this.selectedView = r12;
            if (this.startViewPosition == this.endViewPosition) goto L21;
            this.selectionStart = 0;
        L24:
            this.selectionEnd = this.endViewOffset;
            CharSequence r0 = getText(r12, false);
            if (this.selectionEnd <= r0.length()) goto L27;
            this.selectionEnd = r0.length();
        L27:
            this.arrayList.clear();
            ((ArticleSelectableView) this.selectedView).fillTextLayoutBlocks(this.arrayList);
            int r02 = this.endViewChildPosition;
            if (r02 >= 0) goto L30;
            return;
        L30:
            if (r02 >= this.arrayList.size()) goto L35;
            this.textX = this.arrayList.get(this.endViewChildPosition).getX();
            this.textY = this.arrayList.get(this.endViewChildPosition).getY();
            return;
        L35:
            return;
        L21:
            if (this.startViewChildPosition == this.endViewChildPosition) goto L23;
            this.selectionStart = 0;
            goto L24
        L23:
            this.selectionStart = this.startViewOffset;
            goto L24
        L11:
            if (r1 >= this.parentView.getChildCount()) goto L13;
            r12 = (ArticleSelectableView) this.parentView.getChildAt(this.endViewPosition);
            goto L14
        L13:
            r12 = null;
            goto L14
        L33:
            return;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void pickStartView() {
            if (isInSelectionMode() == false) goto L33;
            this.startPeek = true;
            int r0 = this.startViewPosition;
            if (r0 < 0) goto L30;
            LinearLayoutManager r1 = this.layoutManager;
            if (r1 == null) goto L11;
            ArticleSelectableView r02 = (ArticleSelectableView) r1.findViewByPosition(r0);
        L14:
            if (r02 != null) goto L17;
            this.selectedView = null;
            return;
        L17:
            this.selectedView = r02;
            if (this.startViewPosition == this.endViewPosition) goto L21;
            this.selectionEnd = getText(r02, false).length();
        L24:
            this.selectionStart = this.startViewOffset;
            this.arrayList.clear();
            ((ArticleSelectableView) this.selectedView).fillTextLayoutBlocks(this.arrayList);
            int r03 = this.startViewChildPosition;
            if (r03 >= 0) goto L27;
            return;
        L27:
            if (r03 >= this.arrayList.size()) goto L32;
            this.textX = this.arrayList.get(this.startViewChildPosition).getX();
            this.textY = this.arrayList.get(this.startViewChildPosition).getY();
            return;
        L32:
            return;
        L21:
            if (this.startViewChildPosition == this.endViewChildPosition) goto L23;
            this.selectionEnd = getText(r02, false).length();
            goto L24
        L23:
            this.selectionEnd = this.endViewOffset;
            goto L24
        L11:
            if (this.endViewPosition >= this.parentView.getChildCount()) goto L13;
            r02 = (ArticleSelectableView) this.parentView.getChildAt(this.startViewPosition);
            goto L14
        L13:
            r02 = null;
            goto L14
        L30:
            return;
        }

        public boolean selectAllBlocksRange(int r5, int r6) {
            int r0 = 0;
            if (r5 < 0) goto L23;
            if (r6 < r5) goto L23;
            CharSequence r1 = this.textByPosition.get(r6);
            if (r1 != null) goto L9;
            int r12 = 0;
        L11:
            if (this.parentView != null) goto L13;
        L20:
            ArticleSelectableView r2 = null;
        L22:
            return selectAllBlocksRangeInternal(r5, r6, r12, r2);
        L13:
            if (r0 >= this.parentView.getChildCount()) goto L20;
            KeyEvent.Callback r22 = this.parentView.getChildAt(r0);
            if ((r22 instanceof ArticleSelectableView) == false) goto L19;
            r2 = (ArticleSelectableView) r22;
            if (getAdapterPosition(r2) == r6) goto L22;
        L19:
            r0 = r0 + 1;
            goto L13
        L9:
            r12 = r1.length();
        L23:
            return false;
        }

        public boolean selectAllBlocksRange(int r5, int r6, int r7, int r8) {
            if (r5 < 0) goto L26;
            if (r6 < r5) goto L26;
            if (r7 < 0) goto L26;
            if (this.parentView == null) goto L18;
            int r1 = 0;
        L11:
            if (r1 >= this.parentView.getChildCount()) goto L18;
            KeyEvent.Callback r2 = this.parentView.getChildAt(r1);
            if ((r2 instanceof ArticleSelectableView) == false) goto L17;
            ArticleSelectableView r22 = (ArticleSelectableView) r2;
            if (getAdapterPosition(r22) != r6) goto L17;
        L19:
            this.selectedView = r22;
            this.selectionStart = 0;
            this.selectionEnd = r8;
            this.startViewPosition = r5;
            this.endViewPosition = r6;
            this.startViewChildPosition = 0;
            this.endViewChildPosition = r7;
            this.startViewOffset = 0;
            this.endViewOffset = r8;
            SparseIntArray r82 = this.childCountByPosition;
            r82.put(r5, Math.max(1, r82.get(r5)));
            SparseIntArray r83 = this.childCountByPosition;
            r83.put(r6, Math.max(r7 + 1, r83.get(r6)));
            this.anchorViewPosition = r5;
            this.anchorChildPosition = 0;
            this.anchorOffset = 0;
            TextSelectionHelper<Cell>.TextSelectionOverlay r52 = this.textSelectionOverlay;
            if (r52 == null) goto L22;
            r52.setVisibility(0);
        L22:
            showHandleViews();
            invalidate();
            hideActions();
            AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.showActionsRunnable);
            Callback r4 = this.callback;
            if (r4 == null) goto L25;
            r4.onStateChanged(true);
        L25:
            return true;
        L17:
            r1 = r1 + 1;
        L18:
            r22 = null;
        L26:
            return false;
        }

        public boolean selectChildRange(ArticleSelectableView r3, int r4, int r5, int r6, int r7) {
            int r0 = getAdapterPosition(r3);
            if (r0 >= 0) goto L5;
            return false;
        L5:
            if (r4 != r6) goto L8;
            if (r5 != r7) goto L8;
            return false;
        L8:
            this.selectedView = r3;
            this.endViewPosition = r0;
            this.startViewPosition = r0;
            this.startViewChildPosition = r4;
            this.endViewChildPosition = r6;
            this.startViewOffset = r5;
            this.endViewOffset = r7;
            this.selectionStart = r5;
            this.selectionEnd = r7;
            this.anchorViewPosition = r0;
            this.anchorChildPosition = r4;
            this.anchorOffset = r5;
            populateTextCacheForView(r3, r0);
            this.arrayList.clear();
            r3.fillTextLayoutBlocks(this.arrayList);
            if (this.arrayList.isEmpty() == true) goto L15;
            if (r4 < 0) goto L15;
            if (r4 >= this.arrayList.size()) goto L15;
            this.textX = this.arrayList.get(r4).getX();
            this.textY = this.arrayList.get(r4).getY();
        L17:
            TextSelectionHelper<Cell>.TextSelectionOverlay r42 = this.textSelectionOverlay;
            if (r42 == null) goto L20;
            r42.setVisibility(0);
        L20:
            showHandleViews();
            invalidate();
            hideActions();
            AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.showActionsRunnable);
            Callback r2 = this.callback;
            if (r2 == null) goto L23;
            r2.onStateChanged(true);
        L23:
            r3.invalidate();
            return true;
        L15:
            if (this.arrayList.isEmpty() == true) goto L17;
            this.textX = this.arrayList.get(0).getX();
            this.textY = this.arrayList.get(0).getY();
            goto L17
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean selectLayout(int r7, int r8) {
            if (this.multiselect == true) goto L6;
            return false;
        L6:
            if (r8 > ((ArticleSelectableView) this.selectedView).getTop()) goto L8;
        L20:
            int r0 = this.parentView.getChildCount();
            int r3 = 0;
        L21:
            if (r3 >= r0) goto L34;
            if (isSelectable(this.parentView.getChildAt(r3)) == false) goto L33;
            ArticleSelectableView r4 = (ArticleSelectableView) this.parentView.getChildAt(r3);
            if (r8 <= r4.getTop()) goto L33;
            if (r8 >= r4.getBottom()) goto L33;
            int r72 = findClosestLayoutIndex((int) (r7 - r4.getX()), (int) (r8 - r4.getY()), r4);
            if (r72 < 0) goto L32;
            onNewViewSelected((ArticleSelectableView) this.selectedView, r4, r72);
            this.selectedView = r4;
            return true;
        L32:
            return false;
        L33:
            r3 = r3 + 1;
            goto L21
        L34:
            return false;
        L8:
            if (r8 >= ((ArticleSelectableView) this.selectedView).getBottom()) goto L20;
            if (this.startPeek == false) goto L13;
            int r02 = this.startViewChildPosition;
        L14:
            int r73 = findClosestLayoutIndex((int) (r7 - ((ArticleSelectableView) this.selectedView).getX()), (int) (r8 - ((ArticleSelectableView) this.selectedView).getY()), (ArticleSelectableView) this.selectedView);
            if (r73 == r02) goto L19;
            if (r73 < 0) goto L19;
            Cell r82 = this.selectedView;
            onNewViewSelected((ArticleSelectableView) r82, (ArticleSelectableView) r82, r73);
            return true;
        L19:
            return false;
        L13:
            r02 = this.endViewChildPosition;
            goto L14
        }

        public boolean selectRangeOf(ArticleSelectableView r2, int r3, int r4) {
            return selectRangeOf(r2, 0, r3, r4);
        }

        public boolean selectRangeOf(ArticleSelectableView r4, int r5, int r6, int r7) {
            int r0 = getAdapterPosition(r4);
            if (r0 < 0) goto L23;
            if (r6 == r7) goto L23;
            int r2 = Math.min(r6, r7);
            int r72 = Math.max(r6, r7);
            this.selectedView = r4;
            this.selectionStart = r2;
            this.selectionEnd = r72;
            this.endViewPosition = r0;
            this.startViewPosition = r0;
            this.endViewChildPosition = r5;
            this.startViewChildPosition = r5;
            this.startViewOffset = r2;
            this.endViewOffset = r72;
            this.anchorViewPosition = r0;
            this.anchorOffset = r6;
            this.anchorChildPosition = r5;
            populateTextCacheForView(r4, r0);
            if (this.arrayList.isEmpty() == true) goto L13;
            if (r5 < 0) goto L13;
            if (r5 >= this.arrayList.size()) goto L13;
            this.textX = this.arrayList.get(r5).getX();
            this.textY = this.arrayList.get(r5).getY();
        L15:
            TextSelectionHelper<Cell>.TextSelectionOverlay r52 = this.textSelectionOverlay;
            if (r52 == null) goto L18;
            r52.setVisibility(0);
        L18:
            showHandleViews();
            invalidate();
            hideActions();
            AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.showActionsRunnable);
            Callback r3 = this.callback;
            if (r3 == null) goto L21;
            r3.onStateChanged(true);
        L21:
            r4.invalidate();
            return true;
        L13:
            if (this.arrayList.isEmpty() == true) goto L15;
            this.textX = this.arrayList.get(0).getX();
            this.textY = this.arrayList.get(0).getY();
        L23:
            return false;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void setMaybeView(int r2, int r3, View r4) {
            if ((r4 instanceof ArticleSelectableView) == false) goto L10;
            this.capturedX = r2;
            this.capturedY = r3;
            ArticleSelectableView r42 = (ArticleSelectableView) r4;
            this.maybeSelectedView = r42;
            int r22 = findClosestLayoutIndex(r2, r3, r42);
            this.maybeTextIndex = r22;
            if (r22 >= 0) goto L8;
            this.maybeSelectedView = null;
            return;
        L8:
            this.maybeTextX = this.arrayList.get(r22).getX();
            this.maybeTextY = this.arrayList.get(this.maybeTextIndex).getY();
            return;
        }

        public void trySelect(View r1) {
            if (this.maybeSelectedView != null) goto L5;
            return;
        L5:
            if (this.capturedX >= 0) goto L7;
            return;
        L7:
            if (this.capturedY < 0) goto L12;
            this.startSelectionRunnable.run();
            return;
        }
    }

    public static class Callback {
        public Callback() {
        }

        public abstract void onStateChanged(boolean r1);

        public void onTextCopied() {
        }
    }

    public static class ChatListTextSelectionHelper extends TextSelectionHelper<ChatMessageCell> {
        public static int TYPE_CAPTION = 1;
        public static int TYPE_DESCRIPTION = 2;
        public static int TYPE_FACTCHECK = 3;
        public static int TYPE_MESSAGE = 0;
        public static int TYPE_RICH = 4;
        SparseArray<Animator> animatorSparseArray;
        public boolean isDescription;
        public boolean isFactCheck;
        public boolean isRich;
        private boolean maybeIsDescription;
        private boolean maybeIsFactCheck;
        private boolean maybeIsRich;

        /* renamed from: org.telegram.ui.Cells.TextSelectionHelper$ChatListTextSelectionHelper$1, reason: invalid class name */
        public class AnonymousClass1 extends AnimatorListenerAdapter {
            final /* synthetic */ ChatListTextSelectionHelper this$0;
            final /* synthetic */ ChatMessageCell val$cell;

            public AnonymousClass1(ChatListTextSelectionHelper r1, ChatMessageCell r2) {
                this.this$0 = r1;
                this.val$cell = r2;
            }

            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator r1) {
                this.val$cell.setSelectedBackgroundProgress(0.0f);
            }
        }

        /* renamed from: $r8$lambda$Gcwvj2Ry5WM_FcJtCCC-93mDpYk, reason: not valid java name */
        public static /* synthetic */ void m7101$r8$lambda$Gcwvj2Ry5WM_FcJtCCC93mDpYk(ChatMessageCell r1, int r2, ValueAnimator r3) {
            float r32 = ((Float) r3.getAnimatedValue()).floatValue();
            if (r1.getMessageObject() != null) goto L5;
            return;
        L5:
            if (r1.getMessageObject().getId() != r2) goto L9;
            r1.setSelectedBackgroundProgress(r32);
            return;
        }

        public static /* synthetic */ void $r8$lambda$IOJWew8cAG7I2Px4l6oTMSgA7kw(ChatListTextSelectionHelper r0, boolean r1, ValueAnimator r2) {
            r0.lambda$onTextSelected$0(r1, r2);
        }

        static {
        }

        public ChatListTextSelectionHelper() {
            this.animatorSparseArray = new SparseArray();
        }

        private void fillLayoutForCoords(int r18, int r19, ChatMessageCell r20, LayoutBlock r21, boolean r22) {
            if (r20 == null) goto L124;
            MessageObject r5 = r20.getMessageObject();
            int r7 = 0;
            if (r22 == false) goto L11;
            if (this.maybeIsDescription == true) goto L12;
        L14:
            if (r22 == false) goto L19;
            if (this.maybeIsFactCheck == true) goto L20;
        L22:
            if (r22 == false) goto L27;
            if (this.maybeIsRich == true) goto L29;
        L76:
            if (r20.hasCaptionLayout() == false) goto L99;
            MessageObject.TextLayoutBlocks r0 = r20.getCaptionLayout();
            int r52 = 0;
        L79:
            if (r52 >= r0.textLayoutBlocks.size()) goto L140;
            MessageObject.TextLayoutBlock r6 = r0.textLayoutBlocks.get(r52);
            float r9 = r19;
            if (r9 < r6.textYOffset(r0.textLayoutBlocks)) goto L98;
            if (r9 > ((r6.textYOffset(r0.textLayoutBlocks) + r6.padTop) + r6.height(r20.transitionParams))) goto L98;
            r21.layout = r6.textLayout;
            r21.yOffset = r6.textYOffset(r0.textLayoutBlocks) + r6.padTop;
            if (r6.quote == false) goto L87;
            int r2 = AndroidUtilities.dp(10.0f);
        L89:
            if (r6.isRtl() == false) goto L91;
            r7 = ((int) Math.ceil(r0.textXOffset)) - r2;
        L91:
            float r02 = -r7;
            r21.xOffset = r02;
            if (r6.code == true) goto L94;
        L96:
            r21.charOffset = r6.charactersOffset;
            return;
        L94:
            if (r6.quote == true) goto L96;
            r21.xOffset = r02 + AndroidUtilities.dp(8.0f);
            goto L96
        L87:
            r2 = 0;
        L98:
            r52 = r52 + 1;
            goto L79
        L140:
            return;
        L99:
            int r03 = 0;
        L101:
            if (r03 >= r5.textLayoutBlocks.size()) goto L141;
            MessageObject.TextLayoutBlock r92 = r5.textLayoutBlocks.get(r03);
            float r10 = r19;
            if (r10 < r92.textYOffset(r5.textLayoutBlocks)) goto L123;
            if (r10 > ((r92.textYOffset(r5.textLayoutBlocks) + r92.padTop) + r92.height(r20.transitionParams))) goto L123;
            r21.layout = r92.textLayout;
            r21.yOffset = r92.textYOffset(r5.textLayoutBlocks) + r92.padTop;
            if (r92.quote == false) goto L110;
            int r04 = AndroidUtilities.dp(10.0f);
        L114:
            if (r92.isRtl() == false) goto L116;
            r7 = ((int) Math.ceil(r5.textXOffset)) - r04;
        L116:
            float r05 = -r7;
            r21.xOffset = r05;
            if (r92.code == true) goto L119;
        L121:
            r21.charOffset = r92.charactersOffset;
            return;
        L119:
            if (r92.quote == true) goto L121;
            r21.xOffset = r05 + AndroidUtilities.dp(8.0f);
            goto L121
        L110:
            if (r92.code == false) goto L112;
            r04 = AndroidUtilities.dp(0.0f);
            goto L114
        L112:
            r04 = 0;
        L123:
            r03 = r03 + 1;
            goto L101
        L141:
            return;
        L29:
            if (r5 == null) goto L31;
            RichMessageLayout r3 = r5.richLayout;
        L32:
            if (r3 != null) goto L34;
        L73:
            r21.layout = null;
            return;
        L34:
            if (r3.textBlocks.isEmpty() == true) goto L73;
            int r06 = Integer.MAX_VALUE;
            int r53 = -1;
            int r102 = Integer.MAX_VALUE;
            int r8 = Integer.MIN_VALUE;
            int r93 = 0;
            int r62 = -1;
        L38:
            if (r93 >= r3.textBlocks.size()) goto L60;
            TextLayoutBlock r11 = r3.textBlocks.get(r93);
            int r12 = r11.getY();
            int r13 = r11.getLayout().getHeight() + r12;
            int r14 = r11.getX();
            int r15 = r11.getLayout().getWidth() + r14;
            boolean r16 = true;
            if (r19 < r12) goto L43;
            if (r19 >= r13) goto L43;
            boolean r122 = true;
        L44:
            if (r18 < r14) goto L47;
            if (r18 >= r15) goto L47;
        L48:
            r11.getLayout().getText();
            if (r122 == false) goto L59;
            if (r14 > r18) goto L54;
            if (r14 <= r8) goto L54;
            r53 = r93;
            r8 = r14;
        L54:
            if (r16 == false) goto L56;
            int r112 = 0;
        L57:
            if (r112 >= r102) goto L59;
            r62 = r93;
            r102 = r112;
            goto L59
        L56:
            r112 = Math.min(Math.abs(r18 - r14), Math.abs(r18 - r15));
        L59:
            r93 = r93 + 1;
        L47:
            r16 = false;
        L43:
            r122 = false;
            goto L44
        L60:
            if (r53 >= 0) goto L62;
            r53 = r62;
        L62:
            if (r53 >= 0) goto L71;
            int r1 = 0;
        L65:
            if (r7 >= r3.textBlocks.size()) goto L70;
            TextLayoutBlock r54 = r3.textBlocks.get(r7);
            int r63 = r54.getY();
            int r55 = Math.min(Math.abs(r19 - r63), Math.abs(r19 - (r54.getLayout().getHeight() + r63)));
            if (r55 >= r06) goto L69;
            r06 = r55;
            r1 = r7;
        L69:
            r7 = r7 + 1;
            goto L65
        L70:
            r53 = r1;
        L71:
            r21.layout = r3.textBlocks.get(r53).getLayout();
            r21.yOffset = r0.getY();
            r21.xOffset = r0.getX();
            r21.charOffset = r3.textBlockCharOffsets.get(r53).intValue();
            return;
        L31:
            r3 = null;
            goto L32
        L27:
            if (this.isRich == false) goto L76;
        L20:
            r21.layout = r20.getFactCheckLayout();
            r21.xOffset = 0.0f;
            r21.yOffset = 0.0f;
            r21.charOffset = 0;
            return;
        L19:
            if (this.isFactCheck == false) goto L22;
        L12:
            r21.layout = r20.getDescriptionlayout();
            r21.xOffset = 0.0f;
            r21.yOffset = 0.0f;
            r21.charOffset = 0;
            return;
        L11:
            if (this.isDescription == false) goto L14;
        }

        private /* synthetic */ void lambda$onTextSelected$0(boolean r1, ValueAnimator r2) {
            this.enterProgress = ((Float) r2.getAnimatedValue()).floatValue();
            TextSelectionHelper<Cell>.TextSelectionOverlay r22 = this.textSelectionOverlay;
            if (r22 == null) goto L5;
            r22.invalidate();
        L5:
            Cell r23 = this.selectedView;
            if (r23 != null) goto L8;
            return;
        L8:
            if (((ChatMessageCell) r23).getCurrentMessagesGroup() != null) goto L13;
            if (r1 == false) goto L14;
            ((ChatMessageCell) this.selectedView).setSelectedBackgroundProgress(1.0f - this.enterProgress);
            return;
        L14:
            return;
        }

        public void cancelAllAnimators() {
            int r0 = 0;
        L3:
            int r1 = this.animatorSparseArray.size();
            SparseArray<Animator> r2 = this.animatorSparseArray;
            if (r0 >= r1) goto L6;
            r2.get(r2.keyAt(r0)).cancel();
            r0 = r0 + 1;
            goto L3
        L6:
            r2.clear();
        }

        public void checkDataChanged(MessageObject r2) {
            int r0 = r2.messageOwner.edit_date;     // Catch: Exception -> L7
        L4:
            if (this.selectedCellId != r2.getId()) goto L10;
            clear(true);
            return;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void clear(boolean r1) {
            super.clear(r1);
            this.isDescription = false;
            this.isFactCheck = false;
            this.isRich = false;
        }

        public void draw(MessageObject r13, MessageObject.TextLayoutBlock r14, Canvas r15) {
            Cell r0 = this.selectedView;
            if (r0 != null) goto L5;
            return;
        L5:
            if (((ChatMessageCell) r0).getMessageObject() != null) goto L7;
            return;
        L7:
            if (this.isDescription == false) goto L9;
            return;
        L9:
            if (this.isFactCheck == false) goto L11;
            return;
        L11:
            if (this.isRich == true) goto L41;
            MessageObject r02 = ((ChatMessageCell) this.selectedView).getMessageObject();
            if (r02 != null) goto L16;
            return;
        L16:
            if (r02.textLayoutBlocks != null) goto L19;
            return;
        L19:
            if (r13.getId() != this.selectedCellId) goto L39;
            int r132 = this.selectionStart;
            int r1 = r14.charactersOffset;
            int r2 = this.selectionEnd - r1;
            int r3 = 0;
            int r7 = Utilities.clamp(r132 - r1, r14.textLayout.getText().length(), 0);
            int r8 = Utilities.clamp(r2, r14.textLayout.getText().length(), 0);
            if (r7 == r8) goto L40;
            boolean r133 = r02.isOutOwner();
            Paint r03 = this.selectionPaint;
            if (r133 == false) goto L25;
            int r134 = Theme.key_chat_outTextSelectionHighlight;
            r03.setColor(getThemedColor(r134));
            this.selectionHandlePaint.setColor(getThemedColor(r134));
        L27:
            if (r14.quote == false) goto L30;
            r3 = AndroidUtilities.dp(10.0f);
        L32:
            drawSelection(r15, r14.textLayout, r7, r8, true, true, r3);
            return;
        L30:
            if (r14.code == false) goto L32;
            r3 = AndroidUtilities.dp(0.0f);
            goto L32
        L25:
            int r135 = Theme.key_chat_inTextSelectionHighlight;
            r03.setColor(getThemedColor(r135));
            this.selectionHandlePaint.setColor(getThemedColor(r135));
            goto L27
        L40:
            return;
        L39:
            return;
        }

        public void drawCaption(MessageObject r13, MessageObject.TextLayoutBlock r14, Canvas r15) {
            if (r13 != null) goto L5;
            return;
        L5:
            if (this.isDescription == false) goto L7;
            return;
        L7:
            if (this.isFactCheck == false) goto L9;
            return;
        L9:
            if (this.isRich == true) goto L33;
            Cell r0 = this.selectedView;
            if (r0 != null) goto L13;
            return;
        L13:
            if (((ChatMessageCell) r0).getMessageObject() != null) goto L15;
            return;
        L15:
            if (((ChatMessageCell) this.selectedView).getMessageObject().getId() != r13.getId()) goto L38;
            int r02 = this.selectionStart;
            int r1 = r14.charactersOffset;
            int r2 = this.selectionEnd - r1;
            int r3 = 0;
            int r7 = Utilities.clamp(r02 - r1, r14.textLayout.getText().length(), 0);
            int r8 = Utilities.clamp(r2, r14.textLayout.getText().length(), 0);
            if (r7 == r8) goto L36;
            boolean r132 = r13.isOutOwner();
            Paint r03 = this.selectionPaint;
            if (r132 == false) goto L22;
            int r133 = Theme.key_chat_outTextSelectionHighlight;
            r03.setColor(getThemedColor(r133));
            this.selectionHandlePaint.setColor(getThemedColor(r133));
        L24:
            if (r14.quote == false) goto L27;
            r3 = AndroidUtilities.dp(10.0f);
        L29:
            drawSelection(r15, r14.textLayout, r7, r8, true, true, r3);
            return;
        L27:
            if (r14.code == false) goto L29;
            r3 = AndroidUtilities.dp(0.0f);
            goto L29
        L22:
            int r134 = Theme.key_chat_inTextSelectionHighlight;
            r03.setColor(getThemedColor(r134));
            this.selectionHandlePaint.setColor(getThemedColor(r134));
            goto L24
        L36:
            return;
        L38:
            return;
        }

        public void drawDescription(boolean r10, StaticLayout r11, Canvas r12) {
            if (this.isDescription == true) goto L5;
            return;
        L5:
            Paint r0 = this.selectionPaint;
            if (r10 == false) goto L8;
            int r102 = Theme.key_chat_outTextSelectionHighlight;
            r0.setColor(getThemedColor(r102));
            this.selectionHandlePaint.setColor(getThemedColor(r102));
        L9:
            drawSelection(r12, r11, this.selectionStart, this.selectionEnd, true, true, 0.0f);
            return;
        L8:
            int r103 = Theme.key_chat_inTextSelectionHighlight;
            r0.setColor(getThemedColor(r103));
            this.selectionHandlePaint.setColor(getThemedColor(r103));
            goto L9
        }

        public void drawFactCheck(boolean r10, StaticLayout r11, Canvas r12) {
            if (this.isFactCheck == true) goto L5;
            return;
        L5:
            Paint r0 = this.selectionPaint;
            if (r10 == false) goto L8;
            int r102 = Theme.key_chat_outTextSelectionHighlight;
            r0.setColor(getThemedColor(r102));
            this.selectionHandlePaint.setColor(getThemedColor(r102));
        L9:
            drawSelection(r12, r11, this.selectionStart, this.selectionEnd, true, true, 0.0f);
            return;
        L8:
            int r103 = Theme.key_chat_inTextSelectionHighlight;
            r0.setColor(getThemedColor(r103));
            this.selectionHandlePaint.setColor(getThemedColor(r103));
            goto L9
        }

        public void drawRich(MessageObject r11, RichMessageLayout r12, Canvas r13) {
            if (this.isRich == false) goto L38;
            if (r12 == null) goto L42;
            Cell r0 = this.selectedView;
            if (r0 != null) goto L8;
            return;
        L8:
            if (((ChatMessageCell) r0).getMessageObject() != null) goto L11;
            return;
        L11:
            if (((ChatMessageCell) this.selectedView).getMessageObject().getId() != r11.getId()) goto L45;
            boolean r112 = r11.isOutOwner();
            Paint r02 = this.selectionPaint;
            if (r112 == false) goto L16;
            int r113 = Theme.key_chat_outTextSelectionHighlight;
            r02.setColor(getThemedColor(r113));
            this.selectionHandlePaint.setColor(getThemedColor(r113));
        L17:
            int r03 = 0;
        L19:
            if (r03 >= r12.textBlocks.size()) goto L46;
            Layout r4 = r12.textBlocks.get(r03).getLayout();
            if (r4 != null) goto L23;
        L24:
            ChatListTextSelectionHelper r2 = this;
            Canvas r3 = r13;
        L37:
            r03 = r03 + 1;
            ChatListTextSelectionHelper this = r2;
            r13 = r3;
            goto L19
        L23:
            if (r4.getText() == null) goto L24;
            int r22 = r12.textBlockCharOffsets.get(r03).intValue();
            int r32 = r4.getText().length();
            int r5 = Utilities.clamp(this.selectionStart - r22, r32, 0);
            int r6 = Utilities.clamp(this.selectionEnd - r22, r32, 0);
            if (r5 == r6) goto L24;
            boolean r8 = true;
            if (this.selectionStart < r22) goto L31;
            boolean r7 = true;
        L33:
            if (this.selectionEnd <= (r22 + r32)) goto L36;
            r8 = false;
        L36:
            r13.save();
            r13.translate(r1.getX(), r1.getY());
            r2 = this;
            r3 = r13;
            r2.drawSelection(r3, r4, r5, r6, r7, r8, 0.0f);
            r3.restore();
            goto L37
        L31:
            r7 = false;
            goto L33
        L46:
            return;
        L16:
            int r114 = Theme.key_chat_inTextSelectionHighlight;
            r02.setColor(getThemedColor(r114));
            this.selectionHandlePaint.setColor(getThemedColor(r114));
            goto L17
        L45:
            return;
        L42:
            return;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void fillLayoutForOffset(int r8, LayoutBlock r9, boolean r10) {
            if (r10 == false) goto L5;
            Cell r102 = this.maybeSelectedView;
        L4:
            ChatMessageCell r103 = (ChatMessageCell) r102;
            if (r103 != null) goto L10;
            r9.layout = null;
            return;
        L10:
            MessageObject r1 = r103.getMessageObject();
            int r4 = 0;
            if (this.isDescription == false) goto L15;
            r9.layout = r103.getDescriptionlayout();
            r9.yOffset = 0.0f;
            r9.xOffset = 0.0f;
            r9.charOffset = 0;
            return;
        L15:
            if (this.isFactCheck == false) goto L19;
            r9.layout = r103.getFactCheckLayout();
            r9.yOffset = 0.0f;
            r9.xOffset = 0.0f;
            r9.charOffset = 0;
            return;
        L19:
            if (this.isRich == false) goto L43;
            if (r1 == null) goto L22;
            RichMessageLayout r7 = r1.richLayout;
        L23:
            if (r7 != null) goto L25;
        L40:
            r9.layout = null;
            return;
        L25:
            if (r7.textBlocks.isEmpty() == true) goto L40;
        L28:
            if (r4 >= r7.textBlocks.size()) goto L35;
            int r104 = r7.textBlockCharOffsets.get(r4).intValue();
            int r0 = r7.textBlocks.get(r4).getLayout().getText().length();
            if (r8 < r104) goto L34;
            if (r8 > (r104 + r0)) goto L34;
        L36:
            if (r4 >= 0) goto L38;
            r4 = r7.textBlocks.size() - 1;
        L38:
            r9.layout = r7.textBlocks.get(r4).getLayout();
            r9.yOffset = r8.getY();
            r9.xOffset = r8.getX();
            r9.charOffset = r7.textBlockCharOffsets.get(r4).intValue();
            return;
        L34:
            r4 = r4 + 1;
            goto L28
        L35:
            r4 = -1;
            goto L36
        L22:
            r7 = null;
            goto L23
        L43:
            if (r103.hasCaptionLayout() == false) goto L85;
            MessageObject.TextLayoutBlocks r72 = r103.getCaptionLayout();
            if (r72.textLayoutBlocks.size() != 1) goto L61;
            r9.layout = r72.textLayoutBlocks.get(0).textLayout;
            r9.yOffset = r8.padTop;
            MessageObject.TextLayoutBlock r82 = r72.textLayoutBlocks.get(0);
            if (r82.quote == false) goto L49;
            int r105 = AndroidUtilities.dp(10.0f);
        L51:
            if (r82.isRtl() == false) goto L53;
            int r73 = ((int) Math.ceil(r72.textXOffset)) - r105;
        L54:
            float r74 = -r73;
            r9.xOffset = r74;
            if (r82.code == true) goto L57;
        L59:
            r9.charOffset = 0;
            return;
        L57:
            if (r82.quote == true) goto L59;
            r9.xOffset = r74 + AndroidUtilities.dp(8.0f);
            goto L59
        L53:
            r73 = 0;
            goto L54
        L49:
            r105 = 0;
            goto L51
        L61:
            int r106 = 0;
        L63:
            if (r106 >= r72.textLayoutBlocks.size()) goto L83;
            MessageObject.TextLayoutBlock r12 = r72.textLayoutBlocks.get(r106);
            int r2 = r8 - r12.charactersOffset;
            if (r2 < 0) goto L82;
            if (r2 > r12.textLayout.getText().length()) goto L82;
            r9.layout = r12.textLayout;
            r9.yOffset = r12.textYOffset(r72.textLayoutBlocks) + r12.padTop;
            if (r12.quote == false) goto L71;
            int r83 = AndroidUtilities.dp(10.0f);
        L73:
            if (r12.isRtl() == false) goto L75;
            r4 = ((int) Math.ceil(r72.textXOffset)) - r83;
        L75:
            float r75 = -r4;
            r9.xOffset = r75;
            if (r12.code == true) goto L78;
        L80:
            r9.charOffset = r12.charactersOffset;
            return;
        L78:
            if (r12.quote == true) goto L80;
            r9.xOffset = r75 + AndroidUtilities.dp(8.0f);
            goto L80
        L71:
            r83 = 0;
        L82:
            r106 = r106 + 1;
            goto L63
        L83:
            r9.layout = null;
            return;
        L85:
            ArrayList<MessageObject.TextLayoutBlock> r76 = r1.textLayoutBlocks;
            if (r76 != null) goto L90;
            r9.layout = null;
            return;
        L90:
            if (r76.size() != 1) goto L106;
            r9.layout = r1.textLayoutBlocks.get(0).textLayout;
            r9.yOffset = r7.padTop;
            MessageObject.TextLayoutBlock r77 = r1.textLayoutBlocks.get(0);
            if (r77.quote == false) goto L94;
            int r84 = AndroidUtilities.dp(10.0f);
        L96:
            if (r77.isRtl() == false) goto L98;
            int r107 = ((int) Math.ceil(r1.textXOffset)) - r84;
        L99:
            float r85 = -r107;
            r9.xOffset = r85;
            if (r77.code == true) goto L102;
        L104:
            r9.charOffset = 0;
            return;
        L102:
            if (r77.quote == true) goto L104;
            r9.xOffset = r85 + AndroidUtilities.dp(8.0f);
            goto L104
        L98:
            r107 = 0;
            goto L99
        L94:
            r84 = 0;
            goto L96
        L106:
            int r78 = 0;
        L108:
            if (r78 >= r1.textLayoutBlocks.size()) goto L128;
            MessageObject.TextLayoutBlock r108 = r1.textLayoutBlocks.get(r78);
            int r22 = r8 - r108.charactersOffset;
            if (r22 < 0) goto L127;
            if (r22 > r108.textLayout.getText().length()) goto L127;
            r9.layout = r108.textLayout;
            r9.yOffset = r108.textYOffset(r1.textLayoutBlocks) + r108.padTop;
            if (r108.quote == false) goto L116;
            int r79 = AndroidUtilities.dp(10.0f);
        L118:
            if (r108.isRtl() == false) goto L120;
            r4 = ((int) Math.ceil(r1.textXOffset)) - r79;
        L120:
            float r710 = -r4;
            r9.xOffset = r710;
            if (r108.code == true) goto L123;
        L125:
            r9.charOffset = r108.charactersOffset;
            return;
        L123:
            if (r108.quote == true) goto L125;
            r9.xOffset = r710 + AndroidUtilities.dp(8.0f);
            goto L125
        L116:
            r79 = 0;
        L127:
            r78 = r78 + 1;
            goto L108
        L128:
            r9.layout = null;
            return;
        L5:
            r102 = this.selectedView;
            goto L4
        }

        public int getCharOffsetFromCord(int r6, int r7, int r8, int r9, ChatMessageCell r10, boolean r11) {
            int r0 = 0;
            if (r10 != null) goto L5;
            return 0;
        L5:
            int r62 = r6 - r8;
            int r72 = r7 - r9;
            if (r11 == false) goto L8;
            boolean r82 = this.maybeIsDescription;
        L9:
            if (r11 == false) goto L11;
            boolean r92 = this.maybeIsFactCheck;
        L12:
            if (r11 == false) goto L14;
            boolean r1 = this.maybeIsRich;
        L15:
            float r4 = 0.0f;
            if (r82 == false) goto L18;
            StaticLayout r83 = r10.getDescriptionlayout();
        L40:
            if (r83 != null) goto L42;
            return -1;
        L42:
            if (r72 >= 0) goto L44;
            r72 = 1;
        L44:
            int r84 = (int) (r4 + r83.getLineBottom(r83.getLineCount() - 1));
            if (r72 <= r84) goto L47;
            r72 = r84 - 1;
        L47:
            fillLayoutForCoords(r62, r72, r10, this.layoutBlock, r11);
            LayoutBlock r85 = this.layoutBlock;
            Layout r93 = r85.layout;
            if (r93 != null) goto L50;
            return -1;
        L50:
            int r63 = (int) (r62 - r85.xOffset);
        L52:
            if (r0 >= r93.getLineCount()) goto L59;
            float r86 = r72;
            if (r86 <= (this.layoutBlock.yOffset + r93.getLineTop(r0))) goto L58;
            if (r86 >= (this.layoutBlock.yOffset + r93.getLineBottom(r0))) goto L58;
        L60:
            if (r0 >= 0) goto L62;
            return -1;
        L62:
            return this.layoutBlock.charOffset + r93.getOffsetForHorizontal(r0, r63);
        L58:
            r0 = r0 + 1;
            goto L52
        L59:
            r0 = -1;
            goto L60
        L18:
            if (r92 == false) goto L20;
            r83 = r10.getFactCheckLayout();
            goto L40
        L20:
            if (r1 == false) goto L36;
            StaticLayout r94 = null;
            if (r10.getMessageObject() == null) goto L24;
            RichMessageLayout r87 = r10.getMessageObject().richLayout;
        L25:
            if (r87 != null) goto L27;
        L34:
            return -1;
        L27:
            if (r87.textBlocks.isEmpty() == true) goto L34;
            ArrayList<TextLayoutBlock> r88 = r87.textBlocks;
            TextLayoutBlock r89 = r88.get(r88.size() - 1);
            Layout r12 = r89.getLayout();
            if ((r12 instanceof StaticLayout) == false) goto L32;
            r94 = (StaticLayout) r12;
        L32:
            r4 = r89.getY();
        L33:
            r83 = r94;
            goto L40
        L24:
            r87 = null;
            goto L25
        L36:
            if (r10.hasCaptionLayout() == false) goto L39;
            MessageObject.TextLayoutBlock r810 = r10.getCaptionLayout().textLayoutBlocks.get(r10.getCaptionLayout().textLayoutBlocks.size() - 1);
            r94 = r810.textLayout;
            float r13 = r810.textYOffset(r10.getCaptionLayout().textLayoutBlocks);
            int r811 = r810.padTop;
        L38:
            r4 = r13 + r811;
            goto L33
        L39:
            MessageObject.TextLayoutBlock r812 = r10.getMessageObject().textLayoutBlocks.get(r10.getMessageObject().textLayoutBlocks.size() - 1);
            r94 = r812.textLayout;
            r13 = r812.textYOffset(r10.getMessageObject().textLayoutBlocks);
            r811 = r812.padTop;
            goto L38
        L14:
            r1 = this.isRich;
            goto L15
        L11:
            r92 = this.isFactCheck;
            goto L12
        L8:
            r82 = this.isDescription;
            goto L9
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ int getCharOffsetFromCord(int r1, int r2, int r3, int r4, SelectableView r5, boolean r6) {
            return getCharOffsetFromCord(r1, r2, r3, r4, (ChatMessageCell) r5, r6);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getLineHeight() {
            Cell r0 = this.selectedView;
            if (r0 != null) goto L5;
        L32:
            return 0;
        L5:
            if (((ChatMessageCell) r0).getMessageObject() == null) goto L32;
            MessageObject r02 = ((ChatMessageCell) this.selectedView).getMessageObject();
            if (this.isDescription == false) goto L10;
            Layout r4 = ((ChatMessageCell) this.selectedView).getDescriptionlayout();
        L28:
            if (r4 != null) goto L31;
            return 0;
        L31:
            return r4.getLineBottom(0) - r4.getLineTop(0);
        L10:
            if (this.isFactCheck == false) goto L13;
            r4 = ((ChatMessageCell) this.selectedView).getFactCheckLayout();
            goto L28
        L13:
            if (this.isRich == false) goto L22;
            if (r02 == null) goto L16;
            RichMessageLayout r42 = r02.richLayout;
        L17:
            if (r42 != null) goto L19;
        L27:
            r4 = null;
            goto L28
        L19:
            if (r42.textBlocks.isEmpty() == true) goto L27;
            r4 = r42.textBlocks.get(0).getLayout();
            goto L28
        L16:
            r42 = null;
            goto L17
        L22:
            if (((ChatMessageCell) this.selectedView).hasCaptionLayout() == false) goto L24;
            r4 = ((ChatMessageCell) this.selectedView).getCaptionLayout().textLayoutBlocks.get(0).textLayout;
            goto L28
        L24:
            ArrayList<MessageObject.TextLayoutBlock> r43 = r02.textLayoutBlocks;
            if (r43 == null) goto L27;
            r4 = r43.get(0).textLayout;
            goto L28
        }

        public CharSequence getText(ChatMessageCell r2, boolean r3) {
            if (r2 != null) goto L4;
            return null;
        L4:
            if (r2.getMessageObject() == null) goto L42;
            if (r3 == false) goto L11;
            if (this.maybeIsDescription == true) goto L13;
        L14:
            if (r3 == false) goto L19;
            if (this.maybeIsFactCheck == true) goto L21;
        L22:
            if (r3 == false) goto L27;
            if (this.maybeIsRich == false) goto L35;
        L28:
            RichMessageLayout r1 = r2.getMessageObject().richLayout;
            if (r1 != null) goto L31;
            return _UrlKt.FRAGMENT_ENCODE_SET;
        L31:
            return r1.joinedText;
        L35:
            if (r2.hasCaptionLayout() == false) goto L39;
            return r2.getCaptionLayout().text;
        L39:
            return r2.getMessageObject().messageText;
        L27:
            if (this.isRich == false) goto L35;
        L21:
            return r2.getFactCheckLayout().getText();
        L19:
            if (this.isFactCheck == false) goto L22;
        L13:
            return r2.getDescriptionlayout().getText();
        L11:
            if (this.isDescription == false) goto L14;
        L42:
            return null;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ CharSequence getText(SelectableView r1, boolean r2) {
            return getText((ChatMessageCell) r1, r2);
        }

        public int getTextSelectionType(ChatMessageCell r2) {
            if (this.isDescription == false) goto L7;
            return TYPE_DESCRIPTION;
        L7:
            if (this.isFactCheck == false) goto L11;
            return TYPE_FACTCHECK;
        L11:
            if (this.isRich == false) goto L15;
            return TYPE_RICH;
        L15:
            if (r2.hasCaptionLayout() == false) goto L19;
            return TYPE_CAPTION;
        L19:
            return TYPE_MESSAGE;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void invalidate() {
            super.invalidate();
            Cell r0 = this.selectedView;
            if (r0 != null) goto L5;
        L7:
            Cell r02 = this.selectedView;
            if (r02 != null) goto L10;
            return;
        L10:
            if (this.isFactCheck == false) goto L12;
        L13:
            ((ChatMessageCell) r02).invalidateOutbounds();
            return;
        L12:
            if (this.maybeIsFactCheck == true) goto L13;
            return;
        L5:
            if (((ChatMessageCell) r0).getCurrentMessagesGroup() == null) goto L7;
            this.parentView.invalidate();
            goto L7
        }

        public boolean isMenuEmpty() {
            if (canCopy() == false) goto L5;
            return false;
        L5:
            if (canShowQuote() == true) goto L10;
            return true;
        L10:
            return false;
        }

        public boolean isTouched() {
            return this.movingHandle;
        }

        public void onChatMessageCellAttached(ChatMessageCell r3) {
            if (r3.getMessageObject() != null) goto L5;
            return;
        L5:
            if (r3.getMessageObject().getId() != this.selectedCellId) goto L9;
            this.selectedView = r3;
            return;
        }

        public void onChatMessageCellDetached(ChatMessageCell r2) {
            if (r2.getMessageObject() != null) goto L5;
            return;
        L5:
            if (r2.getMessageObject().getId() != this.selectedCellId) goto L9;
            this.selectedView = null;
            return;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public boolean onCopyOverride() {
            if (this.isRich == false) goto L29;
            Cell r0 = this.selectedView;
            if (r0 == null) goto L29;
            if (((ChatMessageCell) r0).getMessageObject() == null) goto L29;
            RichMessageLayout r02 = ((ChatMessageCell) this.selectedView).getMessageObject().richLayout;
            if (r02 == null) goto L29;
            if (r02.textBlocks.isEmpty() == true) goto L29;
            CharSequence r2 = getSelectedText();
            if (r2 == null) goto L29;
            if (r2.length() == 0) goto L29;
            String r4 = r02.getSelectionHtml(this.selectionStart, this.selectionEnd);     // Catch: Exception -> L21
        L23:
            if (r4 == null) goto L29;
            if (r4.length() == 0) goto L29;
            AndroidUtilities.addToClipboard(r2, r4);
            return true;
        L21:
            e = move-exception;
            FileLog.e(e);
            r4 = null;
        L29:
            return false;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void onExitSelectionMode(boolean r5) {
            Cell r0 = this.selectedView;
            if (r0 != null) goto L5;
            return;
        L5:
            if (((ChatMessageCell) r0).isDrawingSelectionBackground() == false) goto L13;
            if (r5 == true) goto L14;
            Cell r52 = this.selectedView;
            ChatMessageCell r02 = (ChatMessageCell) r52;
            int r53 = ((ChatMessageCell) r52).getMessageObject().getId();
            Animator r1 = this.animatorSparseArray.get(r53);
            if (r1 == null) goto L10;
            r1.removeAllListeners();
            r1.cancel();
        L10:
            r02.setSelectedBackgroundProgress(0.01f);
            ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.01f, 1.0f});
            r12.addUpdateListener(new TextSelectionHelper$ChatListTextSelectionHelper$$ExternalSyntheticLambda0(r02, r53));
            r12.addListener(new AnonymousClass1(this, r02));
            r12.setDuration(300);
            r12.start();
            this.animatorSparseArray.put(r53, r12);
            return;
        L14:
            return;
        }

        public void onTextSelected(ChatMessageCell r5, ChatMessageCell r6) {
            if (r6 != null) goto L4;
        L9:
            boolean r62 = true;
        L10:
            this.selectedCellId = r5.getMessageObject().getId();
            this.selectedCellEditDate = Integer.valueOf(r5.getMessageObject().messageOwner.edit_date);     // Catch: Exception -> L13
        L14:
            this.enterProgress = 0.0f;
            this.isDescription = this.maybeIsDescription;
            this.isFactCheck = this.maybeIsFactCheck;
            this.isRich = this.maybeIsRich;
            Animator r1 = this.animatorSparseArray.get(this.selectedCellId);
            if (r1 == null) goto L17;
            r1.removeAllListeners();
            r1.cancel();
        L17:
            ValueAnimator r12 = ValueAnimator.ofFloat(new float[]{0.0f, 1.0f});
            r12.addUpdateListener(new TextSelectionHelper$ChatListTextSelectionHelper$$ExternalSyntheticLambda1(this, r62));
            r12.setDuration(250);
            r12.start();
            this.animatorSparseArray.put(this.selectedCellId, r12);
            if (r62 == true) goto L20;
            r5.setSelectedBackgroundProgress(0.0f);
        L20:
            SharedConfig.removeTextSelectionHint();
            return;
        L13:
            this.selectedCellEditDate = null;
            goto L14
        L4:
            if (r6.getMessageObject() != null) goto L6;
        L8:
            r62 = false;
            goto L10
        L6:
            if (r6.getMessageObject().getId() == r5.getMessageObject().getId()) goto L8;
            goto L8
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ void onTextSelected(SelectableView r1, SelectableView r2) {
            onTextSelected((ChatMessageCell) r1, (ChatMessageCell) r2);
        }

        public void select(ChatMessageCell r1, int r2, int r3) {
            if (r1 != null) goto L4;
            return;
        L4:
            this.selectedView = r1;
            this.selectedCellId = r1.getMessageObject().getId();
            this.selectionStart = r2;
            this.selectionEnd = r3;
            invalidate();
            Callback r12 = this.callback;
            if (r12 == null) goto L7;
            r12.onStateChanged(true);
        L7:
            this.movingOffsetY = 0.0f;
            this.movingOffsetX = 0.0f;
            onOffsetChanged();
            this.allowDiscard = false;
            TextSelectionHelper<Cell>.TextSelectionOverlay r22 = this.textSelectionOverlay;
            if (r22 == null) goto L10;
            r22.setVisibility(0);
        L10:
            showHandleViews();
        }

        public void setIsDescription(boolean r1) {
            this.maybeIsDescription = r1;
        }

        public void setIsFactCheck(boolean r1) {
            this.maybeIsFactCheck = r1;
        }

        public void setIsRich(boolean r1) {
            this.maybeIsRich = r1;
        }

        public void setMessageObject(ChatMessageCell r7) {
            this.maybeSelectedView = r7;
            MessageObject r0 = r7.getMessageObject();
            if (this.maybeIsDescription == false) goto L9;
            if (r7.getDescriptionlayout() == null) goto L9;
            Rect r02 = this.textArea;
            int r1 = this.maybeTextX;
            r02.set(r1, this.maybeTextY, r7.getDescriptionlayout().getWidth() + r1, this.maybeTextY + r7.getDescriptionlayout().getHeight());
            return;
        L9:
            if (this.maybeIsFactCheck == false) goto L15;
            if (r7.getFactCheckLayout() == null) goto L15;
            Rect r03 = this.textArea;
            int r12 = this.maybeTextX;
            r03.set(r12, this.maybeTextY, r7.getFactCheckLayout().getWidth() + r12, this.maybeTextY + r7.getFactCheckLayout().getHeight());
            return;
        L15:
            if (this.maybeIsRich == false) goto L24;
            if (r0 == null) goto L24;
            RichMessageLayout r13 = r0.richLayout;
            if (r13 == null) goto L24;
            if (r13.textBlocks.isEmpty() == true) goto L24;
            RichMessageLayout r72 = r0.richLayout;
            Rect r04 = this.textArea;
            int r14 = this.maybeTextX;
            r04.set(r14, this.maybeTextY, r72.getMinWidth() + r14, this.maybeTextY + r72.getHeight());
            return;
        L24:
            if (r7.hasCaptionLayout() == true) goto L26;
        L29:
            if (r0 == null) goto L36;
            ArrayList<MessageObject.TextLayoutBlock> r15 = r0.textLayoutBlocks;
            if (r15 == null) goto L36;
            if (r15.size() <= 0) goto L36;
            MessageObject.TextLayoutBlock r16 = r0.textLayoutBlocks.get(r1.size() - 1);
            Rect r2 = this.textArea;
            int r3 = this.maybeTextX;
            r2.set(r3, this.maybeTextY, r16.textLayout.getWidth() + r3, (int) (((this.maybeTextY + r16.textYOffset(r0.textLayoutBlocks, r7.transitionParams)) + r16.padTop) + r16.textLayout.getHeight()));
            return;
        L36:
            this.maybeSelectedView = null;
            return;
        L26:
            if (r7.getCaptionLayout().textLayoutBlocks.size() <= 0) goto L29;
            MessageObject.TextLayoutBlock r05 = r7.getCaptionLayout().textLayoutBlocks.get(r0.size() - 1);
            Rect r17 = this.textArea;
            int r22 = this.maybeTextX;
            r17.set(r22, this.maybeTextY, r05.textLayout.getWidth() + r22, (int) (((this.maybeTextY + r05.textYOffset(r7.getCaptionLayout().textLayoutBlocks, r7.transitionParams)) + r05.padTop) + r05.textLayout.getHeight()));
        }

        public void updateTextPosition(int r2, int r3) {
            if (this.textX == r2) goto L5;
        L8:
            this.textX = r2;
            this.textY = r3;
            invalidate();
            return;
        L5:
            if (this.textY != r3) goto L8;
        }
    }

    public static class IgnoreCopySpannable {
        public IgnoreCopySpannable() {
        }
    }

    public static class LayoutBlock {
        public int charOffset;
        public Layout layout;
        public Rect selectionBounds;
        public float xOffset;
        public float yOffset;

        public LayoutBlock() {
        }
    }

    public interface OnAiGenerateListener {
        void run(CharSequence r1);
    }

    public interface OnTranslateListener {
        void run(CharSequence r1, String r2, String r3, Runnable r4);
    }

    public static class PathCopyTo extends Path {
        private Path destination;

        public PathCopyTo(Path r1) {
            this.destination = r1;
        }

        @Override // android.graphics.Path
        public void addRect(float r1, float r2, float r3, float r4, Path.Direction r5) {
            this.destination.addRect(r1, r2, r3, r4, r5);
        }

        @Override // android.graphics.Path
        public void reset() {
            super.reset();
        }
    }

    public static class PathWithSavedBottom extends Path {
        float lastBottom;

        private PathWithSavedBottom() {
            this.lastBottom = 0.0f;
        }

        public /* synthetic */ PathWithSavedBottom(TextSelectionHelperIA r1) {
            this();
        }

        @Override // android.graphics.Path
        public void addRect(float r1, float r2, float r3, float r4, Path.Direction r5) {
            super.addRect(r1, r2, r3, r4, r5);
            if (r4 <= this.lastBottom) goto L6;
            this.lastBottom = r4;
            return;
        }

        @Override // android.graphics.Path
        public void reset() {
            super.reset();
            this.lastBottom = 0.0f;
        }
    }

    public static class ReplaceCopyTextSpannable {
        public final CharSequence replacement;

        public ReplaceCopyTextSpannable(CharSequence r1) {
            this.replacement = r1;
        }
    }

    public static class ScalablePath extends Path {
        private static ArrayList<RectF> recycled;
        float lastBottom;
        private ArrayList<RectF> rects;
        private int rectsCount;

        /* renamed from: -$$Nest$fgetrects, reason: not valid java name */
        public static /* bridge */ /* synthetic */ ArrayList m7102$$Nest$fgetrects(ScalablePath r0) {
            return r0.rects;
        }

        /* renamed from: -$$Nest$fgetrectsCount, reason: not valid java name */
        public static /* bridge */ /* synthetic */ int m7103$$Nest$fgetrectsCount(ScalablePath r0) {
            return r0.rectsCount;
        }

        private ScalablePath() {
            this.lastBottom = 0.0f;
            this.rects = new ArrayList(1);
            this.rectsCount = 0;
        }

        public /* synthetic */ ScalablePath(TextSelectionHelperIA r1) {
            this();
        }

        @Override // android.graphics.Path
        public void addRect(float r3, float r4, float r5, float r6, Path.Direction r7) {
            ArrayList<RectF> r0 = recycled;
            if (r0 != null) goto L5;
        L7:
            RectF r02 = new RectF();
        L8:
            r02.set(r3, r4, r5, r6);
            this.rects.add(r02);
            this.rectsCount++;
            super.addRect(r3, r4, r5, r6, r7);
            if (r6 <= this.lastBottom) goto L12;
            this.lastBottom = r6;
            return;
        L12:
            return;
        L5:
            if (r0.size() <= 0) goto L7;
            r02 = recycled.remove(0);
            goto L8
        }

        @Override // android.graphics.Path
        public void reset() {
            super.reset();
            if (recycled != null) goto L5;
            recycled = new ArrayList(this.rects.size());
        L5:
            recycled.addAll(this.rects);
            this.rects.clear();
            this.rectsCount = 0;
            this.lastBottom = 0.0f;
        }
    }

    /* loaded from: classes3.dex */
    public interface SelectableView {
        int getBottom();

        int getMeasuredWidth();

        int getTop();

        float getX();

        float getY();

        void invalidate();
    }

    /* loaded from: classes3.dex */
    public interface SimpleSelectabeleView extends SelectableView {
        Layout getStaticTextLayout();

        CharSequence getText();
    }

    public static class SimpleTextSelectionHelper extends TextSelectionHelper<SimpleSelectabeleView> {
        SimpleSelectabeleView selectabeleView;

        public SimpleTextSelectionHelper(SimpleSelectabeleView r1, Theme.ResourcesProvider r2) {
            this.selectabeleView = r1;
            this.resourcesProvider = r2;
        }

        public void draw(Canvas r10) {
            Layout r3 = this.selectabeleView.getStaticTextLayout();
            int r0 = Theme.getColor(Theme.key_chat_textSelectBackground, this.resourcesProvider);
            this.selectionPaint.setColor(r0);
            this.selectionHandlePaint.setColor(r0);
            drawSelection(r10, r3, this.selectionStart, this.selectionEnd, true, true, 0.0f);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public void fillLayoutForOffset(int r1, LayoutBlock r2, boolean r3) {
            r2.layout = this.selectabeleView.getStaticTextLayout();
            r2.yOffset = 0.0f;
            r2.xOffset = 0.0f;
            r2.charOffset = 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ int getCharOffsetFromCord(int r1, int r2, int r3, int r4, SelectableView r5, boolean r6) {
            return getCharOffsetFromCord(r1, r2, r3, r4, (SimpleSelectabeleView) r5, r6);
        }

        public int getCharOffsetFromCord(int r3, int r4, int r5, int r6, SimpleSelectabeleView r7, boolean r8) {
            if (r4 >= 0) goto L5;
            r4 = 1;
        L5:
            Layout r72 = r7.getStaticTextLayout();
            if (r4 <= (r72.getLineBottom(r72.getLineCount() - 1) + 0.0f)) goto L8;
            r4 = (int) ((r72.getLineBottom(r72.getLineCount() - 1) + 0.0f) - 1.0f);
        L8:
            LayoutBlock r52 = this.layoutBlock;
            Layout r73 = r52.layout;
            if (r73 != null) goto L11;
            return -1;
        L11:
            int r32 = (int) (r3 - r52.xOffset);
            int r53 = 0;
        L13:
            if (r53 >= r73.getLineCount()) goto L20;
            if (r4 <= (r73.getLineTop(r53) + r6)) goto L19;
            if (r4 >= (r73.getLineBottom(r53) + r6)) goto L19;
        L21:
            if (r53 >= 0) goto L28;
        L27:
            return -1;
        L28:
            return this.layoutBlock.charOffset + r73.getOffsetForHorizontal(r53, r32);
        L25:
            e = move-exception;
            FileLog.e(e);
        L19:
            r53 = r53 + 1;
            goto L13
        L20:
            r53 = -1;
            goto L21
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public int getLineHeight() {
            Layout r2 = this.selectabeleView.getStaticTextLayout();
            return r2.getLineBottom(0) - r2.getLineTop(0);
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ CharSequence getText(SelectableView r1, boolean r2) {
            return getText((SimpleSelectabeleView) r1, r2);
        }

        public CharSequence getText(SimpleSelectabeleView r1, boolean r2) {
            return r1.getText();
        }

        public boolean isCurrent(SimpleSelectabeleView r1) {
            if (this.selectabeleView != r1) goto L6;
            return true;
        L6:
            return false;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper
        public /* bridge */ /* synthetic */ void onTextSelected(SelectableView r1, SelectableView r2) {
            onTextSelected((SimpleSelectabeleView) r1, (SimpleSelectabeleView) r2);
        }

        public void onTextSelected(SimpleSelectabeleView r1, SimpleSelectabeleView r2) {
        }

        public void update(float r6, float r7) {
            Layout r0 = this.selectabeleView.getStaticTextLayout();
            if (r0 != null) goto L6;
            this.textArea.setEmpty();
            this.maybeSelectedView = null;
            return;
        L6:
            this.maybeSelectedView = this.selectabeleView;
            int r1 = (int) r6;
            this.maybeTextX = r1;
            int r2 = (int) r7;
            this.maybeTextY = r2;
            LayoutBlock r3 = this.layoutBlock;
            r3.layout = r0;
            r3.xOffset = r6;
            r3.yOffset = r7;
            r3.charOffset = 0;
            this.textArea.set(r1, r2, (int) (r6 + r0.getWidth()), (int) (r7 + r0.getHeight()));
        }
    }

    public interface TextLayoutBlock {
        Layout getLayout();

        default CharSequence getPrefix() {
            return null;
        }

        int getRow();

        default Rect getSelectionBounds() {
            return null;
        }

        default CharSequence getText() {
            Layout r0 = getLayout();
            if (r0 != null) goto L7;
            return null;
        L7:
            return r0.getText();
        }

        int getX();

        int getY();
    }

    public class TextSelectionOverlay extends View {
        boolean applyPaddingAsOffset;
        float cancelPressedX;
        float cancelPressedY;
        private final ArrayList<Rect> gestureExclusionRects;
        Paint handleViewPaint;
        Path path;
        long pressedTime;
        float pressedX;
        float pressedY;
        final /* synthetic */ TextSelectionHelper this$0;

        public TextSelectionOverlay(TextSelectionHelper r1, Context r2) {
            this.this$0 = r1;
            super(r2);
            this.handleViewPaint = new Paint(1);
            this.pressedTime = 0;
            this.path = new Path();
            this.gestureExclusionRects = new ArrayList();
            this.handleViewPaint.setStyle(Paint.Style.FILL);
        }

        private void addGestureExclusionRect(RectF r7) {
            if (r7.isEmpty() == false) goto L5;
            return;
        L5:
            this.gestureExclusionRects.add(new Rect((int) Math.floor(r7.left), (int) Math.floor(r7.top), (int) Math.ceil(r7.right), (int) Math.ceil(r7.bottom)));
        }

        private int paddingX() {
            if (this.applyPaddingAsOffset == true) goto L5;
            return 0;
        L5:
            return getPaddingLeft();
        }

        private int paddingY() {
            if (this.applyPaddingAsOffset == true) goto L5;
            return 0;
        L5:
            return getPaddingTop();
        }

        private void requestParentDisallowIntercept(boolean r1) {
            ViewParent r0 = getParent();
            if (r0 == null) goto L6;
            r0.requestDisallowInterceptTouchEvent(r1);
            return;
        }

        private void updateGestureExclusionRects() {
            if (Build.VERSION.SDK_INT >= 29) goto L5;
            return;
        L5:
            this.gestureExclusionRects.clear();
            addGestureExclusionRect(TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0));
            addGestureExclusionRect(TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0));
            setSystemGestureExclusionRects(this.gestureExclusionRects);
        }

        public void checkCancel(float r1, float r2, boolean r3) {
            if (r3 == true) goto L4;
            int r12 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0)[1];
            int r13 = this.this$0.textY;
        L4:
            TextSelectionHelper r0 = this.this$0;
            if (r0.movingHandle == false) goto L7;
            return;
        L7:
            if (r0.allowDiscard == false) goto L11;
            r0.clear();
            return;
        }

        public void checkCancelAction(MotionEvent r4) {
            if (r4.getAction() != 0) goto L7;
            this.cancelPressedX = r4.getX();
            this.cancelPressedY = r4.getY();
            TextSelectionHelper r3 = this.this$0;
            r3.allowDiscard = r3.isInSelectionMode();
            return;
        L7:
            if (this.this$0.allowDiscard == true) goto L9;
            return;
        L9:
            if (Math.abs(r4.getX() - this.cancelPressedX) < AndroidUtilities.touchSlop) goto L11;
            return;
        L11:
            if (Math.abs(r4.getY() - this.cancelPressedY) < AndroidUtilities.touchSlop) goto L13;
            return;
        L13:
            if (r4.getAction() != 3) goto L15;
        L16:
            checkCancel(r4.getX(), r4.getY(), true);
            return;
        L15:
            if (r4.getAction() == 1) goto L16;
        }

        public boolean checkOnTap(MotionEvent r8) {
            if (this.this$0.isInSelectionMode() == true) goto L5;
        L19:
            return false;
        L5:
            if (this.this$0.movingHandle == true) goto L19;
            int r0 = r8.getAction();
            if (r0 != 0) goto L10;
            this.pressedX = r8.getX();
            this.pressedY = r8.getY();
            this.pressedTime = System.currentTimeMillis();
            goto L19
        L10:
            if (r0 != 1) goto L19;
            if ((System.currentTimeMillis() - this.pressedTime) >= 200) goto L19;
            if (MathUtils.distance((int) this.pressedX, (int) this.pressedY, (int) r8.getX(), (int) r8.getY()) >= TextSelectionHelper.m7086$$Nest$fgettouchSlop(this.this$0)) goto L19;
            this.this$0.onTapToDismiss(r8.getRawX(), r8.getRawY());
            this.this$0.hideActions();
            this.this$0.clear();
            return true;
        }

        public void clearGestureExclusionRects() {
            if (Build.VERSION.SDK_INT >= 29) goto L5;
            return;
        L5:
            if (this.gestureExclusionRects.isEmpty() == true) goto L9;
            this.gestureExclusionRects.clear();
            setSystemGestureExclusionRects(this.gestureExclusionRects);
            return;
        }

        @Override // android.view.View
        public void invalidate() {
            super.invalidate();
            if (TextSelectionHelper.m7070$$Nest$fgetinvalidateParent(this.this$0) == false) goto L8;
            ViewGroup r1 = this.this$0.parentView;
            if (r1 == null) goto L9;
            r1.invalidate();
            return;
        L9:
            return;
        }

        @Override // android.view.View
        public void onDraw(Canvas r20) {
            if (this.this$0.isInSelectionMode() == false) goto L89;
            int r2 = AndroidUtilities.dp(22.0f);
            int r3 = TextSelectionHelper.m7085$$Nest$fgettopOffset(this.this$0);
            this.this$0.pickEndView();
            boolean r7 = false;
            if (this.this$0.selectedView == null) goto L38;
            r20.save();
            int[] r4 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0);
            float r9 = (paddingY() + r4[1]) + this.this$0.textY;
            float r42 = (paddingX() + r4[0]) + this.this$0.textX;
            r20.translate(r42, r9);
            Cell r10 = this.this$0.selectedView;
            if ((r10 instanceof ChatMessageCell) == false) goto L10;
            MessageObject r102 = ((ChatMessageCell) r10).getMessageObject();
        L11:
            if (r102 != null) goto L13;
        L15:
            this.handleViewPaint.setColor(this.this$0.getThemedColor(Theme.key_chat_TextSelectionCursor));
        L16:
            TextSelectionHelper r103 = this.this$0;
            int r104 = r103.getText(r103.selectedView, false).length();
            TextSelectionHelper r11 = this.this$0;
            int r12 = r11.selectionEnd;
            if (r12 < 0) goto L36;
            if (r12 > r104) goto L36;
            r11.fillLayoutForOffset(r12, r11.layoutBlock);
            TextSelectionHelper r105 = this.this$0;
            LayoutBlock r112 = r105.layoutBlock;
            Layout r122 = r112.layout;
            if (r122 == null) goto L36;
            int r106 = r105.selectionEnd - r112.charOffset;
            int r113 = r122.getText().length();
            if (r106 <= r113) goto L24;
            r106 = r113;
        L24:
            int r114 = r122.getLineForOffset(r106);
            float r107 = r122.getPrimaryHorizontal(r106);
            float r115 = r122.getLineBottom(r114);
            TextSelectionHelper r13 = this.this$0;
            LayoutBlock r14 = r13.layoutBlock;
            int r116 = (int) (r115 + r14.yOffset);
            float r108 = r107 + r14.xOffset;
            Rect r142 = r14.selectionBounds;
            if (r142 == null) goto L27;
            r108 = r142.right - r13.textX;
            r116 = r142.bottom - r13.textY;
            boolean r123 = false;
        L28:
            float r117 = r116;
            float r92 = r9 + r117;
            TextSelectionHelper r132 = this.this$0;
            if (r92 > (r132.keyboardSize + r3)) goto L31;
        L35:
            TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0).setEmpty();
            goto L36
        L31:
            if (r92 >= r132.parentView.getMeasuredHeight()) goto L35;
            if (r123 == true) goto L34;
            r20.save();
            r20.translate(r108, r117);
            float r118 = TextSelectionHelper.m7069$$Nest$fgetinterpolator(this.this$0).getInterpolation(this.this$0.handleViewProgress);
            float r124 = r2;
            float r133 = r124 / 2.0f;
            r20.scale(r118, r118, r133, r133);
            this.path.reset();
            Path r119 = this.path;
            Path.Direction r143 = Path.Direction.CCW;
            r119.addCircle(r133, r133, r133, r143);
            this.path.addRect(0.0f, 0.0f, r133, r133, r143);
            r20.drawPath(this.path, this.handleViewPaint);
            r20.restore();
            float r43 = r42 + r108;
            TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0).set(r43, r92 - r124, r43 + r124, r92 + r124);
            TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0).inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
            int r44 = 1;
        L37:
            r20.restore();
        L39:
            this.this$0.pickStartView();
            if (this.this$0.selectedView == null) goto L64;
            r20.save();
            int[] r93 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0);
            float r6 = (paddingY() + r93[1]) + this.this$0.textY;
            float r94 = (paddingX() + r93[0]) + this.this$0.textX;
            r20.translate(r94, r6);
            TextSelectionHelper r109 = this.this$0;
            int r1010 = r109.getText(r109.selectedView, false).length();
            TextSelectionHelper r1110 = this.this$0;
            int r125 = r1110.selectionStart;
            if (r125 < 0) goto L63;
            if (r125 > r1010) goto L63;
            r1110.fillLayoutForOffset(r125, r1110.layoutBlock);
            TextSelectionHelper r1011 = this.this$0;
            LayoutBlock r1111 = r1011.layoutBlock;
            Layout r126 = r1111.layout;
            if (r126 == null) goto L63;
            int r1012 = r1011.selectionStart - r1111.charOffset;
            int r1112 = r126.getLineForOffset(r1012);
            float r1013 = r126.getPrimaryHorizontal(r1012);
            float r1113 = r126.getLineBottom(r1112);
            TextSelectionHelper r134 = this.this$0;
            LayoutBlock r144 = r134.layoutBlock;
            int r1114 = (int) (r1113 + r144.yOffset);
            float r1014 = r1013 + r144.xOffset;
            Rect r145 = r144.selectionBounds;
            if (r145 == null) goto L49;
            r1014 = r145.left - r134.textX;
            r1114 = r145.bottom - r134.textY;
        L50:
            float r1115 = r1114;
            float r62 = r6 + r1115;
            TextSelectionHelper r127 = this.this$0;
            if (r62 <= (r3 + r127.keyboardSize)) goto L58;
            if (r62 >= r127.parentView.getMeasuredHeight()) goto L58;
            if (r7 == true) goto L56;
            r20.save();
            float r15 = r2;
            r20.translate(r1014 - r15, r1115);
            float r22 = TextSelectionHelper.m7069$$Nest$fgetinterpolator(this.this$0).getInterpolation(this.this$0.handleViewProgress);
            float r135 = r15 / 2.0f;
            r20.scale(r22, r22, r135, r135);
            this.path.reset();
            Path r23 = this.path;
            Path.Direction r32 = Path.Direction.CCW;
            r23.addCircle(r135, r135, r135, r32);
            this.path.addRect(r135, 0.0f, r15, r135, r32);
            r20.drawPath(this.path, this.handleViewPaint);
            r20.restore();
            float r95 = r94 + r1014;
            TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0).set(r95 - r15, r62 - r15, r95, r62 + r15);
            TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0).inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
            r44 = r44 + 1;
            goto L63
        L56:
            r20.save();
            r20.translate(r1014, r1115);
            float r33 = TextSelectionHelper.m7069$$Nest$fgetinterpolator(this.this$0).getInterpolation(this.this$0.handleViewProgress);
            float r24 = r2;
            float r146 = r24 / 2.0f;
            r20.scale(r33, r33, r146, r146);
            this.path.reset();
            Path r34 = this.path;
            Path.Direction r5 = Path.Direction.CCW;
            r34.addCircle(r146, r146, r146, r5);
            this.path.addRect(0.0f, 0.0f, r146, r146, r5);
            r20.drawPath(this.path, this.handleViewPaint);
            r20.restore();
            float r96 = r94 + r1014;
            TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0).set(r96, r62 - r24, r96 + r24, r62 + r24);
            TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0).inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
        L58:
            if (r62 > 0.0f) goto L60;
        L62:
            TextSelectionHelper.m7084$$Nest$fgetstartArea(this.this$0).setEmpty();
            goto L63
        L60:
            if ((r62 - this.this$0.getLineHeight()) >= this.this$0.parentView.getMeasuredHeight()) goto L62;
            r44 = r44 + 1;
            goto L62
        L49:
            r7 = r126.isRtlCharAt(r134.selectionStart);
        L63:
            r20.restore();
        L64:
            updateGestureExclusionRects();
            if (r44 == 0) goto L77;
            TextSelectionHelper r1 = this.this$0;
            if (r1.movingHandle == false) goto L77;
            if (r1.movingHandleStart == true) goto L71;
            r1.pickEndView();
        L71:
            TextSelectionHelper r16 = this.this$0;
            TextSelectionHelper.m7099$$Nest$mshowMagnifier(r16, TextSelectionHelper.m7072$$Nest$fgetlastX(r16));
            if (TextSelectionHelper.m7075$$Nest$fgetmagnifierY(this.this$0) == TextSelectionHelper.m7076$$Nest$fgetmagnifierYanimated(this.this$0)) goto L74;
        L75:
            invalidate();
            goto L77
        L74:
            if (TextSelectionHelper.m7073$$Nest$fgetmagnifierX(this.this$0) != TextSelectionHelper.m7074$$Nest$fgetmagnifierXanimated(this.this$0)) goto L75;
        L77:
            if (TextSelectionHelper.m7079$$Nest$fgetparentIsScrolling(this.this$0) == true) goto L80;
            AndroidUtilities.cancelRunOnUIThread(this.this$0.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.this$0.showActionsRunnable);
        L80:
            if (TextSelectionHelper.m7066$$Nest$fgetactionMode(this.this$0) == null) goto L85;
            TextSelectionHelper.m7066$$Nest$fgetactionMode(this.this$0).invalidateContentRect();
            if (TextSelectionHelper.m7066$$Nest$fgetactionMode(this.this$0) == null) goto L85;
            ((FloatingActionMode) TextSelectionHelper.m7066$$Nest$fgetactionMode(this.this$0)).updateViewLocationInWindow();
        L85:
            if (TextSelectionHelper.m7071$$Nest$fgetisOneTouch(this.this$0) == false) goto L88;
            invalidate();
            return;
        L88:
            return;
        L34:
            r20.save();
            float r147 = r2;
            r20.translate(r108 - r147, r117);
            float r1116 = TextSelectionHelper.m7069$$Nest$fgetinterpolator(this.this$0).getInterpolation(this.this$0.handleViewProgress);
            float r128 = r147 / 2.0f;
            r20.scale(r1116, r1116, r128, r128);
            this.path.reset();
            Path r1117 = this.path;
            Path.Direction r136 = Path.Direction.CCW;
            r1117.addCircle(r128, r128, r128, r136);
            this.path.addRect(r128, 0.0f, r147, r128, r136);
            r20.drawPath(this.path, this.handleViewPaint);
            r20.restore();
            float r45 = r42 + r108;
            TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0).set(r45 - r147, r92 - r147, r45, r92 + r147);
            TextSelectionHelper.m7067$$Nest$fgetendArea(this.this$0).inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
            goto L36
        L27:
            r123 = r122.isRtlCharAt(r13.selectionEnd);
        L36:
            r44 = 0;
            goto L37
        L13:
            if (r102.isOutOwner() == false) goto L15;
            this.handleViewPaint.setColor(this.this$0.getThemedColor(Theme.key_chat_outTextSelectionCursor));
            goto L16
        L10:
            r102 = null;
            goto L11
        L38:
            r44 = 0;
            goto L39
        }

        @Override // android.view.View
        public boolean onTouchEvent(MotionEvent r23) {
            boolean r2 = false;
            if (this.this$0.isInSelectionMode() == true) goto L6;
            return false;
        L6:
            if (r23.getPointerCount() > 1) goto L8;
            int r1 = (int) r23.getX();
            int r4 = (int) r23.getY();
            int r5 = TextSelectionHelper.m7072$$Nest$fgetlastX(this.this$0) - r1;
            TextSelectionHelper.m7088$$Nest$fputlastX(this.this$0, r1);
            TextSelectionHelper.m7089$$Nest$fputlastY(this.this$0, r4);
            int r6 = r23.getAction();
            if (r6 == 0) goto L280;
            if (r6 == 1) goto L271;
            if (r6 != 2) goto L14;
            TextSelectionHelper r62 = this.this$0;
            if (r62.movingHandle == true) goto L19;
        L303:
            return this.this$0.movingHandle;
        L19:
            if (r62.movingHandleStart == false) goto L21;
            r62.pickStartView();
        L22:
            TextSelectionHelper r63 = this.this$0;
            if (r63.selectedView == null) goto L25;
            int r12 = (int) (r1 + r63.movingOffsetX);
            int r7 = (int) (r4 + r63.movingOffsetY);
            boolean r11 = r63.selectLayout(r12, r7);
            TextSelectionHelper r64 = this.this$0;
            if (r64.selectedView != null) goto L30;
            return true;
        L30:
            if (r64.movingHandleStart == false) goto L32;
            r64.fillLayoutForOffset(r64.selectionStart, r64.layoutBlock);
        L33:
            TextSelectionHelper r65 = this.this$0;
            LayoutBlock r8 = r65.layoutBlock;
            if (r8.layout != null) goto L36;
            return true;
        L36:
            float r13 = r8.yOffset;
            Cell r14 = r65.selectedView;
            int[] r66 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(r65);
            int r72 = r7 - r66[1];
            int r16 = r12 - r66[0];
            TextSelectionHelper r15 = this.this$0;
            if (r15.parentRecyclerView == null) goto L39;
        L42:
            boolean r67 = true;
        L43:
            if (r67 == true) goto L45;
        L53:
            boolean r17 = false;
        L54:
            if (r67 == true) goto L56;
        L62:
            boolean r42 = false;
        L63:
            if (r17 == true) goto L71;
            if (r42 == true) goto L71;
            if (TextSelectionHelper.m7082$$Nest$fgetscrolling(this.this$0) == false) goto L69;
            TextSelectionHelper.m7091$$Nest$fputscrolling(this.this$0, false);
            AndroidUtilities.cancelRunOnUIThread(TextSelectionHelper.m7081$$Nest$fgetscrollRunnable(this.this$0));
        L69:
            int r172 = r72;
            TextSelectionHelper r152 = this.this$0;
            int r9 = r152.getCharOffsetFromCord(r16, r172, r152.textX, r152.textY, r152.selectedView, false);
            if (r9 < 0) goto L270;
            TextSelectionHelper r18 = this.this$0;
            if (r18.movingDirectionSettling == false) goto L91;
            if (r11 == false) goto L85;
            return true;
        L85:
            if (r9 >= r18.selectionStart) goto L88;
            r18.movingDirectionSettling = false;
            r18.movingHandleStart = true;
            r18.hideActions();
            goto L91
        L88:
            if (r9 <= r18.selectionEnd) goto L90;
            r18.movingDirectionSettling = false;
            r18.movingHandleStart = false;
            r18.hideActions();
            goto L91
        L90:
            return true;
        L91:
            TextSelectionHelper r19 = this.this$0;
            if (r19.movingHandleStart == false) goto L188;
            if (r19.selectionStart != r9) goto L96;
        L269:
            this.this$0.onOffsetChanged();
            goto L270
        L96:
            if (r19.canSelect(r9) == false) goto L269;
            TextSelectionHelper r110 = this.this$0;
            CharSequence r111 = r110.getText(r110.selectedView, false);
            TextSelectionHelper r43 = this.this$0;
            r43.fillLayoutForOffset(r9, r43.layoutBlock);
            TextSelectionHelper r44 = this.this$0;
            LayoutBlock r73 = r44.layoutBlock;
            Layout r82 = r73.layout;
            r44.fillLayoutForOffset(r44.selectionStart, r73);
            Layout r45 = this.this$0.layoutBlock.layout;
            if (r82 == null) goto L186;
            if (r45 == null) goto L186;
            int r10 = r9;
        L102:
            int r74 = r10 - 1;
            if (r74 < 0) goto L107;
            if (TextSelectionHelper.isInterruptedCharacter(r111.charAt(r74)) == false) goto L107;
            r10 = r10 - 1;
        L107:
            int r75 = r45.getLineForOffset(r10);
            int r122 = r45.getLineForOffset(this.this$0.selectionStart);
            int r153 = r45.getLineForOffset(r9);
            if (r11 == true) goto L185;
            if (r82 != r45) goto L185;
            if (r153 == r45.getLineForOffset(this.this$0.selectionStart)) goto L115;
            if (r153 == r75) goto L185;
        L115:
            if ((-1) != r45.getParagraphDirection(r45.getLineForOffset(r9))) goto L117;
        L181:
            TextSelectionHelper r112 = this.this$0;
            r112.selectionStart = r9;
            int r3 = r112.selectionEnd;
            if (r9 <= r3) goto L184;
            r112.selectionEnd = r9;
            r112.selectionStart = r3;
            r112.movingHandleStart = false;
        L184:
            AndroidUtilities.vibrateCursor(r112.textSelectionOverlay);
            this.this$0.invalidate();
            goto L269
        L117:
            if (r45.isRtlCharAt(r9) == true) goto L181;
            if (r75 != r122) goto L181;
            if (r153 != r75) goto L181;
            int r46 = r9;
        L122:
            int r68 = r46 + 1;
            if (r68 >= r111.length()) goto L127;
            if (TextSelectionHelper.isInterruptedCharacter(r111.charAt(r68)) == false) goto L127;
            r46 = r68;
        L127:
            int r69 = Math.abs(r9 - r10);
            int r47 = Math.abs(r9 - r46);
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(this.this$0) == false) goto L134;
            TextSelectionHelper r76 = this.this$0;
            if (r5 < 0) goto L132;
            boolean r83 = true;
        L133:
            TextSelectionHelper.m7092$$Nest$fputsnap(r76, r83);
            goto L134
        L132:
            r83 = false;
        L134:
            int r77 = r9 - 1;
            if (r77 > 0) goto L137;
        L139:
            boolean r78 = false;
        L141:
            if (r9 < r111.length()) goto L143;
            r9 = r111.length();
            char r84 = '\n';
        L144:
            int r123 = this.this$0.selectionStart;
            int r132 = r111.length();
            TextSelectionHelper r142 = this.this$0;
            if (r123 < r132) goto L147;
            r142.selectionStart = r111.length();
            char r113 = '\n';
        L148:
            int r124 = this.this$0.selectionStart;
            if (r9 >= r124) goto L151;
            if (r69 >= r47) goto L151;
        L163:
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(this.this$0) == false) goto L167;
            if (r9 != 1) goto L167;
            return true;
        L167:
            if (r9 < this.this$0.selectionStart) goto L169;
        L176:
            this.this$0.selectionStart = r9;
        L177:
            TextSelectionHelper r114 = this.this$0;
            int r32 = r114.selectionStart;
            int r48 = r114.selectionEnd;
            if (r32 <= r48) goto L180;
            r114.selectionEnd = r32;
            r114.selectionStart = r48;
            r114.movingHandleStart = false;
        L180:
            AndroidUtilities.vibrateCursor(r114.textSelectionOverlay);
            this.this$0.invalidate();
            goto L269
        L169:
            if (TextSelectionHelper.isInterruptedCharacter(r84) == false) goto L176;
            if (TextSelectionHelper.isInterruptedCharacter(r113) == true) goto L173;
        L174:
            if (r113 == '\n') goto L176;
            TextSelectionHelper r115 = this.this$0;
            r115.selectionStart = r10;
            TextSelectionHelper.m7092$$Nest$fputsnap(r115, true);
            goto L177
        L173:
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(this.this$0) == false) goto L176;
        L151:
            if (r9 <= r124) goto L154;
            if (r5 < 0) goto L163;
        L154:
            if (TextSelectionHelper.isInterruptedCharacter(r84) == false) goto L163;
            if (TextSelectionHelper.isInterruptedCharacter(r113) == true) goto L158;
        L159:
            if (r9 == 0) goto L163;
            if (r78 == false) goto L163;
            if (r113 != '\n') goto L269;
        L158:
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(this.this$0) == false) goto L163;
        L147:
            r113 = r111.charAt(r142.selectionStart);
            goto L148
        L143:
            r84 = r111.charAt(r9);
            goto L144
        L137:
            if (TextSelectionHelper.isInterruptedCharacter(r111.charAt(r77)) == false) goto L139;
            r78 = true;
        L185:
            TextSelectionHelper r85 = this.this$0;
            r85.jumpToLine(r9, r10, r11, r85.layoutBlock.yOffset, r13, r14);
            AndroidUtilities.vibrateCursor(this.this$0.textSelectionOverlay);
            this.this$0.invalidate();
        L186:
            return true;
        L188:
            if (r9 == r19.selectionEnd) goto L269;
            if (r19.canSelect(r9) == false) goto L269;
            TextSelectionHelper r116 = this.this$0;
            CharSequence r117 = r116.getText(r116.selectedView, false);
            int r102 = r9;
        L193:
            if (r102 >= r117.length()) goto L197;
            if (TextSelectionHelper.isInterruptedCharacter(r117.charAt(r102)) == false) goto L197;
            r102 = r102 + 1;
        L197:
            TextSelectionHelper r49 = this.this$0;
            r49.fillLayoutForOffset(r9, r49.layoutBlock);
            TextSelectionHelper r410 = this.this$0;
            LayoutBlock r79 = r410.layoutBlock;
            Layout r86 = r79.layout;
            r410.fillLayoutForOffset(r410.selectionEnd, r79);
            Layout r411 = this.this$0.layoutBlock.layout;
            if (r86 == null) goto L268;
            if (r411 == null) goto L268;
            if (r9 <= r117.length()) goto L204;
            r9 = r117.length();
        L204:
            int r710 = r411.getLineForOffset(r102);
            int r125 = r411.getLineForOffset(this.this$0.selectionEnd);
            int r154 = r411.getLineForOffset(r9);
            if (r11 == true) goto L267;
            if (r86 != r411) goto L267;
            if (r154 == r411.getLineForOffset(this.this$0.selectionEnd)) goto L212;
            if (r154 == r710) goto L267;
        L212:
            if ((-1) != r411.getParagraphDirection(r411.getLineForOffset(r9))) goto L214;
        L263:
            TextSelectionHelper r118 = this.this$0;
            r118.selectionEnd = r9;
            int r22 = r118.selectionStart;
            if (r22 <= r9) goto L266;
            r118.selectionEnd = r22;
            r118.selectionStart = r9;
            r118.movingHandleStart = true;
        L266:
            AndroidUtilities.vibrateCursor(r118.textSelectionOverlay);
            this.this$0.invalidate();
            goto L269
        L214:
            if (r411.isRtlCharAt(r9) == true) goto L263;
            if (r125 != r710) goto L263;
            if (r154 != r710) goto L263;
            int r412 = r9;
        L219:
            int r610 = r412 - 1;
            if (r610 < 0) goto L224;
            if (TextSelectionHelper.isInterruptedCharacter(r117.charAt(r610)) == false) goto L224;
            r412 = r412 - 1;
        L224:
            int r611 = Math.abs(r9 - r102);
            int r413 = Math.abs(r9 - r412);
            int r711 = r9 - 1;
            if (r711 > 0) goto L227;
        L229:
            boolean r712 = false;
        L231:
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(this.this$0) == false) goto L237;
            TextSelectionHelper r87 = this.this$0;
            if (r5 > 0) goto L235;
            boolean r119 = true;
        L236:
            TextSelectionHelper.m7092$$Nest$fputsnap(r87, r119);
            goto L237
        L235:
            r119 = false;
        L237:
            int r88 = this.this$0.selectionEnd;
            if (r88 > 0) goto L240;
        L242:
            TextSelectionHelper r120 = this.this$0;
            int r89 = r120.selectionEnd;
            if (r9 <= r89) goto L245;
            if (r611 > r413) goto L245;
        L251:
            TextSelectionHelper r121 = this.this$0;
            if (r9 <= r121.selectionEnd) goto L258;
            if (r712 == false) goto L258;
            if (r2 == true) goto L256;
        L257:
            TextSelectionHelper r126 = this.this$0;
            r126.selectionEnd = r102;
            TextSelectionHelper.m7092$$Nest$fputsnap(r126, true);
        L259:
            TextSelectionHelper r127 = this.this$0;
            int r24 = r127.selectionStart;
            int r414 = r127.selectionEnd;
            if (r24 <= r414) goto L262;
            r127.selectionEnd = r24;
            r127.selectionStart = r414;
            r127.movingHandleStart = true;
        L262:
            AndroidUtilities.vibrateCursor(r127.textSelectionOverlay);
            this.this$0.invalidate();
            goto L269
        L256:
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(r121) == true) goto L257;
        L258:
            this.this$0.selectionEnd = r9;
        L245:
            if (r9 >= r89) goto L247;
            if (r5 > 0) goto L251;
        L247:
            if (r712 == false) goto L251;
            if (r2 == false) goto L269;
            if (TextSelectionHelper.m7083$$Nest$fgetsnap(r120) == true) goto L269;
        L240:
            if (TextSelectionHelper.isInterruptedCharacter(r117.charAt(r88 - 1)) == false) goto L242;
            r2 = true;
            goto L242
        L227:
            if (TextSelectionHelper.isInterruptedCharacter(r117.charAt(r711)) == false) goto L229;
            r712 = true;
        L267:
            TextSelectionHelper r810 = this.this$0;
            r810.jumpToLine(r9, r102, r11, r810.layoutBlock.yOffset, r13, r14);
            AndroidUtilities.vibrateCursor(this.this$0.textSelectionOverlay);
            this.this$0.invalidate();
        L268:
            return true;
        L270:
            TextSelectionHelper r128 = this.this$0;
            TextSelectionHelper.m7099$$Nest$mshowMagnifier(r128, TextSelectionHelper.m7072$$Nest$fgetlastX(r128));
        L71:
            if (TextSelectionHelper.m7082$$Nest$fgetscrolling(this.this$0) == true) goto L73;
            TextSelectionHelper.m7091$$Nest$fputscrolling(this.this$0, true);
            AndroidUtilities.runOnUIThread(TextSelectionHelper.m7081$$Nest$fgetscrollRunnable(this.this$0));
        L73:
            TextSelectionHelper.m7090$$Nest$fputscrollDown(this.this$0, r17);
            TextSelectionHelper r415 = this.this$0;
            if (r17 == false) goto L77;
            float r129 = r415.parentView.getMeasuredHeight() - this.this$0.selectedView.getTop();
            float r416 = this.this$0.movingOffsetY;
        L76:
            r72 = (int) (r129 + r416);
            goto L69
        L77:
            r129 = -r415.selectedView.getTop();
            r416 = this.this$0.movingOffsetY;
            goto L76
        L56:
            if (r4 >= (((View) this.this$0.parentView.getParent()).getTop() + this.this$0.getParentTopPadding())) goto L62;
            TextSelectionHelper r417 = this.this$0;
            if (r417.multiselect == false) goto L60;
        L61:
            r42 = true;
            goto L63
        L60:
            if (r417.selectedView.getTop() >= this.this$0.getParentTopPadding()) goto L62;
        L45:
            if ((r4 - TextSelectionHelper.m7086$$Nest$fgettouchSlop(r15)) <= (this.this$0.parentView.getMeasuredHeight() - this.this$0.getParentBottomPadding())) goto L53;
            TextSelectionHelper r130 = this.this$0;
            if (r130.allowScrollPrentRelative == false) goto L49;
        L52:
            r17 = true;
            goto L54
        L49:
            if (r130.multiselect == true) goto L52;
            if (r130.selectedView.getBottom() <= (this.this$0.parentView.getMeasuredHeight() - this.this$0.getParentBottomPadding())) goto L53;
        L39:
            if (r15.parentNestedScrollView != null) goto L42;
            r67 = false;
            goto L43
        L32:
            r64.fillLayoutForOffset(r64.selectionEnd, r64.layoutBlock);
            goto L33
        L25:
            return r63.movingHandle;
        L21:
            r62.pickEndView();
            goto L22
        L14:
            if (r6 != 3) goto L303;
        L271:
            TextSelectionHelper.m7096$$Nest$mhideMagnifier(this.this$0);
            if (this.this$0.movingHandle == false) goto L274;
            requestParentDisallowIntercept(false);
        L274:
            TextSelectionHelper r131 = this.this$0;
            r131.movingHandle = false;
            r131.movingDirectionSettling = false;
            TextSelectionHelper.m7087$$Nest$fputisOneTouch(r131, false);
            if (this.this$0.isInSelectionMode() == false) goto L278;
            this.this$0.textSelectionOverlay.invalidate();
            AndroidUtilities.cancelRunOnUIThread(this.this$0.showActionsRunnable);
            AndroidUtilities.runOnUIThread(this.this$0.showActionsRunnable);
            this.this$0.showHandleViews();
        L278:
            if (TextSelectionHelper.m7082$$Nest$fgetscrolling(this.this$0) == false) goto L303;
            TextSelectionHelper.m7091$$Nest$fputscrolling(this.this$0, false);
            AndroidUtilities.cancelRunOnUIThread(TextSelectionHelper.m7081$$Nest$fgetscrollRunnable(this.this$0));
            goto L303
        L280:
            TextSelectionHelper r52 = this.this$0;
            if (r52.movingHandle == false) goto L283;
            return true;
        L283:
            float r612 = r1;
            float r811 = r4;
            boolean r53 = TextSelectionHelper.m7084$$Nest$fgetstartArea(r52).contains(r612, r811);
            TextSelectionHelper r92 = this.this$0;
            if (r53 == false) goto L294;
            r92.pickStartView();
            TextSelectionHelper r54 = this.this$0;
            if (r54.selectedView != null) goto L288;
            return false;
        L288:
            r54.movingHandle = true;
            r54.movingHandleStart = true;
            requestParentDisallowIntercept(true);
            TextSelectionHelper r55 = this.this$0;
            int[] r56 = r55.offsetToCord(r55.selectionStart);
            float r613 = this.this$0.getLineHeight() / 2;
            int[] r713 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0);
            TextSelectionHelper r812 = this.this$0;
            if (r812.useMovingOffset == false) goto L291;
            r812.movingOffsetX = ((r56[0] + r812.textX) + r713[0]) - r1;
        L292:
            r812.movingOffsetY = (((r56[1] + r812.textY) + r713[1]) - r4) - r613;
            r812.hideActions();
            this.this$0.textSelectionOverlay.invalidate();
            return true;
        L291:
            r812.movingOffsetX = 0.0f;
            goto L292
        L294:
            boolean r57 = TextSelectionHelper.m7067$$Nest$fgetendArea(r92).contains(r612, r811);
            TextSelectionHelper r614 = this.this$0;
            if (r57 == false) goto L301;
            r614.pickEndView();
            TextSelectionHelper r58 = this.this$0;
            if (r58.selectedView != null) goto L299;
            return false;
        L299:
            r58.movingHandle = true;
            r58.movingHandleStart = false;
            requestParentDisallowIntercept(true);
            TextSelectionHelper r59 = this.this$0;
            int[] r510 = r59.offsetToCord(r59.selectionEnd);
            float r615 = this.this$0.getLineHeight() / 2;
            int[] r714 = TextSelectionHelper.m7095$$Nest$mgetCoordsInParent(this.this$0);
            TextSelectionHelper r813 = this.this$0;
            r813.movingOffsetX = ((r510[0] + r813.textX) + r714[0]) - r1;
            r813.movingOffsetY = (((r510[1] + r813.textY) + r714[1]) - r4) - r615;
            TextSelectionHelper.m7099$$Nest$mshowMagnifier(r813, TextSelectionHelper.m7072$$Nest$fgetlastX(r813));
            this.this$0.hideActions();
            this.this$0.textSelectionOverlay.invalidate();
            return true;
        L301:
            r614.movingHandle = false;
            r614.allowDiscard = true;
            goto L303
        L8:
            return this.this$0.movingHandle;
        }
    }

    public static /* synthetic */ void $r8$lambda$Qbuw2rkVXkVNwK374vSaxAKumEM(TextSelectionHelper r0) {
        r0.lambda$new$1();
    }

    public static /* synthetic */ void $r8$lambda$vrAF32jPNZK05dEyUvma2ddo89k(TextSelectionHelper r0, ValueAnimator r1) {
        r0.lambda$showHandleViews$0(r1);
    }

    /* renamed from: -$$Nest$fgetactionMode, reason: not valid java name */
    public static /* bridge */ /* synthetic */ ActionMode m7066$$Nest$fgetactionMode(TextSelectionHelper r0) {
        return r0.actionMode;
    }

    /* renamed from: -$$Nest$fgetendArea, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m7067$$Nest$fgetendArea(TextSelectionHelper r0) {
        return r0.endArea;
    }

    /* renamed from: -$$Nest$fgethideActionsRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7068$$Nest$fgethideActionsRunnable(TextSelectionHelper r0) {
        return r0.hideActionsRunnable;
    }

    /* renamed from: -$$Nest$fgetinterpolator, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Interpolator m7069$$Nest$fgetinterpolator(TextSelectionHelper r0) {
        return r0.interpolator;
    }

    /* renamed from: -$$Nest$fgetinvalidateParent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7070$$Nest$fgetinvalidateParent(TextSelectionHelper r0) {
        return r0.invalidateParent;
    }

    /* renamed from: -$$Nest$fgetisOneTouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7071$$Nest$fgetisOneTouch(TextSelectionHelper r0) {
        return r0.isOneTouch;
    }

    /* renamed from: -$$Nest$fgetlastX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7072$$Nest$fgetlastX(TextSelectionHelper r0) {
        return r0.lastX;
    }

    /* renamed from: -$$Nest$fgetmagnifierX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7073$$Nest$fgetmagnifierX(TextSelectionHelper r0) {
        return r0.magnifierX;
    }

    /* renamed from: -$$Nest$fgetmagnifierXanimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7074$$Nest$fgetmagnifierXanimated(TextSelectionHelper r0) {
        return r0.magnifierXanimated;
    }

    /* renamed from: -$$Nest$fgetmagnifierY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7075$$Nest$fgetmagnifierY(TextSelectionHelper r0) {
        return r0.magnifierY;
    }

    /* renamed from: -$$Nest$fgetmagnifierYanimated, reason: not valid java name */
    public static /* bridge */ /* synthetic */ float m7076$$Nest$fgetmagnifierYanimated(TextSelectionHelper r0) {
        return r0.magnifierYanimated;
    }

    /* renamed from: -$$Nest$fgetonAiGenerateListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnAiGenerateListener m7077$$Nest$fgetonAiGenerateListener(TextSelectionHelper r0) {
        return r0.onAiGenerateListener;
    }

    /* renamed from: -$$Nest$fgetonTranslateListener, reason: not valid java name */
    public static /* bridge */ /* synthetic */ OnTranslateListener m7078$$Nest$fgetonTranslateListener(TextSelectionHelper r0) {
        return r0.onTranslateListener;
    }

    /* renamed from: -$$Nest$fgetparentIsScrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7079$$Nest$fgetparentIsScrolling(TextSelectionHelper r0) {
        return r0.parentIsScrolling;
    }

    /* renamed from: -$$Nest$fgetscrollDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7080$$Nest$fgetscrollDown(TextSelectionHelper r0) {
        return r0.scrollDown;
    }

    /* renamed from: -$$Nest$fgetscrollRunnable, reason: not valid java name */
    public static /* bridge */ /* synthetic */ Runnable m7081$$Nest$fgetscrollRunnable(TextSelectionHelper r0) {
        return r0.scrollRunnable;
    }

    /* renamed from: -$$Nest$fgetscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7082$$Nest$fgetscrolling(TextSelectionHelper r0) {
        return r0.scrolling;
    }

    /* renamed from: -$$Nest$fgetsnap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ boolean m7083$$Nest$fgetsnap(TextSelectionHelper r0) {
        return r0.snap;
    }

    /* renamed from: -$$Nest$fgetstartArea, reason: not valid java name */
    public static /* bridge */ /* synthetic */ RectF m7084$$Nest$fgetstartArea(TextSelectionHelper r0) {
        return r0.startArea;
    }

    /* renamed from: -$$Nest$fgettopOffset, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7085$$Nest$fgettopOffset(TextSelectionHelper r0) {
        return r0.topOffset;
    }

    /* renamed from: -$$Nest$fgettouchSlop, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m7086$$Nest$fgettouchSlop(TextSelectionHelper r0) {
        return r0.touchSlop;
    }

    /* renamed from: -$$Nest$fputisOneTouch, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7087$$Nest$fputisOneTouch(TextSelectionHelper r0, boolean r1) {
        r0.isOneTouch = r1;
    }

    /* renamed from: -$$Nest$fputlastX, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7088$$Nest$fputlastX(TextSelectionHelper r0, int r1) {
        r0.lastX = r1;
    }

    /* renamed from: -$$Nest$fputlastY, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7089$$Nest$fputlastY(TextSelectionHelper r0, int r1) {
        r0.lastY = r1;
    }

    /* renamed from: -$$Nest$fputscrollDown, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7090$$Nest$fputscrollDown(TextSelectionHelper r0, boolean r1) {
        r0.scrollDown = r1;
    }

    /* renamed from: -$$Nest$fputscrolling, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7091$$Nest$fputscrolling(TextSelectionHelper r0, boolean r1) {
        r0.scrolling = r1;
    }

    /* renamed from: -$$Nest$fputsnap, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7092$$Nest$fputsnap(TextSelectionHelper r0, boolean r1) {
        r0.snap = r1;
    }

    /* renamed from: -$$Nest$fputtryCapture, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7093$$Nest$fputtryCapture(TextSelectionHelper r0, boolean r1) {
        r0.tryCapture = r1;
    }

    /* renamed from: -$$Nest$mcopyText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7094$$Nest$mcopyText(TextSelectionHelper r0) {
        r0.copyText();
    }

    /* renamed from: -$$Nest$mgetCoordsInParent, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int[] m7095$$Nest$mgetCoordsInParent(TextSelectionHelper r0) {
        return r0.getCoordsInParent();
    }

    /* renamed from: -$$Nest$mhideMagnifier, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7096$$Nest$mhideMagnifier(TextSelectionHelper r0) {
        r0.hideMagnifier();
    }

    /* renamed from: -$$Nest$mquoteText, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7097$$Nest$mquoteText(TextSelectionHelper r0) {
        r0.quoteText();
    }

    /* renamed from: -$$Nest$mshowActions, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7098$$Nest$mshowActions(TextSelectionHelper r0) {
        r0.showActions();
    }

    /* renamed from: -$$Nest$mshowMagnifier, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m7099$$Nest$mshowMagnifier(TextSelectionHelper r0, int r1) {
        r0.showMagnifier(r1);
    }

    public TextSelectionHelper() {
        this.tmpCoord = new int[2];
        this.path = new PathWithSavedBottom(null);
        this.selectionPaint = new Paint(1);
        this.selectionHandlePaint = new Paint(1);
        this.selectionPath = new CornerPath();
        this.selectionHandlePath = new Path();
        this.selectionPathMirror = new PathCopyTo(this.selectionPath);
        this.selectionStart = -1;
        this.selectionEnd = -1;
        this.textSelectActionCallback = createActionCallback();
        this.textArea = new Rect();
        this.startArea = new RectF();
        this.endArea = new RectF();
        this.layoutBlock = new LayoutBlock();
        this.interpolator = new OvershootInterpolator();
        this.showActionsAsPopupAlways = false;
        this.scrollRunnable = new AnonymousClass1(this);
        this.startSelectionRunnable = new AnonymousClass2(this);
        this.useMovingOffset = true;
        this.onTranslateListener = null;
        this.onAiGenerateListener = null;
        this.showActionsRunnable = new TextSelectionHelper$$ExternalSyntheticLambda2(this);
        this.hideActionsRunnable = new AnonymousClass3(this);
        this.tempPath2 = new ScalablePath(null);
        this.longpressDelay = ViewConfiguration.getLongPressTimeout();
        this.touchSlop = ViewConfiguration.get(ApplicationLoader.applicationContext).getScaledTouchSlop();
        Paint r0 = this.selectionPaint;
        float r2 = AndroidUtilities.dp(6.0f);
        this.cornerRadius = r2;
        r0.setPathEffect(new CornerPathEffect(r2));
        this.selectionPath.setRectsUnionDiffDelta(1.0f);
    }

    private void copyText() {
        if (isInSelectionMode() == true) goto L6;
        return;
    L6:
        if (onCopyOverride() == true) goto L11;
        CharSequence r0 = getSelectedText();
        if (r0 == null) goto L17;
        AndroidUtilities.addToClipboard(r0);
        goto L11
    L17:
        return;
    L11:
        hideActions();
        clear(true);
        Callback r1 = this.callback;
        if (r1 == null) goto L15;
        r1.onTextCopied();
        return;
    }

    private ActionMode.Callback createActionCallback() {
        return new AnonymousClass5(this, new AnonymousClass4(this));
    }

    private void drawLine(Layout r15, int r16, int r17, int r18, boolean r19, boolean r20, float r21) {
        this.tempPath2.reset();
        r15.getSelectionPath(r17, r18, this.tempPath2);
        if (this.tempPath2.lastBottom >= r15.getLineBottom(r16)) goto L5;
        int r4 = r15.getLineTop(r16);
        float r6 = r15.getLineBottom(r16) - r4;
        float r42 = r4;
        float r62 = r6 / (this.tempPath2.lastBottom - r42);
    L6:
        int r7 = 0;
    L7:
        int r8 = ScalablePath.m7103$$Nest$fgetrectsCount(this.tempPath2);
        ScalablePath r9 = this.tempPath2;
        if (r7 >= r8) goto L19;
        RectF r82 = (RectF) ScalablePath.m7102$$Nest$fgetrects(r9).get(r7);
        float r92 = Math.max(r21, r82.left);
        if (r19 == false) goto L12;
        float r11 = this.cornerRadius / 2.0f;
    L13:
        float r93 = (int) (r92 - r11);
        float r112 = (int) (((r82.top - r42) * r62) + r42);
        float r12 = Math.max(r21, r82.right);
        if (r20 == false) goto L16;
        float r13 = this.cornerRadius / 2.0f;
    L17:
        r82.set(r93, r112, (int) (r12 + r13), (int) (((r82.bottom - r42) * r62) + r42));
        this.selectionPath.addRect(r82, Path.Direction.CW);
        r7 = r7 + 1;
        goto L7
    L16:
        r13 = 0.0f;
        goto L17
    L12:
        r11 = 0.0f;
        goto L13
    L19:
        if (ScalablePath.m7103$$Nest$fgetrectsCount(r9) != 0) goto L31;
        if (r20 == true) goto L32;
        int r1 = (int) r15.getPrimaryHorizontal(r17);     // Catch: Exception -> L23
        int r2 = (int) r15.getPrimaryHorizontal(r18);     // Catch: Exception -> L23
        int r3 = r15.getLineTop(r16);     // Catch: Exception -> L23
        int r0 = r15.getLineBottom(r16);     // Catch: Exception -> L23
        CornerPath r43 = this.selectionPath;     // Catch: Exception -> L23
        float r14 = this.cornerRadius;     // Catch: Exception -> L23
        r43.addRect(r1 - (r14 / 2.0f), r3, r2 + (r14 / 4.0f), r0, Path.Direction.CW);     // Catch: Exception -> L23
        return;
    L23:
        e = move-exception;
        FileLog.e(e);
        return;
    L32:
        return;
    L31:
        return;
    L5:
        r62 = 1.0f;
        r42 = 0.0f;
        goto L6
    }

    private int[] getCoordsInParent() {
        View r0 = (View) this.selectedView;
        int r1 = 0;
        if (r0 != null) goto L5;
    L10:
        int r2 = 0;
    L19:
        return new int[]{r1, r2};
    L5:
        if (this.parentView == null) goto L10;
        r2 = 0;
        int r3 = 0;
    L8:
        if (r0 == this.parentView) goto L17;
        if (r0 == null) goto L10;
        r2 = (int) (r2 + r0.getY());
        r3 = (int) (r3 + r0.getX());
        if ((r0 instanceof NestedScrollView) == false) goto L15;
        r2 = r2 - r0.getScrollY();
        r3 = r3 - r0.getScrollX();
    L15:
        if ((r0.getParent() instanceof View) == false) goto L10;
        r0 = (View) r0.getParent();
        goto L8
    L17:
        r1 = r3;
        goto L19
    }

    private void hideMagnifier() {
        if (Build.VERSION.SDK_INT < 28) goto L8;
        Magnifier r0 = this.magnifier;
        if (r0 == null) goto L9;
        r0.dismiss();
        this.magnifier = null;
        return;
    L9:
        return;
    }

    public static boolean isInterruptedCharacter(char r1) {
        if (Character.isLetter(r1) == false) goto L5;
        return true;
    L5:
        if (Character.isDigit(r1) == false) goto L7;
        return true;
    L7:
        if (r1 == '_') goto L14;
        return false;
    L14:
        return true;
    }

    private /* synthetic */ void lambda$new$1() {
        this.textSelectionOverlay.invalidate();
        showActions();
    }

    private /* synthetic */ void lambda$showHandleViews$0(ValueAnimator r1) {
        this.handleViewProgress = ((Float) r1.getAnimatedValue()).floatValue();
        this.textSelectionOverlay.invalidate();
    }

    private void quoteText() {
        if (isInSelectionMode() == false) goto L16;
        Cell r0 = this.selectedView;
        if ((r0 instanceof ChatMessageCell) == false) goto L8;
        MessageObject r02 = ((ChatMessageCell) r0).getMessageObject();
    L9:
        if (r02 == null) goto L17;
        CharSequence r1 = getSelectedText();
        if (r1 != null) goto L14;
        return;
    L14:
        onQuoteClick(r02, this.selectionStart, this.selectionEnd, r1);
        clear(true);
        return;
    L17:
        return;
    L8:
        r02 = null;
        goto L9
    }

    private void showActions() {
        if (this.textSelectionOverlay != null) goto L6;
        return;
    L6:
        if (this.movingHandle == false) goto L8;
        return;
    L8:
        if (isInSelectionMode() == true) goto L10;
        return;
    L10:
        if (canShowActions() == true) goto L12;
        return;
    L12:
        if (this.actionsIsShowing == false) goto L14;
    L17:
        AndroidUtilities.cancelRunOnUIThread(this.hideActionsRunnable);
        this.actionsIsShowing = true;
        return;
    L14:
        if (this.actionMode != null) goto L16;
        FloatingToolbar r0 = new FloatingToolbar(this.textSelectionOverlay.getContext(), this.textSelectionOverlay, 1, getResourcesProvider());
        FloatingActionMode r2 = new FloatingActionMode(this.textSelectionOverlay.getContext(), (ActionMode.Callback2) this.textSelectActionCallback, this.textSelectionOverlay, r0);
        this.actionMode = r2;
        this.textSelectActionCallback.onCreateActionMode(r2, r2.getMenu());
    L16:
        ActionMode.Callback r02 = this.textSelectActionCallback;
        ActionMode r22 = this.actionMode;
        r02.onPrepareActionMode(r22, r22.getMenu());
        this.actionMode.hide(1);
        goto L17
    }

    private void showMagnifier(int r11) {
        if (Build.VERSION.SDK_INT >= 28) goto L5;
        return;
    L5:
        if (this.selectedView != null) goto L7;
        return;
    L7:
        if (this.isOneTouch == false) goto L9;
        return;
    L9:
        if (this.movingHandle == true) goto L11;
        return;
    L11:
        if (this.textSelectionOverlay != null) goto L14;
        return;
    L14:
        if (this.movingHandleStart == false) goto L16;
        int r0 = this.selectionStart;
    L17:
        fillLayoutForOffset(r0, this.layoutBlock);
        LayoutBlock r1 = this.layoutBlock;
        Layout r2 = r1.layout;
        if (r2 == null) goto L70;
        int r02 = r2.getLineForOffset(Utilities.clamp(r0 - r1.charOffset, r2.getText().length(), 0));
        int r12 = r2.getLineBottom(r02) - r2.getLineTop(r02);
        int[] r4 = getCoordsInParent();
        float r5 = (((r2.getLineTop(r02) + this.textY) + r4[1]) - r12) - AndroidUtilities.dp(8.0f);
        LayoutBlock r7 = this.layoutBlock;
        int r52 = (int) (r5 + r7.yOffset);
        Cell r8 = this.selectedView;
        if ((r8 instanceof ArticleViewer.BlockTableCell) == false) goto L23;
        int r03 = r4[0];
        int r22 = r8.getMeasuredWidth() + r03;
    L24:
        if (r11 >= r03) goto L26;
        r11 = r03;
    L28:
        float r23 = r52;
        if (this.magnifierY == r23) goto L31;
        this.magnifierY = r23;
        this.magnifierDy = (r23 - this.magnifierYanimated) / 200.0f;
    L31:
        float r112 = r11;
        if (this.magnifierX == r112) goto L35;
        this.magnifierX = r112;
        this.magnifierDx = (r112 - this.magnifierXanimated) / 100.0f;
    L35:
        if (this.magnifier != null) goto L37;
        TextSelectionHelper$$ExternalSyntheticApiModelOutline1.m();
        this.magnifier = TextSelectionHelper$$ExternalSyntheticApiModelOutline0.m(this.textSelectionOverlay);
        this.magnifierYanimated = this.magnifierY;
        this.magnifierXanimated = this.magnifierX;
    L37:
        float r113 = this.magnifierYanimated;
        float r04 = this.magnifierY;
        if (r113 == r04) goto L40;
        this.magnifierYanimated = r113 + (this.magnifierDy * 16.0f);
    L40:
        float r114 = this.magnifierDy;
        if (r114 <= 0.0f) goto L46;
        if (this.magnifierYanimated <= r04) goto L46;
        this.magnifierYanimated = r04;
    L50:
        float r115 = this.magnifierXanimated;
        float r05 = this.magnifierX;
        if (r115 == r05) goto L53;
        this.magnifierXanimated = r115 + (this.magnifierDx * 16.0f);
    L53:
        float r116 = this.magnifierDx;
        if (r116 <= 0.0f) goto L59;
        if (this.magnifierXanimated <= r05) goto L59;
        this.magnifierXanimated = r05;
    L63:
        this.magnifier.show(this.magnifierXanimated, (this.magnifierYanimated + (r12 * 1.5f)) + AndroidUtilities.dp(8.0f));
        this.magnifier.update();
        return;
    L59:
        if (r116 >= 0.0f) goto L63;
        if (this.magnifierXanimated >= r05) goto L63;
        this.magnifierXanimated = r05;
    L46:
        if (r114 >= 0.0f) goto L50;
        if (this.magnifierYanimated >= r04) goto L50;
        this.magnifierYanimated = r04;
        goto L50
    L26:
        if (r11 <= r22) goto L28;
        r11 = r22;
        goto L28
    L23:
        int r72 = (int) (((r4[0] + this.textX) + r7.xOffset) + r2.getLineLeft(r02));
        r22 = (int) (((r4[0] + this.textX) + this.layoutBlock.xOffset) + r2.getLineRight(r02));
        r03 = r72;
        goto L24
    L70:
        return;
    L16:
        r0 = this.selectionEnd;
        goto L17
    }

    public boolean canCopy() {
        return true;
    }

    public boolean canCut() {
        return false;
    }

    public boolean canPaste() {
        return false;
    }

    public boolean canSelect(int r2) {
        if (r2 != this.selectionStart) goto L5;
        return false;
    L5:
        if (r2 == this.selectionEnd) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean canShowActions() {
        if (this.selectedView == null) goto L6;
        return true;
    L6:
        return false;
    }

    public boolean canShowQuote() {
        return false;
    }

    public void cancelTextSelectionRunnable() {
        AndroidUtilities.cancelRunOnUIThread(this.startSelectionRunnable);
        this.tryCapture = false;
    }

    public void checkSelectionCancel(MotionEvent r3) {
        if (r3.getAction() != 1) goto L5;
    L8:
        cancelTextSelectionRunnable();
        return;
    L5:
        if (r3.getAction() == 3) goto L8;
    }

    public void clear() {
        clear(false);
    }

    public void clear(boolean r4) {
        onExitSelectionMode(r4);
        this.selectionStart = -1;
        this.selectionEnd = -1;
        hideMagnifier();
        hideActions();
        invalidate();
        this.selectedView = null;
        this.selectedCellId = 0;
        this.selectedCellEditDate = null;
        AndroidUtilities.cancelRunOnUIThread(this.startSelectionRunnable);
        this.tryCapture = false;
        TextSelectionHelper<Cell>.TextSelectionOverlay r0 = this.textSelectionOverlay;
        if (r0 == null) goto L5;
        r0.setVisibility(8);
        this.textSelectionOverlay.clearGestureExclusionRects();
    L5:
        this.handleViewProgress = 0.0f;
        Callback r2 = this.callback;
        if (r2 == null) goto L8;
        r2.onStateChanged(false);
    L8:
        this.capturedX = -1;
        this.capturedY = -1;
        this.maybeTextX = -1;
        this.maybeTextY = -1;
        this.movingOffsetX = 0.0f;
        this.movingOffsetY = 0.0f;
        this.movingHandle = false;
    }

    public boolean clipboardHasContent() {
        TextSelectionHelper<Cell>.TextSelectionOverlay r2 = this.textSelectionOverlay;     // Catch: Exception -> L16
        if (r2 == null) goto L6;
        Context r22 = r2.getContext();     // Catch: Exception -> L16
    L7:
        if (r22 != null) goto L9;
        return false;
    L9:
        ClipboardManager r23 = (ClipboardManager) r22.getSystemService("clipboard");     // Catch: Exception -> L16
        if (r23 == null) goto L15;
        if (r23.hasPrimaryClip() == false) goto L15;
        return true;
    L6:
        r22 = ApplicationLoader.applicationContext;     // Catch: Exception -> L16
    L15:
        return false;
    }

    public void drawSelection(Canvas r24, Layout r25, int r26, int r27, boolean r28, boolean r29, float r30) {
        if (r25 != null) goto L5;
        return;
    L5:
        if (r25.getText() == null) goto L79;
        int r3 = Utilities.clamp(r26, r25.getText().length(), 0);
        int r4 = Utilities.clamp(r27, r25.getText().length(), 0);
        this.selectionPath.reset();
        this.selectionHandlePath.reset();
        float r2 = this.cornerRadius;
        float r10 = r2 * 1.65f;
        int r11 = (int) (r2 / 2.0f);
        int r22 = r25.getLineForOffset(r3);
        int r12 = r25.getLineForOffset(r4);
        if (r22 != r12) goto L10;
        drawLine(r25, r22, r3, r4, !r28, !r29, r30);
        TextSelectionHelper<Cell> r0 = this;
        int r15 = r22;
        int r13 = r3;
        int r23 = r12;
        boolean r272 = true;
    L42:
        int r32 = Build.VERSION.SDK_INT;
        if (r32 < 26) goto L45;
        boolean r9 = r272;
    L46:
        if (r9 == false) goto L48;
        r24.save();
    L48:
        float r6 = r25.getPrimaryHorizontal(r13);
        float r7 = r25.getPrimaryHorizontal(r4);
        float r112 = r25.getLineBottom(r15);
        float r122 = r25.getLineBottom(r23);
        if (r28 == false) goto L58;
        if (r29 == false) goto L58;
        if (r112 != r122) goto L58;
        if (Math.abs(r7 - r6) >= r10) goto L58;
        float r1 = Math.min(r6, r7);
        float r210 = Math.max(r6, r7);
        Rect r42 = AndroidUtilities.rectTmp2;
        r42.set((int) r1, (int) (r112 - r10), (int) r210, (int) r112);
        RectF r14 = AndroidUtilities.rectTmp;
        r14.set(r42);
        r0.selectionHandlePath.addRect(r14, Path.Direction.CW);
        if (r32 < 26) goto L70;
        r24.clipOutRect(r42);
    L70:
        r0.selectionPath.closeRects();
        r24.drawPath(r0.selectionPath, r0.selectionPaint);
        if (r9 == false) goto L78;
        r24.restore();
        r24.drawPath(r0.selectionHandlePath, r0.selectionHandlePaint);
        return;
    L78:
        return;
    L58:
        if (r28 == true) goto L60;
    L64:
        if (r29 == false) goto L70;
        if (r25.isRtlCharAt(r4) == true) goto L70;
        Rect r43 = AndroidUtilities.rectTmp2;
        r43.set((int) Math.max(r7 - r10, r25.getLineLeft(r23)), (int) (r122 - r10), (int) r7, (int) r122);
        RectF r16 = AndroidUtilities.rectTmp;
        r16.set(r43);
        r0.selectionHandlePath.addRect(r16, Path.Direction.CW);
        if (r32 < 26) goto L70;
        r24.clipOutRect(r43);
        goto L70
    L60:
        if (r25.isRtlCharAt(r13) == true) goto L64;
        Rect r132 = AndroidUtilities.rectTmp2;
        r132.set((int) r6, (int) (r112 - r10), (int) Math.min(r6 + r10, r25.getLineRight(r15)), (int) r112);
        RectF r5 = AndroidUtilities.rectTmp;
        r5.set(r132);
        r0.selectionHandlePath.addRect(r5, Path.Direction.CW);
        if (r32 < 26) goto L64;
        r132.set(r132.left - ((int) r10), r132.top, r132.right, r132.bottom);
        r24.clipOutRect(r132);
        goto L64
    L45:
        r9 = false;
        goto L46
    L10:
        int r02 = r25.getLineEnd(r22);
        if (r25.getParagraphDirection(r22) == (-1)) goto L34;
        if (r02 <= 0) goto L34;
        int r44 = r02 - 1;
        CharSequence r52 = r25.getText();
        int r152 = (int) r25.getPrimaryHorizontal(r44);
        if (r25.isRtlCharAt(r44) == false) goto L25;
        int r62 = r44;
    L17:
        if (r25.isRtlCharAt(r62) == false) goto L21;
        if (r62 == 0) goto L21;
        r62 = r62 - 1;
    L21:
        r272 = true;
        if (r25.getLineForOffset(r62) != r25.getLineForOffset(r44)) goto L24;
        float r63 = r25.getPrimaryHorizontal(r62 + 1);
    L26:
        int r64 = (int) r63;
        int r92 = Math.min(r152, r64);
        int r65 = Math.max(r152, r64);
        if (r44 > 0) goto L29;
    L33:
        Rect r93 = null;
    L35:
        r0 = this;
        r0.drawLine(r25, r22, r3, r44, !r28, true, r30);
        r15 = r22;
        r13 = r3;
        if (r93 == null) goto L38;
        RectF r211 = AndroidUtilities.rectTmp;
        r211.set(r93);
        r0.selectionPath.addRect(r211, Path.Direction.CW);
    L38:
        int r212 = r15 + 1;
    L39:
        if (r212 >= r12) goto L41;
        int r33 = (int) r25.getLineLeft(r212);
        int r45 = (int) r25.getLineRight(r212);
        float r66 = r11;
        r0.selectionPath.addRect(Math.max(r30, Math.min(r33, r45)) - r66, r25.getLineTop(r212), Math.max(r30, Math.max(r33, r45)) + r66, r25.getLineBottom(r212) + 1, Path.Direction.CW);
        r212 = r212 + 1;
        goto L39
    L41:
        r23 = r12;
        r4 = r4;
        r0.drawLine(r25, r23, r25.getLineStart(r12), r4, true, !r29, r30);
        goto L42
    L29:
        if (r44 >= r52.length()) goto L33;
        if (Character.isWhitespace(r52.charAt(r02 - 2)) == true) goto L33;
        r93 = new Rect(((int) Math.max(r30, r92)) - r11, r25.getLineTop(r22), ((int) Math.max(r30, r65)) + r11, r25.getLineBottom(r22));
        goto L35
    L24:
        r63 = r25.getLineLeft(r22);
        goto L26
    L25:
        r272 = true;
        r63 = r25.getLineRight(r22);
    L34:
        r272 = true;
        r44 = r02;
        goto L33
    }

    public void fillLayoutForOffset(int r2, LayoutBlock r3) {
        fillLayoutForOffset(r2, r3, false);
    }

    public abstract void fillLayoutForOffset(int r1, LayoutBlock r2, boolean r3);

    public void finishOneTouchSelection() {
        if (isInSelectionMode() == true) goto L5;
        return;
    L5:
        this.movingHandle = false;
        this.movingDirectionSettling = false;
        this.isOneTouch = false;
        TextSelectionHelper<Cell>.TextSelectionOverlay r0 = this.textSelectionOverlay;
        if (r0 == null) goto L8;
        r0.invalidate();
    L8:
        AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
        AndroidUtilities.runOnUIThread(this.showActionsRunnable);
    }

    public boolean forceShowSelectAll() {
        return false;
    }

    public abstract int getCharOffsetFromCord(int r1, int r2, int r3, int r4, Cell r5, boolean r6);

    public abstract int getLineHeight();

    public TextSelectionHelper<Cell>.TextSelectionOverlay getOverlayView(Context r2) {
        if (this.textSelectionOverlay != null) goto L6;
        this.textSelectionOverlay = new TextSelectionOverlay(this, r2);
    L6:
        return this.textSelectionOverlay;
    }

    public int getParentBottomPadding() {
        return 0;
    }

    public int getParentTopPadding() {
        return 0;
    }

    public Theme.ResourcesProvider getResourcesProvider() {
        return this.resourcesProvider;
    }

    public Cell getSelectedCell() {
        return this.selectedView;
    }

    public CharSequence getSelectedText() {
        CharSequence r0 = getText(this.selectedView, false);
        if (r0 != null) goto L5;
        return null;
    L5:
        return r0.subSequence(this.selectionStart, this.selectionEnd);
    }

    public abstract CharSequence getText(Cell r1, boolean r2);

    public int getThemedColor(int r1) {
        return Theme.getColor(r1, this.resourcesProvider);
    }

    public void hideActions() {
        if (this.actionMode != null) goto L5;
    L7:
        this.actionsIsShowing = false;
        if (isInSelectionMode() == true) goto L12;
        ActionMode r0 = this.actionMode;
        if (r0 == null) goto L12;
        r0.finish();
        this.actionMode = null;
    L12:
        ActionBarPopupWindow r2 = this.popupWindow;
        if (r2 == null) goto L16;
        r2.dismiss();
        return;
    L16:
        return;
    L5:
        if (this.actionsIsShowing == false) goto L7;
        this.actionsIsShowing = false;
        this.hideActionsRunnable.run();
        goto L7
    }

    public void hideActionsMenu() {
        AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
        hideActions();
    }

    public void invalidate() {
        Cell r0 = this.selectedView;
        if (r0 == null) goto L5;
        r0.invalidate();
    L5:
        TextSelectionHelper<Cell>.TextSelectionOverlay r1 = this.textSelectionOverlay;
        if (r1 == null) goto L9;
        r1.invalidate();
        return;
    }

    public boolean isInSelectionMode() {
        if (this.selectionStart >= 0) goto L5;
        return false;
    L5:
        if (this.selectionEnd < 0) goto L10;
        return true;
    L10:
        return false;
    }

    public boolean isSelected(MessageObject r2) {
        if (r2 != null) goto L6;
        return false;
    L6:
        if (this.selectedCellId != r2.getId()) goto L9;
        return true;
    L9:
        return false;
    }

    public boolean isTryingSelect() {
        return this.tryCapture;
    }

    public void jumpToLine(int r1, int r2, boolean r3, float r4, float r5, Cell r6) {
        if (this.movingHandleStart == false) goto L11;
        this.selectionStart = r2;
        if (r3 == true) goto L9;
        int r12 = this.selectionEnd;
        if (r2 <= r12) goto L9;
        this.selectionEnd = r2;
        this.selectionStart = r12;
        this.movingHandleStart = false;
    L9:
        this.snap = true;
        return;
    L11:
        this.selectionEnd = r2;
        if (r3 == true) goto L16;
        int r13 = this.selectionStart;
        if (r13 <= r2) goto L16;
        this.selectionEnd = r13;
        this.selectionStart = r2;
        this.movingHandleStart = true;
    L16:
        this.snap = true;
    }

    public int[] offsetToCord(int r5) {
        fillLayoutForOffset(r5, this.layoutBlock);
        LayoutBlock r0 = this.layoutBlock;
        Layout r1 = r0.layout;
        int r52 = r5 - r0.charOffset;
        if (r1 == null) goto L11;
        if (r52 < 0) goto L11;
        if (r52 > r1.getText().length()) goto L11;
        int r02 = r1.getLineForOffset(r52);
        this.tmpCoord[0] = (int) (r1.getPrimaryHorizontal(r52) + this.layoutBlock.xOffset);
        this.tmpCoord[1] = r1.getLineBottom(r02);
        int[] r53 = this.tmpCoord;
        r53[1] = (int) (r53[1] + this.layoutBlock.yOffset);
        return r53;
    L11:
        return this.tmpCoord;
    }

    public boolean onCopyOverride() {
        return false;
    }

    public void onCutAction() {
    }

    public void onExitSelectionMode(boolean r1) {
    }

    public void onOffsetChanged() {
    }

    public void onParentScrolled() {
        if (isInSelectionMode() == false) goto L8;
        TextSelectionHelper<Cell>.TextSelectionOverlay r0 = this.textSelectionOverlay;
        if (r0 == null) goto L9;
        this.parentIsScrolling = true;
        r0.invalidate();
        hideActions();
        return;
    L9:
        return;
    }

    public void onPasteAction() {
    }

    public void onQuoteClick(MessageObject r1, int r2, int r3, CharSequence r4) {
    }

    public boolean onSelectAllOverride() {
        return false;
    }

    public void onTapToDismiss(float r1, float r2) {
    }

    public abstract void onTextSelected(Cell r1, Cell r2);

    public boolean onTouchEvent(MotionEvent r12) {
        int r0 = r12.getAction();
        if (r0 == 0) goto L25;
        if (r0 != 1) goto L6;
    L15:
        AndroidUtilities.cancelRunOnUIThread(this.startSelectionRunnable);
        if (this.tryCapture == true) goto L18;
    L23:
        this.tryCapture = false;
        return false;
    L18:
        if (isInSelectionMode() == false) goto L23;
        this.movingHandle = false;
        this.movingDirectionSettling = false;
        this.isOneTouch = false;
        TextSelectionHelper<Cell>.TextSelectionOverlay r122 = this.textSelectionOverlay;
        if (r122 == null) goto L22;
        r122.invalidate();
    L22:
        AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
        AndroidUtilities.runOnUIThread(this.showActionsRunnable);
        showHandleViews();
        goto L23
    L6:
        if (r0 != 2) goto L8;
        int r02 = (int) r12.getY();
        int r123 = (int) r12.getX();
        int r2 = this.capturedY;
        int r3 = (r2 - r02) * (r2 - r02);
        int r03 = this.capturedX;
        int r32 = r3 + ((r03 - r123) * (r03 - r123));
        int r124 = this.touchSlop;
        if (r32 <= (r124 * r124)) goto L14;
        AndroidUtilities.cancelRunOnUIThread(this.startSelectionRunnable);
        this.tryCapture = false;
    L14:
        return this.tryCapture;
    L8:
        if (r0 == 3) goto L15;
        return false;
    L25:
        this.capturedX = (int) r12.getX();
        this.capturedY = (int) r12.getY();
        this.tryCapture = false;
        this.textArea.inset(-AndroidUtilities.dp(8.0f), -AndroidUtilities.dp(8.0f));
        if (this.textArea.contains(this.capturedX, this.capturedY) == true) goto L28;
    L58:
        TextSelectionHelper<Cell> r4 = this;
    L60:
        return r4.tryCapture;
    L28:
        if (this.maybeSelectedView == null) goto L58;
        this.textArea.inset(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
        int r125 = this.capturedX;
        int r04 = this.capturedY;
        Rect r33 = this.textArea;
        int r42 = r33.right;
        if (r125 <= r42) goto L32;
        r125 = r42 - 1;
    L32:
        int r43 = r33.left;
        if (r125 >= r43) goto L35;
        r125 = r43 + 1;
    L35:
        int r5 = r125;
        int r126 = r33.top;
        if (r04 >= r126) goto L38;
        r04 = r126 + 1;
    L38:
        int r127 = r33.bottom;
        if (r04 <= r127) goto L41;
        r04 = r127 - 1;
    L41:
        r4 = this;
        int r11 = r4.getCharOffsetFromCord(r5, r04, this.maybeTextX, this.maybeTextY, this.maybeSelectedView, true);
        CharSequence r128 = r4.getText(r4.maybeSelectedView, true);
        if (r11 < r128.length()) goto L52;
        r4.fillLayoutForOffset(r11, r4.layoutBlock, true);
        Layout r05 = r4.layoutBlock.layout;
        if (r05 != null) goto L47;
        r4.tryCapture = false;
        return false;
    L47:
        int r06 = r05.getLineCount() - 1;
        float r1 = r5 - r4.maybeTextX;
        if (r1 >= (r4.layoutBlock.layout.getLineRight(r06) + AndroidUtilities.dp(4.0f))) goto L52;
        if (r1 <= r4.layoutBlock.layout.getLineLeft(r06)) goto L52;
        r11 = r128.length() - 1;
    L52:
        if (r11 < 0) goto L60;
        if (r11 >= r128.length()) goto L60;
        if (r128.charAt(r11) == '\n') goto L60;
        AndroidUtilities.cancelRunOnUIThread(r4.startSelectionRunnable);
        AndroidUtilities.runOnUIThread(r4.startSelectionRunnable, r4.longpressDelay);
        r4.tryCapture = true;
        goto L60
    }

    public void pickEndView() {
    }

    public void pickStartView() {
    }

    public boolean selectLayout(int r1, int r2) {
        return false;
    }

    public void setCallback(Callback r1) {
        this.callback = r1;
    }

    public void setInvalidateParent() {
        this.invalidateParent = true;
    }

    public void setKeyboardSize(int r1) {
        this.keyboardSize = r1;
        invalidate();
    }

    public void setMaybeTextCord(int r1, int r2) {
        this.maybeTextX = r1;
        this.maybeTextY = r2;
    }

    public void setOnAiGenerate(OnAiGenerateListener r1) {
        this.onAiGenerateListener = r1;
    }

    public void setOnTranslate(OnTranslateListener r1) {
        this.onTranslateListener = r1;
    }

    public void setParentView(ViewGroup r2) {
        if ((r2 instanceof RecyclerListView) == false) goto L5;
        this.parentRecyclerView = (RecyclerListView) r2;
    L5:
        this.parentView = r2;
    }

    public void setTopOffset(int r1) {
        this.topOffset = r1;
    }

    public void showHandleViews() {
        if (this.handleViewProgress != 1.0f) goto L5;
        return;
    L5:
        if (this.textSelectionOverlay == null) goto L13;
        ValueAnimator r0 = this.handleViewAnimator;
        if (r0 == null) goto L10;
        r0.cancel();
    L10:
        ValueAnimator r02 = ValueAnimator.ofFloat(new float[]{this.handleViewProgress, 1.0f});
        this.handleViewAnimator = r02;
        r02.addUpdateListener(new TextSelectionHelper$$ExternalSyntheticLambda3(this));
        this.handleViewAnimator.setDuration((long) (Math.abs(1.0f - this.handleViewProgress) * 250.0f));
        this.handleViewAnimator.start();
        return;
    }

    public void stopScrolling() {
        this.parentIsScrolling = false;
        this.textSelectionOverlay.invalidate();
        AndroidUtilities.cancelRunOnUIThread(this.showActionsRunnable);
        AndroidUtilities.runOnUIThread(this.showActionsRunnable);
    }
}
