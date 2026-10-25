package com.google.android.gms.cast.framework.media;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable;
import c.f$$ExternalSyntheticBUOutline1;
import com.google.android.gms.cast.framework.media.internal.ResourceProvider;
import com.google.android.gms.common.internal.Preconditions;
import com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable;
import com.google.android.gms.common.internal.safeparcel.SafeParcelWriter;
import com.google.android.gms.internal.cast.zzhv;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* loaded from: classes4.dex */
public class NotificationOptions extends AbstractSafeParcelable {
    public static final Parcelable.Creator<NotificationOptions> CREATOR = null;
    public static final long SKIP_STEP_TEN_SECONDS_IN_MS = 10000;
    public static final long SKIP_STEP_THIRTY_SECONDS_IN_MS = 30000;
    private static final zzhv zza = null;
    private static final int[] zzb = null;
    private final int zzA;
    private final int zzB;
    private final int zzC;
    private final int zzD;
    private final int zzE;
    private final int zzF;
    private final int zzG;
    private final zzg zzH;
    private final boolean zzI;
    private final boolean zzJ;
    private final List zzc;
    private final int[] zzd;
    private final long zze;
    private final String zzf;
    private final int zzg;
    private final int zzh;
    private final int zzi;
    private final int zzj;
    private final int zzk;
    private final int zzl;
    private final int zzm;
    private final int zzn;
    private final int zzo;
    private final int zzp;
    private final int zzq;
    private final int zzr;
    private final int zzs;
    private final int zzt;
    private final int zzu;
    private final int zzv;
    private final int zzw;
    private final int zzx;
    private final int zzy;
    private final int zzz;

    public static final class Builder {
        private String zza;
        private List zzb;
        private NotificationActionsProvider zzc;
        private int[] zzd;
        private int zze;
        private int zzf;
        private int zzg;
        private int zzh;
        private int zzi;
        private int zzj;
        private int zzk;
        private int zzl;
        private int zzm;
        private int zzn;
        private int zzo;
        private int zzp;
        private int zzq;
        private long zzr;
        private boolean zzs;
        private boolean zzt;

        public Builder() {
            this.zzb = NotificationOptions.zzp();
            this.zzd = NotificationOptions.zzq();
            this.zze = zza("smallIconDrawableResId");
            this.zzf = zza("stopLiveStreamDrawableResId");
            this.zzg = zza("pauseDrawableResId");
            this.zzh = zza("playDrawableResId");
            this.zzi = zza("skipNextDrawableResId");
            this.zzj = zza("skipPrevDrawableResId");
            this.zzk = zza("forwardDrawableResId");
            this.zzl = zza("forward10DrawableResId");
            this.zzm = zza("forward30DrawableResId");
            this.zzn = zza("rewindDrawableResId");
            this.zzo = zza("rewind10DrawableResId");
            this.zzp = zza("rewind30DrawableResId");
            this.zzq = zza("disconnectDrawableResId");
            this.zzr = NotificationOptions.SKIP_STEP_TEN_SECONDS_IN_MS;
        }

        private static int zza(String r4) {
            int r2 = ResourceProvider.$r8$clinit;     // Catch: Throwable -> L8
            Integer r42 = (Integer) ResourceProvider.class.getMethod("findResourceByName", new Class[]{String.class}).invoke(null, new Object[]{r4});     // Catch: Throwable -> L8
            if (r42 != null) goto L6;
            return 0;
        L6:
            return r42.intValue();
        L8:
            return 0;
        }

        /* JADX WARN: Multi-variable type inference failed */
        /* JADX WARN: Type inference failed for: r35v0, types: [android.os.IBinder] */
        public NotificationOptions build() {
            NotificationActionsProvider r1 = this.zzc;
            if (r1 != null) goto L6;
            zzg r12 = null;
        L5:
            ?? r35 = r12;
            return new NotificationOptions(this.zzb, this.zzd, this.zzr, this.zza, this.zze, this.zzf, this.zzg, this.zzh, this.zzi, this.zzj, this.zzk, this.zzl, this.zzm, this.zzn, this.zzo, this.zzp, this.zzq, zza("notificationImageSizeDimenResId"), zza("castingToDeviceStringResId"), zza("stopLiveStreamStringResId"), zza("pauseStringResId"), zza("playStringResId"), zza("skipNextStringResId"), zza("skipPrevStringResId"), zza("forwardStringResId"), zza("forward10StringResId"), zza("forward30StringResId"), zza("rewindStringResId"), zza("rewind10StringResId"), zza("rewind30StringResId"), zza("disconnectStringResId"), r35, this.zzs, this.zzt);
        L6:
            r12 = r1.zza();
            goto L5
        }

