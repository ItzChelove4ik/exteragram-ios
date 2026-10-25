package org.telegram.ui.Components.emojiview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.text.TextUtils;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.core.graphics.ColorUtils;
import com.google.android.material.navigation.NavigationBarView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes7.dex */
public class FoundStickerPacksHeaderCell extends FrameLayout implements Theme.Colorable {
    private final ImageView backButton;
    private final TextView headerText;
    private final Theme.ResourcesProvider resourcesProvider;

    public FoundStickerPacksHeaderCell(Context r9, Theme.ResourcesProvider r10) {
        super(r9);
        this.resourcesProvider = r10;
        ImageView r102 = new ImageView(r9);
        this.backButton = r102;
        r102.setScaleType(ImageView.ScaleType.CENTER);
        r102.setImageResource(R.drawable.msg_arrow_back);
        addView(r102, LayoutHelper.createFrame(48, 48.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 6.0f, 0.0f, 0.0f, 0.0f));
        TextView r103 = new TextView(r9);
        this.headerText = r103;
        r103.setText(LocaleController.getString(R.string.EmojiSearchBackToSearch));
        r103.setTextSize(1, 15.0f);
        r103.setTypeface(AndroidUtilities.bold());
        r103.setSingleLine(true);
        r103.setEllipsize(TextUtils.TruncateAt.END);
        addView(r103, LayoutHelper.createFrame(-2, -2.0f, NavigationBarView.ITEM_GRAVITY_START_CENTER, 50.0f, 0.0f, 16.0f, 0.0f));
        updateColors();
    }

    private int getGlassIconColor(float r2) {
        return ColorUtils.setAlphaComponent(Theme.getColor(Theme.key_glass_defaultIcon, this.resourcesProvider), (int) (r2 * 255.0f));
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public /* bridge */ /* synthetic */ int[] getColorKeys() {
        return super.getColorKeys();
    }

    public void setOnBackClickListener(View.OnClickListener r1) {
        this.backButton.setOnClickListener(r1);
    }

    @Override // org.telegram.ui.ActionBar.Theme.Colorable
    public void updateColors() {
        this.headerText.setTextColor(getGlassIconColor(0.6f));
        this.backButton.setColorFilter(new PorterDuffColorFilter(getGlassIconColor(0.6f), PorterDuff.Mode.MULTIPLY));
        this.backButton.setBackground(Theme.createSelectorDrawable(getGlassIconColor(0.1f), 1));
    }
}
