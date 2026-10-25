package com.exteragram.messenger.speech.ui;

import android.widget.TextView;
import com.exteragram.messenger.speech.VoiceRecognitionController;
import com.exteragram.messenger.translator.TranslatorUtils;
import com.exteragram.messenger.utils.ui.PopupUtils;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.messenger.Utilities;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.BulletinFactory;

/* loaded from: classes4.dex */
public abstract class RecognitionModelDialogs {

    /* renamed from: com.exteragram.messenger.speech.ui.RecognitionModelDialogs$1, reason: invalid class name */
    public class AnonymousClass1 implements VoiceRecognitionController.DownloadModelCallback {
        final /* synthetic */ AlertDialog val$alert;
        final /* synthetic */ boolean[] val$done;
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ LoadingModelView val$loadingModelView;
        final /* synthetic */ Runnable val$onDownloaded;
        final /* synthetic */ float[] val$progressValue;
        final /* synthetic */ long[] val$start;
        final /* synthetic */ Runnable val$updateProgress;

        /* renamed from: $r8$lambda$wtSpJQ-DGE_ZiiupiOJe_hPsXUc, reason: not valid java name */
        public static /* synthetic */ void m1734$r8$lambda$wtSpJQDGE_ZiiupiOJe_hPsXUc(AlertDialog r0, BaseFragment r1) {
            r0.dismiss();
            BulletinFactory.of(r1).createErrorBulletin(LocaleController.getString(R.string.ModelError)).show();
        }

        /* renamed from: $r8$lambda$zu5DB9QxB0rwh9aFNuA5R18-4NE, reason: not valid java name */
        public static /* synthetic */ void m1735$r8$lambda$zu5DB9QxB0rwh9aFNuA5R184NE(boolean[] r6, LoadingModelView r7, long[] r8, AlertDialog r9, Runnable r10, BaseFragment r11) {
            r6[0] = true;
            r7.setProgress(1.0f);
            if (r8[0] <= 0) goto L5;
            Objects.requireNonNull(r9);
            AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$1$$ExternalSyntheticLambda0(r9), Math.max(0, 1000 - (System.currentTimeMillis() - r8[0])));
        L6:
            r10.run();
            BulletinFactory.of(r11).createSuccessBulletin(LocaleController.getString(R.string.ModelDownloaded)).show();
            return;
        L5:
            r9.dismiss();
            goto L6
        }

        public AnonymousClass1(float[] r1, Runnable r2, boolean[] r3, LoadingModelView r4, long[] r5, AlertDialog r6, Runnable r7, BaseFragment r8) {
            this.val$progressValue = r1;
            this.val$updateProgress = r2;
            this.val$done = r3;
            this.val$loadingModelView = r4;
            this.val$start = r5;
            this.val$alert = r6;
            this.val$onDownloaded = r7;
            this.val$fragment = r8;
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.DownloadModelCallback
        public void onCompleted() {
            AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$1$$ExternalSyntheticLambda1(this.val$done, this.val$loadingModelView, this.val$start, this.val$alert, this.val$onDownloaded, this.val$fragment));
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.DownloadModelCallback
        public void onError(Exception r2) {
            AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$1$$ExternalSyntheticLambda2(this.val$alert, this.val$fragment));
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.DownloadModelCallback
        public void onProgress(float r3) {
            this.val$progressValue[0] = r3;
            AndroidUtilities.cancelRunOnUIThread(this.val$updateProgress);
            AndroidUtilities.runOnUIThread(this.val$updateProgress);
        }
    }

    /* renamed from: com.exteragram.messenger.speech.ui.RecognitionModelDialogs$2, reason: invalid class name */
    public class AnonymousClass2 implements VoiceRecognitionController.DeleteModelCallback {
        final /* synthetic */ BaseFragment val$fragment;
        final /* synthetic */ VoiceRecognitionController.RecognitionModel val$model;
        final /* synthetic */ Utilities.Callback val$onDeleted;

        public static /* synthetic */ void $r8$lambda$9T3ISXKBl4rmyYfiZH3AOIiVt_k(BaseFragment r1) {
            BulletinFactory.of(r1).createErrorBulletin(LocaleController.getString(R.string.RecognitionModelDeleteError)).show();
        }

        public static /* synthetic */ void $r8$lambda$iCzzdvgxoGUtZCylTyNKqb7oGIc(Utilities.Callback r0, VoiceRecognitionController.RecognitionModel r1, BaseFragment r2) {
            r0.run(r1);
            BulletinFactory.of(r2).createSuccessBulletin(LocaleController.getString(R.string.RecognitionModelDeleted)).show();
        }

        public AnonymousClass2(Utilities.Callback r1, VoiceRecognitionController.RecognitionModel r2, BaseFragment r3) {
            this.val$onDeleted = r1;
            this.val$model = r2;
            this.val$fragment = r3;
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.DeleteModelCallback
        public void onCompleted() {
            AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$2$$ExternalSyntheticLambda0(this.val$onDeleted, this.val$model, this.val$fragment));
        }

