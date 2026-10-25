.class public abstract Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public constructor <init>()V
    .locals 0

    invoke-direct {p0}, Ljava/lang/Object;-><init>()V

    return-void
.end method

.method public static zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhz;
    .locals 1

    new-instance v0, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhj;

    invoke-direct {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzhj;-><init>()V

    return-object v0
.end method


# virtual methods
.method public abstract zza()Ljava/lang/String;
.end method

.method public abstract zzb()Ljava/lang/String;
.end method

.method public abstract zzc()Ljava/lang/Boolean;
.end method

.method public abstract zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;
.end method

.method public abstract zzf()Ljava/lang/Long;
.end method

.method public abstract zzg()Ljava/lang/Long;
.end method

.method public abstract zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;
.end method

.method public abstract zzj()Ljava/lang/Long;
.end method

.method public abstract zzk()Ljava/lang/Long;
.end method

.method public abstract zzl()Ljava/lang/Double;
.end method

.method public abstract zzm()Ljava/lang/Double;
.end method

.method public abstract zzn()Ljava/lang/Long;
.end method

.method public abstract zzo()Ljava/lang/Long;
.end method

.method public abstract zzp()Ljava/lang/String;
.end method

.method public abstract zzq()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzr()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;
.end method

.method public abstract zzs()Ljava/lang/String;
.end method

.method public abstract zzt()I
.end method

.method public final zzv()Lorg/json/JSONObject;
    .locals 6

    .line 1
    new-instance v0, Lorg/json/JSONObject;

    invoke-direct {v0}, Lorg/json/JSONObject;-><init>()V

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzt()I

    move-result v1

    if-eqz v1, :cond_0

    add-int/lit8 v2, v1, -0x1

    packed-switch v2, :pswitch_data_0

    invoke-static {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzia;->zza(I)Ljava/lang/String;

    move-result-object v1

    sget-object v2, Ljava/util/Locale;->US:Ljava/util/Locale;

    .line 2
    invoke-virtual {v1, v2}, Ljava/lang/String;->toLowerCase(Ljava/util/Locale;)Ljava/lang/String;

    move-result-object v1

    goto :goto_0

    .line 25
    :pswitch_0
    const-string v1, "object"

    goto :goto_0

    :pswitch_1
    const-string v1, "array"

    goto :goto_0

    :pswitch_2
    const-string v1, "boolean"

    goto :goto_0

    :pswitch_3
    const-string v1, "integer"

    goto :goto_0

    :pswitch_4
    const-string v1, "number"

    goto :goto_0

    :pswitch_5
    const-string v1, "string"

    .line 2
    :goto_0
    const-string v2, "type"

    .line 3
    invoke-virtual {v0, v2, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_0
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzb()Ljava/lang/String;

    move-result-object v1

    const-string v2, "description"

    .line 4
    invoke-virtual {v0, v2, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zza()Ljava/lang/String;

    move-result-object v1

    const-string v2, "format"

    .line 5
    invoke-virtual {v0, v2, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzc()Ljava/lang/Boolean;

    move-result-object v1

    const-string v2, "nullable"

    .line 6
    invoke-virtual {v0, v2, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string v1, "title"

    const/4 v2, 0x0

    .line 7
    invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-eqz v1, :cond_1

    .line 8
    invoke-virtual {v1}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_1

    .line 9
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3, v1}, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V

    const-string v1, "enum"

    invoke-virtual {v0, v1, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_1
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzf()Ljava/lang/Long;

    move-result-object v1

    const-string v3, "maxItems"

    .line 10
    invoke-virtual {v0, v3, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzg()Ljava/lang/Long;

    move-result-object v1

    const-string v3, "minItems"

    .line 11
    invoke-virtual {v0, v3, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v1

    if-eqz v1, :cond_2

    const-string v3, "items"

    .line 12
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzv()Lorg/json/JSONObject;

    move-result-object v1

    invoke-virtual {v0, v3, v1}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 13
    invoke-virtual {v1}, Ljava/util/AbstractCollection;->isEmpty()Z

    move-result v3

    if-nez v3, :cond_3

    .line 14
    new-instance v3, Lorg/json/JSONArray;

    invoke-direct {v3, v1}, Lorg/json/JSONArray;-><init>(Ljava/util/Collection;)V

    const-string v1, "required"

    invoke-virtual {v0, v1, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_3
    const-string v1, "maxProperties"

    .line 15
    invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string v1, "minProperties"

    .line 16
    invoke-virtual {v0, v1, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    if-eqz v1, :cond_5

    new-instance v3, Lorg/json/JSONObject;

    .line 17
    invoke-direct {v3}, Lorg/json/JSONObject;-><init>()V

    .line 18
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v4

    if-eqz v4, :cond_4

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/util/Map$Entry;

    .line 19
    invoke-interface {v4}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v5

    check-cast v5, Ljava/lang/String;

    invoke-interface {v4}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    invoke-virtual {v4}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzv()Lorg/json/JSONObject;

    move-result-object v4

    invoke-virtual {v3, v5, v4}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    goto :goto_1

    :cond_4
    const-string v1, "properties"

    .line 20
    invoke-virtual {v0, v1, v3}, Lorg/json/JSONObject;->put(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    :cond_5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzm()Ljava/lang/Double;

    move-result-object v1

    const-string v3, "maximum"

    .line 21
    invoke-virtual {v0, v3, v1}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzl()Ljava/lang/Double;

    move-result-object p0

    const-string v1, "minimum"

    .line 22
    invoke-virtual {v0, v1, p0}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string p0, "maxLength"

    .line 23
    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string p0, "minLength"

    .line 24
    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    const-string p0, "pattern"

    .line 25
    invoke-virtual {v0, p0, v2}, Lorg/json/JSONObject;->putOpt(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;

    return-object v0

    nop

    :pswitch_data_0
    .packed-switch 0x1
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method

.method public final zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;
    .locals 5

    .line 1
    invoke-static {}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;->zzu()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v0

    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzt()I

    move-result v1

    if-eqz v1, :cond_2

    add-int/lit8 v1, v1, -0x1

    if-eqz v1, :cond_0

    const/4 v2, 0x1

    if-eq v1, v2, :cond_1

    const/4 v2, 0x2

    if-eq v1, v2, :cond_1

    const/4 v2, 0x3

    if-eq v1, v2, :cond_1

    const/4 v2, 0x4

    if-eq v1, v2, :cond_1

    const/4 v2, 0x5

    if-eq v1, v2, :cond_1

    const/4 v2, 0x6

    goto :goto_0

    :cond_0
    const/4 v2, 0x0

    .line 2
    :cond_1
    :goto_0
    invoke-virtual {v0, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zza(I)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_2
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zza()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_3

    .line 3
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzb(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_3
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzb()Ljava/lang/String;

    move-result-object v1

    if-eqz v1, :cond_4

    .line 4
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzc(Ljava/lang/String;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_4
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzc()Ljava/lang/Boolean;

    move-result-object v1

    if-eqz v1, :cond_5

    .line 5
    invoke-virtual {v1}, Ljava/lang/Boolean;->booleanValue()Z

    move-result v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzd(Z)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_5
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzd()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-eqz v1, :cond_6

    .line 6
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzq(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    move-result-object v0

    :cond_6
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    move-result-object v1

    if-eqz v1, :cond_7

    .line 7
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzf(Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_7
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzf()Ljava/lang/Long;

    move-result-object v1

    if-eqz v1, :cond_8

    .line 8
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzg(J)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_8
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzg()Ljava/lang/Long;

    move-result-object v1

    if-eqz v1, :cond_9

    .line 9
    invoke-virtual {v1}, Ljava/lang/Long;->longValue()J

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzh(J)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_9
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzh()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzml;

    move-result-object v1

    if-eqz v1, :cond_a

    .line 10
    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzi(Ljava/util/List;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_a
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzi()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    if-eqz v1, :cond_c

    new-instance v2, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    .line 11
    invoke-direct {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;-><init>()V

    .line 12
    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;->zze()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;

    move-result-object v1

    invoke-virtual {v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzms;->zza()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzns;

    move-result-object v1

    :goto_1
    invoke-interface {v1}, Ljava/util/Iterator;->hasNext()Z

    move-result v3

    if-eqz v3, :cond_b

    invoke-interface {v1}, Ljava/util/Iterator;->next()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Ljava/util/Map$Entry;

    .line 13
    invoke-interface {v3}, Ljava/util/Map$Entry;->getKey()Ljava/lang/Object;

    move-result-object v4

    check-cast v4, Ljava/lang/String;

    invoke-interface {v3}, Ljava/util/Map$Entry;->getValue()Ljava/lang/Object;

    move-result-object v3

    check-cast v3, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;

    invoke-virtual {v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzw()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object v3

    invoke-virtual {v2, v4, v3}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zza(Ljava/lang/Object;Ljava/lang/Object;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;

    goto :goto_1

    .line 14
    :cond_b
    invoke-virtual {v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmn;->zzc()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzmo;

    move-result-object v1

    invoke-virtual {v0, v1}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzo(Ljava/util/Map;)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_c
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzl()Ljava/lang/Double;

    move-result-object v1

    if-eqz v1, :cond_d

    .line 15
    invoke-virtual {v1}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzj(D)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    :cond_d
    invoke-virtual {p0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzib;->zzm()Ljava/lang/Double;

    move-result-object p0

    if-eqz p0, :cond_e

    .line 16
    invoke-virtual {p0}, Ljava/lang/Double;->doubleValue()D

    move-result-wide v1

    invoke-virtual {v0, v1, v2}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzk(D)Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;

    .line 17
    :cond_e
    invoke-virtual {v0}, Lcom/google/android/gms/internal/mlkit_genai_prompt/zzco;->zzp()Lcom/google/android/gms/internal/mlkit_genai_prompt/zzcp;

    move-result-object p0

    return-object p0
.end method
