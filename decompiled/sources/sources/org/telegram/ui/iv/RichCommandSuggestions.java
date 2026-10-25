package org.telegram.ui.iv;

import android.view.View;
import android.widget.LinearLayout;
import com.android.dx.io.Opcodes;
import java.util.ArrayList;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.ItemOptions;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.iv.RichCommand;

/* loaded from: classes7.dex */
public class RichCommandSuggestions {
    private RichTextCell backgroundCell;
    private RichTextCell cell;
    private LinearLayout content;
    private final MenuFactory menuFactory;
    private ItemOptions options;
    private final Theme.ResourcesProvider resourcesProvider;
    private ArrayList<RichCommand> shown;

    public interface MenuFactory {
        ItemOptions make(View r1);
    }

    public static /* synthetic */ void $r8$lambda$QXvI27XzMATjKVEZcEs3DkVBG_w(RichCommandSuggestions r0, RichTextCell r1, RichCommand r2, View r3) {
        r0.lambda$populate$1(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$TH8OKJSsWrwpgyo9BLOaRguLmxI(RichCommandSuggestions r0) {
        r0.lambda$show$0();
    }

    public RichCommandSuggestions(MenuFactory r1, Theme.ResourcesProvider r2) {
        this.menuFactory = r1;
        this.resourcesProvider = r2;
    }

    private /* synthetic */ void lambda$populate$1(RichTextCell r1, RichCommand r2, View r3) {
        hide();
        r1.selectCommand(r2);
    }

    private /* synthetic */ void lambda$show$0() {
        this.options = null;
        this.content = null;
        this.shown = null;
        this.cell = null;
        setBackgroundCell(null);
    }

    private void populate(RichTextCell r8, ArrayList<RichCommand> r9) {
        LinearLayout r0 = this.content;
        if (r0 == null) goto L8;
        r0.removeAllViews();
        int r02 = r9.size();
        int r2 = 0;
    L6:
        if (r2 >= r02) goto L10;
        RichCommand r3 = r9.get(r2);
        r2 = r2 + 1;
        RichCommand r32 = r3;
        RichCommand.View r4 = new RichCommand.View(r8.getContext(), r32, this.resourcesProvider);
        r4.setPadding(AndroidUtilities.dp(8.0f), 0, AndroidUtilities.dp(12.0f), 0);
        r4.setBackground(Theme.createRadSelectorDrawable(Theme.getColor(Theme.key_listSelector, this.resourcesProvider), 0, 0));
        r4.setOnClickListener(new RichCommandSuggestions$$ExternalSyntheticLambda1(this, r8, r32));
        this.content.addView(r4, LayoutHelper.createLinear(-1, 48));
        goto L6
    L10:
        return;
    }

    private void setBackgroundCell(RichTextCell r3) {
        RichTextCell r0 = this.backgroundCell;
        if (r0 == r3) goto L12;
        if (r0 == null) goto L7;
        r0.setShowCommandBackground(false);
    L7:
        this.backgroundCell = r3;
        if (r3 == null) goto L11;
        r3.setShowCommandBackground(true);
        return;
    L11:
        return;
    }

    private void show(RichTextCell r3, ArrayList<RichCommand> r4) {
        LinearLayout r0 = new LinearLayout(r3.getContext());
        this.content = r0;
        r0.setOrientation(1);
        populate(r3, r4);
        ItemOptions r32 = this.menuFactory.make(r3.getEditText()).dontFocus().setDimAlpha(0).setDrawScrim(false);
        r32.addView(this.content, LayoutHelper.createLinear(Opcodes.REM_INT_LIT8, -2));
        r32.setMaxHeight(AndroidUtilities.dp(240.0f));
        r32.setGravity(3);
        r32.translate(-AndroidUtilities.dp(12.0f), 0.0f);
        r32.setOnDismiss(new RichCommandSuggestions$$ExternalSyntheticLambda0(this));
        r32.followScrimView();
        r32.show();
        this.options = r32;
    }

    public void hide() {
        setBackgroundCell(null);
        ItemOptions r1 = this.options;
        if (r1 == null) goto L5;
        r1.dismiss();
        this.options = null;
    L5:
        this.content = null;
        this.shown = null;
        this.cell = null;
    }

    public void update(RichTextCell r2, String r3) {
        if (r3 != null) goto L5;
        hide();
        return;
    L5:
        ArrayList<RichCommand> r32 = RichCommand.match(r3);
        if (r32.isEmpty() == false) goto L9;
        hide();
        return;
    L9:
        setBackgroundCell(r2);
        if (this.cell != r2) goto L19;
        if (r32.equals(this.shown) == false) goto L19;
        ItemOptions r0 = this.options;
        if (r0 == null) goto L19;
        if (r0.isShown() == false) goto L19;
        return;
    L19:
        if (this.cell != r2) goto L28;
        ItemOptions r02 = this.options;
        if (r02 == null) goto L28;
        if (r02.isShown() == false) goto L28;
        if (this.content == null) goto L28;
        this.shown = r32;
        populate(r2, r32);
        this.options.reposition();
        return;
    L28:
        hide();
        setBackgroundCell(r2);
        this.cell = r2;
        this.shown = r32;
        show(r2, r32);
    }
}