        public Builder setActions(List<String> r6, int[] r7) {
            if (r6 != null) goto L8;
            if (r7 == null) goto L8;
            f$$ExternalSyntheticBUOutline1.m("When setting actions to null, you must also set compatActionIndices to null.");
            return null;
        L8:
            if (r6 == null) goto L13;
            if (r7 != null) goto L13;
            f$$ExternalSyntheticBUOutline1.m("When setting compatActionIndices to null, you must also set actions to null.");
            return null;
        L13:
            if (r6 == null) goto L29;
            if (r7 == null) goto L29;
            int r1 = r7.length;
            int r2 = r6.size();
            if (r1 > r2) goto L27;
            int r3 = 0;
        L18:
            if (r3 >= r1) goto L25;
            int r4 = r7[r3];
            if (r4 < 0) goto L23;
            if (r4 >= r2) goto L23;
            r3 = r3 + 1;
        L23:
            f$$ExternalSyntheticBUOutline1.m(String.format(Locale.ROOT, "Index %d in compatActionIndices out of range: [0, %d]", new Object[]{Integer.valueOf(r4), Integer.valueOf(r2 - 1)}));
            return null;
        L25:
            this.zzb = new ArrayList(r6);
            this.zzd = Arrays.copyOf(r7, r7.length);
            return this;
        L27:
            f$$ExternalSyntheticBUOutline1.m(String.format(Locale.ROOT, "Invalid number of compat actions: %d > %d.", new Object[]{Integer.valueOf(r1), Integer.valueOf(r2)}));
            return null;
        L29:
            this.zzb = NotificationOptions.zzp();
            this.zzd = NotificationOptions.zzq();
            return this;
        }

        public Builder setDisconnectDrawableResId(int r1) {
            this.zzq = r1;
            return this;
        }

        public Builder setForward10DrawableResId(int r1) {
            this.zzl = r1;
            return this;
        }

        public Builder setForward30DrawableResId(int r1) {
            this.zzm = r1;
            return this;
        }

        public Builder setForwardDrawableResId(int r1) {
            this.zzk = r1;
            return this;
        }

        public Builder setNotificationActionsProvider(NotificationActionsProvider r1) {
            if (r1 == null) goto L5;
            this.zzc = r1;
            return this;
        L5:
            f$$ExternalSyntheticBUOutline1.m("notificationActionsProvider cannot be null.");
            return null;
        }

        public Builder setPauseDrawableResId(int r1) {
            this.zzg = r1;
            return this;
        }

        public Builder setPlayDrawableResId(int r1) {
            this.zzh = r1;
            return this;
        }

        public Builder setRewind10DrawableResId(int r1) {
            this.zzo = r1;
            return this;
        }

        public Builder setRewind30DrawableResId(int r1) {
            this.zzp = r1;
            return this;
        }

        public Builder setRewindDrawableResId(int r1) {
            this.zzn = r1;
            return this;
        }

        public Builder setSkipNextDrawableResId(int r1) {
            this.zzi = r1;
            return this;
        }

        public Builder setSkipPrevDrawableResId(int r1) {
            this.zzj = r1;
            return this;
        }

        public Builder setSkipStepMs(long r3) {
            if (r3 <= 0) goto L5;
            boolean r0 = true;
        L6:
            Preconditions.checkArgument(r0, "skipStepMs must be positive.");
            this.zzr = r3;
            return this;
        L5:
            r0 = false;
            goto L6
        }

        public Builder setSkipToNextSlotReserved(boolean r1) {
            this.zzt = r1;
            return this;
        }

