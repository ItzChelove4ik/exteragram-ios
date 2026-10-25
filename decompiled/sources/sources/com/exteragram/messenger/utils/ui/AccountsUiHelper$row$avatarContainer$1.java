package com.exteragram.messenger.utils.ui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.widget.FrameLayout;
import com.exteragram.messenger.ExteraConfig;
import kotlin.Metadata;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;

@Metadata(d1 = {"\u0000\u001d\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\u00020\u0001J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"com/exteragram/messenger/utils/ui/AccountsUiHelper$row$avatarContainer$1", "Landroid/widget/FrameLayout;", "selectedPaint", "Landroid/graphics/Paint;", "dispatchDraw", _UrlKt.FRAGMENT_ENCODE_SET, "canvas", "Landroid/graphics/Canvas;", "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
/* loaded from: classes4.dex */
public final class AccountsUiHelper$row$avatarContainer$1 extends FrameLayout {
    final /* synthetic */ Theme.ResourcesProvider $resourcesProvider;
    final /* synthetic */ boolean $selected;
    private final Paint selectedPaint;

    public AccountsUiHelper$row$avatarContainer$1(Context r1, boolean r2, Theme.ResourcesProvider r3) {
        this.$selected = r2;
        this.$resourcesProvider = r3;
        super(r1);
        this.selectedPaint = new Paint(1);
    }

    @Override // android.view.ViewGroup, android.view.View
    public void dispatchDraw(Canvas r10) {
        if (this.$selected == false) goto L5;
        this.selectedPaint.setStyle(Paint.Style.STROKE);
        this.selectedPaint.setStrokeWidth(AndroidUtilities.dp(1.33f));
        this.selectedPaint.setColor(Theme.getColor(Theme.key_featuredStickers_addButton, this.$resourcesProvider));
        float r6 = ExteraConfig.getAvatarCorners$default(34.0f, false, false, 6, null);
        float r2 = AndroidUtilities.dp(1.0f);
        Canvas r1 = r10;
        r1.drawRoundRect(r2, r2, getWidth() - r2, getHeight() - r2, r6, r6, this.selectedPaint);
    L6:
        super.dispatchDraw(r1);
        return;
    L5:
        r1 = r10;
        goto L6
    }
}
