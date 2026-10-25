package org.telegram.ui.Components;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.View;
import java.util.ArrayList;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.AccountInstance;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.Emoji;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessageObject;
import org.telegram.messenger.MessagesController;
import org.telegram.ui.ActionBar.Theme;

/* loaded from: classes3.dex */
public class DialogCellTags {
    private final ArrayList<MessagesController.DialogFilter> filters;
    private Tag moreTags;
    private final View parentView;
    private final ArrayList<Tag> tags;

    /* loaded from: classes7.dex */
    public static class Tag {
        int color;
        public int colorId;
        public int filterId;
        Text text;
        private int textHeight;
        int width;

        private Tag() {
        }

        public static Tag asMore(View r4, int r5) {
            Tag r0 = new Tag();
            r0.filterId = r5;
            r0.text = new Text("+" + r5, 10.0f, AndroidUtilities.bold()).supportAnimatedEmojis(r4);
            r0.width = AndroidUtilities.dp(9.32f) + ((int) r0.text.getCurrentWidth());
            r0.textHeight = (int) r0.text.getHeight();
            r0.color = Theme.getColor(Theme.key_avatar_nameInMessageBlue);
            return r0;
        }

        public static Tag fromFilter(View r4, int r5, MessagesController.DialogFilter r6) {
            Tag r52 = new Tag();
            r52.filterId = r6.id;
            r52.colorId = r6.color;
            String r1 = r6.name;
            if (r1 != null) goto L5;
            r1 = _UrlKt.FRAGMENT_ENCODE_SET;
        L5:
            SpannableStringBuilder r0 = new SpannableStringBuilder(r1.toUpperCase());
            Text r42 = new Text(r0, 10.0f, AndroidUtilities.bold()).supportAnimatedEmojis(r4);
            r52.text = r42;
            Spannable r43 = MessageObject.replaceAnimatedEmoji(Emoji.replaceEmoji(r0, r42.getFontMetricsInt(), false), r6.entities, r52.text.getFontMetricsInt());
            r52.text.setText(r43);
            r52.text.setEmojiCacheType(26);
            r52.width = AndroidUtilities.dp(9.32f) + ((int) r52.text.getCurrentWidth());
            r52.textHeight = (int) r52.text.getHeight();
            int[] r44 = Theme.keys_avatar_nameInMessage;
            r52.color = Theme.getColor(r44[r6.color % r44.length]);
            return r52;
        }

        public void draw(Canvas r12) {
            Paint r0 = Theme.dialogs_tagPaint;
            int r1 = this.color;
            if (Theme.isCurrentThemeDark() == false) goto L5;
            float r2 = 0.2f;
        L6:
            r0.setColor(Theme.multAlpha(r1, r2));
            RectF r02 = AndroidUtilities.rectTmp;
            r02.set(0.0f, 0.0f, this.width, AndroidUtilities.dp(14.66f));
            r12.drawRoundRect(r02, AndroidUtilities.dp(4.0f), AndroidUtilities.dp(4.0f), Theme.dialogs_tagPaint);
            this.text.draw(r12, AndroidUtilities.dp(4.66f), AndroidUtilities.dp(14.66f) / 2.0f, this.color, 1.0f);
            return;
        L5:
            r2 = 0.1f;
            goto L6
        }
    }

    public DialogCellTags(View r2) {
        this.filters = new ArrayList();
        this.tags = new ArrayList();
        this.moreTags = null;
        this.parentView = r2;
    }

