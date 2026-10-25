package com.exteragram.messenger.components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.ui.Components.ColoredImageSpan;

@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005JR\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\r2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0014\u001a\u00020\u0015H\u0016¨\u0006\u0016"}, d2 = {"Lcom/exteragram/messenger/components/BlendedReplyColorIconSpan;", "Lorg/telegram/ui/Components/ColoredImageSpan;", "drawable", "Landroid/graphics/drawable/Drawable;", "<init>", "(Landroid/graphics/drawable/Drawable;)V", "draw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "text", _UrlKt.FRAGMENT_ENCODE_SET, "start", _UrlKt.FRAGMENT_ENCODE_SET, "end", "x", _UrlKt.FRAGMENT_ENCODE_SET, "top", "y", "bottom", "paint", "Landroid/graphics/Paint;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class BlendedReplyColorIconSpan extends ColoredImageSpan {
    public BlendedReplyColorIconSpan(Drawable r1) {
        super(r1);
    }

    @Override // org.telegram.ui.Components.ColoredImageSpan, android.text.style.ReplacementSpan
    public void draw(Canvas r4, CharSequence r5, int r6, int r7, float r8, int r9, int r10, int r11, Paint r12) {
        setOverrideColor(BlendedReplySpansKt.blendedReplyColor$default(0.0f, 1, null));
        super.draw(r4, r5, r6, r7, r8, r9, r10, r11, r12);
    }
}
