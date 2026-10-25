package org.telegram.ui.iv;

import android.content.Context;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Space;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.tgnet.TLObject;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.LayoutHelper;

/* loaded from: classes7.dex */
public class RichCommand {
    private static ArrayList<RichCommand> cmds;
    public final List<String> commands;
    public final int icon;
    public final String name;

    public static class View extends LinearLayout implements Theme.Colorable {
        private final RichCommand cmd;
        private final ImageView iconView;
        private final Theme.ResourcesProvider resourcesProvider;
        private final TextView textView;
        private final TextView textView2;

        public View(Context r13, RichCommand r14, Theme.ResourcesProvider r15) {
            super(r13);
            this.cmd = r14;
            this.resourcesProvider = r15;
            setOrientation(0);
            ImageView r0 = new ImageView(r13);
            this.iconView = r0;
            r0.setScaleType(ImageView.ScaleType.CENTER);
            addView(r0, LayoutHelper.createLinear(42, 42, 19, 0, 0, 0, 0));
            TextView r1 = new TextView(r13);
            this.textView = r1;
            r1.setTextSize(1, 16.0f);
            TextView r4 = new TextView(r13);
            this.textView2 = r4;
            r4.setTextSize(1, 16.0f);
            r4.setGravity(5);
            addView(r1, LayoutHelper.createLinear(-2, -2, 19, 8, 0, 0, 0));
            addView(new Space(r13), LayoutHelper.createLinear(0, -2, 1.0f, 119));
            addView(r4, LayoutHelper.createLinear(-2, -2, 21, 8, 0, 0, 0));
            r0.setImageResource(r14.icon);
            r1.setText(r14.name);
            r4.setText(r14.commands.get(0));
            updateColors();
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public /* bridge */ /* synthetic */ int[] getColorKeys() {
            return super.getColorKeys();
        }

        @Override // android.widget.LinearLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(48.0f), TLObject.FLAG_30));
        }

        @Override // org.telegram.ui.ActionBar.Theme.Colorable
        public void updateColors() {
            ImageView r0 = this.iconView;
            int r2 = Theme.key_windowBackgroundWhiteBlackText;
            r0.setColorFilter(new PorterDuffColorFilter(Theme.getColor(r2, this.resourcesProvider), PorterDuff.Mode.SRC_IN));
            this.textView.setTextColor(Theme.getColor(r2, this.resourcesProvider));
            this.textView2.setTextColor(Theme.multAlpha(Theme.getColor(r2, this.resourcesProvider), 0.75f));
        }
    }

    public RichCommand(int r1, String r2, String... r3) {
        this.icon = r1;
        this.name = r2;
        this.commands = Arrays.asList(r3);
    }

