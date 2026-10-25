package org.telegram.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.Locale;
import kotlin.time.DurationKt;
import org.telegram.messenger.BuildVars;
import org.telegram.messenger.LocaleController;
import org.telegram.messenger.R;
import org.telegram.ui.ActionBar.ActionBar;
import org.telegram.ui.ActionBar.AlertDialog;
import org.telegram.ui.ActionBar.BaseFragment;
import org.telegram.ui.ActionBar.Theme;
import org.telegram.ui.Cells.HeaderCell;
import org.telegram.ui.Cells.TextCheckCell;
import org.telegram.ui.Cells.TextInfoPrivacyCell;
import org.telegram.ui.Cells.TextSettingsCell;
import org.telegram.ui.Components.LayoutHelper;
import org.telegram.ui.Components.RecyclerListView;
import org.telegram.utils.camera.roundvideo.RoundVideoSession;
import org.telegram.utils.settings.BooleanSetting;
import org.telegram.utils.settings.EnumSetting;
import org.telegram.utils.settings.SharedSettings;

/* loaded from: classes6.dex */
public class RoundVideoSettingsActivity extends BaseFragment {
    private static final int[] BITRATES = null;
    private static final int ROW_COUNT = 0;
    private ListAdapter adapter;
    private RecyclerListView listView;

    /* renamed from: org.telegram.ui.RoundVideoSettingsActivity$1, reason: invalid class name */
    public class AnonymousClass1 extends ActionBar.ActionBarMenuOnItemClick {
        final /* synthetic */ RoundVideoSettingsActivity this$0;

        public AnonymousClass1(RoundVideoSettingsActivity r1) {
            this.this$0 = r1;
        }

        @Override // org.telegram.ui.ActionBar.ActionBar.ActionBarMenuOnItemClick
        public void onItemClick(int r2) {
            if (r2 != (-1)) goto L6;
            this.this$0.finishFragment();
            return;
        }
    }

    public interface ChoiceHandler {
        void onChoice(int r1);
    }

    public class ListAdapter extends RecyclerListView.SelectionAdapter {
        private final Context context;
        final /* synthetic */ RoundVideoSettingsActivity this$0;

        private ListAdapter(RoundVideoSettingsActivity r1, Context r2) {
            this.this$0 = r1;
            this.context = r2;
        }

