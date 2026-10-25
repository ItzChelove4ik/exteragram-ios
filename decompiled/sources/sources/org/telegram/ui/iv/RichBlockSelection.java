package org.telegram.ui.iv;

import android.graphics.Rect;
import android.text.Layout;
import android.text.StaticLayout;
import android.text.TextPaint;
import org.telegram.ui.Cells.TextSelectionHelper;

/* loaded from: classes7.dex */
abstract class RichBlockSelection {
    private static Layout placeholder;

    /* renamed from: org.telegram.ui.iv.RichBlockSelection$1, reason: invalid class name */
    public class AnonymousClass1 implements TextSelectionHelper.TextLayoutBlock {
        final /* synthetic */ Rect val$bounds;
        final /* synthetic */ Layout val$layout;

        public AnonymousClass1(Layout r1, Rect r2) {
            this.val$layout = r1;
            this.val$bounds = r2;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Layout getLayout() {
            return this.val$layout;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getRow() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public Rect getSelectionBounds() {
            return this.val$bounds;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getX() {
            return 0;
        }

        @Override // org.telegram.ui.Cells.TextSelectionHelper.TextLayoutBlock
        public int getY() {
            return 0;
        }
    }

    public static TextSelectionHelper.TextLayoutBlock of(int r1, int r2, int r3, int r4) {
        Rect r0 = new Rect(r1, r2, r3, r4);
        return new AnonymousClass1(placeholder(), r0);
    }

    private static Layout placeholder() {
        if (placeholder != null) goto L6;
        placeholder = new StaticLayout(" ", new TextPaint(), 1, Layout.Alignment.ALIGN_NORMAL, 1.0f, 0.0f, false);
    L6:
        return placeholder;
    }
}
