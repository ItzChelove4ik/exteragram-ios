package com.exteragram.messenger.plugins.ui.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import kotlin.Metadata;
import kotlin.jvm.JvmOverloads;
import kotlin.jvm.internal.DefaultConstructorMarker;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BackupImageView;
import org.telegram.ui.Components.EffectsTextView;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.ViewHelper;

@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\r\n\u0000\n\u0002\u0010\u000b\n\u0000\b\u0007\u0018\u00002\u00020\u0001B\u001d\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\b\u0002\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011J\b\u0010\u0012\u001a\u00020\u0013H\u0016R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0014"}, d2 = {"Lcom/exteragram/messenger/plugins/ui/components/EmptyPluginsView;", "Landroid/widget/FrameLayout;", "context", "Landroid/content/Context;", "resourcesProvider", "Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;", "<init>", "(Landroid/content/Context;Lorg/telegram/ui/ActionBar/Theme$ResourcesProvider;)V", "textView", "Lorg/telegram/ui/Components/EffectsTextView;", "backupImageView", "Lorg/telegram/ui/Components/BackupImageView;", "getBackupImageView", "()Lorg/telegram/ui/Components/BackupImageView;", "setText", _UrlKt.FRAGMENT_ENCODE_SET, "text", _UrlKt.FRAGMENT_ENCODE_SET, "hasOverlappingRendering", _UrlKt.FRAGMENT_ENCODE_SET, "TMessagesProj"}, k = 1, mv = {2, 2, 0}, xi = 48)
@SuppressLint({"ClickableViewAccessibility"})
/* loaded from: classes4.dex */
public final class EmptyPluginsView extends FrameLayout {
    private final BackupImageView backupImageView;
    private final EffectsTextView textView;

    public static boolean $r8$lambda$Vd6WFN2r51k6Ofv4Z8vIUXzLhEQ(View r0, MotionEvent r1) {
        return true;
    }

    @JvmOverloads
    public EmptyPluginsView(Context r3) {
        this(r3, null, 2, null);
    }

    @JvmOverloads
    public EmptyPluginsView(Context r11, Theme.ResourcesProvider r12) {
        super(r11);
        LinearLayout r0 = new LinearLayout(r11);
        ViewHelper.setPadding(r0, 20.0f, 0.0f, 20.0f, 0.0f);
        r0.setGravity(1);
        r0.setClipChildren(false);
        r0.setClipToPadding(false);
        r0.setOrientation(1);
        BackupImageView r2 = new BackupImageView(r11);
        this.backupImageView = r2;
        r0.addView(r2, LayoutHelper.createLinear(100, 100, 17, 0.0f, 0.0f, 0.0f, 20.0f));
        EffectsTextView r22 = new EffectsTextView(r11);
        r22.setTextSize(1, 14.0f);
        r22.setTextColor(Theme.getColor(Theme.key_emptyListPlaceholder, r12));
        r22.setGravity(1);
        r22.setText(LocaleController.getString(R.string.NoResult));
        r22.setTypeface(AndroidUtilities.regular());
        r22.setMovementMethod(new AndroidUtilities.LinkMovementMethodMy());
        r22.setLinkTextColor(Theme.getColor(Theme.key_windowBackgroundWhiteLinkText));
        this.textView = r22;
        r0.addView(r22, LayoutHelper.createLinear(-2, -2, 17));
        addView(r0, LayoutHelper.createFrame(-2, -2, 17));
        setOnTouchListener(new EmptyPluginsView$$ExternalSyntheticLambda0());
    }

    public /* synthetic */ EmptyPluginsView(Context r1, Theme.ResourcesProvider r2, int r3, DefaultConstructorMarker r4) {
        if ((r3 & 2) == 0) goto L5;
        r2 = null;
    L5:
        this(r1, r2);
    }

    public final BackupImageView getBackupImageView() {
        return this.backupImageView;
    }

    @Override // android.view.View
    public boolean hasOverlappingRendering() {
        return false;
    }

    public final void setText(CharSequence r1) {
        this.textView.setText(r1);
    }
}