    public void draw(Canvas r7, int r8) {
        int r2 = 0;
        r7.clipRect(0, 0, r8, AndroidUtilities.dp(14.66f));
        RectF r1 = AndroidUtilities.rectTmp;
        float r3 = r8;
        r1.set(0.0f, 0.0f, r3, AndroidUtilities.dp(14.66f));
        r7.saveLayerAlpha(r1, 255, 31);
        if (LocaleController.isRTL == false) goto L5;
        r7.translate(r3, 0.0f);
    L5:
        int r82 = r8 - AndroidUtilities.dp(25.0f);
    L7:
        if (r2 >= this.tags.size()) goto L17;
        Tag r0 = this.tags.get(r2);
        r82 = r82 - (r0.width + AndroidUtilities.dp(4.0f));
        if (r82 < 0) goto L17;
        if (LocaleController.isRTL == false) goto L14;
        r7.translate(-r0.width, 0.0f);
        r0.draw(r7);
        r7.translate(-AndroidUtilities.dp(4.0f), 0.0f);
    L15:
        r2 = r2 + 1;
        goto L7
    L14:
        r0.draw(r7);
        r7.translate(r0.width + AndroidUtilities.dp(4.0f), 0.0f);
    L17:
        if (r2 >= this.tags.size()) goto L27;
        int r83 = this.tags.size() - r2;
        Tag r02 = this.moreTags;
        if (r02 != null) goto L21;
    L22:
        this.moreTags = Tag.asMore(this.parentView, r83);
    L23:
        boolean r84 = LocaleController.isRTL;
        Tag r03 = this.moreTags;
        if (r84 == false) goto L26;
        r7.translate(-r03.width, 0.0f);
        this.moreTags.draw(r7);
        r7.translate(-AndroidUtilities.dp(4.0f), 0.0f);
        goto L27
    L26:
        r03.draw(r7);
        r7.translate(this.moreTags.width + AndroidUtilities.dp(4.0f), 0.0f);
        goto L27
    L21:
        if (r02.filterId == r83) goto L23;
    L27:
        r7.restore();
    }

    public boolean isEmpty() {
        return this.tags.isEmpty();
    }

    public boolean update(int r10, int r11, long r12) {
        AccountInstance r0 = AccountInstance.getInstance(r10);
        MessagesController r1 = MessagesController.getInstance(r10);
        if (r1.folderTags == true) goto L5;
    L70:
        boolean r102 = this.tags.isEmpty();
        this.tags.clear();
        return !r102;
    L5:
        if (r0.getUserConfig().isPremium() == false) goto L70;
        ArrayList<MessagesController.DialogFilter> r2 = r1.dialogFilters;
        if (r11 != 7) goto L10;
        MessagesController.DialogFilter r13 = r1.selectedDialogFilter[0];
    L13:
        this.filters.clear();
        if (r11 == 0) goto L17;
        if (r11 == 7) goto L17;
        if (r11 == 8) goto L17;
    L30:
        int r112 = 0;
        boolean r122 = false;
    L32:
        if (r112 >= this.tags.size()) goto L54;
        Tag r132 = this.tags.get(r112);
        int r02 = 0;
    L35:
        if (r02 >= this.filters.size()) goto L40;
        if (this.filters.get(r02).id == r132.filterId) goto L38;
        r02 = r02 + 1;
        goto L35
    L38:
        MessagesController.DialogFilter r03 = this.filters.get(r02);
    L41:
        if (r03 != null) goto L45;
        this.tags.remove(r112);
        r112 = r112 - 1;
    L43:
        r122 = true;
    L53:
        r112 = r112 + 1;
        goto L32
    L45:
        if (r03.color != r132.colorId) goto L52;
        String r14 = r03.name;
        if (r14 == null) goto L53;
        if (r132.text == null) goto L53;
        if (r14.length() == r132.text.getText().length()) goto L53;
    L52:
        this.tags.set(r112, Tag.fromFilter(this.parentView, r10, r03));
        goto L43
    L40:
        r03 = null;
        goto L41
    L54:
        int r113 = 0;
    L55:
        int r133 = this.filters.size();
        ArrayList<MessagesController.DialogFilter> r04 = this.filters;
        if (r113 >= r133) goto L68;
        MessagesController.DialogFilter r134 = r04.get(r113);
        int r05 = 0;
    L59:
        if (r05 >= this.tags.size()) goto L64;
        if (this.tags.get(r05).filterId == r134.id) goto L62;
        r05 = r05 + 1;
        goto L59
    L62:
        Tag r06 = this.tags.get(r05);
    L65:
        if (r06 != null) goto L67;
        this.tags.add(r113, Tag.fromFilter(this.parentView, r10, r134));
        r122 = true;
    L67:
        r113 = r113 + 1;
        goto L55
    L64:
        r06 = null;
        goto L65
    L68:
        r04.clear();
        return r122;
    L17:
        int r114 = 0;
    L19:
        if (r114 >= r2.size()) goto L30;
        MessagesController.DialogFilter r4 = r2.get(r114);
        if (r4 == null) goto L29;
        if (r4 == r13) goto L29;
        if (r4.color < 0) goto L29;
        if (r4.includesDialog(r0, r12) == false) goto L29;
        this.filters.add(r4);
    L29:
        r114 = r114 + 1;
        goto L19
    L10:
        if (r11 != 8) goto L12;
        r13 = r1.selectedDialogFilter[1];
        goto L13
    L12:
        r13 = null;
        goto L13
    }
}
