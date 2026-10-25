package org.telegram.ui.bots;

import android.app.DownloadManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.text.SpannableString;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.interpolator.view.animation.FastOutSlowInInterpolator;
import com.google.android.gms.cast.HlsSegmentFormat;
import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.text.Typography;
import okhttp3.internal.url._UrlKt;
import org.json.JSONObject;
import org.telegram.messenger.AndroidUtilities;
import org.telegram.messenger.ApplicationLoader;
import org.telegram.messenger.FileLog;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.MessagesController;
import org.telegram.messenger.NotificationCenter;
import org.telegram.messenger.R;
import org.telegram.messenger.UserObject;
import org.telegram.messenger.Utilities;
import org.telegram.tgnet.TLObject;
import org.telegram.tgnet.TLRPC;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Components.AnimatedFloat;
import org.telegram.ui.Components.AnimatedTextView;
import org.telegram.ui.Components.Bulletin;
import org.telegram.ui.Components.CircularProgressDrawable;
import org.telegram.ui.Components.CubicBezierInterpolator;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.LoadingSpan;
import org.telegram.ui.Components.RLottieDrawable;
import org.telegram.ui.LaunchActivity;

/* loaded from: classes7.dex */
public class BotDownloads {
    private static HashMap<String, Pair<String, Long>> cachedMimeAndSizes;
    private static final HashMap<Pair<Integer, Long>, BotDownloads> instances = null;
    public final long botId;
    public final Context context;
    public final int currentAccount;
    private FileDownload currentFile;
    public final DownloadManager downloadManager;
    private final ArrayList<FileDownload> files;

    /* renamed from: org.telegram.ui.bots.BotDownloads$1, reason: invalid class name */
    public class AnonymousClass1 extends AsyncTask<String, Void, String> {
        String mime;
        long size;
        final /* synthetic */ String val$url;
        final /* synthetic */ Utilities.Callback2 val$whenDone;

        public AnonymousClass1(String r1, Utilities.Callback2 r2) {
            this.val$url = r1;
            this.val$whenDone = r2;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ String doInBackground(String[] r1) {
            return doInBackground2(r1);
        }

        /* renamed from: doInBackground, reason: avoid collision after fix types in other method */
        public String doInBackground2(String... r6) {
            HttpURLConnection r1 = (HttpURLConnection) new URL(this.val$url).openConnection();     // Catch: Exception -> L6
            r1.setRequestMethod("GET");     // Catch: Exception -> L6
            r1.setRequestProperty("Accept-Encoding", "identity");     // Catch: Exception -> L6
            r1.setConnectTimeout(1000);     // Catch: Exception -> L6
            r1.setReadTimeout(1000);     // Catch: Exception -> L6
            r1.setUseCaches(false);     // Catch: Exception -> L6
            r1.setDefaultUseCaches(false);     // Catch: Exception -> L6
            r1.setDoOutput(false);     // Catch: Exception -> L6
            r1.setDoInput(false);     // Catch: Exception -> L6
            r1.getResponseCode();     // Catch: Exception -> L6
            this.size = r1.getContentLengthLong();     // Catch: Exception -> L6
            String r3 = r1.getContentType();     // Catch: Exception -> L6
            this.mime = r3;     // Catch: Exception -> L6
            if (r3.contains("; ") == false) goto L8;
            String r32 = this.mime;     // Catch: Exception -> L6
            this.mime = r32.substring(0, r32.indexOf("; "));     // Catch: Exception -> L6
        L8:
            r1.getInputStream().close();     // Catch: Exception -> L6
            return null;
        L6:
            e = move-exception;
            FileLog.e(e);
            return null;
        }

        @Override // android.os.AsyncTask
        public /* bridge */ /* synthetic */ void onPostExecute(String r1) {
            onPostExecute2(r1);
        }

        /* renamed from: onPostExecute, reason: avoid collision after fix types in other method */
        public void onPostExecute2(String r6) {
            BotDownloads.m21319$$Nest$sfgetcachedMimeAndSizes().put(this.val$url, new Pair(this.mime, Long.valueOf(this.size)));
            Utilities.Callback2 r62 = this.val$whenDone;
            if (r62 == null) goto L6;
            r62.run(this.mime, Long.valueOf(this.size));
            return;
        }
    }

    public static class DownloadBulletin extends Bulletin.ButtonLayout {
        public final BackgroundDrawable background;
        private int currentButtonType;
        private FileDownload file;
        private final ImageView imageView;
        private final Theme.ResourcesProvider resourcesProvider;
        public final StatusDrawable status;
        private final TextView subtitleView;
        private final LinearLayout textLayout;
        private final TextView titleView;

        public static class BackgroundDrawable extends Drawable {
            private boolean arrow;
            private int arrowMargin;
            private final AnimatedFloat arrowProgress;
            private final AnimatedFloat arrowX;
            private final Paint paint;
            private final Path path;
            private final int r;
            private final RectF rect;

