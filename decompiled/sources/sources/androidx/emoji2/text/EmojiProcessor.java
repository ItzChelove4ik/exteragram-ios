package androidx.emoji2.text;

import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.MetaKeyKeyListener;
import android.view.KeyEvent;
import android.view.inputmethod.InputConnection;
import androidx.emoji2.text.EmojiCompat;
import androidx.emoji2.text.MetadataRepo;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

/* loaded from: classes.dex */
final class EmojiProcessor {
    private final int[] mEmojiAsDefaultStyleExceptions;
    private EmojiCompat.GlyphChecker mGlyphChecker;
    private final MetadataRepo mMetadataRepo;
    private final EmojiCompat.SpanFactory mSpanFactory;
    private final boolean mUseEmojiAsDefaultStyle;

    /* loaded from: classes3.dex */
    public static final class CodepointIndexFinder {
        public static int findIndexBackward(CharSequence r5, int r6, int r7) {
            int r0 = r5.length();
            if (r6 < 0) goto L30;
            if (r0 < r6) goto L30;
            if (r7 >= 0) goto L9;
            return -1;
        L9:
            boolean r2 = false;
        L10:
            if (r7 == 0) goto L11;
            r6 = r6 - 1;
            if (r6 < 0) goto L14;
            char r3 = r5.charAt(r6);
            if (r2 == true) goto L20;
            if (Character.isSurrogate(r3) == false) goto L25;
            if (Character.isHighSurrogate(r3) == true) goto L28;
            r2 = true;
            goto L10
        L28:
            return -1;
        L25:
            r7 = r7 - 1;
            goto L10
        L20:
            if (Character.isHighSurrogate(r3) == false) goto L21;
            r7 = r7 - 1;
            goto L9
        L21:
            return -1;
        L14:
            if (r2 == false) goto L16;
            return -1;
        L16:
            return 0;
        L11:
            return r6;
        L30:
            return -1;
        }

        public static int findIndexForward(CharSequence r6, int r7, int r8) {
            int r0 = r6.length();
            if (r7 < 0) goto L29;
            if (r0 < r7) goto L29;
            if (r8 >= 0) goto L9;
            return -1;
        L9:
            boolean r3 = false;
        L10:
            if (r8 == 0) goto L11;
            if (r7 >= r0) goto L13;
            char r4 = r6.charAt(r7);
            if (r3 == true) goto L19;
            if (Character.isSurrogate(r4) == false) goto L24;
            if (Character.isLowSurrogate(r4) == true) goto L27;
            r7 = r7 + 1;
            r3 = true;
            goto L10
        L27:
            return -1;
        L24:
            r8 = r8 - 1;
            r7 = r7 + 1;
            goto L10
        L19:
            if (Character.isLowSurrogate(r4) == false) goto L20;
            r8 = r8 - 1;
            r7 = r7 + 1;
            goto L9
        L20:
            return -1;
        L13:
            if (r3 == false) goto L15;
            return -1;
        L15:
            return r0;
        L11:
            return r7;
        L29:
            return -1;
        }
    }

    /* loaded from: classes3.dex */
    public static class EmojiProcessAddSpanCallback implements EmojiProcessCallback<UnprecomputeTextOnModificationSpannable> {
        private final EmojiCompat.SpanFactory mSpanFactory;
        public UnprecomputeTextOnModificationSpannable spannable;