        public /* synthetic */ ListAdapter(RoundVideoSettingsActivity r1, Context r2, RoundVideoSettingsActivityIA r3) {
            this(r1, r2);
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemCount() {
            return RoundVideoSettingsActivity.m18508$$Nest$sfgetROW_COUNT();
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public int getItemViewType(int r2) {
            if (r2 != 0) goto L4;
            return 0;
        L4:
            if (r2 != 7) goto L7;
            return 0;
        L7:
            if (r2 != 1) goto L9;
            return 1;
        L9:
            if (r2 != 8) goto L12;
            return 1;
        L12:
            if (r2 != 6) goto L14;
        L18:
            return 3;
        L14:
            if (r2 == 9) goto L18;
            return 2;
        }

        @Override // org.telegram.ui.Components.RecyclerListView.SelectionAdapter
        public boolean isEnabled(RecyclerView.ViewHolder r3) {
            int r2 = r3.getAdapterPosition();
            if (r2 != 1) goto L6;
            return true;
        L6:
            if (SharedSettings.roundVideoCamera2Enabled.get() == true) goto L9;
            return false;
        L9:
            if (r2 != 2) goto L11;
        L20:
            return true;
        L11:
            if (r2 == 3) goto L20;
            if (r2 == 4) goto L20;
            if (r2 == 5) goto L20;
            if (r2 == 8) goto L20;
            return false;
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public void onBindViewHolder(RecyclerView.ViewHolder r5, int r6) {
            boolean r4 = SharedSettings.roundVideoCamera2Enabled.get();
            if (r5.getItemViewType() != 0) goto L11;
            HeaderCell r42 = (HeaderCell) r5.itemView;
            if (r6 != 0) goto L7;
            String r52 = LocaleController.getString(R.string.RoundVideoGeneral);
        L8:
            r42.setText(r52);
            return;
        L7:
            r52 = LocaleController.getString(R.string.RoundVideoComposition);
            goto L8
        L11:
            if (r5.getItemViewType() != 1) goto L24;
            TextCheckCell r53 = (TextCheckCell) r5.itemView;
            if (r6 == 1) goto L17;
            if (r4 == true) goto L17;
            boolean r0 = false;
        L18:
            r53.setEnabled(r0);
            if (r6 != 1) goto L22;
            r53.setTextAndCheck(LocaleController.getString(R.string.RoundVideoUseNewRecorder), r4, false);
            return;
        L22:
            r53.setTextAndCheck(LocaleController.getString(R.string.RoundVideoCompositionEnabled), SharedSettings.roundVideoComposition.get(), false);
            return;
        L17:
            r0 = true;
            goto L18
        L24:
            int r02 = r5.getItemViewType();
            View r54 = r5.itemView;
            if (r02 != 2) goto L40;
            TextSettingsCell r55 = (TextSettingsCell) r54;
            r55.setEnabled(r4);
            if (r6 != 2) goto L31;
            r55.setTextAndValue(LocaleController.getString(R.string.RoundVideoOutputResolution), ((RoundVideoSession.OutputResolution) SharedSettings.roundVideoOutputResolution.get()).getSize() + "p", true);
            return;
        L31:
            if (r6 != 3) goto L35;
            r55.setTextAndValue(LocaleController.getString(R.string.RoundVideoCameraResolution), RoundVideoSettingsActivity.m18509$$Nest$smcameraResolutionLabel((RoundVideoSession.CameraResolution) SharedSettings.roundVideoCameraResolution.get()), true);
            return;
        L35:
            if (r6 != 4) goto L38;
            r55.setTextAndValue(LocaleController.getString(R.string.RoundVideoFrameRate), ((RoundVideoSession.FrameRate) SharedSettings.roundVideoFrameRate.get()).getValue() + " FPS", true);
            return;
        L38:
            r55.setTextAndValue(LocaleController.getString(R.string.RoundVideoBitrate), RoundVideoSettingsActivity.m18510$$Nest$smformatBitrate(SharedSettings.roundVideoVideoBitrate.get()), false);
            return;
        L40:
            TextInfoPrivacyCell r56 = (TextInfoPrivacyCell) r54;
            if (r6 != 6) goto L43;
            String r43 = LocaleController.getString(R.string.RoundVideoGeneralInfo);
        L44:
            r56.setText(r43);
            return;
        L43:
            r43 = LocaleController.getString(R.string.RoundVideoCompositionInfo);
            goto L44
        }

        @Override // androidx.recyclerview.widget.RecyclerView.Adapter
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup r2, int r3) {
            if (r3 != 0) goto L5;
            View r22 = new HeaderCell(this.context);
        L11:
            r22.setLayoutParams(new RecyclerView.LayoutParams(-1, -2));
            return new RecyclerListView.Holder(r22);
        L5:
            if (r3 != 1) goto L8;
            r22 = new TextCheckCell(this.context);
            goto L11
        L8:
            if (r3 != 2) goto L10;
            TextSettingsCell r32 = new TextSettingsCell(this.context);
            r32.setCanDisable(true);
            r22 = r32;
            goto L11
        L10:
            r22 = new TextInfoPrivacyCell(this.context);
            goto L11
        }
    }

    public static /* synthetic */ void $r8$lambda$Gis0vw9LrtTD9PPuX9SEKpIN9Hk(RoundVideoSettingsActivity r0, ChoiceHandler r1, DialogInterface r2, int r3) {
        r0.lambda$showChoice$5(r1, r2, r3);
    }

    public static /* synthetic */ void $r8$lambda$LefkhkHlKFc1UpW5L2A0vKnSym4(int r1) {
        EnumSetting<RoundVideoSession.OutputResolution> r0 = SharedSettings.roundVideoOutputResolution;
        if (r1 != 0) goto L5;
        RoundVideoSession.OutputResolution r12 = RoundVideoSession.OutputResolution.P480;
    L6:
        r0.set(r12);
        return;
    L5:
        r12 = RoundVideoSession.OutputResolution.P360;
        goto L6
    }

    public static /* synthetic */ void $r8$lambda$c3UFsn1FLwLsoj8rMgqFx4338hw(RoundVideoSettingsActivity r0, View r1, int r2) {
        r0.lambda$createView$0(r1, r2);
    }

    public static /* synthetic */ void $r8$lambda$d6xgz13q9mDltbTEjNqDxEhKR4s(int r2) {
        SharedSettings.roundVideoVideoBitrate.set(BITRATES[r2]);
    }

    /* renamed from: $r8$lambda$mwVtg9OFRksN-5cckhVRhc8oNeI, reason: not valid java name */
    public static /* synthetic */ void m18506$r8$lambda$mwVtg9OFRksN5cckhVRhc8oNeI(int r1) {
        EnumSetting<RoundVideoSession.FrameRate> r0 = SharedSettings.roundVideoFrameRate;
        if (r1 != 0) goto L5;
        RoundVideoSession.FrameRate r12 = RoundVideoSession.FrameRate.FPS_30;
    L6:
        r0.set(r12);
        return;
    L5:
        r12 = RoundVideoSession.FrameRate.FPS_60;
        goto L6
    }

    /* renamed from: $r8$lambda$zRuz5yr7-CokBjBANuTgpcWMeAA, reason: not valid java name */
    public static /* synthetic */ void m18507$r8$lambda$zRuz5yr7CokBjBANuTgpcWMeAA(int r2) {
        SharedSettings.roundVideoCameraResolution.set(RoundVideoSession.CameraResolution.values()[r2]);
    }

    /* renamed from: -$$Nest$sfgetROW_COUNT, reason: not valid java name */
    public static /* bridge */ /* synthetic */ int m18508$$Nest$sfgetROW_COUNT() {
        return ROW_COUNT;
    }

    /* renamed from: -$$Nest$smcameraResolutionLabel, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m18509$$Nest$smcameraResolutionLabel(RoundVideoSession.CameraResolution r0) {
        return cameraResolutionLabel(r0);
    }

    /* renamed from: -$$Nest$smformatBitrate, reason: not valid java name */
    public static /* bridge */ /* synthetic */ String m18510$$Nest$smformatBitrate(int r0) {
        return formatBitrate(r0);
    }

    static {
        if (BuildVars.DEBUG_PRIVATE_VERSION == false) goto L5;
        int r0 = 10;
    L6:
        ROW_COUNT = r0;
        BITRATES = new int[]{750000, DurationKt.NANOS_IN_MILLIS, 1200000, 2000000};
        return;
    L5:
        r0 = 7;
        goto L6
    }

    public RoundVideoSettingsActivity() {
    }

    private static String cameraResolutionLabel(RoundVideoSession.CameraResolution r1) {
        if (r1 != RoundVideoSession.CameraResolution.HIGH) goto L7;
        return LocaleController.getString(R.string.RoundVideoCameraResolutionHigh);
    L7:
        if (r1 != RoundVideoSession.CameraResolution.MEDIUM) goto L11;
        return LocaleController.getString(R.string.RoundVideoCameraResolutionMedium);
    L11:
        return LocaleController.getString(R.string.RoundVideoCameraResolutionLow);
    }

    private static String formatBitrate(int r2) {
        if ((r2 % DurationKt.NANOS_IN_MILLIS) == 0) goto L5;
        if (r2 <= 1000000) goto L10;
        return String.format(Locale.US, "%.1f Mbps", new Object[]{Float.valueOf(r2 / 1000000.0f)});
    L10:
        return (r2 / 1000) + " kbps";
    L5:
        return (r2 / DurationKt.NANOS_IN_MILLIS) + " Mbps";
    }

    private /* synthetic */ void lambda$createView$0(View r1, int r2) {
        onRowClicked(r2);
    }

    private /* synthetic */ void lambda$showChoice$5(ChoiceHandler r1, DialogInterface r2, int r3) {
        r1.onChoice(r3);
        this.adapter.notifyDataSetChanged();
    }

    private void onRowClicked(int r8) {
        if (r8 != 1) goto L7;
        BooleanSetting r82 = SharedSettings.roundVideoCamera2Enabled;
        r82.set(true ^ r82.get());
        this.adapter.notifyDataSetChanged();
        return;
    L7:
        if (SharedSettings.roundVideoCamera2Enabled.get() == false) goto L35;
        int r1 = 0;
        if (r8 != 2) goto L14;
        showChoice(R.string.RoundVideoOutputResolution, new CharSequence[]{"480p", "360p"}, new RoundVideoSettingsActivity$$ExternalSyntheticLambda1());
        return;
    L14:
        if (r8 != 3) goto L18;
        showChoice(R.string.RoundVideoCameraResolution, new CharSequence[]{LocaleController.getString(R.string.RoundVideoCameraResolutionHigh), LocaleController.getString(R.string.RoundVideoCameraResolutionMedium), LocaleController.getString(R.string.RoundVideoCameraResolutionLow)}, new RoundVideoSettingsActivity$$ExternalSyntheticLambda2());
        return;
    L18:
        if (r8 != 4) goto L22;
        showChoice(R.string.RoundVideoFrameRate, new CharSequence[]{"30 FPS", "60 FPS"}, new RoundVideoSettingsActivity$$ExternalSyntheticLambda3());
        return;
    L22:
        if (r8 != 5) goto L30;
        CharSequence[] r83 = new CharSequence[BITRATES.length];
    L24:
        int[] r2 = BITRATES;
        if (r1 >= (r2.length - (!BuildVars.DEBUG_PRIVATE_VERSION ? 1 : 0))) goto L27;
        r83[r1] = formatBitrate(r2[r1]);
        r1 = r1 + 1;
        goto L24
    L27:
        showChoice(R.string.RoundVideoBitrate, r83, new RoundVideoSettingsActivity$$ExternalSyntheticLambda4());
        return;
    L30:
        if (r8 != 8) goto L34;
        BooleanSetting r12 = SharedSettings.roundVideoComposition;
        r12.set(true ^ r12.get());
        this.adapter.notifyItemChanged(r8);
        return;
    L34:
        return;
    }

    private void showChoice(int r3, CharSequence[] r4, ChoiceHandler r5) {
        AlertDialog.Builder r0 = new AlertDialog.Builder(getParentActivity());
        r0.setTitle(LocaleController.getString(r3));
        r0.setItems(r4, new RoundVideoSettingsActivity$$ExternalSyntheticLambda5(this, r5));
        showDialog(r0.create());
    }

    @Override // org.telegram.ui.ActionBar.BaseFragment
    public View createView(Context r5) {
        this.actionBar.setBackButtonImage(R.drawable.ic_ab_back);
        this.actionBar.setAllowOverlayTitle(true);
        this.actionBar.setTitle(LocaleController.getString(R.string.RoundVideoSettings));
        this.actionBar.setActionBarMenuOnItemClick(new AnonymousClass1(this));
        FrameLayout r0 = new FrameLayout(r5);
        r0.setBackgroundColor(Theme.getColor(Theme.key_windowBackgroundGray));
        this.fragmentView = r0;
        RecyclerListView r1 = new RecyclerListView(r5);
        this.listView = r1;
        r1.setSections();
        this.actionBar.setAdaptiveBackground(this.listView);
        this.listView.setLayoutManager(new LinearLayoutManager(r5));
        this.listView.setVerticalScrollBarEnabled(false);
        RecyclerListView r12 = this.listView;
        ListAdapter r2 = new ListAdapter(this, r5, null);
        this.adapter = r2;
        r12.setAdapter(r2);
        this.listView.setOnItemClickListener(new RoundVideoSettingsActivity$$ExternalSyntheticLambda0(this));
        r0.addView(this.listView, LayoutHelper.createFrame(-1, -1.0f));
        return this.fragmentView;
    }
}