            public BackgroundDrawable(int r11) {
                this.paint = new Paint(1);
                this.rect = new RectF();
                Path r0 = new Path();
                this.path = r0;
                BotDownloads$DownloadBulletin$BackgroundDrawable$$ExternalSyntheticLambda0 r2 = new BotDownloads$DownloadBulletin$BackgroundDrawable$$ExternalSyntheticLambda0(this);
                CubicBezierInterpolator r7 = CubicBezierInterpolator.EASE_OUT_QUINT;
                this.arrowProgress = new AnimatedFloat(r2, 0, 320, r7);
                this.arrowX = new AnimatedFloat(new BotDownloads$DownloadBulletin$BackgroundDrawable$$ExternalSyntheticLambda0(this), 0, 320, r7);
                this.r = r11;
                r0.moveTo(-AndroidUtilities.dp(6.5f), 0.0f);
                r0.lineTo(AndroidUtilities.dp(6.5f), 0.0f);
                r0.lineTo(0.0f, -AndroidUtilities.dp(6.16f));
                r0.close();
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r6) {
                this.rect.set(getBounds());
                this.rect.inset(AndroidUtilities.dp(8.0f), AndroidUtilities.dp(8.0f));
                RectF r0 = this.rect;
                int r2 = this.r;
                r6.drawRoundRect(r0, r2, r2, this.paint);
                float r02 = this.arrowProgress.set(this.arrow);
                float r22 = (this.rect.right + AndroidUtilities.dp(8.0f)) - this.arrowX.set(this.arrowMargin);
                if (r02 <= 0.0f) goto L6;
                r6.save();
                r6.translate(r22, AndroidUtilities.dp(8.0f) + (AndroidUtilities.dp(6.16f) * (1.0f - r02)));
                r6.drawPath(this.path, this.paint);
                r6.restore();
                return;
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            public void setArrow(int r2) {
                if (r2 < 0) goto L4;
                boolean r0 = true;
            L5:
                this.arrow = r0;
                if (r0 == false) goto L8;
                this.arrowMargin = r2;
            L8:
                invalidateSelf();
                return;
            L4:
                r0 = false;
                goto L5
            }

            public BackgroundDrawable setColor(int r2) {
                this.paint.setColor(r2);
                return this;
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }
        }

        public static class StatusDrawable extends Drawable {
            private AnimatedFloat animatedDone;
            private AnimatedFloat animatedHasPercent;
            private AnimatedFloat animatedProgress;
            private boolean cancelled;
            private final Drawable doc;
            private boolean done;
            private RLottieDrawable doneDrawable;
            private boolean hasPercent;
            private float progress;
            private final RectF rect;
            private final long start;
            private final Paint strokePaint;
            private final View view;

            public StatusDrawable(Context r12, View r13) {
                Paint r0 = new Paint(1);
                this.strokePaint = r0;
                this.rect = new RectF();
                this.done = false;
                BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0 r3 = new BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0(this);
                CubicBezierInterpolator r10 = CubicBezierInterpolator.EASE_OUT_QUINT;
                this.animatedHasPercent = new AnimatedFloat(r3, 0, 320, r10);
                this.animatedProgress = new AnimatedFloat(new BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0(this), 0, 320, r10);
                this.animatedDone = new AnimatedFloat(new BotDownloads$DownloadBulletin$StatusDrawable$$ExternalSyntheticLambda0(this), 0, 320, r10);
                this.view = r13;
                this.start = System.currentTimeMillis();
                this.doc = r12.getResources().getDrawable(R.drawable.search_files_filled).mutate();
                r0.setStyle(Paint.Style.STROKE);
                r0.setStrokeWidth(AndroidUtilities.dp(2.0f));
                r0.setStrokeCap(Paint.Cap.ROUND);
                r0.setStrokeJoin(Paint.Join.ROUND);
            }

            @Override // android.graphics.drawable.Drawable
            public void draw(Canvas r20) {
                Canvas r1 = r20;
                Rect r2 = getBounds();
                int r7 = r2.centerX();
                int r8 = r2.centerY();
                float r9 = this.animatedDone.set(this.done);
                if (r9 >= 1.0f) goto L9;
                float r3 = 1.0f - r9;
                float r4 = (r3 * 0.4f) + 0.6f;
                r1.save();
                float r5 = r7;
                float r6 = r8;
                r1.scale(r4, r4, r5, r6);
                Drawable r42 = this.doc;
                float r17 = 255.0f;
                r42.setBounds(r7 - (r42.getIntrinsicWidth() / 2), r8 - (this.doc.getIntrinsicHeight() / 2), (this.doc.getIntrinsicWidth() / 2) + r7, (this.doc.getIntrinsicHeight() / 2) + r8);
                this.doc.setAlpha((int) (r3 * 255.0f));
                this.doc.draw(r1);
                float r22 = AndroidUtilities.dp(14.0f);
                this.strokePaint.setColor(Theme.multAlpha(-1, 0.2f * r3));
                r1.drawCircle(r5, r6, r22, this.strokePaint);
                float r11 = r3 * 1.0f;
                this.strokePaint.setColor(Theme.multAlpha(-1, r11));
                float r18 = 0.4f;
                this.rect.set(r5 - r22, r6 - r22, r5 + r22, r6 + r22);
                float r12 = this.animatedHasPercent.set(this.hasPercent);
                this.strokePaint.setColor(Theme.multAlpha(-1, (r3 * 0.15f) * (1.0f - r12)));
                r1.drawArc(this.rect, (-(((((System.currentTimeMillis() - this.start) % 600) / 600.0f) - 1.0f) * 360.0f)) - 90.0f, -90.0f, false, this.strokePaint);
                float r13 = ((System.currentTimeMillis() - this.start) * 0.45f) % 5400.0f;
                float r23 = Math.max(0.0f, ((1520.0f * r13) / 5400.0f) - 20.0f);
                int r32 = 0;
            L6:
                if (r32 >= 4) goto L8;
                FastOutSlowInInterpolator r43 = CircularProgressDrawable.interpolator;
                r43.getInterpolation((r13 - (r32 * 1350)) / 667.0f);
                r23 = r23 + (r43.getInterpolation((r13 - (r5 + 667)) / 667.0f) * 250.0f);
                r32 = r32 + 1;
                goto L6
            L8:
                this.strokePaint.setColor(Theme.multAlpha(-1, r11));
                r1 = r20;
                r1.drawArc(this.rect, (-90.0f) - r23, (Math.max(0.02f, this.animatedProgress.set(this.progress)) * (-360.0f)) * r12, false, this.strokePaint);
                invalidateSelf();
                r1.restore();
            L11:
                if (r9 <= 0.0f) goto L23;
                float r122 = (r9 * r18) + 0.6f;
                if (this.cancelled == false) goto L15;
                r1.save();
                r1.scale(r122, r122, r7, r8);
            L15:
                RLottieDrawable r24 = this.doneDrawable;
                if (r24 == null) goto L19;
                r24.setBounds(r7 - (r24.getIntrinsicWidth() / 2), r8 - (this.doneDrawable.getIntrinsicHeight() / 2), r7 + (this.doneDrawable.getIntrinsicWidth() / 2), r8 + (this.doneDrawable.getIntrinsicHeight() / 2));
                this.doneDrawable.setAlpha((int) (r9 * r17));
                this.doneDrawable.draw(r1);
            L19:
                if (this.cancelled == false) goto L24;
                r1.restore();
                return;
            L24:
                return;
            L23:
                return;
            L9:
                r18 = 0.4f;
                r17 = 255.0f;
                goto L11
            }

