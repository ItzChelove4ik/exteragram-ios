package org.telegram.ui.Components;

import android.graphics.Paint;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.MetricAffectingSpan;
import com.exteragram.messenger.utils.ui.FontUtils;
import me.vkryl.core.BitwiseUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class TextStyleSpan extends MetricAffectingSpan {
    private int color;
    private TextStyleRun style;
    private int textSize;

    public static class TextStyleRun {
        public int end;
        public int flags;
        public boolean header;
        public int start;
        public TLRPC.MessageEntity urlEntity;

        public TextStyleRun() {
        }

        public TextStyleRun(TextStyleRun r2) {
            this.flags = r2.flags;
            this.start = r2.start;
            this.end = r2.end;
            this.urlEntity = r2.urlEntity;
            this.header = r2.header;
        }

        public void applyStyle(TextPaint r3) {
            Typeface r0 = getTypeface();
            if (r0 == null) goto L6;
            r3.setTypeface(r0);
        L6:
            if ((this.flags & 16) == 0) goto L8;
            r3.setFlags(r3.getFlags() | 8);
        L9:
            int r02 = this.flags;
            if ((r02 & 8) == 0) goto L12;
        L15:
            r3.setFlags(r3.getFlags() | 16);
        L17:
            if ((this.flags & 1) != 0) goto L21;
            if (this.header == true) goto L21;
        L24:
            if ((this.flags & 2) == 0) goto L29;
            if (FontUtils.isItalicSupported() == true) goto L29;
            r3.setTextSkewX(-0.25f);
        L29:
            if ((this.flags & 512) == 0) goto L31;
            r3.bgColor = Theme.getColor(Theme.key_chats_archivePullDownBackground);
        L31:
            int r2 = this.flags;
            if ((r2 & 8192) == 0) goto L36;
            r3.setColor(Theme.getColor(Theme.key_text_RedBold));
            return;
        L36:
            if ((r2 & 4096) == 0) goto L39;
            r3.setColor(Theme.getColor(Theme.key_featuredStickers_addButton));
            return;
        L39:
            return;
        L21:
            if (FontUtils.isMediumWeightSupported() == true) goto L24;
            r3.setStrokeWidth(0.65f);
            r3.setStyle(Paint.Style.FILL_AND_STROKE);
            goto L24
        L12:
            if ((r02 & 8192) != 0) goto L15;
            r3.setFlags(r3.getFlags() & (-17));
            goto L17
        L8:
            r3.setFlags(r3.getFlags() & (-9));
            goto L9
        }

        public Typeface getTypeface() {
            boolean r0 = this.header;
            int r1 = this.flags;
            if (r0 == false) goto L24;
            if ((r1 & 2) == 0) goto L13;
            if (FontUtils.isMediumWeightSupported() == false) goto L13;
            if (FontUtils.isItalicSupported() == false) goto L13;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC);
        L13:
            if (FontUtils.isMediumWeightSupported() == false) goto L17;
            return AndroidUtilities.bold();
        L17:
            if ((this.flags & 2) != 0) goto L19;
        L22:
            return null;
        L19:
            if (FontUtils.isItalicSupported() == false) goto L22;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_ITALIC);
        L24:
            if ((r1 & 4) != 0) goto L52;
            if ((r1 & 2048) != 0) goto L52;
            if ((r1 & 1) == 0) goto L39;
            if ((r1 & 2) == 0) goto L39;
            if (FontUtils.isMediumWeightSupported() == false) goto L39;
            if (FontUtils.isItalicSupported() == false) goto L39;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_MEDIUM_ITALIC);
        L39:
            if ((this.flags & 1) == 0) goto L45;
            if (FontUtils.isMediumWeightSupported() == false) goto L45;
            return AndroidUtilities.bold();
        L45:
            if ((this.flags & 2) != 0) goto L47;
        L50:
            return null;
        L47:
            if (FontUtils.isItalicSupported() == false) goto L50;
            return AndroidUtilities.getTypeface(AndroidUtilities.TYPEFACE_ROBOTO_ITALIC);
        L52:
            return Typeface.MONOSPACE;
        }

        public void merge(TextStyleRun r3) {
            this.flags |= r3.flags;
            if (this.urlEntity != null) goto L8;
            TLRPC.MessageEntity r32 = r3.urlEntity;
            if (r32 == null) goto L9;
            this.urlEntity = r32;
            return;
        L9:
            return;
        }

        public void replace(TextStyleRun r2) {
            this.flags = r2.flags;
            this.urlEntity = r2.urlEntity;
        }
    }

    public TextStyleSpan(TextStyleRun r2) {
        this(r2, 0, 0);
    }

    public TextStyleSpan(TextStyleRun r2, int r3) {
        this(r2, r3, 0);
    }

    public TextStyleSpan(TextStyleRun r1, int r2, int r3) {
        this.style = r1;
        if (r2 <= 0) goto L5;
        this.textSize = r2;
    L5:
        this.color = r3;
    }

    private void applySubSuper(TextPaint r4) {
        if (BitwiseUtils.hasFlag(this.style.flags, 49152) == false) goto L14;
        float r0 = r4.getTextSize();
        r4.setTextSize(0.75f * r0);
        if (BitwiseUtils.hasFlag(this.style.flags, 32768) == false) goto L10;
        r4.baselineShift -= (int) (r0 * 0.35f);
        return;
    L10:
        if (BitwiseUtils.hasFlag(this.style.flags, 16384) == false) goto L13;
        r4.baselineShift += (int) (r0 * 0.12f);
        return;
    L13:
        return;
    }

    public int getStyleFlags() {
        return this.style.flags;
    }

    public TextStyleRun getTextStyleRun() {
        return this.style;
    }

    public boolean isSpoiler() {
        if ((this.style.flags & 256) <= 0) goto L6;
        return true;
    L6:
        return false;
    }

    public void setSpoilerRevealed(boolean r1) {
        TextStyleRun r0 = this.style;
        if (r1 == false) goto L6;
        r0.flags |= 512;
        return;
    L6:
        r0.flags &= -513;
    }

    @Override // android.text.style.CharacterStyle
    public void updateDrawState(TextPaint r2) {
        int r0 = this.textSize;
        if (r0 == 0) goto L5;
        r2.setTextSize(r0);
    L5:
        applySubSuper(r2);
        int r02 = this.color;
        if (r02 == 0) goto L8;
        r2.setColor(r02);
    L8:
        r2.setFlags(r2.getFlags() | 128);
        this.style.applyStyle(r2);
    }

    @Override // android.text.style.MetricAffectingSpan
    public void updateMeasureState(TextPaint r2) {
        int r0 = this.textSize;
        if (r0 == 0) goto L5;
        r2.setTextSize(r0);
    L5:
        applySubSuper(r2);
        r2.setFlags(r2.getFlags() | 128);
        this.style.applyStyle(r2);
    }
}
