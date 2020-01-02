[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [SwitchItem](./index.md)

# SwitchItem

`data class SwitchItem : `[`ItemInterface`](../-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

状態をON/OFFで指定するアイテムの作成

### Types

| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`SwitchItem`](./index.md)`>` |
| [OnValueChangedListener](-on-value-changed-listener/index.md) | `interface OnValueChangedListener`<br>スイッチのチェックが変更されたときの処理 |

### Constructors

| [&lt;init&gt;](-init-.md) | `SwitchItem(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``SwitchItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`? = null, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, checked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, textOnTrue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = SwitchItemViewHolder.STATE_ON_TEXT_DEFAULT, textOnFalse: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = SwitchItemViewHolder.STATE_OFF_TEXT_DEFAULT)`<br>状態をON/OFFで指定するアイテムの作成 |

### Properties

| [bmpIcon](bmp-icon.md) | `val bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>左側に表示するアイコン(省略時null) |
| [checked](checked.md) | `var checked: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>チェック(省略時false) |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか(省略時true) |
| [tag](tag.md) | `val tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タグ(識別子、省略不可) |
| [textOnFalse](text-on-false.md) | `val textOnFalse: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>OFFの時に表示するテキスト(省略時OFF) |
| [textOnTrue](text-on-true.md) | `val textOnTrue: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>ONの時に表示するテキスト(省略時ON) |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル(省略不可) |
| [valueChangedListener](value-changed-listener.md) | `var valueChangedListener: `[`OnValueChangedListener`](-on-value-changed-listener/index.md)`?` |

### Functions

| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`SwitchItem`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`SwitchItem`](./index.md)`?>` |

