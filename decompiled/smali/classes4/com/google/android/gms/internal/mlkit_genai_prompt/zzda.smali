.class public final Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;
.super Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;
.source "SourceFile"


# static fields
.field public static final CREATOR:Landroid/os/Parcelable$Creator;
    .annotation system Ldalvik/annotation/Signature;
        value = {
            "Landroid/os/Parcelable$Creator<",
            "Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;",
            ">;"
        }
    .end annotation
.end field


# instance fields
.field private final zza:Ljava/lang/String;

.field private final zzb:Landroid/os/Bundle;

.field private transient zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;


# direct methods
.method static constructor <clinit>()V
    .locals 8

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v0

    const-string v1, "FunctionCall"

    .line 2
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzn(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const/4 v1, 0x6

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v2, "The function call schema"

    .line 4
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 5
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v2

    const/4 v3, 0x1

    .line 6
    invoke-virtual {v2, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v4, "The function name"

    .line 7
    invoke-virtual {v2, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 8
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v2

    .line 9
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v4

    const/4 v5, 0x5

    .line 10
    invoke-virtual {v4, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v5, "An array of function arguments in an OBJECT format, e.g. [{\'argName\': \'argName1\', \'argValue\': \'argValue1\'}, {\'argName\': \'argName2\', \'argValue\': 123.0}]"

    .line 11
    invoke-virtual {v4, v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 12
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v5

    .line 13
    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v1, "The function argument root schema, which contains the argument name and its value."

    .line 14
    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 15
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v1

    .line 16
    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    const-string v3, "The argument name"

    .line 17
    invoke-virtual {v1, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 18
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v1

    sget-object v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->zza:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    .line 19
    const-string v6, "argName"

    const-string v7, "argValue"

    invoke-static {v6, v1, v7, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zzc(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    .line 20
    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzo(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 21
    invoke-static {v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzm(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzm(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 22
    invoke-static {v6, v7}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzm(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v5, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzi(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 23
    invoke-virtual {v5}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v1

    .line 24
    invoke-virtual {v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 25
    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v1

    .line 26
    const-string v3, "name"

    const-string v4, "args"

    invoke-static {v3, v2, v4, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zzc(Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    .line 27
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzo(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 28
    invoke-static {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzm(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzm(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 29
    invoke-static {v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;->zzm(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzi(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 30
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdb;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzdb;-><init>()V

    sput-object v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->CREATOR:Landroid/os/Parcelable$Creator;

    return-void
.end method

.method public constructor <init>(Ljava/lang/String;Landroid/os/Bundle;)V
    .locals 1

    .line 1
    invoke-direct {p0}, Lcom/google/android/gms/common/internal/safeparcel/AbstractSafeParcelable;-><init>()V

    const/4 v0, 0x0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    iput-object p1, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza:Ljava/lang/String;

    iput-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzb:Landroid/os/Bundle;

    return-void
.end method


# virtual methods
.method public final toString()Ljava/lang/String;
    .locals 4

    .line 1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v0

    if-eqz v0, :cond_1

    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->isEmpty()Z

    move-result v1

    if-eqz v1, :cond_0

    goto :goto_0

    .line 2
    :cond_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza:Ljava/lang/String;

    const-string v1, ", "

    .line 3
    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzku;->zza(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzku;

    move-result-object v1

    const-string v2, "="

    .line 5
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;

    move-result-object v0

    .line 6
    invoke-interface {v0}, Ljava/lang/Iterable;->iterator()Ljava/util/Iterator;

    move-result-object v0

    new-instance v3, Ljava/lang/StringBuilder;

    .line 7
    invoke-direct {v3}, Ljava/lang/StringBuilder;-><init>()V

    .line 8
    :try_start_0
    invoke-static {v3, v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzkt;->zza(Ljava/lang/Appendable;Ljava/util/Iterator;Lcom/google/android/gms/internal/mlkit_genai_prompt/zzku;Ljava/lang/String;)Ljava/lang/Appendable;
    :try_end_0
    .catch Ljava/io/IOException; {:try_start_0 .. :try_end_0} :catch_0

    .line 9
    invoke-virtual {v3}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object v0

    .line 3
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object v1

    invoke-virtual {v1}, Ljava/lang/String;->length()I

    move-result v1

    new-instance v2, Ljava/lang/StringBuilder;

    add-int/lit8 v1, v1, 0x1

    invoke-virtual {v0}, Ljava/lang/String;->length()I

    move-result v3

    add-int/2addr v1, v3

    add-int/lit8 v1, v1, 0x1

    invoke-direct {v2, v1}, Ljava/lang/StringBuilder;-><init>(I)V

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, "("

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2, v0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    const-string p0, ")"

    invoke-virtual {v2, p0}, Ljava/lang/StringBuilder;->append(Ljava/lang/String;)Ljava/lang/StringBuilder;

    invoke-virtual {v2}, Ljava/lang/StringBuilder;->toString()Ljava/lang/String;

    move-result-object p0

    return-object p0

    :catch_0
    move-exception p0

    .line 9
    invoke-static {p0}, Lokio/Buffer$$ExternalSyntheticBUOutline2;->m(Ljava/lang/Object;)V

    const/4 p0, 0x0

    return-object p0

    .line 1
    :cond_1
    :goto_0
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza:Ljava/lang/String;

    .line 2
    invoke-static {p0}, Ljava/lang/String;->valueOf(Ljava/lang/Object;)Ljava/lang/String;

    move-result-object p0

    const-string v0, "()"

    invoke-virtual {p0, v0}, Ljava/lang/String;->concat(Ljava/lang/String;)Ljava/lang/String;

    move-result-object p0

    return-object p0
.end method

.method public final writeToParcel(Landroid/os/Parcel;I)V
    .locals 3

    .line 1
    iget-object p2, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza:Ljava/lang/String;

    invoke-static {p1}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->beginObjectHeader(Landroid/os/Parcel;)I

    move-result v0

    const/4 v1, 0x1

    const/4 v2, 0x0

    .line 2
    invoke-static {p1, v1, p2, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeString(Landroid/os/Parcel;ILjava/lang/String;Z)V

    const/4 p2, 0x2

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzb:Landroid/os/Bundle;

    .line 3
    invoke-static {p1, p2, p0, v2}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->writeBundle(Landroid/os/Parcel;ILandroid/os/Bundle;Z)V

    .line 4
    invoke-static {p1, v0}, Lcom/google/android/gms/common/internal/safeparcel/SafeParcelWriter;->finishObjectHeader(Landroid/os/Parcel;I)V

    return-void
.end method

.method public final zza()Ljava/lang/String;
    .locals 0

    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zza:Ljava/lang/String;

    return-object p0
.end method

.method public final zzb()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;
    .locals 9

    .line 1
    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    if-nez v0, :cond_2

    iget-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzb:Landroid/os/Bundle;

    if-eqz v0, :cond_2

    new-instance v1, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    invoke-direct {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;-><init>()V

    .line 2
    invoke-virtual {v0}, Landroid/os/BaseBundle;->keySet()Ljava/util/Set;

    move-result-object v2

    invoke-interface {v2}, Ljava/util/Set;->iterator()Ljava/util/Iterator;

    move-result-object v2

    :cond_0
    :goto_0
    invoke-interface {v2}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_1

    invoke-interface {v2}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/lang/String;

    .line 3
    sget-object v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;->CREATOR:Landroid/os/Parcelable$Creator;

    .line 4
    invoke-virtual {v0, v3}, Landroid/os/Bundle;->getByteArray(Ljava/lang/String;)[B

    move-result-object v5

    .line 13
    invoke-virtual {v5}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    .line 5
    invoke-static {}, Landroid/os/Parcel;->obtain()Landroid/os/Parcel;

    move-result-object v6

    :try_start_0
    array-length v7, v5

    const/4 v8, 0x0

    .line 6
    invoke-virtual {v6, v5, v8, v7}, Landroid/os/Parcel;->unmarshall([BII)V

    .line 7
    invoke-virtual {v6, v8}, Landroid/os/Parcel;->setDataPosition(I)V

    .line 8
    invoke-interface {v4, v6}, Landroid/os/Parcelable$Creator;->createFromParcel(Landroid/os/Parcel;)Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzv;
    :try_end_0
    .catchall {:try_start_0 .. :try_end_0} :catchall_0

    .line 9
    invoke-virtual {v6}, Landroid/os/Parcel;->recycle()V

    .line 10
    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcy;

    if-eqz v4, :cond_0

    .line 11
    invoke-virtual {v1, v3, v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zza(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    goto :goto_0

    :catchall_0
    move-exception p0

    .line 9
    invoke-virtual {v6}, Landroid/os/Parcel;->recycle()V

    .line 12
    throw p0

    .line 14
    :cond_1
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v0

    iput-object v0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    .line 13
    :cond_2
    iget-object p0, p0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzda;->zzc:Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    return-object p0
.end method
