[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [DateItem](./index.md)

# DateItem

`data class DateItem : `[`ItemInterface`](../-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

日付を選択するアイテムの作成

### Types

| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`DateItem`](./index.md)`>` |

### Constructors

| [&lt;init&gt;](-init-.md) | `DateItem(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``DateItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, date: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)` = Calendar.getInstance(), bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`? = null, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, format: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "yyyy/MM/dd", requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1)`<br>日付を選択するアイテムの作成 |

### Properties

| [bmpIcon](bmp-icon.md) | `val bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>左側に表示するアイコン(省略時null) |
| [date](date.md) | `var date: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)<br>日付 |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか(省略時true) |
| [format](format.md) | `val format: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>出力フォーマット |
| [requestCode](request-code.md) | `val requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>リクエストコード |
| [tag](tag.md) | `val tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タグ(識別子、省略不可) |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル(省略不可) |

### Functions

| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`DateItem`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`DateItem`](./index.md)`?>` |