    public static ArrayList<RichCommand> get() {
        ArrayList<RichCommand> r0 = cmds;
        if (r0 == null) goto L5;
        return r0;
    L5:
        ArrayList<RichCommand> r02 = new ArrayList();
        cmds = r02;
        r02.add(new RichCommand(R.drawable.iv_h1, LocaleController.getString(R.string.ArticleHeading1), new String[]{"#", "/h1", "/header", "/title", "/heading"}));
        cmds.add(new RichCommand(R.drawable.iv_h2, LocaleController.getString(R.string.ArticleHeading2), new String[]{"##", "/h2"}));
        cmds.add(new RichCommand(R.drawable.iv_h3, LocaleController.getString(R.string.ArticleHeading3), new String[]{"###", "/h3"}));
        cmds.add(new RichCommand(R.drawable.iv_h4, LocaleController.getString(R.string.ArticleHeading4), new String[]{"####", "/h4"}));
        cmds.add(new RichCommand(R.drawable.iv_h5, LocaleController.getString(R.string.ArticleHeading5), new String[]{"#####", "/h5"}));
        cmds.add(new RichCommand(R.drawable.iv_h6, LocaleController.getString(R.string.ArticleHeading6), new String[]{"######", "/h6"}));
        cmds.add(new RichCommand(R.drawable.iv_quote, LocaleController.getString(R.string.ArticleQuote), new String[]{"|", "/quote"}));
        cmds.add(new RichCommand(R.drawable.iv_pullquote, LocaleController.getString(R.string.ArticlePullquote), new String[]{"/pullquote"}));
        cmds.add(new RichCommand(R.drawable.iv_code, LocaleController.getString(R.string.ArticleCode), new String[]{"```", "/code", "/pre", "/preformatted"}));
        cmds.add(new RichCommand(R.drawable.iv_footer, LocaleController.getString(R.string.ArticleFooter), new String[]{"/footer"}));
        cmds.add(new RichCommand(R.drawable.iv_list, LocaleController.getString(R.string.ArticleCommandList), new String[]{"-", "/list"}));
        cmds.add(new RichCommand(R.drawable.iv_ordered_list, LocaleController.getString(R.string.ArticleCommandOrderedList), new String[]{"1."}));
        cmds.add(new RichCommand(R.drawable.iv_todo, LocaleController.getString(R.string.ArticleListChecklist), new String[]{_UrlKt.PATH_SEGMENT_ENCODE_SET_URI, "/todo", "/checklist"}));
        cmds.add(new RichCommand(R.drawable.iv_details, LocaleController.getString(R.string.ArticleCommandToggle), new String[]{">", "/toggle", "/details"}));
        cmds.add(new RichCommand(R.drawable.iv_button, LocaleController.getString(R.string.RichEditorButton), new String[]{"/button"}));
        cmds.add(new RichCommand(R.drawable.iv_table, LocaleController.getString(R.string.ArticleCommandTable), new String[]{"/table"}));
        cmds.add(new RichCommand(R.drawable.iv_math, LocaleController.getString(R.string.ArticleCommandMath), new String[]{"/math", "/latex", "/expression"}));
        cmds.add(new RichCommand(R.drawable.iv_divider, LocaleController.getString(R.string.ArticleCommandDivider), new String[]{"---"}));
        cmds.add(new RichCommand(R.drawable.iv_media, LocaleController.getString(R.string.ArticleCommandImage), new String[]{"/image", "/pic", "/picture", "/photo", "/img", "/media"}));
        cmds.add(new RichCommand(R.drawable.iv_media, LocaleController.getString(R.string.ArticleCommandVideo), new String[]{"/video", "/vid", "/media"}));
        cmds.add(new RichCommand(R.drawable.iv_audio, LocaleController.getString(R.string.ArticleCommandAudio), new String[]{"/audio", "/music", "/media"}));
        cmds.add(new RichCommand(R.drawable.iv_location, LocaleController.getString(R.string.ArticleCommandMap), new String[]{"/map", "/location", "/venue"}));
        return cmds;
    }

    public static ArrayList<RichCommand> match(String r6) {
        if (r6 != null) goto L4;
        String r62 = _UrlKt.FRAGMENT_ENCODE_SET;
    L6:
        if (r62.startsWith("/") == false) goto L8;
        r62 = r62.substring(1);
    L8:
        String r63 = r62.toLowerCase();
        ArrayList<RichCommand> r0 = new ArrayList();
        ArrayList<RichCommand> r1 = get();
        int r2 = r1.size();
        int r3 = 0;
    L9:
        if (r3 >= r2) goto L15;
        RichCommand r4 = r1.get(r3);
        r3 = r3 + 1;
        RichCommand r42 = r4;
        if (r63.isEmpty() == true) goto L14;
        if (r42.matches(r63) == false) goto L9;
    L14:
        r0.add(r42);
        goto L9
    L15:
        return r0;
    L4:
        r62 = r6.trim();
        goto L6
    }

    public boolean matches(String r7) {
        String[] r0 = this.name.toLowerCase().split(" ");
        int r1 = r0.length;
        int r3 = 0;
    L4:
        if (r3 >= r1) goto L9;
        if (r0[r3].startsWith(r7) == true) goto L7;
        r3 = r3 + 1;
        goto L4
    L7:
        return true;
    L9:
        Iterator<String> r6 = this.commands.iterator();
    L11:
        if (r6.hasNext() == false) goto L18;
        String r02 = r6.next().toLowerCase();
        if (r02.startsWith("/") == false) goto L16;
        r02 = r02.substring(1);
    L16:
        if (r02.startsWith(r7) == false) goto L11;
        return true;
    L18:
        return false;
    }
}
