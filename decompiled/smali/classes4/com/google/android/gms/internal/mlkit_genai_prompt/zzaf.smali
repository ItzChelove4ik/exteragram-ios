.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;
.super Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;
.source "SourceFile"


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzb:F

.field private final zzc:I

.field private final zzd:I

.field private final zze:I

.field private final zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

.field private final zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

.field private final zzh:I

.field private final zzi:I

.field private final zzj:Ljava/lang/String;

.field private final zzk:Ljava/lang/String;

.field private final zzl:Ljava/lang/String;

.field private final zzm:Ljava/lang/String;
    .annotation runtime Ljava/lang/Deprecated;
    .end annotation
.end field

.field private final zzn:I

.field private final zzo:I

.field private final zzp:I

.field private final zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;


# direct methods
.method static constructor <clinit>()V
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzag;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzag;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;FIILcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;ILcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V
    .locals 0

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;-><init>()V

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 p1, 0x0

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzb:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzc:I

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzd:I

    const/4 p1, 0x0

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zze:I

    iput-object p7, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    iput-object p8, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    const/4 p2, 0x0

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzh:I

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzi:I

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzj:Ljava/lang/String;

    iput-object p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzk:Ljava/lang/String;

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzl:Ljava/lang/String;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzm:Ljava/lang/String;

    iput p15, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzn:I

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzo:I

    move/from16 p1, p17

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzp:I

    return-void
.end method