        public Builder setSkipToPrevSlotReserved(boolean r1) {
            this.zzs = r1;
            return this;
        }

        public Builder setSmallIconDrawableResId(int r1) {
            this.zze = r1;
            return this;
        }

        public Builder setStopLiveStreamDrawableResId(int r1) {
            this.zzf = r1;
            return this;
        }

        public Builder setTargetActivityClassName(String r1) {
            this.zza = r1;
            return this;
        }
    }

    static {
        zza = zzhv.zzi(MediaIntentReceiver.ACTION_TOGGLE_PLAYBACK, MediaIntentReceiver.ACTION_STOP_CASTING);
        zzb = new int[]{0, 1};
        CREATOR = new zzs();
    }

    public NotificationOptions(List r3, int[] r4, long r5, String r7, int r8, int r9, int r10, int r11, int r12, int r13, int r14, int r15, int r16, int r17, int r18, int r19, int r20, int r21, int r22, int r23, int r24, int r25, int r26, int r27, int r28, int r29, int r30, int r31, int r32, int r33, int r34, IBinder r35, boolean r36, boolean r37) {
        this.zzc = new ArrayList(r3);
        this.zzd = Arrays.copyOf(r4, r4.length);
        this.zze = r5;
        this.zzf = r7;
        this.zzg = r8;
        this.zzh = r9;
        this.zzi = r10;
        this.zzj = r11;
        this.zzk = r12;
        this.zzl = r13;
        this.zzm = r14;
        this.zzn = r15;
        this.zzo = r16;
        this.zzp = r17;
        this.zzq = r18;
        this.zzr = r19;
        this.zzs = r20;
        this.zzt = r21;
        this.zzu = r22;
        this.zzv = r23;
        this.zzw = r24;
        this.zzx = r25;
        this.zzy = r26;
        this.zzz = r27;
        this.zzA = r28;
        this.zzB = r29;
        this.zzC = r30;
        this.zzD = r31;
        this.zzE = r32;
        this.zzF = r33;
        this.zzG = r34;
        this.zzI = r36;
        this.zzJ = r37;
        if (r35 != null) goto L5;
        zzg r38 = null;
    L9:
        this.zzH = r38;
        return;
    L5:
        IInterface r39 = r35.queryLocalInterface("com.google.android.gms.cast.framework.media.INotificationActionsProvider");
        if ((r39 instanceof zzg) == false) goto L8;
        r38 = (zzg) r39;
        goto L9
    L8:
        r38 = new zze(r35);
        goto L9
    }

    public static /* synthetic */ zzhv zzp() {
        return zza;
    }

    public static /* synthetic */ int[] zzq() {
        return zzb;
    }

    public List<String> getActions() {
        return this.zzc;
    }

    public int getCastingToDeviceStringResId() {
        return this.zzu;
    }

    public int[] getCompatActionIndices() {
        int[] r1 = this.zzd;
        return Arrays.copyOf(r1, r1.length);
    }

    public int getDisconnectDrawableResId() {
        return this.zzs;
    }

    public int getForward10DrawableResId() {
        return this.zzn;
    }

    public int getForward30DrawableResId() {
        return this.zzo;
    }

    public int getForwardDrawableResId() {
        return this.zzm;
    }

    public int getPauseDrawableResId() {
        return this.zzi;
    }

    public int getPlayDrawableResId() {
        return this.zzj;
    }

    public int getRewind10DrawableResId() {
        return this.zzq;
    }

    public int getRewind30DrawableResId() {
        return this.zzr;
    }

    public int getRewindDrawableResId() {
        return this.zzp;
    }

    public int getSkipNextDrawableResId() {
        return this.zzk;
    }

    public int getSkipPrevDrawableResId() {
        return this.zzl;
    }

    public long getSkipStepMs() {
        return this.zze;
    }

    public int getSmallIconDrawableResId() {
        return this.zzg;
    }

    public int getStopLiveStreamDrawableResId() {
        return this.zzh;
    }

    public int getStopLiveStreamTitleResId() {
        return this.zzv;
    }

    public String getTargetActivityClassName() {
        return this.zzf;
    }