        @Override // com.exteragram.messenger.speech.VoiceRecognitionController.DeleteModelCallback
        public void onError(Exception r1) {
            AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$2$$ExternalSyntheticLambda1(this.val$fragment));
        }
    }

    public static /* synthetic */ void $r8$lambda$JZVluijCixlfmQt3ZxCjOuc9eSc(VoiceRecognitionController.RecognitionModel r1, Utilities.Callback r2, BaseFragment r3, AlertDialog r4, int r5) {
        VoiceRecognitionController.getInstance().deleteModel("vosk", r1.getLanguage(), new AnonymousClass2(r2, r1, r3));
    }

    public static /* synthetic */ void $r8$lambda$NoVMi7tZnl6JlyDUbhcvGrGZc9Q(LoadingModelView r1, float[] r2) {
        r1.setProgress(r2[0]);
    }

    public static /* synthetic */ void $r8$lambda$eBXgfm79L1j0AVJI557h5mvN69s(List r1, BaseFragment r2, Utilities.Callback r3, int r4) {
        if (r4 >= 0) goto L4;
        return;
    L4:
        if (r4 >= r1.size()) goto L9;
        showDeleteConfirmDialog(r2, (VoiceRecognitionController.RecognitionModel) r1.get(r4), r3);
        return;
    }

    public static /* synthetic */ void $r8$lambda$fjCtNl4Ch8ZiuwuQNilRrGjSNjE(boolean[] r3, long[] r4, AlertDialog r5) {
        if (r3[0] == true) goto L6;
        r4[0] = System.currentTimeMillis();
        r5.show();
        return;
    }

    public static /* synthetic */ void $r8$lambda$tdBu2yICZgtDsWF_TZy6toVAMk0(BaseFragment r9, String r10, Runnable r11, AlertDialog r12, int r13) {
        r9.dismissCurrentDialog();
        LoadingModelView r4 = new LoadingModelView(r9.getContext());
        AlertDialog.Builder r122 = new AlertDialog.Builder(r9.getContext());
        r122.setView(r4);
        AlertDialog r6 = r122.create();
        r6.setCanceledOnTouchOutside(false);
        r6.setCancelable(false);
        boolean[] r3 = {false};
        float[] r1 = {0.0f};
        RecognitionModelDialogs$$ExternalSyntheticLambda3 r2 = new RecognitionModelDialogs$$ExternalSyntheticLambda3(r4, r1);
        long[] r5 = {-1};
        AndroidUtilities.runOnUIThread(new RecognitionModelDialogs$$ExternalSyntheticLambda4(r3, r5, r6), 150);
        VoiceRecognitionController.getInstance().downloadModel("vosk", r10, new AnonymousClass1(r1, r2, r3, r4, r5, r6, r11, r9));
    }

    public static CharSequence getRecognitionLanguageOption(String r2) {
        String r0 = TranslatorUtils.getLanguageTitleSystem(r2);
        String r22 = TranslatorUtils.getLanguageDisplayName(r2);
        if (r22 != null) goto L6;
        return r0;
    L6:
        return r0 + " - " + r22;
    }

    private static void showDeleteConfirmDialog(BaseFragment r3, VoiceRecognitionController.RecognitionModel r4, Utilities.Callback<VoiceRecognitionController.RecognitionModel> r5) {
        if (r3.getContext() == null) goto L10;
        AlertDialog.Builder r0 = new AlertDialog.Builder(r3.getContext());
        r0.setTitle(LocaleController.getString(R.string.DeleteRecognitionModel));
        r0.setSubtitle(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.DeleteRecognitionModelInfo, new Object[]{getRecognitionLanguageOption(r4.getLanguage())})));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.getString(R.string.Delete), new RecognitionModelDialogs$$ExternalSyntheticLambda1(r4, r5, r3));
        AlertDialog r42 = r0.create();
        r3.showDialog(r42);
        TextView r32 = (TextView) r42.getButton(-1);
        if (r32 == null) goto L9;
        r32.setTextColor(Theme.getColor(Theme.key_text_RedBold));
        return;
    L9:
        return;
    }

    public static void showDeleteFlow(BaseFragment r4, List<VoiceRecognitionController.RecognitionModel> r5, Utilities.Callback<VoiceRecognitionController.RecognitionModel> r6) {
        if (r4.getContext() != null) goto L5;
        return;
    L5:
        if (r5.isEmpty() == false) goto L8;
        return;
    L8:
        if (r5.size() != 1) goto L11;
        showDeleteConfirmDialog(r4, r5.get(0), r6);
        return;
    L11:
        ArrayList r0 = new ArrayList(r5.size());
        Iterator<VoiceRecognitionController.RecognitionModel> r1 = r5.iterator();
    L13:
        if (r1.hasNext() == false) goto L15;
        r0.add(getRecognitionLanguageOption(r1.next().getLanguage()));
        goto L13
    L15:
        PopupUtils.showDialogWithoutRadio(r0, LocaleController.getString(R.string.DeleteRecognitionModel), r4.getContext(), new RecognitionModelDialogs$$ExternalSyntheticLambda0(r5, r4, r6));
    }

    public static void showDownloadDialog(BaseFragment r4, String r5, VoiceRecognitionController.RecognitionModel r6, Runnable r7) {
        if (r4.getContext() != null) goto L5;
        return;
    L5:
        AlertDialog.Builder r0 = new AlertDialog.Builder(r4.getContext());
        r0.setTitle(LocaleController.getString(R.string.MissingLanguageModel));
        r0.setSubtitle(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.ModelDownloadInfo, new Object[]{TranslatorUtils.getLanguageTitleSystem(r5)})));
        r0.setNegativeButton(LocaleController.getString(R.string.Cancel), null);
        r0.setPositiveButton(LocaleController.formatString(R.string.ModelDownload, new Object[]{AndroidUtilities.formatFileSize(r6.getSize())}), new RecognitionModelDialogs$$ExternalSyntheticLambda2(r4, r5, r7));
        r4.showDialog(r0.create());
    }
}