            @Override // android.graphics.drawable.Drawable
            public int getIntrinsicHeight() {
                return AndroidUtilities.dp(40.0f);
            }

            @Override // android.graphics.drawable.Drawable
            public int getIntrinsicWidth() {
                return AndroidUtilities.dp(40.0f);
            }

            @Override // android.graphics.drawable.Drawable
            public int getOpacity() {
                return -2;
            }

            public void reset() {
                AnimatedFloat r0 = this.animatedDone;
                this.done = false;
                r0.set(false, true);
                this.cancelled = false;
                RLottieDrawable r02 = this.doneDrawable;
                if (r02 == null) goto L5;
                r02.recycle(true);
                this.doneDrawable = null;
            L5:
                AnimatedFloat r03 = this.animatedHasPercent;
                this.hasPercent = false;
                r03.set(false, true);
            }

            @Override // android.graphics.drawable.Drawable
            public void setAlpha(int r1) {
            }

            @Override // android.graphics.drawable.Drawable
            public void setColorFilter(ColorFilter r1) {
            }

            public void setDone(boolean r6) {
                if (this.done == true) goto L14;
                this.done = true;
                this.cancelled = r6;
                if (r6 == false) goto L8;
                int r2 = R.raw.error;
            L9:
                RLottieDrawable r1 = new RLottieDrawable(r2, AndroidUtilities.dp(40.0f), AndroidUtilities.dp(40.0f));
                this.doneDrawable = r1;
                r1.setMasterParent(this.view);
                this.doneDrawable.setAllowDecodeSingleFrame(true);
                this.doneDrawable.start();
                if (r6 == true) goto L13;
                this.progress = 1.0f;
                return;
            L13:
                return;
            L8:
                r2 = R.raw.contact_check;
                goto L9
            }

            public void setProgress(Pair<Long, Long> r5) {
                if (r5 != null) goto L4;
            L6:
                boolean r0 = false;
            L7:
                this.hasPercent = r0;
                if (r0 == false) goto L10;
                this.progress = Utilities.clamp(((Long) r5.first).longValue() / ((Long) r5.second).longValue(), 1.0f, 0.0f);
            L10:
                invalidateSelf();
                return;
            L4:
                if (((Long) r5.second).longValue() <= 0) goto L6;
                r0 = true;
                goto L7
            }
        }

        public static /* synthetic */ void $r8$lambda$IWY8qx1dd2fOpmJq0W1fYLDbKyA(DownloadBulletin r0) {
            r0.lambda$setButton$1();
        }

        public static /* synthetic */ void $r8$lambda$RkDLfBfCbuHBru1kNa9lrIw7xgA(DownloadBulletin r0) {
            r0.lambda$setButton$0();
        }

        public DownloadBulletin(Context r13, Theme.ResourcesProvider r14) {
            super(r13, r14);
            this.currentButtonType = 0;
            this.resourcesProvider = r14;
            BackgroundDrawable r0 = new BackgroundDrawable(AndroidUtilities.dp(10.0f)).setColor(Theme.getColor(Theme.key_undo_background, r14));
            this.background = r0;
            setBackground(r0);
            ImageView r02 = new ImageView(r13);
            this.imageView = r02;
            r02.setScaleType(ImageView.ScaleType.CENTER);
            StatusDrawable r1 = new StatusDrawable(r13, r02);
            this.status = r1;
            r02.setImageDrawable(r1);
            addView(r02, LayoutHelper.createFrame(40, 40.0f, 23, 7.0f, 0.0f, 0.0f, 0.0f));
            LinearLayout r03 = new LinearLayout(r13);
            this.textLayout = r03;
            r03.setOrientation(1);
            addView(r03, LayoutHelper.createFrame(-1, -2.0f, 23, 54.0f, 0.0f, 0.0f, 0.0f));
            TextView r2 = new TextView(r13);
            this.titleView = r2;
            r2.setTextSize(1, 14.0f);
            int r3 = Theme.key_undo_infoColor;
            r2.setTextColor(Theme.getColor(r3, r14));
            r2.setTypeface(AndroidUtilities.bold());
            r03.addView(r2, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 2));
            TextView r22 = new TextView(r13);
            this.subtitleView = r22;
            r22.setTextSize(1, 13.0f);
            r22.setTextColor(Theme.getColor(r3, r14));
            r03.addView(r22, LayoutHelper.createLinear(-1, -2, 55, 0, 0, 0, 0));
        }

        private /* synthetic */ void lambda$setButton$0() {
            Bulletin r0 = getBulletin();
            if (r0 == null) goto L5;
            r0.setDuration(2750);
            r0.setCanHide(true);
        L5:
            FileDownload r2 = this.file;
            if (r2 == null) goto L9;
            r2.cancel();
            return;
        }

        private /* synthetic */ void lambda$setButton$1() {
            Bulletin r0 = getBulletin();
            if (r0 == null) goto L5;
            r0.hide();
        L5:
            FileDownload r1 = this.file;
            if (r1 == null) goto L9;
            r1.open();
            return;
        }

