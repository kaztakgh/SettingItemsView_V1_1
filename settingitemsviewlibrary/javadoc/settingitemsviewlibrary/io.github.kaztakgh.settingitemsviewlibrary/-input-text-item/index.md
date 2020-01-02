[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [InputTextItem](./index.md)

# InputTextItem

`data class InputTextItem : `[`ItemInterface`](../-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

テキストを入力するアイテムの作成

### Types

| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`InputTextItem`](./index.md)`>` |
| [OnTextChangedListener](-on-text-changed-listener/index.md) | `interface OnTextChangedListener`<br>テキストを変更したときの処理 |

### Constructors

| [&lt;init&gt;](-init-.md) | `InputTextItem(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``InputTextItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`? = null, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "", length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = InputTextItemViewHolder.INIT_LENGTH, inputType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = InputType.TYPE_CLASS_TEXT)`<br>テキストを入力するアイテムの作成 |

### Properties

| [bmpIcon](bmp-icon.md) | `val bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>左側に表示するアイコン(省略時null) |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか(省略時true) |
| [inputType](input-type.md) | `val inputType: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>入力可能な文字列の形式(InputTypeクラスの定数を利用すること) |
| [length](length.md) | `val length: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>テキストの長さ(省略時はViewHolderで指定した長さを利用する) |
| [tag](tag.md) | `val tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タグ(識別子、省略不可) |
| [text](text.md) | `var text: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>テキスト |
| [textChangedListener](text-changed-listener.md) | `var textChangedListener: `[`OnTextChangedListener`](-on-text-changed-listener/index.md)`?` |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル(省略不可) |

### Functions

| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`InputTextItem`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`InputTextItem`](./index.md)`?>` |

