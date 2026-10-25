.class public abstract Lorg/telegram/localization/LocalizationUtils;
.super Ljava/lang/Object;
.source "SourceFile"


# direct methods
.method public static getLocalizationAsset(Ljava/util/Locale;)Ljava/lang/String;
    .locals 43

    const/4 v0, 0x0

    if-nez p0, :cond_0

    return-object v0

    .line 18
    :cond_0
    invoke-virtual/range {p0 .. p0}, Ljava/util/Locale;->toLanguageTag()Ljava/lang/String;

    move-result-object v1

    .line 20
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v2

    const-string v3, "fr"

    const-string v5, "fi"

    const-string v7, "fa"

    const-string v9, "es"

    const/4 v10, 0x6

    const-string v11, "en"

    const/4 v12, 0x5

    const-string v13, "de"

    const/4 v14, 0x4

    const-string v15, "cs"

    const/16 v16, 0x3

    move-object/from16 v17, v0

    const-string v0, "ca"

    const/16 v18, 0x2

    const-string v4, "be"

    const/16 v19, 0x1

    const-string v6, "az"

    const/16 v20, 0x0

    const-string v8, "ar"

    const/16 v21, -0x1

    sparse-switch v2, :sswitch_data_0

    :goto_0
    move/from16 v1, v21

    goto/16 :goto_1

    :sswitch_0
    const-string/jumbo v2, "zh-TW"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1

    goto :goto_0

    :cond_1
    const/16 v1, 0x21

    goto/16 :goto_1

    :sswitch_1
    const-string/jumbo v2, "zh-CN"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_2

    goto :goto_0

    :cond_2
    const/16 v1, 0x20

    goto/16 :goto_1

    :sswitch_2
    const-string v2, "pt-BR"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_3

    goto :goto_0

    :cond_3
    const/16 v1, 0x1f

    goto/16 :goto_1

    :sswitch_3
    const-string/jumbo v2, "uz"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_4

    goto :goto_0

    :cond_4
    const/16 v1, 0x1e

    goto/16 :goto_1

    :sswitch_4
    const-string/jumbo v2, "uk"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_5

    goto :goto_0

    :cond_5
    const/16 v1, 0x1d

    goto/16 :goto_1

    :sswitch_5
    const-string/jumbo v2, "tr"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_6

    goto :goto_0

    :cond_6
    const/16 v1, 0x1c

    goto/16 :goto_1

    :sswitch_6
    const-string v2, "sv"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_7

    goto :goto_0

    :cond_7
    const/16 v1, 0x1b

    goto/16 :goto_1

    :sswitch_7
    const-string v2, "sr"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_8

    goto :goto_0

    :cond_8
    const/16 v1, 0x1a

    goto/16 :goto_1

    :sswitch_8
    const-string v2, "sk"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_9

    goto :goto_0

    :cond_9
    const/16 v1, 0x19

    goto/16 :goto_1

    :sswitch_9
    const-string v2, "ru"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_a

    goto/16 :goto_0

    :cond_a
    const/16 v1, 0x18

    goto/16 :goto_1

    :sswitch_a
    const-string v2, "ro"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_b

    goto/16 :goto_0

    :cond_b
    const/16 v1, 0x17

    goto/16 :goto_1

    :sswitch_b
    const-string v2, "pl"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_c

    goto/16 :goto_0

    :cond_c
    const/16 v1, 0x16

    goto/16 :goto_1

    :sswitch_c
    const-string v2, "nl"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_d

    goto/16 :goto_0

    :cond_d
    const/16 v1, 0x15

    goto/16 :goto_1

    :sswitch_d
    const-string v2, "nb"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_e

    goto/16 :goto_0

    :cond_e
    const/16 v1, 0x14

    goto/16 :goto_1

    :sswitch_e
    const-string v2, "ms"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_f

    goto/16 :goto_0

    :cond_f
    const/16 v1, 0x13

    goto/16 :goto_1

    :sswitch_f
    const-string v2, "ko"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_10

    goto/16 :goto_0

    :cond_10
    const/16 v1, 0x12

    goto/16 :goto_1

    :sswitch_10
    const-string v2, "kk"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_11

    goto/16 :goto_0

    :cond_11
    const/16 v1, 0x11

    goto/16 :goto_1

    :sswitch_11
    const-string v2, "ja"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_12

    goto/16 :goto_0

    :cond_12
    const/16 v1, 0x10

    goto/16 :goto_1

    :sswitch_12
    const-string v2, "it"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_13

    goto/16 :goto_0

    :cond_13
    const/16 v1, 0xf

    goto/16 :goto_1

    :sswitch_13
    const-string v2, "id"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_14

    goto/16 :goto_0

    :cond_14
    const/16 v1, 0xe

    goto/16 :goto_1

    :sswitch_14
    const-string v2, "hu"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_15

    goto/16 :goto_0

    :cond_15
    const/16 v1, 0xd

    goto/16 :goto_1

    :sswitch_15
    const-string v2, "hr"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_16

    goto/16 :goto_0

    :cond_16
    const/16 v1, 0xc

    goto/16 :goto_1

    :sswitch_16
    const-string v2, "he"

    invoke-virtual {v1, v2}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_17

    goto/16 :goto_0

    :cond_17
    const/16 v1, 0xb

    goto/16 :goto_1

    :sswitch_17
    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_18

    goto/16 :goto_0

    :cond_18
    const/16 v1, 0xa

    goto/16 :goto_1

    :sswitch_18
    invoke-virtual {v1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_19

    goto/16 :goto_0

    :cond_19
    const/16 v1, 0x9

    goto/16 :goto_1

    :sswitch_19
    invoke-virtual {v1, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1a

    goto/16 :goto_0

    :cond_1a
    const/16 v1, 0x8

    goto :goto_1

    :sswitch_1a
    invoke-virtual {v1, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1b

    goto/16 :goto_0

    :cond_1b
    const/4 v1, 0x7

    goto :goto_1

    :sswitch_1b
    invoke-virtual {v1, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1c

    goto/16 :goto_0

    :cond_1c
    move v1, v10

    goto :goto_1

    :sswitch_1c
    invoke-virtual {v1, v13}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1d

    goto/16 :goto_0

    :cond_1d
    move v1, v12

    goto :goto_1

    :sswitch_1d
    invoke-virtual {v1, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1e

    goto/16 :goto_0

    :cond_1e
    move v1, v14

    goto :goto_1

    :sswitch_1e
    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_1f

    goto/16 :goto_0

    :cond_1f
    move/from16 v1, v16

    goto :goto_1

    :sswitch_1f
    invoke-virtual {v1, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_20

    goto/16 :goto_0

    :cond_20
    move/from16 v1, v18

    goto :goto_1

    :sswitch_20
    invoke-virtual {v1, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_21

    goto/16 :goto_0

    :cond_21
    move/from16 v1, v19

    goto :goto_1

    :sswitch_21
    invoke-virtual {v1, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v1

    if-nez v1, :cond_22

    goto/16 :goto_0

    :cond_22
    move/from16 v1, v20

    .line 57
    :goto_1
    const-string v2, "localization_fr.bin"

    const-string v22, "localization_he.bin"

    const-string v23, "localization_hr.bin"

    const-string v24, "localization_hu.bin"

    const-string v25, "localization_id.bin"

    const-string v26, "localization_it.bin"

    const-string v27, "localization_ja.bin"

    const-string v28, "localization_kk.bin"

    const-string v29, "localization_ko.bin"

    const-string v30, "localization_ms.bin"

    const-string v31, "localization_nb.bin"

    const-string v32, "localization_nl.bin"

    const-string v33, "localization_pl.bin"

    const-string v34, "localization_ro.bin"

    const-string v35, "localization_ru.bin"

    const-string v36, "localization_sk.bin"

    const-string v37, "localization_sr.bin"

    const-string v38, "localization_sv.bin"

    const-string v39, "localization_tr.bin"

    const-string v40, "localization_uk.bin"

    const-string v41, "localization_uz.bin"

    packed-switch v1, :pswitch_data_0

    invoke-virtual/range {p0 .. p0}, Ljava/util/Locale;->getLanguage()Ljava/lang/String;

    move-result-object v1

    .line 59
    invoke-virtual {v1}, Ljava/lang/Object;->getClass()Ljava/lang/Class;

    invoke-virtual {v1}, Ljava/lang/String;->hashCode()I

    move-result v42

    sparse-switch v42, :sswitch_data_1

    :goto_2
    move/from16 v4, v21

    goto/16 :goto_3

    :sswitch_22
    const-string/jumbo v0, "uz"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_23

    goto :goto_2

    :cond_23
    const/16 v4, 0x20

    goto/16 :goto_3

    :sswitch_23
    const-string/jumbo v0, "uk"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_24

    goto :goto_2

    :cond_24
    const/16 v4, 0x1f

    goto/16 :goto_3

    :sswitch_24
    const-string/jumbo v0, "tr"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_25

    goto :goto_2

    :cond_25
    const/16 v4, 0x1e

    goto/16 :goto_3

    :sswitch_25
    const-string v0, "sv"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_26

    goto :goto_2

    :cond_26
    const/16 v4, 0x1d

    goto/16 :goto_3

    :sswitch_26
    const-string v0, "sr"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_27

    goto :goto_2

    :cond_27
    const/16 v4, 0x1c

    goto/16 :goto_3

    :sswitch_27
    const-string v0, "sk"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_28

    goto :goto_2

    :cond_28
    const/16 v4, 0x1b

    goto/16 :goto_3

    :sswitch_28
    const-string v0, "ru"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_29

    goto :goto_2

    :cond_29
    const/16 v4, 0x1a

    goto/16 :goto_3

    :sswitch_29
    const-string v0, "ro"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2a

    goto :goto_2

    :cond_2a
    const/16 v4, 0x19

    goto/16 :goto_3

    :sswitch_2a
    const-string v0, "pl"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2b

    goto :goto_2

    :cond_2b
    const/16 v4, 0x18

    goto/16 :goto_3

    :sswitch_2b
    const-string v0, "nl"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2c

    goto/16 :goto_2

    :cond_2c
    const/16 v4, 0x17

    goto/16 :goto_3

    :sswitch_2c
    const-string v0, "nb"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2d

    goto/16 :goto_2

    :cond_2d
    const/16 v4, 0x16

    goto/16 :goto_3

    :sswitch_2d
    const-string v0, "ms"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2e

    goto/16 :goto_2

    :cond_2e
    const/16 v4, 0x15

    goto/16 :goto_3

    :sswitch_2e
    const-string v0, "ko"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_2f

    goto/16 :goto_2

    :cond_2f
    const/16 v4, 0x14

    goto/16 :goto_3

    :sswitch_2f
    const-string v0, "kk"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_30

    goto/16 :goto_2

    :cond_30
    const/16 v4, 0x13

    goto/16 :goto_3

    :sswitch_30
    const-string v0, "ja"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_31

    goto/16 :goto_2

    :cond_31
    const/16 v4, 0x12

    goto/16 :goto_3

    :sswitch_31
    const-string v0, "iw"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_32

    goto/16 :goto_2

    :cond_32
    const/16 v4, 0x11

    goto/16 :goto_3

    :sswitch_32
    const-string v0, "it"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_33

    goto/16 :goto_2

    :cond_33
    const/16 v4, 0x10

    goto/16 :goto_3

    :sswitch_33
    const-string v0, "in"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_34

    goto/16 :goto_2

    :cond_34
    const/16 v4, 0xf

    goto/16 :goto_3

    :sswitch_34
    const-string v0, "id"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_35

    goto/16 :goto_2

    :cond_35
    const/16 v4, 0xe

    goto/16 :goto_3

    :sswitch_35
    const-string v0, "hu"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_36

    goto/16 :goto_2

    :cond_36
    const/16 v4, 0xd

    goto/16 :goto_3

    :sswitch_36
    const-string v0, "hr"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_37

    goto/16 :goto_2

    :cond_37
    const/16 v4, 0xc

    goto/16 :goto_3

    :sswitch_37
    const-string v0, "he"

    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_38

    goto/16 :goto_2

    :cond_38
    const/16 v4, 0xb

    goto/16 :goto_3

    :sswitch_38
    invoke-virtual {v1, v3}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_39

    goto/16 :goto_2

    :cond_39
    const/16 v4, 0xa

    goto/16 :goto_3

    :sswitch_39
    invoke-virtual {v1, v5}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3a

    goto/16 :goto_2

    :cond_3a
    const/16 v4, 0x9

    goto/16 :goto_3

    :sswitch_3a
    invoke-virtual {v1, v7}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3b

    goto/16 :goto_2

    :cond_3b
    const/16 v4, 0x8

    goto :goto_3

    :sswitch_3b
    invoke-virtual {v1, v9}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3c

    goto/16 :goto_2

    :cond_3c
    const/4 v4, 0x7

    goto :goto_3

    :sswitch_3c
    invoke-virtual {v1, v11}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3d

    goto/16 :goto_2

    :cond_3d
    move v4, v10

    goto :goto_3

    :sswitch_3d
    invoke-virtual {v1, v13}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3e

    goto/16 :goto_2

    :cond_3e
    move v4, v12

    goto :goto_3

    :sswitch_3e
    invoke-virtual {v1, v15}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_3f

    goto/16 :goto_2

    :cond_3f
    move v4, v14

    goto :goto_3

    :sswitch_3f
    invoke-virtual {v1, v0}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_40

    goto/16 :goto_2

    :cond_40
    move/from16 v4, v16

    goto :goto_3

    :sswitch_40
    invoke-virtual {v1, v4}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_41

    goto/16 :goto_2

    :cond_41
    move/from16 v4, v18

    goto :goto_3

    :sswitch_41
    invoke-virtual {v1, v6}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_42

    goto/16 :goto_2

    :cond_42
    move/from16 v4, v19

    goto :goto_3

    :sswitch_42
    invoke-virtual {v1, v8}, Ljava/lang/String;->equals(Ljava/lang/Object;)Z

    move-result v0

    if-nez v0, :cond_43

    goto/16 :goto_2

    :cond_43
    move/from16 v4, v20

    :goto_3
    packed-switch v4, :pswitch_data_1

    return-object v17

    :pswitch_0
    return-object v41

    :pswitch_1
    return-object v40

    :pswitch_2
    return-object v39

    :pswitch_3
    return-object v38

    :pswitch_4
    return-object v37

    :pswitch_5
    return-object v36

    :pswitch_6
    return-object v35

    :pswitch_7
    return-object v34

    :pswitch_8
    return-object v33

    :pswitch_9
    return-object v32

    :pswitch_a
    return-object v31

    :pswitch_b
    return-object v30

    :pswitch_c
    return-object v29

    :pswitch_d
    return-object v28

    :pswitch_e
    return-object v27

    :pswitch_f
    return-object v26

    :pswitch_10
    return-object v25

    :pswitch_11
    return-object v24

    :pswitch_12
    return-object v23

    :pswitch_13
    return-object v22

    :pswitch_14
    return-object v2

    .line 69
    :pswitch_15
    const-string v0, "localization_fi.bin"

    return-object v0

    .line 68
    :pswitch_16
    const-string v0, "localization_fa.bin"

    return-object v0

    .line 67
    :pswitch_17
    const-string v0, "localization_es.bin"

    return-object v0

    .line 66
    :pswitch_18
    const-string v0, "localization_en.bin"

    return-object v0

    .line 65
    :pswitch_19
    const-string v0, "localization_de.bin"

    return-object v0

    .line 64
    :pswitch_1a
    const-string v0, "localization_cs.bin"

    return-object v0

    .line 63
    :pswitch_1b
    const-string v0, "localization_ca.bin"

    return-object v0

    .line 62
    :pswitch_1c
    const-string v0, "localization_be.bin"

    return-object v0

    .line 61
    :pswitch_1d
    const-string v0, "localization_az.bin"

    return-object v0

    .line 60
    :pswitch_1e
    const-string v0, "localization_ar.bin"

    return-object v0

    .line 54
    :pswitch_1f
    const-string v0, "localization_zh_tw.bin"

    return-object v0

    .line 53
    :pswitch_20
    const-string v0, "localization_zh_cn.bin"

    return-object v0

    .line 44
    :pswitch_21
    const-string v0, "localization_pt_br.bin"

    return-object v0

    :pswitch_22
    return-object v41

    :pswitch_23
    return-object v40

    :pswitch_24
    return-object v39

    :pswitch_25
    return-object v38

    :pswitch_26
    return-object v37

    :pswitch_27
    return-object v36

    :pswitch_28
    return-object v35

    :pswitch_29
    return-object v34

    :pswitch_2a
    return-object v33

    :pswitch_2b
    return-object v32

    :pswitch_2c
    return-object v31

    :pswitch_2d
    return-object v30

    :pswitch_2e
    return-object v29

    :pswitch_2f
    return-object v28

    :pswitch_30
    return-object v27

    :pswitch_31
    return-object v26

    :pswitch_32
    return-object v25

    :pswitch_33
    return-object v24

    :pswitch_34
    return-object v23

    :pswitch_35
    return-object v22

    :pswitch_36
    return-object v2

    .line 30
    :pswitch_37
    const-string v0, "localization_fi.bin"

    return-object v0

    .line 29
    :pswitch_38
    const-string v0, "localization_fa.bin"

    return-object v0

    .line 28
    :pswitch_39
    const-string v0, "localization_es.bin"

    return-object v0

    .line 27
    :pswitch_3a
    const-string v0, "localization_en.bin"

    return-object v0

    .line 26
    :pswitch_3b
    const-string v0, "localization_de.bin"

    return-object v0

    .line 25
    :pswitch_3c
    const-string v0, "localization_cs.bin"

    return-object v0

    .line 24
    :pswitch_3d
    const-string v0, "localization_ca.bin"

    return-object v0

    .line 23
    :pswitch_3e
    const-string v0, "localization_be.bin"

    return-object v0

    .line 22
    :pswitch_3f
    const-string v0, "localization_az.bin"

    return-object v0

    .line 21
    :pswitch_40
    const-string v0, "localization_ar.bin"

    return-object v0

    nop

    :sswitch_data_0
    .sparse-switch
        0xc31 -> :sswitch_21
        0xc39 -> :sswitch_20
        0xc43 -> :sswitch_1f
        0xc5e -> :sswitch_1e
        0xc70 -> :sswitch_1d
        0xc81 -> :sswitch_1c
        0xca9 -> :sswitch_1b
        0xcae -> :sswitch_1a
        0xcbb -> :sswitch_19
        0xcc3 -> :sswitch_18
        0xccc -> :sswitch_17
        0xcfd -> :sswitch_16
        0xd0a -> :sswitch_15
        0xd0d -> :sswitch_14
        0xd1b -> :sswitch_13
        0xd2b -> :sswitch_12
        0xd37 -> :sswitch_11
        0xd60 -> :sswitch_10
        0xd64 -> :sswitch_f
        0xda6 -> :sswitch_e
        0xdb4 -> :sswitch_d
        0xdbe -> :sswitch_c
        0xdfc -> :sswitch_b
        0xe3d -> :sswitch_a
        0xe43 -> :sswitch_9
        0xe58 -> :sswitch_8
        0xe5f -> :sswitch_7
        0xe63 -> :sswitch_6
        0xe7e -> :sswitch_5
        0xe96 -> :sswitch_4
        0xea5 -> :sswitch_3
        0x65fb4b9 -> :sswitch_2
        0x6e72b6a -> :sswitch_1
        0x6e72d82 -> :sswitch_0
    .end sparse-switch

    :pswitch_data_0
    .packed-switch 0x0
        :pswitch_40
        :pswitch_3f
        :pswitch_3e
        :pswitch_3d
        :pswitch_3c
        :pswitch_3b
        :pswitch_3a
        :pswitch_39
        :pswitch_38
        :pswitch_37
        :pswitch_36
        :pswitch_35
        :pswitch_34
        :pswitch_33
        :pswitch_32
        :pswitch_31
        :pswitch_30
        :pswitch_2f
        :pswitch_2e
        :pswitch_2d
        :pswitch_2c
        :pswitch_2b
        :pswitch_2a
        :pswitch_29
        :pswitch_28
        :pswitch_27
        :pswitch_26
        :pswitch_25
        :pswitch_24
        :pswitch_23
        :pswitch_22
        :pswitch_21
        :pswitch_20
        :pswitch_1f
    .end packed-switch

    :sswitch_data_1
    .sparse-switch
        0xc31 -> :sswitch_42
        0xc39 -> :sswitch_41
        0xc43 -> :sswitch_40
        0xc5e -> :sswitch_3f
        0xc70 -> :sswitch_3e
        0xc81 -> :sswitch_3d
        0xca9 -> :sswitch_3c
        0xcae -> :sswitch_3b
        0xcbb -> :sswitch_3a
        0xcc3 -> :sswitch_39
        0xccc -> :sswitch_38
        0xcfd -> :sswitch_37
        0xd0a -> :sswitch_36
        0xd0d -> :sswitch_35
        0xd1b -> :sswitch_34
        0xd25 -> :sswitch_33
        0xd2b -> :sswitch_32
        0xd2e -> :sswitch_31
        0xd37 -> :sswitch_30
        0xd60 -> :sswitch_2f
        0xd64 -> :sswitch_2e
        0xda6 -> :sswitch_2d
        0xdb4 -> :sswitch_2c
        0xdbe -> :sswitch_2b
        0xdfc -> :sswitch_2a
        0xe3d -> :sswitch_29
        0xe43 -> :sswitch_28
        0xe58 -> :sswitch_27
        0xe5f -> :sswitch_26
        0xe63 -> :sswitch_25
        0xe7e -> :sswitch_24
        0xe96 -> :sswitch_23
        0xea5 -> :sswitch_22
    .end sparse-switch

    :pswitch_data_1
    .packed-switch 0x0
        :pswitch_1e
        :pswitch_1d
        :pswitch_1c
        :pswitch_1b
        :pswitch_1a
        :pswitch_19
        :pswitch_18
        :pswitch_17
        :pswitch_16
        :pswitch_15
        :pswitch_14
        :pswitch_13
        :pswitch_12
        :pswitch_11
        :pswitch_10
        :pswitch_10
        :pswitch_f
        :pswitch_13
        :pswitch_e
        :pswitch_d
        :pswitch_c
        :pswitch_b
        :pswitch_a
        :pswitch_9
        :pswitch_8
        :pswitch_7
        :pswitch_6
        :pswitch_5
        :pswitch_4
        :pswitch_3
        :pswitch_2
        :pswitch_1
        :pswitch_0
    .end packed-switch
.end method
