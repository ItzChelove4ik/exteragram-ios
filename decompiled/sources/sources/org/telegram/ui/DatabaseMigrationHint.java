package org.telegram.ui;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RLottieImageView;

/* loaded from: classes3.dex */
public class DatabaseMigrationHint extends FrameLayout {
    LinearLayout container;
    private final int currentAccount;
    TextView description1;
    TextView description2;
    RLottieImageView stickerView;
    TextView title;

    /* renamed from: org.telegram.ui.DatabaseMigrationHint$1, reason: invalid class name */
    /* loaded from: classes6.dex */
    public class AnonymousClass1 implements View.OnTouchListener {
        final /* synthetic */ DatabaseMigrationHint this$0;

        public AnonymousClass1(DatabaseMigrationHint r1) {
            this.this$0 = r1;
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View r1, MotionEvent r2) {
            return true;
        }
    }

    public DatabaseMigrationHint(Context r13, int r14) {
        super(r13);
        this.currentAccount = r14;
        LinearLayout r142 = new LinearLayout(r13);
        this.container = r142;
        r142.setOrientation(1);
        RLottieImageView r143 = new RLottieImageView(r13);
        this.stickerView = r143;
        r143.setAnimation(R.raw.db_migration_placeholder, 150, 150);
        this.stickerView.getAnimatedDrawable().setAutoRepeat(1);
        this.stickerView.playAnimation();
        this.container.addView(this.stickerView, LayoutHelper.createLinear(150, 150, 1));
        TextView r144 = new TextView(r13);
        this.title = r144;
        r144.setTextSize(1, 24.0f);
        this.title.setText(LocaleController.getString(R.string.OptimizingTelegram));
        TextView r145 = this.title;
        int r1 = Theme.key_windowBackgroundWhiteBlackText;
        r145.setTextColor(Theme.getColor(r1));
        this.title.setGravity(1);
        this.container.addView(this.title, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 50, 32, 50, 0));
        TextView r146 = new TextView(r13);
        this.description1 = r146;
        r146.setLineSpacing(AndroidUtilities.dp(2.0f), 1.0f);
        this.description1.setTextSize(1, 14.0f);
        this.description1.setText(LocaleController.getString(R.string.OptimizingTelegramDescription1));
        this.description1.setTextColor(Theme.getColor(r1));
        this.description1.setGravity(1);
        this.container.addView(this.description1, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 36, 20, 36, 0));
        TextView r147 = new TextView(r13);
        this.description2 = r147;
        r147.setTextSize(1, 14.0f);
        this.description2.setText(LocaleController.getString(R.string.OptimizingTelegramDescription2));
        this.description2.setTextColor(Theme.getColor(r1));
        this.description2.setGravity(1);
        this.container.addView(this.description2, LayoutHelper.createLinear(-1, -2, 0.0f, 0, 36, 24, 36, 0));
        addView(this.container, LayoutHelper.createFrame(-1, -2, 16));
        setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundWhite));
        setOnTouchListener(new AnonymousClass1(this));
    }
}