    @Override // android.os.Parcelable
    public void writeToParcel(Parcel r6, int r7) {
        int r72 = SafeParcelWriter.beginObjectHeader(r6);
        SafeParcelWriter.writeStringList(r6, 2, getActions(), false);
        SafeParcelWriter.writeIntArray(r6, 3, getCompatActionIndices(), false);
        SafeParcelWriter.writeLong(r6, 4, getSkipStepMs());
        SafeParcelWriter.writeString(r6, 5, getTargetActivityClassName(), false);
        SafeParcelWriter.writeInt(r6, 6, getSmallIconDrawableResId());
        SafeParcelWriter.writeInt(r6, 7, getStopLiveStreamDrawableResId());
        SafeParcelWriter.writeInt(r6, 8, getPauseDrawableResId());
        SafeParcelWriter.writeInt(r6, 9, getPlayDrawableResId());
        SafeParcelWriter.writeInt(r6, 10, getSkipNextDrawableResId());
        SafeParcelWriter.writeInt(r6, 11, getSkipPrevDrawableResId());
        SafeParcelWriter.writeInt(r6, 12, getForwardDrawableResId());
        SafeParcelWriter.writeInt(r6, 13, getForward10DrawableResId());
        SafeParcelWriter.writeInt(r6, 14, getForward30DrawableResId());
        SafeParcelWriter.writeInt(r6, 15, getRewindDrawableResId());
        SafeParcelWriter.writeInt(r6, 16, getRewind10DrawableResId());
        SafeParcelWriter.writeInt(r6, 17, getRewind30DrawableResId());
        SafeParcelWriter.writeInt(r6, 18, getDisconnectDrawableResId());
        SafeParcelWriter.writeInt(r6, 19, this.zzt);
        SafeParcelWriter.writeInt(r6, 20, getCastingToDeviceStringResId());
        SafeParcelWriter.writeInt(r6, 21, getStopLiveStreamTitleResId());
        SafeParcelWriter.writeInt(r6, 22, this.zzw);
        SafeParcelWriter.writeInt(r6, 23, this.zzx);
        SafeParcelWriter.writeInt(r6, 24, this.zzy);
        SafeParcelWriter.writeInt(r6, 25, this.zzz);
        SafeParcelWriter.writeInt(r6, 26, this.zzA);
        SafeParcelWriter.writeInt(r6, 27, this.zzB);
        SafeParcelWriter.writeInt(r6, 28, this.zzC);
        SafeParcelWriter.writeInt(r6, 29, this.zzD);
        SafeParcelWriter.writeInt(r6, 30, this.zzE);
        SafeParcelWriter.writeInt(r6, 31, this.zzF);
        SafeParcelWriter.writeInt(r6, 32, this.zzG);
        zzg r0 = this.zzH;
        if (r0 != null) goto L5;
        IBinder r02 = null;
    L6:
        SafeParcelWriter.writeIBinder(r6, 33, r02, false);
        SafeParcelWriter.writeBoolean(r6, 34, this.zzI);
        SafeParcelWriter.writeBoolean(r6, 35, this.zzJ);
        SafeParcelWriter.finishObjectHeader(r6, r72);
        return;
    L5:
        r02 = r0.asBinder();
        goto L6
    }

    public final int zza() {
        return this.zzt;
    }

    public final int zzb() {
        return this.zzw;
    }

    public final int zzc() {
        return this.zzx;
    }

    public final int zzd() {
        return this.zzy;
    }

    public final int zze() {
        return this.zzz;
    }

    public final int zzf() {
        return this.zzA;
    }

    public final int zzg() {
        return this.zzB;
    }

    public final int zzh() {
        return this.zzC;
    }

    public final int zzi() {
        return this.zzD;
    }

    public final int zzj() {
        return this.zzE;
    }

    public final int zzk() {
        return this.zzF;
    }

    public final int zzl() {
        return this.zzG;
    }

    public final boolean zzm() {
        return this.zzI;
    }

    public final boolean zzn() {
        return this.zzJ;
    }

    public final zzg zzo() {
        return this.zzH;
    }
}