        public EmojiProcessAddSpanCallback(UnprecomputeTextOnModificationSpannable r1, EmojiCompat.SpanFactory r2) {
            this.spannable = r1;
            this.mSpanFactory = r2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public UnprecomputeTextOnModificationSpannable getResult() {
            return this.spannable;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public /* bridge */ /* synthetic */ UnprecomputeTextOnModificationSpannable getResult() {
            return getResult();
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean handleEmoji(CharSequence r4, int r5, int r6, TypefaceEmojiRasterizer r7) {
            if (r7.isPreferredSystemRender() == false) goto L6;
            return true;
        L6:
            if (this.spannable == null) goto L8;
        L12:
            EmojiSpan r42 = this.mSpanFactory.createSpan(r7);
            this.spannable.setSpan(r42, r5, r6, 33);
            return true;
        L8:
            if ((r4 instanceof Spannable) == false) goto L10;
            Spannable r43 = (Spannable) r4;
        L11:
            this.spannable = new UnprecomputeTextOnModificationSpannable(r43);
            goto L12
        L10:
            r43 = new SpannableString(r4);
            goto L11
        }
    }

    /* loaded from: classes3.dex */
    public interface EmojiProcessCallback<T> {
        T getResult();

        boolean handleEmoji(CharSequence r1, int r2, int r3, TypefaceEmojiRasterizer r4);
    }

    /* loaded from: classes3.dex */
    public static class MarkExclusionCallback implements EmojiProcessCallback<MarkExclusionCallback> {
        private final String mExclusion;

        public MarkExclusionCallback(String r1) {
            this.mExclusion = r1;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public MarkExclusionCallback getResult() {
            return this;
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public /* bridge */ /* synthetic */ MarkExclusionCallback getResult() {
            return getResult();
        }

        @Override // androidx.emoji2.text.EmojiProcessor.EmojiProcessCallback
        public boolean handleEmoji(CharSequence r1, int r2, int r3, TypefaceEmojiRasterizer r4) {
            if (TextUtils.equals(r1.subSequence(r2, r3), this.mExclusion) == false) goto L6;
            r4.setExclusion(true);
            return false;
        L6:
            return true;
        }
    }

    /* loaded from: classes3.dex */
    public static final class ProcessorSm {
        private int mCurrentDepth;
        private MetadataRepo.Node mCurrentNode;
        private final int[] mEmojiAsDefaultStyleExceptions;
        private MetadataRepo.Node mFlushNode;
        private int mLastCodepoint;
        private final MetadataRepo.Node mRootNode;
        private int mState;
        private final boolean mUseEmojiAsDefaultStyle;

        public ProcessorSm(MetadataRepo.Node r2, boolean r3, int[] r4) {
            this.mState = 1;
            this.mRootNode = r2;
            this.mCurrentNode = r2;
            this.mUseEmojiAsDefaultStyle = r3;
            this.mEmojiAsDefaultStyleExceptions = r4;
        }

        private static boolean isEmojiStyle(int r1) {
            if (r1 != 65039) goto L6;
            return true;
        L6:
            return false;
        }

        private static boolean isTextStyle(int r1) {
            if (r1 != 65038) goto L6;
            return true;
        L6:
            return false;
        }

        private int reset() {
            this.mState = 1;
            this.mCurrentNode = this.mRootNode;
            this.mCurrentDepth = 0;
            return 1;
        }

        private boolean shouldUseEmojiPresentationStyleForSingleCodepoint() {
            if (this.mCurrentNode.getData().isDefaultEmoji() == false) goto L6;
            return true;
        L6:
            if (isEmojiStyle(this.mLastCodepoint) == false) goto L9;
            return true;
        L9:
            if (this.mUseEmojiAsDefaultStyle == true) goto L11;
        L16:
            return false;
        L11:
            if (this.mEmojiAsDefaultStyleExceptions != null) goto L13;
            return true;
        L13:
            int r0 = this.mCurrentNode.getData().getCodepointAt(0);
            if (Arrays.binarySearch(this.mEmojiAsDefaultStyleExceptions, r0) >= 0) goto L16;
            return true;
        }

        public int check(int r5) {
            MetadataRepo.Node r0 = this.mCurrentNode.get(r5);
            int r3 = 2;
            if (this.mState == 2) goto L7;
            if (r0 != null) goto L6;
            r3 = reset();
        L25:
            this.mLastCodepoint = r5;
            return r3;
        L6:
            this.mState = 2;
            this.mCurrentNode = r0;
            this.mCurrentDepth = 1;
            goto L25
        L7:
            if (r0 == null) goto L10;
            this.mCurrentNode = r0;
            this.mCurrentDepth++;
            goto L25
        L10:
            if (isTextStyle(r5) == false) goto L13;
            r3 = reset();
            goto L25
        L13:
            if (isEmojiStyle(r5) == true) goto L25;
            if (this.mCurrentNode.getData() == null) goto L24;
            r3 = 3;
            if (this.mCurrentDepth == 1) goto L20;
            this.mFlushNode = this.mCurrentNode;
            reset();
            goto L25
        L20:
            if (shouldUseEmojiPresentationStyleForSingleCodepoint() == false) goto L22;
            this.mFlushNode = this.mCurrentNode;
            reset();
            goto L25
        L22:
            r3 = reset();
            goto L25
        L24:
            r3 = reset();
            goto L25
        }

        public TypefaceEmojiRasterizer getCurrentMetadata() {
            return this.mCurrentNode.getData();
        }

        public TypefaceEmojiRasterizer getFlushMetadata() {
            return this.mFlushNode.getData();
        }

        public boolean isInFlushableState() {
            if (this.mState == 2) goto L5;
            return false;
        L5:
            if (this.mCurrentNode.getData() != null) goto L7;
            return false;
        L7:
            if (this.mCurrentDepth <= 1) goto L9;
        L10:
            return true;
        L9:
            if (shouldUseEmojiPresentationStyleForSingleCodepoint() == true) goto L10;
            return false;
        }
    }

    public EmojiProcessor(MetadataRepo r1, EmojiCompat.SpanFactory r2, EmojiCompat.GlyphChecker r3, boolean r4, int[] r5, Set<int[]> r6) {
        this.mSpanFactory = r2;
        this.mMetadataRepo = r1;
        this.mGlyphChecker = r3;
        this.mUseEmojiAsDefaultStyle = r4;
        this.mEmojiAsDefaultStyleExceptions = r5;
        initExclusions(r6);
    }

    private static boolean delete(Editable r6, KeyEvent r7, boolean r8) {
        if (hasModifiers(r7) == false) goto L5;
        return false;
    L5:
        int r72 = Selection.getSelectionStart(r6);
        int r1 = Selection.getSelectionEnd(r6);
        if (hasInvalidSelection(r72, r1) == false) goto L8;
        return false;
    L8:
        EmojiSpan[] r12 = (EmojiSpan[]) r6.getSpans(r72, r1, EmojiSpan.class);
        if (r12 != null) goto L11;
    L24:
        return false;
    L11:
        if (r12.length <= 0) goto L24;
        int r2 = r12.length;
        int r3 = 0;
    L13:
        if (r3 >= r2) goto L24;
        EmojiSpan r4 = r12[r3];
        int r5 = r6.getSpanStart(r4);
        int r42 = r6.getSpanEnd(r4);
        if (r8 == false) goto L17;
        if (r5 != r72) goto L17;
    L21:
        r6.delete(r5, r42);
        return true;
    L17:
        if (r8 == true) goto L19;
        if (r42 == r72) goto L21;
    L19:
        if (r72 <= r5) goto L23;
        if (r72 < r42) goto L21;
    L23:
        r3 = r3 + 1;
        goto L13
    }

    public static boolean handleDeleteSurroundingText(InputConnection r5, Editable r6, int r7, int r8, boolean r9) {
        if (r6 == null) goto L27;
        if (r5 == null) goto L27;
        if (r7 < 0) goto L27;
        if (r8 < 0) goto L27;
        int r1 = Selection.getSelectionStart(r6);
        int r2 = Selection.getSelectionEnd(r6);
        if (hasInvalidSelection(r1, r2) == false) goto L12;
        return false;
    L12:
        if (r9 == false) goto L17;
        int r72 = CodepointIndexFinder.findIndexBackward(r6, r1, Math.max(r7, 0));
        int r82 = CodepointIndexFinder.findIndexForward(r6, r2, Math.max(r8, 0));
        if (r72 == (-1)) goto L16;
        if (r82 == (-1)) goto L16;
    L18:
        EmojiSpan[] r92 = (EmojiSpan[]) r6.getSpans(r72, r82, EmojiSpan.class);
        if (r92 == null) goto L27;
        if (r92.length <= 0) goto L27;
        int r12 = r92.length;
        int r22 = 0;
    L23:
        if (r22 >= r12) goto L25;
        EmojiSpan r3 = r92[r22];
        int r4 = r6.getSpanStart(r3);
        int r32 = r6.getSpanEnd(r3);
        r72 = Math.min(r4, r72);
        r82 = Math.max(r32, r82);
        r22 = r22 + 1;
        goto L23
    L25:
        int r73 = Math.max(r72, 0);
        int r83 = Math.min(r82, r6.length());
        r5.beginBatchEdit();
        r6.delete(r73, r83);
        r5.endBatchEdit();
        return true;
    L16:
        return false;
    L17:
        r72 = Math.max(r1 - r7, 0);
        r82 = Math.min(r2 + r8, r6.length());
    L27:
        return false;
    }

    public static boolean handleOnKeyDown(Editable r3, int r4, KeyEvent r5) {
        if (r4 != 67) goto L5;
        boolean r42 = delete(r3, r5, false);
    L9:
        if (r42 == false) goto L12;
        MetaKeyKeyListener.adjustMetaAfterKeypress(r3);
        return true;
    L12:
        return false;
    L5:
        if (r4 == 112) goto L7;
        r42 = false;
        goto L9
    L7:
        r42 = delete(r3, r5, true);
        goto L9
    }

    private boolean hasGlyph(CharSequence r2, int r3, int r4, TypefaceEmojiRasterizer r5) {
        if (r5.getHasGlyph() != 0) goto L6;
        r5.setHasGlyph(this.mGlyphChecker.hasGlyph(r2, r3, r4, r5.getSdkAdded()));
    L6:
        if (r5.getHasGlyph() != 2) goto L9;
        return true;
    L9:
        return false;
    }

    private static boolean hasInvalidSelection(int r1, int r2) {
        if (r1 == (-1)) goto L9;
        if (r2 == (-1)) goto L11;
        if (r1 != r2) goto L12;
        return false;
    L12:
        return true;
    L11:
        return true;
    L9:
        return true;
    }

    private static boolean hasModifiers(KeyEvent r0) {
        return !KeyEvent.metaStateHasNoModifiers(r0.getMetaState());
    }

    private void initExclusions(Set<int[]> r9) {
        if (r9.isEmpty() == true) goto L9;
        Iterator<int[]> r92 = r9.iterator();
    L7:
        if (r92.hasNext() == false) goto L11;
        int[] r0 = r92.next();
        String r2 = new String(r0, 0, r0.length);
        MarkExclusionCallback r7 = new MarkExclusionCallback(r2);
        process(r2, 0, r2.length(), 1, true, r7);
        goto L7
    L11:
        return;
    }

    private <T> T process(CharSequence r9, int r10, int r11, int r12, boolean r13, EmojiProcessCallback<T> r14) {
        ProcessorSm r0 = new ProcessorSm(this.mMetadataRepo.getRootNode(), this.mUseEmojiAsDefaultStyle, this.mEmojiAsDefaultStyleExceptions);
        int r4 = 0;
        boolean r5 = true;
        int r2 = Character.codePointAt(r9, r10);
    L3:
        int r1 = r10;
    L4:
        if (r10 >= r11) goto L26;
        if (r4 >= r12) goto L26;
        if (r5 == false) goto L26;
        int r6 = r0.check(r2);
        if (r6 != 1) goto L10;
        r1 = r1 + Character.charCount(Character.codePointAt(r9, r1));
        if (r1 >= r11) goto L24;
        r2 = Character.codePointAt(r9, r1);
    L24:
        r10 = r1;
        goto L4
    L10:
        if (r6 != 2) goto L12;
        r10 = r10 + Character.charCount(r2);
        if (r10 >= r11) goto L4;
        r2 = Character.codePointAt(r9, r10);
        goto L4
    L12:
        if (r6 != 3) goto L4;
        if (r13 == true) goto L17;
        if (hasGlyph(r9, r1, r10, r0.getFlushMetadata()) == true) goto L3;
    L17:
        r5 = r14.handleEmoji(r9, r1, r10, r0.getFlushMetadata());
        r4 = r4 + 1;
    L26:
        if (r0.isInFlushableState() == false) goto L34;
        if (r4 >= r12) goto L34;
        if (r5 == false) goto L34;
        if (r13 == false) goto L31;
    L32:
        r14.handleEmoji(r9, r1, r10, r0.getCurrentMetadata());
        goto L34
    L31:
        if (hasGlyph(r9, r1, r10, r0.getCurrentMetadata()) == false) goto L32;
    L34:
        return r14.getResult();
    }

    public CharSequence process(CharSequence r11, int r12, int r13, int r14, boolean r15) {
        boolean r1 = r11 instanceof SpannableBuilder;
        if (r1 == false) goto L6;
        ((SpannableBuilder) r11).beginBatchEdit();
    L6:
        if (r1 == false) goto L70;
    L66:
        UnprecomputeTextOnModificationSpannable r2 = new UnprecomputeTextOnModificationSpannable((Spannable) r11);     // Catch: Throwable -> L58
    L21:
        if (r2 == null) goto L33;
        EmojiSpan[] r4 = (EmojiSpan[]) r2.getSpans(r12, r13, EmojiSpan.class);     // Catch: Throwable -> L16
        if (r4 == null) goto L33;
        if (r4.length <= 0) goto L33;
        int r5 = r4.length;     // Catch: Throwable -> L16
        int r6 = 0;
    L27:
        if (r6 >= r5) goto L33;
        EmojiSpan r7 = r4[r6];     // Catch: Throwable -> L16
        int r8 = r2.getSpanStart(r7);     // Catch: Throwable -> L16
        int r9 = r2.getSpanEnd(r7);     // Catch: Throwable -> L16
        if (r8 == r13) goto L31;
        r2.removeSpan(r7);     // Catch: Throwable -> L16
    L31:
        r12 = Math.min(r8, r12);     // Catch: Throwable -> L16
        r13 = Math.max(r9, r13);     // Catch: Throwable -> L16
        r6 = r6 + 1;
    L33:
        int r42 = r12;
        int r52 = r13;
        if (r42 != r52) goto L36;
    L37:
        CharSequence r3 = r11;
        if (r1 == false) goto L62;
        SpannableBuilder r112 = (SpannableBuilder) r3;
    L56:
        r112.endBatchEdit();
    L57:
        return r3;
    L62:
        return r3;
    L36:
        if (r42 >= r11.length()) goto L37;
        if (r14 == Integer.MAX_VALUE) goto L44;
        if (r2 == null) goto L44;
        r14 = r14 - ((EmojiSpan[]) r2.getSpans(0, r2.length(), EmojiSpan.class)).length;
    L16:
        th = move-exception;
        Throwable r10 = th;
        r3 = r11;
    L63:
        if (r1 == false) goto L73;
        ((SpannableBuilder) r3).endBatchEdit();
        throw r10;
    L73:
        throw r10;
    L44:
        r3 = r11;
        UnprecomputeTextOnModificationSpannable r102 = (UnprecomputeTextOnModificationSpannable) process(r3, r42, r52, r14, r15, new EmojiProcessAddSpanCallback(r2, this.mSpanFactory));     // Catch: Throwable -> L52
        if (r102 == null) goto L54;
        Spannable r103 = r102.getUnwrappedSpannable();     // Catch: Throwable -> L52
        if (r1 == false) goto L51;
        ((SpannableBuilder) r3).endBatchEdit();
    L51:
        return r103;
    L54:
        if (r1 == false) goto L57;
        r112 = (SpannableBuilder) r3;
    L52:
        th = th;
    L53:
        r10 = th;
    L58:
        th = th;
        r3 = r11;
        goto L53
    L70:
        if ((r11 instanceof Spannable) == true) goto L66;
        if ((r11 instanceof Spanned) == true) goto L13;
    L18:
        r2 = null;
        goto L21
    L13:
        if (((Spanned) r11).nextSpanTransition(r12 - 1, r13 + 1, EmojiSpan.class) > r13) goto L18;
        r2 = new UnprecomputeTextOnModificationSpannable(r11);     // Catch: Throwable -> L16
        goto L21
    }
}
