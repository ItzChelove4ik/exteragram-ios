package org.telegram.ui.Components.Reactions;

import android.graphics.Paint;
import android.text.Editable;
import android.text.SpannableString;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import okhttp3.internal.url._UrlKt;
import org.telegram.messenger.ChatObject;
import org.telegram.messenger.MediaDataController;
import org.telegram.messenger.UserConfig;
import org.telegram.tgnet.TLRPC;
import org.telegram.tgnet.tl.TL_stories;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.Components.AnimatedEmojiDrawable;
import org.telegram.ui.Components.AnimatedEmojiSpan;
import org.telegram.ui.Components.Premium.LimitReachedBottomSheet;
import org.telegram.ui.Components.Reactions.ReactionsLayoutInBubble;
import org.telegram.ui.LaunchActivity;
import org.telegram.ui.SelectAnimatedEmojiDialog;
import org.telegram.ui.StatisticActivity;

/* loaded from: classes7.dex */
public abstract class ReactionsUtils {
    public static /* synthetic */ void $r8$lambda$Uw3SnrBPny24_CN5weoyOz4uxzo(BaseFragment r1, long r2) {
        r1.presentFragment(StatisticActivity.create(r1.getMessagesController().getChat(Long.valueOf(-r2))));
    }

    public static void addReactionToEditText(TLRPC.TL_availableReaction r4, HashMap<Long, AnimatedEmojiSpan> r5, List<Long> r6, Editable r7, SelectAnimatedEmojiDialog r8, Paint.FontMetricsInt r9) {
        TLRPC.Document r0 = r4.activate_animation;
        long r1 = r0.id;
        AnimatedEmojiSpan r92 = createAnimatedEmojiSpan(r0, Long.valueOf(r1), r9);
        r5.put(Long.valueOf(r1), r92);
        r6.add(Long.valueOf(r1));
        r7.append(createSpannableText(r92, r4.reaction));
        if (r8 == null) goto L6;
        r8.setMultiSelected(Long.valueOf(r1), false);
        return;
    }

    public static void addReactionToEditText(TLRPC.TL_reactionCustomEmoji r2, HashMap<Long, AnimatedEmojiSpan> r3, List<Long> r4, Editable r5, SelectAnimatedEmojiDialog r6, Paint.FontMetricsInt r7) {
        AnimatedEmojiSpan r72 = createAnimatedEmojiSpan(null, Long.valueOf(r2.document_id), r7);
        r3.put(Long.valueOf(r2.document_id), r72);
        r4.add(Long.valueOf(r2.document_id));
        r5.append(createSpannableText(r72, "e"));
        if (r6 == null) goto L6;
        r6.setMultiSelected(Long.valueOf(r2.document_id), false);
        return;
    }

    public static void applyForStoryViews(TLRPC.Reaction r5, TLRPC.Reaction r6, TL_stories.StoryViews r7) {
        if (r7 == null) goto L28;
        int r0 = 0;
        boolean r1 = false;
    L6:
        if (r0 >= r7.reactions.size()) goto L19;
        TLRPC.ReactionCount r2 = r7.reactions.get(r0);
        if (r5 != null) goto L10;
    L14:
        if (r6 == null) goto L18;
        if (compare(r2.reaction, r6) == false) goto L18;
        r2.count++;
        r1 = true;
    L18:
        r0 = r0 + 1;
        goto L6
    L10:
        if (compare(r2.reaction, r5) == false) goto L14;
        int r4 = r2.count - 1;
        r2.count = r4;
        if (r4 > 0) goto L14;
        r7.reactions.remove(r0);
        r0 = r0 - 1;
        goto L18
    L19:
        if (r1 == true) goto L27;
        TLRPC.TL_reactionCount r52 = new TLRPC.TL_reactionCount();
        r52.count = 1;
        r52.reaction = r6;
        r7.reactions.add(r52);
        return;
    L27:
        return;
    }