        private void setButton(int r4) {
            if (this.currentButtonType == r4) goto L24;
            this.currentButtonType = r4;
            if (r4 != 0) goto L10;
            setButton(null);
            return;
        L10:
            if (r4 != 1) goto L17;
            Bulletin.UndoButton r42 = new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(LocaleController.getString(R.string.BotFileDownloadCancel)).setUndoAction(new BotDownloads$DownloadBulletin$$ExternalSyntheticLambda0(this));
            if (getBulletin() == null) goto L14;
            r42.onAttach(this, getBulletin());
        L14:
            setButton(r42);
            return;
        L17:
            if (r4 != 2) goto L23;
            Bulletin.UndoButton r43 = new Bulletin.UndoButton(getContext(), true, this.resourcesProvider).setText(LocaleController.getString(R.string.BotFileDownloadOpen)).setUndoAction(new BotDownloads$DownloadBulletin$$ExternalSyntheticLambda1(this));
            if (getBulletin() == null) goto L21;
            r43.onAttach(this, getBulletin());
        L21:
            setButton(r43);
            return;
        L23:
            return;
        }

        @Override // org.telegram.ui.Components.Bulletin.ButtonLayout, android.widget.FrameLayout, android.view.View
        public void onMeasure(int r2, int r3) {
            super.onMeasure(r2, View.MeasureSpec.makeMeasureSpec(AndroidUtilities.dp(68.0f), TLObject.FLAG_30));
        }

        public boolean set(FileDownload r8) {
            if (this.file == r8) goto L5;
            this.status.reset();
        L5:
            this.file = r8;
            this.titleView.setText(r8.file_name);
            if (r8.isDownloading() == false) goto L16;
            Pair<Long, Long> r82 = r8.getProgress();
            this.status.setProgress(r82);
            if (((Long) r82.first).longValue() > 0) goto L10;
            this.subtitleView.setText(LocaleController.getString(R.string.BotFileDownloading));
        L14:
            setButton(1);
        L26:
            return false;
        L10:
            long r3 = ((Long) r82.second).longValue();
            TextView r32 = this.subtitleView;
            if (r3 > 0) goto L13;
            r32.setText(AndroidUtilities.formatFileSize(((Long) r82.first).longValue()));
            goto L14
        L13:
            r32.setText(AndroidUtilities.formatFileSize(((Long) r82.first).longValue()) + " / " + AndroidUtilities.formatFileSize(((Long) r82.second).longValue()));
            goto L14
        L16:
            if (r8.cancelled == false) goto L22;
            Bulletin r7 = getBulletin();
            if (r7 == null) goto L20;
            r7.hide();
        L20:
            return true;
        L22:
            if (r8.done == false) goto L26;
            this.subtitleView.setText(LocaleController.getString(R.string.BotFileDownloaded));
            setButton(2);
            this.status.setDone(false);
            Bulletin r72 = getBulletin();
            if (r72 == null) goto L26;
            r72.setCanHide(false);
            r72.setDuration(5000);
            r72.setCanHide(true);
            goto L26
        }

        public void setArrow(int r1) {
            this.background.setArrow(r1);
        }
    }

    public class FileDownload {
        public boolean cancelled;
        public boolean done;
        public File file;
        public String file_name;
        public Long id;
        public long last_progress_time;
        public long loaded_size;
        public String mime;
        public boolean resaved;
        public boolean shown;
        public long size;
        final /* synthetic */ BotDownloads this$0;
        private final Runnable updateProgressRunnable;
        public String url;

        public static /* synthetic */ void $r8$lambda$eDtM8f868m8tZrRInWwC7tzwDXo(FileDownload r0) {
            r0.updateProgress();
        }

        public FileDownload(BotDownloads r4, String r5, String r6) {
            this.this$0 = r4;
            this.updateProgressRunnable = new BotDownloads$FileDownload$$ExternalSyntheticLambda0(this);
            this.url = r5;
            this.file_name = r6;
            TLRPC.User r0 = MessagesController.getInstance(r4.currentAccount).getUser(Long.valueOf(r4.botId));
            DownloadManager.Request r1 = new DownloadManager.Request(Uri.parse(r5));
            r1.setTitle(UserObject.getUserName(r0));
            if (TextUtils.isEmpty(r6) == false) goto L5;
            String r52 = "Downloading file...";
        L6:
            r1.setDescription(r52);
            r1.setNotificationVisibility(0);
            r1.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS, r6);
            this.id = Long.valueOf(r4.downloadManager.enqueue(r1));
            return;
        L5:
            r52 = "Downloading " + r6 + "...";
            goto L6
        }

        public FileDownload(BotDownloads r3, JSONObject r4) {
            this.this$0 = r3;
            this.updateProgressRunnable = new BotDownloads$FileDownload$$ExternalSyntheticLambda0(this);
            this.url = r4.optString("url");
            this.file_name = r4.optString("file_name");
            this.size = r4.optLong("size");
            this.done = r4.optBoolean("done");
            this.mime = r4.optString("mime");
            String r32 = r4.optString("path");
            if (TextUtils.isEmpty(r32) == true) goto L6;
            this.file = new File(r32);
            return;
        }

