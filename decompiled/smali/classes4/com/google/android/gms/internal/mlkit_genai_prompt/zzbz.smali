.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"

# interfaces
.implements Lcom/google/android/gms/internal/mlkit_genai_prompt/zzv;


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:I

.field private final zzb:Ljava/lang/String;

.field private final zzc:Landroid/graphics/Bitmap;

.field private final zzd:I

.field private final zze:Landroid/os/ParcelFileDescriptor;

.field private final zzf:Z

.field private final zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;

.field private final zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzca;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzca;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(ILjava/lang/String;Landroid/graphics/Bitmap;ILandroid/os/ParcelFileDescriptor;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zza:I

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzb:Ljava/lang/String;

    iput-object p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzc:Landroid/graphics/Bitmap;

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzd:I

    iput-object p5, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zze:Landroid/os/ParcelFileDescriptor;

    iput-boolean p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzf:Z

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;

    iput-object p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;

    return-void
.end method

.method public static zzc(ILandroid/graphics/Bitmap;Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;
    .locals 9

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v2, 0x0

    const/4 v4, 0x1

    const/4 v5, 0x0

    const/4 v6, 0x0

    move v1, p0

    move-object v3, p1

    invoke-direct/range {v0 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;-><init>(ILjava/lang/String;Landroid/graphics/Bitmap;ILandroid/os/ParcelFileDescriptor;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;)V

    return-object v0
.end method

.method public static zzd(ILjava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;
    .locals 9

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;

    const/4 v7, 0x0

    const/4 v8, 0x0

    const/4 v3, 0x0

    const/4 v4, 0x0

    const/4 v5, 0x0

    const/4 v6, 0x0

    move v1, p0

    move-object v2, p1

    invoke-direct/range {v0 .. v8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;-><init>(ILjava/lang/String;Landroid/graphics/Bitmap;ILandroid/os/ParcelFileDescriptor;ZLcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;)V

    return-object v0
.end method


# virtual methods
.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 4

    .line 1
    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v0

    const/4 v1, 0x1

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zza:I

    .line 2
    invoke-static {p1, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzb:Ljava/lang/String;

    const/4 v2, 0x2

    const/4 v3, 0x0

    .line 3
    invoke-static {p1, v2, v1, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/4 v1, 0x3

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzc:Landroid/graphics/Bitmap;

    .line 4
    invoke-static {p1, v1, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/4 v1, 0x4

    iget v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzd:I

    .line 5
    invoke-static {p1, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 v1, 0x5

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zze:Landroid/os/ParcelFileDescriptor;

    .line 6
    invoke-static {p1, v1, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/4 v1, 0x6

    iget-boolean v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzf:Z

    .line 7
    invoke-static {p1, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBoolean(Landroid/os/Parcel;IZ)V

    const/4 v1, 0x7

    iget-object v2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;

    .line 8
    invoke-static {p1, v1, v2, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    const/16 v1, 0x8

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzh:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbx;

    .line 9
    invoke-static {p1, v1, p0, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeParcelable(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V

    .line 10
    invoke-static {p1, v0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zza()Landroid/os/ParcelFileDescriptor;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zze:Landroid/os/ParcelFileDescriptor;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbz;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbv;

    return-object p0
.end method
