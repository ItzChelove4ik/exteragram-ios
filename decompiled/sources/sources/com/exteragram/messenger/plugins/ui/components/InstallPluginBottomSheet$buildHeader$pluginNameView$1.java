package com.exteragram.messenger.plugins.ui.components;

import android.app.Activity;
import android.graphics.Canvas;
import android.widget.TextView;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;

@Metadata(d1 = {"\u0000\u001f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0018\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0005H\u0014J\u0010\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\tH\u0014¨\u0006\n"}, d2 = {"com/exteragram/messenger/plugins/ui/components/InstallPluginBottomSheet$buildHeader$pluginNameView$1", "Landroid/widget/TextView;", "onMeasure", _UrlKt.FRAGMENT_ENCODE_SET, "widthMeasureSpec", _UrlKt.FRAGMENT_ENCODE_SET, "heightMeasureSpec", "onDraw", "canvas", "Landroid/graphics/Canvas;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class InstallPluginBottomSheet$buildHeader$pluginNameView$1 extends TextView {
    final /* synthetic */ InstallPluginBottomSheet this$0;

    public InstallPluginBottomSheet$buildHeader$pluginNameView$1(Activity r1, InstallPluginBottomSheet r2) {
        this.this$0 = r2;
        super(r1);
    }

    @Override // android.widget.TextView, android.view.View
    public void onDraw(Canvas r2) {
        int r0 = InstallPluginBottomSheet.access$getNameText$p(this.this$0).beforeDraw(r2);
        super.onDraw(r2);
        InstallPluginBottomSheet.access$getNameText$p(this.this$0).afterDraw(r2, r0);
    }

    @Override // android.widget.TextView, android.view.View
    public void onMeasure(int r2, int r3) {
        super.onMeasure(r2, r3);
        setMeasuredDimension(getMeasuredWidth(), InstallPluginBottomSheet.access$getNameText$p(this.this$0).getHeight(getMeasuredHeight()));
    }
}
