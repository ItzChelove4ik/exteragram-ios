.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Ljava/lang/String;

.field private final zzc:I

.field private final zzd:I

.field private final zze:I

.field private final zzf:I

.field private final zzg:I

.field private final zzh:Ljava/lang/String;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzo;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzo;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzb:Ljava/lang/String;

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    iput p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    iput p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzg:I

    iput-object p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzh:Ljava/lang/String;

    return-void
.end method

.method public static zza(Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;
    .locals 9

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    move-object v1, p0

    move-object v2, p1

    move v3, p2

    move v4, p3

    move v5, p4

    move v6, p5

    move v7, p6

    move-object/from16 v8, p7

    invoke-direct/range {v0 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;-><init>(Ljava/lang/String;Ljava/lang/String;IIIIILjava/lang/String;)V

    return-object v0
.end method


# virtual methods
.method public final equals(Ljava/lang/Object;)Z
    .locals 4

    const/4 v0, 0x1

    if-ne p0, p1, :cond_0

    return v0

    .line 1
    :cond_0
    instance-of v1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    const/4 v2, 0x0

    if-nez v1, :cond_1

    return v2

    :cond_1
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    .line 2
    iget v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    if-ne v1, v3, :cond_2

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    iget v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    if-ne v1, v3, :cond_2

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    iget-object v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    .line 3
    invoke-static {v1, v3}, Ljava/util/Objects;->equals(Ljava/lang/Object;Ljava/lang/Object;)Z

    move-result v1

    if-eqz v1, :cond_2

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    iget v3, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    if-ne v1, v3, :cond_2

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    iget p1, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    if-ne p0, p1, :cond_2

    return v0

    :cond_2
    return v2
.end method

.method public final hashCode()I
    .locals 4

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    invoke-static {v1}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    invoke-static {v2}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v2

    iget v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    invoke-static {v3}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object v3

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    invoke-static {p0}, Ljava/lang/Integer;->valueOf(I)Ljava/lang/Integer;

    move-result-object p0

    filled-new-array {v0, v1, v2, v3, p0}, [Ljava/lang/Object;

    move-result-object p0

    invoke-static {p0}, Ljava/util/Objects;->hash([Ljava/lang/Object;)I

    move-result p0

    return p0
.end method

.method public final toString()Ljava/lang/String;
    .locals 3

    .line 1
    invoke-static {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzky;->zza(Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    move-result-object v0

    const-string v1, "name"

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    .line 2
    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zza(Ljava/lang/String;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    const-string v1, "modelName"

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzb:Ljava/lang/String;

    .line 3
    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zza(Ljava/lang/String;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    const-string v1, "type"

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    .line 4
    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb(Ljava/lang/String;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    const-string v1, "variant"

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    .line 5
    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb(Ljava/lang/String;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    const-string v1, "id"

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    .line 6
    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb(Ljava/lang/String;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    const-string v1, "version"

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    .line 7
    invoke-virtual {v0, v1, p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->zzb(Ljava/lang/String;I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkx;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 3

    .line 1
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 2
    invoke-static {p1, v1, p2, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/4 p2, 0x2

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzb:Ljava/lang/String;

    .line 3
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/4 p2, 0x3

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    .line 4
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 p2, 0x4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    .line 5
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 p2, 0x5

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    .line 6
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 p2, 0x6

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    .line 7
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 p2, 0x7

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzg:I

    .line 8
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 p2, 0x8

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzh:Ljava/lang/String;

    .line 9
    invoke-static {p1, p2, p0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    .line 10
    invoke-static {p1, v0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzb:Ljava/lang/String;

    return-object p0
.end method

.method public final zzd()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzc:I

    return p0
.end method

.method public final zze()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzd:I

    return p0
.end method

.method public final zzf()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zze:I

    return p0
.end method

.method public final zzg()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzf:I

    return p0
.end method

.method public final zzh()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzg:I

    return p0
.end method

.method public final zzi()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzn;->zzh:Ljava/lang/String;

    return-object p0
.end method