        private void updateProgress() {
            if (this.done == false) goto L5;
            return;
        L5:
            if (this.cancelled == true) goto L40;
            AndroidUtilities.cancelRunOnUIThread(this.updateProgressRunnable);
            this.last_progress_time = System.currentTimeMillis();
            DownloadManager.Query r0 = new DownloadManager.Query();
            r0.setFilterById(new long[]{this.id.longValue()});
            Cursor r1 = null;
            r1 = this.this$0.downloadManager.query(r0);     // Catch: Throwable -> L15 Exception -> L17
            if (r1.moveToFirst() == false) goto L27;
            int r02 = r1.getInt(r1.getColumnIndex("status"));     // Catch: Throwable -> L15 Exception -> L17
            if (r02 != 8) goto L21;
            File r2 = new File(Uri.parse(r1.getString(r1.getColumnIndex("local_uri"))).getPath());     // Catch: Throwable -> L15 Exception -> L17
            this.file = r2;     // Catch: Throwable -> L15 Exception -> L17
            this.done = true;     // Catch: Throwable -> L15 Exception -> L17
            long r22 = r2.length();     // Catch: Throwable -> L15 Exception -> L17
            this.size = r22;     // Catch: Throwable -> L15 Exception -> L17
            if (r22 > 0) goto L19;
            cancel();     // Catch: Throwable -> L15 Exception -> L17
        L19:
            this.this$0.save();     // Catch: Throwable -> L15 Exception -> L17
        L29:
            r1.close();
        L33:
            BotDownloads.m21318$$Nest$mpostNotify(this.this$0);
            return;
        L21:
            if (r02 != 16) goto L25;
            cancel();     // Catch: Throwable -> L15 Exception -> L17
            r1.close();
            return;
        L25:
            this.loaded_size = r1.getLong(r1.getColumnIndex("bytes_so_far"));     // Catch: Throwable -> L15 Exception -> L17
            this.size = r1.getLong(r1.getColumnIndex("total_size"));     // Catch: Throwable -> L15 Exception -> L17
            AndroidUtilities.runOnUIThread(this.updateProgressRunnable, 160);     // Catch: Throwable -> L15 Exception -> L17
            goto L29
        L27:
            if (this.done == true) goto L29;
            cancel();     // Catch: Throwable -> L15 Exception -> L17
        L15:
            th = move-exception;
            if (0 == 0) goto L37;
            r1.close();
        L37:
            throw th;
        L17:
            e = move-exception;
            FileLog.e(e);     // Catch: Throwable -> L15
            if (0 == 0) goto L33;
        }

        public void cancel() {
            this.this$0.cancel(this);
        }

        public Pair<Long, Long> getProgress() {
            if (this.done == false) goto L7;
            return new Pair(Long.valueOf(this.size), Long.valueOf(this.size));
        L7:
            if (this.id == null) goto L18;
            if (this.cancelled == true) goto L18;
            if ((System.currentTimeMillis() - this.last_progress_time) < 150) goto L14;
            updateProgress();
            return new Pair(Long.valueOf(this.loaded_size), Long.valueOf(this.size));
        L14:
            return new Pair(Long.valueOf(this.loaded_size), Long.valueOf(this.size));
        L18:
            return new Pair(Long.valueOf(this.loaded_size), Long.valueOf(this.size));
        }

        public boolean isDownloading() {
            if (this.done == false) goto L5;
            return false;
        L5:
            if (this.id == null) goto L10;
            return true;
        L10:
            return false;
        }

        public void open() {
            File r0 = this.file;
            if (r0 != null) goto L5;
            return;
        L5:
            if (r0.exists() == false) goto L9;
            File r1 = this.file;
            AndroidUtilities.openForView(r1, r1.getName(), null, LaunchActivity.instance, null, true);
            return;
        }

        public JSONObject toJSON() {
            JSONObject r0 = new JSONObject();
            r0.put("url", this.url);     // Catch: Exception -> L9
            r0.put("file_name", this.file_name);     // Catch: Exception -> L9
            r0.put("size", this.size);     // Catch: Exception -> L9
            File r2 = this.file;     // Catch: Exception -> L9
            if (r2 != null) goto L6;
            String r22 = null;
        L7:
            r0.put("path", r22);     // Catch: Exception -> L9
            r0.put("done", this.done);     // Catch: Exception -> L9
            r0.put("mime", this.mime);     // Catch: Exception -> L9
            return r0;
        L6:
            r22 = r2.getAbsolutePath();     // Catch: Exception -> L9
        L9:
            e = move-exception;
            FileLog.e(e);
            return r0;
        }
    }

