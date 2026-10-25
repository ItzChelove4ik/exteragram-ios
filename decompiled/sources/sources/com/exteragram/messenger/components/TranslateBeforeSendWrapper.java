package com.exteragram.messenger.components;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ui.PopupUtils;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBarMenuSubItem;
import org.telegram.ui.ActionBar.Theme;

@SuppressLint({"ViewConstructor"})
/* loaded from: classes4.dex */
public abstract class TranslateBeforeSendWrapper extends ActionBarMenuSubItem {
    public static /* synthetic */ boolean $r8$lambda$QCxaEPUXYTmiFvwMl471u47oRFU(TranslateBeforeSendWrapper r0, Context r1, View r2) {
        return r0.lambda$new$1(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$Sx7lwR40cmSZI1ngUpquZ04tgzU(TranslateBeforeSendWrapper r0, Context r1, View r2) {
        r0.lambda$new$2(r1, r2);
    }

    /* renamed from: $r8$lambda$aGZbA9h-pkeC_SLO5-YLooSV8U0, reason: not valid java name */
    public static /* synthetic */ void m1232$r8$lambda$aGZbA9hpkeC_SLO5YLooSV8U0(TranslateBeforeSendWrapper r0, int r1) {
        r0.lambda$showDialog$3(r1);
    }

    /* renamed from: $r8$lambda$aYJSxHP-FPSQaOdc-CcaU1_Qucg, reason: not valid java name */
    public static /* synthetic */ void m1233$r8$lambda$aYJSxHPFPSQaOdcCcaU1_Qucg(TranslateBeforeSendWrapper r0, View r1) {
        r0.lambda$new$0(r1);
    }

    public TranslateBeforeSendWrapper(Context r1, boolean r2, boolean r3, Theme.ResourcesProvider r4) {
        super(r1, r2, r3, r4);
        setTextAndIcon(LocaleController.getString(R.string.TranslateTo), R.drawable.msg_translate);
        setSubtext(TranslatorUtils.getSendTargetLanguageTitle());
        setMinimumWidth(AndroidUtilities.dp(196.0f));
        setItemHeight(56);
        setOnClickListener(new TranslateBeforeSendWrapper$$ExternalSyntheticLambda0(this));
        setOnLongClickListener(new TranslateBeforeSendWrapper$$ExternalSyntheticLambda1(this, r1));
        setRightIcon(R.drawable.msg_arrowright);
        getRightIcon().setOnClickListener(new TranslateBeforeSendWrapper$$ExternalSyntheticLambda2(this, r1));
    }

    private /* synthetic */ void lambda$new$0(View r1) {
        onClick();
    }

    private /* synthetic */ boolean lambda$new$1(Context r1, View r2) {
        return showDialog(r1);
    }

    private /* synthetic */ void lambda$new$2(Context r1, View r2) {
        showDialog(r1);
    }

    private /* synthetic */ void lambda$showDialog$3(int r1) {
        TranslatorUtils.setSendTargetLanguage(TranslatorUtils.getTargetLanguageCodeByIndex(r1));
        setSubtext(TranslatorUtils.getSendTargetLanguageTitle());
    }

    private boolean showDialog(Context r5) {
        CharSequence[] r0 = TranslatorUtils.getTargetLanguageTitles();
        CharSequence[] r1 = new CharSequence[r0.length];
        System.arraycopy(r0, 0, r1, 0, r0.length);
        PopupUtils.showDialog(r1, LocaleController.getString(R.string.Language), TranslatorUtils.getSendTargetLanguageIndex(), r5, new TranslateBeforeSendWrapper$$ExternalSyntheticLambda3(this));
        return true;
    }

    public abstract void onClick();
}
