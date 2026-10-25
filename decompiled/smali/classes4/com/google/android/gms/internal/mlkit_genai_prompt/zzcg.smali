.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;
.source "SourceFile"


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;",
            ">;"
        }
    .end annotation
.end field

.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;


# instance fields
.field private final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzc:F

.field private final zzd:I

.field private final zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzf:I

.field private final zzg:I

.field private final zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

.field private final zzi:Z

.field private final zzj:I

.field private final zzk:I

.field private final zzl:I

.field private final zzm:I

.field private final zzn:F

.field private final zzo:Ljava/lang/String;

.field private final zzp:Landroid/os/Bundle;

.field private final zzq:I

.field private final zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

.field private final zzs:I

.field private final zzt:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

.field private final zzu:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

.field private final zzv:Z

.field private final zzw:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;

.field private final zzx:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;


# direct methods
.method static constructor <clinit>()V
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzch;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzch;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->CREATOR:Landroid/os/Parcelable$Creator;

    const-string v0, "auxiliary_drafter_lora_file"

    const-string v1, "auxiliary_session_state_file"

    const-string v2, "auxiliary_lora_file"

    invoke-static {v2, v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzn(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FILcom/google/android/gms/internal/mlkit_genai_prompt/zzml;IILcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;ZILcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;IILjava/lang/String;Landroid/os/Bundle;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;IFLcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzc:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzd:I

    move/from16 p1, p20

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzn:F

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzf:I

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzg:I

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

    iput-boolean p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzi:Z

    iput p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzj:I

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzx:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    iput p11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzk:I

    iput p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzl:I

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzo:Ljava/lang/String;

    iput-object p14, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzp:Landroid/os/Bundle;

    iput p15, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzq:I

    if-nez p16, :cond_0

    .line 2
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    goto :goto_0

    :cond_0
    move-object/from16 p1, p16

    :goto_0
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    const/4 p1, 0x0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzs:I

    move-object/from16 p2, p18

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzt:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzm:I

    move-object/from16 p1, p21

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzu:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move/from16 p1, p22

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzv:Z

    move-object/from16 p1, p23

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzw:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;

    return-void
.end method

.method public constructor <init>(Ljava/util/List;FILjava/util/List;IILandroid/os/IBinder;ZILandroid/os/IBinder;IILjava/lang/String;Landroid/os/Bundle;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;IFLcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;)V
    .locals 0

    .line 3
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;-><init>()V

    .line 4
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzc:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzd:I

    move/from16 p1, p20

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzn:F

    .line 5
    invoke-static {p4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzf:I

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzg:I

    const/4 p1, 0x0

    if-nez p7, :cond_0

    move-object p2, p1

    goto :goto_0

    .line 9
    :cond_0
    const-string p2, "com.google.android.apps.aicore.aidl.ILLMStreamingCallback"

    .line 6
    invoke-interface {p7, p2}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p2

    instance-of p3, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

    if-eqz p3, :cond_1

    .line 7
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

    goto :goto_0

    :cond_1
    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbj;

    invoke-direct {p2, p7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbj;-><init>(Landroid/os/IBinder;)V

    .line 5
    :goto_0
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

    iput-boolean p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzi:Z

    iput p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzj:I

    if-nez p10, :cond_2

    goto :goto_1

    .line 10
    :cond_2
    const-string p1, "com.google.android.apps.aicore.aidl.IImageEmbeddingCallback"

    .line 8
    invoke-interface {p10, p1}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p1

    instance-of p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    if-eqz p2, :cond_3

    .line 9
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    goto :goto_1

    :cond_3
    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    invoke-direct {p1, p10}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;-><init>(Landroid/os/IBinder;)V

    .line 5
    :goto_1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzx:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    iput p11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzk:I

    iput p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzl:I

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzo:Ljava/lang/String;

    iput-object p14, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzp:Landroid/os/Bundle;

    iput p15, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzq:I

    if-nez p16, :cond_4

    .line 10
    sget-object p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    goto :goto_2

    :cond_4
    move-object/from16 p1, p16

    :goto_2
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    move/from16 p1, p17

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzs:I

    move-object/from16 p1, p18

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzt:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

    move/from16 p1, p19

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzm:I

    move-object/from16 p1, p21

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzu:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move/from16 p1, p22

    iput-boolean p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzv:Z

    move-object/from16 p1, p23

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzw:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzt;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzt;-><init>()V

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzt;->zzj(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzk(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzq(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 4
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzd(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    const/16 v2, 0x7b

    .line 5
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    sget-object v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    .line 6
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzn(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 7
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzo(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    const/high16 v2, 0x3f800000    # 1.0f

    .line 8
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzr(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    .line 9
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzt(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    const/4 v1, 0x0

    .line 10
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;->zzu(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcf;

    return-object v0
.end method


# virtual methods
.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 5

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v1

    const/4 v2, 0x1

    const/4 v3, 0x0

    .line 2
    invoke-static {p1, v2, v0, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeTypedList(Landroid/os/Parcel;ILjava/util/List;Z)V

    const/4 v0, 0x2

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzc:F

    .line 3
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeFloat(Landroid/os/Parcel;IF)V

    const/4 v0, 0x3

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzd:I

    .line 4
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 v0, 0x4

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zze:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 5
    invoke-static {p1, v0, v2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeStringList(Landroid/os/Parcel;ILjava/util/List;Z)V

    const/4 v0, 0x5

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzf:I

    .line 6
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 v0, 0x6

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzg:I

    .line 7
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbl;

    const/4 v2, 0x0

    if-nez v0, :cond_0

    move-object v0, v2

    goto :goto_0

    .line 25
    :cond_0
    invoke-interface {v0}, Landroid/os/IInterface;->asBinder()Landroid/os/IBinder;

    move-result-object v0

    :goto_0
    const/4 v4, 0x7

    .line 8
    invoke-static {p1, v4, v0, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeIBinder(Landroid/os/Parcel;ILandroid/os/IBinder;Z)V

    const/16 v0, 0x8

    iget-boolean v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzi:Z

    .line 9
    invoke-static {p1, v0, v4}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBoolean(Landroid/os/Parcel;IZ)V

    const/16 v0, 0x9

    iget v4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzj:I

    .line 10
    invoke-static {p1, v0, v4}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzx:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbf;

    if-nez v0, :cond_1

    goto :goto_1

    .line 25
    :cond_1
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzk;->asBinder()Landroid/os/IBinder;

    move-result-object v2

    :goto_1
    const/16 v0, 0xa

    .line 11
    invoke-static {p1, v0, v2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeIBinder(Landroid/os/Parcel;ILandroid/os/IBinder;Z)V

    const/16 v0, 0xb

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzk:I

    .line 12
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 v0, 0xc

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzl:I

    .line 13
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 v0, 0xd

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzo:Ljava/lang/String;

    .line 14
    invoke-static {p1, v0, v2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/16 v0, 0xe

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzp:Landroid/os/Bundle;

    .line 15
    invoke-static {p1, v0, v2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBundle(Landroid/os/Parcel;ILandroid/os/Bundle;Z)V

    const/16 v0, 0xf

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzq:I

    .line 16
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 v0, 0x10

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzr:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcw;

    .line 17
    invoke-static {p1, v0, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/16 v0, 0x11

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzs:I

    .line 18
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 v0, 0x12

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzt:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

    .line 19
    invoke-static {p1, v0, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/16 v0, 0x13

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzm:I

    .line 20
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 v0, 0x14

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzn:F

    .line 21
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeFloat(Landroid/os/Parcel;IF)V

    const/16 v0, 0x15

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzu:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    .line 22
    invoke-static {p1, v0, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/16 v0, 0x16

    iget-boolean v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzv:Z

    .line 23
    invoke-static {p1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBoolean(Landroid/os/Parcel;IZ)V

    const/16 v0, 0x17

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzw:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdf;

    .line 24
    invoke-static {p1, v0, p0, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    .line 25
    invoke-static {p1, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zzb()V
    .locals 6

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_2

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    .line 1
    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    .line 2
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zza()Landroid/os/ParcelFileDescriptor;

    move-result-object v5

    if-eqz v5, :cond_0

    .line 3
    invoke-virtual {v5}, Landroid/os/ParcelFileDescriptor;->close()V

    .line 4
    :cond_0
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;

    move-result-object v4

    if-eqz v4, :cond_1

    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;->zza()Landroid/os/ParcelFileDescriptor;

    move-result-object v5

    if-eqz v5, :cond_1

    .line 5
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;->zza()Landroid/os/ParcelFileDescriptor;

    move-result-object v4

    .line 6
    invoke-virtual {v4}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 5
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor;->close()V

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzp:Landroid/os/Bundle;

    if-eqz v0, :cond_4

    sget-object v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-interface {v1}, Ljava/util/List;->size()I

    move-result v3

    :goto_1
    if-ge v2, v3, :cond_4

    invoke-interface {v1, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    .line 7
    check-cast v4, Ljava/lang/String;

    const-class v5, Landroid/os/ParcelFileDescriptor;

    .line 8
    invoke-virtual {v0, v4, v5}, Landroid/os/Bundle;->getParcelable(Ljava/lang/String;Ljava/lang/Class;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Landroid/os/ParcelFileDescriptor;

    if-eqz v4, :cond_3

    .line 9
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor;->close()V

    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_4
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcg;->zzt:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;

    if-eqz p0, :cond_5

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcb;->zza()Landroid/os/ParcelFileDescriptor;

    move-result-object p0

    if-eqz p0, :cond_5

    .line 10
    invoke-virtual {p0}, Landroid/os/ParcelFileDescriptor;->close()V

    :cond_5
    return-void
.end method