    /* renamed from: $r8$lambda$0V5vVA5p02uOzaqNbeUDV-Eoiqw, reason: not valid java name */
    public static /* synthetic */ void m21317$r8$lambda$0V5vVA5p02uOzaqNbeUDVEoiqw(boolean[] r0, Utilities.Callback r1, AlertDialog r2, int r3) {
        if (r0[0] == true) goto L6;
        r1.run(Boolean.TRUE);
        r0[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$Hqq6I3GomsJ5IjmnhzIfOGWibtc(AnimatedTextView r5, String r6, Long r7) {
        StringBuilder r0 = new StringBuilder();
        if (r7.longValue() <= 0) goto L5;
        r0.append("~");
        r0.append(AndroidUtilities.formatFileSize(r7.longValue()));
    L5:
        if (r6 != null) goto L7;
        String r62 = null;
    L9:
        if (TextUtils.isEmpty(r62) == true) goto L15;
        if (r0.length() <= 0) goto L13;
        r0.append(" ");
    L13:
        r0.append(r62.toUpperCase());
    L15:
        if (r0.length() > 0) goto L17;
        r0.append(LocaleController.getString(R.string.AttachDocument));
    L17:
        r5.setText(r0);
        return;
    L7:
        r62 = getExt(r6).toUpperCase();
        goto L9
    }

    public static /* synthetic */ void $r8$lambda$OQh7ybzxESYhklG32SuXfFcyUBY(boolean[] r1, Utilities.Callback r2, DialogInterface r3) {
        if (r1[0] == true) goto L6;
        r2.run(Boolean.FALSE);
        r1[0] = true;
        return;
    }

    public static /* synthetic */ void $r8$lambda$SvY6Nau02KUegIqZhFcTpXoUtaQ(boolean[] r0, Utilities.Callback r1, AlertDialog r2, int r3) {
        if (r0[0] == true) goto L6;
        r1.run(Boolean.FALSE);
        r0[0] = true;
        return;
    }

    /* renamed from: -$$Nest$mpostNotify, reason: not valid java name */
    public static /* bridge */ /* synthetic */ void m21318$$Nest$mpostNotify(BotDownloads r0) {
        r0.postNotify();
    }

    /* renamed from: -$$Nest$sfgetcachedMimeAndSizes, reason: not valid java name */
    public static /* bridge */ /* synthetic */ HashMap m21319$$Nest$sfgetcachedMimeAndSizes() {
        return cachedMimeAndSizes;
    }

    static {
        instances = new HashMap();
        cachedMimeAndSizes = new HashMap();
    }

    private BotDownloads(Context r3, int r4, long r5) {
        this.files = new ArrayList();
        this.context = r3;
        this.currentAccount = r4;
        this.botId = r5;
        this.downloadManager = (DownloadManager) r3.getSystemService("download");
        Set<String> r32 = r3.getSharedPreferences("botdownloads_" + r4, 0).getStringSet(_UrlKt.FRAGMENT_ENCODE_SET + r5, null);
        if (r32 == null) goto L16;
        Iterator<String> r33 = r32.iterator();
    L6:
        if (r33.hasNext() == false) goto L28;
        FileDownload r52 = new FileDownload(this, new JSONObject(r33.next()));     // Catch: Exception -> L14
        File r42 = r52.file;     // Catch: Exception -> L14
        if (r42 == null) goto L6;
        if (r42.exists() == false) goto L6;
        this.files.add(r52);     // Catch: Exception -> L14
    L14:
        e = move-exception;
        FileLog.e(e);
        goto L6
    L28:
        return;
    }

    public static void clear() {
        Context r0 = ApplicationLoader.applicationContext;
        if (r0 != null) goto L5;
        return;
    L5:
        int r2 = 0;
    L7:
        if (r2 >= 16) goto L9;
        r0.getSharedPreferences("botdownloads_" + r2, 0).edit().clear().apply();
        r2 = r2 + 1;
        goto L7
    L9:
        instances.clear();
    }

    public static BotDownloads get(Context r3, int r4, long r5) {
        Pair<Integer, Long> r0 = new Pair(Integer.valueOf(r4), Long.valueOf(r5));
        HashMap<Pair<Integer, Long>, BotDownloads> r1 = instances;
        BotDownloads r2 = r1.get(r0);
        if (r2 != null) goto L6;
        BotDownloads r22 = new BotDownloads(r3, r4, r5);
        r1.put(r0, r22);
        return r22;
    L6:
        return r2;
    }

    public static String getExt(String r4) {
        if (r4 != null) goto L4;
        return _UrlKt.FRAGMENT_ENCODE_SET;
    L4:
        if (r4.isEmpty() == true) goto L257;
        char r3 = 65535;
        switch(r4.hashCode()) {
            case -2008589971: goto L166;
            case -1719571662: goto L162;
            case -1664118616: goto L158;
            case -1578389996: goto L154;
            case -1348237359: goto L150;
            case -1348236892: goto L146;
            case -1079884372: goto L142;
            case -1073633483: goto L138;
            case -1071817359: goto L134;
            case -1050893613: goto L130;
            case -1007601745: goto L126;
            case -958424608: goto L122;
            case -816908365: goto L118;
            case -648684635: goto L114;
            case -433129473: goto L110;
            case -366307023: goto L106;
            case -48069494: goto L102;
            case -43923783: goto L98;
            case -43491031: goto L94;
            case 187091926: goto L90;
            case 817335912: goto L86;
            case 859118878: goto L82;
            case 886992732: goto L78;
            case 904647503: goto L74;
            case 1154306387: goto L70;
            case 1154455342: goto L66;
            case 1178484637: goto L62;
            case 1423759679: goto L58;
            case 1436962847: goto L54;
            case 1454024983: goto L50;
            case 1455492626: goto L46;
            case 1503095341: goto L42;
            case 1504831518: goto L38;
            case 1509238306: goto L34;
            case 1578362927: goto L30;
            case 1643664935: goto L26;
            case 1672200517: goto L22;
            case 1993842850: goto L18;
            case 2049276534: goto L14;
            case 2132236175: goto L10;
            default: goto L169;
        };
    L169:
        switch(r3) {
            case 0: goto L253;
            case 1: goto L251;
            case 2: goto L249;
            case 3: goto L247;
            case 4: goto L245;
            case 5: goto L243;
            case 6: goto L241;
            case 7: goto L239;
            case 8: goto L237;
            case 9: goto L235;
            case 10: goto L233;
            case 11: goto L231;
            case 12: goto L229;
            case 13: goto L227;
            case 14: goto L225;
            case 15: goto L223;
            case 16: goto L227;
            case 17: goto L221;
            case 18: goto L219;
            case 19: goto L217;
            case 20: goto L215;
            case 21: goto L213;
            case 22: goto L211;
            case 23: goto L209;
            case 24: goto L207;
            case 25: goto L221;
            case 26: goto L205;
            case 27: goto L203;
            case 28: goto L201;
            case 29: goto L199;
            case 30: goto L197;
            case 31: goto L249;
            case 32: goto L195;
            case 33: goto L193;
            case 34: goto L191;
            case 35: goto L189;
            case 36: goto L187;
            case 37: goto L185;
            case 38: goto L183;
            case 39: goto L181;
            default: goto L171;
        };
    L181:
        return "js";
    L183:
        return "jar";
    L185:
        return "xlsx";
    L187:
        return "azw";
    L189:
        return "ods";
    L191:
        return "ico";
    L193:
        return "rar";
    L195:
        return HlsSegmentFormat.MP3;
    L197:
        return "arc";
    L199:
        return "7z";
    L201:
        return "odp";
    L203:
        return "bz2";
    L205:
        return "bin";
    L207:
        return "bz";
    L209:
        return "doc";
    L211:
        return "jsonld";
    L213:
        return "abw";
    L215:
        return "txt";
    L217:
        return "opus";
    L219:
        return "sh";
    L221:
        return "gz";
    L223:
        return "xls";
    L225:
        return "mpkg";
    L227:
        return "3g2";
    L229:
        return "php";
    L231:
        return "ics";
    L233:
        return "midi";
    L235:
        return "docx";
    L237:
        return "ppt";
    L239:
        return "pptx";
    L241:
        return "avi";
    L243:
        return "csh";
    L245:
        return "cda";
    L247:
        return "eot";
    L249:
        return "3gp";
    L251:
        return "odt";
    L253:
        return "epub";
    L171:
        if (r4.contains("/") == false) goto L174;
        r4 = r4.substring(r4.indexOf("/") + 1);
    L174:
        if (r4.contains("-") == false) goto L177;
        r4 = r4.substring(r4.indexOf("-") + 1);
    L177:
        if (r4.contains("+") == false) goto L180;
        r4 = r4.substring(0, r4.indexOf("+"));
    L180:
        return r4.toLowerCase();
    L10:
        if (r4.equals("text/javascript") == false) goto L169;
        r3 = '\'';
        goto L169
    L14:
        if (r4.equals("application/java-archive") == false) goto L169;
        r3 = Typography.amp;
        goto L169
    L18:
        if (r4.equals("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet") == false) goto L169;
        r3 = '%';
        goto L169
    L22:
        if (r4.equals("application/vnd.amazon.ebook") == false) goto L169;
        r3 = Typography.dollar;
        goto L169
    L26:
        if (r4.equals("application/vnd.oasis.opendocument.spreadsheet") == false) goto L169;
        r3 = '#';
        goto L169
    L30:
        if (r4.equals("image/vnd.microsoft.icon") == false) goto L169;
        r3 = Typography.quote;
        goto L169
    L34:
        if (r4.equals("application/vnd.rar") == false) goto L169;
        r3 = '!';
        goto L169
    L38:
        if (r4.equals("audio/mpeg") == false) goto L169;
        r3 = ' ';
        goto L169
    L42:
        if (r4.equals("audio/3gpp") == false) goto L169;
        r3 = 31;
        goto L169
    L46:
        if (r4.equals("application/x-freearc") == false) goto L169;
        r3 = 30;
        goto L169
    L50:
        if (r4.equals("application/x-7z-compressed") == false) goto L169;
        r3 = 29;
        goto L169
    L54:
        if (r4.equals("application/vnd.oasis.opendocument.presentation") == false) goto L169;
        r3 = 28;
        goto L169
    L58:
        if (r4.equals("application/x-bzip2") == false) goto L169;
        r3 = 27;
        goto L169
    L62:
        if (r4.equals("application/octet-stream") == false) goto L169;
        r3 = 26;
        goto L169
    L66:
        if (r4.equals("application/x-gzip") == false) goto L169;
        r3 = 25;
        goto L169
    L70:
        if (r4.equals("application/x-bzip") == false) goto L169;
        r3 = 24;
        goto L169
    L74:
        if (r4.equals("application/msword") == false) goto L169;
        r3 = 23;
        goto L169
    L78:
        if (r4.equals("application/ld+json") == false) goto L169;
        r3 = 22;
        goto L169
    L82:
        if (r4.equals("application/x-abiword") == false) goto L169;
        r3 = 21;
        goto L169
    L86:
        if (r4.equals("text/plain") == false) goto L169;
        r3 = 20;
        goto L169
    L90:
        if (r4.equals("audio/ogg") == false) goto L169;
        r3 = 19;
        goto L169
    L94:
        if (r4.equals("application/x-sh") == false) goto L169;
        r3 = 18;
        goto L169
    L98:
        if (r4.equals("application/gzip") == false) goto L169;
        r3 = 17;
        goto L169
    L102:
        if (r4.equals("video/3gpp2") == false) goto L169;
        r3 = 16;
        goto L169
    L106:
        if (r4.equals("application/vnd.ms-excel") == false) goto L169;
        r3 = 15;
        goto L169
    L110:
        if (r4.equals("application/vnd.apple.installer+xml") == false) goto L169;
        r3 = 14;
        goto L169
    L114:
        if (r4.equals("audio/3gpp2") == false) goto L169;
        r3 = '\r';
        goto L169
    L118:
        if (r4.equals("application/x-httpd-php") == false) goto L169;
        r3 = '\f';
        goto L169
    L122:
        if (r4.equals("text/calendar") == false) goto L169;
        r3 = 11;
        goto L169
    L126:
        if (r4.equals("audio/x-midi") == false) goto L169;
        r3 = '\n';
        goto L169
    L130:
        if (r4.equals("application/vnd.openxmlformats-officedocument.wordprocessingml.document") == false) goto L169;
        r3 = '\t';
        goto L169
    L134:
        if (r4.equals("application/vnd.ms-powerpoint") == false) goto L169;
        r3 = '\b';
        goto L169
    L138:
        if (r4.equals("application/vnd.openxmlformats-officedocument.presentationml.presentation") == false) goto L169;
        r3 = 7;
        goto L169
    L142:
        if (r4.equals("video/x-msvideo") == false) goto L169;
        r3 = 6;
        goto L169
    L146:
        if (r4.equals("application/x-csh") == false) goto L169;
        r3 = 5;
        goto L169
    L150:
        if (r4.equals("application/x-cdf") == false) goto L169;
        r3 = 4;
        goto L169
    L154:
        if (r4.equals("application/vnd.ms-fontobject") == false) goto L169;
        r3 = 3;
        goto L169
    L158:
        if (r4.equals("video/3gpp") == false) goto L169;
        r3 = 2;
        goto L169
    L162:
        if (r4.equals("application/vnd.oasis.opendocument.text") == false) goto L169;
        r3 = 1;
        goto L169
    L166:
        if (r4.equals("application/epub+zip") == false) goto L169;
        r3 = 0;
        goto L169
    L257:
        return _UrlKt.FRAGMENT_ENCODE_SET;
    }

    public static void getMimeAndSize(String r1, Utilities.Callback2<String, Long> r2) {
        if (cachedMimeAndSizes.containsKey(r1) == false) goto L6;
        Pair<String, Long> r12 = cachedMimeAndSizes.get(r1);
        r2.run((String) r12.first, (Long) r12.second);
        return;
    L6:
        new AnonymousClass1(r1, r2).execute(new String[]{r1});
    }

    private void postNotify() {
        NotificationCenter.getInstance(this.currentAccount).postNotificationName(NotificationCenter.botDownloadsUpdate, new Object[0]);
    }

    public static AlertDialog showAlert(Context r8, String r9, String r10, String r11, Utilities.Callback<Boolean> r12, long r13, String r15) {
        if (r12 != null) goto L5;
        return null;
    L5:
        AlertDialog.Builder r132 = new AlertDialog.Builder(r8);
        r132.setTitle(LocaleController.getString(R.string.BotDownloadFileTitle));
        r132.setMessage(AndroidUtilities.replaceTags(LocaleController.formatString(R.string.BotDownloadFileText, new Object[]{r11})));
        LinearLayout r112 = new LinearLayout(r8);
        r112.setPadding(AndroidUtilities.dp(22.0f), 0, AndroidUtilities.dp(22.0f), 0);
        r112.setOrientation(0);
        ImageView r14 = new ImageView(r8);
        r14.setScaleType(ImageView.ScaleType.CENTER);
        r14.setBackground(Theme.createCircleDrawable(AndroidUtilities.dp(44.0f), Theme.getColor(Theme.key_featuredStickers_addButton)));
        r14.setImageResource(R.drawable.msg_round_file_s);
        r112.addView(r14, LayoutHelper.createLinear(44, 44, 19, 0, 0, 10, 0));
        LinearLayout r142 = new LinearLayout(r8);
        r142.setOrientation(1);
        TextView r1 = new TextView(r8);
        r1.setTextSize(1, 15.0f);
        r1.setTypeface(AndroidUtilities.bold());
        r1.setText(r10);
        r1.setTextColor(Theme.getColor(Theme.key_dialogTextBlue2));
        r142.addView(r1, LayoutHelper.createLinear(-1, -2, 0.0f, 0.0f, 0.0f, 3.0f));
        AnimatedTextView r102 = new AnimatedTextView(r8, true, true, true);
        r102.setTextSize(AndroidUtilities.dp(12.0f));
        SpannableString r82 = new SpannableString("l");
        LoadingSpan r16 = new LoadingSpan(r102, AndroidUtilities.dp(55.0f));
        int r2 = Theme.key_chat_inFileInfoText;
        r16.setColors(Theme.multAlpha(Theme.getColor(r2), 0.35f), Theme.multAlpha(Theme.getColor(r2), 0.075f));
        r82.setSpan(r16, 0, 1, 33);
        r102.setText(r82);
        getMimeAndSize(r9, new BotDownloads$$ExternalSyntheticLambda0(r102));
        r102.setTextColor(Theme.getColor(r2));
        r142.addView(r102, LayoutHelper.createLinear(-1, 15));
        r112.addView(r142, LayoutHelper.createLinear(-1, -2, 23, 0, 0, 0, 2));
        r132.setView(r112);
        boolean[] r83 = new boolean[1];
        r132.setNegativeButton(LocaleController.getString(R.string.Cancel), new BotDownloads$$ExternalSyntheticLambda1(r83, r12));
        r132.setPositiveButton(LocaleController.getString(R.string.BotDownloadFileDownload), new BotDownloads$$ExternalSyntheticLambda2(r83, r12));
        AlertDialog r92 = r132.create();
        r92.setOnDismissListener(new BotDownloads$$ExternalSyntheticLambda3(r83, r12));
        r92.show();
        return r92;
    }

    public static void showAlert(Context r8, String r9, String r10, String r11, Utilities.Callback<Boolean> r12) {
        if (r12 != null) goto L4;
        return;
    L4:
        showAlert(r8, r9, r10, r11, r12, 0, _UrlKt.FRAGMENT_ENCODE_SET);
    }

    public void cancel(FileDownload r6) {
        if (r6 != null) goto L4;
        return;
    L4:
        r6.cancelled = true;
        Long r1 = r6.id;
        if (r1 == null) goto L7;
        this.downloadManager.remove(new long[]{r1.longValue()});
        r6.id = null;
    L7:
        this.files.remove(r6);
        postNotify();
    }

    public FileDownload download(String r2, String r3) {
        FileDownload r0 = getCached(r2);
        if (r0 == null) goto L6;
        this.currentFile = r0;
        r0.resaved = true;
        postNotify();
        return r0;
    L6:
        FileDownload r02 = new FileDownload(this, r2, r3);
        this.currentFile = r02;
        r02.shown = false;
        this.files.add(r02);
        save();
        postNotify();
        return r02;
    }

    public FileDownload getCached(String r5) {
        ArrayList<FileDownload> r4 = this.files;
        int r0 = r4.size();
        int r1 = 0;
    L3:
        if (r1 >= r0) goto L9;
        FileDownload r2 = r4.get(r1);
        r1 = r1 + 1;
        FileDownload r22 = r2;
        if (TextUtils.equals(r22.url, r5) == false) goto L3;
        if (r22.done == false) goto L3;
        return r22;
    L9:
        return null;
    }

    public FileDownload getCurrent() {
        return this.currentFile;
    }

    public ArrayList<FileDownload> getFiles() {
        return this.files;
    }

    public boolean hasFiles() {
        return !this.files.isEmpty();
    }

    public boolean isDownloading() {
        ArrayList<FileDownload> r4 = this.files;
        int r0 = r4.size();
        int r2 = 0;
    L3:
        if (r2 >= r0) goto L8;
        FileDownload r3 = r4.get(r2);
        r2 = r2 + 1;
        if (r3.isDownloading() == false) goto L3;
        return true;
    L8:
        return false;
    }

    public void save() {
        int r2 = 0;
        SharedPreferences.Editor r0 = this.context.getSharedPreferences("botdownloads_" + this.currentAccount, 0).edit();
        r0.clear();
        HashSet r1 = new HashSet();
        ArrayList<FileDownload> r3 = this.files;
        int r4 = r3.size();
    L3:
        if (r2 >= r4) goto L5;
        FileDownload r5 = r3.get(r2);
        r2 = r2 + 1;
        r1.add(r5.toJSON().toString());
        goto L3
    L5:
        r0.putStringSet(_UrlKt.FRAGMENT_ENCODE_SET + this.botId, r1);
        r0.apply();
    }
}
