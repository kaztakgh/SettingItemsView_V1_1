[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SingleSelectItem](./index.md)

# SingleSelectItem

`data class SingleSelectItem : `[`ItemInterface`](../-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

指定されたものから1つ選択するアイテムの作成
    SpinnerItemと比較すると、選択するものに画像、音楽などを追加することができる
        アイテムを押下したとき、ダイアログを表示する

### Types

| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`SingleSelectItem`](./index.md)`>` |

### Constructors

| [&lt;init&gt;](-init-.md) | `SingleSelectItem(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``SingleSelectItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`? = null, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, options: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>? = null, optionBitmaps: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`>? = null, optionSounds: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`? = null, optionSelectable: `[`BooleanArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean-array/index.html)`? = null, columns: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1, bitmapWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = SelectorItemAdapter.INIT_GRAPH_MAX_WIDTH_DP, bitmapHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = SelectorItemAdapter.INIT_GRAPH_MAX_HEIGHT_DP, select: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 0, requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1)`<br>指定されたものから1つ選択するアイテムの作成     SpinnerItemと比較すると、選択するものに画像、音楽などを追加することができる         アイテムを押下したとき、ダイアログを表示する |

### Properties

| [bitmapHeight](bitmap-height.md) | `val bitmapHeight: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>(画像がある場合)画像の高さ |
| [bitmapWidth](bitmap-width.md) | `val bitmapWidth: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>(画像がある場合)画像の幅 |
| [bmpIcon](bmp-icon.md) | `val bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>左側に表示するアイコン(省略時null) |
| [columns](columns.md) | `val columns: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>ダイアログの表示列(省略時1) |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか(省略時true) |
| [optionBitmaps](option-bitmaps.md) | `val optionBitmaps: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`>?`<br>選択肢画像 |
| [optionSelectable](option-selectable.md) | `val optionSelectable: `[`BooleanArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean-array/index.html)`?`<br>選択肢が選べるか(省略時全てtrue) |
| [optionSounds](option-sounds.md) | `val optionSounds: `[`IntArray`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int-array/index.html)`?`<br>効果音(省略時null) |
| [options](options.md) | `val options: `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`>?`<br>選択肢一覧(この項目と選択肢画像のどちらかは必須) |
| [requestCode](request-code.md) | `val requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>リクエストコード |
| [select](select.md) | `var select: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>初期選択位置(省略時0) |
| [tag](tag.md) | `val tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タグ(識別子、省略不可) |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル(省略不可) |

### Functions

| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [equals](equals.md) | `fun equals(other: `[`Any`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-any/index.html)`?): `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html) |
| [hashCode](hash-code.md) | `fun hashCode(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`SingleSelectItem`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`SingleSelectItem`](./index.md)`?>` |

