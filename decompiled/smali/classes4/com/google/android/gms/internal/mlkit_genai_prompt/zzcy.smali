.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzv;


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;",
            ">;"
        }
    .end annotation
.end field

.field public static final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

.field private static final zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;


# instance fields
.field private final zzc:I

.field private final zzd:Ljava/lang/String;

.field private final zze:Z

.field private final zzf:I

.field private final zzg:D

.field private final zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzi:J


# direct methods
.method static constructor <clinit>()V
    .locals 9

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v0

    .line 2
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v1

    const/4 v2, 0x1

    invoke-virtual {v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v1

    .line 3
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v3

    const/4 v4, 0x2

    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v3

    .line 4
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v5

    const/4 v6, 0x3

    invoke-virtual {v5, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v5

    .line 5
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v7

    const/4 v8, 0x4

    invoke-virtual {v7, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v7

    .line 6
    invoke-static {v1, v3, v5, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzo(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    .line 7
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzl(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 8
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzb:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    .line 9
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v1

    .line 10
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v3

    invoke-virtual {v3, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v2

    .line 11
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v3

    invoke-virtual {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v3

    .line 12
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v4

    invoke-virtual {v4, v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v4

    .line 13
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v5

    invoke-virtual {v5, v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v5

    .line 14
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v6

    const/4 v7, 0x5

    .line 15
    invoke-virtual {v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 16
    invoke-virtual {v6, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 17
    invoke-virtual {v6}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v0

    .line 18
    invoke-static {v2, v3, v4, v5, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzp(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v0

    .line 19
    invoke-virtual {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzl(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v0, "The actual value of the argument"

    .line 20
    invoke-virtual {v1, v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 21
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v0

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcz;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcz;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;ZIDLjava/util/List;J)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzc:I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzd:Ljava/lang/String;

    iput-boolean p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zze:Z

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzf:I

    iput-wide p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzg:D

    .line 2
    invoke-static {p7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput-wide p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzi:J

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 2

    .line 5
    iget v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzc:I

    if-eqz v0, :cond_5

    const/4 v1, 0x1

    if-eq v0, v1, :cond_4

    const/4 v1, 0x2

    if-eq v0, v1, :cond_3

    const/4 v1, 0x3

    if-eq v0, v1, :cond_2

    const/4 v1, 0x4

    if-eq v0, v1, :cond_1

    const/4 v1, 0x5

    if-ne v0, v1, :cond_0

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzi:J

    .line 1
    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(J)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 6
    :cond_0
    invoke-static {v0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    invoke-virtual {p0}, Ljava/lang/String;->length()I

    move-result p0

    add-int/lit8 p0, p0, 0x12

    const-string v1, "Unsupported kind: "

    invoke-static {p0, v1, v0}, Lcom/google/android/gms/cast/internal/zzar$$ExternalSyntheticBUOutline1;->m(ILjava/lang/Object;I)V

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_1
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 2
    invoke-virtual {p0}, Ljava/lang/Object;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_2
    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzg:D

    .line 3
    invoke-static {v0, v1}, Ljava/lang/String;->valueOf(D)Ljava/lang/String;

    move-result-object p0

    return-object p0

    :cond_3
    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzf:I

    .line 4
    invoke-static {p0}, Ljava/lang/String;->valueOf(I)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 5
    :cond_4
    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zze:Z

    invoke-static {p0}, Ljava/lang/String;->valueOf(Z)Ljava/lang/String;

    move-result-object p0

    return-object p0

    .line 6
    :cond_5
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 5

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result p2

    const/4 v0, 0x1

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzc:I

    .line 2
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzd:Ljava/lang/String;

    const/4 v1, 0x2

    const/4 v2, 0x0

    .line 3
    invoke-static {p1, v1, v0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/4 v0, 0x3

    iget-boolean v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zze:Z

    .line 4
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBoolean(Landroid/os/Parcel;IZ)V

    const/4 v0, 0x4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzf:I

    .line 5
    invoke-static {p1, v0, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 v0, 0x5

    iget-wide v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzg:D

    .line 6
    invoke-static {p1, v0, v3, v4}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeDouble(Landroid/os/Parcel;ID)V

    const/4 v0, 0x6

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 7
    invoke-static {p1, v0, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeTypedList(Landroid/os/Parcel;ILjava/util/List;Z)V

    const/4 v0, 0x7

    iget-wide v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzi:J

    .line 8
    invoke-static {p1, v0, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeLong(Landroid/os/Parcel;IJ)V

    .line 9
    invoke-static {p1, p2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zza()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzc:I

    return p0
.end method

.method public final zzb()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzd:Ljava/lang/String;

    return-object p0
.end method

.method public final zzc()Z
    .locals 0

    iget-boolean p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zze:Z

    return p0
.end method

.method public final zzd()I
    .locals 0

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzf:I

    return p0
.end method

.method public final zze()D
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzg:D

    return-wide v0
.end method

.method public final zzf()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    return-object p0
.end method

.method public final zzg()J
    .locals 2

    iget-wide v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zzi:J

    return-wide v0
.end method
