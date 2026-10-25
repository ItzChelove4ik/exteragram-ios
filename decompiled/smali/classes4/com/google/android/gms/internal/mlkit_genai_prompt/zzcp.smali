.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzv;


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:I

.field private final zzb:Ljava/lang/String;

.field private final zzc:Ljava/lang/String;

.field private final zzd:Ljava/lang/Boolean;

.field private final zze:Ljava/util/List;

.field private final zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

.field private final zzg:Ljava/lang/Long;

.field private final zzh:Ljava/lang/Long;

.field private final zzi:Ljava/util/List;

.field private final zzj:Ljava/lang/Long;

.field private final zzk:Ljava/lang/Long;

.field private final zzl:Ljava/lang/Double;

.field private final zzm:Ljava/lang/Double;

.field private final zzn:Ljava/lang/Long;

.field private final zzo:Ljava/lang/Long;

.field private final zzp:Ljava/lang/String;

.field private final zzq:Ljava/util/List;

.field private final zzr:Ljava/util/List;

.field private final zzs:Ljava/lang/String;

.field private final zzt:Landroid/os/Bundle;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcq;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcq;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;Ljava/util/List;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;Ljava/lang/Long;Ljava/lang/Long;Ljava/util/List;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/Double;Ljava/lang/Double;Ljava/lang/Long;Ljava/lang/Long;Ljava/lang/String;Ljava/util/List;Ljava/util/List;Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zza:I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzb:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzc:Ljava/lang/String;

    iput-object p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzd:Ljava/lang/Boolean;

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zze:Ljava/util/List;

    iput-object p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzg:Ljava/lang/Long;

    iput-object p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzh:Ljava/lang/Long;

    iput-object p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzi:Ljava/util/List;

    iput-object p10, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzj:Ljava/lang/Long;

    iput-object p11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzk:Ljava/lang/Long;

    iput-object p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzl:Ljava/lang/Double;

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzm:Ljava/lang/Double;

    iput-object p14, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzn:Ljava/lang/Long;

    iput-object p15, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzo:Ljava/lang/Long;

    move-object/from16 p1, p16

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzp:Ljava/lang/String;

    move-object/from16 p1, p17

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzq:Ljava/util/List;

    move-object/from16 p1, p18

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzr:Ljava/util/List;

    move-object/from16 p1, p19

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzs:Ljava/lang/String;

    move-object/from16 p1, p20

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzt:Landroid/os/Bundle;

    return-void
.end method

.method public static zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;
    .locals 2

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzu;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzu;-><init>()V

    const/4 v1, 0x0

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzu;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    return-object v0
.end method


# virtual methods
.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 0

    .line 1
    invoke-static {p0, p1, p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcq;->zza(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zza:I

    return p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzb:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzc:Ljava/lang/String;

    return-object p0
.end method

.method public final zzd()Ljava/lang/Boolean;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzd:Ljava/lang/Boolean;

    return-object p0
.end method

.method public final zze()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zze:Ljava/util/List;

    return-object p0
.end method

.method public final zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    return-object p0
.end method

.method public final zzg()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzg:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzh()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzh:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzi()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzi:Ljava/util/List;

    return-object p0
.end method

.method public final zzj()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzj:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzk()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzk:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzl()Ljava/lang/Double;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzl:Ljava/lang/Double;

    return-object p0
.end method

.method public final zzm()Ljava/lang/Double;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzm:Ljava/lang/Double;

    return-object p0
.end method

.method public final zzn()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzn:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzo()Ljava/lang/Long;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzo:Ljava/lang/Long;

    return-object p0
.end method

.method public final zzp()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzp:Ljava/lang/String;

    return-object p0
.end method

.method public final zzq()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzq:Ljava/util/List;

    return-object p0
.end method

.method public final zzr()Ljava/util/List;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzr:Ljava/util/List;

    return-object p0
.end method

.method public final zzs()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzs:Ljava/lang/String;

    return-object p0
.end method

.method public final zzt()Landroid/os/Bundle;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzt:Landroid/os/Bundle;

    return-object p0
.end method
