[settingitemsviewlibrary](../../index.md) / [io.github.kaztakgh.settingitemsviewlibrary](../index.md) / [TimeItem](./index.md)

# TimeItem

`data class TimeItem : `[`ItemInterface`](../-item-interface/index.md)`, `[`Parcelable`](https://developer.android.com/reference/android/os/Parcelable.html)

時刻を選択するアイテムの作成

### Types

| [CREATOR](-c-r-e-a-t-o-r/index.md) | `companion object CREATOR : `[`Creator`](https://developer.android.com/reference/android/os/Parcelable/Creator.html)`<`[`TimeItem`](./index.md)`>` |

### Constructors

| [&lt;init&gt;](-init-.md) | `TimeItem(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`)``TimeItem(title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)`, time: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)` = Calendar.getInstance(), bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`? = null, enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = true, display24h: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)` = false, format24h: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "H:mm", format12h: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)` = "h:mm a", requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)` = 1)`<br>時刻を選択するアイテムの作成 |

### Properties

| [bmpIcon](bmp-icon.md) | `val bmpIcon: `[`Bitmap`](https://developer.android.com/reference/android/graphics/Bitmap.html)`?`<br>左側に表示するアイコン(省略時null) |
| [display24h](display24h.md) | `val display24h: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>24時間表記(省略時false) |
| [enabled](enabled.md) | `var enabled: `[`Boolean`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-boolean/index.html)<br>選択可能であるか(省略時true) |
| [format12h](format12h.md) | `val format12h: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>12時間表記の場合のフォーマット |
| [format24h](format24h.md) | `val format24h: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>24時間表記の場合のフォーマット |
| [requestCode](request-code.md) | `val requestCode: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)<br>リクエストコード |
| [tag](tag.md) | `val tag: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タグ(識別子、省略不可) |
| [time](time.md) | `var time: `[`Calendar`](https://developer.android.com/reference/java/util/Calendar.html)<br>時刻 |
| [title](title.md) | `val title: `[`String`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-string/index.html)<br>タイトル(省略不可) |

### Functions

| [describeContents](describe-contents.md) | `fun describeContents(): `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html) |
| [writeToParcel](write-to-parcel.md) | `fun writeToParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`, flags: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Unit`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-unit/index.html) |

### Companion Object Functions

| [createFromParcel](create-from-parcel.md) | `fun createFromParcel(parcel: `[`Parcel`](https://developer.android.com/reference/android/os/Parcel.html)`): `[`TimeItem`](./index.md) |
| [newArray](new-array.md) | `fun newArray(size: `[`Int`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-int/index.html)`): `[`Array`](https://kotlinlang.org/api/latest/jvm/stdlib/kotlin/-array/index.html)`<`[`TimeItem`](./index.md)`?>` |