.method public constructor <init>(Ljava/util/List;FIILandroid/os/IBinder;ILandroid/os/IBinder;Ljava/util/List;IILjava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;III)V
    .locals 0

    .line 2
    invoke-direct {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzp;-><init>()V

    .line 3
    invoke-static {p1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzb:F

    iput p3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzc:I

    iput p4, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzd:I

    const/4 p1, 0x0

    if-nez p5, :cond_0

    move-object p2, p1

    goto :goto_0

    .line 7
    :cond_0
    const-string p2, "com.google.android.apps.aicore.aidl.ICortanaStreamingCallback"

    .line 4
    invoke-interface {p5, p2}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p2

    instance-of p3, p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    if-eqz p3, :cond_1

    .line 5
    check-cast p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    goto :goto_0

    :cond_1
    new-instance p2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    invoke-direct {p2, p5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;-><init>(Landroid/os/IBinder;)V

    .line 3
    :goto_0
    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    iput p6, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zze:I

    if-nez p7, :cond_2

    goto :goto_1

    .line 8
    :cond_2
    const-string p1, "com.google.android.apps.aicore.aidl.ICortanaStateCallback"

    .line 6
    invoke-interface {p7, p1}, Landroid/os/IBinder;->queryLocalInterface(Ljava/lang/String;)Landroid/os/IInterface;

    move-result-object p1

    instance-of p2, p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    if-eqz p2, :cond_3

    .line 7
    check-cast p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    goto :goto_1

    :cond_3
    new-instance p1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaz;

    invoke-direct {p1, p7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaz;-><init>(Landroid/os/IBinder;)V

    .line 3
    :goto_1
    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    .line 8
    invoke-static {p8}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzq(Ljava/util/Collection;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object p1

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    iput p9, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzh:I

    iput p10, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzi:I

    iput-object p11, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzj:Ljava/lang/String;

    iput-object p12, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzk:Ljava/lang/String;

    iput-object p13, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzl:Ljava/lang/String;

    iput-object p14, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzm:Ljava/lang/String;

    iput p15, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzn:I

    move/from16 p1, p16

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzo:I

    move/from16 p1, p17

    iput p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzp:I

    return-void
.end method

.method public static zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;
    .locals 3

    .line 1
    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;-><init>()V

    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzs;->zza(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/4 v1, 0x0

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzb(F)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/4 v1, 0x1

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzc(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/16 v2, 0x1f4

    .line 4
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzd(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/4 v2, 0x0

    .line 5
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzh(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 6
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzi(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 7
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzm(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 8
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzk()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v2

    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzg(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 9
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zze(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const/4 v2, -0x1

    .line 10
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzl(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    .line 11
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzn(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    const-string v1, "cortana_type_unspecified"

    .line 12
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;->zzj(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzae;

    return-object v0
.end method


# virtual methods
.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 4

    .line 1
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 2
    invoke-static {p1, v1, p2, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeTypedList(Landroid/os/Parcel;ILjava/util/List;Z)V

    const/4 p2, 0x2

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzb:F

    .line 3
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeFloat(Landroid/os/Parcel;IF)V

    const/4 p2, 0x3

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzc:I

    .line 4
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/4 p2, 0x4

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzd:I

    .line 5
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzq:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbc;

    const/4 v1, 0x0

    if-nez p2, :cond_0

    move-object p2, v1

    goto :goto_0

    .line 19
    :cond_0
    invoke-virtual {p2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzk;->asBinder()Landroid/os/IBinder;

    move-result-object p2

    :goto_0
    const/4 v3, 0x5

    .line 6
    invoke-static {p1, v3, p2, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeIBinder(Landroid/os/Parcel;ILandroid/os/IBinder;Z)V

    const/4 p2, 0x6

    iget v3, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zze:I

    .line 7
    invoke-static {p1, p2, v3}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzf:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzbb;

    if-nez p2, :cond_1

    goto :goto_1

    .line 19
    :cond_1
    invoke-interface {p2}, Landroid/os/IInterface;->asBinder()Landroid/os/IBinder;

    move-result-object v1

    :goto_1
    const/4 p2, 0x7

    .line 8
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeIBinder(Landroid/os/Parcel;ILandroid/os/IBinder;Z)V

    const/16 p2, 0x8

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    .line 9
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeTypedList(Landroid/os/Parcel;ILjava/util/List;Z)V

    const/16 p2, 0x9

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzh:I

    .line 10
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 p2, 0xa

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzi:I

    .line 11
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 p2, 0xb

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzj:Ljava/lang/String;

    .line 12
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/16 p2, 0xc

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzk:Ljava/lang/String;

    .line 13
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/16 p2, 0xd

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzl:Ljava/lang/String;

    .line 14
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/16 p2, 0xe

    iget-object v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzm:Ljava/lang/String;

    .line 15
    invoke-static {p1, p2, v1, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/16 p2, 0xf

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzn:I

    .line 16
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 p2, 0x10

    iget v1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzo:I

    .line 17
    invoke-static {p1, p2, v1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    const/16 p2, 0x11

    iget p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzp:I

    .line 18
    invoke-static {p1, p2, p0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeInt(Landroid/os/Parcel;II)V

    .line 19
    invoke-static {p1, v0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zzb()V
    .locals 6

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    invoke-interface {v0}, Ljava/util/List;->size()I

    move-result v1

    const/4 v2, 0x0

    move v3, v2

    :goto_0
    if-ge v3, v1, :cond_2

    invoke-interface {v0, v3}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v4

    .line 1
    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaa;

    .line 2
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaa;->zza()Landroid/os/ParcelFileDescriptor;

    move-result-object v5

    if-eqz v5, :cond_0

    .line 3
    invoke-virtual {v5}, Landroid/os/ParcelFileDescriptor;->close()V

    .line 4
    :cond_0
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaa;->zzb()Landroid/os/ParcelFileDescriptor;

    move-result-object v4

    if-eqz v4, :cond_1

    .line 5
    invoke-virtual {v4}, Landroid/os/ParcelFileDescriptor;->close()V

    :cond_1
    add-int/lit8 v3, v3, 0x1

    goto :goto_0

    :cond_2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzaf;->zzg:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    if-eqz p0, :cond_4

    invoke-interface {p0}, Ljava/util/List;->size()I

    move-result v0

    :goto_1
    if-ge v2, v0, :cond_4

    invoke-interface {p0, v2}, Ljava/util/List;->get(I)Ljava/lang/Object;

    move-result-object v1

    .line 6
    check-cast v1, Landroid/os/ParcelFileDescriptor;

    if-eqz v1, :cond_3

    .line 7
    invoke-virtual {v1}, Landroid/os/ParcelFileDescriptor;->close()V

    :cond_3
    add-int/lit8 v2, v2, 0x1

    goto :goto_1

    :cond_4
    return-void
.end method