    public static boolean compare(TLRPC.Reaction r4, TLRPC.Reaction r5) {
        if ((r4 instanceof TLRPC.TL_reactionEmoji) == false) goto L10;
        if ((r5 instanceof TLRPC.TL_reactionEmoji) == false) goto L10;
        if (TextUtils.equals(((TLRPC.TL_reactionEmoji) r4).emoticon, ((TLRPC.TL_reactionEmoji) r5).emoticon) == false) goto L10;
        return true;
    L10:
        if ((r4 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L12;
        return false;
    L12:
        if ((r5 instanceof TLRPC.TL_reactionCustomEmoji) == true) goto L14;
        return false;
    L14:
        if (((TLRPC.TL_reactionCustomEmoji) r4).document_id != ((TLRPC.TL_reactionCustomEmoji) r5).document_id) goto L19;
        return true;
    L19:
        return false;
    }

    public static boolean compare(TLRPC.Reaction r6, ReactionsLayoutInBubble.VisibleReaction r7) {
        if ((r6 instanceof TLRPC.TL_reactionEmoji) == false) goto L10;
        if (r7.documentId != 0) goto L10;
        if (TextUtils.equals(((TLRPC.TL_reactionEmoji) r6).emoticon, r7.emojicon) == false) goto L10;
        return true;
    L10:
        if ((r6 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L16;
        long r4 = r7.documentId;
        if (r4 != 0) goto L14;
        return false;
    L14:
        if (((TLRPC.TL_reactionCustomEmoji) r6).document_id != r4) goto L19;
        return true;
    L19:
        return false;
    L16:
        return false;
    }

    public static AnimatedEmojiSpan createAnimatedEmojiSpan(TLRPC.Document r3, Long r4, Paint.FontMetricsInt r5) {
        if (r3 == null) goto L5;
        AnimatedEmojiSpan r42 = new AnimatedEmojiSpan(r3, 1.0f, r5);
    L6:
        r42.cacheType = AnimatedEmojiDrawable.getCacheTypeForEnterView();
        return r42;
    L5:
        r42 = new AnimatedEmojiSpan(r4.longValue(), 1.0f, r5);
        goto L6
    }

    public static SpannableString createSpannableText(AnimatedEmojiSpan r3, String r4) {
        SpannableString r0 = new SpannableString(r4);
        r0.setSpan(r3, 0, r0.length(), 33);
        return r0;
    }

    public static CharSequence reactionToCharSequence(TLRPC.Reaction r4) {
        if ((r4 instanceof TLRPC.TL_reactionEmoji) == false) goto L7;
        return ((TLRPC.TL_reactionEmoji) r4).emoticon;
    L7:
        if ((r4 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L10;
        SpannableStringBuilder r0 = new SpannableStringBuilder("d");
        r0.setSpan(new AnimatedEmojiSpan(((TLRPC.TL_reactionCustomEmoji) r4).document_id, null), 0, 1, 0);
        return r0;
    L10:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static void showLimitReachedDialogForReactions(long r6, int r8, TL_stories.TL_premium_boostsStatus r9) {
        BaseFragment r1 = LaunchActivity.getLastFragment();
        if (r1 == null) goto L8;
        if (r9 == null) goto L9;
        LimitReachedBottomSheet r0 = new LimitReachedBottomSheet(r1, r1.getContext(), 21, UserConfig.selectedAccount, r1.getResourceProvider());
        r0.setRequiredLvl(r8);
        r0.setBoostsStats(r9, true);
        r0.setDialogId(r6);
        r0.showStatisticButtonInLink(new ReactionsUtils$$ExternalSyntheticLambda0(r1, r6));
        r0.show();
        return;
    L9:
        return;
    }

    public static List<AnimatedEmojiDrawable> startPreloadReactions(TLRPC.Chat r8, TLRPC.ChatFull r9) {
        ArrayList r0 = new ArrayList();
        if (r9 != null) goto L5;
    L32:
        return r0;
    L5:
        if (ChatObject.isChannelAndNotMegaGroup(r8) == false) goto L32;
        TLRPC.ChatReactions r82 = r9.available_reactions;
        if ((r82 instanceof TLRPC.TL_chatReactionsSome) == false) goto L24;
        ArrayList<TLRPC.Reaction> r83 = ((TLRPC.TL_chatReactionsSome) r82).reactions;
        int r92 = r83.size();
        int r2 = 0;
    L10:
        if (r2 >= r92) goto L32;
        TLRPC.Reaction r3 = r83.get(r2);
        r2 = r2 + 1;
        TLRPC.Reaction r32 = r3;
        if ((r32 instanceof TLRPC.TL_reactionEmoji) == false) goto L18;
        TLRPC.TL_availableReaction r33 = MediaDataController.getInstance(UserConfig.selectedAccount).getReactionsMap().get(((TLRPC.TL_reactionEmoji) r32).emoticon);
        if (r33 == null) goto L10;
        AnimatedEmojiDrawable r34 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, AnimatedEmojiDrawable.getCacheTypeForEnterView(), r33.activate_animation);
    L21:
        if (r34 == null) goto L10;
        r0.add(r34);
        r34.addView(null);
        goto L10
    L18:
        if ((r32 instanceof TLRPC.TL_reactionCustomEmoji) == false) goto L20;
        r34 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, AnimatedEmojiDrawable.getCacheTypeForEnterView(), ((TLRPC.TL_reactionCustomEmoji) r32).document_id);
        goto L21
    L20:
        r34 = null;
        goto L21
    L24:
        if ((r82 instanceof TLRPC.TL_chatReactionsAll) == false) goto L32;
        Iterator<TLRPC.TL_availableReaction> r84 = MediaDataController.getInstance(UserConfig.selectedAccount).getEnabledReactionsList().iterator();
    L27:
        if (r84.hasNext() == false) goto L32;
        TLRPC.TL_availableReaction r93 = r84.next();
        if (r93 == null) goto L27;
        AnimatedEmojiDrawable r94 = AnimatedEmojiDrawable.make(UserConfig.selectedAccount, AnimatedEmojiDrawable.getCacheTypeForEnterView(), r93.activate_animation);
        r0.add(r94);
        r94.addView(null);
        goto L27
    }

    public static void stopPreloadReactions(List<AnimatedEmojiDrawable> r2) {
        Iterator<AnimatedEmojiDrawable> r22 = r2.iterator();
    L4:
        if (r22.hasNext() == false) goto L6;
        r22.next().removeView(null);
        goto L4
    }

    public static TLRPC.Reaction toTLReaction(ReactionsLayoutInBubble.VisibleReaction r3) {
        if (r3.emojicon == null) goto L6;
        TLRPC.TL_reactionEmoji r0 = new TLRPC.TL_reactionEmoji();
        r0.emoticon = r3.emojicon;
        return r0;
    L6:
        TLRPC.TL_reactionCustomEmoji r02 = new TLRPC.TL_reactionCustomEmoji();
        r02.document_id = r3.documentId;
        return r02;
    }
}
